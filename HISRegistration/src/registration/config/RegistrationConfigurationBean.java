package registration.config;
/**
 * Modified On 20.Mar.18
 * For CDAC Noida
 * Created to serve new configuration needs arises due to sdc hdc model*/
import hisglobal.vo.ValueObject;

public class RegistrationConfigurationBean extends ValueObject
{
	private String strMlcMode;
	private String alreadyRegisteredFlag;
	private String isCrossConsulation;
	private String isAptBased;
	private String isHourBased;
	private String hours;
	private String isAadhaarSearchable;
	private String seniorCitizenAgeLimit;
	private String seniorCitizenCatCode;
	private String isMobileSearch;
	private String isImageStoredFTP;
	private String isBarcodeSlipPrint;
	private String isQRCodePrint;
	
	public String getStrMlcMode() {
		return strMlcMode;
	}

	public void setStrMlcMode(String strMlcMode) {
		this.strMlcMode = strMlcMode;
	}

	public String getAlreadyRegisteredFlag() {
		return alreadyRegisteredFlag;
	}

	public void setAlreadyRegisteredFlag(String alreadyRegisteredFlag) {
		this.alreadyRegisteredFlag = alreadyRegisteredFlag;
	}

	public String getIsCrossConsulation() {
		return isCrossConsulation;
	}

	public void setIsCrossConsulation(String isCrossConsulation) {
		this.isCrossConsulation = isCrossConsulation;
	}

	public String getIsAptBased() {
		return isAptBased;
	}

	public void setIsAptBased(String isAptBased) {
		this.isAptBased = isAptBased;
	}
	public String getIsHourBased() {
		return isHourBased;
	}

	public void setIsHourBased(String isHourBased) {
		this.isHourBased = isHourBased;
	}
	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getIsAadhaarSearchable() {
		return isAadhaarSearchable;
	}

	public void setIsAadhaarSearchable(String isAadhaarSearchable) {
		this.isAadhaarSearchable = isAadhaarSearchable;
	}
	
	public String getSeniorCitizenAgeLimit() {
		return seniorCitizenAgeLimit;
	}

	public void setSeniorCitizenAgeLimit(String seniorCitizenAgeLimit) {
		this.seniorCitizenAgeLimit = seniorCitizenAgeLimit;
	}
	
	public String getSeniorCitizenCatCode() {
		return seniorCitizenCatCode;
	}

	public void setSeniorCitizenCatCode(String seniorCitizenCatCode) {
		this.seniorCitizenCatCode = seniorCitizenCatCode;
	}
	public String getisMobileSearch() {
		return isMobileSearch;
	}

	public void setisMobileSearch(String isMobileSearch) {
		this.isMobileSearch = isMobileSearch;
	}
	
	public String getIsImageStoredFTP() {
		return isImageStoredFTP;
	}

	public void setIsImageStoredFTP(String isImageStoredFTP) {
		this.isImageStoredFTP = isImageStoredFTP;
	}
	public String getIsBarcodeSlipPrint() {
		return isBarcodeSlipPrint;
	}

	public void setIsBarcodeSlipPrint(String isBarcodeSlipPrint) {
		this.isBarcodeSlipPrint = isBarcodeSlipPrint;
	}

	public String getIsQRCodePrint() {
		return isQRCodePrint;
	}

	public void setIsQRCodePrint(String isQRCodePrint) {
		this.isQRCodePrint = isQRCodePrint;
	}
	
}//End Of Class
