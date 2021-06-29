package hisglobal.vo;

public class BloodBagIssueVO extends ValueObject
{
   private String requisitionNo;
   private String issueSequenceNo;
   private String bloodBagNo;
   private String bagSequenceNo;
   private String componentId;
   private String billNo;
   private String isActive;
   private String requisitionSlNo;
   private String status;
   private String issueDate; 
   private String bagAndSeqNo; 
   private String componentName;
   private String issueTo;
   private String requiredComponent;
   //issued Bag return
   private String bloodBagExpiry;
   private String storageDesc;
   private String bloodGroupDesc;
   private String flag;
   private String requisitionType;
   
   private String bloodGroupCode;
   private String bagBatchNo;
   private String tubingNo;
   private String patCrNo;
   private String bagVolume;
   
   // bulk Issue //
   private String noOfUnitAccepted;
   
   private String bagExpiryDate;
   
   private String transfusionStartDate;
   private String transfusionEndDate;
   private String donorName;
   
   private String qtyTransfused;
   // cross match details
   
   private String crossMatchDate; 
   
public String getTransfusionStartDate()
{
	return transfusionStartDate;
}
public void setTransfusionStartDate(String transfusionStartDate)
{
	this.transfusionStartDate = transfusionStartDate;
}
public String getTransfusionEndDate()
{
	return transfusionEndDate;
}
public void setTransfusionEndDate(String transfusionEndDate)
{
	this.transfusionEndDate = transfusionEndDate;
}
public String getBagExpiryDate()
{
	return bagExpiryDate;
}
public void setBagExpiryDate(String bagExpiryDate)
{
	this.bagExpiryDate = bagExpiryDate;
}
public String getRequisitionNo()
{
	return requisitionNo;
}
public void setRequisitionNo(String requisitionNo)
{
	this.requisitionNo = requisitionNo;
}
public String getIssueSequenceNo()
{
	return issueSequenceNo;
}
public void setIssueSequenceNo(String issueSequenceNo)
{
	this.issueSequenceNo = issueSequenceNo;
}
public String getBloodBagNo()
{
	return bloodBagNo;
}
public void setBloodBagNo(String bloodBagNo)
{
	this.bloodBagNo = bloodBagNo;
}
public String getBagSequenceNo()
{
	return bagSequenceNo;
}
public void setBagSequenceNo(String bagSequenceNo)
{
	this.bagSequenceNo = bagSequenceNo;
}
public String getComponentId()
{
	return componentId;
}
public void setComponentId(String componentId)
{
	this.componentId = componentId;
}
public String getBillNo()
{
	return billNo;
}
public void setBillNo(String billNo)
{
	this.billNo = billNo;
}
public String getIsActive()
{
	return isActive;
}
public void setIsActive(String isActive)
{
	this.isActive = isActive;
}
public String getRequisitionSlNo()
{
	return requisitionSlNo;
}
public void setRequisitionSlNo(String requisitionSlNo)
{
	this.requisitionSlNo = requisitionSlNo;
}
public String getStatus()
{
	return status;
}
public void setStatus(String status)
{
	this.status = status;
}
public String getIssueDate()
{
	return issueDate;
}
public void setIssueDate(String issueDate)
{
	this.issueDate = issueDate;
}
public String getBagAndSeqNo()
{
	return bagAndSeqNo;
}
public void setBagAndSeqNo(String bagAndSeqNo)
{
	this.bagAndSeqNo = bagAndSeqNo;
}
public String getComponentName()
{
	return componentName;
}
public void setComponentName(String componentName)
{
	this.componentName = componentName;
}
public String getIssueTo()
{
	return issueTo;
}
public void setIssueTo(String issueTo)
{
	this.issueTo = issueTo;
}
public String getBloodBagExpiry()
{
	return bloodBagExpiry;
}
public void setBloodBagExpiry(String bloodBagExpiry)
{
	this.bloodBagExpiry = bloodBagExpiry;
}
public String getStorageDesc()
{
	return storageDesc;
}
public void setStorageDesc(String storageDesc)
{
	this.storageDesc = storageDesc;
}
public String getBloodGroupDesc()
{
	return bloodGroupDesc;
}
public void setBloodGroupDesc(String bloodGroupDesc)
{
	this.bloodGroupDesc = bloodGroupDesc;
}
public String getFlag()
{
	return flag;
}
public void setFlag(String flag)
{
	this.flag = flag;
}
public String getNoOfUnitAccepted()
{
	return noOfUnitAccepted;
}
public void setNoOfUnitAccepted(String noOfUnitAccepted)
{
	this.noOfUnitAccepted = noOfUnitAccepted;
}
public String getRequiredComponent()
{
	return requiredComponent;
}
public void setRequiredComponent(String requiredComponent)
{
	this.requiredComponent = requiredComponent;
}
public String getRequisitionType()
{
	return requisitionType;
}
public void setRequisitionType(String requisitionType)
{
	this.requisitionType = requisitionType;
}
public String getBloodGroupCode()
{
	return bloodGroupCode;
}
public void setBloodGroupCode(String bloodGroupCode)
{
	this.bloodGroupCode = bloodGroupCode;
}
public String getBagBatchNo()
{
	return bagBatchNo;
}
public void setBagBatchNo(String bagBatchNo)
{
	this.bagBatchNo = bagBatchNo;
}
public String getTubingNo()
{
	return tubingNo;
}
public void setTubingNo(String tubingNo)
{
	this.tubingNo = tubingNo;
}
public String getPatCrNo()
{
	return patCrNo;
}
public void setPatCrNo(String patCrNo)
{
	this.patCrNo = patCrNo;
}
public String getBagVolume()
{
	return bagVolume;
}
public void setBagVolume(String bagVolume)
{
	this.bagVolume = bagVolume;
}
public String getDonorName() {
	return donorName;
}
public void setDonorName(String donorName) {
	this.donorName = donorName;
}
public String getCrossMatchDate() {
	return crossMatchDate;
}
public void setCrossMatchDate(String crossMatchDate) {
	this.crossMatchDate = crossMatchDate;
}
public String getQtyTransfused()
{
	return qtyTransfused;
}
public void setQtyTransfused(String qtyTransfused)
{
	this.qtyTransfused = qtyTransfused;
}
   
}
