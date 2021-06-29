package billing.transactions;
/*

/***************************Start of program*****************************\

## Copyright Information : C-DAC, Noida  

## Project Name :NIMS

## Name of Developer  : Shefali garg		 

## Module Name : BIlling

## Process/Database Object Name :

## Purpose : LF Cash collection Process

## Date of Creation : 12-April-2015

## Modification Log : 

## Modify Date : 

## Reason (CR/PRS) : 

## Modify By :*/
import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import billing.BillConfigUtil;

public class LFNoTransCNT extends DispatchAction {

	/**
	 * forwards control to the Page cashcollection_billtrans.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException {

		LFNoTransFB formBean = (LFNoTransFB) form;
		String strCounterId ="";
		String target = "cashcollection";
		
		if(BillConfigUtil.BACK_DATE_DAYEND_STOP_PROCESS.equals("1"))
		{		
			if(request.getSession().getAttribute("BACK_DATE_DAY_END_FLAG").toString().equals("1"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Perform Day End First.");
				return mapping.findForward(target);
			}
			else if(request.getSession().getAttribute("BACK_DATE_DAY_END_FLAG").toString().equals("2"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Deposit Day End Amount.");
				return mapping.findForward(target);
			}
		}
		
		
		BillConfigUtil bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrConfirmationType(bcu.getGeneralCashCollectionConfrimType());
		//System.out.println("IP Address"+request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrCrNo("");
		if(formBean.getStrAccountStatus()==null ||formBean.getStrAccountStatus()=="")
	 	{
			formBean.setStrAccountStatus("1");
	 	}
		//if(formBean.getStrCRorLFwise()==null ||formBean.getStrCRorLFwise()=="")
	 //	{
		formBean.setStrCRorLFwise("1");
		
	 	//}
	String counterCheck=BillConfigUtil.CHECK_COUNTER_STATUS;
		
		
		
		if(counterCheck.equals("1"))//Counter Check Needed=1, Counter Check Not Needed=0
		{
			strCounterId =LFNoTransDATA.checkCounterStatus(request, formBean);
			if(strCounterId!=null)
			{
				if(strCounterId.equals(""))
				{
					return mapping.findForward(target);
				}
				else
				{
					if (strCounterId.trim().length() < 4)
					{
						return mapping.findForward(target);
					}
					else
					{
						return this.OFFLINEMODE(mapping, form, request, response);
					}
				}				
			}
			else
			{
				return mapping.findForward(target);
			}
		}
		else
		{
			return this.OFFLINEMODE(mapping, form, request, response);
		}
	}

	
	
	
	/**
	 * forwards Cash Collection in Off line Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward OFFLINEMODE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		String target = "cashcollection";
		LFNoTransFB formBean = (LFNoTransFB) form;
		
		if(BillConfigUtil.BACK_DATE_DAYEND_STOP_PROCESS.equals("1"))
		{		
			if(request.getSession().getAttribute("BACK_DATE_DAY_END_FLAG").toString().equals("1"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Perform Day End First.");
				return mapping.findForward(target);
			}
			else if(request.getSession().getAttribute("BACK_DATE_DAY_END_FLAG").toString().equals("2"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Deposit Day End Amount.");
				return mapping.findForward(target);
			}
		}
		
		
		String strCounterMode = "0";
		if(request.getParameter("counterMode") != null)
		{
			 strCounterMode = request.getParameter("counterMode");
		}
		else
		{
			strCounterMode = formBean.getStrCounterMode();
		}
		
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		if(formBean.getStrAccountStatus()==null ||formBean.getStrAccountStatus()=="")
	 	{
			formBean.setStrAccountStatus("1");
	 	}
		LFNoTransDATA.preInitOffLineDetails(formBean );//Populates Hospital Service Combo and Request Type Combo Only
		
		formBean.setStrCounterMode(strCounterMode);
		if(formBean.getStrAccountStatus().equals("4"))
		{
			target = "LFAccountSummary";
			System.out.println("I M IN 4!!!!!");
		}
		else if(formBean.getStrAccountStatus().equals("2"))
		{
			target = "LFAccountClose";
			System.out.println("I M IN 2!!!!!");
		}
		else if(formBean.getStrAccountStatus().equals("3"))
		{
			target = "partpay";
			System.out.println("I M IN 3!!!!!");
		}
		
		
		else
		{
			target = "cashcollection";
			 System.out.println("I M IN 1!!!!!");
		}
		
		
		
		return mapping.findForward(target);
	}

	
	
	/**
	 * forwards control to the Cash Collection Page by initializing all the
	 * required information for given Cr. Number.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		//Gets Patient Details,Combos Like Client,Payment Mode,RelastrAccountStatustion,Groups,Tariffs,Department,Episode,Category,Configuration Parameters  
		
		String strTarget = "";
		LFNoTransFB formBean = (LFNoTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		System.out.println("formBean.getStrLFAccountStatus()>>>>>>>>>>>A>>>>>>>>"+formBean.getStrAccountStatus());
		if(formBean.getStrCRorLFwise().equals("2"))
		{
			LFNoTransDATA.GetCrFromLFNo(formBean,request);  
		}
		System.out.println("formBean.setStrCrNo()"+formBean.getStrCrNo());
		
		boolean bResult = LFNoTransDATA.init(request, formBean);
System.out.println("formBean.getStrLFAccountStatus()>>>>>>>>>>>B>>>>>>>>"+formBean.getStrAccountStatus());
		if(formBean.getStrAccountStatus().equals("4"))
		{
			strTarget = "LFAccountSummary";
			System.out.println("I M IN 4!!!!!");
		}
		else if(formBean.getStrAccountStatus().equals("2"))
		{
			strTarget = "LFAccountClose";
			System.out.println("I M IN 2!!!!!");
		}
		else if(formBean.getStrAccountStatus().equals("3"))
		{
			strTarget = "partpay";
			System.out.println("cr or lf wise"+formBean.getStrCRorLFwise());
			System.out.println("I M IN 3!!!!!");
		}
		
		
		else
		{
			 strTarget = "cashcollection";
			 System.out.println("I M IN 1!!!!!");
		}
		
		System.out.println("bResult>>"+bResult);
		if (bResult) 
		{
			
			return mapping.findForward(strTarget);
			
		} 
		else 
		{
			return this.OFFLINEMODE(mapping, formBean, request, response);
		}
	}
  
	public void GetCrFromLF(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException,SQLException
	{

		String strTarget = "";
		LFNoTransFB formBean = (LFNoTransFB) form;
		System.out.println("formBean.getStrLFNo()>>>>>>>>>>>A>>>>>>>>"+formBean.getStrLFNo());
		 LFNoTransDATA.GetCrFromLFNo(formBean,request);
      System.out.println("formBean.getStrLFAccountStatus()>>>>>>>>>>>B>>>>>>>>"+formBean.getStrCrNo());

      this.GO( mapping, formBean, request, response);
		
		
	}
	  
	

	/**
	 * function invoked by Ajax, populates the Episode Combo box based on
	 * Raising Department and Cr Number.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward EPISODEDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		LFNoTransDATA.getEpisodeDetails(request, response);

		return null;

	}
 

	

	

	
	/**
	 * function invoked by Ajax, gives the Part Payment or Advance amount
	 * Details based on Hospital Service, Request Type, Bill Service, Treatment
	 * Category and Ward Code.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward PARTACCAMT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		LFNoTransDATA.getPartPaymentOrAccountDtls(request, response);

		return null;

	}

	
	
	
	public ActionForward OFFLINETREATCATDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		LFNoTransDATA.getOfflineTreatmentCategoryDtls(request, response);

		return null;

	}
	

	public ActionForward OFFLINEDEPTDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		LFNoTransDATA.getOfflineRaisingDetapartmentDtls(request, response);

		return null;

	}
	
	
	
	public ActionForward OFFLINEWARDDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		LFNoTransDATA.getOfflineWardDtls(request, response);

		return null;

	}
	 
	
	public ActionForward OFFLINESPLWARDDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		LFNoTransDATA.getOfflineSpecialWardDtls(request, response);

		return null;

	}
	
	 
	          
	
	
	/**
	 * inserts "Off-line Receipt Part Payment" Details
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward OFFRECPARTPAY(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		LFNoTransFB formBean = (LFNoTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());		
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		if(request.getSession().getAttribute("USER_LEVEL") != null)
		{			
			formBean.setStrUserLevel(request.getSession().getAttribute("USER_LEVEL").toString());			
		}
		else
		{
			formBean.setStrUserLevel("1");
		}		
		LFNoTransDATA.insertOfflineReceiptPartPayment(formBean , request);		
		return this.OFFLINEMODE(mapping, formBean, request, response);		
	}
	
	
	/**
	 * inserts "Off-line Receipt Advance Amount" Details
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward RECLFADV(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		LFNoTransFB formBean = (LFNoTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());		
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());		

		if(request.getSession().getAttribute("USER_LEVEL") != null)
		{
			formBean.setStrUserLevel(request.getSession().getAttribute("USER_LEVEL").toString());			
		}
		else
		{
			formBean.setStrUserLevel("1");
		}
		LFNoTransDATA.insertLFlineReceiptAdvance(formBean , request);		
		return this.OFFLINEMODE(mapping, formBean, request, response);		
	}
	
	
	
	
	public ActionForward PRINTSLIP(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		LFNoTransFB formBean = (LFNoTransFB) form;
 		if(request.getParameter("filePath")!=null)
		{
			formBean.setFilePath(request.getParameter("filePath"));
			request.setAttribute("filePath", request.getParameter("filePath"));
		}
		return mapping.findForward("printPopUp");
	}

	public ActionForward CLOSEACCOUNT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//String target = "cashcollection";

		LFNoTransFB formBean = (LFNoTransFB) form;

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR")
				.toString());

	
		

		if(request.getSession().getAttribute("USER_LEVEL") != null){
			
			formBean.setStrUserLevel(request.getSession().getAttribute("USER_LEVEL")
					.toString());
			
		}else{
			formBean.setStrUserLevel("1");
		}
	 LFNoTransDATA.CLOSEACCOUNT(formBean , request);

	 // PRINTSLIP(mapping, formBean, request, response);
		
			return this.unspecified(mapping, formBean, request, response);
		
	}
	public ActionForward ONLINE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		LFNoTransFB formBean = (LFNoTransFB) form;		
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/CashCollectionOnlineTransCNT.cnt?hmode=ONLINEMODE&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	public ActionForward OFFLINE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		LFNoTransFB formBean = (LFNoTransFB) form;		
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/CashCollectionOfflineTransCNT.cnt?hmode=OFFLINEMODE&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	public ActionForward PATWALLET(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		LFNoTransFB formBean = (LFNoTransFB) form;			
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/PatWalletTransCNT.cnt?hmode=OFFLINEMODE&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	public ActionForward WITHOUTCRNO(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		LFNoTransFB formBean = (LFNoTransFB) form;				
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/CashCollectionWithoutCrTransCNT.cnt?hmode=WITHOUTCRNO&isOnline=0&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	public ActionForward ADVANCED(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		LFNoTransFB formBean = (LFNoTransFB) form;				
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/AdvanceCashCollectionOnlineTransCNT.cnt?hmode=ONLINEMODE&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
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
	public ActionForward CANCELPAGE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
        ActionForward acFwd = new ActionForward();
        acFwd.setPath("/hisglobal/initPage.jsp");
        acFwd.setContextRelative(true);
        return acFwd;
    }	
}
