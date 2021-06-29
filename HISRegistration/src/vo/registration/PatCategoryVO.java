/**
 * 
 */
package vo.registration;

/**
 * @author s.singaravelan
 * DATE : 07-Jan-2014
 */
public class PatCategoryVO 
{
	
	private String strPatCategoryCode;
	private String strPatCategoryName;
	private String strPatCategoryShort;
	private String strPatCategoryType;
	private String strPatCategoryGroup;
	/*Start: Surabhi
	 * reason: for adding client combo
	 * date : 29-7-2016*/
	private String strPatClient;
	//End
	private String strIsPaid;
	private String strIdSize;
	private String strCatPriority;
	private String strIsExpiry;
	private String strIdValidationType;
	private String strIdVerificationDoc;
	private String strEconomicStatus;
	private String strExpiryMonth;
	private String strExpiryDays;
	private String strBenefit;
	private String strCriteria;
	private String strIsIdReq;
	private String strRemarks;
	
	private String strGlobalPatCategoryName;
	
	private String strSeatId;	
	private String strHospitalCode;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private String strOldPatCategoryName;

	private String strIsApprovalRequired;
	//By Mukund on 03.11.2017
	private String strIsCatSpl;
	private String strBoundDeptCode;
	//End On 03.11.2017
	
	//Added by Vasu on 14.May.2018
	private String strApplicableServices;
	private String strApplicableServicesCode;
	private String strIsDefault;
	
	//End Vasu
	public void reset()
	{
		strPatCategoryCode="";
		strPatCategoryType="-1";
		strPatCategoryName="";
		strPatCategoryShort="";
		strPatCategoryGroup="0";
		strEconomicStatus="-1";
		strCatPriority="-1";
		strIsPaid="-1";
		strIsIdReq="-1";
		strIdVerificationDoc="-1";
		strIdSize="";
		strIdValidationType="-1";
		strIsExpiry="-1";
		strExpiryMonth="";
		strExpiryDays="";
		strBenefit="";
		strCriteria="";
		strPatCategoryGroup="0";
		
	}

	public String getStrPatCategoryCode() {
		return strPatCategoryCode;
	}
	public void setStrPatCategoryCode(String strPatCategoryCode) {
		this.strPatCategoryCode = strPatCategoryCode;
	}
	public String getStrPatCategoryName() {
		return strPatCategoryName;
	}
	public void setStrPatCategoryName(String strPatCategoryName) {
		this.strPatCategoryName = strPatCategoryName;
	}
	public String getStrPatCategoryShort() {
		return strPatCategoryShort;
	}
	public void setStrPatCategoryShort(String strPatCategoryShort) {
		this.strPatCategoryShort = strPatCategoryShort;
	}
	public String getStrPatCategoryType() {
		return strPatCategoryType;
	}
	public void setStrPatCategoryType(String strPatCategoryType) {
		this.strPatCategoryType = strPatCategoryType;
	}
	public String getStrIsPaid() {
		return strIsPaid;
	}
	public void setStrIsPaid(String strIsPaid) {
		this.strIsPaid = strIsPaid;
	}
	public String getStrIdSize() {
		return strIdSize;
	}
	public void setStrIdSize(String strIdSize) {
		this.strIdSize = strIdSize;
	}
	public String getStrCatPriority() {
		return strCatPriority;
	}
	public void setStrCatPriority(String strCatPriority) {
		this.strCatPriority = strCatPriority;
	}
	public String getStrIsExpiry() {
		return strIsExpiry;
	}
	public void setStrIsExpiry(String strIsExpiry) {
		this.strIsExpiry = strIsExpiry;
	}
	public String getStrIdValidationType() {
		return strIdValidationType;
	}
	public void setStrIdValidationType(String strIdValidationType) {
		this.strIdValidationType = strIdValidationType;
	}
	public String getStrEconomicStatus() {
		return strEconomicStatus;
	}
	public void setStrEconomicStatus(String strEconomicStatus) {
		this.strEconomicStatus = strEconomicStatus;
	}
	public String getStrExpiryMonth() {
		return strExpiryMonth;
	}
	public void setStrExpiryMonth(String strExpiryMonth) {
		this.strExpiryMonth = strExpiryMonth;
	}
	public String getStrExpiryDays() {
		return strExpiryDays;
	}
	public void setStrExpiryDays(String strExpiryDays) {
		this.strExpiryDays = strExpiryDays;
	}
	public String getStrBenefit() {
		return strBenefit;
	}
	public void setStrBenefit(String strBenefit) {
		this.strBenefit = strBenefit;
	}
	public String getStrCriteria() {
		return strCriteria;
	}
	public void setStrCriteria(String strCriteria) {
		this.strCriteria = strCriteria;
	}
	public String getStrIsIdReq() {
		return strIsIdReq;
	}
	public void setStrIsIdReq(String strIsIdReq) {
		this.strIsIdReq = strIsIdReq;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
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

	public String getStrGlobalPatCategoryName() {
		return strGlobalPatCategoryName;
	}

	public void setStrGlobalPatCategoryName(String strGlobalPatCategoryName) {
		this.strGlobalPatCategoryName = strGlobalPatCategoryName;
	}

	public String getStrPatCategoryGroup() {
		return strPatCategoryGroup;
	}

	public void setStrPatCategoryGroup(String strPatCategoryGroup) {
		this.strPatCategoryGroup = strPatCategoryGroup;
	}

	public String getStrOldPatCategoryName() {
		return strOldPatCategoryName;
	}

	public void setStrOldPatCategoryName(String strOldPatCategoryName) {
		this.strOldPatCategoryName = strOldPatCategoryName;
	}

	public String getStrIdVerificationDoc() {
		return strIdVerificationDoc;
	}

	public void setStrIdVerificationDoc(String strIdVerificationDoc) {
		this.strIdVerificationDoc = strIdVerificationDoc;
	}
	
	public String getStrIsApprovalRequired() {
		return strIsApprovalRequired;
	}
	public void setStrIsApprovalRequired(String strIsApprovalRequired) {
		this.strIsApprovalRequired = strIsApprovalRequired;
	}
	
	/*Start: Surabhi
	 * reason: for adding client combo
	 * date : 29-7-2016*/
	public String getStrPatClient() {
		return strPatClient;
	}

	public void setStrPatClient(String strPatClient) {
		this.strPatClient = strPatClient;
	}
	//End

	public String getStrIsCatSpl() {
		return strIsCatSpl;
	}

	public void setStrIsCatSpl(String strIsCatSpl) {
		this.strIsCatSpl = strIsCatSpl;
	}

	public String getStrBoundDeptCode() {
		return strBoundDeptCode;
	}

	public void setStrBoundDeptCode(String strBoundDeptCode) {
		this.strBoundDeptCode = strBoundDeptCode;
	}

	public String getStrApplicableServices() {
		return strApplicableServices;
	}

	public void setStrApplicableServices(String strApplicableServices) {
		this.strApplicableServices = strApplicableServices;
	}

	public String getStrApplicableServicesCode() {
		return strApplicableServicesCode;
	}

	public void setStrApplicableServicesCode(String strApplicableServicesCode) {
		this.strApplicableServicesCode = strApplicableServicesCode;
	}

	public String getStrIsDefault() {
		return strIsDefault;
	}

	public void setStrIsDefault(String strIsDefault) {
		this.strIsDefault = strIsDefault;
	}

}
