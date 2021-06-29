

/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Client Master FB
 * 
 * Created on: 30-08-2011
 */
package billing.masters.controller.fb;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;

public class ClientMstFB extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	private String strHospitalCode = "";
	private String strSeatId = "";
	private String strEffectiveDate = null;
	private String strClientName = null;
	private String strClientCode = null;
	private String strClientType = null;
	private String strRemarks = null;
	private String strRegistrationNo = null;
	private String strPaymentType = "";
	private String strChk = "";

	private String strContactPerson = "";
	private String strContactNo = "";
	private String strClientAddress = "";
	private String strNearestOff = "";
	private String strEmail = "";

	private String strIsValid = "1";
	private String strErr = "";
	private String strWarning = "";
	private String strMsg = "";
	private String strLastModSeatId = "1001";
	private String strCtDate = null;

	private String isOPD = "0";
	private String isIPD = "0";
	private String isEME = "0";
	private String strFaxNo;
	private String strCatValues;//dropdown values of category
	private String strSelCat;
	private String strClientTypeId;
	private String strClientId;//generated at back end..used in modification..
	private String hmode="ADD";
	private String strIpdPayRule ="";

	public String getStrIpdPayRule() {
		return strIpdPayRule;
	}

	public void setStrIpdPayRule(String strIpdPayRule) {
		this.strIpdPayRule = strIpdPayRule;
	}

	public String getStrCtDate() { // function for gettin SYSDATE
		HisUtil util = new HisUtil("Client Master", "ClientMstFB");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "ComplientTypeMaster",
					"VOClientTypeMst.getStrCtDate()-->" + e.getMessage());
		}

		return strCtDate;
	}

	public String getStrLastModSeatId() {
		return strLastModSeatId;
	}

	public void setStrLastModSeatId(String lastModSeatId) {
		this.strLastModSeatId = lastModSeatId;
	}

	public void setStrEffectiveDate(String strEffectiveDate) {
		this.strEffectiveDate = strEffectiveDate;
	}

	public String getStrEffectiveDate() {
		if (this.strEffectiveDate == null) {
			HisUtil util = new HisUtil("Client Master", "ClientMstFB");
			try {
				strEffectiveDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
		 
			}
		}
		return strEffectiveDate;
	}

	public String getStrErr() {
		return strErr;
	}

	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrWarning() {
		return strWarning;
	}

	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}

	public String getStrClientName() {
		return strClientName;
	}

	public void setStrClientName(String strClientName) {
		this.strClientName = strClientName;
	}

	public String getStrClientType() {
		return strClientType;
	}

	public void setStrClientType(String strClientType) {
		this.strClientType = strClientType;
	}

	public String getStrPaymentType() {
		return strPaymentType;
	}

	public void setStrPaymentType(String strPaymentType) {
		this.strPaymentType = strPaymentType;
	}

	public String getStrClientAddress() {
		return strClientAddress;
	}

	public void setStrClientAddress(String strClientAddress) {
		this.strClientAddress = strClientAddress;
	}

	public String getStrContactNo() {
		return strContactNo;
	}

	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}

	public String getStrContactPerson() {
		return strContactPerson;
	}

	public void setStrContactPerson(String strContactPerson) {
		this.strContactPerson = strContactPerson;
	}

	public String getStrEmail() {
		return strEmail;
	}

	public void setStrEmail(String strEmail) {
		this.strEmail = strEmail;
	}

	public String getStrNearestOff() {
		return strNearestOff;
	}

	public void setStrNearestOff(String strNearestOff) {
		this.strNearestOff = strNearestOff;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public String getIsEME() {
		return isEME;
	}

	public void setIsEME(String isEME) {
		this.isEME = isEME;
	}

	public String getIsIPD() {
		return isIPD;
	}

	public void setIsIPD(String isIPD) {
		this.isIPD = isIPD;
	}

	public String getIsOPD() {
		return isOPD;
	}

	public void setIsOPD(String isOPD) {
		this.isOPD = isOPD;
	}

	public String getStrRegistrationNo() {
		return strRegistrationNo;
	}

	public void setStrRegistrationNo(String strRegistrationNo) {
		this.strRegistrationNo = strRegistrationNo;
	}

	public String getStrChk() {
		return strChk;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	public String getStrFaxNo() {
		return strFaxNo;
	}

	public void setStrFaxNo(String strFaxNo) {
		this.strFaxNo = strFaxNo;
	}

	public String getStrCatValues() {
		return strCatValues;
	}

	public void setStrCatValues(String strCatValues) {
		this.strCatValues = strCatValues;
	}

	public String getStrSelCat() {
		return strSelCat;
	}

	public void setStrSelCat(String strSelCat) {
		this.strSelCat = strSelCat;
	}

	public String getStrClientTypeId() {
		return strClientTypeId;
	}

	public void setStrClientTypeId(String strClientTypeId) {
		this.strClientTypeId = strClientTypeId;
	}

	public String getStrClientId() {
		return strClientId;
	}

	public void setStrClientId(String strClientId) {
		this.strClientId = strClientId;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getStrClientCode() {
		return strClientCode;
	}

	public void setStrClientCode(String strClientCode) {
		this.strClientCode = strClientCode;
	}

}
