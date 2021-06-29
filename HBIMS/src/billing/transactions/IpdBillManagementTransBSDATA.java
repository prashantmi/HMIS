package billing.transactions;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.tools.hlp.PatientDtlHLPNew;
import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.HLPAccountDtl;
import billing.HLPClientApprovalDtl;
import billing.HLPGrpDtl;
import billing.HLPPartPaymentPendingRequest;
import billing.HLPParticularDtl;
import billing.HLPParticularDtlBillApproval;
import billing.HLPParticularDtlBillApprovalNew;
import billing.HLPPkgBreakUp;
import billing.HLPTariffDtl;
import billing.PrintHLP;

public class IpdBillManagementTransBSDATA {
	/**
	 * This class will be used to Interact with Businees Object & also for
	 * presentation logic. Only this class may interact with
	 * HisUtil/Helper/HisException/TransferObjectFactory Class.
	 * 
	 * It is better not to use PopulateData() function to transfer the data if
	 * there is no more data to be transfered.
	 */
	/**
	 * ************************************Init Patient Dtl for (PART PAYMENT
	 * REQUEST)********************************************
	 */

	/**
	 * initPatientDtl(request,response,formBean) -- > This Method generate the
	 * data for UpdateAccountStatus Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static boolean initPatientDtl(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean) {

		// Declaring Variable

		String strClientApprovalDtlHlp = "";
		String strClientAcctDtlHlp = "";
		String strPartPaymentPendingHlp = "";
		String strChk = null;
		String strChk2 = null;
		String strChkBoxComboValue = null;
		String strTemp[] = null;
		boolean bretVal = true;
		String msgStr = "";
		String strPatView = "";

		// Create The Object for VO & BO .
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;
		HisUtil hisutil = null;
		// TransferObjectFactory.populateData(formBean,vo);
		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			// Set Value in VO
			//strChk = request.getParameter("chk");
			//strChk2 = request.getParameter("chk");
			if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
			{
				String[] arr=formBean.getChk();
				strChk =arr[0];
				strChk2 =arr[0];
			}
			else
			{
				strChk =formBean.getStrChk();
				strChk2 =formBean.getStrChk();
			}
			if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
			{
				String[] arr=formBean.getChk();
				strChk =arr[0];
				strChk2 =arr[0];
			}
			else
			{
				strChk =formBean.getStrChk();
				strChk2 =formBean.getStrChk();
			}
			String s1[] = formBean.getStrComboValue();
			
			formBean.setStrWardCode(formBean.getCombo()[2].replace("^", "#").split("#")[0]);
			
			formBean.setStrChk(strChk);
			String s = s1[1] + "^" + s1[2];
			strChkBoxComboValue = strChk2 + "@" + s;
			vo.setStrDeptCode(s1[1]);
			vo.setStrChk(strChk);
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			// Setting List Page Chk Value
			vo.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString()); // Setting SEATID
			vo.setStrComboValue(formBean.getStrComboValue()); // Setting For
			// List Page
			// Combo Value

			String strStatus = "";

			if (s1[0].equals("1"))
				strStatus = "Active";
			if (s1[0].equals("2"))
				strStatus = "In-Active";
			if (s1[0].equals("3"))
				strStatus = "On-Hold";
			if (s1[0].equals("4"))
				strStatus = "Dormant";
			if (s1[0].equals("5"))
				strStatus = "Approved Request";
			if (s1[0].equals("6"))
				strStatus = "Canceled";
			if (s1[0].equals("0"))
				strStatus = "Closed";
			strTemp = strChk.replace('@', '#').split("#"); // Get The Cr No...
			bo.getValues(vo);

			// Setting Value In Form Bean to Display..

			formBean.setStrCrNo(strTemp[1]);
			formBean.setStrAccountNo(strTemp[0]);
			formBean.setStrPresentAcctStatus(strStatus);
			formBean.setStrPresentAcctStatusCode(s1[0]);

			// Setting of Department Code
			formBean.setStrDeptCode(s1[1]);

			// Calling of HLP - ClientApprovalDtl
			strClientApprovalDtlHlp = HLPClientApprovalDtl.getClientApprovalDetails_APPID(strTemp[2], strTemp[1], vo.getStrHospitalCode());
			String[] TestData1 = strClientApprovalDtlHlp.split("\\####");
			if (TestData1[0].equals("ERROR")) 
			{
				throw new Exception(TestData1[1]);
			} 
			else 
			{
				formBean.setStrClientApprovalDtl(TestData1[0]);
			}

			// Calling of HLP - PartPayment
			strPartPaymentPendingHlp = HLPPartPaymentPendingRequest.getPendingRequest(strTemp[1], vo.getStrHospitalCode());
			String[] TestData2 = strPartPaymentPendingHlp.split("\\####");
			if (TestData2[0].equals("ERROR")) 
			{
				throw new Exception(TestData2[1]);
			} 
			else 
			{
				formBean.setStrPendingPartPaymentReq(TestData2[0]);
			}

			// Calling of Account Detail HLP
			strClientAcctDtlHlp = HLPAccountDtl.getAccountDetailsHLPBS(strTemp[0], strChkBoxComboValue, vo.getStrHospitalCode());
			String[] TestData3 = strClientAcctDtlHlp.split("\\####");
			if (TestData3[0].equals("ERROR")) 
			{
				throw new Exception(TestData3[1]);
			} 
			else 
			{
				formBean.setStrClientAcctDtl(TestData3[0]);
			}
						
			// Calling HLP to get Patient Detail
			strPatView = PatientDtlHLPNew.compactPatientWithAdmissionDtl(formBean.getStrCrNo(), vo.getStrHospitalCode(), false);
			formBean.setStrPatientDetailsView(strPatView);

			vo.setStrRemarksCombo(null);

			if (vo.getStrRemarksCombo() != null && vo.getStrRemarksCombo().size() > 0) 
			{
				String str = hisutil.getOptionValue(vo.getStrRemarksCombo(),"", "", true);
				formBean.setStrRemarksCombo(str); // Testing
			} 
			else 
			{
				String str = "";
				formBean.setStrRemarksCombo(str);
				formBean.setStrMsgString(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				if (formBean.getStrMsgType().equals("1")) 
				{
					throw new Exception(formBean.getStrMsgString());
				}
			}

			formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));

			// Get Approved By Combo
			if (vo.getStrApprovedByCombo() != null
					&& vo.getStrApprovedByCombo().size() > 0) {
				String str = hisutil.getOptionValue(vo.getStrApprovedByCombo(),
						"0", "0^Select value", true);
				formBean.setStrApprovedByCombo(str);
			} else {
				// Set Messages into form bean
				String str = "<option value='0'>Select Value</option>";
				formBean.setStrApprovedByCombo(str);
				formBean.setStrMsgString(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}
			}
			
			
			if (vo.getStrAccStatusCombo() != null
					&& vo.getStrAccStatusCombo().size() > 0) {
				String str = hisutil.getOptionValue(vo.getStrAccStatusCombo(),
						"0", "0^Select value", true);
				formBean.setStrAccStatusCombo(str);
			} else {
				// Set Messages into form bean
				String str = "<option value='0'>Select Value</option>";
				formBean.setStrAccStatusCombo(str);
				formBean.setStrMsgString(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}
			}
		} catch (Exception e) {

			bretVal = false;
			msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->initPatientDtl()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			vo = null;

			bo = null;

			hisutil = null;
		}
		return bretVal;
	}

	/** *******************************(billApprovalDtl)**************************************** */

	/**
	 * billApprovalDtl(request,response,formBean) -- > This Method generate the
	 * data for Bill Approval Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static boolean billApprovalDtl(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean , String strMode) {
		// Declaring Variable
		String strClientApprovalDtlHlp = "";
		String strClientAcctDtlHlp = "";
		String strParticulaDtl = "";
		String msgStr = "";
		String strChkBoxComboValue = "";
		String s = null;
		String s1[] = null;
		String strTemp[] = null;
		String strChk = null;
		String strChk2 = null;

		boolean bretVal = true;

		// Create The Object for VO & BO .
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;
		HisUtil hisutil = null;
		BillConfigUtil billConfigUtil = null;

		// TransferObjectFactory.populateData(formBean,vo);
		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();

			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			billConfigUtil = new BillConfigUtil(formBean.getStrHospitalCode());

			formBean.setStrIsIpdDiscount(billConfigUtil.getIpdDiscountClerk());

			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			//formBean.setStrComboValue(request.getParameterValues("combo"));
			formBean.setStrComboValue(formBean.getCombo());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			formBean.setIsApproved("1");

			formBean.setStrWardCode(formBean.getCombo()[2].replace("^", "#").split("#")[0]);
			
			//strChk = request.getParameter("chk");
			//strChk2 = request.getParameter("chk");
			if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
			{
				String[] arr=formBean.getChk();
				strChk =arr[0];
				strChk2 =arr[0];
			}
			else
			{
				strChk =formBean.getStrChk();
				strChk2 =formBean.getStrChk();
			}
			s1 = formBean.getStrComboValue();
			s = s1[1] + "^" + s1[2];

			strChkBoxComboValue = strChk2 + "@" + s;
			formBean.setStrChkBoxComboValue(s);
			// Set Value in VO
			vo.setStrChk(strChk);
			vo.setStrComboValue(formBean.getStrComboValue()); // Setting For
			// List Page
			// Combo Value
			strTemp = strChk.replace('@', '#').split("#"); // Get The Cr No....

			// Setting Value in Form Bean
			formBean.setStrChk(strChk);
			formBean.setStrCrNo(strTemp[1]);
			formBean.setStrAccountNo(strTemp[0]);
			formBean.setStrChargeTypeID(strTemp[4]);
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			 
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrChargeTypeID(formBean.getStrChargeTypeID());
			
			vo.setStrAccountNo(formBean.getStrAccountNo());
			/*bo.getTreatmentCatAndWardTypeValues(vo);
			
			if(vo.getStrMsgType().equals("1")){
				
				throw new Exception(vo.getStrMsgString());
			}
			
			String strTreatmentCat = "<option value='0'>Select Value</option>";
			
			if(vo.getOfflineTreatmentCategoryList() != null && vo.getOfflineTreatmentCategoryList().size() > 0){
				
			 
				strTreatmentCat = hisutil.getOptionValue(vo.getOfflineTreatmentCategoryList(),
							"0", "0^Select value", true);							
			}
			
			formBean.setStrTreatmentCategoryValues(strTreatmentCat);
			
			
		if(strMode.equals("2")){
			

			String strWardType = "<option value='0'>Select Value</option>";
						
						if(vo.getWardTypeList() != null && vo.getWardTypeList().size() > 0){
							
						 
							strWardType = hisutil.getOptionValue(vo.getWardTypeList(),
										"", "", true);							
						}
						
						formBean.setStrWardTypeValues(strWardType);
						
						
			
		}*/
			
			
			formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));
			
			
			// Calling BO method
			bo.getApprovedBy(vo);

			// Getting Value From BillConfigUtil

			int i = Integer.parseInt(billConfigUtil.getIpdThirdPartyBilling());
			formBean.setStrIpdRoundOff(billConfigUtil.getIpdRoundOff());
			// HLP - Patient Approval Dtl
			if (i == 1) 
			{
				formBean.setStrIpdThirdPartyBilling(billConfigUtil.getIpdThirdPartyBilling());
				strClientApprovalDtlHlp = HLPClientApprovalDtl.getClientApprovalDetails_APPID(strTemp[2], strTemp[1],vo.getStrHospitalCode());

				String[] TestData1 = strClientApprovalDtlHlp.split("\\####");
				if (TestData1[0].equals("ERROR")) 
				{
					throw new Exception(TestData1[1]);
				} 
				else 
				{
					strClientApprovalDtlHlp = TestData1[0];
				}
			} 
			else 
			{
				formBean.setStrIpdThirdPartyBilling("0");
				strClientApprovalDtlHlp = "";
			}
			bo.getAccountDtls(vo);
			formBean.setServiceFlag(vo.getServiceFlag());
			formBean.setAccType(vo.getAccType());
			formBean.setStrClientApprovalDtl(strClientApprovalDtlHlp);

			// Calling of HLP - Particular Dtl
			strParticulaDtl = HLPParticularDtlBillApprovalNew.getParticularDtlForBillApproval(strTemp[0], i, vo.getStrHospitalCode(),formBean.getAccType());
			String[] TestData4 = strParticulaDtl.split("\\####");
			if (TestData4[0].equals("ERROR")) 
			{
				throw new Exception(TestData4[1]);
			} 
			else 
			{
				String[] strBillDtl = TestData4[0].split("\\&&");
				formBean.setStrBillAprovalDtl1(strBillDtl[0]);
				formBean.setStrBillAprovalDtl2(strBillDtl[1]);
			}
			// Calling of Account Detail HLP
			strClientAcctDtlHlp = HLPAccountDtl.getAccountDetailsHLPBS(strTemp[0], strChkBoxComboValue, vo.getStrHospitalCode());
			String[] TestData3 = strClientAcctDtlHlp.split("\\####");
			if (TestData3[0].equals("ERROR")) 
			{
				throw new Exception(TestData3[1]);
			} 
			else 
			{
				formBean.setStrClientAcctDtl(TestData3[0]);
			}
			// Client Details HLP Exception Handling is Still Pending
			String strPatView = "";
			strPatView = PatientDtlHLPNew.compactPatientWithAdmissionDtl(formBean.getStrCrNo(), vo.getStrHospitalCode(), false);
			//strPatView=strPatView.replace("~1", "");//~1 APPENDED IN PATIENT STRING To check Whether Admission Details Found or Not--OFFLINE BILLING CASE NOT FOUND.ONLINE SHOULD BE FOUND.
			formBean.setStrPatientDetailsView(strPatView);

			formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));

			// Get Approved By Combo
			if (vo.getStrApprovedByCombo() != null && vo.getStrApprovedByCombo().size() > 0) 
			{
				String str = hisutil.getOptionValue(vo.getStrApprovedByCombo(),"0", "", true);
				formBean.setStrApprovedByCombo(str);
			} 
			else 
			{
				// Set Messages into form bean
				String str = "<option value='0'>Select Value</option>";
				formBean.setStrApprovedByCombo(str);
				formBean.setStrMsgString(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) 
				{
					throw new Exception(formBean.getStrMsgString());
				}
			}
			
			////details for poor free
			/*if(vo.getStrTreatmentCategory().equals(billConfigUtil.getIpdFreeCategory()))
			{
				String strPoorFreeDetails=IpdBillManagementTransHLPNew.getPoorFreeTarrif(formBean);
				formBean.setStrPoorFreeDetail(strPoorFreeDetails);
			}*/

		} 
		catch (Exception e) 
		{
			bretVal = false;
			msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->billApprovalDtl()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{

			vo = null;
			bo = null;
			hisutil = null;
			billConfigUtil = null;
		}
		return bretVal;
	}

 
	/** *****************************(initPatientDtl1)************************************* */
	/**
	 * initPatientDtl1(request,response,formBean) -- > This Method generate the
	 * data for View Bill Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static boolean initPatientDtl1(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean) {

		// Declaring Variable
		String strClientApprovalDtlHlp = "";
		String strClientAcctDtlHlp = "";
		String msgStr = "";
		String strChk = null;
		String strChk2 = null;
		String strTemp[] = null;
		String s1[] = null;
		String strIsDesk= "0";
		String temp = null;

		boolean bretVal = true;

		// Create The Object for VO & BO .
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;
		HisUtil hisutil = null;
		// TransferObjectFactory.populateData(formBean,vo);

		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();

			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			strIsDesk= request.getParameter("isDesk");
			if(strIsDesk==null)
				strIsDesk="0";
			if(Integer.parseInt(strIsDesk)!=1)
			{	
    		// Set Value in VO
				if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
				{
					String[] arr=formBean.getChk();
					strChk =arr[0];
					strChk2 =arr[0];
				}
				else
				{
					strChk =formBean.getStrChk();
					strChk2 =formBean.getStrChk();
				}
			//strChk = request.getParameter("chk");
			//strChk2 = request.getParameter("chk");
			strTemp = strChk.replace('@', '#').split("#"); // Get The Cr No....
			s1 = formBean.getStrComboValue();

			String s = s1[1] + "^" + s1[2];

			formBean.setStrVal(s);

			String strChkBoxComboValue = strChk2 + "@" + s;

			vo.setStrChk(strChk);
			vo.setStrComboValue(formBean.getStrComboValue()); // Setting For
			// List Page
			// Combo Value
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			// Setting Value in FormBean
			formBean.setStrChk(strChk);
			formBean.setStrCrNo(strTemp[1]);
			formBean.setStrAccountNo(strTemp[0]);
			formBean.setStrPresentAcctStatus(vo.getStrPresentAcctStatus());

			vo.setStrAccountNo(formBean.getStrAccountNo());

			// Calling BO method
			// bo.getValues(vo);

			// Getting Value From BillConfigUtil
			BillConfigUtil billConfigUtil = new BillConfigUtil(formBean.getStrHospitalCode());
			int i = Integer.parseInt(billConfigUtil.getIpdThirdPartyBilling());
			formBean.setStrIpdRoundOff(billConfigUtil.getIpdRoundOff());

			// HLP - Patient Approval Dtl
			if (i == 1) {
				formBean.setStrIpdThirdPartyBilling(billConfigUtil
						.getIpdThirdPartyBilling());
				strClientApprovalDtlHlp = HLPClientApprovalDtl
						.getClientApprovalDetails_APPID(strTemp[2], strTemp[1],
								vo.getStrHospitalCode());
				String[] TestData1 = strClientApprovalDtlHlp.split("\\####");
				if (TestData1[0].equals("ERROR")) {
					throw new Exception(TestData1[1]);
				} else {
					strClientApprovalDtlHlp = TestData1[0];
				}

			} else {
				formBean.setStrIpdThirdPartyBilling("0");
				strClientApprovalDtlHlp = "";
			}
			formBean.setStrClientApprovalDtl(strClientApprovalDtlHlp);

			//Tariff Details Data Populated on Search Icon)
			bo.getParticularDtlsList(vo);

			formBean.setStrParticularDtlsListWs(vo.getStrParticularDtlsListWs());

			formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));
			formBean.setStrParticulaDtl(IpdBillManagementTransHLPNew.getViewBillParticularsList(formBean));

			/*
			 * // Calling of HLP - Particular Dtl strParticulaDtl =
			 * HLPParticularDtl.getParticularDtl1(strTemp[0],i,vo.getStrHospitalCode());
			 * String[] TestData2 = strParticulaDtl.split("\\####");
			 * if(TestData2[0].equals("ERROR")) { throw new
			 * Exception(TestData2[1]); } else {
			 * formBean.setStrParticulaDtl(TestData2[0]); }
			 */

			// Calling of HLP - AccountDtl
			strClientAcctDtlHlp = HLPAccountDtl.getAccountDetailsHLPBS(
					strTemp[0], strChkBoxComboValue, vo.getStrHospitalCode());
			String[] TestData3 = strClientAcctDtlHlp.split("\\####");
			if (TestData3[0].equals("ERROR")) {
				throw new Exception(TestData3[1]);
			} else {
				formBean.setStrClientAcctDtl(TestData3[0]);
			}

			// Get Client Detail
			String strPatView = "";
			strPatView = PatientDtlHLPNew.compactPatientWithAdmissionDtlWithoutCatExpiryCheck(formBean.getStrCrNo(), vo.getStrHospitalCode(), false);
			formBean.setStrPatientDetailsView(strPatView);
			
			}
			
			else
			{
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
				formBean.setStrCrNo(request.getParameter("patCrNo"));
				formBean.setStrAddmissionNo(request.getParameter("patAdmNo"));
				vo.setStrCrNo(formBean.getStrCrNo());
				vo.setStrAddmissionNo(formBean.getStrAddmissionNo());
				
				String strPatView = "";
				strPatView = PatientDtlHLPNew.compactPatientWithAdmissionDtlWithoutCatExpiryCheck(formBean.getStrCrNo(), vo.getStrHospitalCode(), false);
				formBean.setStrPatientDetailsView(strPatView);
				
				bo.getAccDetailIpd(vo);
				WebRowSet ws = vo.getStrAccDtls();
				if (ws.next()) {
					temp = ws.getString(1);
				}
				// Set Value in VO
				strChk = temp;
				strChk2 = temp;
				strTemp = strChk.replace('@', '#').split("#"); // Get The Cr No....

				String s = 1 + "^" + 1;

				formBean.setStrVal(s);

				String strChkBoxComboValue = strChk2 + "@" + s;

				vo.setStrChk(strChk);

				// Setting Value in FormBean
				formBean.setStrChk(strChk);
				formBean.setStrAccountNo(strTemp[0]);
				formBean.setStrPresentAcctStatus(vo.getStrPresentAcctStatus());

				vo.setStrAccountNo(formBean.getStrAccountNo());

				// Calling BO method
				// bo.getValues(vo);

				// Getting Value From BillConfigUtil
				BillConfigUtil billConfigUtil = new BillConfigUtil(formBean.getStrHospitalCode());
				int i = Integer.parseInt(billConfigUtil.getIpdThirdPartyBilling());
				formBean.setStrIpdRoundOff(billConfigUtil.getIpdRoundOff());

				// HLP - Patient Approval Dtl
				if (i == 1) {
					formBean.setStrIpdThirdPartyBilling(billConfigUtil
							.getIpdThirdPartyBilling());
					strClientApprovalDtlHlp = HLPClientApprovalDtl
							.getClientApprovalDetails_APPID(strTemp[2], strTemp[1],
									vo.getStrHospitalCode());
					String[] TestData1 = strClientApprovalDtlHlp.split("\\####");
					if (TestData1[0].equals("ERROR")) {
						throw new Exception(TestData1[1]);
					} else {
						strClientApprovalDtlHlp = TestData1[0];
					}

				} else {
					formBean.setStrIpdThirdPartyBilling("0");
					strClientApprovalDtlHlp = "";
				}
				formBean.setStrClientApprovalDtl(strClientApprovalDtlHlp);

				bo.getParticularDtlsList(vo);

				formBean
						.setStrParticularDtlsListWs(vo.getStrParticularDtlsListWs());

				formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));
				formBean.setStrParticulaDtl(IpdBillManagementTransHLPNew
						.getViewBillParticularsList(formBean));

				/*
				 * // Calling of HLP - Particular Dtl strParticulaDtl =
				 * HLPParticularDtl.getParticularDtl1(strTemp[0],i,vo.getStrHospitalCode());
				 * String[] TestData2 = strParticulaDtl.split("\\####");
				 * if(TestData2[0].equals("ERROR")) { throw new
				 * Exception(TestData2[1]); } else {
				 * formBean.setStrParticulaDtl(TestData2[0]); }
				 */

				// Calling of HLP - AccountDtl
				strClientAcctDtlHlp = HLPAccountDtl.getAccountDetailsHLPBS(
						strTemp[0], strChkBoxComboValue, vo.getStrHospitalCode());
				String[] TestData3 = strClientAcctDtlHlp.split("\\####");
				if (TestData3[0].equals("ERROR")) {
					throw new Exception(TestData3[1]);
				} else {
					formBean.setStrClientAcctDtl(TestData3[0]);
				}
				
			}
		

		} catch (Exception e) {

			e.printStackTrace();

			bretVal = false;
			msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->initPatientDtl1()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			bo = null;

			hisutil = null;
		}

		return bretVal;
	}

	/** ************************Add Client Approval***************************** */

	/**
	 * initClientApprovalDtl(request,response,formBean) -- > This Method
	 * generate the data for Client Approval Detail's Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static boolean initClientApprovalDtl(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean) {

		// Declaring String Variable
		String strClientAcctDtlHlp = "";
		// String strClientApprovalDtlHlp2 = "";
		String msgStr = "";
		String s1[] = null;
		String s = null;
		String strChkBoxComboValue = null;
		boolean bRetVal = true;
		// Create The Object for VO & BO .
		IpdBillManagementTransVO vo = null;
		HisUtil hisutil = null;
		try {

			vo = new IpdBillManagementTransVO();
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			String strChk = request.getParameter("chk");
			String strChk2 = request.getParameter("chk");

			s1 = formBean.getStrComboValue();
			s = s1[1] + "^" + s1[2];
			strChkBoxComboValue = strChk2 + "@" + s;

			vo.setStrComboValue(formBean.getStrComboValue()); // Setting For
			// List Page
			// Combo Value
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			String strTemp[] = strChk.replace('@', '#').split("#"); // Get The
			// Cr No....
			formBean.setStrCrNo(strTemp[1]);
			formBean.setStrAccountNo(strTemp[0]);
			// HLP - ClientApprovalDtl
			// strClientApprovalDtlHlp2 =
			// HLPClientApprovalDtl.getClientApprovalDetails_PUK(strTemp[1],strTemp[1],vo.getStrHospitalCode());
			String[] TestData2 = strClientAcctDtlHlp.split("\\####");
			if (TestData2[0].equals("ERROR")) {
				throw new Exception(TestData2[1]);
			} else {
				formBean.setStrClientApprovalDtl(TestData2[0]);
			}
			// HLP - AccountDtl
			strClientAcctDtlHlp = HLPAccountDtl.getAccountDetailsHLPBS(
					strTemp[0], strChkBoxComboValue, vo.getStrHospitalCode());
			String[] TestData3 = strClientAcctDtlHlp.split("\\####");
			if (TestData3[0].equals("ERROR")) {
				throw new Exception(TestData3[1]);
			} else {
				formBean.setStrClientAcctDtl(TestData3[0]);
			}
			// Get Client Detail
			String strPatView = "";
			strPatView = PatientDtlHLPNew.compactPatientWithAdmissionDtl(formBean
					.getStrCrNo(), vo.getStrHospitalCode(), false);
			formBean.setStrPatientDetailsView(strPatView);
			formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			bRetVal = false;
			msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->initClientApprovalDtl()",
					msgStr);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;
			hisutil = null;
		}
		return bRetVal;
	}

	/**
	 * ********************************Patient Detail
	 * for(ADD-SERVICE)******************************************
	 */
	
	
	/**
	 * initAddServicesDtl(request,response,formBean) -- > This Method
	 * generate the data for Client Approval Detail's Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static void initAddServicesDtl(HttpServletRequest request,HttpServletResponse response, IpdBillManagementTransFB formBean , String strMode) 
	{
 
		IpdBillManagementTransBO bo = null;
		IpdBillManagementTransVO vo = null;
		HisUtil hisutil = null;
		BillConfigUtil configUtil = null;
		try {

			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			configUtil = new BillConfigUtil(formBean.getStrHospitalCode());
			 
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrChargeTypeID(formBean.getStrChargeTypeID());
			vo.setStrAccountNo(formBean.getStrAccountNo());
			
			vo.setStrWardCode(formBean.getCombo()[2].replace("^", "#").split("#")[0]);
			vo.setStrWardType(formBean.getStrTempWardCode());
			formBean.setStrUrgSur(configUtil.getStrUrgTrfSur());
			
			bo.getAccountDtls(vo);
			formBean.setServiceFlag(vo.getServiceFlag());
			/*
			String strEndDate = vo.getStrEndDate();
				
				
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(strEndDate));
			c.add(Calendar.DATE, 1);  // number of days to add
			strEndDate = sdf.format(c.getTime());  // dt is now the new date

			vo.setStrEndDate(strEndDate);	
			*/
			System.out.println("strAdvanceamt"+vo.getStrAdvanceamt());
			formBean.setGrpid(vo.getGrpid());
			formBean.setStrAdvanceamt(vo.getStrAdvanceamt());	
			formBean.setStrStartDate(vo.getStrStartDate());
			formBean.setStrEndDate(vo.getStrEndDate());
					
			bo.getTreatmentCatAndWardTypeValues(vo);
			 
			if(vo.getStrMsgType().equals("1"))
			{				
				throw new Exception(vo.getStrMsgString());
			}
			
			String strTreatmentCat = "<option value='0'>Select Value</option>";
			
			if(vo.getOfflineTreatmentCategoryList() != null && vo.getOfflineTreatmentCategoryList().size() > 0)
			{			 
				strTreatmentCat = hisutil.getOptionValue(vo.getOfflineTreatmentCategoryList(),vo.getStrTreatmentCategory(), "", true);							
			}
			
			formBean.setStrTreatmentCategoryValues(strTreatmentCat);
			
			 if(strMode.equals("1"))
			 {				 
				 if(formBean.getStrTempWardCode().trim().length() > 0)
					 vo.setStrWardType(formBean.getStrTempWardCode().replace("^", "#").split("#")[0]);
				 
				 if(vo.getStrWardType().equals("1"))
				 {						
					 formBean.setStrWardTypeValues("<option value='1'>GENERAL</option>");						
				 }
				 else
				 {						
						formBean.setStrWardTypeValues("<option value='2'>PRIVATE</option>");						
				 }				 
			 }
			 else
			 {				 
				 //if(formBean.getStrTempWardCode().trim().length() > 0)
				 //vo.setStrWardType(formBean.getStrTempWardCode().replace("^", "#").split("#")[0]);		
				 
				 String strWardType = "<option value='0'>Select Value</option>";
					
					if(vo.getWardTypeList() != null && vo.getWardTypeList().size() > 0)
					{					 
						strWardType = hisutil.getOptionValue(vo.getWardTypeList(),vo.getStrWardType() , "0^Select value", false , false);							
					}
					
					formBean.setStrWardTypeValues(strWardType);
					 String strDept = "<option value='0'>Select Value</option>";
						
						if(vo.getStrDepartmentList() != null && vo.getStrDepartmentList().size() > 0)
						{						 
							strDept = hisutil.getOptionValue(vo.getStrDepartmentList(),vo.getStrDepartment(), "0^Select value", true);							
						}
						
						formBean.setStrDepartmentValues(strDept);						
						String strPrevDates = "<option value='0'>Select Value</option>";
						
						if(vo.getStrPreviousDatesList() != null && vo.getStrPreviousDatesList().size() > 0)
						{							 
								strPrevDates = hisutil.getOptionValue(vo.getStrPreviousDatesList(),
											"0", "0^Select value", true);							
						}							
						formBean.setStrPreviousDateValues(strPrevDates);
						LinkedHashMap<String, String> mapProcedureParam = new LinkedHashMap<String, String>();
						mapProcedureParam.put("mode_val", "1");
						mapProcedureParam.put("dept_code", vo.getStrDepartment());
						mapProcedureParam.put("hosp_code", formBean.getStrHospitalCode());
						mapProcedureParam.put("err", "#1");
						mapProcedureParam.put("resultset", "#2");
						
						formBean.setStrSpecialWardTypeValues(HisComboOptions.getOptionsFromProc("pkg_bill_view.proc_ward_list", mapProcedureParam, vo.getStrSpecialWardType(), "0^Select Value", false));									
			 }
					 
			 bo.getParticularDtlsList(vo);

			formBean.setStrParticularDtlsListWs(vo.getStrParticularDtlsListWs());

			formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));
			String sb=IpdBillManagementTransHLPNew.getViewBillParticularsList(formBean);
			
			bo.getAllTrfList(vo);
			String JSONObj=IpdBillManagementTransHLPNew.getAllTrfListJSON(vo);
			//System.out.println("JSONObj"+JSONObj);
			//formBean.setStrAllTrfJSON(JSONObj);
			formBean.setStrAllTrfHLP(vo.getStrAllTrfHLP());
			formBean.setStrNumRows(vo.getStrNumRows());
			String strParticulaDtl = HLPParticularDtl.getParticularDtlDiffPayable(vo.getStrAccountNo(),vo.getStrHospitalCode(),"0","0","0",null);
			String sb2=sb+""+strParticulaDtl;
			formBean.setStrParticulaDtl(sb2);				
				
			formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));
		} 
		catch (Exception e) 
		{
			HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->initClientApprovalDtl()",e.getMessage());
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{
			bo = null;
			vo = null;
			hisutil = null;
		}		
	}
	

	/**
	 * PatientDtl(request,response,formBean) -- > This Method generate the
	 * Patient Details for Add-Service Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */

	public static boolean PatientDtl(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean,
			String chk) {
		// Declaring Variable
		String msgStr = "";
		String strChk = null;
		String strChk2 = null;
		boolean bRetVal = true;
		String s = null;

		String strClientApprovalDtlHlp = null;
		String strClientAcctDtlHlp = null;
		// String value= null;
		String strPatView = "";
		// Creating Object for VO
		IpdBillManagementTransVO vo = null;
		BillConfigUtil billConfigUtil = null;

		try {

			billConfigUtil = new BillConfigUtil(formBean.getStrHospitalCode());

			formBean.setStrExcessCreditLimit(billConfigUtil.getIpdExcessCreditLimit());
			formBean.setStrArogyaIpdCreditLimit(billConfigUtil.getStrArogyaIpdCreditLimit());
			/*if (request.getParameter("chk") == null) {
				strChk = chk;
				strChk2 = chk;
			} else {
				strChk = request.getParameter("chk");
				strChk2 = request.getParameter("chk");
			}*/
			String StrChkTemp ="";
			if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
			{
				String[] arr=formBean.getChk();
				StrChkTemp =arr[0];
				
			}
			else
			{
				StrChkTemp =formBean.getStrChk();
			}
			if (StrChkTemp == null) {
				strChk = chk;
				strChk2 = chk;
			} else {
				strChk = StrChkTemp;
				strChk2 = StrChkTemp;
			}
			if (StrChkTemp == null) {
				strChk = chk;
				strChk2 = chk;
			} else {
				strChk = StrChkTemp;
				strChk2 = StrChkTemp;
			}
			// System.out.println("Add Service
			// Length-->"+formBean.getStrComboValue().length);
			if (formBean.getStrComboValue().length == 3) {
				String s1[] = formBean.getStrComboValue();
				s = s1[1] + "^" + s1[2];

			} else {
				String s1[] = request.getParameterValues("strComboVal");
				s = s1[0];
			}
			String strChkBoxComboValue = strChk2 + "@" + s;

			formBean.setStrChkBoxComboValue(s);
			vo = new IpdBillManagementTransVO();
			// Set the Value into formBean
			formBean.setStrChk(strChk);

			String strTemp[] = strChk.replace('@', '#').split("#"); // Get The
			// Cr No....
			vo.setStrComboValue(formBean.getStrComboValue()); // Setting For
			// List Page
			// Combo Value

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrAccountNo(strTemp[0]);
			formBean.setStrComboValue(vo.getStrComboValue());
			formBean.setStrCrNo(strTemp[1]);
			formBean.setStrCtDate(vo.getStrCtDate());
			formBean.setStrAccountNo(strTemp[0]);
			// Getting Value From BillConfigUtil

			int i = Integer.parseInt(billConfigUtil.getIpdThirdPartyBilling());
			formBean.setStrIpdRoundOff(billConfigUtil.getIpdRoundOff());

			// HLP - Patient Approval Dtl
			if (i == 1) {
				formBean.setStrIpdThirdPartyBilling(billConfigUtil
						.getIpdThirdPartyBilling());
				strClientApprovalDtlHlp = HLPClientApprovalDtl
						.getClientApprovalDetails_APPID(strTemp[2], strTemp[1],
								vo.getStrHospitalCode());
				String[] TestData2 = strClientApprovalDtlHlp.split("\\####");
				if (TestData2[0].equals("ERROR")) {
					throw new Exception(TestData2[1]);
				} else {
					strClientApprovalDtlHlp = TestData2[0];
				}
			} else {
				formBean.setStrIpdThirdPartyBilling("0");
				strClientApprovalDtlHlp = "";
			}
			formBean.setStrClientApprovalDtl(strClientApprovalDtlHlp);

			// Pateint Detail HLP...Which..Replace TLD
			strPatView = PatientDtlHLPNew.compactPatientWithAdmissionDtlWithoutCatExpiryCheck(formBean.getStrCrNo(), vo.getStrHospitalCode(), false);
			formBean.setStrPatientDetailsView(strPatView);
			// HLP - Account Dtl With Exception Handling
			strClientAcctDtlHlp = HLPAccountDtl.getAccountDetailsHLPBS(strTemp[0], strChkBoxComboValue, vo.getStrHospitalCode());
			String[] TestData1 = strClientAcctDtlHlp.split("\\####");
			System.out.println("---------------------------------------------------------------- TestData1");
			System.out.println(TestData1[0]);
			if (TestData1[0].equals("ERROR")) {
				throw new Exception(TestData1[1]);
			} else {
				formBean.setStrClientAcctDtl(TestData1[0]);
			}

		} catch (Exception e) {

			e.printStackTrace();

			bRetVal = false;
			msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->PatientDtl()", msgStr);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;
			billConfigUtil = null;

		}

		return bRetVal;
	}

	/**
	 * PatientDtl1(request,response,formBean) -- > This Method generate the
	 * Patient Details for Add-Service Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */

	public static boolean PatientDtl1(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean,
			String chk) {
		// Declaring Variable
		String msgStr = "";
		String strChk = null;
		String strChk2 = null;
		boolean bRetVal = true;
		String s = null;

		String strClientApprovalDtlHlp = null;
		String strClientAcctDtlHlp = null;
		// String value= null;
		String strPatView = "";
		// Creating Object for VO
		IpdBillManagementTransVO vo = null;

		try {

			if (request.getParameter("strChk") == null) {
				strChk = chk;
				strChk2 = chk;
			} else {
				strChk = request.getParameter("strChk");
				strChk2 = request.getParameter("strChk");
			}
			// System.out.println("Add Service
			// Length-->"+formBean.getStrComboValue().length);
			if (formBean.getStrComboValue().length == 3) {
				String s1[] = formBean.getStrComboValue();
				s = s1[1] + "^" + s1[2];

			} else {
				String s1[] = request.getParameterValues("strComboVal");
				s = s1[0];
			}
			String strChkBoxComboValue = strChk2 + "@" + s;

			formBean.setStrChkBoxComboValue(s);
			vo = new IpdBillManagementTransVO();
			// Set the Value into formBean
			formBean.setStrChk(strChk);

			String strTemp[] = strChk.replace('@', '#').split("#"); // Get The
			// Cr No....
			vo.setStrComboValue(formBean.getStrComboValue()); // Setting For
			// List Page
			// Combo Value

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrAccountNo(strTemp[0]);
			formBean.setStrComboValue(vo.getStrComboValue());
			formBean.setStrCrNo(strTemp[1]);
			formBean.setStrCtDate(vo.getStrCtDate());
			formBean.setStrAccountNo(strTemp[0]);
			// Getting Value From BillConfigUtil
			BillConfigUtil billConfigUtil = new BillConfigUtil(formBean.getStrHospitalCode());
			int i = Integer.parseInt(billConfigUtil.getIpdThirdPartyBilling());
			formBean.setStrIpdRoundOff(billConfigUtil.getIpdRoundOff());

			// HLP - Patient Approval Dtl
			if (i == 1) {
				formBean.setStrIpdThirdPartyBilling(billConfigUtil
						.getIpdThirdPartyBilling());
				strClientApprovalDtlHlp = HLPClientApprovalDtl
						.getClientApprovalDetails_APPID(strTemp[2], strTemp[1],
								vo.getStrHospitalCode());
				String[] TestData2 = strClientApprovalDtlHlp.split("\\####");
				if (TestData2[0].equals("ERROR")) {
					throw new Exception(TestData2[1]);
				} else {
					strClientApprovalDtlHlp = TestData2[0];
				}
			} else {
				formBean.setStrIpdThirdPartyBilling("0");
				strClientApprovalDtlHlp = "";
			}
			formBean.setStrClientApprovalDtl(strClientApprovalDtlHlp);

			// Pateint Detail HLP...Which..Replace TLD
			strPatView = PatientDtlHLPNew.compactPatientWithAdmissionDtl(formBean
					.getStrCrNo(), vo.getStrHospitalCode(), false);
			formBean.setStrPatientDetailsView(strPatView);
			// HLP - Account Dtl With Exception Handling
			strClientAcctDtlHlp = HLPAccountDtl.getAccountDetailsHLPBS(
					strTemp[0], strChkBoxComboValue, vo.getStrHospitalCode());
			String[] TestData1 = strClientAcctDtlHlp.split("\\####");
			if (TestData1[0].equals("ERROR")) {
				throw new Exception(TestData1[1]);
			} else {
				formBean.setStrClientAcctDtl(TestData1[0]);
			}

		} catch (Exception e) {
			bRetVal = false;
			msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->PatientDtl()", msgStr);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

		}

		return bRetVal;
	}

	/** *************************initDtl*********************************** */

	/**
	 * initDtl(formBean) -- > This Method generate the data for VIEWCHARGES Page
	 * 
	 * @param form
	 */
	public static boolean initDtl(IpdBillManagementTransFB formBean) {
		// Declaring Variables
		String msgStr = "";
		boolean bRetVal = true;
		// Creating Object for VO
		IpdBillManagementTransVO vo = null;

		try {
			vo = new IpdBillManagementTransVO();
			vo.setStrComboValue(formBean.getStrComboValue()); // Setting For
			// List Page
			// Combo Value
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			String s[] = formBean.getStrComboValue();
			formBean.setStrWardCode(s[2]);
			// ComboGroup is Local Method Which Used to Get the Combo Value for
			// Four Group of Combo
			ComboGroup(formBean);
			// Set Messages into form bean
			formBean.setStrMsgString(vo.getStrMsgString());
			formBean.setStrMsgType(vo.getStrMsgType());
			// Check error
			if (formBean.getStrMsgType().equals("1")) {
				throw new Exception(formBean.getStrMsgString());
			}
		} catch (Exception e) {
			bRetVal = false;
			msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->initDtl()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {
			if (vo != null)
				vo = null;
		}
		return bRetVal;
	}

	/** **************************Combo Group********************************* */
	public static void ComboGroup(IpdBillManagementTransFB formBean) {
		// Declaring variables
		String msgStr = "";
		String str = null;
		// Creating VO & BO Object
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;
		HisUtil hisutil = null;

		try {
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			vo.setStrComboValue(formBean.getStrComboValue()); // Setting For
			// List Page
			// Combo Value
			vo.setStrWardCode(formBean.getStrWardCode().replace("^", "#").split("#")[0]); // Setting Ward Code
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			// Call BO Method
			bo.getComboValues(vo);
			// Get Hospital Service Combo...!
			if (vo.getStrHospitalServiceCombo() != null
					&& vo.getStrHospitalServiceCombo().size() > 0) {
				str = hisutil.getOptionValue(vo.getStrHospitalServiceCombo(),
						"2", "0^Select value", true);
				formBean.setStrHospitalServiceCombo(str);
			}

			else {
				// Set Messages into form bean
				str = "<option value='0'>Select Value</option>";
				formBean.setStrHospitalServiceCombo(str);
				formBean.setStrMsgString(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}
			}
			// Get Patient Category Combo..
			if (vo.getStrPatientCategoryCombo() != null
					&& vo.getStrPatientCategoryCombo().size() > 0) {
				str = hisutil.getOptionValue(vo.getStrPatientCategoryCombo(),
						"0", "0^Select value", true);
				formBean.setStrPatientCategoryCombo(str);
			}

			else {
				// Set Messages into form bean
				str = "<option value='0'>Select Value</option>";
				formBean.setStrPatientCategoryCombo(str);
				formBean.setStrMsgString(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}

			}

			formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));

			// Get Ward name Combo..
			if (vo.getStrWardnameCombo() != null
					&& vo.getStrWardnameCombo().size() > 0) {
				// Here we Split Ward Code from Ipd_Charge_Type_Id
				String[] wardid = vo.getStrWardCode().split("\\^");

				if (wardid[0].equals("0")) {

					str = hisutil.getOptionValue(vo.getStrWardnameCombo(), "0",
							"0^Select value", true);
				} else {

					str = hisutil.getOptionValue(vo.getStrWardnameCombo(),
							wardid[0], "0^Select value", true);
				}
				formBean.setStrWardnameCombo(str);
			}

			else {
				// Set Messages into form bean
				str = "<option value='0'>Select Value</option>";
				formBean.setStrWardnameCombo(str);
				formBean.setStrMsgString(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}
			}

		} catch (Exception e) {
			msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->ComboGroup()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			bo = null;

			hisutil = null;
		}

	}

	/** **********************Method to Get Combo Value************************** */
	public static void ComboValue(String strChk,
			IpdBillManagementTransFB formBean) {
		// Declaring Variable
		String strDeptWard = null;
		String msgStr = "";
		// Creating VO Object
		IpdBillManagementTransVO vo = null;
		HisUtil hisutil = null;
		try {
			vo = new IpdBillManagementTransVO();
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			vo.setStrComboValue(formBean.getStrComboValue()); // Setting For
			// List Page
			// Combo Value
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			// Get Remarks Combo.
			if (vo.getStrRemarksCombo() != null
					&& vo.getStrRemarksCombo().size() > 0) {
				String str = hisutil.getOptionValue(vo.getStrRemarksCombo(),
						"", "", true);
				formBean.setStrRemarksCombo(str);
			}

			else {
				// Set Messages into form bean
				String str = "";
				formBean.setStrRemarksCombo(str);
				formBean.setStrMsgString(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}
			}
			// Get Approved By Combo
			if (vo.getStrApprovedByCombo() != null
					&& vo.getStrApprovedByCombo().size() > 0) {
				String str = hisutil.getOptionValue(vo.getStrApprovedByCombo(),
						"", "", true);
				formBean.setStrApprovedByCombo(str);
			}

			else {
				// Set Messages into form bean
				String str = "<option value='0'>Select Value</option>";
				formBean.setStrApprovedByCombo(str);
				formBean.setStrMsgString(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}
			}

			formBean.setStrPresentAcctStatus(vo.getStrPresentAcctStatus());
			formBean.setStrDeptWard(strDeptWard);

		} catch (Exception e) {
			msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->ComboValue()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			hisutil = null;
		}

	}

	
	public static void getSpecialWardDtls(HttpServletRequest request,HttpServletResponse response) 
	{
		String temp = "";
		try 
		{			 
			String strDept = request.getParameter("strDept");
			String strHospCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();			
			LinkedHashMap<String, String> mapProcedureParam = new LinkedHashMap<String, String>();
			
			mapProcedureParam.put("mode_val", "1");
			mapProcedureParam.put("dept_code", strDept);
			mapProcedureParam.put("hosp_code", strHospCode);
			mapProcedureParam.put("err", "#1");
			mapProcedureParam.put("resultset", "#2");
			
			temp = HisComboOptions.getOptionsFromProc("pkg_bill_view.proc_ward_list", mapProcedureParam, "0", "0^Select Value", false);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->getSpecialWardDtls()",strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} 
			catch (IOException e1) 
			{
				new HisException("Billing","IpdBillManagementTransBSDATA->getSpecialWardDtls()", e1.getMessage());
			}
		}
	}	
	
	public static void getPreviousDtls(HttpServletRequest request,HttpServletResponse response) 
	{		
		IpdBillManagementTransBO bo = null;
		IpdBillManagementTransVO voObj = null;
		
		try 
		{
			bo = new IpdBillManagementTransBO();
			voObj = new IpdBillManagementTransVO();			 
			 
			String strAccNo = request.getParameter("strAccNo");
			String strPreviousDates = request.getParameter("strPreviousDates");
			String strHospCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();		 	
			
			voObj.setStrAccountNo(strAccNo);
			voObj.setStrPreviousDates(strPreviousDates.replace("^", "#"));
			voObj.setStrHospitalCode(strHospCode);
			
			bo.getPreviousDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}			
			String temp = IpdBillManagementTransHLPNew.getPreviousDtlsView(voObj.getStrPreviousDtls());
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->getSpecialWardDtls()",strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} 
			catch (IOException e1) 
			{
				new HisException("Billing","IpdBillManagementTransBSDATA->getSpecialWardDtls()", e1.getMessage());
			}
		} 
		finally 
		{	
			bo = null;
			voObj = null;
		}
	}	

	public static void deletePreviousDtls(HttpServletRequest request,
			HttpServletResponse response) {
 		 
		
		IpdBillManagementTransBO bo = null;
		IpdBillManagementTransVO voObj = null;
		
		try {
			bo = new IpdBillManagementTransBO();
			voObj = new IpdBillManagementTransVO();
			 
			
			String strPreviousVals =  request.getParameter("strPreviousVals");
		
			
			String strHospCode = request.getSession().getAttribute(
			"HOSPITAL_CODE").toString();
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			
			String[] strTemp = strPreviousVals.replace("$", "#").split("#");
					
			voObj.setStrPreviousCheck(strTemp);
		 			
			voObj.setStrHospitalCode(strHospCode);
			bo.deletePreviousDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			
			String temp = "Data Deleted";

			 		 
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
		 
			new HisException("Billing",
					"IpdBillManagementTransBSDATA->getSpecialWardDtls()",
					e.getMessage());
		 						
			
		} finally {

			 bo = null;
			voObj = null;
		 

		}

	}
	
	/**
	 * ******************************INSERT STATEMENT FOR ADD
	 * SERVICE(In-Complete)************************
	 */
	/**
	 * InsertAddService(request,response,formBean) -- > Insert Logic for Add
	 * Service Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static boolean InsertAddService(HttpServletRequest request,HttpServletResponse response, IpdBillManagementTransFB formBean) 
	{
		boolean fretValue = true;
		String strmsgText = "";
		String strMsg = "";
		String strWarning = "";
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;

		try 
		{
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			if(formBean.getIsBillFinal()!=null && !formBean.getIsBillFinal().equals(""))
			{
				vo.setIsBillFinal(formBean.getIsBillFinal());
			}
			else
				vo.setIsBillFinal("90");
			
			String strChk = request.getParameter("strChk");
			formBean.setStrChk(strChk);
			String[] strTemp = strChk.split("\\@");

			vo.setStrPatAcctNo(strTemp[0]);
			vo.setStrPukNo(strTemp[1]);
			vo.setStrPatientCatCode(formBean.getStrNewTreatmentCategory());
			vo.setStrRemarks(formBean.getStrRemarks());
			//vo.setStrWardType(formBean.getStrTempWardCode().replace("^", "#").split("#")[0]);
			vo.setStrWardType(formBean.getStrWardType().replace("^", "#").split("#")[0]);
			vo.setStrChargeTypeID(strTemp[4]);
			vo.setStrIpdChgTypeId(strTemp[4]);
			vo.setStrAddmissionNo(strTemp[5]);
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrNetActualTariffAmt("0");
			vo.setStrNetServiceTaxAmt("0");
			vo.setStrNetTariffAmt("0");			
			vo.setStrCompChargeCheck(formBean.getStrCompChargeCheck());		
			vo.setStrDepartment(formBean.getStrDepartment());
			vo.setStrSpecialWardType(formBean.getStrSpecialWardType());
			//vo.setStrStartDate(formBean.getStrStartDate());
			//vo.setStrEndDate(formBean.getStrEndDate());
			vo.setStrStartDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date(formBean.getStrStartDate())));
			vo.setStrEndDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date(formBean.getStrEndDate())));			
			vo.setStrSpecialChargeCheck(formBean.getStrSpecialChargeCheck());
			int length =0;
			if(formBean.getStrOfflineTariffDetailsHidden()!=null)
				length = formBean.getStrOfflineTariffDetailsHidden().length;
			
			String[] strTariffId = new String[length];
			String[] strGrpId = new String[length];
			String[] strTariffRate = new String[length];
			String[] strUnitId = new String[length];
			String[] strUnitBaseValue = new String[length];
			String[] strIsPackaged = new String[length];
			String[] strIsRefundable = new String[length];
			// String[] strTariffActCost = new String[length];
			String[] strTariffActualCost = new String[length];
			String[] strServTax = new String[length];
			String[] strServiceId = new String[length];
			String[] strGblTariffId = new String[length];
			String[] strBillQty = new String[length];
			String[] strTariffDate = new String[length];
			String[] strPriority = new String[length];
			String[] strDiscount = new String[length];
			String[] strDiscountType = new String[length];
			String[] strDiscountAmt = new String[length];
			String[] strTrfName = new String[length];

			String[] strServiceType = new String[length];			
			String[] strRateBaseValue = new String[length];
			String[] strTariffAmtValue = new String[length];
			String[] sno = new String[length];

			if (formBean.getStrOfflineTariffNetAmount() != null) 
			{
				for (int i = 0; i < formBean.getStrOfflineTariffNetAmount().length; i++) 
				{
					if (formBean.getStrOfflineTariffNetAmount()[i] != null || formBean.getStrOfflineTariffNetAmount()[i] != "") 
					{
						strTariffAmtValue[i] = formBean.getStrOfflineTariffNetAmount()[i];
					}
				}
			}

			if (formBean.getStrOfflineTariffUnit() != null) 
			{
				for (int i = 0; i < formBean.getStrOfflineTariffUnit().length; i++) 
				{
					if (formBean.getStrOfflineTariffUnit()[i] != null || formBean.getStrOfflineTariffUnit()[i] != "") 
					{
						String[] strUnitCombo = formBean.getStrOfflineTariffUnit()[i].split("\\^");						
						if(strUnitCombo.length >= 2)
						{							
							strUnitBaseValue[i] = strUnitCombo[1];							
						}
						else
						{							
							strUnitBaseValue[i] = formBean.getStrOfflineTariffDetailsHidden()[i].replace("^", "#").split("#")[6];							
						}
					}
				}
			}

			if (formBean.getStrOfflineTariffDetailsHidden() != null) 
			{
				for (int i = 0; i < formBean.getStrOfflineTariffDetailsHidden().length; i++) 
				{
					if (formBean.getStrOfflineTariffDetailsHidden()[i] != null || formBean.getStrOfflineTariffDetailsHidden()[i] != "") 
					{
						String[] strMultiRow = formBean.getStrOfflineTariffDetailsHidden()[i].split("\\^");
						strTariffId[i] = strMultiRow[0];
						strGrpId[i] = strMultiRow[1];
						// strTariffActCost[i] = strMultiRow[11];
						strIsPackaged[i] = strMultiRow[7];
						strRateBaseValue[i] = strMultiRow[6];
						strTariffActualCost[i] = strMultiRow[11];
						strServTax[i] = strMultiRow[13];
						strUnitId[i] = strMultiRow[5];
						strIsRefundable[i] = strMultiRow[9];
						strServiceId[i] = strMultiRow[15];
						strGblTariffId[i] = strMultiRow[16];
						sno[i]= strMultiRow[20];
						/*if(strMultiRow.length == 19){
						
							strServiceType[i] = strMultiRow[18];
							
						}else{
							strServiceType[i] = "3";
						}*/
						/*if(strMultiRow.length == 20){
							
							strServiceType[i] = strMultiRow[17];
							
						}else{
							strServiceType[i] = "3";
						}*/
						strServiceType[i] = "3";
						
					}
				}
			}
			if (formBean.getStrOfflineTariffRateUnit() != null) 
			{
				for (int i = 0; i < formBean.getStrOfflineTariffRateUnit().length; i++) 
				{
					if (formBean.getStrOfflineTariffRateUnit()[i] != null || formBean.getStrOfflineTariffRateUnit()[i] != "") 
					{
						String strTemp1[] = formBean.getStrOfflineTariffRateUnit()[i].replace('/','#').split("#");
						strTariffRate[i] = strTemp1[0];
					}
				}
			}
			if (formBean.getStrOfflineTariffQty() != null) 
			{
				for (int i = 0; i < formBean.getStrOfflineTariffQty().length; i++) 
				{
					if (formBean.getStrOfflineTariffQty()[i] != null || formBean.getStrOfflineTariffQty()[i] != "") 
					{
						strBillQty[i] = formBean.getStrOfflineTariffQty()[i];
					}
				}
			}
			if (formBean.getStrOfflineTariffDate() != null) 
			{
				for (int i = 0; i < formBean.getStrOfflineTariffDate().length; i++) 
				{
					if (formBean.getStrOfflineTariffDate()[i] != null || formBean.getStrOfflineTariffDate()[i] != "") 
					{
						strTariffDate[i] = formBean.getStrOfflineTariffDate()[i];
					}
				}
			}
			if (formBean.getStrPriority() != null) 
			{
				for (int i = 0; i < formBean.getStrPriority().length; i++) 
				{
					if (formBean.getStrPriority()[i] != null || formBean.getStrPriority()[i] != "") 
					{
						strPriority[i] = formBean.getStrPriority()[i];
					}
				}
			}
			if (formBean.getStrDiscount() != null) 
			{
				for (int i = 0; i < formBean.getStrDiscount().length; i++) 
				{
					if (formBean.getStrDiscount()[i] != null || formBean.getStrDiscount()[i] != "") 
					{
						strDiscount[i] = formBean.getStrDiscount()[i];
					}
				}
			}
			if (formBean.getStrDiscountType() != null) 
			{
				for (int i = 0; i < formBean.getStrDiscountType().length; i++) 
				{
					if (formBean.getStrDiscountType()[i] != null || formBean.getStrDiscountType()[i] != "") 
					{
						strDiscountType[i] = formBean.getStrDiscountType()[i];
					}
				}
			}
			if (formBean.getStrDiscountAmt() != null) 
			{
				for (int i = 0; i < formBean.getStrDiscountAmt().length; i++) 
				{
					if (formBean.getStrDiscountAmt()[i] != null || formBean.getStrDiscountAmt()[i] != "") 
					{
						strDiscountAmt[i] = formBean.getStrDiscountAmt()[i];
					}
				}
			}
			if (formBean.getStrOfflineTariffServiceTax() != null) 
			{
				for (int i = 0; i < formBean.getStrOfflineTariffServiceTax().length; i++) 
				{
					if (formBean.getStrOfflineTariffServiceTax()[i] != null || formBean.getStrOfflineTariffServiceTax()[i] != "") 
					{
						strServTax[i] = formBean.getStrOfflineTariffServiceTax()[i];
					}
				}
			}
			if (formBean.getStrOfflineTariffName() != null) 
			{
				for (int i = 0; i < formBean.getStrOfflineTariffName().length; i++) 
				{
					if (formBean.getStrOfflineTariffName()[i] != null || formBean.getStrOfflineTariffName()[i] != "") 
					{
						if(formBean.getStrOfflineTariffName()[i].contains("- ("))
							strTrfName[i]=formBean.getStrOfflineTariffName()[i].substring(0, formBean.getStrOfflineTariffName()[i].lastIndexOf("- (")-1);
						else
							strTrfName[i]=formBean.getStrOfflineTariffName()[i];
						if(strPriority[i].equals("2"))
							strTrfName[i] = strTrfName[i]+"(U)";
					}
				}
			}

			vo.setStrTariffId(strTariffId);
			vo.setStrGrpId(strGrpId);
			vo.setStrTariffRate(strTariffRate);
			vo.setStrBillQty(strBillQty);
			vo.setStrOfflineTariffDate(strTariffDate);
			vo.setStrServTax(strServTax);
			vo.setStrPriority(strPriority);
			vo.setStrDiscount(strDiscount);
			vo.setStrDiscountType(strDiscountType);
			vo.setStrDiscountAmt(strDiscountAmt);
			vo.setStrOfflineTariffName(strTrfName);
			// vo.setStrTariffActCost(strTariffActCost);
			vo.setStrIsPackg(strIsPackaged);
			vo.setStrUnitBaseValue(strUnitBaseValue);
			vo.setStrRateBaseValue(strRateBaseValue);
			vo.setStrTariffActualCost(strTariffActualCost);
			vo.setStrServTaxAmt(strServTax);
			vo.setStrUnitIDVal(strUnitId);
			vo.setStrTariffNetAmt(strTariffAmtValue);
			vo.setStrIsRefundable(strIsRefundable);
			vo.setStrServiceId(strServiceId);
			vo.setStrGlblTariffId(strGblTariffId);
			vo.setStrServiceType(strServiceType);
			vo.setsNo(sno);
			vo.setStrPrevReqNo(formBean.getStrPrevReqNo());
			vo.setDeleteFlag(formBean.getDeleteFlag());
			vo.setStrDisActAmt(formBean.getStrDisActAmt());
			//System.out.println("formBean.getStrOfflineTotalPayAmountWithoutConsumable()"+formBean.getStrOfflineTotalPayAmountWithoutConsumable());
			fretValue = bo.insertAddService(vo);
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			if (fretValue) 
			{
				strMsg = "Record saved successfully!";
				formBean.setStrMsg(strMsg);
			} 
			else 
			{
				strWarning = "Record Not Saved!Data Already Exist!!";
				formBean.setStrWarning(strWarning);
			}
			return fretValue;
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			fretValue = false;
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->InserAddService()", strmsgText);
			formBean.setStrErrMsg("Patient Account Error/Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{
			bo = null;
			vo = null;
		}
		return fretValue;
	}

	/**
	 * *************************INSERT STATEMENT FOR
	 * BILL-APPROVAL**************************
	 */

	/**
	 * InsertBillApproval(request,response,formBean) -- > This Method contain
	 * Insert Logic for Bill Approval
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */

	public static void InsertBillApproval(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean) {
		// Declaring Variables

		String strmsgText = "";
		String strMsg = "";

		int chklength = 0;
		int length = 0;
		// Creating VO & BO Object
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;

		BillConfigUtil billUtil = null;

		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();

			

			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			vo.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());

			billUtil = new BillConfigUtil(vo.getStrHospitalCode());
			vo.setTotalRecAmtDivId1(formBean.getTotalRecAmtDivId1());

			vo.setStrTotalDiscAmt(formBean.getStrTotalDisAmt());

			vo.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);

			vo.setStrServiceTax(billUtil.getIpdServiceTaxOnTotalBill());
			vo.setStrAccountNo(formBean.getStrChkVal());
			
			vo.setStrCrNo(formBean.getStrCrNo());
			 
			
			
 
			vo.setIsApproved(formBean.getIsApproved());
			
			vo.setStrPrimaryCategory(formBean.getStrTreatmentCategory());
			
			if (formBean.getStrOfflineTariffDetailsHidden() != null) {
				length = formBean.getStrOfflineTariffDetailsHidden().length;
			}
			/*
			 * if (formBean.getStrRefundQty() != null) { chklength =
			 * formBean.getStrRefundQty().length; }
			 */

			if (formBean.getChkBoxHidden() != null) {

				chklength = formBean.getChkBoxHidden().length;
			}

			vo.setNChkSize(chklength);
			vo.setStrApprovalBy(formBean.getStrApprovedByCombo());
			String[] strUnProcReqDate = new String[chklength];
			String[] strTariffIdUnProcQty = new String[chklength];
			String[] strUnProcQty = new String[chklength];
			String[] strUnProcRefQty = new String[chklength];
			String[] strUnProcUnitCombo = new String[chklength];
			String[] strUnProcNetAmt = new String[chklength];
			/*--------------Hidden Field-----------*/
			String[] strUnProReqNo = new String[chklength];
			String[] strUnProDeptCode = new String[chklength];
			String[] strUnProWardCode = new String[chklength];
			String[] strUnProTariffId = new String[chklength];
			String[] strUnProcBServiceId = new String[chklength];
			String[] strUnProcIpdChgTypeId = new String[chklength];

			String[] strUnProServiceId = new String[chklength];
			String[] strUnProHblDisUnit = new String[chklength];
			String[] strUnProHblDisType = new String[chklength];
			String[] strUnProGstrTariffId = new String[chklength];
			String[] strUnProcGstrReqNo = new String[chklength];
			String[] strUnProcHbAppId = new String[chklength];

			String[] strUnProTariffRate = new String[chklength];
			String[] strUnProRateUnitCode = new String[chklength];
			String[] strUnProQtyUnitId = new String[chklength];
			String[] strUnProHbReciptNo = new String[chklength];
			String[] strUnProcTariffActualRate = new String[chklength];
			String[] strUnProcHbIsPkg = new String[chklength];

			String[] strUnProRemainQty = new String[chklength];
			String[] strUnProUnitBaseVal = new String[chklength];
			String[] strUnProcSercTax = new String[chklength];
			String[] strUnProcUnitName = new String[chklength];
			String[] strSeviceTax = new String[chklength];
			String[] strServiceTaxAmt = new String[chklength];
			String[] strDiscAmt = new String[chklength];
			String[] strUnProcGroupId = new String[chklength];

			String[] strNetAmt = new String[chklength];

			String[] strActualTariffAmt = new String[chklength];

			// System.out.println("chklength-->>>>" + chklength);

			/*----------------Testing-------------------*/
			for (int i = 0; i < chklength; i++) {
				vo.setChkBox1(formBean.getChkBoxHidden());
				if (formBean.getChkBoxHidden()[i].equals("1")) {

					strUnProcReqDate[i] = formBean.getStrReqDate()[i];
					strTariffIdUnProcQty[i] = formBean
							.getStrUnProcTarriffName()[i];
					strUnProcQty[i] = formBean.getStrUnProcessQty()[i];
					strUnProcRefQty[i] = formBean.getStrRefundQty()[i];
					strUnProcUnitCombo[i] = formBean.getStrUnitCombo()[i];
					strUnProcNetAmt[i] = formBean.getStrUnProcNetAmt()[i];
					strDiscAmt[i] = formBean.getStrDiscAmt()[i];
					strSeviceTax[i] = formBean.getStrServiceTax()[i];
					strNetAmt[i] = formBean.getStrNetAmt()[i];

					strActualTariffAmt[i] = formBean.getStrActualTariffNetAmt()[i];

					strServiceTaxAmt[i] = formBean.getStrSeviceTaxAmt()[i];

					if (formBean.getStrUnProcHiddVal() != null) {
						String[] strUnProQty = formBean.getStrUnProcHiddVal()[i]
								.replace("^", "#").split("#");

						strUnProReqNo[i] = strUnProQty[0];
						strUnProDeptCode[i] = strUnProQty[1];
						strUnProWardCode[i] = strUnProQty[2];
						strUnProTariffId[i] = strUnProQty[3];
						strUnProcBServiceId[i] = strUnProQty[4];
						strUnProcIpdChgTypeId[i] = strUnProQty[5];

						strUnProServiceId[i] = strUnProQty[6];
						strUnProHblDisUnit[i] = strUnProQty[7];
						strUnProHblDisType[i] = strUnProQty[8];
						strUnProGstrTariffId[i] = strUnProQty[9];
						strUnProcGstrReqNo[i] = strUnProQty[10];
						strUnProcHbAppId[i] = strUnProQty[11];

						strUnProTariffRate[i] = strUnProQty[12];
						strUnProRateUnitCode[i] = strUnProQty[13];
						strUnProQtyUnitId[i] = strUnProQty[14];
						strUnProHbReciptNo[i] = strUnProQty[15];
						strUnProcTariffActualRate[i] = strUnProQty[16];
						strUnProcHbIsPkg[i] = strUnProQty[17];

						strUnProRemainQty[i] = strUnProQty[18];
						strUnProUnitBaseVal[i] = strUnProQty[19];
						strUnProcSercTax[i] = strUnProQty[20];
						strUnProcUnitName[i] = strUnProQty[21];
						strUnProcGroupId[i] = strUnProQty[22];

					}
				}
			}

			/*----------------Set Un-Process Value------------------*/
			vo.setStrUnProcRefQty(strUnProcRefQty);
			vo.setStrUnitCombo(strUnProcUnitCombo);
			vo.setStrUnProcessServiceTax(strSeviceTax);
			vo.setStrUnProcessServiceTaxAmt(strServiceTaxAmt);

			vo.setStrUnProQtyUnitId(strUnProQtyUnitId);

			vo.setStrUnProcNetAmt(strNetAmt);

			vo.setStrActualTariffNetAmt(strActualTariffAmt);

			vo.setStrUnProReqNo(strUnProReqNo);
			vo.setStrUnProDeptCode(strUnProDeptCode);
			vo.setStrUnProWardCode(strUnProWardCode);
			vo.setStrUnProTariffId(strUnProTariffId);
			vo.setStrUnProcBServiceId(strUnProcBServiceId);
			vo.setStrUnProcIpdChgTypeId(strUnProcIpdChgTypeId);
			vo.setStrUnProServiceId(strUnProServiceId);
			vo.setStrUnProHblDisUnit(strUnProHblDisUnit);
			vo.setStrUnProHblDisType(strUnProHblDisType);
			vo.setStrUnProGstrTariffId(strUnProGstrTariffId);
			vo.setStrUnProcGstrReqNo(strUnProcGstrReqNo);
			vo.setStrUnProcHbAppId(strUnProcHbAppId);
			vo.setStrUnProTariffRate(strUnProTariffRate);
			vo.setStrUnProRateUnitCode(strUnProRateUnitCode);
			vo.setStrUnProQtyUnitId(strUnProQtyUnitId);

			vo.setStrUnProHbReciptNo(strUnProHbReciptNo);
			vo.setStrUnProcTariffActualRate(strUnProcTariffActualRate);
			vo.setStrUnProcHbIsPkg(strUnProcHbIsPkg);
			vo.setStrUnProRemainQty(strUnProRemainQty);
			vo.setStrUnProUnitBaseVal(strUnProUnitBaseVal);
			vo.setStrUnProcSercTax(strUnProcSercTax);
			vo.setStrUnProcUnitName(strUnProcUnitName);
			vo.setStrDiscAmt(strDiscAmt);
			// vo.setStrUnProcServiceTaxAmt(strServiceTaxAmt);
			vo.setStrUnProcGroupId(strUnProcGroupId);

			/*-------------------------------------------*/

			String[] strTariffId = new String[length];
			String[] strGrpId = new String[length];
			String[] strTariffRate = new String[length];
			String[] strBillQty = new String[length];
			// String[] strServTax = new String[length];
			String[] strTariffActCost = new String[length];
			String[] strIsPackaged = new String[length];
			String[] strIsRefundable = new String[length];
			String[] strUnitBaseValue = new String[length];
			String[] strRateBaseValue = new String[length];
			String[] strTariffActualCost = new String[length];
			String[] strServTax = new String[length];
			String[] strServTaxAmt = new String[length];
			String[] strUnitId = new String[length];
			String[] strUnitName = new String[length];
			String[] strServiceId = new String[length];
			String[] strGlblTariffId = new String[length];

			String[] strTariffAmtValue = new String[length];

			/*-------------------- Data From Drop Down Utility -----------------------*/
			if (formBean.getStrOfflineTariffDetailsHidden() != null
					&& formBean.getStrOfflineTariffDetailsHidden().length > 0) {
				for (int i = 0; i < formBean.getStrOfflineTariffDetailsHidden().length; i++) {
					// System.out.println("Net
					// Amt("+i+")Value->"+formBean.getStrOfflineTariffDetailsHidden()[i]);
					String[] strMultiRow = formBean
							.getStrOfflineTariffDetailsHidden()[i].replace("^",
							"#").split("#");

					strTariffId[i] = strMultiRow[0];
					strGrpId[i] = strMultiRow[1];
					strTariffActCost[i] = strMultiRow[11];
					strIsPackaged[i] = strMultiRow[7];
					strRateBaseValue[i] = strMultiRow[6];
					strTariffActualCost[i] = strMultiRow[11];
					// strServTax[i] = strMultiRow[13];
					strUnitName[i] = strMultiRow[14];
					strIsRefundable[i] = strMultiRow[9];
					strServiceId[i] = strMultiRow[15];
					strGlblTariffId[i] = strMultiRow[16];
					strUnitId[i] = strMultiRow[5];

					String strTemp1[] = formBean.getStrOfflineTariffRateUnit()[i]
							.replace('/', '#').split("#");
					strTariffRate[i] = strTemp1[0];

					strBillQty[i] = formBean.getStrOfflineTariffQty()[i];
					strServTax[i] = formBean.getStrOfflineTariffServiceTax()[i];
					strServTaxAmt[i] = formBean
							.getStrOfflineTariffServiceTaxAmtVal()[i];

					strTariffAmtValue[i] = formBean
							.getStrOfflineTariffNetAmount()[i];

					String[] strUnitCombo = formBean.getStrOfflineTariffUnit()[i]
							.replace("^", "#").split("#");

					strUnitBaseValue[i] = strUnitCombo[1];

				}
			}

			/** *****Not in Use Plz Check It********** */
			// vo.setStrDefaultQtyType(BillConfigUtil.DEFAULT_UNIT_ID);
			// vo.setStrRate(BillConfigUtil.DEFAULT_UNIT_ID);
			/** ************************************** */

			// vo.setStrDefaultUnit(BillConfigUtil.DEFAULT_UNIT_ID);
			
			vo.setStrWardType(formBean.getStrWardType());
			
			vo.setStrTotalServiceTax(formBean.getStrTotalServiceTax());
			vo.setStrTariffDiscountAmtConfgDtlBillApproval(formBean
					.getStrTariffDiscountAmtConfgDtlBillApproval());
			vo.setStrTariffId(strTariffId);
			vo.setStrGrpId(strGrpId);
			vo.setStrTariffRate(strTariffRate);
			vo.setStrBillQty(strBillQty);
			vo.setStrServTax(strServTax);
			vo.setStrTariffActCost(strTariffActCost);
			vo.setStrIsPackg(strIsPackaged);
			vo.setStrUnitBaseValue(strUnitBaseValue);
			vo.setStrRateBaseValue(strRateBaseValue);
			vo.setStrTariffActualCost(strTariffActualCost);
			vo.setStrServTaxAmt(strServTaxAmt);
			vo.setStrUnitIDVal(strUnitId);
			vo.setStrServiceId(strServiceId);
			vo.setStrGlblTariffId(strGlblTariffId);

			vo.setStrTariffNetAmt(strTariffAmtValue);
			vo.setStrIsRefundable(strIsRefundable);

			vo.setStrNetPaybleAmt(formBean.getStrNetPaybleAmt());

			vo.setStrOfflineDiscountApprovedByDetails(formBean
					.getStrOffLineTariffDiscountBy());
			vo.setStrOffLineTariffDiscountUnit(formBean
					.getStrOffLineTariffDiscountUnit());
			vo.setStrOffLineTariffDiscountType(formBean
					.getStrOffLineTariffDiscountType());
			vo.setStrOfflineTariffQty(formBean.getStrOfflineTariffQty());

			vo.setStrOffLineTariffDiscountReasonText(formBean
					.getStrOffLineTariffDiscountReasonText());

			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			// String strChk = request.getParameter("strChkVal");

			String strChk = request.getParameter("strChk");

			String[] strTemp = strChk.replace("@", "#").split("#");

			vo.setStrPatAcctNo(strTemp[0]);
			vo.setStrPukNo(strTemp[1]);
			//vo.setStrPatientCatCode(formBean.getStrNewTreatmentCategory());COMMENTED BY AMIT ATERIA BECAUSE strNewTreatmentCategory COMBO ON JSP BECOMES HIDE
			vo.setStrPatientCatCode(formBean.getStrTreatmentCategory());// NEW LOGIC -PICK VALUE(strTreatmentCategory) FROM ACCOUNT DETAILS DIV
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrChargeTypeID(strTemp[4]);
			vo.setStrAddmissionNo(strTemp[5]);

			vo.setStrOfflineTariffNetAmount(formBean.getStrOfflineTariffNetAmount());
			vo.setStrOfflineActualTariffAmtVal(formBean.getStrOfflineActualTariffAmtVal());
			vo.setStrOfflineTariffServiceTaxAmtVal(formBean.getStrOfflineTariffServiceTaxAmtVal());

			String[] strTemp22 = strTemp[8].replace("^", "#").split("#");

			vo.setStrEpisodeNo(strTemp22[0].replace("$", "#").split("#")[0]);

			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

			if (request.getSession().getAttribute("USER_LEVEL") != null) 
			{
				vo.setStrUserLevel(request.getSession().getAttribute("USER_LEVEL").toString());
			} 
			else 
			{
				vo.setStrUserLevel("1");
			}

			
			if(new BillConfigUtil(formBean.getStrHospitalCode()).getIpdGenAdtProcessType().equals("1"))//ONLINE ADT
			{				
				String strDeptWardCombo = request.getParameter("strChkBoxComboValue");//PICK VALUE FROM IPD BILL MGMT DESK COMBO DEPT & WARD
				String[] strDeptWrd = strDeptWardCombo.replace("^", "#").split("#");		
				vo.setStrDeptId(strDeptWrd[0]);
				vo.setStrWardCode(strDeptWrd[1]);
				vo.setStrIpdChgTypeId(strDeptWrd[2]);				
				vo.setStrTreatmentCategory(formBean.getStrTreatmentCategory());
			}
			else
			{
				//strAdmissionDtlHidVal=ADMNO^EPCODE^VISITNO^ADMDATETIME^ADMADVNO^DEPTCODE^WARDCODE^ROOMCODE^BEDCODE^TREATCATCODE^ISNEWBORN^MOTHERADMNO^MLCNO^OCCUPID^BEDALLOCDATETIME^LENGTHOFSTAY
				if(formBean.getStrAdmissionDtlHidVal() != null && formBean.getStrAdmissionDtlHidVal().length() > 0)
				{
					String[] temp = formBean.getStrAdmissionDtlHidVal().replace("^", "#").split("#");				
					if(temp.length > 6)
					{				
						vo.setStrDeptId(temp[5].substring(0, 3));
						vo.setStrWardCode(temp[6]);					
						vo.setStrIpdChgTypeId(vo.getStrWardType().replace("^", "#").split("#")[0]);					
					}
					else
					{					
						vo.setStrDeptId("1");
						vo.setStrWardCode(vo.getStrWardType().replace("^", "#").split("#")[0]);
						vo.setStrIpdChgTypeId(vo.getStrWardType().replace("^", "#").split("#")[0]);					
					}				
				}
				else
				{					
					vo.setStrDeptId("1");
					vo.setStrWardCode(vo.getStrWardType().replace("^", "#").split("#")[0]);
					vo.setStrIpdChgTypeId(vo.getStrWardType().replace("^", "#").split("#")[0]);					
				}	
				
				//vo.setStrTreatmentCategory(formBean.getStrNewTreatmentCategory());COMMENTED BY AMIT ATERIA BECAUSE strNewTreatmentCategory COMBO ON JSP BECOMES HIDE
				vo.setStrTreatmentCategory(formBean.getStrTreatmentCategory());// NEW LOGIC -PICK VALUE(strTreatmentCategory) FROM ACCOUNT DETAILS DIV
		
			} 
			 
			vo.setStrDefaultUnit(BillConfigUtil.DEFAULT_UNIT_ID);

			//formBean.getPrimaryCategoryCode() SET FROM JS FUNCTION
			if(formBean.getPrimaryCategoryCode().equals(new BillConfigUtil(formBean.getStrHospitalCode()).getIpdFreeCategory()))
			{
				vo.setStrPoorFreeWelfareAmt(formBean.getStrPoorFreeWelfareAmt());
				vo.setStrPoorFreeGrantAmt(formBean.getStrPoorFreeGrantAmt());
				vo.setStrPrimaryCategory(formBean.getPrimaryCategoryCode());				
				float expence = Float.parseFloat(formBean.getTotalRecAmtDivId1());
				float poorGrant = Float.parseFloat(formBean.getStrPoorFreeGrantAmt());
				float poorWelfare = Float.parseFloat(formBean.getStrPoorFreeWelfareAmt());				
				float total = expence + poorGrant + poorWelfare;				
				vo.setTotalRecAmtDivId1(HisUtil.getAmountWithDecimal(total, 2));
						
			}
			bo.insertBillApproval(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			} 
			else 
			{
				strMsg = "Record saved successfully!";
				vo.setStrMsg(strMsg);
			}
		} 
		catch (Exception e) 
		{
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->InserBillApproval()",strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{
			vo = null;
			bo = null;
			billUtil = null;
		}

	}

	/**
	 * ********************INSERT STATEMENT Client
	 * Approval*****************************
	 */
	/**
	 * InsertRecord(formBean) -- > Insert Logic for Client Approval Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static boolean InsertRecord(IpdBillManagementTransFB formBean) {

		// Declaring Variables
		boolean fretValue = true;
		// String strErrmsg = "";
		String strmsgText = "";
		String strMsg = "";
		String strWarning = "";
		// Creating VO & BO Object
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;

		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			// Set value into VO
			vo.setStrAccountNo(formBean.getStrAccountNo());
			vo.setStrCltAppSancAmt(formBean.getStrCltAppSancAmt());
			vo.setStrCltApprBalanceAmt(formBean.getStrCltApprBalanceAmt());
			vo.setStrClientPatNo(formBean.getStrClientPatNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			// Calling BO insert method
			fretValue = bo.insertPorcedure(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			if (fretValue) {
				strMsg = "Record saved successfully!";
				vo.setStrMsg(strMsg);
			} else {
				strWarning = "Record Not Saved!Data Already Exist!!";
				vo.setStrWarning(strWarning);
			}
		} catch (Exception e) {
			fretValue = false;
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->InserRecord()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;
			bo = null;
		}
		return fretValue;
	}

	/**
	 * *************************INSERT STATEMENT FOR PART PAYMENT
	 * COMBO*************************
	 */

	/**
	 * InsertRecordPartPayment(request,response,formBean) -- > Insert Logic for
	 * Part Payment Status Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */

	public static boolean InsertRecordPartPayment( HttpServletRequest request , 
			IpdBillManagementTransFB formBean ) {
		// Declare Variables
		// String strErrmsg = "";
		String strmsgText = "";
		boolean bRetVal = true;
		String strMsg = "";
		// String strWarning = "";
		// Create Object for BO & VO
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;

		// Set the Vale for Value Object
		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();

			vo.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
			vo.setStrUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

			vo.setStrChk(formBean.getStrChk());
			vo.setStrApprovalBy(formBean.getStrApprovedByCombo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

		    vo.setStrWardType(formBean.getStrWardType());
			vo.setStrSeatId(formBean.getStrSeatId());
			
			 
				if(new BillConfigUtil(formBean.getStrHospitalCode()).getIpdGenAdtProcessType().equals("1")){
				
				String strDeptWardCombo = request
				.getParameter("strChkBoxComboValue");

				String[] strDeptWrd = strDeptWardCombo.replace("^", "#").split("#");
		
				vo.setStrDeptCode(strDeptWrd[0]);
				vo.setStrWardCode(strDeptWrd[1]+"^"+strDeptWrd[2]);
				vo.setStrIpdChgTypeId(strDeptWrd[1]+"^"+strDeptWrd[2]);
				
				vo.setStrTreatmentCategory(formBean.getStrTreatmentCategory());
				
			}else{
				
			  

		vo.setStrDeptCode("1");
		vo.setStrWardCode(vo.getStrWardType());
		vo.setStrIpdChgTypeId(vo.getStrWardType());
		
		vo.setStrTreatmentCategory(formBean.getStrNewTreatmentCategory());
		
			} 
			
			
			vo.setStrRemarks(formBean.getStrRemarksCombo2());
			vo.setStrApprovalBy(formBean.getStrApprovedByCombo());
			vo.setStrPartPaymentAmt(formBean.getStrPartpayment());

			boolean fretValue = bo.insertPorcedureForPartPayment(vo); // Get
			// Return
			// Value
			// from
			// BO
			// insert
			// method
			if (fretValue) {
				strMsg = "Record saved successfully!";
				vo.setStrMsg(strMsg);
			} else {
				// Check Error
				if (vo.getStrMsgType().equals("1")) {
					throw new Exception(vo.getStrMsgString());
				}
			}
		} catch (Exception e) {
			bRetVal = false;
			strmsgText = e.getMessage();

			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->InserRecordForPartPayment()",
					strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			System.out.println("=======================>>>>>>>>>>>>>>>> inside catch");
			eObj = null;
		} finally {

			vo = null;
			bo = null;
		}
		return bRetVal;
	}

	/**
	 * ********************INSERT STATEMENT FOR UPDATE ACCOUNT
	 * STATUS************************
	 */

	/**
	 * InsertRecordUpDateAcctStatus(formBean) -- > Insert Logic for
	 * UpdateAccountStatus Page
	 * 
	 * @param form
	 */

	public static boolean InsertRecordUpDateAcctStatus(
			IpdBillManagementTransFB formBean) {
		// Declare Variables
		boolean fretValue = false;
		// String strErrmsg = "";
		String strmsgText = "";
		String strMsg = "";
		String strWarning = "";

		// Create Object for VO & BO
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;

		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			// Set the Value for VO

			vo.setStrChk(formBean.getStrChk());
			vo.setStrApprovalBy(formBean.getStrApprovedByCombo());
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrRemarks(formBean.getStrRemarksCombo2());
			vo.setStrPresentAcctStatus(formBean.getStrPresentAcctStatus());
			//vo.setStrPAcctStatus(formBean.getCombo()[0]);
			vo.setStrNewAcctStatus(formBean.getStrNewAcctStatus());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			fretValue = bo.insertPorcedureForUpdateAcctStatus(vo);
			if (fretValue) {
				strMsg = "Record saved successfully!";
				vo.setStrMsg(strMsg);
			} else {
				strWarning = "Record Not Saved!Data Already Exist!!";
				vo.setStrWarning(strWarning);
			}
			// Check Error
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->InserRecordForPartPayment()",
					strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;
			bo = null;
		}
		return fretValue;
	}

	/**
	 * ***************************Ajax Function for View
	 * Charges(Combo)**********************************
	 */
	/**
	 * UNITVAL(request,response,formBean) -- > This Method generate the Ajax
	 * Response for View Charges Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static void UNITVAL(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean) {
		// Declaring Variables
		String strTariffCombo = "";
		String strmsgText = "";
		String strChk = null;
		String strRes = null;

		// Creating Object for BO & VO.
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;
		HisUtil hisutil = null;
		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			strChk = request.getParameter("modName");

			vo.setStrGroupId(strChk);
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			// Call BO TariffCombo Method
			bo.getTariffCombo(vo);
			// TariffName Combo
			if (vo.getStrMsgType().equals("0")) {
				if (vo.getStrTariffNameCombo() != null
						&& vo.getStrTariffNameCombo().size() > 0) {
					strTariffCombo = hisutil.getOptionValue(vo
							.getStrTariffNameCombo(), "0", "0^Select value",
							true);
					response.setHeader("Cache-Control", "no-cache");
					strRes = strTariffCombo + "#";
					response.getWriter().print(strRes);
				} else {
					response.setHeader("Cache-Control", "no-cache");
					String str = "<option value='0' >Select Value</option>";
					response.getWriter().print(str);
				}
			} else {
				HisException eObj = new HisException("Billing",
						"IpdBillManagementTransBSDATA->UNITVAL()", vo
								.getStrMsgString());
				String str = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator! ";
				response.getWriter().print(str);
			}
		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->UNITVAL()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			hisutil = null;

			bo = null;
		}

	}

	/**
	 * *************************Ajax Function for View
	 * Charges(Combo)***************************
	 */

	/**
	 * UNITVAL1(request,response,formBean) -- > This Method generate the Ajax
	 * Response for View Charges Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */

	public static void UNITVAL1(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean) {
		// Declaring Variables
		String strGroupNameCombo = "";
		String strmsgText = "";
		String strChk = null;
		String strRes = null;
		// Creating Object for BO & VO.
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;
		HisUtil hisutil = null;
		// Set Value in VO

		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			strChk = request.getParameter("modName");
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			vo.setStrChargeTypeID(strChk);
			// Call BO Group Combo Method
			bo.getGroupCombo(vo);
			// Get Group Name Combo
			// if(vo.getStrGroupNameCombo()!=null)
			// {
			// strGroupNameCombo =
			// hisutil.getOptionValue(vo.getStrGroupNameCombo(), "0", "0^Select
			// value", true);
			// response.setHeader("Cache-Control", "no-cache");
			// strRes = strGroupNameCombo + "#";
			// response.getWriter().print(strRes);
			//				
			// }
			// else
			// {
			// formBean.setStrMsgString("err");
			// HisException eObj = new HisException("Billing",
			// "IpdBillManagementTransBSDATA->UNITVAL1()", strmsgText);
			// response.setHeader("Cache-Control", "no-cache");
			// strRes = strGroupNameCombo + "#" + "ERROR####Application Error
			// [ERROR ID : " + eObj.getErrorID() + "], Contact System
			// Administrator! ";
			// response.getWriter().print(strRes);
			// eObj = null;
			// }

			if (vo.getStrMsgType().equals("0")) {
				if (vo.getStrGroupNameCombo() != null
						&& vo.getStrGroupNameCombo().size() > 0) {
					strGroupNameCombo = hisutil.getOptionValue(vo
							.getStrGroupNameCombo(), "0", "0^Select value",
							true);
					response.setHeader("Cache-Control", "no-cache");
					strRes = strGroupNameCombo + "#";
					response.getWriter().print(strRes);
				} else {
					response.setHeader("Cache-Control", "no-cache");
					String str = "<option value='0'>Select Value</option>";
					response.getWriter().print(str);
				}
			} else {
				HisException eObj = new HisException("Billing",
						"IpdBillManagementTransBSDATA->UNITVAL1()", vo
								.getStrMsgString());
				String str = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator! ";
				response.getWriter().print(str);
			}

		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->UNITVAL1()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			hisutil = null;

			bo = null;

		}

	}

	/**
	 * *************************AJAX FUNCTION for View
	 * Bill****************************
	 */

	/**
	 * UNITVAL12(request,response,formBean) -- > This Method generate the Ajax
	 * Response for View Bill Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static void UNITVAL12(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean) {
		// Declaring Variables
		String strData = null;
		String strTariffDtl = "";
		String strTariffDtl1 = "";
		String strmsgText = "";
		String[] data = null;
		// String strRes = null;

		// Creating Object for BO & VO.
		IpdBillManagementTransVO vo = null;

		try {

			vo = new IpdBillManagementTransVO();
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			// Here we Split Data
			strData = request.getParameter("modName");
			data = strData.split("\\^");
			// Calling TariffDtls
			strTariffDtl1 = HLPTariffDtl.getTariffDtl(data[0], data[1], vo
					.getStrHospitalCode());
			String[] TestData = strTariffDtl1.split("\\####");
			response.setHeader("Cache-Control", "no-cache");
			if (TestData[0].equals("ERROR")) {
				throw new Exception(TestData[1]);
			} else {
				response.setHeader("Cache-Control", "no-cache");
				strTariffDtl = TestData[0] + '@' + data[2];
				response.getWriter().print(strTariffDtl);
			}

		} catch (Exception e) {
			strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"IpdBillManagementTransBSDATA->UNITVAL12()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {

				new HisException("Billing",
						"IpdBillManagementTransBSDATA->UNITVAL12()", strmsgText
								+ "-->" + e1.getMessage());

			}

		} finally {

			vo = null;

		}
	}

	/**
	 * ****************************Ajax Function For Bill-Approval(Process &
	 * Un-Process)****************************
	 */

	/**
	 * UNITVAL13(request,response,formBean) -- > This Method generate the Ajax
	 * Response for Bill-Approval(Process & Un-Process) Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */

	public static void UNITVAL13(HttpServletRequest request,HttpServletResponse response) 
	{
		String strTariffDtl = "";
		String strTariffDtl1 = "";
		String strmsgText = "";
		String[] data = null;
		String strData = request.getParameter("modName");

		IpdBillManagementTransVO vo = null;

		try 
		{
			vo = new IpdBillManagementTransVO();
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			data = strData.split("\\^");
			strTariffDtl1 = HLPGrpDtl.getProcessUnProcessDtlBS(data[0], data[1],data[2], vo.getStrHospitalCode());
			String[] TestData = strTariffDtl1.split("\\####");
			response.setHeader("Cache-Control", "no-cache");
			if (TestData[0].equals("ERROR")) 
			{
				throw new Exception(TestData[1]);
			} 
			else 
			{
				response.setHeader("Cache-Control", "no-cache");
				strTariffDtl = TestData[0] + '@' + data[2];
				response.getWriter().print(strTariffDtl);
			}
		} 
		catch (Exception e) 
		{
			strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->UNITVAL13()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} 
			catch (IOException e1) 
			{
				// System.out.println("Inside IInd Else::::"+e1.getMessage());
			}
		} 
		finally 
		{
			vo = null;
		}

	}

	/**
	 * ************************Method FUNCVIEW for View
	 * Charges*****************************
	 */

	/**
	 * UNITVAL(request,response,formBean) -- > This Method generate the Ajax
	 * Response for View Charges Page when click generate page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */

	public static void FUNCVIEW(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean) {
		// Declaring Variables
		String strmsgText = "";
		String strPkgBreakUp = "";
		String strRes = "";
		WebRowSet ws = null;
		String strChk = request.getParameter("modName");

		// Creating Object for BO & VO.
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;

		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();

			// System.out.println("strChk-->>>"+strChk);
			vo.setStrGroupId(strChk);
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			// Call BO GetViewFunc Method
			bo.getViewFunc(vo);
			if (vo.getChkgValue().equals("1")) {
				vo.setStrPkgBrkUpWs(ws);
			}

			if (vo.getStrMsgType().equals("0") || vo.getStrPkgBrkUpWs() != null) {
				strPkgBreakUp = HLPPkgBreakUp.getPkgBreakUp(vo
						.getStrPkgBrkUpWs(), vo.getStrRetValue());
				String[] TestData = strPkgBreakUp.split("\\####");
				response.setHeader("Cache-Control", "no-cache");
				if (TestData[0].equals("ERROR")) {
					throw new Exception(TestData[1]);
				} else {
					response.setHeader("Cache-Control", "no-cache");
					formBean.setStrPkgBreakUp(TestData[0]);
					strRes = TestData[0] + "@";
					response.getWriter().print(strRes);
				}

			} else {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {

			strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"IpdBillManagementTransBSDATA->FUNCVIEW()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				// System.out.println("Inside IInd Else::::"+e1.getMessage());
			}
		} finally {

			vo = null;
			bo = null;

		}

	}

	/**
	 * ********************************AJAX
	 * FUNCTION*********************************
	 */

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
			HttpServletResponse response, IpdBillManagementTransFB formBean) {
		// Declaring Variables
		String strmsgText = "";
		String strRes = "";
	  
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;
		HisUtil hisutil = null;

		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
						 
			String strGroupCode = request.getParameter("groupId");
			String strHospitalService = request.getParameter("hService");
			String strTreatmentCategory = request.getParameter("treatmentCat");
			String strWardCode = request.getParameter("ward").replace("^", "#").split("#")[0];
			String strSearchLetter = request.getParameter("searchLetter");

			if (strGroupCode == null)
				strGroupCode = "0";
			if (strHospitalService == null)
				strHospitalService = "0";
			if (strTreatmentCategory == null)
				strTreatmentCategory = "0";
			if (strWardCode == null || !strHospitalService.equals("2"))
				strWardCode = "0";

			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrGroupId(strGroupCode);
			vo.setStrChargeTypeID(strHospitalService);
			vo.setStrNewTreatmentCategory(strTreatmentCategory);
			vo.setStrOffLineWard(strWardCode);
			vo.setStrSearchLetter(strSearchLetter);
			// Bo Method Calling
			bo.TariffCombo(vo);
			formBean.setStrOfflineTariffDetails(strRes);

			if (vo.getOfflineTariffList() != null) {
				strRes = hisutil.getDropDown(vo.getOfflineTariffList(), 1, 4,
						true);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strRes);
			} else {
				formBean.setStrMsgString("err");
			}

			/*
			 * if(vo.getOfflineTariffList()!=null) { strRes1 =
			 * hisutil.getDropDown(vo.getOfflineTariffList(), 1, 4, true);
			 * response.setHeader("Cache-Control", "no-cache"); strRes = strRes1 +
			 * "#"; response.getWriter().print(strRes); } else {
			 * formBean.setStrMsgString("err"); HisException eObj = new
			 * HisException("Billing", "IpdBillManagementTransBSDATA->UNITVAL1()",
			 * strmsgText); response.setHeader("Cache-Control", "no-cache");
			 * strRes = strGroupNameCombo + "#" + "ERROR####Application Error
			 * [ERROR ID : " + eObj.getErrorID() + "], Contact System
			 * Administrator! "; response.getWriter().print(strRes); eObj =
			 * null; }
			 */

		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->TARIFFDTLS()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
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
	public static void getTariffCodeDetails(HttpServletRequest request,HttpServletResponse response) 
	{
		IpdBillManagementTransBO bo = null;
		IpdBillManagementTransVO voObj = null;
		
		try 
		{
			bo = new IpdBillManagementTransBO();
			voObj = new IpdBillManagementTransVO();
			
			String strGroupCode = request.getParameter("groupId");
			String strHospitalService = request.getParameter("hService");
			String strTreatmentCategory = request.getParameter("treatmentCat");
			String strWardCode = request.getParameter("ward").replace("^", "#").split("#")[0];
			String strTariffCode = request.getParameter("tariffCode");

			if (strGroupCode == null)
				strGroupCode = "0";
			if (strHospitalService == null)
				strHospitalService = "0";
			if (strTreatmentCategory == null)
				strTreatmentCategory = "0";
			if (strWardCode == null || !strHospitalService.equals("2"))
				strWardCode = "0";

			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			voObj.setStrGroupId(strGroupCode);
			voObj.setStrChargeTypeID(strHospitalService);
			voObj.setStrNewTreatmentCategory(strTreatmentCategory);
			voObj.setStrOffLineWard(strWardCode);
			voObj.setStrTariffCode(strTariffCode);
			
			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			bo.getTariffCodeDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
			String temp = "";

			if (voObj.getOfflineTariffList().size() != 0) 
			{				
				if(voObj.getOfflineTariffList().next())
				{					
					temp =  "1@@"+voObj.getOfflineTariffList().getString(2)+"@@"+voObj.getOfflineTariffList().getString(1);
				}							
			}
			else
			{				
					temp = "0@@_@@0";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Billing","CashCollectionTransDATA->getTariffCodeDetails()",strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} 
			catch (IOException e1) 
			{
				new HisException("Billing","IpdBillManagementTransBSDATA->getTariffCodeDetails()", e1.getMessage());
			}
		} 
		finally 
		{
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
	public static void getDefaultTariffDetails(HttpServletRequest request,HttpServletResponse response) 
	{
		IpdBillManagementTransBO bo = null;
		IpdBillManagementTransVO voObj = null;
		
		try 
		{
			bo = new IpdBillManagementTransBO();
			voObj = new IpdBillManagementTransVO();
			
			String strIpdChargeTypeId = request.getParameter("ipdCharge");
			String strHospitalService = request.getParameter("hService");
			String strTreatmentCategory = request.getParameter("treatmentCat");
			String strWardCode = request.getParameter("ward").replace("^", "#").split("#")[0];
			String strStartDate = request.getParameter("startDate");
			String strEndDate = request.getParameter("endDate");
		//	String strWardType = request.getParameter("wardType");

			if (strIpdChargeTypeId == null)
				strIpdChargeTypeId = "0";
			if (strHospitalService == null)
				strHospitalService = "0";
			if (strTreatmentCategory == null)
				strTreatmentCategory = "0";
			if (strWardCode == null || !strHospitalService.equals("2"))
				strWardCode = "0";
					
			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			voObj.setStrIpdChgTypeId(strIpdChargeTypeId.replace("^", "#").split("#")[0]);
			voObj.setStrChargeTypeID(strHospitalService);
			voObj.setStrNewTreatmentCategory(strTreatmentCategory);
			voObj.setStrOffLineWard(strWardCode);
			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			bo.getDefaultTariffDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}			
			String temp = IpdBillManagementTransHLPNew.getCompulsaryChargesView(voObj.getOfflineTariffList() , strStartDate , strEndDate , voObj );
			
			bo.getSpecialTariffDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
						
			temp = temp+"@@@@"+IpdBillManagementTransHLPNew.getSpecialChargesView(voObj.getOfflineTariffList() , strStartDate , strEndDate , voObj );
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Billing","CashCollectionTransDATA->getTariffCodeDetails()",strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} 
			catch (IOException e1) 
			{
				new HisException("Billing","IpdBillManagementTransBSDATA->getTariffCodeDetails()", e1.getMessage());
			}
		} 
		finally 
		{
			bo = null;
			voObj = null;
		}
	}
	
	

	/**
	 * ********************************AJAX
	 * FUNCTION**********************************
	 */

	/**
	 * TRFUNIT(request,response,formBean) -- > This Method generate the Ajax
	 * Response
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */

	public static void TRFUNIT(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean) {
		// Declaring Variables
		String strData = request.getParameter("modName");
		String strmsgText = "";
		// String strRes ="";
		String strOfflineTariffUnit1 = "";
		String strOfflineTariffUnit = "";
		String[] strHidData = null;

		// Creating Object for BO & VO.
		HisUtil hisutil = null;
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;

		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");

			// Split the Data
			strHidData = strData.split("\\^");
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			vo.setStrOffLineTariffUnitTempId(strHidData[0]);
			//bo.TariffUnit(vo);

			// Tariff Name Combo
			/*if (vo.getOfflineTariffUnit() != null
					&& vo.getOfflineTariffUnit().size() > 0) {
				strOfflineTariffUnit1 = hisutil.getOptionValue(vo
						.getOfflineTariffUnit(), "", "", true);
				strOfflineTariffUnit = strHidData[2] + '@'
						+ strOfflineTariffUnit1;
				// Set Value in Response
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strOfflineTariffUnit);
			} else {
				formBean.setStrMsgString("err");
			}*/
			strOfflineTariffUnit1=BillConfigUtil.getDefaultUnitComboWithBaseValue(vo.getStrOffLineTariffUnitTempId()+"^1");
			strOfflineTariffUnit = strHidData[2] + '@'
					+ strOfflineTariffUnit1;
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strOfflineTariffUnit);
			
		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->TRFUNIT()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			hisutil = null;

			bo = null;

		}
	}

	/**
	 * ***************************Drop Down TariffCombo For(Bill Approval + Add
	 * Service)****************************
	 */
	/**
	 * DropTariffCombo(request,response,formBean) -- > This Method generate the
	 * data for Drop Down Utility for ( Bill Approval + Add Service) Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static boolean DropTariffCombo(HttpServletRequest request,
			HttpServletResponse response, IpdBillManagementTransFB formBean,
			String strChk2) {
		// Declaring Variables

		String strmsgText = "";
		boolean bRetVal = true;
		String strOfflineGroupDetails1 = "";
		String strOfflinePackageGroup1 = "";
	//	String strOfflineTarffList1 = "";
		String strOfflineDiscountRemarks1 = "";
		String strOfflineDiscountApprovedByDetails = "";
		String strTemp[] = null;
		String strTemp1[] = { "0" };
		String strTemp2[] = null;
		String strWardCode = null;
		// Creating Object fro BO & VO
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;
		HisUtil hisutil = null;
		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			//formBean.setStrComboValue(request.getParameterValues("combo"));
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrComboValue(formBean.getStrComboValue());
			if (formBean.getStrComboValue().length == 3) {
				strTemp1 = formBean.getStrComboValue();
				vo.setStrWardCode(strTemp1[2]);
				vo.setStrOffLineWard(strTemp1[2]);
				strTemp = strChk2.replace("@", "#").split("#");
				vo.setStrOffLineTreatmentCategory(strTemp[3]);
				vo.setStrChargeTypeID(strTemp[4]);
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
				vo.setStrOffLineWard(strTemp1[2]);
				strWardCode = strTemp1[2];
			} else {
				String s1[] = request.getParameterValues("strComboVal");
				strTemp2 = s1[0].replace("\\^", "#").split("#");
				vo.setStrWardCode(strTemp2[1]);
				vo.setStrOffLineWard(strTemp2[1]);
				strWardCode = strTemp2[1];
				strTemp = strChk2.replace("@", "#").split("#");
				vo.setStrOffLineTreatmentCategory(strTemp[3]);
				vo.setStrChargeTypeID(strTemp[4]);
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
			}
			vo.setStrGroupId("0"); // Hard Coded Value
			String strHidden = strTemp[4] + "^" + strTemp[3] + "^"
					+ formBean.getStrHospitalCode() + "^" + strWardCode;
			formBean.setStrHidden(strHidden);
			// Call BO method
			bo.GrpOfTariffCombo(vo);
			// Group Combo
			if (vo.getOfflineGroupList() != null
					&& vo.getOfflineGroupList().size() > 0) {
				strOfflineGroupDetails1 = hisutil.getOptionValue(vo
						.getStrGroupNameCombo(), "0", "0^Select value", false);
				formBean.setStrOfflineGroupDetails(strOfflineGroupDetails1);

			}

			else {
				String str = "<option value='0' style='bgcolor:blue'>DATA N/A</option>";
				formBean.setStrOfflineGroupDetails(str);
				// Set Messages into form bean
				formBean.setStrErrMsg(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}
			}
			if (vo.getOfflinePackageGroup() != null
					&& vo.getOfflinePackageGroup().size() > 0) {

				strOfflinePackageGroup1 = hisutil.getOptionValue(vo
						.getOfflinePackageGroup(), "0", "0^Select All Value",
						false);
				formBean.setStrOfflineGroupDetails(strOfflinePackageGroup1);
			}

			else {
				String str = "<option value='0' style='bgcolor:blue'>DATA N/A</option>";
				formBean.setStrOfflineGroupDetails(str);
				// Set Messages into form bean
				formBean.setStrErrMsg(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}

			}

			if (vo.getOfflineDiscountRemarks() != null
					&& vo.getOfflineDiscountRemarks().size() > 0) {

				strOfflineDiscountRemarks1 = hisutil.getOptionValue(vo
						.getOfflineDiscountRemarks(), "", "", true);
				formBean
						.setStrOfflineDiscountRemarksDetails(strOfflineDiscountRemarks1
								+ "<option value='0'>Others </option>");

			}

			else {
				// Set Messages into form bean
				formBean.setStrErrMsg(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}

			}

			if (vo.getOfflineDiscountApprovedBy() != null
					&& vo.getOfflineDiscountApprovedBy().size() > 0) {
				strOfflineDiscountApprovedByDetails = hisutil.getOptionValue(vo
						.getOfflineDiscountApprovedBy(), "0", "0^Select Value",
						true);
				formBean
						.setStrOfflineDiscountApprovedByDetails(strOfflineDiscountApprovedByDetails);

			}

			else {
				// Set Messages into form bean
				formBean.setStrErrMsg(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}

			}

		/*	if (vo.getOfflineTariffList() != null) {
				strOfflineTarffList1 = hisutil.getDropDown(vo
						.getOfflineTariffList(), 1, 5, true);
				formBean.setStrOfflineTariffDetails(strOfflineTarffList1);
				// System.out.println("strOfflineTarffList1 4 DropDown
				// 1-->"+strOfflineTarffList1);

			} else {
				formBean.setStrErrMsg(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}
			}*/

		} catch (Exception e) {

			e.printStackTrace();
			
			bRetVal = false;
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->DropTariffCombo()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			hisutil = null;

			bo = null;

		}
		return bRetVal;
	}

	/**
	 * ************************Part Payment
	 * Combo*********************************
	 */
	/**
	 * PartPaymentAmtCombo(request,response,formBean) -- > This Method generate
	 * the Part Payment Combo for Part Payment Request Page
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static void PartPaymentAmtCombo(HttpServletRequest request,
			HttpServletResponse response) {
		 
	 
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;
		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
		  
			
			String strHospCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String strTreatmentCategory = request.getParameter("treatmentCat");
			String strWardCode = request.getParameter("ward").replace("^", "#").split("#")[0];
			
			vo.setStrHospitalCode(strHospCode);
			vo.setStrWardCode(strWardCode);
			vo.setStrPtCatCode(strTreatmentCategory);
			
			
			// Call BO PartPayment Combo Method
			bo.PartPaymentCombo(vo);
		  
			String temp = "0";
			
			if (vo.getStrPartPaymentWs() != null) {
 
				if (vo.getStrPartPaymentWs().size() > 0) {
					
					WebRowSet ws = vo.getStrPartPaymentWs();
					if (ws.next()) {
						
						temp = ws.getString(1);
					}
					
				}  
			}  
			
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"CashCollectionTransDATA->PartPaymentAmtCombo()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"IpdBillManagementTransBSDATA->PartPaymentAmtCombo()", e1
								.getMessage());
			} finally {

			vo = null;

			bo = null;

		}
		 
	}

	}
	public static void getParticularDtlsView(HttpServletRequest request,HttpServletResponse response, IpdBillManagementTransFB formBean) 
	{
		try 
		{
			String strHospCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String strAccNo = request.getParameter("accNo");
			String strGrpId = request.getParameter("grpId");

			String strView = HLPTariffDtl.getTariffDtl(strGrpId, strAccNo,strHospCode);
			String[] TestData = strView.split("###");

			response.setHeader("Cache-Control", "no-cache");
			if (TestData[0].equals("ERROR")) 
			{
				throw new Exception(TestData[1]);
			} 
			else 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strView);
			}
		} 
		catch (Exception e) 
		{
			String strMsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->UNITVAL12()", strMsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} 
			catch (IOException e1) 
			{
				new HisException("Billing","IpdBillManagementTransBSDATA->UNITVAL12()", strMsgText);
			}
		} 
		finally 
		{
		}
	}

	public static void showReport(IpdBillManagementTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		String reportFormat = "html";
		String strmsgText = "";
		String strTemp[] = null;

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			String s1 = request.getParameter("strVal");
			String[] strWard = s1.replace("^", "#").split("#");
			String strChk = request.getParameter("strChk");
			strTemp = strChk.replace("@", "#").split("#");
			String strReportName = "Provisional Final Adjustment Bill";
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strAccountNo = strTemp[0]; // Check It
			String strDeptCode = strWard[0]; // Check It
			String strWardCode = strWard[1]; // Check It
			reportFormat = formBean.getStrReportFormat();

			if (formBean.getStrBillTypeCombo().equals("1")) {

				String reportPath = "/billing/reports/provisionalFinalAdjustmentBill1New_billrpt.rptdesign";

				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);
				params.put("hosp_Code", strHospitalCode);

				params.put("acc_No", strAccountNo);
				params.put("bill_No", "null");
				params.put("dept_Code", strDeptCode);
				params.put("ward_Code", strWardCode);

				ts.displayReport(request, response, reportPath, reportFormat,
						params,strHospitalCode);

			} else if (formBean.getStrBillTypeCombo().equals("2")) {

				String reportPath = "/billing/reports/provisionalFinalAdjustmentBill2New_billrpt.rptdesign";

				params.put("hosp_Code", strHospitalCode);
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);

				params.put("acc_No", strAccountNo);
				params.put("bill_No", "null");
				params.put("dept_Code", strDeptCode);
				params.put("ward_Code", strWardCode);

				ts.displayReport(request, response, reportPath, reportFormat,
						params,strHospitalCode);
			}

		} catch (Exception e) {

			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->showReport()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],"
					+ "Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		}
	}
	
	public static void viewReport(IpdBillManagementTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		String reportFormat = "html";
		String strmsgText = "";
		String strTemp[] = null;
		IpdBillManagementTransBO bo = null;
		Map<String, Object> params = new HashMap<String, Object>();
		IpdBillManagementTransVO vo = null;
		String strReopen="";
			
		try {
			vo = new IpdBillManagementTransVO();
			/*String s1 = request.getParameter("strVal");
			String[] strWard = s1.replace("^", "#").split("#");
			String strChk = request.getParameter("strChk");
			strTemp = strChk.replace("@", "#").split("#");
			String strReportName = "Provisional Final Adjustment Bill";
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strAccountNo = strTemp[0]; // Check It
			String strDeptCode = strWard[0]; // Check It
			String strWardCode = strWard[1]; // Check It
			reportFormat = formBean.getStrReportFormat();*/
			
			String strDeptCode=request.getParameter("deptCode");
		    String strWardCode=request.getParameter("wardCode");
			//String strAccountNo=request.getParameter("accNo");
			String strChk = request.getParameter("chk");
			
			String strRptMode = "0";
			
			if(request.getParameter("rptMode") != null){
				
				strRptMode = request.getParameter("rptMode");
				
			}
			
			strTemp = strChk.replace("@", "#").split("#");
			String strAccountNo = strTemp[0];
			
			
			String strBillNo="----";
			if((request.getParameter("billNo"))!=null)
			{
			 strBillNo=request.getParameter("billNo");
			}
			String strHospitalCode=ControllerUTIL.getUserVO(request).getHospitalCode();
			String strReportName = "Provisional Final Adjustment Bill";
			vo.setStrAccountNo(strAccountNo);
			vo.setStrHospitalCode(strHospitalCode);
			
			strReopen=IpdBillManagementTransBO.getBillReopenFlag(vo);
			
			if(strRptMode.equals("1")){
			//	System.out.println("strRptMode"+strRptMode);
				params.put("modeval", "39");
			}else{
			//	System.out.println("strRptMode"+strRptMode);
			//	System.out.println("strReopen"+strReopen);
				
				if( strReopen.equals("1"))
					params.put("modeval", "39");
				else
					params.put("modeval", "37");
				
				
			}
			 
			
				String reportPath = "/billing/reports/provisionalFinalAdjustmentBill3New_billrpt.rptdesign";
				params.put("hosp_Code", strHospitalCode);
				params.put("report_id", "1");
				params.put("report_Name", strReportName);

				params.put("acc_No", strAccountNo);
				params.put("bill_No",strBillNo);
				params.put("dept_Code", strDeptCode);
				params.put("ward_Code", strWardCode);

				ts.displayReport(request, response, reportPath, reportFormat,
						params,strHospitalCode);
		
		} catch (Exception e) {

			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->showReport()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],"
					+ "Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		}
	}

	public static void getTariffCodeDetailsNew(HttpServletRequest request,HttpServletResponse response) 
	{
		IpdBillManagementTransBO bo = null;
		IpdBillManagementTransVO voObj = null;
		
		try 
		{
			bo = new IpdBillManagementTransBO();
			voObj = new IpdBillManagementTransVO();
			
			String strGroupCode = request.getParameter("groupId");
			String strHospitalService = request.getParameter("hService");
			String strTreatmentCategory = request.getParameter("treatmentCat");
			String strWardCode = request.getParameter("ward").replace("^", "#").split("#")[0];
			String strTariffCode = request.getParameter("tariffCode");
			String strNumRows=request.getParameter("strNumRows");
			String strCurrindex=request.getParameter("strCurrindex");
			String wardcode = request.getParameter("wardCode");

			if (strGroupCode == null)
				strGroupCode = "0";
			if (strHospitalService == null)
				strHospitalService = "0";
			if (strTreatmentCategory == null)
				strTreatmentCategory = "0";
			if (strWardCode == null || !strHospitalService.equals("2"))
				strWardCode = "0";
			if (wardcode == null || !strHospitalService.equals("2"))
				wardcode = "0";

			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			voObj.setStrGroupId(strGroupCode);
			voObj.setStrChargeTypeID(strHospitalService);
			voObj.setStrNewTreatmentCategory(strTreatmentCategory);
			if(strGroupCode.equals("107"))
				voObj.setStrOffLineWard(wardcode);  // ward code is set in case of drugs
			else
				voObj.setStrOffLineWard(strWardCode); // ward id is set in case of normal tariffs
			//System.out.println("voObj.setStrOffLineWard"+voObj.getStrOffLineWard());
			voObj.setStrTariffCode(strTariffCode);
			voObj.setStrNumRows(strNumRows);
			voObj.setStrCurrindex(strCurrindex);
			
			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			bo.getTariffCodeDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
			
			String strTrfDtls=IpdBillManagementTransHLPNew.getTrfDtlsByCode(voObj);
			System.out.println("strTrfDtls"+strTrfDtls);
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strTrfDtls);
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->getTariffCodeDetailsNew()",strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} 
			catch (IOException e1) 
			{
				new HisException("Billing","IpdBillManagementTransBSDATA->getTariffCodeDetailsNew()", e1.getMessage());
			}
		} 
		finally 
		{
			bo = null;
			voObj = null;
		}
	}
	public static void getTariffCodeDetailsNewDrug(HttpServletRequest request,HttpServletResponse response) 
	{
		IpdBillManagementTransBO bo = null;
		IpdBillManagementTransVO voObj = null;
		
		try 
		{
			bo = new IpdBillManagementTransBO();
			voObj = new IpdBillManagementTransVO();
			
			String strGroupCode = request.getParameter("groupId");
			String strHospitalService = request.getParameter("hService");
			String strTreatmentCategory = request.getParameter("treatmentCat");
			String strWardCode = request.getParameter("ward").replace("^", "#").split("#")[0];
			String strTariffCode = request.getParameter("tariffCode");
			String strNumRows=request.getParameter("strNumRows");
			String strCurrindex=request.getParameter("strCurrindex");
			String wardcode = request.getParameter("wardCode");

			if (strGroupCode == null)
				strGroupCode = "0";
			if (strHospitalService == null)
				strHospitalService = "0";
			if (strTreatmentCategory == null)
				strTreatmentCategory = "0";
			if (strWardCode == null || !strHospitalService.equals("2"))
				strWardCode = "0";
			if (wardcode == null || !strHospitalService.equals("2"))
				wardcode = "0";

			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			voObj.setStrGroupId(strGroupCode);
			voObj.setStrChargeTypeID(strHospitalService);
			voObj.setStrNewTreatmentCategory(strTreatmentCategory);
			voObj.setStrOffLineWard(wardcode);
			//System.out.println("wardcode"+wardcode);
			voObj.setStrTariffCode(strTariffCode);
			voObj.setStrNumRows(strNumRows);
			voObj.setStrCurrindex(strCurrindex);
			
			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			bo.getTariffCodeDetailsDrug(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
			
			String strTrfDtls=IpdBillManagementTransHLPNew.getTrfDtlsByCode(voObj);
			System.out.println("strTrfDtls"+strTrfDtls);
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strTrfDtls);
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->getTariffCodeDetailsNew()",strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} 
			catch (IOException e1) 
			{
				new HisException("Billing","IpdBillManagementTransBSDATA->getTariffCodeDetailsNew()", e1.getMessage());
			}
		} 
		finally 
		{
			bo = null;
			voObj = null;
		}
	}
	
	public static void getWardList(HttpServletRequest request,HttpServletResponse response) 
	{
		String temp = "";
		try 
		{			 
			String strDept = request.getParameter("strDept");
			String strHospCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();			
			LinkedHashMap<String, String> mapProcedureParam = new LinkedHashMap<String, String>();
			
			mapProcedureParam.put("mode_val", "2");
			mapProcedureParam.put("dept_code", strDept);
			mapProcedureParam.put("hosp_code", strHospCode);
			mapProcedureParam.put("err", "#1");
			mapProcedureParam.put("resultset", "#2");
			
			temp = HisComboOptions.getOptionsFromProc("pkg_bill_view.proc_ward_list", mapProcedureParam, "0", "0^Select Value", false);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->getSpecialWardDtls()",strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} 
			catch (IOException e1) 
			{
				new HisException("Billing","IpdBillManagementTransBSDATA->getSpecialWardDtls()", e1.getMessage());
			}
		}
	}
	
	public static void initBedTransferDtl(HttpServletRequest request,HttpServletResponse response, IpdBillManagementTransFB formBean , String strMode) 
	{ 
		IpdBillManagementTransBO bo = null;
		IpdBillManagementTransVO vo = null;
		HisUtil hisutil = null;
		BillConfigUtil configUtil = null;
		try 
		{
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			configUtil = new BillConfigUtil(formBean.getStrHospitalCode());
			 
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrChargeTypeID(formBean.getStrChargeTypeID());
			vo.setStrAccountNo(formBean.getStrAccountNo());
			
			vo.setStrWardCode(formBean.getCombo()[2].replace("^", "#").split("#")[0]);
			vo.setStrWardType(formBean.getStrTempWardCode());
			formBean.setStrUrgSur(configUtil.getStrUrgTrfSur());
			
			bo.getAccountDtls(vo);
			
			formBean.setStrStartDate(vo.getStrStartDate());
			formBean.setStrEndDate(vo.getStrEndDate());	
			formBean.setStrAccStatus(vo.getStrAccStatus());
			/*System.out.println("new (new Date(formBean.getStrStartDate()))"+new SimpleDateFormat("dd-MM-yyyy").format(new Date(formBean.getStrStartDate())));
			System.out.println("new (new Date(formBean.getStrStartDate()))"+new SimpleDateFormat("dd-MM-yyyy").format(new Date(formBean.getStrEndDate())));*/
			formBean.setStrEndDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date(formBean.getStrEndDate())));
			formBean.setStrStartDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date(formBean.getStrStartDate())));
			//System.out.println("formBean.getStrStartDate()"+formBean.getStrStartDate());
			//System.out.println("formBean.getStrEndDate()"+formBean.getStrEndDate());
					
			bo.getTreatmentCatAndWardTypeValues(vo);
			
			if(vo.getStrMsgType().equals("1"))
			{				
				throw new Exception(vo.getStrMsgString());
			}
			
			String strTreatmentCat = "<option value='0'>Select Value</option>";
			
			if(vo.getOfflineTreatmentCategoryList() != null && vo.getOfflineTreatmentCategoryList().size() > 0)
			{			 
				strTreatmentCat = hisutil.getOptionValue(vo.getOfflineTreatmentCategoryList(),vo.getStrTreatmentCategory(), "", true);							
			}
			
			formBean.setStrTreatmentCategoryValues(strTreatmentCat);
			
			 if(strMode.equals("1"))
			 {				 
				// if(formBean.getStrTempWardCode().trim().length() > 0)
				//	 vo.setStrWardType(formBean.getStrTempWardCode().replace("^", "#").split("#")[0]);		
					 	 
				 if(vo.getStrWardType().equals("1"))
				 {						
					 formBean.setStrWardTypeValues("<option value='1'>GENERAL</option>");						
				 }
				 else
				 {
						formBean.setStrWardTypeValues("<option value='2'>PRIVATE</option>");					
				 }				 
			 }
			 else
			 {		
				 bo.getPrevBedTransfers(vo);
				 IpdBillManagementTransHLPNew.getPrevBedTransfer(vo);
				 if(formBean.getStrTempWardCode().trim().length() > 0)
				 vo.setStrWardType(formBean.getStrTempWardCode().replace("^", "#").split("#")[0]);		
				 
				 String strWardType = "<option value='0'>Select Value</option>";
					
				if(vo.getWardTypeList() != null && vo.getWardTypeList().size() > 0)
				{					 
					strWardType = hisutil.getOptionValue(vo.getWardTypeList(),vo.getStrLastchargetype() , "0^Select value", false , false);							
				}
				
				formBean.setStrWardTypeValues(strWardType);				 
			 							
				String strPrevDates = "<option value='0'>Select Value</option>";
						
				if(vo.getStrPreviousDatesList() != null && vo.getStrPreviousDatesList().size() > 0)
				{							 
					strPrevDates = hisutil.getOptionValue(vo.getStrPreviousDatesList(),"0", "0^Select value", true);							
				}							
				formBean.setStrPreviousDateValues(strPrevDates);						
				LinkedHashMap<String, String> mapProcedureParam = new LinkedHashMap<String, String>();
				mapProcedureParam.put("mode_val", "2");
				mapProcedureParam.put("dept_code", vo.getStrDepartment());
				mapProcedureParam.put("hosp_code", formBean.getStrHospitalCode());
				mapProcedureParam.put("err", "#1");
				mapProcedureParam.put("resultset", "#2");
				
				
				formBean.setStrSpecialWardTypeValues(HisComboOptions.getOptionsFromProc("pkg_bill_view.proc_ward_list", mapProcedureParam, vo.getStrLastwardTypr()+"^"+vo.getStrLastchargetype(), "0^Select Value", false));
				
				
				
				
				LinkedHashMap<String, String> mapProcedureParam1 = new LinkedHashMap<String, String>();
				//mapProcedureParam1.put("mode_val", "1");
				mapProcedureParam1.put("dept_code", vo.getStrDepartment());
				mapProcedureParam1.put("hosp_code", formBean.getStrHospitalCode());
				mapProcedureParam1.put("seatid", "");
				mapProcedureParam1.put("err", "#1"); 
				mapProcedureParam1.put("resultset", "#2");
				
				
				formBean.setStrUnitNameValues(HisComboOptions.getOptionsFromProc("pkg_bill_view.proc_unit", mapProcedureParam1,  vo.getStrDeptUnitId(), "0^Select Value", false));
				
				LinkedHashMap<String, String> mapProcedureParam2= new LinkedHashMap<String, String>();
				mapProcedureParam2.put("modval", "6");
				mapProcedureParam2.put("deptunitcode", vo.getStrDeptUnitId());
				mapProcedureParam2.put("hosp_code", formBean.getStrHospitalCode());
				mapProcedureParam2.put("seatid", "");
				mapProcedureParam2.put("err", "#1"); 
				mapProcedureParam2.put("resultset", "#2");
				
				
				formBean.setStrDocNameValues(HisComboOptions.getOptionsFromProc("pkg_ipd_view.proc_unit_consulatant_view", mapProcedureParam2, "", "0^Select Value", false));
			 
			 }					 
			 bo.getParticularDtlsList(vo);

			 formBean.setStrParticularDtlsListWs(vo.getStrParticularDtlsListWs());
			 formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));
			 String sb=IpdBillManagementTransHLPNew.getViewBillParticularsList(formBean);
			
			 bo.getPrevBedTransfers(vo);
			 String strPrevBedTransfer=IpdBillManagementTransHLPNew.getPrevBedTransfer(vo);
			 formBean.setStrPrevBedTransfer(strPrevBedTransfer);
			 if(vo.getStrLastStratDate()!=null && !vo.getStrLastStratDate().equals(""))
				 formBean.setStrStartDate(vo.getStrLastStratDate());
			 if(vo.getStrLastEndDate()!=null && !vo.getStrLastEndDate().equals(""))
			 {
				 formBean.setStrEndDate(vo.getStrLastEndDate());
			 	 formBean.setStrCtDate(vo.getStrLastEndDate());
			 }
			
			 //formBean.setStrNumRows(vo.getStrNumRows());
			 //String strParticulaDtl = HLPParticularDtl.getParticularDtlDiffPayable(vo.getStrAccountNo(),vo.getStrHospitalCode(),"0","0","0",null);
			 //String sb2=sb+""+strParticulaDtl;
			 //formBean.setStrParticulaDtl(sb2);				
			
			 formBean.setStrcurrentdate(hisutil.getASDate("dd-MM-yyyy kk:mm:ss"));
			 String strDept = "<option value='0'>Select Value</option>";
				if(vo.getStrDepartmentList() != null && vo.getStrDepartmentList().size() > 0)
				{
						strDept = hisutil.getOptionValue(vo.getStrDepartmentList(),vo.getStrLastDept(), "0^Select value", true);							
				}						
				formBean.setStrDepartmentValues(strDept);
				formBean.setStrchkvalue(vo.getStrchkvalue());
				//System.out.println("formBean.getStrStartDate()"+formBean.getStrStartDate());
				//System.out.println("formBean.getStrEndDate()"+formBean.getStrEndDate());
				//System.out.println("formBean.getStrCtDate()"+formBean.getStrCtDate());
			 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->initBedTransferDtl()",e.getMessage());
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{
			bo = null;
			vo = null;
			hisutil = null;
		}
		
	}
	
	public static boolean InsertBedTransfer(HttpServletRequest request,HttpServletResponse response, IpdBillManagementTransFB formBean) 
	{
		boolean fretValue = true;
		String strmsgText = "";
		String strMsg = "";
		String strWarning = "";
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;

		try 
		{
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			if(formBean.getIsBillFinal()!=null && !formBean.getIsBillFinal().equals(""))
			{
				vo.setIsBillFinal(formBean.getIsBillFinal());
			}
			else
				vo.setIsBillFinal("90");
			
			String strChk = request.getParameter("strChk");
			formBean.setStrChk(strChk);
			String[] strTemp = strChk.split("\\@");

			vo.setStrPatAcctNo(strTemp[0]);
			vo.setStrPukNo(strTemp[1]);
			vo.setStrPatientCatCode(formBean.getStrNewTreatmentCategory());
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrWardType(formBean.getStrTempWardCode().replace("^", "#").split("#")[0]);			
			vo.setStrChargeTypeID(strTemp[4]);
			vo.setStrIpdChgTypeId(strTemp[4]);
			vo.setStrAddmissionNo(strTemp[5]);
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrNetActualTariffAmt("0");
			vo.setStrNetServiceTaxAmt("0");
			vo.setStrNetTariffAmt("0");			
			vo.setStrCompChargeCheck(formBean.getStrCompChargeCheck());		
			vo.setStrDepartment(formBean.getStrDepartment());
			vo.setStrSpecialWardType(formBean.getStrSpecialWardType());
			vo.setStrStartDate(formBean.getStrStartDate());
			vo.setStrEndDate(formBean.getStrEndDate());
			vo.setStrSpecialChargeCheck(formBean.getStrSpecialChargeCheck());
			vo.setStrNewBabyBed(formBean.getStrNewBabyBed());

			vo.setStrTransferDeptCode(formBean.getStrTransferDeptCode());
			vo.setStrTransferWardCode(formBean.getStrTransferWardCode());
			vo.setStrTransferChargeType(formBean.getStrTransferChargeType());
			vo.setStrInDate(formBean.getStrInDate());
			vo.setStrOutDate(formBean.getStrOutDate());
			vo.setStrSaveFlag(formBean.getStrSaveFlag());
			vo.setStrMovNo(formBean.getStrMovNo());
			vo.setStrIsDoubleOc(formBean.getStrIsDoubleOc());
			vo.setStrDblOccDate(formBean.getStrDblOccDate());
			vo.setStrUnitCode(formBean.getStrUnitCode());
			vo.setStrConsultant(formBean.getStrConsultant());
			vo.setStrbcflag(formBean.getStrbcflag());
			vo.setAdtOnlineFlag(formBean.getAdtOnlineFlag());
			fretValue = bo.InsertBedTransfer(vo);
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			if (fretValue) 
			{
				strMsg = "Record Saved Successfully!";
				formBean.setStrMsg(strMsg);
			} 
			else 
			{
				strWarning = "Record Not Saved!Data Already Exist!!";
				formBean.setStrWarning(strWarning);
			}
			return fretValue;
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			fretValue = false;
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->InserAddService()", strmsgText);
			formBean.setStrErrMsg("Patient Account Error/Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{
			bo = null;
			vo = null;
		}
		return fretValue;
	}
	
	public static boolean admissionList(HttpServletRequest request,HttpServletResponse response, IpdBillManagementTransFB formBean) 
	{
		
		String strChk = null;
		String strChk2 = null;
		String strChkBoxComboValue = null;
		String strTemp[] = null;
		boolean bretVal = true;
		String msgStr = "";
		String strPatView = "";

		// Create The Object for VO & BO .
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;
		HisUtil hisutil = null;
		// TransferObjectFactory.populateData(formBean,vo);
		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			// Set Value in VO
			//strChk = request.getParameter("chk");
			//strChk2 = request.getParameter("chk");
			if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
			{
				String[] arr=formBean.getChk();
				strChk =arr[0];
				strChk2 =arr[0];
			}
			else
			{
				strChk =formBean.getStrChk();
				strChk2 =formBean.getStrChk();
			}
			if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
			{
				String[] arr=formBean.getChk();
				strChk =arr[0];
				strChk2 =arr[0];
			}
			else
			{
				strChk =formBean.getStrChk();
				strChk2 =formBean.getStrChk();
			}
			String s1[] = formBean.getStrComboValue();
			
			formBean.setStrWardCode(formBean.getCombo()[2].replace("^", "#").split("#")[0]);
			
			formBean.setStrChk(strChk);
			String s = s1[1] + "^" + s1[2];
			strChkBoxComboValue = strChk2 + "@" + s;
			vo.setStrDeptCode(s1[1]);
			vo.setStrChk(strChk);
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			// Setting List Page Chk Value
			vo.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString()); // Setting SEATID
			vo.setStrComboValue(formBean.getStrComboValue()); // Setting For
			// List Page
			// Combo Value

			String strStatus = "";

			if (s1[0].equals("1"))
				strStatus = "Active";
			if (s1[0].equals("2"))
				strStatus = "In-Active";
			if (s1[0].equals("3"))
				strStatus = "On-Hold";
			if (s1[0].equals("4"))
				strStatus = "Dormant";
			if (s1[0].equals("5"))
				strStatus = "Approved Request";
			if (s1[0].equals("6"))
				strStatus = "Canceled";

			strTemp = strChk.replace('@', '#').split("#"); // Get The Cr No....

			// Calling BO getValues() Method

			bo.getValues(vo);

			// Setting Value In Form Bean to Display..

			formBean.setStrCrNo(strTemp[1]);
			formBean.setStrAccountNo(strTemp[0]);
			formBean.setStrPresentAcctStatus(strStatus);
			formBean.setStrPresentAcctStatusCode(s1[0]);
			formBean.setStrAddmissionNo(strTemp[5]);
			//System.out.println("formBean.setStrAddmissionNo"+formBean.getStrAddmissionNo());

			// Setting of Department Code
			formBean.setStrDeptCode(s1[1]);
						
			// Calling HLP to get Patient Detail
			strPatView = PatientDtlHLPNew.compactPatientWithAdmissionDtl(formBean.getStrCrNo(), vo.getStrHospitalCode(), false);
			String[] str=strPatView.replace("~","#").split("#");
			formBean.setStrPatientDetailsView(str[0]);

			

			formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));

			vo.setStrAddmissionNo(formBean.getStrAddmissionNo());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			formBean.setPrintMode(BillConfigUtil.PRINT_MODE.toString());//Laser Printing or DMP Printing

			String strAdmissionList = "";
			
			try 
			{
				bo.admissionList(vo);
				strAdmissionList = IpdBillManagementTransHLPNew.admissionList(vo);
				formBean.setPrintFlag(vo.getPrintFlag());
				formBean.setStrAddmissionNo(vo.getStrAddmissionNo());
				formBean.setStrAdmissionDate(vo.getStrAdmissionDate());
				formBean.setStrDischargeDate(vo.getStrDischargeDate());
				formBean.setStrAccountNo(vo.getStrAccountNo());
				formBean.setStrCrNo(vo.getStrCrNo());
				formBean.setStrTreatmentCategory(vo.getStrTreatmentCategory());
				formBean.setStrDeptId(vo.getStrDeptId());
				formBean.setStrWardCode(vo.getStrWardCode());
				formBean.setStrBillNo(vo.getStrBillNo());
				//System.out.println("formBean.getPrintFlag()"+formBean.getPrintFlag());
			} 
			catch (Exception e) 
			{
				throw e;
			}
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrErrMsg());
			}

			formBean.setStrPatientAdmndtl(strAdmissionList);
		 

			
		} catch (Exception e) {

			bretVal = false;
			msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->initPatientDtl()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			vo = null;

			bo = null;

			hisutil = null;
		}
		return bretVal;
		
		
	}
	public static boolean InsertNoDuesPrint(
			IpdBillManagementTransFB formBean  , HttpServletRequest request) {
		// Declare Variables
		boolean fretValue = false;
		// String strErrmsg = "";
		String strmsgText = "";
		String strMsg = "";
		String strWarning = "";
		HisUtil hisutil = null;

		// Create Object for VO & BO
		IpdBillManagementTransVO vo = null;
		IpdBillManagementTransBO bo = null;

		try {
			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			hisutil = new HisUtil("transaction", "IpdBillManagementTransBSDATA");
			// Set the Value for VO

			vo.setStrChk(formBean.getStrChk());
			//vo.setStrApprovalBy(formBean.getStrApprovedByCombo());
			vo.setStrSeatId(formBean.getStrSeatId());
			/*vo.setStrRemarks(formBean.getStrRemarksCombo2());
			vo.setStrPresentAcctStatus(formBean.getStrPresentAcctStatus());
			vo.setStrPAcctStatus(formBean.getCombo()[0]);
			vo.setStrNewAcctStatus(formBean.getStrNewAcctStatus());*/
			formBean.setStrCtDate(hisutil.getASDate("dd-MMM-yyyy"));
			vo.setStrCtDate(formBean.getStrCtDate());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrAddmissionNo(formBean.getStrAddmissionNo());
			vo.setStrAdmissionDate(formBean.getStrAdmissionDate());
			vo.setStrDischargeDate(formBean.getStrDischargeDate());
			vo.setStrAccountNo(formBean.getStrAccountNo());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrTreatmentCategory(formBean.getStrTreatmentCategory());
			vo.setStrDeptId(formBean.getStrDeptId());
			vo.setStrWardCode(formBean.getStrWardCode());
			vo.setStrBillNo(formBean.getStrBillNo());
			int isdup;
			if(vo.getStrBillNo().equals("0"))
				isdup=0;
			else
				isdup=1;
			bo.insertNoDuesPrint(vo);
			formBean.setStrBillNo(vo.getStrBillNo());
			if (fretValue) {
				strMsg = "No Dues Printed Successfully!";
				vo.setStrMsg(strMsg);
			} else {
				strWarning = "Record Not Saved!Data Already Exist!!";
				vo.setStrWarning(strWarning);
			}
			// Check Error
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			if (!vo.getStrMsgType().equals("1")) {

				PrintHLP.NO_DUES(formBean.getStrBillNo(), "21", formBean.getStrHospitalCode(),
						"1" , request , isdup,"0");

				formBean.setStrMsg("Receipt No. '"
						+ formBean.getStrBillNo()
						+ "/1"
						+ "' Generated Successfully for the No Dues Print");

				
				
				String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
				formBean.setFilePath(fileName);
				request.setAttribute("filePath", fileName);
				formBean.setIsOpenPopUp("1");

				fretValue = true;
			} else {

				fretValue = false;

				throw new Exception(vo.getStrMsgString());

			}

		 
		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransBSDATA->InserRecordForPartPayment()",
					strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;
			bo = null;
		}
		return fretValue;
	}
	public static void getUnitDtls(HttpServletRequest request,HttpServletResponse response) 
	{
		String temp = "";
		try 
		{			 
			String strDept = request.getParameter("strDept");
			String strHospCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();			
			LinkedHashMap<String, String> mapProcedureParam1 = new LinkedHashMap<String, String>();
			mapProcedureParam1.put("dept_code", strDept);
			mapProcedureParam1.put("hosp_code", strHospCode);
			mapProcedureParam1.put("seatid", "");
			mapProcedureParam1.put("err", "#1"); 
			mapProcedureParam1.put("resultset", "#2");
			
			temp = HisComboOptions.getOptionsFromProc("pkg_bill_view.proc_unit", mapProcedureParam1, "0", "0^Select Value", false);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->getSpecialWardDtls()",strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} 
			catch (IOException e1) 
			{
				new HisException("Billing","IpdBillManagementTransBSDATA->getSpecialWardDtls()", e1.getMessage());
			}
		}
	}
	public static void getConsultatnt(HttpServletRequest request,HttpServletResponse response) 
	{
		String temp = "";
		String temp1 = "";
		try 
		{			 
			String strDept = request.getParameter("strDept");
			String strUnit = request.getParameter("strUnit");
			String strHospCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();			
			
			LinkedHashMap<String, String> mapProcedureParam2= new LinkedHashMap<String, String>();
			mapProcedureParam2.put("modval", "6");
			mapProcedureParam2.put("deptunitcode", strUnit);
			mapProcedureParam2.put("hosp_code", strHospCode);
			mapProcedureParam2.put("seatid", "");
			mapProcedureParam2.put("err", "#1"); 
			mapProcedureParam2.put("resultset", "#2");
			temp1 = HisComboOptions.getOptionsFromProc("pkg_ipd_view.proc_unit_consulatant_view", mapProcedureParam2, "", "0^Select Value", false);
			
			
			LinkedHashMap<String, String> mapProcedureParam = new LinkedHashMap<String, String>();
			mapProcedureParam.put("mode_val", "3");
			mapProcedureParam.put("dept_code", strUnit);
			mapProcedureParam.put("hosp_code", strHospCode);
			mapProcedureParam.put("err", "#1");
			mapProcedureParam.put("resultset", "#2");
			
			temp = HisComboOptions.getOptionsFromProc("pkg_bill_view.proc_ward_list", mapProcedureParam, "", "0^Select Value", false);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp+"@@"+temp1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Billing","IpdBillManagementTransBSDATA->getSpecialWardDtls()",strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} 
			catch (IOException e1) 
			{
				new HisException("Billing","IpdBillManagementTransBSDATA->getSpecialWardDtls()", e1.getMessage());
			}
		}
	}
 }

		