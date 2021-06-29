package bmed.reports.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bmed.reports.controller.fb.EquipmentInspectionOrTestReportFB;
import bmed.reports.controller.util.EquipmentInspectionOrTestReportUTIL;

/**
 * @author   Adil Wasi
 * Creation Date:- 03-Sept-2012
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) Equipment Inspection/Test Report
 * 
 */
public class EquipmentInspectionOrTestReportACTION extends DispatchAction 
{
	 /**
	 * Unspecified method
	 * 
	 * @param mapping_p the	ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 * 
	 * @return	ActionForward
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward unspecified(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException, SQLException {

		return this.INITDETAIL(mapping_p, form_p, request_p, response_p);

	}
	
	/**
	 *	This function is used forward control to main  page
	 * 
	 * @param mapping_p the	ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 * 
	 * @return	ActionForward
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
		public ActionForward INITDETAIL(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException, SQLException {

		String target="index";
		EquipmentInspectionOrTestReportFB formBean = (EquipmentInspectionOrTestReportFB) form_p;
		EquipmentInspectionOrTestReportUTIL.setInitDtl(formBean, request_p);
		return mapping_p.findForward(target);

	}
	
		/**
		 * GETITEMNAMECOMBO Method is used to get Item Name for Equipment Name Combo.
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
		public ActionForward GETITEMNAMECOMBO(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws HisException 
		{		
			EquipmentInspectionOrTestReportFB formBean_p = (EquipmentInspectionOrTestReportFB)form_p;
			EquipmentInspectionOrTestReportUTIL.getItemNameCombo(formBean_p,request_p,response_p);
			return null;
		}	
		
		
		/**
		 * GETEQUIPMENTTESTNAMECOMBO Method is used to get EQUIPMENT TEST NAME COMBO
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
		public ActionForward GETEQUIPMENTTESTNAMECOMBO(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws HisException 
		{		
			EquipmentInspectionOrTestReportFB formBean_p = (EquipmentInspectionOrTestReportFB)form_p;
			EquipmentInspectionOrTestReportUTIL.getEquipmentTestCombo(formBean_p,request_p,response_p);
			return null;
		}
		
		
		/**
		 * GETTESTNAMECOMBO Method is used to get TEST NAME COMBO
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
		/*public ActionForward GETTESTNAMECOMBO(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws HisException 
		{		
			EquipmentInspectionOrTestReportFB formBean_p = (EquipmentInspectionOrTestReportFB)form_p;
			EquipmentInspectionOrTestReportUTIL.setTestDtl(formBean_p,request_p,response_p);
			return null;
		}*/
	/**
	 * To Show Report
	 * 
	 * @param mapping_p the	ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	public void SHOWRPT(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException, SQLException {

		EquipmentInspectionOrTestReportFB formBean = (EquipmentInspectionOrTestReportFB) form_p;
		formBean.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
		EquipmentInspectionOrTestReportUTIL.showReport(formBean, request_p, response_p);
		
		
	}
	
	/**
	 * To Cancel 
	 * 
	 * @param mapping_p the	ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 * 
	 * @return	ActionForward
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward CANCEL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			 {
			  ActionForward acFwd = new ActionForward();
			  acFwd.setPath("/hisglobal/initPage.jsp");
			  acFwd.setContextRelative(true);
			  return acFwd;
			 }
}
