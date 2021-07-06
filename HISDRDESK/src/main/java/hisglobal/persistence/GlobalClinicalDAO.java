package hisglobal.persistence;

/**
 * Global Clinical Data Access Object for Accessing Global Procedures & Functions 
 * (pkg_clinical_gbl_view & pkg_clinical_gbl_fun Packages)
 * 
 * @author Pragya Sharma 
 * Creation Date: 23-May-2011
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DepartmentMasterVO;
import hisglobal.vo.EmployeeMasterVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.GuestHouseAllottmentDetailVO;
import hisglobal.vo.GuestHouseMasterVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UnitMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import com.sun.tools.jxc.gen.config.Config;

import dietkitchen.DietKitchenConfig;
import dietkitchen.vo.DietSuppierMasterVO;

public class GlobalClinicalDAO
{

	/**
	 * Getting List of Diagnosis Detail of a Patient Episode
	 * @param hisDAO_p
	 * @param strMode_p 
	 * @param voEpiDiagnosis_p
	 * @param voUser_p
	 * @return
	 * @throws Exception
	 * @author Pragya on 19-May-2011
	 */
	public List<EpisodeDiagnosisVO> getPatEpiDiagnosisDetail(HisDAO hisDAO_p, String strMode_p, EpisodeDiagnosisVO voEpiDiagnosis_p, UserVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(GlobalClinicalConfig.PROC_PAT_DIAGNOSIS_DTL);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_puk", (voEpiDiagnosis_p.getPatCrNo()==null) ? "":voEpiDiagnosis_p.getPatCrNo(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_episode_code", (voEpiDiagnosis_p.getEpisodeCode()==null) ? "":voEpiDiagnosis_p.getEpisodeCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_visit_no", (voEpiDiagnosis_p.getEpisodeVisitNo()==null) ? "":voEpiDiagnosis_p.getEpisodeVisitNo(),5);
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
			throw new HisDataAccessException("GlobalClinicalDAO.getPatEpiDiagnosisDetail()::hisDAO_p.executeProcedure(" + GlobalClinicalConfig.PROC_PAT_DIAGNOSIS_DTL 
					+ ",Mode:" + strMode_p + ") -> " + e.getMessage()); 
		}

		List<EpisodeDiagnosisVO> lst = new ArrayList<EpisodeDiagnosisVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(EpisodeDiagnosisVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((EpisodeDiagnosisVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GlobalClinicalDAO.getPatEpiDiagnosisDetail()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Getting List of Patient's Open Episodes
	 * @param hisDAO_p
	 * @param strMode_p 
	 * @param voEpisode_p
	 * @param voUser_p
	 * @return
	 * @throws Exception
	 * @author Pragya on 19-May-2011
	 */
	public List<EpisodeVO> getPatOpenEpisodesDtl(HisDAO hisDAO_p, String strMode_p, EpisodeVO voEpisode_p, UserVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(GlobalClinicalConfig.PROC_PAT_EPISODE_DTL);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_puk", (voEpisode_p.getPatCrNo()==null) ? "":voEpisode_p.getPatCrNo(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_episode_code", (voEpisode_p.getEpisodeCode()==null) ? "":voEpisode_p.getEpisodeCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_visit_no", (voEpisode_p.getEpisodeVisitNo()==null) ? "":voEpisode_p.getEpisodeVisitNo(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_episode_date", (voEpisode_p.getEpisodeDate()==null) ? "":voEpisode_p.getEpisodeDate(),6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8); // Cursor

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
			throw new HisDataAccessException("GlobalClinicalDAO.getPatOpenEpisodesDtl()::hisDAO_p.executeProcedure(" + GlobalClinicalConfig.PROC_PAT_EPISODE_DTL 
					+ ",Mode:" + strMode_p + ") -> " + e.getMessage()); 
		}

		List<EpisodeVO> lst = new ArrayList<EpisodeVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(EpisodeVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((EpisodeVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GlobalClinicalDAO.getPatOpenEpisodesDtl()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Getting List of Departments
	 * @param hisDAO_p
	 * @param strMode_p 0-All Depts, 1-All Type Wise Dept, 2-Role Based Clinical Depts
	 * @param voDept_p
	 * @param voUser_p
	 * @return
	 * @throws Exception
	 * @author Pragya on 20-May-2011
	 */
	public List<DepartmentMasterVO> getDepartmentList(HisDAO hisDAO_p, String strMode_p, DepartmentMasterVO voDept_p, UserVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(GlobalClinicalConfig.PROC_DEPARTMENT_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_type", (voDept_p.getDepartmentType()==null) ? "":voDept_p.getDepartmentType(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_id", voUser_p.getSeatId(),4);
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
			e.printStackTrace();
			throw new HisDataAccessException("GlobalClinicalDAO.getDepartmentList()::hisDAO_p.executeProcedure(" + GlobalClinicalConfig.PROC_DEPARTMENT_LIST 
					+ ",Mode:" + strMode_p + ") -> " + e.getMessage()); 
		}

		List<DepartmentMasterVO> lst = new ArrayList<DepartmentMasterVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(DepartmentMasterVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((DepartmentMasterVO) obj);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("GlobalClinicalDAO.getDepartmentList()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
	/**
	 * Getting List of Units
	 * @param hisDAO_p
	 * @param strMode_p 0-All Dept Unit, 1-All Type Wise Dept Units, 2-Role Based Depts Units, 3-Role Based Type Wise Units,
	 * 4-All Units of Given Depts
	 * @param voUnit_p
	 * @param voUser_p
	 * @return
	 * @throws Exception
	 * @author Pragya on 20-May-2011
	 */
	public List<UnitMasterVO> getUnitsList(HisDAO hisDAO_p, String strMode_p, UnitMasterVO voUnit_p, UserVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(GlobalClinicalConfig.PROC_UNIT_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_code", (voUnit_p.getDepartmentCode()==null) ? "":voUnit_p.getDepartmentCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_unit_type", (voUnit_p.getIsGeneral()==null) ? "":voUnit_p.getIsGeneral(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_user_id", voUser_p.getSeatId(),5);
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
			throw new HisDataAccessException("GlobalClinicalDAO.getUnitsList()::hisDAO_p.executeProcedure(" + GlobalClinicalConfig.PROC_UNIT_LIST 
					+ ",Mode:" + strMode_p + ") -> " + e.getMessage()); 
		}

		List<UnitMasterVO> lst = new ArrayList<UnitMasterVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(UnitMasterVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((UnitMasterVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GlobalClinicalDAO.getUnitsList()::HelperMethods.populateVOfrmRS -> " + e);
		}

		return lst;
	}

	/**
	 * Getting List of Wards
	 * @param hisDAO_p
	 * @param strMode_p 0-All Wards, 1-Dept Unit Wise Wards
	 * 4-All Units of Given Depts
	 * @param voUnit_p
	 * @param voUser_p
	 * @return
	 * @throws Exception
	 * @author Pragya on 20-May-2011
	 */
	public List<Entry> getWardsList(HisDAO hisDAO_p, String strMode_p, UnitMasterVO voUnit_p, UserVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(GlobalClinicalConfig.PROC_WARD_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_code", (voUnit_p.getDepartmentCode()==null) ? "":voUnit_p.getDepartmentCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_unit_code", (voUnit_p.getDepartmentUnitCode()==null) ? "":voUnit_p.getDepartmentUnitCode(),4);
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
			throw new HisDataAccessException("GlobalClinicalDAO.getWardsList()::hisDAO_p.executeProcedure(" + GlobalClinicalConfig.PROC_WARD_LIST 
					+ ",Mode:" + strMode_p + ") -> " + e.getMessage()); 
		}

		List<Entry> lst = new ArrayList<Entry>();
		//ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				lst = HelperMethodsDAO.getAlOfEntryObjects(objResSet);
				//objResSet.beforeFirst();
				//arrVOs = HelperMethods.populateVOfrmRS(Entry.class, objResSet);
				//for (ValueObject obj : arrVOs)
					//lst.add((Entry) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GlobalClinicalDAO.getWardsList()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Getting List of Admitted Patients
	 * @param hisDAO_p
	 * @param strMode_p 0-Dept Unit Ward Wise Admitted PatList
	 * @param voUnit_p
	 * @param voUser_p
	 * @return
	 * @throws Exception
	 * @author Pragya on 20-May-2011
	 */
	public List<PatientDetailVO> getAdmittedPatientList(HisDAO hisDAO_p, String strMode_p, PatientDetailVO voPatientDtl_p, UserVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(GlobalClinicalConfig.PROC_ADMITTED_PATIENT_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_code", (voPatientDtl_p.getDepartmentCode()==null) ? "":voPatientDtl_p.getDepartmentCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_unit_code", (voPatientDtl_p.getDepartmentUnitCode()==null) ? "":voPatientDtl_p.getDepartmentUnitCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_ward_code", (voPatientDtl_p.getWardCode()==null) ? "":voPatientDtl_p.getWardCode(),5);
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
			throw new HisDataAccessException("GlobalClinicalDAO.getAdmittedPatientList()::hisDAO_p.executeProcedure(" + GlobalClinicalConfig.PROC_ADMITTED_PATIENT_LIST 
					+ ",Mode:" + strMode_p + ") -> " + e.getMessage()); 
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
		{
			throw new HisDataAccessException("GlobalClinicalDAO.getAdmittedPatientList()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Getting List of Guest Houses
	 * @param hisDAO_p
	 * @param strMode_p 0-List of Guest House
	 * @param voGuestHouse_p
	 * @param voUser_p
	 * @return
	 * @throws Exception
	 * @author Pragya on 30-May-2011
	 */
	
	public List<GuestHouseMasterVO> getGuestHouseList(HisDAO hisDAO_p, String strMode_p, GuestHouseMasterVO voGuestHouse_p, UserVO voUser_p) throws Exception
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = DietKitchenConfig.QUERY_FILE_FOR_DIETKITCKEN_ESSENTIALDAO;
		String queryKey = "GET_GUEST_HOUSE_LIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();
		try
		{
			DataAccessObject dao=new DataAccessObject(null) {
			};
			rs = HelperMethodsDAO.executeQuery(dao.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
				throw new HisRecordNotFoundException("No Location Name record Exists in database  ");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<GuestHouseMasterVO> lst = new ArrayList<GuestHouseMasterVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(GuestHouseMasterVO.class, rs);
				for (ValueObject obj : arrVOs)
					lst.add((GuestHouseMasterVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GlobalClinicalDAO.getGuestHouseList()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
	
	
	
	
	
	/*public List<GuestHouseMasterVO> getGuestHouseList(HisDAO hisDAO_p, String strMode_p, GuestHouseMasterVO voGuestHouse_p, UserVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(GlobalClinicalConfig.PROC_GUEST_HOUSE_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,3); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,4); // Cursor

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
			throw new HisDataAccessException("GlobalClinicalDAO.getGuestHouseList()::hisDAO_p.executeProcedure(" + GlobalClinicalConfig.PROC_GUEST_HOUSE_LIST 
					+ ",Mode:" + strMode_p + ") -> " + e.getMessage());
		}

		List<GuestHouseMasterVO> lst = new ArrayList<GuestHouseMasterVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(GuestHouseMasterVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((GuestHouseMasterVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GlobalClinicalDAO.getGuestHouseList()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
*/
	/**
	 * Getting List of Employees
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voEmpMst_p
	 * @param voUser_p
	 * @return
	 * @throws Exception
	 * @author Pragya on 30-May-2011
	 */
	public List<EmployeeMasterVO> getEmployeeList(HisDAO hisDAO_p, String strMode_p, EmployeeMasterVO voEmpMst_p, UserVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(GlobalClinicalConfig.PROC_EMPLOYEE_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,3); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,4); // Cursor

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
			e.printStackTrace();
			throw new HisDataAccessException("GlobalClinicalDAO.getEmployeeList()::hisDAO_p.executeProcedure(" + GlobalClinicalConfig.PROC_EMPLOYEE_LIST 
					+ ",Mode:" + strMode_p + ") -> " + e.getMessage());
		}

		List<EmployeeMasterVO> lst = new ArrayList<EmployeeMasterVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(EmployeeMasterVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((EmployeeMasterVO) obj);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("GlobalClinicalDAO.getEmployeeList()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Getting List of Guest House Allottments/Guest Names
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voGuestAllott_p
	 * @param voUser_p
	 * @return
	 * @throws Exception
	 * @author Asha on 23-May-2011
	 */
	public List<GuestHouseAllottmentDetailVO> getGuestNameList(HisDAO hisDAO_p, String strMode_p, GuestHouseAllottmentDetailVO voGuestAllott_p, UserVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(GlobalClinicalConfig.PROC_GUEST_NAME_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_id", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_guest_house_id", (voGuestAllott_p.getStrGuestHouseId()==null) ? "":voGuestAllott_p.getStrGuestHouseId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_status", (voGuestAllott_p.getStrStatus()==null) ? "":voGuestAllott_p.getStrStatus(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor

			// Executing Procedure 
			//hisDAO_p.executeProcedure(nProcedureIndex);
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
			throw new HisDataAccessException("GlobalClinicalDAO.getGuestNameList()::hisDAO_p.executeProcedure(" + GlobalClinicalConfig.PROC_GUEST_NAME_LIST 
					+ ",Mode:" + strMode_p +  ") -> " + e.getMessage());
		}

		List<GuestHouseAllottmentDetailVO> lst = new ArrayList<GuestHouseAllottmentDetailVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(GuestHouseAllottmentDetailVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((GuestHouseAllottmentDetailVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GlobalClinicalDAO.getGuestNameList()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Getting List of Wards
	 * @param hisDAO_p
	 * @param strMode_p 0-All Wards
	 * 4-All Units of Given Diet Suppliers
	 * @param voUnit_p
	 * @param voUser_p
	 * @return
	 * @throws Exception
	 * @author Pawan Kumar B N  on 10-Jul-2012
	 */
	public List<Entry> getSupplierWardsList(HisDAO hisDAO_p, String strMode_p, DietSuppierMasterVO voDietSupplier_p, UserVO voUser_p) throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(GlobalClinicalConfig.PROC_SUPPLIER_WARD_LIST);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_supplier_id", (voDietSupplier_p.getStrDietSupplierId()==null) ? "":voDietSupplier_p.getStrDietSupplierId(),3);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5); // Cursor

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
			throw new HisDataAccessException("GlobalClinicalDAO.getWardsList()::hisDAO_p.executeProcedure(" + GlobalClinicalConfig.PROC_WARD_LIST 
					+ ",Mode:" + strMode_p + ") -> " + e.getMessage()); 
		}

		List<Entry> lst = new ArrayList<Entry>();
		//ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				lst = HelperMethodsDAO.getAlOfEntryObjects(objResSet);
				//objResSet.beforeFirst();
				//arrVOs = HelperMethods.populateVOfrmRS(Entry.class, objResSet);
				//for (ValueObject obj : arrVOs)
					//lst.add((Entry) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GlobalClinicalDAO.getWardsList()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
	public List getRelationsList(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call pkg_clinical_gbl_view.PROC_GBLT_RELATION_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);			
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",hisglobal.hisconfig.Config.SUPER_USER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				//throw new HisRecordNotFoundException("No Relation Found");
				webRs.beforeFirst();
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}
	
	
	
	
	
}
