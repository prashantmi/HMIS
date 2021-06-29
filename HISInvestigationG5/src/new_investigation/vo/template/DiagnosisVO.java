package new_investigation.vo.template;

import hisglobal.vo.ValueObject;

public class DiagnosisVO extends ValueObject{
private String diagnosisName;
private String diagnosisCode;
private String inDiagnosisCode;
private String dtrNo;
private String dMode;
public String getDMode() {
	return dMode;
}
public void setDMode(String mode) {
	dMode = mode;
}
public String getDtrNo() {
	return dtrNo;
}
public void setDtrNo(String dtrNo) {
	this.dtrNo = dtrNo;
}
public String getDiagnosisName() {
	return diagnosisName;
}
public void setDiagnosisName(String diagnosisName) {
	this.diagnosisName = diagnosisName;
}
public String getDiagnosisCode() {
	return diagnosisCode;
}
public void setDiagnosisCode(String diagnosisCode) {
	this.diagnosisCode = diagnosisCode;
}
public String getInDiagnosisCode() {
	return inDiagnosisCode;
}
public void setInDiagnosisCode(String inDiagnosisCode) {
	this.inDiagnosisCode = inDiagnosisCode;
}
}
