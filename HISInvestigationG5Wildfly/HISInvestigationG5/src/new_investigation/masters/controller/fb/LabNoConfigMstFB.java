package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LabNoConfigMstFB extends ActionForm
{


	private String hmode;
	private String chk[];

	private String testCode;
	private String seqNo;
	private String patientType;
	private String seqDigit;
	private String labNoFormat;
	private String labCode;
	private String hospitalCode;
	private String initializationType;
	private String selectedChk;
	private String fromSeries;
	private String toSeries;
	private String initDate;
	private String reinitDate;
	private String yearFormat;
	private String monthFormat;
	private String dateFormat;
	private String sequenceFormat;
	private String isActive;
	private String seriesFormat;
	
	private String dateOrder;
	private String monthOrder;
	private String yearOrder;
	private String partOne;
	private String partTwo;
	
	private String sampleAcceptanceAreaCode;
	private String acceptanceAreawise;
	
	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}






	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		this.patientType="3";
		this.labCode="-1";
		this.seqDigit="";
		this.labNoFormat="";
		this.initializationType="d";
		this.testCode="-1";
		this.fromSeries="";
		this.toSeries="";
		this.initDate="";
		this.reinitDate="";
		this.yearFormat="";
		this.monthFormat="";
		this.dateFormat="";
		this.dateOrder="";
		this.yearOrder="";
		this.monthOrder="";
		this.seriesFormat="";
		this.partOne="";
		this.partTwo="";
		this.sampleAcceptanceAreaCode="-1";
		this.acceptanceAreawise="0";
	}




	public String getHmode()
	{
		return hmode;
	}
	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
	public String[] getChk()
	{
		return chk;
	}
	public void setChk(String[] chk)
	{
		this.chk = chk;
	}



	public String getHospitalCode() {
		return hospitalCode;
	}



	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}






	public String getSelectedChk() {
		return selectedChk;
	}



	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}



	public String getPatientType() {
		return patientType;
	}



	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}



	public String getSeqDigit() {
		return seqDigit;
	}



	public void setSeqDigit(String seqDigit) {
		this.seqDigit = seqDigit;
	}



	public String getLabNoFormat() {
		return labNoFormat;
	}



	public void setLabNoFormat(String labNoFormat) {
		this.labNoFormat = labNoFormat;
	}



	public String getLabCode() {
		return labCode;
	}



	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}



	public String getInitializationType() {
		return initializationType;
	}



	public void setInitializationType(String initializationType) {
		this.initializationType = initializationType;
	}



	public String getTestCode() {
		return testCode;
	}



	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}



	public String getFromSeries() {
		return fromSeries;
	}



	public void setFromSeries(String fromSeries) {
		this.fromSeries = fromSeries;
	}



	public String getToSeries() {
		return toSeries;
	}



	public void setToSeries(String toSeries) {
		this.toSeries = toSeries;
	}



	public String getInitDate() {
		return initDate;
	}



	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}



	public String getReinitDate() {
		return reinitDate;
	}



	public void setReinitDate(String reinitDate) {
		this.reinitDate = reinitDate;
	}



	public String getSeqNo() {
		return seqNo;
	}



	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}



	public String getYearFormat() {
		return yearFormat;
	}



	public void setYearFormat(String yearFormat) {
		this.yearFormat = yearFormat;
	}



	public String getMonthFormat() {
		return monthFormat;
	}



	public void setMonthFormat(String monthFormat) {
		this.monthFormat = monthFormat;
	}



	public String getDateFormat() {
		return dateFormat;
	}



	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}



	public String getSequenceFormat() {
		return sequenceFormat;
	}



	public void setSequenceFormat(String sequenceFormat) {
		this.sequenceFormat = sequenceFormat;
	}



	public String getSeriesFormat() {
		return seriesFormat;
	}



	public void setSeriesFormat(String seriesFormat) {
		this.seriesFormat = seriesFormat;
	}





	public String getDateOrder() {
		return dateOrder;
	}



	public void setDateOrder(String dateOrder) {
		this.dateOrder = dateOrder;
	}



	public String getMonthOrder() {
		return monthOrder;
	}



	public void setMonthOrder(String monthOrder) {
		this.monthOrder = monthOrder;
	}



	public String getYearOrder() {
		return yearOrder;
	}



	public void setYearOrder(String yearOrder) {
		this.yearOrder = yearOrder;
	}



	public String getPartOne() {
		return partOne;
	}



	public void setPartOne(String partOne) {
		this.partOne = partOne;
	}



	public String getPartTwo() {
		return partTwo;
	}



	public void setPartTwo(String partTwo) {
		this.partTwo = partTwo;
	}



	public String getSampleAcceptanceAreaCode() {
		return sampleAcceptanceAreaCode;
	}



	public void setSampleAcceptanceAreaCode(String sampleAcceptanceAreaCode) {
		this.sampleAcceptanceAreaCode = sampleAcceptanceAreaCode;
	}



	public String getAcceptanceAreawise() {
		return acceptanceAreawise;
	}



	public void setAcceptanceAreawise(String acceptanceAreawise) {
		this.acceptanceAreawise = acceptanceAreawise;
	}




}
