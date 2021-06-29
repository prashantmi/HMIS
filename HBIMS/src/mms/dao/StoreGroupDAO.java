package mms.dao;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class StoreTypeDAO.
 */

/**
 * Developer : Anshul Jindal Version : 1.0 Date : 30/Dec/2008
 * 
 * This class will be used to insert/update/delete the records Table Name :
 * HSTT_GROUP_MST
 */
public class StoreGroupDAO {

	/** The str proc name. */
	//private final String strProcName = "";
	
	/** The str file name. */
	private final String strFileName = "mms.dao.StoreGroupDAO";

	/** The str hospital code. */
	private String strHospitalCode = "";
	
	/** The str group id. */
	private String strGroupId = "";
	
	/** The str group name. */
	private String strGroupName = "";
	
	/** The str item cat id. */
	private String strItemCatId = "";
	
	/** The str remarks. */
	private String strRemarks = "";
	
	/** The str effective from. */
	private String strEffectiveFrom = "";
	
	/** The str last mode seat id. */
	//private String strLastModeSeatId = "";
	
	/** The str last mode date. */
	//private String strLastModeDate = "";
	
	/** The str entry date. */
	//private String strEntryDate = "";
	
	/** The str seat id. */
	private String strSeatId = "";
	
	/** The str is valid. */
	private String strIsValid = "1";

	/** The str err. */
	private String strErr = "";

	/** The n row inserted. */
	private int nRowInserted = 0;
	
	/** The n row updated. */
	private int nRowUpdated = 0;
	
	/** The n row deleted. */
	private int nRowDeleted = 0;

	/** The n inserted index. */
	private int nInsertedIndex = 0;
	
	/** The n updated index. */
	private int nUpdatedIndex = 0;
	
	/** The n deleted index. */
	private int nDeletedIndex = 0;

	public String strSlNo;
	
	public String getStrSlNo() {
		return strSlNo;
	}

	public void setStrSlNo(String strSlNo) {
		this.strSlNo = strSlNo;
	}

	/**
	 * Gets the str err.
	 * 
	 * @return the strErr
	 */
	public String getStrErr() {
		return strErr;
	}

	/**
	 * Gets the n row inserted.
	 * 
	 * @return the nRowInserted
	 */
	public int getNRowInserted() {
		return nRowInserted;
	}

	/**
	 * Gets the n row updated.
	 * 
	 * @return the nRowUpdated
	 */
	public int getNRowUpdated() {
		return nRowUpdated;
	}

	/**
	 * Gets the n row deleted.
	 * 
	 * @return the nRowDeleted
	 */
	public int getNRowDeleted() {
		return nRowDeleted;
	}

	/**
	 * Gets the n inserted index.
	 * 
	 * @return the nInsertedIndex
	 */
	public int getNInsertedIndex() {
		return nInsertedIndex;
	}

	/**
	 * Gets the n updated index.
	 * 
	 * @return the nUpdatedIndex
	 */
	public int getNUpdatedIndex() {
		return nUpdatedIndex;
	}

	/**
	 * Gets the n deleted index.
	 * 
	 * @return the nDeletedIndex
	 */
	public int getNDeletedIndex() {
		return nDeletedIndex;
	}

	/**
	 * Sets the str hospital code.
	 * 
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * Sets the str group id.
	 * 
	 * @param strGroupId the strGroupId to set
	 */
	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}

	/**
	 * Sets the str group name.
	 * 
	 * @param strGroupName the strGroupName to set
	 */
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}

	/**
	 * Sets the str remarks.
	 * 
	 * @param strRemarks the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	/**
	 * Sets the str effective from.
	 * 
	 * @param strEffectiveFrom the strEffectiveFrom to set
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

 

	/**
	 * Sets the str seat id.
	 * 
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * Sets the str is valid.
	 * 
	 * @param strIsValid the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	// Methods starts from here

	/**
	 * This method will be used to insert the records.
	 * 
	 * @param dao :
	 * HisDAO Object
	 * 
	 * @throws Exception -
	 * when strGroupName or strHospitalCode or strStoreTypeId is
	 * blank
	 */
	public void insert(HisDAO dao) throws Exception {

		strErr = "";

		try {

			// check mandatory information
			if (strHospitalCode.equals("0") || strHospitalCode.equals("")) {
				throw new Exception("strHospitalCode can not be blank");
			}
			if (strGroupName.equals("0") || strGroupName.equals("")) {
				throw new Exception("strGroupName can not be blank");
			}
			if (strItemCatId.equals("0") || strItemCatId.equals("")) {
				throw new Exception("strItemCatId can not be blank");
			}

			int nQueryIndex = 0;

			String strQuery = null;

			strQuery = mms.qryHandler_mms.getQuery(1, "insert.storegroup.0");
			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex, 2, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex, 3, strItemCatId);
			dao.setQryValue(nQueryIndex, 4, strGroupName);
			dao.setQryValue(nQueryIndex, 5, strItemCatId);
			dao.setQryValue(nQueryIndex, 6, strRemarks);
			dao.setQryValue(nQueryIndex, 7, strEffectiveFrom);
			dao.setQryValue(nQueryIndex, 8, strSeatId);
			dao.setQryValue(nQueryIndex, 9, strIsValid);

			dao.execute(nQueryIndex, 0);
			this.nRowInserted++;
		} catch (Exception e) {

			this.strErr = e.getMessage();
			throw new Exception(strFileName + ".insert() --> " + this.strErr);
		} finally {
			this.reset(); // to reset the variables
		}

	}

	/**
	 * This method will be used to update the records.
	 * 
	 * @param dao :
	 * HisDAO Object
	 * 
	 * @throws Exception -
	 * when strGroupName or strHospitalCode is blank
	 */
	public void update(HisDAO dao) throws Exception {
		strErr = "";

		try {

			// check mandatory information
			if (strHospitalCode.equals("0") || strHospitalCode.equals("")) {
				throw new Exception("strHospitalCode can not be blank");
			}
			if (strGroupName.equals("0") || strGroupName.equals("")) {
				throw new Exception("strGroupName can not be blank");
			}

			int nQueryIndex = 0;

			String strQuery = null;

			strQuery = mms.qryHandler_mms.getQuery(1, "update.storeGroup.1");
			nQueryIndex = dao.setQuery(strQuery);

			
			dao.setQryValue(nQueryIndex, 1, strSeatId);			
			dao.setQryValue(nQueryIndex, 2, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex, 3, strGroupId);
			dao.setQryValue(nQueryIndex, 4, strSlNo);

			dao.execute(nQueryIndex, 0);
			
			
			
			strQuery = mms.qryHandler_mms.getQuery(1, "insert.storegroup.new.record");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex, 2, strGroupId);			
			dao.setQryValue(nQueryIndex, 3, strGroupName);
			dao.setQryValue(nQueryIndex, 4, strItemCatId);
			dao.setQryValue(nQueryIndex, 5, strRemarks);
			dao.setQryValue(nQueryIndex, 6, strEffectiveFrom);
			dao.setQryValue(nQueryIndex, 7, strSeatId);
			dao.setQryValue(nQueryIndex, 8, strIsValid);
			dao.setQryValue(nQueryIndex, 9, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex, 10, strGroupId);
			
			dao.execute(nQueryIndex, 0);
			
			this.nRowInserted++;
		} catch (Exception e) {

			this.strErr = e.getMessage();
			throw new Exception(strFileName + ".update() --> " + this.strErr);
		} finally {
			this.reset(); // to reset the variables
		}

	}

	/**
	 * Reset.
	 */
	public void reset() {

		strHospitalCode = "";
		strGroupId = "";
		strGroupName = "";
		strItemCatId = "";
		strRemarks = "";
		strEffectiveFrom = "";
	/*	strLastModeSeatId = "";
		strLastModeDate = "";
		strEntryDate = "";*/
		strSeatId = "";
		strIsValid = "1";

	}

	/**
	 * Sets the str item cat id.
	 * 
	 * @param strItemCatId the new str item cat id
	 */
	public void setStrItemCatId(String strItemCatId) {
		this.strItemCatId = strItemCatId;
	}

}
