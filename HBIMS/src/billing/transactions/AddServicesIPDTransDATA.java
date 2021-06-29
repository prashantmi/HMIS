package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.utility.HisUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billing.HLPAccountDtl;

public class AddServicesIPDTransDATA {

	public static void initAddService( HttpServletRequest request, AddServicesIPDTransFB formBean ){
		
		AddServicesIPDTransBO bo = null;
		AddServicesIPDTransVO vo = null;
		HisUtil util = null;
		
	 
		try{
			
			String strCrNo = formBean.getStrCrNo();
			
			formBean.setStrHospitalCode(request.getSession().getAttribute(
			"HOSPITAL_CODE").toString());

	formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
			.toString());
 

	
	try{
	formBean.setStrPatientDetailsView(PatientDtlHLP.compactPatientWithAdmissionDtl(strCrNo,
			formBean.getStrHospitalCode(), false));
	}catch (Exception e) {
		
		throw e;
		
	}
	
	formBean.setStrAccountDetailsView(HLPAccountDtl.getAccountDetailsHLP(strCrNo, "2", "1" , formBean.getStrHospitalCode() ));
	
	if(formBean.getStrAccountDetailsView().toUpperCase().contains("NO RECORD FOUND FOR SELECTED CR NO")){
		
		throw new Exception("No Account Detail is Available for the CR. No. "+formBean.getStrCrNo());
		
	}
	
		bo = new AddServicesIPDTransBO();
		vo = new AddServicesIPDTransVO();
		util = new HisUtil("billing" , "AddServicesIPDTransDATA");
		
		
		vo.setStrCrNo(formBean.getStrCrNo());
		vo.setStrHospitalCode(formBean.getStrHospitalCode());
		vo.setStrSeatId(formBean.getStrSeatId());
		
		
		bo.initAddService(vo);
		
		if(!vo.getStrMsgType().equals("0")){
			
			throw new Exception(vo.getStrMsgString());
			
		}
		
		if(vo.getWsOfflineGroupDetails() != null ){
		
			formBean.setStrOfflineGroupDetails( util.getOptionValue(vo.getWsOfflineGroupDetails(), "0", "0^Select Value", false) );
			
		}else{
			
			formBean.setStrOfflineGroupDetails("<option value='0'>Select Value</option>");
			
		}
		
		if(vo.getWsPateintCategory() != null ){
			
			formBean.setStrPatientCategoryValues( util.getOptionValue(vo.getWsPateintCategory(), "0", "0^Select Value", false) );
			
		}else{
			
			formBean.setStrPatientCategoryValues("<option value='0'>Select Value</option>");
			
		}
		
		 		
		
		formBean.setStrCtDate(util.getASDate("dd-MMM-yyyy"));
	
		}catch (Exception e) {
			
			String msgStr = e.getMessage();

			if (msgStr.contains("Invalid CR.")) {
				
				formBean.setStrErrMsg("Invalid CR. No. :"+formBean.getStrCrNo());
				formBean.setStrCrNo("");
			}else if(msgStr.contains("No Account Detail is Available for the CR. No.")){
			
				formBean.setStrErrMsg(msgStr);
				formBean.setStrCrNo("");
				
			}else {
			
				HisException eObj = new HisException("Billing",
						"AddServicesIPDTransDATA->initAddService()", msgStr);
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator! ");

				formBean.setStrCrNo("");
				eObj = null;

			}
			
		}finally{
			
			vo = null;
			bo = null;
			util = null;
			
		}
		
		
	}
	
	
	public static void getPreviousDetails(HttpServletRequest request, HttpServletResponse response,  AddServicesIPDTransFB formBean){
		
		AddServicesIPDTransBO bo = null;
		AddServicesIPDTransVO vo = null;
		
		try{
			
			bo = new AddServicesIPDTransBO();
			vo = new AddServicesIPDTransVO();
			
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			vo.setStrAccountNo(formBean.getStrAccountNo());
			
			bo.getPreviousDetails(vo);
			
			if(! vo.getStrMsgType().equals("0")){
				throw new Exception(vo.getStrMsgString());
			}
						
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(AddServicesIPDTransHLP.getPreviousAddServicesView(vo.getWsPreviousAddServiceDetails()));
						 
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"AddServicesIPDTransDATA->getPreviousDetails()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"AddServicesIPDTransDATA->getPreviousDetails()", e1
								.getMessage());
			}
		} finally {

			bo = null;	
			vo = null;
		}
		
		
	}
	
	
	/**
	 * TARIFFDTLS(request,response,formBean) -- > This Method generate the Ajax
	 * Response for Drop Down Utility which use in Add Services & Bill Approval
	 * Transaction
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */

	public static void TARIFFDTLS(HttpServletRequest request,
			HttpServletResponse response, AddServicesIPDTransFB formBean) {
		// Declaring Variables
		String strmsgText = "";
		String strRes = "";
	  
		HisUtil hisutil = null;
		AddServicesIPDTransBO bo = null;
		AddServicesIPDTransVO vo = null;
		
		try {
			bo = new AddServicesIPDTransBO();
			vo = new AddServicesIPDTransVO();
			hisutil = new HisUtil("transaction", "AddServicesIPDTransDATA");
						 
			String strGroupCode = request.getParameter("groupId");
			String strHospitalService = request.getParameter("hService");
			String strPatientCategory = request.getParameter("treatmentCat");
			String strWardCode = request.getParameter("ward");
			 
			
			vo.setStrHospitalCode(request.getSession().getAttribute(
			"HOSPITAL_CODE").toString());
			vo.setStrGroupId(strGroupCode);
			vo.setStrChargeTypeId(strHospitalService);
			vo.setStrPatientCategory(strPatientCategory);
			vo.setStrWardCode(strWardCode);
			// Bo Method Calling
			bo.getTariffDetails(vo);
		 
			if (vo.getWsTariffList() != null) {
				strRes = hisutil.getDropDown(vo.getWsTariffList(), Integer.parseInt(strWardCode) , 4,
						true);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strRes);
			}  
			 

		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"AddServicesIPDTransDATA->TARIFFDTLS()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			 
			eObj = null;
		} finally {

			vo = null;

			hisutil = null;

			bo = null;

		}

	}
	
	
	/**
	 * writes tariff Details based on
	 * Tariff Code as Ajax Response.
	 * 
	 * @param request
	 * @param response
	 */
	public static void getTariffCodeDetails(HttpServletRequest request,
			HttpServletResponse response) {

		AddServicesIPDTransBO bo = null;
		AddServicesIPDTransVO voObj = null;
		
		try {
			bo = new AddServicesIPDTransBO();
			voObj = new AddServicesIPDTransVO();
			
			String strGroupCode = request.getParameter("groupId");
			String strHospitalService = request.getParameter("hService");
			String strPatientCategory = request.getParameter("treatmentCat");
			String strWardCode = request.getParameter("ward").replace("^", "#").split("#")[0];
			String strTariffCode = request.getParameter("tariffCode");

			if (strGroupCode == null)
				strGroupCode = "0";
			if (strHospitalService == null)
				strHospitalService = "0";
			if (strPatientCategory == null)
				strPatientCategory = "0";
			if (strWardCode == null || !strHospitalService.equals("2"))
				strWardCode = "0";

			voObj.setStrHospitalCode(request.getSession().getAttribute(
			"HOSPITAL_CODE").toString());
			voObj.setStrGroupId(strGroupCode);
			voObj.setStrChargeTypeId(strHospitalService);
			voObj.setStrPatientCategory(strPatientCategory);
			voObj.setStrWardCode(strWardCode);
			voObj.setStrTariffCode(strTariffCode);
			
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			bo.getTariffCodeDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			String temp = "";

			if (voObj.getWsTariffList().size() != 0) {
				
				if(voObj.getWsTariffList().next()){
					
					temp =  "1@@"+voObj.getWsTariffList().getString(2)+"@@"+voObj.getWsTariffList().getString(1);
				}
							
			}else{
				
					temp = "0@@_@@0";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"CashCollectionTransDATA->getTariffCodeDetails()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"IpdBillManagementTransDATA->getTariffCodeDetails()", e1
								.getMessage());
			}
		} finally {

			bo = null;

			voObj = null;
		}

	}


	/**
	 * writes tariff Details based on
	 * Tariff Code as Ajax Response.
	 * 
	 * @param request
	 * @param response
	 */
	public static void getDefaultTariffDetails(HttpServletRequest request,
			HttpServletResponse response) {

		AddServicesIPDTransBO bo = null;
		AddServicesIPDTransVO voObj = null;
		
		try {
			bo = new AddServicesIPDTransBO();
			voObj = new AddServicesIPDTransVO();
			
			String strGroupCode = request.getParameter("groupId");
			String strHospitalService = request.getParameter("hService");
			String strPatientCategory = request.getParameter("treatmentCat");
			String strStartDate = request.getParameter("startDate");
			String strEndDate = request.getParameter("endDate");
			String strWardType = request.getParameter("wardType");

			if (strGroupCode == null)
				strGroupCode = "0";
			if (strHospitalService == null)
				strHospitalService = "0";
			if (strPatientCategory == null)
				strPatientCategory = "0";
			 

					
			voObj.setStrHospitalCode(request.getSession().getAttribute(
			"HOSPITAL_CODE").toString());
			voObj.setStrGroupId(strGroupCode);
			voObj.setStrChargeTypeId(strHospitalService);
			voObj.setStrPatientCategory(strPatientCategory);
			voObj.setStrWardCode(strWardType);
			
			
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			bo.getDefaultTariffDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			
			String temp = AddServicesIPDTransHLP.getCompulsaryChargesView(voObj.getWsTariffList() , strStartDate , strEndDate , voObj.getStrWardCode() );

			 
			 
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"CashCollectionTransDATA->getTariffCodeDetails()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"IpdBillManagementTransDATA->getTariffCodeDetails()", e1
								.getMessage());
			}
		} finally {

			bo = null;

			voObj = null;
		}

	}
	
	
	/**
	 * TRFUNIT(request,response,formBean) -- > This Method generate the Ajax
	 * Response
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */

	public static void TRFUNIT(HttpServletRequest request,
			HttpServletResponse response, AddServicesIPDTransFB formBean) {
		// Declaring Variables
		String strData = request.getParameter("modName");
		String strmsgText = "";
		// String strRes ="";
		String strOfflineTariffUnit1 = "";
		String strOfflineTariffUnit = "";
		String[] strHidData = null;

		// Creating Object for BO & VO.
		HisUtil hisutil = null;
		AddServicesIPDTransBO bo = null;
		AddServicesIPDTransVO vo = null;
		
		try {
			bo = new AddServicesIPDTransBO();
			vo = new AddServicesIPDTransVO();
			hisutil = new HisUtil("transaction", "AddServicesIPDTransDATA");

			// Split the Data
			strHidData = strData.split("\\^");
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			vo.setStrTariffUnitTempId(strHidData[0]);
			
			bo.getTariffUnit(vo);

			if(!vo.getStrMsgType().equals("0")){
				
				throw new Exception(vo.getStrMsgString());
				
			}
			
			// Tariff Name Combo
			if (vo.getWsTariffUnit() != null
					&& vo.getWsTariffUnit().size() > 0) {
				strOfflineTariffUnit1 = hisutil.getOptionValue(vo
						.getWsTariffUnit(), "", "", true);
				strOfflineTariffUnit = strHidData[2] + '@'
						+ strOfflineTariffUnit1;
				// Set Value in Response
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strOfflineTariffUnit);
			} 
		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransDATA->TRFUNIT()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			 
			eObj = null;
		} finally {

			vo = null;

			hisutil = null;

			bo = null;

		}
	}

	
	
public static void getQuantity(HttpServletRequest request, HttpServletResponse response,  AddServicesIPDTransFB formBean){
		
		AddServicesIPDTransBO bo = null;
		AddServicesIPDTransVO vo = null;
		
		try{
			
			String strWardType = request.getParameter("wardType");
			String strDateString = request.getParameter("strDateString");
			
			
			bo = new AddServicesIPDTransBO();
			vo = new AddServicesIPDTransVO();
			
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrWardCode(strWardType);
			vo.setStrDateString(strDateString);
						
			bo.getQuantity(vo);
			
			if(! vo.getStrMsgType().equals("0")){
				throw new Exception(vo.getStrMsgString());
			}
						
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(vo.getStrQuantity());
						 
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"AddServicesIPDTransDATA->getQuantity()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"AddServicesIPDTransDATA->getQuantity()", e1
								.getMessage());
			}
		} finally {

			bo = null;	
			vo = null;
		}
		
		
	}
	

public static void insert( HttpServletRequest request, AddServicesIPDTransFB formBean ){
	
	AddServicesIPDTransBO bo = null;
	AddServicesIPDTransVO vo = null;

	
 
	try{
		
		String strCrNo = formBean.getStrCrNo();
		
		formBean.setStrHospitalCode(request.getSession().getAttribute(
		"HOSPITAL_CODE").toString());

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
		.toString());

  

	bo = new AddServicesIPDTransBO();
	vo = new AddServicesIPDTransVO();
	
	
	
	vo.setStrCrNo(formBean.getStrCrNo());
	vo.setStrHospitalCode(formBean.getStrHospitalCode());
	vo.setStrSeatId(formBean.getStrSeatId());
	
	
	bo.initAddService(vo);
	  

	}catch (Exception e) {
		
		String msgStr = e.getMessage();

		if (msgStr.contains("Invalid CR.")) {
			
			formBean.setStrErrMsg("Invalid CR. No. :"+formBean.getStrCrNo());
			formBean.setStrCrNo("");
		}else if(msgStr.contains("No Account Detail is Available for the CR. No.")){
		
			formBean.setStrErrMsg(msgStr);
			formBean.setStrCrNo("");
			
		}else {
		
			HisException eObj = new HisException("Billing",
					"AddServicesIPDTransDATA->initAddService()", msgStr);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID()
					+ "], Contact System Administrator! ");

			formBean.setStrCrNo("");
			eObj = null;

		}
		
	}finally{
		
		vo = null;
		bo = null;
	
		
	}
	
	
}


}
