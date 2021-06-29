package opd.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.vo.AllergyTypeVO;
import hisglobal.vo.AllergyWiseSymptomMasterVO;
import hisglobal.vo.AttendantReasonMasterVO;
import hisglobal.vo.AudioVideoMasterVO;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.ChartUnitMapppingVO;
import hisglobal.vo.ConsentMappingMasterVO;
import hisglobal.vo.DepartmentHosDisMasterVO;
import hisglobal.vo.DepartmentHosDiseaseMstVO;
import hisglobal.vo.DepartmentIcdMasterVO;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.DeskMenuMacroMstVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.DeskTypeMenuMappingVO;
import hisglobal.vo.DeskWiseDefaultProfileMstVO;
import hisglobal.vo.DiseaseSiteMasterVO;
import hisglobal.vo.DrugDoseMstVO;
import hisglobal.vo.DrugListItemMstVO;
import hisglobal.vo.DrugListMasterVO;
import hisglobal.vo.DrugRouteMstVO;
import hisglobal.vo.EpisodeKeywordsMasterVO;
import hisglobal.vo.IcdCrossRefMasterVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdGroupMasterVO;
import hisglobal.vo.IcdHospitalMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdIndexMasterVO;
import hisglobal.vo.IcdMappingMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.ImagePointerMasterVO;
import hisglobal.vo.MacroMasterVO;
import hisglobal.vo.OPDUnitImageMasterVO;
import hisglobal.vo.OpdParameterVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.OpdTemplateVO;
import hisglobal.vo.ParameterRangeMasterVO;
import hisglobal.vo.PatientAlertMasterVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.ProfileGroupMasterVO;
import hisglobal.vo.ProfileRestrictedCategoryMasterVO;
import hisglobal.vo.ProfileTypeMstVO;
import hisglobal.vo.ProfileTypeTabMappingVO;
import hisglobal.vo.SinglePageInterfaceMasterVO;
import hisglobal.vo.TemplateMappingMstVO;
import hisglobal.vo.UnitAudioVideoMasterVO;
import hisglobal.vo.UnitDrugListMasterVO;
import hisglobal.vo.UnitDrugMstVO;
import hisglobal.vo.UnitEpisodeKeywordVO;
import hisglobal.vo.UnitExtTreatMstVO;
import hisglobal.vo.UnitImageDescMasterVO;
import hisglobal.vo.UnitMasterVO;
import hisglobal.vo.UnitWiseMacroMstVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserDeskUnitWardMappingMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.HospitalDiseaseMasterVO;
import hisglobal.vo.IcdSnomedMappingMasterVO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.dao.ChartMasterDAO;
import opd.dao.ChartMasterDAOi;
import opd.dao.DepartmentHosDisMasterDAO;
import opd.dao.DepartmentIcdMasterDAO;
import opd.dao.DepartmentUnitHosDiseaseMstDAO;
import opd.dao.DepartmentUnitHosDiseaseMstDAOi;
import opd.dao.OpdEssentialDAO;
import opd.dao.OpdEssentialDAOi;
import opd.dao.OpdIcdMappingDAO;
import opd.dao.OpdIcdMappingDAOi;
import opd.dao.OpdPatientImageDtlDAO;
import opd.dao.ProfileTypeDAO;
import opd.dao.ProfileTypeTabMappingDAO;
import opd.dao.SinglePageInterfaceDAO;
import opd.master.dao.AllergyTypeDAO;
import opd.master.dao.AllergyWiseSymptomMasterDAO;
import opd.master.dao.AttendantReasonMasterDAO;
import opd.master.dao.AttendantReasonMasterDAOi;
import opd.master.dao.AudioVideoMasterDAO;
import opd.master.dao.ChartParameterMappingDAO;
import opd.master.dao.ChartParameterMappingDAOi;
import opd.master.dao.ChartUnitMappingMstDAO;
import opd.master.dao.ChartUnitMappingMstDAOi;
import opd.master.dao.ConsentMappingMasterDAO;
import opd.master.dao.DepartmentUnitIcdMappingDAO;
import opd.master.dao.DeskDetailDAO;
import opd.master.dao.DeskMasterDAO;
import opd.master.dao.DeskMenuMacroMasterDAO;
import opd.master.dao.DeskMenuMasterDAO;
import opd.master.dao.DeskTypeMenuMappingMstDAO;
import opd.master.dao.DeskWiseDefaultProfileMstDAO;
import opd.master.dao.DiseaseSiteDAO;
import opd.master.dao.DiseaseSiteDAOi;
import opd.master.dao.DrugDoseMasterDAO;
import opd.master.dao.DrugListItemMasterDAO;
import opd.master.dao.DrugListItemMasterDAOi;
import opd.master.dao.DrugListMasterDAO;
import opd.master.dao.DrugListMasterDAOi;
import opd.master.dao.DrugRouteMstDAO;
import opd.master.dao.EpisodeKeywordsMasterDAO;
import opd.master.dao.EpisodeKeywordsMasterDAOi;
import opd.master.dao.GlobalDeskMasterDAO;
import opd.master.dao.IIcdCrossRefMasterDAOi;
import opd.master.dao.IIcdIndexMasterDAOi;
import opd.master.dao.IcdCrossRefMasterDAO;
import opd.master.dao.IcdDiseaseMasterDAO;
import opd.master.dao.IcdDiseaseMasterDAOi;
import opd.master.dao.IcdHospitalMasterDAO;
import opd.master.dao.IcdIndexLevelMasterDAO;
import opd.master.dao.IcdIndexLevelMasterDAOi;
import opd.master.dao.IcdIndexMasterDAO;
import opd.master.dao.ImageMasterDAO;
import opd.master.dao.ImagePointerMasterDAO;
import opd.master.dao.MacroMstDAO;
import opd.master.dao.OPDParameterMasterDAO;
import opd.master.dao.OPDTemplateMasterDAO;
import opd.master.dao.ParaRangeMstDAO;
import opd.master.dao.PatientAlertMasterDAO;
import opd.master.dao.PatientAlertMasterDAOi;
import opd.master.dao.ProfileAccessPolicyDAO;
import opd.master.dao.ProfileAccessPolicyDAOi;
import opd.master.dao.ProfileGroupDetailDAO;
import opd.master.dao.ProfileGroupDetailDAOi;
import opd.master.dao.ProfileGroupMasterDAO;
import opd.master.dao.ProfileGroupMasterDAOi;
import opd.master.dao.ProfileRestrictedCatDAO;
import opd.master.dao.ProfileRestrictedCatDAOi;
import opd.master.dao.TemplateMappingMstDAO;
import opd.master.dao.TemplateMappingMstDAOi;
import opd.master.dao.UnitAudioVideoMasterDAO;
import opd.master.dao.UnitDrugDAO;
import opd.master.dao.UnitDrugDAOi;
import opd.master.dao.UnitDrugListDAO;
import opd.master.dao.UnitDrugListDAOi;
import opd.master.dao.UnitEpisodeKeywordMasterDAO;
import opd.master.dao.UnitEpisodeKeywordMasterDAOi;
import opd.master.dao.UnitExtTreatDAO;
import opd.master.dao.UnitExtTreatDAOi;
import opd.master.dao.UnitImageDescMasterDAO;
import opd.master.dao.UnitImageMasterDAO;
import opd.master.dao.UnitMacroMasterDAO;
import opd.master.dao.UnitMacroMasterDAOi;
import opd.master.dao.UserDeskMenuMasterDAO;
import opd.master.dao.UserDeskMenuTemplateMasterDAO;
import opd.master.dao.UserDeskUnitWardMappingMasterDAO;
import registration.master.dao.UnitMasterDAO;
import opd.master.dao.UnitDrugDAO;
import opd.master.dao.HospitalDiseaseMasterDAOi;
import opd.master.dao.HospitalDiseaseMasterDAO;
import opd.master.dao.IcdSnomedMappingMasterDAOi;
import opd.master.dao.IcdSnomedMappingMasterDAO;

public class OpdMasterBO implements OpdMasterBOi
{

	public void saveDeptIcdCode(DepartmentIcdMasterVO[] _deptIcdVO, String _icdCodeType, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DepartmentIcdMasterDAO deptIcdDAO = new DepartmentIcdMasterDAO(tx);
		DepartmentIcdMasterVO deptIcdVO[] = null;
		int counter = 0;
		try
		{

			if (_icdCodeType.equals(OpdConfig.CHOICE_GROUP))// If 1
			{
				for (int i = 0; i < _deptIcdVO.length; i++)// For 1
				{
					deptIcdVO = new OpdMasterBO().getDetailsByCode(_deptIcdVO[i], OpdConfig.CHOICE_GROUP, _userVO);

					for (int j = 0; j < deptIcdVO.length; j++)// For 2
					{
						if (counter == 0)
						{
							tx.begin();
						}
						counter++;
						if (counter < 100)
						{
							HelperMethods.populatetToNullOrEmpty(deptIcdVO[j], _deptIcdVO[i]);
							deptIcdDAO.create(deptIcdVO[j], _userVO);
						}
						else
						{
							HelperMethods.populatetToNullOrEmpty(deptIcdVO[j], _deptIcdVO[i]);
							deptIcdDAO.create(deptIcdVO[j], _userVO);
							counter = 0; // re-initializing the counter
							tx.close(); // closing connection after inserting
							// 100 records
						}
					}// Close of For2
				}// For1
			}
			else // Else of if 1
			if (_icdCodeType.equals(OpdConfig.CHOICE_SUBGROUP))
			{
				for (int i = 0; i < _deptIcdVO.length; i++)
				{
					deptIcdVO = new OpdMasterBO().getDetailsByCode(_deptIcdVO[i], OpdConfig.CHOICE_SUBGROUP, _userVO);
					for (int j = 0; j < deptIcdVO.length; j++)// For 2
					{
						if (counter == 0)
						{
							tx.begin();
						}
						counter++;
						if (counter < 100)
						{
							HelperMethods.populatetToNullOrEmpty(deptIcdVO[j], _deptIcdVO[i]);
							deptIcdDAO.create(deptIcdVO[j], _userVO);

						}
						else
						{
							HelperMethods.populatetToNullOrEmpty(deptIcdVO[j], _deptIcdVO[i]);
							deptIcdDAO.create(deptIcdVO[j], _userVO);
							counter = 0; // re-initializing the counter
							tx.close(); // closing connection after inserting
							// 100 records
						}
					}
				}
			}
			else if (_icdCodeType.equals(OpdConfig.CHOICE_DISEASE) || _icdCodeType.equals(OpdConfig.CHOICE_SUBDISEASE))
			{
				for (int i = 0; i < _deptIcdVO.length; i++)
				{
					deptIcdVO = new OpdMasterBO().getDetailsByCode(_deptIcdVO[i], OpdConfig.CHOICE_DISEASE, _userVO);
					for (int j = 0; j < deptIcdVO.length; j++)// For 2
					{
						if (counter == 0)
						{
							tx.begin();
						}
						counter++;
						if (counter < 100)
						{
							HelperMethods.populatetToNullOrEmpty(deptIcdVO[j], _deptIcdVO[i]);
							deptIcdDAO.create(deptIcdVO[j], _userVO);
						}
						else
						{
							HelperMethods.populatetToNullOrEmpty(deptIcdVO[j], _deptIcdVO[i]);
							deptIcdDAO.create(deptIcdVO[j], _userVO);
							counter = 0; // re-initializing the counter
							tx.close(); // closing connection after inserting
							// 100 records
						}
					}
				}
			}

		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{

			tx.close();
		}
	}

	public DepartmentIcdMasterVO[] getDetailsByCode(DepartmentIcdMasterVO _deptIcdVO, String choice, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DepartmentIcdMasterDAO deptIcdDAO = new DepartmentIcdMasterDAO(tx);
		DepartmentIcdMasterVO deptIcdVO[] = null;
		try
		{
			tx.begin();
			if (choice.equals(OpdConfig.CHOICE_GROUP))
			{
				deptIcdVO = deptIcdDAO.getDetailsByGroupCode(_deptIcdVO, _userVO);
			}
			else if (choice.equals(OpdConfig.CHOICE_SUBGROUP))
			{
				deptIcdVO = deptIcdDAO.getDetailsBySubGroupCode(_deptIcdVO, _userVO);
			}
			else if ((choice.equals(OpdConfig.CHOICE_DISEASE)) || (choice.equals(OpdConfig.CHOICE_SUBDISEASE)))
			{
				deptIcdVO = deptIcdDAO.getDetailsByDiseaseCode(_deptIcdVO, _userVO);
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{

			tx.close();
		}
		return deptIcdVO;
	}

	public DepartmentIcdMasterVO[] getDeptIcdDetail(DepartmentIcdMasterVO _deptIcdVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		DepartmentIcdMasterDAO deptIcdDAO = new DepartmentIcdMasterDAO(tx);
		DepartmentIcdMasterVO deptIcdVO[] = null;
		try
		{
			tx.begin();
			if (_deptIcdVO.getDepartmentUnitCode().equals(OpdConfig.DEFAULT_UNIT_CODE_ICD_MASTER)) deptIcdVO = deptIcdDAO.getIcdDeatilsDeptWise(
					_deptIcdVO, _userVO);
			else deptIcdVO = deptIcdDAO.getIcdDeatilsUnitWise(_deptIcdVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{

			tx.close();
		}
		return deptIcdVO;
	}

	public void deleteDeptIcdCode(DepartmentIcdMasterVO _deptIcdVO, String[] _selectedRecord, String _choice, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DepartmentIcdMasterDAO deptIcdDAO = new DepartmentIcdMasterDAO(tx);

		try
		{
			tx.begin();

			if (_choice.equals(OpdConfig.CHOICE_GROUP))
			{
				for (int i = 0; i < _selectedRecord.length; i++)
				{
					deptIcdDAO.deleteByGroupCode(_deptIcdVO, _selectedRecord[i], _userVO);
				}

			}
			else if (_choice.equals(OpdConfig.CHOICE_SUBGROUP))
			{
				for (int i = 0; i < _selectedRecord.length; i++)
				{
					deptIcdDAO.deleteBySubGroupCode(_deptIcdVO, _selectedRecord[i], _userVO);
				}
			}
			else if ((_choice.equals(OpdConfig.CHOICE_DISEASE)) || (_choice.equals(OpdConfig.CHOICE_SUBDISEASE)))
			{
				for (int i = 0; i < _selectedRecord.length; i++)
				{
					deptIcdDAO.deleteByDiseaseCode(_deptIcdVO, _selectedRecord[i], _userVO);
				}
			}

		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{

			tx.close();
		}
	}

	// * Saving Data to Desk Master
	public String saveDesk(DeskMasterVO _DskMstVO, UserVO _userVO)
	{
		String DeskId = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			DeskMasterDAO daoobj = new DeskMasterDAO(tx);
			boolean flag = daoobj.checkDuplicateDesk(_DskMstVO, _userVO);
			if (flag) daoobj.create(_DskMstVO, _userVO);
			else throw new HisRecordNotFoundException("This Desk Name Already Added.");
			DeskId = daoobj.getDeskId(_DskMstVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return DeskId;
	}

	// * Saving Data to Desk Detail
	public void saveDeskDetail(DeskDetailVO _DskDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DeskDetailDAO daoobj = new DeskDetailDAO(tx);
			daoobj.create(_DskDtlVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Saving Desk Detail
	public void saveCompleteDeskDetail(DeskMasterVO _deskVO, List<DeskDetailVO> _lstDeskDtl, UserVO _userVO)
	{
		String deskId = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			DeskMasterDAO daoobj = new DeskMasterDAO(tx);
			_deskVO.setDeskId("-1");
			if (daoobj.checkDuplicateDesk(_deskVO, _userVO)) daoobj.create(_deskVO, _userVO);
			else throw new HisRecordNotFoundException("Desk Name Already Exists");
			deskId = daoobj.getDeskId(_deskVO, _userVO);

			DeskDetailDAO deskDtlDAO = new DeskDetailDAO(tx);
			for (DeskDetailVO deskDtlVO : _lstDeskDtl)
			{
				deskDtlVO.setDeskId(deskId);
				deskDtlVO.setIsValid(_deskVO.getIsValid());
				deskDtlDAO.create(deskDtlVO, _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	
	
	// Saving Desk Detail
	public void saveCompleteGlobalDeskDetail(DeskMasterVO _deskVO, List<DeskDetailVO> _lstDeskDtl, UserVO _userVO)
	{
		String deskId = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			GlobalDeskMasterDAO daoobj = new GlobalDeskMasterDAO(tx);
			_deskVO.setDeskId("-1");
			if (daoobj.checkDuplicateDesk(_deskVO, _userVO)) 
				daoobj.create(_deskVO, _userVO);
			else throw new HisRecordNotFoundException("Desk Name Already Exists");
			deskId = daoobj.getDeskId(_deskVO, _userVO);

			DeskDetailDAO deskDtlDAO = new DeskDetailDAO(tx);
			for (DeskDetailVO deskDtlVO : _lstDeskDtl)
			{
				deskDtlVO.setDeskId(deskId);
				deskDtlVO.setIsValid(_deskVO.getIsValid());
				deskDtlDAO.createGlobal(deskDtlVO, _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}


	
	
	
	// * Getting Desk VO By Desk Id
	public DeskMasterVO fetchDeskVOByDeskId(String _DeskId, UserVO _userVo)
	{
		DeskMasterVO voDeskMst = new DeskMasterVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DeskMasterDAO daoobj = new DeskMasterDAO(tx);
			voDeskMst = daoobj.selectByDeskId(_DeskId, _userVo);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return voDeskMst;
	}
	
	
	
	// * Getting Desk VO By Desk Id
	public DeskMasterVO fetchDeskVOByGlobalDeskId(String _DeskId, UserVO _userVo)
	{
		DeskMasterVO voDeskMst = new DeskMasterVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			GlobalDeskMasterDAO daoobj = new GlobalDeskMasterDAO(tx);
			voDeskMst = daoobj.selectByDeskId(_DeskId, _userVo);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return voDeskMst;
	}
	
	
	
	

	// * Getting Desk Detail VO Array By Desk
	public DeskDetailVO[] getMenuListByDeskId(DeskMasterVO _voDeskMst, UserVO _UserVo)
	{
		DeskDetailVO[] voDeskDtlList = new DeskDetailVO[0];
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DeskDetailDAO daoobj = new DeskDetailDAO(tx);
			voDeskDtlList = daoobj.selectMenuListByDeskId(_voDeskMst, _UserVo);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return voDeskDtlList;
	}
	
	
	// * Getting Desk Detail VO Array By Desk
		public DeskDetailVO[] getMenuListByGlobalDeskId(DeskMasterVO _voDeskMst, UserVO _UserVo)
		{
			DeskDetailVO[] voDeskDtlList = new DeskDetailVO[0];
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();
				DeskDetailDAO daoobj = new DeskDetailDAO(tx);
				voDeskDtlList = daoobj.selectMenuListByGlobalDeskId(_voDeskMst, _UserVo);
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException();
			}
			catch (HisDataAccessException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisDataAccessException();
			}
			catch (HisException e)
			{
				tx.rollback();
				
				e.printStackTrace();
				throw new HisException();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return voDeskDtlList;
		}


	// * Updating Data to Desk Master
	public void updateDesk(DeskMasterVO _DskMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			DeskMasterDAO daoobj = new DeskMasterDAO(tx);
			daoobj.update(_DskMstVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Updating Complete Desk Detail
	public void updateCompleteDesk(DeskMasterVO _deskVO, List<DeskDetailVO> _lstDeskDtl, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			DeskMasterDAO daoobj = new DeskMasterDAO(tx);
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);

			if (!daoobj.checkDuplicateDesk(_deskVO, _userVO)) throw new HisRecordNotFoundException("Desk Name Already Exists");
			if (_deskVO.getIsDefault().trim().equals(OpdConfig.YES) && !daoOpdEssentail.getisDefault(_deskVO, _userVO)) throw new HisRecordNotFoundException(
					"A Default Desk already Exixts for this Desk Type");
			daoobj.update(_deskVO, _userVO);

			DeskDetailDAO deskDtlDAO = new DeskDetailDAO(tx);
			deskDtlDAO.deleteDeskMenus(_deskVO.getDeskId(), _userVO);

			for (DeskDetailVO deskDtlVO : _lstDeskDtl)
			{
				deskDtlVO.setDeskId(_deskVO.getDeskId());
				deskDtlVO.setIsValid(_deskVO.getIsValid());
				deskDtlDAO.create(deskDtlVO, _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}
	
	
	
	// Updating Complete GLobal Desk Detail
	public void updateCompleteGlobalDesk(DeskMasterVO _deskVO, List<DeskDetailVO> _lstDeskDtl, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			GlobalDeskMasterDAO daoobj = new GlobalDeskMasterDAO(tx);
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);

			if (!daoobj.checkDuplicateDesk(_deskVO, _userVO)) throw new HisRecordNotFoundException("Desk Name Already Exists");
			if (_deskVO.getIsDefault().trim().equals(OpdConfig.YES) && !daoOpdEssentail.getisDefault(_deskVO, _userVO))
				throw new HisRecordNotFoundException("A Default Desk already Exists for this Desk Type");
			daoobj.update(_deskVO, _userVO);

			DeskDetailDAO deskDtlDAO = new DeskDetailDAO(tx);
			deskDtlDAO.deleteGlobalDeskMenus(_deskVO.getDeskId(), _userVO);

			for (DeskDetailVO deskDtlVO : _lstDeskDtl)
			{
				deskDtlVO.setDeskId(_deskVO.getDeskId());
				deskDtlVO.setIsValid(_deskVO.getIsValid());
				deskDtlDAO.createGlobal(deskDtlVO, _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}
	

	// * Deleting All Menus From Desk By Desk Id
	public void deleteDeskMenus(String _DeskId, UserVO _UserVo)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			DeskDetailDAO daoobj = new DeskDetailDAO(tx);
			daoobj.deleteDeskMenus(_DeskId, _UserVo);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving User Desk Menu Record
	public void saveUserDeskMenu(UserDeskMenuMasterVO _UserDeskMenuMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			UserDeskMenuMasterDAO daoobj = new UserDeskMenuMasterDAO(tx);
			daoobj.create(_UserDeskMenuMstVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}
	
	
	// * Saving User Desk Menu Record
		public void saveUserDeskMenu1(UserDeskMenuMasterVO[] _UserDeskMenuMstVO, UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			UserDeskMenuMasterVO userDeskMenuMasterVO=null;
			try
			{
				tx.begin();

				UserDeskMenuMasterDAO daoobj = new UserDeskMenuMasterDAO(tx);
				for (int i = 0; i < _UserDeskMenuMstVO.length; i++) {
					userDeskMenuMasterVO=_UserDeskMenuMstVO[i];
					daoobj.create(userDeskMenuMasterVO, _userVO);
				}
				
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException();
			}
			catch (HisDataAccessException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisDataAccessException();
			}
			catch (HisException e)
			{
				tx.rollback();
				
				e.printStackTrace();
				throw new HisException();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
		}

	// * Updating User Desk Menu Record
	public void updateUserDeskMenu(UserDeskMenuMasterVO _UserDeskMenuMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			UserDeskMenuMasterDAO daoobj = new UserDeskMenuMasterDAO(tx);
			daoobj.update(_UserDeskMenuMstVO, _userVO);
			//if (_UserDeskMenuMstVO.getUserSeatId() == null || _UserDeskMenuMstVO.getUserSeatId() == "") daoobj.updateUnitWise(_UserDeskMenuMstVO,
				//	_userVO);
			//else
				
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving Template Record
	public void saveTemplate(OpdTemplateVO _tmpltVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OPDTemplateMasterDAO daoobj = new OPDTemplateMasterDAO(tx);
			daoobj.create(_tmpltVO, _userVO);

			_tmpltVO.setTemplateId(daoobj.getMaxTemplateId(_userVO));
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Updating Template Record
	/*
	 * public void updateTemplate(OpdTemplateVO _tmpltVO, UserVO _userVO) { JDBCTransactionContext tx =new
	 * JDBCTransactionContext(); try { tx.begin(); OPDTemplateMasterDAO daoobj =new OPDTemplateMasterDAO(tx);
	 * 
	 * String nextFromDate=daoobj.getNextDate(_tmpltVO,_userVO); if(nextFromDate!="") { if(_tmpltVO.getEffectiveTo()!=null ||
	 * _tmpltVO.getEffectiveTo()!="") { Date currentEffectiveToDate= new Date(_tmpltVO.getEffectiveTo()); Date
	 * nextEffFromDate= new Date(nextFromDate); if(currentEffectiveToDate.compareTo(nextEffFromDate)>=0) { throw new
	 * HisRecordNotFoundException("Effective To Date cannot be greater than"+nextFromDate ); } } if(nextFromDate!="") {
	 * if(_tmpltVO.getEffectiveTo()==null || _tmpltVO.getEffectiveTo()=="") { throw new HisRecordNotFoundException("Effective
	 * To Date cannot be Null" ); } } } daoobj.updateName(_tmpltVO, _userVO); daoobj.update(_tmpltVO,_userVO); }
	 * catch(HisRecordNotFoundException e) { tx.rollback(); throw new HisRecordNotFoundException(e.getMessage()); }
	 * catch(HisApplicationExecutionException e) { tx.rollback(); e.printStackTrace(); throw new
	 * HisApplicationExecutionException(e.getMessage()); } catch(HisDataAccessException e) { tx.rollback();
	 * e.printStackTrace(); throw new HisDataAccessException(e.getMessage()); } catch(HisException e) { tx.rollback();
	 * System.out.println("HisException:: "+e); e.printStackTrace(); throw new HisException(); } catch(Exception e) {
	 * e.printStackTrace(); tx.rollback(); if(e.getClass()==HisRecordNotFoundException.class) throw new
	 * HisRecordNotFoundException(e.getMessage()); else throw new HisDataAccessException("HisDataAccessException:: "+e); }
	 * finally { tx.close(); } }
	 * 
	 * //* Adding Template with New Serial No public void saveNewTemp(OpdTemplateVO _tmpltVO, UserVO _userVO) {
	 * JDBCTransactionContext tx =new JDBCTransactionContext(); try { tx.begin(); OPDTemplateMasterDAO daoobj =new
	 * OPDTemplateMasterDAO(tx);
	 * 
	 * int count=daoobj.getPreviousDate(_tmpltVO,_userVO); if(count>0) { throw new HisRecordNotFoundException("Effective From
	 * Date in this range already exists"); }
	 * 
	 * String nextFromDate=daoobj.getNextDate(_tmpltVO,_userVO); String[] arrayRS
	 * =daoobj.getPreviousSerialNo(_tmpltVO,_userVO); String serialNo= arrayRS[0]; String serialNoEffFromDate=arrayRS[1];
	 * if(serialNoEffFromDate!="") { Date _serialNoEffFromDate = new Date(serialNoEffFromDate); Date currentEffFromDate= new
	 * Date(_tmpltVO.getEffectiveFrom());
	 * 
	 * if(_serialNoEffFromDate.compareTo(currentEffFromDate)>=0) if(_tmpltVO.getEffectiveTo()==null ||
	 * _tmpltVO.getEffectiveTo().equals("")) throw new HisRecordNotFoundException("Effective to Date cannot be null and
	 * should be less than"+_serialNoEffFromDate); } if(count==0 && nextFromDate!="") { Date nextEffectiveFromDate = new
	 * Date(nextFromDate); Date currentEffectiveTo = new Date(_tmpltVO.getEffectiveTo());
	 * if(nextEffectiveFromDate.compareTo(currentEffectiveTo)>0) { daoobj.updateName(_tmpltVO, _userVO);
	 * daoobj.insertRow(_tmpltVO,_userVO); } else throw new HisRecordNotFoundException("Effective to Date in this range
	 * already exists"); } if(count==0 && nextFromDate=="") { if (serialNo=="") { daoobj.updateName(_tmpltVO, _userVO);
	 * daoobj.insertRow(_tmpltVO,_userVO); } else { daoobj.updateName(_tmpltVO, _userVO);
	 * daoobj.updatePrevRow(_tmpltVO,serialNo,_userVO); daoobj.insertRow(_tmpltVO,_userVO); } } }
	 * catch(HisApplicationExecutionException e) { tx.rollback(); e.printStackTrace(); throw new
	 * HisApplicationExecutionException(e.getMessage()); } catch(HisDataAccessException e) { e.printStackTrace();
	 * tx.rollback(); throw new HisDataAccessException(e.getMessage()); } catch(Exception e) { e.printStackTrace();
	 * tx.rollback(); if(e.getClass()==HisRecordNotFoundException.class) throw new
	 * HisRecordNotFoundException(e.getMessage()); else throw new HisDataAccessException("HisDataAccessException:: "+e); }
	 * finally { tx.close(); } }
	 */

	// * Saving Parameter Record
	public void saveOPDParameter(OpdParameterVO _paraVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OPDParameterMasterDAO daoobj = new OPDParameterMasterDAO(tx);
			if (!daoobj.fetchIdByName(_paraVO, _userVO)) daoobj.create(_paraVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	/*
	 * // * Saving Template Parameter Detail to TemplateParameterMaster public void
	 * saveParameterToTemplate(OpdTemplateParameterVO _tempParaVO, UserVO _userVO) { JDBCTransactionContext tx = new
	 * JDBCTransactionContext(); try { tx.begin();
	 * 
	 * OPDTemplateParameterMasterDAO daoobj = new OPDTemplateParameterMasterDAO( tx); daoobj.create(_tempParaVO, _userVO); }
	 * catch (HisRecordNotFoundException e) { tx.rollback(); throw new HisRecordNotFoundException(e.getMessage()); } catch
	 * (HisApplicationExecutionException e) { tx.rollback(); e.printStackTrace(); throw new
	 * HisApplicationExecutionException(); } catch (HisDataAccessException e) { tx.rollback(); e.printStackTrace(); throw new
	 * HisDataAccessException(); } catch (HisException e) { tx.rollback(); System.out.println("HisException:: " + e);
	 * e.printStackTrace(); throw new HisException(); } catch (Exception e) { e.printStackTrace();
	 * System.out.println("error.... Opd Master BO"); tx.rollback(); throw new HisApplicationExecutionException(); } finally {
	 * tx.close(); } }
	 */

	// * Getting Template Data
	public void getTemplateDataById(OpdTemplateVO _voTemp, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OPDTemplateMasterDAO daoOpdEssentail = new OPDTemplateMasterDAO(tx);
			daoOpdEssentail.getTemplateDataById(_voTemp, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	/*
	 * public void savePatientParameters(OpdPatientParameterVO _patParaVO, UserVO _userVO) { JDBCTransactionContext tx = new
	 * JDBCTransactionContext(); try { tx.begin(); OPDPatientParameterDetailDAO daoobj = new OPDPatientParameterDetailDAO(
	 * tx); daoobj.create(_patParaVO, _userVO); } catch (HisRecordNotFoundException e) { tx.rollback(); throw new
	 * HisRecordNotFoundException(e.getMessage()); } catch (HisApplicationExecutionException e) { tx.rollback();
	 * e.printStackTrace(); throw new HisApplicationExecutionException(); } catch (HisDataAccessException e) { tx.rollback();
	 * e.printStackTrace(); throw new HisDataAccessException(); } catch (HisException e) { tx.rollback();
	 * System.out.println("HisException:: " + e); e.printStackTrace(); throw new HisException(); } catch (Exception e) {
	 * e.printStackTrace(); System.out.println("error.... Appointment BO"); tx.rollback(); throw new
	 * HisApplicationExecutionException(); } finally { tx.close(); } }
	 */

	/*
	 * // * Delete the old Template Parameter Data By Template Id public void deleteTempParaById(String _TempId, UserVO
	 * _userVO) { JDBCTransactionContext tx = new JDBCTransactionContext(); try { tx.begin(); OPDTemplateParameterMasterDAO
	 * daoobj = new OPDTemplateParameterMasterDAO( tx); daoobj.delete(_TempId, _userVO); } catch (HisRecordNotFoundException
	 * e) { tx.rollback(); throw new HisRecordNotFoundException(e.getMessage()); } catch (HisApplicationExecutionException e) {
	 * tx.rollback(); e.printStackTrace(); throw new HisApplicationExecutionException(); } catch (HisDataAccessException e) {
	 * tx.rollback(); e.printStackTrace(); throw new HisDataAccessException(); } catch (HisException e) { tx.rollback();
	 * System.out.println("HisException:: " + e); e.printStackTrace(); throw new HisException(); } catch (Exception e) {
	 * e.printStackTrace(); System.out.println("error.... Opd Master BO"); tx.rollback(); throw new
	 * HisApplicationExecutionException(); } finally { tx.close(); } }
	 */

	// * Deleting UserDeskMenuTemplate Master By Given Conditions
	public void deleteTemplateToDeskMenuUnitWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			

			if (_voUDMT.getAdditionMode() == "0") daoobj.delete(_voUDMT, _UserVO);
			if (_voUDMT.getAdditionMode() == "1") daoobj.deleteSeatWise(_voUDMT, _UserVO);
			if (_voUDMT.getAdditionMode() == "2") daoobj.deleteDeskWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void deleteTemplateToDeskMenuUnitSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);

			if (_voUDMT.getAdditionMode() == OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE) daoobj.delete(_voUDMT, _UserVO);
			if (_voUDMT.getAdditionMode() == OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE) daoobj.deleteSeatWise(_voUDMT, _UserVO);
			if (_voUDMT.getAdditionMode() == OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE) daoobj.deleteDeskWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void deleteTemplateToDeskMenuDeskWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			

			if (_voUDMT.getAdditionMode() == "0") daoobj.delete(_voUDMT, _UserVO);
			if (_voUDMT.getAdditionMode() == "1") daoobj.deleteSeatWise(_voUDMT, _UserVO);
			if (_voUDMT.getAdditionMode() == "2") daoobj.deleteDeskWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Deleting UserDeskMenuTemplate Master By Given Conditions (Unit Wise)
	public void deleteTemplateToDeskMenuTemplateMasterUnitWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.deleteUnitWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Deleting UserDeskMenuTemplate Master By Given Conditions (UnitWard Wise)
	public void deleteTemplateToDeskMenuTemplateMasterUnitWardWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.deleteUnitWardWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Deleting UserDeskMenuTemplate Master By Given Conditions (UnitWardSeat
	// Wise)
	public void deleteTemplateToDeskMenuTemplateMasterUnitSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.deleteSeatWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Deleting UserDeskMenuTemplate Master By Given Conditions in modify
	// (Unit Wise)
	public void deleteTemplateToDeskMenuTemplateMasterDeskWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.deleteDeskWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving User Desk Menu Template Record
	public void saveTemplateToDeskMenu(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.createUnitWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving User Desk Menu Template Record
	public void saveTemplateToDeskMenuSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.createSeatWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving User Desk Menu Template Record
	public void saveTemplateToDeskMenuWardSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.createWardSeatWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving User Desk Menu Template Record
	public void saveTemplateToDeskMenuWardWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.createWardWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving User Desk Menu Template Record Unit Wise
	public void saveTemplateToDeskMenuUnitWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.createUnitWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving User Desk Menu Template Record Unit Wise
	public void saveTemplateToDeskMenuDeskWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.createDeskWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	/*
	 * public void saveUserDeskMenuTemplate(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO) { JDBCTransactionContext tx
	 * =new JDBCTransactionContext(); try { tx.begin(); UserDeskMenuTemplateMasterDAO daoobj =new
	 * UserDeskMenuTemplateMasterDAO(tx); daoobj.create(_voUDMT,_UserVO); } catch(HisRecordNotFoundException e) {
	 * tx.rollback(); throw new HisRecordNotFoundException(e.getMessage()); } catch(HisApplicationExecutionException e) {
	 * tx.rollback(); e.printStackTrace(); throw new HisApplicationExecutionException(); } catch(HisDataAccessException e) {
	 * tx.rollback(); e.printStackTrace(); throw new HisDataAccessException(); } catch(HisException e) { tx.rollback();
	 * System.out.println("HisException:: "+e); e.printStackTrace(); throw new HisException(); } catch(Exception e) {
	 * e.printStackTrace(); System.out.println("error.... Opd Essential BO"); tx.rollback(); throw new
	 * HisApplicationExecutionException(); } finally { tx.close(); } }
	 */

	// Getting Unit Detail
	public UnitMasterVO getUnitDetails(String _deptUnitCode, String _sNo, UserVO _UserVO)
	{
		UnitMasterVO voUnit = new UnitMasterVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitMasterDAO daoobj = new UnitMasterDAO(tx);
			voUnit = daoobj.getUnitDetails(_deptUnitCode, _sNo, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Appointment BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return voUnit;
	}

	// Saving OPD Macro Head
	public void saveMacroHead(DeskMenuMacroMstVO _deskMenuMacroVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DeskMenuMacroMasterDAO daoobj = new DeskMenuMacroMasterDAO(tx);
			daoobj.create(_deskMenuMacroVO, _UserVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Getting OPD Macro Head for Modify
	public DeskMenuMacroMstVO getMacroHeadForModify(String _macroID, UserVO _UserVO)
	{
		DeskMenuMacroMstVO voMenuMacro = new DeskMenuMacroMstVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			DeskMenuMacroMasterDAO daoobj = new DeskMenuMacroMasterDAO(tx);
			voMenuMacro = daoobj.getMacroHeadForModify(_macroID, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return voMenuMacro;
	}

	// Saving Modified OPD Macro Head
	public void modifySaveMacroHead(DeskMenuMacroMstVO _deskMenuMacroVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DeskMenuMacroMasterDAO daoobj = new DeskMenuMacroMasterDAO(tx);
			daoobj.update(_deskMenuMacroVO, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Creating Initial Deleted Entry for Image in Patient Image Detail
	/**
	 * Creating Entry for Exam Image
	 * 
	 * @param _vo Patient Image Detail VO
	 * @param _UserVO User VO
	 * @return Patient Image Detail VO
	 */
	public OpdPatientImageDtlVO createEntryForImage(OpdPatientImageDtlVO _vo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdPatientImageDtlDAO daoobj = new OpdPatientImageDtlDAO(tx);
			daoobj.removeInitialEntries(_vo, _UserVO);
			String sno = daoobj.createInitialEntry(_vo, _UserVO);

			// Creating Image File Name
			_vo.setSerialNo(sno);
			String fileName = Config.IMAGE_EDITOR_EXAMINATION_FILENAME_STARTSWITH + _vo.getPatCrNo()
					+ Config.IMAGE_EDITOR_EXAMINATION_FILENAME_CONNECTOR + sno + Config.IMAGE_EDITOR_EXAMINATION_FILENAME_EXTENSION;
			_vo.setImageFileName(fileName);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _vo;
	}

	// * Removing Initial Deleted Entry for Image in Patient Image Detail
	public void removeEntryForImage(OpdPatientImageDtlVO _VO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdPatientImageDtlDAO daoobj = new OpdPatientImageDtlDAO(tx);
			daoobj.removeInitialEntries(_VO, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving Image to Patient in Patient Image Detail
	// * Saving the Image To the Patient Finally
	public void saveOpdPatientImage(OpdPatientImageDtlVO _vo, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdPatientImageDtlDAO daoobj = new OpdPatientImageDtlDAO(tx);
			daoobj.updateFinalRecord(_vo, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Getting Images List of Patient in given Episode Visit
	// * Getting Already Added Image List to Patient in given Episode Visit
	public List<OpdPatientImageDtlVO> getOPDPatOldImagesList(OpdPatientImageDtlVO _PatImgVo, UserVO _UserVO)
	{
		List<OpdPatientImageDtlVO> lstImages = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdPatientImageDtlDAO dao = new OpdPatientImageDtlDAO(tx);
			lstImages = dao.getOPDPatOldImagesList(_PatImgVo, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstImages;
	}

	// Saving Image Record
	public void saveImage(ImageMasterVO imageMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ImageMasterDAO imageMasterDAO = new ImageMasterDAO(tx);
			if(imageMasterDAO.checkDuplicateImage(imageMasterVO, userVO))
			{
				imageMasterDAO.create(imageMasterVO, userVO);
			}
			else
			{
				throw new HisRecordNotFoundException("Image Name Already Exists");
			}
			
			String fileName = imageMasterDAO.selectFileName(imageMasterVO, userVO);
			String imageFirstName=fileName.substring(0,fileName.lastIndexOf("."));
            String actualImageName = imageFirstName.substring(5,8);
            imageMasterVO.setImageCode(actualImageName);
			ByteArrayInputStream in = new ByteArrayInputStream(imageMasterVO.getImageFile());
			BufferedImage bImageFromConvert = ImageIO.read(in);
			
			//String Path = context.getRealPath("/../HIS.war/hisglobal/images/ImgForImgEdtr/"+".png");
			
			//String Path = imageMasterVO.getImageStoragePath()+"\\"+actualImageName+".jpg";
			 
			//ImageIO.write(bImageFromConvert, "jpg", new File(Path));
			
			//HelperMethods.storeImageInCorrectFileSystem(imageMasterVO.getImageFile(), fileName, fileName,
					//Config.IMAGE_EXAMINATION_IMAGE_PATH_WINDOWS, Config.IMAGE_EXAMINATION_IMAGE_PATH_LINUX);
			imageMasterDAO.saveImageToPostgres(imageMasterVO, userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	// Getting Image for Modify
	public ImageMasterVO getImageForModify(String imageCode, String imageSlno, UserVO userVO)
	{
		ImageMasterVO imageMasterVO = new ImageMasterVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			ImageMasterDAO imageMasterDAO = new ImageMasterDAO(tx);
			imageMasterVO = imageMasterDAO.getImageForModify(imageCode, imageSlno, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return imageMasterVO;
	}

	// Modifying Image Record
	public void saveModifyImage(ImageMasterVO imageMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			ImageMasterDAO imageMasterDAO = new ImageMasterDAO(tx);

			boolean flag = imageMasterDAO.check_Modify_DuplicateImage(imageMasterVO, userVO);
			if (flag)
			{
				if (imageMasterVO.getImageFile() != null)
				{
					imageMasterDAO.update(imageMasterVO, userVO);
					imageMasterDAO.modifySaveWithImage(imageMasterVO, userVO);
					String fileName = imageMasterDAO.selectFileNameModify(imageMasterVO, userVO);
					imageMasterDAO.updateImageToPostgres(imageMasterVO, userVO);
					HelperMethods.storeImageInCorrectFileSystem(imageMasterVO.getImageFile(), fileName, fileName,
							Config.IMAGE_EXAMINATION_IMAGE_PATH_WINDOWS, Config.IMAGE_EXAMINATION_IMAGE_PATH_LINUX);
				}
				else
				{
					imageMasterDAO.updateImageName(imageMasterVO, userVO);
					imageMasterDAO.modifySaveWithoutImage(imageMasterVO, userVO);
					// String
					// fileName=imageMasterDAO.selectFileNameModify(imageMasterVO,userVO);
					// HelperMethods.storeImageInCorrectFileSystem(imageMasterVO.getImageFile(),fileName,
					// fileName,Config.IMAGE_EXAMINATION_IMAGE_PATH_WINDOWS,
					// Config.IMAGE_EXAMINATION_IMAGE_PATH_LINUX );
				}
			}
			else
			{

				throw new HisRecordNotFoundException("Image Name Already Exists");
			}

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	// Saving Unit Wise Image Description Assigned to Color
	public void saveUnitImageDesc(List unitImageDescMstVOLst, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			UnitImageDescMasterDAO daoObject = new UnitImageDescMasterDAO(tx);

			for (int i = 0; i < unitImageDescMstVOLst.size(); i++)
			{
				UnitImageDescMasterVO vo = (UnitImageDescMasterVO) unitImageDescMstVOLst.get(i);
				daoObject.create(vo, userVO);
			}

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void saveModUnitImageDesc(List unitImageDescMasterVOLst, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitImageDescMasterDAO daoObject = new UnitImageDescMasterDAO(tx);

			UnitImageDescMasterVO unitImageDescMstVO = (UnitImageDescMasterVO) unitImageDescMasterVOLst.get(0);
			daoObject.delete(unitImageDescMstVO.getUnitCode(), userVO);

			for (int i = 0; i < unitImageDescMasterVOLst.size(); i++)
			{
				UnitImageDescMasterVO vo = (UnitImageDescMasterVO) unitImageDescMasterVOLst.get(i);
				daoObject.modifySave(vo, userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	// Getting All Image Description assigned to Color for a particular Unit
	public List getAllColorImageDescForUnit(String selectedUnit, String imageId, String slNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List list = new ArrayList();
		try
		{
			tx.begin();
			UnitImageDescMasterDAO daoObject = new UnitImageDescMasterDAO(tx);
			list = daoObject.select(selectedUnit, imageId, slNo, userVO);

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return list;
	}

	// Deleting All image Description Assigned to Color for a particular Unit
	public void deleteUnitImageDesc(String unitCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitImageDescMasterDAO daoObject = new UnitImageDescMasterDAO(tx);
			daoObject.delete(unitCode, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Saving Audio/Video File in the DB & in File System
	public void saveAudioVideoFile(AudioVideoMasterVO audioVideoMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			AudioVideoMasterDAO audioVideoMasterDAO = new AudioVideoMasterDAO(tx);
			boolean flag = audioVideoMasterDAO.checkDuplicateFile(audioVideoMasterVO, userVO);
			if (flag)
			{
				audioVideoMasterDAO.create(audioVideoMasterVO, userVO);
			}
			else
			{

				throw new HisRecordNotFoundException("This File Header Already Added.");
			}

			String fileName = audioVideoMasterDAO.selectFileName(audioVideoMasterVO, userVO);

			HelperMethods.storeImageInCorrectFileSystem(audioVideoMasterVO.getFile(), fileName, fileName,
					Config.OPD_AUDIO_VIDEO_STORAGE_PATH_WINDOWS, Config.OPD_AUDIO_VIDEO_STORAGE_PATH_LINUX);

			/*
			 * // String fileName=audioVideoMasterDAO.selectFileName(audioVideoMasterVO,userVO); HisFileControlUtil fileUtil =
			 * new HisFileControlUtil(); fileUtil.setFileName(audioVideoMasterVO.getFileName());
			 * fileUtil.setWindowsFilePath(Config.OPD_AUDIO_VIDEO_STORAGE_PATH_WINDOWS);
			 * fileUtil.setLinuxFilePath(Config.OPD_AUDIO_VIDEO_STORAGE_PATH_LINUX);
			 * fileUtil.setFileContent(audioVideoMasterVO.getFile()); fileUtil.saveFile();
			 */
			// HelperMethods.storeImageInFileSystem(audioVideoMasterVO.getFile(),fileName,
			// fileName,Config.OPD_AUDIO_VIDEO_STORAGE_PATH);
			// FTPHelperMethods.saveAudioVideoFileInFTP(audioVideoMasterVO.getFile(),fileName);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	// Getting Audio/Video File for Modify
	public AudioVideoMasterVO getAudioVideoForModify(String fileCode, String slNo, UserVO userVO)
	{
		AudioVideoMasterVO avMasterVO = new AudioVideoMasterVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			AudioVideoMasterDAO audioVideoMasterDAO = new AudioVideoMasterDAO(tx);
			avMasterVO = audioVideoMasterDAO.getAudioVideoForModify(fileCode, slNo, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return avMasterVO;
	}

	// Saving Modified Audio/Video File
	public void saveModifyAudioVideo(AudioVideoMasterVO audioVideoMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			AudioVideoMasterDAO audioVideoMasterDAO = new AudioVideoMasterDAO(tx);

			boolean flag = audioVideoMasterDAO.check_Modify_DuplicateFile(audioVideoMasterVO, userVO);
			if (flag)
			{
				audioVideoMasterDAO.updateudioVideo(audioVideoMasterVO, userVO);
				audioVideoMasterDAO.saveModifyAudioVideo(audioVideoMasterVO, userVO);
			}
			else
			{

				throw new HisRecordNotFoundException("This File Header Already Added.");
			}

			// String
			// fileName=audioVideoMasterDAO.getFileName(audioVideoMasterVO,userVO);
			// String fileName=audioVideoMasterVO.getFileName();

			HisFileControlUtil fileUtil = new HisFileControlUtil();
			fileUtil.setFileName(audioVideoMasterVO.getFileName());
			fileUtil.setWindowsFilePath(Config.OPD_AUDIO_VIDEO_STORAGE_PATH_WINDOWS);
			fileUtil.setLinuxFilePath(Config.OPD_AUDIO_VIDEO_STORAGE_PATH_LINUX);
			fileUtil.setFileContent(audioVideoMasterVO.getFile());
			fileUtil.saveFile();
			// HelperMethods.storeImageInFileSystem(audioVideoMasterVO.getFile(),fileName,
			// fileName,Config.OPD_AUDIO_VIDEO_STORAGE_PATH);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving Image Unit Record Unit-Wise
	public void saveImageUnit(List<OPDUnitImageMasterVO> _lstUnitImages, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			UnitImageMasterDAO daoobj = new UnitImageMasterDAO(tx);
			for (OPDUnitImageMasterVO vo : _lstUnitImages)
				daoobj.create(vo, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void ModifysaveImageUnit(List<OPDUnitImageMasterVO> _lstUnitImages, String _deptUnitCode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			UnitImageMasterDAO daoobj = new UnitImageMasterDAO(tx);
			daoobj.deleteImageUnitWise(_deptUnitCode, _userVO);
			for (OPDUnitImageMasterVO vo : _lstUnitImages)
				daoobj.create(vo, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Getting Records
	public UserDeskMenuTemplateMasterVO getRecords(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{

		UserDeskMenuTemplateMasterVO records = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			records = daoobj.getRecords(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return records;
	}

	public UserDeskMenuTemplateMasterVO getRecordsForWard(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{

		UserDeskMenuTemplateMasterVO records = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			records = daoobj.getRecordsForWard(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return records;
	}

	// * Getting Image for View
	public OPDUnitImageMasterVO[] getImageForView(String deptUnitCode, UserVO userVO)
	{
		OPDUnitImageMasterVO[] opdUnitImageMasterVO;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			UnitImageMasterDAO daoobj = new UnitImageMasterDAO(tx);
			opdUnitImageMasterVO = daoobj.getImageForView(deptUnitCode, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return opdUnitImageMasterVO;
	}

	public void saveUnitAudioVideo(UnitAudioVideoMasterVO unitAVMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitAudioVideoMasterDAO unitAVMasterDAO = new UnitAudioVideoMasterDAO(tx);
			unitAVMasterDAO.create(unitAVMasterVO, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void deleteUnitAudioVideo(String unitCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitAudioVideoMasterDAO unitAVMasterDAO = new UnitAudioVideoMasterDAO(tx);
			unitAVMasterDAO.delete(unitCode, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	// saving data HOspital ICD Master

	public void save(IcdHospitalMasterVO[] _VOs, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			IcdHospitalMasterDAO dao = new IcdHospitalMasterDAO(tx);
			dao.deleteAll(_VOs[0].getHospitalDiseaseCode(), _userVO);
			for (int i = 0; i < _VOs.length; i++)
			{
				if (dao.exist(_VOs[i], _userVO)) dao.updateActive(_VOs[i], _userVO);
				else dao.save(_VOs[i], _userVO);
			}
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	// * Saving User Desk Unit Ward Mapping Master Record
	public void saveUserDeskUnitWardMappingMaster(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			UserDeskUnitWardMappingMasterDAO daoobj = new UserDeskUnitWardMappingMasterDAO(tx);
			daoobj.create(_UserDeskUnitWardMappingVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// * Saving User Desk Unit Ward Mapping Master Record both type(Unit Ward
	// and Unit Ward Seat Wise)
	public void saveUserDeskUnitWardMappingMasterUnitWardWise(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			UserDeskUnitWardMappingMasterDAO daoobj = new UserDeskUnitWardMappingMasterDAO(tx);
			daoobj.createUnitWardWise(_UserDeskUnitWardMappingVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	/*
	 * (non-Javadoc) Function used to delete the Hospital disease
	 * 
	 * @see opd.bo.OpdMasterBOi#deleteDeptHosDisCode(hisglobal.vo.DepartmentHosDisMasterVO, java.lang.String[],
	 *      java.lang.String, hisglobal.vo.UserVO)
	 */
	public void deleteDeptHosDisCode(DepartmentHosDisMasterVO _deptHosDisVO, String[] _selectedRecord, String _choice, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DepartmentHosDisMasterDAO deptHosDisDAO = new DepartmentHosDisMasterDAO(tx);

		try
		{
			tx.begin();

			if ((_choice.equals(OpdConfig.CHOICE_DISEASE)) || (_choice.equals(OpdConfig.CHOICE_SUBDISEASE)))
			{
				for (int i = 0; i < _selectedRecord.length; i++)
				{
					deptHosDisDAO.deleteByDiseaseCode(_deptHosDisVO, _selectedRecord[i], _userVO);
				}
			}

		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{

			tx.close();
		}
	}

	/*
	 * (non-Javadoc) Getting the Hospital disease List department and Unit wise
	 * 
	 * @see opd.bo.OpdMasterBOi#getDeptHosDisDetail(hisglobal.vo.DepartmentHosDisMasterVO, hisglobal.vo.UserVO)
	 */
	public DepartmentHosDisMasterVO[] getDeptHosDisDetail(DepartmentHosDisMasterVO _deptHosDisVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		DepartmentHosDisMasterDAO deptHosDisDAO = new DepartmentHosDisMasterDAO(tx);
		DepartmentHosDisMasterVO deptHosDisVO[] = null;
		try
		{
			tx.begin();
			if (_deptHosDisVO.getDepartmentUnitCode().equals(OpdConfig.DEFAULT_UNIT_CODE_HOS_MASTER)) deptHosDisVO = deptHosDisDAO
					.getHosDisDeatilsDeptWise(_deptHosDisVO, _userVO);
			else deptHosDisVO = deptHosDisDAO.getHosDisDeatilsUnitWise(_deptHosDisVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{

			tx.close();
		}
		return deptHosDisVO;
	}

	/*
	 * (non-Javadoc) Function used for saving the Hospital Diseases
	 * 
	 * @see opd.bo.OpdMasterBOi#saveDeptHosDisCode(hisglobal.vo.DepartmentHosDisMasterVO[], java.lang.String,
	 *      hisglobal.vo.UserVO)
	 */
	public void saveDeptHosDisCode(DepartmentHosDisMasterVO[] _deptHosDisVO, String _hosDisCodeType, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DepartmentHosDisMasterDAO deptHosDisDAO = new DepartmentHosDisMasterDAO(tx);
			if (_hosDisCodeType.equals(OpdConfig.CHOICE_DISEASE) || _hosDisCodeType.equals(OpdConfig.CHOICE_SUBDISEASE))
			{
				for (int i = 0; i < _deptHosDisVO.length; i++)
				{

					/*
					 * 
					 * 
					 * 
					 * deptHosDisVO=new OpdMasterBO().getDetailByCode(_deptHosVO[i],OpdConfig.CHOICE_DISEASE,_userVO);
					 * for(int j=0;j<deptHosDisVO.length;j++)//For 2 { for(int k =0;k<deptHosDisVO.length;k++) {
					 * 
					 */

					if (deptHosDisDAO.exist(_deptHosDisVO[i], _userVO)) deptHosDisDAO.updateActive(_deptHosDisVO[i], _userVO);
					else deptHosDisDAO.save(_deptHosDisVO[i], _userVO);

				}
			}
		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{

			tx.close();
		}
	}

	// * Updating User Desk Unit Ward Mapping Master Record
	public void updateUserDeskUnitWardMappingMasterRecord(UserDeskUnitWardMappingMasterVO _UserDeskMenuMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			UserDeskUnitWardMappingMasterDAO daoobj = new UserDeskUnitWardMappingMasterDAO(tx);
			daoobj.update(_UserDeskMenuMstVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Getting service list by service type id
	public Map getDetail(ConsentMappingMasterVO consentMappingMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		List serviceList = null;
		List templateList = null;
		List SelectedServiceIdList = null;
		try
		{
			tx.begin();
			ConsentMappingMasterDAO daoObject = new ConsentMappingMasterDAO(tx);
			if (!consentMappingMasterVO.getServiceTypeId().equals(""))
			{
				String query = daoObject.getServiceListQuery(consentMappingMasterVO, _userVO);
				serviceList = daoObject.getServiceList(query, consentMappingMasterVO, _userVO);
				SelectedServiceIdList = daoObject.getSelectedServiceIdList(consentMappingMasterVO, _userVO);
			}

			if (consentMappingMasterVO.getChoice() != null)
			{
				if (consentMappingMasterVO.getChoice().equals(OpdConfig.TEMPLATE_MODE_CONSENT))
				{
					consentMappingMasterVO.setTemplateCategory(GenericTemplateConfig.TEMPLATE_CATEGORY_CONSENT);
				}
				else
				{
					consentMappingMasterVO.setTemplateCategory(GenericTemplateConfig.TEMPLATE_CATEGORY_GUIDELINES);
				}
				templateList = daoObject.getTemplateList(consentMappingMasterVO, _userVO);
			}

			essentialMap.put(OpdConfig.OPD_SERVICE_LIST, serviceList);
			essentialMap.put(OpdConfig.OPD_TEMPLATE_LIST, templateList);
			essentialMap.put(OpdConfig.OPD_SELECTED_SERVICEID_LIST, SelectedServiceIdList);

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	/*
	 * ***********************Allergy Type Master Functions **************************
	 */

	public AllergyTypeVO getAllergyType(String _allergyCode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		AllergyTypeVO allergyTypeVO = null;
		try
		{
			tx.begin();
			AllergyTypeDAO daoObject = new AllergyTypeDAO(tx);
			allergyTypeVO = daoObject.getDetail(_allergyCode, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return allergyTypeVO;
	}

	public boolean updateAllergyType(AllergyTypeVO _allergyTypeVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		try
		{
			tx.begin();
			AllergyTypeDAO daoObject = new AllergyTypeDAO(tx);
			String record = daoObject.checkDuplicateBeforeUpdate(_allergyTypeVO, _userVO);
			if (record == null)
			{
				daoObject.update(_allergyTypeVO, _userVO);
				flag = true;
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	}

	public String getAllergySensitivitydesc(String sensitvity, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		String record = "";
		try
		{
			tx.begin();
			AllergyTypeDAO daoObject = new AllergyTypeDAO(tx);
			record = daoObject.getAllergySensitivitydesc(sensitvity, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return record;
	}

	public Map getTheValues(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List tableName = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			AllergyTypeDAO daoobj = new AllergyTypeDAO(tx);

			tableName = daoobj.getTheValues(_userVO);
			essentialMap.put(OpdConfig.ALLERGYTYPE_ALL_TABLE_NAME, tableName);

		}

		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public List getTableData(UserVO _userVO, String TableId)
	{

		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			AllergyTypeDAO daoobj = new AllergyTypeDAO(tx);
			list = daoobj.getTableData(_userVO, TableId);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return list;
	}

	public List getPrimaryKey(String tableName)
	{

		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			AllergyTypeDAO daoobj = new AllergyTypeDAO(tx);
			list = daoobj.getPrimaryKey(tableName);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return list;
	}

	public List getAllergySensistivity(UserVO _userVO)
	{

		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoobj = new OpdEssentialDAO(tx);
			list = daoobj.getListSensitivity(_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return list;
	}

	// Inserting into Allergy Type Master in Dynamic Mode
	public boolean insertAllergyTypeDynamicMode(AllergyTypeVO _allergyTypeVO, UserVO _userVO)
	{
		String allergytypeCode;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String record;
		boolean flag = false;
		try
		{
			tx.begin();
			AllergyTypeDAO daoObject = new AllergyTypeDAO(tx);
			record = daoObject.checkDuplicateBeforeInsert(_allergyTypeVO, _userVO);
			if (record == null)
			{
				daoObject.createInDynamicMode(_allergyTypeVO, _userVO);
				allergytypeCode = daoObject.getAllergyTypeCode(_allergyTypeVO, _userVO);
				daoObject.insert(_allergyTypeVO, _userVO, allergytypeCode);
				flag = true;
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	}

	public Map getAllergyTypeNotInAllergyWiseSymptom(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List allergyType = new ArrayList();
		List symptom = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			AllergyWiseSymptomMasterDAO objDAO = new AllergyWiseSymptomMasterDAO(tx);
			allergyType = objDAO.getAllergyTypeNotInAllergyWiseSymptom(_userVO);
			symptom = objDAO.getSymptomListWhereSymptomTypeIsOne(_userVO);
			essentialMap.put(OpdConfig.ALLERGY_TYPE, allergyType);
			essentialMap.put(OpdConfig.SYMPTOM_DESC_WHERE_SYMPTOM_TYPE_IS_ONE, symptom);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	

	public void addAllergyTypeAgainstSymptom(AllergyWiseSymptomMasterVO[] _voAllergyWiseSymptom, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			AllergyWiseSymptomMasterDAO objDAO = new AllergyWiseSymptomMasterDAO(tx);
			for (int i = 0; i < _voAllergyWiseSymptom.length; i++)
			{
				objDAO.addAllergyTypeAgainstSymptom(_voAllergyWiseSymptom[i], _UserVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}
	
	public List getDetail(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO, UserVO _UserVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			AllergyWiseSymptomMasterDAO objDAO = new AllergyWiseSymptomMasterDAO(tx);
			list = objDAO.getListForView(allergyWiseSymptomMasterVO, _UserVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return list;
	}

	public Map getDetails(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List selectedList = new ArrayList();
		List allList = new ArrayList();
		// List allergyType = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			AllergyWiseSymptomMasterDAO objDAO = new AllergyWiseSymptomMasterDAO(tx);
			// allergyType =
			// objDAO.getAllergyTypeNotInAllergyWiseSymptom(_userVO);
			selectedList = objDAO.getSelectedList(allergyWiseSymptomMasterVO, _userVO);
			allList = objDAO.getRemainingList(allergyWiseSymptomMasterVO, _userVO);
			String allergyType = objDAO.getAllergyType(allergyWiseSymptomMasterVO, _userVO);
			essentialMap.put(OpdConfig.SYMPTOM_SELECTED_SYMPTOM_LIST, selectedList);
			essentialMap.put(OpdConfig.SYMPTOM_ALL_SYMPTOM_LIST, allList);
			essentialMap.put(OpdConfig.ALLERGY_TYPE_BY_ALLERGY_TYPE_CODE, allergyType);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	

	// saving consent mapping master detail
	public void saveDetail(ConsentMappingMasterVO[] consentMappingMasterVO, String[] serviceId, List lstServices, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ConsentMappingMasterDAO daoObject = new ConsentMappingMasterDAO(tx);
			String serviceDesc = "";
			for (int j = 0; j < serviceId.length; j++)
			{
				for (int i = 0; i < consentMappingMasterVO.length; i++)
				{
					Iterator itr = lstServices.iterator();
					while (itr.hasNext())
					{
						Entry obj = (Entry) itr.next();
						if (serviceId[j].equals(obj.getValue()))
						{
							serviceDesc = obj.getLabel();
						}

					}
					consentMappingMasterVO[i].setServiceId(serviceId[j]);
					consentMappingMasterVO[i].setServiceDesc(serviceDesc);
					daoObject.create(consentMappingMasterVO[i], _userVO);
				}
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public Map getModifyDetail(ConsentMappingMasterVO consentMappingMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ConsentMappingMasterVO _consentMappingMasterVO = new ConsentMappingMasterVO();
		List templateList = null;
		List selectedTemplateList = null;
		List serviceLst = null;
		List selectedServiceLst = null;
		Map essentialMap = new HashMap();
		try
		{
			tx.begin();
			ConsentMappingMasterDAO daoObject = new ConsentMappingMasterDAO(tx);

			_consentMappingMasterVO = daoObject.getServiceTypeDesc(consentMappingMasterVO, _userVO);
			consentMappingMasterVO.setServiceTypeDesc(_consentMappingMasterVO.getServiceTypeDesc());

			_consentMappingMasterVO = daoObject.getServiceDescAndTemplateType(consentMappingMasterVO, _userVO);

			consentMappingMasterVO.setServiceDesc(_consentMappingMasterVO.getServiceDesc());
			consentMappingMasterVO.setTemplateType(_consentMappingMasterVO.getTemplateType());
			if (consentMappingMasterVO.getTemplateType().equals("1"))
			{
				consentMappingMasterVO.setTemplateCategory(GenericTemplateConfig.TEMPLATE_CATEGORY_CONSENT);
			}
			else
			{
				consentMappingMasterVO.setTemplateCategory(GenericTemplateConfig.TEMPLATE_CATEGORY_GUIDELINES);
			}
			_consentMappingMasterVO = daoObject.getTemplateTypeDesc(consentMappingMasterVO, _userVO);
			consentMappingMasterVO.setTemplateDesc(_consentMappingMasterVO.getTemplateDesc());

			serviceLst = daoObject.getServiceList(consentMappingMasterVO, _userVO);
			selectedServiceLst = daoObject.getSelectedServiceList(consentMappingMasterVO, _userVO);

			essentialMap.put(OpdConfig.OPD_SERVICE_LIST, serviceLst);
			essentialMap.put(OpdConfig.OPD_SELECTED_SERVICE_LIST, selectedServiceLst);

			templateList = daoObject.getTemplateList(consentMappingMasterVO, _userVO);
			selectedTemplateList = daoObject.getSelectedTemplateList(consentMappingMasterVO, _userVO);

			essentialMap.put(OpdConfig.OPD_TEMPLATE_LIST, templateList);
			essentialMap.put(OpdConfig.OPD_SELECTED_CONSENTMAPPING_VO, consentMappingMasterVO);
			essentialMap.put(OpdConfig.OPD_SELECTED_TEMPLATE_LIST, selectedTemplateList);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	// saving modify consent mapping master detail
	public void saveModifyDetail(ConsentMappingMasterVO[] consentMappingMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			ConsentMappingMasterDAO daoObject = new ConsentMappingMasterDAO(tx);
			ConsentMappingMasterVO _consentMappingMasterVO = new ConsentMappingMasterVO();
			HelperMethods.populate(_consentMappingMasterVO, consentMappingMasterVO[0]);

			daoObject.updateConsentMappingDetail(_consentMappingMasterVO, _userVO);
			/*
			 * if(_consentMappingMasterVO.getTemplateType().equals(GenericTemplateConfig.TEMPLATE_TYPE_CONSENT)) {
			 * _consentMappingMasterVO.setTemplateType("1"); } else { _consentMappingMasterVO.setTemplateType("2"); }
			 */
			for (int i = 0; i < consentMappingMasterVO.length; i++)
			{
				if (consentMappingMasterVO[i].getTemplateType().equals("1"))
				{
					consentMappingMasterVO[i].setChoice("1");
				}
				else
				{
					consentMappingMasterVO[i].setChoice("2");
				}

				daoObject.create(consentMappingMasterVO[i], _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void saveAlergySymMasterModifyDetail(AllergyWiseSymptomMasterVO[] allergyWiseSymptomMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			AllergyWiseSymptomMasterDAO objDAO = new AllergyWiseSymptomMasterDAO(tx);
			AllergyWiseSymptomMasterVO _allergyWiseSymptomMasterVO = new AllergyWiseSymptomMasterVO();
			HelperMethods.populate(_allergyWiseSymptomMasterVO, allergyWiseSymptomMasterVO[0]);

			objDAO.updateAlergySymMasterDetail(_allergyWiseSymptomMasterVO, _UserVO);

			for (int i = 0; i < allergyWiseSymptomMasterVO.length; i++)
			{

				objDAO.addAllergyTypeAgainstSymptom(allergyWiseSymptomMasterVO[i], _UserVO);
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public UserDeskMenuMasterVO gettingUnitName(String _deptUnitCode, UserVO _UserVO)
	{

		UserDeskMenuMasterVO unitName = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitMasterDAO daoobj = new UnitMasterDAO(tx);
			unitName = daoobj.gettingUnitName(_deptUnitCode, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return unitName;
	}

	public String getUnitName(String _deptUnitCode, UserVO _UserVO)
	{
		String unitName = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitMasterDAO daoobj = new UnitMasterDAO(tx);
			unitName = daoobj.getUnit(_deptUnitCode, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return unitName;
	}

	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskType(String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		UserDeskMenuTemplateMasterVO[] userDeskMenuTemplateVO = null;
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			userDeskMenuTemplateVO = daoobj.getTemplateByDeskType(deskId, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return userDeskMenuTemplateVO;
	}

	public void saveForDeskId(UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);

			// Deleting All Exisiting Records First
			UserDeskMenuTemplateMasterVO vo = userDeskMenuDeskVO[0];
			if (vo.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE)) daoobj.deleteForDeskId(vo.getDeskId(), userVO);
			else if (vo.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE)) daoobj.deleteForDeskNUnit(vo.getUnitCode(), vo
					.getDeskId(), userVO);
			else if (vo.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE)) daoobj.deleteForDeskUnitNSeat(vo.getUserSeatId(),
					vo.getUnitCode(), vo.getDeskId(), userVO);
			else if (vo.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE)) daoobj.deleteForDeskUnitNWard(vo.getWardCode(),
					vo.getUnitCode(), vo.getDeskId(), userVO);
			else if (vo.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE)) daoobj.deleteForDeskUnitNWardNSeat(vo
					.getUserSeatId(), vo.getWardCode(), vo.getUnitCode(), vo.getDeskId(), userVO);

			// Insert New Records
			for (int i = 0; i < userDeskMenuDeskVO.length; i++)
				daoobj.saveForDeskId(userDeskMenuDeskVO[i], userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void deleteForDeskId(String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.deleteForDeskId(deskId, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	// saving desk menu master detail
	public void saveDetail(DeskMenuMasterVO deskMenuMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			DeskMenuMasterDAO daoObject = new DeskMenuMasterDAO(tx);
			boolean flag = daoObject.checkDuplicateDeskMenu(deskMenuMasterVO, _userVO);
			if (flag)
			{
				daoObject.create(deskMenuMasterVO, _userVO);
			}
			else
			{

				throw new HisRecordNotFoundException("This Menu For This Desk Already Added.");
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public DeskMenuMasterVO getModifyDetail(DeskMenuMasterVO deskMenuMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DeskMenuMasterVO _deskMenuMasterVO = new DeskMenuMasterVO();
		try
		{
			tx.begin();
			DeskMenuMasterDAO daoObject = new DeskMenuMasterDAO(tx);

			_deskMenuMasterVO = daoObject.fetchRecord(deskMenuMasterVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _deskMenuMasterVO;
	}

	public void saveForUnit(UserDeskMenuTemplateMasterVO[] userDeskMenuDeskVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			for (int i = 0; i < userDeskMenuDeskVO.length; i++)
			{
				daoobj.saveForUnit(userDeskMenuDeskVO[i], userVO);
			}

		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskNUnit(String unitCode, String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		UserDeskMenuTemplateMasterVO[] userDeskMenuTemplateVO = null;
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			userDeskMenuTemplateVO = daoobj.getTemplateByDeskNUnit(unitCode, deskId, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return userDeskMenuTemplateVO;
	}

	public void deleteForDeskNUnit(String unitCode, String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.deleteForDeskNUnit(unitCode, deskId, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNSeat(String seatId, String unitCode, String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		UserDeskMenuTemplateMasterVO[] userDeskMenuTemplateVO = null;
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			userDeskMenuTemplateVO = daoobj.getTemplateByDeskUnitNSeat(seatId, unitCode, deskId, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return userDeskMenuTemplateVO;
	}

	public void deleteForDeskUnitNSeat(String seatId, String unitCode, String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.deleteForDeskUnitNSeat(seatId, unitCode, deskId, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNWard(String wardCode, String unitCode, String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		UserDeskMenuTemplateMasterVO[] userDeskMenuTemplateVO = null;
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			userDeskMenuTemplateVO = daoobj.getTemplateByDeskUnitNWard(wardCode, unitCode, deskId, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return userDeskMenuTemplateVO;
	}

	public void deleteForDeskUnitNWard(String wardCode, String unitCode, String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.deleteForDeskUnitNWard(wardCode, unitCode, deskId, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNWardNSeat(String seatId, String wardCode, String unitCode, String deskId,
			UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		UserDeskMenuTemplateMasterVO[] userDeskMenuTemplateVO = null;
		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			userDeskMenuTemplateVO = daoobj.getTemplateByDeskUnitNWardNSeat(seatId, wardCode, unitCode, deskId, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return userDeskMenuTemplateVO;
	}

	public void deleteForDeskUnitNWardNSeat(String seatId, String wardCode, String unitCode, String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			UserDeskMenuTemplateMasterDAO daoobj = new UserDeskMenuTemplateMasterDAO(tx);
			daoobj.deleteForDeskUnitNWardNSeat(seatId, wardCode, unitCode, deskId, userVO);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void saveModifyDetail(DeskMenuMasterVO deskMenuMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			DeskMenuMasterDAO daoObject = new DeskMenuMasterDAO(tx);

			boolean flag = daoObject.check_Modify_DuplicateDeskMenu(deskMenuMasterVO, _userVO);
			if (flag)
			{
				daoObject.update(deskMenuMasterVO, _userVO);
			}
			else
			{

				throw new HisRecordNotFoundException("This Menu For This Desk Already Added.");
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	/*
	 * ********************* function for Template mapping master ***********************************
	 */

	public Map getEssentialForTemplateMapping(String categoryCode, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		String templateCategoryName = "";
		List deptList = new ArrayList();
		List templateList = new ArrayList();
		Map essentialMap = new HashMap();
		try
		{
			tx.begin();
			TemplateMappingMstDAOi daoObject = new TemplateMappingMstDAO(tx);
			templateCategoryName = daoObject.getCategoryName(categoryCode, userVO);
			essentialMap.put(OpdConfig.TEMPLATE_CATEGORY_NAME, templateCategoryName);
			deptList = daoObject.getDepartmentNotAdded(categoryCode, userVO);
			essentialMap.put(OpdConfig.TEMPLATE_MAPPING_DEPT_NOT_ASSIGNED, deptList);
			templateList = daoObject.getTemplateListNotAdded(categoryCode, userVO);
			essentialMap.put(OpdConfig.TEMPLATE_NOT_ASSIGNED, templateList);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public List getUnitNotAdded(TemplateMappingMstVO templateMappingVO, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List unitList = new ArrayList();
		try
		{
			tx.begin();
			TemplateMappingMstDAOi daoObject = new TemplateMappingMstDAO(tx);
			unitList = daoObject.getUnitNotAdded(templateMappingVO, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return unitList;
	}

	public List getAllUnits(String deptCode, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List unitList = new ArrayList();
		try
		{
			tx.begin();
			TemplateMappingMstDAOi daoObject = new TemplateMappingMstDAO(tx);
			unitList = daoObject.getAllUnits(deptCode, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return unitList;
	}

	public void saveTemplateMapping(TemplateMappingMstVO[] templateMappingVOs, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		TemplateMappingMstVO templateMappingVO = new TemplateMappingMstVO();
		try
		{
			tx.begin();
			TemplateMappingMstDAOi daoObject = new TemplateMappingMstDAO(tx);
			for (int i = 0; i < templateMappingVOs.length; i++)
			{
				templateMappingVO.setTemplateCategory(templateMappingVOs[i].getTemplateCategory());
				templateMappingVO.setTemplateId(templateMappingVOs[i].getTemplateId());
				templateMappingVO.setDeptCode(templateMappingVOs[i].getDeptCode());
				templateMappingVO.setDeptUnitCode(templateMappingVOs[i].getDeptUnitCode());
				templateMappingVO.setWardCode(templateMappingVOs[i].getWardCode());
				templateMappingVO.setIsDefault(templateMappingVOs[i].getIsDefault());

				daoObject.saveTemplateMapping(templateMappingVO, userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public void modifyTemplateMapping(TemplateMappingMstVO[] updateTemplateMappingVOs, TemplateMappingMstVO[] insertTemplateMappingVOs, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		TemplateMappingMstVO templateMappingVO = new TemplateMappingMstVO();
		try
		{
			tx.begin();
			TemplateMappingMstDAOi daoObject = new TemplateMappingMstDAO(tx);
			for (int i = 0; i < updateTemplateMappingVOs.length; i++)
			{
				templateMappingVO.setTemplateCategory(updateTemplateMappingVOs[i].getTemplateCategory());
				templateMappingVO.setTemplateMappingId(updateTemplateMappingVOs[i].getTemplateMappingId());

				daoObject.modifyTemplateMapping(templateMappingVO, userVO);
			}
			for (int i = 0; i < insertTemplateMappingVOs.length; i++)
			{
				templateMappingVO.setTemplateCategory(insertTemplateMappingVOs[i].getTemplateCategory());
				templateMappingVO.setTemplateId(insertTemplateMappingVOs[i].getTemplateId());
				templateMappingVO.setDeptCode(insertTemplateMappingVOs[i].getDeptCode());
				templateMappingVO.setDeptUnitCode(insertTemplateMappingVOs[i].getDeptUnitCode());
				templateMappingVO.setWardCode(insertTemplateMappingVOs[i].getWardCode());
				templateMappingVO.setIsDefault(insertTemplateMappingVOs[i].getIsDefault());

				daoObject.modifyInsertTemplateMapping(templateMappingVO, userVO);
			}
		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public Map getTemplateMapping(TemplateMappingMstVO templateMappingVO, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		TemplateMappingMstVO templateMappingVOs[] = null;
		TemplateMappingMstVO fetchedTemplateMappingVO = new TemplateMappingMstVO();
		Map essentialMap = new HashMap();
		try
		{
			tx.begin();
			TemplateMappingMstDAOi daoObject = new TemplateMappingMstDAO(tx);
			fetchedTemplateMappingVO = daoObject.checkBeforeModify(templateMappingVO, userVO);
			if (fetchedTemplateMappingVO.getDeptUnitCode() == null)
			{
				templateMappingVOs = daoObject.getTemplateMapping(templateMappingVO, 1, userVO);
			}
			else if (fetchedTemplateMappingVO.getWardCode() == null)
			{
				templateMappingVOs = daoObject.getTemplateMapping(templateMappingVO, 2, userVO);
			}
			else
			{
				templateMappingVOs = daoObject.getTemplateMapping(templateMappingVO, 0, userVO);
			}
			if (templateMappingVOs != null) essentialMap.put(OpdConfig.TEMPLATE_MAPPING_DETAIL, templateMappingVOs);
			// List
			// templateList=daoObject.getAddedTemplateList(templateMappingVO,
			// userVO);
			// essentialMap.put(OpdConfig.TEMPLATE_ASSIGNED, templateList);
			List templateListNotAdded = daoObject.getTemplateListNotAdded(templateMappingVO.getTemplateCategory(), userVO);
			essentialMap.put(OpdConfig.TEMPLATE_NOT_ASSIGNED, templateListNotAdded);
			String templateCategoryName = daoObject.getCategoryName(templateMappingVO.getTemplateCategory(), userVO);
			essentialMap.put(OpdConfig.TEMPLATE_CATEGORY_NAME, templateCategoryName);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public List getAllDepartments(UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listDepartment = new ArrayList();
		try
		{
			tx.begin();
			OpdEssentialDAO daoObject = new OpdEssentialDAO(tx);
			listDepartment = daoObject.getAllDepartmentList(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return listDepartment;
	}

	public List getWardNotAdded(TemplateMappingMstVO templateMappingVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List wardList = new ArrayList();
		try
		{
			tx.begin();
			TemplateMappingMstDAOi daoObject = new TemplateMappingMstDAO(tx);
			wardList = daoObject.getWardNotAdded(templateMappingVO, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return wardList;
	}

	public void saveDrugDoseDetail(DrugDoseMstVO drugDoseMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			DrugDoseMasterDAO daoObject = new DrugDoseMasterDAO(tx);
			boolean flag = daoObject.checkDuplicate(drugDoseMstVO, _userVO);

			if (flag)
			{
				daoObject.create(drugDoseMstVO, _userVO);
			}
			else
			{
				throw new HisRecordNotFoundException("This Dose Name Allready Added For This ItemType");
			}

			// daoObject.create(drugDoseMstVO,_userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public DrugDoseMstVO getDrugDoseModifyDetail(DrugDoseMstVO drugDoseMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// DrugDoseMstVO[] _drugDoseMstVO = null;

		try
		{
			tx.begin();
			DrugDoseMasterDAO daoObject = new DrugDoseMasterDAO(tx);

			drugDoseMstVO = daoObject.fetchRecord(drugDoseMstVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return drugDoseMstVO;
	}

	public void saveDrugDoseModifyDetail(DrugDoseMstVO drugDoseMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			DrugDoseMasterDAO daoObject = new DrugDoseMasterDAO(tx);
			boolean flag = daoObject.checkModifyDuplicate(drugDoseMstVO, _userVO);

			if (flag)
			{
				daoObject.modify(drugDoseMstVO, _userVO);
				daoObject.modifySave(drugDoseMstVO, _userVO);
			}
			else
			{
				throw new HisRecordNotFoundException("Duplicate record.");
			}

			

			/*
			 * daoObject.update(drugDoseMstVO,_userVO);
			 * 
			 * for(int i=0;i<drugDoseMstVO.length;i++) { if(drugDoseMstVO[i].getDoseId()==null ||
			 * drugDoseMstVO[i].getDoseId().equals("")) { drugDoseMstVO[i].setSereialNo("1");
			 * daoObject.create(drugDoseMstVO[i],_userVO); } else { daoObject.modifySave(drugDoseMstVO[i],_userVO); } }
			 */
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void saveDetail(DeskTypeMenuMappingVO[] _deskTypeMenuMappingVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			DeskTypeMenuMappingMstDAO daoobj = new DeskTypeMenuMappingMstDAO(tx);
			for (int i = 0; i < _deskTypeMenuMappingVO.length; i++)
			{
				daoobj.create(_deskTypeMenuMappingVO[i], _userVO);
			}

		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public Map getModifyDetail(DeskTypeMenuMappingVO deskTypeMenuMappingVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		try
		{
			tx.begin();
			DeskTypeMenuMappingMstDAO daoObject = new DeskTypeMenuMappingMstDAO(tx);
			OpdEssentialDAO daoEssen = new OpdEssentialDAO(tx);
			List selectedMenuLst = daoObject.getSelectedMenuList(deskTypeMenuMappingVO, _userVO);
			String deskTypeDesc = daoEssen.getDeskTypeDesc(deskTypeMenuMappingVO.getDeskType(), _userVO);
			List remaningMenuLst = daoObject.getRemaningMenuList(deskTypeMenuMappingVO, _userVO);
			essentialMap.put(OpdConfig.SELECTED_MENU_LIST, selectedMenuLst);
			essentialMap.put(OpdConfig.DESC_TYPE_DESC, deskTypeDesc);
			essentialMap.put(OpdConfig.REMANING_MENU_LIST, remaningMenuLst);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public void saveModifyDetail(DeskTypeMenuMappingVO[] _deskTypeMenuMappingVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			DeskTypeMenuMappingMstDAO daoobj = new DeskTypeMenuMappingMstDAO(tx);

			DeskTypeMenuMappingVO vo = new DeskTypeMenuMappingVO();
			vo.setDeskType(_deskTypeMenuMappingVO[0].getDeskType());// For
			// getting
			// desk type
			daoobj.updateDeskTypeMenu(vo, _userVO);

			for (int i = 0; i < _deskTypeMenuMappingVO.length; i++)
			{
				daoobj.create(_deskTypeMenuMappingVO[i], _userVO);
			}

		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public MacroMasterVO getProcessName(String processID, UserVO _UserVO)
	{
		MacroMasterVO _macroMstVO = new MacroMasterVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MacroMstDAO daoobj = new MacroMstDAO(tx);
			_macroMstVO = daoobj.processName(processID, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _macroMstVO;
	}

	public boolean saveMacroInfo(MacroMasterVO _macroMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag = true;
		try
		{
			tx.begin();

			MacroMstDAO daoobj = new MacroMstDAO(tx);

			String macroID = daoobj.checkDuplicateMacroInfo(_macroMstVO, _UserVO);
			if (macroID == null)
			{
				daoobj.saveMacroInfo(_macroMstVO, _UserVO);
				hasFlag = true;
			}
			else
			{
				hasFlag = false;
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();

		}
		return hasFlag;
	}

	public MacroMasterVO fetchMacroInfo(MacroMasterVO _macroMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			MacroMstDAO daoobj = new MacroMstDAO(tx);
			_macroMstVO = daoobj.fetchMacroInfo(_macroMstVO, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _macroMstVO;
	}

	public void saveModMacroInfo(MacroMasterVO _macroMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			MacroMstDAO daoobj = new MacroMstDAO(tx);
			daoobj.updateMacroInfo(_macroMstVO, _UserVO);

			daoobj.saveModMacroInfo(_macroMstVO, _UserVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public DrugRouteMstVO getItemName(String itemID, UserVO _UserVO)
	{
		DrugRouteMstVO _drugRouteMstVO = new DrugRouteMstVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DrugRouteMstDAO daoobj = new DrugRouteMstDAO(tx);
			_drugRouteMstVO = daoobj.itemName(itemID, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _drugRouteMstVO;
	}

	public boolean saveDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag = true;
		try
		{
			tx.begin();

			DrugRouteMstDAO daoobj = new DrugRouteMstDAO(tx);

			String drugRouteID = daoobj.checkDuplicateDrugRouteInfo(_drugRouteMstVO, _UserVO);
			if (drugRouteID == null)
			{
				daoobj.saveDrugRouteInfo(_drugRouteMstVO, _UserVO);
				hasFlag = true;
			}
			else
			{
				hasFlag = false;
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();

		}
		return hasFlag;
	}

	public DrugRouteMstVO fetchDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			DrugRouteMstDAO daoobj = new DrugRouteMstDAO(tx);
			_drugRouteMstVO = daoobj.fetchDrugRouteInfo(_drugRouteMstVO, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _drugRouteMstVO;
	}

	//Added By Chetan Sharma 
	//Date: 07_12_2015  
	public DrugRouteMstVO getDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			DrugRouteMstDAO daoobj = new DrugRouteMstDAO(tx);
			_drugRouteMstVO = daoobj.getDrugRouteInfo(_drugRouteMstVO, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _drugRouteMstVO;
	}
	
	
	
	
	
	
	
	
	public void saveModDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag = true;
		try
		{
			tx.begin();

			DrugRouteMstDAO daoobj = new DrugRouteMstDAO(tx);
			String drugRouteID = daoobj.checkDuplicateDrugRouteInfoForModify(_drugRouteMstVO, _UserVO);
			if (drugRouteID == null)
			{
				daoobj.updateDrugRouteInfo(_drugRouteMstVO, _UserVO);

				daoobj.saveModDrugRouteInfo(_drugRouteMstVO, _UserVO);

				hasFlag = true;
			}
			else
			{
				throw new HisRecordNotFoundException("Duplicate record.");
			}

			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public List getDeskType(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List list = new ArrayList();
		try
		{
			tx.begin();
			DeskWiseDefaultProfileMstDAO daoObject = new DeskWiseDefaultProfileMstDAO(tx);
			list = daoObject.getDeskType(userVO);

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return list;
	}

	public List getDeskName(String deskTypeId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List list = new ArrayList();
		try
		{
			tx.begin();
			DeskWiseDefaultProfileMstDAO daoObject = new DeskWiseDefaultProfileMstDAO(tx);
			list = daoObject.getDeskName(deskTypeId, userVO);

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return list;
	}

	public List getMenuName(String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List defaultList = new ArrayList();
		try
		{
			tx.begin();
			DeskWiseDefaultProfileMstDAO daoObject = new DeskWiseDefaultProfileMstDAO(tx);
			defaultList = daoObject.checkDefaultValue(deskId, userVO);
			/*
			 * if(defaultList.equals(null)) { defaultMenu=daoObject.getAllMenuName(deskId,userVO); } else {
			 * defaultMenu=daoObject.getDefaultMenuName(deskId,userVO);
			 * nonDefaultMenu=daoObject.getNonDefaultMenuName(deskId,userVO); }
			 */

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return defaultList;
	}

	public List getAllMenuName(String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List allMenuList = new ArrayList();

		try
		{
			tx.begin();
			DeskWiseDefaultProfileMstDAO daoObject = new DeskWiseDefaultProfileMstDAO(tx);
			allMenuList = daoObject.getAllMenuName(deskId, userVO);
			/*
			 * if(defaultList.equals(null)) { defaultMenu=daoObject.getAllMenuName(deskId,userVO); } else {
			 * defaultMenu=daoObject.getDefaultMenuName(deskId,userVO);
			 * nonDefaultMenu=daoObject.getNonDefaultMenuName(deskId,userVO); }
			 */

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return allMenuList;
	}

	public List getNonDefaultMenuName(String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List list = new ArrayList();
		try
		{
			tx.begin();
			DeskWiseDefaultProfileMstDAO daoObject = new DeskWiseDefaultProfileMstDAO(tx);
			list = daoObject.getNonDefaultMenuName(deskId, userVO);

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return list;
	}

	public List getDefaultMenuName(String deskId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List list = new ArrayList();
		try
		{
			tx.begin();
			DeskWiseDefaultProfileMstDAO daoObject = new DeskWiseDefaultProfileMstDAO(tx);
			list = daoObject.getDefaultMenuName(deskId, userVO);

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return list;
	}

	public void saveDefaultProfileDetails(DeskWiseDefaultProfileMstVO _DeskWiseDefaultProfileMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			DeskWiseDefaultProfileMstDAO daoobj = new DeskWiseDefaultProfileMstDAO(tx);
			String profileOrder = "";
			if (_DeskWiseDefaultProfileMstVO.getMenuListDefault() != null)
			{
				for (int i = 0; i < _DeskWiseDefaultProfileMstVO.getMenuListDefault().length; i++)
				{
					_DeskWiseDefaultProfileMstVO.setDeskMenuId(_DeskWiseDefaultProfileMstVO.getMenuListDefault()[i]);
					_DeskWiseDefaultProfileMstVO.setIsDefault(OpdConfig.IS_PROFILE_DEFAULT_YES);
					profileOrder = String.valueOf(i + 1);
					_DeskWiseDefaultProfileMstVO.setProfileOrder(profileOrder);

					daoobj.saveDefaultProfileDetails(_DeskWiseDefaultProfileMstVO, _UserVO);

				}
			}

			if (_DeskWiseDefaultProfileMstVO.getMenuListNonDefault() != null)
			{

				for (int j = 0; j < _DeskWiseDefaultProfileMstVO.getMenuListNonDefault().length; j++)
				{
					_DeskWiseDefaultProfileMstVO.setDeskMenuId(_DeskWiseDefaultProfileMstVO.getMenuListNonDefault()[j]);
					_DeskWiseDefaultProfileMstVO.setIsDefault(OpdConfig.IS_PROFILE_DEFAULT_NO);
					profileOrder = String.valueOf(j + 1);
					_DeskWiseDefaultProfileMstVO.setProfileOrder(profileOrder);
					daoobj.saveDefaultProfileDetails(_DeskWiseDefaultProfileMstVO, _UserVO);
				}
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void saveImagePointerDetail(ImagePointerMasterVO imagePointerMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			ImagePointerMasterDAO daoObject = new ImagePointerMasterDAO(tx);
			String flag = daoObject.checkDuplicateImagePointer(imagePointerMasterVO, _userVO);
			if (flag.equals("0"))
			{
				daoObject.create(imagePointerMasterVO, _userVO);
			}
			else
			{

				throw new HisRecordNotFoundException("This Image Pointer Already Added.");
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public ImagePointerMasterVO getModifyDetail(ImagePointerMasterVO imagePointerMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ImagePointerMasterVO _ImagePointerMasterVO = new ImagePointerMasterVO();

		try
		{
			tx.begin();
			ImagePointerMasterDAO daoObject = new ImagePointerMasterDAO(tx);

			_ImagePointerMasterVO = daoObject.getDataForModify(imagePointerMasterVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _ImagePointerMasterVO;
	}

	public void saveModifyDetail(ImagePointerMasterVO imagePointerMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			ImagePointerMasterDAO daoObject = new ImagePointerMasterDAO(tx);

			String flag = daoObject.checkDuplicateNameForModify(imagePointerMasterVO, _userVO);
			if (flag.equals("0"))
			{
				daoObject.update(imagePointerMasterVO, _userVO);
				daoObject.modifySave(imagePointerMasterVO, _userVO);
			}
			else
			{

				throw new HisRecordNotFoundException("Image Description Already Exists");
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public ParameterRangeMasterVO getParaName(String paraID, UserVO _UserVO)
	{
		ParameterRangeMasterVO parameterRangeMasterVO = new ParameterRangeMasterVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ParaRangeMstDAO daoobj = new ParaRangeMstDAO(tx);
			parameterRangeMasterVO = daoobj.getparaName(paraID, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return parameterRangeMasterVO;
	}

	public boolean saveParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		boolean count = false;
		boolean duplicateRecord = false;
		try
		{
			tx.begin();

			ParaRangeMstDAO daoobj = new ParaRangeMstDAO(tx);
			if (!(parameterRangeMasterVO.getGenderCode().equals(OpdConfig.GENDER_FLAG_FOR_NOT_REQUIRED)))
			{
				count = daoobj.checkRange(parameterRangeMasterVO, _UserVO);
				if (count == true)
				{
					daoobj.saveParaRangeInfo(parameterRangeMasterVO, _UserVO);
					flag = true;
				}
				else throw new HisException("Age Overlapping");
			}
			else
			{
				duplicateRecord = daoobj.checkDuplicateRecord(parameterRangeMasterVO, _UserVO);
				if (duplicateRecord == true)
				{
					daoobj.saveParaRangeInfo(parameterRangeMasterVO, _UserVO);
					flag = true;
				}
				else throw new HisRecordNotFoundException("Duplicate Record Exists");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e.getMessage());
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();

		}
		return flag;
	}

	public ParameterRangeMasterVO fetchParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			ParaRangeMstDAO daoobj = new ParaRangeMstDAO(tx);
			parameterRangeMasterVO = daoobj.fetchParaRangeInfo(parameterRangeMasterVO, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return parameterRangeMasterVO;
	}

	public boolean saveModParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean count = false;
		boolean duplicateRecord = false;
		boolean flag = false;
		try
		{
			tx.begin();

			ParaRangeMstDAO daoobj = new ParaRangeMstDAO(tx);

			if (!(parameterRangeMasterVO.getGenderCode().equals(OpdConfig.GENDER_FLAG_FOR_NOT_REQUIRED)))
			{
				count = daoobj.checkRange(parameterRangeMasterVO, _UserVO);
				if (count == true)
				{
					daoobj.updateParaRangeInfo(parameterRangeMasterVO, _UserVO);
					daoobj.saveModParaRangeInfo(parameterRangeMasterVO, _UserVO);
					flag = true;
				}
				else throw new HisException("Age Overlapping");
			}
			else
			{
				duplicateRecord = daoobj.checkDuplicateRecord(parameterRangeMasterVO, _UserVO);
				if (duplicateRecord == true)
				{
					daoobj.updateParaRangeInfo(parameterRangeMasterVO, _UserVO);
					daoobj.saveModParaRangeInfo(parameterRangeMasterVO, _UserVO);
					flag = true;
				}
				else throw new HisRecordNotFoundException("Duplicate Record Exists");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	}

	// Profile Restricted Category
	public void saveProfileRestrictedCategory(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String patientCategoryCode = null;
		try
		{
			tx.begin();
			ProfileRestrictedCatDAOi daoobj = new ProfileRestrictedCatDAO(tx);
			patientCategoryCode = daoobj.checkDuplicateBeforeSave(profileRestrictedCategoryMasterVO, userVO);
			if (patientCategoryCode == null)
			{
				daoobj.save(profileRestrictedCategoryMasterVO, userVO);
			}
			else throw new HisDuplicateRecordException("Patient Category COde with Profile Type already exists");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	// Profile Restricted Category
	public void saveProfileRestrictedCategory(ProfileRestrictedCategoryMasterVO[] insertProfileRestrictedCatMstVO,
			ProfileRestrictedCategoryMasterVO[] updateProfileRestrictedCatMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// String patientCategoryCode = null;
		try
		{
			tx.begin();
			ProfileRestrictedCatDAOi daoobj = new ProfileRestrictedCatDAO(tx);

			for (ProfileRestrictedCategoryMasterVO vo : updateProfileRestrictedCatMstVO)
			{
				daoobj.updateIsValid(vo, userVO);
			}

			for (ProfileRestrictedCategoryMasterVO vo : insertProfileRestrictedCatMstVO)
			{
				daoobj.modifyInsert(vo, userVO);
			}

		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public ProfileRestrictedCategoryMasterVO fetchPatientCatModify(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO, UserVO _UserVO)
	{
		ProfileRestrictedCategoryMasterVO _profileRestrictedCategoryVOs = new ProfileRestrictedCategoryMasterVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ProfileRestrictedCatDAOi daoobj = new ProfileRestrictedCatDAO(tx);
			_profileRestrictedCategoryVOs = daoobj.fetchPatientCatModify(profileRestrictedCategoryMasterVO, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Appointment BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _profileRestrictedCategoryVOs;
	}

	public void modifySaveProfileRestrictedCategory(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String patientCategoryCode = null;
		try
		{
			tx.begin();
			ProfileRestrictedCatDAOi daoobj = new ProfileRestrictedCatDAO(tx);
			patientCategoryCode = daoobj.checkDuplicateBeforeModify(profileRestrictedCategoryMasterVO, userVO);
			if (patientCategoryCode == null)
			{
				daoobj.modify(profileRestrictedCategoryMasterVO, userVO);
				daoobj.modifyInsert(profileRestrictedCategoryMasterVO, userVO);
			}
			else throw new HisDuplicateRecordException("Patient Category COde with Profile Type already exists");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	// Profile Group Master
	public Map fetchProfileGroupEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List deptUnitCodeList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);

			deptUnitCodeList = opdEssentialDAO.getAllUnitList(_userVO);
			essentialMap.put(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST, deptUnitCodeList);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;

	}

	public void saveProfileGroupDetail(ProfileGroupMasterVO profileGroupMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String profileGroupName = null;
		try
		{
			tx.begin();
			ProfileGroupMasterDAOi daoobj = new ProfileGroupMasterDAO(tx);
			profileGroupName = daoobj.checkDuplicateBeforeSave(profileGroupMasterVO, userVO);
			if (profileGroupName == null)
			{
				daoobj.save(profileGroupMasterVO, userVO);
			}
			else throw new HisDuplicateRecordException("Profile Group Name Already Exists for this Department Unit");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public ProfileGroupMasterVO fetchProfileGroupDetailModify(ProfileGroupMasterVO profileGroupMasterVO, UserVO _UserVO)
	{
		ProfileGroupMasterVO _profileGroupMasterVOs = new ProfileGroupMasterVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ProfileGroupMasterDAOi daoobj = new ProfileGroupMasterDAO(tx);
			_profileGroupMasterVOs = daoobj.fetchProfileGroupDetailModify(profileGroupMasterVO, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Appointment BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _profileGroupMasterVOs;
	}

	public void modifySaveProfileGroupMaster(ProfileGroupMasterVO profileGroupMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String profileIsValid = null;
		try
		{
			tx.begin();

			ProfileGroupMasterDAOi daoobj = new ProfileGroupMasterDAO(tx);
			profileIsValid = daoobj.checkDuplicateBeforeModify(profileGroupMasterVO, userVO);
			if (profileIsValid == null)
			{
				daoobj.modify(profileGroupMasterVO, userVO);
				daoobj.modifyInsert(profileGroupMasterVO, userVO);
			}else if(!profileGroupMasterVO.getIsActive().equalsIgnoreCase(profileIsValid)){
				daoobj.modify(profileGroupMasterVO, userVO);
				daoobj.modifyInsert(profileGroupMasterVO, userVO);
			}
			else throw new HisDuplicateRecordException("Patient Category COde with Profile Type already exists");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	// Profile Group Detail
	public Map fetchProfileGroupDetailEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List deptUnitCodeList = null;
		List profileGroupList = null;
		List usersList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);

			profileGroupList = opdEssentialDAO.getProfileGroupList(_userVO);
			essentialMap.put(OpdConfig.PROFILE_GROUP_DETAIL_GROUP_LIST, profileGroupList);

			deptUnitCodeList = opdEssentialDAO.getAllUnitList(_userVO);
			essentialMap.put(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST, deptUnitCodeList);

			List _unitList = new ArrayList();
			_unitList.addAll(deptUnitCodeList);
			essentialMap.put(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST_ALL, _unitList);

			usersList = opdEssentialDAO.getSearchAllUsers(_userVO);
			essentialMap.put(OpdConfig.PROFILE_GROUP_DETAIL_ALL_USERS, usersList);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;

	}

	public void saveProfileGroupAccessPrivDetail(List<ProfileGroupDtlVO> lstProfileAccesses, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String strMaxSerialNoForSave="0";
		try
		{
			tx.begin();
			ProfileGroupDetailDAOi dao = new ProfileGroupDetailDAO(tx);
			strMaxSerialNoForSave=dao.getMaxSerialNoforSave(userVO.getHospitalCode());
			
			
			for (ProfileGroupDtlVO vo : lstProfileAccesses)
			{
				vo.setSerialNo(strMaxSerialNoForSave);
				dao.save(vo, userVO);
			}
			/*
			 * profileGroupName =daoobj.checkDuplicateBeforeSave(profileGroupMasterVO, userVO); if(profileGroupName==null){
			 * daoobj.save(profileGroupMasterVO, userVO); } else throw new HisDuplicateRecordException("Profile Group Name
			 * Already Exists for this Department Unit");
			 */
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public List<ProfileGroupDtlVO> fetchProfileGroupDetailAccessModify(ProfileGroupDtlVO profileGroupDetailVO, UserVO _UserVO)
	{
		List<ProfileGroupDtlVO> _profileGroupDetailList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ProfileGroupDetailDAOi daoobj = new ProfileGroupDetailDAO(tx);
			_profileGroupDetailList = daoobj.fetchProfileGroupDetailAccessModify(profileGroupDetailVO, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Appointment BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _profileGroupDetailList;
	}

	public void modifySaveProfileGroupDetail(List<ProfileGroupDtlVO> lstProfileAccesses, ProfileGroupDtlVO profileGroupDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			ProfileGroupDetailDAOi daoobj = new ProfileGroupDetailDAO(tx);

			daoobj.modify(profileGroupDtlVO, userVO);

			for (ProfileGroupDtlVO vo : lstProfileAccesses)
			{
				String serialNo = vo.getSerialNo();
				String newSerialNo = String.valueOf(Integer.parseInt(serialNo) + 1);
				vo.setSerialNo(newSerialNo);
				daoobj.modifyInsert(vo, userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	// Profile Group Master
	public Map fetchProfileAccessPolicyEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List deptUnitCodeList = null;
		List profileGroupList = null;
		List profileTypeList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			ProfileTypeDAO profileTypeDAO = new ProfileTypeDAO(tx);

			deptUnitCodeList = opdEssentialDAO.getAllUnitList(_userVO);
			essentialMap.put(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST, deptUnitCodeList);

			profileTypeList = profileTypeDAO.getAllProfileTypes(_userVO);
			essentialMap.put(OpdConfig.PROFILE_TYPE_LIST, profileTypeList);

			profileGroupList = opdEssentialDAO.getAllProfileGroupList(_userVO);
			essentialMap.put(OpdConfig.PROFILE_ACCESS_POLICY_GROUP_LIST, profileGroupList);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;

	}

	public void saveProfileAccessPolicy(List<ProfileAccessPolicyVO> profileAccessPolicyVOList, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// String deptUnit = null;
		try
		{
			tx.begin();
			ProfileAccessPolicyDAOi daoobj = new ProfileAccessPolicyDAO(tx);
			if (profileAccessPolicyVOList != null) for (ProfileAccessPolicyVO vo : profileAccessPolicyVOList)
			{
				daoobj.save(vo, userVO);
			}

			/*
			 * deptUnit = daoobj.checkDuplicateBeforeSave(profileAccessPolicyVO, userVO); if (deptUnit == null) {
			 * daoobj.save(profileAccessPolicyVO, userVO); } else throw new HisDuplicateRecordException( "Department Unit
			 * Code with Profile Type already exists");
			 */
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public ProfileAccessPolicyVO fetchProfileAccessPolicyModify(ProfileAccessPolicyVO profileAccessPolicyVO, UserVO _UserVO)
	{
		ProfileAccessPolicyVO _profileAccessPolicyVOs = new ProfileAccessPolicyVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ProfileAccessPolicyDAOi daoobj = new ProfileAccessPolicyDAO(tx);
			_profileAccessPolicyVOs = daoobj.fetchProfileAccessPolicyModify(profileAccessPolicyVO, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Appointment BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _profileAccessPolicyVOs;
	}

	public void modifySaveProfileAccessPolicy(ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			ProfileAccessPolicyDAOi daoobj = new ProfileAccessPolicyDAO(tx);
			// deptName
			// =daoobj.checkDuplicateBeforeModify(profileAccessPolicyVO,
			// userVO);

			daoobj.modify(profileAccessPolicyVO, userVO);
			daoobj.modifyInsert(profileAccessPolicyVO, userVO);
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public void saveUnitExtTreatDetail(List unitExtTreatLst, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			UnitExtTreatDAOi daoObj = new UnitExtTreatDAO(tx);

			for (int i = 0; i < unitExtTreatLst.size(); i++)
			{
				UnitExtTreatMstVO vo = (UnitExtTreatMstVO) unitExtTreatLst.get(i);
				daoObj.create(vo, userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}
	
	
	public void saveUnitDrugDetail(List unitDrugVOLst, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			UnitDrugDAOi daoObj = new UnitDrugDAO(tx);

			for (int i = 0; i < unitDrugVOLst.size(); i++)
			{
				UnitDrugMstVO vo = (UnitDrugMstVO) unitDrugVOLst.get(i);
				daoObj.delete(vo, userVO);
				daoObj.create(vo, userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public void modifySaveUnitExtTreat(List unitExtTreatVOLst, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			UnitExtTreatDAOi daoObj = new UnitExtTreatDAO(tx);

			UnitExtTreatMstVO unitExtTreatVo = (UnitExtTreatMstVO) unitExtTreatVOLst.get(0);

			daoObj.updateByDeptUnit(unitExtTreatVo.getDeptUnitCode(), userVO);

			for (int i = 0; i < unitExtTreatVOLst.size(); i++)
			{
				UnitExtTreatMstVO vo = (UnitExtTreatMstVO) unitExtTreatVOLst.get(i);
				daoObj.create(vo, userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}
	
	
	
	
	public void modifySaveUnitDrugDetail(List unitDrugVOLst, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			UnitDrugDAOi daoObj = new UnitDrugDAO(tx);

			UnitDrugMstVO unitDrugVo = (UnitDrugMstVO) unitDrugVOLst.get(0);

			daoObj.updateByDeptUnit(unitDrugVo.getDeptUnitCode(), userVO);

			for (int i = 0; i < unitDrugVOLst.size(); i++)
			{
				UnitDrugMstVO vo = (UnitDrugMstVO) unitDrugVOLst.get(i);
				daoObj.create(vo, userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public void saveUnitMacroDetail(List unitMacroTreatLst, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			UnitMacroMasterDAOi daoObj = new UnitMacroMasterDAO(tx);

			for (int i = 0; i < unitMacroTreatLst.size(); i++)
			{
				UnitWiseMacroMstVO vo = (UnitWiseMacroMstVO) unitMacroTreatLst.get(i);
				daoObj.create(vo, userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public List getProfileType(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List profileTypeList;
		try
		{
			tx.begin();

			ProfileTypeDAO daoObj = new ProfileTypeDAO(tx);
			profileTypeList = daoObj.getAllProfileTypes(userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return profileTypeList;
	}

	public Map getDeskMenuForProfileMapping(String profileType, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List deskMenuIdAddedList;
		List deskMenuIdNotAddedList;
		Map essentialMap = new HashMap();
		try
		{
			tx.begin();

			ProfileTypeTabMappingDAO daoObj = new ProfileTypeTabMappingDAO(tx);
			deskMenuIdAddedList = daoObj.getDeskMenuByProfileType(profileType, userVO);
			essentialMap.put(OpdConfig.DESK_MENU_ADDED_TO_PROFILE_TYPE_LIST, deskMenuIdAddedList);
			deskMenuIdNotAddedList = daoObj.getDeskMenuNotAdded(profileType, userVO);
			essentialMap.put(OpdConfig.DESK_MENU_NOT_ADDED_TO_PROFILE_TYPE_LIST, deskMenuIdNotAddedList);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public void saveProfileTypeTabMapping(ProfileTypeTabMappingVO[] insertProfileTypeTabMappingVO,
			ProfileTypeTabMappingVO[] updateProfileTypeTabMappingVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ProfileTypeTabMappingDAO daoObj = new ProfileTypeTabMappingDAO(tx);
			//for (int i = 0; i < updateProfileTypeTabMappingVO.length; i++)
			{
				daoObj.update(updateProfileTypeTabMappingVO[0], userVO);
			}
			for (int i = 0; i < insertProfileTypeTabMappingVO.length; i++)
			{
				daoObj.create(insertProfileTypeTabMappingVO[i], userVO);
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println("error.... OpdMasterBO BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public void saveProfileType(ProfileTypeMstVO profileTypeMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ProfileTypeDAO daoObj = new ProfileTypeDAO(tx);
			boolean flag = daoObj.checkDuplicateBeforeSave(profileTypeMasterVO, userVO);
			if (flag)
			{
				daoObj.create(profileTypeMasterVO, userVO);
			}
			else throw new HisDuplicateRecordException("Profile Name already exists");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public List<Entry> getDeptUnitMappedWithProfileAccess(ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List departmentUnitList = null;
		try
		{
			tx.begin();

			ProfileAccessPolicyDAOi daoObj = new ProfileAccessPolicyDAO(tx);
			departmentUnitList = daoObj.getDeptUnitByProfileTypeAndPolicyType(profileAccessPolicyVO, userVO);
		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return departmentUnitList;
	}

	public void modifySaveUnitMacro(List unitMacroVOLst, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			UnitMacroMasterDAOi daoObj = new UnitMacroMasterDAO(tx);

			UnitWiseMacroMstVO unitWiseMacroMstVO = (UnitWiseMacroMstVO) unitMacroVOLst.get(0);
			daoObj.Update(unitWiseMacroMstVO.getDeptUnitCode(), userVO);

			for (int i = 0; i < unitMacroVOLst.size(); i++)
			{
				UnitWiseMacroMstVO vo = (UnitWiseMacroMstVO) unitMacroVOLst.get(i);
				daoObj.create(vo, userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public boolean saveKeywordMstDetail(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag = true;
		String existName = "";

		try
		{
			tx.begin();

			EpisodeKeywordsMasterDAOi daoobj = new EpisodeKeywordsMasterDAO(tx);

			existName = daoobj.checkDuplicateName(keywordMstVO, userVO);
			if (existName.equals("0"))
			{
				daoobj.creat(keywordMstVO, userVO);
			}
			else
			{
				hasFlag = false;
				throw new HisDuplicateRecordException("Keyword Already Exists");
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();

		}
		return hasFlag;
	}

	public Map getDataForModifyKeywordMst(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeKeywordsMasterVO vo = new EpisodeKeywordsMasterVO();
		Map modifyMap = new HashMap();

		try
		{
			tx.begin();

			EpisodeKeywordsMasterDAOi daoobj = new EpisodeKeywordsMasterDAO(tx);

			vo = daoobj.getDataForModify(keywordMstVO, userVO);
			modifyMap.put(OpdConfig.KEYWORD_MST_VO_FOR_MODIFY, vo);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return modifyMap;
	}

	public boolean saveModKeywordMaster(EpisodeKeywordsMasterVO keywordMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		String existName = "";

		try
		{
			tx.begin();

			EpisodeKeywordsMasterDAOi daoobj = new EpisodeKeywordsMasterDAO(tx);

			existName = daoobj.checkDuplicateNameForModify(keywordMstVO, _UserVO);

			if (existName.equals("0"))
			{
				daoobj.updateKeywordMaster(keywordMstVO, _UserVO);
				daoobj.saveModEnclosureMaster(keywordMstVO, _UserVO);
				flag = true;
			}
			else
			{
				throw new HisDuplicateRecordException("Keyword Already Exists");
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;

	}

	public boolean saveDrugListMstDetail(DrugListMasterVO drugListMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag = true;
		String existName = "";

		try
		{
			tx.begin();

			DrugListMasterDAOi daoobj = new DrugListMasterDAO(tx);

			existName = daoobj.checkDuplicateName(drugListMasterVO, userVO);
			if (existName.equals("0"))
			{
				daoobj.creat(drugListMasterVO, userVO);
			}
			else
			{
				hasFlag = false;
				throw new HisDuplicateRecordException("Drug List Name Already Exists");
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();

		}
		return hasFlag;
	}

	public Map getDataForModifyDrugListMst(DrugListMasterVO drugListMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DrugListMasterVO vo = new DrugListMasterVO();
		Map modifyMap = new HashMap();

		try
		{
			tx.begin();

			DrugListMasterDAOi daoobj = new DrugListMasterDAO(tx);

			vo = daoobj.getDataForModify(drugListMasterVO, userVO);
			modifyMap.put(OpdConfig.KEYWORD_MST_VO_FOR_MODIFY, vo);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return modifyMap;
	}

	public boolean saveModDrugListMstDetail(DrugListMasterVO drugListMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		String existName = "";

		try
		{
			tx.begin();

			DrugListMasterDAOi daoobj = new DrugListMasterDAO(tx);

			existName = daoobj.checkDuplicateNameForModify(drugListMasterVO, userVO);

			if (existName.equals("0"))
			{
				daoobj.updateDrugListMaster(drugListMasterVO, userVO);
				daoobj.saveModDrugListMaster(drugListMasterVO, userVO);
				flag = true;
			}
			else
			{
				throw new HisDuplicateRecordException("Drug List Name Already Exists");
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;

	}

	public Map getDrugListItemMasterEssential(PatientDetailVO _patDetailVO ,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		List allDrugList = null;
		List lstDrugDosesVO = null;
		List lstFreq = null;
		List drugListNameList = null;
		HisDAO hisDAO = new HisDAO("OPD", "OpdMasterBO");
		try
		{
			tx.begin();

			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			DrugListMasterDAOi drugListDao = new DrugListMasterDAO(tx);

			drugListNameList = drugListDao.getDrugListNameList(userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUGLIST_NAME_LIST, drugListNameList);
			// Commented By Chetan Sharma
			// Reason: SCM integration
			// Date : 10_12_2015
			PatientDetailVO patVO = new PatientDetailVO();
			
			//allDrugList = dao.getDrugList(userVO);
			allDrugList = dao.getDrugListDetail(hisDAO , "1", patVO, userVO);

			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS, allDrugList);

			lstDrugDosesVO = dao.getDrugDosesList(userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES, lstDrugDosesVO);

			lstFreq = dao.getDosageFrequecy(userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY, lstFreq);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			if(hisDAO!=null){
				hisDAO.free();
			}
			hisDAO=null;
			tx.close();
		}
		return essentialMap;
	}

	public boolean saveDrugListItemMstDetail(List drugListItemMstVOList, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean hasFlag = true;

		try
		{
			tx.begin();

			DrugListItemMasterDAOi daoobj = new DrugListItemMasterDAO(tx);

			for (int i = 0; i < drugListItemMstVOList.size(); i++)
			{
				DrugListItemMstVO vo = (DrugListItemMstVO) drugListItemMstVOList.get(i);
				daoobj.creat(vo, userVO);
			}

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();

		}
		return hasFlag;
	}

	public Map getDataForModifyDrugListItemMst(DrugListItemMstVO drugListItemMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		Map essentialMap = new HashMap();
		List drugListNameList = null;
		List allDrugList = null;
		List lstDrugDosesVO = null;
		List lstFreq = null;
		List selectedDrugList = null;
		HisDAO hisDAO = new HisDAO("OPD", "OpdMasterBO");

		try
		{
			tx.begin();

			DrugListItemMasterDAOi daoobj = new DrugListItemMasterDAO(tx);
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			DrugListMasterDAOi drugListDao = new DrugListMasterDAO(tx);

			selectedDrugList = daoobj.getDrugDeatilByDrugList(drugListItemMstVO, userVO);
			essentialMap.put(OpdConfig.SELECTED_DRUG_LIST, selectedDrugList);

		//	drugListNameList = drugListDao.getDrugListNameList(userVO);
		//	essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUGLIST_NAME_LIST, drugListNameList);
			// Commented By Chetan Sharma
						// Reason: SCM integration
						// Date : 10_12_2015
						PatientDetailVO patVO = new PatientDetailVO();
						
						//allDrugList = dao.getDrugList(userVO);
						allDrugList = dao.getDrugListDetail(hisDAO , "1", patVO, userVO);

						essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS, allDrugList);



			lstDrugDosesVO = dao.getDrugDosesList(userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES, lstDrugDosesVO);

			lstFreq = dao.getDosageFrequecy(userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY, lstFreq);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			if(hisDAO!=null){
				hisDAO.free();
			}
			hisDAO=null;
			tx.close();
		}
		return essentialMap;
	}

	public void saveModifyDrugListItemMstDetail(List selectedDrugList, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			DrugListItemMasterDAOi daoobj = new DrugListItemMasterDAO(tx);

			DrugListItemMstVO drugListItemMstVo = (DrugListItemMstVO) selectedDrugList.get(0);

			daoobj.updateDrugListItemMaster(drugListItemMstVo, userVO);

			for (int i = 0; i < selectedDrugList.size(); i++)
			{
				DrugListItemMstVO vo = (DrugListItemMstVO) selectedDrugList.get(i);
				daoobj.creat(vo, userVO);
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public void saveUnitDrugList(List unitDrugListVOLst, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			UnitDrugListDAOi daoObj = new UnitDrugListDAO(tx);

			for (int i = 0; i < unitDrugListVOLst.size(); i++)
			{
				UnitDrugListMasterVO vo = (UnitDrugListMasterVO) unitDrugListVOLst.get(i);
				daoObj.create(vo, userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public void modifySaveDrugList(List unitDrugListVOLst, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			UnitDrugListDAOi daoObj = new UnitDrugListDAO(tx);

			UnitDrugListMasterVO unitDrugListMasterVO = (UnitDrugListMasterVO) unitDrugListVOLst.get(0);
			daoObj.update(unitDrugListMasterVO.getDeptUnitCode(), userVO);

			for (int i = 0; i < unitDrugListVOLst.size(); i++)
			{
				UnitDrugListMasterVO vo = (UnitDrugListMasterVO) unitDrugListVOLst.get(i);
				daoObj.create(vo, userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	// Saving Addendant Reason Record
	public void saveAttendantReason(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			AttendantReasonMasterDAOi daoObj = new AttendantReasonMasterDAO(tx);
			if (!daoObj.checkDuplicate(_attReasonVO, _userVO)) daoObj.create(_attReasonVO, _userVO);
			else throw new HisRecordNotFoundException("Attendant Reason Already Exists");
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Getting Addendant Reason Record
	public AttendantReasonMasterVO getAttendantReason(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		AttendantReasonMasterVO attReasonVO = new AttendantReasonMasterVO();
		try
		{
			tx.begin();
			AttendantReasonMasterDAOi daoObj = new AttendantReasonMasterDAO(tx);
			attReasonVO = daoObj.get(_attReasonVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return attReasonVO;
	}

	// Modifying Addendant Reason Record
	public void updateAttendantReason(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			AttendantReasonMasterDAOi daoObj = new AttendantReasonMasterDAO(tx);
			if (!daoObj.checkDuplicateforModify(_attReasonVO, _userVO))
			{
				daoObj.update(_attReasonVO, _userVO);
				daoObj.createUpdate(_attReasonVO, _userVO);
			}
			else throw new HisRecordNotFoundException("Attendant Reason Already Exists");
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Saving Keyword Unit Record Unit-Wise
	public void saveUnitEpisodeKeywords(List<UnitEpisodeKeywordVO> _lstUnitKeywords, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			UnitEpisodeKeywordMasterDAOi daoUnitKeyword = new UnitEpisodeKeywordMasterDAO(tx);
			for (UnitEpisodeKeywordVO vo : _lstUnitKeywords)
				daoUnitKeyword.create(vo, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Modifying Unit Episode Keywords
	public void modifyUnitEpisodeKeywords(List<UnitEpisodeKeywordVO> _lstUnitKeywords, String _deptUnitCode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			UnitEpisodeKeywordMasterDAOi daoobj = new UnitEpisodeKeywordMasterDAO(tx);
			daoobj.deleteEpiKeywordsUnitWise(_deptUnitCode, _userVO);
			for (UnitEpisodeKeywordVO vo : _lstUnitKeywords)
				daoobj.create(vo, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Saving ICD Include Exclude Record
	public void saveICDIncludeExclude(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			IcdDiseaseMasterDAOi daoObj = new IcdDiseaseMasterDAO(tx);
			if (!daoObj.checkIncludeExcludeDuplicate(_icdDiseaseVO, _userVO)) daoObj.createIncludeExclude(_icdDiseaseVO, _userVO);
			else throw new HisRecordNotFoundException("ICD Include Exclude or Synonym Already Exists for Selected Disease");
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Getting ICD Include Exclude Record
	public IcdDiseaseMasterVO getICDIncludeExcludeRecord(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		IcdDiseaseMasterVO icdDiseaseVO = new IcdDiseaseMasterVO();
		try
		{
			tx.begin();
			IcdDiseaseMasterDAOi daoObj = new IcdDiseaseMasterDAO(tx);
			icdDiseaseVO = daoObj.getIncludeExclude(_icdDiseaseVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return icdDiseaseVO;
	}

	// Modifying ICD Include Exclude Record
	public void updateICDIncludeExcludeRecord(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			IcdDiseaseMasterDAOi daoObj = new IcdDiseaseMasterDAO(tx);
			if (!daoObj.checkDuplicateforIncludeExcludeModify(_icdDiseaseVO, _userVO))
			{
				daoObj.update(_icdDiseaseVO, _userVO);
				daoObj.createIncludeExclude(_icdDiseaseVO, _userVO);
			}
			else throw new HisRecordNotFoundException("ICD Include Exclude or Synonym Already Exists for Selected Disease");
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public ProfileTypeMstVO getModifyDetail(ProfileTypeMstVO profileTypeMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ProfileTypeDAO daoObj = new ProfileTypeDAO(tx);

			profileTypeMstVO = daoObj.fetchRecord(profileTypeMstVO, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return profileTypeMstVO;
	}

	public void modifySaveProfileType(ProfileTypeMstVO profileTypeMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		try
		{
			tx.begin();
			ProfileTypeDAO daoobj = new ProfileTypeDAO(tx);

			flag = daoobj.checkDuplicateBeforeModifySave(profileTypeMstVO, userVO);

			if (flag)
			{
				daoobj.modify(profileTypeMstVO, userVO);
				daoobj.modifyInsert(profileTypeMstVO, userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Profile Name Already Exist");
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public Map getPatientAlertEssentials(UserVO _userVO)
	{
		List diseaseList = null;
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			// PatientAlertMasterDAO patientAlertMasterDAO = new PatientAlertMasterDAO(tx);

			diseaseList = opdEssentialDAO.getIcdDiseaseList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_HOSDIS_DISEASE, diseaseList);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;

	}

	public PatientAlertMasterVO fetchPatientAlertModify(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			PatientAlertMasterDAOi daoObj = new PatientAlertMasterDAO(tx);

			_patientAlertMasterVO = daoObj.fetchPatientAlertModify(_patientAlertMasterVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _patientAlertMasterVO;
	}

	public void savePatientAlert(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientAlertMasterDAOi daoObj = new PatientAlertMasterDAO(tx);
			boolean flag = daoObj.checkDuplicateBeforeSave(_patientAlertMasterVO, _userVO);
			if (flag)
			{
				daoObj.create(_patientAlertMasterVO, _userVO);
			}
			else throw new HisDuplicateRecordException("Disease Name already exists");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public void modifySavePatientAlert(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		try
		{
			tx.begin();
			PatientAlertMasterDAOi daoobj = new PatientAlertMasterDAO(tx);

			flag = daoobj.checkDuplicateBeforeModifySave(_patientAlertMasterVO, _userVO);

			if (flag)
			{
				daoobj.modify(_patientAlertMasterVO, _userVO);
				daoobj.modifyInsert(_patientAlertMasterVO, _userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Disease Name Already Exist");
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public DrugDoseMstVO getDrugItemName(String itemID, UserVO _UserVO)
	{
		DrugDoseMstVO _drugDoseMstVO = new DrugDoseMstVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDetailVO patVO = new PatientDetailVO();
		List itemTypeList = null;
		Map essentialMap = new HashMap();
		HisDAO hisDAO = new HisDAO("OPD", "OpdEssentialBO");
		try
		{
			tx.begin();
			DrugDoseMasterDAO daoobj = new DrugDoseMasterDAO(tx);
			_drugDoseMstVO = daoobj.itemName(itemID, _UserVO);
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			
			itemTypeList = opdEssDAO.getItemTypeList(hisDAO , OpdConfig.lstItemTypes, patVO, _UserVO);
			
						
			//essentialMap.put(OpdConfig.ESSENTIALBO_ITEM_TYPE_LIST, itemTypeList);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE, itemTypeList);	
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			if(hisDAO!=null){
				hisDAO.free();
			}
			hisDAO=null;
			tx.close();
		}
		return _drugDoseMstVO;
	}

	// Getting Chart Parameter Essentials
	public Map<String, Object> getChartParameterEssentials(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssential = new HashMap();
		List chartList = new ArrayList();
		List<Entry> lstParams = new ArrayList();

		try
		{
			tx.begin();
			ChartParameterMappingDAOi chartParaDAO = new ChartParameterMappingDAO(tx);
			chartList = chartParaDAO.getAllChart(_userVO);
			mpEssential.put(OpdConfig.CHART_NAME_LIST, chartList);

			// Clinical Paramters
			lstParams = chartParaDAO.getParametersByParaType(OpdConfig.CHART_PARAMETER_TYPE_CLINICAL, _userVO);
			mpEssential.put(OpdConfig.ALL_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL, lstParams);

			// Investigation Parameters
			lstParams = chartParaDAO.getParametersByParaType(OpdConfig.CHART_PARAMETER_TYPE_INVESTIGATION, _userVO);
			mpEssential.put(OpdConfig.ALL_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION, lstParams);

			// Intake Oupput Parameters
			lstParams = chartParaDAO.getParametersByParaType(OpdConfig.CHART_PARAMETER_TYPE_INTAKE_OUTPUT, _userVO);
			mpEssential.put(OpdConfig.ALL_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT, lstParams);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mpEssential;
	}

	// Getting parameter for Chart Parameter Mapping
	public Map getParameterForChart(String _chartId, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<ChartParameterMappingVO> lstParaAdded = null;
		List<ChartParameterMappingVO> lstParaClinical = new ArrayList<ChartParameterMappingVO>();
		List<ChartParameterMappingVO> lstParaInv = new ArrayList<ChartParameterMappingVO>();
		List<ChartParameterMappingVO> lstParaInOut = new ArrayList<ChartParameterMappingVO>();
		Map paraMap = new HashMap();
		try
		{
			tx.begin();
			ChartMasterDAOi chartMstDAO = new ChartMasterDAO(tx);
			lstParaAdded = chartMstDAO.getChartTempParas(_chartId, _userVO);

			for (ChartParameterMappingVO vo : lstParaAdded)
			{
				if (vo.getParaType().equalsIgnoreCase(OpdConfig.CHART_PARAMETER_TYPE_CLINICAL)) lstParaClinical.add(vo);
				else if (vo.getParaType().equalsIgnoreCase(OpdConfig.CHART_PARAMETER_TYPE_INVESTIGATION)) lstParaInv.add(vo);
				else if (vo.getParaType().equalsIgnoreCase(OpdConfig.CHART_PARAMETER_TYPE_INTAKE_OUTPUT)) lstParaInOut.add(vo);
			}

			paraMap.put(OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL, lstParaClinical);
			paraMap.put(OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION, lstParaInv);
			paraMap.put(OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT, lstParaInOut);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return paraMap;
	}

	// Saving Chart Parameter Details
	public void saveChartParameterMapping(String _chartId, List<ChartParameterMappingVO> _insertList, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ChartParameterMappingDAOi daoObj = new ChartParameterMappingDAO(tx);
			daoObj.update(_chartId, _userVO);
			for (ChartParameterMappingVO vo : _insertList)
				daoObj.create(vo, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println("error.... MasterBO BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	// Saving Disease Site Detail
	public void saveDiseaseSite(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DiseaseSiteDAOi daoObj = new DiseaseSiteDAO(tx);
			boolean flag = daoObj.checkDuplicate(_diseaseSiteVO, _userVO);
			if (!flag) daoObj.create(_diseaseSiteVO, _userVO);
			else throw new HisDuplicateRecordException("Duplicate Record Exists");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Fetching Disease Site Detail
	public DiseaseSiteMasterVO fetchDiseaseSiteDtl(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DiseaseSiteMasterVO diseaseSiteVO = null;
		try
		{
			tx.begin();
			DiseaseSiteDAOi daoObj = new DiseaseSiteDAO(tx);
			diseaseSiteVO = daoObj.get(_diseaseSiteVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return diseaseSiteVO;
	}

	// Modifying Disease Site Detail
	public void modifyDiseaseSite(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DiseaseSiteDAOi daoObj = new DiseaseSiteDAO(tx);
			boolean flag = daoObj.checkDuplicateforModify(_diseaseSiteVO, _userVO);
			if (!flag)
			{
				daoObj.update(_diseaseSiteVO, _userVO);
			}
			else throw new HisDuplicateRecordException("Duplicate Record Exists");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void saveChart(ChartMasterVO _chartMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ChartMasterDAOi daoObj = new ChartMasterDAO(tx);
			boolean flag = daoObj.checkDuplicate(_chartMasterVO, _userVO);
			if (flag)
			{
				daoObj.create(_chartMasterVO, _userVO);
			}
			else throw new HisDuplicateRecordException("Chart Name already exists");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public ChartMasterVO getModifyDetail(ChartMasterVO _chartMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			ChartMasterDAOi daoObj = new ChartMasterDAO(tx);
			_chartMasterVO = daoObj.fetchRecord(_chartMasterVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _chartMasterVO;
	}

	public void modifySaveChart(ChartMasterVO _chartMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		try
		{
			tx.begin();
			ChartMasterDAOi daoObj = new ChartMasterDAO(tx);

			flag = daoObj.checkDuplicateBeforeModifySave(_chartMasterVO, _userVO);

			if (flag)
			{
				daoObj.modify(_chartMasterVO, _userVO);
				daoObj.modifyInsert(_chartMasterVO, _userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Chart Name Already Exist");
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void saveChartUnitList(List _chartUnitMapppingVOList, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			ChartUnitMappingMstDAOi daoObj = new ChartUnitMappingMstDAO(tx);

			for (int i = 0; i < _chartUnitMapppingVOList.size(); i++)
			{
				ChartUnitMapppingVO vo = (ChartUnitMapppingVO) _chartUnitMapppingVOList.get(i);
				daoObj.create(vo, _userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public void modifySaveChartList(List _chartUnitMapppingVOLst, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			ChartUnitMappingMstDAOi daoObj = new ChartUnitMappingMstDAO(tx);

			ChartUnitMapppingVO unitChartListMasterVO = (ChartUnitMapppingVO) _chartUnitMapppingVOLst.get(0);
			daoObj.update(unitChartListMasterVO.getDeptUnitCode(), _userVO);

			for (int i = 0; i < _chartUnitMapppingVOLst.size(); i++)
			{
				ChartUnitMapppingVO vo = (ChartUnitMapppingVO) _chartUnitMapppingVOLst.get(i);
				daoObj.create(vo, _userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println("error.... ChartUnitMappingBO BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public void saveDeptUnitIcdMapping(List _departmentIcdMasterVOLst, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			DepartmentUnitIcdMappingDAO daoObj = new DepartmentUnitIcdMappingDAO(tx);

			for (int i = 0; i < _departmentIcdMasterVOLst.size(); i++)
			{
				DepartmentIcdMasterVO vo = (DepartmentIcdMasterVO) _departmentIcdMasterVOLst.get(i);
				daoObj.create(vo, _userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	// Fetching Department Unit Icd Detail
	public Map getDeptUnitIcdForModify(DepartmentIcdMasterVO _vo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();

		List listAllUnit = null;
		DepartmentIcdMasterVO[] deptUnitIcdVO = null;
		List<IcdGroupMasterVO> lstICDGroup = new ArrayList<IcdGroupMasterVO>();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			DepartmentUnitIcdMappingDAO daoObj = new DepartmentUnitIcdMappingDAO(tx);

			listAllUnit = opdEssentialDAO.getAllUnit(_userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);

			lstICDGroup = opdEssentialDAO.getAllUsedICDGroups(_userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP, lstICDGroup);

			deptUnitIcdVO = daoObj.getDeptUnitIcdForModify(_vo.getDepartmentUnitCode(), _userVO);
			essentialMap.put(OpdConfig.MAPPED_UNIT_ICD_DISEASE_LIST_VO_ARRAY, deptUnitIcdVO);
			// for getting Not Mapped Disease
			// for(int i=0;i<deptUnitIcdVO.length;i++)
			// {notMappedDisease = daoObj.getNotMappedDisease(.,_userVO);
			// essentialMap.put(OpdConfig.DISEASE_LIST_NOT_MAPPED, notMappedDisease);}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	// Save Modified Department Unit Icd Details
	public void modifySaveDeptIcdMapping(List _departmentIcdMasterVOLst, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			DepartmentUnitIcdMappingDAO daoObj = new DepartmentUnitIcdMappingDAO(tx);
			DepartmentIcdMasterVO deptUnitIcdVO = (DepartmentIcdMasterVO) _departmentIcdMasterVOLst.get(0);
			daoObj.update(deptUnitIcdVO.getDepartmentUnitCode(), _userVO);

			for (int i = 0; i < _departmentIcdMasterVOLst.size(); i++)
			{
				DepartmentIcdMasterVO vo = (DepartmentIcdMasterVO) _departmentIcdMasterVOLst.get(i);
				daoObj.create(vo, _userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println("error.... ChartUnitMappingBO BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public Map getModDisease(String _deptCode, String _icdSubgroupCode, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();

		List<IcdDiseaseMasterVO> lstDisease = new ArrayList<IcdDiseaseMasterVO>();

		try
		{
			tx.begin();
			DepartmentUnitIcdMappingDAO daoObj = new DepartmentUnitIcdMappingDAO(tx);
			// for getting Not Mapped Disease
			lstDisease = daoObj.getNotMappedDisease(_deptCode, _icdSubgroupCode, _userVO);
			essentialMap.put(OpdConfig.DISEASE_LIST_NOT_MAPPED, lstDisease);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public void saveDeptUnitHosDisease(List _departmentHosDisVOLst, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			DepartmentUnitHosDiseaseMstDAOi daoObj = new DepartmentUnitHosDiseaseMstDAO(tx);

			for (int i = 0; i < _departmentHosDisVOLst.size(); i++)
			{
				DepartmentHosDiseaseMstVO vo = (DepartmentHosDiseaseMstVO) _departmentHosDisVOLst.get(i);
				daoObj.create(vo, _userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public Map getUnitWiseMappedHosDiseasForModify(DepartmentHosDiseaseMstVO _vo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();

		List listAllUnit = null;
		DepartmentHosDiseaseMstVO[] deptUnitHosDiseaseVO = null;
		List hospitalDiseaseLst = null;
		// List lstDisease=null;

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			DepartmentUnitHosDiseaseMstDAOi daoObj = new DepartmentUnitHosDiseaseMstDAO(tx);

			listAllUnit = opdEssentialDAO.getAllUnit(_userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);

			deptUnitHosDiseaseVO = daoObj.getDeptUnitHosDiseaseForModify(_vo.getDepartmentUnitCode(), _userVO);
			essentialMap.put(OpdConfig.LIST_OF_MAPPED_UNIT_HOSPITAL_DISEASE_VO_ARRAY, deptUnitHosDiseaseVO);

			hospitalDiseaseLst = opdEssentialDAO.getHospitalDisease(_userVO);
			essentialMap.put(OpdConfig.ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE, hospitalDiseaseLst);
			// notMappedDisease = daoObj.getNotMappedHosDisease(_vo.getDepartmentUnitCode(),_userVO);
			// es//sentialMap.put(OpdConfig.LIST_NOT_MAPPED_HOSPITAL_DISEASE, lstDisease);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public void modifySaveDeptUnitHosDiseaseMapping(List _departmentHosDiseaseMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			DepartmentUnitHosDiseaseMstDAOi daoObj = new DepartmentUnitHosDiseaseMstDAO(tx);

			DepartmentHosDiseaseMstVO deptUnitHosDiseaseMstVO = (DepartmentHosDiseaseMstVO) _departmentHosDiseaseMstVO.get(0);
			daoObj.update(deptUnitHosDiseaseMstVO.getDepartmentUnitCode(), _userVO);

			for (int i = 0; i < _departmentHosDiseaseMstVO.size(); i++)
			{
				DepartmentHosDiseaseMstVO vo = (DepartmentHosDiseaseMstVO) _departmentHosDiseaseMstVO.get(i);
				daoObj.create(vo, _userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println("error.... ChartUnitMappingBO BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	// save ICD mapping master

	public void saveIcdMapping(List listIcdMasterVO_p, UserVO userVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			OpdIcdMappingDAOi daoObj = new OpdIcdMappingDAO(tx);

			for (int i = 0; i < listIcdMasterVO_p.size(); i++)
			{
				IcdMappingMasterVO vo = (IcdMappingMasterVO) listIcdMasterVO_p.get(i);
				daoObj.create(vo, userVO_p);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	// modify icd mapping
	public Map getIcdMappingForModify(IcdMappingMasterVO vo_p, UserVO userVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mapEessential = new HashMap();

		IcdMappingMasterVO[] icdMappingMasterVO = null;
		List<IcdGroupMasterVO> listICDGroup = new ArrayList<IcdGroupMasterVO>();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			OpdIcdMappingDAOi daoObj = new OpdIcdMappingDAO(tx);

			listICDGroup = opdEssentialDAO.getAllUsedICDGroups(userVO_p);
			mapEessential.put(OpdConfig.OPD_ESSENTIAL_LIST_ICD_GROUP, listICDGroup);

			icdMappingMasterVO = daoObj.getIcdMappingForModify(vo_p, userVO_p);
			mapEessential.put(OpdConfig.MAPPED_ICD_DISEASE_LIST_VO_ARRAY, icdMappingMasterVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mapEessential;
	}

	// to get modified icd disease
	public Map getModIcdDisease(String strMappingType_p, String strMappingId_p, String strIcdSubGroupCode_p, UserVO userVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mapEssential = new HashMap();

		List<IcdDiseaseMasterVO> listDisease = new ArrayList<IcdDiseaseMasterVO>();

		try
		{
			tx.begin();
			OpdIcdMappingDAOi daoObj = new OpdIcdMappingDAO(tx);
			// for getting Not Mapped Disease
			listDisease = daoObj.getNotMappedIcdDisease(strMappingType_p, strMappingId_p, strIcdSubGroupCode_p, userVO_p);
			mapEssential.put(OpdConfig.ICD_DISEASE_LIST_NOT_MAPPED, listDisease);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mapEssential;
	}

	// to save modified icd mapping
	public void modifySaveIcdMapping(List listIcdMappingMasterVO_p, UserVO userVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			OpdIcdMappingDAOi daoObj = new OpdIcdMappingDAO(tx);
			IcdMappingMasterVO icdMappingMasterVO = (IcdMappingMasterVO) listIcdMappingMasterVO_p.get(0);
			daoObj.update(icdMappingMasterVO.getMappingID(), icdMappingMasterVO.getMappingType(), userVO_p);

			for (int i = 0; i < listIcdMappingMasterVO_p.size(); i++)
			{
				IcdMappingMasterVO vo = (IcdMappingMasterVO) listIcdMappingMasterVO_p.get(i);
				daoObj.create(vo, userVO_p);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println("error.... ChartUnitMappingBO BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// Initial Add Page for Icd Index Level Master
	public Map<String, Object> getInitializeAdd(UserVO _userVO)
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();

		List<Entry> indexTermList = null;
		List<IcdGroupMasterVO> lstIcdGroups = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			IcdIndexLevelMasterDAOi icdDao = new IcdIndexLevelMasterDAO(tx);
			indexTermList = icdDao.getIndexTermCombo(_userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ICD_INDEX_TERM, indexTermList);

			OpdEssentialDAOi opdEssDAO = new OpdEssentialDAO(tx);
			lstIcdGroups = opdEssDAO.getAllUsedICDGroups(_userVO);
			essentialMap.put(OpdConfig.OPD_LIST_ICD_GROUP, lstIcdGroups);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	/*
	 * Populating the Parent Modifier Combo
	 */
	public List<Entry> getParentModifier(IcdIndexLevelMasterVO icdIndexLevelMasterVO, UserVO _userVO)
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();

		List<Entry> parentModifierList = new ArrayList<Entry>();

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			IcdIndexLevelMasterDAOi icdDao = new IcdIndexLevelMasterDAO(tx);

			parentModifierList = icdDao.getParentModifierCombo(icdIndexLevelMasterVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return parentModifierList;
	}

	/*
	 * Populating the Icd Sub Group Combo
	 */
	public List<IcdSubgroupMasterVO> getIcdSubGroupByGroupCode(String _icdGroupCode, UserVO _userVO)
	{

		List<IcdSubgroupMasterVO> lstIcdSubGroups = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			IcdIndexLevelMasterDAOi icdDao = new IcdIndexLevelMasterDAO(tx);
			lstIcdSubGroups = icdDao.getSubgroupsByGroup(_icdGroupCode, _userVO);
			if (lstIcdSubGroups == null) lstIcdSubGroups = new ArrayList<IcdSubgroupMasterVO>();
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstIcdSubGroups;
	}

	// Get Disease SubGroup Wise
	public List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String _icdSubgroupCode, UserVO _userVO)
	{
		List<IcdDiseaseMasterVO> lstICDDisease = new ArrayList<IcdDiseaseMasterVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		IcdIndexLevelMasterDAOi icdDao = null;
		try
		{
			tx.begin();

			icdDao = new IcdIndexLevelMasterDAO(tx);

			lstICDDisease = icdDao.getDiseaseBySubGroup(_icdSubgroupCode, _userVO);
			if (lstICDDisease == null) lstICDDisease = new ArrayList<IcdDiseaseMasterVO>();
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			throw new HisException();
		}
		catch (Exception e)
		{

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstICDDisease;
	}

	/*
	 * To save Data on Add Page of IcdIndexLevelMaster
	 */
	public boolean saveIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();

		boolean flag = false;

		try
		{
			tx.begin();

			IcdIndexLevelMasterDAOi icdIndexLevelMasterDAO = new IcdIndexLevelMasterDAO(tx);

			flag = icdIndexLevelMasterDAO.chkDuplicate(vo, userVO, "insert");
			if (flag) // no duplicacy , so new record is added
			{
				icdIndexLevelMasterDAO.saveIcdIndexLevelMaster(vo, userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	}

	/*
	 * To modify IcdIndexLevelMaster
	 */
	public void modifyRecordIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		IcdIndexLevelMasterDAOi icdIndexLevelMasterDAO = null;
		try
		{
			tx.begin();
			icdIndexLevelMasterDAO = new IcdIndexLevelMasterDAO(tx);

			icdIndexLevelMasterDAO.getModifyRecord(vo, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			throw new HisException();
		}
		catch (Exception e)
		{

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	/*
	 * To ModifySave a Record for IcdIndexLevelMaster
	 */
	public boolean modifySaveIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		IcdIndexLevelMasterDAOi icdIndexLevelMasterDAO;
		boolean flag = false;

		try
		{
			tx.begin();

			icdIndexLevelMasterDAO = new IcdIndexLevelMasterDAO(tx);

			flag = icdIndexLevelMasterDAO.chkDuplicate(vo, userVO, "update");
			if (flag)
			{
				icdIndexLevelMasterDAO.modifyUpdate(vo, userVO);
				icdIndexLevelMasterDAO.modifySave(vo, userVO);
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	}

	// To View Page for IcdIndexLevelMaster
	public void getViewRecordIcdIndexLevelMaster(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		IcdIndexLevelMasterDAOi icdIndexLevelMasterDAO = null;
		try
		{
			tx.begin();
			icdIndexLevelMasterDAO = new IcdIndexLevelMasterDAO(tx);

			icdIndexLevelMasterDAO.getViewRecord(vo, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			throw new HisException();
		}
		catch (Exception e)
		{

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	// populating Group Combo Values

	public Map<String, Object> getInitializeGroup(UserVO strUserVO_p)
	{
		Map<String, Object> mapEssentialGroup = new HashMap<String, Object>();
		List<IcdGroupMasterVO> lstIcdIndexGroup = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssDAO = new OpdEssentialDAO(tx);
			lstIcdIndexGroup = opdEssDAO.getAllUsedICDGroups(strUserVO_p);
			mapEssentialGroup.put(OpdConfig.OPD_LIST_ICD_GROUP, lstIcdIndexGroup);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), mapEssentialGroup);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mapEssentialGroup;
	}

	/*
	 * To save Record for Icd Index Master
	 */
	public boolean saveIcdIndexMaster(IcdIndexMasterVO vo, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();

		boolean flag = false;

		try
		{
			tx.begin();

			IIcdIndexMasterDAOi icdIndexMasterDAO = new IcdIndexMasterDAO(tx);

			flag = icdIndexMasterDAO.chkDuplicateIcd(vo, userVO, "insert");
			if (flag) // no duplicacy , so new record is added
			{
				icdIndexMasterDAO.saveIcdIndexMaster(vo, userVO);
			}

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	}

	/*
	 * To modify IcdIndexMaster
	 */
	public void modifyRecordIcdIndexMaster(IcdIndexMasterVO vo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		IIcdIndexMasterDAOi icdIndexMasterDAO = null;
		try
		{
			tx.begin();
			icdIndexMasterDAO = new IcdIndexMasterDAO(tx);

			icdIndexMasterDAO.getModifyRecord(vo, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			throw new HisException();
		}
		catch (Exception e)
		{

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	/*
	 * To ModifySave a Record for IcdIndexMaster
	 */
	public boolean modifySaveIcdIndexMaster(IcdIndexMasterVO vo, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		IIcdIndexMasterDAOi icdIndexMasterDAO;
		boolean flag = false;

		try
		{
			tx.begin();

			icdIndexMasterDAO = new IcdIndexMasterDAO(tx);

			flag = icdIndexMasterDAO.chkDuplicateIcd(vo, userVO, "update");
			if (flag)
			{
				icdIndexMasterDAO.modifyUpdate(vo, userVO);
				icdIndexMasterDAO.modifySave(vo, userVO);
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	}

	// To Initialize index term for Icd Cross Ref Master

	public Map<String, Object> getInitializeIndexTerm(UserVO _userVO)
	{
		Map<String, Object> mapEssentialIndex = new HashMap<String, Object>();

		List<Entry> listIndexCombo = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			IIcdCrossRefMasterDAOi icdDao = new IcdCrossRefMasterDAO(tx);
			listIndexCombo = icdDao.getIndexTermCombo(_userVO);
			mapEssentialIndex.put(OpdConfig.OPD_ESSENTIAL_LIST_ICD_INDEX_TERM, listIndexCombo);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), mapEssentialIndex);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mapEssentialIndex;
	}

	// To Populate Modifier level

	public List<Entry> getModifier(String strIndex, UserVO _userVO)
	{
		List<Entry> listModifierCombo = new ArrayList<Entry>();
		// List<Entry> listSeeValues = new ArrayList<Entry>();

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			IIcdCrossRefMasterDAOi icdDao = new IcdCrossRefMasterDAO(tx);
			listModifierCombo = icdDao.getModifier(strIndex, _userVO);
			// mapEssentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_TERM, listModifierCombo);

			// listSeeValues = icdDao.getSeeValues(strIndex,_userVO);
			// mapEssentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_SEE_TERMS,listSeeValues);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			throw new HisException();
		}
		catch (Exception e)
		{

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return listModifierCombo;
	}

	public List<IcdIndexLevelMasterVO> getSeeTerms(String strIndex, UserVO _userVO)
	{
		List<IcdIndexLevelMasterVO> listSeeTerms = new ArrayList<IcdIndexLevelMasterVO>();
		// List<Entry> listSeeValues = new ArrayList<Entry>();

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			IIcdCrossRefMasterDAOi icdDao = new IcdCrossRefMasterDAO(tx);
			listSeeTerms = icdDao.getSeeTerms(strIndex, _userVO);
			// mapEssentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_TERM, listModifierCombo);

			// listSeeValues = icdDao.getSeeValues(strIndex,_userVO);
			// mapEssentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_SEE_TERMS,listSeeValues);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			throw new HisException();
		}
		catch (Exception e)
		{

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return listSeeTerms;
	}

	// To Populate Modifier next level
	public List<Entry> getInitializeSubModifierNext(String strModifierID, String level, UserVO _userVO)
	{
		List<Entry> listModifierCombo = new ArrayList<Entry>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			IIcdCrossRefMasterDAOi icdDao = new IcdCrossRefMasterDAO(tx);
			listModifierCombo = icdDao.getInitializeSubModifierNext(strModifierID, level, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			throw new HisException();
		}
		catch (Exception e)
		{

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return listModifierCombo;
	}

	public List<IcdIndexLevelMasterVO> getSeeTermsForModi(String strModId, UserVO _userVO)
	{
		List<IcdIndexLevelMasterVO> listSeeTerms = new ArrayList<IcdIndexLevelMasterVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			IIcdCrossRefMasterDAOi icdDao = new IcdCrossRefMasterDAO(tx);
			listSeeTerms = icdDao.getSeeTermsForModi(strModId, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			throw new HisException();
		}
		catch (Exception e)
		{

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return listSeeTerms;
	}

	// To save Record for Icd Index Master
	public void saveIcdCrossReferenceMaster(IcdCrossRefMasterVO vo, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			IIcdCrossRefMasterDAOi icdCrossRefMasterDAO = new IcdCrossRefMasterDAO(tx);

			icdCrossRefMasterDAO.saveIcdCrossRefMaster(vo, userVO);

		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		// return flag;
	}
	
	
	
	
	
	public Map getHospitalDiseaseEssentials(UserVO _userVO)
	{
		List diseaseList = null;
		List diseaseType = null;
		Map essentialMap = new HashMap(); 
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			// PatientAlertMasterDAO patientAlertMasterDAO = new PatientAlertMasterDAO(tx);

			diseaseList = opdEssentialDAO.getIcdDiseaseList(_userVO);
			diseaseType = opdEssentialDAO.getDiseaseTypeList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ICD_DISEASE, diseaseList);
			essentialMap.put(OpdConfig.EssentialBO_LIST_DISEASE_TYPE, diseaseType);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;

	}

	public HospitalDiseaseMasterVO fetchHospitalDiseaseModify(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			HospitalDiseaseMasterDAOi daoObj = new HospitalDiseaseMasterDAO(tx);

			_hospitalDiseaseMasterVO = daoObj.fetchHospitalDiseaseModify(_hospitalDiseaseMasterVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _hospitalDiseaseMasterVO;
	}

	public void saveHospitalDisease(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			HospitalDiseaseMasterDAOi daoObj = new HospitalDiseaseMasterDAO(tx);
			boolean flag = daoObj.checkDuplicateBeforeSave(_hospitalDiseaseMasterVO, _userVO);
			if (flag)
			{
				daoObj.create(_hospitalDiseaseMasterVO, _userVO);
				daoObj.MapICD(_hospitalDiseaseMasterVO, _userVO);
				daoObj.mapSnomed(_hospitalDiseaseMasterVO, _userVO);
			}
			else throw new HisDuplicateRecordException("Disease Name already exists");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public void modifySaveHospitalDisease(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		try
		{
			tx.begin();
			HospitalDiseaseMasterDAOi daoobj = new HospitalDiseaseMasterDAO(tx);

			flag = daoobj.checkDuplicateBeforeModifySave(_hospitalDiseaseMasterVO, _userVO);

			if (flag)
			{
				daoobj.modify(_hospitalDiseaseMasterVO, _userVO);
				daoobj.modifyInsert(_hospitalDiseaseMasterVO, _userVO);
				daoobj.modifyMapping(_hospitalDiseaseMasterVO, _userVO);
				daoobj.MapICD(_hospitalDiseaseMasterVO, _userVO);
				daoobj.mapSnomed(_hospitalDiseaseMasterVO, _userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Disease Name Already Exist");
			}
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	
	public Map getHospitalDiseaseMappings(HospitalDiseaseMasterVO _hospitalDiseaseMasterVO)
	{
		List icdList = null;
		List snomedList = null;
		Map essentialMap = new HashMap(); 
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			// PatientAlertMasterDAO patientAlertMasterDAO = new PatientAlertMasterDAO(tx);

			icdList = opdEssentialDAO.getMapIcdList(_hospitalDiseaseMasterVO);
			snomedList = opdEssentialDAO.getMapSnomedList(_hospitalDiseaseMasterVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ICD_MAPPED, icdList);
			essentialMap.put(OpdConfig.EssentialBO_LIST_SNOMED_MAPPED, snomedList);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;

	}
	
	// ICD Snomed Mapping Master
	public Map getIcdSnomedEssentials(UserVO _userVO)
	{
		List diseaseList = null;
		Map essentialMap = new HashMap(); 
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			// PatientAlertMasterDAO patientAlertMasterDAO = new PatientAlertMasterDAO(tx);

			diseaseList = opdEssentialDAO.getIcdDiseaseList(_userVO);			
			essentialMap.put(OpdConfig.EssentialBO_LIST_ICD_DISEASE, diseaseList);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;

	}

	public IcdSnomedMappingMasterVO fetchIcdSnomedModify(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			IcdSnomedMappingMasterDAOi daoObj = new IcdSnomedMappingMasterDAO(tx);

			_icdSnomedMappingMasterVO = daoObj.fetchIcdSnomedModify(_icdSnomedMappingMasterVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _icdSnomedMappingMasterVO;
	}

	public void saveIcdSnomedMapping(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=true;
		try
		{
			tx.begin();
			IcdSnomedMappingMasterDAOi daoObj = new IcdSnomedMappingMasterDAO(tx);
			for(int i=0;i<_icdSnomedMappingMasterVO.getSelsnomed().length;i++)
			{
				String _snomedID = _icdSnomedMappingMasterVO.getSelsnomed()[i];
				flag = daoObj.checkDuplicateBeforeSave(_icdSnomedMappingMasterVO, _snomedID);
				if (!flag)
					break;
			}
			if (flag)
			{
				daoObj.mapSnomed(_icdSnomedMappingMasterVO, _userVO);
			}
			else throw new HisDuplicateRecordException("Mapping already exists");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	

	
	public Map fetchIcdSnomedMapping(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO)
	{
		List icdList = null;
		List snomedList = null;
		Map essentialMap = new HashMap(); 
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			// PatientAlertMasterDAO patientAlertMasterDAO = new PatientAlertMasterDAO(tx);

			//icdList = opdEssentialDAO.getMapIcdList(_icdSnomedMappingMasterVO);
			//snomedList = opdEssentialDAO.getMapSnomedList(_icdSnomedMappingMasterVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ICD_MAPPED, icdList);
			essentialMap.put(OpdConfig.EssentialBO_LIST_SNOMED_MAPPED, snomedList);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;

	}
	
	// Save the new  Single Page Interface(Added by Shweta on 12-03-2019)
	public void saveSinglePageInterface(SinglePageInterfaceMasterVO singlePageInterfaceMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			SinglePageInterfaceDAO daoObj = new SinglePageInterfaceDAO(tx);
			boolean flag = daoObj.checkDuplicateBeforeSave(singlePageInterfaceMasterVO, userVO);
			if (flag)
			{
				daoObj.create(singlePageInterfaceMasterVO, userVO);
			}
			else throw new HisDuplicateRecordException("Interface Exists for this Clinical Section and Interface Type.");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println("error.... OpdMasterBO ");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}
	
	
	public SinglePageInterfaceMasterVO getSinglePageModifyDetail(SinglePageInterfaceMasterVO singlePageInterfaceMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			SinglePageInterfaceDAO daoObj = new SinglePageInterfaceDAO(tx);

			singlePageInterfaceMasterVO = daoObj.fetchRecord(singlePageInterfaceMasterVO, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return singlePageInterfaceMasterVO;
	}
	
	public void modifySaveSinglePage(SinglePageInterfaceMasterVO singlePageInterfaceMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean interfaceName = false;
		try
		{
			tx.begin();
			SinglePageInterfaceDAO daoObj = new SinglePageInterfaceDAO(tx);
			interfaceName = daoObj.checkDuplicateBeforeModifySave( singlePageInterfaceMasterVO, userVO);
			if (interfaceName == true)
			{
				daoObj.modify(singlePageInterfaceMasterVO, userVO);
				//daoObj.modifyInsert(singlePageInterfaceMasterVO, userVO);
			}
			else throw new HisDuplicateRecordException("Patient Category COde with Profile Type already exists");
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

	}

	public byte[] fetchimageFromPostgres(String imageCode)
	{
		//ImageMasterVO imageMasterVO = new ImageMasterVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		byte[] getImage=null;
		try
		{
			tx.begin();
			ImageMasterDAO imageMasterDAO = new ImageMasterDAO(tx);
			 getImage = imageMasterDAO.fetchimageFromPostgres(imageCode);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return getImage;
	}

	@Override
	public Map getTemplateListForSymptom(UserVO _UserVO) {
		// TODO Auto-generated method stub
		return null;
	}

	//Added by Vasu on 26.Dec.2019
	public static void saveTemplateImage(HisFileControlUtil fileUtil,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			ImageMasterDAO imageMasterDAO = new ImageMasterDAO(tx);
			
		    imageMasterDAO.createTemplateImage(fileUtil, userVO);
		
			
			String fileName = imageMasterDAO.selectTemplateImageName(fileUtil, userVO);
			String imageFirstName=fileName.substring(0,fileName.lastIndexOf("."));
            String actualImageName = imageFirstName.substring(5,8);
            fileUtil.setActualFileName(actualImageName);
			ByteArrayInputStream in = new ByteArrayInputStream(fileUtil.getFileContent());
			BufferedImage bImageFromConvert = ImageIO.read(in);
			
			File file=new File(fileUtil.getFileName());
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(fileUtil.getFileContent()));
			ImageIO.write(image, "jpg", file);
			FileInputStream fileInFTPStream = new FileInputStream(file);
			//imageMasterDAO.saveTemplateImageToPostgres(fileUtil, userVO);
			String fileLocation=FTPFileTransfer.uploadTemplateImageFile(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, fileUtil.getFileName(), fileInFTPStream, actualImageName);
			System.out.println("FTP Image location for template<<<"+fileLocation);
			imageMasterDAO.saveTemplateImageFTPDetails(fileUtil, userVO,fileLocation);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
	}

	//Added by Vasu on 27.Dec.2019
	public static String getTemplateImageFileNo(String fileName)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String fileNo = "";
		try
		{
			tx.begin();
			ImageMasterDAO imageMasterDAO = new ImageMasterDAO(tx);
			
			
			 fileNo = imageMasterDAO.getTemplateImageFileNo(fileName);
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return fileNo;
	}

	/*@Override
	public void addSymptomWiseTemplateList(
			SymptomWiseTemplateMappingMasterVO[] voSymptomWiseTemplate,
			UserVO _UserVO) {
		// TODO Auto-generated method stub
		
	}*/

/*	@Override
	public Map getDetails(
			SymptomWiseTemplateMappingMasterVO symptomWiseTemplateMappingMasterVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public void saveModifyDetail(
			SymptomWiseTemplateMappingMasterVO[] _symptomWiseTemplateMappingMasterVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}*/
}
