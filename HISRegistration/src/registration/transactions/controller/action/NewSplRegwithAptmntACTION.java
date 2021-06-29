package registration.transactions.controller.action;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.presentation.CSRFGardTokenAction;//By Mukund
import hisglobal.vo.HospitalMstVO;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import registration.config.RegistrationConfig;
import registration.transactions.controller.fb.newSplRegFB;
import registration.transactions.controller.util.NewSplRegUTIL;
import vo.registration.RegistrationConfigVO;

//By Mukund
//public class NewSplRegwithAptmntACTION  extends DispatchAction
public class NewSplRegwithAptmntACTION  extends CSRFGardTokenAction
{
	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action  "NEW"
	 */
	
	boolean isAptBased=false;
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		
		HttpSession ses=request.getSession();
		RegistrationConfigVO objRegConfigVO=null;
		
		Map mp=NewSplRegUTIL.splRegConfigEssentials(request);
		
		if(mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG)!=null){	
			objRegConfigVO=(RegistrationConfigVO) mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			if(objRegConfigVO.getIsAptBased()!=null&&objRegConfigVO.getIsAptBased().equalsIgnoreCase("1")){
				isAptBased=true;
				return this.NEW(mapping,form,request,response);		
			}
			else{
				isAptBased=false;
				return this.NEWREGWOAPT(mapping,form,request,response);
			}
		}
		else	
			return this.NEW(mapping,form,request,response);	
	}
	
	/**
	 * the default action called when a page is loaded for the first time to fetch the list of patients who has taken the appointment
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action  "NEW"
	 */
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//By Mukund for token generation
		generateToken(request);
		String token=request.getSession().getAttribute("org.apache.struts.action.TOKEN").toString();
		System.out.println("Token Generated at NEWSplRegWithApmtACTION: "+token);
		//End:Mukund
		Status objStatus=new Status();
		
		newSplRegFB newSplRegFBObj = (newSplRegFB) form;
		newSplRegFBObj.reset(mapping,request);	
		HttpSession ses=request.getSession();
		if(((String)ses.getAttribute(RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE))==null)
		{
			WebUTIL.refreshTransState(request,"NewSplRegwithAptmntACTION");
		}	
		WebUTIL.setAttributeInSession(request,RegistrationConfig.ALL_PATIENT_VO_LIST, "");
		WebUTIL.setAttributeInSession(request,RegistrationConfig.PATIENT_VO_LIST, "");
	 	NewSplRegUTIL.setAllSpecialCRNoWithAppointment(request);	 	
	 	ses.setAttribute("optionNewDeptVisitDepartment",new ArrayList());	 
	 	WebUTIL.setStatus(request,objStatus); 	
	 		
	 	return mapping.findForward("NEW");
	}
  
	public ActionForward NEWREGWAPT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		Status objStatus=new Status();
		
		newSplRegFB newSplRegFBObj = (newSplRegFB) form;
		String aptNoToRegister=newSplRegFBObj.getPatAptNoToRegister()!=null?newSplRegFBObj.getPatAptNoToRegister():newSplRegFBObj.getPatAptNo();
		newSplRegFBObj.reset(mapping,request);	
		HttpSession _httpses=request.getSession();
		HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);

		newSplRegFBObj.setPatAptNo(aptNoToRegister);
		System.out.println("-----------"+_httpses.getAttribute(RegistrationConfig.ALL_PATIENT_VO_LIST)+"--------");
		System.out.println("crNoToModify.............."+aptNoToRegister);
		if(_httpses.getAttribute(RegistrationConfig.ALL_PATIENT_VO_LIST)!=null){
			newSplRegFBObj=NewSplRegUTIL.setVoEssentials(newSplRegFBObj,_httpses,newSplRegFBObj.getPatAptNoToRegister());
		}
		else
		{
			newSplRegFBObj=NewSplRegUTIL.setVoEssentialsOnAptNo(newSplRegFBObj,request,"2");
		}		
		
		newSplRegFBObj.setPatAddPIN(hospitalVO.getPinCode());
		WebUTIL.refreshTransState(request,"NewSplRegwithAptmntACTION");
		
	 	NewSplRegUTIL.setAllNewRegistrationEssentials(newSplRegFBObj,request,isAptBased);
	 	
	 	boolean ff=request.getSession().isNew();
 	
	 	_httpses.setAttribute("optionNewDeptVisitDepartment",new ArrayList());	 	
	 	
	 	WebUTIL.setStatus(request,objStatus);
	 	
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward NEWREGWOAPT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		Status objStatus=new Status();
		
		newSplRegFB newSplRegFBObj = (newSplRegFB) form;
		newSplRegFBObj.reset(mapping,request);	
		HttpSession _httpses=request.getSession();
		HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);			
		
		newSplRegFBObj.setPatAddPIN(hospitalVO.getPinCode());
		WebUTIL.refreshTransState(request,"NewSplRegwithAptmntACTION");
		
	 	NewSplRegUTIL.setAllNewRegistrationEssentials(newSplRegFBObj,request,isAptBased);
	 	_httpses.setAttribute("optionNewDeptVisitDepartment",new ArrayList());	 	
	 	
	 	WebUTIL.setStatus(request,objStatus);
	 	
	 	return mapping.findForward("NEW");
	}
	
	/**
	 * saves New Registration Details
	
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls the action "NEW"
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
		//By Mukund on 01.12.2016 for token validation
		try{	
			validateToken(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Token Error: "+e);
		}//End: Mukund
	    newSplRegFB fb = (newSplRegFB) form;
	    ServletContext context=servlet.getServletContext();
		HttpSession _httpses=request.getSession();

	    boolean exists=false;
	    int flag=0;	   
	    
	    //CharacterEncoding.setCharacterEncoding(request);
	    //SplRegistrationAction _splTemp=new SplRegistrationAction();
	    //_splTemp.populate(fb);	
	    
	    
	    if(fb.getPatPrimaryCatCode().indexOf("#")>0){
	    	request.getSession().setAttribute("patCatCodeString", fb.getPatPrimaryCatCode());
	    	String[] patPrimaryCategory=fb.getPatPrimaryCatCode().split("#");
	    	fb.setPatPrimaryCatCode(patPrimaryCategory[0]);
	    	fb.setPatPrimaryCat(patPrimaryCategory[1]);	    
	    	fb.setPatPrimaryCatGrp(patPrimaryCategory[9]);
	    	/* #Start					:Sheeldarshi 
	    	#Modify Date(CR/PRS)	:15thJuly'15 
	    	#Reason					:Change for adding Organisation combo on All Registration Pages
	    	 */
	    	//fb.setClientName(patPrimaryCategory[14]);
	    	//fb.setClientCode(patPrimaryCategory[13]);
	    	//End
	    	fb.setPatCatDocIsAlternateId(patPrimaryCategory[12]);
	    }
	    
	    if(fb.getDepartmentCode().indexOf("#")>0){
	    	String[] deptUnit=fb.getDepartmentCode().split("#");
	    	if(deptUnit.length>2){
		    	fb.setDepartmentCode(deptUnit[0]);
		    	fb.setDepartmentUnitCode(deptUnit[1]);
		    	fb.setRoomCode(deptUnit[2]);//order might be changed from time to time modification in the dept combo
	    	}
	    }
	    if(fb.getPatDocType().indexOf("#")>0){
	    	String patDocType=fb.getPatDocType().split("#")[0];
	    	fb.setPatDocType(patDocType);
	    }
	    fb.setPatNationalityCode("1");
	    
	   // CharacterEncoding.setCharacterEncoding(request);
	    fb.setPatFirstNameInMultiLang(new String(fb.getPatFirstNameInMultiLang().getBytes("ISO-8859-1"), "UTF-8"));
	    fb.setPatMiddleNameInMultiLang(new String(fb.getPatMiddleNameInMultiLang().getBytes("ISO-8859-1"), "UTF-8"));
		fb.setPatLastNameInMultiLang(new String(fb.getPatLastNameInMultiLang().getBytes("ISO-8859-1"), "UTF-8"));
		 fb.setAsNewPatient("0");
	    flag=NewSplRegUTIL.saveNewPatientRegistration(request, fb,context);	
	    //SplRegistrationUTIL.saveSplPatientRegistration(_splTemp,request,response);
	   
	   // NewSplRegUTIL.setAllNewRegistrationEssentials(fb,request);
	    if(flag==2){
	    	//fb.reset(mapping, request);
			NewSplRegUTIL.setAllNewRegistrationEssentialsForDuplicate(fb,request);
			
			if(fb.getIsDuplicatePatientPopup().equalsIgnoreCase("1")&&_httpses.getAttribute("patCatCodeString")!=null)
				fb.setPatPrimaryCatCode(_httpses.getAttribute("patCatCodeString").toString());
		 	
			return mapping.findForward("NEW");
			
	    }
	    else if(flag==1){
	    	System.out.println("Duplicate");
	    	return this.ADHAARNEW(mapping,form,request,response);
	    }
		else{
			 //fb.reset(mapping, request);
			 //NewSplRegUTIL.setDeptOptions(request, fb);	
			
			if(!isAptBased)
				return this.NEWREGWOAPT(mapping, form, request, response);
			else
			 return this.NEW(mapping, form, request, response);
		}	
	   
	}	
	//Start:Sheeldarshi:20thOct'14:Duplicacy check
	 	public ActionForward SAVEASNEWPATIENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, ParseException{
	 		//By Mukund on 01.12.2016 for token validation
	 		try{	
				validateToken(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Token Error: "+e);
			}//End:Mukund
	 		  newSplRegFB fb = (newSplRegFB) form;
	 		 
	 		    ServletContext context=servlet.getServletContext();
	 		boolean exists=false;
	 	    int flag=0;
	 	    
	 	  
	 	    
	 	  //  CharacterEncoding.setCharacterEncoding(request);
	 	//	SplRegistrationAction _splTemp=new SplRegistrationAction();
	 	//	_splTemp.populate(fb);	
	 	    
	 	    if(fb.getPatPrimaryCatCode().indexOf("#")>0){
	 	    	String[] patPrimaryCategory=fb.getPatPrimaryCatCode().split("#");
	 	    	fb.setPatPrimaryCatCode(patPrimaryCategory[0]);
	 	    	fb.setPatPrimaryCat(patPrimaryCategory[1]);	    
	 	    	fb.setPatPrimaryCatGrp(patPrimaryCategory[9]);
	 	    	fb.setClientName(patPrimaryCategory[14]);
	 	    	fb.setClientCode(patPrimaryCategory[13]);
	 	    }
	 	    
	 	  /* if(fb.getDepartmentCode().indexOf("#")>0){
	 	    	String deptUnit=fb.getDepartmentCode().split("#")[1];
	 	    	fb.setDepartmentCode(deptUnit.substring(0, 3));
	 	    	fb.setDepartmentUnitCode(deptUnit);
	 	    }*/
	 	    
	 	   if(fb.getDepartmentCode().indexOf("#")>0){
		    	String[] deptUnit=fb.getDepartmentCode().split("#");
		    	if(deptUnit.length>2){
			    	fb.setDepartmentCode(deptUnit[0]);
			    	fb.setDepartmentUnitCode(deptUnit[1]);
			    	fb.setRoomCode(deptUnit[2]);//order might be changed from time to time modification in the dept combo
		    	}
	 	   }
		    	
	 	    if(fb.getPatDocType().indexOf("#")>0){
	 	    	String patDocType=fb.getPatDocType().split("#")[0];
	 	    	fb.setPatDocType(patDocType);
	 	    }
	 	    fb.setPatNationalityCode("1");
	 	    fb.setIsDuplicatePatientPopup("0");
			fb.setIsDuplicate("1");
	 	   // CharacterEncoding.setCharacterEncoding(request);
	 	    fb.setPatFirstNameInMultiLang(new String(fb.getPatFirstNameInMultiLang().getBytes("ISO-8859-1"), "UTF-8"));
	 	    fb.setPatMiddleNameInMultiLang(new String(fb.getPatMiddleNameInMultiLang().getBytes("ISO-8859-1"), "UTF-8"));
	 		fb.setPatLastNameInMultiLang(new String(fb.getPatLastNameInMultiLang().getBytes("ISO-8859-1"), "UTF-8"));
	 		 fb.setAsNewPatient("1");
	 	    flag=NewSplRegUTIL.saveNewPatientRegistration(request, fb,context);	
	 	    //SplRegistrationUTIL.saveSplPatientRegistration(_splTemp,request,response);
	 	   
	 	    if(flag==2){
	 	    	fb.reset(mapping, request);
	 			NewSplRegUTIL.setAllNewRegistrationEssentialsForDuplicate(fb,request);
	 			return mapping.findForward("NEW");
	 			
	 	    }
	 	    else if(flag==1){
	 	    	System.out.println("Duplicate");
	 	    	return this.ADHAARNEW(mapping,form,request,response);
	 	    }
	 		else{
	 			 //fb.reset(mapping, request);
	 			 //NewSplRegUTIL.setDeptOptions(request, fb);	
	 			
	 			if(!isAptBased)
					return this.NEWREGWOAPT(mapping, form, request, response);
				else
					return this.NEW(mapping, form, request, response);
	 		}	
		 }
	 		//End:Sheeldarshi:20thOct'14:Duplicacy check
	
	public ActionForward ADHAARNEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		Status objStatus=new Status();
		newSplRegFB fb = (newSplRegFB) form;
		HttpSession ses=request.getSession();
		/*if(((String)ses.getAttribute(RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION))==null)
		{
		WebUTIL.refreshTransState(request,"NewSplRegwithAptmntACTION");
		}*/
		//RegDskNewRegistrationUTILDuplicate.setAllNewRegistrationEssentials(RegDskNewRegistrationFBobj,request);
		NewSplRegUTIL.setAllNewRegistrationEssentials(fb,request,isAptBased);
	 	boolean ff=request.getSession().isNew();
	 	ses.setAttribute("optionNewDeptVisitDepartment",new ArrayList());	 	
	 		 	
	 	
	 	WebUTIL.setStatus(request,objStatus);
	 	fb.setPatGenderCode(fb.getPatGenderCode());
	 	fb.setDepartmentCode(fb.getDepartmentCode());

	 	return mapping.findForward("NEW");
	}
	
	/**
	 * method will register the patient as a new Patient if the patient 
	 * is not one in the list of duplicate record found. 
	 * @param request
	 * @param fb
	 */
	/*public ActionForward SAVEASNEWPATIENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		newSplRegFB fb = (newSplRegFB) form;
		fb.setIsDuplicatePatientPopup("0");
		fb.setIsDuplicate("1");
		//NewSplRegUTIL.saveAsNewPatientRegistration(request, fb);		
	
		NewSplRegUTIL.setDeptOptions(request, fb);	
		Status status=(Status)request.getAttribute(Config.STATUS_OBJECT);
		WebUTIL.setStatus(request, status);
		return this.NEW(mapping,form,request,response);		
	}	*/

	/*public ActionForward SAVEPATIENTNEWDEPTVISIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		newSplRegFB fb = (newSplRegFB) form;
		NewSplRegUTIL.savePatientNewDepartmentVisit(request, fb);	
		if(request.getAttribute(RegistrationConfig.REDIRECT_TO_OLD_PATIENT_PROCESS).equals("true")){
			//return mapping.findForward("OLD_PATIENT");
			fb.setIsOldPatient("1");
			fb.setOldPatCrNo(fb.getPatCrNo());
			return this.NEW(mapping,form,request,response);
		}	
		else
			return this.NEW(mapping,form,request,response);		
	}	*/
	
	
	public ActionForward BPLPOPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		newSplRegFB newSplRegFBObj = (newSplRegFB) form;
		newSplRegFBObj.setPatBPLCardNo(request.getParameter("bplCardNo"));
		return mapping.findForward("BPLPOPUP");
			
	}
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//newSplRegFB newSplRegFBObj = (newSplRegFB) form;
		return mapping.findForward("POPUP");
			
	}
	
	/*public ActionForward CHECKFORPREVIOUSREG(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		newSplRegFB newSplRegFBObj = (newSplRegFB) form;
		ServletContext context=servlet.getServletContext();
		boolean exists=NewSplRegUTIL.checkForPreviousRegistration(newSplRegFBObj,request,context);
		if(exists){
			NewSplRegUTIL.setAllNewRegistrationEssentials(newSplRegFBObj,request);
			newSplRegFBObj.setIsDuplicatePatientPopup("1");
			newSplRegFBObj.setIsAddressDelhi("1");
		 	newSplRegFBObj.setPatAddStateCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);	 	
		 	newSplRegFBObj.setPatAddCountryCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
		 	newSplRegFBObj.setPatIsUrban(RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE);
		 	newSplRegFBObj.setFileNo(new String[]{""});	 
			return mapping.findForward("NEW");
			//return this.NEW(mapping, form, request, response);
		}
		else{
			return this.SAVE(mapping, form, request, response);
		}	
	}*/
	
	
	/**
	 * refreshes the page
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls the action "NEW"
	 */
	public ActionForward refresh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//HttpSession ses=request.getSession();
		//newSplRegFB fb = (newSplRegFB) form;
		//fb.reset(mapping, request);
		//return mapping.findForward("SAME");
		if(!isAptBased)
			return this.NEWREGWOAPT(mapping, form, request, response);
		else
			return this.NEWREGWAPT(mapping, form, request, response);
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		
		WebUTIL.refreshTransState(request,"NewSplRegwithAptmntACTION");
		return mapping.findForward("MENU");
	}
	
	/*public ActionForward GETSTATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		newSplRegFB newSplRegFBObj = (newSplRegFB) form;
		NewSplRegUTIL.setStateBasedOnCountry(newSplRegFBObj,request);					
		return mapping.findForward("SAME");	
	}*/
	
	public ActionForward GETBILLAMOUNT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		newSplRegFB newSplRegFBObj = (newSplRegFB) form;
		NewSplRegUTIL.setBillAmountBasedOncategory(newSplRegFBObj,request,response);
		//return null;
		return mapping.findForward("NEW");  
		//return mapping.findForward("SAME");	
	}
	
	
	public ActionForward GETEMPDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){							
		return mapping.findForward("EMPPOPUP");	
	}
	
	
	public ActionForward GETAMOUNTANDBPLDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		newSplRegFB newSplRegFBObj = (newSplRegFB) form;
		
		NewSplRegUTIL.setBillAmountBasedOncategory(newSplRegFBObj,request,response);					
		return null;
		//return mapping.findForward("NEW");  
	}
	
	
	/**
     * adds the selected Department to Department to be visited
     * sets Department Option
     * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return  action forwards to the output view called "SAME"
   */
	public ActionForward ADDDEPT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		newSplRegFB newSplRegFBObj = (newSplRegFB) form;		
	
			NewSplRegUTIL.addDeptToVist(newSplRegFBObj);	
		NewSplRegUTIL.setDeptOptions(request, newSplRegFBObj);
		Status objStatus =new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("SAME");
	}	
	
	
	/**
	 * Search New Registration Details
	
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls the action "NEW"
	 */
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	    
		Status objStatus=new Status();
		
		HttpSession _httpses=request.getSession();		
		newSplRegFB newSplRegFBObj = (newSplRegFB) form;
	    ServletContext context=servlet.getServletContext();
	    
	    if(_httpses.getAttribute(RegistrationConfig.ALL_PATIENT_VO_LIST)!=null){
			NewSplRegUTIL.setVoArrEssentials(request,newSplRegFBObj);
		}
		else{
		 	NewSplRegUTIL.searchSpecialCRNoWithAppointment(request, newSplRegFBObj);
		}		
	   
	 
	 	WebUTIL.setStatus(request,objStatus);
	 	
	 	return mapping.findForward("NEW");
		
	   
	}	
	/* #Start					:Sheeldarshi 
	#Modify Date(CR/PRS)	:22ndMay'15 
	#Reason					:The Total amount collected by concerned operator should be displayed on registration & Billing Processes
	 */
	 public ActionForward CASHCOLLECTIONPOPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {

			//BillingFB formBean = (BillingFB) form;
			String target="cashCollectionPopup";
			newSplRegFB newSplRegFBObj = (newSplRegFB) form;
			NewSplRegUTIL.getCashCollectionDetail(request,response, newSplRegFBObj);
			return mapping.findForward("cashCollectionPopup");
			
		}
}