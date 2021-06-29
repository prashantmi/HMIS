package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
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
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.BloodRequisitionComponentDtlVO;
import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.DrugRouteMstVO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.ExtAdminDtlVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.JsyRuleMasterVO;
import hisglobal.vo.MonitoringModeMstVO;
import hisglobal.vo.PatBloodStockDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientBulletinDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.UnitInvParaMappingVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;
import inpatient.transaction.vo.PendingSampleCollectionVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject; 

//import bloodbank.BloodBankConfig;

public class InPatientEssentialDAO extends DataAccessObject implements InPatientEssentialDAOi
{
	Logger log;

	public InPatientEssentialDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
	// Getting the List of Unit
	public List getDeptUnitList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HOPT_DEPT_UNIT_ROSTER_DTL.RETRIEVE_UNITS_BY_SEATID";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	
	public String getDrugAdminData(PatientDetailVO voPat, UserVO voUser)
	{
		String count = "0";
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.COUNT_DRUG_ADMIN.HRGT_EPISODE_DRUG_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}		
		populateMAP.put(sq.next(), voPat.getPatCrNo());
		populateMAP.put(sq.next(), voUser.getHospitalCode());
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				count = "0";
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				count = rs.getString(1);
				System.out.println("");
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

		return count;
	}
	
	public String getProgressNotesData(PatientDetailVO voPat, UserVO voUser)
	{
		String count = "0";
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.COUNT_PROGRESS_NOTES.HIPD_NURSE_ROUND_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}		
		populateMAP.put(sq.next(), voPat.getPatCrNo());
		populateMAP.put(sq.next(), voPat.getPatAdmNo());
		populateMAP.put(sq.next(), voUser.getHospitalCode());
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				count = "0";
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				count = rs.getString(1);
				System.out.println("");
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

		return count;
	}
	
	
	public List getAllUnitList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL_ALL_UNITS.HOPT_DEPT_UNIT_ROSTER_DTL1";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}

	public List getDeptUnitListForUnitWise(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNITLIST_FORUNITWISE";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
		return alRecord;
	}
	
	public List getDeptUnitListForUnitWiseForModify(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNITLIST_FORUNITWISEFORMODIFY";
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
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
		return alRecord;
	}
	
	public List getDeptUnitListForWardWise(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNITLIST_FORWARDWISE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
		return alRecord;
	}

	public List getWardListForWardWise(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.WARDLIST.WITHOUTUNITS";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
		return alRecord;
	}

	
	public List getParameter(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.PARAMETERLIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	public List getParameterForModify(UnitInvParaMappingVO unitInvParaMapVO,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.PARAMETERLISTFORMODIFY";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), unitInvParaMapVO.getUnitId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	public List getParameterForWardWise(UnitInvParaMappingVO unitInvParaMapVO,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.PARAMETERLISTFORWARDWISE";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), unitInvParaMapVO.getUnitId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), unitInvParaMapVO.getWardCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}

	// Getting the List of ward on the basis of unitCode
	public List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "WARD_LIST.HIPT_DUWRBED_MST.RETRIEVE_BY_DEPTUNITCODE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		/*populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getSeatId());*/

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Ward Alloted For This Unit");
			}

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	/** Getting the List of ward on the basis of unitCode
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public List getWardListBasedOnRole(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "WARD_LIST.HIPT_WARD_MST.RETRIEVE_BY_ROLEPERMISSION";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getSeatId());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Ward Alloted For This Unit");
			}

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	// Getting the List of Admitted patient on the basis of unitCode & wardCode
	public PatientDetailVO[] getAdmittedPatientList(String roomCode,String unitCode,String wardCode,UserVO userVO)
	{
		PatientDetailVO[] patDtlVO = null;
		Map populateMap = new HashMap();
		ValueObject[] valueObjects = null;
		String query = "", conditionUnit = "", conditionRoom = "", conditionOrderBy = "";
		String filename =InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.HIPT_PATADMISSION_DTL.RETRIEVE_PATIENT";
		String queryCondKeyUnit = "SELECT.HIPT_PATADMISSION_DTL.RETRIEVE_PATIENT.COND.1";
		String queryCondKeyRoom = "SELECT.HIPT_PATADMISSION_DTL.RETRIEVE_PATIENT.COND.2";
		String queryCondKeyOrderBy = "SELECT.HIPT_PATADMISSION_DTL.RETRIEVE_PATIENT.COND.ORDER_BY";
		
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			conditionUnit = HelperMethodsDAO.getQuery(filename, queryCondKeyUnit);
			conditionRoom = HelperMethodsDAO.getQuery(filename, queryCondKeyRoom);
			conditionOrderBy = HelperMethodsDAO.getQuery(filename, queryCondKeyOrderBy);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMap.put(sq.next(), wardCode);
			populateMap.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
			populateMap.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
			populateMap.put(sq.next(), OpdConfig.YES);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), userVO.getHospitalCode());
			populateMap.put(sq.next(), userVO.getUserId());
			populateMap.put(sq.next(), userVO.getHospitalCode());
			
		
			if(unitCode!=null && !unitCode.equals(InpatientConfig.UNIT_ALL_CODE))
			{
				query = query + " " + conditionUnit;
				populateMap.put(sq.next(), unitCode);
			}

			if(roomCode!=null && !roomCode.equals(InpatientConfig.ROOM_ALL_CODE))
			{
				query = query + " " + conditionRoom;
				populateMap.put(sq.next(), roomCode);
			}
			
			query = query + " " + conditionOrderBy;
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
				patDtlVO = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					patDtlVO[i] = (PatientDetailVO) valueObjects[i];
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return patDtlVO;
	}

	public List getWardsForModify(String _unitId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.WARDSFORMODIFY";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_unitId);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getTemplatesByUnitSeatDesk" + e);
		}
		return alRecord;
	}
	
	public UnitInvParaMappingVO getWardName(UnitInvParaMappingVO _unitInvParaMapVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		UnitInvParaMappingVO unitInvParaMapVo = new UnitInvParaMappingVO();
		
		
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.WARDNAMEFORMODIFY";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_unitInvParaMapVO.getUnitId());
			populateMAP.put(sq.next(),_unitInvParaMapVO.getSlNo());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			if(rs.next())
			{
				unitInvParaMapVo.setWardCode(rs.getString(1));
				unitInvParaMapVo.setUnitId(rs.getString(2));
				unitInvParaMapVo.setWardName(rs.getString(3));
			}
		
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return unitInvParaMapVo;
	}
	
	
	public List getWardList(UnitInvParaMappingVO voUDMT, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.WARDSFORMODIFY";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query in fetch is  :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), voUDMT.getSlNo());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), voUDMT.getUnitId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getTemplatesByUnitSeatDesk" + e);
		}
		return alRecord;
	}
	
	// Getting the List of Employee List Unit Wise
	public List getEmployeeListUnitWise(String unitCode,UserVO userVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(InpatientDaoConfig.PROCEDURE_GET_EMP_LIST_UNITWISE);
			strProc.addInParameter(1, Types.VARCHAR, userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, unitCode);
			strProc.addOutParameter(3, Types.VARCHAR);
			strProc.addOutParameter(4, Types.REF);//OracleTypes.CURSOR);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(3);
			rs = (ResultSet) strProc.getParameterAt(4);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		// log.error(query + "\n");
		/*
		 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
		 */

		List alRecord = new ArrayList();

		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return alRecord;
	}

	
	public List getOutParameterList(String type,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HIPD_INTAKEOUT_PARA_MST";

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), type);
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Out Parameter Found");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
		return alRecord;
	}
	
	public MonitoringModeMstVO[] getMonitorMode(UserVO userVO)
	{
		MonitoringModeMstVO[] monitorModeMstVo = null;
		Map populateMap = new HashMap();
		ValueObject[] valueObjects = null;
		String query = "";
		String filename =InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT_MODE.HIPD_MONITORING_MODE_MST";
		Connection conn = super.getTransactionContext().getConnection();
		
		Sequence sq = new Sequence();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMap.put(sq.next(), userVO.getHospitalCode());

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Monitor Mode Found");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(MonitoringModeMstVO.class, rs);
				monitorModeMstVo = new MonitoringModeMstVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					monitorModeMstVo[i] = (MonitoringModeMstVO) valueObjects[i];
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("InPatientEssentialDAO :: " + e);
		}
		return monitorModeMstVo;
	}
	
	public String getTotalAdmittedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode)
	{
		String count = "";
		Map _populateMapPatientDtl = new HashMap();
		String query = "", conditionUnit = "", conditionRoom = "";
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "COUNT_TOTAL_ADMITTED_PAT.HIPT_PATADMISSION_DTL";
		String queryKeyCondUnit = "COUNT_TOTAL_ADMITTED_PAT.HIPT_PATADMISSION_DTL.COND.1";
		String queryKeyCondRoom = "COUNT_TOTAL_ADMITTED_PAT.HIPT_PATADMISSION_DTL.COND.2";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			conditionUnit = HelperMethodsDAO.getQuery(filename, queryKeyCondUnit);
			conditionRoom = HelperMethodsDAO.getQuery(filename, queryKeyCondRoom);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(	"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{
			_populateMapPatientDtl.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
			_populateMapPatientDtl.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
			_populateMapPatientDtl.put(sq.next(), OpdConfig.YES);
			_populateMapPatientDtl.put(sq.next(), wardCode);
			_populateMapPatientDtl.put(sq.next(), userVO.getHospitalCode());
			_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);

			if(!unitCode.equals(InpatientConfig.UNIT_ALL_CODE))
			{
				query = query + " " + conditionUnit;
				_populateMapPatientDtl.put(sq.next(), unitCode);
			}

			if(!roomCode.equals(InpatientConfig.ROOM_ALL_CODE))
			{
				query = query + " " + conditionRoom;
				_populateMapPatientDtl.put(sq.next(), roomCode);
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMapPatientDtl);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				count = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return count;
	}
	
	public String getTodayAdmittedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode)
	{
		String count = "";
		Map _populateMapPatientDtl = new HashMap();
		String query = "", conditionUnit = "", conditionRoom = "";
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "COUNT_ADMITTED_PAT_TODAY.HIPT_PATADMISSION_DTL";
		String queryKeyCondUnit = "COUNT_ADMITTED_PAT_TODAY.HIPT_PATADMISSION_DTL.COND.1";
		String queryKeyCondRoom = "COUNT_ADMITTED_PAT_TODAY.HIPT_PATADMISSION_DTL.COND.2";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			conditionUnit = HelperMethodsDAO.getQuery(filename, queryKeyCondUnit);
			conditionRoom = HelperMethodsDAO.getQuery(filename, queryKeyCondRoom);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(	"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{
			_populateMapPatientDtl.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
			_populateMapPatientDtl.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
			_populateMapPatientDtl.put(sq.next(), OpdConfig.YES);
			_populateMapPatientDtl.put(sq.next(), wardCode);
			_populateMapPatientDtl.put(sq.next(), userVO.getHospitalCode());
			_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			if(!unitCode.equals(InpatientConfig.UNIT_ALL_CODE))
			{
				query = query + " " + conditionUnit;
				_populateMapPatientDtl.put(sq.next(), unitCode);
			}
			if(!roomCode.equals(InpatientConfig.ROOM_ALL_CODE))
			{
				query = query + " " + conditionRoom;
				_populateMapPatientDtl.put(sq.next(), roomCode);
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMapPatientDtl);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				count = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return count;
	}
	
	public String getTodayDischargedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode)
	{
		String count = "";
		Map _populateMapPatientDtl = new HashMap();
		String query = "", conditionUnit = "", conditionRoom = "";
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "COUNT_DISCHARGED_PAT_TODAY.HIPT_PATADMISSION_DTL";
		String queryKeyCondUnit = "COUNT_DISCHARGED_PAT_TODAY.HIPT_PATADMISSION_DTL.COND.1";
		String queryKeyCondRoom = "COUNT_DISCHARGED_PAT_TODAY.HIPT_PATADMISSION_DTL.COND.2";
		
		Connection conn = super.getTransactionContext().getConnection();
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			conditionUnit = HelperMethodsDAO.getQuery(filename, queryKeyCondUnit);
			conditionRoom = HelperMethodsDAO.getQuery(filename, queryKeyCondRoom);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(	"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{
			_populateMapPatientDtl.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE);
			_populateMapPatientDtl.put(sq.next(), wardCode);
			_populateMapPatientDtl.put(sq.next(), userVO.getHospitalCode());
			_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);

			if(!unitCode.equals(InpatientConfig.UNIT_ALL_CODE))
			{
				query = query + " " + conditionUnit;
				_populateMapPatientDtl.put(sq.next(), unitCode);
			}
			if(!roomCode.equals(InpatientConfig.ROOM_ALL_CODE))
			{
				query = query + " " + conditionRoom;
				_populateMapPatientDtl.put(sq.next(), roomCode);
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				count = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return count;
	}
	
	public List getRoomOnBasisOfWardCode(String unitCode,String wardCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ROOM_LIST.HIPT_DUWRBED_MST.RETRIEVE_BY_DEPTUNITCODE";

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
		
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), wardCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Room Alloted For This Unit");
			}*/

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}
	
	public List getProgressNotes(String processId,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "GET_MACRO_BY_PROCESSID.HGBT_MACRO_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), processId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Room Alloted For This Unit");
			}*/

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public List getMacrosByProcessId(String processId, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "GET_MACRO_BY_PROCESSID.HGBT_MACRO_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), processId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getCallRemarksNNotes(String processId,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "GET_MACRO_BY_PROCESSID.HGBT_MACRO_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), processId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Room Alloted For This Unit");
			}*/

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	
	public List getRouteList(String type,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT_ROUTE_BY_TYPE.HIPD_INTAKEOUT_ROUTE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), type);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}*/

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

		
	public PatientBulletinDetailVO[] getAllAdmittedPatientListBulletin(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT_ALL.INPATIENT_BULLETIN_DETAIL";
		PatientBulletinDetailVO [] patientBulletinDtlVO=null;
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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Patient With Bullentin Found");
				throw new HisRecordNotFoundException("");
			}
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()) len++;
				patientBulletinDtlVO=new PatientBulletinDetailVO[len];
				len=0;
				rs.beforeFirst();
				while(rs.next()){
					patientBulletinDtlVO[len]=new PatientBulletinDetailVO();
					HelperMethods.populateVOfrmRS(patientBulletinDtlVO[len], rs);
					len++;
				}
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return patientBulletinDtlVO;
	}

	
	public List<Entry> getDosageFrequecy(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
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
			if (!rs.next()) throw new HisRecordNotFoundException( "No Dosage Frequecy Exists  ");
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
	
	/**
	 * Getting Previous Drug Detail
	 * @param _patCrNo Cr No.
	 * @param _episodeCode Episode Code 
	 * @param _userVO User VO
	 * @return List of Drug Treatment Detail
	 */
	
	public List<PatDrugTreatmentDetailVO> getPrevPatDrugDetail(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS_DRUG_DETAIL.HRGT_EPISODE_DRUG_DTL";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode); 
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		List<PatDrugTreatmentDetailVO> _previousEpisodeDiagVO = new ArrayList<PatDrugTreatmentDetailVO>();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PatDrugTreatmentDetailVO.class, rs);			
				for (ValueObject v : vo)
					_previousEpisodeDiagVO.add((PatDrugTreatmentDetailVO)v);
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
	
	public List getTodayPatDrugDetail(String _patCrNo, String _episodeCode,String _patAdmNo ,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.TODAY_DRUG_DETAIL.HRGT_EPISODE_DRUG_ADMIN_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), InpatientConfig.DRUG_ALLERGY_TYPE_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode); 
		populateMAP.put(sq.next(), _patAdmNo); 
		populateMAP.put(sq.next(), OpdConfig.SCHEDULE); 
				

		List _todayDrugDtlVOList = new ArrayList();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DrugAdminDtlVO.class, rs);			
				for (ValueObject v : vo)
					_todayDrugDtlVOList.add((DrugAdminDtlVO)v);
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
		return _todayDrugDtlVOList;
	}
	
	
	
	public List getPrevDrugSchedule(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
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

	
	public List getConsultantDetails(String unitCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		List consultantLst=new ArrayList();
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "FETCH.CONSULTANT.LIST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), unitCode.split("#")[0]); /* Nilesh Gupta 08-11-2017*/
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			System.out.println("Unit_code: "+unitCode+" HospitalCode: "+_userVO.getHospitalCode()+" valid: "+Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		try
		{
			consultantLst = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return consultantLst;
	}

	public List getConsultantPhone(String empNo,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		List phoneLst=new ArrayList();
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "FETCH.CONSULTANT.PHONE.LIST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), empNo);
		
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Phone no. found");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		try
		{
			phoneLst = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return phoneLst;
	}
	public List getUnitPhone(String unitCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		List phoneLst=new ArrayList();
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "FETCH.UNIT.PHONE.LIST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), unitCode);
		
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Phone no. found");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		try
		{
			phoneLst = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return phoneLst;
	}

	
	public List getPeonDetails(String unitCode,UserVO userVO)
	{
			String deptCode="";
			String errorMsg = "";
			ResultSet rs = null;
			Connection conn = super.getTransactionContext().getConnection();
			try
			{
				Procedure strProc = new Procedure(InpatientConfig.PROCEDURE_GET_PEON_LIST);
				strProc.addInParameter(1, Types.VARCHAR, userVO.getHospitalCode());
				strProc.addInParameter(2, Types.VARCHAR, InpatientConfig.PROCESS_TYPE_FOR_CALLBOOK_PEONLIST);
				strProc.addInParameter(3, Types.VARCHAR, deptCode);
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.REF);//OracleTypes.CURSOR);
				strProc.execute(conn);
				errorMsg = (String) strProc.getParameterAt(4);
				rs = (ResultSet) strProc.getParameterAt(5);

			}
			catch (Exception e)
			{
				throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}
			// log.error(query + "\n");
			/*
			 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
			 */

			List alRecord = new ArrayList();

			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			}
			catch (Exception e)
			{

				throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			finally
			{
				if (rs != null)
				{
					try
					{
						rs.close();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
			}
			return alRecord;
		}

	
	public List getAllConsultantDetails(String unitCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		List consultantLst=new ArrayList();
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "FETCH.ALLCONSULTANT.LIST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			//populateMAP.put(sq.next(), unitCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		try
		{
			consultantLst = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return consultantLst;
	}

	/*public List getDrugList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		//String queryKey = "ESSENTIAL.LIST.HSTT_DRUG_MST";
		String queryKey = "ESSENTIAL.LIST.HSTT_DRUGBRAND_MST";
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
			if (!rs.next()) throw new HisRecordNotFoundException( "No Drugs Exists  ");
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
	}*/
	
	public List getAllDrugRouteList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HGBT_DRUG_ROUTE_MST";
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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
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
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstDrugRoutes;
	}
	
	public DrugFrequencyMstVO[] getDrugFrequencyVOList(UserVO userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		ValueObject[] vo={};
		DrugFrequencyMstVO[] drugFrequencyMstVO;
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
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
	
	public List getDrugAdminListByCRNo(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HRGT_EPISODE_DRUG_ADMIN_DTL";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), _episodeCode);
			
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

		List lstDrugExec = new ArrayList();
		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(DrugAdminDtlVO.class, rs);
				for(ValueObject v : vo)
					lstDrugExec.add((DrugAdminDtlVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstDrugExec;
	}
	
	public List getDateWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST_BY_DATE.HRGT_EPISODE_DRUG_ADMIN_DTL";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), drugAdminDtlVO.getPatCrNo());
			//populateMAP.put(sq.next(), drugAdminDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), drugAdminDtlVO.getTreatFromDate());
			populateMAP.put(sq.next(),drugAdminDtlVO.getTreatToDate());
			populateMAP.put(sq.next(), drugAdminDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), OpdConfig.SCHEDULE);
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

		List lstDrugExec = new ArrayList();
		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(DrugAdminDtlVO.class, rs);
				for(ValueObject v : vo)
					lstDrugExec.add((DrugAdminDtlVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstDrugExec;
	}
	
	public List getDrugWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST_BY_DRUG.HRGT_EPISODE_DRUG_ADMIN_DTL";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), drugAdminDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), drugAdminDtlVO.getItemId());
			populateMAP.put(sq.next(), drugAdminDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), OpdConfig.SCHEDULE);
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

		List lstDrugExec = new ArrayList();
		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(DrugAdminDtlVO.class, rs);
				for(ValueObject v : vo)
					lstDrugExec.add((DrugAdminDtlVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstDrugExec;
	}
	
	
	public List getAllDrugAdminListByAdminEndDate(PatientDetailVO patientDetailVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST_BY_END_DATE.HRGT_EPISODE_DRUG_ADMIN_DTL";
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
			populateMAP.put(sq.next(), patientDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), OpdConfig.SCHEDULE);
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

		List lstDrugExec = new ArrayList();
		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(DrugAdminDtlVO.class, rs);
				for(ValueObject v : vo)
					lstDrugExec.add((DrugAdminDtlVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstDrugExec;
	}
	
	public List getExecDrugByCrNo(PatientDetailVO patientDetailVO,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL_LIST_BY_CRNO.HRGT_EPISODE_DRUG_ADMIN_DTL";
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
			populateMAP.put(sq.next(), patientDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.SCHEDULE);
			
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
	
	
	public List getALLDrugAdminListByCRNo(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_LIST.HRGT_EPISODE_DRUG_ADMIN_DTL";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), _episodeCode);
			populateMAP.put(sq.next(), InpatientConfig.IS_REACTION_NO);
			populateMAP.put(sq.next(), OpdConfig.SCHEDULE);
			
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

		List lstDrugExec = new ArrayList();
		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(DrugAdminDtlVO.class, rs);
				for(ValueObject v : vo)
					lstDrugExec.add((DrugAdminDtlVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstDrugExec;
	}
	
	public List getAdminDateLst(String crNo,String episodeCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		//String queryKey = "ESSENTIAL.LIST.HSTT_DRUG_MST";
		String queryKey = "ESSENTIAL.DATE_LIST.HRGT_EPISODE_DRUG_ADMIN_DTL";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), crNo);
			populateMAP.put(sq.next(), episodeCode);
			populateMAP.put(sq.next(), OpdConfig.SCHEDULE);
			populateMAP.put(sq.next(), InpatientConfig.IS_REACTION_NO);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO.getDosageFrequecy::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException( "This patient did not take any drug ");
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

	
	public List getReqBloodComponentList(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HBBT_BLDREQ_COMPONENT_DTL";
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
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);//_userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);//_userVO.getHospitalCode());
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), InpatientConfig.IN_STOCK);
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), _episodeCode);
			populateMAP.put(sq.next(), InpatientConfig.HBNUM_REQ_TYPE_INTERNAL_FROM_OPD);
			populateMAP.put(sq.next(), InpatientConfig.HBNUM_REQ_TYPE_INTERNAL_FROM_IPD);
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
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

		List lstDrugExec = new ArrayList();
		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(BloodRequisitionComponentDtlVO.class, rs);
				for(ValueObject v : vo)
					lstDrugExec.add((BloodRequisitionComponentDtlVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstDrugExec;
	}
	
	public List getAllComponentList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SELECT_ALL.HBBT_COMPONENT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
	
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getcOMPONENT:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	public List getBloodABO(UserVO _userVO)
	{
		System.out.println("inside getprimarycat");
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.HBSTR_BLD_ABO.HBBT_BLDABO_MST";
		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
	//	log.error(query + "\n");
		/*
		 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
		 */

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
					populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Records ABO Found");
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

			throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		System.out.println("alRecord abo" + alRecord);
		return alRecord;
	}
	
	public List getBloodRH()
	{

		List finalCollectionPointEntryList = new ArrayList();
		List initialCollectionPointList = new ArrayList();
		initialCollectionPointList.add(InpatientConfig.RH_FLAG_POSITIVE);
		initialCollectionPointList.add(InpatientConfig.RH_FLAG_POSITIVE_VALUE);
		initialCollectionPointList.add(InpatientConfig.RH_FLAG_NEGATIVE);
		initialCollectionPointList.add(InpatientConfig.RH_FLAG_NEGATIVE_VALUE);
		initialCollectionPointList.add(InpatientConfig.RH_FLAG_NIL);
		initialCollectionPointList.add(InpatientConfig.RH_FLAG_NIL_VALUE);
		for (int i = 0; i < initialCollectionPointList.size();)
		{
			Entry objEntry = new Entry((String) initialCollectionPointList.get(i++), (String) initialCollectionPointList.get(i++));
			finalCollectionPointEntryList.add(objEntry);
		}

		return finalCollectionPointEntryList;
	}
	
	public List getAllComponentListForCombo(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SELECT_ALL.HBBT_COMPONENT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
	
		Sequence sq = new Sequence();
		//populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getcOMPONENT:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	public List getInStockBloodBagListByCrNo(String _patCrNo, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.BLOODBAG_STOCK_LIST.HBBT_PAT_BLDSTOCK_DTL";
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
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);//_userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);//_userVO.getHospitalCode());
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), InpatientConfig.IN_STOCK);
			populateMAP.put(sq.next(), InpatientConfig.TRANSFUSED);
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

		List lstDrugExec = new ArrayList();
		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatBloodStockDtlVO.class, rs);
				for(ValueObject v : vo)
					lstDrugExec.add((PatBloodStockDtlVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstDrugExec;
	}
	
	
	
	
	public List getCrossMatchList(BloodRequisitionComponentDtlVO bloodRequisitionComponentDtlVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.CROSS_MATCH_LIST.HBBT_CROSSMATCH_DTL";
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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);//_userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);//_userVO.getHospitalCode());
			
			populateMAP.put(sq.next(), InpatientConfig.BAG_CROSSMATCH_BUT_NOT_ISSUED);
			populateMAP.put(sq.next(), bloodRequisitionComponentDtlVO.getRequisitionNo());
			
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
			
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

		List lstDrugExec = new ArrayList();
		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatBloodStockDtlVO.class, rs);
				for(ValueObject v : vo)
					lstDrugExec.add((PatBloodStockDtlVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstDrugExec;
	}
	
	public List getRoundByListUnitWise(String unitCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT_ROUND_BY_LIST.HGBT_UNIT_CONSULTANT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
	
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getcOMPONENT:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}


	/**
	 * Getting List of Education Status
	 * @param _userVO User Detail
	 * @return
	 */
	
	/**
	## 		Modification Log		:	hospital code filter removed			
	##		Modify Date				: 	11-03-2015
	##		Reason	(CR/PRS)		: 	table changed for nimenew & hospital code check removed
	##		Modify By				: 	Akash Singh
	*/
	public List<Entry> getEducationStatusList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.GBLT_EDUCATION_STATUS_MST";
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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Education Status Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getEducationStatusList" + e);
		}
		return alRecord;
	}

	/**
	 * Getting List of Caste
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getCasteList(UserVO _userVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(InpatientDaoConfig.PROCEDURE_GET_CASTE_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addOutParameter(2, Types.VARCHAR);
			strProc.addOutParameter(3, Types.REF);//OracleTypes.CURSOR);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(2);
			rs = (ResultSet) strProc.getParameterAt(3);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return alRecord;
	}

	/**
	 * Getting List of Menstrual Cycle Id
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getMenstrualCycleList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HANCT_MENSTRUAL_CYCLE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Menstrual Cycle Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getMenstrualCycleList" + e);
		}
		return alRecord;
	}

	/**
	 * Getting List of Delivery Place
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getDeliveryPlaceList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HANCT_DELIVERY_PLACE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Delivery Place Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getDeliveryPlaceList" + e);
		}
		return alRecord;
	}

	/**
	 * Getting List of Birth Types
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getBirthTypeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HANCT_BIRTH_TYPE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Birth Type Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getBirthTypeList" + e);
		}
		return alRecord;
	}
	
	/**
	 * Getting List of Delivery Type
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getDeliveryTypeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HANCT_DELIVERY_TYPE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Delivery Type Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getDeliveryTypeList" + e);
		}
		return alRecord;
	}

	public List getBloodTransReactionEssential( String patCrNo, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		//String queryKey = "ESSENTIAL.BLOOD_TRANS_LIST.HRGT_EPISODE_BLDTRANS_DTL";
		String queryKey = "ESSENTIAL.BLOOD_TRANS_LIST.HBBT_TRANSFUSSION_DTL";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), patCrNo);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), InpatientConfig.IS_REACTION_NO);
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

		List lstBloodTransVO = new ArrayList();
		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(BloodTransfusionDtlVO.class, rs);
				for(ValueObject v : vo)
					lstBloodTransVO.add((BloodTransfusionDtlVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstBloodTransVO;
	}
	
	public List getInStockNotTransfusedBloodBagListByCrNo(String _patCrNo, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.NOTTRANSFUSED_BLOODBAG_STOCK_LIST_BY_CRNO.HBBT_PAT_BLDSTOCK_DTL";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), InpatientConfig.IN_STOCK);
			//populateMAP.put(sq.next(), InpatientConfig.TRANSFUSED);
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

		List lstDrugExec = new ArrayList();
		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatBloodStockDtlVO.class, rs);
				for(ValueObject v : vo)
					lstDrugExec.add((PatBloodStockDtlVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return lstDrugExec;
	}
	
	public List<BloodTransfusionDtlVO> getPreviousBloodTransDtl(String patCrNo,List<BloodTransfusionDtlVO> lstBldTransDtlVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.PREVIOUS_TRANSFUSION_DETAIL.HBBT_TRANSFUSION_DTL";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), patCrNo);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
						
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("InpatientEssentialDAO.getPreviousBloodTransDtl::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		ValueObject[] vo = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(BloodTransfusionDtlVO.class, rs);
				for(ValueObject v : vo)
					lstBldTransDtlVO.add((BloodTransfusionDtlVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getPreviousBloodTransDtl" + e);
		}
		return lstBldTransDtlVO;
	}
	
	public JsyRuleMasterVO getJsyRule(UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		JsyRuleMasterVO jsyRuleMasterVO=new JsyRuleMasterVO();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey ="SELECT_JSYRULE.HANCT_JSY_RULE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		 populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		 populateMAP.put(sq.next(), InpatientConfig.TREATMENT_TYPE_JSY);
		 populateMAP.put(sq.next(), _userVO.getHospitalCode());
		 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(jsyRuleMasterVO, rs);
			else
				jsyRuleMasterVO=null;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return jsyRuleMasterVO;
	}

	
	public List getAreaCategory()
	{
		System.out.println("inside get srea cat");
		List lstAreaCat = new ArrayList();
		List alList = new ArrayList();
		alList.add(InpatientConfig.AREA_TYPE_URBAN_LABLE);
		alList.add(InpatientConfig.AREA_TYPE_URBAN_VALUE);
		alList.add(InpatientConfig.AREA_TYPE_SEMIURBAN_LABLE);
		alList.add(InpatientConfig.AREA_TYPE_SEMIURBAN_VALUE);
		alList.add(InpatientConfig.AREA_TYPE_RURAL_LABLE);
		alList.add(InpatientConfig.AREA_TYPE_RURAL_VALUE);

		for (int i = 0; i < alList.size();)
		{
			Entry objEntry = new Entry((String) alList.get(i++), (String) alList.get(i++));
			lstAreaCat.add(objEntry);
		}
		return lstAreaCat;
	}

	
	

	
	public String getMaxEntryDateFromDrugDetail(String _patCrNo,String _episodeCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.MAX_ENTRY_DATE.HRGT_EPISODE_DRUG_DTL";
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
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO.getDosageFrequecy::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				throw new HisRecordNotFoundException( " ");
			}
			
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
	
	
	/* get the list of patient whose consent is pending  */
	
	public List getPendingConsentList(String deptUnitCode,String wardCode,String roomCode,UserVO _userVO)
	{
		ConsentRequestVO consentVO;
		List consentVOList=new ArrayList();
		ResultSet rs = null;
		String query = "", condition = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.PENDING_CONSENT.HGBT_CONSENT_DTL";
	//	String queryKeyCond = "SELECT.PENDING_CONSENT.HGBT_CONSENT_DTL.COND.1";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		//	condition = HelperMethodsDAO.getQuery(filename, queryKeyCond);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{	
		//	if(roomCode != null){
				//	if(!roomCode.equals(InpatientConfig.ROOM_ALL_CODE))
				//	{
					//	query = query.replace("#", condition);
				//		populateMAP.put(sq.next(), roomCode);
				//	}
				//}
		//	else
			//	query = query.replace("#", "");
			//populateMAP.put(sq.next(), deptUnitCode);
			//populateMAP.put(sq.next(), wardCode);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
			populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
			populateMAP.put(sq.next(), OpdConfig.YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), OpdConfig.CONSENT_REQUEST);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("InPatientEssentialDAO.getPendingConsentList::populateMAP" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next()) 
			{
				throw new HisRecordNotFoundException("No Record Found");
			}*/
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					consentVO=new ConsentRequestVO();
					HelperMethods.populateVOfrmRS(consentVO, rs);
					consentVOList.add(consentVO);
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return consentVOList;
	}
	
	//get the list of patient whose treatment is pending today
	
	public List getPendingTreatmentList(UserVO _userVO)
	{
		DrugAdminDtlVO drugAdminDtlVO;
		List drugAdminVOList=new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.PENDING_TREATMENT.HRGT_EPISODE_DRUG_ADMIN_DTL";
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
			
			//populateMAP.put(sq.next(), admissionNo);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), InpatientConfig.DRUG_SCHEDULE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("InPatientEssentialDAO.getPendingConsentList::populateMAP" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next()) 
			{
				throw new HisRecordNotFoundException("No Record Found");
			}*/
			
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					drugAdminDtlVO=new DrugAdminDtlVO();
					HelperMethods.populateVOfrmRS(drugAdminDtlVO, rs);
					drugAdminVOList.add(drugAdminDtlVO);
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return drugAdminVOList;
	}
	
	
	/**
	 * get the List of patient whose sample is yet not collected.
	 */
	public List getPendingSampleCollectionList(String wardCode,String deptUnitCode,String roomCode,UserVO _userVO)
	{
		PendingSampleCollectionVO pendingSampleColVO;
		List pendingSampleColVOList=new ArrayList();
		ResultSet rs = null;
		String query = "", condition = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.PENDING_SAMPLE.HIVT_REQUISITION_HEADER";
	//	String queryKeyCond = "SELECT.PENDING_SAMPLE.HIVT_REQUISITION_HEADER.COND.1";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		//	condition = HelperMethodsDAO.getQuery(filename, queryKeyCond);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{	
			
			//populateMAP.put(sq.next(), admissionNo);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_userVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
//			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
//			populateMAP.put(sq.next(), _userVO.getHospitalCode());
//			populateMAP.put(sq.next(), wardCode);
//			if(roomCode != null)
//			{
//				if(!roomCode.equals(InpatientConfig.ROOM_ALL_CODE))
//				{
//					query = query.replace("#", condition);
//					populateMAP.put(sq.next(), roomCode);
//				}
//			}
//			else
//				query = query.replace("#", "");
//			populateMAP.put(sq.next(), deptUnitCode);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("InPatientEssentialDAO.getPendingConsentList::populateMAP" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					pendingSampleColVO=new PendingSampleCollectionVO();
					HelperMethods.populateVOfrmRS(pendingSampleColVO, rs);
					pendingSampleColVOList.add(pendingSampleColVO);
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return pendingSampleColVOList;
	}

	
	/** this method is Incomplete
	 * get the list of patient whose Instruction is pending
	 * @param wardCode
	 * @param deptUnitCode
	 * @param roomCode
	 * @param _userVO
	 * @return
	 */
	public List getPendingInstructionList(UserVO _userVO)
	{
		PendingSampleCollectionVO pendingSampleColVO;
		List pendingSampleColVOList=new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.PENDING_SAMPLE.";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("InPatientEssentialDAO.getPendingConsentList::populateMAP" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next()) 
			{
				throw new HisRecordNotFoundException("No Record Found");
			}*/
			
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					pendingSampleColVO=new PendingSampleCollectionVO();
					HelperMethods.populateVOfrmRS(pendingSampleColVO, rs);
					pendingSampleColVOList.add(pendingSampleColVO);
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return pendingSampleColVOList;
	}
	
	/**
	 * Getting List of Methods
	 * @param _methodType Method Type
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getMethodList(String _methodType, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.BY_METHOD_TYPE.HANCT_METHOD_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), _methodType);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for "+ InpatientConfig.METHOD_TYPE_ARR[Integer.parseInt(_methodType)]+" Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getMethodList" + e);
		}
		return alRecord;
	}

	/**
	 * Getting List of Labor Rooms
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getLaborRoomList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HANCT_LABOR_ROOM_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Labor Room Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getLaborRoomList" + e);
		}
		return alRecord;
	}

	/**
	 * Getting List of Labor Room Areas
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getLaborRoomAreaList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HANCT_LABORROOM_AREA_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Labor Room Area Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getLaborRoomAreaList" + e);
		}
		return alRecord;
	}

	/**
	 * Getting List of Labor Room Areas of given Area Type
	 * @param _userVO User Detail
	 * @param _areaTypes Area Types
	 * @return
	 */
	public List<Entry> getLaborRoomAreaListByAreaType(UserVO _userVO, String[] _areaTypes)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST_BY_AREATYPE.HANCT_LABORROOM_AREA_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		String at = "";
		for(String areatype : _areaTypes)
			at+=areatype+",";
		if(!at.equals(""))	at = at.substring(0,at.length()-1);
		
		query = query.replace("#", at);
		
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Labor Room Area Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getLaborRoomAreaList" + e);
		}
		return alRecord;
	}

	/**
	 * Getting List of Placenta Types
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getPlacentaTypeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HANCT_PLACENTA_TYPE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Placenta Type Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getPlacentaTypeList" + e);
		}
		return alRecord;
	}
	

	/**
	 * Getting List of Complications
	 * @param _type Complications Type
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getComplicationsList(String _type, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.BY_TYPE.HANCT_COMPLICATION_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), _type);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());

			try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for "+InpatientConfig.COMPLICATION_TYPE_ARR[Integer.parseInt(_type)]+" Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getComplicationsList" + e);
		}
		return alRecord;
	}
	
	

	/**
	 * Getting List of Genders
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getGenderList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.GBLT_GENDER_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Gender Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getGenderList" + e);
		}
		return alRecord;
	}

	/**
	 * Getting List of APGAR Times
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getApgarTimeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.APGAR_TIMES.HPMRT_FLAG_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Apgar Time List Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getApgarTimeList" + e);
		}
		return alRecord;
	}
	
	public List getPendingMonitoringList(UserVO _userVO)
	{
		PatientMonitoringMstVO pendingMonitoringVO;
		List pendingMonitoringVOList=new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.PENDING_VITALS.HIPD_PAT_CLINICAL_DTL";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("InPatientEssentialDAO.getPendingConsentList::populateMAP" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next()) 
			{
				throw new HisRecordNotFoundException("No Record Found");
			}*/
			
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					pendingMonitoringVO=new PatientMonitoringMstVO();
					HelperMethods.populateVOfrmRS(pendingMonitoringVO, rs);
					pendingMonitoringVOList.add(pendingMonitoringVO);
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return pendingMonitoringVOList;
	}
	
	public List<Entry> getPrevInstListForPat(String crNo,String episocode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.PREV_INST.HRGT_EPISODE_EXTTREATMENT_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), OpdConfig.OTHER_INSTRUCTION);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), episocode);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Record for Gender Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getGenderList" + e);
		}
		return alRecord;
	}
	
	public List<Entry> getPrevActivityListForPat(String crNo,String episocode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.PREV_ACTIVITY.HRGT_EPISODE_EXTTREATMENT_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), OpdConfig.ONE_TIME_ACTIVITY);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), episocode);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Record for Gender Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getGenderList" + e);
		}
		return alRecord;
	}

	
	public PatientDetailVO[] getPatientAdmissionDetailsEMR(String _patCrNo,UserVO _userVO)
	{
		//PatientDetailVO patientDetailVO;
		PatientDetailVO[] patientDetailVOs=null;
		ValueObject[] vo=null;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HIPT_PATADMISSION_DTL.ADMMISSIOM_DETAILS_EMR";
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
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_NOT_REPORTED);
			populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMISSION_CANCELLED);
			
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("InPatientEssentialDAO.getPatientAdmissionDetails::populateMAP" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next()) 
			{
				throw new HisRecordNotFoundException("No Record Found");
			}*/
			
			if(rs.next()){
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
				patientDetailVOs = new PatientDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					patientDetailVOs[i] = (PatientDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return patientDetailVOs;
	}
	
	public String getIcdDtls(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_ICD_CODE.HGBT_ICD_DISEASE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		try
		{
			Sequence sq = new Sequence();
	
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Records for ICD Disease Found");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<IcdDiseaseMasterVO> lstDiseases = new ArrayList<IcdDiseaseMasterVO>();
		ValueObject[] vo = {};
		JSONArray mapIcdCodeLst;
		try
		{
			Map<String , List> DiagnosisMap=new HashMap<String , List>(); 
			while(rs.next())
			{ 
				ArrayList<String> DiagnosisList=new ArrayList<String>();
				int ColumnLength=	rs.getMetaData().getColumnCount();
					for(int i=1 ;i<=ColumnLength;i++)
					{
						DiagnosisList.add(rs.getString(i));
					}
					DiagnosisMap.put(rs.getString(1), DiagnosisList); 
				
			}
			mapIcdCodeLst = new JSONArray();   
			for(Map.Entry mapIcdCodeItem:DiagnosisMap.entrySet()){  
                  JSONObject mapIcdCodeLstSubObj = new JSONObject(); 
				  String strCode=(String)mapIcdCodeItem.getKey();
                  ArrayList lst = (ArrayList) mapIcdCodeItem.getValue();
                  mapIcdCodeLstSubObj.put("icdCode", strCode!=null?strCode:"");
                  mapIcdCodeLstSubObj.put("diagnosisName", lst.get(4)!=null?lst.get(4):""); 
                  mapIcdCodeLst.put(mapIcdCodeLstSubObj);
			} 

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :getICDDiseaseCodeList" + e);
		}
		return mapIcdCodeLst.toString();
	}
	
	public List getDischargeStatusList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT_DISCHARGE_STATUS.HIPT_DISCHARGE_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}*/

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	
	public List getTodayExtDetailList(String _patCrNo, String _episodeCode,String _admissionNo ,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.TODAY_EXT_LIST.HRGT_EPISODE_EXTADMIN_DTL";

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
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		populateMAP.put(sq.next(), _admissionNo);
		populateMAP.put(sq.next(), OpdConfig.SCHEDULE);
		

		List todayExtList = new ArrayList();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(ExtAdminDtlVO.class, rs);			
				for (ValueObject v : vo)
					todayExtList.add((ExtAdminDtlVO)v);
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
		return todayExtList;
	}
	
	public List getExecutedExtTreatList(String _patCrNo, String _episodeCode,String _admissionNo ,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.EXEC_EXT_LIST.HRGT_EPISODE_EXTADMIN_DTL";

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
		populateMAP.put(sq.next(), OpdConfig.SCHEDULE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), OpdConfig.EXTERNAL_TREATMENT);
		
		List execExtList = new ArrayList();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(ExtAdminDtlVO.class, rs);			
				for (ValueObject v : vo)
					execExtList.add((ExtAdminDtlVO)v);
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
		return execExtList;
	}
	
	public List getExecutedActivityList(String _patCrNo, String _episodeCode,String _admissionNo ,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT.EXEC_ACTIVITY_LIST.HRGT_EPISODE_EXTADMIN_DTL";

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
		populateMAP.put(sq.next(), OpdConfig.SCHEDULE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), OpdConfig.ONE_TIME_ACTIVITY);
		
		List execExtList = new ArrayList();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(ExtAdminDtlVO.class, rs);			
				for (ValueObject v : vo)
					execExtList.add((ExtAdminDtlVO)v);
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
		return execExtList;
	}

	public List getAllRoleList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_ROLE.HANCT_ROLE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}*/

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	/**
	 * Getting List of Anomoly Types
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getAnomolyTypeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HANCT_ANOMALY_TYPE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Anomoly Type Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getAnomolyTypeList" + e);
		}
		return alRecord;
	}
	
	public String getPatientProfileStatus(PatientDetailVO patientVO,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		String profileStatus="";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "GET_PROFILE_STATUS.HPMRT_PAT_PROFILE_DTL";
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
			populateMAP.put(sq.next(), OpdConfig.PROFILE_TYPE_DISCHARGE);
			populateMAP.put(sq.next(), patientVO.getPatCrNo());
			populateMAP.put(sq.next(), patientVO.getEpisodeCode());
			populateMAP.put(sq.next(), patientVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), patientVO.getPatAdmNo());
			
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				profileStatus="0";
			}
			else
				profileStatus=rs.getString(1); 
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return profileStatus;
	}

	// * Getting Department List of Entry  
	public List<Entry> getAllDepartmentList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_DEPT.GBLT_DEPARTMENT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), InpatientConfig.DEPT_TYPE_CLINICAL_VALUE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("InPatientEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Department record Exists in database  ");
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
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment" + e);
		}
		return alRecord;
	}
	
	public List getBloodGroup(UserVO _userVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(InpatientConfig.PROCEDURE_GET_BLOOD_GROUP);
			strProc.addInParameter(1, Types.VARCHAR, Config.SUPER_USER_HOSPITAL_CODE);
			strProc.addOutParameter(2, Types.VARCHAR);
			strProc.addOutParameter(3, Types.REF);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(2);
			rs = (ResultSet) strProc.getParameterAt(3);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		// log.error(query + "\n");
		/*
		 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
		 */

		List alRecord = new ArrayList();

		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return alRecord;
	}
	
	
	public List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,String _deptUnitCode, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HIPT_DUWRBED_MST.WARD";
		//first call the getQueryMethod with arguments filename,querykey from prop file    		 	  
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), _hosCode);
		populateMAP.put(sq.next(), _deptCode);
		populateMAP.put(sq.next(), _deptUnitCode);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Record against specified Department Not Found");
			}
			else rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}

		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		System.out.println("inside getWardBasedOnDepartment()...." + alRecord);
		return alRecord;

	}
	
	public List getPatientCategoryList(UserVO _userVO)
	{

		ResultSet rs=null;
    	String query =  "" ;
    	Map populateMAP =new HashMap();
    	String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
    	String queryKey="ESSENTIAL.GBLT_PATIENT_CAT_MST.PRIMARY_OLD";
    	
    	//first call the getQueryMethod with arguments filename,querykey from prop file
    	try
    	{
    		query =HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    	}
    
    	Sequence sq=new Sequence();
    	populateMAP.put(sq.next(),InpatientConfig.PATIENT_CAT_TYPE_PRIMARY);
    	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
    	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
    	
    		    	
    	try{
    		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
    		if(!rs.next()){
    			throw new HisRecordNotFoundException("");
    		}
    	}
    	catch(Exception e){
    		if(e.getClass()==HisRecordNotFoundException.class){
    			throw new HisRecordNotFoundException();
    		}
    		else
    			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    	}
    	List alRecord = new ArrayList();
    	try{
    		alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
    	}
    	catch(Exception e){
    		throw new HisDataAccessException(" Master:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);
    	}
    	return alRecord;
	
	}
	
	public List getPrimaryCat(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Inpatient","InpatientEssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1");
			daoObj.setProcInValue(nProcIndex, "p_sup_hosp_code",Config.SUPER_USER_HOSPITAL_CODE);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode());
			daoObj.setProcInValue(nProcIndex, "p_charge_type_id", "0");
			daoObj.setProcInValue(nProcIndex, "p_moduleId", _userVO.getModuleId()!=null?_userVO.getModuleId():"0");
			daoObj.setProcInValue(nProcIndex, "p_seatId", _userVO.getSeatId());
			daoObj.setProcInValue(nProcIndex, "p_effect_from", "");
			daoObj.setProcInValue(nProcIndex, "p_effect_TO", "");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
			{
				throw new HisRecordNotFoundException("No Category Found");
			}
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}
	
	public List getRegistrationCategory()

    {

          System.out.println("inside get srea cat");

          List lstRegCat = new ArrayList();

          List alList = new ArrayList();

          alList.add("Normal");

          alList.add(InpatientConfig.PATIENT_REG_CATEGORY_NORMAL);

          alList.add("Special");

          alList.add(InpatientConfig.PATIENT_REG_CATEGORY_SPECIAL);

          alList.add("Emergency");

          alList.add(InpatientConfig.PATIENT_REG_CATEGORY_EMERGENCY);



          for (int i = 0; i < alList.size();)

          {

                Entry objEntry = new Entry((String) alList.get(i++), (String) alList.get(i++));

                lstRegCat.add(objEntry);

          }

          return lstRegCat;

    }
	
	/**
	 * Retrieves all the departments of a hospital.
	 * @param	_userVO	provides user details
	 * @return	List of the departments.
	 */
	public List getAllHospitalsSeatIDWise(UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_HOSPTIAL_SEAT_WISE.GBLT_HOSPITAL_MST";

		//first call the getQueryMethod with arguments filename,querykey from prop file
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
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Hospitals Found");
			}
			else rs.beforeFirst();
			
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		return alRecord;
	}
	
	public List getAllGlobalDepartment(UserVO userVO) {
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_GLOBAL_DEPT.GBLT_DEPARTMENT_MST";

		//first call the getQueryMethod with arguments filename,querykey from prop file
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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), InpatientConfig.DEPT_TYPE_CLINICAL_VALUE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Department Found");
			}
			else rs.beforeFirst();
			
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		return alRecord;
	}

	/*public Map<String, Object> getAllPatientList_AJAX(String _calMode,PatientDetailVO patientDetailVO, UserVO _userVO, int p_page,int p_limit, String p_sidx, String p_sord, String p_where) 
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;		
		Map<String, Object> populateMAP = new HashMap<String, Object>();
		
		String errorMsg = null;
		
		int $total_pages = 0;
		int $count=0;
		
		String count="";
		System.out.println("InPatientEssentialDAO.getTodayAllPatientList_AJAX()");
		System.out.println("departmentName :"+patientDetailVO.getDepartmentUnitCode());
		System.out.println("visit type :"+patientDetailVO.getEpisodeVisitType());
		System.out.println("room code :"+patientDetailVO.getRoomCode());
		System.out.println("seat id :"+_userVO.getSeatId());
		List<List<String>> alRecord = new ArrayList<List<String>>();
		try
		{
			Procedure strProc = new Procedure(DynamicDeskConfig.PROC_FOR_TODAY_ALL_IPD_PATIENT_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, "");
			strProc.addInParameter(4, Types.VARCHAR, "");
			strProc.addInParameter(5, Types.VARCHAR, "");
			strProc.addInParameter(6, Types.VARCHAR, "");
			strProc.addInParameter(7, Types.VARCHAR, p_where);
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getSeatId());
			strProc.addInParameter(10, Types.VARCHAR, patientDetailVO.getDepartmentUnitCode());
			strProc.addInParameter(11, Types.VARCHAR, patientDetailVO.getEpisodeVisitType());
			strProc.addInParameter(12, Types.VARCHAR, patientDetailVO.getRoomCode());
			strProc.addInParameter(13, Types.VARCHAR, InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
			strProc.addInParameter(14, Types.VARCHAR, InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
			strProc.addInParameter(15, Types.VARCHAR, OpdConfig.YES);
			strProc.addOutParameter(16, Types.VARCHAR);
			strProc.addOutParameter(17, Types.VARCHAR);
			strProc.addOutParameter(18, Types.REF);		
			
			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(16));
			System.out.println("2:"+strProc.getParameterAt(17));
			System.out.println("3:"+strProc.getParameterAt(18));
			errorMsg = (String) strProc.getParameterAt(16);
			//String count = (String) strProc.getParameterAt(12);
			rs = (ResultSet) strProc.getParameterAt(18);
			
			try
			{
				if(rs.next()) count=rs.getString(1); else count="0"; 
			}
			catch (SQLException e)
			{
				throw new HisDataAccessException("InPatientEssentialDAO.getTodayAllPatientList_AJAX()" + e);
			}
			
			System.out.println("count:"+count);
			
			System.out.println("p_page"+p_page);
			System.out.println("p_limit:"+p_limit);
			System.out.println("p_sidx:"+p_sidx);
			System.out.println("p_sord:"+p_sord);
									
			
			$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			
			$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			if($start<0)$start=0;
			
		
			strProc = new Procedure(DynamicDeskConfig.PROC_FOR_TODAY_ALL_IPD_PATIENT_LIST);
			strProc.addInParameter(1, Types.VARCHAR, "2");
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, p_sidx);
			strProc.addInParameter(4, Types.VARCHAR, p_sord);
			strProc.addInParameter(5, Types.VARCHAR, $start+"");
			strProc.addInParameter(6, Types.VARCHAR, p_limit+"");
			strProc.addInParameter(7, Types.VARCHAR, p_where);
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getSeatId());
			strProc.addInParameter(10, Types.VARCHAR, patientDetailVO.getDepartmentUnitCode());
			strProc.addInParameter(11, Types.VARCHAR, patientDetailVO.getEpisodeVisitType());
			strProc.addInParameter(12, Types.VARCHAR, patientDetailVO.getRoomCode());
			strProc.addInParameter(13, Types.VARCHAR, InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
			strProc.addInParameter(14, Types.VARCHAR, InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
			strProc.addInParameter(15, Types.VARCHAR, OpdConfig.YES);
			strProc.addOutParameter(16, Types.VARCHAR);
			strProc.addOutParameter(17, Types.VARCHAR);
			strProc.addOutParameter(18, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(16));
			System.out.println("2:"+strProc.getParameterAt(17));
			System.out.println("3:"+strProc.getParameterAt(18));
			errorMsg = (String) strProc.getParameterAt(16);
			count = (String) strProc.getParameterAt(17);
			rs = (ResultSet) strProc.getParameterAt(18);
			
			System.out.println("getTodayAllPatientList_AJAX end");
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		try
		{
			alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWise(rs);	
			//alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWiseWithColumn(rs);	
			
			System.out.println("$count"+$count);
			System.out.println("$total_pages:"+$total_pages);
			System.out.println("p_page:"+p_page);
						
			
			populateMAP.put("count", $count+"");
			populateMAP.put("total_pages", $total_pages+"");
			populateMAP.put("page", p_page+"");
			populateMAP.put("listObj", alRecord);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("InPatientEssentialDAO.getTodayAllPatientList_AJAX()" + e);
		}
		
		return populateMAP;
	}*/
	
	public List<Entry> getDrugListDetail(HisDAO hisDAO_p, String mode, PatientDetailVO _patDetailVO, UserVO _userVO)
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(OpdConfig.PROC_OPD_VIEW_DRUGS_DETAILS);
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
			throw new HisDataAccessException("InpatientEssentialDAO.getDrugListDetail::hisDAO_p.execute" + OpdConfig.PROC_OPD_VIEW_DRUGS_DETAILS
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) 
				hisDAO_p.free();
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
			throw new HisDataAccessException("InpatientDAO.getDrugListDetail::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

//Added by Vasu on 15.Sept.2018
	public List getDeptUnitListForIPDNursingDesk(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HOPT_DEPT_UNIT_ROSTER_DTL.RETRIEVE_WARDBASIS_BY_SEATID";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getSeatId());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);  //commented by Dheeraj on 28-Dec-2018
			//populateMAP.put(sq.next(), userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	
//Added by Vasu on 15.Sept.2018
	public List getWardOnBasisOfUnitCodeForIPDNursing(String unitCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "WARD_LIST_NURSE.HIPT_DUWRBED_MST.RETRIEVE_BY_DEPTUNITCODE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), unitCode);
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), userVO.getSeatId());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Ward Alloted For This Unit");
			}

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}
		
	
	

}//end of class

