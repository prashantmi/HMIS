package new_investigation.vo;

import java.util.List;

import hisglobal.vo.ValueObject;

public class invFungusProcessVO extends ValueObject {

	
	private String organismName;
	private String organismCode;
	private String antibioticCode;
	private String antibioticName;
	private String resistive; 
	private String sensitive;
	private String interresistive;
	private String antibioticClassName;
	private String antibioticsClassCode;
	private String value;
	private String reqDno;
	private String testParaCode;
	private String growth;
	private String remark;
	private String growthname;
	List<invFungusProcessVO> organismList;	
	List<invFungusProcessVO> AntibioticList;
	public String getOrganismName() {
		return organismName;
	}
	public void setOrganismName(String organismName) {
		this.organismName = organismName;
	}
	public String getOrganismCode() {
		return organismCode;
	}
	public void setOrganismCode(String organismCode) {
		this.organismCode = organismCode;
	}
	public String getAntibioticCode() {
		return antibioticCode;
	}
	public void setAntibioticCode(String antibioticCode) {
		this.antibioticCode = antibioticCode;
	}
	public String getAntibioticName() {
		return antibioticName;
	}
	public void setAntibioticName(String antibioticName) {
		this.antibioticName = antibioticName;
	}
	public String getResistive() {
		return resistive;
	}
	public void setResistive(String resistive) {
		this.resistive = resistive;
	}
	public String getSensitive() {
		return sensitive;
	}
	public void setSensitive(String sensitive) {
		this.sensitive = sensitive;
	}
	public String getInterresistive() {
		return interresistive;
	}
	public void setInterresistive(String interresistive) {
		this.interresistive = interresistive;
	}
	public String getAntibioticClassName() {
		return antibioticClassName;
	}
	public void setAntibioticClassName(String antibioticClassName) {
		this.antibioticClassName = antibioticClassName;
	}
	public String getAntibioticsClassCode() {
		return antibioticsClassCode;
	}
	public void setAntibioticsClassCode(String antibioticsClassCode) {
		this.antibioticsClassCode = antibioticsClassCode;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<invFungusProcessVO> getOrganismList() {
		return organismList;
	}
	public void setOrganismList(List<invFungusProcessVO> organismList) {
		this.organismList = organismList;
	}
	public List<invFungusProcessVO> getAntibioticList() {
		return AntibioticList;
	}
	public void setAntibioticList(List<invFungusProcessVO> antibioticList) {
		AntibioticList = antibioticList;
	}
	public String getReqDno() {
		return reqDno;
	}
	public void setReqDno(String reqDno) {
		this.reqDno = reqDno;
	}
	public String getTestParaCode() {
		return testParaCode;
	}
	public void setTestParaCode(String testParaCode) {
		this.testParaCode = testParaCode;
	}
	public String getGrowth() {
		return growth;
	}
	public void setGrowth(String growth) {
		this.growth = growth;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGrowthname() {
		return growthname;
	}
	public void setGrowthname(String growthname) {
		this.growthname = growthname;
	}
	
	
}
