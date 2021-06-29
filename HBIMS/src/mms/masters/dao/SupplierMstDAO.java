package mms.masters.dao;

import javax.sql.rowset.WebRowSet;

import mms.dao.BlackListSupplierDtlDAO;
import mms.dao.SupplierDAO;
import mms.masters.vo.SupplierMstVO;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class SupplierMstDAO.
 */
public class SupplierMstDAO {

	/**
	 * This function is used to initialize the initial parameters.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void getInitParam(SupplierMstVO vo) {
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("mms", "SupplierMstDAO");

			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.2");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrItemCatNo());
			web = dao.executeQry(nQueryIndex);

			if (web.next()) 
			{
				vo.setStrItemCategoryName(web.getString(1));
			}

			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.3");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			web = dao.executeQry(nQueryIndex);
			vo.setSupplierGradeWS(web);

			// // System.out.println("store type id--"+vo.getStrStoreTypeId());

			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.4");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			// dao.setQryValue(nQueryIndex, 2, vo.getStrItemCatNo());
			web = null;
			web = dao.executeQry(nQueryIndex);
			vo.setCommiteeNameWS(web);
System.out.println("hosp code::: "+vo.getStrHospCode());
			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplierType.1");
			nQueryIndex = dao.setQuery(strQuery);
		//	dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			web = null;
			web = dao.executeQry(nQueryIndex);
			vo.setWsSupplierType(web);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("SupplierMstDAO.getInitParam() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			web = null;
		}
	}

	/**
	 * This function is used to initialize the initial parameters.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void getCountry(SupplierMstVO vo) {
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("mms", "SupplierMstDAO");

			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.11");
			nQueryIndex = dao.setQuery(strQuery);
		//	dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			web = null;
			web = dao.executeQry(nQueryIndex);
			vo.setCountryNameWS(web);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("SupplierMstDAO.getInitParam() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			web = null;
		}
	}

	/**
	 * This function is used to initialize the initial parameters.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void getState(SupplierMstVO vo) {
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("mms", "SupplierMstDAO");

			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.12");
			nQueryIndex = dao.setQuery(strQuery);
		//	dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			dao.setQryValue(nQueryIndex, 1, vo.getStrCountryCode());
			web = null;
			web = dao.executeQry(nQueryIndex);
			vo.setStateNameWS(web);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("SupplierMstDAO.getInitParam() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			web = null;
		}
	}

	/**
	 * This function is used to insert the data into database.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void insert(SupplierMstVO vo) 
	{
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		int j=0;
		WebRowSet web = null;
		SupplierDAO supplierDAO = null;
		BlackListSupplierDtlDAO blackListSupp = null;
		String strSupplierId = "";
		
		try 
		{
			supplierDAO = new SupplierDAO();
			blackListSupp = new BlackListSupplierDtlDAO();

			dao = new HisDAO("MMS", "SupplierMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.5");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrItemCatNo());

			web = dao.executeQry(nQueryIndex);
			if (web.next()) 
			{
				strSupplierId = web.getString(1);

			}
            
			supplierDAO.setStrAddress(vo.getStrAddress());
			supplierDAO.setStrCityName(vo.getStrCity());
			supplierDAO.setStrContactPerson(vo.getStrContactPerson());
			supplierDAO.setStrEffectiveFrom(vo.getStrEffectiveFrom());
			supplierDAO.setStrEmail1(vo.getStrEmailId1());
			supplierDAO.setStrEmail2(vo.getStrEmailId2());
			supplierDAO.setStrFaxNo1(vo.getStrFaxNo1());
			supplierDAO.setStrFaxNo2(vo.getStrFaxNo2());
			supplierDAO.setStrHospitalCode(vo.getStrHospCode());
			supplierDAO.setStrIsValid("1");
			supplierDAO.setStrPhone1(vo.getStrPhoneNo1());
			supplierDAO.setStrPhone2(vo.getStrPhoneNo2());
			supplierDAO.setStrPinCode(vo.getStrPincode());
			supplierDAO.setStrRemarks(vo.getStrRemarks());
			supplierDAO.setStrSeatId(vo.getStrSeatId());
			supplierDAO.setStrStoreTypeId(vo.getStrItemCatNo());
			supplierDAO.setStrSupplierGradeId(vo.getStrSupplierGrade());
			supplierDAO.setStrSupplierName(vo.getStrSupplierName());
			supplierDAO.setStrSupplierStatus(vo.getStrSupplierStatus());
			supplierDAO.setStrSupplierType(vo.getStrSupplierType());
			supplierDAO.setStrWebSite(vo.getStrWebsite());
			supplierDAO.setStrSupplierId(strSupplierId);
			supplierDAO.setStrForeignerSuppFlag(vo.getStrForeignerSuppFlag());
			supplierDAO.setStrIsSupplier(vo.getStrIsSupplier());
			supplierDAO.setStrIsManufacturer(vo.getStrIsManufacturer());
			supplierDAO.setStrIsAgent(vo.getStrIsAgent());
			supplierDAO.setStrIsBuyer(vo.getStrIsBuyer());
			supplierDAO.setStrLstNo(vo.getStrLstNo());
			supplierDAO.setStrCstNo(vo.getStrCstNo());
			supplierDAO.setStrPANNo(vo.getStrPANNo());
			supplierDAO.setStrSupplierType(vo.getStrSupplierType());
			supplierDAO.setStrCountryCode(vo.getStrCountryCode());
			supplierDAO.setStrStateCode(vo.getStrStateCode());

			supplierDAO.setStrContractNo(vo.getStrContractNo());
			supplierDAO.setStrContractDate(vo.getStrContractDate());
			supplierDAO.setStrExpiryDate(vo.getStrExpiryDate());
			supplierDAO.setStrSuppTurnOver(vo.getStrSuppTurnOver());
			supplierDAO.setStrSuppTurnOverUnit(vo.getStrSuppTurnOverUnit());
			supplierDAO.setStrSupplierProvMaintenance(vo.getStrSupplierProvMaintenance());
			supplierDAO.setStrEsclationMtxAvl(vo.getStrEsclationMtxAvl());
			
            supplierDAO.insert(dao);
      
            if(vo.getStrLevelOneEsc().equals("1") && vo.getStrLevelTwoEsc().equals("1"))
			{
				for(int i=0;i<2;i++)
				{
				    j++;	
					blackListSupp.setStrSupplierId(strSupplierId);
					blackListSupp.setStrEscLevel(String.valueOf(j));
					blackListSupp.setStrHospitalCode(vo.getStrHospCode());
					blackListSupp.setStrSupplierId(strSupplierId);
					blackListSupp.setStrHospitalCode(vo.getStrHospCode());
					blackListSupp.setStrCotactPersonForEsc(vo.getStrCotactPersonForEsc()[i]);
					blackListSupp.setStrContactPersonDesgForEsc(vo.getStrContactPersonDesgForEsc()[i]);
					blackListSupp.setStrCotactEmailIdForEsc(vo.getStrCotactEmailIdForEsc()[i]);
					blackListSupp.setStrCotactNoForEsc(vo.getStrCotactNoForEsc()[i]);
					blackListSupp.setStrCotactFaxForEsc(vo.getStrCotactFaxForEsc()[i]);
										
					blackListSupp.insertSupplierEsc(dao);					
				}				
			}
			else
			{
				if(vo.getStrLevelOneEsc().equals("1"))
				{
					for(int i=0;i<1;i++)
					{						
						blackListSupp.setStrSupplierId(strSupplierId);
						blackListSupp.setStrEscLevel(String.valueOf(j));
						blackListSupp.setStrHospitalCode(vo.getStrHospCode());
						blackListSupp.setStrSupplierId(strSupplierId);
						blackListSupp.setStrHospitalCode(vo.getStrHospCode());
						blackListSupp.setStrCotactPersonForEsc(vo.getStrCotactPersonForEsc()[i]);
						blackListSupp.setStrContactPersonDesgForEsc(vo.getStrContactPersonDesgForEsc()[i]);
						blackListSupp.setStrCotactEmailIdForEsc(vo.getStrCotactEmailIdForEsc()[i]);
						blackListSupp.setStrCotactNoForEsc(vo.getStrCotactNoForEsc()[i]);
						blackListSupp.setStrCotactFaxForEsc(vo.getStrCotactFaxForEsc()[i]);
										
						blackListSupp.insertSupplierEsc(dao);
					}										
				}				
			}
            
			if (vo.getStrSupplierStatus().equals("2")) 
			{
				blackListSupp.setStrActionDate(vo.getStrActionDate());
				blackListSupp.setStrCommitteeNo(vo.getStrCommitee());
				blackListSupp.setStrHospitalCode(vo.getStrHospCode());
				blackListSupp.setStrIsBlackListFlag("1");
				blackListSupp.setStrRemarks(vo.getStrBlackListedRemarks());
				blackListSupp.setStrSeatId(vo.getStrSeatId());
				blackListSupp.setStrIsValid("1");
				blackListSupp.setStrSupplierId(strSupplierId);
				
				blackListSupp.insert(dao);
			}			
			synchronized (dao) 
			{
				dao.fire();
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error:::  "+e.getMessage());
            vo.setStrMsgString("SupplierMstDAO.insert() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
			supplierDAO = null;
			blackListSupp = null;
			web = null;
		}
	}

	/**
	 * This function is used set initial parameters for modify page.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void modify(SupplierMstVO vo) {
		HisDAO dao = null;
		String strQuery = "";
		String strQuery1 = "";
		int nQueryIndex = 0;
		int nQueryIndex1 = 0;
		WebRowSet web = null;
		WebRowSet web1 = null;
		int j=0;
		
		try {

			dao = new HisDAO("mms", "SupplierMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.6");

			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrSupplierId());
			dao.setQryValue(nQueryIndex, 2, vo.getStrHospCode());
			dao.setQryValue(nQueryIndex, 3, vo.getStrSlNo());
			
			web = dao.executeQry(nQueryIndex);
			if (web.next()) 
			{

				vo.setStrSupplierName(web.getString(1));
				vo.setStrItemCatNo(web.getString(2));
				vo.setStrContactPerson(web.getString(3));
				vo.setStrAddress(web.getString(4));
				vo.setStrCity(web.getString(5));
				vo.setStrPincode(web.getString(6));
				vo.setStrPhoneNo1(web.getString(7));
				vo.setStrPhoneNo2(web.getString(8));
				vo.setStrEmailId1(web.getString(9));
				vo.setStrEmailId2(web.getString(10));
				vo.setStrFaxNo1(web.getString(11));
				vo.setStrFaxNo2(web.getString(12));
				vo.setStrWebsite(web.getString(13));
				vo.setStrSupplierStatus(web.getString(14));

				vo.setStrRemarks(web.getString(15));
				vo.setStrEffectiveFrom(web.getString(16));
				vo.setStrIsValid(web.getString(17));
				vo.setStrSupplierGradeCode(web.getString(18));
				vo.setStrItemCatNo(web.getString(19));
				// vo.setStrLocalPurchaseSuppFlag(web.getString(25));
				if(web.getString(20).equalsIgnoreCase("0"))
				{
					vo.setStrLstNo("");
				}
				else
				{
				vo.setStrLstNo(web.getString(20));
				}
				if(web.getString(21).equalsIgnoreCase("0"))
				{
					vo.setStrCstNo("");
				}
				else
				{
				vo.setStrCstNo(web.getString(21));
				}
				if(web.getString(22).equalsIgnoreCase("0"))
				{
					vo.setStrPANNo("");
				}
				else
				{
				vo.setStrPANNo(web.getString(22));
				}
				//vo.setStrCstNo(web.getString(21).equalsIgnoreCase("0")?null:web.getString(21));
				//vo.setStrPANNo(web.getString(22).equalsIgnoreCase("0")?null:web.getString(22));
				vo.setStrIsSupplier(web.getString(23));
				vo.setStrIsManufacturer(web.getString(24));
				vo.setStrIsAgent(web.getString(25));
				vo.setStrIsBuyer(web.getString(26));
				vo.setStrForeignerSuppFlag(web.getString(27));
				vo.setStrSupplierTypeCode(web.getString(28));
				vo.setStrCountryCode(web.getString(29));
				vo.setStrStateCode(web.getString(30));
				vo.setStrSuppTurnOver(web.getString("HSTNUM_TURNOVER"));
				//System.out.println("Turn Over Unit::::::"+web.getString(37));
				vo.setStrSuppTurnOverUnit(web.getString(37));
	            vo.setStrSupplierProvMaintenance(web.getString("HSTNUM_MAINTANANCE_FLAG"));
	            vo.setStrEsclationMtxAvl(web.getString("HSTNUM_ESCALANATION_FLAG"));
	           	/* Aritra */
				vo.setStrContractNo(web.getString("HSTSTR_CONTRACT_NO"));
				vo.setStrContractDate(web.getString("HSTDT_CONTRACT_DATE"));
				vo.setStrExpiryDate(web.getString("HSTDT_CONTRACT_EXPIRY_DATE"));
				vo.setStrCountryCode(web.getString("GNUM_COUNTRYCODE"));//GNUM_STATECODE
				vo.setStrStateCode(web.getString("GNUM_STATECODE"));
				// // System.out.println("modify
				// getStrLocalPurchaseSuppFlag"+vo.getStrLocalPurchaseSuppFlag());
				if (vo.getStrSupplierStatus().equals("2")) 
				{
					strQuery = mms.qryHandler_mms.getQuery(1,"select.supplier.7");
					nQueryIndex = dao.setQuery(strQuery);
					dao.setQryValue(nQueryIndex, 1, vo.getStrSupplierId());
					dao.setQryValue(nQueryIndex, 2, vo.getStrHospCode());
					dao.setQryValue(nQueryIndex, 3, vo.getStrSupplierId());
					dao.setQryValue(nQueryIndex, 4, vo.getStrHospCode());
					web = dao.executeQry(nQueryIndex);
					if (web.next()) {
						vo.setStrActionDate(web.getString(1));
						vo.setStrBlackListedRemarks(web.getString(2));
						vo.setStrCommitteCode(web.getString(3));
					}

					vo.setStrIsBlackListMod("1");
				} else {
					vo.setStrIsBlackListMod("0");
				}
			}
			
			strQuery1 = mms.qryHandler_mms.getQuery(1,"select.supplier.88");
			nQueryIndex1 = dao.setQuery(strQuery1);
			dao.setQryValue(nQueryIndex1, 1, vo.getStrSupplierId());
			dao.setQryValue(nQueryIndex1, 2, vo.getStrHospCode());
		    
					if (vo.getStrEsclationMtxAvl().equals("1")) 
					{				
						web1 = dao.executeQry(nQueryIndex1);
						
						String arr1[] = new String[web1.size()];
						String arr2[] = new String[web1.size()];
						String arr3[] = new String[web1.size()];
						String arr4[] = new String[web1.size()];
						String arr5[] = new String[web1.size()];
						String arr6[] = new String[web1.size()];
						
		                for(int k =0;web1.next();k++) 
						{		                	
							arr1[k] = web1.getString(1);
							arr2[k] = web1.getString(2);
							arr3[k] = web1.getString(3);
							arr4[k] = web1.getString(4);
							arr5[k] = web1.getString(5);
							arr6[k] = web1.getString(6);
							j++;
							
						}
		                vo.setStrCotactPersonForEsc(arr2);
		
						vo.setStrContactPersonDesgForEsc(arr3);
		
						vo.setStrCotactEmailIdForEsc(arr4);
		
						vo.setStrCotactNoForEsc(arr5);
		
						vo.setStrCotactFaxForEsc(arr6);
				    }
				
					if(j>1)
					{
						vo.setStrLevelOneEsc("1");
						vo.setStrLevelTwoEsc("1");
					}
					else
					{
						vo.setStrLevelOneEsc("1");
						vo.setStrLevelTwoEsc("0");
						
					}	
				
               
			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplierType.1");
			nQueryIndex = dao.setQuery(strQuery);
		//	dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			web = null;
			web = dao.executeQry(nQueryIndex);
			vo.setWsSupplierType(web);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("SupplierMstDAO.modify() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

			web = null;
		}
	}

	/**
	 * This function is used to update record during click of modify.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void update(SupplierMstVO vo) {
		HisDAO dao = null;
		// String strQuery = "";
		 int j = 0;
		// WebRowSet web = null;
		SupplierDAO supplierDAO = null;
		BlackListSupplierDtlDAO blackListSupp = null;
		// String strslno = "";
		try {
            //System.out.println("Inside DAO");
			supplierDAO = new SupplierDAO();
			blackListSupp = new BlackListSupplierDtlDAO();
			dao = new HisDAO("mms", "SupplierMstDAO");
			/*
			 * strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.8");
			 * nQueryIndex = dao.setQuery(strQuery);
			 * dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			 * dao.setQryValue(nQueryIndex, 2, vo.getStrSupplierId()); web =
			 * dao.executeQry(nQueryIndex); if (web.next()) { strslno =
			 * web.getString(1);
			 *  }
			 */
			// System.out.println("vo.getStrAddress();;;"+vo.getStrAddress());
			// // System.out.println("---------->"+vo.getStrEffectiveFrom());
			supplierDAO.setStrAddress(vo.getStrAddress());

			supplierDAO.setStrCityName(vo.getStrCity());
			supplierDAO.setStrContactPerson(vo.getStrContactPerson());
			supplierDAO.setStrEffectiveFrom(vo.getStrEffectiveFrom());
			supplierDAO.setStrEmail1(vo.getStrEmailId1());
			supplierDAO.setStrEmail2(vo.getStrEmailId2());
			// supplierDAO.setStrEntryDate("sysdate");
			supplierDAO.setStrFaxNo1(vo.getStrFaxNo1());
			supplierDAO.setStrFaxNo2(vo.getStrFaxNo2());
			supplierDAO.setStrHospitalCode(vo.getStrHospCode());
			supplierDAO.setStrIsValid(vo.getStrIsValid());

			supplierDAO.setStrPhone1(vo.getStrPhoneNo1());
			supplierDAO.setStrPhone2(vo.getStrPhoneNo2());
			supplierDAO.setStrPinCode(vo.getStrPincode());

			supplierDAO.setStrRemarks(vo.getStrRemarks());
			supplierDAO.setStrSeatId(vo.getStrSeatId());
			supplierDAO.setStrStoreTypeId(vo.getStrItemCatNo());
			supplierDAO.setStrSupplierGradeId(vo.getStrSupplierGrade());
			supplierDAO.setStrSupplierName(vo.getStrSupplierName());
			supplierDAO.setStrSupplierStatus(vo.getStrSupplierStatus());
			// supplierDAO.setStrSupplierType(vo.getStrSupplierType());
			supplierDAO.setStrWebSite(vo.getStrWebsite());
			supplierDAO.setStrSupplierId(vo.getStrSupplierId());
			supplierDAO.setStrIsSupplier(vo.getStrIsSupplier());
			supplierDAO.setStrIsManufacturer(vo.getStrIsManufacturer());
			supplierDAO.setStrIsAgent(vo.getStrIsAgent());
			supplierDAO.setStrIsBuyer(vo.getStrIsBuyer());
			supplierDAO.setStrLstNo(vo.getStrLstNo());
			supplierDAO.setStrCstNo(vo.getStrCstNo());
			supplierDAO.setStrPANNo(vo.getStrPANNo());
			supplierDAO.setStrSupplierType(vo.getStrSupplierType());
			// // System.out.println("update
			// getStrLocalPurchaseSuppFlag"+vo.getStrLocalPurchaseSuppFlag());

			// supplierDAO.setStrLocalPurchaseSuppFlag(vo.getStrLocalPurchaseSuppFlag());
			supplierDAO.setStrForeignerSuppFlag(vo.getStrForeignerSuppFlag());

			/* Aritra */
			supplierDAO.setStrContractNo(vo.getStrContractNo());
			supplierDAO.setStrContractDate(vo.getStrContractDate());
			supplierDAO.setStrExpiryDate(vo.getStrExpiryDate());
			
			supplierDAO.setStrCountryCode(vo.getStrCountryCode());
			supplierDAO.setStrStateCode(vo.getStrStateCode());
			supplierDAO.setStrSuppTurnOver(vo.getStrSuppTurnOver());
			supplierDAO.setStrSuppTurnOverUnit(vo.getStrSuppTurnOverUnit());
			supplierDAO.setStrSupplierProvMaintenance(vo.getStrSupplierProvMaintenance());
			supplierDAO.setStrEsclationMtxAvl(vo.getStrEsclationMtxAvl());
			
			supplierDAO.setStrSlNo(vo.getStrSlNo());
			
			supplierDAO.update(dao);

            //System.out.println("Level One:::"+vo.getStrLevelOneEsc()+"::Level Two::"+vo.getStrLevelTwoEsc());
            
            blackListSupp.setStrSeatId(vo.getStrSeatId());
			blackListSupp.setStrSupplierId(vo.getStrSupplierId());
			blackListSupp.setStrHospitalCode(vo.getStrHospCode());
			blackListSupp.updateEsc(dao);
            
            if(vo.getStrLevelOneEsc().equals("1") && vo.getStrLevelTwoEsc().equals("1"))
			{
				for(int i=0;i<2;i++)
				{
				    //System.out.println("When Both Level Selected in Updated ::"+i);
                    j++;
                    
                   
                    
					blackListSupp.setStrSupplierId(vo.getStrSupplierId());
					blackListSupp.setStrEscLevel(String.valueOf(j));
					blackListSupp.setStrHospitalCode(vo.getStrHospCode());
					blackListSupp.setStrSupplierId(vo.getStrSupplierId());
					blackListSupp.setStrEscLevel(String.valueOf(j));
					blackListSupp.setStrHospitalCode(vo.getStrHospCode());
					blackListSupp.setStrCotactPersonForEsc(vo.getStrCotactPersonForEsc()[i]);
					blackListSupp.setStrContactPersonDesgForEsc(vo.getStrContactPersonDesgForEsc()[i]);
					blackListSupp.setStrCotactEmailIdForEsc(vo.getStrCotactEmailIdForEsc()[i]);
					blackListSupp.setStrCotactNoForEsc(vo.getStrCotactNoForEsc()[i]);
					blackListSupp.setStrCotactFaxForEsc(vo.getStrCotactFaxForEsc()[i]);
					blackListSupp.insertSupplierEsc(dao);
					
				}	
				
				
			}
			else
			{
				
				if(vo.getStrLevelOneEsc().equals("1"))
				{
					for(int i=0;i<1;i++)
					{
						j++;
						System.out.println("When Single Level Selected in Updated");
												
						blackListSupp.setStrSupplierId(vo.getStrSupplierId());
						blackListSupp.setStrEscLevel(String.valueOf(j));
						blackListSupp.setStrHospitalCode(vo.getStrHospCode());
						blackListSupp.setStrSupplierId(vo.getStrSupplierId());
						blackListSupp.setStrEscLevel(String.valueOf(j));
						blackListSupp.setStrHospitalCode(vo.getStrHospCode());
						blackListSupp.setStrCotactPersonForEsc(vo.getStrCotactPersonForEsc()[i]);
						blackListSupp.setStrContactPersonDesgForEsc(vo.getStrContactPersonDesgForEsc()[i]);
						blackListSupp.setStrCotactEmailIdForEsc(vo.getStrCotactEmailIdForEsc()[i]);
						blackListSupp.setStrCotactNoForEsc(vo.getStrCotactNoForEsc()[i]);
						blackListSupp.setStrCotactFaxForEsc(vo.getStrCotactFaxForEsc()[i]);
						
						
						blackListSupp.insertSupplierEsc(dao);
					}	
										
				}
				
			}
			
			if (vo.getStrSupplierStatus().equals("2")
					|| vo.getStrIsBlackListMod().equals("1")) {

				blackListSupp.setStrActionDate(vo.getStrActionDate());
				blackListSupp.setStrCommitteeNo(vo.getStrCommitee());
				blackListSupp.setStrHospitalCode(vo.getStrHospCode());
				if (vo.getStrSupplierStatus().equals("2")) {
					blackListSupp.setStrIsBlackListFlag("1");
				} else {
					blackListSupp.setStrIsBlackListFlag("0");
				}
				blackListSupp.setStrRemarks(vo.getStrBlackListedRemarks());
				blackListSupp.setStrSeatId(vo.getStrSeatId());
				blackListSupp.setStrIsValid(vo.getStrIsValid());
				// blackListSupp.setStrSuppSlno(strslno);
				// blackListSupp.setStrStoreTypeId(vo.getStrItemCatNo());
				blackListSupp.setStrSupplierId(vo.getStrSupplierId());
				blackListSupp.update(dao);

				blackListSupp.setStrActionDate(vo.getStrActionDate());
				blackListSupp.setStrCommitteeNo(vo.getStrCommitee());
				blackListSupp.setStrHospitalCode(vo.getStrHospCode());
				blackListSupp.setStrRemarks(vo.getStrBlackListedRemarks());
				blackListSupp.setStrSeatId(vo.getStrSeatId());
				blackListSupp.setStrIsValid(vo.getStrIsValid());
				// blackListSupp.setStrSuppSlno(strslno);
				// blackListSupp.setStrStoreTypeId(vo.getStrItemCatNo());
				blackListSupp.setStrSupplierId(vo.getStrSupplierId());

				blackListSupp.insert(dao);
			}
			synchronized (dao) {
				dao.fire();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			vo.setStrMsgString("SupplierMstDAO.update() --> " + e.getMessage());
			System.out.println(	vo.getStrMsgString());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			supplierDAO = null;
			blackListSupp = null;
			// web = null;
		}
	}

	/*
	 * This function check whether data of same name is already exist for same
	 * supplier type id
	 * 
	 */
	/**
	 * Check duplicay add.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void CheckDuplicayAdd(SupplierMstVO vo) {
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		WebRowSet web = null;
		int ncount = 0;
		try {
			dao = new HisDAO("mms", "SupplierMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.9");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrItemCatNo());
			dao.setQryValue(nQueryIndex, 3, vo.getStrSupplierName());
			web = dao.executeQry(nQueryIndex);
			while (web.next()) {
				ncount = Integer.parseInt(web.getString(1));
			}

			// // System.out.println("ncount="+ncount);
			if (ncount == 0) {
				vo.setIsFlag(true);
			} else {
				vo.setIsFlag(false);
			}

		} catch (Exception e) {

			vo.setStrMsgString("SupplierMstDAO.CheckDuplicayAdd() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			web = null;
		}

	}

	/*
	 * This function check whether data of same name is already exist other same
	 * supplier type id
	 * 
	 */
	/**
	 * Check duplicay modi.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void CheckDuplicayModi(SupplierMstVO vo) {
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		WebRowSet web = null;
		int ncount = 0;
		try {

			dao = new HisDAO("mms", "SupplierMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.10");
			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrSupplierId());
			dao.setQryValue(nQueryIndex, 3, vo.getStrSupplierName());
			dao.setQryValue(nQueryIndex, 4, vo.getStrItemCatNo());
			web = dao.executeQry(nQueryIndex);
			if (web.next()) {
				ncount = Integer.parseInt(web.getString(1));
			}

			// // System.out.println("ncount="+ncount);
			if (ncount == 0) {
				vo.setIsFlag(true);
			} else {
				vo.setIsFlag(false);
			}

		} catch (Exception e) {

			vo.setStrMsgString("SupplierMstDAO.CheckDuplicayModi() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			web = null;
		}

	}

	/**
	 * This function is used to bring details in supplier Grade Combo.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void getSupplierGrade(SupplierMstVO vo) {
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("mms", "SupplierMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.3");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			web = dao.executeQry(nQueryIndex);
			vo.setSupplierGradeWS(web);
		} catch (Exception e) {
			vo.setStrMsgString("SupplierMstDAO.getSupplierGrade() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			web = null;
		}
	}

	/**
	 * This function is used to bring details in Committe Combo.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void getCommiteeDtl(SupplierMstVO vo) {
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("mms", "SupplierMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.4");
			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
			// dao.setQryValue(nQueryIndex, 2, vo.getStrItemCatNo());
			web = null;
			web = dao.executeQry(nQueryIndex);
			vo.setCommiteeNameWS(web);
		} catch (Exception e) {
			// e.printStackTrace();
			vo.setStrMsgString("SupplierMstDAO.getCommiteeDtl() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			web = null;
		}
	}

	/**
	 * This function is used set initial parameters for view page.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void view(SupplierMstVO vo) {
		HisDAO dao = null;
		String strQuery = "";
		int nQueryIndex = 0;
		WebRowSet web = null;
		WebRowSet web1 = null;
		int j=0;
		String strQuery1 = "";
		int nQueryIndex1 = 0;
		try {

			dao = new HisDAO("mms", "SupplierMstDAO");
			strQuery = mms.qryHandler_mms.getQuery(1, "select.supplier.view.0");

			nQueryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQueryIndex, 1, vo.getStrSupplierId());
			dao.setQryValue(nQueryIndex, 2, vo.getStrHospCode());
			web = dao.executeQry(nQueryIndex);
			if (web.next()) {

				vo.setStrSupplierName(web.getString(1));
				vo.setStrItemCategoryName(web.getString(2));
				vo.setStrContactPerson(web.getString(3));
				vo.setStrAddress(web.getString(4));
				vo.setStrCity(web.getString(5));
				vo.setStrPincode(web.getString(6));
				vo.setStrPhoneNo1(web.getString(7));
				vo.setStrPhoneNo2(web.getString(8));
				String mail=web.getString(9).replaceAll("\\.", " \\ [dot]");
				String mail1=mail.replace("@", " [at]");
				
				vo.setStrEmailId1(mail1);
				String mail2=web.getString(10).replaceAll("\\.", " \\ [dot]");
				String mail3=mail2.replace("@", " [at]");
				
				vo.setStrEmailId2(mail3);
				vo.setStrFaxNo1(web.getString(11));
				vo.setStrFaxNo2(web.getString(12));
				vo.setStrWebsite(web.getString(13));
				vo.setStrSupplierStatus(web.getString(14));
				vo.setStrSupplierStatusCode(web.getString(14));
				vo.setStrRemarks(web.getString(15));
				vo.setStrEffectiveFrom(web.getString(16));
				vo.setStrIsValid(web.getString(17));
				vo.setStrSupplierGradeCode(web.getString(18));

				vo.setStrItemCatNo(web.getString(19));
				// vo.setStrLocalPurchaseSuppFlag(web.getString(20));
				vo.setStrLstNo(web.getString(20));
				vo.setStrCstNo(web.getString(21));
				vo.setStrPANNo(web.getString(22));
				vo.setStrIsSupplier(web.getString(23));
				vo.setStrIsManufacturer(web.getString(24));
				vo.setStrIsAgent(web.getString(25));
				vo.setStrIsBuyer(web.getString(26));
				vo.setStrSupplierGrade(web.getString(27));
				vo.setStrForeignerSuppFlag(web.getString(28));
				vo.setStrCountryCode(web.getString(29));
				vo.setStrStateCode(web.getString(30));

				/* Aritra */
				vo.setStrContractNo(web.getString(31));
				vo.setStrContractDate(web.getString(32));
				vo.setStrExpiryDate(web.getString(33));
				vo.setStrSupplierType(web.getString(34));
				vo.setStrSuppTurnOver(web.getString(35));
				vo.setStrSuppTurnOverUnit(web.getString(36));
				
				vo.setStrSupplierProvMaintenance(web.getString(37));
				vo.setStrEsclationMtxAvl(web.getString(38));
				// // System.out.println("modify
				// getStrLocalPurchaseSuppFlag"+vo.getStrLocalPurchaseSuppFlag());
				if (vo.getStrSupplierStatus().equals("2")) {
					strQuery = mms.qryHandler_mms.getQuery(1,
							"select.supplier.view.1");
					nQueryIndex = dao.setQuery(strQuery);
					dao.setQryValue(nQueryIndex, 1, vo.getStrSupplierId());
					dao.setQryValue(nQueryIndex, 2, vo.getStrHospCode());
					dao.setQryValue(nQueryIndex, 3, vo.getStrSupplierId());
					dao.setQryValue(nQueryIndex, 4, vo.getStrHospCode());
					web = dao.executeQry(nQueryIndex);
					if (web.next()) {
						vo.setStrActionDate(web.getString(1));
						vo.setStrBlackListedRemarks(web.getString(2));
						vo.setStrCommitteCode(web.getString(3));
						vo.setStrCommitee(web.getString(4));
					}

					vo.setStrIsBlackListMod("1");
				} else {
					vo.setStrIsBlackListMod("0");
				}

				/* Aritra */
				if (!"0".equals(vo.getStrSupplierType())) {
					strQuery = mms.qryHandler_mms.getQuery(1,
							"select.supplier.view.2");
					nQueryIndex = dao.setQuery(strQuery);
					dao.setQryValue(nQueryIndex, 1, vo.getStrHospCode());
					dao.setQryValue(nQueryIndex, 2, vo.getStrSupplierType());
					web = dao.executeQry(nQueryIndex);
					if (web.next()) {
						vo.setStrSupplierType(web.getString(1));
					}
				}

			}
			
			strQuery1 = mms.qryHandler_mms.getQuery(1,"select.supplier.88");
			nQueryIndex1 = dao.setQuery(strQuery1);
			dao.setQryValue(nQueryIndex1, 1, vo.getStrSupplierId());
			dao.setQryValue(nQueryIndex1, 2, vo.getStrHospCode());
		
			if (vo.getStrEsclationMtxAvl().equals("1")) 
			{				
				web1 = dao.executeQry(nQueryIndex1);
				
				String arr1[] = new String[web1.size()];
				String arr2[] = new String[web1.size()];
				String arr3[] = new String[web1.size()];
				String arr4[] = new String[web1.size()];
				String arr5[] = new String[web1.size()];
				String arr6[] = new String[web1.size()];
				
                for(int k =0;web1.next();k++) 
				{
                	
					arr1[k] = web1.getString(1);
					arr2[k] = web1.getString(2);
					arr3[k] = web1.getString(3);
					arr4[k] = web1.getString(4);
					arr5[k] = web1.getString(5);
					arr6[k] = web1.getString(6);
					j++;
					
				}
                vo.setStrCotactPersonForEsc(arr2);

				vo.setStrContactPersonDesgForEsc(arr3);

				vo.setStrCotactEmailIdForEsc(arr4);

				vo.setStrCotactNoForEsc(arr5);

				vo.setStrCotactFaxForEsc(arr6);
			}
			
				if(j>1)
				{
					vo.setStrLevelOneEsc("1");
					vo.setStrLevelTwoEsc("1");
				}
				else
				{
					vo.setStrLevelOneEsc("1");
					vo.setStrLevelTwoEsc("0");
					
				}	
				

		} catch (Exception e) {
			 e.printStackTrace();
			vo.setStrMsgString("SupplierMstDAO.view() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

			web = null;
		}
	}

}
