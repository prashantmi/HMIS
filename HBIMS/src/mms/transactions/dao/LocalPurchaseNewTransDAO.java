/**
 * 
 */
package mms.transactions.dao;

import java.util.Arrays;
import java.util.Set;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.SMSHttpsNICPostClient;

import javax.sql.rowset.WebRowSet;

// import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;
import billing.dao.PrimaryKeyDAO;
import mms.MmsConfigUtil;
import mms.transactions.vo.LocalPurchaseNewTransVO;

/**
 * @author user
 *
 */
public class LocalPurchaseNewTransDAO {
	
	
	public static void save(LocalPurchaseNewTransVO vo)
	{
		//String strProcName = "{call Pkg_Mms_dml.dml_hstt_lp_dtl(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,?)}";  //30 variables
		HisDAO daoObj=null;
		int funcIndex=0;
		String lpno;	
		String strProcName1 ="";
		int nProcIndex = 0 , nProcIndex1 = 0;
		String strErr = "";
		WebRowSet ws = null;
		
		MmsConfigUtil mcu;
		double salprice=0;
		//String ucNo;
		//String billtariff="",billrate="",billqty="";
	//	String item="";
		int i;
		//double netpo=0.0;
		//String[] hid=new String[vo.getStrSearchDrug().length];
		//String[] reqno=new String[1];
		//String[] storeid=new String[1];
		//String[] em=new String[1];
		try
		{		
			daoObj=new HisDAO("Local Purchase","LocalPurchaseNewTransDAO"); 
			funcIndex = daoObj.setFunction("{? = call MMS_MST.generate_lp_no(?,?,?,?)}");

			daoObj.setFuncInValue(funcIndex, 2, vo.getStrHospitalCode());
			daoObj.setFuncInValue(funcIndex, 3, vo.getStrStoreId());
			daoObj.setFuncInValue(funcIndex, 4, "22");
			daoObj.setFuncInValue(funcIndex, 5, vo.getStrItemCategoryNo());
			daoObj.setFuncOutValue(funcIndex, 1);

			daoObj.executeFunction_new(funcIndex);
			
			lpno = daoObj.getFuncString(funcIndex);
			vo.setStrLPNo(lpno);
			mcu=new MmsConfigUtil(vo.getStrHospitalCode());
			
			for(i=1;i<vo.getStrSearchDrug().length;i++)
			{
				String strProcName = "{call Pkg_Mms_dml.dml_hstt_lp_dtl(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?)}";  //30 variables
				nProcIndex = daoObj.setProcedure(strProcName);
				salprice = Double.parseDouble(vo.getStrCosttoPat()[i]);
				if(salprice >1000)
				{
					salprice = Double.parseDouble(vo.getStrPurRate()[i]);
				}
				
					if(vo.getStrItemCategoryNo().equals("10"))
						daoObj.setProcInValue(nProcIndex, "modval", "1",1);
					else
						daoObj.setProcInValue(nProcIndex, "modval", "2",1);
					daoObj.setProcInValue(nProcIndex, "store_id",vo.getStrStoreId(),2);
					daoObj.setProcInValue(nProcIndex, "item_id","0",3);
					daoObj.setProcInValue(nProcIndex, "itembrand_id",vo.getStrBrandId()[i],4);
					daoObj.setProcInValue(nProcIndex, "batchsl_no",vo.getStrbatchno()[i],5);
					daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),6);
					daoObj.setProcInValue(nProcIndex, "item_cat_code", vo.getStrItemCategoryNo(),7);
					daoObj.setProcInValue(nProcIndex, "group_id","0",8);
					daoObj.setProcInValue(nProcIndex, "subgroup_id","0",9);
					daoObj.setProcInValue(nProcIndex, "supplier_id",vo.getStrSupplier(),10);
					daoObj.setProcInValue(nProcIndex, "qty",vo.getStrTotalQty()[i],11);
					daoObj.setProcInValue(nProcIndex, "qty_unitid","0",12);
					daoObj.setProcInValue(nProcIndex, "expdate",vo.getStrExpDate()[i],13);
					daoObj.setProcInValue(nProcIndex, "mfgdate",vo.getStrMfgDate()[i],14);
					daoObj.setProcInValue(nProcIndex, "rate", vo.getStrPurRate()[i],15);
					daoObj.setProcInValue(nProcIndex, "rate_unitid","0",16);
					daoObj.setProcInValue(nProcIndex, "saleprice",Double.toString(salprice),17);
					daoObj.setProcInValue(nProcIndex, "saleprice_unitid","0",18);
					daoObj.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId(),19);
					daoObj.setProcInValue(nProcIndex, "invno", vo.getStrInvoiceNo(),20);
					daoObj.setProcInValue(nProcIndex, "invdate", vo.getStrInvoiceDate(),21); 
					daoObj.setProcInValue(nProcIndex, "remarks",(vo.getStrRemarks() == null || vo.getStrRemarks().equals(""))?"-":vo.getStrRemarks(),22); 
					daoObj.setProcInValue(nProcIndex, "dcno",vo.getStrDCNo(),23); 
					daoObj.setProcInValue(nProcIndex, "challan_date",vo.getStrChallanDate(),24); 
					daoObj.setProcInValue(nProcIndex, "adm_chg",vo.getStrAdmchg()[i],25); 
					daoObj.setProcInValue(nProcIndex, "tax",vo.getStrGST()[i],26); 
					if(vo.getStrItemCategoryNo().equals("10") || vo.getStrItemCategoryNo().equals("13"))
					{
						daoObj.setProcInValue(nProcIndex, "packsize",vo.getStrPackSize()[i],27); 
						daoObj.setProcInValue(nProcIndex, "packno",vo.getStrPackNo()[i],28); 
					}
					else
					{
						daoObj.setProcInValue(nProcIndex, "packsize","0",27); 
						daoObj.setProcInValue(nProcIndex, "packno","0",28); 
					}
					daoObj.setProcInValue(nProcIndex, "transno",lpno,29); 
					
					daoObj.setProcInValue(nProcIndex, "pono",vo.getStrLpoNo(),30); 
					daoObj.setProcInValue(nProcIndex, "strUcChk",vo.getStrUcChk(),31); 
					
					daoObj.setProcOutValue(nProcIndex, "err",1,32); 
					daoObj.execute(nProcIndex,1);
			
					if(vo.getStrItemCategoryNo().equals("10")){
					strProcName1 = "{call PKG_MMS_DML.dml_stock_update_new_lp(?,?,?,?,?,  ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,?, ?,?,?,?,? ,?)}"; // 24+1 Variable, 1 var added to save purchase rate

					nProcIndex1 = daoObj.setProcedure(strProcName1);
					
					daoObj.setProcInValue(nProcIndex1, "modval","1",1); // 1
					daoObj.setProcInValue(nProcIndex1, "strId",vo.getStrStoreId(),2); // 1
					daoObj.setProcInValue(nProcIndex1,"brandId",vo.getStrBrandId()[i],3);// 2
					daoObj.setProcInValue(nProcIndex1, "v_batchNo",vo.getStrbatchno()[i],4); // 3
					daoObj.setProcInValue(nProcIndex1, "expDate",vo.getStrExpDate()[i],5); // 4
					daoObj.setProcInValue(nProcIndex1, "mfgDate",vo.getStrMfgDate()[i],6); // 5
					daoObj.setProcInValue(nProcIndex1, "manufId",vo.getStrSupplier(),7); // 6
					daoObj.setProcInValue(nProcIndex1, "activeQty",vo.getStrTotalQty()[i],8); // 7
					daoObj.setProcInValue(nProcIndex1, "inactiveQty","0",9); // 8
					daoObj.setProcInValue(nProcIndex1, "quartineQty","0",10); // 9
					daoObj.setProcInValue(nProcIndex1, "seatId",	vo.getStrSeatId(),11); // 10
					daoObj.setProcInValue(nProcIndex1, "rate",vo.getStrCosttoPat()[i],12); // 12
					daoObj.setProcInValue(nProcIndex1, "rateUnitId",	"6303",13); // 13
					daoObj.setProcInValue(nProcIndex1, "tenderNo","0",14); // 15
					daoObj.setProcInValue(nProcIndex1, "poNo",lpno,15); // 16
					daoObj.setProcInValue(nProcIndex1, "hosp_code",vo.getStrHospitalCode(),16); // 17
					daoObj.setProcInValue(nProcIndex1, "supplierId",	vo.getStrSupplier(),17); // 18
					daoObj.setProcInValue(nProcIndex1, "prgid",	"0",18); // 17
					daoObj.setProcInValue(nProcIndex1, "existingbatch",	"0",19); // 18
					daoObj.setProcInValue(nProcIndex1, "existstockstatus", "10",20); // 19	
					daoObj.setProcInValue(nProcIndex1, "dcno", vo.getStrDCNo(),21);
					daoObj.setProcInValue(nProcIndex1, "invoiceno", vo.getStrInvoiceNo(),22);
					daoObj.setProcInValue(nProcIndex1, "invoicedate", vo.getStrInvoiceDate(),23);
					daoObj.setProcInValue(nProcIndex1, "purrate",vo.getStrPurRate()[i],24); // 23
					daoObj.setProcInValue(nProcIndex1, "handlingcharges",vo.getStrAdmchg()[i],25); // 11
					daoObj.setProcInValue(nProcIndex1, "purtax",vo.getStrGST()[i],26); // 11
					daoObj.setProcInValue(nProcIndex1, "flag","0",27); // 11  added to chk whether data inserted offline ie from inventory process.
					if(vo.getStrItemCategoryNo().equals("10"))
					{
					daoObj.setProcInValue(nProcIndex1, "packsize",vo.getStrPackSize()[i],28);
					daoObj.setProcInValue(nProcIndex1, "packno",vo.getStrPackNo()[i],29);
					}else{
						daoObj.setProcInValue(nProcIndex1, "packsize","0",28);
						daoObj.setProcInValue(nProcIndex1, "packno","0",29);
					}
					daoObj.setProcInValue(nProcIndex1, "invFlag","0",30);
					daoObj.setProcOutValue(nProcIndex1, "err", 1,31); // 18
					daoObj.execute(nProcIndex1, 1);
			}
					else{
						System.out.println("vo.getStrItemCategoryNo()"+vo.getStrItemCategoryNo());
						strProcName1 = "{call PKG_MMS_DML.dml_item_stock_update_new_lp(?,?,?,?,?,  ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,? ,?,?,?,?)}";
						nProcIndex1 = daoObj.setProcedure(strProcName1);
						daoObj.setProcInValue(nProcIndex1, "modval","1",1); // 1
						daoObj.setProcInValue(nProcIndex1, "strId",vo.getStrStoreId(),2); // 1
						daoObj.setProcInValue(nProcIndex1, "brandId",vo.getStrBrandId()[i],3);// 2
						daoObj.setProcInValue(nProcIndex1, "v_batchNo",vo.getStrbatchno()[i],4); // 3
						daoObj.setProcInValue(nProcIndex1, "expDate",vo.getStrExpDate()[i],5); // 4
						daoObj.setProcInValue(nProcIndex1, "mfgDate",vo.getStrMfgDate()[i],6); // 5
						daoObj.setProcInValue(nProcIndex1, "manufId",vo.getStrSupplier(),7); // 6
						daoObj.setProcInValue(nProcIndex1, "activeQty",vo.getStrTotalQty()[i],8); // 7
						daoObj.setProcInValue(nProcIndex1, "inactiveQty","0",9); // 8
						daoObj.setProcInValue(nProcIndex1, "quartineQty","0",10); // 9
						daoObj.setProcInValue(nProcIndex1, "seatId",	vo.getStrSeatId(),11); // 10
						daoObj.setProcInValue(nProcIndex1, "rate",vo.getStrCosttoPat()[i],12); // 11
						daoObj.setProcInValue(nProcIndex1, "rateUnitId",	"6303",13); // 12
						daoObj.setProcInValue(nProcIndex1, "tenderNo","0",14); // 13
						daoObj.setProcInValue(nProcIndex1, "poNo",lpno,15); // 14
						daoObj.setProcInValue(nProcIndex1, "hosp_code",vo.getStrHospitalCode(),16); // 15
						daoObj.setProcInValue(nProcIndex1, "supplierId",vo.getStrSupplier(),17); // 16
						daoObj.setProcInValue(nProcIndex1, "prgid","0",18); // 17
						daoObj.setProcInValue(nProcIndex1, "existingbatch",	"0",19); // 18
						daoObj.setProcInValue(nProcIndex1, "existstockstatus", "10",20); // 19
						daoObj.setProcInValue(nProcIndex1, "item_cat_no",vo.getStrItemCategoryNo() ,21); // 20
						daoObj.setProcInValue(nProcIndex1, "dcno", vo.getStrDCNo(),22);
						daoObj.setProcInValue(nProcIndex1, "invoiceno", vo.getStrInvoiceNo(),23);
						daoObj.setProcInValue(nProcIndex1, "invoicedate", vo.getStrInvoiceDate(),24);
						daoObj.setProcInValue(nProcIndex1, "purrate",vo.getStrPurWidTax()[i],25); // 11
						daoObj.setProcInValue(nProcIndex1, "handlingcharges",vo.getStrAdmchg()[i],26); // 11
						daoObj.setProcInValue(nProcIndex1, "tax",vo.getStrGST()[i],27); // 11
						daoObj.setProcInValue(nProcIndex1, "invFlag","0",28);
						daoObj.setProcOutValue(nProcIndex1, "err", 1,29);
						daoObj.execute(nProcIndex1, 1);
					}
					/* strProcName = "{call PKG_MMS_DML.Dml_Stock_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
					  
					daoObj.setProcInValue(nProcIndex, "modval", "1",1);
					daoObj.setProcInValue(nProcIndex, "strid", vo.getStrStoreId(),2);
					daoObj.setProcInValue(nProcIndex, "itemid", vo.getStrItemId()[i],3);
					daoObj.setProcInValue(nProcIndex, "itembrandid", vo.getStrSearchDrug()[i],4);
					daoObj.setProcInValue(nProcIndex, "batchno", vo.getStrbatchno()[i],5);
					//System.out.println("Catg==>"+vo.getStrHiddenVerifiedChallanValue()[i].split("\\$")[32]);
					daoObj.setProcInValue(nProcIndex, "itemcatno", vo.getStrItemCategoryNo(),6);
					daoObj.setProcInValue(nProcIndex, "groupid", "0",7);
					daoObj.setProcInValue(nProcIndex, "subgroupid", "0",8);
					daoObj.setProcInValue(nProcIndex, "expirydate", vo.getStrExpDate()[i],9);
					daoObj.setProcInValue(nProcIndex, "manufdate", vo.getStrMfgDate()[i],10);
					daoObj.setProcInValue(nProcIndex, "stockstatuscode", "10",11);
					daoObj.setProcInValue(nProcIndex, "inventoryflag", "0",12);
					daoObj.setProcInValue(nProcIndex, "inhandqty", vo.getStrTotalQty()[i],13);
					daoObj.setProcInValue(nProcIndex, "inhandqtyunitid", "6303",14);
					daoObj.setProcInValue(nProcIndex, "suppid", vo.getStrSupplier(),15); //vo.getStrManufacturerId()
					daoObj.setProcInValue(nProcIndex, "rate", vo.getStrPurRate()[i],16);
					daoObj.setProcInValue(nProcIndex, "rateunitid", "6303",17);
					daoObj.setProcInValue(nProcIndex, "saleprice", vo.getStrCosttoPat()[i],18);
					daoObj.setProcInValue(nProcIndex, "salepriceunitid", "6303",19);
					daoObj.setProcInValue(nProcIndex, "pono", lpno,20);
					daoObj.setProcInValue(nProcIndex, "podate", vo.getStrCtDate(),21);
					//System.out.println("Supplier Ide in Freeze==>"+vo.getStrHiddenVerifiedChallanValue()[i].split("\\$")[9]);
					daoObj.setProcInValue(nProcIndex, "suppliedBy", vo.getStrSupplier(),23);
					daoObj.setProcInValue(nProcIndex, "recievedDate", vo.getStrCtDate(),24);
					daoObj.setProcInValue(nProcIndex, "seatid", vo.getStrSeatId(),22);
					daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),27);
					daoObj.setProcInValue(nProcIndex, "description","Recieved through Local Purchase Desk against LPO No : " + lpno,42);
					daoObj.setProcInValue(nProcIndex, "currencyCode", "100",25);
					daoObj.setProcInValue(nProcIndex, "currencyValue", "1",28);
					daoObj.setProcInValue(nProcIndex, "freeItemFlag", "0",26);
					daoObj.setProcInValue(nProcIndex, "partFlag", "0",36);
					daoObj.setProcInValue(nProcIndex, "warrentyFlag", "0",37);
					daoObj.setProcInValue(nProcIndex, "itemParamFlag", "0",35);
					daoObj.setProcInValue(nProcIndex, "item_serialNoFlag", "0",51);
					daoObj.setProcInValue(nProcIndex, "old_stockstatuscode", "1",29);
					daoObj.setProcInValue(nProcIndex, "old_batchno", "0",30);
					daoObj.setProcInValue(nProcIndex, "old_itemSerialNo", "0",31);
					daoObj.setProcInValue(nProcIndex, "old_itemid", "0",32);
					daoObj.setProcInValue(nProcIndex, "old_itembrandid", "0",33);
					daoObj.setProcInValue(nProcIndex, "old_strid", "0",34);
					daoObj.setProcInValue(nProcIndex, "toStrId", "",38);
					daoObj.setProcInValue(nProcIndex, "reservedFlag", "0",39);
					daoObj.setProcInValue(nProcIndex, "transNo", lpno,40);
					daoObj.setProcInValue(nProcIndex, "transDate", vo.getStrCtDate(),41);
					daoObj.setProcInValue(nProcIndex, "reqTypeId", "22",43);
					daoObj.setProcInValue(nProcIndex, "blockQtyFlag", "0",44);
					daoObj.setProcInValue(nProcIndex, "blockedQty", "0",45);
					daoObj.setProcInValue(nProcIndex, "blockedQtyUnitId", "0",46);
					daoObj.setProcInValue(nProcIndex, "releaseQty", "0",47);
					daoObj.setProcInValue(nProcIndex, "releaseQtyUnitId", "6303",48);
					daoObj.setProcInValue(nProcIndex, "invoiceNo", vo.getStrInvoiceNo(),49);
					daoObj.setProcInValue(nProcIndex, "invoiceDate", vo.getStrInvoiceDate(),50);
					daoObj.setProcInValue(nProcIndex, "item_specification", "",52);
					
	
					daoObj.setProcOutValue(nProcIndex, "err", 1,54);
					daoObj.setProcOutValue(nProcIndex, "retSerialNo", 1,53);
	
					daoObj.execute(nProcIndex, 1);
					*/
			}		
			synchronized (daoObj) 
			{
				daoObj.fire();
			}
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			if (strErr.equals("")) {
				vo.setStrMsgType("0");
							}
							else {
								throw new Exception(strErr);
							}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("LocalPurchaseNewTransDAO.stockDetails() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
		
		
	}
	/**
	 * for getting  Supplier List on page from HSTT_Supplier_MST
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void getSupplierList(LocalPurchaseNewTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.proc_supplier_list(?,?,?,?,? ,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","LocalPurchaseNewTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "catcode", voObj.getStrReqTypeId(),2);
			daoObj.setProcInValue(nProcIndex, "branditem_id", "0",3);
			daoObj.setProcInValue(nProcIndex, "contracttype", "12",4);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrSupplierWs(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("LocalPurchaseNewTransDAO.getItemCatDtls() --> "
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
	 * for getting  Supplier List on page from HSTT_Supplier_MST
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void getItemList(LocalPurchaseNewTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.proc_itembrand_list(?,?,?,?,? ,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("MMS Transactions","LocalPurchaseNewTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "catcode", voObj.getStrReqTypeId(),2);
			daoObj.setProcInValue(nProcIndex, "branditem_id", "0",3);
			daoObj.setProcInValue(nProcIndex, "grpid", "0",4);
			daoObj.setProcInValue(nProcIndex, "subgrpid", "0",5);
			daoObj.setProcInValue(nProcIndex, "setflag", "0",6);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),7);
			daoObj.setProcOutValue(nProcIndex, "err", 1,8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setItemWS(ws);
							
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("LocalPurchaseNewTransDAO.getItemCatDtls() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	public static void getPrintDetails(LocalPurchaseNewTransVO vo) {
		
		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("mms", "LocalPurchaseNewTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.PROC_HSTT_LP_DTL(?,?,?,?,? ,?)}";
			nprocIndex = dao.setProcedure(strproc_name);			
			
			dao.setProcInValue(nprocIndex, "modval", "1",1);
			dao.setProcInValue(nprocIndex, "strId", vo.getStrStoreId(),2);
			dao.setProcInValue(nprocIndex, "lpno", vo.getStrLPNo(),3);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),4);	
			dao.setProcOutValue(nprocIndex, "err", 1,5);
			dao.setProcOutValue(nprocIndex, "resultset", 2,6);
			
			dao.executeProcedureByPosition(nprocIndex);
			
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "resultset");		
				vo.setWsPrint(ws);							
			} else {
				throw new Exception(strerr);
			}	

		} catch (Exception e) {
			vo.setStrMsgString("LocalPurchaseNewTransDAO.getPrintDetails() --> "
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
