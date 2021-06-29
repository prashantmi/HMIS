package new_investigation.vo.template;

import java.util.List;

import hisglobal.vo.ValueObject;

public class TestGroupTreeListVO extends ValueObject {

	public List<Test> testList;
	public List<TestGroup> testGroupList;
	public List<Test> testModifyList;
	public String groupName;
	public String hospitalName;
	
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<Test> getTestModifyList() {
		return testModifyList;
	}
	public void setTestModifyList(List<Test> testModifyList) {
		this.testModifyList = testModifyList;
	}
	public List<TestGroup> getTestGroupModifyList() {
		return testGroupModifyList;
	}
	public void setTestGroupModifyList(List<TestGroup> testGroupModifyList) {
		this.testGroupModifyList = testGroupModifyList;
	}
	public List<TestGroup> testGroupModifyList;
	
	public List<Test> getTestList() {
		return testList;
	}
	public void setTestList(List<Test> testList) {
		this.testList = testList;
	}
	public List<TestGroup> getTestGroupList() {
		return testGroupList;
	}
	public void setTestGroupList(List<TestGroup> testGroupList) {
		this.testGroupList = testGroupList;
	}
	
	
}
