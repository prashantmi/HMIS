package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EmrCommonDeskFB extends ActionForm {
	
	private String patCrNo;
	
	private String hmode;
	private String treeNode;
	///////////////////////////
	private String patCat;
    private String patPrimaryCatCode;		
    private String patSecondaryCatCode;
	private String patFirstName;
    private String patMiddleName;
    private String patLastName;
    private String patGender;
	private String patAge;
    private String patGuardianName;
    private String patAgeUnit;		
	private String valid;		
	private String patRegCatCode;		
    private String patPrimaryCat;		
    private String patSecondaryCat;		
    private String patGenderCode;
    private String patRegCat;
    private String registerDate;
    private String mlcNo;
    private String patHusbandName;
    private String patStatusCode;
    private String episodeVisitNo;
    private String unitCodeArray;
    private String selectedTab;
    private String selectedVisit;
    private String emrTabs;
    private String imageIndex;
    private String sendVisitDetail;
    private String episodeCode;
    private String departmentUnitCode;
    private String selectionCriteria;
    private String selectedUnit;
    private String addmissionTreeNode;
    private String selectedPatAdmNo;
    private String totalViewIntake;
	private String totalViewOuttake;
	private String showProgNotes;
	private String tabId;
	private String tabMode;
	private String callFrom;
	private String isNew;
	private String sortOn;
	private String addmissionNo;//Added By Shweta On 09-MAY-2019
	
	private String[] hospitalCode;
	private String[] hospitalName;
	private String hosCode;
	private String chkTreatment;
	
    public String getShowProgNotes() {
		return showProgNotes;
	}
	public void setShowProgNotes(String showProgNotes) {
		this.showProgNotes = showProgNotes;
	}
	public String getSelectedPatAdmNo() {
		return selectedPatAdmNo;
	}
	public void setSelectedPatAdmNo(String selectedPatAdmNo) {
		this.selectedPatAdmNo = selectedPatAdmNo;
	}
	public String getAddmissionTreeNode() {
		return addmissionTreeNode;
	}
	public void setAddmissionTreeNode(String addmissionTreeNode) {
		this.addmissionTreeNode = addmissionTreeNode;
	}
	public String getSelectionCriteria() {
		return selectionCriteria;
	}
	public void setSelectionCriteria(String selectionCriteria) {
		this.selectionCriteria = selectionCriteria;
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
	public String getSendVisitDetail() {
		return sendVisitDetail;
	}
	public void setSendVisitDetail(String sendVisitDetail) {
		this.sendVisitDetail = sendVisitDetail;
	}
	public String getImageIndex() {
		return imageIndex;
	}
	public void setImageIndex(String imageIndex) {
		this.imageIndex = imageIndex;
	}
	public String getEmrTabs() {
		return emrTabs;
	}
	public void setEmrTabs(String emrTabs) {
		this.emrTabs = emrTabs;
	}
	public String getSelectedVisit() {
		return selectedVisit;
	}
	public void setSelectedVisit(String selectedVisit) {
		this.selectedVisit = selectedVisit;
	}
	public String getSelectedTab() {
		return selectedTab;
	}
	public void setSelectedTab(String selectedTab) {
		this.selectedTab = selectedTab;
	}
	public String getUnitCodeArray() {
		return unitCodeArray;
	}
	public void setUnitCodeArray(String unitCodeArray) {
		this.unitCodeArray = unitCodeArray;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	////////////////////////////
	public String getTreeNode() {
		return treeNode;
	}
	public void setTreeNode(String treeNode) {
		this.treeNode = treeNode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.setPatCrNo("");
		this.setHmode("");
		this.departmentUnitCode="-1";
		super.reset(mapping, request);
	}
	public String getPatCat() {
		return patCat;
	}
	public void setPatCat(String patCat) {
		this.patCat = patCat;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getPatSecondaryCatCode() {
		return patSecondaryCatCode;
	}
	public void setPatSecondaryCatCode(String patSecondaryCatCode) {
		this.patSecondaryCatCode = patSecondaryCatCode;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getPatAgeUnit() {
		return patAgeUnit;
	}
	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getPatRegCatCode() {
		return patRegCatCode;
	}
	public void setPatRegCatCode(String patRegCatCode) {
		this.patRegCatCode = patRegCatCode;
	}
	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}
	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}
	public String getPatSecondaryCat() {
		return patSecondaryCat;
	}
	public void setPatSecondaryCat(String patSecondaryCat) {
		this.patSecondaryCat = patSecondaryCat;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatRegCat() {
		return patRegCat;
	}
	public void setPatRegCat(String patRegCat) {
		this.patRegCat = patRegCat;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getMlcNo() {
		return mlcNo;
	}
	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}
	public String getPatHusbandName() {
		return patHusbandName;
	}
	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}
	public String getPatStatusCode() {
		return patStatusCode;
	}
	public void setPatStatusCode(String patStatusCode) {
		this.patStatusCode = patStatusCode;
	}
	public String getSelectedUnit() {
		return selectedUnit;
	}
	public void setSelectedUnit(String selectedUnit) {
		this.selectedUnit = selectedUnit;
	}
	public String getTotalViewIntake() {
		return totalViewIntake;
	}
	public void setTotalViewIntake(String totalViewIntake) {
		this.totalViewIntake = totalViewIntake;
	}
	public String getTotalViewOuttake() {
		return totalViewOuttake;
	}
	public void setTotalViewOuttake(String totalViewOuttake) {
		this.totalViewOuttake = totalViewOuttake;
	}
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public String getTabMode() {
		return tabMode;
	}
	public void setTabMode(String tabMode) {
		this.tabMode = tabMode;
	}
	public String getCallFrom() {
		return callFrom;
	}
	public void setCallFrom(String callFrom) {
		this.callFrom = callFrom;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getSortOn() {
		return sortOn;
	}
	public void setSortOn(String sortOn) {
		this.sortOn = sortOn;
	}
	public String[] getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String[] hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String[] getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String[] hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getHosCode() {
		return hosCode;
	}
	public void setHosCode(String hosCode) {
		this.hosCode = hosCode;
	}
	public String getChkTreatment() {
		return chkTreatment;
	}
	public void setChkTreatment(String chkTreatment) {
		this.chkTreatment = chkTreatment;
	}
	public String getAddmissionNo() {
		return addmissionNo;
	}
	public void setAddmissionNo(String addmissionNo) {
		this.addmissionNo = addmissionNo;
	}
	
}
