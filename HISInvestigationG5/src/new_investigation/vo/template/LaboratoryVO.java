package new_investigation.vo.template;

import hisglobal.vo.ValueObject;

public class LaboratoryVO extends ValueObject
{
	String deptName;
	String labName;
	String timeName;
	String labShort;
	String labType;
	String deptCode;
	String location;
	String labCode;
	String locCode;
	String remarks;
	String hospitalCode;
	String labIncharge;
	String labInchargeName;
	String buildingCode;
	String buildingName;
	String blockId;
	String blockName;
	String floorId;
	String floorName;
	String floorBlockId;
	String floorBlockName;
	String roomId;
	String roomName;
	String storeId;
	String isRequisitionForm;
	String defaultPageWidth;
	String defaultPageHeight;
	
	
	String workOderProcessDataRequired="1";    //workOder Processing DataRequired
	String rangeDataProcessRequired="1";     //range Data ProcessingRequired
	String dbElementProcessRequired="0";   //database Element ProcessingRequired
	String templateProcessRequired="0";      //template Processing Required
	String reqFormProcessingRequired="0";   //requisiton Form Processing Required
	String sampleColFormProcessRequired="0";   //sampleCollection Form ProcessingRequired
	String imagesProcessRequired="0";   //imagesProcessing Required
	String siteDiagnosisProcessRequired="0";   //site Diagnosis ProcessingRequired
	String microbialDataProcessRequired="0";  //microbial Data ProcessingRequired
	String rptCRDNoDisplayRequired="1";     //report CRDNoD isplayRequired
	String resultValdtnDtDisplayRequired="1"; // result Validation DateDisplayRequired
	String resultEnterByDisplayRequired="1";   //result EnteredBy DisplayRequired
	String logoName;
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getTimeName() {
		return timeName;
	}
	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}
	public String getLabShort() {
		return labShort;
	}
	public void setLabShort(String labShort) {
		this.labShort = labShort;
	}
	public String getLabType() {
		return labType;
	}
	public void setLabType(String labType) {
		this.labType = labType;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getLocCode() {
		return locCode;
	}
	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getLabIncharge() {
		return labIncharge;
	}
	public void setLabIncharge(String labIncharge) {
		this.labIncharge = labIncharge;
	}
	public String getLabInchargeName() {
		return labInchargeName;
	}
	public void setLabInchargeName(String labInchargeName) {
		this.labInchargeName = labInchargeName;
	}
	public String getBuildingCode() {
		return buildingCode;
	}
	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public String getFloorBlockId() {
		return floorBlockId;
	}
	public void setFloorBlockId(String floorBlockId) {
		this.floorBlockId = floorBlockId;
	}
	public String getFloorBlockName() {
		return floorBlockName;
	}
	public void setFloorBlockName(String floorBlockName) {
		this.floorBlockName = floorBlockName;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getIsRequisitionForm() {
		return isRequisitionForm;
	}
	public void setIsRequisitionForm(String isRequisitionForm) {
		this.isRequisitionForm = isRequisitionForm;
	}
	public String getDefaultPageWidth() {
		return defaultPageWidth;
	}
	public void setDefaultPageWidth(String defaultPageWidth) {
		this.defaultPageWidth = defaultPageWidth;
	}
	public String getDefaultPageHeight() {
		return defaultPageHeight;
	}
	public void setDefaultPageHeight(String defaultPageHeight) {
		this.defaultPageHeight = defaultPageHeight;
	}
	public String getWorkOderProcessDataRequired() {
		return workOderProcessDataRequired;
	}
	public void setWorkOderProcessDataRequired(String workOderProcessDataRequired) {
		this.workOderProcessDataRequired = workOderProcessDataRequired;
	}
	public String getRangeDataProcessRequired() {
		return rangeDataProcessRequired;
	}
	public void setRangeDataProcessRequired(String rangeDataProcessRequired) {
		this.rangeDataProcessRequired = rangeDataProcessRequired;
	}
	public String getDbElementProcessRequired() {
		return dbElementProcessRequired;
	}
	public void setDbElementProcessRequired(String dbElementProcessRequired) {
		this.dbElementProcessRequired = dbElementProcessRequired;
	}
	public String getTemplateProcessRequired() {
		return templateProcessRequired;
	}
	public void setTemplateProcessRequired(String templateProcessRequired) {
		this.templateProcessRequired = templateProcessRequired;
	}
	public String getReqFormProcessingRequired() {
		return reqFormProcessingRequired;
	}
	public void setReqFormProcessingRequired(String reqFormProcessingRequired) {
		this.reqFormProcessingRequired = reqFormProcessingRequired;
	}
	
	public String getImagesProcessRequired() {
		return imagesProcessRequired;
	}
	public void setImagesProcessRequired(String imagesProcessRequired) {
		this.imagesProcessRequired = imagesProcessRequired;
	}
	public String getSiteDiagnosisProcessRequired() {
		return siteDiagnosisProcessRequired;
	}
	public void setSiteDiagnosisProcessRequired(String siteDiagnosisProcessRequired) {
		this.siteDiagnosisProcessRequired = siteDiagnosisProcessRequired;
	}
	public String getMicrobialDataProcessRequired() {
		return microbialDataProcessRequired;
	}
	public void setMicrobialDataProcessRequired(String microbialDataProcessRequired) {
		this.microbialDataProcessRequired = microbialDataProcessRequired;
	}
	public String getRptCRDNoDisplayRequired() {
		return rptCRDNoDisplayRequired;
	}
	public void setRptCRDNoDisplayRequired(String rptCRDNoDisplayRequired) {
		this.rptCRDNoDisplayRequired = rptCRDNoDisplayRequired;
	}
	
	public String getResultEnterByDisplayRequired() {
		return resultEnterByDisplayRequired;
	}
	public void setResultEnterByDisplayRequired(String resultEnterByDisplayRequired) {
		this.resultEnterByDisplayRequired = resultEnterByDisplayRequired;
	}
	
	public String getResultValdtnDtDisplayRequired() {
		return resultValdtnDtDisplayRequired;
	}
	public void setResultValdtnDtDisplayRequired(
			String resultValdtnDtDisplayRequired) {
		this.resultValdtnDtDisplayRequired = resultValdtnDtDisplayRequired;
	}
	public String getSampleColFormProcessRequired() {
		return sampleColFormProcessRequired;
	}
	public void setSampleColFormProcessRequired(String sampleColFormProcessRequired) {
		this.sampleColFormProcessRequired = sampleColFormProcessRequired;
	}
	public String getLogoName() {
		return logoName;
	}
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
	
}
