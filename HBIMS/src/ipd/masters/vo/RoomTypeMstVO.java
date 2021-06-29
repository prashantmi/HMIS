package ipd.masters.vo;

import javax.sql.rowset.WebRowSet;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;

public class RoomTypeMstVO extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	private String strRoomType = "";
	private String strSeatId = "";
	private String strLastModifSeatId = "";
	private String strRemarks = "";
	private String strIsValid = "";
	private String strEffectiveDate = null;
	private String strerrorMsg = "";
    private String strHospitalCode ="";
   	private String chk1 = "";
	private String strErrorMsg = "";
	private String strWarning ="";
	// Getter Method for Variables
	private String strMsg = "";
	private String strCtDate = null;
	private String strNormalMsg="";
	private String strGlobalRoomType="";
	private String strGlobalRoomTypeCombo="";
	private String hmode="";
	WebRowSet wrsGlobalRoomType;
	

	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrMsg() {
		return strMsg;
	}
/**
 * 
 * @param strMsg
 */
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	public String getStrCtDate() { // function for gettin SYSDATE
		HisUtil util = new HisUtil("RoomType", "VORoomTypeMst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "RoomTypeMaster",
					"VORoomTypeMst.getStrCtDate()-->" + e.getMessage());
		}

		return strCtDate;
	}

	public String getStrErrorMsg() {
		return strErrorMsg;
	}
/**
 * 
 * @param strErrorMsg
 */
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}

	public String getChk1() {
		return chk1;
	}

	public String getstrRemarks() {
		return strRemarks;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	
	public String getStrRoomType() {
		return strRoomType;
	}

	public String getStrerrorMsg() {
		return strerrorMsg;
	}

	// Setter Method for Variables
/**
 * 
 */
	public void setChk1(String chk1) {
		this.chk1 = chk1;
	}
/**
 * 
 * @param strRemarks
 */
	public void setstrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
/**
 * 
 * @param strEffectiveDate
 */
	public void setStrEffectiveDate(String strEffectiveDate) {
		this.strEffectiveDate = strEffectiveDate;
	}

	public String getStrEffectiveDate() {
		if (this.strEffectiveDate == null) {
			HisUtil util = new HisUtil("Room Type Master", "VORoomTypeMst");
			try {
				strEffectiveDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return strEffectiveDate;
	}
/**
 * 
 * @param strIsValid
 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	
	public String getStrLastModifSeatId() {
		return strLastModifSeatId;
	}
/**
 * 
 * @param strLastModifSeatId
 */
	public void setStrLastModifSeatId(String strLastModifSeatId) {
		this.strLastModifSeatId = strLastModifSeatId;
	}

	public String getStrSeatId() {
		return strSeatId;
	}
/**
 * 
 * @param strSeatId
 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
/**
 * 
 * @param strRoomType
 */
	public void setStrRoomType(String strRoomType) {
		this.strRoomType = strRoomType;
	}
/**
 * 
 * @param strerrorMsg
 */
	public void setStrerrorMsg(String strerrorMsg) {
		this.strerrorMsg = strerrorMsg;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}
/**
 * 
 * @param strHospitalCode
 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrRemarks() {
		return strRemarks;
	}
/**
 * 
 * @param strRemarks
 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrWarning() {
		return strWarning;
	}
/**
 * 
 * @param strWarning
 */
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrGlobalRoomType() {
		return strGlobalRoomType;
	}
	public void setStrGlobalRoomType(String strGlobalRoomType) {
		this.strGlobalRoomType = strGlobalRoomType;
	}
	public String getStrGlobalRoomTypeCombo() {
		return strGlobalRoomTypeCombo;
	}
	public void setStrGlobalRoomTypeCombo(String strGlobalRoomTypeCombo) {
		this.strGlobalRoomTypeCombo = strGlobalRoomTypeCombo;
	}
	
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	public WebRowSet getWrsGlobalRoomType() {
		return wrsGlobalRoomType;
	}
	public void setWrsGlobalRoomType(WebRowSet wrsGlobalRoomType) {
		this.wrsGlobalRoomType = wrsGlobalRoomType;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	
}
