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

public class ChartUnitMapppingDAO extends DataAccessObject implements ChartUnitMapppingDAOi
{
	Logger log;

	public ChartUnitMapppingDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	/**
	 * Get Chart Category & Unit Wise Chart List
	 * 
	 * @param _chartCategory Chart Category
	 * @param _userVO User Detail
	 * @return List of Chart Detail
	 */
	public List<ChartMasterVO> getChartsByCategoryByUnit(String _chartCategory, String _deptUnitCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.CHARTS_DETAIL_BY_CATEGORY.HGBT_CHART_UNIT_MAPPING";

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
				
			populateMAP.put(sq.next(), _deptUnitCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ChartUnitMapppingDAO.populateMAP::" + e);
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
			throw new HisDataAccessException("HisDataAccessException : getChartsByCategoryByUnit" + e);
		}
		return lstCharts;
	}
}
