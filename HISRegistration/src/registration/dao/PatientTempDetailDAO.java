package registration.dao;


import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.exceptions.HisException;

public class PatientTempDetailDAO extends RegistrationDAO
{
	public PatientTempDetailDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}
	public static void updateStatus(String p_Mode, String crNo, String tempCRNo, String regStatus, String hospitalCode, HisDAO hisDAO_p)
	{
		final String strProcName = "{call pkg_reg_dml.dml_hrgt_patient_temp_dtl(?,?,?,?,?,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",p_Mode,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_puk",crNo,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_temp_puk",tempCRNo,3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hrgnum_reg_status",regStatus,4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",hospitalCode,5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 6);
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			
			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) 
			{
				throw new Exception("Data Base Error:" + strDbErr);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException(e.getMessage());
		}
	}
}
