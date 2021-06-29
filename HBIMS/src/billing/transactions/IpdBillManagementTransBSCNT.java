package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.BillConfigUtil;

public class IpdBillManagementTransBSCNT extends GenericController {
	public IpdBillManagementTransBSCNT()
	{
		super(new IpdBillManagementTransUTL(),"/transactions/IpdBillManagementTransBSCNT");
	}
	/**
	 * Forwards control to the IpdBillManagementTrans UpdateAccountStatus Page
	 * & generate the data to display
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward UPDATEACCOUNTSTATUS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException
	{
		generateToken(request);

		String strtarget = "updateaccount";
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//formBean.setStrComboValue(request.getParameterValues("combo"));
		if(formBean.getCombo()==null)
		{
		  String[] StrCombo={formBean.getStrAcctStatMode(),"1","1"};
		  formBean.setCombo(StrCombo);
		}
		formBean.setStrComboValue(formBean.getCombo());
		String chk="";
		if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
		{
			String[] arr=formBean.getChk();
			chk =arr[0];
		}
		else
		{
			chk =formBean.getStrChk();
		}
		if (chk==null)
		{
			return this.LIST(mapping, form, request, response);
		}	
		else
		{	
			boolean bRetVal	= IpdBillManagementTransBSDATA.initPatientDtl(request,response,formBean);
			if(bRetVal)
			{
				return mapping.findForward(strtarget);
			}
			else
			{
				return this.LIST(mapping, form, request, response);
			}
		
		}	
	}
	/**
	 * INSERT Logic For IpdBillManagementTrans  Update Account Status
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward UPDACCTSTATUSINSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		String strtarget = "updateaccount";
		validateToken(request, response);

        boolean retValue = false ;
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		if (formBean.getStrChk()== null)
		{
			return this.LIST(mapping, form, request, response);
		}	
		else
		{
			retValue = IpdBillManagementTransBSDATA.InsertRecordUpDateAcctStatus(formBean);     //defined in DATA class
			if(formBean.getStrIsCalledFromIpdBillNew().equals("1"))
		      {
		    	    ActionForward acFwd = new ActionForward();
		    	    formBean.setStrErrMsg("");
		    	    formBean.setStrMsg("Record Updated Successfully.");
					acFwd.setPath("/billing/transactions/IpdBillManagementTransBSCNT.cnt?hmode=unspecified");
					acFwd.setContextRelative(true);
					return acFwd;
		      }
		      else
		      {
				if(retValue)
				{	
				    return this.LIST(mapping, form, request, response);
				}
				else
				{
					return mapping.findForward(strtarget);
				}	
		      }
		}	

		
   }
	
	/**
	 * Forwards control to the IpdBillManagementTrans ViewCharge Page
	 * & generate the data to display
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward VIEWCHARGES(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		generateToken(request);
		String strtarget = "viewcharge";
		boolean retValue = false ;
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrComboValue(request.getParameterValues("combo"));	 
		retValue = IpdBillManagementTransBSDATA.initDtl(formBean);     
		if(retValue)
		{	
			return mapping.findForward(strtarget);
		   
		}
		else
		{
			 return this.LIST(mapping, form, request, response);
		}	
			
		
	}
	/**
	 * Forwards control to the IpdBillManagementTrans ViewBill Page
	 * & generate the data to display
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward VIEWBILL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		generateToken(request);
		String strtarget = "viewbill";
		boolean retValue = false ;
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//formBean.setStrComboValue(request.getParameterValues("combo"));	
		if(formBean.getCombo()==null)
		{
		  String[] StrCombo={formBean.getStrAcctStatMode(),"1","1"};
		  formBean.setCombo(StrCombo);
		}
		formBean.setStrComboValue(formBean.getCombo());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		
			
			retValue = IpdBillManagementTransBSDATA.initPatientDtl1(request,response,formBean);
			if(retValue)
			{	
				return mapping.findForward(strtarget);
			}
			else
			{
				//if(request.getParameter("isDesk")!="1")
				return this.LIST(mapping, form, request, response);				
			}	
		
     }
	/**
	 * Forwards control to the IpdBillManagementTrans Bill Approval Page
	 * & generate the data to display
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward BILLAPPROVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		generateToken(request);
		String strtarget = "billapproval";
		String chk2 = "";//request.getParameter("chk");
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		if(formBean.getCombo()==null)
		{
		  String[] StrCombo={formBean.getStrAcctStatMode(),"1","1"};
		  formBean.setCombo(StrCombo);
		}
		if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
		{
			String[] arr=formBean.getChk();
			 chk2 =arr[0];
		}
		else
		{
			 chk2 =formBean.getStrChk();
		}
				
		if (chk2==null)		
		//if (request.getParameter("chk")==null)
		{
			return this.LIST(mapping, form, request, response);
		}	
		else
		{	
			
			 
   		 formBean.setStrIpdBillManagementMode(new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString()).getIpdGenAdtProcessType()); 
   		 
   		 
   		 String strMode = formBean.getStrIpdBillManagementMode();
   		 
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			boolean bRetVal  	= IpdBillManagementTransBSDATA.billApprovalDtl(request,response,formBean , strMode);
			boolean bRetVal1	= IpdBillManagementTransBSDATA.DropTariffCombo(request,response,formBean,chk2);
			
			if(bRetVal && bRetVal1)
			{
				return mapping.findForward(strtarget);
			}
			else
			{
				return this.LIST(mapping, form, request, response);
			}         
		}	
     }
	/**
	 * INSERT Logic For IpdBillManagementTrans  Bill Approval 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward BILLAPPROVALINSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		validateToken(request, response);
      	IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
      	
      	formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
      	IpdBillManagementTransBSDATA.InsertBillApproval(request,response,formBean);                                   //defined in DATA class
      	if(formBean.getStrIsCalledFromIpdBillNew().equals("1"))
        {
      	    ActionForward acFwd = new ActionForward();
      	    formBean.setStrErrMsg("");
      	    formBean.setStrMsg("Record Saved Successfully.");
  			acFwd.setPath("/billing/transactions/IpdBillManagementTransNewBSCNT.cnt?hmode=unspecified");
  			acFwd.setContextRelative(true);
  			return acFwd;
        }
        else
        {
			return this.LIST(mapping, form, request, response);
        }       
	}
	 
	
	/**
	 * IpdBillManagementTrans  Testing Dummy Method  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	
	public final ActionForward VIEWDATA1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	{
	//	System.out.println("IN VIEWDATA");
    //	GenericData.VIEWDATA(request, response, masterObj);
		return null;
	}
		
	/**
	 * (ADDSERVICE)-->
	 * Forwards control to the IpdBillManagementTrans Add Service
	 * & generate the data to display
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward ADDSERVICE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException
	{
		generateToken(request);
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		String strtarget = "addservice";
		//String chk = request.getParameter("chk");
		//String chk2 = request.getParameter("chk");
		String chk ="";
		String chk2 ="";
		if(formBean.getCombo()==null)
		{
		  String[] StrCombo={formBean.getStrAcctStatMode(),"1","1"};
		  formBean.setCombo(StrCombo);
		}
		if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
		{
			String[] arr=formBean.getChk();
			 chk =arr[0];
			 chk2 =arr[0];
			
		}
		else
		{
			 chk =formBean.getStrChk();
			 chk2 =formBean.getStrChk();
		}
		
		
		//formBean.setStrComboValue(request.getParameterValues("combo"));
		formBean.setStrComboValue(formBean.getCombo());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		//persisting list page values///
		/*formBean.setSearchColumn(request.getParameter("searchColumn"));
		formBean.setSearch(request.getParameter("search"));
		formBean.setBlockNo(request.getParameter("blockNo"));
		formBean.setPrevNext(request.getParameter("prevNext"));
		formBean.setRowNum(request.getParameter("rowNum"));
		formBean.setDivisionId(request.getParameter("divisionId"));*/
		////////////////////////////
		
		String strTemp[] = chk.replace("@", "#").split("#");
		
		formBean.setStrAccountNo(strTemp[0]);
		formBean.setStrCrNo(strTemp[1]);
		formBean.setStrChargeTypeID(strTemp[4]);
		
		//if(request.getParameter("chk")==null)
		if(chk==null)
		{
			return this.LIST(mapping, form, request, response);
		}	
		else
		{	
			 boolean bRetVal  	= IpdBillManagementTransBSDATA.PatientDtl(request,response,formBean,chk);
	    	 boolean bRetVal1	= IpdBillManagementTransBSDATA.DropTariffCombo(request,response,formBean,chk2);
	    	 if(bRetVal && bRetVal1)
			 {
	    		 BillConfigUtil bcu=new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	    		 formBean.setStrIpdBillManagementMode(bcu.getIpdGenAdtProcessType());
	    		 formBean.setDietChargeId(bcu.DIET_CHARGE_ID);
	    		 
	    		 String strMode = formBean.getStrIpdBillManagementMode();
	    		 
	    		 IpdBillManagementTransBSDATA.initAddServicesDtl(request, response, formBean , strMode);
	    		 formBean.setStrConsumableChargesGroupId(bcu.getStrConsumableChargesGroupId());
	    		 formBean.setStrConsumableChargesTariffCode(bcu.getStrConsumableChargesTariffCode());
	    		 
	     		return mapping.findForward(strtarget);
			 }
			 else
			 {
				return this.LIST(mapping, form, request, response);
			 }
		}
	}
	/**
	 * (ADDSERVICEINSERT)-->
	 * INSERT Logic For IpdBillManagementTrans  Add Service Page  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward ADDSERVICEINSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		 
		validateToken(request, response);
       	IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
       	formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString()); 
       	
       	formBean.setStrChk(request.getParameter("chk"));
       	
//		String strData[] = {request.getParameter("strAcctNo"),request.getParameter("strActHLPHid"),request.getParameter("strCltAppHLPHid"),request.getParameter("strCltAppSancAmt"),request.getParameter("strCltApprBalanceAmt")};
		
        IpdBillManagementTransBSDATA.InsertAddService(request,response,formBean);  //defined in DATA class
        if(formBean.getStrIsCalledFromIpdBillNew().equals("1"))
        {
      	    //ActionForward acFwd = new ActionForward();
      	    formBean.setStrMsg("Record Saved Successfully.");
      	    formBean.setStrErrMsg("");
  			//acFwd.setPath("/billing/transactions/IpdBillManagementTransBSCNT.cnt?hmode=unspecified");
  			//acFwd.setContextRelative(true);
  			//return acFwd;
      	    return this.ADDSERVICE(mapping, formBean, request, response);
        }
        else
        {
		if(formBean.getIsBillFinal()!=null && formBean.getIsBillFinal().equals("91"))
			return this.LIST(mapping, form, request, response);
		else
			return this.ADDSERVICE(mapping, formBean, request, response);
        }
	}
	 
	
	/**
	 * (PARTPAYMENTREQUEST)-->
	 * Forwards control to the IpdBillManagementTrans Part Payment Request
	 * & generate the data to display 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward PARTPAYMENTREQUEST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException
	{
		generateToken(request);
		String strTarget = "partpaymentrequest";
		
		String chk = request.getParameter("chk");
	 
		
    	IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		formBean.setStrComboValue(request.getParameterValues("combo"));

		formBean.setStrComboValue(request.getParameterValues("combo"));	              
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		String strTemp[] = chk.replace("@", "#").split("#");
		
		formBean.setStrCrNo(strTemp[1]);
		formBean.setStrChargeTypeID(strTemp[4]);
		formBean.setStrWardCode(formBean.getStrComboValue()[2].replace("^", "#").split("#")[0]);
		 
		boolean bRetVal  = IpdBillManagementTransBSDATA.initPatientDtl(request,response,formBean);
	
		if(bRetVal )
		{
			
			formBean.setStrIpdBillManagementMode(new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString()).getIpdGenAdtProcessType()); 
    		 
    		 
    		 String strMode = formBean.getStrIpdBillManagementMode();
    		 
    		 
    			 IpdBillManagementTransBSDATA.initAddServicesDtl(request, response, formBean , strMode);
    			     		 
    		 
			
			return mapping.findForward(strTarget);
		}
		else
		{
			return this.LIST(mapping, form, request, response);
		}            
	}
	
	
	public final ActionForward PARTPAYMENTAMTDTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	{
 
		
		 IpdBillManagementTransBSDATA.PartPaymentAmtCombo(request, response);
		
		
		return null;
	}
	
	
	
	/**
	 * (PARTPAYMENTINSERT)-->
	 * INSERT Logic For IpdBillManagementTrans  Part Payment Insert  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward PARTPAYMENTINSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		validateToken(request, response);
		String strtarget = "partpaymentrequest";
       	IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
       	formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
       	formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		if (formBean.getStrChk()== null)
		{
			return this.LIST(mapping, form, request, response);
		}	
		else
		{
			boolean retValue = IpdBillManagementTransBSDATA.InsertRecordPartPayment(request , formBean); //defined in DATA class  
			if(retValue)
			{	
			    return this.LIST(mapping, form, request, response);
			}
			else
			{
				return mapping.findForward(strtarget);
			}	
		}	
   }
	
   /**
	 * (ADDCLTAPPROVAL)-->
	 * Forwards control to the IpdBillManagementTrans Add Client Approval
	 * & generate the data to display 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward ADDCLTAPPROVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException
	{
		String strtarget = "addcltapproval";
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrComboValue(request.getParameterValues("combo"));	
		if (request.getParameter("chk")==null)
		{
			return this.LIST(mapping, form, request, response);
		}	
		else
		{
			boolean retValue = IpdBillManagementTransBSDATA.initClientApprovalDtl(request,response,formBean);
			if(retValue)
			{	
				return mapping.findForward(strtarget);
			}
			else
			{
				 return this.LIST(mapping, form, request, response);
			}	
		
		}	
	}
	
	/**
	 * forwards control to the IpdBillManagementTrans Save Client Approval Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward SAVECLTAPPROVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException
	{

		
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
    //	String strData[] = {request.getParameter("strAcctNo"),request.getParameter("strActHLPHid"),request.getParameter("strCltAppHLPHid"),request.getParameter("strCltAppSancAmt"),request.getParameter("strCltApprBalanceAmt")};

		formBean.setStrAccountNo(request.getParameter("strAcctNo"));
		formBean.setStrCltAppSancAmt(request.getParameter("strCltAppSancAmt"));
		formBean.setStrCltApprBalanceAmt(request.getParameter("strCltApprBalanceAmt"));
		formBean.setStrClientPatNo(request.getParameter("strCltAppHLPHid"));
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		if (request.getParameter("strCltAppHLPHid")!= null)
		{
		//	boolean retValue = IpdBillManagementTransBSDATA.InsertRecord(formBean);                                   //defined in DATA class
		    return this.LIST(mapping, form, request, response);
		} 
		else
		{
			return this.LIST(mapping, form, request, response);
		}
   }
	
	/**
	 * (UNITVAL)-->
	 * This Method give the Response of Ajax Function
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward UNITVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
	    IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
	    IpdBillManagementTransBSDATA.UNITVAL(request,response,formBean);
		return null;
	}
	/**
	 * (UNITVAL1)--> 
	 * This Method give the Response of Ajax Function
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward UNITVAL1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
	    IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
	    IpdBillManagementTransBSDATA.UNITVAL1(request,response,formBean);
		return null;
	}
	/**
	 * (VIEWGO)--> 
	 * This Method give the Response of Ajax Function for View Charges Transaction
	 * This Method give 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward VIEWGO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
	    IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
	    IpdBillManagementTransBSDATA.FUNCVIEW(request,response,formBean);
		return null;
	}
	
	
	
	public ActionForward SPLWARDDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		IpdBillManagementTransBSDATA.getSpecialWardDtls(request, response);

		return null;

	}
	
	
	public ActionForward PREVDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		IpdBillManagementTransBSDATA.getPreviousDtls(request, response);

		return null;

	}
	
	
	public ActionForward DELPREVDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		IpdBillManagementTransBSDATA.deletePreviousDtls(request, response);

		return null;

	}
	
	/**
	 * (UNITVAL12)--> Genrate Particular Deta
	 * Ajax Function for Pop-Up in View-Bill 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward UNITVAL12(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
	    IpdBillManagementTransBSDATA.UNITVAL12(request,response,formBean);
		return null;
	}
 	/**
	 * (UNITVAL13)--> 
	 * Ajax Function for Pop-Up in Bill-Approval 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward UNITVAL13(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		
		IpdBillManagementTransBSDATA.UNITVAL13(request, response);
		return null;
	}
	
   /**
	 * (TARIFFDTLS)--> 
	 * Ajax Function for Pop-Up in Drop Down Data 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward TARIFFDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		IpdBillManagementTransBSDATA.TARIFFDTLS(request, response, formBean);
		return null;
	}
	
	

	/**
	 * function invoked by Ajax, populates the Tariff Details based on Tariff Code.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward TARIFFCODEDTLS(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		IpdBillManagementTransBSDATA.getTariffCodeDetails(request, response);
		return null;
	}


	/**
	 * function invoked by Ajax, populates the Tariff Details based on Tariff Code.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward DEFULTTARIFFLIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

			IpdBillManagementTransBSDATA.getDefaultTariffDetails(request, response );

		return null;

	}

	
	
	
	
	 /**
	 * (TRFUNIT)--> 
	 * Ajax Function 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward TRFUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
				IpdBillManagementTransBSDATA.TRFUNIT(request, response,formBean);
		return null;
	}
	
	
	
	//Tariff Details-On Click Of Search Button One Time Request Only
	public ActionForward GETPARTDTLS(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;	
		IpdBillManagementTransBSDATA.getParticularDtlsView(request, response, formBean);		
		return null;
	}
	
	
	
	
	
	/**SHOWRPT -> Method use to get Report
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdBillManagementTransBSDATA.showReport(formBean, request, response);
		
		
	}

	public void VIEWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdBillManagementTransBSDATA.viewReport(formBean, request, response);
		
		
	}
	public ActionForward TARIFFCODEDTLSNEW(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		IpdBillManagementTransBSDATA.getTariffCodeDetailsNew(request, response);
		return null;
	}
	public ActionForward TARIFFCODEDTLSNEWDRUG(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		IpdBillManagementTransBSDATA.getTariffCodeDetailsNewDrug(request, response);
		return null;
	}
	public ActionForward CANCELTOIPDDESKNEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		            ActionForward acFwd = new ActionForward();
		    	    formBean.setStrErrMsg("");
					acFwd.setPath("/billing/transactions/IpdBillManagementTransBSCNT.cnt?hmode=unspecified");
					acFwd.setContextRelative(true);
					return acFwd;
		    
   }
	
	public ActionForward BEDTRANSFER(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException
	{
		generateToken(request);
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		String strtarget = "bedtransfer";
		String chk ="";
		String chk2 ="";
		if(formBean.getCombo()==null)
		{
		  String[] StrCombo={formBean.getStrAcctStatMode(),"1","1"};
		  formBean.setCombo(StrCombo);
		}
		if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
		{
			String[] arr=formBean.getChk();
			 chk =arr[0];
			 chk2 =arr[0];		
		}
		else
		{
			 chk =formBean.getStrChk();
			 chk2 =formBean.getStrChk();
		}
		formBean.setStrComboValue(formBean.getCombo());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		String strTemp[] = chk.replace("@", "#").split("#");
		
		formBean.setStrAccountNo(strTemp[0]);
		formBean.setStrCrNo(strTemp[1]);
		formBean.setStrChargeTypeID(strTemp[4]);
		
		if(chk==null)
		{
			return this.LIST(mapping, form, request, response);
		}	
		else
		{	
			 boolean bRetVal  	= IpdBillManagementTransBSDATA.PatientDtl(request,response,formBean,chk);
	    	 //boolean bRetVal1	= IpdBillManagementTransBSDATA.DropTariffCombo(request,response,formBean,chk2);
	    	 //if(bRetVal && bRetVal1)
			 if(bRetVal)
			 {
	    		 BillConfigUtil bcu=new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	    		 formBean.setStrIpdBillManagementMode(bcu.getIpdGenAdtProcessType());
	    		 
	    		 String strMode = formBean.getStrIpdBillManagementMode();
	    		 IpdBillManagementTransBSDATA.initBedTransferDtl(request, response, formBean , strMode);
	    		 return mapping.findForward(strtarget);
			 }
			 else
			 {
				return this.LIST(mapping, form, request, response);
			 }
		}
	}
	
	public ActionForward GETWARDLIST(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws HisException, SQLException 
	{
		IpdBillManagementTransBSDATA.getWardList(request, response);
		return null;
	}
	
	public ActionForward BEDTRANSFERINSERT(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception, SQLException
	{
		validateToken(request, response);
       	IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
       	formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString()); 
       	formBean.setStrChk(request.getParameter("chk"));
        IpdBillManagementTransBSDATA.InsertBedTransfer(request,response,formBean);  //defined in DATA class
        if(formBean.getStrIsCalledFromIpdBillNew().equals("1"))
        {
      	    ActionForward acFwd = new ActionForward();
      	    formBean.setStrMsg("Record Saved Successfully.");
      	    if(formBean.getIsBillFinal()!=null && !formBean.getIsBillFinal().equals(""))
			{
				request.getSession().setAttribute("USERVALUE", "1");
			}
      	    formBean.setStrErrMsg("");
  			//acFwd.setPath("/billing/transactions/IpdBillManagementTransBSCNT.cnt?hmode=unspecified");
  			//acFwd.setContextRelative(true);
  			//return acFwd;
      	    return this.BEDTRANSFER(mapping, formBean, request, response);
        }
        else
        {
			if(formBean.getIsBillFinal()!=null && formBean.getIsBillFinal().equals("91"))
				return this.LIST(mapping, form, request, response);
			else
				return this.ADDSERVICE(mapping, formBean, request, response);
        }
	}
	
	public ActionForward NODUESPRINT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		generateToken(request);
		String strtarget = "nodues";
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		formBean.setFilePath("");
		formBean.setIsOpenPopUp("0");
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//formBean.setStrComboValue(request.getParameterValues("combo"));
		if(formBean.getCombo()==null)
		{
		  String[] StrCombo={formBean.getStrAcctStatMode(),"1","1"};
		  formBean.setCombo(StrCombo);
		}
		formBean.setStrComboValue(formBean.getCombo());
		String chk="";
		if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
		{
			String[] arr=formBean.getChk();
			chk =arr[0];
		}
		else
		{
			chk =formBean.getStrChk();
		}
		if (chk==null)
		{
			return this.LIST(mapping, form, request, response);
		}	
		else
		{	
			boolean bRetVal	= IpdBillManagementTransBSDATA.admissionList(request,response,formBean);
			if(bRetVal)
			{
				return mapping.findForward(strtarget);
			}
			else
			{
				return this.LIST(mapping, form, request, response);
			}
		
		}
		/*String strtarget = "nodues";
		boolean retValue = false ;
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//formBean.setStrComboValue(request.getParameterValues("combo"));	
		if(formBean.getCombo()==null)
		{
		  String[] StrCombo={formBean.getStrAcctStatMode(),"1","1"};
		  formBean.setCombo(StrCombo);
		}
		formBean.setStrComboValue(formBean.getCombo());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		
			
			retValue = IpdBillManagementTransBSDATA.initPatientDtl1(request,response,formBean);
			if(retValue)
			{	
				return mapping.findForward(strtarget);
			}
			else
			{
				//if(request.getParameter("isDesk")!="1")
				return this.LIST(mapping, form, request, response);				
			}	*/
		
     }
	public ActionForward NODUESPRINTSAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{
		validateToken(request, response);
		String strtarget = "nodues";
        boolean retValue = false ;
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		if (formBean.getStrChk()== null)
		{
			return this.LIST(mapping, form, request, response);
		}	
		else
		{
			retValue = IpdBillManagementTransBSDATA.InsertNoDuesPrint(formBean , request);     //defined in DATA class
			if(formBean.getStrIsCalledFromIpdBillNew().equals("1"))
		      {
		    	    ActionForward acFwd = new ActionForward();
		    	    formBean.setStrErrMsg("");
		    	    formBean.setStrMsg("No Dues Printed Successfully.");
					/*acFwd.setPath("/billing/transactions/IpdBillManagementTransBSCNT.cnt?hmode=unspecified");
					acFwd.setContextRelative(true);
					return acFwd;*/
		    	    return mapping.findForward(strtarget);
		      }
		      else
		      {
				if(retValue)
				{	
				    //return this.LIST(mapping, form, request, response);
					return mapping.findForward(strtarget);
				}
				else
				{
					return mapping.findForward(strtarget);
				}	
		      }
		}	
     }
	public ActionForward GETUNITDTLS(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws HisException, SQLException 
	{
		IpdBillManagementTransBSDATA.getUnitDtls(request, response);
		return null;
	}
	public ActionForward GETCONSULTANT(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws HisException, SQLException 
	{
		IpdBillManagementTransBSDATA.getConsultatnt(request, response);
		return null;
	}
	
	public ActionForward IPDBILLREOPEN(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws HisException, SQLException 
	{
		ActionForward acFwd = new ActionForward();
		//formBean.setStrErrMsg("");
		//formBean.setStrMsg("Record Updated Successfully.");
		acFwd.setPath("/billing/transactions/IpdBillReOpenCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward HOME(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws HisException, SQLException 
	{
		ActionForward acFwd = new ActionForward();
		//formBean.setStrErrMsg("");
		//formBean.setStrMsg("Record Updated Successfully.");
		acFwd.setPath("/billing/transactions/IpdBillManagementTransNewBSCNT.cnt");
		acFwd.setContextRelative(true);
		return acFwd;
	}
}
