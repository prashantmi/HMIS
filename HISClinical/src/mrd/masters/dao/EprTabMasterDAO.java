package mrd.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EprTabMasterVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

public class EprTabMasterDAO extends DataAccessObject implements EprTabMasterDAOi
{
	public EprTabMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	/**
	 * get the list of the tab which are default and 
	 * the list of the tab based on the user seat id
	 */
	public List selectTab(String tabType,UserVO userVO){
	
		String query = "";
		ResultSet rs;
		EprTabMasterVO eprTabMasterVO=new EprTabMasterVO();
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List<EprTabMasterVO> tabList=new ArrayList<EprTabMasterVO>();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey ="SELECT.HPMRT_EPR_TAB_MST";
	
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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), MrdConfig.IS_DEFAULT_EPR_TAB_YES);
			populateMAP.put(sq.next(), tabType);
			populateMAP.put(sq.next(), MrdConfig.EPR_TAB_TYPE_OPD_AND_IPD);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), tabType);
			populateMAP.put(sq.next(), MrdConfig.EPR_TAB_TYPE_OPD_AND_IPD);
			populateMAP.put(sq.next(), userVO.getSeatId());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EprTabMasterDAO.populateMAP::" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			while (rs.next())
			{
				eprTabMasterVO=new EprTabMasterVO();
				HelperMethods.populateVOfrmRS(eprTabMasterVO,rs);
				tabList.add(eprTabMasterVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	
	
	return tabList;

	}
}

	