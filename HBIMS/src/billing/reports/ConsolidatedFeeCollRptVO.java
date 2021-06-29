package billing.reports;

import javax.sql.rowset.WebRowSet;

public class ConsolidatedFeeCollRptVO {
	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "";
	private String strModuleId = "0";
	private String strReportFormat = "0";
	private String strCounterName = "0";
	private String strHospSerName = "0";
	private String strHospitalCode = "";
	private String strCase = "1";
	private String strChargeId = "0";
	private String strCatType = "1";
	private WebRowSet strCounterWs = null;
	private WebRowSet strClerkWs = null;
	private WebRowSet strTreatCatWs = null;
	private WebRowSet strDeptWs = null;
	private WebRowSet strHospSerWs = null;
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private WebRowSet GrpNameWS = null;
	private WebRowSet TariffNameWS = null;
	
	private String strGroupId = "";
	private String strTariffId = "";
	private String strGroupName="";
	private String strTariffName="";
	private String strGroupTariffId = "";
	private String strGroupTariffVal = "";
	private WebRowSet GroupTariffWS = null;
	
	//Added by Anshul on 3rd April,2012
	private WebRowSet strHospitalWs = null; 
	private String strHospNameValues=""; 
	private String strHospitalName = "";
	private String strSeatId="";
	private String[] strHospitalCode1 ;
	/**
	 * @return the strGroupTariffId
	 */
	public String getStrGroupTariffId() {
		return strGroupTariffId;
	}
	/**
	 * @param strGroupTariffId the strGroupTariffId to set
	 */
	public void setStrGroupTariffId(String strGroupTariffId) {
		this.strGroupTariffId = strGroupTariffId;
	}
	/**
	 * @return the strGroupTariffVal
	 */
	public String getStrGroupTariffVal() {
		return strGroupTariffVal;
	}
	/**
	 * @param strGroupTariffVal the strGroupTariffVal to set
	 */
	public void setStrGroupTariffVal(String strGroupTariffVal) {
		this.strGroupTariffVal = strGroupTariffVal;
	}
	/**
	 * @return the groupTariffWS
	 */
	public WebRowSet getGroupTariffWS() {
		return GroupTariffWS;
	}
	/**
	 * @param groupTariffWS the groupTariffWS to set
	 */
	public void setGroupTariffWS(WebRowSet groupTariffWS) {
		GroupTariffWS = groupTariffWS;
	}
	/**
	 * @return the strGroupId
	 */
	public String getStrGroupId() {
		return strGroupId;
	}
	/**
	 * @param strGroupId the strGroupId to set
	 */
	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}
	/**
	 * @return the strTariffId
	 */
	public String getStrTariffId() {
		return strTariffId;
	}
	/**
	 * @param strTariffId the strTariffId to set
	 */
	public void setStrTariffId(String strTariffId) {
		this.strTariffId = strTariffId;
	}
	/**
	 * @return the strGroupName
	 */
	public String getStrGroupName() {
		return strGroupName;
	}
	/**
	 * @param strGroupName the strGroupName to set
	 */
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}
	/**
	 * @return the strTatiffName
	 */
	public String getStrTariffName() {
		return strTariffName;
	}
	/**
	 * @param strTatiffName the strTatiffName to set
	 */
	public void setStrTariffName(String strTariffName) {
		this.strTariffName = strTariffName;
	}
	/**
	 * @return the grpNameWS
	 */
	public WebRowSet getGrpNameWS() {
		return GrpNameWS;
	}
	/**
	 * @return the tariffNameWS
	 */
	public WebRowSet getTariffNameWS() {
		return TariffNameWS;
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
	public String getStrModuleId() {
		return strModuleId;
	}
	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}
	public String getStrCounterName() {
		return strCounterName;
	}
	public void setStrCounterName(String strCounterName) {
		this.strCounterName = strCounterName;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrChargeId() {
		return strChargeId;
	}
	public void setStrChargeId(String strChargeId) {
		this.strChargeId = strChargeId;
	}
	public WebRowSet getStrCounterWs() {
		return strCounterWs;
	}
	public void setStrCounterWs(WebRowSet strCounterWs) {
		this.strCounterWs = strCounterWs;
	}
	public WebRowSet getStrClerkWs() {
		return strClerkWs;
	}
	public void setStrClerkWs(WebRowSet strClerkWs) {
		this.strClerkWs = strClerkWs;
	}
	public WebRowSet getStrTreatCatWs() {
		return strTreatCatWs;
	}
	public void setStrTreatCatWs(WebRowSet strTreatCatWs) {
		this.strTreatCatWs = strTreatCatWs;
	}
	public WebRowSet getStrDeptWs() {
		return strDeptWs;
	}
	public void setStrDeptWs(WebRowSet strDeptWs) {
		this.strDeptWs = strDeptWs;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrCatType() {
		return strCatType;
	}
	public void setStrCatType(String strCatType) {
		this.strCatType = strCatType;
	}
	public WebRowSet getStrHospSerWs() {
		return strHospSerWs;
	}
	public void setStrHospSerWs(WebRowSet strHospSerWs) {
		this.strHospSerWs = strHospSerWs;
	}
	public String getStrHospSerName() {
		return strHospSerName;
	}
	public void setStrHospSerName(String strHospSerName) {
		this.strHospSerName = strHospSerName;
	}
	/**
	 * @param grpNameWS the grpNameWS to set
	 */
	public void setGrpNameWS(WebRowSet grpNameWS) {
		GrpNameWS = grpNameWS;
	}
	/**
	 * @param tariffNameWS the tariffNameWS to set
	 */
	public void setTariffNameWS(WebRowSet tariffNameWS) {
		TariffNameWS = tariffNameWS;
	}
	public WebRowSet getStrHospitalWs() {
		return strHospitalWs;
	}
	public void setStrHospitalWs(WebRowSet strHospitalWs) {
		this.strHospitalWs = strHospitalWs;
	}
	public String getStrHospNameValues() {
		return strHospNameValues;
	}
	public void setStrHospNameValues(String strHospNameValues) {
		this.strHospNameValues = strHospNameValues;
	}
	public String getStrHospitalName() {
		return strHospitalName;
	}
	public void setStrHospitalName(String strHospitalName) {
		this.strHospitalName = strHospitalName;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String[] getStrHospitalCode1() {
		return strHospitalCode1;
	}
	public void setStrHospitalCode1(String[] strHospitalCode1) {
		this.strHospitalCode1 = strHospitalCode1;
	}
	
}
