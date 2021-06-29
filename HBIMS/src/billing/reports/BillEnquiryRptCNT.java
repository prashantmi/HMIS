package billing.reports;
/*
 * Bill Enquiry Report ACTION
 * 
 * author: Debashis Sardar
 * 
 * dated: 05/03/2012
 */
import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;


public class BillEnquiryRptCNT extends CSRFGardTokenAction{

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.DispatchAction#unspecified
	 * (org.apache.struts.action.ActionMapping,
	 *  org.apache.struts.action.ActionForm, 
	 *  javax.servlet.http.HttpServletRequest, 
	 *  javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
		generateToken(request);

		BillEnquiryRptFB formBean = (BillEnquiryRptFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillEnquiryRptDATA.getBillTypeDtls(formBean,request,response);
		formBean.setStrReportFormat("HTML");		
		String target = "reportPage";
				
			return mapping.findForward(target);
	}
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
		generateToken(request);

		BillEnquiryRptFB formBean = (BillEnquiryRptFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillEnquiryRptDATA.getBillDetails(formBean,request,response);
		
		return null;
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
	    ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		BillEnquiryRptFB formBean = (BillEnquiryRptFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillEnquiryRptDATA.showReport(formBean, request, response);
	}
	public ActionForward GENERATEREPORT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		BillEnquiryRptFB formBean = (BillEnquiryRptFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillEnquiryRptDATA.generateReport(formBean, request, response);
		
		String target = "reportForm";
		
		return mapping.findForward(target);
		
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
		
		BillEnquiryRptFB formBean = (BillEnquiryRptFB) form;
		formBean.reset( mapping, request);
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillEnquiryRptDATA.getBillTypeDtls(formBean,request,response);
		
		return this.unspecified(mapping, form, request, response);	/*String target = "reportPage";
				
			return mapping.findForward(target);*/
	}
	
	// returns report data in html format..
	public ActionForward RETURNHTML(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		BillEnquiryRptFB formBean = (BillEnquiryRptFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillEnquiryRptDATA.returnHTML(formBean, request, response);
		
		return null;
	}
	public ActionForward SHOWCONSOLIDATEDCREDITREPORT(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		BillEnquiryRptFB formBean = (BillEnquiryRptFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillEnquiryRptDATA.showConsolidatedCreditReport(formBean, request, response);		
		return null;		
	}
}
