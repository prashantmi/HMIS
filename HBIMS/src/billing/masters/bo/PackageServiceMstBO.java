/* (PACKAGE SERVICE MASTER)  */

package billing.masters.bo;

/* Package Service Master BO
 * author: Debashis Sardar
 * Created on : 01-Sep-2011
 */

import javax.sql.rowset.WebRowSet;

import billing.masters.dao.PackageServiceMstDAO;
import billing.masters.vo.PackageServiceMstVO;

public class PackageServiceMstBO {
	/**
	 * retrieves insert Query and executes it.
	 * @param vo
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */
	public static boolean addData(PackageServiceMstVO bean) {
		boolean returnValue = false;
		returnValue=PackageServiceMstDAO.addData(bean);
			
		if (bean.getStrMsgType().equals("1")) {
			bean.setStrMsgString("PackageServiceMstBO.addData() --> " + bean.getStrMsgString());
			
		}
		return returnValue;
	}

	/*
	 *  To retrieve data from the database for the modify page.
	 * @param vo
	 * @param chk
	 * @return boolean
	 */
	public static boolean getDataQuery(PackageServiceMstVO bean, String chk) {
		boolean returnValue = true;
		returnValue=PackageServiceMstDAO.getDataQuery(bean,chk);
			
		if (bean.getStrMsgType().equals("1")) {
			bean.setStrMsgString("PackageServiceMstBO.getDataQuery() --> " + bean.getStrMsgString());
			
		}
		return returnValue;
	}

	
	/*
	 *  function to update data in Database through modify page
	 * @param vo
	 * @return boolean
	 */

	public static boolean update( String chk,PackageServiceMstVO bean) {
		boolean returnValue = false;
		returnValue=PackageServiceMstDAO.update(chk,bean);
			
		if (bean.getStrMsgType().equals("1")) {
			bean.setStrMsgString("PackageServiceMstBO.update() --> " + bean.getStrMsgString());
			
		}
		return returnValue;
	}

	/*
	 *  function to check uniqueness in modify page
	 * @param vo
	 * @return boolean
	 */
	
	public static boolean chkModifyCounter(PackageServiceMstVO bean) {
		boolean returnValue = false;
		returnValue=PackageServiceMstDAO.chkModifyCounter(bean);
			
		if (bean.getStrMsgType().equals("1")) {
			bean.setStrMsgString("PackageServiceMstBO.chkModifyCounter() --> " + bean.getStrMsgString());
			
		}
		return returnValue;
	}
	/*
	 *  to execute initial add query
	 * @param vo
	 * @return WebRowSet
	 */
	public static WebRowSet initAddQuery(PackageServiceMstVO vo) throws Exception 
	{		
		WebRowSet wb = null;		
		wb=PackageServiceMstDAO.initAddQuery(vo);		
		if (vo.getStrMsgType().equals("1")) 
		{
			vo.setStrMsgString("PackageServiceMstBO.initAddQuery() --> " + vo.getStrMsgString());			
		}
		
		return wb;
	}
	/*
	 * for tariff Name Combo Query
	 * @param vo
	 * @return WebRowSet
	 */

	public static WebRowSet tariffNameCmbQuery(PackageServiceMstVO vo)
			throws Exception {

		
		WebRowSet wb = null;
		
wb=PackageServiceMstDAO.tariffNameCmbQuery(vo);
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("PackageServiceMstBO.tariffNameCmbQuery() --> " + vo.getStrMsgString());
			
		}
		

		return wb;
	}
	/*
	 * for unit Name Query
	 * @param vo
	 * @return WebRowSet
	 */
	public static WebRowSet unitNameQuery(PackageServiceMstVO vo) throws Exception {

		WebRowSet wb = null;
		
		wb=PackageServiceMstDAO.unitNameQuery(vo);
				
				if (vo.getStrMsgType().equals("1")) {
					vo.setStrMsgString("PackageServiceMstBO.unitNameQuery() --> " + vo.getStrMsgString());
					
				}
				

				return wb;
	}
	/*
	 * for added Tariff Details Query
	 * @param vo
	 * @return WebRowSet
	 */
	public static WebRowSet addedTariffDetailsQuery(PackageServiceMstVO vo)
			throws Exception {
WebRowSet wb = null;
		
		wb=PackageServiceMstDAO.addedTariffDetailsQuery(vo);
				
				if (vo.getStrMsgType().equals("1")) {
					vo.setStrMsgString("PackageServiceMstBO.addedTariffDetailsQuery() --> " + vo.getStrMsgString());
					
				}
				

				return wb;
		
	}
	/*
	 * for unit combo
	 * @param vo
	 * @return WebRowSet
	 */
	public WebRowSet unitCmb(PackageServiceMstVO vo) throws Exception {
         WebRowSet wb = null;
		
		wb=PackageServiceMstDAO.unitCmb(vo);
				
				if (vo.getStrMsgType().equals("1")) {
					vo.setStrMsgString("PackageServiceMstBO.unitCmb() --> " + vo.getStrMsgString());
					
				}
				

				return wb;
	}
	/*
	 * to get Tariff Combo Query
	 * @param vo
	 * @return WebRowSet
	 */
	public static WebRowSet getTariffComboQuery(PackageServiceMstVO vo)
			throws Exception {

		 WebRowSet wb = null;
			
			wb=PackageServiceMstDAO.getTariffComboQuery(vo);
					
					if (vo.getStrMsgType().equals("1")) {
						vo.setStrMsgString("PackageServiceMstBO.getTariffComboQuery() --> " + vo.getStrMsgString());
						
					}
					

					return wb;
	}
	/*
	 * to get tariff Name Query
	 * @param vo
	 * @return WebRowSet
	 */
	public static WebRowSet tariffNameQuery(PackageServiceMstVO vo) throws Exception {

		 WebRowSet wb = null;
			
			wb=PackageServiceMstDAO.tariffNameQuery(vo);
					
					if (vo.getStrMsgType().equals("1")) {
						vo.setStrMsgString("PackageServiceMstBO.tariffNameQuery() --> " + vo.getStrMsgString());
						
					}
					

					return wb;
	}

	/*
	 * to check for duplicacy on add page
	 * @param vo
	 * @return boolean
	 */

	public static boolean chkAddDuplicateQuery(PackageServiceMstVO vo)
			throws Exception {

		
		boolean fretValue = false;
		
		fretValue=PackageServiceMstDAO.chkAddDuplicateQuery(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("PackageServiceMstBO.chkAddDuplicateQuery() --> " + vo.getStrMsgString());
			
		}
		
		return fretValue;
	}
}
