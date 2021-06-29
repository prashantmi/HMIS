package billing.masters.bo;
/* Charge Master BO
 * author: Debashis Sardar
 * Created on : 06-Sep-2011
 */
import javax.sql.rowset.WebRowSet;
import billing.masters.dao.ChargeMstDAO;
import billing.masters.vo.ChargeMstVO;



public class ChargeMstBO {

	/**
	 * to retrieve package data from database.
	 * @param vo
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getDataOnPackage(String str, ChargeMstVO vo)
			throws Exception {
		
		
		WebRowSet wb = null;
		
			wb=ChargeMstDAO.getDataOnPackage(str,vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.getDataOnPackage() --> " + vo.getStrMsgString());
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
	public static boolean insertRecord(ChargeMstVO vo) throws Exception {
		boolean fretVal = false;
		
		
		
			fretVal=ChargeMstDAO.insertRecord(vo);
				
		

			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.insertRecord() --> " + vo.getStrMsgString());
				
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
	public static boolean getData(ChargeMstVO vo, String[] chk , String chkValue )
			throws Exception {
		boolean fResult = false;
		
		
			fResult=ChargeMstDAO.getData(vo,chk,chkValue);
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.getData() --> " + vo.getStrMsgString());
			
	 
			
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
	public static boolean isValidUpdation(ChargeMstVO vo) throws Exception {
		
		boolean fRes = false;
		
	
			fRes=ChargeMstDAO.isValidUpdation(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.isValidUpdation() --> " + vo.getStrMsgString());
		
			
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
	public static boolean newInsertData(ChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		
		
			fVal=ChargeMstDAO.newInsertData(vo,chk);
			

			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.updateData() --> " + vo.getStrMsgString());
		
  
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
	public static boolean modifyData(ChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		
		
			fVal=ChargeMstDAO.modifyData(vo,chk);
			

	

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.modifyData() --> " + vo.getStrMsgString());
			
				
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
	public static boolean saveModifiedData(ChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		
	

			fVal=ChargeMstDAO.saveModifiedData(vo,chk);

		
	 
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.saveModifiedData() --> " + vo.getStrMsgString());
			
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
	public static boolean qryBeforeUpdate1(ChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		
	
			fVal=ChargeMstDAO.qryBeforeUpdate1(vo,chk);
			
		
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.qryBeforeUpdate1() --> " + vo.getStrMsgString());
			
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
	public static boolean qryBeforeUpdate2(ChargeMstVO vo) throws Exception {
		boolean fVal = false;
		
		
			fVal=ChargeMstDAO.qryBeforeUpdate2(vo);	

			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.qryBeforeUpdate2() --> " + vo.getStrMsgString());
	 
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
	public static boolean batchUpdateQry(ChargeMstVO vo) throws Exception {

		boolean fVal = false;
		
	
			
			fVal=ChargeMstDAO.batchUpdateQry(vo);		

		
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.batchUpdateQry() --> " + vo.getStrMsgString());
			
			
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
	public static boolean batchCopyToQry(ChargeMstVO vo) throws Exception {

		boolean fVal = false;
		
		
			fVal=ChargeMstDAO.batchCopyToQry(vo);	
		
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.batchCopyToQry() --> " + vo.getStrMsgString());
	 
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
	public WebRowSet groupCmb(ChargeMstVO vo, String isPackage)
			throws Exception {
		
		
		
		WebRowSet wb = null;
		
			wb = ChargeMstDAO.groupCmb(vo,isPackage);
			

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.groupCmb() --> " + vo.getStrMsgString());
			
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
	public WebRowSet groupCmb1(ChargeMstVO vo, String isPackage)
			throws Exception {
		
		WebRowSet wb = null;
		

		

			wb = ChargeMstDAO.groupCmb1(vo,isPackage);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.groupCmb1() --> " + vo.getStrMsgString());
		
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
	public WebRowSet tariffCmb(ChargeMstVO vo) throws Exception {
		
		WebRowSet wb = null;
		

		

			wb = ChargeMstDAO.tariffCmb(vo);

	
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.tariffCmb() --> " + vo.getStrMsgString());
			
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
	public WebRowSet tariffCmb1(ChargeMstVO vo) throws Exception {
		
		WebRowSet wb = null;
		

	

			wb = ChargeMstDAO.tariffCmb1(vo);
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.tariffCmb() --> " + vo.getStrMsgString());
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
	public WebRowSet unitCmb(ChargeMstVO vo) throws Exception 
	{
		WebRowSet wb = null;			
		wb = ChargeMstDAO.unitCmb(vo);
		
		
		if (vo.getStrMsgType().equals("1")) 
		{
				vo.setStrMsgString("ChargeMstBO.unitCmb() --> " + vo.getStrMsgString());
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
	public WebRowSet wardCmb(ChargeMstVO vo) throws Exception {


		WebRowSet wb = null;



		
			wb = ChargeMstDAO.wardCmb(vo);
			

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.wardCmb() --> " + vo.getStrMsgString());
			
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
	public static WebRowSet getPreviousData(ChargeMstVO vo) throws Exception {


		WebRowSet wb = null;




			wb = ChargeMstDAO.getPreviousData(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.getPreviousData() --> " + vo.getStrMsgString());
			
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
	public static boolean qryBeforeModify(ChargeMstVO vo, String chk)
			throws Exception {
		boolean fVal = false;
		



		fVal=ChargeMstDAO.qryBeforeModify(vo,chk);	
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("ChargeMstBO.qryBeforeUpdate1() --> " + vo.getStrMsgString());
			
		}
		return fVal;
	}
	/**
	 * delete Data.
	 * @param vo
	 * @param str
	 */
	public static void deleteData(ChargeMstVO vo, String[] chk){
		


			ChargeMstDAO.deleteData(vo,chk);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ChargeMstBO.deleteData() --> " + vo.getStrMsgString());
				
			}
		
	}
}
