package billing.masters.bo;
/* Charge Master BO
 * author: Debashis Sardar
 * Created on : 06-Sep-2011
 */
import javax.sql.rowset.WebRowSet;
import billing.masters.dao.ClientChargeMstDAO;
import billing.masters.vo.ClientChargeMstVO;



public class ClientChargeMstBO {

	/**
	 * to retrieve package data from database.
	 * @param vo
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getDataOnPackage(String str, ClientChargeMstVO vo)
			throws Exception {
		
		
		WebRowSet wb = null;
		
			wb=ClientChargeMstDAO.getDataOnPackage(str,vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.getDataOnPackage() --> " + vo.getStrMsgString());
			}		
		
		return wb;
	}

	/**
	 * insert data on submitting the add page.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean insertRecord(ClientChargeMstVO vo) throws Exception 
	{
		boolean fretVal = false;		
		fretVal=ClientChargeMstDAO.insertRecord(vo);			
		if (vo.getStrMsgType().equals("1")) 
		{
				vo.setStrMsgString("ClientChargeMstBO.insertRecord() --> " + vo.getStrMsgString());
		}		
		return fretVal;
	}

	/**
	 * retrieve data from the database,which is shown in modify page.
	 * 
	 * @param vo
	 * @param chk
	 * @param chkValue
	 * @return
	 * @throws Exception
	 */
	public static boolean getData(ClientChargeMstVO vo, String[] chk , String chkValue )
			throws Exception {
		boolean fResult = false;
		
		
			fResult=ClientChargeMstDAO.getData(vo,chk,chkValue);
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.getData() --> " + vo.getStrMsgString());
			
	 
			
		}
		
		return fResult;
	}

	/**
	 * checks conditions if data can be updated in modify page or not.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean isValidUpdation(ClientChargeMstVO vo) throws Exception {
		
		boolean fRes = false;
		
	
			fRes=ClientChargeMstDAO.isValidUpdation(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.isValidUpdation() --> " + vo.getStrMsgString());
		
			
		}
		return fRes;
	}

	/**
	 * insert data in modification mode of modify page.
	 * 
	 * @param vo
	 * @param chk
	 * @return
	 * @throws Exception
	 */
	public static boolean newInsertData(ClientChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		
		
			fVal=ClientChargeMstDAO.newInsertData(vo,chk);
			

			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.updateData() --> " + vo.getStrMsgString());
		
  
			}
		
		return fVal;

	}

	/**
	 * for correction part.
	 * 
	 * @param vo
	 * @param chk
	 * @return
	 * @throws Exception
	 */
	public static boolean modifyData(ClientChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		
		
			fVal=ClientChargeMstDAO.modifyData(vo,chk);
			

	

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.modifyData() --> " + vo.getStrMsgString());
			
				
			}
		return fVal;
	}

	/**
	 * for correction as well as update part.
	 * 
	 * @param vo
	 * @param chk
	 * @return
	 * @throws Exception
	 */
	public static boolean saveModifiedData(ClientChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		
	

			fVal=ClientChargeMstDAO.saveModifiedData(vo,chk);

		
	 
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.saveModifiedData() --> " + vo.getStrMsgString());
			
			}
		return fVal;
	}
	
	/**
	 * validation no.1 to be checked before modifying the data.
	 * 
	 * @param vo
	 * @param chk
	 * @return
	 * @throws Exception
	 */
	public static boolean qryBeforeUpdate1(ClientChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		
	
			fVal=ClientChargeMstDAO.qryBeforeUpdate1(vo,chk);
			
		
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.qryBeforeUpdate1() --> " + vo.getStrMsgString());
			
			}
		return fVal;
	}

	/**
	 * validation no.2 to be checked before modifying the data.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean qryBeforeUpdate2(ClientChargeMstVO vo) throws Exception {
		boolean fVal = false;
		
		
			fVal=ClientChargeMstDAO.qryBeforeUpdate2(vo);	

			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.qryBeforeUpdate2() --> " + vo.getStrMsgString());
	 
			}
	
		return fVal;
	}

	/**
	 * batch update method. CASE 1- Apply New Change.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean batchUpdateQry(ClientChargeMstVO vo) throws Exception {

		boolean fVal = false;
		
	
			
			fVal=ClientChargeMstDAO.batchUpdateQry(vo);		

		
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.batchUpdateQry() --> " + vo.getStrMsgString());
			
			
			}

		return fVal;
	}

	/**
	 * batch update method. CASE 2- Copy To Change.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean batchCopyToQry(ClientChargeMstVO vo) throws Exception {

		boolean fVal = false;
		
		
			fVal=ClientChargeMstDAO.batchCopyToQry(vo);	
		
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.batchCopyToQry() --> " + vo.getStrMsgString());
	 
			}
		
		return fVal;
	}

// methods by kapil and anshul//

	/**
	 * Group combo
	 * 
	 * @param vo
	 * @param isPackage
	 * @return
	 * @throws Exception
	 */
	public WebRowSet groupCmb(ClientChargeMstVO vo, String isPackage)
			throws Exception {
		
		
		
		WebRowSet wb = null;
		
			wb = ClientChargeMstDAO.groupCmb(vo,isPackage);
			

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.groupCmb() --> " + vo.getStrMsgString());
			
			}
		return wb;

	}
	/**
	 * Group combo1
	 * 
	 * @param vo
	 * @param isPackage
	 * @return
	 * @throws Exception
	 */
	public WebRowSet groupCmb1(ClientChargeMstVO vo, String isPackage)
			throws Exception {
		
		WebRowSet wb = null;
		

		

			wb = ClientChargeMstDAO.groupCmb1(vo,isPackage);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.groupCmb1() --> " + vo.getStrMsgString());
		
			}
		return wb;

	}
	/**
	 * tariff combo
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public WebRowSet tariffCmb(ClientChargeMstVO vo) throws Exception {
		
		WebRowSet wb = null;
		

		

			wb = ClientChargeMstDAO.tariffCmb(vo);

	
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.tariffCmb() --> " + vo.getStrMsgString());
			
			}
		return wb;

	}
	/**
	 * tariffCmb1
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public WebRowSet tariffCmb1(ClientChargeMstVO vo) throws Exception 
	{		
			WebRowSet wb = null;
			wb = ClientChargeMstDAO.tariffCmb1(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("ClientChargeMstBO.tariffCmb() --> " + vo.getStrMsgString());
			}		
		return wb;
	}
	/**
	 * unitCmb
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public WebRowSet unitCmb(ClientChargeMstVO vo) throws Exception 
	{
		WebRowSet wb = null;			
		wb = ClientChargeMstDAO.unitCmb(vo);
		
		
		if (vo.getStrMsgType().equals("1")) 
		{
				vo.setStrMsgString("ClientChargeMstBO.unitCmb() --> " + vo.getStrMsgString());
		}
		return wb;
	}
	/**
	 * ward combo
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public WebRowSet wardCmb(ClientChargeMstVO vo) throws Exception {


		WebRowSet wb = null;



		
			wb = ClientChargeMstDAO.wardCmb(vo);
			

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.wardCmb() --> " + vo.getStrMsgString());
			
			}
		return wb;

	}
	/**
	 * to get PreviousData
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getPreviousData(ClientChargeMstVO vo) throws Exception {


		WebRowSet wb = null;




			wb = ClientChargeMstDAO.getPreviousData(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.getPreviousData() --> " + vo.getStrMsgString());
			
			}

		return wb;
	}
	/**
	 * query before modify
	 * 
	 * @param vo
	 * @param chk
	 * @return
	 * @throws Exception
	 */
	public static boolean qryBeforeModify(ClientChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		



		fVal=ClientChargeMstDAO.qryBeforeModify(vo,chk);	
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("ClientChargeMstBO.qryBeforeUpdate1() --> " + vo.getStrMsgString());
			
		}
		return fVal;
	}
	/**
	 * delete Data.
	 * @param vo
	 * @param str
	 */
	public static void deleteData(ClientChargeMstVO vo, String[] chk){
		


			ClientChargeMstDAO.deleteData(vo,chk);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ClientChargeMstBO.deleteData() --> " + vo.getStrMsgString());
				
			}
		
	}
}
