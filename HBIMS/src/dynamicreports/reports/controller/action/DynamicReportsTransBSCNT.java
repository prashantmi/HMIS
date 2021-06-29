/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportsTransCNT.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.reports.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import dynamicreports.reports.controller.data.DynamicReportsTransDATA;
import dynamicreports.reports.controller.fb.DynamicReportsTransFB;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportsTransCNT.
 */
public class DynamicReportsTransBSCNT extends DispatchAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts
	 * .action.ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return this.INIT(mapping, form, request, response);

	}

	/**
	 * Inits the.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 * @throws Exception
	 *             the exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward INIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		String strtarget = "init";

		String strReportTemplateId = "0";

		DynamicReportsTransFB formBean = (DynamicReportsTransFB) form;

		if (request.getParameter("strReportId") != null
				&& request.getParameter("strReportId").toString().trim()
						.length() > 1)
			strReportTemplateId = request.getParameter("strReportId")
					.toString();

		formBean.setStrReportTemplateId(strReportTemplateId);
		formBean.setStrReportHiddenId(strReportTemplateId);

		DynamicReportsTransDATA.init(formBean, request);

		return mapping.findForward(strtarget);
	}

	/*
	 * public ActionForward GETREPORTLIST(ActionMapping mapping, ActionForm
	 * form, HttpServletRequest request, HttpServletResponse response) throws
	 * HisException, SQLException {
	 * 
	 * DynamicReportsTransFB formBean = (DynamicReportsTransFB) form;
	 * 
	 * DynamicReportsTransDATA.getReportList(formBean, request, response);
	 * 
	 * return null;
	 * 
	 * }
	 */

	/**
	 * Getparamindtl.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETPARAMINDTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DynamicReportsTransFB formBean = (DynamicReportsTransFB) form;

		DynamicReportsTransDATA.getProcAndInOutParamDtls_BS(formBean, request,
				response);

		return null;

	}

	/**
	 * Getcombos.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETCOMBOS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DynamicReportsTransFB formBean = (DynamicReportsTransFB) form;

		DynamicReportsTransDATA.getComboDtls(request, response, formBean);

		return null;
	}

	/**
	 * Generatereport.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GENERATEREPORT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DynamicReportsTransFB formBean = (DynamicReportsTransFB) form;

		if (formBean.getStrReportTypeId().equals("1")
				|| formBean.getStrReportTypeId().equals("2")
				|| formBean.getStrReportTypeId().equals("3")) {

			DynamicReportsTransDATA
					.generateRecords(request, response, formBean);

		} else {

			DynamicReportsTransDATA.generateMergeRecords(request, response,
					formBean);

		}

		if (formBean.getStrExportType().equals("html")) {

			return mapping.findForward("reportPage");

		}
		else if (formBean.getStrExportType().equals("excel")) {

			return mapping.findForward("reportPage");

		}

		
		else {
			return null;
		}

	}

	/**
	 * Getdrilldownrpt.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETDRILLDOWNRPT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		DynamicReportsTransFB formBean = (DynamicReportsTransFB) form;

		DynamicReportsTransDATA.generateDrillDownRecords(request, response,
				formBean);

		return null;
	}

	/**
	 * Forwards Control to the menu Page.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	public ActionForward CANCELPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}

}
