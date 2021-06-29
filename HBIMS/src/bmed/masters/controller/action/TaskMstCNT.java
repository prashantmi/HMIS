package bmed.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import bmed.masters.controller.utl.TaskMstUTL;
import bmed.masters.controller.data.TaskMstDATA;
import bmed.masters.controller.fb.TaskMstFB;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Vivek Aggarwal  
 * Creation Date:- 11-Jan-2011 
 * Modifying Date:- 20-Jan-2011
 * Used For:- 
 * Team Lead By:-  
 *  Module:- BMED(HIS Project)
 * 
 */
public class TaskMstCNT extends GenericController  
{

		/** The strTarget. */
		String strTarget = "";

		/**
		 * calls super class constructor.
		 */
		 public TaskMstCNT() 
	    {
	    	super(new TaskMstUTL(), "/masters/TaskMstCNT");
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
			TaskMstFB fb = (TaskMstFB) form;
			TaskMstDATA.initializeAdd(fb, request);
			return mapping.findForward(strTarget);

		}
		
		 /**
		 * Get Engg Item Sub Type Combo using Ajax. 
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

			TaskMstFB fb = (TaskMstFB) form;
			TaskMstDATA.getEnggItemSubTypeCmb(fb, request, response);
			
			return null;

		}

		/**
		 * To Save data.
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
			TaskMstFB fb = (TaskMstFB) form;
			TaskMstDATA.insertRecord(fb, request);

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
			TaskMstFB fb = (TaskMstFB) form;
			TaskMstDATA.modifyRecord(fb, request);
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
		 * 
		 * @throws HisException the his exception
		 * @throws SQLException the SQL exception
		 */
		public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, Exception {
			boolean bReturnValue = false;
			validateToken(request,response);
			TaskMstFB fb = (TaskMstFB) form;
			bReturnValue = TaskMstDATA.updateRecord(fb, request);

			if (bReturnValue)
				return this.LIST(mapping, form, request, response);
			else
				return this.MODIFY(mapping, form, request, response);
		}

}
