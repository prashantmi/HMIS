/* Remarks Master VO
 * author: Pawan Kumar B N
 * Created on : 26-Aug-2011
 */

package billing.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import hisglobal.vo.ValueObject;

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalRemarksMstVO.
 */
public class GlobalRemarksMstVO extends ValueObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;
	
	/** The Str seat id. */
	private String StrSeatId = null;
	
	/** The str remarks desc. */
	private String strRemarksDesc = null;
	
	/** The str remarks type. */
	private String strRemarksType = null;
	
	/** The str effective from. */
	private String strEffectiveFrom = null;
	
	/** The str remarks for. */
	private String strRemarksFor = null;
	
	/** The str valid. */
	private String strValid = null;
	
	/** The str err. */
	private String strErr = "";
	
	/** The str warning. */
	private String strWarning = "";
	
	/** The str msg. */
	private String strMsg = "";
	
	/** The str last mod seat id. */
	private String strLastModSeatId = "";
	
	/** The str last mod date. */
	private String strLastModDate = "";
	
	/** The str entry date. */
	private String strEntryDate= "";
	
	/** The str hospital code. */
	private String strHospitalCode= "";
	
	/** The str ct date. */
	private String strCtDate= "";
	
	/** The str crnt date. */
	private String strCrntDate = null;
	
		
	/**
	 * Gets the str last mod seat id.
	 * 
	 * @return the str last mod seat id
	 */
	public String getStrLastModSeatId() {
		return strLastModSeatId;
	}

	/**
	 * Sets the str last mod seat id.
	 * 
	 * @param lastModSeatId the new str last mod seat id
	 */
	public void setStrLastModSeatId(String lastModSeatId) {
		this.strLastModSeatId = lastModSeatId;
	}

	/**
	 * Gets the str remarks desc.
	 * 
	 * @return the str remarks desc
	 */
	public String getStrRemarksDesc() {
		return strRemarksDesc;
	}

	/**
	 * Sets the str remarks desc.
	 * 
	 * @param remarksDesc the new str remarks desc
	 */
	public void setStrRemarksDesc(String remarksDesc) {
		this.strRemarksDesc = remarksDesc;
	}

	/**
	 * Gets the str remarks type.
	 * 
	 * @return the str remarks type
	 */
	public String getStrRemarksType() {
		return strRemarksType;
	}

	/**
	 * Sets the str remarks type.
	 * 
	 * @param remarksType the new str remarks type
	 */
	public void setStrRemarksType(String remarksType) {
		this.strRemarksType = remarksType;
	}

	/**
	 * retrieves Current Date from Application Server.
	 * 
	 * @return current Date.
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	
	/**
	 * Sets the str effective from.
	 * 
	 * @param effectiveFrom the new str effective from
	 */
	public void setStrEffectiveFrom(String effectiveFrom) {
		this.strEffectiveFrom = effectiveFrom;
	}

	/**
	 * Gets the str remarks for.
	 * 
	 * @return the str remarks for
	 */
	public String getStrRemarksFor() {
		
		if(strRemarksFor ==null)
			strRemarksFor = "";
		
		return strRemarksFor;
	}

	/**
	 * Sets the str remarks for.
	 * 
	 * @param remarksFor the new str remarks for
	 */
	public void setStrRemarksFor(String remarksFor) {
		this.strRemarksFor = remarksFor;
	}

	/**
	 * Gets the str err.
	 * 
	 * @return the str err
	 */
	public String getStrErr() {
		return strErr;
	}

	/**
	 * Sets the str err.
	 * 
	 * @param err the new str err
	 */
	public void setStrErr(String err) {
		this.strErr = err;
	}

	/**
	 * Gets the str valid.
	 * 
	 * @return the str valid
	 */
	public String getStrValid() {
		return strValid;
	}

	/**
	 * Sets the str valid.
	 * 
	 * @param valid the new str valid
	 */
	public void setStrValid(String valid) {
		this.strValid = valid;
	}

	/**
	 * Gets the str warning.
	 * 
	 * @return the str warning
	 */
	public String getStrWarning() {
		return strWarning;
	}

	/**
	 * Sets the str warning.
	 * 
	 * @param warning the new str warning
	 */
	public void setStrWarning(String warning) {
		this.strWarning = warning;
	}

	/**
	 * Gets the str msg.
	 * 
	 * @return the str msg
	 */
	public String getStrMsg() {
		return strMsg;
	}

	/**
	 * Sets the str msg.
	 * 
	 * @param msg the new str msg
	 */
	public void setStrMsg(String msg) {
		this.strMsg = msg;
	}

	/**
	 * Gets the str seat id.
	 * 
	 * @return the str seat id
	 */
	public String getStrSeatId() {
		return StrSeatId;
	}

	/**
	 * Sets the str seat id.
	 * 
	 * @param strSeatId the new str seat id
	 */
	public void setStrSeatId(String strSeatId) {
		StrSeatId = strSeatId;
	}

	/**
	 * Gets the str last mod date.
	 * 
	 * @return the str last mod date
	 */
	public String getStrLastModDate() {
		return strLastModDate;
	}

	/**
	 * Sets the str last mod date.
	 * 
	 * @param strLastModDate the new str last mod date
	 */
	public void setStrLastModDate(String strLastModDate) {
		this.strLastModDate = strLastModDate;
	}

	/**
	 * Gets the str entry date.
	 * 
	 * @return the str entry date
	 */
	public String getStrEntryDate() {
		return strEntryDate;
	}

	/**
	 * Sets the str entry date.
	 * 
	 * @param strEntryDate the new str entry date
	 */
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	/**
	 * Gets the str hospital code.
	 * 
	 * @return the str hospital code
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	/**
	 * Sets the str hospital code.
	 * 
	 * @param strHospitalCode the new str hospital code
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * Gets the serial version uid.
	 * 
	 * @return the serial version uid
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	 /**
 	 * Gets the str ct date.
 	 * 
 	 * @return the str ct date
 	 */
 	public String getStrCtDate() {
			HisUtil util = new HisUtil("Remarks Master", "GlobalRemarksMstVO");
			try {
				strCtDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			 } catch (Exception e) {
				new HisException("Billing Module", "Remarks Master",
						"GlobalRemarksMstVO.getStrCtDate()-->" + e.getMessage());
		     }
		      return strCtDate;
		   }

	/**
	 * Sets the str ct date.
	 * 
	 * @param strCtDate the new str ct date
	 */
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	
/**
 * Gets the str crnt date.
 * 
 * @return the str crnt date
 * 
 * @throws Exception the exception
 */
public String getStrCrntDate() throws Exception {
		
		if (this.strCrntDate == null) {
			HisUtil util = new HisUtil("billing", "GlobalRemarksMstVO");
			try {
				this.strCrntDate =  util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				throw new Exception("Billing.RemarksMstVO.getStrCrntDate()-->"
						+ e.getMessage());
			}
		}
		
		return strCrntDate;
	}

/**
 * Sets the str crnt date.
 * 
 * @param strCrntDate the new str crnt date
 */
public void setStrCrntDate(String strCrntDate) {
	this.strCrntDate = strCrntDate;
}


}
