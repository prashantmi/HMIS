package registration.transactions.controller.util;

/**
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name						: HMIS NIMS
 ## Name of Developer		 			: s.singaravelan
 ## Module Name							: Registration
 ## Process/Database Object Name		:
 ## Purpose								: Change Patient Primary category
 ## Date of Creation					: 04-Feb-2014

 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.CRNoSUP;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import registration.transactions.controller.actionsupport.PrimaryCategoryChangeSUP;
import registration.transactions.controller.data.NewRegistrationDATA;
import registration.transactions.controller.data.PatientVisitDATA;
import registration.transactions.controller.data.PrimaryCategoryChangeDATA;
import vo.registration.EmpMasterVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.PatCategoryVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientVO;
import vo.registration.PrimaryCategoryChangeVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.VerificationDocumentVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

public class PrimaryCategoryChangeUTIL extends ControllerUTIL{
	
	public static void setPatientDtlByCrno(PrimaryCategoryChangeSUP _fb,HttpServletRequest _request){
		Status  objStatus=new Status();
		PatientVO patientVO=null;
		HttpSession ses=WebUTIL.getSession(_request);
		Map mp= new LinkedHashMap();
		PrimaryCategoryChangeSUP _fbLog=new PrimaryCategoryChangeSUP();

		try{
			
			UserVO userVO =getUserVO(_request);
			
			patientVO=new PatientVO();
			EpisodeVO[] arrOpenEpisodeVO;
			patientVO.setPatCrNo(_fb.getPatCrNo());
			patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			_request.getSession().setAttribute(RegistrationConfig.PATIENT_VO, null);
			
			PatDetailUTIL.getPatientDtlByCrno(_fb, _request);
			patientVO=(PatientVO)_request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
			
			if(patientVO!=null){
			HelperMethods.populate(_fb,patientVO);
			_fb.setPatIdNo(patientVO.getPatIdNo());	
			_fb.setMemSlNo(patientVO.getMemSlNo());	
			
			if(_fb.getPatPrimaryCat().equalsIgnoreCase("DATA NOT FOUND")||_fb.getPatPrimaryCat()==""||_fb.getPatPrimaryCat()==null)
			{
				_fb.setUnavailValidUpto(_fb.getPatPrimaryCatValid());
				_fb.setPatPrimaryCatValid(null);
				//_fb.setPatPrimaryCatCode(patientVO.getOldhosPrimaryCatCode());
			}
			
			//To Check whether the CRNo is merged or not,Added by Singaravelan on 16-Jul-2015 
			if(patientVO.getPatIsMerged()!=null && patientVO.getPatIsMerged().equals("2"))
			{
				_fb.setAfterGo("0");
				throw new HisException("CR No is Not Valid, Already Merged with CR No. " +patientVO.getPatMergedMainCrNO() );
			}
			
			if (patientVO.getPatPrimaryCatValid()!=null)
				_fb.setIsRenewal("1");
			else
				_fb.setIsRenewal("2");
			
			//setAllChangePrimaryCategoryEssentials(_fb,_request);
			getPrimaryCategoryEssentials(_fb, _request);
			getPrimaryCatListExceptPatientCat(_fb, _request);
			getRenewalVerificationDocumet(patientVO.getPatCatCode(), _request);
			
			_fb.setIsDetailAvailable("1");		
			_fb.setPatGuardianName(patientVO.getPatGuardianName());
			_fb.setPatHusbandName(patientVO.getPatHusbandName());
			_fb.setPatMotherName(patientVO.getPatMotherName());
			String primayPatCodeDetails=(String)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_PRIMARY_CATEGORY_WITH_DETAILS);
			String details[]=primayPatCodeDetails.split("#");
			if(details.length>1){
			System.out.println("---details[2]---"+details[2]+"----");
			_fb.setIsIdRequired(details[2]);			
//			if(details[2].equals("2"))
//			{
//				String primayPatCodeIdDetails=(String)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_PRIMARY_CATEGORY_WITH_ID_DETAILS);
//				String idDetails[]=primayPatCodeIdDetails.split("#");
//				System.out.println("---idDetails[0]---"+idDetails[0]+"----");
//				_fb.setPatIdNo(idDetails[0]);
//			}
			}
			String primayPatCodeIdDetails=(String)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_PRIMARY_CATEGORY_WITH_ID_DETAILS);
			String idDetails[]=primayPatCodeIdDetails.split("#");
			if(idDetails.length>1)	
				_fb.setPrevVerificationDocument(idDetails[1]);
			_fb.setRemarks("");			
			
			//Audit Log Initiation
			/*String auditlogProcessId=RegistrationConfig.AUDIT_LOG_CHANGE_PATIENT_CATEGORY;
			_fbLog.setPatCrNo(patientVO.getPatCrNo());
			_fbLog.setPatPrimaryCatCode(patientVO.getPatPrimaryCatCode());
			_fbLog.setVerificationDocument(_fb.getVerificationDocument());
			_fbLog.setPatIdNo(_fb.getPatIdNo());
			mp.put("save_1", _fbLog);
			AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(_request),_request);*/

			RegistrationConfigVO voRegistrationConfig =(RegistrationConfigVO)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			_fb.setRegConfigCatCode(voRegistrationConfig.getSeniorCitizenCatCode());
			_fb.setRegConfigCatAgeLimit(voRegistrationConfig.getSeniorCitizenAgeLimit());
			
			objStatus.add(Status.TRANSINPROCESS,"","");
			}
			else
			{
				_fb.setErrorMessage("Patient Details Not Found");
				_fb.setIsDetailAvailable("0");
				_fb.setPatCrNo((String)_request.getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
			}			
			
						
		}
		catch(HisRecordNotFoundException e){
			//e.printStackTrace();
			System.out.println(e.getMessage());
			if((patientVO!=null) && (patientVO.getPatPrimaryCatCode()==null || patientVO.getPatPrimaryCatCode().equals(""))){
				WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO,patientVO);
				objStatus.add(Status.NEW,"","");
				HelperMethods.populate(_fb,patientVO);
				_fb.setPatIdNo("");
				_fb.setIsDetailAvailable("0");
				_fb.setErrorMessage(e.getMessage());
			}
			else{
				objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			}
		}
		catch(HisDataAccessException e){
			_fb.setIsDetailAvailable("0");
			_fb.setErrorMessage(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisApplicationExecutionException e){
			_fb.setIsDetailAvailable("0");
			_fb.setErrorMessage(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			_fb.setIsDetailAvailable("0");
			_fb.setErrorMessage(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			_fb.setIsDetailAvailable("0");
			_fb.setErrorMessage(e.getMessage());
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		finally{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void getPrimaryCatListExceptPatientCat(PrimaryCategoryChangeSUP _fb, HttpServletRequest _request)
	{
		Status objStatus=new Status();		
		try
		{
			String patCat=_fb.getPatCatCode();
			List patPrimaryCatList= new ArrayList();
			List districtList= new ArrayList();
			List relationList= new ArrayList();			
			List companyList= new ArrayList();
			UserVO userVO=getUserVO(_request);
			HospitalMstVO hospitalVO=getHospitalVO(_request);
			userVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			patPrimaryCatList=PrimaryCategoryChangeDATA.getPrimaryCatListExceptPatientCat(patCat,getUserVO(_request));
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY_LIST_EXCEPT_PATIENT_CATEGORY, patPrimaryCatList);
			districtList=PrimaryCategoryChangeDATA.getDistrictBasedOnState(userVO,hospitalVO.getState() ,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE); 
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ESSENTIALBO_AGS_DISTRICT, districtList);
			relationList=PrimaryCategoryChangeDATA.getRelationsList(userVO);
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ESSENTIALBO_RELATION_LIST, relationList);			
			companyList=PrimaryCategoryChangeDATA.getCompanyList(userVO);
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ESSENTIALBO_COMPANY_LIST, companyList);
			objStatus.add(Status.TRANSINPROCESS,"","");
		}
		catch(HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY_LIST_EXCEPT_PATIENT_CATEGORY, new ArrayList());
			objStatus.add(Status.TRANSINPROCESS,"",e.getMessage());	
			e.printStackTrace();
		}
		catch(HisUpdateUnsuccesfullException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Update Unsuccessful");	
			e.printStackTrace();
		}		
		catch(HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			e.printStackTrace();
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Transaction Failed");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void savePrimaryCategoryChange(PrimaryCategoryChangeSUP _fb, HttpServletRequest _request){
		System.out.println("inside savePrimaryCategoryChange");
		Status objStatus =new Status();	
		VerificationDocumentVO verificationDocVO=new VerificationDocumentVO();
		PatientIdVO objPatientIdVO = new PatientIdVO();
		String strUniqueIdDuplicyFlag="";
		PrimaryCategoryChangeSUP _fbLog=new PrimaryCategoryChangeSUP();
		Map mp= new LinkedHashMap();

		//EmpMasterVO[] empMasterVO=null;
		EmpMasterVO selectedEmpMstVO=null;
		String patCatWithID=_fb.getPatPrimaryCatCode();
		if(!_fb.getIsRenewal().equalsIgnoreCase(RegistrationConfig.RENEWAL_REQUIRED_TRUE))
		{
			_fb.setPatPrimaryCatCode(patCatWithID.split("#")[0]);
			_fb.setIsIdRequired(patCatWithID.split("#")[2]);
			_fb.setIsAlternateId(patCatWithID.split("#")[11]);
		}
		
		HttpSession ses=WebUTIL.getSession(_request);
		try{
			PrimaryCategoryChangeVO primCatChangeVO = new PrimaryCategoryChangeVO();
			PatientVO patientVO = new PatientVO();
			patientVO= (PatientVO)ses.getAttribute(RegistrationConfig.PATIENT_VO);
			_fb.setPatNewPrimaryCatCode(_fb.getPatPrimaryCatCode());
			_fb.setPatPrevPrimaryCatCode(patientVO.getPatPrimaryCatCode());

			HelperMethods.populate(primCatChangeVO, _fb);
			
			primCatChangeVO.setValidUpto(patientVO.getPatPrimaryCatValid());
			primCatChangeVO.setCardNo(_fb.getCardNo());
		/*  ## 		Modification Log							
	 		##		Modify Date				:17thDec'14 
	 		##		Reason	(CR/PRS)		:Patient category not getting changed
	 		##		Modify By				:Sheeldarshi */
			if(_fb.getVerificationDocument()!=null)
			primCatChangeVO.setVerificationDocumentId(_fb.getVerificationDocument().split("#")[0]);

			primCatChangeVO.setPatIdNo(_fb.getPatIdNo());
			primCatChangeVO.setMemSlNo(_fb.getMemSlNo());
			
			if(_fb.getVerificationDocument()!=null && _fb.getVerificationDocument().equals("-1"))
				primCatChangeVO.setVerificationDocumentId("");				
			else				
				primCatChangeVO.setCardNo(_fb.getPatIdNo());
			
			if((_fb.getPatIdNo()!=null && !_fb.getPatIdNo().equals("") && _fb.getVerificationDocument()!=null && !_fb.getVerificationDocument().equals("-1") && !_fb.getIsRenewal().equals("1")))
			{
				objPatientIdVO.setPatIdNo(_fb.getPatIdNo());
				objPatientIdVO.setMemSlNo(_fb.getMemSlNo());
				objPatientIdVO.setPatDocType(_fb.getVerificationDocument().split("#")[0]);
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkUniqueIdDuplicy(getUserVO(_request), objPatientIdVO,"");
				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){	
				//if(_fb.getMemSlNo()!=null && !_fb.getMemSlNo().equals("") && !_fb.getMemSlNo().equals("undefined")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						_fb.setErrorMessage("Duplicate National Id/ Aadhar No");
						return;
					}else if(strUniqueIdDuplicyFlag.equals("2")){
						_fb.setErrorMessage("Patient with this Id No ("+_fb.getPatIdNo()+ ") already registered.");
						return;
					}
				//}
				}
			}
			
			verificationDocVO.setMenuID(RegistrationConfig.PATIENT_CATEGORY_PROCESS_ID);
			verificationDocVO.setPatCrNo(patientVO.getPatCrNo());
			if(_fb.getVerificationDocument()!=null)
			verificationDocVO.setVerificationDocuments(_fb.getVerificationDocument());
			//End:Sheeldarshi
			
			verificationDocVO.setRemarks(_fb.getRemarks());
			
			if(_fb.getIsRenewal().equalsIgnoreCase(RegistrationConfig.RENEWAL_REQUIRED_TRUE))
			{
				primCatChangeVO.setPatNewPrimaryCatCode(_fb.getPatPrevPrimaryCatCode());
			}
			
			/*  ## 		Modification Log							
	 		##		Modify Date				:10thMar'15 
	 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
	 		##		Modify By				:Sheeldarshi 
			 */
			if(!_fb.getIsRenewal().equalsIgnoreCase(RegistrationConfig.RENEWAL_REQUIRED_TRUE))
			{
			if(!patCatWithID.equals("-1") && patCatWithID.split("#")[12].equals("1"))
			{
			primCatChangeVO.setApprovedBy(getUserVO(_request).getUserEmpID());
			primCatChangeVO.setNewCatapprovedBy(patientVO.getApprovedBy());
			}
			primCatChangeVO.setPatPrimaryCatGrp(patCatWithID.split("#")[8]);
			}
			
			//End
			if(PrimaryCategoryChangeDATA.changePrimaryCategory(primCatChangeVO, getUserVO(_request),verificationDocVO,selectedEmpMstVO)){
				
				if(_fb.getIsRenewal().equalsIgnoreCase(RegistrationConfig.RENEWAL_REQUIRED_TRUE))
				  _fb.setStrNormalMsg("Patient Category Renewed Successfully");
				else
					 _fb.setStrNormalMsg("Patient Category Changed Successfully");
				//Audit Log on update	
				
				/*_fbLog.setPatCrNo(patientVO.getPatCrNo());
				_fbLog.setPatPrimaryCatCode(primCatChangeVO.getPatNewPrimaryCatCode());
				_fbLog.setVerificationDocument(primCatChangeVO.getVerificationDocumentId());
				_fbLog.setPatIdNo(_fb.getPatIdNo());
				mp.put("save_1"  , _fbLog);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=_fbLog.getPatCrNo();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(_request),_request,arrKeyVariables);*/
			
			}
			objStatus.add(Status.DONE,"Patient Category Changed for "+primCatChangeVO.getPatCrNo(),"");
			_request.getSession().removeAttribute("keyPatientVO");
				 
	}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.TRANSINPROCESS,"",e.getMessage());
			_fb.setErrorMessage("No Record Found");
			e.printStackTrace();
		}
		catch(HisUpdateUnsuccesfullException e){
			System.out.println("Inside HisUpdateUnsuccesfullException");			
			objStatus.add(Status.UNSUCESSFULL,"","Update Unsuccessful");
			_fb.setErrorMessage("Updates are unsuccessful, Please contact System Administrator");
			e.printStackTrace();
		}		
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			_fb.setErrorMessage("Exceptions occured, Please contact System Administrator");
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			_fb.setErrorMessage("Exceptions occured, Please contact System Administrator");
			e.printStackTrace();
		}
		catch(HisException e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Transaction Failed");
			_fb.setErrorMessage("Exceptions occured, Please contact System Administrator");
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			_fb.setErrorMessage("Exceptions occured, Please contact System Administrator");
			e.printStackTrace();
		}
		finally
		{
		WebUTIL.setStatus(_request,objStatus);
		 }
	}
	
	public static void getVerificationDocumet(PrimaryCategoryChangeSUP _fb, HttpServletRequest _request)
	{
		Status objStatus=new Status();
		List lstVerDoc=new ArrayList();
		
		try
		{
			String idRequired=_fb.getPatPrimaryCatCode().split("#")[2];
			
			if(!_fb.getPatPrimaryCatCode().equals("-1")){
				
				lstVerDoc=PrimaryCategoryChangeDATA.getVerificationDocumetByCatCode(_fb.getPatPrimaryCatCode().split("#")[0],getUserVO(_request)); 
				if(lstVerDoc!=null)
				{
					if(lstVerDoc.size()==1)
					{
						Entry entry = (Entry) lstVerDoc.get(0);
						String code = entry.getValue();
						_fb.setVerificationDocument(code);
					}
				}	
			}	
			WebUTIL.setAttributeInSession(_request, RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS, lstVerDoc);
			if(lstVerDoc!=null)
				objStatus.add(Status.TRANSINPROCESS,"","");
			else
				objStatus.add(Status.TRANSINPROCESS,"","No Verification Document Found");
		
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS,"",e.getMessage());	
			e.printStackTrace();
		}
		catch(HisUpdateUnsuccesfullException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Update Unsuccessful");	
			e.printStackTrace();
		}		
		catch(HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			e.printStackTrace();
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Transaction Failed");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
		
	public static void getRenewalVerificationDocumet(String catCode, HttpServletRequest _request)
	{
		Status objStatus=new Status();
		List lstVerDoc=new ArrayList();
		
		try
		{
			lstVerDoc=PrimaryCategoryChangeDATA.getVerificationDocumetByCatCode(catCode,getUserVO(_request)); 
				
			WebUTIL.setAttributeInSession(_request, RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS, lstVerDoc);
			if(lstVerDoc!=null)
				objStatus.add(Status.TRANSINPROCESS,"","");
			else
				objStatus.add(Status.TRANSINPROCESS,"","No Verification Document Found");
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS,"",e.getMessage());	
			e.printStackTrace();
		}
		catch(HisUpdateUnsuccesfullException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Update Unsuccessful");	
			e.printStackTrace();
		}		
		catch(HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			e.printStackTrace();
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Transaction Failed");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void getPrimaryCategoryEssentials(PrimaryCategoryChangeSUP _fb, HttpServletRequest _request)
	{
		Status objStatus=new Status();
		List lstVerDoc=new ArrayList();
		HttpSession ses=WebUTIL.getSession(_request);
		Map mp=new HashMap();
		try
		{
			
			PatientVO patientVO= (PatientVO)ses.getAttribute(RegistrationConfig.PATIENT_VO);
			String crNo=patientVO.getPatCrNo();
			String patCatCode=patientVO.getPatCatCode();
			mp=PrimaryCategoryChangeDATA.getPrimaryCategoryChangeInitials(crNo, patCatCode, getUserVO(_request));
			WebUTIL.setMapInSession(mp,_request,"PrimaryCategoryChangeACTION");
			
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS,"",e.getMessage());	
			e.printStackTrace();
		}
		catch(HisUpdateUnsuccesfullException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Update Unsuccessful");	
			e.printStackTrace();
		}		
		catch(HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			e.printStackTrace();
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Transaction Failed");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public static void setAllChangePrimaryCategoryEssentials(PrimaryCategoryChangeSUP _fb, HttpServletRequest _request) {	
//		System.out.println("inside setAllChangePrimaryCategoryEssentials()");
//		Map mp=new HashMap();
//		String primaryCatAndExpiry="";
//		List primaryCategory=new ArrayList();
//		try{
//			String crNo=_fb.getPatCrNo();
//			setSysdate(_request);
//			//mp=PrimaryCategoryChangeDATA.getPrimaryCategoryChangeInitials(crNo,getUserVO(_request));
//			
//			//PrimaryCategoryChangeVO[] priCatChangeVO=(PrimaryCategoryChangeVO[])mp.get(RegistrationConfig.ARRAY_PRIMARY_CATEGORY_CHANGE_VOS);
//			
//			//for(int i=0;i<priCatChangeVO.length;i++)
//			//{
//			//	if(priCatChangeVO[i].getPatPrevPrimaryCat()==null)
//			//		priCatChangeVO[i].setPatPrevPrimaryCat("");
//			//	if(priCatChangeVO[i].getPatNewPrimaryCat()==null)
//			//		priCatChangeVO[i].setPatNewPrimaryCat("");
//			//	if(priCatChangeVO[i].getEffectiveFrom()==null)
//			//		priCatChangeVO[i].setEffectiveFrom("");
//			//	if(priCatChangeVO[i].getEffectiveTo()==null)
//			//		priCatChangeVO[i].setEffectiveTo("");
//			//	if(priCatChangeVO[i].getRemarks()==null)
//			//		priCatChangeVO[i].setRemarks("");
//			//}
//			
//			List primaryCatAndExpiryList=(List)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY_WITH_EXPIRY_DAYS);
//			Iterator itr=primaryCatAndExpiryList.iterator();
//			while(itr.hasNext())
//			{
//				Entry entryObj=(Entry)itr.next();
//				primaryCatAndExpiry=primaryCatAndExpiry+entryObj.getValue()+":";
//				entryObj.setValue(entryObj.getValue().substring(0, entryObj.getValue().indexOf("#")));
//				primaryCategory.add(entryObj);
//				
//			}
//			
//			Entry entry=new Entry();
//			entry.setLabel(_fb.getPatPrimaryCat());
//			entry.setValue(_fb.getPatPrimaryCatCode());
//			primaryCategory.remove(entry);
//			
//			_fb.setPrimaryCatAndExpiryDay(primaryCatAndExpiry);
//			
//			
//			mp.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,primaryCategory);
//			
//			WebUTIL.setMapInSession(mp,_request);
//		}
//		catch(HisRecordNotFoundException e){
//			e.printStackTrace();
//			mp=e.getEssentialMap();
//			
//			List primaryCatAndExpiryList=(List)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY_WITH_EXPIRY_DAYS);
//			if(primaryCatAndExpiryList==null)
//			{
//				throw new HisRecordNotFoundException("No Primary Category Assigned To This Seat Id");
//			}
//			Iterator itr=primaryCatAndExpiryList.iterator();
//			while(itr.hasNext())
//			{
//				Entry entryObj=(Entry)itr.next();
//				primaryCatAndExpiry=primaryCatAndExpiry+entryObj.getValue()+":";
//				entryObj.setValue(entryObj.getValue().substring(0, entryObj.getValue().indexOf("#")));
//				primaryCategory.add(entryObj);
//				
//			}
//			
//			Entry entry=new Entry();
//			entry.setLabel(_fb.getPatPrimaryCat());
//			entry.setValue(_fb.getPatPrimaryCatCode());
//			primaryCategory.remove(entry);
//			
//			_fb.setPrimaryCatAndExpiryDay(primaryCatAndExpiry);
//			
//			
//			mp.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,primaryCategory);
//			
//			WebUTIL.setMapInSession(mp,_request);	
//		}		
//			catch(HisDataAccessException e){
//			System.out.println("Inside HisDataAccessException");
//			throw new HisDataAccessException(e.getMessage());		
//		}
//		catch(HisApplicationExecutionException e){		
//			System.out.println("Inside HisApplicationExecutionException");
//			throw new HisApplicationExecutionException(e.getMessage());
//		}
//		catch(HisException e){
//			System.out.println("Inside HisException");
//			throw new HisException(e.getMessage());
//		}
//		finally
//		{
//			WebUTIL.setMapInSession(mp,_request);	
//		}
//		
//		
//	}

	public static void getcatLogDtl(PrimaryCategoryChangeSUP objPatcatlogSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		PrimaryCategoryChangeVO primCatChangeVO = new PrimaryCategoryChangeVO();
		Status objStatus=new Status();
		HttpSession ses=WebUTIL.getSession(objRequest_p);
		try
		{  
			PatientVO patientVO= (PatientVO)ses.getAttribute(RegistrationConfig.PATIENT_VO);
			String crNo=patientVO.getPatCrNo();
			System.out.println("PrimaryCategoryChangeUTIL :: getcatLogDtl()");
			PrimaryCategoryChangeVO[] arrEpisodepatcatlogVO=PrimaryCategoryChangeDATA.getpatcatchangeLog(crNo,objUserVO,primCatChangeVO );
			for(int i=0;i<arrEpisodepatcatlogVO.length;i++)
				arrEpisodepatcatlogVO[i].setPatCrNo(crNo);
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_PAT_CAT_CHANGE_LOG ,arrEpisodepatcatlogVO);
			System.out.println("arrEpisodepatcatlogVO.length :"+arrEpisodepatcatlogVO.length);
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			//objStatus.add(Status.ERROR,"",e.getMessage());
			objPatcatlogSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			//objStatus.add(Status.ERROR_DA,"","Record Not Found");	
			objPatcatlogSUP_p.setErrorMessage("Record Not Found");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p,objStatus);
		}
	}	
	
	//By Mukund for Audit Log
	public static void getauditLogDtl(PrimaryCategoryChangeSUP objPatcatlogSUP_p,HttpServletRequest objRequest_p)
	{
		UserVO objUserVO =getUserVO(objRequest_p);
		PrimaryCategoryChangeVO primCatChangeVO = new PrimaryCategoryChangeVO();
		Status objStatus=new Status();
		HttpSession ses=WebUTIL.getSession(objRequest_p);
		Map<String, Object> mpMaps=new HashMap<String, Object>();
		try
		{  
			PatientVO patientVO= (PatientVO)ses.getAttribute(RegistrationConfig.PATIENT_VO);
			String crNo=patientVO.getPatCrNo();
			System.out.println("PrimaryCategoryChangeUTIL :: getcatLogDtl()");
			List alrecordList=PrimaryCategoryChangeDATA.getauditLog(crNo,objUserVO,primCatChangeVO );
			
			for(int i=0;i<alrecordList.size();i++)
			{
			//By Mukund on 26.09.2016	
			Map<String, String> mapAudit= arraytoMap2(alrecordList,i);
			//Map<String, String> mapAudit= arraytoMap(alrecordList,i);
			mpMaps.put("map".concat(Integer.toString(i)),mapAudit);
			
			}
			objRequest_p.getSession().setAttribute("multiRowMap", mpMaps);
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			objPatcatlogSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();	
			objPatcatlogSUP_p.setErrorMessage("Record Not Found");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p,objStatus);
		}
	}
	
	public static Map<String, String> arraytoMap(List s,Integer i) {
		Map<String, String> m = new HashMap<String, String>();
	
		String[] tokens = s.get(i).toString().replace("|", "#").split("#")[1].split(", ");
		String[] newtokens= s.get(i).toString().replace("|", "#").split("#")[0].split(", ");
		String modifiedTime = s.get(i).toString().replace("|", "#").split("#")[2];
		m.put("ModifiedTime", modifiedTime);
		for (String token : tokens) 
		{
			String[] kv = token.split("=>");
			String k = kv[0];
			k = k.trim().substring(1, k.length()-1);
			for (String newtoken : newtokens) 
			{
				String[] newkv = newtoken.split("=>");
				String newk = newkv[0];
				newk = newk.trim().substring(1, newk.length()-1);
					if(newk.equals(k))
					{
						String v = kv[1];
						String newv = newkv[1];
						
						if(v.equals("NULL"))
						{
							v="NA".concat("#");
						}
						else
							v = v.trim().substring(1, v.length()-1).concat("#");
						
						if(newv.equals("NULL"))
							v=v.concat("NA");	
						else
							v=v.concat(newv.trim().substring(1, newv.length()-1));	
						
						//v=v.concat("#").concat(modifiedTime);
						m.put(k, v);
						
					}
			}
		}
		
		return m;
	}
	
	//By Mukund on 26.09.2016
	public static Map<String, String> arraytoMap2(List s,Integer i) {
		Map<String, String> m = new HashMap<String, String>();
		
		String	entity2="", key="";
		String[] tokens = s.get(i).toString().replace("|", "#").split("#")[1].split(", ");//rowData2
		String[] newtokens= s.get(i).toString().replace("|", "#").split("#")[0].split(", ");//changedData1
		String modifiedTime = s.get(i).toString().replace("|", "#").split("#")[2];//3
		
		for (String token : tokens)//allData
		{
			String[] kv = token.split("=>");
		
			String k = kv[0];
			k = k.trim().substring(1, k.length()-1);
			if(k.equals("gnum_seat_id"))
				key=kv[1];//the value of seatId is assigned to key
			for (String newtoken : newtokens)//changedData
			{
				String[] newkv = newtoken.split("=>");
				String newk = newkv[0];System.out.println("newk="+newk+" && "+newk.length());
				if(newk!=null && !newk.equals(""))
				newk = newk.trim().substring(1, newk.length()-1);
					if(newk.equals(k))
					{
						String v = kv[1];//kv[1] is the previous value
						String newv = newkv[1];//newkv[1] is the current value
						if(k.equals("gnum_seat_id"))
							key=newkv[1];//the value of seatId is assigned to key
						if(v.equals("NULL"))
						{
							v="NA".concat("#");
						}
						else
							v = v.trim().substring(1, v.length()-1).concat("#");
						                                                            
						if(newv.equals("NULL"))
							v=v.concat("NA");	
						else
							v=v.concat(newv.trim().substring(1, newv.length()-1));	
						
						String entity = k.concat("#").concat(v).concat("@");//after this statement the value of entity be like columnName#prev#current
							
						entity2= entity.concat(entity2);
					}
			}
		}
		key=modifiedTime.concat("#").concat(key);
		m.put(key, entity2);
		return m;
	}//End: Mukund
	
}
