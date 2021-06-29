/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TemplateHelper.vo;

import DataHelper.Config;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */




public class TemplateQueryParameterVO extends ValueObject {

	public String patCRNo;
	public String patEpisodeCode;
	public String patDeptCode;
	public String laboratoryCode;
	public String laboratory;
	public String testCode;
	public String testName;
	public String patDeptUnitCode;
	public String patDeptUnit;
    public String patAdmissionNo;
    public String patVisitNo;
    public String patGenderCode;
    public String patAge;
    public String requisitionDNo;
    public String sampleNo;
    public String groupCode;
    public String testGroupCode;
	
	public String hospitalCode=Config.HOSPITAL_CODE;
	
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
	}
	public String getPatEpisodeCode() {
		return patEpisodeCode;
	}
	public void setPatEpisodeCode(String patEpisodeCode) {
		this.patEpisodeCode = patEpisodeCode;
	}
	public String getPatDeptCode() {
		return patDeptCode;
	}
	public String getPatAge() {
		if(patAge==null)
			return null;
		else
		{
		String[] ageSplit=null;
		
		
		if(patAge!=null && patAge.equals("")==false)
			ageSplit=patAge.replaceAll(" ", "#").split("#");
		
		
		return ageSplit[0];
		}
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public void setPatDeptCode(String patDeptCode) {
		this.patDeptCode = patDeptCode;
	}
	public String getLaboratoryCode() {
		return laboratoryCode;
	}
	public void setLaboratoryCode(String laboratoryCode) {
		this.laboratoryCode = laboratoryCode;
	}
	public String getLaboratory() {
		return laboratory;
	}
	public void setLaboratory(String laboratory) {
		this.laboratory = laboratory;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getPatDeptUnitCode() {
		return patDeptUnitCode;
	}
	public void setPatDeptUnitCode(String patDeptUnitCode) {
		this.patDeptUnitCode = patDeptUnitCode;
	}
	public String getPatDeptUnit() {
		return patDeptUnit;
	}
	public void setPatDeptUnit(String patDeptUnit) {
		this.patDeptUnit = patDeptUnit;
	}
	public String getPatAdmissionNo() {
		return patAdmissionNo;
	}
	public void setPatAdmissionNo(String patAdmissionNo) {
		this.patAdmissionNo = patAdmissionNo;
	}
	public String getPatVisitNo() {
		return patVisitNo;
	}
	public void setPatVisitNo(String patVisitNo) {
		this.patVisitNo = patVisitNo;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
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
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getTestGroupCode() {
		return testGroupCode;
	}
	public void setTestGroupCode(String testGroupCode) {
		this.testGroupCode = testGroupCode;
	}
	
}

