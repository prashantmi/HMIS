package opd.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

public class ChartParameterMappingDAO extends DataAccessObject implements ChartParameterMappingDAOi
{
	public ChartParameterMappingDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	public List<ChartMasterVO> getAllChart(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT_ALL_CHART_NAME.HGBT_CHART_MST";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), OpdConfig.CHART_GENERATION_TYPE_COLUMN_WISE);

		ValueObject[] vo = null;
		List<ChartMasterVO> lstCharts = new ArrayList<ChartMasterVO>();
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Chart Found for Mapping");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(ChartMasterVO.class, rs);
				for (ValueObject v : vo)
					lstCharts.add((ChartMasterVO)v);
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
		return lstCharts;
	}

	public List getParaByChartId(String _chartId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.PARA_NAME.HGBT_CHART_PARA_MAPPING_MST";
		List paraList = new ArrayList();
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
			populateMAP.put(sq.next(), _chartId);
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
			if (rs.next())
			{
				paraList = HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return paraList;
	}

	public List getParaNotAdded(String chartId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.PARA_NAME_NOT_ADDED.HGBT_CHART_PARA_MAPPING_MST";
		List paraNotAddedList = new ArrayList();;
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
			populateMAP.put(sq.next(), GenericTemplateConfig.TEMPLATE_GROUP_CLINICAL);
			populateMAP.put(sq.next(), chartId);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				paraNotAddedList = HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return paraNotAddedList;
	}

	public void update(String _chartId, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.DELETE.HGBT_CHART_PARA_MST";

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
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _chartId);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
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
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	public void create(ChartParameterMappingVO _chartParameterMappingVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_CHART_PARA_MST";

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
			populateMAP.put(sq.next(), _chartParameterMappingVO.getPara());
			populateMAP.put(sq.next(), _chartParameterMappingVO.getChartName());

			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _chartParameterMappingVO.getChartName());

			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _chartParameterMappingVO.getParaType());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _chartParameterMappingVO.getDisplayOrder());
			// populateMAP.put(sq.next(), _userVO.getHospitalCode());
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
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	// Getting List of Parameters based on Parameter Type
	public List<Entry> getParametersByParaType(String _paraType, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.PARA_LIST.PARA_TYPE." + _paraType;
		List paraNotAddedList = new ArrayList();;
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
			if (_paraType.equalsIgnoreCase(OpdConfig.CHART_PARAMETER_TYPE_CLINICAL))
			{
				
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), GenericTemplateConfig.TEMPLATE_GROUP_CLINICAL);
			}
			else if (_paraType.equalsIgnoreCase(OpdConfig.CHART_PARAMETER_TYPE_INVESTIGATION))
			{
				//populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			else if (_paraType.equalsIgnoreCase(OpdConfig.CHART_PARAMETER_TYPE_INTAKE_OUTPUT))
			{
				populateMAP.put(sq.next(), OpdConfig.INTAKEOUT_MODE_INTAKE);
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), OpdConfig.INTAKEOUT_MODE_INTAKE);
				populateMAP.put(sq.next(), OpdConfig.INTAKEOUT_MODE_BOTH);
				
				populateMAP.put(sq.next(), OpdConfig.INTAKEOUT_MODE_OUTTAKE);
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), OpdConfig.INTAKEOUT_MODE_OUTTAKE);
				populateMAP.put(sq.next(), OpdConfig.INTAKEOUT_MODE_BOTH);
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				paraNotAddedList = HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return paraNotAddedList;
	}
}
