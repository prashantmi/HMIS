package opd.transaction.controller.fb;

import hisglobal.vo.EpisodeDiagnosisVO;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OpdDiagnosisFB extends ActionForm {
	
	
	private String icdCode[];
	private String dignosisName[];
	private String hospitalDiagnosisCode[];
	private String hospitalDiagnosisName[];

	private String diagonisticTypeCode[];
	private String remarks[];
	private String hmode;
	private String departmentUnitCode;
	private String departmentCode;
	private String episodeCode;
	private String patCrNo;
	private String mode;
	private String searchCode;
	private String searchDisease;
	private String selectedCode;
	private String clickedICDCode;
	private String numberOfRow="1";
	private EpisodeDiagnosisVO[] previousDiagnosisVO;
	private EpisodeDiagnosisVO[] arrayPreviousDiagnosisVO;
	private String repeat;
	private String diagnosisCodeType;
	private String unitDiagnosisCodeType;
	private String comboOptionString;
	private String selectedDiagnosis[];
	private String addDiagnosis[];
	private String repeatedDiagnosisLength;
	private String episodeVisitNo;
	
	private String addDiagnosisCode[];
	private String addDiagnosticTypeCode[];
	private String addDiagnosisRemarks[];
	private String addDiagnosisCancelRemarks[];
	private String addDiagnosisSite[];
	private String addDiagnosisCodeType[];
	private String selectedDiagCode;
	private String revokeDiagCode;
	private String icdNHospitalFlag;
	private String icdNHospitalFlagValue;
	private String diagnosisSite[];
	private String revokeDiagicdNHospitalFlag;
	private String comboDiagnosisSite;
	private String showRemarksTextArea;
	
	private String diagnosticCode;	
	private String icdName;
	
	private String snomedCTDiagnosisCode[]; // snomed integration
	private String snomedCTDiagnosisName[];  //snomed integration
	private String diagnosisSiteLabel[];
	private String diagnosticTypeName[];
	private String snomedCTDiagnosisSiteName[];
	private String snomedCTDiagnosisSiteCode[];
	private String selSnomedCTDiagnosisSiteCode;
	
	private String snomedCIdRemarks[]; // snomed integration
	private String snomedPTRemarks[];
	
	/**Added by Vasu on 18.Oct.2018 for ICD-O Integration*/
	private String diseaseCode[];
	private String diseaseSite[];
	private String diseaseSiteId[];
	private String morphCode[];
	private String morphTitle[];
	
	
	public String[] getSnomedCIdRemarks() {
		return snomedCIdRemarks;
	}
	public void setSnomedCIdRemarks(String[] snomedCIdRemarks) {
		this.snomedCIdRemarks = snomedCIdRemarks;
	}
	public String[] getSnomedPTRemarks() {
		return snomedPTRemarks;
	}
	public void setSnomedPTRemarks(String[] snomedPTemarks) {
		this.snomedPTRemarks = snomedPTemarks;
	}
	
		
	
	public String getSelectedDiagCode() {
		return selectedDiagCode;
	}
	public void setSelectedDiagCode(String selectedDiagCode) {
		this.selectedDiagCode = selectedDiagCode;
	}
	public String getRepeatedDiagnosisLength() {
		return repeatedDiagnosisLength;
	}
	public void setRepeatedDiagnosisLength(String repeatedDiagnosisLength) {
		this.repeatedDiagnosisLength = repeatedDiagnosisLength;
	}
	public String getComboOptionString() {
		return comboOptionString;
	}
	public String[] getSelectedDiagnosis() {
		return selectedDiagnosis;
	}
	public void setComboOptionString(String comboOptionString) {
		this.comboOptionString = comboOptionString;
	}
	public String getDiagnosisCodeType() {
		return diagnosisCodeType;
	}
	public void setDiagnosisCodeType(String diagnosisCodeType) {
		this.diagnosisCodeType = diagnosisCodeType;
	}
	public String getRepeat() {
		return repeat;
	}
	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
	public String getNumberOfRow() {
		return numberOfRow;
	}
	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}
	public String getSearchDisease() {
		return searchDisease;
	}
	public void setSearchDisease(String searchDisease) {
		this.searchDisease = searchDisease;
	}
	
	public String getSearchCode() {
		return searchCode;
	}
	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}
	public String getSelectedCode() {
		return selectedCode;
	}
	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setMode("");
		
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	
	public String[] getDiagonisticTypeCode() {
		return diagonisticTypeCode;
	}
	public void setDiagonisticTypeCode(String[] diagonisticTypeCode) {
		this.diagonisticTypeCode = diagonisticTypeCode;
	}
	public String[] getDignosisName() {
		return dignosisName;
	}
	public void setDignosisName(String[] dignosisName) {
		this.dignosisName = dignosisName;
	}
	public String[] getHospitalDiagnosisCode() {
		return hospitalDiagnosisCode;
	}
	public void setHospitalDiagnosisCode(String[] hospitalDiagnosisCode) {
		this.hospitalDiagnosisCode = hospitalDiagnosisCode;
	}
	public String[] getHospitalDiagnosisName() {
		return hospitalDiagnosisName;
	}
	public void setHospitalDiagnosisName(String[] hospitalDiagnosisName) {
		this.hospitalDiagnosisName = hospitalDiagnosisName;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}
	
	public String[] getIcdCode() {
		return icdCode;
	}
	public void setIcdCode(String[] icdCode) {
		this.icdCode = icdCode;
	}
	
	public String getUnitDiagnosisCodeType() {
		return unitDiagnosisCodeType;
	}
	public void setUnitDiagnosisCodeType(String unitDiagnosisCodeType) {
		this.unitDiagnosisCodeType = unitDiagnosisCodeType;
	}
	public EpisodeDiagnosisVO[] getArrayPreviousDiagnosisVO() {
		return arrayPreviousDiagnosisVO;
	}
	public void setArrayPreviousDiagnosisVO(
			EpisodeDiagnosisVO[] arrayPreviousDiagnosisVO) {
		this.arrayPreviousDiagnosisVO = arrayPreviousDiagnosisVO;
	}
	public void setSelectedDiagnosis(String[] selectedDiagnosis) {
		this.selectedDiagnosis = selectedDiagnosis;
	}
	public void setPreviousDiagnosisVO(EpisodeDiagnosisVO[] previousDiagnosisVO) {
		this.previousDiagnosisVO = previousDiagnosisVO;
	}
	public String[] getAddDiagnosis() {
		return addDiagnosis;
	}
	public void setAddDiagnosis(String[] addDiagnosis) {
		this.addDiagnosis = addDiagnosis;
	}
	public EpisodeDiagnosisVO[] getPreviousDiagnosisVO() {
		return previousDiagnosisVO;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String[] getAddDiagnosisCode() {
		return addDiagnosisCode;
	}
	public void setAddDiagnosisCode(String[] addDiagnosisCode) {
		this.addDiagnosisCode = addDiagnosisCode;
	}
	public String[] getAddDiagnosticTypeCode() {
		return addDiagnosticTypeCode;
	}
	public void setAddDiagnosticTypeCode(String[] addDiagnosticTypeCode) {
		this.addDiagnosticTypeCode = addDiagnosticTypeCode;
	}
	public String[] getAddDiagnosisRemarks() {
		return addDiagnosisRemarks;
	}
	public void setAddDiagnosisRemarks(String[] addDiagnosisRemarks) {
		this.addDiagnosisRemarks = addDiagnosisRemarks;
	}
	public String getRevokeDiagCode() {
		return revokeDiagCode;
	}
	public void setRevokeDiagCode(String revokeDiagCode) {
		this.revokeDiagCode = revokeDiagCode;
	}
	public String getIcdNHospitalFlag() {
		return icdNHospitalFlag;
	}
	public void setIcdNHospitalFlag(String icdNHospitalFlag) {
		this.icdNHospitalFlag = icdNHospitalFlag;
	}
	public String getIcdNHospitalFlagValue() {
		return icdNHospitalFlagValue;
	}
	public void setIcdNHospitalFlagValue(String icdNHospitalFlagValue) {
		this.icdNHospitalFlagValue = icdNHospitalFlagValue;
	}
	public String[] getDiagnosisSite() {
		return diagnosisSite;
	}
	public void setDiagnosisSite(String[] diagnosisSite) {
		this.diagnosisSite = diagnosisSite;
	}
	public String[] getAddDiagnosisSite() {
		return addDiagnosisSite;
	}
	public void setAddDiagnosisSite(String[] addDiagnosisSite) {
		this.addDiagnosisSite = addDiagnosisSite;
	}
	public String getRevokeDiagicdNHospitalFlag() {
		return revokeDiagicdNHospitalFlag;
	}
	public void setRevokeDiagicdNHospitalFlag(String revokeDiagicdNHospitalFlag) {
		this.revokeDiagicdNHospitalFlag = revokeDiagicdNHospitalFlag;
	}
	public String getComboDiagnosisSite() {
		return comboDiagnosisSite;
	}
	public void setComboDiagnosisSite(String comboDiagnosisSite) {
		this.comboDiagnosisSite = comboDiagnosisSite;
	}
	public String[] getAddDiagnosisCodeType() {
		return addDiagnosisCodeType;
	}
	public void setAddDiagnosisCodeType(String[] addDiagnosisCodeType) {
		this.addDiagnosisCodeType = addDiagnosisCodeType;
	}
	public String getShowRemarksTextArea() {
		return showRemarksTextArea;
	}
	public void setShowRemarksTextArea(String showRemarksTextArea) {
		this.showRemarksTextArea = showRemarksTextArea;
	}
	public String[] getAddDiagnosisCancelRemarks()
	{
		return addDiagnosisCancelRemarks;
	}
	public void setAddDiagnosisCancelRemarks(String[] addDiagnosisCancelRemarks)
	{
		this.addDiagnosisCancelRemarks = addDiagnosisCancelRemarks;
	}
	public String getClickedICDCode()
	{
		return clickedICDCode;
	}
	public void setClickedICDCode(String clickedICDCode)
	{
		this.clickedICDCode = clickedICDCode;
	}
	public String getDiagnosticCode() {
		return diagnosticCode;
	}
	public void setDiagnosticCode(String diagnosticCode) {
		this.diagnosticCode = diagnosticCode;
	}
	public String getIcdName() {
		return icdName;
	}
	public void setIcdName(String icdName) {
		this.icdName = icdName;
	}
	
	public String[] getDiagnosisSiteLabel() {
		return diagnosisSiteLabel;
	}
	public void setDiagnosisSiteLabel(String[] diagnosisSiteLabel) {
		this.diagnosisSiteLabel = diagnosisSiteLabel;
	}
	public String[] getDiagnosticTypeName() {
		return diagnosticTypeName;
	}
	public void setDiagnosticTypeName(String[] diagnosticTypeName) {
		this.diagnosticTypeName = diagnosticTypeName;
	}
	public String[] getSnomedCTDiagnosisCode() {
		return snomedCTDiagnosisCode;
	}
	public void setSnomedCTDiagnosisCode(String snomedCTDiagnosisCode[]) {
		this.snomedCTDiagnosisCode = snomedCTDiagnosisCode;
	}
	public String[] getSnomedCTDiagnosisName() {
		return snomedCTDiagnosisName;
	}
	public void setSnomedCTDiagnosisName(String snomedCTDiagnosisName[]) {
		this.snomedCTDiagnosisName = snomedCTDiagnosisName;
	}
	public String[] getSnomedCTDiagnosisSiteName() {
		return snomedCTDiagnosisSiteName;
	}
	public void setSnomedCTDiagnosisSiteName(
			String snomedCTDiagnosisSiteName[]) {
		this.snomedCTDiagnosisSiteName = snomedCTDiagnosisSiteName;
	}
	public String[] getSnomedCTDiagnosisSiteCode() {
		return snomedCTDiagnosisSiteCode;
	}
	public void setSnomedCTDiagnosisSiteCode(
			String snomedCTDiagnosisSiteCode[]) {
		this.snomedCTDiagnosisSiteCode = snomedCTDiagnosisSiteCode;
	}
	public String getSelSnomedCTDiagnosisSiteCode() {
		return selSnomedCTDiagnosisSiteCode;
	}
	public void setSelSnomedCTDiagnosisSiteCode(
			String selSnomedCTDiagnosisSiteCode) {
		this.selSnomedCTDiagnosisSiteCode = selSnomedCTDiagnosisSiteCode;
	}
	public String[] getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String[] diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String[] getDiseaseSite() {
		return diseaseSite;
	}
	public void setDiseaseSite(String[] diseaseSite) {
		this.diseaseSite = diseaseSite;
	}
	public String[] getDiseaseSiteId() {
		return diseaseSiteId;
	}
	public void setDiseaseSiteId(String[] diseaseSiteId) {
		this.diseaseSiteId = diseaseSiteId;
	}
	public String[] getMorphCode() {
		return morphCode;
	}
	public void setMorphCode(String[] morphCode) {
		this.morphCode = morphCode;
	}
	public String[] getMorphTitle() {
		return morphTitle;
	}
	public void setMorphTitle(String[] morphTitle) {
		this.morphTitle = morphTitle;
	}


	
	
}
