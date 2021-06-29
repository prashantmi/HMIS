package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;

public class ComponentMstVO extends GenericFormBean {
	private static final long serialVersionUID = 02L;

	private String strWarning = "";
	private String strMsg = "";
	private String strComponentCode = "";
	private String strComponentName = "";
	private String strSeatId = "";
	private String strLastModifiedSeatId = "";
	private String strIsValid = "";
	private String strEffectiveFrom = null;
	private String strErrorMsg = "";
	
	private String strRemarks = "";
	private String strCtDate = null;
	private String strHospitalCode="";
	
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
	public String getStrComponentCode() {
		return strComponentCode;
	}
	public void setStrComponentCode(String strComponentCode) {
		this.strComponentCode = strComponentCode;
	}
	public String getStrComponentName() {
		return strComponentName;
	}
	public void setStrComponentName(String strComponentName) {
		this.strComponentName = strComponentName;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrLastModifiedSeatId() {
		return strLastModifiedSeatId;
	}
	public void setStrLastModifiedSeatId(String strLastModifiedSeatId) {
		this.strLastModifiedSeatId = strLastModifiedSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	/**
	 * retrieves and returns the Application Server Current Date if the Existing
	 * Date is Null
	 * 
	 * @return - Current Date in String Format.
	 */
	public String getStrEffectiveFrom() {
		if (this.strEffectiveFrom == null) {
			HisUtil util = new HisUtil("Component Master", "VOComponentMst");
			try {
				strEffectiveFrom = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	/*public String getStrChk1() {
		return strChk1;
	}
	public void setStrChk1(String strChk1) {
		this.strChk1 = strChk1;
	}*/
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrCtDate() {
		HisUtil util = new HisUtil("Component Master", "VOComponentMst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "Component Master",
					"VOComponentMst.getStrCtDate()-->" + e.getMessage());
		}

		return strCtDate;
	}

}
