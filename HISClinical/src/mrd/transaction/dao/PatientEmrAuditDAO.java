package mrd.transaction.dao;
/**
 * @author : Adil Wasi
 * Creation Date: 06-Jun-2012
 */
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import mrd.MrdConfig;
import mrd.vo.PatientEmrAuditVO;

public class PatientEmrAuditDAO extends DataAccessObject{

	public PatientEmrAuditDAO(TransactionContext _tx) {
		super(_tx);
	}
	
	/**
	 * Getting List of Patient Emr Audit 
	 * @param hisDAO_p
	 * @param strMode_p  1-Patient EMR Audit Type list, 
	 * @param List of Patient EMR Audit Type list
	 * @return
	 * @throws Exception
	 * @author Adil on 06-June-2012
	 */
	public List getAuditTypeWiseList(HisDAO hisDAO_p, String strMode_p, UserVO userVO_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROCEDURE_GET_AUDIT_TYPE_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,2); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,3); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientEmrAuditDAO.getAuditTypeWiseList()::hisDAO_p.executeProcedure(" + MrdConfig.PROCEDURE_GET_AUDIT_TYPE_LIST 
					+ ",Mode:" + strMode_p + ") -> " + e.getMessage()); 
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(objResSet);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientEmrAuditDAO:HelperMethodsDAO.getAlOfEntryObjects(objResSet)" + e);
		}

		return alRecord;
	}
	
	/**
	 * Getting List of All Audit User Names
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param patientEmrAuditVO_p
	 * @param userVO_p
	 * @return
	 * @throws Exception
	 * @author Adil Wasi on 07-jun-2012
	 */
	public List<PatientEmrAuditVO> getAuditUserList(HisDAO hisDAO_p, String strMode_p, PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROCEDURE_GET_AUDIT_USER_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1", 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", userVO_p.getHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_audit_type_id", (patientEmrAuditVO_p.getStrEmrAuditTypeId()==null) ? "":(patientEmrAuditVO_p.getStrEmrAuditTypeId()), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (userVO_p.getSeatId()==null) ? "":userVO_p.getSeatId(), 4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 6); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientEmrAuditDAO.getAuditUserList()::hisDAO_p.executeProcedure(" + MrdConfig.PROCEDURE_GET_AUDIT_USER_LIST 
					+ ",Mode:" + strMode_p + ") -> " + e.getMessage());
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		List<PatientEmrAuditVO> lst = new ArrayList<PatientEmrAuditVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PatientEmrAuditVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((PatientEmrAuditVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientEmrAudit.getAuditUserList()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Getting List of All Patient Emr Audit Dtl By CrNo
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param patientEmrAuditVO_p
	 * @param userVO_p
	 * @author Adil Wasi on 07-jun-2012
	 */
	public List<PatientEmrAuditVO> getPatientEmrAuditDtlByCrNo(HisDAO hisDAO_p, PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) {
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROCEDURE_GET_AUDIT_PATIENT_LIST);

			// Setting and Registering In and Out Parameters 
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1", 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", userVO_p.getHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_audit_type_id", (patientEmrAuditVO_p.getStrEmrAuditTypeId()==null) ? "":(patientEmrAuditVO_p.getStrEmrAuditTypeId()), 3);
			if(patientEmrAuditVO_p.getStrLitingType().equals("0")){
				hisDAO_p.setProcInValue(nProcedureIndex, "p_patCrNo", patientEmrAuditVO_p.getPatCrNo(), 4);
			}else{
				hisDAO_p.setProcInValue(nProcedureIndex, "p_patCrNo", "", 4);
			}
			hisDAO_p.setProcInValue(nProcedureIndex, "p_from_date", patientEmrAuditVO_p.getStrFromDate(), 5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_to_date", patientEmrAuditVO_p.getStrToDate(), 6);
			if(patientEmrAuditVO_p.getStrLitingType().equals("0")){
				hisDAO_p.setProcInValue(nProcedureIndex, "p_audit_user_seat_id", "", 7);
			}else{
				hisDAO_p.setProcInValue(nProcedureIndex, "p_audit_user_seat_id", patientEmrAuditVO_p.getStrEmrAuditUserId(), 7);
			}
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 8); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 9); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientEmrAuditDAO.getAuditUserListList()::hisDAO_p.executeProcedure(" + MrdConfig.PROCEDURE_GET_AUDIT_USER_LIST 
					+ ") -> " + e.getMessage());
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		List<PatientEmrAuditVO> lstPatientEmrAuditVO = new ArrayList<PatientEmrAuditVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PatientEmrAuditVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lstPatientEmrAuditVO.add((PatientEmrAuditVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientEmrAudit.getPatientEmrAuditDtlByCrNo()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lstPatientEmrAuditVO;
	}

	/**
	 * Getting List of All Patient Emr Audit Dtl By Primary Key
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param patientEmrAuditVO_p
	 * @param userVO_p
	 * @author Adil Wasi on 07-jun-2012
	 */
	public List<List<String>> showPatientEmrAuditDiagnosisDialTileByPrimaryKey(
			HisDAO hisDAO_p, PatientEmrAuditVO patientEmrAuditVO_p,
			UserVO userVO_p) {
		int nProcedureIndex;
		String strDBErr;
		//String[] strPrimaryKey=patientEmrAuditVO_p.getStrPrimaryKey().split("\\#");
	
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROCEDURE_GET_AUDIT_PATIENT_DIGNOSIS_DIAL_BY_PRIMARY_KEY_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1", 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", userVO_p.getHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_audit_type_id", (patientEmrAuditVO_p.getStrEmrAuditTypeId()==null) ? "":(patientEmrAuditVO_p.getStrEmrAuditTypeId()), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_patCrNo", patientEmrAuditVO_p.getPatCrNo(), 4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_from_date", patientEmrAuditVO_p.getStrFromDate(), 5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_to_date", patientEmrAuditVO_p.getStrToDate(), 6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_primary_key", patientEmrAuditVO_p.getStrPrimaryKey(), 7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (userVO_p.getSeatId()==null) ? "":userVO_p.getSeatId(), 8);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 9); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 10); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientEmrAuditDAO.showPatientEmrAuditDiagnosisDialTileByPrimaryKey()::hisDAO_p.executeProcedure(" + MrdConfig.PROCEDURE_GET_AUDIT_PATIENT_DIGNOSIS_DIAL_BY_PRIMARY_KEY_LIST 
					+ ") -> " + e.getMessage());
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}

		List<List<String>> lstOfLstPatientEmrAudit = new ArrayList<List<String>>();
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				ResultSetMetaData rsm=objResSet.getMetaData();
				List<String> lstColumnLabel = new ArrayList<String>();
				int count=rsm.getColumnCount();
				for(int i=1; i<=count; i++)
				{
					lstColumnLabel.add(rsm.getColumnName(i));
				}
				
				lstOfLstPatientEmrAudit.add(lstColumnLabel);
				
				
				objResSet.beforeFirst();
				while(objResSet.next())
				{
					List<String> lstPatientEmrAudit = new ArrayList<String>();
					for(int i=1;i<=count;i++)
					{
						lstPatientEmrAudit.add(objResSet.getString(i));
					}
					lstOfLstPatientEmrAudit.add(lstPatientEmrAudit);
				}

			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientEmrAuditDAO.showPatientEmrAuditDiagnosisDialTileByPrimaryKey()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lstOfLstPatientEmrAudit;
	}

	/**
	 * Save Patient Emr Audit Detail
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param patientEmrAuditVO_p
	 * @param userVO_p
	 * @author Adil Wasi on 07-jun-2012
	 */
	public boolean savePatientEmrAuditDtl(HisDAO hisDAO_p,PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) {
		int nProcedureIndex;
		String strDBErr;
		boolean insertSuccesfullFlag=true;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROCEDURE_SAVE_PATIENT_EMR_AUDIT_DTL);
			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1", 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_patCrNo", patientEmrAuditVO_p.getPatCrNo(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_episode_code", patientEmrAuditVO_p.getStrEpisodeCode(), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_episode_visit_no", patientEmrAuditVO_p.getStrVisitNo(), 4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_audit_type_id", patientEmrAuditVO_p.getStrEmrAuditTypeId(), 5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_admission_no", patientEmrAuditVO_p.getStrAdmissionNo(), 6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_deo_seat_id", patientEmrAuditVO_p.getStrDEOSeatId(), 7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_deo_dateTime", patientEmrAuditVO_p.getStrDeoDateTime(), 8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_audit_status", patientEmrAuditVO_p.getStrAuditStatusId(), 9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_remarks", patientEmrAuditVO_p.getStrRemark(), 10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", userVO_p.getSeatId(), 11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", userVO_p.getHospitalCode(), 12);
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 13); // varchar
						// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			
			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				insertSuccesfullFlag=false;
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			insertSuccesfullFlag=false;
			throw new HisDataAccessException("PatientEmrAuditDAO.savePatientEmrAuditDtl()::hisDAO_p.executeProcedure(" + MrdConfig.PROCEDURE_SAVE_PATIENT_EMR_AUDIT_DTL 
					+  ") -> " + e.getMessage()); 
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		return insertSuccesfullFlag;
	}

	/**
	 * Getting List of Previous Patient Emr Audit Dtl By PrimaryKey
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param patientEmrAuditVO_p
	 * @param userVO_p
	 * @author Adil Wasi on 07-jun-2012
	 */
	public List<PatientEmrAuditVO> getPreviousPatientEmrAuditDtlByPrimaryKey(HisDAO hisDAO_p, PatientEmrAuditVO patientEmrAuditVO_p,UserVO userVO_p) {
		
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROCEDURE_GET_PATIENT_EMR_PREVIOUS_AUDIT_DTL_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1", 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", userVO_p.getHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_audit_type_id", (patientEmrAuditVO_p.getStrEmrAuditTypeId()==null) ? "":(patientEmrAuditVO_p.getStrEmrAuditTypeId()), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_primary_key", patientEmrAuditVO_p.getStrPrimaryKey(), 4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 6); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientEmrAuditDAO.getPreviousPatientEmrAuditDtlByPrimaryKey()::hisDAO_p.executeProcedure(" + MrdConfig.PROCEDURE_GET_PATIENT_EMR_PREVIOUS_AUDIT_DTL_LIST 
					+ ") -> " + e.getMessage());
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		List<PatientEmrAuditVO> lstPatientEmrAuditVO = new ArrayList<PatientEmrAuditVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PatientEmrAuditVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lstPatientEmrAuditVO.add((PatientEmrAuditVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientEmrAudit.getPreviousPatientEmrAuditDtlByPrimaryKey()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lstPatientEmrAuditVO;
	}

	
}
