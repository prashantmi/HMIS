package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class FungusprocessVO extends ValueObject {

	
	private String organismname;
	private String organismName;
	private String organismcode;
	private String growth;
	private String antibioticname;
	private String antibioticcode;
	private String remark;
	private String result;
	private String growthname;
	
	private String requisitionDNo;
	private String testParaCode;
	private String requisitionNo;
	
	public String getOrganismname() {
		return organismname;
	}
	public void setOrganismname(String organismname) {
		this.organismname = organismname;
	}
	public String getOrganismcode() {
		return organismcode;
	}
	public void setOrganismcode(String organismcode) {
		this.organismcode = organismcode;
	}
	public String getGrowth() {
		return growth;
	}
	public void setGrowth(String growth) {
		this.growth = growth;
	}
	public String getAntibioticname() {
		return antibioticname;
	}
	public void setAntibioticname(String antibioticname) {
		this.antibioticname = antibioticname;
	}
	public String getAntibioticcode() {
		return antibioticcode;
	}
	public void setAntibioticcode(String antibioticcode) {
		this.antibioticcode = antibioticcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getOrganismName() {
		return organismName;
	}
	public void setOrganismName(String organismName) {
		this.organismName = organismName;
	}
	public String getGrowthname() {
		return growthname;
	}
	public void setGrowthname(String growthname) {
		this.growthname = growthname;
	}
	public String getRequisitionDNo() {
		return requisitionDNo;
	}
	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}
	public String getTestParaCode() {
		return testParaCode;
	}
	public void setTestParaCode(String testParaCode) {
		this.testParaCode = testParaCode;
	}
	public String getRequisitionNo() {
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	
	
	
}
