package billing.transactions;

import javax.sql.rowset.WebRowSet;
import org.apache.struts.action.ActionForm;

public class BillReconcileTransFB extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String strErrMsg = "";
	private String strMsg = "";

	private String strHospitalCode = "";
	private String strSeatId = "";
	private String strCtDate = "";
	private String strCrNo = "";

	
	private String strBillNoVal = "";
	
	private String strOffLineTariffDiscountBy = "";
	private String strOffLineTariffDiscountReason = "";

	private String strOfflineDiscountApprovedByDetails = "";
	private String strOfflineDiscountRemarksDetails = "";

	private String strReconcilationBy = "";
	private String strReconcilationReason = "";

	private String strOffLineGroup = "0";
	private String strOfflineGroupDetails = "";

	private String strOfflineTariffDetails = "";

	private String strUserLevel = "";
	private String strPatientDetailsView = "";

	private String strReconcilationReasonText = "";
	private String strReconcilationByValues = "";

	private String strErrPrimaryKeyLog = "";
	private String strReconcilationReasonValues = "";

	private String[] strOfflineTariffName = null;
	private String strTariffId = "";

	private String[] strOfflineTariffDetailsHidden = null;
	private String[] strOfflineTariffDiscount = null;
	private String[] strOfflineTariffNetAmount = null;
	private String[] strOfflineTariffRateUnit = null;
	private String[] strOfflineTariffQty = null;
	private String[] strOfflineTariffUnit = null;
	private String[] strOfflineTariffServiceTax = null;
	
	private String[] strOfflineTariffDiscountConfigDetails = null;
	private String[] strOffLineTariffDiscountType = null;
	private String[] strOffLineTariffDiscountUnit = null;

	
	private String strTariffDetailsValue = "";
				
	private String[] strBillTariffVal = null ;
	private String[] strBillTariffRefund = null;
	private String[] strBillTariffUnit = null ;
	private String[] strBillTariffRefundAmount = null;
	
	private String[] strBillTariffRefundDiscountAmount = null;
	private String[] strBillTariffRefundServiceTaxAmount = null;
	
	private String strReconcileTariffAmount = "";
	
	
	private WebRowSet strBillWs = null;
	private WebRowSet strBillRecWs = null;
	
	private WebRowSet strPatientDetailsWs = null;
	private WebRowSet strOnLineReqRefundWs = null;

	private WebRowSet strDiscAppByList = null;
	private WebRowSet strReconlByList = null;
	private WebRowSet strDisByRmkList = null;
	private WebRowSet strReconlByRmkList = null;

	public WebRowSet getStrDiscAppByList() {
		return strDiscAppByList;
	}

	public void setStrDiscAppByList(WebRowSet strDiscAppByList) {
		this.strDiscAppByList = strDiscAppByList;
	}

	public WebRowSet getStrReconlByList() {
		return strReconlByList;
	}

	public void setStrReconlByList(WebRowSet strReconlByList) {
		this.strReconlByList = strReconlByList;
	}

	public WebRowSet getStrDisByRmkList() {
		return strDisByRmkList;
	}

	public void setStrDisByRmkList(WebRowSet strDisByRmkList) {
		this.strDisByRmkList = strDisByRmkList;
	}

	public WebRowSet getStrReconlByRmkList() {
		return strReconlByRmkList;
	}

	public void setStrReconlByRmkList(WebRowSet strReconlByRmkList) {
		this.strReconlByRmkList = strReconlByRmkList;
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

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}

	public WebRowSet getStrPatientDetailsWs() {
		return strPatientDetailsWs;
	}

	public void setStrPatientDetailsWs(WebRowSet strPatientDetailsWs) {
		this.strPatientDetailsWs = strPatientDetailsWs;
	}

	public WebRowSet getStrOnLineReqRefundWs() {
		return strOnLineReqRefundWs;
	}

	public void setStrOnLineReqRefundWs(WebRowSet strOnLineReqRefundWs) {
		this.strOnLineReqRefundWs = strOnLineReqRefundWs;
	}

	public String getStrOffLineGroup() {
		return strOffLineGroup;
	}

	public void setStrOffLineGroup(String strOffLineGroup) {
		this.strOffLineGroup = strOffLineGroup;
	}

	public String getStrPatientDetailsView() {
		return strPatientDetailsView;
	}

	public void setStrPatientDetailsView(String strPatientDetailsView) {
		this.strPatientDetailsView = strPatientDetailsView;
	}

	/**
	 * @return the strUserLevel
	 */
	public String getStrUserLevel() {
		return strUserLevel;
	}

	/**
	 * @param strUserLevel
	 *            the strUserLevel to set
	 */
	public void setStrUserLevel(String strUserLevel) {
		this.strUserLevel = strUserLevel;
	}

	/**
	 * @return the strErrMsg
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}

	/**
	 * @param strErrMsg
	 *            the strErrMsg to set
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	/**
	 * @return the strErrPrimaryKeyLog
	 */
	public String getStrErrPrimaryKeyLog() {
		return strErrPrimaryKeyLog;
	}

	/**
	 * @param strErrPrimaryKeyLog
	 *            the strErrPrimaryKeyLog to set
	 */
	public void setStrErrPrimaryKeyLog(String strErrPrimaryKeyLog) {
		this.strErrPrimaryKeyLog = strErrPrimaryKeyLog;
	}


	public String getStrReconcilationBy() {
		return strReconcilationBy;
	}

	public void setStrReconcilationBy(String strReconcilationBy) {
		this.strReconcilationBy = strReconcilationBy;
	}

	public String getStrReconcilationReason() {
		return strReconcilationReason;
	}

	public void setStrReconcilationReason(String strReconcilationReason) {
		this.strReconcilationReason = strReconcilationReason;
	}

	public String getStrReconcilationReasonText() {
		return strReconcilationReasonText;
	}

	public void setStrReconcilationReasonText(String strReconcilationReasonText) {
		this.strReconcilationReasonText = strReconcilationReasonText;
	}

	public String getStrReconcilationByValues() {
		return strReconcilationByValues;
	}

	public void setStrReconcilationByValues(String strReconcilationByValues) {
		this.strReconcilationByValues = strReconcilationByValues;
	}

	public String getStrReconcilationReasonValues() {
		return strReconcilationReasonValues;
	}

	public void setStrReconcilationReasonValues(
			String strReconcilationReasonValues) {
		this.strReconcilationReasonValues = strReconcilationReasonValues;
	}

	/**
	 * @return the strBillWs
	 */
	public WebRowSet getStrBillWs() {
		return strBillWs;
	}

	/**
	 * @param strBillWs
	 *            the strBillWs to set
	 */
	public void setStrBillWs(WebRowSet strBillWs) {
		this.strBillWs = strBillWs;
	}

	/**
	 * @return the strBillRecWs
	 */
	public WebRowSet getStrBillRecWs() {
		return strBillRecWs;
	}

	/**
	 * @return the strOffLineTariffDiscountType
	 */
	public String[] getStrOffLineTariffDiscountType() {
		return strOffLineTariffDiscountType;
	}

	/**
	 * @param strOffLineTariffDiscountType
	 *            the strOffLineTariffDiscountType to set
	 */
	public void setStrOffLineTariffDiscountType(
			String[] strOffLineTariffDiscountType) {
		this.strOffLineTariffDiscountType = strOffLineTariffDiscountType;
	}

	/**
	 * @return the strOffLineTariffDiscountUnit
	 */
	public String[] getStrOffLineTariffDiscountUnit() {
		return strOffLineTariffDiscountUnit;
	}

	/**
	 * @param strOffLineTariffDiscountUnit
	 *            the strOffLineTariffDiscountUnit to set
	 */
	public void setStrOffLineTariffDiscountUnit(
			String[] strOffLineTariffDiscountUnit) {
		this.strOffLineTariffDiscountUnit = strOffLineTariffDiscountUnit;
	}

	/**
	 * @param strBillRecWs
	 *            the strBillRecWs to set
	 */
	public void setStrBillRecWs(WebRowSet strBillRecWs) {
		this.strBillRecWs = strBillRecWs;
	}

	/**
	 * @return the strOfflineTariffDiscount
	 */
	public String[] getStrOfflineTariffDiscount() {
		return strOfflineTariffDiscount;
	}

	/**
	 * @param strOfflineTariffDiscount
	 *            the strOfflineTariffDiscount to set
	 */
	public void setStrOfflineTariffDiscount(String[] strOfflineTariffDiscount) {
		this.strOfflineTariffDiscount = strOfflineTariffDiscount;
	}

	/**
	 * @return the strOfflineTariffName
	 */
	public String[] getStrOfflineTariffName() {
		return strOfflineTariffName;
	}

	/**
	 * @param strOfflineTariffName
	 *            the strOfflineTariffName to set
	 */
	public void setStrOfflineTariffName(String[] strOfflineTariffName) {
		this.strOfflineTariffName = strOfflineTariffName;
	}

	// /**
	// * @return the strTariffId
	// */
	// public String[] getStrTariffId() {
	// return strTariffId;
	// }
	//
	// /**
	// * @param strTariffId the strTariffId to set
	// */
	// public void setStrTariffId(String[] strTariffId) {
	// this.strTariffId = strTariffId;
	// }

	/**
	 * @return the strOfflineTariffDetailsHidden
	 */
	public String[] getStrOfflineTariffDetailsHidden() {
		return strOfflineTariffDetailsHidden;
	}

	/**
	 * @param strOfflineTariffDetailsHidden
	 *            the strOfflineTariffDetailsHidden to set
	 */
	public void setStrOfflineTariffDetailsHidden(
			String[] strOfflineTariffDetailsHidden) {
		this.strOfflineTariffDetailsHidden = strOfflineTariffDetailsHidden;
	}

	/**
	 * @return the strTariffId
	 */
	public String getStrTariffId() {
		return strTariffId;
	}

	/**
	 * @param strTariffId
	 *            the strTariffId to set
	 */
	public void setStrTariffId(String strTariffId) {
		this.strTariffId = strTariffId;
	}

	/**
	 * @return the strOffLineTariffDiscountBy
	 */
	public String getStrOffLineTariffDiscountBy() {
		return strOffLineTariffDiscountBy;
	}

	/**
	 * @param strOffLineTariffDiscountBy
	 *            the strOffLineTariffDiscountBy to set
	 */
	public void setStrOffLineTariffDiscountBy(String strOffLineTariffDiscountBy) {
		this.strOffLineTariffDiscountBy = strOffLineTariffDiscountBy;
	}

	/**
	 * @return the strOffLineTariffDiscountReason
	 */
	public String getStrOffLineTariffDiscountReason() {
		return strOffLineTariffDiscountReason;
	}

	/**
	 * @param strOffLineTariffDiscountReason
	 *            the strOffLineTariffDiscountReason to set
	 */
	public void setStrOffLineTariffDiscountReason(
			String strOffLineTariffDiscountReason) {
		this.strOffLineTariffDiscountReason = strOffLineTariffDiscountReason;
	}

	/**
	 * @return the strOfflineTariffDiscountConfigDetails
	 */
	public String[] getStrOfflineTariffDiscountConfigDetails() {
		return strOfflineTariffDiscountConfigDetails;
	}

	/**
	 * @param strOfflineTariffDiscountConfigDetails
	 *            the strOfflineTariffDiscountConfigDetails to set
	 */
	public void setStrOfflineTariffDiscountConfigDetails(
			String[] strOfflineTariffDiscountConfigDetails) {
		this.strOfflineTariffDiscountConfigDetails = strOfflineTariffDiscountConfigDetails;
	}

	/**
	 * @return the strOfflineDiscountApprovedByDetails
	 */
	public String getStrOfflineDiscountApprovedByDetails() {
		return strOfflineDiscountApprovedByDetails;
	}

	/**
	 * @param strOfflineDiscountApprovedByDetails
	 *            the strOfflineDiscountApprovedByDetails to set
	 */
	public void setStrOfflineDiscountApprovedByDetails(
			String strOfflineDiscountApprovedByDetails) {
		this.strOfflineDiscountApprovedByDetails = strOfflineDiscountApprovedByDetails;
	}

	/**
	 * @return the strOfflineDiscountRemarksDetails
	 */
	public String getStrOfflineDiscountRemarksDetails() {
		return strOfflineDiscountRemarksDetails;
	}

	/**
	 * @param strOfflineDiscountRemarksDetails
	 *            the strOfflineDiscountRemarksDetails to set
	 */
	public void setStrOfflineDiscountRemarksDetails(
			String strOfflineDiscountRemarksDetails) {
		this.strOfflineDiscountRemarksDetails = strOfflineDiscountRemarksDetails;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrCtDate() {
		return strCtDate;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	public String getStrTariffDetailsValue() {
		return strTariffDetailsValue;
	}

	public void setStrTariffDetailsValue(String strTariffDetailsValue) {
		this.strTariffDetailsValue = strTariffDetailsValue;
	}

	public String[] getStrOfflineTariffRateUnit() {
		return strOfflineTariffRateUnit;
	}

	public void setStrOfflineTariffRateUnit(String[] strOfflineTariffRateUnit) {
		this.strOfflineTariffRateUnit = strOfflineTariffRateUnit;
	}

	public String[] getStrOfflineTariffQty() {
		return strOfflineTariffQty;
	}

	public void setStrOfflineTariffQty(String[] strOfflineTariffQty) {
		this.strOfflineTariffQty = strOfflineTariffQty;
	}

	public String[] getStrOfflineTariffUnit() {
		return strOfflineTariffUnit;
	}

	public void setStrOfflineTariffUnit(String[] strOfflineTariffUnit) {
		this.strOfflineTariffUnit = strOfflineTariffUnit;
	}

	public String[] getStrOfflineTariffServiceTax() {
		return strOfflineTariffServiceTax;
	}

	public void setStrOfflineTariffServiceTax(String[] strOfflineTariffServiceTax) {
		this.strOfflineTariffServiceTax = strOfflineTariffServiceTax;
	}
	
	public String getStrBillNoVal() {
		return strBillNoVal;
	}

	public void setStrBillNoVal(String strBillNoVal) {
		this.strBillNoVal = strBillNoVal;
	}

	
	public String[] getStrBillTariffVal() {
		return strBillTariffVal;
	}

	public void setStrBillTariffVal(String[] strBillTariffVal) {
		this.strBillTariffVal = strBillTariffVal;
	}

	public String[] getStrBillTariffRefund() {
		return strBillTariffRefund;
	}

	public void setStrBillTariffRefund(String[] strBillTariffRefund) {
		this.strBillTariffRefund = strBillTariffRefund;
	}

	public String[] getStrBillTariffUnit() {
		return strBillTariffUnit;
	}

	public void setStrBillTariffUnit(String[] strBillTariffUnit) {
		this.strBillTariffUnit = strBillTariffUnit;
	}

	public String[] getStrBillTariffRefundAmount() {
		return strBillTariffRefundAmount;
	}

	public void setStrBillTariffRefundAmount(String[] strBillTariffRefundAmount) {
		this.strBillTariffRefundAmount = strBillTariffRefundAmount;
	}

	public String getStrReconcileTariffAmount() {
		return strReconcileTariffAmount;
	}

	public void setStrReconcileTariffAmount(String strReconcileTariffAmount) {
		this.strReconcileTariffAmount = strReconcileTariffAmount;
	}

	public String[] getStrOfflineTariffNetAmount() {
		return strOfflineTariffNetAmount;
	}

	public void setStrOfflineTariffNetAmount(String[] strOfflineTariffNetAmount) {
		this.strOfflineTariffNetAmount = strOfflineTariffNetAmount;
	}

	public String[] getStrBillTariffRefundDiscountAmount() {
		return strBillTariffRefundDiscountAmount;
	}

	public void setStrBillTariffRefundDiscountAmount(
			String[] strBillTariffRefundDiscountAmount) {
		this.strBillTariffRefundDiscountAmount = strBillTariffRefundDiscountAmount;
	}

	public String[] getStrBillTariffRefundServiceTaxAmount() {
		return strBillTariffRefundServiceTaxAmount;
	}

	public void setStrBillTariffRefundServiceTaxAmount(
			String[] strBillTariffRefundServiceTaxAmount) {
		this.strBillTariffRefundServiceTaxAmount = strBillTariffRefundServiceTaxAmount;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

}