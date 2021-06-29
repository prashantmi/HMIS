package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.ChartCellData;
import hisglobal.utility.generictemplate.ChartColumnHead;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ChartDetailDAO extends DataAccessObject implements ChartDetailDAOi
{
	Logger log;

	public ChartDetailDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// Getting Map of Dynaically Fetched Details of Chart Data of Template Based Parameters in OPD
	public LinkedHashMap<String, Map<String,ChartCellData>> getDynamicChartDataTemplateOPD(PatientDetailVO _patDtlVO, String _strParaIds, String _criteria, String _fromDate, String _toDate, UserVO _userVO)
	{
		String query = "";
		String criteria = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.DYNAMIC_CHART_DATA.TEMPLATE.OPD";
		String criteriaKey = "SELECT.DYNAMIC_CHART_DATA.TEMPLATE.OPD.CRITERIA";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			criteria = HelperMethodsDAO.getQuery(filename, criteriaKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			query = query.replace("@", _strParaIds);
			String age = Integer.valueOf(_patDtlVO.getPatAge().substring(0,_patDtlVO.getPatAge().indexOf(" "))).toString();
			
			populateMAP.put(sq.next(), _patDtlVO.getPatGenderType());
			populateMAP.put(sq.next(), age);
			populateMAP.put(sq.next(), _patDtlVO.getPatGenderType());
			populateMAP.put(sq.next(), age);
			populateMAP.put(sq.next(), _patDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			if(_criteria.equals(OpdConfig.CHOICE_EPISODE_WISE))
				query = query.replace("#", "");
			else
			{
				query = query.replace("#", criteria);
				populateMAP.put(sq.next(), _fromDate);
				populateMAP.put(sq.next(), _toDate);
			}
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ChartDetailDAO.populateMAP::"+e);
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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		LinkedHashMap<String, Map<String,ChartCellData>>  mpChartData = new LinkedHashMap<String, Map<String,ChartCellData>>();
		try
		{			
			if (rs.next())
			{
				ResultSetMetaData rsMetaData = rs.getMetaData();
				rs.beforeFirst();
				while(rs.next())
					setDataCellRow(rs, rsMetaData, mpChartData);
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
		return mpChartData;
	}
	
	private void setDataCellRow(ResultSet _rs, ResultSetMetaData _rsMetaData, LinkedHashMap<String, Map<String,ChartCellData>> _mpChartData) throws Exception
	{
		try
		{			
			int rsColCount = _rsMetaData.getColumnCount();
			
			// Column Count should be at least 4 as follows
			//-- First Column 'Date', Second Column 'Para Id', Third Column 'Para Value' and the Last Column 'Flag Logic'
			// 	-- the columns between third & the last (if any) are Extra Data Column to display
			
			if(rsColCount<4)	return;
			
			// Creating Cell Data
			ChartCellData objCell = new ChartCellData();
			objCell.setEntryDate(_rs.getString(1));
			objCell.setId(_rs.getString(2));
			objCell.setValue(_rs.getString(3));
			objCell.setFlag(_rs.getString(rsColCount));
			
			// Searching for the Place for Cell Data Entry 
			Map<String, ChartCellData> mpData = _mpChartData.get(objCell.getEntryDate());
			if(mpData==null)
			{
				mpData = new HashMap<String, ChartCellData>();
				_mpChartData.put(objCell.getEntryDate(), mpData);
			}
			mpData.put(objCell.getId(),objCell);

			// Adding Extra Data --------
			/*for (int rsCounter=0; _rs.next(); rsCounter++)
			{
			}*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}	

	// Getting Map of Dynaically Fetched Details of Chart Data of Template Based Parameters in IPD
	public LinkedHashMap<String, Map<String,ChartCellData>> getDynamicChartDataTemplateIPD(PatientDetailVO _patDtlVO, String _strParaIds, String _criteria, String _fromDate, String _toDate, UserVO _userVO)
	{
		String query = "";
		String criteria = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.DYNAMIC_CHART_DATA.TEMPLATE.IPD";
		String criteriaKey = "SELECT.DYNAMIC_CHART_DATA.TEMPLATE.IPD.CRITERIA";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			criteria = HelperMethodsDAO.getQuery(filename, criteriaKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			query = query.replace("@", _strParaIds);
			String age = Integer.valueOf(_patDtlVO.getPatAge().substring(0,_patDtlVO.getPatAge().indexOf(" "))).toString();
			
			populateMAP.put(sq.next(), _patDtlVO.getPatGenderType());
			populateMAP.put(sq.next(), age);
			populateMAP.put(sq.next(), _patDtlVO.getPatGenderType());
			populateMAP.put(sq.next(), age);
			populateMAP.put(sq.next(), _patDtlVO.getPatAdmNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			if(_criteria.equals(OpdConfig.CHOICE_EPISODE_WISE))
				query = query.replace("#", "");
			else
			{
				query = query.replace("#", criteria);
				populateMAP.put(sq.next(), _fromDate);
				populateMAP.put(sq.next(), _toDate);
			}
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ChartDetailDAO.populateMAP::"+e);
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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		LinkedHashMap<String, Map<String,ChartCellData>>  mpChartData = new LinkedHashMap<String, Map<String,ChartCellData>>();
		try
		{			
			if (rs.next())
			{
				ResultSetMetaData rsMetaData = rs.getMetaData();
				rs.beforeFirst();
				while(rs.next())
					setDataCellRow(rs, rsMetaData, mpChartData);
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
		return mpChartData;
	}

	// Getting Map of Dynaically Fetched Details of Chart Data of Investigation Parameters
	public LinkedHashMap<String, Map<String,ChartCellData>> getDynamicChartDataInvestigation(LinkedHashMap<String, Map<String,ChartCellData>> _mp, PatientDetailVO _patDtlVO, String _strParaIds, String _criteria, String _fromDate, String _toDate, UserVO _userVO)
	{
		String query = "";
		String criteria = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.DYNAMIC_CHART_DATA.INV";
		String criteriaKey = "SELECT.DYNAMIC_CHART_DATA.INV.CRITERIA";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			criteria = HelperMethodsDAO.getQuery(filename, criteriaKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			query = query.replace("@", _strParaIds);
			
			populateMAP.put(sq.next(), _patDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDtlVO.getEpisodeCode());
			if(_criteria.equals(OpdConfig.CHOICE_EPISODE_WISE))
				query = query.replace("#", "");
			else
			{
				query = query.replace("#", criteria);
				populateMAP.put(sq.next(), _fromDate);
				populateMAP.put(sq.next(), _toDate);
			}
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ChartDetailDAO.populateMAP::"+e);
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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		LinkedHashMap<String, Map<String,ChartCellData>>  mpChartData = null;
		if(_mp==null)
			mpChartData = new LinkedHashMap<String, Map<String,ChartCellData>>();
		else
			mpChartData = _mp;
		try
		{			
			if (rs.next())
			{
				ResultSetMetaData rsMetaData = rs.getMetaData();
				rs.beforeFirst();
				while(rs.next())
					setDataCellRow(rs, rsMetaData, mpChartData);
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
		return mpChartData;
	}

	// Getting Map of Dynaically Fetched Details of Chart Data of Intake Output Parameters
	public LinkedHashMap<String, Map<String,ChartCellData>> getDynamicChartDataIntakeOutput(LinkedHashMap<String, Map<String,ChartCellData>> _mp, PatientDetailVO _patDtlVO, String _strParaIds, String _criteria, String _fromDate, String _toDate, UserVO _userVO)
	{
		String query = "";
		String criteria = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.DYNAMIC_CHART_DATA.INOUT";
		String criteriaKey = "SELECT.DYNAMIC_CHART_DATA.INOUT.CRITERIA";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			criteria = HelperMethodsDAO.getQuery(filename, criteriaKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			query = query.replace("@", _strParaIds);
			
			populateMAP.put(sq.next(), _patDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDtlVO.getEpisodeCode());
			if(_criteria.equals(OpdConfig.CHOICE_EPISODE_WISE))
				query = query.replace("#", "");
			else
			{
				query = query.replace("#", criteria);
				populateMAP.put(sq.next(), _fromDate);
				populateMAP.put(sq.next(), _toDate);
			}
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ChartDetailDAO.populateMAP::"+e);
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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		LinkedHashMap<String, Map<String,ChartCellData>>  mpChartData = null;
		if(_mp==null)
			mpChartData = new LinkedHashMap<String, Map<String,ChartCellData>>();
		else
			mpChartData = _mp;
		try
		{			
			if (rs.next())
			{
				ResultSetMetaData rsMetaData = rs.getMetaData();
				rs.beforeFirst();
				while(rs.next())
					setDataCellRow(rs, rsMetaData, mpChartData);
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
		return mpChartData;
	}

	// Getting Map of Dynaically Fetched Details of Chart Data of Investigation Parameters
	public Map<Object, Object> getDynamicChartRowWiseData(String _query, PatientDetailVO _patDtlVO, String _fromDate, String _toDate, UserVO _userVO)
	{
		String query = _query;
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _patDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _fromDate);
			populateMAP.put(sq.next(), _toDate);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ChartDetailDAO.populateMAP::"+e);
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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		LinkedHashMap<String, ChartColumnHead> mpColHead = new LinkedHashMap<String, ChartColumnHead>();
		LinkedHashMap<String, Map<String,ChartCellData>>  mpChartData = new LinkedHashMap<String, Map<String,ChartCellData>>();
		Map<Object,Object> mp = new HashMap<Object, Object>();
		mp.put("Header", mpColHead);
		mp.put("Data", mpChartData);
		try
		{			
			if (rs.next())
			{
				ResultSetMetaData rsMetaData = rs.getMetaData();
				setDataRowWise(rs, rsMetaData, mpColHead, mpChartData);
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
		return mp;
	}

	private void setDataRowWise(ResultSet _rs, ResultSetMetaData _rsMetaData, LinkedHashMap<String, ChartColumnHead> _mpColHead, LinkedHashMap<String, Map<String,ChartCellData>> _mpChartData) throws Exception
	{
		try
		{			
			int rsColCount = _rsMetaData.getColumnCount();
			
			// Column Count should be at least 2 as follows
			//-- First Column 'Date', and at least one column for value
			// 	-- the columns between third & the last (if any) are Extra Data Column to display
			
			if(rsColCount<2)	return;
			
			// Creating Cell Header
			for (int i = 1; i <= rsColCount; i++)
			{
				String strColLabel = _rsMetaData.getColumnLabel(i);
				ChartColumnHead objHead = new ChartColumnHead();
				objHead.setId(strColLabel);
				objHead.setName(strColLabel);
				objHead.setOrder(Integer.toString(i));
				_mpColHead.put(objHead.getId(), objHead);
			}

			// Creating Cell Data
			_rs.beforeFirst();
			int rowCount=0;
			while(_rs.next())
			{
				rowCount++;
				for (int i = 1; i <= rsColCount; i++)
				{
					String strColLabel = _rsMetaData.getColumnLabel(i);
					ChartCellData objCell = new ChartCellData();
					objCell.setEntryDate(Integer.toString(rowCount));
					objCell.setId(strColLabel);
					objCell.setValue(_rs.getString(i));
					objCell.setFlag("-1");
					Map<String, ChartCellData> mpData = _mpChartData.get(objCell.getEntryDate());
					if(mpData==null)
					{
						mpData = new HashMap<String, ChartCellData>();
						_mpChartData.put(objCell.getEntryDate(), mpData);
					}
					mpData.put(objCell.getId(),objCell);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}	
}
