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
import hisglobal.vo.DynamicVisitSummaryVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DynamicVisitSummaryDtlDAO extends DataAccessObject implements DynamicVisitSummaryDtlDAOi
{
	Logger log;

	public DynamicVisitSummaryDtlDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// Getting Map of Dynaically Fetched Details of Visit
	public LinkedHashMap<String, List<List<Object>>> getDynamicVisitDetail(EpisodeVO _epiVO, List<String> _lstMenuIds, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.QUERY_BYMENUID.FROMPROFILE_TYPETABMAPPING";
		Sequence sq = new Sequence();

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
			String strDeskMenus = "";
			for(String mnu : _lstMenuIds)
				strDeskMenus += mnu +",";
			if(!strDeskMenus.equals(""))	strDeskMenus = strDeskMenus.substring(0,strDeskMenus.length()-1);
			query = query.replace("#", strDeskMenus);
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //changedby: NehaRajgariya Date:7Sept2016
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.PROFILE_TYPE_OPD_VISIT_PROFILE);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("DynamicVisitSummaryDtlDAO.populateMAP::"+e);
        }

		ValueObject vo[] = null;
		List<DynamicVisitSummaryVO> lstQry = new ArrayList<DynamicVisitSummaryVO>();
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Visit Detail Found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(DynamicVisitSummaryVO.class, rs);
			for (ValueObject v : vo)	lstQry.add((DynamicVisitSummaryVO)v);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		/*String header = "";
		String query = "";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String headerKey = "";
		String queryKey = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List<DynamicVisitSummaryVO> lstQry = new ArrayList<DynamicVisitSummaryVO>();
		try
		{
			for(String mnu : _lstMenuIds)
			{
				headerKey = "DYNAMIC_VISIT_SUMMARY.HEADER."+mnu;
				queryKey = "DYNAMIC_VISIT_SUMMARY.QUERY."+mnu;
				try
				{
					header = HelperMethodsDAO.getQuery(filename, headerKey);
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					header = "";
					query = "";
				}
				if(header!=null && !header.equals(""))
				{
					DynamicVisitSummaryVO vo = new DynamicVisitSummaryVO();
					vo.setDeskMenuId(mnu);
					vo.setHeader(header);
					vo.setQuery(query);
					lstQry.add(vo);
				}
			}
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("DynamicVisitSummaryDtlDAO.getting.header.query::"+e);
        }*/

        LinkedHashMap<String,List<List<Object>>>  mpVisitDtl = new LinkedHashMap<String, List<List<Object>>>();
		try
		{
			for(DynamicVisitSummaryVO dynaVO : lstQry)
			{
				query = dynaVO.getQuery();
				sq = new Sequence();
				populateMAP = new HashMap();
				populateMAP.put(sq.next(), _epiVO.getPatCrNo());
				populateMAP.put(sq.next(), _epiVO.getEpisodeCode());
				populateMAP.put(sq.next(), _epiVO.getEpisodeVisitNo());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), dynaVO.getDeskMenuId());
				
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())	continue;
				
				mpVisitDtl.put(dynaVO.getHeader(), getDataLists(rs));
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

		return mpVisitDtl;
	}
	
	private List<List<Object>> getDataLists(ResultSet _rs) throws Exception
	{
		List<List<Object>> lstData = new ArrayList<List<Object>>();
		
		try
		{
			ResultSetMetaData rsMetaData = _rs.getMetaData();
			int rsColCount = rsMetaData.getColumnCount();
			
			// Adding Column Name
			/*
			 * Column Name Stategy
			 * If Start with _		DO NOT DISPLAY
			 * If Start with $		DISPLAY IN REMARK AT BOTTOM
			 * If Start with #	then at the end there is size of the Column in percentage separated by _ 
			 */
			List<Object> lstCoumnNames = new ArrayList<Object>();
			for(int i=1; i<=rsColCount; i++)
			{
				if(rsMetaData.getColumnLabel(i).charAt(0) != '_')
				{
					DynamicVisitSummaryVO.ColumnLogic colObj = new DynamicVisitSummaryVO.ColumnLogic();
					colObj.setRemarkable(false);

					String tempWidth = null;
					String column = (rsMetaData.getColumnLabel(i)==null)?" ":rsMetaData.getColumnLabel(i);
					if(column.charAt(0) == '#')
					{
						if(column.lastIndexOf("_")!=-1) 
						{
							if(column.length()>(column.lastIndexOf("_")+1))
								tempWidth = column.substring(column.lastIndexOf("_")+1) +"%";
							column = column.substring(1,column.lastIndexOf("_"));
						}
					}
					if(column.charAt(0) == '$')
					{
						colObj.setRemarkable(true);
						column = column.substring(1);
					}
					
					colObj.setColumn(column);
					colObj.setWidth(tempWidth);					
					lstCoumnNames.add(colObj);
				}
			}
			lstData.add(lstCoumnNames);
			
			
			
			// Adding Data
			_rs.beforeFirst();
			for (int rsCounter=0; _rs.next(); rsCounter++)
			{
				List<Object> lstCoumnData = new ArrayList<Object>();
				for (int i=1; i<=rsColCount; i++)
				{
					if(rsMetaData.getColumnLabel(i).charAt(0) != '_')
						lstCoumnData.add(_rs.getString(rsMetaData.getColumnName(i)));
				}
				lstData.add(lstCoumnData);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		return lstData;
	}	
}
