package bmed.transactions.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bmed.transactions.controller.util.ComplaintLogOfflineUTIL;
import bmed.transactions.controller.util.ComplaintMaintenanceStatusUTIL;
import bmed.transactions.controller.util.EquipmentInspectionTestDtlsUTIL;
import bmed.transactions.controller.fb.ComplaintLogOfflineFB;
import bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB;
import bmed.transactions.controller.fb.EquipmentInspectionTestDtlsFB;

public class EquipmentInspectionTestDtlsACTION extends CSRFGardTokenAction {

	private String strForwardName;

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		generateToken(request);	
		EquipmentInspectionTestDtlsFB   complaintLogOfflineFB =(EquipmentInspectionTestDtlsFB ) form;
		return initializeItemComplaintRegister(mapping, form, request, response);

	}

	public ActionForward getAjaxComplaintRequestData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		ComplaintLogOfflineUTIL.getAjaxComplaintRequestData(request,
				response);

		return null;

	}

	/**
	 * Forward Control to Item Complaint Register
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 */
	public ActionForward initializeItemComplaintRegister(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
	{		
		generateToken(request_p);	
		EquipmentInspectionTestDtlsFB  complaintMaintenanceStatusFB = (EquipmentInspectionTestDtlsFB) form_p;
		complaintMaintenanceStatusFB.setStrPageFlag("ITEM");
		complaintMaintenanceStatusFB.setStrItemOrNonItemMode("1");
		EquipmentInspectionTestDtlsUTIL.initializeItemComplaintRegister(complaintMaintenanceStatusFB,request_p);
		System.out.println("Type"+complaintMaintenanceStatusFB.getStrComplaintType());
		return mapping_p.findForward("item");

	}
	
	
	/**
	 * To get STORE.
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward GETSTORENAME(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException,SQLException 
	{		
		EquipmentInspectionTestDtlsFB formBean = (EquipmentInspectionTestDtlsFB)form_p;
		EquipmentInspectionTestDtlsUTIL.getStoreName(formBean,request_p,response_p);
		return null;
	}
	
	/**
	 * To get VENDOR DETAILS.
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward GETVENDORDTLS(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException,SQLException 
	{		
		ComplaintLogOfflineFB formBean = (ComplaintLogOfflineFB)form_p;
		ComplaintLogOfflineUTIL.getVendorDetails(formBean,request_p,response_p);
		return null;
	}
	
	
	
	public ActionForward GETVENDORCOMBO(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException,SQLException 
	{		
		ComplaintLogOfflineFB formBean = (ComplaintLogOfflineFB)form_p;
		ComplaintLogOfflineUTIL.getVendorCombo(formBean,request_p,response_p);
		return null;
	}
	
	
	public ActionForward EMPCOMBO(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException,SQLException 
	{		
		ComplaintLogOfflineFB formBean = (ComplaintLogOfflineFB)form_p;
		ComplaintLogOfflineUTIL.getEmpCombo(formBean,request_p,response_p);
		return null;
	}
	
	
	
	public ActionForward EMPDESIGNATION(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException,SQLException 
	{		
		ComplaintLogOfflineFB formBean = (ComplaintLogOfflineFB)form_p;
		ComplaintLogOfflineUTIL.getEmpDesignationDetails(formBean,request_p,response_p);
		return null;
	}
	
	/**
	 * To get SERVICE ENG  DETAILS.
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward GETSERVICEENGNAME(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException,SQLException 
	{		
		EquipmentInspectionTestDtlsFB formBean = (EquipmentInspectionTestDtlsFB)form_p;
		EquipmentInspectionTestDtlsUTIL.getServiceEngName(formBean,request_p,response_p);
		return null;
	}
	
	
	
	
	
	
	/**
	 * Get Engg Item Sub Type Combo using Ajax. 
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the null
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward ENGGITEMSUBTYPECMB(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException, SQLException {

		EquipmentInspectionTestDtlsFB fb = (EquipmentInspectionTestDtlsFB) form_p;
		EquipmentInspectionTestDtlsUTIL.getEnggItemSubTypeCmb(fb, request_p, response_p);
		
		return null;
	}
	
	/**
	 * Get Item Name.
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward GETITEMNAME(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		EquipmentInspectionTestDtlsFB formBean = (EquipmentInspectionTestDtlsFB)form_p;
		EquipmentInspectionTestDtlsUTIL.getItemName(formBean,request_p,response_p);
		
		return null;
	}
	
	

	/**
	 * Get GET NON_ITEM NAME
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward GETNONITEMNAME(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		EquipmentInspectionTestDtlsFB formBean = (EquipmentInspectionTestDtlsFB)form_p;
		EquipmentInspectionTestDtlsUTIL.getNonItemName(formBean,request_p,response_p);
		
		return null;
	}
	
	/**
	 *  to GET STOCK DTL .
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward GETSTOCKDTL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		EquipmentInspectionTestDtlsFB formBean = (EquipmentInspectionTestDtlsFB)form_p;
		EquipmentInspectionTestDtlsUTIL.getStockDetails(formBean,request_p,response_p);
		return null;
	}
	
	
	/**
	 *  to GET PREVIOUS COMPLAINT DTL .
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward PREVIOUSCOMPLAINTDTL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		EquipmentInspectionTestDtlsFB formBean = (EquipmentInspectionTestDtlsFB)form_p;
		EquipmentInspectionTestDtlsUTIL.getPreviousComplaintDetails(formBean,request_p,response_p);
		return null;
	}
	
	/**
	 *  to GET WARRANTY DTL .
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward WARRANTYDTL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		EquipmentInspectionTestDtlsFB formBean = (EquipmentInspectionTestDtlsFB)form_p;
		EquipmentInspectionTestDtlsUTIL.getWarrantyDetails(formBean,request_p,response_p);
		return null;
	}
	
	
	
	/**
	 *  to GET MAINTENANCE CONTRACT DTL .
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward MAINTENANCECONTRACTDTL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		EquipmentInspectionTestDtlsFB formBean = (EquipmentInspectionTestDtlsFB)form_p;
		EquipmentInspectionTestDtlsUTIL.getMaintenanceContractDetails(formBean,request_p,response_p);
		return null;
	}
	
	/**
	 *  to save Data .
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward SAVE(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws Exception 
	{	
		validateToken(request_p,response_p);
		EquipmentInspectionTestDtlsFB formBean = (EquipmentInspectionTestDtlsFB)form_p;
		EquipmentInspectionTestDtlsUTIL.saveData(formBean,request_p);
		return this.unspecified(mapping_p, form_p, request_p, response_p);
	}
	
	
	/**
	 * Forward Control to Non-Item Complaint Register
	 *
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward initializeNonItemComplaintRegister(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
	{		
		generateToken(request_p);
		ComplaintLogOfflineFB complaintMaintenanceStatusFB = (ComplaintLogOfflineFB) form_p;
		complaintMaintenanceStatusFB.setStrPageFlag("NON_ITEM");
		complaintMaintenanceStatusFB.setStrItemOrNonItemMode("0");
		ComplaintLogOfflineUTIL.initializeItemComplaintRegister(complaintMaintenanceStatusFB,request_p);
				
		return mapping_p.findForward("item");
	}

	public ActionForward initializeCancel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		generateToken(request);
		ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB = (ComplaintMaintenanceStatusFB) form;
		ComplaintMaintenanceStatusUTIL.initializeCancel(
				complaintMaintenanceStatusFB, request);
		strForwardName = "cancel";
		return mapping.findForward(strForwardName);

	}

	public ActionForward saveCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB = (ComplaintMaintenanceStatusFB) form;
		ComplaintMaintenanceStatusUTIL.saveCancel(complaintMaintenanceStatusFB,
				request);
		return unspecified(mapping, form, request, response);

	}

	public ActionForward initializeSchedule(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		generateToken(request);
		ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB = (ComplaintMaintenanceStatusFB) form;
		ComplaintMaintenanceStatusUTIL.initializeSchedule(
				complaintMaintenanceStatusFB, request);
		strForwardName = "schedule";
		return mapping.findForward(strForwardName);

	}

	public ActionForward saveSchedule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB = (ComplaintMaintenanceStatusFB) form;
		ComplaintMaintenanceStatusUTIL.saveSchedule(
				complaintMaintenanceStatusFB, request);
		return unspecified(mapping, form, request, response);

	}

	public ActionForward getFilterValue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ComplaintMaintenanceStatusUTIL.getFilterValue(request, response);
		return null;

	}

	public ActionForward getOtherServiceEngineerDetailsTable(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		ComplaintMaintenanceStatusUTIL.getOtherServiceEngineerDetailsTable(
				request, response);
		return null;

	}

	/*
	 * This Method is called to go to HIS welcome page.
	 */
	public ActionForward goToWelcomePage(ActionMapping mapping_p,
			ActionForm form_p, HttpServletRequest request_p,
			HttpServletResponse response_p) throws HisException {
		generateToken(request_p);
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward initializeAttended(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		generateToken(request);
		ComplaintLogOfflineFB complaintMaintenanceStatusFB = (ComplaintLogOfflineFB) form;
		ComplaintLogOfflineUTIL.initializeAttended(
				complaintMaintenanceStatusFB, request,response);
		strForwardName = "item";
		//return mapping.findForward(strForwardName);
		
		return null;

	}
	
	/**
	 * Forwards the control to Complaint Details View Page
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 */
	public ActionForward viewDetails(ActionMapping mapping_p,ActionForm form_p, HttpServletRequest request_p,HttpServletResponse response_p) 
	{
		generateToken(request_p);
		ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB = (ComplaintMaintenanceStatusFB) form_p;
		ComplaintMaintenanceStatusUTIL.initializeComplaintDetailsView(complaintMaintenanceStatusFB,request_p);
		strForwardName = "view";
		return mapping_p.findForward(strForwardName);

	}
	
	/**
	 * Forwards the control to Complaint Reminders Page
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 */
	public ActionForward initializeReminder(ActionMapping mapping_p,ActionForm form_p, HttpServletRequest request_p,HttpServletResponse response_p) 
	{
		generateToken(request_p);
		ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB = (ComplaintMaintenanceStatusFB) form_p;
		ComplaintMaintenanceStatusUTIL.initializeReminder(complaintMaintenanceStatusFB,request_p);
		strForwardName = "reminder";
		return mapping_p.findForward(strForwardName);

	}
	
	/**
	 * To Save Reminder Details
	 * 	 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * @throws Exception 
	 */
	public ActionForward saveReminder(ActionMapping mapping_p, ActionForm form_p,HttpServletRequest request_p, HttpServletResponse response_p) throws Exception
	{	
		validateToken(request_p,response_p);
		ComplaintMaintenanceStatusFB formBean = (ComplaintMaintenanceStatusFB)form_p;
		ComplaintMaintenanceStatusUTIL.saveReminder(formBean,request_p);
		return this.unspecified(mapping_p, form_p, request_p, response_p);
	}

			

	/**
	 * Forwards the control to Complaint Reminder Reply Page
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 */
	public ActionForward initializeReminderReply(ActionMapping mapping_p,ActionForm form_p, HttpServletRequest request_p,HttpServletResponse response_p) 
	{
		generateToken(request_p);
		ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB = (ComplaintMaintenanceStatusFB) form_p;
		ComplaintMaintenanceStatusUTIL.initializeReminderReply(complaintMaintenanceStatusFB,request_p);
		strForwardName = "reminderReply";
		return mapping_p.findForward(strForwardName);

	}
	
	/**
	 * To Save Reminder Reply Details
	 * 	 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * @throws Exception 
	 */
	public ActionForward saveReminderReply(ActionMapping mapping_p, ActionForm form_p,HttpServletRequest request_p, HttpServletResponse response_p) throws Exception
	{		
		validateToken(request_p,response_p);
		ComplaintMaintenanceStatusFB formBean = (ComplaintMaintenanceStatusFB)form_p;
		ComplaintMaintenanceStatusUTIL.saveReminderReply(formBean,request_p);
		if("1".equals(formBean.getStrIsHemDesk())) {
			ActionForward acFwd = new ActionForward();
			acFwd.setPath("/bmed/transactions/HemDeskACTION.cnt?hmode=unspecified");
		    acFwd.setContextRelative(true);
	        return acFwd;
		} else {
			return this.unspecified(mapping_p, form_p, request_p, response_p);
		}
		
	}

	public ActionForward saveAttended(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ComplaintLogOfflineFB complaintMaintenanceStatusFB = (ComplaintLogOfflineFB) form;
		ComplaintLogOfflineUTIL.saveAttended(
				complaintMaintenanceStatusFB, request);
		return unspecified(mapping, form, request, response);

	}
	
	public ActionForward initializeClose(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		generateToken(request);
		ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB = (ComplaintMaintenanceStatusFB) form;
		ComplaintMaintenanceStatusUTIL.initializeClose(complaintMaintenanceStatusFB, request);
		strForwardName = "close";
		return mapping.findForward(strForwardName);

	}
	
	public ActionForward saveClose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB = (ComplaintMaintenanceStatusFB) form;
		ComplaintMaintenanceStatusUTIL.saveClose(complaintMaintenanceStatusFB, request);
		return unspecified(mapping, form, request, response);

	}
	
	public ActionForward CANCELTODESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		generateToken(request);
		ActionForward acFwd = new ActionForward();
		String strPath = "";
		if(request.getParameter("strPath")!= null)
		{
			strPath = request.getParameter("strPath").concat("?hmode=CANCEL");
			acFwd.setPath(strPath);
	        acFwd.setContextRelative(true);
	        
		}
		return acFwd;
	}
	
	public ActionForward initializeGrievances(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		generateToken(request);
		ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB = (ComplaintMaintenanceStatusFB) form;
		String strChk = ComplaintMaintenanceStatusUTIL.initializeGrievances(
				complaintMaintenanceStatusFB, request);
		// ComplaintMaintenanceStatusUTIL.saveClose(complaintMaintenanceStatusFB,
		// request);
		
		//ComplaintMaintenanceStatusTransACTION
		//complaintMaintenanceStatusFB.getStrP
//		System.out.println("strChk:::"+strChk);
		String strUrl = "/bmed/transactions/HemDeskACTION.cnt?hmode=GRIEVANCES&cnt_page=/transactions/HemDeskACTION&chk="+strChk+"&strHemDesk=0";
		
		
		ActionForward acFwd = new ActionForward();
		acFwd.setPath(strUrl);
	    acFwd.setContextRelative(true);
        return acFwd;

	}
	
	/**
	 * GETUPLOADEDFILE method is used to get  Uploaded File 
	 *   
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	
	public ActionForward GETUPLOADEDFILE(ActionMapping mapping_p, ActionForm form_p,HttpServletRequest request_p, HttpServletResponse response_p)
	throws Exception 
    {		
		EquipmentInspectionTestDtlsFB fb = (EquipmentInspectionTestDtlsFB) form_p;
		EquipmentInspectionTestDtlsUTIL.getUploadedFile(fb, request_p, response_p);
		return null;
	}

}
