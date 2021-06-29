package ipd.masters.dao;

import ipd.IpdConfigUtil;
import ipd.masters.vo.BedTypeMstVO;
import ipd.masters.vo.RoomTypeMstVO;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class RoomTypeMstDAO {
	private String qryStr = "";

	private String strmoduleName = "";

	private String strfileName = "";

	private int nqryIndex = 0;

	// error message
	private String strErrMsg = "";
	
	
	
	/* To Get Global Room Type for Main Page.
	 * 
	 * @param vo the vo
	 */
	public static void getGlobalRoomType(RoomTypeMstVO vo) 
	throws Exception {

		HisDAO dao = null;
		int nqryIndex=0;
		String strquery = "";
		WebRowSet wb = null;
		
		try {
			dao = new HisDAO("ipd", "RoomTypeMstDAO");
			
			//System.out.println("hmode is "+vo.getHmode());

			if(vo.getHmode().equals("ADD")){
				strquery = ipd.qryHandler_ipd.getQuery(1,"select.roomtype.globalRoomType.0");
				nqryIndex = dao.setQuery(strquery);
				dao.setQryValue(nqryIndex, 1, IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
				dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			}else if(vo.getHmode().equals("MODIFY")){
				strquery = ipd.qryHandler_ipd.getQuery(1,"select.roomtype.globalRoomType.1");
				nqryIndex = dao.setQuery(strquery);
				dao.setQryValue(nqryIndex, 1, IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
				dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
				dao.setQryValue(nqryIndex, 3, vo.getStrGlobalRoomType());
			}
			else if(vo.getHmode().equals("SAVE")){
				strquery = ipd.qryHandler_ipd.getQuery(1,"select.roomtype.globalRoomType.0");
				nqryIndex = dao.setQuery(strquery);
				dao.setQryValue(nqryIndex, 1, IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
				dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			}
			
			
			wb = dao.executeQry(nqryIndex);
			if(wb !=null && wb.size()>0){
				vo.setWrsGlobalRoomType(wb);
			} 
				
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ipd.RoomTypeMstDAO.getGlobalRoomType --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		
	}
	
	
/**
 * To insert data
 * @param vo
 * @return
 */
	public static boolean insertQuery(RoomTypeMstVO vo) {

		HisDAO dao = null;
		int qryIndex;
		boolean retvalue = true;
		String query = new String();
		dao = new HisDAO("ipd", "DAORoomConfigMst");

		query = ipd.qryHandler_ipd.getQuery(1, "insert.roomtype.0");
		// System.out.println("Query in Insert Query :=>"+query);
		qryIndex = dao.setQuery(query);

		try {
			dao.setQryValue(qryIndex, 1, vo.getStrGlobalRoomType());
			dao.setQryValue(qryIndex, 2, vo.getStrRoomType());
			dao.setQryValue(qryIndex, 3, vo.getStrEffectiveDate());
			dao.setQryValue(qryIndex, 4, vo.getStrSeatId());
			dao.setQryValue(qryIndex, 5, vo.getstrRemarks());
			dao.setQryValue(qryIndex, 6, vo.getStrSeatId());
			dao.setQryValue(qryIndex, 7, "1");
			dao.setQryValue(qryIndex, 8, vo.getStrHospitalCode());
			dao.setQryValue(qryIndex, 9, vo.getStrHospitalCode());
		
			
			dao.execute(qryIndex, 0);

			synchronized (dao) {
				dao.fire();
				retvalue = true;
			}
		} catch (Exception e) {
			retvalue = false;
			new HisException("IPD", "DAORoomTypeMst", e.getMessage());
			//e.printStackTrace();
		} finally {
			dao.free();
			dao = null;
		}
		return retvalue;

	}
/**
 * Check duplicate  data before inserting any new record
 * @param vo
 * @return
 */
	public static boolean initialAddQuery(RoomTypeMstVO vo) {
		HisDAO dao = new HisDAO("ipd", "DAORoomTypeMst");
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		 //System.out.println("In initialAddQuery vo.getStrHospitalCode()=="+vo.getStrHospitalCode());

		String query = ipd.qryHandler_ipd.getQuery(1, "select.roomtype.2");
      
		try {
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, vo.getStrRoomType());
			dao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
			
			wb = dao.executeQry(qryIndex);
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
			new HisException("ipd", "DAORoomTypeMst.initialAddQuery()", e
					.getMessage());
			e.printStackTrace();
		} finally {
			dao.free();
			dao = null;
		}
		// System.out.println("returnValue in add"+returnValue);
		return returnValue;
	}
/**
 * is used to bring data on modify page
 * @param chk1
 * @param vo
 * @throws HisException
 */
	public static void modifyQuery(String chk1, RoomTypeMstVO vo)
			throws HisException {
	
		HisDAO dao = null;
	
		int nqryIndex;
		String query = new String();
		//System.out.println("chk1" + chk1);
	
		
		try {
			String strtemp[] = null;
			String strtemp1[] =null;
			
			dao = new HisDAO("ipd", "DAORoomTypeMst");
			query = ipd.qryHandler_ipd.getQuery(1, "select.roomtype.3");
			strtemp = chk1.replace('$', '#').split("#");
			nqryIndex = dao.setQuery(query);
	    	//dao.setQryValue(qryIndex, 1, temp[0]);
			strtemp1 = strtemp[0].replace('@', '#').split("#");
			for (int i = 0; i < strtemp1.length; i++) {
				dao.setQryValue(nqryIndex, (i + 1), strtemp1[i]);
			}
	    	
	    	WebRowSet web = dao.executeQry(nqryIndex);
			while (web.next()) {
				vo.setStrRoomType(web.getString(1));
				vo.setStrEffectiveDate(web.getString(2));
				if(web.getString(3).equals("null"))
				{
					vo.setstrRemarks("");
				}
				else
					vo.setstrRemarks(web.getString(3));
				vo.setStrIsValid(web.getString(4));
			}
		} catch (Exception e) {

			new HisException("ipd", "DAORoomTypeMst", e.getMessage());
			//e.printStackTrace();
		} finally {
			dao.free();
			dao = null;
		}

	}
/**
 * is used to update record
 * @param chk
 * @param vo
 * @return
 * @throws HisException
 */
	public static boolean updateQuery(String chk, RoomTypeMstVO vo)
			throws HisException {
		boolean retvalue = true;
		HisDAO dao = null;
		String strtemp[] = null;
		String strTemp1[] = null;
		int qryIndex;
		String query = new String();
		try {
			dao = new HisDAO("Billing", "DAOGroupMst");
			query = ipd.qryHandler_ipd.getQuery(1, "update.roomtype.1");
			qryIndex = dao.setQuery(query);

			strtemp = chk.replace('$', '#').split("#");
			strTemp1 = strtemp[0].replace('@', '#').split("#");
			
			dao.setQryValue(qryIndex, 1, vo.getStrRoomType());
			dao.setQryValue(qryIndex, 2, vo.getStrEffectiveDate());
			if(vo.getStrLastModifSeatId().equals(""))
				 dao.setQryValue(qryIndex, 3, vo.getStrSeatId());
			else
			     dao.setQryValue(qryIndex, 3, vo.getStrLastModifSeatId());
			dao.setQryValue(qryIndex, 4, vo.getstrRemarks());
			dao.setQryValue(qryIndex, 5, vo.getStrSeatId());
			dao.setQryValue(qryIndex, 6, vo.getStrIsValid());
			dao.setQryValue(qryIndex, 7, strTemp1[0]);
			dao.setQryValue(qryIndex, 8, vo.getStrHospitalCode());
			dao.setQryValue(qryIndex, 9, strTemp1[2]);
			dao.execute(0, qryIndex);

			synchronized (dao) {
				dao.fire();
			}
		} catch (Exception e) {
			retvalue = false;
			new HisException("ipd", "DAORoomTypeMst", e.getMessage());
			//e.printStackTrace();
		} finally {
			dao.free();
			dao = null;
		}
		// System.out.println("Successfully Executed=>"+retvalue);
		return retvalue;
	}
/**
 * Check duplicate data before update
 * @param chk
 * @param vo
 * @return
 */
	public static boolean initialUpdateQuery(String chk, RoomTypeMstVO vo) {
		HisDAO dao = new HisDAO("ipd", "DAORoomTypeMst");
		String strtemp[] = null;
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		String strTemp1[] =null;
		WebRowSet wb = null;
		String query = ipd.qryHandler_ipd.getQuery(1, "select.roomtype.4");

		// System.out.println("In(DAO)intialupdate query => "+ query);
		try {
			strtemp = chk.replace('$', '#').split("#");
			strTemp1 = strtemp[0].replace('@', '#').split("#");
			 
		
			
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, strTemp1[0]);
			dao.setQryValue(qryIndex, 2, vo.getStrRoomType());
			dao.setQryValue(qryIndex, 3,strTemp1[1]);
			dao.setQryValue(qryIndex, 4,strTemp1[2]);
			wb = dao.executeQry(qryIndex);
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
			new HisException("ipd", "DAORoomTypeMst.initialAddQuery()", e
					.getMessage());
			e.printStackTrace();
		} finally {
			dao.free();
			dao = null;
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

	public WebRowSet getRoomTypeDtl(int roomCode, boolean orderBy,String hCode)
			throws Exception {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String tempStr = "";
		
		this.strErrMsg = "";

		this.qryStr = ipd.qryHandler_ipd.getQuery(1, "select.bservice.0");
		
		tempStr =  ipd.qryHandler_ipd.getQuery(1, "select.bservice.cond.1").replace("?", ""+hCode+"");
		this.qryStr += " WHERE " + tempStr;
		
		if (roomCode != -1) {
			// room type condition
			tempStr = ipd.qryHandler_ipd.getQuery(1, "select.bservice.cond.0");
			this.qryStr += " where " + tempStr;
			
		}
		// CHECK ORDER BY
		if (orderBy) {
			tempStr = ipd.qryHandler_ipd.getQuery(1, "select.bservice.cond.2");
			this.qryStr += " ORDER BY " + tempStr;
		}

		try {
			if (!this.qryStr.equals("")) {
				daoObj = new HisDAO(this.strmoduleName, "RoomType"
						+ this.strfileName);
				nqryIndex = daoObj.setQuery(this.qryStr);
				if (roomCode != -1)
					daoObj.setQryValue(nqryIndex, 1, String.valueOf(roomCode));
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