package ipd.reports;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
	
	public class VacantBedRecordRptCNT extends DispatchAction{

		/**
		 * @see org.apache.struts.actions.DispatchAction#unspecified
		 * (org.apache.struts.action.ActionMapping, 
		 * org.apache.struts.action.ActionForm, 
		 * javax.servlet.http.HttpServletRequest, 
		 * javax.servlet.http.HttpServletResponse)
		 */
		public ActionForward unspecified(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

			VacantBedRecordRptFB formBean = (VacantBedRecordRptFB) form;
			//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			VacantBedRecordRptDATA.initReportPage(formBean,request);
				
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

			VacantBedRecordRptFB formBean = (VacantBedRecordRptFB) form;
			//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			VacantBedRecordRptDATA.showReport(formBean, request, response);
			
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
	/////////////////////This Method is used for DeptCombo Values/////////////////////////////
			
	public ActionForward DEPTCMB(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

		VacantBedRecordRptFB formBean = (VacantBedRecordRptFB) form;
			//formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
			VacantBedRecordRptDATA.getdeptComboDetails(formBean,request, response);
			
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

			VacantBedRecordRptFB formBean = (VacantBedRecordRptFB) form;
			//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			VacantBedRecordRptDATA.getUnitWardDetails(formBean,request, response);
			
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

			VacantBedRecordRptFB formBean = (VacantBedRecordRptFB) form;
			//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			VacantBedRecordRptDATA.getWardDetailsCombo(formBean,request, response);
			
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
		/**
		 * @see org.apache.struts.actions.DispatchAction#unspecified
		 * (org.apache.struts.action.ActionMapping, 
		 * org.apache.struts.action.ActionForm, 
		 * javax.servlet.http.HttpServletRequest, 
		 * javax.servlet.http.HttpServletResponse)
		 */
		public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

			VacantBedRecordRptFB formBean = (VacantBedRecordRptFB) form;
			//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			VacantBedRecordRptDATA.initReportPage(formBean,request);
			String target = "reportPage";
			return mapping.findForward(target);
		}
}
