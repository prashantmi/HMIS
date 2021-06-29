package billing.transactions;

import java.io.IOException;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OnLineRefundApprovalBSDATA
{
	public static void getOnLineRefundApprovalDtls(OnLineRefundApprovalFB formBean,HttpServletRequest request) 
	{
		OnLineRefundApprovalVO voObj = null;
		OnLineRefundApprovalBO bo = null;
	//	boolean retVal = true;
		
		String strTodayRefundApprovalDtls = "";
		String strRefundRequestDtls = "";
		HisUtil hisutil = null;
		
		try 
		{
			voObj = new OnLineRefundApprovalVO();
			   bo = new OnLineRefundApprovalBO();
	      hisutil = new HisUtil("transaction", "OnLineRefundApprovalBSDATA");
			
			
			// Calling Business Object Method
			
	      voObj.setStrHosCode(formBean.getStrHosCode());
	      voObj.setStrSeatId(formBean.getStrSeatId());
	      
			bo.getOnLineRefundApprovalDtl(voObj);
			
			formBean.setTodayRefundApprovalDetails(voObj.getTodayRefundApprovalDetails());
			formBean.setRefundRequestDetails(voObj.getRefundRequestDetails());
			
			if (voObj.getStrMsgType().equals("0")) 
			{
				strTodayRefundApprovalDtls  = OnLineRefundApprovalHLPNew.todayRefundApprovalDtls(formBean);
				      strRefundRequestDtls  = OnLineRefundApprovalHLPNew.refundRequestDtls(formBean);
				formBean.setStrTodayApprovalListDtls(strTodayRefundApprovalDtls);
				formBean.setStrRefundRequestDtls(strRefundRequestDtls);
				
				// Get Approved By Combo
				if (voObj.getApprovedBy() != null && voObj.getApprovedBy().size() > 0) 
				{
					String str = hisutil.getOptionValue(voObj.getApprovedBy(),"0", "", true);
					formBean.setStrRefendedBy(str);
				} 
				else 
				{
					// Set Messages into form bean
					String str = "<option value='0'>Select Value</option>";
					formBean.setStrRefendedBy(str);
					formBean.setStrMsg(voObj.getStrMsgString());
					formBean.setStrMsgType(voObj.getStrMsgType());
					// Check error
					if (formBean.getStrMsgType().equals("1")) 
					{
						throw new Exception(formBean.getStrMsg());
					}
				}
				
				
	
			} 
			else 
			{
				throw new Exception(voObj.getStrMsgString());

			}

		} catch (Exception e) {

					e.printStackTrace();
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"OnLineRefundApprovalBSDATA->getOnLineRefundApprovalDtls()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			bo = null;
			voObj = null;
		}
		//return retVal;
	}
	
	/**
	 * GO(request,response,formBean) -- > This Method generate the Ajax Response
	 * for Bill Details For Bill-Re Print Transaction
	 * 
	 * @param request
	 * @param response
	 * @param formBean
	 */
	public static void SEARCH(HttpServletRequest request,
			HttpServletResponse response, OnLineRefundApprovalFB formBean) 
	{
		// Declaring Variables
		
		String strmsgText = "";
		String strRes = null;
		String strSearchMode;
		String strSearchValue;
		// Creating Object for BO & VO.
		OnLineRefundApprovalVO vo = null;
		OnLineRefundApprovalBO bo = null;
		try {

			vo = new OnLineRefundApprovalVO();
			bo = new OnLineRefundApprovalBO();
			vo.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE")
					.toString());
			// Here we Split Data
			strSearchValue = request.getParameter("strSearchValue");
			strSearchMode  = request.getParameter("strSearchMode");
			
						System.out.println("strSearchValue::::"+strSearchValue);
						System.out.println("strSearchMode::::"+strSearchMode);
						
			vo.setStrSearchMode(strSearchMode);
			vo.setStrSearchValue(strSearchValue);
			// Calling TariffDtls
			bo.getRefundwithCR(vo);
			
			formBean.setRefundRequestDetails(vo.getRefundRequestDetails());
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
					
			} 					
				strRes =  OnLineRefundApprovalHLPNew.refundRequestDtls(formBean);   
			    response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strRes);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"OnLineRefundApprovalBSDATA->GO()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing", "OnLineRefundApprovalBSDATA->GO()",
						strmsgText + "-->" + e1.getMessage());
			}

		} 
	}
	
	/**
	 * GO(request,response,formBean) -- > This Method generate the Ajax Response
	 * for Bill Details For Bill-Re Print Transaction
	 * 
	 * @param request
	 * @param response
	 * @param formBean
	 */
	public static void GO(HttpServletRequest request,
			HttpServletResponse response, OnLineRefundApprovalFB formBean) 
	{
		// Declaring Variables
		
		String strmsgText = "";
		String strRes = null;
		String strSearchMode;
		
		// Creating Object for BO & VO.
		OnLineRefundApprovalVO vo = null;
		OnLineRefundApprovalBO bo = null;
		try {

			vo = new OnLineRefundApprovalVO();
			bo = new OnLineRefundApprovalBO();
			vo.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE")
					.toString());
			// Here we Split Data
			
			strSearchMode  = request.getParameter("strSearchMode");
	    	//System.out.println("strSearchMode::::"+strSearchMode);
			String tmp[] = strSearchMode.replace('^', '#').split("#");
			
			vo.setStrReqNo(tmp[0]);
			
			// Calling TariffDtls
			bo.getTariffDetails(vo);
			
			formBean.setRefundTariffDetails(vo.getRefundTariffDetails());
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
					
			} 
			    strRes =  OnLineRefundApprovalHLPNew.tarrifDtls(formBean,strSearchMode);   
			    response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strRes);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"OnLineRefundApprovalBSDATA->GO()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing", "OnLineRefundApprovalBSDATA->GO()",
						strmsgText + "-->" + e1.getMessage());
			}

		} 
	}
	
	/**
	 * Method is Used to Insert Data in DataBase Table HSTT_BREAKAGE_DTL & HSTT_BREAKAGE_ITEM_DTL
	 * Breakage Item Detail Transaction 
	 * @param formBean
	 * @param request
	 */

	public static boolean  SAVE(OnLineRefundApprovalFB formBean,HttpServletRequest request)
	{
		OnLineRefundApprovalBO bo = null;
		OnLineRefundApprovalVO vo = null;
		String strmsgText = "";
		
	    
	    boolean retValue = true;
	  
		try 
		{
			// Creating Object
			bo = new OnLineRefundApprovalBO();
			vo = new OnLineRefundApprovalVO();
	         
			// Set Value in Value Object
			vo.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
			vo.setStrCrNoVal(formBean.getStrCrNoVal());
			vo.setStrHiddenVal(formBean.getStrHiddenVal());
			vo.setStrApprovedQty(formBean.getStrApprovedQty());
			vo.setStrRefundCost(formBean.getStrRefundCost());
			vo.setStrReqNo(formBean.getStrReqNo());
			vo.setStrRefundedByComboVal(formBean.getStrRefundedByComboVal());
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setTariffId(formBean.getTariffId());
			vo.setStrRequestQty(formBean.getStrRequestQty());
			
			// Calling Businnes Object Method					
			retValue = bo.save(vo);
			
			if (retValue) 
			{
				String strMsg = vo.getStrMsg();
				formBean.setStrMsg(strMsg);
			} 
			else 
			{
				String strWarning = "Record Not Saved!!";
				formBean.setStrErrMsg(strWarning);
			}
			
			
		}
		  catch (Exception e) 
		  {
	        e.printStackTrace(); 
	        retValue = false;
			strmsgText = "OnLineRefundApprovalBSDATA.INSERT(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("purchase",
					"OnLineRefundApprovalBSDATA->INSERT()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}
		return retValue;
	}

}
