package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import bmed.vo.TestParameterDtlVO;

public class TestParameterDtlDAO {
	/*
	 * To insert or update data
	 * 
	 * @param complaintRequestDtlVO_p the ComplaintRequestDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void save(TestParameterDtlVO testParameterDtlVO_p,
			HisDAO hisDAO_p) throws Exception {
		/* Total Variable 52 */
		final String strproc_name = "{CALL  pkg_bmed_dml.proc_hemt_test_parameter_dtl(?,?,?,?,?, ?,?,?,?)}";
		final int nProcedureIndex;
		try {
			
			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(testParameterDtlVO_p);
			
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", testParameterDtlVO_p.getStrMode(),1); // 1

			hisDAO_p.setProcInValue(nProcedureIndex, "strequtestid",	testParameterDtlVO_p.getStrTestParaEquId(),2); // 2
					
			hisDAO_p.setProcInValue(nProcedureIndex, "strtestid",	testParameterDtlVO_p.getStrTestId(),3); // 3
		
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strparameterid",	testParameterDtlVO_p.getStrTestParaId(),4); // 4
			hisDAO_p.setProcInValue(nProcedureIndex, "stroutput",	testParameterDtlVO_p.getStrOutPut(),5); // 5
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strhospitalcode",	testParameterDtlVO_p.getStrHospitalCode(),6); // 6
			
			
			hisDAO_p.setProcInValue(nProcedureIndex, "strseatid",	testParameterDtlVO_p.getStrSeatId(),7); //7
			hisDAO_p.setProcInValue(nProcedureIndex, "strisvalid",	testParameterDtlVO_p.getStrIsValid(),8); // 8
			
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,9); // varchar //9

			/* Executing Procedure */
			hisDAO_p.execute(nProcedureIndex, 1);
		} catch (Exception exception) {
			throw new Exception(
					"TestDtlDAO.save(TestDtlVO)-->"
							+ exception.getMessage());
		}
	}



	
	

}
