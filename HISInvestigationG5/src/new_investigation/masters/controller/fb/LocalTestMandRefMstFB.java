package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LocalTestMandRefMstFB extends ActionForm
{
	private String hmode;
	private String chk[];
	
	private String testCode;
	private String parameterCode;
	private String criteriaCode;

	
	
	private String numberOfRow;
	private String selectedChk;
	
	private String seqNo[];
	private String gender[];
	private String lowAge[];
	private String highAge[];
	
	private String lowValueUom[];
	
	private String highValue[];
	private String lowValue[];
	private String remarks[];
	
	private String highValueUom[];
	
	private String symbol[];
	
	private String lowAgeUom[];
	private String highAgeUom[];
	private String sampleCode[];
	private String labCode[];
	private String mandCode[];
	private String seqNoModify;
	
	private String oldSeq[];
	private String newSeq[];
	
	private String numberOfRowPresent;
	
	private String modify[];
	private String delete[];
	private String fromGlobal;
	
	 private String rangeTyp;
	    private String range[];
	    private String rangeUom[];

	
	public String getFromGlobal() {
		return fromGlobal;
	}


	public void setFromGlobal(String fromGlobal) {
		this.fromGlobal = fromGlobal;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.testCode="-1";
		this.parameterCode="-1";

		
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


	public String getNumberOfRow() {
		return numberOfRow;
	}



	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}



	
	

	public String getTestCode() {
		return testCode;
	}


	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}


	public String getParameterCode() {
		return parameterCode;
	}


	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}


	

	public String getSelectedChk() {
		return selectedChk;
	}


	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}




	public String getCriteriaCode() {
		return criteriaCode;
	}


	public void setCriteriaCode(String criteriaCode) {
		this.criteriaCode = criteriaCode;
	}


	public String[] getSeqNo() {
		return seqNo;
	}


	public void setSeqNo(String[] seqNo) {
		this.seqNo = seqNo;
	}


	public String[] getGender() {
		return gender;
	}


	public void setGender(String[] gender) {
		this.gender = gender;
	}


	public String[] getLowAge() {
		return lowAge;
	}


	public void setLowAge(String[] lowAge) {
		this.lowAge = lowAge;
	}


	public String[] getHighAge() {
		return highAge;
	}


	public void setHighAge(String[] highAge) {
		this.highAge = highAge;
	}


	public String[] getLowValueUom() {
		return lowValueUom;
	}


	public void setLowValueUom(String[] lowValueUom) {
		this.lowValueUom = lowValueUom;
	}


	public String[] getHighValue() {
		return highValue;
	}


	public void setHighValue(String[] highValue) {
		this.highValue = highValue;
	}


	public String[] getLowValue() {
		return lowValue;
	}


	public void setLowValue(String[] lowValue) {
		this.lowValue = lowValue;
	}


	public String[] getRemarks() {
		return remarks;
	}


	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}


	public String[] getHighValueUom() {
		return highValueUom;
	}


	public void setHighValueUom(String[] highValueUom) {
		this.highValueUom = highValueUom;
	}


	public String[] getSymbol() {
		return symbol;
	}


	public void setSymbol(String[] symbol) {
		this.symbol = symbol;
	}


	public String[] getLowAgeUom() {
		return lowAgeUom;
	}


	public void setLowAgeUom(String[] lowAgeUom) {
		this.lowAgeUom = lowAgeUom;
	}


	public String[] getHighAgeUom() {
		return highAgeUom;
	}


	public void setHighAgeUom(String[] highAgeUom) {
		this.highAgeUom = highAgeUom;
	}


	public String[] getSampleCode() {
		return sampleCode;
	}


	public void setSampleCode(String[] sampleCode) {
		this.sampleCode = sampleCode;
	}


	public String[] getLabCode() {
		return labCode;
	}


	public void setLabCode(String[] labCode) {
		this.labCode = labCode;
	}


	public String[] getMandCode() {
		return mandCode;
	}


	public void setMandCode(String[] mandCode) {
		this.mandCode = mandCode;
	}


	public String getSeqNoModify() {
		return seqNoModify;
	}


	public void setSeqNoModify(String seqNoModify) {
		this.seqNoModify = seqNoModify;
	}


	public String[] getOldSeq() {
		return oldSeq;
	}


	public void setOldSeq(String[] oldSeq) {
		this.oldSeq = oldSeq;
	}


	public String[] getNewSeq() {
		return newSeq;
	}


	public void setNewSeq(String[] newSeq) {
		this.newSeq = newSeq;
	}


	public String getNumberOfRowPresent() {
		return numberOfRowPresent;
	}


	public void setNumberOfRowPresent(String numberOfRowPresent) {
		this.numberOfRowPresent = numberOfRowPresent;
	}


	public String[] getModify() {
		return modify;
	}


	public void setModify(String[] modify) {
		this.modify = modify;
	}


	public String[] getDelete() {
		return delete;
	}


	public void setDelete(String[] delete) {
		this.delete = delete;
	}


	public String getRangeTyp() {
		return rangeTyp;
	}


	public void setRangeTyp(String rangeTyp) {
		this.rangeTyp = rangeTyp;
	}


	public String[] getRange() {
		return range;
	}


	public void setRange(String[] range) {
		this.range = range;
	}


	public String[] getRangeUom() {
		return rangeUom;
	}


	public void setRangeUom(String[] rangeUom) {
		this.rangeUom = rangeUom;
	}


	


}
