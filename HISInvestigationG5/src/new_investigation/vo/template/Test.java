package new_investigation.vo.template;

import java.util.List;

import new_investigation.InvestigationConfig;

import org.w3c.dom.Document;

import hisglobal.vo.ValueObject;

public class Test extends ValueObject {
	private String  testType;
	private String  testName;
	private String  testCode;
	private String  groupName;
	private String  groupCode="";
	private String  groupType;
	private String  groupTypeName="";
	private String  priority;
	private String  laboratoryName;
	private String	laboratoryCode;
	private String  selectedLab; 
	private String  proposedTestDate;
	private String  appointmentSlotReferenceId;
	private String  appointmentSlotStartTime;
	private String  appointmentSlotEndTime;
	private String  testRemarks;
	private String  requisitionDNo;
	private String  requisitionType;
	private String  workOrderNo;// used for storing daily test number/ lab number
	private String  mandatoryInfoPresent="0";
	//0 for no Appointment required, 
	//1 for all department before Appointment
	//2 all department After billing
	//3 By owning Laboratory before billing
	//4 By owning Laboratory After Billing
	private String  isAppointmentBased="0"; 
	private String  isMultiSessionTest="0";
	private String  testStatus;
	private String  defaultNoOfSession;
	private String  defaultDuration;
	private String  isMandatoryAssociated;
	private List<TemplateRepresentationClass> mandatoryInfoDtl;
	//private List<MultiSessionDetail> multisessionalDetailList;
	private String  patAcceptanceDate; 
	private String  isPatientAccepted; 
	private String  patientRejectionReason; 
	private String  patientRejectionAction; 
	private String  patAcceptanceStatus; 
	private String multisessionRegrouped;
	private String isProcedure;
	private String raisedGroupNo;
	private String outsideSampleId;
	private String testSampleVolume;
	private String selectedUnitOfVolumeCode;

	private String isBilling;
	private List<Test> associatedTest;
	private String testDay;
	private String associatedSampleNo;
	private String billDetail;
	private Document testDocument;
	private String templateDocumentString;
	private String isRequisitionFormrequired;
	private List<PentaStringObject> requisitionFormDetailList;
	private String labRequisitionFormRequired;
	private boolean requisitionFormFilled=false;
	
	//private List<ImageUtilityVO> editedImageList;
	
	//public Map<String,List<ImageUtilityVO>> editedImageMap;
	private String priorityColor="";
	private String defaultSampleCode; //for change request by vijay on 27 dec 2010 
	
	String defaultGroupSampleCode;
	String defaultGroupSampleName;
	String defaultGroupSampleQty;
	String defaultGroupSampleUOMCode;
	String defaultGroupSampleUOM;
	String defaultGroupSampleContainerCode;
	String defaultGroupSampleContainerName;
	
	
	String tempSampleNo;
	String sampleNo;
	String previousMachineID;
	String machineID;
	String rackNo;
	String rejectionReason;
	String isManualLabNo;
	String labNo;
	String raisingDeptCode;
	String sessionNo;
	String isMultiSessionRegrouped;
	String isGrossingRequired;
	String typeOfComponent;
	private List<Entry> machineList;
	private String machineStringforCombo; 
	private boolean isGroup = false;
	
	
	public boolean isGroup() {
		return isGroup;
	}
	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}
	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	public String getRejectionAction() {
		return rejectionAction;
	}
	public void setRejectionAction(String rejectionAction) {
		this.rejectionAction = rejectionAction;
	}
	String rejectionAction;
	
	
	public String getMachineID() {
		return machineID;
	}
	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}
	public String getRackNo() {
		return rackNo;
	}
	public void setRackNo(String rackNo) {
		this.rackNo = rackNo;
	}
	public String getDefaultSampleCode() {
		return defaultSampleCode;
	}
	public void setDefaultSampleCode(String defaultSampleCode) {
		this.defaultSampleCode = defaultSampleCode;
	}
	public String getPriorityColor() {
		String pColor=this.priorityColor;
		if(priority!=null)
		{
			 pColor=InvestigationConfig.PRIORITY_NORMALCOLOR;
		if(priority.equals("High"))
			pColor=InvestigationConfig.PRIORITY_HIGHCOLOR;
		else if(priority.equals("Normal"))
			pColor=InvestigationConfig.PRIORITY_NORMALCOLOR;
		else
			pColor=InvestigationConfig.PRIORITY_LOWCOLOR;
		}
		
		return pColor;
	}
	public void setPriorityColor(String priorityColor) {
		this.priorityColor = priorityColor;
	}
	
	public String getLabRequisitionFormRequired() {
		return labRequisitionFormRequired;
	}
	public void setLabRequisitionFormRequired(String labRequisitionFormRequired) {
		this.labRequisitionFormRequired = labRequisitionFormRequired;
	}
	/*public Map<String, List<ImageUtilityVO>> getEditedImageMap() {
		return editedImageMap;
	}
	public void setEditedImageMap(Map<String, List<ImageUtilityVO>> editedImageMap) {
		this.editedImageMap = editedImageMap;
	}
	public List<ImageUtilityVO> getEditedImageList() {
		return editedImageList;
	}
	public void setEditedImageList(List<ImageUtilityVO> editedImageList) {
		this.editedImageList = editedImageList;
	}*/
	public boolean isRequisitionFormFilled() {
		return requisitionFormFilled;
	}
	public void setRequisitionFormFilled(boolean requisitionFormFilled) {
		this.requisitionFormFilled = requisitionFormFilled;
	}
	public List<PentaStringObject> getRequisitionFormDetailList() {
		return requisitionFormDetailList;
	}
	public void setRequisitionFormDetailList(
			List<PentaStringObject> requisitionFormDetailList) {
		this.requisitionFormDetailList = requisitionFormDetailList;
	}
	public String getIsRequisitionFormrequired() {
		return isRequisitionFormrequired;
	}
	public void setIsRequisitionFormrequired(String isRequisitionFormrequired) {
		this.isRequisitionFormrequired = isRequisitionFormrequired;
	}
	public Document getTestDocument() {
		return testDocument;
	}
	public void setTestDocument(Document testDocument) {
		this.testDocument = testDocument;
	}
	public String getTemplateDocumentString() {
		return templateDocumentString;
	}
	public void setTemplateDocumentString(String templateDocumentString) {
		this.templateDocumentString = templateDocumentString;
	}
	public String getBillDetail() {
		return billDetail;
	}
	public void setBillDetail(String billDetail) {
		this.billDetail = billDetail;
	}
	public String getAssociatedSampleNo() {
		return associatedSampleNo;
	}
	public void setAssociatedSampleNo(String associatedSampleNo) {
		this.associatedSampleNo = associatedSampleNo;
	}
	public String getTestDay() {
		return testDay;
	}
	public void setTestDay(String testDay) {
		this.testDay = testDay;
	}
	public List<Test> getAssociatedTest() {
		return associatedTest;
	}
	public void setAssociatedTest(List<Test> associatedTest) {
		this.associatedTest = associatedTest;
	}
	public String getIsBilling() {
		return isBilling;
	}
	public void setIsBilling(String isBilling) {
		this.isBilling = isBilling;
	}

	public String getTestSampleVolume() {
		return testSampleVolume;
	}
	public void setTestSampleVolume(String testSampleVolume) {
		this.testSampleVolume = testSampleVolume;
	}
	public String getSelectedUnitOfVolumeCode() {
		return selectedUnitOfVolumeCode;
	}
	public void setSelectedUnitOfVolumeCode(String selectedUnitOfVolumeCode) {
		this.selectedUnitOfVolumeCode = selectedUnitOfVolumeCode;
	}
	public String getOutsideSampleId() {
		return outsideSampleId;
	}
	public void setOutsideSampleId(String outsideSampleId) {
		this.outsideSampleId = outsideSampleId;
	}
	public String getRaisedGroupNo() {
		return raisedGroupNo;
	}
	public void setRaisedGroupNo(String raisedGroupNo) {
		this.raisedGroupNo = raisedGroupNo;
	}
	public String getPatAcceptanceDate() {
		return patAcceptanceDate;
	}
	public String getIsProcedure() {
		return isProcedure;
	}
	public void setIsProcedure(String isProcedure) {
		this.isProcedure = isProcedure;
	}
	public void setPatAcceptanceDate(String patAcceptanceDate) {
		this.patAcceptanceDate = patAcceptanceDate;
	}
	public String getIsPatientAccepted() {
		return isPatientAccepted;
	}
	public void setIsPatientAccepted(String isPatientAccepted) {
		this.isPatientAccepted = isPatientAccepted;
	}
	public String getPatientRejectionReason() {
		return patientRejectionReason;
	}
	public void setPatientRejectionReason(String patientRejectionReason) {
		this.patientRejectionReason = patientRejectionReason;
	}
	public String getPatientRejectionAction() {
		return patientRejectionAction;
	}
	public void setPatientRejectionAction(String patientRejectionAction) {
		this.patientRejectionAction = patientRejectionAction;
	}
	public String getPatAcceptanceStatus() {
		return patAcceptanceStatus;
	}
	public void setPatAcceptanceStatus(String patAcceptanceStatus) {
		this.patAcceptanceStatus = patAcceptanceStatus;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getGroupTypeName() {
		return groupTypeName;
	}
	public void setGroupTypeName(String groupTypeName) {
		this.groupTypeName = groupTypeName;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getLaboratoryName() {
		return laboratoryName;
	}
	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}
	public String getLaboratoryCode() {
		return laboratoryCode;
	}
	public void setLaboratoryCode(String laboratoryCode) {
		this.laboratoryCode = laboratoryCode;
	}
	public String getSelectedLab() {
		return selectedLab;
	}
	public void setSelectedLab(String selectedLab) {
		this.selectedLab = selectedLab;
	}
	public String getProposedTestDate() {
		return proposedTestDate;
	}
	public void setProposedTestDate(String proposedTestDate) {
		this.proposedTestDate = proposedTestDate;
	}
	public String getTestRemarks() {
		return testRemarks;
	}
	public void setTestRemarks(String testRemarks) {
		this.testRemarks = testRemarks;
	}
	public String getRequisitionDNo() {
		return requisitionDNo;
	}
	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}
	public String getRequisitionType() {
		return requisitionType;
	}
	public void setRequisitionType(String requisitionType) {
		this.requisitionType = requisitionType;
	}
	public String getWorkOrderNo() {
		return workOrderNo;
	}
	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}
	public String getMandatoryInfoPresent() {
		return mandatoryInfoPresent;
	}
	public void setMandatoryInfoPresent(String mandatoryInfoPresent) {
		this.mandatoryInfoPresent = mandatoryInfoPresent;
	}
	public String getIsAppointmentBased() {
		return isAppointmentBased;
	}
	public void setIsAppointmentBased(String isAppointmentBased) {
		this.isAppointmentBased = isAppointmentBased;
	}
	public String getIsMultiSessionTest() {
		return isMultiSessionTest;
	}
	public void setIsMultiSessionTest(String isMultiSessionTest) {
		this.isMultiSessionTest = isMultiSessionTest;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getDefaultNoOfSession() {
		return defaultNoOfSession;
	}
	public void setDefaultNoOfSession(String defaultNoOfSession) {
		this.defaultNoOfSession = defaultNoOfSession;
	}
	public String getDefaultDuration() {
		return defaultDuration;
	}
	public void setDefaultDuration(String defaultDuration) {
		this.defaultDuration = defaultDuration;
	}
	public String getAppointmentSlotReferenceId() {
		return appointmentSlotReferenceId;
	}
	public void setAppointmentSlotReferenceId(String appointmentSlotReferenceId) {
		this.appointmentSlotReferenceId = appointmentSlotReferenceId;
	}
	public String getAppointmentSlotStartTime() {
		return appointmentSlotStartTime;
	}
	public void setAppointmentSlotStartTime(String appointmentSlotStartTime) {
		this.appointmentSlotStartTime = appointmentSlotStartTime;
	}
	public String getAppointmentSlotEndTime() {
		return appointmentSlotEndTime;
	}
	public void setAppointmentSlotEndTime(String appointmentSlotEndTime) {
		this.appointmentSlotEndTime = appointmentSlotEndTime;
	}
	
	public static Test getInstance(String testType)
	{
		if(testType.equals("S"))
		{
			return new SampleBasedTest();
		}
		else if(testType.equals("P"))
		{
			return new PatientBasedTest();
		}
		else
		{
			return new SampleBasedTest();
			//return new SlideBasedTest();
		}
	}
	public String getIsMandatoryAssociated() {
		return isMandatoryAssociated;
	}
	public void setIsMandatoryAssociated(String isMandatoryAssociated) {
		this.isMandatoryAssociated = isMandatoryAssociated;
	}
	public List<TemplateRepresentationClass> getMandatoryInfoDtl() {
		return mandatoryInfoDtl;
	}
	public void setMandatoryInfoDtl(List<TemplateRepresentationClass> mandatoryInfoDtl) {
		this.mandatoryInfoDtl = mandatoryInfoDtl;
	}
//	public List<MultiSessionDetail> getMultisessionalDetailList() {
//		return multisessionalDetailList;
//	}
//	public void setMultisessionalDetailList(
//			List<MultiSessionDetail> multisessionalDetailList) {
//		this.multisessionalDetailList = multisessionalDetailList;
//	}
	public String getMultisessionRegrouped() {
		return multisessionRegrouped;
	}
	public void setMultisessionRegrouped(String multisessionRegrouped) {
		this.multisessionRegrouped = multisessionRegrouped;
	}
	
	void populatePriority()
	{
		if(this.priority.equals("0"))
		{
			this.priority="Normal";
		}
		else if(this.priority.equals("1"))
		{
			this.priority="High";
		}
		else
		{
			this.priority="low";
		}
	}
	/*public String getLabNo() {
		return labNo;
	}
	public void setLabNo(String labNo) {
		this.labNo = labNo;
	}*/
	public String getDefaultGroupSampleCode() {
		return defaultGroupSampleCode;
	}
	public void setDefaultGroupSampleCode(String defaultGroupSampleCode) {
		this.defaultGroupSampleCode = defaultGroupSampleCode;
	}
	public String getDefaultGroupSampleQty() {
		return defaultGroupSampleQty;
	}
	public void setDefaultGroupSampleQty(String defaultGroupSampleQty) {
		this.defaultGroupSampleQty = defaultGroupSampleQty;
	}
	public String getDefaultGroupSampleContainerCode() {
		return defaultGroupSampleContainerCode;
	}
	public void setDefaultGroupSampleContainerCode(
			String defaultGroupSampleContainerCode) {
		this.defaultGroupSampleContainerCode = defaultGroupSampleContainerCode;
	}
	public String getDefaultGroupSampleUOMCode() {
		return defaultGroupSampleUOMCode;
	}
	public void setDefaultGroupSampleUOMCode(String defaultGroupSampleUOMCode) {
		this.defaultGroupSampleUOMCode = defaultGroupSampleUOMCode;
	}
	public String getDefaultGroupSampleUOM() {
		return defaultGroupSampleUOM;
	}
	public void setDefaultGroupSampleUOM(String defaultGroupSampleUOM) {
		this.defaultGroupSampleUOM = defaultGroupSampleUOM;
	}
	public String getDefaultGroupSampleContainerName() {
		return defaultGroupSampleContainerName;
	}
	public void setDefaultGroupSampleContainerName(
			String defaultGroupSampleContainerName) {
		this.defaultGroupSampleContainerName = defaultGroupSampleContainerName;
	}
	public String getDefaultGroupSampleName() {
		return defaultGroupSampleName;
	}
	public void setDefaultGroupSampleName(String defaultGroupSampleName) {
		this.defaultGroupSampleName = defaultGroupSampleName;
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
	public String getPreviousMachineID() {
		return previousMachineID;
	}
	public void setPreviousMachineID(String previousMachineID) {
		this.previousMachineID = previousMachineID;
	}
	public String getIsManualLabNo() {
		return isManualLabNo;
	}
	public void setIsManualLabNo(String isManualLabNo) {
		this.isManualLabNo = isManualLabNo;
	}
	public String getLabNo() {
		return labNo;
	}
	public void setLabNo(String labNo) {
		this.labNo = labNo;
	}
	public String getRaisingDeptCode() {
		return raisingDeptCode;
	}
	public void setRaisingDeptCode(String raisingDeptCode) {
		this.raisingDeptCode = raisingDeptCode;
	}
	public String getSessionNo() {
		return sessionNo;
	}
	public void setSessionNo(String sessionNo) {
		this.sessionNo = sessionNo;
	}
	public String getIsMultiSessionRegrouped() {
		return isMultiSessionRegrouped;
	}
	public void setIsMultiSessionRegrouped(String isMultiSessionRegrouped) {
		this.isMultiSessionRegrouped = isMultiSessionRegrouped;
	}
	public String getIsGrossingRequired() {
		return isGrossingRequired;
	}
	public void setIsGrossingRequired(String isGrossingRequired) {
		this.isGrossingRequired = isGrossingRequired;
	}
	public String getTypeOfComponent() {
		return typeOfComponent;
	}
	public void setTypeOfComponent(String typeOfComponent) {
		this.typeOfComponent = typeOfComponent;
	}
	public List<Entry> getMachineList() {
		return machineList;
	}
	public void setMachineList(List<Entry> machineList) {
		this.machineList = machineList;
	}
	public String getMachineStringforCombo() {
		return machineStringforCombo;
	}
	public void setMachineStringforCombo(String machineStringforCombo) {
		this.machineStringforCombo = machineStringforCombo;
	}


	
	
	
}

