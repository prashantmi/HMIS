package bmed.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bmed.masters.controller.data.EquipmentTestMstDATA;
import bmed.masters.controller.fb.EquipmentTestMstFB;
import bmed.masters.controller.utl.EquipmentTestMstUTL;


/**
 * @author Vivek Aggarwal  
 * Creation Date:- 27-Jul-2012 
 * Modifying Date:- 27-Jul-2012
 * Used For:- 
 * Team Lead By:-  
 *  Module:- BMED(HIS Project)
 * 
 */
public class EquipmentTestMstCNT extends GenericController  
{
		static EquipmentTestMstUTL masterObj = new EquipmentTestMstUTL();
		/** The strTarget. */
		String strTarget = "";

		/**
		 * calls super class constructor.
		 */
		 public EquipmentTestMstCNT() 
	    {
	    	//super(new EquipmentTestMstUTL(), "/masters/EquipmentTestMstCNT");
	    	super(masterObj,"/masters/EquipmentTestMstCNT");
	    }
	    	    
		 public ActionForward ListPage(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws HisException, SQLException 
			{
		    	return this.LIST(mapping, form, request, response);
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

			strTarget = "add";
			EquipmentTestMstFB fb = (EquipmentTestMstFB) form;
			EquipmentTestMstDATA.initializeAdd(fb, request);
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

			EquipmentTestMstFB fb = (EquipmentTestMstFB) form;
			EquipmentTestMstDATA.insertRecord(fb, request);
			return mapping.findForward("add");
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

			
			EquipmentTestMstFB fb = (EquipmentTestMstFB) form;
			EquipmentTestMstDATA.modifyRecord(fb, request);
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
				throws HisException, SQLException {
			
			
			boolean bReturnValue = false;

			EquipmentTestMstFB fb = (EquipmentTestMstFB) form;
			bReturnValue = EquipmentTestMstDATA.updateRecord(fb, request);

			strTarget = "modify";
			return mapping.findForward(strTarget);
		}

		public ActionForward PARAMETER(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

			System.out.println("In Equipment Parameter method to switch to new cnt!!!!!!");
				request.setAttribute("userValue", null);
				ActionForward acFwd = new ActionForward();
				acFwd.setPath("/bmed/masters/EquipmentParameterMstCNT.cnt?hmode=unspecified");
			    acFwd.setContextRelative(true);
		        return acFwd;
		

		}
		public ActionForward MAPPING(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

			System.out.println("In Equipment Test Parameter Mapping method to switch to new cnt!!!!!!");
				ActionForward acFwd = new ActionForward();
				acFwd.setPath("/bmed/masters/EquipmentTestParameterMstCNT.cnt?hmode=unspecified");
			    acFwd.setContextRelative(true);
		        return acFwd;
		

		}
		public EquipmentTestMstCNT TEST(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

			return new EquipmentTestMstCNT();
		

		}
		public ActionForward ENTRY(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

			System.out.println("In Equipment Test/Inspection Entry method to switch to new cnt!!!!!!");
				ActionForward acFwd = new ActionForward();
				acFwd.setPath("/bmed/transactions/EquipmentInspectionTestDtlsACTION.cnt?hmode=unspecified");
			    acFwd.setContextRelative(true);
		        return acFwd;
		

		}
		
		public ActionForward CANCELTODESK(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException 
		{
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
		
		/**
		 * To Show Report
		 * 
		 * @param mapping_p the	ActionMapping
		 * @param form_p the ActionForm
		 * @param request_p the HttpServletRequest
		 * @param response_p the HttpServletResponse
		 * 
		 * @throws HisException
		 * 
		 * @throws SQLException
		 */
		public void SHOWRPT(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws HisException, SQLException {

			
			EquipmentTestMstFB fb = (EquipmentTestMstFB) form_p;
			
			fb.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
			EquipmentTestMstDATA.showReport(fb, request_p,response_p);
		}
		public void PRINT(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

			EquipmentTestMstFB fb = (EquipmentTestMstFB) form;
			EquipmentTestMstDATA.print(request,response,fb);
		}
}
