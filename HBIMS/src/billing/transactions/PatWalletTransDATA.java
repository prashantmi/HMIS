package billing.transactions;

import hisglobal.ReportUtil;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.utility.HisUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import org.apache.struts.upload.FormFile;

import billing.BillConfigUtil;
import billing.HLPTariffDtl;
import billing.PrintHLP;
import billing.reports.BillEnquiryRptBO;
import billing.reports.BillEnquiryRptFB;
import billing.reports.BillEnquiryRptVO;
import hisglobal.utility.SMSHttpsNICPostClient;
import hisglobal.utility.SMSSender.SMSHttpPostClient;

// TODO: Auto-generated Javadoc
/**
 * The Class CashCollectionTransDATA.
 */
public class PatWalletTransDATA
{

	/**
	 * method used to check whether the System's IP Address is Registered in
	 * Counter Master or Not.
	 * 
	 * @param request
	 * @param formBean
	 */
	public static String checkCounterStatus(HttpServletRequest request, PatWalletTransFB formBean)
	{

		PatWalletTransBO bo = null;
		PatWalletTransVO voObj = null;
		String strCounterId ="";
		try
		{
			formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

			bo = new PatWalletTransBO();
			voObj = new PatWalletTransVO();

			voObj.setStrIpAddress(formBean.getStrIpAddress());
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.checkCounterStatus(voObj);

			strCounterId = voObj.getStrCounterId();

			if (strCounterId != null)
			{
				if (strCounterId.trim().length() < 4)
				{
					formBean.setStrErrMsg("Cash Collection is Not Applicable, System IP Address is not Registered as Counter");
					formBean.setStrCrNo("");
				}
			}
			else
			{
				formBean.setStrErrMsg("Cash Collection is Not Applicable, System IP Address is not Registered as Counter");
				formBean.setStrCrNo("");
			}
		} 
		catch (Exception e)
		{
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing", "CashCollectionTransDATA->checkCounterStatus()", msgStr);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrCrNo("");
			eObj = null;
		} 
		finally
		{
			bo = null;
			voObj = null;
			return strCounterId;
		}

	}

	/**
	 * Initialize all the required information like Online & Off-line Details
	 * for given Cr Number.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * 
	 * @return true - all the required information has been initialized<br>
	 *         false - all the required information has not been initialized.
	 */
	public static boolean init(HttpServletRequest request, PatWalletTransFB formBean)
	{
		boolean bResultVal = true;
		String strCrNo = formBean.getStrCrNo();

		PatWalletTransBO bo = null;
		PatWalletTransVO voObj = null;
		BillConfigUtil configUtil = null;
		HisUtil hisUtil = null;

		try
		{
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			String strPatView = "";

			strPatView = PatientDtlHLP.compactPatientWithAdmissionDtl(strCrNo, formBean.getStrHospitalCode(), false);

			/*String[] str=strPatView.replace("~","#").split("#");
			formBean.setStrPatientDetailsView(str[0]);*/
			formBean.setStrPatientDetailsView(strPatView);
			
			/*if(str[1].equals("1"))//AdmittedDetailsFound
			{
				if(PatientDtlHLP.strAdmittedTreamentCatCode!=null && !PatientDtlHLP.strAdmittedTreamentCatCode.equals(""))
				{
					formBean.setStrTreatmentCategory(PatientDtlHLP.strAdmittedTreamentCatCode);
					formBean.setStrIsAdmittedTreatmentCategoryPresent("1");//1-Yes,0-No
					formBean.setStrTreatmentCategoryGroup(PatientDtlHLP.strAdmittedTreamentCatCodeGroup);
					
				}
			}
			else if(PatientDtlHLP.strTreamentCatCode!=null && !PatientDtlHLP.strTreamentCatCode.equals(""))
				{
					formBean.setStrTreatmentCategory(PatientDtlHLP.strTreamentCatCode);///Primary Category
					formBean.setStrTreatmentCategoryGroup(PatientDtlHLP.strTreamentCatCodeGroup);
				}*/
			
			bo = new PatWalletTransBO();
			voObj = new PatWalletTransVO();
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			configUtil = new BillConfigUtil(voObj.getStrHospitalCode());

			hisUtil = new HisUtil("Billing", "CashCollectionTransDATA");

			formBean.setStrIpdThirdPartyBenefit(configUtil.getIpdThirdPartyBilling());
			formBean.setStrOpdThirdPartyBenefit(configUtil.getOpdThirdPartyBilling());
			formBean.setStrEmergencyThirdPartyBenefit(configUtil.getEmergencyThirdPartyBilling());

			formBean.setStrConsumableChargesGroupId(configUtil.getStrConsumableChargesGroupId());
			formBean.setStrConsumableChargesTariffCode(configUtil.getStrConsumableChargesTariffCode());
			formBean.setPrintMode(BillConfigUtil.PRINT_MODE.toString());//Laser Printing or DMP Printing

			if (formBean.getStrOffLineHospitalService().equals("2"))
			{
				formBean.setStrFreeCategory(configUtil.getIpdFreeCategory());
			} 
			else if (formBean.getStrOffLineHospitalService().equals("1"))
			{
				formBean.setStrFreeCategory(configUtil.getOpdFreeCategory());
			} 
			else
			{
				formBean.setStrFreeCategory(configUtil.getEmergencyFreeCategory());
			}

			formBean.setStrCurrentDate(hisUtil.getASDate("dd-MMM-yyyy"));
			formBean.setStrSurCc(configUtil.getStrSurCc());
			formBean.setStrSurDc(configUtil.getStrSurDc());
			formBean.setStrSurIc(configUtil.getStrSurIc());
			formBean.setStrSurId(configUtil.getStrSurId());
			formBean.setStrSurCc1(configUtil.getStrSurCc1());
			formBean.setStrSurDc1(configUtil.getStrSurDc1());
			formBean.setStrSurIc1(configUtil.getStrSurIc1());
			formBean.setStrSurId1(configUtil.getStrSurId1());
			formBean.setDefsurlim(BillConfigUtil.DEFAULT_SURCHARGE_LIMIT);

			PatWalletTransDATA.initOffLineDetails(formBean, bo, voObj);

			formBean.setStrIsRefundReq(configUtil.getGeneralCCOfflineRefundRequired());
			formBean.setStrPrintMessageLimit(configUtil.getGeneralPrintMessageLimit());
			
			System.out.println("temp2"+formBean.getStrAccountDetailsView());

		} 
		catch (Exception e)
		{
			bResultVal = false;
			String msgStr = e.getMessage();

			if (msgStr.contains("CR."))
			{
				formBean.setStrErrMsg("Invalid CR. No./Category Expired");
			} 
			else
			{
				HisException eObj = new HisException("Billing", "PatWalletTransDATA->init()", msgStr);
				formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				formBean.setStrCrNo("");
				eObj = null;
			}
		} 
		finally
		{
			bo = null;
			voObj = null;
			configUtil = null;
			hisUtil = null;
		}

		return bResultVal;
	}

	public static void preInitOffLineDetails(PatWalletTransFB formBean)
	{
		PatWalletTransBO bo = null;
		PatWalletTransVO voObj = null;

		HisUtil util = null;
		BillConfigUtil bcu = null;

		try
		{
			bo = new PatWalletTransBO();
			voObj = new PatWalletTransVO();
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			bcu = new BillConfigUtil(voObj.getStrHospitalCode());

			//voObj.setStrOffLineHospitalService("1");//1-OPD,2-IPD,3-Emg
			voObj.setStrOffLineRequestType("1");//1-OPD Services
			voObj.setStrSeatId(formBean.getStrSeatId());

			bo.preInitOffLineDetails(voObj);
			if (voObj.getStrMsgType().equals("1"))
			{
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("Billing Transaction", "CashCollectionTrans");
			String temp = "";

			if (voObj.getOfflineHospitalServiceList() != null && voObj.getOfflineHospitalServiceList().size() != 0)
			{
				temp = util.getOptionValue(voObj.getOfflineHospitalServiceList(), voObj.getStrOffLineHospitalService(), "",false);
				formBean.setStrHospitalServiceDetails(temp);
			}
			if (voObj.getOfflineBillingServiceList() != null && voObj.getOfflineBillingServiceList().size() != 0)
			{
				temp = util.getOptionValue(voObj.getOfflineBillingServiceList(), voObj.getStrOffLineBillingService(), "",false);
				formBean.setStrBillingServiceDetails(temp);
			}
			formBean.setStrIsOnline("0");
			formBean.setCurrentState("2");//?

			formBean.setStrIsWithoutCrNoRequired(bcu.getGeneralIsWithoutCrNoRequired());
			formBean.setStrConfirmationType(bcu.getGeneralCashCollectionConfrimType());//OK-Cancel
			formBean.setStrIsRefundReq(bcu.getGeneralCCOfflineRefundRequired());//Refund Without Approval Required or Not
			formBean.setStrPrintMessageLimit(bcu.getGeneralPrintMessageLimit());//Print Alert
		} 
		catch (Exception e)
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing", "CashCollectionTransDATA->preInitOffLineDetails()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} 
		finally
		{
			bo = null;
			voObj = null;
			util = null;
			bcu = null;
		}
	}

	/**
	 * Initialize all the required information like Off-line Details for given
	 * Cr Number.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param bo -
	 *            Business Object
	 * @param voObj -
	 *            Value Object
	 */
	public static void initOffLineDetails(PatWalletTransFB formBean, PatWalletTransBO bo,PatWalletTransVO voObj)
	{
		BillConfigUtil billConfigUtil = null;
		HisUtil util = null;
		String selectedTreatmentCat="11^0";//Default General

		try
		{
			billConfigUtil = new BillConfigUtil(voObj.getStrHospitalCode());
			System.out.println("in data status is "+formBean.getStrAccountStatus());
			formBean.setStrOfflineIpdPenaltyVal(billConfigUtil.getIpdRefundPenalty());
			formBean.setStrOfflineOpdPenaltyVal(billConfigUtil.getOpdRefundPenalty());
			formBean.setStrOfflineEmergencyPenaltyVal(billConfigUtil.getEmergencyRefundPenalty());

			formBean.setStrIsEmergencyDiscount(billConfigUtil.getEmergencyDiscountClerk());
			formBean.setStrIsIpdDiscount(billConfigUtil.getIpdDiscountClerk());
			formBean.setStrIsOpdDiscount(billConfigUtil.getOpdDiscountClerk());
			formBean.setStrIsApprovalRequired(billConfigUtil.getGeneralIsApprovalRequired());
			formBean.setStrIsAdvanceRequired(billConfigUtil.getIpdGenAdvanceReq());
			//System.out.println("formBean.getStrMobileNo()"+formBean.getStrMobileNo());
			/*if(formBean.getStrAccountStatus().equals("1"))
			  formBean.setStrMobileNo1(formBean.getStrMobileNo());
			System.out.println("formBean.getStrMobileNo1()"+formBean.getStrMobileNo1());*/
			//System.out.println("billConfigUtil.getIpdGenAdvanceReq()="+billConfigUtil.getIpdGenAdvanceReq());
			//System.out.println("billConfigUtil.strIsAdvanceRequired()="+billConfigUtil.ipdGenAdvanceReq);
			if (formBean.getStrOffLineHospitalService().equals("1"))
			{
				voObj.setStrClerkDiscount(formBean.getStrIsOpdDiscount());
			} 
			else if (formBean.getStrOffLineHospitalService().equals("2"))
			{
				voObj.setStrClerkDiscount(formBean.getStrIsIpdDiscount());
			} 
			else
			{
				voObj.setStrClerkDiscount(formBean.getStrIsEmergencyDiscount());
			}

			voObj.setStrOffLineRequestType(formBean.getStrOffLineRequestType());
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrCrNo(formBean.getStrCrNo());
			//voObj.setStrOffLineHospitalService(formBean.getStrOffLineHospitalService());
			voObj.setStrOffLineBillingService(formBean.getStrOffLineBillingService());
			voObj.setStrSeatId(formBean.getStrSeatId());
			voObj.setStrIsApprovalRequired(formBean.getStrIsApprovalRequired());
			voObj.setStrIsAdvanceRequired(formBean.getStrIsAdvanceRequired());
			voObj.setStrTreatmentCategory(formBean.getStrTreatmentCategory());
			voObj.setStrAccountStatus(formBean.getStrAccountStatus());
			bo.initOffLineDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1"))
			{
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("Billing Transaction", "CashCollectionTrans");
			String temp1 = "";
			String temp = "";

			if(voObj.getStrWalletAccountNo() !=null && voObj.getStrWalletAccountNo() !="")
			{
				formBean.setStrWalletAccountNo(voObj.getStrWalletAccountNo());
				formBean.setStrWalletAccountOpenDate(voObj.getStrWalletAccountOpenDate());
				formBean.setStrWalletDepositedAmount(voObj.getStrWalletDepositedAmount());
				formBean.setStrWalletBalanceAmount(voObj.getStrWalletBalanceAmount());
				formBean.setStrWalletAccountStatus(voObj.getStrWalletAccountStatus());
				formBean.setStrMobileNo1(voObj.getStrMobileNo1());
				//formBean.setStrChargeType(voObj.getStrChargeType());
				formBean.setStrOffLineWard(voObj.getStrOffLineWard());
				formBean.setStrOffLineSpecialWard(voObj.getStrOffLineSpecialWard());
				
				if(voObj.getStrAccountStatus().equals("3"))
			      {
			temp1 = PatWalletTransHLP.WalletAccountDetail(voObj);
			      }
				formBean.setStrAccountDetailsView(temp1);
			      
			}
			

			formBean.setStrAccountDetailsView(temp1);
			if(voObj.getStrAccountStatus().equals("4") || voObj.getStrAccountStatus().equals("2"))
		      {
				System.out.println();
				
            	temp= PatWalletTransHLP.getWalletAccountSummary(voObj.getWsWalletAccountSummary(),voObj.getStrAccountStatus());
        
			System.out.println("temp is::"+temp);
			formBean.setStrWalletAccountSummary(temp);
			}
			if (voObj.getAdmEpisodeTreatCatDeptDtls() != null && voObj.getAdmEpisodeTreatCatDeptDtls().size() != 0)
			{
				WebRowSet ws=voObj.getAdmEpisodeTreatCatDeptDtls();
				if(voObj.getAdmEpisodeTreatCatDeptDtls().next())
				{
					voObj.setStrOffLineRaisingDepartment(ws.getString(1));
					voObj.setStrOffLineEpisode(ws.getString(2));
					//selectedTreatmentCat=ws.getString(4)+"^"+ws.getString(5);					
				}
				
			}
			//strOffLineTreatmentCategory strTreatmentCategoryDetails
			
			if (voObj.getOfflineRaisingDepartmentList() != null && voObj.getOfflineRaisingDepartmentList().size() != 0)
			{//Last Visited Department Selected
				System.out.println("Dept"+voObj.getStrOffLineRaisingDepartment());
				temp = util.getOptionValue(voObj.getOfflineRaisingDepartmentList(), voObj.getStrOffLineRaisingDepartment(),	"0^Select Value", false);
				formBean.setStrRaisingDepartmentDetails(temp);
			} 
			else
			{
				formBean.setStrRaisingDepartmentDetails("<option value='0'>Select Value</option>");
			}

			if (voObj.getOfflineEpisodeList() != null && voObj.getOfflineEpisodeList().size() != 0)
			{//Last Visited Department Episode
				temp = util.getOptionValue(voObj.getOfflineEpisodeList(), voObj.getStrOffLineEpisode(), "", true);
				formBean.setStrEpisodeDetails(temp);
				/*String temp1[] = voObj.getStrOffLineEpisode().replace("@", "#").split("#");
				if (!temp1[1].equals("") || !temp1[1].equals("0"))
				{
					voObj.setStrOffLineTreatmentCategory(temp1[1]);
					voObj.setStrOffLineTreatmentCategoryGroup(temp1[2]);
				}*/
			} 
			else
			{
				formBean.setStrEpisodeDetails("<option value='0'>Select Value</option>");
			}
			selectedTreatmentCat=PatientDtlHLP.strTreamentCatCode+"^"+PatientDtlHLP.strTreamentCatCodeGroup;
			if (voObj.getOfflineTreatmentCategoryList() != null && voObj.getOfflineTreatmentCategoryList().size() != 0)
			{
				temp = util.getOptionValue(voObj.getOfflineTreatmentCategoryList(),selectedTreatmentCat ,	"", false);
				formBean.setStrTreatmentCategoryDetails(temp);
				
				formBean.setStrOffLineTreatmentCategory(selectedTreatmentCat);
			} 
			else
			{
				formBean.setStrTreatmentCategoryDetails("<option value='0'>Select Value</option>");
			}
			if (voObj.getOfflineWardList() != null && voObj.getOfflineWardList().size() != 0)
			{
				temp = util.getOptionValue(voObj.getOfflineWardList(), voObj.getStrOffLineWard(), "", false, false);
				formBean.setStrWardDetails(temp);
			} 
			else
			{
				formBean.setStrWardDetails("<option value='0'>Select Value</option>");
			}

			
			if (voObj.getStrPaymentModeList() != null && voObj.getStrPaymentModeList().size() != 0)
			{
				temp = util.getOptionValue(voObj.getStrPaymentModeList(), "", "", false);
				formBean.setStrPaymentModeContents(temp);
			}
			if (bo.isPartPaymentorAdvanceExist(voObj))
			{
				String strPartOrAdvanceDtls = "";

				if (voObj.getStrOffLineBillingService().equals("19"))
				{
					strPartOrAdvanceDtls = PatWalletTransHLP.getOffLineAdvanceDetailsView(voObj);
				} 
				else
				{
					strPartOrAdvanceDtls = PatWalletTransHLP.getOffLinePartPaymentDetailsView(voObj);
				}
				formBean.setStrPartPayAdvanceAmountDetails(strPartOrAdvanceDtls);
			}
			if (voObj.getStrOffLineBillingService().equals("20") && voObj.getStrOffLineRequestType().equals("2"))
			{
				String strPartOrAdvanceDtls = "";
				strPartOrAdvanceDtls = PatWalletTransHLP.getOffLinePartPaymentDetailsView(voObj);

			}
			
			if (voObj.getOfflineSplWardList() != null && voObj.getOfflineSplWardList().size() > 0)
			{
				temp = util.getOptionValue(voObj.getOfflineSplWardList(), voObj.getStrOffLineSpecialWard(),"0^Select Value", false);
			} 
			else
			{
				temp = "<option value='0'>Select Value</option>";
			}
            
			
			
			/******Added By vikrant For Cash Collection Offline(Patient Details)**********/
			formBean.setStrRmk(voObj.getStrRmk());
			formBean.setStrRelatinDetails(voObj.getStrRelationWs());
			
			formBean.setStrSpecialWardDetails(temp);

			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrRelatinDetails(voObj.getStrRelationWs());
			formBean.setStrRmk(voObj.getStrRmk());
			System.out.println("setStrNormalMsg>>>>"+formBean.getStrNormalMsg());
			
		} 
		catch (Exception e)
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing", "CashCollectionTransDATA->initOffLineDetails()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} 
		finally
		{
			billConfigUtil = null;
			util = null;
		}
	}

	


	/**
	 * Re-Print's the bill.
	 * @param request
	 * @param response
	 */
	public static void rePrint(HttpServletRequest request, HttpServletResponse response)
	{

		try
		{

			PrintHLP.reprintFile(request);

		} catch (Exception e)
		{

			HisException eObj = new HisException("Billing", "PatWalletTransDATA->rePrint()", e.getMessage());
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";

			eObj = null;

			try
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(response1);
			} catch (IOException e1)
			{

				new HisException("Billing", "PatWalletTransDATA->rePrint()", e1.getMessage());
			}

		}
	}

	/**
	 * Inserts Off-line Receipt Part Payment Details.
	 * 
	 * @param formBean
	 *            the form bean
	 * 
	 * @return true, if insert off-line receipt part payment
	 */
	public static boolean insertOfflineReceiptPartPayment(PatWalletTransFB formBean,HttpServletRequest request)
	{
		boolean fRes = false;
		PatWalletTransBO bo = null;
		PatWalletTransVO voObj = null;

		try
		{
			bo = new PatWalletTransBO();
			formBean.setStrOffLineClientPatientNo(formBean.getStrOffLineClientDetailsHidden().replace("^", "#").split("#")[0]);
			formBean.setStrOffLineAdmissionNo(formBean.getStrAdmissionDtlHidVal().replace("^", "#").split("#")[0]);
			String[] strWardDtls = formBean.getStrOffLineWard().replace("^", "#").split("#");
			formBean.setStrOffLineWard(strWardDtls[0]);
			formBean.setStrOffLineIpdChargeTypeId(strWardDtls[1]);
			formBean.setStrOffLineEpisode(formBean.getStrOffLineEpisode().replace("^", "#").split("#")[0]);
			formBean.setStrOffLinePartPaymentAmount(formBean.getStrPartpayment());
			formBean.setStrOffLineApprovedBy(formBean.getStrApprovedByCombo());
			formBean.setStrOffLineRemarks(formBean.getStrRemarksCombo2());
			voObj = (PatWalletTransVO) TransferObjectFactory.populateData("billing.transactions.PatWalletTransVO", formBean);
			bo.insertOfflineReceiptPartPayment(voObj);

			formBean.setStrBillNo(voObj.getStrBillNo());
			formBean.setStrReceiptNo("1");


			if (!voObj.getStrMsgType().equals("1"))
			{
				PrintHLP.LF_PART_PAYMENT(formBean.getStrBillNo(), formBean.getStrOffLineBillingService(), formBean.getStrHospitalCode(), formBean.getStrReceiptNo(), request, 0,"1");

				formBean.setStrNormalMsg("Receipt No. '" + formBean.getStrBillNo() + "/1" + "' Generated Successfully for the Collected Amount <img src='../../hisglobal/images/INR.png'> '" + HisUtil
								.getAmountWithDecimal(formBean.getStrOfflineTotalRecAmount(), 2) + "'");

				formBean.setStrTempBillNo(formBean.getStrBillNo());
				formBean.setStrTempReceiptNo("1");
				
				String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
				formBean.setFilePath(fileName);
				request.setAttribute("filePath", fileName);
				System.out.println("fileName"+fileName);
				formBean.setIsOpenPopUp("1");
				fRes = true;
			} 
			else
			{
				String err = voObj.getStrMsgString();
				fRes = false;
				throw new Exception(err);
			}
		} 
		catch (Exception e)
		{

			HisException eObj = new HisException("Billing Transaction","CashCollectionTransDATA.insertOfflineReceiptPartPayment()-->", e.getMessage());
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		}

		return fRes;

	}

	/**
	 * Inserts Off-line Receipt Advance Details.
	 * 
	 * @param formBean
	 *            the form bean
	 * 
	 * @return true, if insert off-line receipt advance
	 */
	public static boolean insertWalletReceiptAdvance(PatWalletTransFB formBean, HttpServletRequest request)
	{
		boolean fRes = false;
		PatWalletTransBO bo = null;
		PatWalletTransVO voObj = null;

		try
		{
			SMSHttpsNICPostClient sms=new SMSHttpsNICPostClient();
			bo = new PatWalletTransBO();
			formBean.setStrOffLineClientPatientNo(formBean.getStrOffLineClientDetailsHidden().replace("^", "#").split("#")[0]);
			formBean.setStrOffLineAdmissionNo(formBean.getStrAdmissionDtlHidVal().replace("^", "#").split("#")[0]);
			/*String[] strWardDtls = formBean.getStrOffLineWard().replace("^", "#").split("#");
			formBean.setStrOffLineWard(strWardDtls[0]);*/
			formBean.setStrOffLineEpisode(formBean.getStrOffLineEpisode().replace("^", "#").split("#")[0]);
			formBean.setStrOffLineAdvanceAmount(formBean.getStrPartpayment());
			formBean.setStrOffLineApprovedBy(formBean.getStrApprovedByCombo());
			formBean.setStrOffLineRemarks(formBean.getStrRemarksCombo2());

			voObj = (PatWalletTransVO) TransferObjectFactory.populateData("billing.transactions.PatWalletTransVO", formBean);
			//voObj.setStrOffLineSpecialWard("0");
			voObj.setStrAccountStatus(formBean.getStrAccountStatus());
			voObj.setStrWalletAccountNo(formBean.getStrWalletAccountNo());
			voObj.setStrChargeType(formBean.getStrChargeType());
			voObj.setStrOffLineWard(formBean.getStrOffLineWard());
			voObj.setStrOffLineSpecialWard(formBean.getStrOffLineSpecialWard());
		
			bo.insertWalletReceiptAdvance(voObj);
            
			formBean.setStrMobileNo1(voObj.getStrMobileNo1());
			formBean.setStrBillNo(voObj.getStrBillNo());
			formBean.setStrReceiptNo("1");
			
				
			if (!voObj.getStrMsgType().equals("1"))
			{
				if(formBean.getStrAccountStatus().equals("1"))
		        {
					if(Integer.parseInt(voObj.getStrCheckMobileNO()) == 0 )
					{
				    String smsMsg="You Paid Rs. "+voObj.getStrAdvacnceAmount()+" from Your Patient Wallet for Opening Wallet Against Reference No. "+voObj.getStrBillNo()+". Your Current Wallet balance is Rs. "+(Double.parseDouble(voObj.getStrAdvacnceAmount()));
				    SMSHttpPostClient.sendSMS (formBean.getStrMobileNo1(),smsMsg);
					}
		        }
				else
				{
					String smsMsg="You Paid Rs. "+voObj.getStrAdvacnceAmount()+" from Your Patient Wallet for Recharging Wallet Against Reference No. "+voObj.getStrBillNo()+". Your Current Wallet balance is Rs. "+(Double.parseDouble(voObj.getStrAdvacnceAmount())+Double.parseDouble(voObj.getStrWalletBalanceAmount()));
					 SMSHttpPostClient.sendSMS (formBean.getStrMobileNo1(),smsMsg);
				}
				if(formBean.getStrAccountStatus().equals("1"))
		        {
					if(Integer.parseInt(voObj.getStrCheckMobileNO()) == 0 )
					{
				PrintHLP.WALLET(formBean.getStrBillNo(), formBean.getStrOffLineBillingService(), formBean.getStrHospitalCode(), formBean.getStrReceiptNo(), request, 0);

				formBean.setStrNormalMsg("Receipt No. '" + formBean.getStrBillNo() + "/1" + "' Generated Successfully for the Collected Amount <img src='../../hisglobal/images/INR.png'> '" + HisUtil
								.getAmountWithDecimal(formBean.getStrAdvacnceAmount(), 2) + "'");

				formBean.setStrTempBillNo(formBean.getStrBillNo());
				formBean.setStrTempReceiptNo("1");	

				String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
				formBean.setFilePath(fileName);
				request.setAttribute("filePath", fileName);
				formBean.setIsOpenPopUp("1");
				formBean.setChkMobileNoCount("1");
				
				fRes = true;
					}
					else
					{
						request.setAttribute("filePath", "");
						formBean.setIsOpenPopUp("0");
						formBean.setChkMobileNoCount("0");
						fRes = false;
						formBean.setStrNormalMsg("Mobile No is already Registered");	
					}
		        }else
		        {
		        	PrintHLP.WALLET(formBean.getStrBillNo(), formBean.getStrOffLineBillingService(), formBean.getStrHospitalCode(), formBean.getStrReceiptNo(), request, 0);

					formBean.setStrNormalMsg("Receipt No. '" + formBean.getStrBillNo() + "/1" + "' Generated Successfully for the Collected Amount <img src='../../hisglobal/images/INR.png'> '" + HisUtil
									.getAmountWithDecimal(formBean.getStrAdvacnceAmount(), 2) + "'");

					formBean.setStrTempBillNo(formBean.getStrBillNo());
					formBean.setStrTempReceiptNo("1");	

					String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
					formBean.setFilePath(fileName);
					request.setAttribute("filePath", fileName);
					formBean.setIsOpenPopUp("1");
					formBean.setChkMobileNoCount("1");
					
					fRes = true;
		        	
		        }
		
			} 
				else
				{
					String err = voObj.getStrMsgString();
					fRes = false;
					throw new Exception(err);
				}
		
		} 
		catch (Exception e)
		{
			String err = e.getMessage();
			HisException eObj = new HisException("Billing Transaction","WalletCashCollectionTransDATA.insertOfflineReceiptAdvance()-->", err);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 
		finally
		{
			bo = null;
			voObj = null;
		}

		return fRes;

	}
	
	
	public static boolean CLOSEACCOUNT(PatWalletTransFB formBean, HttpServletRequest request)
	{

		boolean fRes = false;

		PatWalletTransBO bo = null;
		PatWalletTransVO voObj = null;

		try
		{

			bo = new PatWalletTransBO();

			formBean
					.setStrOffLineClientPatientNo(formBean.getStrOffLineClientDetailsHidden().replace("^", "#").split("#")[0]);
			formBean.setStrOffLineAdmissionNo(formBean.getStrAdmissionDtlHidVal().replace("^", "#").split("#")[0]);

			String[] strWardDtls = formBean.getStrOffLineWard().replace("^", "#").split("#");

			formBean.setStrOffLineWard(strWardDtls[0]);
			//formBean.setStrOffLineIpdChargeTypeId(strWardDtls[1]);

			formBean.setStrOffLineEpisode(formBean.getStrOffLineEpisode().replace("^", "#").split("#")[0]);
			formBean.setStrOffLineAdvanceAmount(formBean.getStrPartpayment());
            
			formBean.setStrOffLineApprovedBy(formBean.getStrApprovedByCombo());
			formBean.setStrOffLineRemarks(formBean.getStrRemarksCombo2());
         
			voObj = (PatWalletTransVO) TransferObjectFactory.populateData("billing.transactions.PatWalletTransVO", formBean);
			voObj.setStrOffLineSpecialWard("0");
			voObj.setStrAccountStatus(formBean.getStrAccountStatus());
			voObj.setStrWalletAccountNo(formBean.getStrWalletAccountNo());
			voObj.setStrCrNo(formBean.getStrCRNum());
			
			System.out.println("formBean.getStrCatgoryCode()*****************"+formBean.getStrCRNum());
			voObj.setStrCatgoryCode(formBean.getStrCatgoryCode());
			voObj.setRefundAmount(formBean.getRefundAmount());
			
			System.out.println("voObj.setRefundAmount"+voObj.getRefundAmount());
			
			bo.CLOSEACCOUNT(voObj);

			formBean.setStrBillNo(voObj.getStrBillNo());
			formBean.setStrReceiptNo("1");
			formBean.setChkMobileNoCount("1");
			//formBean.setStrCrNo("");
			if (!voObj.getStrMsgType().equals("1"))
			{

				PrintHLP.WALLET(formBean.getStrBillNo(), formBean.getStrOffLineBillingService(), formBean
						.getStrHospitalCode(), formBean.getStrReceiptNo(), request, 0);
				formBean
						.setStrNormalMsg("Account Closed");

				formBean.setStrTempBillNo(formBean.getStrBillNo());
				formBean.setStrTempReceiptNo("1");
				String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
				formBean.setFilePath(fileName);
				request.setAttribute("filePath", fileName);
				formBean.setIsOpenPopUp("1");
				fRes = true;
				

			} else
			{

				String err = voObj.getStrMsgString();

				fRes = false;

				throw new Exception(err);

			}

		} catch (Exception e)
		{

			String err = e.getMessage();

			HisException eObj = new HisException("Billing Transaction",
					"WalletCollectionTransDATA.CloseAccount()-->", err);

			formBean
					.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		} finally
		{

			bo = null;
			voObj = null;

		}

		return fRes;

	}
	public static void GetCrFromWalletNo(PatWalletTransFB formBean, HttpServletRequest request) 
	{
		PatWalletTransBO bo = null;
		PatWalletTransVO voObj = null;
		try	
		{	
			bo = new PatWalletTransBO();
			voObj = (PatWalletTransVO) TransferObjectFactory.populateData("billing.transactions.PatWalletTransVO", formBean);
			voObj.setStrOffLineSpecialWard("0");
			voObj.setStrAccountStatus(formBean.getStrAccountStatus());
			voObj.setStrWalletNo(formBean.getStrWalletNo());
			bo.GetCrFromWalletNo(voObj);
			if (!voObj.getStrMsgType().equals("1"))
			{
				if(voObj.getStrCrNo() != null  && voObj.getStrCrNo()!="")
				formBean.setStrCrNo(voObj.getStrCrNo());	
			} 
			else
			{
				String err = voObj.getStrMsgString();
				throw new Exception(err);
			}	
		} 
		catch (Exception e)
		{	
			String err = e.getMessage();	
			HisException eObj = new HisException("Billing Transaction","WalletCollectionTransDATA.CloseAccount()-->", err);	
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");	
			eObj = null;	
		} 
		finally
		{	
			bo = null;
			voObj = null;	
		}
	}
	public static void showReport(PatWalletTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				
				String strmsgText = "";
				
				Map<String, Object> params = new HashMap<String, Object>();
			try {
				
					String strReportName = "Wallet Transaction History";								
					String strHospitalCode = formBean.getStrHospitalCode();
					String walAccNo = formBean.getStrWalletAccountNo();
					
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					if(formBean.getStrReportingFormat().equalsIgnoreCase("text"))
					{
						reportFormat = "html";
					}
					else
					{
						reportFormat = "pdf";
					}
					boolean footerVisible = true;
					
					
						
						
						String reportPath="";
						
						reportPath = "/billing/reports/patWallet_transSum.rptdesign";
						
						System.out.println("report path"+reportPath);
						
						//String strRcptNo=formBean.getStrRcptNo();
			                
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", "");
						params.put("hosp_Code", strHospitalCode);
						params.put("walAccNo", walAccNo);
												
					/*	ts.displayReport(request, response, reportPath, reportFormat,
								params,strHospitalCode);*/
						
						ts.displayReportBilling(request, response, reportPath, reportFormat,
								params,strHospitalCode);
						
					
				} catch (Exception e) {
					strmsgText = "billing.transactions.PatWalletTransDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"PatWalletTransDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;
				}
			}
}

