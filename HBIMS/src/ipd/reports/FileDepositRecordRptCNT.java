package ipd.reports;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class FileDepositRecordRptCNT extends DispatchAction{

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.DispatchAction#unspecified
	 * (org.apache.struts.action.ActionMapping, 
	 * org.apache.struts.action.ActionForm, 
	 * javax.servlet.http.HttpServletRequest, 
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		FileDepositRecordRptFB formBean = (FileDepositRecordRptFB) form;
		formBean.setStrHospCode((String)request.getSession().getAttribute("HOSPITAL_CODE"));
		FileDepositRecordRptDATA.initReportPage(formBean);
		
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
/////////////////////This Method is used for showing Report/////////////////////////////
	
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		FileDepositRecordRptFB formBean = (FileDepositRecordRptFB) form;
		formBean.setStrHospCode((String)request.getSession().getAttribute("HOSPITAL_CODE"));
		FileDepositRecordRptDATA.showReport(formBean, request, response);
			
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
/////////////////////This Method is used for Unit Combo Values/////////////////////////////
	
	public ActionForward UNITCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		FileDepositRecordRptFB formBean = (FileDepositRecordRptFB) form;
		formBean.setStrHospCode((String)request.getSession().getAttribute("HOSPITAL_CODE"));
		FileDepositRecordRptDATA.getUnitDetails(formBean,request, response);
		
		return null;
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
/////////////////////This Method is used for Ward Combo Values/////////////////////////////
	
	public ActionForward WARDCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		//System.out.println("inside ward");
		FileDepositRecordRptFB formBean = (FileDepositRecordRptFB) form;
		formBean.setStrHospCode((String)request.getSession().getAttribute("HOSPITAL_CODE"));
		FileDepositRecordRptDATA.getWardDetails(formBean,request, response);
		
		return null;
	}
	
}
