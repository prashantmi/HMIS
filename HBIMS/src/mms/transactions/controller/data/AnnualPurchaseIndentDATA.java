package mms.transactions.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;

import mms.MmsConfigUtil;
import mms.transactions.bo.AnnualPurchaseIndentBO;
import mms.transactions.controller.fb.AnnualPurchaseIndentFB;
import mms.transactions.vo.AnnualPurchaseIndentVO;
/**
 * Developer : Amit Kumar
 * Version : 1.0
 * Date : 1/May/2009
 * Modif Date : / /2009 
*/
public class AnnualPurchaseIndentDATA
{
/**
 * Method is Used to Populate the Data for Save Page of
 * Issue To Patient Transaciton ADD Page 
 * @param formBean
 * @param request
 */
public static void GetData(AnnualPurchaseIndentFB formBean,HttpServletRequest request) 
{
	AnnualPurchaseIndentBO bo = null;
	AnnualPurchaseIndentVO vo = null;
	String strmsgText = "";
	HisUtil util = null;
	String[] combo = null;
	String  strStoreId ="";
	String  strStoreTypeId="";
	String  strReqType = "";
	String  strItemCategoryNo ="";
	String path = "";
	
	try 
	{
		bo = new AnnualPurchaseIndentBO();
		vo = new AnnualPurchaseIndentVO();
		
		util = new HisUtil("AnnualPurchaseIndentDATA", "AnnualPurchaseIndentDATA");
		String strCtDate = util.getASDate("dd-MMM-yyyy");
		util = null;	
        
		path = "/mms"+request.getParameter("cnt_page")+".cnt";
		
		if(request.getParameter("cnt_page") == null)
		{
			
			path = request.getParameter("strPath");
		}
		
		
		formBean.setStrPath(path.trim());
		
        combo = request.getParameterValues("combo");
		
		String[] strTemp =  combo[0].split("\\^");
							
		strStoreId = strTemp[0];       // Store Id
		strStoreTypeId = strTemp[1];   // Store Type ID
		strItemCategoryNo = combo[1];  // Item category
		String[] strTemp1 =combo[2].split("\\^"); 
		
        strReqType = strTemp1[1];    // Request Type ID
		
		String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
		String seatId  = request.getSession().getAttribute("SEATID").toString();

		formBean.setStrHospitalCode(hosCode);
		formBean.setStrSeatId(seatId);
		
		vo.setStrHospitalCode(hosCode);
		vo.setStrSeatId(seatId);
		vo.setStrCtDate(strCtDate);
		vo.setStrStoreId(strStoreId);
		vo.setStrStoreTypeId(strStoreTypeId);
		vo.setStrItemCategoryNo(strItemCategoryNo);
		vo.setStrReqType(strReqType);
		
		// Calling BO Method
		bo.GetData(vo);
		formBean.setStrItemCatg(vo.getStrItemCatg());
		formBean.setStrCostRequired("1");
		formBean.setStrItemCatg(vo.getStrItemCatg());
		formBean.setStrStoreTypeId(vo.getStrStoreTypeId());
		formBean.setStrTmpItemCatg(strItemCategoryNo);
		formBean.setStrReqDate(strCtDate);
		formBean.setStrTmpReqType(vo.getStrReqType());
		formBean.setStrItemCatgCombo(vo.getStrItemCatgCombo());
		formBean.setStrGroupIdForItemSearch(vo.getStrGroupIdForItemSearch());
		formBean.setStrTmpStoreName(strStoreId);
		formBean.setStrStoreName(vo.getStrStoreName());
		formBean.setStrToStoreCombo(vo.getStrToStoreCombo());
		formBean.setStrGrantTypeCombo(vo.getStrGrantTypeCombo());
		formBean.setStrRecmndByCombo(vo.getStrRecmndByCombo());
		formBean.setStrIndentPeriodCombo(vo.getStrIndentPeriodCombo());
		
        if (vo.getStrMsgType().equals("1")) 
		{
			throw new Exception(vo.getStrMsgString());
		}
		
		
	}
	  catch (Exception e) 
	  {
       
		strmsgText = "AnnualPurchaseIndentDATA.GetData(vo) --> "
				+ e.getMessage();
		HisException eObj = new HisException("mms",
				"AnnualPurchaseIndentDATA->GetData()", strmsgText);
		formBean.setStrErr("Application Error [ERROR ID : "+eObj.getErrorID()+ "],Contact System Administrator! ");

		eObj = null;
	} finally {
		bo = null;
		vo = null;
	}

}

/**
 * Method is Used to Insert Data in DataBase Table 
 * Breakage Item Detail Transaction 
 * @param formBean
 * @param request
 */

public static boolean  INSERT(AnnualPurchaseIndentFB formBean,HttpServletRequest request) 
{
	AnnualPurchaseIndentBO bo = null;
	AnnualPurchaseIndentVO vo = null;
	String strmsgText;
	MmsConfigUtil mcu = null;
    String strFinancialStartYear;
    String strFinancialEndYear;
    boolean retValue = true;
    String strCostRequired;
  
	try 
	{
							  bo = new AnnualPurchaseIndentBO();
							  vo = new AnnualPurchaseIndentVO();
					
			      String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
						     mcu = new MmsConfigUtil(hosCode);
				  String  seatid = request.getSession().getAttribute("SEATID").toString();
		
		String        strReqType = request.getParameter("strTmpReqType");
		String           strPath = request.getParameter("strPath");
		String        strStoreId = request.getParameter("strTmpStoreName");
		String    strStoreTypeId = request.getParameter("strStoreTypeId");
		String strItemCategoryNo = request.getParameter("strTmpItemCatg");
		         strCostRequired = mcu.getStrCostReq();
		if(formBean.getStrIsNormal().equals("0"))
		{
			vo.setStrUrgentFlg("1");
		}	
		else
		{
			vo.setStrUrgentFlg("0");
		}	
		
		strFinancialStartYear = mcu.getStrFinancialStartDate(strStoreId , hosCode);
		strFinancialEndYear = mcu.getStrFinancialEndDate(strStoreId , hosCode);
	    vo.setStrCostRequired(strCostRequired);
		vo.setStrApproxAmt(formBean.getStrApproxAmt());
		vo.setStrItemType("0");
    	vo.setStrIndentPeriod(formBean.getStrIndentPeriod());
		vo.setStrStoreId(strStoreId);
		vo.setStrStoreTypeId(strStoreTypeId);
		vo.setStrItemCategoryNo(strItemCategoryNo);
		vo.setStrReqType(strReqType);
		vo.setItemParamValue(formBean.getItemParamValue());
		vo.setStrCost(formBean.getStrCost());
		vo.setStrUnitName(formBean.getStrUnitName());
		vo.setStrRemarks(formBean.getStrRemarks());
		vo.setStrReqQty(formBean.getStrReqQty());
		vo.setStrFinancialEndYear(strFinancialEndYear);
		vo.setStrFinancialStartYear(strFinancialStartYear);
	    vo.setStrToStoreCombo(formBean.getStrToStore());
		formBean.setStrPath(strPath);	
	    formBean.setStrHospitalCode(hosCode);
		formBean.setStrSeatId(seatid);
       	vo.setStrHospitalCode(hosCode);
		vo.setStrSeatId(seatid);
		vo.setStrIndentPeriodValue(formBean.getStrIndentPeriodValue());
		formBean.setStrCostRequired(strCostRequired);
		
	    // Calling BO Method			
		bo.INSERT(vo);
		
		if (vo.getStrMsgType().equals("1")) 
		{
			retValue = false;
			throw new Exception(vo.getStrMsgString());
		}
    	else 
		{
    		formBean.setStrMsg("Indent Successfully Raised!!");
		}
		
	}
	  catch (Exception e) 
	  {
		  retValue = false;
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
	return retValue;

}

}
