package mrd.transaction.controller.data;
/**
 * @author : Adil Wasi
 * Creation Date: 06-Jun-2012
 */

import hisglobal.vo.UserVO;
import java.util.List;

import mrd.transaction.delegate.MrdEssentialDelegate;
import mrd.vo.PatientEmrAuditVO;


public class PatientEmrAuditDATA {

	/**
	 * Getting Audit Type Wise List
	 * @param userVO_p
	 */
	public static List getAuditTypeWiseList(UserVO userVO_p) {
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getAuditTypeWiseList(userVO_p); 
	}
	
	
	/**
	 * Getting Audit User List
	 * @param strMode_p
	 * @param patientEmrAuditVO_p
	 * @param userVO_p
	 */
	public static List<PatientEmrAuditVO> getAuditUserList(String strMode_p, PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getAuditUserList(strMode_p,patientEmrAuditVO_p,userVO_p);
		//return boTransactions.getKitchenList(strMode_p, voKitchenMst_p, voUser_p);
	}

	/**
	 * Getting Patient Emr Audit Dtl By Cr.No List
	 * @param patientEmrAuditVO_p
	 * @param userVO_p
	 */
	public static List<PatientEmrAuditVO> getPatientEmrAuditDtlByCrNo( PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) {
		//DietKitchenTransactionsBO boTransactions = new DietKitchenTransactionsBO();
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return (List<PatientEmrAuditVO>)delegate.getPatientEmrAuditDtlByCrNo(patientEmrAuditVO_p,userVO_p);
		//return boTransactions.getKitchenList(strMode_p, voKitchenMst_p, voUser_p);
	}

	/**
	 * Getting Patient Emr Audit Diagnosis Detail By Primary Key to show on patientEmrAuditDiagnosisDialTile.jsp
	 * @param patientEmrAuditVO_p
	 * @param userVO_p
	 */
	public static List<List<String>> showPatientEmrAuditDiagnosisDialTileByPrimaryKey(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) {
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return (List<List<String>>)delegate.showPatientEmrAuditDiagnosisDialTileByPrimaryKey(patientEmrAuditVO_p,userVO_p);
	}

	/**
	 * Getting Patient Emr Audit Diagnosis Detail By Primary Key to show on patientEmrAuditDiagnosisDtlByPrimaryKey.jsp
	 * @param patientEmrAuditVO_p
	 * @param userVO_p
	 */
	public static boolean savePatientEmrAuditDtl( PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) {
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return (boolean)delegate.savePatientEmrAuditDtl(patientEmrAuditVO_p,userVO_p);
	}

	/**
	 * Getting Previous Patient Emr Audit Detail By Primary Key to show on patientEmrAuditDiagnosisDtlByPrimaryKey.jsp
	 * @param patientEmrAuditVO_p
	 * @param userVO_p
	 */
	public static List<PatientEmrAuditVO> getPreviousPatientEmrAuditDtlByPrimaryKey(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) {
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return (List<PatientEmrAuditVO>)delegate.getPreviousPatientEmrAuditDtlByPrimaryKey(patientEmrAuditVO_p,userVO_p);
	}

}
