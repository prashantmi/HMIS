package ipd.masters.dao;

import ipd.masters.vo.RoomConfigMstVO;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class RoomConfigMstDAO {
	
	/**
	 * To check for Duplicate Records
	 * 
	 * @param vo - Form Object of the Current Master
	 * @return - true then record already exist else false then record saved
	 * @throws Exception
	 */
	public static boolean getPropertyComboValues(RoomConfigMstVO vo) throws Exception {
		HisDAO dao = new HisDAO("ipd", "RoomConfigMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		
		WebRowSet wb = null;

		String strQuery = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.9");

		try {
			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			wb = dao.executeQry(nqryIndex);
			vo.setPropertyComboWs(wb);

			
		} catch (Exception e) {
			freturnValue = false;
			throw new Exception("RoomConfigMstDAO.initialAddQuery(vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return freturnValue;
	}


	/**
	 * retrieves and executes insert Query
	 * 
	 * @param vo -Form Object of the Current Master
	 * @return - If Record Inserted Successfully. <br> false - If Record Not Inserted Successfully
	 * @throws Exception
	 */
	public static boolean insertQuery(RoomConfigMstVO vo) throws Exception {

		HisDAO dao = null;
		int nqryIndex;
		boolean fretValue = true;
		String strQuery = new String();
		//String strTempRoom = vo.getStrRoomId();
		int nMultiRowLength = 0; 
		String strFuncName = "";
		int nFuncIndex = 0;
		//String strErr = "";
		String strRoomNo = "";
		
		
		//String strRoomValue[] = strTempRoom.replace("^","#").split("#");
		
		try {
			dao = new HisDAO("ipd", "RoomConfigMstDAO");

			strFuncName = "{? = call IPD_MST.get_room_no(?::numeric)}";
			nFuncIndex = dao.setFunction(strFuncName);
			dao.setFuncInValue(nFuncIndex, 2, vo.getStrHospitalCode());
			dao.setFuncOutValue(nFuncIndex, 3);
			dao.executeFuncForNumeric(nFuncIndex);
			strRoomNo = dao.getFuncNumeric(nFuncIndex);
			
			strQuery = ipd.qryHandler_ipd.getQuery(1, "insert.roomConfig.7");
         
			nqryIndex = dao.setQuery(strQuery);
            
            dao.setQryValue(nqryIndex, 1, strRoomNo);
			dao.setQryValue(nqryIndex, 2, vo.getStrRoomTypeId());
			dao.setQryValue(nqryIndex, 3, vo.getStrRoomDescription());
			dao.setQryValue(nqryIndex, 4, vo.getStrRoomStatus());
			dao.setQryValue(nqryIndex, 5, vo.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 6, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 7, "1");
			dao.setQryValue(nqryIndex, 8, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 9, vo.getStrRoomId());
			dao.setQryValue(nqryIndex, 10,vo.getStrHospitalCode());
			
			dao.setQryValue(nqryIndex, 11, vo.getStrBuildingId().split("\\^")[0]);
			dao.setQryValue(nqryIndex, 12, vo.getStrBlockId());
			dao.setQryValue(nqryIndex, 13, vo.getStrFloorId());
			dao.setQryValue(nqryIndex, 14, vo.getStrNoOfBeds());
			
			dao.execute(nqryIndex, 0);
			
			strQuery = ipd.qryHandler_ipd.getQuery(1, "insert.roomConfig.8");
			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, strRoomNo);
			
			dao.execute(nqryIndex, 0);
			
			if(vo.getStrPropertyId()==null || vo.getStrPropertyId().equals(""))
			{
				System.out.println("vo prop null");
			}
			else{
			nMultiRowLength = vo.getStrPropertyId().length;
			if(nMultiRowLength>0 && !vo.getStrPropertyId()[0].equals("0"))
			{
			for(int i=0; i<nMultiRowLength ; i++)
			{
				strQuery = ipd.qryHandler_ipd.getQuery(1, "insert.roomConfig.9");
		         
				nqryIndex = dao.setQuery(strQuery);
	              
	            dao.setQryValue(nqryIndex, 1, strRoomNo);
				dao.setQryValue(nqryIndex, 2, vo.getStrPropertyId()[i]);
				dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());
				dao.setQryValue(nqryIndex, 4, "1");
				
				
				dao.execute(nqryIndex, 0);
			}
			
			}
			}
			synchronized (dao) {
				dao.fire();
				fretValue = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			fretValue = false;
			throw new Exception("RoomConfigMstDAO.insertQuery() --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return fretValue;

	}

	/**
	 * To check for Duplicate Records
	 * 
	 * @param vo - Form Object of the Current Master
	 * @return - true then record already exist else false then record saved
	 * @throws Exception
	 */
	/*public static boolean initialAddQuery(VORoomConfigMst vo) throws Exception {
		HisDAO dao = new HisDAO("ipd", "RoomConfigMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		int nCount = 0;
		WebRowSet wb = null;

		String strQuery = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.6");

		try {
			
			System.out.println();
			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, vo.getStrRoomId());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
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
			throw new Exception("RoomConfigMstDAO.initialAddQuery(vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return freturnValue;
	}
*/
	// changes by anshul on 26-08-09(last is commented)
	
	public static boolean initialAddQuery(RoomConfigMstVO vo) throws Exception {
		HisDAO dao = new HisDAO("ipd", "RoomConfigMstDAO");
		boolean freturnValue = false;
		int qryIndex;
		int qryIndex1;
		int qryIndex2;
		int qryIndex3;
		
		int count = 0;
		//String strTemp1[] =null;
		WebRowSet wb = null;
		WebRowSet wb1 = null;
		WebRowSet wb2 = null;
		WebRowSet wb3 = null;
		
		String query = "";
		String query1 = "";
		String query2 = "";
		String query3 = "";
		String strHospCode="";
		String strRoomDescArr[] =null;
	

		try {
			
			System.out.println("dao initialUpdateQuery vo.getStrRoomId()"+vo.getStrRoomId());
			strHospCode = vo.getStrHospitalCode();
		
			System.out.println("dao strHospCode- "+strHospCode);
			System.out.println("dao getStrRoomId- "+vo.getStrRoomId()); 
			
			// to get building ,block,floor id by room id n hosp code by anshul on 26-08-09
			
			//below lines are commented by Adil Wasi on 16-Feb-2012
			query1 = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.10");
			qryIndex1 = dao.setQuery(query1);
			dao.setQryValue(qryIndex1, 1, vo.getStrRoomId());
			dao.setQryValue(qryIndex1, 2, strHospCode);
			wb1 = dao.executeQry(qryIndex1);
			while (wb1.next()) {
				
				vo.setStrBuildingId(wb1.getString(1));
				vo.setStrBlockId(wb1.getString(2));
				vo.setStrFloorId(wb1.getString(3));
				
			}
			System.out.println("dao update getStrBuildingId :"+vo.getStrBuildingId());
			System.out.println("dao update getStrBlockId ::::"+vo.getStrBlockId());
			System.out.println("dao update getStrFloorId ::::"+vo.getStrFloorId());
			
			// to get ALL room idS BY  building ,block,floor id  n hosp code by anshul on 26-08-09

			query2 = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.13");
			qryIndex2 = dao.setQuery(query2);
			dao.setQryValue(qryIndex2, 1, vo.getStrBuildingId());
			dao.setQryValue(qryIndex2, 2, vo.getStrBlockId());
			dao.setQryValue(qryIndex2, 3, vo.getStrFloorId());
			dao.setQryValue(qryIndex2, 4, strHospCode);
		
			
			wb2 = dao.executeQry(qryIndex2);
			System.out.println("room id wb size-"+wb2.size());
			strRoomDescArr = new String[wb2.size()];
			int i=0;
			while (wb2.next()) {
				
				// to get ALL room DESCRIPTIONS FROM  HIPT_ROOM_CONFIG_MST BY  ROOM CODEs n hosp code by anshul on 26-08-09

				System.out.println("wb.getString(1)-"+wb2.getString(1));
				
				query3 = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.12");
				qryIndex3 = dao.setQuery(query3);
				dao.setQryValue(qryIndex3, 1, wb2.getString(1));
				dao.setQryValue(qryIndex3, 2, strHospCode);
				wb3 = dao.executeQry(qryIndex3);
				System.out.println("wb3.size-"+wb3.size());
				if(wb3.size()!=0)
				{
				while (wb3.next()) {
					strRoomDescArr[i]=wb3.getString(1);
					System.out.println("strRoomDescArr[i]-"+strRoomDescArr[i]);
				}
				
				i++;
				}
				
			}
			
			System.out.println("i"+i);
			System.out.println("vo.getStrRoomDescription().trim()-"+vo.getStrRoomDescription().trim());
			/*for(int j=0;j<i;j++)
			{
				if( vo.getStrRoomDescription().trim().equals(strRoomDescArr[j].trim()))
				{
					count++;
				}
			}*/
			
			
			
			String query5 = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.checkDuplicate.0");
			int qryIndex5 = dao.setQuery(query5);
			dao.setQryValue(qryIndex5, 1, vo.getStrRoomDescription());
			dao.setQryValue(qryIndex5, 2, vo.getStrHospitalCode());
			
			wb = dao.executeQry(qryIndex5);
			while (wb.next()) {
				count = Integer.parseInt(wb.getString(1));
			}
			System.out.println("count-"+count);
			if (count < 1) {
				freturnValue = true;
			} else {
				freturnValue = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			freturnValue = false;
			throw new Exception("RoomConfigMstDAO.initialAddQuery(vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return freturnValue;
	}

	/**
	 * retrieves and executes modify Query
	 * 
	 * @param strchk1 - Primary Keys Concatenated with '@'.
	 * @param vo - Form Object of the Current Master
	 * @throws Exception
	 */
	public static void modifyQuery(String strchk1, RoomConfigMstVO vo)
			throws Exception {
		
		HisDAO dao = null;
		String strQuery1 = new String();
		String strQuery2 = "";
		int nqryIndex;
		int nqryIndex1;
		int nqryIndex2;
        String[] strtemp1 =null;
		String[] strTemp = null;
        String strQuery = new String();
        String strProperty[] = null;
        int j=0;
		
        try {
			
        dao = new HisDAO("ipd", "RoomConfigMstDAO");
		strQuery = ipd.qryHandler_ipd.getQuery(1, "modify.roomConfig.0");		
		strQuery1 = ipd.qryHandler_ipd.getQuery(1, "modify.roomConfig.01");		
		strQuery2 = ipd.qryHandler_ipd.getQuery(1, "modify.roomConfig.02");	
		nqryIndex = dao.setQuery(strQuery);		
		strTemp = strchk1.replace('$', '#').split("#");
		nqryIndex = dao.setQuery(strQuery);
    	//dao.setQryValue(qryIndex, 1, temp[0]);
		strtemp1 = strTemp[0].replace('@', '#').split("#");		
		vo.setStrRoomNo(strtemp1[0]);
		for (int i = 0; i < strtemp1.length; i++) {										
			dao.setQryValue(nqryIndex, (i + 1), strtemp1[i]);					    
		}		
			WebRowSet web = dao.executeQry(nqryIndex); 
			while (web.next()) {
				vo.setStrRoomDescription(web.getString(1));
				vo.setStrRoomTypeId(web.getString(2));
				vo.setStrRoomStatus(web.getString(3));
				vo.setStrRemarks(web.getString(4));
				vo.setStrIsValid(web.getString(5));							
				vo.setStrRoomId(web.getString(6));
				//vo.setRoomModi(web.getString(6));
				vo.setStrRoomNo(web.getString(7));	
				vo.setStrEffectiveFrom(web.getString(8));
				vo.setStrBuildingId(web.getString(9));
				vo.setStrBlockId(web.getString(10));
				vo.setStrFloorId(web.getString(11));
				vo.setStrNoOfBeds(web.getString(12));
			}
            
            web.close();
            /*nqryIndex1 = dao.setQuery(strQuery1);
        	
            for (int i = 0; i < strtemp1.length; i++) {
    			  dao.setQryValue(nqryIndex1, (i + 1), strtemp1[i]);
    		 }
            WebRowSet web1 = dao.executeQry(nqryIndex1);
            while(web1.next()) {
				vo.setStrBuildingId(web1.getString(1));
				vo.setStrBlockId(web1.getString(2));
				vo.setStrFloorId(web1.getString(3));
				vo.setStrBuildingName(web1.getString(4));
				vo.setStrBlockName(web1.getString(5));
				vo.setStrFloorName(web1.getString(6));
				//if()
				//vo.setStrBlockDetailModi(web1.getString(5));
				//vo.setStrFloorDetailModi(web1.getString(6));
			}*/
            
            nqryIndex2 = dao.setQuery(strQuery2);
        		  dao.setQryValue(nqryIndex2, 1, vo.getStrRoomNo());
        		  dao.setQryValue(nqryIndex2, 2, vo.getStrHospitalCode());
        		  dao.setQryValue(nqryIndex2, 3,"1");
    		
            WebRowSet wb = dao.executeQry(nqryIndex2);
            strProperty = new String[wb.size()];
            while(wb.next()) {
            	
            	strProperty[j]= (wb.getString(1));
				j++;
			}
            vo.setStrPropertyId(strProperty);
            
            
		} catch (Exception e) {
			throw new Exception("RoomConfigMstDAO.modifyQuery(chk,vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;

		}

	}

	/**
	 * For Combo Query
	 * 
	 * @param strval - First variable of Combo Query
	 * @param strval1 - Second Variable of Combo Query
	 * @param vo - FormBean Object
	 * @throws Exception
	 */
	public static void comboQuery(String strval, String strval1,
			RoomConfigMstVO vo) throws Exception {

		HisDAO dao = null;
		int nqryIndex;
		String strQuery = new String();
		try {
			dao = new HisDAO("ipd", "RoomConfigMstDAO");
			strQuery = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.3");
			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, strval);
			dao.setQryValue(nqryIndex, 2, strval1);
			WebRowSet web = dao.executeQry(nqryIndex);
			while (web.next()) {
				vo.setStrBuildingId(web.getString(1));
				vo.setStrBlockId(web.getString(2));
				vo.setStrFloorId(web.getString(3));
			}
		} catch (Exception e) {
			throw new Exception("RoomConfigMstDAO.comboQuery(val,val1,vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	}

	/**
	 * updates the Current Record.
	 * 
	 * @param strchk1 - Primary Keys Concatenated with '@'.
	 * @param vo -  Form Object of the Current Master.
	 * @return -true - if Record Updated Successfully. <br>
	 * 		   false - if Record Not Updated Successfully.
	 * @throws Exception
	 */
	public static boolean updateQuery(String strchk1, RoomConfigMstVO vo)
			throws Exception {

		int nMultiRowLength = 0; 
		HisDAO dao = null;
		boolean fretValue;
		int nqryIndex;
		String strQuery = new String();
		String strtemp[] = null;
       // String strTempRoom[] = vo.getStrRoomId().split("^");
		//String strTempRoom = vo.getStrRoomId();
		//String strRoomValue[] = null;
		
		dao = new HisDAO("ipd", "RoomConfigMstDAO");
		String strtemp2[] = null;
		try {
			System.out.println("strchk1-"+strchk1);
			 strtemp = strchk1.replace('$', '#').split("#");
			  //strRoomValue = strTempRoom.replace("^","#").split("#");
			   strtemp2 = strtemp[0].replace('@', '#').split("#");
		
			strQuery = ipd.qryHandler_ipd.getQuery(1, "update.roomConfig.1");

			nqryIndex = dao.setQuery(strQuery);
		
			dao.setQryValue(nqryIndex, 1,  vo.getStrRoomId());
			dao.setQryValue(nqryIndex, 2,  vo.getStrRoomTypeId());
			dao.setQryValue(nqryIndex, 3,  vo.getStrRoomStatus());
			dao.setQryValue(nqryIndex, 4,  vo.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 5,  vo.getStrLastModifSeatId());
			dao.setQryValue(nqryIndex, 6,  vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 7,  vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 8,  vo.getStrRoomDescription());
			dao.setQryValue(nqryIndex, 9, vo.getStrBuildingId());
			dao.setQryValue(nqryIndex, 10, vo.getStrBlockId());
			dao.setQryValue(nqryIndex, 11, vo.getStrFloorId());
			dao.setQryValue(nqryIndex, 12, vo.getStrNoOfBeds());
			dao.setQryValue(nqryIndex, 13, strtemp2[0]);
			dao.setQryValue(nqryIndex, 14, strtemp2[1]);
			dao.execute(nqryIndex, 0);
			System.out.println("strtemp2[0] ROOM ID-"+strtemp2[0]);
			
			strQuery = ipd.qryHandler_ipd.getQuery(1, "insert.roomConfig.8");
			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, strtemp2[1]);
			dao.setQryValue(nqryIndex, 2, strtemp2[0]);
			
			dao.execute(nqryIndex, 0);
			
			if(vo.getStrPropertyId()==null || vo.getStrPropertyId().equals(""))
			{
				System.out.println("vo prop null");
			}
			else{
			nMultiRowLength = vo.getStrPropertyId().length;
			System.out.println("nMultiRowLength-"+nMultiRowLength);
			if(nMultiRowLength>0 && !vo.getStrPropertyId()[0].equals("0"))
			{
			for(int i=0; i<nMultiRowLength ; i++)
			{
				strQuery = ipd.qryHandler_ipd.getQuery(1, "insert.roomConfig.9");
		         
				nqryIndex = dao.setQuery(strQuery);
	              
	            dao.setQryValue(nqryIndex, 1, strtemp2[0]);
				dao.setQryValue(nqryIndex, 2, vo.getStrPropertyId()[i]);
				dao.setQryValue(nqryIndex, 3, strtemp2[1]);
				dao.setQryValue(nqryIndex, 4, "1");
				
				
				dao.execute(nqryIndex, 0);
			}
			}
			
			}
			synchronized (dao) {
				dao.fire();
				fretValue = true;
			}
		} catch (Exception e) {
			throw new Exception("RoomConfigMstDAO.updateQuery(chk,vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return fretValue;
	}
	
	
	/**
	 * For initial update
	 * 
	 * @param chk - Primary Keys Concatenated with '@'.
	 * @param vo - FormBean Object
	 * @return boolean Value true or false
	 */
	public static boolean initialUpdateQuery(String chk, RoomConfigMstVO vo) {
		HisDAO dao = new HisDAO("ipd", "RoomConfigMstDAO");
		String strtemp[] = null;
		boolean returnValue = false;
		int qryIndex1;
		int qryIndex2;
		int qryIndex3;
		
		int count = 0;
		String strTemp1[] =null;
		WebRowSet wb1 = null;
		WebRowSet wb2 = null;
		WebRowSet wb3 = null;
		
		String query1 = "";
		String query2 = "";
		String query3 = "";
	
		String strHospCode="";
		String strRoomDescArr[] =null;

		try {
			System.out.println("dao initialUpdateQuery vo.getStrRoomId()"+vo.getStrRoomId());
			
			strtemp = chk.replace('$', '#').split("#");
			strTemp1 = strtemp[0].replace('@', '#').split("#");
			
			strHospCode = strTemp1[1];
			System.out.println("dao strHospCode- "+strHospCode);
			System.out.println("dao getStrRoomId- "+vo.getStrRoomId()); 
			
			// to get building ,block,floor id by room id n hosp code by anshul on 26-08-09
			/*
			query1 = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.10");
			qryIndex1 = dao.setQuery(query1);
			dao.setQryValue(qryIndex1, 1, vo.getStrRoomId());
			dao.setQryValue(qryIndex1, 2, strHospCode);
			wb1 = dao.executeQry(qryIndex1);
			while (wb1.next()) {
				
				vo.setStrBuildingId(wb1.getString(1));
				vo.setStrBlockId(wb1.getString(2));
				vo.setStrFloorId(wb1.getString(3));
				
			}
			System.out.println("dao update getStrBuildingId"+vo.getStrBuildingId());
			System.out.println("dao update getStrBuildingId"+vo.getStrBlockId());
			System.out.println("dao update getStrBuildingId"+vo.getStrFloorId());
			
			// to get ALL room idS BY  building ,block,floor id  n hosp code by anshul on 26-08-09

			query2 = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.11");
			qryIndex2 = dao.setQuery(query2);
			dao.setQryValue(qryIndex2, 1, vo.getStrBuildingId());
			dao.setQryValue(qryIndex2, 2, vo.getStrBlockId());
			dao.setQryValue(qryIndex2, 3, vo.getStrFloorId());
			dao.setQryValue(qryIndex2, 4, strHospCode);
			dao.setQryValue(qryIndex2, 5, vo.getStrRoomId());
			
			wb2 = dao.executeQry(qryIndex2);
			System.out.println("room id wb size-"+wb2.size());
			strRoomDescArr = new String[wb2.size()];
			int i=0;
			while (wb2.next()) {
				
				// to get ALL room DESCRIPTIONS FROM  HIPT_ROOM_CONFIG_MST BY  ROOM CODEs n hosp code by anshul on 26-08-09

				System.out.println("wb.getString(1)-"+wb2.getString(1));
				
				query3 = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.12");
				qryIndex3 = dao.setQuery(query3);
				dao.setQryValue(qryIndex3, 1, wb2.getString(1));
				dao.setQryValue(qryIndex3, 2, strHospCode);
				wb3 = dao.executeQry(qryIndex3);
				System.out.println("wb3.size-"+wb3.size());
				if(wb3.size()!=0)
				{
				while (wb3.next()) {
					strRoomDescArr[i]=wb3.getString(1);
					System.out.println("strRoomDescArr[i]-"+strRoomDescArr[i]);
				}
				
				i++;
				}
				
			}
			
			System.out.println("i"+i);
			System.out.println("vo.getStrRoomDescription().trim()-"+vo.getStrRoomDescription().trim());
			for(int j=0;j<i;j++)
			{
				if( vo.getStrRoomDescription().trim().equals(strRoomDescArr[j].trim()))
				{
					count++;
				}
			}*/
			
			
			
			String query5 = ipd.qryHandler_ipd.getQuery(1, "select.roomConfig.8");
			int qryIndex5 = dao.setQuery(query5);
			dao.setQryValue(qryIndex5, 1, strTemp1[0]);
			dao.setQryValue(qryIndex5, 2, vo.getStrRoomDescription());
			dao.setQryValue(qryIndex5, 3, strTemp1[1]);
			
			wb1 = dao.executeQry(qryIndex5);
			while (wb1.next()) {
				count = Integer.parseInt(wb1.getString(1));
			}
			System.out.println("count-"+count);
			if (count < 1) {
				returnValue = true;
			} else {
				returnValue = false;
			}

		} catch (Exception e) {
			returnValue = false;
			vo.setStrMsg("exception");
			vo.setStrErr("RoomConfigMstDAO.initialupdatequery()-"+e);
			new HisException("ipd", "RoomConfigMstDAO.initialupdatequery()", e
					.getMessage());
			e.printStackTrace();
		} finally {
			dao.free();
			dao = null;
		}
		return returnValue;
	}

	
/*
	public static boolean initialUpdateQuery(String strchk, VORoomConfigMst vo)
			throws Exception {
		HisDAO dao = new HisDAO("ipd", "RoomConfigMstDAO");
		boolean freturnValue = false;

		int nqryIndex;
		int nCount = 0;

		WebRowSet wb = null;

		String strQuery = ipd.qryHandler_ipd.getQuery(1,
				"select.roomConfigCorr.0");
		String strTemp[] = strchk.replace('@', '#').split("#");

		String strtemp2[] = strTemp[2].replace('$', '#').split("#");

		try {
			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, strTemp[0]);
			dao.setQryValue(nqryIndex, 2, strTemp[1]);
			dao.setQryValue(nqryIndex, 3, strtemp2[0]);
			dao.setQryValue(nqryIndex, 4, vo.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 5, vo.getStrEffectiveTo());
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
			throw new Exception(
					"RoomConfigMstDAO.initialUpdateQuery(strchk,vo) --> "
							+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return freturnValue;
	}

	public static boolean updateModificationQuery(String strchk1,
			VORoomConfigMst vo) throws Exception {

		HisDAO dao = null;
		boolean fretValue = false;

		int nqryIndex;
		int nqryIndex1;

		String strQuery = new String();
		String strQuery1 = new String();
		String strTemp[] = strchk1.replace('@', '#').split("#");

		dao = new HisDAO("ipd", "RoomConfigMstDAO");

		try {
			try {
				strQuery1 = ipd.qryHandler_ipd.getQuery(1,
						"select.roomConfigModif.2");

				nqryIndex1 = dao.setQuery(strQuery1);
				dao.setQryValue(nqryIndex1, 1, vo.getStrEffectiveFrom());
				dao.setQryValue(nqryIndex1, 2, strTemp[0]);
				dao.setQryValue(nqryIndex1, 3, strTemp[1]);
				dao.setQryValue(nqryIndex1, 4, vo.getStrEffectiveFrom());
				dao.execute(nqryIndex1, 0);
			} catch (Exception e) {
				throw new Exception(
						"RoomConfigMstDAO.updateModificationQuery(Before Insertion) --> "
								+ e.getMessage());
			}

			strQuery = ipd.qryHandler_ipd.getQuery(1,
					"modification.roomConfig.1");

			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, strTemp[0]);
			dao.setQryValue(nqryIndex, 2, vo.getStrRoomTypeId());
			dao.setQryValue(nqryIndex, 3, strTemp[0]);
			dao.setQryValue(nqryIndex, 4, strTemp[1]);
			dao.setQryValue(nqryIndex, 5, vo.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 6, vo.getStrEffectiveTo());
			dao.setQryValue(nqryIndex, 7, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 8, vo.getStrIsValid());
			dao.execute(nqryIndex, 0);
			synchronized (dao) {
				dao.fire();
				fretValue = true;
			}

		} catch (Exception e) {
			throw new Exception(
					"RoomConfigMstDAO.updateModificationQuery(strchk1,vo) --> "
							+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return fretValue;
	}

	public static boolean initialModifcationUpdateQuery(String strchk,
			VORoomConfigMst vo) throws Exception {
		HisDAO dao = new HisDAO("ipd", "RoomConfigMstDAO");

		boolean freturnValue = false;

		int nqryIndex;
		int nqueryIndex1;
		int nCount = 0;
		int nCount1 = 0;

		WebRowSet wb1 = null;
		WebRowSet wb = null;

		String strQuery = ipd.qryHandler_ipd.getQuery(1,
				"select.roomConfigModif.0");
		String strQuery1 = ipd.qryHandler_ipd.getQuery(1,
				"select.roomConfigModif.1");
		String strTemp[] = strchk.replace('@', '#').split("#");

		try {

			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, strTemp[0]);
			dao.setQryValue(nqryIndex, 2, strTemp[1]);
			dao.setQryValue(nqryIndex, 3, vo.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 4, vo.getStrEffectiveTo());
			dao.setQryValue(nqryIndex, 5, vo.getStrEffectiveTo());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				nCount = Integer.parseInt(wb.getString(1));
			}
			if (nCount == 0) {
				try {

					nqueryIndex1 = dao.setQuery(strQuery1);

					dao.setQryValue(nqueryIndex1, 1, strTemp[0]);
					dao.setQryValue(nqueryIndex1, 2, strTemp[1]);
					dao.setQryValue(nqueryIndex1, 3, vo.getStrEffectiveFrom());
					dao.setQryValue(nqueryIndex1, 4, vo.getStrEffectiveTo());
					dao.setQryValue(nqueryIndex1, 5, vo.getStrEffectiveTo());
					wb1 = dao.executeQry(nqueryIndex1);
					while (wb1.next()) {
						nCount1 = Integer.parseInt(wb1.getString(1));

					}
				} catch (Exception e) {
					throw new Exception(
							"RoomConfigMstDAO.initialModifcationUpdateQuery(strchk1,vo) --> "
									+ e.getMessage());
				}
				if (nCount1 == 0) {
					freturnValue = true;
				} else {
					freturnValue = false;
				}
			} else {
				freturnValue = false;
			}
		} catch (Exception e) {
			throw new Exception(
					"RoomConfigMstDAO.initialModifcationUpdateQuery(strchk1,vo) --> "
							+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return freturnValue;
	}
*/
}