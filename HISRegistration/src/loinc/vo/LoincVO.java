package loinc.vo;

import hisglobal.vo.ValueObject;

/**
 * @author sheeldarshi
 * DATE : 26th-Sep-2014
 */

public class LoincVO  extends ValueObject 
{
	
	private String strTestName;
	private String strTestNameValue;
	private String strTestParaName;
	private String strTestParaNameValue;
	private String strTestSample;
	private String strTestSampleValue;
	private String strSystem;
	private String strScale;
	private String strProperty;
	private String strTimeofMeasurement;
	private String strUOMCode;
	private String strUOMValue;
	private String strLoincCode;
	private String strComponentName;
	private String strMethod;
	
	private String strTestNameCode;
	private String strTestParaNameCode;
	private String strTestSampleCode;
	private String StrSeqNo;
	private String strOldLoincCode;
	private String strLoincSearchParameter;

	private String isLoincName;
	
	public void reset()
	{
		 strTestName = "";
		 strTestNameValue="";
		 strTestParaName="";
		 strTestParaNameValue="";
		 strTestSample="";
		 strTestSampleValue="";
		 strSystem="";
		 strScale="";
		 strProperty="";
		 strTimeofMeasurement="";
		 strUOMCode="";
		 strUOMValue="";
		 strLoincCode="";
		 strComponentName="";
		 strMethod="";
		 strTestNameCode="";
		 strTestParaNameCode="";
		 strTestSampleCode="";
	}
	
	public String getStrSeqNo() {
		return StrSeqNo;
	}

	public void setStrSeqNo(String StrSeqNo) {
		this.StrSeqNo = StrSeqNo;
	}
	public String getStrTestNameCode() {
		return strTestNameCode;
	}

	public void setStrTestNameCode(String strTestNameCode) {
		this.strTestNameCode = strTestNameCode;
	}
	public String getstrTestParaNameCode() {
		return strTestParaNameCode;
	}

	public void setstrTestParaNameCode(String strTestParaNameCode) {
		this.strTestParaNameCode = strTestParaNameCode;
	}
	public String getstrTestSampleCode() {
		return strTestSampleCode;
	}

	public void setstrTestSampleCode(String strTestSampleCode) {
		this.strTestSampleCode = strTestSampleCode;
	}
	public String getStrLoincCode() {
		return strLoincCode;
	}

	public void setStrLoincCode(String strLoincCode) {
		this.strLoincCode = strLoincCode;
	}
	public String getStrOldLoincCode() {
		return strOldLoincCode;
	}

	public void setStrOldLoincCode(String strOldLoincCode) {
		this.strOldLoincCode = strOldLoincCode;
	}
	public String getStrComponentName() {
		return strComponentName;
	}

	public void setStrComponentName(String strComponentName) {
		this.strComponentName = strComponentName;
	}

	public String getStrMethod() {
		return strMethod;
	}

	public void setStrMethod(String strMethod) {
		this.strMethod = strMethod;
	}
	private String strHospitalCode;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	public String getStrTestName() {
		return strTestName;
	}
	
	public void setStrTestName(String strTestName) {
		this.strTestName = strTestName;
	}
	
	public String getStrTestNameValue() {
		return strTestNameValue;
	}
	
	public void setStrTestNameValue(String strTestNameValue) {
		this.strTestNameValue = strTestNameValue;
	}
	
	public String getStrTestParaName() {
		return strTestParaName;
	}
	
	public void setStrTestParaName(String strTestParaName) {
		this.strTestParaName = strTestParaName;
	}
	
	public String getStrTestParaNameValue() {
		return strTestParaNameValue;
	}
	
	public void setStrTestParaNameValue(String strTestParaNameValue) {
		this.strTestParaNameValue = strTestParaNameValue;
	}
	
	public String getStrTestSample() {
		return strTestSample;
	}
	
	public void setStrTestSample(String strTestSample) {
		this.strTestSample = strTestSample;
	}
	
	public String getStrTestSampleValue() {
		return strTestSampleValue;
	}
	
	public void setStrTestSampleValue(String strTestSampleValue) {
		this.strTestSampleValue = strTestSampleValue;
	}
	
	public String getStrSystem() {
		return strSystem;
	}
	
	public void setStrSystem(String strSystem) {
		this.strSystem = strSystem;
	}
	
	public String getStrScale() {
		return strScale;
	}
	
	public void setStrScale(String strScale) {
		this.strScale = strScale;
	}
	
	public String getStrProperty() {
		return strProperty;
	}
	
	public void setStrProperty(String strProperty) {
		this.strProperty = strProperty;
	}
	
	public String getStrTimeofMeasurement() {
		return strTimeofMeasurement;
	}
	public void setStrTimeofMeasurement(String strTimeofMeasurement) {
		this.strTimeofMeasurement = strTimeofMeasurement;
	}
	public String getStrUOMCode() {
		return strUOMCode;
	}
	public void setStrUOMCode(String strUOMCode) {
		this.strUOMCode = strUOMCode;
	}
	public String getStrUOMValue() {
		return strUOMValue;
	}
	public void setStrUOMValue(String strUOMValue) {
		this.strUOMValue = strUOMValue;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrMsgString() {
		return strMsgString;
	}
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	
	public String getStrLoincSearchParameter() {
		return strLoincSearchParameter;
	}

	public void setStrLoincSearchParameter(String strLoincSearchParameter) {
		this.strLoincSearchParameter = strLoincSearchParameter;
	}

	public String getIsLoincName() {
		return isLoincName;
	}

	public void setIsLoincName(String isLoincName) {
		this.isLoincName = isLoincName;
	}

	
	
	
}
