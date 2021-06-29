/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.history.vo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hisglobal.utility.HelperMethods;
import hisglobal.vo.ValueObject;


	public class EHRSection_HistoryVO extends ValueObject
	{
		private String patCrNo;
		private String episodeCode;
		private String episodeVisitNo;
		private String admissionNo;
		private String recordDate;
		private String deskMenuId;
		private String templateId;
		private String[] templateIds;
		private String paraId;
		private String paraIdConcpetId;
		private String paraIdPreffereTerm;
		private String paraValue;
		private String paraValueConcpetId;
		private String paraValuePreffereTerm;
		private String isValid;
		private String seatId;
		private String entryDate;
		private String paraDesc;
		private String departmentUnitCode;
		private String hospitalCode;
		private String hospitalName;
		private String templateName;
		
		//Added by Vasu on 14.May.2019
		private String deskType;
		private String deskId;
		
		private String templateContent;
	    private String templateContentSummarized;
		
		
		public String getParaIdConcpetId() {
			return paraIdConcpetId;
		}

		public void setParaIdConcpetId(String paraIdConcpetId) {
			this.paraIdConcpetId = paraIdConcpetId;
		}

		public String getParaIdPreffereTerm() {
			return paraIdPreffereTerm;
		}

		public void setParaIdPreffereTerm(String paraIdPreffereTerm) {
			this.paraIdPreffereTerm = paraIdPreffereTerm;
		}
		public String getDepartmentUnitCode() {
			return departmentUnitCode;
		}

		public void setDepartmentUnitCode(String departmentUnitCode) {
			this.departmentUnitCode = departmentUnitCode;
		}

		public String getIsValid()
		{
			return isValid;
		}

		public void setIsValid(String isValid)
		{
			this.isValid = isValid;
		}

		public String getSeatId()
		{
			return seatId;
		}

		public void setSeatId(String seatId)
		{
			this.seatId = seatId;
		}

		public String getEntryDate()
		{
			return entryDate;
		}

		public void setEntryDate(String entryDate)
		{
			this.entryDate = entryDate;
		}

		public String getPatCrNo()
		{
			return patCrNo;
		}

		public void setPatCrNo(String patCrNo)
		{
			this.patCrNo = patCrNo;
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

		public String getDeskMenuId()
		{
			return deskMenuId;
		}

		public void setDeskMenuId(String deskMenuId)
		{
			this.deskMenuId = deskMenuId;
		}

		public String getTemplateId()
		{
			return templateId;
		}

		public void setTemplateId(String templateId)
		{
			this.templateId = templateId;
		}

		public String getParaId()
		{
			return paraId;
		}

		public void setParaId(String paraId)
		{
			this.paraId = paraId;
		}

		public String getParaValue()
		{
			return paraValue;
		}

		public void setParaValue(String paraValue)
		{
			this.paraValue = paraValue;
		}

		public String getRecordDate()
		{
			return recordDate;
		}

		public void setRecordDate(String recordDate)
		{
			this.recordDate = recordDate;
		}

		public String getAdmissionNo()
		{
			return admissionNo;
		}

		public void setAdmissionNo(String admissionNo)
		{
			this.admissionNo = admissionNo;
		}

		public String[] getTemplateIds()
		{
			return templateIds;
		}

		public void setTemplateIds(String[] templateIds)
		{
			this.templateIds = templateIds;
		}

		public String getParaDesc() {
			return paraDesc;
		}

		public void setParaDesc(String paraDesc) {
			this.paraDesc = paraDesc;
		}

		public String getHospitalCode() {
			return hospitalCode;
		}

		public void setHospitalCode(String hospitalCode) {
			this.hospitalCode = hospitalCode;
		}

		public String getHospitalName() {
			return hospitalName;
		}

		public void setHospitalName(String hospitalName) {
			this.hospitalName = hospitalName;
		}

		public String getParaValueConcpetId() {
			return paraValueConcpetId;
		}

		public void setParaValueConcpetId(String paraValueConcpetId) {
			this.paraValueConcpetId = paraValueConcpetId;
		}

		public String getParaValuePreffereTerm() {
			return paraValuePreffereTerm;
		}

		public void setParaValuePreffereTerm(String paraValuePreffereTerm) {
			this.paraValuePreffereTerm = paraValuePreffereTerm;
		}

		public String getDeskType() {
			return deskType;
		}

		public void setDeskType(String deskType) {
			this.deskType = deskType;
		}

		public String getDeskId() {
			return deskId;
		}

		public void setDeskId(String deskId) {
			this.deskId = deskId;
		}

		public String getTemplateContent() {
			return templateContent;
		}

		public void setTemplateContent(String templateContent) {
			this.templateContent = templateContent;
		}

		public String getTemplateName() {
			return templateName;
		}

		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}

		public String getTemplateContentSummarized() {
			return templateContentSummarized;
		}

		public void setTemplateContentSummarized(String templateContentSummarized) {
			this.templateContentSummarized = templateContentSummarized;
		}

	}

