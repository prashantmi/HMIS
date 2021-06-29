package opd.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.ConsultationProfileDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.ConsultationInboxDATA;
import opd.transaction.controller.data.PatientProfileInboxDATA;
import opd.transaction.controller.fb.ConsultationInboxFB;

public class ConsultationInboxUTIL extends ControllerUTIL {
	
	public static boolean getEssentials(ConsultationInboxFB _fb, HttpServletRequest _rq)
	{
		boolean flag=true;
		Status objStatus= new Status();
		setSysdate(_rq);
		//String seatId;
		//String empNo="";
		//String compSeatId;
		
		List consultantList=null;
		//List cosultantListWithSeatidAndNo=null;
		try{
			//_fb.setSelectedPatCrNo(_fb.getPatCrNo());-------// No Selection
		//seatId=getUserVO(_rq).getSeatId();
		Map map=ConsultationInboxDATA.getEssentials(getUserVO(_rq));
		//cosultantListWithSeatidAndNo=(List)map.get(OpdConfig.OPD_CONSULTANT_NO_SEATID);
		consultantList=(List)map.get(OpdConfig.OPD_ECONSULTANT_DETAIL_LIST);
	/*	ListIterator cosultantListWithSeatidAndNoItr=cosultantListWithSeatidAndNo.listIterator();
		while(cosultantListWithSeatidAndNoItr.hasNext()){
			Entry entry =(Entry)cosultantListWithSeatidAndNoItr.next();
			if(seatId.equals(entry.getLabel())){
				empNo=entry.getValue();
				break;
			}
		}*/
		/*ListIterator consultantListItr=consultantList.listIterator();
		while(consultantListItr.hasNext()){
			Entry entry=(Entry)consultantListItr.next();
			if(seatId.equals(entry.getValue())){
				consultantListItr.remove();
				break;
			}
		}*/
			
		map.put(OpdConfig.OPD_ECONSULTANT_DETAIL_LIST, consultantList);
		WebUTIL.setMapInSession(map, _rq);
		objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	return flag;
	}

	public static void getMailContent(ConsultationInboxFB _fb, HttpServletRequest _rq)
	{
		//boolean flag=true;
		ConsultationDtlVO[] consultationVO=null;
		//String mailId=null;
		Status objStatus= new Status();
		setSysdate(_rq);
		int i=0;
		//List<PatientProfileDetailVO> lstProfiles = null;
		List<ConsultationProfileDtlVO> consultationProfileDtlVOList = null;
		List<Entry> listProfileCombo=null;
		List<PatientProfileDetailVO> lstProfiles = null;
		try{
			//_fb.setSelectedPatCrNo(_fb.getPatCrNo()); // -----
		HttpSession session=WebUTIL.getSession(_rq);
		consultationVO=(ConsultationDtlVO[])session.getAttribute(OpdConfig.CONSULTATION_INBOX_DETAIL_VO);
		for(; i<consultationVO.length;i++){
			if(_fb.getMailRequestId().equals(consultationVO[i].getMailRequestId())){
				break; 
			}
		}
		if(consultationVO[i].getAckStatus().equals(OpdConfig.OPD_CONSULTANT_ACK_STATUS_NEW)){
		ConsultationInboxDATA.updateMailAckStatus(consultationVO[i] ,_fb.getMailRequestId(),OpdConfig.OPD_CONSULTANT_ACK_STATUS_READ, getUserVO(_rq));
		}
		PatientDetailVO patDtlVO = InpatientDetailUTL.getInpatientDetailByPatCrNo(_fb.getMailPatCrNo(), DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK, getUserVO(_rq));
		WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_CONSULTATION_INBOX_MAIL_PATIENT_DETAIL_VO, patDtlVO);
		
        /**Added by Vasu to get patient profile at consultation inbox*/
		
		lstProfiles = PatientProfileInboxDATA.getPatientProfilesForInbox(_fb.getMailPatCrNo(), patDtlVO.getDepartmentUnitCode(), getUserVO(_rq));
		
		WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILES_FOR_INBOX_LIST, lstProfiles);
		WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST, lstProfiles);
		
		/**End Vasu**/
		
		consultationProfileDtlVOList=ConsultationInboxDATA.getPatientProfilesForInbox(_fb.getMailRequestId(), getUserVO(_rq));
		if((consultationProfileDtlVOList!=null) && (consultationProfileDtlVOList.size()>0))
		{
			listProfileCombo=new ArrayList<Entry>();
			Iterator lstProfilesIterator=consultationProfileDtlVOList.iterator();
			while(lstProfilesIterator.hasNext())
			{
				ConsultationProfileDtlVO consultationProfileDtlVO=(ConsultationProfileDtlVO)lstProfilesIterator.next();
				Entry entry=new Entry();
				entry.setValue(consultationProfileDtlVO.getProfileId());
				entry.setLabel((String)consultationProfileDtlVO.getProfileHeader());
				listProfileCombo.add(entry);
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PROFILE_LIST_COMBO, listProfileCombo);
		}
		
		//_rq.setAttribute("patCrNo",consultationVO[i].getPatCrNo());//---------
		
		WebUTIL.populate(_fb, consultationVO[i]);
		_fb.setMailPatCrNo(consultationVO[i].getPatCrNo());
		
		
		
		objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e){
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	
	}
	
	public static void replyMailContent(ConsultationInboxFB _fb, HttpServletRequest _rq)
	{
		//boolean flag=true;
		ConsultationDtlVO[] consultationVO=null;
		String mailId=null;
		Status objStatus= new Status();
		setSysdate(_rq);
		int i=0;
		try{
			HttpSession session=WebUTIL.getSession(_rq);
			consultationVO=(ConsultationDtlVO[])session.getAttribute(OpdConfig.CONSULTATION_INBOX_DETAIL_VO);
			mailId=_fb.getMailRequestId();
			for(; i<consultationVO.length;i++){
				if(mailId.equals(consultationVO[i].getMailRequestId())){
					break; 
				}
			}
		String subject=consultationVO[i].getSubject();
		_fb.setToDoctor(_fb.getFromDoctor());
		_fb.setToDoctorCode(_fb.getFromDoctorCode());
		_fb.setToDoctorSeatId(_fb.getFromDoctorSeatId());
		//String subject=_fb.getSubject();
		if(subject==null)//Added by Prachi
		{
			subject="RE: ";
		}
		else
		{
		subject="RE: "+subject;
		}
		_fb.setSubject(subject);
	//	_rq.setAttribute("patCrNo",_fb.getpat);
		
		objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	
	}
	
	public static void sendMail(ConsultationInboxFB _fb, HttpServletRequest _rq)
	{
		
			Status objStatus=new Status();
			HttpSession session=WebUTIL.getSession(_rq);
			ConsultationDtlVO consultationDtlVO=new ConsultationDtlVO();
			ConsultationDtlVO[] consultationVO=null;
			//DailyPatientVO[] dailyPatientVO;
			int i=0;
			Entry entry1=null;
			String mailId=null;
			boolean flag=false;
			try{
				
				HelperMethods.populate(consultationDtlVO, _fb);
				//List fromDocDetail=(List)session.getAttribute(OpdConfig.OPD_CONSULTANT_NAME_NO);
				List toDocNoSeatid=(List)session.getAttribute(OpdConfig.OPD_CONSULTANT_NO_SEATID);
				consultationVO=(ConsultationDtlVO[])session.getAttribute(OpdConfig.CONSULTATION_INBOX_DETAIL_VO);
				mailId=_fb.getMailRequestId();
				for(; i<consultationVO.length;i++){
					if(mailId.equals(consultationVO[i].getMailRequestId())){
						flag=true;
						break;
							}
				}
				if(flag){
					consultationDtlVO.setEpisodeCode(consultationVO[i].getEpisodeCode());
					consultationDtlVO.setEpisodeVisitNo(consultationVO[i].getEpisodeVisitNo());
					consultationDtlVO.setPatCrNo(consultationVO[i].getPatCrNo());
					consultationDtlVO.setEpisodeVisitNo(consultationVO[i].getEpisodeVisitNo());
				}
				consultationDtlVO.setMailType(OpdConfig.OPD_COSULTANT_REPLY_MAIL);
				consultationDtlVO.setMailParentRequestId(mailId);
			//	Entry entry=(Entry)fromDocDetail.get(0);
				consultationDtlVO.setFromDoctorCode(getUserVO(_rq).getUserEmpID());
				consultationDtlVO.setFromDoctorSeatId(getUserVO(_rq).getSeatId());
				consultationDtlVO.setMailType(OpdConfig.OPD_COSULTANT_NEW_MAIL);
				ListIterator listIteratorToDocNoSeatid=toDocNoSeatid.listIterator();
				
				while(listIteratorToDocNoSeatid.hasNext()){
					 entry1=(Entry)listIteratorToDocNoSeatid.next();
					if(consultationDtlVO.getToDoctorSeatId().equals(entry1.getValue())){
						break;
					}
					
				}
				consultationDtlVO.setToDoctorCode(entry1.getLabel());
				if((_fb.getToDoctor()!=null)&&(!(_fb.getToDoctor().equals(""))))
				{
				consultationDtlVO.setToDoctName(_fb.getToDoctor());
				}
				else
				{	
					String part[]=_fb.getToDoctorCode().split("#");
					consultationDtlVO.setToDoctName(part[1]);
				}
				consultationDtlVO.setUserType("1");
				String content=consultationDtlVO.getContent().trim();
				consultationDtlVO.setContent(content);
				consultationDtlVO.setAckStatus(OpdConfig.OPD_CONSULTANT_ACK_STATUS_NEW);
				ConsultationInboxDATA.sendMail(consultationDtlVO, getUserVO(_rq));
				//_fb.setPatCrNo(_fb.getSelectedPatCrNo());---------
				objStatus.add(Status.DONE,"","Mail Sent Succesfully");
		objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	
	}
	
	public static void forwardMailContent(ConsultationInboxFB _fb, HttpServletRequest _rq)
	{
		//boolean flag=true;
		ConsultationDtlVO[] consultationVO=null;
		String mailId=null;
		Status objStatus= new Status();
		setSysdate(_rq);
		int i=0;
		try{
			HttpSession session=WebUTIL.getSession(_rq);
			consultationVO=(ConsultationDtlVO[])session.getAttribute(OpdConfig.CONSULTATION_INBOX_DETAIL_VO);
			mailId=_fb.getMailRequestId();
			for(; i<consultationVO.length;i++){
				if(mailId.equals(consultationVO[i].getMailRequestId())){
					break; 
				}
			}
		String subject=consultationVO[i].getSubject();
		if(subject==null)//Added by prachi
		{
			subject="FW: ";
		}
		else
		{
		subject="FW: "+subject;
		}
		//subject="FW: "+subject;
		_fb.setSubject(subject);
	//	_rq.setAttribute("patCrNo",_fb.getpat);
		
		objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	
	}
	
	public static void deleteMail(ConsultationInboxFB _fb, HttpServletRequest _rq)
	{
		//boolean flag=true;
		//ConsultationDtlVO[] consultationVO=null;
		//String mailId=null;
		Status objStatus= new Status();
		String selectedMailId[];
		//int i=0;
		try{
		selectedMailId=_fb.getSelectedMailId();
		ConsultationInboxDATA.deleteMails(selectedMailId,getUserVO(_rq));
		objStatus.add(Status.DONE, "", "Mail Deleted Successfully");
		objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	
	}
	
	public static void getPreviousMailContent(ConsultationInboxFB _fb, HttpServletRequest _rq)
	{
		//boolean flag=true;
		ConsultationDtlVO[] consultationVO=null;
		ConsultationDtlVO consultationParentDtlVO=null;
		//String mailId=null;
		Status objStatus= new Status();
		setSysdate(_rq);
		int i=0;
		try{
			//_fb.setSelectedPatCrNo(_fb.getPatCrNo());------
		HttpSession session=WebUTIL.getSession(_rq);
		consultationVO=(ConsultationDtlVO[])session.getAttribute(OpdConfig.CONSULTATION_INBOX_DETAIL_VO);
		boolean matchFlag=false;
		for(; i<consultationVO.length;i++)
		{
			if(_fb.getMailParentRequestId().equals(consultationVO[i].getMailRequestId())){
				matchFlag=true;
				break; 
			}
		}
		
		if(matchFlag)
		{
			_fb.setMailPatCrNo(consultationVO[i].getPatCrNo());
			//_rq.setAttribute("patCrNo",consultationVO[i].getPatCrNo());-------
		}
		else
		{
			_fb.setMailPatCrNo(consultationVO[i-1].getPatCrNo());
			//_rq.setAttribute("patCrNo",consultationVO[i-1].getPatCrNo());------
		}
		
		consultationParentDtlVO=ConsultationInboxDATA.getMailDetailsByMailId(getUserVO(_rq),_fb.getMailParentRequestId());
		WebUTIL.setAttributeInSession(_rq,OpdConfig.ACKNOWLEDGED_MAIL_DETAIL, consultationParentDtlVO);
		
		PatientDetailVO patDtlVO = InpatientDetailUTL.getInpatientDetailByPatCrNo(_fb.getMailPatCrNo(), DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK, getUserVO(_rq));
		WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_CONSULTATION_INBOX_MAIL_PATIENT_DETAIL_VO, patDtlVO);

		objStatus.add(Status.TRANSINPROCESS);
		objStatus.add(Status.INPROCESS);
		}catch(HisRecordNotFoundException e){
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	
	}
}
