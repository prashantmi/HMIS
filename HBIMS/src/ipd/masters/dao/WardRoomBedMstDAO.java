package ipd.masters.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import ipd.qryHandler_ipd;
import ipd.masters.controller.hlp.WardRoomBedMstHLP;
import ipd.masters.vo.WardRoomBedMstVO;

import javax.sql.rowset.WebRowSet;

public class WardRoomBedMstDAO {
	
	/**
	 * This function is used to insert new bed into particular ward
	 * @param vo
	 * @return true when new record is successfully inserted otherwise return false
	 * @throws Exception
	 */
	public static boolean insertData(WardRoomBedMstVO vo) throws Exception {
		boolean retValue = false;
		HisDAO hisdao = null;
		int index2=0;
		String temp1[]=null;
		int nLength = 0;

	try {

		
		int nProcIndex = 0;
		String val="0";
			String str1 = vo.getStrWardCode();
			String str2=vo.getStrRoomId();
			String roomCode=str2.replace("^","#").split("#")[0];
			String str3bedName[]=vo.getBedName();
			String str3bedType[]=vo.getBedType();
			String str3bedStatus[]=vo.getBedStatus();
			//spliting ward code
			//String temp[]=str1.replace('^', '#').split("#");

	nLength = str3bedName.length;
	//System.out.println("str1"+str1+"1"+str2+"3"+roomCode+"4"+vo.getBedName()+"5"+vo.getBedType()+"6"+vo.getBedStatus()+"7"+nLength+"8"+vo.getStrCheckedHidden().length);

			for(int i=0;i<nLength;i++)
			{
					hisdao = new HisDAO("ipd", "DAOWardRoomBedMst");
					String strProcName = "{? = call ipd_mst.get_bed_code(?::numeric,?::numeric,?::numeric)}";
					nProcIndex = hisdao.setFunction(strProcName);
					hisdao.setFuncInValue(nProcIndex, 2, str1);
					hisdao.setFuncInValue(nProcIndex, 3, roomCode);
					hisdao.setFuncInValue(nProcIndex, 4, vo.getStrHospitalCode());
					hisdao.setFuncOutValue(nProcIndex, 1);
					hisdao.executeFunction(nProcIndex);
					val=hisdao.getFuncString(nProcIndex);
					if(!(str3bedName[i].trim().equals("")))
					{
						String query2 = qryHandler_ipd.getQuery(1, "insert.wardroombed.1");
						index2 = hisdao.setQuery(query2);
						hisdao.setQryValue(index2, 1, str1);
						hisdao.setQryValue(index2, 2, roomCode);
						hisdao.setQryValue(index2, 3, val);
						hisdao.setQryValue(index2, 4, str3bedType[i]);
						hisdao.setQryValue(index2, 5, str3bedStatus[i]);
						hisdao.setQryValue(index2, 6, vo.getStrEffectiveFrom());
						hisdao.setQryValue(index2, 7, vo.getStrRemarks());
						hisdao.setQryValue(index2, 8, vo.getStrSeatId());
						hisdao.setQryValue(index2, 9, str3bedName[i]);
						hisdao.setQryValue(index2, 10, vo.getStrHospitalCode());
						hisdao.setQryValue(index2, 11, vo.getStrIsSharable()[i]);
						hisdao.execute(index2,0);
						if(vo.getStrCheckedHidden().length>0 )
						{	
							
								temp1=vo.getStrCheckedHidden()[i].replace('^', '#').split("#");
								for(int j=0;j<temp1.length;j++){
								if(temp1[j].equals("") || temp1[j]==null)
								{
								}
								else{
									query2 = qryHandler_ipd.getQuery(1, "insert.wardroombed.2");
									index2 = hisdao.setQuery(query2);
									hisdao.setQryValue(index2, 1, str1);//ward code
									hisdao.setQryValue(index2, 2, roomCode); // room no
									hisdao.setQryValue(index2, 3, val); // bed code
									hisdao.setQryValue(index2, 4, temp1[j]); // property id
									hisdao.setQryValue(index2, 5, vo.getStrHospitalCode());
									hisdao.execute(index2,0);
									}
								
								}
						}
						synchronized (hisdao) {
							hisdao.fire();
							retValue = true;
						}	
					}
						
					}
					
			
			
			/*
			 * insert into HIPT_BED_PROPERTY_DTL(HGNUM_WARD_CODE, HIPNUM_ROOM_NO, HGNUM_BED_CODE, \
						HIPNUM_PROPERTY_ID, GNUM_HOSPITAL_CODE, GNUM_ISVALID) \
						values (?,?,?,?,,?,1)	
			 */
			
		

		} catch (Exception e) {
			e.printStackTrace();
			retValue = false;
			new HisException("IPD", "Ward Room Bed Master.getRoom","ipd.masters.WardRoomBedMstDAO.insertData() --> ");

		} /*finally {
			hisdao.free();
		}*/
		return retValue;
	}
	public static void getRoomBlock(WardRoomBedMstVO vo)
	{
		
		String cmbstr2 = "";
		String strQuery4 ;
		WebRowSet wb1 = null;
		String hcode=vo.getStrHospitalCode();
		strQuery4 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.3");
		HisDAO dao = new HisDAO("ipd", "WardRoomBedMstDAO");
		int index;
		try {
			index=dao.setQuery(strQuery4);
			dao.setQryValue(index, 1, hcode);
			//dao.setQryValue(index, 2, "999"); // as per CR 13 jan 2012 ward code 999 always
			wb1=dao.executeQry(index);
			HisUtil hisutil = new HisUtil("ipd", "WardRoomBedMstVO");
			cmbstr2=hisutil.getOptionValue(wb1, "0", "0^Select Value", false);
			vo.setStrRoomCombo(cmbstr2);
			} 
		
		catch (Exception e) {
			new HisException("IPD", "Room Bed Master.getRoom","ipd.masters.WardRoomBedMstDAO.getRoomBlock() --> ");
		} finally {
			
		}	
	
	} 	
/*
	public static boolean initialUpdate(String strchk1, VOWardRoomBedMst vo)
			throws Exception {
		HisDAO dao = new HisDAO("ipd", "WardRoomBedMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		String strTemp[] = strchk1.replace('@', '#').split("#");
		WebRowSet wb = null;

		String strquery = ipd.qryHandler_ipd.getQuery(1, "get.wardroombed.1");

		try {

			dao = new HisDAO("ipd", "WardRoomBedMstDAO");

			String strTemp2[] = strTemp[2].replace('$', '#').split("#");
			String strTemp3[] = strTemp[3].replace('$', '#').split("#");

			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, strTemp[0]);
			dao.setQryValue(nqryIndex, 2, strTemp[1]);
			dao.setQryValue(nqryIndex, 3, strTemp2[0]);
			dao.setQryValue(nqryIndex, 4, strTemp3[0]);
			wb = dao.executeQry(nqryIndex);

			while (wb.next()) {
				//vo.setStrLastModSeatId(wb.getString(1));
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount != 0) {
				freturnValue = true;
			} else {
				freturnValue = false;
			}
		} catch (Exception e) {
			freturnValue = false;
			throw new Exception("DAODischargeTypeMst.initialAddQuery(vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return freturnValue;
	}
*/
/**
 * This function is used to update particular selected bed 
 * @param strchk1
 * @param vo
 * @return
 * @throws Exception
 */
	public static boolean updateData(String strchk1, WardRoomBedMstVO vo)
			throws Exception {

		boolean retvalue = true;
		HisDAO dao = null;
		String temp[] = null;

		int qryIndex;
		String query = new String();
		String [] strTemp=strchk1.replace('@', '#').split("#");
		try {
			dao = new HisDAO("ipd", "DAOWardRoomBedmST");			
			query = ipd.qryHandler_ipd.getQuery(1, "update.wardroombed.2");
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, vo.getStrEffectiveFrom());
			dao.setQryValue(qryIndex, 2, vo.getStrBedTypeName());
			dao.setQryValue(qryIndex, 3, vo.getStrBedStatusName());
			dao.setQryValue(qryIndex, 4, vo.getStrBedName2());
			dao.setQryValue(qryIndex, 5, vo.getStrRemarks());
			dao.setQryValue(qryIndex, 6, vo.getStrValid());
			dao.setQryValue(qryIndex, 7, vo.getStrSeatId());
			dao.setQryValue(qryIndex, 8, strTemp[2]);
			dao.setQryValue(qryIndex, 9, strTemp[1]);
			dao.setQryValue(qryIndex, 10, strTemp[0]);
			dao.setQryValue(qryIndex, 11, vo.getStrHospitalCode());
			dao.execute(qryIndex, 0);
			
			query = ipd.qryHandler_ipd.getQuery(1, "delete.wardroombed.3");
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, strTemp[2]);
			dao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
			
			dao.execute(qryIndex, 0);
			
			temp = vo.getStrModifyChkProperties().replace("^", "#").split("#");
			if(vo.getStrModifyChkProperties()==null  || vo.getStrModifyChkProperties().equals(""))
			{
			}
			else{
				for(int j=0; j<temp.length;j++)
				{	
					temp = vo.getStrModifyChkProperties().replace("^", "#").split("#");
					
					query = qryHandler_ipd.getQuery(1, "insert.wardroombed.2");
					qryIndex = dao.setQuery(query);
					dao.setQryValue(qryIndex, 1, strTemp[0]);//ward code
					dao.setQryValue(qryIndex, 2, strTemp[1]); // room no
					dao.setQryValue(qryIndex, 3, strTemp[2]); // bed code
					dao.setQryValue(qryIndex, 4, temp[j]); // property id
					dao.setQryValue(qryIndex, 5, vo.getStrHospitalCode());
					dao.execute(qryIndex,0);
				}
				
				
			}			
			synchronized (dao) {
				dao.fire();
				retvalue=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retvalue = false;
			new HisException("IPD", "Ward Room Bed Master.getRoom","ipd.masters.WardRoomBedMstDAO.updateData() --> ");

		} finally {

			dao.free();
			dao = null;
		}
		return retvalue;
		

	}
/**
 * This function select beds for particular ward and room display details on modify page
 * @param strchk1
 * @param vo
 * @return
 * @throws Exception
 */
	public static boolean modifyQuery(String strchk1, WardRoomBedMstVO vo)
			throws Exception {
		boolean retValue = false;
		HisDAO dao = new HisDAO("ipd", "WardRoomBedMstDAO");

		int nqryIndex1;
		String strQuery1 = new String();
		String strTemp[] = strchk1.replace('@', '#').split("#");
		vo.setStrBedTypeName(strTemp[2]);
		dao = new HisDAO("ipd", "WardRoomBedMstDAO");
		try {
			// get ward name
			strQuery1 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.16");
			nqryIndex1 = dao.setQuery(strQuery1);
			dao.setQryValue(nqryIndex1, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex1, 2, strTemp[0]);
			WebRowSet web1 = dao.executeQry(nqryIndex1);
			if (web1.next()) {
				vo.setStrWardName(web1.getString(1));
			}
			
			
			//get  Room name on the basis of room no
			strQuery1 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.15");
			nqryIndex1 = dao.setQuery(strQuery1);
			dao.setQryValue(nqryIndex1, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex1, 2, strTemp[1]);
			web1 = dao.executeQry(nqryIndex1);
			if (web1.next()) {
				vo.setStrRoomName(web1.getString(1));
			}
			
			// get data on modification page
			
			strQuery1 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.17");
			nqryIndex1 = dao.setQuery(strQuery1);
			dao.setQryValue(nqryIndex1, 1, strTemp[0]);
			dao.setQryValue(nqryIndex1, 2, strTemp[1]);
			dao.setQryValue(nqryIndex1, 3, strTemp[2]);
			dao.setQryValue(nqryIndex1, 4, vo.getStrHospitalCode());
			web1 = dao.executeQry(nqryIndex1);
			if (web1.next()) {
				String[] arr={web1.getString(9)};
				vo.setStrBedTypeNameModi(web1.getString(1));
				vo.setStrBedStatusNameModi(web1.getString(2));
				vo.setStrRemarks(web1.getString(3));
				vo.setStrBedName(web1.getString(5));
				vo.setStrDate(web1.getString(6));
				vo.setStrValid(web1.getString(4));
				vo.setStrIsSharable(arr);
			}
			vo.setStrWardCode(strTemp[0]);
			vo.setStrRoomId(strTemp[1]);
			vo.setStrBedCode(strTemp[2]);
			retValue = true;
		} catch (Exception e) {

			retValue = false;
			new HisException("IPD", "Ward Room Bed Master.getRoom","ipd.masters.WardRoomBedMstDAO.modifyQuery() --> ");

		} finally {
			dao.free();
			dao = null;
		}
		return retValue;
	}

	/**
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getData(WardRoomBedMstVO vo) throws Exception {
		HisDAO dao = null;
		WebRowSet web4 = null;
		int nqryIndex4;

		String strQuery4 = new String();
		dao = new HisDAO("ipd", "WardRoomBedMstDAO");
		try {
			strQuery4 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.61");
			nqryIndex4 = dao.setQuery(strQuery4);
			web4 = dao.executeQry(nqryIndex4);
			
		}

		catch (Exception e) {
			new HisException("IPD", "Ward Room Bed Master.getRoom","ipd.masters.WardRoomBedMstDAO.getData() --> ");
		}
		return web4;
	}
	/**
	 * This function brings room details with corresponding building and block details 
	 * @param pk
	 * @param vo
	 * @return
	 */
	public static String getRoomBlock(String pk,WardRoomBedMstVO vo)
	{
		String temp[];
		// split  primary key of ward combo
		temp=pk.replace('^', '#').split("#");
		String cmbstr2 = "";
		String strQuery4 ;
		WebRowSet wb1 = null;
		vo.setStrBuildingBlock(temp[1]);
		vo.setStrBedInWard(temp[3]);
		vo.setStrBedLimit(temp[2]);
		String hcode=vo.getStrHospitalCode();
		strQuery4 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.3");
		HisDAO dao = new HisDAO("ipd", "WardRoomBedMstDAO");
		int index;
		try {
			index=dao.setQuery(strQuery4);
			dao.setQryValue(index, 1, hcode);
			dao.setQryValue(index, 2, temp[0]);        //ward code
			wb1=dao.executeQry(index);
			HisUtil hisutil = new HisUtil("ipd", "VOWardRoomBedMst");
			cmbstr2=hisutil.getOptionValue(wb1, "0", "0^Select Value", false);
			} 
		
		catch (Exception e) {
			new HisException("IPD", "Ward Room Bed Master.getRoom","ipd.masters.WardRoomBedMstDAO.getRoomBlock() --> ");
		} finally {
			/*if (wb1 != null) {
				//web.close();
				//web = null;
			}*/
			
		}	
	return cmbstr2;	}
	/**
	 * This function select bed details on the basis of ward code and display result on add page
	 * @param pk1
	 * @param pk2
	 * @return
	 */
	public static String getBedView(String pk1,WardRoomBedMstVO vo)
	{
		HisDAO dao = null;
		int nqryIndex11;
		String strQuery11 = new String();
		String cmbstr="";
		
		dao = new HisDAO("ipd", "WardRoomBedMstDAO");
		try {
			// /////////////////GET WARD ID FROM HIPT_WARD_MST//////////////////
			strQuery11 = ipd.qryHandler_ipd
					.getQuery(1, "select.wardroombed.13");
			nqryIndex11 = dao.setQuery(strQuery11);
			dao.setQryValue(nqryIndex11, 1,vo.getStrHospitalCode());
			//dao.setQryValue(nqryIndex11, 2, temp[0]);
			dao.setQryValue(nqryIndex11, 2, pk1);//For CR dated 13 Jan 2012 
			dao.setQryValue(nqryIndex11, 3, vo.getStrRoomId());//For CR dated 13 Jan 2012
			WebRowSet web1 = dao.executeQry(nqryIndex11);
			WardRoomBedMstHLP hlp=new WardRoomBedMstHLP();
			cmbstr=hlp.getBedDetails(web1);
			// append  max limit of bed of a ward
			//cmbstr=cmbstr+"^"+temp[2];
		}

		catch (Exception e) {
			new HisException("IPD", "Ward Room Bed Master.getRoom","ipd.masters.WardRoomBedMstDAO.getBedView) --> ");
		}
		return cmbstr;
	}
	
	/*public static String createView(String chk,VOWardRoomBedMst vo) throws Exception
	{
		try
		{
			boolean retValue = false;
			HisDAO dao = new HisDAO("ipd", "WardRoomBedMstDAO");

			int nqryIndex1;
			
			String strQuery1 = new String();

			//System.out.println("chkk---view"+chk);
			String strTemp[] = chk.replace('@', '#').split("#");
			//System.out.println("strTemp[]--->"+strTemp[0]);
			//System.out.println("strTemp[]--->"+strTemp[1]);
			//System.out.println("strTemp[]--->"+strTemp[2]);
			//System.out.println("strTemp[]--->"+strTemp[3]);
			strQuery1 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.16");
			//System.out.println("strQuery1----->"+strQuery1);
			nqryIndex1 = dao.setQuery(strQuery1);
			dao.setQryValue(nqryIndex1, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex1, 2, strTemp[0]);
			WebRowSet web1 = dao.executeQry(nqryIndex1);
			if (web1.next()) {
				vo.setStrWardName(web1.getString(1));
			}
			//System.out.println("successfully executed");
			
			//get  Room name on the basis of room no
			strQuery1 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.15");
			//System.out.println("strQuery1----->"+strQuery1);
			nqryIndex1 = dao.setQuery(strQuery1);
			dao.setQryValue(nqryIndex1, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex1, 2, strTemp[1]);
			web1 = dao.executeQry(nqryIndex1);
			//////////////////////////////Bed type
			strQuery1 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.20");
			System.out.println("strQuery1----->"+strQuery1);
			nqryIndex1 = dao.setQuery(strQuery1);
			dao.setQryValue(nqryIndex1, 1, strTemp[2]);
			dao.setQryValue(nqryIndex1, 2,  vo.getStrHospitalCode());
			web1 = dao.executeQry(nqryIndex1);
			if (web1.next()) {
				System.out.println("qweewew----------->"+web1.getString(1));
			}
			
			
			//////////////////////////////////
			if (web1.next()) {
				vo.setStrRoomName(web1.getString(1));
			}
			//System.out.println("Hello1");
			strQuery1 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.19");
			//System.out.println("strQuery1----->"+strQuery1);
			nqryIndex1 = dao.setQuery(strQuery1);
			dao.setQryValue(nqryIndex1, 1, strTemp[0]);
			dao.setQryValue(nqryIndex1, 2, strTemp[1]);
			dao.setQryValue(nqryIndex1, 3, strTemp[2]);
			dao.setQryValue(nqryIndex1, 4, vo.getStrHospitalCode());
			web1 = dao.executeQry(nqryIndex1);
			if (web1.next()) {
				vo.setStrBedName2(web1.getString(1));
				vo.setStrDate(web1.getString(3));
				vo.setStrValid(web1.getString(2));
				vo.setStrRemarks(web1.getString(4));
				vo.setStrBedTypeName(web1.getString(5));
				vo.setStrBedStatusName(web1.getString(6));
				
			}
		}
		catch(Exception e)
		{
			
			new HisException("ipd.WardRoomBedMstDAO.modifyQuery","VOWardRoomBedMst",e.getMessage());
		}
		return null;
	}*/
	
	/**
	 * This function brings room details with corresponding building and block details 
	 * @param pk
	 * @param vo
	 * @return
	 */
	public static String getPropertyPopup(String room,WardRoomBedMstVO vo)
	{
		/*String temp[];
		// split  primary key of ward combo
		temp=room.replace('^', '#').split("#");*/
		String cmbstr2 = "";
		String strQuery4 ;
		WebRowSet wb1 = null;
		/*vo.setStrBuildingBlock(temp[1]);
		vo.setStrBedInWard(temp[3]);
		vo.setStrBedLimit(temp[2]);*/
		String hcode=vo.getStrHospitalCode();
		strQuery4 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.searchProperty.1");
		HisDAO dao = new HisDAO("ipd", "WardRoomBedMstDAO");
		int index;
		
		try {
			index=dao.setQuery(strQuery4);
			dao.setQryValue(index, 1, hcode);
			dao.setQryValue(index, 2, vo.getStrRoomId());        //ward code
			wb1=dao.executeQry(index);
			vo.setPropertyDetails(wb1);
			} 		
		catch (Exception e) {
			new HisException("IPD", "Ward Room Bed Master.getRoom","ipd.masters.WardRoomBedMstDAO.getRoomBlock() --> ");
		} finally {
			/*if (wb1 != null) {
				//web.close();
				//web = null;
			}*/
		}	
	return cmbstr2;	
	}
	
	/**
	 * This function brings room details with corresponding building and block details 
	 * @param pk
	 * @param vo
	 * @return
	 */
	public static String getBedProperty(WardRoomBedMstVO vo)
	{
		/*String temp[];
		// split  primary key of ward combo
		temp=room.replace('^', '#').split("#");*/
		String cmbstr2 = "";
		String strQuery4 ;
		WebRowSet wb1 = null;
		/*vo.setStrBuildingBlock(temp[1]);
		vo.setStrBedInWard(temp[3]);
		vo.setStrBedLimit(temp[2]);*/
		//String hcode=vo.getStrHospitalCode();
		strQuery4 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.property");
		HisDAO dao = new HisDAO("ipd", "WardRoomBedMstDAO");
		int index;
		
		try {
			index=dao.setQuery(strQuery4);
			dao.setQryValue(index, 1, vo.getStrWardCode());
			dao.setQryValue(index, 2, vo.getStrRoomId());
			dao.setQryValue(index, 3, vo.getStrBedCode());
			dao.setQryValue(index, 4, vo.getStrHospitalCode());    
			wb1=dao.executeQry(index);
			vo.setStrPropertyIdWS(wb1);
			} 
		
		catch (Exception e) {
			new HisException("IPD", "Ward Room Bed Master.getRoom","ipd.masters.WardRoomBedMstDAO.getRoomBlock() --> ");
		} finally {
			/*if (wb1 != null) {
				//web.close();
				//web = null;
			}*/
		}	
	return cmbstr2;	
	}
}
