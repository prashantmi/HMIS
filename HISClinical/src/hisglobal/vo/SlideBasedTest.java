package hisglobal.vo;
import java.util.List;
import java.util.Map;

import hisglobal.utility.Entry;
import hisglobal.vo.MultiSessionDetail;

public class SlideBasedTest extends Test {
	private String  testComponentType;
	private String  selectedSpecimenType;
	private String  selectedSpecimenCode;
	private String  selectedSpecimenName;
	private String  selectedSpecimenGroupCode;
	private Map<Integer,SampleGroupDetail>  specimenGroupDetailList;//contains slide Types
	private List <Entry> specimenList;// contains both specimen and specimen group
	private String specimenId;
	public String getSpecimenId() {
		return specimenId;
	}
	public void setSpecimenId(String specimenId) {
		this.specimenId = specimenId;
	}
	public String getTestComponentType() {
		return testComponentType;
	}
	public void setTestComponentType(String testComponentType) {
		this.testComponentType = testComponentType;
	}
	public String getSelectedSpecimenType() {
		return selectedSpecimenType;
	}
	public void setSelectedSpecimenType(String selectedSpecimenType) {
		this.selectedSpecimenType = selectedSpecimenType;
	}
	public String getSelectedSpecimenCode() {
		return selectedSpecimenCode;
	}
	public void setSelectedSpecimenCode(String selectedSpecimenCode) {
		this.selectedSpecimenCode = selectedSpecimenCode;
	}
	public Map<Integer,SampleGroupDetail> getSpecimenGroupDetailList() {
		return specimenGroupDetailList;
	}
	public void setSpecimenGroupDetailList(
			Map <Integer,SampleGroupDetail> specimenGroupDetailList) {
		this.specimenGroupDetailList = specimenGroupDetailList;
	}
	public List<Entry> getSpecimenList() {
		return specimenList;
	}
	public void setSpecimenList(List<Entry> specimenList) {
		this.specimenList = specimenList;
	}
	public String getSelectedSpecimenGroupCode() {
		return selectedSpecimenGroupCode;
	}
	public void setSelectedSpecimenGroupCode(String selectedSpecimenGroupCode) {
		this.selectedSpecimenGroupCode = selectedSpecimenGroupCode;
	}
	public String getSelectedSpecimenName() {
		return selectedSpecimenName;
	}
	public void setSelectedSpecimenName(String selectedSpecimenName) {
		this.selectedSpecimenName = selectedSpecimenName;
	}
	public void populateComponentType()
	{
		if(this.testComponentType.equals("0"))
		{
			this.testComponentType="Specimen";
		}
		else
		{
			this.testComponentType="Slide";
		}
	}
	public boolean populateSelectedSpecimenName()
	{
		for(Entry entry:specimenList)
		{
			if(this.selectedSpecimenCode.equals(entry.getValue()))
			{
				this.selectedSpecimenName=entry.getLabel();
				return true;
			}
		}
		return false;
	}

}
