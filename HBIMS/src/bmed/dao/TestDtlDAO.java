package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import bmed.vo.TestDtlVO;

public class TestDtlDAO {

	

	/*
	 * To insert or update data
	 * 
	 * @param complaintRequestDtlVO_p the ComplaintRequestDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void save(TestDtlVO testDtlVO_p,
			HisDAO hisDAO_p) throws Exception {
		/* Total Variable 52 */
		final String strproc_name = "{CALL  pkg_bmed_dml.proc_hemt_test_dtl(?,?,?,?,?,?,?,? ,?,? ,  ?,?,?,?,?,?,?,? ,?,? , ?,?,?,?,?,?)}";
		final int nProcedureIndex;
		try {
			
			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(testDtlVO_p);
			
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", testDtlVO_p.getStrMode(),1); // 1

			hisDAO_p.setProcInValue(nProcedureIndex, "strequtestid",	testDtlVO_p.getStrEquTestId(),2); // 2
			/*p_mode                              VARCHAR2 DEFAULT 1,
		      strequtestid                        VARCHAR2,
		      strtestid                           VARCHAR2,
		      strhospitalcode                     VARCHAR2,
		      strdeptcode                         VARCHAR2,
		      strstoreid                          VARCHAR2,
		      strengineeringitemtypeid            VARCHAR2,
		      strengineeringitemsubtypeid         VARCHAR2,
		      stritemid                           VARCHAR2,
		      stritemslno                         VARCHAR2,
		      strbatchslno                        VARCHAR2,
		      strmanufactureslno                  VARCHAR2,
		      strwarrantyslno                     VARCHAR2,
		      strmaintenancecontractslno          VARCHAR2,  
		      strisinternalexternal               VARCHAR2,
		      strconfirmby                        VARCHAR2,
		      strpersonname                       VARCHAR2,
		      strtestdate                         DATE,
		      strtesttime                         VARCHAR2,
		      strresult                           VARCHAR2,
		      strremarks                          VARCHAR2,
		      strseatid                           VARCHAR2,
		      strisvalid                          VARCHAR2 DEFAULT 1,
		      
		      */
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strtestid",	testDtlVO_p.getStrTestId(),3); // 3
		
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strhospitalcode",	testDtlVO_p.getStrHospitalCode(),4); // 4
			hisDAO_p.setProcInValue(nProcedureIndex, "strdeptcode",	testDtlVO_p.getStrDeptCodeNew(),5); // 5
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strstoreid",	testDtlVO_p.getStrStoreId(),6); // 6
			hisDAO_p.setProcInValue(nProcedureIndex, "strengineeringitemtypeid",	testDtlVO_p.getStrEnggItemTypeId(),7); // 7
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strengineeringitemsubtypeid",	testDtlVO_p.getStrEnggItemSubTypeId(),8); // 8
			hisDAO_p.setProcInValue(nProcedureIndex, "stritemid",	testDtlVO_p.getStrItemId(),9); // 9
			
			hisDAO_p.setProcInValue(nProcedureIndex, "stritemslno",	testDtlVO_p.getStrItemSerialNo(),10); // 10
			hisDAO_p.setProcInValue(nProcedureIndex, "strbatchslno",	testDtlVO_p.getStrItemBatchNo(),11); // 11
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strmanufactureslno",	testDtlVO_p.getStrMaintenanceContractSlNo(),12); // 12
			hisDAO_p.setProcInValue(nProcedureIndex, "strwarrantyslno",	testDtlVO_p.getStrWarrantySlNo(),13); // 13
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strmaintenancecontractslno",	testDtlVO_p.getStrMaintenanceContractSlNo(),14); // 14
			hisDAO_p.setProcInValue(nProcedureIndex, "strisinternalexternal",	testDtlVO_p.getStrIsInternal(),15); // 15
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strconfirmby",	testDtlVO_p.getStrConfirmedBy(),16); // 16
			hisDAO_p.setProcInValue(nProcedureIndex, "strpersonname",	testDtlVO_p.getStrContactPersonName(),17); // 17
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strtestdate",	testDtlVO_p.getStrTestDate(),18); // 18
			hisDAO_p.setProcInValue(nProcedureIndex, "strtesttime",	testDtlVO_p.getStrTestTime(),19); // 19


			hisDAO_p.setProcInValue(nProcedureIndex, "strresult",	testDtlVO_p.getStrResult(),20); // 20
			hisDAO_p.setProcInValue(nProcedureIndex, "strremarks",	testDtlVO_p.getStrRemarks(),21); // 21
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strseatid",	testDtlVO_p.getStrSeatId(),22); // 22
			hisDAO_p.setProcInValue(nProcedureIndex, "strisvalid",	testDtlVO_p.getStrIsValid(),23); // 23
			
			hisDAO_p.setProcInValue(nProcedureIndex, "stritembrandid",	"0",24); // 22
			hisDAO_p.setProcInValue(nProcedureIndex, "strtestinpmode",	"1",25); // 23
		
			

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,26); // varchar //24

			/* Executing Procedure */
			hisDAO_p.execute(nProcedureIndex, 1);
		} catch (Exception exception) {
			throw new Exception(
					"TestDtlDAO.save(TestDtlVO)-->"
							+ exception.getMessage());
		}
	}



	
	

}
