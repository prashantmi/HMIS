package opd.transaction.controller.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.transaction.controller.data.OpdChangeTreatmentCategoryDATA;
import opd.transaction.controller.fb.OpdChangeTreatmentCategoryFB;

import registration.RegistrationConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.UserVO;

public class OpdChangeTreatmentCategoryUTIL extends ControllerUTIL {
	
	public static void setAllChangeSecondaryCategoryEssentials(OpdChangeTreatmentCategoryFB _fb, HttpServletRequest _request){	
		
		Status objStatus=new Status();
		List secondaryCategory=new ArrayList();
		//String[] catAndExpiryDayArray;
		String secCatAndExpiry="";
		try{
			
			setSysdate(_request);
			
			Map mp=OpdChangeTreatmentCategoryDATA.getSecondaryCategoryChangeInitials(getUserVO(_request));
			
			List secCatAndExpiryList=(List)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY_WITH_EXPIRY_DAYS);
			
			Iterator itr=secCatAndExpiryList.iterator();
			//catAndExpiryDayArray=new String[secCatAndExpiryList.size()];
			while(itr.hasNext())
			{
				
				Entry entryObj=(Entry)itr.next();
				secCatAndExpiry=secCatAndExpiry+entryObj.getValue()+":";
				entryObj.setValue(entryObj.getValue().substring(0, entryObj.getValue().indexOf("#")));
				secondaryCategory.add(entryObj);
			}
			
			
			_fb.setSecCatCodeAndExpiryDay(secCatAndExpiry);
			
			mp.put(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY, secondaryCategory);
			mp.put(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY_WITH_EXPIRY_DAYS, secCatAndExpiry);
			
			
			WebUTIL.setMapInSession(mp,_request);
			
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "No Records for Treatment Category found. Either there are No Records in Database or No Records against your Seat Id exist", "");
		}		
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
	public static void setPatientDtlByCrno( OpdChangeTreatmentCategoryFB _fb,HttpServletRequest _request){
		Status  objStatus=new Status();
		EpisodeVO[] arrOpenEpisodeVO=null;
		HttpSession ses=WebUTIL.getSession(_request);
		
		try
		{
			String deskType = (String) ses.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK) || deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
				_fb.setIsIpdFlag(RegistrationConfig.YES);
			else
				_fb.setIsIpdFlag(RegistrationConfig.NO);
			
			UserVO userVO =getUserVO(_request);
			setAllChangeSecondaryCategoryEssentials(_fb,_request);
			PatientVO patientVO=new PatientVO();
			patientVO.setPatCrNo(_fb.getPatCrNo());
			patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			patientVO=OpdChangeTreatmentCategoryDATA.getPatientDtl(patientVO, userVO);
			HelperMethods.populate(_fb,patientVO);			
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO,patientVO);
			patientVO=(PatientVO)ses.getAttribute(RegistrationConfig.PATIENT_VO);
			String crNo=patientVO.getPatCrNo();
			
			if(_fb.getIsIpdFlag().equals(RegistrationConfig.YES))
				arrOpenEpisodeVO=OpdChangeTreatmentCategoryDATA.getPatientAdmittedEpisodes(crNo,userVO);
			else
				arrOpenEpisodeVO=OpdChangeTreatmentCategoryDATA.getAllOpenEpisodes(crNo, userVO);
			
			for(int i=0;i<arrOpenEpisodeVO.length;i++){
				
				if(arrOpenEpisodeVO[i].getPatSecondaryCatCode()==null)
					arrOpenEpisodeVO[i].setPatSecondaryCatCode("-1");
			}
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ARR_OPEN_EPISODE_VO,arrOpenEpisodeVO);
			
			
			
			
			Collection existingSequnece=new LinkedList();
			
			for(int i=0;i<arrOpenEpisodeVO.length;i++){
				OpdChangeTreatmentCategoryUTIL.ChangeSecondaryCategory innerClassSequence= new OpdChangeTreatmentCategoryUTIL().new ChangeSecondaryCategory(arrOpenEpisodeVO[i].getPatSecondaryCatCode(),arrOpenEpisodeVO[i].getEpisodeVisitNo(),arrOpenEpisodeVO[i].getEpisodeCode(), arrOpenEpisodeVO[i].getDepartment(), arrOpenEpisodeVO[i].getDepartmentCode(), arrOpenEpisodeVO[i].getPatSecondaryCatCode(), arrOpenEpisodeVO[i].getPatSecondaryCat(), arrOpenEpisodeVO[i].getRemarks(), arrOpenEpisodeVO[i].getDepartmentUnitCode(), arrOpenEpisodeVO[i].getDepartmentUnit(), arrOpenEpisodeVO[i].getValidUpto());
				existingSequnece.add(innerClassSequence);		
			}
			WebUTIL.setAttributeInSession(_request, RegistrationConfig.COLL_OPEN_EPISODE_VO, existingSequnece);
			
			objStatus.add(Status.TRANSINPROCESS,"","");
		}
		catch(HisRecordNotFoundException e){
			
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
		}
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisApplicationExecutionException e){
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		finally{
			WebUTIL.setStatus(_request,objStatus);			
			System.out.println("objStatus in finally"+objStatus);
			System.out.println("objStatus list"+objStatus.getStatusList());
		}
	}
	
	public static void saveSecondaryCategoryChange(OpdChangeTreatmentCategoryFB _fb, HttpServletRequest _request){
		
		Status objStatus =new Status();	
		String depts="";
		HttpSession ses=WebUTIL.getSession(_request);
		SecondaryCategoryChangeVO[] secCatRevokeVO =null;
		SecondaryCategoryChangeVO[] secCatChangeVO =null;
		try{
			PatientVO patientVO = new PatientVO();
			patientVO= (PatientVO)ses.getAttribute(RegistrationConfig.PATIENT_VO);
			EpisodeVO[] arrOpenEpisodeVO = (EpisodeVO[])ses.getAttribute(RegistrationConfig.ARR_OPEN_EPISODE_VO);
			//String[] selectedEpisodes = _fb.getSelectEpisode();	
			String[] newSecCatselectedEpisodes = _fb.getHiddenNewSecCatCode();
			String[] remarks=_fb.getArrRemarks();
			String[] validUpto=_fb.getArrValidUpto(); 
			String[] expiryDate=_fb.getCategoryExpiryDate();
			
			//SecondaryCategoryChangeVO[] secCatChangeVO = new SecondaryCategoryChangeVO[_fb.getSelectEpisode().length];
			if(_fb.getSelectEpisode().length!=0)
			{
				secCatChangeVO = new SecondaryCategoryChangeVO[_fb.getSelectEpisode().length];
				for(int i=0,k=0,l=0,m=0;i<secCatChangeVO.length;i++,k++,m++,l++)
				{
					int x=Integer.parseInt(_fb.getSelectEpisode()[i]);
					secCatChangeVO[i]=new SecondaryCategoryChangeVO();
					HelperMethods.populate(secCatChangeVO[i], patientVO);
					secCatChangeVO[i].setEpisodeCode(arrOpenEpisodeVO[x].getEpisodeCode());
					secCatChangeVO[i].setEpisodeVisitNo(arrOpenEpisodeVO[x].getEpisodeVisitNo());
					secCatChangeVO[i].setPatNewSecondaryCatCode(newSecCatselectedEpisodes[l]);
					secCatChangeVO[i].setRemarks(remarks[x]);
					secCatChangeVO[i].setValidUpto(validUpto[m]);
					secCatChangeVO[i].setExpiryDate(expiryDate[m]);
					secCatChangeVO[i].setIsIpdFlag(_fb.getIsIpdFlag());
				}
					
					
				for(int j=0;j<secCatChangeVO.length;j++){
					for(int i=0; i<arrOpenEpisodeVO.length; i++){
						String str1=secCatChangeVO[j].getEpisodeCode();
						String str2=arrOpenEpisodeVO[i].getEpisodeCode();
						
					
						if(str1.trim().compareTo(str2.trim())==0){
							secCatChangeVO[j].setPatPrevSecondaryCatCode(arrOpenEpisodeVO[i].getPatSecondaryCatCode());
							depts=depts+"	"+arrOpenEpisodeVO[i].getDepartment();
						}
						
					 }
				}
				
				for(int j=0;j<secCatChangeVO.length;j++)
				{
					if(secCatChangeVO[j].getPatPrevSecondaryCatCode().equals("-1"))
					{
						secCatChangeVO[j].setPatPrevSecondaryCatCode("");
					}
				}
			}	
			
			///////////REVOKE
			if(_fb.getRevokeChk()!=null)
			{
				secCatRevokeVO = new SecondaryCategoryChangeVO[_fb.getRevokeChk().length];
				for(int i=0;i<_fb.getRevokeChk().length;i++)
				{
					int y=Integer.parseInt(_fb.getRevokeChk()[i]);
					secCatRevokeVO[i]=new SecondaryCategoryChangeVO();
					HelperMethods.populate(secCatRevokeVO[i], patientVO);
					secCatRevokeVO[i].setPatCrNo(_fb.getPatCrNo());
					secCatRevokeVO[i].setEpisodeCode(arrOpenEpisodeVO[y].getEpisodeCode());
					secCatRevokeVO[i].setEpisodeVisitNo(arrOpenEpisodeVO[y].getEpisodeVisitNo());
					secCatRevokeVO[i].setIsIpdFlag(_fb.getIsIpdFlag());
					secCatRevokeVO[i].setRemarks(remarks[y]);
					secCatRevokeVO[i].setPatNewSecondaryCatCode("");
				}
				
				for(int j=0;j<secCatRevokeVO.length;j++)
				{
					for(int i=0; i<arrOpenEpisodeVO.length; i++)
					{
						String str1=secCatRevokeVO[j].getEpisodeCode();
						String str2=arrOpenEpisodeVO[i].getEpisodeCode();
				
						if(str1.trim().compareTo(str2.trim())==0)
						{
							secCatRevokeVO[j].setPatPrevSecondaryCatCode(arrOpenEpisodeVO[i].getPatSecondaryCatCode());
							depts=depts+"	"+arrOpenEpisodeVO[i].getDepartment();
						}	
					}
				}
				
				for(int j=0;j<secCatRevokeVO.length;j++)
				{
					if(secCatRevokeVO[j].getPatPrevSecondaryCatCode().equals("-1"))
					{
						secCatRevokeVO[j].setPatPrevSecondaryCatCode("");
					}
				}
			}	
			////////////
			
			OpdChangeTreatmentCategoryDATA.changeSecondaryCategory(secCatChangeVO,secCatRevokeVO, getUserVO(_request));
			//objStatus.add(Status.DONE,"Treatment Category Changed for "+secCatChangeVO[0].getPatCrNo()+" for the Departments: "+depts,"");
			objStatus.add(Status.DONE,"Treatment Category Changed","");
		}
		catch(HisUpdateUnsuccesfullException e){
			System.out.println("Inside HisUpdateUnsuccesfullException");			
			objStatus.add(Status.UNSUCESSFULL,"","Update Failed");	
		}		
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e){
			e.printStackTrace();
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("objStatus in finally"+objStatus);
			System.out.println("objStatus list"+objStatus.getStatusList());
		}
	}	
	
	
	public class ChangeSecondaryCategory implements Serializable{
		private String episodeCode;
		private String episodeVisitNo;
		private String department;
		private String departmentCode;
		//private String patSecondaryCatCode;
	    //private String patSecondaryCat;  
	    private String arrRemarks;
	    private String newSecCatCode;
	    private String departmentUnitCode;
	    private String departmentUnit;
	    private String validUpto;
	    private String hiddenNewSecCatCode;
	    
	    ChangeSecondaryCategory(String hiddenNewSecCatCode,String episodeVisitNo,String episodeCode, String department, String departmentCode, String newSecCatCode, String patSecondaryCat, String remarks, String departmentUnitCode,  String departmentUnit,String validUpto){
	    	
	    	this.episodeCode=episodeCode;
	    	this.department=department;
	    	this.departmentCode=departmentCode;
	    	this.newSecCatCode=newSecCatCode;
	    	//this.patSecondaryCat=patSecondaryCat;  
	    	this.arrRemarks=remarks;
	    	this.departmentUnit=departmentUnit;
	    	this.departmentUnitCode=departmentUnitCode;
	    	this.validUpto=validUpto;
	    	this.episodeVisitNo=episodeVisitNo;
	    	this.hiddenNewSecCatCode=hiddenNewSecCatCode;
	    }

	    
	    
		public String getDepartmentUnit() {
			return departmentUnit;
		}



		public void setDepartmentUnit(String departmentUnit) {
			this.departmentUnit = departmentUnit;
		}



		public String getDepartmentUnitCode() {
			return departmentUnitCode;
		}



		public void setDepartmentUnitCode(String departmentUnitCode) {
			this.departmentUnitCode = departmentUnitCode;
		}



		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getDepartmentCode() {
			return departmentCode;
		}

		public void setDepartmentCode(String departmentCode) {
			this.departmentCode = departmentCode;
		}

		public String getEpisodeCode() {
			return episodeCode;
		}

		public void setEpisodeCode(String episodeCode) {
			this.episodeCode = episodeCode;
		}

		/*public String getPatSecondaryCat() {
			return patSecondaryCat;
		}

		public void setPatSecondaryCat(String patSecondaryCat) {
			this.patSecondaryCat = patSecondaryCat;
		}*/

		public String getNewSecCatCode() {
			return newSecCatCode;
		}

		public void setNewSecCatCode(String newSecCatCode) {
			this.newSecCatCode = newSecCatCode;
		}

		public String getArrRemarks() {
			return arrRemarks;
		}

		public void setArrRemarks(String arrRemarks) {
			this.arrRemarks = arrRemarks;
		}



		public String getValidUpto() {
			return validUpto;
		}



		public void setValidUpto(String validUpto) {
			this.validUpto = validUpto;
		}



		public String getEpisodeVisitNo() {
			return episodeVisitNo;
		}



		public void setEpisodeVisitNo(String episodeVisitNo) {
			this.episodeVisitNo = episodeVisitNo;
		}



		public String getHiddenNewSecCatCode() {
			return hiddenNewSecCatCode;
		}



		public void setHiddenNewSecCatCode(String hiddenNewSecCatCode) {
			this.hiddenNewSecCatCode = hiddenNewSecCatCode;
		}
	    
	    
		
	    
	    
		
	}


}
