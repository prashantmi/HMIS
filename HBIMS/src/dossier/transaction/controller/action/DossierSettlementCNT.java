package dossier.transaction.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.transactions.controller.data.IssueTransDATA;
import mms.transactions.controller.fb.IssueTransFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import dossier.transaction.controller.fb.DossierSettlementFB;
import dossier.transaction.controller.data.DossierSettlementDATA;

public class DossierSettlementCNT extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		System.out.println("Inside DossierSettlementCNT Form");
		return this.NEW(mapping, form, request, response);
	}

	/**
	 * sets the view to Patient Listing View
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println("123::::::::::");
		/*DoctorDeskFB formBean = (DoctorDeskFB) form;
		 * 
		DoctorDeskDATA.getInitailData(formBean ,request);*/
		System.out.println(request.getParameter("servicetypeId"));
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		formBean.setStrSericeType(request.getParameter("servicetypeId"));
		return mapping.findForward("NEW");
	}
	public ActionForward NEW1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		formBean.setPrintReq("1");
		return mapping.findForward("NEW");
	}
	
	public ActionForward INITVALGO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
   {
		
		generateToken(request);
		String target="";
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		if(formBean.getNewreqflg().equalsIgnoreCase("1"))
		{
			formBean.setStrCrNo(formBean.getCrNo());
		}
		formBean.setPrintReq("0");
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());		
	    System.out.println("formBean.getStrCrNo()>>>"+formBean.getStrCrNo());
	    System.out.println("formBean.getStrLFNo()>>>"+formBean.getStrLFNo());
	
	    DossierSettlementDATA.getPatientDetails(formBean, request, response);
		 System.out.println("formBean.getStrCrNo()>>>"+formBean.getStrCrNo());
	    formBean.setCrNo(formBean.getStrCrNo());
		if(formBean.getStrInvalidCrFlag().equals("1") || formBean.getStrInvalidLFFlag().equals("1") )
		{
			
			return this.INITVAl(mapping, formBean, request, response);
			
		}
		else
		{
			DossierSettlementDATA.getOnlineReqDtl(formBean, request, response);
			//DossierSettlementDATA.getDeptDetails(formBean, request);
			//DossierSettlementDATA.getUnitDetails1(formBean, request, response);
			//DossierSettlementDATA.getFrequencyDetails(formBean, request);
			//DossierSettlementDATA.getDoseDetails(formBean, request);
			
			//DossierSettlementDATA.getItemCatValues(formBean, request,response);
			//DossierSettlementDATA.getDossierValues(formBean, request,response);
		//DossierSettlementDATA.getOnlineTreatmentDtl(formBean, request, response);
			
			
//			 Added By Ranjit for Dossier OT Integration  
			//if(formBean.getRequestMode()!=null && formBean.getRequestMode().equals("OT") ){
				
			//	DossierSettlementDATA.getOperationName(formBean, request, response);
			//	DossierSettlementDATA.getDossierStatusString(formBean, request, response);
				
				//DossierSettlementDATA.getOTDossierId(formBean, request, response);
			//}
			
			//DossierSettlementDATA.getStoreDtls(formBean, request);
			//DossierSettlementDATA.getDeptDetails(formBean, request);
			
			/*if(!formBean.getStrIssueMode().equals("0")){
			
				IssueTransDATA.getGroupDetails(formBean, request);
				
			}
			if(formBean.getStrCRorLFwise().equals("2"))
				{
				
				target = "issueGoLFWise";
				
				}
			else*/
			//formBean.setStrDoseFrqFlg("0");
			//System.out.println("formBean.getStrPatientType()"+formBean.getStrPatientType());
			
			/* if(formBean.getStrPatientType().equalsIgnoreCase("1"))
			{
				 if(!(formBean.getStrBillingHiddenValue().split("\\^")[4].equals("2")))
					{
						formBean.setStrErrMsg("Patient Not Admitted");
						target = "NEW";
					}else if(formBean.getStrBillingHiddenValue().split("\\^")[0].equals("0")){
						formBean.setStrErrMsg("Patient Account Not exits");
						target = "NEW";
					}
				 else*/
					 target = "GO";
			//}
		
			//else{
				
				// if(formBean.getStrBillingHiddenValue().split("\\^")[4].equals("2")){
			//		 formBean.setStrErrMsg("Patient Admitted");
			//			target = "NEW"; 
				 //}else{
					// target = "GOOPD";
				 //}
				
		//	}
				
			return mapping.findForward(target);
		}
		//return null;
	
	}
	
	public ActionForward GETDOSSIERITEMDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
     
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierSettlementDATA.getDossierItemDetails(formBean, request, response);
		return null;
	}
	public ActionForward GETDOSSIERITEMDTLSOPD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
     
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierSettlementDATA.getDossierItemDetailsopd(formBean, request, response);
		return null;
	}
	public ActionForward GETSERVICEDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
     
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierSettlementDATA.getServiceDetails(formBean, request, response);
		return null;
	}
	
	public ActionForward GETDOSSIERNAME(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
     
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierSettlementDATA.getDossierDetails(formBean, request, response);
		return null;
	}
	
	
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
   {
     
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierSettlementDATA.INSERT(formBean, request, response);
		//return mapping.findForward("GO");
		return this.NEW1(mapping, formBean, request, response);
	}
	
	
	// Added By Ranjit for Dossier OT Integration
	public ActionForward INSERTOT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
   {
     
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierSettlementDATA.INSERTOT(formBean, request, response);
		//return mapping.findForward("GO");
		//return this.NEW1(mapping, formBean, request, response);
		return null;
	}
	

	public ActionForward INSERTOPD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
   {
     
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierSettlementDATA.INSERTOPD(formBean, request, response);
		//return mapping.findForward("GO");
		return this.NEW1(mapping, formBean, request, response);
	}
	public ActionForward INITVAl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	
		DossierSettlementFB formBean = (DossierSettlementFB) form;
	
		return mapping.findForward("NEW");
	}
	public ActionForward CANCELTODESK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	
			ActionForward acFwd = new ActionForward();
		 	acFwd.setPath("/dossier/transaction/DossierDeskTransCNT.cnt?hmode=RETURNTOMAINDESK");
	    	acFwd.setContextRelative(true);
	        return acFwd;
	        
		//DossierSettlementFB formBean = (DossierSettlementFB) form;
	
		//return mapping.findForward("NEW");
	}
	
	public ActionForward ISSUEDTLSINITONE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DossierSettlementFB formBean = (DossierSettlementFB) form;
		DossierSettlementDATA.issueDtlsInit(request, response, formBean);
		return null;
	}
	
	// Added By Ranjit for Dossier OT Integration
	public ActionForward getOTDossierRadio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		DossierSettlementDATA.getOTDossierId(formBean,request, response );
		return null;
	}
	
	// Added By Ranjit for Dossier OT Integration
public ActionForward rejectDossier(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DossierSettlementFB formBean = (DossierSettlementFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		DossierSettlementDATA.rejectDossier(formBean,request, response );
		return null;
	}

// Added By Ranjit for Dossier OT Integration
public ActionForward ajaxGetDossierStatusString(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	DossierSettlementFB formBean = (DossierSettlementFB) form;
	formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	
	
	DossierSettlementDATA.ajaxGetDossierStatusString(formBean, request, response);
	
	return null;
}
	
	
		
	

}
