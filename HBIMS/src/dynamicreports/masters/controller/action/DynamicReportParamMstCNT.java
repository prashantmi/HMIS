/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportParamMstCNT.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import dynamicreports.masters.controller.data.DynamicReportParamMstDATA;
import dynamicreports.masters.controller.fb.DynamicReportParamMstFB;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportParamMstCNT.
 */
public class DynamicReportParamMstCNT extends DispatchAction {

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
		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;

		DynamicReportParamMstDATA.init(formBean, request);
		return mapping.findForward(strtarget);
	}

	/**
	 * Getreportlist.
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
	public ActionForward GETREPORTLIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;
		DynamicReportParamMstDATA.getReportList(formBean, request, response);

		return null;

	}

	/**
	 * Getprocinparamlist.
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
	public ActionForward GETPROCINPARAMLIST(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;
		DynamicReportParamMstDATA.getProcInParamList(formBean, request,
				response);

		return null;

	}

	/**
	 * Getmergeinparamlist.
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
	public ActionForward GETMERGEINPARAMLIST(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;
		DynamicReportParamMstDATA.getMergeProcPreInParamList(formBean, request,
				response);

		return null;

	}

	/**
	 * Getddinparamlist.
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
	public ActionForward GETDDINPARAMLIST(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;
		DynamicReportParamMstDATA.getDrillDownProcPreInParamList(formBean,
				request, response);

		return null;

	}

	/**
	 * Getprerptdtl.
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
	public ActionForward GETPRERPTDTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;
		DynamicReportParamMstDATA.getPreReportDtls(formBean, request, response);

		return null;

	}

	/**
	 * Rowbasedrpt.
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
	public ActionForward ROWBASEDRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		String strtarget = "rowbased";
		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;

		DynamicReportParamMstDATA.rowBasedReport(formBean, request);
		return mapping.findForward(strtarget);
	}

	/**
	 * Columnbasedrpt.
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
	public ActionForward COLUMNBASEDRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		String strtarget = "colbased";
		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;

		DynamicReportParamMstDATA.colBasedReport(formBean, request);
		return mapping.findForward(strtarget);
	}

	/**
	 * Drilldownrpt.
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
	public ActionForward DRILLDOWNRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		String strtarget = "drilldown";
		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;

		DynamicReportParamMstDATA.drillDownReport(formBean, request);
		return mapping.findForward(strtarget);
	}

	/**
	 * Getinoutcombo.
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
	public ActionForward GETINOUTCOMBO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;
		DynamicReportParamMstDATA.getProcInOutParamCombo(formBean, request,
				response);

		return null;

	}

	/**
	 * Mergerpt.
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
	public ActionForward MERGERPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		String strtarget = "merge";
		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;

		DynamicReportParamMstDATA.mergeReport(formBean, request);
		return mapping.findForward(strtarget);
	}

	/**
	 * Rowbasesave.
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
	public ActionForward ROWBASESAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;

		DynamicReportParamMstDATA.saveRowBasedRptData(formBean, request);

		return this.INIT(mapping, formBean, request, response);
	}

	/**
	 * Colbasesave.
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
	public ActionForward COLBASESAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;

		DynamicReportParamMstDATA.saveColBasedRptData(formBean, request);

		return this.INIT(mapping, formBean, request, response);
	}

	/**
	 * Drilldownsave.
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
	public ActionForward DRILLDOWNSAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;

		DynamicReportParamMstDATA.saveDrillDownRptData(formBean, request);

		return this.INIT(mapping, formBean, request, response);
	}

	/**
	 * Mergesave.
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
	public ActionForward MERGESAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;

		DynamicReportParamMstDATA.saveMergeRptData(formBean, request);

		return this.INIT(mapping, formBean, request, response);
	}

	/**
	 * Cancelrpt.
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
	public ActionForward CANCELRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;

		DynamicReportParamMstDATA.cancelReport(formBean, request, response);

		return null;
	}

	/**
	 * Viewrpt.
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
	public ActionForward VIEWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		DynamicReportParamMstFB formBean = (DynamicReportParamMstFB) form;

		DynamicReportParamMstDATA.viewReport(formBean, request, response);

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
