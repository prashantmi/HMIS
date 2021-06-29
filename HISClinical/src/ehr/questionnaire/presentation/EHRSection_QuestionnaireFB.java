package ehr.questionnaire.presentation;



	/**
	 * @copyright CDAC
	 * @developer Vasu
	 */

	import hisglobal.utility.generictemplate.GenericTemplateConfig;

	import java.util.List;

import javax.servlet.http.HttpServletRequest;

	import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ehr.global.presentation.EHRSection_GlobalFB;

	public class EHRSection_QuestionnaireFB extends EHRSection_GlobalFB
	{
		private String hmode;

		private String patCrNo;
		private String userSeatId;
		private String wardCode;
		private String deptUnitCode;
		private String deskType;
		private String episodeCode;
		private String episodeVisitNo;
		private String admissionNo;
		private String deskId;
		private String deskMenuId;
		private String deskMenuName;
		private String recordDate;
		private String entryDate;

		private String reportMode;
		private String viewMode;
		private String [] selectedTemplates;
		private String [] selectedParas;
		
		
		
		private String consentHtmlToPrint;
		private List activeTemplateIds;
		private String selUndefaultTemp;
		private String haveTemplate;

		private String choice;
		private String selectedVisitNo;
		private String fromVisitDate;
		private String toVisitDate;
		private String selectedMenuId;
		private String departmentUnitCode;
		private String []selectedRow;
		private String complaintsCheckFlag;
		private String complaintsDateWiseCheckFlag;
		private String profileGenerationMode;
		private String profileType;
		private String isSetCOMPLAINTS;

		private String ftlValueForQuestionnaire;
		//private String selectedParaList;

		public String getEntryDate()
		{
			return entryDate;
		}

		public void setEntryDate(String entryDate)
		{
			this.entryDate = entryDate;
		}

		public EHRSection_QuestionnaireFB()
		{
			this.userSeatId="";
			this.wardCode="";
			this.deptUnitCode="";
			this.deskType="";
			this.episodeCode="";
			this.episodeVisitNo="";
			this.deskId="";
			this.deskMenuName="";
			this.recordDate="";
			this.entryDate="";
			this.reportMode=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE;
			this.viewMode=GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE;
			this.selectedTemplates = new String[0];

			
			
			
			
			
			this.activeTemplateIds = null;
			this.selUndefaultTemp = "";
			this.haveTemplate = "0";
			this.choice = "";
			this.selectedVisitNo = "";
			this.fromVisitDate = "";
			this.toVisitDate = "";
		}

		public void reset(ActionMapping arg0, HttpServletRequest arg1)
		{
			this.userSeatId="";
			this.wardCode="";
			this.deptUnitCode="";
			this.deskType="";
			this.episodeCode="";
			this.episodeVisitNo="";
			this.deskId="";
			this.deskMenuName="";
			this.recordDate="";
			this.entryDate="";
			this.reportMode=GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE;
			this.viewMode=GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE;
			this.selectedTemplates = new String[0];

			
			this.activeTemplateIds = null;
			this.selUndefaultTemp = "";
			this.haveTemplate = "0";
			this.choice = "";
			this.selectedVisitNo = "";
			this.fromVisitDate = "";
			this.toVisitDate = "";
		}

		public String getDeskMenuId()
		{
			return deskMenuId;
		}

		public void setDeskMenuId(String deskMenuId)
		{
			this.deskMenuId = deskMenuId;
		}

		public String getUserSeatId()
		{
			return userSeatId;
		}

		public void setUserSeatId(String userSeatId)
		{
			this.userSeatId = userSeatId;
		}

		public String getHmode()
		{
			return hmode;
		}

		public void setHmode(String hmode)
		{
			this.hmode = hmode;
		}

		public String getEpisodeCode()
		{
			return episodeCode;
		}

		public void setEpisodeCode(String episodeCode)
		{
			this.episodeCode = episodeCode;
		}

		public String getEpisodeVisitNo()
		{
			return episodeVisitNo;
		}

		public void setEpisodeVisitNo(String episodeVisitNo)
		{
			this.episodeVisitNo = episodeVisitNo;
		}

		public String getHaveTemplate()
		{
			return haveTemplate;
		}

		public void setHaveTemplate(String haveTemplate)
		{
			this.haveTemplate = haveTemplate;
		}

		public String getDeskMenuName()
		{
			return deskMenuName;
		}

		public void setDeskMenuName(String deskMenuName)
		{
			this.deskMenuName = deskMenuName;
		}

		public List getActiveTemplateIds()
		{
			return activeTemplateIds;
		}

		public void setActiveTemplateIds(List activeTemplateIds)
		{
			this.activeTemplateIds = activeTemplateIds;
		}

		public String getSelUndefaultTemp()
		{
			return selUndefaultTemp;
		}

		public void setSelUndefaultTemp(String selUndefaultTemp)
		{
			this.selUndefaultTemp = selUndefaultTemp;
		}

		public String getConsentHtmlToPrint()
		{
			return consentHtmlToPrint;
		}

		public void setConsentHtmlToPrint(String consentHtmlToPrint)
		{
			this.consentHtmlToPrint = consentHtmlToPrint;
		}

		public String getChoice()
		{
			return choice;
		}

		public void setChoice(String choice)
		{
			this.choice = choice;
		}

		public String getSelectedVisitNo()
		{
			return selectedVisitNo;
		}

		public void setSelectedVisitNo(String selectedVisitNo)
		{
			this.selectedVisitNo = selectedVisitNo;
		}

		public String getFromVisitDate()
		{
			return fromVisitDate;
		}

		public void setFromVisitDate(String fromVisitDate)
		{
			this.fromVisitDate = fromVisitDate;
		}

		public String getToVisitDate()
		{
			return toVisitDate;
		}

		public void setToVisitDate(String toVisitDate)
		{
			this.toVisitDate = toVisitDate;
		}

		public String getWardCode()
		{
			return wardCode;
		}

		public void setWardCode(String wardCode)
		{
			this.wardCode = wardCode;
		}

		public String getPatCrNo()
		{
			return patCrNo;
		}

		public void setPatCrNo(String patCrNo)
		{
			this.patCrNo = patCrNo;
		}

		public String getDeskId()
		{
			return deskId;
		}

		public void setDeskId(String deskId)
		{
			this.deskId = deskId;
		}

		public String getDeptUnitCode()
		{
			return deptUnitCode;
		}

		public void setDeptUnitCode(String deptUnitCode)
		{
			this.deptUnitCode = deptUnitCode;
		}

		public String getDeskType()
		{
			return deskType;
		}

		public void setDeskType(String deskType)
		{
			this.deskType = deskType;
		}

		public String getRecordDate()
		{
			return recordDate;
		}

		public void setRecordDate(String recordDate)
		{
			this.recordDate = recordDate;
		}

		public String getReportMode()
		{
			return reportMode;
		}

		public void setReportMode(String reportMode)
		{
			this.reportMode = reportMode;
		}

		public String[] getSelectedTemplates()
		{
			return selectedTemplates;
		}

		public void setSelectedTemplates(String[] selectedTemplates)
		{
			this.selectedTemplates = selectedTemplates;
		}

		public String[] getSelectedParas()
		{
			return selectedParas;
		}

		public void setSelectedParas(String[] selectedParas)
		{
			this.selectedParas = selectedParas;
		}

		public String getViewMode()
		{
			return viewMode;
		}

		public void setViewMode(String viewMode)
		{
			this.viewMode = viewMode;
		}

		public String getAdmissionNo()
		{
			return admissionNo;
		}

		public void setAdmissionNo(String admissionNo)
		{
			this.admissionNo = admissionNo;
		}

		public String getSelectedMenuId() {
			return selectedMenuId;
		}

		public void setSelectedMenuId(String selectedMenuId) {
			this.selectedMenuId = selectedMenuId;
		}

		public String getDepartmentUnitCode() {
			return departmentUnitCode;
		}

		public void setDepartmentUnitCode(String departmentUnitCode) {
			this.departmentUnitCode = departmentUnitCode;
		}

		

		public String getComplaintsCheckFlag() {
			return complaintsCheckFlag;
		}

		public void setComplaintsCheckFlag(String complaintsCheckFlag) {
			this.complaintsCheckFlag = complaintsCheckFlag;
		}

		public String [] getSelectedRow() {
			return selectedRow;
		}

		public void setSelectedRow(String [] selectedRow) {
			this.selectedRow = selectedRow;
		}

		public String getComplaintsDateWiseCheckFlag() {
			return complaintsDateWiseCheckFlag;
		}

		public void setComplaintsDateWiseCheckFlag(
				String complaintsDateWiseCheckFlag) {
			this.complaintsDateWiseCheckFlag = complaintsDateWiseCheckFlag;
		}

		public String getProfileGenerationMode() {
			return profileGenerationMode;
		}

		public void setProfileGenerationMode(String profileGenerationMode) {
			this.profileGenerationMode = profileGenerationMode;
		}

		public String getProfileType() {
			return profileType;
		}

		public void setProfileType(String profileType) {
			this.profileType = profileType;
		}

		public String getIsSetCOMPLAINTS() {
			return isSetCOMPLAINTS;
		}

		public void setIsSetCOMPLAINTS(String isSetCOMPLAINTS) {
			this.isSetCOMPLAINTS = isSetCOMPLAINTS;
		}

		public String getFtlValueForQuestionnaire() {
			return ftlValueForQuestionnaire;
		}

		public void setFtlValueForQuestionnaire(String ftlValueForQuestionnaire) {
			this.ftlValueForQuestionnaire = ftlValueForQuestionnaire;
		}

		
	}
