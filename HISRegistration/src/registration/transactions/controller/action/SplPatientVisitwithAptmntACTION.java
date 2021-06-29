
package registration.transactions.controller.action;

import java.util.Map;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.*;

import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.SpclPatientVisitSUP;
import registration.transactions.controller.fb.SplPatientVisitFB;
import registration.transactions.controller.fb.newSplRegFB;
import registration.transactions.controller.util.NewSplRegUTIL;
import registration.transactions.controller.util.SpclPatientVisitUTL;
import registration.transactions.controller.util.SplPatientVisitWithAppUTIL;
import vo.registration.RegistrationConfigVO;


import hisglobal.presentation.CSRFGardTokenAction;//By Mukund on 02.12.2016


//public class SplPatientVisitwithAptmntACTION extends DispatchAction {
public class SplPatientVisitwithAptmntACTION extends CSRFGardTokenAction {
	

	boolean isAptBased=false;

	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action  "NEW"
	 */
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//return this.NEW(mapping,form,request,response);
		
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
				return this.NEW(mapping,form,request,response);				
			}
		}
		else	
			return this.NEW(mapping,form,request,response);	
	}
	
	/**
	 * action mainly called at the initial loading of a page or when a form is reset
	 * refreshes Transaction State
	 * sets all initial Old Patient Visit essentials
	 * @param mapping -object of ActionMapping
	 *  @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		//By Mukund to generate token
			//generateToken(request);
				//String token=request.getSession().getAttribute("org.apache.struts.action.TOKEN").toString();
				//System.out.println("generated token SplPatientVisitwithAptmntACTION: "+token);
				//End:Mukund
		SplPatientVisitFB fb = (SplPatientVisitFB) form;
		Status objStatus=new Status();
		fb.reset(mapping,request);
		String strMode="";
		HttpSession session=request.getSession();
		WebUTIL.refreshTransState(request,"SplPatientVisitwithAptmntACTION");
		
	 	if(fb.getModeCase()==null)
	 		fb.setModeCase("2");
	 	//if(Config.REGISTRATION_ONLINE_OFFLINE_TYPE.equals(Config.REGISTRATION_ONLINE_OFFLINE_BOTH) || Config.REGISTRATION_ONLINE_OFFLINE_TYPE.equals(Config.REGISTRATION_ONLINE))
	 	//{
	 		//WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_EPISODE_REFER_PAT_VO,"");
	 	request.getSession().removeAttribute(RegistrationConfig.ARR_EPISODE_REFER_PAT_VO);
	 	request.getSession().removeAttribute(RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO);
	 	request.getSession().removeAttribute(RegistrationConfig.ARR_APPOINTMENT_NEW_PAT_REGD_VO);//By Mukund on 15.04.2017
	 	
	 		SplPatientVisitWithAppUTIL.getMapOfRenewalConfigDtlOnKeyPatCat(fb, request);
	 		
	 		SplPatientVisitWithAppUTIL.getDeptUnit(request);//By Mukund To populate the Dept/unit combo
	 		
	 		if(isAptBased)
	 			SplPatientVisitWithAppUTIL.getAppointmentNewRegTdDtl(fb, request);
	 		else
	 			SplPatientVisitWithAppUTIL.getVisitTdDtl(fb, request);
	 		if(fb.getModeCase().equals("0"))
			{
		 		strMode="NEWPATIENT";
			}
			else if(fb.getModeCase().equals("1"))
			{
				strMode="NEWPATIENT";
			}
			else 
			{
				strMode="NEWPATIENT";
			}
	 		
	 		
	 	//}	
	 		WebUTIL.setStatus(request,objStatus); 	
	 	
	 		return mapping.findForward(strMode);
	 	}
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		SplPatientVisitFB fb = (SplPatientVisitFB) form;	
		
		request.getSession().removeAttribute(RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO);
		SplPatientVisitWithAppUTIL.setPatientDtlByCrno(fb,request);
		String strMode="";
		fb.setSaveSuccessful("");
		request.getSession().removeAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT);
		
		if((fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE)))
		{
			if(request.getSession().getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR)!=null)
				fb.setModeCase("0");
			else
				fb.setModeCase("1");
			
			SplPatientVisitWithAppUTIL.setPatientAptDtlByAptNO(fb,request);			
			
		}
		
		if(fb.getModeCase().equals("0"))
		{
	 		strMode="SAMENEWPATIENT";
	 		fb.setOldDepartmentVisit("On");
		}
		else if(fb.getModeCase().equals("1"))
		{
			strMode="SAMENEWPATIENT";
			fb.setNewDepartmentVisit("On");
		}
		else if(fb.getModeCase().equals("2"))
		{
			if(fb.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
			{
				if(fb.getSelectedReferal().equalsIgnoreCase("O"))
				{
					fb.setOldDepartmentVisit("On");
					fb.setModeCase("0");
				}
				else
				{
					fb.setNewDepartmentVisit("On");
					fb.setModeCase("1");
				}
				strMode="SAMENEWPATIENT";		
			}
			else
			{
			fb.setOldDepartmentVisit("On");
			fb.setNewDepartmentVisit("On");
	 		strMode="SAMENEWPATIENT";		
			}
		}
		SplPatientVisitWithAppUTIL.getInitEssentials(request, response);
		
		return mapping.findForward(strMode);
	}
	
	public ActionForward NEWDEPARTMENTVISITDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		SplPatientVisitFB fb = (SplPatientVisitFB) form;
		if(fb.getNewDepartmentVisit()!=null&&fb.getNewDepartmentVisit().equalsIgnoreCase("on"))
			SplPatientVisitWithAppUTIL.setNewDepartmentVisitDtl(fb,request);
		
		fb.setSaveSuccessful("");

		if(fb.getOldDepartmentVisit().equalsIgnoreCase("on"))
			fb.setOldDepartmentVisit("On");
		
		request.getSession().removeAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT);
		return mapping.findForward("SAMENEWPATIENT");
	}
	
	/**
	 * sets Secondary Category Essentials
	* @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "SAME"
	 */
	/*public ActionForward GETREGFEE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		
		SplPatientVisitFB fb= (		SplPatientVisitFB) form;
		SplPatientVisitUTIL.getBillAmountByDeptGrouping(fb, request);
		Status status=(Status)request.getAttribute(Config.STATUS_OBJECT);
		
		if(fb.getNewDepartmentVisit().equalsIgnoreCase("on"))
			fb.setNewDepartmentVisit("On");
		
		if(fb.getOldDepartmentVisit().equalsIgnoreCase("on"))
			fb.setOldDepartmentVisit("On");
		
		return mapping.findForward("SAMENEWPATIENT");
	}*/
	public ActionForward OLDDEPARTMENTVISITDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		SplPatientVisitFB fb = (SplPatientVisitFB) form;
		//SplPatientVisitUTIL.setAllInitialOldPatientVisitEssentials(request);
		System.out.println("PatientVisitACTION:OLDDEPARTMENTVISITDTL"+fb.getNewDepartmentVisit());
		System.out.println("PatientVisitACTION:OLDDEPARTMENTVISITDTL"+fb.getOldDepartmentVisit());
		
		if(fb.getOldDepartmentVisit()!=null&&fb.getOldDepartmentVisit().equalsIgnoreCase("on"))
			SplPatientVisitWithAppUTIL.setOldDepartmentVisitDtl(fb,request);
		else
			SplPatientVisitWithAppUTIL.setOldDepartmentVisitDtlOFF(fb,request);
		fb.setSaveSuccessful("");
		
		if(fb.getNewDepartmentVisit().equalsIgnoreCase("on"))
			fb.setNewDepartmentVisit("On");
		
		//fb.setOldDepartmentVisit("1");
		request.getSession().removeAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT);
		return mapping.findForward("SAMENEWPATIENT");
	}
	
	/**
	 * saves Old Patient Visit Details
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls the action "NEW"
	 * /**
	 * saves New Department Visit Details	
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls the action "NEW"
	 */
	 
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("Servlet Action SAVE");
		//By Mukund on 02.12.216 for validating token
				try{
				//validateToken(request, response);
				}catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("Token Error: "+e);
				}//End : Mukund
				
		SplPatientVisitFB fb = (SplPatientVisitFB) form;
		boolean renewStatus=false;
		
		SpclPatientVisitACTION _splTemp=new SpclPatientVisitACTION();
		_splTemp.populate(fb);	
		_splTemp.setIsPrintStatus("2");//2 for printing barcode only, 1 for with OPD card
		
		if(fb.getOldDepartmentVisit().equalsIgnoreCase("On") && fb.getNewDepartmentVisit().equals(""))
		{
			//renewStatus=SplPatientVisitUTIL.saveOldSplPatientVisit(request,fb,"SAVE");
			renewStatus=SpclPatientVisitUTL.saveOldPatientVisit(request,_splTemp.getActionSUP(),"SAVE");
			//fb.setPrint("1");//By Mukund on 16.11.2016 to get the print of bill receipt
			//fb.setPrint("1"); // Commented to skip print pop up for Old visit stamping
			//System.out.println("Old Department Visit Saved");
		}
		else if(fb.getOldDepartmentVisit().equals("")  && fb.getNewDepartmentVisit().equalsIgnoreCase("On"))
		{
			//System.out.println("New Department Visit Saved");
			//SplPatientVisitUTIL.savePatientNewDepartmentVisit(request, fb);
			SpclPatientVisitUTL.savePatientNewDepartmentVisit(request,_splTemp.getActionSUP());
			fb.setPrint(_splTemp.getActionSUP().getPrint());//2 for printing barcode only, 1 for with OPD card

		}
		else if(fb.getNewDepartmentVisit().equalsIgnoreCase("On") && fb.getOldDepartmentVisit().equalsIgnoreCase("On"))
		{
			//SplPatientVisitUTIL.saveSplPatientVisitStamp(request,fb);
			//System.out.println("Patient Visit Saved");
			SpclPatientVisitUTL.saveSplPatientVisitStamp(request,_splTemp.getActionSUP());
			fb.setPrint("1");
		}
		
		
		return this.NEW(mapping,form,request,response);
		
	}
	
	
	
	
	public ActionForward DGNDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		SplPatientVisitFB  fb=(SplPatientVisitFB )form;
		fb.setPatCrNo(fb.getCrNoToRetrieve());
		//WebUTIL.setAttributeInSession(request,RegistrationConfig.retrieveBypatNameCRNO,fb.getDiagnosticCode());
		//WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_SELECTED_DIAGNOSIS_NAME,fb.getDiagnosticName());
		return mapping.findForward("SAME");
	 }
	public ActionForward EPISODEDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		SplPatientVisitFB  fb=(SplPatientVisitFB )form;
		SplPatientVisitWithAppUTIL.getOpenEpisodeDtl(request,fb);
		return mapping.findForward("SAMENEWPATIENT");
	}
	
	
	/**
	 * saves New Department Visit Details	
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls the action "NEW"
	 */
	public ActionForward CHECKMANDATORY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		SplPatientVisitFB fb = (SplPatientVisitFB) form;
		//boolean flag=SplPatientVisitUTIL.checkMandatoryFiledsForDepartment(fb,request);
		boolean flag=false;
		if(!flag )
		{
			return this.SAVE(mapping, form, request, response);
		}
		else
			return mapping.findForward("SAMENEWPATIENT");
		//return this.NEW(mapping, form, request, response);
	}
	/*public ActionForward RENEWAL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		SplPatientVisitFB  fb=(SplPatientVisitFB )form;
		boolean renewStatus=false;
		
		if(fb.getModeCase().equals("0"))
		{
			renewStatus=SplPatientVisitWithAppUTIL.saveOldSplPatientVisit(request,fb,"RENEWAL");
		}
		
		return mapping.findForward("SAMENEWPATIENT");
		
		
	}
	//for reprint opd card
	public ActionForward PRINT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		SplPatientVisitFB  fb=(SplPatientVisitFB )form;
		SplPatientVisitWithAppUTIL.printLastOpdCard(fb,request);
		//fb.reset(mapping, request);
		fb.setSaveSuccessful("");
		request.getSession().removeAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT);
		return this.NEW(mapping,form,request,response);
		
	}*/
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		
		WebUTIL.refreshTransState(request,"SplPatientVisitwithAptmntACTION");
		return mapping.findForward("MENU");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	    
		Status objStatus=new Status();
		
		HttpSession _httpses=request.getSession();		
		SplPatientVisitFB fb = (SplPatientVisitFB) form;
	    ServletContext context=servlet.getServletContext();
	    
	    if(_httpses.getAttribute(RegistrationConfig.ALL_PATIENT_VO_LIST)!=null){
	    	SplPatientVisitWithAppUTIL.setVoArrEssentials(request,fb);
		}
		else{
			SplPatientVisitWithAppUTIL.searchSpecialCRNoWithAppointment(request, fb);
		}		
	   
	 
	 	WebUTIL.setStatus(request,objStatus);
	 	
	 	return mapping.findForward("NEW");
		
	   
	}	
	/* #Start				:Sheeldarshi 
	#Modify Date(CR/PRS)	:22ndMay'15 
	#Reason					:The Total amount collected by concerned operator should be displayed on registration & Billing Processes
	 */
	 public ActionForward CASHCOLLECTIONPOPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {

			//BillingFB formBean = (BillingFB) form;
			String target="cashCollectionPopup";
			SplPatientVisitFB fb = (SplPatientVisitFB) form;
			SplPatientVisitWithAppUTIL.getCashCollectionDetail(request,response, fb);
			return mapping.findForward("cashCollectionPopup");
			
		}
}
