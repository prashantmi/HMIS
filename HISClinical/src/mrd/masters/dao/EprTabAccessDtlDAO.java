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
import hisglobal.vo.EprTabAccessDtlVO;
import hisglobal.vo.UserVO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

public class EprTabAccessDtlDAO extends DataAccessObject implements EprTabAccessDtlDAOi
{
	public EprTabAccessDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	/**
	 * get the list of the department unit/seatid for the specified tab. 
	 */
	public List selectTabAccessDetail(EprTabAccessDtlVO eprTabAccessDtlVO,UserVO userVO){
	
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List<EprTabAccessDtlVO> eprTabAccessDtlVOList=new ArrayList<EprTabAccessDtlVO>();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey ="SELECT.TAB_ACCESS_POLICY.HPMRT_EPR_TABACCESS_MST";
	
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
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getDepartmentUnitCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getPolicyType());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getUserLevel());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EprTabAccessDtlDAO.populateMAP::" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			while (rs.next())
			{
				eprTabAccessDtlVO=new EprTabAccessDtlVO();
				HelperMethods.populateVOfrmRS(eprTabAccessDtlVO,rs);
				eprTabAccessDtlVOList.add(eprTabAccessDtlVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	
	
	return eprTabAccessDtlVOList;

	}
	
	
	public List getTabAccessDtlByUnitCodePolicyType(EprTabAccessDtlVO eprTabAccessDtlVO,UserVO userVO){
		
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List<EprTabAccessDtlVO> eprTabAccessDtlVOList=new ArrayList<EprTabAccessDtlVO>();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey ="SELECT.BY_UNIT_AND_POLICY_TYPE.HPMRT_EPR_TABACCESS_MST";
		
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
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getDepartmentUnitCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getPolicyType());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getToDepartmentUnitCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EprTabAccessDtlDAO.populateMAP::" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			while (rs.next())
			{
				eprTabAccessDtlVO=new EprTabAccessDtlVO();
				HelperMethods.populateVOfrmRS(eprTabAccessDtlVO,rs);
				eprTabAccessDtlVOList.add(eprTabAccessDtlVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		return eprTabAccessDtlVOList;
		
	}
	
	
	public void create(EprTabAccessDtlVO eprTabAccessDtlVO,UserVO userVO){
		
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey ="INSERT.HPMRT_EPR_TABACCESS_MST";
		
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
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getDepartmentUnitCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getTabId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getPolicyType());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getToDepartmentUnitCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getDepartmentUnitCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getTabId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getPolicyType());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getToDepartmentUnitCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getAccessType());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getUserLevel());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getSeatId());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getEffectiveTo());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EprTabAccessDtlDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	}
	
	public void updateIsValid(EprTabAccessDtlVO eprTabAccessDtlVO, UserVO userVO){
		
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey ="UPDATE.IS_VALID.HPMRT_EPR_TABACCESS_MST";
		
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
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),userVO.getSeatId());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getDepartmentUnitCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getTabId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getPolicyType());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getToDepartmentUnitCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getDepartmentUnitCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getTabId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getPolicyType());
			populateMAP.put(sq.next(), eprTabAccessDtlVO.getToDepartmentUnitCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EprTabAccessDtlDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	}
	
	
}

	