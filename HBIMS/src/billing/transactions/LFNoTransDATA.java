package billing.transactions;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.utility.HisUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import org.apache.struts.upload.FormFile;

import billing.BillConfigUtil;
import billing.HLPTariffDtl;
import billing.PrintHLP;

// TODO: Auto-generated Javadoc
/**
 * The Class CashCollectionTransDATA.
 */
public class LFNoTransDATA
{

	/**
	 * method used to check whether the System's IP Address is Registered in
	 * Counter Master or Not.
	 * 
	 * @param request
	 * @param formBean
	 */
	public static String checkCounterStatus(HttpServletRequest request, LFNoTransFB formBean)
	{

		LFNoTransBO bo = null;
		LFNoTransVO voObj = null;
		String strCounterId ="";
		try
		{
			formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

			bo = new LFNoTransBO();
			voObj = new LFNoTransVO();

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
	public static boolean init(HttpServletRequest request, LFNoTransFB formBean)
	{
		boolean bResultVal = true;
		String strCrNo = formBean.getStrCrNo();

		LFNoTransBO bo = null;
		LFNoTransVO voObj = null;
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
			
			bo = new LFNoTransBO();
			voObj = new LFNoTransVO();
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

			LFNoTransDATA.initOffLineDetails(formBean, bo, voObj);

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
				HisException eObj = new HisException("Billing", "LFNoTransDATA->init()", msgStr);
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

	public static void preInitOffLineDetails(LFNoTransFB formBean)
	{
		LFNoTransBO bo = null;
		LFNoTransVO voObj = null;

		HisUtil util = null;
		BillConfigUtil bcu = null;

		try
		{
			bo = new LFNoTransBO();
			voObj = new LFNoTransVO();
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			bcu = new BillConfigUtil(voObj.getStrHospitalCode());

			voObj.setStrOffLineHospitalService("1");//1-OPD,2-IPD,3-Emg
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
	public static void initOffLineDetails(LFNoTransFB formBean, LFNoTransBO bo,LFNoTransVO voObj)
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
			voObj.setStrOffLineHospitalService(formBean.getStrOffLineHospitalService());
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

			if(voObj.getStrLFAccountNo() !=null)
			{
				formBean.setStrLFAccountNo(voObj.getStrLFAccountNo());
				formBean.setStrLFAccountOpenDate(voObj.getStrLFAccountOpenDate());
				formBean.setStrLFDepositedAmount(voObj.getStrLFDepositedAmount());
				formBean.setStrLFBalanceAmount(voObj.getStrLFBalanceAmount());
				formBean.setStrLFAccountStatus(voObj.getStrLFAccountStatus());
				if(voObj.getStrAccountStatus().equals("3"))
			      {
			temp1 = LFNoTransHLP.LFAccountDetail(voObj);
			      }
				formBean.setStrAccountDetailsView(temp1);
			      
			}
			

			formBean.setStrAccountDetailsView(temp1);
			if(voObj.getStrAccountStatus().equals("4") || voObj.getStrAccountStatus().equals("2"))
		      {
				System.out.println();
				
            	temp= LFNoTransHLP.getLFAccountSummary(voObj.getWsLFAccountSummary(),voObj.getStrAccountStatus());
        
			System.out.println("temp is::"+temp);
			formBean.setStrLFAccountSummary(temp);
			}
			if (voObj.getAdmEpisodeTreatCatDeptDtls() != null && voObj.getAdmEpisodeTreatCatDeptDtls().size() != 0)
			{
				WebRowSet ws=voObj.getAdmEpisodeTreatCatDeptDtls();
				if(voObj.getAdmEpisodeTreatCatDeptDtls().next())
				{
					voObj.setStrOffLineRaisingDepartment(ws.getString(1));
					voObj.setStrOffLineEpisode(ws.getString(2));
					selectedTreatmentCat=ws.getString(4)+"^"+ws.getString(5);					
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
					strPartOrAdvanceDtls = LFNoTransHLP.getOffLineAdvanceDetailsView(voObj);
				} 
				else
				{
					strPartOrAdvanceDtls = LFNoTransHLP.getOffLinePartPaymentDetailsView(voObj);
				}
				formBean.setStrPartPayAdvanceAmountDetails(strPartOrAdvanceDtls);
			}
			if (voObj.getStrOffLineBillingService().equals("20") && voObj.getStrOffLineRequestType().equals("2"))
			{
				String strPartOrAdvanceDtls = "";
				strPartOrAdvanceDtls = LFNoTransHLP.getOffLinePartPaymentDetailsView(voObj);

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
	 * writes populated Episode Combo box (Option List) based on Cr Number and
	 * Department Code as Ajax response.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void getEpisodeDetails(HttpServletRequest request, HttpServletResponse response)
	{

		LFNoTransBO bo = null;
		LFNoTransVO voObj = null;
		HisUtil util = null;

		try
		{

			bo = new LFNoTransBO();
			voObj = new LFNoTransVO();

			String strCrNo = request.getParameter("crNo");
			String strDeptCode = request.getParameter("deptCode");

			if (strCrNo == null)
				strCrNo = "0";
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrCrNo(strCrNo);
			voObj.setStrOffLineRaisingDepartment(strDeptCode);
			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			System.out.println("DEPT BEFOREs"+ voObj.getStrOffLineRaisingDepartment());
			bo.getEpisodeDetails(voObj);

			if (voObj.getStrMsgType().equals("1"))
			{
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("Billing Transaction", "CashCollectionTrans");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getOfflineEpisodeList().size() != 0)
			{

				temp = util.getOptionValue(voObj.getOfflineEpisodeList(), voObj.getStrOffLineEpisode(), "", true);

				String temp1[] = voObj.getStrOffLineEpisode().replace("@", "#").split("#");

				if (!temp1[1].equals("") || !temp1[1].equals("0"))
				{

					voObj.setStrOffLineTreatmentCategory(temp1[1]);
				}

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp + "@" + voObj.getStrOffLineTreatmentCategory());

		} catch (Exception e)
		{
			String strmsgText = e.getMessage();
			new HisException("Billing", "CashCollectionTransDATA->getEpisodeDetails()", strmsgText);

		} finally
		{

			bo = null;

			voObj = null;

			util = null;
		}

	}

	/**
	 * writes populated Part Payment or Account Details based on Hospital
	 * Service, Request Type, Bill Service, Treatment Category and Ward Code as
	 * Ajax response.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void getPartPaymentOrAccountDtls(HttpServletRequest request, HttpServletResponse response)
	{

		LFNoTransBO bo = null;
		LFNoTransVO voObj = null;

		try
		{
			bo = new LFNoTransBO();
			voObj = new LFNoTransVO();

			String strHService = request.getParameter("hService");
			String strRequestType = request.getParameter("requestType");
			String strBillService = request.getParameter("billService");
			String strTreatmentCat = request.getParameter("treatmentCat");
			String strWard = request.getParameter("ward");
			String strCrNo = request.getParameter("strCrNo");
			String strSplWard = request.getParameter("strSplWard");

			if (strHService == null)
				strHService = "0";
			if (strRequestType == null)
				strRequestType = "0";
			if (strBillService == null)
				strBillService = "0";
			if (strTreatmentCat == null)
				strTreatmentCat = "0";
			if (strWard == null)
				strWard = "0";
			if (strCrNo == null)
				strCrNo = "0";

			if (strSplWard == null)
				strSplWard = "0";

			voObj.setStrOffLineHospitalService(strHService);
			voObj.setStrOffLineRequestType(strRequestType);
			voObj.setStrOffLineBillingService(strBillService);
			voObj.setStrOffLineTreatmentCategory(strTreatmentCat);
			voObj.setStrOffLineSpecialWard(strSplWard);

			String[] strWardDtls = strWard.replace("^", "#").split("#");

			voObj.setStrOffLineWard(strWardDtls[0]);
			voObj.setStrOffLineIpdChargeTypeId(strWardDtls[1]);

			voObj.setStrIsApprovalRequired(new BillConfigUtil(voObj.getStrHospitalCode()).getGeneralIsApprovalRequired());

			voObj.setStrCrNo(strCrNo);
			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			String temp = "";

			if (bo.isPartPaymentorAdvanceExist(voObj))
			{

				bo.getPartPaymentOrAdvanceDtls(voObj);

				if (voObj.getStrMsgType().equals("1"))
				{
					throw new Exception(voObj.getStrMsgString());

				}

				if (voObj.getStrOffLineBillingService().equals("19"))
				{

					temp = LFNoTransHLP.getOffLineAdvanceDetailsView(voObj);
				} else
				{

					temp = LFNoTransHLP.getOffLinePartPaymentDetailsView(voObj);
				}

			} else
			{

				// Hospital Service - 2(IPD) , Request Type - 2(Refund) and Bill
				// Service - 19(Account)

				if (voObj.getStrOffLineHospitalService().equals("2") && voObj.getStrOffLineRequestType().equals("2") && voObj
						.getStrOffLineBillingService().equals("19"))
				{

				//	bo.getAdmissionCancellationDtl(voObj);

					if (voObj.getAdmissionCancellationDetails() != null && voObj.getAdmissionCancellationDetails().size() > 0)
					{

						temp = LFNoTransHLP.getAdmissionCancellationDetails(voObj, "2");

					} else
					{

						temp = "@Either Account Not Opened or patient has been accepted in ward";

					}
				}

				if (voObj.getStrOffLineHospitalService().equals("2") && voObj.getStrOffLineRequestType().equals("2") && voObj
						.getStrOffLineBillingService().equals("20"))
				{

					bo.getPartPaymentOrAdvanceDtls(voObj);

					if (voObj.getStrMsgType().equals("1"))
					{
						throw new Exception(voObj.getStrMsgString());

					}

					if (voObj.getStrOffLineBillingService().equals("19"))
					{

						temp = LFNoTransHLP.getOffLineAdvanceDetailsView(voObj);
					} else
					{

						temp = LFNoTransHLP.getOffLinePartPaymentDetailsView(voObj);
					}
				}
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e)
		{
			String strmsgText = e.getMessage();
			new HisException("Billing", "CashCollectionTransDATA->getPaymentOrAccountDetails()", strmsgText);

		} finally
		{

			bo = null;

			voObj = null;

		}

	}

	

	public static void getOfflineRaisingDetapartmentDtls(HttpServletRequest request, HttpServletResponse response)
	{

		LFNoTransVO voObj = null;
		LFNoTransBO bo = null;
		HisUtil util = null;

		String temp = "";

		try
		{

			voObj = new LFNoTransVO();
			bo = new LFNoTransBO();
			util = new HisUtil("Cash Collection Trans", "CashCollectionTransDATA.getOfflineRaisingDetapartmentDtls()");

			String strCrNo = request.getParameter("strCrNo");
			String strHospitalService = request.getParameter("strChargeTypeId");
			String strDept = request.getParameter("strDept");

			if (strCrNo == null)
				strCrNo = "0";
			if (strHospitalService == null)
				strHospitalService = "0";

			voObj.setStrCrNo(strCrNo);

			voObj.setStrOffLineHospitalService(strHospitalService);
			voObj.setStrOffLineRaisingDepartment(strDept);

			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			bo.getOfflineRaisingDepartmentDtl(voObj);

			if (voObj.getOfflineRaisingDepartmentList() != null && voObj.getOfflineRaisingDepartmentList().size() > 0)
			{

				temp = util.getOptionValue(voObj.getOfflineRaisingDepartmentList(), voObj.getStrOffLineRaisingDepartment(),
						"", false);

			} else
			{

				temp = "<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e)
		{
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try
			{
				HisException eObj = new HisException("Billing",
						"CashCollectionTransDATA->getOfflineRaisingDetapartmentDtls()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1)
			{
				new HisException("Billing", "IpdBillManagementTransDATA->getOfflineRaisingDetapartmentDtls()", e1
						.getMessage());
			}
		} finally
		{

			voObj = null;
			bo = null;
			util = null;

		}

	}

	/*public static void getOfflineTreatmentCategoryDtls(HttpServletRequest request, HttpServletResponse response)
	{

		LFNoTransVO voObj = null;
		LFNoTransBO bo = null;
		HisUtil util = null;

		String temp = "";

		try
		{

			voObj = new LFNoTransVO();
			bo = new LFNoTransBO();
			util = new HisUtil("Cash Collection Trans", "CashCollectionTransDATA.getOfflineTreatmentCategoryDtls()");

			String strCrNo = request.getParameter("strCrNo");
			String strHospitalService = request.getParameter("strChargeTypeId");
			String strTreatCat = request.getParameter("strTreatCat");

			if (strCrNo == null)
				strCrNo = "0";
			if (strHospitalService == null)
				strHospitalService = "0";

			voObj.setStrCrNo(strCrNo);

			voObj.setStrOffLineHospitalService(strHospitalService);
			voObj.setStrOffLineTreatmentCategory(strTreatCat);

			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			bo.getOfflineTreatmentCategoryDtl(voObj);

			if (voObj.getOfflineTreatmentCategoryList() != null && voObj.getOfflineTreatmentCategoryList().size() > 0)
			{

				temp = util.getOptionValue(voObj.getOfflineTreatmentCategoryList(), voObj.getStrOffLineTreatmentCategory(),
						"", false);

			} else
			{

				temp = "<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e)
		{
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try
			{
				HisException eObj = new HisException("Billing",
						"CashCollectionTransDATA->getOfflineTreatmentCategoryDtls()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1)
			{
				new HisException("Billing", "IpdBillManagementTransDATA->getOfflineTreatmentCategoryDtls()", e1
						.getMessage());
			}
		} finally
		{

			voObj = null;
			bo = null;
			util = null;

		}

	}
*/
	public static void getOfflineWardDtls(HttpServletRequest request, HttpServletResponse response)
	{

		LFNoTransVO voObj = null;
		LFNoTransBO bo = null;
		HisUtil util = null;

		String temp = "";

		try
		{

			voObj = new LFNoTransVO();
			bo = new LFNoTransBO();
			util = new HisUtil("Cash Collection Trans", "CashCollectionTransDATA.getOfflineWardDtls()");

			String strCrNo = request.getParameter("strCrNo");
			String strHospitalService = request.getParameter("strChargeTypeId");
			String strWard = request.getParameter("strWard");

			if (strCrNo == null)
				strCrNo = "0";
			if (strHospitalService == null)
				strHospitalService = "0";

			voObj.setStrCrNo(strCrNo);

			voObj.setStrOffLineHospitalService(strHospitalService);
			voObj.setStrOffLineWard(strWard);

			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			bo.getOfflineWardDtl(voObj);

			if (voObj.getOfflineWardList() != null && voObj.getOfflineWardList().size() > 0)
			{

				temp = util.getOptionValue(voObj.getOfflineWardList(), voObj.getStrOffLineWard(), "", false, false);

			} else
			{

				temp = "<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e)
		{
			//e.printStackTrace();

			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try
			{
				HisException eObj = new HisException("Billing", "CashCollectionTransDATA->getOfflineWardDtls()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1)
			{
				new HisException("Billing", "IpdBillManagementTransDATA->getOfflineWardDtls()", e1.getMessage());
			}
		} finally
		{

			voObj = null;
			bo = null;
			util = null;

		}

	}

	public static void getOfflineSpecialWardDtls(HttpServletRequest request, HttpServletResponse response)
	{

		LFNoTransVO voObj = null;
		LFNoTransBO bo = null;
		HisUtil util = null;

		String temp = "";

		try
		{

			voObj = new LFNoTransVO();
			bo = new LFNoTransBO();
			util = new HisUtil("Cash Collection Trans", "CashCollectionTransDATA.getOfflineSpecialWardDtls()");

			String strDept = request.getParameter("strDept");

			voObj.setStrOffLineRaisingDepartment(strDept);

			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			bo.getOfflineSpecialWardDtl(voObj);

			if (voObj.getOfflineSplWardList() != null && voObj.getOfflineSplWardList().size() > 0)
			{

				temp = util.getOptionValue(voObj.getOfflineSplWardList(), voObj.getStrOffLineSpecialWard(), "", false,
						false);

			} else
			{

				temp = "<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e)
		{
			e.printStackTrace();

			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try
			{
				HisException eObj = new HisException("Billing", "CashCollectionTransDATA->getOfflineSpecialWardDtls()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1)
			{
				new HisException("Billing", "IpdBillManagementTransDATA->getOfflineSpecialWardDtls()", e1.getMessage());
			}
		} finally
		{

			voObj = null;
			bo = null;
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

			HisException eObj = new HisException("Billing", "LFNoTransDATA->rePrint()", e.getMessage());
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";

			eObj = null;

			try
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(response1);
			} catch (IOException e1)
			{

				new HisException("Billing", "LFNoTransDATA->rePrint()", e1.getMessage());
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
	public static boolean insertOfflineReceiptPartPayment(LFNoTransFB formBean,HttpServletRequest request)
	{
		boolean fRes = false;
		LFNoTransBO bo = null;
		LFNoTransVO voObj = null;

		try
		{
			bo = new LFNoTransBO();
			formBean.setStrOffLineClientPatientNo(formBean.getStrOffLineClientDetailsHidden().replace("^", "#").split("#")[0]);
			formBean.setStrOffLineAdmissionNo(formBean.getStrAdmissionDtlHidVal().replace("^", "#").split("#")[0]);
			String[] strWardDtls = formBean.getStrOffLineWard().replace("^", "#").split("#");
			formBean.setStrOffLineWard(strWardDtls[0]);
			formBean.setStrOffLineIpdChargeTypeId(strWardDtls[1]);
			formBean.setStrOffLineEpisode(formBean.getStrOffLineEpisode().replace("^", "#").split("#")[0]);
			formBean.setStrOffLinePartPaymentAmount(formBean.getStrPartpayment());
			formBean.setStrOffLineApprovedBy(formBean.getStrApprovedByCombo());
			formBean.setStrOffLineRemarks(formBean.getStrRemarksCombo2());
			voObj = (LFNoTransVO) TransferObjectFactory.populateData("billing.transactions.LFNoTransVO", formBean);
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
	public static boolean insertLFlineReceiptAdvance(LFNoTransFB formBean, HttpServletRequest request)
	{
		boolean fRes = false;
		LFNoTransBO bo = null;
		LFNoTransVO voObj = null;

		try
		{
			bo = new LFNoTransBO();
			formBean.setStrOffLineClientPatientNo(formBean.getStrOffLineClientDetailsHidden().replace("^", "#").split("#")[0]);
			formBean.setStrOffLineAdmissionNo(formBean.getStrAdmissionDtlHidVal().replace("^", "#").split("#")[0]);
			String[] strWardDtls = formBean.getStrOffLineWard().replace("^", "#").split("#");
			formBean.setStrOffLineWard(strWardDtls[0]);
			formBean.setStrOffLineEpisode(formBean.getStrOffLineEpisode().replace("^", "#").split("#")[0]);
			formBean.setStrOffLineAdvanceAmount(formBean.getStrPartpayment());
			formBean.setStrOffLineApprovedBy(formBean.getStrApprovedByCombo());
			formBean.setStrOffLineRemarks(formBean.getStrRemarksCombo2());

			voObj = (LFNoTransVO) TransferObjectFactory.populateData("billing.transactions.LFNoTransVO", formBean);
			voObj.setStrOffLineSpecialWard("1");
			voObj.setStrAccountStatus(formBean.getStrAccountStatus());
			voObj.setStrLFAccountNo(formBean.getStrLFAccountNo());
		
			bo.insertOfflineReceiptAdvance(voObj);

			formBean.setStrBillNo(voObj.getStrBillNo());
			formBean.setStrReceiptNo("1");
			
			if (!voObj.getStrMsgType().equals("1"))
			{

				PrintHLP.LFADVANCE(formBean.getStrBillNo(), formBean.getStrOffLineBillingService(), formBean.getStrHospitalCode(), formBean.getStrReceiptNo(), request, 0);

				formBean.setStrNormalMsg("Receipt No. '" + formBean.getStrBillNo() + "/1" + "' Generated Successfully for the Collected Amount <img src='../../hisglobal/images/INR.png'> '" + HisUtil
								.getAmountWithDecimal(formBean.getStrAdvacnceAmount(), 2) + "'");

				formBean.setStrTempBillNo(formBean.getStrBillNo());
				formBean.setStrTempReceiptNo("1");	

				String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
				formBean.setFilePath(fileName);
				request.setAttribute("filePath", fileName);
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
			String err = e.getMessage();
			HisException eObj = new HisException("Billing Transaction","LFCashCollectionTransDATA.insertOfflineReceiptAdvance()-->", err);
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
	public static void getOfflineTreatmentCategoryDtls(HttpServletRequest request, HttpServletResponse response)
	{

		LFNoTransVO voObj = null;
		LFNoTransBO bo = null;
		HisUtil util = null;

		String temp = "";

		try
		{

			voObj = new LFNoTransVO();
			bo = new LFNoTransBO();
			util = new HisUtil("Cash Collection Trans", "LFNoTransDATA.getOfflineTreatmentCategoryDtls()");

			String strCrNo = request.getParameter("strCrNo");
			String strHospitalService = request.getParameter("strChargeTypeId");
			String strTreatCat = request.getParameter("strTreatCat");

			if (strCrNo == null)
				strCrNo = "0";
			if (strHospitalService == null)
				strHospitalService = "0";

			voObj.setStrCrNo(strCrNo);

			voObj.setStrOffLineHospitalService(strHospitalService);
			voObj.setStrOffLineTreatmentCategory(strTreatCat);

			voObj.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			bo.getOfflineTreatmentCategoryDtl(voObj);

			if (voObj.getOfflineTreatmentCategoryList() != null && voObj.getOfflineTreatmentCategoryList().size() > 0)
			{

				temp = util.getOptionValue(voObj.getOfflineTreatmentCategoryList(), voObj.getStrOffLineTreatmentCategory(),
						"", false);

			} else
			{

				temp = "<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e)
		{
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try
			{
				HisException eObj = new HisException("Billing",
						"CashCollectionTransDATA->getOfflineTreatmentCategoryDtls()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1)
			{
				new HisException("Billing", "IpdBillManagementTransDATA->getOfflineTreatmentCategoryDtls()", e1
						.getMessage());
			}
		} finally
		{

			voObj = null;
			bo = null;
			util = null;

		}

	}
	
	
	public static boolean CLOSEACCOUNT(LFNoTransFB formBean, HttpServletRequest request)
	{

		boolean fRes = false;

		LFNoTransBO bo = null;
		LFNoTransVO voObj = null;

		try
		{

			bo = new LFNoTransBO();

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
         
			voObj = (LFNoTransVO) TransferObjectFactory.populateData("billing.transactions.LFNoTransVO", formBean);
			voObj.setStrOffLineSpecialWard("1");
			voObj.setStrAccountStatus(formBean.getStrAccountStatus());
			voObj.setStrLFAccountNo(formBean.getStrLFAccountNo());
			voObj.setStrCrNo(formBean.getStrCRNum());
			
			System.out.println("formBean.getStrCatgoryCode()*****************"+formBean.getStrCRNum());
			voObj.setStrCatgoryCode(formBean.getStrCatgoryCode());
			
			bo.CLOSEACCOUNT(voObj);

			formBean.setStrBillNo(voObj.getStrBillNo());
			formBean.setStrReceiptNo("1");
			
			//formBean.setStrCrNo("");
			if (!voObj.getStrMsgType().equals("1"))
			{

				PrintHLP.LFADVANCE(formBean.getStrBillNo(), formBean.getStrOffLineBillingService(), formBean
						.getStrHospitalCode(), formBean.getStrReceiptNo(), request, 0);
				formBean
						.setStrNormalMsg("Account CLosed");

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
					"LFCollectionTransDATA.CloseAccount()-->", err);

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
	public static void GetCrFromLFNo(LFNoTransFB formBean, HttpServletRequest request) 
	{
		LFNoTransBO bo = null;
		LFNoTransVO voObj = null;
		try	
		{	
			bo = new LFNoTransBO();
			voObj = (LFNoTransVO) TransferObjectFactory.populateData("billing.transactions.LFNoTransVO", formBean);
			voObj.setStrOffLineSpecialWard("1");
			voObj.setStrAccountStatus(formBean.getStrAccountStatus());
			voObj.setStrLFNo(formBean.getStrLFNo());
			bo.GetCrFromLFNo(voObj);
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
			HisException eObj = new HisException("Billing Transaction","LFCollectionTransDATA.CloseAccount()-->", err);	
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");	
			eObj = null;	
		} 
		finally
		{	
			bo = null;
			voObj = null;	
		}
	}
}
