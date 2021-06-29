package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LoincMstFB extends ActionForm {

	private String loincName;
	private String loincCode;
	private String seqNo;
	private String testCode;
	private String parentParaCode;
	private String paraCode;
	private String sampleCode;
	private String uomCode;
	private String methodCode;
	private String organismcode;
	private String hmode;
	private String chk[];
	private String testName;
	private String paraName;
	private String sampleName;
	private String uomName;
	private String methodName;
	private String organismName;
	private String loincTime;
	private String loincSystem;
	private String loincValues;
	private String loincScale;
	private String loincProperty;
	private String loincSearch;
	private String mode;
	private String currentPageNo="1";
	private int currentPage;
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.currentPage=1;
	}
	
	public String getLoincName() {
		return loincName;
	}
	public void setLoincName(String loincName) {
		this.loincName = loincName;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getParentParaCode() {
		return parentParaCode;
	}
	public void setParentParaCode(String parentParaCode) {
		this.parentParaCode = parentParaCode;
	}
	public String getParaCode() {
		return paraCode;
	}
	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}
	public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
	public String getUomCode() {
		return uomCode;
	}
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}
	public String getMethodCode() {
		return methodCode;
	}
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}
	public String getOrganismcode() {
		return organismcode;
	}
	public void setOrganismcode(String organismcode) {
		this.organismcode = organismcode;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getOrganismName() {
		return organismName;
	}
	public void setOrganismName(String organismName) {
		this.organismName = organismName;
	}
	public String getLoincTime() {
		return loincTime;
	}
	public void setLoincTime(String loincTime) {
		this.loincTime = loincTime;
	}
	public String getLoincSystem() {
		return loincSystem;
	}
	public void setLoincSystem(String loincSystem) {
		this.loincSystem = loincSystem;
	}
	public String getLoincValues() {
		return loincValues;
	}
	public void setLoincValues(String loincValues) {
		this.loincValues = loincValues;
	}
	public String getLoincScale() {
		return loincScale;
	}
	public void setLoincScale(String loincScale) {
		this.loincScale = loincScale;
	}
	public String getLoincProperty() {
		return loincProperty;
	}
	public void setLoincProperty(String loincProperty) {
		this.loincProperty = loincProperty;
	}
	public String getLoincSearch() {
		return loincSearch;
	}
	public void setLoincSearch(String loincSearch) {
		this.loincSearch = loincSearch;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getLoincCode() {
		return loincCode;
	}
	public void setLoincCode(String loincCode) {
		this.loincCode = loincCode;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	
	
}
