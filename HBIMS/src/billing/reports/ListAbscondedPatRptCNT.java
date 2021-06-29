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


	public class ListAbscondedPatRptCNT extends CSRFGardTokenAction{
		
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

			ListAbscondedPatRptFB formBean = (ListAbscondedPatRptFB) form;
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			ListAbscondedPatRptDATA.initReportPage(formBean);
			
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
//////////////////This method is used for showing report/////////////////////

		public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
	
			ListAbscondedPatRptFB formBean = (ListAbscondedPatRptFB) form;
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			ListAbscondedPatRptDATA.showReport(formBean, request, response);
			
		
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
//////////////////This method is used for Unit combo values/////////////////////
		
		public ActionForward UNITCMB(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

			ListAbscondedPatRptFB formBean = (ListAbscondedPatRptFB) form;
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			ListAbscondedPatRptDATA.getUnitDetails(formBean,request, response);
			
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
//////////////////This method is used for Ward combo values/////////////////////
		
		public ActionForward WARDCMB(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

		
			ListAbscondedPatRptFB formBean = (ListAbscondedPatRptFB) form;
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			ListAbscondedPatRptDATA.getWardDetails(formBean,request, response);
			
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
