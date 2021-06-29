package new_investigation.vo.template;

import java.io.Serializable;
import java.util.List;

public class TestGroup implements Serializable{
	String groupName;
	String groupCode;
	String groupType;
	String groupTypeCode;
	String labCode;
	String lab;
	String hospitalCode;
	String defaultGroupSampleCode;
	String defaultGroupSampleName;
	String defaultGroupSampleQty;
	String defaultGroupSampleUOMCode;
	String defaultGroupSampleUOM;
	String defaultGroupSampleContainerCode;
	String defaultGroupSampleContainerName;
	List <Test> test;
	
	
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<Test> getTest() {
		return test;
	}
	public void setTest(List<Test> test) {
		this.test = test;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getGroupTypeCode() {
		return groupTypeCode;
	}
	public void setGroupTypeCode(String groupTypeCode) {
		this.groupTypeCode = groupTypeCode;
	}
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
	
	
}