package billing.reports;

import hisglobal.exceptions.HisException;
import ipd.reports.CurrentlyAdmittedPatientRptDATA;
import ipd.reports.CurrentlyAdmittedPatientRptFB;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;

	public class DueDetailsRptCNT extends CSRFGardTokenAction
	{
		public ActionForward unspecified(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException 
	     {
			generateToken(request);

			DueDetailsRptFB formBean = (DueDetailsRptFB) form;
			DueDetailsRptDATA.initReportPage(formBean,request);
		    String target = "reportPage";
			return mapping.findForward(target);
		 }
		
	   	public ActionForward UNITCMB(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws HisException, SQLException 
		    {
				
		     
				DueDetailsRptFB formBean = (DueDetailsRptFB) form;
				formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
				DueDetailsRptDATA.getUNITCMB(formBean,request,response);
				return null;
			 }
			public ActionForward WARDCMB(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws HisException, SQLException 
		    {
				
		     
				DueDetailsRptFB formBean = (DueDetailsRptFB) form;
				formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
				DueDetailsRptDATA.getWARDCMB(formBean,request,response);
				return null;
			}
			public ActionForward DEPTCMB(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws HisException, SQLException {

				DueDetailsRptFB formBean = (DueDetailsRptFB) form;
				formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
				DueDetailsRptDATA.getdeptComboDetails(formBean, request, response);
				return null;
			}
			public ActionForward SHOWRPT(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws HisException, SQLException 
		     {
		     
				DueDetailsRptFB formBean = (DueDetailsRptFB) form;
				//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				DueDetailsRptDATA.showReport(formBean,request,response);
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
			
	