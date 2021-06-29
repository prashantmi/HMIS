package mms.reports.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.reports.controller.data.BreakageAndLostDrugDetailsRptDATA;
import mms.reports.controller.fb.BreakageAndLostDrugDetailsRptFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class BreakageAndLostDrugDetailsRptCNT extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		return this.INITVAL(mapping, form, request, response);
		
	}
	
	
	public ActionForward INITVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		

		String strTarget = "reportPage";
		BreakageAndLostDrugDetailsRptFB formBean = (BreakageAndLostDrugDetailsRptFB) form;
		
		BreakageAndLostDrugDetailsRptDATA.getInitializedValues(formBean,request, response);
		return mapping.findForward(strTarget);
	}
	
	public ActionForward ITEMCATCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		BreakageAndLostDrugDetailsRptFB formBean = (BreakageAndLostDrugDetailsRptFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BreakageAndLostDrugDetailsRptDATA.getItemCatList(formBean,request, response);
		return null;
	}
	
	/*public ActionForward STORECMB(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws HisException, SQLException 
	{
		
		BreakageAndLostDrugDetailsRptFB formBean = (BreakageAndLostDrugDetailsRptFB) form;
		BreakageAndLostDrugDetailsRptDATA.getStoreList(formBean,request, response);
		return null;
	}*/
	
	
	
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		BreakageAndLostDrugDetailsRptFB formBean = (BreakageAndLostDrugDetailsRptFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BreakageAndLostDrugDetailsRptDATA.showReport(formBean, request, response);
		
		
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

