package new_investigation.reports.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.reports.dao.InvestigationStatisticalReportDAO;
import new_investigation.vo.InvestigationStatisticalReportVO;

public class InvestigationStatisticalReportBO {
	
	public Map fetchDept(InvestigationStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstdept=new ArrayList();
		try
		{
			tx.begin();
			InvestigationStatisticalReportDAO reqlist_DAO = new InvestigationStatisticalReportDAO(tx);
			lstdept=reqlist_DAO.getDept( _UserVO);
			mp.put(InvestigationConfig.DEPART_COMBO,lstdept);
			mp.put(InvestigationConfig.LIST_TEST_COMBO,null);
			mp.put(InvestigationConfig.LIST_LAB_COMBO,null);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO,null);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	
	
	
	
	
	public Map fetchLab(InvestigationStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLab=new ArrayList();
		try
		{
			tx.begin();
			InvestigationStatisticalReportDAO reqlist_DAO = new InvestigationStatisticalReportDAO(tx);
			lstLab=reqlist_DAO.getLab(reqList_VO, _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO,lstLab);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}

	
	public Map fetchTest(InvestigationStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstTest=new ArrayList();
		List lstSample=new ArrayList();
		try
		{
			tx.begin();
			InvestigationStatisticalReportDAO reqlist_DAO = new InvestigationStatisticalReportDAO(tx);
			lstTest=reqlist_DAO.getTest(reqList_VO, _UserVO);
			lstSample=reqlist_DAO.getSample(reqList_VO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO,lstTest);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO,lstSample);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			//throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	
	
	public Map fetchLabOnLoad(InvestigationStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLab=new ArrayList();
		try
		{
			tx.begin();
			InvestigationStatisticalReportDAO reqlist_DAO = new InvestigationStatisticalReportDAO(tx);
			lstLab=reqlist_DAO.getTest(reqList_VO, _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO,lstLab);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			//throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	
	
	
	public Map fetchTestOnLoad(InvestigationStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstTest=new ArrayList();
		try
		{
			tx.begin();
			InvestigationStatisticalReportDAO reqlist_DAO = new InvestigationStatisticalReportDAO(tx);
			lstTest=reqlist_DAO.getTest(reqList_VO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO,lstTest);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			//throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	
	public Map fetchSampleOnLoad(InvestigationStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstSample=new ArrayList();
		try
		{
			tx.begin();
			InvestigationStatisticalReportDAO reqlist_DAO = new InvestigationStatisticalReportDAO(tx);
			lstSample=reqlist_DAO.getSample(reqList_VO, _UserVO);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO,lstSample);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			//throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}
	

	
	public Map fetchSample(InvestigationStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstsample=new ArrayList();
		try
		{
			tx.begin();
			InvestigationStatisticalReportDAO reqlist_DAO = new InvestigationStatisticalReportDAO(tx);
			lstsample=reqlist_DAO.getSample(reqList_VO, _UserVO);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO,lstsample);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}

}
