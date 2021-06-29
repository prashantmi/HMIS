package bmed.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bmed.masters.controller.data.EquipmentParameterMstDATA;
import bmed.masters.controller.data.EquipmentTestMstDATA;
import bmed.masters.controller.fb.EquipmentParameterMstFB;
import bmed.masters.controller.fb.EquipmentTestMstFB;
import bmed.masters.controller.utl.EquipmentParameterMstUTL;


/**
 * @author Arun VR 
 * Creation Date:- 06-Aug-2012 
 * Modifying Date:- 
 * Used For:- 
 * Team Lead By:-  
 *  Module:- BMED(HIS Project)
 * 
 */
public class EquipmentParameterMstCNT extends GenericController  
{

		/** The strTarget. */
		String strTarget = "";

		/**
		 * calls super class constructor.
		 */
		 public EquipmentParameterMstCNT() 
	    {
	    	super(new EquipmentParameterMstUTL(), "/masters/EquipmentParameterMstCNT");
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
			EquipmentParameterMstFB fb = (EquipmentParameterMstFB) form;
			EquipmentParameterMstDATA.initializeAdd(fb, request);
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

			EquipmentTestMstFB fb = (EquipmentTestMstFB) form;
			EquipmentTestMstDATA.getEnggItemSubTypeCmb(fb, request, response);
			
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
			EquipmentParameterMstFB fb = (EquipmentParameterMstFB) form;
			EquipmentParameterMstDATA.insertRecord(fb, request);

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
			EquipmentParameterMstFB fb = (EquipmentParameterMstFB) form;
			EquipmentParameterMstDATA.modifyRecord(fb, request);
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
			validateToken(request,response);
			boolean bReturnValue = false;

			EquipmentParameterMstFB fb = (EquipmentParameterMstFB) form;
			bReturnValue = EquipmentParameterMstDATA.updateRecord(fb, request);

			if (bReturnValue)
				return this.LIST(mapping, form, request, response);
			else
				return this.MODIFY(mapping, form, request, response);
		}

}
