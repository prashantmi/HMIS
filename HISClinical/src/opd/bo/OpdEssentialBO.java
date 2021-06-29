/**
## 		Modification Log		: getEpisodeDiagnosisDetail				
##		Modify Date				: 20-01-2015
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh
*/
package opd.bo;

import hisglobal.backutil.HelperMethods;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.AudioVideoMasterVO;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartUnitMapppingVO;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.DeskMenuMacroMstVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.DeskTypeMenuMappingVO;
import hisglobal.vo.DisclaimerMstVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.DrugDoseVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.EpisodeAttendantDetailVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeKeywordsMasterVO;
import hisglobal.vo.EpisodeReferVO;
import hisglobal.vo.EpisodeSummaryDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.EstimateCertificateReqVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdGroupMasterVO;
import hisglobal.vo.IcdHospitalMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.ImagePointerMasterVO;
import hisglobal.vo.OpdClinicalDetailVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientBelongingVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientFamilyDtlVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileAccessDetailVO;
import hisglobal.vo.ProfileRestrictedCategoryMasterVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UnitDrugListMasterVO;
import hisglobal.vo.UnitDrugMstVO;
import hisglobal.vo.UnitExtTreatMstVO;
import hisglobal.vo.UnitImageDescMasterVO;
import hisglobal.vo.UnitWiseMacroMstVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserDeskUnitWardMappingMasterVO;
import hisglobal.vo.UserVO;
import investigationDesk.InvestigationConfig;
import investigationDesk.transactions.dao.viewInvestigationDAO;
import investigationDesk.vo.viewInvestigationVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.dao.EMRPatientDetailDAO;
import mrd.transaction.dao.MedicalCertificateDAO;
import mrd.transaction.dao.MedicalCertificateDAOi;
import mrd.transaction.dao.MrdEssentialDAO;
import mrd.transaction.dao.PatientFamilyDtlDAO;
import mrd.transaction.dao.PatientFamilyDtlDAOi;
import mrd.vo.EMRPateintDataPolicyVO;
import opd.OpdConfig;
import opd.bo.delegate.OpdPatientDelegate;
import opd.dao.ChartUnitMapppingDAO;
import opd.dao.ChartUnitMapppingDAOi;
import opd.dao.ConsultationDtlDAO;
import opd.dao.DocumentUploadDtlDAO;
import opd.dao.DocumentUploadDtlDAOi;
import opd.dao.DynamicVisitSummaryDtlDAO;
import opd.dao.DynamicVisitSummaryDtlDAOi;
import opd.dao.EpisodeAttendantDtlDAO;
import opd.dao.EpisodeAttendantDtlDAOi;
import opd.dao.EstimateRequestDAO;
import opd.dao.OpdEssentialDAO;
import opd.dao.OpdEssentialDAOi;
import opd.dao.OpdPatientImageDtlDAO;
import opd.dao.OpdPatientImageDtlDAOi;
import opd.dao.OpdRegEssentialDAO;
import opd.dao.PatientProfileDetailDAO;
import opd.dao.PatientProfileDetailDAOi;
import opd.dao.ProfileAccessDetailDAO;
import opd.dao.ProfileAccessDetailDAOi;
import opd.dao.ProfileTypeDAO;
import opd.master.controller.fb.ClinicalSectionCompMapFB;
import opd.master.dao.DeskMenuMacroMasterDAO;
import opd.master.dao.DeskTypeMenuMappingMstDAO;
import opd.master.dao.IcdDiseaseMasterDAO;
import opd.master.dao.IcdDiseaseMasterDAOi;
import opd.master.dao.IcdGroupMasterDAO;
import opd.master.dao.IcdSubGroupMasterDAO;
import opd.master.dao.ImageMasterDAO;
import opd.master.dao.ImageMasterDAOi;
import opd.master.dao.ImagePointerMasterDAO;
import opd.master.dao.ImagePointerMasterDAOi;
import opd.master.dao.ProfileRestrictedCatDAO;
import opd.master.dao.ProfileRestrictedCatDAOi;
import opd.master.dao.UnitEpisodeKeywordMasterDAO;
import opd.master.dao.UnitEpisodeKeywordMasterDAOi;
import opd.master.dao.UnitImageDescMasterDAO;
import opd.master.dao.UnitImageDescMasterDAOi;
import opd.master.dao.UnitImageMasterDAO;
import opd.master.dao.UnitImageMasterDAOi;
import opd.master.dao.UserDeskMenuMasterDAO;
import opd.master.dao.UserDeskMenuTemplateMasterDAO;
import opd.master.dao.UserDeskUnitWardMappingMasterDAO;
import registration.RegistrationConfig;
import registration.dao.DailyPatientDAO;
import registration.dao.EpisodeDAO;
import registration.dao.EpisodeDAOi;
import registration.dao.EpisodeSummaryDAO;
import registration.dao.EpisodeSummaryDAOi;
import registration.dao.EssentialDAO;
import registration.master.dao.UnitMasterDAO;
import registration.vo.DSSEpisodeVO;

public class OpdEssentialBO implements OpdEssentialBOi
{

	// /////Method for selecting essential data for DEPARTMENT_ICD_MASTER/////////
	public Map getDeptIcdEssential(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List icdGroupList = null;
		List icdSubGroupList = null;
		List icdDiseaseList = null;
		List icdSubDiseaseList = null;
		List departmentList = null;
		List unitList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			EssentialDAO ObjEssDao = new EssentialDAO(tx);

			departmentList = ObjEssDao.getAllDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT, departmentList);

			unitList = ObjEssDao.getAllUnitBasedOnSeatID(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_SEATID, unitList);

			icdGroupList = opdEssDAO.getIcdGroupList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ICD_GROUP, icdGroupList);

			icdSubGroupList = opdEssDAO.getIcdSubGroupList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ICD_SUBGROUP, icdSubGroupList);

			icdDiseaseList = opdEssDAO.getIcdDiseaseList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ICD_DISEASE, icdDiseaseList);

			icdSubDiseaseList = opdEssDAO.getIcdSubDiseaseList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ICD_SUBDISEASE, icdSubDiseaseList);

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

	// ///Method for selecting essential data for deletion from DEPT_ICD_MST/////////
	public Map getDeptIcdRemovalDetails(String _choice, String _code, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List icdGroupList = null;
		List icdSubGroupList = null;
		List icdDiseaseList = null;
		List icdSubDiseaseList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);

			if (_choice.equals(OpdConfig.CHOICE_DEPARTMENT))
			{
				icdGroupList = opdEssDAO.getIcdGroupListRemovalDeptWise(_code, _userVO);
				essentialMap.put(OpdConfig.GROUP_LIST_FOR_REMOVAL, icdGroupList);
				icdSubGroupList = opdEssDAO.getIcdSubGroupListRemovalDeptWise(_code, _userVO);
				essentialMap.put(OpdConfig.SUB_GROUP_LIST_FOR_REMOVAL, icdSubGroupList);
				icdDiseaseList = opdEssDAO.getIcdDiseaseListRemovalDeptWise(_code, _userVO);
				essentialMap.put(OpdConfig.DISEASE_LIST_FOR_REMOVAL, icdDiseaseList);
				icdSubDiseaseList = opdEssDAO.getIcdSubDiseaseListRemovalDeptWise(_code, _userVO);
				essentialMap.put(OpdConfig.SUB_DISEASE_LIST_FOR_REMOVAL, icdSubDiseaseList);
			}
			if (_choice.equals(OpdConfig.CHOICE_UNIT))
			{
				icdGroupList = opdEssDAO.getIcdGroupListRemovalUnitWise(_code, _userVO);
				essentialMap.put(OpdConfig.GROUP_LIST_FOR_REMOVAL, icdGroupList);
				icdSubGroupList = opdEssDAO.getIcdSubGroupListRemovalUnitWise(_code, _userVO);
				essentialMap.put(OpdConfig.SUB_GROUP_LIST_FOR_REMOVAL, icdSubGroupList);
				icdDiseaseList = opdEssDAO.getIcdDiseaseListRemovalUnitWise(_code, _userVO);
				essentialMap.put(OpdConfig.DISEASE_LIST_FOR_REMOVAL, icdDiseaseList);
				icdSubDiseaseList = opdEssDAO.getIcdSubDiseaseListRemovalUnitWise(_code, _userVO);
				essentialMap.put(OpdConfig.SUB_DISEASE_LIST_FOR_REMOVAL, icdSubDiseaseList);
			}

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

	public List getIcdGroupList(UserVO _userVO)
	{
		List icdGroupList = new ArrayList();

		return icdGroupList;

	}

	/**
	 * get Opd Desk Menu Detail According To SeatID And Location (Left Menu/Right Menu) calls getMenuDtlBySeatId from
	 * OpdEssentialDAO class through OpdEssentialDAOi Interface
	 * 
	 * @param _UserVO
	 *            Provides User details.
	 * @return UserDeskMenuMasterVO[] containing All Details For Menu Display
	 */
	// *
	public DeskDetailVO[] getOpdMenuDetail(UserVO _userVO, String location, String unitCode, String deskType)
	{
		DeskDetailVO[] deskDetailVOs;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			deskDetailVOs = opdEssentialDAOi.getMenuDtlBySeatId(_userVO, location, unitCode, deskType);
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
		return deskDetailVOs;
	}

	// * Getting Essentials for Adding into User Desk Menu Master
	public Map getAddUserDeskMenuMasterEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstDept = new ArrayList();
		List lstUnits = new ArrayList();
		List lstWard=new ArrayList();
		List lstDeskType=new ArrayList();
		List lstGroup=new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
		
			
			lstDept = objDAO.getAllClinicalDepartmentList(_userVO);
			lstUnits = objDAO.getAllClinicalUnitList(_userVO);
			
			lstDeskType=objDAO.getDeskType(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDept);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_UNITS, lstUnits);
			essentialMap.put(OpdConfig.Essential_BO_LIST_ALL_DESK_TYPE, lstDeskType);
			
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
	
	public Map getDeptAndUnit(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstDept = new ArrayList();
		List lstUnits = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			lstDept = objDAO.getAllClinicalDepartmentList(_userVO);
			lstUnits = objDAO.getAllClinicalUnitList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDept);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_UNITS, lstUnits);
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

	// Getting Clinical Dept & Unit List Mode Wise 
	public Map getDeptNUnitModeWise(String _mode, String _deskId, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstDept = new ArrayList();
		List lstUnits = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			UserDeskMenuTemplateMasterDAO userDeskMenuDAO = new UserDeskMenuTemplateMasterDAO(tx);
			
			lstDept = objDAO.getAllClinicalDepartmentList(_userVO);			
			if(_mode.equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
				lstUnits = userDeskMenuDAO.getNotAddedClinicalUnits(_deskId, _userVO);
			else
				lstUnits = objDAO.getAllClinicalUnitList(_userVO);
			
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDept);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_UNITS, lstUnits);
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

	// Getting All Group & Seat List Mode Wise
	public Map getGroupNSeatModeWise(String _mode, String _deskId, List<Entry> _units, List<Entry> _wards, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstGroups = new ArrayList();		
		List lstSeats = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			UserDeskMenuTemplateMasterDAO userDeskMenuDAO = new UserDeskMenuTemplateMasterDAO(tx);

			lstGroups = opdEssentialDAOi.getAllGroupList(_userVO); 
			if(_mode.equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
				lstSeats = userDeskMenuDAO.getNotAddedSeatsUnitSeatWise(_deskId, _units, _userVO);
			else if(_mode.equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
				lstSeats = userDeskMenuDAO.getNotAddedSeatsUnitWardSeatWise(_deskId, _units, _wards, _userVO);
			else
				lstSeats = new ArrayList();
			essentialMap.put(OpdConfig.ESSENTIALBO_ALL_GROUP_LIST, lstGroups);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_SEATS, lstSeats);
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

	// *
	public PatientDetailVO[] getTodayPatientList(UserVO _userVO, String unitCode, String roomCode)
	{
		PatientDetailVO[] dailyPatientVOs;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			dailyPatientVOs = dailyPatientDAO.getTodayPatientsBySeatID(_userVO, unitCode, roomCode);
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
		return dailyPatientVOs;
	}

	// *
	public List opdDeskEssentials(UserVO _userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getUnitsBySeatId(_userVO);
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

	// *
	public List getRoomsByUnitCode(UserVO _userVO, String unitCode)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getRoomsByUnitCode(_userVO, unitCode);
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
		return list;
	}

	// *
	public String getOpdPatientCount(UserVO _userVO, String unitCode, String roomCode)
	{
		String count = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			count = dailyPatientDAO.getTodayPatientsCountBySeatID(_userVO, unitCode, roomCode);
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
		return count;

	}

	public String getAttendedOpdPatientCount(UserVO _userVO, String unitCode, String roomCode)
	{
		String count = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			count = dailyPatientDAO.getTodayPatientsAttendedCountBySeatID(_userVO, unitCode, roomCode);
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
		return count;

	}

	// * Getting Desk Type List
	public List getDeskType(UserVO _userVO)
	{
		List listDeskType = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			listDeskType = daoOpdEssentail.getDeskType(_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
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
		return listDeskType;
	}

	// * Getting Essentials for Add-Modify Menus To Desk Master
	public Map getAddModifyMenuToDeskMasterEssentials(String _deskType, UserVO _userVO)
	{
		Map mp = new HashMap();
		List lstMenus = new ArrayList();
		List<Entry> lstDutyRoles = new ArrayList<Entry>();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi daoOpdEssentail = new OpdEssentialDAO(tx);			
			lstMenus = daoOpdEssentail.getMenusList(_deskType, _userVO);
			for (int i = 0; i < lstMenus.size(); i++)
			{
				Entry entobj = (Entry) lstMenus.get(i);
				entobj.setValue(entobj.getValue() + "@" + OpdConfig.DESK_DEFAULT_COLOR + "@" + entobj.getLabel() + "@0@-1");
			}
			mp.put(OpdConfig.EssentialBO_LIST_ALL_MENUS, lstMenus);
			
			lstDutyRoles = daoOpdEssentail.getDutyRolesList(_userVO);
			mp.put(OpdConfig.ESSENTIALBO_DESK_MENU_BASED_DUTY_ROLE_LIST, lstDutyRoles);
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
		return mp;
	}

	// //Essential Map for Opd Diagnosis///

	public Map getIcdDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List diagnosisTypeList = null;
		//List diagnosisTypeListIcd = null;
		//List diagnosisTypeListHospital = null;
		List diagnosisCodeList = null;
		List diagnosisSiteList = null;
		EpisodeDiagnosisVO[] episodeDiagVO = null;
		//String str0="0";
		//String str1="1";
		//String str2="2";

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			
			diagnosisTypeList = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,OpdConfig.CHOICE_ICD_CODE,OpdConfig.CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE);
			essentialMap.put(OpdConfig.DIAGNOSIS_LIST_ICD, diagnosisTypeList);
			essentialMap.put(RegistrationConfig.DIAGNOSIS_LIST, diagnosisTypeList);

			/*diagnosisTypeList = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,str0,str2);
			essentialMap.put(OpdConfig.DIAGNOSIS_LIST_ICD, diagnosisTypeList);
			
			diagnosisTypeListHospital = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,str1,str2);
			essentialMap.put(OpdConfig.DIAGNOSIS_LIST_HOSPITAL, diagnosisTypeListHospital);
			
			diagnosisTypeList = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,str0,str2);
			essentialMap.put(RegistrationConfig.DIAGNOSIS_LIST, diagnosisTypeList);*/

			diagnosisCodeList = opdEssDAO.getIcdDiagnosisCodeListByUnit(_userVO); // Not showing from here showing from ajax from desk footer
			essentialMap.put(OpdConfig.EssentialBO_DIAGNOSIS_CODE_LIST_UNIT_WISE, diagnosisCodeList);

			// //fetching the diagnosis record for previous visit///
			episodeDiagVO = opdEssDAO.getPrevDiagnosisDetail(_patDtlVO.getPatCrNo(), _patDtlVO.getEpisodeCode(),_userVO);
			essentialMap.put(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO, episodeDiagVO);
			
			diagnosisSiteList = opdEssDAO.getDiagnosisSiteList(_userVO);
			essentialMap.put(OpdConfig.DIAGNOSIS_SITE_LIST, diagnosisSiteList);

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

	public Map getHospitalDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List diagnosisTypeList = null;
		//List diagnosisTypeListIcd = null;
		//List diagnosisTypeListHospital = null;
		List diagnosisCodeList = null;
		List diagnosisSiteList = null;
		EpisodeDiagnosisVO[] episodeDiagVO = null;
		
		List diagnosisSiteCodeList = null;
		List morphologyCodeList = null;
		//String str0="0";
		//String str1="1";
		//String str2="2";

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);

			diagnosisTypeList = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,OpdConfig.CHOICE_HOSPITAL_CODE,OpdConfig.CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE);
			essentialMap.put(OpdConfig.DIAGNOSIS_LIST_HOSPITAL, diagnosisTypeList);
			essentialMap.put(RegistrationConfig.DIAGNOSIS_LIST, diagnosisTypeList);

			/*diagnosisTypeListIcd = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,str0,str2);
			essentialMap.put(OpdConfig.DIAGNOSIS_LIST_ICD, diagnosisTypeListIcd);
			
			diagnosisTypeListHospital = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,str1,str2);
			essentialMap.put(OpdConfig.DIAGNOSIS_LIST_HOSPITAL, diagnosisTypeListHospital);
			
			diagnosisTypeList = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,str1,str2);
			essentialMap.put(RegistrationConfig.DIAGNOSIS_LIST, diagnosisTypeList);*/

			diagnosisCodeList = opdEssDAO.getUnitWiseHospitalDiagnosisCodeList(_patDtlVO.getDepartmentUnitCode(),_userVO);
			if(diagnosisCodeList==null || diagnosisCodeList.size()<=0)
				//diagnosisCodeList = opdEssDAO.getHospitalDiagnosisCodeList(_userVO);
				//Modified by Vasu on 18.Oct.2018
				diagnosisCodeList =null;
			essentialMap.put(OpdConfig.EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST, diagnosisCodeList);

			// //fetching the diagnosis record for previous visit///
			episodeDiagVO = opdEssDAO.getPrevDiagnosisDetail(_patDtlVO.getPatCrNo(),_patDtlVO.getEpisodeCode(),_userVO);
			essentialMap.put(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO, episodeDiagVO);

			diagnosisSiteList = opdEssDAO.getDiagnosisSiteList(_userVO);
			essentialMap.put(OpdConfig.DIAGNOSIS_SITE_LIST, diagnosisSiteList);
			
			//Added by Vasu on 24.Oct.2018 for ICD-O Integration
			
			diagnosisSiteCodeList = opdEssDAO.getDiagnosisSiteCodeList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_DIAGNOSIS_SITE_LIST_UNIT_WISE, diagnosisSiteCodeList);

			morphologyCodeList = opdEssDAO.getMorphologyCodeList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_MORPHOLOGY_LIST_UNIT_WISE, morphologyCodeList);
			
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
	
	
	
	public Map getSnomdDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List diagnosisTypeList = null;
		//List diagnosisTypeListIcd = null;
		//List diagnosisTypeListHospital = null;
		List diagnosisCodeList = null;
		List diagnosisSiteList = null;
		EpisodeDiagnosisVO[] episodeDiagVO = null;
		//String str0="0";
		//String str1="1";
		//String str2="2";

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);

			diagnosisTypeList = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,OpdConfig.CHOICE_HOSPITAL_CODE,OpdConfig.CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE);
			essentialMap.put(OpdConfig.DIAGNOSIS_LIST_HOSPITAL, diagnosisTypeList);
			essentialMap.put(RegistrationConfig.DIAGNOSIS_LIST, diagnosisTypeList);

			/*diagnosisTypeListIcd = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,str0,str2);
			essentialMap.put(OpdConfig.DIAGNOSIS_LIST_ICD, diagnosisTypeListIcd);
			
			diagnosisTypeListHospital = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,str1,str2);
			essentialMap.put(OpdConfig.DIAGNOSIS_LIST_HOSPITAL, diagnosisTypeListHospital);
			
			diagnosisTypeList = opdEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,str1,str2);
			essentialMap.put(RegistrationConfig.DIAGNOSIS_LIST, diagnosisTypeList);*/

			
			// //fetching the diagnosis record for previous visit///
			episodeDiagVO = opdEssDAO.getPrevDiagnosisDetail(_patDtlVO.getPatCrNo(),_patDtlVO.getEpisodeCode(),_userVO);
			essentialMap.put(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO, episodeDiagVO);

			//diagnosisSiteList = opdEssDAO.getDiagnosisSiteList(_userVO);
			//essentialMap.put(OpdConfig.DIAGNOSIS_SITE_LIST, diagnosisSiteList);
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
	

	public List getIcdCodes(String _searchIcdCode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objEssentialDAO = new OpdEssentialDAO(tx);
			List list = objEssentialDAO.getIcdCodes(_searchIcdCode, _userVO);

			return list;
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
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
	}

	public List getDiseaseName(String _searchDisease, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objEssentialDAO = new OpdEssentialDAO(tx);
			List list = objEssentialDAO.getDiseaseName(_searchDisease, _userVO);

			return list;
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
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
	}

	/**
	 * Getting List of ICD Diseases on the basis of Search Criteria
	 * 
	 * @param _strSearch Search String either ICD Code or Disease Name Segment
	 * @param _strSearchType Search Style either Code-based or Name-based
	 * @param _userVO User Detail
	 * @return List ICD Disease Detail
	 */
	public List<IcdDiseaseMasterVO> getICDCodesSearchDetail(String _strSearch, String _strSearchType, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<IcdDiseaseMasterVO> lstICDDiseases = new ArrayList<IcdDiseaseMasterVO>();
		try
		{
			tx.begin();
			OpdEssentialDAO objEssentialDAO = new OpdEssentialDAO(tx);
			if(_strSearchType.equals("1"))
				lstICDDiseases = objEssentialDAO.getICDDiseaseByCode(_strSearch, _userVO);
			else
				lstICDDiseases = objEssentialDAO.getICDDiseaseByName(_strSearch, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
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
		return lstICDDiseases;
	}

	/**
	 * Getting List of ICD Sub Diseases on the basis of Clicked Parent ICD Disease Code
	 * 
	 * @param _strICDCode Clicked ICD Code
	 * @param _userVO User Detail
	 * @return List of ICD Sub Disease
	 */
	public List<IcdDiseaseMasterVO> getICDSubDiseases(String _strICDCode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<IcdDiseaseMasterVO> lstICDSubDiseases = new ArrayList<IcdDiseaseMasterVO>();
		try
		{
			tx.begin();
			OpdEssentialDAO objEssentialDAO = new OpdEssentialDAO(tx);
			lstICDSubDiseases = objEssentialDAO.getICDSubDiseaseByCode(_strICDCode, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
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
		return lstICDSubDiseases;
	}

	public List getHospitalDiagnosisName(String _searchDisease, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objEssentialDAO = new OpdEssentialDAO(tx);
			List list = objEssentialDAO.getHospitalDiseaseName(_searchDisease, _userVO);

			return list;
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
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

	}

	public List getHospitalDiagnosisCodes(String _searchCode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objEssentialDAO = new OpdEssentialDAO(tx);
			List list = objEssentialDAO.getHospitalDiagnosisCodes(_searchCode, _userVO);

			return list;
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
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
	}

	// * Getting Seats not assigned to given Department Units
	// * Returning List of Seats that are not yet assigned a desk/template for given units in List
	// Change By Chetan According to Global Mapping Concept
	//public List getAddUserDeskMenuMasterSeatsByNotUnits(String[] _UnitsList, String deskType, UserVO _userVO, String groupCode)
		
	public List getAddUserDeskMenuMasterSeatsByNotUnits(String deskType, UserVO _userVO, String groupCode)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			//list = opdEssentialDAOi.getSeatsNotforUnits(_UnitsList, deskType, _userVO,groupCode);
			list = opdEssentialDAOi.getSeatsNotforUnits(deskType, _userVO,groupCode);
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
	
	public List getAddUserDeskMenuMasterSeatsByNotUnits(String[] _WardsList, UserVO _userVO, String groupCode)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getSeatsByNotWards(_WardsList, _userVO, groupCode);
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

	// * Getting All Desk By given Desk Type
	public List getAddUserDeskMenuMasterDeskByType(String _DeskType, UserVO _userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getDeskByType(_DeskType, _userVO);
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

	// * Getting User Desk Menu Master Record
	public UserDeskMenuMasterVO getModifyViewUserDeskMenuMstVO(UserDeskMenuMasterVO _UserDeskVO, UserVO _UserVO)
	{
		UserDeskMenuMasterVO deskMenuMstVO;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskMenuMasterDAO userDeskDAOi = new UserDeskMenuMasterDAO(tx);
		
			deskMenuMstVO = userDeskDAOi.fetchModifyRecord(_UserDeskVO, _UserVO);
			
			
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
		return deskMenuMstVO;
	}

	public Map getReferPatientEssentials(String _crNo, String _deptCode, UserVO _userVO, String _deskType, String episodeCode)
	{
		Map essentialMap = new HashMap();
		List lstUnit = null;
		List lstAllDepartment = null;
		List lstRefHospital = null;
		List<Entry> lstDeptUnits = null;
		List<Entry> profileTypeList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);
			/////earlier the department and unit list was from roster but now it shows
			////list of all available department units
			/*lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_GENERAL);
			essentialMap.put(OpdConfig.OPD_ESSENTIALBO_OPTION_DEPARTMENT, lstDepartment);
			lstUnit = objEssentialDAO.getUnitsByType(_userVO,RegistrationConfig.UNIT_TYPE_SPECIALITY);
			essentialMap.put(OpdConfig.OPD_UNIT_WITH_SPECIALITY, lstUnit);*/
			
				// All Special Units
			lstUnit = objEssentialDAO.getAllUnitByTypeForReferal(_crNo,_userVO,RegistrationConfig.UNIT_TYPE_SPECIALITY);			
			essentialMap.put(OpdConfig.OPD_UNIT_WITH_SPECIALITY, lstUnit);
				// All Departments with General Unit
			//lstAllDepartment = objEssentialDAO.getAllDepartmentWithGeneralUnitForReferal(_crNo,_userVO);
			lstAllDepartment = objEssentialDAO.getAllGlobalDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT, lstAllDepartment);
			
				// Refer Other Hospitals
			//lstRefHospital = objEssentialDAO.getRefHospitalForReferal(_userVO);
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL, lstRefHospital);
			// All General Units of Current Department
			lstDeptUnits = opdEssentialDAO.getGeneralUnitsListByDept(_deptCode,_userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIALBO_LIST_DEPARTMENTS_GENERAL_UNITS, lstDeptUnits);

			// get profile type for refer patients
			String usablity = OpdConfig.PROFILE_TYPE_USABLITY_OPD;
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK)) usablity = OpdConfig.PROFILE_TYPE_USABLITY_IPD;
			profileTypeList = opdEssentialDAO.getProfileTypes(usablity, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED, _userVO);
			essentialMap.put(OpdConfig.PROFILE_TYPE_LIST, profileTypeList);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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

	public Map getAllergyType(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List allergyList = null;
		List listSensitivity = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			allergyList = opdEssDAO.getAllergyTypeList(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALBO_LIST_ALL_ALLERGY_TYPES, allergyList);
			listSensitivity = opdEssDAO.getListSensitivity(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY, listSensitivity);
	//		listAdvice = opdEssDAO.getListAllergyAdvice(_userVO);
	//		essentialMap.put(OpdConfig.ESSENTIALBO_OPTION_ALLERGY_ADVICE, listAdvice);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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

	public Map getEConsultionEssentials(ConsultationDtlVO consultationDtlVO,UserVO _userVO)
	{

		List consultantList = null;
		List consultantNameNo = null;
		List cosultantListWithSeatidAndNo = null;
		List tempList=null;
		List deptList=null;
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			PatientProfileDetailDAOi patProfileDAOi = new PatientProfileDetailDAO(tx);
			
			tempList = opdEssentialDAOi.getCosultantListForMail(_userVO);
			deptList=	opdEssentialDAOi.getAllUnitList1(_userVO);

			if(tempList!=null)
			{
				consultantList=new ArrayList();
				cosultantListWithSeatidAndNo=new ArrayList();
				Iterator listIterator=tempList.iterator();
				while(listIterator.hasNext())
				{
					List list=(List)listIterator.next();
					Entry entry=new Entry();
					entry.setValue((String)list.get(2));
					entry.setLabel((String)list.get(1));
					consultantList.add(entry);
					Entry entry1=new Entry();
					entry1.setValue((String)list.get(2));
					entry1.setLabel((String)list.get(0));
					cosultantListWithSeatidAndNo.add(entry1);
				}
				
				essentialMap.put(OpdConfig.OPD_ECONSULTANT_DETAIL_LIST, consultantList);
				essentialMap.put(OpdConfig.OPD_DEPT_LIST, deptList);

			//	consultantNameNo = opdEssentialDAOi.getCosultantListNameAndNoBySeatId(_userVO);
				essentialMap.put(OpdConfig.OPD_CONSULTANT_NAME_NO, consultantNameNo);
				//cosultantListWithSeatidAndNo = opdEssentialDAOi.getCosultantListWithNoAndSeatId(_userVO);
				essentialMap.put(OpdConfig.OPD_CONSULTANT_NO_SEATID, cosultantListWithSeatidAndNo);
				//get the available profile of the patient
				List patProfileVOList=patProfileDAOi.getPatientProfilesForInbox(consultationDtlVO.getPatCrNo(), consultationDtlVO.getDepartmentUnitCode(), _userVO);
				essentialMap.put(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST, patProfileVOList);
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

	public Map getConsultionInboxEssentials(UserVO _userVO)
	{
		List consultantList = null;
		List cosultantListWithSeatidAndNo = null;
		Map essentialMap = new HashMap();
		ConsultationDtlVO[] consultationVO = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List tempList=null;
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			ConsultationDtlDAO consultationDAO = new ConsultationDtlDAO(tx);
			consultationVO = consultationDAO.getAllDetailsBySeatId(_userVO, OpdConfig.OPD_CONSULTANT_ACK_STATUS_NEW,
					OpdConfig.OPD_CONSULTANT_ACK_STATUS_READ);
			essentialMap.put(OpdConfig.CONSULTATION_INBOX_DETAIL_VO, consultationVO);
			
			
			
			
			tempList = opdEssentialDAOi.getCosultantListForMail(_userVO);
			if(tempList!=null)
			{
				consultantList=new ArrayList();
				cosultantListWithSeatidAndNo=new ArrayList();
				Iterator listIterator=tempList.iterator();
				while(listIterator.hasNext())
				{
					List list=(List)listIterator.next();
					Entry entry=new Entry();
					entry.setValue((String)list.get(2));
					entry.setLabel((String)list.get(1));
					consultantList.add(entry);
					Entry entry1=new Entry();
					entry1.setValue((String)list.get(2));
					entry1.setLabel((String)list.get(0));
					cosultantListWithSeatidAndNo.add(entry1);
				}
			
			}
			
			
			//consultantNameNo = opdEssentialDAOi.getCosultantListNameAndNoBySeatId(_userVO);
			//essentialMap.put(OpdConfig.OPD_CONSULTANT_NAME_NO, consultantNameNo);
			//cosultantListWithSeatidAndNo = opdEssentialDAOi.getCosultantListWithNoAndSeatId(_userVO);
			essentialMap.put(OpdConfig.OPD_CONSULTANT_NO_SEATID, cosultantListWithSeatidAndNo);
			//consultantList = opdEssentialDAOi.getCosultantListForMail(_userVO);
			essentialMap.put(OpdConfig.OPD_ECONSULTANT_DETAIL_LIST, consultantList);
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
		return essentialMap;
	}

	public List getAllergiesEssential(String _allergyCode, UserVO _userVO)
	{

		List listAllergyReason = null;
		List listAllergyStatic = null;
		List listAllergyDynamic = null;
		String[] allergyDetails = new String[2];
		String sourceFlag = "";
		// String tableName="";
		// String columnList="";
		// String nameField="";
		// String codefield="";
		String tableQuery = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objEssentialDAO = new OpdEssentialDAO(tx);
			allergyDetails = objEssentialDAO.getAllergyDetails(_allergyCode);
			sourceFlag = allergyDetails[0];
			if (sourceFlag.equals(OpdConfig.ALLERY_TYPE_SOURCE_FLAG_STATIC))
			{
				listAllergyReason = objEssentialDAO.getListAllergyReason(_allergyCode, _userVO);
			}
			else
			{
				// tableName=allergyDetails[1];
				// columnList=allergyDetails[2];
				tableQuery = allergyDetails[1];

				// nameField=columnList.substring(0, columnList.indexOf("#"));
				// codefield=columnList.substring(columnList.indexOf("#")+1);
				listAllergyReason=new ArrayList();
				listAllergyDynamic = objEssentialDAO.getReasonListFromTableQuery(tableQuery,_userVO);
				listAllergyStatic = objEssentialDAO.getListAllergyReason(_allergyCode, _userVO);
				if(listAllergyDynamic!=null)
					for(int i=0;i<listAllergyDynamic.size();i++)
					{
						 Entry ent=(Entry)listAllergyDynamic.get(i);
						 listAllergyReason.add(ent);
					}
				if(listAllergyStatic!=null)
					for(int i=0;i<listAllergyStatic.size();i++)
					{
						 Entry ent=(Entry)listAllergyStatic.get(i);
						 listAllergyReason.add(ent);
					}
			}
			if (listAllergyReason==null || listAllergyReason.size()==0) 
				throw new HisRecordNotFoundException("No record for Allergy Reason Found");
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
		return listAllergyReason;
	}

	public ConsultationDtlVO[] getconsultationInboxEssentials(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ConsultationDtlVO[] consultationVO = null;
		try
		{
			tx.begin();
			ConsultationDtlDAO consultationDAO = new ConsultationDtlDAO(tx);
			consultationVO = consultationDAO.getAllDetailsBySeatId(_userVO, OpdConfig.OPD_CONSULTANT_ACK_STATUS_NEW,
					OpdConfig.OPD_CONSULTANT_ACK_STATUS_READ);

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

		}
		finally
		{
			tx.close();
		}
		return consultationVO;
	}

	// * Getting Parameter List
	public Map getAddTempleteParameterMasterParaList(UserVO _userVO)
	{
		Map mp = new HashMap();
		List lstPara = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lstPara = daoOpdEssentail.getAllTemplateParametersList(_userVO);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_PARAMETERS, lstPara);
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
		return mp;
	}

	// * Getting Templates List
	public List getEntryTemplateAllTempList(UserVO _userVO)
	{
		List lstTemps = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lstTemps = daoOpdEssentail.getAllTemplateList(_userVO);
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
		return lstTemps;
	}

	// //////////////////Next Visit appointment//////////////////////
	/*public Map getSlotDtl(Apt_slotDtlVO _slotDtlVO)
	{
		Map essentialMap = new HashMap();
		//String[] paraCode = new String[4];
		List lstSlotDtl = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			AptEssentialDAO essensialDAO = new AptEssentialDAO(tx);
			lstSlotDtl = essensialDAO.getSlotDtl(_slotDtlVO);
			essentialMap.put(OpdConfig.APPOINTMENT_ALL_SLOTDTL, lstSlotDtl);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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

	public Apt_slotDtlVO getNextSlotDate(Apt_slotDtlVO _slotDtlVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			AptEssentialDAO essensialDAO = new AptEssentialDAO(tx);
			_slotDtlVO = essensialDAO.getNextSlotDate(_slotDtlVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
		return _slotDtlVO;
	}*/

	// Getting Template Parameter List
	public List getTemplateParaListByTempId(String _tempId, UserVO _userVO)
	{
		List lstTemps = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lstTemps = daoOpdEssentail.getTemplateParaListByTempId(_tempId, _userVO);
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
		return lstTemps;
	}

	// * Getting Template Parameter Detail List
	public List getTemplateParaDetailListByTempId(String _tempId, UserVO _userVO)
	{
		List lstTemps = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lstTemps = daoOpdEssentail.getTemplateParaDetailListByTempId(_tempId, _userVO);
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
		return lstTemps;
	}

	// * Getting Parameter Dynamic Data
	public List getParameterDynamicData(String _query, UserVO _userVO)
	{
		List lstTemps = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lstTemps = daoOpdEssentail.getParameterDynamicData(_query,_userVO);
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
		return lstTemps;
	}

	// Getting Patient Parameter CR Number List
	public Map getAllPatientParaCrNoList(UserVO _userVO)
	{
		Map mp = new HashMap();
		List lstCrno = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lstCrno = daoOpdEssentail.getAllPatientParaCrNoList(_userVO);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_PAT_PARA_CRNO, lstCrno);
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
		return mp;
	}

	// Setting Template Report Essentials
	public Map getSetTemplateReportEssentials(String _crNo, UserVO _userVO)
	{
		Map mp = new HashMap();
		List lstTemps = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lstTemps = daoOpdEssentail.getTemplateListByCrNo(_crNo, _userVO);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_PAT_TEMP_LIST, lstTemps);
			

			lstTemps = daoOpdEssentail.getTempParaListByCrNo(_crNo, _userVO);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_PAT_PARA_LIST, lstTemps);
			
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
		return mp;
	}

	// Getting Actual Template Ids
	public List getActualTempIds(String _crNo, String[] _aPV, UserVO _userVO)
	{
		List lst = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lst = daoOpdEssentail.getActualTempIdsList(_crNo, _aPV, _userVO);
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
		return lst;
	}

	// Getting Actual Parameter Values
	public List getPatActualParaValues(String _crNo, String[] _aPV, UserVO _userVO)
	{
		List lst = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lst = daoOpdEssentail.getPatActualParaValuesList(_crNo, _aPV, _userVO);
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
		return lst;
	}

	// Getting Patient Parameter Vlaues
	public List getPatParaValues(String _crNo, String[] _aPV, UserVO _userVO)
	{
		List lst = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lst = daoOpdEssentail.getParaValueList(_crNo, _aPV, _userVO);
			
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
		return lst;
	}

	public Map getDocumentUploadEssentials(String patCrNo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DocumentUploadDtlVO[] documentUploadDtlVOs = null;
		List documentType = null;
		Map essMap = new HashMap();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			documentType = opdEssentialDAOi.getVerificationDocumentsList();
			essMap.put(OpdConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS_LIST, documentType);
			//-----documentUploadDtlVOs = documentUploadDtlDAOi.getDocumentDetailsByCrNo(patCrNo, _userVO);
			essMap.put(OpdConfig.DOCUMENT_DTL_VO_ARRAY, documentUploadDtlVOs);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essMap);
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

		}
		finally
		{
			tx.close();
		}
		return essMap;
	}

	// * Getting Desk Menu Template List for Current Active Desk
	public List getOpdDeskMenuTemplates(String _unitCode, String _deskMenuId, UserVO _userVO)
	{
		List lst = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lst = daoOpdEssentail.getOpdDeskMenuTemplates(_unitCode, _deskMenuId, _userVO);
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
		return lst;
	}

	// * Getting Desk Menu Template Master ADD Essentials (For all modes)
	public Map getAddUserDeskMenuTemplateUnitWiseEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstDept = new ArrayList();
		List lstUserDeskUnitsNotUnitTemp = new ArrayList();
		List lstUserDeskUnitsWithSeatTemp = new ArrayList();
		List lstUnitsForUnitWardWise = new ArrayList();
		List lstUnitsForUnitWardSeatWise = new ArrayList();
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);

			lstDept = objDAO.getAllClinicalDepartmentList(_userVO);//List all depts.
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDept);

			lstUserDeskUnitsNotUnitTemp = objDAO.getUserDeskUnitsExceptUnitTemplates(_userVO);//List unit for unit wise
			essentialMap.put(OpdConfig.EssentialBO_LIST_USERDESKUNITS_NOT_UNITTEMPLATE, lstUserDeskUnitsNotUnitTemp);

			lstUserDeskUnitsWithSeatTemp = objDAO.getUserDeskUnitsWithSeat(_userVO);//List units for unit-seat wise
			essentialMap.put(OpdConfig.EssentialBO_LIST_USERDESKUNITS_SEAT_NOTNULL, lstUserDeskUnitsWithSeatTemp);
			
			lstUnitsForUnitWardWise = objDAO.getUnitsForUnitWardWise(_userVO);//List units for unit-ward wise
			essentialMap.put(OpdConfig.EssentialBO_LIST_UNITS_FOR_UNITWARDWSISE, lstUnitsForUnitWardWise);
									
			lstUnitsForUnitWardSeatWise = objDAO.getUnitsForUnitWardSeatWise(_userVO);//List units for ward-seat wise
			essentialMap.put(OpdConfig.EssentialBO_LIST_UNITS_FOR_UNITWARDSEATWSISE, lstUnitsForUnitWardSeatWise);
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
	

	// * Getting all desks during desk wise mode
	public Map getAllDeskEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List allDesk = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);

				allDesk = objDAO.getAllDesk(_userVO);
				essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DESK, allDesk);
		
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

	public Map getAllDeskBasedOnDeskType(String deskType,UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List allDesk = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			allDesk = objDAO.getDeskBasedOnDeskType(deskType,_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE, allDesk);		
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
	
	public Map getMappedUnits(String deskId,UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstMappedUnits = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			lstMappedUnits = objDAO.getMappedUnits(deskId,_userVO);
			essentialMap.put(OpdConfig.LIST_MAPPED_UNITS, lstMappedUnits);
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


	public Map getDocumentUploadEssentials(String patCrNo,String episodeCode, UserVO _userVO)
	{
	DocumentUploadDtlVO[] documentUploadDtlVOs = null;
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map essMap = new HashMap();
	try
	{
		tx.begin();
		DocumentUploadDtlDAOi documentUploadDtlDAOi = new DocumentUploadDtlDAO(tx);
		documentUploadDtlVOs = documentUploadDtlDAOi.getDocumentDetailsByCrNo(patCrNo,episodeCode, _userVO);
		essMap.put(OpdConfig.DOCUMENT_DTL_VO_ARRAY, documentUploadDtlVOs); 
	}
	catch (HisRecordNotFoundException e)
	{
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage(), essMap);
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
	return essMap;
	}
	
	public Map getMappedUnitsForUnitSeat(String deskId,UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstMappedUnitsForUnitSeat = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			lstMappedUnitsForUnitSeat = objDAO.getMappedUnitsForUnitSeat(deskId,_userVO);
			essentialMap.put(OpdConfig.LIST_MAPPED_UNITS_FOR_UNITSEAT, lstMappedUnitsForUnitSeat);
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
	
	public Map getMappedUnitsForUnitSeatWard(String deskId,UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstMappedUnitsForUnitSeatWard = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			lstMappedUnitsForUnitSeatWard = objDAO.getMappedUnitsForUnitSeatWard(deskId,_userVO);
			essentialMap.put(OpdConfig.LIST_MAPPED_UNITS_FOR_UNITSEATWARD, lstMappedUnitsForUnitSeatWard);
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
	
	public Map getMappedUnitsForWard(String deskId,UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstMappedUnitsForWard = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			lstMappedUnitsForWard = objDAO.getMappedUnitsForWard(deskId,_userVO);
			essentialMap.put(OpdConfig.LIST_MAPPED_UNITS_FOR_WARD, lstMappedUnitsForWard);
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

	
	// * Getting all desks during desk wise mode
	public Map getAllDeskTypeEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List allDeskType = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			allDeskType = objDAO.getAllDeskType(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE, allDeskType);
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
	

	// * Setting Source Seats List not already added a Template for Selected Units
	// * Getting User Seats from User Desk Menu Master common to all Selected Units (unit-seat wise mode)
	public List getAddUsrDskMnuTempSeatsByInAllUnits(String[] _UnitsList, UserVO _userVO)
	{
		List seat;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			seat = opdEssentialDAOi.getUserDeskMenuSeatsInAllUnits(_UnitsList, _userVO);
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
		return seat;
	}
	
	// getting seats list for unit-ward-seat mode
	public List getAddUsrDskMnuTempSeats(String[] _UnitsList,String[] _WardsList, UserVO _userVO)
	{
		List seat;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			seat = opdEssentialDAOi.getUserDeskMenuSeats(_UnitsList,_WardsList, _userVO);
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
		return seat;
	}
	
	// * Setting Source Seats List not already added a Template for Selected Units
	// * Getting User Seats from User Desk Menu Master common to all Selected Units
	public UserDeskMenuTemplateMasterVO getAddUsrDskMnuTempSeats(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO)
	{
		UserDeskMenuTemplateMasterVO seat;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			seat = opdEssentialDAOi.getUserDeskMenuSeats(_voUDMT, _userVO);
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
		return seat;
	}

	//Getting wards list for unit-ward wise mode
	public UserDeskMenuTemplateMasterVO getAddUsrDskMnuTempWards(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO)
	{
		UserDeskMenuTemplateMasterVO seat;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			seat = opdEssentialDAOi.getUserDeskMenuWards(_voUDMT, _userVO);
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
		return seat;
	}
	
	// * Getting Desk List By Seats and Units (unit-seat wise mode)
	public List getAddUsrDskMnuTempdeksInUnitsnSeats(String[] _UnitsList, String[] _SeatsList, UserVO _userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getUserDeskMenuDesksInAllUnitsnSeats(_UnitsList, _SeatsList, _userVO);
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
		return list;
	}
	
	//Getting desk list for unit-ward wise mode
	public List getAddUsrDskMnuTempdeksInUnitsnWards(String[] _UnitsList, String[] _WardsList, UserVO _userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getUserDeskMenuDesksInAllUnitsnWards(_UnitsList, _WardsList, _userVO);
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
		return list;
	}
	
	//Getting desk list for unit-ward-seat wise mode
	public List getAddUsrDskMnuTempdeksInUnitsnWardsSeat(String[] _UnitsList, String[] _WardsList,String[] _SeatsList, UserVO _userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getUserDeskMenuDesksInAllUnitsnWardsSeat(_UnitsList, _WardsList,_SeatsList, _userVO);
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
		return list;
	}
	
	//Getting ward list for unit-ward wise mode
	public List getAddUsrDskMnuTempWardsInUnits(String[] _UnitsList, UserVO _userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getWardsInAllUnits(_UnitsList, _userVO);
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
		return list;
	}
	
	//Getting ward list for unit-ward-seat wise mode
	public List getAddUsrDskMnuTempWardsForWardSeatWise(String[] _UnitsList, UserVO _userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getWardsForWardSeatWise(_UnitsList, _userVO);
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
		return list;
	}
	
	
	// * Getting Desk List for unit wise mode
	public List getAddUsrDskMnuTempdeksInUnits(String[] _UnitsList, UserVO _userVO)
	{
		List list=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getUserDeskMenuDesksInAllUnits(_UnitsList, _userVO);
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
		return list;
	}

	// * Getting Desk Menu that are Template-Based by Desk Id
	public List getAllTemplateBasedDeskMenusByDeskId(String _deskId, UserVO _UserVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getAllTemplateBasedDeskMenusByDeskId(_deskId, _UserVO);
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

	// * Getting Templates By Unit,Seat,Desk Id
	public List getTemplatesByUnitSeatDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO)
	{
		List list = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getTemplatesByUnitSeatDesk(voUDMT, _UserVO);
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

	// * Getting Templates 
	public List getTemplatesByUnitDesk(UserDeskMenuTemplateMasterVO voUDMT, UserVO _UserVO)
	{
		List list = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
				
			if(voUDMT.getAdditionMode()==OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE)
				list = opdEssentialDAOi.getTemplatesByUnitDesk(voUDMT, _UserVO);
			if(voUDMT.getAdditionMode()==OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE)
				list= opdEssentialDAOi.getTemplatesByUnitSeatDesk(voUDMT, _UserVO);
			if(voUDMT.getAdditionMode()==OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE)
				list= opdEssentialDAOi.getTemplatesByDesk(voUDMT, _UserVO);
			if(voUDMT.getAdditionMode()==OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE)
				list= opdEssentialDAOi.getTemplatesByUnitWard(voUDMT, _UserVO);
			if(voUDMT.getAdditionMode()==OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE)
				list= opdEssentialDAOi.getTemplatesByUnitWardSeat(voUDMT, _UserVO);
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

	
	
	
	// * Getting Clinical Template Data
	public List getClinicalTemplateData(OpdClinicalDetailVO voClnData, UserVO _UserVO)
	{
		List list = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getClinicalTemplateData(voClnData, _UserVO);
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

	// * Getting Previous Visit Dates List
	public List getPrevVisitDates(String _patCrNo, String _episodeCode, UserVO _userVO)
	{
		List list = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getPrevVisitDates(_patCrNo, _episodeCode, _userVO);
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

	// * Getting All Parameters List of given Templates
	public List getOpdDeskTemplatesAllParas(List tempIds, UserVO _userVO)
	{
		List list = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);

			String tempArr = "";
			for (int i = 0; i < tempIds.size(); i++)
				tempArr += "'" + tempIds.get(i).toString().trim() + "'" + ",";
			if (!tempArr.equals("")) tempArr = tempArr.substring(0, tempArr.length() - 1);
			list = opdEssentialDAOi.getOpdDeskTemplatesAllParas(tempArr, _userVO);
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

	// * Getting Template Parameter Names
	public List getOpdTemplParaNames(List paraIds, UserVO _userVO)
	{
		List list = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);

			String paraArr = "";
			for (int i = 0; i < paraIds.size(); i++)
				paraArr += "'" + paraIds.get(i).toString().trim() + "'" + ",";
			if (!paraArr.equals("")) paraArr = paraArr.substring(0, paraArr.length() - 1);
			list = opdEssentialDAOi.getOpdTemplParaNames(paraArr, _userVO);
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

	// * Getting Previous Visits In Between Given Dates
	public List getPrevVisitsInBetween(String _patCrNo, String _episodeCode, String _fromDate, String _toDate, UserVO _userVO)
	{
		List list = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getPrevVisitsInBetween(_patCrNo, _episodeCode, _fromDate, _toDate, _userVO);
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

	// * Getting OPD Clinical Data By Selected Parameters
	public List getOpdClinDataBySelParas(OpdClinicalDetailVO voOpdCD, String qryVisits, String paraQuery, UserVO _userVO)
	{
		List list = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getOpdClinDataBySelParas(voOpdCD, qryVisits, paraQuery, _userVO);
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

	/*public Map getServiceEssentials(UserVO _userVO, DailyPatientVO selectedPatientVO)
	{
		List lstDept = new ArrayList();
		List lstAllReqByCrNo = new ArrayList();
		List lstServiceArea = new ArrayList();
		String serviceDate = null;
		Map mp = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi _opdEssentialDAOi = new OpdEssentialDAO(tx);
			ServiceAreaEssentialDAOi _serviceAreaEssentialDAOi = new ServiceAreaEssentialDAO(tx);
			lstDept = _serviceAreaEssentialDAOi.getAllDepartmentWithServiceArea();
			serviceDate = _serviceAreaEssentialDAOi.getServiceDate();
			lstAllReqByCrNo = _opdEssentialDAOi.getAllServiceReqForCrNo(selectedPatientVO);
			ServiceAreaVO _serviceAreaVO = new ServiceAreaVO();
			_serviceAreaVO.setDeptCode(selectedPatientVO.getDepartmentCode());
			lstServiceArea = getDeptWiseServiceAreaList(_serviceAreaVO, _userVO);
			mp.put(OpdConfig.SERVICE_REQ_DATE, serviceDate);
			mp.put(ServiceAreaConfig.OPTION_DEPARTMENT, lstDept);
			mp.put(OpdConfig.SERVICE_ALL_SERVICE_REQ_DTL_BY_CRNO, lstAllReqByCrNo);
			mp.put(ServiceAreaConfig.OPTION_SERVICEAREA, lstServiceArea);
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
		return mp;
	}*/

	// get all service areas
	/*public List getDeptWiseServiceAreaList(ServiceAreaVO _serviceAreaVO, UserVO _userVO)
	{
		List lstServiceArea = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			// OpdEssentialDAOi essentialDAOi=new OpdEssentialDAO(tx);
			ServiceAreaEssentialDAOi _serviceAreaEssentialDAOi = new ServiceAreaEssentialDAO(tx);
			// lstServiceArea=essentialDAOi.getDeptWiseServiceAreaList(_serviceAreaVO,_userVO);
			lstServiceArea = _serviceAreaEssentialDAOi.getDeptWiseServiceAreaList(_serviceAreaVO, _userVO);

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
		return lstServiceArea;
	}*/

	/*public List getServiceAreaWiseServiceList(ServiceVO _serviceVO, UserVO _userVO)
	{
		List lstService = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			ServiceAreaEssentialDAOi _serviceAreaEssentialDAOi = new ServiceAreaEssentialDAO(tx);
			lstService = _serviceAreaEssentialDAOi.getServiceAreaWiseServiceList(_serviceVO, _userVO);
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
		return lstService;
	}*/

	// Getting template details and service Area and service Details
	/*public Map TemplateDtl(ServiceAreaVO _serviceAreaVO, ServiceVO _serviceVO, UserVO _userVO)
	{
		List lstService = new ArrayList();
		Map mp = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstServiceArea = new ArrayList();
		String templateId;

		try
		{
			tx.begin();
			ServiceAreaEssentialDAOi _serviceAreaEssentialDAOi = new ServiceAreaEssentialDAO(tx);
			lstServiceArea = _serviceAreaEssentialDAOi.getDeptWiseServiceAreaList(_serviceAreaVO, _userVO);
			lstService = _serviceAreaEssentialDAOi.getServiceAreaWiseServiceList(_serviceVO, _userVO);
			templateId = _serviceAreaEssentialDAOi.getTemplateId(_serviceVO, _userVO);
			mp.put(ServiceAreaConfig.OPTION_SERVICEAREA, lstServiceArea);
			mp.put(ServiceAreaConfig.OPTION_SERVICE, lstService);
			mp.put(ServiceAreaConfig.TEPLATEID, templateId);
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
		return mp;
	}*/

	// * Getting Desk Type Description
	public String getDeskTypeDesc(String _deskType, UserVO _userVO)
	{
		String deskTypeDesc = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAOi = new OpdEssentialDAO(tx);
			deskTypeDesc = opdEssentialDAOi.getDeskTypeDesc(_deskType, _userVO);
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
		return deskTypeDesc;
	}

	public Map getPatientBelongingEssentials(String patCrNo,UserVO _userVO)
	{
		List belongingList;
		Map essentialMap = new HashMap();
		PatientBelongingVO[] patBelongingVO = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			belongingList = opdEssentialDAO.getBelongingList(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALBO_BELONGING_LIST, belongingList);

			patBelongingVO = opdEssentialDAO.getPatientBelongingDetails(patCrNo,_userVO);
			essentialMap.put(OpdConfig.PATIENT_BELONGING_DETAILS_VOS, patBelongingVO);
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

	public AudioVideoMasterVO[] getAudioVideoEssentials(String _unitCode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		AudioVideoMasterVO[] audioVideoMasterVO=null;
		try
		{
			tx.begin();
			
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			audioVideoMasterVO = opdEssentialDAO.getAudioVideoEssentials(_unitCode, _userVO);
			
			if(audioVideoMasterVO==null)
				audioVideoMasterVO = opdEssentialDAO.getAudioVideoDefaultEssentials(_userVO);
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
		return audioVideoMasterVO;
	}

	public List getDeskMenuBasedOnDeskType(String _deskType, UserVO _UserVO)
	{
		List listDeskMenu = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			listDeskMenu = daoOpdEssentail.getDeskMenuBasedOnDeskType(_deskType, _UserVO);
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
		return listDeskMenu;
	}

	public DeskMenuMacroMstVO[] getMacroHead(String _deskType, String _deskMenu, UserVO _UserVO)
	{
		DeskMenuMacroMstVO[] deskMenuMacroMstVO;
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			deskMenuMacroMstVO = daoOpdEssentail.getMacroHead(_deskType, _deskMenu, _UserVO);
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
		return deskMenuMacroMstVO;
	}

	public List getAllUnit(UserVO _UserVO)
	{
		List listAllUnit = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			listAllUnit = daoOpdEssentail.getAllUnit(_UserVO);
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
		return listAllUnit;
	}

	public void deleteMacroHead(DeskMenuMacroMstVO _deskMenuMacroMstVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DeskMenuMacroMasterDAO deskMenuMacroMasterDAO = new DeskMenuMacroMasterDAO(tx);
			deskMenuMacroMasterDAO.deleteMacroHead(_deskMenuMacroMstVO, _UserVO);
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

	public List getAllImageDesc(UserVO userVO)
	{
		List allImageDesc = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			allImageDesc = daoOpdEssentail.getAllImageDescWithColorCode(userVO);
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
		return allImageDesc;
	}

	public Map getUnitNotInTable(UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List unitList = null;
		List deptLst=null;
		List allImageDescLst=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			
			unitList = daoOpdEssentail.getUnitNotInTable(userVO);
			essentialMap.put(OpdConfig.LIST_UNITS_NOTIN_TABLE, unitList);
			
			deptLst = daoOpdEssentail.getAllClinicalDepartmentList(userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, deptLst);
			
			allImageDescLst=daoOpdEssentail.getAllImageDescWithColorCode(userVO);
			essentialMap.put(OpdConfig.ESSENTIAL_BO_OPTION_LIST_ALL_IMAGE_DESC,allImageDescLst);

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
		return essentialMap;
	}

	public void addImageDescription(String imageDesc, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			daoOpdEssentail.addImageDescription(imageDesc, userVO);
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
	}

	// Getting Image Examination Essentials
	public Map getImageExaminationEssentials(String _unitCode, OpdPatientImageDtlVO _patImgVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssentail = new HashMap();

		List<ImageMasterVO> lstImages = null;
		List<ImagePointerMasterVO> lstImagePointers = null;
		List<OpdPatientImageDtlVO> lstPrevImages=null;

		try
		{
			tx.begin();
			ImageMasterDAOi imageDAO = new ImageMasterDAO(tx);
			UnitImageMasterDAOi unitImageDAO = new UnitImageMasterDAO(tx);
			lstImages = unitImageDAO.getOPDImagesListOfUnit(_unitCode, _userVO);
			if(lstImages==null || lstImages.size()==0)
				lstImages = imageDAO.getDefaultImagesList(_userVO);
			mpEssentail.put(OpdConfig.OPD_ESSENTIAL_IMAGES_OF_DEPTUNIT, lstImages);
			
			ImagePointerMasterDAOi imagePointerDAO = new ImagePointerMasterDAO(tx);
			UnitImageDescMasterDAOi unitImagePointerDAO = new UnitImageDescMasterDAO(tx);
			lstImagePointers = unitImagePointerDAO.getUnitsImageColorDesc(_unitCode, _userVO);
			if(lstImagePointers==null || lstImagePointers.size()==0)
				lstImagePointers = imagePointerDAO.getDefaultImagePointersList(_userVO);
			
			// * Getting Description List of Colors in String and Format
			// * Color1:Desc1#Color2:Desc2.....
			StringBuffer colorDesc = new StringBuffer("");
			if(lstImagePointers!=null && lstImagePointers.size()!=0)
			{
				for (ImagePointerMasterVO vo : lstImagePointers)
				{
					colorDesc.append(vo.getColor().replace("#", ""));
					colorDesc.append(":");
					colorDesc.append(vo.getImageDesc());
					colorDesc.append("#");
				}
				if (!colorDesc.toString().equals("")) colorDesc.setLength(colorDesc.length()-1);
			}
			mpEssentail.put(OpdConfig.OPD_ESSENTIAL_IMAGE_POINTERS_OF_DEPTUNIT, colorDesc.toString());

			OpdPatientImageDtlDAO opdPatImageDAO=new OpdPatientImageDtlDAO(tx);
			
			PatientDetailVO voDp = new PatientDetailVO();
			HelperMethods.populate(voDp, _patImgVO);
			voDp.setPatAdmNo(_patImgVO.getAdmissionNo());
			lstPrevImages = opdPatImageDAO.getOPDPatOldImagesList(_userVO, voDp, OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED);
			mpEssentail.put(OpdConfig.OPD_ESSENTIAL_OLD_ADDED_IMAGES_OF_DEPTUNIT, lstPrevImages);
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
		return mpEssentail;
	}

	// Getting Image Log Detail
	public OpdPatientImageDtlVO getImageLogDetail(String _imageFileName, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		OpdPatientImageDtlVO vo=null;
		try
		{
			tx.begin();
			
			OpdPatientImageDtlDAOi opdPatImageDAO=new OpdPatientImageDtlDAO(tx);
			vo = opdPatImageDAO.getImagesLogDetail(_imageFileName, _userVO);
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
		return vo;
	}

	// * Getting Images List of Current Unit
	public List getOPDImagesListOfUnit(String _unitCode, UserVO _UserVO)
	{
		List listDeskMenu = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			listDeskMenu = daoOpdEssentail.getOPDImagesListOfUnit(_unitCode, _UserVO);
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
		return listDeskMenu;
	}

	// * Getting Editor Essentials e.g. Color-Description List 
	// * Getting Description List of Colors in String and Format
	// * Color1:Desc1#Color2:Desc2.....
	public String getUnitsImageColorDesc(String _unitCode, UserVO _UserVO)
	{
		String colorDesc = "";
		List lstColorDesc = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			lstColorDesc = daoOpdEssentail.getUnitsImageColorDesc(_unitCode, _UserVO);
			for (Object o : lstColorDesc)
			{
				Entry entObj = (Entry) o;
				colorDesc += entObj.getValue() + ":" + entObj.getLabel() + "#";
			}
			if (!colorDesc.equals("")) colorDesc = colorDesc.substring(0, colorDesc.length() - 1);
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
		return colorDesc;
	}

	//* Getting Unit Image Master ADD Essentials
	public Map getAddUnitImageMasterEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstImages = new ArrayList();
		List notAddedUnits = new ArrayList();
		List lstDept = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();

			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			lstDept = opdEssentialDAO.getAllClinicalDepartmentList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDept);
			//lstUnit = opdEssentialDAO.gettingAllClinicalUnitList(_userVO);

			lstImages = opdEssentialDAO.getAllImageList(_userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_ALL_IMAGE_LIST, lstImages);

			notAddedUnits = opdEssentialDAO.getNotAddedUnitList(_userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_NOT_ADDED_UNIT_LIST, notAddedUnits);
			

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

	//* Getting Unit Image Master MODIFY Essentials
	public Map getModifyUnitImageMasterEssentials(String _deptUnitCode, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List allImages = new ArrayList();
		List selectedImages = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			allImages = opdEssentialDAO.getAllImageList(_userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_ALL_IMAGE_LIST, allImages);

			selectedImages = opdEssentialDAO.getSelectedImages(_deptUnitCode, _userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_SELECTED_IMAGE_LIST, selectedImages);
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

	public List getAllAudioVideoFile(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List allAVFile=null;
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			allAVFile=opdEssentialDAO.getAllAudioVideoFile(userVO);
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
		return allAVFile;
	}
	

	public List getAllAudioVideoFileHeader(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List allAVFile=null;
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			allAVFile=opdEssentialDAO.getAllAudioVideoFileHeader(userVO);
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
		return allAVFile;
	}
	
	public List getAllUnitNotInTable(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstUnit=null;
		//List lstDept=new ArrayList();
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			lstUnit=opdEssentialDAO.getAllUnitNotInTable(userVO);
			
			//lstDept = opdEssentialDAO.getAllClinicalDepartmentList(userVO);
			
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
		return lstUnit;
	}

	
	public List getAllDept(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstDept=new ArrayList();
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			lstDept = opdEssentialDAO.getAllClinicalDepartmentList(userVO);
			
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
		return lstDept;
	}

	// Getting Casulaity Desk Essentials
	public List csultyDeskEssentials(UserVO _userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getCsultyUnitsBySeatId(_userVO);
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


	// Getting Casuality Patients List
	public PatientDetailVO[] getCsultyPatientList(String _unitCode, String _roomCode, UserVO _userVO)
	{
		PatientDetailVO[] dailyPatientVOs;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			dailyPatientVOs = dailyPatientDAO.getCsultyPatientsBySeatID(_unitCode, _roomCode, _userVO);
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
		return dailyPatientVOs;
	}

	// Getting Roles List
	public List getRoleList(UserVO _userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getRoleList(_userVO);
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
	
	// Getting ICD Hospital Disease Essentials
	public  List getIcdHospitalMasterEssentails(UserVO _userVO)
	{
		
		List lst=new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoobj =new OpdEssentialDAO(tx);
			lst=daoobj.getIcdHospitalMasterEssentails(_userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: "+e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			System.out.println("error.... Opd Master BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return lst;
	}

	// Getting ICD Disease List By Hospital Disease Code
	public  List fetchDiseaseList(IcdHospitalMasterVO _diseaseMasterVO,UserVO _userVO)
		{
		
		List lst=new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoobj =new OpdEssentialDAO(tx);
			lst=daoobj.fetchDiseaseList(_diseaseMasterVO,_userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: "+e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			System.out.println("error.... Opd Master BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return lst;
	}

	/*public Apt_slotDtlVO getNextSlotDate(Apt_slotDtlVO dtlVO, UserVO uservo) {
		return null;
	}*/

	/*public List getServiceWiseParameters(ServiceVO _servicevo, UserVO _uservo) {
		return null;
	}*/

	/*public Map getSlotDtl(Apt_slotDtlVO dtlVO, UserVO userVO) {
		return null;
	}*/
	
	public List getUnitExceptAssignedByDeskType(UserDeskMenuMasterVO _voDeskMapping, UserVO _voUser)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			list = opdEssentialDAO.getUnitExceptAssignedByDeskType(_voDeskMapping, _voUser);
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
	
	public boolean getisDefault(DeskMasterVO _deskMstVO,UserVO _userVO)
	{
		boolean defaultValue;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			defaultValue = daoOpdEssentail.getisDefault(_deskMstVO,_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
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
		return defaultValue;
	}	
	
	
	public boolean getisDefaultGlobal(DeskMasterVO _deskMstVO,UserVO _userVO)
	{
		boolean defaultValue;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOpdEssentail = new OpdEssentialDAO(tx);
			defaultValue = daoOpdEssentail.getisDefaultGlobal(_deskMstVO,_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
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
		return defaultValue;
	}	
	
	public List getAllGroupList(UserVO _userVO)
	{
		List listGroup;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			listGroup = opdEssentialDAOi.getAllGroupList(_userVO); 
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
		return listGroup;
	}

	
	public List getUnitExceptTemplate(UserVO _userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getUnitExceptTemplate(_userVO);
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


	/* (non-Javadoc)
	 * Getting Hospital Disease and Department and Unit List Essentials
	 * and getting the Hospital Disease and SubDisease List
	 * @see opd.bo.OpdEssentialBOi#getDeptHosDisEssential(hisglobal.vo.UserVO)
	 */
	public Map getDeptHosDisEssential(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List hosdisDiseaseList = null;
		List hosdisSubDiseaseList = null;
		List departmentList = null;
		List unitList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			EssentialDAO ObjEssDao = new EssentialDAO(tx);

			departmentList = ObjEssDao.getAllDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT, departmentList);

			unitList = ObjEssDao.getAllUnitBasedOnSeatID(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_SEATID, unitList);

			hosdisDiseaseList = opdEssDAO.getHosDisDiseaseList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_HOSDIS_DISEASE, hosdisDiseaseList);

			hosdisSubDiseaseList = opdEssDAO.getHosDisSubDiseaseList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_HOSDIS_SUBDISEASE, hosdisSubDiseaseList);

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


	//Function used for displaying the Department Hospital Diseases Removal Diseases
	public Map getDeptHosDisRemovalDetails(String _choice, String _code, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List hosdisDiseaseList = null;
		List hosdisSubDiseaseList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);

			if (_choice.equals(OpdConfig.CHOICE_DEPARTMENT))
			{
				hosdisDiseaseList = opdEssDAO.getHosDisDiseaseListRemovalDeptWise(_code, _userVO);
				essentialMap.put(OpdConfig.DISEASE_LIST_FOR_REMOVAL, hosdisDiseaseList);
				hosdisSubDiseaseList = opdEssDAO.getHosDisSubDiseaseListRemovalDeptWise(_code, _userVO);
				essentialMap.put(OpdConfig.SUB_DISEASE_LIST_FOR_REMOVAL, hosdisSubDiseaseList);
			}
			if (_choice.equals(OpdConfig.CHOICE_UNIT))
			{
				hosdisDiseaseList = opdEssDAO.getHosDisDiseaseListRemovalUnitWise(_code, _userVO);
				essentialMap.put(OpdConfig.DISEASE_LIST_FOR_REMOVAL, hosdisDiseaseList);
				hosdisSubDiseaseList = opdEssDAO.getHosDisSubDiseaseListRemovalUnitWise(_code, _userVO);
				essentialMap.put(OpdConfig.SUB_DISEASE_LIST_FOR_REMOVAL, hosdisSubDiseaseList);
			}

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

	
	
	// * Getting Essentials for Adding into User Desk Unit Ward Mapping Master
	public Map getAddUserDeskUnitWardMappingMasterEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstDept = new ArrayList();
		List lstUnit=new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			
			lstDept = objDAO.getAllClinicalDepartmentList(_userVO);
			lstUnit = objDAO.gettingAllClinicalUnitList(_userVO);
	
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDept);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_UNITS_MAPPING_MASTER, lstUnit);
			
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
	
	
	public List getUnitExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO _userVO)
	{
		
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getUnitExceptAssignedByDeskTypeForUnitWardMappingMaster(_deskType, _userVO);
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
		
	public List getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(String _deskType,UserVO _userVO,String deptCode)
	{
		
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getWardExceptAssignedByDeskTypeForUnitWardMappingMaster(_deskType, _userVO,deptCode);
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
	
	public List getWardExceptAssignedByDeskType(String _deskType,UserVO _userVO,String UnitId)
	{
		
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getWardExceptAssignedByDeskType(_deskType, _userVO,UnitId);
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

	
	public List getWardExceptAssignedByDeskTypeForUnitWardSeat(String _deskType,UserVO _userVO,String UnitId)
	{
		
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getWardExceptAssignedByDeskTypeForUnitWardSeat(_deskType, _userVO,UnitId);
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

	
	public List getAllWardInUnitWardSeatMode(String _deskType,UserVO _userVO,String UnitId)
	{
		
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getAllWardInUnitWardSeatMode(_deskType, _userVO,UnitId);
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

	
	
	
	

	// * Getting Seats not assigned to given Wards
	// * Returning List of Seats that are not yet assigned a desk/template for given wards in List
	public List getSeatsByNotWards(String[] _WardsList, String deskType, UserVO _userVO, String groupCode)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getSeatsByNotWards(_WardsList, deskType, _userVO,groupCode);
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
	
	// * Getting UserDesk Unit Ward Mapping Master Record
	public UserDeskUnitWardMappingMasterVO getModifyViewUserDeskUnitWardMappingMstVO(UserDeskUnitWardMappingMasterVO _UserDeskVO, UserVO _UserVO)
	{
		UserDeskUnitWardMappingMasterVO _voUsrDeskunitWardMappingMaster;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UserDeskUnitWardMappingMasterDAO userDeskDAOi = new UserDeskUnitWardMappingMasterDAO(tx);
			
				_voUsrDeskunitWardMappingMaster = userDeskDAOi.fetchRecordForUnitWardSeatWise(_UserDeskVO, _UserVO);
			
			
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
		return _voUsrDeskunitWardMappingMaster;
	}
	
	//Getting Service Type List
	public List getServiceTypeList(UserVO _UserVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		List list=new ArrayList();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list=opdEssentialDAOi.getServiceTypeList(_UserVO);
			
		}
		catch(HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch(Exception e)
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

	public List getAllAllergySite(UserVO userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getAllAllergySite(userVO);
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
	
	public Map getCommonNAllergyTypeSymptom(String allergyTypeCode,UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List lstCommonSymp = new ArrayList();
		List lstallergyTypeSymp=new ArrayList();
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			lstCommonSymp = objDAO.getACommonSymptomList(userVO);
			essentialMap.put(OpdConfig.ALLERGY_COMMON_SYMPTOM_LIST, lstCommonSymp);
	
			lstallergyTypeSymp = objDAO.getAllergyTypeSymptomList(allergyTypeCode,userVO);
			essentialMap.put(OpdConfig.ALLERGY_TYPE_SYMPTOM_LIST, lstallergyTypeSymp);
			
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
	
	public Map getEssentials(ConsentRequestVO consentRequestVO,UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List relationList=new ArrayList(); 
		ConsentRequestVO[] _consentRequestVO;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			/*
			 * This is in case of consent offline inbox
			 * 
			patientDetailVO=episodeConsentDtlDAO.getPatInfoByCrNoAndTodayVisit(consentRequestVO, _userVO);
			
			consentRequestVO.setEpisodeCode(patientDetailVO.getEpisodeCode());
			consentRequestVO.setEpisodeVisitNo(patientDetailVO.getEpisodeVisitNo());
			*/
			_consentRequestVO=objDAO.getConsentRequestVOList(consentRequestVO, _userVO);
			relationList=objDAO.getAllRelationList(_userVO);
			
			essentialMap.put(OpdConfig.CONSENT_REQUEST_VO_LIST, _consentRequestVO);
			essentialMap.put(OpdConfig.ALL_RELATIONSHIP_LIST, relationList);
			
			
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
	
	public UserDeskMenuMasterVO getSeats(UserDeskMenuMasterVO _voUDM, UserVO _userVO)
	{
		UserDeskMenuMasterVO seat;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			seat = opdEssentialDAOi.getSeats(_voUDM, _userVO);
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
		return seat;
	}

	//* Getting Unit Name
	public UserDeskMenuTemplateMasterVO getUnitName(String _deptUnitCode,UserVO _UserVO)
	{
		
		UserDeskMenuTemplateMasterVO unitName=null;
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitMasterDAO daoobj =new UnitMasterDAO(tx);
			unitName=daoobj.getUnitName(_deptUnitCode,_UserVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: "+e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			System.out.println("error.... Opd Master BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return unitName;
	}
	
	//Getting wards list for userDeskUnitWardMapping Master
	public UserDeskUnitWardMappingMasterVO gettingWards(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO)
	{
		UserDeskUnitWardMappingMasterVO seat;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			seat = opdEssentialDAOi.gettingWards(_voUDMT, _userVO);
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
		return seat;
	}
	
	public UserDeskUnitWardMappingMasterVO gettingSeats(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO)
	{
		UserDeskUnitWardMappingMasterVO seat;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			seat = opdEssentialDAOi.gettingSeats(_voUDMT, _userVO);
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
		return seat;
	}
	
	public void updateDeskUnitWardMappingMaster(UserDeskUnitWardMappingMasterVO _voUDMT, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			opdEssentialDAO.updateTable(_voUDMT, _userVO);
			
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
	
	public Map getEpisodeRoomChangeEssential(UserVO _userVO,String _unitCode,String _roomCode,PatientDetailVO patDtlVO)
	{
		Map essentialMap = new HashMap();
		List roomList = null;
        String oldRoomName="";
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			
			roomList = opdEssDAO.getUnitRoomList(_userVO,_unitCode,_roomCode,patDtlVO);
			oldRoomName=opdEssDAO.getOldRoomName(_userVO, _unitCode, _roomCode);
			
			essentialMap.put(OpdConfig.ESSENTIALBO_LIST_CHANGE_ROOM, roomList);
			essentialMap.put(OpdConfig.ESSENTIALBO_OLD_ROOM_NAME, oldRoomName);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
	

	public List<TemplateMasterVO> getAllTemplatesNotAdded(String deskId,UserVO userVO)
	{
		List<TemplateMasterVO> templateMstVOs;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			templateMstVOs = opdEssentialDAOi.getAllTemplatesNotAdded(deskId, userVO);
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
		return templateMstVOs;
	}

	public List getDeskListByUnit(String unitCode,UserVO userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getDeskListByUnit(unitCode, userVO);
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
	
	public Map getAllUnitForMapping(UserVO userVO)
	{
		List list;
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getAllUnitForMapping(userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_UNIT_FOR_MAPPING, list);
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
		return essentialMap;
	}
	
	public Map getUnitSeatWise(UserVO userVO)
	{
		List list;
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getUnitForSeatWise(userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_UNIT_SEAT_WISE, list);
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
		return essentialMap;
	}
	
	public List getSeatListByUnit(String _deskId, String unitCode, UserVO userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getSeatListByUnitForUnitSeat(_deskId, unitCode, userVO);
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
	
	public List getDeskListByUnitNSeat(String seatId,String unitCode,UserVO userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getDeskListByUnitNSeat(seatId,unitCode, userVO);
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
	
	public Map getUnitForWardWise(UserVO userVO)
	{
		List list;
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getUnitForWardWise(userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_UNIT_WARD_WISE, list);
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
		return essentialMap;
	}
	
	public Map getUnitForWardSeatWise(UserVO userVO)
	{
		List list;
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getUnitForWardSeatWise(userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_UNIT_WARD_SEAT_WISE, list);
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
		return essentialMap;
	}
	
	public List getWardListByUnit(String _deskId, String unitCode, UserVO userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getWardListByUnit(_deskId, unitCode, userVO);
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
	
	public List getDeskListByUnitNWard(String wardCode,String unitCode,UserVO userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getDeskListByUnitNWard(wardCode,unitCode, userVO);
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
	
	public List getWardListByUnitForUWS(String _deskId, String unitCode, UserVO userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getWardListByUnitForUWS(_deskId, unitCode, userVO);
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
	
	public List getSeatListByUnitNWard(String _deskId, String wardCode, String unitCode, UserVO userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getSeatListByUnitNWard(_deskId, wardCode, unitCode, userVO);
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
	
	public List getDeskListByUnitNWardNSeat(String seatId,String wardCode,String unitCode,UserVO userVO)
	{
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi = new OpdEssentialDAO(tx);
			list = opdEssentialDAOi.getDeskListByUnitNWardNSeat(seatId,wardCode,unitCode, userVO);
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

	public Map getDeskMenuMasterEssentails(UserVO _userVO)
{
	Map essentialMap = new HashMap();
	List treatmentCategoryList = null;
	List deskTypeList=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			
			treatmentCategoryList = opdEssDAO.getTemplateCategory(_userVO);
			deskTypeList=opdEssDAO.getDeskType(_userVO);
						
			essentialMap.put(OpdConfig.ESSENTIALBO_LIST_TEMPLATE_CATEGORY, treatmentCategoryList);
			essentialMap.put(OpdConfig.ESSENTIALBO_LIST_DESK_TYPE, deskTypeList);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
	
	
	
	
	
	
	public Map getDrugDoseMasterEssentails(UserVO _userVO)
	{
		
		Map essentialMap = new HashMap();
		List lstDrugItemTypes = null;
		PatientDetailVO patVO = new PatientDetailVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAO = new HisDAO("OPD", "OpdEssentialBO");

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			//Commented By Chetan 
		//	itemTypeList = opdEssDAO.getItemTypeList(_userVO);
			
			lstDrugItemTypes = opdEssDAO.getItemTypeList(hisDAO , OpdConfig.lstItemTypes, patVO, _userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE, lstDrugItemTypes);
			
						
			//essentialMap.put(OpdConfig.ESSENTIALBO_ITEM_TYPE_LIST, itemTypeList);
				
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			if(hisDAO!=null)
				hisDAO.free();
			hisDAO=null;
			tx.close();
		}
		return essentialMap;
	}

	/**
	 * Getting Patient Treatment Detail Essentials
	 * @param _patCrNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO User VO 
	 * @return Map of Essentials
	 */
	public Map getPatTreatmentDetailEssential(String _patCrNo, String _episodeCode, String depUnitCode ,String genderType, PatientDetailVO _patDetailVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List<Entry> lstDosageFreq=null;
		List<Entry> lstDrugs=null;
		List<Entry> lstEXTTreatment=null;
		List<Entry> lstDietType=null;
		List<DrugDoseVO> lstDrugDoses = null;
		List lstDrugRoutes=null;
		List<Entry> lstDrugItemTypes = null;
		List<PatDrugTreatmentDetailVO> lstPrevDrugDetail = null;
		List lstPrevRestDetail = null;
		List<PatExtTreatmentDetailVO> lstPrevExtTretDetail=null;
		List<PatDietAdviceDetailVO> lstPrevDietDetail=null;
		List prevDrugSchedule=null;
		DrugFrequencyMstVO[] drugFrequencyMstVO;
		List otherInstructionList=null;
		List oneTimeActivityList=null;
		List prevOtherInstAndActivity=null;
		List otherInstListByGender=null;
		List activityListByGender=null;
		List serachDrugList=null;
		List<PatDrugTreatmentDetailVO> lstPrevDrugDetailForLog = null;
		List drugProfileDrugDetailLst=null;
		List drugListForSearch=null;
		List lstDrugAdminFlags=null;
		List lstPatPregCat=null;
		Map<String,String> drugStockDtl=new HashMap();
		
		String mode=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAO = new HisDAO("OPD", "OpdEssentialBO");
		try
		{
			tx.begin();
			OpdEssentialDAO dao = new OpdEssentialDAO(tx);
			
			lstDosageFreq = dao.getDosageFrequecy(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY, lstDosageFreq);
			
			drugFrequencyMstVO = dao.getDrugFrequencyVOList(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY, drugFrequencyMstVO);
			
			//lstDrugs = dao.getDrugList(_userVO);
			
			PatientDetailVO patVO = new PatientDetailVO();
			patVO.setPatCrNo(_patCrNo);
			patVO.setEpisodeCode(_episodeCode);
			patVO.setPatGenderType(genderType);
			patVO.setDepartmentUnitCode(depUnitCode);
			
			lstDrugs = dao.getGenericDrugListDetail(hisDAO , OpdConfig.lstDrugs, patVO, _userVO);   //for Generic Drug List
			
	     // lstDrugs = dao.getDrugListDetail(hisDAO , OpdConfig.lstDrugs, patVO, _userVO);      // for Drug Brand List 
			
			
			
			if (lstDrugs==null || lstDrugs.size()==0 )
			{
				//lstDrugs = dao.getDrugList(_userVO);
				lstDrugs = dao.getGenericDrugListDetail(hisDAO , OpdConfig.lstDrugs, patVO, _userVO);
				
			}
			//added by shruti shail for drug stock details
			drugStockDtl = dao.getDrugStockDetail(hisDAO , OpdConfig.lstDrugs, patVO, _userVO);
			//essentialMap.put(OpdConfig.DRUG_STOCK_LIST, drugStockDtl);
			for(Entry i : lstDrugs)
			{
				String val = i.getValue();
				String id[] = i.getValue().split("#");
				String itemid = id[4];
				String Stock=null;
				if(drugStockDtl.containsKey(itemid))
					Stock = drugStockDtl.get(itemid);
				else
					Stock = "0";
				i.setValue(val+"#"+Stock);
			}
			System.out.println(lstDrugs);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS, lstDrugs);
			
			//serachDrugList=dao.getDrugListForSearch(_userVO);
			//serachDrugList=dao.getDrugListDetailForSearch(_userVO, depUnitCode);
			//serachDrugList=dao.getDrugListDetailForSearch(hisDAO , _patDetailVO, _userVO);
			
			serachDrugList=dao.getGenericDrugListDetail(hisDAO , OpdConfig.serachDrugList, patVO, _userVO);
			if(serachDrugList==null || serachDrugList.size()==0)
			{
				//serachDrugList=dao.getDrugListForSearch(hisDAO,_userVO);
				serachDrugList=dao.getGenericDrugListDetail(hisDAO , OpdConfig.serachDrugList, patVO, _userVO);
			}
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS_FOR_SEARCH, serachDrugList); 

			lstDrugDoses = dao.getDrugDosesList(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES, lstDrugDoses);
			
			lstDrugRoutes = dao.getDrugRouteList(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE, lstDrugRoutes);

	
			lstDrugAdminFlags = dao.getDrugAdministrationFlagsList(hisDAO , OpdConfig.lstDrugAdminFlag);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ADMIN_FLAGS, lstDrugAdminFlags);

			lstPatPregCat = dao.getPatPregnantCategoryList(hisDAO , OpdConfig.lstPregCat);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_PREGNANT_CATEGORY, lstPatPregCat);
			
			//Commented By: Chetan Sharma 
           //Date: 2015_12_4
	     	//	lstDrugItemTypes = dao.getItemTypeList(_userVO);
			
			
			lstDrugItemTypes = dao.getItemTypeList(hisDAO , OpdConfig.lstItemTypes, patVO, _userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE, lstDrugItemTypes);

			lstPrevDrugDetail = dao.getPrevPatDrugDetail(_patCrNo, _episodeCode, _userVO);
			essentialMap.put(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST, lstPrevDrugDetail);
			
			lstPrevDrugDetailForLog=dao.getPrevPatDrugDetailForLog(_patCrNo, _episodeCode, _userVO);
			essentialMap.put(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG, lstPrevDrugDetailForLog);
			
			lstPrevRestDetail=dao.getPrevPatRestDetail(_patCrNo, _episodeCode, _userVO);
			essentialMap.put(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_REST_DETAIL_LIST, lstPrevRestDetail);
			
			lstEXTTreatment = dao.getEXTTreatmentList(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_EXT_TREATMENT, lstEXTTreatment);
			
			otherInstructionList=dao.getOtherInstructionList(genderType, depUnitCode, _userVO);
			essentialMap.put(OpdConfig.OTHER_INSTRUCTION_LIST_BY_DEPTUNITCODE_AND_GENDER, otherInstructionList);
			
			oneTimeActivityList=dao.getOneTimeActivityList(genderType, depUnitCode, _userVO);
			essentialMap.put(OpdConfig.ONE_TIME_ACTIVITY_LIST_BY_DEPTUNITCODE_AND_GENDER, oneTimeActivityList);
			
			otherInstListByGender=dao.getAllOtherInstructionList(genderType, depUnitCode, _userVO);
			essentialMap.put(OpdConfig.ALL_OTHER_INSTRUCTION_LIST_BY_GENDER, otherInstListByGender);
			
			activityListByGender=dao.getAllOneTimeActivityList(genderType, depUnitCode, _userVO);
			essentialMap.put(OpdConfig.ALL_ONE_TIME_ACTIVITY_LIST_BY_GENDER, activityListByGender);
			
			lstPrevExtTretDetail = dao.getPrevPatExtTreatmentDetail(_patCrNo, _episodeCode, _userVO);
			essentialMap.put(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST, lstPrevExtTretDetail);
			
			lstDietType=dao.getAllDietTypeList(_userVO);
			essentialMap.put(OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST, lstDietType);
			
			lstPrevDietDetail=dao.getPrevPatDietAdviceDetail(_patCrNo,  _episodeCode,_userVO);
			essentialMap.put(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DIET_ADVICE_DETAIL_LIST, lstPrevDietDetail);
			
			prevDrugSchedule=dao.getPrevDrugSchedule(_patCrNo,  _episodeCode,_userVO);
			essentialMap.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE, prevDrugSchedule);
			
			String maxEntryDate=dao.getMaxEntryDateFromDrugDetail(_patCrNo, _episodeCode, _userVO);
			essentialMap.put(OpdConfig.MAX_ENTRY_DATE_BY_CRNO, maxEntryDate);
			
			String maxEntryDateForExtTreat=dao.getMaxEntryDateFromExtTreatDetail(_patCrNo, _episodeCode, _userVO);
			essentialMap.put(OpdConfig.MAX_ENTRY_DATE_BY_CRNO_FOR_EXTTREAT, maxEntryDateForExtTreat);
			
			prevOtherInstAndActivity=dao.getPrevOtherInstructionDetail(_patCrNo, _episodeCode, _userVO);
			essentialMap.put(OpdConfig.PREV_OTHER_INST_AND_ACTIVITY_LIST, prevOtherInstAndActivity);
			
			drugProfileDrugDetailLst=dao.getDefaultDrugProfileForUnit(depUnitCode, _userVO);
			essentialMap.put(OpdConfig.DEFAULT_DRUG_PROFILE_DRUG_LIST, drugProfileDrugDetailLst);
			
			drugListForSearch=dao.getAllDrugListByDeptUnit(depUnitCode, _userVO);
			essentialMap.put(OpdConfig.DRUGLIST_LIST_FOR_SEARCH, drugListForSearch);
			
			

			//lstPrevDrugDetailForPrint=dao.getPrevPatDrugDetailForPrinting(_patCrNo, _episodeCode, _userVO);
			//essentialMap.put(OpdConfig.DRUG_LIST_FOR_PRINTING, lstPrevDrugDetailForPrint);

			/*synchronized (hisDAO)
			{
				hisDAO.fire();
			}*/
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
			tx.close();
		}
		return essentialMap;
	}
	
	public List<TemplateMasterVO> getAllTemplatesVO(UserVO userVO)
	{
		List<TemplateMasterVO> templateMstVOs;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			templateMstVOs = dao.getAllTemplatesVO(userVO);
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
		return templateMstVOs;
	}
	
	public DeskMenuMasterVO[] getMenuNameBasedOnDeskId(String deskId,UserVO userVO)
	{
		DeskMenuMasterVO[] deskMenuMstVOs;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			deskMenuMstVOs = dao.getMenuNameBasedOnDeskId(deskId, userVO);
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
		return deskMenuMstVOs;
	}
	
	// List of Alert Names that are not assigned to the Patient
	public List getAllPatAlerts(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstAlert=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			lstAlert=dao.getAllPatAlerts(crNo,userVO);
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
		return lstAlert;
	}
	
	public List getAdvice(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstAdvice=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO dao = new OpdEssentialDAO(tx);
			lstAdvice=dao.getListAllergyAdvice(userVO);
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
		return lstAdvice;
	}
	
	public String getCsultyTotalPatCount(UserVO userVO,String unitCode,String roomCode)
	{
		String count = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			count = dailyPatientDAO.getCsultyTotalPatCount(userVO, unitCode, roomCode);
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
		return count;
	}
	
	public String getCsultyTodayAdmPatCount(UserVO userVO,String unitCode,String roomCode)
	{
		String count = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			count = dailyPatientDAO.getCsultyTodayAdmPatCount(userVO, unitCode, roomCode);
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
		return count;
	}


	
	public List getUserBasedOnGroup(String groupCode,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstUser=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			lstUser=dao.getUserBasedOnGroup(groupCode,_userVO);
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
		return lstUser;
	}


	/**
	 * Getting Patient Episode Essentials
	 * @param _patProfileVO Patient Profile Process Details
	 * @param _userVO User Detail
	 * @return Map of Essentials of the Process
	 */
	public Map<String, Object> getPatientProfilesEssentials(PatientProfileDetailVO _patProfileVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List<ProfileAccessDetailVO> lstProfileAccesses = null;
		List<Entry> unitList = null;

		try
		{
			tx.begin();

			ProfileAccessDetailDAOi dao = new ProfileAccessDetailDAO(tx);
			lstProfileAccesses = dao.get(_patProfileVO.getProfileId(), _userVO);
			essentialMap.put(OpdConfig.PATIENT_PROFILE_ACCESS_PRIVILEDGES_LIST, lstProfileAccesses);
			
			EssentialDAO ObjEssDao = new EssentialDAO(tx);
			unitList = ObjEssDao.getAllUnit(_userVO);
			essentialMap.put(OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_UNIT_LIST, unitList);
			
			List _unitList=new ArrayList();
			_unitList.addAll(unitList);
			essentialMap.put(OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_ALL_UNIT_LIST, _unitList);
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
		return essentialMap;
	}

	/**
	 * Getting Serach Result of Users for the Profile Access Priviledges
	 * @param _mode Search Mode
	 * @param _str Search String 
	 * @param _userVO User Detail
	 * @return
	 */
	public List<UserVO> getSearchUsersForProfileAccessPrivil(String _mode, String _str, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<UserVO> lstSearchUsers = null;
		try
		{
			tx.begin();

			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			if(_mode.equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_USER_SEARCH_BY_USER_NAME))
			{
				lstSearchUsers = dao.getSearchUsersByUserName(_str, _userVO);
			}
			else if(_mode.equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_USER_SEARCH_BY_EMPLOYEE_ID))
			{
				lstSearchUsers = dao.getSearchUsersByEmpId(_str, _userVO);
			}
			else if(_mode.equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_USER_SEARCH_BY_EMPLOYEE_NAME))
			{
				lstSearchUsers = dao.getSearchUsersByEmpName(_str, _userVO);
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
		return lstSearchUsers;
	}

	
	public Map getOfflineConsentEssentials(ConsentRequestVO consentRequestVO,UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List relationList=new ArrayList(); 
		ConsentRequestVO[] _consentRequestVO;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			/*
			 * This is in case of consent offline inbox
			 * 
			patientDetailVO=episodeConsentDtlDAO.getPatInfoByCrNoAndTodayVisit(consentRequestVO, _userVO);
			
			consentRequestVO.setEpisodeCode(patientDetailVO.getEpisodeCode());
			consentRequestVO.setEpisodeVisitNo(patientDetailVO.getEpisodeVisitNo());
			*/
			_consentRequestVO=objDAO.getConsentRequestVOListByCrNo(consentRequestVO, _userVO);
			relationList=objDAO.getAllRelationList(_userVO);
			
			essentialMap.put(OpdConfig.CONSENT_REQUEST_VO_LIST, _consentRequestVO);
			essentialMap.put(OpdConfig.ALL_RELATIONSHIP_LIST, relationList);
			
			
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
	
	public  Map getDeskTypeMenuMappingMstEssentails(DeskTypeMenuMappingVO deskTypeMenuMappingVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap<String, Object>();
		List menuLst = null;
		
		try
		{
			tx.begin();

			OpdEssentialDAO daoEssen=new OpdEssentialDAO(tx);
			DeskTypeMenuMappingMstDAO daoObject=new DeskTypeMenuMappingMstDAO(tx);
			String deskTypeDesc =daoEssen.getDeskTypeDesc(deskTypeMenuMappingVO.getDeskType(), _userVO);
			menuLst=daoObject.getRemaningMenuList(deskTypeMenuMappingVO, _userVO);
			essentialMap.put(OpdConfig.ALL_MENU_LIST, menuLst);
			essentialMap.put(OpdConfig.DESC_TYPE_DESC, deskTypeDesc);
					
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
		return essentialMap;
	}

	
	public List getParameterForExtInv(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstParameter = null;
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			lstParameter=dao.getParameterForExtInv(userVO);
			
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
		return lstParameter;
	}

	public DisclaimerMstVO fetchDisclaimerDetails(String _deptUnitCode,String profileType,UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		DisclaimerMstVO disclaimerMstVOs=new DisclaimerMstVO();
		try
		{
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			tx.begin();
			disclaimerMstVOs = dao.fetchDisclaimerDetails(_deptUnitCode,profileType, _userVO);
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
		return disclaimerMstVOs;

	}

	public String getProfileBound(String _deskType,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String profileBound="";
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			profileBound=dao.getProfileBound(_deskType, userVO);
			
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
		return profileBound;
	}

	public String getProfileBound(String _deskt) {
		return null;
	}
		

	/**
	 * Getting Visit Summary Essentials
	 * @param _episodeVO Episode Detail VO
	 * @param _userVO User Detail VO
	 * @return Map of Essentials
	 */
	public Map getVisitSummaryEssentials(EpisodeVO _episodeVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<EpisodeVO> lstEpisodeDtl = new ArrayList<EpisodeVO>();
		List<EpisodeSummaryDetailVO> lstEpisodeSummaryDtl = new ArrayList<EpisodeSummaryDetailVO>();
		List<EpisodeKeywordsMasterVO> lstEpisodeKeywords = new ArrayList<EpisodeKeywordsMasterVO>();
		
		try
		{
			tx.begin();
			
			EpisodeDAOi episodeDAO = new EpisodeDAO(tx);
			EpisodeSummaryDAOi episodeSummaryDAO = new EpisodeSummaryDAO(tx);
			OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);
			/*EpisodeDiagnosisDAO episodeDiagnosisDAO = new EpisodeDiagnosisDAO(tx);*/
			
			// Episode All Visit Detail
			lstEpisodeDtl = episodeDAO.retrieveAllVisitsByEpisodeNo(_episodeVO, _userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST, lstEpisodeDtl);
			
			// Episode Current Visit Summary Detail
			lstEpisodeSummaryDtl = episodeSummaryDAO.getAllVisitSummaryByEpisodeVisit(_episodeVO, _userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST, lstEpisodeSummaryDtl);
			
			// Episode Keywords List Unit Wise
			//lstEpisodeKeywords = opdEssentialDAO.getEpisodeKeywordsUnitWise(_episodeVO.getDepartmentUnitCode(), _userVO);
			lstEpisodeKeywords = opdEssentialDAO.getAllEpisodeKeywordList(_userVO);
			
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_ALL_KEYWORDS_LIST, lstEpisodeKeywords);
			
			// Episode Visit Diagnosis Detail
			/*List<EpisodeDiagnosisVO> lstEpiVisitDiag = episodeDiagnosisDAO.getDiagnosisDtl(_episodeVO, _userVO);
			essentialMap.put(OpdConfig.OPD_EPISODE_VISIT_DIAGNOSIS_DETAIL, lstEpiVisitDiag);*/
			
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

	/**
	 * Getting Macros of given Process Id
	 * @param _processId Process Id
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getMacrosListByProcessId(String _processId, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstMacros = null;
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			lstMacros=dao.getMacrosByProcessId(_processId, _userVO);			
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
		return lstMacros;
	}

	/**
	 * Getting Macros of given Process Id & Unit 
	 * @param _processId Process Id
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getUnitMacrosByProcessId(String _processId, String _unitCode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstMacros = null;
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			lstMacros=dao.getUnitMacrosByProcessId(_processId, _unitCode, _userVO);			
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
		return lstMacros;
	}

	/**
	 * Getting Schedule Dates Unit User Date Wise
	 * @param _deptUnitCode Unit Code
	 * @param _userId User ID
	 * @param _date Date
	 * @param _userVO User Detail
	 * @return List of Schedule Dates
	 */
	public List<Entry> getOpdRosterSchedule(String _deptUnitCode, String _userId, String _date, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Entry> lstScheduleDates = null;
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			lstScheduleDates = dao.getOpdRosterScheduleDates(_deptUnitCode, _userId, _date, _userVO);			
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
		return lstScheduleDates;
	}
	
	public Map fetchRestrictedCategoryEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		//List patientCategoryList = null;
		List profileTypeList=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			//EssentialDAO ObjEssDao = new EssentialDAO(tx);
			ProfileTypeDAO daoObj=new ProfileTypeDAO(tx);
			//patientCategoryList = ObjEssDao.getPatientCategoryList(_userVO);
			//essentialMap.put(OpdConfig.PROFILE_RESTRICTED_CATEGORY_PATIENT_CATEGORY_LIST, patientCategoryList);
			profileTypeList=daoObj.getAllProfileTypes(_userVO);
			essentialMap.put(OpdConfig.PROFILE_TYPE_LIST, profileTypeList);
			
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
	
	public Map getIcdNHospitalDiagnosisEssential(UserVO _userVO, String _patCrNo, String _episodeCode)
	{
		Map essentialMap = new HashMap();
		List diagnosisTypeList = null;
		List hospitalDiagnosisCodeList = null;
		List icdDiagnosisCodeList = null;
		EpisodeDiagnosisVO[] episodeDiagVO = null;
		List diagnosisSiteCodeList = null;
		List morphologyCodeList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			EssentialDAO ObjEssDao = new EssentialDAO(tx);

			diagnosisTypeList = ObjEssDao.getDiagnosisTypeList(_userVO);
			essentialMap.put(RegistrationConfig.DIAGNOSIS_LIST, diagnosisTypeList);

			icdDiagnosisCodeList = opdEssDAO.getIcdDiagnosisCodeListByUnit(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_DIAGNOSIS_CODE_LIST_UNIT_WISE, icdDiagnosisCodeList);
			
			diagnosisSiteCodeList = opdEssDAO.getDiagnosisSiteCodeList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_DIAGNOSIS_SITE_LIST_UNIT_WISE, diagnosisSiteCodeList);

			morphologyCodeList = opdEssDAO.getMorphologyCodeList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_MORPHOLOGY_LIST_UNIT_WISE, morphologyCodeList);

			// //fetching the diagnosis record for previous visit///
			episodeDiagVO = opdEssDAO.getPrevDiagnosisDetail(_patCrNo, _episodeCode,_userVO);
			essentialMap.put(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO, episodeDiagVO);
			
			hospitalDiagnosisCodeList = opdEssDAO.getHospitalDiagnosisCodeList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST, hospitalDiagnosisCodeList);
			

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
	
	public Map getExtTreatMasterEssential(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		List listAllExtTreatList=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			lstDept = opdEssentialDAO.getAllClinicalDepartmentList(userVO);
			essentialMap.put(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST, lstDept);
			
			listAllUnit = opdEssentialDAO.getAllUnit(userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			
			listNotMappedUnit=opdEssentialDAO.getAllUnitNotMappedWithExtTreat(userVO);
			essentialMap.put(OpdConfig.NOT_MAPPED_ALL_UNIT_LIST, listNotMappedUnit);
			
			listAllExtTreatList=opdEssentialDAO.getAllExternalTreatment(userVO);
			essentialMap.put(OpdConfig.ALL_EXT_TREATMENT_LIST, listAllExtTreatList);
			
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
	public Map getDrugMasterEssential(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		List listAllExtTreatList=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			lstDept = opdEssentialDAO.getAllClinicalDepartmentList(userVO);
			essentialMap.put(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST, lstDept);
			
			listAllUnit = opdEssentialDAO.getAllUnit(userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			
			listNotMappedUnit=opdEssentialDAO.getAllUnitNotMappedWithDrugs(userVO);
			essentialMap.put(OpdConfig.NOT_MAPPED_ALL_UNIT_LIST, listNotMappedUnit);
			
			listAllExtTreatList=opdEssentialDAO.getAllDrugListValues(userVO);
			essentialMap.put(OpdConfig.ALL_EXT_TREATMENT_LIST, listAllExtTreatList);
			
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
	
	
	
	public Map getUnitExtTreatForModify(UnitExtTreatMstVO vo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		
		List listAllUnit=null;
		UnitExtTreatMstVO[] unitExtTreatVOArray=null;
		List listAllExtTreatList=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			listAllUnit = opdEssentialDAO.getAllUnit(userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			
			listAllExtTreatList=opdEssentialDAO.getAllExternalTreatment(userVO);
			essentialMap.put(OpdConfig.ALL_EXT_TREATMENT_LIST, listAllExtTreatList);
			
			unitExtTreatVOArray=opdEssentialDAO.getUnitExtTreatLstByDeptUnit(vo.getDeptUnitCode(), userVO);
			essentialMap.put(OpdConfig.MAPPED_UNIT_EXT_TREATMENT_VO_ARRAY, unitExtTreatVOArray);
			
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
	
	
	public Map getUnitDrugLisyForModify(UnitDrugMstVO vo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		
		List listAllUnit=null;
		UnitDrugMstVO[] unitDrugMstVOArray=null;
		List listAllExtTreatList=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			listAllUnit = opdEssentialDAO.getAllUnit(userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			
			listAllExtTreatList=opdEssentialDAO.getAllDrugListValues(userVO);
			essentialMap.put(OpdConfig.ALL_EXT_TREATMENT_LIST, listAllExtTreatList);
			
			unitDrugMstVOArray=opdEssentialDAO.getUnitDrugLstDetailByDeptUnit(vo.getDeptUnitCode(), userVO);
			essentialMap.put(OpdConfig.MAPPED_UNIT_EXT_TREATMENT_VO_ARRAY, unitDrugMstVOArray);
			
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
	
	
	public Map getPatientAddedAttendant(String strPatCrNo_p, String strEpisodeCode_p, UserVO strUserVO_p)
	{
		Map essentialMap = new HashMap();
		List<PatientFamilyDtlVO> patFamilyAttendantVOList=null;
		List<EpisodeAttendantDetailVO> lstPatAttendants = null;
		List relationList=new ArrayList();
		List attendantReasonList=new ArrayList(); 
		String str="";
		
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			PatientFamilyDtlDAOi patFamilyDAO=new PatientFamilyDtlDAO(tx);
			EpisodeAttendantDtlDAOi epiAttendantDAO = new EpisodeAttendantDtlDAO(tx);
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			
			patFamilyAttendantVOList=patFamilyDAO.getPatAttendantDtlByCrNo(strPatCrNo_p, strUserVO_p);
			essentialMap.put(OpdConfig.PATIENT_ALL_ATTENDANT_VO_LIST, patFamilyAttendantVOList);
			
			lstPatAttendants = epiAttendantDAO.getEpisodeAttendantsList(strPatCrNo_p, strEpisodeCode_p, strUserVO_p);
			essentialMap.put(OpdConfig.PATIENT_EPISODE_ATTENDANT_LIST, lstPatAttendants);

			relationList=objDAO.getAllRelationList(strUserVO_p);
			essentialMap.put(OpdConfig.ALL_RELATIONSHIP_LIST, relationList);
			
			attendantReasonList=objDAO.getAllAttendantReasonList(strUserVO_p);
			essentialMap.put(OpdConfig.ALL_ATTENDANT_REASON_LIST, attendantReasonList);
			
			str=objDAO.getPatientFatherMotherSpouceName(strPatCrNo_p);
			essentialMap.put(OpdConfig.PATIENT_FATHER_MOTHER_SPOUCE_NAME, str);
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
	
	public EpisodeVO[] getAllEpisodeOfThePatientTodayVisited(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] arrEpisodeVO = null;
		
		try
		{
			tx.begin();
			MedicalCertificateDAOi mcDAO=new MedicalCertificateDAO(tx);
			arrEpisodeVO=mcDAO.getAllEpisodeOfThePatientTodayVisited(crNo,userVO);
			
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
		return arrEpisodeVO;
	}
	
	
	public Map getUnitMacroMasterEssential(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		List listAllMacroList=null;
		List listAllMacroProcessLst=null;
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			lstDept = opdEssentialDAO.getAllClinicalDepartmentList(userVO);
			essentialMap.put(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST, lstDept);
			
			listAllUnit = opdEssentialDAO.getAllUnit(userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			
			listNotMappedUnit=opdEssentialDAO.getAllUnitNotMappedWithMacro(userVO);
			essentialMap.put(OpdConfig.NOT_MAPPED_ALL_UNIT_LIST, listNotMappedUnit);
			
			listAllMacroList=opdEssentialDAO.getAllMacro(userVO);
			essentialMap.put(OpdConfig.ALL_MACRO_LIST, listAllMacroList);
			
			listAllMacroProcessLst=opdEssentialDAO.getAllMacroProcessList(userVO);
			essentialMap.put(OpdConfig.ALL_MACRO_PROCESS_LIST, listAllMacroProcessLst);
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
	
	
	public Map getUnitExtTreatForModify(UnitWiseMacroMstVO vo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		
		List listAllUnit=null;
		UnitWiseMacroMstVO[] unitWiseMacroMstVO=null;
		List listAllMacroList=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			listAllUnit = opdEssentialDAO.getAllUnit(userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			
			listAllMacroList=opdEssentialDAO.getAllMacro(userVO);
			essentialMap.put(OpdConfig.ALL_MACRO_LIST, listAllMacroList);
			
			unitWiseMacroMstVO=opdEssentialDAO.getUnitMacroLstByDeptUnit(vo.getDeptUnitCode(), userVO);
			essentialMap.put(OpdConfig.MAPPED_UNIT_MACRO_VO_ARRAY, unitWiseMacroMstVO);
			
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
	
	
	public Map getPatientCategoryForProfileType(String profileType,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List addedPatientCategoryList=null;
		List allPatientCategory=null;
		
		try
		{
			tx.begin();
			ProfileRestrictedCatDAOi profileRestrcitedDAO = new ProfileRestrictedCatDAO(tx);
			EssentialDAO essentialDAO=new EssentialDAO(tx);
			
			allPatientCategory=essentialDAO.getPatientPrimaryCategory(_userVO);
			essentialMap.put(OpdConfig.ALL_PATIENT_PRIMARY_CATEGORY_LIST, allPatientCategory);
			
			ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO=new ProfileRestrictedCategoryMasterVO();
			profileRestrictedCategoryMasterVO.setProfileType(profileType);
			addedPatientCategoryList=profileRestrcitedDAO.getCategoryBasedOnProfileType(profileRestrictedCategoryMasterVO, _userVO);
			essentialMap.put(OpdConfig.PATIENT_PRIMARY_CATEGORY_MAPPED_WITH_PROFILE_TYPE_LIST, addedPatientCategoryList);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			//throw new HisRecordNotFoundException(e.getMessage());
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
	

	public Map getUnitImageDescForModify(UnitImageDescMasterVO vo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		
		List listAllUnit=null;
		UnitImageDescMasterVO[] unitImageDescMasterVO=null;
		List listallImageDesc=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
						
			listAllUnit = opdEssentialDAO.getAllUnit(userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			
			listallImageDesc=opdEssentialDAO.getAllImageDescWithColorCode(userVO);
			essentialMap.put(OpdConfig.ALL_IMAGE_DESCRIPTION_WITH_COLOR, listallImageDesc);
			
			unitImageDescMasterVO=opdEssentialDAO.getUnitImageDescLstByDeptUnit(vo.getUnitCode(), userVO);
			essentialMap.put(OpdConfig.MAPPED_UNIT_IMAGE_DESC_VO_ARRAY, unitImageDescMasterVO);
			
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
	
	
	public Map getUnitDrugListMasterEssential(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		List listAllDrugListList=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			lstDept = opdEssentialDAO.getAllClinicalDepartmentList(userVO);
			essentialMap.put(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST, lstDept);
			
			listAllUnit = opdEssentialDAO.getAllUnit(userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			
			listNotMappedUnit=opdEssentialDAO.getAllUnitNotMappedWithDrugList(userVO);
			essentialMap.put(OpdConfig.NOT_MAPPED_ALL_UNIT_LIST, listNotMappedUnit);
			
			listAllDrugListList=opdEssentialDAO.getAllDrugList(userVO);
			essentialMap.put(OpdConfig.ALL_DRUGLIST_LIST, listAllDrugListList);
			
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
	
	
	public Map getUnitMacroForModify(UnitDrugListMasterVO vo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		
		List listAllUnit=null;
		UnitDrugListMasterVO[] unitDrugListMstVO=null;
		List listAllDrugListList=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
						
			listAllUnit = opdEssentialDAO.getAllUnit(userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
						
			listAllDrugListList=opdEssentialDAO.getAllDrugList(userVO);
			essentialMap.put(OpdConfig.ALL_DRUGLIST_LIST, listAllDrugListList);
			
			unitDrugListMstVO=opdEssentialDAO.getUnitDrugLstByDeptUnit(vo.getDeptUnitCode(), userVO);
			essentialMap.put(OpdConfig.MAPPED_UNIT_DRUG_LIST_VO_ARRAY, unitDrugListMstVO);
			
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
	
	
	
	public List getParticularDrugListDtl(String drugListId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstDrugListDetail = null;
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			lstDrugListDetail=dao.getParticularDrugListDetail(drugListId, userVO);			
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
		return lstDrugListDetail;
	}

	// Getting Unit Keyword ADD Essentials
	public Map getUnitEpisodeKeywordEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List<EpisodeKeywordsMasterVO> lstKeyword = new ArrayList<EpisodeKeywordsMasterVO>();
		List notAddedUnits = new ArrayList();
		List lstDept = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);
			
			// All Clinical Departments List
			lstDept = opdEssentialDAO.getAllClinicalDepartmentList(_userVO);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDept);

			// All Episode Keywords List
			lstKeyword = opdEssentialDAO.getAllEpisodeKeywordList(_userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_ALL_EPISODE_KEYWORD_LIST, lstKeyword);

			// All Units to which no keyword is attached
			UnitEpisodeKeywordMasterDAOi daoUnitKeyword = new UnitEpisodeKeywordMasterDAO(tx);
			notAddedUnits = daoUnitKeyword.getNotAddedUnitList(_userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_NOT_ADDED_UNIT_LIST, notAddedUnits);
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
		
	// Getting Unit Episode Keyword MODIFY Essentails
	public Map getModifyUnitEpisodeKeywordEssentials(String _deptUnitCode, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List<EpisodeKeywordsMasterVO> allKeyword = new ArrayList<EpisodeKeywordsMasterVO>();
		List<EpisodeKeywordsMasterVO> selectedKeyword = new ArrayList<EpisodeKeywordsMasterVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);
			allKeyword = opdEssentialDAO.getAllEpisodeKeywordList(_userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_ALL_EPISODE_KEYWORD_LIST, allKeyword);

			selectedKeyword = opdEssentialDAO.getEpisodeKeywordsUnitWise(_deptUnitCode, _userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_SELECTED_KEYWORD_LIST, selectedKeyword);
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
	
	// Getting Unit Episode Keyword VIEW Essentails
	public Map getViewUnitEpisodeKeywordEssentials(String _deptUnitCode, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List<EpisodeKeywordsMasterVO> selectedKeyword = new ArrayList<EpisodeKeywordsMasterVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);
			selectedKeyword = opdEssentialDAO.getEpisodeKeywordsUnitWise(_deptUnitCode, _userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_SELECTED_KEYWORD_LIST, selectedKeyword);
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

	// Getting ICD Include Exclude Essentails
	public Map getICDIncludeExcludeEssential(String _groupCode, String _subgroupCode, String _diseaseCode, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		//List<IcdGroupMasterVO> lstICDGroup = new ArrayList<IcdGroupMasterVO>();
		//List<IcdSubgroupMasterVO> lstICDSubgroup = new ArrayList<IcdSubgroupMasterVO>();
		List<IcdDiseaseMasterVO> lstICDDisease = new ArrayList<IcdDiseaseMasterVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);
			
			// List all ICD Groups that have at least one used Subgroup
			/*lstICDGroup = opdEssentialDAO.getAllUsedICDGroups(_userVO);
			if(lstICDGroup==null || lstICDGroup.size()==0)
				throw new HisRecordNotFoundException("No ICD Group Found");
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP, lstICDGroup);

			// List all ICD Sub Groups that have at least one disease
			lstICDSubgroup = opdEssentialDAO.getAllUsedICDSubGroups(_userVO);
			if(lstICDSubgroup==null || lstICDSubgroup.size()==0)
				throw new HisRecordNotFoundException("No ICD Sub Group Found");
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP, lstICDSubgroup);*/

			// List all ICD Diseases
			lstICDDisease = opdEssentialDAO.getAllICDDisease(_userVO);
			if(lstICDDisease==null || lstICDDisease.size()==0)
				throw new HisRecordNotFoundException("No ICD Disease Found");
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBDISEASE, lstICDDisease);

			// Group Name
			IcdGroupMasterDAO grpDAO = new IcdGroupMasterDAO(tx);
			String groupName = grpDAO.getGroupName(_groupCode, _userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_GROUP_NAME, groupName);
		
			// Subgroup Name
			IcdSubGroupMasterDAO subgrpDAO = new IcdSubGroupMasterDAO(tx);
			String subgroupName = subgrpDAO.getSubgroupName(_subgroupCode, _userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_SUBGROUP_NAME, subgroupName);

			// Disease Name
			IcdDiseaseMasterDAOi diseaseDAO = new IcdDiseaseMasterDAO(tx);
			String diseaeseName = diseaseDAO.getName(_diseaseCode, _userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_DISEASE_SUBDISEASE_NAME, diseaeseName);
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

	// Getting ICD Code Essentials
	public Map getICDAllEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List diagnosisCodeList = null;
		
		/**Added by Vasu on 29.Oct.2018 to get ICD-O Essentials at OPD Desk footer*/
        List diagnosisSiteCodeList = null;
        List morphologyCodeList = null;
        
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			
			diagnosisCodeList = opdEssDAO.getICDDiseaseCodeList(_userVO);
			essentialMap.put(DynamicDeskConfig.DYNAMIC_DESK_ICD_DISEASE_LIST, diagnosisCodeList);
			
			diagnosisSiteCodeList = opdEssDAO.getDiagnosisSiteCodeListForICDEssentials(_userVO);
			essentialMap.put(DynamicDeskConfig.DYNAMIC_DESK_ICDO_DIAGNOSIS_SITE_LIST , diagnosisSiteCodeList);

			morphologyCodeList = opdEssDAO.getMorphologyCodeListForICDEssentials(_userVO);
			essentialMap.put(DynamicDeskConfig.DYNAMIC_DESK_ICDO_MORPHOLOGY_LIST , morphologyCodeList);
			
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

	// Getting Dynamic Previous Visit Summary Detail
	public LinkedHashMap<String, List<List<Object>>> getDynamicVisitSummaryDetail(EpisodeVO _episodeVO, List<String> _lstMenuIds, UserVO _userVO)
	{
		LinkedHashMap<String, List<List<Object>>> mp = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();		
		try
		{
			tx.begin();
			
			DynamicVisitSummaryDtlDAOi dynaVisitDtlDAO = new DynamicVisitSummaryDtlDAO(tx);
			mp = dynaVisitDtlDAO.getDynamicVisitDetail(_episodeVO, _lstMenuIds, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), mp);
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
		return mp;
	}

	// Getting Disease Site Essentails
	public Map getDiseaseSiteEssential(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List<IcdGroupMasterVO> lstICDGroup = new ArrayList<IcdGroupMasterVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);
			
			// List all ICD Groups that have at least one used Subgroup
			lstICDGroup = opdEssentialDAO.getAllUsedICDGroups(_userVO);
			if(lstICDGroup==null || lstICDGroup.size()==0)
				lstICDGroup = new ArrayList<IcdGroupMasterVO>();
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP, lstICDGroup);
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

	// Get Subgroups Group Wise
	public List<IcdSubgroupMasterVO> getSubGroupsByGroup(String _icdGroupCode, UserVO _userVO)
	{
		List<IcdSubgroupMasterVO> lstICDSubGroup = new ArrayList<IcdSubgroupMasterVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);
			lstICDSubGroup = opdEssentialDAO.getSubgroupsByGroup(_icdGroupCode, _userVO);
			if(lstICDSubGroup==null)
				lstICDSubGroup = new ArrayList<IcdSubgroupMasterVO>();
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
		return lstICDSubGroup;
	}

	// Get Disease SubGroup Wise
	public List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String _icdSubgroupCode, UserVO _userVO)
	{
		List<IcdDiseaseMasterVO> lstICDDisease = new ArrayList<IcdDiseaseMasterVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);
			lstICDDisease = opdEssentialDAO.getDiseaseBySubGroup(_icdSubgroupCode, _userVO);
			if(lstICDDisease==null)
				lstICDDisease = new ArrayList<IcdDiseaseMasterVO>();
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
		return lstICDDisease;
	}

	/**
	 * Getting Generic Chart Reporting Essentials
	 * 
	 * @param _deskType Desk Type
	 * @param _unitCode Department Unit Code
	 * @param _patDtlVO Patient Detail
	 * @param _userVO User Detail
	 * @return Map of Essentials
	 */
	public Map<String, Object> getChartReportingEssentials(String _deskType, String _unitCode, PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		Map<String, Object> essentialMap = new HashMap();
		List<ChartMasterVO> lstCharts = new ArrayList<ChartMasterVO>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();			
			
			OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);
			ChartUnitMapppingDAOi chartUnitDAO = new ChartUnitMapppingDAO(tx);
			
			// List all Charts for the given Desk Type
			// Get Episode or Admission Start Date
			String date = "";
			lstCharts = chartUnitDAO.getChartsByCategoryByUnit(OpdConfig.CHART_CATEGORY_OPD, _unitCode, _userVO);
			if(_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK))
			{
				date = opdEssentialDAO.getAdmissionStartDate(_patDtlVO, _userVO);
			}
			else //if(_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK))
			{
				date = opdEssentialDAO.getEpiosdeStartDate(_patDtlVO, _userVO);
			}

			if(lstCharts==null || lstCharts.size()==0)
				lstCharts = new ArrayList<ChartMasterVO>();
			Map<String, Map> mpDefulatCharts = new LinkedHashMap<String, Map>();
			for(ChartMasterVO vo : lstCharts)
			{
				if(vo.getIsDefault().equals(OpdConfig.IS_DEFAULT_NO))
					continue;
				Map mp = null;
				if(vo.getIsDefault().equals(OpdConfig.IS_DEFAULT_CURRENT_VISIT))
					mp = new OpdPatientDelegate().getChartReportingData(_deskType, _patDtlVO, vo, null, OpdConfig.CHOICE_DATE_WISE, _patDtlVO.getEpisodeDate(), _patDtlVO.getEpisodeDate(), _userVO);					
				else if(vo.getIsDefault().equals(OpdConfig.IS_DEFAULT_COMPLETE_EPISODE))
					mp = new OpdPatientDelegate().getChartReportingData(_deskType, _patDtlVO, vo, null, OpdConfig.CHOICE_EPISODE_WISE, _patDtlVO.getEpisodeDate(), _patDtlVO.getEpisodeDate(), _userVO);
				mpDefulatCharts.put(vo.getChartId(), mp);
			}
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_MAP_OF_ALL_DEFAULT_CHARTS_DETAILS, mpDefulatCharts);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHARTS_DESK_N_UNIT_WISE, lstCharts);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_DATE_EPISODE_OR_ADMISSION_START, date);
			if(lstCharts==null || lstCharts.size()==0)
				throw new HisRecordNotFoundException("No Chart Found for the Unit");
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
	
	public Map getChartUnitListEssential(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		List chartList = null;
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			lstDept = opdEssentialDAO.getAllClinicalDepartmentList(_userVO);
			essentialMap.put(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST, lstDept);
			
			listAllUnit = opdEssentialDAO.getAllUnit(_userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			
			listNotMappedUnit=opdEssentialDAO.getAllUnitNotMappedWithChartList(_userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST_NOT_MAPPED_WITH_CHART, listNotMappedUnit);
			
			chartList = opdEssentialDAO.getChartUnitListEssential(_userVO);
			
			essentialMap.put(OpdConfig.CHART_NAME_LIST,chartList);
		} 
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getChartUnitListForModify(ChartUnitMapppingVO _vo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		
		List listAllUnit=null;
		ChartUnitMapppingVO[] chartUnitMstVO=null;
		List listAllChartList=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
						
			listAllUnit = opdEssentialDAO.getAllUnit(_userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
						
			listAllChartList=opdEssentialDAO.getChartUnitListEssential(_userVO);
			essentialMap.put(OpdConfig.CHART_NAME_LIST, listAllChartList);
			
			chartUnitMstVO=opdEssentialDAO.getUnitChartLstByDeptUnit(_vo.getDeptUnitCode(), _userVO);
			essentialMap.put(OpdConfig.MAPPED_UNIT_CHART_LIST_VO_ARRAY, chartUnitMstVO);
			
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
	
	public Map getDeptUnitListEssential(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		//List chartList = null;
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			lstDept = opdEssentialDAO.getAllClinicalDepartmentList(_userVO);
			essentialMap.put(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST, lstDept);
//			
			listAllUnit = opdEssentialDAO.getAllUnit(_userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, listAllUnit);
////			
			listNotMappedUnit=opdEssentialDAO.getAllUnitNotMapped(_userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST_NOT_MAPPED, listNotMappedUnit);
			
		} 
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getEssential(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		//List hosDiseaseLst=null;
		//List icdDiseaseList = null;
		//List lstGroup=null;
		
		List<IcdGroupMasterVO> lstICDGroup = new ArrayList<IcdGroupMasterVO>();
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			lstICDGroup = opdEssentialDAO.getAllUsedICDGroups(_userVO);
//			icdDiseaseList = opdEssentialDAO.getIcdDiseaseList(_userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP, lstICDGroup);
//			essentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE,icdDiseaseList);
			
		} 
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getUnitForDept(String _dept, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List unitNotAddedList=new ArrayList();
		Map unitMap = new HashMap();
		try
		{
			tx.begin();
			//OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
		
//			unitNotAddedList=opdEssentialDAO.getAllUnitNotMapped(_dept,_userVO);
			unitMap.put(OpdConfig.ALL_UNIT_LIST_NOT_MAPPED, unitNotAddedList);
		
	} 
	catch (HisRecordNotFoundException e) {
		tx.rollback();
		e.printStackTrace();
		throw new HisRecordNotFoundException(e.getMessage());
	} catch (HisDataAccessException e) {
		tx.rollback();
		
		e.printStackTrace();
		throw new HisDataAccessException();
	} catch (HisApplicationExecutionException e) {
		tx.rollback();
		
		e.printStackTrace();
		throw new HisApplicationExecutionException();
	} catch (HisException e) {
		tx.rollback();
		
		e.printStackTrace();
		throw new HisException();
	} catch (Exception e) {
		tx.rollback();
		
		e.printStackTrace();
		throw new HisApplicationExecutionException();
	} finally {
		tx.close();
	}
		return unitMap;
	}
	public Map getIcdDiseaseList(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		//List lstGroup=null;
		List<IcdDiseaseMasterVO> lstICDDisease = new ArrayList<IcdDiseaseMasterVO>();
		
		//List<IcdGroupMasterVO> lstICDGroup = new ArrayList<IcdGroupMasterVO>();
			
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
//			lstICDGroup = opdEssentialDAO.getAllUsedICDGroups(_userVO);
			lstICDDisease = opdEssentialDAO.getAllICDDisease(_userVO);
//			essentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP, lstICDGroup);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE,lstICDDisease);
			
		} 
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	// Get DepartmentUnit List for Hospital disease Mapping
	
	public Map getDeptUnitEssential(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		//List chartList = null;
		List deptList=null;
		List allUnitList=null;
		List notMappedUnitLst=null;
		List hospitalDiseaseLst =  null;
		
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);
			
			deptList = opdEssentialDAO.getAllClinicalDepartmentList(_userVO);
			essentialMap.put(OpdConfig.LIST_OF_ALL_CLINICAL_DEPARTMENT, deptList);
//			
			allUnitList = opdEssentialDAO.getAllUnit(_userVO);
			essentialMap.put(OpdConfig.ALL_UNIT_LIST, allUnitList);
////			
			notMappedUnitLst=opdEssentialDAO.getNotMappedUnit(_userVO);
			essentialMap.put(OpdConfig.DEPT_WISE_LIST_OF_ALL_NOT_MAPPED_UNIT, notMappedUnitLst);
			
			hospitalDiseaseLst=opdEssentialDAO.getHospitalDisease(_userVO);
			essentialMap.put(OpdConfig.ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE, hospitalDiseaseLst);
			
		} 
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getHospitalDisease(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		//List chartList = null;
		List hospitalDiseaseLst=null;
		
		try
		{
			tx.begin();
		    OpdEssentialDAO opdEssentialDAO = new OpdEssentialDAO(tx);	
		    hospitalDiseaseLst=opdEssentialDAO.getHospitalDisease(_userVO);
			essentialMap.put(OpdConfig.ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE, hospitalDiseaseLst);
			
		} 
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	/**
	 * Getting Group Name for ICD Mapping
	 * 
	 * @param _userVO User Detail
	 * @return Map of Group Name
	 */
	public Map getMappingTypeWiseDiseaseEssential(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mapEssentialMap = new HashMap();
		List listICDGroup = null;
		try
		{
			tx.begin();
		    OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);	
		    
		    
		    listICDGroup = opdEssentialDAO.getAllUsedICDGroups(_userVO);
		    mapEssentialMap.put(OpdConfig.OPD_ESSENTIAL_LIST_ICD_GROUP, listICDGroup);
		    
		} 
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mapEssentialMap;
	}
	/**
	 * Getting Hospital Disease For IcdMapping
	 * 
	 */
	public Map getHospitalDiseaseForIcdMapping(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mapEssential = new HashMap();
		List listHospitalDisease=null;
		try
		{
			tx.begin();
		    OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);	
		    
		    listHospitalDisease=opdEssentialDAO.getHospitalDisease(_userVO);
		   
		    mapEssential.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE, listHospitalDisease);
		    
		} 
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mapEssential;
	}
	/**
	 * Getting Chronic Disease For IcdMapping
	 * 
	 */
	public Map getChronicDiseaseForIcdMapping(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mapEssential = new HashMap();
		List listChronicDisease=null;
		try
		{
			tx.begin();
		    OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);	
		    listChronicDisease=opdEssentialDAO.getChronicDisease(_userVO);
		    mapEssential.put(OpdConfig.OPD_ESSENTIAL_LIST_ALL_CHRONIC_DISEASE, listChronicDisease);
		    
		} 
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mapEssential;
	}
	
	// ICD Entry Form Essentails
	public Map<String,Object> getICDEntryFormEssentials(UserVO voUser_p)
	{
		Map essentialMap = new HashMap();
		//List diagnosisCodeList = null;
		List lstUnits = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			
			//diagnosisCodeList = opdEssDAO.getIcdDiagnosisCodeListByUnit(voUser_p); // Not Unit Wise
			//essentialMap.put(OpdConfig.DIAGNOSIS_CODE_LIST, diagnosisCodeList);

			lstUnits = opdEssDAO.getAllClinicalUnitList(voUser_p); //getUnitsBySeatId
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_UNITS, lstUnits);
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

	// Pat List for ICD Entry Form
	/*public List<PatientDetailVO> getOPDPatientListForICDEntry(String _tabMode, PatientDetailVO voPatDtl_p, String strFromDat_p, String strToDate_p, UserVO _userVO)
	{
		List<PatientDetailVO> lstPatients;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO daoOPDEss = new OpdEssentialDAO(tx);
			lstPatients = daoOPDEss.getOPDPatientListForICDEntry(_tabMode, voPatDtl_p, strFromDat_p, strToDate_p, _userVO);
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
		return lstPatients;
	}*/
	
	// ICD Codes List for ICD Entry Form
	public List<Entry> getICDCodesListForICDEntry(UserVO voUser_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Entry> lstICDCodes = null;
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			lstICDCodes = opdEssDAO.getICDDiagnosisCodesOnlyList(voUser_p);
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
		return lstICDCodes;
	}
	// Unit Room List for ICD Entry Form
	public List<Entry> getUnitRoomListForICDEntry(String strUnitCode_p, UserVO voUser_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Entry> roomList = null;
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			
			//roomList = opdEssDAO.getUnitRoomList(voUser_p, strUnitCode_p,"-1");
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
		return roomList;
	}
	
	public Map getMinYearEssential(UserVO userVO,String strMode_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		List<DSSEpisodeVO> lstDSSData = null;
		try
		{
			tx.begin();
			OpdEssentialDAO objEssentialDAO = new OpdEssentialDAO(tx);
			
			String strMinYear = objEssentialDAO.getMinimumYear(RegistrationConfig.DSS_REPORTS_TARGET_TABLE_REGISTRATION,userVO);
			essentialMap.put(OpdConfig.REPORTS_MINIMUM_YEAR, strMinYear);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
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
			e.printStackTrace();
			tx.rollback();
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
	
	/*Functions Added By Pawan Kumar B N*/
	public List getParameterForPatientComplaints(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstParameter = null;
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			lstParameter=dao.getParameterForPatientComplaints(userVO);
			
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
		return lstParameter;
	}
	
	
	public List getUsersOpd(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			OpdEssentialDAO objEssentialDAO = new OpdEssentialDAO(tx);
			List list = objEssentialDAO.getUsersOpd(_userVO);

			return list;
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public StringBuffer getPatientCount(UserDeskMenuMasterVO userDeskVO, UserVO userVO) 
	{
		String patientCountObj = "";
		String p_mode="0";
		String currentDeskType= userDeskVO.getDeskType();
		StringBuffer sbAjaxRes = new StringBuffer();
		String total,page,records,rows;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			//String head="{\"total\":\"1\",\"page\":\"1\",\"records\":\"2\",\"rows\":";
			//sbAjaxRes.append(head);
			sbAjaxRes.append("[");
			//-- Check for Specific 
			if(((userDeskVO.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK)) && (userDeskVO.getDeptUnitCode()!= null)) ||
					((userDeskVO.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK)) && (userDeskVO.getDeptUnitCode()!= null)))
				{
				p_mode ="1";
				patientCountObj = dailyPatientDAO.getPatientCount(p_mode, userDeskVO, userVO);
				sbAjaxRes.append(patientCountObj+",");
				}
			// Call for current desktype 
			else {
				currentDeskType = userDeskVO.getDeskType();
				//if(currentDeskType.equals("2"))currentDeskType="1"; //added by Akash Singh [16-01-2015 For Casuality Desk deskType]
				patientCountObj = dailyPatientDAO.getPatientCount(p_mode, userDeskVO, userVO);
				
				sbAjaxRes.append(patientCountObj+",");
				for(int i=1; i<DynamicDeskConfig.DESK_TYPES_CODE_FOR_DOC_DESK.length ; i++)
				{
					if(!(DynamicDeskConfig.DESK_TYPES_CODE_FOR_DOC_DESK[i]).equals(currentDeskType))
					{
						
						userDeskVO.setDeskType(DynamicDeskConfig.DESK_TYPES_CODE_FOR_DOC_DESK[i]);
						patientCountObj = dailyPatientDAO.getPatientCount(p_mode, userDeskVO, userVO);
						
						if(patientCountObj!= null)
						{	
							if(i==(DynamicDeskConfig.DESK_TYPES_CODE_FOR_DOC_DESK.length)-1)
							{
								
								sbAjaxRes.append(patientCountObj);								
							}
							else
							{
								
								sbAjaxRes.append(patientCountObj+",");								
							}
							}
					}
					
				}
				
			
			// Call for other desk types 
				// append to SB, ","
			
			// remove last ',"
			sbAjaxRes.append("]");
			//sbAjaxRes.append("}");
			
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
		return sbAjaxRes;
	}
	
	// All Patient list for toady*
	public PatientDetailVO[] getTodayAllPatientList(UserVO _userVO, String unitCode, String roomCode)
	{
		
		PatientDetailVO[] dailyPatientVOs;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			dailyPatientVOs = dailyPatientDAO.getTodayAllPatientsBySeatID(_userVO, unitCode, roomCode);
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
		return dailyPatientVOs;
	}

	public Map<String, Object> getAllPatientList_AJAX(PatientDetailVO patientDetailVO, UserVO _userVO, int p_page, int p_limit, String p_sidx, String p_sord, String p_where, String deskType)
	{		
		Map<String, Object> mapObj = null;		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			mapObj = dailyPatientDAO.getAllPatientList_AJAX("1", patientDetailVO, _userVO, p_page, p_limit, p_sidx, p_sord, p_where, deskType);			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mapObj;
	}
	
	public List treatmentDetailList(String patcrNO,String episodeCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstParameter = null;
		try
		{
			tx.begin();
			OpdEssentialDAOi dao = new OpdEssentialDAO(tx);
			lstParameter=dao.getPrevPatDrugDetail(patcrNO, episodeCode, userVO);
			
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
		return lstParameter;
	}
	

	public Map<String, Object> AJX_G_PATIENTS_COUNT_NEW(UserVO _userVO, int p_page, int p_limit, String p_sidx, String p_sord, String p_where)
	{		
		Map<String, Object> mapObj = null;		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			mapObj = dailyPatientDAO.AJX_G_PATIENTS_COUNT_NEW("1",_userVO, p_page, p_limit, p_sidx, p_sord, p_where);			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mapObj;
	}
	
	public Map getVisitSummaryDetails(EpisodeVO _episodeVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List<EpisodeVO> lstEpisodeDtl = new ArrayList<EpisodeVO>();
		
		
		try
		{
			tx.begin();
			
			EpisodeDAOi episodeDAO = new EpisodeDAO(tx);
			EpisodeSummaryDAOi episodeSummaryDAO = new EpisodeSummaryDAO(tx);
			OpdEssentialDAOi opdEssentialDAO = new OpdEssentialDAO(tx);
			/*EpisodeDiagnosisDAO episodeDiagnosisDAO = new EpisodeDiagnosisDAO(tx);*/
			
			
			// Episode Current Visit Summary Detail
			lstEpisodeDtl = episodeSummaryDAO.getVisitSummaryDetails(_episodeVO, _userVO);
			essentialMap.put(OpdConfig.OPD_ESSENTIAL_ALL_EPISODE_VISIT_LIST, lstEpisodeDtl);
		
			
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

	public Map<String, Object> getDailyPatientDetail( PatientDetailVO patientDetailVO, UserVO userVO) 
	{
		Map<String, Object> mapEssential = new HashMap<String, Object>();
		HisDAO hisDAO = new HisDAO("Opd", "OpdEssentialBO");	
		try
		{			
			OpdRegEssentialDAO opdRegEssentialDAO = new OpdRegEssentialDAO();
			
			// To get current visit detail for a purticular episode
			List<PatientDetailVO> lstDailyPatDtl = opdRegEssentialDAO.getDailyPatientDetail(hisDAO, "1", patientDetailVO, userVO);
			//if(lstDailyPatDtl != null && !lstDailyPatDtl.isEmpty()){
			//mapEssential.put(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO, lstDailyPatDtl.get(0));}
			
			mapEssential.put(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO, lstDailyPatDtl);
			
			
			// To get All visit detail for a purticular episode
			List<PatientDetailVO> lstPatVisitDtl = opdRegEssentialDAO.getPatientVisitDetail(hisDAO, "1", patientDetailVO, userVO);
			mapEssential.put(OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST, lstPatVisitDtl);
			
			// To get referred detail of  a Patient
			List<EpisodeReferVO> lstPatReferDtl = opdRegEssentialDAO.getPatientReferredDetail(hisDAO, "1", patientDetailVO, userVO);
			mapEssential.put(OpdConfig.OPD_ESSENTIAL_REEERRED_DETAIL_LIST, lstPatReferDtl);
			
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("OpdEssentialBO.getDailyPatientDetail()::HisRecordNotFoundException -> "
					+ e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			throw new HisDataAccessException("OpdEssentialBO.getDailyPatientDetail()::HisDataAccessException -> " + e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			throw new HisApplicationExecutionException("OpdEssentialBO.getDailyPatientDetail()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		catch (HisException e)
		{
			throw new HisException("OpdEssentialBO.getDailyPatientDetail()::HisException -> " + e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialBO.getDailyPatientDetail()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		finally
		{
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
		}
		return mapEssential;
	}

	public Map<String, Object> getEpisodeDiagnosisDetail(EpisodeDiagnosisVO episodeDiagnosisVO, UserVO userVO) 
	{
		Map<String, Object> mapEssential = new HashMap<String, Object>();
		HisDAO hisDAO = new HisDAO("Opd", "OpdEssentialBO");		
		try
		{
			EMRPatientDetailDAO objDAO = new EMRPatientDetailDAO();
			EMRPateintDataPolicyVO ObjEMRPateintDataPolicyVO = new EMRPateintDataPolicyVO(); // Added this for sending  futuristic Data like... deskId, Menuid, genration Mode
			// To get current visit detail for a purticular episode
			List<EpisodeDiagnosisVO> lstDiagnosisDtl = objDAO.getEpisodeDiagnosisDetail(hisDAO, "1", episodeDiagnosisVO, ObjEMRPateintDataPolicyVO, userVO);
			mapEssential.put(OpdConfig.OPD_EPISODE_VISIT_DIAGNOSIS_DETAIL, lstDiagnosisDtl);
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("OpdEssentialBO.getEpisodeDiagnosisDetail()::HisRecordNotFoundException -> "
					+ e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			throw new HisDataAccessException("OpdEssentialBO.getEpisodeDiagnosisDetail()::HisDataAccessException -> " + e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			throw new HisApplicationExecutionException("OpdEssentialBO.getEpisodeDiagnosisDetail()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		catch (HisException e)
		{
			throw new HisException("OpdEssentialBO.getEpisodeDiagnosisDetail()::HisException -> " + e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialBO.getEpisodeDiagnosisDetail()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		finally
		{
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
		}
		return mapEssential;
	}

	public Map<String, Object> getEpisodeDrugDetail(PatDrugTreatmentDetailVO patDrugTreatmentDetailVO, UserVO userVO) 
	{
		Map<String, Object> mapEssential = new HashMap<String, Object>();
		HisDAO hisDAO = new HisDAO("Opd", "OpdEssentialBO");		
		try
		{	
			EMRPatientDetailDAO objDAO = new EMRPatientDetailDAO();
			EMRPateintDataPolicyVO objEMRPateintDataPolicyVO = new EMRPateintDataPolicyVO(); // Added this for sending  futuristic Data like... deskId, Menuid, genration Mode
			// To get current visit detail for a purticular episode
			List<PatDrugTreatmentDetailVO> lstDrugTreatmentDtl = objDAO.getEpisodeDrugDetail(hisDAO, "1", patDrugTreatmentDetailVO,objEMRPateintDataPolicyVO, userVO);
			mapEssential.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("OpdEssentialBO.getEpisodeDrugDetail()::HisRecordNotFoundException -> "
					+ e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			throw new HisDataAccessException("OpdEssentialBO.getEpisodeDrugDetail()::HisDataAccessException -> " + e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			throw new HisApplicationExecutionException("OpdEssentialBO.getEpisodeDrugDetail()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		catch (HisException e)
		{
			throw new HisException("OpdEssentialBO.getEpisodeDrugDetail()::HisException -> " + e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialBO.getEpisodeDrugDetail()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		finally
		{
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
		}
		return mapEssential;
	}

	public Map<String, Object> getPatMedicalHistory(PatientAlertsDetailVO patientAlertsDetailVO,PatAllergyDtlVO patAllergyDtlVO, UserVO userVO) 
	{
		Map<String, Object> mapEssential = new HashMap<String, Object>();
		HisDAO hisDAO = new HisDAO("Opd", "OpdEssentialBO");		
		try
		{			
			EMRPatientDetailDAO objDAO = new EMRPatientDetailDAO();
			EMRPateintDataPolicyVO ObjEMRPateintDataPolicyVO = new EMRPateintDataPolicyVO(); // Added this for sending  futuristic Data like... deskId, Menuid, genration Mode
			// To get current allergies detail 
			PatAllergyDtlVO[] _patAllVoArr={};
			//int i=0;
			List<PatAllergyDtlVO> lstAllergiesDtl =new ArrayList<PatAllergyDtlVO>();
				lstAllergiesDtl= objDAO.getPatAllergiesDetails(hisDAO, "1", patAllergyDtlVO,ObjEMRPateintDataPolicyVO, userVO);
				//_patAllVoArr = (PatAllergyDtlVO[]) lstAllergiesDtl.toArray();
				//for(PatAllergyDtlVO _patAlVO:lstAllergiesDtl){
				//	_patAllVoArr[i]=_patAlVO;
				//	i++;
				//}
			mapEssential.put(OpdConfig.PATIENT_PROFILE_ALLERGY_DTL_DESK_TILE, lstAllergiesDtl);
			
			// To get current Chronic detail 
			List<PatientAlertsDetailVO> lstChronicDtl = objDAO.getPatChronicDetails(hisDAO, "1", patientAlertsDetailVO,ObjEMRPateintDataPolicyVO, userVO);
			mapEssential.put(OpdConfig.PATIENT_PROFILE_ALERTS_DTL_DESK_TILE, lstChronicDtl);
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("OpdEssentialBO.getPatMedicalHistory()::HisRecordNotFoundException -> "
					+ e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			throw new HisDataAccessException("OpdEssentialBO.getPatMedicalHistory()::HisDataAccessException -> " + e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			throw new HisApplicationExecutionException("OpdEssentialBO.getPatMedicalHistory()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		catch (HisException e)
		{
			throw new HisException("OpdEssentialBO.getPatMedicalHistory()::HisException -> " + e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialBO.getPatMedicalHistory()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		finally
		{
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
		}
		return mapEssential;
	}
	
	
	public  Map<String, Object>  getPatInvestigationDetail(String CrNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String labNames="";
		String prvTestDetail="";
		List<viewInvestigationVO> lstPrvLabTest=null;
		String testGroupNames="";
		Map<String, Object> mp = new HashMap<String, Object>();
		

		try {

			tx.begin();

			viewInvestigationDAO invEssentialDAO=new viewInvestigationDAO(tx);
			//List lstLabNames=invEssentialDAO.getLabNames(userVO);
			  lstPrvLabTest=invEssentialDAO.getPrvTestDtlUsingAJAX(CrNo, userVO);
		 
			  //LabTestVO LabTestVO=new LabTestVO();
			  
			 
			/*  
			  for(LabTestVO vo:lstPrvLabTest)
			  {
				  prvTestDetail+=vo.getStatus()+"#"+vo.getLabName()+"#"+vo.getSampleName()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType())+"#"+vo.getIsMandatoryReq()+"#"+vo.getBookMarkCode()+"#"+vo.getOfflineAppoitMentDate()+"@";  
				  
				  	 
			  }*/
			mp.put(InvestigationConfig.LIST_REQ_DATA, lstPrvLabTest);
		

		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {

			tx.close();
		}

		return mp;
	}	

	
	

	public Map<String, Object> getDeskPatDtl(PatientDetailVO patientDetailVO,UserVO userVO, String deskType) 
	{
		Map<String, Object> mapEssential = new HashMap<String, Object>();
		HisDAO hisDAO = new HisDAO("Opd", "OpdEssentialBO");	
		try
		{			
			OpdRegEssentialDAO opdRegEssentialDAO = new OpdRegEssentialDAO();
			
			// To get current visit detail for a purticular episode
			List<PatientDetailVO> lstDeskPatDtl = opdRegEssentialDAO.getDeskPatDtl(hisDAO, "1", patientDetailVO, userVO, deskType);
			if(lstDeskPatDtl != null && !lstDeskPatDtl.isEmpty()){
			mapEssential.put(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO, lstDeskPatDtl.get(0));}
			
			//mapEssential.put(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO, lstDeskPatDtl);			
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("OpdEssentialBO.getDeskPatDtl()::HisRecordNotFoundException -> "
					+ e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			throw new HisDataAccessException("OpdEssentialBO.getDeskPatDtl()::HisDataAccessException -> " + e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			throw new HisApplicationExecutionException("OpdEssentialBO.getDeskPatDtl()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		catch (HisException e)
		{
			throw new HisException("OpdEssentialBO.getDeskPatDtl()::HisException -> " + e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialBO.getDeskPatDtl()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		finally
		{
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
		}
		return mapEssential;
	}

	public StringBuffer getSingleDeptPatientCount(UserDeskMenuMasterVO userDeskVO,	UserVO userVO) 
	{
		String patientCountObj = "";
		String p_mode="0";
		String currentDeskType= userDeskVO.getDeskType();
		StringBuffer sbAjaxRes = new StringBuffer();
		String total,page,records,rows;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			//sbAjaxRes.append(head);
			sbAjaxRes.append("[");
				p_mode ="0";
				patientCountObj = dailyPatientDAO.getPatientCount(p_mode, userDeskVO, userVO);
				sbAjaxRes.append(patientCountObj);

				sbAjaxRes.append("]");
			
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
		return sbAjaxRes;
	}
	public Map getEstimateRequestEssentials(EstimateCertificateReqVO estReqDtlVO,  UserVO strUserVO)
	{
		Map essentialMap = new HashMap();
		List<EstimateCertificateReqVO> estimateCertificateReqVOList=null;
		List lstEstimateReqAdvBy=new ArrayList();
		List<EstimateCertificateReqVO> lstPrevEstimateReqDtl=new ArrayList();
		String str="";
		String pmode="1";
		
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EstimateRequestDAO estimateReqDAO=new EstimateRequestDAO(tx);
			
			estimateCertificateReqVOList=estimateReqDAO.getEstimateReqTariffDtl(estReqDtlVO.getDepartmentUnitCode(), strUserVO);
			/*if(estimateCertificateReqVOList.size()<1)
			{
				estimateCertificateReqVOList=estimateReqDAO.getEstimateReqTariffDtlMst(strUserVO);	
			}*/
			essentialMap.put(MrdConfig.ESTIMATE_PROCEDURE_TARIFF_DETAIL_LIST, estimateCertificateReqVOList);
			
			lstEstimateReqAdvBy = estimateReqDAO.getEstimateReqAdvisedBy(estReqDtlVO.getDepartmentUnitCode(),strUserVO);
			essentialMap.put(MrdConfig.ESTIMATE_REQUEST_ADVISEDBY_DETAIL, lstEstimateReqAdvBy);
			lstPrevEstimateReqDtl = estimateReqDAO.getPrevEstimateReqDtl(pmode,estReqDtlVO,strUserVO);
			essentialMap.put(MrdConfig.PREVIOUS_ESTIMATE_CERTIFIATE_DTL, lstPrevEstimateReqDtl);
			
			estimateCertificateReqVOList=estimateReqDAO.getTariffsList(estReqDtlVO.getDepartmentUnitCode(), strUserVO);
			essentialMap.put(MrdConfig.TARIFF_LIST_ESTIMATE_CERTIFICATE_REQUEST, estimateCertificateReqVOList);
			
			
			
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
	
	
	
	public Map getTariffsList(EstimateCertificateReqVO estReqDtlVO,  UserVO strUserVO)
	{
		Map essentialMap = new HashMap();
		List<EstimateCertificateReqVO> estimateCertificateReqVOList=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EstimateRequestDAO estimateReqDAO=new EstimateRequestDAO(tx);
			
			estimateCertificateReqVOList=estimateReqDAO.getTariffsList(estReqDtlVO.getDepartmentUnitCode(), strUserVO);
			essentialMap.put(MrdConfig.TARIFF_LIST_ESTIMATE_CERTIFICATE_REQUEST, estimateCertificateReqVOList);
								
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
	
	
	
	
	public void saveEstimateCertificateReqDtl(EstimateCertificateReqVO estReqVo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String pmode="1";	
		try
		{
			tx.begin();
			MrdEssentialDAO mrdEssDAO=new MrdEssentialDAO(tx);
			EstimateRequestDAO estimateReqDAO=new EstimateRequestDAO(tx);

		
			if (estReqVo != null)
			{
				String tarifid="";
				String certificateId = mrdEssDAO.generateCertificateId(MrdConfig.RECORD_TYPE_ESTIMATE_CERTIFICATE,estReqVo.getDepartmentUnitCode(),MrdConfig.ESTIMATE_REQUEST_GENMODE_AUTOMATIC,userVO);	
				estimateReqDAO.create(pmode,certificateId,tarifid,estReqVo, userVO);
				
				String tariffId[]=estReqVo.getTariffId().split(",");
				
				for(int i = 0;i<tariffId.length;i++)
				{
				String id=	tariffId[i];
				estimateReqDAO.addTariffsForEstimateRequest(pmode,certificateId,id,estReqVo, userVO);
				//estimateReqDAO.billingRequest(certificateId,id,estReqVo, userVO);
				}
			}
			
			
			/*if (estReqVo != null)
			{
				String tariffId[]=estReqVo.getTariffId().split(",");
				System.out.println(tariffId.length);
				for(int i = 0;i<tariffId.length;i++)
				{
				String certificateId = mrdEssDAO.generateCertificateId(MrdConfig.RECORD_TYPE_ESTIMATE_CERTIFICATE,estReqVo.getDepartmentUnitCode(),MrdConfig.ESTIMATE_REQUEST_GENMODE_AUTOMATIC,userVO);	
				String id=	tariffId[i];
				estimateReqDAO.create(pmode,certificateId,id,estReqVo, userVO);
				//estimateReqDAO.billingRequest(certificateId,id,estReqVo, userVO);
				}
			}*/
						

			//estimateReqDAO.create(estReqVo, userVO);

			
			
			
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}
	
	public Map getAddUserDeskMenuMasterDetailsToMap(UserDeskMenuMasterVO _voDeskMapping, UserVO _voUser) 
	{
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
		
			if(_voDeskMapping.getMappingType().equals(OpdConfig.DESK_MAPPING_TYPE_UNIT_WISE))
			{
				List<Entry> lstDept = null;
				List<Entry> lstUnitNotAssigned = null;
				lstDept = objDAO.getAllClinicalDepartmentList(_voUser);
				lstUnitNotAssigned = objDAO.getUnitExceptAssignedByDeskType(_voDeskMapping, _voUser);
				List lstDeptNotAssigned = new ArrayList();
				Set<String> setDept = new HashSet<String>();
				for(Entry entDept: lstDept)
				{				
					for(Entry entUnit: lstUnitNotAssigned)
						if(entUnit.getValue().substring(0, 3).equals(entDept.getValue()))
							if(setDept.add(entDept.getValue()))
								lstDeptNotAssigned.add(entDept);
				}

				essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDeptNotAssigned);
				essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_UNITS_NOT_ASSIGNED, lstUnitNotAssigned);
			}
			else if(_voDeskMapping.getMappingType().equals(OpdConfig.DESK_MAPPING_TYPE_WARD_WISE))
			{
				List lstWard=new ArrayList();
				lstWard = objDAO.getAllClinicalDepartmentList(_voUser);
				essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_UNITS, lstWard);//**********************
			}
			else if(_voDeskMapping.getMappingType().equals(OpdConfig.DESK_MAPPING_TYPE_USER_WISE))
			{	
			
				List lstGroup=null;
				lstGroup=new ArrayList();
				
				lstGroup = objDAO.getAllGroupList(_voUser);
				essentialMap.put(OpdConfig.ESSENTIALBO_ALL_GROUP_LIST, lstGroup);//**********************
			
			
			
			}		
			
			List lstDesks = getAddUserDeskMenuMasterDeskByType(_voDeskMapping.getDeskType(), _voUser);
			essentialMap.put(OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE,lstDesks);
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
	//Created By Chetan Sharma
	
	public Map getDrugRouteMasterEssentails(UserVO _userVO)
	{
		
		Map essentialMap = new HashMap();
		List lstDrugItemTypes = null;
		PatientDetailVO patVO = new PatientDetailVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAO = new HisDAO("OPD", "OpdEssentialBO");

		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			lstDrugItemTypes = opdEssDAO.getItemTypeList(hisDAO , OpdConfig.lstItemTypes, patVO, _userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE, lstDrugItemTypes);
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			if(hisDAO!=null)
				hisDAO.free();
			hisDAO=null;
			tx.close();
		}
		return essentialMap;
	}

	@Override
	public Map getIcdDiagnosisEssential(UserVO _userVO, String _patCrNo,
			String _episodeCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getUnitExceptAssignedByDeskType(String _deskType, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Added by Dheeraj on 02-Nov-2018 to get Composition  Type
	
		public Map getCompositionType(String hospitalCode,UserVO _userVO)
		{
			Map essentialMap = new HashMap();
			List allDesk = new ArrayList();
			List interfaceTypeList = new ArrayList();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();

				OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
				allDesk = objDAO.getCompositionType(hospitalCode,_userVO);
				essentialMap.put(OpdConfig.COMPOSITION_TYPE, allDesk);
				
				//added by swati on date:31-05-2019
				//getting interface type list
				interfaceTypeList=objDAO.getInterfaceType(_userVO.getHospitalCode(),_userVO);
				
				essentialMap.put(OpdConfig.ESSENTIALBO_LIST_INTERFACE_TYPE, interfaceTypeList);
				
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

		//Added by prachi
		
		public Map getSectionAndTemplate(String hospitalCode,UserVO _userVO)
		{
			Map essentialMap = new HashMap();
			List allSection = new ArrayList();
			List allTemplate = new ArrayList();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();

				OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
				allSection = objDAO.getSection(hospitalCode,_userVO);
				
				allTemplate = objDAO.getTemplate(hospitalCode,_userVO);
				
				essentialMap.put(OpdConfig.SECTION_TYPE, allSection);			
				essentialMap.put(OpdConfig.TEMPLATE_TYPE, allTemplate);	
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
		

		public Map getClinicalSection(String hospitalCode,UserVO _userVO)
		{
			Map essentialMap = new HashMap();
			List allDesk = new ArrayList();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();

				OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
				allDesk = objDAO.getClinicalSection(hospitalCode,_userVO);
				essentialMap.put(OpdConfig.CLINICAL_SECTION, allDesk);		
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


		public Map getSubSection(String hospitalCode,UserVO _userVO)
		{
			Map essentialMap = new HashMap();
			List allDesk = new ArrayList();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();

				OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
				allDesk = objDAO.getSubSection(hospitalCode,_userVO);
				essentialMap.put(OpdConfig.CLINICAL_SUB_SECTION, allDesk);		
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

		
		public Map getInterfaceType(String hospitalCode,UserVO _userVO)
		{
			Map essentialMap = new HashMap();
			List allDesk = new ArrayList();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();

				OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
				allDesk = objDAO.getInterfaceType(hospitalCode,_userVO);
				essentialMap.put(OpdConfig.INTERFACE_TYPE, allDesk);		
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

		
		public Map getSectionInterface(String hospitalCode,UserVO _userVO)
		{
			Map essentialMap = new HashMap();
			List allDesk = new ArrayList();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();

				OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
				allDesk = objDAO.getSectionInterface(hospitalCode,_userVO);
				essentialMap.put(OpdConfig.SECTION_INTERFACE, allDesk);		
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
		
		public boolean saveClinicalComposition(ClinicalSectionCompMapFB fb,UserVO _userVO)
		{
			Map essentialMap = new HashMap();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();

				OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
				objDAO.saveClinicalComposition(fb,_userVO);
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
			
			return true;
		}
		
		//Added by Shweta on 12-03-2019
		/*public Map getSinglePageInterfaceEssentails(UserVO _userVO)
		{
			Map essentialMap = new HashMap();
			List clinicalSectionList = null;
			List interfaceTypeList=null;
				JDBCTransactionContext tx = new JDBCTransactionContext();

				try
				{
					tx.begin();
					OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
					
					clinicalSectionList = opdEssDAO.getClinicalSection(_userVO.getHospitalCode(),_userVO);
					interfaceTypeList=opdEssDAO.getInterfaceType(_userVO.getHospitalCode(),_userVO);
								
					essentialMap.put(OpdConfig.ESSENTIALBO_LIST_CLINICAL_SECTION, clinicalSectionList);
					essentialMap.put(OpdConfig.ESSENTIALBO_LIST_INTERFACE_TYPE, interfaceTypeList);
					
				}
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
		
		
		
		
		//Added by swati on date:28-05-2019
		public void getClinicalSectionKey(SinglePageInterfaceMasterVO singlePageInterfaceMasterVO,UserVO _userVO,HttpServletRequest request_p,HttpServletResponse response)
		{
			Map essentialMap = new HashMap();
			List clinicalSectionList = null;
			List interfaceTypeList=null;
				JDBCTransactionContext tx = new JDBCTransactionContext();

				try
				{
					tx.begin();
					OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
					
				 opdEssDAO.getClinicalSectionKey(singlePageInterfaceMasterVO,_userVO,request_p,response);
								
					//essentialMap.put(OpdConfig.ESSENTIALBO_LIST_CLINICAL_SECTION, clinicalSectionList);
					//essentialMap.put(OpdConfig.ESSENTIALBO_LIST_INTERFACE_TYPE, interfaceTypeList);
					
				}
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			
			}
				
		public clinicalSectionMappingMstVO[] getSectionTemplateEssentials(String compositionType,String sectionCode,String templateCode,String MappingType,UserVO _userVO)
		{
			clinicalSectionMappingMstVO[] getSectionTemplateEssentialsVO = null;
			List essentialData = new ArrayList();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			
				try
				{
					tx.begin();
					OpdEssentialDAO objDao = new OpdEssentialDAO(tx);
					getSectionTemplateEssentialsVO = objDao.getSectionTemplateEssentials(compositionType,sectionCode,templateCode,MappingType, _userVO);
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
					System.out.println("error.... Opd Master BO");
					tx.rollback();
					throw new HisApplicationExecutionException();
				}
				finally
				{
					tx.close();
				}
				return getSectionTemplateEssentialsVO;
			}
		
		//Added by Prachi
		public Map getTemplates(String sectionCode,UserVO _userVO)
		{
			Map essentialMap = new HashMap();
			List sectionWiseTemplates = new ArrayList();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();

				OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
				sectionWiseTemplates = objDAO.getTemplates(sectionCode,_userVO);
							
				essentialMap.put(OpdConfig.TEMPLATE_TYPE, sectionWiseTemplates);	
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
		
		
		
		//added by swati on date:03-06-2019
		public String getPreviewData(String sectionId ,String interfaceId,UserVO _userVO)
		{
			Map essentialMap = new HashMap();
			List sectionWiseTemplates = new ArrayList();
			JDBCTransactionContext tx = new JDBCTransactionContext();
			clinicalSectionMappingMstVO[] arrSectionTemplateEssentialsVO=null;
			String rsDATA="";
			try
			{
				tx.begin();

				OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
				 rsDATA = objDAO.getPreviewData(sectionId,interfaceId,_userVO);
				// _response.getWriter().write(json.toString());
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
			return rsDATA;
		}
		
		
		
		//Added by Prachi
		public void saveClinicalSectionComp(List<clinicalSectionMappingMstVO> clnclSecMapMstVOwithoutDuplicates ,UserVO _UserVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
				tx.begin();
				System.out.println("inside save4");
				System.out.println("inside savelist"+clnclSecMapMstVOwithoutDuplicates.size());
				for(clinicalSectionMappingMstVO vo : clnclSecMapMstVOwithoutDuplicates)
				{ 
					System.out.println("sysout");
					OpdEssentialDAO.create(vo,_UserVO);
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

		}*/
	
}//end class
