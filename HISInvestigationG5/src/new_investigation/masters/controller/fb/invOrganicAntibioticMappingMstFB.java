package new_investigation.masters.controller.fb;

import org.apache.struts.action.ActionForm;

public class invOrganicAntibioticMappingMstFB extends ActionForm {

	private String hmode;
	private String organicName;
	private String antibioticName;
	private String[] chk;
	private	String[] unmappedList;
	private	String[] mappedList;
	private String cannedName;
	private String selectedChk;
	private String  numberOfRow;
	private String  antibioticSeqNo[];
	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getOrganicName() {
		return organicName;
	}

	public void setOrganicName(String organicName) {
		this.organicName = organicName;
	}

	public String getAntibioticName() {
		return antibioticName;
	}

	public void setAntibioticName(String antibioticName) {
		this.antibioticName = antibioticName;
	}

	public String[] getUnmappedList() {
		return unmappedList;
	}

	public void setUnmappedList(String[] unmappedList) {
		this.unmappedList = unmappedList;
	}

	public String[] getMappedList() {
		return mappedList;
	}

	public void setMappedList(String[] mappedList) {
		this.mappedList = mappedList;
	}

	public String getCannedName() {
		return cannedName;
	}

	public void setCannedName(String cannedName) {
		this.cannedName = cannedName;
	}

	public String getSelectedChk() {
		return selectedChk;
	}

	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}

	

	public String getNumberOfRow() {
		return numberOfRow;
	}

	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}

	public String[] getAntibioticSeqNo() {
		return antibioticSeqNo;
	}

	public void setAntibioticSeqNo(String[] antibioticSeqNo) {
		this.antibioticSeqNo = antibioticSeqNo;
	}
	
	
	
	
	
}
