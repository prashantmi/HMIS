package new_investigation.masters.bo;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.fb.TestGroupInfoLocalMstFB;
import new_investigation.masters.dao.BookMarkMstDAO;
import new_investigation.masters.dao.CannedMstDAO;
import new_investigation.masters.dao.CannedMstDAOi;
import new_investigation.masters.dao.CollAreaSampleNoConfigMstDAO;
import new_investigation.masters.dao.CollAreaSampleNoConfigMstDAOi;
import new_investigation.masters.dao.CollectionAreaMstDAO;
import new_investigation.masters.dao.CollectionAreaMstDAOi;
import new_investigation.masters.dao.ContainerMstDAO;
import new_investigation.masters.dao.ContainerMstDAOi;
import new_investigation.masters.dao.FilmMstDAO;
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
import new_investigation.masters.dao.ItemLabTestMappingMstDAO;
import new_investigation.masters.dao.LabCollectionAreaMstDAO;
import new_investigation.masters.dao.LabCollectionAreaMstDAOi;
import new_investigation.masters.dao.LabConfigratorMstDAO;
import new_investigation.masters.dao.LabConsumableMstDAO;
import new_investigation.masters.dao.LabItemMappingMstDAO;
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
import new_investigation.masters.dao.TemplateMstDAO;
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
import new_investigation.masters.dao.MachineTestParameterMstDAO;
import new_investigation.masters.dao.MachineTestParameterMstDAOi;
import new_investigation.masters.dao.UserwiseBookMarkMstDAO;
import new_investigation.masters.dao.invAddendumReasonMstDAO;
import new_investigation.masters.dao.invAntibioticMstDAO;
import new_investigation.masters.dao.invOrganicAntibioticMAppingMstDAO;
import new_investigation.masters.dao.invOrganicMstDAO;
import new_investigation.masters.dao.machineMstDAO;
import new_investigation.masters.dao.machineMstDAOi;
import new_investigation.transactions.controller.data.SampleCollectionDATA;
import new_investigation.transactions.delegate.testAvailabilityDelegate;
import new_investigation.vo.BookMarkMstVO;
import new_investigation.vo.CannedMasterVO;
import new_investigation.vo.CollAreaSampleNoConfigMasterVO;
import new_investigation.vo.CollectionAreaMasterVO;
import new_investigation.vo.ContainerMasterVO;
import new_investigation.vo.FilmMstVO;
import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.InvTestSampleMasterVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.ItemLabTestMappingMstVO;
import new_investigation.vo.LabCannedMasterVO;
import new_investigation.vo.LabCollectionAreaMasterVO;
import new_investigation.vo.LabConfigratorMstVO;
import new_investigation.vo.LabConsumableMstVO;
import new_investigation.vo.LabItemMappingMasterVO;
import new_investigation.vo.LabMacroMapMasterVO;
import new_investigation.vo.LabMasterVO;
import new_investigation.vo.LabNoConfigMasterVO;
import new_investigation.vo.LabTestGlobalMstVO;
import new_investigation.vo.LabTestSampleMstVO;
import new_investigation.vo.LocalTestMandRefMasterVO;
import new_investigation.vo.LoincMstVO;
import new_investigation.vo.MachineTestParameterMasterVO;
import new_investigation.vo.MacroMasterVO;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.MandatoryMasterVO;
import new_investigation.vo.RejectionReasonMasterVO;
import new_investigation.vo.SampleNoConfigMasterVO;
import new_investigation.vo.TemplateMstVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import new_investigation.vo.TestGroupMasterVO;
import new_investigation.vo.TestMandRefMasterVO;
import new_investigation.vo.TestMandatoryMstVO;
import new_investigation.vo.TestNewMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
import new_investigation.vo.TestParameterMasterVO;
import new_investigation.vo.UOMMasterVO;
import new_investigation.vo.UserBookMarkMstVO;
import new_investigation.vo.MachineTestParameterMasterVO;
import new_investigation.vo.UserwiseBookMarkMstVO;
import new_investigation.vo.invAddendumReasonMstVO;
import new_investigation.vo.invAntibioticMstVO;
import new_investigation.vo.invOrganicAntibioticMappingMstVO;
import new_investigation.vo.invOrganicMstVO;
import new_investigation.vo.machineMstVO;


public class InvestigationMasterBO implements InvestigationMasterBOi
{


	////START SAMPLE Master ///

	public void saveCheckList(InvSampleMasterVO bCheckListMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			InvSampleMstDAOi bCheckListMstDAOi = new InvSampleMstDAO(tx);

			checkList=bCheckListMstDAOi.checkDuplicateCheckList(bCheckListMasterVO, _UserVO);
			if(checkList==null)
			{
				bCheckListMstDAOi.create(bCheckListMasterVO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("  Data Is Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	public Map fetchCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLoinic=new ArrayList();
		try
		{
			tx.begin();
			InvSampleMstDAOi bCheckListMstDAOi = new InvSampleMstDAO(tx);
			bCheckListMstDAOi.fetchCheckList(bCheckListMasterVO, _UserVO);

			lstLoinic=bCheckListMstDAOi.getLoinicCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LOINIC_COMBO, lstLoinic);
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

	public Map fetchSample(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLoinic=new ArrayList();
		try
		{
			tx.begin();
			InvSampleMstDAOi bCheckListMstDAOi = new InvSampleMstDAO(tx);
			//bCheckListMstDAOi.fetchCheckList(bCheckListMasterVO, _UserVO);

			lstLoinic=bCheckListMstDAOi.getLoinicCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LOINIC_COMBO, lstLoinic);
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




	public void savemodifyCheckList(InvSampleMasterVO bCheckListMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			InvSampleMstDAOi bCheckListMstDAOi = new InvSampleMstDAO(tx);
			count=bCheckListMstDAOi.checkDuplicateCheckListModify(bCheckListMasterVO, _UserVO);
			if(count.equals("0"))
			{
				bCheckListMstDAOi.updateCheckList(bCheckListMasterVO, _UserVO);
				//bCheckListMstDAOi.savemodifyCheckList(bCheckListMasterVO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("  Data Is Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	////END SAMPLE Master ///


	//////////////////////////////////////START MandatoryCombo Master//////////////////////////////////////


	public void saveMandatoryCombo(List<MandatoryComboMasterVO> lstMandatoryComboVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";			

		try
		{
			tx.begin();
			MandatoryComboMstDAOi mandatorycombo_DAOi = new MandatoryComboMstDAO(tx);

			for(MandatoryComboMasterVO mandatorycombo_VO:lstMandatoryComboVO)
			{


				checkList=mandatorycombo_DAOi.checkDuplicateMandatoryCombo(mandatorycombo_VO, _UserVO);
				if(checkList==null)
				{
					mandatorycombo_DAOi.createMandatoryCombo(mandatorycombo_VO, _UserVO);
				}else{
					throw new HisDuplicateRecordException("Values Already Exists");
				} 
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
			//tx.commitAll();
			tx.close();
		}

	}


	public Map fetchCheckListMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstMandName = new ArrayList();

		try
		{
			tx.begin();
			MandatoryComboMstDAOi mandatorycombo_DAOi = new MandatoryComboMstDAO(tx);
			mandatorycombo_DAOi.fetchCheckListMandatoryCombo(mandatorycombo_VO, _UserVO);
			lstMandName=mandatorycombo_DAOi.getMandCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_MAND_COMBO,lstMandName);

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

	public Map fetchMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstMandName=new ArrayList();
		try
		{
			tx.begin();
			MandatoryComboMstDAOi mandatorycombo_DAOi = new MandatoryComboMstDAO(tx);
			lstMandName=mandatorycombo_DAOi.getMandCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_MAND_COMBO,lstMandName);
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




	public void savemodifyMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			MandatoryComboMstDAOi mandatorycombo_DAOi = new MandatoryComboMstDAO(tx);
			count=mandatorycombo_DAOi.checkDuplicateModifyMandatoryCombo(mandatorycombo_VO, _UserVO);
			if(count.equals("0"))
			{

				mandatorycombo_DAOi.updateMandatoryCombo(mandatorycombo_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Values Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}



	public List<MandatoryComboMasterVO> fetchdisplaydataMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<MandatoryComboMasterVO> mandatorycombo_listVO=new ArrayList<MandatoryComboMasterVO>();
		try
		{
			tx.begin();
			MandatoryComboMstDAOi mandatorycombo_DAOi = new MandatoryComboMstDAO(tx);
			mandatorycombo_listVO=mandatorycombo_DAOi.fetchdisplaydataMandatoryCombo(mandatorycombo_VO, _UserVO);
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
		return mandatorycombo_listVO;
	}
	//////////////////////////////////END mandatory combo Master///////////////////////////////


	////////////////////////////////////START Mandatory Master/////////////////////////////////

	public void saveMandatory(MandatoryMasterVO mandatorymaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			MandatoryMstDAOi mandatorymaster_DAOi = new MandatoryMstDAO(tx);

			checkList=mandatorymaster_DAOi.checkDuplicateMandatory(mandatorymaster_VO, _UserVO);
			if(checkList==null)
			{
				mandatorymaster_DAOi.createMandatory(mandatorymaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("  Data Is Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	public void fetchCheckListMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		try
		{
			tx.begin();
			MandatoryMstDAOi mandatorymaster_DAOi = new MandatoryMstDAO(tx);
			mandatorymaster_DAOi.fetchCheckListMandatory(mandatorymaster_VO, _UserVO);
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

	}

	public void fetchMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		try
		{
			tx.begin();

			MandatoryMstDAOi mandatorymaster_DAOi = new MandatoryMstDAO(tx);
			mandatorymaster_DAOi.fetchCheckListMandatory(mandatorymaster_VO, _UserVO);
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

	}




	public void savemodifyMandatory(MandatoryMasterVO mandatorymaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			MandatoryMstDAOi mandatorymaster_DAOi = new MandatoryMstDAO(tx);
			count=mandatorymaster_DAOi.checkDuplicateModifyMandatory(mandatorymaster_VO, _UserVO);
			if(count.equals("0"))
			{
				mandatorymaster_DAOi.updateMandatory(mandatorymaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("DATA Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	////////////////////////////////////////////END Mandatory Master//////////////////////////////////


	//////////////////////////////////////////START PARAMETER MASTER///////////////////////////////////

	public void saveParameter(InvParameterMasterVO parametermaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			InvParameterMstDAOi parametermaster_DAOi = new InvParameterMstDAO(tx);
			checkList=parametermaster_DAOi.checkDuplicateParameter(parametermaster_VO, _UserVO);

			if(checkList==null)
			{
				parametermaster_DAOi.createParameter(parametermaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	public void fetchCheckListParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLoinic=new ArrayList();
		try
		{
			tx.begin();
			InvParameterMstDAOi parametermaster_DAOi = new InvParameterMstDAO(tx);
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

	}

	public void fetchParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InvParameterMstDAOi parametermaster_DAOi = new InvParameterMstDAO(tx);
			parametermaster_DAOi.fetchParameter(parametermaster_VO, _UserVO);
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
	}

	public void savemodifyParameter(InvParameterMasterVO parametermaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			InvParameterMstDAOi parametermaster_DAOi = new InvParameterMstDAO(tx);
			count=parametermaster_DAOi.checkDuplicateModifyParameter(parametermaster_VO, _UserVO);
			if(count.equals("0"))
			{

				parametermaster_DAOi.updateParameter(parametermaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	////////////////////////////////////////END PARAMETER MASTER/////////////////////////////////////////

	////SATRT UOM Master ///

	public void saveUOM(UOMMasterVO uOMMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			InvSampleMstDAOi uOMMstDAOi = new InvSampleMstDAO(tx);

			checkList=uOMMstDAOi.checkDuplicateUOM(uOMMasterVO, _UserVO);
			if(checkList==null)
			{
				uOMMstDAOi.createUOM(uOMMasterVO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("  Data Is Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	public Map fetchUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLoinicuom=new ArrayList();
		try
		{
			tx.begin();
			InvSampleMstDAOi uOMMstDAOi = new InvSampleMstDAO(tx);
			uOMMstDAOi.fetchUOM(uOMMasterVO, _UserVO);

			lstLoinicuom=uOMMstDAOi.getLoinicCombouom( _UserVO);
			mp.put(InvestigationConfig.LIST_LOINIC_COMBO_UOM, lstLoinicuom);
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

	public void savemodifyUOM(UOMMasterVO uOMMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			InvSampleMstDAOi uOMMstDAOi = new InvSampleMstDAO(tx);
			count=uOMMstDAOi.checkDuplicateUOMModify(uOMMasterVO, _UserVO);
			if(count.equals("0"))
			{
				uOMMstDAOi.updateUOM(uOMMasterVO, _UserVO);
			}else{
				throw new HisDuplicateRecordException(" Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	public Map fetchUOMD(UOMMasterVO uOMMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLoinicuom=new ArrayList();
		try
		{
			tx.begin();
			InvSampleMstDAOi uOMMstDAOi = new InvSampleMstDAO(tx);
			//uOMMstDAOi.fetchUOM(uOMMasterVO, _UserVO);

			lstLoinicuom=uOMMstDAOi.getLoinicCombouom( _UserVO);
			mp.put(InvestigationConfig.LIST_LOINIC_COMBO_UOM, lstLoinicuom);
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

	//END LAB TEST MASTER//




	////SATRT  TEST Parameter Master ///


	public void saveTestParameter(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		boolean noMatch=true;
		List<TestParameterMasterVO> lstTestParaCode=new  ArrayList<TestParameterMasterVO>();
		try
		{
			tx.begin();
			InvTestParameterMstDAOi TestParameterMstDAOi = new InvTestParameterMstDAO(tx);

			checkList=TestParameterMstDAOi.checkDuplicateTestParameter(testParameterMasterVO, _UserVO);
			if(checkList==null)
			{


				lstTestParaCode=TestParameterMstDAOi.CheckTestParameterCode(testParameterMasterVO, _UserVO);

				for(TestParameterMasterVO voTestPara:lstTestParaCode)
				{

					if(voTestPara.getTmpTestCode().equals(testParameterMasterVO.getTestCode())&&voTestPara.getParaCode().equals(testParameterMasterVO.getParameterCode()))		

					{
						noMatch=false;
					}
				}
				if(noMatch)
				{
					TestParameterMstDAOi.createTestParameter(testParameterMasterVO, _UserVO);
				}
				else
				{
					TestParameterMstDAOi.updateTestParameter(testParameterMasterVO, _UserVO);
				}
			}else{

				throw new HisDuplicateRecordException("  Data Is Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	public Map fetchTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List testcombo=new ArrayList();
		List parametercombo=new ArrayList();
		List criteriacombo=new ArrayList();
		List elementtypecombo=new ArrayList();
		List loincScale=new ArrayList();
		List testeditorValue=new ArrayList();
		List urlcombo=new ArrayList();
		try
		{
			tx.begin();
			InvTestParameterMstDAOi TestParameterMstDAOi = new InvTestParameterMstDAO(tx);
			 TestParameterMstDAOi.fetchTestParameter(testParameterMasterVO, _UserVO);
             
			 
			
			
			testcombo=TestParameterMstDAOi.getTestCombo( _UserVO);
			mp.put(InvestigationConfig.TEST_COMBO, testcombo);

			parametercombo=TestParameterMstDAOi.getParameterCombo( _UserVO);
			mp.put(InvestigationConfig.PARAMETER_COMBO, parametercombo);

			criteriacombo=TestParameterMstDAOi.getCriteriaCombo( _UserVO);
			mp.put(InvestigationConfig.CRITERIA_COMBO, criteriacombo);

			elementtypecombo=TestParameterMstDAOi.getElementTypeCombo( _UserVO);
			mp.put(InvestigationConfig.ELEMENT_TYPE_COMBO, elementtypecombo);
			
			loincScale=TestParameterMstDAOi.getLoincScale( _UserVO);
			mp.put(InvestigationConfig.LOINC_SCALE_COMBO, loincScale);
			
             urlcombo=TestParameterMstDAOi.ajaxUrlCombo( _UserVO);
			mp.put(InvestigationConfig.URL_MAP_COMBO, urlcombo);
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

	public void savemodifyTestParameter(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			InvTestParameterMstDAOi TestParameterMstDAOi = new InvTestParameterMstDAO(tx);
			// count=TestParameterMstDAOi.checkDuplicateTestParameterModify(testParameterMasterVO, _UserVO);
			//if(count.equals("0"))
			//{
			TestParameterMstDAOi.updateTestParameter(testParameterMasterVO, _UserVO);
			//labMstDAOi.savemodifyLab(labMasterVO, _UserVO);
			//}else{
			// throw new HisDuplicateRecordException("LAB Data Is Already Exists");
			//} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}



	public Map fetchParameterCombo(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List testcombo=new ArrayList();
		List testcomboReq=new ArrayList();
		List mastertypetestcombo=new ArrayList();
		List parametercombo=new ArrayList();
		List criteriacombo=new ArrayList();
		List elementtypecombo=new ArrayList();
		List loincScale=new ArrayList();
		List urlcombo=new ArrayList();
		try
		{
			tx.begin();
			InvTestParameterMstDAOi TestParameterMstDAOi = new InvTestParameterMstDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			testcombo=TestParameterMstDAOi.getTestCombo( _UserVO);
			mp.put(InvestigationConfig.TEST_COMBO, testcombo);

			testcomboReq=TestParameterMstDAOi.getTestComboReqform( _UserVO);
			mp.put(InvestigationConfig.TEST_COMBO_REQQ, testcomboReq);
			
			
			mastertypetestcombo=TestParameterMstDAOi.getmastertypeTestCombo( _UserVO);
			mp.put(InvestigationConfig.MASTERTYPE_TEST_COMBO, mastertypetestcombo);

			
			parametercombo=TestParameterMstDAOi.getParameterCombo( _UserVO);
			mp.put(InvestigationConfig.PARAMETER_COMBO, parametercombo);

			criteriacombo=TestParameterMstDAOi.getCriteriaCombo( _UserVO);
			mp.put(InvestigationConfig.CRITERIA_COMBO, criteriacombo);

			elementtypecombo=TestParameterMstDAOi.getElementTypeCombo( _UserVO);
			mp.put(InvestigationConfig.ELEMENT_TYPE_COMBO, elementtypecombo);
			
			loincScale=TestParameterMstDAOi.getLoincScale( _UserVO);
			mp.put(InvestigationConfig.LOINC_SCALE_COMBO, loincScale);
			
			 urlcombo=TestParameterMstDAOi.ajaxUrlCombo( _UserVO);
				mp.put(InvestigationConfig.URL_MAP_COMBO, urlcombo);
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

	public Map  TestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List parametercombo=new ArrayList();

		try
		{
			tx.begin();
			InvTestParameterMstDAOi TestParameterMstDAOi = new InvTestParameterMstDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			parametercombo=TestParameterMstDAOi.getTestParameterCombo(testParameterMasterVO,_UserVO);
			mp.put(InvestigationConfig.PARAMETER_COMBO, parametercombo);


		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException("No parameter Name Available For This Test" );
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
	
	
	
	
	/*public Map ajaxUrlCombo(TestParameterMasterVO testNewMasterVO, UserVO _UserVO)
	  {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lsttest=new ArrayList();
		
		List lstPrintTemplate=new ArrayList();
		try
		{
			tx.begin();
			InvTestParameterMstDAOi testMstDAOi = new InvTestParameterMstDAO(tx);
			//bCheckListMstDAOi.fetchCheckList(bCheckListMasterVO, _UserVO);
			lsttest=testMstDAOi.ajaxUrlCombo( _UserVO);
			
			mp.put(InvestigationConfig.URL_MAP_COMBO, lsttest);
			
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());

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
	}*/
	////END UOM Master ///
	////SATRT LAB Master ////



	////END LAB Master ///
	public void saveLab(LabMasterVO labMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			InvSampleMstDAOi labMstDAOi = new InvSampleMstDAO(tx);

			checkList=labMstDAOi.checkDuplicateLab(labMasterVO, _UserVO);
			if(checkList==null)
			{
				labMstDAOi.createLab(labMasterVO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("  Data Is Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}



	public Map fetchLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List departcombo=new ArrayList();
		try
		{
			tx.begin();
			InvSampleMstDAOi labMstDAOi = new InvSampleMstDAO(tx);
			labMstDAOi.fetchLab(labMasterVO, _UserVO);

			departcombo=labMstDAOi.getLabCombo( _UserVO);
			mp.put(InvestigationConfig.DEPART_COMBO, departcombo);
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

	public void savemodifyLab(LabMasterVO labMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			InvSampleMstDAOi labMstDAOi = new InvSampleMstDAO(tx);
			count=labMstDAOi.checkDuplicateLabModify(labMasterVO, _UserVO);
			if(count.equals("0"))
			{
				labMstDAOi.updateLab(labMasterVO, _UserVO);
				//labMstDAOi.savemodifyLab(labMasterVO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("  Data Is Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	public Map fetchLabD(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List departcombo=new ArrayList();
		try
		{
			tx.begin();
			InvSampleMstDAOi labMstDAOi = new InvSampleMstDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			departcombo=labMstDAOi.getLabCombo( _UserVO);
			mp.put(InvestigationConfig.DEPART_COMBO, departcombo);
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


	////START TEST Master ///

	public void saveTest(TestNewMasterVO testNewMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			InvSampleMstDAOi testMstDAOi = new InvSampleMstDAO(tx);

			checkList=testMstDAOi.checkDuplicateTest(testNewMasterVO, _UserVO);
			if(checkList==null)
			{
				testMstDAOi.createTest(testNewMasterVO, _UserVO);
				testMstDAOi.updatePrintingTemplate(testNewMasterVO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	public Map fetchTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lsttest=new ArrayList();
		List lstPrintTemplate=new ArrayList();
		try
		{
			tx.begin();
			InvSampleMstDAOi testMstDAOi = new InvSampleMstDAO(tx);
			testMstDAOi.fetchTest(testNewMasterVO, _UserVO);

			lsttest=testMstDAOi.getTestCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lsttest);
			
			lstPrintTemplate=testMstDAOi.getTestPrintingTemplateCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_PRINTING_TEMPLATE, lstPrintTemplate);
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

	public Map fetchTestD(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lsttest=new ArrayList();
		List lstPrintTemplate=new ArrayList();
		try
		{
			tx.begin();
			InvSampleMstDAOi testMstDAOi = new InvSampleMstDAO(tx);
			//bCheckListMstDAOi.fetchCheckList(bCheckListMasterVO, _UserVO);
			lsttest=testMstDAOi.getTestCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lsttest);
			
			lstPrintTemplate=testMstDAOi.getTestPrintingTemplateCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_PRINTING_TEMPLATE, lstPrintTemplate);
			
			
			
			
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());

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




	public void savemodifyTest(TestNewMasterVO testNewMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			InvSampleMstDAOi testMstDAOi = new InvSampleMstDAO(tx);
			count=testMstDAOi.checkDuplicateTestModify(testNewMasterVO, _UserVO);
			if(count.equals("0"))
			{
				testMstDAOi.updateTest(testNewMasterVO, _UserVO);
				testMstDAOi.updatePrintingTemplateOnModify(testNewMasterVO, _UserVO);


			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}




	////END TEST Master ///	







	///////////////////////////////////////////START CONTAINER Master ///////////////////////////////////


	public void saveContainer(ContainerMasterVO container_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			ContainerMstDAOi container_DAOi = new ContainerMstDAO(tx);

			checkList=container_DAOi.checkDuplicateContainer(container_VO, _UserVO);
			if(checkList==null)
			{
				container_DAOi.createContainer(container_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	public Map fetchCheckListContainer(ContainerMasterVO container_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstUOM=new ArrayList();
		try
		{
			tx.begin();
			ContainerMstDAOi container_DAOi = new ContainerMstDAO(tx);
			container_DAOi.fetchCheckListContainer(container_VO, _UserVO);

			lstUOM=container_DAOi.getuomCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, lstUOM);
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

	public Map fetchContainer(ContainerMasterVO container_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstUOM=new ArrayList();
		try
		{
			tx.begin();
			ContainerMstDAOi container_DAOi = new ContainerMstDAO(tx);

			lstUOM=container_DAOi.getuomCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, lstUOM);
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




	public void savemodifyContainer(ContainerMasterVO container_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			ContainerMstDAOi container_DAOi = new ContainerMstDAO(tx);
			count=container_DAOi.checkDuplicateModifyContainer(container_VO, _UserVO);
			if(count.equals("0"))
			{
				container_DAOi.updateContainer(container_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	////END CONTAINER Master ///




	///////////////////////////////////////////START COLLECTION AREA Master ///////////////////////////////////

	public void saveCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList = "";
		try {
			tx.begin();
			CollectionAreaMstDAOi collectionarea_DAOi = new CollectionAreaMstDAO(tx);

			checkList = collectionarea_DAOi.checkDuplicateCollectionArea(collectionarea_VO,
					_UserVO);
			if (checkList == null) {
				collectionarea_DAOi.createCollectionArea(collectionarea_VO, _UserVO);
			} else {
				throw new HisDuplicateRecordException(
						"Data Already Exists");
			}
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public Map fetchCheckListCollectionArea(CollectionAreaMasterVO collectionarea_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLocation = new ArrayList();
		List lstWard = new ArrayList();

		try {
			tx.begin();
			CollectionAreaMstDAOi collectionarea_DAOi = new CollectionAreaMstDAO(tx);
			collectionarea_DAOi.fetchCheckListCollectionArea(collectionarea_VO, _UserVO);

			lstLocation = collectionarea_DAOi.getlocationCombo(_UserVO);
			
			if(collectionarea_VO.getHmode().equals("MODIFY"))
				lstWard = collectionarea_DAOi.getwardComboOnModify(collectionarea_VO.getCollectionareaCode(),_UserVO);
			else
				lstWard = collectionarea_DAOi.getwardCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LOCATION_COMBO, lstLocation);
			mp.put(InvestigationConfig.LIST_WARD_COMBO, lstWard);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public Map fetchCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLocation = new ArrayList();
		List lstWard = new ArrayList();

		try {
			tx.begin();
			CollectionAreaMstDAOi collectionarea_DAOi = new CollectionAreaMstDAO(tx);

			lstLocation = collectionarea_DAOi.getlocationCombo(_UserVO);
			lstWard = collectionarea_DAOi.getwardCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LOCATION_COMBO, lstLocation);
			mp.put(InvestigationConfig.LIST_WARD_COMBO, lstWard);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public void savemodifyCollectionArea(CollectionAreaMasterVO collectionarea_VO,
			UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();
			CollectionAreaMstDAOi collectionarea_DAOi = new CollectionAreaMstDAO(tx);
			count = collectionarea_DAOi.checkDuplicateModifyCollectionArea(collectionarea_VO,
					_UserVO);
			if (count.equals("0")) {
				collectionarea_DAOi.updateCollectionArea(collectionarea_VO, _UserVO);

			} else {
				throw new HisDuplicateRecordException(
						"Data Already Exists");
			}
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	// //END CollectionArea Master ///


	///////////////////////////////////////////START TEST SAMPLE Master ///////////////////////////////////

	public void saveInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList = "";
		String checkDefault="";
		try {
			tx.begin();
			InvTestSampleMstDAOi testsample_DAOi = new InvTestSampleMstDAO(tx);

			checkList = testsample_DAOi.checkDuplicateInvTestSample(testsample_VO,
					_UserVO);
			checkDefault= testsample_DAOi.checkDefaultInvTestSample(testsample_VO,
					_UserVO);

			if(checkDefault!=null && testsample_VO.getDefaultSample().equals("1"))
				throw new HisDuplicateRecordException(
						"Default Sample Already Exists");

			else{

				if (checkList == null)
					testsample_DAOi.createInvTestSample(testsample_VO, _UserVO);
				else
					testsample_DAOi.update_insertInvTestSample(testsample_VO, _UserVO);
			}

		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public Map fetchCheckListInvTestSample(InvTestSampleMasterVO testsample_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstTest = new ArrayList();
		List lstSample = new ArrayList();
		List lstUOM = new ArrayList();
		List lstContainer = new ArrayList();

		try {
			tx.begin();
			InvTestSampleMstDAOi testsample_DAOi = new InvTestSampleMstDAO(tx);
			testsample_DAOi.fetchCheckListInvTestSample(testsample_VO, _UserVO);

			lstTest = testsample_DAOi.gettestCombo(_UserVO);
			lstSample = testsample_DAOi.getsampleCombo(testsample_VO,_UserVO);
			lstUOM = testsample_DAOi.getuomCombo(_UserVO);
			lstContainer = testsample_DAOi.getcontainerCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_TESTNAME_COMBO, lstTest);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, lstSample);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, lstUOM);
			mp.put(InvestigationConfig.LIST_CONTAINER_COMBO, lstContainer);



		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public Map fetchInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstTest = new ArrayList();
		List lstSample = new ArrayList();
		List lstUOM = new ArrayList();
		List lstContainer = new ArrayList();

		try {
			tx.begin();
			InvTestSampleMstDAOi testsample_DAOi = new InvTestSampleMstDAO(tx);


			testsample_DAOi.gettestName(testsample_VO, _UserVO);


			lstSample = testsample_DAOi.getsampleCombo(testsample_VO,_UserVO);
			lstUOM = testsample_DAOi.getuomCombo(_UserVO);
			lstContainer = testsample_DAOi.getcontainerCombo(_UserVO);




			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, lstSample);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, lstUOM);
			mp.put(InvestigationConfig.LIST_CONTAINER_COMBO, lstContainer);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public void savemodifyInvTestSample(InvTestSampleMasterVO testsample_VO,
			UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		String checkDefault="";
		try {
			tx.begin();
			InvTestSampleMstDAOi testsample_DAOi = new InvTestSampleMstDAO(tx);
			checkDefault= testsample_DAOi.checkDefaultInvTestSample(testsample_VO,
					_UserVO);

			if(testsample_VO.getDefaultTrue().equals("1"))
				testsample_DAOi.updateInvTestSample(testsample_VO, _UserVO);

			else if(checkDefault!=null && testsample_VO.getDefaultSample().equals("1"))
				throw new HisDuplicateRecordException(
						"Default Sample Already Exists");
			else
				testsample_DAOi.updateInvTestSample(testsample_VO, _UserVO);

		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}




	// //END InvTestSample Master ///




	//////////////////////////////////////START TestParaCombo Master//////////////////////////////////////


	public void saveTestParaCombo(List<TestParaComboMasterVO> lstTestParaComboVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";			

		try
		{
			tx.begin();
			TestParaComboMstDAOi testparacombo_DAOi = new TestParaComboMstDAO(tx);

			for(TestParaComboMasterVO testparacombo_VO:lstTestParaComboVO)
			{

				checkList=testparacombo_DAOi.checkDuplicateTestParaCombo(testparacombo_VO, _UserVO);
				if(checkList==null)
				{  if(testparacombo_VO.getSetdefault().equals("1")) {
					testparacombo_DAOi.removeDefaultTestParaCombo(testparacombo_VO, _UserVO);
					testparacombo_DAOi.createTestParaCombo(testparacombo_VO, _UserVO);
					} else {
						testparacombo_DAOi.createTestParaCombo(testparacombo_VO, _UserVO);
						}
				}else{
					
					throw new HisDuplicateRecordException("Values Already Exists");
				} 
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
			//tx.commitAll();
			tx.close();
		}

	}


	public Map fetchCheckListTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();


		Map mp = new HashMap();
		List lstParameter = new ArrayList();
		List lstTest = new ArrayList();



		try
		{
			tx.begin();
			TestParaComboMstDAOi testparacombo_DAOi = new TestParaComboMstDAO(tx);
			testparacombo_DAOi.fetchCheckListTestParaCombo(testparacombo_VO, _UserVO);

			lstParameter = testparacombo_DAOi.getTestParaCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_TESTPARA_COMBO, lstParameter);

			lstTest = testparacombo_DAOi.getTestCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);


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

	public Map fetchTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstTestName=new ArrayList();
		List lstTestParaName=new ArrayList();

		try
		{
			tx.begin();
			TestParaComboMstDAOi testparacombo_DAOi = new TestParaComboMstDAO(tx);
			lstTestName=testparacombo_DAOi.getTestCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO,lstTestName);
			lstTestParaName=testparacombo_DAOi.getTestParaCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_TESTPARA_COMBO,lstTestParaName);
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




	public void savemodifyTestParaCombo(TestParaComboMasterVO testparacombo_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			TestParaComboMstDAOi testparacombo_DAOi = new TestParaComboMstDAO(tx);
//			count=testparacombo_DAOi.checkDuplicateModifyTestParaCombo(testparacombo_VO, _UserVO);
//			if(count.equals("0"))
//			{ if(testparacombo_VO.getSetdefault()=="1") {testparacombo_DAOi.removeDefaultTestParaCombo(testparacombo_VO, _UserVO);}
//				testparacombo_DAOi.updateTestParaCombo(testparacombo_VO, _UserVO);
//			}else{
//				throw new HisDuplicateRecordException("Values Already Exists");
//			} 
			
			if(testparacombo_VO.getSetdefault().equals("1")) {
			testparacombo_DAOi.removeDefaultTestParaCombo(testparacombo_VO, _UserVO);
			testparacombo_DAOi.updateTestParaCombo(testparacombo_VO, _UserVO);
			} else {
			testparacombo_DAOi.updateTestParaCombo(testparacombo_VO, _UserVO);
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	
	
	/*public void savemodifyMachineTestParaCombo(MachineTestParameterMasterVO machinetestparacombo_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			MachineTestParameterMstDAOi machinetestparacombo_DAOi = new MachineTestParameterMstDAO(tx);
			count=machinetestparacombo_DAOi.checkDuplicateModifyTestParaCombo(machinetestparacombo_VO, _UserVO);
			if(count.equals("0"))
			{
				machinetestparacombo_DAOi.updateTestParaCombo(machinetestparacombo_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Values Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}*/
	
	
	


	public List<TestParaComboMasterVO> fetchdisplaydataTestParaCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<TestParaComboMasterVO> testparacombo_listVO=new ArrayList<TestParaComboMasterVO>();
		try
		{
			tx.begin();
			TestParaComboMstDAOi testparacombo_DAOi = new TestParaComboMstDAO(tx);
			testparacombo_listVO=testparacombo_DAOi.fetchdisplaydataTestParaCombo(testparacombo_VO, _UserVO);
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
		return testparacombo_listVO;
	}


	public Map fetchParameterCombo(TestParaComboMasterVO testparacombo_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstParameter = new ArrayList();


		try {
			tx.begin();
			TestParaComboMstDAOi testparacombo_DAOi = new TestParaComboMstDAO(tx);

			lstParameter = testparacombo_DAOi.fetchParameterCombo(testparacombo_VO, _UserVO);

			mp.put(InvestigationConfig.LIST_TESTPARA_COMBO, lstParameter);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	//////////////////////////////////END testpara combo Master///////////////////////////////

	public Map fetchLabTest(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();
		List testMethod=new ArrayList();
		try
		{
			tx.begin();
			InvSampleMstDAOi labTestMstDAOi = new InvSampleMstDAO(tx);
			LabTestGlobalMStDAO labTestGlobalMStDAO=new LabTestGlobalMStDAO(tx);
			testMethod=labTestGlobalMStDAO.getTestMethodCombo(labTestMasterVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_METHOD_COMBO, testMethod);
			labTestGlobalMStDAO.getLabCombo(labTestMasterVO, _UserVO);
			labTestMasterVO.setIsTestAvailable("1");
			labTestMasterVO.setIsAppointment("0");
			labTestMasterVO.setIsConfidential("0");
			labTestMasterVO.setIsConsent("0");
			labTestMasterVO.setIsMultisession("0");
			labTestMasterVO.setIsMandatoryReq("0");
			labTestMasterVO.setIsRequisitionFormNeeded("0");
			labTestMasterVO.setIsSampleFormNeeded("0");
			labTestMasterVO.setIsSecurePrinting("0");
			labTestMasterVO.setIsGrossingReq("0");
			labTestMasterVO.setIsFilmReq("0");
			labTestMasterVO.setNoOfTest("9999");
			labTestMasterVO.setAgeBound("0");
			labTestMasterVO.setGenderBound("0");
			labTestMasterVO.setTestDays("1111111");
			labTestMasterVO.setIsOPDDoctorDesk("1");
			labTestMasterVO.setIsOPDBayDoctorDesk("1");
			labTestMasterVO.setIsIPDDoctorDesk("1");
			
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

	public List getTestBylabCode(LabTestGlobalMstVO labTestGlobalMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List testcombo=new ArrayList();
		try
		{
			tx.begin();
			InvSampleMstDAO labTestMstDAO = new InvSampleMstDAO(tx);
			testcombo=labTestMstDAO.getTestBylabCode(labTestGlobalMstVO, _UserVO);
			//mp.put(InvestigationConfig.LIST_TEST_COMBO, testcombo);
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
		return testcombo;
	}

	public void savemodifyLabTest(LabTestGlobalMstVO labTestGlobalMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			LabTestGlobalMStDAO labTestGlobalMStDAO=new LabTestGlobalMStDAO(tx);
			
			//setting desk properties
			labTestGlobalMstVO.setDeskProperties(labTestGlobalMstVO.getIsOPDDoctorDesk()+"$"+labTestGlobalMstVO.getIsOPDBayDoctorDesk()+"$"+labTestGlobalMstVO.getIsIPDDoctorDesk());
			labTestGlobalMStDAO.saveModifyLabTest(labTestGlobalMstVO, _UserVO);

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	public void savemodifyLabTestLocal(LabTestGlobalMstVO labTestGlobalMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			LabTestGlobalMStDAO labTestGlobalMStDAO=new LabTestGlobalMStDAO(tx);
			LabTestLocalMstDAO localLabTestMstDAO=new LabTestLocalMstDAO(tx);
			
			//setting desk properties
			labTestGlobalMstVO.setDeskProperties(labTestGlobalMstVO.getIsOPDDoctorDesk()+"$"+labTestGlobalMstVO.getIsOPDBayDoctorDesk()+"$"+labTestGlobalMstVO.getIsIPDDoctorDesk());
			
			
			checkList=localLabTestMstDAO.checkDuplicateUserTestCodeOnModify(labTestGlobalMstVO, _UserVO);
			if(checkList==null)
			labTestGlobalMStDAO.saveModifyLabTestLocal(labTestGlobalMstVO, _UserVO);
			else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	public void saveLabTestGlobalTest(LabTestGlobalMstVO labTestGlobalMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			LabTestGlobalMStDAO labTestGlobalMStDAO=new LabTestGlobalMStDAO(tx);
			
			//setting desk properties
			labTestGlobalMstVO.setDeskProperties(labTestGlobalMstVO.getIsOPDDoctorDesk()+"$"+labTestGlobalMstVO.getIsOPDBayDoctorDesk()+"$"+labTestGlobalMstVO.getIsIPDDoctorDesk());
			
			
			
			labTestGlobalMStDAO.checkDuplicateData(labTestGlobalMstVO, _UserVO);
			if(labTestGlobalMstVO.getCount().equals("0"))
			{
				labTestGlobalMStDAO.SaveLabTest(labTestGlobalMstVO, _UserVO);
			}
			else
				labTestGlobalMStDAO.UpdateLabTest(labTestGlobalMstVO, _UserVO);
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	public Map fetchLabTestLocal(LabTestGlobalMstVO labTestMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();
		List testMethod=new ArrayList();
		try
		{
			tx.begin();
			LabTestLocalMstDAO labTestLocalMstDAO=new LabTestLocalMstDAO(tx);
			testMethod=labTestLocalMstDAO.getTestMethodLocalCombo(labTestMasterVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_METHOD_COMBO, testMethod);
			labcombo=labTestLocalMstDAO.getLabComboLocal(labTestMasterVO, _UserVO);
			labTestMasterVO.setIsTestAvailable("1");
			labTestMasterVO.setIsAppointment("0");
			labTestMasterVO.setIsConfidential("0");
			labTestMasterVO.setIsConsent("0");
			labTestMasterVO.setIsMultisession("0");
			labTestMasterVO.setIsMandatoryReq("0");
			labTestMasterVO.setIsRequisitionFormNeeded("0");
			labTestMasterVO.setIsSampleFormNeeded("0");
			labTestMasterVO.setIsSecurePrinting("0");
			labTestMasterVO.setIsGrossingReq("0");
			labTestMasterVO.setIsFilmReq("0");
			labTestMasterVO.setNoOfTest("9999");
			labTestMasterVO.setAgeBound("0");
			labTestMasterVO.setGenderBound("0");
			labTestMasterVO.setTestDays("1111111");
			labTestMasterVO.setIsOPDDoctorDesk("1");
			labTestMasterVO.setIsOPDBayDoctorDesk("1");
			labTestMasterVO.setIsIPDDoctorDesk("1");
			labTestMasterVO.setIsPID("0");
			
			
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
	public List getTestBylabCodeLocal(LabTestGlobalMstVO labTestGlobalMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List testcombo=new ArrayList();
		try
		{
			tx.begin();
			LabTestLocalMstDAO labTestLocalMstDAO=new LabTestLocalMstDAO(tx);
			testcombo=labTestLocalMstDAO.getTestBylabCode(labTestGlobalMstVO, _UserVO);
			//mp.put(InvestigationConfig.LIST_TEST_COMBO, testcombo);
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
		return testcombo;
	}

	public Map fetchLabTestGlobalData(LabTestGlobalMstVO labTestGlobalMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List testMethod=new ArrayList();
		List testcombo=new ArrayList();
		try
		{
			tx.begin();
			LabTestLocalMstDAO labTestLocalMstDAO=new LabTestLocalMstDAO(tx);
			testMethod=labTestLocalMstDAO.getTestMethodLocalCombo(labTestGlobalMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_METHOD_COMBO, testMethod);
			labTestLocalMstDAO.fetchLabTestGlobalData(labTestGlobalMstVO, _UserVO);
			
			//setting desk properties
			labTestGlobalMstVO.setIsOPDDoctorDesk(labTestGlobalMstVO.getDeskProperties().split("\\$")[0]);
			labTestGlobalMstVO.setIsOPDBayDoctorDesk(labTestGlobalMstVO.getDeskProperties().split("\\$")[1]);
			labTestGlobalMstVO.setIsIPDDoctorDesk(labTestGlobalMstVO.getDeskProperties().split("\\$")[2]);
			
			labTestLocalMstDAO.getLabComboLocal(labTestGlobalMstVO, _UserVO);

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

	public void saveLabTestLocalTest(LabTestGlobalMstVO labTestGlobalMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		String check="";
		try
		{
			tx.begin();
			LabTestLocalMstDAO labTestLocalMstDAO=new LabTestLocalMstDAO(tx);
			
			//setting desk properties
			labTestGlobalMstVO.setDeskProperties(labTestGlobalMstVO.getIsOPDDoctorDesk()+"$"+labTestGlobalMstVO.getIsOPDBayDoctorDesk()+"$"+labTestGlobalMstVO.getIsIPDDoctorDesk());
			
			
			
		check=labTestLocalMstDAO.checkDuplicateUserTestCode(labTestGlobalMstVO, _UserVO);
			
		
		if(check==null)
		{
			labTestLocalMstDAO.checkDuplicateData(labTestGlobalMstVO, _UserVO);
			if(labTestGlobalMstVO.getCount().equals("0"))
			{
				labTestLocalMstDAO.SaveLabTest(labTestGlobalMstVO, _UserVO);
			}
			if(labTestGlobalMstVO.getCount().equals("1"))
			{
				labTestLocalMstDAO.UpdateLabTest(labTestGlobalMstVO, _UserVO);
			}
		}else{
			throw new HisDuplicateRecordException("Test Code Already Exists");
		} 
		
		
		
		
			
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	@Override
	public Map fetchModifyLabTestGlobal(LabTestGlobalMstVO labTestMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();
		List testMethod=new ArrayList();
		List test=new ArrayList();
		try
		{
			tx.begin();
			LabTestGlobalMStDAO labTestGlobalMstDAO=new LabTestGlobalMStDAO(tx);
			labTestGlobalMstDAO.getModifyData(labTestMasterVO, _UserVO);
			//setting desk properties
			labTestMasterVO.setIsOPDDoctorDesk(labTestMasterVO.getDeskProperties().split("\\$")[0]);
			labTestMasterVO.setIsOPDBayDoctorDesk(labTestMasterVO.getDeskProperties().split("\\$")[1]);
			labTestMasterVO.setIsIPDDoctorDesk(labTestMasterVO.getDeskProperties().split("\\$")[2]);
			
			labTestGlobalMstDAO.getLabCombo(labTestMasterVO, _UserVO);
			labTestGlobalMstDAO.getTestName(labTestMasterVO, _UserVO);
			testMethod=labTestGlobalMstDAO.getTestMethodLocalCombo(labTestMasterVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_METHOD_COMBO, testMethod);
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
	public Map fetchModifyLabTestLocal(LabTestGlobalMstVO labTestMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();
		List testMethod=new ArrayList();
		List test=new ArrayList();
		try
		{
			tx.begin();
			LabTestGlobalMStDAO labTestLocalMstDAO=new LabTestGlobalMStDAO(tx);
			LabTestLocalMstDAO localLabTestMstDAO= new LabTestLocalMstDAO(tx);
			
			localLabTestMstDAO.getModifyDataLocal(labTestMasterVO, _UserVO);

			//setting desk properties
			labTestMasterVO.setIsOPDDoctorDesk(labTestMasterVO.getDeskProperties().split("\\$")[0]);
			labTestMasterVO.setIsOPDBayDoctorDesk(labTestMasterVO.getDeskProperties().split("\\$")[1]);
			labTestMasterVO.setIsIPDDoctorDesk(labTestMasterVO.getDeskProperties().split("\\$")[2]);
			
			
			labTestLocalMstDAO.getLabComboLocal(labTestMasterVO, _UserVO);
			labTestLocalMstDAO.getTestName(labTestMasterVO, _UserVO);
			testMethod=labTestLocalMstDAO.getTestMethodLocalCombo(labTestMasterVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_METHOD_COMBO, testMethod);
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

	public Map fetchLabTestSampleEssential(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List samplecombo=new ArrayList();
		List containercombo=new ArrayList();
		List UOMcombo=new ArrayList();
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO=new LabTestSampleMstDAO(tx);
			samplecombo=labTestSampleMstDAO.getSampleNameGlobal(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, samplecombo);
			containercombo=labTestSampleMstDAO.getContainerName(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_CONTAINER_COMBO, containercombo);
			UOMcombo=labTestSampleMstDAO.getUOMName(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, UOMcombo);
			labTestSampleMstDAO.getLabName(labTestSampleMstVO, _UserVO);
			labTestSampleMstDAO.getTestName(labTestSampleMstVO, _UserVO);
			labTestSampleMstVO.setIsDefaultSample("0");
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

	public void saveLabTestSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO = new LabTestSampleMstDAO(tx);
			int isDefaultSample=Integer.parseInt(labTestSampleMstVO.getIsDefaultSample());
			labTestSampleMstDAO.checkIsSampleDefaultGlobal(labTestSampleMstVO, _UserVO);
			if(Integer.parseInt(labTestSampleMstVO.getCount())>=isDefaultSample&&isDefaultSample==1)
			{
				tx.rollback();
				throw new HisDuplicateRecordException();
			}
			else
			{
				labTestSampleMstDAO.checkDuplicateRecord(labTestSampleMstVO, _UserVO);
				if(labTestSampleMstVO.getCount().equals("0"))
					labTestSampleMstDAO.saveLabTestSample(labTestSampleMstVO, _UserVO);
				else
					labTestSampleMstDAO.updateInsertData(labTestSampleMstVO, _UserVO);
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	public Map fetchModifyLabTestGlobal(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List containercombo=new ArrayList();
		List UOMcombo=new ArrayList();
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO=new LabTestSampleMstDAO(tx);
			labTestSampleMstDAO.fetchModifyLabTestGlobal(labTestSampleMstVO, _UserVO);
			containercombo=labTestSampleMstDAO.getContainerName(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_CONTAINER_COMBO, containercombo);
			UOMcombo=labTestSampleMstDAO.getUOMName(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, UOMcombo);
			labTestSampleMstDAO.getTestName(labTestSampleMstVO, _UserVO);

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

	public List getUOM(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List UOMcombo=new ArrayList();
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO=new LabTestSampleMstDAO(tx);
			UOMcombo=labTestSampleMstDAO.getUOM(labTestSampleMstVO, _UserVO);


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
		return UOMcombo;	

	}

	public String getContainerValues(String containerCode,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String strUOMCodeHashVolume="";
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO=new LabTestSampleMstDAO(tx);
			strUOMCodeHashVolume=labTestSampleMstDAO.getContainerValues(containerCode, _UserVO);


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
		return strUOMCodeHashVolume;	

	}
	public List getSampleQty(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List UOMcombo=new ArrayList();
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO=new LabTestSampleMstDAO(tx);
			UOMcombo=labTestSampleMstDAO.getSampleQty(labTestSampleMstVO, _UserVO);


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
		return UOMcombo;	

	}


	public void saveModifyLabTestSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO=new LabTestSampleMstDAO(tx);
			int isDefaultSample=Integer.parseInt(labTestSampleMstVO.getIsDefaultSample());
			labTestSampleMstDAO.checkIsSampleDefaultGlobal(labTestSampleMstVO, _UserVO);
			labTestSampleMstDAO.checkIsSampleDefaultGlobalSpecific(labTestSampleMstVO, _UserVO);
			if(Integer.parseInt(labTestSampleMstVO.getCountSpecific())==1&&Integer.parseInt(labTestSampleMstVO.getCount())==1&&isDefaultSample==0)
			{
				labTestSampleMstDAO.UpdateIsDefaultSampleGlobal(labTestSampleMstVO, _UserVO);
			}
			labTestSampleMstDAO.checkIsSampleDefaultGlobal(labTestSampleMstVO, _UserVO);
			labTestSampleMstDAO.checkIsSampleDefaultGlobalSpecific(labTestSampleMstVO, _UserVO);
			if(Integer.parseInt(labTestSampleMstVO.getCountSpecific())==0&&Integer.parseInt(labTestSampleMstVO.getCount())==1&&isDefaultSample==1)
			{

				tx.rollback();
				throw new HisDuplicateRecordException();

			}
			else
				labTestSampleMstDAO.saveModifyLabTestSample(labTestSampleMstVO, _UserVO);




		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	public Map fetchLabTestLocalSampleEssential(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List samplecombo=new ArrayList();
		List containercombo=new ArrayList();
		List UOMcombo=new ArrayList();
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO=new LabTestSampleMstDAO(tx);
			samplecombo=labTestSampleMstDAO.getSampleNameLocal(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, samplecombo);
			containercombo=labTestSampleMstDAO.getContainerName(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_CONTAINER_COMBO, containercombo);
			UOMcombo=labTestSampleMstDAO.getUOMName(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, UOMcombo);
			labTestSampleMstDAO.getLabName(labTestSampleMstVO, _UserVO);
			labTestSampleMstDAO.getTestName(labTestSampleMstVO, _UserVO);
			labTestSampleMstVO.setIsDefaultSample("0");
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

	public Map fetchGlobalData(LabTestSampleMstVO labTestSampleMstVO, UserVO _UserVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List samplecombo=new ArrayList();
		List containercombo=new ArrayList();
		List UOMcombo=new ArrayList();
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO=new LabTestSampleMstDAO(tx);
			labTestSampleMstDAO.fetchGlobalData(labTestSampleMstVO, _UserVO);
			samplecombo=labTestSampleMstDAO.getSampleNameLocal(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, samplecombo);
			containercombo=labTestSampleMstDAO.getContainerName(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_CONTAINER_COMBO, containercombo);
			UOMcombo=labTestSampleMstDAO.getUOMName(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, UOMcombo);
			labTestSampleMstDAO.getLabName(labTestSampleMstVO, _UserVO);
			labTestSampleMstDAO.getTestName(labTestSampleMstVO, _UserVO);

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


	public void saveLabTestLocalSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO = new LabTestSampleMstDAO(tx);
			int isDefaultSample=Integer.parseInt(labTestSampleMstVO.getIsDefaultSample());
			labTestSampleMstDAO.checkIsSampleDefaultLocal(labTestSampleMstVO, _UserVO);
			if(Integer.parseInt(labTestSampleMstVO.getCount())>=isDefaultSample&&isDefaultSample==1)
			{
				tx.rollback();
				throw new HisDuplicateRecordException();
			}
			else
			{
				labTestSampleMstDAO.checkDuplicateRecordLocal(labTestSampleMstVO, _UserVO);
				if(labTestSampleMstVO.getCount().equals("0"))
					labTestSampleMstDAO.saveLabTestLocalSample(labTestSampleMstVO, _UserVO);
				else
					labTestSampleMstDAO.updateInsertDataLocal(labTestSampleMstVO, _UserVO);	
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	public Map fetchModifyLabTestLocal(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List containercombo=new ArrayList();
		List UOMcombo=new ArrayList();
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO=new LabTestSampleMstDAO(tx);
			labTestSampleMstDAO.fetchModifyLabTestLocal(labTestSampleMstVO, _UserVO);
			containercombo=labTestSampleMstDAO.getContainerName(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_CONTAINER_COMBO, containercombo);
			UOMcombo=labTestSampleMstDAO.getUOMName(labTestSampleMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, UOMcombo);
			labTestSampleMstDAO.getTestName(labTestSampleMstVO, _UserVO);
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
	public void saveModifyLabTestLocalSample(LabTestSampleMstVO labTestSampleMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			LabTestSampleMstDAO labTestSampleMstDAO=new LabTestSampleMstDAO(tx);
			int isDefaultSample=Integer.parseInt(labTestSampleMstVO.getIsDefaultSample());
			labTestSampleMstDAO.checkIsSampleDefaultLocal(labTestSampleMstVO, _UserVO);
			labTestSampleMstDAO.checkIsSampleDefaultLocalSpecific(labTestSampleMstVO, _UserVO);
			if(Integer.parseInt(labTestSampleMstVO.getCountSpecific())==1&&Integer.parseInt(labTestSampleMstVO.getCount())==1&&isDefaultSample==0)
			{
				labTestSampleMstDAO.UpdateIsDefaultSampleLocal(labTestSampleMstVO, _UserVO);
			}
			labTestSampleMstDAO.checkIsSampleDefaultLocal(labTestSampleMstVO, _UserVO);
			labTestSampleMstDAO.checkIsSampleDefaultLocalSpecific(labTestSampleMstVO, _UserVO);
			if(Integer.parseInt(labTestSampleMstVO.getCountSpecific())==0&&Integer.parseInt(labTestSampleMstVO.getCount())==1&&isDefaultSample==1)
			{

				tx.rollback();
				throw new HisDuplicateRecordException();

			}
			else
				labTestSampleMstDAO.saveModifyLabTestSampleLocal(labTestSampleMstVO, _UserVO);

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	/*Test Mandatory Global Master */

	public Map fetchTestMandatory(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstTest = new ArrayList();


		try {
			tx.begin();
			TestMandatoryMstDAO testMandatoryMstDAO = new TestMandatoryMstDAO(tx);

			lstTest = testMandatoryMstDAO.getTestCombo(testMandatoryMstVO,_UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);



		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public Map getMandatory(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstArea = new ArrayList();
		List lstSelectedArea = new ArrayList();

		try {
			tx.begin();
			TestMandatoryMstDAO testMandatoryMstDAO = new TestMandatoryMstDAO(tx);

			lstArea = testMandatoryMstDAO.getnewareaComboLeft(testMandatoryMstVO,_UserVO);
			mp.put(InvestigationConfig.LIST_MANDATORY_COMBO, lstArea);

			lstSelectedArea = testMandatoryMstDAO.getselectedareaComboRight(testMandatoryMstVO,_UserVO);
			mp.put(InvestigationConfig.LIST_SELECTED_COMBO, lstSelectedArea);

		}/* catch (HisRecordNotFoundException e) {
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	}*/ catch (HisApplicationExecutionException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException();
	} catch (HisDataAccessException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisDataAccessException();
	} catch (HisException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisException();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisApplicationExecutionException();
	} finally {
		tx.close();
	}
		return mp;
	}

	public void saveTestMandatory(TestMandatoryMstVO[] insert_testMandatoryMstVO,TestMandatoryMstVO[] delete_testMandatoryMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();

			TestMandatoryMstDAO testMandatoryMstDAO = new TestMandatoryMstDAO(tx);


			//for inserting new records

			if(insert_testMandatoryMstVO!=null && insert_testMandatoryMstVO.length!=0){

				for(int i=0;i < insert_testMandatoryMstVO.length;i++){

					count = testMandatoryMstDAO.checkPrimaryKeyTestMandatoryGlobalMst(insert_testMandatoryMstVO[i],
							_UserVO);
					if (count.equals("0"))
						testMandatoryMstDAO.createTestMandatoryGlobalMst(insert_testMandatoryMstVO[i], _UserVO);
					else
						testMandatoryMstDAO.updateValidTestMandatoryGlobalMst(insert_testMandatoryMstVO[i], _UserVO);

				}
			}

			if(delete_testMandatoryMstVO!=null && delete_testMandatoryMstVO.length!=0){

				for(int i=0;i < delete_testMandatoryMstVO.length;i++)
					testMandatoryMstDAO.deleteTestMandatoryGlobalMst(delete_testMandatoryMstVO[i], _UserVO);

			}




		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}


	/*TEST MANDATORY LOCAL MASTER*/

	public Map fetchTestMandatoryLocal(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstTest = new ArrayList();


		try {
			tx.begin();
			TestMandatoryLocalMstDAO testMandatoryLocalMstDAO = new TestMandatoryLocalMstDAO(tx);

			lstTest = testMandatoryLocalMstDAO.getTestCombo(testMandatoryMstVO,_UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public Map getMandatoryLocal(TestMandatoryMstVO testMandatoryMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstArea = new ArrayList();
		List lstSelectedArea = new ArrayList();
		int count;
		try {
			tx.begin();
			TestMandatoryLocalMstDAO testMandatoryLocalMstDAO = new TestMandatoryLocalMstDAO(tx);
			TestMandatoryMstDAO testMandatoryMstDAO=new TestMandatoryMstDAO(tx);
			String count1=testMandatoryLocalMstDAO.checkLocallyMapped(testMandatoryMstVO, _UserVO);
			count=Integer.parseInt(count1);
			if(count>0)
			{
				lstArea = testMandatoryLocalMstDAO.getnewareaComboLeft(testMandatoryMstVO,_UserVO);
				mp.put(InvestigationConfig.LIST_MANDATORY_COMBO, lstArea);
				lstSelectedArea = testMandatoryLocalMstDAO.getselectedareaComboRight(testMandatoryMstVO,_UserVO);
				mp.put(InvestigationConfig.LIST_SELECTED_COMBO, lstSelectedArea);

			}
			if(count==0)
			{
				lstArea=testMandatoryMstDAO.getnewareaComboLeft(testMandatoryMstVO, _UserVO);
				mp.put(InvestigationConfig.LIST_MANDATORY_COMBO, lstArea);
				lstSelectedArea=testMandatoryMstDAO.getselectedareaComboRight(testMandatoryMstVO, _UserVO);
				mp.put(InvestigationConfig.LIST_SELECTED_COMBO, lstSelectedArea);
			}
			testMandatoryMstVO.setCount(count1);

		}/* catch (HisRecordNotFoundException e) {
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	}*/ catch (HisApplicationExecutionException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException();
	} catch (HisDataAccessException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisDataAccessException();
	} catch (HisException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisException();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisApplicationExecutionException();
	} finally {
		tx.close();
	}
		return mp;
	}

	public void saveTestMandatoryLocal(TestMandatoryMstVO[] insert_testMandatoryMstVO,TestMandatoryMstVO[] delete_testMandatoryMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();

			TestMandatoryLocalMstDAO testMandatoryLocalMstDAO = new TestMandatoryLocalMstDAO(tx);


			//for inserting new records

			if(insert_testMandatoryMstVO!=null && insert_testMandatoryMstVO.length!=0){

				for(int i=0;i < insert_testMandatoryMstVO.length;i++){

					count = testMandatoryLocalMstDAO.checkPrimaryKeyTestMandatoryGlobalMst(insert_testMandatoryMstVO[i],
							_UserVO);
					if (count.equals("0"))
						testMandatoryLocalMstDAO.createTestMandatoryGlobalMst(insert_testMandatoryMstVO[i], _UserVO);
					else
						testMandatoryLocalMstDAO.updateValidTestMandatoryGlobalMst(insert_testMandatoryMstVO[i], _UserVO);

				}
			}

			if(delete_testMandatoryMstVO!=null && delete_testMandatoryMstVO.length!=0){

				for(int i=0;i < delete_testMandatoryMstVO.length;i++)
					testMandatoryLocalMstDAO.deleteTestMandatoryGlobalMst(delete_testMandatoryMstVO[i], _UserVO);

			}

		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}
	///////////////////////////////////////////START TEST GROUP INFO LOCAL Master ///////////////////////////////////

	public void saveTestGroupInfoLocal(TestGroupInfoMasterVO[] insert_testgroupinfolocal_VO,TestGroupInfoMasterVO[] delete_testgroupinfolocal_VO, UserVO _UserVO,TestGroupInfoMasterVO[] modify_testgroupinfolocal_VO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		String count1="";
		TestGroupInfoLocalMstFB fb=new TestGroupInfoLocalMstFB();
		try {
			tx.begin();

			TestGroupInfoLocalMstDAOi testgroupinfolocal_DAOi = new TestGroupInfoLocalMstDAO(tx);


			//for inserting new records

			if(insert_testgroupinfolocal_VO!=null && insert_testgroupinfolocal_VO.length!=0){

				for(int i=0;i < insert_testgroupinfolocal_VO.length;i++){

					count1 = testgroupinfolocal_DAOi.checkUserGroupCodeTestGroupInfoLocal(insert_testgroupinfolocal_VO[i],
							_UserVO);
					
					
					
					count = testgroupinfolocal_DAOi.checkPrimaryKeyTestGroupInfoLocal(insert_testgroupinfolocal_VO[i],
							_UserVO);
					if (count.equals("0") )
					{
						if(count1.equals("0"))
						{
						testgroupinfolocal_DAOi.createTestGroupInfoLocal(insert_testgroupinfolocal_VO[i], _UserVO);
						}
						else
						{
							throw new HisDuplicateRecordException("User Group Code Already Exist");
						
						}
					
						
					}	else
					{
						if(count1.equals("0"))
						testgroupinfolocal_DAOi.updateValidTestGroupInfoLocal(insert_testgroupinfolocal_VO[i], _UserVO);
						else
							throw new HisDuplicateRecordException("User Group Code Already Exist");
					}	

				}
			}

			if(delete_testgroupinfolocal_VO!=null && delete_testgroupinfolocal_VO.length!=0){

				for(int i=0;i < delete_testgroupinfolocal_VO.length;i++)
					testgroupinfolocal_DAOi.deleteTestGroupInfoLocal(delete_testgroupinfolocal_VO[i], _UserVO);

			}
			
			
			if(modify_testgroupinfolocal_VO!=null && modify_testgroupinfolocal_VO.length!=0){

				for(int i=0;i < modify_testgroupinfolocal_VO.length;i++)
					testgroupinfolocal_DAOi.updateValidTestGroupInfoLocal(modify_testgroupinfolocal_VO[i], _UserVO);

			}




		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}



	public Map fetchTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();
		List lstGroup = new ArrayList();
		List lstPrintingTemplate = new ArrayList();

		try {
			tx.begin();
			TestGroupInfoLocalMstDAOi testgroupinfolocal_DAOi = new TestGroupInfoLocalMstDAO(tx);


			lstGroup = testgroupinfolocal_DAOi.getgroupCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_GROUP_COMBO, lstGroup);

			lstLab = testgroupinfolocal_DAOi.getlabCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			
			//to fetch unmapped templates combo
			/*lstPrintingTemplate = testgroupinfolocal_DAOi.getPrintingTemplateCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_PRINTING_TEMPLATE_COMBO, lstPrintingTemplate);*/
			
			if(testgroupinfolocal_VO.getHmode()!=null && (testgroupinfolocal_VO.getHmode().equals("MODIFY") || testgroupinfolocal_VO.getHmode().equals("CLEAR")) )
			{
				
				//to fetch mapped lists
				lstPrintingTemplate = testgroupinfolocal_DAOi.getPrintingTemplateMappedCombo(testgroupinfolocal_VO,_UserVO);
				mp.put(InvestigationConfig.LIST_PRINTING_TEMPLATE_COMBO, lstPrintingTemplate);
				
				
				testgroupinfolocal_DAOi.fetchParameter(testgroupinfolocal_VO, _UserVO);
			}
			
			else
			{}


		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}



	public Map getTestLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstTest = new ArrayList();
		List lstSelectedTest = new ArrayList();
		List lstPrintingTemplate = new ArrayList();
		List<TestGroupInfoMasterVO>  lstTestSeqNo=new ArrayList<TestGroupInfoMasterVO>();

		try {
			tx.begin();
			TestGroupInfoLocalMstDAOi testgroupinfolocal_DAOi = new TestGroupInfoLocalMstDAO(tx);
			TestGroupInfoMstDAOi testgroupinfo_DAOi = new TestGroupInfoMstDAO(tx);
			testgroupinfolocal_VO.setCheckLocal("1");
			lstSelectedTest = testgroupinfolocal_DAOi.getselectedtestComboRight(testgroupinfolocal_VO,_UserVO,lstTestSeqNo);
			
			if(lstSelectedTest != null)
			{
				testgroupinfolocal_VO.setCheckLocal("1");
				mp.put(InvestigationConfig.LIST_SELECTED_COMBO, lstSelectedTest);
				lstTest = testgroupinfolocal_DAOi.gettestComboLeft(testgroupinfolocal_VO,_UserVO);
				mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
				testgroupinfolocal_DAOi.fetchParameter(testgroupinfolocal_VO, _UserVO);
				mp.put(InvestigationConfig.LIST_TEST_SEQ_NO, lstTestSeqNo);
			}

			else
			{
				testgroupinfolocal_VO.setCheckLocal("0");	
				lstTestSeqNo=new ArrayList<TestGroupInfoMasterVO>();
				lstTest = testgroupinfolocal_DAOi.gettestComboLeft(testgroupinfolocal_VO,_UserVO);
				mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);

				lstSelectedTest = testgroupinfolocal_DAOi.getselectedtestComboRight(testgroupinfolocal_VO,_UserVO,lstTestSeqNo);
				mp.put(InvestigationConfig.LIST_SELECTED_COMBO, lstSelectedTest);
				mp.put(InvestigationConfig.LIST_TEST_SEQ_NO, lstTestSeqNo);
				testgroupinfolocal_DAOi.fetchParameter(testgroupinfolocal_VO, _UserVO);
				
				

			}

			
			
			//get the mapped global template into the list
			lstPrintingTemplate = testgroupinfolocal_DAOi.getPrintingTemplateMappedCombo(testgroupinfolocal_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_PRINTING_TEMPLATE_COMBO, lstPrintingTemplate);


		} /*catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
} 	*/	catch (HisApplicationExecutionException e) {
	tx.rollback();
	System.out.println(e.getMessage());
	throw new HisApplicationExecutionException();
} catch (HisDataAccessException e) {
	tx.rollback();
	System.out.println(e.getMessage());
	throw new HisDataAccessException();
} catch (HisException e) {
	tx.rollback();
	System.out.println(e.getMessage());
	throw new HisException();
} catch (Exception e) {
	System.out.println(e.getMessage());
	tx.rollback();
	throw new HisApplicationExecutionException();
} finally {
	tx.close();
}
		return mp;
	}
	// //END TEST GROUP INFO LOCAL Master ///
	///////////////////////////////////////////START Lab Number Configuration Master ///////////////////////////////////

	public void saveLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList = "";
		try {
			tx.begin();
			LabNoConfigMstDAOi labnoconfig_DAOi = new LabNoConfigMstDAO(tx);

				checkList = labnoconfig_DAOi.checkDuplicateLabNoConfig(labnoconfig_VO,
					_UserVO);
			if (checkList == null) {
			labnoconfig_DAOi.createLabNoConfig(labnoconfig_VO, _UserVO);
			} else {
				throw new HisDuplicateRecordException(
						"Data Already Exists");
			}
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public Map fetchCheckListLabNoConfig(LabNoConfigMasterVO labnoconfig_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		String part[]={};
		List lstLab = new ArrayList();
		List lstTest = new ArrayList();
		List lstlocLab = new ArrayList();
		int len=0;
		String series="";


		try {
			tx.begin();
			LabNoConfigMstDAOi labnoconfig_DAOi = new LabNoConfigMstDAO(tx);
			LabNoConfigMstDAO labnoconfig_DAO = new LabNoConfigMstDAO(tx);
			labnoconfig_DAOi.fetchCheckListLabNoConfig(labnoconfig_VO, _UserVO);

			//break lab no format into part one and two

			if(!(labnoconfig_VO.getLabNoFormat().equals("-")))
			{
				part=labnoconfig_VO.getLabNoFormat().split("&");
				labnoconfig_VO.setPartOne(part[0]);
				labnoconfig_VO.setPartTwo(part[0]+part[1]);

				len=part[1].length();

				if(len > Integer.parseInt(labnoconfig_VO.getSeqDigit()))
				{
					series=part[1].substring(0,len-Integer.parseInt(labnoconfig_VO.getSeqDigit()));
					labnoconfig_VO.setSeriesFormat(series);
				}	
			}


			lstLab = labnoconfig_DAOi.getlabCombo(_UserVO);
			lstTest = labnoconfig_DAOi.gettestCombo(_UserVO);
			lstlocLab =labnoconfig_DAO.getSampleCollectionArea(_UserVO);
			
			
			mp.put(InvestigationConfig.LIST_SAMPLE_COLLECTION_VO, lstlocLab);
			
			
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public Map fetchLabNoConfig(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();
		List lstlocLab = new ArrayList();
		List lstTest = new ArrayList();
		List<Inv_SampleCollectionVO> lstinvSampleCollectionVO= null;
		lstinvSampleCollectionVO=	new ArrayList<Inv_SampleCollectionVO>();

		try {
			tx.begin();
			LabNoConfigMstDAOi labnoconfig_DAOi = new LabNoConfigMstDAO(tx);
			LabNoConfigMstDAO labnoconfig_DAO = new LabNoConfigMstDAO(tx);

			lstLab = labnoconfig_DAOi.getlabCombo(_UserVO);
		
			
			lstlocLab =labnoconfig_DAO.getSampleCollectionArea(_UserVO);
			
			
			
			
			//lstTest = labnoconfig_DAOi.gettestCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
			mp.put(InvestigationConfig.LIST_SAMPLE_COLLECTION_VO, lstlocLab);
			labnoconfig_VO.setInitializationType("d");
			labnoconfig_VO.setPatientType("3");
			labnoconfig_VO.setAcceptanceAreawise("0");

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public void savemodifyLabNoConfig(LabNoConfigMasterVO labnoconfig_VO,
			UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		String checkList = "";
		try {
			tx.begin();
			LabNoConfigMstDAOi labnoconfig_DAOi = new LabNoConfigMstDAO(tx);

			/*if(labnoconfig_VO.getAcceptanceAreawise().equals("1"))
			checkList = labnoconfig_DAOi.checkModifyDuplicateLabNoConfig(labnoconfig_VO,_UserVO);
			
			if(labnoconfig_VO.getAcceptanceAreawise().equals("0"))
			checkList = labnoconfig_DAOi.checkModifyDuplicateLabNoConfigWithoutAreaWise(labnoconfig_VO,_UserVO);
			
			
			if (checkList == null) {
				labnoconfig_DAOi.updateLabNoConfig(labnoconfig_VO, _UserVO);
			} else {
				throw new HisDuplicateRecordException(
						"Data Already Exists");
			
			}*/

			labnoconfig_DAOi.updateLabNoConfig(labnoconfig_VO, _UserVO);



		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public Map getTest(LabNoConfigMasterVO labnoconfig_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstTest = new ArrayList();


		try {
			tx.begin();
			LabNoConfigMstDAOi labnoconfig_DAOi = new LabNoConfigMstDAO(tx);

			lstTest = labnoconfig_DAOi.getTest(labnoconfig_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
			labnoconfig_VO.setInitializationType("d");

		}	 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	///////////////////////////////////////////START LAB COLLECTION AREA Master ///////////////////////////////////

	public void saveLabCollectionArea(LabCollectionAreaMasterVO[] insert_labcollectionarea_VO,LabCollectionAreaMasterVO[] delete_labcollectionarea_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();

			LabCollectionAreaMstDAOi labcollectionarea_DAOi = new LabCollectionAreaMstDAO(tx);


			//for inserting new records

			if(insert_labcollectionarea_VO!=null && insert_labcollectionarea_VO.length!=0){

				for(int i=0;i < insert_labcollectionarea_VO.length;i++){

					count = labcollectionarea_DAOi.checkPrimaryKeyLabCollectionArea(insert_labcollectionarea_VO[i],
							_UserVO);
					if (count.equals("0"))
						labcollectionarea_DAOi.createLabCollectionArea(insert_labcollectionarea_VO[i], _UserVO);
					else
						labcollectionarea_DAOi.updateValidLabCollectionArea(insert_labcollectionarea_VO[i], _UserVO);

				}
			}

			if(delete_labcollectionarea_VO!=null && delete_labcollectionarea_VO.length!=0){

				for(int i=0;i < delete_labcollectionarea_VO.length;i++)
					labcollectionarea_DAOi.deleteLabCollectionArea(delete_labcollectionarea_VO[i], _UserVO);

			}




		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}


	public Map fetchLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();


		try {
			tx.begin();
			LabCollectionAreaMstDAOi labcollectionarea_DAOi = new LabCollectionAreaMstDAO(tx);

			lstLab = labcollectionarea_DAOi.getlabCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);



		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}



	public Map getArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstArea = new ArrayList();
		List lstSelectedArea = new ArrayList();

		try {
			tx.begin();
			LabCollectionAreaMstDAOi labcollectionarea_DAOi = new LabCollectionAreaMstDAO(tx);

			lstArea = labcollectionarea_DAOi.getnewareaComboLeft(labcollectionarea_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_AREA_COMBO, lstArea);

			lstSelectedArea = labcollectionarea_DAOi.getselectedareaComboRight(labcollectionarea_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_SELECTED_COMBO, lstSelectedArea);

		}/* catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}*/ catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	// //END Lab CollectionArea Master ///

	///////////////////////////////////////////START TEST GROUP INFO Master ///////////////////////////////////

	public void saveTestGroupInfo(TestGroupInfoMasterVO[] insert_testgroupinfo_VO,TestGroupInfoMasterVO[] delete_testgroupinfo_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();

			TestGroupInfoMstDAOi testgroupinfo_DAOi = new TestGroupInfoMstDAO(tx);


			//for inserting new records

			if(insert_testgroupinfo_VO!=null && insert_testgroupinfo_VO.length!=0){

				for(int i=0;i < insert_testgroupinfo_VO.length;i++){

					count = testgroupinfo_DAOi.checkPrimaryKeyTestGroupInfo(insert_testgroupinfo_VO[i],
							_UserVO);
					if (count.equals("0"))
						testgroupinfo_DAOi.createTestGroupInfo(insert_testgroupinfo_VO[i], _UserVO);
					else
						testgroupinfo_DAOi.updateValidTestGroupInfo(insert_testgroupinfo_VO[i], _UserVO);

				}
			}

			if(delete_testgroupinfo_VO!=null && delete_testgroupinfo_VO.length!=0){

				for(int i=0;i < delete_testgroupinfo_VO.length;i++)
					testgroupinfo_DAOi.deleteTestGroupInfo(delete_testgroupinfo_VO[i], _UserVO);

			}




		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}



	public Map fetchTestGroupInfo(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();
		List lstGroup = new ArrayList();
		List lstPrintingTemplate = new ArrayList();
	

		try {
			tx.begin();
			TestGroupInfoMstDAOi testgroupinfo_DAOi = new TestGroupInfoMstDAO(tx);


			lstGroup = testgroupinfo_DAOi.getgroupCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_GROUP_COMBO, lstGroup);

			lstLab = testgroupinfo_DAOi.getlabCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			
			lstPrintingTemplate = testgroupinfo_DAOi.getPrintingTemplateCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_PRINTING_TEMPLATE_COMBO, lstPrintingTemplate);
	
			if(testgroupinfo_VO.getHmode()!=null && (testgroupinfo_VO.getHmode().equals("MODIFY") || testgroupinfo_VO.getHmode().equals("CLEAR")) )
			{
			testgroupinfo_DAOi.fetchParameter(testgroupinfo_VO, _UserVO);
			}
			
			else
			{}
		

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}



	public Map getTest(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstTest = new ArrayList();
		List lstSelectedTest = new ArrayList();

		try {
			tx.begin();
			TestGroupInfoMstDAOi testgroupinfo_DAOi = new TestGroupInfoMstDAO(tx);

			lstTest = testgroupinfo_DAOi.gettestComboLeft(testgroupinfo_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);

			lstSelectedTest = testgroupinfo_DAOi.getselectedtestComboRight(testgroupinfo_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_SELECTED_COMBO, lstSelectedTest);
			
			//testgroupinfo_DAOi.fetchParameter(testgroupinfo_VO, _UserVO);

		}	 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	
	
	// //END TEST GROUP INFO Master ///


	//////////////////////////////////////////START Rejection Reason MASTER(added by yogender yadav)///////////////////////////////////

	public void saveRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			RejectionReasonMstDAOi RejectionReasonMstDAOi = new RejectionReasonMstDAO(tx);
			checkList=RejectionReasonMstDAOi.checkDuplicateRejectionReason(rejectionreasonmaster_VO, _UserVO);

			if(checkList==null)
			{
				RejectionReasonMstDAOi.createRejectionReason(rejectionreasonmaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	public void fetchCheckListRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLoinic=new ArrayList();
		try
		{
			tx.begin();
			RejectionReasonMstDAOi RejectionReasonMstDAOi = new RejectionReasonMstDAO(tx);
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

	}

	public void fetchRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			RejectionReasonMstDAOi RejectionReasonMstDAOi = new RejectionReasonMstDAO(tx);
			RejectionReasonMstDAOi.fetchRejectionReason(rejectionreasonmaster_VO, _UserVO);
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
	}

	public void savemodifyRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			RejectionReasonMstDAOi RejectionReasonMstDAOi = new RejectionReasonMstDAO(tx);
			count=RejectionReasonMstDAOi.checkDuplicateModifyRejectionReason(rejectionreasonmaster_VO, _UserVO);
			if(count.equals("0"))
			{
				RejectionReasonMstDAOi.updateRejectionReason(rejectionreasonmaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	////////////////////////////////////////END RejectionReason MASTER/////////////////////////////////////////

	//////////////////////////////////////////START Global Test Group MASTER(added by yogender yadav)///////////////////////////////////

	public void saveTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			TestGroupMstDAOi testgroupmstdaoi = new TestGroupMstDAO(tx);
			checkList=testgroupmstdaoi.checkDuplicateTestGroup(testgroupmaster_VO, _UserVO);

			if(checkList==null)
			{
				testgroupmstdaoi.createTestGroup(testgroupmaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	public void modifyTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLoinic=new ArrayList();
		try
		{
			tx.begin();
			TestGroupMstDAOi testgroupmstdaoi = new TestGroupMstDAO(tx);
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

	}

	public void fetchTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			TestGroupMstDAOi testgroupmstdaoi = new TestGroupMstDAO(tx);
			testgroupmstdaoi.fetchTestGroup(testgroupmaster_VO, _UserVO);
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
	}

	public void updateTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			TestGroupMstDAOi testgroupmstdaoi = new TestGroupMstDAO(tx);
			count=testgroupmstdaoi.checkDuplicateModifyTestGroup(testgroupmaster_VO, _UserVO);
			if(count.equals("0"))
			{
				testgroupmstdaoi.updateTestGroup(testgroupmaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	////////////////////////////////////////END  Global Test Group MASTER/////////////////////////////////////////

	//////////////////////////////////////////START Local Test Group MASTER(added by yogender yadav)///////////////////////////////////

	public void saveLocalTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			LocalTestGroupMstDAOi localtestgroupmstdaoi = new LocalTestGroupMstDAO(tx);
			checkList=localtestgroupmstdaoi.checkDuplicateLocalTestGroup(testgroupmaster_VO, _UserVO);

			if(checkList==null)
			{
				localtestgroupmstdaoi.createLocalTestGroup(testgroupmaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	public void modifyLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLoinic=new ArrayList();
		try
		{
			tx.begin();
			LocalTestGroupMstDAOi localtestgroupmstdaoi = new LocalTestGroupMstDAO(tx);
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

	}

	public Map fetchCheckListLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		Map mp = new HashMap();
		List lstLab = new ArrayList();
		try
		{
			tx.begin();

			LocalTestGroupMstDAOi localtestgroupmstdaoi = new LocalTestGroupMstDAO(tx);
			localtestgroupmstdaoi.fetchCheckListLocalTestGroup(testgroupmaster_VO, _UserVO);
			lstLab = localtestgroupmstdaoi.gettestgroupCombo(testgroupmaster_VO,_UserVO);
			String prefVal = testgroupmaster_VO.getPreferenceOrder();    //Harshita
			mp.put(InvestigationConfig.LIST_TESTGROUP_COMBO, lstLab);


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

	public Map fetchLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();


		try {
			tx.begin();
			LocalTestGroupMstDAOi localtestgroupmstdaoi = new LocalTestGroupMstDAO(tx);

			lstLab = localtestgroupmstdaoi.gettestgroupCombo(testgroupmaster_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_TESTGROUP_COMBO, lstLab);
			testgroupmaster_VO.setGroupType("1"); //set value on radio button default
			testgroupmaster_VO.setIsprint("0");
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public void updateLocalTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			LocalTestGroupMstDAOi localtestgroupmstdaoi = new LocalTestGroupMstDAO(tx);
			count=localtestgroupmstdaoi.checkDuplicateModifyLocalTestGroup(testgroupmaster_VO, _UserVO);
			if(count.equals("0"))
			{
				localtestgroupmstdaoi.updateLocalTestGroup(testgroupmaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	public void fetchdisplaydataLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			LocalTestGroupMstDAOi localtestgroupmstdaoi = new LocalTestGroupMstDAO(tx);
			localtestgroupmstdaoi.fetchdisplaydataLocalTestGroup(testgroupmaster_VO, _UserVO);
			testgroupmaster_VO.setGroupType("1"); //set value on radio button default
			testgroupmaster_VO.setIsprint("0");
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
	}

	////////////////////////////////////////END Local Test Group MASTER/////////////////////////////////////////


	//////////////////////////////////////////START Canned MASTER(added by yogender yadav)///////////////////////////////////

	public void saveCanned(CannedMasterVO cannedmaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			CannedMstDAOi cannedmstdaoi = new CannedMstDAO(tx);
			checkList=cannedmstdaoi.checkDuplicateCanned(cannedmaster_VO, _UserVO);

			if(checkList==null)
			{
				cannedmstdaoi.createCanned(cannedmaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	public void modifyCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLoinic=new ArrayList();
		try
		{
			tx.begin();
			CannedMstDAOi cannedmstdaoi = new CannedMstDAO(tx);
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

	}

	public void fetchCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			CannedMstDAOi cannedmstdaoi = new CannedMstDAO(tx);
			cannedmstdaoi.fetchCanned(cannedmaster_VO, _UserVO);
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
	}

	public void updateCanned(CannedMasterVO cannedmaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			CannedMstDAOi cannedmstdaoi = new CannedMstDAO(tx);
			count=cannedmstdaoi.checkDuplicateModifyCanned(cannedmaster_VO, _UserVO);
			if(count.equals("0"))
			{
				cannedmstdaoi.updateCanned(cannedmaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	////////////////////////////////////////END Canned MASTER/////////////////////////////////////////


	//////////////////////////////////////////START Lab Macro Local Mapping  MASTER(added by yogender yadav)///////////////////////////////////


	public void saveLabMacroLocalMap(LabMacroMapMasterVO[] insert_labmacromap_VO,LabMacroMapMasterVO[] delete_labmacromap_VO, UserVO _UserVO,LabMacroMapMasterVO[] modify_labmacromap_VO ) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();

			LabMacroLocalMapMstDAOi labmacromap_DAOi = new LabMacroLocalMapMstDAO(tx);


			//for inserting new records

			if(insert_labmacromap_VO!=null && insert_labmacromap_VO.length!=0){

				for(int i=0;i < insert_labmacromap_VO.length;i++){

					count = labmacromap_DAOi.checkPrimaryKeyLabMacroMap(insert_labmacromap_VO[i],
							_UserVO);
					if (count.equals("0"))
					{labmacromap_DAOi.createLabMacroMap(insert_labmacromap_VO[i], _UserVO);
					/* labmacromap_DAOi.insertLaboratoryMacroMap(insert_labmacromap_VO[i], _UserVO);*/
					}
					else
					{labmacromap_DAOi.updateValidLabMacroMap(insert_labmacromap_VO[i], _UserVO);
					/* labmacromap_DAOi.updateValidLaboratoryMacroMap(insert_labmacromap_VO[i],  _UserVO);*/}
				}
			}

			if(delete_labmacromap_VO!=null && delete_labmacromap_VO.length!=0){

				for(int i=0;i < delete_labmacromap_VO.length;i++)
					labmacromap_DAOi.deleteLabMacroMap(delete_labmacromap_VO[i], _UserVO);

			}

			
			if(modify_labmacromap_VO!=null && modify_labmacromap_VO.length!=0){

				for(int i=0;i < modify_labmacromap_VO.length;i++)
					labmacromap_DAOi.updateValidLabMacroMap(modify_labmacromap_VO[i], _UserVO);

			}



		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}


	public Map fetchLabMacroLocalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();


		try {
			tx.begin();
			LabMacroLocalMapMstDAOi labmacromap_DAOi = new LabMacroLocalMapMstDAO(tx);

			lstLab = labmacromap_DAOi.getlabCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);



		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}


	public Map getLocalMacro(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		
		List<LabMacroMapMasterVO> labmacro_listVO = new ArrayList<LabMacroMapMasterVO>();

		List lstArea = new ArrayList();
		List lstSelectedArea = new ArrayList();
		int macro;

		try {
			tx.begin();
			LabMacroLocalMapMstDAOi labmacromap_DAOi = new LabMacroLocalMapMstDAO(tx);
			labmacromap_DAOi.fetchdisplaydataMacroLocalMap(labmacromap_VO, _UserVO);
			LabMacroLocalMapMstDAO labMacroLocalMapMstDAO=new LabMacroLocalMapMstDAO(tx);
			LabMacroGlobalMapMstDAO labMacroGlobalMapMstDAO=new LabMacroGlobalMapMstDAO(tx);
			String count=labMacroLocalMapMstDAO.checkLocallyMapped(labmacromap_VO, _UserVO);
			macro=Integer.parseInt(count);
			if(macro>0)
			{
				lstArea = labmacromap_DAOi.getnewmacroComboLeft(labmacromap_VO,_UserVO);
				mp.put(InvestigationConfig.LIST_MACRO_COMBO, lstArea);
				lstSelectedArea = labmacromap_DAOi.getselectedmacroComboRight(labmacromap_VO,_UserVO,labmacro_listVO);
				mp.put(InvestigationConfig.LIST_SELECTED_MACRO_COMBO, lstSelectedArea);
				mp.put(InvestigationConfig.LIST_SELECTED_MACRO_COMBO_CODES, labmacro_listVO);
			}
			if(macro==0)
			{
				labmacro_listVO = new ArrayList<LabMacroMapMasterVO>();
				lstArea=labMacroGlobalMapMstDAO.getnewmacroglobalComboLeft(labmacromap_VO, _UserVO);
				mp.put(InvestigationConfig.LIST_MACRO_COMBO, lstArea);
				lstSelectedArea=labMacroGlobalMapMstDAO.getselectedmacroglobalComboRight(labmacromap_VO, _UserVO,labmacro_listVO);
				mp.put(InvestigationConfig.LIST_SELECTED_MACRO_COMBO, lstSelectedArea);
				mp.put(InvestigationConfig.LIST_SELECTED_MACRO_COMBO_CODES, labmacro_listVO);
			}
			labmacromap_VO.setCount(count);

		}
		catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	/*public void fetchdisplaydataMacroLocalMap(LabMacroMapMasterVO labmacromapmaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			LabMacroLocalMapMstDAOi labmacromap_DAOi = new LabMacroLocalMapMstDAO(tx);

			LabMacroLocalMapMstDAOi.fetchdisplaydataMacroLocalMap(labmacromapmaster_VO, _UserVO);

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
	}*/


	////////////////////////////////////////END Lab Macro Local Mapping MASTER/////////////////////////////////////////

	//////////////////////////////////////////START Lab Macro Global Mapping  MASTER(added by yogender yadav)///////////////////////////////////


	public void saveLabMacroGlobalMap(LabMacroMapMasterVO[] insert_labmacromap_VO,LabMacroMapMasterVO[] delete_labmacromap_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();

			LabMacroGlobalMapMstDAOi labmacromap_DAOi = new LabMacroGlobalMapMstDAO(tx);


			//for inserting new records

			if(insert_labmacromap_VO!=null && insert_labmacromap_VO.length!=0){

				for(int i=0;i < insert_labmacromap_VO.length;i++){

					count = labmacromap_DAOi.checkPrimaryKeyLabMacroGlobalMap(insert_labmacromap_VO[i],
							_UserVO);
					if (count.equals("0"))
						labmacromap_DAOi.createLabMacroGlobalMap(insert_labmacromap_VO[i], _UserVO);
					else
						labmacromap_DAOi.updateValidLabMacroGlobalMap(insert_labmacromap_VO[i], _UserVO);

				}
			}

			if(delete_labmacromap_VO!=null && delete_labmacromap_VO.length!=0){

				for(int i=0;i < delete_labmacromap_VO.length;i++)
					labmacromap_DAOi.deleteLabMacroGlobalMap(delete_labmacromap_VO[i], _UserVO);

			}




		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}
	/*USER BOOKMARK MASTER */

	public Map getEssential(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstUser = new ArrayList();
		List lstDept = new ArrayList();
		List lstunit = new ArrayList();
		List lstLab = new ArrayList();
		List lstTest = new ArrayList();
		List lstTestGroup = new ArrayList();
		List lstBookMark = new ArrayList();
		try {
			tx.begin();
			UserBookMarkMstDAO userBookMarkMstDAO = new UserBookMarkMstDAO(tx);
			
			lstBookMark = userBookMarkMstDAO.getBookMarkCombo(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_BOOKMARK_COMBO, lstBookMark);
			
			lstUser = userBookMarkMstDAO.getUserCombo(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_USER_COMBO, lstUser);

			lstDept = userBookMarkMstDAO.getDeptCombo(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_DEPT_COMBO, lstDept);

			lstLab = userBookMarkMstDAO.getLabCombo(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);

			lstTest = userBookMarkMstDAO.getTestCombo1(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);

			lstTestGroup = userBookMarkMstDAO.getTestGroupCombo(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_GROUP_COMBO, lstTestGroup);
			
			userBookMarkMstVO.setIsTestGroup("0");
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	public Map getBookMarkType(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstUser = new ArrayList();
		List lstDept = new ArrayList();
		List lstunit = new ArrayList();
		List lstLab = new ArrayList();
		List lstTest = new ArrayList();
		List lstTestGroup = new ArrayList();
		List lstBookMark = new ArrayList();
		try {
			tx.begin();
			UserBookMarkMstDAO userBookMarkMstDAO = new UserBookMarkMstDAO(tx);
			
			lstBookMark = userBookMarkMstDAO.getBookMarkCombo(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_BOOKMARK_COMBO, lstBookMark);
			
			lstUser = userBookMarkMstDAO.getUserCombo(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_USER_COMBO, lstUser);

			lstDept = userBookMarkMstDAO.getDeptCombo(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_DEPT_COMBO, lstDept);

			lstLab = userBookMarkMstDAO.getLabCombo(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);

			lstTest = userBookMarkMstDAO.getTestCombo1(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);

			lstTestGroup = userBookMarkMstDAO.getTestGroupCombo(userBookMarkMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_GROUP_COMBO, lstTestGroup);
			String bookmarkType=userBookMarkMstDAO.getBookMarkType(userBookMarkMstVO, _UserVO);
			userBookMarkMstVO.setBookarkType(bookmarkType);
			userBookMarkMstVO.setIsTestGroup("0");
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	public List getUnit(String deptCode,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List unit=new ArrayList();
		try
		{
			tx.begin();
			UserBookMarkMstDAO userBookMarkMstDAO=new UserBookMarkMstDAO(tx);
			unit=userBookMarkMstDAO.getUnitCombo(deptCode, _UserVO);


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
		return unit;
	}
	public List getTest(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List unit=new ArrayList();
		try
		{
			tx.begin();
			UserBookMarkMstDAO userBookMarkMstDAO=new UserBookMarkMstDAO(tx);
			unit=userBookMarkMstDAO.getTestCombo(userBookMarkMstVO, _UserVO);
			//by prashant
			//unit=userBookMarkMstDAO.getTestBylabCode(userBookMarkMstVO, _UserVO);

			
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
		return unit;
	}
	
	//  by prashant to fetch all test in group and also check duplicate test in bookmark
	public List<UserBookMarkMstVO> getTestByGroup(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<UserBookMarkMstVO> test=new ArrayList<UserBookMarkMstVO>();
		List duplicateTest=new ArrayList();
		StringBuffer strduplicateTest = new StringBuffer();
		try
		{
			tx.begin();
			UserBookMarkMstDAO userBookMarkMstDAO=new UserBookMarkMstDAO(tx);
			test=userBookMarkMstDAO.getTestByGroup(userBookMarkMstVO, _UserVO);
			
			strduplicateTest.append("(");
			for(int i=0; i<test.size(); i++)
			 {
				strduplicateTest.append(test.get(i).getTestCode()+",");
			 }
			strduplicateTest.append("0)");
			
			duplicateTest=userBookMarkMstDAO.getDuplicateTestInBookmark(userBookMarkMstVO, _UserVO, strduplicateTest);
			if(duplicateTest.isEmpty() || duplicateTest.size()<1 ) {}
			else {return duplicateTest;}
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
		return test;
	}
	
	public void saveUserBookMark(UserBookMarkMstVO userBookMarkMstVO,List<UserBookMarkMstVO> lstUserBookMarkVO,UserVO _UserVO) 
    { 
        JDBCTransactionContext tx = new JDBCTransactionContext(); 
        String checkList=""; 
        try 
        { 
            tx.begin(); 
            UserBookMarkMstDAO userBookMarkMstDAO = new UserBookMarkMstDAO(tx); 
            if(lstUserBookMarkVO!=null)
            {
            	if(lstUserBookMarkVO.isEmpty()==false)
            	{
            for(int i=0;i<lstUserBookMarkVO.size();i++) 
            { 
            	//added by prashant
                UserBookMarkMstVO UserBookmVO=(UserBookMarkMstVO)lstUserBookMarkVO.get(i); 
              
                userBookMarkMstVO.setLabCode(UserBookmVO.getLabCode());
                userBookMarkMstVO.setTestCode(UserBookmVO.getTestCode());
                userBookMarkMstVO.setGroupCode(UserBookmVO.getGroupCode());
                userBookMarkMstVO.setIsTestGroup(UserBookmVO.getIsTestGroup());
                
                userBookMarkMstDAO.saveUserBookMark(userBookMarkMstVO, _UserVO);
                		//commented by prashant
						/*
						 * if(UserBookmVO.getTestCode()!=null) {
						 * if(UserBookmVO.getTestCode().equals("-1")==false) {
						 * userBookMarkMstVO.setLabCode(UserBookmVO.getLabCode());
						 * if(userBookMarkMstVO.getIsTestGroup().equals("0")) {
						 * userBookMarkMstVO.setTestCode(UserBookmVO.getTestCode()); }
						 * if(userBookMarkMstVO.getIsTestGroup().equals("1")) {
						 * userBookMarkMstVO.setGroupCode(UserBookmVO.getGroupCode()); }
						 * userBookMarkMstDAO.saveUserBookMark(userBookMarkMstVO, _UserVO); } } else {
						 * userBookMarkMstVO.setLabCode(UserBookmVO.getLabCode());
						 * if(userBookMarkMstVO.getIsTestGroup().equals("0")) {
						 * userBookMarkMstVO.setTestCode(UserBookmVO.getTestCode()); }
						 * if(userBookMarkMstVO.getIsTestGroup().equals("1")) {
						 * userBookMarkMstVO.setGroupCode(UserBookmVO.getGroupCode()); }
						 * userBookMarkMstDAO.saveUserBookMark(userBookMarkMstVO, _UserVO); }
						 */
            } 
          }
            }
            //userBookMarkMstDAO.saveUserBookMark(userBookMarkMstVO, _UserVO);
        } 
        catch (HisDuplicateRecordException e) 
        { 
            tx.rollback(); 
            throw new HisDuplicateRecordException(e.getMessage()); 
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
    } 
	public void getTestName(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO) 
    { 
        JDBCTransactionContext tx = new JDBCTransactionContext(); 
        String checkList=""; 
        try 
        { 
            tx.begin(); 
            UserBookMarkMstDAO userBookMarkMstDAO = new UserBookMarkMstDAO(tx); 
            userBookMarkMstDAO.getTestName(userBookMarkMstVO, _UserVO);

        } 
        catch (HisDuplicateRecordException e) 
        { 
            tx.rollback(); 
            throw new HisDuplicateRecordException(e.getMessage()); 
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
    } 

	public Map fetchModifyUserBookMark(UserBookMarkMstVO userBookMarkMstVO, UserVO _UserVO) { 
	    JDBCTransactionContext tx = new JDBCTransactionContext(); 
	    Map mp = new HashMap(); 
	     
	    List lstUser = new ArrayList(); 
	    List lstDept = new ArrayList(); 
	    List lstunit = new ArrayList(); 
	    List lstLab = new ArrayList(); 
	    List lstTest = new ArrayList(); 
	    List lstTestGroup = new ArrayList(); 
	    List lstBookMark = new ArrayList(); 
	    try { 
	        tx.begin(); 
	        UserBookMarkMstDAO userBookMarkMstDAO = new UserBookMarkMstDAO(tx); 

	        lstUser = userBookMarkMstDAO.getUserCombo(userBookMarkMstVO, _UserVO); 
	        mp.put(InvestigationConfig.LIST_USER_COMBO, lstUser); 
	     
	        lstDept = userBookMarkMstDAO.getDeptCombo(userBookMarkMstVO, _UserVO); 
	        mp.put(InvestigationConfig.LIST_DEPT_COMBO, lstDept); 
	         
	        /*lstunit = userBookMarkMstDAO.getUnitCombo(userBookMarkMstVO, _UserVO); 
	        mp.put(InvestigationConfig.LIST_UNIT_COMBO, lstunit);*/ 
	         
	        lstLab = userBookMarkMstDAO.getLabCombo(userBookMarkMstVO, _UserVO); 
	        mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab); 
	         
	        lstBookMark = userBookMarkMstDAO.getBookMarkCombo(userBookMarkMstVO, _UserVO); 
	        mp.put(InvestigationConfig.LIST_BOOKMARK_COMBO, lstBookMark); 
	         
	        lstTestGroup = userBookMarkMstDAO.getTestGroupCombo(userBookMarkMstVO, _UserVO); 
	        mp.put(InvestigationConfig.LIST_TEST_GROUP_COMBO, lstTestGroup); 
	         
	        userBookMarkMstDAO.fetchModifyUserBookMark(userBookMarkMstVO, _UserVO); 
	         
	        lstTest = userBookMarkMstDAO.getTestBylabCode(userBookMarkMstVO, _UserVO); 
	        mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest); 
	         
	    } catch (HisApplicationExecutionException e) { 
	        tx.rollback(); 
	        System.out.println(e.getMessage()); 
	        throw new HisApplicationExecutionException(); 
	    } catch (HisDataAccessException e) { 
	        tx.rollback(); 
	        System.out.println(e.getMessage()); 
	        throw new HisDataAccessException(); 
	    } catch (HisException e) { 
	        tx.rollback(); 
	        System.out.println(e.getMessage()); 
	        throw new HisException(); 
	    } catch (Exception e) { 
	        System.out.println(e.getMessage()); 
	        tx.rollback(); 
	        throw new HisApplicationExecutionException(); 
	    } finally { 
	        tx.close(); 
	    } 
	    return mp; 
	} 


	public Map fetchLabMacroGlobalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();


		try {
			tx.begin();
			LabMacroGlobalMapMstDAOi labmacromap_DAOi = new LabMacroGlobalMapMstDAO(tx);

			lstLab = labmacromap_DAOi.getlabCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);



		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	
	
	public void modifySaveUserBookmark(UserBookMarkMstVO userBookMarkMstVO , UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();
			UserBookMarkMstDAO userBookMarkMstDAO=new UserBookMarkMstDAO(tx);
			userBookMarkMstDAO.saveModifyUserBookMark(userBookMarkMstVO, _UserVO);
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}


	public Map getGlobalMacro(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List<LabMacroMapMasterVO> labmacro_listVO = new ArrayList<LabMacroMapMasterVO>();
		List lstArea = new ArrayList();
		List lstSelectedArea = new ArrayList();

		try {
			tx.begin();
			LabMacroGlobalMapMstDAOi labmacromap_DAOi = new LabMacroGlobalMapMstDAO(tx);

			lstArea = labmacromap_DAOi.getnewmacroglobalComboLeft(labmacromap_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_MACRO_COMBO, lstArea);

			lstSelectedArea = labmacromap_DAOi.getselectedmacroglobalComboRight(labmacromap_VO,_UserVO,labmacro_listVO);
			mp.put(InvestigationConfig.LIST_SELECTED_MACRO_COMBO, lstSelectedArea);

		}
		catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	////////////////////////////////////////END Lab Macro Global Mapping MASTER/////////////////////////////////////////


	//////////////////////////////////////////START Macro MASTER(added by yogender yadav)///////////////////////////////////

	public void saveMacro(MacroMasterVO macromaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";
		try
		{
			tx.begin();
			MacroMstDAOi MacroMstDAOi = new MacroMstDAO(tx);
			checkList=MacroMstDAOi.checkDuplicateMacro(macromaster_VO, _UserVO);

			if(checkList==null)
			{
				MacroMstDAOi.createMacro(macromaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	public void fetchCheckListMacro(MacroMasterVO macromaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstLoinic=new ArrayList();
		try
		{
			tx.begin();
			MacroMstDAOi MacroMstDAOi = new MacroMstDAO(tx);
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

	}

	public void fetchMacro(MacroMasterVO macromaster_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MacroMstDAOi MacroMstDAOi = new MacroMstDAO(tx);
			MacroMstDAOi.fetchMacro(macromaster_VO, _UserVO);
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
	}

	public void savemodifyMacro(MacroMasterVO macromaster_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			MacroMstDAOi MacroMstDAOi = new MacroMstDAO(tx);
			count=MacroMstDAOi.checkDuplicateModifyMacro(macromaster_VO, _UserVO);
			if(count.equals("0"))
			{
				MacroMstDAOi.updateMacro(macromaster_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}





	////////////////////////////////////////END Macro MASTER/////////////////////////////////////////


	//////////////////////////////////////////START Global Lab Canned  MASTER(added by yogender yadav)///////////////////////////////////


	public void saveGlobalLabCanned(LabCannedMasterVO[] insert_labcannedmaster_VO,LabCannedMasterVO[] delete_labcannedmaster_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();

			GlobalLabCannedMstDAOi labcanned_DAOi = new GlobalLabCannedMstDAO(tx);


			//for inserting new records

			if(insert_labcannedmaster_VO!=null && insert_labcannedmaster_VO.length!=0){

				for(int i=0;i < insert_labcannedmaster_VO.length;i++){

					count = labcanned_DAOi.checkPrimaryKeyGlobalLabCannedMaster(insert_labcannedmaster_VO[i],
							_UserVO);
					if (count.equals("0"))
						labcanned_DAOi.createGlobalLabCannedMaster(insert_labcannedmaster_VO[i], _UserVO);
					else
						labcanned_DAOi.updateValidGlobalLabCannedMaster(insert_labcannedmaster_VO[i], _UserVO);

				}
			}

			if(delete_labcannedmaster_VO!=null && delete_labcannedmaster_VO.length!=0){

				for(int i=0;i < delete_labcannedmaster_VO.length;i++)
					labcanned_DAOi.deleteGlobalLabCannedMaster(delete_labcannedmaster_VO[i], _UserVO);

			}




		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} 
		finally {
			tx.close();
		}
	}

	public Map fetchGlobalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();


		try {
			tx.begin();
			GlobalLabCannedMstDAOi labcanned_DAOi = new GlobalLabCannedMstDAO(tx);

			lstLab = labcanned_DAOi.getlabCannedCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);



		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	public Map getGlobalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List<LabCannedMasterVO> labcanned_listVO = new ArrayList<LabCannedMasterVO>();

		List lstArea = new ArrayList();
		List lstSelectedArea = new ArrayList();

		try {
			tx.begin();
			GlobalLabCannedMstDAOi labcanned_DAOi = new GlobalLabCannedMstDAO(tx);

			lstArea = labcanned_DAOi.getnewglobalCannedComboLeft(labcannedmaster_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_CANNED_COMBO, lstArea);

			lstSelectedArea = labcanned_DAOi.getselectedglobalCannedComboRight(labcannedmaster_VO,_UserVO,labcanned_listVO);
			mp.put(InvestigationConfig.LIST_SELECTED_CANNED_COMBO, lstSelectedArea);

		}
		catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	////////////////////////////////////////END Global Lab Canned MASTER/////////////////////////////////////////


	//////////////////////////////////////////START Local Lab Canned  MASTER(added by yogender yadav)///////////////////////////////////


	public void saveLocalLabCanned(LabCannedMasterVO[] insert_labcannedmaster_VO,LabCannedMasterVO[] delete_labcannedmaster_VO, UserVO _UserVO,LabCannedMasterVO[] modify_labcannedmaster_VO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();

			LocalLabCannedMstDAOi labcanned_DAOi = new LocalLabCannedMstDAO(tx);


			//for inserting new records

			if(insert_labcannedmaster_VO!=null && insert_labcannedmaster_VO.length!=0){

				for(int i=0;i < insert_labcannedmaster_VO.length;i++){

					count = labcanned_DAOi.checkPrimaryKeyLocalLabCanned(insert_labcannedmaster_VO[i],
							_UserVO);
					if (count.equals("0"))
					{labcanned_DAOi.createLocalLabCanned(insert_labcannedmaster_VO[i], _UserVO);
					/* labcanned_DAOi.insertLaboratoryMacroMap(insert_labcannedmaster_VO[i], _UserVO);*/
					}
					else
					{labcanned_DAOi.updateValidLocalLabCanned(insert_labcannedmaster_VO[i], _UserVO);
					/* labcanned_DAOi.updateValidLaboratoryMacroMap(insert_labcannedmaster_VO[i],  _UserVO);*/}
				}
			}

			if(delete_labcannedmaster_VO!=null && delete_labcannedmaster_VO.length!=0){

				for(int i=0;i < delete_labcannedmaster_VO.length;i++)
					labcanned_DAOi.deleteLocalLabCanned(delete_labcannedmaster_VO[i], _UserVO);

			}

			
			if(modify_labcannedmaster_VO!=null && modify_labcannedmaster_VO.length!=0){

				for(int i=0;i < modify_labcannedmaster_VO.length;i++)
					labcanned_DAOi.updateValidLocalLabCanned(modify_labcannedmaster_VO[i], _UserVO);

			}




		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}


	public Map fetchLocalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();


		try {
			tx.begin();
			LocalLabCannedMstDAOi labcanned_DAOi = new LocalLabCannedMstDAO(tx);

			lstLab = labcanned_DAOi.getlabCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);



		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}


	public Map getLocalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List<LabCannedMasterVO> labcanned_listVO = new ArrayList<LabCannedMasterVO>();
		List lstArea = new ArrayList();
		List lstSelectedArea = new ArrayList();

		try {
			tx.begin();
			LocalLabCannedMstDAOi labcanned_DAOi = new LocalLabCannedMstDAO(tx);
			GlobalLabCannedMstDAOi labcanned_DAO=new GlobalLabCannedMstDAO(tx);
			labcanned_DAOi.fetchdisplaydataLocalLabCanned(labcannedmaster_VO, _UserVO);
			String count1=labcanned_DAOi.checkLocallyMapped(labcannedmaster_VO, _UserVO);
			int count=Integer.parseInt(count1);
			if(count>0)
			{
				lstArea = labcanned_DAOi.getnewLocalLabCannedComboLeft(labcannedmaster_VO,_UserVO);
				mp.put(InvestigationConfig.LIST_CANNED_COMBO, lstArea);
				lstSelectedArea = labcanned_DAOi.getselectedLocalLabCannedComboRight(labcannedmaster_VO,_UserVO,labcanned_listVO);
				mp.put(InvestigationConfig.LIST_SELECTED_CANNED_COMBO, lstSelectedArea);
				mp.put(InvestigationConfig.LIST_SELECTED_CANNED_COMBO_CODES, labcanned_listVO);
			}
			if(count==0)
			{
				labcanned_listVO = new ArrayList<LabCannedMasterVO>();
				lstArea = labcanned_DAO.getnewglobalCannedComboLeft(labcannedmaster_VO, _UserVO);
				mp.put(InvestigationConfig.LIST_CANNED_COMBO, lstArea);
				lstSelectedArea = labcanned_DAO.getselectedglobalCannedComboRight(labcannedmaster_VO, _UserVO,labcanned_listVO);
				mp.put(InvestigationConfig.LIST_SELECTED_CANNED_COMBO, lstSelectedArea);
				mp.put(InvestigationConfig.LIST_SELECTED_CANNED_COMBO_CODES, labcanned_listVO);
			}
			labcannedmaster_VO.setCount(count1);

		}
		catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	///////////////////////////////////////////START Sample Number Configuration Master ///////////////////////////////////

	public void saveSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList = "";
		try {
			tx.begin();
			SampleNoConfigMstDAOi samplenoconfig_DAOi = new SampleNoConfigMstDAO(tx);

				checkList = samplenoconfig_DAOi.checkDuplicateSampleNoConfig(samplenoconfig_VO,
					_UserVO);
			if (checkList == null) {
			samplenoconfig_DAOi.createSampleNoConfig(samplenoconfig_VO, _UserVO);
			} else {
				throw new HisDuplicateRecordException(
						"Data Already Exists");
			}
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public Map fetchCheckListSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();
		List lstTest = new ArrayList();
		String part[]={};
		String series="";
		int len=0;

		try {
			tx.begin();
			SampleNoConfigMstDAOi samplenoconfig_DAOi = new SampleNoConfigMstDAO(tx);
			samplenoconfig_DAOi.fetchCheckListSampleNoConfig(samplenoconfig_VO, _UserVO);

			//break sample no format into part one and two

			if(!(samplenoconfig_VO.getSampleNoFormat().equals("-")))
			{
				part=samplenoconfig_VO.getSampleNoFormat().split("&");
				samplenoconfig_VO.setPartOne(part[0]);
				samplenoconfig_VO.setPartTwo(part[0]+part[1]);

				len=part[1].length();

				if(len > Integer.parseInt(samplenoconfig_VO.getSeqDigit()))
				{
					series=part[1].substring(0,len-Integer.parseInt(samplenoconfig_VO.getSeqDigit()));
					samplenoconfig_VO.setSeriesFormat(series);
				}	

			}


			lstLab = samplenoconfig_DAOi.getlabCombo(_UserVO);
			lstTest = samplenoconfig_DAOi.gettestCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public Map fetchSampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();
		List lstTest = new ArrayList();

		try {
			tx.begin();
			SampleNoConfigMstDAOi samplenoconfig_DAOi = new SampleNoConfigMstDAO(tx);

			lstLab = samplenoconfig_DAOi.getlabCombo(_UserVO);
			//lstTest = samplenoconfig_DAOi.gettestCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
			samplenoconfig_VO.setInitializationType("d");
			samplenoconfig_VO.setPatientType("3");

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public void savemodifySampleNoConfig(SampleNoConfigMasterVO samplenoconfig_VO,
			UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();
			SampleNoConfigMstDAOi samplenoconfig_DAOi = new SampleNoConfigMstDAO(tx);
			samplenoconfig_DAOi.updateSampleNoConfig(samplenoconfig_VO, _UserVO);
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public Map getTest(SampleNoConfigMasterVO samplenoconfig_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstTest = new ArrayList();


		try {
			tx.begin();
			SampleNoConfigMstDAOi samplenoconfig_DAOi = new SampleNoConfigMstDAO(tx);

			lstTest = samplenoconfig_DAOi.getTest(samplenoconfig_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
			samplenoconfig_VO.setInitializationType("d");

		}	 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	///Star Local Lab Master


	public void saveLocalLab(LabMasterVO labMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="",count="";
		try
		{
			tx.begin();
			LocalLabMstDAOi labMstDAOi = new LocalLabMstDAO(tx);
			count=labMstDAOi.checkPrimaryKeyLocalLab(labMasterVO, _UserVO);

			if(count.equals("0"))
			{				
				checkList=labMstDAOi.checkDuplicateLocalLab(labMasterVO, _UserVO);
				if(checkList==null)
				{
					labMstDAOi.createLocalLab(labMasterVO, _UserVO);
				}else{
					throw new HisDuplicateRecordException("  Data Already Exists");
				}
			}
			else
			{	

				checkList=labMstDAOi.checkDuplicateLocalLab(labMasterVO, _UserVO);
				if(checkList==null)
				{			
					labMstDAOi.updateValid(labMasterVO,_UserVO);

					labMstDAOi.updateLocalLab(labMasterVO, _UserVO);
				}else{
					throw new HisDuplicateRecordException("  Data Already Exists");
				}


			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	public Map fetchLocalLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();
		List departcombo=new ArrayList();
		List locationcombo=new ArrayList();
		List labInchargecombo=new ArrayList();

		try
		{
			tx.begin();
			LocalLabMstDAOi labMstDAOi = new LocalLabMstDAO(tx);
			labMstDAOi.fetchLocalLab(labMasterVO, _UserVO);
			labcombo=labMstDAOi.getGlobalLabCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, labcombo);
			departcombo=labMstDAOi.getLocalLabCombo( _UserVO);
			mp.put(InvestigationConfig.DEPART_COMBO, departcombo);
			locationcombo=labMstDAOi.getlocationCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LOCATION_COMBO, locationcombo);

			labInchargecombo=labMstDAOi.getlabInchargeCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LABINCHARGE_COMBO, labInchargecombo);	

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

	public void savemodifyLocalLab(LabMasterVO labMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			LocalLabMstDAOi labMstDAOi = new LocalLabMstDAO(tx);
			count=labMstDAOi.checkDuplicateLocalLabModify(labMasterVO, _UserVO);
			if(count.equals("0"))
			{
				labMstDAOi.updateLocalLab(labMasterVO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("  Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	public Map fetchLocalLabD(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();
		List departcombo=new ArrayList();
		List locationcombo=new ArrayList();
		List labInchargecombo=new ArrayList();
		try
		{
			tx.begin();
			LocalLabMstDAOi labMstDAOi = new LocalLabMstDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			labcombo=labMstDAOi.getGlobalLabCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, labcombo);


			departcombo=labMstDAOi.getLocalLabCombo( _UserVO);
			mp.put(InvestigationConfig.DEPART_COMBO, departcombo);

			locationcombo=labMstDAOi.getlocationCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LOCATION_COMBO, locationcombo);

			labInchargecombo=labMstDAOi.getlabInchargeCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LABINCHARGE_COMBO, labInchargecombo);		



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

	public Map Populate(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();
		List locationcombo=new ArrayList();
		List labInchargecombo=new ArrayList();
		List departcombo=new ArrayList();
		try
		{
			tx.begin();
			LocalLabMstDAOi labMstDAOi = new LocalLabMstDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			labMstDAOi.Populate(labMasterVO,_UserVO);
			labcombo=labMstDAOi.getGlobalLabCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, labcombo);
			departcombo=labMstDAOi.getLocalLabCombo( _UserVO);
			mp.put(InvestigationConfig.DEPART_COMBO, departcombo);
			locationcombo=labMstDAOi.getlocationCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LOCATION_COMBO, locationcombo);

			labInchargecombo=labMstDAOi.getlabInchargeCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LABINCHARGE_COMBO, labInchargecombo);	
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

	public Map fetchLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();
		List departcombo=new ArrayList();
		List locationcombo=new ArrayList();
		List labInchargecombo=new ArrayList();
		try
		{
			tx.begin();
			LabConfigratorMstDAO labConfigratorMstDAO = new LabConfigratorMstDAO(tx);
			//labConfigratorMstDAO.fetchLocalLab(labConfigratorMstVO, _UserVO);
			labcombo=labConfigratorMstDAO.getGlobalLabCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, labcombo);
			departcombo=labConfigratorMstDAO.getLocalLabCombo( _UserVO);
			mp.put(InvestigationConfig.DEPART_COMBO, departcombo);
			locationcombo=labConfigratorMstDAO.getlocationCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LOCATION_COMBO, locationcombo);

			labInchargecombo=labConfigratorMstDAO.getlabInchargeCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LABINCHARGE_COMBO, labInchargecombo);	

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
	public Map populate(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();
		List locationcombo=new ArrayList();
		List labInchargecombo=new ArrayList();
		List departcombo=new ArrayList();
		List testCombo=new ArrayList();
		try
		{
			tx.begin();
			LabConfigratorMstDAO labConfigratorMstDAO = new LabConfigratorMstDAO(tx);
			labConfigratorMstDAO.populate(labConfigratorMstVO,_UserVO);
			labcombo=labConfigratorMstDAO.getGlobalLabCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, labcombo);
			departcombo=labConfigratorMstDAO.getLocalLabCombo( _UserVO);
			mp.put(InvestigationConfig.DEPART_COMBO, departcombo);
			locationcombo=labConfigratorMstDAO.getlocationCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LOCATION_COMBO, locationcombo);

			labInchargecombo=labConfigratorMstDAO.getlabInchargeCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LABINCHARGE_COMBO, labInchargecombo);

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
	public Map getTestByLabCode(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstTestGlobal=new ArrayList();
		List lstCanned=new ArrayList();
		List lstMacro=new ArrayList();
		List<LabConfigratorMstVO> lstTestSampleDtl=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstTestMandDtl=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstTestGroupDtl=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstTestGlobalDtl=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstLabCanned=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstLabMacro=new ArrayList<LabConfigratorMstVO>();
		Map<String,List<String>> mpTestSample=new HashMap<String, List<String>>();
		Map<String,List<String>> mpTestMand=new HashMap<String, List<String>>();
		Map<String,List<String>> mpTestGroup=new HashMap<String, List<String>>();
		try
		{
			tx.begin();
			LabConfigratorMstDAO labConfigratorMstDAO = new LabConfigratorMstDAO(tx);
			lstTestGlobalDtl=labConfigratorMstDAO.getTestCode(labConfigratorMstVO,"0", _UserVO);
			if(lstTestGlobalDtl!=null)
			{
				if(lstTestGlobalDtl.isEmpty()==false)
				{
				for(LabConfigratorMstVO vo:lstTestGlobalDtl)
				{	
					lstTestGlobal.add(vo.getTestCode1()+"#"+vo.getTestName1());
				}
				}
			}
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTestGlobal);
			
			lstLabCanned=labConfigratorMstDAO.getCannedByLabCode(labConfigratorMstVO, _UserVO);
			if(lstLabCanned!=null)
			{	
				if(lstLabCanned.isEmpty()==false)
				{
			
				for(LabConfigratorMstVO vo:lstLabCanned)
				{	
					lstCanned.add(vo.getCannedCode()+"#"+vo.getCannedName());
				}
				}
			}
			mp.put(InvestigationConfig.LIST_CANNED_COMBO, lstCanned);
			
			lstLabMacro=labConfigratorMstDAO.getMacroByLabCode(labConfigratorMstVO, _UserVO);
			if(lstLabMacro!=null)
				if(lstLabMacro.isEmpty()==false)
				{
			{
					for(LabConfigratorMstVO vo:lstLabMacro)
					{	
						lstMacro.add(vo.getMacroCode()+"#"+vo.getMacroName());
					}
				}
			}
			mp.put(InvestigationConfig.LIST_MACRO_COMBO, lstMacro);
			
			lstTestMandDtl=labConfigratorMstDAO.getMandBylabCode(labConfigratorMstVO, _UserVO);
			if(lstTestMandDtl!=null)
			{
			for(LabConfigratorMstVO vo:lstTestMandDtl)
			{
				List lstmand=mpTestMand.get(vo.getTestCode()+"#"+vo.getTestName());
				if(lstmand!=null&&lstmand.size()>0)
				{
					lstmand.add(vo.getTestCode()+"#"+vo.getMandCode()+"#"+vo.getMandName());
				}
				else
				{
					lstmand=new ArrayList();
					lstmand.add(vo.getTestCode()+"#"+vo.getMandCode()+"#"+vo.getMandName());
				}
				mpTestMand.put(vo.getTestCode()+"#"+vo.getTestName(),lstmand);

			}
			}
			mp.put(InvestigationConfig.MAP_TEST_MAND_DTLS, mpTestMand);

			lstTestSampleDtl=labConfigratorMstDAO.getTestBylabCode(labConfigratorMstVO, _UserVO);
			if(lstTestSampleDtl!=null)
			{
			for(LabConfigratorMstVO vo:lstTestSampleDtl)
			{
				List lstSample=mpTestSample.get(vo.getTestCode()+"#"+vo.getTestName());
				if(lstSample!=null&&lstSample.size()>0)
				{
					lstSample.add(vo.getTestCode()+"#"+vo.getSampleCode()+"#"+vo.getSampleName());
				}
				else
				{
					lstSample=new ArrayList();
					lstSample.add(vo.getTestCode()+"#"+vo.getSampleCode()+"#"+vo.getSampleName());
				}
				mpTestSample.put(vo.getTestCode()+"#"+vo.getTestName(),lstSample);

			}
			}
			mp.put(InvestigationConfig.MAP_TEST_SAMPLE_DTLS, mpTestSample);
			
			lstTestGroupDtl=labConfigratorMstDAO.getGroupByTestCode(labConfigratorMstVO, _UserVO);
			if(lstTestGroupDtl!=null)
			{
			for(LabConfigratorMstVO vo:lstTestGroupDtl)
			{
				List lstGroup=mpTestGroup.get(vo.getTestCode()+"#"+vo.getTestName());
				if(lstGroup!=null&&lstGroup.size()>0)
				{
					lstGroup.add(vo.getTestCode()+"#"+vo.getGroupCode()+"#"+vo.getGroupName());
				}
				else
				{
					lstGroup=new ArrayList();
					lstGroup.add(vo.getTestCode()+"#"+vo.getGroupCode()+"#"+vo.getGroupName());
				}
				mpTestGroup.put(vo.getTestCode()+"#"+vo.getTestName(),lstGroup);
					
			}
			}
			mp.put(InvestigationConfig.MAP_TEST_GROUP_DTLS, mpTestGroup);
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

	public void saveLabConfigrator(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO) 
    { 
        JDBCTransactionContext tx = new JDBCTransactionContext(); 
        String checkList="",count=""; 
        try 
        { 
            tx.begin(); 
            LabConfigratorMstDAO labConfigratorMstDAO=new LabConfigratorMstDAO(tx);   
            count=labConfigratorMstDAO.checkPrimaryKeyLocalLab(labConfigratorMstVO, _UserVO); 
            String labCode=labConfigratorMstVO.getLabCode(); 
            if(count.equals("0")) 
            {                 
                checkList=labConfigratorMstDAO.checkDuplicateLocalLab(labConfigratorMstVO, _UserVO); 
                if(checkList==null) 
                { 
                    labConfigratorMstDAO.createLocalLab(labConfigratorMstVO, _UserVO); 
                }else{ 
                    throw new HisDuplicateRecordException("  Data Already Exists"); 
                } 
            } 
            else 
            {     

                checkList=labConfigratorMstDAO.checkDuplicateLocalLab(labConfigratorMstVO, _UserVO); 
                if(checkList==null) 
                {             
                    labConfigratorMstDAO.updateValid(labConfigratorMstVO,_UserVO); 

                    labConfigratorMstDAO.updateLocalLab(labConfigratorMstVO, _UserVO); 
                }else{ 
                    throw new HisDuplicateRecordException("  Data Already Exists"); 
                } 


            } 
            if(labConfigratorMstVO.getTestChkBox()!=null) 
            { 
                for (int i=0;i<labConfigratorMstVO.getTestChkBox().length;i++) 
                { 
                    String testCode=labConfigratorMstVO.getTestChkBox()[i]; 
                    labConfigratorMstDAO.getLabTestData(testCode,labConfigratorMstVO, _UserVO); 
                    labConfigratorMstDAO.insertLabTestData(labConfigratorMstVO,_UserVO); 
                     
                    if(labConfigratorMstVO.getSampleChkBox()!=null) 
                    { 
                        for (int j=0;j<labConfigratorMstVO.getSampleChkBox().length;j++) 
                        { 
                        String sampleCodeHash=labConfigratorMstVO.getSampleChkBox()[j]; 
                        String testCode1=sampleCodeHash.split("#")[0]; 
                        String sampleCode=sampleCodeHash.split("#")[1]; 
                        if(testCode.equals(testCode1)) 
                        { 
                        labConfigratorMstVO.setSampleCode(sampleCode); 
                        labConfigratorMstDAO.fetchLabTestSampleData(testCode,sampleCode,labConfigratorMstVO, _UserVO); 
                        labConfigratorMstDAO.insertLabTestSampleData(labConfigratorMstVO,testCode1,_UserVO); 
                        } 
                        } 
                    } 
                    if(labConfigratorMstVO.getMandChkBox()!=null) 
                    { 
                        for (int k=0;k<labConfigratorMstVO.getMandChkBox().length;k++) 
                        { 
                        String mandCodeHash=labConfigratorMstVO.getMandChkBox()[k]; 
                        String testCode2=mandCodeHash.split("#")[0]; 
                        String mandCode=mandCodeHash.split("#")[1]; 
                        if(testCode.equals(testCode2)) 
                        { 
                        labConfigratorMstVO.setMandCode(mandCode); 
                        String countDelMand=labConfigratorMstDAO.checkDeletedLocalTestMandatory(testCode2, mandCode, _UserVO);
                        if(countDelMand.equals("1"))
                        {
                        	labConfigratorMstDAO.UpdateTestMand(labConfigratorMstVO, _UserVO);
                        }
                        String countMand=labConfigratorMstDAO.checkLocalTestMandatory(testCode2, mandCode, _UserVO);
                        if(countMand.equals("0")) 
                        { 
                        labConfigratorMstDAO.insertTestMandatoryData(testCode, labConfigratorMstVO, _UserVO); 
                        } 
                        } 
                        } 
                    } 
                    if(labConfigratorMstVO.getGroupChkBox()!=null) 
                    { 
                        for (int k=0;k<labConfigratorMstVO.getGroupChkBox().length;k++) 
                        { 
                        String mandCodeHash=labConfigratorMstVO.getGroupChkBox()[k]; 
                        String testCode3=mandCodeHash.split("#")[0]; 
                        String groupCode=mandCodeHash.split("#")[1]; 
                        if(testCode.equals(testCode3)) 
                        { 
                        labConfigratorMstVO.setGroupCode(groupCode); 
                        labConfigratorMstDAO.insertTestGroupData(testCode, labConfigratorMstVO, _UserVO); 
                        } 
                        } 
                    } 
             
                } 
            } 
            if(labConfigratorMstVO.getCannedChkBox()!=null) 
            { 
                for (int k=0;k<labConfigratorMstVO.getCannedChkBox().length;k++) 
                { 
                String cannedCode=labConfigratorMstVO.getCannedChkBox()[k]; 
                labConfigratorMstVO.setCannedCode(cannedCode); 
                labConfigratorMstDAO.insertLabCannedData(labConfigratorMstVO, _UserVO); 
                } 
            } 
            if(labConfigratorMstVO.getMacroChkBox()!=null) 
            { 
                for (int k=0;k<labConfigratorMstVO.getMacroChkBox().length;k++) 
                { 
                String macroCode=labConfigratorMstVO.getMacroChkBox()[k]; 
                labConfigratorMstVO.setMacroCode(macroCode); 
                labConfigratorMstDAO.insertLabMacroData(labConfigratorMstVO, _UserVO); 
                } 
            } 
             
             
        } 
        catch (HisDuplicateRecordException e) 
        { 
            tx.rollback(); 
            throw new HisDuplicateRecordException(e.getMessage()); 
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
    } 

	public Map fetchModifyLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();
		List departcombo=new ArrayList();
		List locationcombo=new ArrayList();
		List labInchargecombo=new ArrayList();
		List lstTestGlobal=new ArrayList();
		List lstTestLocal=new ArrayList();
		List lstCanned=new ArrayList();
		List lstMacro=new ArrayList();
		List lstCannedLocal=new ArrayList();
		List lstMacroLocal=new ArrayList();
		List<LabConfigratorMstVO> lstTestSampleDtl=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstTestSampleDtlLocal=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstTestMandDtl=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstTestMandDtlLocal=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstTestGlobalDtl=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstTestLocalDtl=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstTestGroupDtl=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstTestGroupDtlLocal=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstLabCanned=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstLabCannedLocal=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstLabMacro=new ArrayList<LabConfigratorMstVO>();
		List<LabConfigratorMstVO> lstLabMacroLocal=new ArrayList<LabConfigratorMstVO>();
		Map<String,List<String>> mpTestSample=new HashMap<String, List<String>>();
		Map<String,List<String>> mpTestSampleLocal=new HashMap<String, List<String>>();
		Map<String,List<String>> mpTestMand=new HashMap<String, List<String>>();
		Map<String,List<String>> mpTestMandLocal=new HashMap<String, List<String>>();
		Map<String,List<String>> mpTestGroup=new HashMap<String, List<String>>();
		Map<String,List<String>> mpTestGroupLocal=new HashMap<String, List<String>>();
		
		try
		{
			tx.begin();
			LabConfigratorMstDAO labConfigratorMstDAO = new LabConfigratorMstDAO(tx);
			
			labConfigratorMstDAO.fetchLocalLab(labConfigratorMstVO, _UserVO);
			labcombo=labConfigratorMstDAO.getGlobalLabCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, labcombo);
			departcombo=labConfigratorMstDAO.getLocalLabCombo( _UserVO);
			mp.put(InvestigationConfig.DEPART_COMBO, departcombo);
			locationcombo=labConfigratorMstDAO.getlocationCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LOCATION_COMBO, locationcombo);

			labInchargecombo=labConfigratorMstDAO.getlabInchargeCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LABINCHARGE_COMBO, labInchargecombo);
			
			lstTestGlobalDtl=labConfigratorMstDAO.getTestCode(labConfigratorMstVO,"0", _UserVO);
			if(lstTestGlobalDtl!=null)
			{
				for(LabConfigratorMstVO vo:lstTestGlobalDtl)
				{	
					lstTestGlobal.add(vo.getTestCode1()+"#"+vo.getTestName1());
				}
			}
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTestGlobal);
			
			
			lstTestLocalDtl=labConfigratorMstDAO.getTestCode(labConfigratorMstVO,"1", _UserVO);
			if(lstTestLocalDtl!=null)
			{
				for(LabConfigratorMstVO vo:lstTestLocalDtl)
				{	
					lstTestLocal.add(vo.getTestCode1()+"#"+vo.getTestName1());
				}
			}
			mp.put(InvestigationConfig.LIST_TEST_COMBO_MAPPED, lstTestLocal);
			
			lstTestMandDtl=labConfigratorMstDAO.getMandBylabCode(labConfigratorMstVO, _UserVO);
			if(lstTestMandDtl!=null)
			{
			for(LabConfigratorMstVO vo:lstTestMandDtl)
			{
				List lstmand=mpTestMand.get(vo.getTestCode()+"#"+vo.getTestName());
				
			/*	List lstmand=new ArrayList();
				String valuefetched=vo.getTestCode()+"#"+vo.getTestName();*/
				
				if(lstmand!=null&&lstmand.size()>0)
				{
					lstmand.add(vo.getTestCode()+"#"+vo.getMandCode()+"#"+vo.getMandName()+"#"+vo.getHospitalCode());
				}
				else
				{
					lstmand=new ArrayList();
					lstmand.add(vo.getTestCode()+"#"+vo.getMandCode()+"#"+vo.getMandName()+"#"+vo.getHospitalCode());
				}
				mpTestMand.put(vo.getTestCode()+"#"+vo.getTestName(),lstmand);
					
			}
			}
			mp.put(InvestigationConfig.MAP_TEST_MAND_DTLS, mpTestMand);
			
		 lstTestMandDtlLocal=labConfigratorMstDAO.getMandBylabCodeLocal(labConfigratorMstVO, _UserVO);
			if(lstTestMandDtlLocal!=null)
			{
			for(LabConfigratorMstVO vo:lstTestMandDtlLocal)
			{
				List lstmandLocal=mpTestMand.get(vo.getTestCode()+"#"+vo.getTestName());
				
				/*List lstmandLocal=new ArrayList();
				String valuefetched=vo.getTestCode()+"#"+vo.getTestName();
				*/
				
				if(lstmandLocal!=null&&lstmandLocal.size()>0)
				{
					Object checkVal="";
					String values="";
					String[] splitValues=null;
					int iterate=0;
					for(iterate=0;iterate<lstmandLocal.size();iterate++)
						
						{
						checkVal=lstmandLocal.get(iterate);
						values=checkVal.toString();
						splitValues=values.split("#");
						
						String tCode=splitValues[0];
						String mCode=splitValues[1];
						String mName=splitValues[2];
						
						if((tCode+"#"+mCode+"#"+mName).equals(vo.getTestCode()+"#"+vo.getMandCode()+"#"+vo.getMandName()))
							lstmandLocal.remove(iterate);
					
					}
					lstmandLocal.add(vo.getTestCode()+"#"+vo.getMandCode()+"#"+vo.getMandName()+"#"+vo.getHospitalCode());
				}
				else
				{
					lstmandLocal=new ArrayList();
					lstmandLocal.add(vo.getTestCode()+"#"+vo.getMandCode()+"#"+vo.getMandName()+"#"+vo.getHospitalCode());
				}
				mpTestMandLocal.put(vo.getTestCode()+"#"+vo.getTestName(),lstmandLocal);
					
			}
			}
			mp.put(InvestigationConfig.MAP_TEST_MAND_DTLS_MAPPED, mpTestMandLocal);
			
			lstTestSampleDtl=labConfigratorMstDAO.getTestBylabCode(labConfigratorMstVO, _UserVO);
			if(lstTestSampleDtl!=null)
			{
			for(LabConfigratorMstVO vo:lstTestSampleDtl)
			{
				 List lstSample=mpTestSample.get(vo.getTestCode()+"#"+vo.getTestName());
				
				/*List lstSample=new ArrayList();
				String valuefetched=vo.getTestCode()+"#"+vo.getTestName();*/
				
				if(lstSample!=null&&lstSample.size()>0)
				{
					lstSample.add(vo.getTestCode()+"#"+vo.getSampleCode()+"#"+vo.getSampleName()+"#"+vo.getHospitalCode());
				}
				else
				{
					lstSample=new ArrayList();
					lstSample.add(vo.getTestCode()+"#"+vo.getSampleCode()+"#"+vo.getSampleName()+"#"+vo.getHospitalCode());
				}
				mpTestSample.put(vo.getTestCode()+"#"+vo.getTestName(),lstSample);
					
			}
			}
			mp.put(InvestigationConfig.MAP_TEST_SAMPLE_DTLS, mpTestSample);
			
			lstTestSampleDtlLocal=labConfigratorMstDAO.getTestBylabCodeLocal(labConfigratorMstVO, _UserVO);
			if(lstTestSampleDtlLocal!=null)
			{
			for(LabConfigratorMstVO vo:lstTestSampleDtlLocal)
			{
				List lstSampleLocal=mpTestSample.get(vo.getTestCode()+"#"+vo.getTestName());
				
		/*		List lstSampleLocal=new ArrayList();
				String valuefetched=vo.getTestCode()+"#"+vo.getTestName();*/
				
				if(lstSampleLocal!=null&&lstSampleLocal.size()>0)
				{
					
					Object checkVal="";
					String values="";
					String[] splitValues=null;
					int iterate=0;
					for(iterate=0;iterate<lstSampleLocal.size();iterate++)
						
						{
						checkVal=lstSampleLocal.get(iterate);
						values=checkVal.toString();
						splitValues=values.split("#");
						
						String tCode=splitValues[0];
						String sCode=splitValues[1];
						String sName=splitValues[2];
						
						if((tCode+"#"+sCode+"#"+sName).equals(vo.getTestCode()+"#"+vo.getSampleCode()+"#"+vo.getSampleName()))
							lstSampleLocal.remove(iterate);
					
					}
					lstSampleLocal.add(vo.getTestCode()+"#"+vo.getSampleCode()+"#"+vo.getSampleName()+"#"+vo.getHospitalCode());
				}
				else
				{
					lstSampleLocal=new ArrayList();
					lstSampleLocal.add(vo.getTestCode()+"#"+vo.getSampleCode()+"#"+vo.getSampleName()+"#"+vo.getHospitalCode());
				}
				mpTestSampleLocal.put(vo.getTestCode()+"#"+vo.getTestName(),lstSampleLocal);
					
			}
			}
			mp.put(InvestigationConfig.MAP_TEST_SAMPLE_DTLS_MAPPED, mpTestSampleLocal);
			
			lstTestGroupDtl=labConfigratorMstDAO.getGroupByTestCode(labConfigratorMstVO, _UserVO);
			if(lstTestGroupDtl!=null)
			{
			for(LabConfigratorMstVO vo:lstTestGroupDtl)
			{
				List lstGroup=mpTestGroup.get(vo.getTestCode()+"#"+vo.getTestName());
			
			/*	List lstGroup=new ArrayList();
				String valuefetched=vo.getTestCode()+"#"+vo.getTestName();*/
				
				if(lstGroup!=null&&lstGroup.size()>0)
				{
					lstGroup.add(vo.getTestCode()+"#"+vo.getGroupCode()+"#"+vo.getGroupName()+"#"+vo.getHospitalCode());
				}
				else
				{
					lstGroup=new ArrayList();
					lstGroup.add(vo.getTestCode()+"#"+vo.getGroupCode()+"#"+vo.getGroupName()+"#"+vo.getHospitalCode());
				}
				mpTestGroup.put(vo.getTestCode()+"#"+vo.getTestName(),lstGroup);
					
			}
			}
			mp.put(InvestigationConfig.MAP_TEST_GROUP_DTLS, mpTestGroup);
			lstTestGroupDtlLocal=labConfigratorMstDAO.getGroupBylabCodeLocal(labConfigratorMstVO, _UserVO);
			if(lstTestGroupDtlLocal!=null)
			{
			for(LabConfigratorMstVO vo:lstTestGroupDtlLocal)
			{
				List lstGroup=mpTestGroup.get(vo.getTestCode()+"#"+vo.getTestName());
				if(lstGroup!=null&&lstGroup.size()>0)
				{
					
					
					
					Object checkVal="";
					String values="";
					String[] splitValues=null;
					int iterate=0;
					for(iterate=0;iterate<lstGroup.size();iterate++)
						
						{
						checkVal=lstGroup.get(iterate);
						values=checkVal.toString();
						splitValues=values.split("#");
						
						String tCode=splitValues[0];
						String gCode=splitValues[1];
						String gName=splitValues[2];
						
						if((tCode+"#"+gCode+"#"+gName).equals(vo.getTestCode()+"#"+vo.getGroupCode()+"#"+vo.getGroupName()))
							lstGroup.remove(iterate);
					
					}
					lstGroup.add(vo.getTestCode()+"#"+vo.getGroupCode()+"#"+vo.getGroupName()+"#"+vo.getHospitalCode());
				}
				else
				{
					lstGroup=new ArrayList();
					lstGroup.add(vo.getTestCode()+"#"+vo.getGroupCode()+"#"+vo.getGroupName()+"#"+vo.getHospitalCode());
				}
				mpTestGroupLocal.put(vo.getTestCode()+"#"+vo.getTestName(),lstGroup);
					
			}
			}
			mp.put(InvestigationConfig.MAP_TEST_GROUP_DTLS_MAPPED, mpTestGroupLocal);
			lstLabCanned=labConfigratorMstDAO.getCannedByLabCode(labConfigratorMstVO, _UserVO);
			if(lstLabCanned!=null)
			{
				if(lstLabCanned.isEmpty()==false)
				{
			
				for(LabConfigratorMstVO vo:lstLabCanned)
				{	
					lstCanned.add(vo.getCannedCode()+"#"+vo.getCannedName()+"#"+vo.getHospitalCode());
				}
				}
			}
			mp.put(InvestigationConfig.LIST_CANNED_COMBO, lstCanned);
			
			lstLabMacro=labConfigratorMstDAO.getMacroByLabCode(labConfigratorMstVO, _UserVO);
			if(lstLabMacro!=null)
			{	
				if(lstLabMacro.isEmpty()==false)
				{
			
				for(LabConfigratorMstVO vo:lstLabMacro)
				{	
					lstMacro.add(vo.getMacroCode()+"#"+vo.getMacroName()+"#"+vo.getHospitalCode());
				}
				}
			}
			mp.put(InvestigationConfig.LIST_MACRO_COMBO, lstMacro);
			lstLabCanned=labConfigratorMstDAO.getCannedByLabCodeLocal(labConfigratorMstVO, _UserVO);
			if(lstLabCanned!=null)
			{
				for(LabConfigratorMstVO vo:lstLabCanned)
				{	
					lstCannedLocal.add(vo.getCannedCode()+"#"+vo.getCannedName()+"#"+vo.getHospitalCode());
				}
			}
			mp.put(InvestigationConfig.LIST_CANNED_COMBO_MAPPED, lstCannedLocal);
			
			lstLabMacro=labConfigratorMstDAO.getMacroByLabCodeLocal(labConfigratorMstVO, _UserVO);
			if(lstLabMacro!=null)
			{
				for(LabConfigratorMstVO vo:lstLabMacro)
				{	
					lstMacroLocal.add(vo.getMacroCode()+"#"+vo.getMacroName()+"#"+vo.getHospitalCode());
				}
			}
			mp.put(InvestigationConfig.LIST_MACRO_COMBO_MAPPED, lstMacroLocal);
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
	public void saveModifyLabConfigrator(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO, List deleteTest, List deleteSample, List deleteMand, List deleteCanned,List deleteMacro)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			
			
			tx.begin();
			LabConfigratorMstDAO labConfigratorMstDAO = new LabConfigratorMstDAO(tx);
			count=labConfigratorMstDAO.checkDuplicateLabConfigratorModify(labConfigratorMstVO, _UserVO);
			if(count.equals("0"))
			{
				labConfigratorMstDAO.updateLocalLab(labConfigratorMstVO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("  Data Already Exists");
			} 
			
		
			
			
			
			
			
			
			
			
			//add update
			if(labConfigratorMstVO.getTestChkBox()!=null)
			{
				for (int i=0;i<labConfigratorMstVO.getTestChkBox().length;i++)
				{
					String testCode=labConfigratorMstVO.getTestChkBox()[i];
					String countTest2=labConfigratorMstDAO.checkDeletedLabTest(testCode, labConfigratorMstVO, _UserVO);
					if(countTest2.equals("1"))
					{
						labConfigratorMstVO.setTestCode(testCode);
						labConfigratorMstDAO.updateLabTest(labConfigratorMstVO, _UserVO);
					}
					String countTest=labConfigratorMstDAO.checkLocalLabTest(testCode,labConfigratorMstVO,_UserVO);
					if(countTest.equals("0"))
					{
					labConfigratorMstDAO.getLabTestData(testCode,labConfigratorMstVO, _UserVO);
					labConfigratorMstDAO.insertLabTestData(labConfigratorMstVO,_UserVO);
					}
					/*else
					{
						labConfigratorMstVO.setTestCode(testCode);
						labConfigratorMstDAO.deleteLabTest(labConfigratorMstVO, _UserVO);
					}*/
					
					
					if(labConfigratorMstVO.getSampleChkBox()!=null)
					{
						for (int j=0;j<labConfigratorMstVO.getSampleChkBox().length;j++) 
						{
						String sampleCodeHash=labConfigratorMstVO.getSampleChkBox()[j];
						String testCode1=sampleCodeHash.split("#")[0];
						String sampleCode=sampleCodeHash.split("#")[1];
						String countSample2=labConfigratorMstDAO.checkDeletedLabTestSample(testCode1, sampleCode, labConfigratorMstVO, _UserVO);
						if(testCode.equals(testCode1)&&countSample2.equals("1"))
						{
							labConfigratorMstVO.setTestCode(testCode1);
							labConfigratorMstVO.setSampleCode(sampleCode);
							labConfigratorMstDAO.updateLabTestSample(labConfigratorMstVO, _UserVO);
						}
						String countSample=labConfigratorMstDAO.checkLocalLabTestSample(testCode1, sampleCode, labConfigratorMstVO, _UserVO);
						if(testCode.equals(testCode1)&&countSample.equals("0"))
						{
						labConfigratorMstVO.setSampleCode(sampleCode);
						labConfigratorMstDAO.fetchLabTestSampleData(testCode1,sampleCode,labConfigratorMstVO, _UserVO);
						labConfigratorMstDAO.insertLabTestSampleData(labConfigratorMstVO,testCode1,_UserVO);
						}
					/*	else
						{
							labConfigratorMstVO.setTestCode(testCode1);
							labConfigratorMstVO.setSampleCode(sampleCode);
							labConfigratorMstDAO.deleteLabTestSample(labConfigratorMstVO, _UserVO);
						}*/
						
						}
					}
					if(labConfigratorMstVO.getMandChkBox()!=null)
					{
						for (int k=0;k<labConfigratorMstVO.getMandChkBox().length;k++) 
						{
						String mandCodeHash=labConfigratorMstVO.getMandChkBox()[k];
						String testCode2=mandCodeHash.split("#")[0];
						String mandCode=mandCodeHash.split("#")[1];
						String countMand2=labConfigratorMstDAO.checkDeletedTestMandatory(testCode2, mandCode, _UserVO);
						if(testCode.equals(testCode2)&&countMand2.equals("1"))
						{
							labConfigratorMstVO.setMandCode(mandCode);
							labConfigratorMstVO.setTestCode(testCode2);
							labConfigratorMstDAO.UpdateTestMand(labConfigratorMstVO, _UserVO);
						}
						String countMand=labConfigratorMstDAO.checkLocalTestMandatory(testCode2, mandCode, _UserVO);
						if(testCode.equals(testCode2)&&countMand.equals("0"))
						{
						labConfigratorMstVO.setMandCode(mandCode);
						labConfigratorMstDAO.insertTestMandatoryData(testCode2, labConfigratorMstVO, _UserVO);
						}
					/*	else
						{
							labConfigratorMstVO.setTestCode(testCode2);
							labConfigratorMstVO.setMandCode(mandCode);
							labConfigratorMstDAO.deleteTestMand(labConfigratorMstVO, _UserVO);
						}*/
						
						}
					}
					if(labConfigratorMstVO.getGroupChkBox()!=null)
					{
						for (int k=0;k<labConfigratorMstVO.getGroupChkBox().length;k++) 
						{
						String mandCodeHash=labConfigratorMstVO.getGroupChkBox()[k];
						String testCode2=mandCodeHash.split("#")[0];
						String groupCode=mandCodeHash.split("#")[1];
						
					/*	String countMand2=labConfigratorMstDAO.checkDeleted(testCode2, groupCode, _UserVO);
						if(testCode.equals(testCode2)&&countMand2.equals("1"))
						{
							labConfigratorMstVO.setGroupCode(groupCode);
							labConfigratorMstVO.setTestCode(testCode2);
							labConfigratorMstDAO.UpdateTestGroup(labConfigratorMstVO, _UserVO);
						}*/
						
						String countgroup=labConfigratorMstDAO.checkLocalTestGroup(testCode2, labConfigratorMstVO, _UserVO);
						if(testCode.equals(testCode2)&&countgroup.equals("0"))
						{
						labConfigratorMstVO.setGroupCode(groupCode);
						labConfigratorMstDAO.insertTestGroupData(testCode2, labConfigratorMstVO, _UserVO);
						}
						}
					}
					if(labConfigratorMstVO.getCannedChkBox()!=null)
					{
						for (int k=0;k<labConfigratorMstVO.getCannedChkBox().length;k++) 
						{
						String cannedCode=labConfigratorMstVO.getCannedChkBox()[k];
						String countCanned=labConfigratorMstDAO.checkLocalLabCanned( cannedCode,labConfigratorMstVO, _UserVO);
						String countCanned2=labConfigratorMstDAO.checkDeletedLabCanned(cannedCode, labConfigratorMstVO, _UserVO);
						if(countCanned2.equals("1"))
						{
							labConfigratorMstVO.setCannedCode(cannedCode);
							labConfigratorMstDAO.updateLabCanned(labConfigratorMstVO, _UserVO);
						}
						
						/*	if(countCanned.equals("0"))
						{
						labConfigratorMstVO.setCannedCode(cannedCode);
						labConfigratorMstDAO.insertLabCannedData(labConfigratorMstVO, _UserVO);
						}*/
						if(countCanned.equals("0") && countCanned2.equals("1")==false)
						{
							labConfigratorMstVO.setCannedCode(cannedCode);
							labConfigratorMstDAO.insertLabCannedData(labConfigratorMstVO, _UserVO);
						}
						
						
						}
					}
					if(labConfigratorMstVO.getMacroChkBox()!=null)
					{
						for (int k=0;k<labConfigratorMstVO.getMacroChkBox().length;k++) 
						{
						String macroCode=labConfigratorMstVO.getMacroChkBox()[k];
						String countMacro=labConfigratorMstDAO.checkLocalLabMacro( macroCode,labConfigratorMstVO, _UserVO);
						String countMacro2=labConfigratorMstDAO.checkDeletedLabMacro(macroCode, labConfigratorMstVO, _UserVO);
						if(countMacro2.equals("1"))
						{
							labConfigratorMstVO.setMacroCode(macroCode);
							labConfigratorMstDAO.updateLabMacro(labConfigratorMstVO, _UserVO);
						}
						
						
						if(countMacro.equals("0") && countMacro2.equals("1")==false)
						{
						labConfigratorMstVO.setMacroCode(macroCode);
						labConfigratorMstDAO.insertLabMacroData(labConfigratorMstVO, _UserVO);
						}
						/*else
						{
							labConfigratorMstVO.setMacroCode(macroCode);
							labConfigratorMstDAO.deleteLabMacro(labConfigratorMstVO, _UserVO);
						}*/
						
						}
					}
			
				}
			}
			
			
			//delete
			if(deleteTest!=null)
			{
				for(int del=0;del<deleteTest.size();del++)
					{					
					labConfigratorMstVO.setTestCode(deleteTest.get(del).toString());
					labConfigratorMstDAO.deleteLabTest(labConfigratorMstVO, _UserVO);
					labConfigratorMstDAO.deleteLabTestSample_Test(labConfigratorMstVO, _UserVO);
					labConfigratorMstDAO.deleteTestMand(labConfigratorMstVO, _UserVO);
					}
				
			}
			
			if(deleteSample!=null)
			{
				
				for(int del=0;del<deleteSample.size();del++)
				{
					String sampleCodeHash=deleteSample.get(del).toString();
					String testCode1=sampleCodeHash.split("#")[0];
					String sampleCode=sampleCodeHash.split("#")[1];
					
					labConfigratorMstVO.setTestCode(testCode1);
					labConfigratorMstVO.setSampleCode(sampleCode);
					labConfigratorMstDAO.deleteLabTestSample(labConfigratorMstVO, _UserVO);
					
				}
				
				
			}
			
			
			if(deleteMand!=null)
			{
				
				for(int del=0;del<deleteMand.size();del++)
				{
					String sampleCodeHash=deleteMand.get(del).toString();
					String testCode1=sampleCodeHash.split("#")[0];
					String mandCode=sampleCodeHash.split("#")[1];
					
					labConfigratorMstVO.setTestCode(testCode1);
					labConfigratorMstVO.setMandCode(mandCode);
					labConfigratorMstDAO.deleteTestMand(labConfigratorMstVO, _UserVO);
					
				}
				
				
			}
			
			
			
			if(deleteCanned!=null)
			{
				
				for(int del=0;del<deleteCanned.size();del++)
				{
					String cannedCode=deleteCanned.get(del).toString();
					labConfigratorMstVO.setCannedCode(cannedCode);
					labConfigratorMstDAO.deleteLabCanned(labConfigratorMstVO, _UserVO);
					
				}
				
				
			}
			
			if(deleteMacro!=null)
			{
				
				for(int del=0;del<deleteMacro.size();del++)
				{
					String macroCode=deleteMacro.get(del).toString();
					labConfigratorMstVO.setMacroCode(macroCode);
					labConfigratorMstDAO.deleteLabMacro(labConfigratorMstVO, _UserVO);
					
				}
				
				
			}
			
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}

	///////////////////////////////////////////START COLL AREA Sample Number Configuration Master ///////////////////////////////////

	public void saveCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList = "";
		try {
			tx.begin();
			CollAreaSampleNoConfigMstDAOi collareasamplenoconfig_DAOi = new CollAreaSampleNoConfigMstDAO(tx);

				checkList = collareasamplenoconfig_DAOi.checkDuplicateCollAreaSampleNoConfig(collareasamplenoconfig_VO,
					_UserVO);
							if (checkList == null) {
			collareasamplenoconfig_DAOi.createCollAreaSampleNoConfig(collareasamplenoconfig_VO, _UserVO);
			} else {
							throw new HisDuplicateRecordException(
							"Data Already Exists");
							}
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public Map fetchCheckListCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();
		List lstArea = new ArrayList();
		String part[]={};
		String series="";
		int len=0;

		try {
			tx.begin();
			CollAreaSampleNoConfigMstDAOi collareasamplenoconfig_DAOi = new CollAreaSampleNoConfigMstDAO(tx);
			collareasamplenoconfig_DAOi.fetchCheckListCollAreaSampleNoConfig(collareasamplenoconfig_VO, _UserVO);

			//break sample no format into part one and two

			if(!(collareasamplenoconfig_VO.getSampleNoFormat().equals("-")))
			{
				part=collareasamplenoconfig_VO.getSampleNoFormat().split("&");
				collareasamplenoconfig_VO.setPartOne(part[0]);
				collareasamplenoconfig_VO.setPartTwo(part[0]+part[1]);
				len=part[1].length();

				len=part[1].length();

				if(len > Integer.parseInt(collareasamplenoconfig_VO.getSeqDigit()))
				{
					series=part[1].substring(0,len-Integer.parseInt(collareasamplenoconfig_VO.getSeqDigit()));
					collareasamplenoconfig_VO.setSeriesFormat(series);
				}	

			}


			lstLab = collareasamplenoconfig_DAOi.getlabCombo(_UserVO);
			lstArea = collareasamplenoconfig_DAOi.getareaCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			mp.put(InvestigationConfig.LIST_AREA_COMBO, lstArea);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public Map fetchCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstLab = new ArrayList();
		List lstArea = new ArrayList();

		try {
			tx.begin();
			CollAreaSampleNoConfigMstDAOi collareasamplenoconfig_DAOi = new CollAreaSampleNoConfigMstDAO(tx);

			//list of lab to be fetched on change of collection area. modification as required by ts
			//lstLab = collareasamplenoconfig_DAOi.getlabCombo(_UserVO);
			
			//modification for ts. generation strictly based on area code with lab as an option
			lstArea = collareasamplenoconfig_DAOi.getareaCombo(_UserVO);
		
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			mp.put(InvestigationConfig.LIST_AREA_COMBO, lstArea);
			collareasamplenoconfig_VO.setInitializationType("d");
			collareasamplenoconfig_VO.setPatientType("3");


		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public void savemodifyCollAreaSampleNoConfig(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO,
			UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();
			CollAreaSampleNoConfigMstDAOi collareasamplenoconfig_DAOi = new CollAreaSampleNoConfigMstDAO(tx);
			collareasamplenoconfig_DAOi.updateCollAreaSampleNoConfig(collareasamplenoconfig_VO, _UserVO);
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public Map getArea(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstArea = new ArrayList();


		try {
			tx.begin();
			CollAreaSampleNoConfigMstDAOi collareasamplenoconfig_DAOi = new CollAreaSampleNoConfigMstDAO(tx);

			lstArea = collareasamplenoconfig_DAOi.getArea(collareasamplenoconfig_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_AREA_COMBO, lstArea);
			collareasamplenoconfig_VO.setInitializationType("d");

		}	 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	

	public void saveTemplate(TemplateMstVO templateMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();
			TemplateMstDAO templateMstDAO=new TemplateMstDAO(tx);
			String dup=templateMstDAO.checkDuplicateRecord(templateMstVO, _UserVO);
			String dup1=templateMstDAO.checkDuplicateRecordSave(templateMstVO, _UserVO);
			if(dup.equals("0"))
			{
				templateMstDAO.saveTemplate(templateMstVO, _UserVO);
				templateMstVO.setTemplateType("1");
			}
			else{
				throw new HisDuplicateRecordException("Data Already Exists");
			}
			/*if(dup1.equals("1"))
			{
				templateMstDAO.saveDelTemplate(templateMstVO, _UserVO);
				templateMstVO.setTemplateName("");
				templateMstVO.setTemplateType("1");
			}*/
			
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}
	public void fetchModifyTemplate(TemplateMstVO templateMstVO,UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			TemplateMstDAO templateMstDAO=new TemplateMstDAO(tx);
			templateMstDAO.fetchModifyTemplate(templateMstVO, _UserVO);
		}	 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}
	public void saveModifyTemplate(TemplateMstVO templateMstVO,UserVO _UserVO) 
    { 
JDBCTransactionContext tx = new JDBCTransactionContext(); 
String count = ""; 
try { 
    tx.begin(); 
    TemplateMstDAO templateMstDAO=new TemplateMstDAO(tx); 
    String dup=templateMstDAO.checkDuplicateRecord(templateMstVO, _UserVO); 
    if(dup.equals("0")) 
    { 
    templateMstDAO.saveModifyTemplate(templateMstVO, _UserVO); 
    } 
    else 
    { 
        throw new HisDuplicateRecordException("Data Allready Present"); 
    } 
} catch (HisDuplicateRecordException e) { 
    tx.rollback(); 
    throw new HisDuplicateRecordException(e.getMessage()); 
} catch (HisRecordNotFoundException e) { 
    tx.rollback(); 
    throw new HisRecordNotFoundException(e.getMessage()); 
} catch (HisApplicationExecutionException e) { 
    tx.rollback(); 
    System.out.println(e.getMessage()); 
    throw new HisApplicationExecutionException(); 
} catch (HisDataAccessException e) { 
    tx.rollback(); 
    System.out.println(e.getMessage()); 
    throw new HisDataAccessException(); 
} catch (HisException e) { 
    tx.rollback(); 
    System.out.println(e.getMessage()); 
    throw new HisException(); 
} catch (Exception e) { 
    System.out.println(e.getMessage()); 
    tx.rollback(); 
    throw new HisApplicationExecutionException(); 
} finally { 
    tx.close(); 
} 
} 
	//////////////////////////////////////START TEST MAND REFERENCE Master//////////////////////////////////////


	public void saveTestMandRef(List<TestMandRefMasterVO> lstTestMandRefVO,UserVO _UserVO)
	{
/*JDBCTransactionContext tx = new JDBCTransactionContext();
String count = "";
try {
	tx.begin();
	BookMarkMstDAO bookMarkMstDAO=new BookMarkMstDAO(tx);
	String dup=bookMarkMstDAO.checkDuplicateRecordModify(bookMarkMstVO, _UserVO);
	if(dup.equals("0"))*/
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";			

		try
		{
			tx.begin();
			TestMandRefMstDAOi testmandref_DAOi = new TestMandRefMstDAO(tx);

			for(TestMandRefMasterVO testmandref_VO:lstTestMandRefVO)
			{

				/*checkList=testmandref_DAOi.checkDuplicateTestMandRef(testmandref_VO, _UserVO);
				if(checkList==null)
				{*/
				
				if(testmandref_VO.getCriteriaCode().equals("10"))
				{
					checkList=testmandref_DAOi.checkDuplicateTestMandRef(testmandref_VO, _UserVO);
					if(checkList==null)
						testmandref_DAOi.createTestMandRef(testmandref_VO, _UserVO);
					else
						throw new HisDuplicateRecordException("Reference Value Already Exists for this Parameter");
				}
				else
					testmandref_DAOi.createTestMandRef(testmandref_VO, _UserVO);
				/*}else{
					throw new HisDuplicateRecordException("Values Already Exists");
				} */
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
			//tx.commitAll();
			tx.close();
		}

	}


	public Map fetchCheckListTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)

	{
		JDBCTransactionContext tx = new JDBCTransactionContext();


		Map mp = new HashMap();
		List lstParameter = new ArrayList();
		List lstTest = new ArrayList();
		List lstLab=new ArrayList();
		List lstSample=new ArrayList();
		List lstMand=new ArrayList();
		
		
		String criteria="";

		List<TestMandRefMasterVO> lstTestMandRefMasterVO=new ArrayList<TestMandRefMasterVO>();
		try
		{
			tx.begin();
			TestMandRefMstDAOi testmandref_DAOi = new TestMandRefMstDAO(tx);
			
			lstTestMandRefMasterVO=testmandref_DAOi.fetchCheckListTestMandRef(testmandref_VO, _UserVO);
			
			// If there are more than one row, then better to take a list of VO 
						//For this Logic you should iterate over any formbean array
						// Like say int length=fb.getChk().length;
						//Iterate over for loop and populate the formbean values to VO's and add this VO in a List<TestMandRefMasterVO>
						// Example
						  /*
						   * for(int i=0;i<len;i++)
						   * {
						   *   TestMandRefMasterVO voTestMand=new TestMandRefMasterVO();
						   *   voTestMand.setValues
						   * }
						   */
			
			  mp.put(InvestigationConfig.LIST_TESTMANDREF_VO, lstTestMandRefMasterVO);

			lstParameter = testmandref_DAOi.getTestMandRef(_UserVO);
			mp.put(InvestigationConfig.LIST_TESTPARA_COMBO, lstParameter);

			lstTest = testmandref_DAOi.getTestCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
			
			lstLab=testmandref_DAOi.fetchLabCombo(testmandref_VO, _UserVO);
			lstSample=testmandref_DAOi.fetchSampleCombo(testmandref_VO, _UserVO);
			lstMand=testmandref_DAOi.fetchMandCombo(testmandref_VO, _UserVO);

			
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, lstSample);
			mp.put(InvestigationConfig.LIST_MAND_COMBO, lstMand);

			criteria=testmandref_DAOi.fetchCriteriaCode(testmandref_VO, _UserVO);
			testmandref_VO.setCriteriaCode(criteria);
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

	public Map fetchTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstTestName=new ArrayList();
		List lstTestParaName=new ArrayList();

		try
		{
			tx.begin();
			TestMandRefMstDAOi testmandref_DAOi = new TestMandRefMstDAO(tx);
			lstTestName=testmandref_DAOi.getTestCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO,lstTestName);
			lstTestParaName=testmandref_DAOi.getTestMandRef(_UserVO);
			mp.put(InvestigationConfig.LIST_TESTPARA_COMBO,lstTestParaName);
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




	public void savemodifyTestMandRef(List<TestMandRefMasterVO> lstTestMandRefVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			TestMandRefMstDAOi testmandref_DAOi = new TestMandRefMstDAO(tx);
			
			for(TestMandRefMasterVO testmandref_VO:lstTestMandRefVO)
			{
		/*	count=testmandref_DAOi.checkDuplicateModifyTestMandRef(testmandref_VO, _UserVO);
			if(count.equals("0"))
			{*/
				testmandref_DAOi.updateTestMandRef(testmandref_VO, _UserVO);
			/*}else{
				throw new HisDuplicateRecordException("Values Already Exists");
			}*/
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}



	public List<TestMandRefMasterVO> fetchdisplaydataTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<TestMandRefMasterVO> testmandref_listVO=new ArrayList<TestMandRefMasterVO>();
		try
		{
			tx.begin();
			TestMandRefMstDAOi testmandref_DAOi = new TestMandRefMstDAO(tx);
			testmandref_listVO=testmandref_DAOi.fetchdisplaydataTestMandRef(testmandref_VO, _UserVO);
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
		return testmandref_listVO;
	}


	public Map fetchParameterCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstParameter = new ArrayList();


		try {
			tx.begin();
			TestMandRefMstDAOi testmandref_DAOi = new TestMandRefMstDAO(tx);

			lstParameter = testmandref_DAOi.fetchParameterCombo(testmandref_VO, _UserVO);

			mp.put(InvestigationConfig.LIST_TESTPARA_COMBO, lstParameter);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	
	
	public Map fetchCombo(TestMandRefMasterVO testmandref_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstLab = new ArrayList();
		List lstSample = new ArrayList();List lstMand = new ArrayList();
		String criteria="";
		try {
			tx.begin();
			TestMandRefMstDAOi testmandref_DAOi = new TestMandRefMstDAO(tx);

			criteria=testmandref_DAOi.fetchCriteriaCode(testmandref_VO, _UserVO);
			testmandref_VO.setCriteriaCode(criteria);

			
			lstLab=testmandref_DAOi.fetchLabComboFilter(testmandref_VO, _UserVO);
			lstSample=testmandref_DAOi.fetchSampleComboFilter(testmandref_VO, _UserVO);
			lstMand=testmandref_DAOi.fetchMandCombo(testmandref_VO, _UserVO);

			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, lstSample);
			mp.put(InvestigationConfig.LIST_MAND_COMBO, lstMand);


		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	
	
	public List fetchOld(String testCode,String parameterCode, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();


		Map mp = new HashMap();
		List lstOld = new ArrayList();
	
	
		try
		{
			tx.begin();
			TestMandRefMstDAOi testmandref_DAOi = new TestMandRefMstDAO(tx);
			
			lstOld=testmandref_DAOi.fetchOld(testCode,parameterCode, _UserVO);
			
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
		return lstOld;
	}

	public void deleteTestMandRef(List deleteList, String testCode, String parameterCode,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		int k;
		try
		{
			tx.begin();
			TestMandRefMstDAOi testmandref_DAOi = new TestMandRefMstDAO(tx);
			
			for(k=0;k<deleteList.size();k++)
			{
				String  str1=(String)deleteList.get(k);
				testmandref_DAOi.deleteTestMandRef(str1,testCode,parameterCode, _UserVO);
			
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	
	
	
	
	public List getLabSample(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();


		Map mp = new HashMap();
		List lstOld = new ArrayList();
	
	
		try
		{
			tx.begin();
			TestMandRefMstDAOi testmandref_DAOi = new TestMandRefMstDAO(tx);
			
			lstOld=testmandref_DAOi.fetchLabSampleCombo(testmandref_VO, _UserVO);
			
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
		return lstOld;
	}
	
	public Map fetchCheckListLocalTestMandRef(TestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();


		Map mp = new HashMap();
		List lstParameter = new ArrayList();
		List lstTest = new ArrayList();
		List lstLab=new ArrayList();
		List lstSample=new ArrayList();
		List lstMand=new ArrayList();
		
		
		String criteria="";

		List<TestMandRefMasterVO> lstLocalTestMandRefMasterVO=new ArrayList<TestMandRefMasterVO>();
		try
		{
			tx.begin();
			TestMandRefMstDAOi testmandref_DAOi = new TestMandRefMstDAO(tx);
			TestMandRefMstDAOi gtestmandref_DAOi = new TestMandRefMstDAO(tx);
			
			testmandref_VO.setFromGlobal("0");
			lstLocalTestMandRefMasterVO=testmandref_DAOi.fetchCheckListLocalTestMandRef(testmandref_VO, _UserVO);
			
			if(lstLocalTestMandRefMasterVO.isEmpty())
			{
				testmandref_VO.setFromGlobal("1");
				lstLocalTestMandRefMasterVO=testmandref_DAOi.fetchCheckListLocalTestMandRef(testmandref_VO, _UserVO);
				
				
			}	
			
			
			// If there are more than one row, then better to take a list of VO 
						//For this Logic you should iterate over any formbean array
						// Like say int length=fb.getChk().length;
						//Iterate over for loop and populate the formbean values to VO's and add this VO in a List<LocalTestMandRefMasterVO>
						// Example
						  /*
						   * for(int i=0;i<len;i++)
						   * {
						   *   LocalTestMandRefMasterVO voTestMand=new LocalTestMandRefMasterVO();
						   *   voTestMand.setValues
						   * }
						   */
			
			  mp.put(InvestigationConfig.LIST_TESTMANDREF_VO, lstLocalTestMandRefMasterVO);

				lstParameter = gtestmandref_DAOi.getTestMandRef(_UserVO);
				mp.put(InvestigationConfig.LIST_TESTPARA_COMBO, lstParameter);

				lstTest = testmandref_DAOi.getTestCombo(_UserVO);
				mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
			
			lstLab=testmandref_DAOi.fetchLabCombo(testmandref_VO, _UserVO);
			lstSample=testmandref_DAOi.fetchSampleCombo(testmandref_VO, _UserVO);
			lstMand=testmandref_DAOi.fetchMandCombo(testmandref_VO, _UserVO);

			
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, lstSample);
			mp.put(InvestigationConfig.LIST_MAND_COMBO, lstMand);

			criteria=testmandref_DAOi.fetchCriteriaCode(testmandref_VO, _UserVO);
			testmandref_VO.setCriteriaCode(criteria);
			
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


	//////////////////////////////////END test mandatory  Master///////////////////////////////



	/*Loinc Master By Anant Patel*/
	public Map fetchLoincMst(LoincMstVO loincMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstTest = new ArrayList();


		try {
			tx.begin();
			LoincMstDAO loincMstDAO=new LoincMstDAO(tx);
			lstTest=loincMstDAO.getTestCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);

		}	 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	public Map fetchTestPara(LoincMstVO loincMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstTest=new ArrayList();
		List lstTestPara = new ArrayList();
		List lstSample=new ArrayList();
		List lstUom=new ArrayList();
		List lstMethod=new ArrayList();

		try {
			tx.begin();
			LoincMstDAO loincMstDAO=new LoincMstDAO(tx);
			lstTest=loincMstDAO.getTestCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
			lstTestPara=loincMstDAO.getParaByTest(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_PARA_COMBO, lstTestPara);
			lstSample=loincMstDAO.getSampleCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, lstSample);
			lstUom=loincMstDAO.getUomCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, lstUom);
			lstMethod=loincMstDAO.getMethodCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_METHOD_COMBO, lstMethod);
		}	 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
/*	public Map fetchTestUOMData(LoincMstVO loincMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstTest=new ArrayList();
		List lstTestPara = new ArrayList();
		List lstSample=new ArrayList();
		List lstUom=new ArrayList();

		try {
			tx.begin();
			LoincMstDAO loincMstDAO=new LoincMstDAO(tx);
			lstTest=loincMstDAO.getTestCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
			lstTestPara=loincMstDAO.getParaByTest(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_PARA_COMBO, lstTestPara);
			lstUom=loincMstDAO.getUomCombo(loincMstVO, _UserVO);
			lstSample=loincMstDAO.getSampleCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, lstSample);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, lstUom);
		}	 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}*/
	public Map getLoinc(LoincMstVO loincMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstTest=new ArrayList();
		List lstTestPara = new ArrayList();
		List lstSample=new ArrayList();
		List lstUom=new ArrayList();
		List lstMethod=new ArrayList();
		List<LoincMstVO> loincData=new ArrayList<LoincMstVO>();
		try {
			tx.begin();
			LoincMstDAO loincMstDAO=new LoincMstDAO(tx);
			lstTest=loincMstDAO.getTestCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
			lstTestPara=loincMstDAO.getParaByTest(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_PARA_COMBO, lstTestPara);
			lstSample=loincMstDAO.getUomCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, lstSample);
			lstSample=loincMstDAO.getSampleCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, lstSample);
			String testName=loincMstDAO.getTestname(loincMstVO, _UserVO);
			lstMethod=loincMstDAO.getMethodCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_METHOD_COMBO, lstMethod);
			loincMstVO.setTestName(testName);
			loincData=loincMstDAO.getLoinc(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_LOINC_DTLS, loincData);
			loincMstVO.setLoincTime(loincMstVO.getTestCode().split("#")[2]);
			loincMstVO.setLoincScale(loincMstVO.getParaCode().split("#")[1]);
			loincMstVO.setLoincSystem(loincMstVO.getSampleCode().split("#")[1]);
			loincMstVO.setLoincProperty(loincMstVO.getUomCode().split("#")[1]);
			loincMstVO.setMethodCode(loincMstVO.getMethodCode());
		}	 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	public Map fetchModifyLoinc(LoincMstVO loincMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List<LoincMstVO> loincData=new ArrayList<LoincMstVO>();
		List<LoincMstVO> loincData1=new ArrayList<LoincMstVO>();
		Map<String,List<String>> mpLoincData=new HashMap<String, List<String>>();
		try {
			tx.begin();
			LoincMstDAO loincMstDAO=new LoincMstDAO(tx);
			loincMstDAO.fetchModifyLoinc(loincMstVO, _UserVO);
			loincData=loincMstDAO.getLoinc1(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_LOINC_DTLS, loincData);
			if(loincData==null)
			{
			loincData1=loincMstDAO.getLoincBaesdOnLoincNum(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_LOINC_DTLS,loincData1 );
			}
			loincMstDAO.fetchModifyCodeName(loincMstVO, _UserVO);
			loincMstVO.setLoincTime(loincMstVO.getTestCode().split("#")[2]);
			loincMstVO.setLoincScale(loincMstVO.getParaCode().split("#")[1]);
			loincMstVO.setLoincSystem(loincMstVO.getSampleCode().split("#")[1]);
			loincMstVO.setLoincProperty(loincMstVO.getUomCode().split("#")[1]);
		}	 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	public Map getSearch(LoincMstVO loincMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();
		List lstTest=new ArrayList();
		List lstTestPara = new ArrayList();
		List lstSample=new ArrayList();
		List lstUom=new ArrayList();
		List lstMethod=new ArrayList();
		List<LoincMstVO> loincData=new ArrayList<LoincMstVO>();
		Map<String,List<String>> mpLoincData=new HashMap<String, List<String>>();
		try {
			tx.begin();
			LoincMstDAO loincMstDAO=new LoincMstDAO(tx);
			lstTest=loincMstDAO.getTestCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
			lstTestPara=loincMstDAO.getParaByTest(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_PARA_COMBO, lstTestPara);
			lstSample=loincMstDAO.getUomCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, lstSample);
			lstSample=loincMstDAO.getSampleCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, lstSample);
			lstMethod=loincMstDAO.getMethodCombo(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_METHOD_COMBO, lstMethod);
			String testName=loincMstDAO.getTestname(loincMstVO, _UserVO);
			loincMstVO.setTestName(testName);
			loincData=loincMstDAO.searchLoinc(loincMstVO, _UserVO);
			mp.put(InvestigationConfig.LIST_LOINC_DTLS, loincData);
			loincMstVO.setLoincTime(loincMstVO.getTestCode().split("#")[2]);
			loincMstVO.setLoincScale(loincMstVO.getParaCode().split("#")[1]);
			loincMstVO.setLoincSystem(loincMstVO.getSampleCode().split("#")[1]);
			loincMstVO.setLoincProperty(loincMstVO.getUomCode().split("#")[1]);
			loincMstVO.setMethodCode(loincMstVO.getMethodCode());
		}catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}

	public void saveLoinc(LoincMstVO loincMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			LoincMstDAO loincMstDAO=new LoincMstDAO(tx);
			String dup=loincMstDAO.checkDuplicateLoinc(loincMstVO, _UserVO);
			String dup2=loincMstDAO.checkDuplicateLoinc2(loincMstVO, _UserVO);
			if(dup.equals("0"))
			{
				if(dup2.equals("0"))
				{
					loincMstDAO.saveLoinc(loincMstVO, _UserVO);
				}
				else
					throw new HisDuplicateRecordException();
			}
			else
				throw new HisDuplicateRecordException();
		}
			 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDuplicateRecordException();
		}catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		
	}
	public void saveModifyLoinc(LoincMstVO loincMstVO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			LoincMstDAO loincMstDAO=new LoincMstDAO(tx);
			String isDeleted=loincMstDAO.checkDeletedData(loincMstVO, _UserVO);
			if(isDeleted.equals("1"))
			{
			loincMstDAO.updateDeletedLoinc(loincMstVO, _UserVO);
			}
			String dup=loincMstDAO.checkDuplicateLoinc(loincMstVO, _UserVO);
			if(dup.equals("0"))
			{
				loincMstDAO.modifySaveLoinc(loincMstVO, _UserVO);
			}
		}
			 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		
	}

	//////////////////////////////////////START local TEST MAND REFERENCE Master//////////////////////////////////////


	public void saveLocalTestMandRef(List<LocalTestMandRefMasterVO> lstLocalTestMandRefVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";			
		try
		{
			tx.begin();
			LocalTestMandRefMstDAOi testmandref_DAOi = new LocalTestMandRefMstDAO(tx);

			for(LocalTestMandRefMasterVO testmandref_VO:lstLocalTestMandRefVO)
			{

				/*checkList=testmandref_DAOi.checkDuplicateLocalTestMandRef(testmandref_VO, _UserVO);
				if(checkList==null)
				{*/
				
				
				if(testmandref_VO.getCriteriaCode().equals("10"))
				{
					checkList=testmandref_DAOi.checkDuplicateLocalTestMandRef(testmandref_VO, _UserVO);
					if(checkList==null)
						testmandref_DAOi.createLocalTestMandRef(testmandref_VO, _UserVO);
					else
						throw new HisDuplicateRecordException("Reference Value Already Exists for this Parameter");
				}
				else				
					testmandref_DAOi.createLocalTestMandRef(testmandref_VO, _UserVO);
				/*}else{
					throw new HisDuplicateRecordException("Values Already Exists");
				} */
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
			//tx.commitAll();
			tx.close();
		}

	}


	public Map fetchCheckListLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();


		Map mp = new HashMap();
		List lstParameter = new ArrayList();
		List lstTest = new ArrayList();
		List lstLab=new ArrayList();
		List lstSample=new ArrayList();
		List lstMand=new ArrayList();
		
		
		String criteria="";

		List<LocalTestMandRefMasterVO> lstLocalTestMandRefMasterVO=new ArrayList<LocalTestMandRefMasterVO>();
		try
		{
			tx.begin();
			LocalTestMandRefMstDAOi testmandref_DAOi = new LocalTestMandRefMstDAO(tx);
			TestMandRefMstDAOi gtestmandref_DAOi = new TestMandRefMstDAO(tx);

			testmandref_VO.setFromGlobal("0");
			lstLocalTestMandRefMasterVO=testmandref_DAOi.fetchCheckListLocalTestMandRef(testmandref_VO, _UserVO);
			
			if(lstLocalTestMandRefMasterVO.isEmpty())
			{
				testmandref_VO.setFromGlobal("1");
				lstLocalTestMandRefMasterVO=testmandref_DAOi.fetchCheckListLocalTestMandRef(testmandref_VO, _UserVO);
				
				
			}	
			
			
			// If there are more than one row, then better to take a list of VO 
						//For this Logic you should iterate over any formbean array
						// Like say int length=fb.getChk().length;
						//Iterate over for loop and populate the formbean values to VO's and add this VO in a List<LocalTestMandRefMasterVO>
						// Example
						  /*
						   * for(int i=0;i<len;i++)
						   * {
						   *   LocalTestMandRefMasterVO voTestMand=new LocalTestMandRefMasterVO();
						   *   voTestMand.setValues
						   * }
						   */
			
			  mp.put(InvestigationConfig.LIST_TESTMANDREF_VO, lstLocalTestMandRefMasterVO);

				lstParameter = gtestmandref_DAOi.getTestMandRef(_UserVO);
				mp.put(InvestigationConfig.LIST_TESTPARA_COMBO, lstParameter);

				lstTest = testmandref_DAOi.getTestCombo(_UserVO);
				mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);
			
			lstLab=testmandref_DAOi.fetchLabCombo(testmandref_VO, _UserVO);
			lstSample=testmandref_DAOi.fetchSampleCombo(testmandref_VO, _UserVO);
			lstMand=testmandref_DAOi.fetchMandCombo(testmandref_VO, _UserVO);

			
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, lstSample);
			mp.put(InvestigationConfig.LIST_MAND_COMBO, lstMand);

			criteria=testmandref_DAOi.fetchCriteriaCode(testmandref_VO, _UserVO);
			testmandref_VO.setCriteriaCode(criteria);
			
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

	public Map fetchLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstTestName=new ArrayList();
		List lstTestParaName=new ArrayList();

		try
		{
			tx.begin();
			LocalTestMandRefMstDAOi testmandref_DAOi = new LocalTestMandRefMstDAO(tx);
			lstTestName=testmandref_DAOi.getTestCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO,lstTestName);
			/*lstTestParaName=testmandref_DAOi.getLocalTestMandRef(_UserVO);
			mp.put(InvestigationConfig.LIST_TESTPARA_COMBO,lstTestParaName);*/
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




	public void savemodifyLocalTestMandRef(List<LocalTestMandRefMasterVO> lstLocalTestMandRefVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			LocalTestMandRefMstDAOi testmandref_DAOi = new LocalTestMandRefMstDAO(tx);
			
			for(LocalTestMandRefMasterVO testmandref_VO:lstLocalTestMandRefVO)
			{
		/*	count=testmandref_DAOi.checkDuplicateModifyLocalTestMandRef(testmandref_VO, _UserVO);
			if(count.equals("0"))
			{*/
				testmandref_DAOi.updateLocalTestMandRef(testmandref_VO, _UserVO);
			/*}else{
				throw new HisDuplicateRecordException("Values Already Exists");
			}*/
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}



	public List<LocalTestMandRefMasterVO> fetchdisplaydataLocalTestMandRef(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<LocalTestMandRefMasterVO> testmandref_listVO=new ArrayList<LocalTestMandRefMasterVO>();
		try
		{
			tx.begin();
			LocalTestMandRefMstDAOi testmandref_DAOi = new LocalTestMandRefMstDAO(tx);
			testmandref_listVO=testmandref_DAOi.fetchdisplaydataLocalTestMandRef(testmandref_VO, _UserVO);
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
		return testmandref_listVO;
	}


	public Map fetchParameterCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstParameter = new ArrayList();


		try {
			tx.begin();
			LocalTestMandRefMstDAOi testmandref_DAOi = new LocalTestMandRefMstDAO(tx);

			lstParameter = testmandref_DAOi.fetchParameterCombo(testmandref_VO, _UserVO);

			mp.put(InvestigationConfig.LIST_TESTPARA_COMBO, lstParameter);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	
	
	public Map fetchCombo(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp = new HashMap();

		List lstLab = new ArrayList();
		List lstSample = new ArrayList();List lstMand = new ArrayList();
		String criteria="";
		try {
			tx.begin();
			LocalTestMandRefMstDAOi testmandref_DAOi = new LocalTestMandRefMstDAO(tx);

			criteria=testmandref_DAOi.fetchCriteriaCode(testmandref_VO, _UserVO);
			testmandref_VO.setCriteriaCode(criteria);

			
			lstLab=testmandref_DAOi.fetchLabComboFilter(testmandref_VO, _UserVO);
			lstSample=testmandref_DAOi.fetchSampleComboFilter(testmandref_VO, _UserVO);
			lstMand=testmandref_DAOi.fetchMandCombo(testmandref_VO, _UserVO);

			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
			mp.put(InvestigationConfig.LIST_SAMPLE_COMBO, lstSample);
			mp.put(InvestigationConfig.LIST_MAND_COMBO, lstMand);


		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mp;
	}
	
	
	public List fetchOldLocal(String testCode,String parameterCode, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();


		Map mp = new HashMap();
		List lstOld = new ArrayList();
	
	
		try
		{
			tx.begin();
			LocalTestMandRefMstDAOi testmandref_DAOi = new LocalTestMandRefMstDAO(tx);
			
			lstOld=testmandref_DAOi.fetchOld(testCode,parameterCode, _UserVO);
			
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
		return lstOld;
	}

	public void deleteLocalTestMandRef(List deleteList, String testCode, String parameterCode,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		int k;
		try
		{
			tx.begin();
			LocalTestMandRefMstDAOi testmandref_DAOi = new LocalTestMandRefMstDAO(tx);
			
			for(k=0;k<deleteList.size();k++)
			{
				String  str1=(String)deleteList.get(k);
				testmandref_DAOi.deleteLocalTestMandRef(str1,testCode,parameterCode, _UserVO);
			
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	
	
	public List getLabSample(LocalTestMandRefMasterVO testmandref_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();


		Map mp = new HashMap();
		List lstOld = new ArrayList();
	
	
		try
		{
			tx.begin();
			LocalTestMandRefMstDAOi testmandref_DAOi = new LocalTestMandRefMstDAO(tx);
			
			lstOld=testmandref_DAOi.fetchLabSampleCombo(testmandref_VO, _UserVO);
			
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
		return lstOld;
	}



	//////////////////////////////////END test mandatory  Master///////////////////////////////
	
	////////////////////////////////////fetch global for local lab//////////////////////////
	
	public Map fetchGlobalLabCombo(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List labcombo=new ArrayList();
		List departcombo=new ArrayList();
		List locationcombo=new ArrayList();
		List labInchargecombo=new ArrayList();

		try
		{
			tx.begin();
			LocalLabMstDAOi labMstDAOi = new LocalLabMstDAO(tx);
			
			labcombo=labMstDAOi.getGlobalLabCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, labcombo);
			

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
	
	public List getMachine(LabTestGlobalMstVO labTestGlobalMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstMachine=new ArrayList();
		try
		{
			tx.begin();
			InvSampleMstDAO labTestMstDAO = new InvSampleMstDAO(tx);
			lstMachine=labTestMstDAO.getMachine(labTestGlobalMstVO, _UserVO);
			//mp.put(InvestigationConfig.LIST_TEST_COMBO, testcombo);
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
		return lstMachine;
	}

	
	public List getMachineLocal(LabTestGlobalMstVO labTestGlobalMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstMachine=new ArrayList();
		try
		{
			tx.begin();
			LabTestLocalMstDAO labTestLocalMstDAO=new LabTestLocalMstDAO(tx);
			lstMachine=labTestLocalMstDAO.getMachineLocal(labTestGlobalMstVO, _UserVO);
			//mp.put(InvestigationConfig.LIST_TEST_COMBO, testcombo);
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
		return lstMachine;
	}

	
	
	public Map fetchParaMachineCombo(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstMachineName=new ArrayList();

		try
		{
			tx.begin();
			MachineTestParameterMstDAOi machineTestParaMst_DAOi = new MachineTestParameterMstDAO(tx);
			lstMachineName=machineTestParaMst_DAOi.getMachineCombo( _UserVO);
			mp.put(InvestigationConfig.PARAMETER_MACHINE_COMBO,lstMachineName);
			mp.put(InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM,null);
			mp.put(InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM_ADDED,null);
			
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
	

	public Map fetchParaTestCombo(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lstTestName=new ArrayList();

		try
		{
			tx.begin();
			MachineTestParameterMstDAOi machineTestParaMst_DAOi = new MachineTestParameterMstDAO(tx);
			lstTestName=machineTestParaMst_DAOi.getTestCombo( _UserVO);
			mp.put(InvestigationConfig.PARAMETER_TEST_COMBO,lstTestName);
			
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

	public List<MachineTestParameterMasterVO> displayParameterDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<MachineTestParameterMasterVO> machineTestParamMst_listVO=new ArrayList<MachineTestParameterMasterVO>();
		try
		{
			tx.begin();
			MachineTestParameterMstDAOi machineTestParaMst_DAOi = new MachineTestParameterMstDAO(tx);
			machineTestParamMst_listVO=machineTestParaMst_DAOi.displayParameterDetails(machineTestParaMstVO, _UserVO);
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
		return machineTestParamMst_listVO;
	}
	
	
	/*public Map fetchCheckListMachineTestParaCombo(MachineTestParameterMasterVO testparacombo_VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();


		Map mp = new HashMap();
		List lstParameter = new ArrayList();
		List lstTest = new ArrayList();



		try
		{
			tx.begin();
			MachineTestParameterMstDAOi testparacombo_DAOi = new MachineTestParameterMstDAO(tx);
			testparacombo_DAOi.fetchCheckListMachineTestParaCombo(testparacombo_VO, _UserVO);
			
			lstParameter = testparacombo_DAOi.getTestParaCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_TESTPARA_COMBO, lstParameter);

			lstTest = testparacombo_DAOi.getTestCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);


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
	*/
	
	public void saveDetails(List<MachineTestParameterMasterVO> machineTestParamMst_listVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String checkList="";			

		try
		{
			tx.begin();
			
			String paraCount=""+machineTestParamMst_listVO.size();
			
			MachineTestParameterMstDAOi machineTestParaMst_DAOi = new MachineTestParameterMstDAO(tx);
			for(MachineTestParameterMasterVO machineTestParaMstVO:machineTestParamMst_listVO)
			{

				machineTestParaMstVO.setParaCount(paraCount);
				machineTestParaMst_DAOi.saveDetails(machineTestParaMstVO, _UserVO);
			}	
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
			//tx.commitAll();
			tx.close();
		}

	}
	
	public Map fetchModifyDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();


		Map mp = new HashMap();
		List lstMachine = new ArrayList();
		List lstTest = new ArrayList();
		List<MachineTestParameterMasterVO> machineTestParamMst_listVO = new ArrayList<MachineTestParameterMasterVO>();

		try
		{
			tx.begin();
			MachineTestParameterMstDAOi machineTestParaMst_DAOi = new MachineTestParameterMstDAO(tx);
			machineTestParamMst_listVO =	machineTestParaMst_DAOi.fetchModifyDetails(machineTestParaMstVO, _UserVO);
					
			mp.put(InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM, machineTestParamMst_listVO);
			
			lstMachine = machineTestParaMst_DAOi.getMachineCombo(_UserVO);
			mp.put(InvestigationConfig.PARAMETER_MACHINE_COMBO, lstMachine);

			lstTest = machineTestParaMst_DAOi.getTestCombo(_UserVO);
			mp.put(InvestigationConfig.PARAMETER_TEST_COMBO, lstTest);


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

	
	
	public Map displayDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();


		Map mp = new HashMap();
		List lstMachine = new ArrayList();
		List lstTest = new ArrayList();
		List<MachineTestParameterMasterVO> machineTestParamMst_listVO = new ArrayList<MachineTestParameterMasterVO>();

		try
		{
			tx.begin();
			MachineTestParameterMstDAOi machineTestParaMst_DAOi = new MachineTestParameterMstDAO(tx);
			machineTestParamMst_listVO =	machineTestParaMst_DAOi.displayDetails(machineTestParaMstVO, _UserVO);
					
			mp.put(InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM_ADDED, machineTestParamMst_listVO);
			
			


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


	
	public void savemodifyMachineTestParaCombo(List<MachineTestParameterMasterVO> machineTestParamMst_listVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		try
		{
			tx.begin();
			MachineTestParameterMstDAOi machineTestParaMst_DAOi = new MachineTestParameterMstDAO(tx);
			for(MachineTestParameterMasterVO machineTestParaMstVO:machineTestParamMst_listVO)
			{

				
				machineTestParaMst_DAOi.updateTestParaCombo(machineTestParaMstVO, _UserVO);
			}	
			
			
			
		/*	tx.begin();
			MachineTestParameterMstDAOi machinetestparacombo_DAOi = new MachineTestParameterMstDAO(tx);
			count=machinetestparacombo_DAOi.checkDuplicateModifyTestParaCombo(machinetestparacombo_VO, _UserVO);
			if(count.equals("0"))
			{
				machinetestparacombo_DAOi.updateTestParaCombo(machinetestparacombo_VO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Values Already Exists");
			} 
		*/
		}	
			
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}


	
	





///////////////////////////////////////////START Machine Master ///////////////////////////////////

	public void saveMachine(List<machineMstVO> lstVO,machineMstVO machinemst_vo, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();

			machineMstDAOi machinemst_DAOi = new machineMstDAO(tx);


			//check for duplicate name
			String alreadyExists=machinemst_DAOi.checkDuplicateMachine(machinemst_vo, _UserVO);
			
			if(alreadyExists==null)
			{
				//for inserting new machine generate new machine id
				String newCode=machinemst_DAOi.generateMachineId(machinemst_vo, _UserVO);
				machinemst_vo.setMachineCode(newCode);
							
				//create in machine mst
				machinemst_DAOi.createMachine(machinemst_vo, _UserVO);
				
				//CREATING COMMPORTS
				for(int i=0;i<lstVO.size();i++)
				{
					machineMstVO _vo=new machineMstVO();
					
					_vo=lstVO.get(i);
					_vo.setMachineCode(newCode);
					
					machinemst_DAOi.createMachineCommports(_vo, _UserVO);
								
				}
			
				
			}
			
			else{
				throw new HisDuplicateRecordException("Data Already Exists");
			}
			
			
			
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	
	public void saveBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		try {
			tx.begin();
			BookMarkMstDAO bookMarkMstDAO=new BookMarkMstDAO(tx);
			String dup=bookMarkMstDAO.checkDuplicateRecord(bookMarkMstVO, _UserVO);
			String dup1=bookMarkMstDAO.checkDuplicateRecordSave(bookMarkMstVO, _UserVO);
			if(dup.equals("0"))
			{
				bookMarkMstDAO.saveBookMark(bookMarkMstVO, _UserVO);
				bookMarkMstVO.setBookmarkType("1");
			}
			else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
			/*if(dup1.equals("1"))
			{
				bookMarkMstDAO.saveDelBookMark(bookMarkMstVO, _UserVO);
				bookMarkMstVO.setBookmarkName("");
				bookMarkMstVO.setBookmarkType("1");
			}*/
			
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}
	public void fetchModifyBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			BookMarkMstDAO bookMarkMstDAO=new BookMarkMstDAO(tx);
			bookMarkMstDAO.fetchModifyBOokMark(bookMarkMstVO, _UserVO);
		}	 catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}
	public void saveModifyBookMark(BookMarkMstVO bookMarkMstVO,UserVO _UserVO) 
    { 
JDBCTransactionContext tx = new JDBCTransactionContext(); 
String count = ""; 
try { 
    tx.begin(); 
    BookMarkMstDAO bookMarkMstDAO=new BookMarkMstDAO(tx); 
    String dup=bookMarkMstDAO.checkDuplicateRecordd(bookMarkMstVO, _UserVO); 
    if(dup.equals("0")) 
    { 
    bookMarkMstDAO.saveModifyBookMark(bookMarkMstVO, _UserVO); 
    } 
    else 
    { 
        throw new HisDuplicateRecordException("Data Allready Present"); 
    } 
} catch (HisDuplicateRecordException e) { 
    tx.rollback(); 
    throw new HisDuplicateRecordException(e.getMessage()); 
} catch (HisRecordNotFoundException e) { 
    tx.rollback(); 
    throw new HisRecordNotFoundException(e.getMessage()); 
} catch (HisApplicationExecutionException e) { 
    tx.rollback(); 
    System.out.println(e.getMessage()); 
    throw new HisApplicationExecutionException(); 
} catch (HisDataAccessException e) { 
    tx.rollback(); 
    System.out.println(e.getMessage()); 
    throw new HisDataAccessException(); 
} catch (HisException e) { 
    tx.rollback(); 
    System.out.println(e.getMessage()); 
    throw new HisException(); 
} catch (Exception e) { 
    System.out.println(e.getMessage()); 
    tx.rollback(); 
    throw new HisApplicationExecutionException(); 
} finally { 
    tx.close(); 
} 
} 
	
	

	public void saveModifyMachine(List<machineMstVO> lstVO,machineMstVO machinemst_vo,List deleteList, UserVO _UserVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count = "";
		String code="";
		try {
			tx.begin();

			machineMstDAOi machinemst_DAOi = new machineMstDAO(tx);


			//check for duplicate name
			String alreadyExists=machinemst_DAOi.checkDuplicateModifyMachine(machinemst_vo, _UserVO);
			
			if(alreadyExists.equals("0"))
			{
				
				
			
				machinemst_DAOi.updateMachine(machinemst_vo, _UserVO);		
			
				//CREATING COMMPORTS
				for(int i=0;i<lstVO.size();i++)
				{
					machineMstVO _vo=new machineMstVO();
					
					_vo=lstVO.get(i);
					_vo.setMachineCode(machinemst_vo.getMachineCode());
					code=machinemst_vo.getMachineCode();
					_vo.setCommFlag(machinemst_vo.getCommFlag());
					//check if commport already exists
					count=	machinemst_DAOi.checkCommPortAvailable(_vo,_UserVO);
								 
					if (count.equals("0"))
					 machinemst_DAOi.createMachineCommports(_vo, _UserVO);
					else
					machinemst_DAOi.updateMachineCommports(_vo, _UserVO);
				}
				
				for(int i=0;i<deleteList.size();i++)
					
				{
					
					machinemst_DAOi.deleteMachineCommPort(code,deleteList.get(i).toString(), _UserVO);
					
					
					
				}
			
				
			}
			
			else{
				throw new HisDuplicateRecordException("Data Already Exists");
			}
			
			
			
		} catch (HisDuplicateRecordException e) {
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}


public Map getEssentialMachineCombo(machineMstVO machinemst_vo, UserVO _UserVO) {
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map mp = new HashMap();
	List lstLab = new ArrayList();
	List lstCommunicationFlag = new ArrayList();
	List lstFormat = new ArrayList();


	try {
		tx.begin();
		machineMstDAOi machinemst_DAOi = new machineMstDAO(tx);

		lstLab = machinemst_DAOi.getLocationCombo(machinemst_vo, _UserVO);
		mp.put(InvestigationConfig.LIST_MACHINE_MST_LOCATION_COMBO, lstLab);

		lstCommunicationFlag = machinemst_DAOi.getCommFlag(machinemst_vo, _UserVO);
		mp.put(InvestigationConfig.LIST_MACHINE_MST_COMMPORT, lstCommunicationFlag);

		lstFormat = machinemst_DAOi.getFormat(machinemst_vo, _UserVO);
		mp.put(InvestigationConfig.LIST_MACHINE_MST_FORMAT, lstFormat);



	} catch (HisRecordNotFoundException e) {
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	} catch (HisApplicationExecutionException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException();
	} catch (HisDataAccessException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisDataAccessException();
	} catch (HisException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisException();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisApplicationExecutionException();
	} finally {
		tx.close();
	}
	return mp;

}



public List<machineMstVO> fetchMachineDetails(machineMstVO machinemst_VO, UserVO _UserVO) {
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map mp = new HashMap();
	List<machineMstVO> machine_lst=new ArrayList<machineMstVO>();

	
	List lstArea = new ArrayList();
	List lstSelectedArea = new ArrayList();

	try {
		tx.begin();
		machineMstDAOi labcollectionarea_DAOi = new machineMstDAO(tx);
		
		machine_lst=labcollectionarea_DAOi.fetchMachineAndCommportDetails(machinemst_VO,_UserVO);


	}catch (HisApplicationExecutionException e) {
	tx.rollback();
	System.out.println(e.getMessage());
	throw new HisApplicationExecutionException();
} catch (HisDataAccessException e) {
	tx.rollback();
	System.out.println(e.getMessage());
	throw new HisDataAccessException();
} catch (HisException e) {
	tx.rollback();
	System.out.println(e.getMessage());
	throw new HisException();
} catch (Exception e) {
	System.out.println(e.getMessage());
	tx.rollback();
	throw new HisApplicationExecutionException();
} finally {
	tx.close();
}
	return machine_lst;
}




// //END Lab CollectionArea Master ///


//test group info master
public void modifyTemplateValue(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO) {
	JDBCTransactionContext tx = new JDBCTransactionContext();
	String count = "";
	try {
		tx.begin();

		TestGroupInfoMstDAOi testgroupinfo_DAOi = new TestGroupInfoMstDAO(tx);


		//update the template value in test group info master
		testgroupinfo_DAOi.modifyTemplateValue(testgroupinfo_VO, _UserVO);
		//update the printing template value in printing template master
		testgroupinfo_DAOi.modifyPrintingTemplateValue(testgroupinfo_VO, _UserVO);


	} catch (HisDuplicateRecordException e) {
		tx.rollback();
		throw new HisDuplicateRecordException(e.getMessage());
	} catch (HisRecordNotFoundException e) {
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	} catch (HisApplicationExecutionException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException();
	} catch (HisDataAccessException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisDataAccessException();
	} catch (HisException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisException();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisApplicationExecutionException();
	} finally {
		tx.close();
	}
}


public void modifyTemplateValueLocal(TestGroupInfoMasterVO testgroupinfo_VO, UserVO _UserVO) {
	JDBCTransactionContext tx = new JDBCTransactionContext();
	String count = "";
	String count1="";
	try {
		tx.begin();

		TestGroupInfoLocalMstDAOi testgroupinfolocal_DAOi = new TestGroupInfoLocalMstDAO(tx);
		if(testgroupinfo_VO.getGlobalTemplate()==null)
			testgroupinfo_VO.setGlobalTemplate("1");
		count1 = testgroupinfolocal_DAOi.checkUserGroupCodeTestGroupInfoLocal(testgroupinfo_VO,
				_UserVO);
		
		//update the template value in test group info master
		if(count1.equals("0") )
		testgroupinfolocal_DAOi.modifyTemplateValueLocal(testgroupinfo_VO, _UserVO);
		else
			throw new HisDuplicateRecordException("User Group Code Already Exist");
		//update the printing template value in printing template master
	//	testgroupinfolocal_DAOi.modifyPrintingTemplateValueLocal(testgroupinfo_VO, _UserVO);

		//insert local template value in printing template master
		//needs primary key check too !!!!!
		if(testgroupinfo_VO.getGlobalTemplate().equals("0"))//create new template
		if(testgroupinfo_VO.getHmode()!=null && testgroupinfo_VO.getHmode().equals("MODIFYSAVE")==false)
		{
			if(count1.equals("0"))
		testgroupinfolocal_DAOi.insertPrintingTemplateValueLocal(testgroupinfo_VO, _UserVO);
			else
				throw new HisDuplicateRecordException("User Group Code Already Exist");
		}
		
	} catch (HisDuplicateRecordException e) {
		//tx.rollback();
		throw new HisDuplicateRecordException(e.getMessage());
	} catch (HisRecordNotFoundException e) {
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	} catch (HisApplicationExecutionException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException();
	} catch (HisDataAccessException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisDataAccessException();
	} catch (HisException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisException();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisApplicationExecutionException();
	} finally {
		tx.close();
	}
}





/*public Map getTemplateLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO) {
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map mp = new HashMap();

	List lstTest = new ArrayList();
	List lstSelectedTest = new ArrayList();
	String templateValue=null;

	try {
		tx.begin();
		TestGroupInfoLocalMstDAOi testgroupinfolocal_DAOi = new TestGroupInfoLocalMstDAO(tx);
		TestGroupInfoMstDAOi testgroupinfo_DAOi = new TestGroupInfoMstDAO(tx);
		testgroupinfolocal_VO.setCheckLocal("1");
		lstSelectedTest = testgroupinfolocal_DAOi.getselectedtestComboRight(testgroupinfolocal_VO,_UserVO);

		if(lstSelectedTest != null)
		{
			testgroupinfolocal_VO.setCheckLocal("1");
			mp.put(InvestigationConfig.LIST_SELECTED_COMBO, lstSelectedTest);
			lstTest = testgroupinfolocal_DAOi.gettestComboLeft(testgroupinfolocal_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);

		}

		else
		{
			testgroupinfolocal_VO.setCheckLocal("0");	

			lstTest = testgroupinfolocal_DAOi.gettestComboLeft(testgroupinfolocal_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);

			lstSelectedTest = testgroupinfolocal_DAOi.getselectedtestComboRight(testgroupinfolocal_VO,_UserVO);
			mp.put(InvestigationConfig.LIST_SELECTED_COMBO, lstSelectedTest);
			
			testgroupinfolocal_DAOi.fetchParameter(testgroupinfolocal_VO,_UserVO);
			mp.put("TemplateValue",testgroupinfolocal_VO );
			


		}



	} catch (HisRecordNotFoundException e) {
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
} 		catch (HisApplicationExecutionException e) {
tx.rollback();
System.out.println(e.getMessage());
throw new HisApplicationExecutionException();
} catch (HisDataAccessException e) {
tx.rollback();
System.out.println(e.getMessage());
throw new HisDataAccessException();
} catch (HisException e) {
tx.rollback();
System.out.println(e.getMessage());
throw new HisException();
} catch (Exception e) {
System.out.println(e.getMessage());
tx.rollback();
throw new HisApplicationExecutionException();
} finally {
tx.close();
}
	return mp;
}*/




//////////////////////////////////////////START EXTERNAL LAB MASTER - PUNEET ///////////////////////////////////
/*
public void saveParameter(externalLabMasterVO parametermaster_VO,UserVO _UserVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	String checkList="";
	try
	{
		tx.begin();
		externalLabMstDAOi parametermaster_DAOi = new externalLabMstDAO(tx);
		checkList=parametermaster_DAOi.checkDuplicateParameter(parametermaster_VO, _UserVO);

		if(checkList==null)
		{
			parametermaster_DAOi.createParameter(parametermaster_VO, _UserVO);
		}else{
			throw new HisDuplicateRecordException("Data Already Exists");
		} 
	}
	catch (HisDuplicateRecordException e)
	{
		tx.rollback();
		throw new HisDuplicateRecordException(e.getMessage());
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
}

public void fetchCheckListParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map mp=new HashMap();
	List lstLoinic=new ArrayList();
	try
	{
		tx.begin();
		externalLabMstDAOi parametermaster_DAOi = new externalLabMstDAO(tx);
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

}

public void fetchParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	try
	{
		tx.begin();
		externalLabMstDAOi parametermaster_DAOi = new externalLabMstDAO(tx);
		parametermaster_DAOi.fetchParameter(parametermaster_VO, _UserVO);
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
}

public void savemodifyParameter(externalLabMasterVO parametermaster_VO,UserVO _UserVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	String count="";
	try
	{
		tx.begin();
		externalLabMstDAOi parametermaster_DAOi = new externalLabMstDAO(tx);
		count=parametermaster_DAOi.checkDuplicateModifyParameter(parametermaster_VO, _UserVO);
		if(count.equals("0"))
		{

			parametermaster_DAOi.updateParameter(parametermaster_VO, _UserVO);
		}else{
			throw new HisDuplicateRecordException("Data Already Exists");
		} 
	}
	catch (HisDuplicateRecordException e)
	{
		tx.rollback();
		throw new HisDuplicateRecordException(e.getMessage());
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
}

*/
////////////////////////////////////////END EXTERNAL LAB MASTER/////////////////////////////////////////


//GET LAB BASED ON SELECTED AREA FOR COLL AREA BASED SAMPLE NO GENERATION
public Map getAreaWiseLab(CollAreaSampleNoConfigMasterVO collareasamplenoconfig_VO, UserVO _UserVO) {
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map mp = new HashMap();

	List lstLab = new ArrayList();


	try {
		tx.begin();
		CollAreaSampleNoConfigMstDAOi collareasamplenoconfig_DAOi = new CollAreaSampleNoConfigMstDAO(tx);

		lstLab = collareasamplenoconfig_DAOi.getAreaWiseLab(collareasamplenoconfig_VO, _UserVO);
		mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);
		collareasamplenoconfig_VO.setInitializationType("d");

	}	 catch (HisApplicationExecutionException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException();
	} catch (HisDataAccessException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisDataAccessException();
	} catch (HisException e) {
		tx.rollback();
		System.out.println(e.getMessage());
		throw new HisException();
	} catch (Exception e) {
		System.out.println(e.getMessage());
		tx.rollback();
		throw new HisApplicationExecutionException();
	} finally {
		tx.close();
	}
	return mp;
}


//FILM MASTER START
 public Map fetchFilmD(FilmMstVO testNewMasterVO, UserVO _UserVO)
  {
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map mp=new HashMap();
	List lsttest=new ArrayList();
	List itemnamecombo=new ArrayList();
	List storenamecombo=new ArrayList();
	
	List lstPrintTemplate=new ArrayList();
	try
	{
		tx.begin();
		FilmMstDAO testMstDAOi = new FilmMstDAO(tx);
		//bCheckListMstDAOi.fetchCheckList(bCheckListMasterVO, _UserVO);
		lsttest=testMstDAOi.getFilmCombo( _UserVO);
		mp.put(InvestigationConfig.LIST_FILM_TESTNAME_COMBO, lsttest);
		itemnamecombo=testMstDAOi.getitemnameCombo( _UserVO);
		mp.put(InvestigationConfig.LIST_FILM_ITEMNAME_COMBO,itemnamecombo);
		storenamecombo=testMstDAOi.getstorenameCombo( _UserVO);
		mp.put(InvestigationConfig.LIST_FILM_STORENAME_COMBO,storenamecombo);
		
	}
	catch (HisDuplicateRecordException e)
	{
		tx.rollback();
		throw new HisDuplicateRecordException(e.getMessage());

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

	public void saveFilm(FilmMstVO testNewMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int checkList=0;
		try
		{
			tx.begin();
			FilmMstDAO testMstDAOi = new FilmMstDAO(tx);

			checkList=testMstDAOi.checkDuplicateFilm(testNewMasterVO, _UserVO);
		if(checkList==0)
			{
				testMstDAOi.createFilm(testNewMasterVO, _UserVO);
			//testMstDAOi.updatePrintingTemplate(testNewMasterVO, _UserVO);
		}else{
			throw new HisDuplicateRecordException("Data Already Exists");
		} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	
	public Map fetchFilm(FilmMstVO testNewMasterVO, UserVO _UserVO,String filmid,String slno)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lsttest=new ArrayList();
		List lstitem=new ArrayList();
		List storeitem=new ArrayList();
		
		try
		{
			tx.begin();
			FilmMstDAO testMstDAOi = new FilmMstDAO(tx);
			testMstDAOi.fetchFilm(testNewMasterVO, _UserVO,filmid,slno);

			lsttest=testMstDAOi.getFilmCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_FILM_TESTNAME_COMBO, lsttest);
			
			lstitem=testMstDAOi.getitemnameCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_FILM_ITEMNAME_COMBO, lstitem);
			
			storeitem=testMstDAOi.getstorenameCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_FILM_STORENAME_COMBO, storeitem);
			
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
 
    
	public void savemodifyFilm(FilmMstVO testNewMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		try
		{
			tx.begin();
			FilmMstDAO testMstDAOi = new FilmMstDAO(tx);
			count=testMstDAOi.checkModifyDuplicateFilm(testNewMasterVO, _UserVO);
			if(count==0)
			{
				testMstDAOi.updateFilm(testNewMasterVO, _UserVO);
			//	testMstDAOi.updatePrintingTemplateOnModify(testNewMasterVO, _UserVO);


			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	
	//FILM MASTER END
	public void saveDetailsLabConsumable(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		String unit=labConsumableMstVO.getUomCode();
		try
		{
			tx.begin();
			LabConsumableMstDAO labConsumableMstDAO=new LabConsumableMstDAO(tx);
			labConsumableMstDAO.generateKey(labConsumableMstVO, _UserVO);
			labConsumableMstVO.setUomCode(unit);
			labConsumableMstDAO.saveDetails(labConsumableMstVO, _UserVO);
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	public void saveModifyDetails(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		try
		{
			tx.begin();
			LabConsumableMstDAO labConsumableMstDAO=new LabConsumableMstDAO(tx);
			labConsumableMstDAO.updateDetails(labConsumableMstVO, _UserVO);
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	}
	public Map fetchDetails(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		try
		{
			tx.begin();
			LabConsumableMstDAO labConsumableMstDAO=new LabConsumableMstDAO(tx);
			labConsumableMstDAO.fetchDetails(labConsumableMstVO, _UserVO);
			//labConsumableMstDAO.saveDetails(labConsumableMstVO, _UserVO);
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
		return null;
	}
	public int checkDuplicateForModify(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		try
		{
			tx.begin();
			LabConsumableMstDAO labConsumableMstDAO=new LabConsumableMstDAO(tx);
			if(labConsumableMstVO.getOtherItemID()==null||labConsumableMstVO.getOtherItemID().equals(""))
			{
				labConsumableMstVO.setOtherItemID("0");
			}
		count=	labConsumableMstDAO.checkDuplicateForModify(labConsumableMstVO, _UserVO);
			//labConsumableMstDAO.saveDetails(labConsumableMstVO, _UserVO);
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
		return count;
	}
	public Map fetchDetailsOfItems(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		try
		{
			tx.begin();
			ItemLabTestMappingMstDAO ItemLabTestMappingMstDAO =new ItemLabTestMappingMstDAO(tx);
			ItemLabTestMappingMstDAO.fetchDetailsOfItems(itemLabTestMappingMstVO, _UserVO);
			
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
		return null;
	}
	
	public Map saveDetailsModifyItemCosnumable(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		try
		{
			tx.begin();
			ItemLabTestMappingMstDAO ItemLabTestMappingMstDAO =new ItemLabTestMappingMstDAO(tx);
			ItemLabTestMappingMstDAO.updateDetails(itemLabTestMappingMstVO, _UserVO);
			
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
		return null;
	}
	public Map saveDetailsItemCosnumable(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		try
		{
			tx.begin();
			ItemLabTestMappingMstDAO ItemLabTestMappingMstDAO =new ItemLabTestMappingMstDAO(tx);
			ItemLabTestMappingMstDAO.saveDetails(itemLabTestMappingMstVO, _UserVO);
			
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
		return null;
	}
	public Map getEssentialForLabConsumables(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstTest=new ArrayList();
		List lstItemType=new ArrayList();
		List lstUOM=new ArrayList();
		int count=0;
		Map mp=new HashMap();
		try
		{
			tx.begin();
			LabConsumableMstDAO labConsumableMstDAO=new LabConsumableMstDAO(tx);
			lstTest=labConsumableMstDAO.getItemNameCombo(_UserVO);
			lstItemType=labConsumableMstDAO.getItemTypeCombo(_UserVO);
			lstUOM=labConsumableMstDAO.getUOMName(_UserVO);
			mp.put(InvestigationConfig.ITEMNAME_COMBO_HSTT_ITEM_MST, lstTest);
			mp.put(InvestigationConfig.ITEMTYPE_COMBO_HIVT_ITEMTYPE_MST, lstItemType);
			mp.put(InvestigationConfig.LIST_UOM_COMBO, lstUOM);
			
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	public Map getEssentialsForItemConsumable(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		List otherItemList=new ArrayList();
		List lstItemType=new ArrayList();
		List storeitem=new ArrayList();
		Map mp=new HashMap();
		try
		{
			tx.begin();
			ItemLabTestMappingMstDAO ItemLabTestMappingMstDAO =new ItemLabTestMappingMstDAO(tx);
			otherItemList=ItemLabTestMappingMstDAO.getOtherItemList( _UserVO); 
			lstItemType=ItemLabTestMappingMstDAO.getItemTypeCombo(_UserVO);
			mp.put(InvestigationConfig.OTHER_ID_LIST,otherItemList);
			mp.put(InvestigationConfig.ITEMTYPE_COMBO_HIVT_ITEMTYPE_MST, lstItemType);
			storeitem=ItemLabTestMappingMstDAO.getstorenameCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_FILM_STORENAME_COMBO, storeitem);
			
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	public Map lotDuplicacyCheck(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		List otherItemList=new ArrayList();
		Map mp=new HashMap();
		try
		{
			tx.begin();
			ItemLabTestMappingMstDAO ItemLabTestMappingMstDAO =new ItemLabTestMappingMstDAO(tx);
		mp=	ItemLabTestMappingMstDAO.getLotDuplicacy(itemLabTestMappingMstVO, _UserVO); 
			
			
			
			
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	public List getItemListCombo(ItemLabTestMappingMstVO itemLabTestMappingMstVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		List itemListCombo=new ArrayList();
		
		try
		{
			tx.begin();
			ItemLabTestMappingMstDAO ItemLabTestMappingMstDAO =new ItemLabTestMappingMstDAO(tx);
			itemListCombo=	ItemLabTestMappingMstDAO.getItemsCombo(itemLabTestMappingMstVO, _UserVO); 
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
		
		return itemListCombo;
	}
	
	public Map getEssentialForLabItemMapping(LabItemMappingMasterVO labItemMappingMasterVO,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int count=0;
		List lstLab=new ArrayList();
		Map mp=new HashMap();
		try
		{
			tx.begin();
			LabItemMappingMstDAO labItemMappingMstDAO=new LabItemMappingMstDAO(tx);
			lstLab=labItemMappingMstDAO.getlabCombo(_UserVO);
			mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	public Map getLabItemsMappingList(LabItemMappingMasterVO labItemMappingMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List lstLeft=new ArrayList();
		List lstRight=new ArrayList();
		Map mp=new HashMap();
		try
		{
			tx.begin();
			LabItemMappingMstDAO labItemMappingMstDAO=new LabItemMappingMstDAO(tx);
			lstLeft=labItemMappingMstDAO.getItemsComboLeft(labItemMappingMasterVO,_UserVO);
			mp.put(InvestigationConfig.ITEM_LIST_LEFT_COMBO, lstLeft);
			lstRight=labItemMappingMstDAO.getItemsComboRight(labItemMappingMasterVO,_UserVO);
			mp.put(InvestigationConfig.ITEM_LIST_RIGHT_COMBO, lstRight);

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	public  Map saveNewItemsList(LabItemMappingMasterVO labItemMappingMasterVO, ArrayList deleteList,ArrayList insertList,UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List lstLeft=new ArrayList();
		List lstRight=new ArrayList();
		Map mp=new HashMap();
		int i=0;
		try
		{
			tx.begin();
			LabItemMappingMstDAO labItemMappingMstDAO=new LabItemMappingMstDAO(tx);
			for(i=0;i<insertList.size();i++)
			{
			int count=	labItemMappingMstDAO.getCount((String)insertList.get(i),labItemMappingMasterVO,_UserVO);
			if(count>0)
			{
				labItemMappingMstDAO.updateList((String)insertList.get(i),labItemMappingMasterVO,_UserVO);
			}
			else
			{
				labItemMappingMstDAO.InsertItemInList((String)insertList.get(i),labItemMappingMasterVO,_UserVO);
			}
			}
			for(i=0;i<deleteList.size();i++)
			{
				labItemMappingMstDAO.DelelteListItem((String)deleteList.get(i),labItemMappingMasterVO,_UserVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
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
	





	//organic MASTER START
	public Map fetchFilmD1(invOrganicMstVO testNewMasterVO, UserVO _UserVO,String namecode)
	  {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mp=new HashMap();
		List lsttest=new ArrayList();
		List itemnamecombo=new ArrayList();
		List storenamecombo=new ArrayList();
		
		List lstPrintTemplate=new ArrayList();
		try
		{
			tx.begin();
			FilmMstDAO testMstDAOi = new FilmMstDAO(tx);
			//bCheckListMstDAOi.fetchCheckList(bCheckListMasterVO, _UserVO);
			lsttest=testMstDAOi.getFilmCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_FILM_TESTNAME_COMBO, lsttest);
			itemnamecombo=testMstDAOi.getitemnameCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_FILM_ITEMNAME_COMBO,itemnamecombo);
			storenamecombo=testMstDAOi.getstorenameCombo( _UserVO);
			mp.put(InvestigationConfig.LIST_FILM_STORENAME_COMBO,storenamecombo);
			
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());

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

		public void saveOrganism(invOrganicMstVO testNewMasterVO,UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			int checkList=0;
			int checkOrder=0;
			try
			{
				tx.begin();
				invOrganicMstDAO testMstDAOi = new invOrganicMstDAO(tx);

				checkList=testMstDAOi.checkDuplicateOrganism(testNewMasterVO, _UserVO);
				checkOrder=testMstDAOi.checkDuplicateOrderOrganism(testNewMasterVO, _UserVO);
			if(checkList==0 && checkOrder==0)
				{
					testMstDAOi.createOrganism(testNewMasterVO, _UserVO);
				//testMstDAOi.updatePrintingTemplate(testNewMasterVO, _UserVO);
			}else{
				throw new HisDuplicateRecordException("Data Already Exists");
			} 
			}
			catch (HisDuplicateRecordException e)
			{
				tx.rollback();
				throw new HisDuplicateRecordException(e.getMessage());
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
		}
		
		public Map fetchOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO,String namecode)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List lsttest=new ArrayList();
			List lstitem=new ArrayList();
			List storeitem=new ArrayList();
			
			try
			{
				tx.begin();
				invOrganicMstDAO testMstDAOi = new invOrganicMstDAO(tx);
				testMstDAOi.fetchOrganism(testNewMasterVO, _UserVO,namecode);

				
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


		@Override
		public Map fetchiOrganism(invOrganicMstVO testNewMasterVO,
				UserVO _UserVO) {
			// TODO Auto-generated method stub
			return null;
		}


		

	 
	  
		public void savemodifyOrganism(invOrganicMstVO testNewMasterVO,UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			int count=0;
			int checkOrder=0;
			try
			{
				tx.begin();
				invOrganicMstDAO testMstDAOi = new invOrganicMstDAO(tx);
				count=testMstDAOi.checkModifyDuplicateOrganism(testNewMasterVO, _UserVO);
				checkOrder=testMstDAOi.checkModifyDuplicateOrderOrganism(testNewMasterVO, _UserVO);
				if(count==0 || checkOrder==0)
				{
					testMstDAOi.updateOrganism(testNewMasterVO, _UserVO);
				//	testMstDAOi.updatePrintingTemplateOnModify(testNewMasterVO, _UserVO);


				}else{
					throw new HisDuplicateRecordException("Data Already Exists");
				} 
			}
			catch (HisDuplicateRecordException e)
			{
				tx.rollback();
				throw new HisDuplicateRecordException(e.getMessage());
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
		}
		
		//organic MASTER END
		
		
		
		
		//Antibiotic MASTER START
		
		public Map fetchFilmD1(invAntibioticMstVO testNewMasterVO, UserVO _UserVO,String namecode)
		  {
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List lsttest=new ArrayList();
			List itemnamecombo=new ArrayList();
			List storenamecombo=new ArrayList();
			
			List lstPrintTemplate=new ArrayList();
			try
			{
				tx.begin();
				FilmMstDAO testMstDAOi = new FilmMstDAO(tx);
				//bCheckListMstDAOi.fetchCheckList(bCheckListMasterVO, _UserVO);
				lsttest=testMstDAOi.getFilmCombo( _UserVO);
				mp.put(InvestigationConfig.LIST_FILM_TESTNAME_COMBO, lsttest);
				itemnamecombo=testMstDAOi.getitemnameCombo( _UserVO);
				mp.put(InvestigationConfig.LIST_FILM_ITEMNAME_COMBO,itemnamecombo);
				storenamecombo=testMstDAOi.getstorenameCombo( _UserVO);
				mp.put(InvestigationConfig.LIST_FILM_STORENAME_COMBO,storenamecombo);
				
			}
			catch (HisDuplicateRecordException e)
			{
				tx.rollback();
				throw new HisDuplicateRecordException(e.getMessage());

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

			public void saveAntibiotic(invAntibioticMstVO testNewMasterVO,UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				int checkList=0;
				int checkOrder=0;
				try
				{
					tx.begin();
					invAntibioticMstDAO testMstDAOi = new invAntibioticMstDAO(tx);

					checkList=testMstDAOi.checkDuplicateAntibiotic(testNewMasterVO, _UserVO);
					checkOrder=testMstDAOi.checkDuplicateOrderAntibiotic(testNewMasterVO, _UserVO);
				if(checkList==0 && checkOrder==0)
					{
						testMstDAOi.createAntibiotic(testNewMasterVO, _UserVO);
					//testMstDAOi.updatePrintingTemplate(testNewMasterVO, _UserVO);
				}else{
					throw new HisDuplicateRecordException("Data Already Exists");
				} 
				}
				catch (HisDuplicateRecordException e)
				{
					tx.rollback();
					throw new HisDuplicateRecordException(e.getMessage());
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
			}
			
			public Map fetchAntibiotic(invAntibioticMstVO testNewMasterVO, UserVO _UserVO,String namecode)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List lsttest=new ArrayList();
				List lstitem=new ArrayList();
				List storeitem=new ArrayList();
				
				try
				{
					tx.begin();
					invAntibioticMstDAO testMstDAOi = new invAntibioticMstDAO(tx);
					testMstDAOi.fetchAntibiotic(testNewMasterVO, _UserVO,namecode);

					
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


			@Override
			public Map fetchiAntibiotic(invAntibioticMstVO testNewMasterVO,
					UserVO _UserVO) {
				// TODO Auto-generated method stub
				return null;
			}


			

		 
		  
			public void savemodifyAntibiotic(invAntibioticMstVO testNewMasterVO,UserVO _UserVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				int count=0;
				int checkOrder=0;
				try
				{
					tx.begin();
					invAntibioticMstDAO testMstDAOi = new invAntibioticMstDAO(tx);
					count=testMstDAOi.checkModifyDuplicateAntibiotic(testNewMasterVO, _UserVO);
					checkOrder=testMstDAOi.checkModifyDuplicateOrderAntibiotic(testNewMasterVO, _UserVO);
					if(count==0 || checkOrder==0)
					{
						testMstDAOi.updateAntibiotic(testNewMasterVO, _UserVO);
					//	testMstDAOi.updatePrintingTemplateOnModify(testNewMasterVO, _UserVO);


					}else{
						throw new HisDuplicateRecordException("Data Already Exists");
					} 
				}
				catch (HisDuplicateRecordException e)
				{
					tx.rollback();
					throw new HisDuplicateRecordException(e.getMessage());
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
			}
			
			//Antibiotic MASTER END

			
			
			
			//OrganicAntibiotic MASTER START
			
			public Map fetchFilmD1(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO,String namecode)
			  {
				JDBCTransactionContext tx = new JDBCTransactionContext();
				Map mp=new HashMap();
				List lsttest=new ArrayList();
				List itemnamecombo=new ArrayList();
				List storenamecombo=new ArrayList();
				
				List lstPrintTemplate=new ArrayList();
				try
				{
					tx.begin();
					FilmMstDAO testMstDAOi = new FilmMstDAO(tx);
					//bCheckListMstDAOi.fetchCheckList(bCheckListMasterVO, _UserVO);
					lsttest=testMstDAOi.getFilmCombo( _UserVO);
					mp.put(InvestigationConfig.LIST_FILM_TESTNAME_COMBO, lsttest);
					itemnamecombo=testMstDAOi.getitemnameCombo( _UserVO);
					mp.put(InvestigationConfig.LIST_FILM_ITEMNAME_COMBO,itemnamecombo);
					storenamecombo=testMstDAOi.getstorenameCombo( _UserVO);
					mp.put(InvestigationConfig.LIST_FILM_STORENAME_COMBO,storenamecombo);
					
				}
				catch (HisDuplicateRecordException e)
				{
					tx.rollback();
					throw new HisDuplicateRecordException(e.getMessage());

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

		
			public void saveOrganicAntibiotic(invOrganicAntibioticMappingMstVO[] insert_labcannedmaster_VO,invOrganicAntibioticMappingMstVO[] delete_labcannedmaster_VO, UserVO _UserVO,invOrganicAntibioticMappingMstVO[] update) {
				JDBCTransactionContext tx = new JDBCTransactionContext();
				String count = "";
				try {
					tx.begin();

					invOrganicAntibioticMAppingMstDAO labcanned_DAOi = new invOrganicAntibioticMAppingMstDAO(tx);


					//for inserting new records

					if(insert_labcannedmaster_VO!=null && insert_labcannedmaster_VO.length!=0){

						for(int i=0;i < insert_labcannedmaster_VO.length;i++){

							count = labcanned_DAOi.checkPrimaryKeyGlobalLabCannedMaster(insert_labcannedmaster_VO[i],
									_UserVO);
							if (count.equals("0"))
								labcanned_DAOi.createGlobalLabCannedMaster(insert_labcannedmaster_VO[i], _UserVO);
							else
								labcanned_DAOi.updateValidGlobalLabCannedMaster(insert_labcannedmaster_VO[i], _UserVO);

						}
					}

					if(delete_labcannedmaster_VO!=null && delete_labcannedmaster_VO.length!=0){

						for(int i=0;i < delete_labcannedmaster_VO.length;i++)
							labcanned_DAOi.deleteGlobalLabCannedMaster(delete_labcannedmaster_VO[i], _UserVO);

					}


					if(update!=null && update.length!=0){

						for(int i=0;i < update.length;i++){

							count = labcanned_DAOi.checkPrimaryKeyGlobalLabCannedMaster(update[i],
									_UserVO);
							if (count.equals("0"))
								labcanned_DAOi.createGlobalLabCannedMaster(update[i], _UserVO);
							else
								labcanned_DAOi.updateValidGlobalLabCannedMaster(update[i], _UserVO);

						}
					}
					


				} catch (HisDuplicateRecordException e) {
					tx.rollback();
					throw new HisDuplicateRecordException(e.getMessage());
				} catch (HisRecordNotFoundException e) {
					tx.rollback();
					throw new HisRecordNotFoundException(e.getMessage());
				} catch (HisApplicationExecutionException e) {
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisApplicationExecutionException();
				} catch (HisDataAccessException e) {
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisDataAccessException();
				} catch (HisException e) {
					tx.rollback();
					System.out.println(e.getMessage());
					throw new HisException();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					tx.rollback();
					throw new HisApplicationExecutionException();
				} 
				finally {
					tx.close();
				}
			}
				
				public Map fetchOrganicAntibiotic(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO,String namecode)
				{
					JDBCTransactionContext tx = new JDBCTransactionContext();
					Map mp=new HashMap();
					List lsttest=new ArrayList();
					List lstitem=new ArrayList();
					List storeitem=new ArrayList();
					
					try
					{
						tx.begin();
						invOrganicAntibioticMAppingMstDAO testMstDAOi = new invOrganicAntibioticMAppingMstDAO(tx);
						testMstDAOi.fetchOrganicAntibiotic(testNewMasterVO, _UserVO,namecode);

						
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


				@Override
				public Map fetchiOrganicAntibiotic(invOrganicAntibioticMappingMstVO testNewMasterVO,
						UserVO _UserVO) {
					// TODO Auto-generated method stub
					return null;
				}


				

			 
			  
				public void savemodifyOrganicAntibiotic(invOrganicAntibioticMappingMstVO testNewMasterVO,UserVO _UserVO)
				{
					JDBCTransactionContext tx = new JDBCTransactionContext();
					int count=0;
					int checkOrder=0;
					try
					{
						tx.begin();
						invOrganicAntibioticMAppingMstDAO testMstDAOi = new invOrganicAntibioticMAppingMstDAO(tx);
						count=testMstDAOi.checkModifyDuplicateOrganicAntibiotic(testNewMasterVO, _UserVO);
						checkOrder=testMstDAOi.checkModifyDuplicateOrderOrganicAntibiotic(testNewMasterVO, _UserVO);
						if(count==0 || checkOrder==0)
						{
							testMstDAOi.updateOrganicAntibiotic(testNewMasterVO, _UserVO);
						//	testMstDAOi.updatePrintingTemplateOnModify(testNewMasterVO, _UserVO);


						}else{
							throw new HisDuplicateRecordException("Data Already Exists");
						} 
					}
					catch (HisDuplicateRecordException e)
					{
						tx.rollback();
						throw new HisDuplicateRecordException(e.getMessage());
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
				}
				
				
				 public Map fetchOrganicAntibioticD1(invOrganicAntibioticMappingMstVO testNewMasterVO, UserVO _UserVO)
				  {
					JDBCTransactionContext tx = new JDBCTransactionContext();
					Map mp=new HashMap();
					List lsttest=new ArrayList();
					List itemnamecombo=new ArrayList();
					List storenamecombo=new ArrayList();
					
					List lstPrintTemplate=new ArrayList();
					try
					{
						tx.begin();
						invOrganicAntibioticMAppingMstDAO testMstDAOi = new invOrganicAntibioticMAppingMstDAO(tx);
						//bCheckListMstDAOi.fetchCheckList(bCheckListMasterVO, _UserVO);
						lsttest=testMstDAOi.getOrganicNameCombo( _UserVO);
						mp.put(InvestigationConfig.LIST_ORGANIC_NAME_COMBO, lsttest);
						
					}
					catch (HisDuplicateRecordException e)
					{
						tx.rollback();
						throw new HisDuplicateRecordException(e.getMessage());

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

				
				 
				 public Map getGlobalLabCanned(invOrganicAntibioticMappingMstVO labcannedmaster_VO, UserVO _UserVO) {
						JDBCTransactionContext tx = new JDBCTransactionContext();
						Map mp = new HashMap();
						List<invOrganicAntibioticMappingMstVO> labcanned_listVO = new ArrayList<invOrganicAntibioticMappingMstVO>();

						List lstArea = new ArrayList();
						List lstSelectedArea = new ArrayList();
						List lsttest = new ArrayList();
						List<invOrganicAntibioticMappingMstVO>	 lstTestSeqNo = new ArrayList<invOrganicAntibioticMappingMstVO>();

						try {
							tx.begin();
							invOrganicAntibioticMAppingMstDAO labcanned_DAOi = new invOrganicAntibioticMAppingMstDAO(tx);

							lstArea = labcanned_DAOi.getnewglobalCannedComboLeft(labcannedmaster_VO,_UserVO);
							mp.put(InvestigationConfig.LIST_CANNED_COMBO, lstArea);

							//lstTestSeqNo = labcanned_DAOi.getselectedorhamismsequence(labcannedmaster_VO,_UserVO);

							//mp.put(InvestigationConfig.LIST_TEST_SEQ_NO_ANTIBOTIC, lstTestSeqNo);

							
							
							lstSelectedArea = labcanned_DAOi.getselectedglobalCannedComboRight(labcannedmaster_VO,_UserVO,labcanned_listVO);
							mp.put(InvestigationConfig.LIST_SELECTED_CANNED_COMBO, lstSelectedArea);
							mp.put(InvestigationConfig.LIST_TEST_SEQ_NO_ANTIBOTIC, labcanned_listVO);

							
							lsttest=labcanned_DAOi.getOrganicNameCombo( _UserVO);
							mp.put(InvestigationConfig.LIST_ORGANIC_NAME_COMBO, lsttest);
							
						}
						catch (HisApplicationExecutionException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisApplicationExecutionException();
						} catch (HisDataAccessException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisDataAccessException();
						} catch (HisException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisException();
						} catch (Exception e) {
							System.out.println(e.getMessage());
							tx.rollback();
							throw new HisApplicationExecutionException();
						} finally {
							tx.close();
						}
						return mp;
					}
					
				 
				 public Map fetchGlobalLabCanned(invOrganicAntibioticMappingMstVO labcannedmaster_VO, UserVO _UserVO) {
						JDBCTransactionContext tx = new JDBCTransactionContext();
						Map mp = new HashMap();
						List lstLab = new ArrayList();


						try {
							tx.begin();
							invOrganicAntibioticMAppingMstDAO labcanned_DAOi = new invOrganicAntibioticMAppingMstDAO(tx);

							lstLab = labcanned_DAOi.getlabCannedCombo(labcannedmaster_VO,_UserVO);
							mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);



						} catch (HisRecordNotFoundException e) {
							tx.rollback();
							throw new HisRecordNotFoundException(e.getMessage());
						} catch (HisApplicationExecutionException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisApplicationExecutionException();
						} catch (HisDataAccessException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisDataAccessException();
						} catch (HisException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisException();
						} catch (Exception e) {
							System.out.println(e.getMessage());
							tx.rollback();
							throw new HisApplicationExecutionException();
						} finally {
							tx.close();
						}
						return mp;
					}
					
				//OrganicAntibiotic MASTER END

				 
				 //addendum reason mst

					public void saveAddendum(invAddendumReasonMstVO testNewMasterVO,UserVO _UserVO)
					{
						JDBCTransactionContext tx = new JDBCTransactionContext();
						int checkList=0;
						int checkOrder=0;
						try
						{
							tx.begin();
							invAddendumReasonMstDAO testMstDAOi = new invAddendumReasonMstDAO(tx);

							
								testMstDAOi.createAddendum(testNewMasterVO, _UserVO);
							//testMstDAOi.updatePrintingTemplate(testNewMasterVO, _UserVO);
						
						}
						catch (HisDuplicateRecordException e)
						{
							tx.rollback();
							throw new HisDuplicateRecordException(e.getMessage());
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
					}
					
					public Map fetchAddendum(invAddendumReasonMstVO testNewMasterVO, UserVO _UserVO,String namecode)
					{
						JDBCTransactionContext tx = new JDBCTransactionContext();
						Map mp=new HashMap();
						List lsttest=new ArrayList();
						List lstitem=new ArrayList();
						List storeitem=new ArrayList();
						
						try
						{
							tx.begin();
							invAddendumReasonMstDAO testMstDAOi = new invAddendumReasonMstDAO(tx);
							testMstDAOi.fetchAddendum(testNewMasterVO, _UserVO,namecode);

							
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


					public void savemodifyAddendum(invAddendumReasonMstVO testNewMasterVO,UserVO _UserVO)
					{
						JDBCTransactionContext tx = new JDBCTransactionContext();
						int count=0;
						int checkOrder=0;
						try
						{
							tx.begin();
							invAddendumReasonMstDAO testMstDAOi = new invAddendumReasonMstDAO(tx);
							
								testMstDAOi.updateAddendum(testNewMasterVO, _UserVO);
							//	testMstDAOi.updatePrintingTemplateOnModify(testNewMasterVO, _UserVO);


							
						}
						catch (HisDuplicateRecordException e)
						{
							tx.rollback();
							throw new HisDuplicateRecordException(e.getMessage());
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
					}
					
					
					public List getTemplate(LabTestGlobalMstVO labTestGlobalMstVO, UserVO _UserVO)
					{
						JDBCTransactionContext tx = new JDBCTransactionContext();
						Map mp=new HashMap();
						List lstPrintTemplate=new ArrayList();
						try
						{
							tx.begin();
							InvSampleMstDAO labTestMstDAO = new InvSampleMstDAO(tx);
							lstPrintTemplate=labTestMstDAO.getTestPrintingTemplateCombo( _UserVO);
							//mp.put(InvestigationConfig.LIST_TEST_PRINTING_TEMPLATE, lstPrintTemplate);
							//mp.put(InvestigationConfig.LIST_TEST_COMBO, testcombo);
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
						return lstPrintTemplate;
					}


					public void saveTestParameterMasterForm(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO)
					{
						JDBCTransactionContext tx = new JDBCTransactionContext();
						String checkList="";
						boolean noMatch=true;
						List<TestParameterMasterVO> lstTestParaCode=new  ArrayList<TestParameterMasterVO>();
						try
						{
							tx.begin();
							InvTestParameterMstDAOi TestParameterMstDAOi = new InvTestParameterMstDAO(tx);

							
							
							
									TestParameterMstDAOi.createTestParameter(testParameterMasterVO, _UserVO);
													}
						catch (HisDuplicateRecordException e)
						{
							tx.rollback();
							throw new HisDuplicateRecordException(e.getMessage());
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
					}
					

					
					
					public Map fetchParameterComboReqForm( UserVO _UserVO)
					{
						JDBCTransactionContext tx = new JDBCTransactionContext();
						Map mp=new HashMap();
						List testcombo=new ArrayList();
						List mastertypetestcombo=new ArrayList();
						List parametercombo=new ArrayList();
						List criteriacombo=new ArrayList();
						List elementtypecombo=new ArrayList();
						List loincScale=new ArrayList();
						List urlcombo=new ArrayList();
						try
						{
							tx.begin();
							InvTestParameterMstDAOi TestParameterMstDAOi = new InvTestParameterMstDAO(tx);
							//labMstDAOi.fetchLab(labMasterVO, _UserVO);

							testcombo=TestParameterMstDAOi.getTestComboReqform( _UserVO);
							mp.put(InvestigationConfig.TEST_COMBO, testcombo);

							
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

					
					
					
					public void updateTestParameter(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO)
					{
						JDBCTransactionContext tx = new JDBCTransactionContext();
						String checkList="";
						boolean noMatch=true;
						List<TestParameterMasterVO> lstTestParaCode=new  ArrayList<TestParameterMasterVO>();
						try
						{
							tx.begin();
							InvTestParameterMstDAOi TestParameterMstDAOi = new InvTestParameterMstDAO(tx);

							
							TestParameterMstDAOi.updateTestParameter(testParameterMasterVO, _UserVO);
							
							TestParameterMstDAOi.updateisreqformmapped(testParameterMasterVO, _UserVO);
							
													}
						catch (HisDuplicateRecordException e)
						{
							tx.rollback();
							throw new HisDuplicateRecordException(e.getMessage());
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
					}

					
					
					public List getTestgroup(UserBookMarkMstVO userBookMarkMstVO,UserVO _UserVO)
					{
						JDBCTransactionContext tx = new JDBCTransactionContext();
						List unit=new ArrayList();
						try
						{
							tx.begin();
							UserBookMarkMstDAO userBookMarkMstDAO=new UserBookMarkMstDAO(tx);
							unit=userBookMarkMstDAO.getTestCombogroup(userBookMarkMstVO, _UserVO);


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
						return unit;
					}

					
					
					/*Userwise Bookmark Mst Start*/
					

					// by prashant to fetch all test in group and also check duplicate test in bookmark
					public List<UserwiseBookMarkMstVO> getTestByGroup(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO)
					{
						JDBCTransactionContext tx = new JDBCTransactionContext();
						List<UserwiseBookMarkMstVO> test=new ArrayList<UserwiseBookMarkMstVO>();
						List duplicateTest=new ArrayList();
						StringBuffer strduplicateTest = new StringBuffer();
						try
						{
							tx.begin();
							UserwiseBookMarkMstDAO userwiseBookMarkMstDAO=new UserwiseBookMarkMstDAO(tx);
							test=userwiseBookMarkMstDAO.getTestByGroup(userwiseBookMarkMstVO, _UserVO);
							
							strduplicateTest.append("(");
							for(int i=0; i<test.size(); i++)
							 {
								strduplicateTest.append(test.get(i).getTestCode()+",");
							 }
							strduplicateTest.append("0)");
							
							duplicateTest=userwiseBookMarkMstDAO.getDuplicateTestInBookmark(userwiseBookMarkMstVO, _UserVO, strduplicateTest);
							if(duplicateTest.isEmpty() || duplicateTest.size()<1 ) {}
							else {return duplicateTest;}
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
						return test;
					}

					 
					public void modifySaveUserBookmark(UserwiseBookMarkMstVO userwiseBookMarkMstVO , UserVO _UserVO) {
						JDBCTransactionContext tx = new JDBCTransactionContext();
						String count = "";
						try {
							tx.begin();
							  UserwiseBookMarkMstDAO  userwiseBookMarkMstDAO=new   UserwiseBookMarkMstDAO(tx);
							  userwiseBookMarkMstDAO.saveModifyUserBookMark(userwiseBookMarkMstVO, _UserVO);
						} catch (HisDuplicateRecordException e) {
							tx.rollback();
							throw new HisDuplicateRecordException(e.getMessage());
						} catch (HisRecordNotFoundException e) {
							tx.rollback();
							throw new HisRecordNotFoundException(e.getMessage());
						} catch (HisApplicationExecutionException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisApplicationExecutionException();
						} catch (HisDataAccessException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisDataAccessException();
						} catch (HisException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisException();
						} catch (Exception e) {
							System.out.println(e.getMessage());
							tx.rollback();
							throw new HisApplicationExecutionException();
						} finally {
							tx.close();
						}
					}
					public Map getBookMarkType(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO) {
						JDBCTransactionContext tx = new JDBCTransactionContext();
						Map mp = new HashMap();

						List lstUser = new ArrayList();
						List lstDept = new ArrayList();
						List lstunit = new ArrayList();
						List lstLab = new ArrayList();
						List lstTest = new ArrayList();
						List lstTestGroup = new ArrayList();
						List lstBookMark = new ArrayList();
						try {
							tx.begin();
							  UserwiseBookMarkMstDAO userwiseBookMarkMstDAO = new   UserwiseBookMarkMstDAO(tx);
							
							lstBookMark = userwiseBookMarkMstDAO.getBookMarkCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_BOOKMARK_COMBO, lstBookMark);
							
							lstUser = userwiseBookMarkMstDAO.getUserCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_USER_COMBO, lstUser);

							lstDept = userwiseBookMarkMstDAO.getDeptCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_DEPT_COMBO, lstDept);

							lstLab = userwiseBookMarkMstDAO.getLabCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);

							lstTest = userwiseBookMarkMstDAO.getTestCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);

							lstTestGroup = userwiseBookMarkMstDAO.getTestGroupCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_TEST_GROUP_COMBO, lstTestGroup);
							String bookmarkType=userwiseBookMarkMstDAO.getBookMarkType(userwiseBookMarkMstVO, _UserVO);
							userwiseBookMarkMstVO.setBookarkType(bookmarkType);
							userwiseBookMarkMstVO.setIsTestGroup("0");
						} catch (HisApplicationExecutionException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisApplicationExecutionException();
						} catch (HisDataAccessException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisDataAccessException();
						} catch (HisException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisException();
						} catch (Exception e) {
							System.out.println(e.getMessage());
							tx.rollback();
							throw new HisApplicationExecutionException();
						} finally {
							tx.close();
						}
						return mp;
					}
					public void getTestName(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO) 
					{ 
					    JDBCTransactionContext tx = new JDBCTransactionContext(); 
					    String checkList=""; 
					    try 
					    { 
					        tx.begin(); 
					        UserwiseBookMarkMstDAO userwiseBookMarkMstDAO = new  UserwiseBookMarkMstDAO(tx); 
					        userwiseBookMarkMstDAO.getTestName(userwiseBookMarkMstVO, _UserVO);

					    } 
					    catch (HisDuplicateRecordException e) 
					    { 
					        tx.rollback(); 
					        throw new HisDuplicateRecordException(e.getMessage()); 
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
					} 
					public Map fetchModifyUserBookMark(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO) { 
					    JDBCTransactionContext tx = new JDBCTransactionContext(); 
					    Map mp = new HashMap(); 
					     
					    List lstUser = new ArrayList(); 
					    List lstDept = new ArrayList(); 
					    List lstunit = new ArrayList(); 
					    List lstLab = new ArrayList(); 
					    List lstTest = new ArrayList(); 
					    List lstTestGroup = new ArrayList(); 
					    List lstBookMark = new ArrayList(); 
					    try { 
					        tx.begin(); 
					        UserwiseBookMarkMstDAO userwiseBookMarkMstDAO = new UserwiseBookMarkMstDAO(tx); 

					        lstUser = userwiseBookMarkMstDAO.getUserCombo(userwiseBookMarkMstVO, _UserVO); 
					        mp.put(InvestigationConfig.LIST_USER_COMBO, lstUser); 
					     
					        lstDept = userwiseBookMarkMstDAO.getDeptCombo(userwiseBookMarkMstVO, _UserVO); 
					        mp.put(InvestigationConfig.LIST_DEPT_COMBO, lstDept); 
					         
					        /*lstunit = userBookMarkMstDAO.getUnitCombo(UserwiseBookMarkMstVO, _UserVO); 
					        mp.put(InvestigationConfig.LIST_UNIT_COMBO, lstunit);*/ 
					         
					        lstLab =userwiseBookMarkMstDAO.getLabCombo(userwiseBookMarkMstVO, _UserVO); 
					        mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab); 
					         
					        lstBookMark = userwiseBookMarkMstDAO.getBookMarkCombo(userwiseBookMarkMstVO, _UserVO); 
					        mp.put(InvestigationConfig.LIST_BOOKMARK_COMBO, lstBookMark); 
					         
					        lstTestGroup = userwiseBookMarkMstDAO.getTestGroupCombo(userwiseBookMarkMstVO, _UserVO); 
					        mp.put(InvestigationConfig.LIST_TEST_GROUP_COMBO, lstTestGroup); 
					         
					        userwiseBookMarkMstDAO.fetchModifyUserBookMark(userwiseBookMarkMstVO, _UserVO); 
					         
					        lstTest = userwiseBookMarkMstDAO.getTestBylabCode(userwiseBookMarkMstVO, _UserVO); 
					        mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest); 
					         
					    } catch (HisApplicationExecutionException e) { 
					        tx.rollback(); 
					        System.out.println(e.getMessage()); 
					        throw new HisApplicationExecutionException(); 
					    } catch (HisDataAccessException e) { 
					        tx.rollback(); 
					        System.out.println(e.getMessage()); 
					        throw new HisDataAccessException(); 
					    } catch (HisException e) { 
					        tx.rollback(); 
					        System.out.println(e.getMessage()); 
					        throw new HisException(); 
					    } catch (Exception e) { 
					        System.out.println(e.getMessage()); 
					        tx.rollback(); 
					        throw new HisApplicationExecutionException(); 
					    } finally { 
					        tx.close(); 
					    } 
					    return mp; 
					} 
					public void saveUserBookMark(UserwiseBookMarkMstVO userwiseBookMarkMstVO,List<UserwiseBookMarkMstVO> lstUserwiseBookMarkVO,UserVO _UserVO) 
					{ 
					    JDBCTransactionContext tx = new JDBCTransactionContext(); 
					    String checkList=""; 
					    try 
					    { 
					        tx.begin(); 
					        UserwiseBookMarkMstDAO userwiseBookMarkMstDAO = new UserwiseBookMarkMstDAO(tx); 
					        if(lstUserwiseBookMarkVO!=null)
					        {
					        	if(lstUserwiseBookMarkVO.isEmpty()==false)
					        	{
					        for(int i=0;i<lstUserwiseBookMarkVO.size();i++) 
					        { 
					        	//by prashant
					            UserwiseBookMarkMstVO UserwiseBookmVO=(UserwiseBookMarkMstVO)lstUserwiseBookMarkVO.get(i); 
					    
						     userwiseBookMarkMstVO.setLabCode(UserwiseBookmVO.getLabCode());
	               			 userwiseBookMarkMstVO.setTestCode(UserwiseBookmVO.getTestCode());                        
	               			 userwiseBookMarkMstVO.setGroupCode(UserwiseBookmVO.getGroupCode());
	                         userwiseBookMarkMstVO.setIsTestGroup(UserwiseBookmVO.getIsTestGroup());
	                
	                         userwiseBookMarkMstDAO.saveUserBookMark(userwiseBookMarkMstVO, _UserVO);
	               					            
						/*//commented by prashant
						 * if(UserwiseBookmVO.getTestCode().equals("-1")==false) {
						 * userwiseBookMarkMstVO.setLabCode(UserwiseBookmVO.getLabCode());
						 * if(userwiseBookMarkMstVO.getIsTestGroup().equals("0")) {
						 * userwiseBookMarkMstVO.setTestCode(UserwiseBookmVO.getTestCode()); }
						 * if(userwiseBookMarkMstVO.getIsTestGroup().equals("1")) {
						 * userwiseBookMarkMstVO.setGroupCode(UserwiseBookmVO.getGroupCode()); }
						 * userwiseBookMarkMstDAO.saveUserBookMark(userwiseBookMarkMstVO, _UserVO); }
						 */
					        } 
					      }
					        }
					        //userBookMarkMstDAO.saveUserBookMark(UserwiseBookMarkMstVO, _UserVO);
					    } 
					    catch (HisDuplicateRecordException e) 
					    { 
					        tx.rollback(); 
					        throw new HisDuplicateRecordException(e.getMessage()); 
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
					} 
					public Map getEssential(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO) {
						JDBCTransactionContext tx = new JDBCTransactionContext();
						Map mp = new HashMap();

						List lstUser = new ArrayList();
						List lstDept = new ArrayList();
						List lstunit = new ArrayList();
						List lstLab = new ArrayList();
						List lstTest = new ArrayList();
						List lstTestGroup = new ArrayList();
						List lstBookMark = new ArrayList();
						try {
							tx.begin();
							UserwiseBookMarkMstDAO userwiseBookMarkMstDAO = new UserwiseBookMarkMstDAO(tx);
							
							lstBookMark = userwiseBookMarkMstDAO.getBookMarkCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_BOOKMARK_COMBO, lstBookMark);
							
							lstUser = userwiseBookMarkMstDAO.getUserCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_USER_COMBO, lstUser);

							lstDept = userwiseBookMarkMstDAO.getDeptCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_DEPT_COMBO, lstDept);

							lstLab = userwiseBookMarkMstDAO.getLabCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_LAB_COMBO, lstLab);

							lstTest = userwiseBookMarkMstDAO.getTestCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_TEST_COMBO, lstTest);

							lstTestGroup = userwiseBookMarkMstDAO.getTestGroupCombo(userwiseBookMarkMstVO, _UserVO);
							mp.put(InvestigationConfig.LIST_TEST_GROUP_COMBO, lstTestGroup);
							userwiseBookMarkMstVO.setIsTestGroup("0");
						} catch (HisApplicationExecutionException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisApplicationExecutionException();
						} catch (HisDataAccessException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisDataAccessException();
						} catch (HisException e) {
							tx.rollback();
							System.out.println(e.getMessage());
							throw new HisException();
						} catch (Exception e) {
							System.out.println(e.getMessage());
							tx.rollback();
							throw new HisApplicationExecutionException();
						} finally {
							tx.close();
						}
						return mp;
					}
					       public List getTest(UserwiseBookMarkMstVO userwiseBookMarkMstVO,UserVO _UserVO)
					{
						JDBCTransactionContext tx = new JDBCTransactionContext();
						List unit=new ArrayList();
						try
						{
							tx.begin();
							UserwiseBookMarkMstDAO userwiseBookMarkMstDAO=new UserwiseBookMarkMstDAO(tx);
							unit=userwiseBookMarkMstDAO.getTestCombo(userwiseBookMarkMstVO, _UserVO);


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
						return unit;
					}
					       
					       public List getTestgroup(UserwiseBookMarkMstVO userwiseBookMarkMstVO, UserVO _UserVO)
							{
								JDBCTransactionContext tx = new JDBCTransactionContext();
								List unit=new ArrayList();
								try
								{
									tx.begin();
									UserwiseBookMarkMstDAO userwiseBookMarkMstDAO=new UserwiseBookMarkMstDAO(tx);
									unit=userwiseBookMarkMstDAO.getTestCombogroup(userwiseBookMarkMstVO, _UserVO);


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
								return unit;
							}


					       
}


