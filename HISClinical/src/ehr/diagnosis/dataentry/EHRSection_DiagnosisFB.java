/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.diagnosis.dataentry;

	import hisglobal.vo.EpisodeDiagnosisVO;

	import javax.servlet.http.HttpServletRequest;

	import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

	public class EHRSection_DiagnosisFB extends ActionForm
	{
	
		private String mode;
		private String patCrNo;
		private String patFirstName;
		private String patMiddleName;
		private String patLastName;
		private String patGender;
		private String patGenderAge;
		private String departmentUnitCode;
		private String hmode;
		private String orderBy;
		private String episodeNextVisitDate;
		private String episodeIsOpen;
		private String patChoice;
		private String selectedPatCrNo;
		private String roomCode;
		private String visitType;
		private String hospitalCode;
		private String snomedCTDiagnosisName[];
		private String snomedCTDiagnosisCode[];
		private String diagnosisRecordStatus[];
		private String dignosisName[];
		private String diagonisticTypeCode[];
		private String remarks[];
		private String departmentCode;
		private String episodeCode;
		private String numberOfRow="1";
		private EpisodeDiagnosisVO[] previousDiagnosisVO;
		private EpisodeDiagnosisVO[] arrayPreviousDiagnosisVO;
		private String repeat;
		private String diagnosisCodeType;
		private String unitDiagnosisCodeType;
		private String comboOptionString;
		private String selectedDiagnosis[];
		private String episodeVisitNo;
		private String diagnosisSite[];
		private String diagnosticCode;	
		private String diagnosisSiteLabel[];
		private String diagnosticTypeName[];
		private String snomedCTDiagnosisSiteName[];
		private String snomedCTDiagnosisSiteCode[];
		private String snomedCIdRemarks[]; // snomed integration
		private String snomedPTRemarks[];
		private String deptUnit;
		private String visitDate;
		private String visitReason;
		private String snomdPTRemarks;
		private String snomdCIdRemarks;
    	private String advisedBy;
    	private String icdNHospitalFlagValue;
    	private String selSnomedCTDiagnosisSiteCode;
    	private String comboDiagnosisSite;
    	private String isSetDIGANOSIS;
    	

		
		public String getSelectedPatCrNo()
		{
			return selectedPatCrNo;
		}

		public void setSelectedPatCrNo(String selectedPatCrNo)
		{
			this.selectedPatCrNo = selectedPatCrNo;
		}

		public String getEpisodeIsOpen()
		{
			return episodeIsOpen;
		}

		public void setEpisodeIsOpen(String episodeIsOpen)
		{
			this.episodeIsOpen = episodeIsOpen;
		}

		public String getEpisodeNextVisitDate()
		{
			return episodeNextVisitDate;
		}

		public void setEpisodeNextVisitDate(String episodeNextVisitDate)
		{
			this.episodeNextVisitDate = episodeNextVisitDate;
		}

		public String getHmode()
		{
			return hmode;
		}

		public void setHmode(String hmode)
		{
			this.hmode = hmode;
		}

		public String getDepartmentUnitCode()
		{
			return departmentUnitCode;
		}

		public void setDepartmentUnitCode(String departmentUnitCode)
		{
			this.departmentUnitCode = departmentUnitCode;
		}

		public String getPatGenderAge()
		{
			return patGenderAge;
		}

		public void setPatGenderAge(String patGenderAge)
		{
			this.patGenderAge = patGenderAge;
		}

		public String getMode()
		{
			return mode;
		}

		public String getPatCrNo()
		{
			return patCrNo;
		}

		public void setPatCrNo(String patCrNo)
		{
			this.patCrNo = patCrNo;
		}

		public String getPatFirstName()
		{
			return patFirstName;
		}

		public void setPatFirstName(String patFirstName)
		{
			this.patFirstName = patFirstName;
		}

		public String getPatGender()
		{
			return patGender;
		}

		public void setPatGender(String patGender)
		{
			this.patGender = patGender;
		}

		public String getPatLastName()
		{
			return patLastName;
		}

		public void setPatLastName(String patLastName)
		{
			this.patLastName = patLastName;
		}

		public String getPatMiddleName()
		{
			return patMiddleName;
		}

		public void setPatMiddleName(String patMiddleName)
		{
			this.patMiddleName = patMiddleName;
		}

		

		public void setMode(String mode)
		{
			this.mode = mode;
		}

		public void reset(ActionMapping arg0, HttpServletRequest arg1)
		{
			this.setPatCrNo("");
			this.setPatFirstName("");
			this.setPatMiddleName("");
			this.setPatLastName("");
			this.setPatGender("");
			this.setDepartmentUnitCode("");
			this.setOrderBy("");
			this.setMode("");
			this.setHmode("");
			this.setPatChoice("");
			this.setSnomedCTDiagnosisSiteName(null);
			this.setSnomedPTRemarks(null);
			this.setSnomedCIdRemarks(null);
			this.setRemarks(null);
			this.setSnomedCTDiagnosisSiteName(null);
			this.setSnomedCTDiagnosisSiteCode(null);
			this.setSnomedCTDiagnosisName (null);
			this.setSnomedCTDiagnosisCode(null);
			
			
			super.reset(arg0, arg1);
		}

		public String getOrderBy()
		{
			return orderBy;
		}

		public void setOrderBy(String orderBy)
		{
			this.orderBy = orderBy;
		}

		public String getPatChoice()
		{
			return patChoice;
		}

		public void setPatChoice(String patChoice)
		{
			this.patChoice = patChoice;
		}

		
		public String getRoomCode() {
			return roomCode;
		}

		public void setRoomCode(String roomCode) {
			this.roomCode = roomCode;
		}

		
		public String getVisitType() {
			return visitType;
		}

		public void setVisitType(String visitType) {
			this.visitType = visitType;
		}

		public String getHospitalCode() {
			return hospitalCode;
		}

		public void setHospitalCode(String hospitalCode) {
			this.hospitalCode = hospitalCode;
		}

		
		

		
		public String[] getDignosisName() {
			return dignosisName;
		}

		public void setDignosisName(String[] dignosisName) {
			this.dignosisName = dignosisName;
		}

	

		public String[] getDiagonisticTypeCode() {
			return diagonisticTypeCode;
		}

		public void setDiagonisticTypeCode(String[] diagonisticTypeCode) {
			this.diagonisticTypeCode = diagonisticTypeCode;
		}

		public String[] getRemarks() {
			return remarks;
		}

		public void setRemarks(String[] remarks) {
			this.remarks = remarks;
		}

		public String getDepartmentCode() {
			return departmentCode;
		}

		public void setDepartmentCode(String departmentCode) {
			this.departmentCode = departmentCode;
		}

		public String getEpisodeCode() {
			return episodeCode;
		}

		public void setEpisodeCode(String episodeCode) {
			this.episodeCode = episodeCode;
		}

		

		

		public String getNumberOfRow() {
			return numberOfRow;
		}

		public void setNumberOfRow(String numberOfRow) {
			this.numberOfRow = numberOfRow;
		}

		public EpisodeDiagnosisVO[] getPreviousDiagnosisVO() {
			return previousDiagnosisVO;
		}

		public void setPreviousDiagnosisVO(EpisodeDiagnosisVO[] previousDiagnosisVO) {
			this.previousDiagnosisVO = previousDiagnosisVO;
		}

		public EpisodeDiagnosisVO[] getArrayPreviousDiagnosisVO() {
			return arrayPreviousDiagnosisVO;
		}

		public void setArrayPreviousDiagnosisVO(
				EpisodeDiagnosisVO[] arrayPreviousDiagnosisVO) {
			this.arrayPreviousDiagnosisVO = arrayPreviousDiagnosisVO;
		}

		public String getRepeat() {
			return repeat;
		}

		public void setRepeat(String repeat) {
			this.repeat = repeat;
		}

		public String getDiagnosisCodeType() {
			return diagnosisCodeType;
		}

		public void setDiagnosisCodeType(String diagnosisCodeType) {
			this.diagnosisCodeType = diagnosisCodeType;
		}

		public String getUnitDiagnosisCodeType() {
			return unitDiagnosisCodeType;
		}

		public void setUnitDiagnosisCodeType(String unitDiagnosisCodeType) {
			this.unitDiagnosisCodeType = unitDiagnosisCodeType;
		}

		public String getComboOptionString() {
			return comboOptionString;
		}

		public void setComboOptionString(String comboOptionString) {
			this.comboOptionString = comboOptionString;
		}

		public String[] getSelectedDiagnosis() {
			return selectedDiagnosis;
		}

		public void setSelectedDiagnosis(String[] selectedDiagnosis) {
			this.selectedDiagnosis = selectedDiagnosis;
		}

		
		public String getEpisodeVisitNo() {
			return episodeVisitNo;
		}

		public void setEpisodeVisitNo(String episodeVisitNo) {
			this.episodeVisitNo = episodeVisitNo;
		}

		

		public String[] getDiagnosisSite() {
			return diagnosisSite;
		}

		public void setDiagnosisSite(String[] diagnosisSite) {
			this.diagnosisSite = diagnosisSite;
		}

	

		public String getDiagnosticCode() {
			return diagnosticCode;
		}

		public void setDiagnosticCode(String diagnosticCode) {
			this.diagnosticCode = diagnosticCode;
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

		public String[] getSnomedCTDiagnosisSiteName() {
			return snomedCTDiagnosisSiteName;
		}

		public void setSnomedCTDiagnosisSiteName(String[] snomedCTDiagnosisSiteName) {
			this.snomedCTDiagnosisSiteName = snomedCTDiagnosisSiteName;
		}

		public String[] getSnomedCTDiagnosisSiteCode() {
			return snomedCTDiagnosisSiteCode;
		}

		public void setSnomedCTDiagnosisSiteCode(String[] snomedCTDiagnosisSiteCode) {
			this.snomedCTDiagnosisSiteCode = snomedCTDiagnosisSiteCode;
		}

		
		public String[] getSnomedCIdRemarks() {
			return snomedCIdRemarks;
		}

		public void setSnomedCIdRemarks(String[] snomedCIdRemarks) {
			this.snomedCIdRemarks = snomedCIdRemarks;
		}

		public String[] getSnomedPTRemarks() {
			return snomedPTRemarks;
		}

		public void setSnomedPTRemarks(String[] snomedPTRemarks) {
			this.snomedPTRemarks = snomedPTRemarks;
		}

		public String[] getSnomedCTDiagnosisName() {
			return snomedCTDiagnosisName;
		}

		public void setSnomedCTDiagnosisName(String snomedCTDiagnosisName[]) {
			this.snomedCTDiagnosisName = snomedCTDiagnosisName;
		}

		public String[] getSnomedCTDiagnosisCode() {
			return snomedCTDiagnosisCode;
		}

		public void setSnomedCTDiagnosisCode(String snomedCTDiagnosisCode[]) {
			this.snomedCTDiagnosisCode = snomedCTDiagnosisCode;
		}

		public String getDeptUnit() {
			return deptUnit;
		}

		public void setDeptUnit(String deptUnit) {
			this.deptUnit = deptUnit;
		}

		public String getVisitDate() {
			return visitDate;
		}

		public void setVisitDate(String visitDate) {
			this.visitDate = visitDate;
		}

		public String getVisitReason() {
			return visitReason;
		}

		public void setVisitReason(String visitReason) {
			this.visitReason = visitReason;
		}

		public String getSnomdPTRemarks() {
			return snomdPTRemarks;
		}

		public void setSnomdPTRemarks(String snomdPTRemarks) {
			this.snomdPTRemarks = snomdPTRemarks;
		}

		public String getSnomdCIdRemarks() {
			return snomdCIdRemarks;
		}

		public void setSnomdCIdRemarks(String snomdCIdRemarks) {
			this.snomdCIdRemarks = snomdCIdRemarks;
		}

		public String[] getDiagnosisRecordStatus() {
			return diagnosisRecordStatus;
		}

		public void setDiagnosisRecordStatus(String diagnosisRecordStatus[]) {
			this.diagnosisRecordStatus = diagnosisRecordStatus;
		}

		
		public String getAdvisedBy() {
			return advisedBy;
		}

		public void setAdvisedBy(String advisedBy) {
			this.advisedBy = advisedBy;
		}

		public String getIcdNHospitalFlagValue() {
			return icdNHospitalFlagValue;
		}

		public void setIcdNHospitalFlagValue(String icdNHospitalFlagValue) {
			this.icdNHospitalFlagValue = icdNHospitalFlagValue;
		}

		public String getSelSnomedCTDiagnosisSiteCode() {
			return selSnomedCTDiagnosisSiteCode;
		}

		public void setSelSnomedCTDiagnosisSiteCode(
				String selSnomedCTDiagnosisSiteCode) {
			this.selSnomedCTDiagnosisSiteCode = selSnomedCTDiagnosisSiteCode;
		}

		public String getComboDiagnosisSite() {
			return comboDiagnosisSite;
		}

		public void setComboDiagnosisSite(String comboDiagnosisSite) {
			this.comboDiagnosisSite = comboDiagnosisSite;
		}

		public String getIsSetDIGANOSIS() {
			return isSetDIGANOSIS;
		}

		public void setIsSetDIGANOSIS(String isSetDIGANOSIS) {
			this.isSetDIGANOSIS = isSetDIGANOSIS;
		}

		
		
	}
