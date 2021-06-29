package billing.masters.dao;

import hisglobal.masterutil.GenericVO;
import hisglobal.transactionmgnt.HisDAO;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

import billing.masters.controller.fb.VOAdvanceMst;

public class DAOAdvanceMst {

	/**
	 * inserts a new Record.
	 * 
	 * @param formBean
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */
	public static boolean insertQuery(VOAdvanceMst formBean) throws Exception {

		boolean fReturnValue = true;

		int nQueryIndex = 0;
		HisDAO dao = null;
		String strQuery = null;
		dao = new HisDAO("Advance Master", "DAOAdvanceMst.insertQuery()");
		strQuery = billing.qryHandler_billing
				.getQuery(1, "insert.advanceMst.0");
		nQueryIndex = dao.setQuery(strQuery);

		try {

			dao.setQryValue(nQueryIndex, 1, formBean.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, formBean.getStrHospitalServiceId());
			dao.setQryValue(nQueryIndex, 3, formBean.getStrWardType());
			dao.setQryValue(nQueryIndex, 4, formBean.getStrCategory());
			
			dao.setQryValue(nQueryIndex, 5, formBean.getStrWardType());
			dao.setQryValue(nQueryIndex, 6, formBean.getStrCategory());
			dao.setQryValue(nQueryIndex, 7, formBean.getStrHospitalServiceId());
			dao.setQryValue(nQueryIndex, 8, formBean.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 9, formBean.getStrSpecialWardType());
			
			dao.setQryValue(nQueryIndex, 10, formBean.getStrAdvanceAmount());
			dao.setQryValue(nQueryIndex, 11, formBean.getStrAdvanceSecurity());
			dao.setQryValue(nQueryIndex, 12, formBean.getStrPartPayment());
			dao
					.setQryValue(nQueryIndex, 13, formBean
							.getStrEffectiveFromDate());
			//dao.setQryValue(nQueryIndex, 14, formBean.getStrEffectiveToDate());
			dao.setQryValue(nQueryIndex, 14, formBean.getStrSeatId());
			dao.setQryValue(nQueryIndex, 15, formBean.getStrRemarks());
			dao.setQryValue(nQueryIndex, 16, "1");
			dao.setQryValue(nQueryIndex, 17, formBean.getStrSpecialWardType());

			dao.execute(nQueryIndex, 0);

			synchronized (dao) {
				dao.fire();
			}

		} catch (Exception e) {
			formBean.setStrErr("Record Not Saved");
			fReturnValue = false;

			String strExcMsg = "billing.master.DAOAdvanceMst.insertQuery()-->"
					+ e.getMessage();
			throw new Exception(strExcMsg);

		} finally {
			dao.free();
			dao = null;
		}

		return fReturnValue;
	}

	public static boolean checkForInsert(VOAdvanceMst formBean)
			throws Exception {

		boolean fRes = false;
		int nRes = 1;
		int nQueryIndex = 0;
		HisDAO dao = null;
		String strQuery = null;
		dao = new HisDAO("Advance Master", "DAOAdvanceMst.checkForInsert()");
		strQuery = billing.qryHandler_billing
				.getQuery(1, "select.advanceMst.8");
		nQueryIndex = dao.setQuery(strQuery);

		try {

			dao.setQryValue(nQueryIndex, 1, formBean.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, formBean.getStrSpecialWardType());
			dao.setQryValue(nQueryIndex, 3, formBean.getStrWardType());
			dao.setQryValue(nQueryIndex, 4, formBean.getStrHospitalServiceId());
			dao.setQryValue(nQueryIndex, 5, formBean.getStrCategory());

			WebRowSet wb = dao.executeQry(nQueryIndex);

			while (wb.next()) {
				nRes = Integer.parseInt(wb.getString(1));
			}

			if (nRes == 0)
				fRes = true;

		} catch (Exception e) {

			String strExcMsg = "billing.master.DAOAdvanceMst.checkForInsert()-->"
					+ e.getMessage();
			throw new Exception(strExcMsg);

		} finally {
			dao.free();
			dao = null;
		}

		return fRes;
	}

	public static void modifyQuery(String chk, VOAdvanceMst formBean)
			throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex;

		dao = new HisDAO("Advance Master", "DAOAdvanceMst.modifyQuery()");
		String strTemp[] = chk.replace('|', '#').split("#");
		/* System.out.println("chk---"+chk); */

		strQuery = billing.qryHandler_billing
				.getQuery(1, "select.advanceMst.3");
		try {
			String[] temp = strTemp[0].split("@");
			nQryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQryIndex, 1, temp[0]);
			dao.setQryValue(nQryIndex, 2, temp[1]);
			dao.setQryValue(nQryIndex, 3, temp[2]);
			dao.setQryValue(nQryIndex, 4, temp[3]);
			dao.setQryValue(nQryIndex, 5, temp[4]);
			dao.setQryValue(nQryIndex, 6, temp[5]);

			WebRowSet web = dao.executeQry(nQryIndex);

			while (web.next()) {

				formBean.setStrWardType(web.getString(1));
				formBean.setStrCategory(web.getString(2));
				formBean.setStrAdvanceAmount(web.getString(3));
				formBean.setStrPartPayment(web.getString(4));
				formBean.setStrAdvanceSecurity(web.getString(5));
				formBean.setStrEffectiveFromDate(web.getString(6));
				formBean.setStrEffectiveToDate(web.getString(7));
				formBean.setStrRemarks(web.getString(8));
				formBean.setStrHospitalServiceId(web.getString(9));
				formBean.setStrSpecialWardType(web.getString(10));

				if (formBean.getStrHospitalServiceId().equals("1")) {

					formBean.setStrHospitalService("OPD");
				} else if (formBean.getStrHospitalServiceId().equals("2")) {
					formBean.setStrHospitalService("IPD");
				} else {
					formBean.setStrHospitalService("EMERGENCY");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			String strExcMsg = " billing.master.DAOAdvanceMst.modifyQuery()--> "
					+ e.getMessage();
			throw new Exception(strExcMsg);
		} finally {
			dao.free();
			dao = null;
		}
	}

	/**
	 * updates the Current Record.
	 * 
	 * @param chk
	 * @param bean
	 * @return true - if Record Updated Successfully. <br>
	 *         false - if Record Not Updated Successfully.
	 */

	public static boolean updateQuery(String chk, VOAdvanceMst bean) {

		boolean fReturnValue = false;
		HisDAO dao = null;
		String strQuery = null;
		int strQryIndex = 0;

		try {
			String strTemp1[] = null;
			dao = new HisDAO("Advance Master ", "DAOAdvanceMst.updateQuery()");
			String strTemp[] = chk.replace('@', '#').split("#");
			strQuery = billing.qryHandler_billing.getQuery(1,
					"update.advanceMst.0");
			strQryIndex = dao.setQuery(strQuery);

			dao.setQryValue(strQryIndex, 1, bean.getStrAdvanceAmount());
			dao.setQryValue(strQryIndex, 2, bean.getStrPartPayment());
			dao.setQryValue(strQryIndex, 3, bean.getStrAdvanceSecurity());
			dao.setQryValue(strQryIndex, 4, bean.getStrEffectiveFromDate());
			dao.setQryValue(strQryIndex, 5, bean.getStrEffectiveToDate());
			dao.setQryValue(strQryIndex, 6, bean.getStrLastModSeatId());
			dao.setQryValue(strQryIndex, 7, bean.getStrRemarks());

			for (int i = 0; i < strTemp.length; i++) {

				strTemp1 = strTemp[i].replace('|', '#').split("#");

				dao.setQryValue(strQryIndex, (i + 8), strTemp1[0]);
			}

			dao.execute(0, strQryIndex);

			synchronized (dao) {
				dao.fire();
			}
			fReturnValue = true;
		} catch (Exception e) {

			bean.setStrErr("Row Not Updated");
			fReturnValue = false;
			new Exception("DAOAdvanceMst.updateQuery()" + e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return fReturnValue;
	}

	/**
	 * Updates Old Records Effective To Date and Inserts New Record by getting
	 * the Current Record Primary Key and Max(SNo)+1 in the Modification Logic.
	 * 
	 * @param formBean
	 * @param chk
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */
	public static boolean insertNewQuery(VOAdvanceMst formBean, String chk)
			throws Exception

	{
		boolean fReturnValue = true;

		HisDAO dao = null;

		dao = new HisDAO("Advance Master", "DAOAdvanceMst.insertNewQuery()");

		String strQuery = billing.qryHandler_billing.getQuery(1,
				"insert.advanceMst.1");
		int nQueryIndex = 0;

		String strTemp[] = chk.replace('$', '#').split("#");

		try {
			String[] temp = strTemp[0].split("@");
			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, temp[4]); // hosp 
			dao.setQryValue(nQueryIndex, 2, temp[0]); // ward code  
			dao.setQryValue(nQueryIndex, 3, temp[1]); // ipd charge 
			dao.setQryValue(nQueryIndex, 4, temp[2]); // pat cat 
			
			dao.setQryValue(nQueryIndex, 5, temp[1]);
			dao.setQryValue(nQueryIndex, 6, temp[2]);
			dao.setQryValue(nQueryIndex, 7, temp[0]);
			dao.setQryValue(nQueryIndex, 8, formBean.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 9, temp[3]);
			dao.setQryValue(nQueryIndex, 10, formBean.getStrAdvanceAmount() );
			dao.setQryValue(nQueryIndex, 11, formBean.getStrAdvanceSecurity());
			dao.setQryValue(nQueryIndex, 12, formBean.getStrPartPayment());
			dao
					.setQryValue(nQueryIndex, 13, formBean
							.getStrEffectiveFromDate());
			dao.setQryValue(nQueryIndex, 14, formBean.getStrEffectiveToDate());
			dao.setQryValue(nQueryIndex, 15, formBean.getStrSeatId());
			dao.setQryValue(nQueryIndex, 16, formBean.getStrLastModSeatId());
			dao.setQryValue(nQueryIndex, 17, formBean.getStrRemarks());
			dao.setQryValue(nQueryIndex, 18, "1");
			dao.setQryValue(nQueryIndex, 19, temp[3]);

			dao.execute(nQueryIndex, 0);

			synchronized (dao) {
				dao.fire();
			}

		} catch (Exception e1) {
			fReturnValue = false;
			String strExcMsg = "billing.master.DAOAdvanceMst.insertNewQuery()-->"
					+ e1.getMessage();
			throw new Exception(strExcMsg);
		}
		/*
		 * String strQuery1 = billing.qryHandler_billing.getQuery(1,
		 * "insert.advanceMst.1"); int index1;
		 * 
		 * try { String[] temp = strTemp[0].split("@"); index1 =
		 * dao.setQuery(strQuery1); //System.out.println("temp[0]--2-"+temp[0]);
		 * //System.out.println("temp[1]--3-"+temp[1]);
		 * //System.out.println("temp[2]--4-"+temp[2]);
		 * //System.out.println("temp[3]--5-"+temp[3]);
		 * //System.out.println("getStrAdvanceAmount--"+formBean.getStrAdvanceAmount());
		 * //System.out.println("getStrAdvanceSecurity---"+formBean.getStrAdvanceSecurity());
		 * //System.out.println("getStrPartPayment--"+formBean.getStrPartPayment());
		 * //System.out.println("getStrEffectiveFromDate--"+formBean.getStrEffectiveFromDate());
		 * //System.out.println("getStrEffectiveToDate--"+formBean.getStrEffectiveToDate());
		 * //System.out.println("getStrSeatId--"+formBean.getStrSeatId());
		 * //System.out.println("getStrSeatId--"+formBean.getStrSeatId());
		 * //System.out.println("getStrRemarks--"+formBean.getStrRemarks());
		 * 
		 * dao.setQryValue(index1, 1, temp[0]); dao.setQryValue(index1, 2,
		 * temp[1]); dao.setQryValue(index1, 3, temp[2]);
		 * dao.setQryValue(index1, 3, temp[3]); dao.setQryValue(index1, 4,
		 * formBean.getStrAdvanceAmount()); dao.setQryValue(index1, 5,
		 * formBean.getStrAdvanceSecurity()); dao.setQryValue(index1, 6,
		 * formBean.getStrPartPayment()); dao.setQryValue(index1, 7,
		 * formBean.getStrEffectiveFromDate()); dao.setQryValue(index1, 8,
		 * formBean.getStrEffectiveToDate()); dao.setQryValue(index1, 9,
		 * formBean.getStrSeatId()); dao.setQryValue(index1, 10,
		 * formBean.getStrSeatId()); dao.setQryValue(index1, 11,
		 * formBean.getStrRemarks()); dao.setQryValue(index1, 12, "1");
		 * 
		 * dao.execute(nQueryIndex1, 0);
		 * 
		 * synchronized (dao) { dao.fire(); } } catch (Exception e) {
		 * formBean.setStrErr("Row Not Updated"); fReturnValue = false;
		 * 
		 * String strExcMsg = "billing.master.DAOAdvanceMst.insertNewQuery()-->" +
		 * e.getMessage(); throw new Exception(strExcMsg); }
		 */
		finally {
			dao.free();
			dao = null;
		}

		return fReturnValue;
	}

	/**
	 * updateLogic method is used to check whether Effective To Date is less
	 * than Effective From Date of the Next Record if exists.
	 * 
	 * @param strChk
	 * @param formBean
	 * @return 0 - if not exists. <br>
	 *         >=1 - if exists
	 */

	public int updateLogic(String strChk, VOAdvanceMst formBean)
			throws Exception {

		int nRes = 1;
		String strTemp[] = strChk.replace('$', '#').split("#");

		HisDAO dao = new HisDAO("Advance Master", "DAOAdvanceMst.update()");
		String strQuery = billing.qryHandler_billing.getQuery(1,
				"select.advanceMst.5");
		int nQueryIndex = dao.setQuery(strQuery);

		try {

			String[] temp = strTemp[0].split("@");

			dao.setQryValue(nQueryIndex, 1, temp[0]);
			dao.setQryValue(nQueryIndex, 2, temp[1]);
			dao.setQryValue(nQueryIndex, 3, temp[2]);
			dao.setQryValue(nQueryIndex, 4, temp[3]);
			dao.setQryValue(nQueryIndex, 5, temp[4]);

			// dao.setQryValue(nQueryIndex, 3, String.valueOf(nSNo + 1));
			dao.setQryValue(nQueryIndex, 6, formBean.getStrEffectiveFromDate());
			dao.setQryValue(nQueryIndex, 7, formBean.getStrEffectiveToDate());
			dao.setQryValue(nQueryIndex, 8, formBean.getStrEffectiveToDate());

			WebRowSet wb = dao.executeQry(nQueryIndex);

			while (wb.next()) {
				nRes = Integer.parseInt(wb.getString(1));
			}

		} catch (SQLException e) {
			throw new Exception("billing.master.DAOAdvanceMst.updateLogic()-->"
					+ e.getMessage());
		} catch (Exception e) {
			throw new Exception("billing.master.DAOAdvanceMst.updateLogic()-->"
					+ e.getMessage());
		}

		return nRes;
	}

	/**
	 * method is use to check whether the Effective From Date is Valid or Not.
	 * 
	 * @param formBean
	 * @param strChk
	 * @return 0 - if the Effective From Date is Valid. <br> >= 1 - if the
	 *         Effective From Date is not Valid.
	 */
	public int insertNewInitLogic(VOAdvanceMst formBean, String strChk)
			throws Exception {

		int nRes = 1;
		HisDAO dao = new HisDAO("Advance Master", "DAOAdvanceMst.update()");
		String strQuery = billing.qryHandler_billing.getQuery(1,
				"select.advanceMst.6");
		int index;

		String strTemp[] = strChk.replace('$', '#').split("#");

		try {
			String[] temp = strTemp[0].split("@");
			index = dao.setQuery(strQuery);

			dao.setQryValue(index, 1, temp[0]);
			dao.setQryValue(index, 2, temp[1]);
			dao.setQryValue(index, 3, temp[2]);
			dao.setQryValue(index, 4, temp[3]);
			dao.setQryValue(index, 5, temp[4]);
			dao.setQryValue(index, 6, formBean.getStrEffectiveFromDate());

			WebRowSet wb = dao.executeQry(index);

			while (wb.next()) {
				nRes = Integer.parseInt(wb.getString(1));
			}

		} catch (Exception e) {

			String strExcMsg = "billing.master.DAOAdvanceMst.insertNewInitLogic()-->"
					+ e.getMessage();
			throw new Exception(strExcMsg);
		}

		if (nRes != 0) {
			formBean
					.setStrWarning("Effective From Date ("
							+ formBean.getStrEffectiveFromDate()
							+ ") exists between the effective from date and effective to date of the existing records");
		}

		return nRes;
	}

	/**
	 * method used to find whether the Effective From Date is valid if Effective
	 * To Date is Null or Empty.
	 * 
	 * @param formBean
	 * @param strChk
	 * @return 0 - if the Effective From Date is Valid. <br> >= 1 - if the
	 *         Effective From Date is not Valid.
	 */
	/*
	 * public int effToDtNull(VOAdvanceMst formBean, String strChk) {
	 * 
	 * HisDAO dao = new HisDAO("Advance Master", "DAOAdvanceMst.update()");
	 * String strQuery = billing.qryHandler_billing.getQuery(1,
	 * "select.advanceMst.7"); int nQueryIndex = dao.setQuery(strQuery); int
	 * nRes = 1;
	 * 
	 * try { String strTemp[] = strChk.replaceAll("@", "#").split("#");
	 * dao.setQryValue(nQueryIndex, 1, strTemp[0]); dao.setQryValue(nQueryIndex,
	 * 2, strTemp[1]); dao.setQryValue(nQueryIndex, 3,
	 * formBean.getStrEffectiveFromDate()); WebRowSet wb =
	 * dao.executeQry(nQueryIndex);
	 * 
	 * while (wb.next()) { nRes = Integer.parseInt(wb.getString(1)); } } catch
	 * (Exception e) { new Exception("DAOAdvanceMst.effToDtNotNull()"+
	 * e.getMessage()); }
	 * 
	 * return nRes; }
	 */

	/**
	 * method used to find whether the Effective To Date is valid.
	 * 
	 * @param formBean
	 * @param strChk
	 * @return 0 - if the Effective To Date is Valid. <br> >= 1 - if the
	 *         Effective To Date is not Valid.
	 */
	public int effToDtNotNull(VOAdvanceMst formBean, String strChk)
			throws Exception {

		HisDAO dao = new HisDAO("Advance Master", "DAOAdvanceMst.update()");
		String strQuery = billing.qryHandler_billing.getQuery(1,
				"select.advanceMst.7");
		int index;
		int nRes = 1;

		String strTemp[] = strChk.replace('$', '#').split("#");

		try {
			String[] temp = strTemp[0].split("@");
			index = dao.setQuery(strQuery);

			dao.setQryValue(index, 1, temp[0]);
			dao.setQryValue(index, 2, temp[1]);
			dao.setQryValue(index, 3, temp[2]);
			dao.setQryValue(index, 4, temp[3]);
			dao.setQryValue(index, 5, temp[4]);
			dao.setQryValue(index, 6, formBean.getStrEffectiveFromDate());
			dao.setQryValue(index, 7, formBean.getStrEffectiveToDate());
			dao.setQryValue(index, 8, formBean.getStrEffectiveToDate());

			WebRowSet wb = dao.executeQry(index);

			while (wb.next()) {

				nRes = Integer.parseInt(wb.getString(1));

			}

		} catch (SQLException e) {
			String strExcMsg = "billing.master.DAOAdvanceMst.effToDtNotNull()-->"
					+ e.getMessage();
			throw new Exception(strExcMsg);
		} catch (Exception e) {
			String strExcMsg = "billing.master.DAOAdvanceMst.effToDtNotNull()-->"
					+ e.getMessage();
			throw new Exception(strExcMsg);
		}
		if (nRes != 0) {
			formBean
					.setStrWarning("Effective To Date ("
							+ formBean.getStrEffectiveToDate()
							+ ") exists between the effective from date and effective to date of the existing records");
		}
		return nRes;
	}

	/**
	 * method updates the Effective To Date of the Previous Record.
	 * 
	 * @param formBean
	 * @param strChk
	 * @return true - if Effective To Date of the Previous Record is Updated
	 *         Successfully. false - if Effective To Date of the Previous Record
	 *         is not Updated Successfully.
	 */
	public boolean updatePrevToDt(VOAdvanceMst formBean, String strChk) {

		HisDAO dao = new HisDAO("Advance Master", "DAOAdvanceMst.update()");
		String strQuery = billing.qryHandler_billing.getQuery(1,
				"update.advanceMst.1");

		boolean fResult = false;
		int nQueryIndex = dao.setQuery(strQuery);
		String strTemp[] = strChk.replaceAll("@", "#").split("#");

		//String temp[] = strTemp[4].replace("$", "#").split("#");

		try {

			dao.setQryValue(nQueryIndex, 1, formBean.getStrEffectiveFromDate());
			dao.setQryValue(nQueryIndex, 2, strTemp[0]);
			dao.setQryValue(nQueryIndex, 3, strTemp[1]);
			dao.setQryValue(nQueryIndex, 4, strTemp[2]);
			dao.setQryValue(nQueryIndex, 5, strTemp[3]);
			dao.setQryValue(nQueryIndex, 6, strTemp[4]);
			dao.setQryValue(nQueryIndex, 7, formBean.getStrEffectiveFromDate());

			dao.execute(nQueryIndex, 0);

			synchronized (dao) {
				dao.fire();
				fResult = true;
			}

		} catch (Exception e) {

			fResult = false;
			new Exception("DAOAdvanceMst.updatePrevToDt()" + e.getMessage());
		}

		return fResult;
	}
	
	public static void correctUpdateData(VOAdvanceMst formBean, String strChk) {

		HisDAO dao = new HisDAO("Advance Master", "DAOAdvanceMst.update()");
		String strTemp[] = strChk.replaceAll("@", "#").replace("$", "#").split("#");

		String strQuery;

		int nQueryIndex;
		try {
			if("0".equals(formBean.getStrUpdateMode()))//Correction
			{
				strQuery = billing.qryHandler_billing.getQuery(1,"update.advanceMst.3");

				nQueryIndex = dao.setQuery(strQuery);
				dao.setQryValue(nQueryIndex, 1, formBean.getStrAdvanceAmount());
				dao.setQryValue(nQueryIndex, 2, "0");
				dao.setQryValue(nQueryIndex, 3, formBean.getStrPartPayment());
				dao.setQryValue(nQueryIndex, 4, formBean.getStrSeatId());
				dao.setQryValue(nQueryIndex, 5, formBean.getStrRemarks());
				dao.setQryValue(nQueryIndex, 6, strTemp[5].replace("|", "#").split("#")[0]);
				dao.setQryValue(nQueryIndex, 7, strTemp[4]);
				dao.setQryValue(nQueryIndex, 8, strTemp[3]);
				dao.setQryValue(nQueryIndex, 9, strTemp[2]);
				dao.setQryValue(nQueryIndex, 10, strTemp[1]);
				dao.setQryValue(nQueryIndex, 11, strTemp[0]);

				dao.execute(nQueryIndex, 0);				
			} 
			else if("1".equals(formBean.getStrUpdateMode()))//Updation
			{
				strQuery = billing.qryHandler_billing.getQuery(1,"update.advanceMst.2");

				nQueryIndex = dao.setQuery(strQuery);
				dao.setQryValue(nQueryIndex, 1, formBean.getStrEffectiveFromDate());
				dao.setQryValue(nQueryIndex, 2, formBean.getStrAdvanceAmount());
				dao.setQryValue(nQueryIndex, 3, formBean.getStrPartPayment());
				dao.setQryValue(nQueryIndex, 4, "0");				
				dao.setQryValue(nQueryIndex, 5, formBean.getStrSeatId());
				dao.setQryValue(nQueryIndex, 6, formBean.getStrRemarks());
				dao.setQryValue(nQueryIndex, 7, strTemp[5].replace("|", "#").split("#")[0]);
				dao.setQryValue(nQueryIndex, 8, strTemp[4]);
				dao.setQryValue(nQueryIndex, 9, strTemp[3]);
				dao.setQryValue(nQueryIndex, 10, strTemp[2]);
				dao.setQryValue(nQueryIndex, 11, strTemp[1]);
				dao.setQryValue(nQueryIndex, 12, strTemp[0]);

				dao.execute(nQueryIndex, 0);
				strQuery = billing.qryHandler_billing.getQuery(1,"insert.advanceMst.1");

				nQueryIndex = dao.setQuery(strQuery);
				dao.setQryValue(nQueryIndex, 1, strTemp[0]);  
				dao.setQryValue(nQueryIndex, 2, strTemp[1]);  
				dao.setQryValue(nQueryIndex, 3, strTemp[2]); 
				 				
				dao.setQryValue(nQueryIndex, 4, strTemp[1]);
				dao.setQryValue(nQueryIndex, 5, strTemp[2]);
				dao.setQryValue(nQueryIndex, 6, strTemp[0]);
				dao.setQryValue(nQueryIndex, 7, strTemp[4]);
				dao.setQryValue(nQueryIndex, 8, strTemp[3]);
				
				dao.setQryValue(nQueryIndex, 9, strTemp[4]);  
				
				dao.setQryValue(nQueryIndex, 10, formBean.getStrAdvanceAmount());
				dao.setQryValue(nQueryIndex, 11, "0");
				dao.setQryValue(nQueryIndex, 12, formBean.getStrPartPayment());
				dao.setQryValue(nQueryIndex, 13, formBean.getStrEffectiveFromDate());
				dao.setQryValue(nQueryIndex, 14, formBean.getStrSeatId());
				dao.setQryValue(nQueryIndex, 15, formBean.getStrRemarks());
				dao.setQryValue(nQueryIndex, 16, strTemp[3]);

				dao.execute(nQueryIndex, 0);
				
				
			}

			synchronized (dao) {
				dao.fire();
			}

		} catch (Exception e) {
		 e.printStackTrace();
		 
		 new Exception("DAOAdvanceMst.correctUpdateData()" + e.getMessage());
		 
		}
	}
	/*public static void deleteData(VOAdvanceMst formBean, String[] strChk) {

		HisDAO dao = new HisDAO("Advance Master", "DAOAdvanceMst.update()");

		String strQuery;
		int nQueryIndex;
		try {

			for(int nTmpI = 0;nTmpI<strChk.length; nTmpI++){

				String strTemp[] = strChk[nTmpI].replaceAll("@", "#").replace("|", "#").split("#");
				strQuery = billing.qryHandler_billing.getQuery(1,"delete.advanceMst.0");
	
				String temp = strTemp[7].replace("$", "#").split("#")[0];
				
				
				nQueryIndex = dao.setQuery(strQuery);
				dao.setQryValue(nQueryIndex, 1, formBean.getStrHospitalCode());
				dao.setQryValue(nQueryIndex, 2, strTemp[3]); // Ward Code
				dao.setQryValue(nQueryIndex, 3, strTemp[1]); // IPD Charge Type
				dao.setQryValue(nQueryIndex, 4, strTemp[0]); // Charge Type | Hosp serv
				dao.setQryValue(nQueryIndex, 5, strTemp[2]); // Pat Cat 
				dao.setQryValue(nQueryIndex, 6, temp.trim());
	
				dao.execute(nQueryIndex, 0);
				strQuery = billing.qryHandler_billing.getQuery(1,"delete.advanceMst.1");
	
				nQueryIndex = dao.setQuery(strQuery);
				dao.setQryValue(nQueryIndex, 1, strTemp[5]);
				dao.setQryValue(nQueryIndex, 2, formBean.getStrHospitalCode());
				dao.setQryValue(nQueryIndex, 3, strTemp[3]); // Ward Code 
				dao.setQryValue(nQueryIndex, 4, strTemp[1]); // IPD Charge Type
				dao.setQryValue(nQueryIndex, 5, strTemp[0]); // Charge Type | Hosp Serv
				dao.setQryValue(nQueryIndex, 6, strTemp[2]); // Pat Cat 
	
				dao.execute(nQueryIndex, 0);
			}
			
			synchronized (dao) {
				dao.fire();
			}

		} catch (Exception e) {
		 
			e.printStackTrace();
			
			 new Exception("DAOAdvanceMst.deleteData()" + e.getMessage());
			
			
		}
	}*/
	public static void deleteData(String hosCode,GenericVO formBean, String Chk) {

		HisDAO dao = new HisDAO("Advance Master", "DAOAdvanceMst.update()");

		String strQuery;
		int nQueryIndex;
		int nTmpI = 0;
		String[] strChk=Chk.replaceAll("~", "#").split("#");
		try {

			for(nTmpI = 0;nTmpI<strChk.length; nTmpI++){

				String strTemp[] = strChk[nTmpI].replaceAll("@", "#").replace("|", "#").split("#");
				strQuery = billing.qryHandler_billing.getQuery(1,"delete.advanceMst.0");
	
				String temp = strTemp[7].replace("$", "#").split("#")[0];
				
				
				nQueryIndex = dao.setQuery(strQuery);
				dao.setQryValue(nQueryIndex, 1, hosCode);
				dao.setQryValue(nQueryIndex, 2, strTemp[3]); // Ward Code
				dao.setQryValue(nQueryIndex, 3, strTemp[1]); // IPD Charge Type
				dao.setQryValue(nQueryIndex, 4, strTemp[0]); // Charge Type | Hosp serv
				dao.setQryValue(nQueryIndex, 5, strTemp[2]); // Pat Cat 
				dao.setQryValue(nQueryIndex, 6, temp.trim());
	
				dao.execute(nQueryIndex, 0);
				strQuery = billing.qryHandler_billing.getQuery(1,"delete.advanceMst.1");
	
				nQueryIndex = dao.setQuery(strQuery);
				dao.setQryValue(nQueryIndex, 1, strTemp[5]);
				dao.setQryValue(nQueryIndex, 2, hosCode);
				dao.setQryValue(nQueryIndex, 3, strTemp[3]); // Ward Code 
				dao.setQryValue(nQueryIndex, 4, strTemp[1]); // IPD Charge Type
				dao.setQryValue(nQueryIndex, 5, strTemp[0]); // Charge Type | Hosp Serv
				dao.setQryValue(nQueryIndex, 6, strTemp[2]); // Pat Cat 
	
				dao.execute(nQueryIndex, 0);
			}
			
			synchronized (dao) {
				dao.fire();
			}
			formBean.setStrMsgString(""+nTmpI);    		    
			formBean.setStrMsgType("0");

		} catch (Exception e) {
		 
			e.printStackTrace();
			formBean.setStrMsgType("1");
			 new Exception("DAOAdvanceMst.deleteData()" + e.getMessage());
			
			
		}
	}
}
