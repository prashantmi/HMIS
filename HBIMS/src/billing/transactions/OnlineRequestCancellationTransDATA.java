package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;

/**
 * @author Anshul Jindal
 * This is DATA file.This file is used to define the presentation logic
 * and transfer the control to BO file.Exception is handled here using HISException.
 * Populate formbean to VO
 * 
 */
public class OnlineRequestCancellationTransDATA {

	/**
	 * @param formBean
	 */
	public static void onlineRequestDetails(
			OnlineRequestCancellationTransFB formBean) 
	{
		String result = null;
		String strmsgText = null;
		OnlineRequestCancellationTransBO bo = null;
		OnlineRequestCancellationTransVO vo = null;
	
		try 
		{
			bo = new OnlineRequestCancellationTransBO();
			vo = new OnlineRequestCancellationTransVO();
			
			vo.setStrCrNo(formBean.getStrCrNo());
            vo.setStrHospitalCode(formBean.getStrHospitalCode());
            
            String strPatView = PatientDtlHLP.patientDtl(formBean.getStrCrNo(),
					true);
			if (strPatView.trim().equals("")) {
				formBean.setStrErrMsg("Invalid CR No.");
				formBean.setStrCRNoSatus("0");
			} else {
				formBean.setStrPatientDetailsView(strPatView);
				formBean.setStrCRNoSatus("1");
				formBean.setStrMsgType("0");
			}
				
			bo.getOnlineRequestDetails(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				formBean.setStrErrMsg("Request Details are not displaying due to some error, see Error Log");
				throw new Exception(vo.getStrMsgString());
			}
			else 
			{
				result = OnlineRequestCancellationTransHLP.onlineRequestDetails(vo);
				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				}
				else 
				{
					vo.setStrOnlineReqDtl(result);
					formBean.setStrOnlineReqDtl(vo.getStrOnlineReqDtl());
				}
			}
		 } 
		 catch (Exception e) 
		 {
			 String msgStr = e.getMessage();
				if (msgStr.contains("CR.")) {

					formBean.setStrErrMsg("Invalid CR. No.");
					formBean.setStrCrNo("");

				} else{
			strmsgText = "billing.transactions.OnlineRequestCancellationTransDATA.onlineRequestDetails(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("Billing",
				"OnlineRequestCancellation->onlineRequestDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1"); 
				   eObj = null;
				}
   		 }
		 finally 
		 {
		
				vo = null;
				bo = null;
			
		}

	}

	/**
	 * @param formBean
	 */
	public static void cancelledByCmb(OnlineRequestCancellationTransFB formBean) {

		String strmsgText = null;
		OnlineRequestCancellationTransBO bo = null;
		OnlineRequestCancellationTransVO vo = null;
		
		try {
			bo = new OnlineRequestCancellationTransBO();
			vo = new OnlineRequestCancellationTransVO();

			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			bo.getCancelledByCmb(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else {
				formBean.setStrCancelledBy(vo.getStrCancelledBy());
			}

		} catch (Exception e) {
			strmsgText = "billing.transactions.OnlineRequestCancellationTransDATA.cancelledByCmb(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("Billing",
				"OnlineRequestCancellation->cancelledByCmb()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");				  
				   eObj = null;
			
		} finally {

	
				vo = null;
				bo = null;
			
		}

	}

	/**
	 * @param formBean
	 */
	public static void cancelReasonCmb(OnlineRequestCancellationTransFB formBean) {

		String strmsgText = null;
		OnlineRequestCancellationTransBO bo = null;
		OnlineRequestCancellationTransVO vo = null;
	

		try {
			bo = new OnlineRequestCancellationTransBO();
			vo = new OnlineRequestCancellationTransVO();
		

			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getCancelReasonCmb(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else {
				formBean.setStrCancelReason(vo.getStrCancelReason());
			}

		} catch (Exception e) {
			strmsgText = "billing.transactions.OnlineRequestCancellationTransDATA.cancelReasonCmb(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("Billing",
				"OnlineRequestCancellation->cancelReasonCmb()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");	  
				   eObj = null;
			
		} finally {

		
				vo = null;
				bo = null;
				
		}

	}

	/**
	 * @param request
	 * @param response
	 */
	public static void getPopUp(HttpServletRequest request,
			HttpServletResponse response,OnlineRequestCancellationTransFB formBean) {

		String strmsgText = null;
		String strPopUpDtls = null;
		String index = "";
		OnlineRequestCancellationTransBO bo = null;
		OnlineRequestCancellationTransVO vo = null;
	

		try {
			bo = new OnlineRequestCancellationTransBO();
			vo = new OnlineRequestCancellationTransVO();
		              
			vo.setStrRequestNo((String) request.getParameter("reqNo"));
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			index = (String) request.getParameter("index");

			bo.getPopUpInfo(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			else 
			{
				strPopUpDtls = OnlineRequestCancellationTransHLP.getPopUpInfo(vo,index);
				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				}
				else
				{
					vo.setStrPopUpDtls(strPopUpDtls);
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(vo.getStrPopUpDtls());
				}
			}

		} catch (Exception e) {
			strmsgText = "billing.transactions.OnlineRequestCancellationTransDATA.getPopUp(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("Billing",
				"OnlineRequestCancellation->getPopUp()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");		  
				   eObj = null;
			
		} finally {

	
				vo = null;
	
				bo = null;
	
		}

	}

	/**
	 * @param request
	 * @param response
	 */
	public static void getPopUpData(HttpServletRequest request,
			HttpServletResponse response,OnlineRequestCancellationTransFB formBean) {

		String strmsgText = null;
		String strPopUpDtls = null;

		OnlineRequestCancellationTransVO vo = null;
	

		try {
			
		
			vo = new OnlineRequestCancellationTransVO();
			
			/*
			 * System.out.println("in data popup data-->" + (String)
			 * request.getParameter("popupData"));
			 */
			vo.setStrPopUpData((String) request.getParameter("popupData"));
			strPopUpDtls = OnlineRequestCancellationTransHLP.getPopUpInfo2(vo);
			// System.out.println("in data res data-->" + strPopUpDtls);
			 vo.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(strPopUpDtls);

		} catch (Exception e) {
			strmsgText = "billing.transactions.OnlineRequestCancellationTransDATA.getPopUp(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("Billing",
				"OnlineRequestCancellation->getPopUp()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");		  
				   eObj = null;
			
		} finally {

				vo = null;
		
		}

	}

	/**
	 * @param form
	 * @param request
	 * @param response
	 */
	public static void insertData(ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{

		String strmsgText = null;

	    String strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
		String strSeatId = request.getSession().getAttribute("SEATID").toString();
		String strIpAddress = request.getSession().getAttribute("IP_ADDR").toString();
		// String strHospitalCode ="108";
		// String strSeatId = "6";

		String strChkValues = "";

		OnlineRequestCancellationTransBO bo = null;
		OnlineRequestCancellationTransVO vo = null;
		OnlineRequestCancellationTransFB formBean = null;

		try 
		{
			bo = new OnlineRequestCancellationTransBO();
			vo = new OnlineRequestCancellationTransVO();
			formBean = (OnlineRequestCancellationTransFB) form;

			strChkValues = request.getParameter("chkValue");
			vo.setStrChkValues(strChkValues);

			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrIpAddress(strIpAddress);

			vo.setStrCancelledBy(formBean.getStrCancelledBy());
			vo.setStrCancelReason(formBean.getStrOtherReason());
            //System.out.println("Inside DATA--->>");
			bo.insertData(vo);
			if (!vo.getStrErrMsg().equals("")) 
			{
				formBean.setStrErrMsg(vo.getStrErrMsg());
			}

			if (vo.getStrMsgType().equals("1")) 
			{
				formBean.setStrErrMsg("Data has not been cancelled due to some error");
				formBean.setStrCrNo("");
				throw new Exception(vo.getStrMsgString());
			} 
			else 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsg(vo.getStrMsg());
				formBean.setStrCrNo("");
			}

		} 
		catch (Exception e) 
		{
			strmsgText = "billing.transactions.OnlineRequestCancellationTransDATA.insertData(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("Billing",
				"OnlineRequestCancellation->insertData()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");				  
				   eObj = null;
		}
		finally 
		{
		
				bo = null;
		
				vo = null;
		
		}

	}

}
