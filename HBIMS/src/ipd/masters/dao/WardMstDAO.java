package ipd.masters.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import ipd.qryHandler_ipd;
import ipd.masters.vo.WardMstVO;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.rowset.WebRowSet;

public class WardMstDAO {

	
		/**
		 * This function is used to insert data in database
		 * @param vo
		 * @param tempwardcode
		 * @return
		 * @throws Exception
		 */
	public static boolean insertData(WardMstVO vo,String tempwardcode) throws Exception 
	{		
		boolean retValue = false;
		HisDAO hisdao = new HisDAO("ADT", "WardMstDAO");
		int index1, index3;
		String[] tempbuilding = null;// split value of building from sl_no
		tempbuilding = vo.getStrBuilding().replace("^", "#").split("#");
		String[] tempblock = null;// split value of block from sl_no
		tempblock = vo.getStrBlock().replace("^", "#").split("#");
		String [] room=vo.getStrRroom();
		
		String [][] roomfloor=new String [room.length][room.length];
		String [] tempfloor =new String[room.length];
		for(int i=0;i<room.length;i++)
			roomfloor[i]=room[i].replace("^", "#").split("#");//split primary key room number,floor id,hospital code
		for(int i=0;i<room.length;i++)
		{
			if(roomfloor[i][2]!=null)
			{
				tempfloor[i]=roomfloor[i][2];
			}
			else
			{
				tempfloor[i]="0";
			}
		}
			String strfloor=getCommaSeperated(tempfloor);// get floor like (1,2,3)
			
			try 
			{

			String query1 = qryHandler_ipd.getQuery(1, "insert.ward.0");//insert into ward master
			//String query2 = qryHandler_ipd.getQuery(1, "insert.ward.1");// insert into ward criteria
			String query3 = qryHandler_ipd.getQuery(1, "insert.ward.2");//insert into ward room
			// for first insert query-->data will be saved in ward master
			index1 = hisdao.setQuery(query1);
			hisdao.setQryValue(index1, 1, tempwardcode);
			hisdao.setQryValue(index1, 2, vo.getStrWardType());
			hisdao.setQryValue(index1, 3, vo.getStrWardName());
			hisdao.setQryValue(index1, 4, vo.getStrNoOfBed());
			hisdao.setQryValue(index1, 5, tempbuilding[0]);
			hisdao.setQryValue(index1, 6, tempblock[0]);
			hisdao.setQryValue(index1, 7, strfloor);
			hisdao.setQryValue(index1, 8, vo.getStrEffectiveFrom());
			//hisdao.setQryValue(index1, 9, vo.getStrEffectiveTo());
			hisdao.setQryValue(index1, 9, vo.getStrRemark());
			hisdao.setQryValue(index1, 10, vo.getStrSeatId());
			if(vo.getStrbookingAllowed().equals(""))
				hisdao.setQryValue(index1, 11,"0");
			else
			    hisdao.setQryValue(index1, 11,vo.getStrbookingAllowed());
			hisdao.setQryValue(index1, 12, vo.getStrmoreorless());
			hisdao.setQryValue(index1, 13, vo.getStrmsapproveRequired());
			if(vo.getStrmaxNoWaitingList().equals(""))
				hisdao.setQryValue(index1, 14, "0");
			else
			    hisdao.setQryValue(index1, 14, vo.getStrmaxNoWaitingList());
			hisdao.setQryValue(index1, 15, vo.getStrHospitalCode());
			
			if(vo.getStrWardType().equals("12"))
			hisdao.setQryValue(index1, 16, "1");
			else
			hisdao.setQryValue(index1, 16, vo.getStrMaxPatPerBed());
			
			hisdao.execute(index1, 0);
			
			
			for(int i=0;i<room.length;i++) 
			{
				index3 = hisdao.setQuery(query3);
				
				hisdao.setQryValue(index3, 1, tempwardcode);
				hisdao.setQryValue(index3, 2, roomfloor[i][1]);
				hisdao.setQryValue(index3, 3, vo.getStrEffectiveFrom());
				//hisdao.setQryValue(index3, 4, vo.getStrEffectiveTo());
				hisdao.setQryValue(index3, 4, vo.getStrSeatId());
				hisdao.setQryValue(index3, 5, vo.getStrHospitalCode());
				hisdao.execute(index3, 0);				
			}
			
			synchronized (hisdao) 
			{
					hisdao.fire();
					retValue = true;
		    }			
		} 
		catch (Exception e) 
		{
			retValue = false;
			new HisException("ADT", "Ward Master","ipd.masters.WardMstDAO.insertData() --> ");
		} 
		finally 
		{
			hisdao.free();
			hisdao = null;
		}
		return retValue;
	}
	public static boolean checkDuplicateModify(String chk1,WardMstVO vo)
	{
		HisDAO dao = new HisDAO("ADT", "WardMstDAO");
		boolean freturnValue = false;

		int nqryIndex;
		int nCount = 0;

		WebRowSet wb = null;
		String []temp=chk1.replace("@", "#").split("#");
		String strQuery = ipd.qryHandler_ipd.getQuery(1, "select.ward.24");
		try 
		{
		
			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, temp[1]);
			dao.setQryValue(nqryIndex, 2, vo.getStrWardName());
			dao.setQryValue(nqryIndex, 3, temp[0]);
			
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) 
			{
				nCount = Integer.parseInt(wb.getString(1));
			}
			if (nCount == 0) 
			{
				freturnValue = true;
			} 
			else 
			{
				freturnValue = false;
			}
		} 
		catch (Exception e) 
		{			
			freturnValue = false;
			new HisException("ADT", "Ward Master","ipd.masters.WardMstDAO.checkDuplicateModify() --> ");						
		} 
		finally 
		{
			dao.free();
			dao = null;
		}
		return freturnValue;

	}
	/**
	 * 
	 * @param vo
	 * @return
	 */
	public static boolean checkDuplicate(WardMstVO vo)
	{
		HisDAO dao = new HisDAO("IPD", "WardMstDAO");
		boolean freturnValue = false;

		int nqryIndex;
		int nCount = 0;

		WebRowSet wb = null;

		String strQuery = ipd.qryHandler_ipd.getQuery(1, "select.ward.23");
		try 
		{		
			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, vo.getStrWardName());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) 
			{
				nCount = Integer.parseInt(wb.getString(1));
			}
			if (nCount == 0) 
			{
				freturnValue = true;
			} 
			else 
			{
				freturnValue = false;
			}
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			freturnValue = false;
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.checkDuplicate() --> ");
						
		} 
		finally 
		{
			dao.free();
			dao = null;
		}
		return freturnValue;
	}
   /**
    * this function is used for logical deletion of records from ward room and
     ward criteria master
    * @param chk1
    * @param vo
    * @return
    * @throws Exception
    */
	public static boolean delete(String chk1, WardMstVO vo) throws Exception 
	{	
		boolean fretvalue = true;
		HisDAO dao = new HisDAO("ADT", "WardMstDAO");
		int index2; 
		
		try 
		{
			String[] temp = null;
			temp = chk1.replace('@', '#').split("#");
			String query2 = qryHandler_ipd.getQuery(1, "delete.wardroom.0");// delete room
			//String query3 = qryHandler_ipd.getQuery(1, "delete.ward.1");// delete data  from ward criteria
			index2 = dao.setQuery(query2);
			dao.setQryValue(index2, 1, vo.getStrLastModifySeatId());//last modified seat id
			dao.setQryValue(index2, 2, temp[1]);
			dao.setQryValue(index2, 3, temp[0]);
			dao.execute(index2, 0);
			synchronized (dao) 
			{
				dao.fire();
				fretvalue = true;
			}
		} 
		catch (Exception e) 
		{
			fretvalue = false;
			new HisException("ADT", "Ward Master","ipd.masters.WardMstDAO.delete() --> ");
		} 
		finally 
		{
			dao.free();
			dao = null;
		}
		
		return fretvalue;

	}
	/**
	 * This function is used to update data during correction mode it invoke updateDataWard()
	 * @param chk1
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean updateCorrection(String chk1, WardMstVO vo) throws Exception 
	{
    	boolean fretvalue = true;
		boolean fretvalue1 = false;// flag for delete function
		boolean fretvalue2 = false;// flag for ward master updation function in case of updation
		boolean fretvalue3 = false; // flag for ward room insertion function in case of updation
		//boolean fretvalue4 = true;// flag for ward criteria insertion function in case of updation

		try 
		{
			//update ward data
			fretvalue2 = updateDataWard(chk1, vo);
			if(fretvalue2)
			{
				//call delete function before correction
				fretvalue1 = delete(chk1, vo);
				if(fretvalue1)
					fretvalue3 = insertDataWardRoom(vo, chk1);
			}
			if (!fretvalue2 && !fretvalue3)
			{
				fretvalue = false;
			}
		} 
		catch (Exception e) 
		{
			fretvalue = false;
			new HisException("ADT", "Ward Master","ipd.masters.WardMstDAO.updateCorrection() --> ");
		}
		return fretvalue;
	}

	/**
	 * this function is used for updating records of ward master table in case of modification during correction mode
	 * @param chk
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean updateDataWard(String chk, WardMstVO vo) throws Exception 
	{
		boolean retvalue = true;
		HisDAO dao = null;
		String temp[] = null;
		//String strfloor =  getComaSeparatedFloor(vo);
		int qryIndex;
		String query = new String();
		
		try 
		{
			dao = new HisDAO("ADT", "WardMstDAO");
			query = ipd.qryHandler_ipd.getQuery(1,"update.ward.0");
			qryIndex = dao.setQuery(query);
			temp = chk.replace('@', '#').split("#");
			String tempslno[]=temp[2].replace("$", "#").split("#");
			String room[]=vo.getStrRroom();
			String room1[][]=new String [room.length][room.length];
			String []strtempfloor=new String[room.length];
			for(int i=0;i<room.length;i++)
				room1[i]=room[i].replace("^", "#").split("#");
			for(int i=0;i<room.length;i++)	
			{
				strtempfloor[i]=room1[i][2];			
			}
			String floor=getCommaSeperated(strtempfloor);
			dao.setQryValue(qryIndex, 1, vo.getStrWardType());
			dao.setQryValue(qryIndex, 2, vo.getStrWardName());
			dao.setQryValue(qryIndex, 3, vo.getStrNoOfBed());
			dao.setQryValue(qryIndex, 4, vo.getStrBuilding());
			dao.setQryValue(qryIndex, 5, vo.getStrBlock());
			dao.setQryValue(qryIndex, 6, floor);
			dao.setQryValue(qryIndex, 7, vo.getStrEffectiveFromModi());
			//dao.setQryValue(qryIndex, 8, vo.getStrEffectiveTo());
			//dao.setQryValue(qryIndex, 8, "");
			dao.setQryValue(qryIndex, 8, vo.getStrLastModifySeatId());
			dao.setQryValue(qryIndex, 9, vo.getStrRemark());
			if(vo.getStrbookingAllowed().equals(""))
				dao.setQryValue(qryIndex, 10,"0");
			else
			    dao.setQryValue(qryIndex, 10,vo.getStrbookingAllowed());
			dao.setQryValue(qryIndex, 11, vo.getStrmoreorless());
			dao.setQryValue(qryIndex, 12, vo.getStrmsapproveRequired());
			if(vo.getStrmaxNoWaitingList().equals(""))
				dao.setQryValue(qryIndex, 13, "0");
			else
			    dao.setQryValue(qryIndex, 13, vo.getStrmaxNoWaitingList());
			dao.setQryValue(qryIndex, 14, vo.getStrMaxPatPerBed());
			dao.setQryValue(qryIndex, 15, temp[1]);
			dao.setQryValue(qryIndex, 16, tempslno[0]);
			dao.setQryValue(qryIndex, 17, temp[0]);		
			dao.execute(qryIndex, 0);
			
			synchronized (dao) 
			{
				dao.fire();
			}
		} 
		catch (Exception e) 
		{			
			retvalue = false;
			new HisException("ADT", "Ward Master","ipd.masters.WardMstDAO.updateDataWard() --> ");			
		} 
		finally 
		{
			dao.free();
			dao = null;
		}
		return retvalue;
	}
  /**
   * //this function is used for updating records in case of modification
   * @param chk1
   * @param vo
   * @return
   * @throws Exception
   */
	public static boolean updateModification(String chk1, WardMstVO vo)
			throws Exception {

		boolean fretvalue=false;
		boolean fretvalue1 = false;
	    boolean fretvalue2 = false;
		boolean fretvalue3=false;
		
		String[] temp = chk1.replace('@', '#').split("#");
		
		String tempwardcode =  temp[1]; //getWardKey(vo);
	
		try {
				fretvalue1=updateDataWard(vo,chk1);
				if(fretvalue1)
				{
					fretvalue2=insertDataWard(vo,tempwardcode);
					if(fretvalue2)
					{
						//fretvalue1 = delete(chk1, vo);
						//if(fretvalue1)
						//{
							fretvalue3 = insertDataWardRoom(vo, chk1);
					//	}
						
					}
						
				}
		if (fretvalue1 && fretvalue2 && fretvalue3)
				fretvalue = true;
			else
				fretvalue=false;

		} catch (Exception e) {
			fretvalue = false;
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.updateModification() --> ");
			}
		return fretvalue;
	}
/**
 * This function is used to insert data in ward master during modification mode of modify
 * @param vo
 * @param tempwardcode
 * @return
 * @throws Exception
 */
	public static boolean insertDataWard(WardMstVO vo, String tempwardcode)
			throws Exception {
		boolean retValue = false;
		HisDAO hisdao = new HisDAO("ipd", "WardMstDAO");
		int index1;
		String[] tempFloorRoom = vo.getStrRroom();// concatenated value of  floor
		String[][]roomfloor=new String [tempFloorRoom.length][tempFloorRoom.length];
		String[] tempfloor=new String[tempFloorRoom.length];
	//	String floor;
		for(int i=0;i<tempFloorRoom.length;i++)
			roomfloor[i]=tempFloorRoom[i].replace("^", "#").split("#");
		for(int i=0;i<tempFloorRoom.length;i++)
			tempfloor[i]=roomfloor[i][2];
	//	floor=getCommaSeperated(tempfloor);
		String[] tempbuilding = null;// split value of building from sl_no
		tempbuilding = vo.getStrBuilding().replace("^", "#").split("#");
		String[] tempblock = null;// split value of block from sl_no
		tempblock = vo.getStrBlock().replace("^", "#").split("#");
		String query1 = "";
		try {
			query1 = qryHandler_ipd.getQuery(1, "insert.ward.3");
			String tempFloor[] = new String[tempFloorRoom.length];
			for (int i = 0; i < tempFloorRoom.length; i++) {
				tempFloor[i] = tempFloorRoom[i].replace("^", "#").split("#")[0];
			}
			String strfloor = WardMstDAO.getCommaSeperated(tempFloor);
				index1 = hisdao.setQuery(query1);
				hisdao.setQryValue(index1, 1, tempwardcode);
				hisdao.setQryValue(index1, 2, vo.getStrHospitalCode());
				hisdao.setQryValue(index1, 3, tempwardcode);
				hisdao.setQryValue(index1, 4, vo.getStrWardType());
				hisdao.setQryValue(index1, 5, vo.getStrWardName());
				hisdao.setQryValue(index1, 6, vo.getStrNoOfBed());
				hisdao.setQryValue(index1, 7, tempbuilding[0]);
				hisdao.setQryValue(index1, 8, tempblock[0]);
				hisdao.setQryValue(index1, 9, strfloor);
				hisdao.setQryValue(index1, 10, vo.getStrEffectiveFrom());
				hisdao.setQryValue(index1, 11, vo.getStrEffectiveTo());
				hisdao.setQryValue(index1, 12, vo.getStrRemark());
				hisdao.setQryValue(index1, 13, vo.getStrSeatId());
				hisdao.setQryValue(index1, 14,vo.getStrbookingAllowed());
				hisdao.setQryValue(index1, 15, vo.getStrmoreorless());
				hisdao.setQryValue(index1, 16, vo.getStrmsapproveRequired());
				hisdao.setQryValue(index1, 17, vo.getStrmaxNoWaitingList());
				hisdao.setQryValue(index1, 18, vo.getStrHospitalCode());
				hisdao.setQryValue(index1, 19, vo.getStrMaxPatPerBed());
				hisdao.execute(index1, 0);
			//////////System.out.println(" query1==" + query1);
			synchronized (hisdao) {
				hisdao.fire();
				retValue = true;
			}
		} catch (Exception e) {
			retValue = false;
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.insertDataWard() --> ");
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return retValue;
		
	}
   
/**
 * This function is used to insert room in ward room master during modification mode of modify
 * @param vo
 * @param chk1
 * @return
 * @throws Exception
 */
	
	public static boolean insertDataWardRoom(WardMstVO vo, String chk1)
			throws Exception {
		boolean retValue = true;

		 String temp[] =null;
		temp = chk1.replace('@', '#').split("#");
		HisDAO hisdao = new HisDAO("ipd", "WardMstDAO");
		int index3;
		String query3 = "";
		query3 = qryHandler_ipd.getQuery(1, "insert.ward.4");
		String[] tempFloorRoom = vo.getStrRroom();// concatenated value of
		String temproom[][]=new String[tempFloorRoom.length][tempFloorRoom.length];											// //////////System.out.println("VALUE
		for(int i=0;i<tempFloorRoom.length;i++)// OF FLOOR ROOM==" +
			temproom[i]=tempFloorRoom[i].replace("^", "#").split("#");// spliting room floor
		try {
			
						//////////System.out.println("length of list" + tempFloorRoom.length);
			for (int i = 0; i < tempFloorRoom.length; i++)
			{
				   index3 = hisdao.setQuery(query3);
				
					hisdao.setQryValue(index3, 1, temp[1]);
					hisdao.setQryValue(index3, 2,temp[0]);
					hisdao.setQryValue(index3, 3,temp[1]);
					hisdao.setQryValue(index3, 4, temproom[i][1]);
					hisdao.setQryValue(index3, 5, temproom[i][1]);
					//hisdao.setQryValue(index3, 6, vo.getStrEffectiveTo());
					hisdao.setQryValue(index3, 6, vo.getStrCtDate());
					hisdao.setQryValue(index3, 7, vo.getStrLastModifySeatId());
					hisdao.setQryValue(index3, 8, temp[0]);
					hisdao.setQryValue(index3, 9, vo.getStrLastModifySeatId());
					hisdao.setQryValue(index3, 10, vo.getStrEffectiveFromModi());
					hisdao.execute(index3, 0);
				}
			
			synchronized (hisdao) {

			hisdao.fire();
			retValue = true;
		}
		} catch (Exception e) {
			e.printStackTrace();
			retValue = false;
			
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.insertDataWardRoom() --> ");
    	} finally {
			hisdao.free();
			hisdao = null;
		}
		return retValue;
	}

	/**
	 * This function generates ward code before inserting new ward
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static String getWardKey(WardMstVO vo) throws Exception {

		String key = "";
		String query = "";
		int index;
		HisDAO hisdao = new HisDAO("ipd", "WardMstDAO");
		query = ipd.qryHandler_ipd.getQuery(1, "select.ward.2");
		index = hisdao.setQuery(query);
		hisdao.setQryValue(index, 1, vo.getStrHospitalCode());
        try{
		WebRowSet wb = hisdao.executeQry(index); // executes query to recieve
		// primary key.
		if (wb.next()) {

			key = wb.getString(1);

		}
        }catch (Exception e) {
        	e.printStackTrace();
        	new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.getWardKey() --> ");
        }
        return key;

	}

	/**
	 * This function used to select data for a record to be modified and display back it on modify page
	 * @param chk1
	 * @param vo
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean modifyQuery(String chk1, WardMstVO vo) throws Exception{
       	boolean retValue = false;
		HisDAO hisdao = new HisDAO("ipd", "WardMstDAO");
		int index1;
		WebRowSet wb = null;
		String[] temp = null;
		chk1=chk1.replace('@', '$');
		temp=chk1.replace("$", "#").split("#");
		String query1 = qryHandler_ipd.getQuery(1, "select.ward.3");
		// value for left Combo
		//String lRoom= getLRoomModi(temp[0], temp[1]); 
		// value for right room Combo
		String rRoom=getRRoomModi(temp[0],  temp[1]);
		try {
			//for ward and ward room
			//String query2 = qryHandler_ipd.getQuery(1, "select.ward.4");//for ward criteria
		//	System.out.println("query1 in modify1==" + query1);
			//System.out.println("query2 in modify2==" + query2);
			// the
			// 'chk'
			// value
			// to
			// get
			// both
			// primary
			// keys.
			index1 = hisdao.setQuery(query1);
			hisdao.setQryValue(index1, 1, temp[1]);
			hisdao.setQryValue(index1, 2, temp[2]);
			hisdao.setQryValue(index1, 3, temp[0]);	
			wb = hisdao.executeQry(index1);
			while (wb.next()) {
				
				vo.setStrwardCode(temp[1]);
				vo.setStrWardName(wb.getString(1));
				vo.setStrWardType(wb.getString(2));
				vo.setStrNoOfBed(wb.getString(3));
				vo.setStrBuilding(wb.getString(4));
				vo.setStrEffectiveFrom(wb.getString(7));
				vo.setStrEffectiveTo(wb.getString(8));
				vo.setStrRemark(wb.getString(9));
				vo.setStrBlock(wb.getString(5));
				vo.setStrbookingAllowed(wb.getString(10));
				vo.setStrmoreorless(wb.getString(11));
				vo.setStrmsapproveRequired(wb.getString(12));
				vo.setStrmaxNoWaitingList(wb.getString(13));
				vo.setStrMaxPatPerBed(wb.getString(14));
				String lRoom= getLRoomModi(temp[0], temp[1],wb.getString(4),wb.getString(5)); 
				vo.setStrLRoomModi(lRoom);
				vo.setStrRRoomModi(rRoom);
						
			
			}
					} catch (Exception e) {
			// 
			retValue = false;
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.modifyQuery() --> ");
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return retValue;
	}
/**
 * returns the array of data in a given Row Set based on the give index 
 * @param ws - WebRowSet Object for a particular Table.
 * @param index - integer which represent the column index.
 * 			  1 - will return Sex array.
 * 			  2 - will return Age From array.
 * 			  3 - will return Age To array.		     
 * @return - String array data of particular column 
 */
	public static String[] getMultiRowContent(WebRowSet ws , int index)throws Exception{
		
		String[] result = null;
		int count = 0;
		try {
			result = new String[ws.size()];
			
			while(ws.next()){
			
				result[count] = ws.getString(index);
				count = count+1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.getMultiRowContent() --> ");
		}
		
		return result;
	}
/**
 * 
 * This function is used to check data before updating data during correction mode.it will check whether effective to of corrected record is less than from effective from data or not
 * @param strchk
 * @param vo
 * @return
 * @throws Exception
 */
	public static boolean initialUpdateCorrection(String strchk, WardMstVO vo) throws Exception 
	{	
		HisDAO dao = new HisDAO("ADT", "WardMstDAO");
		String[] temp = null;
		boolean freturnValue = false;
		int nqryIndex;
		int nCount = 0;
		WebRowSet wb = null;
		strchk.replace("@", "$");
		temp=strchk.replace("@", "#").split("#");
		String strQuery = ipd.qryHandler_ipd.getQuery(1, "select.ward.corr.0");
		try 
		{
			
			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, temp[1]);
			dao.setQryValue(nqryIndex, 2, temp[0]);
			//dao.setQryValue(nqryIndex, 3, vo.getStrEffectiveFrom());
			//dao.setQryValue(nqryIndex, 4, vo.getStrEffectiveTo());
			//dao.setQryValue(nqryIndex, 5, vo.getStrEffectiveTo());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) 
			{
				nCount = Integer.parseInt(wb.getString(1));				
			}
		
			if (nCount == 0) 
			{
				freturnValue = true;
			} 
			else 
			{
				freturnValue = false;
			}
		} 
		catch (Exception e) 
		{
			freturnValue = false;
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.initialUpdateCorrection() --> ");
		} 
		finally 
		{
			dao.free();
			dao = null;
		}
		
		return freturnValue;
	}

	/**
	 * This function is used check data before modify any data during modification mode.It check whether effective from exist between effective from date and effective to date of that record to be modified. 
	 * @param strchk
	 * @param vo
	 * @return If it return false it will set error message
	 * @throws Exception
	 */
	public static boolean initialUpdateModi1(String strchk, WardMstVO vo) throws Exception {
		
		HisDAO dao = new HisDAO("ipd", "WardMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		int nCount = 0;
		WebRowSet wb = null;
		String strQuery = ipd.qryHandler_ipd.getQuery(1, "select.ward.modi.o");
		String strTemp[] = strchk.replace('@', '#').split("#");
		
		try {

			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, strTemp[0]);
			dao.setQryValue(nqryIndex, 2, strTemp[1]);
			dao.setQryValue(nqryIndex, 3, vo.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 4, vo.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 5, vo.getStrEffectiveFrom());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				nCount = Integer.parseInt(wb.getString(1));
			
			}
			if (nCount == 0) {
				freturnValue = true;
			} else {
				freturnValue = false;
			}
		} catch (Exception e) {
			freturnValue = false;
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.initialUpdateModi1() --> ");
		
		} finally {
			dao.free();
			dao = null;
		}
		
		return freturnValue;
	}
  /**
   * This function is used check whether effective to exist between effective from date and effective to date of record to be modified.
   * @param strchk
   * @param vo
   * @return
   * @throws Exception
   */
	public static boolean initialUpdateModi2(String strchk, WardMstVO vo) throws Exception {
		
		
		HisDAO dao = new HisDAO("ipd", "WardMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		int nCount = 0;
		WebRowSet wb = null;
		String strQuery = ipd.qryHandler_ipd.getQuery(1, "select.ward.modi.1");
		String strTemp[] = strchk.replace('@', '#').split("#");
		try {
			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, strTemp[1]);
			dao.setQryValue(nqryIndex, 2, vo.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 3, vo.getStrEffectiveTo());
			dao.setQryValue(nqryIndex, 4, vo.getStrEffectiveTo());
			dao.setQryValue(nqryIndex, 5, strTemp[0]);
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				nCount = Integer.parseInt(wb.getString(1));
			}
			if (nCount == 0) {
				freturnValue = true;
			} else {
				freturnValue = false;
			}
		} catch (Exception e) {
			freturnValue = false;
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.initialUpdateModi2() --> ");
						
		} finally {
			dao.free();
			dao = null;
		}
		return freturnValue;
	}

	/**
	 *  for concatenated , between array values and sorting values
	 */
	public static String getCommaSeperated(String[] resTemp) {

		Arrays.sort(resTemp);
		String res = resTemp[0];
		String temp = res;

		for (int i = 0; i < resTemp.length; i++) {

			if (!temp.equals(resTemp[i])) {

				res = res + "," + resTemp[i];
				temp = resTemp[i];
			}
	}

		return res;
	}
	/**
	 * this function will give ward detail
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet  getWarddtl(boolean orderBy) throws Exception {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String tempStr = "";
		int qryIndex = 0;
		String errMsg = "";
        String qryStr ="";
		qryStr = ipd.qryHandler_ipd.getQuery(1, "gbl.ward.0");
		tempStr = ipd.qryHandler_ipd.getQuery(1,"gbl.ward.cond.0");	
		qryStr += " WHERE " + tempStr;
//		CHECK ORDER BY
		if(orderBy) {
			tempStr = ipd.qryHandler_ipd.getQuery(1,"gbl.ward.cond.1");
			qryStr += " ORDER BY " + tempStr;
		}
		try {
			if(!qryStr.equals("")) {
				daoObj = new HisDAO("ipd","ipd_qry_mst.properties." +"WardMstDAO");
				qryIndex = daoObj.setQuery(qryStr);
			   
				ws = daoObj.executeQry(qryIndex);
			}
			else {
				errMsg = "DAOIpd.getWarddtl -->Query is blank";
				throw new Exception(errMsg);
			}
		}
		catch(Exception e) {
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.getWarddtl() --> ");
		}
		finally {
			if(daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

		return ws;
	}

/**
 * This  function return view detail for view page
 * @param chk1
 * @param vo
 * @return
 * @throws Exception
 */
	public static boolean viewQuery(String chk1, WardMstVO vo) throws Exception{

		boolean retValue = false;
		HisDAO hisdao = new HisDAO("ipd", "WardMstDAO");
		int nindex1,nindex3;
		WebRowSet wb = null;
		String[] temp = null;
		temp = chk1.replace('@', '#').split("#");
		String []temp1=temp[2].replace('$', '#').split("#");
		try {
			String strquery1 = qryHandler_ipd.getQuery(1, "select.ward.view.1");//WARD QUERY
			String strquery3 = qryHandler_ipd.getQuery(1, "select.roomR.Modi.0"); //query for room
			nindex1 = hisdao.setQuery(strquery1);
			hisdao.setQryValue(nindex1, 1,temp[1] );
			hisdao.setQryValue(nindex1, 2,temp1[0] );
			hisdao.setQryValue(nindex1, 3,temp[0] );				
			wb = hisdao.executeQry(nindex1);
			while (wb.next()) {
				vo.setStrwardCode(temp[1]);
				vo.setStrWardName(wb.getString(1));
				vo.setStrBuildingName(wb.getString(4));
				vo.setStrBlockName(wb.getString(5));
				vo.setStrWardType(wb.getString(2));
				vo.setStrNoOfBed(wb.getString(3));
				vo.setStrEffectiveFrom(wb.getString(7));
				vo.setStrEffectiveTo(wb.getString(8));
				vo.setStrRemark(wb.getString(9));
				vo.setStrbookingAllowed(wb.getString(10));
				vo.setStrmoreorless(wb.getString(11));
				vo.setStrmsapproveRequired(wb.getString(12));
				vo.setStrmaxNoWaitingList(wb.getString(13));
				vo.setStrMaxPatPerBed(wb.getString(14));
			}
			nindex1=hisdao.setQuery( strquery3);
		    hisdao.setQryValue(nindex1, 1,temp[1] );
		    hisdao.setQryValue(nindex1, 2,temp[0] );
			wb = null;
			wb=hisdao.executeQry(nindex1);
			HisUtil hs=new HisUtil("ipd", "VOWardMst");
			String cmb=hs.getOptionValue(wb, "0", "", true);
			vo.setStrRRoomModi(cmb);
			/*wb.beforeFirst();
			String[] sex = WardMstDAO.getMultiRowContent(wb,1);
			wb.beforeFirst();
			String[] fromage = WardMstDAO.getMultiRowContent(wb,2);
			wb.beforeFirst();
			String[] toage = WardMstDAO.getMultiRowContent(wb,3);
   			wb.beforeFirst();
			String[] unit = WardMstDAO.getMultiRowContent(wb,4);
			vo.setStrGender(sex);
			vo.setStrFromAge(fromage);
			vo.setStrToAge(toage);
			vo.setStrFunit(unit);*/
			/*wb = null;
			nindex3= hisdao.setQuery(strquery3);
			hisdao.setQryValue(nindex3, 1, temp1[0]);
			hisdao.setQryValue(nindex3, 2, vo.getStrBuilding());
			hisdao.setQryValue(nindex3, 3, vo.getStrBlock());
			wb = hisdao.executeQry(nindex3);
			if(wb!= null){
			 int i = 0;
			 int rowLen = wb.size();
			 String[] arrtemp = new String[rowLen];
			 String[] arrtemp1 = new String[rowLen];
			while(wb.next()){
				arrtemp[i] = wb.getString(1);
				arrtemp1[i] = String.valueOf(wb.getInt(2));
				i = i+1;
			}			
			vo.setStrFloors(arrtemp);
			// fetching the room value
			}*/
		} catch (Exception e) {
			retValue = false;
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.viewQuery() --> ");
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return retValue;
	}
/**
 * 	for getting comma seperated value of floors
 * @param floorid
 * @return
 */
	public static String [] getComaSeparatedFloor(String floorid[]) {

		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.ward.10");
		String strfloor[]=new String[floorid.length];
		HisDAO dao=new HisDAO("ipd", "WardMstDAO");
		String tempfloorid=getCommaSeperated(floorid);
		try
		{
				int nqryIndex = dao.setQuery(strquery);
				dao.setQryValue(nqryIndex,1,tempfloorid);
			//	WebRowSet web2=dao.executeQry(nqryIndex);
		}
		catch(Exception e)
		{
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.getComaSeparatedFloor() --> ");
		}
		return strfloor;
	}
	/**
	 * 
	 * @param vo
	 * @param chk
	 * @return
	 * @throws Exception
	 */
	public static boolean updateDataWard(WardMstVO vo,String chk) throws Exception
	{
		int qryIndex;
		boolean retValue=true;
		String query = ipd.qryHandler_ipd.getQuery(1, "update.ward.modi.1");
		HisDAO dao = new HisDAO("ipd", "DAOWard");
		String temp[];
		temp = chk.replace('@', '#').split("#");
		try
		{
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, vo.getStrEffectiveFrom());
			dao.setQryValue(qryIndex, 2, temp[0]);
			dao.setQryValue(qryIndex, 3, temp[1]);
			dao.setQryValue(qryIndex, 4, vo.getStrEffectiveFrom());
			dao.execute(qryIndex, 0);
			synchronized (dao) {
				dao.fire();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			retValue = false;
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.updateDataWard() --> ");
		}
		finally {

			dao.free();
			dao = null;
		}
		return retValue;
	}
	/**
	 * This function is used to bring room number in left room combo on add page
	 * @param hCode
	 * @return
	 * @throws Exception
	 */
	public WebRowSet getLRoom(String hCode,String blockId, String buildingId) throws Exception
	{
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.Lroom.1");
		WebRowSet web2=null;
		HisDAO dao=new HisDAO("ipd", "WardMstDAO");
		try
		{
			
			if(!blockId.equals("0"))
			{
				strquery = ipd.qryHandler_ipd.getQuery(1, "select.Lroom.X");
				strquery=strquery.concat(" "+ipd.qryHandler_ipd.getQuery(1, "select.Lroom.1.cond.1"));			
			}
			if(!buildingId.equals("0"))
			{
				strquery+=" AND A.ENUM_EST_BUILDING_CODE=C.NUM_BUILDING_CODE AND A.ENUM_EST_BUILDING_CODE=?";			
			}
			int nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex,1,hCode);	
			
			if(!blockId.equals("0")){
				dao.setQryValue(nqryIndex,2,blockId);	
			}
			if(!buildingId.equals("0")){
				if(!blockId.equals("0")){
					dao.setQryValue(nqryIndex,3,buildingId);	
				}else{
					dao.setQryValue(nqryIndex,2,buildingId);
				}
					
			}
			
			
			web2=dao.executeQry(nqryIndex);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//System.out.println("error"+e.getMessage());
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.getLRoom() --> ");
		}
		return web2;
	}
		
	/**
	 * This function is used to fetch the room value in left room combo during modification 
	 * @param hCode
	 * @param wardCode
	 * @return
	 * @throws Exception
	 */
	//This function is developed By Anurudra Goel
	public static String getLRoomModi(String hCode,String wardCode,String buildingId,String blockId) throws Exception{
			String strquery = ipd.qryHandler_ipd.getQuery(1, "select.roomL.Modi.1");
			String cmbstr="";
			WebRowSet web2=null;
			HisDAO dao=new HisDAO("ipd", "WardMstDAO");
			try
			{
				if(!blockId.equals("0")){
					strquery=strquery.concat(" "+ipd.qryHandler_ipd.getQuery(1, "select.roomL.Modi.1.cond.1"));			
				}
				if(!buildingId.equals("0")){
					strquery=strquery.concat(" "+ipd.qryHandler_ipd.getQuery(1, "select.roomL.Modi.1.cond.2"));			
				}
				int nqryIndex = dao.setQuery(strquery);
				dao.setQryValue(nqryIndex,1,hCode);	
				dao.setQryValue(nqryIndex,2,wardCode);
				
				if(!blockId.equals("0")){
					dao.setQryValue(nqryIndex,3,blockId);	
				}
				if(!buildingId.equals("0")){
					if(!blockId.equals("0")){
						dao.setQryValue(nqryIndex,4,buildingId);
					}else{
						dao.setQryValue(nqryIndex,3,buildingId);
					}
						
				}
				
				web2=dao.executeQry(nqryIndex);
				HisUtil hisutil = new HisUtil("ipd", "VOWardMst");
				cmbstr = hisutil.getOptionValue(web2, "0", "", true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.getLRoomModi() --> ");
			}
			
			return cmbstr;
	}
	/**
	 * This function is used to fetch the room value in right room combo during modification 
	 * @param hCode
	 * @param wardCode
	 * @return
	 * @throws Exception
	 */
	public static String getRRoomModi(String hCode,String wardCode) throws Exception{
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.roomR.Modi.0");
		String cmbstr="";
		WebRowSet web2=null;
		HisDAO dao=new HisDAO("ipd", "WardMstDAO");
		try
		{
			int nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex,1,wardCode);
			dao.setQryValue(nqryIndex,2,hCode);
			web2=dao.executeQry(nqryIndex);
			HisUtil hisutil = new HisUtil("ipd", "VOWardMst");
			cmbstr = hisutil.getOptionValue(web2, "0", "", true);
		}
		catch(Exception e)
		{
			new HisException("IPD", "Ward Master","ipd.masters.WardMstDAO.getRRoomModi() --> ");
		}
		return cmbstr;
}
	
}
