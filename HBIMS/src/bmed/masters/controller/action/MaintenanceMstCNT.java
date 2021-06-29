package bmed.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import bmed.masters.controller.utl.MaintenanceMstUTL;
import bmed.masters.controller.data.MaintenanceMstDATA;
import bmed.masters.controller.fb.MaintenanceMstFB;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Vivek Aggarwal   
 * Creation Date:- 19-Jan-2011 
 * Modifying Date:- 21-Jan-2011
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class MaintenanceMstCNT extends GenericController  
{

		/** The strTarget. */
		String strTarget = "";

		/**
		 * calls super class constructor.
		 */
			
	    public MaintenanceMstCNT() 
	    {
	    	super(new MaintenanceMstUTL(), "/masters/MaintenanceMstCNT");
	    }
	    
		    
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
		public ActionForward ADD(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {
			generateToken(request);
			strTarget = "add";
			MaintenanceMstFB fb = (MaintenanceMstFB) form;
			MaintenanceMstDATA.initializeAdd(fb, request);
			return mapping.findForward(strTarget);

		}
		
		 /**
		 * Get ENGG ITEM SUB TYPE COMBO using Ajax. 
		 * 
		 * @param mapping the mapping
		 * @param form the form
		 * @param request the request
		 * @param response the response
		 * 
		 * @return the null
		 * 
		 * @throws HisException the his exception
		 * @throws SQLException the SQL exception
		 */
		public ActionForward ENGGITEMSUBTYPECMB(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

			MaintenanceMstFB fb = (MaintenanceMstFB) form;
			MaintenanceMstDATA.getEnggItemSubTypeCmbValues(fb, request, response);
			
			return null;
		}

		/**
		 * To Save data on the Add Page.
		 * 
		 * @param mapping the mapping
		 * @param form the form
		 * @param request the request
		 * @param response the response
		 * 
		 * @return the action forward
		 * 
		 * @throws Exception the exception
		 * @throws SQLException the SQL exception
		 */
		public ActionForward SAVE(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception, SQLException {
			validateToken(request,response);
			MaintenanceMstFB fb = (MaintenanceMstFB) form;
			MaintenanceMstDATA.insertRecord(fb, request);

			return this.ADD(mapping, form, request, response);
		}

		/**
		 * Forwards control to the modify page.
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
		public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {
			generateToken(request);
			MaintenanceMstFB fb = (MaintenanceMstFB) form;
			MaintenanceMstDATA.modifyRecord(fb, request);
			strTarget = "modify";
			return mapping.findForward(strTarget);
		}

		/**
		 * To Update data.
		 * 
		 * @param mapping the mapping
		 * @param form the form
		 * @param request the request
		 * @param response the response
		 * 
		 * @return the action forward
		 * @throws Exception 
		 */
		public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			validateToken(request,response);
			boolean bReturnValue = false;

			MaintenanceMstFB fb = (MaintenanceMstFB) form;
			bReturnValue = MaintenanceMstDATA.updateRecord(fb, request);

			if (bReturnValue)
				return this.LIST(mapping, form, request, response);
			else
				return this.MODIFY(mapping, form, request, response);
		}
		
		
		public ActionForward CANCELTODESK(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, Exception 
		{
			validateToken(request,response);
			ActionForward acFwd = new ActionForward();
			String strPath = "";
			if(request.getParameter("strPath")!= null)
			{
				strPath = request.getParameter("strPath").concat("?hmode=CANCEL");
				acFwd.setPath(strPath);
		        acFwd.setContextRelative(true);
		        
			}
			return acFwd;
		}

}