package hisglobal.vo;

public class ProcedureMasterVO extends ValueObject
{
	private String strhospCode;
	private String isValid="1";//1 active 2 inactive
	private String strProcname;
	private String strIsSchReq="0";	//1 req 0 not req
	private String strIsApptReqd="0";	//1 req 0 not req
	private String strIsBillReq="0";//1 req 0 not req
	private String msg;
	private String strProcCat="0";// 0 non immunization,1 immunization
	private String strMaxSittings="0";//max no of sittings..
	private String strSeatId;
	private String strMsgType;//1 error
	private String procedureCode;
	private String strMaxSittingsHidden;//max no of sittings..
	private String strImmunizationCount="0";//count of max sittings in immunizable proc..
	
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getStrProcname() {
		return strProcname;
	}
	public void setStrProcname(String strProcname) {
		this.strProcname = strProcname;
	}
	public String getStrIsSchReq() {
		return strIsSchReq;
	}
	public void setStrIsSchReq(String strIsSchReq) {
		this.strIsSchReq = strIsSchReq;
	}
	public String getStrIsApptReqd() {
		return strIsApptReqd;
	}
	public void setStrIsApptReqd(String strIsApptReqd) {
		this.strIsApptReqd = strIsApptReqd;
	}
	public String getStrIsBillReq() {
		return strIsBillReq;
	}
	public void setStrIsBillReq(String strIsBillReq) {
		this.strIsBillReq = strIsBillReq;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStrProcCat() {
		return strProcCat;
	}
	public void setStrProcCat(String strProcCat) {
		this.strProcCat = strProcCat;
	}
	public String getStrMaxSittings() {
		return strMaxSittings;
	}
	public void setStrMaxSittings(String strMaxSittings) {
		this.strMaxSittings = strMaxSittings;
	}
	public String getStrhospCode() {
		return strhospCode;
	}
	public void setStrhospCode(String strhospCode) {
		this.strhospCode = strhospCode;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}
	public String getStrMaxSittingsHidden() {
		return strMaxSittingsHidden;
	}
	public void setStrMaxSittingsHidden(String strMaxSittingsHidden) {
		this.strMaxSittingsHidden = strMaxSittingsHidden;
	}
	public String getStrImmunizationCount() {
		return strImmunizationCount;
	}
	public void setStrImmunizationCount(String strImmunizationCount) {
		this.strImmunizationCount = strImmunizationCount;
	}
	
	
}
