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

import dossier.transaction.controller.fb.DossierRequisitionFB;
import dossier.transaction.controller.data.DossierRequisitionDATA;

public class DossierRequisitionCNT extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		System.out.println("Inside DossierRequisitionCNT Form");
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
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		formBean.setStrSericeType(request.getParameter("servicetypeId"));
		return mapping.findForward("NEW");
	}
	public ActionForward NEW1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		formBean.setPrintReq("1");
		return mapping.findForward("NEW");
	}
	
	public ActionForward INITVALGO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
   {
		
		generateToken(request);
		String target="";
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		if(formBean.getNewreqflg().equalsIgnoreCase("1"))
		{
			formBean.setStrCrNo(formBean.getCrNo());
		}
		formBean.setPrintReq("0");
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());		
	    System.out.println("formBean.getStrCrNo()>>>"+formBean.getStrCrNo());
	    System.out.println("formBean.getStrLFNo()>>>"+formBean.getStrLFNo());
	
	    DossierRequisitionDATA.getPatientDetails(formBean, request, response);
		 System.out.println("formBean.getStrCrNo()>>>"+formBean.getStrCrNo());
	    formBean.setCrNo(formBean.getStrCrNo());
		if(formBean.getStrInvalidCrFlag().equals("1") || formBean.getStrInvalidLFFlag().equals("1") )
		{
			
			return this.INITVAl(mapping, formBean, request, response);
			
		}
		else
		{
			DossierRequisitionDATA.getOnlineReqDtl(formBean, request, response);
			DossierRequisitionDATA.getDeptDetails(formBean, request);
			//DossierRequisitionDATA.getUnitDetails1(formBean, request, response);
			DossierRequisitionDATA.getFrequencyDetails(formBean, request);
			DossierRequisitionDATA.getDoseDetails(formBean, request);
			
			DossierRequisitionDATA.getItemCatValues(formBean, request,response);
			//DossierRequisitionDATA.getDossierValues(formBean, request,response);
			DossierRequisitionDATA.getOnlineTreatmentDtl(formBean, request, response);
			
			
//			 Added By Ranjit for Dossier OT Integration  
			if(formBean.getRequestMode()!=null && formBean.getRequestMode().equals("OT") ){
				
				DossierRequisitionDATA.getOperationName(formBean, request, response);
				DossierRequisitionDATA.getDossierStatusString(formBean, request, response);
				
				//DossierRequisitionDATA.getOTDossierId(formBean, request, response);
			}
			
			//DossierRequisitionDATA.getStoreDtls(formBean, request);
			//DossierRequisitionDATA.getDeptDetails(formBean, request);
			
			/*if(!formBean.getStrIssueMode().equals("0")){
			
				IssueTransDATA.getGroupDetails(formBean, request);
				
			}
			if(formBean.getStrCRorLFwise().equals("2"))
				{
				
				target = "issueGoLFWise";
				
				}
			else*/
			formBean.setStrDoseFrqFlg("0");
			System.out.println("formBean.getStrPatientType()"+formBean.getStrPatientType());
			
			 if(formBean.getStrPatientType().equalsIgnoreCase("1"))
			{
				 if(!(formBean.getStrBillingHiddenValue().split("\\^")[4].equals("2")))
					{
						formBean.setStrErrMsg("Patient Not Admitted");
						target = "NEW";
					}else if(formBean.getStrBillingHiddenValue().split("\\^")[0].equals("0")){
						formBean.setStrErrMsg("Patient Account Not exits");
						target = "NEW";
					}
				 else
					 target = "GO";
			}
		
			else{
				
				 if(formBean.getStrBillingHiddenValue().split("\\^")[4].equals("2")){
					 formBean.setStrErrMsg("Patient Admitted");
						target = "NEW"; 
				 }else{
					 target = "GOOPD";
				 }
				
			}
				
			return mapping.findForward(target);
		}
		//return null;
	
	}
	
	public ActionForward GETDOSSIERITEMDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
     
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierRequisitionDATA.getDossierItemDetails(formBean, request, response);
		return null;
	}
	public ActionForward GETDOSSIERITEMDTLSOPD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
     
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierRequisitionDATA.getDossierItemDetailsopd(formBean, request, response);
		return null;
	}
	public ActionForward GETSERVICEDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
     
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierRequisitionDATA.getServiceDetails(formBean, request, response);
		return null;
	}
	
	public ActionForward GETDOSSIERNAME(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
     
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierRequisitionDATA.getDossierDetails(formBean, request, response);
		return null;
	}
	
	
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
   {
     
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierRequisitionDATA.INSERT(formBean, request, response);
		//return mapping.findForward("GO");
		return this.NEW1(mapping, formBean, request, response);
	}
	
	
	// Added By Ranjit for Dossier OT Integration
	public ActionForward INSERTOT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
   {
     
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierRequisitionDATA.INSERTOT(formBean, request, response);
		//return mapping.findForward("GO");
		//return this.NEW1(mapping, formBean, request, response);
		return null;
	}
	

	public ActionForward INSERTOPD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
   {
     
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		DossierRequisitionDATA.INSERTOPD(formBean, request, response);
		//return mapping.findForward("GO");
		return this.NEW1(mapping, formBean, request, response);
	}
	public ActionForward INITVAl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
	
		return mapping.findForward("NEW");
	}
	public ActionForward CANCELTODESK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	
			ActionForward acFwd = new ActionForward();
		 	acFwd.setPath("/dossier/transaction/DossierDeskTransCNT.cnt?hmode=RETURNTOMAINDESK");
	    	acFwd.setContextRelative(true);
	        return acFwd;
	        
		//DossierRequisitionFB formBean = (DossierRequisitionFB) form;
	
		//return mapping.findForward("NEW");
	}
	
	public ActionForward ISSUEDTLSINITONE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		DossierRequisitionDATA.issueDtlsInit(request, response, formBean);
		return null;
	}
	
	// Added By Ranjit for Dossier OT Integration
	public ActionForward getOTDossierRadio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		DossierRequisitionDATA.getOTDossierId(formBean,request, response );
		return null;
	}
	
	// Added By Ranjit for Dossier OT Integration
public ActionForward rejectDossier(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DossierRequisitionFB formBean = (DossierRequisitionFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		DossierRequisitionDATA.rejectDossier(formBean,request, response );
		return null;
	}

// Added By Ranjit for Dossier OT Integration
public ActionForward ajaxGetDossierStatusString(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	DossierRequisitionFB formBean = (DossierRequisitionFB) form;
	formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	
	
	DossierRequisitionDATA.ajaxGetDossierStatusString(formBean, request, response);
	
	return null;
}
	
	
		
	

}
