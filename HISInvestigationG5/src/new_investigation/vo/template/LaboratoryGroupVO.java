package new_investigation.vo.template;

import hisglobal.vo.ValueObject;

public class LaboratoryGroupVO extends ValueObject {
	
	private static final long serialVersionUID = 1L;
	private String   groupTypeCode;
	private String   groupTypeName;
	private String   groupName;
	private String   groupCode;
	private String   laboratoryName;
	private String	 laboratoryCode;
	
	private String 	 defaultSampleCode;
	private String 	 defaultSampleVolume;
	private String 	 defaultUom;
	private String   defaultContainerCode;
	private String   printedWithTemplateID;
	private String   printingType;
	public String getGroupTypeCode() {
		return groupTypeCode;
	}
	public void setGroupTypeCode(String groupTypeCode) {
		this.groupTypeCode = groupTypeCode;
	}
	public String getGroupTypeName() {
		return groupTypeName;
	}
	public void setGroupTypeName(String groupTypeName) {
		this.groupTypeName = groupTypeName;
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
	public String getDefaultSampleCode() {
		return defaultSampleCode;
	}
	public void setDefaultSampleCode(String defaultSampleCode) {
		this.defaultSampleCode = defaultSampleCode;
	}
	public String getPrintedWithTemplateID() {
		return printedWithTemplateID;
	}
	public void setPrintedWithTemplateID(String printedWithTemplateID) {
		this.printedWithTemplateID = printedWithTemplateID;
	}
	public String getPrintingType() {
		return printingType;
	}
	public void setPrintingType(String printingType) {
		this.printingType = printingType;
	}
	public String getDefaultSampleVolume() {
		return defaultSampleVolume;
	}
	public void setDefaultSampleVolume(String defaultSampleVolume) {
		this.defaultSampleVolume = defaultSampleVolume;
	}
	public String getDefaultContainerCode() {
		return defaultContainerCode;
	}
	public void setDefaultContainerCode(String defaultContainerCode) {
		this.defaultContainerCode = defaultContainerCode;
	}
	public String getDefaultUom() {
		return defaultUom;
	}
	public void setDefaultUom(String defaultUom) {
		this.defaultUom = defaultUom;
	}
	
	
	
	
	
	
	
	
}
