package hisglobal.vo;

public class ProcServiceMappingMasterVO extends ValueObject
{
	private String hmode;
	private String isValid="1";//1 active 2 inactive
	private String strProcCode;
	private String strServiceName[];//array of service names..
	private String msg;
	private String strhospCode;
	private String strSeatId;
	private String chk[];
	private String procedureName;
	private String strService;//service name to be used in modify page...
	private String strMsgType;
	private String maxSittings;//max sittings bound to procedure..
	private String strServiceCode;
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getStrProcCode() {
		return strProcCode;
	}
	public void setStrProcCode(String strProcCode) {
		this.strProcCode = strProcCode;
	}
	public String[] getStrServiceName() {
		return strServiceName;
	}
	public void setStrServiceName(String[] strServiceName) {
		this.strServiceName = strServiceName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getStrService() {
		return strService;
	}
	public void setStrService(String strService) {
		this.strService = strService;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getMaxSittings() {
		return maxSittings;
	}
	public void setMaxSittings(String maxSittings) {
		this.maxSittings = maxSittings;
	}
	public String getStrServiceCode() {
		return strServiceCode;
	}
	public void setStrServiceCode(String strServiceCode) {
		this.strServiceCode = strServiceCode;
	}
	
	
}
