/* (PACKAGE SERVICE MASTER)  */
package billing.masters.dao;
/* Package Service Master DAO
 * author: Debashis Sardar
 * Created on : 01-Sep-2011
 */
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.qryHandler_billing;
import billing.masters.vo.PackageServiceMstVO;

public class PackageServiceMstDAO {
	/**
	 * retrieves insert Query and executes it.
	 * @param vo
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */
	public static boolean addData(PackageServiceMstVO bean) {
		
		HisDAO hisdao = new HisDAO("Billing", "DAOpackservMst");
		boolean returnValue = false;
		int nLength = 0;
		int index;
		String strTrfId[] = null;
		String strQty[] = null;
		String strUnitId[] = null;
		String temp[] = null;
		String Query = billing.qryHandler_billing.getQuery(1,
				"insert.packservMst.0");

		index = hisdao.setQuery(Query);

		try {
			nLength = bean.getStrMultiTariffId().length;
			strTrfId = bean.getStrMultiTariffId();
			strQty = bean.getStrMultiQty();
			strUnitId = bean.getStrMultiUnitId();

			for (int i = 0; i < nLength; i++) {
				
				

				temp = strTrfId[i].replace("^", "#").split("#");
				hisdao.setQryValue(index, 1, bean.getStrpackageId());
				hisdao.setQryValue(index, 2, temp[0]);
				hisdao.setQryValue(index, 3, strQty[i]);
				hisdao.setQryValue(index, 4, strUnitId[i]);
				hisdao.setQryValue(index, 5, bean.getStreffectiveFrm());
				hisdao.setQryValue(index, 6, bean.getStrremarks());
				hisdao.setQryValue(index, 7, bean.getStrseatId());
				hisdao.setQryValue(index, 8, "1");
				hisdao.setQryValue(index, 9, bean.getStrHospitalCode());
				hisdao.setQryValue(index, 10, bean.getStrpackageId());
				hisdao.setQryValue(index, 11, temp[0]);
				hisdao.setQryValue(index, 12, bean.getStrHospitalCode());
                hisdao.setQryValue(index,13,bean.getStrtariffId());
				hisdao.execute(index, 0);
			}
			synchronized (hisdao) {
				hisdao.fire();
				returnValue = true;
			}
			
		} catch (Exception e) {
			bean.setStrMsgString("DAOpackservMst.addData() --> "
					+ e.getMessage());
			bean.setStrMsgType("1");
			returnValue = false;
			
		} finally {

			hisdao.free();
			hisdao = null;
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
		HisDAO hisdao = new HisDAO("Billing", "DAOpackservMst");
		boolean returnValue = true;
		WebRowSet wb;
		int index;
		String[] str = chk.replace('$', '#').split("#"); // splitting the	
		String Query = billing.qryHandler_billing.getQuery(1,"select.packservMst.3");
		try
		{
			String[] temp = str[0].split("@");
			index = hisdao.setQuery(Query);
			hisdao.setQryValue(index, 1, temp[0]);
			hisdao.setQryValue(index, 2, temp[1]);
			hisdao.setQryValue(index, 3, temp[2]);
			hisdao.setQryValue(index, 4, temp[3]);

			wb = hisdao.executeQry(index);

			while (wb.next()) 
			{
				bean.setStrpackageId(temp[0]);
				bean.setStrtariffId(temp[1]);
				bean.setStrHospitalCode(temp[2]);
				bean.setStrpackageId(wb.getString(1));
				bean.setStrtariffId(wb.getString(2));
				bean.setStrqty(wb.getString(3));
				bean.setStrunitId(wb.getString(4));
				bean.setStreffectiveFrm(wb.getString(5));
				bean.setStrremarks(wb.getString(6));
				bean.setStrisValid(wb.getString(7));
			}
		} 
		catch (Exception e)
		{
			bean.setStrMsgString("DAOpackservMst.getData() --> "+ e.getMessage());
			bean.setStrMsgType("1");
			returnValue = false;
	
		} 
		finally
		{

			hisdao.free();
			hisdao = null;
		}

		return returnValue;
	}

	/*
	 *  function to update data in Database through modify page
	 * @param vo
	 * @return boolean
	 */
	

	public static boolean update(String chk,PackageServiceMstVO bean) {
		HisDAO hisdao = new HisDAO("Billing", "DAOpackservMst");
		boolean returnValue = false;
		int index;
		String query = billing.qryHandler_billing.getQuery(1,
				"update.packservMst.0");
		String[] str = chk.replace('$', '#').split("#");
		try {
			String[] temp = str[0].split("@");

			index = hisdao.setQuery(query);


			hisdao.setQryValue(index, 1, bean.getStrqty());
			hisdao.setQryValue(index, 2, bean.getStrunitId());
			hisdao.setQryValue(index, 3, bean.getStrLstModifySeatId());
			hisdao.setQryValue(index, 4, bean.getStrremarks());
			hisdao.setQryValue(index, 5, bean.getStrisValid());
			hisdao.setQryValue(index, 6, bean.getStrpackageId());
			hisdao.setQryValue(index, 7, bean.getStrtariffId());
			hisdao.setQryValue(index, 8, bean.getStrHospitalCode());
			hisdao.setQryValue(index, 9, temp[3]);
			hisdao.execute(index,0);

			hisdao.fire();

			returnValue = true;
		} catch (Exception e) {
			bean.setStrMsgString("DAOpackservMst.modifyData() --> "
					+ e.getMessage());
			bean.setStrMsgType("1");
			returnValue = false;
			
		} finally {

			hisdao.free();
			hisdao = null;
		}
		
		return returnValue;
	}
	/*
	 *  function to check uniqueness in modify page
	 * @param vo
	 * @return boolean
	 */
	
	public static boolean chkModifyCounter(PackageServiceMstVO bean) {
		HisDAO hisdao = new HisDAO("Billing", "DAOpackservMst");
		boolean returnValue = false;
		int index;
		int count = 0;
		WebRowSet wb;
		String query = billing.qryHandler_billing.getQuery(1,
				"select.packservMst.4");

		try {
			index = hisdao.setQuery(query);
			wb = hisdao.executeQry(index);
			while (wb.next()) {
				count = Integer.parseInt(wb.getString(1));
			}
			if (count == 0) {
				returnValue = true;
			} else {
				returnValue = false;
			}

		} catch (Exception e) {
			bean.setStrMsgString("DAOpackservMst.chkModifyCounter() --> "
					+ e.getMessage());
			bean.setStrMsgType("1");
			returnValue = false;
			
		}
		return returnValue;
	}
	/*
	 *  to execute initial add query to get package name
	 * @param vo
	 * @return WebRowSet
	 */
	
	public static WebRowSet initAddQuery(PackageServiceMstVO vo) throws Exception 
	{
		HisDAO dao = new HisDAO("billing", "DAOpackservMst");
		int nqryIndex;
		WebRowSet wb = null;
		String strquery = billing.qryHandler_billing.getQuery(1,"select.packservname.1");

		try 
		{			
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrtariffId());
			dao.setQryValue(nqryIndex, 2, vo.getStrpackageId());
			dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());			
			wb = dao.executeQry(nqryIndex);
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("DAOpackservMst.initAddQuery() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} 
		finally 
		{
			dao.free();
			dao = null;
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

		HisDAO dao = new HisDAO("billing", "DAOpackservMst");
		int nqryIndex;
		WebRowSet wb = null;
		String strquery = billing.qryHandler_billing.getQuery(1,
				"select.packtariffname.1")
				.replace("?", vo.getStrHospitalCode());
		strquery = strquery.concat(" AND ").concat(
				billing.qryHandler_billing.getQuery(1,
						"select.packtariffname.cond.1").replace("?",
						vo.getStrpackageId()));
		
		try {
			nqryIndex = dao.setQuery(strquery);
			wb = dao.executeQry(nqryIndex);

		} catch (Exception e) {

			vo.setStrMsgString("DAOpackservMst.tariffNameCmbQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
			
		} finally {
			dao.free();
			dao = null;
		}

		return wb;
	}
	/*
	 * for unit Name Query
	 * @param vo
	 * @return WebRowSet
	 */
	public static WebRowSet unitNameQuery(PackageServiceMstVO vo) throws Exception {

		HisDAO dao = new HisDAO("billing", "DAOpackservMst");

		int nqryIndex;
		String strModuleId = "";
		String strquery = "";
		WebRowSet wb = null;

		try {
			strModuleId = BillConfigUtil.BILL_MODULE_ID;
			strquery = billing.qryHandler_billing.getQuery(1,
					"select.packunitname.1");
			
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			dao.setQryValue(nqryIndex, 2, strModuleId);
			dao.setQryValue(nqryIndex, 3, vo.getStrtrfDefUnit());
			dao.setQryValue(nqryIndex, 4, BillConfigUtil.SUPER_HOSPITAL_CODE);
		
		
			
		
			wb = dao.executeQry(nqryIndex);

		} catch (Exception e) {
			vo.setStrMsgString("DAOpackservMst.unitNameQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

			
		} finally {
			dao.free();
			dao = null;
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

		HisDAO dao = new HisDAO("billing", "DAOpackservMst");

		int nqryIndex;

	
		WebRowSet wb = null;

		String strquery = billing.qryHandler_billing.getQuery(1,
				"select.tariffdetails.1").replace("?", vo.getStrHospitalCode());
		strquery = strquery.concat(" AND ").concat(
				billing.qryHandler_billing.getQuery(1,
						"select.tariffdetails.cond.1").replace("?",
						vo.getStrpackageId()));
		strquery = strquery.concat(billing.qryHandler_billing.getQuery(1,
				"select.tariffdetails.cond.2"));

		
		try {
			nqryIndex = dao.setQuery(strquery);
			wb = dao.executeQry(nqryIndex);

		} catch (Exception e) {

			vo.setStrMsgString("DAOpackservMst.addedTariffDetailsQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

			
		} finally {
			dao.free();
			dao = null;
		}
		
		return wb;
	}
	/*
	 * for unit combo
	 * @param vo
	 * @return WebRowSet
	 */
	public static WebRowSet unitCmb(PackageServiceMstVO vo) throws Exception {
		int nIndex = 0;
		String strQry = "";
		
		WebRowSet wb = null;
		HisDAO hisdao = new HisDAO("Billing", "DAOChargeMst");

		try {

			strQry = qryHandler_billing.getQuery(1, "gbl.unit.0");
			nIndex = hisdao.setQuery(strQry);
			
			hisdao.setQryValue(nIndex, 1,BillConfigUtil.SUPER_HOSPITAL_CODE);
			hisdao.setQryValue(nIndex, 2,BillConfigUtil.BILL_MODULE_ID);
			hisdao.setQryValue(nIndex, 3, vo.getStrunitId());
			
			
			wb = hisdao.executeQry(nIndex);

		} catch (Exception e) {
			
			vo.setStrMsgString("DAOpackservMst.unitCmb() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			hisdao.free();
			hisdao = null;
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

		HisDAO dao = new HisDAO("billing", "DAOpackservMst");

		int nqryIndex = 0;

	
		WebRowSet wb = null;
		String strquery = "";
		
					
		try {
		
		strquery = billing.qryHandler_billing.getQuery(1,"select.packtariffname.2");
		
		nqryIndex = dao.setQuery(strquery);
				
		dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
		System.out.println("----->>>VO Values are"+vo.getStrHospitalCode()+" and "+ vo.getStrgroupId());
		dao.setQryValue(nqryIndex, 2, vo.getStrgroupId());
		wb = dao.executeQry(nqryIndex);
		}
		catch (Exception e) {

			vo.setStrMsgString("DAOpackservMst.getTariffComboQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

			
		} finally {
			dao.free();
			dao = null;
		}
		
		return wb;
	}
	/*
	 * to get tariff Name Query
	 * @param vo
	 * @return WebRowSet
	 */
	public static WebRowSet tariffNameQuery(PackageServiceMstVO vo) throws Exception {

		HisDAO dao = new HisDAO("billing", "DAOpackservMst");
		int nqryIndex;
		WebRowSet wb = null;
		
		// Modified by Manisha 12-10-2015, To get correct tariff name on click of Modify Button
		/*String strquery = billing.qryHandler_billing.getQuery(1,
				"select.tariffname.1").replace("?", vo.getStrtariffId());

		try {
			nqryIndex = dao.setQuery(strquery);
			wb = dao.executeQry(nqryIndex);*/
		
		String strquery = billing.qryHandler_billing.getQuery(1,
				"select.tariffname.1");
       try {
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1,  vo.getStrtariffId());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			wb = dao.executeQry(nqryIndex);

		} catch (Exception e) {
			vo.setStrMsgString("DAOpackservMst.tariffNameQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
			
		} finally {
			dao.free();
			dao = null;
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

		HisDAO dao = null;
		boolean fretValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strMultiTrfId[] = null;
		int nLength = 0;
		String strquery = billing.qryHandler_billing.getQuery(1,
				"select.packservdup.2");

		try {
			dao = new HisDAO("billing", "DAOpackservMst");
			nLength = vo.getStrMultiTariffId().length;
			
			strMultiTrfId = vo.getStrMultiTariffId();
			nqryIndex = dao.setQuery(strquery);
			
			for (int i = 0; i < nLength; i++) {

			
				String[] temp = strMultiTrfId[i].replace("^", "#").split("#");
				dao.setQryValue(nqryIndex, 1, vo.getStrpackageId());
				dao.setQryValue(nqryIndex, 2, temp[0]);
				dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());
				
				wb = dao.executeQry(nqryIndex);
				while (wb.next()) {
					ncount = Integer.parseInt(wb.getString(1));
					
				}
				
				if (ncount == 0) {
					fretValue = true;
				} else {
					fretValue = false;
					break;
				}
			}
			

		} catch (Exception e) {

			vo.setStrMsgString("DAOpackservMst.chkAddDuplicateQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
			fretValue = false;
			
		} finally {
			dao.free();
			dao = null;
		}
		return fretValue;
	}
}
