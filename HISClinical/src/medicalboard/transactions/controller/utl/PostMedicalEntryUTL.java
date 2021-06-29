package medicalboard.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.vo.MBInvestigationRequisitionDetailVO;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.MedicalBoardRequisitionChangeVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.MedicalBoardVerificationDtlVO;
import hisglobal.vo.UserVO;
import investigation.InvestigationConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.data.PostMedicalEntryDATA;
import medicalboard.transactions.controller.fb.PostMedicalEntryFB;
import registration.controller.fb.CRNoFB;
import registration.controller.util.PatDetailUTIL;


public class PostMedicalEntryUTL  extends ControllerUTIL{
	/**
	 * get the list of the certificate type for which candidate has attended
	 * get the list of the board no.
	 * get the list of the consultants
	 * @param _request -HttpServletRequest
	 */
	public static void getMBPostEntryEssential(PostMedicalEntryFB _fb,HttpServletRequest _request){	
	
		Status objStatus=new Status();
		Map mp=new HashMap();
		HisUtil hisutil = null;

		//List certificateTypeList=new ArrayList();
		try{
			UserVO userVO=getUserVO(_request);
			setSysdate(_request);
			mp=PostMedicalEntryDATA.getMBPostEntryEssential(userVO);
			hisutil=new HisUtil("cssd","RequestOfItemsUTIL");
			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			_fb.setSysDate(ctDate);
		 	WebUTIL.setMapInSession(mp, _request);        
	        objStatus.add(Status.NEW);
		
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());	
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Inside Exception");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		    System.out.println("objStatus in finally"+objStatus);		 
		    System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}
		
		
	/**
	 * get the list of the candidate who has attended on the schedule date
	 * @param _fb
	 * @param _request
	 */
	public static void getCandidateList(PostMedicalEntryFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		//Map mp=new HashMap();
		List <MedicalBoardRequisitionVO> mbRequisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		try{
			UserVO userVO=getUserVO(_request);
			String boardNo=_fb.getBoardNo();
			mbRequisitionVOList=PostMedicalEntryDATA.getCandidateList(_fb.getCertificateTypeID(),boardNo,
							_fb.getFromDate(),_fb.getToDate(),userVO);
			_fb.setCertificateTypeName(getCertificateTypeName(_fb.getCertificateTypeID(), _request));
			//_fb.setBoardName(getBoardName(boardNo, _request));
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST, mbRequisitionVOList);
			
			objStatus.add(Status.LIST);
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.LIST,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Inside Exception");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("objStatus in finally"+objStatus);		 
			System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}
		

	/**
	 * get the detail of the Candidate Referral 
	 * @param _fb
	 * @param _request
	 */
	public static void getCandidateDetailForPostEntry(PostMedicalEntryFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		List <MedicalBoardRequisitionVO> requisitionVOList=null;
		MedicalBoardRequisitionVO requisitionVO=new MedicalBoardRequisitionVO();
		
		try{
			UserVO userVO=getUserVO(_request);
			CRNoFB crNoFb=new CRNoFB();
			requisitionVOList=(List)_request.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
			if(requisitionVOList!=null){
				requisitionVO=requisitionVOList.get(Integer.parseInt(_fb.getSelectedCandidate().split("#")[0]));
			}
			_fb.setPatCrNo(requisitionVO.getPatCrNo());
			crNoFb.setPatCrNo(_fb.getPatCrNo());
			PatDetailUTIL.getPatientDtlByCrno(crNoFb, _request);
			//get the detail of the candidate's refer if any and raised investigation detail if lab test has been raised
			//and checklist compulsory at post entry level
			essentialMap =PostMedicalEntryDATA.getCandidateDetailForPostEntry(requisitionVO,userVO);
						
			String strLastCertNo=PostMedicalEntryDATA.getLastCertificateNo(requisitionVO.getCertificateTypeID(),userVO);
			_fb.setLastCertNo(strLastCertNo);

			String isDocPresent=PostMedicalEntryDATA.getScanFlag(requisitionVO,userVO);
			System.out.println(isDocPresent+"ndsbfkjdas");
			_fb.setDepartmentUnitCode(requisitionVO.getDepartmentUnitCode());
			_fb.setReqID(requisitionVO.getReqID());
			_fb.setSlNo(requisitionVO.getSlNo());
			_fb.setLastVisitDate(requisitionVO.getVisitDate());
			_fb.setBoardName(getBoardName(requisitionVO.getBoardNo(), _request));
			//_fb.setBoardNo(requisitionVO.getBoardNo());
			_fb.setPatIsDocPresent(isDocPresent);
			//set the lab test status of the candidate if any has been raised
			List <MBInvestigationRequisitionDetailVO> invReqVOList=(List)essentialMap.get(MedicalBoardConfig.INV_REQUISITION_DTL_VO_LIST);
			if(invReqVOList!=null){
				for(int i=0;i<invReqVOList.size();i++){
					setTestStatus(invReqVOList.get(i));
				}
			}
			
			WebUTIL.setMapInSession(essentialMap,_request);
			
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.LIST,"","");	
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Inside Exception");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}	
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("objStatus in finally"+objStatus);		 
			System.out.println("objStatus list"+objStatus.getStatusList());
		}	
		
	}
	
	
	/* *
	 * Save the Post Entry detail
	 * if the medical has been performed then certificate Result and approval detail is saved.
	 * the reqStatus of the candidate requisition Id will be MEDICAL_PERFORMED if the 
	 * certificate is not verified, and reqStatus will be CERTIFICATE_GENERATED if certificate is approved 
	 * in case medical is not performed then the next visit date will be given to the candidate
	 * and the reqStatus of the candidate will be PARTIAL_PERFORMED.the next visit detail will
	 * be entered into req_change_dtl
	 * @param _fb
	 * @param _rq
	 */
	public static void savePostEntryDetail(PostMedicalEntryFB _fb,HttpServletRequest _rq){
		
		Status objStatus=new Status();
		//HttpSession session=WebUTIL.getSession(_rq);
		MedicalBoardRequisitionVO requisitionVO=null;
		List <MedicalBoardRequisitionVO> requisitionVOList=null;
		MedicalBoardRequisitionChangeVO reqChangeVO=null;
		List <MedicalBoardChecklistVO> checklistVOList=new ArrayList<MedicalBoardChecklistVO>();
		MedicalBoardChecklistVO checklistVO=new MedicalBoardChecklistVO();
		try{
			//get the list of the candidate
			requisitionVOList=(List)_rq.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);

			String reqID=_fb.getReqID();
			for(int i=0;i<requisitionVOList.size();i++){
				if(requisitionVOList.get(i).getReqID().equals(reqID)){
					requisitionVO=requisitionVOList.get(i);
				}
			}
			
			//if medical is not performed then save req change detail
			if(_fb.getMedicalPerformed().equals(MedicalBoardConfig.IS_MEDICAL_PERFORMED_NO)){
				reqChangeVO=new MedicalBoardRequisitionChangeVO();
				reqChangeVO.setOldExamDate(requisitionVO.getExamDate());
				reqChangeVO.setNewExamDate(_fb.getExamDate());
				reqChangeVO.setReqMode(MedicalBoardConfig.REQUISITION_CHANGE_REQ_MODE_BY_POST_ENTRY);
				reqChangeVO.setChangeReason(_fb.getReason());
			}
			
			//get the checklist detail
			if(_fb.getSelectedCheckList()!=null){
				for(int i=0;i<_fb.getSelectedCheckList().length;i++){
					checklistVO=new MedicalBoardChecklistVO();
					checklistVO.setReqID(requisitionVO.getReqID());
					String checklistID=_fb.getSelectedCheckList()[i].split("#")[1];
					checklistVO.setChecklistID(checklistID);
					checklistVO.setRemarks(_fb.getRemarks()[i]);
					checklistVOList.add(checklistVO);
				}
			}
			String boardNo=requisitionVO.getBoardNo();
			HelperMethods.populate(requisitionVO,_fb);
			requisitionVO.setBoardNo(boardNo);
			//if the certificate is not verified then set the reqStatus as medical_performed
			requisitionVO.setReqStatus(MedicalBoardConfig.REQUEST_STATUS_MEDICAL_PERFORMED);
			
			if(_fb.getOnlineOfflineFlag().equals(MedicalBoardConfig.OFFLINE))
			{
				if(_fb.getMedicalPerformed().equals(MedicalBoardConfig.IS_MEDICAL_PERFORMED_YES ))
				{
					requisitionVO.setReqStatus(MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_GENERATED);
					_fb.setIsVerified("1");
					requisitionVO.setOpinionCode(_fb.getOpinionCode().split("#")[0]);
					requisitionVO.setFinalRemark(_fb.getFinalRemark());
					requisitionVO.setIsVerified(_fb.getIsVerified());
					requisitionVO.setCertificateResult(_fb.getOpinionCode().split("#")[1]);
				}
				
			}
			else
			{
				if(_fb.getIsVerified().equals("1"))
				{
					requisitionVO.setReqStatus(MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_GENERATED);
					
					requisitionVO.setOpinionCode(_fb.getOpinionCode().split("#")[0]);
					requisitionVO.setFinalRemark(_fb.getFinalRemark());
					requisitionVO.setIsVerified(_fb.getIsVerified());
					requisitionVO.setCertificateResult(_fb.getOpinionCode().split("#")[1]);
				}
				
			}
						
			/*
			//if the certificate is approved then set the reqStatus as certificate generated
			if(_fb.getIsApproved().equals("1")){
				requisitionVO.setReqStatus(MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_GENERATED);
			}
			*/
			if(reqChangeVO!=null){
				HelperMethods.populate(reqChangeVO,requisitionVO);
				requisitionVO.setReqStatus(MedicalBoardConfig.REQUEST_STATUS_PARTIAL_PERFORMED);
			}
			
			MedicalBoardVerificationDtlVO verificationVO=null;
			List verificationVOList=new ArrayList();
			if(_fb.getEmpIdArray()!=null)
			{
				for(int i=0;i<_fb.getEmpIdArray().length;i++)
				{
					if(_fb.getMedicalPerformed().equals(MedicalBoardConfig.IS_MEDICAL_PERFORMED_YES ))
					{
						verificationVO=new MedicalBoardVerificationDtlVO();
						verificationVO.setReqId(_fb.getReqID());
						verificationVO.setCertificateResult(_fb.getOpinionCodeArray()[i].split("#")[1]);
						verificationVO.setOpinionCode(_fb.getOpinionCodeArray()[i].split("#")[0]);
						verificationVO.setOpinion(_fb.getOpinionRemarkArray()[i]);
						verificationVO.setVerifyEmpId(_fb.getEmpIdArray()[i]);
						
						verificationVOList.add(verificationVO);
					}
				}
			}
			
			PostMedicalEntryDATA.savePostEntryDetail(requisitionVO,reqChangeVO,_fb.getMedicalPerformed(),checklistVOList,verificationVOList,getUserVO(_rq));
			objStatus.add(Status.DONE,"","Record Saved Successfully");
			
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}	
	}
	
	
	public static String getCertificateTypeName(String certificateTypeID,HttpServletRequest request){
		String name="";
		List certificateTypeList=(List)request.getSession().getAttribute(MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE);
		for (Iterator iterator = certificateTypeList.iterator(); iterator
				.hasNext();) {
			Entry entry = (Entry) iterator.next();
			if(entry.getValue().equals(certificateTypeID)){
				name=entry.getLabel();
				break;
			}
		}
		return name;
	}
		
	//get board name by board No.
	public static String getBoardName(String boardNo,HttpServletRequest request){
		String name="";
		List boardList=(List)request.getSession().getAttribute(MedicalBoardConfig.MEDICAL_BOARD_LIST);
		for (Iterator iterator = boardList.iterator(); iterator
		.hasNext();) {
			Entry entry = (Entry) iterator.next();
			if(entry.getValue().equals(boardNo)){
				name=entry.getLabel();
				break;
			}
		}
		return name;
	}
	
	public static MBInvestigationRequisitionDetailVO setTestStatus(MBInvestigationRequisitionDetailVO invReqVO){
		int testStatusCode=Integer.parseInt(invReqVO.getTestStatusCode());
		String testStatus[]=InvestigationConfig.testStatus;
		invReqVO.setTestStatus(testStatus[testStatusCode-1]);
		return invReqVO;
	}
	
	
	/**
	 * get the list of the board on basis of certificate type
	 * @param _fb
	 * @param _request
	 */
	public static void getBoardList(PostMedicalEntryFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
//		Map map=new HashMap();
		List <MedicalBoardRequisitionVO> mbBoardList=new ArrayList<MedicalBoardRequisitionVO>();
		try{
			UserVO userVO=getUserVO(_request);
			mbBoardList=PostMedicalEntryDATA.getBoardList(_fb.getCertificateTypeID(),userVO);
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.MEDICAL_BOARD_LIST, mbBoardList);

			//_fb.setBoardName(getBoardName(boardNo, _request));
		 	//WebUTIL.setMapInSession(map, _request); 
			String sysdate=WebUTIL.getCustomisedSysDate((Date)_request.getSession().getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy");
			_fb.setSysDate(sysdate);

	        objStatus.add(Status.NEW);
			
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.LIST,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Inside Exception");
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("objStatus in finally"+objStatus);		 
			System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}
		
}//end class
