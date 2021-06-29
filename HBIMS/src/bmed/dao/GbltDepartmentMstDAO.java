package bmed.dao;



import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.GbltDepartmentMstVO;


public class GbltDepartmentMstDAO{

	public static  void getDepartmentCombo(GbltDepartmentMstVO gbltDepartmentMstVO_p,HisDAO hisDAO_p)
			throws Exception {
		
		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_GBLT_DEPARTMENT_MST_CMB(?,?,?,?,?)}";
		
		final int nProcedureIndex;
		
		final String strDbErr;
		final WebRowSet webRowSet;
		try {
			
			
			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
			System.out.println("Department Name::::::"+gbltDepartmentMstVO_p.getStrHospitalCode());

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", gbltDepartmentMstVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", gbltDepartmentMstVO_p.getStrHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", gbltDepartmentMstVO_p.getStrSeatId(),3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // 1 for varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5); // 2 for Cursor
			
			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			/* Getting out parameters*/
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet=hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");
			
			/* If Database Error Occurs, No farther processing is required.*/
			if (strDbErr != null && !strDbErr.equals("") ) {
				throw new Exception("Data Base Error:"+strDbErr);
			}
				
			
			/* Sets The WebRowSet in GbltDepartmentMstVO  */
			gbltDepartmentMstVO_p.setWrsDepartmentOptions(webRowSet);

			

			
		} catch (Exception exception) {
			
			exception.printStackTrace();
			
			throw new Exception("GbltDepartmentMstDAO.getDepartmentCombo(GbltDepartmentMstVO)-->"+exception.getMessage());
		} 
		
	}
	
	public static  void getDepartmentBasedOnRoleSeatCombo(GbltDepartmentMstVO gbltDepartmentMstVO_p,HisDAO hisDAO_p)
	throws Exception {

		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_GBLT_DEPARTMENT_ROLEBASED(?,?,?,?,?)}";
		
		final int nProcedureIndex;
		
		final String strDbErr;
		final WebRowSet webRowSet;
		try {
			
			
			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
		
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", gbltDepartmentMstVO_p.getStrHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", gbltDepartmentMstVO_p.getStrSeatId(),3);
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // 1 for varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5); // 2 for Cursor
			
			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			/* Getting out parameters*/
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet=hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");
			
			/* If Database Error Occurs, No farther processing is required.*/
			if (strDbErr != null && !strDbErr.equals("") ) {
				throw new Exception("Data Base Error:"+strDbErr);
			}
				
			
			/* Sets The WebRowSet in GbltDepartmentMstVO  */
			gbltDepartmentMstVO_p.setWrsDepartmentOptions(webRowSet);
		
			
		
			
		} catch (Exception exception) {
			throw new Exception("GbltDepartmentMstDAO.getDepartmentCombo(GbltDepartmentMstVO)-->"+exception.getMessage());
		} 

}
	

}