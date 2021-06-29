package ipd.masters.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import ipd.qryHandler_ipd;
import ipd.masters.vo.WardCriteriaMstVO;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

public class WardCriteriaMstDAO {

	/**
	 * retrieves and executes insert Query
	 * 
	 * @param vo
	 *            -Form Object of the Current Master
	 * @return - If Record Inserted Successfully. <br>
	 *         false - If Record Not Inserted Successfully
	 * @throws Exception
	 */
	public static boolean insert(WardCriteriaMstVO vo) throws Exception {

		boolean fretValue = false;
		HisDAO hisdao = new HisDAO("ipd", "WardCriteriaMstDAO.insert()");

		try {
			fretValue = insertCriteria(vo, hisdao);
			synchronized (hisdao) {
				if (fretValue)
					hisdao.fire();
			}
		} catch (Exception e) {
			e.printStackTrace();
			fretValue = false;
			throw new Exception("ipd.WardCriteriaMstDAO.insert() -> "
					+ e.getMessage());
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return fretValue;
	}

	/**
	 * updates the Current Record.
	 * 
	 * @param chk -
	 *            Primary Keys Concatenated with '@'.
	 * @param vo -
	 *            Form Object of the Current Master.
	 * @return -true - if Record Updated Successfully. <br>
	 *         false - if Record Not Updated Successfully.
	 * @throws Exception
	 */
	public static boolean update(String chk, WardCriteriaMstVO vo)
			throws Exception {
		boolean fretValue = false;
		HisDAO hisdao = new HisDAO("ipd", "WardCriteriaMstDAO.insert()");
		try {
			fretValue = deleteCriteria(vo, hisdao, chk);

			if (fretValue) {
				fretValue = insertCriteria(vo, hisdao);
			}

			if (fretValue) {
				synchronized (hisdao) {
					hisdao.fire();

					fretValue = true;

				}

			}
		} catch (Exception e) {
			fretValue = false;
			throw new Exception("ipd.WardCriteriaMstDAO.update" + e.getMessage());
		}
		return fretValue;
	}

	public static boolean deleteCriteria(WardCriteriaMstVO vo, HisDAO hisdao,
			String chk1) throws Exception {
		boolean fretValue = true;
		int nindex = 0;
		String strquery = "";
		String[] strtemp = null;
		strtemp = chk1.replace('$', '#').split("#");
		try {
			String[] strtemp1 = strtemp[0].replaceAll("@", "#").split("#"); // splitting
			strquery = qryHandler_ipd.getQuery(1, "select.wardcriteria.11");
			nindex = hisdao.setQuery(strquery);
			hisdao.setQryValue(nindex, 1, vo.getStrLastModifySeatId());
			hisdao.setQryValue(nindex, 2, strtemp1[0]); // hospital code
			hisdao.setQryValue(nindex, 3, strtemp1[1]); // ward code
			hisdao.setQryValue(nindex, 4, strtemp1[3]);
			// hisdao.setQryValue(nindex, 4,strtemp1[2]);//sl no

			hisdao.execute(nindex, 0);

		} catch (Exception e) {
			fretValue = false;
			throw new Exception("ipd.WardCriteriaMstDAO.deleteCriteria"
					+ e.getMessage());
		}
		return fretValue;
	}

	/**
	 * Insert data into ward criteria master.
	 * 
	 * @param vo
	 * @param hisdao
	 * @return
	 * @throws Exception
	 */
	public static boolean insertCriteria(WardCriteriaMstVO vo, HisDAO hisdao)
			throws Exception {

		boolean fretValue = true;
		int nindex = 0;
		int stragelength = 0;
		int strtreatmentlength = 0;
		int strdiseaselength = 0;

		String strTempAgeFrom = "-1";
		String strTempAgeTo = "-1";
		String strTempGender = "-1";
		String strTempUnit = "-1";
		String strTempCatg = "-1";
		String strTempDisease = "-1";
		String strquery = "";
		WebRowSet wb = null;

		try {
			strquery = qryHandler_ipd.getQuery(1, "insert.wardcriteria.0");
			nindex = hisdao.setQuery(strquery);

			// check for age criteria

			if (vo.getStrChkAge().equals("1")) {
				stragelength = 1;
			} else {

				stragelength = vo.getStrGender().length;
			}
			// check for Treatment criteria
			if (vo.getStrChkTreatment().equals("1")) {
				strtreatmentlength = 1;
			} else {
				strtreatmentlength = vo.getStrRCategory().length;
			}
			// check for disease type criteria
			if (vo.getStrChkDisease().equals("1")) {
				strdiseaselength = 1;
			} else {
				strdiseaselength = vo.getStrRDisease().length;
			}

			for (int i = 0; i < stragelength; i++) {
				if (vo.getStrChkAge().equals("0")) {
					strTempAgeFrom = vo.getStrFromAge()[i];
					strTempAgeTo = vo.getStrToAge()[i];
					strTempGender = vo.getStrGender()[i];
					strTempUnit = vo.getStrFunit()[i];
				}

				for (int j = 0; j < strtreatmentlength; j++) {
					if (vo.getStrChkTreatment().equals("0")) {
						strTempCatg = vo.getStrRCategory()[j];
					}

					for (int k = 0; k < strdiseaselength; k++) {
						if (vo.getStrChkDisease().equals("0")) {
							strTempDisease = vo.getStrRDisease()[k];
						}

						if (vo.getHmode().equals("SAVE")) {
							if (!vo.getStrRoomNo().equals("0")) {
								
								
								
								
								
								hisdao.setQryValue(nindex, 1, vo
										.getStrWardCmb());
								hisdao.setQryValue(nindex, 2, vo
										.getStrHospitalCode());
								hisdao.setQryValue(nindex, 3, vo
										.getStrWardCmb());
								hisdao.setQryValue(nindex, 4, strTempAgeFrom);
								hisdao.setQryValue(nindex, 5, strTempAgeTo);
								hisdao.setQryValue(nindex, 6, strTempGender);
								hisdao.setQryValue(nindex, 7, vo
										.getStrEffectiveFrom());
								hisdao
										.setQryValue(nindex, 8, vo
												.getStrRemark());
								hisdao
										.setQryValue(nindex, 9, vo
												.getStrSeatId());
								hisdao.setQryValue(nindex, 10, strTempUnit);
								hisdao.setQryValue(nindex, 11, strTempCatg);
								hisdao.setQryValue(nindex, 12, vo
										.getStrHospitalCode());
								hisdao.setQryValue(nindex, 13, strTempDisease);
								hisdao.setQryValue(nindex, 14, vo
										.getStrRoomNo());
								hisdao.execute(nindex, 0);
							} else {
								wb = getRoomInWardWOCriteria(vo);
								while (wb.next()) {
									hisdao.setQryValue(nindex, 1, vo
											.getStrWardCmb());
									hisdao.setQryValue(nindex, 2, vo
											.getStrHospitalCode());
									hisdao.setQryValue(nindex, 3, vo
											.getStrWardCmb());
									hisdao.setQryValue(nindex, 4,
											strTempAgeFrom);
									hisdao.setQryValue(nindex, 5, strTempAgeTo);
									hisdao
											.setQryValue(nindex, 6,
													strTempGender);
									hisdao.setQryValue(nindex, 7, vo
											.getStrEffectiveFrom());
									hisdao.setQryValue(nindex, 8, vo
											.getStrRemark());
									hisdao.setQryValue(nindex, 9, vo
											.getStrSeatId());
									hisdao.setQryValue(nindex, 10, strTempUnit);
									hisdao.setQryValue(nindex, 11, strTempCatg);
									hisdao.setQryValue(nindex, 12, vo
											.getStrHospitalCode());
									hisdao.setQryValue(nindex, 13,
											strTempDisease);
									hisdao.setQryValue(nindex, 14, wb
											.getString(1));
									hisdao.execute(nindex, 0);
								}
							}
						} else if (vo.getHmode().equals("UPDATE")) {

							hisdao.setQryValue(nindex, 1, vo.getStrWardModi());
							hisdao.setQryValue(nindex, 2, vo
									.getStrHospitalCode());
							hisdao.setQryValue(nindex, 3, vo.getStrWardModi());
							hisdao.setQryValue(nindex, 4, strTempAgeFrom);
							hisdao.setQryValue(nindex, 5, strTempAgeTo);
							hisdao.setQryValue(nindex, 6, strTempGender);
							hisdao.setQryValue(nindex, 7, vo
									.getStrEffectiveFrom());
							hisdao.setQryValue(nindex, 8, vo.getStrRemark());
							hisdao.setQryValue(nindex, 9, vo
									.getStrLastModifySeatId());
							hisdao.setQryValue(nindex, 10, strTempUnit);
							hisdao.setQryValue(nindex, 11, strTempCatg);
							hisdao.setQryValue(nindex, 12, vo
									.getStrHospitalCode());
							hisdao.setQryValue(nindex, 13, strTempDisease);
							hisdao.setQryValue(nindex, 14, vo.getStrRoomNo());
							hisdao.execute(nindex, 0);
						}
						
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			fretValue = false;
			vo.setStrErrorMsg("Data Not Modified");
			throw new Exception("ipd.WardCriteriaMstDAO.insertData "
					+ e.getMessage());
		}
		return fretValue;
	}

	public static boolean insertModify(WardCriteriaMstVO vo, HisDAO hisdao)
			throws Exception {
		boolean fretValue = true;
		int nindex = 0;
		int stragelength = 0;
		int strtreatmentlength = 0;
		int strdiseaselength = 0;

		String strTempAgeFrom = "-1";
		String strTempAgeTo = "-1";
		String strTempGender = "-1";
		String strTempUnit = "-1";
		String strTempCatg = "-1";
		String strTempDisease = "-1";
		String strquery = "";

		try {
			strquery = qryHandler_ipd.getQuery(1, "insert.wardcriteria.0");
			nindex = hisdao.setQuery(strquery);
			// check for age criteria

			if (vo.getStrChkAge().equals("1")) {
				stragelength = 1;
			} else {

				stragelength = vo.getStrGender().length;
			}
			// check for Treatment criteria
			if (vo.getStrChkTreatment().equals("1")) {
				strtreatmentlength = 1;
			} else {
				strtreatmentlength = vo.getStrRCategory().length;
			}
			// check for disease type criteria
			if (vo.getStrChkDisease().equals("1")) {
				strdiseaselength = 1;
			} else {
				strdiseaselength = vo.getStrRDisease().length;
			}
			for (int i = 0; i < stragelength; i++) {
				if (vo.getStrChkAge().equals("0")) {
					strTempAgeFrom = vo.getStrFromAge()[i];
					strTempAgeTo = vo.getStrToAge()[i];
					strTempGender = vo.getStrGender()[i];
					strTempUnit = vo.getStrFunit()[i];
				}

				for (int j = 0; j < strtreatmentlength; j++) {
					if (vo.getStrChkTreatment().equals("0")) {
						strTempCatg = vo.getStrRCategory()[j];
					}

					for (int k = 0; k < strdiseaselength; k++) {
						if (vo.getStrChkDisease().equals("0")) {
							strTempDisease = vo.getStrRDisease()[k];
						}

						hisdao.setQryValue(nindex, 1, vo.getStrWardModi());
						hisdao.setQryValue(nindex, 2, vo.getStrHospitalCode());
						hisdao.setQryValue(nindex, 3, vo.getStrWardModi());
						hisdao.setQryValue(nindex, 4, strTempAgeFrom);
						hisdao.setQryValue(nindex, 5, strTempAgeTo);
						hisdao.setQryValue(nindex, 6, strTempGender);
						hisdao.setQryValue(nindex, 7, vo.getStrEffectiveFrom());
						hisdao.setQryValue(nindex, 8, vo.getStrRemark());
						hisdao.setQryValue(nindex, 9, vo
								.getStrLastModifySeatId());
						hisdao.setQryValue(nindex, 10, strTempUnit);
						hisdao.setQryValue(nindex, 11, strTempCatg);
						hisdao.setQryValue(nindex, 12, vo.getStrHospitalCode());
						hisdao.setQryValue(nindex, 13, strTempDisease);
						hisdao.execute(nindex, 0);
					}
				}
			}

		} catch (Exception e) {
			fretValue = false;
			vo.setStrErrorMsg("Data Not Modified");
			throw new Exception("ipd.WardCriteriaMstDAO.insertData "
					+ e.getMessage());
		}
		return fretValue;

	}

	/**
	 * retrieves and executes modify Query
	 * 
	 * @param strchk1 -
	 *            Primary Keys Concatenated with '@'.
	 * @param vo -
	 *            Form Object of the Current Master
	 * @throws Exception
	 */
	public static boolean modifyQuery(String chk1, WardCriteriaMstVO vo)
			throws Exception {

		boolean fretValue = false;
		HisDAO hisdao = new HisDAO("ipd", "WardCriteriaMstDAO");
		int nindex1, nindex2;
		WebRowSet wb = null;
		String[] strtemp = null;
		strtemp = chk1.replace('$', '#').split("#");
		try {

			String strquery1 = qryHandler_ipd.getQuery(1,
					"select.wardcriteria.6");// general
			String strquery2 = qryHandler_ipd.getQuery(1,
					"select.wardcriteria.5");// for age,sex criteria
			String[] strtemp1 = strtemp[0].replaceAll("@", "#").split("#"); // splitting
			// the
			// 'chk'
			// value
			// to
			// get
			// both
			// primary
			// keys.
			nindex1 = hisdao.setQuery(strquery1);

			hisdao.setQryValue(nindex1, 1, strtemp1[0]);
			hisdao.setQryValue(nindex1, 2, strtemp1[1]);
			hisdao.setQryValue(nindex1, 3, strtemp1[2]);
			hisdao.setQryValue(nindex1, 4, strtemp1[3]);
			wb = hisdao.executeQry(nindex1);

			while (wb.next()) {

				vo.setStrWardModi(wb.getString(1));
				vo.setStrWardName(wb.getString(2));
				vo.setStrRoomName(wb.getString("roomname"));
				vo.setStrEffectiveFrom(wb.getString(3));
				vo.setStrRemark(wb.getString(4));
				String[] arrtemp = { wb.getString(5) };
				vo.setStrRCategory(arrtemp);
				String[] arrtemp1 = { wb.getString(6) };
				vo.setStrRDisease(arrtemp1);
				for (int i = 0; i < vo.getStrRCategory().length; i++) {

					if (vo.getStrRCategory()[i].equals("-1")) {
						vo.setStrChkTreatment("1");

						String[] strtempCatg = { " " };
						vo.setStrRCategory(strtempCatg);
					} else {
						vo.setStrChkTreatment("0");
					}
				}
				for (int i = 0; i < vo.getStrRDisease().length; i++) {

					if (vo.getStrRDisease()[i].equals("-1")) {
						vo.setStrChkDisease("1");
						String[] strtempDisease = { "" };
						vo.setStrRDisease(strtempDisease);
					} else {
						vo.setStrChkDisease("0");
					}
				}
			}
			wb = null;
			nindex2 = hisdao.setQuery(strquery2);
			hisdao.setQryValue(nindex2, 1, strtemp1[0]);
			hisdao.setQryValue(nindex2, 2, strtemp1[1]);
			hisdao.setQryValue(nindex2, 3, strtemp1[3]);
			wb = hisdao.executeQry(nindex2);

			String[] sex = WardCriteriaMstDAO.getMultiRowContent(wb, 1);
			wb.beforeFirst();
			String[] fromage = WardCriteriaMstDAO.getMultiRowContent(wb, 2);
			wb.beforeFirst();
			String[] toage = WardCriteriaMstDAO.getMultiRowContent(wb, 3);
			wb.beforeFirst();
			String[] unit = WardCriteriaMstDAO.getMultiRowContent(wb, 4);

			// vo.setStrGender(sex);
			// vo.setStrFromAge(fromage);
			// vo.setStrToAge(toage);
			// vo.setStrFunit(unit);
			vo.setStrGender(sex);
			vo.setStrFromAge(fromage);
			vo.setStrToAge(toage);
			vo.setStrFunit(unit);

			for (int i = 0; i < vo.getStrGender().length; i++) {
				if (vo.getStrGender()[i].equals("-1")) {
					vo.setStrChkAge("1");

					String[] strtempfage = {};
					String[] strtemptage = {};
					String[] strtempsex = {};
					String[] strtempunit = {};

					vo.setStrGender(strtempsex);
					vo.setStrFromAge(strtempfage);
					vo.setStrToAge(strtemptage);
					vo.setStrFunit(strtempunit);
				} else {
					vo.setStrChkAge("0");
				}
			}
			vo.setStrRoomNo(strtemp1[3]);
			wb = null;
		} catch (Exception e) {
			e.printStackTrace();
			fretValue = false;
			throw new Exception("ipd.WardCriteriaMstDAO.modifyQuery"
					+ e.getMessage());
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return fretValue;
	}

	/**
	 * returns the array of data in a given Row Set based on the give index
	 * 
	 * @param ws -
	 *            WebRowSet Object for a particular Table.
	 * @param index -
	 *            integer which represent the column index. 1 - will return Sex
	 *            array. 2 - will return Age From array. 3 - will return Age To
	 *            array.
	 * @return - String array data of particular column
	 */
	public static String[] getMultiRowContent(WebRowSet ws, int index)
			throws Exception {

		String[] result = null;
		int count = 0;
		try {
			result = new String[ws.size()];

			while (ws.next()) {

				result[count] = ws.getString(index);
				count = count + 1;
			}

		} catch (SQLException e) {

			throw new Exception("ipd.WardCriteriaMstDAO.getMultiRowContent"
					+ e.getMessage());
		}

		return result;
	}

	/**
	 * By this method user get Left side treatment Category through hospital
	 * code and ward code
	 * 
	 * @param vo -
	 *            FormBean Object
	 * @param strhospcode -
	 *            String Variable
	 * @param strwardcode -
	 *            String Variable
	 * @return webrowset left side treatment category
	 */
	public static WebRowSet getLeftCatg(WardCriteriaMstVO vo, String strhospcode,
			String strwardcode) {
		WebRowSet web = null;
		HisDAO dao = null;
		int nqryIndex;

		try {

			dao = new HisDAO("ipd", "WardCriteriaMstDAO");
			String strqry = ipd.qryHandler_ipd.getQuery(1,
					"select.wardcriteria.7");
			nqryIndex = dao.setQuery(strqry);
			dao.setQryValue(nqryIndex, 1, strhospcode);
			// dao.setQryValue(nqryIndex,1,"108");
			// dao.setQryValue(nqryIndex,2, vo.getStrWardCode());
			dao.setQryValue(nqryIndex, 2, strwardcode);
			web = dao.executeQry(nqryIndex);
			// while(web.next())
			// {
			// }

		} catch (Exception e) {

			new HisException("ipd", "getLeftCatg", e.getMessage());
			// e.printStackTrace();
		} finally {
			dao.free();
			dao = null;
		}
		return web;
	}

	/**
	 * By this method user get Right side treatment Category through hospital
	 * code and ward code
	 * 
	 * @param vo -
	 *            FormBean Object
	 * @param strhospcode -
	 *            String Variable
	 * @param strwardcode -
	 *            String Variable
	 * @return webrowset Right side treatment category
	 */
	public static WebRowSet getRightCatg(WardCriteriaMstVO vo, String strhospcode,
			String strwardcode) {
		WebRowSet web = null;
		HisDAO dao = null;
		int nqryIndex;
		try {

			dao = new HisDAO("ipd", "WardCriteriaMstDAO");
			String strqry = ipd.qryHandler_ipd.getQuery(1,
					"select.wardcriteria.8");
			nqryIndex = dao.setQuery(strqry);
			// dao.setQryValue(nqryIndex,1, strwardcode);
			dao.setQryValue(nqryIndex, 1, strwardcode);
			// dao.setQryValue(nqryIndex,1, vo.getStrWardCode());
			dao.setQryValue(nqryIndex, 2, strhospcode);
			web = dao.executeQry(nqryIndex);
			// while(web.next())
			// {
			// }

		} catch (Exception e) {

			new HisException("ipd", "getReftCatg", e.getMessage());

		} finally {
			dao.free();
			dao = null;
		}
		return web;
	}

	/**
	 * By this method user get Left side Disease Type Criteria through hospital
	 * code and ward code
	 * 
	 * @param vo -
	 *            FormBean Object
	 * @param strhospcode -
	 *            String Variable
	 * @param strwardcode -
	 *            String Variable
	 * @return webrowset left side Disease Type Criteria
	 */
	public static WebRowSet getLDisease(WardCriteriaMstVO vo, String strhospcode,
			String strwardcode) {

		WebRowSet web = null;
		HisDAO dao = null;
		int nqryIndex;
		try {

			dao = new HisDAO("ipd", "WardCriteriaMstDAO");
			String strqry = ipd.qryHandler_ipd.getQuery(1,
					"select.wardcriteria.9");
			nqryIndex = dao.setQuery(strqry);

			dao.setQryValue(nqryIndex, 1, strhospcode);
			dao.setQryValue(nqryIndex, 2, strwardcode);
			web = dao.executeQry(nqryIndex);

		} catch (Exception e) {

			new HisException("ipd", "getLDisease", e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return web;

	}

	/**
	 * By this method user get Right side Disease Type Criteria through hospital
	 * code and ward code
	 * 
	 * @param vo -
	 *            FormBean Object
	 * @param strhospcode -
	 *            String Variable
	 * @param strwardcode -
	 *            String Variable
	 * @return webrowset Right side Disease Type Criteria
	 */
	public static WebRowSet getRDisease(WardCriteriaMstVO vo, String strhospcode,
			String strwardcode) {
		WebRowSet web = null;
		HisDAO dao = null;
		int nqryIndex;
		try {

			dao = new HisDAO("ipd", "WardCriteriaMstDAO");
			String strqry = ipd.qryHandler_ipd.getQuery(1,
					"select.wardcriteria.10");
			nqryIndex = dao.setQuery(strqry);

			// dao.setQryValue(nqryIndex,1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 1, strwardcode);
			dao.setQryValue(nqryIndex, 2, strhospcode);
			// dao.setQryValue(nqryIndex,1, vo.getStrWardCode());
			web = dao.executeQry(nqryIndex);

		} catch (Exception e) {

			new HisException("ipd", "getLDisease", e.getMessage());

		} finally {
			dao.free();
			dao = null;
		}

		return web;
	}

	/*
	 * 
	 * //updateData public static boolean insertData(VOWardCriteria vo ) throws
	 * Exception { boolean fretValue = false;
	 * 
	 * HisDAO hisdao = new HisDAO("ipd", "WardCriteriaMstDAO"); int nindex; String
	 * strquery = qryHandler_ipd.getQuery(1, "insert.wardcriteria.0");
	 * 
	 * System.out.println("insert strquery=="+strquery); String strtreatcond
	 * =""; String strdisescond=""; System.out.println("inside chk val
	 * cond=="+vo.getStrChkAge()); String stragecond= vo.getStrChkAge(); if
	 * (vo.getStrChkTreatment() != null || vo.getStrChkTreatment() != "") {
	 * strtreatcond = vo.getStrChkTreatment(); } if (vo.getStrChkDisease()!=
	 * null || vo.getStrChkDisease() != "") { strdisescond =
	 * vo.getStrChkDisease(); } try {
	 * 
	 * 
	 * nindex = hisdao.setQuery(strquery);
	 * 
	 * String strTempAgeFrom = ""; String strTempAgeTo =""; String strTempGender
	 * =""; String strTempUnit =""; String strTempCatg =""; String
	 * strTempDisease =""; //if ((vo.getStrGender() != null
	 * )&&(vo.getStrRDisease()!=null)&& (vo.getStrRDisease()!=null){
	 * 
	 * if(strtreatcond.equals("on")&&strtreatcond.equals("on")&&strdisescond.equals("on")){
	 * 
	 * System.out.println("if checked set -1");
	 * 
	 * strTempAgeFrom = "-1"; strTempAgeTo ="-1"; strTempGender ="-1";
	 * strTempUnit ="-1"; strTempCatg ="-1"; strTempDisease="-1";
	 * 
	 * 
	 * System.out.println("1:vo.getStrWardCmb()"+vo.getStrWardCmb());
	 * System.out.println("2:strTempAgeFrom"+strTempAgeFrom);
	 * System.out.println("3: strTempAgeTo"+ strTempAgeTo);
	 * System.out.println("4: strTempGender"+ strTempGender);
	 * System.out.println("5: vo.getStrEffectiveFrom()"+
	 * vo.getStrEffectiveFrom()); System.out.println("6: vo.getStrRemark()"+
	 * vo.getStrRemark()); System.out.println("7: vo.getStrSeatId()"+
	 * vo.getStrSeatId()); System.out.println("8: strTempUnit"+strTempUnit);
	 * System.out.println("9: strTempCatg"+strTempCatg); System.out.println("10:
	 * vo.getStrHospitalCode()"+vo.getStrHospitalCode());
	 * System.out.println("11: strTempDisease"+strTempDisease);
	 * 
	 * hisdao.setQryValue(nindex, 1, vo.getStrWardCmb());
	 * hisdao.setQryValue(nindex, 2, strTempAgeFrom); hisdao.setQryValue(nindex,
	 * 3, strTempAgeTo); hisdao.setQryValue(nindex, 4,strTempGender);
	 * hisdao.setQryValue(nindex, 5, vo.getStrEffectiveFrom());
	 * hisdao.setQryValue(nindex, 6,vo.getStrRemark());
	 * hisdao.setQryValue(nindex, 7,vo.getStrSeatId());
	 * hisdao.setQryValue(nindex, 8,strTempUnit); hisdao.setQryValue(nindex,
	 * 9,strTempCatg); hisdao.setQryValue(nindex, 10,vo.getStrHospitalCode());
	 * hisdao.setQryValue(nindex, 11,strTempDisease); hisdao.execute(nindex, 0);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }else{
	 * 
	 * 
	 * 
	 * for (int i = 0; i < vo.getStrGender().length; i++) {
	 * System.out.println("inside for loop==");
	 * 
	 * for(int j=0;j<vo.getStrRDisease().length;j++){
	 * 
	 * System.out.println("inside for1 loop=="); for(int k=0;k
	 * <vo.getStrRDisease().length;k++) { System.out.println("inside for3
	 * loop=="); if(stragecond=="0") { System.out.println("inside age cond==");
	 * strTempAgeFrom = vo.getStrFromAge()[i]; strTempAgeTo =
	 * vo.getStrToAge()[i]; strTempGender =vo.getStrGender()[i]; strTempUnit =
	 * vo.getStrFunit()[i]; }
	 * 
	 * 
	 * if(strtreatcond=="0"){
	 * 
	 * strTempCatg =vo.getStrRCategory()[j]; }
	 * 
	 * if(strdisescond=="0"){ strTempDisease =vo.getStrRDisease()[k];
	 *  } } //if (vo.getStrGender()[i] != null || vo.getStrGender()[i] != "") {
	 * 
	 * 
	 * 
	 * System.out.println("1:vo.getStrWardCmb()"+vo.getStrWardCmb());
	 * System.out.println("2:strTempAgeFrom"+strTempAgeFrom);
	 * System.out.println("3: strTempAgeTo"+ strTempAgeTo);
	 * System.out.println("4: strTempGender"+ strTempGender);
	 * System.out.println("5: vo.getStrEffectiveFrom()"+
	 * vo.getStrEffectiveFrom()); System.out.println("6: vo.getStrRemark()"+
	 * vo.getStrRemark()); System.out.println("7: vo.getStrSeatId()"+
	 * vo.getStrSeatId()); System.out.println("8: strTempUnit"+strTempUnit);
	 * System.out.println("9: strTempCatg"+strTempCatg); System.out.println("10:
	 * vo.getStrHospitalCode()"+vo.getStrHospitalCode());
	 * System.out.println("11: strTempDisease"+strTempDisease);
	 * 
	 * hisdao.setQryValue(nindex, 1, vo.getStrWardCmb());
	 * hisdao.setQryValue(nindex, 2,vo.getStrHospitalCode());
	 * hisdao.setQryValue(nindex, 3, vo.getStrWardCmb());
	 * hisdao.setQryValue(nindex, 4, strTempAgeFrom); hisdao.setQryValue(nindex,
	 * 5, strTempAgeTo); hisdao.setQryValue(nindex, 6,strTempGender);
	 * hisdao.setQryValue(nindex, 7, vo.getStrEffectiveFrom());
	 * hisdao.setQryValue(nindex, 8,vo.getStrRemark());
	 * hisdao.setQryValue(nindex, 9,vo.getStrSeatId());
	 * hisdao.setQryValue(nindex, 10,strTempUnit); hisdao.setQryValue(nindex,
	 * 11,strTempCatg); hisdao.setQryValue(nindex, 12,vo.getStrHospitalCode());
	 * hisdao.setQryValue(nindex, 13,strTempDisease); hisdao.execute(nindex, 0);
	 *  } } }
	 * 
	 * synchronized (hisdao) {
	 * 
	 * hisdao.fire(); fretValue = true; } } catch (Exception e) { fretValue =
	 * false; throw new
	 * Exception("ipd.WardCriteriaMstDAO.insertData"+e.getMessage()); } finally {
	 * hisdao.free(); hisdao = null; } return fretValue; }
	 * 
	 * 
	 * 
	 */

	public static void getRoomValues(WardCriteriaMstVO _Vo) throws Exception {
		HisDAO dao = null;
		int nqryIndex;
		WebRowSet wb = null;
		String strquery = new String();
		HisUtil util = null;
		try {
			util = new HisUtil("adt", "WardCriteriaMstDAO");
			dao = new HisDAO("adt", "WardCriteriaMstDAO");
			strquery = ipd.qryHandler_ipd.getQuery(1, "select.wardcriteria.13");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, _Vo.getStrWardCmb());
			dao.setQryValue(nqryIndex, 2, _Vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 3, _Vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 4, _Vo.getStrWardCmb());
			wb = dao.executeQry(nqryIndex);
			_Vo.setStrRoomNoValues(util.getOptionValue(wb, "0", "Select All",
					false));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("WardCriteriaMstDAO.getRoomValues() --> "
					+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static WebRowSet getRoomInWardWOCriteria(WardCriteriaMstVO _Vo)
			throws Exception {
		HisDAO dao = null;
		int nqryIndex;
		WebRowSet wb = null;
		String strquery = new String();
		try {
			dao = new HisDAO("adt", "WardCriteriaMstDAO");
			strquery = ipd.qryHandler_ipd.getQuery(1, "select.wardcriteria.13");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, _Vo.getStrWardCmb());
			dao.setQryValue(nqryIndex, 2, _Vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 3, _Vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 4, _Vo.getStrWardCmb());
			wb = dao.executeQry(nqryIndex);
			return wb;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(
					"WardCriteriaMstDAO.getRoomInWardWOCriteria() --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

}
