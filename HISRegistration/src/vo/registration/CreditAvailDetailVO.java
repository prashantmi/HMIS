package vo.registration;

import hisglobal.vo.ValueObject;

public class CreditAvailDetailVO extends ValueObject {

	private String patCrNo;
	private String creditLetterRefNo;
	private String creditLetterDate;
	private String tariffId;
	private String groupId;
	private String billNo;
	private String recieptNo;
	private String recieptDate;
	private String recieptType;
	private String billQty;
	private String processedQty;
	private String remainedQty;
	private String qtyUnitId;
	private String patPrimaryCatCode;
	private String billServiceId;
	private String serviceId;
	private String tariff;
	private String isPackage;
	private String tariffActualRate;
	private String rateUnitCode;
	private String netAmount;
	private String paidByPatAmt;
	private String paidByClientAmt;
	private String creditApprovedBy;
	private String creditApprovedDate;
	private String remarks;	
	private String systemIPAddress;
	private String seatId;
	private String entryDate;
	private String isValid;
	private String hospitalCode;
	private String moduleId;
	
	private String clientCode;
	/*Start: Surabhi
	 * reason: for adding client combo
	 * date : 29-7-2016*/
	private String clientName;

	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	//End
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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
	public String getTariffId() {
		return tariffId;
	}
	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	public String getRecieptDate() {
		return recieptDate;
	}
	public void setRecieptDate(String recieptDate) {
		this.recieptDate = recieptDate;
	}
	public String getRecieptType() {
		return recieptType;
	}
	public void setRecieptType(String recieptType) {
		this.recieptType = recieptType;
	}
	public String getBillQty() {
		return billQty;
	}
	public void setBillQty(String billQty) {
		this.billQty = billQty;
	}
	public String getProcessedQty() {
		return processedQty;
	}
	public void setProcessedQty(String processedQty) {
		this.processedQty = processedQty;
	}
	public String getRemainedQty() {
		return remainedQty;
	}
	public void setRemainedQty(String remainedQty) {
		this.remainedQty = remainedQty;
	}
	public String getQtyUnitId() {
		return qtyUnitId;
	}
	public void setQtyUnitId(String qtyUnitId) {
		this.qtyUnitId = qtyUnitId;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getBillServiceId() {
		return billServiceId;
	}
	public void setBillServiceId(String billServiceId) {
		this.billServiceId = billServiceId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getTariff() {
		return tariff;
	}
	public void setTariff(String tariff) {
		this.tariff = tariff;
	}
	public String getIsPackage() {
		return isPackage;
	}
	public void setIsPackage(String isPackage) {
		this.isPackage = isPackage;
	}
	public String getTariffActualRate() {
		return tariffActualRate;
	}
	public void setTariffActualRate(String tariffActualRate) {
		this.tariffActualRate = tariffActualRate;
	}
	public String getRateUnitCode() {
		return rateUnitCode;
	}
	public void setRateUnitCode(String rateUnitCode) {
		this.rateUnitCode = rateUnitCode;
	}
	public String getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}
	public String getPaidByPatAmt() {
		return paidByPatAmt;
	}
	public void setPaidByPatAmt(String paidByPatAmt) {
		this.paidByPatAmt = paidByPatAmt;
	}
	public String getPaidByClientAmt() {
		return paidByClientAmt;
	}
	public void setPaidByClientAmt(String paidByClientAmt) {
		this.paidByClientAmt = paidByClientAmt;
	}
	public String getCreditApprovedBy() {
		return creditApprovedBy;
	}
	public void setCreditApprovedBy(String creditApprovedBy) {
		this.creditApprovedBy = creditApprovedBy;
	}
	public String getCreditApprovedDate() {
		return creditApprovedDate;
	}
	public void setCreditApprovedDate(String creditApprovedDate) {
		this.creditApprovedDate = creditApprovedDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	
	
		
	
}
