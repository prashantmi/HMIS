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

import bmed.transactions.controller.fb.OfflineSparePartAddTransFB;
import bmed.transactions.controller.util.OfflineSparePartAddTransUTIL;

/**
 * @author Vivek Aggarwal  
 * Creation Date:- 10-May-2011 
 * Modifying Date:- 11-May-2011
 * Used For:- 
 * Team Lead By:-  
 *  Module:- BMED(HIS Project)
 * 
 */
public class OfflineSparePartAddTransACTION extends CSRFGardTokenAction {
	
	private String strForwardName;

	
	/**
	 * To initialize Offline Spare Part Add
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 */
	
	public ActionForward unspecified(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response) {
		generateToken(request_p);		
		OfflineSparePartAddTransFB offlineSparePartAddTransFB = (OfflineSparePartAddTransFB) form_p;
		OfflineSparePartAddTransUTIL.initializeMain(offlineSparePartAddTransFB,request_p);
		strForwardName="main";
		return mapping_p.findForward(strForwardName);

	}
	
	/**
	 * To get STORE on basis of Department.
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 */
	public ActionForward GETSTORENAME(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException,SQLException 
	{		
		OfflineSparePartAddTransFB offlineSparePartAddTransFB = (OfflineSparePartAddTransFB) form_p;
		OfflineSparePartAddTransUTIL.getStoreName(offlineSparePartAddTransFB,request_p,response_p);
		return null;
	}
	
	/**
	 * Get Engg Item Sub Type Combo using Ajax. 
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the null
	 */
	public ActionForward ENGGITEMSUBTYPECMB(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
	{

		OfflineSparePartAddTransFB offlineSparePartAddTransFB = (OfflineSparePartAddTransFB) form_p;
		OfflineSparePartAddTransUTIL.getEnggItemSubTypeCmb(offlineSparePartAddTransFB, request_p, response_p);
		
		return null;
	}
	
	
	/**
	 * To  GET ITEM CATEGORY NAME on basis of Store.
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 */
	public ActionForward GETITEMCATEGORYNAME(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException,SQLException 
	{		
		OfflineSparePartAddTransFB offlineSparePartAddTransFB = (OfflineSparePartAddTransFB) form_p;
		OfflineSparePartAddTransUTIL.getItemCategoryName(offlineSparePartAddTransFB,request_p,response_p);
		return null;
	}
	
	
	/**
	 * Get Item Name on basis of Item Category.
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 */
	public ActionForward GETITEMNAME(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		OfflineSparePartAddTransFB offlineSparePartAddTransFB = (OfflineSparePartAddTransFB) form_p;
		OfflineSparePartAddTransUTIL.getItemName(offlineSparePartAddTransFB,request_p,response_p);
		
		return null;
	}
	
	/**
	 *  to GET STOCK DTL .
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 */
	public ActionForward GETSTOCKDTL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		OfflineSparePartAddTransFB offlineSparePartAddTransFB = (OfflineSparePartAddTransFB) form_p;
		OfflineSparePartAddTransUTIL.getStockDetails(offlineSparePartAddTransFB,request_p,response_p);
		return null;
	}
	
	
	/**
	 * Get Item Name on basis of Item Category.
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 */
	public ActionForward SPAREPARTSTOCKDETAILS(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException 
	{		
		OfflineSparePartAddTransFB offlineSparePartAddTransFB = (OfflineSparePartAddTransFB) form_p;
		OfflineSparePartAddTransUTIL.getSparePartStockDetails(offlineSparePartAddTransFB,request_p,response_p);
		
		return null;
	}
	
	/**
	 * To  GET SPARE PART NAME on basis of Item Name.
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 */
	public ActionForward GETSPAREPARTNAME(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException,SQLException 
	{		
		OfflineSparePartAddTransFB offlineSparePartAddTransFB = (OfflineSparePartAddTransFB) form_p;
		OfflineSparePartAddTransUTIL.getSparePartName(offlineSparePartAddTransFB,request_p,response_p);
		return null;
	}
		
	
	/**
	 * To  GET SPARE PART NAME on basis of Item Name.
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 */
	
	public ActionForward DELETESPAREPARTSTOCKDTL(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException,SQLException 
	{		
		OfflineSparePartAddTransFB offlineSparePartAddTransFB = (OfflineSparePartAddTransFB) form_p;
		OfflineSparePartAddTransUTIL.deleteSparePartStockDtl(offlineSparePartAddTransFB,request_p,response_p);
		return null;
	}
	
	/**
	 *  to save Data .
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 */
	public ActionForward SAVE(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws Exception 
	{		
		validateToken(request_p,response_p);
		OfflineSparePartAddTransFB offlineSparePartAddTransFB = (OfflineSparePartAddTransFB) form_p;
		OfflineSparePartAddTransUTIL.saveData(offlineSparePartAddTransFB,request_p);
		return this.unspecified(mapping_p, offlineSparePartAddTransFB, request_p, response_p);
	}
	
	
	/**
	 * forwards control to the Home Page.
	 * 
	 * @param mapping_p the ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response the HttpServletResponse
	 * 
	 * @return the action forward
	 */

	public ActionForward CANCEL(ActionMapping mapping_p, ActionForm form_p,	HttpServletRequest request_p, HttpServletResponse response) 
	{
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
}
