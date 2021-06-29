package vo.registration;

import hisglobal.vo.ValueObject;


public class RenewalConfigVO extends ValueObject
{
	
	private String   strRenewalGroup;
	private String   strHospitalCode;
	private String   strRenewalType;
	private String   strRenewalMode;
	private String   strNoOfDays;
	private String   strIsMultipleMonth;
	private String   strMonth1;
	private String   strMonth2;
	private String   strMonth3;

	
	private String   strSeatID;
	private String   strLastModifiedSeatID;
	
	private String   alreadyRegisteredFlag;
	private String 	 strRenewalPatCat;
	
	public String getStrRenewalGroup() 
	{
		return strRenewalGroup;
	}
	
	public void setStrRenewalGroup(String strRenewalGroup) 
	{
		this.strRenewalGroup = strRenewalGroup;
	}
	
	public String getStrHospitalCode() 
	{
		return strHospitalCode;
	}
	
	public void setStrHospitalCode(String strHospitalCode) 
	{
		this.strHospitalCode = strHospitalCode;
	}
	
	public String getStrRenewalType() 
	{
		return strRenewalType;
	}
	
	public void setStrRenewalType(String strRenewalType) 
	{
		this.strRenewalType = strRenewalType;
	}
	
	public String getStrRenewalMode() 
	{
		return strRenewalMode;
	}
	
	public void setStrRenewalMode(String strRenewalMode) 
	{
		this.strRenewalMode = strRenewalMode;
	}
	
	public String getStrNoOfDays() 
	{
		return strNoOfDays;
	}
	
	public void setStrNoOfDays(String strNoOfDays)
	{
		this.strNoOfDays = strNoOfDays;
	}
	
	public String getStrIsMultipleMonth()
	{
		return strIsMultipleMonth;
	}
	
	public void setStrIsMultipleMonth(String strIsMultipleMonth)
	{
		this.strIsMultipleMonth = strIsMultipleMonth;
	}
	
	public String getStrMonth1() 
	{
		return strMonth1;
	}
	
	public void setStrMonth1(String strMonth1)
	{
		this.strMonth1 = strMonth1;
	}
	
	public String getStrMonth2() 
	{
		return strMonth2;
	}
	
	public void setStrMonth2(String strMonth2) 
	{
		this.strMonth2 = strMonth2;
	}
	
	public String getStrSeatID()
	{
		return strSeatID;
	}
	
	public void setStrSeatID(String strSeatID)
	{
		this.strSeatID = strSeatID;
	}
	
	public String getStrLastModifiedSeatID() 
	{
		return strLastModifiedSeatID;
	}
	
	public void setStrLastModifiedSeatID(String strLastModifiedSeatID)
	{
		this.strLastModifiedSeatID = strLastModifiedSeatID;
	}

	public String getStrMonth3() {
		return strMonth3;
	}

	public void setStrMonth3(String strMonth3) {
		this.strMonth3 = strMonth3;
	}

	public String getAlreadyRegisteredFlag() {
		return alreadyRegisteredFlag;
	}

	public void setAlreadyRegisteredFlag(String alreadyRegisteredFlag) {
		this.alreadyRegisteredFlag = alreadyRegisteredFlag;
	}

	public String getStrRenewalPatCat() {
		return strRenewalPatCat;
	}

	public void setStrRenewalPatCat(String strRenewalPatCat) {
		this.strRenewalPatCat = strRenewalPatCat;
	}

	
	
}
