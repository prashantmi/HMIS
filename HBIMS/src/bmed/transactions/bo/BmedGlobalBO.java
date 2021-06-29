package bmed.transactions.bo;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.dao.EnggItemTypeDAO;
import bmed.dao.EnggSkillsMstDAO;
import bmed.dao.GbltDepartmentMstDAO;
import bmed.dao.HemtCompEscMstDAO;
import bmed.dao.HemtNonItemMstDAO;
import bmed.dao.HemtServiceEnggMstDAO;
import bmed.dao.HsttItemMstDAO;
import bmed.dao.HsttStoreCategoryMstDAO;
import bmed.dao.HsttSupplierItemMsDAO;
import bmed.dao.HsttSupplierMstDAO;
import bmed.dao.ItemBrandMstDAO;
import bmed.dao.ItemCurrStockDtlMstDAO;
import bmed.dao.ItemSubTypeMstDAO;
import bmed.dao.ItemTypeMstDAO;
import bmed.dao.PistEmpCurDetailDtlDAO;
import bmed.dao.SemtCancelTypeMstDAO;
import bmed.dao.SemtMaintTypeMstDAO;
import bmed.dao.SparePartStatusMstDAO;
import bmed.dao.StoreMstDAO;
import bmed.dao.TestMstDAO;
import bmed.dao.TestParameterMstDAO;
import bmed.dao.UnitMstDAO;
import bmed.global.controller.data.StockDetailsDATA;
import bmed.vo.EnggItemTypeVO;
import bmed.vo.EnggSkillsMstVO;
import bmed.vo.GbltDepartmentMstVO;
import bmed.vo.HemDeskVO;
import bmed.vo.HemtCompEscMstVO;
import bmed.vo.HemtNonItemMstVO;
import bmed.vo.HemtServiceEnggMstVO;
import bmed.vo.HsttItemMstVO;
import bmed.vo.HsttStoreCategoryMstVO;
import bmed.vo.HsttSupplierItemMstVO;
import bmed.vo.HsttSupplierMstVO;
import bmed.vo.ItemBrandMstVO;
import bmed.vo.ItemCurrStockDtlMstVO;
import bmed.vo.ItemSubTypeMstVO;
import bmed.vo.ItemTypeMstVO;
import bmed.vo.PistEmpCurDetailDtlVO;
import bmed.vo.SemtCancelTypeMstVO;
import bmed.vo.SemtMaintTypeMstVO;
import bmed.vo.SparePartStatusMstVO;
import bmed.vo.StoreMstVO;
import bmed.vo.TestMstVO;
import bmed.vo.TestParameterMstVO;
import bmed.vo.UnitMstVO;
//import bmed.global.controller.data.PerviousMaintenanceDetailsDATA;

/**
 * @author deps
 * 
 */
public class BmedGlobalBO {

	/**
	 * @param strHospitalCode_p
	 *            The hospital code
	 * @return list of department options.
	 * @throws Exception
	 */
	public String getDepartmentComboOptions(String strHospitalCode_p,String strSeatId,int strMode)
			throws Exception {

		String strDepartmentComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		GbltDepartmentMstVO gbltDepartmentMstVO;
		WebRowSet wrsDepartmentComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltDepartmentMstVO = new GbltDepartmentMstVO();
			gbltDepartmentMstVO.setStrHospitalCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			
			
			gbltDepartmentMstVO.setStrSeatId(strSeatId);
				gbltDepartmentMstVO.setStrMode(Integer.toString(strMode));
			
			
			GbltDepartmentMstDAO.getDepartmentCombo(gbltDepartmentMstVO, hisDAO);
			wrsDepartmentComboOptions = gbltDepartmentMstVO.getWrsDepartmentOptions();
			if (wrsDepartmentComboOptions != null) {
				strDepartmentComboOptions = hisUtil.getOptionValue(wrsDepartmentComboOptions, "0","0^Select Value", false);

			}

		} catch (Exception ex) {
			
			ex.printStackTrace();
			
			throw new Exception(
					"BmedGlobalBO.getDepartmentComboOptions(String)-->"
							+ ex.getMessage());
		}
		return strDepartmentComboOptions;

	}

	/**
	 * @param strHospitalCode_p  The hospital code
	 * @param strSeatId_p  The Seat Id
	 * @return list of department options based on Role Seat.
	 * @throws Exception
	 */
	public String getDepartmentBasedOnRoleSeatCombo(String strHospitalCode_p, String strSeatId_p)
			throws Exception {

		String strDepartmentComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		GbltDepartmentMstVO gbltDepartmentMstVO;
		WebRowSet wrsDepartmentComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltDepartmentMstVO = new GbltDepartmentMstVO();
			gbltDepartmentMstVO.setStrHospitalCode(strHospitalCode_p);
			gbltDepartmentMstVO.setStrSeatId(strSeatId_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			GbltDepartmentMstDAO
					.getDepartmentBasedOnRoleSeatCombo(gbltDepartmentMstVO, hisDAO);
			wrsDepartmentComboOptions = gbltDepartmentMstVO
					.getWrsDepartmentOptions();
			if (wrsDepartmentComboOptions != null) {
				strDepartmentComboOptions = hisUtil.getOptionValue(wrsDepartmentComboOptions, "0",
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

	public String getStoreComboOptions(String strHospitalCode_p,
			String strSeatid_p) throws Exception {

		String strStoreComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		StoreMstVO storeMstVO;
		WebRowSet wrsStoreComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			storeMstVO = new StoreMstVO();

			storeMstVO.setStrHospitalCode(strHospitalCode_p);
			storeMstVO.setStrMode("1");
			storeMstVO.setStrSeatid(strSeatid_p);

			 
			storeMstVO.setStrStoreId("0");
			storeMstVO.setStrStoretypeId("0");
			storeMstVO.setStrDeptCode("0");

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
			storeMstVO.setStrMode("3");
			storeMstVO.setStrSeatid(strSeatid_p);
			storeMstVO.setStrDeptCode(strDeptCode_p);

			 //Default Value 
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

	public String getItemBrandComboOptionsOnItemCategory(
			String strHospitalCode_p, String strItemCatNo_p) throws Exception {

		String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemBrandMstVO gbltItemBrandMstVO;
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltItemBrandMstVO = new ItemBrandMstVO();

			gbltItemBrandMstVO.setStrMode("1");
			gbltItemBrandMstVO.setStrItemCatNo(strItemCatNo_p);
			gbltItemBrandMstVO.setStrStoreId("0");
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
					"BmedGlobalBO.getItemBrandComboOptionsOnItemCategory(String)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

	}

	public String getItemBrandComboOptionsOnItemCategoryAndDepartment(
			String strHospitalCode_p, String strItemCatNo_p,
			String strDepartmentId_p) throws Exception {

		String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemBrandMstVO gbltItemBrandMstVO;
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltItemBrandMstVO = new ItemBrandMstVO();

			gbltItemBrandMstVO.setStrMode("2");
			gbltItemBrandMstVO.setStrItemCatNo(strItemCatNo_p);
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
					"BmedGlobalBO.getItemBrandComboOptionsOnItemCategoryAndDepartment(String)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

	}

	public String getItemBrandComboOptionsOnItemCategoryAndStore(
			String strHospitalCode_p, String strItemCatNo_p, String strStoreId_p)
			throws Exception {

		String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemBrandMstVO gbltItemBrandMstVO;
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltItemBrandMstVO = new ItemBrandMstVO();

			gbltItemBrandMstVO.setStrMode("3");
			gbltItemBrandMstVO.setStrItemCatNo(strItemCatNo_p);
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
					"BmedGlobalBO.getItemBrandComboOptionsOnItemCategoryAndStore(String)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

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
			throw new Exception("BmedGlobalBO.getMaintenanceContTypeComboOptions(String)-->"
					+ ex.getMessage());
		}
		return strMaintTypeComboOptions;

	}

//	public String getRenewPageSrc(String strHospitalCode_p,
//			String strItemBrandId_p) throws Exception {
//
//		String strMaintTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
//		SemtMaintTypeMstVO gbltSemtMaintTypeMstVO_p;
//		String strUnitComboOptions;
//		WebRowSet wrsMainContractComboOptions;
//
//		HisUtil hisUtil;
//		try {
//			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
//			gbltSemtMaintTypeMstVO_p = new SemtMaintTypeMstVO();
//			gbltSemtMaintTypeMstVO_p.setStrMode("1");
//			gbltSemtMaintTypeMstVO_p.setStrHospCode(strHospitalCode_p);
//			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
//			SemtMaintTypeMstDAO.getSemtMaintTypeMstCombo(
//					gbltSemtMaintTypeMstVO_p, hisDAO);
//			strUnitComboOptions = this.getUnitComboOptions(strHospitalCode_p);
//			wrsMainContractComboOptions = gbltSemtMaintTypeMstVO_p
//					.getWrsMaintTypeComboOptions();
//			if (wrsMainContractComboOptions != null) {
//				strMaintTypeComboOptions = hisUtil.getOptionValue(
//						wrsMainContractComboOptions, "0", "0^Select Value",
//						false);
//
//			}
//
//		} catch (Exception ex) {
//			throw new Exception(
//					"BmedGlobalBO.getSupplierComboOptions(String,String)-->"
//							+ ex.getMessage());
//		}
//		return strMaintTypeComboOptions + "@@" + strUnitComboOptions;
//
//	}

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

	public String getItemBrandComboOptionsOnStore(String strHospitalCode_p,String strStoreId_p,ItemBrandMstVO gbltItemBrandMstVO) throws Exception 
	{
		String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try 
		{
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");

			gbltItemBrandMstVO.setStrItemCatNo("0");
			gbltItemBrandMstVO.setStrStoreId(strStoreId_p);
			gbltItemBrandMstVO.setStrDepartmentId("0");
			gbltItemBrandMstVO.setStrHospitalCode(strHospitalCode_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			ItemBrandMstDAO.getItemBrandCombo(gbltItemBrandMstVO, hisDAO);
			wrsItemBrandComboOptions = gbltItemBrandMstVO.getWrsItemBrandComboOptions();
			if (wrsItemBrandComboOptions != null) 
			{
				strItemBrandComboOptions = hisUtil.getOptionValue(wrsItemBrandComboOptions, "0", "0^Select Value", false);
			}
		} 
		catch (Exception ex) 
		{
			throw new Exception("BmedGlobalBO.getItemBrandComboOptions(String)-->"+ ex.getMessage());
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

	}/*

	public String getPrevMantDetails(String strHospitalCode_p,
			String strStockInfo_p, String strMode) throws Exception {

		HemtItemMcDtlVO gblItemMCDtlVO;
		String strPrevMantDtl = null;
		try {
			
			 * 0 1 2
			 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID 3
			 * 4 ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'|| 5 6 7
			 * GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item(1
			 * : for Item 2:for Non-Item)
			 
			gblItemMCDtlVO = new HemtItemMcDtlVO();
			if (strStockInfo_p.split("\\^")[7].equals("1")) {
				gblItemMCDtlVO.setStrMode("2");
			} else {
				gblItemMCDtlVO.setStrMode("3");
			}
			// System.out.println("Hosp Code:::"+strHospitalCode_p);
			// System.out.println("Item Brand
			// Id:::"+strStockInfo_p.split("\\^")[2]);
			// System.out.println("Batch:::"+strStockInfo_p.split("\\^")[3]);
			// System.out.println("Item Sl
			// No:::"+strStockInfo_p.split("\\^")[4]);

			gblItemMCDtlVO.setStrItemId(strStockInfo_p.split("\\^")[2]);
			gblItemMCDtlVO.setStrBatchNo(strStockInfo_p.split("\\^")[3]);
			gblItemMCDtlVO.setStrItemSlNo(strStockInfo_p.split("\\^")[4]);
			gblItemMCDtlVO.setStrSlNo("0");
			gblItemMCDtlVO.setStrHospCode(strHospitalCode_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");

			HemtItemMcDtlDAO.getPrevMantDtl(gblItemMCDtlVO, hisDAO);

			// strPrevMantDtl =
			// PerviousMaintenanceDetailsDATA.getPreviousMaintenanceDetails(gblItemMCDtlVO.getWrsMCDetails(),strMode);

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getPrevMantDetails(String)-->"
					+ ex.getMessage());
		}
		return strPrevMantDtl;

	}

	public String getPrevWarrantyDetails(String strHospitalCode_p,
			String strStockInfo_p, String strMode) throws Exception {

		WarrantyDtlVO gblWarrantyDtlVO;
		String strPrevMantDtl = null;
		try {

			
			 * 0 1 2
			 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID 3
			 * 4 ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'|| 5 6 7
			 * GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item(1
			 * : for Item 2:for Non-Item)
			 
			gblWarrantyDtlVO = new WarrantyDtlVO();

			if (strStockInfo_p.split("\\^")[7].equals("1")) {
				gblWarrantyDtlVO.setStrMode("2");

			} else {
				gblWarrantyDtlVO.setStrMode("3");
			}
			// System.out.println("Hosp Code::IN Prev
			// Warranty(GlbBo):::"+strHospitalCode_p);
			// System.out.println("Item Brand
			// Id:::"+strStockInfo_p.split("\\^")[2]);
			// System.out.println("Batch:::"+strStockInfo_p.split("\\^")[3]);
			// System.out.println("Item Sl
			// No:::"+strStockInfo_p.split("\\^")[4]);

			gblWarrantyDtlVO.setStrItemId(strStockInfo_p.split("\\^")[1]);
			gblWarrantyDtlVO.setStrItemBrandId(strStockInfo_p.split("\\^")[2]);
			gblWarrantyDtlVO.setStrBatchSlNo(strStockInfo_p.split("\\^")[3]);
			gblWarrantyDtlVO.setStrItemSlNo(strStockInfo_p.split("\\^")[4]);
			gblWarrantyDtlVO.setStrSlNo("0");
			gblWarrantyDtlVO.setStrHospitalCode(strHospitalCode_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");

			WarrantyDtlDAO.getData(gblWarrantyDtlVO, hisDAO);

			// strPrevMantDtl =
			// PreviousWarrantyDATA.getPreviousWarrantyDetails(gblWarrantyDtlVO.getWrsResultData(),strMode);

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getPrevMantDetails(String)-->"
					+ ex.getMessage());
		}
		return strPrevMantDtl;

	}

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

	}*/

	public String getCancelTypeComboOptions(String strHospitalCode_p)
			throws Exception {

		String strCancelTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		SemtCancelTypeMstVO semtCancelTypeMstVO;
		WebRowSet wrsCancelTypeComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			semtCancelTypeMstVO = new SemtCancelTypeMstVO();

			semtCancelTypeMstVO.setStrMode("1");
			semtCancelTypeMstVO.setStrHospCode(strHospitalCode_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");

			SemtCancelTypeMstDAO.getSemtCancelTypeMstCombo(semtCancelTypeMstVO,
					hisDAO);
			wrsCancelTypeComboOptions = semtCancelTypeMstVO
					.getWrsCancelTypeInfo();
			if (wrsCancelTypeComboOptions != null) {
				strCancelTypeComboOptions = hisUtil
						.getOptionValue(wrsCancelTypeComboOptions, "0",
								"0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getCancelTypeComboOptions(String)-->"
							+ ex.getMessage());
		}
		return strCancelTypeComboOptions;

	}

	public String getDepartmentComboOptions(String strHospitalCode_p,String strSelectedValue,String strSeatId,int strMode) throws Exception {

		String strDepartmentComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		GbltDepartmentMstVO gbltDepartmentMstVO;
		WebRowSet wrsDepartmentComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltDepartmentMstVO = new GbltDepartmentMstVO();
			gbltDepartmentMstVO.setStrHospitalCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			
			gbltDepartmentMstVO.setStrSeatId(strSeatId);
			gbltDepartmentMstVO.setStrMode(Integer.toString(strMode));
			
			GbltDepartmentMstDAO.getDepartmentCombo(gbltDepartmentMstVO, hisDAO);
			wrsDepartmentComboOptions = gbltDepartmentMstVO.getWrsDepartmentOptions();
			if (wrsDepartmentComboOptions != null) {
				if (strSelectedValue != null) 
				{
					strDepartmentComboOptions = hisUtil.getOptionValue(wrsDepartmentComboOptions, strSelectedValue,"0^Select Value", false);

				}
				else 
				{
					strDepartmentComboOptions = hisUtil.getOptionValue(wrsDepartmentComboOptions, "0", "0^Select Value",false);

				}

			}

		}
		catch (Exception ex) 
		{
			throw new Exception("BmedGlobalBO.getDepartmentComboOptions(String)-->"+ ex.getMessage());
		}
		return strDepartmentComboOptions;

	}

	/**
	 * To Get Item Category Name Data on basis of store
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 */

	public String getItemCategoryComboOptionsOnBasisOfStore(
			String strHospitalCode_p, String strStoreId_p) throws Exception {
		String strItemCategoryComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		HsttStoreCategoryMstVO hsttStoreCategoryMstVO;
		WebRowSet wrsItemCategoryComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			hsttStoreCategoryMstVO = new HsttStoreCategoryMstVO();

			hsttStoreCategoryMstVO.setStrMode("1");
			hsttStoreCategoryMstVO.setStrStoreId(strStoreId_p);
			hsttStoreCategoryMstVO.setStrItemCatNo("0");
			hsttStoreCategoryMstVO.setStrHospitalCode(strHospitalCode_p);
			hsttStoreCategoryMstVO.setStrCatSlno("0");

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			
			if(!hsttStoreCategoryMstVO.getStrStoreId().equals("0"))
			{
				HsttStoreCategoryMstDAO.getData(hsttStoreCategoryMstVO, hisDAO);
			}
			
			
			wrsItemCategoryComboOptions = hsttStoreCategoryMstVO.getWrsData();
			if (wrsItemCategoryComboOptions != null) {
				strItemCategoryComboOptions = hisUtil.getOptionValue(
						wrsItemCategoryComboOptions, "0", "0^Select Value",
						false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getItemCategoryComboOptionsOnBasisOfStore(String strHospitalCode_p, String strStoreId_p)-->"
							+ ex.getMessage());
		}
		return strItemCategoryComboOptions;
	}

	/**
	 * To Get Item Name Data on basis of Item Category
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 */

	public String getItemNameComboOptionsOnItemCategory(
			String strHospitalCode_p, String strItemCategoryNo_p)
			throws Exception {
		String strItemCategoryComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		HsttItemMstVO hsttItemMstVO;
		WebRowSet wrsItemComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			hsttItemMstVO = new HsttItemMstVO();

			hsttItemMstVO.setStrMode("1");
			hsttItemMstVO.setStrItemId("0");// Default
			hsttItemMstVO.setStrHospitalCode(strHospitalCode_p);
			hsttItemMstVO.setStrItemCatNo(strItemCategoryNo_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			
			if(!strItemCategoryNo_p.equals("0"))
			{
				HsttItemMstDAO.getData(hsttItemMstVO, hisDAO);
			}
			
			wrsItemComboOptions = hsttItemMstVO.getWrsData();
			if (wrsItemComboOptions != null) {
				strItemCategoryComboOptions = hisUtil.getOptionValue(
						wrsItemComboOptions, "0", "0^Select Value", false);
			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getItemCategoryComboOptionsOnBasisOfStore(String strHospitalCode_p, String strStoreId_p)-->"
							+ ex.getMessage());
		}
		return strItemCategoryComboOptions;
	}

	 //To get Combo of Engg Skill 
	public String getSkillComboOptions(String strHospitalCode_p,
			String strSelectedValue) throws Exception {

		String strSkillComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		EnggSkillsMstVO enggSkillsMstVO;
		WebRowSet wrsSkillComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			enggSkillsMstVO = new EnggSkillsMstVO();
			enggSkillsMstVO.setStrMode("1");
			enggSkillsMstVO.setStrHospitalCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			EnggSkillsMstDAO.getSkillCombo(enggSkillsMstVO, hisDAO);
			wrsSkillComboOptions = enggSkillsMstVO.getWrsData();
			if (wrsSkillComboOptions != null) {
				if (strSelectedValue != null) {
					strSkillComboOptions = hisUtil.getOptionValue(
							wrsSkillComboOptions, strSelectedValue,
							"0^Select Value", false);

				} else {
					strSkillComboOptions = hisUtil.getOptionValue(
							wrsSkillComboOptions, "0", "0^Select Value", false);

				}

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getSkillComboOptions(String,String)-->"
							+ ex.getMessage());
		}
		return strSkillComboOptions;

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

	/**
	 * Added By Amit Kumar 13 May 2011
	 * 
	 * @param strHospitalCode_p
	 *            The hospital code
	 * @return list of Engineering Item Type.
	 * @throws Exception
	 */
	public String getEnggItemTypeComboOptions(String strHospitalCode_p)
			throws Exception {

		String strEnggItemTypeComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		EnggItemTypeVO gbltEnggMstVO;
		EnggItemTypeDAO enggItemTypeDAO;
		WebRowSet wrsEnggItemTypeComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltEnggMstVO = new EnggItemTypeVO();
			enggItemTypeDAO = new EnggItemTypeDAO();
			gbltEnggMstVO.setStrHospCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");

			wrsEnggItemTypeComboOptions = enggItemTypeDAO.getEnggItemTypeCmb(
					gbltEnggMstVO, hisDAO);
			if (wrsEnggItemTypeComboOptions != null) {
				strEnggItemTypeComboOptions = hisUtil.getOptionValue(
						wrsEnggItemTypeComboOptions, "0", "0^Select Value",
						false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getDepartmentComboOptions(String)-->"
							+ ex.getMessage());
		}
		return strEnggItemTypeComboOptions;

	}

	/**
	 * Added By Amit Kumar 13 May 2011
	 * 
	 * @param strHospitalCode_p
	 *            The hospital code
	 * @return list of Engineering Item Type.
	 * @throws Exception
	 */
	public String getEscLevelCombo(String strMode, String strHospitalCode_p,
			String strEnggItemTypeId_p, String strEnggItemSubTypeId_p,
			String strEmpId_p) throws Exception {

		String strEscLevelComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		HemtCompEscMstVO gbltCompEscMstVO;
		WebRowSet wrsEscLevelComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltCompEscMstVO = new HemtCompEscMstVO();
			gbltCompEscMstVO.setStrMode(strMode);
			gbltCompEscMstVO.setStrEnggItemType(strEnggItemTypeId_p);
			gbltCompEscMstVO.setStrEnggItemSubType(strEnggItemSubTypeId_p);
			gbltCompEscMstVO.setStrEmpId(strEmpId_p);
			gbltCompEscMstVO.setStrHospitalCode(strHospitalCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			HemtCompEscMstDAO.getComplaintEscCmb(gbltCompEscMstVO, hisDAO);
			wrsEscLevelComboOptions = gbltCompEscMstVO.getWrsData();
			if (wrsEscLevelComboOptions != null) {
				strEscLevelComboOptions = hisUtil.getOptionValue(
						wrsEscLevelComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getDepartmentComboOptions(String)-->"
							+ ex.getMessage());
		}
		return strEscLevelComboOptions;

	}

	/**
	 * Added By Amit Kumar 13 May 2011
	 * 
	 * @param strHospitalCode_p
	 *            The hospital code
	 * @return list of Engineering Item Type.
	 * @throws Exception
	 */
	public String getServiceEnggNameCombo(HemDeskVO hemDesk_p) throws Exception {

		String strServiceEnggComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		HemtServiceEnggMstVO gbltServiceEnggMstVO;
		WebRowSet wrsServiceEnggComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			gbltServiceEnggMstVO = new HemtServiceEnggMstVO();
			gbltServiceEnggMstVO.setStrMode(hemDesk_p.getStrMode());
			gbltServiceEnggMstVO.setStrHospCode(hemDesk_p.getStrHospCode());
			gbltServiceEnggMstVO.setStrEnggItemTypeId(hemDesk_p
					.getStrEnggItemTypeId());
			gbltServiceEnggMstVO.setStrEmpId("0");
			gbltServiceEnggMstVO.setStrSkillId("0");
			gbltServiceEnggMstVO.setStrEnggItemSubTypeId(hemDesk_p
					.getStrEnggItemSubTypeId());
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			HemtServiceEnggMstDAO.getServiceEnggMstCmb(gbltServiceEnggMstVO,
					hisDAO);
			wrsServiceEnggComboOptions = gbltServiceEnggMstVO.getWrsData();

			if (wrsServiceEnggComboOptions != null) {
				strServiceEnggComboOptions = hisUtil.getOptionValue(
						wrsServiceEnggComboOptions, "0", "0^Select Value",
						false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getDepartmentComboOptions(String)-->"
							+ ex.getMessage());
		}

		return strServiceEnggComboOptions;

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
			
			wrsItemSubTypeComboOptions = supplierItemMstVO.getWrsSupplierDetails();

			if (wrsItemSubTypeComboOptions != null)
			{
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

	public String getSparePartStatusOptions(String strHospitalCode_p,
			String strSelectedValue_p) throws Exception {

		String strSparePartStatusComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		SparePartStatusMstVO sparePartStatusMstVO;
		WebRowSet wrsSparePartStatusComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			sparePartStatusMstVO = new SparePartStatusMstVO();
			sparePartStatusMstVO.setStrHospitalCode(strHospitalCode_p);
			sparePartStatusMstVO.setStrMode("1");
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			SparePartStatusMstDAO.getSparePartStatusCombo(sparePartStatusMstVO,
					hisDAO);
			wrsSparePartStatusComboOptions = sparePartStatusMstVO.getWrsData();
			if (wrsSparePartStatusComboOptions != null) {
				if (strSelectedValue_p != null) {
					strSparePartStatusComboOptions = hisUtil.getOptionValue(
							wrsSparePartStatusComboOptions, strSelectedValue_p,
							"0^Select Value", false);

				} else {
					strSparePartStatusComboOptions = hisUtil.getOptionValue(
							wrsSparePartStatusComboOptions, "0",
							"0^Select Value", false);

				}

			}

		} catch (Exception ex) {
			
			ex.printStackTrace();
			throw new Exception(
					"BmedGlobalBO.getSparePartStatusOptions(String,String)-->"
							+ ex.getMessage());
		}
		return strSparePartStatusComboOptions;

	}

	/**
	 * This method is used to get the combo options for all spare part item.
	 * 
	 * @param strHospitalCode_p
	 *            The global hospital code.
	 * @param strSelectedValue_p
	 *            Value of the option to be preselected. It may be null or '0'
	 *            to make the 'Select Value' option preselected.
	 * @return the resultant combo options.
	 * @throws Exception
	 */
	public String getAllSparePartOptions(String strHospitalCode_p,
			String strSelectedValue_p) throws Exception {

		String strAllSparePartOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemBrandMstVO itemBrandMstVO;
		WebRowSet wrsSparePartOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			itemBrandMstVO = new ItemBrandMstVO();
			itemBrandMstVO.setStrHospitalCode(strHospitalCode_p);
			itemBrandMstVO.setStrMode("8");
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");

			ItemBrandMstDAO.getItemBrandCombo(itemBrandMstVO, hisDAO);
			wrsSparePartOptions = itemBrandMstVO.getWrsItemBrandComboOptions();
			if (wrsSparePartOptions != null) {
				if (strSelectedValue_p != null) {
					strAllSparePartOptions = hisUtil.getOptionValue(
							wrsSparePartOptions, strSelectedValue_p,
							"0^Select Value", false);

				} else {
					strAllSparePartOptions = hisUtil.getOptionValue(
							wrsSparePartOptions, "0", "0^Select Value", false);

				}

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedGlobalBO.getAllSparePartOptions(String,String)-->"
							+ ex.getMessage());
		}
		return strAllSparePartOptions;

	}

	/**
	 * To Get Manufacturer Name
	 * 
	 * @param strHospitalCode_p the String
	 * 
	 * @return	strItemCategoryComboOptions the String
	 */
	
	public String getManufacturerNameComboOptions(String strHospitalCode_p) throws Exception
	{
		String strItemCategoryComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		HsttSupplierMstVO hsttSupplierMstVO;
		WebRowSet wrsItemComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedTransBO");
			hsttSupplierMstVO = new HsttSupplierMstVO();
	
			hsttSupplierMstVO.setStrMode("1");
			hsttSupplierMstVO.setStrSupplierId("0");// Default
			hsttSupplierMstVO.setStrHospitalCode(strHospitalCode_p);
			
			HisDAO hisDAO = new HisDAO("BMED", "BmedTransBO");
			HsttSupplierMstDAO.getData(hsttSupplierMstVO, hisDAO);
			wrsItemComboOptions = hsttSupplierMstVO.getWrsData();
			if (wrsItemComboOptions != null)
			{
				strItemCategoryComboOptions = hisUtil.getOptionValue(
				wrsItemComboOptions, "0", "0^Select Value", false);
			}

		} 
		catch (Exception ex) 
		{
			throw new Exception("BmedTransBO.getManufacturerNameComboOptions(String strHospitalCode_p)-->" + ex.getMessage());
		}
		return strItemCategoryComboOptions;
	}
	
	
	/**
	 * To Get Manufacturer Name
	 * 
	 * @param strHospitalCode_p the String
	 * 
	 * @return	strItemCategoryComboOptions the String
	 */
	
	public String getManufacturerDeatils(String strHospitalCode_p,String strSupplierId) throws Exception
	{
		String strReturnString = "";
		HsttSupplierMstVO hsttSupplierMstVO;
		HsttSupplierMstVO vo=null;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedTransBO");
			hsttSupplierMstVO = new HsttSupplierMstVO();
	
			hsttSupplierMstVO.setStrMode("1");
			hsttSupplierMstVO.setStrSupplierId("0");// Default
			hsttSupplierMstVO.setStrHospitalCode(strHospitalCode_p);
			
			HisDAO hisDAO = new HisDAO("BMED", "BmedTransBO");
		
			vo=HsttSupplierMstDAO.getDetailsData(strSupplierId,strHospitalCode_p, hisDAO);
			if(vo!=null){
				strReturnString+=vo.getStrContractNo()+"#"+vo.getStrAddress();
				
			}
			

		} 
		catch (Exception ex) 
		{
			 ex.printStackTrace();
			throw new Exception("BmedTransBO.getManufacturerNameComboOptions(String strHospitalCode_p)-->" + ex.getMessage());
		}
		return strReturnString;
	}
	
	
	
	/**
	 * To Get Employee Name
	 * 
	 * @param strHospitalCode_p the String
	 * 
	 * @return	strItemCategoryComboOptions the String
	 */
	
	public String getEmployeeNameComboOptions(String strHospitalCode_p) throws Exception
	{
		String strEmployeeNameComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		PistEmpCurDetailDtlVO pistEmpCurDetailDtlVO;
		WebRowSet wrsComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedTransBO");
			pistEmpCurDetailDtlVO = new PistEmpCurDetailDtlVO();
	
			pistEmpCurDetailDtlVO.setStrMode("1");
			pistEmpCurDetailDtlVO.setStrHospitalCode(strHospitalCode_p);
			
			HisDAO hisDAO = new HisDAO("BMED", "BmedTransBO");
			PistEmpCurDetailDtlDAO.getEmployeeCombo(pistEmpCurDetailDtlVO, hisDAO);
			wrsComboOptions = pistEmpCurDetailDtlVO.getWrsData();
			if (wrsComboOptions != null)
			{
				strEmployeeNameComboOptions = hisUtil.getOptionValue(
						wrsComboOptions, "0", "0^Select Value", false);
			}

		} 
		catch (Exception ex) 
		{
			throw new Exception("BmedGlobalBO.getEmployeeNameComboOptions(String strHospitalCode_p)-->" + ex.getMessage());
		}
		return strEmployeeNameComboOptions;
	}
	
	
	public String getEmployeeNameDeptBasedComboOptions(String strHospitalCode_p, String strDeptCode_p) throws Exception
	{
		String strEmployeeNameComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		PistEmpCurDetailDtlVO pistEmpCurDetailDtlVO;
		WebRowSet wrsComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedTransBO");
			pistEmpCurDetailDtlVO = new PistEmpCurDetailDtlVO();
	
			pistEmpCurDetailDtlVO.setStrMode("1");
			pistEmpCurDetailDtlVO.setStrHospitalCode(strHospitalCode_p);
			pistEmpCurDetailDtlVO.setStrDeptId(strDeptCode_p);
			HisDAO hisDAO = new HisDAO("BMED", "BmedTransBO");
			if(strDeptCode_p!=null){
			PistEmpCurDetailDtlDAO.getEmployeeDeptBasedCombo(pistEmpCurDetailDtlVO, hisDAO);
			}
			wrsComboOptions = pistEmpCurDetailDtlVO.getWrsData();
			if (wrsComboOptions != null)
			{
				strEmployeeNameComboOptions = hisUtil.getOptionValue(
						wrsComboOptions, "0", "0^Select Value", false);
			}

		} 
		catch (Exception ex) 
		{
			throw new Exception("BmedGlobalBO.getEmployeeNameComboOptions(String strHospitalCode_p)-->" + ex.getMessage());
		}
		return strEmployeeNameComboOptions;
	}
	
	public String getTestComboOptions(String strHospitalCode_p
			) throws Exception {

		String strTestComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		TestMstVO testMstVO;
		WebRowSet wrsStoreComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			testMstVO = new TestMstVO();

			testMstVO.setStrHospitalCode(strHospitalCode_p);
			
			testMstVO.setStrMode("2");
		

//			 Default Value 
			
			testMstVO.setStrEnggItemSubTypeId("0");
			testMstVO.setStrEnggItemTypeId("0");
					
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			TestMstDAO.getTestCombo(testMstVO, hisDAO);
			wrsStoreComboOptions = testMstVO.getWrsData();
			if (wrsStoreComboOptions != null) {
				strTestComboOptions = hisUtil.getOptionValue(
						wrsStoreComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getStoreComboOptions(String)-->"
					+ ex.getMessage());
		}
		return strTestComboOptions;

	}
	
	
	public String getTestParaMeterComboOptions(String strHospitalCode_p) throws Exception 
	{

		String strTestParaComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		TestParameterMstVO testPMstVO;
		WebRowSet wrsStoreComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			testPMstVO = new TestParameterMstVO();
		
			testPMstVO.setStrHospitalCode(strHospitalCode_p);
			
			testPMstVO.setStrMode("1");
		
		
			// Default Value 
			
					
			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			TestParameterMstDAO.getTestParameterCombo(testPMstVO, hisDAO);
			wrsStoreComboOptions = testPMstVO.getWrsData();
			if (wrsStoreComboOptions != null) {
				strTestParaComboOptions = hisUtil.getOptionValue(
						wrsStoreComboOptions, "0", "0^Select Value", false);
		
			}
		
		} catch (Exception ex) {
			throw new Exception("BmedGlobalBO.getStoreComboOptions(String)-->"
					+ ex.getMessage());
		}
		return strTestParaComboOptions;
		
	}
	
	public String getNatureOfServiceComboOptions(String strHospitalCode_p,String strSeatid_p, String strDeptCode_p) throws Exception 
	{
		String strData = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		StoreMstVO storeMstVO;
		WebRowSet wrs;
		HisUtil hisUtil;
		try 
		{
			hisUtil = new HisUtil("BMED", "BmedGlobalBO");
			storeMstVO = new StoreMstVO();

			storeMstVO.setStrHospitalCode(strHospitalCode_p);
			storeMstVO.setStrMode("3");
			storeMstVO.setStrSeatid(strSeatid_p);
			storeMstVO.setStrDeptCode(strDeptCode_p);

			 //Default Value 
			storeMstVO.setStrStoreId("0");
			storeMstVO.setStrStoretypeId("0");

			HisDAO hisDAO = new HisDAO("BMED", "BmedGlobalBO");
			StoreMstDAO.getNatureOfServiceCombo(storeMstVO, hisDAO);
			wrs = storeMstVO.getWrsNatureOfService();
			if (wrs != null) 
			{
				strData = hisUtil.getOptionValue(wrs, "0", "0^Select Value", false);
			}
		} 
		catch (Exception ex) 
		{
			throw new Exception("BmedGlobalBO.getNatureOfServiceComboOptions(String)-->"+ ex.getMessage());
		}
		return strData;
	}
	
	
}
