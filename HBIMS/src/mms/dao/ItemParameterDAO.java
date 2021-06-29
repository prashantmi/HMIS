package mms.dao;

import hisglobal.transactionmgnt.HisDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class StoreTypeDAO.
 */
public class ItemParameterDAO {

	/** The str proc name. */
	//private final String strProcName = "";
	
	/** The str file name. */
	private final String strFileName = "mms.dao.ItemParameterDAO";

	/** The str item param id. */
	private String strItemParamId = "0";
	
	/** The str item param name. */
	private String strItemParamName = "";
	
	/** The str category no. */
	private String strCategoryNo = "";
	
	/** The str param type. */
	private String strParamType = "";
	
	/** The str param length. */
	private String strParamLength = "";
	
	/** The str parent param id. */
	private String strParentParamId = "";

	/** The str effective from. */
	private String strEffectiveFrom = "";

	/** The str remarks. */
	private String strRemarks = "";

	/** The str entry date. */
	private String strEntryDate = "";

	/** The str last modified date. */
	private String strLastModifiedDate = "";

	/** The str seat id. */
	private String strSeatId = "";

	/** The str last modified seat id. */
	private String strLastModifiedSeatId = "";

	/** The str is valid. */
	private String strIsValid = "1";

	/** The str hospital code. */
	private String strHospitalCode = "";
	
	/** The str sl no. */
	private String strSlNo;

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

	/**
	 * Gets the str effective from.
	 * 
	 * @return the str effective from
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	/**
	 * Sets the str effective from.
	 * 
	 * @param strEffectiveFrom the new str effective from
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	/**
	 * Gets the str remarks.
	 * 
	 * @return the str remarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}

	/**
	 * Sets the str remarks.
	 * 
	 * @param strRemarks the new str remarks
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
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
	 * Gets the str last modified date.
	 * 
	 * @return the str last modified date
	 */
	public String getStrLastModifiedDate() {
		return strLastModifiedDate;
	}

	/**
	 * Sets the str last modified date.
	 * 
	 * @param strLastModifiedDate the new str last modified date
	 */
	public void setStrLastModifiedDate(String strLastModifiedDate) {
		this.strLastModifiedDate = strLastModifiedDate;
	}

	/**
	 * Gets the str seat id.
	 * 
	 * @return the str seat id
	 */
	public String getStrSeatId() {
		return strSeatId;
	}

	/**
	 * Sets the str seat id.
	 * 
	 * @param strSeatId the new str seat id
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * Gets the str last modified seat id.
	 * 
	 * @return the str last modified seat id
	 */
	public String getStrLastModifiedSeatId() {
		return strLastModifiedSeatId;
	}

	/**
	 * Sets the str last modified seat id.
	 * 
	 * @param strLastModifiedSeatId the new str last modified seat id
	 */
	public void setStrLastModifiedSeatId(String strLastModifiedSeatId) {
		this.strLastModifiedSeatId = strLastModifiedSeatId;
	}

	/**
	 * Gets the str is valid.
	 * 
	 * @return the str is valid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}

	/**
	 * Sets the str is valid.
	 * 
	 * @param strIsValid the new str is valid
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
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
	 * Sets the str sl no.
	 * 
	 * @param strSlNo the new str sl no
	 */
	public void setStrSlNo(String strSlNo) {
		this.strSlNo = strSlNo;
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
	 * Gets the n row inserted.
	 * 
	 * @return the n row inserted
	 */
	public int getNRowInserted() {
		return nRowInserted;
	}

	/**
	 * Gets the n row updated.
	 * 
	 * @return the n row updated
	 */
	public int getNRowUpdated() {
		return nRowUpdated;
	}

	/**
	 * Gets the n row deleted.
	 * 
	 * @return the n row deleted
	 */
	public int getNRowDeleted() {
		return nRowDeleted;
	}

	/**
	 * Gets the n inserted index.
	 * 
	 * @return the n inserted index
	 */
	public int getNInsertedIndex() {
		return nInsertedIndex;
	}

	/**
	 * Gets the n updated index.
	 * 
	 * @return the n updated index
	 */
	public int getNUpdatedIndex() {
		return nUpdatedIndex;
	}

	/**
	 * Gets the n deleted index.
	 * 
	 * @return the n deleted index
	 */
	public int getNDeletedIndex() {
		return nDeletedIndex;
	}

	/**
	 * Insert.
	 * 
	 * @param dao the dao
	 * 
	 * @throws Exception the exception
	 */
	public void insert(HisDAO dao) throws Exception {

		strErr = "";

		try {

			/*if (strItemParamName.equals("0") || strItemParamName.equals("")) {
				throw new Exception("strItemParamName can not be blank");
			}
			if (strParamType.equals("0") || strParamType.equals("")) {
				throw new Exception("strParamType can not be blank");
			}
			if (strParamLength.equals("0") || strParamLength.equals("")) {
				throw new Exception("strParamLength can not be blank");
			}

			if (strEffectiveFrom.equals("0") || strEffectiveFrom.equals("")) {
				throw new Exception("strEffectiveFrom can not be blank");
			}
			if (strSeatId.equals("0") || strSeatId.equals("")) {
				throw new Exception("strSeatId can not be blank");
			}
			if (strHospitalCode.equals("0") || strHospitalCode.equals("")) {
				throw new Exception("strHospitalCode can not be blank");
			}*/

			int nQueryIndex = 0;

			String strQuery = null;

			strQuery = mms.qryHandler_mms.getQuery(1, "insert.itemparameter.0");
			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, strHospitalCode);
			dao.setQryValue(nQueryIndex, 2, strCategoryNo);
			dao.setQryValue(nQueryIndex, 3, strCategoryNo);
			dao.setQryValue(nQueryIndex, 4, strItemParamName);
			dao.setQryValue(nQueryIndex, 5, strParamType);
			dao.setQryValue(nQueryIndex, 6, strSeatId);
			dao.setQryValue(nQueryIndex, 7, strParamLength);
			dao.setQryValue(nQueryIndex, 8, strRemarks);
			dao.setQryValue(nQueryIndex, 9, strEffectiveFrom);
			dao.setQryValue(nQueryIndex, 10, strHospitalCode);
			// dao.setQryValue(nQueryIndex, 11, strCategroyNo);
			dao.setQryValue(nQueryIndex, 11, strParentParamId);

			dao.execute(nQueryIndex, 0);
			this.nRowInserted++;
		} catch (Exception e) {
			this.strErr = e.getMessage();
			throw new Exception(strFileName + ".insert() --> " + this.strErr);
		} finally {
			this.reset();
		}

	}

	/**
	 * Update.
	 * 
	 * @param dao the dao
	 * 
	 * @throws Exception the exception
	 */
	public void update(HisDAO dao) throws Exception {

		strErr = "";

		try {

			/*if (strItemParamName.equals("0") || strItemParamName.equals("")) {
				throw new Exception("strItemParamName can not be blank");
			}
			if (strParamType.equals("0") || strParamType.equals("")) {
				throw new Exception("strParamType can not be blank");
			}
			if (strParamLength.equals("0") || strParamLength.equals("")) {
				throw new Exception("strParamLength can not be blank");
			}
			if (strEffectiveFrom.equals("0") || strEffectiveFrom.equals("")) {
				throw new Exception("strEffectiveFrom can not be blank");
			}

			if (strHospitalCode.equals("0") || strHospitalCode.equals("")) {
				throw new Exception("strHospitalCode can not be blank");
			}*/
			int nQueryIndex = 0;

			String strQuery = null;

			strQuery = mms.qryHandler_mms.getQuery(1, "update.itemparameter.0");
			nQueryIndex = dao.setQuery(strQuery);

			
			dao.setQryValue(nQueryIndex, 1, strLastModifiedSeatId);
			dao.setQryValue(nQueryIndex, 2, strItemParamId);
			dao.setQryValue(nQueryIndex, 3, strHospitalCode);
			dao.setQryValue(nQueryIndex, 4, strSlNo);
			dao.execute(nQueryIndex, 0);
			
			strQuery = mms.qryHandler_mms.getQuery(1, "update.itemparameter.1");
			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, strItemParamId);
			dao.setQryValue(nQueryIndex, 2, strCategoryNo);
			dao.setQryValue(nQueryIndex, 3, strItemParamName);
			dao.setQryValue(nQueryIndex, 4, strParamType);
			dao.setQryValue(nQueryIndex, 5, strSeatId);
			dao.setQryValue(nQueryIndex, 6, strParamLength);
			dao.setQryValue(nQueryIndex, 7, strRemarks);
			dao.setQryValue(nQueryIndex, 8, strEffectiveFrom);
			dao.setQryValue(nQueryIndex, 9, strHospitalCode);
			dao.setQryValue(nQueryIndex, 10, strParentParamId);
			dao.setQryValue(nQueryIndex, 11, strIsValid);
			dao.setQryValue(nQueryIndex, 12, strHospitalCode);
			dao.setQryValue(nQueryIndex, 13, strItemParamId);
			
			dao.execute(nQueryIndex, 0);

			this.nRowUpdated++;
		} catch (Exception e) {
			this.strErr = e.getMessage();
			throw new Exception(strFileName + ".update() --> " + this.strErr);
		} finally {
			this.reset();
		}

	}

	/**
	 * Reset.
	 */
	public void reset() {

		strItemParamId = "0";
		strItemParamName = "";
		strCategoryNo = "";
		// strCategroyNo = "";
		strParamType = "";
		strParamLength = "";
		strParentParamId = "";
		strEffectiveFrom = "";
		strRemarks = "";
		strEntryDate = "";
		strLastModifiedDate = "";
		strSeatId = "";
		strLastModifiedSeatId = "";
		strIsValid = "";
		strHospitalCode = "";

	}

	/**
	 * Gets the str item param id.
	 * 
	 * @return the str item param id
	 */
	public String getStrItemParamId() {
		return strItemParamId;
	}

	/**
	 * Sets the str item param id.
	 * 
	 * @param strItemParamId the new str item param id
	 */
	public void setStrItemParamId(String strItemParamId) {
		this.strItemParamId = strItemParamId;
	}

	/**
	 * Gets the str item param name.
	 * 
	 * @return the str item param name
	 */
	public String getStrItemParamName() {
		return strItemParamName;
	}

	/**
	 * Sets the str item param name.
	 * 
	 * @param strItemParamName the new str item param name
	 */
	public void setStrItemParamName(String strItemParamName) {
		this.strItemParamName = strItemParamName;
	}

	/**
	 * Gets the str param type.
	 * 
	 * @return the str param type
	 */
	public String getStrParamType() {
		return strParamType;
	}

	/**
	 * Sets the str category no.
	 * 
	 * @param strCategoryNo the strCategoryNo to set
	 */
	public void setStrCategoryNo(String strCategoryNo) {
		this.strCategoryNo = strCategoryNo;
	}

	/**
	 * Sets the str param type.
	 * 
	 * @param strParamType the new str param type
	 */
	public void setStrParamType(String strParamType) {
		this.strParamType = strParamType;
	}

	/**
	 * Gets the str param length.
	 * 
	 * @return the str param length
	 */
	public String getStrParamLength() {
		return strParamLength;
	}

	/**
	 * Sets the str param length.
	 * 
	 * @param strParamLength the new str param length
	 */
	public void setStrParamLength(String strParamLength) {
		this.strParamLength = strParamLength;
	}

	/**
	 * Gets the str parent param id.
	 * 
	 * @return the str parent param id
	 */
	public String getStrParentParamId() {
		return strParentParamId;
	}

	/**
	 * Sets the str parent param id.
	 * 
	 * @param strParentParamId the new str parent param id
	 */
	public void setStrParentParamId(String strParentParamId) {
		this.strParentParamId = strParentParamId;
	}

	/**
	 * Sets the str err.
	 * 
	 * @param strErr the new str err
	 */
	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}

	

}
