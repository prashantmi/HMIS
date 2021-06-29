package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class InvCriteriaCodeVO extends ValueObject
{
	public String testCode;
	public String paramterCode;
	public String criteriaCode;
	
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getParamterCode() {
		return paramterCode;
	}
	public void setParamterCode(String paramterCode) {
		this.paramterCode = paramterCode;
	}
	public String getCriteriaCode() {
		return criteriaCode;
	}
	public void setCriteriaCode(String criteriaCode) {
		this.criteriaCode = criteriaCode;
	}

}
