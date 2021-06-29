package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.vo.AudioVideoMasterVO;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartUnitMapppingVO;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.DeskMenuMacroMstVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.DisclaimerMstVO;
import hisglobal.vo.DrugDoseVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.DrugRouteMstVO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeKeywordsMasterVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.HospitalDiseaseMasterVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdGroupMasterVO;
import hisglobal.vo.IcdHospitalMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.OpdClinicalDetailVO;
import hisglobal.vo.OpdTemplateParameterVO;
import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.PatientBelongingVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.RestAdviceDtlVO;
import hisglobal.vo.Service_Req_dtlVO;
//import hisglobal.vo.SinglePageInterfaceMasterVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UnitDrugListMasterVO;
import hisglobal.vo.UnitDrugMstVO;
import hisglobal.vo.UnitExtTreatMstVO;
import hisglobal.vo.UnitImageDescMasterVO;
import hisglobal.vo.UnitWiseMacroMstVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserDeskUnitWardMappingMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
//import hisglobal.vo.clinicalSectionMappingMstVO;
import inpatient.InpatientConfig;
import investigationDesk.InvestigationConfig;
import investigationDesk.vo.viewInvestigationVO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import opd.OpdConfig;
import opd.master.controller.fb.ClinicalSectionCompMapFB;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;
import ehr.EHRConfig;
import emr.vo.EHR_PatientProfileDtlVO;

public class OpdEssentialDAO extends DataAccessObject implements OpdEssentialDAOi
{
	Logger log;

	/**
	 * Constructor for Setting Transaction Context
	 */
	public OpdEssentialDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}

	// /////Method for getting List of all groups from ICD_GROUP_MST

	public List getIcdGroupList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_ICD_GROUP_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	public List getHosDisList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_ICD_GROUP_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}	

	// /////Method for getting List of all sub groups from ICD_SUBGROUP_MST
	/**
	 * get Opd Desk Menu Detail Based On Seat ID And Location (Left Menu/Right Menu)
	 * 
	 * @param _UserVO Provides User details.
	 * @return UserDeskMenuMasterVO[] containing All Details For Menu Display
	 */
	public List getIcdSubGroupList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_ICD_SUBGROUP_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	// /////Method for getting List of all disease from ICD_DISEASE_MST

	public List getIcdDiseaseList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		
		//String queryKey = "ESSENTIAL.DISEASE.HGBT_ICD_DISEASE_MST";
		String queryKey = "ESSENTIAL.DISEASE.HGBT_ICD_DISEASE_MST_HOSP_DISEASE";
		
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	/**?Method Used for getting the Hospital disease List
	 * @param _userVO
	 * @return
	 */
	public List getHosDisDiseaseList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DISEASE.HGBT_HOS_DISEASE_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No records for  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	// /////Method for getting List of all sub disease from ICD_DISEASE_MST

	public List getIcdSubDiseaseList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SUBDISEASE.HGBT_ICD_DISEASE_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	/**Method used for getting the hospital sub disease list
	 * @param _userVO
	 * @return
	 */
	public List getHosDisSubDiseaseList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SUBDISEASE.HGBT_HOSDIS_DISEASE_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	
	
	
	
	

	// /////Method for getting List of all groups For Department from DEPT_ICD_MST

	public List getIcdGroupListRemovalDeptWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.GROUP.LIST.DEPT.WISE.HGBT_DEPT_ICD_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	// ///Method for getting List of all sub groups For Department from DEPT_ICD_MST

	public List getIcdSubGroupListRemovalDeptWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SUBGROUP.LIST.DEPT.WISE.HGBT_DEPT_ICD_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	// ///Method for getting List of all disease For Department from DEPT_ICD_MST

	public List getIcdDiseaseListRemovalDeptWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DISEASE.LIST.DEPT.WISE.HGBT_DEPT_ICD_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	//Function used for getting the list of Removal details department wise in case the 
	//choice department is selected and choice selected is Disease
	public List getHosDisDiseaseListRemovalDeptWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DISEASE.LIST.DEPT.WISE.HGBT_DEPT_HOSDIS_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	
	
	
	
	

	// ///Method for getting List of all sub disease For Department from DEPT_ICD_MST

	public List getIcdSubDiseaseListRemovalDeptWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SUBDISEASE.LIST.DEPT.WISE.HGBT_DEPT_ICD_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	//Function used for getting the list of Removal details department wise in case the 
	//choice department is selected and choice selected is SubDisease
	
	public List getHosDisSubDiseaseListRemovalDeptWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SUBDISEASE.LIST.DEPT.WISE.HGBT_DEPT_HOSDIS_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}


	// /////Method for getting List of all groups For Unit from DEPT_ICD_MST

	public List getIcdGroupListRemovalUnitWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.GROUP.LIST.UNIT.WISE.HGBT_DEPT_ICD_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	// ///Method for getting List of all sub groups For Unit from DEPT_ICD_MST

	public List getIcdSubGroupListRemovalUnitWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SUBGROUP.LIST.UNIT.WISE.HGBT_DEPT_ICD_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	// ///Method for getting List of all disease For Unit from DEPT_ICD_MST

	public List getIcdDiseaseListRemovalUnitWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DISEASE.LIST.UNIT.WISE.HGBT_DEPT_ICD_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	//Function used for getting the list of Removal details Unit wise in case the 
	//choice Unit is selected and choice selected is Disease
	
	public List getHosDisDiseaseListRemovalUnitWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DISEASE.LIST.UNIT.WISE.HGBT_DEPT_HOSDIS_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	// ///Method for getting List of all sub disease For Unit from DEPT_ICD_MST

	public List getIcdSubDiseaseListRemovalUnitWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SUBDISEASE.LIST.UNIT.WISE.HGBT_DEPT_ICD_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	//Function used for getting the list of Removal details Unit wise in case the 
	//choice Unit is selected and choice selected is SubDisease
	public List getHosDisSubDiseaseListRemovalUnitWise(String _code, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SUBDISEASE.LIST.UNIT.WISE.HGBT_DEPT_HOSDIS_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _code);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}


	// //List icd DiagnosisCode Unit Wise////

	public List getIcdDiagnosisCodeListByUnit(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		// String queryKey="ESSENTIAL.DIAGNOSIS.CODE.UNIT.WISE.HGBT_DEPT_ICD_MST"; Fetching Data from DEPT_ICD_MST
		String queryKey = "ESSENTIAL.DIAGNOSIS.CODE.HGBT_ICD_DISEASE_MST"; // fetching data from ICD_DISEASE_MST

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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		/*
		 * populateMAP.put(sq.next(),_code); populateMAP.put(sq.next(),_code); populateMAP.put(sq.next(),_code);
		 */
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	// //List ICD Diagnosis Codes Only////
	public List<Entry> getICDDiagnosisCodesOnlyList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DIAGNOSIS_CODE_ONLY.HGBT_ICD_DISEASE_MST";

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
			if (rs.next())	alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getICDDiagnosisCodesOnlyList" + e);
		}
		return alRecord;
	}

	// //List Hospital DiagnosisCode////

	public List getHospitalDiagnosisCodeList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_HOSPITAL_DIAGNOSIS.HGBT_HOSPITAL_DISEASE_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Hospital Code Found for Unit");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	public List getUnitWiseHospitalDiagnosisCodeList(String _unitCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNIT_HOS_DISEASE_LIST.HGBT_DEPT_HOSDISEASE_MST";
		
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _unitCode);
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getUnitWiseHospitalDiagnosisCodeList" + e);
		}
		return alRecord;
	}

	
	// *
	public DeskDetailVO[] getMenuDtlBySeatId(UserVO _userVO, String location, String unitCode, String deskType)
	{
		ValueObject[] vo =
		{};
		DeskDetailVO[] deskDetailVOs = null;
		System.out.println("inside getMenuDtlBySeatId");
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "OPDESSENTIAL.MENU_DTL.GBLT_USER_DESKMENU_MST";
		// first call the getQueryMethod with arguments filename,query key from prop file
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
		/*
		 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
		 */

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), location);
		populateMAP.put(sq.next(), deskType);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No records for this Location ");
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
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(DeskDetailVO.class, rs);
			System.out.println("length" + vo.length);
			deskDetailVOs = new DeskDetailVO[vo.length];
			System.out.println("_patientVO.length:: " + deskDetailVOs.length);
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				deskDetailVOs[i] = (DeskDetailVO) vo[i];

			}
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		System.out.println("alRecord primary. cat" + alRecord);
		return deskDetailVOs;
	}

	// * Getting Units By Seat Id
	public List getUnitsBySeatId(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HOPT_DEPT_UNIT_ROSTER_DTL.RETRIEVE_UNITS_BY_SEATID";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			//updated by Dheeraj on 03-Jan-2019
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);   
			//populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
			//populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _userVO.getSeatId());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			/*populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
			populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);*/
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
				throw new HisRecordNotFoundException("No Unit Allotted For This Seat ID");
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
		/*
		 * finally{ try{if(true) throw new HisRecordNotFoundException("No Department with any Units functioning"); }
		 * catch(Exception e){ throw new HisRecordNotFoundException("No Department with any Units functioning"); }}
		 */
		return alRecord;
	}

	// *
	public List getRoomsByUnitCode(UserVO _userVO, String unitCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_ROOM_MST.RETRIEVE_BY_DEPTUNITCODE";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), unitCode);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Room Allotted For This Unit");
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

	// * Getting Department List
	public List getAllDepartmentList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
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
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
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

	// Get Seat List
	public List getAllSeats(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_SEATS.GBLT_SEAT_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getAllSeats" + e);
		}
		return alRecord;
	}

	// Get Seat List of given Department
	public List getSeatsByDepartemnt(UserVO _userVO, String _DeptCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SEATS_BY_DEPT.GBLT_SEAT_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _DeptCode);
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
			throw new HisDataAccessException("HisDataAccessException  :getSeatsByDepartemnt" + e);
		}
		return alRecord;
	}

	// * Getting Desk Menu List
	public List getMenusList(String _deskType, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.MENUS_ALL.GBLT_DESK_MENU_MST";

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _deskType);
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
			throw new HisDataAccessException("HisDataAccessException  :getMenusList" + e);
		}
		return alRecord;
	}

	// Get System Date
	public String getSystemDate()
	{
		ResultSet rs = null;
		String date = "";
		String query = "";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.SYSTEM_DATE";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		List alRecord = new ArrayList();
		System.out.println("query" + query);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}

		try
		{
			ResultSetMetaData rsmd = rs.getMetaData();
			int no_of_col = rsmd.getColumnCount();
			while (rs.next())
			{
				for (int i = 1; i <= no_of_col; i++)
				{
					alRecord.add(rs.getString(i));
				}
			}
			Calendar cl = Calendar.getInstance();
			cl.set(Calendar.DATE, Integer.parseInt((String) alRecord.get(0)));
			cl.set(Calendar.MONTH, Integer.parseInt((String) alRecord.get(1)) - 1);
			cl.set(Calendar.YEAR, Integer.parseInt((String) alRecord.get(2)));
			cl.set(Calendar.HOUR, Integer.parseInt((String) alRecord.get(3)));
			cl.set(Calendar.MINUTE, Integer.parseInt((String) alRecord.get(4)));
			cl.set(Calendar.SECOND, Integer.parseInt((String) alRecord.get(5)));
			Date dt = cl.getTime();
			SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
			sf.applyPattern("dd/MM/yyyy HH:mm");
			date = sf.format(dt);
			System.out.println("date in dao" + date);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:resultset to list" + e);
		}
		return date;
	}

	// ** End of Get System Date ***************************************

	public List getIcdCodes(String searchIcdCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.BY.ICD.CODE.HGBT_DEPT_ICD_MST";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), searchIcdCode.trim() + "%");
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No such ICD code Found in the database ");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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

	public List getDiseaseName(String _searchDisease, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.BY.DISEASE.NAME.HGBT_DEPT_ICD_MST";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _searchDisease.trim() + "%");
		// populateMAP.put(sq.next(),_unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No such disease  Found in the database ");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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

	public List<IcdDiseaseMasterVO> getICDDiseaseByCode(String _strICDCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.SEARCH_BY_ICD_CODE.HGBT_DEPT_ICD_MST";

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
			populateMAP.put(sq.next(), _strICDCode.trim() + "%");
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No ICD Code Found");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<IcdDiseaseMasterVO> lstDiseases = new ArrayList<IcdDiseaseMasterVO>();
		ValueObject[] vo = {};
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(IcdDiseaseMasterVO.class,rs);
			for(ValueObject v :vo)
				lstDiseases.add((IcdDiseaseMasterVO)v);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return lstDiseases;
	}

	public List<IcdDiseaseMasterVO> getICDDiseaseByName(String _strICDDiseaseName, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.SEARCH_BY_ICD_NAME.HGBT_DEPT_ICD_MST";

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
			populateMAP.put(sq.next(), _strICDDiseaseName);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No ICD Code Found");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<IcdDiseaseMasterVO> lstDiseases = new ArrayList<IcdDiseaseMasterVO>();
		ValueObject[] vo = {};
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(IcdDiseaseMasterVO.class,rs);
			for(ValueObject v :vo)
				lstDiseases.add((IcdDiseaseMasterVO)v);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return lstDiseases;
	}

	public List<IcdDiseaseMasterVO> getICDSubDiseaseByCode(String _strICDCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.SUB_DISEASE_BY_PARENT_ICD_CODE.HGBT_DEPT_ICD_MST";

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
			populateMAP.put(sq.next(), _strICDCode);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<IcdDiseaseMasterVO> lstDiseases = new ArrayList<IcdDiseaseMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdDiseaseMasterVO.class,rs);
				for(ValueObject v :vo)
					lstDiseases.add((IcdDiseaseMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return lstDiseases;
	}
	// /////Getting hospital diagnosis code and name according to the search criteria

	public List getHospitalDiagnosisCodes(String searchIcdCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.BY.HOSPITAL.DIAGNOSIS.CODE.HGBT_HOSPITAL_DISEASE_MST";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), searchIcdCode.trim() + "%");
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No such ICD code Found in the database ");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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

	public List getHospitalDiseaseName(String _searchDisease, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.BY.HOSPITAL.DIAGNOSIS.NAME.HGBT_HOSPITAL_DISEASE_MST";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _searchDisease.trim() + "%");
		populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No such disease  Found in the database ");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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

	// * Getting All Units List in Dept (Unit) form
	public List getAllUnitList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_UNIT.HGBT_UNIT_MST";

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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	// * Get All Seats that are not assigned a desk for given Units //**** Now Users
	
	// Change By Chetan According to Global Mapping Concept
	//public List getSeatsNotforUnits(String[] _UnitsList, String deskType, UserVO _userVO, String groupCode)
	public List getSeatsNotforUnits(String deskType, UserVO _userVO, String groupCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		//String queryKey = "SELECT.UNITS_NOT_ASSIGNED_DESK.GBLT_SEAT_MST";
		String queryKey = "SELECT.UNITS_NOT_ASSIGNED_DESK.GBLT_USER_MST";// from user master in place of Seat

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

		/*String unitStr = "";
		int i = 0;
		for (i = 0; i < _UnitsList.length - 1; i++)
			unitStr += _UnitsList[i].toString().split("@")[0] + ",";
		unitStr += _UnitsList[i].toString().split("@")[0];
		query = query.replace("@", unitStr);
		System.out.println("   -------> query :: " + query);*/

		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), groupCode);
			populateMAP.put(sq.next(), deskType);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getSeatsNotforUnits" + e);
		}
		return alRecord;
	}

	public List getSeatsNotforUnits(String[] _UnitsList, UserVO _userVO, String groupCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.UNITS_NOT_ASSIGNED_DESK.WITHOUTDESKTYPE.GBLT_SEAT_MST";

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

		String unitStr = "";
		int i = 0;
		for (i = 0; i < _UnitsList.length - 1; i++)
			unitStr += _UnitsList[i].toString().split("@")[0] + ",";
		unitStr += _UnitsList[i].toString().split("@")[0];
		query = query.replace("@", unitStr);
		System.out.println("   -------> query :: " + query);

		Sequence sq = new Sequence();

		try
		{
			//populateMAP.put(sq.next(), deskType);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), groupCode);
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
			throw new HisDataAccessException("HisDataAccessException  :getSeatsNotforUnits" + e);
		}
		return alRecord;
	}

	
	// * Getting All Desk By given Desk Type
	public List getDeskByType(String _DeskType, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.DESK_BY_TYPE.GBLT_DESK_MST";

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
			populateMAP.put(sq.next(), _DeskType);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.USER_DESK_IS_DEFAULT_NO);
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
			throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
		}
		return alRecord;
	}
	
	public List getDeskBasedOnDeskType(String _DeskType, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.DESK_BASEDON_TYPE.GBLT_DESK_MST";

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
			populateMAP.put(sq.next(), _DeskType);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
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
			throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
		}
		return alRecord;
	}
	
	public List getMappedUnits(String _DeskId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.MAPPEDUNITS.GBLT_USER_DESKMENU_TEMPLATE";

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
			populateMAP.put(sq.next(), _DeskId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
					
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
			throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
		}
		return alRecord;
	}
	
	public List getMappedUnitsForUnitSeat(String _DeskId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.MAPPEDUNITS.FORUNITSEAT.GBLT_USER_DESKMENU_TEMPLATE";

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
			populateMAP.put(sq.next(), _DeskId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
					
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
			throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
		}
		return alRecord;
	}
	
	public List getMappedUnitsForUnitSeatWard(String _DeskId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.MAPPEDUNITS.FORUNITSEATWARD.GBLT_USER_DESKMENU_TEMPLATE";

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
			populateMAP.put(sq.next(), _DeskId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
					
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
			throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
		}
		return alRecord;
	}
	
	public List getMappedUnitsForWard(String _DeskId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.MAPPEDUNITS.FORWARD.GBLT_USER_DESKMENU_TEMPLATE";

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
			populateMAP.put(sq.next(), _DeskId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
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
			throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
		}
		return alRecord;
	}
	
	public List getMappedUnitForUnitSeat(String _DeskId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.MAPPEDUNITS.GBLT_USER_DESKMENU_TEMPLATE";

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
			populateMAP.put(sq.next(), _DeskId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
					
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
			throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
		}
		return alRecord;
	}

	public EpisodeDiagnosisVO[] getPrevDiagnosisDetail(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		EpisodeDiagnosisVO[] _previousEpisodeDiagVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS.DIAGNOSIS.HRGT_EPISODE_DIAGNOSIS_DTL";

		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record for previous diagnosis found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeDiagnosisVO.class, rs);
			_previousEpisodeDiagVO = new EpisodeDiagnosisVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_previousEpisodeDiagVO[i] = (EpisodeDiagnosisVO) vo[i];
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

	public List getCosultantListForMail(UserVO _userVO)
	{
		//Map populateMAP = new HashMap();
		ResultSet rs = null;
		//String query = "";
		//Sequence sq = new Sequence();
		//String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		//String queryKey = "ESSENTIAL.DOCTOR_LIST.HOPT_CONSULTATION_DTL";
		String errorMsg="";
		try
		{
			Procedure strProc = new Procedure(OpdDaoConfig.PROCEDURE_GET_ECONSULTANT_USER);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, "opdDeskLogin.cnt");
			strProc.addOutParameter(3, Types.VARCHAR);
			strProc.addOutParameter(4, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(3);
			rs = (ResultSet) strProc.getParameterAt(4);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"PROCEDURE_GET_ECONSULTANT_USER::" + e);
		}
		
		
		
		try
		{
		
			//if (!rs.next()) throw new HisRecordNotFoundException("No Econsultant record Exists in database  ");
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
			//rs.beforeFirst(); 
			while(rs.next())
			{
				List list=new ArrayList();
				list.add(rs.getString(3));
				list.add(rs.getString(2));
				list.add(rs.getString(1));
				alRecord.add(list);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getConsultant" + e+errorMsg);
		}
		return alRecord;
	}
/*	public List getDeptList(UserVO _userVO)
	{
		
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_REFER_DEPARTMENT_LIST(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);			
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_dept_type_clinical",RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE,3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				//throw new HisRecordNotFoundException("No Refer Department Found");
				webRs.beforeFirst();
			if (strErr.equals("")) 
			{
				System.out.println("getReferDepartmentList b4 throw");
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
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

	}*/
	public List getAllUnitList1(UserVO _userVO)
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

	public List getCosultantListWithNoAndSeatId(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIALS.DOCTOR_LIST_WITH_SEADTID_EMPNO.HOPT_CONSULTATION_DTL";

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

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query);
			if (!rs.next()) throw new HisRecordNotFoundException("No Consultant Found");
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

	public List getAllergyTypeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALLERGY_TYPE.HGBT_ALLERGY_TYPE_MST";

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

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No record for Allergy Type Exists in ALLERGIES_TYPE_MST  ");
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

	public List getListAllergyReason(String _allergyCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALLERGY_REASON.HGBT_ALLERGY_MST";

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

		populateMAP.put(sq.next(), _allergyCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No record for Allergy Reason Exists");
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

	public List getListSensitivity(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALLERGY_SENSITIVITY.HGBT_ALLERGY_SENSITIVITY_MST";

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

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No record for Sensitivity Exists in HGBT_ALLERGIES_SENSITIVITY_MST  ");
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

	public List getCosultantListNameAndNoBySeatId(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.DOCTOR_DETAIL_BY_SEATID.GBLT_USER_MST";

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
		populateMAP.put(sq.next(), _userVO.getSeatId());

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

	// * Getting Parameter List
	public List getAllTemplateParametersList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_PARAMETERS.HGBT_PARAMETER_MST";

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
			throw new HisDataAccessException("HisDataAccessException  :getAllTemplateParametersList" + e);
		}
		return alRecord;
	}

	// * Getting Templates List
	public List getAllTemplateList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_TEMPLATE.HGBT_TEMPLATE_MST";

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
			throw new HisDataAccessException("HisDataAccessException  :getAllTemplateList" + e);
		}
		return alRecord;
	}

	// Get Template Parameter List
	public List getTemplateParaListByTempId(String _tempId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PARA_LIST_BY_TEMP_ID.HGBT_TEMPLATE_PARA_MST";

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
			populateMAP.put(sq.next(), _tempId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getTemplateParaListByTempId" + e);
		}
		return alRecord;
	}

	// * Getting Template Parameter Detail List
	public List getTemplateParaDetailListByTempId(String _tempId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_PARA_BY_TEMP_ID.HGBT_TEMPLATE_PARA_MST";

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
			populateMAP.put(sq.next(), _tempId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
		ArrayList alRecord = new ArrayList();
		try
		{
			while (rs.next())
			{
				OpdTemplateParameterVO obj = new OpdTemplateParameterVO();
				obj.setTemplateId(rs.getString(1));
				obj.setParaId(rs.getString(2));
				obj.setParentParaId(rs.getString(3));
				obj.setLocationId(rs.getString(4));
				obj.setRowId(rs.getString(5));
				obj.setColumnId(rs.getString(6));
				obj.setValueObjId(rs.getString(7));
				obj.setValueObjProp(rs.getString(8));
				obj.setParaValue(rs.getString(9));
				obj.setSourceFlag(rs.getString(10));
				obj.setTableQuery(rs.getString(11));
				obj.setColspan(rs.getString(12));
				obj.setIsCompulsory(rs.getString(13));
				obj.setIsRange(rs.getString(14));

				alRecord.add(obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getTemplateParaDetailListByTempId" + e);
		}
		return alRecord;
	}

	// * Get Template Parameter Dynamic Data
	public List getParameterDynamicData(String _query, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = _query;
		Map populateMAP = new HashMap();

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,populateMAP);
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
			throw new HisDataAccessException("HisDataAccessException  :getParameterDynamicData" + e);
		}
		return alRecord;
	}

	// Get All Patient List
	public List getAllPatientParaCrNoList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.CRNO_LIST.HOPT_PATIENT_PARA_DTL";

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

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query);
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
			throw new HisDataAccessException("HisDataAccessException  :getAllPatientParaCrNoList" + e);
		}
		return alRecord;
	}

	// Get All Patient Template List
	public List getTemplateListByCrNo(String _crNo, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_TEMP_BY_CRNO.HOPT_PATIENT_PARA_DTL";

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
			populateMAP.put(sq.next(), _crNo);
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
			throw new HisDataAccessException("HisDataAccessException  :getTemplateListByCrNo" + e);
		}
		return alRecord;
	}

	// Get All Patient Parameter List
	public List getTempParaListByCrNo(String _crNo, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PARA_LIST_BY_CRNO.HOPT_PATIENT_PARA_DTL";

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
			populateMAP.put(sq.next(), _crNo);
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
			while (rs.next())
				alRecord.add(rs.getString(1));
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getTempParaListByCrNo" + e);
		}

		query = "";

		populateMAP = new HashMap();
		queryKey = "SELECT.PARA_IN_LIST.HGBT_PARAMETER_MST";

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

		String str = "";
		for (int i = 0; i < alRecord.size(); i++)
		{
			String splited[] = alRecord.get(i).toString().trim().split("#");
			for (int j = 0; j < splited.length; j++)
				str += "'" + splited[j] + "',";
		}
		if (!str.equals("")) str = str.substring(0, str.length() - 1);
		query = query.replace("@", str);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		alRecord = new ArrayList();
		try
		{
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getTempParaListByCrNo" + e);
		}
		return alRecord;
	}

	// Get Patient Parameter Value List
	public List getActualTempIdsList(String _crNo, String[] _aPV, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ACTUAL_TEMP.HOPT_PATIENT_PARA_DTL";

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

		String tempStr = "";
		for (int i = 0; i < _aPV.length; i++)
			tempStr += "OR HGNUM_PARA_ID LIKE '%" + _aPV[i] + "%' ";
		query = query.replace("@", tempStr);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _crNo);
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
			while (rs.next())
				alRecord.add(rs.getString(1));
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getActualTempIdsList" + e);
		}
		return alRecord;
	}

	// Get Patient Parameter Value List
	public List getPatActualParaValuesList(String _crNo, String[] _aPV, UserVO _userVO)
	{
		ResultSet rs = null;
		List alRecord = new ArrayList();
		String query = "";

		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ACTUAL_PARA_VALUES.HOPT_PATIENT_PARA_DTL";

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

		String tempStr = "";
		Sequence sq = new Sequence();

		try
		{
			for (int i = 0; i < _aPV.length; i++)
				tempStr += " AND HGNUM_PARA_ID LIKE '%" + _aPV[i] + "%'";
			query = query.replace("@", tempStr);
			populateMAP.put(sq.next(), _crNo);
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
			rs.first();
			if (Integer.parseInt(rs.getString(1)) == 0) return alRecord;
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getPatActualParaValuesList" + e);
		}

		populateMAP = new HashMap();

		try
		{
			query = query + tempStr; // query.replace("@",tempStr);
			sq = new Sequence();
			populateMAP.put(sq.next(), _crNo);
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
			rs.first();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getPatActualParaValuesList" + e);
		}

		return alRecord;
	}

	// Get Patient Parameter Value List
	public List getParaValueList(String _crNo, String[] _aPV, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";

		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.VALUES_BY_CRNO.HOPT_PATIENT_PARA_DTL";

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
			populateMAP.put(sq.next(), _crNo);
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
			throw new HisDataAccessException("HisDataAccessException  :getParaValueList" + e);
		}
		return alRecord;
	}

	// * Getting Desk Menu Template List for Current Active Desk
	public List getOpdDeskMenuTemplates(String _unitCode, String _deskMenuId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";

		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.TEMPIDS_BY_SEATUNITMENU.GBLT_USER_DESKMENU_TEMPLATE";

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
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _unitCode);
			populateMAP.put(sq.next(), _deskMenuId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getOpdDeskMenuTemplates" + e);
		}
		return alRecord;
	}

	public List getVerificationDocumentsList()
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "OPD_ESSENTIAL.GBLT_VERIFICATION_DOCUMENT_MST";
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
			throw new HisDataAccessException("VerificationDocumentsDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	// * Getting All Units List in Dept (Unit) form that are in User Desk Menu Master
	public List getAllUserDeskMenuMasterUnitsList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_USER_DESKMENU_UNIT.HGBT_UNIT_MST";

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
			throw new HisDataAccessException("HisDataAccessException  :getAllUserDeskMenuMasterUnitsList" + e);
		}
		return alRecord;
	}

	// /////Method for getting List of All ServiceReq For CrNo

	public List getAllServiceReqForCrNo(DailyPatientVO selectedPatientVO)
	{
		ResultSet rs = null;
		String query = "";
		List alRecord = new ArrayList();
		alRecord = null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELET.ALL.SERVICE.REQ.NO.FOR.CRNO.HOPT_SERVICE_REQ__DTL";
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
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), selectedPatientVO.getPatCrNo());
		populateMAP.put(sq.next(), OpdConfig.SERVICE_STATUS_SERVICE_PROVIDED);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) return alRecord;
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
			alRecord = this.populateMapListForServiceReqDtl(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	public List populateMapListForServiceReqDtl(ResultSet rs) throws SQLException
	{
		List temp = new ArrayList();
		rs.beforeFirst();
		while (rs.next())
		{
			Service_Req_dtlVO objVO = new Service_Req_dtlVO();
			HelperMethods.populateVOfrmRS(objVO, rs);
			temp.add(objVO);
		}
		return temp;
	}

	// * Getting Desk Type List
	public List getDeskType(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.GBLT_DESK_TYPE_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("OpdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	// * Getting Desk Type Description
	public String getDeskTypeDesc(String deskType, UserVO _userVO)
	{
		String deskTypeName = "";
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.DESC.GBLT_DESK_TYPE_MST";
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), deskType);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Desk Type Record");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				deskTypeName = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("OpdEssentialDAO:Desk Type Description :: " + e);
		}
		return deskTypeName;
	}

	// * Setting Source Seats List not already added a Template for Selected Units
	// * Getting User Seats from User Desk Menu Master common to all Selected Units
	public List getUserDeskMenuSeatsInAllUnits(String[] _UnitsList, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		List voUDMT = new ArrayList();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.USERDESK_SEATS_IN_UNITS.GBLT_USER_DESKMENU_MST";

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
		String unitStr = "";
		int i = 0;
		Sequence sq = new Sequence();
		try
		{
			for (i = 0; i < _UnitsList.length - 1; i++)
				unitStr += _UnitsList[i].toString() + ",";
			unitStr += _UnitsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			if (rs.next())
			voUDMT = HelperMethodsDAO.getAlOfEntryObjects(rs);	 	
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :getUserDeskMenuSeatsInAllUnits" + e);
		}
		return voUDMT;
	}

	//Getting seats list for unit-ward-seat wise mode
	public List getUserDeskMenuSeats(String[] _UnitsList,String[] _WardsList, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		List voUDMT = new ArrayList();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.USERDESK_SEATS.GBLT_USER_DESKMENU_MST";

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
		String wardStr = "";
		int i = 0;
		Sequence sq = new Sequence();
		try
		{
			for (i = 0; i < _WardsList.length - 1; i++)
				wardStr += _WardsList[i].toString() + ",";
			wardStr += _WardsList[i].toString();
			query = query.replace("@", wardStr);
			query = query.replace("*", String.valueOf(_WardsList.length));
			
			populateMAP.put(sq.next(), _UnitsList[0]);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			if (rs.next())
			voUDMT = HelperMethodsDAO.getAlOfEntryObjects(rs);	 	
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :getUserDeskMenuSeatsInAllUnits" + e);
		}
		return voUDMT;
	}
	

	// * Setting Source Seats List not already added a Template for Selected Units
	// * Getting User Seats from User Desk Menu Master common to all Selected Units
	public UserDeskMenuTemplateMasterVO getUserDeskMenuSeats(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		//UserDeskMenuTemplateMasterVO voUDMT = new UserDeskMenuTemplateMasterVO();
		UserDeskMenuTemplateMasterVO seatDetails = new UserDeskMenuTemplateMasterVO();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.USERDESK_SEATSFORMODIFY.GBLT_USER_DESKMENU_MST";

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
		//String unitStr = "";
		//int i = 0;
		Sequence sq = new Sequence();
		try
		{
		/*	for (i = 0; i < _UnitsList.length - 1; i++)
				unitStr += _UnitsList[i].toString() + ",";
			unitStr += _UnitsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));*/

			populateMAP.put(sq.next(), _voUDMT.getSlNo());
		
			populateMAP.put(sq.next(), _voUDMT.getHospCode());
			
			
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
			while(rs.next())
			{
				seatDetails.setUserSeatId(rs.getString(1));
				seatDetails.setDeptUnitCode(rs.getString(2));
				seatDetails.setSeatName(rs.getString(3));
			}
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :getUserDeskMenuSeatsInAllUnits" + e);
		}
		return seatDetails;
	}
	
	//Getting ward list for unit-ward wise mode
	public UserDeskMenuTemplateMasterVO getUserDeskMenuWards(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		//UserDeskMenuTemplateMasterVO voUDMT = new UserDeskMenuTemplateMasterVO();
		UserDeskMenuTemplateMasterVO seatDetails = new UserDeskMenuTemplateMasterVO();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.USERDESK_WARDS.GBLT_USER_DESKMENU_MST";

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
		//String unitStr = "";
		//int i = 0;
		Sequence sq = new Sequence();
		try
		{
		/*	for (i = 0; i < _UnitsList.length - 1; i++)
				unitStr += _UnitsList[i].toString() + ",";
			unitStr += _UnitsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));*/

			populateMAP.put(sq.next(), _voUDMT.getSlNo());
		
			populateMAP.put(sq.next(), _voUDMT.getHospCode());
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
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
			while(rs.next())
			{
				seatDetails.setWardCode(rs.getString(1));
				seatDetails.setDeptUnitCode(rs.getString(2));
				seatDetails.setWardName(rs.getString(3));
			}
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :getUserDeskMenuSeatsInAllUnits" + e);
		}
		return seatDetails;
	}

	// * Getting Desk List By Seats and Units
	public List getUserDeskMenuDesksInAllUnitsnSeats(String[] _UnitsList, String[] _SeatsList, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.USERDESK_DESKS_IN_UNITSSEATS.GBLT_USER_DESKMENU_MST";

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
		String unitStr = "";
		int i;
		for (i = 0; i < _UnitsList.length - 1; i++)
			unitStr += _UnitsList[i].toString() + ",";
		unitStr += _UnitsList[i].toString();

		String seatsStr = "";
		Sequence sq = new Sequence();
		try
		{
			for (i = 0; i < _SeatsList.length - 1; i++)
				seatsStr += _SeatsList[i].toString() + ",";
			seatsStr += _SeatsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));
			query = query.replace("%", seatsStr);
			query = query.replace("*", String.valueOf(_SeatsList.length));
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getUserDeskMenuDesksInAllUnitsnSeats" + e);
		}
		return alRecord;
	}
	
	//Getting desk for unit-ward wise mode
	public List getUserDeskMenuDesksInAllUnitsnWards(String[] _UnitsList, String[] _WardsList, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.USERDESK_DESKS_IN_UNITSWARDS.GBLT_USER_DESKMENU_MST";

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
		String unitStr = "";
		int i;
		for (i = 0; i < _UnitsList.length - 1; i++)
			unitStr += _UnitsList[i].toString() + ",";
		unitStr += _UnitsList[i].toString();

		String wardsStr = "";
		Sequence sq = new Sequence();
		try
		{
			for (i = 0; i < _WardsList.length - 1; i++)
				wardsStr += _WardsList[i].toString() + ",";
			wardsStr += _WardsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));
			query = query.replace("%", wardsStr);
			query = query.replace("*", String.valueOf(_WardsList.length));
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getUserDeskMenuDesksInAllUnitsnSeats" + e);
		}
		return alRecord;
	}
	
	//Getting desk for unit-ward-seat wise mode
	public List getUserDeskMenuDesksInAllUnitsnWardsSeat(String[] _UnitsList, String[] _WardsList,String[] _SeatsList, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.USERDESK_DESKS_IN_UNITSWARDSSEAT.GBLT_USER_DESKMENU_MST";

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
		String unitStr = "";
		int i;
		for (i = 0; i < _UnitsList.length - 1; i++)
			unitStr += _UnitsList[i].toString() + ",";
		unitStr += _UnitsList[i].toString();

		String wardsStr = "";
		String seatsStr="";
		
		Sequence sq = new Sequence();
		try
		{
			for (i = 0; i < _WardsList.length - 1; i++)
				wardsStr += _WardsList[i].toString() + ",";
			wardsStr += _WardsList[i].toString();
			
			for (i = 0; i < _SeatsList.length - 1; i++)
				seatsStr += _SeatsList[i].toString() + ",";
			seatsStr += _SeatsList[i].toString();
					
			query = query.replace("@", wardsStr);
			query = query.replace("%", String.valueOf(_SeatsList.length));
			query = query.replace("#", seatsStr);
			query = query.replace("*", String.valueOf(_WardsList.length));
			
			populateMAP.put(sq.next(), _UnitsList[0]);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getUserDeskMenuDesksInAllUnitsnSeats" + e);
		}
		return alRecord;
	}
	
	//Getting ward list for unit-ward wise mode
	public List getWardsInAllUnits(String[] _UnitsList, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.WARDS_INALLUNITS_NOTSEAT.GBLT_USER_DESKMENU_MST";

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
		String unitStr = "";
		int i;
		for (i = 0; i < _UnitsList.length - 1; i++)
			unitStr += _UnitsList[i].toString() + ",";
		unitStr += _UnitsList[i].toString();

		//String seatsStr = "";
		Sequence sq = new Sequence();
		try
		{
			/*for (i = 0; i < _SeatsList.length - 1; i++)
				seatsStr += _SeatsList[i].toString() + ","; */
		//	seatsStr += _SeatsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));
			//query = query.replace("%", seatsStr);
			//query = query.replace("*", String.valueOf(_SeatsList.length));
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
		List alRecord = null;
		try
		{
			alRecord = new ArrayList();
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getUserDeskMenuDesksInAllUnits" + e);
		}
		return alRecord;
	}
	
	//GEtting wards list for unit-ward-seat wise mode
	public List getWardsForWardSeatWise(String[] _UnitsList, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.WARDS_INALLUNITS.GBLT_USER_DESKMENU_MST";

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
		String unitStr = "";
		int i;
		for (i = 0; i < _UnitsList.length - 1; i++)
			unitStr += _UnitsList[i].toString() + ",";
		unitStr += _UnitsList[i].toString();

		//String seatsStr = "";
		Sequence sq = new Sequence();
		try
		{
			/*for (i = 0; i < _SeatsList.length - 1; i++)
				seatsStr += _SeatsList[i].toString() + ","; */
		//	seatsStr += _SeatsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));
			//query = query.replace("%", seatsStr);
			//query = query.replace("*", String.valueOf(_SeatsList.length));
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
		List alRecord = null;
		try
		{
			alRecord = new ArrayList();
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getUserDeskMenuDesksInAllUnits" + e);
		}
		return alRecord;
	}
	
	// * Getting Desk List By Seats 
	public List getUserDeskMenuDesksInAllUnits(String[] _UnitsList, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.DESKS_INALLUNITS_NOTSEAT.GBLT_USER_DESKMENU_MST";

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
		String unitStr = "";
		int i;
		for (i = 0; i < _UnitsList.length - 1; i++)
			unitStr += _UnitsList[i].toString() + ",";
		unitStr += _UnitsList[i].toString();

		//String seatsStr = "";
		Sequence sq = new Sequence();
		try
		{
			/*for (i = 0; i < _SeatsList.length - 1; i++)
				seatsStr += _SeatsList[i].toString() + ","; */
		//	seatsStr += _SeatsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));
			//query = query.replace("%", seatsStr);
			//query = query.replace("*", String.valueOf(_SeatsList.length));
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
		List alRecord = null;
		try
		{
			alRecord = new ArrayList();
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getUserDeskMenuDesksInAllUnits" + e);
		}
		return alRecord;
	}

	
	// * Getting Desk Menu that are Template-Based by Desk Id
	public List getAllTemplateBasedDeskMenusByDeskId(String _deskId, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.TEMPLATEBASED_MENU_BYID.GBLT_DESK_DTL";

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
			populateMAP.put(sq.next(), _deskId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getAllTemplateBasedDeskMenusByDeskId" + e);
		}
		return alRecord;
	}

	// * Getting Templates By Unit,Seat,Desk Id
	public List getTemplatesByUnitSeatDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.TEMPLATES_BYUNITSEATDESK.GBLT_USER_DESKMENU_TEMPLATE";
		
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
			populateMAP.put(sq.next(), voUDMT.getDeptUnitCode());
			populateMAP.put(sq.next(), voUDMT.getUserSeatId());
			populateMAP.put(sq.next(), voUDMT.getDeskId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
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

	// * Getting Previous Visit Report Dates List  //added by manisha gangwar date: 24.8.2017
	public List getPrevVisitReportDates(String _patCrNo, String _episodeCode, String deskMenuId,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREV_VISIT_REPORT_DATES.HRGT_EPISODE_DTL";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), deskMenuId);
			
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
			throw new HisDataAccessException("HisDataAccessException  :getPrevVisitDates" + e);
		}
		return alRecord;
	}

	//Getting templates for unit-ward wise mode
	public List getTemplatesByUnitWard(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.TEMPLATES_BYUNITWARD.GBLT_USER_DESKMENU_TEMPLATE";
		
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
			populateMAP.put(sq.next(), voUDMT.getDeptUnitCode());
			populateMAP.put(sq.next(), voUDMT.getDeskId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), voUDMT.getWardCode());
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

	//Getting te,plates for unit-ward-seat wise mode
	public List getTemplatesByUnitWardSeat(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.TEMPLATES_BYUNITWARDSEAT.GBLT_USER_DESKMENU_TEMPLATE";
		
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
			populateMAP.put(sq.next(), voUDMT.getDeptUnitCode());
			populateMAP.put(sq.next(), voUDMT.getDeskId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), voUDMT.getWardCode());
			populateMAP.put(sq.next(), voUDMT.getUserSeatId());
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
	
	// * Getting Templates By Unit,Desk Id
	public List getTemplatesByUnitDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.TEMPLATES_BYUNITDESK.GBLT_USER_DESKMENU_TEMPLATE";
		
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
			populateMAP.put(sq.next(), voUDMT.getDeptUnitCode());
			populateMAP.put(sq.next(), voUDMT.getDeskId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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

	// * Getting Templates By Desk Id
	public List getTemplatesByDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO)
	{
		
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.TEMPLATES_BYDESK.GBLT_USER_DESKMENU_TEMPLATE";
		
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
			populateMAP.put(sq.next(), voUDMT.getDeskId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
	
	// * Getting Clinical Template Data
	public List getClinicalTemplateData(OpdClinicalDetailVO voClnData, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.TEMPLATE_CINICAL_DATA.GBLT_USER_DESKMENU_TEMPLATE";

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
			populateMAP.put(sq.next(), voClnData.getPatCrNo());
			populateMAP.put(sq.next(), voClnData.getEpisodeCode());
			populateMAP.put(sq.next(), voClnData.getEpisodeVisitNo());
			populateMAP.put(sq.next(), voClnData.getDeskMenuId());
			populateMAP.put(sq.next(), voClnData.getTemplateId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getClinicalTemplateData" + e);
		}
		return alRecord;
	}

	public String[] getAllergyDetails(String _allergyCode)
	{
		String query = "";
		Map populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		// String queryKey="SELECT._SOURCE_FLAG_TABLE_NAME_AND_COLUMNLIST.HGBT_ALLERGIES_TYPE_MST";
		String queryKey = "SELECT._SOURCE_FLAG_AND_TABLE_QUERY.HGBT_ALLERGY_TYPE_MST";

		String sourceFlag = "";
		// String tableName="";
		// String columnList="";
		String tableQuery = "";

		// String[] arrayRS=new String[3];
		String[] arrayRS = new String[2];

		Sequence sq = new Sequence();

		populateMap.put(sq.next(), _allergyCode);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		log.info(query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			rs.beforeFirst();

			if (rs.next())
			{
				sourceFlag = rs.getString(1);

				tableQuery = rs.getString(2);

				// tableName=rs.getString(2);

				// columnList=rs.getString(3);
			}

			arrayRS[0] = sourceFlag;
			// arrayRS[1]=tableName;
			// arrayRS[2]=columnList;
			arrayRS[1] = tableQuery;

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HisDataAccessException:: " + e);
		}
		return arrayRS;

	}

	public List getReasonListFromTableQuery(String query,UserVO userVO)
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		// String query="SELECT "+ _codeField + "," + _nameField + " FROM "+ _tableName +" WHERE GNUM_ISVALID=1" ;
		//populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			//rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No reason against allergy found  ");
			HisDAO daoObj = new HisDAO("OPD","OpdEssentialDAO");
			nProcedureIndex = daoObj.setProcedure("{call pkg_opd_view.proc_get_drug_list(?,?,?,?,?,?)}");
			// Setting and Registering In and Out Parameters 
				daoObj.setProcInValue(nProcedureIndex, "p_mode","2",1);
				daoObj.setProcInValue(nProcedureIndex, "is_valid",Config.IS_VALID_ACTIVE ,2);
				daoObj.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),3);
				daoObj.setProcInValue(nProcedureIndex, "seat_id",userVO.getSeatId() ,4);
				daoObj.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
				daoObj.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor

				// Executing Procedure 
				daoObj.executeProcedureByPosition(nProcedureIndex);
				// Getting out parameters 
				strDBErr = daoObj.getString(nProcedureIndex, "err");
				rs = daoObj.getWebRowSet(nProcedureIndex, "resultset");
				
				// If Database Error Occurs, No further processing is required. 
				if (strDBErr != null && !strDBErr.equals(""))
				{
					throw new Exception("Data Base Error:" + strDBErr);
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
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment" + e);
		}
		return alRecord;
	}

	// * Getting Previous Visit Dates List
	public List getPrevVisitDates(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREV_VISIT_DATES.HRGT_EPISODE_DTL";

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
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), _episodeCode);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getPrevVisitDates" + e);
		}
		return alRecord;
	}

	// * Getting All Parameters List of given Templates
	public List getOpdDeskTemplatesAllParas(String _tempIds, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_TEMP_PARAS.HGBT_TEMPLATE_PARA_MST";

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
			query = query.replace("#", _tempIds);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getOpdDeskTemplatesAllParas" + e);
		}
		return alRecord;
	}

	// * Getting Template Parameter Names
	public List getOpdTemplParaNames(String paraIds, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PARA_NAMES.HGBT_PARAMETER_MST";

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
			query = query.replace("#", paraIds);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getOpdTemplParaNames" + e);
		}
		return alRecord;
	}

	// * Getting Previous Visits In Between Given Dates
	public List getPrevVisitsInBetween(String _patCrNo, String _episodeCode, String _fromDate, String _toDate, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREV_VISIT_DATES_IN_BETWEEN.HRGT_EPISODE_DTL";

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
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), _episodeCode);
			populateMAP.put(sq.next(), _fromDate);
			populateMAP.put(sq.next(), _toDate);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getPrevVisitsInBetween" + e);
		}
		return alRecord;
	}

	// * Getting OPD Clinical Data By Selected Parameters
	public List getOpdClinDataBySelParas(OpdClinicalDetailVO voOpdCD, String qryVisits, String paraQuery, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.CLIN_DATA_OF_VISITS_BY_SEL_PARAS.HRGT_EPISODE_DTL";

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
			query = query.replace("#", qryVisits);
			query = query.replace("@", paraQuery);
			populateMAP.put(sq.next(), voOpdCD.getPatCrNo());
			populateMAP.put(sq.next(), voOpdCD.getEpisodeCode());
			populateMAP.put(sq.next(), voOpdCD.getDeskMenuId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getOpdClinDataBySelParas" + e);
		}
		return alRecord;
	}

	public List getBelongingList()
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.BELONGING.HOPT_PAT_BELONGING_MST";

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

		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("NO Belonging Details Found");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("opd Essential dao" + e);
		}
		return alRecord;

	}



	// ///////////Method for getting audio video files against a unit code////////////

	public AudioVideoMasterVO[] getAudioVideoEssentials(String _unitCode, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		ValueObject[] vo={};
		AudioVideoMasterVO[] audioVideoMasterVO=null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HOPT_UNIT_PLAYERFILE_MST";
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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("No audio Video File found For This Unit.");
			}
			else
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(AudioVideoMasterVO.class,rs);
				System.out.println("vo length-----------"+vo.length);
				audioVideoMasterVO=new AudioVideoMasterVO[vo.length];
				for(int i=0;i<vo.length;i++)
				{
					audioVideoMasterVO[i]=(AudioVideoMasterVO)vo[i];
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

		

		return audioVideoMasterVO;
	}

	public AudioVideoMasterVO[] getAudioVideoDefaultEssentials(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		ValueObject[] vo={};
		AudioVideoMasterVO[] audioVideoMasterVO=null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DEFAULT.HOPT_UNIT_PLAYERFILE_MST";
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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), OpdConfig.YES);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				throw new HisRecordNotFoundException("No Audio Video File Found");
			}
			else
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(AudioVideoMasterVO.class,rs);
				System.out.println("vo length-----------"+vo.length);
				audioVideoMasterVO=new AudioVideoMasterVO[vo.length];
				for(int i=0;i<vo.length;i++)
				{
					audioVideoMasterVO[i]=(AudioVideoMasterVO)vo[i];
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

		

		return audioVideoMasterVO;
	}

	public List getDeskMenuBasedOnDeskType(String _deskType, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.DESKMENU.BASEDON.DESKTYPE.GBLT_DESK_MENU_MST";

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

		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _deskType);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("NO Desk Menu Found");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("opd Essential dao" + e);
		}
		return alRecord;
	}

	public DeskMenuMacroMstVO[] getMacroHead(String _deskType, String _deskMenu, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map _populateMap = new HashMap();
		ValueObject[] vo =
		{};
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.HOPT_DESK_MENU_MACRO";

		DeskMenuMacroMstVO[] _deskMenuMacroMstVO;

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

		_populateMap.put(sq.next(), _UserVO.getHospitalCode());
		_populateMap.put(sq.next(), _UserVO.getHospitalCode());
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), _deskType);
		_populateMap.put(sq.next(), _deskMenu);
		_populateMap.put(sq.next(), _UserVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next()) throw new HisRecordNotFoundException(" No Macro Head Found ");
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("" + e);
		}

		try
		{
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(DeskMenuMacroMstVO.class, rs);
			_deskMenuMacroMstVO = new DeskMenuMacroMstVO[vo.length];

			for (int i = 0; i < vo.length; i++)
			{
				_deskMenuMacroMstVO[i] = (DeskMenuMacroMstVO) vo[i];
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO::getMacroHead" + e);
		}
		return _deskMenuMacroMstVO;
	}

	public List getAllUnit(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_UNIT.HGBT_UNIT_MST";

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

		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("NO Unit Found");
			}*/
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("opd Essential dao" + e);
		}
		return alRecord;
	}

	// * Getting Images List of Current Unit
	public List getOPDImagesListOfUnit(String _unitCode, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.IMAGES_OF_DEPTUNIT.HOPT_IMAGE_MST";

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
		try
		{
			populateMAP.put(sq.next(), _unitCode);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
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
			if (!rs.next()) throw new HisRecordNotFoundException("No Image Exists for this Unit ...");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List lst = new ArrayList();
		try
		{
			rs.beforeFirst();
			while (rs.next())
			{
				ImageMasterVO voImg = new ImageMasterVO();
				voImg.setImageCode(rs.getString(1));
				voImg.setImageName(rs.getString(2));
				voImg.setFileName(rs.getString(3));
				lst.add(voImg);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getting Image List" + e);
		}
		return lst;
	}

	// * Getting Editor Essentials e.g. Color-Description List
	// * Getting Description List of Colors in String and Format
	// * Color1:Desc1#Color2:Desc2.....
	public List getUnitsImageColorDesc(String _unitCode, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.COLOR_DESCS_OF_UNIT.HOPT_UNIT_IMAGEDESC_MST";

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

		try
		{
			populateMAP.put(sq.next(), _unitCode);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
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
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List lst = new ArrayList();
		try
		{
			if (rs.next()) lst = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getting Image List" + e);
		}
		return lst;
	}

	public List getAllImageDesc(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_IMAGE_DESC.HOPT_IMAGEDESC_MST";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("NO Image Description Found");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("opd Essential dao" + e);
		}

		return alRecord;
	}

	public List getUnitNotInTable(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.UNIT_NOT_IN.HOPT_UNIT_IMAGEDESC_MST";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("NO Image Description Found");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("opd Essential dao" + e);
		}

		return alRecord;
	}

	public void addImageDescription(String imageDesc, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ADD.HOPT_IMAGEDESC_MST";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), imageDesc);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMenuMacroMasterDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}


	// * Getting Patient Belongings List
	public List getBelongingList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.BELONGING.HOPT_PAT_BELONGING_MST";

		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("NO Belonging Details Found");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("opd Essential dao" + e);
		}
		return alRecord;
	}

	// * Get All Images List
	public List getAllImageList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_IMAGES.HOPT_IMAGE_MST";


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
			throw new HisDataAccessException("HisDataAccessException  :getAllImageList" + e);
		}
		return alRecord;
	}	
	 
	//* Getting Array of Patient Belonging Detail VOs
	public PatientBelongingVO[] getPatientBelongingDetails(String _patCrNo, UserVO _userVO)
	{
		PatientBelongingVO[] patBelongingVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PATIENT.BELONGING.DETAILS.HOPT_PAT_BELONGING_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _patCrNo);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No patient belonging found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatientBelongingVO.class, rs);
			patBelongingVO = new PatientBelongingVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				patBelongingVO[i] = (PatientBelongingVO) vo[i];
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

		return patBelongingVO;
	}
	
	// * Getting Selected Images By Department Unit
	public List getSelectedImages(String _deptUnitCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.SELECTED_IMAGES.HOPSTR_IMAGE_NAME";

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
			populateMap.put(sq.next(), _deptUnitCode);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List selectedRecord = new ArrayList();
		try
		{
			if (rs.next()) selectedRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getSelectedImages" + e);
		}
		return selectedRecord;
	}

	// * Unit List to which any Image is not added yet
	public List getNotAddedUnitList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.NOT_ADDED_IMAGES.HGNUM_DEPT_CODE";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
	}
	
	public List getAllAudioVideoFile(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.HOPT_PLAYERFILE_MST";
		
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
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	public List getAllAudioVideoFileHeader(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALLAUDIOVIDEOHEADER.HOPT_PLAYERFILE_MST";
		
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
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	public List getAllUnitNotInTable(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.UNIT.NOT.IN.HOPT_PLAYERFILE_MST";
		
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
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}

	// Getting Casulatity Units By Seat Id
	public List getCsultyUnitsBySeatId(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HOPT_DEPT_UNIT_ROSTER_DTL.RETRIEVE_EMG_UNITS_BY_SEATID";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_CASUALITY);
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
				throw new HisRecordNotFoundException("No Unit Allotted For This Seat ID");
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
		/*
		 * finally{ try{if(true) throw new HisRecordNotFoundException("No Department with any Units functioning"); }
		 * catch(Exception e){ throw new HisRecordNotFoundException("No Department with any Units functioning"); }}
		 */
		return alRecord;
	}

	// Getting Roles List
	public List getRoleList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_ROLES_LIST.GBLT_ROLE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
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
				throw new HisRecordNotFoundException("No Unit Allotted For This Seat ID");
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

	
	// Getting ICD Hospital Disease Essentials
	public  List getIcdHospitalMasterEssentails(UserVO _userVO)
	{
		
		
		
		ResultSet rs=null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
        String queryKey="SELECT.ALL_DISEASE_LIST.HGBT_HOSPITAL_DISEASE_MST";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
         populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
         populateMAP.put(sq.next(),_userVO.getHospitalCode());
        
        try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Allotted For This Seat ID");
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
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	
	// Getting ICD Disease List By Hospital Disease Code
	public  List fetchDiseaseList(IcdHospitalMasterVO _diseaseMasterVO,UserVO _userVO)
	{
		
		
		
		ResultSet rs=null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
        String queryKey="SELECT.ALL_ICD_BY_HOS.HGBT_ICD_HOSPITAL_MST";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
         populateMAP.put(sq.next(),_diseaseMasterVO.getHospitalDiseaseCode());
         populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
         populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
         populateMAP.put(sq.next(),_userVO.getHospitalCode());
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
			if(rs.next())
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	
	// * Getting Clinical Department List
	public List getAllClinicalDepartmentList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_CLINICAL_DEPT.GBLT_DEPARTMENT_MST";

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
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
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
	
	// * Getting All Clinical Units List in Dept (Unit) form   returning   (UnitCode@IsGeneralCode , Dept(Unit))
	public List getAllClinicalUnitList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_CLINICAL_UNIT.HGBT_UNIT_MST";

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
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	public List gettingAllClinicalUnitList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.GET_ALL_CLINICAL_UNIT.HGBT_UNIT_MST";

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
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	public List getUnitExceptAssignedByDeskType(UserDeskMenuMasterVO _voDeskMapping, UserVO _voUser)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNIT_EXCEPT_ASSIGN.HGBT_UNIT_MST";

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
			
			populateMAP.put(sq.next(), _voUser.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
			populateMAP.put(sq.next(), _voDeskMapping.getDeskType());
			populateMAP.put(sq.next(), _voDeskMapping.getMappingType());
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	
	public List getUnitByDeskType(UserDeskMenuMasterVO _voDeskMapping, UserVO _voUser)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNIT_ASSIGN.HGBT_UNIT_MST";

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
			
			populateMAP.put(sq.next(), _voUser.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
			//populateMAP.put(sq.next(), _voDeskMapping.getDeskType());
			//populateMAP.put(sq.next(), _voDeskMapping.getMappingType());
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	
	
	//Function to check whether the default desk exists or not.If already exists then disable check box, else enable it
	public boolean getisDefault(DeskMasterVO _deskMstVO,UserVO _userVO)
	{
		ResultSet rs = null;
		boolean defaultValue=false;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "COUNTDEFAULT.GBLT_DESK_MST";
		Connection conn=super.getTransactionContext().getConnection();
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
			populateMAP.put(sq.next(), OpdConfig.IS_DEFAULT);
			populateMAP.put(sq.next(),_deskMstVO.getDeskType());
			populateMAP.put(sq.next(),_deskMstVO.getDeskId());			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			 rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			
			if (!rs.next())
			{
				defaultValue=true;
			}
			else
			{
				defaultValue=false;
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return defaultValue;
	}
	
	
	
	
	
	//Function to check whether the default desk exists or not.If already exists then disable check box, else enable it
		public boolean getisDefaultGlobal(DeskMasterVO _deskMstVO,UserVO _userVO)
		{
			ResultSet rs = null;
			boolean defaultValue=false;
			String query = "";
			Map populateMAP = new HashMap();

			String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
			String queryKey = "COUNTDEFAULT.GBLT_DESK_MST";
			Connection conn=super.getTransactionContext().getConnection();
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
				populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);
				//populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), OpdConfig.IS_DEFAULT);
				populateMAP.put(sq.next(),_deskMstVO.getDeskType());
				populateMAP.put(sq.next(),_deskMstVO.getDeskId());			
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
			}

			try
			{
				 rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
				
				if (!rs.next())
				{
					defaultValue=true;
				}
				else
				{
					defaultValue=false;
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }	
			return defaultValue;
		}
	
	
	
	public List getAllGroupList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_GROUP.GBLT_GROUP_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	// List of Units from User Desk Menu Master minus Those Units to which a template is 
	// already assigned for user seat id is null 
	public List getUserDeskUnitsExceptUnitTemplates(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNITS_NOT_UNITTEMPLATE.GBLT_USER_DESKMENU_MST";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	//Getting units for unit-ward wise mode
	public List getUnitsForUnitWardWise(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNITS_UNITWARDWISE.GBLT_USER_DESKMENU_MST";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	//Getting units for unit-ward-seat wise mode
	public List getUnitsForUnitWardSeatWise(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNITS_UNITWARDSEATWISE.GBLT_USER_DESKMENU_MST";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	
	// List of Units from User Desk Menu Master minus Those Units to which a template is 
	// already assigned for user seat id is not null 
	public List getUserDeskUnitsWithSeat(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNITS_WITHSEAT.GBLT_USER_DESKMENU_MST";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	// List of all desks during desk wise mode 
	public List getAllDesk(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		System.out.println("inDAO");
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_DESK.GBLT_DESK_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	
	// List of all desks during desk wise mode 
	public List getAllDeskType(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_DESK_TYPE.GBLT_DESK_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	
	// List of Units from User Desk Menu Master minus Those Units to which a template is 
	// already assigned  
	public List getUnitExceptTemplate(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNIT_EXCEPT_TEMPLATE.HGBT_UNIT_MST";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		//	populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
		//	populateMAP.put(sq.next(), _deskType);
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	
	
	//*User Desk Unit Ward Mapping Master
	
	public List getUnitExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.UNIT_EXCEPT_ASSIGN_FOR_UNIT_WARD_MAPPING_MASTER.HGBT_UNIT_MST";

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
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
			populateMAP.put(sq.next(), _deskType);
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	
	
	  /*User Desk Unit Ward Mapping Master 
	   *Getting All Unit List
	   */
	public List getAllUnitsList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_UNIT_MST";

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
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Unit record Exists in database  ");
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnits" + e);
		}
		return alRecord;
	}
	
	
	//User Desk Unit Ward Mapping Master
	//Getting the wards list where seat is null and desk type exist
	public List getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO _userVO,String deptCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.WARD_EXCEPT_ASSIGN_FOR_UNIT_WARD_MAPPING_MASTER.HIPT_WARD_MST";

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

		System.out.println("   -------> wardquery :: " + query);
		Sequence sq = new Sequence();

		try
		{
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), deptCode);
		//	populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//	populateMAP.put(sq.next(), _deskType);
		//	populateMAP.put(sq.next(), UnitId);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			System.out.println("query-->"+query);
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	
	// Ward Not Assigned in Given Desk & Unit
	public List getWardExceptAssignedByDeskType(String _deskId,UserVO _userVO,String UnitId)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.WARD_FOR_UNITWARD.GBLT_USER_DESKMENU_TEMPLATE";

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
			populateMAP.put(sq.next(), UnitId);
			populateMAP.put(sq.next(), _deskId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), UnitId);
			
			
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	public List getWardExceptAssignedByDeskTypeForUnitWardSeat(String _deskId,UserVO _userVO,String UnitId)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.WARD_FOR_UNITWARDSEAT.GBLT_USER_DESKMENU_TEMPLATE";

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
			populateMAP.put(sq.next(), UnitId);
			populateMAP.put(sq.next(), _deskId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), UnitId);
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	
	//User Desk Unit Ward Mapping Master
	//Getting the wards list where seat is not null and desk type exist
	public List getAllWardInUnitWardSeatMode(String _deskType,UserVO _userVO,String UnitId)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_WARD.HGBT_UNIT_MST";

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
			populateMAP.put(sq.next(), UnitId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _deskType);
			populateMAP.put(sq.next(), UnitId);
			
			
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	
	
	// * Get All Seats that are not assigned a desk for given Units //******Now Users
	public List getSeatsByNotWards(String[] _WardsList, String deskType, UserVO _userVO, String groupCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		//String queryKey = "SELECT.WARDS_NOT_ASSIGNED_DESK.GBLT_SEAT_MST";
		String queryKey = "SELECT.USERS_WARDS_NOT_ASSIGNED_DESK.GBLT_USER_MST";

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

		String wardStr = "";
		int i = 0;
		for (i = 0; i < _WardsList.length - 1; i++)
			wardStr += _WardsList[i].toString() + ",";
		wardStr += _WardsList[i].toString();
		query = query.replace("@", wardStr);
		System.out.println("   -------> query :: " + query);

		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), groupCode);
			populateMAP.put(sq.next(), deskType);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getSeatsNotforUnits" + e);
		}
		return alRecord;
	}
	
	public List getSeatsByNotWards(String[] _WardsList, UserVO _userVO, String groupCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.WARDS_NOT_ASSIGNED_DESK.GBLT_SEAT_MST.WITHOUTDESKTYPE";

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

		String wardStr = "";
		int i = 0;
		for (i = 0; i < _WardsList.length - 1; i++)
			wardStr += _WardsList[i].toString() + ",";
		wardStr += _WardsList[i].toString();
		query = query.replace("@", wardStr);
		System.out.println("   -------> query :: " + query);

		Sequence sq = new Sequence();

		try
		{
		//	populateMAP.put(sq.next(), deskType);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), groupCode);
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
			throw new HisDataAccessException("HisDataAccessException  :getSeatsNotforUnits" + e);
		}
		return alRecord;
	}
	
	public List getAllAllergySite(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SLECT_ALL_SITE.HGBT_ALLERGY_SITE_MST";

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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	public List getACommonSymptomList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SLECT_COMMON_SYMPTOM.HGBT_ALLERGY_SYMPTOM_MST";

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
			populateMAP.put(sq.next(), OpdConfig.ALLERGY_SYMPTOM_COMMON);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	public List getAllergyTypeSymptomList(String allergyTypeCode, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SLECT_ALLERGY_TYPE_SYMPTOM.HGBT_ALLERGY_SYMPTOM_MST";

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
			populateMAP.put(sq.next(), allergyTypeCode);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), OpdConfig.ALLERGY_SYMPTOM_ALLERGY_TYPE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	public List getListAllergyAdvice(UserVO _userVO)
	{
		List alRecord = null;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALLERGY_ADVICE.HGBT_ALLERGY_ADVICE_MST";

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

		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())  
			{	
				alRecord = new ArrayList();
			//	throw new HisRecordNotFoundException("No record for Advice Exists ");
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
		//List alRecord = new ArrayList();
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
	

	
	//Getting Service Type List
	public List getServiceTypeList(UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.SERVICETYPE.HGBT_CONSENT_SERVICE_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
	
		
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
	
	
	
	public ConsentRequestVO[] getConsentRequestVOList(ConsentRequestVO consentRequestVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		ValueObject[] vo={};
		ConsentRequestVO[] _consentRequestVO;
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.CONSENT_REQUEST_VO_LIST.HGBT_CONSENT_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), consentRequestVO.getPatCrNo());
		populateMAP.put(sq.next(), consentRequestVO.getEpisodeCode());
		populateMAP.put(sq.next(), consentRequestVO.getEpisodeVisitNo());
		//populateMAP.put(sq.next(), OpdConfig.CONSENT_REQUEST);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), consentRequestVO.getIsRevoke());
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		//List alRecord = new ArrayList();
		try
		{
			rs.beforeFirst();
			
			vo=HelperMethods.populateVOfrmRS(ConsentRequestVO.class,rs);
			System.out.println("vo length-----------"+vo.length);
			_consentRequestVO=new ConsentRequestVO[vo.length];
			for(int i=0;i<vo.length;i++)
			{
				_consentRequestVO[i]=(ConsentRequestVO)vo[i];
			}
			
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("RegistrationTimingMasterDAO::getEssentialForRegTiming"+e);
		}
		return _consentRequestVO;
	}
	
	public ConsentRequestVO[] getConsentRequestVOListByCrNo(ConsentRequestVO consentRequestVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		ValueObject[] vo={};
		ConsentRequestVO[] _consentRequestVO;
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.CONSENT_REQUEST_VO_BY_CRNO_LIST.HGBT_CONSENT_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
	
		populateMAP.put(sq.next(), consentRequestVO.getPatCrNo());
		//populateMAP.put(sq.next(), consentRequestVO.getEpisodeCode());
		//populateMAP.put(sq.next(), consentRequestVO.getEpisodeVisitNo());
		//populateMAP.put(sq.next(), OpdConfig.CONSENT_REQUEST);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), consentRequestVO.getIsRevoke());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		//List alRecord = new ArrayList();
		try
		{
			rs.beforeFirst();
			
			vo=HelperMethods.populateVOfrmRS(ConsentRequestVO.class,rs);
			System.out.println("vo length-----------"+vo.length);
			_consentRequestVO=new ConsentRequestVO[vo.length];
			for(int i=0;i<vo.length;i++)
			{
				_consentRequestVO[i]=(ConsentRequestVO)vo[i];
			}
			
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("RegistrationTimingMasterDAO::getEssentialForRegTiming"+e);
		}
		return _consentRequestVO;
	}

	//****** Noe Users
	public UserDeskMenuMasterVO getSeats(UserDeskMenuMasterVO _voUDM, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		//UserDeskMenuMasterVO voUDMT = new UserDeskMenuMasterVO();
		UserDeskMenuMasterVO seatDetails = new UserDeskMenuMasterVO();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		//String queryKey = "SELECT.SEATS.GBLT_USER_DESKMENU_MST";
		String queryKey = "SELECT.USERS.GBLT_USER_DESKMENU_MST";

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
		//String unitStr = "";
		//int i = 0;
		Sequence sq = new Sequence();
		try
		{
		/*	for (i = 0; i < _UnitsList.length - 1; i++)
				unitStr += _UnitsList[i].toString() + ",";
			unitStr += _UnitsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));*/

			populateMAP.put(sq.next(),_voUDM.getMappingSeqNo());
			populateMAP.put(sq.next(),_voUDM.getDeskType());
			//populateMAP.put(sq.next(), _voUDM.getUserDeskMenuId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _voUDM.getIsValid());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
			
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
			while(rs.next())
			{
				seatDetails.setUserSeatId(rs.getString(1));
				seatDetails.setDeptUnitCode(rs.getString(2));
				seatDetails.setSeatName(rs.getString(3));
			}
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :getUserDeskMenuSeatsInAllUnits" + e);
		}
		return seatDetails;
	}
	
	//Getting ward list for unit-ward wise mode
	public UserDeskUnitWardMappingMasterVO gettingWards(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		//UserDeskUnitWardMappingMasterVO voUDMT = new UserDeskUnitWardMappingMasterVO();
		UserDeskUnitWardMappingMasterVO seatDetails = new UserDeskUnitWardMappingMasterVO();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.WARDS.GBLT_USER_DESKMENU_MST";

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
		//String unitStr = "";
		//int i = 0;
		Sequence sq = new Sequence();
		try
		{
		/*	for (i = 0; i < _UnitsList.length - 1; i++)
				unitStr += _UnitsList[i].toString() + ",";
			unitStr += _UnitsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));*/

		//	populateMAP.put(sq.next(), _voUDMT.getUserDeskMenuId());
			populateMAP.put(sq.next(),_voUDMT.getMappingSeqNo());
			populateMAP.put(sq.next(),_voUDMT.getDeskType());
		
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _voUDMT.getIsValid());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
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
			while(rs.next())
			{
				seatDetails.setWardCode(rs.getString(1));
				seatDetails.setDeptUnitCode(rs.getString(2));
				seatDetails.setWardName(rs.getString(3));
			}
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :getUserDeskMenuSeatsInAllUnits" + e);
		}
		return seatDetails;
	}
	
	public UserDeskUnitWardMappingMasterVO gettingSeats(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		//UserDeskUnitWardMappingMasterVO voUDMT = new UserDeskUnitWardMappingMasterVO();
		UserDeskUnitWardMappingMasterVO seatDetails = new UserDeskUnitWardMappingMasterVO();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		//String queryKey = "SELECT.SEATS.GBLT_USER_DESKMENU_MST";
		String queryKey = "SELECT.USERS.GBLT_USER_DESKMENU_MST";

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
		//String unitStr = "";
		//int i = 0;
		Sequence sq = new Sequence();
		try
		{
		/*	for (i = 0; i < _UnitsList.length - 1; i++)
				unitStr += _UnitsList[i].toString() + ",";
			unitStr += _UnitsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));*/
			populateMAP.put(sq.next(),_voUDMT.getMappingSeqNo());
			populateMAP.put(sq.next(),_voUDMT.getDeskType());
			//populateMAP.put(sq.next(), _voUDMT.getUserDeskMenuId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _voUDMT.getIsValid());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
			
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
			while(rs.next())
			{
				seatDetails.setUserSeatId(rs.getString(1));
				seatDetails.setDeptUnitCode(rs.getString(2));
				seatDetails.setSeatName(rs.getString(3));
			}
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :getUserDeskMenuSeatsInAllUnits" + e);
		}
		return seatDetails;
	}
	
	public void updateTable(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO)
	{
		//ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "UPDATETABLE.GBLT_USER_DESKMENU_MST";

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
		//String unitStr = "";
		//int i = 0;
		Sequence sq = new Sequence();
		try
		{
		/*	for (i = 0; i < _UnitsList.length - 1; i++)
				unitStr += _UnitsList[i].toString() + ",";
			unitStr += _UnitsList[i].toString();
			query = query.replace("@", unitStr);
			query = query.replace("#", String.valueOf(_UnitsList.length));*/

			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _voUDMT.getUserDeskMenuId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :getUserDeskMenuSeatsInAllUnits" + e);
		}
		
	}
	
	public List getUnitRoomList(UserVO _userVO,String _unitCode,String _roomCode,PatientDetailVO patDtlVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.CHANGE_ROOM.HRGT_DEPT_UNIT_ROOM_MST";

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
       
		//Modified by Vasu on 21.Feb.2019 to get roster based room
		populateMAP.put(sq.next(), patDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), patDtlVO.getEpisodeCode());
		populateMAP.put(sq.next(), patDtlVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _unitCode);
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _roomCode);		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Record for Other Room Exists");
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
	
	public String  getOldRoomName(UserVO _userVO,String _unitCode,String _roomCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.OLD_ROOM.HGBT_ROOM_MST";

		String oldRoomName="";
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

		populateMAP.put(sq.next(), _roomCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No record for Room Exists  ");
			else
			{
				rs.beforeFirst();
				rs.next();
				oldRoomName=rs.getString(1);
				
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
		
		return oldRoomName;
	}
	

	public List<TemplateMasterVO> getAllTemplatesNotAdded(String deskId,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		ValueObject[] vo={};
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_TEMPLATE_NOTADDED.HGBT_TEMPLATE_MST";

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
		populateMAP.put(sq.next(), deskId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

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
		List<TemplateMasterVO> templateMstVO = new ArrayList<TemplateMasterVO>();
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(TemplateMasterVO.class,rs);
				for(ValueObject v :vo)
					templateMstVO.add((TemplateMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment" + e);
		}
		return templateMstVO;
	}
	
	public List getAllUnitForMapping(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_UNIT_FOR_MAPPING.HGBT_UNIT_MST";

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
			if (!rs.next()) throw new HisRecordNotFoundException("No Unit Found");
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
	
	public List getDeskListByUnit(String unitCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_DESK_BY_UNIT.HGBT_UNIT_MST";

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
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Desk Found");
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
	
	public List getUnitForSeatWise(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_UNIT_FOR_SEATWISE.HGBT_UNIT_MST";

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
			if (!rs.next()) throw new HisRecordNotFoundException("No Unit Found");
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
			throw new HisDataAccessException("HisDataAccessException  " + e);
		}
		return alRecord;
	}
	
	public List getSeatListByUnit(String unitCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_SEAT_BASED_ON_UNIT.GBLT_USER_DESKMENU_MST";

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
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Seat Found");
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
			throw new HisDataAccessException("HisDataAccessException " + e);
		}
		return alRecord;
	}
	
	public List getSeatListByUnitForUnitSeat(String _deskId, String unitCode, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		//String queryKey = "SELECT_SEAT_BASED_ON_UNIT.GBLT_USER_DESKMENU_TEMPLATE";
		String queryKey = "SELECT_USER_BASED_ON_UNIT.GBLT_USER_DESKMENU_TEMPLATE";
		
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
		populateMAP.put(sq.next(), _deskId);
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Seat Found");
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
			throw new HisDataAccessException("HisDataAccessException " + e);
		}
		return alRecord;
	}
	
	public List getDeskListByUnitNSeat(String seatId,String unitCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_DESK_BASED_ON_UNIT_N_SEAT";

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
		populateMAP.put(sq.next(), seatId);
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Seat Found");
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
			throw new HisDataAccessException("HisDataAccessException " + e);
		}
		return alRecord;
	}
	
	public List getUnitForWardWise(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_UNIT_FOR_WARDWISE.HGBT_UNIT_MST";

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
			if (!rs.next()) throw new HisRecordNotFoundException("No Unit Found");
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
			throw new HisDataAccessException("HisDataAccessException  " + e);
		}
		return alRecord;
	}
	
	public List getUnitForWardSeatWise(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_UNIT_FOR_WARD_SEAT_WISE.HGBT_UNIT_MST";

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
			if (!rs.next()) throw new HisRecordNotFoundException("No Unit Found");
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
			throw new HisDataAccessException("HisDataAccessException  " + e);
		}
		return alRecord;
	}
	
	public List getWardListByUnit(String _deskId, String unitCode, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_WARD_BASED_ON_UNIT.GBLT_USER_DESKMENU_TEMPLATE";

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
		populateMAP.put(sq.next(), _deskId);
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Unit Found");
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
			throw new HisDataAccessException("HisDataAccessException  " + e);
		}
		return alRecord;
	}
	
	public List getDeskListByUnitNWard(String wardCode,String unitCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_DESK_BASED_ON_WARD_GBLT_USER_DESKMENU_MST";

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
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), wardCode);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Unit Found");
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
			throw new HisDataAccessException("HisDataAccessException  " + e);
		}
		return alRecord;
	}
	/////////////////////////////
	public List getWardListByUnitForUWS(String _deskId, String unitCode, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_WARD_FOR_UNIT_WARD_SEAT_WISE.HGBT_UNIT_MST";

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
		populateMAP.put(sq.next(), _deskId);
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Unit Found");
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
			throw new HisDataAccessException("HisDataAccessException  " + e);
		}
		return alRecord;
	}
	
	public List getSeatListByUnitNWard(String _deskId, String wardCode, String unitCode, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		//String queryKey = "SELECT_SEAT_FOR_UNIT_WARD_N_SEAT_WISE.HGBT_UNIT_MST";		
		String queryKey = "SELECT_USER_FOR_UNIT_WARD_N_USER_WISE.HGBT_UNIT_MST";

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
		populateMAP.put(sq.next(), _deskId);
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), wardCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Unit Found");
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
			throw new HisDataAccessException("HisDataAccessException  " + e);
		}
		return alRecord;
	}
	
	public List getDeskListByUnitNWardNSeat(String seatId,String wardCode,String unitCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_DESK_BASED_ON_UNIT_N_WARD_N_SEAT_GBLT_USER_DESKMENU_MST";

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
		populateMAP.put(sq.next(), seatId);
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), wardCode);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Unit Found");
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
			throw new HisDataAccessException("HisDataAccessException  " + e);
		}
		return alRecord;
	}

	// getting template category master
	public List getTemplateCategory(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_TEMPLATE_CATEGORY_MST";
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
		log.error(query + "\n");

		
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No Template Catagory exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}	
	

	
	//getting item type List
	
	public List getItemTypeList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HSTT_ITEMTYPE_MST";
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
		log.error(query + "\n");

		
		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), OpdConfig.DRUG_CATEGORY);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No Item Type exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getItemTypeList" + e);
		}

		return alRecord;
	}	

	/**
	 * Getting Dosage Frequecy List
	 * @param _userVO User VO
	 * @return List of Dosage Frequecy 
	 */
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

	/**
	 * Getting Drug List
	 * @param _userVO User VO
	 * @return List of Drugs 
	 */
/*	public List<Entry> getDrugList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
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
	
/*	public List<Entry> getDrugListForSearch(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		//String queryKey = "ESSENTIAL.LIST.HSTT_DRUG_MST";
		String queryKey = "ESSENTIAL.DRUG_SEARCH_LIST.HSTT_DRUGBRAND_MST";
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
/* public List<Entry> getDrugListDetail(UserVO _userVO, String depUnitCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		//String queryKey = "ESSENTIAL.LIST.HSTT_DRUG_MST";
		String queryKey = "ESSENTIAL.LIST.DETAIL.HSTT_DRUGBRAND_MST";
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
			populateMAP.put(sq.next(), depUnitCode );
			populateMAP.put(sq.next(), _userVO.getHospitalCode() );
			
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
				return null;
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)			{
				
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
	
	
	/*public List<Entry> getDrugListDetailForSearch(UserVO _userVO, String depUnitCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		//String queryKey = "ESSENTIAL.LIST.HSTT_DRUG_MST";
		String queryKey = "ESSENTIAL.DRUG_SEARCH_LIST_DETAIL.HSTT_DRUGBRAND_MST";
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
			populateMAP.put(sq.next(), depUnitCode);
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
				return null;
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
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return alRecord;
	}
*/
	/**
	 * Getting Drug Doses List
	 * @param _userVO User VO
	 * @return List of Drugs Doses 
	 */
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

	
	/**
	 * Getting Item Type List
	 * @param _userVO User VO
	 * @return List of Item Types 
	 */
	/*
	public List<Entry> getDrugItemTypeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = null;

		try
		{
			Procedure strProc = new Procedure(OpdDaoConfig.PROCEDURE_GET_ITEM_TYPE_MST);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addOutParameter(2, Types.VARCHAR);
			strProc.addOutParameter(3, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(2);
			rs = (ResultSet) strProc.getParameterAt(3);
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

		List<Entry> lstDrugItemType = new ArrayList<Entry>();
		try
		{
			while(rs.next())
			{
				Entry e = new Entry();
				e.setValue(rs.getString(1));
				e.setLabel(rs.getString(2));
				lstDrugItemType.add(e);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return lstDrugItemType;
	}
	*/
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
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
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
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		//populateMAP.put(sq.next(), OpdConfig.ENTRY_MODE);
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

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
	
	public List<PatDrugTreatmentDetailVO> getPrevPatDrugDetailForLog(String _patCrNo, String _episodeCode, UserVO _userVO)
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
	
	
	
	public  List<viewInvestigationVO>  getPrvTestDtlUsingAJAX(String CrNo,UserVO _UserVO)
	{
		String query = "";		
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		List<viewInvestigationVO> lstViewVO=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.PRV_TEST_DETAIL";
		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(),CrNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(),CrNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
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
			
			 
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
					populateMAP);
	           
	            if (!rs.next())
	            {
	                throw new HisRecordNotFoundException("No Test / Lab  Found");
	            }
	            else
	            {
	                rs.beforeFirst();
	                lstViewVO=new ArrayList<viewInvestigationVO>();
	                viewInvestigationVO voViewInv=null;
	                while (rs.next()) {
	                	voViewInv=new viewInvestigationVO();
	                    HelperMethods.populateVOfrmRS(voViewInv, rs);
	                    lstViewVO.add(voViewInv);
	                }
	               
	            }
			
		}
		catch (HisRecordNotFoundException e)
		{

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("" + e);
		}
		return lstViewVO;

	}
	

	
	
	public List<TemplateMasterVO> getAllTemplatesVO(UserVO userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		ValueObject[] vo={};
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_TEMPLATE_N_CATEGORY.HGBT_TEMPLATE_MST";
		
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
		
		List<TemplateMasterVO> templateMstVO = new ArrayList<TemplateMasterVO>();
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(TemplateMasterVO.class,rs);
				for(ValueObject v :vo)
					templateMstVO.add((TemplateMasterVO)v);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("RegistrationTimingMasterDAO::getEssentialForRegTiming"+e);
		}
		return templateMstVO;
	}
	
	public DeskMenuMasterVO[] getMenuNameBasedOnDeskId(String deskId,UserVO userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		ValueObject[] vo={};
		DeskMenuMasterVO[] deskMenuMstVO;
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_DESK_MENU_BASED_ON_DESKID.GBLT_DESK_MENU_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), OpdConfig.IS_TEMPLATE_YES);
		populateMAP.put(sq.next(), deskId);
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
		try
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(DeskMenuMasterVO.class,rs);
			deskMenuMstVO=new DeskMenuMasterVO[vo.length];
			for(int i=0;i<vo.length;i++)
			{
				deskMenuMstVO[i]=(DeskMenuMasterVO)vo[i];
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("RegistrationTimingMasterDAO::getEssentialForRegTiming"+e);
		}
		return deskMenuMstVO;
	}
	
	// List of Alert Names that are not assigned to the Patient
	public List getAllPatAlerts(String crNo,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_ALERT.HGBT_PAT_ALERT_MST";

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
			populateMAP.put(sq.next(), crNo);
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
				//List alRecord = new ArrayList();//throw new HisRecordNotFoundException("No Alert Found");
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
			throw new HisDataAccessException("OpdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}

	public List getPrevPatRestDetail(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS_REST_DETAIL.HRGT_EPISODE_RESTADVICE_DTL";

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

		List<RestAdviceDtlVO> restAdviceDtlVOLst = new ArrayList<RestAdviceDtlVO>();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(RestAdviceDtlVO.class, rs);			
				for (ValueObject v : vo)
					restAdviceDtlVOLst.add((RestAdviceDtlVO)v);
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
		return restAdviceDtlVOLst;
	}
	
	public List<Entry> getEXTTreatmentList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HGBT_EXT_TREATMENT_MST";
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
			populateMAP.put(sq.next(), OpdConfig.EXTERNAL_TREATMENT);
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
	
	
	public List<Entry> getOtherInstructionList(String genderFlag,String deptUnitCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.OTHER_INSTRUCTION_LIST.HGBT_EXT_TREATMENT_MST";
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
			populateMAP.put(sq.next(),deptUnitCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.OTHER_INSTRUCTION);
			populateMAP.put(sq.next(),genderFlag);
			populateMAP.put(sq.next(),OpdConfig.GENDER_FLAG_FOR_BOTH);
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
	
	public List<Entry> getOneTimeActivityList(String genderFlag,String deptUnitCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ONE_TIME_ACTIVITY_LIST.HGBT_EXT_TREATMENT_MST";
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
			populateMAP.put(sq.next(),deptUnitCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ONE_TIME_ACTIVITY);
			populateMAP.put(sq.next(),genderFlag);
			populateMAP.put(sq.next(),OpdConfig.GENDER_FLAG_FOR_BOTH);
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
	
	public List<Entry> getAllOneTimeActivityList(String genderFlag,String deptUnitCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_ONE_TIME_ACTIVITY_LIST.HGBT_EXT_TREATMENT_MST";
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
			populateMAP.put(sq.next(), OpdConfig.ONE_TIME_ACTIVITY);
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
	
	public List<PatExtTreatmentDetailVO> getPrevPatExtTreatmentDetail(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS_EXTTREATMENT_DETAIL.HRGT_EPISODE_EXTTREATMENT_DTL";

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
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), OpdConfig.EXTERNAL_TREATMENT);

		List<PatExtTreatmentDetailVO> _previousEpisodeDiagVO = new ArrayList<PatExtTreatmentDetailVO>();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PatExtTreatmentDetailVO.class, rs);			
				for (ValueObject v : vo)
					_previousEpisodeDiagVO.add((PatExtTreatmentDetailVO)v);
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
	
	public List<PatExtTreatmentDetailVO> getPrevOtherInstructionDetail(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS_OTHER_INSTRUCTION_DETAIL.HRGT_EPISODE_EXTTREATMENT_DTL";

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
		populateMAP.put(sq.next(), _patCrNo);
		populateMAP.put(sq.next(), _episodeCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), OpdConfig.ONE_TIME_ACTIVITY);
		populateMAP.put(sq.next(), OpdConfig.OTHER_INSTRUCTION);

		List<PatExtTreatmentDetailVO> _previousEpisodeDiagVO = new ArrayList<PatExtTreatmentDetailVO>();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PatExtTreatmentDetailVO.class, rs);			
				for (ValueObject v : vo)
					_previousEpisodeDiagVO.add((PatExtTreatmentDetailVO)v);
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
	
	public List<Entry> getAllDietTypeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HDKT_MEALTYPE_MST";
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
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO.getDosageFrequecy::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException( "No Diet Advice Exists  ");
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
	
	
	public List<PatDietAdviceDetailVO> getPrevPatDietAdviceDetail(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS_DIET_ADVICE_DETAIL.HRGT_EPISODE_DIETADVICE_DTL";

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

		List<PatDietAdviceDetailVO> _previousDietDtlVO = new ArrayList<PatDietAdviceDetailVO>();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PatDietAdviceDetailVO.class, rs);			
				for (ValueObject v : vo)
					_previousDietDtlVO.add((PatDietAdviceDetailVO)v);
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
		return _previousDietDtlVO;
	}
	
	public List getAllRelationList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.GBLT_RELATION_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), OpdConfig.RELATIONSHIP_CODE_FOR_SELF);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No Relation Exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
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

	public List<UserVO> getSearchUsersByEmpName(String _str, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.USERS_BY_EMPNAME.GBLT_USER_MST";
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
			query = query.replace("#", _str+"%");
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		List<UserVO> lstUsers = new ArrayList<UserVO>();
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(UserVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstUsers.add((UserVO) valueObjects[i]);
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
		return lstUsers;
	}
	
	public List getParameterForExtInv(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "GET_PARA_LIST_TEMP_CAT_WISE.HGBT_PARAMETER_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), GenericTemplateConfig.TEMPLATE_GROUP_CLINICAL);
		//populateMAP.put(sq.next(), GenericTemplateConfig.PARAMETER_TYPE_VITAL_MONITORING);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Parameter Found  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	public List getUserBasedOnGroup(String groupCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_USER_BASED_ON_GROUP.GBLT_SEAT_MST";
		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), groupCode);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No User Found  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	/**
	 * Getting Serach Users By Emp ID
	 * @param _str Search String 
	 * @param _userVO User Detail
	 * @return
	 */
	public List<UserVO> getSearchUsersByEmpId(String _str, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.USERS_BY_EMPID.GBLT_USER_MST";
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
			query = query.replace("#", _str+"%");
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		List<UserVO> lstUsers = new ArrayList<UserVO>();
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(UserVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstUsers.add((UserVO) valueObjects[i]);
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
		return lstUsers;
	}
	
	/**
	 * Getting Serach Users By User Name
	 * @param _str Search String 
	 * @param _userVO User Detail
	 * @return
	 */
	public List<UserVO> getSearchUsersByUserName(String _str, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.USERS_BY_USERNAME.GBLT_USER_MST";
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
			query = query.replace("#", _str+"%");
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		List<UserVO> lstUsers = new ArrayList<UserVO>();
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(UserVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstUsers.add((UserVO) valueObjects[i]);
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
		return lstUsers;
	}
	
	
	/**
	 * Process is used to get profile Bound for desk type
	 * @param _str Desk Type String 
	 * @param _userVO User Detail
	 * @return
	 */
	public String getProfileBound(String _deskType, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.GBLT_DESK_TYPE_MST.PROFILE_BOUND";
		String profileBound="";
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
			populateMAP.put(sq.next(), _deskType);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				profileBound=rs.getString(1);
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
		return profileBound;
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
	
	public String getMaxEntryDateFromExtTreatDetail(String _patCrNo,String _episodeCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.MAX_SLNO.HRGT_EPISODE_EXTTREATMENT_DTL";
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
			//populateMAP.put(sq.next(), OpdConfig.EXTERNAL_TREATMENT);
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
	
	public DisclaimerMstVO fetchDisclaimerDetails(String _deptUnitCode,String profileType,UserVO _userVO)
	{
		//EpisodeVO _episodeVO = new EpisodeVO();
		DisclaimerMstVO _disclaimerVO=new DisclaimerMstVO();
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_DESC_DETAILS.HRGT_DISCLAIMER_MST";

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _deptUnitCode);
		//_populateMap.put(sq.next(), OpdConfig.HRGT_DISCLAIMER_MST_DISCHARGE_SUMMARY_USABILITY_FLAG);
		_populateMap.put(sq.next(), profileType);
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);

		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"
					+ e);
		}

		System.out.println("query" + query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);

			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Emergency Episode Found Against This CrNo.");
			}
			else
			{
				//System.out.println("Record found");
				//System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_disclaimerVO, rs);
				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			throw new HisException("OpdEssentialDAO:disclaimer Detai::etails:: " + e);
		}
		return _disclaimerVO;
	}

	
	public ProfileInvestigationVO[] getPatientInvestigationDetailsEMR(String _crNo,String [] departmentUnitArray,String accessType,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HIVT_REQUISITION_DTL_HIVT_REQUISITION_HEADER.ALL_INVESTIGATION_EMR";
		//String orderBy=" order by hivt_entry_date desc";//Commented By Shweta
		//String checks = "AND hgnum_group_code is null UNION SELECT DISTINCT ";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			//String[] query1 = query.split(" ",2);
			if(accessType.equals(MrdConfig.EPR_DISPLAY_ALL_RECORD_NO)){
				String inArg="";
				for(int i=0;i<departmentUnitArray.length;i++){
					inArg+=","+departmentUnitArray[i];
				}
				inArg=inArg.replaceFirst(",","");
				query+="  and (SELECT hgnum_deptunitcode FROM hrgt_episode_dtl WHERE hrgnum_episode_code = a.hrgnum_episode_code "
						+ "and HRGNUM_PUK=a.HRGNUM_PUK and HRGNUM_VISIT_NO=a.HRGNUM_VISIT_NO and gnum_isvalid=a.gnum_isvalid "
						+ " AND gnum_hospital_code = a.gnum_hospital_code) in ("+inArg+") ";
			}
			//query+= checks+query1[1]+"AND hgnum_group_code IS NOT NULL"+orderBy;
			//query+=orderBy;//Commented By Shweta
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _crNo);
			
			/**Added by Vasu on 30.Jan.2019*/
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _crNo);
		
			//populateMAP.put(sq.next(), _crNo);
			//populateMAP.put(sq.next(), _crNo);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("opdEssentialDAO.populateMAP::" + e);
		}

		ProfileInvestigationVO[] patProfileInvestigationVOs = null;
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(ProfileInvestigationVO.class, rs);
				patProfileInvestigationVOs = new ProfileInvestigationVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					patProfileInvestigationVOs[i] = (ProfileInvestigationVO) vo[i];
				}
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
		return patProfileInvestigationVOs;
	}
	
	/**
	 * Getting List of Duty Roles
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getDutyRolesList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.LIST.HDRT_DUTY_ROLE_MST";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Record for Duty Role Found");
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
			if (rs.next())	alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getDutyRolesList" + e);
		}
		return alRecord;
	}
	
	/**To get list of performed operations 
	 * @param crNo
	 * @param _userVO
	 * @param []departmentUnitArray 
	 * @return List of operations
	 */
	public List getOperationPerformedListEMR(String crNo,String []departmentUnitArray, String accessType,UserVO _userVO )
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HOTT_OP_REQUEST_DTL.LIST_EMR";
		String orderBy = " ORDER BY ot.hotdt_operation_date DESC";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(accessType.equals(MrdConfig.EPR_DISPLAY_ALL_RECORD_NO)){
				String inArg="";
				for(int i=0;i<departmentUnitArray.length;i++){
					inArg+=","+departmentUnitArray[i];
				}
				inArg=inArg.replaceFirst(",","");
				query+="  ot.hgnum_deptunitcode in ("+inArg+") ";
			}
			query+=orderBy;
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), OpdConfig.OT_NAME_ACTUAL);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), crNo);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Record for Duty Role Found");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List operationList = new ArrayList();
		try
		{
			while(rs.next())
			{
				List tempOperationList=new ArrayList();
				tempOperationList.add(rs.getString(1)); // OT Req No
				tempOperationList.add(rs.getString(2)); // Addmission No
				tempOperationList.add(rs.getString(3));// depaartment unit code
				tempOperationList.add(rs.getString(4)); // episode 
				tempOperationList.add(rs.getString(5)); // operation date
				tempOperationList.add(rs.getString(6)); //operation name
				tempOperationList.add(rs.getString(7)); //Visit No
				tempOperationList.add(rs.getString(8)); //Department Code
				
				
				operationList.add(tempOperationList);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getDutyRolesList" + e);
		}
		return operationList;
	}

	
	/**To get list of OT template  
	 * @param crNo
	 * @param _userVO
	 * @return List of operations
	 */
	public List getOTTemplateListEMR(String deptCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.HOTT_DEPT_TEMPLATE_CTRL_MST.TEMPLATE_ID_OT";
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
			populateMAP.put(sq.next(), deptCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Record for Duty Role Found");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List templateList = new ArrayList();
		try
		{
			while(rs.next())
			{
				String[] templateArray=rs.getString(1).split(":");
				for(int i=0;i<templateArray.length;i++)
				{
					templateList.add(templateArray[i]);
				}
				
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getDutyRolesList" + e);
		}
		return templateList;
	}


	/**To get Map of OT para id and value  
	 * @param crNo
	 * @param _userVO
	 * @return List of operations
	 */
	public List getOTTemplateParaValueEMR(String deptCode,String crNo,String reqNo,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.HOTT_OT_RECORD_PARA_DTL.OT_PARA_ID_VALUE";
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
			populateMAP.put(sq.next(), deptCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), crNo);
			populateMAP.put(sq.next(), reqNo);
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

		List paraValueIdList=new ArrayList();
		try
		{
			while(rs.next())
			{
				List tempList=new ArrayList();
				tempList.add(rs.getString(1)); // template Id
				tempList.add(rs.getString(2)); // parameter ID
				tempList.add(rs.getString(3)); // parameter value
				tempList.add(rs.getString(4)); // status code
				paraValueIdList.add(tempList);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getDutyRolesList" + e);
		}
		return paraValueIdList;
	}

	
	public List getPatAlertDetail(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_PAT_ALERTS.HGBT_PAT_ALERT_MST";

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
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
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
				//List alRecord = new ArrayList();//throw new HisRecordNotFoundException("No Alert Found");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage() );
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
			throw new HisDataAccessException("OpdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}


	/**
	 * Getting Macros By Process Id
	 * @param processId
	 * @param userVO
	 * @return
	 */
	public List<Entry> getMacrosByProcessId(String _processId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "GET_MACROS_BY_PROCESSID.HGBT_MACRO_MST";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _processId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Macro Found");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<Entry> alRecord = new ArrayList<Entry>();
		try
		{
			if (rs.next())	alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getMacrosByProcessId" + e);
		}
		return alRecord;
	}

	/**
	 * Getting Macros of given Process Id & Unit 
	 * @param _processId Process Id
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getUnitMacrosByProcessId(String _processId, String _unitCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "GET_MACROS_BY_PROCESSID.HGBT_MACRO_UNITWISE_MST";
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
			populateMAP.put(sq.next(), _processId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _unitCode);
			populateMAP.put(sq.next(), _processId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Macro Found");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<Entry> alRecord = new ArrayList<Entry>();
		try
		{
			if (rs.next())	alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getUnitMacrosByProcessId" + e);
		}
		return alRecord;
	}

	/**
	 * Getting Schedule Dates Unit User Date Wise
	 * @param _deptUnitCode Unit Code
	 * @param _userId User ID
	 * @param _date Date
	 * @param _userVO User Detail
	 * @return List of Schedule Dates
	 */
	public List<Entry> getOpdRosterScheduleDates(String _deptUnitCode, String _userId, String _date, UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(OpdDaoConfig.PROCEDURE_GET_OPD_SCHEDULE_DATES);
			strProc.addInParameter(1, Types.NUMERIC, _deptUnitCode);
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.NUMERIC, "3");
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_OPD_SCHEDULE_DATES:: " + errorMsg+" :: "+ e);
		}
		
		try
		{		
			//if (!rs.next()) throw new HisRecordNotFoundException("No Schedule Date Found");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		List<Entry> alRecord = new ArrayList<Entry>();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getOpdRosterScheduleDates" + e);
		}
		return alRecord;
	}
	
	public List getAllProfileGroupList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.GROUP_LIST.HPMRT_PROFILE_GROUP_MST";

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
			/*populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);*/
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
				// throw new HisRecordNotFoundException(e.getMessage());
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
			throw new HisDataAccessException("HisDataAccessException  :getAllGroupList" + e);
		}
		return alRecord;
	}
	
	public List getProfileGroupList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_GROUP_LIST.HPMRT_PROFILE_GROUP_MST";

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
				// throw new HisRecordNotFoundException(e.getMessage());
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
			throw new HisDataAccessException("HisDataAccessException  :getAllGroupList" + e);
		}
		return alRecord;
	}

	public List<UserVO> getSearchAllUsers( UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_USERS.GBLT_USER_MST";
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		List<UserVO> lstUsers = new ArrayList<UserVO>();
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(UserVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstUsers.add((UserVO) valueObjects[i]);
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
		return lstUsers;
	}

	
	public List getDiagnosisTypeListByDiseaseCode(UserVO _userVO,String str1,String str2)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DIAGNOSIS.TYPE.HGBT_DIAGNOSIS_TYPE_MST";

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

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), str1);
		populateMAP.put(sq.next(), str2);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Diagnosis Type Found");
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
			throw new HisDataAccessException();
		}
		return alRecord;
	}
	
	public List getDiagnosisSiteList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DIAGNOSIS.SITE.HGBT_DISEASE_SITE_MST";

		//first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Diagnosis Site Found ");
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
			throw new HisDataAccessException();
		}
		return alRecord;
	}

	
	public List<PatDrugTreatmentDetailVO> getPrevPatDrugDetailForPrinting(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.LAST_DRUG_DETAIL_PRINT.HRGT_EPISODE_DRUG_DTL";

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
		populateMAP.put(sq.next(), OpdConfig.ENTRY_MODE);

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


	public List getAllUnitNotMappedWithExtTreat(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.UNIT.NOT.IN.HGBT_EXT_UNIT_TREATMENT_MST";
		
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
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	
	
	public List getAllUnitNotMappedWithDrugs(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.UNIT.NOT.IN.hgbt_unit_drug_mapping_mst";
		
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
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	public List getAllDrugListValues(UserVO userVO)
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		//String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		//String queryKey = "SELECT.ALLDRUGLIST.HSTT_DRUGBRAND_MST";
		
		
		try
		{
			//query = HelperMethodsDAO.getQuery(filename, queryKey);
			HisDAO daoObj = new HisDAO("OPD","OpdEssentialDAO");
			nProcedureIndex = daoObj.setProcedure("{call pkg_opd_view.proc_get_drug_list(?,?,?,?,?,?)}");
			// Setting and Registering In and Out Parameters 
				daoObj.setProcInValue(nProcedureIndex, "p_mode","1",1);
				daoObj.setProcInValue(nProcedureIndex, "is_valid",Config.IS_VALID_ACTIVE ,2);
				daoObj.setProcInValue(nProcedureIndex, "hosp_code", userVO.getHospitalCode(),3);
				daoObj.setProcInValue(nProcedureIndex, "seat_id",userVO.getSeatId() ,4);
				daoObj.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
				daoObj.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor

				// Executing Procedure 
				daoObj.executeProcedureByPosition(nProcedureIndex);
				// Getting out parameters 
				strDBErr = daoObj.getString(nProcedureIndex, "err");
				rs = daoObj.getWebRowSet(nProcedureIndex, "resultset");
				
				// If Database Error Occurs, No further processing is required. 
				if (strDBErr != null && !strDBErr.equals(""))
				{
					throw new Exception("Data Base Error:" + strDBErr);
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
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	
	public List getAllExternalTreatment(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALLEXTTREATMENT.HGBT_EXT_TREATMENT_MST";
		
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
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	public UnitExtTreatMstVO[] getUnitExtTreatLstByDeptUnit(String deptUnitCode, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		ValueObject[] vo={};
		UnitExtTreatMstVO[] unitExtTreatMstVO=null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_EXT_UNIT_TREATMENT_MST";
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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("No audio Video File found For This Unit.");
			}
			else
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(UnitExtTreatMstVO.class,rs);
				unitExtTreatMstVO=new UnitExtTreatMstVO[vo.length];
				for(int i=0;i<vo.length;i++)
				{
					unitExtTreatMstVO[i]=(UnitExtTreatMstVO)vo[i];
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

		

		return unitExtTreatMstVO;
	}
	
	
	
	
	
	
	
	public UnitDrugMstVO[] getUnitDrugLstDetailByDeptUnit(String deptUnitCode, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		ValueObject[] vo={};
		UnitDrugMstVO[] unitDrugMstVO=null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.hgbt_unit_drug_mapping_mst";
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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("No audio Video File found For This Unit.");
			}
			else
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(UnitDrugMstVO.class,rs);
				unitDrugMstVO=new UnitDrugMstVO[vo.length];
				
				for(int i=0;i<vo.length;i++)
				{
					unitDrugMstVO[i]=(UnitDrugMstVO)vo[i];
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

		

		return unitDrugMstVO;
	}
	

	/**
	 * Getting List of Episode Keywords Unit Wise
	 * @param _unitCode Department Unit Code
	 * @param _userVO User Detail
	 * @return List of Episode Keywords Objects
	 */
	public List<EpisodeKeywordsMasterVO> getEpisodeKeywordsUnitWise(String _unitCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.LIST_UNIT_WISE.HGBT_EPI_KEYWORD_UNITWISE_MST";
		//String queryKey = "SELECT.LIST_ALL_KEYWORDS.HGBT_EPISODE_KEYWORD_MST";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _unitCode);
		   populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Default Image Exists ...");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<EpisodeKeywordsMasterVO> lstUnitKeywords = new ArrayList<EpisodeKeywordsMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(EpisodeKeywordsMasterVO.class,rs);
				for(ValueObject v :vo)
					lstUnitKeywords.add((EpisodeKeywordsMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getting Unit Wise Episode Keywords List" + e);
		}
		return lstUnitKeywords;
	}

	public List getAllAttendantReasonList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ALL_REASON.HGBT_ATTENDANT_REASON_MST";
		// first call the getQueryMethod with arguments filename,querykey from prop file
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
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Attendant Reason Found ");
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
			throw new HisDataAccessException("HisDataAccessException  :" + e);
		}

		return alRecord;
	}
	
	public String getPatientFatherMotherSpouceName(String patCrNo)
	{
		String query = "";
		ResultSet rs;

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey ="GET_PATIENT_FAT_MOM_SPOUCE_NAME.HRGT_PATIENT_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), patCrNo);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}
	
	public List getAllUnitNotMappedWithMacro(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.UNIT.NOT.IN.HGBT_MACRO_UNITWISE_MST";
		
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
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	public List getAllMacro(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALLMACRO.HGBT_MACRO_MST";
		
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
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	
	public List getAllMacroProcessList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_MACRO_PROCESS.HGBT_MACRO_MST";
		
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
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(),userVO.getHospitalCode());
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	public UnitWiseMacroMstVO[] getUnitMacroLstByDeptUnit(String deptUnitCode, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		ValueObject[] vo={};
		UnitWiseMacroMstVO[] unitWiseMacroMstVO=null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_MACRO_UNITWISE_MST";
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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("No audio Video File found For This Unit.");
			}
			else
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(UnitWiseMacroMstVO.class,rs);
				unitWiseMacroMstVO=new UnitWiseMacroMstVO[vo.length];
				for(int i=0;i<vo.length;i++)
				{
					unitWiseMacroMstVO[i]=(UnitWiseMacroMstVO)vo[i];
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

		

		return unitWiseMacroMstVO;
	}
	
	public List getAllImageDescWithColorCode(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_IMAGE_DESC_WITH_COLOR.HOPT_IMAGEDESC_MST";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("NO Image Description Found");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("opd Essential dao" + e);
		}

		return alRecord;
	}
	
	
	public UnitImageDescMasterVO[] getUnitImageDescLstByDeptUnit(String deptUnitCode, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		ValueObject[] vo={};
		UnitImageDescMasterVO[] unitImageDescMasterVO=null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HOPT_UNIT_IMAGEDESC_MST";
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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("No audio Video File found For This Unit.");
			}
			else
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(UnitImageDescMasterVO.class,rs);
				unitImageDescMasterVO=new UnitImageDescMasterVO[vo.length];
				for(int i=0;i<vo.length;i++)
				{
					unitImageDescMasterVO[i]=(UnitImageDescMasterVO)vo[i];
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

		

		return unitImageDescMasterVO;
	}
	
	public List getAllUnitNotMappedWithDrugList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.UNIT.NOT.IN.HGBT_DRUGLIST_UNITWISE_MST";
		
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
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	public List getAllDrugList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALLDRUGLIST.HGBT_DRUG_LIST_MST";
		
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
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	
	public UnitDrugListMasterVO[] getUnitDrugLstByDeptUnit(String deptUnitCode, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		ValueObject[] vo={};
		UnitDrugListMasterVO[] unitDrugListMasterVO=null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_DRUGLIST_UNITWISE_MST";
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

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); 	//This line is added by adil 
																		//to pass super hospital code while 
																		//retrieving record from 
																		//hgbt_drug_list_mst table
		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("No audio Video File found For This Unit.");
			}
			else
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(UnitDrugListMasterVO.class,rs);
				unitDrugListMasterVO=new UnitDrugListMasterVO[vo.length];
				for(int i=0;i<vo.length;i++)
				{
					unitDrugListMasterVO[i]=(UnitDrugListMasterVO)vo[i];
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

		

		return unitDrugListMasterVO;
	}
	
	public List<PatDrugTreatmentDetailVO> getDefaultDrugProfileForUnit(String depUnitCode, UserVO _userVO)
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
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		
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
	
	public List getAllDrugListByDeptUnit(String deptUnitCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALLDRUGLIST_BY_DEPTUNIT.HGBT_DRUG_LIST_MST";
		
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
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),deptUnitCode);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
		
	}
	
	public List<PatDrugTreatmentDetailVO> getParticularDrugListDetail(String drugListId, UserVO _userVO)
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

	// List of All Episode Keywords
	public List<EpisodeKeywordsMasterVO> getAllEpisodeKeywordList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.LIST_ALL_KEYWORDS.HGBT_EPISODE_KEYWORD_MST";

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
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
		List<EpisodeKeywordsMasterVO> lstKeywords = new ArrayList<EpisodeKeywordsMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(EpisodeKeywordsMasterVO.class,rs);
				for(ValueObject v :vo)
					lstKeywords.add((EpisodeKeywordsMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getting Unit Wise Episode Keywords List" + e);
		}
		System.out.println("listkeywords::"+lstKeywords.size());
		return lstKeywords;
	}	

	// List of All Used ICD Group
	public List<IcdGroupMasterVO> getAllUsedICDGroups(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.LIST_ALL_USED.HGBT_ICD_GROUP_MST";

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<IcdGroupMasterVO> lstGroups = new ArrayList<IcdGroupMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdGroupMasterVO.class,rs);
				for(ValueObject v :vo)
					lstGroups.add((IcdGroupMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getAllUsedICDGroups" + e);
		}
		return lstGroups;
	}

	// List of All Used ICD Sub Group
	public List<IcdSubgroupMasterVO> getAllUsedICDSubGroups(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.LIST_ALL_USED.HGBT_ICD_SUBGROUP_MST";

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<IcdSubgroupMasterVO> lstSubGroups = new ArrayList<IcdSubgroupMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdSubgroupMasterVO.class,rs);
				for(ValueObject v :vo)
					lstSubGroups.add((IcdSubgroupMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getAllUsedICDSubGroups" + e);
		}
		return lstSubGroups;
	}

	// List of All ICD Disese & Sub Diseases
	public List<IcdDiseaseMasterVO> getAllICDDisease(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.LIST_MAIN_SUB.HGBT_ICD_DISEASE_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdDiseaseMasterVO.class,rs);
				for(ValueObject v :vo)
					lstDiseases.add((IcdDiseaseMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getAllICDDisease" + e);
		}
		return lstDiseases;
	}

	// ICD Code List including ICD Diseases, Sub Diseases, Includes, Synonyms not including Excludes
	public List<IcdDiseaseMasterVO> getICDDiseaseCodeList(UserVO _userVO)
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
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdDiseaseMasterVO.class,rs);
				for(ValueObject v :vo)
					lstDiseases.add((IcdDiseaseMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :getICDDiseaseCodeList" + e);
		}
		return lstDiseases;
	}

	// Get Subgroup Group Wise
	public List<IcdSubgroupMasterVO> getSubgroupsByGroup(String _icdGroupCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.SUBGROUP_BY_GROUP.HGBT_ICD_SUBGROUP_MST";

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
			populateMAP.put(sq.next(), _icdGroupCode);
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
		List<IcdSubgroupMasterVO> lstSubGroups = new ArrayList<IcdSubgroupMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdSubgroupMasterVO.class,rs);
				for(ValueObject v :vo)
					lstSubGroups.add((IcdSubgroupMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getSubgroupsByGroup" + e);
		}
		return lstSubGroups;
	}

	// Get Disease SubGroup Wise
	public List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String _icdSubgroupCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.DISEASES_BY_SUBGROUP.HGBT_ICD_DISEASE_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
			populateMAP.put(sq.next(), _icdSubgroupCode);
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
		List<IcdDiseaseMasterVO> lstDisease = new ArrayList<IcdDiseaseMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdDiseaseMasterVO.class,rs);
				for(ValueObject v :vo)
					lstDisease.add((IcdDiseaseMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getSubgroupsByGroup" + e);
		}
		return lstDisease;
	}

	/**
	 * Get Chart Category Wise Chart List
	 * 
	 * @param _chartCategory Chart Category
	 * @param _userVO User Detail
	 * @return List of Chart Detail
	 */
	public List<ChartMasterVO> getChartsByCategory(String _chartCategory, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.CHARTS_BY_CATEGORY.HGBT_CHART_MST";

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
			String strCat = "";
			if(_chartCategory.equals(OpdConfig.CHART_CATEGORY_OPD))
				strCat = _chartCategory + "," + OpdConfig.CHART_CATEGORY_OPD_IPD;
			else if(_chartCategory.equals(OpdConfig.CHART_CATEGORY_IPD))
				strCat = _chartCategory + "," + OpdConfig.CHART_CATEGORY_OPD_IPD;
			else
				strCat = _chartCategory;
			query = query.replace("#", strCat);
				
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
		List<ChartMasterVO> lstCharts = new ArrayList<ChartMasterVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(ChartMasterVO.class,rs);
				for(ValueObject v :vo)
					lstCharts.add((ChartMasterVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getChartsByCategory" + e);
		}
		return lstCharts;
	}

	/**
	 * Getting Start Date of The Patient Episode i.e Date of First Visit of the Episode
	 *  
	 * @param _patDetailVO Patient Detail
	 * @param _userVO User Detail
	 * @return Start Date
	 */
	public String getEpiosdeStartDate(PatientDetailVO _patDetailVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.EPISODE_START_DATE.HRGT_EPISODE_DTL";

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
			populateMAP.put(sq.next(), _patDetailVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDetailVO.getEpisodeCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
		String episodeStartDate = "";
		try
		{
			if(rs.next())
			{
				rs.first();
				episodeStartDate = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getEpiosdeStartDate" + e);
		}
		return episodeStartDate;
	}
	
	/**
	 * Getting Start Date of The Patient Admission
	 *  
	 * @param _patDetailVO Patient Detail
	 * @param _userVO User Detail
	 * @return Start Date
	 */
	public String getAdmissionStartDate(PatientDetailVO _patDetailVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ADMISSION_START_DATE.HIPT_PATADMISSION_DTL";

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
			populateMAP.put(sq.next(), _patDetailVO.getPatAdmNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
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
		String admissionStartDate = "";
		try
		{
			if(rs.next())
			{
				rs.first();
				admissionStartDate = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getAdmissionStartDate" + e);
		}
		return admissionStartDate;
	}

	
	public List getChartUnitListEssential(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		List chartList=new ArrayList();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT_CHART_UNIT_LIST.HGBT_CHART_MST";

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

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
//			
			
			if(rs.next())
			{
				chartList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
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
		return chartList;
	}
	
	public List getAllUnitNotMappedWithChartList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.UNIT.NOT.IN.HGBT_CHART_UNIT_MAPPING";
		
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
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
	}
	public ChartUnitMapppingVO[] getUnitChartLstByDeptUnit(String _deptUnitCode, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		ValueObject[] vo={};
		ChartUnitMapppingVO[] unitChartListMasterVO=null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_CHART_UNIT_MAPPING";
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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);	//This line is added by adil 
																		//to pass super hospital code while 
																		//retrieving record from 
																		//hgbt_drug_list_mst table
		populateMAP.put(sq.next(), _deptUnitCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("No audio Video File found For This Unit.");
			}
			else
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(ChartUnitMapppingVO.class,rs);
				unitChartListMasterVO=new ChartUnitMapppingVO[vo.length];
				for(int i=0;i<vo.length;i++)
				{
					unitChartListMasterVO[i]=(ChartUnitMapppingVO)vo[i];
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

		

		return unitChartListMasterVO;
	}
	public List getAllUnitNotMapped(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.UNIT.NOT.IN.HGBT_DEPT_ICD_MST";
		
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
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
	}


	/**
	 * Getting List of All General Units of the Given Department
	 * 
	 * @param _deptCode Department Code
	 * @param _userVO User Detail
	 * @return List of Entry objects of the Unit Code and Name
	 */
	public List<Entry> getGeneralUnitsListByDept(String _deptCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_CLINICAL_UNIT_BY_DEPT.HGBT_UNIT_MST";

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
			populateMAP.put(sq.next(), ((_deptCode!=null) && (_deptCode.equals("")))?null:_deptCode);
			populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())	return null; 
				//throw new HisRecordNotFoundException("");
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
			throw new HisDataAccessException("HisDataAccessException  :getAllUnits" + e);
		}
		return alRecord;
	}

	// Departmemt Unit Hospital disease Mapping
	// To get Unit not mapped with department for hospital Disease
	
	
	public List getNotMappedUnit(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.UNIT.NOT.MAPPED.HGBT_DEPT_HOSDISEASE_MST";
		
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
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			
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
			throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
		}
		return alRecord;
	}

	
	public List getHospitalDisease(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HGBT_HOSPITAL_DISEASE_MST";
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
//		log.error(query + "\n");

//		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),_userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Hospital Disease found.  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getHospitalDisease" + e);
		}

		return alRecord;
	}
	
	public List getChronicDisease(UserVO _userVO)
	{

		ResultSet rs = null;
		String strQuery = "";
		Map mapPopulateMAP = new HashMap();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String strQueryKey = "ESSENTIAL.HGBT_PAT_ALERT_MST";
		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			strQuery = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();

		mapPopulateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		mapPopulateMAP.put(sq.next(),_userVO.getHospitalCode());
		mapPopulateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		mapPopulateMAP.put(sq.next(),_userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), strQuery, mapPopulateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Chronic Disease found. ");
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List listAlRecord = new ArrayList();

		try
		{
			listAlRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return listAlRecord;
	}
	
	
	public String getMinimumYear(String strMode_p, UserVO voUser_p)
	{
		HisDAO daoObj = null;
		String strFunc = "{? = call Pkg_opd_rpt.get_min_year(?,?)}";
		int nfuncIndex = 0;
		String strYear = "";

		try 
		{
			daoObj = new HisDAO("Opd","OpdEssentialDAO.get_min_year()");

			nfuncIndex = daoObj.setFunction(strFunc);
		
			daoObj.setFuncInValue(nfuncIndex, 2, strMode_p);
			daoObj.setFuncInValue(nfuncIndex, 3, voUser_p.getHospitalCode());
			daoObj.setFuncOutValue(nfuncIndex, 1);
		
			daoObj.executeFunction(nfuncIndex);
		
			strYear = daoObj.getFuncString(nfuncIndex);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return strYear;
	}
	
	public List getDischargeTypeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String strQuery = "";
		Map mapPopulateMAP = new HashMap();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String strQueryKey = "ESSENTIAL.HIPT_DISCHARGE_TYPE_MST";
		try
		{
			strQuery = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();

		mapPopulateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		mapPopulateMAP.put(sq.next(),_userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), strQuery, mapPopulateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No records for Chronic Disease found. ");
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List listAlRecord = new ArrayList();

		try
		{
			listAlRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HisDataAccessException  :getDischargeTypeList" + e);
		}

		return listAlRecord;
	}

	/**
	 * Getting Final Discharge Adviced BY Consultant List
	 * @param _deptUnitCode Unit Code
	 * @param _userVO User Detail
	 * @return List of Schedule Dates
	 */
	public List<Entry> getConsultantList(String _deptUnitCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String strQuery = "";
		Map mapPopulateMAP = new HashMap();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String strQueryKey = "ESSENTIAL.CONSULTANT_LIST";
		try
		{
			strQuery = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();

		mapPopulateMAP.put(sq.next(),_userVO.getHospitalCode());
		mapPopulateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), strQuery, mapPopulateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No records for Consultant List. ");
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List listAlRecord = new ArrayList();

		try
		{
			listAlRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HisDataAccessException  :getConsultantList" + e);
		}

		return listAlRecord;
//		ResultSet rs = null;
//		String errorMsg = "";
//		try
//		{
//			Procedure strProc = new Procedure(OpdDaoConfig.PROCEDURE_GET_CONSULTANT_LIST);
//			strProc.addInParameter(1, Types.VARCHAR, "2");
//			strProc.addInParameter(2, Types.VARCHAR, _deptUnitCode);
//			strProc.addInParameter(3, Types.VARCHAR, _userVO.getHospitalCode());
//			strProc.addInParameter(4, Types.VARCHAR, _userVO.getSeatId());
//			strProc.addOutParameter(5, Types.VARCHAR);
//			strProc.addOutParameter(6, OracleTypes.CURSOR);
//
//			strProc.execute(super.getTransactionContext().getConnection());
//			errorMsg = (String) strProc.getParameterAt(4);
//			rs = (ResultSet) strProc.getParameterAt(5);
//		}
//		catch (Exception e)
//		{
//			throw new HisDataAccessException("PROCEDURE_GET_CONSULTANT_LIST:: " + errorMsg+" :: "+ e);
//		}
//		
//		try
//		{		
//			//if (!rs.next()) throw new HisRecordNotFoundException("No Schedule Date Found");
//		}
//		catch (Exception e)
//		{
//			if (e.getClass() == HisRecordNotFoundException.class)
//			{
//				throw new HisRecordNotFoundException(e.getMessage());
//			}
//			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
//		}
//		
//		List<Entry> alRecord = new ArrayList<Entry>();
//		try
//		{
//			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
//		}
//		catch (Exception e)
//		{
//			throw new HisDataAccessException("HisDataAccessException:getConsultantList" + e);
//		}
//		return alRecord;
	}
	
	
	/* Functions Created By Pawan Kumar B N*/
	public List getParameterForPatientComplaints(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "GET_PARA_LIST_TEMP_CAT_WISE.HGBT_PARAMETER_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), GenericTemplateConfig.TEMPLATE_GROUP_CLINICAL);
		//populateMAP.put(sq.next(), GenericTemplateConfig.PARAMETER_TYPE_VITAL_MONITORING);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Parameter Found  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	
	public List getUsersOpd(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String querykey = "ESSENTIAL.OPD.USERS.GBLT_USER_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, querykey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),_userVO.getHospitalCode()); 
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for User found. Either there are no records in database or no records against ur seat id exist ");
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
			throw new HisDataAccessException("UserDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	public List<Entry> getProfileTypes(String usablity,String generationMode, UserVO _uservo)
	{
		ResultSet rs = null;
		String strQuery = "";
		Map mapPopulateMAP = new HashMap();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String strQueryKey = "SELECT.HPMRT_PROFILE_TYPE_MST_FOR_REFER_PATIENT";
		try
		{
			strQuery = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();

		mapPopulateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		mapPopulateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);//changedby:NehaRajgariya Date:7Sept2016
		//mapPopulateMAP.put(sq.next(), _uservo.getHospitalCode());
		mapPopulateMAP.put(sq.next(), usablity);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), strQuery, mapPopulateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No records for Consultant List. ");
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List listAlRecord = new ArrayList();

		try
		{
			listAlRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HisDataAccessException  :getConsultantList" + e);
		}

		return listAlRecord;
	}
	
	
	 /* 
       Author       : Manisha Gangwar
	   Date         : 06/04/2016
	   Reason 		: For DB-Link Implementation.
	   Method       : getGenericDrugListDetail
	   Detail       : To fetch the list of drugs on the basis of mode passed from BO.
	 */
	
	
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
	
	
		
	/* 
       Author       : Manisha Gangwar
	   Date         : 06/04/2016
	   Reason 		: For DB-Link Implementation.
	   Method       : getDrugIntakeTimingsListDetail
	   Detail       : To fetch the list of drugs intake timings(before, after or with meal).
	 */
	
	
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
			throw new HisDataAccessException("OpdEssentialDAO.getDrugAdminFlagList::hisDAO_p.execute" + OpdConfig.PROC_OPD_VIEW_DRUG_ADMIN_FLAG_LIST
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
			throw new HisDataAccessException("OpdEssentialDAO.getDrugAdminFlagList::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
	
	
	/* 
    Author       : Manisha Gangwar
	   Date         : 08/04/2016
	   Reason 		:  For geeting pregnant category list
	   Method       : getDrugIntakeTimingsListDetail
	   Detail       : To fetch the list of pregnant category (for Pregnancy Category Alert).
	 */
	
	
	public List<Entry> getPatPregnantCategoryList(HisDAO hisDAO_p, String pmode)
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(OpdConfig.PROC_OPD_VIEW_PAT_PREGNANT_CATEGORY_LIST);
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
			throw new HisDataAccessException("OpdEssentialDAO.getPatPregnantCategoryList::hisDAO_p.execute" + OpdConfig.PROC_OPD_VIEW_PAT_PREGNANT_CATEGORY_LIST
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
			throw new HisDataAccessException("OpdEssentialDAO.getPatPregnantCategoryList::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
   /* 
       Author       : Chetan Sharma
	   Date         : 27/11/2015
	   Reason 		: For DB-Link Implementation.
	   Method       : getDrugListDetail
	   Detail       : To fetch the list of drugs on the basis of mode passed from BO.
	 */
	
	
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
			throw new HisDataAccessException("OpdEssentialDAO.getDrugListDetail::hisDAO_p.execute" + OpdConfig.PROC_OPD_VIEW_DRUGS_DETAILS
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

	
	 /* 
    Author          : Chetan Sharma
	   Date         : 04/12/2015
	   Reason 		: For DB-Link Implementation.
	   Method       : getItemTypeList 
	   Detail       : To fetch the list of Item Type List.
	 */
	
	
	public List<Entry> getItemTypeList(HisDAO hisDAO_p, String mode, PatientDetailVO _patDetailVO, UserVO _userVO)
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(OpdConfig.PROC_OPD_VIEW_GET_ITEM_TYPE_DETAILS);
			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", mode,1);
		    hisDAO_p.setProcInValue(nProcedureIndex, "p_item_cat",(OpdConfig.DRUG_CATEGORY) ,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", (_userVO.getSeatId()==null)?"":_userVO.getSeatId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hosp_code", _userVO.getHospitalCode(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor

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
			throw new HisDataAccessException("OpdEssentialDAO.getItemTypeListDetail::hisDAO_p.execute" + OpdConfig.PROC_OPD_VIEW_GET_ITEM_TYPE_DETAILS
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
			throw new HisDataAccessException("OpdEssentialDAO.getItemTypeListDetail::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	public List getDiseaseTypeList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DISEASE_TYPE.HGBT_DISEASE_TYPE_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Disease Type found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	
	public List getMapIcdList(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.MAPPED.ICD.HGBT_DISEASE_MAPPING_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _hospitalDiseaseMasterVO.getDiseaseID());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	public List getMapSnomedList(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.MAPPED.SNOMED.HGBT_DISEASE_MAPPING_MST";
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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();


		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _hospitalDiseaseMasterVO.getDiseaseID());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd Group found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	/* 
    Author       	: Shruti Shail
	   Date         : 10/02/2017
	   Reason 		: to Show Stock Detail of Drug at Treatment Detail Page
	   Method       : getDrugStockDetail
	   Detail       : To fetch the Stock of drugs
	 */
	
	
	public Map getDrugStockDetail(HisDAO hisDAO_p, String mode, PatientDetailVO _patDetailVO, UserVO _userVO)
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		Map<String,String> stockMap = new HashMap();
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(OpdConfig.PROC_OPD_VIEW_DRUG_STOCK_LIST); // DATE: 10.02.2017 // ADDED FOR GETTING STOCK
			
			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",mode,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "item_id","0",2);
			hisDAO_p.setProcInValue(nProcedureIndex, "ward_code", (_patDetailVO.getWardCode()==null)?"":_patDetailVO.getWardCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", _userVO.getHospitalCode(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor

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
			throw new HisDataAccessException("OpdEssentialDAO.getDrugStockDetail::hisDAO_p.execute" + OpdConfig.PROC_OPD_VIEW_DRUG_STOCK_LIST
					+ ") -> " + e.getMessage());
		}
		

		
		List<Entry> lst = new ArrayList<Entry>();
		
		try
		{
			lst = HelperMethodsDAO.getAlOfEntryObjects(objResSet);
			for(Entry i : lst)
				stockMap.put(i.getValue(), i.getLabel());
			System.out.println("stockMap:"+stockMap.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("OpdEssentialDAO.getDrugStockDetail::HelperMethods.populateVOfrmRS -> " + e);
		}
		return stockMap;
	}

	@Override
	public List getDeptList(UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**Added by Vasu on 25.Oct.2018 for ICD-O Integration*/
	public List getDiagnosisSiteCodeList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		// String queryKey="ESSENTIAL.DIAGNOSIS.CODE.UNIT.WISE.HGBT_DEPT_ICD_MST"; Fetching Data from DEPT_ICD_MST
		String queryKey = "ESSENTIAL.DIAGNOSIS.SITE_LIST.HGBT_DISEASE_SITE_MST"; // fetching data from ICD_DISEASE_MST

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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		/*
		 * populateMAP.put(sq.next(),_code); populateMAP.put(sq.next(),_code); populateMAP.put(sq.next(),_code);
		 */
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Diagnosis Site found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}

	/**Added by Vasu on 25.Oct.2018 for ICD-O Integration*/
	public List getMorphologyCodeList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		// String queryKey="ESSENTIAL.DIAGNOSIS.CODE.UNIT.WISE.HGBT_DEPT_ICD_MST"; Fetching Data from DEPT_ICD_MST
		String queryKey = "ESSENTIAL.MORPHOLOGY.CODE.HGBT_ICD_O_MORPHOLOGY_MST"; // fetching data from ICD_DISEASE_MST

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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		/*
		 * populateMAP.put(sq.next(),_code); populateMAP.put(sq.next(),_code); populateMAP.put(sq.next(),_code);
		 */
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Morphology found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}	
	
	
	public List getDiagnosisSiteCodeListForICDEssentials(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		// String queryKey="ESSENTIAL.DIAGNOSIS.CODE.UNIT.WISE.HGBT_DEPT_ICD_MST"; Fetching Data from DEPT_ICD_MST
		String queryKey = "ESSENTIAL.DIAGNOSIS.SITE_LIST.HGBT_DISEASE_SITE_MST"; // fetching data from ICD_DISEASE_MST

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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		/*
		 * populateMAP.put(sq.next(),_code); populateMAP.put(sq.next(),_code); populateMAP.put(sq.next(),_code);
		 */
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		List<IcdDiseaseMasterVO> lstDiseases = new ArrayList<IcdDiseaseMasterVO>();
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
				return lstDiseases;
				//throw new HisRecordNotFoundException(
					//"No records for Diagnosis Site found. Either there are no records in database or no records against your seat id exist  ");
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		ValueObject[] vo = {};

		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdDiseaseMasterVO.class,rs);
				for(ValueObject v :vo)
					lstDiseases.add((IcdDiseaseMasterVO)v);
			}
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return lstDiseases;
	}

	
	public List getMorphologyCodeListForICDEssentials(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		// String queryKey="ESSENTIAL.DIAGNOSIS.CODE.UNIT.WISE.HGBT_DEPT_ICD_MST"; Fetching Data from DEPT_ICD_MST
		String queryKey = "ESSENTIAL.MORPHOLOGY.CODE.HGBT_ICD_O_MORPHOLOGY_MST"; // fetching data from ICD_DISEASE_MST

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
		log.error(query + "\n");

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		/*
		 * populateMAP.put(sq.next(),_code); populateMAP.put(sq.next(),_code); populateMAP.put(sq.next(),_code);
		 */
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		List<IcdDiseaseMasterVO> lstDiseases = new ArrayList<IcdDiseaseMasterVO>();
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
				return lstDiseases;
				//throw new HisRecordNotFoundException(
					//"No records for Morphology found. Either there are no records in database or no records against your seat id exist  ");
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		ValueObject[] vo = {};

		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdDiseaseMasterVO.class,rs);
				for(ValueObject v :vo)
					lstDiseases.add((IcdDiseaseMasterVO)v);
			}
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return lstDiseases;
	}	
	
	
	//Added by Dheeraj on 05-Nov-2018 
	
		public List getCompositionType(String hospitalCode, UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();

			//String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			//String queryKey = "SELECT.DESK_BASEDON_TYPE.GBLT_DESK_MST";

			try
			{
				//query = HelperMethodsDAO.getQuery(filename, queryKey);
				query = "select gnum_composition_type_code as compositionType,gstr_composition_type as compositionName from ehrts_composition_type_mst";
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
			//	populateMAP.put(sq.next(), _DeskType);
			//	populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
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
				throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
			}
			return alRecord;
		}

		//ADDED BY PRACHI
		public List getSection(String hospitalCode, UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();

			

			try
			{
				
				query = "select gnum_clinical_section_code as section,gstr_clinical_section as sectionName from ehrts_clinical_section_mst";
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
			//	populateMAP.put(sq.next(), _DeskType);
			//	populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
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
				throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
			}
			return alRecord;
		}
		
		
		
		public List getTemplate(String hospitalCode, UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();

			

			try
			{
				
				query = "select gnum_section_template_id as template,gstr_section_template as templateName from ehrts_section_templates_mst";
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
			//	populateMAP.put(sq.next(), _DeskType);
			//	populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
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
				throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
			}
			return alRecord;
		}
		
		
		
	//Added by Dheeraj on 08-Nov-2018
		
		public List getClinicalSection(String hospitalCode, UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();

			//String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			//String queryKey = "SELECT.DESK_BASEDON_TYPE.GBLT_DESK_MST";

			try
			{
				//query = HelperMethodsDAO.getQuery(filename, queryKey);
				//query = "select gnum_clinical_section_code, gstr_clinical_section,gstr_clinical_section_short from ehrts_clinical_section_mst";
				query = "select gnum_clinical_section_code, gstr_clinical_section from ehrts_clinical_section_mst";
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
			//	populateMAP.put(sq.next(), _DeskType);
			//	populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
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
				throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
			}
			return alRecord;
		}

		
		//added by swati sagar on date:28-may-2019
		
//		public static void writeResponse(HttpServletResponse resp, String output){
//			try{
//				resp.reset();
//				resp.setContentType("text/xml");
//				resp.setHeader("Cache-Control", "no-cache");
//				resp.getWriter().write(output);
//			}
//			catch(Exception e){
//				System.out.println(e);
//			}
//			
//		}
		
//		public static void writeResponse(HttpServletResponse resp, String output){
//			try{
//				resp.reset();
//				resp.setContentType("text/xml");
//				resp.setCharacterEncoding("UTF-8");
//				resp.setHeader("Cache-Control", "no-cache");
//				System.out.println(output);
//			resp.getWriter().write(output);
//			}
//			catch(Exception e){
//				System.out.println(e);
//			}
//		}
		
		/* public static void writeResponse(HttpServletResponse response, String message)
				    throws IOException {
				        response.setHeader("Cache-Control", "no-cache");
				        response.setHeader("Pragma", "no-cache");
				        PrintWriter writer = response.getWriter();
				        writer.print(message);
				        writer.flush();
				        writer.close();
				    }

		
		
		
		//Added by swati on date:28-05-2019
		
				public void getClinicalSectionKey(SinglePageInterfaceMasterVO singlePageInterfaceMasterVO,UserVO _userVO,HttpServletRequest request_p,HttpServletResponse response)
				{
					ResultSet rs = null;
					String query = "";
					Map populateMAP = new HashMap();
					String result="";
                       String clinicalcode=singlePageInterfaceMasterVO.getClinicalSectionCode();
					String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
					String queryKey = "SELECT.CLINICAL_SECTION_KEY";

                       
					try
					{
						//query = HelperMethodsDAO.getQuery(filename, queryKey);
						//query = "select gnum_clinical_section_code, gstr_clinical_section,gstr_clinical_section_short from ehrts_clinical_section_mst";
					//	query = "select  gstr_clinical_section_short from ehrts_clinical_section_mst where gnum_clinical_section_code = "clinicalcode";
					
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
					populateMAP.put(sq.next(), singlePageInterfaceMasterVO.getClinicalSectionCode());

					try
					{
					//	populateMAP.put(sq.next(), _DeskType);
					//	populateMAP.put(sq.next(), _userVO.getHospitalCode());
					//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
						
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
					try{
						 while (rs.next()) {

							 result=rs.getString(1);
					        }
						
						writeResponse(response, result);
						}catch(Exception e){
							e.printStackTrace();
						}
				}*/

		
		
		
		
		//Added by Dheeraj on 08-Nov-2018
		
			public List getSubSection(String hospitalCode, UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();

				//String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
				//String queryKey = "SELECT.DESK_BASEDON_TYPE.GBLT_DESK_MST";

				try
				{
					//query = HelperMethodsDAO.getQuery(filename, queryKey);
					query = "select gnum_clinical_subsection_code, gstr_clinical_subsection from ehrts_clinical_subsection_mst";
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
				//	populateMAP.put(sq.next(), _DeskType);
				//	populateMAP.put(sq.next(), _userVO.getHospitalCode());
				//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					
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
					throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
				}
				return alRecord;
			}

			//Added by Dheeraj on 08-Nov-2018
			
			public List getInterfaceType(String hospitalCode, UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();

				//String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
				//String queryKey = "SELECT.DESK_BASEDON_TYPE.GBLT_DESK_MST";

				try
				{
					//query = HelperMethodsDAO.getQuery(filename, queryKey);
					query = "select gnum_interface_type_code, gstr_interface_type from ehrts_interface_type_mst";
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
				//	populateMAP.put(sq.next(), _DeskType);
				//	populateMAP.put(sq.next(), _userVO.getHospitalCode());
				//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					
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
					throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
				}
				return alRecord;
			}
			
			//Added by Dheeraj on 08-Nov-2018
			
			public List getSectionInterface(String hospitalCode, UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();

				//String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
				//String queryKey = "SELECT.DESK_BASEDON_TYPE.GBLT_DESK_MST";

				try
				{
					//query = HelperMethodsDAO.getQuery(filename, queryKey);
					query = "select gnum_section_interface_id, gstr_section_interface from ehrts_section_interfaces_mst";
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
				//	populateMAP.put(sq.next(), _DeskType);
				//	populateMAP.put(sq.next(), _userVO.getHospitalCode());
				//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					
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
					throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
				}
				return alRecord;
			}

			
			//Added by Dheeraj on 08-Nov-2018
			
			public void saveClinicalComposition(ClinicalSectionCompMapFB fb, UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();

				//String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
				//String queryKey = "SELECT.DESK_BASEDON_TYPE.GBLT_DESK_MST";

				try
				{
					//query = HelperMethodsDAO.getQuery(filename, queryKey);
					query = "INSERT INTO ehrt_comp_sec_interfaces_map_mst(gnum_composition_type_code, gnum_mapping_type, gnum_clinical_section_code, "
							+ "gnum_clinical_subsection_code, gnum_section_interface_type, gnum_section_interface_id, gnum_hospital_code, "
							+ "gnum_userseat_id, hgnum_deptunitcode, gnum_dept_code, gstr_section_title, gnum_seat_id, gnum_isvalid, "
							+ "gdt_entry_date, gdt_lstmod_date, gnum_lstmod_seatid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
					populateMAP.put(sq.next(), fb.getCompositionType());
					populateMAP.put(sq.next(), fb.getAdditionMode());
					populateMAP.put(sq.next(), fb.getSectionCode());
					//populateMAP.put(sq.next(), fb.getSubSectionCode());   //Data not available in Table
					populateMAP.put(sq.next(), "1");
					populateMAP.put(sq.next(), fb.getInterfaceType());
					//populateMAP.put(sq.next(), fb.getSectionInterface());  // Data not available in Table
					populateMAP.put(sq.next(), "2");
					populateMAP.put(sq.next(), _userVO.getHospitalCode());
					populateMAP.put(sq.next(), _userVO.getUserSeatId());
					populateMAP.put(sq.next(), "Dept Unit Code");    // To be entered
					populateMAP.put(sq.next(), "Dept Code");		// To be entered
					populateMAP.put(sq.next(), fb.getSectionName());
					populateMAP.put(sq.next(), _userVO.getSeatId());
					populateMAP.put(sq.next(), OpdConfig.GNUM_IS_VALID);
					populateMAP.put(sq.next(), "sysdate" );
					populateMAP.put(sq.next(), "sysdate");
					populateMAP.put(sq.next(), _userVO.getSeatId());
					
					
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
					throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
				}
			}
			
			//Added by Vasu on 05.Nov.2018
			public void savePatProfileToPostgreS(HisDAO daoObj,EHR_PatientProfileDtlVO _patProfileDtlVO, byte[] decodedData, UserVO _userVO) throws Exception
			{
				
				PreparedStatement ps = null;
				Connection conn	=	super.getTransactionContext().getConnection();
				try
				{
					if(_patProfileDtlVO.getProfileDataPdf()!=null)
					{
					 String query = "update hpmrt_pat_profile_dtl SET gbyte_profile_pdf_data=? where HRGNUM_PUK=? and hpmrnum_slno = (SELECT NVL (MAX (hpmrnum_slno),1) FROM hpmrt_pat_profile_dtl where hrgnum_puk = ?)";
				   	 ps = conn.prepareStatement(query);
				   	 InputStream iss = new ByteArrayInputStream(_patProfileDtlVO.getProfileDataPdf());	
				   	 ps.setBinaryStream(1,iss,_patProfileDtlVO.getProfileDataPdf().length);
					// ps.setBytes(1,decodedData);
				   	 ps.setString(2,_patProfileDtlVO.getPatCrNo());
				   	 ps.setString(3,_patProfileDtlVO.getPatCrNo());
				   	 ps.executeUpdate();
					}
				}
				catch(Exception e)
				   	{
				   		System.out.println("exception in Image saving transaction..."+e); 
				   		try 
				   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
				   		} 
				   		catch( SQLException se ) { System.out.println("error in try block..."+se); }
				   	}
					
			}

			//Added by Vasu on 16.April.2019
			public List<EpisodeVO> retrieveAllVisitsByEpisodeNo(PatientDetailVO voDp, UserVO _userVO) {
				int nProcedureIndex;
				HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_VisitReasonDAO");
				String pmode="4";
				String strDBErr;
				ResultSet objResSet;
				ValueObject vo[] = null;
				EpisodeVO episodeVO[] = null;
		
				try
				{
				nProcedureIndex = hisDAO_p.setProcedure("{call pkg_ehr_view.proc_pat_follow_up_details(?,?,?,?,?,?,?,?)}");
				// Setting and Registering In and Out Parameters 
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
				hisDAO_p.setProcInValue(nProcedureIndex, "pat_cr_no", (voDp.getPatCrNo()==null) ? "":voDp.getPatCrNo(),2);
				hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code", (_userVO.getHospitalCode()==null) ? "": _userVO.getHospitalCode(),3);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_episodeCode", (voDp.getEpisodeCode()==null) ? "": voDp.getEpisodeCode(),4);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_visitNo", (voDp.getEpisodeVisitNo()==null) ? "": voDp.getEpisodeVisitNo(),5);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE, 6);
				//hisDAO_p.setProcInValue(nProcedureIndex, "p_source", source,4);
				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); // varchar
				hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8); // Cursor
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
					throw new HisDataAccessException("EHRSection_VisitReasonDAO_Create::hisDAO_p.execute" + e.getMessage());
				}
				//ValueObject vo[] = null;

				List<EpisodeVO> lstEpisodeDetail = new ArrayList<EpisodeVO>();
				try
				{
						
					vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, objResSet);
					for(ValueObject o : vo)
						lstEpisodeDetail.add((EpisodeVO)o);

				}
				catch (Exception e)
				{
					if (e.getClass() == HisRecordNotFoundException.class)
					{
						throw new HisRecordNotFoundException(e.getMessage());
					}
				else throw new HisDataAccessException("Application Execution Exception" + e);
				}
				
				return lstEpisodeDetail;

			}
			
			
			//aDDED BY PRACHI ON 1-JAN-2019
			
			/*public clinicalSectionMappingMstVO[] getSectionTemplateEssentials(String compositionType,String sectionCode,String templateCode,String MappingType,UserVO _userVO)
			
			{
				clinicalSectionMappingMstVO[] arrSectionTemplateEssentialsVO=null;
				ValueObject vo[]= null;
				String query  = "";
			    Map populateMAP =new HashMap();
			   
			    String filename=OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			    String queryKey="SELECT.EHRTS_SECTION_TEMPLATE_ESSENTIALS";
			    
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
				
				populateMAP.put(sq.next(),sectionCode);
				populateMAP.put(sq.next(),templateCode);
				populateMAP.put(sq.next(),compositionType);
				populateMAP.put(sq.next(),MappingType);
				//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

				try
			 	{
			 		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
			 		
				 	   
					if (rs.next())
					{
						rs.beforeFirst();
						vo=HelperMethods.populateVOfrmRS(clinicalSectionMappingMstVO.class, rs);
						arrSectionTemplateEssentialsVO= new clinicalSectionMappingMstVO[vo.length];
						for(int i=0;i<vo.length;i++)
							arrSectionTemplateEssentialsVO[i]= (clinicalSectionMappingMstVO) vo[i];
					}
				}
				catch(Exception e)
				{
					if(e.getClass()==HisRecordNotFoundException.class)
						throw new HisRecordNotFoundException(e.getMessage());
					else
						throw new HisDataAccessException("Application Execution Exception"+e);
				}
				return arrSectionTemplateEssentialsVO;
			}

			//added by swati sagar on date:03-06-2019
	public String getPreviewData(String sectionId,String interfaceId,UserVO _userVO)
			
			{
				clinicalSectionMappingMstVO[] arrSectionTemplateEssentialsVO=null;
				ValueObject vo[]= null;
				String query  = "";
			    Map populateMAP =new HashMap();
			    String rsData="";
			    String filename=OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
			    String queryKey="SELECT.EHRTS_SECTION_PREVIEW_DATA";
			   // String [] responseData=null;
			    String secDATA="";
			    
			    try
				{
					
					query = HelperMethodsDAO.getQuery(filename, queryKey);
					System.out.println("rs.getString(1)serfvs");
				}
				catch (Exception e)
				{
					throw new HisDataAccessException(
							"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
				log.error(query + "\n");

				System.out.println("   -------> query :: " + query);
				Sequence sq = new Sequence();
				
				populateMAP.put(sq.next(),sectionId);
				populateMAP.put(sq.next(),interfaceId);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

				try
			 	{
			 		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
			 		
			 	   
					if (rs.next())
					{
						
					
					//	secDATA=rs.getString(1)+"@"+rs.getString(2)+"@"+rs.getString(3);
						secDATA=rs.getString(3);
						System.out.println("secDATA......."+secDATA);
				}

			 	}
				catch(Exception e)
				{
					if(e.getClass()==HisRecordNotFoundException.class)
						throw new HisRecordNotFoundException(e.getMessage());
					else
						throw new HisDataAccessException("Application Execution Exception"+e);
				}
				return secDATA;
			}

			
			
			
			
			//ADDED BY PRACHI
			public List getTemplates(String sectionCode, UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();

				String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
				String queryKey = "SELECT.EHRTS_SECTION_TEMPLATES_MST";

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
				populateMAP.put(sq.next(),sectionCode);
				//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

				try
				{
				
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
					throw new HisDataAccessException("HisDataAccessException  :getDeskByType" + e);
				}
				return alRecord;
			}
			
			//Added by prachi
			//List<clinicalSectionMappingMstVO>clnclSecMapMstVO,UserVO _UserVO
			public static void create(clinicalSectionMappingMstVO clnclSecMapMstVO, UserVO _UserVO)
			{
				 int nProcedureIndex;
				 int nProcedureIndex1;
				HisDAO hisDAO_p = new HisDAO("EHR", "OpdEssentialDAO");
				String pmode="0";
				String strDBErr;
				ResultSet objResSet;
				try
				{
					System.out.println("insidedao");
				nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.SAVE_COMPOSITION_SECTION_MAPPING);
				//nProcedureIndex1 = hisDAO_p.setProcedure(EHRConfig.COMP_ENTRY_KEY);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", pmode,1);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_compTypeCode", (clnclSecMapMstVO.getCompositionType()==null) ? "":clnclSecMapMstVO.getCompositionType(),2);
				//hisDAO_p.setProcInValue(nProcedureIndex, "p_mappingCode", (clnclSecMapMstVO.getMappingType()==null) ? "": clnclSecMapMstVO.getMappingType(),3);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mappingCode",EHRConfig.COMP_ENTRY_KEY,3);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_hospitalCode",Config.SUPER_USER_HOSPITAL_CODE,4);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_deptCode", (clnclSecMapMstVO.getDeptCode()==null) ? "":clnclSecMapMstVO.getDeptCode(),5);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mapping_vnd",EHRConfig.COMP_ENTRY_KEY,6);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_clinicalSectionCode",(clnclSecMapMstVO.getClinicalSectionCodeNew()==null) ? "": clnclSecMapMstVO.getClinicalSectionCodeNew(),7);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_sectiontemplateid", (clnclSecMapMstVO.getClinicalInterfaceCode()==null) ? "": clnclSecMapMstVO.getClinicalInterfaceCode(),8);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", Config.IS_VALID_ACTIVE, 9);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_sectiontemplateorder", (clnclSecMapMstVO.getSectiontemplateorder()==null) ? "": clnclSecMapMstVO.getSectiontemplateorder(),10);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_clinicalsectionshort", (clnclSecMapMstVO.getClinicalsectionshort()==null) ? "": clnclSecMapMstVO.getClinicalsectionshort(),11);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_parentsectionshort",(clnclSecMapMstVO.getParentsectionshort()==null) ? "": clnclSecMapMstVO.getParentsectionshort(),12);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_sectiontemplatekey",(clnclSecMapMstVO.getSectiontemplatedefposition()==null) ? "":  clnclSecMapMstVO.getSectiontemplatedefposition(),13);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_sectiontitle",(clnclSecMapMstVO.getSectiontitle()==null) ? "": clnclSecMapMstVO.getSectiontitle(),14);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_templatetitle",(clnclSecMapMstVO.getTemplatetitle()==null) ? "": clnclSecMapMstVO.getTemplatetitle(),15);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_parentsectioncode", (clnclSecMapMstVO.getParentsectioncode()==null) ? "":clnclSecMapMstVO.getParentsectioncode(),16);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id",_UserVO.getSeatId(),17);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_sectiontemplatetype", (clnclSecMapMstVO.getSectiontemplatetype()==null) ? "": clnclSecMapMstVO.getSectiontemplatetype(),18);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_sectiontemplatedefposition", (clnclSecMapMstVO.getSectiontemplatedefposition()==null) ? "": clnclSecMapMstVO.getSectiontemplatedefposition(),19);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_interfacecontent", (clnclSecMapMstVO.getInterfaceContent()==null) ? "": clnclSecMapMstVO.getInterfaceContent(),20);
				hisDAO_p.setProcOutValue(nProcedureIndex,"err", 1,21); // varchar
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
					throw new HisDataAccessException("opdEssentialDAO_Create::hisDAO_p.execute" + EHRConfig.SAVE_COMPOSITION_SECTION_MAPPING
							+ ") -> " + e.getMessage());
				}
					
			}*/
			
			//Added by Vasu on 30.May.2019
			public byte[] getLatestPDFFromPostgres(PatientProfileDetailVO patProfileDtlVO)
			{
				
				PreparedStatement ps = null;
				Connection conn	=	super.getTransactionContext().getConnection();
				byte[] imgBytes = null;
				InputStream fis=null;
				try
				{
					
					 String query = "select gbyte_profile_pdf_data from hpmrt_pat_profile_dtl where hpmrnum_profile_id = ? and hrgnum_puk = ? and hrgnum_episode_code = ? and hrgnum_visit_no = ? and hpmrnum_slno = (select max(hpmrnum_slno) from hpmrt_pat_profile_dtl where hpmrnum_profile_id = ? and hrgnum_puk = ? and hrgnum_episode_code = ? and hrgnum_visit_no = ?)";
					 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				   	 
					 ps.setString(1,patProfileDtlVO.getProfileId());
					 ps.setString(2,patProfileDtlVO.getPatCrNo());
					 ps.setString(3, patProfileDtlVO.getEpisodeCode());
				   	 ps.setString(4, patProfileDtlVO.getEpisodeVisitNo());
				   	 ps.setString(5,patProfileDtlVO.getProfileId());
					 ps.setString(6,patProfileDtlVO.getPatCrNo());
					 ps.setString(7, patProfileDtlVO.getEpisodeCode());
				   	 ps.setString(8, patProfileDtlVO.getEpisodeVisitNo());
				   	 
					 ResultSet resultSet = ps.executeQuery();
					 
					 if(resultSet!=null && resultSet.next())
						{
						 //Base64 codec = new Base64();
						 //imgBytes = Base64.decodeBase64(resultSet.getBytes(1));
						  imgBytes = resultSet.getBytes(1);
						  if(imgBytes!=null)
						  fis = new ByteArrayInputStream(imgBytes); 
							
						  
						}
				}
				catch(Exception e)
				   	{
				   		System.out.println("exception in Image saving transaction..."+e); 
				   		try 
				   		{  ps.close(); ps =null;
				   		//throw new Exception("error in saving image to postgres...terminated unsuccesfully");
				   		} 
				   		catch( SQLException se ) { System.out.println("error in try block..."+se); }
				   	}
				return imgBytes;
			}

}//end class
