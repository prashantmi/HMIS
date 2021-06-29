package hisglobal.vo;

public class MedicalBoardBillingVO extends ValueObject
{
     private String tarriff; 
     private String billNo; 
	 private String patCrNo;
	 private String remainedQuantity;
	 private String serviceId;
	 
	 
	public String getTarriff() {
		return tarriff;
	}
	public void setTarriff(String tarriff) {
		this.tarriff = tarriff;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getRemainedQuantity() {
		return remainedQuantity;
	}
	public void setRemainedQuantity(String remainedQuantity) {
		this.remainedQuantity = remainedQuantity;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	 
	 
	

}
