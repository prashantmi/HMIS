package hisglobal.vo;
import investigation.vo.TriStringObject;
import investigation.vo.BlockDetail;

import java.util.*;

public class SlideBasedTestVO extends ValueObject 
{
	private String sampleNo;
	private String requisitionDNo;
	private String accessionNo;
	private String specimen;	
	private String specimenReceivedOn;
	private String specimenCollectedOn;	
	private String grossedOn;
	private String grossedBy;
	private String grossDetail;	
	private String labCode;
	private String testName;	
	private String isgrossingrequired;
	private String typeofcomponent;
	List<BlockDetail> blockDetailList=null;
	List<BlockDetail> blockDetailActiveList=null;
	List<BlockDetail> blockDetailPasiveList=null;
	
	private String slidePreparedOn;
	List<BlockDetail> deletedBlockDetailList=null;
	private String isGrossCompleted;
	private String isProcessingCompleted;

	private String isStaningCompleted;
	private String isCuttingCompleted;
	private String isReportinCompleted;
	private String isReGrossing;	
	private String labNo;
	private String kef;
	private String notissueleft;
	private String noBlockRequired;
	private String specimenGrossingRemarks="";
	private String userSampleNo;
	
	public String getUserSampleNo() {
		return userSampleNo;
	}
	public void setUserSampleNo(String userSampleNo) {
		this.userSampleNo = userSampleNo;
	}
	public List<BlockDetail> getBlockDetailActiveList() {
		return blockDetailActiveList;
	}
	public void setBlockDetailActiveList(List<BlockDetail> blockDetailActiveList) {
		this.blockDetailActiveList = blockDetailActiveList;
	}
	public List<BlockDetail> getBlockDetailPasiveList() {
		return blockDetailPasiveList;
	}
	public void setBlockDetailPasiveList(List<BlockDetail> blockDetailPasiveList) {
		this.blockDetailPasiveList = blockDetailPasiveList;
	}
	public String getNoBlockRequired() {
		return noBlockRequired;
	}
	public void setNoBlockRequired(String noBlockRequired) {
		this.noBlockRequired = noBlockRequired;
	}
	public String getKef() {
		return kef;
	}
	public void setKef(String kef) {
		this.kef = kef;
	}
	public String getNotissueleft() {
		return notissueleft;
	}
	public void setNotissueleft(String notissueleft) {
		this.notissueleft = notissueleft;
	}
	public String getSpecimenGrossingRemarks() {
		return specimenGrossingRemarks;
	}
	public void setSpecimenGrossingRemarks(String specimenGrossingRemarks) {
		this.specimenGrossingRemarks = specimenGrossingRemarks;
	}
	public String getIsGrossCompleted() {
		return isGrossCompleted;
	}
	public void setIsGrossCompleted(String isGrossCompleted) {
		this.isGrossCompleted = isGrossCompleted;
	}
	public String getIsProcessingCompleted() {
		return isProcessingCompleted;
	}
	public void setIsProcessingCompleted(String isProcessingCompleted) {
		this.isProcessingCompleted = isProcessingCompleted;
	}
	public String getIsStaningCompleted() {
		return isStaningCompleted;
	}
	public void setIsStaningCompleted(String isStaningCompleted) {
		this.isStaningCompleted = isStaningCompleted;
	}
	public String getIsCuttingCompleted() {
		return isCuttingCompleted;
	}
	public void setIsCuttingCompleted(String isCuttingCompleted) {
		this.isCuttingCompleted = isCuttingCompleted;
	}
	public String getIsReportinCompleted() {
		return isReportinCompleted;
	}
	public void setIsReportinCompleted(String isReportinCompleted) {
		this.isReportinCompleted = isReportinCompleted;
	}
	public String getIsReGrossing() {
		return isReGrossing;
	}
	public void setIsReGrossing(String isReGrossing) {
		this.isReGrossing = isReGrossing;
	}
	/**
	 * @return Returns the blockDetailList.
	 */
	public List<BlockDetail> getBlockDetailList() {
		return blockDetailList;
	}
	/**
	 * @param blockDetailList The blockDetailList to set.
	 */
	public void setBlockDetailList(List<BlockDetail> blockDetailList) {
		this.blockDetailList = blockDetailList;
	}
	/**
	 * @return Returns the slideDetailList.
	 */
	
	/**
	 * @return Returns the blocks.
	 */
	
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getAccessionNo() {
		return accessionNo;
	}
	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}
	
	public String getGrossDetail() {
		return grossDetail;
	}
	public void setGrossDetail(String grossDetail) {
		this.grossDetail = grossDetail;
	}
	public String getGrossedBy() {
		return grossedBy;
	}
	public void setGrossedBy(String grossedBy) {
		this.grossedBy = grossedBy;
	}
	public String getGrossedOn() {
		return grossedOn;
	}
	public void setGrossedOn(String grossedOn) {
		this.grossedOn = grossedOn;
	}
	
	public String getRequisitionDNo() {
		return requisitionDNo;
	}
	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}
	public String getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}
	
	public String getSlidePreparedOn() {
		return slidePreparedOn;
	}
	public void setSlidePreparedOn(String slidePreparedOn) {
		this.slidePreparedOn = slidePreparedOn;
	}
	public String getSpecimen() {
		return specimen;
	}
	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}
	public String getSpecimenCollectedOn() {
		return specimenCollectedOn;
	}
	public void setSpecimenCollectedOn(String specimenCollectedOn) {
		this.specimenCollectedOn = specimenCollectedOn;
	}
	public String getSpecimenReceivedOn() {
		return specimenReceivedOn;
	}
	public void setSpecimenReceivedOn(String specimenReceivedOn) {
		this.specimenReceivedOn = specimenReceivedOn;
	}	
	public String getIsgrossingrequired() {
		return isgrossingrequired;
	}
	public void setIsgrossingrequired(String isgrossingrequired) {
		this.isgrossingrequired = isgrossingrequired;
	}
	public String getTypeofcomponent() {
		return typeofcomponent;
	}
	public void setTypeofcomponent(String typeofcomponent) {
		this.typeofcomponent = typeofcomponent;
	}
	public List<BlockDetail> getDeletedBlockDetailList() {
		return deletedBlockDetailList;
	}
	public void setDeletedBlockDetailList(List<BlockDetail> deletedBlockDetailList) {
		this.deletedBlockDetailList = deletedBlockDetailList;
	}
	public String getLabNo() {
		return labNo;
	}
	public void setLabNo(String labNo) {
		this.labNo = labNo;
	}
		

}
