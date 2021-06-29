package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class invOrganicAntibioticMappingMstVO extends ValueObject {

	private String organicName;
	private String antibioticName;
	private	String[] mappedList;
	private String antibioticNameCode;
	private String antibioticSeqNo;
	
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
	public String[] getMappedList() {
		return mappedList;
	}
	public void setMappedList(String[] mappedList) {
		this.mappedList = mappedList;
	}
	public String getAntibioticNameCode() {
		return antibioticNameCode;
	}
	public void setAntibioticNameCode(String antibioticNameCode) {
		this.antibioticNameCode = antibioticNameCode;
	}
	public String getAntibioticSeqNo() {
		return antibioticSeqNo;
	}
	public void setAntibioticSeqNo(String antibioticSeqNo) {
		this.antibioticSeqNo = antibioticSeqNo;
	}
	
	
	
	
	
	
}
