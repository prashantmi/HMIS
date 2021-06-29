

/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Unit Master DAO
 * 
 * Created on: 14-09-2011
 */



package billing.masters.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.masters.vo.UnitMstVO;

public class UnitMstDAO {

	/**
	 * retrieves and executes insert Query
	 * 
	 * @param formBean
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */
	public static boolean insertQuery(UnitMstVO vo) {

		boolean fReturnValue = true;
		int nQueryIndex = 0;
		int qryIndex2 = 0;
		HisDAO dao = null;
		String strQuery = null;
		String strQuery2 = null;
		String[] strTemp = null;
		String strUnitId = "";
		String strFunc = "";
		//strFunc = "{? = call BILL_MST.GetUnitId(?,?)}";
		int nfuncIndex = 0;

		try {
			dao = new HisDAO("Unit Master", "UnitMstDAO.insertQuery()");

			// call a function to get unit id
			/*nfuncIndex = dao.setFunction(strFunc);
			dao.setFuncInValue(nfuncIndex, 2, vo.getStrModuleVal());
			dao.setFuncInValue(nfuncIndex, 3, vo.getStrHospitalCode());
			dao.setFuncOutValue(nfuncIndex, 1);
			dao.executeFunction(nfuncIndex);
			strUnitId = dao.getFuncString(nfuncIndex);
*/
		/*	System.out.println("vo.getStrBaseUnit()-"
					+ vo.getStrBaseUnit());*/

			if (!vo.getStrIsDecimal().equals("1")) {
				vo.setStrIsDecimal("0");
			}

			// if unit is base unit 
			if (vo.getStrBaseUnit() == null
					|| vo.getStrBaseUnit().equals("")) {

			//	System.out.println("if base unit");
				strQuery = billing.qryHandler_billing.getQuery(1,
						"insert.unitMst.0");

			} else { // if unit is sub unit 

			//	System.out.println("else");
				strQuery = billing.qryHandler_billing.getQuery(1,
						"insert.unitMst.1");
				strTemp = vo.getStrBaseUnit().split("#");
			}

			nQueryIndex = dao.setQuery(strQuery);

			/*System.out.println("strUnitId-" + strUnitId);
			System.out.println("vo.getStrUnitName()-"
					+ vo.getStrUnitName());
			System.out.println("getStrModuleVal-" + vo.getStrModuleVal());
			System.out.println("getStrBaseUnit-" + vo.getStrBaseUnit());
			System.out.println("vo.getStrEffectiveDate()-"
					+ vo.getStrEffectiveDate());
			System.out.println("getStrRemarks-" + vo.getStrRemarks());
			System.out.println("getStrSeatId-" + vo.getStrSeatId());

			System.out.println("vo.getStrModuleVal()-"
					+ vo.getStrModuleVal());
			System.out.println("getStrHospitalCode-"
					+ vo.getStrHospitalCode());
			System.out.println("getStrIsDecimal-" + vo.getStrIsDecimal());*/

			dao.setQryValue(nQueryIndex, 1, vo.getStrModuleVal());
			dao.setQryValue(nQueryIndex, 2, BillConfigUtil.SUPER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex, 3, vo.getStrUnitName());
			dao.setQryValue(nQueryIndex, 4, vo.getStrModuleVal());

			// if unit is base unit 
			if (vo.getStrIsBaseUnit().equals("1")) {

				/*System.out.println("vo.getStrBaseUnit());-"
						+ vo.getStrBaseUnit());*/

				dao.setQryValue(nQueryIndex, 5, vo.getStrBaseUnit());
			} else { // if unit is sub unit 

				dao.setQryValue(nQueryIndex, 5, strTemp[0]);
			}

			dao.setQryValue(nQueryIndex, 6, vo.getStrEffectiveDate());
			dao.setQryValue(nQueryIndex, 7, vo.getStrRemarks());
			dao.setQryValue(nQueryIndex, 8, vo.getStrSeatId());
			dao.setQryValue(nQueryIndex, 9, "1");

			if (vo.getStrBaseUnit() == null
					|| vo.getStrBaseUnit().equals("")) {

			//	System.out.println("2222 if base unit");
				dao.setQryValue(nQueryIndex, 10, vo.getStrModuleVal());
				dao.setQryValue(nQueryIndex, 11, BillConfigUtil.SUPER_HOSPITAL_CODE);
				dao.setQryValue(nQueryIndex, 12, BillConfigUtil.SUPER_HOSPITAL_CODE);
				dao.setQryValue(nQueryIndex, 13, vo.getStrIsDecimal());
			} else {

			//	System.out.println("2222else");
				dao.setQryValue(nQueryIndex, 10, strTemp[1]);
				dao.setQryValue(nQueryIndex, 11, BillConfigUtil.SUPER_HOSPITAL_CODE);
				dao.setQryValue(nQueryIndex, 12, strTemp[0]);
				dao.setQryValue(nQueryIndex, 13, BillConfigUtil.SUPER_HOSPITAL_CODE);
			}

			strQuery2 = billing.qryHandler_billing.getQuery(1, "insert.unit.2");
			qryIndex2 = dao.setQuery(strQuery2);

			dao.setQryValue(qryIndex2, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);			
			dao.setQryValue(qryIndex2, 2, vo.getStrModuleVal());
			dao.setQryValue(qryIndex2, 3, vo.getStrModuleVal());
			dao.setQryValue(qryIndex2, 4, BillConfigUtil.SUPER_HOSPITAL_CODE);
			dao.setQryValue(qryIndex2, 5, vo.getStrModuleVal());
			dao.setQryValue(qryIndex2, 6, BillConfigUtil.SUPER_HOSPITAL_CODE);
	
			dao.setQryValue(qryIndex2, 7, "1");
			dao.setQryValue(qryIndex2, 8, vo.getStrEffectiveDate());
			dao.setQryValue(qryIndex2, 9, vo.getStrSeatId());
			dao.setQryValue(qryIndex2, 10, vo.getStrRemarks());
			dao.setQryValue(qryIndex2, 11, "1");
			dao.setQryValue(qryIndex2, 12, "1");

			dao.execute(nQueryIndex, 0);
			dao.execute(qryIndex2, 0);

			synchronized (dao) {
				dao.fire();
			}

		} catch (Exception e) {
	 
			new HisException("Unit Master", "UnitMstDAO.insertQuery()", e
					.getMessage());
			vo.setStrErr("Record Not Inserted");
			fReturnValue = false;
		} finally {
			dao.free();
			dao = null;
		}

		return fReturnValue;
	}

	/**
	 * retrieves and executes Modify Query
	 * 
	 * @param strChk
	 * @param vo
	 */
	public static void modifyQuery(String chk, UnitMstVO vo) {

		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex;

		try {
			String strtemp[] = null;
			String strtemp2[] = null;
			dao = new HisDAO("Unit Master", "UnitMstDAO.modifyQuery()");
			strQuery = billing.qryHandler_billing.getQuery(1,
					"select.unitMst.1");
			nQryIndex = dao.setQuery(strQuery);

			// change by anshul
			strtemp = chk.replace('@', '#').split("#");
			strtemp2 = strtemp[1].replace('$', '#').split("#");
			strtemp[1] = strtemp2[0];

			dao.setQryValue(nQryIndex, 1, strtemp[0]);
			dao.setQryValue(nQryIndex, 2, strtemp[1]);

			WebRowSet web = dao.executeQry(nQryIndex);

			while (web.next()) {

				vo.setStrModuleVal(web.getString(1));
				vo.setStrUnitName(web.getString(2));
				vo.setStrBaseUnit(web.getString(3));
				vo.setStrEffectiveDate(web.getString(4));
				vo.setStrRemarks(web.getString(5));
				vo.setStrValid(web.getString(6));
				vo.setStrIsDecimal(web.getString(7));
			}

		} catch (Exception e) {

			new HisException("Unit Master", "UnitMstDAO.modifyQuery()", e
					.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	}

	/**
	 * retrieves and executes update Query
	 * 
	 * @param strChk
	 * @param bean
	 * @return true - if Record Updated Successfully. <br>
	 *         false - if Record Not Updated Successfully.
	 */
	public static boolean updateQuery(String chk, UnitMstVO vo) {

		boolean returnValue = false;
		HisDAO dao = null;
		String strQuery = null;
		String strQuery2 = null;
		int nQryIndex = 0;
		int nQryIndex2 = 0;
		String strtemp[] = null;
		String strtemp2[] = null;

		try {
			// String strTemp1[] = null;
			dao = new HisDAO("Unit Master ", "UnitMstDAO.updateQuery()");
			////  System.out.println("in dao update chk-->"+chk);
			// String strTemp[] = chk.replace('@', '#').split("#");
			strQuery = billing.qryHandler_billing.getQuery(1,
					"update.unitMst.0");
			nQryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQryIndex, 1, vo.getStrUnitName());
			dao.setQryValue(nQryIndex, 2, vo.getStrEffectiveDate());
			dao.setQryValue(nQryIndex, 3, vo.getStrSeatId());
			dao.setQryValue(nQryIndex, 4, vo.getStrRemarks());
			dao.setQryValue(nQryIndex, 5, vo.getStrValid());

			if (vo.getStrIsDecimal() == null
					|| vo.getStrIsDecimal().equals("")) {
				vo.setStrIsDecimal("0");
			}

			dao.setQryValue(nQryIndex, 6, vo.getStrIsDecimal());

			strtemp = chk.replace('@', '#').split("#");
			strtemp2 = strtemp[1].replace('$', '#').split("#");
			strtemp[1] = strtemp2[0];

			dao.setQryValue(nQryIndex, 7, strtemp[0]);
			dao.setQryValue(nQryIndex, 8, strtemp[1]);

			// added by anshul on 30th april 09 to update isdecimal of subunits
			strQuery2 = billing.qryHandler_billing.getQuery(1,
					"update.unitMst.1");
			nQryIndex2 = dao.setQuery(strQuery2);

		/*	System.out.println("vo.getStrSeatId()" + vo.getStrSeatId());
			System.out.println("vo.getStrIsDecimal()"
					+ vo.getStrIsDecimal());
			System.out.println("vo.strtemp0()" + strtemp[0]);
			System.out.println("vo.strtemp1()" + strtemp[1]);*/

			dao.setQryValue(nQryIndex2, 1, vo.getStrSeatId());
			dao.setQryValue(nQryIndex2, 2, vo.getStrIsDecimal());
			dao.setQryValue(nQryIndex2, 3, strtemp[0]);
			dao.setQryValue(nQryIndex2, 4, strtemp[1]);

			dao.execute(nQryIndex, 0);
			dao.execute(nQryIndex2, 0);

			synchronized (dao) {
				dao.fire();
			}
			returnValue = true;
		} catch (Exception e) {

			vo.setStrErr("Row Not Updated");
			returnValue = false;
			new HisException("Unit Master", "UnitMstDAO.updateQuery()", e
					.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return returnValue;

	}

	// //////// function by anshul- to get the values of second combo on add
	// page

	public static WebRowSet initialAddQuery(UnitMstVO vo) throws Exception {

		HisDAO dao = new HisDAO("Unit Master", "UnitMstDAO");

		int nqryIndex;

		WebRowSet wb = null;
		String strquery = billing.qryHandler_billing.getQuery(1,
				"select.unitMst.3");
		
		try {
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrModuleVal());
			dao.setQryValue(nqryIndex, 2, BillConfigUtil.SUPER_HOSPITAL_CODE);

			wb = dao.executeQry(nqryIndex);

		} catch (Exception e) {

			throw new Exception("UnitMstDAO.initialAddQuery() --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return wb;
	}

	public static String modNameQuery(UnitMstVO vo) throws Exception {

		HisDAO dao = new HisDAO("billing", "UnitMstDAO");

		int nqryIndex;
		String modName = "";

		WebRowSet wb = null;
		String strquery = billing.qryHandler_billing.getQuery(1,
				"select.moduleName.0");

		try {
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrModuleVal());
			dao.setQryValue(nqryIndex, 2, BillConfigUtil.SUPER_HOSPITAL_CODE);

			wb = dao.executeQry(nqryIndex);

			while (wb.next()) {
				modName = wb.getString(1);
			}

		} catch (Exception e) {

			throw new Exception("UnitMstDAO.modNameQuery() --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return modName;
	}

}
