package hisglobal.vo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestTemplateVO extends ValueObject
{
	private String requisitionDNO;
	private String sampleNO;
	private String testCode;
	private String testName;
	private String paraType;
	 Map<String ,TestParameterVO> testParameterObj;
	 List<MandatoryInfoVO> mandatoryInfoObj;
	public String getRequisitionDNO() {
		return requisitionDNO;
	}
	public void setRequisitionDNO(String requisitionDNO) {
		this.requisitionDNO = requisitionDNO;
	}
	public String getSampleNO() {
		return sampleNO;
	}
	public void setSampleNO(String sampleNO) {
		this.sampleNO = sampleNO;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public  Map<String,TestParameterVO> getTestParameterObj() {
		return testParameterObj;
	}
	public void setTestParameterObj( Map<String,TestParameterVO> testParameterObj) {
		this.testParameterObj = testParameterObj;
	}
	public List<MandatoryInfoVO> getMandatoryInfoObj() {
		return mandatoryInfoObj;
	}
	public void setMandatoryInfoObj(List<MandatoryInfoVO> mandatoryInfoObj) {
		this.mandatoryInfoObj = mandatoryInfoObj;
	}
	public String getParaType() {
		return paraType;
	}
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}

}
