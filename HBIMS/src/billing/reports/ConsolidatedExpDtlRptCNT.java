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


	public class ConsolidatedExpDtlRptCNT extends CSRFGardTokenAction {
		
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

			ConsolidatedExpDtlRptFB formBean = (ConsolidatedExpDtlRptFB) form;
			//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			ConsolidatedExpDtlRptDATA.initReportPage(formBean,request);
		 
					
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
		public void SHOWRPT(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

			ConsolidatedExpDtlRptFB formBean = (ConsolidatedExpDtlRptFB) form;
			//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			ConsolidatedExpDtlRptDATA.showReport(formBean, request, response);
			
			
		}
		
		public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
		{
		    ActionForward acFwd = new ActionForward();
			acFwd.setPath("/hisglobal/initPage.jsp");
			acFwd.setContextRelative(true);
			return acFwd;
		}
		
		public ActionForward TARIFFNAME(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException 
	   {
	     
			ConsolidatedExpDtlRptFB formBean = (ConsolidatedExpDtlRptFB) form;
			//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			ConsolidatedExpDtlRptDATA.getTariffName(formBean,request,response);
			return null;
		}
		public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException 
	   {
	     
			ConsolidatedExpDtlRptFB formBean = (ConsolidatedExpDtlRptFB) form;
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			ConsolidatedExpDtlRptDATA.initReportPage(formBean,request);
		 
					
					String target = "reportPage";
					
				return mapping.findForward(target);
		}


}
