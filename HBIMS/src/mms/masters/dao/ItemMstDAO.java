/**
 * 
 */
package mms.masters.dao;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import mms.MmsConfigUtil;
import mms.dao.ItemDAO;
import mms.masters.vo.ItemMstVO;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemMstDAO.
 * 
 * @author user
 */
public class ItemMstDAO {
	
	/**
	 * for getting option value of combo on add page (store type name ).
	 * 
	 * @param vo the vo
	 */

	public static void initialAddQuery(ItemMstVO vo) {

		HisDAO dao = null;
		int nqryIndex;
		WebRowSet wb = null;
		String strquery = "";
		try {
			dao = new HisDAO("mms", "ItemMstDAO");
			strquery = mms.qryHandler_mms.getQuery(1,
					"select.itemBrandMstType.0");// Query for Item Type Name
													// Combo
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrHospCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrItemCatNo());
			wb = dao.executeQry(nqryIndex);
			vo.setItemTypeWS(wb);

			strquery = mms.qryHandler_mms.getQuery(1,
					"select.itemBrandMstManuf.0");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrHospCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrItemCatNo());
			wb = dao.executeQry(nqryIndex);
			vo.setManufacturerWS(wb);

			strquery = mms.qryHandler_mms.getQuery(1,"select.itemBrandMstRate.1");// Query For Unit Id
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrInventoryUnitId());
			wb = dao.executeQry(nqryIndex);
			
			if(wb != null && wb.next()){
				
				vo.setStrRateUnitId(wb.getString(1));
				vo.setStrRateUnitName(wb.getString(2));
				
			}
			
			
			strquery = mms.qryHandler_mms.getQuery(1,
			"select.itemBrandMstGenItem.1");// Query for Generic Item Name Combo
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrItemCatNo());
			dao.setQryValue(nqryIndex, 2, vo.getStrGroupId().equalsIgnoreCase("")?"0":vo.getStrGroupId());
			dao.setQryValue(nqryIndex, 3, vo.getStrHospCode());
			wb = dao.executeQry(nqryIndex);
			
			vo.setWsGenericItems(wb);
			

		} catch (Exception e) {
			vo.setStrMsgString("ItemMstDAO.initialAddQuery()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null)
				dao.free();
			dao = null;
		}

	}

	/**
	 * Check the Record weather data is Duplicate or not.
	 * 
	 * @param vo -
	 * FormBean Object
	 * 
	 * @return - true -record cannot be saved ,already exist false - record will
	 * save
	 * 
	 * @throws Exception 	 */
	public static void chkDuplicate(ItemMstVO vo) {
		HisDAO dao = null;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = new String();

		try {

			dao = new HisDAO("mms", "ItemMstDAO");
			strquery = mms.qryHandler_mms.getQuery(1, "select.itemBrandMst.2");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrItemName());
			dao.setQryValue(nqryIndex, 2, vo.getStrItemCatNo());
			dao.setQryValue(nqryIndex, 3, vo.getStrHospCode());

			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount == 0) {
				vo.setBExistStatus(true);
			} else {
				vo.setBExistStatus(false);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ItemMstDAO.chkDuplicate() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	/**
	 * to insert the data.
	 * 
	 * @param vo the vo
	 */
	public static void insertQuery(ItemMstVO vo) {
		HisDAO dao = null;
		ItemDAO itemDao = null;
		String strQuery1 = "";
		int nQueryIndex1=0;
		WebRowSet web1 = null;
		String strTariffId = "";
		String strItemId="";

		try {
			itemDao = new ItemDAO();
			dao = new HisDAO("mms", "ItemMstDAO");
			MmsConfigUtil mmsConfigUtil=new MmsConfigUtil(vo.getStrHospiCode());
			
			strQuery1 = mms.qryHandler_mms.getQuery(1, "select.brandItem.1");
			nQueryIndex1 = dao.setQuery(strQuery1);
			dao.setQryValue(nQueryIndex1, 1,(vo.getStrHospCode()));
			dao.setQryValue(nQueryIndex1, 2, vo.getStrItemCatNo()); // /ITEM CATEGORY FOR item
			web1 = dao.executeQry(nQueryIndex1);
			if (web1.next()) {
				strItemId = web1.getString(1);
			}
			
			itemDao.setStrItemBrandId(strItemId);
			itemDao.setStrHospitalCode(vo.getStrHospCode());
			itemDao.setStrItemCatNO(vo.getStrItemCatNo());
			itemDao.setStrGenItemId(vo.getStrGenItemId().replace("^", "#").split("#")[0]);
			itemDao.setStrItemTypeId(vo.getStrItemTypeId().equalsIgnoreCase("")?"0":vo.getStrItemTypeId());
			itemDao.setStrItemName(vo.getStrItemName());
			itemDao.setStrManufacturerId(vo.getStrManufacturerId());
			itemDao.setStrDefaultRate(vo.getStrDefaultRate().equalsIgnoreCase("")?"0":vo.getStrDefaultRate());
			itemDao.setStrRateUnitId(vo.getStrRateUnitId());
			itemDao.setStrApprovedType(vo.getStrApprovedType());
			itemDao.setStrIssueType(vo.getStrIssueType());
			itemDao.setStrItemMake(vo.getStrItemMake());
			itemDao.setStrSparePartFlag(vo.getStrSparePartFlag().equalsIgnoreCase("")?"0":vo.getStrSparePartFlag());
			itemDao.setStrSetSachetFlag(vo.getStrSetSachetFlag().equalsIgnoreCase("")?"0":vo.getStrSetSachetFlag());
			itemDao.setStrIsQuantified(vo.getStrIsQuantified().equalsIgnoreCase("")?"0":vo.getStrIsQuantified());

			itemDao.setStrSeatId(vo.getStrSeatId());
			itemDao.setStrIsValid("1");
			itemDao.setStrSpecification(vo.getStrSpecification());
			itemDao.setStrEffectiveFrom(vo.getStrEffectiveFrom());
			itemDao.setStrItemReservedFlag(vo.getStrItemType());
			itemDao.setStrItemCode(vo.getStrCPACode());
			
			itemDao.setStrItemClass("0");
			itemDao.setStrBatchnoReq(vo.getStrBatchNo());
			itemDao.setStrExpiryDateReq(vo.getStrExpiryDate());
			itemDao.setStrConsumableType(vo.getStrConsumableType());
			itemDao.setStrManufDate(vo.getStrManufDate());
			itemDao.setStrIsMisc(vo.getStrIsMisc());
			
			if(vo.getStrUploadFlag().equals("1"))
			{
				itemDao.setStrUploadFileId(vo.getStrUploadFileId());
				itemDao.setStrUploadFileName(vo.getStrUploadFileName());
			}	
			else
			{	
				itemDao.setStrUploadFileId("0");
				itemDao.setStrUploadFileName("");
			}	
			if(vo.getStrSterilizationFlag().equals("1"))
			{	
				itemDao.setStrSterilizationFlag(vo.getStrSterilizationFlag());
				itemDao.setStrSterilizationLife(vo.getStrSterilizationLife());
			}
			else
			{
				itemDao.setStrSterilizationFlag(vo.getStrSterilizationFlag());
				itemDao.setStrSterilizationLife("0");
			}	
			itemDao.setStrHSNCode(vo.getStrHSNCode());
			itemDao.insert(dao);
			
			/*strQuery1 = mms.qryHandler_mms.getQuery(1, "select.drug.tariff");//107  is for both items and drugs 
			nQueryIndex1 = dao.setQuery(strQuery1);
			web1 = dao.executeQry(nQueryIndex1);
			if (web1.next()) {
				strTariffId = web1.getString(1);
				System.out.println("strTariffId"+strTariffId);
			}
			//itemDao.setStrItemBrandId(strItemId);
			itemDao.setStrTariffId(strTariffId);
			if(mmsConfigUtil.getStrBillingIntegration().equals("1"))
				itemDao.insert2(dao);*/

			synchronized (dao) {
				dao.fire();

			}
		} catch (Exception e) {

			vo.setStrMsgString("ItemSetsMstDAO.insertQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

			itemDao = null;

		}

	}

	/**
	 * retrieves and executes modify Query.
	 * 
	 * @param vo the vo
	 * 
	 * @throws Exception 	 */
	public static void modifyQuery(ItemMstVO vo) {

		HisDAO dao = new HisDAO("mms", "ItemMstDAO");

		int nqryIndex;
		String strquery = new String();

		try {

			strquery = mms.qryHandler_mms.getQuery(1, "select.itemBrandMst.3");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrItemBrandId());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospCode());
			dao.setQryValue(nqryIndex, 3, vo.getStrSerialNo());

			WebRowSet web = dao.executeQry(nqryIndex);
			if (web.next()) {

				vo.setStrItemCatNo(web.getString(1));
				vo.setStrGenItemId(web.getString(2));
				vo.setStrItemTypeId(web.getString(3));
				vo.setStrItemName(web.getString(4));
				vo.setStrManufacturerId(web.getString(5));
				vo.setStrDefaultRate(web.getString(6));

				vo.setStrRateUnitId(web.getString(7));
				vo.setStrInventoryUnitId(web.getString(8));
				vo.setStrApprovedType(web.getString(9));
				vo.setStrIssueType(web.getString(10));
				vo.setStrItemMake(web.getString(11));
				vo.setStrSparePartFlag(web.getString(12));
				vo.setStrSetSachetFlag(web.getString(13));
				vo.setStrIsQuantified(web.getString(14));
				vo.setStrSpecification(web.getString(15));
				vo.setStrEffectiveFrom(web.getString(16));
				vo.setStrIsValid(web.getString(17));
				vo.setStrNewCPACode(web.getString(18));
				vo.setStrGenItemName(web.getString(19));
				vo.setStrBrandReserveFlag(web.getString(20));
                vo.setStrSterilizationFlag(web.getString(21));
                vo.setStrSterilizationLife(web.getString(22));
                vo.setStrUploadFileId(web.getString(23));
                vo.setStrUploadFileName(web.getString(24));
                
                vo.setStrItemClass(web.getString(25));
                vo.setStrBatchNo(web.getString(26));
                vo.setStrExpiryDate(web.getString(27));
                vo.setStrConsumableType(web.getString(28));
                vo.setStrHSNCode(web.getString(29));
                vo.setStrManufDate(web.getString(30));
                vo.setStrIsMisc(web.getString(31));
                
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo
					.setStrMsgString("ItemMstDAO.modifyQuery() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	/**
	 * Check the Record weather data is Duplicate or not.
	 * 
	 * @param vo -
	 * FormBean Object
	 * 
	 * @return - true -record cannot be saved ,already exist false - record will
	 * save
	 * 
	 * @throws Exception 	 */
	public static void chkUpdateDuplicate(ItemMstVO vo) {
		HisDAO dao = null;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = new String();

		try {

			dao = new HisDAO("mms", "ItemMstDAO");
			strquery = mms.qryHandler_mms.getQuery(1, "select.itemBrandMst.4");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrItemName());
			dao.setQryValue(nqryIndex, 2, vo.getStrItemCatNo());
			dao.setQryValue(nqryIndex, 3, vo.getStrItemBrandId());
			dao.setQryValue(nqryIndex, 4, vo.getStrHospCode());

			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount == 0) {
				vo.setBExistStatus(false);
			} else {
				vo.setBExistStatus(true);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ItemMstDAO.chkUpdateDuplicate() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	/**
	 * retrieves and executes update Query.
	 * 
	 * @param vo the vo
	 */
	public static void updateQuery(ItemMstVO vo) {

		HisDAO dao = null;
		String strQuery1 = "";
		ItemDAO itemDao = null;
		int nQueryIndex1=0;
		WebRowSet web1 = null;
		String strTariffId = "";
		try {

			itemDao = new ItemDAO();
			dao = new HisDAO("mms", "ItemMstDAO");
			
			//itemDao.setStrSeatId(vo.getStrSeatId());					
			itemDao.setStrItemBrandId(vo.getStrItemBrandId());
			itemDao.setStrHospitalCode(vo.getStrHospCode());
			itemDao.setStrSerialNo(vo.getStrSerialNo());
			itemDao.update1(dao);	
			
			strQuery1 = mms.qryHandler_mms.getQuery(1, "select.drug.tariff");
			nQueryIndex1 = dao.setQuery(strQuery1);
			web1 = dao.executeQry(nQueryIndex1);
			if (web1.next()) {
				strTariffId = web1.getString(1);
				System.out.println("strTariffId"+strTariffId);
			}
			/************************************************************************/
			itemDao.setStrTariffId(strTariffId);
			/*****************************************************************/
			itemDao.setStrHospitalCode(vo.getStrHospCode());
			itemDao.setStrItemCatNO(vo.getStrItemCatNo());
			itemDao.setStrGenItemId(vo.getStrGenItemId().replace("^", "#").split("#")[0]);
			itemDao.setStrItemTypeId(vo.getStrItemTypeId().equalsIgnoreCase("")?"0":vo.getStrItemTypeId());
			itemDao.setStrItemName(vo.getStrItemName());
			itemDao.setStrManufacturerId(vo.getStrManufacturerId());
			itemDao.setStrDefaultRate(vo.getStrDefaultRate().equalsIgnoreCase("")?"0":vo.getStrDefaultRate());
			itemDao.setStrRateUnitId(vo.getStrRateUnitId());
			itemDao.setStrApprovedType(vo.getStrApprovedType());
			itemDao.setStrIssueType(vo.getStrIssueType());
			itemDao.setStrItemMake(vo.getStrItemMake());
			itemDao.setStrSparePartFlag(vo.getStrSparePartFlag().equalsIgnoreCase("")?"0":vo.getStrSparePartFlag());
			itemDao.setStrSetSachetFlag(vo.getStrSetSachetFlag().equalsIgnoreCase("")?"0":vo.getStrSetSachetFlag());
			itemDao.setStrIsQuantified(vo.getStrIsQuantified().equalsIgnoreCase("")?"0":vo.getStrIsQuantified());
			itemDao.setStrIsMisc(vo.getStrIsMisc().equalsIgnoreCase("")?"0":vo.getStrIsMisc());
			itemDao.setStrSeatId(vo.getStrSeatId());
			itemDao.setStrIsValid("1");
			itemDao.setStrSpecification(vo.getStrSpecification());
			itemDao.setStrEffectiveFrom(vo.getStrEffectiveFrom());
			itemDao.setStrItemReservedFlag(vo.getStrBrandReserveFlag());
			itemDao.setStrItemCode(vo.getStrCPACode());
			
			itemDao.setStrConsumableType(vo.getStrConsumableType());
			itemDao.setStrBatchnoReq(vo.getStrBatchNo().equalsIgnoreCase("")?"0":vo.getStrBatchNo());
			itemDao.setStrExpiryDateReq(vo.getStrExpiryDate().equalsIgnoreCase("")?"0":vo.getStrExpiryDate());
			itemDao.setStrItemClass("0");
			itemDao.setStrManufDate(vo.getStrManufDate());
			if(vo.getStrUploadFlag().equals("1"))
			{
				itemDao.setStrUploadFileId(vo.getStrUploadFileId());
				itemDao.setStrUploadFileName(vo.getStrUploadFileName());
			}	
			else
			{	
				itemDao.setStrUploadFileId("");
				itemDao.setStrUploadFileName("");
			}	
			if(vo.getStrSterilizationFlag().equals("1"))
			{	
				itemDao.setStrSterilizationFlag(vo.getStrSterilizationFlag());
				itemDao.setStrSterilizationLife(vo.getStrSterilizationLife().equalsIgnoreCase("")?"0":vo.getStrSterilizationLife());
			}
			else
			{
				itemDao.setStrSterilizationFlag(vo.getStrSterilizationFlag());
				itemDao.setStrSterilizationLife("0");
			}	
		//	itemDao.update2(dao);
			itemDao.insert1(dao);
			//itemDao.insert3(dao);

			synchronized (dao) 
			{
				dao.fire();
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
			vo
					.setStrMsgString("ItemMstDAO.updateQuery() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * View.
	 * 
	 * @param vo the vo
	 */
	public static void view(ItemMstVO vo) {
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {

			dao = new HisDAO("MMS", "ItemMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "view.itemBrandMst.0");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrItemBrandId());
			dao.setQryValue(nQueryIndex, 2, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex, 3, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex, 4, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex, 5, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex, 6, vo.getStrSerialNo());
			web = dao.executeQry(nQueryIndex);
			if (web.next()) {
				vo.setStrItemTypeName(web.getString(1));
				vo.setStrItemName(web.getString(2));
				vo.setStrDefaultRate(web.getString(3));
				vo.setStrRateUnitName(web.getString(4));
				// vo.setStrInventoryUnitId(web.getString(5));
				vo.setStrManufacturerName(web.getString(5));
				vo.setStrApprovedTypeName(web.getString(6));

				vo.setStrIssueType(web.getString(7));
				vo.setStrItemMake(web.getString(8));
				vo.setStrSparePartFlag(web.getString(9));
				vo.setStrSetSachetFlag(web.getString(10));
				vo.setStrIsQuantified(web.getString(11));
				vo.setStrSpecification(web.getString(12));
				vo.setStrEffectiveFrom(web.getString(13));
				vo.setStrIsValid(web.getString(14));
				vo.setStrGenItemName(web.getString(15));
				vo.setStrGroupName(web.getString(16));
				vo.setStrItemCatName(web.getString(17));
				vo.setStrBrandReserveFlag(web.getString(18));
				vo.setStrCPACode(web.getString(19));

			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ItemMstDAO.view() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;
		}
	}

	/**
	 * Gets the Flag Item Code Required 
	 * 
	 * @param vo the vo
	 * 
	 * @return the Flag Item Code Required 
	 */
	public static void getItemCodeRequired(ItemMstVO vo) {
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("MMS", "GenericDrugMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "select.itemCatDtls.0");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrItemCatNo());
			web = dao.executeQry(nQueryIndex);
			
			if(web != null && web.next()){
				
				vo.setStrIsItemCodeRequired(web.getString(1));
				
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
			vo
					.setStrMsgString("ItemMstDAO.getItemCodeRequired() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;
		}
	}

	public static void setGenericItemCode(ItemMstVO vo) {
		HisDAO dao = null;
		String strQuery ;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("MMS", "ItemMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "select.itembrand.genericitemCode.0");
			nQueryIndex = dao.setQuery(strQuery);
			
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrItemBrandId());
			dao.setQryValue(nQueryIndex, 3, vo.getStrHospCode());
			
			
			
			web = dao.executeQry(nQueryIndex);

			if (web.next()) {

				vo.setStrGenericItemCode(web.getString(1));

			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ItemMstDAO.setGenericDrugCode() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if(dao != null) {
				dao.free();
			}
			dao = null;
			web = null;
		}
		
	}
	
	public static void setApprovedType(ItemMstVO vo) {
		HisDAO dao = null;
		String strQuery ;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			
			dao = new HisDAO("MMS", "ItemMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "select.itembrand.approvedType");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			web = dao.executeQry(nQueryIndex);
			vo.setWrsApprovedTypeOptions(web);
			
		} catch (Exception e) {
			
			vo.setStrMsgString("ItemMstDAO.setApprovedType() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if(dao != null) {
				dao.free();
			}
			dao = null;
			web = null;
		}
		
	}
	
	public static void consumeCombo(ItemMstVO vo) {
		
		HisDAO dao = null;
		String strQuery ;
		int nQueryIndex = 0;
		WebRowSet web = null;
        try {
			
			dao = new HisDAO("MMS", "ItemMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "select.itembrand.ConsumeType");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrItemBrandId());
			dao.setQryValue(nQueryIndex, 2, vo.getStrInventoryUnitId());
			dao.setQryValue(nQueryIndex, 3, vo.getStrGenericItem());
			web = dao.executeQry(nQueryIndex);

			if (web.next()) {
				vo.setStrConsumableType(web.getString(1));
			}
			
		} catch (Exception e) {
			
			vo.setStrMsgString("ItemMstDAO.setConsumeComboValue() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if(dao != null) {
				dao.free();
			}
			dao = null;
			web = null;
		}
	}

	
}
