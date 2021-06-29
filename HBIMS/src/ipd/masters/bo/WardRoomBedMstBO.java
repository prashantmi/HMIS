package ipd.masters.bo;

import ipd.masters.controller.hlp.WardRoomBedMstHLP;
import ipd.masters.dao.WardRoomBedMstDAO;
import ipd.masters.vo.WardRoomBedMstVO;
import hisglobal.exceptions.HisException;

public class WardRoomBedMstBO 
{
	/**
	 * This function is invoke WardRoomBedMstDAO's insertData() 
	 * @param vo
	 * @return if return value is true it will show message record saved successfully otherwise show an error message record is not saved successfully
	 */
	public String InsertRecord(WardRoomBedMstVO vo) {
		String strTarget = "add";
		boolean fretValue = true;
		String strErrmsg = "";
		String strmsgText = "";
		String strMsg = ""; 
	
	try {
		   	 fretValue = true;
			 fretValue = WardRoomBedMstDAO.insertData(vo);
			 if (fretValue) {
				strMsg = "Record saved successfully!";
				vo.setStrMsg(strMsg);
			}
		   else
		   {
			  throw new Exception();
		   }
		   
		} catch (Exception e) {
			e.printStackTrace();
			fretValue = false;
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->WardRoomBed Master", "WardRoomBedMstBO-->InsertRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			strmsgText = "ipd.masters.BOBedStatusMst.InsertRecord(vo) --> "
					+ e.getMessage();
			new HisException("IPD", "Bed Status Master.Add", strmsgText);
			
			strErrmsg = "Record Not Saved!!!";
			vo.setStrErrorMsg(strErrmsg); 
		}

		
		return strTarget;
	}
	

	/*public boolean initialAdd(VOWardRoomBedMst vo) throws Exception// Check
																		// for
																		// Existing
																		// Duplicate
																		// Record
	{
		boolean fretvalue = true;
		try {
			// System.out.println("In initialAdd(BO) (SAVE-II)");
//			fretvalue = WardRoomBedMstDAO.initialAddQuery(vo);
//			 System.out.println("retvalue in bo =>"+fretvalue);
		} catch (Exception e) {
			throw new Exception("WardRoomBedMstBO.initialAdd() -->"
					+ e.getMessage());
		}
		return fretvalue;

	}*/
	
	
	
	
	/**
	 * Invoke WardRoomBedMstDAO's modifyQuery()
	 * @param chk1
	 * @param vo
	 */

	public void modifyRecord(String chk1, WardRoomBedMstVO vo)  {
		String res ="";
		try{
		WardRoomBedMstDAO.modifyQuery(chk1, vo);
		WardRoomBedMstDAO.getPropertyPopup(vo.getStrRoomId(),vo);
		WardRoomBedMstDAO.getBedProperty(vo);
		WardRoomBedMstHLP hlp=new WardRoomBedMstHLP();
		 res=hlp.showModifyProperty(vo);
		 vo.setStrmodProperties(res);
		 
		
		}catch(Exception e){
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->WardRoomBed Master", "WardRoomBedMstBO-->modifyRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
	    }		
		

	}
	public void  getRoom(WardRoomBedMstVO vo) throws Exception
	{
		
		try 
		{
			  WardRoomBedMstDAO.getRoomBlock(vo);
		}
		catch(Exception e)
		{
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->WardRoomBed Master", "BOWardRoomBedMst-->getRoom()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}	
		
			
		
	}
/**
 * Invoke WardRoomBedMstDAO's updateData()
 * @param chk1
 * @param vo
 * @return if return is true it forward control to list page otherwise show error message record is not modified successfully
 */
	public String updateRecord(String chk1, WardRoomBedMstVO vo) {
		String strtarget = "";
		boolean fretValue;
	//	String strmsgText = "";
		try {
			  
				fretValue = WardRoomBedMstDAO.updateData(chk1, vo);
				if (!fretValue) {

					String errmsg = "Record not modified successfully!";
					vo.setStrErrorMsg(errmsg);
					strtarget = "modify";
				} else {

					strtarget = "list";
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			fretValue = false;
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->WardRoomBed Master", "WardRoomBedMstBO-->updateRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			String errmsg = "Record not modified successfully!";
			vo.setStrErrorMsg(errmsg);
		}

		return strtarget;
	}
	/**
	 * This function invoke WardRoomBedMstDAO's getRoomBlock()
	 * @param pk
	 * @param vo
	 * @return its return type String i.e. Bring room details with corresponding building and block 
	 * @throws Exception
	 */
	public String  getRoom(String pk,WardRoomBedMstVO vo) throws Exception
	{
		String res="";
		try 
		{
			 res= WardRoomBedMstDAO.getRoomBlock(pk,vo);
		}
		catch(Exception e)
		{
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->WardRoomBed Master", "WardRoomBedMstBO-->getRoom()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}	
		
			
		return res;
	}
	/**
	 * Invoke WardRoomBedMstDAO's getBedView()
	 * @param pk1
	 * @param pk2
	 * @return return String i.e. it contains all bed details that already exist for selected ward and it corresponding room
	 * @throws Exception
	 */
	public String getBedDetail(String pk1,WardRoomBedMstVO vo) throws Exception
	{
		String res="";
		try 
		{
			
			res= WardRoomBedMstDAO.getBedView(pk1,vo);
		}
		catch(Exception e)
		{
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->WardRoomBed Master", "WardRoomBedMstBO-->getBedDetail()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}	
		
		return res;
	}
	/**
	 * Invoke HLPWardRoomBedMst's enterBedDetail()
	 * @param pk1
	 * @param vo
	 * @return String i.e.it create space on add page.
	 * @throws Exception
	 */
	public String createNewEntrySpace(String pk1,WardRoomBedMstVO vo) throws Exception {
		
		WardRoomBedMstHLP hlp=new WardRoomBedMstHLP();
		String res=hlp.enterBedDetail(pk1, vo);
		return res;
		
	}
	/*public String createView(String chk,VOWardRoomBedMst vo) throws Exception
	{
		WardRoomBedMstDAO.createView(chk, vo);
		return null;
	}*/

	/**
	 * This function invoke WardRoomBedMstDAO's getRoomBlock()
	 * @param pk
	 * @param vo
	 * @return its return type String i.e. Bring room details with corresponding building and block 
	 * @throws Exception
	 */
	public String getProperty(String searchIndex,String room,WardRoomBedMstVO vo) throws Exception
	{
		String res="";
		
		try 
		{
			  WardRoomBedMstDAO.getPropertyPopup(room,vo);
			 WardRoomBedMstHLP hlp=new WardRoomBedMstHLP();
			 res=hlp.showSearchProperty(vo.getPropertyDetails(),searchIndex);
			 return res;
		}
		catch(Exception e)
		{
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->WardRoomBed Master", "WardRoomBedMstBO-->getProperty()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}	
		
			
		return res;
	}
}
