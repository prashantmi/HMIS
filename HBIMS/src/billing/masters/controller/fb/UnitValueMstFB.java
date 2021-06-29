package billing.masters.controller.fb;
/* Unit Value Master Form Bean
 * author: Debashis Sardar
 * Created on : 16-Sep-2011
 */
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;


public class UnitValueMstFB extends GenericFormBean {

	private static final long serialVersionUID = 02L;
	private String StrLastSeatId = "";
	private String StrSeatId = "";
	private String StrHospitalCode = "";
	private String StrCtDate = "";
	private String StrEntryDate = "";
	private String chk1 = "";
	private String strmoduleName = "";
	private String strfromUnit = "";
	private String strtoUnit = "";
	private String strfromUnit1 = "";
	private String strtoUnit1 = "";
	private String streffectiveFrom = null;
	private String strconvertedValue = "";
	private String strremark = "";
	private String strisValid = "";
	private String strErrorMsg = "";
	private String StrWarning = "";
	private String StrMsg = "";
	private String StrFromUnitCombo = "";
	private String StrToUnitCombo = "";
	private String StrModuleId = "";
	private String strCmbval = "";
	private String strUnitId = "";
	public String[] temp1 = null;
	private String strUnitLevel = ""; 
	private String StrModuleName = ""; 
	private String strMsgType="";
	private String strMsgString="";
	
	public String getStrModuleId() {
		return StrModuleId;
	}

	public void setStrModuleId(String strModuleId) {
		this.StrModuleId = strModuleId;
	}

	public String getChk1() {
		return chk1;
	}

	public void setChk1(String chk1) {
		this.chk1 = chk1;
	}

	

	public String getStrErrorMsg() {
		return strErrorMsg;
	}

	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}

	public String getStrLastSeatId() {
		return StrLastSeatId;
	}

	public void setStrLastSeatId(String strLastSeatId) {
		this.StrLastSeatId = strLastSeatId;
	}

	public String getStrSeatId() {
		return StrSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.StrSeatId = strSeatId;
	}

	public String getStrHospitalCode() {
		return StrHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.StrHospitalCode = strHospitalCode;
	}

	public String getStrCtDate() {
		HisUtil util = new HisUtil("Unit Value Master", "UnitValueMstFB");
		try {
			StrCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Billing Module", "Unit Value Master",
					"UnitValueMstFB.getStrCtDate()-->" + e.getMessage());
		}
		return StrCtDate;
	}

	public void setStrCtDate(String strCtDate) {
		this.StrCtDate = strCtDate;
	}

	public String getStrEntryDate() {
		return StrEntryDate;
	}

	public void setStrEntryDate(String strEntryDate) {
		this.StrEntryDate = strEntryDate;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getStrWarning() {
		return StrWarning;
	}

	public void setStrWarning(String strWarning) {
		this.StrWarning = strWarning;
	}

	public String getStrMsg() {
		return StrMsg;
	}

	public void setStrMsg(String strMsg) {
		this.StrMsg = strMsg;
	}

	
	public String getStrFromUnitCombo() {
		return StrFromUnitCombo;
	}

	public void setStrFromUnitCombo(String strFromUnitCombo) {
		this.StrFromUnitCombo = strFromUnitCombo;
	}

	public String getStrToUnitCombo() {
		return StrToUnitCombo;
	}

	public void setStrToUnitCombo(String strToUnitCombo) {
		this.StrToUnitCombo = strToUnitCombo;
	}

	

	public String getStrmoduleName() {
		return strmoduleName;
	}

	public void setStrmoduleName(String strmoduleName) {
		this.strmoduleName = strmoduleName;
	}

	public String getStrfromUnit() {
		return strfromUnit;
	}

	public void setStrfromUnit(String strfromUnit) {
		this.strfromUnit = strfromUnit;
	}

	public String getStrtoUnit() {
		return strtoUnit;
	}

	public void setStrtoUnit(String strtoUnit) {
		this.strtoUnit = strtoUnit;
	}

	public String getStrfromUnit1() {
		return strfromUnit1;
	}

	public void setStrfromUnit1(String strfromUnit1) {
		this.strfromUnit1 = strfromUnit1;
	}

	public String getStrtoUnit1() {
		return strtoUnit1;
	}

	public void setStrtoUnit1(String strtoUnit1) {
		this.strtoUnit1 = strtoUnit1;
	}

	public String getStreffectiveFrom() {
		return streffectiveFrom;
	}

	public void setStreffectiveFrom(String streffectiveFrom) {
		this.streffectiveFrom = streffectiveFrom;
	}

	public String getStrconvertedValue() {
		return strconvertedValue;
	}

	public void setStrconvertedValue(String strconvertedValue) {
		this.strconvertedValue = strconvertedValue;
	}

	public String getStrremark() {
		return strremark;
	}

	public void setStrremark(String strremark) {
		this.strremark = strremark;
	}

	public String getStrisValid() {
		return strisValid;
	}

	public void setStrisValid(String strisValid) {
		this.strisValid = strisValid;
	}

	public String getStrModuleName() {
		return StrModuleName;
	}

	public void setStrModuleName(String strModuleName) {
		StrModuleName = strModuleName;
	}

	public String getStrCmbval() {
		return strCmbval;
	}

	public void setStrCmbval(String strCmbval) {
		this.strCmbval = strCmbval;
	}

	public String getStrUnitId() {
		return strUnitId;
	}

	public void setStrUnitId(String strUnitId) {
		this.strUnitId = strUnitId;
	}

	public String getStrUnitLevel() {
		return strUnitLevel;
	}

	public void setStrUnitLevel(String strUnitLevel) {
		this.strUnitLevel = strUnitLevel;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

}
