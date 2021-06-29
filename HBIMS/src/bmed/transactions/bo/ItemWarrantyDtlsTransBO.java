package bmed.transactions.bo;

import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.upload.FormFile;

import bmed.dao.HemtNonItemMstDAO;
import bmed.dao.HsttSupplierItemMsDAO;
import bmed.dao.ItemBrandMstDAO;
import bmed.dao.ItemCurrStockDtlMstDAO;
import bmed.dao.ItemSubTypeMstDAO;
import bmed.dao.ItemTypeMstDAO;
import bmed.dao.SemtCancelTypeMstDAO;
import bmed.dao.SemtMaintTypeMstDAO;
import bmed.dao.StoreMstDAO;
import bmed.dao.UnitMstDAO;
import bmed.dao.WarrantyDtlDAO;
import bmed.global.controller.data.PreviousWarrantyDATA;
import bmed.global.controller.data.StockDetailsDATA;
import hisglobal.transactionmgnt.HisDAO;
import bmed.transactions.dao.ItemWarrantyDtlsTransDAO;
import bmed.transactions.vo.ItemWarrantyDtlsTransVO;
import bmed.vo.HemtNonItemMstVO;
import bmed.vo.HsttSupplierItemMstVO;
import bmed.vo.ItemBrandMstVO;
import bmed.vo.ItemCurrStockDtlMstVO;
import bmed.vo.ItemSubTypeMstVO;
import bmed.vo.ItemTypeMstVO;
import bmed.vo.SemtCancelTypeMstVO;
import bmed.vo.SemtMaintTypeMstVO;
import bmed.vo.StoreMstVO;
import bmed.vo.UnitMstVO;
import bmed.vo.WarrantyDtlVO;

public class ItemWarrantyDtlsTransBO {
	/**
	 * @param strHospitalCode_p
	 *            The hospital code
	 * @return list of department options.
	 * @throws Exception
	 */
	public String getDepartmentComboOptions(String strHospitalCode_p,
			String strSeatId, int strMode) throws Exception {

		String strDepartmentComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemWarrantyDtlsTransVO vo;
		WebRowSet wrsDepartmentComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			vo = new ItemWarrantyDtlsTransVO();
			vo.setStrHospitalCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");

			vo.setStrSeatId(strSeatId);
			vo.setStrMode(Integer.toString(strMode));

			ItemWarrantyDtlsTransDAO.getBmedHospitalsCombo(vo, hisDAO);
			wrsDepartmentComboOptions = vo.getWrsDepartmentOptions();
			if (wrsDepartmentComboOptions != null) {
				strDepartmentComboOptions = hisUtil
						.getOptionValue(wrsDepartmentComboOptions, "0",
								"0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getDepartmentComboOptions(String)-->"
							+ ex.getMessage());
		}
		return strDepartmentComboOptions;

	}

	/**
	 * @param strHospitalCode_p
	 *            The hospital code
	 * @return list of department options.
	 * @throws Exception
	 */
	public String getBmedHospitalsCombo(ItemWarrantyDtlsTransVO vo)
			throws Exception {

		String strDepartmentComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		;
		WebRowSet wrsDepartmentComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");

			// System.out.println("vo.getStrDeptId()::"+vo.getStrDeptId());
			ItemWarrantyDtlsTransDAO.getBmedHospitalsCombo(vo, hisDAO);
			wrsDepartmentComboOptions = vo.getWrsDepartmentOptions();
			if (wrsDepartmentComboOptions != null) {
				strDepartmentComboOptions = hisUtil.getOptionValue(
						wrsDepartmentComboOptions, vo.getStrDeptId(),
						"0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getDepartmentComboOptions(String)-->"
							+ ex.getMessage());
		}
		return strDepartmentComboOptions;

	}

	public String getItemTypeComboOptions(String strHospitalCode_p)
			throws Exception {

		String strItemTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemTypeMstVO gbltItemTypeMstVO;
		WebRowSet wrsItemTypeComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltItemTypeMstVO = new ItemTypeMstVO();
			gbltItemTypeMstVO.setStrHospitalCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			ItemTypeMstDAO.getItemTypeCombo(gbltItemTypeMstVO, hisDAO);
			wrsItemTypeComboOptions = gbltItemTypeMstVO
					.getWrsItemTypeComboOptions();
			if (wrsItemTypeComboOptions != null) {
				strItemTypeComboOptions = hisUtil.getOptionValue(
						wrsItemTypeComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getItemTypeComboOptions(String)-->"
							+ ex.getMessage());
		}
		return strItemTypeComboOptions;

	}

	/**
	 * for getting value of item brand combo
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void getItemBrandName(ItemWarrantyDtlsTransVO vo) {
		ItemWarrantyDtlsTransDAO.getItemBrandName(vo);
		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("ItemInventoryTransBO.getItemBrandName() --> "
					+ strErr);
		}

	}

	/**
	 * Method by Amit Kumar
	 * 
	 * @param strHospitalCode_p
	 * @return
	 * @throws Exception
	 */
	public static String getUnitComboOptions(String strHospitalCode_p)
			throws Exception {

		String strUnitComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		UnitMstVO gbltUnitMstVO;
		WebRowSet wrsUnitComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltUnitMstVO = new UnitMstVO();
			gbltUnitMstVO.setStrMode("1");
			gbltUnitMstVO.setStrHospitalCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			UnitMstDAO.getUnitMstCombo(gbltUnitMstVO, hisDAO);
			wrsUnitComboOptions = gbltUnitMstVO.getWrsUnitComboOptions();
			if (wrsUnitComboOptions != null) {
				strUnitComboOptions = hisUtil.getOptionValue(
						wrsUnitComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getUnitComboOptions(String)-->"
					+ ex.getMessage());
		}
		return strUnitComboOptions;

	}

	public String getMaintenanceContTypeComboOptions(String strHospitalCode_p)
			throws Exception {

		String strMaintTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		SemtMaintTypeMstVO gbltSemtMaintTypeMstVO;
		WebRowSet wrsMainContractComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltSemtMaintTypeMstVO = new SemtMaintTypeMstVO();
			gbltSemtMaintTypeMstVO.setStrMode("1");
			gbltSemtMaintTypeMstVO.setStrHospCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			SemtMaintTypeMstDAO.getSemtMaintTypeMstCombo(
					gbltSemtMaintTypeMstVO, hisDAO);
			wrsMainContractComboOptions = gbltSemtMaintTypeMstVO
					.getWrsMaintTypeComboOptions();
			if (wrsMainContractComboOptions != null) {
				strMaintTypeComboOptions = hisUtil.getOptionValue(
						wrsMainContractComboOptions, "0", "0^Select Value",
						false);

			}

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getUnitComboOptions(String)-->"
					+ ex.getMessage());
		}
		return strMaintTypeComboOptions;

	}

	public String getStoreComboOptions(ItemWarrantyDtlsTransVO vo)
			throws Exception {

		String strStoreComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		StoreMstVO storeMstVO;
		WebRowSet wrsStoreComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			storeMstVO = new StoreMstVO();

			storeMstVO.setStrHospitalCode(vo.getStrHospitalCode());
			storeMstVO.setStrMode("5");
			storeMstVO.setStrSeatid(vo.getStrSeatId());
			storeMstVO.setStrDeptCode(vo.getStrDeptId());

			/* Default Value */
			storeMstVO.setStrStoreId("0");
			storeMstVO.setStrStoretypeId("0");

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			StoreMstDAO.getStoreCombo(storeMstVO, hisDAO);
			wrsStoreComboOptions = storeMstVO.getWrsStoreComboOptions();
			if (wrsStoreComboOptions != null) {
				strStoreComboOptions = hisUtil.getOptionValue(
						wrsStoreComboOptions, vo.getStrStoreId(),
						"0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getStoreComboOptions(String)-->"
					+ ex.getMessage());
		}
		return strStoreComboOptions;

	}

	public String getStoreComboOptions(String strHospitalCode_p,
			String strSeatid_p, String strDeptCode_p) throws Exception {

		String strStoreComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		StoreMstVO storeMstVO;
		WebRowSet wrsStoreComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			storeMstVO = new StoreMstVO();

			storeMstVO.setStrHospitalCode(strHospitalCode_p);
			storeMstVO.setStrMode("5");
			storeMstVO.setStrSeatid(strSeatid_p);
			storeMstVO.setStrDeptCode(strDeptCode_p);

			/* Default Value */
			storeMstVO.setStrStoreId("0");
			storeMstVO.setStrStoretypeId("0");

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			StoreMstDAO.getStoreCombo(storeMstVO, hisDAO);
			wrsStoreComboOptions = storeMstVO.getWrsStoreComboOptions();
			if (wrsStoreComboOptions != null) {
				strStoreComboOptions = hisUtil.getOptionValue(
						wrsStoreComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getStoreComboOptions(String)-->"
					+ ex.getMessage());
		}
		return strStoreComboOptions;

	}

	public String getItemSubTypeComboOptions(String strHospitalCode_p,
			String strEnggItemTypeId_p) throws Exception {

		String strItemSubTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemSubTypeMstVO itemSubTypeMstVO;
		WebRowSet wrsItemSubTypeComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			itemSubTypeMstVO = new ItemSubTypeMstVO();
			itemSubTypeMstVO.setStrHospitalCode(strHospitalCode_p);
			itemSubTypeMstVO.setStrEnggItemTypeId(strEnggItemTypeId_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			ItemSubTypeMstDAO.getItemSubTypeCombo(itemSubTypeMstVO, hisDAO);
			wrsItemSubTypeComboOptions = itemSubTypeMstVO
					.getWrsItemSubTypeComboOptions();
			if (wrsItemSubTypeComboOptions != null) {
				strItemSubTypeComboOptions = hisUtil.getOptionValue(
						wrsItemSubTypeComboOptions, "0", "0^Select Value",
						false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getItemSubTypeComboOptions(String,String)-->"
							+ ex.getMessage());
		}
		return strItemSubTypeComboOptions;

	}

	/*public String getEquipmentNameComboOptionsOnStore(ItemWarrantyDtlsTransVO vo)
			throws Exception {

		String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemBrandMstVO gbltItemBrandMstVO;
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltItemBrandMstVO = new ItemBrandMstVO();

			// gbltItemBrandMstVO.setStrMode("5");
			gbltItemBrandMstVO.setStrMode("11");
			gbltItemBrandMstVO.setStrItemCatNo("0");
			gbltItemBrandMstVO.setStrStoreId(vo.getStrStoreId());
			gbltItemBrandMstVO.setStrDepartmentId("0");
			gbltItemBrandMstVO.setStrHospitalCode(vo.getStrHospitalCode());
			// gbltItemBrandMstVO.setStrItemId(strEquipmentNameId_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			ItemBrandMstDAO.getEquipmentNameCombo(gbltItemBrandMstVO, hisDAO);
			wrsItemBrandComboOptions = gbltItemBrandMstVO
					.getWrsItemBrandComboOptions();
			if (wrsItemBrandComboOptions != null) {
				strItemBrandComboOptions = hisUtil.getOptionValue(
						wrsItemBrandComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"ItemMaintContractDtlsBO.getEquipmentNameComboOptionsOnStore(String)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

	}*/

	/*public String getItemNameComboOptionsOnStore(String strStoreId)
			throws Exception {
		
		

		String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemBrandMstVO gbltItemBrandMstVO;
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltItemBrandMstVO = new ItemBrandMstVO();
			gbltItemBrandMstVO.setStrMode("11");
			gbltItemBrandMstVO.setStrItemCatNo("18");
			gbltItemBrandMstVO.setStrStoreId(strStoreId);
			gbltItemBrandMstVO.setStrDepartmentId("0");
			gbltItemBrandMstVO.setStrHospitalCode("100");
			// gbltItemBrandMstVO.setStrItemId(strEquipmentNameId_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			ItemBrandMstDAO.getEquipmentNameCombo(gbltItemBrandMstVO, hisDAO);
			wrsItemBrandComboOptions = gbltItemBrandMstVO
					.getWrsItemBrandComboOptions();
		
			if (wrsItemBrandComboOptions != null) {
				strItemBrandComboOptions = hisUtil.getOptionValue(
						gbltItemBrandMstVO.getWrsItemBrandComboOptions(), "0", "0^Select Value", false);
				

			}

		} catch (Exception ex) {
			throw new Exception(
					"ItemMaintContractDtlsBO.getEquipmentNameComboOptionsOnStore(String)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

	}*/

	public String getItemBrandComboOptionsOnStore(String strHospitalCode_p,
			String strStoreId_p) throws Exception {

		String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemBrandMstVO gbltItemBrandMstVO;
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltItemBrandMstVO = new ItemBrandMstVO();

			// gbltItemBrandMstVO.setStrMode("5");
			gbltItemBrandMstVO.setStrMode("10");
			gbltItemBrandMstVO.setStrItemCatNo("0");
			gbltItemBrandMstVO.setStrStoreId(strStoreId_p);
			gbltItemBrandMstVO.setStrDepartmentId("0");
			gbltItemBrandMstVO.setStrHospitalCode(strHospitalCode_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			ItemBrandMstDAO.getItemBrandCombo(gbltItemBrandMstVO, hisDAO);
			wrsItemBrandComboOptions = gbltItemBrandMstVO
					.getWrsItemBrandComboOptions();
			if (wrsItemBrandComboOptions != null) {
				strItemBrandComboOptions = hisUtil.getOptionValue(
						wrsItemBrandComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getItemBrandComboOptions(String)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

	}

	public String getItemBrandComboOptionsOnDepartment(
			String strHospitalCode_p, String strDepartmentId_p)
			throws Exception {

		String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemBrandMstVO gbltItemBrandMstVO;
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltItemBrandMstVO = new ItemBrandMstVO();

			gbltItemBrandMstVO.setStrMode("4");
			gbltItemBrandMstVO.setStrItemCatNo("0");
			gbltItemBrandMstVO.setStrStoreId("0");
			gbltItemBrandMstVO.setStrDepartmentId(strDepartmentId_p);
			gbltItemBrandMstVO.setStrHospitalCode(strHospitalCode_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			ItemBrandMstDAO.getItemBrandCombo(gbltItemBrandMstVO, hisDAO);
			wrsItemBrandComboOptions = gbltItemBrandMstVO
					.getWrsItemBrandComboOptions();
			if (wrsItemBrandComboOptions != null) {
				strItemBrandComboOptions = hisUtil.getOptionValue(
						wrsItemBrandComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getItemBrandComboOptionsOnDepartment(String)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

	}

	public String getItemBrandComboOptionsOnBasisOfEngg(
			String strHospitalCode_p, String strEnggItemType_p,
			String strEnggItemSubType_p) throws Exception {

		String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		HemtNonItemMstVO hemtNonItemMstVO_p;
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			hemtNonItemMstVO_p = new HemtNonItemMstVO();

			hemtNonItemMstVO_p.setStrMode("1");
			hemtNonItemMstVO_p.setStrEnggItemTypeId(strEnggItemType_p);
			hemtNonItemMstVO_p.setStrEnggItemSubTypeId(strEnggItemSubType_p);
			hemtNonItemMstVO_p.setStrHospCode(strHospitalCode_p);

			HisDAO hisDAO_p = new HisDAO("BMED", "BmedGlobalBO");
			HemtNonItemMstDAO.getNonItemCmb(hemtNonItemMstVO_p, hisDAO_p);
			wrsItemBrandComboOptions = hemtNonItemMstVO_p
					.getWrsNonItemDetails();

			if (wrsItemBrandComboOptions != null) {
				strItemBrandComboOptions = hisUtil.getOptionValue(
						wrsItemBrandComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getItemBrandComboOptionsOnDepartment(String)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

	}

	public String getStockDetails(String strHospitalCode_p,
			String strItemBrandId_p, String strDeptId_p) throws Exception {

		ItemCurrStockDtlMstVO gblItemCurrStockMstVO;
		WebRowSet wrsStockDtl;
		String strStockDtl;
		try {

			gblItemCurrStockMstVO = new ItemCurrStockDtlMstVO();
			gblItemCurrStockMstVO.setStrMode("2");
			gblItemCurrStockMstVO.setStrItemBrandId(strItemBrandId_p);
			gblItemCurrStockMstVO.setStrDeptId(strDeptId_p);
			gblItemCurrStockMstVO.setStrHospCode(strHospitalCode_p);
			gblItemCurrStockMstVO.setStrItemId("0");
			gblItemCurrStockMstVO.setStrBatchNo("0");
			gblItemCurrStockMstVO.setStrStoreId("0");
			gblItemCurrStockMstVO.setStrItemSlNo("0");
			gblItemCurrStockMstVO.setStrItemCatgNo("0");
			gblItemCurrStockMstVO.setStrStockStatusCode("0");

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			ItemCurrStockDtlMstDAO.getStockDtl(gblItemCurrStockMstVO, hisDAO);
			wrsStockDtl = gblItemCurrStockMstVO.getWrsStockDtl();

			strStockDtl = StockDetailsDATA.getStockDtl(wrsStockDtl);

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getStockDetails(String)-->"
					+ ex.getMessage());
		}
		return strStockDtl;

	}

	/**
	 * This Method is Used to get stock details for HLP
	 * 
	 * @param strHospitalCode_p
	 * @param strItemBrandId_p
	 * @param strDeptId_p
	 * @param strMode_p
	 * @return
	 * @throws Exception
	 */
	public String getStockDetails(String strHospitalCode_p,
			String strItemBrandId_p, String strDeptId_p, String strStoreId_p,
			String strMode_p) throws Exception {

		ItemCurrStockDtlMstVO gblItemCurrStockMstVO;
		WebRowSet wrsStockDtl;
		String strStockDtl;
		try {

			gblItemCurrStockMstVO = new ItemCurrStockDtlMstVO();
			if (strMode_p.equals("1")) {
				// gblItemCurrStockMstVO.setStrMode("2");
				gblItemCurrStockMstVO.setStrMode("6"); // For DWH_BMED
			} else {
				gblItemCurrStockMstVO.setStrMode("3");
			}
			gblItemCurrStockMstVO.setStrItemBrandId(strItemBrandId_p);
			gblItemCurrStockMstVO.setStrDeptId(strDeptId_p);
			// gblItemCurrStockMstVO.setStrDeptId("0");
			gblItemCurrStockMstVO.setStrHospCode(strHospitalCode_p);
			gblItemCurrStockMstVO.setStrItemId("0");
			gblItemCurrStockMstVO.setStrBatchNo("0");
			// gblItemCurrStockMstVO.setStrStoreId("0");
			gblItemCurrStockMstVO.setStrStoreId(strStoreId_p);

			gblItemCurrStockMstVO.setStrItemSlNo("0");
			gblItemCurrStockMstVO.setStrItemCatgNo("0");
			gblItemCurrStockMstVO.setStrStockStatusCode("0");

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			ItemCurrStockDtlMstDAO.getStockDtl(gblItemCurrStockMstVO, hisDAO);
			wrsStockDtl = gblItemCurrStockMstVO.getWrsStockDtl();

			strStockDtl = StockDetailsDATA.getStockDtlWarranty(wrsStockDtl);

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getStockDetails(String)-->"
					+ ex.getMessage());
		}
		return strStockDtl;

	}

	/**
	 * Method is used to get previous Warranty details
	 * 
	 * @param strHospitalCode_p
	 * @param strStockInfo_p
	 * @param strMode
	 * @return
	 * @throws Exception
	 */
	public String getPrevWarrantyDetails(String strHospitalCode_p,
			String strStockInfo_p, String strMode) throws Exception {

		WarrantyDtlVO gblWarrantyDtlVO;
		String strPrevMantDtl = null;
		try {

			/*
			 * 0 1 2
			 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID 3
			 * 4 ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'|| 5 6 7
			 * GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item(1 :
			 * for Item 2:for Non-Item)
			 */

			gblWarrantyDtlVO = new WarrantyDtlVO();

			gblWarrantyDtlVO.setStrMode("5");

			if (strStockInfo_p.split("\\^")[7].equals("2")) {
				gblWarrantyDtlVO.setStrItemId(strStockInfo_p.split("\\^")[2]);
				gblWarrantyDtlVO.setStrItemBrandId("0");
			} else {
				gblWarrantyDtlVO.setStrItemId(strStockInfo_p.split("\\^")[1]);
				gblWarrantyDtlVO
						.setStrItemBrandId(strStockInfo_p.split("\\^")[2]);
			}
			gblWarrantyDtlVO.setStrBatchSlNo(strStockInfo_p.split("\\^")[3]);
			gblWarrantyDtlVO.setStrItemSlNo(strStockInfo_p.split("\\^")[4]);
			gblWarrantyDtlVO.setStrSlNo("0");
			gblWarrantyDtlVO.setStrHospitalCode(strHospitalCode_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");

			WarrantyDtlDAO.getData(gblWarrantyDtlVO, hisDAO);

			strPrevMantDtl = PreviousWarrantyDATA.getPreviousWarrantyDetails(
					gblWarrantyDtlVO.getWrsResultData(), strMode);

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getPrevMantDetails(String)-->"
					+ ex.getMessage());
		}
		return strPrevMantDtl;

	}

	/**
	 * Method by Amit Kumar
	 * 
	 * @param strHospitalCode_p
	 * @param strItemBrandId_p
	 * @return
	 * @throws Exception
	 */
	public String getSupplierComboOptions(String strHospitalCode_p,
			String strItemBrandId_p, String strCheckIsItem_p) throws Exception {

		String strSupplierComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		HsttSupplierItemMstVO supplierItemMstVO;
		WebRowSet wrsItemSubTypeComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			supplierItemMstVO = new HsttSupplierItemMstVO();
			if (strCheckIsItem_p.equals("1")) {
				supplierItemMstVO.setStrItemId("0");
				supplierItemMstVO.setStrItemBrandId(strItemBrandId_p);
			} else {
				supplierItemMstVO.setStrItemId(strItemBrandId_p);
				supplierItemMstVO.setStrItemBrandId("0");
			}
			supplierItemMstVO.setStrMode("2");
			supplierItemMstVO.setStrHospCode(strHospitalCode_p);
			supplierItemMstVO.setStrSupplierId("0");
			supplierItemMstVO.setStrSupplItemSlNo("0");

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			HsttSupplierItemMsDAO.getSupplierCmb(supplierItemMstVO, hisDAO);

			wrsItemSubTypeComboOptions = supplierItemMstVO
					.getWrsSupplierDetails();

			if (wrsItemSubTypeComboOptions != null) {
				strSupplierComboOptions = hisUtil.getOptionValue(
						wrsItemSubTypeComboOptions, "0", "0^Select Value",
						false);
			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getSupplierComboOptions(String,String)-->"
							+ ex.getMessage());
		}
		return strSupplierComboOptions;

	}

	/**
	 * To Create File Name on the basis of Doc ref No Developer Adil Wasi
	 * 
	 * @param strHospitalCode_p
	 *            String
	 * 
	 * @return strRetVal the String
	 * 
	 */

	public static String getFileName(FormFile myFile, String strHospitalCode_p,
			String strMode) throws Exception {

		String strFileExt;
		String[] arrStrTmp = null;
		String strFileName;
		try {
			strFileExt = myFile.getFileName();
			arrStrTmp = strFileExt.replace('.', '#').split("#");
			strFileExt = arrStrTmp[arrStrTmp.length - 1];
			strFileName = BmedTransBO
					.getStrDocRefNo(strHospitalCode_p, strMode)
					+ "." + strFileExt;
			// System.out.println("strFileName (Inside BO) ::::::"+strFileName);
			return strFileName;

		} catch (Exception ex) {
			throw new Exception("BmedTransBO.getFileName(String)-->"
					+ ex.getMessage());
		}

	}

	/**
	 * saveItemMaintenanceContactDetails() Method is used to cancel data form
	 * table [ hemt_item_mc_dtl ]
	 * 
	 * @param hemtItemMcGlobalVO_p
	 * @return
	 */
	public static boolean saveItemWarrantyDetails(
			WarrantyDtlVO hsttWarrantyDtlVO_p) throws Exception {
		boolean retVal = false;
		HisDAO hisDAO_p = null;
		try {
			hisDAO_p = new HisDAO("BMED", "BmedGlobalBO");
			WarrantyDtlDAO.insert(hsttWarrantyDtlVO_p, hisDAO_p);

			hisDAO_p.fire(); // Here we Execute in Batch
			retVal = true;
			hsttWarrantyDtlVO_p.setStrMsgType("0");

		} catch (Exception ex) {
			hsttWarrantyDtlVO_p.setStrMsgType("1");
			throw new Exception(
					"BmedGlobalBO.saveItemMaintenanceContactDetails(String,String)-->"
							+ ex.getMessage());
		} finally {

			if (hisDAO_p != null) {

				hisDAO_p.free();

				hisDAO_p = null;

			}

		}
		return retVal;

	}

	/**
	 * Method is used to get Renew page source
	 * 
	 * @param strHospitalCode_p
	 * @param strItemBrandId_p
	 * @return
	 * @throws Exception
	 */
	public String getRenewPageSrc(String strHospitalCode_p,
			String strItemBrandId_p) throws Exception {

		String strMaintTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		SemtMaintTypeMstVO gbltSemtMaintTypeMstVO_p;
		String strUnitComboOptions;
		WebRowSet wrsMainContractComboOptions;

		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltSemtMaintTypeMstVO_p = new SemtMaintTypeMstVO();
			gbltSemtMaintTypeMstVO_p.setStrMode("1");
			gbltSemtMaintTypeMstVO_p.setStrHospCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			SemtMaintTypeMstDAO.getSemtMaintTypeMstCombo(
					gbltSemtMaintTypeMstVO_p, hisDAO);
			strUnitComboOptions = BmedGlobalBO
					.getUnitComboOptions(strHospitalCode_p);
			wrsMainContractComboOptions = gbltSemtMaintTypeMstVO_p
					.getWrsMaintTypeComboOptions();
			if (wrsMainContractComboOptions != null) {
				strMaintTypeComboOptions = hisUtil.getOptionValue(
						wrsMainContractComboOptions, "0", "0^Select Value",
						false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getSupplierComboOptions(String,String)-->"
							+ ex.getMessage());
		}
		return strMaintTypeComboOptions + "@@" + strUnitComboOptions;

	}

	/**
	 * Method is used to get Cancel Page Src
	 * 
	 * @param strHospitalCode_p
	 * @param strItemBrandId_p
	 * @return
	 * @throws Exception
	 */

	public String getCancelPageSrc(String strHospitalCode_p,
			String strItemBrandId_p) throws Exception {

		String strCancelTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		SemtCancelTypeMstVO gbltSemtCancelTypeMstVO_p;
		WebRowSet wrsCancelComboOptions;

		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltSemtCancelTypeMstVO_p = new SemtCancelTypeMstVO();
			gbltSemtCancelTypeMstVO_p.setStrMode("1");
			gbltSemtCancelTypeMstVO_p.setStrHospCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			SemtCancelTypeMstDAO.getSemtCancelTypeMstCombo(
					gbltSemtCancelTypeMstVO_p, hisDAO);
			wrsCancelComboOptions = gbltSemtCancelTypeMstVO_p
					.getWrsCancelTypeInfo();
			if (wrsCancelComboOptions != null) {
				strCancelTypeComboOptions = hisUtil.getOptionValue(
						wrsCancelComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getSupplierComboOptions(String,String)-->"
							+ ex.getMessage());
		}
		return strCancelTypeComboOptions;

	}

	/**
	 * to get data for view page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void viewRecord(ItemWarrantyDtlsTransVO vo) {

		ItemWarrantyDtlsTransDAO.viewRecord(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("ItemMaintContractDtlsBO.viewRecord() --> "
					+ strErr);
		}

	}

}
