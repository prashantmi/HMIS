/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         StockOnHandRptDAO.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package mms.reports.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import mms.MmsConfigUtil;
import mms.reports.vo.ListItemWiseSupplierRptVO_NEW;
import mms.reports.vo.StockOnHandRptVO;
import mms.reports.vo.StockOnHandRptVO_NEW;

// TODO: Auto-generated Javadoc
/**
 * The Class StockOnHandRptDAO.
 */
public class StockOnHandRptDAO_NEW {

	/**
	 * Gets the circle list.
	 * 
	 * @param voObj
	 *            the vo obj
	 * @return the circle list
	 */
	public static void getCircleList(StockOnHandRptVO_NEW voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		MmsConfigUtil mcu = null;

		String strProcName = "{call PKG_MMS_VIEW.PROC_DISTRICT_NAME_COMBO(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			//mcu = new MmsConfigUtil();
			daoObj = new HisDAO("MMS Transactions", "StockOnHandRptDAO");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "p_mode", voObj.getStrMode()); // 2
																				// if
																				// userLevel
																				// 1,
																				// 3
																				// if
																				// userLeve
																				// 2
			daoObj.setProcInValue(nProcIndex, "p_country_code",
					voObj.getStrDistrictId() == null
							|| voObj.getStrDistrictId().equals("") ? "0"
							: voObj.getStrDistrictId()); // passing dist_id
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",
					voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "p_state_code",
					mcu.getStrStateCode());
			daoObj.setProcInValue(nProcIndex, "p_seatid", voObj.getStrSeatId());

			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				voObj.setStrCircleWS(ws);

				if (voObj.getStrCircleWS().next()) {
					voObj.setStrCircleId(voObj.getStrCircleWS().getString(1));
				}
				voObj.getStrCircleWS().beforeFirst();

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj.setStrMsgString("StockOnHandRptDAO.getCircleList() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * Gets the district list.
	 * 
	 * @param voObj
	 *            the vo obj
	 * @return the district list
	 */
	public static void getDistrictList(StockOnHandRptVO_NEW voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		MmsConfigUtil mcu = null;

		String strProcName = "{call PKG_MMS_VIEW.PROC_DISTRICT_NAME_COMBO(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			//mcu = new MmsConfigUtil();
			daoObj = new HisDAO("MMS Transactions", "StockOnHandRptDAO");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);

			if (voObj.getStrUserLevel().equals("1")
					|| voObj.getStrUserLevel().equals("2")) {
				voObj.setStrMode("4");
			} else {
				voObj.setStrMode("5");
			}

			daoObj.setProcInValue(nProcIndex, "p_mode", voObj.getStrMode()); // 4
																				// if
																				// userLevel
																				// 1
																				// or
																				// 2,
																				// 5
																				// if
																				// userLevel
																				// 3
			daoObj.setProcInValue(
					nProcIndex,
					"p_country_code",
					voObj.getStrCircleId() == null
							|| voObj.getStrCircleId().equals("") ? "0" : voObj
							.getStrCircleId()); // passing circle_id
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",
					voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "p_state_code",
					mcu.getStrStateCode());
			daoObj.setProcInValue(nProcIndex, "p_seatid", voObj.getStrSeatId());

			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				voObj.setStrDistrictWS(ws);

				if (voObj.getStrDistrictWS().next()) {
					voObj.setStrDistrictId(voObj.getStrDistrictWS()
							.getString(1));
				}
				voObj.getStrDistrictWS().beforeFirst();

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj.setStrMsgString("StockOnHandRptDAO.getDistrictList() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * Gets the store type list.
	 * 
	 * @param voObj
	 *            the vo obj
	 * @return the store type list
	 */
	/*public static void getStoreTypeList(StockOnHandRptVO_NEW voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call PKG_MMS_VIEW.proc_institute_type_list(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("MMS Transactions", "StockOnHandRptDAO");
			daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "2");
			daoObj.setProcInValue(nProcIndex, "hosp_code",
					voObj.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "ERR", 1); // 1 for string return
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2); // 2 for object
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "ERR");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setStrStoreTypeWS(ws);
				while (ws.next()) {
					voObj.setStrStoreTypeId(ws.getString(1));
				}
				ws.beforeFirst();

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj.setStrMsgString("StockOnHandRptDAO.getStoreTypeList() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}*/

	/**
	 * To get Drug Store Type Combo
	 * 
	 * 
	 * @param voObj
	 *            the vo obj
	 * @return the dwh type combo
	 */
	public static void getDwhTypeCombo(StockOnHandRptVO_NEW voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.proc_sstt_dwh_type_mst_cmb(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("DWH", "StockOnHandRptDAO");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "p_mode", "7");
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code",voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "p_sstnum_is_district_dwh",voObj.getStrStoreId().split("\\^")[0]);
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				voObj.setStrStoreTypeWS(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj.setStrMsgString("StockOnHandRptDAO.getStoreList() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}


	/**
	 * To get Store Combo from the hstt_store_mst
	 * 
	 * 
	 * @param voObj
	 *            the vo obj
	 * @return the store list
	 */
	public static void getStoreList(StockOnHandRptVO_NEW voObj) {

		/*HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_Rpt.rptm_hstt_store_mst(?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","StockOnHandRptDAO");
	//		daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "seatid", voObj.getStrSeatId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "item_category", "10",4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";*/
		HisDAO daoObj = null;
		WebRowSet ws = null;

	//	String strProcName = "{call Pkg_Mms_View.poc_hstt_store_mst(?,?,?,?,?,?,?)}";
		int nprocIndex = 0;
	
		String strErr = "";

		try{
			daoObj = new HisDAO("MMS Transactions","StockOnHandRptDAO");
			String strproc_name = "{call pkg_mms_rpt.rptm_hstt_store_mst(?,?,?,?,?,?)}";
		nprocIndex = daoObj.setProcedure(strproc_name);
		
		daoObj.setProcInValue(nprocIndex, "modeval","8",1);
		daoObj.setProcInValue(nprocIndex, "seatid",voObj.getStrSeatId(),2);
		daoObj.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospitalCode(),3);
		daoObj.setProcInValue(nprocIndex, "item_category", "10",4);
		daoObj.setProcOutValue(nprocIndex, "err", 1,5);
		daoObj.setProcOutValue(nprocIndex, "resultset", 2,6); 
		daoObj.executeProcedureByPosition(nprocIndex);
		strErr = daoObj.getString(nprocIndex, "err");
			strErr = daoObj.getString(nprocIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nprocIndex, "resultset");
					System.out.println("ws.size()"+ws.size());
					voObj.setStrStoreWs(ws);
			
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("StockOnHandRptDAO.getStoreList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}/*
	public static void getStoreList(StockOnHandRptVO_NEW voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.get_store_based_on_district(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("DWH", "StockOnHandRptDAO");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", voObj.getStrMode());
			daoObj.setProcInValue(nProcIndex, "seatid", voObj.getStrSeatId());
			daoObj.setProcInValue(nProcIndex, "hosp_code",
					voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "storeid", "0");
			daoObj.setProcInValue(nProcIndex, "storetype_id", (voObj
					.getStrStoreTypeId() == null || voObj.getStrStoreTypeId()
					.equals("")) ? "0" : voObj.getStrStoreTypeId());
			daoObj.setProcInValue(nProcIndex, "cirleid",
					(voObj.getStrCircleId() == null || voObj.getStrCircleId()
							.equals("")) ? "0" : voObj.getStrCircleId());
			daoObj.setProcInValue(nProcIndex, "districtid",
					voObj.getStrDistrictId());

			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				voObj.setStrStoreWs(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj.setStrMsgString("StockOnHandRptDAO.getStoreList() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * To get Store Combo from the hstt_store_mst
	 * 
	 * 
	 * @param voObj
	 *            the vo obj
	 * @return the sub store list
	 */
	public static void getSubStoreList(StockOnHandRptVO_NEW voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.get_store_based_on_district(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("DWH", "StockOnHandRptDAO");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);			

			daoObj.setProcInValue(nProcIndex, "modeval", voObj.getStrMode());
			daoObj.setProcInValue(nProcIndex, "seatid", voObj.getStrSeatId());
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "storeid", voObj.getStrDistrictStoreId().split("\\^")[0]);
			daoObj.setProcInValue(nProcIndex, "storetype_id",voObj.getStrStoreTypeId());
			daoObj.setProcInValue(nProcIndex, "cirleid", voObj.getStrCircleId());
			daoObj.setProcInValue(nProcIndex, "districtid",	voObj.getStrDistrictId());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				voObj.setStrStoreWs(ws);

			} else {
				throw new Exception(strErr);
			}
			
			if (voObj.getStrStoreWs().next()) {
				voObj.setStrDistrictStoreId(voObj.getStrStoreWs().getString(1));
			}
			voObj.getStrStoreWs().beforeFirst();

		} catch (Exception e) {
            e.printStackTrace();
			voObj.setStrMsgString("StockOnHandRptDAO.getStoreList() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * Gets the item cat list.
	 * 
	 * @param voObj
	 *            the vo obj
	 * @return the item cat list
	 */
	public static void getItemCatList(StockOnHandRptVO_NEW voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_Rpt.Rptm_item_category_list(?::character varying,?::character varying,?::character varying,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","ListBlackListedSupplierRptDAO");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", MmsConfigUtil.GLOBAL_HOSPITAL_CODE,2);
			daoObj.setProcInValue(nProcIndex, " storeId","",3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";
			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrItemCatWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("StockOnHandRptDAO.getItemCatList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	/*public static void getItemCatList(StockOnHandRptVO_NEW voObj) {

		HisDAO dao = null;
		int procIndex1 = 0;
		WebRowSet ws = null;
		String proc_name1 = "{call Pkg_Mms_View.proc_hstt_drug_classification(?,?,?,?)}";
		String strErr = "";

		try {

			dao = new HisDAO("mms","IndentTransADDDAO.ItemCategoryCombo(IndentTransADDVO vo)");

			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modeval", "1");
			dao.setProcInValue(procIndex1, "hosp_code", voObj.getStrHospitalCode());
			dao.setProcOutValue(procIndex1, "err", 1); // 1 for string return value
			dao.setProcOutValue(procIndex1, "resultset", 2); // 2 for object
			dao.executeProcedure(procIndex1);

			// get value
			strErr = dao.getString(procIndex1, "err");

			if (strErr == null)
				strErr = "";

			ws = dao.getWebRowSet(procIndex1, "resultset");
			
			
			if (ws != null && ws.size() != 0) {				
				voObj.setStrItemCatWs(ws);
			} 

		} catch (Exception e) {
			e.printStackTrace();
			voObj.setStrMsgString("StockOnHandRptDAO.getItemCatList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
-*/
	/**
	 * Gets the group list.
	 * 
	 * @param voObj
	 *            the vo obj
	 * @return the group list
	 */
	public static void getGroupList(StockOnHandRptVO_NEW voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_Rpt.rptm_store_group_list(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions", "StockOnHandRptDAO");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcInValue(nProcIndex, "hosp_code","100");
			daoObj.setProcInValue(nProcIndex, "item_category",voObj.getStrItemCatId());// voObj
					
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				System.out.println("ws"+ws.size());
				voObj.setStrGroupCmbWS(ws);
				

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj.setStrMsgString("StockOnHandRptDAO.getGroupList() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * Gets the drug list.
	 * 
	 * @param voObj
	 *            the vo obj
	 * @return the drug list
	 */
	public static void getDrugList(StockOnHandRptVO_NEW voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.proc_get_Drug_list_Combo1(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";



		try {
			daoObj = new HisDAO("MMS Transactions", "StockOnHandRptDAO");
			//daoObj.setConnType("2");
			System.out.println("voObj.getStrDistrictStoreId()"+voObj.getStrDistrictStoreId());
			String storeid=voObj.getStrStoreId().equals("")||voObj.getStrStoreId()==null?"0":voObj.getStrStoreId();
			String stock_status_code=(voObj.getStrStatusId() == null || voObj.getStrStatusId().equals("")) ? "0" : voObj.getStrStatusId();
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "4");
			daoObj.setProcInValue(nProcIndex, "p_hstnum_store_id",voObj.getStrStoreId().equals("")||voObj.getStrStoreId()==null?"0":voObj.getStrStoreId());
			daoObj.setProcInValue(nProcIndex, "p_hstnum_item_id", "0");
			daoObj.setProcInValue(nProcIndex, "p_hstnum_itembrand_id", "0");
			daoObj.setProcInValue(nProcIndex, "p_hststr_batch_no", "0");
			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code",voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "p_sstnum_item_cat_no",voObj.getStrItemCatId());
			daoObj.setProcInValue(nProcIndex, "p_hstnum_stock_status_code",stock_status_code);
			daoObj.setProcInValue(nProcIndex, "p_hstnum_group_id",voObj.getStrGroupId());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println("storeid"+storeid+"\nstock_status_code"+stock_status_code+"\nvoObj.getStrGroupId()"+voObj.getStrGroupId());
			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				System.out.println("no of item"+ws.size());
				voObj.setStrDrugWs(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			voObj.setStrMsgString("StockOnHandRptDAO.getDrugList() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	
	/*public static void getDrugList(StockOnHandRptVO_NEW vo) {
		String strProcName = "{call Pkg_Mms_Rpt.rptm_itembrand_list(?,?,?,?,?,?,?)}";

		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj = null;

		try {
			daoObj = new HisDAO("Item Location", "StockOnHandRptDAO_NEW");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);

		
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "catCode",vo.getStrItemCatId(),2);
			daoObj.setProcInValue(nProcIndex, "groupid", vo.getStrGroupId(),3);
			daoObj.setProcInValue(nProcIndex, "subgrpid", "0",4);
			daoObj.setProcInValue(nProcIndex, "hosp_code","100",5);
			//daoObj.setProcInValue(nProcIndex, "drug_class", "0");
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			
			System.out.println("ws item name "+ws.size());
			if (strErr.equals("")) {
				//vo.setItemIdWS(ws);
				vo.setStrDrugWs(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ListItemWiseSupplierRptDAO.ItemName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
*/
	/**
	 * Gets the user level.
	 * 
	 * @param voObj
	 *            the vo obj
	 */
	public static void GetUserLevel(StockOnHandRptVO_NEW voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_Rpt.get_userlevel_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions", "StockOnHandRptDAO");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);

			
			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcInValue(nProcIndex, "hospCode",
					voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "seatId", voObj.getStrSeatId());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				voObj.setStrUserlevelWs(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj.setStrMsgString("DailyActivityRptDAO.getUserLevel() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * Gets the item type values.
	 * 
	 * @param vo
	 *            the vo
	 * @return the item type values
	 */
	public static void getItemTypeValues(StockOnHandRptVO_NEW vo) {
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("MMS", "StockOnHandRptDAO");
			strQuery = mms.qryHandler_mms.getQuery(1,
					"select.drugbrand.itemtype.0");
			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, "100");
			dao.setQryValue(nQueryIndex, 2, vo.getStrItemCatId());

			web = dao.executeQry(nQueryIndex);
			System.out.println("web"+web.size());
			vo.setItemTypeWs(web);

		} catch (Exception e) {
			// e.printStackTrace();
			vo.setStrMsgString("StockOnHandRptDAO.getItemValues() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
			web = null;
		}
	}

	/**
	 * Gets the programme combo.
	 * 
	 * @param vo
	 *            the vo
	 * @return the programme combo
	 */
	public static void getProgrammeCombo(StockOnHandRptVO_NEW vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		String storeId ="0";
		try {
			dao = new HisDAO("mms", "DrugInventoryTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_programme_list(?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);

			storeId=vo.getStrDistrictStoreId();
			dao.setProcInValue(nprocIndex, "modeval", "2");
			dao.setProcInValue(nprocIndex, "storeid", (vo.getStrDistrictStoreId() == null || vo.getStrDistrictStoreId()
					.equals("")) ? "0" : vo.getStrDistrictStoreId());
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode());
			dao.setProcInValue(nprocIndex, "seatid", vo.getStrSeatId());
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);

			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {

				vo.setStrProgNameComboWS(wb);

			} else {
				throw new Exception(strerr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("DrugInventoryTransDAO.phdItemCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
}
