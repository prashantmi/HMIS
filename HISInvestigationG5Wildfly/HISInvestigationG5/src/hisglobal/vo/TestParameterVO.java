package hisglobal.vo;

import hisglobal.utility.Entry;

import java.util.List;

public class TestParameterVO extends ValueObject
{
	private String level;
	private String parameterCode;
	private String testParameterCode;
	private String testParameterName;
	private String testParameterParentID;
	private String criteriaCode;
	private List<CriteriaRefValueVO> criteriaRefObj;   
    private String testValueType;
    private List<Entry> options;
    private String objectID;
    private String levelID;
    private List<String> childElement;
    private String parentID;
    private boolean isElementProcessed;
    private int rowNo;
    private int colNo;
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public int getColNo() {
		return colNo;
	}
	public void setColNo(int colNo) {
		this.colNo = colNo;
	}
	public boolean isElementProcessed() {
		return isElementProcessed;
	}
	public void setElementProcessed(boolean isElementProcessed) {
		this.isElementProcessed = isElementProcessed;
	}
	public String getCriteriaCode() {
		return criteriaCode;
	}
	public void setCriteriaCode(String criteriaCode) {
		this.criteriaCode = criteriaCode;
	}
	public List<CriteriaRefValueVO> getCriteriaRefObj() {
		return criteriaRefObj;
	}
	public void setCriteriaRefObj(List<CriteriaRefValueVO> criteriaRefObj) {
		this.criteriaRefObj = criteriaRefObj;
	}
	public String getLevelID() {
		return levelID;
	}
	public void setLevelID(String levelID) {
		this.levelID = levelID;
	}
	public String getObjectID() {
		return objectID;
	}
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}
	public String getTestParameterCode() {
		return testParameterCode;
	}
	public void setTestParameterCode(String testParameterCode) {
		this.testParameterCode = testParameterCode;
	}
	public String getTestParameterParentID() {
		return testParameterParentID;
	}
	public void setTestParameterParentID(String testParameterParentID) {
		this.testParameterParentID = testParameterParentID;
	}
	public String getTestValueType() {
		return testValueType;
	}
	public void setTestValueType(String testValueType) {
		this.testValueType = testValueType;
	}
	public List<String> getChildElement() {
		return childElement;
	}
	public void setChildElement(List<String> childElement) {
		this.childElement = childElement;
	}
	public String getTestParameterName() {
		return testParameterName;
	}
	public void setTestParameterName(String testParameterName) {
		this.testParameterName = testParameterName;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getParameterCode() {
		return parameterCode;
	}
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

}
