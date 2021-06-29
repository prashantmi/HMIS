package mms.transactions.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;

import mms.MmsConfigUtil;
import mms.transactions.bo.AnnualPurchaseIndentBO;
import mms.transactions.bo.IndentViewTransBO;
import mms.transactions.controller.fb.AnnualPurchaseIndentFB;
import mms.transactions.controller.fb.IndentViewTransFB;
import mms.transactions.controller.hlp.ApprovalDtlHLP;
import mms.transactions.controller.hlp.IndentViewTransHLP;
import mms.transactions.vo.AnnualPurchaseIndentVO;
import mms.transactions.vo.IndentViewTransVO;

public class IndentViewTransDATA {
	
	/**
	 * Method is Used to get the Data for view Page of
	 * Indent Desk 
	 * @param formBean
	 * @param request
	 */
	public static void viewData(IndentViewTransFB formBean,HttpServletRequest request) 
	{
		IndentViewTransBO bo = null;
		IndentViewTransVO vo = null;
		String strmsgText = "";
		String str1 ="";
		String str2 ="";
		String str3 ="";
		//Change Request
		String strCurrentDate;
		int strCurrentMonth;
		int CURRENT_YEAR;
		String strFinancialStartDate;
		String strFinancialEndDate;	
		HisUtil            util = null;
		MmsConfigUtil mmsConfig = null;

		
		try 
		{
			bo = new IndentViewTransBO();
			vo = new IndentViewTransVO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatId  = request.getSession().getAttribute("SEATID").toString();
				 mmsConfig = new MmsConfigUtil(hosCode);
	          util = new HisUtil("MMSModule", "IndentViewTransDATA");
			
			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatId);
			
	       	vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatId);
			
			String strChk = formBean.getStrChk();
			
//			System.out.println("strChk-"+strChk);
			String[] temp  = strChk.split("\\^");
			String[] temp1 = temp[0].split("\\@");
			String[] temp2 = temp1[6].split("\\$");
	//		String strIndentPeriod = temp2[0];

    //		System.out.println("Req No-->>"+temp1[0]);
    //  	System.out.println("Store Id-->>"+temp1[1]);
    //		System.out.println("Req Type Id-->>"+temp1[2]);
		
			vo.setStrReqNo(temp1[0]);
			vo.setStrReqTypeId(temp1[2]);
			vo.setStrStoreId(temp1[1]);
			vo.setStrToStoreId(temp2[0]);
			
			//Change Request
            strCurrentDate  = util.getASDate("dd-MM-yyyy");

			strCurrentMonth = Integer.parseInt( strCurrentDate.split("\\-")[1] );
			
			
			if(strCurrentMonth>=4 )
			{
				CURRENT_YEAR = Integer.parseInt( strCurrentDate.split("\\-")[2] );
			}
			else
			{
				CURRENT_YEAR = Integer.parseInt( strCurrentDate.split("\\-")[2] ) - 1 ;	
			}
							
			strFinancialStartDate = "01-Apr"+"-" + CURRENT_YEAR; 
			
			strFinancialEndDate = "31-Mar"+"-" + (CURRENT_YEAR+1);	
			  
			vo.setStrFinancialEndDate(strFinancialEndDate);
			vo.setStrFinancialStartDate(strFinancialStartDate);
			
			
         	bo.viewData(vo);
         	
         	//Change Request
			
			if(mmsConfig.getStrBudgetFlg().equals("1"))
			{
				formBean.setStrAvalaibleBudget(vo.getStrAvalaibleBudget());
				formBean.setStrAvalaibleBudgetDtl(vo.getStrAvalaibleBudgetDtl());	
			}
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			
			if(temp1[2].equals("12") || temp1[2].equals("13") || temp1[2].equals("14") || temp1[2].equals("10"))
			{
				str1=IndentViewTransHLP.getItemDetails1(vo);
			}
			else
			{
				
				str1=IndentViewTransHLP.getItemDetails(vo);
			}	
			
			//System.out.println("Req Type:::"+temp1[2]);
			str2  =  ApprovalDtlHLP.getApprovalDtl(vo.getStrStoreId(), vo.getStrHospitalCode(), vo.getStrToStoreId(), temp1[3], vo.getStrReqTypeId(), vo.getStrReqNo());
			str3  =  IndentViewTransHLP.getIndentDetails(vo);
			formBean.setStrSetItemDetails(str1);
			formBean.setStrSetApprovedDetails(str2);
			formBean.setStrIndentDetails(str3);
			formBean.setStrRequestName(vo.getStrIndentName());
			if(temp1[2].equals("11")|| temp1[2].equals("86"))
			{
				formBean.setStrBudgetRequired("0");
			}
		}
		  catch (Exception e) 
		  {
            e.printStackTrace(); 
			strmsgText = "IndentViewTransDATA.viewData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"IndentViewTransDATA->viewData()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}

	}
	
	
	public static void modifyData(IndentViewTransFB formBean,HttpServletRequest request) 
	{
		IndentViewTransBO bo = null;
		IndentViewTransVO vo = null;
		String strmsgText = "";
		String str1 ="";
		String str2 ="";
		String str3 ="";
		//Change Request
		String strCurrentDate;
		int strCurrentMonth;
		int CURRENT_YEAR;
		String strFinancialStartDate;
		String strFinancialEndDate;	
		HisUtil            util = null;
		MmsConfigUtil mmsConfig = null;

		
		try 
		{
			bo = new IndentViewTransBO();
			vo = new IndentViewTransVO();
            System.out.println("In the modify");
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatId  = request.getSession().getAttribute("SEATID").toString();
				 mmsConfig = new MmsConfigUtil(hosCode);
	          util = new HisUtil("MMSModule", "IndentViewTransDATA");
			
			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatId);
			
	       	vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatId);
			
			String strChk = formBean.getStrChk();
			//161114110003@12401108@11@16@0@10@12401107$1
		System.out.println("strChk-"+strChk);
			String[] temp  = strChk.split("\\^");
			String[] temp1 = temp[0].split("\\@");
			String[] temp2 = temp1[6].split("\\$");
	//		String strIndentPeriod = temp2[0];

	System.out.println("Req No-->>"+temp1[0]);
  	System.out.println("Store Id-->>"+temp1[1]);
  	System.out.println("Req Type Id-->>"+temp1[2]);
		
			vo.setStrReqNo(temp1[0]);
			vo.setStrReqTypeId(temp1[2]);
			vo.setStrStoreId(temp1[1]);
			vo.setStrToStoreId(temp2[0]);
			
			//Change Request
            strCurrentDate  = util.getASDate("dd-MM-yyyy");

			strCurrentMonth = Integer.parseInt( strCurrentDate.split("\\-")[1] );
			
			
			if(strCurrentMonth>=4 )
			{
				CURRENT_YEAR = Integer.parseInt( strCurrentDate.split("\\-")[2] );
			}
			else
			{
				CURRENT_YEAR = Integer.parseInt( strCurrentDate.split("\\-")[2] ) - 1 ;	
			}
							
			strFinancialStartDate = "01-Apr"+"-" + CURRENT_YEAR; 
			
			strFinancialEndDate = "31-Mar"+"-" + (CURRENT_YEAR+1);	
			  
			vo.setStrFinancialEndDate(strFinancialEndDate);
			vo.setStrFinancialStartDate(strFinancialStartDate);
			formBean.setStrRaisingStore(temp1[1]);
			
         	bo.ModifyData(vo);
         	
         	//Change Request
			
			if(mmsConfig.getStrBudgetFlg().equals("1"))
			{
				formBean.setStrAvalaibleBudget(vo.getStrAvalaibleBudget());
				formBean.setStrAvalaibleBudgetDtl(vo.getStrAvalaibleBudgetDtl());	
			}
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			
			if(temp1[2].equals("12") || temp1[2].equals("13") || temp1[2].equals("14") || temp1[2].equals("10"))
			{
				str1=IndentViewTransHLP.getItemDetails1(vo);
			}
			else
			{
				
				str1=IndentViewTransHLP.getItemDetailsModify(vo);
			}	
			
		    System.out.println("Req Type:::"+temp1[2]);
			str2  =  ApprovalDtlHLP.getApprovalDtl(vo.getStrStoreId(), vo.getStrHospitalCode(), vo.getStrToStoreId(), temp1[3], vo.getStrReqTypeId(), vo.getStrReqNo());
			str3  =  IndentViewTransHLP.getIndentDetailsModify(vo);
			System.out.println(str2);
			formBean.setStrSetItemDetails(str1);
			formBean.setStrSetApprovedDetails(str2);
			formBean.setStrIndentDetails(str3);
			formBean.setStrRequestName(vo.getStrIndentName());
			if(temp1[2].equals("11")|| temp1[2].equals("86"))
			{
				formBean.setStrBudgetRequired("0");
			}
		}
		  catch (Exception e) 
		  {
            e.printStackTrace(); 
			strmsgText = "IndentViewTransDATA.viewData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"IndentViewTransDATA->viewData()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}

	}
	
	
	public static void  INSERT(IndentViewTransFB formBean,HttpServletRequest request) 
	{
		IndentViewTransBO bo = null;
		IndentViewTransVO vo = null;
		String strmsgText = "";
		String str1 ="";
		String str2 ="";
		String str3 ="";
		//Change Request
		String strCurrentDate;
		int strCurrentMonth;
		int CURRENT_YEAR;
		String strFinancialStartDate;
		String strFinancialEndDate;	
		HisUtil            util = null;
		MmsConfigUtil mmsConfig = null;

		
		try 
		{
			bo = new IndentViewTransBO();
			vo = new IndentViewTransVO();
            System.out.println("In the modify");
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatId  = request.getSession().getAttribute("SEATID").toString();
				 mmsConfig = new MmsConfigUtil(hosCode);
	          util = new HisUtil("MMSModule", "IndentViewTransDATA");
			
			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatId);
			
	       	vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatId);
			
			String strChk = formBean.getStrChk();
			
			System.out.println("strChk-"+strChk);
		/*	String[] temp  = strChk.split("\\^");
			String[] temp1 = temp[0].split("\\@");
			String[] temp2 = temp1[6].split("\\$");*/
	//		String strIndentPeriod = temp2[0];

 	System.out.println("Req No-->>"+vo.getStrReqNo());
    //  	System.out.println("Store Id-->>"+temp1[1]);
    //		System.out.println("Req Type Id-->>"+temp1[2]);
		
			/*vo.setStrReqNo(temp1[0]);
			vo.setStrReqTypeId(temp1[2]);
			vo.setStrStoreId(temp1[1]);
			vo.setStrToStoreId(temp2[0]);
		     vo.setStrReqQty(formBean.getStrReqQty());*/
		    // Calling BO Method		
 	  vo.setStrIndentNo(formBean.getStrIndentNo());
 	  vo.setStrRaisingStore(formBean.getStrRaisingStore());
 	  vo.setStrReqQty(formBean.getStrReqQty());
 	  vo.setStrIndentTypeId(formBean.getStrIndentTypeId());
 	  vo.setStrItemBrandIds(formBean.getStrItemBrandIds());
 	  //System.out.println("formBean.getStrModifedQty()"+formBean.getStrModifedQty());
 	 //System.out.println("formBean.getStrItemBrandIds()"+formBean.getStrItemBrandIds());
 	  vo.setStrModifedQty(formBean.getStrModifedQty());
			bo.INSERT(vo);
			
			if (vo.getStrMsgType().equals("1")) 
			{
				
				throw new Exception(vo.getStrMsgString());
			}
	    	else 
			{
	    		formBean.setStrMsg("Indent Modified !!");
			}
			
		}
		  catch (Exception e) 
		  {
			  
	        e.printStackTrace(); 
			strmsgText = "BreakgeItemDtlTransDATA.INSERT(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"BreakgeItemDtlTransDATA->INSERT()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "+eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}
	

	}

}
