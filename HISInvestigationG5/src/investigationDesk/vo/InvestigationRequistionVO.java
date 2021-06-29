package investigationDesk.vo;

import hisglobal.vo.ValueObject;

public class InvestigationRequistionVO extends ValueObject {

	
	public String strHospitalCode;
	public String strRequsitionDNo;
	public String strIsAppointment;
	public String strLabCode;
	public String strTestCode;
	public String strIsConfidential;
	public String strPriority;
	public String strResultDate;
	public String strSampleNo;
	public String strAppRefNo; 
	public String strTempSampleNo;
	public String strGroupCode;
	public String strGroupType;
	public String strCancellationDate;
	public String strCancellationSeatId;
	public String strBillNo;
	public String strResValSeatID;
	public String strResValidationDate;
	public String strResultValidationSeatID;
	public String strSampleCode;
	public String strResultReValidateDate;
	public String strResultPrintSeatId;
	public String strDailyLabNo;
	public String strPatAcceptanceDateTime;
	public String strResPrintDate;
	public String strPatRejectionReason;
	public String strResultModifySeatId;
	public String strResultModifyDate;
	public String strPatRejectionAction;
	public String strRescheduleSeatId;
	public String strWorkOrderSequence;
	public String strSamRejectionAction;
	public String strSamRejectionReason;
	public String strTestDeleteSeatId;
	public String strRescheduleDate;
	public String strTestDeleteDate;
	public String strTypeOfComponent;
	public String strReqNo;
	public String strIsAccepted;
	public String strPackingListNo;
	public String strMachineCode;
	public String strAcceptanceDateTime;
	public String strAcceptanceSeatId;
	public String strCollDateTime;
	public String strCollectionSeatId;
	public String strSampleCollectionAreaCode;
	public String strAppointmentDate;
	public String strAppointmentTime;
	public String strTestGroupCode;
	public String strTestGroupType;

	 
	
	public String strRequisitionDtlStatus;
	
	private String finalMandValues;
	
	private String mandCode;
	
	private String advisedBYDoctorName;
   private String appointmentRefNo;
   
 //sample coll vo
 	private String uomCode;
 	private String containerCode;
 	private String containerVolume;
 	private String tempSampleNo;
 	private String sampleNo;
	
 	private String testLabTestCodeWise;
	private String testCodeWise;  
	private String testCodeLabValue;
	private String delLabCode;
	private String delTestCode;
	private String dupTestCode;
	private String dupLabCode;
	private String appoitmentNo;
	private String hidAptNo;
	private String offlineAptDtl;
	private String lengendStatus;
	
	 
	 private String prvLabCode;
	 private String prvTestCode;
	 private String prvReqStatus;
 	private String appointmentNo;
 	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getAppointmentNo() {
		return appointmentNo;
	}
	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}
	public String getTestLabTestCodeWise() {
		return testLabTestCodeWise;
	}
	public void setTestLabTestCodeWise(String testLabTestCodeWise) {
		this.testLabTestCodeWise = testLabTestCodeWise;
	}
	public String getTestCodeWise() {
		return testCodeWise;
	}
	public void setTestCodeWise(String testCodeWise) {
		this.testCodeWise = testCodeWise;
	}
	public String getTestCodeLabValue() {
		return testCodeLabValue;
	}
	public void setTestCodeLabValue(String testCodeLabValue) {
		this.testCodeLabValue = testCodeLabValue;
	}
	public String getDelLabCode() {
		return delLabCode;
	}
	public void setDelLabCode(String delLabCode) {
		this.delLabCode = delLabCode;
	}
	public String getDelTestCode() {
		return delTestCode;
	}
	public void setDelTestCode(String delTestCode) {
		this.delTestCode = delTestCode;
	}
	public String getDupTestCode() {
		return dupTestCode;
	}
	public void setDupTestCode(String dupTestCode) {
		this.dupTestCode = dupTestCode;
	}
	public String getDupLabCode() {
		return dupLabCode;
	}
	public void setDupLabCode(String dupLabCode) {
		this.dupLabCode = dupLabCode;
	}
	public String getAppoitmentNo() {
		return appoitmentNo;
	}
	public void setAppoitmentNo(String appoitmentNo) {
		this.appoitmentNo = appoitmentNo;
	}
	public String getHidAptNo() {
		return hidAptNo;
	}
	public void setHidAptNo(String hidAptNo) {
		this.hidAptNo = hidAptNo;
	}
	public String getOfflineAptDtl() {
		return offlineAptDtl;
	}
	public void setOfflineAptDtl(String offlineAptDtl) {
		this.offlineAptDtl = offlineAptDtl;
	}
	public String getLengendStatus() {
		return lengendStatus;
	}
	public void setLengendStatus(String lengendStatus) {
		this.lengendStatus = lengendStatus;
	}
	public String getPrvLabCode() {
		return prvLabCode;
	}
	public void setPrvLabCode(String prvLabCode) {
		this.prvLabCode = prvLabCode;
	}
	public String getPrvTestCode() {
		return prvTestCode;
	}
	public void setPrvTestCode(String prvTestCode) {
		this.prvTestCode = prvTestCode;
	}
	public String getPrvReqStatus() {
		return prvReqStatus;
	}
	public void setPrvReqStatus(String prvReqStatus) {
		this.prvReqStatus = prvReqStatus;
	}
	public String getAppointmentRefNo() {
	return appointmentRefNo;
}
public void setAppointmentRefNo(String appointmentRefNo) {
	this.appointmentRefNo = appointmentRefNo;
}
	public String getAdvisedBYDoctorName() {
		return advisedBYDoctorName;
	}
	public void setAdvisedBYDoctorName(String advisedBYDoctorName) {
		this.advisedBYDoctorName = advisedBYDoctorName;
	}
	public String getMandCode() {
		return mandCode;
	}
	public void setMandCode(String mandCode) {
		this.mandCode = mandCode;
	}
	public String getFinalMandValues() {
		return finalMandValues;
	}
	public void setFinalMandValues(String finalMandValues) {
		this.finalMandValues = finalMandValues;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrRequsitionDNo() {
		return strRequsitionDNo;
	}
	public void setStrRequsitionDNo(String strRequsitionDNo) {
		this.strRequsitionDNo = strRequsitionDNo;
	}
	public String getStrIsAppointment() {
		return strIsAppointment;
	}
	public void setStrIsAppointment(String strIsAppointment) {
		this.strIsAppointment = strIsAppointment;
	}
	public String getStrLabCode() {
		return strLabCode;
	}
	public void setStrLabCode(String strLabCode) {
		this.strLabCode = strLabCode;
	}
	public String getStrTestCode() {
		return strTestCode;
	}
	public void setStrTestCode(String strTestCode) {
		this.strTestCode = strTestCode;
	}
	public String getStrIsConfidential() {
		return strIsConfidential;
	}
	public void setStrIsConfidential(String strIsConfidential) {
		this.strIsConfidential = strIsConfidential;
	}
	public String getStrPriority() {
		return strPriority;
	}
	public void setStrPriority(String strPriority) {
		this.strPriority = strPriority;
	}
	public String getStrResultDate() {
		return strResultDate;
	}
	public void setStrResultDate(String strResultDate) {
		this.strResultDate = strResultDate;
	}
	public String getStrSampleNo() {
		return strSampleNo;
	}
	public void setStrSampleNo(String strSampleNo) {
		this.strSampleNo = strSampleNo;
	}
	public String getStrAppRefNo() {
		return strAppRefNo;
	}
	public void setStrAppRefNo(String strAppRefNo) {
		this.strAppRefNo = strAppRefNo;
	}
	public String getStrTempSampleNo() {
		return strTempSampleNo;
	}
	public void setStrTempSampleNo(String strTempSampleNo) {
		this.strTempSampleNo = strTempSampleNo;
	}
	public String getStrGroupCode() {
		return strGroupCode;
	}
	public void setStrGroupCode(String strGroupCode) {
		this.strGroupCode = strGroupCode;
	}
	public String getStrGroupType() {
		return strGroupType;
	}
	public void setStrGroupType(String strGroupType) {
		this.strGroupType = strGroupType;
	}
	public String getStrCancellationDate() {
		return strCancellationDate;
	}
	public void setStrCancellationDate(String strCancellationDate) {
		this.strCancellationDate = strCancellationDate;
	}
	public String getStrCancellationSeatId() {
		return strCancellationSeatId;
	}
	public void setStrCancellationSeatId(String strCancellationSeatId) {
		this.strCancellationSeatId = strCancellationSeatId;
	}
	public String getStrBillNo() {
		return strBillNo;
	}
	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}
	public String getStrResValSeatID() {
		return strResValSeatID;
	}
	public void setStrResValSeatID(String strResValSeatID) {
		this.strResValSeatID = strResValSeatID;
	}
	public String getStrResValidationDate() {
		return strResValidationDate;
	}
	public void setStrResValidationDate(String strResValidationDate) {
		this.strResValidationDate = strResValidationDate;
	}
	public String getStrResultValidationSeatID() {
		return strResultValidationSeatID;
	}
	public void setStrResultValidationSeatID(String strResultValidationSeatID) {
		this.strResultValidationSeatID = strResultValidationSeatID;
	}
	public String getStrSampleCode() {
		return strSampleCode;
	}
	public void setStrSampleCode(String strSampleCode) {
		this.strSampleCode = strSampleCode;
	}
	public String getStrResultReValidateDate() {
		return strResultReValidateDate;
	}
	public void setStrResultReValidateDate(String strResultReValidateDate) {
		this.strResultReValidateDate = strResultReValidateDate;
	}
	public String getStrResultPrintSeatId() {
		return strResultPrintSeatId;
	}
	public void setStrResultPrintSeatId(String strResultPrintSeatId) {
		this.strResultPrintSeatId = strResultPrintSeatId;
	}
	public String getStrDailyLabNo() {
		return strDailyLabNo;
	}
	public void setStrDailyLabNo(String strDailyLabNo) {
		this.strDailyLabNo = strDailyLabNo;
	}
	public String getStrPatAcceptanceDateTime() {
		return strPatAcceptanceDateTime;
	}
	public void setStrPatAcceptanceDateTime(String strPatAcceptanceDateTime) {
		this.strPatAcceptanceDateTime = strPatAcceptanceDateTime;
	}
	public String getStrResPrintDate() {
		return strResPrintDate;
	}
	public void setStrResPrintDate(String strResPrintDate) {
		this.strResPrintDate = strResPrintDate;
	}
	public String getStrPatRejectionReason() {
		return strPatRejectionReason;
	}
	public void setStrPatRejectionReason(String strPatRejectionReason) {
		this.strPatRejectionReason = strPatRejectionReason;
	}
	public String getStrResultModifySeatId() {
		return strResultModifySeatId;
	}
	public void setStrResultModifySeatId(String strResultModifySeatId) {
		this.strResultModifySeatId = strResultModifySeatId;
	}
	public String getStrResultModifyDate() {
		return strResultModifyDate;
	}
	public void setStrResultModifyDate(String strResultModifyDate) {
		this.strResultModifyDate = strResultModifyDate;
	}
	public String getStrPatRejectionAction() {
		return strPatRejectionAction;
	}
	public void setStrPatRejectionAction(String strPatRejectionAction) {
		this.strPatRejectionAction = strPatRejectionAction;
	}
	public String getStrRescheduleSeatId() {
		return strRescheduleSeatId;
	}
	public void setStrRescheduleSeatId(String strRescheduleSeatId) {
		this.strRescheduleSeatId = strRescheduleSeatId;
	}
	public String getStrWorkOrderSequence() {
		return strWorkOrderSequence;
	}
	public void setStrWorkOrderSequence(String strWorkOrderSequence) {
		this.strWorkOrderSequence = strWorkOrderSequence;
	}
	public String getStrSamRejectionAction() {
		return strSamRejectionAction;
	}
	public void setStrSamRejectionAction(String strSamRejectionAction) {
		this.strSamRejectionAction = strSamRejectionAction;
	}
	public String getStrSamRejectionReason() {
		return strSamRejectionReason;
	}
	public void setStrSamRejectionReason(String strSamRejectionReason) {
		this.strSamRejectionReason = strSamRejectionReason;
	}
	public String getStrTestDeleteSeatId() {
		return strTestDeleteSeatId;
	}
	public void setStrTestDeleteSeatId(String strTestDeleteSeatId) {
		this.strTestDeleteSeatId = strTestDeleteSeatId;
	}
	public String getStrRescheduleDate() {
		return strRescheduleDate;
	}
	public void setStrRescheduleDate(String strRescheduleDate) {
		this.strRescheduleDate = strRescheduleDate;
	}
	public String getStrTestDeleteDate() {
		return strTestDeleteDate;
	}
	public void setStrTestDeleteDate(String strTestDeleteDate) {
		this.strTestDeleteDate = strTestDeleteDate;
	}
	public String getStrTypeOfComponent() {
		return strTypeOfComponent;
	}
	public void setStrTypeOfComponent(String strTypeOfComponent) {
		this.strTypeOfComponent = strTypeOfComponent;
	}
	public String getStrReqNo() {
		return strReqNo;
	}
	public void setStrReqNo(String strReqNo) {
		this.strReqNo = strReqNo;
	}
	public String getStrIsAccepted() {
		return strIsAccepted;
	}
	public void setStrIsAccepted(String strIsAccepted) {
		this.strIsAccepted = strIsAccepted;
	}
	public String getStrPackingListNo() {
		return strPackingListNo;
	}
	public void setStrPackingListNo(String strPackingListNo) {
		this.strPackingListNo = strPackingListNo;
	}
	public String getStrMachineCode() {
		return strMachineCode;
	}
	public void setStrMachineCode(String strMachineCode) {
		this.strMachineCode = strMachineCode;
	}
	public String getStrAcceptanceDateTime() {
		return strAcceptanceDateTime;
	}
	public void setStrAcceptanceDateTime(String strAcceptanceDateTime) {
		this.strAcceptanceDateTime = strAcceptanceDateTime;
	}
	public String getStrAcceptanceSeatId() {
		return strAcceptanceSeatId;
	}
	public void setStrAcceptanceSeatId(String strAcceptanceSeatId) {
		this.strAcceptanceSeatId = strAcceptanceSeatId;
	}
	public String getStrCollDateTime() {
		return strCollDateTime;
	}
	public void setStrCollDateTime(String strCollDateTime) {
		this.strCollDateTime = strCollDateTime;
	}
	public String getStrCollectionSeatId() {
		return strCollectionSeatId;
	}
	public void setStrCollectionSeatId(String strCollectionSeatId) {
		this.strCollectionSeatId = strCollectionSeatId;
	}
	public String getStrSampleCollectionAreaCode() {
		return strSampleCollectionAreaCode;
	}
	public void setStrSampleCollectionAreaCode(String strSampleCollectionAreaCode) {
		this.strSampleCollectionAreaCode = strSampleCollectionAreaCode;
	}
	public String getStrAppointmentDate() {
		return strAppointmentDate;
	}
	public void setStrAppointmentDate(String strAppointmentDate) {
		this.strAppointmentDate = strAppointmentDate;
	}
	public String getStrAppointmentTime() {
		return strAppointmentTime;
	}
	public void setStrAppointmentTime(String strAppointmentTime) {
		this.strAppointmentTime = strAppointmentTime;
	}
	public String getStrRequisitionDtlStatus() {
		return strRequisitionDtlStatus;
	}
	public void setStrRequisitionDtlStatus(String strRequisitionDtlStatus) {
		this.strRequisitionDtlStatus = strRequisitionDtlStatus;
	}
	public String getStrTestGroupCode() {
		return strTestGroupCode;
	}
	public void setStrTestGroupCode(String strTestGroupCode) {
		this.strTestGroupCode = strTestGroupCode;
	}
	public String getStrTestGroupType() {
		return strTestGroupType;
	}
	public void setStrTestGroupType(String strTestGroupType) {
		this.strTestGroupType = strTestGroupType;
	}
	public String getUomCode() {
		return uomCode;
	}
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}
	public String getContainerCode() {
		return containerCode;
	}
	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}
	public String getContainerVolume() {
		return containerVolume;
	}
	public void setContainerVolume(String containerVolume) {
		this.containerVolume = containerVolume;
	}
	public String getTempSampleNo() {
		return tempSampleNo;
	}
	public void setTempSampleNo(String tempSampleNo) {
		this.tempSampleNo = tempSampleNo;
	}
	public String getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}
	 
	
	
}
