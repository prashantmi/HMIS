package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;


public class WardDiseaseTypeMstVO extends GenericFormBean {
	
	private static final long serialVersionUID = 02L;
	
	private String strWarning = "";
	private String strMsg = "";
	private String strDiseaseTypeCode="";
	private String strDiseaseTypeName = "";
	private String strSeatId = "";
	private String strLastModifiedSeatId = "";
	private String strIsValid = "";
	private String strEffectiveDate = null;
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strErrorMsg = "";
	private String strChk1 = "";
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
	
	public String getStrDiseaseTypeCode() {
		return strDiseaseTypeCode;
	}
	
	public void setStrDiseaseTypeCode(String strDiseaseTypeCode) {
		this.strDiseaseTypeCode = strDiseaseTypeCode;
	}
	
	public String getStrDiseaseTypeName() {
		return strDiseaseTypeName;
	}
	
	public void setStrDiseaseTypeName(String strDiseaseTypeName) {
		this.strDiseaseTypeName = strDiseaseTypeName;
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
	public String getStrEffectiveDate() {
		if (this.strEffectiveDate == null) {
			HisUtil util = new HisUtil("Ward Disease Type Master", "VOWardDiseaseTypeMst");
			try {
				strEffectiveDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Ipd Module", "Ward Disease Type Master",
						"VOWardDiseaseTypeMst.getStrEffectiveDate()-->" + e.getMessage());
		
			}
		}
		return strEffectiveDate;
	}
	
	public void setStrEffectiveDate(String strEffectiveDate) {
		this.strEffectiveDate = strEffectiveDate;
	}
	
	/**
	 * retrieves and returns the Application Server Current Date if the Existing
	 * Date is Null
	 * 
	 * @return - Current Date in String Format.
	 */
	public String getStrEffectiveFrom() {
		if (this.strEffectiveFrom == null) {
			HisUtil util = new HisUtil("Ward Disease Type Master", "VOWardDiseaseTypeMst");
			try {
				strEffectiveFrom = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Ipd Module", "Ward Disease Type Master",
						"VOWardDiseaseTypeMst.getStrEffectiveFrom()-->" + e.getMessage());
		
			}
		}
		return strEffectiveFrom;
	}
	
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	
	/**
	 * retrieves and returns the Application Server Current Date if the Existing
	 * Date is Null
	 * 
	 * @return - Current Date in String Format.
	 */
	public String getStrEffectiveTo() {
		if (this.strEffectiveTo == null) {
			HisUtil util = new HisUtil("Ward Disease Type Master", "VOWardDiseaseTypeMst");
			try {
				strEffectiveTo = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Ipd Module", "Ward Disease Type Master",
						"VOWardDiseaseTypeMst.getStrEffectiveTo()-->" + e.getMessage());
		
			}
		}
		return strEffectiveTo;
		
	}
	
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	
	public String getStrChk1() {
		return strChk1;
	}
	
	public void setStrChk1(String strChk1) {
		this.strChk1 = strChk1;
	}
	
	public String getStrRemarks() {
		return strRemarks;
	}
	
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrCtDate() {
	     HisUtil util = new HisUtil("Ward Disease Type Master", "VO Ward Disease Type Mst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "Ward Disease Type Master",
					"VOWardDiseaseTypeMst.getStrCtDate()-->" + e.getMessage());
		}
		return strCtDate;
	}
	
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

}
