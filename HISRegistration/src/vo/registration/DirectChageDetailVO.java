package vo.registration;

import hisglobal.vo.ValueObject;

public class DirectChageDetailVO extends ValueObject {

	private String patCrNo;
	private String departmentCode;
	private String departmentUnitCode;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String serviceId;
	private String tariffId;
	private String patPrimaryCatCode;
	private String patSecondaryCatCode;
	private String patAmountCollected;
	private String systemIPAddress;
	private String seatId;
	private String entryDate;
	private String isValid;
	private String hospitalCode;
	private String moduleId;
	
	private String billNo;
	private String recieptNo;
	private String recieptType;
	private String requestType;
	
	private String creditBillFlag;
	private String creditLetterRefNo;
	private String creditLetterDate;
	
	private String clientCode;
	/*Start: Surabhi
	 * reason: for adding client combo
	 * date : 29-7-2016*/
	private String clientName;
	//End 
	
	private String cardNo;
	private String staffCardName;
	private String relationWithStaff;
	private String cardvalidityDate;
	private String agsDistrictCode;
	private String agsCounterNo;
	private String patActualAmount;
	private String patRenewalActualAmount;
	private String chargeType;//By Mukund on 15.05.2017
	private String directChargeType;
	private String PaymentModeRefId; //by warish on 09.08.2018
	private String paymentMode;//1 for cash, 2 for cheque
	private String CatBoundFlag;
	private String CatCodeBound;
	public String getCatBoundFlag() {
		return CatBoundFlag;
	}
	public void setCatBoundFlag(String catBoundFlag) {
		CatBoundFlag = catBoundFlag;
	}
	public String getCatCodeBound() {
		return CatCodeBound;
	}
	public void setCatCodeBound(String catCodeBound) {
		CatCodeBound = catCodeBound;
	}
	
	public String getDirectChargeType() {
		return directChargeType;
	}
	public void setDirectChargeType(String directChargeType) {
		this.directChargeType = directChargeType;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getTariffId() {
		return tariffId;
	}
	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getPatSecondaryCatCode() {
		return patSecondaryCatCode;
	}
	public void setPatSecondaryCatCode(String patSecondaryCatCode) {
		this.patSecondaryCatCode = patSecondaryCatCode;
	}
	public String getPatAmountCollected() {
		return patAmountCollected;
	}
	public void setPatAmountCollected(String patAmountCollected) {
		this.patAmountCollected = patAmountCollected;
	}
	public String getSystemIPAddress() {
		return systemIPAddress;
	}
	public void setSystemIPAddress(String systemIPAddress) {
		this.systemIPAddress = systemIPAddress;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getRecieptNo() {
		return recieptNo;
	}
	public void setRecieptNo(String recieptNo) {
		this.recieptNo = recieptNo;
	}
	public String getRecieptType() {
		return recieptType;
	}
	public void setRecieptType(String recieptType) {
		this.recieptType = recieptType;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getCreditBillFlag() {
		return creditBillFlag;
	}
	public void setCreditBillFlag(String creditBillFlag) {
		this.creditBillFlag = creditBillFlag;
	}
	public String getCreditLetterRefNo() {
		return creditLetterRefNo;
	}
	public void setCreditLetterRefNo(String creditLetterRefNo) {
		this.creditLetterRefNo = creditLetterRefNo;
	}
	public String getCreditLetterDate() {
		return creditLetterDate;
	}
	public void setCreditLetterDate(String creditLetterDate) {
		this.creditLetterDate = creditLetterDate;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	
	public String getStaffCardName() {
		return staffCardName;
	}
	public void setStaffCardName(String staffCardName) {
		this.staffCardName = staffCardName;
	}
	public String getRelationWithStaff() {
		return relationWithStaff;
	}
	public void setRelationWithStaff(String relationWithStaff) {
		this.relationWithStaff = relationWithStaff;
	}
	public String getCardvalidityDate() {
		return cardvalidityDate;
	}
	public void setCardvalidityDate(String cardvalidityDate) {
		this.cardvalidityDate = cardvalidityDate;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getAgsDistrictCode() {
		return agsDistrictCode;
	}
	public void setAgsDistrictCode(String agsDistrictCode) {
		this.agsDistrictCode = agsDistrictCode;
	}
	public String getAgsCounterNo() {
		return agsCounterNo;
	}
	public void setAgsCounterNo(String agsCounterNo) {
		this.agsCounterNo = agsCounterNo;
	}
	public String getPatActualAmount() {
		return patActualAmount;
	}
	public void setPatActualAmount(String patActualAmount) {
		this.patActualAmount = patActualAmount;
	}
	public String getPatRenewalActualAmount() {
		return patRenewalActualAmount;
	}
	public void setPatRenewalActualAmount(String patRenewalActualAmount) {
		this.patRenewalActualAmount = patRenewalActualAmount;
	}
	
	/*Start: Surabhi
	 * reason: for adding client combo
	 * date : 29-7-2016*/
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	//End
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getPaymentModeRefId() {
		return PaymentModeRefId;
	}
	public void setPaymentModeRefId(String paymentModeRefId) {
		PaymentModeRefId = paymentModeRefId;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	

}
