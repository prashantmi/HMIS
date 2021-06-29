package ipd.masters.dao;

import ipd.masters.vo.GlobalRoomTypeMstVO;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class GlobalRoomTypeMstDAO {
	private String qryStr = "";

	private String strmoduleName = "";

	private String strfileName = "";

	private int nqryIndex = 0;

	// error message
	private String strErrMsg = "";
/**
 * To insert data
 * @param globalRoomTypeMstVO_p
 * @return
 */
	public static boolean insertQuery(GlobalRoomTypeMstVO globalRoomTypeMstVO_p) {

		HisDAO hisDaoObj = null;
		int qryIndex;
		boolean retvalue = true;
		String query = new String();
		hisDaoObj = new HisDAO("ipd", "DAORoomConfigMst");

		query = ipd.qryHandler_ipd.getQuery(1, "insert.globalRoomtype.0");
		// System.out.println("Query in Insert Query :=>"+query);
		qryIndex = hisDaoObj.setQuery(query);

		try {
			hisDaoObj.setQryValue(qryIndex, 1, globalRoomTypeMstVO_p.getStrHospitalCode());
			hisDaoObj.setQryValue(qryIndex, 2, globalRoomTypeMstVO_p.getStrGlobalRoomType());
			hisDaoObj.setQryValue(qryIndex, 3, globalRoomTypeMstVO_p.getStrEffectiveDate());
			hisDaoObj.setQryValue(qryIndex, 4, globalRoomTypeMstVO_p.getStrSeatId());
			hisDaoObj.setQryValue(qryIndex, 5, globalRoomTypeMstVO_p.getstrRemarks());
			hisDaoObj.setQryValue(qryIndex, 6, globalRoomTypeMstVO_p.getStrSeatId());
			hisDaoObj.setQryValue(qryIndex, 7, "1");
			hisDaoObj.setQryValue(qryIndex, 8, globalRoomTypeMstVO_p.getStrHospitalCode());
		
			
			hisDaoObj.execute(qryIndex, 0);

			synchronized (hisDaoObj) {
				hisDaoObj.fire();
				retvalue = true;
			}
		} catch (Exception e) {
			retvalue = false;
			new HisException("IPD", "GlobalRoomTypeMstDAO", e.getMessage());
			//e.printStackTrace();
		} finally {
			hisDaoObj.free();
			hisDaoObj = null;
		}
		return retvalue;

	}
/**
 * Check duplicate  data before inserting any new record
 * @param globalRoomTypeMstVO_p
 * @return
 */
	public static boolean initialAddQuery(GlobalRoomTypeMstVO globalRoomTypeMstVO_p) {
		HisDAO hisDaoObj = new HisDAO("ipd", "GlobalRoomTypeMstDAO");
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		 //System.out.println("In initialAddQuery globalRoomTypeMstVO_p.getStrHospitalCode()=="+globalRoomTypeMstVO_p.getStrHospitalCode());

		String query = ipd.qryHandler_ipd.getQuery(1, "select.globalRoomtype.2");
      
		try {
			qryIndex = hisDaoObj.setQuery(query);
			hisDaoObj.setQryValue(qryIndex, 1, globalRoomTypeMstVO_p.getStrGlobalRoomType());
			hisDaoObj.setQryValue(qryIndex, 2, globalRoomTypeMstVO_p.getStrHospitalCode());
			
			wb = hisDaoObj.executeQry(qryIndex);
			while (wb.next()) {
				count = Integer.parseInt(wb.getString(1));
			}
			if (count == 0) {
				returnValue = true;
			} else {
				returnValue = false;
			}
		} catch (Exception e) {
			returnValue = false;
			new HisException("ipd", "GlobalRoomTypeMstDAO.initialAddQuery()", e
					.getMessage());
			e.printStackTrace();
		} finally {
			hisDaoObj.free();
			hisDaoObj = null;
		}
		// System.out.println("returnValue in add"+returnValue);
		return returnValue;
	}
/**
 * is used to bring data on modify page
 * @param strChk1_p
 * @param globalRoomTypeMstVO_p
 * @throws HisException
 */
	public static void modifyQuery(String strChk1_p, GlobalRoomTypeMstVO globalRoomTypeMstVO_p)
			throws HisException {
	
		HisDAO hisDaoObj = null;
	
		int nqryIndex;
		String query = new String();
		//System.out.println("strChk1_p" + strChk1_p);
	
		
		try {
			String strtemp[] = null;
			String strtemp1[] =null;
			
			hisDaoObj = new HisDAO("ipd", "GlobalRoomTypeMstDAO");
			query = ipd.qryHandler_ipd.getQuery(1, "select.globalRoomtype.3");
			strtemp = strChk1_p.replace('$', '#').split("#");
			nqryIndex = hisDaoObj.setQuery(query);
	    	//hisDaoObj.setQryValue(qryIndex, 1, temp[0]);
			strtemp1 = strtemp[0].replace('@', '#').split("#");
			
			hisDaoObj.setQryValue(nqryIndex, 1, strtemp1[0]);
			hisDaoObj.setQryValue(nqryIndex, 2, strtemp1[1]);
			hisDaoObj.setQryValue(nqryIndex, 3, strtemp1[2].replace("|", "@").split("@")[0]);
			
	    	WebRowSet web = hisDaoObj.executeQry(nqryIndex);
			while (web.next()) {
				globalRoomTypeMstVO_p.setStrGlobalRoomType(web.getString(1));
				globalRoomTypeMstVO_p.setStrEffectiveDate(web.getString(2));
				if(web.getString(3).equals("null"))
				{
					globalRoomTypeMstVO_p.setstrRemarks("");
				}
				else
					globalRoomTypeMstVO_p.setstrRemarks(web.getString(3));
				globalRoomTypeMstVO_p.setStrIsValid(web.getString(4));
			}
		} catch (Exception e) {

			new HisException("ipd", "GlobalRoomTypeMstDAO", e.getMessage());
			//e.printStackTrace();
		} finally {
			hisDaoObj.free();
			hisDaoObj = null;
		}

	}
/**
 * is used to update record
 * @param strChk_p
 * @param globalRoomTypeMstVO_p
 * @return
 * @throws HisException
 */
	public static boolean updateQuery(String strChk_p, GlobalRoomTypeMstVO globalRoomTypeMstVO_p)
			throws HisException {
		boolean retvalue = true;
		HisDAO hisDaoObj = null;
		String strtemp[] = null;
		String strTemp1[] = null;
		int qryIndex;
		String query = new String();
		try {
			hisDaoObj = new HisDAO("Billing", "DAOGroupMst");
			query = ipd.qryHandler_ipd.getQuery(1, "update.globalRoomtype.1");
			qryIndex = hisDaoObj.setQuery(query);

			strtemp = strChk_p.replace('$', '#').split("#");
			strTemp1 = strtemp[0].replace('@', '#').split("#");
			
			hisDaoObj.setQryValue(qryIndex, 1, globalRoomTypeMstVO_p.getStrGlobalRoomType());
			hisDaoObj.setQryValue(qryIndex, 2, globalRoomTypeMstVO_p.getStrEffectiveDate());
			hisDaoObj.setQryValue(qryIndex, 3, globalRoomTypeMstVO_p.getStrSeatId());
			hisDaoObj.setQryValue(qryIndex, 4, globalRoomTypeMstVO_p.getstrRemarks());
			hisDaoObj.setQryValue(qryIndex, 5, globalRoomTypeMstVO_p.getStrIsValid());
			hisDaoObj.setQryValue(qryIndex, 6, strTemp1[0]);
			hisDaoObj.setQryValue(qryIndex, 7, globalRoomTypeMstVO_p.getStrHospitalCode());
			hisDaoObj.setQryValue(qryIndex, 8, strTemp1[2].replace("|", "@").split("@")[0]);
			hisDaoObj.execute(0, qryIndex);

			synchronized (hisDaoObj) {
				hisDaoObj.fire();
			}
		} catch (Exception e) {
			retvalue = false;
			new HisException("ipd", "GlobalRoomTypeMstDAO", e.getMessage());
			//e.printStackTrace();
		} finally {
			hisDaoObj.free();
			hisDaoObj = null;
		}
		// System.out.println("Successfully Executed=>"+retvalue);
		return retvalue;
	}
/**
 * Check duplicate data before update
 * @param strChk_p
 * @param globalRoomTypeMstVO_p
 * @return
 */
	public static boolean initialUpdateQuery(String strChk_p, GlobalRoomTypeMstVO globalRoomTypeMstVO_p) {
		HisDAO hisDaoObj = new HisDAO("ipd", "GlobalRoomTypeMstDAO");
		String strtemp[] = null;
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		String strTemp1[] =null;
		WebRowSet wb = null;
		String query = ipd.qryHandler_ipd.getQuery(1, "select.globalRoomtype.4");

		// System.out.println("In(DAO)intialupdate query => "+ query);
		try {
			strtemp = strChk_p.replace('$', '#').split("#");
			strTemp1 = strtemp[0].replace('@', '#').split("#");
			 
		
			
			qryIndex = hisDaoObj.setQuery(query);
			hisDaoObj.setQryValue(qryIndex, 1, strTemp1[0]);
			hisDaoObj.setQryValue(qryIndex, 2, globalRoomTypeMstVO_p.getStrGlobalRoomType());
			hisDaoObj.setQryValue(qryIndex, 3,strTemp1[1]);
			hisDaoObj.setQryValue(qryIndex, 4,strTemp1[2].replace("|", "@").split("@")[0]);
			wb = hisDaoObj.executeQry(qryIndex);
			while (wb.next()) {
				count = Integer.parseInt(wb.getString(1));
		
			}
			if (count < 1) {
				returnValue = true;
			} else {
				returnValue = false;
			}

		} catch (Exception e) {
			returnValue = false;
			new HisException("ipd", "GlobalRoomTypeMstDAO.initialAddQuery()", e
					.getMessage());
			e.printStackTrace();
		} finally {
			hisDaoObj.free();
			hisDaoObj = null;
		}
		
		return returnValue;
	}

	/**
	 * This function used to get the Room Type from  sipt_wardroom_type_mst table. It
	 * has the following parameters roomType --> Room Type Id [etc]. If user
	 * does not want to check it then pass -1 Columns in query >>
	 * GNUM_ROOM_TYPE_CODE,GSTR_ROOM_DESC Order By Field >> GSTR_ROOM_DESC
	 * @param roomCode
	 * @param orderBy
	 * @param hCode
	 * @return
	 * @throws Exception
	 */
	/*
	 * 
	 */

	public WebRowSet getRoomTypeDtl(int strRoomCode_p, boolean strOrderBy_p,String strhospitalCode_p)
			throws Exception {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String tempStr = "";
		
		this.strErrMsg = "";

		this.qryStr = ipd.qryHandler_ipd.getQuery(1, "select.globalBservice.0");
		
		tempStr =  ipd.qryHandler_ipd.getQuery(1, "select.globalBservice.cond.1").replace("?", ""+strhospitalCode_p+"");
		this.qryStr += " WHERE " + tempStr;
		
		if (strRoomCode_p != -1) {
			// room type condition
			tempStr = ipd.qryHandler_ipd.getQuery(1, "select.globalBservice.cond.0");
			this.qryStr += " where " + tempStr;
			
		}
		// CHECK ORDER BY
		if (strOrderBy_p) {
			tempStr = ipd.qryHandler_ipd.getQuery(1, "select.globalBservice.cond.2");
			this.qryStr += " ORDER BY " + tempStr;
		}

		try {
			if (!this.qryStr.equals("")) {
				daoObj = new HisDAO(this.strmoduleName, "GlobalRoomType"
						+ this.strfileName);
				nqryIndex = daoObj.setQuery(this.qryStr);
				if (strRoomCode_p != -1)
					daoObj.setQryValue(nqryIndex, 1, String.valueOf(strRoomCode_p));
				// fire the query
			
				ws = daoObj.executeQry(nqryIndex);
			} else {
				this.strErrMsg = "RoomType.getRoomType() -->Query is blank";
				throw new Exception(this.strErrMsg);
			}
		} catch (Exception e) {
			this.strErrMsg = "RoomType.getRoomType() -->" + e.getMessage();
			//e.printStackTrace();
			throw new Exception(this.strErrMsg);
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

		return ws;
	}
  
}