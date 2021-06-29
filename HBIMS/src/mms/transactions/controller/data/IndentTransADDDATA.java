package mms.transactions.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.MmsConfigUtil;
import mms.transactions.bo.IndentTransADDBO;
import mms.transactions.bo.IndentViewTransBO;
import mms.transactions.bo.RequestForLPPatientBO;
import mms.transactions.controller.fb.IndentTransADDFB;
import mms.transactions.controller.fb.IndentViewTransFB;
import mms.transactions.controller.fb.RequestForLPPatientFB;
import mms.transactions.controller.hlp.ApprovalDtlHLP;
import mms.transactions.controller.hlp.IndentTransADDHLP;
import mms.transactions.controller.hlp.IndentViewTransHLP;
import mms.transactions.controller.hlp.RequestForLPPatientHLP;
import mms.transactions.dao.RequestForLPPatientDAO;
import mms.transactions.vo.IndentTransADDVO;
import mms.transactions.vo.IndentViewTransVO;
import mms.transactions.vo.RequestForLPPatientVO;

/**
 * @author Amit Kumar
 * Date of Creation : 31/3/2009
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : Store
 */
public class IndentTransADDDATA 
{
	/**
	 * Method is Used to Populate the Data for Save Page of
	 * Indent Issue Transaciton  Page 
	 * @param formBean
	 * @param request
	 */
	public static void GetData(IndentTransADDFB formBean,HttpServletRequest request,HttpServletResponse response) 
	{
		IndentTransADDBO     bo = null;
		IndentTransADDVO     vo = null;
		String       strmsgText = "";
		String             path = "";
		HisUtil            util = null;
		String          combo[] = null;
		String[]        strTemp = null;  
		String[]       strTemp1 = null; 
		MmsConfigUtil mmsConfig = null;
		String strCostRequired;
		
//Change Request
		String strCurrentDate;
		int strCurrentMonth;
		int CURRENT_YEAR;
		String strFinancialStartDate;
		String strFinancialEndDate;	
		String     strRes = null;
		String hosCode=null;
		try 
		{
			      		bo = new IndentTransADDBO();
		    			vo = new IndentTransADDVO();
		    	          hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
		    	          mmsConfig = new MmsConfigUtil(hosCode);
			          util = new HisUtil("MMSModule", "IndentTransADDDATA");
		   strCostRequired = mmsConfig.getStrCostReq();
			         combo = request.getParameterValues("combo");
			       strTemp =  combo[0].split("\\^");
			          path = "/mms"+request.getParameter("cnt_page")+".cnt";
				
			if(request.getParameter("cnt_page") == null)
			{
				
				path = request.getParameter("strPath");
			}
			
			formBean.setStrPath(path.trim());
			
			vo.setStrStoreId(strTemp[0]);     // Store ID
			vo.setStrStoreTypeId(strTemp[1]); // Store Type ID
			vo.setStrItemCategory(combo[1]);  // Item category 
			
            strTemp1 = combo[2].split("\\^"); 
			
			vo.setStrIndentTypeId(strTemp1[1]);    // Request Type ID
						
			// Get value from Session

           // String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatId  = request.getSession().getAttribute("SEATID").toString();
			
			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatId);
	       	
			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatId);
			
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
			
            
			// Calling BO method
			bo.GetData(vo);
			
			//Change Request
			
			if(mmsConfig.getStrBudgetFlg().equals("1"))
			{
				formBean.setStrAvalaibleBudget(vo.getStrAvalaibleBudget());
				formBean.setStrAvalaibleBudgetDtl(vo.getStrAvalaibleBudgetDtl());	
			}
			
//		 	response.setHeader("Cache-Control", "no-cache");
//		 	strRes = vo.getStrAvalaibleBudget()+"##"+vo.getStrAvalaibleBudgetDtl();
//		 	response.getWriter().print(strRes);
			//Change Request End

		 	
			bo.CallFunction(vo);
						
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			formBean.setStrGroupIdForItemSearch(vo.getStrGroupIdForItemSearch());
			formBean.setStrStoreName(combo[0]);
			formBean.setStrStoreId(vo.getStrStoreId());
			formBean.setStrStoreTypeId(vo.getStrStoreTypeId());
			formBean.setStrItemCatNo(vo.getStrItemCategory());
			formBean.setStrIndentTypeId(vo.getStrIndentTypeId());
			formBean.setStrToStoreCombo(vo.getStrToStoreCombo());
			formBean.setStrIndentTypeFunc(vo.getStrIndentTypeFunc());
			formBean.setStrStoreNameFunc(vo.getStrStoreNameFunc());
			formBean.setStrItemCategoryFunc(vo.getStrItemCategoryFunc());
			formBean.setStrIndentPeriodCombo(vo.getStrIndentPeriodCombo());
			formBean.setStrIndentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCostRequired(strCostRequired);
		}
		  catch (Exception e) 
		  {
            //e.printStackTrace(); 
			strmsgText = "IndentTransADDDATA.GetData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"IndentTransADDDATA->GetData()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "+eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}

	}
	/**
	 * Method is Used to Insert Data in DataBase Table 
	 * Indent for Issue Transaction 
	 * @param formBean
	 * @param request
	 */
	
	public static boolean  INSERT(IndentTransADDFB formBean,HttpServletRequest request) 
	{
		IndentTransADDBO bo = null;
		IndentTransADDVO vo = null;
		HisUtil        util = null;
        boolean    retValue = true;
		MmsConfigUtil   mcu = null;
		String strmsgText;
        String strFinancialStartYear;
        String strFinancialEndYear;
        String hosCode=null;
      
		try 
		{
								  bo = new IndentTransADDBO();
								  vo = new IndentTransADDVO();
				    	          hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			    	              mcu = new MmsConfigUtil(hosCode);
				     
					            util = new HisUtil("MMSModule", "IndentTransADDDATA");
			    	 
					  String  seatid = request.getSession().getAttribute("SEATID").toString();
			
		
			String        strReqType = request.getParameter("strIndentTypeId");
			String           strPath = request.getParameter("strPath");
			String        strStoreId = request.getParameter("strStoreId");
			String    strStoreTypeId = request.getParameter("strStoreTypeId");
			String strItemCategoryNo = request.getParameter("strItemCatNo");
			
		  	   strFinancialStartYear = mcu.getStrFinancialStartDate(strStoreId , request.getSession().getAttribute("HOSPITAL_CODE").toString());
			     strFinancialEndYear = mcu.getStrFinancialEndDate(strStoreId , request.getSession().getAttribute("HOSPITAL_CODE").toString());

			
			
			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatid);
			formBean.setStrPath(strPath);
	       	/* Setting Value in VO */
			if(formBean.getStrToStoreCombo() != null && !formBean.getStrToStoreCombo().equals("") )
				vo.setStrToStoreCombo(formBean.getStrToStoreCombo());
			else
				vo.setStrToStoreCombo(formBean.getStrToStore());
			vo.setStrReqType(strReqType);
			vo.setStrStoreId(strStoreId);
			vo.setStrStoreTypeId(strStoreTypeId);
			vo.setStrItemCatNo(strItemCategoryNo);
			vo.setStrFinancialEndYear(strFinancialEndYear);
	        vo.setStrFinancialStartYear(strFinancialStartYear);
			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);
			if(formBean.getIsNormal().equals("0"))
			{
				vo.setStrUrgentFlg("1");
			}	
			else
			{
				vo.setStrUrgentFlg("0");
			}	
			vo.setStrIndentPeriodValue(formBean.getStrIndentPeriodValue());
			vo.setStrIndentPeriod(formBean.getStrIndentPeriod());
			vo.setItemParamValue(formBean.getItemParamValue());
			vo.setStrBkgEntryDate(util.getASDate("dd-MMM-yyyy"));
			vo.setStrReqQty(formBean.getStrReqQty());			
			vo.setStrUnitName(formBean.getStrUnitName());
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrStoreName(formBean.getStrStoreName());
		
			//Change Request
				vo.setStrAvalaibleBudget( (formBean.getStrAvalaibleBudget()!=null && !formBean.getStrAvalaibleBudget().equals("") ) ? formBean.getStrAvalaibleBudget():"0");
				vo.setStrAvalaibleBudgetDtl( (formBean.getStrAvalaibleBudgetDtl()!=null && !formBean.getStrAvalaibleBudgetDtl().equals("") ) ? formBean.getStrAvalaibleBudgetDtl():"0");	
			
				vo.setStrBSReqNo(formBean.getStrBSReqNo());
			/* Calling BO Insert method */
		   	bo.INSERT(vo);
			
    		if (vo.getStrMsgType().equals("1")) 
			{
    			retValue = false;
    			vo.setStrIndentNo("0");
				throw new Exception(vo.getStrMsgString());
				
			}
        	else 
			{
        		
				formBean.setStrMsg("Indent Successfully Raised!!");
				formBean.setStrIndentNo(vo.getStrIndentNo());
			}
			
		}
		  catch (Exception e) 
		  {

			retValue = false;
            //e.printStackTrace(); 
			strmsgText = "IndentTransADDDATA.INSERT(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"IndentTransADDDATA->INSERT()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "+eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}
		return retValue;

	}
	
	
	public static void placeRegularIndent(IndentTransADDFB formBean,HttpServletRequest request) 
	{
		IndentTransADDBO bo = null;
		IndentTransADDVO vo = null;
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
			bo = new IndentTransADDBO();
			vo = new IndentTransADDVO();
            System.out.println("In the PlaceRegularIndent");
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatId  = request.getSession().getAttribute("SEATID").toString();
				 mmsConfig = new MmsConfigUtil(hosCode);
	          util = new HisUtil("MMSModule", "IndentTransADDDATA");
			
	        
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
		
			vo.setStrIndentNo(temp1[0]);
			vo.setStrReqType(temp1[2]);
			vo.setStrStoreId(temp1[1]);
			vo.setStrToStore(temp2[0]);
			
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
			formBean.setStrStoreId(temp1[1]);
			
         	bo.PLACEREGULARINDENT(vo);
         //	IndentTransADDDAO.GetRecommendByCombo(vo);
         	
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			
				str1=IndentTransADDHLP.getItemDetails1(vo);
			formBean.setStrSetItemDetails(str1);
			formBean.setStrIndentDate(strCurrentDate);
			if(temp1[2].equals("86"))
			{
			formBean.setStrIndentTypeId("13");
			//formBean.setStrTmpReqType("13");
			formBean.setStrIndentType("Indent for Issue (Patient Wise)");
			}
			else
			{
				formBean.setStrIndentTypeId("17");
				//formBean.setStrTmpReqType("17");
				formBean.setStrIndentTypeFunc("Indent for Issue (Dept. Wise)");
			}
			vo.getStrItemDetailsWs().beforeFirst();
			vo.getStrItemDetailsWs().next();
			formBean.setStrItemCatNo(temp1[3]);
			formBean.setStrItemCategoryFunc(vo.getStrItemDetailsWs().getString(22));
			formBean.setStrStoreNameFunc(vo.getStrItemDetailsWs().getString(20));
			formBean.setStrStoreId(temp1[1]);
			formBean.setStrToStore(vo.getStrItemDetailsWs().getString(27));
			formBean.setStrToStoreCombo(vo.getStrItemDetailsWs().getString(21));
			//formBean.setStrPatientDtlHidVal(vo.getStrItemDetailsWs().getString(23));
			//formBean.setStrRecmndByCombo(vo.getStrRecmndByCombo());
			formBean.setStrStoreName(temp1[1]);
			//formBean.setStrItemCatg(temp1[3]);
			formBean.setStrStoreTypeId("999");
			//formBean.setStrToStoreCombo(vo.getStrToStore());
			formBean.setStrIndentPeriod(vo.getStrItemDetailsWs().getString(26));
			formBean.setStrIndentPeriodCombo(vo.getStrItemDetailsWs().getString(25));
			
			String path = "/mms"+request.getParameter("cnt_page")+".cnt";
	        if(request.getParameter("cnt_page") == null)
			{
			   path = request.getParameter("strPath");
			   
			}
	        
	        formBean.setStrPath(path.trim());			
	        formBean.setStrReqQty(vo.getStrReqQty());
		}
		  catch (Exception e) 
		  {
            e.printStackTrace(); 
			strmsgText = "IndentTransADDDATA.viewData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"IndentTransADDDATA->placeregluarindent()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}

	}
}
