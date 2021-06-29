package hisglobal.vo;

public class BloodRequisitionDtlVO extends ValueObject
{
	
	private String requisitionNo;
	private String requisitionRaisedDate;
	private String requisitionType;
	private String priority;
	private String isOnline;
	private String priorityNo;
	private String bloodGroupCode;
    private String bloodGroupDesc;
	private String requisitionStatus;
	private String initialBloodABO;
	private String isAutologous;
	private String initialRH;
	private String unitFreeAccount;
	private String unitCreditBalance;
	private String unitReplacementAccount;
	private String requisitionRaisedBy;
	private String unitSanctionAccount;
	private String requisitionAcceptedBy;
	private String unitVoluntaryAccount;
	private String requisitionAcceptedDate;
	private String isValid;
	private String unitCashAct;
	private String hospitalCode;
	private String isSample;
	private String sendSample;
	private String patientName;
	private String ageSex;
	private String directedDonationAccount;
	private String replacementGaurantedDepositAccount;
	private String billingCategoryCode;
	private String requisitionCategoryCode;
	private String antibodyStatus;
    private String antibodyResult;
    private String antibodyStatusDesc;
    private String antibodyResultDesc;
    private String antibodyTypeCode;
    private String antibodyTypeCodeDesc;
	private String unitAutologousAccount;
	private String freeCreditBalance;
	private String reasonCode;
	private String unitRGDCreditBalance;
	private String repGaurantedDepositAcc;
	private String bloodBankName;
	private String issueTo;
		// voluntory Card///
	private String totalQuantity;
	private String noOfUnitDemanded;
	private String noOfUnitIssued;
      // paient Sample Acceptance //
	private String flag;
	private String patCrNo;
	private String visitNo;
	
	private String inPGI;
	
	private String consentId;
	private String isConsentReceived;
	private String consentServiceId;
	private String consentServiceTypeId;

	private String reasonRemarks;
	
	private String nationalId;

	
	public String getAntibodyStatus() {
		return antibodyStatus;
	}
	public void setAntibodyStatus(String antibodyStatus) {
		this.antibodyStatus = antibodyStatus;
	}
	public String getRequisitionCategoryCode() {
		return requisitionCategoryCode;
	}
	public void setRequisitionCategoryCode(String requisitionCategoryCode) {
		this.requisitionCategoryCode = requisitionCategoryCode;
	}
	public String getBillingCategoryCode()
	{
		return billingCategoryCode;
	}
	public void setBillingCategoryCode(String billingCategoryCode)
	{
		this.billingCategoryCode = billingCategoryCode;
	}
	public String getDirectedDonationAccount()
	{
		return directedDonationAccount;
	}
	public void setDirectedDonationAccount(String directedDonationAccount)
	{
		this.directedDonationAccount = directedDonationAccount;
	}
	public String getReplacementGaurantedDepositAccount()
	{
		return replacementGaurantedDepositAccount;
	}
	public void setReplacementGaurantedDepositAccount(String replacementGaurantedDepositAccount)
	{
		this.replacementGaurantedDepositAccount = replacementGaurantedDepositAccount;
	}
	public String getRequisitionNo()
	{
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo)
	{
		this.requisitionNo = requisitionNo;
	}
	public String getRequisitionRaisedDate()
	{
		return requisitionRaisedDate;
	}
	public void setRequisitionRaisedDate(String requisitionRaisedDate)
	{
		this.requisitionRaisedDate = requisitionRaisedDate;
	}
	public String getRequisitionType()
	{
		return requisitionType;
	}
	public void setRequisitionType(String requisitionType)
	{
		this.requisitionType = requisitionType;
	}
	public String getPriority()
	{
		return priority;
	}
	public void setPriority(String priority)
	{
		this.priority = priority;
	}
	public String getIsOnline()
	{
		return isOnline;
	}
	public void setIsOnline(String isOnline)
	{
		this.isOnline = isOnline;
	}
	public String getPriorityNo()
	{
		return priorityNo;
	}
	public void setPriorityNo(String priorityNo)
	{
		this.priorityNo = priorityNo;
	}
	public String getBloodGroupCode()
	{
		return bloodGroupCode;
	}
	public void setBloodGroupCode(String bloodGroupCode)
	{
		this.bloodGroupCode = bloodGroupCode;
	}
	public String getBloodGroupDesc()
	{
		return bloodGroupDesc;
	}
	public void setBloodGroupDesc(String bloodGroupDesc)
	{
		this.bloodGroupDesc = bloodGroupDesc;
	}
	public String getRequisitionStatus()
	{
		return requisitionStatus;
	}
	public void setRequisitionStatus(String requisitionStatus)
	{
		this.requisitionStatus = requisitionStatus;
	}
	public String getInitialBloodABO()
	{
		return initialBloodABO;
	}
	public void setInitialBloodABO(String initialBloodABO)
	{
		this.initialBloodABO = initialBloodABO;
	}
	public String getIsAutologous()
	{
		return isAutologous;
	}
	public void setIsAutologous(String isAutologous)
	{
		this.isAutologous = isAutologous;
	}
	public String getInitialRH()
	{
		return initialRH;
	}
	public void setInitialRH(String initialRH)
	{
		this.initialRH = initialRH;
	}
	public String getUnitFreeAccount()
	{
		return unitFreeAccount;
	}
	public void setUnitFreeAccount(String unitFreeAccount)
	{
		this.unitFreeAccount = unitFreeAccount;
	}
	public String getUnitCreditBalance()
	{
		return unitCreditBalance;
	}
	public void setUnitCreditBalance(String unitCreditBalance)
	{
		this.unitCreditBalance = unitCreditBalance;
	}
	public String getUnitReplacementAccount()
	{
		return unitReplacementAccount;
	}
	public void setUnitReplacementAccount(String unitReplacementAccount)
	{
		this.unitReplacementAccount = unitReplacementAccount;
	}
	public String getRequisitionRaisedBy()
	{
		return requisitionRaisedBy;
	}
	public void setRequisitionRaisedBy(String requisitionRaisedBy)
	{
		this.requisitionRaisedBy = requisitionRaisedBy;
	}
	public String getUnitSanctionAccount()
	{
		return unitSanctionAccount;
	}
	public void setUnitSanctionAccount(String unitSanctionAccount)
	{
		this.unitSanctionAccount = unitSanctionAccount;
	}
	public String getRequisitionAcceptedBy()
	{
		return requisitionAcceptedBy;
	}
	public void setRequisitionAcceptedBy(String requisitionAcceptedBy)
	{
		this.requisitionAcceptedBy = requisitionAcceptedBy;
	}
	
	public String getRequisitionAcceptedDate()
	{
		return requisitionAcceptedDate;
	}
	public void setRequisitionAcceptedDate(String requisitionAcceptedDate)
	{
		this.requisitionAcceptedDate = requisitionAcceptedDate;
	}
	public String getIsValid()
	{
		return isValid;
	}
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}
	public String getUnitCashAct()
	{
		return unitCashAct;
	}
	public void setUnitCashAct(String unitCashAct)
	{
		this.unitCashAct = unitCashAct;
	}
	public String getHospitalCode()
	{
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}
	public String getIsSample()
	{
		return isSample;
	}
	public void setIsSample(String isSample)
	{
		this.isSample = isSample;
	}
	public String getSendSample()
	{
		return sendSample;
	}
	public void setSendSample(String sendSample)
	{
		this.sendSample = sendSample;
	}
	public String getUnitVoluntaryAccount()
	{
		return unitVoluntaryAccount;
	}
	public void setUnitVoluntaryAccount(String unitVoluntaryAccount)
	{
		this.unitVoluntaryAccount = unitVoluntaryAccount;
	}
	public String getPatientName()
	{
		return patientName;
	}
	public void setPatientName(String patientName)
	{
		this.patientName = patientName;
	}
	public String getAgeSex()
	{
		return ageSex;
	}
	public void setAgeSex(String ageSex)
	{
		this.ageSex = ageSex;
	}
	public String getAntibodyResult() {
		return antibodyResult;
	}
	public void setAntibodyResult(String antibodyResult) {
		this.antibodyResult = antibodyResult;
	}
	public String getAntibodyStatusDesc() {
		return antibodyStatusDesc;
	}
	public void setAntibodyStatusDesc(String antibodyStatusDesc) {
		this.antibodyStatusDesc = antibodyStatusDesc;
	}
	public String getAntibodyResultDesc() {
		return antibodyResultDesc;
	}
	public void setAntibodyResultDesc(String antibodyResultDesc) {
		this.antibodyResultDesc = antibodyResultDesc;
	}
	public String getAntibodyTypeCode() {
		return antibodyTypeCode;
	}
	public void setAntibodyTypeCode(String antibodyTypeCode) {
		this.antibodyTypeCode = antibodyTypeCode;
	}
	public String getAntibodyTypeCodeDesc() {
		return antibodyTypeCodeDesc;
	}
	public void setAntibodyTypeCodeDesc(String antibodyTypeCodeDesc) {
		this.antibodyTypeCodeDesc = antibodyTypeCodeDesc;
	}
	public String getUnitAutologousAccount() {
		return unitAutologousAccount;
	}
	public void setUnitAutologousAccount(String unitAutologousAccount) {
		this.unitAutologousAccount = unitAutologousAccount;
	}
	public String getFreeCreditBalance() {
		return freeCreditBalance;
	}
	public void setFreeCreditBalance(String freeCreditBalance) {
		this.freeCreditBalance = freeCreditBalance;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getUnitRGDCreditBalance() {
		return unitRGDCreditBalance;
	}
	public void setUnitRGDCreditBalance(String unitRGDCreditBalance) {
		this.unitRGDCreditBalance = unitRGDCreditBalance;
	}
	public String getRepGaurantedDepositAcc() {
		return repGaurantedDepositAcc;
	}
	public void setRepGaurantedDepositAcc(String repGaurantedDepositAcc) {
		this.repGaurantedDepositAcc = repGaurantedDepositAcc;
	}
	public String getBloodBankName() {
		return bloodBankName;
	}
	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
	}
	public String getIssueTo() {
		return issueTo;
	}
	public void setIssueTo(String issueTo) {
		this.issueTo = issueTo;
	}
	public String getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getNoOfUnitDemanded() {
		return noOfUnitDemanded;
	}
	public void setNoOfUnitDemanded(String noOfUnitDemanded) {
		this.noOfUnitDemanded = noOfUnitDemanded;
	}
	public String getNoOfUnitIssued() {
		return noOfUnitIssued;
	}
	public void setNoOfUnitIssued(String noOfUnitIssued) {
		this.noOfUnitIssued = noOfUnitIssued;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public String getInPGI() {
		return inPGI;
	}
	public void setInPGI(String inPGI) {
		this.inPGI = inPGI;
	}
	public String getConsentId() {
		return consentId;
	}
	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}
	public String getIsConsentReceived() {
		return isConsentReceived;
	}
	public void setIsConsentReceived(String isConsentReceived) {
		this.isConsentReceived = isConsentReceived;
	}
	public String getConsentServiceId() {
		return consentServiceId;
	}
	public void setConsentServiceId(String consentServiceId) {
		this.consentServiceId = consentServiceId;
	}
	public String getConsentServiceTypeId() {
		return consentServiceTypeId;
	}
	public void setConsentServiceTypeId(String consentServiceTypeId) {
		this.consentServiceTypeId = consentServiceTypeId;
	}
	public String getReasonRemarks() {
		return reasonRemarks;
	}
	public void setReasonRemarks(String reasonRemarks) {
		this.reasonRemarks = reasonRemarks;
	}
	public String getNationalId() {
		return nationalId;
	}
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	
}
