
	package billing.transactions;
/*
 * Ipd Bill Re-Open Transaction ACTION
 * 
 * author : Debashis Sardar
 * 
 * date: 10-Dec-2011
 * 
 */
	import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;


	

	public class IpdBillReOpenTransCNT extends CSRFGardTokenAction {
		//public String bsid = "";

		/**This method is used to call BILLREOPEN  Method to transfer control  
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 * @throws SQLException
		 */
		
		public ActionForward unspecified(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			generateToken(request);

			return this.BILLREOPEN(mapping, form, request, response);

		}

		/**This method is used to call BILLREOPEN Page   
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 * @throws SQLException
		 */
		
		public ActionForward BILLREOPEN(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			generateToken(request);

			String target = "billreopen";
		
			return mapping.findForward(target);
		}

		/**This method is used to Transfer Control Over Inti Page   
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 * @throws SQLException
		 */
		public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

	        ActionForward acFwd = new ActionForward();

	          acFwd.setPath("/billing/transactions/IpdBillManagementTransNewCNT.cnt?hmode=unspecified");

	          acFwd.setContextRelative(true);

	          return acFwd;

	    }
		
		public ActionForward NODUESPRINT(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

	        ActionForward acFwd = new ActionForward();

	          acFwd.setPath("/billing/transactions/IpdBillManagementTransCNT.cnt?hmode=NODUESPRINT");

	          acFwd.setContextRelative(true);

	          return acFwd;

	    }
		


		/**This method is Called when User Enter/Select Bill No & press GO
		 * button to get Patient Bill Details 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 * @throws SQLException
		 */
		
		public ActionForward GO(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			generateToken(request);

			IpdBillReOpenTransFB formBean = (IpdBillReOpenTransFB) form;
			
			  
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
			String strTarget = "billreopen";
			
			IpdBillReOpenTransDATA.getCrNo(formBean,request);
			if(formBean.getStrCrNo().equals(""))
			{
				formBean.setStrRcptNo("");
				formBean.setStrErrMsg("Invalid Bill No.");
				formBean.setStrMsgType("1");
			return mapping.findForward("billreopen");	
			}
			else {
			 IpdBillReOpenTransDATA.getPatDtls(formBean,request);
			 IpdBillReOpenTransDATA.getBillDetails(formBean,request);
			return mapping.findForward(strTarget);
			}
		}

		
		
		/**This method calls on SAVE button to cancel a Request and insert the data 
		 * into database.
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 * @throws SQLException
		 */
		public ActionForward ADDDATA(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception, SQLException
		{
			validateToken(request, response);

			boolean retValue = false ;
			IpdBillReOpenTransFB formBean = (IpdBillReOpenTransFB) form;
			 
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			retValue = IpdBillReOpenTransDATA.saveData(form,request, response);
			if(retValue)
			{	
				formBean.setStrRcptNo("");
				return mapping.findForward("billreopen");		
				
			}
			else
			{
				formBean.setStrRcptNo("");
				return this.unspecified(mapping, form, request, response);
			}	
			
		}
		
	}


