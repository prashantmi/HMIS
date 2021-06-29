package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericFormBean;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class ClientApprovalDetailsTransFB extends GenericFormBean 
{

	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strApprovalStatus = "";
	private String strMsgType = "0";
	private String strHidden = null;
	private String strHospitalCode = "0"; 
	private String strClientPatientNo = "";
	private String strClientPatNo = "0";
	private String strClientPatientSrNo = "0";
	private String strCrNo = "";
	private String strClientName = "0";
	private String strClientAddress = "";
	private String strCardNumber = "";
	private String strCardHolderName = "";
	private String strCardExpiryDate = "";
	private String strAuthenticationNumber = "";
	private String strAuthenticationDate = "";
	private String strEffectiveDate = "";
	private String strExpiryDate = "";
	private String strApprovalFor = "0";
	private String strIsGroupWise = "0";
	private String strSanctionAmount = "";
	private String strClientExpenseAmount = "";
	private String strAmountReceivedFromClient = "";
	private String strAmountReceivedFromPatient = "";
	private String strRefundAmount = "";
	private String strPaymentMode = "";
	private String strRemarks = "";
	private String strRegistrationNo = "";
	private String strClientType = "";
	private String strContactPerson = "";
	private String strEmailId = "";
	private String strContactNo = "";
	private String strChk = "";
	private String strErrMsg ="";
	private String strWarning = "";
	private String strMsg = "";
	private String strOneTimeService = "";
	private String strPreSancAmt ="";
	private String strCurrntSancAmt ="";
	private String strNewAuthenticationNumber ="";
	private String strNewAuthenticationDate ="";
	private String strNewExpiryDate ="";
	private String strNewSanctionAmount = "";
	private String strSeatId = "";
	private String strViewDtl="";
	private String strCltNo = "";
	private String strCrdHlderName = "";
	private String strPatientStatus = "";
	private String strClientNo = ""; 
	private String strCrNoStatus = "1"; 
	
	

	private String strPatientDetailsView = "";
	private String strGroupTempCode = "0";
    private String strClientDetail ="";
	private String[] strCltPatSlNum = null;
	 private String[] strAuthNumber = null;
	 private String[] strAuthDate = null;
	 private String[] strTotSancAmt = null;
	 private String[] strExpAmt = null;
	 private String[] strExpyDate = null;
	 private String[] strClientAmt = null;
	 private String[] strPrevSancAmt = null;
	 private String[] strCurrSancAmt = null;
	
	
	
	
	private String strGroupName[] = null;
	private String strSancAmt[] = null;
	private String strTariffWise[] = null;
	private String strTariffDetails[] = null;

	private String strClientNameValues = "";
	private String strApprovalForValues = "";
	private String strGroupNameValues = "";
	private String strTariffValues = "";
	private String strApprovalDtlsValues = "";
	/*-----------DropDown Utility-----------*/
	 private String strOffLineTariffDiscountUnit = "";
	 private String strOffLineTariffDiscountType ="";
	 private String strOffLineTariffDiscountBy ="";
	 private String strOfflineDiscountApprovedByDetails ="";
	 private String strOffLineTariffDiscountReason = "";
	 private String strOfflineDiscountRemarksDetails="";
	 private String strOffLineTariffDiscountDate="";
	 private String strOnlinePaymentMode = "";
	 private String strOnlinePaymentDtls = "";
	 private String strOnlineAmount = "";
	 private String strOfflineGroupDetails ="";
	 private String strOfflineTariffDetails = "";
	 private WebRowSet OfflinePackageGroup = null;
	 private WebRowSet OfflineTariffList = null;
	 private String strOffLineWard = null;
	 private String strOffLineTariffUnitTempId = "";
	 private WebRowSet OfflineDiscountRemarks = null;

	 private WebRowSet OfflineTariffUnit = null;
	
	 private WebRowSet OfflineDiscountApprovedBy = null;
	 private String strGroupId = "";
	 private String strOffLineTreatmentCategory ="";
	 private String strStrOffLineWard = "";
	 private WebRowSet strClientList = null;
	 private WebRowSet strGroupNameCombo = null;
	 private String strOfflineTariffName = "";
	 private String strOfflineTariffDetailsHidden = "";
	 private String strOfflineTariffQty = "";
	 private String strOfflineTariffUnit = "";
	 private String strCtDate = null;
	 
	 private String OPD = "0";
	 private String IPD = "0";
	 private String EME = "0";
	 



	public String getStrCtDate() 
	 { // function for gettin SYSDATE
			HisUtil util = new HisUtil("ClientApprovalDetailsTransFB", "ClientApprovalDetailsTransFB");
			try {
				strCtDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("ClientApprovalDetailsTransFB", "ClientApprovalDetailsTransFB",
						"ClientApprovalDetailsTransFB.getStrCtDate()-->" + e.getMessage());
			}

			return strCtDate;
		}
	 
	 
	public String getStrOfflineTariffName() {
		return strOfflineTariffName;
	}

	public void setStrOfflineTariffName(String strOfflineTariffName) {
		this.strOfflineTariffName = strOfflineTariffName;
	}

	public String getStrOfflineTariffDetailsHidden() {
		return strOfflineTariffDetailsHidden;
	}

	public void setStrOfflineTariffDetailsHidden(
			String strOfflineTariffDetailsHidden) {
		this.strOfflineTariffDetailsHidden = strOfflineTariffDetailsHidden;
	}

	public String getStrOfflineTariffQty() {
		return strOfflineTariffQty;
	}

	public void setStrOfflineTariffQty(String strOfflineTariffQty) {
		this.strOfflineTariffQty = strOfflineTariffQty;
	}

	public String getStrOfflineTariffUnit() {
		return strOfflineTariffUnit;
	}

	public void setStrOfflineTariffUnit(String strOfflineTariffUnit) {
		this.strOfflineTariffUnit = strOfflineTariffUnit;
	}

	public WebRowSet getOfflinePackageGroup() {
		return OfflinePackageGroup;
	}

	public void setOfflinePackageGroup(WebRowSet offlinePackageGroup) {
		OfflinePackageGroup = offlinePackageGroup;
	}

	public WebRowSet getOfflineTariffList() {
		return OfflineTariffList;
	}

	public void setOfflineTariffList(WebRowSet offlineTariffList) {
		OfflineTariffList = offlineTariffList;
	}

	public WebRowSet getOfflineDiscountRemarks() {
		return OfflineDiscountRemarks;
	}

	public void setOfflineDiscountRemarks(WebRowSet offlineDiscountRemarks) {
		OfflineDiscountRemarks = offlineDiscountRemarks;
	}
	
	public WebRowSet getOfflineTariffUnit() {
		return OfflineTariffUnit;
	}

	public void setOfflineTariffUnit(WebRowSet offlineTariffUnit) {
		OfflineTariffUnit = offlineTariffUnit;
	}

	public WebRowSet getOfflineDiscountApprovedBy() {
		return OfflineDiscountApprovedBy;
	}

	public void setOfflineDiscountApprovedBy(WebRowSet offlineDiscountApprovedBy) {
		OfflineDiscountApprovedBy = offlineDiscountApprovedBy;
	}

	public String getStrGroupId() {
		return strGroupId;
	}

	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}

	public String getStrOffLineTreatmentCategory() {
		return strOffLineTreatmentCategory;
	}

	public void setStrOffLineTreatmentCategory(String strOffLineTreatmentCategory) {
		this.strOffLineTreatmentCategory = strOffLineTreatmentCategory;
	}

	public String getStrStrOffLineWard() {
		return strStrOffLineWard;
	}

	public void setStrStrOffLineWard(String strStrOffLineWard) {
		this.strStrOffLineWard = strStrOffLineWard;
	}

	public String getStrOfflineTariffDetails() {
		return strOfflineTariffDetails;
	}

	public void setStrOfflineTariffDetails(String strOfflineTariffDetails) {
		this.strOfflineTariffDetails = strOfflineTariffDetails;
	}

	public String getStrOfflineGroupDetails() {
		return strOfflineGroupDetails;
	}

	public void setStrOfflineGroupDetails(String strOfflineGroupDetails) {
		this.strOfflineGroupDetails = strOfflineGroupDetails;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}

	public String getStrClientName() {
		return strClientName;
	}

	public void setStrClientName(String strClientName) {
		this.strClientName = strClientName;
	}

	public String getStrClientAddress() {
		return strClientAddress;
	}

	public void setStrClientAddress(String strClientAddress) {
		this.strClientAddress = strClientAddress;
	}

	public String getStrCardNumber() {
		return strCardNumber;
	}

	public void setStrCardNumber(String strCardNumber) {
		this.strCardNumber = strCardNumber;
	}

	public String getStrCardHolderName() {
		return strCardHolderName;
	}

	public void setStrCardHolderName(String strCardHolderName) {
		this.strCardHolderName = strCardHolderName;
	}

	public String getStrCardExpiryDate() {
		return strCardExpiryDate;
	}

	public void setStrCardExpiryDate(String strCardExpiryDate) {
		this.strCardExpiryDate = strCardExpiryDate;
	}

	public String getStrAuthenticationNumber() {
		return strAuthenticationNumber;
	}

	public void setStrAuthenticationNumber(String strAuthenticationNumber) {
		this.strAuthenticationNumber = strAuthenticationNumber;
	}

	public String getStrAuthenticationDate() {
		return strAuthenticationDate;
	}

	public void setStrAuthenticationDate(String strAuthenticationDate) {
		this.strAuthenticationDate = strAuthenticationDate;
	}

	public String getStrEffectiveDate() {
		return strEffectiveDate;
	}

	public void setStrEffectiveDate(String strEffectiveDate) {
		this.strEffectiveDate = strEffectiveDate;
	}

	public String getStrExpiryDate() {
		return strExpiryDate;
	}

	public void setStrExpiryDate(String strExpiryDate) {
		this.strExpiryDate = strExpiryDate;
	}

	public String getStrApprovalFor() {
		return strApprovalFor;
	}

	public void setStrApprovalFor(String strApprovalFor) {
		this.strApprovalFor = strApprovalFor;
	}

	public String getStrIsGroupWise() {
		return strIsGroupWise;
	}

	public void setStrIsGroupWise(String strIsGroupWise) {
		this.strIsGroupWise = strIsGroupWise;
	}

	public String getStrSanctionAmount() {
		return strSanctionAmount;
	}

	public void setStrSanctionAmount(String strSanctionAmount) {
		this.strSanctionAmount = strSanctionAmount;
	}

	public String[] getStrGroupName() {
		return strGroupName;
	}

	public void setStrGroupName(String[] strGroupName) {
		this.strGroupName = strGroupName;
	}

	public String[] getStrSancAmt() {
		return strSancAmt;
	}

	public void setStrSancAmt(String[] strSancAmt) {
		this.strSancAmt = strSancAmt;
	}

	public String[] getStrTariffWise() {
		return strTariffWise;
	}

	public void setStrTariffWise(String[] strTariffWise) {
		this.strTariffWise = strTariffWise;
	}

	public String getStrClientNameValues() {
		return strClientNameValues;
	}

	public void setStrClientNameValues(String strClientNameValues) {
		this.strClientNameValues = strClientNameValues;
	}

	public String getStrApprovalForValues() {
		return strApprovalForValues;
	}

	public void setStrApprovalForValues(String strApprovalForValues) {
		this.strApprovalForValues = strApprovalForValues;
	}

	public String getStrGroupNameValues() {
		return strGroupNameValues;
	}

	public void setStrGroupNameValues(String strGroupNameValues) {
		this.strGroupNameValues = strGroupNameValues;
	}

	public String[] getStrTariffDetails() {
		return strTariffDetails;
	}

	public void setStrTariffDetails(String[] strTariffDetails) {
		this.strTariffDetails = strTariffDetails;
	}

	public String getStrGroupTempCode() {
		return strGroupTempCode;
	}

	public void setStrGroupTempCode(String strGroupTempCode) {
		this.strGroupTempCode = strGroupTempCode;
	}

	public String getStrTariffValues() {
		return strTariffValues;
	}

	public void setStrTariffValues(String strTariffValues) {
		this.strTariffValues = strTariffValues;
	}

	public String getStrClientPatientNo() {
		return strClientPatientNo;
	}

	public void setStrClientPatientNo(String strClientPatientNo) {
		this.strClientPatientNo = strClientPatientNo;
	}

	public String getStrClientPatientSrNo() {
		return strClientPatientSrNo;
	}

	public void setStrClientPatientSrNo(String strClientPatientSrNo) {
		this.strClientPatientSrNo = strClientPatientSrNo;
	}

	public String getStrClientExpenseAmount() {
		return strClientExpenseAmount;
	}

	public void setStrClientExpenseAmount(String strClientExpenseAmount) {
		this.strClientExpenseAmount = strClientExpenseAmount;
	}

	public String getStrAmountReceivedFromClient() {
		return strAmountReceivedFromClient;
	}

	public void setStrAmountReceivedFromClient(
			String strAmountReceivedFromClient) {
		this.strAmountReceivedFromClient = strAmountReceivedFromClient;
	}

	public String getStrAmountReceivedFromPatient() {
		return strAmountReceivedFromPatient;
	}

	public void setStrAmountReceivedFromPatient(
			String strAmountReceivedFromPatient) {
		this.strAmountReceivedFromPatient = strAmountReceivedFromPatient;
	}

	public String getStrRefundAmount() {
		return strRefundAmount;
	}

	public void setStrRefundAmount(String strRefundAmount) {
		this.strRefundAmount = strRefundAmount;
	}

	public String getStrPaymentMode() {
		return strPaymentMode;
	}

	public void setStrPaymentMode(String strPaymentMode) {
		this.strPaymentMode = strPaymentMode;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrApprovalDtlsValues() {
		return strApprovalDtlsValues;
	}

	public void setStrApprovalDtlsValues(String strApprovalDtlsValues) {
		this.strApprovalDtlsValues = strApprovalDtlsValues;
	}

	public String getStrPatientDetailsView() {
		return strPatientDetailsView;
	}

	public void setStrPatientDetailsView(String strPatientDetailsView) {
		this.strPatientDetailsView = strPatientDetailsView;
	}

	public String getStrRegistrationNo() {
		return strRegistrationNo;
	}

	public void setStrRegistrationNo(String strRegistrationNo) {
		this.strRegistrationNo = strRegistrationNo;
	}

	public String getStrClientType() {
		return strClientType;
	}

	public void setStrClientType(String strClientType) {
		this.strClientType = strClientType;
	}

	public String getStrContactPerson() {
		return strContactPerson;
	}

	public void setStrContactPerson(String strContactPerson) {
		this.strContactPerson = strContactPerson;
	}

	public String getStrEmailId() {
		return strEmailId;
	}

	public void setStrEmailId(String strEmailId) {
		this.strEmailId = strEmailId;
	}

	public String getStrContactNo() {
		return strContactNo;
	}

	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrOffLineTariffDiscountUnit() {
		return strOffLineTariffDiscountUnit;
	}

	public void setStrOffLineTariffDiscountUnit(String strOffLineTariffDiscountUnit) {
		this.strOffLineTariffDiscountUnit = strOffLineTariffDiscountUnit;
	}

	public String getStrOffLineTariffDiscountType() {
		return strOffLineTariffDiscountType;
	}

	public void setStrOffLineTariffDiscountType(String strOffLineTariffDiscountType) {
		this.strOffLineTariffDiscountType = strOffLineTariffDiscountType;
	}

	public String getStrOffLineTariffDiscountBy() {
		return strOffLineTariffDiscountBy;
	}

	public void setStrOffLineTariffDiscountBy(String strOffLineTariffDiscountBy) {
		this.strOffLineTariffDiscountBy = strOffLineTariffDiscountBy;
	}

	public String getStrOfflineDiscountApprovedByDetails() {
		return strOfflineDiscountApprovedByDetails;
	}

	public void setStrOfflineDiscountApprovedByDetails(
			String strOfflineDiscountApprovedByDetails) {
		this.strOfflineDiscountApprovedByDetails = strOfflineDiscountApprovedByDetails;
	}

	public String getStrOffLineTariffDiscountReason() {
		return strOffLineTariffDiscountReason;
	}

	public void setStrOffLineTariffDiscountReason(
			String strOffLineTariffDiscountReason) {
		this.strOffLineTariffDiscountReason = strOffLineTariffDiscountReason;
	}

	public String getStrOfflineDiscountRemarksDetails() {
		return strOfflineDiscountRemarksDetails;
	}

	public void setStrOfflineDiscountRemarksDetails(
			String strOfflineDiscountRemarksDetails) {
		this.strOfflineDiscountRemarksDetails = strOfflineDiscountRemarksDetails;
	}

	public String getStrOffLineTariffDiscountDate() {
		return strOffLineTariffDiscountDate;
	}

	public void setStrOffLineTariffDiscountDate(String strOffLineTariffDiscountDate) {
		this.strOffLineTariffDiscountDate = strOffLineTariffDiscountDate;
	}

	public String getStrOnlinePaymentMode() {
		return strOnlinePaymentMode;
	}

	public void setStrOnlinePaymentMode(String strOnlinePaymentMode) {
		this.strOnlinePaymentMode = strOnlinePaymentMode;
	}

	public String getStrOnlinePaymentDtls() {
		return strOnlinePaymentDtls;
	}

	public void setStrOnlinePaymentDtls(String strOnlinePaymentDtls) {
		this.strOnlinePaymentDtls = strOnlinePaymentDtls;
	}

	public String getStrOnlineAmount() {
		return strOnlineAmount;
	}

	public void setStrOnlineAmount(String strOnlineAmount) {
		this.strOnlineAmount = strOnlineAmount;
	}

	public String getStrHidden() {
		return strHidden;
	}

	public void setStrHidden(String strHidden) {
		this.strHidden = strHidden;
	}

	public String getStrOffLineWard() {
		return strOffLineWard;
	}

	public void setStrOffLineWard(String strOffLineWard) {
		this.strOffLineWard = strOffLineWard;
	}

	public String getStrOffLineTariffUnitTempId() {
		return strOffLineTariffUnitTempId;
	}

	public void setStrOffLineTariffUnitTempId(String strOffLineTariffUnitTempId) {
		this.strOffLineTariffUnitTempId = strOffLineTariffUnitTempId;
	}

	public WebRowSet getStrGroupNameCombo() {
		return strGroupNameCombo;
	}

	public void setStrGroupNameCombo(WebRowSet strGroupNameCombo) {
		this.strGroupNameCombo = strGroupNameCombo;
	}


	public String getStrChk() {
		return strChk;
	}


	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}
	
	public String getStrErrMsg() {
		return strErrMsg;
	}


	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}


	public String getStrWarning() {
		return strWarning;
	}


	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}


	public String getStrMsg() {
		return strMsg;
	}


	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}


	public WebRowSet getStrClientList() {
		return strClientList;
	}


	public void setStrClientList(WebRowSet strClientList) {
		this.strClientList = strClientList;
	}


	public String getStrApprovalStatus() {
		return strApprovalStatus;
	}


	public void setStrApprovalStatus(String strApprovalStatus) {
		this.strApprovalStatus = strApprovalStatus;
	}


	public String getOPD() {
		return OPD;
	}


	public void setOPD(String opd) {
		OPD = opd;
	}


	public String getIPD() {
		return IPD;
	}


	public void setIPD(String ipd) {
		IPD = ipd;
	}


	public String getEME() {
		return EME;
	}


	public void setEME(String eme) {
		EME = eme;
	}


	public String getStrOneTimeService() {
		return strOneTimeService;
	}


	public void setStrOneTimeService(String strOneTimeService) {
		this.strOneTimeService = strOneTimeService;
	}


	public String getStrPreSancAmt() {
		return strPreSancAmt;
	}


	public void setStrPreSancAmt(String strPreSancAmt) {
		this.strPreSancAmt = strPreSancAmt;
	}


	public String getStrCurrntSancAmt() {
		return strCurrntSancAmt;
	}


	public void setStrCurrntSancAmt(String strCurrntSancAmt) {
		this.strCurrntSancAmt = strCurrntSancAmt;
	}


	public String getStrNewAuthenticationNumber() {
		return strNewAuthenticationNumber;
	}


	public void setStrNewAuthenticationNumber(String strNewAuthenticationNumber) {
		this.strNewAuthenticationNumber = strNewAuthenticationNumber;
	}


	public String getStrNewAuthenticationDate() {
		return strNewAuthenticationDate;
	}


	public void setStrNewAuthenticationDate(String strNewAuthenticationDate) {
		this.strNewAuthenticationDate = strNewAuthenticationDate;
	}


	public String getStrNewExpiryDate() {
		return strNewExpiryDate;
	}


	public void setStrNewExpiryDate(String strNewExpiryDate) {
		this.strNewExpiryDate = strNewExpiryDate;
	}


	public String getStrNewSanctionAmount() {
		return strNewSanctionAmount;
	}


	public void setStrNewSanctionAmount(String strNewSanctionAmount) {
		this.strNewSanctionAmount = strNewSanctionAmount;
	}


	public String getStrSeatId() {
		return strSeatId;
	}


	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}


	public String getStrClientPatNo() {
		return strClientPatNo;
	}


	public void setStrClientPatNo(String strClientPatNo) {
		this.strClientPatNo = strClientPatNo;
	}


	public String[] getStrCltPatSlNum() {
		return strCltPatSlNum;
	}


	public void setStrCltPatSlNum(String[] strCltPatSlNum) {
		this.strCltPatSlNum = strCltPatSlNum;
	}


	public String[] getStrAuthNumber() {
		return strAuthNumber;
	}


	public void setStrAuthNumber(String[] strAuthNumber) {
		this.strAuthNumber = strAuthNumber;
	}


	public String[] getStrAuthDate() {
		return strAuthDate;
	}


	public void setStrAuthDate(String[] strAuthDate) {
		this.strAuthDate = strAuthDate;
	}


	public String[] getStrTotSancAmt() {
		return strTotSancAmt;
	}


	public void setStrTotSancAmt(String[] strTotSancAmt) {
		this.strTotSancAmt = strTotSancAmt;
	}


	public String[] getStrExpAmt() {
		return strExpAmt;
	}


	public void setStrExpAmt(String[] strExpAmt) {
		this.strExpAmt = strExpAmt;
	}


	public String[] getStrExpyDate() {
		return strExpyDate;
	}


	public void setStrExpyDate(String[] strExpyDate) {
		this.strExpyDate = strExpyDate;
	}


	public String[] getStrClientAmt() {
		return strClientAmt;
	}


	public void setStrClientAmt(String[] strClientAmt) {
		this.strClientAmt = strClientAmt;
	}


	public String[] getStrPrevSancAmt() {
		return strPrevSancAmt;
	}


	public void setStrPrevSancAmt(String[] strPrevSancAmt) {
		this.strPrevSancAmt = strPrevSancAmt;
	}


	public String[] getStrCurrSancAmt() {
		return strCurrSancAmt;
	}


	public void setStrCurrSancAmt(String[] strCurrSancAmt) {
		this.strCurrSancAmt = strCurrSancAmt;
	}


	public String getStrClientDetail() {
		return strClientDetail;
	}


	public void setStrClientDetail(String strClientDetail) {
		this.strClientDetail = strClientDetail;
	}


	public String getStrViewDtl() {
		return strViewDtl;
	}


	public void setStrViewDtl(String strViewDtl) {
		this.strViewDtl = strViewDtl;
	}


	public String getStrCltNo() {
		return strCltNo;
	}


	public void setStrCltNo(String strCltNo) {
		this.strCltNo = strCltNo;
	}


	public String getStrCrdHlderName() {
		return strCrdHlderName;
	}


	public void setStrCrdHlderName(String strCrdHlderName) {
		this.strCrdHlderName = strCrdHlderName;
	}


	public String getStrPatientStatus() {
		return strPatientStatus;
	}


	public void setStrPatientStatus(String strPatientStatus) {
		this.strPatientStatus = strPatientStatus;
	}


	/**
	 * @return the strClientNo
	 */
	public String getStrClientNo() {
		return strClientNo;
	}


	/**
	 * @param strClientNo the strClientNo to set
	 */
	public void setStrClientNo(String strClientNo) {
		this.strClientNo = strClientNo;
	}


	/**
	 * @return the strCrNoStatus
	 */
	public String getStrCrNoStatus() {
		return strCrNoStatus;
	}


	/**
	 * @param strCrNoStatus the strCrNoStatus to set
	 */
	public void setStrCrNoStatus(String strCrNoStatus) {
		this.strCrNoStatus = strCrNoStatus;
	}
}
