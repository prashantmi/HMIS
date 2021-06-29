package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.WarrantyDtlVO;

public class WarrantyDtlDAO {

	public static void getData(WarrantyDtlVO warrantyDtlVO_p, HisDAO hisDAO_p)
			throws Exception {

		final String strProcName = "{call pkg_bmed_view.proc_hstt_warranty_dtl(?,?,?,?,?, ?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(warrantyDtlVO_p);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", warrantyDtlVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_item_id",
					warrantyDtlVO_p.getStrItemId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					warrantyDtlVO_p.getStrHospitalCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_batch_no",
					warrantyDtlVO_p.getStrBatchSlNo(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_item_sl_no",
					warrantyDtlVO_p.getStrItemSlNo(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_sl_no",
					warrantyDtlVO_p.getStrSlNo(),6);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_itembrand_id", warrantyDtlVO_p.getStrItemBrandId(),7);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,8); // 1 for
																	// varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,9); // 2 for
																		// Cursor

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			/* Sets The WebRowSet in GbltDepartmentMstVO */
			warrantyDtlVO_p.setWrsResultData(webRowSet);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(
					"WarrantyDtlDAO.getData(WarrantyDtlVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}
	
	/*
	 * insert() method is used to insert data into Table HSTT_WARRANTY_DTL with 
	 * Mode 1) Save
	 * Mode 2) Update
	 * Mode 3) 
	 * 
	 * @param warrantyDtlVO_p		the WarrantyDtlVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void insert(WarrantyDtlVO warrantyDtlVO_p, HisDAO hisDAO_p) throws Exception
	{
		  /*   Total Variable 41   */
		final String strproc_name = "{CALL pkg_bmed_dml.proc_HSTT_WARRANTY_DTL(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? , ?,?,?,?,?)}";
		final int nProcedureIndex;
		
		try 
		{			
			    
			    // Here We Call Utility method to fill Null value with Empty String
	            HisUtil.replaceNullValueWithEmptyString(warrantyDtlVO_p);
	            
	            /*
	             System.out.println("Inside DAO");
	            System.out.println("p_HEMNUM_ITEM_ID::"+warrantyDtlVO_p.getStrItemId());
			    System.out.println("p_HEMSTR_BATCH_NO::"+warrantyDtlVO_p.getStrBatchSlNo());
			    System.out.println("p_HEMNUM_ITEM_SL_NO::"+warrantyDtlVO_p.getStrItemSlNo());
			    System.out.println("p_HEMNUM_SL_NO::"+warrantyDtlVO_p.getStrSlNo());
			    System.out.println("p_GNUM_HOSPITAL_CODE:::"+warrantyDtlVO_p.getStrHospitalCode());
			    System.out.println("p_HSTNUM_ITEMBRAND_ID::::"+warrantyDtlVO_p.getStrItemBrandId());
			    System.out.println("p_mode::::"+warrantyDtlVO_p.getStrMode());
	            */
				nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
				hisDAO_p.setProcInValue(nProcedureIndex,"p_mode",warrantyDtlVO_p.getStrMode(),1);                      //1
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_ITEM_ID",warrantyDtlVO_p.getStrItemId(),2);            //2 
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_ITEMBRAND_ID",warrantyDtlVO_p.getStrItemBrandId(),3);  //3
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTSTR_BATCH_SL_NO",warrantyDtlVO_p.getStrBatchSlNo(),4);     //4
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GNUM_HOSPITAL_CODE",warrantyDtlVO_p.getStrHospitalCode(),5);  //5
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_ITEM_SL_NO", warrantyDtlVO_p.getStrItemSlNo(),6);     //6
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_MANUF_ID" ,warrantyDtlVO_p.getStrManufId(),7);         //7
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_MANUF_SL_NO","0",8);                                   //8
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_SL_NO" ,warrantyDtlVO_p.getStrSlNo(),9);  // Enter By Function  in case of save   //9
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTDT_WARRENTY_DATE",warrantyDtlVO_p.getStrWarrentyDate(),10); //10
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_WARRENTY_UPTO" , warrantyDtlVO_p.getStrWarrentyUpto(),11);         //11
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_WARRENTY_UPTO_UNIT",warrantyDtlVO_p.getStrWarrentyUptoUnit(),12);  //12
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_IS_ITEM" ,warrantyDtlVO_p.getStrIsItem(),13);                      //13
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTDT_FINANCIAL_START_YEAR",warrantyDtlVO_p.getStrFinancialStartYear(),14);  //14
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTDT_FINANCIAL_END_YEAR" ,warrantyDtlVO_p.getStrFinancialEndYear(),15);     //15
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURNUM_UPLOAD_NO",warrantyDtlVO_p.getStrUploadNo(),16);        //16
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURSTR_DOC_REF_NO",warrantyDtlVO_p.getStrDocRefNo(),17);       //17   
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMSTR_TERM_N_CON" ,warrantyDtlVO_p.getStrTermsAndCondition(),18);  //18
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURDT_DOC_REF_DATE",warrantyDtlVO_p.getStrDocRefDate(),19);        //19
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GSTR_REMARKS",warrantyDtlVO_p.getStrRemarks(),20);                  //20
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GDT_ENTRY_DATE","",21);            // Dummy Value Default SYSDATE   //21  
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GNUM_SEATID" ,warrantyDtlVO_p.getStrSeatId(),22);       //22
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GNUM_ISVALID",warrantyDtlVO_p.getStrIsValid(),23);      //23
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMSTR_TENDER_NO",warrantyDtlVO_p.getStrTenderNo(),24); //24
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GDT_LSTMOD_DATE" ,warrantyDtlVO_p.getStrInstallationDate(),25);// Dummy field use for new field Installation Date//25   
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMDT_TENDER_DATE",warrantyDtlVO_p.getStrTenderDate(),26);         //26
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GNUM_LSTMOD_SEATID",warrantyDtlVO_p.getStrSeatId(),27);  // Enter By Function  //27
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMSTR_ORDER_NO",warrantyDtlVO_p.getStrOrderNo(),28);                          //28
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMDT_ORDER_DATE" ,warrantyDtlVO_p.getStrOrderDate(),29);                      //29
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNO_CANCEL_ID",warrantyDtlVO_p.getStrCancelId(),30);                         //30   
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMDT_CANCEL_DATE",warrantyDtlVO_p.getStrCancelDate(),31);                     //31
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMSTR_EXT_TERM_N_CON",warrantyDtlVO_p.getStrExtTermsAndCondition(),32);       //32
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMSTR_CANCEL_REMARKS",warrantyDtlVO_p.getStrCancelRemarks(),33);              //33
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_IS_EXTENDED",warrantyDtlVO_p.getStrIsExtended(),34);                    //34
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMDT_EXTENDED_START_DATE",warrantyDtlVO_p.getStrExtendedStartDate(),35);      //35
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_EXTENDED_UPTO",warrantyDtlVO_p.getStrExtendedUpto(),36);                //36
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_EXTENDED_UPTO_UNIT",warrantyDtlVO_p.getStrExtendedUptoUnit(),37);       //37
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURNUM_EXT_UPLOAD_NO",warrantyDtlVO_p.getStrExtUploadNo(),38);                //38
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURNUM_EXT_DOC_REF_NO",warrantyDtlVO_p.getStrExtDocRefNo(),39);               //39
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURDT_EXT_DOC_REF_DATE" ,warrantyDtlVO_p.getStrExtDocRefDate(),40);           //40
				
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_P_WAREHOUSEID" ,warrantyDtlVO_p.getStrDeptId(),41);           //40
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_C_WAREHOUSEID" ,warrantyDtlVO_p.getStrStoreId(),42);           //40
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_ENGGITEMTYPEID" ,warrantyDtlVO_p.getStrEnggItemTypeId(),43);           //40
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_ENGGITEMSUBTYPEID" ,warrantyDtlVO_p.getStrEnggItemSubTypeId(),44);           //40
				/* Setting and Registering In and Out Parameters */
				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,45); // varchar    //41
					
				/* Executing Procedure */
				hisDAO_p.execute(nProcedureIndex, 1);
				//hisDAO_p.executeProcedureByPosition(nProcedureIndex);
		
				System.out.println("hisDAO_p.executeProcedureByPosition(nProcedureIndex) of insert method-------");
								

			
	} 
	catch (Exception exception) 
	{
		exception.printStackTrace();
	   throw new Exception("WarrantyDtlVO.insert(WarrantyDtlVO)-->"+ exception.getMessage());
	}
	
		
  }
	
	
	/* 
	 * Thsis function is used for 	Equipment Inspection Test Details transaction
	 * 
	 * 
	 *
	* 
	 * @param warrantyDtlVO_p		the WarrantyDtlVO
	 * @param hisDAO_p		the HisDAO
	 */
	
	public static void update(WarrantyDtlVO warrantyDtlVO_p, HisDAO hisDAO_p) throws Exception
	{
		  /*   Total Variable 41   */
		final String strproc_name = "{CALL  pkg_bmed_dml.proc_HSTT_WARRANTY_DTL(?,?,?,?,?,?,?,?  ,  ?,?,?,?,?,?,?,?  ,  ?,?,?,?,?,?,?,?  , ?,?,?,?,?,?,?,?  , ?,?,?,?,?,?,?,? , ?,?,?,?,?)}";
		final int nProcedureIndex;
		try 
		{			
			    
			    // Here We Call Utility method to fill Null value with Empty String
	            HisUtil.replaceNullValueWithEmptyString(warrantyDtlVO_p);
	            
	            /*
	             System.out.println("Inside DAO");
	            System.out.println("p_HEMNUM_ITEM_ID::"+warrantyDtlVO_p.getStrItemId());
			    System.out.println("p_HEMSTR_BATCH_NO::"+warrantyDtlVO_p.getStrBatchSlNo());
			    System.out.println("p_HEMNUM_ITEM_SL_NO::"+warrantyDtlVO_p.getStrItemSlNo());
			    System.out.println("p_HEMNUM_SL_NO::"+warrantyDtlVO_p.getStrSlNo());
			    System.out.println("p_GNUM_HOSPITAL_CODE:::"+warrantyDtlVO_p.getStrHospitalCode());
			    System.out.println("p_HSTNUM_ITEMBRAND_ID::::"+warrantyDtlVO_p.getStrItemBrandId());
			    System.out.println("p_mode::::"+warrantyDtlVO_p.getStrMode());
	            */
				nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
				hisDAO_p.setProcInValue(nProcedureIndex,"p_mode",warrantyDtlVO_p.getStrMode(),1);                      //1
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_ITEM_ID",warrantyDtlVO_p.getStrItemId(),2);            //2 
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_ITEMBRAND_ID",warrantyDtlVO_p.getStrItemBrandId(),3);  //3
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTSTR_BATCH_SL_NO",warrantyDtlVO_p.getStrBatchSlNo(),4);     //4
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GNUM_HOSPITAL_CODE",warrantyDtlVO_p.getStrHospitalCode(),5);  //5
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_ITEM_SL_NO", warrantyDtlVO_p.getStrItemSlNo(),6);     //6
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_MANUF_ID" ,warrantyDtlVO_p.getStrManufId(),7);         //7
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_MANUF_SL_NO","0",8);                                   //8
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_SL_NO" ,warrantyDtlVO_p.getStrSlNo(),9);  // Enter By Function  in case of save   //9
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTDT_WARRENTY_DATE",warrantyDtlVO_p.getStrWarrentyDate(),10); //10
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_WARRENTY_UPTO" , warrantyDtlVO_p.getStrWarrentyUpto(),11);         //11
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_WARRENTY_UPTO_UNIT",warrantyDtlVO_p.getStrWarrentyUptoUnit(),12);  //12
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTNUM_IS_ITEM" ,warrantyDtlVO_p.getStrIsItem(),13);                      //13
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTDT_FINANCIAL_START_YEAR",warrantyDtlVO_p.getStrFinancialStartYear(),14);  //14
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HSTDT_FINANCIAL_END_YEAR" ,warrantyDtlVO_p.getStrFinancialEndYear(),15);     //15
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURNUM_UPLOAD_NO",warrantyDtlVO_p.getStrUploadNo(),16);        //16
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURSTR_DOC_REF_NO",warrantyDtlVO_p.getStrDocRefNo(),17);       //17   
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMSTR_TERM_N_CON" ,warrantyDtlVO_p.getStrTermsAndCondition(),18);  //18
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURDT_DOC_REF_DATE",warrantyDtlVO_p.getStrDocRefDate(),19);        //19
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GSTR_REMARKS",warrantyDtlVO_p.getStrRemarks(),20);                  //20
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GDT_ENTRY_DATE","",21);            // Dummy Value Default SYSDATE   //21  
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GNUM_SEATID" ,warrantyDtlVO_p.getStrSeatId(),22);       //22
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GNUM_ISVALID",warrantyDtlVO_p.getStrIsValid(),23);      //23
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMSTR_TENDER_NO",warrantyDtlVO_p.getStrTenderNo(),24); //24
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GDT_LSTMOD_DATE" ,"",25);         // Dummy Value Default SYSDATE   //25  
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMDT_TENDER_DATE",warrantyDtlVO_p.getStrTenderDate(),26);         //26
				hisDAO_p.setProcInValue(nProcedureIndex,"p_GNUM_LSTMOD_SEATID",warrantyDtlVO_p.getStrSeatId(),27);  // Enter By Function  //27
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMSTR_ORDER_NO",warrantyDtlVO_p.getStrOrderNo(),28);                          //28
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMDT_ORDER_DATE" ,warrantyDtlVO_p.getStrOrderDate(),29);                      //29
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNO_CANCEL_ID",warrantyDtlVO_p.getStrCancelId(),30);                         //30   
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMDT_CANCEL_DATE",warrantyDtlVO_p.getStrCancelDate(),31);                     //31
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMSTR_EXT_TERM_N_CON",warrantyDtlVO_p.getStrExtTermsAndCondition(),32);       //32
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMSTR_CANCEL_REMARKS",warrantyDtlVO_p.getStrCancelRemarks(),33);              //33
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_IS_EXTENDED",warrantyDtlVO_p.getStrIsExtended(),34);                    //34
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMDT_EXTENDED_START_DATE",warrantyDtlVO_p.getStrExtendedStartDate(),35);      //35
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_EXTENDED_UPTO",warrantyDtlVO_p.getStrExtendedUpto(),36);                //36
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_EXTENDED_UPTO_UNIT",warrantyDtlVO_p.getStrExtendedUptoUnit(),37);       //37
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURNUM_EXT_UPLOAD_NO",warrantyDtlVO_p.getStrExtUploadNo(),38);                //38
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURNUM_EXT_DOC_REF_NO",warrantyDtlVO_p.getStrExtDocRefNo(),39);               //39
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HPURDT_EXT_DOC_REF_DATE" ,warrantyDtlVO_p.getStrExtDocRefDate(),40);           //40
				
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_P_WAREHOUSEID" ,warrantyDtlVO_p.getStrDeptId(),41);           //40
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_C_WAREHOUSEID" ,warrantyDtlVO_p.getStrStoreId(),42);           //40
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_ENGGITEMTYPEID" ,warrantyDtlVO_p.getStrEnggItemTypeId(),43);           //40
				hisDAO_p.setProcInValue(nProcedureIndex,"p_HEMNUM_ENGGITEMSUBTYPEID" ,warrantyDtlVO_p.getStrEnggItemSubTypeId(),44);           //40
				/* Setting and Registering In and Out Parameters */
				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,45); // varchar    //41
					
				/* Executing Procedure */
				hisDAO_p.execute(nProcedureIndex, 1);
								

			
	} 
	catch (Exception exception) 
	{
//		exception.printStackTrace();
	   throw new Exception("WarrantyDtlVO.insert(WarrantyDtlVO)-->"+ exception.getMessage());
	}
	
		
  }
	
	
	
}
