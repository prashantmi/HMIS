/**
##		Date					: 05-Aug-2019
##		Reason	(CR/PRS)		: External Investigation Section at SPD 
##		Created By				: Vasu
*/


package ehr.externalinvestigation.vo;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hisglobal.tools.tag.GenericTemplateTag;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.vo.ValueObject;


	public class EHRSection_ExternalInvestigationVO extends ValueObject
	{
		private String patCrNo;
		//private String admissionNo;
		private String patAdmNo;
		private String serialNo;
	    private String episodeCode;
	    private String episodeVisitNo;
	    private String recordDate;
	    private String recordTime;
	    private String paraValue;
		private String paraId;
		private String testName;
		private String extLabName;
		private String extLabAdd;
		private String extLabContactNo;
		private String minValue;
		private String maxValue;
		private String remarks;
		private String entryMode;
		private String paraName;
		private String testConductedFrom;
		private String departmentUnitCode;
		private String hospitalCode;
		private String hospitalName;
		
		private String selRecordDate;
		private String selRecordTimeHr;
		private String selRecordTimeMin;
		private String selTestName;
		private String selTestCode;
		private String selParaName;
		private String selParaCode;
		private String selParaValue;
		
		private String slno;
		
		public String getTestConductedFrom() {
			return testConductedFrom;
		}
		public void setTestConductedFrom(String testConductedFrom) {
			this.testConductedFrom = testConductedFrom;
		}
		public String getPatCrNo() {
			return patCrNo;
		}
		public void setPatCrNo(String patCrNo) {
			this.patCrNo = patCrNo;
		}
		/*public String getAdmissionNo() {
			return admissionNo;
		}
		public void setAdmissionNo(String admissionNo) {
			this.admissionNo = admissionNo;
		}*/
		public String getSerialNo() {
			return serialNo;
		}
		public void setSerialNo(String serialNo) {
			this.serialNo = serialNo;
		}
		public String getEpisodeCode() {
			return episodeCode;
		}
		public void setEpisodeCode(String episodeCode) {
			this.episodeCode = episodeCode;
		}
		public String getEpisodeVisitNo() {
			return episodeVisitNo;
		}
		public void setEpisodeVisitNo(String episodeVisitNo) {
			this.episodeVisitNo = episodeVisitNo;
		}
		public String getRecordDate() {
			return recordDate;
		}
		public void setRecordDate(String recordDate) {
			this.recordDate = recordDate;
		}
		public String getParaValue() {
			return paraValue;
		}
		public void setParaValue(String paraValue) {
			this.paraValue = paraValue;
		}
		public String getParaId() {
			return paraId;
		}
		public void setParaId(String paraId) {
			this.paraId = paraId;
		}
		public String getTestName() {
			return testName;
		}
		public void setTestName(String testName) {
			this.testName = testName;
		}
		public String getExtLabName() {
			return extLabName;
		}
		public void setExtLabName(String extLabName) {
			this.extLabName = extLabName;
		}
		public String getExtLabAdd() {
			return extLabAdd;
		}
		public void setExtLabAdd(String extLabAdd) {
			this.extLabAdd = extLabAdd;
		}
		public String getExtLabContactNo() {
			return extLabContactNo;
		}
		public void setExtLabContactNo(String extLabContactNo) {
			this.extLabContactNo = extLabContactNo;
		}
		public String getMinValue() {
			return minValue;
		}
		public void setMinValue(String minValue) {
			this.minValue = minValue;
		}
		public String getMaxValue() {
			return maxValue;
		}
		public void setMaxValue(String maxValue) {
			this.maxValue = maxValue;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getEntryMode() {
			return entryMode;
		}
		public void setEntryMode(String entryMode) {
			this.entryMode = entryMode;
		}
		public String getRecordTime() {
			return recordTime;
		}
		public void setRecordTime(String recordTime) {
			this.recordTime = recordTime;
		}
		public String getParaName() {
			return paraName;
		}
		public void setParaName(String paraName) {
			this.paraName = paraName;
		}

		public String getDepartmentUnitCode() {
			return departmentUnitCode;
		}
		public void setDepartmentUnitCode(String departmentUnitCode) {
			this.departmentUnitCode = departmentUnitCode;
		}

		public String getPatAdmNo() {
			return patAdmNo;
		}
		public void setPatAdmNo(String patAdmNo) {
			this.patAdmNo = patAdmNo;
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
		public String getSelRecordDate() {
			return selRecordDate;
		}
		public void setSelRecordDate(String selRecordDate) {
			this.selRecordDate = selRecordDate;
		}
		public String getSelRecordTimeHr() {
			return selRecordTimeHr;
		}
		public void setSelRecordTimeHr(String selRecordTimeHr) {
			this.selRecordTimeHr = selRecordTimeHr;
		}
		public String getSelRecordTimeMin() {
			return selRecordTimeMin;
		}
		public void setSelRecordTimeMin(String selRecordTimeMin) {
			this.selRecordTimeMin = selRecordTimeMin;
		}
		public String getSelTestName() {
			return selTestName;
		}
		public void setSelTestName(String selTestName) {
			this.selTestName = selTestName;
		}
		public String getSelTestCode() {
			return selTestCode;
		}
		public void setSelTestCode(String selTestCode) {
			this.selTestCode = selTestCode;
		}
		public String getSelParaName() {
			return selParaName;
		}
		public void setSelParaName(String selParaName) {
			this.selParaName = selParaName;
		}
		public String getSelParaCode() {
			return selParaCode;
		}
		public void setSelParaCode(String selParaCode) {
			this.selParaCode = selParaCode;
		}
		public String getSelParaValue() {
			return selParaValue;
		}
		public void setSelParaValue(String selParaValue) {
			this.selParaValue = selParaValue;
		}
		public String getSlno() {
			return slno;
		}
		public void setSlno(String slno) {
			this.slno = slno;
		}

	}

