package new_investigation.vo.template;

import hisglobal.utility.Entry;
import java.util.List;
import java.util.Map;

public class SampleBasedTest extends Test {
	private String  selectedSampleType;
	private String  selectedSampleCode;
	private String  selectedSampleGroupCode;
	//private Map<Integer,SampleGroupDetail>  sampleGroupDetailList;
	private List <Entry> sampleList;// contains both sample and sample group
	private String sampleIDNo;// enter by user
	private String  testSampleVolume;
	private String  selectedUnitOfVolumeCode;
	private List <Entry> testUnitOfVolume;
	//List<MultiSessionDetail> multisessionalDetailList;
	public String getSelectedSampleType() {
		return selectedSampleType;
	}
	public void setSelectedSampleType(String selectedSampleType) {
		this.selectedSampleType = selectedSampleType;
	}
	
	public String getTestSampleVolume() {
		return testSampleVolume;
	}
	public void setTestSampleVolume(String testSampleVolume) {
		this.testSampleVolume = testSampleVolume;
	}
	public String getSelectedUnitOfVolumeCode() {
		return selectedUnitOfVolumeCode;
	}
	public void setSelectedUnitOfVolumeCode(String selectedUnitOfVolumeCode) {
		this.selectedUnitOfVolumeCode = selectedUnitOfVolumeCode;
	}
	public List<Entry> getTestUnitOfVolume() {
		return testUnitOfVolume;
	}
	public void setTestUnitOfVolume(List<Entry> testUnitOfVolume) {
		this.testUnitOfVolume = testUnitOfVolume;
	}
	public List<Entry> getSampleList() {
		return sampleList;
	}
	public void setSampleList(List<Entry> sampleList) {
		this.sampleList = sampleList;
	}
	public String getSampleIDNo() {
		return sampleIDNo;
	}
	public void setSampleIDNo(String sampleIDNo) {
		this.sampleIDNo = sampleIDNo;
	}
//	public List<MultiSessionDetail> getMultisessionalDetailList() {
//		return multisessionalDetailList;
//	}
//	public void setMultisessionalDetailList(
//			List<MultiSessionDetail> multisessionalDetailList) {
//		this.multisessionalDetailList = multisessionalDetailList;
//	}
	public String getSelectedSampleCode() {
		return selectedSampleCode;
	}
	public void setSelectedSampleCode(String selectedSampleCode) {
		this.selectedSampleCode = selectedSampleCode;
	}
//	public Map<Integer, SampleGroupDetail> getSampleGroupDetailList() {
//		return sampleGroupDetailList;
//	}
//	public void setSampleGroupDetailList(
//			Map<Integer, SampleGroupDetail> sampleGroupDetailList) {
//		this.sampleGroupDetailList = sampleGroupDetailList;
//	}
	public String getSelectedSampleGroupCode() {
		return selectedSampleGroupCode;
	}
	public void setSelectedSampleGroupCode(String selectedSampleGroupCode) {
		this.selectedSampleGroupCode = selectedSampleGroupCode;
	}
	
}
