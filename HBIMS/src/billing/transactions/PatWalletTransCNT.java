package billing.transactions;
/*

/***************************Start of program*****************************\

## Copyright Information : C-DAC, Noida  

## Project Name :NIMS

## Name of Developer  : Ajay Deshwal		 

## Module Name : BIlling

## Process/Database Object Name :

## Purpose : Wallet Cash collection Process

## Date of Creation : 19-Sept.-2016

## Modification Log : 

## Modify Date : 

## Reason (CR/PRS) : 

## Modify By :*/
import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import hisglobal.presentation.CSRFGardTokenAction;


import hisglobal.vo.UserVO;
import billing.BillConfigUtil;
import billing.reports.BillEnquiryRptDATA;
import billing.reports.BillEnquiryRptFB;

public class PatWalletTransCNT extends CSRFGardTokenAction {

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
		generateToken(request);

		PatWalletTransFB formBean = (PatWalletTransFB) form;
		String strCounterId ="";
		String target = "cashcollection";
		
        UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
		
		//System.out.println("Back Date Day End Check Flag :::value :::"+userVO.getCheckBackDateDayEndFlag());			
		
			
		if(BillConfigUtil.BACK_DATE_DAYEND_STOP_PROCESS.equals("1"))
		{		
			if(userVO.getCheckBackDateDayEndFlag().toString().equals("1"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Perform Day End First.\n If Day End Already Performed , Please Relogin");
				return mapping.findForward(target);
			}
			else if(userVO.getCheckBackDateDayEndFlag().toString().equals("2"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Deposit Day End Amount.\n If Already Deposited , Please Relogin");
				return mapping.findForward(target);
			}
		}
		
		
		BillConfigUtil bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrConfirmationType(bcu.getGeneralCashCollectionConfrimType());
		//System.out.println("IP Address"+request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrCrNo("");
		formBean.setStrWalletAccountNo("");
		if(formBean.getStrAccountStatus()==null ||formBean.getStrAccountStatus()=="")
	 	{
			formBean.setStrAccountStatus("1");
	 	}
		//if(formBean.getStrCRorWalletwise()==null ||formBean.getStrCRorWalletwise()=="")
	 //	{
		formBean.setStrCRorWalletwise("1");
		
	 	//}
		String counterCheck=BillConfigUtil.CHECK_COUNTER_STATUS;
		
		
		
		if(counterCheck.equals("1"))//Counter Check Needed=1, Counter Check Not Needed=0
		{
			strCounterId =PatWalletTransDATA.checkCounterStatus(request, formBean);
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
		generateToken(request);

		String target = "cashcollection";
		PatWalletTransFB formBean = (PatWalletTransFB) form;
        UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
		
		//System.out.println("Back Date Day End Check Flag :::value :::"+userVO.getCheckBackDateDayEndFlag());			
		
			
		if(BillConfigUtil.BACK_DATE_DAYEND_STOP_PROCESS.equals("1"))
		{		
			if(userVO.getCheckBackDateDayEndFlag().toString().equals("1"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Perform Day End First.\n If Day End Already Performed , Please Relogin");
				return mapping.findForward(target);
			}
			else if(userVO.getCheckBackDateDayEndFlag().toString().equals("2"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Deposit Day End Amount.\n If Already Deposited , Please Relogin");
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
		PatWalletTransDATA.preInitOffLineDetails(formBean );//Populates Hospital Service Combo and Request Type Combo Only
		
		formBean.setStrCounterMode(strCounterMode);
		if(formBean.getStrAccountStatus().equals("4"))
		{
			target = "PatWalletAccountSummary";
			//System.out.println("I M IN 4!!!!!");
		}
		else if(formBean.getStrAccountStatus().equals("2"))
		{
			target = "PatWalletAccountClose";
			//System.out.println("I M IN 2!!!!!");
		}
		else if(formBean.getStrAccountStatus().equals("3"))
		{
			target = "partpay";
			//System.out.println("I M IN 3!!!!!");
		}
		
		
		else
		{
			target = "cashcollection";
			 //System.out.println("I M IN 1!!!!!");
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
		generateToken(request);

		String strTarget = "";
		PatWalletTransFB formBean = (PatWalletTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//System.out.println("formBean.getStrWalletAccountStatus()>>>>>>>>>>>A>>>>>>>>"+formBean.getStrAccountStatus());
		if(formBean.getStrCRorWalletwise().equals("2"))
		{
			PatWalletTransDATA.GetCrFromWalletNo(formBean,request);  
		}
		//System.out.println("formBean.setStrCrNo()"+formBean.getStrCrNo());
		
		boolean bResult = PatWalletTransDATA.init(request, formBean);
		//System.out.println("formBean.getStrWalletAccountStatus()>>>>>>>>>>>B>>>>>>>>"+formBean.getStrAccountStatus());
		if(formBean.getStrAccountStatus().equals("4"))
		{
			strTarget = "PatWalletAccountSummary";
			//System.out.println("I M IN 4!!!!!");
		}
		else if(formBean.getStrAccountStatus().equals("2"))
		{
			strTarget = "PatWalletAccountClose";
			//System.out.println("I M IN 2!!!!!");
		}
		else if(formBean.getStrAccountStatus().equals("3"))
		{
			strTarget = "partpay";
			//System.out.println("cr or Wallet wise"+formBean.getStrCRorWalletwise());
			//System.out.println("I M IN 3!!!!!");
		}
		
		
		else
		{
			 strTarget = "cashcollection";
			 //System.out.println("I M IN 1!!!!!");
		}
		
		//System.out.println("bResult>>"+bResult);
		if (bResult) 
		{
			
			return mapping.findForward(strTarget);
			
		} 
		else 
		{
			return this.OFFLINEMODE(mapping, formBean, request, response);
		}
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
	public ActionForward OFFRECPARTPAY(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		validateToken(request, response);

		PatWalletTransFB formBean = (PatWalletTransFB) form;
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
		PatWalletTransDATA.insertOfflineReceiptPartPayment(formBean , request);		
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
	public ActionForward RECWalletADV(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		validateToken(request, response);

		PatWalletTransFB formBean = (PatWalletTransFB) form;
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
		Boolean boolean1=PatWalletTransDATA.insertWalletReceiptAdvance(formBean , request);
		if(boolean1)
		{
			return this.OFFLINEMODE(mapping, formBean, request, response);	
		}else{
			return this.unspecified(mapping, formBean, request, response);	
		}
				
	}
	
	
	
	
	public ActionForward PRINTSLIP(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		PatWalletTransFB formBean = (PatWalletTransFB) form;
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

		PatWalletTransFB formBean = (PatWalletTransFB) form;

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
		PatWalletTransDATA.CLOSEACCOUNT(formBean , request);
		formBean.setStrWalletAccountNo("");

		// PRINTSLIP(mapping, formBean, request, response);
		
			return this.unspecified(mapping, formBean, request, response);
		
	}
	public ActionForward ONLINE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		PatWalletTransFB formBean = (PatWalletTransFB) form;		
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/CashCollectionOnlineTransCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	public ActionForward OFFLINE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		PatWalletTransFB formBean = (PatWalletTransFB) form;		
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/CashCollectionOfflineTransCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	public ActionForward LF(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		PatWalletTransFB formBean = (PatWalletTransFB) form;				
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/LFNoTransCNT.cnt?hmode=OFFLINEMODE&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	public ActionForward WITHOUTCRNO(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		PatWalletTransFB formBean = (PatWalletTransFB) form;				
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/CashCollectionWithoutCrTransCNT.cnt?hmode=WITHOUTCRNO&isOnline=0&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	public ActionForward ADVANCED(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		PatWalletTransFB formBean = (PatWalletTransFB) form;				
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
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		PatWalletTransFB formBean = (PatWalletTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatWalletTransDATA.showReport(formBean, request, response);
	}
	public ActionForward PATWALLET(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		PatWalletTransFB formBean = (PatWalletTransFB) form;				
		/*ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/PatWalletTransCNT.cnt?hmode=OFFLINEMODE&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);*/
		return this.OFFLINEMODE(mapping, formBean, request, response);
	}
}

