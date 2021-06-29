package vo.registration;

public class ApptTypeVO {
	
	private String strApptTypeId;
	private String strApptTypeName;
	private String strApptTypeColor;
	private String strApptIsDefault;
	private String strIsValid;
	private String strEntryDate;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	
		
	public void reset()
	{
		strApptTypeName="";
		strApptIsDefault="-1";
	}

	
	public String getStrApptTypeId() {
		return strApptTypeId;
	}
	public void setStrApptTypeId(String strApptTypeId) {
		this.strApptTypeId = strApptTypeId;
	}
	public String getStrApptTypeName() {
		return strApptTypeName;
	}
	public void setStrApptTypeName(String strApptTypeName) {
		this.strApptTypeName = strApptTypeName;
	}
	public String getStrApptTypeColor() {
		return strApptTypeColor;
	}
	public void setStrApptTypeColor(String strApptTypeColor) {
		this.strApptTypeColor = strApptTypeColor;
	}
	public String getStrApptIsDefault() {
		return strApptIsDefault;
	}
	public void setStrApptIsDefault(String strApptIsDefault) {
		this.strApptIsDefault = strApptIsDefault;
	}
	public String getStrEntryDate() {
		return strEntryDate;
	}
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
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


}
