package new_investigation.reports.bo;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.masters.dao.BookMarkMstDAO;
import new_investigation.masters.dao.CannedMstDAO;
import new_investigation.masters.dao.CannedMstDAOi;
import new_investigation.masters.dao.CollAreaSampleNoConfigMstDAO;
import new_investigation.masters.dao.CollAreaSampleNoConfigMstDAOi;
import new_investigation.masters.dao.CollectionAreaMstDAO;
import new_investigation.masters.dao.CollectionAreaMstDAOi;
import new_investigation.masters.dao.ContainerMstDAO;
import new_investigation.masters.dao.ContainerMstDAOi;
import new_investigation.masters.dao.GlobalLabCannedMstDAO;
import new_investigation.masters.dao.GlobalLabCannedMstDAOi;
import new_investigation.masters.dao.InvParameterMstDAO;
import new_investigation.masters.dao.InvParameterMstDAOi;
import new_investigation.masters.dao.InvSampleMstDAO;
import new_investigation.masters.dao.InvSampleMstDAOi;
import new_investigation.masters.dao.InvTestParameterMstDAO;
import new_investigation.masters.dao.InvTestParameterMstDAOi;
import new_investigation.masters.dao.InvTestSampleMstDAO;
import new_investigation.masters.dao.InvTestSampleMstDAOi;
import new_investigation.masters.dao.LabCollectionAreaMstDAO;
import new_investigation.masters.dao.LabCollectionAreaMstDAOi;
import new_investigation.masters.dao.LabConfigratorMstDAO;
import new_investigation.masters.dao.LabMacroGlobalMapMstDAO;
import new_investigation.masters.dao.LabMacroGlobalMapMstDAOi;
import new_investigation.masters.dao.LabMacroLocalMapMstDAO;
import new_investigation.masters.dao.LabMacroLocalMapMstDAOi;
import new_investigation.masters.dao.LabNoConfigMstDAO;
import new_investigation.masters.dao.LabNoConfigMstDAOi;
import new_investigation.masters.dao.LabTestGlobalMStDAO;
import new_investigation.masters.dao.LabTestLocalMstDAO;
import new_investigation.masters.dao.LabTestSampleMstDAO;
import new_investigation.masters.dao.LocalLabCannedMstDAO;
import new_investigation.masters.dao.LocalLabCannedMstDAOi;
import new_investigation.masters.dao.LocalLabMstDAO;
import new_investigation.masters.dao.LocalLabMstDAOi;
import new_investigation.masters.dao.LocalTestGroupMstDAO;
import new_investigation.masters.dao.LocalTestGroupMstDAOi;
import new_investigation.masters.dao.LocalTestMandRefMstDAO;
import new_investigation.masters.dao.LocalTestMandRefMstDAOi;
import new_investigation.masters.dao.LoincMstDAO;
import new_investigation.masters.dao.MacroMstDAO;
import new_investigation.masters.dao.MacroMstDAOi;
import new_investigation.masters.dao.MandatoryComboMstDAO;
import new_investigation.masters.dao.MandatoryComboMstDAOi;
import new_investigation.masters.dao.MandatoryMstDAO;
import new_investigation.masters.dao.MandatoryMstDAOi;
import new_investigation.masters.dao.RejectionReasonMstDAO;
import new_investigation.masters.dao.RejectionReasonMstDAOi;
import new_investigation.masters.dao.SampleNoConfigMstDAO;
import new_investigation.masters.dao.SampleNoConfigMstDAOi;
import new_investigation.masters.dao.TestGroupInfoLocalMstDAO;
import new_investigation.masters.dao.TestGroupInfoLocalMstDAOi;
import new_investigation.masters.dao.TestGroupInfoMstDAO;
import new_investigation.masters.dao.TestGroupInfoMstDAOi;
import new_investigation.masters.dao.TestGroupMstDAO;
import new_investigation.masters.dao.TestGroupMstDAOi;
import new_investigation.masters.dao.TestMandRefMstDAO;
import new_investigation.masters.dao.TestMandRefMstDAOi;
import new_investigation.masters.dao.TestMandatoryLocalMstDAO;
import new_investigation.masters.dao.TestMandatoryMstDAO;
import new_investigation.masters.dao.TestParaComboMstDAO;
import new_investigation.masters.dao.TestParaComboMstDAOi;
import new_investigation.masters.dao.UserBookMarkMstDAO;
import new_investigation.reports.dao.SampleCollectionListingReportDAO;
import new_investigation.vo.BookMarkMstVO;
import new_investigation.vo.CannedMasterVO;
import new_investigation.vo.CollAreaSampleNoConfigMasterVO;
import new_investigation.vo.CollectionAreaMasterVO;
import new_investigation.vo.ContainerMasterVO;
import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.InvTestSampleMasterVO;
import new_investigation.vo.LabCannedMasterVO;
import new_investigation.vo.LabCollectionAreaMasterVO;
import new_investigation.vo.LabConfigratorMstVO;
import new_investigation.vo.LabMacroMapMasterVO;
import new_investigation.vo.LabMasterVO;
import new_investigation.vo.LabNoConfigMasterVO;
import new_investigation.vo.LabTestGlobalMstVO;
import new_investigation.vo.LabTestSampleMstVO;
import new_investigation.vo.LocalTestMandRefMasterVO;
import new_investigation.vo.LoincMstVO;
import new_investigation.vo.MacroMasterVO;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.MandatoryMasterVO;
import new_investigation.vo.RejectionReasonMasterVO;
import new_investigation.vo.SampleCollectionListingReportVO;
import new_investigation.vo.SampleNoConfigMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import new_investigation.vo.TestGroupMasterVO;
import new_investigation.vo.TestMandRefMasterVO;
import new_investigation.vo.TestMandatoryMstVO;
import new_investigation.vo.TestNewMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
import new_investigation.vo.TestParameterMasterVO;
import new_investigation.vo.UOMMasterVO;
import new_investigation.vo.UserBookMarkMstVO;

public class SampleCollectionListingReportBO 
{

	public Map fetchLab(SampleCollectionListingReportVO reqList_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLab=new ArrayList();
		try
		{
			tx.begin();
			SampleCollectionListingReportDAO reqlist_DAO = new SampleCollectionListingReportDAO(tx);
			lstLab=reqlist_DAO.getLab( _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_DEPT_COMBO,lstLab);
			mp.put(InvestigationConfig.LIST_TEST_COMBO,null);
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

	
	public Map fetchTest(SampleCollectionListingReportVO reqList_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstTest=new ArrayList();
		try
		{
			tx.begin();
			SampleCollectionListingReportDAO reqlist_DAO = new SampleCollectionListingReportDAO(tx);
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
	
	
	
	public Map fetchTestOnLoad(SampleCollectionListingReportVO reqList_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstTest=new ArrayList();
		try
		{
			tx.begin();
			SampleCollectionListingReportDAO reqlist_DAO = new SampleCollectionListingReportDAO(tx);
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

}





