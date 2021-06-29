package ehr.externalinvestigation.presentation;

	/**
	 * @copyright CDAC
	 * @developer Vasu
	 */

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import ehr.global.presentation.EHRSection_GlobalFB;

public class EHRSection_ExternalInvestigationFB extends EHRSection_GlobalFB
	{
		private String patCrNo;
		private String admissionNo;
		private String serialNo;
	    private String episodeCode;
	    private String episodeVisitNo;
		private String hmode;
		private String recordDate="";
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
		private String entryDate;
		private String hospitalCode;
		private String seatId;
		private String isValid;
		private String recordTimeHr;
		private String recordTimeMin;
		private String deleteIndex;
		private String tempMode;
		private String sysDate;
		private String addedRecordDate[];
		private String addedRecordTime[];
		private String addedParaId[];
		private String currentAddedRecordDate[];
		private String currentAddedRecordTime[];
		private String currentAddedParaId[];
		private String hiddenTimeHr;
		private String hiddenTimeMin;
		
		private String testConductedFrom;
		private String isDirectCall;
		private String testId;
		private String paraCode;
		private String testCode;
		
		
		private String[] selRecordDate;
		private String[] selRecordTimeHr;
		private String[] selRecordTimeMin;
		private String[] selTestName;
		private String[] selTestCode;
		private String[] selParaName;
		private String[] selParaCode;
		private String[] selParaValue;
		
		private String slno;
		private String deskMenuId;
		
		public EHRSection_ExternalInvestigationFB()
		{
			this.isDirectCall="";
		}
		
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
		public String getAdmissionNo() {
			return admissionNo;
		}
		public void setAdmissionNo(String admissionNo) {
			this.admissionNo = admissionNo;
		}
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
		public String getHmode() {
			return hmode;
		}
		public void setHmode(String hmode) {
			this.hmode = hmode;
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
		public String getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}
		public String getHospitalCode() {
			return hospitalCode;
		}
		public void setHospitalCode(String hospitalCode) {
			this.hospitalCode = hospitalCode;
		}
		public String getSeatId() {
			return seatId;
		}
		public void setSeatId(String seatId) {
			this.seatId = seatId;
		}
		public String getIsValid() {
			return isValid;
		}
		public void setIsValid(String isValid) {
			this.isValid = isValid;
		}
		public String getRecordTimeHr() {
			return recordTimeHr;
		}
		public void setRecordTimeHr(String recordTimeHr) {
			this.recordTimeHr = recordTimeHr;
		}
		public String getRecordTimeMin() {
			return recordTimeMin;
		}
		public void setRecordTimeMin(String recordTimeMin) {
			this.recordTimeMin = recordTimeMin;
		}
		public String getDeleteIndex() {
			return deleteIndex;
		}
		public void setDeleteIndex(String deleteIndex) {
			this.deleteIndex = deleteIndex;
		}
		
		public void reset(ActionMapping mapping,HttpServletRequest request)
		{
			this.setRecordDate("");
			//this.setRecordTimeHr("");
			//this.setRecordTimeMin("");
			this.setParaId("");
			this.setParaValue("");
		}
		public String getTempMode() {
			return tempMode;
		}
		public void setTempMode(String tempMode) {
			this.tempMode = tempMode;
		}
		public String getSysDate() {
			return sysDate;
		}
		public void setSysDate(String sysDate) {
			this.sysDate = sysDate;
		}
		public String[] getAddedRecordDate() {
			return addedRecordDate;
		}
		public void setAddedRecordDate(String[] addedRecordDate) {
			this.addedRecordDate = addedRecordDate;
		}
		public String[] getAddedRecordTime() {
			return addedRecordTime;
		}
		public void setAddedRecordTime(String[] addedRecordTime) {
			this.addedRecordTime = addedRecordTime;
		}
		public String[] getCurrentAddedRecordDate() {
			return currentAddedRecordDate;
		}
		public void setCurrentAddedRecordDate(String[] currentAddedRecordDate) {
			this.currentAddedRecordDate = currentAddedRecordDate;
		}
		public String[] getCurrentAddedRecordTime() {
			return currentAddedRecordTime;
		}
		public void setCurrentAddedRecordTime(String[] currentAddedRecordTime) {
			this.currentAddedRecordTime = currentAddedRecordTime;
		}
		public String[] getAddedParaId() {
			return addedParaId;
		}
		public void setAddedParaId(String[] addedParaId) {
			this.addedParaId = addedParaId;
		}
		public String[] getCurrentAddedParaId() {
			return currentAddedParaId;
		}
		public void setCurrentAddedParaId(String[] currentAddedParaId) {
			this.currentAddedParaId = currentAddedParaId;
		}
		public String getHiddenTimeHr() {
			return hiddenTimeHr;
		}
		public void setHiddenTimeHr(String hiddenTimeHr) {
			this.hiddenTimeHr = hiddenTimeHr;
		}
		public String getHiddenTimeMin() {
			return hiddenTimeMin;
		}
		public void setHiddenTimeMin(String hiddenTimeMin) {
			this.hiddenTimeMin = hiddenTimeMin;
		}
		public String getIsDirectCall() {
			return isDirectCall;
		}
		public void setIsDirectCall(String isDirectCall) {
			this.isDirectCall = isDirectCall;
		}

		public String getDeskMenuId() {
			return deskMenuId;
		}

		public void setDeskMenuId(String deskMenuId) {
			this.deskMenuId = deskMenuId;
		}

		public String getTestId() {
			return testId;
		}

		public void setTestId(String testId) {
			this.testId = testId;
		}

		public String getParaCode() {
			return paraCode;
		}

		public void setParaCode(String paraCode) {
			this.paraCode = paraCode;
		}

		public String getTestCode() {
			return testCode;
		}

		public void setTestCode(String testCode) {
			this.testCode = testCode;
		}

		public String[] getSelRecordDate() {
			return selRecordDate;
		}

		public void setSelRecordDate(String[] selRecordDate) {
			this.selRecordDate = selRecordDate;
		}

		public String[] getSelRecordTimeHr() {
			return selRecordTimeHr;
		}

		public void setSelRecordTimeHr(String[] selRecordTimeHr) {
			this.selRecordTimeHr = selRecordTimeHr;
		}

		public String[] getSelRecordTimeMin() {
			return selRecordTimeMin;
		}

		public void setSelRecordTimeMin(String[] selRecordTimeMin) {
			this.selRecordTimeMin = selRecordTimeMin;
		}

		public String[] getSelTestName() {
			return selTestName;
		}

		public void setSelTestName(String[] selTestName) {
			this.selTestName = selTestName;
		}

		public String[] getSelTestCode() {
			return selTestCode;
		}

		public void setSelTestCode(String[] selTestCode) {
			this.selTestCode = selTestCode;
		}

		public String[] getSelParaName() {
			return selParaName;
		}

		public void setSelParaName(String[] selParaName) {
			this.selParaName = selParaName;
		}

		public String[] getSelParaCode() {
			return selParaCode;
		}

		public void setSelParaCode(String[] selParaCode) {
			this.selParaCode = selParaCode;
		}

		public String[] getSelParaValue() {
			return selParaValue;
		}

		public void setSelParaValue(String[] selParaValue) {
			this.selParaValue = selParaValue;
		}

		public String getSlno() {
			return slno;
		}

		public void setSlno(String slno) {
			this.slno = slno;
		}
		
	}
