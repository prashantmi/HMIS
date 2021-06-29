package bmed.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import bmed.masters.controller.utl.NonItemMstUTL;
import bmed.masters.controller.data.NonItemMstDATA;
import bmed.masters.controller.fb.NonItemMstFB;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Vivek Aggarwal  
 * Creation Date:- 11-April-2011 
 * Modifying Date:- 13-April-2011 
 *  Module:- BMED(HIS Project)
 * 
 */
public class NonItemMstCNT extends GenericController  
{

		/** The strTarget. */
		String strTarget = "";

		/**
		 * calls super class constructor.
		 */
		 public NonItemMstCNT() 
	    {
	    	super(new NonItemMstUTL(), "/masters/NonItemMstCNT");
	    }
	    	    
	    
	    /**
		 * forwards control to the ADD page.
		 * 
		 * @param mapping_p the mapping
		 * @param form_p the form
		 * @param request_p the request
		 * @param response_p the response
		 * 
		 * @return the action forward
		 * 
		 * @throws HisException the his exception
		 * @throws SQLException the SQL exception
		 */
		public ActionForward ADD(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws HisException, Exception {
			generateToken(request_p);
			strTarget = "add";
			NonItemMstFB fb = (NonItemMstFB) form_p;
			NonItemMstDATA.initializeAdd(fb, request_p);
			return mapping_p.findForward(strTarget);

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
		 * 
		 * @throws HisException the his exception
		 * @throws SQLException the SQL exception
		 */
		public ActionForward ENGGITEMSUBTYPECMB(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws HisException, SQLException {

			NonItemMstFB fb = (NonItemMstFB) form_p;
			NonItemMstDATA.getEnggItemSubTypeCmb(fb, request_p, response_p);
			
			return null;

		}

		/**
		 * To Save data.
		 * 
		 * @param mapping_p the mapping
		 * @param form_p the form
		 * @param request_p the request
		 * @param response_p the response
		 * 
		 * @return the action forward
		 * 
		 * @throws Exception the exception
		 * @throws SQLException the SQL exception
		 */
		public ActionForward SAVE(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws Exception, SQLException {
			validateToken(request_p,response_p);
			NonItemMstFB fb = (NonItemMstFB) form_p;
			NonItemMstDATA.insertRecord(fb, request_p);

			return this.ADD(mapping_p, form_p, request_p, response_p);
		}

		/**
		 * Forwards control to the modify page.
		 * 
		 * @param mapping_p the mapping
		 * @param form_p the form
		 * @param request_p the request
		 * @param response_p the response
		 * 
		 * @return the action forward
		 * 
		 * @throws HisException the his exception
		 * @throws SQLException the SQL exception
		 */
		public ActionForward MODIFY(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws HisException, SQLException {
			generateToken(request_p);
			NonItemMstFB fb = (NonItemMstFB) form_p;
			NonItemMstDATA.modifyRecord(fb, request_p);
			strTarget = "modify";
			return mapping_p.findForward(strTarget);
		}

		/**
		 * To Update data.
		 * 
		 * @param mapping_p the mapping
		 * @param form_p the form
		 * @param request_p the request
		 * @param response_p the response
		 * 
		 * @return the action forward
		 * @throws Exception 
		 */
		public ActionForward UPDATE(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws Exception {
			validateToken(request_p,response_p);
			boolean bReturnValue = false;
			NonItemMstFB fb = (NonItemMstFB) form_p;
			bReturnValue = NonItemMstDATA.updateRecord(fb, request_p);

			if (bReturnValue)
				return this.LIST(mapping_p, form_p, request_p, response_p);
			else
				return this.MODIFY(mapping_p, form_p, request_p, response_p);
		}

}
