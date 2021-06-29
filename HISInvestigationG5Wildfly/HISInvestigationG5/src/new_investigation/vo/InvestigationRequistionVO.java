package new_investigation.vo;

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
	public String priority;
	private String advice;
	private String isrequisitionFormNeeded;
	private String requisitionFormData;
	
	  private String advisedByDocName;
		private String visitReason;
		  private String testwiseinst ;
	 
	
	public String strRequisitionDtlStatus;
	
	private String finalMandValues;
	
	private String mandCode;
	
	private String advisedBYDoctorName;
   private String appointmentRefNo;
	private String appointmentNo;
	private String errorMessage;
	
	
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
	 
	 	private String is_Sample_Received;
	 	private String collectedVolume;
	 	private String reqDno;
	 	private String changeCount;
	 	private String reportChange;
	 	private String reportChangeSeatId;
	 	private String reportChangeDate;
	  private String reportUrl;
	  private String reportDate;
	  private String piddata;
		private String pidtestcontains;
		private String labbaseddatetimeappt;
	    private String site;
	    private String  islabappointmentbased;
		private String isAppointment;
		  private String viewscount;
		    private String xraycount;
			private String advisedByDocNo;

			
			  private String chief_complaints_code[] ;
			  private String chief_complaints_name[] ;
			  private String diagnosis_code[] ;
			  private String diagnosis_name[] ;
			  private String is_pregnant ;
			  private String is_mlc ;
			  private String is_vip ;
			  private String is_dead ;
			  private String is_newborn ;
				private String requisitionwiseno;
				private String instruction_testwise ;
				  private String is_unknown ;
				  private String chiefname ;
				  private String diagname ;
				  private String patpuk ;
				  
				  
	public String getAppointmentNo() {
		return appointmentNo;
	}
	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
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
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getIsrequisitionFormNeeded() {
		return isrequisitionFormNeeded;
	}
	public void setIsrequisitionFormNeeded(String isrequisitionFormNeeded) {
		this.isrequisitionFormNeeded = isrequisitionFormNeeded;
	}
	public String getRequisitionFormData() {
		return requisitionFormData;
	}
	public void setRequisitionFormData(String requisitionFormData) {
		this.requisitionFormData = requisitionFormData;
	}
	public String getIs_Sample_Received() {
		return is_Sample_Received;
	}
	public void setIs_Sample_Received(String is_Sample_Received) {
		this.is_Sample_Received = is_Sample_Received;
	}
	public String getCollectedVolume() {
		return collectedVolume;
	}
	public void setCollectedVolume(String collectedVolume) {
		this.collectedVolume = collectedVolume;
	}
	public String getReqDno() {
		return reqDno;
	}
	public void setReqDno(String reqDno) {
		this.reqDno = reqDno;
	}
	public String getChangeCount() {
		return changeCount;
	}
	public void setChangeCount(String changeCount) {
		this.changeCount = changeCount;
	}
	public String getReportChange() {
		return reportChange;
	}
	public void setReportChange(String reportChange) {
		this.reportChange = reportChange;
	}
	public String getReportChangeSeatId() {
		return reportChangeSeatId;
	}
	public void setReportChangeSeatId(String reportChangeSeatId) {
		this.reportChangeSeatId = reportChangeSeatId;
	}
	public String getReportChangeDate() {
		return reportChangeDate;
	}
	public void setReportChangeDate(String reportChangeDate) {
		this.reportChangeDate = reportChangeDate;
	}
	public String getReportUrl() {
		return reportUrl;
	}
	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getPiddata() {
		return piddata;
	}
	public void setPiddata(String piddata) {
		this.piddata = piddata;
	}
	public String getPidtestcontains() {
		return pidtestcontains;
	}
	public void setPidtestcontains(String pidtestcontains) {
		this.pidtestcontains = pidtestcontains;
	}
	public String getLabbaseddatetimeappt() {
		return labbaseddatetimeappt;
	}
	public void setLabbaseddatetimeappt(String labbaseddatetimeappt) {
		this.labbaseddatetimeappt = labbaseddatetimeappt;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getIslabappointmentbased() {
		return islabappointmentbased;
	}
	public void setIslabappointmentbased(String islabappointmentbased) {
		this.islabappointmentbased = islabappointmentbased;
	}
	public String getIsAppointment() {
		return isAppointment;
	}
	public void setIsAppointment(String isAppointment) {
		this.isAppointment = isAppointment;
	}
	public String getViewscount() {
		return viewscount;
	}
	public void setViewscount(String viewscount) {
		this.viewscount = viewscount;
	}
	public String getXraycount() {
		return xraycount;
	}
	public void setXraycount(String xraycount) {
		this.xraycount = xraycount;
	}
	public String getAdvisedByDocNo() {
		return advisedByDocNo;
	}
	public void setAdvisedByDocNo(String advisedByDocNo) {
		this.advisedByDocNo = advisedByDocNo;
	}
	public String[] getChief_complaints_code() {
		return chief_complaints_code;
	}
	public void setChief_complaints_code(String[] chief_complaints_code) {
		this.chief_complaints_code = chief_complaints_code;
	}
	public String[] getChief_complaints_name() {
		return chief_complaints_name;
	}
	public void setChief_complaints_name(String[] chief_complaints_name) {
		this.chief_complaints_name = chief_complaints_name;
	}
	public String[] getDiagnosis_code() {
		return diagnosis_code;
	}
	public void setDiagnosis_code(String[] diagnosis_code) {
		this.diagnosis_code = diagnosis_code;
	}
	public String[] getDiagnosis_name() {
		return diagnosis_name;
	}
	public void setDiagnosis_name(String[] diagnosis_name) {
		this.diagnosis_name = diagnosis_name;
	}
	public String getIs_pregnant() {
		return is_pregnant;
	}
	public void setIs_pregnant(String is_pregnant) {
		this.is_pregnant = is_pregnant;
	}
	public String getIs_mlc() {
		return is_mlc;
	}
	public void setIs_mlc(String is_mlc) {
		this.is_mlc = is_mlc;
	}
	public String getIs_vip() {
		return is_vip;
	}
	public void setIs_vip(String is_vip) {
		this.is_vip = is_vip;
	}
	public String getIs_dead() {
		return is_dead;
	}
	public void setIs_dead(String is_dead) {
		this.is_dead = is_dead;
	}
	public String getIs_newborn() {
		return is_newborn;
	}
	public void setIs_newborn(String is_newborn) {
		this.is_newborn = is_newborn;
	}
	public String getRequisitionwiseno() {
		return requisitionwiseno;
	}
	public void setRequisitionwiseno(String requisitionwiseno) {
		this.requisitionwiseno = requisitionwiseno;
	}
	public String getInstruction_testwise() {
		return instruction_testwise;
	}
	public void setInstruction_testwise(String instruction_testwise) {
		this.instruction_testwise = instruction_testwise;
	}
	public String getIs_unknown() {
		return is_unknown;
	}
	public void setIs_unknown(String is_unknown) {
		this.is_unknown = is_unknown;
	}
	public String getAdvisedByDocName() {
		return advisedByDocName;
	}
	public void setAdvisedByDocName(String advisedByDocName) {
		this.advisedByDocName = advisedByDocName;
	}
	public String getVisitReason() {
		return visitReason;
	}
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}
	public String getTestwiseinst() {
		return testwiseinst;
	}
	public void setTestwiseinst(String testwiseinst) {
		this.testwiseinst = testwiseinst;
	}
	public String getChiefname() {
		return chiefname;
	}
	public void setChiefname(String chiefname) {
		this.chiefname = chiefname;
	}
	public String getDiagname() {
		return diagname;
	}
	public void setDiagname(String diagname) {
		this.diagname = diagname;
	}
	public String getPatpuk() {
		return patpuk;
	}
	public void setPatpuk(String patpuk) {
		this.patpuk = patpuk;
	}

	 
	
	
}
