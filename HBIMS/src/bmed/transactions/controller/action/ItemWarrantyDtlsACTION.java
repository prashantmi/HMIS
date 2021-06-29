package bmed.transactions.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import bmed.transactions.controller.fb.ItemWarrantyDtlsFB;
import bmed.transactions.controller.util.ItemWarrantyDtlsUTIL;
/**
 * @author   Amit kr
 * Creation Date:- 28-April-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) Item Warranty Details Transactions
 * 
 */

public class ItemWarrantyDtlsACTION extends CSRFGardTokenAction 
{
	
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
			ItemWarrantyDtlsFB formBean_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.initializeMain(formBean_p,request_p);
			String strTarget = "item";
			return mapping_p.findForward(strTarget);
		}
		
		
		/**
		 * GETSTORENAME Method is used to get Store Name Combo.
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
			ItemWarrantyDtlsFB formBean_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.getStoreName(formBean_p,request_p,response_p);
			return null;
		}
		
		
		/**
		 * GETENGGITEMSUBTYPE method is used to get Engineering Item Sub Type Combo 
		 * on basis of Ajax calling
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
			ItemWarrantyDtlsFB formBean_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.getEnggItemSubType(formBean_p,request_p,response_p);
			return null;
		}
		
		/**
		 * GETITEMNAME method is used to get Item Name Combo 
		 * on basis of Ajax calling
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
		public ActionForward GETITEMNAME(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws HisException 
		{		
			ItemWarrantyDtlsFB formBean_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.getItemName(formBean_p,request_p,response_p);
			return null;
		}
		
		
		/**
		 * GETSTOCKDTL method is used to get Stock Details 
		 * on basis of Ajax calling
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
			ItemWarrantyDtlsFB formBean_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.getStockDetails(formBean_p,request_p,response_p);
			return null;
		}
		
		/**
		 * GETPREVWARANTYDTL method is used to get Previous Warranty Details 
		 * on basis of Ajax calling
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
		public ActionForward GETPREVWARANTYDTL(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws HisException 
		{		
			ItemWarrantyDtlsFB formBean_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.getWarrantyDetails(formBean_p,request_p,response_p);
			return null;
		}
		
		
		
		/**
		 * GETUPLOADEDFILE method is used to Up-Load attach file 
		 * on basis of Ajax calling
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
	        ItemWarrantyDtlsFB formBean_p = (ItemWarrantyDtlsFB)form_p;
	        ItemWarrantyDtlsUTIL.getUploadedFile(formBean_p, request_p, response_p);
			return null;
		}
		
		/**
		 * GETSUPPLIER method is used to get Supplier Name Combo 
		 * on basis of Ajax calling
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
			ItemWarrantyDtlsFB formBean_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.getSupplierCmb(formBean_p,request_p,response_p);
			return null;
		}
		
		
		
		/**
		 * WARRANTYCANCEL method is used to get Warranty Cancel Pop-Up 
		 * on basis of Ajax calling
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
		public ActionForward WARRANTYCANCEL(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws HisException 
		{		
			ItemWarrantyDtlsFB formBean_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.getCommanAction(formBean_p,request_p,response_p);
			return null;
			
		}
		
		/**
		 * MANTVIEW method is used to get View Pop-Up 
		 * on basis of Ajax calling
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
			generateToken(request_p);
			ItemWarrantyDtlsFB formBean_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.getCommanAction(formBean_p,request_p,response_p);
			return null;
			//return this.unspecified(mapping_p, form_p, request_p, response_p);
		}
				
		/**
		 * SAVECANCEL method is used to save Cancel details
		 * on basis of Ajax calling
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
				throws HisException 
		{		
			ItemWarrantyDtlsFB formBean_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.cancelData(formBean_p,request_p,response_p);
			return null;
		}
			
		
		/**
		 * SAVE method is used to save Data into HSTT_WARRANTY_DTL
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
			validateToken(request_p, response_p);
			ItemWarrantyDtlsFB formBean_p_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.saveData(formBean_p_p,request_p);
			return this.unspecified(mapping_p, form_p, request_p, response_p);
		}
		
		
		/**
		 * EXTEND method is used to extend warranty data into HSTT_WARRANTY_DTL
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
		public ActionForward EXTEND(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws Exception 
		{		
			validateToken(request_p, response_p);
			ItemWarrantyDtlsFB formBean_p_p = (ItemWarrantyDtlsFB)form_p;
			ItemWarrantyDtlsUTIL.saveExtendWarrantyData(formBean_p_p,request_p);
			return this.unspecified(mapping_p, form_p, request_p, response_p);
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
		public ActionForward CANCEL(ActionMapping mapping_p, ActionForm form_p,
				HttpServletRequest request_p, HttpServletResponse response_p)
				throws HisException 
		{
		    ActionForward acFwd = new ActionForward();
			acFwd.setPath("/hisglobal/initPage.jsp");
			acFwd.setContextRelative(true);
			return acFwd;
		}
		
		
	}