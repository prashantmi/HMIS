package mms.global.controller;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

// TODO: Auto-generated Javadoc
/**
 * The Class MmsCNT.
 */
public class MmsCNT extends DispatchAction {

	/**
	 * ITEMPARAMUTLINIT.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMPARAMUTLINIT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.initItemParam(request, response, formBean);

		return null;
	}
	
	public ActionForward ITEMPARAMUTLINITNEW(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.initItemParamNEW(request, response, formBean);

		return null;
	}

	/**
	 * ITEMPARAMDTL.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMPARAMDTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.initItemParamDtl(request, response, formBean);

		return null;
	}

	/**
	 * ITEMEXEPARAMDTL.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMEXEPARAMDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.initItemExistingParam(request, response, formBean);

		return null;
	}

	/**
	 * ITEMPARAMUTLMODIFY.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMPARAMUTLMODIFY(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.initItemParam(request, response, formBean);

		return null;
	}

	/**
	 * ITEMPARAMDTLMODI.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMPARAMDTLMODI(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.initExistingItemParamDtl(request, response, formBean);

		return null;
	}

	/**
	 * ITEMPARAMUTLDISPLAY.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMPARAMUTLDISPLAY(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.initDisplayItemParam(request, response, formBean, "", "");

		return null;
	}

	/**
	 * ITEMPARAMDTLDISPLAY.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMPARAMDTLDISPLAY(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.initDisplayItemParamDtl(request, response, formBean);

		return null;
	}

	/**
	 * ITEMPARAMDTLDISPLAY.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMPARAMDTLDISPLAYFORVIEW(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.initDisplayItemParamDtlForView(request, response, formBean);

		return null;
	}

	/**
	 * INSERT.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.insert(request, formBean);

		return null;
	}

	/**
	 * UPDATE.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.update(request, formBean);

		return null;
	}

	/**
	 * SEARCHITEMINIT.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward SEARCHITEMINIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;
//Used To Get Group List
		MmsDATA.searchItemInit(request, response, formBean);

		return null;
	}

	/**
	 * GETSUBGROUPLIST.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETSUBGROUPLIST(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getSubGroupAndGenericItems(request, response, formBean);

		return null;
	}

	/**
	 * GETITEMLIST.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETITEMLIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.searchItemList(request, response, formBean);

		return null;
	}

	/**
	 * GETITEMBRANDLIST.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETITEMBRANDLIST(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.searchItemBrandList(request, response, formBean);

		return null;
	}

	/**
	 * GETUNITCOMBO.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETUNITCOMBO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getUnitComboList(request, response, formBean);

		return null;
	}

	/**
	 * GETITEMMANDATORYDTLS.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETITEMMANDATORYDTLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsDATA.getItemMandatoryDetails(request, response);

		return null;
	}

	/**
	 * MMSLISTING.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward MMSLISTING(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String target = "mmslist";
		MmsFB formBean = (MmsFB) form;
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
		formBean.setUsrArg(request.getParameter("mmsList"));
		formBean.setUsrFuncName(request.getParameter("usrFuncName"));
		// System.out.println("here");
		return mapping.findForward(target);
	}

	/**
	 * FETCHMMSLISTING.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward FETCHMMSLISTING(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;
		MmsDATA.getMmsListingDtls(request, response, formBean);

		return null;
	}

	/**
	 * STOCKDTLSINIT - gets the Item & Drug Stock Details view used for ajax.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward STOCKDTLSINIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.stockItemDtlsInit(request, response, formBean);

		return null;
	}

	/**
	 * ISSUEDTLSINIT - gets the issue details view used for ajax.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ISSUEDTLSINIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.issueDtlsInit(request, response, formBean);

		return null;
	}
	
	public ActionForward ISSUEDTLSINITNEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.issueDtlsInitNEW(request, response, formBean);

		return null;
	}
	
	public ActionForward DUPLICATEISSUEDTLSINITNEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.duplicateIssueDtlsInitNEW(request, response, formBean);

		return null;
	}
	
	public ActionForward ISSUEDTLSINITSERVICE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.issueDtlsInitService(request, response, formBean);

		return null;
	}
	

	/**
	 * FREEITEMDTLS - gets free item details view used for ajax.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward FREEITEMDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getFreeItemDtls(request, response, formBean);

		return null;
	}

	/**
	 * PARTITEMDTLS - gets part item details view used for ajax.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward PARTITEMDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getPartItemDtls(request, response, formBean);

		return null;
	}

	/**
	 * WARRANTYDTLS - gets item warranty details view used for ajax.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward WARRANTYDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getItemWarrantyDtls(request, response, formBean);

		return null;
	}

	/**
	 * ITEMPARAMDTLS - gets item parameter details view in a special mode where
	 * popup name is specified.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMPARAMDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.initDisplayItemParam(request, response, formBean, "2",
				"itemOtherDtlsDiv");

		return null;
	}

	/**
	 * ITEMOTHERDTLS.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMOTHERDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getInitItemOtherDtls(request, response, formBean);

		return null;
	}

	
	
	public ActionForward ITEMOTHERDTLSNEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getInitItemOtherDtlsNEW(request, response, formBean);

		return null;
	}
	/**
	 * ITEMOTHERGROUPDTLS.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMOTHERGROUPDTLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getGroupList(request, response, formBean);

		return null;
	}

	/**
	 * ITEMOTHERSUBGROUPDTLS.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMOTHERSUBGROUPDTLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getSubGroupList(request, response, formBean);

		return null;
	}

	/**
	 * ITEMOTHERGENITEMDTLS.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMOTHERGENITEMDTLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getGenItemList(request, response, formBean);

		return null;
	}

	/**
	 * ITEMOTHERITEMDTLS.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ITEMOTHERITEMDTLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getItemList(request, response, formBean);

		return null;
	}

	/**
	 * GETOTHERITEMUNITCOMBO.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETOTHERITEMUNITCOMBO(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getOtherItemUnitComboList(request, response, formBean);

		return null;
	}

	/**
	 * GETOTHERITEMMODIVIEW.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETOTHERITEMMODIVIEW(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getOtherItemModifyInitView(request, response, formBean);

		return null;
	}

	/**
	 * GETMANUFCOMBO.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETMANUFCOMBO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getManufacturerComboList(request, response, formBean);

		return null;
	}

	/**
	 * GETADDNEWITEM - get the view of Add
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETADDNEWITEM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.getAddNewItemView(request, response, formBean);

		return null;
	}

	/**
	 * SAVENEWITEMDTLS - save new item details
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward SAVENEWITEMDTLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		MmsFB formBean = (MmsFB) form;

		MmsDATA.insertAddNewItemView(request, response, formBean);

		return null;
	}


}
