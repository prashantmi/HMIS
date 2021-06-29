package opd.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.ChartUnitMapppingVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class ChartUnitMappingMstDAO extends DataAccessObject implements ChartUnitMappingMstDAOi{
	Logger log;
	
	public ChartUnitMappingMstDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}


	
	public void create(ChartUnitMapppingVO _unitChartListMasterVO,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_CHART_UNIT_MAPPING";
		
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
			populateMAP.put(sq.next(),_unitChartListMasterVO.getDeptUnitCode());
			populateMAP.put(sq.next(),_unitChartListMasterVO.getChartId());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_unitChartListMasterVO.getIsDefault());
			//sereal no
			populateMAP.put(sq.next(),_unitChartListMasterVO.getDeptUnitCode());
			populateMAP.put(sq.next(),_unitChartListMasterVO.getChartId());
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			
			populateMAP.put(sq.next(),_userVO.getSeatId());
			//entryDate
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),_unitChartListMasterVO.getLastModifiedSeatID());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("UnitImageMasterDAO.populateMAP::" + e);
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
	
	public void update(String _unitCode,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_CHART_UNIT_MAPPING";
		
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
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),_unitCode );
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("UnitImageMasterDAO.populateMAP::" + e);
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
}
