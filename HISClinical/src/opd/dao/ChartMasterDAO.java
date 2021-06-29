package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ChartMasterDAO extends DataAccessObject implements ChartMasterDAOi
{
	Logger log;

	public ChartMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// Getting Chart Parameters List By Chart ID
	public List<ChartParameterMappingVO> getChartTempParas(String _chartID, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.CHART_PARA_BY_TYPE.HGBT_CHART_PARA_MST";

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
			populateMAP.put(sq.next(), OpdConfig.CHART_PARAMETER_TYPE_CLINICAL);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.CHART_PARAMETER_TYPE_INVESTIGATION);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.CHART_PARAMETER_TYPE_INTAKE_OUTPUT);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.INTAKEOUT_MODE_INTAKE);
			populateMAP.put(sq.next(), _chartID);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("ChartMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<ChartParameterMappingVO> lstChartParas = new ArrayList<ChartParameterMappingVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(ChartParameterMappingVO.class,rs);
				for(ValueObject v :vo)
					lstChartParas.add((ChartParameterMappingVO)v);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException : getChartsByCategory" + e);
		}
		return lstChartParas;
	}

	// Getting Chart Parameters List With Range By Chart ID
	public List<ChartParameterMappingVO> getChartTempParasWithRange(PatientDetailVO _patDtlVO, String _chartID, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.CHART_PARA_BY_TYPE.WITH_RANGE.HGBT_CHART_PARA_MST";

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
			String age = Integer.valueOf(_patDtlVO.getPatAge().substring(0,_patDtlVO.getPatAge().indexOf(" "))).toString();

			populateMAP.put(sq.next(), OpdConfig.CHART_PARAMETER_TYPE_CLINICAL);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patDtlVO.getPatGenderType());
			populateMAP.put(sq.next(), age);
			populateMAP.put(sq.next(), age);
			populateMAP.put(sq.next(), _patDtlVO.getPatGenderType());
			populateMAP.put(sq.next(), age);
			populateMAP.put(sq.next(), OpdConfig.CHART_PARAMETER_TYPE_INVESTIGATION);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.CHART_PARAMETER_TYPE_INTAKE_OUTPUT);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.INTAKEOUT_MODE_INTAKE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _chartID);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ChartMasterDAO.populateMAP::" + e);
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
		List<ChartParameterMappingVO> lstChartParas = new ArrayList<ChartParameterMappingVO>();
		ValueObject[] vo = {};
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(ChartParameterMappingVO.class,rs);
				for(ValueObject v :vo)
					lstChartParas.add((ChartParameterMappingVO)v);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getChartsByCategory" + e);
		}
		return lstChartParas;
	}

	public void create(ChartMasterVO _chartMasterVO,UserVO _userVO) {

		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_CHART_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		Sequence sq = new Sequence();
		try {
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _chartMasterVO.getChartName());
			populateMAP.put(sq.next(), Config.SL_NO);
			populateMAP.put(sq.next(), _chartMasterVO.getChartDescription());
			populateMAP.put(sq.next(), _chartMasterVO.getChartCategory());
			populateMAP.put(sq.next(), _chartMasterVO.getGenerationType());
			populateMAP.put(sq.next(), _chartMasterVO.getBodyQuery());
			populateMAP.put(sq.next(), _chartMasterVO.getFooterQuery());
			populateMAP.put(sq.next(), _chartMasterVO.getIsGraph());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ChartMasterDAO.populateMAP::" + e);
		}

		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.executeUpdate"
					+ e);
		}

	}
	
	public boolean checkDuplicate(ChartMasterVO _chartMasterVO,UserVO _userVO) 
	{
		
		ResultSet rs;
		boolean flag=false;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_SAVE.HGBT_CHART_MST";
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			populateMAP.put(sq.next(), _chartMasterVO.getChartName());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ChartMasterDAO.populateMAP::" + e);
		}
		try {
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
	    	rs.next();
	    	if(rs.getInt(1)==0)
	    	{
	    		flag=true;
	    	}
	    	else
	    	{
	    		flag=false;
	    	}
	        
	        return flag;
	    } 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
			}
		}
	}
		
	public ChartMasterVO fetchRecord(ChartMasterVO _chartMasterVO,	UserVO _userVO) {
		String query = "";

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HGBT_CHART_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			populateMAP.put(sq.next(), _chartMasterVO.getChartId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _chartMasterVO.getSlNo());
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"UserDeskMenuMasterDAO.populateMAP::" + e);
		}

		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(super
					.getTransactionContext().getConnection(), query,
					populateMAP);
			if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				HelperMethods.populateVOfrmRS(_chartMasterVO, rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}

		return _chartMasterVO;
	}
	
	public void modify(ChartMasterVO _chartMasterVO,	UserVO _userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_CHART_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		try {
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _chartMasterVO.getChartId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _chartMasterVO.getSlNo());

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileTypeMstDAO.populateMAP::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"
					+ e);
		}

	}
	
	public void modifyInsert(ChartMasterVO _chartMasterVO,	UserVO _userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "MODIFY_INSERT.HGBT_CHART_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			populateMAP.put(sq.next(), _chartMasterVO.getChartId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _chartMasterVO.getChartId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _chartMasterVO.getChartName());
			populateMAP.put(sq.next(), _chartMasterVO.getChartDescription());
			populateMAP.put(sq.next(), _chartMasterVO.getIsGraph());
			populateMAP.put(sq.next(), _chartMasterVO.getGenerationType());
			populateMAP.put(sq.next(), _chartMasterVO.getChartCategory());
			populateMAP.put(sq.next(), _chartMasterVO.getBodyQuery());
			populateMAP.put(sq.next(), _chartMasterVO.getFooterQuery());
			populateMAP.put(sq.next(), _chartMasterVO.getIsValid());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			
					
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileTypeDAO.populateMAP::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"
					+ e);
		}

	}

	public boolean checkDuplicateBeforeModifySave(ChartMasterVO _chartMasterVO,	UserVO _userVO) {
		
		ResultSet rs;
		boolean flag=false;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_MODIFY_SAVE.HGBT_CHART_MST";
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		try {
			populateMAP.put(sq.next(), _chartMasterVO.getChartName());
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),_chartMasterVO.getChartId());
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ChartMasterDAO.populateMAP::" + e);
		}
		try {
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
	    	rs.next();
	    
	    	if(rs.getInt(1)==0)
	    	{
	    		flag=true;
	    	}
	    	else
	    	{
	    		flag=false;
	    	}
	        
	        return flag;
	    } 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
			}
		}
	}
		
	
}
