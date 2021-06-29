package bmed.transactions.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bmed.transactions.controller.fb.ItemMaintContractDtlsFB;
import bmed.transactions.controller.util.ItemMaintContractDtlsUTIL;
/**
 * @author   Amit kr
 * Creation Date:- 18-April-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project): Item  Non-Item Maintenance Details Transactions
 * 
 */
public class ItemMaintContractDtlsACTION extends CSRFGardTokenAction {
	
String strTarget = "";
	
	/**
	 * forwards control to the ADD page.
	 * 
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
	public ActionForward unspecified(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		generateToken(request_p);
		ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.initializeMain(formBean_p,request_p);
		String strTarget = "item";
		return mapping_p.findForward(strTarget);
	}
	
	
	/**
	 * GETSTORENAME method is used to get Store Name by using 
	 * Ajax  
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
	public ActionForward GETSTORENAME(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.getStoreName(formBean_p,request_p,response_p);
		return null;
	}
	
	
	/**
	 * GETENGGITEMSUBTYPE method is used to get Engineering Item Sub Type by using 
	 * Ajax  
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
	public ActionForward GETENGGITEMSUBTYPE(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.getEnggItemSubType(formBean_p,request_p,response_p);
		return null;
	}
	
	/**
	 * GETITEMNAME method is used to get  Item   by using 
	 * Ajax  
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
	public ActionForward GETITEMNAME(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.getItemName(formBean_p,request_p,response_p);
		return null;
	}
	
	
	/**
	 * GETSTOCKDTL method is used to get  Stock Details   by using 
	 * Ajax  
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
	public ActionForward GETSTOCKDTL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.getStockDetails(formBean_p,request_p,response_p);
		return null;
	}
	
	/**
	 * GETPREVMANTDTL method is used to get  Preventive Maintenance Details   by using 
	 * Ajax  
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
	public ActionForward GETPREVMANTDTL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.getPrevMantDetails(formBean_p,request_p,response_p);
		return null;
	}
	
	
	
	/**
	 * GETUPLOADEDFILE method is used to get  Upload File   by using 
	 *   
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
	
	public ActionForward GETUPLOADEDFILE(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
	throws HisException 
    {		
        ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
        ItemMaintContractDtlsUTIL.getUploadedFile(formBean_p, request_p, response_p);
		return null;
	}
	
	/**
	 * GETSUPPLIER method is used to get Supplier Name using Ajax
	 * 
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
	public ActionForward GETSUPPLIER(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.getSupplierCmb(formBean_p,request_p,response_p);
		return null;
	}
	
	
	
	/**
	 * MANTCANCEL method is used to cancel record on the basis of Ajax
	 * 
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
	public ActionForward MANTCANCEL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.getCommanAction(formBean_p,request_p,response_p);
		return null;
		//return this.unspecified(mapping_p, form_p, request_p, response_p);
	}
	
	/**
	 * MANTVIEW used to get view on basis of Ajax
	 * 
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
	public ActionForward MANTVIEW(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.getCommanAction(formBean_p,request_p,response_p);
		return null;
		//return this.unspecified(mapping_p, form_p, request_p, response_p);
	}
	
	/**
	 * GETRENEWPAGE method is used to get Renew Selected Combos 
	 * 
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
	public ActionForward GETRENEWPAGE(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.getRenewSelectedCombo(formBean_p,request_p,response_p);
		return null;
	}
	
	/**
	 * SAVECANCEL method is used to save Cancel Information
	 * 
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
	public ActionForward SAVECANCEL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws Exception 
	{		
		validateToken(request_p,response_p);
		ItemMaintContractDtlsFB formBean_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.cancelData(formBean_p,request_p,response_p);
		return null;
	}
		
	
	/**
	 * SAVE method is used to save data in HEMT_ITEM_MC_DTL table 
	 * 
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
	public ActionForward SAVE(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws Exception 
	{		
		validateToken(request_p,response_p);
		ItemMaintContractDtlsFB formBean_p_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.saveData(formBean_p_p,request_p);
		return this.unspecified(mapping_p, form_p, request_p, response_p);
	}
	
	
	/**
	 * RENEW method is used to save renew data in HEMT_ITEM_MC_DTL table 
	 * 
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
	public ActionForward RENEW(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		generateToken(request_p);
		ItemMaintContractDtlsFB formBean_p_p = (ItemMaintContractDtlsFB)form_p;
		ItemMaintContractDtlsUTIL.saveRenewData(formBean_p_p,request_p);
		return this.unspecified(mapping_p, form_p, request_p, response_p);
	}
	/**
	 * forwards control to the Init page.
	 * 
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
	
	public ActionForward CANCEL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{
		generateToken(request_p);
	    ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	
}