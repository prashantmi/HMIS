package inpatient.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class SpecialityWiseOperationFB extends ReportFB
{
	private String deptCode;
	private String unit;
	private String speciality;
	private String specialityForDept;
	private String option;
	private String operationType;
	private String combo;
	private String operationTypeForDept;
	private String operationTypeMajor;
	private String operationTypeMinor;
	private String label;
	private String fromHr;
	private String fromMin;
	private String toHr;
	private String toMin;
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		
		this.setDeptCode("%");
		this.setOperationType("%");
		this.setOperationTypeForDept("%");
		this.setSpeciality("%");
		this.setSpecialityForDept("%");
		
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getSpecialityForDept() {
		return specialityForDept;
	}

	public void setSpecialityForDept(String specialityForDept) {
		this.specialityForDept = specialityForDept;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getCombo() {
		return combo;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}

	public String getOperationTypeForDept() {
		return operationTypeForDept;
	}

	public void setOperationTypeForDept(String operationTypeForDept) {
		this.operationTypeForDept = operationTypeForDept;
	}

	public String getOperationTypeMajor() {
		return operationTypeMajor;
	}

	public void setOperationTypeMajor(String operationTypeMajor) {
		this.operationTypeMajor = operationTypeMajor;
	}

	public String getOperationTypeMinor() {
		return operationTypeMinor;
	}

	public void setOperationTypeMinor(String operationTypeMinor) {
		this.operationTypeMinor = operationTypeMinor;
	}

	public String getFromHr() {
		return fromHr;
	}

	public void setFromHr(String fromHr) {
		this.fromHr = fromHr;
	}

	public String getFromMin() {
		return fromMin;
	}

	public void setFromMin(String fromMin) {
		this.fromMin = fromMin;
	}

	public String getToHr() {
		return toHr;
	}

	public void setToHr(String toHr) {
		this.toHr = toHr;
	}

	public String getToMin() {
		return toMin;
	}

	public void setToMin(String toMin) {
		this.toMin = toMin;
	}
}
