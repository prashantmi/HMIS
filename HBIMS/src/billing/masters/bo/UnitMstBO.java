

/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Unit Master VO
 * 
 * Created on: 14-09-2011
 */







package billing.masters.bo;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.masters.dao.UnitMstDAO;
import billing.masters.vo.UnitMstVO;

public class UnitMstBO {

	public UnitMstBO() {

	}

	/**
	 * inserts new record by checking the existence of Record with same content.
	 * 
	 * @param formBean
	 * @return true - if New Record Inserted Successfully. <br>
	 *         false - if New Record Not Inserted Successfully.
	 */
	public boolean insert(UnitMstVO vo) {

		String strmsgText = null;
		String strQry = billing.qryHandler_billing.getQuery(1,
				"select.unitMst.5");
		HisDAO hisDao = new HisDAO("Unit Master", "UnitMstBO.insert()");
		int nQryIndex = hisDao.setQuery(strQry);

		try {
			hisDao.setQryValue(nQryIndex, 1, vo.getStrUnitName());
			hisDao.setQryValue(nQryIndex, 2, BillConfigUtil.SUPER_HOSPITAL_CODE);
			hisDao.setQryValue(nQryIndex, 3, vo.getStrModuleVal());
			WebRowSet wb = hisDao.executeQry(nQryIndex);

			while (wb.next()) {
				if (wb.getInt(1) > 0) {

					vo.setStrWarning("Record '"
							+ vo.getStrUnitName() + "'already Exist ");

					return false;
				}
			}
		} catch (SQLException e) {

			strmsgText = "billing.masters.UnitMstBO.insert(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"UnitMaster->insert()", strmsgText);
			vo.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
			return false;

		} catch (Exception e) {

			strmsgText = "billing.masters.UnitMstBO.insert(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"UnitMaster->insert()", strmsgText);
			vo.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;

			return false;

		} finally {
			hisDao.free();
			hisDao = null;
		}

		boolean fReturnValue = UnitMstDAO.insertQuery(vo);

		if (fReturnValue) {

			vo.setStrMsg("Data Inserted Successfully");
		}

		return fReturnValue;
	}

	/**
	 * invokes modifyQuery Method to retrieve Data's from Database
	 * 
	 * @param strChk
	 * @param vo
	 */
	public void modify(String strChk, UnitMstVO vo) {

		UnitMstDAO.modifyQuery(strChk, vo);

			if(vo.getStrBaseUnit().length() > 1){
				
				vo.setStrIsBaseUnit("No");
				
			}else{
				
				vo.setStrIsBaseUnit("Yes");
			}
			
			
	}

	/**
	 * invokes UpdateQuery method based on existence of Record and returns
	 * Update Status
	 * 
	 * @param strChk
	 * @param vo
	 * @return true - if record Updated Successfully. <br>
	 *         false - if record not updated Successfully.
	 */
	public boolean update(String strChk, UnitMstVO vo) {
		String strtemp[] = null;
		String strtemp2[] = null;
		int ncount = 0;
		boolean freturnValue = false;
		boolean returnValue = false;
		String qry = billing.qryHandler_billing.getQuery(1, "select.unitMst.6");
		HisDAO hisDao = new HisDAO("Unit Master", "UnitMstBO.update()");
		try {
			////  System.out.println("in bo update chk-->"+strChk);
			int nQryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(nQryIndex, 1, vo.getStrUnitName());
			/*
			 * String strTemp[] = vo.chk[0].replace('$', '#').split("#");
			 * hisDao.setQryValue(nQryIndex, 2, strTemp[0]);
			 */

			strtemp = strChk.replace('@', '#').split("#");// changes
																	// by anshul
																	// after
																	// addition
																	// of
																	// hospital
																	// code in
																	// primary
																	// key
			strtemp2 = strtemp[1].replace('$', '#').split("#");
			strtemp[1] = strtemp2[0];
			////  System.out.println("in bo update()--strtmp0 shud b unit
			// id-->"+strtemp[0]);
			////  System.out.println("in bo update()--strtmp1 shud b hos
			// code-->"+strtemp[1]);
			hisDao.setQryValue(nQryIndex, 2, strtemp[1]);
			hisDao.setQryValue(nQryIndex, 3, strtemp[0]);
			hisDao.setQryValue(nQryIndex, 4, vo.getStrModuleVal());

			WebRowSet wb = hisDao.executeQry(nQryIndex);

			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
				
				
				////  System.out.println("in dao initial update getting wb
				// ncount"+ncount);
			}

			if (ncount >= 1) {
				freturnValue = false;
				vo.setStrWarning("Record '" + vo.getStrUnitName()
						+ "' already Exists");
			} else {
				freturnValue = true;
			}
			/*
			 * if (freturnValue == false) {
			 * 
			 * vo.setStrWarning("Record '"+ vo.getStrUnitName()+ "'
			 * already Exists");
			 * 
			 * return false; }
			 */

		} catch (Exception e) {

			new HisException("Unit Master", "UnitMstBO.update()", e
					.getMessage());
			// vo.setStrWarning("Record '" + vo.getStrUnitName()+ "'
			// already Exists");

			return false;
		} finally {
			hisDao.free();
			hisDao = null;
		}
		if (freturnValue) {
			returnValue = UnitMstDAO.updateQuery(strChk, vo);
		}
		if (returnValue) {

			vo.setStrMsg("Data Inserted Successfully");
		}

		return returnValue;
	}

	public void initialAdd(UnitMstVO vo) throws Exception
	// for getting option value of combo on add page
	{
		WebRowSet wb = null;

		////  System.out.println("bo init add calling");

		try {
			wb = UnitMstDAO.initialAddQuery(vo);

			HisUtil hisutil = new HisUtil("Unit Master", "UnitMstBO");
			/*
			 * while(wb.next()) {//  System.out.println("cmb
			 * CODE-->"+wb.getString(1));//  System.out.println("cmb
			 * VAL-->"+wb.getString(2)); }
			 */
			String cmb = hisutil.getOptionValue(wb, "0#Select Value",
					"Select Value", false);
			////  System.out.println("in bo cmb-->"+cmb);
			vo.setStrUnitComboValues(cmb);
			////  System.out.println("IN BO VO CMB
			// VALUE-->"+vo.getStrUnitComboValues());

		} catch (Exception e) {
			throw new Exception("UnitMstBO.initialAdd() -->" + e.getMessage());
		}

	}

	public void modName(UnitMstVO vo) throws Exception
	// for getting option value of combo on add page
	{
		// WebRowSet wb=null;
		String modName = "";

		try {

			modName = UnitMstDAO.modNameQuery(vo);

			/*
			 * HisUtil hisutil = new HisUtil("store","BOStoreGroupMst"); String
			 * cmb = hisutil.getOptionValue(wb, "0^Select Value" ,"Select Value"
			 * ,true );
			 */

			vo.setStrModuleName(modName);

		} catch (Exception e) {
			throw new Exception("UnitMstBO.modName() -->" + e.getMessage());
		}

	}

}
