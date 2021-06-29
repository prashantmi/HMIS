package ipd.reports;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientDiagnosisRptCNT extends DispatchAction {

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

		PatientDiagnosisRptFB formBean = (PatientDiagnosisRptFB) form;
		formBean.setStrHospCode((String)request.getSession().getAttribute("HOSPITAL_CODE"));
		PatientDiagnosisRptDATA.initReportPage(formBean);
			
				String target = "reportPage";
				
			return mapping.findForward(target);
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
/////////////////////This Method is used for showing report/////////////////////////////
	
	public ActionForward SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		PatientDiagnosisRptFB formBean = (PatientDiagnosisRptFB) form;
		formBean.setStrHospCode((String)request.getSession().getAttribute("HOSPITAL_CODE"));
		PatientDiagnosisRptDATA.showReport(formBean, request, response);
		
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
/////////////////////This Method is used for Unit Combo Values/////////////////////////////
	
	public ActionForward UNITCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		PatientDiagnosisRptFB formBean = (PatientDiagnosisRptFB) form;
		formBean.setStrHospCode((String)request.getSession().getAttribute("HOSPITAL_CODE"));
		PatientDiagnosisRptDATA.getUnitDetails(formBean,request, response);
		
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
		PatientDiagnosisRptFB formBean = (PatientDiagnosisRptFB) form;
		formBean.setStrHospCode((String)request.getSession().getAttribute("HOSPITAL_CODE"));
		PatientDiagnosisRptDATA.getWardDetails(formBean,request, response);
		
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
/////////////////////This Method is used for Room Combo Values/////////////////////////////
	
	public ActionForward ROOMCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		PatientDiagnosisRptFB formBean = (PatientDiagnosisRptFB) form;
		formBean.setStrHospCode((String)request.getSession().getAttribute("HOSPITAL_CODE"));
		PatientDiagnosisRptDATA.getRoomDetails(formBean,request, response);
		
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
}
