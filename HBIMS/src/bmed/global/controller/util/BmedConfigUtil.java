package bmed.global.controller.util;

import bmed.masters.dao.BmedConfigMstDAO;



// TODO: Auto-generated Javadoc
/**
 * The Class BmedConfigUtil.
 */
public class BmedConfigUtil 
{
	 /**
	  * Mode Details:: SEMT_PROPERTY_CONFIG_MST
	  * 10:Show Mc Details Upto
	  * 11:Show Warranty Details Upto
	  * 12:FINANCIAL START YEAR
	  * 13:FINANCIAL END YEAR
	  * 14:HEM AMC COMPLAINTS
	  * 15:FTP File Save Folder
	  */ 
	 
    /** The str financial start date. */
	private String strFinancialStartDate = "";
	
	/** The str financial end date. */
	private String strFinancialEndDate = "";
	
	/** The str financial start date. */
	private String strMcDetailsUpTo = "";
	
	/** The str financial end date. */
	private String strWarrantyDetailsUpTo = "";
	
	/** The str financial end date. */
	private String strAmcComplaint = "";
		
	/*** committe File Path */
	private String strFtpFileFolder= "";
	
	
	/** The Constant MODULE_ID. */
	public static final String MODULE_ID = "39";
	
	/** The Constant UNIT_ID. */
	public static final String UNIT_ID = "390001";
	
	/** The Constant DEFAULT_CURRENCY_CODE. */
	public static final String DEFAULT_CURRENCY_CODE = "100"; // Default
	// Currency Code
	// INR.
	
	/**
	 * 
	 * Gets the FTP File Save Folder.
	 * 
	 * @param strMode the Mode
	 * @param strHospitalCode the str hospital code
	 * 
	 * @return the str financial start date
	 */
	public String getStrFtpFileFolder(String strMode , String strHospitalCode) {

		strFtpFileFolder = BmedConfigMstDAO.getDateDetails(strMode, strHospitalCode);

		return strFtpFileFolder;
	}
	
	/**
	 * 
	 * Gets the AMC Complaint.
	 * 
	 * @param strStoreId the str store id
	 * @param strHospitalCode the str hospital code
	 * 
	 * @return the str financial start date
	 */
	public String getStrAmcComplaint(String strMode , String strHospitalCode) {

		strAmcComplaint = BmedConfigMstDAO.getDateDetails(strMode, strHospitalCode);

		return strAmcComplaint;
	}
	
	
	/**
	 * 
	 * Gets the Warranty Details UpTo.
	 * 
	 * @param strStoreId the str store id
	 * @param strHospitalCode the str hospital code
	 * 
	 * @return the str financial start date
	 */
	public String getStrWarrantyDetailsUpTo(String strMode , String strHospitalCode) {

		strWarrantyDetailsUpTo = BmedConfigMstDAO.getDateDetails(strMode, strHospitalCode);

		return strWarrantyDetailsUpTo;
	}
	
	
	/**
	 * 
	 * Gets the MC Details UpTo.
	 * 
	 * @param strStoreId the str store id
	 * @param strHospitalCode the str hospital code
	 * 
	 * @return the str financial start date
	 */
	public String getStrMcDetailsUpTo(String strMode , String strHospitalCode) {

		strMcDetailsUpTo = BmedConfigMstDAO.getDateDetails(strMode, strHospitalCode);

		return strMcDetailsUpTo;
	}
	
	
	
	/**
	 * 
	 * Gets the str financial start date.
	 * 
	 * @param strStoreId the str store id
	 * @param strHospitalCode the str hospital code
	 * 
	 * @return the str financial start date
	 */
	public String getStrFinancialStartDate(String strMode , String strHospitalCode) {

		strFinancialStartDate = BmedConfigMstDAO.getDateDetails(strMode, strHospitalCode);

		return strFinancialStartDate;
	}

	/**
	 * Gets the str financial end date.
	 * 
	 * @param strStoreId the str store id
	 * @param strHospitalCode the str hospital code
	 * 
	 * @return the str financial end date
	 */
	public String getStrFinancialEndDate(String strMode , String strHospitalCode) {

		strFinancialEndDate = BmedConfigMstDAO.getDateDetails(strMode, strHospitalCode);

		return strFinancialEndDate;
	}

	 

	

	
}
