package opd.dao;
/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	
## Module Name					: 	OPD
## Process/Database Object Name	:	
## Purpose						:	
## Date of Creation				: 	
## Modification Log				:	removed Funtions (1. getEpisodeDiagnosisDetail(), 2. getEpisodeDrugDetail(), 3. getPatAllergiesDetails(), 4. getPatChronicDetails())				
##		Modify Date				: 	12-Jan-2014
##		Reason	(CR/PRS)		:	To remove all patient Centric DATA function (like Alert Detail, Diagnosis Detail, Chronic Detail etc.......) & move it to EMRPatientDetailDAO
##		Modify By				:	AKASH SINGH
*/
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.EpisodeReferVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import opd.OpdConfig;

public class OpdRegEssentialDAO //extends DataAccessObject
{
	/*Logger log;

	*//**
	 * Constructor for Setting Transaction Context
	 *//*
	public OpdRegEssentialDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}*/
	
	

	/*public PatientDetailVO getDailyPatientDetail(HisDAO daoObj, String mode, PatientDetailVO patientDetailVO, UserVO userVO)
	{
		WebRowSet rs = null;
		ValueObject valueObjects = null;
		PatientDetailVO voPatientDetail= null;
		String strProcName = "{call PKG_DYNAMIC_DESK_VIEW.get_patient_summary(?,?,?,?,?,?,?.?)}";
		int nProcIndex = 0;
		String strErr = "";
		try
		{
			System.out.println("episodeCode"+patientDetailVO.getEpisodeCode());
			System.out.println("EpisodeVisitNo"+patientDetailVO.getEpisodeVisitNo());
			System.out.println("PatCardNo"+patientDetailVO.getPatCrNo());
			System.out.println("HospitalCode"+userVO.getHospitalCode());
			System.out.println("isValid"+Config.IS_VALID_ACTIVE);
			
			daoObj = new HisDAO("OpdDesk","OpdEssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_mode", mode, 1);
			daoObj.setProcInValue(nProcIndex, "episode_code", patientDetailVO.getEpisodeCode(),2);
			daoObj.setProcInValue(nProcIndex, "episode_visit_no", patientDetailVO.getEpisodeVisitNo(),3);
			daoObj.setProcInValue(nProcIndex, "pat_cr_no", patientDetailVO.getPatCrNo(),4);
			daoObj.setProcInValue(nProcIndex, "hosp_code",userVO.getHospitalCode(),5);
			daoObj.setProcInValue(nProcIndex, "is_valid", Config.IS_VALID_ACTIVE,6);
			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println("strErr----------------------->"+strErr);
				
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
					voPatientDetail = (PatientDetailVO) valueObjects;
				
			}
			
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("OpdEssentialDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return voPatientDetail;
	}*/

	public List<PatientDetailVO> getDailyPatientDetail(HisDAO hisDAO_p, String strMode_p, PatientDetailVO patientDetailVO, UserVO userVO) 
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{

			//String strProcName = "{call PKG_DYNAMIC_DESK_VIEW.get_patient_summary(?,?,?,?,?,?,?.?)}";
			nProcedureIndex = hisDAO_p.setProcedure(OpdConfig.PROC_GET_DAILY_PATIENT_DETAIL);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "episode_code", patientDetailVO.getEpisodeCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "episode_visit_no", patientDetailVO.getEpisodeVisitNo(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", patientDetailVO.getPatCrNo(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7); // Cursor

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
			throw new HisDataAccessException("OpdRegEssentialDAO.getDailyPatientDetail1()::hisDAO_p.execute" + OpdConfig.PROC_GET_DAILY_PATIENT_DETAIL 
					+ ") -> " + e.getMessage());
		}
		finally{
			if(hisDAO_p!=null){
				hisDAO_p.free();
			}
			hisDAO_p=null;
		}
			
		List<PatientDetailVO> lst = new ArrayList<PatientDetailVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PatientDetailVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((PatientDetailVO) obj);
			}
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("OpdRegEssentialDAO.getDailyPatientDetail1()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	public List<PatientDetailVO> getPatientVisitDetail(HisDAO hisDAO_p, String strMode_p, PatientDetailVO patientDetailVO, UserVO userVO) 
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{

			//String strProcName = "{call PKG_DYNAMIC_DESK_VIEW.get_patient_summary(?,?,?,?,?,?,?.?)}";
			nProcedureIndex = hisDAO_p.setProcedure(OpdConfig.PROC_GET_PATIENT_VISIT_DTL);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "episode_code", patientDetailVO.getEpisodeCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", patientDetailVO.getPatCrNo(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor

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
			throw new HisDataAccessException("OpdRegEssentialDAO.getDailyPatientDetail1()::hisDAO_p.execute" + OpdConfig.PROC_GET_PATIENT_VISIT_DTL 
					+ ") -> " + e.getMessage());
		}
		finally{
			if(hisDAO_p!=null){
				hisDAO_p.free();
			}
			hisDAO_p=null;
		}
		List<PatientDetailVO> lst = new ArrayList<PatientDetailVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PatientDetailVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((PatientDetailVO) obj);
			}
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("OpdRegEssentialDAO.getDailyPatientDetail1()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	public List<EpisodeReferVO> getPatientReferredDetail(HisDAO hisDAO_p, String strMode_p, PatientDetailVO patientDetailVO, UserVO userVO) 
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(OpdConfig.PROC_GET_PATIENT_REFERRED_DTL);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "episode_code", patientDetailVO.getEpisodeCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", patientDetailVO.getPatCrNo(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor

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
			throw new HisDataAccessException("OpdRegEssentialDAO.getPatientReferDetail()::hisDAO_p.execute" + OpdConfig.PROC_GET_PATIENT_REFERRED_DTL 
					+ ") -> " + e.getMessage());
		}
		finally{
			if(hisDAO_p!=null){
				hisDAO_p.free();
			}
			hisDAO_p=null;
		}
		List<EpisodeReferVO> lst = new ArrayList<EpisodeReferVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(EpisodeReferVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((EpisodeReferVO) obj);
			}
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("OpdRegEssentialDAO.getPatientReferDetail()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	public List<PatientDetailVO> getDeskPatDtl(HisDAO hisDAO_p, String strMode_p, PatientDetailVO patientDetailVO, UserVO userVO, String deskType) 
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			
			nProcedureIndex = hisDAO_p.setProcedure(DynamicDeskConfig.GET_DESK_PATIENT_DTL);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "desk_type", deskType,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", patientDetailVO.getPatCrNo(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "episode_code", patientDetailVO.getEpisodeCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "episode_visit_no", patientDetailVO.getEpisodeVisitNo(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "admin_no",((patientDetailVO.getPatAdmNo()==null) ? " " : patientDetailVO.getPatAdmNo()),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "dept_unit_code",patientDetailVO.getDepartmentUnitCode(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "room_code",patientDetailVO.getRoomCode(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "ward_code",((patientDetailVO.getWardCode()==null) ? " " : patientDetailVO.getWardCode()),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "bed_code",((patientDetailVO.getBedCode()==null) ? " " : patientDetailVO.getBedCode()),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),11);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,12); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,13); // Cursor

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
			throw new HisDataAccessException("OpdRegEssentialDAO.getDeskPatDtl()::hisDAO_p.execute" + DynamicDeskConfig.GET_DESK_PATIENT_DTL 
					+ ") -> " + e.getMessage());
		}
		finally{
			if(hisDAO_p!=null){
				hisDAO_p.free();
			}
			hisDAO_p=null;
		}
		List<PatientDetailVO> lst = new ArrayList<PatientDetailVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PatientDetailVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((PatientDetailVO) obj);
			}
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("OpdRegEssentialDAO.getDeskPatDtl()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
}