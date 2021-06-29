package hisglobal.vo;

import hisglobal.utility.Entry;

import java.util.List;

public class SampleGroupDetail extends ValueObject {
	String sampleGroupCode;
	String sampleGroupName;
	List<Entry> sampleList;
	public String getSampleGroupCode() {
		return sampleGroupCode;
	}
	public void setSampleGroupCode(String sampleGroupCode) {
		this.sampleGroupCode = sampleGroupCode;
	}
	public String getSampleGroupName() {
		return sampleGroupName;
	}
	public void setSampleGroupName(String sampleGroupName) {
		this.sampleGroupName = sampleGroupName;
	}
	public List<Entry> getSampleList() {
		return sampleList;
	}
	public void setSampleList(List<Entry> sampleList) {
		this.sampleList = sampleList;
	}

}
