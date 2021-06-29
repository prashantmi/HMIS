/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.treatmentdetail.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DrugDoseVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.DrugRouteMstVO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ehr.EHRConfig;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import opd.OpdConfig;
import opd.dao.OpdDaoConfig;

public class EHRSection_TreatmentDAO extends DataAccessObject {
	
		
	Logger log;

	
	public EHRSection_TreatmentDAO(JDBCTransactionContext _transactionContext) {
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());

		// TODO Auto-generated constructor stub
	}


	public List<Entry> getGenericDrugListDetail(HisDAO hisDAO_p, String mode, PatientDetailVO _patDetailVO, UserVO _userVO)
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			
			//nProcedureIndex = hisDAO_p.setProcedure(OpdConfig.PROC_OPD_VIEW_GENERIC_DRUGS_LIST);  //USED FOR GENERIC DRUG LIST 
			nProcedureIndex = hisDAO_p.setProcedure(OpdConfig.PROC_OPD_VIEW_GENERICBRAND_BYCONFIG_DRUGS_LIST); // DATE: 21.7.2016 // ADDED FOR MAKING DRUG LIST CONFIGURABLE 
			
			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", mode,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_ward_code", (_patDetailVO.getWardCode()==null)?"":_patDetailVO.getWardCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_unit_code", (_patDetailVO.getDepartmentUnitCode()==null)?"":_patDetailVO.getDepartmentUnitCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (_userVO.getSeatId()==null)?"":_userVO.getSeatId(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hosp_code", _userVO.getHospitalCode(),5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No further processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO.getDrugListDetail::hisDAO_p.execute" + OpdConfig.PROC_OPD_VIEW_GENERICBRAND_BYCONFIG_DRUGS_LIST
					+ ") -> " + e.getMessage());
		}
		

		
		List<Entry> lst = new ArrayList<Entry>();
		try
		{
			lst = HelperMethodsDAO.getAlOfEntryObjects(objResSet);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("OpdEssentialDAO.getDrugListDetail::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
	
	public List<DrugDoseVO> getDrugDosesList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HGBT_DRUG_DOSE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO.getDosageFrequecy::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException( "No Drug Doses Exists  ");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<DrugDoseVO> lstDrugDoses = new ArrayList();
		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(DrugDoseVO.class, rs);
				for(ValueObject v : vo)
					lstDrugDoses.add((DrugDoseVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstDrugDoses;
	}
	
	
	public List<Entry> getDrugAdministrationFlagsList(HisDAO hisDAO_p, String pmode)
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(OpdConfig.PROC_OPD_VIEW_DRUG_ADMIN_FLAG_LIST);
			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,2); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,3); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No further processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_TreatmentDAO.getDrugAdminFlagList::hisDAO_p.execute" + OpdConfig.PROC_OPD_VIEW_DRUG_ADMIN_FLAG_LIST
					+ ") -> " + e.getMessage());
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		List<Entry> lst = new ArrayList<Entry>();
		try
		{
			lst = HelperMethodsDAO.getAlOfEntryObjects(objResSet);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("EHRSection_TreatmentDAO.getDrugAdminFlagList::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
	
	public List getPrevDrugSchedule(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREV_DRUG_SCHEDULE.HRGT_EPISODE_DRUG_SCHEDULE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());


		List _prevDrugScheduleVO = new ArrayList();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DrugSheduleDtlVO.class, rs);			
				for (ValueObject v : vo)
					_prevDrugScheduleVO.add((DrugSheduleDtlVO)v);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return _prevDrugScheduleVO;
	}

	
	
	/**
	 * Getting Previous Drug Detail
	 * @param _patCrNo Cr No.
	 * @param _episodeCode Episode Code 
	 * @param _userVO User VO
	 * @return List of Drug Treatment Detail
	 */
	public List<EHRSection_TreatmentVO> getPrevPatDrugDetail(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS_DRUG_DETAIL.HRGT_EPISODE_DRUG_DTL_SPD";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		//populateMAP.put(sq.next(), OpdConfig.ENTRY_MODE);
		
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		//populateMAP.put(sq.next(), _patCrNo);
		//populateMAP.put(sq.next(), _episodeCode);
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		List<EHRSection_TreatmentVO> _previousEpisodeDiagVO = new ArrayList<EHRSection_TreatmentVO>();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(EHRSection_TreatmentVO.class, rs);			
				for (ValueObject v : vo)
					_previousEpisodeDiagVO.add((EHRSection_TreatmentVO)v);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return _previousEpisodeDiagVO;
	}
	
	
	public List<EHRSection_TreatmentVO> getPrevAdviceOnDischargeDrugDetail_(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS_DISCHARGEADVICE_DRUG_DETAIL.HRGT_EPISODE_DRUG_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.TREATMENT_ADVICE_TYPE_DISCHARGE);  // advice type for advice on discharge
			

		List<EHRSection_TreatmentVO> _previousEpisodeDiagVO = new ArrayList<EHRSection_TreatmentVO>();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(EHRSection_TreatmentVO.class, rs);			
				for (ValueObject v : vo)
					_previousEpisodeDiagVO.add((EHRSection_TreatmentVO)v);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return _previousEpisodeDiagVO;
	}
	
	
	public List<EHRSection_TreatmentVO> getPrevPatDrugDetailForLog(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS_DRUG_DETAIL_FOR_LOG.HRGT_EPISODE_DRUG_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		//populateMAP.put(sq.next(), OpdConfig.ENTRY_MODE);
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		//populateMAP.put(sq.next(), OpdConfig.ENTRY_MODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		List<EHRSection_TreatmentVO> _previousEpisodeDiagVO = new ArrayList<EHRSection_TreatmentVO>();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(EHRSection_TreatmentVO.class, rs);			
				for (ValueObject v : vo)
					_previousEpisodeDiagVO.add((EHRSection_TreatmentVO)v);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return _previousEpisodeDiagVO;
	}
	
	
	
	
	
	public List<Entry> getAllOtherInstructionList(String genderFlag,String deptUnitCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_OTHER_INSTRUCTION_LIST.HGBT_EXT_TREATMENT_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{
			//populateMAP.put(sq.next(),deptUnitCode);
			populateMAP.put(sq.next(), OpdConfig.OTHER_INSTRUCTION);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),OpdConfig.GENDER_FLAG_FOR_BOTH);
			populateMAP.put(sq.next(),genderFlag);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO.getDosageFrequecy::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException( "No EXT Treatment Exists  ");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return alRecord;
	}
	
	
	// Added by Manisha Gangwar Date: 13.04.2016 for Drug Alerts (Allergies, Reaction, Contradiction, Pregnancy ALert for Reactive Drug)
	
		public String getdrugAdviceAlerts(String pmode, HisDAO hisDAO_p, EHRSection_TreatmentVO patDrugDtlVO,UserVO _userVO)
		{
					
			int nFuntionIndex;
			//String strDBErr;
			//ResultSet objResSet;
			String strAlerts=null;
			try
			{
				nFuntionIndex = hisDAO_p.setFunction(OpdConfig.FUNC_EMR_VIEW_PAT_DRUG_ADVICE_ALERTS);
				// Setting and Registering In and Out Parameters 
				hisDAO_p.setFuncOutValue(nFuntionIndex, 1);
				hisDAO_p.setFuncInValue(nFuntionIndex, 2, pmode);
				hisDAO_p.setFuncInValue(nFuntionIndex, 3, _userVO.getHospitalCode());
				hisDAO_p.setFuncInValue(nFuntionIndex, 4, patDrugDtlVO.getPatCrNo());
				hisDAO_p.setFuncInValue(nFuntionIndex, 5, patDrugDtlVO.getDrugId());
				hisDAO_p.setFuncInValue(nFuntionIndex, 6, patDrugDtlVO.getDrugName());
				hisDAO_p.setFuncInValue(nFuntionIndex, 7, patDrugDtlVO.getEpisodeCode());
				hisDAO_p.setFuncInValue(nFuntionIndex, 8, OpdConfig.IS_EPISODE_OPEN);
				hisDAO_p.setFuncInValue(nFuntionIndex, 9, patDrugDtlVO.getDepartmentUnitCode());
				hisDAO_p.setFuncInValue(nFuntionIndex, 10, patDrugDtlVO.getAdmissionNo());
				hisDAO_p.setFuncInValue(nFuntionIndex, 11, OpdConfig.REVOKE);
				hisDAO_p.setFuncInValue(nFuntionIndex, 12, Config.IS_VALID_ACTIVE);
				hisDAO_p.setFuncInValue(nFuntionIndex, 13, Config.ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC);
						
					
				// Executing function
				hisDAO_p.executeFunction(nFuntionIndex);
				strAlerts = hisDAO_p.getFuncString(nFuntionIndex);
				
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				
				else throw new HisDataAccessException("OpdEssentialDAO.getdrugAdviceAlerts::hisDAO_p.execute" + OpdConfig.FUNC_EMR_VIEW_PAT_DRUG_ADVICE_ALERTS
						+ ") -> " + e.getMessage());
			}
			finally {
				if (hisDAO_p != null) {
					hisDAO_p.free();
					
				}
				hisDAO_p = null;
			}
			
			return strAlerts;
			
			
		}
			
			
		
		public List<EHRSection_TreatmentVO> getParticularDrugListDetail(String drugListId, UserVO _userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			String queryKey = "SELECT.DRUG_DETAIL_LIST.HGBT_DRUGLIST_UNITWISE_MST";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			populateMAP.put(sq.next(), drugListId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
			List<EHRSection_TreatmentVO> _previousEpisodeDiagVO = new ArrayList<EHRSection_TreatmentVO>();
			ValueObject vo[] = null;
			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (rs.next())
				{
					rs.beforeFirst();
		
					vo = HelperMethods.populateVOfrmRS(EHRSection_TreatmentVO.class, rs);			
					for (ValueObject v : vo)
						_previousEpisodeDiagVO.add((EHRSection_TreatmentVO)v);
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);
			}
			return _previousEpisodeDiagVO;
		}

		

		public List<EHRSection_TreatmentVO> getDefaultDrugProfileForUnit(String depUnitCode, UserVO _userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			String queryKey = "SELECT.DRUG_PROFILE.HGBT_DRUGLIST_UNITWISE_MST";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			populateMAP.put(sq.next(), depUnitCode);
			populateMAP.put(sq.next(), OpdConfig.IS_DEFAULT_YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
			List<EHRSection_TreatmentVO> _previousEpisodeDiagVO = new ArrayList<EHRSection_TreatmentVO>();
			ValueObject vo[] = null;
			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (rs.next())
				{
					rs.beforeFirst();
		
					vo = HelperMethods.populateVOfrmRS(EHRSection_TreatmentVO.class, rs);			
					for (ValueObject v : vo)
						_previousEpisodeDiagVO.add((EHRSection_TreatmentVO)v);
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);
			}
			return _previousEpisodeDiagVO;
		}
		
		
		public List<Entry> getDosageFrequecy(UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			String queryKey = "ESSENTIAL.HGBT_DRUG_FREQUENCY_MST";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			
			Sequence sq = new Sequence();
			try
			{
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("OpdEssentialDAO.getDosageFrequecy::populateMAP" + e);
			}

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				//if (!rs.next()) throw new HisRecordNotFoundException( "No Dosage Frequecy Exists  ");
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}

			List alRecord = new ArrayList();
			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
			}
			return alRecord;
		}
		
		public DrugFrequencyMstVO[] getDrugFrequencyVOList(UserVO userVO)
		{
			String query = "";
			ResultSet rs;
			Map populateMAP = new HashMap();
			ValueObject[] vo={};
			DrugFrequencyMstVO[] drugFrequencyMstVO;
			Sequence sq = new Sequence();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			String queryKey = "SELECT_DRUG_FREQ_VO_LIST.HGBT_DRUG_FREQUENCY_MST";
			
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
			
			
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);

			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
			}
			try
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(DrugFrequencyMstVO.class,rs);
				drugFrequencyMstVO=new DrugFrequencyMstVO[vo.length];
				for(int i=0;i<vo.length;i++)
				{
					drugFrequencyMstVO[i]=(DrugFrequencyMstVO)vo[i];
				}
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("RegistrationTimingMasterDAO::getEssentialForRegTiming"+e);
			}
			return drugFrequencyMstVO;
		}
		
		
		
		public String getMaxEntryDateFromDrugDetail(String _patCrNo,String _episodeCode,UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			String queryKey = "ESSENTIAL.MAX_SLNO.HRGT_EPISODE_DRUG_DTL";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			
			Sequence sq = new Sequence();
			try
			{	
				populateMAP.put(sq.next(), _patCrNo);
				populateMAP.put(sq.next(), _episodeCode);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				
				populateMAP.put(sq.next(), _patCrNo);
				populateMAP.put(sq.next(), _episodeCode);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("OpdEssentialDAO.getDosageFrequecy::populateMAP" + e);
			}

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				rs.first();
				/*
				if (!rs.next()) 
				{
					throw new HisRecordNotFoundException( " ");
				}
				*/
				return rs.getString(1); 
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}

			
		}	
		
		
		public String getMaxSlNo(EHRSection_TreatmentVO _patDrugDtlVO, UserVO userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT_MAX_SLNO.HRGT_EPISODE_DRUG_SCHEDULE_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
			// populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("No record found");
				}
				return rs.getString(1);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);
			}

		}
		
	
		public void updateLastTodayVisitRecord(EHRSection_TreatmentVO _patDrugDtlVO, UserVO _userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();

			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			//String queryKey = "UPDATE.TODAY_VISIT_RECORD.HRGT_EPISODE_DRUG_DTL";
			String queryKey = "UPDATE.HRGT_EPISODE_DRUG_DTL_FOR_SINGLE_PAGE";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			Sequence sq = new Sequence();

			try
			{
				populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
				// populateMAP.put(sq.next(), _patDrugDtlVO.getEndDate());
				populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
				populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
				populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
				populateMAP.put(sq.next(), _patDrugDtlVO.getSerialNo());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//	populateMAP.put(sq.next(), _patDrugDtlVO.getEntryDate());

			}
			catch (Exception e)
			{
				throw new HisDataAccessException("PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
			}

			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}

		
		public void saveRevokePatDrugTreatmentDetail(EHRSection_TreatmentVO _patDrugDtlVO, UserVO _userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();

			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "INSERT.REVOKED_TREATMENT.HRGT_EPISODE_DRUG_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			Sequence sq = new Sequence();

			try
			{
				populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
				populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
				populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
				// sereal No
				populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
				populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
				populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());

				if (_patDrugDtlVO.getAdmissionNo() == null) _patDrugDtlVO.setAdmissionNo("");
				populateMAP.put(sq.next(), _patDrugDtlVO.getAdmissionNo());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugName());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugBrandId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDoseId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDoseName());
				populateMAP.put(sq.next(), _patDrugDtlVO.getFrequencyId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDays());

				// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTime());
				populateMAP.put(sq.next(), _patDrugDtlVO.getStartDate());
				// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTimeHrs());
				// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTimeMin());

				populateMAP.put(sq.next(), _patDrugDtlVO.getEndDate());
				// populateMAP.put(sq.next(), _patDrugDtlVO.getDays());
				populateMAP.put(sq.next(), _patDrugDtlVO.getRemarks());
				populateMAP.put(sq.next(), _userVO.getUserEmpID());
				// populateMAP.put(sq.next(), OpdConfig.TREATMENT_DETAIL_RX_CONTINUE_NEW);
				populateMAP.put(sq.next(), _patDrugDtlVO.getRxContinue());
				populateMAP.put(sq.next(), _userVO.getSeatId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getEntryDate());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), _patDrugDtlVO.getIsEmptyStomach());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugRouteId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDoseQty());
				populateMAP.put(sq.next(), _patDrugDtlVO.getIssueQty());
				populateMAP.put(sq.next(), _patDrugDtlVO.getRequiredQty());
				populateMAP.put(sq.next(), OpdConfig.ENTRY_MODE);
			 //Added  By Pawan Kumar B N on 19-Feb-2013
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugTypeID());
				populateMAP.put(sq.next(), _patDrugDtlVO.getRevokeRemarks());  
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
			}

			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}

		
		public String getConsentStatus(EHRSection_TreatmentVO patDrugDtlVO, UserVO _userVO)
		{
			String query = "";
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT_DRUG_CONSENT_STATUS.HGBT_CONSENT_MAPPING";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			populateMAP.put(sq.next(), patDrugDtlVO.getDrugId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.SERVICE_TYPE_FOR_CONSENT);

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())
				{
					return "0";
					// throw new HisRecordNotFoundException("No Drug Allergy Is Here");

				}
				return rs.getString(1);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
			}

		}

		public void generatConsent(EHRSection_TreatmentVO patDrugDtlVO, UserVO _userVO)
		{
			String errorMsg = null;
			
			/////////////
			String query = "";
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT.HGNUM_TEMPLATE_ID.HGBT_CONSENT_MAPPING";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			populateMAP.put(sq.next(), OpdConfig.SERVICE_TYPE_FOR_CONSENT); // service Type
			populateMAP.put(sq.next(), patDrugDtlVO.getDrugId()); 			// service Id
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("No Template Exist");

				}else{
					rs.beforeFirst();
					String templateId="";
					while(rs.next()){
						templateId=rs.getString(1);
						
						Procedure strProc = new Procedure(OpdDaoConfig.PROCEDURE_GENERATE_CONSENT);
						strProc.addInParameter(1, Types.NUMERIC, _userVO.getHospitalCode());
						strProc.addInParameter(2, Types.NUMERIC, _userVO.getSeatId());
						strProc.addInParameter(3, Types.VARCHAR, _userVO.getUserEmpID());
						strProc.addInParameter(4, Types.NUMERIC, OpdConfig.SERVICE_TYPE_FOR_CONSENT);// service Type
						strProc.addInParameter(5, Types.VARCHAR, patDrugDtlVO.getDrugId());// service Id
						strProc.addInParameter(6, Types.VARCHAR, patDrugDtlVO.getEpisodeVisitNo());
						strProc.addInParameter(7, Types.NUMERIC, patDrugDtlVO.getPatCrNo());
						strProc.addInParameter(8, Types.NUMERIC, patDrugDtlVO.getEpisodeCode());
						strProc.addInParameter(9, Types.NUMERIC, patDrugDtlVO.getEpisodeVisitNo());
						strProc.addInParameter(10, Types.NUMERIC, templateId);

						strProc.execute(super.getTransactionContext().getConnection());
					}
				}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}

			/*try
			{
				Procedure strProc = new Procedure(OpdDaoConfig.PROCEDURE_GENERATE_CONSENT);
				strProc.addInParameter(1, Types.NUMERIC, _userVO.getHospitalCode());
				strProc.addInParameter(2, Types.NUMERIC, _userVO.getSeatId());
				strProc.addInParameter(3, Types.VARCHAR, _userVO.getUserEmpID());
				strProc.addInParameter(4, Types.NUMERIC, OpdConfig.SERVICE_TYPE_FOR_CONSENT);// service Type
				strProc.addInParameter(5, Types.VARCHAR, patDrugDtlVO.getDrugId());// service Id
				strProc.addInParameter(6, Types.VARCHAR, patDrugDtlVO.getEpisodeVisitNo());
				strProc.addInParameter(7, Types.NUMERIC, patDrugDtlVO.getPatCrNo());
				strProc.addInParameter(8, Types.NUMERIC, patDrugDtlVO.getEpisodeCode());
				strProc.addInParameter(9, Types.NUMERIC, patDrugDtlVO.getEpisodeVisitNo());

				strProc.execute(super.getTransactionContext().getConnection());

			}
			catch (HisException e)
			{
				throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}*/
		}

		
		
		public void savePatDrugTreatmentDetail(EHRSection_TreatmentVO _patDrugDtlVO, String pmode,UserVO _userVO)
		{
			int nProcedureIndex;
			HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_TreatmentDAO");
			String strDBErr;
			ResultSet objResSet;
			
			try
			{
								
			nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_TREATMENT_DETAILS);
			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_puk", (_patDrugDtlVO.getPatCrNo()==null) ? "":_patDrugDtlVO.getPatCrNo(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", (_patDrugDtlVO.getEpisodeCode()==null) ? "": _patDrugDtlVO.getEpisodeCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_visit_no",  (_patDrugDtlVO.getEpisodeVisitNo()==null) ? "":_patDrugDtlVO.getEpisodeVisitNo(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_admissionno", (_patDrugDtlVO.getAdmissionNo()==null) ? "":_patDrugDtlVO.getAdmissionNo(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_itemid",(_patDrugDtlVO.getDrugId()==null) ? "": _patDrugDtlVO.getDrugId(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_itembrandid",(_patDrugDtlVO.getDrugBrandId()==null) ? "": _patDrugDtlVO.getDrugBrandId(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_itembrandname",(_patDrugDtlVO.getDrugBrandName()==null) ? "": _patDrugDtlVO.getDrugBrandName(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_doseid",(_patDrugDtlVO.getDoseId()==null) ? "": _patDrugDtlVO.getDoseId(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dosename",(_patDrugDtlVO.getDoseName()==null) ? "": _patDrugDtlVO.getDoseName(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_frequency_id",(_patDrugDtlVO.getFrequencyId()==null) ? "": _patDrugDtlVO.getFrequencyId(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_days",(_patDrugDtlVO.getDays()==null) ? "": _patDrugDtlVO.getDays(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_requirementTime",(_patDrugDtlVO.getRequirmentTime()==null) ? "": _patDrugDtlVO.getRequirmentTime(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_start_day",(_patDrugDtlVO.getStartDate()==null) ? "": _patDrugDtlVO.getStartDate(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_endDate",(_patDrugDtlVO.getEndDate()==null) ? "": _patDrugDtlVO.getEndDate(),15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_remarks",(_patDrugDtlVO.getRemarks()==null) ? "": _patDrugDtlVO.getRemarks(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_no", (_userVO.getUserEmpID()==null) ? "": _userVO.getUserEmpID(),17);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_rx_continue",(_patDrugDtlVO.getRxContinue()==null) ? "": _patDrugDtlVO.getRxContinue(),18);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", _userVO.getSeatId(),19);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE, 20);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode",(_userVO.getHospitalCode()==null) ? "":  _userVO.getHospitalCode(),21);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_adminId",(_patDrugDtlVO.getDrugAdminId()==null) ? "": _patDrugDtlVO.getDrugAdminId(),22);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_routeId",(_patDrugDtlVO.getDrugRouteId()==null) ? "": _patDrugDtlVO.getDrugRouteId(),23);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_doseQty",(_patDrugDtlVO.getDoseQty()==null) ? "": _patDrugDtlVO.getDoseQty(),24);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_issueQty",(_patDrugDtlVO.getIssueQty()==null) ? "": _patDrugDtlVO.getIssueQty(),25);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_reqQty",(_patDrugDtlVO.getRequiredQty()==null) ? "": _patDrugDtlVO.getRequiredQty(),26);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_entryMode",OpdConfig.ENTRY_MODE,27);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_entryMode",_patDrugDtlVO.getEntryMode(),27);//Added by Vasu on 17.Dec.2018
			hisDAO_p.setProcInValue(nProcedureIndex, "p_itemTypeId",(_patDrugDtlVO.getDrugTypeID()==null) ? "": _patDrugDtlVO.getDrugTypeID(),28);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_revoke_remarks",(_patDrugDtlVO.getRevokeRemarks()==null) ? "": _patDrugDtlVO.getRevokeRemarks(),29);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_advice_type",(_patDrugDtlVO.getAdviceType()==null) ? "": _patDrugDtlVO.getAdviceType(),30);
			
			hisDAO_p.setProcOutValue(nProcedureIndex,"err", 1,31); // varchar
			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
		
			
			// If Database Error Occurs, No further processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("EHRSection_TreatmentDAO_Create::hisDAO_p.execute" + EHRConfig.SAVE_TREATMENT_DETAILS
						+ ") -> " + e.getMessage());
			}
			finally {
				if (hisDAO_p != null) {
					hisDAO_p.free();
					
				}
				hisDAO_p = null;
			}
		}
		
		
		
		public void savePatDrugTreatmentDetail1(EHRSection_TreatmentVO _patDrugDtlVO, UserVO _userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();

			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "INSERT.NEW.OPD.HRGT_EPISODE_DRUG_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			Sequence sq = new Sequence();

			try
			{
				populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
				populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
				populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
				populateMAP.put(sq.next(), _patDrugDtlVO.getPatCrNo());
				populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeCode());
				populateMAP.put(sq.next(), _patDrugDtlVO.getEpisodeVisitNo());
				if (_patDrugDtlVO.getAdmissionNo() == null) _patDrugDtlVO.setAdmissionNo("");
				populateMAP.put(sq.next(), _patDrugDtlVO.getAdmissionNo());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugName());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugBrandId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDoseId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDoseName());
				populateMAP.put(sq.next(), _patDrugDtlVO.getFrequencyId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDays());

				populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTime());
				populateMAP.put(sq.next(), _patDrugDtlVO.getStartDate());
				// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTimeHrs());
				// populateMAP.put(sq.next(), _patDrugDtlVO.getRequirmentTimeMin());

				populateMAP.put(sq.next(), _patDrugDtlVO.getStartDate());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDays());
				populateMAP.put(sq.next(), _patDrugDtlVO.getRemarks());
				populateMAP.put(sq.next(), _userVO.getUserEmpID());
				// populateMAP.put(sq.next(), OpdConfig.TREATMENT_DETAIL_RX_CONTINUE_NEW);
				populateMAP.put(sq.next(), _patDrugDtlVO.getRxContinue());
				populateMAP.put(sq.next(), _userVO.getSeatId());

				populateMAP.put(sq.next(), _patDrugDtlVO.getEntryDate());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugAdminId()); 
				//populateMAP.put(sq.next(), _patDrugDtlVO.getIsEmptyStomach());  // is empty stomach field is replaced by drug administration (after meal, before meal, with meal) 
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugRouteId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDoseQty());
				populateMAP.put(sq.next(), _patDrugDtlVO.getIssueQty());
				populateMAP.put(sq.next(), _patDrugDtlVO.getRequiredQty());
				populateMAP.put(sq.next(), OpdConfig.ENTRY_MODE);
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugId());
				populateMAP.put(sq.next(), _patDrugDtlVO.getDrugTypeID());
				populateMAP.put(sq.next(), _patDrugDtlVO.getRevokeRemarks());  
				

			}
			catch (Exception e)
			{
				throw new HisDataAccessException("PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
			}

			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}


		public List getDrugRouteList(UserVO _userVO) 
		{	
			

			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			
			String queryKey = "ESSENTIAL.LIST.HGBT_DRUG_ROUTE_MST";
			//String queryKey = "ESSENTIAL.LIST.CIMS_DRUG_ADMIN_ROUTE_MST_SYS";
			
			//String queryKey = "ESSENTIAL.LIST.HGBT_DRUG_ROUTE_MST";  // commented for CIMS Drug Route Integration  on 07.04.2016
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			
			Sequence sq = new Sequence();
			try
			{	
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
				
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("OpdEssentialDAO.getDosageFrequecy::populateMAP" + e);
			}

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				//if (!rs.next()) throw new HisRecordNotFoundException( "No Drug Doses Exists  ");
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}

			List lstDrugRoutes = new ArrayList();
			ValueObject[] vo = null;
			try
			{
				if (rs.next())
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(DrugRouteMstVO.class, rs);
					for(ValueObject v : vo)
						lstDrugRoutes.add((DrugRouteMstVO)v);
				}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HisDataAccessException:getDrugRouteList" + e);
			}
			return lstDrugRoutes;
		
	
		}

		
}
