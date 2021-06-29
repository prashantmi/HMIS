package billing.reports;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;


public class DiscountDetailsRptBSCNT extends CSRFGardTokenAction {
	
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

		DiscountDetailsRptFB formBean = (DiscountDetailsRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		DiscountDetailsRptDATA.initReportPage(formBean,request);
		
		DiscountDetailsRptDATA.getCategoryDetails(formBean, request, response);
		//DiscountDetailsRptDATA.getConsultDetails(formBean, request, response);
		//DiscountDetailsRptDATA.getReasonDetails(formBean, request, response);

		
				String target = "reportPage";
				
			return mapping.findForward(target);
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws HisException
	 * @throws SQLException
	 */
//////////////////////This method is used for group combo values//////////////////
	
	public void CONSULTANTCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DiscountDetailsRptFB formBean = (DiscountDetailsRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		DiscountDetailsRptDATA.getConsultDetails(formBean, request, response);
		
		
	}
	
	
	public void GROUPCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DiscountDetailsRptFB formBean = (DiscountDetailsRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		DiscountDetailsRptDATA.getGroupDetails(formBean, request, response);
		
		
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws HisException
	 * @throws SQLException
	 */
//////////////////////This method is used for group combo values//////////////////
	
	public void TARIFFCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DiscountDetailsRptFB formBean = (DiscountDetailsRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		DiscountDetailsRptDATA.getTariffDetails(formBean, request, response);
		
		
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DiscountDetailsRptFB formBean = (DiscountDetailsRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		DiscountDetailsRptDATA.showReport(formBean, request, response);
		
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
	    ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
}
