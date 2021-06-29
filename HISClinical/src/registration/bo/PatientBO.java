package registration.bo;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisAppointmentNotAvailableException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInvalidTokenNumberException;
import hisglobal.exceptions.HisMlcAlreadyExistException;
import hisglobal.exceptions.HisNotEligibleForMLCException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalCardRequired;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.ChangeCategoryVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DeptUnitRosterModDtlVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.DuplicateRenewVO;
import hisglobal.vo.EmpMasterVO;
import hisglobal.vo.EpisodeActionDtlVO;
import hisglobal.vo.EpisodeDeathVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeReferVO;
import hisglobal.vo.EpisodeTriageDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.FileNumberChangeVO;
import hisglobal.vo.MCTSRegistrationVO;
import hisglobal.vo.MLCRevokeEpisodeDetailVO;
import hisglobal.vo.MlcDocUploadDtlVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientAdhaarDtlVO;
import hisglobal.vo.PatientBroughtByDtlVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientParichitVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.PoliceExaminationReqtDtlVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.PrimaryCategoryChangeVO;
import hisglobal.vo.RegCardPrintVO;
import hisglobal.vo.RegChargeDtlVO;
import hisglobal.vo.RenewalDetailVO;
import hisglobal.vo.RoomChangeVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.UnitChangeVO;
import hisglobal.vo.UnknownChangeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.VerificationDocumentVO;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;

import opd.dao.DocumentUploadDtlDAO;
import opd.dao.DocumentUploadDtlDAOi;
import opd.dao.OpdEssentialDAO;
import opd.dao.RoomChangeDetailDAO;
import opd.dao.RoomChangeDetailDAOi;
import registration.RegistrationConfig;
import registration.dao.AddressDAO;
import registration.dao.DailyPatientDAO;
import registration.dao.DailyPatientDAOi;
import registration.dao.EpisodeDAO;
import registration.dao.EpisodeRefDtlDAO;
import registration.dao.EssentialDAO;
import registration.dao.PatientBroughtByDtlDAO;
import registration.dao.PatientDAO;
import registration.dao.PatientDeathDetailDAO;
import registration.dao.PatientDeathDetailDAOi;
import registration.dao.PatientIdDetailDAO;
import registration.dao.PatientImageDtlDAO;
import registration.master.dao.EpisodeTriageDetailDAO;
//import startup.UserMgmtConfigXmlHandler;
import vo.registration.PatientIdVO;

/**
 * PatientBO is a class which specifies the business logic for all the transactions. PatientBO class provides a standard
 * interface between Controller and Data Access Objects.
 * 
 * @author AHIS*
 */

public class PatientBO implements PatientBOi
{

	@Override
	public EpisodeVO[] newPatRegistration(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, UserVO _userVO, HttpServletRequest _request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] newPatSplRegistration(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, UserVO _userVO, HttpServletRequest _request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO searchPatientByCrNo(PatientVO objPatientVO_p, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientDAO objPatientDAO = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			PatientImageDtlDAO patImageDtlDao = new PatientImageDtlDAO(tx);
			PatientImageDtlVO patImageDtlVO = new PatientImageDtlVO();
			objPatientVO_p.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);

			objPatientVO_p = objPatientDAO.retrieveByCrNo(objPatientVO_p, objUserVO_p);
			String fname = "(Unknown)" + objPatientVO_p.getPatFirstName();
			
			
			
			if (objPatientVO_p.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)) objPatientVO_p.setPatFirstName(fname);
			if (objPatientVO_p.getPatAge() != null)
			{
				String patAgeWithUnit = objPatientVO_p.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				objPatientVO_p.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) objPatientVO_p.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) objPatientVO_p.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) objPatientVO_p.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) objPatientVO_p.setPatAgeUnit("Wk");
				if (ageunit.equalsIgnoreCase("min")) objPatientVO_p.setPatAgeUnit("D");//by Mukund for dob conversion to Days from minutes
			}
			//To fetch the Uploaded Image
			try
			{
			if (objPatientVO_p.getIsImageUploaded() != null && objPatientVO_p.getIsImageUploaded().equalsIgnoreCase("1"))
			{
				patImageDtlVO=patImageDtlDao.getPatientDefaultImageByCrNo(objPatientVO_p.getPatCrNo(),objUserVO_p);
				//added by manisha gangwar date:28.3.2018
				 byte[] getImageDoc= patImageDtlDao.latestFetchImagePostgres(patImageDtlVO.getFileNo());
				 objPatientVO_p.setImageFile(getImageDoc);
				 
				
				 
				//boolean flag =HisFileControlUtil.isWindowsOS();
				boolean flag =true;
				if(!flag)
				{
					patImageDtlVO.setFilePath("/root"+patImageDtlVO.getFilePath()); 
				}

				//objPatientVO_p.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
				//objPatientVO_p.setImageFileName(patImageDtlVO.getFilePath() + patImageDtlVO.getFileNo());
				objPatientVO_p.setImageFileName(patImageDtlVO.getFileNo());
			}
			
			
			/*if (objPatientVO_p.getPatDocType() != null)
			{
				objPatientVO_p=patImageDtlDao.fetchVerificationDoc(objPatientVO_p,objUserVO_p);
				//added by manisha gangwar date:28.3.2018
				 byte[] getDoc= patImageDtlDao.latestFetchDocPostgres(objPatientVO_p.getVerificationDocumentId());
				 objPatientVO_p.setVerificationDocumentFile(getDoc);
				
					
				 
				
				 
				//boolean flag =HisFileControlUtil.isWindowsOS();
				boolean flag =true;
				if(!flag)
				{
					patImageDtlVO.setFilePath("/root"+patImageDtlVO.getFilePath()); 
				}

				//objPatientVO_p.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
				//objPatientVO_p.setImageFileName(patImageDtlVO.getFilePath() + patImageDtlVO.getFileNo());
				objPatientVO_p.setImageFileName(patImageDtlVO.getFileNo());
			}
			*/
			
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
			
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return objPatientVO_p;
	}
	

	@Override
	public PatientVO searchPatientByCrNo(PatientVO _patientVO, UserVO _userVO,
			String visitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO[] searchPatient(HashMap _searchMap, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO[] patVO = null;
		PatientDAO patientDao = new PatientDAO(tx);

		try
		{
			tx.begin();
			patVO = patientDao.searchPatient(_searchMap, _userVO);

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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patVO;
	}

	@Override
	public String checkPatientStatus(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO[] getCrNoForModification(UserVO _userVO,
			String episodeVisitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getCrNoModification(UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchPatientEpiosdeByCrNo(PatientVO _patientVO,
			UserVO _userVO, String visitType, String isConfirmed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] newDeptVisitStamp(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] newDepartmentVisitStamp(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,
			PatientVO _oldPatientVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] newSplUnitVisitStamp(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,
			PatientVO _oldPatientVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] newDepartmentVisitStampRoomWise(
			EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO,
			EpisodeRefDtlVO episodeRefVO, UserVO _userVO,
			PatientVO _oldPatientVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] oldDeptVisitStamp(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, UserVO _userVO, EpisodeRefDtlVO episodeRefVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean patCategoryChange(PatientVO _patientVO,
			EpisodeVO[] _arrEpisodeVO, ChangeCategoryVO[] _arrChangeCategoryVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkModify(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AddressVO[] getPatAddressAll(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressVO checkAddModify(AddressVO[] _arrAddressVO,
			PatientVO _patientVO, AddressVO addVO, UserVO _userVO,
			String add_modify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO getPatientDtl(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public PatientVO searchPatientByIdNo(String _idNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO getPrevSystemPatDetail(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] duplicateCardPrinting(RegChargeDtlVO[] _regChargeVO,
			RegCardPrintVO[] _regCardPrintVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnitChangeVO unitChangeOldDeptVisit(PatientVO _patientVO,
			EpisodeVO _episodeVO, UnitChangeVO _unitChangeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO emergencyPatRegistration(PatientVO _patientVO,
			PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO,
			HttpServletRequest _request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO emergencyOldDeptVisitStamp(PatientVO _patientVO,
			EpisodeVO _episodeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean emergencyPatDtlModificationREG(PatientVO _patientVO,
			PatientBroughtByDtlVO patientBroughtByDtlVO,
			PatientVO _patientVOOldData, UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void savePatientNotAttendedDetail(EpisodeActionDtlVO epActionDtlVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientAttendedAdmittedDetail(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MlcVO patMlcDtl(MlcVO _mlcVO, UserVO _userVO,
			PatientBroughtByDtlVO _patBroughtByDtlVO,
			PoliceVerificationDtlVO policeVerDtlVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO getMlcDtl(PatientVO _patientVO, MlcVO _mlcVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyMlcDtl(MlcVO _mlcVO, UserVO _userVO,
			PatientBroughtByDtlVO _patBroughtByDtlVO,
			PoliceVerificationDtlVO policeVerDtlVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveDeathDetail(EpisodeDeathVO _episodeDeathVO,
			PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveBroughtDeathDetail(EpisodeDeathVO _episodeDeathVO,
			DailyPatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientAttendedDisposed(
			EpisodeDiagnosisVO[] _episodeDiagVO,
			EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientReferredOut(EpisodeReferVO _episodeReferVO,
			EpisodeActionDtlVO episodedtlActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientAttendedObserved(
			EpisodeDiagnosisVO[] _episodeDiagVO,
			EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientAttendedRefInCasuality(
			EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientRefInWard(EpisodeActionDtlVO _episodedtlActionDtlVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EpisodeVO isEmergency(PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOpdVisitConfirm(EpisodeVO _episodeVO,
			EpisodeActionDtlVO _episodeActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOpdEpisodeClose(EpisodeVO _episodeVO,
			EpisodeActionDtlVO _episodeActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EpisodeVO[] isOpd(PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO isMlcEligible(PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO isCsultyMlcEligible(PatientVO _patVO, String _episodeCode,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO getMlcDtlBasedOnMlcNo(MlcVO _mlcVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO getMlcDtlBasedOnCrNo(MlcVO vo, UserVO _uservo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO getEmgOpenEpisode(PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchPatientEpiosdeByCrNo(PatientVO _patientVO,
			UserVO _userVO, String visitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean unknownToKnownConversion(PatientVO _patientVO,
			PatientVO patientVOOldData, VerificationDocumentVO verifDocVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeReferVO searchPatientRefEpisodeByEpisodeNo(
			PatientVO _patientVO, EpisodeVO _episodeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean referDtlModificationMRD(PatientVO _patientVO,
			EpisodeReferVO _episodeReferVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO[] referredDeptVisitStamp(EpisodeVO[] _arrEpisodeVO,
			EpisodeReferVO _episodeReferVO, PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnknownChangeVO getConvertedToKnownDetails(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeReferVO[] retrieveRefInternalEpisodeDtl(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkPatientStatusByVisitType(PatientVO _patientVO,
			UserVO _userVO, String visitType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO[] searchAllEpisodeByCrNo(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO isPatientMlc(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO[] getMlcDtlBasedOnCrNoandImage(MlcVO _mlcVo, UserVO _uservo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMlcDoc(MlcVO _mlcVo, UserVO _uservo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkOpenEmgEpisodeByCrNo(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO getLastEpisodeInEmergency(PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] retrieveAllReferredEpisodesByCrNo(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeReferVO[] getAllOpenOPDEpisodes(String crNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeRefDtlVO[] retrieveAllOpenOPDEpisodes(String crNo,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeRefDtlVO[] retrieveAllSpecialEpisodes(String crNo,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getAllOpenEpisodes(String crNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePrimaryCategory(
			PrimaryCategoryChangeVO _primCatChangeVO, UserVO _userVO,
			VerificationDocumentVO _veriDocVO, EmpMasterVO _empMasterVO,
			String _patIdNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeSecondaryCategory(
			SecondaryCategoryChangeVO[] _secCatChangeVO,
			SecondaryCategoryChangeVO[] secCatRevokeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO[] getMapDeptWiseFileNoChange(String crNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] getDeptWiseRenewalOfRegistration(String crNo,
			UserVO _userVO, String patPrimaryCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getMapDeptWiseFileNo(String crNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] changeFileNo(FileNumberChangeVO[] _fileNoChangeVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNewDeptVisitAmount(String crNo, String primCatCode,
			String deptcode, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getDeptsForRenewal(String crNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveRenewalDtl(EpisodeVO _episodeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO[] searchOldPatientEpisodesByCrNo(String crNo,
			UserVO _userVO, String visitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchOldPatientEpisodesByCrNo(String crNo,
			UserVO _userVO, String visitType, String isRenewal,
			String patCatagoryCode, String tariffId, String serviceId,
			String episodeType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchOldPatientEmgEpisodesByCrNo(String crNo,
			UserVO _userVO, String visitType, String renewalTariffId,
			String patPrimaryCatCode, String registrationServiceId,
			String isRenewal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean patDtlModificationMRD(PatientVO _patientVO,
			PatientVO _patientVOOldData, UserVO _userVO,
			VerificationDocumentVO _verDoc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveRenewalDtl(EpisodeVO[] _episodeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO[] saveNewSpVisiStamp(EpisodeVO[] episodeVO, UserVO userVO,
			PatientVO patientVO, RegChargeDtlVO regChargeDtlVO,
			EpisodeRefDtlVO episodeRefDtlVO, PatientVO _oldPatientVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyPatientVO searchDailyPatientByCrNo(
			DailyPatientVO dailyPatientVO, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyPatientVO searchDailyPatientByCrNoWithoutHospital(
			DailyPatientVO dailyPatientVO, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO[] searchPatientByNationalID(String nationalID,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO[] searchPatientByContactNo(String contactNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO[] searchPatientByEmployeeID(String employeeID,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRenewalDetail(PatientVO _patientVO, String _amount,
			UserVO _userVO, String _newExpiryDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EpisodeVO getEpisodeVisitByCrno(String _patCrNo, String _visitDate,
			String _unitCode, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDiagnosisDetails(EpisodeVO episodeVO,
			EpisodeDiagnosisVO[] episodeDiagnosisVO, UserVO userVO,
			String _episodeStatusInVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveMlcDocUpload(MlcDocUploadDtlVO[] _mlcDocUploadDtlVOs,
			MlcVO[] _mlcVo, UserVO userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VerificationDocumentVO[] getVerificationDocumentDtl(
			VerificationDocumentVO verficationVo, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeRefDtlVO[] getReferPat(UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeRefDtlVO[] getSCReferPat(UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO[] getMlcDtlArrayBasedOnCrNo(String crNo, UserVO _uservo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO[] getMlcDtlArrayBasedOnMlcNo(String mlcNo, UserVO _uservo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] getAllOpenEpisodesVisitedToday(String _patCrNo,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeTriageDetailDAO dao = new EpisodeTriageDetailDAO(tx);

		try
		{
			tx.begin();
			
			// Confirm the Visit on Out Time Entry
			if(_triVO.getOutDate()!=null)
			{
				EpisodeVO episodeVO = new EpisodeVO();
				HelperMethods.populate(episodeVO, _triVO);			
				episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
				episodeVO.setComplainDetail("");
				episodeVO.setQueNo(_triVO.getSerialNo());
				//Episode Close/Open
				episodeVO.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);
				
				EpisodeTriageDetailDAO episodeDAO = new EpisodeTriageDetailDAO(tx);
				episodeDAO.create(_triVO, _userVO);
				
				DailyPatientDAO patDao = new DailyPatientDAO(tx);
				patDao.updateIsconfirmed(episodeVO.getPatCrNo(), episodeVO.getQueNo(), episodeVO.getEpisodeVisitNo(), episodeVO.getEpisodeCode(), 
						episodeVO.getIsConfirmed(), _userVO);
				
				/*EpisodeSummaryDAOi episodeSummaryDAO = new EpisodeSummaryDAO(tx);
				episodeSummaryDAO.create(_episodeSummaryVO, _userVO);
	
				// When Episode is closed
				if(_episodeCloseVO != null)
				{
					EpisodeCloseDAOi episodeeCloseDAO = new EpisodeCloseDAO(tx);
					episodeeCloseDAO.create(_episodeCloseVO, _userVO);
				}*/
			}
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
	}

	@Override
	public void modifyTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeTriageDetailDAO dao = new EpisodeTriageDetailDAO(tx);

		try
		{
			tx.begin();
			
			// Confirm the Visit on Out Time Entry
			if(_triVO.getOutDate()!=null)
			{
				EpisodeVO episodeVO = new EpisodeVO();
				HelperMethods.populate(episodeVO, _triVO);			
				episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
				episodeVO.setComplainDetail("");
				episodeVO.setQueNo(_triVO.getSerialNo());
				//Episode Close/Open
				episodeVO.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);
				
				EpisodeTriageDetailDAO episodeDAO = new EpisodeTriageDetailDAO(tx);
				episodeDAO.update(_triVO, _userVO);
				
				DailyPatientDAO patDao = new DailyPatientDAO(tx);
				patDao.updateIsconfirmed(episodeVO.getPatCrNo(), episodeVO.getQueNo(), episodeVO.getEpisodeVisitNo(), episodeVO.getEpisodeCode(), 
						episodeVO.getIsConfirmed(), _userVO);
				
				/*EpisodeSummaryDAOi episodeSummaryDAO = new EpisodeSummaryDAO(tx);
				episodeSummaryDAO.create(_episodeSummaryVO, _userVO);
	
				// When Episode is closed
				if(_episodeCloseVO != null)
				{
					EpisodeCloseDAOi episodeeCloseDAO = new EpisodeCloseDAO(tx);
					episodeeCloseDAO.create(_episodeCloseVO, _userVO);
				}*/
			}
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		

		
	}

	@Override
	public EpisodeTriageDetailVO getPatTriageDtl(String _crNo, String _epiCode,
			String _visitNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] saveReprintCard(RegCardPrintVO[] regCardPrintVO,
			UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientBroughtByDtlVO searchPatientBropughtByDetailByCrNo(
			PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO retrieveByEpisodeNo(EpisodeVO _epiVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeRefDtlVO[] getOldPatReferDtl(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchOldPatientEpisodesByCrNoByDept(String crNo,
			String dept, UserVO _userVO, String visitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchOldPatientEpisodesByCrNoByUnit(String crNo,
			String unit, UserVO _userVO, String visitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getAllEpisodesForDuplicateReprint(String crNo, UserVO userVO,
			String _choice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveParichitDetails(PatientParichitVO _patParichitVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getPrimaryCatListExceptPatientCat(String patCat, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] getAllOpenTodayMLCEpisodes(String _patCrNo,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean revokeMLCEpisodes(MLCRevokeEpisodeDetailVO[] _mlcRevVOs,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	/*@Override
	public void changePatientRoom(RoomChangeVO _roomChangeVO,
			EpisodeVO _episodeVO, DailyPatientVO _dailyPatientVO,
			UserVO _userVO, String _unitCode) {
		// TODO Auto-generated method stub
		
	}*/
	
	//Added by Vasu on 21.Feb.2019
	public void changePatientRoom(RoomChangeVO _roomChangeVO,EpisodeVO _episodeVO,DailyPatientVO _dailyPatientVO,UserVO _userVO,String _unitCode)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
        String  _queNo="";
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			
			DailyPatientDAO _dailyPatientDAO=new DailyPatientDAO(tx);
			RoomChangeDetailDAOi _roomChangeDAO=new RoomChangeDetailDAO(tx);
			EpisodeDAO _episodeDAO=new EpisodeDAO(tx);
			
			
			//_queNo = _dailyPatientDAO.getQueNumber(_userVO,_unitCode,_roomChangeVO.getToRoomCode());
			_episodeVO.setQueNo(_queNo);
			_dailyPatientVO.setQueNo(_queNo);
			
			_roomChangeDAO.changePatientRoomDetails(_roomChangeVO,_userVO);
			_episodeDAO.changeEpisodeDetails(_episodeVO,_userVO);
			_dailyPatientDAO.changeDailyPatientDetails(_dailyPatientVO,_userVO);
			
			
			
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
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		
	}
	

	@Override
	public Map getPatientAuditTrailEssentials(String _patCrNo, UserVO _userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		Map EssentialMap=new HashMap();
		try
		{
			PatientDetailVO _patDetailVO=null;
			PatientDetailVO[] _patAuditDetailVO=null;
			AddressVO[] _addressVO=null;
			PrimaryCategoryChangeVO[] _primaryCatgVO=null;
			SecondaryCategoryChangeVO[] _secondaryCatgVO=null;
			UnknownChangeVO[] _unknownChangeVO=null;
			MlcVO[] _mlcVO=null;
			
			tx.begin();
			PatientDAO _patientDao = new PatientDAO(tx);
			
			_patDetailVO = _patientDao.getPatientHrgtEssentials(_patCrNo, _userVO);
		if(_patDetailVO!=null)
			EssentialMap.put(RegistrationConfig.VO_OF_HRGT_PATIENT_DTL,_patDetailVO);
			
		//commented by Dheeraj on 14-Nov-2018
			
		/*	_patAuditDetailVO = _patientDao.getPatientAuditEssentials(_patCrNo, _userVO);
		if(_patAuditDetailVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_PATIENT_AUDIT_DTL,_patAuditDetailVO);*/
			 
		
			_addressVO=_patientDao.getPatientAddressDetailsEssentials(_patCrNo, _userVO);
		if(_addressVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_PATIENT_ADD_DTL,_addressVO);
			
			
			_primaryCatgVO=_patientDao.getPrimaryCategotyChangeDetailsEssentials(_patCrNo, _userVO);
		if(_primaryCatgVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_PRICAT_CHANGE_DTL,_primaryCatgVO);
			
			
			_secondaryCatgVO=_patientDao.getSecondaryCategotyChangeDetailsEssentials(_patCrNo, _userVO);
		if(_secondaryCatgVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_SECCAT_CHANGE_DTL,_secondaryCatgVO);
			
			
			_unknownChangeVO=_patientDao.getUnknownToKnownChangeDetails(_patCrNo, _userVO);
		if(_unknownChangeVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_UNKNOWN_CHANGE_DTL,_unknownChangeVO);
			
			
		_mlcVO=_patientDao.getPatientMlcChangeDetails(_patCrNo, _userVO);
		if(_mlcVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_PATIENT_MLC_DTL,_mlcVO);	
		
		
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return EssentialMap;
	}

	@Override
	public void savePatientAttendedUnderObservation(
			EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PatientBroughtByDtlVO searchPatientBropughtByDetailByCrNoNew(
			PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map checkEligibleForMLC(PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePoliceVerification(PoliceVerificationDtlVO policeVerDtlVO,
			UserVO userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PoliceVerificationDtlVO getPoliceVerDtl(MlcVO mlcVO, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePatientDeathDetail(ANCDetailVO ancDetailVO,
			PatientDeathDetailVO patDeathDtlVO, UserVO userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkRecordAdded(String crNo, UserVO userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PatientDeathDetailVO getDeathDetailByCrNo(String crNo, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressVO getPatAddress(String crNo, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	//public Map getHandoverToDetail(String crNo, UserVO userVO) {
		// TODO Auto-generated method stub
	//	return null;
//	}

	@Override
	public void saveHandoverToDetail(PatientDeathDetailVO patDeathDtlVO,
			String flagForPrint, UserVO userVO) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	//public PatientDeathDetailVO[] getDeadPatientList(UserVO userVO) {
		// TODO Auto-generated method stub
		//return null;
	//}

	@Override
	public String getPatientMlcNo(PatientDetailVO selectedPatientVO,
			UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ANCDetailVO getPatientANCDetail(String crNo, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentUploadDtlVO[] getMlcUploadDetail(MlcVO _mlcVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public void saveDocumentDetail(DocumentUploadDtlVO[] docUploadVos,
			DocumentUploadDtlVO[] documentUploadRevokeDtlVOs, UserVO userVO) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public EpisodeVO[] getPatientAdmittedEpisodes(String crNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void revokeSecondaryCategory(
			SecondaryCategoryChangeVO[] _secCatChangeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MlcVO[] getMLCDetail(PatientVO patVO, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRedirectOfOPDPatDtl(List deptUnitRosterModDtlVOList,
			List roomChnageVOList, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EpisodeVO[] saveOfflineRegistration(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDeactivateUnitRoomDtl(List deptUnitRosterModDtlVOList,
			List deptUnitRosterModDtlVOInActiveList, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DailyPatientVO[] getTodayPatientListByDeptUnitCode(String mode,
			String departmentUnitCode, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFileNumberPrinting(EpisodeVO[] episodeVOs,
			RegCardPrintVO[] regCardPrintVOs, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EpisodeVO[] oldDeptVisitStampRoomWise(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, EpisodeRefDtlVO episodeRefDtlVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int patientDetailModification(PatientVO _patientVO,
			PatientVO _patientVOOldData, String addModify,
			AddressVO _arrAddressVO, VerificationDocumentVO _verDoc,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map savePatientVisit(EpisodeVO[] _episodeVO, PatientVO _patVO,
			UserVO _userVO, EpisodeRefDtlVO _episodeRefDtlVO,
			EpisodeVO[] _episodeNewDeptVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map saveSplPatientVisit(EpisodeVO[] _episodeVO, PatientVO _patVO,
			UserVO _userVO, EpisodeRefDtlVO _episodeRefDtlVO,
			EpisodeVO[] _episodeNewDeptVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] saveEmgOldPatientVisit(PatientVO patientVO,
			EpisodeVO[] episodeVO, UserVO userVO,
			EpisodeRefDtlVO episodeRefDtlVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map saveEmergenyPatientVisitStamping(PatientVO patientVO,
			EpisodeVO[] oldVisitEpisodeVO, UserVO userVO,
			EpisodeRefDtlVO episodeRefDtlVO, EpisodeVO[] newVisitEpisodeVO,
			RegChargeDtlVO regChargeDtlVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientAdhaarDtlVO checkDupAdhaarPatient(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveMCTSRegNo(MCTSRegistrationVO MCTSRegistrationVO_p,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PatientVO retrieveByCrNoFromMCTS(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] getAllLatestEpisodesVisited(String _patCrNo,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PatientDetailVO> getPatientsForPoliceExaminationReqt(
			String _patCrNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePoliceExaminationReqtDtl(
			PoliceExaminationReqtDtlVO policeExamReqtDtlVO, UserVO userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PatientDetailVO> getPatientsForPoliceExaminationReportGeneration(
			String strMode_p, String strPatCrNo_p, String strEpisode_p,
			String strEpisodeVisitNo_p, UserVO objUserVO_p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePoliceExaminationReportGenerationDtl(
			PoliceExaminationReqtDtlVO objPoliceExamReqtDtlVO_p,
			UserVO objUserVO_p) {
		// TODO Auto-generated method stub
		
	}
	
	//this method is used for calling the Normal Registration, Emergency Registration 
	///offline Registration Mehotd. This method is synchornized having class level lock on PatientDAO
	//PatientBroughtByDtlVO is used only in Emergency Registration
	//ProcessType: 1=New Registration,2=Offline Registration,3=Emergency Registration,4: Special Clinic Registration
	
	/*public  EpisodeVO[] registerNewPatient(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO,PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO,String ProcessType)
	{
		//JDBCTransactionContext tx=new JDBCTransactionContext();
		EpisodeVO[] arrayEpisodeVO=null;
		EpisodeVO episodeVO=new EpisodeVO();
		synchronized(PatientBO.class)
		{
		if(ProcessType.equals(RegistrationConfig.NEW_REGISTRATION_PROCESS))
			arrayEpisodeVO=newPatRegistration(_arrEpisodeVO, _patientVO, _userVO);
		if(ProcessType.equals(RegistrationConfig.ROOM_WISE_REGISTRATION_PROCESS))
			arrayEpisodeVO=saveOfflineRegistration(_arrEpisodeVO, _patientVO, _userVO);
		if(ProcessType.equals(RegistrationConfig.EMERGENCY_REGISTRATION_PROCESS)){
			episodeVO=emergencyPatRegistration(_patientVO, _patBroughtByDtlVO, _userVO);
			arrayEpisodeVO=new EpisodeVO[1];
			arrayEpisodeVO[0]=episodeVO;
		}	
		}
		//if(ProcessType.equals(RegistrationConfig.SPECIAL_REGISTRATION_PROCESS))
			
		return arrayEpisodeVO;
	}*//*
	public EpisodeVO[] newPatRegistration(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO,  HttpServletRequest _request) throws HisDuplicateRecordException
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO daoObj = null;
		boolean isDuplicateReg=false;
		String[] crNoArr = null;
		//synchronized (PatientDAO.class)
		//{
		try
		{
			daoObj = new HisDAO("Registration","PatientBO");
			
			tx.begin();
			Map essentialMap = new HashMap();

			PatientDAO patDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			BPLDetailsDAO bplDAO = new BPLDetailsDAO();
			RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDtlDao=new DirectChargeDetailDAO(tx);
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			List allPatientVoList=new ArrayList();

			
			int arrEpisodeVOLength=_arrEpisodeVO.length;
			
			RegChargeDtlVO[] regChgDtlVO = new RegChargeDtlVO[arrEpisodeVOLength];
			DirectChageDetailVO[] directChargeVO=new DirectChageDetailVO[arrEpisodeVOLength];
			DailyPatientVO dailyPatVO = new DailyPatientVO();
			
			
			if(_patientVO.getPatDocType().indexOf("|")>0)
			{
				_patientVO.setPatDocType(_patientVO.getPatDocType().split("\\|")[0]);
			}
			for (int i = 0; i < arrEpisodeVOLength; i++)
			{
				EpisodeReferVO _episodeReferVO = new EpisodeReferVO();
				EpisodeRefDtlVO episodeRefDtlVO = new EpisodeRefDtlVO();
				
				_patientVO.setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
				_patientVO.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_NOT_ADMITTED);
				_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				_patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
			
				_patientVO.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.getPatAddress().setSeatId(_patientVO.getSeatId());
				AddressVO addressVO=_patientVO.getPatAddress();
				addressVO.setPatIsUrban(_patientVO.getPatIsUrban());
				
				
				///temp code for trapping error
				if(_arrEpisodeVO==null || arrEpisodeVOLength==0)
						throw new HisApplicationExecutionException("Episode Array is Null");

				
					dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
					PatientBOHelper.setNewPatRegEpisodeVO(_arrEpisodeVO[i]);
					
					if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD))
						{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);						
						}
					else
					{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
					}
					_arrEpisodeVO[i].setPatCrNo(dailyPatVO.getPatCrNo());
					_arrEpisodeVO[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
					_arrEpisodeVO[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());

					_arrEpisodeVO[i].setIsValid(Config.IS_VALID_ACTIVE);
					
					_patientVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
					HelperMethods.populate(dailyPatVO, _arrEpisodeVO[i]);
					HelperMethods.populate(dailyPatVO, _patientVO);

					dailyPatVO.setIsReferred(_patientVO.getIsReferred());
					
					dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
					dailyPatVO.setEpisodeVisitNo("1");
					
					dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);

					if(!_patientVO.getIsDuplicate().equals("1")){
					crNoArr=essentialDAO.checkDuplicateRegistration(_patientVO, _userVO);
					if(crNoArr!=null)					{
					PatientVO[] patientVOArr = new PatientVO[crNoArr.length];
					
					for(i=0;i<crNoArr.length;i++)
					{
						PatientVO vo=new PatientVO();
						vo.setPatCrNo(crNoArr[i]);
						patientVOArr[i]=vo	;
					}
					for(i=0;i<crNoArr.length;i++)
					{
						allPatientVoList.add(searchPatientByCrNo(patientVOArr[i], _userVO));	
					}

					essentialMap.put(RegistrationConfig.ALL_PATIENT_VO_LIST,allPatientVoList);
					}
						if(crNoArr!=null)
						{	WebUTIL.setMapInSession(essentialMap, _request);
						throw new HisDuplicateRecordException("Duplicate Registration");
						}
			}
						
						
				dailyPatVO.setDepartmentUnitCode(dailyPatVO.getDepartmentCode());
				//dailyPatVO=dailyPatDao.generateQueueNumber(dailyPatVO, _userVO);				
				dailyPatVO=dailyPatDao.generateQueueNumberDeptWise(dailyPatVO, _userVO);
				
				//generating the Cr Number
				String CrNO=patDao.generateCrNumber(_userVO);
				_patientVO.setPatCrNo(CrNO);
				_patientVO.getPatAddress().setPatCrNo(_patientVO.getPatCrNo());
				_patientVO.setSeatId(_userVO.getSeatId());
				
				dailyPatVO.setPatAge(_patientVO.getPatAge());
				dailyPatVO.setPatDOB(_patientVO.getPatDOB());
				dailyPatVO.setPatAgeUnit(_patientVO.getPatAgeUnit());
				dailyPatVO.setIsActualDob(_patientVO.getIsActualDob());
				
				///get age or DOB
				//_patientVO=patDao.getDOBAndAge(_patientVO);
				
				//seting dialypatientvo details
				dailyPatVO.setPatCrNo(CrNO);
				//dailyPatVO.setPatAge(_patientVO.getPatAge());
				//dailyPatVO.setPatDOB(_patientVO.getPatDOB());
				BPLDetailsVO bplVO=new BPLDetailsVO();
				if(dailyPatVO.getPatPrimaryCatCode().equals(Config.PRIMARY_CATEGORY_BPL_CODE))//BPL Category
				{
					HelperMethods.populate(bplVO, _patientVO);
					bplVO.setPatCrNo(CrNO);
					bplVO.setMemberName(_patientVO.getPatFirstName()+" "+_patientVO.getPatMiddleName()+" "+_patientVO.getPatLastName());
					bplVO.setPatBPLCardNo(_patientVO.getPatIdNo());
					//bplVO = bplDAO.saveBPLDetails(daoObj,bplVO, _userVO);
				}
									
				////setching chagres again in case the ajax for charge has not responded
				//change done by : Prakhar Misra
				//Dated: 3-Feb-2012
				String billAmount=essentialDAO.getBillAmountBasedOnCategory(dailyPatVO.getPatPrimaryCatCode(), _userVO);
				
				
				//explicity fetching and setting the amount in VO
				dailyPatVO.setPatAmountCollected(billAmount);
				//insert into daily
					dailyPatVO = dailyPatDao.createNewRegistration(daoObj,dailyPatVO, _userVO);
					
					
					//_patientVO.setPatAge(dailyPatVO.getPatAge());
					//_patientVO.setPatDOB(dailyPatVO.getPatDOB());
					
					//explicity fetching and setting the amount in VO
					_patientVO.setPatAmountCollected(billAmount);
					
					//Logic Changed for Hospital Wise Renewal--Start
					//insert into patient
					//_patientVO = patDao.create(daoObj,_patientVO, _userVO);
					
					int key=Integer.parseInt(Config.RENEWAL_TYPE);
					System.out.println("key======"+key);
					switch(key){
					case 0: _patientVO=patDao.create(daoObj,_patientVO, _userVO);  break; 
						
					case 1: 
						 	_patientVO.setExpiryDate(Config.HOSPITAL_RENEWAL_EXPIRY_MONTH);
						 	_patientVO=patDao.create(daoObj,_patientVO, _userVO,RegistrationConfig.ISEXPIRY_TYPE_MONTH);
						 	//_patientVO=patDao.create(daoObj,_patientVO, _userVO);   
							 break;
					case 2: 
						    //_patientVO.setExpiryDate(Config.HOSPITAL_RENEWAL_EXPIRY_DAY); 
						  //to calculate expiry date out of procedure so that it cold be used for prinintg
							String expiryDate=patDao.getExpiryDate(_userVO);
							_patientVO.setExpiryDate(expiryDate);
						   // _patientVO=patDao.create(_patientVO, _userVO,RegistrationConfig.ISEXPIRY_TYPE_DAY);
						    _patientVO=patDao.create(daoObj,_patientVO, _userVO);   
						break;
						
					case 3:	_patientVO=patDao.create(daoObj,_patientVO, _userVO); break;
					
					case 4: _patientVO=patDao.create(daoObj,_patientVO, _userVO); break;
					
					case 5:	_patientVO=patDao.create(daoObj,_patientVO, _userVO); break;
						
					}
					
					////Logic Changed for Hospital Wise Renewal--End 
					
					if(dailyPatVO.getIsActualDob().equals("1"))
					{
						_patientVO.setPatAge(dailyPatVO.getPatAge());
						_patientVO.setPatAgeUnit("");
					}
					
					//insert into address dteial
					addDao.createNewAddress(daoObj,addressVO, _userVO);
					
					
					
					
					HelperMethods.populate(_arrEpisodeVO[i], dailyPatVO);
					
					// Create new entry in Episode detail
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{

						_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						_episodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
						episodeRefDtlVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					}
					else _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					
					_arrEpisodeVO[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
				
					_arrEpisodeVO[i].setEntryDate(_patientVO.getSystemDate());
					//PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(daoObj,_arrEpisodeVO[i], _userVO, tx);
					
					_arrEpisodeVO[i].setDepartmentCode(_arrEpisodeVO[i].getDepartmentUnitCode().substring(0, 3));
					
					
					//changed on 14-feb-2012 by prakhar misra
					//to calculate expiry date out of procedure so that it cold be used for prinintg
					//code commented for hospital wise renewal
					//String expiryDate=episodeDAO.getExpiryDate(_arrEpisodeVO[i],_userVO);
					
					//_arrEpisodeVO[i].setExpiryDate(expiryDate);
					//code commented for hospital wise renewal--end
					
					episodeDAO.create(daoObj, _arrEpisodeVO[i], _userVO);
					

					
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						HelperMethods.populate(_episodeReferVO, _patientVO);
						HelperMethods.populate(_episodeReferVO, _arrEpisodeVO[i]);
						HelperMethods.populate(episodeRefDtlVO, _arrEpisodeVO[i]);
						
						episodeRefDtlVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
						episodeRefDtlVO.setExternalHospitalCode(_patientVO.getPatRefGnctdHospitalCode());
						episodeRefDtlVO.setExternalHospitalDepartment(_patientVO.getPatRefGnctdHospitalDept());
						episodeRefDtlVO.setExternalHospitalDepartmentUnit(_patientVO.getPatRefGnctdHospitalDeptUnit());
						episodeRefDtlVO.setExternalHospitalDoctorName(_patientVO.getPatRefDoctor());
						episodeRefDtlVO.setExternalHospitalName(_patientVO.getPatRefHospitalName());
						episodeRefDtlVO.setExternalHospitalPatCrNo(_patientVO.getPatRefGnctdHospitalCrno());
						episodeRefDtlVO.setToDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
						episodeRefDtlVO.setToDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
						episodeRefDtlDAO.createNewRegistration(daoObj,episodeRefDtlVO, _userVO);

					}
					
					regChgDtlVO[i] = new RegChargeDtlVO();
					regChgDtlVO[i].setTariffId(_userVO.getTariffID());
					regChgDtlVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
					regChgDtlVO[i].setModuleId(_userVO.getModuleId());
					HelperMethods.populate(regChgDtlVO[i], _arrEpisodeVO[i]);
					regChgDtlVO[i].setPatAmountCollected(_patientVO.getPatAmountCollected());
					regChgDtlVO[i].setSystemIPAddress(_patientVO.getSystemIPAddress());
					System.out.println("regChgDtlVO[i].getPatAmountCollected()...."+Float.parseFloat(regChgDtlVO[i].getPatAmountCollected()));
					if (!(new Float("0").compareTo(Float.parseFloat(regChgDtlVO[i].getPatAmountCollected()))==0 || regChgDtlVO[i].getPatAmountCollected().equals("") || regChgDtlVO[i]
							.getPatAmountCollected() == null)) 
					{
						///registration module billing
						regChgDtlDAO.create(daoObj,regChgDtlVO[i], _userVO);
						directChargeVO[i]=new DirectChageDetailVO();
						HelperMethods.populate(directChargeVO[i],regChgDtlVO[i]);
						directChargeDtlDao.create(daoObj,directChargeVO[i], _userVO);
						////Billing module billing///
						//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], _userVO);
					}
					
					
					PatientAdhaarDtlDAO patAdhaarDtlDao = new PatientAdhaarDtlDAO(tx);
					if((_patientVO.getPatNationalId()!=null)&&!(_patientVO.getPatNationalId().equals("")))
						patAdhaarDtlDao.create(_patientVO, _userVO);
					
					/*** Query to fetch Disclaimer*********************/
					/*String patRegCatCode=_patientVO.getPatRegCatCode();
					String usablityFlag="";
					if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
						usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
					if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL)){
						usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
					}*/	/*
					_arrEpisodeVO[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL,_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
					
				/***********************************************/	/*
					synchronized (daoObj) {
						daoObj.fire();
					}
					
				}

			}
		catch(HisDuplicateRecordException e){
			tx.rollback();
			throw new HisDuplicateRecordException("Duplicate Registration");
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			//e.printStackTrace();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			//e.printStackTrace();
			
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
			if (daoObj != null) {
				//daoObj.free();
				daoObj = null;
			}
			tx.close();
			
		}
		//}
		return _arrEpisodeVO; // <<<
	}// each VisitStampVO keeps PatientVO

	/**
	 * Creates the template for printing of OPD Slip
	 *//*
	public void createSlip(String remoteAddress, List dataList)
	{
		StringBuffer fileContents = new StringBuffer();
		for (int i = 0; i < dataList.size(); i += 9)
		{
			String testStr = "";
			String spaceOne = "";
			String spaceTwo = "";
			String spaceThree = "";
			// String spaceFour = "";

			for (int j = 0; j < (10 + (30 - ((String) dataList.get(i + 0)).length())); j++)
				spaceOne += " ";
			for (int j = 0; j < (10 + (30 - ((String) dataList.get(i + 2)).length())); j++)
				spaceTwo += " ";
			for (int j = 0; j < (10 + (30 - ((String) dataList.get(i + 4)).length())); j++)
				spaceThree += " ";

			StringTokenizer st = new StringTokenizer((String) dataList.get(i + 6), ",");
			int k = 0;
			while (st.hasMoreElements())
			{
				testStr += (String) st.nextElement() + ",";
				k++;
				if (k % 2 == 0)
				{
					testStr += " \n                 ";
				}
			}
			try
			{
				fileContents.append("    CR Number  : " + (String) dataList.get(i + 0) + "		Name       : " + (String) dataList.get(i + 1) + spaceOne
						+ (String) dataList.get(i + 2) + spaceOne + (String) dataList.get(i + 3) + "\n");
				fileContents.append("    Age        : " + (String) dataList.get(i + 4) + "/" + (String) dataList.get(i + 5) + "	Gender  : "
						+ (String) dataList.get(i + 5) + "\n");
				fileContents.append("\n\n\n\n\n");
				System.out.println("hello....  print...");
			}
			catch (Exception e)
			{
				//System.out.println("Exception in createSlip() " + e);
				System.out.println(e.getMessage());
				//e.printStackTrace();
				
				// tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
		}// END FOR

		Printer print = new Printer();
		print.createTempFile(fileContents.toString(), remoteAddress);
		System.out.println("Print hello....  ");
	}

	/**
	 * Retrieves patient details on the basis of CR No from the Patient Dtl Table. Provides age of the patient according to
	 * the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 * 
	 * @param _patientVO Provides CR No to be searched.
	 * @param _userVO Provides User details.
	 * @return PatientVO with values stored in DB.
	 *//*
	public PatientVO searchPatientByCrNo(PatientVO _patientVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			PatientImageDtlDAO patImageDtlDao = new PatientImageDtlDAO(tx);
			AddressVO _addressVO = new AddressVO();
			PatientImageDtlVO patImageDtlVO = new PatientImageDtlVO();
			_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			try
			{
				_addressVO = addDao.retrieveByCrNo(_patientVO, _userVO);
			}
			catch (HisRecordNotFoundException e)
			{
			}

			_patientVO = patientDao.retrieveByCrNo(_patientVO, _addressVO, _userVO);
			String fname = "(Unknown)" + _patientVO.getPatFirstName();

			if (_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)) _patientVO.setPatFirstName(fname);

			// if(_patientVO.getPatAge()!=null &&
			// _patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE)){
			if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);
				// System.out.println("_patientVO.getPatAge()1:::" + _patientVO.getPatAge());

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Y");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("M");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("W");
			}
			/*
			 * System.out.println("Patient dob::"+_patientVO.getPatDOB()); System.out.println("Patient
			 * isurban::"+_patientVO.getPatIsUrban()); System.out.println("after populate"); System.out.println("Patient
			 * first name::"+_patientVO.getPatFirstName());
			 * System.out.println("_patientVO.getPatAddCity()"+_patientVO.getPatAddCity());
			 */

			/*if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
			{
				patImageDtlVO = patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(), _userVO);
				boolean flag = HisFileControlUtil.isWindowsOS();
				if (!flag)
				{
					patImageDtlVO.setFilePath("/root" + patImageDtlVO.getFilePath());
				}

				_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
				_patientVO.setImageFileName(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo());
			}*/

			// System.out.println(_patientVO.getPatPrimaryCat()+"XXXXX>>>>>"+_patientVO.getPatPrimaryCatCode());
		/*	if (_patientVO.getPatPrimaryCatCode() == null || _patientVO.getPatPrimaryCatCode().equals("")) throw new HisRecordNotFoundException(
					"Current Patient Category is invalid please change the Patient Category first");
			/*
			 * if (_patientVO.getPatPrimaryCat() == null || _patientVO.getPatPrimaryCat().equals("")) throw new
			 * HisRecordNotFoundException("Current Patient Category is invalid please change the Patient Category first");
			 */
		/*}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _patientVO;
	}

	/**
	 * Checks patient's current status in the hospital. Should be called only after the patient details has been retrieved.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return patStatus. Sets patStatus=11 if Patient status if IPD, patStatus=12 is Patient status is OPD, patStatus=13 if
	 *         Patient is dead.
	 *//*
	public String checkPatientStatus(PatientVO _patientVO, UserVO _userVO)
	{
		String patStatus = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);

			if (_patientVO.getPatStatusCode() != null && _patientVO.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD)) patStatus = "11";
			else if (_patientVO.getPatStatusCode() != null
					&& _patientVO.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_NOT_ADMITTED)) patStatus = "12";
			else if (_patientVO.getPatStatusCode() != null && _patientVO.getPatStatusCode().equals(RegistrationConfig.PATIENT_STATUS_CODE_DEAD)) patStatus = "13";
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patStatus;
	}

	/**
	 * Required when isUnknown field is set to TRUE. Sets all the values in the PatientVO to null.
	 * 
	 * @param _patientVO VO to be set to null
	 * @return PatientVO with null values
	 * @deprecated No replacement.
	 *//*
	public PatientVO setUnknownToNull(PatientVO _patientVO)
	{
		/*
		 * _patientVO.setCurrentDepartment(null); _patientVO.setCurrentDepartmentCode(null); _patientVO.setDepartment(null);
		 */
		// _patientVO.setDepartmentCode(null);
		/*_patientVO.setDeptStaffStudent(null);
		_patientVO.setDesigSemester(null);
		// _patientVO.setIsAddressDelhi(null);
		_patientVO.setIsCMOFree(null);
		// _patientVO.setIsCurrentAddress(null);
		// _patientVO.setIsLock(null);
		// _patientVO.setIsLockReason(null);
		// _patientVO.setIsNProgram(null);
		// _patientVO.setPatAddCity(null);
		/*
		 * _patientVO.setPatAddCityLoc(null); _patientVO.setPatAddCityLocCode(null); _patientVO.setPatAddContactNo(null);
		 * _patientVO.setPatAddCountry(null); _patientVO.setPatAddCountryCode(null); _patientVO.setPatAddDistrict(null);
		 * _patientVO.setPatAddHNo(null); _patientVO.setPatAddPIN(null);
		 
		_patientVO.setPatAddress(null);
		/*
		 * _patientVO.setPatAddState(null); _patientVO.setPatAddStateCode(null); _patientVO.setPatAddStreet(null);
		 * _patientVO.setPatAddType(null); _patientVO.setPatAddTypeCode(null);
		 */
		/*_patientVO.setPatAmountCollected(null);
		/*
		 * _patientVO.setPatCat(null); _patientVO.setPatCatcode(null);
		 */
	/*	_patientVO.setPatFirstName(null);
		// _patientVO.setPatGuardianName(null);
		_patientVO.setPatIdNo(null);
		_patientVO.setPatIsUrban(null);
		_patientVO.setPatLastName(null);
		_patientVO.setPatMaritalStatus(null);
		_patientVO.setPatMaritalStatusCode(null);
		_patientVO.setPatMiddleName(null);
		_patientVO.setPatMotherName(null);
		_patientVO.setPatMonthlyIncome(null);
		// _patientVO.setPatPrimaryCat(null);
		// _patientVO.setPatPrimaryCatCode(null);
		_patientVO.setPatReligion(null);
		_patientVO.setPatReligionCode(null);
		// _patientVO.setPatSalutationCode(null);
		// _patientVO.setPatSecondaryCat(null);
		// _patientVO.setPatSecondaryCatCode(null);
		// _patientVO.setPatStatusCode(null);
		_patientVO.setPrevCrNo(null);

		return _patientVO;
	}

	/**
	 * Retrieves all the CR No entered during last 10 minutes from the Patient Dtl Table.
	 * 
	 * @param _userVO Provides User details.
	 * @return Array of PatientVO populated with CR No.
	 */
	/*public PatientVO[] getCrNoForModification(UserVO _userVO, String episodeVisitType)
	{
		//System.out.println("inside getCrNoForModification()");
		// System.out.println("Patient first name::"+_patientVO.getPatFirstName());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO[] _arrPatientVO =
		{};

		try
		{
			//System.out.println("Begining transaction");
			tx.begin();
			//System.out.println("creating object of PatientDAO");
			PatientDAO patientDao = new PatientDAO(tx);

			//System.out.println("patientDao.getLock()");
			_arrPatientVO = patientDao.retrieveCrNoForModification(_userVO, episodeVisitType);
			//System.out.println("_arrPatientVO.length::" + _arrPatientVO.length);
		}
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("inside  BO Record not found exception");
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			//System.out.println("inside  BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			//System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			//System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		//System.out.println("hi!!!!!!!!!!!!");
		return _arrPatientVO;
	}

	/**
	 * Retrieves all the CR No entered during last 10 minutes from the Patient Dtl Table.
	 * 
	 * @param _userVO Provides User details.
	 * @return List of CR No.
	 */
	/*public List getCrNoModification(UserVO _userVO)
	{
		List lstCrNo = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDAO patientDao = new PatientDAO(tx);
		try
		{
			tx.begin();
			lstCrNo = patientDao.getCrNoForModification(_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException("Record Not found");
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		System.out.println("lstCrNo::  " + lstCrNo);
		return lstCrNo;
	}

	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and isConfirmed status.
	 * Provides last visit details of all episodes which are open presently.
	 * 
	 * @param _patientVO Provides CR No of the patient for which episode details are to be searched.
	 * @param _userVO Provides User details.
	 * @param visitType Specifies type of visit, namely, IPD, OPD, or EMG.
	 * @param isConfirmed Specifies whether visit is confirmed.
	 * @return Array of EpisodeVO with values stored in DB.
	 */
	/*public EpisodeVO[] searchPatientEpiosdeByCrNo(PatientVO _patientVO, UserVO _userVO, String visitType, String isConfirmed)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO =
		{};
		EpisodeVO[] arrVO =
		{};
		try
		{
			
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);

			String str = _patientVO.getPatCrNo();
			_arrEpisodeVO = episodeDao.retrieveByCrNo(_patientVO.getPatCrNo(), _userVO);
			
			EpisodeVO[] _tmpArrEpisodeVO = new EpisodeVO[_arrEpisodeVO.length];
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]
			{};
			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				
				// copying one array elements to a new array skipping some indexes from original array
				if (_arrEpisodeVO[i].getEpisodeVisitType().equals(visitType) && _arrEpisodeVO[i].getIsConfirmed().equals(isConfirmed))
				{
					System.out.println("true[" + i + "]");
					_tmpArrEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++)
					{
						_tmpArrEpisodeVO[k] = arrVO[k];
						// System.out.println("arrVO"+arrVO[k].getEpisodeCode());
					}
					_tmpArrEpisodeVO[len - 1] = _arrEpisodeVO[i];
					arrVO = _tmpArrEpisodeVO;

					for (int k = 0; k < arrVO.length; k++)
					{
						System.out.println("i= " + i + " k= " + k + "  arrVO...1" + arrVO[k].getEpisodeCode());
					}
					// arrVO[len-1]=_arrEpisodeVO[i];
					/*
					 * _tmpArrEpisodeVO[j]=_arrEpisodeVO[i]; j++;
					 */
					// _tmpArrEpisodeVO=arrVO;
					/*len++;
				}
				for (int k = 0; k < arrVO.length; k++)
				{
					System.out.println("arrVO" + arrVO[k].getEpisodeCode());
				}
				System.out.println("j[" + i + "]" + j);
			}
			if (arrVO.length == 0) throw new HisRecordNotFoundException();
			System.out.println("_tmpArrEpisodeVO.length::" + _tmpArrEpisodeVO.length);
			/*
			 * int m,n; for( m=0, n=0; m<_tmpArrEpisodeVO.length; m++,n++){ _arrEpisodeVO[n]=_tmpArrEpisodeVO[m];
			 * System.out.println("_arrEpisodeVO.length::1"+_arrEpisodeVO.length); }
			 

			System.out.println("arrVO.length::1" + arrVO.length);
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside HisRecordNotFoundException of ");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		System.out.println("arrVO......::::" + arrVO.length);
		System.out.println("hi!!!!!!!!!!!!");
		return arrVO;
	}

	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and episode not closed.
	 * Provides last visit details of all episodes which are open presently.
	 * 
	 * @param _patientVO Provides CR No of the patient for which episode details are to be searched.
	 * @param _userVO Provides User details.
	 * @param visitType Specifies type of visit, namely, IPD, OPD, or EMG.
	 * @return Array of EpisodeVO with values stored in DB.
	 
	public EpisodeVO[] searchPatientEpiosdeByCrNo(PatientVO _patientVO, UserVO _userVO, String visitType)
	{
		System.out.println("inside searchPatientEpiosdeByCrNo() not checking isConfirmed");
		System.out.println("Patient first name::" + _patientVO.getPatFirstName());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO =
		{};
		EpisodeVO[] arrVO =
		{};
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			System.out.println("creating object of EpisodeDAO");
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			System.out.println("episodeDao.getLock()");
			String str = _patientVO.getPatCrNo();
			System.out.println("Patient first name::" + _patientVO.getPatFirstName());
			System.out.println("visitType in searchPatientEpiosdeByCrNo of PatientBO:" + visitType);

			/*
			 * boolean value=false; value=PatientBOSupport.checkPatientStatus(_patientVO, _userVO, visitType);
			 * if(value==true){
			 

			System.out.println("str::_patientVO.getPatCrNo():: " + str);
			_arrEpisodeVO = episodeDao.retrieveByCrNo(_patientVO.getPatCrNo(), _userVO);
			System.out.println("_arrEpisodeVO.length::" + _arrEpisodeVO.length);
			EpisodeVO[] _tmpArrEpisodeVO = new EpisodeVO[_arrEpisodeVO.length];
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]
			{};
			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
			
				if (_arrEpisodeVO[i].getEpisodeVisitType().equals(visitType))
				{
					System.out.println("true[" + i + "]");
					_tmpArrEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++)
					{
						_tmpArrEpisodeVO[k] = arrVO[k];
						// System.out.println("arrVO"+arrVO[k].getEpisodeCode());
					}
					_tmpArrEpisodeVO[len - 1] = _arrEpisodeVO[i];
					arrVO = _tmpArrEpisodeVO;

					for (int k = 0; k < arrVO.length; k++)
					{
						System.out.println("i= " + i + " k= " + k + "  arrVO...1" + arrVO[k].getEpisodeCode());
					}

					// arrVO[len-1]=_arrEpisodeVO[i];
					_tmpArrEpisodeVO[j] = _arrEpisodeVO[i];
					j++;
					// _tmpArrEpisodeVO=arrVO;
					len++;
				}
				for (int k = 0; k < arrVO.length; k++)
				{
					System.out.println("arrVO" + arrVO[k].getEpisodeCode());
				}
				System.out.println("j[" + i + "]" + j);
			}
			System.out.println("_tmpArrEpisodeVO.length::" + _tmpArrEpisodeVO.length);
			int m, n;
			for (m = 0, n = 0; m < _tmpArrEpisodeVO.length; m++, n++)
			{
				_arrEpisodeVO[n] = _tmpArrEpisodeVO[m];
				System.out.println("_arrEpisodeVO.length::1" + _arrEpisodeVO.length);
			}
			System.out.println("arrVO.length::1" + arrVO.length);
			/*
			 * if(visitType.equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG)) System.out.println("No open episodes in
			 * emergency"); else if(arrVO.length==0) throw new HisRecordNotFoundException();
			 

			if (arrVO.length == 0) throw new HisRecordNotFoundException();
			// }
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside HisRecordNotFoundException of searchPatientEpiosdeByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisNotAnOPDcaseException e)
		{
			System.out.println("inside HisNotAnOPDcaseException of searchPatientEpiosdeByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			System.out.println("inside HisNotAnIPDcaseException of searchPatientEpiosdeByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			System.out.println("inside HisDeadPatientException of searchPatientEpiosdeByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		System.out.println("arrVO......::::" + arrVO.length);
		System.out.println("hi!!!!!!!!!!!!");
		return arrVO;
	}

	/**
	 * Stamps the visit of a patient when he/she visits a department for the first time. Saves data in Daily Patient dtl, and
	 * Episode dtl tables.
	 * 
	 * @param _arrEpisodeVO[] Provides the departments in which patient has been registered.
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return Array of EpisodeVO with values stored in DB.
	 
	public EpisodeVO[] newDeptVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO)
	{
		System.out.println("inside newDeptVisitStamp() of PatientBO");
		System.out.println("Patient First Name::" + _patientVO.getPatFirstName());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			System.out.println("Patient First Name::" + _patientVO.getPatFirstName());
			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
				// _arrEpisodeVO[i].setPatSecondaryCatCode(_patientVO.getPatSecondaryCatCode());
				_arrEpisodeVO[i].setPatPrimaryCatCode(_patientVO.getPatPrimaryCatCode());
				_arrEpisodeVO[i].setPatIsOld(RegistrationConfig.IS_OLD_TRUE);

			}
			PatientBOSupport.newDeptVisitStamp(_arrEpisodeVO, _patientVO, _userVO, tx);
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			// System.out.println("_patientVO.getDepartment()::::"+_patientVO.getDepartment());
			System.out.println("_arrEpisodeVO.getDepartment()::::" + _arrEpisodeVO[0].getDepartment());

			tx.close();
		}
		return _arrEpisodeVO;
	}

	public EpisodeVO[] newDepartmentVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAOObj=null;
		try
		{
			tx.begin();
	
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			PatientDAO patientDao = new PatientDAO(tx);
			
			RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			RegChargeDtlVO[] regChgDtlVO = new RegChargeDtlVO[_arrEpisodeVO.length];
			DirectChageDetailVO[] directChargeVo = new DirectChageDetailVO[_arrEpisodeVO.length];
			RenewalDetailVO[] renewDetailVO = new RenewalDetailVO[_arrEpisodeVO.length];
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(tx);
			String amount = _arrEpisodeVO[0].getPatAmountCollected();
			
			hisDAOObj = new HisDAO("Registration","PatientBO");
			
			
				// EpisodeRefDtlVO episodeRefDtlVO=new EpisodeRefDtlVO();

				////updating patient detail if department wise mandatory fields are captured
				/*disable this for arogya online 
				 * if(_oldPatientVO!=null)
				{
				patDao.updateWithoutAge(_patientVO, _userVO);//not create procedure for arogya online
				patAuditDAO.create(_oldPatientVO, _userVO);//not create procedure for arogya online
				}disable this for arogya online
				
				for (int i = 0; i < _arrEpisodeVO.length; i++)
				{
					
					
					DailyPatientVO dailyPatVO = new DailyPatientVO();
					// Create DailyPatientVO from patientVO: Populate it
					HelperMethods.populate(dailyPatVO, _patientVO);
					// System.out.println("after population dailypatvo"+dailyPatVO.getPatCrNo());
					// System.out.println("before setDailyPatientDetails");
					// set Other required detials in episodeVO
					// explicityly set setIsValid(RegistrationConfig.IS_VALID_ACTIVE)
					dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
					
					// populate the episodeVO with the general details
					PatientBOHelper.setNewPatRegEpisodeVO(_arrEpisodeVO[i]);
					String referMlcNo="";
					referMlcNo=episodeRefVO.getMlcNo();
					if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD)) 
						{
						/*if(_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
						{
							if(episodeRefVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC))
							{
								_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
							}
							else
							{
								_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
							}
						}else
						{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
						//}
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
						}
					else
						{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
						}
					_arrEpisodeVO[i].setPatCrNo(dailyPatVO.getPatCrNo());
					_arrEpisodeVO[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
					_arrEpisodeVO[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
					// _arrEpisodeVO[i].setDepartmentCode("101");
					
					_arrEpisodeVO[i].setIsValid(Config.IS_VALID_ACTIVE);
					// populate this dailyPatient VO with _arrEpisodeVO[i]
					
					HelperMethods.populate(dailyPatVO, _arrEpisodeVO[i]);
					_patientVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
					HelperMethods.populate(dailyPatVO, _patientVO);
					dailyPatVO.setIsReferred(_patientVO.getIsReferred());
					// PatientBOHelper.setDailyPatientDetails(dailyPatVO);
					
					// Create a new entry in daily patient detail
					// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO
					
					//dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					///In case of new dept visit is old is false
					dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
					
					/* disable room usability  for arogya
					 *  if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
						}
						if(_patientVO.getIsPatReferByList()!=null && _patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
						}
					}
					else
						dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
					
					
					//disable room fileno  for arogya dailyPatVO.setIsNewFileRequired(Config.FILE_NO_GENERATION_FLAG);
					dailyPatVO.setEpisodeVisitNo("1");
					///old query 
					//dailyPatVO = dailyPatDao.create(dailyPatVO, _userVO);
					////new query for mlC refer logic
					
					if(dailyPatVO.getPatDocType()!=null&&dailyPatVO.getPatDocType().indexOf("|")>0)
					{
						dailyPatVO.setPatDocType(dailyPatVO.getPatDocType().split("\\|")[0]);
					}
					dailyPatVO = dailyPatDao.createNewDepartment(hisDAOObj, dailyPatVO, _userVO,referMlcNo);
					HelperMethods.populate(_arrEpisodeVO[i], dailyPatVO);
					if(dailyPatVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC))
						_arrEpisodeVO[i].setMlcNo(referMlcNo);
					//Create new entry in Episode detail
					

					// Create new entry in Episode detail
				//	_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);


					/*
					 * if(_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
					 * System.out.println("hi!!!!");
					 * _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					 * System.out.println("_arrEpisodeVO[i].getEpisodeReferAccept()"+_arrEpisodeVO[i].getEpisodeReferAccept());
					 * episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL); } else
					 * _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					 
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL);
						}

					}
					else
					{
						_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
						_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					}

					//_arrEpisodeVO[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
					_arrEpisodeVO[i].setIsCardPrint(dailyPatVO.getCardPrintSetting().substring(0,1));
					
					
					////setting visit number//
					
					_arrEpisodeVO[i].setEpisodeVisitNo("1");
					_arrEpisodeVO[i].setEntryDate(_patientVO.getSystemDate());
					//saving episode dtl
					//hide this for arogya online
					//disable  for arogya PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(_arrEpisodeVO[i], _userVO, tx);
					
					//changed on 14-feb-2012 by prakhar misra
					//to calculate expiry date out of procedure so that it cold be used for prinintg
					//String expiryDate=episodeDao.getExpiryDate(_arrEpisodeVO[i],_userVO);
					
					//_arrEpisodeVO[i].setExpiryDate(expiryDate);
					
					//To Update expiry date on patient dtl added by Singaravelan on 26-09-13 
					System.out.println("----Pat Ren Req--------"+_patientVO.getRegRenewalRequired()+"------------");
					if(_patientVO.getRegRenewalRequired().equalsIgnoreCase("1"))
					{
						_patientVO.setExpiryDate(patientDao.getExpiryDate(_userVO));
						System.out.println("----Date--------"+patientDao.getExpiryDate(_userVO)+"------------");
						patientDao.update(hisDAOObj,_patientVO,_userVO);
					}
					 
					
					 _arrEpisodeVO[i]=episodeDao.createNewRegistration(hisDAOObj,_arrEpisodeVO[i], _userVO);
					// episodeDao.create(_arrEpisodeVO[i], _userVO);

					//_arrEpisodeVO[i].setDepartmentCode(_arrEpisodeVO[i].getDepartmentUnitCode().substring(0, 3));
					/*String deptCode = _arrEpisodeVO[i].getDepartmentCode();
					String roomCode = _arrEpisodeVO[i].getRoomCode();
					String deptUnitCode = _arrEpisodeVO[i].getDepartmentUnitCode();
					List al = new ArrayList();
					al = essentialDAO.getNameValues(deptCode, roomCode, deptUnitCode, _userVO);
					_arrEpisodeVO[i].setDepartment(al.get(0).toString());
					_arrEpisodeVO[i].setRoom(al.get(1).toString());
					_arrEpisodeVO[i].setDepartmentUnit(al.get(2).toString());

					// HelperMethods.populate(_arrEpisodeVO[i], al);




					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						// _patientVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
						// System.out.println("_episodeReferVO.getIsRefferInOut(): "+_episodeReferVO.getIsRefferInOut());
						// HelperMethods.populate(_episodeReferVO, _patientVO);
						// HelperMethods.populate(_episodeReferVO, _arrEpisodeVO[i]);
						// ///////episode referDTL////////////////
						/*
						 * String[] array = episodeDao.getEpisdoeCode(_arrEpisodeVO[i].getPatCrNo(), _arrEpisodeVO[i]
						 * .getDepartmentUnitCode(), _userVO); _arrEpisodeVO[i].setEpisodeVisitNo(array[2]);
						 
						String refEpisode = episodeRefVO.getEpisodeCode();
						String refEpisodeVisit = episodeRefVO.getEpisodeVisitNo();
						String serialNo = episodeRefVO.getSerialNo();
						HelperMethods.populate(episodeRefVO, _arrEpisodeVO[i]);
						episodeRefVO.setSystemIPAddress(_patientVO.getSystemIPAddress());

						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							episodeRefVO.setEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
							episodeRefVO.setEpisodeVisitNo("1");
							episodeRefVO.setToDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
							episodeRefVO.setToDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());

						}
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							episodeRefVO.setToDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
							episodeRefVO.setToDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
							episodeRefVO.setToEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
							episodeRefVO.setToEpisodeVisitNo("1");
							episodeRefVO.setEpisodeCode(refEpisode);
							episodeRefVO.setEpisodeVisitNo(refEpisodeVisit);
							episodeRefVO.setExternalHospitalCode("");
							episodeRefVO.setSerialNo(serialNo);
						}

						if (_patientVO.getIsPatReferByList() != null
								&& _patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							episodeRefDtlDAO.updateAcceptanceDate(hisDAOObj, episodeRefVO, _userVO);
						}
						else
						{

							episodeRefDtlDAO.create(hisDAOObj,episodeRefVO, _userVO);
						}
						// ///////episode referDTL////////////////
						// System.out.println("_episodeReferVO.getEpisodeVisitNo():::"+_episodeReferVO.getEpisodeVisitNo());
						// episodeReferDAO.create(_episodeReferVO, _userVO);
					}
					//Modified to add the details on renewal table while new department visit cum renewal by Singaravelan on 08-10-13 
					System.out.println("----Amount-----"+amount+"-----------");
					if (amount != null && !amount.equals("") && !amount.equals("0"))
					{
						regChgDtlVO[i] = new RegChargeDtlVO();
						regChgDtlVO[i].setTariffId(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
						regChgDtlVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
						regChgDtlVO[i].setModuleId(Config.MODULE_ID_REGISTRATION);
						HelperMethods.populate(regChgDtlVO[i], _arrEpisodeVO[i]);
						regChgDtlVO[i].setPatAmountCollected(amount);
						regChgDtlVO[i].setSystemIPAddress(_patientVO.getSystemIPAddress());
						
						////registration module billing
						regChgDtlDAO.create(hisDAOObj,regChgDtlVO[i], _userVO);
						directChargeVo[i] = new DirectChageDetailVO();
						HelperMethods.populate(directChargeVo[i], regChgDtlVO[i]);
						directChargeDao.create(hisDAOObj,directChargeVo[i], _userVO);
						///Billing module integration
						//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], _userVO);
						// createSlip(_patientVO.getSystemIPAddress(),printData);
						
						if(_arrEpisodeVO[i].getRenewalRequired().equals("1")==true||_patientVO.getRegRenewalRequired().equalsIgnoreCase("1"))
						{
							renewDetailVO[i] = new RenewalDetailVO();
							renewDetailVO[i].setPatCrNo(_arrEpisodeVO[i].getPatCrNo());
							renewDetailVO[i].setSeatId(_userVO.getSeatId());
							renewDetailVO[i].setIsValid(Config.IS_VALID_ACTIVE);
							renewDetailVO[i].setSystemIPAddress(_userVO.getIpAddress());
							renewDetailVO[i].setEntryDate(_arrEpisodeVO[i].getEntryDate());
							renewDetailVO[i].setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
							renewDetailVO[i].setDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
							renewDetailVO[i].setHospitalCode(_userVO.getHospitalCode());
							renewDetailVO[i].setOldExpiryDate(_arrEpisodeVO[i].getOldExpiryDate());
							renewDetailVO[i].setNewExpiryDate(_arrEpisodeVO[i].getExpiryDate());
							renewDetailVO[i].setRenewalType(Config.RENEWAL_TYPE);

							renewDetailDAO.createForOldPatientRenewal(hisDAOObj,renewDetailVO[i], _userVO);
						
						}					
					
					}
				/////Query to fetch Unit working days for printing////
					
				//	_arrEpisodeVO[i].setUnitWorkingDays(essentialDAO.getUnitWorkingDays(_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
				
				/////////////////////////////////////////////////////
			
					
					//****** query to fetch disclaimer for printing ************//*
						_arrEpisodeVO[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL, _arrEpisodeVO[i].getDepartmentUnitCode(), _userVO));
						
					//**************************************************//*	
				
				
			}
			synchronized (hisDAOObj) 
			{
			   hisDAOObj.fire();
			}		
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			// System.out.println("_patientVO.getDepartment()::::"+_patientVO.getDepartment());
			System.out.println("_arrEpisodeVO.getDepartment()::::" + _arrEpisodeVO[0].getDepartment());
			if (hisDAOObj != null) {
				hisDAOObj.free();
				hisDAOObj = null;
			}
			tx.close();
		}
		return _arrEpisodeVO;
	}

	
	public EpisodeVO[] newDepartmentVisitStampRoomWise(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAOObj=null;
		try
		{
			tx.begin();
			PatientDAO patDao = new PatientDAO(tx);
			PatientAuditDAO patAuditDAO=new PatientAuditDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			RegChargeDtlVO[] regChgDtlVO = new RegChargeDtlVO[_arrEpisodeVO.length];
			DirectChageDetailVO[] directChargeVo = new DirectChageDetailVO[_arrEpisodeVO.length];
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			String amount = _patientVO.getPatAmountCollected();
			hisDAOObj = new HisDAO("Registration","PatientBO");
			
			synchronized (patDao.getLock())
			{
			////updating patient detail if department wise mandatory fields are captured
				if(_oldPatientVO!=null)
				{
				patDao.updateWithoutAge(hisDAOObj,_patientVO, _userVO);
				patAuditDAO.create(hisDAOObj,_oldPatientVO, _userVO);
				}
				for (int i = 0; i < _arrEpisodeVO.length; i++)
				{
					DailyPatientVO dailyPatVO = new DailyPatientVO();
					// Create DailyPatientVO from patientVO: Populate it
					HelperMethods.populate(dailyPatVO, _patientVO);
					dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
					// populate the episodeVO with the general details
					PatientBOHelper.setNewPatRegEpisodeVO(_arrEpisodeVO[i]);
					String referMlcNo="";
					referMlcNo=episodeRefVO.getMlcNo();
					if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD)) 
						{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
						}
					else
						{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
						}
					_arrEpisodeVO[i].setPatCrNo(dailyPatVO.getPatCrNo());
					_arrEpisodeVO[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
					_arrEpisodeVO[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
					// _arrEpisodeVO[i].setDepartmentCode("101");
					
					_arrEpisodeVO[i].setIsValid(Config.IS_VALID_ACTIVE);
					// populate this dailyPatient VO with _arrEpisodeVO[i]
					
					HelperMethods.populate(dailyPatVO, _arrEpisodeVO[i]);
					_patientVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
					HelperMethods.populate(dailyPatVO, _patientVO);
					dailyPatVO.setIsReferred(_patientVO.getIsReferred());
					// PatientBOHelper.setDailyPatientDetails(dailyPatVO);
					
					// Create a new entry in daily patient detail
					// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO
					
					//dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					///In case of new dept visit is old is false
					dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
						}
						if(_patientVO.getIsPatReferByList()!=null && _patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
						}
					}
					else
						dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
					
					
					dailyPatVO.setIsNewFileRequired(Config.FILE_NO_GENERATION_FLAG);
					dailyPatVO.setEpisodeVisitNo("1");
					///old query 
					//dailyPatVO = dailyPatDao.create(dailyPatVO, _userVO);
					////new query for mlC refer logic
					dailyPatVO = dailyPatDao.createNewDepartmentRoomWise(dailyPatVO, _userVO,referMlcNo);
					HelperMethods.populate(_arrEpisodeVO[i], dailyPatVO);
					if(dailyPatVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC))
						_arrEpisodeVO[i].setMlcNo(referMlcNo);
					//Create new entry in Episode detail
					
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL);
						}

					}
					else
					{
						_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
						_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					}

					_arrEpisodeVO[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
					
					////setting visit number//
					
					_arrEpisodeVO[i].setEpisodeVisitNo("1");
					_arrEpisodeVO[i].setEntryDate(_patientVO.getSystemDate());
					//saving episode dtl
					
					//changed on 14-feb-2013 by Adil Wasi at 18-feb-2013 for Registration
					
					String expiryDate=episodeDao.getExpiryDate(_arrEpisodeVO[i],_userVO);
					
					_arrEpisodeVO[i].setExpiryDate(expiryDate);
					episodeDao.create(hisDAOObj, _arrEpisodeVO[i], _userVO);
					//PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(hisDAOObj,_arrEpisodeVO[i], _userVO, tx);
					

					_arrEpisodeVO[i].setDepartmentCode(_arrEpisodeVO[i].getDepartmentUnitCode().substring(0, 3));
					




					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						String refEpisode = episodeRefVO.getEpisodeCode();
						String refEpisodeVisit = episodeRefVO.getEpisodeVisitNo();
						String serialNo = episodeRefVO.getSerialNo();
						HelperMethods.populate(episodeRefVO, _arrEpisodeVO[i]);
						episodeRefVO.setSystemIPAddress(_patientVO.getSystemIPAddress());

						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							episodeRefVO.setEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
							episodeRefVO.setEpisodeVisitNo("1");

						}
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							episodeRefVO.setToDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
							episodeRefVO.setToDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
							episodeRefVO.setToEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
							episodeRefVO.setToEpisodeVisitNo("1");
							episodeRefVO.setEpisodeCode(refEpisode);
							episodeRefVO.setEpisodeVisitNo(refEpisodeVisit);
							episodeRefVO.setExternalHospitalCode("");
							episodeRefVO.setSerialNo(serialNo);
						}

						if (_patientVO.getIsPatReferByList() != null
								&& _patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							episodeRefDtlDAO.updateAcceptanceDate(episodeRefVO, _userVO);
						}
						else
						{

							episodeRefDtlDAO.create(episodeRefVO, _userVO);
						}
						// ///////episode referDTL////////////////
						// System.out.println("_episodeReferVO.getEpisodeVisitNo():::"+_episodeReferVO.getEpisodeVisitNo());
						// episodeReferDAO.create(_episodeReferVO, _userVO);
					}
					if (amount != null && !amount.equals("") && !amount.equals("0"))
					{
						regChgDtlVO[i] = new RegChargeDtlVO();
						regChgDtlVO[i].setTariffId(_userVO.getTariffID());
						regChgDtlVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
						regChgDtlVO[i].setModuleId(_userVO.getModuleId());
						HelperMethods.populate(regChgDtlVO[i], _arrEpisodeVO[i]);
						regChgDtlVO[i].setPatAmountCollected(amount);
						regChgDtlVO[i].setSystemIPAddress(_patientVO.getSystemIPAddress());
						
						////registration module billing
						regChgDtlDAO.create(hisDAOObj,regChgDtlVO[i], _userVO);
						directChargeVo[i] = new DirectChageDetailVO();
						HelperMethods.populate(directChargeVo[i], regChgDtlVO[i]);
						directChargeDao.create(hisDAOObj,directChargeVo[i], _userVO);
						///Billing module integration
						//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], _userVO);
						// createSlip(_patientVO.getSystemIPAddress(),printData);
					}
					
					/////Query to fetch Unit working days for printing////
						
					//	_arrEpisodeVO[i].setUnitWorkingDays(essentialDAO.getUnitWorkingDays(_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
					
					/////////////////////////////////////////////////////
					if (Config.CLIENT.equals(Config.CLIENT_GNCTD)) {
						
						
							_arrEpisodeVO[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL, _arrEpisodeVO[i].getDepartmentUnitCode(), _userVO));
							
						
					}
					/***********************************************/	
				/*	synchronized (hisDAOObj) {
						hisDAOObj.fire();
					}
				}
			}
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			// System.out.println("_patientVO.getDepartment()::::"+_patientVO.getDepartment());
			System.out.println("_arrEpisodeVO.getDepartment()::::" + _arrEpisodeVO[0].getDepartment());
			if (hisDAOObj != null) {
				//daoObj.free();
				hisDAOObj = null;
			}
			tx.close();
		}
		return _arrEpisodeVO;
	}
	
	/**
	 * Stamps the visit of a patient when he/she re-visits a department. Saves data in Daily Patient dtl, and Episode dtl
	 * tables.
	 * 
	 * @param _arrEpisodeVO[] Provides the departments in which patient has been registered.
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return Array of EpisodeVO with values stored in DB.
	 
	public EpisodeVO[] oldDeptVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO, EpisodeRefDtlVO episodeRefDtlVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAOObj=null;
		try
		{
			
			PatientDAO patientDao = new PatientDAO(tx);
			tx.begin();
			hisDAOObj = new HisDAO(null, null);
			if(_arrEpisodeVO!=null && _arrEpisodeVO.length>0)
			{
//			for(EpisodeVO episodeVO:_arrEpisodeVO)
//			{
				//Modified by to update expiry date on patient detail by singaravelan on 26-09-13
				//if(episodeVO.getRenewalRequired().equals("1")==true||_patientVO.getRegRenewalRequired().equalsIgnoreCase("1"))//To change reminder
				if(_patientVO.getRegRenewalRequired().equalsIgnoreCase("1"))
				{
					//episodeVO = saveDeptWiseRenewalDetailAndUpdateEpisodeVo(hisDAOObj,episodeVO, _userVO, tx);
					_patientVO.setExpiryDate(patientDao.getExpiryDate(_userVO));
					System.out.println("----Date--------"+patientDao.getExpiryDate(_userVO)+"------------");
					patientDao.update(hisDAOObj,_patientVO,_userVO);
				}
//			}
			}
			
			PatientBOSupport.oldDeptVisitStamp(hisDAOObj,_arrEpisodeVO, _patientVO, _userVO, tx, episodeRefDtlVO);
			
			//Modified for RENEWAL=2 : HOSPITAL RENEWAL : DAYS BASIS by Singaravelan on 04-10-13
			if((Config.RENEWAL_TYPE).equalsIgnoreCase("2"))
			{
				if(_arrEpisodeVO[0].getRenewalRequired().equals("1")==true||_patientVO.getRegRenewalRequired().equalsIgnoreCase("1"))
				{
					_arrEpisodeVO[0].setIsEpisodeRenewed("true");
					_arrEpisodeVO[0].setPatPrimaryCatCode(_patientVO.getPatPrimaryCatCode());
					EpisodeVO episodeVO=saveRenewalAndRegChagrges(hisDAOObj,_arrEpisodeVO[0],_userVO,tx);
				}
							
			}
			else
			{
				for(EpisodeVO episodeVO:_arrEpisodeVO)
				{
					if(episodeVO.getRenewalRequired().equals("1")==true||_patientVO.getRegRenewalRequired().equalsIgnoreCase("1"))
					{
						episodeVO.setIsEpisodeRenewed("true");
						episodeVO.setPatPrimaryCatCode(_patientVO.getPatPrimaryCatCode());
						episodeVO=saveRenewalAndRegChagrges(hisDAOObj,episodeVO,_userVO,tx);
					}
				}
			}
			
			for(int i=0;i<_arrEpisodeVO.length;i++)
			{
			/*** Query to fetch Disclaimer*********************/
			/*String patRegCatCode=_patientVO.getPatRegCatCode();
			String usablityFlag="";
			if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
				usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
			if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL)){
				usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
			}	
			_arrEpisodeVO[i].setDisclaimer(getDisclamer(usablityFlag,_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
			}
			
			synchronized (hisDAOObj) {
				 hisDAOObj.fire();
			}			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch(HisAppointmentNotAvailableException e){
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());	
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			if (hisDAOObj != null) {
				//hisDAOObj.free();
				hisDAOObj = null;
			}
			tx.close();
		}
		return _arrEpisodeVO;
	}
	
//	public EpisodeVO[] oldDeptVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO, EpisodeRefDtlVO episodeRefDtlVO)
//	{
//		JDBCTransactionContext tx = new JDBCTransactionContext();
//		HisDAO hisDAOObj=null;
//		try
//		{
//			
//			PatientDAO patientDao = new PatientDAO(tx);
//			tx.begin();
//			hisDAOObj = new HisDAO(null, null);
//			if(_arrEpisodeVO!=null && _arrEpisodeVO.length>0)
//			{
//			for(EpisodeVO episodeVO:_arrEpisodeVO)
//			{
//				//Modified by to update expiry date on patient detail by singaravelan on 26-09-13
//				if(episodeVO.getRenewalRequired().equals("1")==true||_patientVO.getRegRenewalRequired().equalsIgnoreCase("1"))//To change reminder
//				{
//					episodeVO = saveDeptWiseRenewalDetailAndUpdateEpisodeVo(hisDAOObj,episodeVO, _userVO, tx);
//					_patientVO.setExpiryDate(patientDao.getExpiryDate(_userVO));
//					System.out.println("----Date--------"+patientDao.getExpiryDate(_userVO)+"------------");
//					patientDao.update(hisDAOObj,_patientVO,_userVO);
//				}
//			}
//			
//			PatientBOSupport.oldDeptVisitStamp(hisDAOObj,_arrEpisodeVO, _patientVO, _userVO, tx, episodeRefDtlVO);
//			
//			
//			for(EpisodeVO episodeVO:_arrEpisodeVO)
//			{
//				if(episodeVO.getRenewalRequired().equals("1")==true||_patientVO.getRegRenewalRequired().equalsIgnoreCase("1"))
//				{
//					episodeVO.setIsEpisodeRenewed("true");
//					episodeVO.setPatPrimaryCatCode(_patientVO.getPatPrimaryCatCode());
//					episodeVO=saveRenewalAndRegChagrges(hisDAOObj,episodeVO,_userVO,tx);
//					
//				}
//				
//				
//			}
//			
//			for(int i=0;i<_arrEpisodeVO.length;i++)
//			{
//			/*** Query to fetch Disclaimer*********************/
//			String patRegCatCode=_patientVO.getPatRegCatCode();
//			String usablityFlag="";
//			if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
//				usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
//			if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL)){
//				usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
//			}	
//			_arrEpisodeVO[i].setDisclaimer(getDisclamer(usablityFlag,_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
//			}
//			
//			synchronized (hisDAOObj) {
//				 hisDAOObj.fire();
//			}
//			
//			}
//		}
//		catch (HisInvalidTokenNumberException e)
//		{
//			tx.rollback();
//			System.out.println(e.getMessage());
//			throw new HisInvalidTokenNumberException(e.getMessage());
//		}
//		catch(HisAppointmentNotAvailableException e){
//			tx.rollback();
//			System.out.println(e.getMessage());
//			throw new HisInvalidTokenNumberException(e.getMessage());	
//		}
//		catch (HisApplicationExecutionException e)
//		{
//			tx.rollback();
//			System.out.println(e.getMessage());
//			throw new HisApplicationExecutionException(e.getMessage());
//		}
//		catch (HisDataAccessException e)
//		{
//			System.out.println(e.getMessage());
//			tx.rollback();
//			throw new HisDataAccessException(e.getMessage());
//		}
//		catch (Exception e)
//		{
//			System.out.println(e.getMessage());
//			tx.rollback();
//			throw new HisApplicationExecutionException(e.getMessage());
//		}
//		finally
//		{
//			System.out.println("Close the transaction...");
//			if (hisDAOObj != null) {
//				//hisDAOObj.free();
//				hisDAOObj = null;
//			}
//			tx.close();
//		}
//		return _arrEpisodeVO;
//	}

	/**
	 * Changes primary and secondary categories of a patient. Updates the record in Patient dtl.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return <code>true</code> if records are updated successfully otherwise <code>false</code>.
	 * @deprecated Replaced by PatientBO.changePatientCategory().
	 */
	/*public boolean patCategoryChange(PatientVO _patientVO, EpisodeVO[] _arrEpisodeVO, ChangeCategoryVO[] _arrChangeCategoryVO, UserVO _userVO)
	{
		System.out.println("inside patCategoryChange() of PatientBO");
		System.out.println("Patient First Name::" + _patientVO.getPatFirstName());
		/*
		 * for(int i=0;i<_arrEpisodeVO.length;i++) System.out.println("Patient Primary
		 * Category::"+_arrEpisodeVO[i].getPatPrimaryCatCode());
		 
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int x = 0, y = 0, z = 0;

		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			System.out.println("_arrEpisodeVO.length::...");
			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				System.out.println("Patient Primary Category::" + i + "..." + _arrEpisodeVO[i].getPatPrimaryCatCode());
				System.out.println("Patient Secondary Category::" + i + "..." + _arrEpisodeVO[i].getPatSecondaryCatCode());
			}
			System.out.println("_arrChangeCategoryVO.length ..:: " + _arrChangeCategoryVO.length);
			for (int i = 0; i < _arrChangeCategoryVO.length; i++)
			{
				System.out.println("Patient CrNo for _arrChangeCategoryVO::" + i + "..." + _arrChangeCategoryVO[i].getPatCrNo());
				System.out.println("Patient New Primary Category for _arrChangeCategoryVO::" + i + "..."
						+ _arrChangeCategoryVO[i].getPatNewPrimaryCat());
				System.out.println("Patient New Secondary Category for _arrChangeCategoryVO::" + i + "..."
						+ _arrChangeCategoryVO[i].getPatNewSecondaryCatCode());
				System.out.println("Patient Prev Primary Category for _arrChangeCategoryVO::" + i + "..."
						+ _arrChangeCategoryVO[i].getPatPrevPrimaryCat());
				System.out.println("Patient Prev Secondary Category for _arrChangeCategoryVO::" + i + "..."
						+ _arrChangeCategoryVO[i].getPatPrevSecondaryCatCode());
			}
			PatientDAO patientDao = new PatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			CategoryChangeDAO catChangeDAO = new CategoryChangeDAO(tx);
			// DailyPatientDAO dailyPatDao=new DailyPatientDAO(tx);

			System.out.println("Patient First Name::" + _patientVO.getPatFirstName());
			System.out.println("inside patCategoryChange() after dailyPatDao.getLock()");
			// x=patientDao.changePatientCat(_patientVO,_userVO);
			x = 1;
			for (int i = 0, j = 0; i < _arrEpisodeVO.length; i++, j++)
			{
				if (x >= 1)
				{
					y = 0;
					// _arrEpisodeVO[i].setPatPrimaryCatCode(_patientVO.getPatPrimaryCatCode());
					// _arrEpisodeVO[i].setPatSecondaryCatCode(_patientVO.getPatSecondaryCatCode());
					y = episodeDao.updateSecondaryCategoryEpisodeDtl(_arrEpisodeVO[i], _userVO);

				}
				if (y >= 1)
				{
					_arrChangeCategoryVO[j].setIsValid(Config.IS_VALID_ACTIVE);
					_arrChangeCategoryVO[j].setSeatId(_userVO.getSeatId());
					catChangeDAO.create(_arrChangeCategoryVO[j], _userVO);
					z++;
				}
			}
			// System.out.println("after changing PatPrimaryCatCode::"+_patientVO.getPatPrimaryCatCode());
			// System.out.println("after changing PatSecondaryCatCode::"+_patientVO.getPatSecondaryCatCode());
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			System.out.println("z = " + z);
			tx.close();
		}
		if (z >= 1) return true;
		else return false;
	}

	/**
	 * Checks whether patient record is modifiable at registration desk or not.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return <code>true</code> if time-duration less than 10 min otherwise <code>false</code>.
	 
	public boolean checkModify(PatientVO _patientVO, UserVO _userVO)
	{
		//System.out.println("inside checkModify() of PatientBO");
		//String str = _patientVO.getPatCrNo();
		//System.out.println("_patientVO.getPatCrNo()::str:: " + str);
		JDBCTransactionContext tx = new JDBCTransactionContext();

		float timestamp = 0;
		try
		{
			//System.out.println("Begining transaction");
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			timestamp = patientDao.checkTimeStamp(_patientVO, _userVO);
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
		////Earlier the time was hardcoded as 10 , now it is fetched from UserMgmtConfig.xml
		UserMgmtConfigXmlHandler configHandler=new UserMgmtConfigXmlHandler();
		int modificationTime=configHandler.getRegistrationModificationTime();
		if (timestamp < modificationTime) return true;
		else return false;
	}

	/**
	 * Modifies patient details at Registration Desk. Modification at registration can be done with in 10 minutes of
	 * registration. Updates the record in Patient dtl and Address dtl tables. Also updates patient's demographic details in
	 * Daily Patient Detail table.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return <code>true</code> if records are updated successfully otherwise <code>false</code>.
	 */
/*	public boolean patDtlModificationREG(PatientVO _patientVO,PatientVO _patientVOOldData, UserVO _userVO)
	{
		int x = 0, y = 0, z = 0, m = 0, n = 0;
		String str = _patientVO.getPatCrNo();
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			PatientAuditDAO patAuditDao=new PatientAuditDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			EpisodeRefDtlDAO episodeReferDAO = new EpisodeRefDtlDAO(tx);
			
			DailyPatientVO _dailyPatientVO = new DailyPatientVO();
			EpisodeVO _episodeVO = new EpisodeVO();
			EpisodeRefDtlVO _episodeReferVO = new EpisodeRefDtlVO();
			if (_patientVO.getMlcNo() == null || _patientVO.getMlcNo().equals(""))
			{
				_patientVO.setMlcNo(RegistrationConfig.IS_MLC_FALSE);
			}
			
			x = patientDao.update(_patientVO, _userVO);
			HelperMethods.populate(_dailyPatientVO, _patientVO);

			if (x >= 1)
			{
			
				//y = addDao.update(_patientVO.getPatAddress(), _userVO);
				//addressVO.setIsValid(Config.IS_VALID_ACTIVE);
				// addDao.update(_arrAddressVO, _userVO);
				///adit of address change
				y = addDao.updatePreviousRow(_patientVO.getPatAddress(), _userVO);
				addDao.insertNewRowAddress(_patientVO.getPatAddress(), _userVO);

				//////////////////////////////////////////////
				
			}
			if (y >= 1)
			{
				
				z = dailyPatDao.update(_dailyPatientVO, _userVO);
				
				
			}
			if (z >= 1)
			{
				_episodeVO.setPatCrNo(_patientVO.getPatCrNo());
				_episodeVO.setEntryDate(_patientVO.getEntryDate());
				_episodeVO.setPatSecondaryCatCode(_patientVO.getPatSecondaryCatCode());
				_episodeVO.setPatPrimaryCatCode(_patientVO.getPatPrimaryCatCode());
				if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					HelperMethods.populate(_episodeReferVO, _patientVO);
					_episodeVO.setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					// _episodeVO.setEpisodeReferAccept(RegistrationConfig.SYSDATE);
					_episodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					m = episodeDAO.updateEpisodeDtlAtREG(_episodeVO, _userVO);
					// _patientVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
				

					if (m >= 1)

					_episodeReferVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
					_episodeReferVO.setExternalHospitalCode(_patientVO.getPatRefGnctdHospitalCode());
					_episodeReferVO.setExternalHospitalDepartment(_patientVO.getPatRefGnctdHospitalDept());
					_episodeReferVO.setExternalHospitalDepartmentUnit(_patientVO.getPatRefGnctdHospitalDeptUnit());
					_episodeReferVO.setExternalHospitalDoctorName(_patientVO.getPatRefDoctor());
					_episodeReferVO.setExternalHospitalName(_patientVO.getPatRefHospitalName());
					_episodeReferVO.setExternalHospitalPatCrNo(_patientVO.getPatRefGnctdHospitalCrno());
					_episodeReferVO.setToDepartmentCode(_patientVO.getDepartmentCode());
					_episodeReferVO.setToDepartmentUnitCode(_patientVO.getDepartmentUnitCode());
					if (_patientVO.getPreviouslyReffered().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						n = episodeReferDAO.updateRefEpisodeDtlAtREG(_episodeReferVO, _userVO);
					}
					else
					{
						episodeReferDAO.create(_episodeReferVO, _userVO);
						n = 1;
					}
				}
				else
				{
					_episodeVO.setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					m = episodeDAO.updateEpisodeDtlAtREG(_episodeVO, _userVO);
				}

			}
			///////Audit Of Patient Detail
			
			patAuditDao.create(_patientVOOldData, _userVO);
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		System.out.println("n=    " + n);
		if (n >= 1 || m >= 1) return true;
		else return false;
	}*/

	/**
	 * Modifies patient details at MRD Desk. Updates the record Patient dtl and Address dtl tables.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return number of records updated.
	 */
	/*
	 * public boolean patDtlModificationMRD(PatientVO _patientVO, UserVO _userVO) {
	 * 
	 * int x = 0;
	 * 
	 * String str = _patientVO.getPatCrNo();
	 * 
	 * JDBCTransactionContext tx = new JDBCTransactionContext();
	 * 
	 * try {
	 * 
	 * tx.begin(); PatientDAO patientDao = new PatientDAO(tx); AddressDAO addDao = new AddressDAO(tx); PatientImageDtlDAO
	 * patientImageDtlDAO = new PatientImageDtlDAO(tx); PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
	 * //System.out.println("_addressVO.getPatCrNo():: "+_addressVO.getPatCrNo());
	 * 
	 * if(_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
	 * _patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
	 * 
	 * if (_patientVO.getPatAddress().getIsAddressDelhi().equals(RegistrationConfig.IS_ADDRESS_DELHI_FALSE)) _patientVO
	 * .getPatAddress().setPatAddCityLocCode("");
	 * 
	 * patientDao.updatePatientEmployeeDetails(_patientVO, _userVO); //for test purpose -- procedure test for modification
	 * 
	 * 
	 * fffffffff x = addDao.update(_patientVO.getPatAddress(), _userVO);
	 * 
	 * if (_patientVO.getImageFile() != null) { HelperMethods.populate(patientImageDtlVO, _patientVO);
	 * patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#1");
	 * HelperMethods.storeImageInFileSystem(_patientVO.getImageFile(), _patientVO.getImageFileName(),
	 * patientImageDtlVO.getFileNo(), RegistrationConfig.PATIENT_IMAGE_FILE_STORAGE_PATH);
	 * patientImageDtlVO.setFilePath(RegistrationConfig.PATIENT_IMAGE_FILE_STORAGE_PATH);
	 * patientImageDtlVO.setIsValid(RegistrationConfig.IS_VALID_ACTIVE);
	 * patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_FALSE); //
	 * patientImageDtlDAO.create(patientImageDtlVO, _userVO); } } catch (HisUpdateUnsuccesfullException e) {
	 * System.out.println(e.getMessage()); tx.rollback(); throw new HisUpdateUnsuccesfullException(); } catch
	 * (HisApplicationExecutionException e) { System.out.println(e.getMessage()); tx.rollback(); throw new
	 * HisApplicationExecutionException(e.getMessage()); } catch (HisDataAccessException e) { System.out.println(e.getMessage());
	 * tx.rollback(); throw new HisDataAccessException(e.getMessage()); } catch (Exception e) { System.out.println(e.getMessage());
	 * tx.rollback(); throw new HisException(e.getMessage()); } finally { tx.close(); }
	 * 
	 * if (x >= 1) return true; else return false; }
	 */

	/**
	 * Retrieves all the addresses of a patient from Address Dtl Table.
	 * 
	 * @param _patientVO Provides CR No of the patient for which episode details are to be searched.
	 * @param _userVO Provides User details.
	 * @return Array of AddressVO with all the addresses of the patient.
	 */
	/*public AddressVO[] getPatAddressAll(PatientVO _patientVO, UserVO _userVO)
	{
		//System.out.println("inside getPatAddressAll() of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		AddressVO[] _arrAddressVO = {};
		try
		{
			tx.begin();
			AddressDAO addDao = new AddressDAO(tx);
			String crNo = _patientVO.getPatCrNo();
			_arrAddressVO = addDao.retrieveByCrNoAll(crNo, _userVO);
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		//for (int i = 0; i < _arrAddressVO.length; i++)
		//	System.out.println("_arrAddressVO[" + i + " ].getSerialNo()" + _arrAddressVO[i].getSerialNo());
		//System.out.println("_arrAddressVO.length::1.." + _arrAddressVO.length);
		return _arrAddressVO;
	}

	/**
	 * Checks whether an address entry has to be made in the DB or an existing address is to be modified.
	 * 
	 * @param _arrAddressVO[] Provides all the addresses to be added or modified.
	 * @param _patientVO Provides Patient details.
	 * @param addVO Stores address which is to be modified.
	 * @param _userVO Provides User details.
	 * @return AddressVO with address to be modified.
	 * @deprecated No replacement.
	 
	public AddressVO checkAddModify(AddressVO[] _arrAddressVO, PatientVO _patientVO, AddressVO addVO, UserVO _userVO, String add_modify)
	{
		System.out.println("inside checkAddModify() of PatientBO");
		System.out.println("_arrAddressVO.length::2.." + _arrAddressVO.length);
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// AddressVO addVO=new AddressVO();
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			// PatientDAO patientDao = new PatientDAO(tx);
			// System.out.println("creating object of PatientDAO");
			AddressDAO addDao = new AddressDAO(tx);
			System.out.println("creating object of AddressDAO");

			System.out.println("add_modify::" + add_modify);
			if (add_modify.equals(RegistrationConfig.PATIENT_ADDRESS_MODIFY))
			{
				// AddressVO _addressVO = new AddressVO();
				System.out.println("true");
				System.out.println("_arrAddressVO.length::" + _arrAddressVO.length);
				for (int i = 0; i < _arrAddressVO.length; i++)
				{
					if (_patientVO.getPatAddTypeCode().equals(_arrAddressVO[i].getPatAddTypeCode()))
					{
						// populate PatientVO with Address VO
						HelperMethods.populate(addVO, _arrAddressVO[i]);
						System.out.println("addVO.getPatAddDistrict()..::.." + addVO.getPatAddDistrict());
						System.out.println("after populating patientVO");
					}
				}
			}
			// System.out.println("Patient first name::"+_patientVO.getPatFirstName());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		System.out.println("addVO.getPatAddDistrict()::" + addVO.getPatAddDistrict());
		return addVO;
	}

	/**
	 * Modifies patient address details at MRD Desk. Also more addresses can be added. Updates the record in Address dtl
	 * table.
	 * 
	 * @param _arrAddressVO[] Provides addresses to be added or modified.
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return Number of records updated.
	 */
	/*public int patAddressDtlModification(AddressVO _arrAddressVO, VerificationDocumentVO _verificationDocumentVO, UserVO _userVO, String addTypeCode,
			String addModify)
	{
		int x = 0;
		System.out.println("inside patAddressDtlModification() of PatientBO");
		// System.out.println("_arrAddressVO.length:: " + _arrAddressVO.length);
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String serialNo = null;
		String err = "";
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			// PatientDAO patientDao = new PatientDAO(tx);
			// System.out.println("creating object of PatientDAO");
			String crNo = null;

			AddressDAO addDao = new AddressDAO(tx);
			PatientDAO patDao = new PatientDAO(tx);
			AddressVO[] allAddressVO =
			{};
			VerificationDocumentUsedDAO verificationDocUsedDao = new VerificationDocumentUsedDAO(tx);
			serialNo = verificationDocUsedDao.selectNextSerialNoForPUK(_verificationDocumentVO, _userVO);
			
			 * if (_arrAddressVO.length > 0) crNo = _arrAddressVO[0].getPatCrNo(); //System.out.println("creating object of
			 * AddressDAO"); allAddressVO = addDao.retrieveByCrNoAll(crNo, _userVO);
			 * System.out.println("allAddressVO.length::: " + allAddressVO.length); for (int i = 0; i < _arrAddressVO.length;
			 * i++) { _arrAddressVO[i].setIsValid(RegistrationConfig.IS_VALID_ACTIVE); System.out.println("_arrAddressVO[" +
			 * i + "].getSerialNo()::.. " + _arrAddressVO[i].getSerialNo());
			 
			if (_arrAddressVO.getPatAddTypeCode().equals(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT)) _arrAddressVO
					.setIsCurrentAddress(RegistrationConfig.IS_ADDRESS_CURRENT_TRUE);
			else _arrAddressVO.setIsCurrentAddress(RegistrationConfig.IS_ADDRESS_CURRENT_FALSE);
			
			 * } for (int i = 0; i < _arrAddressVO.length; i++) { System.out.println("_arrAddressVO.length in for " +
			 * _arrAddressVO.length); System.out.println("i= " + i);
			 * //if(add_modify.equals(RegistrationConfig.PATIENT_ADDRESS_ADD)){ if ( (_arrAddressVO[i].getSerialNo() == null ||
			 * _arrAddressVO[i].getSerialNo().trim().equals("")) &&
			 * (_arrAddressVO[i].getPatAddTypeCode().equals(addTypeCode))) { System.out.println("add new address");
			 * _arrAddressVO[i].setVerificationDocumentId(serialNo);
			 
			if (addModify.equals(RegistrationConfig.IS_ADDRESS_ADDED))
			{
				_arrAddressVO.setVerificationDocumentId(serialNo);
				_arrAddressVO.setIsValid(Config.IS_VALID_ACTIVE);
				addDao.createNewAddress(_arrAddressVO, _userVO);
			//	patDao.updateAreaCategory(_arrAddressVO.getPatCrNo(),_arrAddressVO.getPatIsUrban(), _userVO);

			}
			
			 * } else { for (int j = 0; j < allAddressVO.length; j++) { System.out.println("_arrAddressVO[" + i +
			 * "].getSerialNo()::.. " + _arrAddressVO[i].getSerialNo()); System.out.println("allAddressVO[" + i +
			 * "].getSerialNo()::.. " + allAddressVO[i].getSerialNo()); System.out.println("_arrAddressVO[" + i +
			 * "].getPatAddType()::.. " + _arrAddressVO[i].getPatAddType()); System.out.println("allAddressVO[" + i +
			 * "].getPatAddType()::.. " + allAddressVO[i].getPatAddType()); if (
			 * (_arrAddressVO[i].getSerialNo().equals(allAddressVO[j].getSerialNo())) &&
			 * (_arrAddressVO[i].getPatAddTypeCode().equals(addTypeCode)) ) { if
			 * (_arrAddressVO[i].getPatAddType().equals(allAddressVO[j].getPatAddType()) &&
			 * _arrAddressVO[i].getIsValid().equals(RegistrationConfig.IS_VALID_ACTIVE)) { System.out.println("update
			 * address.....");
			 
			if (addModify.equals(RegistrationConfig.IS_ADDRESS_MODIFIED))
			{
				_arrAddressVO.setVerificationDocumentId(serialNo);
				_arrAddressVO.setIsValid(Config.IS_VALID_ACTIVE);
				// addDao.update(_arrAddressVO, _userVO);
				addDao.updatePreviousRow(_arrAddressVO, _userVO);
				addDao.insertNewRowAddress(_arrAddressVO, _userVO);
			//	patDao.updateAreaCategory(_arrAddressVO.getPatCrNo(),_arrAddressVO.getPatIsUrban(), _userVO);
			}
			
			 * } else { if((_arrAddressVO[i].getPatAddTypeCode().equals(addTypeCode))){
			 * System.out.println("updateWithAddressType....."); _arrAddressVO[i].setVerificationDocumentId(serialNo); x =
			 * addDao.updateWithAddressType(_arrAddressVO[i], _userVO); }
			 
			// }
			// }
			// }
			// }
			// }
			System.out.println("before create of verificationDocUsedDao");
			_verificationDocumentVO.setIsValid(Config.IS_VALID_ACTIVE);
			verificationDocUsedDao.create(_verificationDocumentVO, _userVO);
			System.out.println("after create of verificationDocUsedDao");
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		System.out.println("x=    " + x);
		return x;
	}

	/**
	 * Retrieves patient details on the basis of Previous CR No alloted in the previous system from the previous DB.
	 * 
	 * @param _patientVO Provides CR No to be searched.
	 * @param _userVO Provides User details.
	 * @return PatientVO with values stored in DB.
	 
	public PatientVO getPrevSystemPatDetail(PatientVO _patientVO, UserVO _userVO)
	{
		System.out.println("inside getPrevSystemPatDetail() of PatientBO");
		// System.out.println("previousCrNo::"+ _crNo);
		String str = _patientVO.getPrevCrNo();
		System.out.println("_patientVO.getPrevCrNo()::str:: " + str);
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// PatientVO _patientVO;
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			System.out.println("creating object of PatientDAO");
			AddressDAO addDao = new AddressDAO(tx);
			System.out.println("creating object of AddressDAO");

			AddressVO _addressVO = new AddressVO();
			// String str=_patientVO.getPatCrNo();
			System.out.println("_patientVO.getPrevCrNo()::str " + str);
			// populate the addressVO and patientVO with required details
			System.out.println("populate the  addressVO");
			_addressVO = addDao.previousSystemCrNo(_patientVO, _userVO);
			System.out.println("addressVO.getPatAddHNo()..::" + _addressVO.getPatAddHNo());
			System.out.println("populate the  patientVO");
			_patientVO = patientDao.previousSystemCrNo(_patientVO, _addressVO, _userVO);
			System.out.println("after populate");

			if (_patientVO.getPatAge() != null && _patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE))
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				System.out.println("patAgeWithUnit::" + patAgeWithUnit);
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);
				System.out.println("_patientVO.getPatAge()1:::" + _patientVO.getPatAge());

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Y");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("M");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
			}
			// System.out.println("Patient first name::"+_patientVO.getPatFirstName());
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _patientVO;
	}

	/**
	 * Facilitates issue of duplicate card for an OPD to a patient. Also enters the details of duplicate card in the DB.
	 * 
	 * @param _episodeVO Provides all the episodes which are opened for a patient.
	 * @param _duplicateRenewVO Provides duplicate card details.
	 * @param _userVO Provides User details.
	 * @return DuplicateRenewVO with values stored in DB.
	 */
	/*public EpisodeVO[] duplicateCardPrinting(RegChargeDtlVO[] _regChargeVO, RegCardPrintVO[] _regCardPrintVO, UserVO _userVO)
	{
		//System.out.println("inside duplicateCardPrinting() of PatientBO");
		// System.out.println("Patient First Name::"+_patientVO.getPatFirstName());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RegistrationSlip rSlip[]=new RegistrationSlip[_regCardPrintVO.length];
		String disclaimer[]=new String[_regCardPrintVO.length];
		String workingDays[]=new String[_regCardPrintVO.length];
		EpisodeVO[] episodeVO=new EpisodeVO[_regCardPrintVO.length];
		HisDAO daoObj= new HisDAO("Registration","PatientBO");
		
		try
		{
			//System.out.println("Begining transaction");
			tx.begin();
			// System.out.println("Patient First Name::"+_patientVO.getPatFirstName());
			// DuplicateRenewDAO duplicateRenewDAO = new DuplicateRenewDAO(tx);
			RegCardPrintDtlDAO regCardPrintDAO = new RegCardPrintDtlDAO(tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			DirectChageDetailVO[] directChargeVO=new DirectChageDetailVO[_regChargeVO.length];
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			for (int i = 0; i < _regCardPrintVO.length; i++)
			{
				regCardPrintDAO.saveReprintCard(_regCardPrintVO[i], _userVO);
			}
			for (int i = 0; i < _regChargeVO.length; i++)
			{
				if (!(_regChargeVO[i].getPatAmountCollected().equals("0") || 
						_regChargeVO[i].getPatAmountCollected().equals("") || 
						_regChargeVO[i].getPatAmountCollected() == null)) 
				{
				_regChargeVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				_regChargeVO[i].setTariffId(RegistrationConfig.DUPLICATE_RENEW_CARD_TARIFF_ID);
				_regChargeVO[i].setModuleId(_userVO.getModuleId());
				///registration module billing
				regChargeDtlDAO.create(daoObj,_regChargeVO[i], _userVO);
				directChargeVO[i]=new DirectChageDetailVO();
				HelperMethods.populate(directChargeVO[i], _regChargeVO[i]);
				directChargeDao.create(daoObj,directChargeVO[i], _userVO);
				
				daoObj.fire();
				///Billing module integration
				//regChargeDtlDAO.createBillinProcedure(_regChargeVO[i], _userVO);
				}
				episodeVO[i]=new EpisodeVO();
				
				//******** Query to fecth Disclaimer for printing *********************/
				/*String usablityFlag="";
				if(_regCardPrintVO[i].getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
					usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
				else if(_regCardPrintVO[i].getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL))
					usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
				else if(_regCardPrintVO[i].getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_EMERGENCY))
					usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_CASUALTIY;
				episodeVO[i].setDisclaimer(getDisclamer(usablityFlag, _regChargeVO[i].getDepartmentUnitCode(), _userVO));
							
				
			
				//query to fetch working days for printing
				episodeVO[i].setUnitWorkingDays(essentialDAO.getUnitWorkingDays(_regChargeVO[i].getDepartmentUnitCode(), _userVO));
				
			}
					
		    
			//System.out.println("after calling create of DuplicateRenewDAO");
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeVO;
	}
*/
	/**
	 * Facilitates the change of unit if a particular unit is not on duty or if the change of unit is required under some
	 * special conditions. Can only performed from MRD desk. Also saves the details of unit change in the DB.
	 * 
	 * @param _patientVO Provides patient's details.
	 * @param _episodeVO Provides the episode for which unit has to be changed.
	 * @param _unitChangeVO Provides details required for unit change.
	 * @param _userVO Provides User details.
	 * @return UnitChangeVO with values stored in DB.
	 */
	/*public UnitChangeVO unitChangeOldDeptVisit(PatientVO _patientVO, EpisodeVO _episodeVO, UnitChangeVO _unitChangeVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			UnitChangeDAO unitChangeDAO = new UnitChangeDAO(tx);
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			DailyPatientVO _dailyPatientVO = new DailyPatientVO();
			DailyPatientVO tmpdailyPatVO = new DailyPatientVO();

			synchronized (unitChangeDAO.getLock())
			{

				// Create DailyPatientVO from patientVO: Populate it

				HelperMethods.populate(_dailyPatientVO, _patientVO);

				_dailyPatientVO.setPatIsOld(RegistrationConfig.IS_OLD_TRUE);

				// populate dailyPatient VO with _arrEpisodeVO[i]
				HelperMethods.populate(_dailyPatientVO, _episodeVO);

				// Create a new entry in daily patient detail
				// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO

				_dailyPatientVO.setDepartmentUnitCode(_unitChangeVO.getToDepartmentUnitCode());

				tmpdailyPatVO = dailyPatientDAO.getTokenDtlOldDeptVisit(_dailyPatientVO, _userVO);

				_dailyPatientVO.setDepartmentCode(tmpdailyPatVO.getDepartmentCode());
				_dailyPatientVO.setDepartmentUnitCode(tmpdailyPatVO.getDepartmentUnitCode());
				_dailyPatientVO.setRoomCode(tmpdailyPatVO.getRoomCode());
				_dailyPatientVO.setQueNo(tmpdailyPatVO.getQueNo());
				_dailyPatientVO.setPatientAllowed(tmpdailyPatVO.getPatientAllowed());
				dailyPatientDAO.stampOldDeptVisit(_dailyPatientVO, _userVO);
				
				// EpisodeCode is assigned to dailyPatVO
				// populate episodeVO from DailyPatVO
				HelperMethods.populate(_episodeVO, _dailyPatientVO);

				// Create new entry in Episode detail
				episodeDAO.create(_episodeVO, _userVO);

				/*
				 * if(_unitChangeVO.getToDepartmentUnitCode().trim().equals("") ||
				 * _unitChangeVO.getToDepartmentUnitCode()==null )
				 * _unitChangeVO.setToDepartmentUnitCode(_episodeVO.getDepartmentUnitCode());
				 

				// populate episodeVO from DailyPatVO
				HelperMethods.populate(_unitChangeVO, _episodeVO);

				// Create a new entry in Unit Change detail
			
				_unitChangeVO = unitChangeDAO.create(_unitChangeVO, _userVO);

			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _unitChangeVO;
	}

	/**
	 * Registers a patient when he/she visits the hospital for the first time in an Emergency. Generates the CR No of the
	 * Patient. Calculates age of the patient if DOB is provided and DOB if age is provided. Saves data in Patient Dtl,
	 * Address dtl, Daily Patient dtl, and Episode dtl tables.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return EpisodeVO with values stored in DB.
	 */
	
	/*////used fro emergency new patient registration 1-Oct-2010
	public EpisodeVO emergencyPatRegistration(PatientVO _patientVO, PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO _episodeVO = new EpisodeVO();
		try
		{

			tx.begin();
			PatientDAO patDao = new PatientDAO(tx);

			_patientVO.setSeatId(_userVO.getSeatId());

			EssentialDAO essentialDAO = new EssentialDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			EpisodeReferDAO episodeReferDAO = new EpisodeReferDAO(tx);
			RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			EpisodeRefDtlVO episodeRefDtlVO = new EpisodeRefDtlVO();
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			PrimaryCategoryChangeVO primaryCategoryChangeVO = new PrimaryCategoryChangeVO();
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			//PrimaryCategoryChangeDAO primaryCategoryChangeDAO = new PrimaryCategoryChangeDAO(tx);
			//synchronized (patDao.getLock())
			//{
				EpisodeReferVO _episodeReferVO = new EpisodeReferVO();

				// Create a new Patients
				// Specify whether DOB is actual...s

				// System.out.println("_patientVO.getDepartmentCode()1::"+_patientVO.getDepartmentCode());

				// explicityly set setIsValid(RegistrationConfig.IS_VALID_ACTIVE)
				_patientVO.setIsValid(Config.IS_VALID_ACTIVE);
				if (_patientVO.getImageFile() != null) _patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_TRUE);
				else
				{
					_patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
				}

				_patientVO.setPatRegCatCode(RegistrationConfig.PATIENT_REG_CATEGORY_EMERGENCY);
				
				PatientBOSupport.checkForRenewalAtSaveToPatintDao(_patientVO, _userVO, tx);

				// System.out.println("_patientVO.getDepartmentCode()::::"+_patientVO.getDepartmentCode());
				_patientVO.getPatAddress().setPatCrNo(_patientVO.getPatCrNo());

				_patientVO.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.getPatAddress().setSeatId(_patientVO.getSeatId());
				// Create a new Address only if patient is known
				_patientVO.getPatAddress().setPatIsUrban(_patientVO.getPatIsUrban());
				if (_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE))
				{

					addDao.createNewAddress(_patientVO.getPatAddress(), _userVO);

				}

				DailyPatientVO dailyPatVO = new DailyPatientVO();
				HelperMethods.populate(dailyPatVO, _patientVO);
				// HelperMethods.populate(dailyPatVO,dailyPatientVO);

				dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);

				// populate the episodeVO with the general details
				PatientBOHelper.setNewPatRegEpisodeVO(_episodeVO);
				_episodeVO.setPatCrNo(dailyPatVO.getPatCrNo());
				_episodeVO.setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
				_episodeVO.setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
				_episodeVO.setDepartmentCode(dailyPatVO.getDepartmentCode());
				_episodeVO.setDepartmentUnitCode(dailyPatVO.getDepartmentUnitCode());
				// _arrEpisodeVO[i].setDepartmentCode("101");
				
				_episodeVO.setIsValid(Config.IS_VALID_ACTIVE);
				// populate this dailyPatient VO with _arrEpisodeVO[i]

				if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					_episodeVO.setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					// _episodeVO.setEpisodeReferAccept(RegistrationConfig.SYSDATE);
					_episodeVO.setIsReferred(_patientVO.getIsReferred());

					_episodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
				}
				else _episodeVO.setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);

				if (dailyPatVO.getIsMLC().equals(RegistrationConfig.IS_MLC_TRUE)) _episodeVO
						.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
				else _episodeVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC);

				// HelperMethods.populate(dailyPatVO, _episodeVO);
				PatientBOHelper.setDailyPatientDetails(dailyPatVO);

				// Create a new entry in daily patient detail
				// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO

				dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);

				dailyPatVO.setEpisodeTypeCode(_episodeVO.getEpisodeTypeCode());
				if (_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE))
				{
					dailyPatVO = dailyPatDao.createNewRegistration(dailyPatVO, _userVO);
				}
				if (_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
				{
					dailyPatVO.setPatAddCityLoc("");
					dailyPatVO.setPatAddCityLocCode("");
					dailyPatVO.setPatAddStateCode("");
					dailyPatVO.setPatAddCountryCode("");
					dailyPatVO.setIsAddressDelhi("");
					//dailyPatVO = dailyPatDao.create(dailyPatVO, _userVO);
				}
				
				/////generating episode and room usability
				String year=WebUTIL.getCustomisedSysDate(WebUTIL.getDateFromString(_patientVO.getSystemDate(),""), "yy");
				//String year=_patientVO.getSystemDate().substring(arg0)
				int index=1;
				String episodeNumber=_userVO.getHospitalCode()+year;
				if(index<=9)
					episodeNumber=episodeNumber+"00"+index;
				else
					episodeNumber=episodeNumber+"0"+index;
				dailyPatVO.setEpisodeCode(episodeNumber);
				dailyPatVO.setEpisodeVisitNo("1");
				
				if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL);
				else
					dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
				dailyPatVO = dailyPatDao.createNewRegistration(dailyPatVO, _userVO);
				// EpisodeCode is assigned to dailyPatVO

				// populate episodeVO from DailyPatVO
				//HelperMethods.populate(primaryCategoryChangeVO, dailyPatVO);
				//primaryCategoryChangeVO.setPatNewPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
				HelperMethods.populate(_episodeVO, dailyPatVO);

				//_episodeVO.setDepartmentCode(dailyPatientVO.getDepartmentCode());
				//_episodeVO.setDepartmentUnitCode(dailyPatientVO.getDepartmentUnitCode());
				//Create new entry in Episode detail
				if(_patientVO.getIsMLC().equals(RegistrationConfig.IS_MLC_TRUE))
				{
					_episodeVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
				}
				else
				{
					_episodeVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG);
				}

				// _episodeVO.setDepartmentCode(dailyPatientVO.getDepartmentCode());
				// _episodeVO.setDepartmentUnitCode(dailyPatientVO.getDepartmentUnitCode());
				// Create new entry in Episode detail

				_episodeVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG);
				_episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
				_episodeVO.setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
			////setting visit number//
				
				_episodeVO.setEpisodeVisitNo("1");
				//saving episode dtl
				_episodeVO.setEntryDate(_patientVO.getSystemDate());
				PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(_episodeVO, _userVO, tx);

				if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					// _episodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					
					 * String[] array = episodeDao.getEpisdoeCode(_episodeVO.getPatCrNo(),
					 * _episodeVO.getDepartmentUnitCode(), _userVO); _episodeVO.setEpisodeVisitNo(array[2]);
					 
					HelperMethods.populate(_episodeReferVO, _patientVO);
					HelperMethods.populate(_episodeReferVO, _episodeVO);
					// //////referdtl//////
					HelperMethods.populate(episodeRefDtlVO, _episodeVO);
					HelperMethods.populate(episodeRefDtlVO, _episodeReferVO);
					episodeRefDtlVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					episodeRefDtlVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
					episodeRefDtlVO.setExternalHospitalCode(_patientVO.getPatRefGnctdHospitalCode());
					episodeRefDtlVO.setExternalHospitalDepartment(_patientVO.getPatRefGnctdHospitalDept());
					episodeRefDtlVO.setExternalHospitalDepartmentUnit(_patientVO.getPatRefGnctdHospitalDeptUnit());
					episodeRefDtlVO.setExternalHospitalDoctorName(_patientVO.getPatRefDoctor());
					episodeRefDtlVO.setExternalHospitalName(_patientVO.getPatRefHospitalName());
					episodeRefDtlVO.setExternalHospitalPatCrNo(_patientVO.getPatRefGnctdHospitalCrno());
					episodeRefDtlVO.setToDepartmentCode(_episodeVO.getDepartmentCode());
					episodeRefDtlVO.setToDepartmentUnitCode(_episodeVO.getDepartmentUnitCode());
					episodeRefDtlVO.setEpisodeVisitNo("1");
					episodeRefDtlDAO.create(episodeRefDtlVO, _userVO);
					// ////end for referDTL////////////

					// episodeReferDAO.create(_episodeReferVO, _userVO);
				}

				RegChargeDtlVO regChgDtlVO = new RegChargeDtlVO();
				DirectChageDetailVO directChargeVO=new DirectChageDetailVO();
				regChgDtlVO.setTariffId(_userVO.getTariffID());
				regChgDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				regChgDtlVO.setModuleId(Config.MODULE_ID_EMERGENCY);
				HelperMethods.populate(regChgDtlVO, _episodeVO);
				regChgDtlVO.setPatAmountCollected(_patientVO.getPatAmountCollected());
				regChgDtlVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
				regChgDtlVO.setIsFree(_patientVO.getIsFree());
				if ((!(regChgDtlVO.getPatAmountCollected().equals("0") || regChgDtlVO.getPatAmountCollected().equals("") || regChgDtlVO
						.getPatAmountCollected() == null))
						&& (regChgDtlVO.getIsFree().equals(RegistrationConfig.IS_FREE_FALSE))) 
				{
					////registration module billing
					regChgDtlDAO.create(regChgDtlVO, _userVO);
					HelperMethods.populate(directChargeVO,regChgDtlVO);
					directChargeDao.create(directChargeVO, _userVO);
					///Billing module integration
				//	regChgDtlDAO.createBillinProcedure(regChgDtlVO, _userVO);
				}
				// String deptCode=_episodeVO.getDepartmentCode();
				// String deptUnitCode=_episodeVO.getDepartmentUnitCode();
				// dailyPatVO.setDepartmentCode(dailyPatientVO.getDepartmentCode());
				// dailyPatVO.setDepartmentUnitCode(dailyPatientVO.getDepartmentUnitCode());
				String deptCode = dailyPatVO.getDepartmentCode();
				String deptUnitCode = dailyPatVO.getDepartmentUnitCode();
				List al = new ArrayList();

				al = new EssentialDAO(tx).getNameValuesEmergency(deptCode, deptUnitCode);
				_episodeVO.setDepartment(al.get(0).toString());
				_episodeVO.setDepartmentUnit(al.get(1).toString());

				if ((Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE.equals(Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE)) && (_patBroughtByDtlVO != null))
				{
					HelperMethods.populate(_patBroughtByDtlVO, _episodeVO);
					_patBroughtByDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					_patBroughtByDtlVO.setEpisodeVisitNo("1");
					patientBroughtByDtlDAO.create(_patBroughtByDtlVO, _userVO);
				}

				if (_patientVO.getImageFile() != null)
				{
					HelperMethods.populate(patientImageDtlVO, _episodeVO);
					patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#1");
					HelperMethods.storeImageInCorrectFileSystem(_patientVO.getImageFile(), _patientVO.getImageFileName(), patientImageDtlVO
							.getFileNo(), Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
					boolean flag =HisFileControlUtil.isWindowsOS();
					  if(flag)
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
					  }
					  else
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
					  }
					//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
					patientImageDtlDAO.create(patientImageDtlVO, _userVO);
				}

				//primaryCategoryChangeDAO.create(primaryCategoryChangeVO, _userVO);
				
			/////Query to fetch Unit working days for printing////
				
				//_episodeVO.setUnitWorkingDays(essentialDAO.getUnitWorkingDays(_episodeVO.getDepartmentUnitCode(),_userVO));
			
			/////////////////////////////////////////////////////
				if(Config.CLIENT.equals(Config.CLIENT_GNCTD)){	
				*//******** query to fetch disclaimers *********//*
					_episodeVO.setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_CASUALTIY, _episodeVO.getDepartmentUnitCode(), _userVO));
					
				*//*******************************************//*	
				}	
			//}
		}
		catch (FileNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisInvalidTokenNumberException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _episodeVO; // <<<

	}// each VisitStampVO keeps PatientVO

	
////used fro emergency new patient registration 1-Oct-2010
	public EpisodeVO emergencyPatRegistration(PatientVO _patientVO, PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO, HttpServletRequest _request) throws HisDuplicateRecordException
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO _episodeVO = new EpisodeVO();
		boolean isDuplicateReg=false;
		Status objStatus=new Status();

		HisDAO daoObj = null;
		
		synchronized (PatientDAO.class)
		{
		try
		{
			daoObj = new HisDAO("Registration","PatientBO");
			
			tx.begin();
			PatientDAO patDao = new PatientDAO(tx);

			_patientVO.setSeatId(_userVO.getSeatId());

			EssentialDAO essentialDAO = new EssentialDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			EpisodeReferDAO episodeReferDAO = new EpisodeReferDAO(tx);
			RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			EpisodeRefDtlVO episodeRefDtlVO = new EpisodeRefDtlVO();
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			PrimaryCategoryChangeVO primaryCategoryChangeVO = new PrimaryCategoryChangeVO();
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			BPLDetailsDAO bplDAO = new BPLDetailsDAO();

			int i=0;
			String[] crNoArr = null;
			List allPatientVoList=new ArrayList();
			Map essentialMap = new HashMap();
			//synchronized (patDao.getLock())
			//{
				EpisodeReferVO _episodeReferVO = new EpisodeReferVO();

				_patientVO.setIsValid(Config.IS_VALID_ACTIVE);
				if (_patientVO.getImageFile() != null) _patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_TRUE);
				else
				{
					_patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
				}

				_patientVO.setPatRegCatCode(RegistrationConfig.PATIENT_REG_CATEGORY_EMERGENCY);
				
				_patientVO.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.getPatAddress().setSeatId(_patientVO.getSeatId());
				_patientVO.getPatAddress().setPatIsUrban(_patientVO.getPatIsUrban());
				// Create a new Address only if patient is known
				
				
				if(!_patientVO.getIsDuplicate().equals("1")){
					crNoArr=essentialDAO.checkDuplicateRegistration(_patientVO, _userVO);
					if(crNoArr!=null)					{
					PatientVO[] patientVOArr = new PatientVO[crNoArr.length];
					
					for(i=0;i<crNoArr.length;i++)
					{
						PatientVO vo=new PatientVO();
						vo.setPatCrNo(crNoArr[i]);
						patientVOArr[i]=vo	;
					}
					for(i=0;i<crNoArr.length;i++)
					{
						allPatientVoList.add(searchPatientByCrNo(patientVOArr[i], _userVO));	
					}

					essentialMap.put(RegistrationConfig.ALL_PATIENT_VO_LIST,allPatientVoList);
					}
						if(crNoArr!=null)
						{	WebUTIL.setMapInSession(essentialMap, _request);
						throw new HisDuplicateRecordException("Duplicate Registration");
						}
			}
						
				
				
				
				if(_patientVO.getPatDocType().indexOf("|")>0)
				{
					_patientVO.setPatDocType(_patientVO.getPatDocType().split("\\|")[0]);
				}

				DailyPatientVO dailyPatVO = new DailyPatientVO();
				HelperMethods.populate(dailyPatVO, _patientVO);
				// HelperMethods.populate(dailyPatVO,dailyPatientVO);

				dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);

				// populate the episodeVO with the general details
				PatientBOHelper.setNewPatRegEpisodeVO(_episodeVO);
				_episodeVO.setPatCrNo(dailyPatVO.getPatCrNo());
				_episodeVO.setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
				_episodeVO.setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
				_episodeVO.setDepartmentCode(dailyPatVO.getDepartmentCode());
				_episodeVO.setDepartmentUnitCode(dailyPatVO.getDepartmentUnitCode());
				
				
				_episodeVO.setIsValid(Config.IS_VALID_ACTIVE);
				// populate this dailyPatient VO with _arrEpisodeVO[i]

				if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					_episodeVO.setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					// _episodeVO.setEpisodeReferAccept(RegistrationConfig.SYSDATE);
					_episodeVO.setIsReferred(_patientVO.getIsReferred());

					_episodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
				}
				else _episodeVO.setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);

				if (dailyPatVO.getIsMLC().equals(RegistrationConfig.IS_MLC_TRUE)) _episodeVO
						.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
				else _episodeVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC);

				// HelperMethods.populate(dailyPatVO, _episodeVO);
				PatientBOHelper.setDailyPatientDetails(dailyPatVO);

				// Create a new entry in daily patient detail
				// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO

				dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);

				dailyPatVO.setEpisodeTypeCode(_episodeVO.getEpisodeTypeCode());
				if (_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE))
				{
					dailyPatVO = dailyPatDao.createNewRegistration(dailyPatVO, _userVO);
				}
				if (_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
				{
					dailyPatVO.setPatAddCityLoc("");
					dailyPatVO.setPatAddCityLocCode("");
					dailyPatVO.setPatAddStateCode("");
					dailyPatVO.setPatAddCountryCode("");
					dailyPatVO.setIsAddressDelhi("");
					//dailyPatVO = dailyPatDao.create(dailyPatVO, _userVO);
				}
				
			
				dailyPatVO.setEpisodeVisitNo("1");
				
				if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
				else
					dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
				
				/* isDuplicateReg=essentialDAO.checkDuplicateRegistration(_patientVO, _userVO);
				if(isDuplicateReg)
					throw new HisDuplicateRecordException("Duplicate Registration"); */
				
				//generate queue number
				
				
				//dailyPatVO=dailyPatDao.generateQueueNumber(dailyPatVO, _userVO);
			
			//generating the Cr Number
			/*String CrNO=patDao.generateCrNumber(_userVO);
			_patientVO.setPatCrNo(CrNO);
			_patientVO.getPatAddress().setPatCrNo(_patientVO.getPatCrNo());
			_patientVO.setSeatId(_userVO.getSeatId());
			*/
			///get age or DOB
			//_patientVO=patDao.getDOBAndAge(_patientVO);
			
		
			//seting dialypatientvo details
			/*dailyPatVO.setPatCrNo(CrNO);
			dailyPatVO.setPatAge(_patientVO.getPatAge());
			dailyPatVO.setPatDOB(_patientVO.getPatDOB());			
			dailyPatVO.setPatAgeUnit(_patientVO.getPatAgeUnit());
			dailyPatVO.setIsActualDob(_patientVO.getIsActualDob());
			*/
			///get age or DOB
			//_patientVO=patDao.getDOBAndAge(_patientVO);
			
			

			/*BPLDetailsVO bplVO=new BPLDetailsVO();
			if(dailyPatVO.getPatPrimaryCatCode().equals(Config.PRIMARY_CATEGORY_BPL_CODE))//BPL Category
			{
				HelperMethods.populate(bplVO, _patientVO);
				bplVO.setPatCrNo(CrNO);
				bplVO.setMemberName(_patientVO.getPatFirstName()+" "+_patientVO.getPatMiddleName()+" "+_patientVO.getPatLastName());
				//bplVO = bplDAO.saveBPLDetails(daoObj,bplVO, _userVO);
			}*/
			
			////setching chagres again in case the ajax for charge has not responded
			//change done by : Prakhar Misra
			//Dated: 3-Feb-2012
		//	String billAmount=essentialDAO.getBillAmountBasedOnCategory(dailyPatVO.getPatPrimaryCatCode(), _userVO);
			
			
			//explicity fetching and setting the amount in VO
			//dailyPatVO.setPatAmountCollected(billAmount);
				///inserting into daily patient detail
			//	dailyPatVO = dailyPatDao.createNewRegistration(daoObj,dailyPatVO, _userVO);
				
				//explicity fetching and setting the amount in VO
			//	_patientVO.setPatAmountCollected(billAmount);	
				//insert into patient detail
			//	String p_mode="2";
				//PatientBOSupport.checkForRenewalAtSaveToPatintDao(daoObj,_patientVO, _userVO, tx,p_mode);
				
				/*if(dailyPatVO.getIsActualDob().equals("1"))
				{
					_patientVO.setPatAge(dailyPatVO.getPatAge());
					_patientVO.setPatAgeUnit("");
				}*/
				//insert into address detail
				/*if (_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE))
				{
					addDao.createNewAddress(daoObj,_patientVO.getPatAddress(), _userVO);
				}
				HelperMethods.populate(_episodeVO, dailyPatVO);
*/
				
				// Create new entry in Episode detail

				/*_episodeVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG);
				_episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
				_episodeVO.setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
				*/////setting visit number//
				
				//_episodeVO.setEpisodeVisitNo("1");
				//saving episode dtl
				//_episodeVO.setEntryDate(_patientVO.getSystemDate());
				//PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(daoObj,_episodeVO, _userVO, tx);
			///	_episodeVO.setDepartmentCode(_episodeVO.getDepartmentUnitCode());
				//
				//changed on 14-feb-2012 by prakhar misra
				//to calculate expiry date out of procedure so that it cold be used for prinintg
				//String expiryDate=episodeDao.getExpiryDate(_episodeVO,_userVO);
				
				//_episodeVO.setExpiryDate(expiryDate);
				////_episodeVO=episodeDao.create(daoObj, _episodeVO, _userVO);

				/*if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					
					HelperMethods.populate(_episodeReferVO, _patientVO);
					HelperMethods.populate(_episodeReferVO, _episodeVO);

					HelperMethods.populate(episodeRefDtlVO, _episodeVO);
					HelperMethods.populate(episodeRefDtlVO, _episodeReferVO);
					episodeRefDtlVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					episodeRefDtlVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
					episodeRefDtlVO.setExternalHospitalCode(_patientVO.getPatRefGnctdHospitalCode());
					episodeRefDtlVO.setExternalHospitalDepartment(_patientVO.getPatRefGnctdHospitalDept());
					episodeRefDtlVO.setExternalHospitalDepartmentUnit(_patientVO.getPatRefGnctdHospitalDeptUnit());
					episodeRefDtlVO.setExternalHospitalDoctorName(_patientVO.getPatRefDoctor());
					episodeRefDtlVO.setExternalHospitalName(_patientVO.getPatRefHospitalName());
					episodeRefDtlVO.setExternalHospitalPatCrNo(_patientVO.getPatRefGnctdHospitalCrno());
					episodeRefDtlVO.setToDepartmentCode(_episodeVO.getDepartmentCode());
					episodeRefDtlVO.setToDepartmentUnitCode(_episodeVO.getDepartmentUnitCode());
					episodeRefDtlVO.setEpisodeVisitNo("1");
					episodeRefDtlDAO.create(daoObj,episodeRefDtlVO, _userVO);
					// ////end for referDTL////////////

				
				}*/

				/*RegChargeDtlVO regChgDtlVO = new RegChargeDtlVO();
				DirectChageDetailVO directChargeVO=new DirectChageDetailVO();
				regChgDtlVO.setTariffId(_userVO.getTariffID());
				regChgDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				regChgDtlVO.setModuleId(Config.MODULE_ID_EMERGENCY);
				HelperMethods.populate(regChgDtlVO, _episodeVO);
				regChgDtlVO.setPatAmountCollected(_patientVO.getPatAmountCollected());
				regChgDtlVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
				regChgDtlVO.setIsFree(_patientVO.getIsFree());
				if ((!(regChgDtlVO.getPatAmountCollected().equals("0") || regChgDtlVO.getPatAmountCollected().equals("") || regChgDtlVO
						.getPatAmountCollected() == null))
						&& (regChgDtlVO.getIsFree().equals(RegistrationConfig.IS_FREE_FALSE))) 
				{
					////registration module billing
					regChgDtlVO.setDepartmentCode(regChgDtlVO.getDepartmentUnitCode().substring(0,3));
					regChgDtlDAO.create(daoObj,regChgDtlVO, _userVO);
					HelperMethods.populate(directChargeVO,regChgDtlVO);
					directChargeVO.setDepartmentCode(directChargeVO.getDepartmentUnitCode().substring(0,3));
					directChargeDao.create(daoObj,directChargeVO, _userVO);*/
					///Billing module integration
				//	regChgDtlDAO.createBillinProcedure(regChgDtlVO, _userVO);
				//}
				
				/*if ((Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE.equals(Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE)) && (_patBroughtByDtlVO != null))
				{
					HelperMethods.populate(_patBroughtByDtlVO, _episodeVO);
					_patBroughtByDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					_patBroughtByDtlVO.setEpisodeVisitNo("1");
					patientBroughtByDtlDAO.create(daoObj,_patBroughtByDtlVO, _userVO);
				}*/

				/*_episodeVO.setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_CASUALTIY,_episodeVO.getDepartmentUnitCode(),_userVO));
				
				PatientAdhaarDtlDAO patAdhaarDtlDao = new PatientAdhaarDtlDAO(tx);
				if((_patientVO.getPatNationalId()!=null)&&!(_patientVO.getPatNationalId().equals("")))
					patAdhaarDtlDao.create(_patientVO, _userVO);*/
				
				//synchronized (daoObj) {
				//	daoObj.fire();
				
				
				
				/*if (_patientVO.getImageFile() != null)
				{
					HelperMethods.populate(patientImageDtlVO, _episodeVO);
					patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#1");
					HelperMethods.storeImageInCorrectFileSystem(_patientVO.getImageFile(), _patientVO.getImageFileName(), patientImageDtlVO
							.getFileNo(), Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
					boolean flag =HisFileControlUtil.isWindowsOS();
					  if(flag)
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
					  }
					  else
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
					  }
					//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
					patientImageDtlDAO.create(patientImageDtlVO, _userVO);
				}	
			
		}
		/*catch (FileNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
			
		}
		catch(HisDuplicateRecordException e){
			tx.rollback();
			throw new HisDuplicateRecordException("Duplicate Registration");
		}

		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}
		return _episodeVO; // <<<

	}// each VisitStampVO keeps PatientVO

	/**
	 * Stamps the visit of a patient when he/she re-visits the hosital in an emergency. New episode is started whenever a
	 * patient visits in emergency. Saves data in Daily Patient dtl, and Episode dtl tables.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return EpisodeVO with values stored in DB.
	 
	public EpisodeVO emergencyOldDeptVisitStamp(PatientVO _patientVO, EpisodeVO _episodeVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// EpisodeVO _episodeVO=new EpisodeVO();

		try
		{
			tx.begin();
			
			PatientBOSupport.emergencyOldDeptVisitStamp(_patientVO,_episodeVO,_userVO,tx);
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _episodeVO;
	}

	
	 * Modifies patient details at emergency registration desk. Modification at registration can be done with in 10 minutes
	 * of registration. Updates the record in Patient dtl and Address dtl tables. Also updates patient's demographic details
	 * in Daily Patient Detail table.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return <code>true</code> if records are updated otherwise <code>false</code>.
	 
	public boolean emergencyPatDtlModificationREG(PatientVO _patientVO, PatientBroughtByDtlVO patientBroughtByDtlVO, PatientVO _patientVOOldData,
			UserVO _userVO)
	{
		int x = 0, y = 0, z = 0, m = 0, n = 0;

		String str = _patientVO.getPatCrNo();
		String isUnknown = _patientVO.getIsUnknown();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		// PatientVO _patientVO;
		HisDAO daoObj = null;
		try
		{
			daoObj = new HisDAO("Registration", "PatientBO");

			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			PatientAuditDAO patAuditDao = new PatientAuditDAO(tx);
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);

			EpisodeVO _episodeVO = new EpisodeVO();

			EpisodeRefDtlVO episodeRefDtlVO = new EpisodeRefDtlVO();
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);

			DailyPatientVO _dailyPatientVO = new DailyPatientVO();
			// String str=_patientVO.getPatCrNo();
			
			if(_patientVO.getPatDocType().indexOf("|")>0)
			{
				_patientVO.setPatDocType(_patientVO.getPatDocType().split("\\|")[0]);
			}
			

			_patientVO.getPatAddress().setPatCrNo(_patientVO.getPatCrNo());
			if (_patientVO.getMlcNo() == null || _patientVO.getMlcNo().equals(""))
			{
				_patientVO.setMlcNo(RegistrationConfig.IS_MLC_FALSE);
			}
			if (_patientVO.getImageFile() != null) _patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_TRUE);
			else
			{
				_patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
			}
			
			synchronized (daoObj)
			{
				x = patientDao.update(daoObj, _patientVO, _userVO);
	
				if (x >= 1)
				{
	
					// y = addDao.update(_patientVO.getPatAddress(), _userVO);
					y = addDao.updatePreviousRow(daoObj, _patientVO.getPatAddress(), _userVO);
					addDao.insertNewRowAddress(daoObj, _patientVO.getPatAddress(), _userVO);
				}
				HelperMethods.populate(_dailyPatientVO, _patientVO);
				if (y >= 1)
				{
					z = dailyPatDao.update(daoObj, _dailyPatientVO, _userVO);
				}
				if (z >= 1)
				{
					HelperMethods.populate(episodeRefDtlVO, _patientVO);
	
					episodeRefDtlVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
					episodeRefDtlVO.setExternalHospitalCode(_patientVO.getPatRefGnctdHospitalCode());
					episodeRefDtlVO.setExternalHospitalDepartment(_patientVO.getPatRefGnctdHospitalDept());
					episodeRefDtlVO.setExternalHospitalDepartmentUnit(_patientVO.getPatRefGnctdHospitalDeptUnit());
					episodeRefDtlVO.setExternalHospitalDoctorName(_patientVO.getPatRefDoctor());
					episodeRefDtlVO.setExternalHospitalName(_patientVO.getPatRefHospitalName());
					episodeRefDtlVO.setExternalHospitalPatCrNo(_patientVO.getPatRefGnctdHospitalCrno());
					episodeRefDtlVO.setToDepartmentCode(_patientVO.getDepartmentCode());
					episodeRefDtlVO.setToDepartmentUnitCode(_patientVO.getDepartmentUnitCode());
					_episodeVO.setPatCrNo(_patientVO.getPatCrNo());
					_episodeVO.setPatSecondaryCatCode(_patientVO.getPatSecondaryCatCode());
					_episodeVO.setPatPrimaryCatCode(_patientVO.getPatPrimaryCatCode());
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						_episodeVO.setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						// _episodeVO.setEpisodeReferAccept(RegistrationConfig.SYSDATE);
						episodeRefDtlVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
						m = episodeDAO.updateEpisodeDtlAtREG(daoObj,_episodeVO, _userVO);
						// _patientVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
	
						if (m >= 0) episodeRefDtlVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
						episodeRefDtlVO.setExternalHospitalCode(_patientVO.getPatRefGnctdHospitalCode());
						episodeRefDtlVO.setExternalHospitalDepartment(_patientVO.getPatRefGnctdHospitalDept());
						episodeRefDtlVO.setExternalHospitalDepartmentUnit(_patientVO.getPatRefGnctdHospitalDeptUnit());
						episodeRefDtlVO.setExternalHospitalDoctorName(_patientVO.getPatRefDoctor());
						episodeRefDtlVO.setExternalHospitalName(_patientVO.getPatRefHospitalName());
						episodeRefDtlVO.setExternalHospitalPatCrNo(_patientVO.getPatRefGnctdHospitalCrno());
						episodeRefDtlVO.setToDepartmentCode(_patientVO.getDepartmentCode());
						episodeRefDtlVO.setToDepartmentUnitCode(_patientVO.getDepartmentUnitCode());
						if (_patientVO.getPreviouslyReffered().equals(RegistrationConfig.IS_REFERRED_TRUE))
						{
							n = episodeRefDtlDAO.updateRefEpisodeDtlAtREG(daoObj, episodeRefDtlVO, _userVO);
						}
						else
						{
							episodeRefDtlDAO.create(daoObj, episodeRefDtlVO, _userVO);
							n = 1;
						}
	
					}
					else
					{
						_episodeVO.setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
						m = episodeDAO.updateEpisodeDtlAtREG(daoObj, _episodeVO, _userVO);
	
					}
					// saving brought by detail
					if (patientBroughtByDtlVO.getIsBroughtBy().equals(RegistrationConfig.IS_BROUGHT_BY_TRUE))
					{
						if (patientBroughtByDtlVO.getIsAddedEarlier().equals(RegistrationConfig.IS_BROUGHT_BY_TRUE))
						{
							n = patientBroughtByDtlDAO.update(daoObj, patientBroughtByDtlVO, _userVO);
							patientBroughtByDtlDAO.create(daoObj, patientBroughtByDtlVO, _userVO);
						}
						else
						{
							patientBroughtByDtlDAO.create(daoObj, patientBroughtByDtlVO, _userVO);
							n = 1;
						}
					}
					else
					{ // if brought by checkbox is unchecked
						n = patientBroughtByDtlDAO.update(daoObj, patientBroughtByDtlVO, _userVO);
					}
				}
				
				 * if (_patientVO.getImageFile() != null) HelperMethods.storeImageInCorrectFileSystem(_patientVO.getImageFile(),
				 * _patientVO .getImageFileName(), _episodeVO.getPatCrNo(), Config.PATIENT_IMAGE_FILE_STORAGE_PATH,
				 * Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
				 
				if (_patientVO.getImageFile() != null)
				{
					PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
					HelperMethods.populate(patientImageDtlVO, _episodeVO);
					String fileno = _episodeVO.getPatCrNo() + "#1";
	
					HelperMethods.storeImageInCorrectFileSystem(_patientVO.getImageFile(), _patientVO.getImageFileName(), fileno,
							Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
	
					boolean flag = HisFileControlUtil.isWindowsOS();
					if (flag)
					{
						patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					}
					else
					{
						patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
					}
					// patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
					patientImageDtlVO.setFileNo(fileno);
					// to check if image has already been uploaded or not
					String slno = patientImageDtlDAO.selctMaxSerialNo(patientImageDtlVO.getPatCrNo(), _userVO);
					if (slno.equals("1"))
					{
						patientImageDtlDAO.create(daoObj, patientImageDtlVO, _userVO);
					}
				}
				// /////Audit Of Patient Detail
	
				patAuditDao.create(daoObj, _patientVOOldData, _userVO);
				

				PatientAdhaarDtlDAO patAdhaarDtlDao=new PatientAdhaarDtlDAO(tx);
				patAdhaarDtlDao.update(_patientVO, _userVO);
					
				daoObj.fire();
			}
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
		}
		finally
		{
			tx.close();
		}
		if (n >= 1 || m >= 1) return true;
		else return false;
	}

	/*public void savePatientNotAttendedDetail(EpisodeActionDtlVO _episodeActDtlVO, UserVO _userVO)
	{
		// remarks field still needs to be handled
		System.out.println("inside savePatientNotAttendedDetail() of PatientBO");
		PatientVO patientVO = new PatientVO();
		patientVO.setPatCrNo(_episodeActDtlVO.getPatCrNo());
		String str = patientVO.getPatCrNo();
		System.out.println("_patientVO.getPatCrNo()::str:: " + str);
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			EpisodeVO episodeVO[] = new EpisodeVO[]
			{};
			EpisodeCloseVO epCloseVO = new EpisodeCloseVO();
			System.out.println("Begining transaction");
			tx.begin();
			EpisodeCloseDAO episodeCloseDao = new EpisodeCloseDAO(tx);
			EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			System.out.println("creating object of EpisodeCloseDAO");

			System.out.println("populate the Epidose closeVO");
			episodeVO = searchPatientEpiosdeByCrNo(patientVO, _userVO, RegistrationConfig.EPISODE_VISIT_TYPE_EMG,
					RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
			for (int i = 0; i < episodeVO.length; i++)
			{
				episodeVO[i].setEpisodeActionCode(_episodeActDtlVO.getEpisodeActionCode());
				episodeVO[i].setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_FALSE);
				episodeVO[i].setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
				System.out.println("episodeVO[i]" + episodeVO[i].getPatCrNo());
				System.out.println("episodeVO[i]" + episodeVO[i].getEpisodeDate());
				HelperMethods.populate(epCloseVO, episodeVO[i]);
				System.out.println("episodeVO[i]" + epCloseVO.getEpisodeDate());
				episodeDao.updateEpisodeDtlForCMO(episodeVO[i], _userVO);
				episodeCloseDao.create(epCloseVO, _userVO);
				HelperMethods.populate(_episodeActDtlVO, episodeVO[i]);
				episodeActionDtlDAO.create(_episodeActDtlVO, _userVO);
			}
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void savePatientAttendedAdmittedDetail(PatientVO _patientVO, UserVO _userVO)
	{
		/*
		 * System.out.println("inside saveCMOPatientNotAttendedDetail() of PatientBO"); String str=_patientVO.getPatCrNo();
		 * System.out.println("_patientVO.getPatCrNo()::str:: "+str); JDBCTransactionContext tx=new JDBCTransactionContext();
		 * try{ EpisodeVO episodeVO=new EpisodeVO[]{}; EpisodeCloseVO epCloseVO=new EpisodeCloseVO();
		 * System.out.println("Begining transaction"); tx.begin(); EpisodeCloseDAO episodeCloseDao = new EpisodeCloseDAO(tx);
		 * System.out.println("creating object of EpisodeCloseDAO"); synchronized(episodeCloseDao.getLock()){
		 * System.out.println("populate the Epidose closeVO"); episodeVO=
		 * searchPatientEpiosdeByCrNo(_patientVO,_userVO,_objStatus); for(int i=0;i<episodeVO.length;i++) {
		 * System.out.println("episodeVO[i]"+episodeVO[i].getPatCrNo());
		 * System.out.println("episodeVO[i]"+episodeVO[i].getEpisodeDate()); HelperMethods.populate(epCloseVO,episodeVO[i]);
		 * System.out.println("episodeVO[i]"+epCloseVO.getEpisodeDate()); episodeCloseDao.create( epCloseVO,
		 * _userVO,_objStatus); } }
		 * 
		 * }catch(HisApplicationExecutionException e){
		 * 
		 * _objStatus.add(Status.ERROR_AE,RegistrationConfig.APPLICATION_EXECUTION_ERROR,e.getMessage()); tx.rollback();
		 * System.out.println(e.getMessage()); }
		 * 
		 * catch(HisDataAccessException e){ Entry objEntry= Status.ERROR_DA;
		 * _objStatus.add(objEntry,RegistrationConfig.DATA_ACCESS_ERROR,e.getMessage()); System.out.println(e.getMessage()); tx.rollback(); }
		 * catch(Exception e) {
		 * _objStatus.add(Status.ERROR_AE,RegistrationConfig.APPLICATION_EXECUTION_ERROR,e.getMessage());
		 * System.out.println(e.getMessage()); tx.rollback(); System.out.println(e.getMessage()); } finally{ tx.close(); }
		 

	}

	//Modified by Akash Singh, Due to table changed in G5, dated on 21-07-2015
	public MlcVO patMlcDtl(MlcVO _mlcVO, UserVO _userVO, PatientBroughtByDtlVO _patBroughtByDtlVO, PoliceVerificationDtlVO policeVerDtlVO)
	{

		HisDAO daoObj = new HisDAO("MLC Detail", "patientBO");
		boolean exist;		
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			//System.out.println("Begining transaction");
			tx.begin();
			MlcDAO mlcDao = new MlcDAO(tx);
			// PatientVO patvo =new PatientVO();
			// _mlcVOlgetPatCrNo(_mlcVO.);

			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			PoliceVerificationDAOi policeVerDAO=new PoliceVerificationDAO(tx);
			
			
			
			exist=mlcDao.checkExistingMLCNo(_mlcVO,_userVO);
			if(!exist)
			{
				_mlcVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
				mlcDao.create(daoObj,_mlcVO, _userVO);
				
				episodeDAO.episodeUpdateMlcNo(_mlcVO, _userVO);
				//episodeDAO.episodeInsertMlcNo(_mlcVO, _userVO);  //Added by Akash Singh, Due to table changed in G5, dated on 20-07-2015
				episodeDAO.episodeDetailUpdateMlcFlag(_mlcVO, _userVO);
				episodeDAO.patientDetailUpdateMlcFlag(_mlcVO, _userVO);
				episodeDAO.dailyPatientDetailUpdateMlcFlag(_mlcVO, _userVO);
	
				if ((Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE.equals(Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE)) && (_patBroughtByDtlVO != null))
				{
					HelperMethods.populate(_patBroughtByDtlVO, _mlcVO);
					_patBroughtByDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					patientBroughtByDtlDAO.create(_patBroughtByDtlVO, _userVO);
				}
				if(policeVerDtlVO!=null)
				{
					policeVerDAO.create(policeVerDtlVO,_userVO);
				}
				//synchronized (daoObj) {
				//		daoObj.fire();
				//}
			}
			else
			{
				throw new HisDuplicateRecordException("MLC No Already Exist");
			}
			//System.out.println("after MLC Records entered");
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());

		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());

		}
		finally
		{
			if (daoObj != null) {
				//daoObj.free();
				daoObj = null;
			}
			
			tx.close();
		}
		return _mlcVO;
	}

	/**
	 * Retrieves patient MLC details on the basis of CR No from the Patient MLC Dtl Table.
	 * 
	 * @param _patientVO Provides patient details.
	 * @param _mlcVO Provides MLC no no and episode no.
	 * @param _userVO Provides User details.
	 * @return MlcVO with values stored in DB.
	 
	public MlcVO getMlcDtl(PatientVO _patientVO, MlcVO _mlcVO, UserVO _userVO)
	{
		System.out.println("inside getMlcDtl() of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			MlcDAO mlcDao = new MlcDAO(tx);
			System.out.println("creating object of MlcDAO");

			System.out.println("populate the  MlcVO");
			_mlcVO = mlcDao.retrieveByCrNo(_patientVO, _mlcVO, _userVO);
			System.out.println("after populating MlcVO");

		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}

		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _mlcVO;
	}

	/**
	 * Creates a new row in MLC dtl table each time a record is to be modified so as to keep track of all modifications.
	 * 
	 * @param _mlcVO Provides MLC details.
	 * @param _userVO Provides User details.
	 
	public void modifyMlcDtl(MlcVO _mlcVO, UserVO _userVO, PatientBroughtByDtlVO _patBroughtByDtlVO,PoliceVerificationDtlVO policeVerDtlVO)
	{

		
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			
			tx.begin();
			MlcDAO mlcDao = new MlcDAO(tx);
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			PoliceVerificationDAOi policeVerDAO=new PoliceVerificationDAO(tx);
			// System.out.println("creating object of MlcDAO");
			
			_mlcVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
			mlcDao.updateprevRecord(_mlcVO,_userVO);
			mlcDao.updateMlcRecords(_mlcVO, _userVO);
			if ((Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE.equals(Config.EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE)) && (_patBroughtByDtlVO != null))
			{
				HelperMethods.populate(_patBroughtByDtlVO, _mlcVO);
				_patBroughtByDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
				patientBroughtByDtlDAO.create(_patBroughtByDtlVO, _userVO);
			}
			
			if(policeVerDtlVO!=null)
			{
				policeVerDAO.create(policeVerDtlVO,_userVO);
			}

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());

		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());

		}
		finally
		{
			tx.close();
		}
	}

	// Save Death Detail By CMO
	public void saveDeathDetail(EpisodeDeathVO _episodeDeathVO, PatientVO _patientVO, UserVO _userVO)
	{
		System.out.println("inside saveDeathDetail() of PatientBO");

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			// String str = _patientVO.getPatCrNo();
			// System.out.println("_patientVO.getPatCrNo()::str:: " + str);

			EpisodeVO episodeVO[] = new EpisodeVO[]
			{};
			EpisodeCloseVO epCloseVO = new EpisodeCloseVO();
			PatientStatusChangeVO patientStatusChangeVO = new PatientStatusChangeVO();

			// System.out.println("Begining transaction");

			tx.begin();

			EpisodeCloseDAO episodeCloseDao = new EpisodeCloseDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			EpisodeDeathDAO episodeDeathDao = new EpisodeDeathDAO(tx);
			PatientStatusChangeDAO patientStatusChangeDAO = new PatientStatusChangeDAO(tx);

			// System.out.println("creating object of EpisodeCloseDAO");
			// System.out.println("populate the Epidose closeVO");
			episodeVO = searchPatientEpiosdeByCrNo(_patientVO, _userVO, RegistrationConfig.EPISODE_VISIT_TYPE_EMG,
					RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);

			episodeVO[0].setEpisodeActionCode(RegistrationConfig.EPISODE_ACTION_DEATH);
			episodeVO[0].setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_FALSE);
			episodeVO[0].setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);

			// System.out.println("episodeVO[i]" + episodeVO[0].getPatCrNo());
			// System.out.println("episodeVO[i]" + episodeVO[0].getEpisodeDate());
			HelperMethods.populate(epCloseVO, episodeVO[0]);
			_episodeDeathVO.setEpisodeVisitNo(episodeVO[0].getEpisodeVisitNo());
			_episodeDeathVO.setEpisodeCode(episodeVO[0].getEpisodeCode());
			// System.out.println("episodeVO[i]" + epCloseVO.getEpisodeDate());

			episodeDao.updateEpisodeDtlForCMO(episodeVO[0], _userVO);

			episodeDeathDao.create(_episodeDeathVO, _userVO);

			episodeCloseDao.create(epCloseVO, _userVO);

			HelperMethods.populate(patientStatusChangeVO, episodeVO[0]);
			patientStatusChangeVO.setPatStatusCodeNew(RegistrationConfig.PATIENT_STATUS_CODE_DEAD);
			// patientStatusChangeVO.setPatStatusCodeOld(RegistrationConfig.PATIENT_STATUS_CODE_NOT_DEAD);
			patientStatusChangeVO.setPatStatusCodeOld(_patientVO.getPatStatusCode());
			
			//Closed on 20-02-2015 bec table does not exist & no required for nims
			//patientStatusChangeDAO.create(patientStatusChangeVO, _userVO);

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	// Save Brought Death Detail From Desk
	public void saveBroughtDeathDetail(EpisodeDeathVO _episodeDeathVO, DailyPatientVO _dailypatVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		EpisodeVO episodeVO = new EpisodeVO();
		EpisodeCloseVO epCloseVO = new EpisodeCloseVO();
		PatientStatusChangeVO patientStatusChangeVO = new PatientStatusChangeVO();
		try
		{
			tx.begin();

			EpisodeCloseDAO episodeCloseDao = new EpisodeCloseDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			EpisodeDeathDAO episodeDeathDao = new EpisodeDeathDAO(tx);
			PatientStatusChangeDAO patientStatusChangeDAO = new PatientStatusChangeDAO(tx);
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);

			// Retrieving Episode Data
			episodeVO.setPatCrNo(_episodeDeathVO.getPatCrNo());
			episodeVO.setEpisodeCode(_episodeDeathVO.getEpisodeCode());
			episodeVO = episodeDao.retrieveByEpisodeNo(episodeVO, _userVO);

			episodeVO.setEpisodeVisitNo(_episodeDeathVO.getEpisodeVisitNo());
			episodeVO.setEpisodeActionCode(RegistrationConfig.EPISODE_ACTION_DEATH);
			episodeVO.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_FALSE);
			episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);

			// Updating Episode Detail
			HelperMethods.populate(epCloseVO, episodeVO);
			episodeDao.updateEpisodeDtlForCMO(episodeVO, _userVO);

			// Inserting Eisode Death Detail Record
			HelperMethods.populate(_episodeDeathVO, _dailypatVO);
			_episodeDeathVO.setEpisodeVisitNo(episodeVO.getEpisodeVisitNo());
			_episodeDeathVO.setEpisodeCode(episodeVO.getEpisodeCode());
			episodeDeathDao.create(_episodeDeathVO, _userVO);

			// Inserting Episode Close Record
			episodeCloseDao.create(epCloseVO, _userVO);

			// Creating Patient Status Change VO
			HelperMethods.populate(patientStatusChangeVO, episodeVO);
			patientStatusChangeVO.setPatStatusCodeNew(RegistrationConfig.PATIENT_STATUS_CODE_DEAD);
			patientStatusChangeVO.setPatStatusCodeOld(_dailypatVO.getPatStatusCode());
			// patientStatusChangeVO.setPatStatusCodeOld(RegistrationConfig.PATIENT_STATUS_CODE_NOT_DEAD);

			// Inserting Patient Status Change Record
			patientStatusChangeDAO.create(patientStatusChangeVO, _userVO);

			dailyPatientDAO.updateIsconfirmed(_dailypatVO.getPatCrNo(), _dailypatVO.getSerialNo(), _dailypatVO.getEpisodeVisitNo(), _dailypatVO
					.getEpisodeCode(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED, _userVO);
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	public void savePatientReferredOut(EpisodeReferVO _episodeReferVO, EpisodeActionDtlVO _episodeActDtlVO, UserVO _userVO)
	{
		System.out.println("inside savePatientReferredOut() of PatientBO");
		PatientVO patientVO = new PatientVO();
		patientVO.setPatCrNo(_episodeActDtlVO.getPatCrNo());
		String str = patientVO.getPatCrNo();
		System.out.println("_patientVO.getPatCrNo()::str:: " + str);
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			EpisodeVO episodeVO[] = new EpisodeVO[]
			{};
			EpisodeCloseVO epCloseVO = new EpisodeCloseVO();

			System.out.println("Begining transaction");
			tx.begin();
			EpisodeCloseDAO episodeCloseDao = new EpisodeCloseDAO(tx);
			EpisodeReferDAO episodereferDAO = new EpisodeReferDAO(tx);
			EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			System.out.println("creating object of EpisodeCloseDAO");

			System.out.println("populate the Epidose closeVO");
			episodeVO = searchPatientEpiosdeByCrNo(patientVO, _userVO, RegistrationConfig.EPISODE_VISIT_TYPE_EMG,
					RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);

			for (int i = 0; i < episodeVO.length; i++)
			{
				episodeVO[i].setEpisodeActionCode(_episodeActDtlVO.getEpisodeActionCode());
				episodeVO[i].setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_FALSE);
				episodeVO[i].setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
				System.out.println("episodeVO[i]" + episodeVO[i].getPatCrNo());
				System.out.println("episodeVO[i]" + episodeVO[i].getEpisodeDate());
				HelperMethods.populate(epCloseVO, episodeVO[i]);

				// ---------------->>>will effect three table
				episodeDao.updateEpisodeDtlForCMO(episodeVO[i], _userVO);
				episodeCloseDao.create(epCloseVO, _userVO);
				HelperMethods.populate(_episodeActDtlVO, episodeVO[i]);
				episodeActionDtlDAO.create(_episodeActDtlVO, _userVO);
				HelperMethods.populate(_episodeReferVO, episodeVO[i]);
				_episodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_EXTERNAL);
				/*
				 * epReferVO.setPatRefGnctdHospitalCode(_episodeActDtlVO.getPatRefGnctdHospitalCode());
				 * epReferVO.setPatRefHospitalName(_episodeActDtlVO.getPatRefHospitalName());
				 
				episodereferDAO.create(_episodeReferVO, _userVO);
			}
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	public void savePatientAttendedDisposed(EpisodeDiagnosisVO[] _episodeDiagVO, EpisodeActionDtlVO _episodeActDtlVO, UserVO _userVO)
	{
		System.out.println("inside savePatientAttendedDisposed() of PatientBO");
		// String str=_patientVO.getPatCrNo();

		PatientVO patientVO = new PatientVO();
		patientVO.setPatCrNo(_episodeActDtlVO.getPatCrNo());
		System.out.println("_patientVO.getPatCrNo()::str:: " + patientVO.getPatCrNo());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			EpisodeVO episodeVO[] = new EpisodeVO[]
			{};
			System.out.println("Begining transaction");
			tx.begin();
			EpisodeCloseVO epCloseVO = new EpisodeCloseVO();
			EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
			EpisodeDiagnosisDAO episodeDiagnosisDAO = new EpisodeDiagnosisDAO(tx);
			EpisodeCloseDAO episodeCloseDao = new EpisodeCloseDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			System.out.println("creating object of EpisodeCloseDAO");

			System.out.println("populate the Epidose closeVO");
			episodeVO = searchPatientEpiosdeByCrNo(patientVO, _userVO, RegistrationConfig.EPISODE_VISIT_TYPE_EMG,
					RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
			if (episodeVO.length == 1 && _episodeDiagVO.length > 0)
			{
				episodeVO[0].setEpisodeActionCode(_episodeActDtlVO.getEpisodeActionCode());
				episodeVO[0].setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_FALSE);
				episodeVO[0].setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
				System.out.println("episodeVO[i]" + episodeVO[0].getPatCrNo());
				System.out.println("episodeVO[i]" + episodeVO[0].getEpisodeDate());

				// ---------------->>>will effect four tables
				episodeDao.updateEpisodeDtlForCMO(episodeVO[0], _userVO);
				HelperMethods.populate(_episodeActDtlVO, episodeVO[0]);
				if (_episodeDiagVO.length > 0) _episodeActDtlVO.setDiagonisticTypeCode(RegistrationConfig.DIAGNOSIS_PROVISIONAL_CODE);
				episodeActionDtlDAO.create(_episodeActDtlVO, _userVO);
				HelperMethods.populate(epCloseVO, episodeVO[0]);
				episodeCloseDao.create(epCloseVO, _userVO);
				for (int k = 0; k < _episodeDiagVO.length; k++)
				{
					HelperMethods.populate(_episodeDiagVO[k], episodeVO[0]);
					_episodeDiagVO[k].setDiagnosticTypeCode(RegistrationConfig.DIAGNOSIS_PROVISIONAL_CODE);
					episodeDiagnosisDAO.create(_episodeDiagVO[k], _userVO);
				}
			}
			else
			{
				System.out.println("canot be an emergency case");
			}

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void savePatientAttendedObserved(EpisodeDiagnosisVO[] _episodeDiagVO, EpisodeActionDtlVO _episodeActDtlVO, UserVO _userVO)
	{
		System.out.println("inside savePatientAttendedObserved() of PatientBO");
		PatientVO patientVO = new PatientVO();
		patientVO.setPatCrNo(_episodeActDtlVO.getPatCrNo());
		System.out.println("_patientVO.getPatCrNo()::str:: " + patientVO.getPatCrNo());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			EpisodeVO episodeVO[] = new EpisodeVO[]
			{};
			System.out.println("Begining transaction");
			tx.begin();
			EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
			EpisodeDiagnosisDAO episodeDiagnosisDAO = new EpisodeDiagnosisDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			System.out.println("creating object of EpisodeCloseDAO");

			System.out.println("populate the Epidose closeVO");
			episodeVO = searchPatientEpiosdeByCrNo(patientVO, _userVO, RegistrationConfig.EPISODE_VISIT_TYPE_EMG,
					RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
			if (episodeVO.length == 1 && _episodeDiagVO.length > 0)
			{
				episodeVO[0].setEpisodeActionCode(_episodeActDtlVO.getEpisodeActionCode());
				// episodeVO[0].setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_FALSE);
				episodeVO[0].setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
				System.out.println("episodeVO[i]" + episodeVO[0].getPatCrNo());
				System.out.println("episodeVO[i]" + episodeVO[0].getEpisodeDate());

				// ---------------->>>will effect four tables
				episodeDao.updateEpisodeDtlForCMO(episodeVO[0], _userVO);
				HelperMethods.populate(_episodeActDtlVO, episodeVO[0]);
				if (_episodeDiagVO.length > 0) _episodeActDtlVO.setDiagonisticTypeCode(RegistrationConfig.DIAGNOSIS_PROVISIONAL_CODE);
				episodeActionDtlDAO.create(_episodeActDtlVO, _userVO);
				for (int k = 0; k < _episodeDiagVO.length; k++)
				{
					HelperMethods.populate(_episodeDiagVO[k], episodeVO[0]);
					_episodeDiagVO[k].setDiagnosticTypeCode(RegistrationConfig.DIAGNOSIS_PROVISIONAL_CODE);
					episodeDiagnosisDAO.create(_episodeDiagVO[k], _userVO);
				}
			}
			else
			{
				System.out.println("canot be an emergency case");
			}

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void savePatientAttendedRefInCasuality(EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO)
	{
		System.out.println("inside savePatientAttendedObserved() of PatientBO");
		// String str=_patientVO.getPatCrNo();
		PatientVO patientVO = new PatientVO();
		patientVO.setPatCrNo(_episodedtlActionDtlVO.getPatCrNo());
		String deptCode = _episodedtlActionDtlVO.getDepartmentCode();
		System.out.println("_patientVO.getPatCrNo()::str:: " + patientVO.getPatCrNo());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			EpisodeVO episodeVO[] = new EpisodeVO[]
			{};

			System.out.println("Begining transaction");
			tx.begin();
			EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);

			System.out.println("populate the Epidose closeVO");
			episodeVO = searchPatientEpiosdeByCrNo(patientVO, _userVO, RegistrationConfig.EPISODE_VISIT_TYPE_EMG,
					RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
			if (episodeVO.length == 1)
			{
				episodeVO[0].setEpisodeActionCode(_episodedtlActionDtlVO.getEpisodeActionCode());
				episodeVO[0].setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
				System.out.println("episodeVO[i]" + episodeVO[0].getPatCrNo());
				System.out.println("episodeVO[i]" + episodeVO[0].getEpisodeDate());

				// ---------------->>>will effect four tables
				episodeDao.updateEpisodeDtlForCMO(episodeVO[0], _userVO);
				HelperMethods.populate(_episodedtlActionDtlVO, episodeVO[0]);
				_episodedtlActionDtlVO.setDepartmentCode(deptCode);
				episodeActionDtlDAO.create(_episodedtlActionDtlVO, _userVO);
			}
			else
			{
				System.out.println("cannot be an emergency case");
			}

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	/************************************************************************************************************************
	 * Detrmine Episode is open or close. If open return episodeVO If close return map containing episode vo and vo's related
	 * to the action taken on that last closed episode in emergency
	 * 
	 * 
	 * @param _patVO
	 * @param _userVO
	 * @return
	 */

	/*
	 * public Map CMOAction(PatientVO _patVO, UserVO _userVO){
	 * 
	 * Map cmoActionMap=new HashMap(); System.out.println("inside isEmergency");
	 * System.out.println("_patientVO.getPatCrNo()::str:: "+_patVO.getPatCrNo()); JDBCTransactionContext tx=new
	 * JDBCTransactionContext(); EpisodeVO episodeVO[]=new EpisodeVO[]{}; //EpisodeActionDao
	 * 
	 * 
	 * try{ System.out.println("Begining transaction"); tx.begin(); //EpisodeActionDtlDAO episodeActionDtlDAO=new
	 * EpisodeActionDtlDAO(tx); EpisodeDAO episodeDao = new EpisodeDAO(tx);
	 * 
	 * synchronized(episodeDao.getLock()){ System.out.println("populate the Epidose closeVO"); episodeVO=
	 * searchPatientEpiosdeByCrNo(_patVO,_userVO,RegistrationConfig.EPISODE_VISIT_TYPE_EMG); String
	 * actionCode=episodeVO[0].getEpisodeActionCode();
	 * 
	 * //retrieve data from episode action dtl. //retrieve data from episode close dtl. //if patient is ref out or ward
	 * retreive data from referr dtl. //if patient died retrieve data from death dtl. //if patient is diagnosed retrieve it
	 * from diagnois dtl. //// // code to rettrive data from episode action & close dtl //
	 * 
	 * /////
	 * 
	 * EpisodeActionDtlDAO epiosodeActionDtlDAO=new EpisodeActionDtlDAO(tx); EpisodeActionDtlVO
	 * episodeActionVO=epiosodeActionDtlDAO.getActionDtl(episodeVO[0],_userVO);
	 * 
	 * 
	 * if(actionCode.equalsIgnoreCase(RegistrationConfig.EPISODE_ACTION_DEATH)){ EpisodeDeathDAO epiosodeDeathDAO=new
	 * EpisodeDeathDAO(tx); EpisodeDeathVO deathVO=epiosodeDeathDAO.getDeathDtl(episodeVO[0],_userVO); }
	 * 
	 * if((actionCode.equalsIgnoreCase(RegistrationConfig.EPISODE_ACTION_CODE_ATTENDED_DISPOSED)||actionCode.equalsIgnoreCase(RegistrationConfig.EPISODE_ACTION_CODE_ATTENDED_DISPOSED))) {
	 * EpisodeDiagnosisDAO episodeDiagnosisDAO=new EpisodeDiagnosisDAO(tx); EpisodeDiagnosisVO
	 * episodeDiagnosisVO=episodeDiagnosisDAO.getDiagnosisDtl(episodeVO[0],_userVO); } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * return episodeVO[0]; } catch(Exception e){ } }
	 

	public EpisodeVO isEmergency(PatientVO _patVO, UserVO _userVO)
	{
		System.out.println("inside isEmergency");
		// String str=_patientVO.getPatCrNo();
		System.out.println("_patientVO.getPatCrNo()::str:: " + _patVO.getPatCrNo());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO episodeVO[] = new EpisodeVO[]
		{};
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			// EpisodeActionDtlDAO episodeActionDtlDAO=new EpisodeActionDtlDAO(tx);

			System.out.println("populate the Epidose closeVO");
			episodeVO = searchPatientEpiosdeByCrNo(_patVO, _userVO, RegistrationConfig.EPISODE_VISIT_TYPE_EMG,
					RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
			return episodeVO[0];

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			episodeVO = new EpisodeVO[1];
			episodeVO[0] = null;
			System.out.println("inside catch");
			throw new HisNotAnEmergencyCaseException();
		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	public void savePatientRefInWard(EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO)
	{
		System.out.println("inside savePatientRefInWard() of PatientBO");
		// String str=_patientVO.getPatCrNo();
		PatientVO patientVO = new PatientVO();
		String deptCode = _episodedtlActionDtlVO.getDepartmentCode();
		String wardCode = _episodedtlActionDtlVO.getWardCode();
		patientVO.setPatCrNo(_episodedtlActionDtlVO.getPatCrNo());
		System.out.println("_patientVO.getPatCrNo()::str:: " + patientVO.getPatCrNo());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			EpisodeVO episodeVO[] = new EpisodeVO[]
			{};
			EpisodeReferVO episodereferVO = new EpisodeReferVO();
			System.out.println("Begining transaction");
			tx.begin();
			EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			EpisodeReferDAO episoderefDAO = new EpisodeReferDAO(tx);
			System.out.println("populate the Epidose closeVO");
			episodeVO = searchPatientEpiosdeByCrNo(patientVO, _userVO, RegistrationConfig.EPISODE_VISIT_TYPE_EMG,
					RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
			if (episodeVO.length == 1)
			{
				episodeVO[0].setEpisodeActionCode(_episodedtlActionDtlVO.getEpisodeActionCode());
				episodeVO[0].setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
				System.out.println("episodeVO[i]" + episodeVO[0].getPatCrNo());
				System.out.println("episodeVO[i]" + episodeVO[0].getEpisodeDate());

				// ---------------->>>will effect four tables
				episodeDao.updateEpisodeDtlForCMO(episodeVO[0], _userVO);
				HelperMethods.populate(_episodedtlActionDtlVO, episodeVO[0]);
				_episodedtlActionDtlVO.setDepartmentCode(deptCode);
				_episodedtlActionDtlVO.setWardCode(wardCode);
				episodeActionDtlDAO.create(_episodedtlActionDtlVO, _userVO);
				HelperMethods.populate(episodereferVO, episodeVO[0]);
				episodereferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);
				episodereferVO.setDepartmentCode(deptCode);
				episodereferVO.setWardCode(wardCode);
				episoderefDAO.create(episodereferVO, _userVO);
			}
			else
			{
				System.out.println("cannot be an emergency case");
			}

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void saveOpdVisitConfirm(EpisodeVO _episodeVO, EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO)
	{
		System.out.println("inside saveOpdVisitConfirm() of PatientBO");
		// String str=_patientVO.getPatCrNo();
		PatientVO patientVO = new PatientVO();
		patientVO.setPatCrNo(_episodedtlActionDtlVO.getPatCrNo());
		System.out.println("_patientVO.getPatCrNo()::str:: " + patientVO.getPatCrNo());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{

			System.out.println("Begining transaction");
			tx.begin();
			EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);

			System.out.println("populate the Epidose closeVO");
			_episodeVO.setEpisodeActionCode(_episodedtlActionDtlVO.getEpisodeActionCode());
			_episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
			System.out.println("episodeVO[i]" + _episodeVO.getPatCrNo());
			System.out.println("episodeVO[i]" + _episodeVO.getEpisodeDate());
			// ---------------->>>will effect four tables
			episodeDao.updateEpisodeDtl(_episodeVO, _userVO);
			HelperMethods.populate(_episodedtlActionDtlVO, _episodeVO);

			episodeActionDtlDAO.create(_episodedtlActionDtlVO, _userVO);

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public void saveOpdEpisodeClose(EpisodeVO _episodeVO, EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO)
	{
		PatientVO patientVO = new PatientVO();
		System.out.println("inside saveOpdEpisodeClose() of PatientBO");
		// String str=_patientVO.getPatCrNo();

		String deptCode = _episodedtlActionDtlVO.getDepartmentCode();
		// String wardCode=_episodedtlActionDtlVO.getWardCode();
		patientVO.setPatCrNo(_episodedtlActionDtlVO.getPatCrNo());
		System.out.println("_patientVO.getPatCrNo()::str:: " + patientVO.getPatCrNo());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{

			EpisodeCloseVO epCloseVO = new EpisodeCloseVO();

			System.out.println("Begining transaction");
			tx.begin();
			EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			EpisodeCloseDAO episodeCloseDao = new EpisodeCloseDAO(tx);

			System.out.println("populate the Epidose closeVO");

			_episodeVO.setEpisodeActionCode(_episodedtlActionDtlVO.getEpisodeActionCode());
			_episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
			_episodeVO.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_FALSE);
			System.out.println("_episodeVO[i]" + _episodeVO.getPatCrNo());
			System.out.println("_episodeVO[i]" + _episodeVO.getEpisodeDate());

			// ---------------->>>will effect four tables
			episodeDao.updateEpisodeDtl(_episodeVO, _userVO);
			HelperMethods.populate(_episodedtlActionDtlVO, _episodeVO);
			_episodedtlActionDtlVO.setDepartmentCode(deptCode);
			episodeActionDtlDAO.create(_episodedtlActionDtlVO, _userVO);
			HelperMethods.populate(epCloseVO, _episodeVO);
			episodeCloseDao.create(epCloseVO, _userVO);

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	public EpisodeVO[] isOpd(PatientVO _patVO, UserVO _userVO)
	{
		System.out.println("inside isOpd");
		// String str=_patientVO.getPatCrNo();
		System.out.println("_patientVO.getPatCrNo()::str:: " + _patVO.getPatCrNo());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO episodeVO[] = new EpisodeVO[]
		{};
		try
		{

			System.out.println("Begining transaction");
			tx.begin();

			System.out.println("populate the Epidose closeVO");
			episodeVO = searchPatientEpiosdeByCrNo(_patVO, _userVO, RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL,
					RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
			if (episodeVO.length == 0) throw new HisNotAnOPDcaseException();

		}

		catch (HisNotAnOPDcaseException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisNotAnOPDcaseException();
		}

		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisNotAnOPDcaseException();
		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeVO;
	}

	
	 * Detrmine if patient is eligible for mlc or not. *
	 * 
	 * @param _patVO
	 * @param _userVO
	 * @return
	 
	public EpisodeVO isMlcEligible(PatientVO _patVO, UserVO _userVO)
	{
		//System.out.println("inside isMlcEligible");
		// String str=_patientVO.getPatCrNo();
		//System.out.println("_patientVO.getPatCrNo()::str:: " + _patVO.getPatCrNo());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO episodeVO = null;
		try
		{
			//MlcDAO objMlcDao = new MlcDAO(tx);
			//System.out.println("Begining transaction");
			tx.begin();
			// EpisodeActionDtlDAO episodeActionDtlDAO=new EpisodeActionDtlDAO(tx);

			try
			{
				episodeVO = getLastEpisodeInEmergency(_patVO, _userVO);
			}
			catch (Exception e)
			{
				throw new HisNotEligibleForMLCException(e.getMessage());
			}

			MlcVO[] mlcVO = objMlcDao.retrieveByCrNo(_patVO, _userVO);
			if (mlcVO.length > 0)
			{
				for (int i = 0; i < mlcVO.length; i++)
				{
					if (mlcVO[i].getPatCrNo().equalsIgnoreCase(_patVO.getPatCrNo())
							&& mlcVO[i].getEpisodeCode().equalsIgnoreCase(episodeVO.getEpisodeCode()))
					{
						System.out.println("Mlc already exist");
						throw new HisMlcAlreadyExistException();
					}
				}
			}
			else return episodeVO;
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
		}
		catch (HisNotEligibleForMLCException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisNotEligibleForMLCException(e.getMessage());
		}
		catch (HisMlcAlreadyExistException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisMlcAlreadyExistException(e.getMessage());
		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeVO;
	}

	// Checking if MLC is already added
	/*public EpisodeVO isCsultyMlcEligible(PatientVO _patVO, String _episodeCode, UserVO _userVO)
	{
		EpisodeVO episodeVO = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			MlcDAO objMlcDao = new MlcDAO(tx);
			tx.begin();

			try
			{
				episodeVO = getLastEpisodeInEmergency(_patVO, _userVO);
			}
			catch (Exception e)
			{
				throw new HisNotEligibleForMLCException(e.getMessage());
			}

			if (!episodeVO.getEpisodeCode().equals(_episodeCode)) throw new HisNotEligibleForMLCException();

			MlcVO[] mlcVO = objMlcDao.retrieveByCrNo(_patVO, _userVO);
			if (mlcVO.length > 0)
			{
				for (int i = 0; i < mlcVO.length; i++)
				{
					if (mlcVO[i].getPatCrNo().equalsIgnoreCase(_patVO.getPatCrNo()) && mlcVO[i].getEpisodeCode().equalsIgnoreCase(_episodeCode))
					{
						throw new HisMlcAlreadyExistException();
					}
				}
			}
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
		}
		catch (HisNotEligibleForMLCException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisNotEligibleForMLCException(e.getMessage());
		}
		catch (HisMlcAlreadyExistException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisMlcAlreadyExistException(e.getMessage());
		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeVO;
	}
/*
	public MlcVO getMlcDtlBasedOnMlcNo(MlcVO _mlcVo, UserVO _userVO)
	{
		System.out.println("inside getMlcDtlBasedOnMlcNo() of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			MlcDAO mlcDao = new MlcDAO(tx);
			System.out.println("creating object of MlcDAO");
			System.out.println("populate the  MlcVO");
			_mlcVo = mlcDao.retrieveByMlcNo(_mlcVo, _userVO);
			System.out.println("after populating MlcVO");
			if (_mlcVo.getIsImageUploaded() != null && _mlcVo.getIsImageUploaded().equalsIgnoreCase("1"))
			{
				System.out.println("image found :::::::::::");
				_mlcVo.setImageFile(HelperMethods.getByteArrayOfImage(Config.MLC_DOC_IMAGE_FILE_STORAGE_PATH + "\\" + _mlcVo.getPatCrNo() + "$"
						+ _mlcVo.getMlcNo() + "$" + _mlcVo.getEpisodeCode() + "$" + _mlcVo.getSerialNo() + ".jpg"));
				_mlcVo.setImageFileName(Config.PATIENT_IMAGE_FILE_STORAGE_PATH + "\\" + _mlcVo.getPatCrNo() + "$" + _mlcVo.getMlcNo() + "$"
						+ _mlcVo.getEpisodeCode() + "$" + _mlcVo.getSerialNo() + ".jpg");
			}

		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _mlcVo;
	}

	public MlcVO getMlcDtlBasedOnCrNo(MlcVO _mlcVo, UserVO _uservo)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			MlcDAO mlcDao = new MlcDAO(tx);
			_mlcVo = mlcDao.getMlcDtlBasedOnCrNo(_mlcVo, _uservo);
			if (_mlcVo.getIsImageUploaded() != null && _mlcVo.getIsImageUploaded().equalsIgnoreCase("1"))
			{
				_mlcVo.setImageFile(HelperMethods.getByteArrayOfImage(Config.MLC_DOC_IMAGE_FILE_STORAGE_PATH + "\\" + _mlcVo.getPatCrNo() + "$"
						+ _mlcVo.getMlcNo() + "$" + _mlcVo.getEpisodeCode() + "$" + _mlcVo.getSerialNo() + ".jpg"));
				_mlcVo.setImageFileName(Config.PATIENT_IMAGE_FILE_STORAGE_PATH + "\\" + _mlcVo.getPatCrNo() + "$" + _mlcVo.getMlcNo() + "$"
						+ _mlcVo.getEpisodeCode() + "$" + _mlcVo.getSerialNo() + ".jpg");
			}

		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _mlcVo;
	}*/

	/**
	 * Retrieves patient details. Reserved for future use.
	 * 
	 * @param _patientVO Provides CR No to be searched.
	 * @param _userVO Provides User details.
	 * @return PatientVO with values stored in DB.
	 */
	/*public PatientVO getPatientDtl(PatientVO _patientVO, UserVO _userVO)
	{
		return (new PatientVO());
	}*/

	/**
	 * Retrieves patient details on the basis of Patient Name. Reserved for future use.
	 * 
	 * @param _patientName Provides name to be searched.
	 * @param _userVO Provides User details.
	 * @return Array of PatientVO with values stored in DB.
	 */
	/*public PatientVO[] searchPatientByName(String _patientName, UserVO _userVO)
	{
		// System.out.println("_crNo::"+ _crNo);
		PatientVO[] _patientVO = new PatientVO[]
		{};
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			AddressVO[] _addressVO = new AddressVO[]
			{};
			try
			{
				_addressVO = addDao.retrieveByName(_patientName, _userVO);
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println("Patient is unknown");
			}

			_patientVO = patientDao.retrieveByName(_patientName, _addressVO, _userVO);
			
			

			for (int i = 0; i < _patientVO.length; i++)
			{
				if (_patientVO[i].getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
				{
					String fname = "(Unknown)" + _patientVO[i].getPatFirstName();
					_patientVO[i].setPatFirstName(fname);
				}
				if (_patientVO[i].getPatAge() != null && _patientVO[i].getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE))
				{
					String patAgeWithUnit = _patientVO[i].getPatAge();
					int startidx = patAgeWithUnit.lastIndexOf(" ");
					String ageunit = patAgeWithUnit.substring(startidx + 1);
					patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
					_patientVO[i].setPatAge(patAgeWithUnit);

					if (ageunit.equalsIgnoreCase("yr")) _patientVO[i].setPatAgeUnit("Y");
					if (ageunit.equalsIgnoreCase("mth")) _patientVO[i].setPatAgeUnit("M");
					if (ageunit.equalsIgnoreCase("d")) _patientVO[i].setPatAgeUnit("D");
				}
			}
			
			 * System.out.println("Patient dob::"+_patientVO.getPatDOB()); System.out.println("Patient
			 * isurban::"+_patientVO.getPatIsUrban()); System.out.println("after populate"); System.out.println("Patient
			 * first name::"+_patientVO.getPatFirstName());
			 * System.out.println("_patientVO.getPatAddCity()"+_patientVO.getPatAddCity());
			 
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return (_patientVO);
	}

	/**
	 * Retrieves patient details on the basis of Id Noif the patient is an employee of the hospital. Reserved for future use.
	 * 
	 * @param _idNo Provides Id No to be searched.
	 * @param _userVO Provides User details. return PatientVO with values stored in DB.
	 */
	/*public PatientVO searchPatientByIdNo(String _idNo, UserVO _userVO)
	{
		return (new PatientVO());
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see registration.bo.PatientBOi#getEmgOpenEpisode(his.global.vo.PatientVO, his.global.vo.UserVO)
	 * 
	 
	public EpisodeVO getEmgOpenEpisode(PatientVO _patVO, UserVO _userVO)
	{
		System.out.println("inside isEmgOldVisitEligible");
		// String str=_patientVO.getPatCrNo();
		System.out.println("_patientVO.getPatCrNo()::str:: " + _patVO.getPatCrNo());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO episodeVO[];
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			episodeVO = searchPatientEpiosdeByCrNo(_patVO, _userVO, RegistrationConfig.EPISODE_VISIT_TYPE_EMG);
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println(e.getMessage());
			tx.rollback();
			episodeVO = new EpisodeVO[1];
			episodeVO[0] = null;
			System.out.println("inside catch");
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeVO[0];

	}

	/**
	 * Retrieves patient details on the basis of CR No and visit type from the Patient Dtl Table. Provides age of the patient
	 * according to the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 * 
	 * @param _patientVO Provides CR No to be searched.
	 * @param _userVO Provides User details.
	 * @param _visitType Specifies visit type of the patient.
	 * @return PatientVO with values stored in DB.
	 * Modified By Pragya at 05-Aug-2011
	 
	public PatientVO searchPatientByCrNo(PatientVO _patientVO, UserVO _userVO, String _visitType)
	{
		try
		{
			searchPatientByCrNo(_patientVO, _userVO);
			boolean value = false;
			value = PatientBOSupport.checkPatientStatus(_patientVO, _userVO, _visitType);
			if (value == true) System.out.println("Patient eligible for visit type::: " + _visitType);
		}
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("inside HisRecordNotFoundException of searchPatientByCrNo");
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			System.out.println("inside HisNotAnOPDcaseException of searchPatientByCrNo");
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			System.out.println("inside HisNotAnIPDcaseException of searchPatientByCrNo");
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			System.out.println("inside HisDeadPatientException of searchPatientByCrNo");
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		return _patientVO;
	}

	/**
	 * Converts an unknown patient to a known patient. Modifies deatils of the patient. Can be done only by the authorized
	 * personnel. Updates the record in Patient dtl and Address dtl tables. Also creates an entry in Unknown Change Detail
	 * table that stores the information regarding when the patient became known.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return number of records updated.
	 
	public boolean unknownToKnownConversion(PatientVO _patientVO, PatientVO patientVOOldData, VerificationDocumentVO _VerificationDocumentVO,
			UserVO _userVO)
	{
		int x = 0, y = 0, z = 0, m = 0;
		//String str = _patientVO.getPatCrNo();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String verficationSNo = "";
		HisDAO hisDAOObj = null;
		MlcVO[] _mlcVO = null;
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			DailyPatientDAO dailyPatDAO = new DailyPatientDAO(tx);
			PatientAuditDAO patientAuditDao = new PatientAuditDAO(tx);
			VerificationDocumentUsedDAO verificationDocUsedDao = new VerificationDocumentUsedDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			UnknownChangeDAO unknownChangeDAO = new UnknownChangeDAO(tx);
			MlcDAO mlcDao = new MlcDAO(tx);
		//	MlcVO _mlcVO = new MlcVO();

			UnknownChangeVO _unknownChangeVO = new UnknownChangeVO();

			hisDAOObj = new HisDAO("Registration", "PatientBO");
			// System.out.println("_addressVO.getPatCrNo():: "+_addressVO.getPatCrNo());

			/*
			 * if(_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
			 * _patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
			

			x = patientDao.update(hisDAOObj, _patientVO, _userVO);

			DailyPatientVO _dailyPatientVO = new DailyPatientVO();
			HelperMethods.populate(_dailyPatientVO, _patientVO);
			dailyPatDAO.updateUnknownConversion(hisDAOObj, _dailyPatientVO, _userVO);

			HelperMethods.populate(_unknownChangeVO, _patientVO);
			_patientVO.getPatAddress().setPatCrNo(_patientVO.getPatCrNo());
			_patientVO.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
			_patientVO.getPatAddress().setSeatId(_patientVO.getSeatId());
			verficationSNo = verificationDocUsedDao.selectNextSerialNoForPUK(_VerificationDocumentVO, _userVO);
			_unknownChangeVO.setVerificationDocumentId(verficationSNo);
			_VerificationDocumentVO.setIsValid(Config.IS_VALID_ACTIVE);
			verificationDocUsedDao.create(hisDAOObj, _VerificationDocumentVO, _userVO);
			if (x >= 1)
			{

				addDao.createNewAddress(hisDAOObj, _patientVO.getPatAddress(), _userVO);
				y++;

				// /////Audit Of Patient Detail
				patientAuditDao.create(hisDAOObj, patientVOOldData, _userVO);
				// ////////////////////////////////

			}
			if (y >= 1)
			{

				unknownChangeDAO.create(hisDAOObj, _unknownChangeVO, _userVO);
				z++;

			}
			if (z >= 1)
			{
				// if(_patientVO.getIsMLC().equals(RegistrationConfig.IS_MLC_TRUE)){
				try
				{
					_mlcVO=getMlcDtlArrayBasedOnCrNo(_patientVO.getPatCrNo(), _userVO);
					for(int i=0;i<_mlcVO.length;i++){
					mlcDao.updateprevRecord(_mlcVO[i], _userVO);
					HelperMethods.populate(_mlcVO[i], _unknownChangeVO);
					_mlcVO[i].setPatMlcNo(_mlcVO[i].getMlcNo());
					mlcDao.create(hisDAOObj,_mlcVO[i], _userVO);
					m++;
				}
				}
				catch (HisRecordNotFoundException e)
				{
				}

				// }
			}
			
			PatientAdhaarDtlDAO patAdhaarDtlDao = new PatientAdhaarDtlDAO(tx);
			if((_patientVO.getPatNationalId()!=null)&&!(_patientVO.getPatNationalId().equals("")))
				patAdhaarDtlDao.create(_patientVO, _userVO);
			
			synchronized (hisDAOObj)
			{
				hisDAOObj.fire();
			}
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		System.out.println("known conversion successful.....z:: " + z);
		if (_patientVO.getIsMLC().equals(RegistrationConfig.IS_MLC_TRUE))
		{
			if (m >= 1) return true;
			else return false;
		}
		else
		{
			if (z >= 1) return true;
			else return false;
		}
	}

	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table. Used when patient's refer details are to be modified.
	 * 
	 * @param _patientVO Provides CR No of the patient for which episode details are to be searched.
	 * @param _userVO Provides User details.
	 * @return EpisodeVO with values stored in DB.
	 
	public EpisodeReferVO searchPatientRefEpisodeByEpisodeNo(PatientVO _patientVO, EpisodeVO _episodeVO, UserVO _userVO)
	{
		System.out.println("inside searchPatientRefEpisodeByEpisodeNo() of PatientBO");
		System.out.println("Patient CR No::" + _episodeVO.getPatCrNo());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeReferVO _episodeReferVO = new EpisodeReferVO();
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			System.out.println("creating object of EpisodeDAO");
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			EpisodeReferDAO episodeReferDAO = new EpisodeReferDAO(tx);
			System.out.println("creating object of AddressDAO");

			System.out.println("episodeDao.getLock()");
			String str = _episodeVO.getPatCrNo();

			System.out.println("str::_patientVO.getPatCrNo():: " + str);
			_episodeVO = episodeDao.retrieveByEpisodeNo(_episodeVO, _userVO);
			_episodeReferVO.setPatCrNo(_episodeVO.getPatCrNo());
			_episodeReferVO.setEpisodeCode(_episodeVO.getEpisodeCode());
			System.out.println("_episodeVO.getEpisodeCode()::" + _episodeVO.getEpisodeCode());
			System.out.println("_episodeVO.getEpisodeVisitNo()::" + _episodeVO.getEpisodeVisitNo());
			System.out.println("_episodeVO.getEpisodeVisitType()::" + _episodeVO.getEpisodeVisitType());
			if (_episodeVO.getEpisodeReferAccept().equals(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE)) throw new HisPatientNotReferredException(
					"Patient not referred");
			else
			{
				_episodeReferVO = episodeReferDAO.retrieveRefEpisodeDtlByEpisodeNo(_episodeReferVO, _userVO);
				System.out.println("_episodeReferVO.getPatRefDoctor()::" + _episodeReferVO.getPatRefDoctor());
				System.out.println("_episodeReferVO.getPatRefGnctdHospitalCode()::" + _episodeReferVO.getPatRefGnctdHospitalCode());
			}
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside HisRecordNotFoundException of ");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (HisPatientNotReferredException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisPatientNotReferredException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _episodeReferVO;
	}

	/**
	 * Modifies refer details of a patient at MRD. Can be done only by the authorized personnel. Updates the record in
	 * Patient dtl, Episode dtl and Episode Refer dtl tables.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _episodeVO Provides Episode details of the patient.
	 * @param _episodeReferVO Provides Episode Refer details of the patient.
	 * @param _userVO Provides User details.
	 * @return <code>true</code> if records are updated otherwise <code>false</code>.
	 
	public boolean referDtlModificationMRD(PatientVO _patientVO, EpisodeReferVO _episodeReferVO, UserVO _userVO)
	{
		int x = 0, y = 0;
		System.out.println("inside referDtlModificationMRD() of PatientBO");
		String str = _patientVO.getPatCrNo();
		System.out.println("_patientVO.getPatCrNo()::str:: " + str);
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			EpisodeReferDAO episodeReferDAO = new EpisodeReferDAO(tx);
			System.out.println("creating object of PatientDAO");

			System.out.println("_patientVO.getPatCrNo()::str " + str);
			System.out.println("update patientVO");
			x = patientDao.updateRefDtlAtMRD(_patientVO, _userVO);
			// _episodeReferVO.setEpisodeCode(_episodeVO.getEpisodeCode());
			if (x >= 1)
			{
				System.out.println("update _episodeReferVO");
				y = episodeReferDAO.updateRefEpisodeDtlAtMRD(_episodeReferVO, _userVO);
				System.out.println("after update");
			}
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		System.out.println("known conversion successful.....y:: " + y);
		if (y >= 1) return true;
		else return false;
	}

	/**
	 * Stamps the visit of a patient when he/she visits a department in which he/she is referred from some other department.
	 * Saves data in Daily Patient dtl, Episode dtl and Episode Refer dtl tables.
	 * 
	 * @param _arrEpisodeVO[] Provides the departments in which patient has been registered.
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return Array of EpisodeVO with values stored in DB.
	 
	public EpisodeVO[] referredDeptVisitStamp(EpisodeVO[] _arrEpisodeVO, EpisodeReferVO _episodeReferVO, PatientVO _patientVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			// complete the transaction
			tx.begin();
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			EpisodeReferDAO episodeReferDAO = new EpisodeReferDAO(tx);
			//EssentialDAO essentialDAO = new EssentialDAO(tx);
			EpisodeVO[] oldDeptArrVO =
			{};
			EpisodeVO[] newDeptArrVO =
			{};
			synchronized (episodeDAO.getLock())
			{
				EpisodeVO[] tmpOldDeptArrEpisodeVO = new EpisodeVO[_arrEpisodeVO.length];
				EpisodeVO[] tmpNewDeptArrEpisodeVO = new EpisodeVO[_arrEpisodeVO.length];
				int j = 0;
				int lenOld = 1;
				int lenNew = 1;
				oldDeptArrVO = new EpisodeVO[]
				{};
				newDeptArrVO = new EpisodeVO[]
				{};
				EpisodeVO[] openEpisodeArrVO = new EpisodeVO[]
				{};

				if (_episodeReferVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL)) for (int i = 0; i < _arrEpisodeVO.length; i++)
				{
					_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					_arrEpisodeVO[i].setPatSecondaryCatCode(_patientVO.getPatSecondaryCatCode());
					_arrEpisodeVO[i].setPatPrimaryCatCode(_patientVO.getPatPrimaryCatCode());
				}
				else if (_episodeReferVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL)) for (int i = 0; i < _arrEpisodeVO.length; i++)
				{
					_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL);
					_arrEpisodeVO[i].setPatSecondaryCatCode(_patientVO.getPatSecondaryCatCode());
					_arrEpisodeVO[i].setPatPrimaryCatCode(_patientVO.getPatPrimaryCatCode());
		
				}
				else for (int i = 0; i < _arrEpisodeVO.length; i++)
				{

					_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					_arrEpisodeVO[i].setPatSecondaryCatCode(_patientVO.getPatSecondaryCatCode());
					_arrEpisodeVO[i].setPatPrimaryCatCode(_patientVO.getPatPrimaryCatCode());

				}
				try
				{
					openEpisodeArrVO = episodeDAO.retrieveByCrNo(_patientVO.getPatCrNo(), _userVO);
				}
				catch (HisRecordNotFoundException e)
				{

				}
				if (openEpisodeArrVO.length > 0)
				{
					

					for (int i = 0; i < openEpisodeArrVO.length; i++)
					{
						for (int m = 0; m < _arrEpisodeVO.length; m++)
						{
							if (openEpisodeArrVO[i].getDepartmentCode().equals(_arrEpisodeVO[m].getDepartmentCode()))
							{
								_arrEpisodeVO[m].setEpisodeCode(openEpisodeArrVO[i].getEpisodeCode());
						
							}
							else System.out.println("not matched.... m=" + m + " i=" + i);

						
							// System.out.println("openEpisodeArrVO["+i+"].getEpisodeCode()"+openEpisodeArrVO[i].getEpisodeCode());
						}
					}

				}

				for (int i = 0; i < _arrEpisodeVO.length; i++)
				{
					
					List al = new ArrayList();
					try
					{
						al = episodeDAO.getOpenEpisodeDepartment(_arrEpisodeVO[i], _userVO);
					}
					catch (HisRecordNotFoundException e)
					{
					}

					
					
					 * if(al.contains(_episodeVO.getDepartmentCode())== true)
					 * PatientBOSupport.oldDeptVisitStamp(_arrEpisodeVO, _patientVO, _userVO, tx); else
					 * PatientBOSupport.newDeptVisitStamp(_arrEpisodeVO, _patientVO, _userVO, tx);
					 

					// for(int i=0; i<_arrEpisodeVO.length; i++){
					// copying one array elements to another array skipping some indexes
					if (al.size() > 0 && al.contains(new Entry("", _arrEpisodeVO[i].getDepartmentCode())))
					{
						System.out.println("true[" + i + "]");
						tmpOldDeptArrEpisodeVO = new EpisodeVO[lenOld];
						for (int k = 0; k < oldDeptArrVO.length; k++)
						{
							tmpOldDeptArrEpisodeVO[k] = oldDeptArrVO[k];
							// System.out.println("arrVO"+arrVO[k].getEpisodeCode());
						}
						tmpOldDeptArrEpisodeVO[lenOld - 1] = _arrEpisodeVO[i];
						oldDeptArrVO = tmpOldDeptArrEpisodeVO;

						for (int k = 0; k < oldDeptArrVO.length; k++)
						{
							System.out.println("i= " + i + " k= " + k + "  olDeptArrVO...1" + oldDeptArrVO[k].getEpisodeCode());
						}
						lenOld++;
					}
					else
					{
						System.out.println("false[" + i + "]");
						tmpNewDeptArrEpisodeVO = new EpisodeVO[lenNew];
						for (int k = 0; k < newDeptArrVO.length; k++)
						{
							tmpNewDeptArrEpisodeVO[k] = newDeptArrVO[k];
							// System.out.println("arrVO"+arrVO[k].getEpisodeCode());
						}
						tmpNewDeptArrEpisodeVO[lenNew - 1] = _arrEpisodeVO[i];
						newDeptArrVO = tmpNewDeptArrEpisodeVO;

						System.out.println("newDeptArrVO.length...::" + newDeptArrVO.length);
						lenNew++;
					}

					for (int k = 0; k < oldDeptArrVO.length; k++)
					{
						System.out.println("arrVO" + oldDeptArrVO[k].getEpisodeCode());
					}
					System.out.println("j[" + i + "]" + j);

				}
				System.out.println("oldDeptArrVO.length::1" + oldDeptArrVO.length);
				System.out.println("newDeptArrVO.length::1" + newDeptArrVO.length);

				if (oldDeptArrVO.length > 0)
				{
					System.out.println("calling PatientBOSupport.oldDeptVisitStamp()");
					System.out.println("oldDeptArrVO.length..::.." + oldDeptArrVO.length);

					for (int k = 0; k < oldDeptArrVO.length; k++)
					{
						for (int i = 0; i < openEpisodeArrVO.length; i++)
						{
							if (oldDeptArrVO[k].getDepartmentCode().equals(openEpisodeArrVO[i].getDepartmentCode()))
							{
								oldDeptArrVO[k].setVisitedToday(openEpisodeArrVO[i].getVisitedToday());
								oldDeptArrVO[k].setDeptUnitIsOnDuty(openEpisodeArrVO[i].getDeptUnitIsOnDuty());
								oldDeptArrVO[k].setDeptUnitIsClosed(openEpisodeArrVO[i].getDeptUnitIsClosed());
							}
						}
					}

					for (int k = 0; k < oldDeptArrVO.length; k++)
					{
						if (oldDeptArrVO[k].getVisitedToday().equals(RegistrationConfig.DEPT_UNIT_VISITED_TODAY_TRUE)) throw new HisDeptUnitAlreadyVisited(
								"Department Unit is Already Visited");
						if (oldDeptArrVO[k].getDeptUnitIsOnDuty().equals(RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_FALSE)) throw new HisDeptUnitNotOnDuty(
								"Department Unit is Not on Duty");
						if (oldDeptArrVO[k].getDeptUnitIsClosed().equals(RegistrationConfig.DEPT_UNIT_IS_CLOSED_TRUE)) throw new HisDeptUnitIsClosed(
								"Department Unit is Closed");
					}

					for (int i = 0; i < oldDeptArrVO.length; i++)
						System.out.println("oldDeptArrVO[i].getDeptUnitIsClosed()..::..2" + oldDeptArrVO[i].getDeptUnitIsClosed());
					PatientBOSupport.oldDeptVisitStamp(oldDeptArrVO, _patientVO, _userVO, tx);
					System.out.println("oldDeptArrVO.length...:::..." + oldDeptArrVO.length);
					for (int i = 0; i < oldDeptArrVO.length; i++)
					{
						System.out.println("oldDeptArrVO[i].getEpisodeReferAccept()...:::" + oldDeptArrVO[i].getEpisodeReferAccept());
						HelperMethods.populate(_episodeReferVO, oldDeptArrVO[i]);
						
						 * if(_episodeReferVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						 * episodeDAO.episodeUpdateReferralAcceptanceDate(_episodeReferVO, _userVO);
						 
						episodeReferDAO.create(_episodeReferVO, _userVO);
					}

				}
				System.out.println("_tmpArrEpisodeVO.length::" + tmpOldDeptArrEpisodeVO.length);

				if (newDeptArrVO.length > 0)
				{
					System.out.println("calling PatientBOSupport.newDeptVisitStamp()");
					PatientBOSupport.newDeptVisitStamp(newDeptArrVO, _patientVO, _userVO, tx);
					System.out.println("newDeptArrVO.length...:::..." + newDeptArrVO.length);
					for (int i = 0; i < newDeptArrVO.length; i++)
					{
						System.out.println("newDeptArrVO[i].getEpisodeReferAccept()...:::" + newDeptArrVO[i].getEpisodeReferAccept());
						HelperMethods.populate(_episodeReferVO, newDeptArrVO[i]);
						
						 * if(_episodeReferVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						 * episodeDAO.episodeUpdateReferralAcceptanceDate(_episodeReferVO, _userVO);
						 
						episodeReferDAO.create(_episodeReferVO, _userVO);
					}
				}

				System.out.println("_tmpArrEpisodeVO.length::" + tmpOldDeptArrEpisodeVO.length);

				
				 * if(_episodeReferVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
				 * episodeDAO.episodeUpdateReferralAcceptanceDate(_episodeReferVO, _userVO);
				 * episodeReferDAO.create(_episodeReferVO, _userVO);
				 

				
				 * int m,n; for( m=0, n=0; m<_tmpArrEpisodeVO.length; m++,n++){ _arrEpisodeVO[n]=_tmpArrEpisodeVO[m];
				 * System.out.println("_arrEpisodeVO.length::1"+_arrEpisodeVO.length); }
				 
				// System.out.println("oldDeptArrVO.length::1"+oldDeptArrVO.length);
			}

			
			 * //checks if a patient is referred from outside or internally. //if a ptient is referred internally, his
			 * previous episode detail record is udated for referral acceptance date.
			 * if(_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
			 * if(_arrEpisodeVO[i].getEpisodeReferAccept().equals(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL))
			 * episodeDao.episodeUpdateReferralAcceptanceDate(_arrEpisodeVO[i], _episodeReferVO, _userVO); } else
			 * _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
			 * 
			 * PatientBOSupport.newDeptVisitStamp(_arrEpisodeVO, _patientVO, _userVO, tx);
			 * 
			 * if(_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
			 * //_patientVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
			 * HelperMethods.populate(_episodeReferVO, _patientVO); HelperMethods.populate(_episodeReferVO,
			 * _arrEpisodeVO[i]); episodeReferDAO.create(_episodeReferVO, _userVO); }
			 

		}
		catch (HisDeptUnitNotOnDuty e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeptUnitNotOnDuty(e.getMessage());
		}
		catch (HisDeptUnitIsClosed e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeptUnitIsClosed(e.getMessage());
		}
		catch (HisDeptUnitAlreadyVisited e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeptUnitAlreadyVisited(e.getMessage());
		}
		catch (HisNoOpenEpisodeFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNoOpenEpisodeFoundException(e.getMessage());
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			tx.close();
		}
		return _arrEpisodeVO;
	}

	
	public UnknownChangeVO getConvertedToKnownDetails(PatientVO _patientVO, UserVO _userVO)
	{
		//System.out.println("inside getIsConvertedToKnown()");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		UnknownChangeVO unknownChangeVO = new UnknownChangeVO();
		try
		{
			//System.out.println("Begining transaction");
			tx.begin();
			//System.out.println("creating object of EpisodeDAO");
			UnknownChangeDAO unknownDAO = new UnknownChangeDAO(tx);

			//System.out.println("unknownDAO.getLock()");
			unknownChangeVO = unknownDAO.retrieveunknownChangeDetails(_patientVO, _userVO);
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("inside HisRecordNotFoundException of ");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisPatientStillUnknownException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (HisNotAnEmergencyCaseException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisNotAnEmergencyCaseException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return unknownChangeVO;
	}

	/**
	 * Retrieves refer internal details of a patient from the Episode Refer Dtl Table.
	 * 
	 * @param _patientVO Provides patient's CR No.
	 * @param _userVO Provides User details.
	 * @return Array of EpisodeReferVO containing internal referral details.
	 
	public EpisodeReferVO[] retrieveRefInternalEpisodeDtl(PatientVO _patientVO, UserVO _userVO)
	{
		System.out.println("inside retrieveRefInternalEpisodeDtl()");
		// System.out.println("Patient first name::"+_patientVO.getPatFirstName());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeReferVO[] _arrEpisodeReferVO =
		{};
		EpisodeVO _episodeVO = new EpisodeVO();

		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			System.out.println("creating object of PatientDAO");
			EpisodeReferDAO episodeReferDao = new EpisodeReferDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);

			System.out.println("patientDao.getLock()");
			_arrEpisodeReferVO = episodeReferDao.retrieveRefInternalEpisodeDtlInternal(_patientVO, _userVO);
			System.out.println("_arrPatientVO.length::" + _arrEpisodeReferVO.length);
			for (int i = 0; i < _arrEpisodeReferVO.length; i++)
			{
				_episodeVO.setPatCrNo(_arrEpisodeReferVO[i].getPatCrNo());
				_episodeVO.setEpisodeCode(_arrEpisodeReferVO[i].getEpisodeCode());
				_episodeVO = episodeDao.retrieveByEpisodeNo(_episodeVO, _userVO);
				System.out.println("_episodeVO.getDepartmentCode..:: " + _episodeVO.getDepartmentCode());
				System.out.println("_episodeVO.getDepartment..:: " + _episodeVO.getDepartment());
				_arrEpisodeReferVO[i].setRefFromDepartmentCode(_episodeVO.getDepartmentCode());
				_arrEpisodeReferVO[i].setRefFromDepartment(_episodeVO.getDepartment());
				_arrEpisodeReferVO[i].setRefToDepartmentCode(_arrEpisodeReferVO[i].getDepartmentCode());
				_arrEpisodeReferVO[i].setRefToDepartment(_arrEpisodeReferVO[i].getDepartment());
				System.out.println("_arrEpisodeReferVO[" + i + "].getRefFromDepartmentCode..:: " + _arrEpisodeReferVO[i].getRefFromDepartmentCode());
				System.out.println("_arrEpisodeReferVO[" + i + "].getRefFromDepartment..:: " + _arrEpisodeReferVO[i].getRefFromDepartment());
				System.out.println("_arrEpisodeReferVO[" + i + "].getRefToDepartmentCode..:: " + _arrEpisodeReferVO[i].getRefToDepartmentCode());
				System.out.println("_arrEpisodeReferVO[" + i + "].getRefToDepartment..:: " + _arrEpisodeReferVO[i].getRefToDepartment());

			}
		}
		catch (HisReferredRecordNotFoundException e)
		{
			System.out.println("inside  BO Record not found exception");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisReferredRecordNotFoundException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside  BO Record not found exception");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		System.out.println("hi!!!!!!!!!!!!");
		return _arrEpisodeReferVO;
	}

	/**
	 * Checks patient's current status in the hospital. Should be called only after the patient details has been retrieved.
	 * 
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @param visitType Specifies the visit type, whether its OPD, IPD or Emergency.
	 * @return <code>true</code> if patient status is according to the visit type otherwise <code>false</code>.
	 
	public boolean checkPatientStatusByVisitType(PatientVO _patientVO, UserVO _userVO, String visitType)
	{
		System.out.println("inside checkPatientStatusByVisitType() of PatientBO");
		boolean value = false;
		try
		{
			if (_patientVO.getPatStatusCode() != null)
			{
				value = PatientBOSupport.checkPatientStatus(_patientVO, _userVO, visitType);
				if (value == true) System.out.println("Patient eligible for visit type::: " + visitType);
				else System.out.println("Patient not eligible for visit type::: " + visitType);
			}
			else System.out.println("_patientVO.getPatStatusCode() is null");

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside HisRecordNotFoundException of checkPatientStatus in PatientBO");
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisNotAnOPDcaseException e)
		{
			System.out.println("inside HisNotAnOPDcaseException of checkPatientStatus in PatientBO");
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			System.out.println("inside HisNotAnIPDcaseException of checkPatientStatus in PatientBO");
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			System.out.println("inside HisDeadPatientException of checkPatientStatus in PatientBO");
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		return value;
	}

	/**
	 * Retrieves all episode details of the patient fron Episode Dtl Table.
	 * 
	 * @param _patientVO Provides CR No of the patient for which episode details are to be searched.
	 * @param _userVO Provides User details.
	 * @return Array of EpisodeVO with values stored in DB.
	 
	public EpisodeVO[] searchAllEpisodeByCrNo(PatientVO _patientVO, UserVO _userVO)
	{
		System.out.println("inside searchAllEpiosdeByCrNo() not checking isConfirmed");
		System.out.println("Patient first name::" + _patientVO.getPatFirstName());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO =
		{};
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			System.out.println("creating object of EpisodeDAO");
			EpisodeDAO episodeDao = new EpisodeDAO(tx);

			System.out.println("episodeDao.getLock()");
			String str = _patientVO.getPatCrNo();
			System.out.println("Patient first name::" + _patientVO.getPatFirstName());

			System.out.println("str::_patientVO.getPatCrNo():: " + str);
			_arrEpisodeVO = episodeDao.retrieveAllByCrNo(_patientVO, _userVO);
			System.out.println("_arrEpisodeVO.length::" + _arrEpisodeVO.length);
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside HisRecordNotFoundException of ");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrEpisodeVO;
	}

	/**
	 * This Method return the MlcVO for the patient against the cr no. Used for patient detail tile to retrieve the mlc no.
	 * Before retrieving it checks for patients visit type in Episode dtl table for the last episode(open/close). if it
	 * returns a record,the method proceeds to check weather the mlc details for the patients have been recorded or not.
	 * However if patient visit type is found to be MLC and records haven,t been entered into MLC Table(This will be shown as
	 * ::MLC NO yet to be alloted)(set in controller-UTIL)
	 * 
	 * @see
	 
	public MlcVO isPatientMlc(PatientVO _patientVO, UserVO _userVO)
	{
		MlcVO mlcVO = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		EpisodeDAO episodeDao = new EpisodeDAO(tx);
		try
		{
			String mlcNumber = episodeDao.isPatientMLC(_patientVO, _userVO);
			if (mlcNumber != null || !(mlcNumber.equals("")))
			{
				mlcVO = new MlcVO();
				mlcVO.setPatCrNo(_patientVO.getPatCrNo());
				mlcVO.setMlcNo(mlcNumber);
			}
			
			 * if (episodeCode != null) { mlcVO = new MlcVO(); mlcVO.setPatCrNo(_patientVO.getPatCrNo()); mlcVO =
			 * getMlcDtlBasedOnCrNo(mlcVO, _userVO); if (mlcVO.getEpisodeCode().equalsIgnoreCase(episodeCode)) { return
			 * mlcVO; } else { mlcVO = null; throw new HisRecordNotFoundException(); } }
			 
		}
		finally
		{
			tx.close();
		}

		return mlcVO;
	}// end of isPatientMlc

	public MlcVO[] getMlcDtlBasedOnCrNoandImage(MlcVO _mlcVo, UserVO _uservo)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//VerificationDocumentVO[] verificationDocumentVOs = null;
		//VerificationDocumentVO verificationDocumentVO = new VerificationDocumentVO();
		MlcVO[] mlcVo = null;
		try
		{
			tx.begin();
			MlcDAO mlcDao = new MlcDAO(tx);
			//VerificationDocumentUsedDAO verificationDocumentUsedDAO = new VerificationDocumentUsedDAO(tx);
			PatientVO patientVO = new PatientVO();
			patientVO.setPatCrNo(_mlcVo.getPatCrNo());
			mlcVo = mlcDao.retrieveByCrNoForMaxSno(patientVO, _mlcVo, _uservo);
			
			 * verificationDocumentVO.setPatCrNo(patientVO.getPatCrNo());
			 * verificationDocumentVO.setMenuID(RegistrationConfig.MLC_PROCESS_ID);
			 * verificationDocumentVOs=verificationDocumentUsedDAO.retrieveByProcessIDAndCrNo(verificationDocumentVO,_uservo);
			 * String sno=null; if(verificationDocumentVOs.length>0) sno=verificationDocumentVOs[0].getSerialNo(); for(int
			 * i=1;i<verificationDocumentVOs.length;i++){ if(Integer.parseInt(sno)<=Integer.parseInt(verificationDocumentVOs[i].getSerialNo())){
			 * sno=verificationDocumentVOs[i].getSerialNo(); } }
			 * 
			 * if( sno==null || sno.equals("")) sno="0"; System.out.println("after populating MlcVO");
			 * _mlcVo.setSerialNo(sno);
			 

		}// end of try

		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return mlcVo;
	}

	public void saveMlcDoc(MlcVO _mlcVo, UserVO _uservo)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MlcDAO mlcDAO = new MlcDAO(tx);

		try
		{

			tx.begin();
			if (_mlcVo.getImageFile() != null) _mlcVo.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_TRUE);
			else
			{
				_mlcVo.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
			}
			mlcDAO.saveMlcDoc(_mlcVo, _uservo);
			String newfieName = _mlcVo.getPatCrNo() + "$" + _mlcVo.getMlcNo() + "$" + _mlcVo.getEpisodeCode() + "$" + _mlcVo.getSerialNo() + ".jpg";

			if (_mlcVo.getImageFile() != null) HelperMethods.storeImageInCorrectFileSystem(_mlcVo.getImageFile(), _mlcVo.getImageFileName(),
					newfieName, Config.MLC_DOC_IMAGE_FILE_STORAGE_PATH, Config.MLC_DOC_IMAGE_FILE_STORAGE_PATH_LINUX);

		}// end of try
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			tx.close();
		}
	}// end of Method

	public void saveMlcDocUpload(MlcDocUploadDtlVO[] _mlcDocUploadDtlVOs, MlcVO[] _mlcVo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MlcDAO mlcDAO = new MlcDAO(tx);
		MlcDocUploadDtlDAO mlcDocUploadDtlDAO = new MlcDocUploadDtlDAO(tx);
		String sNo = null;
		try
		{
			tx.begin();
			sNo = mlcDocUploadDtlDAO.selectNextSerialNoForPUK(_mlcDocUploadDtlVOs[0], userVO);
			for (int i = 0; i < _mlcDocUploadDtlVOs.length; i++)
			{
				if (_mlcVo[i].getImageFile() != null)
				{
					_mlcVo[i].setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_TRUE);
				}
				else
				{
					_mlcVo[i].setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
				}
				mlcDAO.saveMlcDoc(_mlcVo[i], userVO);

				int idx = _mlcVo[i].getImageFileName().lastIndexOf(".");
				String ext = _mlcVo[i].getImageFileName().substring(idx, _mlcVo[i].getImageFileName().length());

				_mlcDocUploadDtlVOs[i].setMlcNo(_mlcVo[i].getMlcNo());
				String newfieName = _mlcVo[i].getPatCrNo() + "$" + _mlcVo[i].getMlcNo() + "$" + sNo + ext;
				_mlcDocUploadDtlVOs[i].setDocumentName(newfieName);
				mlcDocUploadDtlDAO.create(_mlcDocUploadDtlVOs[i], userVO);

				// if (_mlcVo[i].getImageFile() != null)
				// HelperMethods.storeImageInFileSystem(_mlcVo[i].getImageFile(), _mlcVo[i].getImageFileName(), newfieName,
				// RegistrationConfig.MLC_DOC_IMAGE_FILE_STORAGE_PATH);
				if (_mlcVo[i].getImageFile() != null) HelperMethods.storeImageInCorrectFileSystem(_mlcVo[i].getImageFile(), _mlcVo[i]
						.getImageFileName(), newfieName, Config.MLC_DOC_IMAGE_FILE_STORAGE_PATH, Config.MLC_DOC_IMAGE_FILE_STORAGE_PATH_LINUX);

				sNo = Integer.toString(Integer.parseInt(sNo) + 1);
				System.out.println("");
			}
		}// end of try
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			tx.close();
		}
	}// end of Method

	
	 * Checks whether currently any episode is opened for a patient in emergency on the basis of CR No. Also checks that the
	 * episodes should be valid and active.
	 * 
	 * @param _patientVO Provides CR No of the patient whose address is to be searched.
	 * @param _userVO Provides User details.
	 * @return <code>true</code> if patient's any episode is currently opened in Emergency otherwise <code>false</code>.
	 
	public boolean checkOpenEmgEpisodeByCrNo(PatientVO _patientVO, UserVO _userVO)
	{
		System.out.println("inside searchPatientByCrNo() of PatientBO");
		// System.out.println("_crNo::"+ _crNo);
		String str = _patientVO.getPatCrNo();
		int x = 0;
		System.out.println("_patientVO.getPatCrNo()::str:: " + str);
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			System.out.println("creating object of PatientDAO");
			System.out.println("_patientVO.getPatCrNo()::str " + str);
			System.out.println("inside newDeptVisitStamp()");
			// boolean value=false;

			x = episodeDao.checkOpenEmgEpisodeByCrNo(_patientVO, _userVO);
			
			 * if(x>0) throw new HisEpisodeOpenInEmergencyException();
			 

		}
		
		 * catch(HisEpisodeOpenInEmergencyException e){ System.out.println("inside BO HisEpisodeOpenInEmergencyException");
		 System.out.println(e.getMessage()); tx.rollback(); throw new HisEpisodeOpenInEmergencyException(e.getMessage()); }
		 
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside BO HisRecordNotFoundException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("inside BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside BO Exception");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			tx.close();
		}
		if (x > 0) return true;
		else return false;
	}

	/**
	 * Changes primary and secondary categories of a patient. Updates the record in Patient dtl.
	 * 
	 * @param _arrChangeCategoryVO Provides change of category details.
	 * @param _userVO Provides User details.
	 * @return <code>true</code> if records are updated successfully otherwise <code>false</code>. *
	 */
	/*
	 * public boolean changePatientCategory(ChangeCategoryVO[] _arrChangeCategoryVO, UserVO _userVO) { System.out
	 * .println("inside changePatientCategory(ChangeCategoryVO[] _arrChangeCategoryVO, UserVO _userVO) of PatientBO");
	 * JDBCTransactionContext tx = new JDBCTransactionContext(); int x = 0, y = 0, z = 0; PatientVO _patientVO = new
	 * PatientVO(); EpisodeVO _episodeVO = new EpisodeVO();
	 * 
	 * try { System.out.println("Begining transaction"); tx.begin(); System.out.println("_arrChangeCategoryVO.length ..:: " +
	 * _arrChangeCategoryVO.length); PatientDAO patientDao = new PatientDAO(tx); EpisodeDAO episodeDao = new EpisodeDAO(tx);
	 * CategoryChangeDAO catChangeDAO = new CategoryChangeDAO(tx); if
	 * (_arrChangeCategoryVO[0].getPatNewPrimaryCatCode().equals(_arrChangeCategoryVO[0].getPatPrevPrimaryCatCode())) {
	 * _patientVO.setPatCrNo(_arrChangeCategoryVO[0].getPatCrNo());
	 * _patientVO.setPatPrimaryCatCode(_arrChangeCategoryVO[0].getPatNewPrimaryCatCode()); x =
	 * patientDao.changePatientPrimaryCat(_patientVO, _userVO); if (x >= 1) x =
	 * episodeDao.updatePrimaryCategoryEpisodeDtl(_episodeVO, _userVO); } else x = 1; for (int i = 0, j = 0; i <
	 * _arrChangeCategoryVO.length; i++, j++) { if (x >= 1) { y = 0;
	 * _episodeVO.setPatCrNo(_arrChangeCategoryVO[i].getPatCrNo());
	 * _episodeVO.setEpisodeCode(_arrChangeCategoryVO[i].getEpisodeCode());
	 * _episodeVO.setPatSecondaryCatCode(_arrChangeCategoryVO[i].getPatNewSecondaryCatCode()); y =
	 * episodeDao.updateSecondaryCategoryEpisodeDtl(_episodeVO, _userVO); } if (y >= 1) {
	 * _arrChangeCategoryVO[j].setIsValid(RegistrationConfig.IS_VALID_ACTIVE);
	 * _arrChangeCategoryVO[j].setSeatId(_userVO.getSeatId()); catChangeDAO.create(_arrChangeCategoryVO[j], _userVO); z++; } }
	 * //System.out.println("after changing PatPrimaryCatCode::"+_patientVO.getPatPrimaryCatCode());
	 * //System.out.println("after changing PatSecondaryCatCode::"+_patientVO.getPatSecondaryCatCode()); } catch
	 * (HisUpdateUnsuccesfullException e) { System.out.println(e.getMessage()); tx.rollback(); throw new HisUpdateUnsuccesfullException(); }
	 * catch (HisApplicationExecutionException e) { System.out.println(e.getMessage()); tx.rollback(); throw new
	 * HisApplicationExecutionException(e.getMessage()); } catch (HisDataAccessException e) { System.out.println(e.getMessage());
	 * tx.rollback(); throw new HisDataAccessException(e.getMessage()); } catch (Exception e) { System.out.println(e.getMessage());
	 * tx.rollback(); throw new HisException(e.getMessage()); } finally { System.out.println("z = " + z); tx.close(); } if (z >=
	 * 1) return true; else return false; }
	 
	public EpisodeVO getLastEpisodeInEmergency(PatientVO _patVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO episodeVO = null;
		try
		{
			//System.out.println("Begining transaction");
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			episodeVO = episodeDao.getLastEmergencyEpisode(_patVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{

			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("inside BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside BO Exception");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			tx.close();
		}
		return episodeVO;

	}

	/**
	 * Retrieves all referred episodes of the patient fron Episode Dtl Table.
	 * 
	 * @param _patientVO Provides CR No of the patient for which episode details are to be searched.
	 * @param _userVO Provides User details.
	 * @return Array of EpisodeVO with values stored in DB.
	 
	public EpisodeVO[] retrieveAllReferredEpisodesByCrNo(PatientVO _patientVO, UserVO _userVO)
	{
		System.out.println("inside retrieveAllReferredEpisodesByCrNo() in PatientBO");
		System.out.println("Patient first name::" + _patientVO.getPatFirstName());
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO =
		{};
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			System.out.println("creating object of EpisodeDAO");
			EpisodeDAO episodeDao = new EpisodeDAO(tx);

			String str = _patientVO.getPatCrNo();
			System.out.println("Patient first name::" + _patientVO.getPatFirstName());

			System.out.println("str::_patientVO.getPatCrNo():: " + str);
			_arrEpisodeVO = episodeDao.retrieveAllReferredEpisodeByCrNo(_patientVO, _userVO);
			System.out.println("_arrEpisodeVO.length::" + _arrEpisodeVO.length);
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside HisRecordNotFoundException of ");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrEpisodeVO;
	}

	/**
	 * Retrieves all currently opened episodes of a patient in OPD.
	 * 
	 * @param _patientVO Provides CR No of the patient for which episode details are to be searched.
	 * @param _userVO Provides User details.
	 * @return Array of EpisodeReferVO with all the open OPD episodes of the patient.
	 
	public EpisodeReferVO[] getAllOpenOPDEpisodes(String crNo, UserVO _userVO)
	{

		System.out.println("inside getAllOpenOPDEpisodes() of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO;
		EpisodeReferVO[] _arrOpenOPDEpisode;

		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			System.out.println("creating object of episodeDao");
			// populate the addressVO with required details
			System.out.println("episodeDao.getLock() ");
			_arrEpisodeVO = episodeDao.retrieveAllOpenOPDByCrNo(crNo, _userVO);
			System.out.println("retrieveAllOpenOPDByCrNo()...._arrEpisodeVO.length= " + _arrEpisodeVO.length);
			_arrOpenOPDEpisode = new EpisodeReferVO[_arrEpisodeVO.length];
			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				_arrOpenOPDEpisode[i] = new EpisodeReferVO();
				HelperMethods.populate(_arrOpenOPDEpisode[i], _arrEpisodeVO[i]);
				_arrOpenOPDEpisode[i].setRefFromDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
				_arrOpenOPDEpisode[i].setRefFromDepartment(_arrEpisodeVO[i].getDepartment());

			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrOpenOPDEpisode;
	}

	public EpisodeRefDtlVO[] retrieveAllOpenOPDEpisodes(String crNo, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO;
		EpisodeRefDtlVO[] _arrOpenOPDEpisode;

		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			// populate the addressVO with required details
			_arrEpisodeVO = episodeDao.retrieveByCrNo(crNo, _userVO);// .retrieveOldPatientEpisodes(crNo,_userVO);
			_arrOpenOPDEpisode = new EpisodeRefDtlVO[_arrEpisodeVO.length];
			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				_arrOpenOPDEpisode[i] = new EpisodeRefDtlVO();
				HelperMethods.populate(_arrOpenOPDEpisode[i], _arrEpisodeVO[i]);
				_arrOpenOPDEpisode[i].setFromDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());

				_arrOpenOPDEpisode[i].setFromDepartment(_arrEpisodeVO[i].getDepartment());
				_arrOpenOPDEpisode[i].setFromDepartmentUnit(_arrEpisodeVO[i].getDepartmentUnit());
				_arrOpenOPDEpisode[i].setFromDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
			}
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrOpenOPDEpisode;
	}

	public EpisodeRefDtlVO[] retrieveAllSpecialEpisodes(String crNo, UserVO _userVO)
	{

		System.out.println("inside getAllOpenOPDEpisodes() of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO;
		EpisodeRefDtlVO[] _arrOpenOPDEpisode;

		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			System.out.println("creating object of episodeDao");
			// populate the addressVO with required details
			System.out.println("episodeDao.getLock() ");
			_arrEpisodeVO = episodeDao.retrieveAllSpecialEpisodes(crNo, _userVO);
			System.out.println("getAllOpenOPDEpisodes()...._arrEpisodeVO.length= " + _arrEpisodeVO.length);
			_arrOpenOPDEpisode = new EpisodeRefDtlVO[_arrEpisodeVO.length];
			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				_arrOpenOPDEpisode[i] = new EpisodeRefDtlVO();
				HelperMethods.populate(_arrOpenOPDEpisode[i], _arrEpisodeVO[i]);
				_arrOpenOPDEpisode[i].setFromDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
				_arrOpenOPDEpisode[i].setFromDepartment(_arrEpisodeVO[i].getDepartment());
				_arrOpenOPDEpisode[i].setFromDepartmentUnit(_arrEpisodeVO[i].getDepartmentUnit());
				_arrOpenOPDEpisode[i].setFromDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrOpenOPDEpisode;
	}

	/**
	 * Retrieves all currently opened episodes of a patient in the Hospital.
	 * 
	 * @param _patientVO Provides CR No of the patient for which episode details are to be searched.
	 * @param _userVO Provides User details.
	 * @return Array of EpisodeVO with all the open episodes of the patient.
	 
	public Map getAllOpenEpisodes(String crNo, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeDupVO;

		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_arrEpisodeDupVO = episodeDao.retrieveByCrNo(crNo, _userVO);
			essentialMap.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, _arrEpisodeDupVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public EpisodeVO[] getAllOpenEpisodesVisitedToday(String crNo, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO;

		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_arrEpisodeVO = episodeDao.getAllOpenEpisodesVisitedToday(crNo, _userVO);

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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrEpisodeVO;
	}

	/**
	 * Changes primary category of a patient. Updates the record in Patient Dtl and Episode Dtl table.
	 * 
	 * @param _primCatChangeVO Provides change of category details.
	 * @param _userVO Provides User details.
	 * @return <code>true</code> if records are updated successfully otherwise <code>false</code>.
	 
	public boolean changePrimaryCategory(PrimaryCategoryChangeVO _primCatChangeVO, UserVO _userVO, VerificationDocumentVO _veriDocVO,
			EmpMasterVO _empMstVO, String _patIdNo)
	{
		System.out.println("inside changePrimaryCategory(PrimaryCategoryChangeVO _primCatChangeVO, UserVO _userVO) of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String nextSerialNo = "";
		int x = 0, y = 0;
		PatientVO _patientVO = new PatientVO();
		DailyPatientVO dailyPatVo = new DailyPatientVO();
		EpisodeVO episodeVO=new EpisodeVO();

		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			VerificationDocumentUsedDAO veriDocUsedDAO = new VerificationDocumentUsedDAO(tx);
			PrimaryCategoryChangeDAO primCatChangeDAO = new PrimaryCategoryChangeDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAOi episodeDAO=new EpisodeDAO(tx);
			
			_patientVO.setPatCrNo(_primCatChangeVO.getPatCrNo());
			_patientVO.setPatPrimaryCatCode(_primCatChangeVO.getPatNewPrimaryCatCode());

			// ///////////updating daily patient dtl if patient visited today
			dailyPatVo.setPatCrNo(_primCatChangeVO.getPatCrNo());
			dailyPatVo.setPatPrimaryCatCode(_primCatChangeVO.getPatNewPrimaryCatCode());
			dailyPatDao.changePatientPrimaryCat(dailyPatVo, _userVO);
			
			/////////Updating Episode Detail table/////////
			episodeVO.setPatCrNo(_primCatChangeVO.getPatCrNo());
			episodeVO.setPatPrimaryCatCode(_primCatChangeVO.getPatNewPrimaryCatCode());
			episodeDAO.updatePatCategory(episodeVO,_userVO);

			if (_primCatChangeVO.getPatNewPrimaryCatCode().equals(Config.PRIMARY_CATEGORY_EMPLOYEE_CODE))
			{

				// ////////////inserting into primary cat change dtl
				primCatChangeDAO.create(_primCatChangeVO, _userVO);

				// //////////update hrgt patient detail

				if (Config.PATCAT_EMPLOYEE_FIELD_VALUE.equals(Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE))
				{
					HelperMethods.populate(_patientVO, _empMstVO);
					_patientVO.setPatCrNo(_primCatChangeVO.getPatCrNo());
					_patientVO.setPatPrimaryCatCode(_primCatChangeVO.getPatNewPrimaryCatCode());
					patientDao.updatePatientEmployeeDetails(_patientVO, _userVO);
				}
				if (Config.PATCAT_EMPLOYEE_FIELD_VALUE.equals(Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_TRUE))
				{
					if (_empMstVO == null)
					{
						patientDao.changePatientPrimaryCat(_patientVO, _userVO, _patIdNo);
					}
					else
					{
						HelperMethods.populate(_patientVO, _empMstVO);
						_patientVO.setPatCrNo(_primCatChangeVO.getPatCrNo());
						_patientVO.setPatPrimaryCatCode(_primCatChangeVO.getPatNewPrimaryCatCode());
						patientDao.updatePatientEmployeeDetails(_patientVO, _userVO);
					}
				}

			}
			else
			{
				// ///////////selecting verification doc serial no/////////
				nextSerialNo = veriDocUsedDAO.selectNextSerialNoForPUK(_veriDocVO, _userVO);
				// /////////inserting into verification doc used detail
				veriDocUsedDAO.createWithSerialNO(_veriDocVO, _userVO, nextSerialNo);
				// ////////////inserting into primary cat change dtl
				_primCatChangeVO.setVerificationDocumentId(nextSerialNo);
				primCatChangeDAO.create(_primCatChangeVO, _userVO);
				// ////////////////updating hrgt patient dtl
				patientDao.changePatientPrimaryCat(_patientVO, _userVO, _patIdNo);
			}

			System.out.println("after primCatChangeDAO.create(_primCatChangeVO, _userVO)");
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			System.out.println("y = " + y);
			tx.close();
		}
		if (y >= 1) return true;
		else return false;
	}

	/**
	 * Changes secondary categories of a patient for open episodes. Updates the record in Episode Dtl table.
	 * 
	 * @param _secCatChangeVO Provides change of category details.
	 * @param _userVO Provides User details.
	 * @return <code>true</code> if records are updated successfully otherwise <code>false</code>.
	 
	public boolean changeSecondaryCategory(SecondaryCategoryChangeVO[] _secCatChangeVO,SecondaryCategoryChangeVO[] secCatRevokeVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int x = 0, y = 0;
		DailyPatientVO _dailyPatientVO = new DailyPatientVO();
		EpisodeVO _episodeVO = new EpisodeVO();

		try
		{
			System.out.println("Begining transaction");
			tx.begin();

			// ///////////////Check if the secondary category is revoked/////////
			for (int z = 0; z < _secCatChangeVO.length; z++)
			{
				if (_secCatChangeVO[z].getPatNewSecondaryCatCode().equals(RegistrationConfig.REVOKE_TREATMENT_CATEGORY_VALUE))
				{
					_secCatChangeVO[z].setPatNewSecondaryCatCode("");

				}

			}
			// /////////////////////////////////////////////////////

			DailyPatientDAO dailyPatientDao = new DailyPatientDAO(tx);
			SecondaryCategoryChangeDAO secCatChangeDAO = new SecondaryCategoryChangeDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			if(_secCatChangeVO!=null)
			{
				for (int i = 0; i < _secCatChangeVO.length; i++)
				{
					_episodeVO.setPatCrNo(_secCatChangeVO[i].getPatCrNo());
					_episodeVO.setEpisodeCode(_secCatChangeVO[i].getEpisodeCode());
					_episodeVO.setPatSecondaryCatCode(_secCatChangeVO[i].getPatNewSecondaryCatCode());
					_episodeVO.setEpisodeVisitNo(_secCatChangeVO[i].getEpisodeVisitNo());
					_episodeVO.setValidUpto(_secCatChangeVO[i].getValidUpto());
					_episodeVO.setSecCatExpDate(_secCatChangeVO[i].getExpiryDate());
	
					// ///////updating episode dtl////////
				//	x = episodeDAO.updateSecondaryCategoryEpisodeDtl(_episodeVO, _userVO);
					if(_secCatChangeVO[i].getExpiryDate()!=null && !_secCatChangeVO[i].getExpiryDate().equals(""))
						x = episodeDAO.updateSecondaryCatNExpiryDate(_episodeVO, _userVO);
					else
						x = episodeDAO.updateSecondaryCatNValidUpTo(_episodeVO, _userVO);
	
					_dailyPatientVO.setPatCrNo(_secCatChangeVO[i].getPatCrNo());
					_dailyPatientVO.setEpisodeCode(_secCatChangeVO[i].getEpisodeCode());
					_dailyPatientVO.setPatSecondaryCatCode(_secCatChangeVO[i].getPatNewSecondaryCatCode());
					_dailyPatientVO.setEpisodeVisitNo(_secCatChangeVO[i].getEpisodeVisitNo());
	
					// ////////updating daily patient dtl////////
				//OLD//		dailyPatientDao.updateSecondaryCategory(_dailyPatientVO, _userVO);
					
					if(_secCatChangeVO[i].getIsIpdFlag().equals(RegistrationConfig.NO))
						dailyPatientDao.updateTreatmentCategory(_dailyPatientVO, _userVO);
	
					// /////////inserting into secondary category change dtl////////
					if (x >= 1)
					{
	
						// /////////check if category expiry is extended or category changed////////
	
						if ((_secCatChangeVO[i].getPatNewSecondaryCatCode().equals(_secCatChangeVO[i].getPatPrevSecondaryCatCode()))
								&& (!_secCatChangeVO[i].getExpiryDate().equals("")))
						{
							secCatChangeDAO.createExtendValidUpto(_secCatChangeVO[i], _userVO);
						}
						else
						{
							secCatChangeDAO.create(_secCatChangeVO[i], _userVO);
						}
	
						y++;
					}
				}
			}
			
			if(secCatRevokeVO!=null)
			{
				for(int i=0;i<secCatRevokeVO.length;i++)
				{
					if(secCatRevokeVO[i].getIsIpdFlag().equals(RegistrationConfig.NO))
					{
						_dailyPatientVO.setPatCrNo(secCatRevokeVO[i].getPatCrNo());
						_dailyPatientVO.setEpisodeCode(secCatRevokeVO[i].getEpisodeCode());
						_dailyPatientVO.setPatSecondaryCatCode(secCatRevokeVO[i].getPatNewSecondaryCatCode());
						_dailyPatientVO.setEpisodeVisitNo(secCatRevokeVO[i].getEpisodeVisitNo());
						
						dailyPatientDao.updateTreatmentCategory(_dailyPatientVO, _userVO);
						
					}
						
					
					_episodeVO.setPatCrNo(secCatRevokeVO[i].getPatCrNo());
					_episodeVO.setEpisodeCode(secCatRevokeVO[i].getEpisodeCode());
					_episodeVO.setPatSecondaryCatCode(secCatRevokeVO[i].getPatNewSecondaryCatCode());
					_episodeVO.setEpisodeVisitNo(secCatRevokeVO[i].getEpisodeVisitNo());
					_episodeVO.setSecCatExpDate("");
					
					episodeDAO.updateRevokeSecondaryCat(_episodeVO, _userVO);	
					
					secCatChangeDAO.create(secCatRevokeVO[i], _userVO);
					
				}
			}

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			System.out.println("y = " + y);
			tx.close();
		}
		if (y >= 1) return true;
		else return false;
	}

	public EpisodeVO[] changeFileNo(FileNumberChangeVO[] _fileNoChangeVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int x = 0;
		String fileNumber = "";

		DailyPatientDAO dailyPatientDao = new DailyPatientDAO(tx);
		EpisodeDAO episodeDAO = new EpisodeDAO(tx);
		FileNumberChangeDAO fileNoChangeDAO = new FileNumberChangeDAO(tx);
		DailyPatientVO _dailyPatientVO = new DailyPatientVO();
		EpisodeVO _episodeVO = new EpisodeVO();
		EpisodeVO[] returnEpisodeVO = new EpisodeVO[_fileNoChangeVO.length];
		try
		{
			tx.begin();

			for (int i = 0; i < _fileNoChangeVO.length; i++)
			{

				// //////////////Generating File Number////////

				//if (Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
				if (_fileNoChangeVO[i].getEntryMode().equals(Config.FILENO_GENRATION_TYPE_AUTO))
				{
					fileNumber = dailyPatientDao.generateFileNumber(_fileNoChangeVO[i].getUnitCode(), _userVO.getHospitalCode());
					fileNumber=fileNumber.split("@")[1];
				}
				else
				{
					fileNumber = _fileNoChangeVO[i].getNewFileNo();
				}
				returnEpisodeVO[i] = new EpisodeVO();
				returnEpisodeVO[i].setDepartmentUnitCode(_fileNoChangeVO[i].getUnitCode());
				returnEpisodeVO[i].setDepartmentUnit(_fileNoChangeVO[i].getUnitName());
				returnEpisodeVO[i].setDepartment(_fileNoChangeVO[i].getDepartmentName());
				returnEpisodeVO[i].setFileNo(fileNumber);

				_fileNoChangeVO[i].setNewFileNo(fileNumber);

				_episodeVO.setPatCrNo(_fileNoChangeVO[i].getPatCrNo());
				_episodeVO.setEpisodeCode(_fileNoChangeVO[i].getEpisodeCode());
				_episodeVO.setEpisodeVisitNo(_fileNoChangeVO[i].getEpisodeVisitNo());
				_episodeVO.setFileNo(_fileNoChangeVO[i].getNewFileNo());
				// ///////updating episode dtl////////
				episodeDAO.updateFileNoEpisodeDtl(_episodeVO, _userVO);

				_dailyPatientVO.setPatCrNo(_fileNoChangeVO[i].getPatCrNo());
				_dailyPatientVO.setEpisodeCode(_fileNoChangeVO[i].getEpisodeCode());
				_dailyPatientVO.setEpisodeVisitNo(_fileNoChangeVO[i].getEpisodeVisitNo());
				_dailyPatientVO.setFileNo(_fileNoChangeVO[i].getNewFileNo());
				// ////////updating daily patient dtl////////
				dailyPatientDao.updateFileNumber(_dailyPatientVO, _userVO);

				// //////////inserting into change file number////
				fileNoChangeDAO.create(_fileNoChangeVO[i], _userVO);

			}
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return returnEpisodeVO;
	}

	
	 * public List getListDeptWiseFileNo(String crNo, UserVO _userVO){
	 * 
	 * //Map mapFielNo=new HashMap(); List lstFileNo=null;
	 * 
	 * JDBCTransactionContext tx =new JDBCTransactionContext(); try{ tx.begin(); EpisodeDAO objEpisodeDAO=new EpisodeDAO(tx);
	 * 
	 * lstFileNo=objEpisodeDAO.getDeptWiseFileNo(crNo, _userVO);
	 * //mapFielNo.put(RegistrationConfig.LIST_DEPT_WISE_FILE_NO,lstFileNo); } catch(HisApplicationExecutionException e){
	 * tx.rollback(); System.out.println(e.getMessage()); throw new HisApplicationExecutionException(); } catch(HisDataAccessException e){
	 * tx.rollback(); System.out.println(e.getMessage()); throw new HisDataAccessException(); } catch(Exception e){ System.out.println(e.getMessage());
	 * System.out.println("error.... Essential BO"); tx.rollback(); throw new HisApplicationExecutionException(); } finally{
	 * tx.close(); } System.out.println("lstFileNo... "+lstFileNo); return lstFileNo; }
	 

	public Map getMapDeptWiseFileNo(String crNo, UserVO _userVO)
	{

		Map mapFielNo = new HashMap();
		// List lstFileNo=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);

			mapFielNo = objEpisodeDAO.getDeptWiseFileNo(crNo, _userVO);
			// mapFielNo.put(RegistrationConfig.LIST_DEPT_WISE_FILE_NO,lstFileNo);
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
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		System.out.println("mapFielNo... " + mapFielNo);
		return mapFielNo;
	}

	public EpisodeVO[] getMapDeptWiseFileNoChange(String crNo, UserVO _userVO)
	{

		Map mapFielNo = new HashMap();
		EpisodeVO[] episodevo;

		// List lstFileNo=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);

			episodevo = objEpisodeDAO.getDeptWiseFileNoChange(crNo, _userVO);
			// mapFielNo.put(RegistrationConfig.LIST_DEPT_WISE_FILE_NO,lstFileNo);
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		System.out.println("mapFielNo... " + mapFielNo);
		return episodevo;
	}

	/*public EpisodeVO specialClinicNewPatRegistration(EpisodeVO _episodeVO, PatientVO _patientVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			// /Creating DAO's of effected entities
			PatientDAO patDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			EpisodeReferDAO episodeReferDAO = new EpisodeReferDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			// creating VO for DAO's
			EpisodeReferVO _episodeReferVO = new EpisodeReferVO();
			DailyPatientVO dailyPatVO = new DailyPatientVO();
			synchronized (patDao.getLock())
			{
				// Specifying whether DOB is actual or not by checking if age is entered or DOB is entered...
				if (!_patientVO.getPatAge().trim().equals("")) _patientVO.setIsActualDob(RegistrationConfig.IS_ACTUAL_DOB_FALSE);
				else _patientVO.setIsActualDob(RegistrationConfig.IS_ACTUAL_DOB_TRUE);

				_patientVO.setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
				_patientVO.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_NOT_ADMITTED);
				_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				_patientVO.setPatRegCatCode(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL);
				_patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);

				// creating row in patient dtl table
				_patientVO = patDao.create(_patientVO, _userVO);

				_patientVO.getPatAddress().setPatCrNo(_patientVO.getPatCrNo());

				// Create a new Address
				_patientVO.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.getPatAddress().setSeatId(_userVO.getSeatId());
				addDao.createNewAddress(_patientVO.getPatAddress(), _userVO);

				HelperMethods.populate(dailyPatVO, _patientVO);
				dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);

				PatientBOHelper.setNewPatRegEpisodeVO(_episodeVO);

				_episodeVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
				_episodeVO.setPatCrNo(dailyPatVO.getPatCrNo());
				_episodeVO.setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
				_episodeVO.setIsValid(Config.IS_VALID_ACTIVE);

				HelperMethods.populate(dailyPatVO, _episodeVO);
				dailyPatVO.setIsReferred(_patientVO.getIsReferred());

				// Create a new entry in daily patient detail
				// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO

				dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
				dailyPatVO = dailyPatDao.create(dailyPatVO, _userVO);

				HelperMethods.populate(_episodeVO, dailyPatVO);
				// Create new entry in Episode detail

				_episodeVO.setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);

				if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{

					_episodeVO.setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					_episodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
				}
				else _episodeVO.setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);

				episodeDao.create(_episodeVO, _userVO);

				String deptCode = _episodeVO.getDepartmentCode();
				String roomCode = _episodeVO.getRoomCode();
				String deptUnitCode = _episodeVO.getDepartmentUnitCode();

				List al = new ArrayList();
				al = essentialDAO.getNameValues(deptCode, roomCode, deptUnitCode, _userVO);
				_episodeVO.setDepartment(al.get(0).toString());
				_episodeVO.setRoom(al.get(1).toString());
				_episodeVO.setDepartmentUnit(al.get(2).toString());

				if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					// _patientVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					System.out.println("_episodeReferVO.getIsRefferInOut():  " + _episodeReferVO.getIsRefferInOut());
					HelperMethods.populate(_episodeReferVO, _patientVO);
					HelperMethods.populate(_episodeReferVO, _episodeVO);
					System.out.println("_episodeReferVO.getEpisodeVisitNo():::" + _episodeReferVO.getEpisodeVisitNo());
					episodeReferDAO.create(_episodeReferVO, _userVO);
				}

			}
		}

		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _episodeVO; // <<<
	}

	public EpisodeVO[] getDeptWiseRenewalOfRegistration(String crNo, UserVO _userVO, String patPrimaryCode)
	{

		Map mapFielNo = new HashMap();
		EpisodeVO[] episodevo;

		// List lstFileNo=null;
		System.out.println("0");
		String renewalCharge = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);

			episodevo = objEpisodeDAO.getDeptWiseRenewalOfRegistration(crNo, _userVO);

			
			 * renewalCharge=essentialDAO.getBillAmountBasedOnCategory(patPrimaryCode,_userVO); for(int i=0;i<episodevo.length;i++){
			 * episodevo[i].setPatAmountCollected(renewalCharge); }
			 

		}
		catch (HisRenewalRequiredException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRenewalRequiredException(e.getMessage());
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return episodevo;
	}

	public String getNewDeptVisitAmount(String crNo, String primCatCode, String deptCode, UserVO _userVO)
	{

		System.out.println("inside getNewDeptVisitAmount in PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String depts[];
		String[] deptForCollection;
		String amount;
		boolean val = false;
		try
		{
			tx.begin();
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);
			EssentialDAO objEss = new EssentialDAO(tx);
			depts = objEpisodeDAO.getDeptForRenewal(crNo, _userVO);
			if (depts != null)
			{
				for (int i = 0; i < depts.length; i++)
				{
					if (deptCode.equals(depts[i]))
					{
						System.out.println("renewal required");
						throw new HisRenewalCardRequired();
						// val=true;
						// break;
					}
				}

			}
			
			 * deptForCollection=objEpisodeDAO.getDeptNoCollectionAtNewDeptVisit(crNo, _userVO); if(deptForCollection!=null){
			 * for(int i=0;i<deptForCollection.length;i++){ if (deptCode.equals(deptForCollection[i])){
			 * //System.out.println("renewal required"); val=true; break; } } } if (val==false)
			 * amount=objEss.getBillAmountBasedOnCategory(primCatCode, _userVO); else amount="";
			 
			amount = objEss.getBillAmountBasedOnCategory(primCatCode, _userVO);

		}
		catch (HisRenewalCardRequired e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRenewalCardRequired();
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
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return amount;
	}

	public String[] getDeptsForRenewal(String crNo, UserVO _userVO)
	{
		System.out.println("inside getDeptsForRenewal in PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String arrDept[];
		try
		{
			tx.begin();
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);
			arrDept = objEpisodeDAO.getDeptForRenewal(crNo, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
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
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrDept;
	}

	public boolean saveRenewalDtl(EpisodeVO[] _episodeVO, UserVO _userVO)
	{
		System.out.println("inside changeSecondaryCategory(SecondaryCategoryChangeVO _secCatChangeVO, UserVO _userVO) of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean x = false;
		String[] epicodeAndVisitNo = null;
		HisDAO hisDAOObj=null;

		DuplicateRenewVO[] duplicateRenewVO = new DuplicateRenewVO[_episodeVO.length];
		try
		{
			System.out.println("Begining transaction");
			tx.begin();

			DuplicateRenewDAO duplicateRenewDAO = new DuplicateRenewDAO(tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			hisDAOObj = new HisDAO("Registration","PatientBO");
			// EssentialDAO essentialDAO=new EssentialDAO(tx);
			for (int i = 0; i < _episodeVO.length; i++)
			{
				RegChargeDtlVO regChargeDtlVO = new RegChargeDtlVO();
				epicodeAndVisitNo = episodeDAO.getEpisdoeCode(_episodeVO[i].getPatCrNo(), _episodeVO[i].getDepartmentUnitCode(), _userVO);
				_episodeVO[i].setEpisodeCode(epicodeAndVisitNo[0]);
				_episodeVO[i].setEpisodeVisitNo(epicodeAndVisitNo[1]);
				_episodeVO[i].setEpisodeVisitTypeCode(epicodeAndVisitNo[2]);
				HelperMethods.populate(duplicateRenewVO[i], _episodeVO[i]);
				duplicateRenewVO[i].setDuplicateRenewFlag(RegistrationConfig.OPD_CARD_RENEW);
				duplicateRenewVO[i].setIsValid(Config.IS_VALID_ACTIVE);

				// x=duplicateRenewDAO.create(_episodeVO[i],_userVO);
				HelperMethods.populate(regChargeDtlVO, _episodeVO[i]);
				regChargeDtlVO.setPatAmountCollected(_episodeVO[i].getPatAmountCollected());
				if (_episodeVO[i].getEpisodeVisitTypeCode().equalsIgnoreCase(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL))
				{
					regChargeDtlVO.setTariffId(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
				}
				regChargeDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				regChargeDtlVO.setTariffId(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
				regChargeDtlDAO.create(hisDAOObj,regChargeDtlVO, _userVO);
			}

			// x=episodeDAO.saveRenewalDtl(_episodeVO,_userVO);
			// essentialDAO.getBillAmountBasedOnCategory()

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			System.out.println("y = " + x);
			tx.close();
		}

		return x;
	}

	public void saveRenewalDetail(PatientVO _patientVO, String _amount, UserVO _userVO, String _newExpiryDate)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RenewalDetailVO renewDetailVO = new RenewalDetailVO();
		RegChargeDtlVO regChargeDtlVO = new RegChargeDtlVO();
		HisDAO hisDAOObj=null;
		try
		{
			tx.begin();
			PatientDAO patientDAO = new PatientDAO(tx);
			RenewalDetailDAO renewalDetailDAO = new RenewalDetailDAO(tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(tx);
			hisDAOObj = new HisDAO("Registration","PatientBO");
			// //update hrgt_patient_dtl//////
			patientDAO.updateExpiryDate(_patientVO.getPatCrNo(), _newExpiryDate, _userVO);
			// ///update hrgt_renew_dtl////
			renewDetailVO.setPatCrNo(_patientVO.getPatCrNo());
			renewDetailVO.setSeatId(_userVO.getSeatId());
			renewDetailVO.setIsValid(Config.IS_VALID_ACTIVE);
			renewDetailVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
			renewDetailVO.setEntryDate(_patientVO.getEntryDate());
			renewDetailVO.setDepartmentCode(_patientVO.getDepartmentCode());
			renewDetailVO.setDepartmentUnitCode(_patientVO.getDepartmentUnitCode());
			renewDetailVO.setHospitalCode(_userVO.getHospitalCode());
			renewDetailVO.setOldExpiryDate(_patientVO.getExpiryDate());
			renewDetailVO.setNewExpiryDate(_newExpiryDate);
			renewDetailVO.setRenewalType(Config.RENEWAL_TYPE);

			renewalDetailDAO.create(renewDetailVO, _userVO);

			// ///update hrgt_reg_charge_dtl//////
			regChargeDtlVO.setPatAmountCollected(_amount);
			regChargeDtlVO.setPatCrNo(_patientVO.getPatCrNo());
			regChargeDtlVO.setPatPrimaryCatCode(_patientVO.getPatPrimaryCatCode());
			regChargeDtlVO.setPatSecondaryCatCode(_patientVO.getPatSecondaryCatCode());
			regChargeDtlVO.setSeatId(_userVO.getSeatId());
			regChargeDtlVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
			regChargeDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
			regChargeDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
			regChargeDtlVO.setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
			if (!_amount.equals("0"))
			{
				regChargeDtlDAO.create(hisDAOObj,regChargeDtlVO, _userVO);
			}

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{

			tx.close();
		}

	}

	/*public void saveDeptWiseRenewalDetail(EpisodeVO[] selectedEpisodeVO, String sysDate, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		RenewalDetailVO renewDetailVO = new RenewalDetailVO();
		RegChargeDtlVO regChargeDtlVO = new RegChargeDtlVO();
		DirectChageDetailVO directChargeVO=new DirectChageDetailVO();
		try
		{

			tx.begin();

			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			GlobalEssentialDAO globalEssentialDAO = new GlobalEssentialDAO(tx);
			String selectedcode = "";
			String newExpiryDate = "";
			for (int i = 0; i < selectedEpisodeVO.length; i++)
			{
				if (Config.RENEWAL_TYPE.equals("3"))
				{
					Date dt = new Date();
					dt = globalEssentialDAO.getSystemDate(dt);
					int month = dt.getMonth() + 1;
					if (month <= 6) newExpiryDate = Config.HOSPITAL_RENEWAL_EXPIRY_MONTH_FIRST;
					if (month > 6 && month <= 12) newExpiryDate = Config.HOSPITAL_RENEWAL_EXPIRY_MONTH_SECOND;

				}
				if (Config.RENEWAL_TYPE.equals("4"))
				{
					newExpiryDate = WebUTIL.addDate(sysDate, Integer.parseInt(Config.HOSPITAL_RENEWAL_EXPIRY_DAY));
					newExpiryDate = newExpiryDate.substring(0, 5);
					newExpiryDate = newExpiryDate.replace("/", "-");
				}
				if (Config.RENEWAL_TYPE.equals("5"))
				{
					String[] renewalDetails = essentialDAO.getExpiryDtlForRenewal(selectedEpisodeVO[i].getDepartmentUnitCode());
					String days = renewalDetails[0];
					String month = renewalDetails[1];
					String isExpiry = renewalDetails[2];
					if (isExpiry.equals("0")) throw new HisRenewalRequiredException();
					if (isExpiry.equals("1")) newExpiryDate = month;
					if (isExpiry.equals("2"))
					{
						newExpiryDate = WebUTIL.addDate(sysDate, Integer.parseInt(days));
						newExpiryDate = newExpiryDate.substring(0, 5);
						newExpiryDate = newExpiryDate.replace("/", "-");
					}

				}
				// //update episode detail table////
				episodeDAO.saveDeptWiseRenewalDtl(selectedEpisodeVO[i].getPatCrNo(), selectedEpisodeVO[i].getEpisodeCode(), selectedEpisodeVO[i]
						.getEpisodeVisitNo(), newExpiryDate, userVO);
				// ///update HRGT_RENEW_DTL////

				renewDetailVO.setPatCrNo(selectedEpisodeVO[i].getPatCrNo());
				renewDetailVO.setSeatId(userVO.getSeatId());
				renewDetailVO.setIsValid(Config.IS_VALID_ACTIVE);
				renewDetailVO.setSystemIPAddress(selectedEpisodeVO[i].getSystemIPAddress());
				renewDetailVO.setEntryDate(selectedEpisodeVO[i].getEntryDate());
				renewDetailVO.setDepartmentCode(selectedEpisodeVO[i].getDepartmentCode());
				renewDetailVO.setDepartmentUnitCode(selectedEpisodeVO[i].getDepartmentUnitCode());
				renewDetailVO.setHospitalCode(userVO.getHospitalCode());
				renewDetailVO.setOldExpiryDate(selectedEpisodeVO[i].getExpiryDate());
				renewDetailVO.setNewExpiryDate(newExpiryDate);
				renewDetailVO.setRenewalType(Config.RENEWAL_TYPE);

				renewDetailDAO.create(renewDetailVO, userVO);
				// ///update hrgt_reg_charge_dtl//////
				regChargeDtlVO.setPatAmountCollected(selectedEpisodeVO[i].getPatAmountCollected());
				regChargeDtlVO.setPatCrNo(selectedEpisodeVO[i].getPatCrNo());
				regChargeDtlVO.setPatPrimaryCatCode(selectedEpisodeVO[i].getPatPrimaryCatCode());
				regChargeDtlVO.setPatSecondaryCatCode(selectedEpisodeVO[i].getPatSecondaryCatCode());
				regChargeDtlVO.setSeatId(userVO.getSeatId());
				regChargeDtlVO.setSystemIPAddress(selectedEpisodeVO[i].getSystemIPAddress());
				regChargeDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
				regChargeDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				regChargeDtlVO.setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
				regChargeDtlVO.setModuleId(Config.MODULE_ID_EMERGENCY);
				regChargeDtlVO.setDepartmentCode(selectedEpisodeVO[i].getDepartmentCode());
				regChargeDtlVO.setDepartmentUnitCode(selectedEpisodeVO[i].getDepartmentUnitCode());
				regChargeDtlVO.setEpisodeCode(selectedEpisodeVO[i].getEpisodeCode());
				regChargeDtlVO.setAdmissionNo(selectedEpisodeVO[i].getAdmissionNo());
				regChargeDtlVO.setEpisodeVisitNo(selectedEpisodeVO[i].getEpisodeVisitNo());
				if (!(regChargeDtlVO.getPatAmountCollected().equals("0") || regChargeDtlVO.getPatAmountCollected().equals("")
						|| regChargeDtlVO .getPatAmountCollected() == null))
				{
					///registration module billing
					regChargeDtlDAO.create(regChargeDtlVO, userVO);
					HelperMethods.populate(directChargeVO, regChargeDtlVO);
					directChargeDao.create(directChargeVO, userVO);
					////billing module integration
				//	regChargeDtlDAO.createBillinProcedure(regChargeDtlVO, userVO);
				}
				if (!regChargeDtlVO.getPatAmountCollected().equals("0"))
				{
					regChargeDtlDAO.create(regChargeDtlVO, userVO);
				}
			}

		}
		catch (HisRenewalRequiredException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRenewalRequiredException();
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{

			tx.close();
		}

	}

	public EpisodeVO[] saveNewSpVisiStamp(EpisodeVO[] episodeVO, UserVO userVO, PatientVO patientVO, RegChargeDtlVO regChargeDtlVO,
			EpisodeRefDtlVO episodeRefDtlVO,PatientVO _oldPatientVO)
	{
		
		//Vijay Uniyal Emergency Visit Change Request
		boolean x = false;
		JDBCTransactionContext tx =	new JDBCTransactionContext();
		
		HisDAO hisDAOObj=null;
		try
		{
			tx.begin();

			hisDAOObj = new HisDAO("Registration","PatientBO");
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			PatientDAO  patDao=new PatientDAO(tx);
			PatientAuditDAO patAuditDAO=new PatientAuditDAO(tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
		//	List arraylist = null;
		////updating patient detail if department wise mandatory fields are captured
			/*hide this fro arogya online if(_oldPatientVO!=null)
			{
			patDao.updateWithoutAge(patientVO, userVO);
			patAuditDAO.create(_oldPatientVO, userVO);
			}
			
			// //////////////////////////////////////////
			for (int i = 0; i < episodeVO.length; i++)
			{
				String referMlcNo=episodeRefDtlVO.getMlcNo();
				// episodeVO[i].setEpisodeVisitTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
				
				//episodeVO[i].setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
				//in case of new special visit is old is false
				episodeVO[i].setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
				episodeVO[i].setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);
				episodeVO[i].setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
				episodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
				episodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);

				// ///////////////////////////////////////////

				DailyPatientVO dailyPatVO = new DailyPatientVO();
				HelperMethods.populate(dailyPatVO, episodeVO[i]);
				HelperMethods.populate(dailyPatVO, patientVO);
				//dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
				//in case of new special visit is old is false
				dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
				dailyPatVO.setDepartmentUnitCode(episodeVO[i].getDepartmentUnitCode());
				dailyPatVO.setDepartmentCode(episodeVO[i].getDepartmentCode());
				dailyPatVO.setPatAmountCollected(regChargeDtlVO.getPatAmountCollected());
				dailyPatVO.setIsReferred(patientVO.getIsReferred());

				disable room usability for arogya online if (patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					if (episodeRefDtlVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
					{
						dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL);
					}
					else if (episodeRefDtlVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
					{
						dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
					}
					if (patientVO.getIsPatReferByList() != null
							&& patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
					{
						dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
					}

				}
				else
					dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
				
				dailyPatVO.setIsNewFileRequired(Config.FILE_NO_GENERATION_FLAG);
				dailyPatVO.setEpisodeVisitNo("1");
				
				//new method with mlc refer logic
				//dailyPatVO = dailyPatDao.createNewDepartment(hisDAOObj,dailyPatVO, userVO, referMlcNo);
				if(dailyPatVO.getPatDocType()!=null&&dailyPatVO.getPatDocType().indexOf("|")>0)
				{
					dailyPatVO.setPatDocType(dailyPatVO.getPatDocType().split("\\|")[0]);
				}
				dailyPatVO = dailyPatDao.createNewSplUnit(hisDAOObj,dailyPatVO, userVO, referMlcNo);
				
				///old method
				//dailyPatVO = dailyPatDao.create(dailyPatVO, userVO);
			//	HelperMethods.populate(episodeVO[i], dailyPatVO);
				
				//Create new entry in Episode detail
				//episodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);

				// Create new entry in Episode detail
				episodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);


				if (patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					if (episodeRefDtlVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
					{
						episodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
						episodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					}
					else if (episodeRefDtlVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
					{
						episodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
						episodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL);
					}
					

				}
				else
				{
					episodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
					episodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
				}
				HelperMethods.populate(episodeVO[i], dailyPatVO);
				// episodeVO[i]=episodeDao.create(episodeVO[i], userVO);
				if (patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD))
				{
					episodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
					episodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
				}
				else
				{
					if (patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_SPECIAL_CLINIC))
					{
						episodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
						episodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
						//episodeVO[i].set
					}
					else
					{
						if (patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_EMERGENCY_CLINIC))
						{
							if(patientVO.getIsMLC().equalsIgnoreCase(RegistrationConfig.IS_MLC_TRUE)){
								episodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC); 
								episodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC);
								}
								else{
									episodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC);
									episodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG);
								}
							//episodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG);
						}
					}
				}
				episodeVO[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
				
					////setting visit number//
				
				episodeVO[i].setEpisodeVisitNo("1");
				//saving episode dtl
				episodeVO[i].setEntryDate(patientVO.getSystemDate());
				if(referMlcNo!=null && !referMlcNo.equals(""))
				{
					episodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
					episodeVO[i].setMlcNo(referMlcNo);
				}
				
				//diable this for arogya online PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(episodeVO[i], userVO, tx);
				//changed on 14-feb-2012 by prakhar misra
				//to calculate expiry date out of procedure so that it cold be used for prinintg
				String expiryDate=episodeDao.getExpiryDate(episodeVO[i],userVO);
				
				episodeVO[i].setExpiryDate(expiryDate);
				episodeVO[i]=  episodeDao.createNewRegistration(hisDAOObj,episodeVO[i], userVO);
				
				if (patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD)
						|| patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_SPECIAL_CLINIC))
				{
					arraylist = essentialDAO.getNameValues(episodeVO[i].getDepartmentCode(), episodeVO[i].getRoomCode(), episodeVO[i]
							.getDepartmentUnitCode(), userVO);

					episodeVO[i].setDepartment(arraylist.get(0).toString());
					episodeVO[i].setRoom(arraylist.get(1).toString());
					episodeVO[i].setDepartmentUnit(arraylist.get(2).toString());
				}
				else
				{
					arraylist = essentialDAO.getNameValuesEmergency(episodeVO[i].getDepartmentCode(), episodeVO[i].getDepartmentUnitCode());
					episodeVO[i].setDepartment(arraylist.get(0).toString());
					episodeVO[i].setDepartmentUnit(arraylist.get(1).toString());
				}
				
				if (patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
				{
					// _patientVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					// System.out.println("_episodeReferVO.getIsRefferInOut(): "+_episodeReferVO.getIsRefferInOut());
					// HelperMethods.populate(_episodeReferVO, _patientVO);
					// HelperMethods.populate(_episodeReferVO, _arrEpisodeVO[i]);
					// ///////episode referDTL////////////////
					
					 * String[] array = episodeDao.getEpisdoeCode(episodeVO[i].getPatCrNo(), episodeVO[i]
					 * .getDepartmentUnitCode(), userVO); episodeVO[i].setEpisodeVisitNo(array[2]);
					 
					String refEpisode = episodeRefDtlVO.getEpisodeCode();
					String refEpisodeVisit = episodeRefDtlVO.getEpisodeVisitNo();
					String serialNo = episodeRefDtlVO.getSerialNo();
					HelperMethods.populate(episodeRefDtlVO, episodeVO[i]);
					episodeRefDtlVO.setSystemIPAddress(patientVO.getSystemIPAddress());
					if (episodeRefDtlVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
					{
						episodeRefDtlVO.setEpisodeCode(episodeVO[i].getEpisodeCode());
						episodeRefDtlVO.setEpisodeVisitNo("1");
					}
					if (episodeRefDtlVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
					{
						episodeRefDtlVO.setToDepartmentCode(episodeVO[i].getDepartmentCode());
						episodeRefDtlVO.setToDepartmentUnitCode(episodeVO[i].getDepartmentUnitCode());
						episodeRefDtlVO.setToEpisodeCode(episodeVO[i].getEpisodeCode());
						episodeRefDtlVO.setToEpisodeVisitNo("1");
						episodeRefDtlVO.setEpisodeCode(refEpisode);
						episodeRefDtlVO.setEpisodeVisitNo(refEpisodeVisit);
						episodeRefDtlVO.setExternalHospitalCode("");
						episodeRefDtlVO.setSerialNo(serialNo);
					}
					if (patientVO.getIsPatReferByList() != null
							&& patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
					{
						episodeRefDtlDAO.updateAcceptanceDate(hisDAOObj,episodeRefDtlVO, userVO);
						//episodeRefDtlDAO.updateSCAcceptanceDate(episodeRefDtlVO, userVO);
					}
					else
					{

						episodeRefDtlDAO.create(hisDAOObj,episodeRefDtlVO, userVO);
					}

				}
				HelperMethods.populate(regChargeDtlVO, episodeVO[i]);
				regChargeDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				regChargeDtlVO.setTariffId(userVO.getTariffID());
				regChargeDtlVO.setPatAmountCollected(dailyPatVO.getPatAmountCollected());
				if (!(regChargeDtlVO.getPatAmountCollected() == null || regChargeDtlVO.getPatAmountCollected().equals("") || regChargeDtlVO
						.getPatAmountCollected().equals("0"))) 
				{
					////registration module billing
					regChargeDtlDAO.create(hisDAOObj,regChargeDtlVO, userVO);
					DirectChageDetailVO directChargeVO=new DirectChageDetailVO();
					HelperMethods.populate(directChargeVO, regChargeDtlVO);
					directChargeDao.create(hisDAOObj,directChargeVO, userVO);
					////billing module integration
				//	regChargeDtlDAO.createBillinProcedure(regChargeDtlVO, userVO);
				}
				
				
					
					
					
					episodeVO[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_CASUALTIY, episodeVO[i].getDepartmentUnitCode(), userVO));
					
					
				
				
				//episodeVO[i].setUnitWorkingDays(essentialDAO.getUnitWorkingDays(episodeVO[i].getDepartmentUnitCode(),userVO));
				
				
				
				
			}

			synchronized (hisDAOObj) 
			{
			   hisDAOObj.fire();
			}		
			
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisAppointmentNotAvailableException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisAppointmentNotAvailableException();
		}
		catch(HisInvalidTokenNumberException e){
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisInvalidTokenNumberException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			if (hisDAOObj != null) {
				hisDAOObj.free();
				hisDAOObj = null;
			}
			tx.close();
		}

		return episodeVO;
	}

	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and episode not closed.
	 * Provides last visit details of all episodes which are open presently.
	 * 
	 * @param _patientVO Provides CR No of the patient for which episode details are to be searched.
	 * @param _userVO Provides User details.
	 * @param visitType Specifies type of visit, namely, IPD, OPD, or EMG.
	 * @return Array of EpisodeVO with values stored in DB.
	 
	public EpisodeVO[] searchOldPatientEpisodesByCrNo(String crNo, UserVO _userVO, String visitType)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO =
		{};
		
		EpisodeVO[] arrVO =
		{};
		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_arrEpisodeVO = episodeDao.retrieveOldPatientEpisodes(crNo, _userVO);
			EpisodeVO[] _tmpArrEpisodeVO = new EpisodeVO[_arrEpisodeVO.length];
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]
			{};

			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				_arrEpisodeVO[i].setRenewalType(Config.RENEWAL_TYPE);
				//////31-dec-2008
				////Checking for Episode_VISIT_TYPE 
				////Earlier visit type was passed as argument "visitType"
				///Now Visit Type is RegistrationConfig.EPISODE_VISIT_TYPE_OPD 
				//and RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL
				if (_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD) || 
						_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
				{
					_tmpArrEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++)
					{
						_tmpArrEpisodeVO[k] = arrVO[k];
					}
					_tmpArrEpisodeVO[len - 1] = _arrEpisodeVO[i];
					arrVO = _tmpArrEpisodeVO;

					for (int k = 0; k < arrVO.length; k++)
					{
						System.out.println("i= " + i + " k= " + k + "  arrVO...1" + arrVO[k].getEpisodeCode());
					}

					//arrVO[lenz	-1]=_arrEpisodeVO[i];
					_tmpArrEpisodeVO[j] = _arrEpisodeVO[i];
					j++;
					//_tmpArrEpisodeVO=arrVO;
					len++;
				}
			}
			
			int m, n;
			for (m = 0, n = 0; m < _tmpArrEpisodeVO.length; m++, n++)
			{
				_arrEpisodeVO[n] = _tmpArrEpisodeVO[m];
			
			}
			if (arrVO.length == 0)
			{
				throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
			}
			//}
		}//end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return arrVO;
	}
	
	public EpisodeVO[] searchOldPatientEmgEpisodesByCrNo(String crNo, UserVO _userVO, String visitType)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO ={};

		EpisodeVO[] arrVO =	{};
		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_arrEpisodeVO = episodeDao.retrieveOldEmgPatientEpisodes(crNo, _userVO);
			EpisodeVO[] _tmpArrEpisodeVO = new EpisodeVO[_arrEpisodeVO.length];
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]	{};

			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				_arrEpisodeVO[i].setRenewalType(Config.RENEWAL_TYPE);
				//////31-dec-2008
				////Checking for Episode_VISIT_TYPE 
				////Earlier visit type was passed as argument "visitType"
				///Now Visit Type is RegistrationConfig.EPISODE_VISIT_TYPE_OPD 
				//and RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL
				if (_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG) || 
						_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC))
				{
					_tmpArrEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++)
					{
						_tmpArrEpisodeVO[k] = arrVO[k];
					//	System.out.println("arrVO" + arrVO[k].getEpisodeCode());
					}
					_tmpArrEpisodeVO[len - 1] = _arrEpisodeVO[i];
					arrVO = _tmpArrEpisodeVO;

					for (int k = 0; k < arrVO.length; k++)
					{
						System.out.println("i= " + i + " k= " + k + "  arrVO...1" + arrVO[k].getEpisodeCode());
					}

					// arrVO[len-1]=_arrEpisodeVO[i];
					_tmpArrEpisodeVO[j] = _arrEpisodeVO[i];
					j++;
					// _tmpArrEpisodeVO=arrVO;
					len++;
				}
			}
			int m, n;
			for (m = 0, n = 0; m < _tmpArrEpisodeVO.length; m++, n++)
			{
				_arrEpisodeVO[n] = _tmpArrEpisodeVO[m];
			}
			if (arrVO.length == 0)
			{
				throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
			}
			// }
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return arrVO;
	}


	public EpisodeVO[] getDeptWiseRenewalOfRegistration(String crNo, String primCatCode, UserVO _userVO)
	{
		return null;
	}

	public boolean saveRenewalDtl(EpisodeVO _episodeVO, UserVO _userVO)
	{
		return false;
	}

	public DailyPatientVO searchDailyPatientByCrNo(DailyPatientVO dailyPatientVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
		try
		{
			//System.out.println("Begining transaction");
			tx.begin();
			dailyPatientVO = dailyPatientDAO.searchPatientByCrNo(dailyPatientVO, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside HisRecordNotFoundException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisNotAnOPDcaseException e)
		{
			System.out.println("inside HisNotAnOPDcaseException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			System.out.println("inside HisNotAnIPDcaseException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			System.out.println("inside HisDeadPatientException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return dailyPatientVO;
	}
	
	public DailyPatientVO searchDailyPatientByCrNoWithoutHospital(DailyPatientVO dailyPatientVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
		try
		{
			//System.out.println("Begining transaction");
			tx.begin();
			dailyPatientVO = dailyPatientDAO.searchPatientByCrNoWithoutHospital(dailyPatientVO, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside HisRecordNotFoundException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisNotAnOPDcaseException e)
		{
			System.out.println("inside HisNotAnOPDcaseException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			System.out.println("inside HisNotAnIPDcaseException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			System.out.println("inside HisDeadPatientException of searchOldPatientEpisodesByCrNo in PatientBO");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return dailyPatientVO;
	}

/*	public boolean patDtlModificationMRD(PatientVO _patientVO,PatientVO _patientVOOldData, UserVO _userVO, VerificationDocumentVO _verDoc)
	{

		int x = 0;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String verficationSNo = "";
		try
		{
			tx.begin();
			PatientAuditDAO patAuditDao=new PatientAuditDAO(tx);
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			VerificationDocumentUsedDAO verificationDocUsedDao = new VerificationDocumentUsedDAO(tx);
			// System.out.println("_addressVO.getPatCrNo():: "+_addressVO.getPatCrNo());

			
			 * if(_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
			 * _patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
			 
		
			verficationSNo = verificationDocUsedDao.selectNextSerialNoForPUK(_verDoc, _userVO);

			if (_patientVO.getPatAddress().getIsAddressDelhi().equals(RegistrationConfig.IS_ADDRESS_DELHI_FALSE)) 
				_patientVO.getPatAddress().setPatAddCityLocCode("");

			_patientVO.setVerificationDocumentId(verficationSNo);
			
			if(!_patientVO.getIsActualDob().equals(_patientVOOldData.getIsActualDob())){
				if(_patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
						&& _patientVO.getPatAge().equals(_patientVOOldData.getPatAge())
						&& _patientVO.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) ){
					x = patientDao.updateWithoutAge(_patientVO, _userVO);
				}
				else{
					x = patientDao.update(_patientVO, _userVO);
				}
			}
			else{
				x = patientDao.update(_patientVO, _userVO);
			}	
			
			//update dob and age
			if(_patientVO.getIsActualDob().equals(_patientVOOldData.getIsActualDob())){
				if(_patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
						&& _patientVO.getPatAge().equals(_patientVOOldData.getPatAge())
						&& _patientVO.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) ){
					x = patientDao.updateWithoutAge(_patientVO, _userVO);
				}
				else{
					x = patientDao.update(_patientVO, _userVO);
				}
			}
			else{
				if(_patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
						&& _patientVO.getPatAge().equals(_patientVOOldData.getPatAge())
						&& _patientVO.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) ){
					x = patientDao.updateWithoutAge(_patientVO, _userVO);
				}
				else  x = patientDao.update(_patientVO, _userVO);
			}
			/////////////////////////////////////
			
			if (x >= 1)
			{
				System.out.println("update addressVO");
				AddressVO addressVO = _patientVO.getPatAddress();
				addressVO.setRequestBy(_patientVO.getRequestBy());
				addressVO.setRequestByAddress(_patientVO.getRequestByAddress());
				addressVO.setRequestByName(_patientVO.getRequestByName());
				addressVO.setRequestRelation(_patientVO.getRequestRelation());
				addressVO.setConstableBadgeNo(_patientVO.getConstableBadgeNo());
				addressVO.setConstableDesig(_patientVO.getConstableDesig());
				addressVO.setVerificationDocumentId(verficationSNo);

				// ///////Change in updation of address details///////////
				addressVO.setIsValid(Config.IS_VALID_ACTIVE);
				// addDao.update(_arrAddressVO, _userVO);
				x = addDao.updatePreviousRow(addressVO, _userVO);
				addDao.insertNewRowAddress(addressVO, _userVO);

				//////////////////////////////////////////////
				
				///////Audit Of Patient Detail
				
				patAuditDao.create(_patientVOOldData, _userVO);
				/////////////////////
				//x = addDao.update(addressVO, _userVO);

				// ////////////////////////////////////////////

				// x = addDao.update(addressVO, _userVO);

			
			}
			if (_patientVOOldData.getImageFile() != null && _patientVO.getImageFile() != null)
			{
				String sno = patientImageDtlDAO.selctMaxSerialNo(_patientVO.getPatCrNo(), _userVO);
				HelperMethods.populate(patientImageDtlVO, _patientVO);
				patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#" + sno);
				HelperMethods.storeImageInCorrectFileSystem(_patientVO.getImageFile(), _patientVO.getImageFileName(), patientImageDtlVO.getFileNo(),
						Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
				boolean flag =HisFileControlUtil.isWindowsOS();
				  if(flag)
				  {
					  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
				  }
				  else
				  {
					  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
				  }
				//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
				patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
				//update the is default flag of old image
				patientImageDtlVO.setSerialNo(_patientVOOldData.getImageFileName().split("#")[1]);
				patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_FALSE);
				patientImageDtlDAO.modifyPatientImage(patientImageDtlVO, _userVO);
				//insert new default image
				patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
				patientImageDtlDAO.create(patientImageDtlVO, _userVO);
			}
			
			else if (_patientVO.getImageFile() != null)
			{
				String sno = patientImageDtlDAO.selctMaxSerialNo(_patientVO.getPatCrNo(), _userVO);
				HelperMethods.populate(patientImageDtlVO, _patientVO);
				patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#" + sno);
				HelperMethods.storeImageInCorrectFileSystem(_patientVO.getImageFile(), _patientVO.getImageFileName(), patientImageDtlVO.getFileNo(),
						Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
				boolean flag =HisFileControlUtil.isWindowsOS();
				  if(flag)
				  {
					  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
				  }
				  else
				  {
					  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
				  }
				//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
				patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
				patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
				patientImageDtlDAO.create(patientImageDtlVO, _userVO);
			}
			

			_verDoc.setIsValid(Config.IS_VALID_ACTIVE);
			verificationDocUsedDao.create(_verDoc, _userVO);
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

		System.out.println("x=    " + x);
		if (x >= 1) return true;
		else return false;
	}
	
	
	public boolean patDtlModificationMRD(PatientVO _patientVO,PatientVO _patientVOOldData, UserVO _userVO, VerificationDocumentVO _verDoc)
	{

		int x = 0;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String verficationSNo = "";
		HisDAO daoObj = null;
		try
		{
			daoObj = new HisDAO("Registration","PatientBO");
			tx.begin();
			
			PatientAuditDAO patAuditDao=new PatientAuditDAO(tx);
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			VerificationDocumentUsedDAO verificationDocUsedDao = new VerificationDocumentUsedDAO(tx);
			// System.out.println("_addressVO.getPatCrNo():: "+_addressVO.getPatCrNo());

			
			 * if(_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
			 * _patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
			 
		
			synchronized (daoObj)
			{
				verficationSNo = verificationDocUsedDao.selectNextSerialNoForPUK(_verDoc, _userVO);
	
				if (_patientVO.getPatAddress().getIsAddressDelhi().equals(RegistrationConfig.IS_ADDRESS_DELHI_FALSE)) 
					_patientVO.getPatAddress().setPatAddCityLocCode("");
	
				_patientVO.setVerificationDocumentId(verficationSNo);
				
				if(!_patientVO.getIsActualDob().equals(_patientVOOldData.getIsActualDob())){
					if(_patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& _patientVO.getPatAge().equals(_patientVOOldData.getPatAge())
							&& _patientVO.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) ){
						x = patientDao.updateWithoutAge(_patientVO, _userVO);
					}
					else{
						x = patientDao.update(_patientVO, _userVO);
					}
				}
				else{
					x = patientDao.update(_patientVO, _userVO);
				}	*
				
				//update dob and age
				if(_patientVO.getIsActualDob().equals(_patientVOOldData.getIsActualDob())){
					if(_patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& _patientVO.getPatAge().equals(_patientVOOldData.getPatAge())
							&& _patientVO.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) ){
						x = patientDao.updateWithoutAge(daoObj,_patientVO, _userVO);
					}
					else{
						x = patientDao.update(daoObj,_patientVO, _userVO);
					}
				}
				else{
					if(_patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& _patientVO.getPatAge().equals(_patientVOOldData.getPatAge())
							&& _patientVO.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) ){
						x = patientDao.updateWithoutAge(daoObj,_patientVO, _userVO);
					}
					else  x = patientDao.update(daoObj,_patientVO, _userVO);
				}
				/////////////////////////////////////
				
				if (x >= 1)
				{
					System.out.println("update addressVO");
					AddressVO addressVO = _patientVO.getPatAddress();
					addressVO.setRequestBy(_patientVO.getRequestBy());
					addressVO.setRequestByAddress(_patientVO.getRequestByAddress());
					addressVO.setRequestByName(_patientVO.getRequestByName());
					addressVO.setRequestRelation(_patientVO.getRequestRelation());
					addressVO.setConstableBadgeNo(_patientVO.getConstableBadgeNo());
					addressVO.setConstableDesig(_patientVO.getConstableDesig());
					addressVO.setVerificationDocumentId(verficationSNo);
	
					// ///////Change in updation of address details///////////
					addressVO.setIsValid(Config.IS_VALID_ACTIVE);
					// addDao.update(_arrAddressVO, _userVO);
					x = addDao.updatePreviousRow(daoObj,addressVO, _userVO);
					addDao.insertNewRowAddress(daoObj,addressVO, _userVO);
	
					//////////////////////////////////////////////
					
					///////Audit Of Patient Detail
					
					patAuditDao.create(daoObj,_patientVOOldData, _userVO);
					/////////////////////
					//x = addDao.update(addressVO, _userVO);
	
					// ////////////////////////////////////////////
	
					// x = addDao.update(addressVO, _userVO);
	
				
				}
				if (_patientVOOldData.getImageFile() != null && _patientVO.getImageFile() != null)
				{
					String sno = patientImageDtlDAO.selctMaxSerialNo(_patientVO.getPatCrNo(), _userVO);
					HelperMethods.populate(patientImageDtlVO, _patientVO);
					patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#" + sno);
					HelperMethods.storeImageInCorrectFileSystem(_patientVO.getImageFile(), _patientVO.getImageFileName(), patientImageDtlVO.getFileNo(),
							Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
					boolean flag =HisFileControlUtil.isWindowsOS();
					  if(flag)
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
					  }
					  else
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
					  }
					//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					//update the is default flag of old image
					patientImageDtlVO.setSerialNo(_patientVOOldData.getImageFileName().split("#")[1]);
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_FALSE);
					patientImageDtlDAO.modifyPatientImage(daoObj,patientImageDtlVO, _userVO);
					//insert new default image
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
					patientImageDtlDAO.create(daoObj,patientImageDtlVO, _userVO);
				}
				
				else if (_patientVO.getImageFile() != null)
				{
					String sno = patientImageDtlDAO.selctMaxSerialNo(_patientVO.getPatCrNo(), _userVO);
					HelperMethods.populate(patientImageDtlVO, _patientVO);
					patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#" + sno);
					HelperMethods.storeImageInCorrectFileSystem(_patientVO.getImageFile(), _patientVO.getImageFileName(), patientImageDtlVO.getFileNo(),
							Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
					boolean flag =HisFileControlUtil.isWindowsOS();
					  if(flag)
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
					  }
					  else
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
					  }
					//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
					patientImageDtlDAO.create(daoObj,patientImageDtlVO, _userVO);
				}
				
	
				_verDoc.setIsValid(Config.IS_VALID_ACTIVE);
				verificationDocUsedDao.create(daoObj,_verDoc, _userVO);
				daoObj.fire();
				
				PatientAdhaarDtlDAO patAdhaarDtlDao=new PatientAdhaarDtlDAO(tx);
				patAdhaarDtlDao.update(_patientVO, _userVO);
			}
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

		System.out.println("x=    " + x);
		if (x >= 1) return true;
		else return false;
	}

	public PatientVO[] searchPatientByContactNo(String contactNo, UserVO _userVO)
	{
		System.out.println("inside searchPatientByName() of PatientBO");
		// System.out.println("_crNo::"+ _crNo);
		PatientVO[] _patientVO = new PatientVO[]
		{};
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			System.out.println("creating object of PatientDAO");
			AddressDAO addDao = new AddressDAO(tx);
			System.out.println("creating object of AddressDAO");

			AddressVO[] _addressVO = new AddressVO[]
			{};
			// String str=_patientVO.getPatCrNo();
			// System.out.println("_patientVO.getPatCrNo()::str "+str);
			// populate the addressVO and patientVO with required details
			System.out.println("populate the  addressVO1111");
			// _patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			System.out.println("populate the  addressVO");
			try
			{
				_addressVO = addDao.retrieveByContactNo(contactNo, _userVO);
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println("Patient is unknown");
			}

			System.out.println("populate the  patientVO");
			_patientVO = patientDao.retrieveByContactNo(contactNo, _addressVO, _userVO);
			
			 * System.out.println("_patientVO.getPatAddTypeCode():::"+_patientVO.getPatAddTypeCode());
			 * System.out.println("_patientVO.getPatAddCityLocCode():::"+_patientVO.getPatAddCityLocCode());
			 * System.out.println("_patientVO.getIsActualDob():::"+_patientVO.getIsActualDob());
			 * System.out.println("_patientVO.getPatAge():::"+_patientVO.getPatAge());
			 * System.out.println("_patientVO.getEntryDate():::"+_patientVO.getEntryDate());
			 

			for (int i = 0; i < _patientVO.length; i++)
			{
				if (_patientVO[i].getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
				{
					String fname = "(Unknown)" + _patientVO[i].getPatFirstName();
					_patientVO[i].setPatFirstName(fname);
				}
				if (_patientVO[i].getPatAge() != null && _patientVO[i].getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE))
				{
					String patAgeWithUnit = _patientVO[i].getPatAge();
					System.out.println("patAgeWithUnit::" + patAgeWithUnit);
					int startidx = patAgeWithUnit.lastIndexOf(" ");
					String ageunit = patAgeWithUnit.substring(startidx + 1);
					patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
					_patientVO[i].setPatAge(patAgeWithUnit);
					System.out.println("_patientVO.getPatAge()1:::" + _patientVO[i].getPatAge());

					if (ageunit.equalsIgnoreCase("yr")) _patientVO[i].setPatAgeUnit("Y");
					if (ageunit.equalsIgnoreCase("mth")) _patientVO[i].setPatAgeUnit("M");
					if (ageunit.equalsIgnoreCase("d")) _patientVO[i].setPatAgeUnit("D");
				}
			}
			
			 * System.out.println("Patient dob::"+_patientVO.getPatDOB()); System.out.println("Patient
			 * isurban::"+_patientVO.getPatIsUrban()); System.out.println("after populate"); System.out.println("Patient
			 * first name::"+_patientVO.getPatFirstName());
			 * System.out.println("_patientVO.getPatAddCity()"+_patientVO.getPatAddCity());
			 
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside  BO Record not found exception");
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return (_patientVO);
	}

	public PatientVO[] searchPatientByNationalID(String nationalID, UserVO _userVO)
	{
		System.out.println("inside searchPatientByName() of PatientBO");
		// System.out.println("_crNo::"+ _crNo);
		PatientVO[] _patientVO = new PatientVO[]
		{};
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			System.out.println("creating object of PatientDAO");
			AddressDAO addDao = new AddressDAO(tx);
			System.out.println("creating object of AddressDAO");

			AddressVO[] _addressVO = new AddressVO[]
			{};
			// String str=_patientVO.getPatCrNo();
			// System.out.println("_patientVO.getPatCrNo()::str "+str);
			// populate the addressVO and patientVO with required details
			System.out.println("populate the  addressVO1111");
			// _patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			System.out.println("populate the  addressVO");
			try
			{
				_addressVO = addDao.retrieveByNationalID(nationalID, _userVO);
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println("Patient is unknown");
			}

			System.out.println("populate the  patientVO");
			_patientVO = patientDao.retrieveByNationalID(nationalID, _addressVO, _userVO);
			
			 * System.out.println("_patientVO.getPatAddTypeCode():::"+_patientVO.getPatAddTypeCode());
			 * System.out.println("_patientVO.getPatAddCityLocCode():::"+_patientVO.getPatAddCityLocCode());
			 * System.out.println("_patientVO.getIsActualDob():::"+_patientVO.getIsActualDob());
			 * System.out.println("_patientVO.getPatAge():::"+_patientVO.getPatAge());
			 * System.out.println("_patientVO.getEntryDate():::"+_patientVO.getEntryDate());
			 

			for (int i = 0; i < _patientVO.length; i++)
			{
				if (_patientVO[i].getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
				{
					String fname = "(Unknown)" + _patientVO[i].getPatFirstName();
					_patientVO[i].setPatFirstName(fname);
				}
				if (_patientVO[i].getPatAge() != null && _patientVO[i].getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE))
				{
					String patAgeWithUnit = _patientVO[i].getPatAge();
					System.out.println("patAgeWithUnit::" + patAgeWithUnit);
					int startidx = patAgeWithUnit.lastIndexOf(" ");
					String ageunit = patAgeWithUnit.substring(startidx + 1);
					patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
					_patientVO[i].setPatAge(patAgeWithUnit);
					System.out.println("_patientVO.getPatAge()1:::" + _patientVO[i].getPatAge());

					if (ageunit.equalsIgnoreCase("yr")) _patientVO[i].setPatAgeUnit("Y");
					if (ageunit.equalsIgnoreCase("mth")) _patientVO[i].setPatAgeUnit("M");
					if (ageunit.equalsIgnoreCase("d")) _patientVO[i].setPatAgeUnit("D");
				}
			}
			
			 * System.out.println("Patient dob::"+_patientVO.getPatDOB()); System.out.println("Patient
			 * isurban::"+_patientVO.getPatIsUrban()); System.out.println("after populate"); System.out.println("Patient
			 * first name::"+_patientVO.getPatFirstName());
			 * System.out.println("_patientVO.getPatAddCity()"+_patientVO.getPatAddCity());
			 
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside  BO Record not found exception");
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return (_patientVO);
	}

	public PatientVO[] searchPatientByEmployeeID(String employeeID, UserVO _userVO)
	{
		System.out.println("inside searchPatientByName() of PatientBO");
		// System.out.println("_crNo::"+ _crNo);
		PatientVO[] _patientVO = new PatientVO[]
		{};
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			System.out.println("creating object of PatientDAO");
			AddressDAO addDao = new AddressDAO(tx);
			System.out.println("creating object of AddressDAO");

			AddressVO[] _addressVO = new AddressVO[]
			{};

			try
			{
				_addressVO = addDao.retrieveByEmployeeID(employeeID, _userVO);
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println("Patient is unknown");
			}

			System.out.println("populate the  patientVO");
			_patientVO = patientDao.retrieveByEmployeeID(employeeID, _addressVO, _userVO);

			for (int i = 0; i < _patientVO.length; i++)
			{
				if (_patientVO[i].getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
				{
					String fname = "(Unknown)" + _patientVO[i].getPatFirstName();
					_patientVO[i].setPatFirstName(fname);
				}
				if (_patientVO[i].getPatAge() != null && _patientVO[i].getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE))
				{
					String patAgeWithUnit = _patientVO[i].getPatAge();
					System.out.println("patAgeWithUnit::" + patAgeWithUnit);
					int startidx = patAgeWithUnit.lastIndexOf(" ");
					String ageunit = patAgeWithUnit.substring(startidx + 1);
					patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
					_patientVO[i].setPatAge(patAgeWithUnit);
					System.out.println("_patientVO.getPatAge()1:::" + _patientVO[i].getPatAge());

					if (ageunit.equalsIgnoreCase("yr")) _patientVO[i].setPatAgeUnit("Y");
					if (ageunit.equalsIgnoreCase("mth")) _patientVO[i].setPatAgeUnit("M");
					if (ageunit.equalsIgnoreCase("d")) _patientVO[i].setPatAgeUnit("D");
				}
			}

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside  BO Record not found exception");
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return (_patientVO);
	}

	public EpisodeVO getEpisodeVisitByCrno(String _patCrNo, String _visitDate, String _unitCode,UserVO userVO)
	{

		EpisodeVO episodeVO = new EpisodeVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{

			tx.begin();
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			episodeVO = episodeDAO.getEpisodeVisitByCrno(_patCrNo, _visitDate, _unitCode,userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return (episodeVO);
	}

	public void saveDiagnosisDetails(EpisodeVO episodeVO, EpisodeDiagnosisVO[] episodeDiagnosisVO, UserVO userVO,String _episodeStatusInVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeCloseVO epiCloseVO=new EpisodeCloseVO();
		YellowSlipEntryDtlVO yellowSlipEntryDtlVO=new YellowSlipEntryDtlVO();
		try
		{
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			EpisodeCloseDAO episodeCloseDAO=new EpisodeCloseDAO(tx);
			YellowSlipEntryDAOi yellowSlipEntryDAO = new YellowSlipEntryDAO(tx);
				EpisodeActionDtlDAO episodeActionDAO = new EpisodeActionDtlDAO(tx);

			 EpisodeActionDtlDAO episodeActionDAO = new EpisodeActionDtlDAO(tx); 

			EpisodeDiagnosisDAO episodeDiagnosisDAO = new EpisodeDiagnosisDAO(tx);
			tx.begin();
			///Update Episode_dtl
			episodeDAO.saveDiagnosisDetails(episodeVO, userVO);
			
			//insert into episode_diagnosis_dtl
			for (int i = 0; i < episodeDiagnosisVO.length; i++)
			{
				 episodeActionDAO.create(episodeActionVO[i],userVO); 
				//episodeDiagnosisVO[i].setIsRepeat("0");
				episodeDiagnosisDAO.create(episodeDiagnosisVO[i], userVO);
			}
			//insert record int yellowslip_entry_dtl
			HelperMethods.populate(yellowSlipEntryDtlVO, episodeVO);
			String diagnosisTypeCode="";
			String diagnosisCode="";
			for (int i = 0; i < episodeDiagnosisVO.length; i++)
			{
				diagnosisTypeCode=diagnosisTypeCode+"#"+episodeDiagnosisVO[i].getDiagnosticTypeCode();
				diagnosisCode=diagnosisCode+"#"+episodeDiagnosisVO[i].getDiagnosticCode();
			}
			yellowSlipEntryDtlVO.setDiagnosticTypeCode(diagnosisTypeCode.replaceFirst("#", ""));
			yellowSlipEntryDtlVO.setDiagnosticCode(diagnosisCode.replaceFirst("#", ""));
			yellowSlipEntryDtlVO.setEntryMode(RegistrationConfig.YELLOW_SLIP_ENTRY_MODE_ENTRY);
			yellowSlipEntryDAO.save(yellowSlipEntryDtlVO, userVO);
			
			////Insert into episode_close_dtl
			if(_episodeStatusInVO.equals(RegistrationConfig.EPISODE_ISOPEN_TRUE))//To stop entry in close detail if the episode is alread closed
			{
			if(episodeVO.getEpisodeIsOpen().equals(RegistrationConfig.EPISODE_ISOPEN_FALSE))
				{
				//System.out.println("episodeTypecode in episodevo==========="+episodeVO.getEpisodeTypeCode());
				//System.out.println("episodeTypecode in epiCloseVO==========="+epiCloseVO.getEpisodeTypeCode());
				HelperMethods.populate(epiCloseVO, episodeVO);
				epiCloseVO.setEpisodeTypeCode(episodeVO.getEpisodeTypeCode());
				episodeCloseDAO.create(epiCloseVO, userVO);
				}
			}
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	public VerificationDocumentVO[] getVerificationDocumentDtl(VerificationDocumentVO verficationVo, UserVO userVO)
	{
		VerificationDocumentVO[] verificationDocumentVOs = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			VerificationDocumentUsedDAO verificationDocumentUsedDAO = new VerificationDocumentUsedDAO(tx);

			verificationDocumentVOs = verificationDocumentUsedDAO.retrieveByProcessIDAndCrNo(verficationVo, userVO);

		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			// System.out.println("_patientVO.getDepartment()::::"+_patientVO.getDepartment());

			tx.close();
		}
		return verificationDocumentVOs;
	}

	public MlcVO[] getMlcDtlArrayBasedOnCrNo(String crNo, UserVO _uservo)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MlcVO[] mlcVOs = null;
		try
		{
			
			tx.begin();

			MlcDAO mlcDao = new MlcDAO(tx);
			mlcVOs = mlcDao.getMlcDtlArrayBasedOnCrNo(crNo, _uservo);
			for (int i = 0; i < mlcVOs.length; i++)
			{
				if (mlcVOs[i].getIsImageUploaded() != null && mlcVOs[i].getIsImageUploaded().equalsIgnoreCase("1"))
				{
					System.out.println("image found :::::::::::");
					mlcVOs[i].setImageFile(HelperMethods.getByteArrayOfImage(Config.MLC_DOC_IMAGE_FILE_STORAGE_PATH + "\\" + mlcVOs[i].getPatCrNo()
							+ "$" + mlcVOs[i].getMlcNo() + "$" + mlcVOs[i].getEpisodeCode() + "$" + mlcVOs[i].getSerialNo() + ".jpg"));
					mlcVOs[i].setImageFileName(Config.PATIENT_IMAGE_FILE_STORAGE_PATH + "\\" + mlcVOs[i].getPatCrNo() + "$" + mlcVOs[i].getMlcNo()
							+ "$" + mlcVOs[i].getEpisodeCode() + "$" + mlcVOs[i].getSerialNo() + ".jpg");
				}
			}
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return mlcVOs;
	}

	public MlcVO[] getMlcDtlArrayBasedOnMlcNo(String mlcNo, UserVO _uservo)
	{
		System.out.println("inside getMlcDtlBasedOnMlcNo() of PatientBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MlcVO[] mlcVOs = null;
		try
		{
			System.out.println("Begining transaction");
			tx.begin();

			MlcDAO mlcDao = new MlcDAO(tx);
			System.out.println("creating object of MlcDAO");
			System.out.println("populate the  MlcVO");
			mlcVOs = mlcDao.getMlcDtlArrayBasedOnMlcNo(mlcNo, _uservo);
			for (int i = 0; i < mlcVOs.length; i++)
			{
				if (mlcVOs[i].getIsImageUploaded() != null && mlcVOs[i].getIsImageUploaded().equalsIgnoreCase("1"))
				{
					System.out.println("image found :::::::::::");
					mlcVOs[i].setImageFile(HelperMethods.getByteArrayOfImage(Config.MLC_DOC_IMAGE_FILE_STORAGE_PATH + "\\" + mlcVOs[i].getPatCrNo()
							+ "$" + mlcVOs[i].getMlcNo() + "$" + mlcVOs[i].getEpisodeCode() + "$" + mlcVOs[i].getSerialNo() + ".jpg"));
					mlcVOs[i].setImageFileName(Config.PATIENT_IMAGE_FILE_STORAGE_PATH + "\\" + mlcVOs[i].getPatCrNo() + "$" + mlcVOs[i].getMlcNo()
							+ "$" + mlcVOs[i].getEpisodeCode() + "$" + mlcVOs[i].getSerialNo() + ".jpg");
				}
			}
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return mlcVOs;
	}

	// getting data for online department visit

	public EpisodeRefDtlVO[] getReferPat(UserVO _userVO)
	{
		EpisodeRefDtlVO[] episodeRefDtlVO;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EpisodeRefDtlDAO objEpisodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			episodeRefDtlVO = objEpisodeRefDtlDAO.getReferPat(_userVO);
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeRefDtlVO;
	}

	// getting data for special clinic online department visit
	public EpisodeRefDtlVO[] getSCReferPat(UserVO _userVO)
	{
		EpisodeRefDtlVO[] episodeRefDtlVO;
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EpisodeRefDtlDAO objEpisodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			episodeRefDtlVO = objEpisodeRefDtlDAO.getSCReferPat(_userVO);
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
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
		return episodeRefDtlVO;
	}

	// ////////////search patient //////////////

	public PatientVO[] searchPatient(HashMap _searchMap, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO[] patVO = null;
		PatientDAO patientDao = new PatientDAO(tx);

		try
		{
			tx.begin();
			patVO = patientDao.searchPatient(_searchMap, _userVO);

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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patVO;
	}

	// * Saving Triage Details
	/*public void saveTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeTriageDetailDAO dao = new EpisodeTriageDetailDAO(tx);

		try
		{
			tx.begin();
			dao.create(_triVO, _userVO);
			
			// Confirm the Visit on Out Time Entry
			if(_triVO.getOutDate()!=null)
			{
				EpisodeVO episodeVO = new EpisodeVO();
				HelperMethods.populate(episodeVO, _triVO);			
				episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
				episodeVO.setComplainDetail("");
				episodeVO.setQueNo(_triVO.getSerialNo());
				//Episode Close/Open
				episodeVO.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);
				
				EpisodeDAO episodeDAO = new EpisodeDAO(tx);
				episodeDAO.updateNextVisitDetail(episodeVO, _userVO);
				
				DailyPatientDAO patDao = new DailyPatientDAO(tx);
				patDao.updateIsconfirmed(episodeVO.getPatCrNo(), episodeVO.getQueNo(), episodeVO.getEpisodeVisitNo(), episodeVO.getEpisodeCode(), 
						episodeVO.getIsConfirmed(), _userVO);
				
				EpisodeSummaryDAOi episodeSummaryDAO = new EpisodeSummaryDAO(tx);
				episodeSummaryDAO.create(_episodeSummaryVO, _userVO);
	
				// When Episode is closed
				if(_episodeCloseVO != null)
				{
					EpisodeCloseDAOi episodeeCloseDAO = new EpisodeCloseDAO(tx);
					episodeeCloseDAO.create(_episodeCloseVO, _userVO);
				}
			}
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	// * Getting Patient Triage Detail
	public EpisodeTriageDetailVO getPatTriageDtl(String _crNo, String _epiCode, String _visitNo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeTriageDetailDAO dao = new EpisodeTriageDetailDAO(tx);

		EpisodeTriageDetailVO vo = null;
		try
		{
			tx.begin();
			vo = dao.getDataByCrnoVisit(_crNo, _epiCode, _visitNo, _userVO);
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return vo;
	}*/

	// * Modify Triage Details
	/*public void modifyTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeTriageDetailDAO dao = new EpisodeTriageDetailDAO(tx);

		try
		{
			tx.begin();
			dao.updateOld(_triVO, _userVO);
			dao.create(_triVO, _userVO);
			
			// Confirm the Visit on Out Time Entry
			if(_triVO.getOutDate()!=null)
			{
				EpisodeVO episodeVO = new EpisodeVO();
				HelperMethods.populate(episodeVO, _triVO);			
				episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
				episodeVO.setComplainDetail("");
				episodeVO.setQueNo(_triVO.getSerialNo());
				//Episode Close/Open
				episodeVO.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);
				
				EpisodeDAO episodeDAO = new EpisodeDAO(tx);
				episodeDAO.updateNextVisitDetail(episodeVO, _userVO);
				
				DailyPatientDAO patDao = new DailyPatientDAO(tx);
				patDao.updateIsconfirmed(episodeVO.getPatCrNo(), episodeVO.getQueNo(), episodeVO.getEpisodeVisitNo(), episodeVO.getEpisodeCode(), 
						episodeVO.getIsConfirmed(), _userVO);
				
				EpisodeSummaryDAOi episodeSummaryDAO = new EpisodeSummaryDAO(tx);
				episodeSummaryDAO.create(_episodeSummaryVO, _userVO);
	
				// When Episode is closed
				if(_episodeCloseVO != null)
				{
					EpisodeCloseDAOi episodeeCloseDAO = new EpisodeCloseDAO(tx);
					episodeeCloseDAO.create(_episodeCloseVO, _userVO);
				}
			}
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public EpisodeVO[] saveReprintCard(RegCardPrintVO[] regCardPrintVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO episodeVO[]=new EpisodeVO[regCardPrintVO.length];
		try
		{
			tx.begin();
			RegCardPrintDtlDAO regCardPrintDAO = new RegCardPrintDtlDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			for (int i = 0; i < regCardPrintVO.length; i++)
			{
				regCardPrintDAO.saveReprintCard(regCardPrintVO[i], userVO);
				episodeVO[i]=new EpisodeVO();
			
				
					String usablityFlag="";
					if(regCardPrintVO[i].getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
						usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
					else if(regCardPrintVO[i].getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL))
						usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
					else if(regCardPrintVO[i].getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_EMERGENCY))
						usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_CASUALTIY;
					episodeVO[i].setDisclaimer(getDisclamer(usablityFlag, regCardPrintVO[i].getDepartmentUnitCode(), userVO));
								
					
				
				//query to fetch working days for printing
				episodeVO[i].setUnitWorkingDays(essentialDAO.getUnitWorkingDays(regCardPrintVO[i].getDepartmentUnitCode(), userVO));
				
			}
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeVO;
	}

	public PatientBroughtByDtlVO searchPatientBropughtByDetailByCrNo(PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientBroughtByDtlVO patientBroughtByDtlVO = null;
		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
			patientBroughtByDtlVO = patientBroughtByDtlDAO.searchPatientBroughtByDetailCrNo(_patBroughtByDtlVO, _userVO);
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside HisRecordNotFoundException of ");
			tx.rollback();
			System.out.println(e.getMessage());

		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

		return patientBroughtByDtlVO;
	}

	// * Modify Triage Details
	public EpisodeVO retrieveByEpisodeNo(EpisodeVO _epiVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeDAO dao = new EpisodeDAO(tx);
		EpisodeVO vo = null;

		try
		{
			tx.begin();
			vo = dao.retrieveByEpisodeNo(_epiVO, _userVO);
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return vo;
	}

	public Map getAllEpisodesForDuplicateReprint(String crNo, UserVO userVO, String _choice)
	{
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeDupVO;
		EpisodeVO[] _arrEpisodeReprintVO;
		String reprintMode = "";

		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			if (_choice.equals(RegistrationConfig.DUPLICATE_CARD))
			{
				_arrEpisodeDupVO = episodeDao.DuplicateRetrieveByCrNo(crNo, userVO);
				essentialMap.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_DUPLICATE, _arrEpisodeDupVO);
			}
			if (_choice.equals(RegistrationConfig.REPRINT_REGISTRATION))
			{
				reprintMode = RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT;
				_arrEpisodeReprintVO = episodeDao.reprintRetrieveByCrNo(crNo, userVO, reprintMode);
				essentialMap.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_REGISTRATION, _arrEpisodeReprintVO);
			}
			if (_choice.equals(RegistrationConfig.REPRINT_OLD_VISIT_SLIP))
			{
				reprintMode = RegistrationConfig.IS_CARD_PRINT_OLD_PATIENT;
				_arrEpisodeReprintVO = episodeDao.reprintRetrieveByCrNo(crNo, userVO, reprintMode);
				essentialMap.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_OLD_PATIENT, _arrEpisodeReprintVO);
			}
			if (_choice.equals(RegistrationConfig.REPRINT_RENEWAL))
			{
				_arrEpisodeReprintVO = episodeDao.reprintRenewalRetrieveByCrNo(crNo, userVO);
				essentialMap.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR_REPRINT_RENEWAL, _arrEpisodeReprintVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public EpisodeRefDtlVO[] getOldPatReferDtl(UserVO userVO)
	{
		EpisodeRefDtlVO[] episodeOldPatRefDtlVO;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EpisodeRefDtlDAO objEpisodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			episodeOldPatRefDtlVO = objEpisodeRefDtlDAO.getOldPatReferDtl(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			//System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return episodeOldPatRefDtlVO;
	}*/

/*	public EpisodeVO[] searchOldPatientEpisodesByCrNoByDept(String crNo, String dept, UserVO _userVO, String visitType)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO =	{};

		EpisodeVO[] arrVO =	{};
		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_arrEpisodeVO = episodeDao.retrieveOldPatientEpisodesByDept(crNo, dept, _userVO);
			EpisodeVO[] _tmpArrEpisodeVO = new EpisodeVO[_arrEpisodeVO.length];
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]
			{};

			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				_arrEpisodeVO[i].setRenewalType(Config.RENEWAL_TYPE);
			//////31-dec-2008
				////Checking for Episode_VISIT_TYPE 
				////Earlier visit type was passed as argument "visitType"
				///Now Visit Type is RegistrationConfig.EPISODE_VISIT_TYPE_OPD 
				//and RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL
				if (_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD) || 
						_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
				{
					_tmpArrEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++)
					{
						_tmpArrEpisodeVO[k] = arrVO[k];
					}
					_tmpArrEpisodeVO[len - 1] = _arrEpisodeVO[i];
					arrVO = _tmpArrEpisodeVO;

					for (int k = 0; k < arrVO.length; k++)
					{
						System.out.println("i= " + i + " k= " + k + "  arrVO...1" + arrVO[k].getEpisodeCode());
					}

					// arrVO[len-1]=_arrEpisodeVO[i];
					_tmpArrEpisodeVO[j] = _arrEpisodeVO[i];
					j++;
					// _tmpArrEpisodeVO=arrVO;
					len++;
				}
			}
			int m, n;
			for (m = 0, n = 0; m < _tmpArrEpisodeVO.length; m++, n++)
			{
				_arrEpisodeVO[n] = _tmpArrEpisodeVO[m];
			}
			if (arrVO.length == 0)
			{
				throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
			}
			// }
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return arrVO;
	}*/
/*
	public EpisodeVO[] searchOldPatientEpisodesByCrNoByUnit(String crNo, String unit, UserVO _userVO, String visitType)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO ={};
		
		EpisodeVO[] arrVO ={};
		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_arrEpisodeVO = episodeDao.retrieveOldPatientEpisodesByUnit(crNo, unit, _userVO);
			EpisodeVO[] _tmpArrEpisodeVO = new EpisodeVO[_arrEpisodeVO.length];
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]
			{};

			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				_arrEpisodeVO[i].setRenewalType(Config.RENEWAL_TYPE);
			//////31-dec-2008
				////Checking for Episode_VISIT_TYPE 
				////Earlier visit type was passed as argument "visitType"
				///Now Visit Type is RegistrationConfig.EPISODE_VISIT_TYPE_OPD 
				//and RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL
				if (_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD) || 
						_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
				{
					_tmpArrEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++)
					{
						_tmpArrEpisodeVO[k] = arrVO[k];
					}
					_tmpArrEpisodeVO[len - 1] = _arrEpisodeVO[i];
					arrVO = _tmpArrEpisodeVO;

					for (int k = 0; k < arrVO.length; k++)
					{
						System.out.println("i= " + i + " k= " + k + "  arrVO...1" + arrVO[k].getEpisodeCode());
					}

					// arrVO[len-1]=_arrEpisodeVO[i];
					_tmpArrEpisodeVO[j] = _arrEpisodeVO[i];
					j++;
					// _tmpArrEpisodeVO=arrVO;
					len++;
				}
			}
			int m, n;
			for (m = 0, n = 0; m < _tmpArrEpisodeVO.length; m++, n++)
			{
				_arrEpisodeVO[n] = _tmpArrEpisodeVO[m];
			}
			if (arrVO.length == 0)
			{
				throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
			}
			// }
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return arrVO;
	}

	public void saveParichitDetails(PatientParichitVO _patParichitVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientParichitDAO dao = new PatientParichitDAO(tx);

		try
		{
			tx.begin();
			dao.create(_patParichitVO, _userVO);
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}

	public List getPrimaryCatListExceptPatientCat(String patCat, UserVO userVO)
	{
		List patCatList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDAO patientDao = new PatientDAO(tx);

		try
		{
			tx.begin();
			patCatList = patientDao.getPrimaryCatListExceptPatientCat(patCat, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException("Record Not found");
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patCatList;
	}*/

	// This method renew the registration and the marks the stamping

	/*public EpisodeVO[] saveDeptWiseRenewalAndStaming(PatientVO _patVO, EpisodeVO[] _selectedEpisodeVO, EpisodeVO[] _arrRenewalEpisodeVO,
			EpisodeVO[] _arrEpisodeVO, EpisodeRefDtlVO _episodeRefVO, String _sysDate, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] arrayEpisodeForStamping = null;
		try
		{
			tx.begin();

			_arrEpisodeVO = saveDeptWiseRenewalDetailAndUpdateEpisodeVo(_selectedEpisodeVO, _arrEpisodeVO, _sysDate, _userVO, tx);

			// ////////////Preparing episode array for stamping//////
			ArrayList episodeNotRenewed = new ArrayList();
			boolean episodeRenewed;
			// ////Episode For which renewal not done///
			for (int i = 0; i < _arrRenewalEpisodeVO.length; i++)
			{
				episodeRenewed = false;
				for (int j = 0; j < _selectedEpisodeVO.length; j++)
				{
					if (_arrRenewalEpisodeVO[i].getEpisodeCode().equals(_selectedEpisodeVO[j].getEpisodeCode()))
					{
						episodeRenewed = true;
						//_arrRenewalEpisodeVO[i].setIsEpisodeRenewed("true");
						break;
					}
				}
				if (episodeRenewed == false)
				{
					//_arrRenewalEpisodeVO[i].setIsEpisodeRenewed("false");
					episodeNotRenewed.add(_arrRenewalEpisodeVO[i]);
				}
			}

			EpisodeVO[] arrayEpisodenotRenewed = (EpisodeVO[]) episodeNotRenewed.toArray(new EpisodeVO[episodeNotRenewed.size()]);

			// ////Episode For which stamping to be done/////
			ArrayList episodeForStamping = new ArrayList();
			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				episodeRenewed = false;
				for (int j = 0; j < arrayEpisodenotRenewed.length; j++)
				{
					if (_arrEpisodeVO[i].getEpisodeCode().equals(arrayEpisodenotRenewed[j].getEpisodeCode()))
					{
						episodeRenewed = true;
						break;
					}
				}
				if (episodeRenewed == false)
				{
					episodeForStamping.add(_arrEpisodeVO[i]);
				}
				///////setting flag if registration is renewd
				for (int j = 0; j < _selectedEpisodeVO.length; j++)
				{
					if (_arrEpisodeVO[i].getEpisodeCode().equals(_selectedEpisodeVO[j].getEpisodeCode()))
					{
						
						_arrEpisodeVO[i].setIsEpisodeRenewed("true");
						_arrEpisodeVO[i].setPatAmountCollected(_selectedEpisodeVO[j].getPatAmountCollected());
						
					}else 
					{
						_arrEpisodeVO[i].setIsEpisodeRenewed("false");
						_arrEpisodeVO[i].setPatAmountCollected("0");
					}
				}
			}

			arrayEpisodeForStamping = (EpisodeVO[]) episodeForStamping.toArray(new EpisodeVO[episodeForStamping.size()]);
			// /////////////////////////////////////////

			PatientBOSupport.oldDeptVisitStamp(arrayEpisodeForStamping, _patVO, _userVO, tx, _episodeRefVO);
			
			////inserting into renewal detail and reg charge dtl
			saveRenewalAndRegChagrges(_selectedEpisodeVO,arrayEpisodeForStamping,_userVO,tx);
		}
		catch (HisRenewalRequiredException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRenewalRequiredException();
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

		return arrayEpisodeForStamping;
	}*/
/*	public EpisodeVO saveDeptWiseRenewalDetailAndUpdateEpisodeVo(HisDAO daoObj, EpisodeVO _arrEpisodeVO,UserVO userVO, TransactionContext _tx)
	{

		HisDAO hisDAO=null;
		try
		{
			hisDAO=new HisDAO(null, null);
			
			EpisodeDAO episodeDAO = new EpisodeDAO(_tx);
			EssentialDAO essentialDAO = new EssentialDAO(_tx);
			String newExpiryDate = "";
			
			 //newExpiryDate=episodeDAO.getExpiryDate(_arrEpisodeVO, userVO);
			//System.out.println("nextExpiaryDate="+nextExpiaryDate);
			 // Commented as it was updating expiry date of previous episode data of same department
				//episodeDAO.saveDeptWiseRenewalDtl(hisDAO,_arrEpisodeVO.getPatCrNo(), _arrEpisodeVO.getEpisodeCode(), _arrEpisodeVO.getEpisodeVisitNo(), newExpiryDate, userVO);

				// /Updating episodevo's expiry date for stamping
				//System.out.println("nextExpiaryDate"+nextExpiaryDate);
				
				//String oldExpiryDate=_arrEpisodeVO.getExpiryDate(); Commented to remove the exp date from episode_dtl
				//_arrEpisodeVO.setExpiryDate(newExpiryDate);
				//_arrEpisodeVO.setOldExpiryDate(oldExpiryDate);
				
				
			

		}
		catch (HisRenewalRequiredException e)
		{
			System.out.println(e.getMessage());
			throw new HisRenewalRequiredException();
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisException(e.getMessage());
		}
		finally
		{

		}

		return _arrEpisodeVO;

	}*/
	// This method renew the registration and update the expiry date of episode vo for visit stamping

	/*public EpisodeVO[] saveDeptWiseRenewalDetailAndUpdateEpisodeVo(EpisodeVO[] selectedEpisodeVO, EpisodeVO[] _arrEpisodeVO, String sysDate,
			UserVO userVO, TransactionContext _tx)
	{

		RenewalDetailVO renewDetailVO = new RenewalDetailVO();
		RegChargeDtlVO regChargeDtlVO = new RegChargeDtlVO();
		DirectChageDetailVO directChargeVO=new DirectChageDetailVO();

		try
		{
			EpisodeDAO episodeDAO = new EpisodeDAO(_tx);
			EssentialDAO essentialDAO = new EssentialDAO(_tx);
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(_tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(_tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(_tx);
			GlobalEssentialDAO globalEssentialDAO = new GlobalEssentialDAO(_tx);
			String selectedcode = "";
			String newExpiryDate = "";
			for (int i = 0; i < selectedEpisodeVO.length; i++)
			{
				if (Config.RENEWAL_TYPE.equals("3"))
				{
					Date dt = new Date();
					dt = globalEssentialDAO.getSystemDate(dt);
					int month = dt.getMonth() + 1;
					if (month <= 6) newExpiryDate = Config.HOSPITAL_RENEWAL_EXPIRY_MONTH_FIRST;
					if (month > 6 && month <= 12) newExpiryDate = Config.HOSPITAL_RENEWAL_EXPIRY_MONTH_SECOND;

				}
				if (Config.RENEWAL_TYPE.equals("4"))
				{
					newExpiryDate = WebUTIL.addDate(sysDate, Integer.parseInt(Config.HOSPITAL_RENEWAL_EXPIRY_DAY));
					newExpiryDate = newExpiryDate.substring(0, 5);
					newExpiryDate = newExpiryDate.replace("/", "-");
				}
				if (Config.RENEWAL_TYPE.equals("5"))
				{
					String[] renewalDetails = essentialDAO.getExpiryDtlForRenewal(selectedEpisodeVO[i].getDepartmentUnitCode());
					String days = renewalDetails[0];
					String month = renewalDetails[1];
					String isExpiry = renewalDetails[2];
					if (isExpiry.equals("0")) throw new HisRenewalRequiredException();
					if (isExpiry.equals("1")) newExpiryDate = month;
					if (isExpiry.equals("2"))
					{
						newExpiryDate = WebUTIL.addDate(sysDate, Integer.parseInt(days));
						newExpiryDate = newExpiryDate.substring(0, 5);
						newExpiryDate = newExpiryDate.replace("/", "-");
					}

				}
				// //update episode detail table////
				episodeDAO.saveDeptWiseRenewalDtl(selectedEpisodeVO[i].getPatCrNo(), selectedEpisodeVO[i].getEpisodeCode(), selectedEpisodeVO[i]
						.getEpisodeVisitNo(), newExpiryDate, userVO);

				// /Updating episodevo's expiry date for stamping

				for (int j = 0; j < _arrEpisodeVO.length; j++)
				{
					if (selectedEpisodeVO[i].getEpisodeCode().equals(_arrEpisodeVO[j].getEpisodeCode()))
					{
						String expiryDateForEpisodeVO = essentialDAO.calculateRenewalDateForExpiry(newExpiryDate);
						String oldExpiryDate=_arrEpisodeVO[j].getExpiryDate();
						_arrEpisodeVO[j].setExpiryDate(expiryDateForEpisodeVO);
						_arrEpisodeVO[j].setOldExpiryDate(oldExpiryDate);
					}
				}

				// //////////////////////////////////

				// ///update HRGT_RENEW_DTL////
				
				renewDetailVO.setPatCrNo(selectedEpisodeVO[i].getPatCrNo());
				renewDetailVO.setSeatId(userVO.getSeatId());
				renewDetailVO.setIsValid(Config.IS_VALID_ACTIVE);
				renewDetailVO.setSystemIPAddress(selectedEpisodeVO[i].getSystemIPAddress());
				renewDetailVO.setEntryDate(selectedEpisodeVO[i].getEntryDate());
				renewDetailVO.setDepartmentCode(selectedEpisodeVO[i].getDepartmentCode());
				renewDetailVO.setDepartmentUnitCode(selectedEpisodeVO[i].getDepartmentUnitCode());
				renewDetailVO.setHospitalCode(userVO.getHospitalCode());
				renewDetailVO.setOldExpiryDate(selectedEpisodeVO[i].getExpiryDate());
				renewDetailVO.setNewExpiryDate(newExpiryDate);
				renewDetailVO.setRenewalType(Config.RENEWAL_TYPE);

				renewDetailDAO.create(renewDetailVO, userVO);
				// ///insert hrgt_reg_charge_dtl//////
				
				regChargeDtlVO.setPatAmountCollected(selectedEpisodeVO[i].getPatAmountCollected());
				regChargeDtlVO.setPatCrNo(selectedEpisodeVO[i].getPatCrNo());
				regChargeDtlVO.setPatPrimaryCatCode(selectedEpisodeVO[i].getPatPrimaryCatCode());
				regChargeDtlVO.setPatSecondaryCatCode(selectedEpisodeVO[i].getPatSecondaryCatCode());
				regChargeDtlVO.setSeatId(userVO.getSeatId());
				regChargeDtlVO.setSystemIPAddress(selectedEpisodeVO[i].getSystemIPAddress());
				regChargeDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
				regChargeDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				regChargeDtlVO.setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
				regChargeDtlVO.setModuleId(Config.MODULE_ID_REGISTRATION);
				regChargeDtlVO.setDepartmentCode(selectedEpisodeVO[i].getDepartmentCode());
				regChargeDtlVO.setDepartmentUnitCode(selectedEpisodeVO[i].getDepartmentUnitCode());
				regChargeDtlVO.setEpisodeCode(selectedEpisodeVO[i].getEpisodeCode());
				regChargeDtlVO.setAdmissionNo(selectedEpisodeVO[i].getAdmissionNo());
				//Incresing the visit number as charges are for next visit
				int visitNumber=Integer.parseInt(selectedEpisodeVO[i].getEpisodeVisitNo());
				visitNumber=visitNumber+1;
				regChargeDtlVO.setEpisodeVisitNo(visitNumber+"");
				System.out.println("regChargeDtlVO.getEpisodeVisitNo()="+regChargeDtlVO.getEpisodeVisitNo());
				if (!(regChargeDtlVO.getPatAmountCollected().equals("0") || regChargeDtlVO.getPatAmountCollected().equals("") ||
						regChargeDtlVO.getPatAmountCollected() == null))
				{
					///registration module billing
					regChargeDtlDAO.createForRenewal(regChargeDtlVO, userVO);
					HelperMethods.populate(directChargeVO, regChargeDtlVO);
					directChargeDao.create(directChargeVO, userVO);
					///billing module integraton
				//	regChargeDtlDAO.createBillinProcedure(regChargeDtlVO, userVO);
				}
				if (!regChargeDtlVO.getPatAmountCollected().equals("0"))
				{
					regChargeDtlDAO.create(regChargeDtlVO, userVO);
				}
			}

		}
		catch (HisRenewalRequiredException e)
		{
			System.out.println(e.getMessage());
			throw new HisRenewalRequiredException();
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisException(e.getMessage());
		}
		finally
		{

		}

		return _arrEpisodeVO;

	}
	
	
	// This method renew the registration and update the expiry date of episode vo for visit stamping
	public EpisodeVO saveRenewalAndRegChagrges(HisDAO daoObj,EpisodeVO _arrEpisodeVO, 
			UserVO userVO, TransactionContext _tx)
	{

		RenewalDetailVO renewDetailVO = new RenewalDetailVO();
		RegChargeDtlVO regChargeDtlVO = new RegChargeDtlVO();
		DirectChageDetailVO directChargeVO=new DirectChageDetailVO();

		try
		{
			
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(_tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(_tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(_tx);
			
				// ///update HRGT_RENEW_DTL////
				if(_arrEpisodeVO.getIsEpisodeRenewed().equals("true")||_arrEpisodeVO.getRenewalRequired().equals("1"))
				{
				renewDetailVO.setPatCrNo(_arrEpisodeVO.getPatCrNo());
				renewDetailVO.setSeatId(userVO.getSeatId());
				renewDetailVO.setIsValid(Config.IS_VALID_ACTIVE);
				renewDetailVO.setSystemIPAddress(userVO.getIpAddress());
				renewDetailVO.setEntryDate(_arrEpisodeVO.getEntryDate());
				renewDetailVO.setDepartmentCode(_arrEpisodeVO.getDepartmentCode());
				renewDetailVO.setDepartmentUnitCode(_arrEpisodeVO.getDepartmentUnitCode());
				renewDetailVO.setHospitalCode(userVO.getHospitalCode());
				renewDetailVO.setOldExpiryDate(_arrEpisodeVO.getOldExpiryDate());
				renewDetailVO.setNewExpiryDate(_arrEpisodeVO.getExpiryDate());
				renewDetailVO.setRenewalType(Config.RENEWAL_TYPE);

				//renewDetailDAO.create(renewDetailVO, userVO);
				//Modified to renewal the free patients case by Singaravelan on 08-10-13
				System.out.println("-------Primary Cat--------"+_arrEpisodeVO.getPatPrimaryCatCode()+"----------");
				if(!_arrEpisodeVO.getPatPrimaryCatCode().equalsIgnoreCase("11")) 
					renewDetailDAO.createForOldPatientRenewal(daoObj,renewDetailVO, userVO);
				// ///insert hrgt_reg_charge_dtl//////
				
				regChargeDtlVO.setPatAmountCollected(_arrEpisodeVO.getPatAmountCollected());
				regChargeDtlVO.setPatCrNo(_arrEpisodeVO.getPatCrNo());
				regChargeDtlVO.setPatPrimaryCatCode(_arrEpisodeVO.getPatPrimaryCatCode());
				regChargeDtlVO.setPatSecondaryCatCode(_arrEpisodeVO.getPatSecondaryCatCode());
				regChargeDtlVO.setSeatId(userVO.getSeatId());
				regChargeDtlVO.setSystemIPAddress(userVO.getIpAddress());
				regChargeDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
				regChargeDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				regChargeDtlVO.setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
				regChargeDtlVO.setModuleId(Config.MODULE_ID_REGISTRATION);
				regChargeDtlVO.setDepartmentCode(_arrEpisodeVO.getDepartmentCode());
				regChargeDtlVO.setDepartmentUnitCode(_arrEpisodeVO.getDepartmentUnitCode());
				regChargeDtlVO.setEpisodeCode(_arrEpisodeVO.getEpisodeCode());
				regChargeDtlVO.setAdmissionNo(_arrEpisodeVO.getAdmissionNo());
				//Incresing the visit number as charges are for next visit
				int visitNumber=Integer.parseInt(selectedEpisodeVO[i].getEpisodeVisitNo());
				visitNumber=visitNumber+1;
				regChargeDtlVO.setEpisodeVisitNo(_arrEpisodeVO.getEpisodeVisitNo());
				//System.out.println("regChargeDtlVO.getEpisodeVisitNo()="+regChargeDtlVO.getEpisodeVisitNo());
				System.out.println("-----Recharge Amout------"+regChargeDtlVO.getPatAmountCollected()+"---------------");
				if (regChargeDtlVO.getPatAmountCollected() != null && !(regChargeDtlVO.getPatAmountCollected().equals("0") && !regChargeDtlVO.getPatAmountCollected().equals("") )) 
				{
					///registration module billing
					if(!regChargeDtlVO.getPatAmountCollected().equalsIgnoreCase("0.00"))
					{
						regChargeDtlDAO.createForRenewal(daoObj,regChargeDtlVO, userVO);
						HelperMethods.populate(directChargeVO, regChargeDtlVO);
						directChargeDao.create(daoObj,directChargeVO, userVO);
						//Added to renewal the Paid patients case by Singaravelan on 08-10-13
						renewDetailDAO.createForOldPatientRenewal(daoObj,renewDetailVO, userVO);
					}
					///billing module integraton
				//	regChargeDtlDAO.createBillinProcedure(regChargeDtlVO, userVO);
				}
				if (!regChargeDtlVO.getPatAmountCollected().equals("0"))
				{
					regChargeDtlDAO.create(regChargeDtlVO, userVO);
				}
			}
			

		}
		catch (HisRenewalRequiredException e)
		{
			System.out.println(e.getMessage());
			throw new HisRenewalRequiredException();
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisException(e.getMessage());
		}
		finally
		{

		}

		return _arrEpisodeVO;

	}*/
	/*public EpisodeVO[] saveRenewalAndRegChagrges(EpisodeVO[] selectedEpisodeVO, EpisodeVO[] _arrEpisodeVO, 
			UserVO userVO, TransactionContext _tx)
	{

		RenewalDetailVO renewDetailVO = new RenewalDetailVO();
		RegChargeDtlVO regChargeDtlVO = new RegChargeDtlVO();
		DirectChageDetailVO directChargeVO=new DirectChageDetailVO();

		try
		{
			
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(_tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(_tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(_tx);
			String selectedcode = "";
			String newExpiryDate = "";
			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				// ///update HRGT_RENEW_DTL////
				if(_arrEpisodeVO[i].getIsEpisodeRenewed().equals("true"))
				{
				renewDetailVO.setPatCrNo(_arrEpisodeVO[i].getPatCrNo());
				renewDetailVO.setSeatId(userVO.getSeatId());
				renewDetailVO.setIsValid(Config.IS_VALID_ACTIVE);
				renewDetailVO.setSystemIPAddress(userVO.getIpAddress());
				renewDetailVO.setEntryDate(_arrEpisodeVO[i].getEntryDate());
				renewDetailVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
				renewDetailVO.setDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
				renewDetailVO.setHospitalCode(userVO.getHospitalCode());
				renewDetailVO.setOldExpiryDate(_arrEpisodeVO[i].getOldExpiryDate());
				renewDetailVO.setNewExpiryDate(_arrEpisodeVO[i].getExpiryDate());
				renewDetailVO.setRenewalType(Config.RENEWAL_TYPE);

				//renewDetailDAO.create(renewDetailVO, userVO);
				renewDetailDAO.createForOldPatientRenewal(renewDetailVO, userVO);
				// ///insert hrgt_reg_charge_dtl//////
				
				regChargeDtlVO.setPatAmountCollected(_arrEpisodeVO[i].getPatAmountCollected());
				regChargeDtlVO.setPatCrNo(_arrEpisodeVO[i].getPatCrNo());
				regChargeDtlVO.setPatPrimaryCatCode(_arrEpisodeVO[i].getPatPrimaryCatCode());
				regChargeDtlVO.setPatSecondaryCatCode(_arrEpisodeVO[i].getPatSecondaryCatCode());
				regChargeDtlVO.setSeatId(userVO.getSeatId());
				regChargeDtlVO.setSystemIPAddress(userVO.getIpAddress());
				regChargeDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
				regChargeDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				regChargeDtlVO.setTariffId(RegistrationConfig.RENEWAL_TARIFF_ID);
				regChargeDtlVO.setModuleId(Config.MODULE_ID_REGISTRATION);
				regChargeDtlVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
				regChargeDtlVO.setDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
				regChargeDtlVO.setEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
				regChargeDtlVO.setAdmissionNo(_arrEpisodeVO[i].getAdmissionNo());
				//Incresing the visit number as charges are for next visit
				int visitNumber=Integer.parseInt(selectedEpisodeVO[i].getEpisodeVisitNo());
				visitNumber=visitNumber+1;
				regChargeDtlVO.setEpisodeVisitNo(_arrEpisodeVO[i].getEpisodeVisitNo());
				System.out.println("regChargeDtlVO.getEpisodeVisitNo()="+regChargeDtlVO.getEpisodeVisitNo());
				if (regChargeDtlVO.getPatAmountCollected() != null && !(regChargeDtlVO.getPatAmountCollected().equals("0") && !regChargeDtlVO.getPatAmountCollected().equals("") )) 
				{
					///registration module billing
					regChargeDtlDAO.createForRenewal(regChargeDtlVO, userVO);
					HelperMethods.populate(directChargeVO, regChargeDtlVO);
					directChargeDao.create(directChargeVO, userVO);
					///billing module integraton
				//	regChargeDtlDAO.createBillinProcedure(regChargeDtlVO, userVO);
				}
				if (!regChargeDtlVO.getPatAmountCollected().equals("0"))
				{
					regChargeDtlDAO.create(regChargeDtlVO, userVO);
				}
			}
			}

		}
		catch (HisRenewalRequiredException e)
		{
			System.out.println(e.getMessage());
			throw new HisRenewalRequiredException();
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisException(e.getMessage());
		}
		finally
		{

		}

		return _arrEpisodeVO;

	}
*/

	
	/**
	 * List of All Open MLC Episode of the Patient
	 * 
	 * @param crNo Patient CR Number
	 * @param _userVO User VO
	 * @return
	 */
/*	public EpisodeVO[] getAllOpenTodayMLCEpisodes(String _patCrNo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		EpisodeVO[] _openMLCEpisodes;
		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_openMLCEpisodes = episodeDao.retrieveAllOpenTodayMLCEpisodes(_patCrNo, _userVO);
			EpisodeVO[] episdeVOs=null;
			List<EpisodeVO> episodeVOList=new ArrayList<EpisodeVO>();
			List list=new ArrayList();
			
			for(int i=0;i<_openMLCEpisodes.length;i++){
				if(_openMLCEpisodes[i].getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC)){
					episodeVOList.add(_openMLCEpisodes[i]);
				}
			}
			
			
			if(_openMLCEpisodes.length==1){
				if(_openMLCEpisodes[0].getMlcNo()!=null){
					throw new HisRecordNotFoundException("Cannot convert to non mlc as mlc no has been given");
				}
				else if(!_openMLCEpisodes[0].getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC)){
					throw new HisRecordNotFoundException("Not an Mlc Patient");
				}
			}
			
			for(int k=0;k<episodeVOList.size();k++){
				if(episodeVOList.get(k).getMlcNo()!=null){
					list.add(episodeVOList.get(k));
					episodeVOList.remove(k);
					
				}	
			}
			episdeVOs=new EpisodeVO[episodeVOList.size()];
			episdeVOs=(EpisodeVO[])episodeVOList.toArray(episdeVOs);
			if(episdeVOs.length==0 && list.size()>0){
				throw new HisRecordNotFoundException("Cannot convert to non mlc as mlc no has been given");
			}
			else if(episdeVOs.length==0 && list.size()==0){
				throw new HisRecordNotFoundException("Not an Mlc Patient");
			}
			
			
			_openMLCEpisodes=episdeVOs;
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _openMLCEpisodes;
	}*/
	
	/** Revoking MLC Episodes 
	 * @param _mlcRevVO MLC Revoking VO
	 * @param _userVO User VO
	 * @return true if Revoked otherwise false
	 */
	/*public boolean revokeMLCEpisodes(MLCRevokeEpisodeDetailVO[] _mlcRevVOs, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int x = 0;
		DailyPatientVO _dailyPatientVO = new DailyPatientVO();
		EpisodeVO _episodeVO = new EpisodeVO();
		try
		{
			tx.begin();

			DailyPatientDAO dailyPatientDao = new DailyPatientDAO(tx);
			MLCRevokeEpisodeDetailDAOi mlcRevEpiDAO = new MLCRevokeEpisodeDetailDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			for (int i = 0; i < _mlcRevVOs.length; i++)
			{
				_episodeVO.setPatCrNo(_mlcRevVOs[i].getPatCrNo());
				_episodeVO.setEpisodeCode(_mlcRevVOs[i].getEpisodeCode());
				_episodeVO.setEpisodeVisitNo(_mlcRevVOs[i].getEpisodeVisitNo());
				_episodeVO.setMlcNo(_mlcRevVOs[i].getMlcNo());

				x = episodeDAO.updateRevokedMLCEpisodeDtl(_episodeVO, _userVO);

				_dailyPatientVO.setPatCrNo(_mlcRevVOs[i].getPatCrNo());
				_dailyPatientVO.setEpisodeCode(_mlcRevVOs[i].getEpisodeCode());
				_dailyPatientVO.setEpisodeVisitNo(_mlcRevVOs[i].getEpisodeVisitNo());
				_dailyPatientVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC);

				dailyPatientDao.updateRevokeMLCEpisode(_dailyPatientVO, _userVO);

				mlcRevEpiDAO.create(_mlcRevVOs[i], _userVO);
			}

		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			System.out.println("x = " + x);
			tx.close();
		}
		if (x >= 1) return true;
		else return false;
	}

	public void changePatientRoom(RoomChangeVO _roomChangeVO,EpisodeVO _episodeVO,DailyPatientVO _dailyPatientVO,UserVO _userVO,String _unitCode)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
        String  _queNo="";
		try
		{
			tx.begin();
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			
			DailyPatientDAO _dailyPatientDAO=new DailyPatientDAO(tx);
			RoomChangeDetailDAO _roomChangeDAO=new RoomChangeDetailDAO(tx);
			EpisodeDAO _episodeDAO=new EpisodeDAO(tx);
			
			
			_queNo = _dailyPatientDAO.getQueNumber(_userVO,_unitCode,_roomChangeVO.getToRoomCode());
			_episodeVO.setQueNo(_queNo);
			_dailyPatientVO.setQueNo(_queNo);
			
			_roomChangeDAO.changePatientRoomDetails(_roomChangeVO,_userVO);
			_episodeDAO.changeEpisodeDetails(_episodeVO,_userVO);
			_dailyPatientDAO.changeDailyPatientDetails(_dailyPatientVO,_userVO);
			
			
			
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
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		
	}

	public Map getPatientAuditTrailEssentials(String _patCrNo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		Map EssentialMap=new HashMap();
		try
		{
			PatientDetailVO _patDetailVO=null;
			PatientDetailVO[] _patAuditDetailVO=null;
			AddressVO[] _addressVO=null;
			PrimaryCategoryChangeVO[] _primaryCatgVO=null;
			SecondaryCategoryChangeVO[] _secondaryCatgVO=null;
			UnknownChangeVO[] _unknownChangeVO=null;
			MlcVO[] _mlcVO=null;
			
			tx.begin();
			PatientDAO _patientDao = new PatientDAO(tx);
			
			_patDetailVO = _patientDao.getPatientHrgtEssentials(_patCrNo, _userVO);
		if(_patDetailVO!=null)
			EssentialMap.put(RegistrationConfig.VO_OF_HRGT_PATIENT_DTL,_patDetailVO);
			
			
			_patAuditDetailVO = _patientDao.getPatientAuditEssentials(_patCrNo, _userVO);
		if(_patAuditDetailVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_PATIENT_AUDIT_DTL,_patAuditDetailVO);
			 
		
			_addressVO=_patientDao.getPatientAddressDetailsEssentials(_patCrNo, _userVO);
		if(_addressVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_PATIENT_ADD_DTL,_addressVO);
			
			
			_primaryCatgVO=_patientDao.getPrimaryCategotyChangeDetailsEssentials(_patCrNo, _userVO);
		if(_primaryCatgVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_PRICAT_CHANGE_DTL,_primaryCatgVO);
			
			
			_secondaryCatgVO=_patientDao.getSecondaryCategotyChangeDetailsEssentials(_patCrNo, _userVO);
		if(_secondaryCatgVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_SECCAT_CHANGE_DTL,_secondaryCatgVO);
			
			
			_unknownChangeVO=_patientDao.getUnknownToKnownChangeDetails(_patCrNo, _userVO);
		if(_unknownChangeVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_UNKNOWN_CHANGE_DTL,_unknownChangeVO);
			
			
		_mlcVO=_patientDao.getPatientMlcChangeDetails(_patCrNo, _userVO);
		if(_mlcVO!=null)	
			EssentialMap.put(RegistrationConfig.VO_S_OF_HRGT_PATIENT_MLC_DTL,_mlcVO);	
		
		
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return EssentialMap;
	}*/
/*	
	public String getDisclamer(String usablityFlag,String deptUnitCode,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String disclaimer=""; 
		try
		{
			tx.begin();
			EssentialDAO essentialDao = new EssentialDAO(tx);
			
			disclaimer = essentialDao.getDisclaimerByUnit(deptUnitCode, _userVO);
			if(disclaimer==null)
				disclaimer = essentialDao.getDisclaimerByDept(deptUnitCode.substring(0,3), _userVO);
			if(disclaimer==null)
				disclaimer = essentialDao.getDefaultDisclaimer(usablityFlag, _userVO);
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return (disclaimer==null?"":disclaimer);
	}
	/////for cmo desk patient under observation////
	
	 public void savePatientAttendedUnderObservation(EpisodeActionDtlVO _episodedtlActionDtlVO,UserVO _userVO){
		 
			PatientVO patientVO = new PatientVO();
			patientVO.setPatCrNo(_episodedtlActionDtlVO.getPatCrNo());
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				EpisodeVO episodeVO = new EpisodeVO();
			
				tx.begin();
				EpisodeActionDtlDAO episodeActionDtlDAO = new EpisodeActionDtlDAO(tx);
				EpisodeDAO episodeDao = new EpisodeDAO(tx);
				HelperMethods.populate(episodeVO, _episodedtlActionDtlVO);
				episodeVO.setEpisodeActionCode(_episodedtlActionDtlVO.getEpisodeActionCode());
				episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
				episodeVO.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_TRUE);
				
				////////updating episode dtl
				episodeDao.updateEpisodeDtlForCMO(episodeVO, _userVO);
				//////insert into episode_action_dtl
				episodeActionDtlDAO.create(_episodedtlActionDtlVO, _userVO);
						

			}
			catch (HisUpdateUnsuccesfullException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisUpdateUnsuccesfullException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisApplicationExecutionException(e.getMessage());
			}
			finally
			{
				tx.close();
			}
	 }*/
	 
	 /*public PatientBroughtByDtlVO searchPatientBropughtByDetailByCrNoNew(PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO)
	 {
		 JDBCTransactionContext tx = new JDBCTransactionContext();
			PatientBroughtByDtlVO patientBroughtByDtlVO = null;
			try
			{
				System.out.println("Begining transaction");
				tx.begin();
				PatientBroughtByDtlDAO patientBroughtByDtlDAO = new PatientBroughtByDtlDAO(tx);
				patientBroughtByDtlVO = patientBroughtByDtlDAO.searchPatientBroughtByDetailCrNoNew(_patBroughtByDtlVO, _userVO);
			}// end of try
			catch (HisRecordNotFoundException e)
			{
				System.out.println("inside HisRecordNotFoundException of ");
				tx.rollback();
				System.out.println(e.getMessage());

			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisApplicationExecutionException(e.getMessage());
			}
			finally
			{
				tx.close();
			}

			return patientBroughtByDtlVO;
	 }
	 
	public Map checkEligibleForMLC(PatientVO _patVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO episodeVO = null;
		Map essMap=new HashMap();
		try
		{
			MlcDAO objMlcDao = new MlcDAO(tx);
			tx.begin();
			
			try
			{
				episodeVO = getLastEpisodeInEmergency(_patVO, _userVO);
				essMap.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, episodeVO);
			}
			catch (Exception e)
			{
				throw new HisNotEligibleForMLCException(e.getMessage());
			}

			MlcVO[] mlcVO = objMlcDao.retrieveByCrNo(_patVO, _userVO);
			if (mlcVO.length > 0)
			{
				for (int i = 0; i < mlcVO.length; i++)
				{
					if (mlcVO[i].getPatCrNo().equalsIgnoreCase(_patVO.getPatCrNo())
							&& mlcVO[i].getEpisodeCode().equalsIgnoreCase(episodeVO.getEpisodeCode()))
					{
						essMap.put(RegistrationConfig.MLC_VO_POLICE_VERIFICATION, mlcVO[i]);
						
					}
					else
					{
						throw new HisRecordNotFoundException("");
					}
				}
			}
		//	else return essMap;
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRecordNotFoundException("MLC Not Generated");
		}
		catch (HisNotEligibleForMLCException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisNotEligibleForMLCException(e.getMessage());
		}
		catch (HisMlcAlreadyExistException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisMlcAlreadyExistException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essMap;
	}
	 
	public void savePoliceVerification(PoliceVerificationDtlVO policeVerDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		 
		try
		{
			tx.begin();
			PoliceVerificationDAOi policeVerDAO=new PoliceVerificationDAO(tx);
			policeVerDAO.create(policeVerDtlVO, userVO) ;
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}
	
	public PoliceVerificationDtlVO getPoliceVerDtl(MlcVO mlcVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PoliceVerificationDtlVO policeVerDtlVO=null;
		
		try
		{
			tx.begin();
			PoliceVerificationDAOi polDAO=new PoliceVerificationDAO(tx);
			policeVerDtlVO=polDAO.select(mlcVO, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRecordNotFoundException("");
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return policeVerDtlVO;
	}
	
	 Changes by Amit as per OPD G5 Changes 
 	public void savePatientDeathDetail(ANCDetailVO ancDetailVO, PatientDeathDetailVO patDeathDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		EpisodeVO episodeVO = new EpisodeVO();
		//EpisodeCloseVO epCloseVO = new EpisodeCloseVO();
		PatientStatusChangeVO patientStatusChangeVO = new PatientStatusChangeVO();
		CertificateIssueDtlVO certificateIssueDtlVO = new CertificateIssueDtlVO();
		String deathCertificateId="";
		String genMode="";
		
		try
		{
			tx.begin();
			//EpisodeCloseDAO episodeCloseDao = new EpisodeCloseDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			PatientStatusChangeDAO patientStatusChangeDAO = new PatientStatusChangeDAO(tx);
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			PatientDeathDetailDAOi patDeathDAO=new PatientDeathDetailDAO(tx);
			PatientDAO patDAO=new PatientDAO(tx);
			ANCNeonatalDtlDAOi ancNewnatalDAO=new ANCNeonatalDtlDAO(tx);
			ANCDeliveryDtlDAOi ancDeliveryDtlDAO=new ANCDeliveryDtlDAO(tx);
			
			
			///Start Generating Certificate////////
			if(patDeathDtlVO.getIsPrintCertificate().equals(RegistrationConfig.PRINT_DEATH_CERTIFICATE_YES))
			{
				CertificateIssueDtlDAOi issueDAO=new CertificateIssueDtlDAO(tx); 
				genMode="1";
				deathCertificateId=patDeathDAO.generateDeathCertificateId(patDeathDtlVO,genMode, userVO);
				
				certificateIssueDtlVO.setCertificateId(deathCertificateId);
				certificateIssueDtlVO.setCertificateDesc(deathCertificateId);
				certificateIssueDtlVO.setRecordType(RegistrationConfig.RECORD_TYPE_DEATH_CERTIFICATE);
				certificateIssueDtlVO.setHandoverTo(patDeathDtlVO.getCertificateHandoverTo());
				certificateIssueDtlVO.setIsDuplicate(RegistrationConfig.IS_DUPLICATE_CERTIFICATE_NO);
				certificateIssueDtlVO.setIsReceiptTaken(patDeathDtlVO.getIsReceiptTaken());
				certificateIssueDtlVO.setRecordStatus(patDeathDtlVO.getRecordStatus());
				certificateIssueDtlVO.setDeptUnitCode(patDeathDtlVO.getDepartmentUnitCode());
				
				issueDAO.create(certificateIssueDtlVO, userVO);
			}
			
			///End Generating Certificate////////
			
			// Retrieving Episode Data
			episodeVO.setPatCrNo(patDeathDtlVO.getPatCrNo());
			episodeVO.setEpisodeCode(patDeathDtlVO.getEpisodeCode());
			episodeVO = episodeDao.retrieveByEpisodeNo(episodeVO, userVO);
			
			episodeVO.setEpisodeVisitNo(patDeathDtlVO.getEpisodeVisitNo());
			episodeVO.setEpisodeActionCode(RegistrationConfig.EPISODE_ACTION_DEATH);
			episodeVO.setEpisodeIsOpen(RegistrationConfig.EPISODE_ISOPEN_FALSE);
			episodeVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
			
			// Updating Episode Detail
			episodeDao.updateEpisodeDtlForCMO(episodeVO, userVO);
			
			// Inserting Episode Close Record
			//HelperMethods.populate(epCloseVO, episodeVO);
			//episodeCloseDao.create(epCloseVO, userVO);
			
			// Inserting Episode Death Detail Record	
			patDeathDtlVO.setDeathCertificateId(deathCertificateId);
			patDeathDAO.create(patDeathDtlVO,userVO);
			
			// Creating Patient Status Change VO
			HelperMethods.populate(patientStatusChangeVO, episodeVO);
			patientStatusChangeVO.setPatStatusCodeNew(RegistrationConfig.PATIENT_STATUS_CODE_DEAD);
			patientStatusChangeVO.setPatStatusCodeOld(patDeathDtlVO.getPatStatusCode());
			
			// Inserting Patient Status Change Record
			//Closed on 20-02-2015 bec table does not exist & no required for nims
			//patientStatusChangeDAO.create(patientStatusChangeVO, userVO);
			
			//updating Patient Status in HRGT_DAILY_PATIENT_DTL
			if(patDeathDtlVO.getPatAdmNo()==null || patDeathDtlVO.getPatAdmNo()=="")
			dailyPatientDAO.updatePatStatusIsconfirmed(patDeathDtlVO.getPatCrNo(), patDeathDtlVO.getSerialNo(), patDeathDtlVO.getEpisodeVisitNo(), patDeathDtlVO.getEpisodeCode(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED, userVO);
			
			//updating Patient Status in HRGT_PATIENT_DTL
			patDAO.updatePatStatus(patDeathDtlVO.getPatCrNo(), patDeathDtlVO.getSerialNo(), patDeathDtlVO.getEpisodeVisitNo(), patDeathDtlVO.getEpisodeCode(), userVO);

			//updating Patient Status in HIPT_PATADMISSION_DTL
			if(patDeathDtlVO.getAdmStatusCode() != null)
			{			
				if((patDeathDtlVO.getAdmStatusCode().equals("12"))||(patDeathDtlVO.getAdmStatusCode().equals("18")))
				{
					//PatientAdmissionTransDAO.updatePatDeadStatus(patDeathDtlVO, userVO);
					patDAO.updatePatDeadStatus(patDeathDtlVO, userVO);
				}
			}			
			//Updating Newnatal Death Status
			if(patDeathDtlVO.getIsNewNatal().equals(RegistrationConfig.YES))
				ancNewnatalDAO.updateNewnatalDeath(patDeathDtlVO.getDeathDate(),patDeathDtlVO.getPatCrNo(), userVO);
			
			//Updating Mother Status
			if(ancDetailVO!=null && ancDetailVO.getDeliveryStatus()!=null && ancDetailVO.getDeliveryStatus().equals("1"))		//0-->NO	1-->YES
				ancDeliveryDtlDAO.updateMotherStatus(ancDetailVO,userVO);
				
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
	}
	
	public boolean checkRecordAdded(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean exist=false;
		
		try
		{
			tx.begin();
			PatientDeathDetailDAOi patDeathDAO=new PatientDeathDetailDAO(tx);
			exist=patDeathDAO.checkRecordAdded(crNo,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRecordNotFoundException("");
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return exist;
	}
	
	public PatientDeathDetailVO getDeathDetailByCrNo(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDeathDetailVO patDeathVO=null;
		
		try
		{
			tx.begin();
			PatientDeathDetailDAOi patDeathDAO=new PatientDeathDetailDAO(tx);
			patDeathVO=patDeathDAO.getDeathDetailByCrNo(crNo, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRecordNotFoundException("");
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patDeathVO;
	}
	
	public AddressVO getPatAddress(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		AddressVO patAddressVO=null;
		PatientVO _patientVO=new PatientVO();
		
		try
		{
			tx.begin();
			AddressDAOi addressDAO=new AddressDAO(tx);
			_patientVO.setPatCrNo(crNo);
			_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			patAddressVO=addressDAO.retrieveByCrNo(_patientVO, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return patAddressVO;
	}
	
	*/
	
	public Map getHandoverToDetail(String crNo,UserVO userVO)
	{
	    JDBCTransactionContext tx =new JDBCTransactionContext();
	    Map essentialMap=new HashMap();
	    List lstRelationships=new ArrayList();
	   
	    PatientDeathDetailVO pDetailVO=new PatientDeathDetailVO();
	    try
	    {
	    	tx.begin();
	    	PatientDeathDetailDAOi pDetailDAOi=new PatientDeathDetailDAO(tx);
	    	EssentialDAO objEssentialDAO=new EssentialDAO(tx);
	    	
	    	pDetailVO=pDetailDAOi.getHandoverToDetail(crNo, userVO);
	    	essentialMap.put(RegistrationConfig.DEADBODY_HANDOVER_DETAIL, pDetailVO);
	    	
	    	lstRelationships=objEssentialDAO.getRelationsList(userVO);
	    	essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL, lstRelationships);
	    	
    }
	    catch(HisRecordNotFoundException e)
	    {
			tx.rollback();
			e.getEssentialMap();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
	   		 tx.rollback();
	   		 System.out.println(e.getMessage());   		 
	   		 throw new HisApplicationExecutionException();
	   	}
	   	catch(HisDataAccessException e)
	   	{		
	   		 tx.rollback();
	   		 System.out.println(e.getMessage());   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e)
		{
			System.out.println(e.getMessage());	
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
## 		Modification Log		: saveHandoverToDetail					
##		Modify Date				: 07-01-2015	
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh 
*/	
	/*
	public void saveHandoverToDetail(PatientDeathDetailVO patDeathDtlVO,String flagForPrint,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		EpisodeVO episodeVO = new EpisodeVO();
		EpisodeCloseVO epCloseVO = new EpisodeCloseVO();
		PatientStatusChangeVO patientStatusChangeVO = new PatientStatusChangeVO();
		CertificateIssueDtlVO certificateIssueDtlVO = new CertificateIssueDtlVO();
		String deathCertificateId="";
		String genMode="";
		
		try
		{
			tx.begin();
			PatientDeathDetailDAOi patDeathDAO=new PatientDeathDetailDAO(tx);
			CertificateIssueDtlDAOi issueDAO=new CertificateIssueDtlDAO(tx); 
			MrdEssentialDAO mrdEssDAO = new MrdEssentialDAO(tx);
			
			///Start Generating Certificate////////
			if((patDeathDtlVO.getDeathCertificateId().equals("")) || (patDeathDtlVO.getDeathCertificateId()== null))
			{
				genMode="1";
				deathCertificateId = mrdEssDAO.generateCertificateId(MrdConfig.RECORD_TYPE_DEATH_NOTIFICATION,patDeathDtlVO.getDepartmentUnitCode(),MrdConfig.MEDICAL_CERIFICATE_REQUEST_GENMODE_AUTOMATIC ,userVO);
				//deathCertificateId=patDeathDAO.generateDeathCertificateId(patDeathDtlVO,genMode, userVO);
				
				certificateIssueDtlVO.setCertificateId(deathCertificateId);
				certificateIssueDtlVO.setCertificateDesc(deathCertificateId);
				patDeathDtlVO.setDeathCertificateId(deathCertificateId);
			}
			else
			{
				certificateIssueDtlVO.setCertificateId(patDeathDtlVO.getDeathCertificateId());
				certificateIssueDtlVO.setCertificateDesc(patDeathDtlVO.getDeathCertificateId());
			}

			certificateIssueDtlVO.setRecordType(RegistrationConfig.RECORD_TYPE_DEATH_CERTIFICATE);
			certificateIssueDtlVO.setHandoverTo(patDeathDtlVO.getCertificateHandoverTo());
			certificateIssueDtlVO.setIsDuplicate(RegistrationConfig.IS_DUPLICATE_CERTIFICATE_NO);
			certificateIssueDtlVO.setIsReceiptTaken(patDeathDtlVO.getIsReceiptTaken());
			certificateIssueDtlVO.setRecordStatus(patDeathDtlVO.getRecordStatus());
			certificateIssueDtlVO.setDeptUnitCode(patDeathDtlVO.getDepartmentUnitCode());
			certificateIssueDtlVO.setRemarks(patDeathDtlVO.getRemarks());
			issueDAO.create(certificateIssueDtlVO, userVO);
			///End Generating Certificate////////
			
			// Inserting Episode Death Detail Record	
			if(flagForPrint.equals("1")){
			 patDeathDtlVO.setDeathCertificateId(deathCertificateId);
			 patDeathDAO.updateHandoverDetailTo(patDeathDtlVO, userVO); }
			if(flagForPrint.equals("0"))
			{
			  patDeathDtlVO.setDeathCertificateId(deathCertificateId);
			  patDeathDAO.updateCertificateId(patDeathDtlVO, userVO);
			}
			
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
	}
*/
	
	public PatientDeathDetailVO[] getDeadPatientList(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientDeathDetailVO[] pDeathDetailVOs = null; 
	    try
		{
			tx.begin();
			PatientDeathDetailDAOi patDeathDAO=new PatientDeathDetailDAO(tx);
			
			 pDeathDetailVOs=patDeathDAO.getDeadPatientList(userVO);
		}
	    catch(HisRecordNotFoundException e)
	    {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return pDeathDetailVOs;
	}
/*
	public String getPatientMlcNo(PatientDetailVO selectedPatientVO,UserVO userVO)
	{
		 JDBCTransactionContext tx =new JDBCTransactionContext();
		 String mlcNo="";
		 
		 try
		 {
			tx.begin();
			MlcDAOi mlcDAO=new MlcDAO(tx);
			mlcNo=mlcDAO.getPatientMlcNo(selectedPatientVO,userVO);
		 }
		catch(HisRecordNotFoundException e)
	    {
			tx.rollback();
			e.getEssentialMap();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
		   	System.out.println(e.getMessage());   		 
		   	throw new HisApplicationExecutionException();
		}
		catch(HisDataAccessException e)
		{		
			tx.rollback();
		   	System.out.println(e.getMessage());   		 
		   	throw new HisDataAccessException();  	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return mlcNo;
	}
	
	public ANCDetailVO getPatientANCDetail(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ANCDetailVO ancDtlVO=null;
		
		try
		{
			tx.begin();
			ANCDtlDAOi ancDAO=new ANCDtlDAO(tx);
			ancDtlVO=ancDAO.getPatientANCDetail(crNo,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return ancDtlVO;
	}
	
	/////UPLOADDED Documents
	public DocumentUploadDtlVO[] getMlcUploadDetail(MlcVO _mlcVO,UserVO _userVO){
		
			JDBCTransactionContext tx = new JDBCTransactionContext();
			DocumentUploadDtlVO[] uploadVO=null;
			
			try
			{
				tx.begin();
				DocumentUploadDtlDAOi uploadDAO=new DocumentUploadDtlDAO(tx);
				uploadVO=uploadDAO.getDocumentDetailsByMlcEpisode(_mlcVO, _userVO);
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisApplicationExecutionException(e.getMessage());
			}
			finally
			{
				tx.close();
			}
			return uploadVO;
	}*/
	
	/**
	 * Changed By :- Akash Singh
	 * Reason:- change file upload process from ftp to mongoDb 
	 * Changed Date :- 01-10-2015
	 * @author AHIS*
	 */
	public void saveDocumentDetail(DocumentUploadDtlVO[] docUploadVos,DocumentUploadDtlVO[] documentUploadRevokeDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count;
		String newFileName="";
		int i = 0;
		try
		{
			DocumentUploadDtlDAOi docUploadDaoi = new DocumentUploadDtlDAO(tx);
			tx.begin();
			if( (docUploadVos!=null) && (docUploadVos.length>0) )
				{
				count=docUploadDaoi.getContCrNoWise(docUploadVos[0].getPatCrNo(), userVO);
				//int countInt=Integer.parseInt(count)+10;
				int countInt=Integer.parseInt(count);
				for (; i < docUploadVos.length; i++)
				{
					String sysdate=docUploadVos[i].getEntryDate();
					char[] charArray; 
					String newSysdate="";
					charArray=sysdate.toCharArray();
					for(int charIndex=0;charIndex<charArray.length;charIndex++){
						if(Character.isSpaceChar(charArray[charIndex])){
							newSysdate=newSysdate+'-';
						}
						else{
							newSysdate=newSysdate+charArray[charIndex];
						}
					}
					newSysdate=newSysdate.replace('/','-');
					newSysdate=newSysdate.replace(':','-');
					countInt=countInt+1;
					String[] filePartName={docUploadVos[i].getPatCrNo(),newSysdate,String.valueOf(countInt)};
					String fileName=HelperMethods.getFileName('$',filePartName);
					//String fileExt=docUploadVos[i].getDocumentName().substring(docUploadVos[i].getDocumentName().lastIndexOf("."));
					//fileExt = fileExt.substring(1);
					String fileExt = docUploadVos[i].getFileName();
					fileExt = "."+fileExt;
					fileName=fileName+fileExt;
					newFileName= "16_Doc_"+docUploadVos[i].getPatCrNo()+"_"+countInt+fileExt;
					String docId="";
					docId=docUploadVos[i].getPatCrNo()+"_"+countInt;
					docUploadVos[i].setDocumentCode(docId);
					docUploadVos[i].setDocumentName(newFileName);
					docUploadVos[i].setDocumentDirectoryPath(fileExt);
					docUploadVos[i].setDocSlNo(""+countInt);
					
// comment by Akash Singh, Dated on 01-10-2015 due to change for ftp to MongoDb 					
					//COME
					//HisFileControlUtil fileUtil = new HisFileControlUtil();
					//fileUtil.setFileName(docUploadVos[i].getDocumentName());
					//fileUtil.setWindowsFilePath(docUploadVos[i].getPathForWindows());
					//fileUtil.setLinuxFilePath(docUploadVos[i].getPathPathLinux());
					//fileUtil.setFileContent(docUploadVos[i].getDocFile());
					//fileUtil.saveFile();
					
// Calling MongoDb from Here					
					//MongoXmlHandler.getInstanceWithURL(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD, HISConfig.HIS_MONGODB_PAT_DOC).savePDFFile(docId, docUploadVos[i].getDocFile());
					//MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD).savePDFFile(docId, docUploadVos[i].getDocFile());
					docUploadDaoi.create(docUploadVos[i], userVO);
					}
				}
			if(documentUploadRevokeDtlVO!=null)
			{
				for(int j=0;j<documentUploadRevokeDtlVO.length;j++)
				{
					docUploadDaoi.revokeDocument(documentUploadRevokeDtlVO[j], userVO);
				}
			}
			

		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}
/*	

	public EpisodeVO[] getPatientAdmittedEpisodes(String crNo, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO=null;

		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_arrEpisodeVO = episodeDao.getPatientAdmittedEpisodes(crNo, _userVO);

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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrEpisodeVO;
	}
	
	public void revokeSecondaryCategory(SecondaryCategoryChangeVO[] _secCatChangeVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DailyPatientVO _dailyPatientVO = new DailyPatientVO();
		EpisodeVO _episodeVO = new EpisodeVO();
		
		try
		{
			tx.begin();
			DailyPatientDAO dailyPatientDao = new DailyPatientDAO(tx);
			SecondaryCategoryChangeDAO secCatChangeDAO = new SecondaryCategoryChangeDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			
			for(int i=0;i<_secCatChangeVO.length;i++)
			{
				if(_secCatChangeVO[i].getIsIpdFlag().equals(RegistrationConfig.NO))
				{
					_dailyPatientVO.setPatCrNo(_secCatChangeVO[i].getPatCrNo());
					_dailyPatientVO.setEpisodeCode(_secCatChangeVO[i].getEpisodeCode());
					_dailyPatientVO.setPatSecondaryCatCode(_secCatChangeVO[i].getPatNewSecondaryCatCode());
					_dailyPatientVO.setEpisodeVisitNo(_secCatChangeVO[i].getEpisodeVisitNo());
					
					dailyPatientDao.updateTreatmentCategory(_dailyPatientVO, _userVO);
					
				}
					
				
				_episodeVO.setPatCrNo(_secCatChangeVO[i].getPatCrNo());
				_episodeVO.setEpisodeCode(_secCatChangeVO[i].getEpisodeCode());
				_episodeVO.setPatSecondaryCatCode(_secCatChangeVO[i].getPatNewSecondaryCatCode());
				_episodeVO.setEpisodeVisitNo(_secCatChangeVO[i].getEpisodeVisitNo());
				_episodeVO.setSecCatExpDate("");
				
				episodeDAO.updateRevokeSecondaryCat(_episodeVO, _userVO);	
				
				secCatChangeDAO.create(_secCatChangeVO[i], _userVO);
				
			}
			
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	public MlcVO[] getMLCDetail(PatientVO patvo, UserVO _uservo)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MlcVO[] mlcVOs = null;
		try
		{
			
			tx.begin();

			MlcDAO mlcDao = new MlcDAO(tx);
			mlcVOs = mlcDao.getMlcDtlArrayBasedOnCrNo(patvo.getPatCrNo(), _uservo);
			
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return mlcVOs;
	}

	
	
	public void saveRedirectOfOPDPatDtl(List deptUnitRosterModDtlVOList,List roomChnageVOList,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
						
		try
		{
			tx.begin();
			DeptUnitRosterModDtlDAOi deptUnitRosterModDtlDAOi=new DeptUnitRosterModDtlDAO(tx);
			RoomChangeDetailDAOi roomChangeDetailDAOi=new RoomChangeDetailDAO(tx);
			DailyPatientDAOi dailyPatientDAOi=new DailyPatientDAO(tx);
			DeptUnitRosterDtlDAOi deptUnitRosterDtlDAOi=new DeptUnitRosterDtlDAO(tx);
			
			for(int i=0;i<deptUnitRosterModDtlVOList.size();i++)
			{
				DeptUnitRosterModDtlVO deptUnitRosterModDtlVO=(DeptUnitRosterModDtlVO)deptUnitRosterModDtlVOList.get(i);
				deptUnitRosterModDtlDAOi.create(deptUnitRosterModDtlVO, _userVO);
				deptUnitRosterDtlDAOi.updateChangeUnitRoom(deptUnitRosterModDtlVO, _userVO);
			}
			
			for(int i=0;i<roomChnageVOList.size();i++)
			{
				String fromRoomCode="";
				RoomChangeVO roomChangeVO=(RoomChangeVO)roomChnageVOList.get(i);
				roomChangeDetailDAOi.saveRoomChangeDetail(roomChangeVO, _userVO);
				
				if(roomChangeVO.getChnageToRoomCode()==null || roomChangeVO.getChnageToRoomCode().equals("null"))
				{
					fromRoomCode=roomChangeVO.getRoomName()+"("+roomChangeVO.getQNo()+")";
					roomChangeVO.setFromRoomCode(fromRoomCode);
					
				}
				else
				{
					roomChangeVO.setFromRoomCode(roomChangeVO.getQNo());
				}
				
				
				dailyPatientDAOi.updateChangeRoomDetail(roomChangeVO, _userVO);
			}
			
			
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
	}
	*/
	
	//Offline new Registration 1-oct-2010
	/*public EpisodeVO[] saveOfflineRegistration_old_method(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{

			tx.begin();
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			PatientDAO patDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDtlDao=new DirectChargeDetailDAO(tx);
			RegChargeDtlVO[] regChgDtlVO = new RegChargeDtlVO[_arrEpisodeVO.length];
			DirectChageDetailVO[] directChargeVO=new DirectChageDetailVO[_arrEpisodeVO.length];
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			//PrimaryCategoryChangeDAO primaryCategoryChangeDAO = new PrimaryCategoryChangeDAO(tx);
			//PrimaryCategoryChangeVO primaryCategoryChangeVO = new PrimaryCategoryChangeVO();
			//synchronized (patDao.getLock())
			//{
				EpisodeReferVO _episodeReferVO = new EpisodeReferVO();
				EpisodeRefDtlVO episodeRefDtlVO = new EpisodeRefDtlVO();
				//List printData = new ArrayList();

				_patientVO.setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
				_patientVO.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_NOT_ADMITTED);
				_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				//_patientVO.setPatRegCatCode(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL);
				_patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
				_patientVO = PatientBOSupport.checkForRenewalAtSaveToPatintDao(_patientVO, _userVO, tx);
				_patientVO.getPatAddress().setPatCrNo(_patientVO.getPatCrNo());

				// Create a new Address
				_patientVO.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.getPatAddress().setSeatId(_patientVO.getSeatId());
				AddressVO addressVO=_patientVO.getPatAddress();
				addressVO.setPatIsUrban(_patientVO.getPatIsUrban());
				addDao.createNewAddress(addressVO, _userVO);

				
				///temp code for trapping error
				if(_arrEpisodeVO==null || _arrEpisodeVO.length==0)
						throw new HisApplicationExecutionException("Episode Array in null");
				///////////
				for (int i = 0; i < _arrEpisodeVO.length; i++)
				{
					DailyPatientVO dailyPatVO = new DailyPatientVO();

					// Create DailyPatientVO from patientVO: Populate it
					HelperMethods.populate(dailyPatVO, _patientVO);
					// set Other required detials in episodeVO
					// explicitly set setIsValid(RegistrationConfig.IS_VALID_ACTIVE)
					dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);

					// populate the episodeVO with the general details
					PatientBOHelper.setNewPatRegEpisodeVO(_arrEpisodeVO[i]);
					if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD))
						{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
					}
					if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_SPECIAL_CLINIC)){
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
					}
					if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_EMERGENCY_CLINIC)){
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG);
					}
					
					_arrEpisodeVO[i].setPatCrNo(dailyPatVO.getPatCrNo());
					_arrEpisodeVO[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
					_arrEpisodeVO[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());

					_arrEpisodeVO[i].setIsValid(Config.IS_VALID_ACTIVE);
					// populate this dailyPatient VO with _arrEpisodeVO[i]
					_patientVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
					HelperMethods.populate(dailyPatVO, _arrEpisodeVO[i]);
					HelperMethods.populate(dailyPatVO, _patientVO);

					dailyPatVO.setIsReferred(_patientVO.getIsReferred());
					// PatientBOHelper.setDailyPatientDetails(dailyPatVO);

					// Create a new entry in daily patient detail
					// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO

					dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
					
					dailyPatVO.setEpisodeVisitNo("1");
					
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
						dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL);
					else
						dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
					
					//daily patient offline registration
					dailyPatVO = dailyPatDao.createNewOfflineRegistration(dailyPatVO, _userVO);

					//HelperMethods.populate(primaryCategoryChangeVO, dailyPatVO);
					//primaryCategoryChangeVO.setPatNewPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());

					HelperMethods.populate(_arrEpisodeVO[i], dailyPatVO);
					
					
					// Create new entry in Episode detail
					
					
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{

						_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						_episodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
						episodeRefDtlVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					}
					else _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					
					_arrEpisodeVO[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
				
					_arrEpisodeVO[i].setEntryDate(_patientVO.getSystemDate());
					
					//Check for renewal and inserts data in episode dtl table
					PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(_arrEpisodeVO[i], _userVO, tx);
					///////////////////
					_arrEpisodeVO[i].setDepartmentCode(_arrEpisodeVO[i].getDepartmentUnitCode().substring(0, 3));

					
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						HelperMethods.populate(_episodeReferVO, _patientVO);
						HelperMethods.populate(_episodeReferVO, _arrEpisodeVO[i]);
						// ///////episode referDTL////////////////
						//no need to cal as we already have the visit number
						//String[] array = episodeDao.getEpisdoeCode(_arrEpisodeVO[i].getPatCrNo(), _arrEpisodeVO[i].getDepartmentUnitCode(), _userVO);
						//_arrEpisodeVO[i].setEpisodeVisitNo(array[2]);

						HelperMethods.populate(episodeRefDtlVO, _arrEpisodeVO[i]);
						episodeRefDtlVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
						episodeRefDtlVO.setExternalHospitalCode(_patientVO.getPatRefGnctdHospitalCode());
						episodeRefDtlVO.setExternalHospitalDepartment(_patientVO.getPatRefGnctdHospitalDept());
						episodeRefDtlVO.setExternalHospitalDepartmentUnit(_patientVO.getPatRefGnctdHospitalDeptUnit());
						episodeRefDtlVO.setExternalHospitalDoctorName(_patientVO.getPatRefDoctor());
						episodeRefDtlVO.setExternalHospitalName(_patientVO.getPatRefHospitalName());
						episodeRefDtlVO.setExternalHospitalPatCrNo(_patientVO.getPatRefGnctdHospitalCrno());
						episodeRefDtlVO.setToDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
						episodeRefDtlVO.setToDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
						episodeRefDtlDAO.createNewRegistration(episodeRefDtlVO, _userVO);
						// ///////episode referDTL////////////////

						// episodeReferDAO.create(_episodeReferVO, _userVO);
					}
					regChgDtlVO[i] = new RegChargeDtlVO();
					regChgDtlVO[i].setTariffId(_userVO.getTariffID());
					regChgDtlVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
					regChgDtlVO[i].setModuleId(_userVO.getModuleId());
					HelperMethods.populate(regChgDtlVO[i], _arrEpisodeVO[i]);
					regChgDtlVO[i].setPatAmountCollected(_patientVO.getPatAmountCollected());
					regChgDtlVO[i].setSystemIPAddress(_patientVO.getSystemIPAddress());
					if (!(regChgDtlVO[i].getPatAmountCollected().equals("0") || regChgDtlVO[i].getPatAmountCollected().equals("") || regChgDtlVO[i]
							.getPatAmountCollected() == null)) 
					{
						///registration module billing
						regChgDtlDAO.create(regChgDtlVO[i], _userVO);
						directChargeVO[i]=new DirectChageDetailVO();
						HelperMethods.populate(directChargeVO[i],regChgDtlVO[i]);
						directChargeDtlDao.create(directChargeVO[i], _userVO);
						////Billing module billing///
						//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], _userVO);
					}
					//primaryCategoryChangeDAO.create(primaryCategoryChangeVO, _userVO);
					// createSlip(_patientVO.getSystemIPAddress(),printData);
					
					/////Query to fetch Unit working days for printing////
					
					//	_arrEpisodeVO[i].setUnitWorkingDays(essentialDAO.getUnitWorkingDays(_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
					
					/////////////////////////////////////////////////////
					if(Config.CLIENT.equals(Config.CLIENT_GNCTD)){	
						*//*** Query to fetch Disclaimer*********************//*
							String patRegCatCode=_patientVO.getPatRegCatCode();
							String usablityFlag="";
							if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
								usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
							if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL)){
								usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
							}	
							_arrEpisodeVO[i].setDisclaimer(getDisclamer(usablityFlag,_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
							
						*//***********************************************//*	
					}	
				}

			//}
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{

			tx.close();
		}
		return _arrEpisodeVO; // <<<
	}// each VisitStampVO keeps PatientVO
	
*/	//Offline new Registration 1-oct-2010
/*	public EpisodeVO[] saveOfflineRegistration(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAOObj=null;
		synchronized (PatientDAO.class)
		{
		try
		{

			tx.begin();
			hisDAOObj = new HisDAO("Registration","PatientBO");
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			PatientDAO patDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDtlDao=new DirectChargeDetailDAO(tx);
			RegChargeDtlVO[] regChgDtlVO = new RegChargeDtlVO[_arrEpisodeVO.length];
			DirectChageDetailVO[] directChargeVO=new DirectChageDetailVO[_arrEpisodeVO.length];
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
			EpisodeReferVO _episodeReferVO = new EpisodeReferVO();
				EpisodeRefDtlVO episodeRefDtlVO = new EpisodeRefDtlVO();
				
				_patientVO.setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
				_patientVO.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_NOT_ADMITTED);
				_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				
				_patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
				_patientVO.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.getPatAddress().setSeatId(_patientVO.getSeatId());
				AddressVO addressVO=_patientVO.getPatAddress();
				addressVO.setPatIsUrban(_patientVO.getPatIsUrban());


				DailyPatientVO dailyPatVO = new DailyPatientVO();

				dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);

				// populate the episodeVO with the general details
				PatientBOHelper.setNewPatRegEpisodeVO(_arrEpisodeVO[i]);
				if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD))
					{
					_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
					_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
				}
				if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_SPECIAL_CLINIC)){
					_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
					_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
				}
				if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_EMERGENCY_CLINIC)){
					_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC);
					_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_EMG);
				}
				
				
				_arrEpisodeVO[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
				_arrEpisodeVO[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());

				_arrEpisodeVO[i].setIsValid(Config.IS_VALID_ACTIVE);
				// populate this dailyPatient VO with _arrEpisodeVO[i]
				_patientVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
				HelperMethods.populate(dailyPatVO, _arrEpisodeVO[i]);
				HelperMethods.populate(dailyPatVO, _patientVO);

				dailyPatVO.setIsReferred(_patientVO.getIsReferred());
				// PatientBOHelper.setDailyPatientDetails(dailyPatVO);

				// Create a new entry in daily patient detail
				// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO

				dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
				
				dailyPatVO.setEpisodeVisitNo("1");
				
				if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL);
				else
					dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
				

			////generate queue number
				
				dailyPatVO=dailyPatDao.generateQueueNumberRoomWise(dailyPatVO, _userVO);
			
			//generating the Cr Number
			String CrNO=patDao.generateCrNumber(_userVO);
			_patientVO.setPatCrNo(CrNO);
			_patientVO.getPatAddress().setPatCrNo(_patientVO.getPatCrNo());
			_patientVO.setSeatId(_userVO.getSeatId());
			
			///get age or DOB
			//_patientVO=patDao.getDOBAndAge(_patientVO);	//commented by Adil Wasi as for normal registration process at line no 302
			
			//seting dialypatientvo details
			dailyPatVO.setPatCrNo(CrNO);
			dailyPatVO.setPatAge(_patientVO.getPatAge());
			dailyPatVO.setPatDOB(_patientVO.getPatDOB());
		
		
				
				///temp code for trapping error
				if(_arrEpisodeVO==null || _arrEpisodeVO.length==0)
						throw new HisApplicationExecutionException("Episode Array in null");
				///////////
				
										
					//daily patient offline registration
					dailyPatVO = dailyPatDao.createNewRegistration(hisDAOObj,dailyPatVO, _userVO);
					//insert into patient
					String p_mode="1";
					_patientVO = PatientBOSupport.checkForRenewalAtSaveToPatintDao(hisDAOObj,_patientVO, _userVO, tx,p_mode);
					//insert into address dteial
					addDao.createNewAddress(hisDAOObj,addressVO, _userVO);


				
					HelperMethods.populate(_arrEpisodeVO[i], dailyPatVO);
					
					
					// Create new entry in Episode detail
					
					
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{

						_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						_episodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
						episodeRefDtlVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					}
					else _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					
					_arrEpisodeVO[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
				
					_arrEpisodeVO[i].setEntryDate(_patientVO.getSystemDate());
					
					//Check for renewal and inserts data in episode dtl table
					//PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(hisDAOObj,_arrEpisodeVO[i], _userVO, tx);	// Commented By Adil Wasi
					///////////////////
					
					//changed on 14-feb-2013 by Adil Wasi as it is changed by prakhar misra at 14-feb-2012 for Registration
					
					//to calculate expiry date out of procedure so that it cold be used for prinintg
					String expiryDate=episodeDao.getExpiryDate(_arrEpisodeVO[i],_userVO);
					
					_arrEpisodeVO[i].setExpiryDate(expiryDate);
					episodeDao.create(hisDAOObj, _arrEpisodeVO[i], _userVO);
					
					_arrEpisodeVO[i].setDepartmentCode(_arrEpisodeVO[i].getDepartmentUnitCode().substring(0, 3));

					
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						HelperMethods.populate(_episodeReferVO, _patientVO);
						HelperMethods.populate(_episodeReferVO, _arrEpisodeVO[i]);
						// ///////episode referDTL////////////////
						//no need to cal as we already have the visit number
						//String[] array = episodeDao.getEpisdoeCode(_arrEpisodeVO[i].getPatCrNo(), _arrEpisodeVO[i].getDepartmentUnitCode(), _userVO);
						//_arrEpisodeVO[i].setEpisodeVisitNo(array[2]);

						HelperMethods.populate(episodeRefDtlVO, _arrEpisodeVO[i]);
						episodeRefDtlVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
						episodeRefDtlVO.setExternalHospitalCode(_patientVO.getPatRefGnctdHospitalCode());
						episodeRefDtlVO.setExternalHospitalDepartment(_patientVO.getPatRefGnctdHospitalDept());
						episodeRefDtlVO.setExternalHospitalDepartmentUnit(_patientVO.getPatRefGnctdHospitalDeptUnit());
						episodeRefDtlVO.setExternalHospitalDoctorName(_patientVO.getPatRefDoctor());
						episodeRefDtlVO.setExternalHospitalName(_patientVO.getPatRefHospitalName());
						episodeRefDtlVO.setExternalHospitalPatCrNo(_patientVO.getPatRefGnctdHospitalCrno());
						episodeRefDtlVO.setToDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
						episodeRefDtlVO.setToDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
						episodeRefDtlDAO.createNewRegistration(hisDAOObj,episodeRefDtlVO, _userVO);
						// ///////episode referDTL////////////////

						// episodeReferDAO.create(_episodeReferVO, _userVO);
					}
					regChgDtlVO[i] = new RegChargeDtlVO();
					regChgDtlVO[i].setTariffId(_userVO.getTariffID());
					regChgDtlVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
					regChgDtlVO[i].setModuleId(_userVO.getModuleId());
					HelperMethods.populate(regChgDtlVO[i], _arrEpisodeVO[i]);
					regChgDtlVO[i].setPatAmountCollected(_patientVO.getPatAmountCollected());
					regChgDtlVO[i].setSystemIPAddress(_patientVO.getSystemIPAddress());
					if (!(regChgDtlVO[i].getPatAmountCollected().equals("0") || regChgDtlVO[i].getPatAmountCollected().equals("") || regChgDtlVO[i]
							.getPatAmountCollected() == null)) 
					{
						///registration module billing
						regChgDtlDAO.create(hisDAOObj,regChgDtlVO[i], _userVO);
						directChargeVO[i]=new DirectChageDetailVO();
						HelperMethods.populate(directChargeVO[i],regChgDtlVO[i]);
						directChargeDtlDao.create(hisDAOObj,directChargeVO[i], _userVO);
						////Billing module billing///
						//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], _userVO);
					}
					
					/////////////////////////////////////////////////////
					if(Config.CLIENT.equals(Config.CLIENT_GNCTD)){	
						*//*** Query to fetch Disclaimer*********************//*
							String patRegCatCode=_patientVO.getPatRegCatCode();
							String usablityFlag="";
							if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
								usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
							if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL)){
								usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
							}	
							_arrEpisodeVO[i].setDisclaimer(getDisclamer(usablityFlag,_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
							
					}	
					*//***********************************************//*	
					synchronized (hisDAOObj) {
						hisDAOObj.fire();
					}
				}

			//}
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			if (hisDAOObj != null) {
				//daoObj.free();
				hisDAOObj = null;
			}
			tx.close();
		}
		return _arrEpisodeVO; // <<<
		}
	}*/
	// each VisitStampVO keeps PatientVO

	
	/*public EpisodeVO[] newDepartmentVisitStampForDuplicate(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientDAO patDao = new PatientDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			RegChargeDtlVO[] regChgDtlVO = new RegChargeDtlVO[_arrEpisodeVO.length];
			DirectChageDetailVO[] directChargeVo = new DirectChageDetailVO[_arrEpisodeVO.length];
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			String amount = _arrEpisodeVO[0].getPatAmountCollected();
			synchronized (patDao.getLock())
			{
			////checking if any general episode in this department exist, then send for old patient visit
				for (int i = 0; i < _arrEpisodeVO.length; i++)
				{
				
					
					int count=episodeDao.checkGeneralEpisodeInDepartment(_arrEpisodeVO[i],_userVO);
					if(count>0)
					{
						throw new HisOPDPatientEligibilityException();
					}
				}
				////////////////////////////////////////////////////////////////

				for (int i = 0; i < _arrEpisodeVO.length; i++)
				{
				
					DailyPatientVO dailyPatVO = new DailyPatientVO();
					// Create DailyPatientVO from patientVO: Populate it
					HelperMethods.populate(dailyPatVO, _patientVO);
					dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
					
					// populate the episodeVO with the general details
					PatientBOHelper.setNewPatRegEpisodeVO(_arrEpisodeVO[i]);
					String referMlcNo="";
					referMlcNo=episodeRefVO.getMlcNo();
					if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD)) 
						{
						
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
						
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
						}
					else
						{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
						}
					_arrEpisodeVO[i].setPatCrNo(dailyPatVO.getPatCrNo());
					_arrEpisodeVO[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
					_arrEpisodeVO[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());

					
					_arrEpisodeVO[i].setIsValid(Config.IS_VALID_ACTIVE);
					// populate this dailyPatient VO with _arrEpisodeVO[i]
					
					HelperMethods.populate(dailyPatVO, _arrEpisodeVO[i]);
					_patientVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
					HelperMethods.populate(dailyPatVO, _patientVO);
					dailyPatVO.setIsReferred(_patientVO.getIsReferred());
					// PatientBOHelper.setDailyPatientDetails(dailyPatVO);
					
					// Create a new entry in daily patient detail
					// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO
					
					//dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					///In case of new dept visit is old is false
					dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
						}
						if(_patientVO.getIsPatReferByList()!=null && _patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
						}
					}
					else
						dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
					
					
					dailyPatVO.setIsNewFileRequired(Config.FILE_NO_GENERATION_FLAG);
					dailyPatVO.setEpisodeVisitNo("1");
					///old query 
					//dailyPatVO = dailyPatDao.create(dailyPatVO, _userVO);
					////new query for mlC refer logic
					dailyPatVO = dailyPatDao.createNewDepartment(dailyPatVO, _userVO,referMlcNo);
					HelperMethods.populate(_arrEpisodeVO[i], dailyPatVO);
					if(dailyPatVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC))
						_arrEpisodeVO[i].setMlcNo(referMlcNo);
					//Create new entry in Episode detail
					

					// Create new entry in Episode detail
				//	_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);


					
					 * if(_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
					 * System.out.println("hi!!!!");
					 * _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					 * System.out.println("_arrEpisodeVO[i].getEpisodeReferAccept()"+_arrEpisodeVO[i].getEpisodeReferAccept());
					 * episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL); } else
					 * _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					 
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL);
						}

					}
					else
					{
						_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
						_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					}

					_arrEpisodeVO[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
					
					////setting visit number//
					
					_arrEpisodeVO[i].setEpisodeVisitNo("1");
					_arrEpisodeVO[i].setEntryDate(_patientVO.getSystemDate());
					//saving episode dtl
					PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(_arrEpisodeVO[i], _userVO, tx);
					// episodeDao.create(_arrEpisodeVO[i], _userVO);

					_arrEpisodeVO[i].setDepartmentCode(_arrEpisodeVO[i].getDepartmentUnitCode().substring(0, 3));
					String deptCode = _arrEpisodeVO[i].getDepartmentCode();
					String roomCode = _arrEpisodeVO[i].getRoomCode();
					String deptUnitCode = _arrEpisodeVO[i].getDepartmentUnitCode();
					List al = new ArrayList();
					al = essentialDAO.getNameValues(deptCode, roomCode, deptUnitCode, _userVO);
					_arrEpisodeVO[i].setDepartment(al.get(0).toString());
					_arrEpisodeVO[i].setRoom(al.get(1).toString());
					_arrEpisodeVO[i].setDepartmentUnit(al.get(2).toString());

					// HelperMethods.populate(_arrEpisodeVO[i], al);




					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						// _patientVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
						// System.out.println("_episodeReferVO.getIsRefferInOut(): "+_episodeReferVO.getIsRefferInOut());
						// HelperMethods.populate(_episodeReferVO, _patientVO);
						// HelperMethods.populate(_episodeReferVO, _arrEpisodeVO[i]);
						// ///////episode referDTL////////////////
						
						 * String[] array = episodeDao.getEpisdoeCode(_arrEpisodeVO[i].getPatCrNo(), _arrEpisodeVO[i]
						 * .getDepartmentUnitCode(), _userVO); _arrEpisodeVO[i].setEpisodeVisitNo(array[2]);
						 
						String refEpisode = episodeRefVO.getEpisodeCode();
						String refEpisodeVisit = episodeRefVO.getEpisodeVisitNo();
						String serialNo = episodeRefVO.getSerialNo();
						HelperMethods.populate(episodeRefVO, _arrEpisodeVO[i]);
						episodeRefVO.setSystemIPAddress(_patientVO.getSystemIPAddress());

						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							episodeRefVO.setEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
							episodeRefVO.setEpisodeVisitNo("1");

						}
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							episodeRefVO.setToDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
							episodeRefVO.setToDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
							episodeRefVO.setToEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
							episodeRefVO.setToEpisodeVisitNo("1");
							episodeRefVO.setEpisodeCode(refEpisode);
							episodeRefVO.setEpisodeVisitNo(refEpisodeVisit);
							episodeRefVO.setExternalHospitalCode("");
							episodeRefVO.setSerialNo(serialNo);
						}

						if (_patientVO.getIsPatReferByList() != null
								&& _patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							episodeRefDtlDAO.updateAcceptanceDate(episodeRefVO, _userVO);
						}
						else
						{

							episodeRefDtlDAO.create(episodeRefVO, _userVO);
						}
						// ///////episode referDTL////////////////
						// System.out.println("_episodeReferVO.getEpisodeVisitNo():::"+_episodeReferVO.getEpisodeVisitNo());
						// episodeReferDAO.create(_episodeReferVO, _userVO);
					}
					if (amount != null && !amount.equals("") && !amount.equals("0"))
					{
						regChgDtlVO[i] = new RegChargeDtlVO();
						regChgDtlVO[i].setTariffId(_userVO.getTariffID());
						regChgDtlVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
						regChgDtlVO[i].setModuleId(_userVO.getModuleId());
						HelperMethods.populate(regChgDtlVO[i], _arrEpisodeVO[i]);
						regChgDtlVO[i].setPatAmountCollected(amount);
						regChgDtlVO[i].setSystemIPAddress(_patientVO.getSystemIPAddress());
						
						////registration module billing
						regChgDtlDAO.create(regChgDtlVO[i], _userVO);
						directChargeVo[i] = new DirectChageDetailVO();
						HelperMethods.populate(directChargeVo[i], regChgDtlVO[i]);
						directChargeDao.create(directChargeVo[i], _userVO);
						///Billing module integration
						//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], _userVO);
						// createSlip(_patientVO.getSystemIPAddress(),printData);
					}
					
				/////Query to fetch Unit working days for printing////
					
				//	_arrEpisodeVO[i].setUnitWorkingDays(essentialDAO.getUnitWorkingDays(_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
				
				/////////////////////////////////////////////////////
				if (Config.CLIENT.equals(Config.CLIENT_GNCTD)) {
					
					*//****** query to fetch disclaimer for printing ************//*
						_arrEpisodeVO[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL, _arrEpisodeVO[i].getDepartmentUnitCode(), _userVO));
						
					*//**************************************************//*	
				}	
				}
			}
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch(HisOPDPatientEligibilityException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisOPDPatientEligibilityException(e.getMessage());
		}
		
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			// System.out.println("_patientVO.getDepartment()::::"+_patientVO.getDepartment());
			System.out.println("_arrEpisodeVO.getDepartment()::::" + _arrEpisodeVO[0].getDepartment());

			tx.close();
		}
		return _arrEpisodeVO;
	}

	public void saveDeactivateUnitRoomDtl(List deptUnitRosterModDtlVOList,List deptUnitRosterModDtlVOInActiveList,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
				
		try
		{
			tx.begin();
			DeptUnitRosterModDtlDAOi deptUnitRosterModDtlDAOi=new DeptUnitRosterModDtlDAO(tx);
			DeptUnitRosterDtlDAOi deptUnitRosterDtlDAOi=new DeptUnitRosterDtlDAO(tx);
			
if(deptUnitRosterModDtlVOList!=null && deptUnitRosterModDtlVOList.size()!=0){
			for(int i=0;i<deptUnitRosterModDtlVOList.size();i++)
			{
				DeptUnitRosterModDtlVO deptUnitRosterModDtlVO=(DeptUnitRosterModDtlVO)deptUnitRosterModDtlVOList.get(i);
				deptUnitRosterModDtlDAOi.create(deptUnitRosterModDtlVO, _userVO);
				deptUnitRosterDtlDAOi.updateChangeUnitRoom(deptUnitRosterModDtlVO, _userVO);
			}
}			


if(deptUnitRosterModDtlVOInActiveList!=null && deptUnitRosterModDtlVOInActiveList.size()!=0){
			for(int i=0;i<deptUnitRosterModDtlVOInActiveList.size();i++)
			{
				DeptUnitRosterModDtlVO deptUnitRosterModDtlVO=(DeptUnitRosterModDtlVO)deptUnitRosterModDtlVOInActiveList.get(i);
				deptUnitRosterModDtlDAOi.createInActive(deptUnitRosterModDtlVO, _userVO);
				deptUnitRosterDtlDAOi.updateChangeUnitRoomInActive(deptUnitRosterModDtlVO, _userVO);
			}
}						
			
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
	}
	
	
	public DailyPatientVO[] getTodayPatientListByDeptUnitCode(String mode,String departmentUnitCode, UserVO _userVO){
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DailyPatientVO [] dailyPatientVOs=null;
		try {
			tx.begin();
			DailyPatientDAOi dailyPatientDAO = new DailyPatientDAO(tx);
			dailyPatientVOs=dailyPatientDAO.getTodayPatientListByDeptUnitCode(mode,departmentUnitCode,_userVO);
			
			
		} catch (HisRecordNotFoundException e) {
			
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		
		catch (HisDataAccessException e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return dailyPatientVOs;
	}
	
	public void saveFileNumberPrinting(EpisodeVO[] episodeVOs,RegCardPrintVO[] regCardPrintVOs, UserVO _userVO){
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try {
			tx.begin();
			DailyPatientDAOi dailyPatientDAO = new DailyPatientDAO(tx);
			RegCardPrintDtlDAO regCardPrintDtlDAO=new RegCardPrintDtlDAO(tx);
			DailyPatientVO dailyPatientVO=null;
			//RegCardPrintVO regCardPrintVO=null;
			for(int i=0;i<episodeVOs.length;i++){
				dailyPatientVO=new DailyPatientVO();
				//regCardPrintVO=new RegCardPrintVO();
				HelperMethods.populate(dailyPatientVO, episodeVOs[i]);
				//HelperMethods.populate(regCardPrintVO, episodeVOs[i]);
				//regCardPrintVO.setCardPrintFlag(RegistrationConfig.FILE_NUMBER_PRINT);
				dailyPatientDAO.updateFilePrintFlag(dailyPatientVO, _userVO);
				regCardPrintDtlDAO.saveReprintCard(regCardPrintVOs[i], _userVO);
			}
			
		} catch (HisRecordNotFoundException e) {
			
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		
		catch (HisDataAccessException e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}
	
	
	
	
	 * Stamps the visit of a patient when he/she re-visits a department. Saves data in Daily Patient dtl, and Episode dtl
	 * tables.
	 * 
	 * @param _arrEpisodeVO[] Provides the departments in which patient has been registered.
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return Array of EpisodeVO with values stored in DB.
	 
	public EpisodeVO[] oldDeptVisitStampRoomWise(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefDtlVO,UserVO _userVO )
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			PatientBOSupport.oldDeptVisitStampRoomWise(_arrEpisodeVO, _patientVO, _userVO, tx, episodeRefDtlVO);

		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch(HisAppointmentNotAvailableException e){
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());	
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			tx.close();
		}
		return _arrEpisodeVO;
	}

	@Override
	public EpisodeVO[] newPatRegistration(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, UserVO _userVO, HttpServletRequest _request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] newPatSplRegistration(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, UserVO _userVO, HttpServletRequest _request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO searchPatientByCrNo(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO searchPatientByCrNo(PatientVO _patientVO, UserVO _userVO,
			String visitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO[] searchPatient(HashMap _searchMap, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkPatientStatus(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO[] getCrNoForModification(UserVO _userVO,
			String episodeVisitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getCrNoModification(UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchPatientEpiosdeByCrNo(PatientVO _patientVO,
			UserVO _userVO, String visitType, String isConfirmed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] newDeptVisitStamp(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] newDepartmentVisitStamp(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,
			PatientVO _oldPatientVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] newSplUnitVisitStamp(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,
			PatientVO _oldPatientVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] newDepartmentVisitStampRoomWise(
			EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO,
			EpisodeRefDtlVO episodeRefVO, UserVO _userVO,
			PatientVO _oldPatientVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] oldDeptVisitStamp(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, UserVO _userVO, EpisodeRefDtlVO episodeRefVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean patCategoryChange(PatientVO _patientVO,
			EpisodeVO[] _arrEpisodeVO, ChangeCategoryVO[] _arrChangeCategoryVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkModify(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AddressVO[] getPatAddressAll(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressVO checkAddModify(AddressVO[] _arrAddressVO,
			PatientVO _patientVO, AddressVO addVO, UserVO _userVO,
			String add_modify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO getPatientDtl(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO searchPatientByIdNo(String _idNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO getPrevSystemPatDetail(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] duplicateCardPrinting(RegChargeDtlVO[] _regChargeVO,
			RegCardPrintVO[] _regCardPrintVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnitChangeVO unitChangeOldDeptVisit(PatientVO _patientVO,
			EpisodeVO _episodeVO, UnitChangeVO _unitChangeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO emergencyPatRegistration(PatientVO _patientVO,
			PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO,
			HttpServletRequest _request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO emergencyOldDeptVisitStamp(PatientVO _patientVO,
			EpisodeVO _episodeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePatientNotAttendedDetail(EpisodeActionDtlVO epActionDtlVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientAttendedAdmittedDetail(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MlcVO patMlcDtl(MlcVO _mlcVO, UserVO _userVO,
			PatientBroughtByDtlVO _patBroughtByDtlVO,
			PoliceVerificationDtlVO policeVerDtlVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO getMlcDtl(PatientVO _patientVO, MlcVO _mlcVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyMlcDtl(MlcVO _mlcVO, UserVO _userVO,
			PatientBroughtByDtlVO _patBroughtByDtlVO,
			PoliceVerificationDtlVO policeVerDtlVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveDeathDetail(EpisodeDeathVO _episodeDeathVO,
			PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveBroughtDeathDetail(EpisodeDeathVO _episodeDeathVO,
			DailyPatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientAttendedDisposed(
			EpisodeDiagnosisVO[] _episodeDiagVO,
			EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientReferredOut(EpisodeReferVO _episodeReferVO,
			EpisodeActionDtlVO episodedtlActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientAttendedObserved(
			EpisodeDiagnosisVO[] _episodeDiagVO,
			EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientAttendedRefInCasuality(
			EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePatientRefInWard(EpisodeActionDtlVO _episodedtlActionDtlVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EpisodeVO isEmergency(PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOpdVisitConfirm(EpisodeVO _episodeVO,
			EpisodeActionDtlVO _episodeActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOpdEpisodeClose(EpisodeVO _episodeVO,
			EpisodeActionDtlVO _episodeActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EpisodeVO[] isOpd(PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO isCsultyMlcEligible(PatientVO _patVO, String _episodeCode,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO getMlcDtlBasedOnMlcNo(MlcVO _mlcVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO getMlcDtlBasedOnCrNo(MlcVO vo, UserVO _uservo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO getEmgOpenEpisode(PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchPatientEpiosdeByCrNo(PatientVO _patientVO,
			UserVO _userVO, String visitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean unknownToKnownConversion(PatientVO _patientVO,
			PatientVO patientVOOldData, VerificationDocumentVO verifDocVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeReferVO searchPatientRefEpisodeByEpisodeNo(
			PatientVO _patientVO, EpisodeVO _episodeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean referDtlModificationMRD(PatientVO _patientVO,
			EpisodeReferVO _episodeReferVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO[] referredDeptVisitStamp(EpisodeVO[] _arrEpisodeVO,
			EpisodeReferVO _episodeReferVO, PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnknownChangeVO getConvertedToKnownDetails(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeReferVO[] retrieveRefInternalEpisodeDtl(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkPatientStatusByVisitType(PatientVO _patientVO,
			UserVO _userVO, String visitType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO[] searchAllEpisodeByCrNo(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO isPatientMlc(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO[] getMlcDtlBasedOnCrNoandImage(MlcVO _mlcVo, UserVO _uservo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveMlcDoc(MlcVO _mlcVo, UserVO _uservo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkOpenEmgEpisodeByCrNo(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO getLastEpisodeInEmergency(PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] retrieveAllReferredEpisodesByCrNo(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeReferVO[] getAllOpenOPDEpisodes(String crNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeRefDtlVO[] retrieveAllOpenOPDEpisodes(String crNo,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeRefDtlVO[] retrieveAllSpecialEpisodes(String crNo,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getAllOpenEpisodes(String crNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePrimaryCategory(
			PrimaryCategoryChangeVO _primCatChangeVO, UserVO _userVO,
			VerificationDocumentVO _veriDocVO, EmpMasterVO _empMasterVO,
			String _patIdNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeSecondaryCategory(
			SecondaryCategoryChangeVO[] _secCatChangeVO,
			SecondaryCategoryChangeVO[] secCatRevokeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO[] changeFileNo(FileNumberChangeVO[] _fileNoChangeVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveRenewalDtl(EpisodeVO _episodeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO[] searchOldPatientEpisodesByCrNo(String crNo,
			UserVO _userVO, String visitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchOldPatientEpisodesByCrNo(String crNo,
			UserVO _userVO, String visitType, String isRenewal,
			String patCatagoryCode, String tariffId, String serviceId,
			String episodeType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchOldPatientEmgEpisodesByCrNo(String crNo,
			UserVO _userVO, String visitType, String renewalTariffId,
			String patPrimaryCatCode, String registrationServiceId,
			String isRenewal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean patDtlModificationMRD(PatientVO _patientVO,
			PatientVO _patientVOOldData, UserVO _userVO,
			VerificationDocumentVO _verDoc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EpisodeVO[] saveNewSpVisiStamp(EpisodeVO[] episodeVO, UserVO userVO,
			PatientVO patientVO, RegChargeDtlVO regChargeDtlVO,
			EpisodeRefDtlVO episodeRefDtlVO, PatientVO _oldPatientVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyPatientVO searchDailyPatientByCrNo(
			DailyPatientVO dailyPatientVO, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyPatientVO searchDailyPatientByCrNoWithoutHospital(
			DailyPatientVO dailyPatientVO, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO[] searchPatientByNationalID(String nationalID,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO[] searchPatientByContactNo(String contactNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientVO[] searchPatientByEmployeeID(String employeeID,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO getEpisodeVisitByCrno(String _patCrNo, String _visitDate,
			String _unitCode, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDiagnosisDetails(EpisodeVO episodeVO,
			EpisodeDiagnosisVO[] episodeDiagnosisVO, UserVO userVO,
			String _episodeStatusInVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveMlcDocUpload(MlcDocUploadDtlVO[] _mlcDocUploadDtlVOs,
			MlcVO[] _mlcVo, UserVO userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VerificationDocumentVO[] getVerificationDocumentDtl(
			VerificationDocumentVO verficationVo, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeRefDtlVO[] getReferPat(UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeRefDtlVO[] getSCReferPat(UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO[] getMlcDtlArrayBasedOnCrNo(String crNo, UserVO _uservo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MlcVO[] getMlcDtlArrayBasedOnMlcNo(String mlcNo, UserVO _uservo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] getAllOpenEpisodesVisitedToday(String _patCrNo,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EpisodeTriageDetailVO getPatTriageDtl(String _crNo, String _epiCode,
			String _visitNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] saveReprintCard(RegCardPrintVO[] regCardPrintVO,
			UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchOldPatientEpisodesByCrNoByDept(String crNo,
			String dept, UserVO _userVO, String visitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] searchOldPatientEpisodesByCrNoByUnit(String crNo,
			String unit, UserVO _userVO, String visitType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveParichitDetails(PatientParichitVO _patParichitVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getPrimaryCatListExceptPatientCat(String patCat, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] getAllOpenTodayMLCEpisodes(String _patCrNo,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean revokeMLCEpisodes(MLCRevokeEpisodeDetailVO[] _mlcRevVOs,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changePatientRoom(RoomChangeVO _roomChangeVO,
			EpisodeVO _episodeVO, DailyPatientVO _dailyPatientVO,
			UserVO _userVO, String _unitCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map getPatientAuditTrailEssentials(String patCrNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePatientAttendedUnderObservation(
			EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PatientBroughtByDtlVO searchPatientBropughtByDetailByCrNoNew(
			PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map checkEligibleForMLC(PatientVO _patVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePoliceVerification(PoliceVerificationDtlVO policeVerDtlVO,
			UserVO userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PoliceVerificationDtlVO getPoliceVerDtl(MlcVO mlcVO, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePatientDeathDetail(ANCDetailVO ancDetailVO,
			PatientDeathDetailVO patDeathDtlVO, UserVO userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkRecordAdded(String crNo, UserVO userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PatientDeathDetailVO getDeathDetailByCrNo(String crNo, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressVO getPatAddress(String crNo, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getHandoverToDetail(String crNo, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveHandoverToDetail(PatientDeathDetailVO patDeathDtlVO,
			String flagForPrint, UserVO userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PatientDeathDetailVO[] getDeadPatientList(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPatientMlcNo(PatientDetailVO selectedPatientVO,
			UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ANCDetailVO getPatientANCDetail(String crNo, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentUploadDtlVO[] getMlcUploadDetail(MlcVO _mlcVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDocumentDetail(DocumentUploadDtlVO[] docUploadVos,
			DocumentUploadDtlVO[] documentUploadRevokeDtlVOs, UserVO userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EpisodeVO[] getPatientAdmittedEpisodes(String crNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void revokeSecondaryCategory(
			SecondaryCategoryChangeVO[] _secCatChangeVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MlcVO[] getMLCDetail(PatientVO patVO, UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRedirectOfOPDPatDtl(List deptUnitRosterModDtlVOList,
			List roomChnageVOList, UserVO _userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EpisodeVO[] saveOfflineRegistration(EpisodeVO[] _arrEpisodeVO,
			PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int patientDetailModification(PatientVO _patientVO,
			PatientVO _patientVOOldData, String addModify,
			AddressVO _arrAddressVO, VerificationDocumentVO _verDoc,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map savePatientVisit(EpisodeVO[] _episodeVO, PatientVO _patVO,
			UserVO _userVO, EpisodeRefDtlVO _episodeRefDtlVO,
			EpisodeVO[] _episodeNewDeptVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map saveSplPatientVisit(EpisodeVO[] _episodeVO, PatientVO _patVO,
			UserVO _userVO, EpisodeRefDtlVO _episodeRefDtlVO,
			EpisodeVO[] _episodeNewDeptVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] saveEmgOldPatientVisit(PatientVO patientVO,
			EpisodeVO[] episodeVO, UserVO userVO,
			EpisodeRefDtlVO episodeRefDtlVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map saveEmergenyPatientVisitStamping(PatientVO patientVO,
			EpisodeVO[] oldVisitEpisodeVO, UserVO userVO,
			EpisodeRefDtlVO episodeRefDtlVO, EpisodeVO[] newVisitEpisodeVO,
			RegChargeDtlVO regChargeDtlVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientAdhaarDtlVO checkDupAdhaarPatient(PatientVO _patientVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveMCTSRegNo(MCTSRegistrationVO MCTSRegistrationVO_p,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PatientVO retrieveByCrNoFromMCTS(PatientVO _patientVO, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EpisodeVO[] getAllLatestEpisodesVisited(String _patCrNo,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PatientDetailVO> getPatientsForPoliceExaminationReqt(
			String _patCrNo, UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePoliceExaminationReqtDtl(
			PoliceExaminationReqtDtlVO policeExamReqtDtlVO, UserVO userVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PatientDetailVO> getPatientsForPoliceExaminationReportGeneration(
			String strMode_p, String strPatCrNo_p, String strEpisode_p,
			String strEpisodeVisitNo_p, UserVO objUserVO_p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePoliceExaminationReportGenerationDtl(
			PoliceExaminationReqtDtlVO objPoliceExamReqtDtlVO_p,
			UserVO objUserVO_p) {
		// TODO Auto-generated method stub
		
	}

	/*public EpisodeVO[] newDepartmentVisitStampRoomWiseForDuplicate(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientDAO patDao = new PatientDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			RegChargeDtlVO[] regChgDtlVO = new RegChargeDtlVO[_arrEpisodeVO.length];
			DirectChageDetailVO[] directChargeVo = new DirectChageDetailVO[_arrEpisodeVO.length];
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			String amount = _arrEpisodeVO[0].getPatAmountCollected();
			synchronized (patDao.getLock())
			{
			////checking if any general episode in this department exist, then send for old patient visit
				for (int i = 0; i < _arrEpisodeVO.length; i++)
				{
				
					
					int count=episodeDao.checkUnitWiseEpisodeInDepartment(_arrEpisodeVO[i],_userVO);
					if(count>0)
					{
						throw new HisOPDPatientEligibilityException();
					}
				}
				////////////////////////////////////////////////////////////////

				for (int i = 0; i < _arrEpisodeVO.length; i++)
				{
				
					DailyPatientVO dailyPatVO = new DailyPatientVO();
					// Create DailyPatientVO from patientVO: Populate it
					HelperMethods.populate(dailyPatVO, _patientVO);
					dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
					
					// populate the episodeVO with the general details
					PatientBOHelper.setNewPatRegEpisodeVO(_arrEpisodeVO[i]);
					String referMlcNo="";
					referMlcNo=episodeRefVO.getMlcNo();
					if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD)) 
						{
						
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
						
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
						}
					else
						{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
						}
					_arrEpisodeVO[i].setPatCrNo(dailyPatVO.getPatCrNo());
					_arrEpisodeVO[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
					_arrEpisodeVO[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());

					
					_arrEpisodeVO[i].setIsValid(Config.IS_VALID_ACTIVE);
					// populate this dailyPatient VO with _arrEpisodeVO[i]
					
					HelperMethods.populate(dailyPatVO, _patientVO);
					HelperMethods.populate(dailyPatVO, _arrEpisodeVO[i]);
					_patientVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
					dailyPatVO.setIsReferred(_patientVO.getIsReferred());
					// PatientBOHelper.setDailyPatientDetails(dailyPatVO);
					
					// Create a new entry in daily patient detail
					// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO
					
					//dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					///In case of new dept visit is old is false
					dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
						}
						if(_patientVO.getIsPatReferByList()!=null && _patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
						}
					}
					else
						dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
					
					
					dailyPatVO.setIsNewFileRequired(Config.FILE_NO_GENERATION_FLAG);
					dailyPatVO.setEpisodeVisitNo("1");
					///old query 
					//dailyPatVO = dailyPatDao.create(dailyPatVO, _userVO);
					////new query for mlC refer logic
					dailyPatVO = dailyPatDao.createNewDepartmentRoomWise(dailyPatVO, _userVO,referMlcNo);
					HelperMethods.populate(_arrEpisodeVO[i], dailyPatVO);
					if(dailyPatVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC))
						_arrEpisodeVO[i].setMlcNo(referMlcNo);
					//Create new entry in Episode detail
					

					// Create new entry in Episode detail
				//	_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);


					
					 * if(_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
					 * System.out.println("hi!!!!");
					 * _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					 * System.out.println("_arrEpisodeVO[i].getEpisodeReferAccept()"+_arrEpisodeVO[i].getEpisodeReferAccept());
					 * episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL); } else
					 * _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					 
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL);
						}

					}
					else
					{
						_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
						_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					}

					_arrEpisodeVO[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
					
					////setting visit number//
					
					_arrEpisodeVO[i].setEpisodeVisitNo("1");
					_arrEpisodeVO[i].setEntryDate(_patientVO.getSystemDate());
					//saving episode dtl
					PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(_arrEpisodeVO[i], _userVO, tx);
					// episodeDao.create(_arrEpisodeVO[i], _userVO);

					_arrEpisodeVO[i].setDepartmentCode(_arrEpisodeVO[i].getDepartmentUnitCode().substring(0, 3));
					String deptCode = _arrEpisodeVO[i].getDepartmentCode();
					String roomCode = _arrEpisodeVO[i].getRoomCode();
					String deptUnitCode = _arrEpisodeVO[i].getDepartmentUnitCode();
					List al = new ArrayList();
					al = essentialDAO.getNameValues(deptCode, roomCode, deptUnitCode, _userVO);
					_arrEpisodeVO[i].setDepartment(al.get(0).toString());
					_arrEpisodeVO[i].setRoom(al.get(1).toString());
					_arrEpisodeVO[i].setDepartmentUnit(al.get(2).toString());

					// HelperMethods.populate(_arrEpisodeVO[i], al);




					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						// _patientVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
						// System.out.println("_episodeReferVO.getIsRefferInOut(): "+_episodeReferVO.getIsRefferInOut());
						// HelperMethods.populate(_episodeReferVO, _patientVO);
						// HelperMethods.populate(_episodeReferVO, _arrEpisodeVO[i]);
						// ///////episode referDTL////////////////
						
						 * String[] array = episodeDao.getEpisdoeCode(_arrEpisodeVO[i].getPatCrNo(), _arrEpisodeVO[i]
						 * .getDepartmentUnitCode(), _userVO); _arrEpisodeVO[i].setEpisodeVisitNo(array[2]);
						 
						String refEpisode = episodeRefVO.getEpisodeCode();
						String refEpisodeVisit = episodeRefVO.getEpisodeVisitNo();
						String serialNo = episodeRefVO.getSerialNo();
						HelperMethods.populate(episodeRefVO, _arrEpisodeVO[i]);
						episodeRefVO.setSystemIPAddress(_patientVO.getSystemIPAddress());

						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							episodeRefVO.setEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
							episodeRefVO.setEpisodeVisitNo("1");

						}
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							episodeRefVO.setToDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
							episodeRefVO.setToDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
							episodeRefVO.setToEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
							episodeRefVO.setToEpisodeVisitNo("1");
							episodeRefVO.setEpisodeCode(refEpisode);
							episodeRefVO.setEpisodeVisitNo(refEpisodeVisit);
							episodeRefVO.setExternalHospitalCode("");
							episodeRefVO.setSerialNo(serialNo);
						}

						if (_patientVO.getIsPatReferByList() != null
								&& _patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							episodeRefDtlDAO.updateAcceptanceDate(episodeRefVO, _userVO);
						}
						else
						{

							episodeRefDtlDAO.create(episodeRefVO, _userVO);
						}
						// ///////episode referDTL////////////////
						// System.out.println("_episodeReferVO.getEpisodeVisitNo():::"+_episodeReferVO.getEpisodeVisitNo());
						// episodeReferDAO.create(_episodeReferVO, _userVO);
					}
					if (amount != null && !amount.equals("") && !amount.equals("0"))
					{
						regChgDtlVO[i] = new RegChargeDtlVO();
						regChgDtlVO[i].setTariffId(_userVO.getTariffID());
						regChgDtlVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
						regChgDtlVO[i].setModuleId(_userVO.getModuleId());
						HelperMethods.populate(regChgDtlVO[i], _arrEpisodeVO[i]);
						regChgDtlVO[i].setPatAmountCollected(amount);
						regChgDtlVO[i].setSystemIPAddress(_patientVO.getSystemIPAddress());
						
						////registration module billing
						regChgDtlDAO.create(regChgDtlVO[i], _userVO);
						directChargeVo[i] = new DirectChageDetailVO();
						HelperMethods.populate(directChargeVo[i], regChgDtlVO[i]);
						directChargeDao.create(directChargeVo[i], _userVO);
						///Billing module integration
						//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], _userVO);
						// createSlip(_patientVO.getSystemIPAddress(),printData);
					}
					
				/////Query to fetch Unit working days for printing////
					
				//	_arrEpisodeVO[i].setUnitWorkingDays(essentialDAO.getUnitWorkingDays(_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
				
				/////////////////////////////////////////////////////
				if (Config.CLIENT.equals(Config.CLIENT_GNCTD)) {
					
					*//****** query to fetch disclaimer for printing ************//*
						_arrEpisodeVO[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL, _arrEpisodeVO[i].getDepartmentUnitCode(), _userVO));
						
					*//**************************************************//*	
				}	
				}
			}
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch(HisOPDPatientEligibilityException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisOPDPatientEligibilityException(e.getMessage());
		}
		
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			// System.out.println("_patientVO.getDepartment()::::"+_patientVO.getDepartment());
			System.out.println("_arrEpisodeVO.getDepartment()::::" + _arrEpisodeVO[0].getDepartment());

			tx.close();
		}
		return _arrEpisodeVO;
	}*/
	
	
	/*public EpisodeVO saveEmgRenewalAndStamping(PatientVO _patientvo,EpisodeVO[] _episodevo,String _sysdate, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] arrayEpisodeForStamping = null;
		
		try
		{
			tx.begin();

			_episodevo = saveEmgRenewalDetailAndUpdateEpisodeVo(_episodevo,_sysdate,  _userVO, tx);

				
			// /////////////////////////////////////////

			PatientBOSupport.emergencyOldDeptVisitStamp( _patientvo,_episodevo[0] ,_userVO, tx);
			
			////inserting into renewal detail and reg charge dtl
			saveEmgRenewalAndRegChagrges(_episodevo,_userVO,tx);
		}
		catch (HisRenewalRequiredException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisRenewalRequiredException();
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

		return _episodevo[0];

	}*/

	////used in emergency old patient renewal
	/*public EpisodeVO[] saveEmgRenewalDetailAndUpdateEpisodeVo(EpisodeVO[] selectedEpisodeVO,  String sysDate,UserVO userVO, TransactionContext _tx)
	{

		RenewalDetailVO renewDetailVO = new RenewalDetailVO();
		RegChargeDtlVO regChargeDtlVO = new RegChargeDtlVO();
		DirectChageDetailVO directChargeVO=new DirectChageDetailVO();

		try
		{
			EpisodeDAO episodeDAO = new EpisodeDAO(_tx);
			EssentialDAO essentialDAO = new EssentialDAO(_tx);
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(_tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(_tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(_tx);
			GlobalEssentialDAO globalEssentialDAO = new GlobalEssentialDAO(_tx);
			String selectedcode = "";
			String newExpiryDate = "";
			for (int i = 0; i < selectedEpisodeVO.length; i++)
			{
				if (Config.RENEWAL_TYPE.equals("3"))
				{
					Date dt = new Date();
					dt = globalEssentialDAO.getSystemDate(dt);
					int month = dt.getMonth() + 1;
					if (month <= 6) newExpiryDate = Config.HOSPITAL_RENEWAL_EXPIRY_MONTH_FIRST;
					if (month > 6 && month <= 12) newExpiryDate = Config.HOSPITAL_RENEWAL_EXPIRY_MONTH_SECOND;

				}
				if (Config.RENEWAL_TYPE.equals("4"))
				{
					newExpiryDate = WebUTIL.addDate(sysDate, Integer.parseInt(Config.HOSPITAL_RENEWAL_EXPIRY_DAY));
					newExpiryDate = newExpiryDate.substring(0, 5);
					newExpiryDate = newExpiryDate.replace("/", "-");
				}
				if (Config.RENEWAL_TYPE.equals("5"))
				{
					String[] renewalDetails = essentialDAO.getExpiryDtlForRenewal(selectedEpisodeVO[i].getDepartmentUnitCode());
					String days = renewalDetails[0];
					String month = renewalDetails[1];
					String isExpiry = renewalDetails[2];
					if (isExpiry.equals("0")) throw new HisRenewalRequiredException();
					if (isExpiry.equals("1")) newExpiryDate = month;
					if (isExpiry.equals("2"))
					{
						newExpiryDate = WebUTIL.addDate(sysDate, Integer.parseInt(days));
						newExpiryDate = newExpiryDate.substring(0, 5);
						newExpiryDate = newExpiryDate.replace("/", "-");
					}

				}
				// //update episode detail table////
				episodeDAO.saveDeptWiseRenewalDtl(selectedEpisodeVO[i].getPatCrNo(), selectedEpisodeVO[i].getEpisodeCode(), selectedEpisodeVO[i]
						.getEpisodeVisitNo(), newExpiryDate, userVO);

				// /Updating episodevo's expiry date for stamping

				for (int j = 0; j < selectedEpisodeVO.length; j++)
				{
					
						String expiryDateForEpisodeVO = essentialDAO.calculateRenewalDateForExpiry(newExpiryDate);
						String oldExpiryDate=selectedEpisodeVO[j].getExpiryDate();
						selectedEpisodeVO[j].setExpiryDate(expiryDateForEpisodeVO);
						selectedEpisodeVO[j].setOldExpiryDate(oldExpiryDate);
					
				}


			}

		}
		catch (HisRenewalRequiredException e)
		{
			System.out.println(e.getMessage());
			throw new HisRenewalRequiredException();
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisException(e.getMessage());
		}
		finally
		{

		}

		return selectedEpisodeVO;

	}*/
	
	
	// This method renew the registration and update the expiry date of episode vo for visit stamping

	/*public EpisodeVO[] saveEmgRenewalAndRegChagrges( EpisodeVO[] _arrEpisodeVO, UserVO userVO, TransactionContext _tx)
	{

		RenewalDetailVO renewDetailVO = new RenewalDetailVO();
		RegChargeDtlVO regChargeDtlVO = new RegChargeDtlVO();
		DirectChageDetailVO directChargeVO=new DirectChageDetailVO();

		try
		{
			
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(_tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(_tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(_tx);
			String selectedcode = "";
			String newExpiryDate = "";
			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				// ///update HRGT_RENEW_DTL////
				renewDetailVO.setPatCrNo(_arrEpisodeVO[i].getPatCrNo());
				renewDetailVO.setSeatId(userVO.getSeatId());
				renewDetailVO.setIsValid(Config.IS_VALID_ACTIVE);
				renewDetailVO.setSystemIPAddress(userVO.getIpAddress());
				renewDetailVO.setEntryDate(_arrEpisodeVO[i].getEntryDate());
				renewDetailVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
				renewDetailVO.setDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
				renewDetailVO.setHospitalCode(userVO.getHospitalCode());
				renewDetailVO.setOldExpiryDate(_arrEpisodeVO[i].getOldExpiryDate());
				renewDetailVO.setNewExpiryDate(_arrEpisodeVO[i].getExpiryDate());
				renewDetailVO.setRenewalType(Config.RENEWAL_TYPE);

				//renewDetailDAO.create(renewDetailVO, userVO);
				renewDetailDAO.createForOldPatientRenewal(renewDetailVO, userVO);
				// ///insert hrgt_reg_charge_dtl//////
				
				regChargeDtlVO.setPatAmountCollected(_arrEpisodeVO[i].getPatAmountCollected());
				regChargeDtlVO.setPatCrNo(_arrEpisodeVO[i].getPatCrNo());
				regChargeDtlVO.setPatPrimaryCatCode(_arrEpisodeVO[i].getPatPrimaryCatCode());
				regChargeDtlVO.setPatSecondaryCatCode(_arrEpisodeVO[i].getPatSecondaryCatCode());
				regChargeDtlVO.setSeatId(userVO.getSeatId());
				regChargeDtlVO.setSystemIPAddress(userVO.getIpAddress());
				regChargeDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
				regChargeDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				regChargeDtlVO.setTariffId(RegistrationConfig.EMERGENCY_RENEWAL_TARIFF_ID);
				regChargeDtlVO.setModuleId(Config.MODULE_ID_REGISTRATION);
				regChargeDtlVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
				regChargeDtlVO.setDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
				regChargeDtlVO.setEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
				regChargeDtlVO.setAdmissionNo(_arrEpisodeVO[i].getAdmissionNo());
				//Incresing the visit number as charges are for next visit
				int visitNumber=Integer.parseInt(selectedEpisodeVO[i].getEpisodeVisitNo());
				visitNumber=visitNumber+1;
				regChargeDtlVO.setEpisodeVisitNo(_arrEpisodeVO[i].getEpisodeVisitNo());
				System.out.println("regChargeDtlVO.getEpisodeVisitNo()="+regChargeDtlVO.getEpisodeVisitNo());
				if (regChargeDtlVO.getPatAmountCollected() != null && !(regChargeDtlVO.getPatAmountCollected().equals("0") && !regChargeDtlVO.getPatAmountCollected().equals("") )) 
				{
					///registration module billing
					regChargeDtlDAO.createForRenewal(regChargeDtlVO, userVO);
					HelperMethods.populate(directChargeVO, regChargeDtlVO);
					directChargeDao.create(directChargeVO, userVO);
					///billing module integraton
				//	regChargeDtlDAO.createBillinProcedure(regChargeDtlVO, userVO);
				}
				if (!regChargeDtlVO.getPatAmountCollected().equals("0"))
				{
					regChargeDtlDAO.create(regChargeDtlVO, userVO);
				}
			}
			

		}
		catch (HisRenewalRequiredException e)
		{
			System.out.println(e.getMessage());
			throw new HisRenewalRequiredException();
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisException(e.getMessage());
		}
		finally
		{

		}

		return _arrEpisodeVO;

	}
*/
	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and episode not closed.
	 * Provides last visit details of all episodes which are open presently.
	 * 
	 * @param _patientVO Provides CR No of the patient for which episode details are to be searched.
	 * @param _userVO Provides User details.
	 * @param visitType Specifies type of visit, namely, IPD, OPD, or EMG.
	 * @return Array of EpisodeVO with values stored in DB.
	 */
	/*public EpisodeVO[] searchOldPatientEpisodesByCrNo(String crNo, UserVO _userVO, String visitType,String isRenewal, String patCatagoryCode, String tariffId, String serviceId)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO =
		{};
		
		EpisodeVO[] arrVO =
		{};
		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_arrEpisodeVO = episodeDao.retrieveOldPatientEpisodes(crNo, _userVO,isRenewal,patCatagoryCode,tariffId,serviceId);
			EpisodeVO[] _tmpArrEpisodeVO = new EpisodeVO[_arrEpisodeVO.length];
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]
			{};

			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				_arrEpisodeVO[i].setRenewalType(Config.RENEWAL_TYPE);
				
				if(Config.RENEWAL_TYPE.equalsIgnoreCase("2"))
				{
					_arrEpisodeVO[i].setRenewalRequired(isRenewal);
				}
				//////31-dec-2008
				////Checking for Episode_VISIT_TYPE 
				////Earlier visit type was passed as argument "visitType"
				///Now Visit Type is RegistrationConfig.EPISODE_VISIT_TYPE_OPD 
				//and RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL
				if (_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD) || 
						_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
				{
					_tmpArrEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++)
					{
						_tmpArrEpisodeVO[k] = arrVO[k];
					}
					_tmpArrEpisodeVO[len - 1] = _arrEpisodeVO[i];
					arrVO = _tmpArrEpisodeVO;

					for (int k = 0; k < arrVO.length; k++)
					{
						System.out.println("i= " + i + " k= " + k + "  arrVO...1" + arrVO[k].getEpisodeCode());
					}

					//arrVO[len-1]=_arrEpisodeVO[i];
					_tmpArrEpisodeVO[j] = _arrEpisodeVO[i];
					j++;
					//_tmpArrEpisodeVO=arrVO;
					len++;
				}
			}
			
			int m, n;
			for (m = 0, n = 0; m < _tmpArrEpisodeVO.length; m++, n++)
			{
				_arrEpisodeVO[n] = _tmpArrEpisodeVO[m];
			
			}
			if (arrVO.length == 0)
			{
				throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
			}
			//}
		}//end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return arrVO;
	}*/
	
	/**
	 * Modifies patient+address details. Also more addresses can be added. Updates the record in Patient & Address dtl
	 * table.
	 * 
	 * @param _arrAddressVO[] Provides addresses to be added or modified.
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return Number of records updated. 
	 * 
	 * Cretaed By Pragya at 08-Aug-2011
	 */
/*	public int patientDetailModification(PatientVO _patientVO, PatientVO _patientVOOldData, String addModify, AddressVO _arrAddressVO, 
			VerificationDocumentVO _verDoc, UserVO _userVO)
	{
		int x = 0;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO daoObj = null;
		try
		{
			daoObj = new HisDAO("Registration","PatientBO");
			
			tx.begin();
			
			PatientAuditDAO patAuditDao=new PatientAuditDAO(tx);
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			VerificationDocumentUsedDAO verificationDocUsedDao = new VerificationDocumentUsedDAO(tx);

			PatientImageDtlVO patientImageDtlVO = new PatientImageDtlVO();
			
			 * if(_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
			 * _patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
			 
			
			if(_patientVO.getPatDocType().indexOf("|")>0)
			{
				_patientVO.setPatDocType(_patientVO.getPatDocType().split("\\|")[0]);
			}
			synchronized (daoObj)
	        {
				// Verifcation Doc Sl No.
				String verficationSNo = verificationDocUsedDao.selectNextSerialNoForPUK(_verDoc, _userVO);

				if (_patientVO.getPatAddress().getIsAddressDelhi().equals(RegistrationConfig.IS_ADDRESS_DELHI_FALSE)) 
					_patientVO.getPatAddress().setPatAddCityLocCode("");
				_patientVO.setVerificationDocumentId(verficationSNo);
				
				// Update Patient Detail
				if(_patientVO.getIsActualDob().equals(_patientVOOldData.getIsActualDob())){
					if(_patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& _patientVO.getPatAge().equals(_patientVOOldData.getPatAge())
							&& _patientVO.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) )
					{
						x = patientDao.updateWithoutAge(daoObj, _patientVO, _userVO);
					}
					else
					{
						x = patientDao.update(daoObj, _patientVO, _userVO);
					}
				}
				else
				{
					if(_patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE) 
							&& _patientVO.getPatAge().equals(_patientVOOldData.getPatAge())
							&& _patientVO.getPatAgeUnit().equals(_patientVOOldData.getPatAgeUnit()) ){
						x = patientDao.updateWithoutAge(daoObj, _patientVO, _userVO);
					}
					else  x = patientDao.update(daoObj, _patientVO, _userVO);
				}
			
				// Update Address Detail
				if (_arrAddressVO!=null) //x >= 1)
				{
					if (_arrAddressVO.getPatAddTypeCode().equals(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT)) 
						_arrAddressVO.setIsCurrentAddress(RegistrationConfig.IS_ADDRESS_CURRENT_TRUE);
					else _arrAddressVO.setIsCurrentAddress(RegistrationConfig.IS_ADDRESS_CURRENT_FALSE);

					if (addModify.equals(RegistrationConfig.IS_ADDRESS_ADDED))
					{
						_arrAddressVO.setVerificationDocumentId(verficationSNo);
						_arrAddressVO.setIsValid(Config.IS_VALID_ACTIVE);
						addDao.createNewAddress(daoObj, _arrAddressVO, _userVO);
						// patientDao.updateAreaCategory(_arrAddressVO.getPatCrNo(),_arrAddressVO.getPatIsUrban(), _userVO);
					}
					if (addModify.equals(RegistrationConfig.IS_ADDRESS_MODIFIED))
					{
						_arrAddressVO.setVerificationDocumentId(verficationSNo);
						_arrAddressVO.setIsValid(Config.IS_VALID_DELETED);
						addDao.updatePreviousRow(daoObj,_arrAddressVO, _userVO);
						
						_arrAddressVO.setIsValid(Config.IS_VALID_ACTIVE);
						addDao.insertNewRowAddress(daoObj,_arrAddressVO, _userVO);
						// patientDao.updateAreaCategory(_arrAddressVO.getPatCrNo(),_arrAddressVO.getPatIsUrban(), _userVO);
					}
				}
				
				// Audit Log Of Patient Detail
				patAuditDao.create(daoObj, _patientVOOldData, _userVO);

				// Patient Image File
				if (_patientVOOldData.getImageFile() != null && _patientVO.getImageFile() != null)
				{
					String sno = patientImageDtlDAO.selctMaxSerialNo(_patientVO.getPatCrNo(), _userVO);
					HelperMethods.populate(patientImageDtlVO, _patientVO);
					patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#" + sno);
					HelperMethods.storeImageInCorrectFileSystem(_patientVO.getImageFile(), _patientVO.getImageFileName(), patientImageDtlVO.getFileNo(),
							Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
					boolean flag =HisFileControlUtil.isWindowsOS();
					if(flag)
					{
						patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
					}
					else
					{
						patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
					}
					// patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					// update the is default flag of old image
					patientImageDtlVO.setSerialNo(_patientVOOldData.getImageFileName().split("#")[1]);
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_FALSE);
					patientImageDtlDAO.modifyPatientImage(daoObj, patientImageDtlVO, _userVO);
					// insert new default image
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
					patientImageDtlDAO.create(daoObj, patientImageDtlVO, _userVO);
				}
				else if (_patientVO.getImageFile() != null)
				{
					String sno = patientImageDtlDAO.selctMaxSerialNo(_patientVO.getPatCrNo(), _userVO);
					HelperMethods.populate(patientImageDtlVO, _patientVO);
					patientImageDtlVO.setFileNo(patientImageDtlVO.getPatCrNo() + "#" + sno);
					HelperMethods.storeImageInCorrectFileSystem(_patientVO.getImageFile(), _patientVO.getImageFileName(), patientImageDtlVO.getFileNo(),
							Config.PATIENT_IMAGE_FILE_STORAGE_PATH, Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX);
					boolean flag =HisFileControlUtil.isWindowsOS();
					  if(flag)
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
					  }
					  else
					  {
						  patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
					  }
					//patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH);
					patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
					patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
					patientImageDtlDAO.create(daoObj, patientImageDtlVO, _userVO);
				}
				
				// Update Verification Document
				_verDoc.setIsValid(Config.IS_VALID_ACTIVE);
				verificationDocUsedDao.create(daoObj, _verDoc, _userVO);
				

				PatientAdhaarDtlDAO patAdhaarDtlDao=new PatientAdhaarDtlDAO(tx);
				patAdhaarDtlDao.update(_patientVO, _userVO);
			
				daoObj.fire();
			}
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return x;
	}

	public Map savePatientVisit(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO,UserVO _userVO, EpisodeRefDtlVO _episodeRefDtlVO,EpisodeVO[] _episodeNewDeptVO)
	{
		
		System.out.println("----------New Dept VO------------"+_episodeNewDeptVO+"-----------------------");
		System.out.println("----------Old Dept VO------------"+_arrEpisodeVO+"-----------------------");

		Map episodeMap=new HashMap();
		if(_episodeNewDeptVO!=null)//Modified to check both old and new dept visit by singaravelan on 26-09-13
		{
			if (_episodeNewDeptVO!=null&&_arrEpisodeVO!=null)
			{
				System.out.println("----------In old and New Dept Vist----------------------");
				_episodeNewDeptVO=newDepartmentVisitStamp(_episodeNewDeptVO, _patientVO, _episodeRefDtlVO, _userVO, null);
				_arrEpisodeVO=oldDeptVisitStamp(_arrEpisodeVO, _patientVO, _userVO, _episodeRefDtlVO);
				episodeMap.put("NEWEPISODELIST", _episodeNewDeptVO);
				episodeMap.put("OLDEPISODELIST", _arrEpisodeVO);
			}
			else
			{
				System.out.println("----------In New Dept Vist----------------------");
				_episodeNewDeptVO=newDepartmentVisitStamp(_episodeNewDeptVO, _patientVO, _episodeRefDtlVO, _userVO, null);		
				episodeMap.put("NEWEPISODELIST", _episodeNewDeptVO);
			}
		}
		else
		{
			System.out.println("----------In old Dept Vist----------------------");
			_arrEpisodeVO=oldDeptVisitStamp(_arrEpisodeVO, _patientVO, _userVO, _episodeRefDtlVO);		
			episodeMap.put("OLDEPISODELIST", _arrEpisodeVO);
		}
		
		return episodeMap;
	}*/
/*	public EpisodeVO[] searchOldPatientEmgEpisodesByCrNo(String crNo, UserVO _userVO, String visitType, String renewalTariffId, String patPrimaryCatCode, String registrationServiceId,String isRenewal)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO ={};

		EpisodeVO[] arrVO =	{};
		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_arrEpisodeVO = episodeDao.retrieveOldEmgPatientEpisodes(crNo, _userVO,renewalTariffId,patPrimaryCatCode,registrationServiceId,isRenewal);
			EpisodeVO[] _tmpArrEpisodeVO = new EpisodeVO[_arrEpisodeVO.length];
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]	{};

			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				_arrEpisodeVO[i].setRenewalType(Config.RENEWAL_TYPE);
				//////31-dec-2008
				////Checking for Episode_VISIT_TYPE 
				////Earlier visit type was passed as argument "visitType"
				///Now Visit Type is RegistrationConfig.EPISODE_VISIT_TYPE_OPD 
				//and RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL
				if (_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG) || 
						_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_EMG_MLC))
				{
					_tmpArrEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++)
					{
						_tmpArrEpisodeVO[k] = arrVO[k];
					//	System.out.println("arrVO" + arrVO[k].getEpisodeCode());
					}
					_tmpArrEpisodeVO[len - 1] = _arrEpisodeVO[i];
					arrVO = _tmpArrEpisodeVO;

					for (int k = 0; k < arrVO.length; k++)
					{
						System.out.println("i= " + i + " k= " + k + "  arrVO...1" + arrVO[k].getEpisodeCode());
					}

					// arrVO[len-1]=_arrEpisodeVO[i];
					_tmpArrEpisodeVO[j] = _arrEpisodeVO[i];
					j++;
					// _tmpArrEpisodeVO=arrVO;
					len++;
				}
			}
			int m, n;
			for (m = 0, n = 0; m < _tmpArrEpisodeVO.length; m++, n++)
			{
				_arrEpisodeVO[n] = _tmpArrEpisodeVO[m];
			}
			if (arrVO.length == 0)
			{
				throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
			}
			// }
		}// end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return arrVO;
	}
	
	public EpisodeVO[] saveEmgOldPatientVisit(PatientVO _patientVO,
			EpisodeVO[] _episodeVO, UserVO _userVO,
			EpisodeRefDtlVO episodeRefDtlVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		 EpisodeVO _arrEpisodeVO=new EpisodeVO();
		HisDAO hisDAOObj=null;
		try
		{
			tx.begin();
			hisDAOObj = new HisDAO(null, null);
			
			for(EpisodeVO episodeVO:_episodeVO)
			{
				if(episodeVO.getRenewalRequired().equals("1")==true)
				episodeVO = saveDeptWiseRenewalDetailAndUpdateEpisodeVo(hisDAOObj,episodeVO, _userVO, tx);
				
			}
			
			_episodeVO=PatientBOSupport.saveEmgOldPatientVisit(hisDAOObj,_patientVO,_episodeVO,_userVO,episodeRefDtlVO,tx);
			
			for(EpisodeVO episodeVO:_episodeVO)
			{
				if(episodeVO.getRenewalRequired().equals("1")==true)
				{
					episodeVO.setIsEpisodeRenewed("true");
					episodeVO=saveEmgRenewalAndRegChagrges(hisDAOObj,episodeVO,_userVO,tx);
				}
				
				
			}
			
			
			
			
			for(int i=0;i<_episodeVO.length;i++)
			{
			*//*** Query to fetch Disclaimer*********************//*
			String patRegCatCode=_patientVO.getPatRegCatCode();
			String usablityFlag="";
			if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
				usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
			if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL)){
				usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
			}	
			_episodeVO[i].setDisclaimer(getDisclamer(usablityFlag,_episodeVO[i].getDepartmentUnitCode(),_userVO));
			}
			synchronized (hisDAOObj) {
				 hisDAOObj.fire();
			}
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.rollback();
			tx.close();
			if (hisDAOObj != null) {
				//hisDAOObj.free();
				hisDAOObj = null;
			}
		}
		return _episodeVO;
		
		
	}*/
	
	/*
	public EpisodeVO saveEmgRenewalAndRegChagrges(HisDAO hisDAO, EpisodeVO _arrEpisodeVO, UserVO userVO, TransactionContext _tx)
	{

		RenewalDetailVO renewDetailVO = new RenewalDetailVO();
		RegChargeDtlVO regChargeDtlVO = new RegChargeDtlVO();
		DirectChageDetailVO directChargeVO=new DirectChageDetailVO();

		try
		{
			
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(_tx);
			RegChargeDtlDAO regChargeDtlDAO = new RegChargeDtlDAO(_tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(_tx);
			String selectedcode = "";
			String newExpiryDate = "";
			
				// ///update HRGT_RENEW_DTL////
				renewDetailVO.setPatCrNo(_arrEpisodeVO.getPatCrNo());
				renewDetailVO.setSeatId(userVO.getSeatId());
				renewDetailVO.setIsValid(Config.IS_VALID_ACTIVE);
				renewDetailVO.setSystemIPAddress(userVO.getIpAddress());
				renewDetailVO.setEntryDate(_arrEpisodeVO.getEntryDate());
				renewDetailVO.setDepartmentCode(_arrEpisodeVO.getDepartmentCode());
				renewDetailVO.setDepartmentUnitCode(_arrEpisodeVO.getDepartmentUnitCode());
				renewDetailVO.setHospitalCode(userVO.getHospitalCode());
				renewDetailVO.setOldExpiryDate(_arrEpisodeVO.getOldExpiryDate());
				renewDetailVO.setNewExpiryDate(_arrEpisodeVO.getExpiryDate());
				renewDetailVO.setRenewalType(Config.RENEWAL_TYPE);

				//renewDetailDAO.create(renewDetailVO, userVO);
				renewDetailDAO.createForOldPatientRenewal(renewDetailVO, userVO);
				// ///insert hrgt_reg_charge_dtl//////
				
				regChargeDtlVO.setPatAmountCollected(_arrEpisodeVO.getPatAmountCollected());
				regChargeDtlVO.setPatCrNo(_arrEpisodeVO.getPatCrNo());
				regChargeDtlVO.setPatPrimaryCatCode(_arrEpisodeVO.getPatPrimaryCatCode());
				regChargeDtlVO.setPatSecondaryCatCode(_arrEpisodeVO.getPatSecondaryCatCode());
				regChargeDtlVO.setSeatId(userVO.getSeatId());
				regChargeDtlVO.setSystemIPAddress(userVO.getIpAddress());
				regChargeDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
				regChargeDtlVO.setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
				regChargeDtlVO.setTariffId(RegistrationConfig.EMERGENCY_RENEWAL_TARIFF_ID);
				regChargeDtlVO.setModuleId(Config.MODULE_ID_REGISTRATION);
				regChargeDtlVO.setDepartmentCode(_arrEpisodeVO.getDepartmentCode());
				regChargeDtlVO.setDepartmentUnitCode(_arrEpisodeVO.getDepartmentUnitCode());
				regChargeDtlVO.setEpisodeCode(_arrEpisodeVO.getEpisodeCode());
				regChargeDtlVO.setAdmissionNo(_arrEpisodeVO.getAdmissionNo());
				//Incresing the visit number as charges are for next visit
				int visitNumber=Integer.parseInt(selectedEpisodeVO[i].getEpisodeVisitNo());
				visitNumber=visitNumber+1;
				regChargeDtlVO.setEpisodeVisitNo(_arrEpisodeVO.getEpisodeVisitNo());
				System.out.println("regChargeDtlVO.getEpisodeVisitNo()="+regChargeDtlVO.getEpisodeVisitNo());
				if (regChargeDtlVO.getPatAmountCollected() != null && !(regChargeDtlVO.getPatAmountCollected().equals("0") && !regChargeDtlVO.getPatAmountCollected().equals("") )) 
				{
					///registration module billing
					regChargeDtlDAO.createForRenewal(hisDAO,regChargeDtlVO, userVO);
					HelperMethods.populate(directChargeVO, regChargeDtlVO);
					directChargeDao.create(hisDAO,directChargeVO, userVO);
					///billing module integraton
				//	regChargeDtlDAO.createBillinProcedure(regChargeDtlVO, userVO);
				}
				if (!regChargeDtlVO.getPatAmountCollected().equals("0"))
				{
					regChargeDtlDAO.create(regChargeDtlVO, userVO);
				}
			
			

		}
		catch (HisRenewalRequiredException e)
		{
			System.out.println(e.getMessage());
			throw new HisRenewalRequiredException();
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisException(e.getMessage());
		}
		finally
		{

		}

		return _arrEpisodeVO;

	}
	
	public Map saveEmergenyPatientVisitStamping(PatientVO _patientVO,
			EpisodeVO[] oldVisitEpisodeVO, UserVO userVO,
			EpisodeRefDtlVO episodeRefDtlVO, EpisodeVO[] newVisitEpisodeVO,RegChargeDtlVO regChargeDtlVO)
	{
		Map episodeMap=new HashMap();
		if(newVisitEpisodeVO!=null && newVisitEpisodeVO.length>0)
		{
			newVisitEpisodeVO=saveNewSpVisiStamp(newVisitEpisodeVO, userVO, _patientVO, regChargeDtlVO, episodeRefDtlVO, null);
			episodeMap.put("NEWEPISODEOBJECT", newVisitEpisodeVO);
		}
		if(oldVisitEpisodeVO!=null && oldVisitEpisodeVO.length>0)
		{
			oldVisitEpisodeVO=saveEmgOldPatientVisit(_patientVO, oldVisitEpisodeVO, userVO, episodeRefDtlVO);
			episodeMap.put("OLDEPISODEOBJECT", oldVisitEpisodeVO);
		}
		
		
		
		return episodeMap;
	}
	
	public PatientAdhaarDtlVO checkDupAdhaarPatient(PatientVO _patientVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientAdhaarDtlVO PatientAdhaarDtlVO_p = new PatientAdhaarDtlVO();		
		try
		{
			HisDAO hisDAO = new HisDAO("reg","reg,patadhardao");
			tx.begin();

			PatientAdhaarDtlDAO patAdhaarDtlDao=new PatientAdhaarDtlDAO(tx);
			PatientAdhaarDtlVO_p=patAdhaarDtlDao.retrieveByNationalID(_patientVO, _userVO,hisDAO);

			
			}
		
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			//e.printStackTrace();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			//e.printStackTrace();
			
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			//e.printStackTrace();
			
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			//e.printStackTrace();
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			//e.printStackTrace();
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{

			tx.close();
		}
		//}
		return PatientAdhaarDtlVO_p; // <<<
	}*/// each VisitStampVO keeps PatientVO

/*	public EpisodeVO[] newSplUnitVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAOObj=null;
		try
		{
			tx.begin();
	
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			PatientDAO patientDao = new PatientDAO(tx);
			
			RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDao=new DirectChargeDetailDAO(tx);
			RegChargeDtlVO[] regChgDtlVO = new RegChargeDtlVO[_arrEpisodeVO.length];
			DirectChageDetailVO[] directChargeVo = new DirectChageDetailVO[_arrEpisodeVO.length];
			RenewalDetailVO[] renewDetailVO = new RenewalDetailVO[_arrEpisodeVO.length];
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			RenewalDetailDAO renewDetailDAO = new RenewalDetailDAO(tx);
			String amount = _arrEpisodeVO[0].getPatAmountCollected();
			
			hisDAOObj = new HisDAO("Registration","PatientBO");
			
			
				// EpisodeRefDtlVO episodeRefDtlVO=new EpisodeRefDtlVO();

				////updating patient detail if department wise mandatory fields are captured
				disable this for arogya online 
				 * if(_oldPatientVO!=null)
				{
				patDao.updateWithoutAge(_patientVO, _userVO);//not create procedure for arogya online
				patAuditDAO.create(_oldPatientVO, _userVO);//not create procedure for arogya online
				}disable this for arogya online
				
				for (int i = 0; i < _arrEpisodeVO.length; i++)
				{
					
					
					DailyPatientVO dailyPatVO = new DailyPatientVO();
					// Create DailyPatientVO from patientVO: Populate it
					HelperMethods.populate(dailyPatVO, _patientVO);
					// System.out.println("after population dailypatvo"+dailyPatVO.getPatCrNo());
					// System.out.println("before setDailyPatientDetails");
					// set Other required detials in episodeVO
					// explicityly set setIsValid(RegistrationConfig.IS_VALID_ACTIVE)
					dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
					
					// populate the episodeVO with the general details
					PatientBOHelper.setNewPatRegEpisodeVO(_arrEpisodeVO[i]);
					String referMlcNo="";
					referMlcNo=episodeRefVO.getMlcNo();
					if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD)) 
						{
						if(_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
						{
							if(episodeRefVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC))
							{
								_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
							}
							else
							{
								_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
							}
						}else
						{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
						//}
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
						}
					else
						{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
						}
					_arrEpisodeVO[i].setPatCrNo(dailyPatVO.getPatCrNo());
					_arrEpisodeVO[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
					_arrEpisodeVO[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());
					// _arrEpisodeVO[i].setDepartmentCode("101");
					
					_arrEpisodeVO[i].setIsValid(Config.IS_VALID_ACTIVE);
					// populate this dailyPatient VO with _arrEpisodeVO[i]
					
					HelperMethods.populate(dailyPatVO, _arrEpisodeVO[i]);
					_patientVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
					HelperMethods.populate(dailyPatVO, _patientVO);
					dailyPatVO.setIsReferred(_patientVO.getIsReferred());
					// PatientBOHelper.setDailyPatientDetails(dailyPatVO);
					
					// Create a new entry in daily patient detail
					// TokenDetails --> Unit, Room and Queue No are assigned to dailyPatVO
					
					//dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_TRUE);
					///In case of new dept visit is old is false
					dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
					
					 disable room usability  for arogya
					 *  if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_EXTERNAL_REFERRAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
						}
						if(_patientVO.getIsPatReferByList()!=null && _patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_INTERNAL_REFERRAL);
						}
					}
					else
						dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);
					
					
					//disable room fileno  for arogya dailyPatVO.setIsNewFileRequired(Config.FILE_NO_GENERATION_FLAG);
					dailyPatVO.setEpisodeVisitNo("1");
					///old query 
					//dailyPatVO = dailyPatDao.create(dailyPatVO, _userVO);
					////new query for mlC refer logic
					dailyPatVO = dailyPatDao.createNewSplUnit(hisDAOObj, dailyPatVO, _userVO,referMlcNo);
					HelperMethods.populate(_arrEpisodeVO[i], dailyPatVO);
					if(dailyPatVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC))
						_arrEpisodeVO[i].setMlcNo(referMlcNo);
					//Create new entry in Episode detail
					

					// Create new entry in Episode detail
				//	_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);


					
					 * if(_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE)){
					 * System.out.println("hi!!!!");
					 * _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
					 * System.out.println("_arrEpisodeVO[i].getEpisodeReferAccept()"+_arrEpisodeVO[i].getEpisodeReferAccept());
					 * episodeRefVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL); } else
					 * _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					 
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						}
						else if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_TRUE);
							_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_INTERNAL);
						}

					}
					else
					{
						_arrEpisodeVO[i].setIsReferred(RegistrationConfig.IS_REFERRED_FALSE);
						_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					}

					//_arrEpisodeVO[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
					_arrEpisodeVO[i].setIsCardPrint(dailyPatVO.getCardPrintSetting().substring(0,1));
					
					
					////setting visit number//
					
					_arrEpisodeVO[i].setEpisodeVisitNo("1");
					_arrEpisodeVO[i].setEntryDate(_patientVO.getSystemDate());
					//saving episode dtl
					//hide this for arogya online
					//disable  for arogya PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(_arrEpisodeVO[i], _userVO, tx);
					
					//changed on 14-feb-2012 by prakhar misra
					//to calculate expiry date out of procedure so that it cold be used for prinintg
					//String expiryDate=episodeDao.getExpiryDate(_arrEpisodeVO[i],_userVO);
					
					//_arrEpisodeVO[i].setExpiryDate(expiryDate);
					
					//To Update expiry date on patient dtl added by Singaravelan on 26-09-13 
					System.out.println("----Pat Ren Req--------"+_patientVO.getRegRenewalRequired()+"------------");
					if(_patientVO.getRegRenewalRequired().equalsIgnoreCase("1"))
					{
						_patientVO.setExpiryDate(patientDao.getExpiryDate(_userVO));
						System.out.println("----Date--------"+patientDao.getExpiryDate(_userVO)+"------------");
						patientDao.update(hisDAOObj,_patientVO,_userVO);
					}
					 
					
					 _arrEpisodeVO[i]=episodeDao.createNewRegistration(hisDAOObj,_arrEpisodeVO[i], _userVO);
					// episodeDao.create(_arrEpisodeVO[i], _userVO);

					//_arrEpisodeVO[i].setDepartmentCode(_arrEpisodeVO[i].getDepartmentUnitCode().substring(0, 3));
					String deptCode = _arrEpisodeVO[i].getDepartmentCode();
					String roomCode = _arrEpisodeVO[i].getRoomCode();
					String deptUnitCode = _arrEpisodeVO[i].getDepartmentUnitCode();
					List al = new ArrayList();
					al = essentialDAO.getNameValues(deptCode, roomCode, deptUnitCode, _userVO);
					_arrEpisodeVO[i].setDepartment(al.get(0).toString());
					_arrEpisodeVO[i].setRoom(al.get(1).toString());
					_arrEpisodeVO[i].setDepartmentUnit(al.get(2).toString());

					// HelperMethods.populate(_arrEpisodeVO[i], al);




					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						// _patientVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
						// System.out.println("_episodeReferVO.getIsRefferInOut(): "+_episodeReferVO.getIsRefferInOut());
						// HelperMethods.populate(_episodeReferVO, _patientVO);
						// HelperMethods.populate(_episodeReferVO, _arrEpisodeVO[i]);
						// ///////episode referDTL////////////////
						
						 * String[] array = episodeDao.getEpisdoeCode(_arrEpisodeVO[i].getPatCrNo(), _arrEpisodeVO[i]
						 * .getDepartmentUnitCode(), _userVO); _arrEpisodeVO[i].setEpisodeVisitNo(array[2]);
						 
						String refEpisode = episodeRefVO.getEpisodeCode();
						String refEpisodeVisit = episodeRefVO.getEpisodeVisitNo();
						String serialNo = episodeRefVO.getSerialNo();
						HelperMethods.populate(episodeRefVO, _arrEpisodeVO[i]);
						episodeRefVO.setSystemIPAddress(_patientVO.getSystemIPAddress());

						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL))
						{
							episodeRefVO.setEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
							episodeRefVO.setEpisodeVisitNo("1");

						}
						if (episodeRefVO.getIsRefferInOut().equals(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL))
						{
							episodeRefVO.setToDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
							episodeRefVO.setToDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
							episodeRefVO.setToEpisodeCode(_arrEpisodeVO[i].getEpisodeCode());
							episodeRefVO.setToEpisodeVisitNo("1");
							episodeRefVO.setEpisodeCode(refEpisode);
							episodeRefVO.setEpisodeVisitNo(refEpisodeVisit);
							episodeRefVO.setExternalHospitalCode("");
							episodeRefVO.setSerialNo(serialNo);
						}

						if (_patientVO.getIsPatReferByList() != null
								&& _patientVO.getIsPatReferByList().equals(RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE))
						{
							episodeRefDtlDAO.updateAcceptanceDate(hisDAOObj, episodeRefVO, _userVO);
						}
						else
						{

							episodeRefDtlDAO.create(hisDAOObj,episodeRefVO, _userVO);
						}
						// ///////episode referDTL////////////////
						// System.out.println("_episodeReferVO.getEpisodeVisitNo():::"+_episodeReferVO.getEpisodeVisitNo());
						// episodeReferDAO.create(_episodeReferVO, _userVO);
					}
					//Modified to add the details on renewal table while new department visit cum renewal by Singaravelan on 08-10-13 
					System.out.println("----Amount-----"+amount+"-----------");
					if (amount != null && !amount.equals("") && !amount.equals("0"))
					{
						regChgDtlVO[i] = new RegChargeDtlVO();
						regChgDtlVO[i].setTariffId(RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
						regChgDtlVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
						regChgDtlVO[i].setModuleId(Config.MODULE_ID_REGISTRATION);
						HelperMethods.populate(regChgDtlVO[i], _arrEpisodeVO[i]);
						regChgDtlVO[i].setPatAmountCollected(amount);
						regChgDtlVO[i].setSystemIPAddress(_patientVO.getSystemIPAddress());
						
						////registration module billing
						regChgDtlDAO.create(hisDAOObj,regChgDtlVO[i], _userVO);
						directChargeVo[i] = new DirectChageDetailVO();
						HelperMethods.populate(directChargeVo[i], regChgDtlVO[i]);
						directChargeDao.create(hisDAOObj,directChargeVo[i], _userVO);
						///Billing module integration
						//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], _userVO);
						// createSlip(_patientVO.getSystemIPAddress(),printData);
						
						if(_arrEpisodeVO[i].getRenewalRequired().equals("1")==true||_patientVO.getRegRenewalRequired().equalsIgnoreCase("1"))
						{
							renewDetailVO[i] = new RenewalDetailVO();
							renewDetailVO[i].setPatCrNo(_arrEpisodeVO[i].getPatCrNo());
							renewDetailVO[i].setSeatId(_userVO.getSeatId());
							renewDetailVO[i].setIsValid(Config.IS_VALID_ACTIVE);
							renewDetailVO[i].setSystemIPAddress(_userVO.getIpAddress());
							renewDetailVO[i].setEntryDate(_arrEpisodeVO[i].getEntryDate());
							renewDetailVO[i].setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
							renewDetailVO[i].setDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
							renewDetailVO[i].setHospitalCode(_userVO.getHospitalCode());
							renewDetailVO[i].setOldExpiryDate(_arrEpisodeVO[i].getOldExpiryDate());
							renewDetailVO[i].setNewExpiryDate(_arrEpisodeVO[i].getExpiryDate());
							renewDetailVO[i].setRenewalType(Config.RENEWAL_TYPE);

							renewDetailDAO.createForOldPatientRenewal(hisDAOObj,renewDetailVO[i], _userVO);
						
						}					
					
					}
				/////Query to fetch Unit working days for printing////
					
				//	_arrEpisodeVO[i].setUnitWorkingDays(essentialDAO.getUnitWorkingDays(_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
				
				/////////////////////////////////////////////////////
			
					
					//****** query to fetch disclaimer for printing ************//*
						_arrEpisodeVO[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL, _arrEpisodeVO[i].getDepartmentUnitCode(), _userVO));
						
					//**************************************************//*	
				
				
			}
			synchronized (hisDAOObj) 
			{
			   hisDAOObj.fire();
			}		
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			System.out.println("Close the transaction...");
			// System.out.println("_patientVO.getDepartment()::::"+_patientVO.getDepartment());
			System.out.println("_arrEpisodeVO.getDepartment()::::" + _arrEpisodeVO[0].getDepartment());
			if (hisDAOObj != null) {
				hisDAOObj.free();
				hisDAOObj = null;
			}
			tx.close();
		}
		return _arrEpisodeVO;
	}
	*//**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and episode not closed.
	 * Provides last visit details of all episodes which are open presently.
	 * 
	 * @param _patientVO Provides CR No of the patient for which episode details are to be searched.
	 * @param _userVO Provides User details.
	 * @param visitType Specifies type of visit, namely, IPD, OPD, or EMG.
	 * @return Array of EpisodeVO with values stored in DB.
	 *//*
	public EpisodeVO[] searchOldPatientEpisodesByCrNo(String crNo, UserVO _userVO, String visitType,String isRenewal, String patCatagoryCode, String tariffId, String serviceId,String episodeType)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO =
		{};
		
		EpisodeVO[] arrVO =
		{};
		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_arrEpisodeVO = episodeDao.retrieveOldPatientEpisodes(crNo, _userVO,isRenewal,patCatagoryCode,tariffId,serviceId,episodeType);
			EpisodeVO[] _tmpArrEpisodeVO = new EpisodeVO[_arrEpisodeVO.length];
			int j = 0;
			int len = 1;
			arrVO = new EpisodeVO[]
			{};

			for (int i = 0; i < _arrEpisodeVO.length; i++)
			{
				_arrEpisodeVO[i].setRenewalType(Config.RENEWAL_TYPE);
				
				if(Config.RENEWAL_TYPE.equalsIgnoreCase("2"))
				{
					_arrEpisodeVO[i].setRenewalRequired(isRenewal);
				}
				//////31-dec-2008
				////Checking for Episode_VISIT_TYPE 
				////Earlier visit type was passed as argument "visitType"
				///Now Visit Type is RegistrationConfig.EPISODE_VISIT_TYPE_OPD 
				//and RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL
				if (_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_OPD) || 
						_arrEpisodeVO[i].getEpisodeVisitType().equals(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL))
				{
					_tmpArrEpisodeVO = new EpisodeVO[len];
					for (int k = 0; k < arrVO.length; k++)
					{
						_tmpArrEpisodeVO[k] = arrVO[k];
					}
					_tmpArrEpisodeVO[len - 1] = _arrEpisodeVO[i];
					arrVO = _tmpArrEpisodeVO;

					for (int k = 0; k < arrVO.length; k++)
					{
						System.out.println("i= " + i + " k= " + k + "  arrVO...1" + arrVO[k].getEpisodeCode());
					}

					//arrVO[len-1]=_arrEpisodeVO[i];
					_tmpArrEpisodeVO[j] = _arrEpisodeVO[i];
					j++;
					//_tmpArrEpisodeVO=arrVO;
					len++;
				}
			}
			
			int m, n;
			for (m = 0, n = 0; m < _tmpArrEpisodeVO.length; m++, n++)
			{
				_arrEpisodeVO[n] = _tmpArrEpisodeVO[m];
			
			}
			if (arrVO.length == 0)
			{
				throw new HisRecordNotFoundException("Patient Never Visited In Current OPD");
			}
			//}
		}//end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisNotAnOPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnOPDcaseException();
		}
		catch (HisNotAnIPDcaseException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisNotAnIPDcaseException();
		}
		catch (HisDeadPatientException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDeadPatientException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return arrVO;
	}

	public Map saveSplPatientVisit(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO,UserVO _userVO, EpisodeRefDtlVO _episodeRefDtlVO,EpisodeVO[] _episodeNewDeptVO)
	{
		
		System.out.println("----------New Dept VO------------"+_episodeNewDeptVO+"-----------------------");
		System.out.println("----------Old Dept VO------------"+_arrEpisodeVO+"-----------------------");

		Map episodeMap=new HashMap();
		if(_episodeNewDeptVO!=null)//Modified to check both old and new dept visit by singaravelan on 26-09-13
		{
			if (_episodeNewDeptVO!=null&&_arrEpisodeVO!=null)
			{
				System.out.println("----------In old and New Dept Vist----------------------");
				_episodeNewDeptVO=newSplUnitVisitStamp(_episodeNewDeptVO, _patientVO, _episodeRefDtlVO, _userVO, null);
				_arrEpisodeVO=oldDeptVisitStamp(_arrEpisodeVO, _patientVO, _userVO, _episodeRefDtlVO);
				episodeMap.put("NEWEPISODELIST", _episodeNewDeptVO);
				episodeMap.put("OLDEPISODELIST", _arrEpisodeVO);
			}
			else
			{
				System.out.println("----------In New Dept Vist----------------------");
				_episodeNewDeptVO=newSplUnitVisitStamp(_episodeNewDeptVO, _patientVO, _episodeRefDtlVO, _userVO, null);		
				episodeMap.put("NEWEPISODELIST", _episodeNewDeptVO);
			}
		}
		else
		{
			System.out.println("----------In old Dept Vist----------------------");
			_arrEpisodeVO=oldDeptVisitStamp(_arrEpisodeVO, _patientVO, _userVO, _episodeRefDtlVO);		
			episodeMap.put("OLDEPISODELIST", _arrEpisodeVO);
		}
		
		return episodeMap;
	}*/	

/*
	public EpisodeVO[] newPatSplRegistration(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO,  HttpServletRequest _request) throws HisDuplicateRecordException
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO daoObj = null;
		boolean isDuplicateReg=false;
		String[] crNoArr = null;
		//synchronized (PatientDAO.class)
		//{
		try
		{
			daoObj = new HisDAO("Registration","PatientBO");
			
			tx.begin();
			Map essentialMap = new HashMap();

			PatientDAO patDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			DailyPatientDAO dailyPatDao = new DailyPatientDAO(tx);
			BPLDetailsDAO bplDAO = new BPLDetailsDAO();
			RegChargeDtlDAO regChgDtlDAO = new RegChargeDtlDAO(tx);
			DirectChargeDetailDAO directChargeDtlDao=new DirectChargeDetailDAO(tx);
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			List allPatientVoList=new ArrayList();

			
			int arrEpisodeVOLength=_arrEpisodeVO.length;
			
			RegChargeDtlVO[] regChgDtlVO = new RegChargeDtlVO[arrEpisodeVOLength];
			DirectChageDetailVO[] directChargeVO=new DirectChageDetailVO[arrEpisodeVOLength];
			DailyPatientVO dailyPatVO = new DailyPatientVO();
			
			for (int i = 0; i < arrEpisodeVOLength; i++)
			{
				EpisodeReferVO _episodeReferVO = new EpisodeReferVO();
				EpisodeRefDtlVO episodeRefDtlVO = new EpisodeRefDtlVO();
				
				_patientVO.setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_FALSE);
				_patientVO.setPatStatusCode(RegistrationConfig.PATIENT_STATUS_CODE_NOT_ADMITTED);
				_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				_patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_FALSE);
			
				_patientVO.getPatAddress().setIsValid(Config.IS_VALID_ACTIVE);
				_patientVO.getPatAddress().setSeatId(_patientVO.getSeatId());
				AddressVO addressVO=_patientVO.getPatAddress();
				addressVO.setPatIsUrban(_patientVO.getPatIsUrban());
				
				
				///temp code for trapping error
				if(_arrEpisodeVO==null || arrEpisodeVOLength==0)
						throw new HisApplicationExecutionException("Episode Array is Null");

				
					dailyPatVO.setIsValid(Config.IS_VALID_ACTIVE);
					PatientBOHelper.setNewPatRegEpisodeVO(_arrEpisodeVO[i]);
					
					if (_patientVO.getRegistrationType().equalsIgnoreCase(RegistrationConfig.REGISTRATION_TYPE_GENERAL_OPD))
						{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_OPD);						
						}
					else
					{
						_arrEpisodeVO[i].setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL);
						_arrEpisodeVO[i].setEpisodeVisitType(RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL);
					}
					_arrEpisodeVO[i].setPatCrNo(dailyPatVO.getPatCrNo());
					_arrEpisodeVO[i].setPatSecondaryCatCode(dailyPatVO.getPatSecondaryCatCode());
					_arrEpisodeVO[i].setPatPrimaryCatCode(dailyPatVO.getPatPrimaryCatCode());

					_arrEpisodeVO[i].setIsValid(Config.IS_VALID_ACTIVE);
					
					_patientVO.setDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
					HelperMethods.populate(dailyPatVO, _arrEpisodeVO[i]);
					HelperMethods.populate(dailyPatVO, _patientVO);

					dailyPatVO.setIsReferred(_patientVO.getIsReferred());
					
					dailyPatVO.setPatIsOld(RegistrationConfig.IS_OLD_FALSE);
					dailyPatVO.setEpisodeVisitNo("1");
					
					dailyPatVO.setRoomUsability(RegistrationConfig.ROOM_USABILITY_NO_BOUND);

					if(!_patientVO.getIsDuplicate().equals("1")){
					crNoArr=essentialDAO.checkDuplicateRegistration(_patientVO, _userVO);
					if(crNoArr!=null)					{
					PatientVO[] patientVOArr = new PatientVO[crNoArr.length];
					
					for(i=0;i<crNoArr.length;i++)
					{
						PatientVO vo=new PatientVO();
						vo.setPatCrNo(crNoArr[i]);
						patientVOArr[i]=vo	;
					}
					for(i=0;i<crNoArr.length;i++)
					{
						allPatientVoList.add(searchPatientByCrNo(patientVOArr[i], _userVO));	
					}

					essentialMap.put(RegistrationConfig.ALL_PATIENT_VO_LIST,allPatientVoList);
					}
						if(crNoArr!=null)
						{	WebUTIL.setMapInSession(essentialMap, _request);
						throw new HisDuplicateRecordException("Duplicate Registration");
						}
			}
						
						
				dailyPatVO.setDepartmentUnitCode(dailyPatVO.getDepartmentCode());
				dailyPatVO=dailyPatDao.generateQueueNumber(dailyPatVO, _userVO);				
				//This commented method is used for new reg
				//dailyPatVO=dailyPatDao.generateQueueNumberDeptWise(dailyPatVO, _userVO);
				
				//generating the Cr Number
				String CrNO=patDao.generateCrNumber(_userVO);
				_patientVO.setPatCrNo(CrNO);
				_patientVO.getPatAddress().setPatCrNo(_patientVO.getPatCrNo());
				_patientVO.setSeatId(_userVO.getSeatId());
				
				dailyPatVO.setPatAge(_patientVO.getPatAge());
				dailyPatVO.setPatDOB(_patientVO.getPatDOB());
				dailyPatVO.setPatAgeUnit(_patientVO.getPatAgeUnit());
				dailyPatVO.setIsActualDob(_patientVO.getIsActualDob());
				
				///get age or DOB
				//_patientVO=patDao.getDOBAndAge(_patientVO);
				
				//seting dialypatientvo details
				dailyPatVO.setPatCrNo(CrNO);
				//dailyPatVO.setPatAge(_patientVO.getPatAge());
				//dailyPatVO.setPatDOB(_patientVO.getPatDOB());
				BPLDetailsVO bplVO=new BPLDetailsVO();

									
				////setching chagres again in case the ajax for charge has not responded
				//change done by : Prakhar Misra
				//Dated: 3-Feb-2012
				String billAmount=essentialDAO.getBillAmountBasedOnCategory(dailyPatVO.getPatPrimaryCatCode(), _userVO);
				
				
				//explicity fetching and setting the amount in VO
				dailyPatVO.setPatAmountCollected(billAmount);
				//insert into daily
					dailyPatVO = dailyPatDao.createNewRegistration(daoObj,dailyPatVO, _userVO);
					
					
					//_patientVO.setPatAge(dailyPatVO.getPatAge());
					//_patientVO.setPatDOB(dailyPatVO.getPatDOB());
					
					//explicity fetching and setting the amount in VO
					_patientVO.setPatAmountCollected(billAmount);
					
					//Logic Changed for Hospital Wise Renewal--Start
					//insert into patient
					//_patientVO = patDao.create(daoObj,_patientVO, _userVO);
					
					int key=Integer.parseInt(Config.RENEWAL_TYPE);
					System.out.println("key======"+key);
					switch(key){
					case 0: _patientVO=patDao.create(daoObj,_patientVO, _userVO);  break; 
						
					case 1: 
						 	_patientVO.setExpiryDate(Config.HOSPITAL_RENEWAL_EXPIRY_MONTH);
						 	_patientVO=patDao.create(daoObj,_patientVO, _userVO,RegistrationConfig.ISEXPIRY_TYPE_MONTH);
						 	//_patientVO=patDao.create(daoObj,_patientVO, _userVO);   
							 break;
					case 2: 
						    //_patientVO.setExpiryDate(Config.HOSPITAL_RENEWAL_EXPIRY_DAY); 
						  //to calculate expiry date out of procedure so that it cold be used for prinintg
							String expiryDate=patDao.getExpiryDate(_userVO);
							_patientVO.setExpiryDate(expiryDate);
						   // _patientVO=patDao.create(_patientVO, _userVO,RegistrationConfig.ISEXPIRY_TYPE_DAY);
						    _patientVO=patDao.create(daoObj,_patientVO, _userVO);   
						break;
						
					case 3:	_patientVO=patDao.create(daoObj,_patientVO, _userVO); break;
					
					case 4: _patientVO=patDao.create(daoObj,_patientVO, _userVO); break;
					
					case 5:	_patientVO=patDao.create(daoObj,_patientVO, _userVO); break;
						
					}
					
					////Logic Changed for Hospital Wise Renewal--End 
					
					if(dailyPatVO.getIsActualDob().equals("1"))
					{
						_patientVO.setPatAge(dailyPatVO.getPatAge());
						_patientVO.setPatAgeUnit("");
					}
					
					//insert into address dteial
					addDao.createNewAddress(daoObj,addressVO, _userVO);
					
					
					
					
					HelperMethods.populate(_arrEpisodeVO[i], dailyPatVO);
					
					// Create new entry in Episode detail
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{

						_arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_EXTERNAL);
						_episodeReferVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
						episodeRefDtlVO.setIsRefferInOut(RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL);
					}
					else _arrEpisodeVO[i].setEpisodeReferAccept(RegistrationConfig.EPISODE_REFERRAL_ACCEPTANCE_NONE);
					
					_arrEpisodeVO[i].setIsCardPrint(RegistrationConfig.IS_CARD_PRINT_NEW_DEPARTMENT);
				
					_arrEpisodeVO[i].setEntryDate(_patientVO.getSystemDate());
					//PatientBOSupport.checkForRenewalAtSaveToEpisodeDaoNewRegistration(daoObj,_arrEpisodeVO[i], _userVO, tx);
					
					_arrEpisodeVO[i].setDepartmentCode(_arrEpisodeVO[i].getDepartmentUnitCode().substring(0, 3));
					
					
					//changed on 14-feb-2012 by prakhar misra
					//to calculate expiry date out of procedure so that it cold be used for prinintg
					//code commented for hospital wise renewal
					//String expiryDate=episodeDAO.getExpiryDate(_arrEpisodeVO[i],_userVO);
					
					//_arrEpisodeVO[i].setExpiryDate(expiryDate);
					//code commented for hospital wise renewal--end
					
					episodeDAO.create(daoObj, _arrEpisodeVO[i], _userVO);
					

					
					if (_patientVO.getIsReferred().equals(RegistrationConfig.IS_REFERRED_TRUE))
					{
						HelperMethods.populate(_episodeReferVO, _patientVO);
						HelperMethods.populate(_episodeReferVO, _arrEpisodeVO[i]);
						HelperMethods.populate(episodeRefDtlVO, _arrEpisodeVO[i]);
						
						episodeRefDtlVO.setSystemIPAddress(_patientVO.getSystemIPAddress());
						episodeRefDtlVO.setExternalHospitalCode(_patientVO.getPatRefGnctdHospitalCode());
						episodeRefDtlVO.setExternalHospitalDepartment(_patientVO.getPatRefGnctdHospitalDept());
						episodeRefDtlVO.setExternalHospitalDepartmentUnit(_patientVO.getPatRefGnctdHospitalDeptUnit());
						episodeRefDtlVO.setExternalHospitalDoctorName(_patientVO.getPatRefDoctor());
						episodeRefDtlVO.setExternalHospitalName(_patientVO.getPatRefHospitalName());
						episodeRefDtlVO.setExternalHospitalPatCrNo(_patientVO.getPatRefGnctdHospitalCrno());
						episodeRefDtlVO.setToDepartmentCode(_arrEpisodeVO[i].getDepartmentCode());
						episodeRefDtlVO.setToDepartmentUnitCode(_arrEpisodeVO[i].getDepartmentUnitCode());
						episodeRefDtlDAO.createNewRegistration(daoObj,episodeRefDtlVO, _userVO);

					}
					
					regChgDtlVO[i] = new RegChargeDtlVO();
					regChgDtlVO[i].setTariffId(_userVO.getTariffID());
					regChgDtlVO[i].setServiceId(RegistrationConfig.REGISTRATION_SERVICE_ID);
					regChgDtlVO[i].setModuleId(_userVO.getModuleId());
					HelperMethods.populate(regChgDtlVO[i], _arrEpisodeVO[i]);
					regChgDtlVO[i].setPatAmountCollected(_patientVO.getPatAmountCollected());
					regChgDtlVO[i].setSystemIPAddress(_patientVO.getSystemIPAddress());
					if (!(regChgDtlVO[i].getPatAmountCollected().equals("0") || regChgDtlVO[i].getPatAmountCollected().equals("") || regChgDtlVO[i]
							.getPatAmountCollected() == null)) 
					{
						///registration module billing
						regChgDtlDAO.create(daoObj,regChgDtlVO[i], _userVO);
						directChargeVO[i]=new DirectChageDetailVO();
						HelperMethods.populate(directChargeVO[i],regChgDtlVO[i]);
						directChargeDtlDao.create(daoObj,directChargeVO[i], _userVO);
						////Billing module billing///
						//regChgDtlDAO.createBillinProcedure(regChgDtlVO[i], _userVO);
					}
					
					*//*** Query to fetch Disclaimer*********************//*
					String patRegCatCode=_patientVO.getPatRegCatCode();
					String usablityFlag="";
					if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
						usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL;
					if(patRegCatCode.equals(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL)){
						usablityFlag=RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_SPECIAL;
					}	
					_arrEpisodeVO[i].setDisclaimer(getDisclamer(RegistrationConfig.DEFAULT_DISCLAIMER_USABILITY_FLAG_NORMAL,_arrEpisodeVO[i].getDepartmentUnitCode(),_userVO));
					
				*//***********************************************//*	
					synchronized (daoObj) {
						daoObj.fire();
					}
					
				}

			}
		catch(HisDuplicateRecordException e){
			tx.rollback();
			throw new HisDuplicateRecordException("Duplicate Registration");
		}
		catch (HisInvalidTokenNumberException e)
		{
			tx.rollback();
			//e.printStackTrace();
			throw new HisInvalidTokenNumberException(e.getMessage());
		}
		catch (HisAppointmentNotAvailableException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			//e.printStackTrace();
			
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			//e.printStackTrace();
			
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
			if (daoObj != null) {
				//daoObj.free();
				daoObj = null;
			}
			tx.close();
			
		}
		//}
		return _arrEpisodeVO; // <<<
	}// each VisitStampVO keeps PatientVO
	
	
	public boolean saveMCTSRegNo(MCTSRegistrationVO MCTSRegistrationVO_p, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int x = 0, y = 0;
		DailyPatientVO _dailyPatientVO = new DailyPatientVO();
		EpisodeVO _episodeVO = new EpisodeVO();

		try
		{
			System.out.println("Begining transaction");
			tx.begin();
			// /////////////////////////////////////////////////////

			MCTSRegistrationDAO MCTSRegistrationDAO_p = new MCTSRegistrationDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			MCTSRegistrationDAO_p.chkDuplicate(MCTSRegistrationVO_p, _userVO);
			_episodeVO.setPatCrNo(MCTSRegistrationVO_p.getPatCrNo());
			_episodeVO.setStrMctsNo(MCTSRegistrationVO_p.getStrMctsNo());
			_episodeVO.setEpisodeVisitNo(MCTSRegistrationVO_p.getEpisodeVisitNo());
			if (MCTSRegistrationVO_p.isBExistStatus() == true)	//no duplicacy , so new record is added
			{
			x = episodeDAO.updateMCTSRegNo(_episodeVO, _userVO);
		
					if (x >= 1)
					{
						MCTSRegistrationDAO_p.createMCTSReg(MCTSRegistrationVO_p, _userVO);
					}
			}	
		}
		catch (HisUpdateUnsuccesfullException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisUpdateUnsuccesfullException();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			System.out.println("y = " + y);
			tx.close();
		}
		if (y >= 1) return true;
		else return false;
	}*/
	
	/**
	 * Retrieves patient details on the basis of CR No from the Patient Dtl Table. Provides age of the patient according to
	 * the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 * 
	 * @param _patientVO Provides CR No to be searched.
	 * @param _userVO Provides User details.
	 * @return PatientVO with values stored in DB.
	 */
/*	public PatientVO retrieveByCrNoFromMCTS(PatientVO _patientVO, UserVO _userVO)
	{
		String str = _patientVO.getPatCrNo();
		boolean unknown = false;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			PatientImageDtlDAO patImageDtlDao=new PatientImageDtlDAO(tx);
			AddressVO _addressVO = new AddressVO();
			PatientImageDtlVO patImageDtlVO=new PatientImageDtlVO();
			_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			try
			{
				_addressVO = addDao.retrieveByCrNo(_patientVO, _userVO);
			}
			catch (HisRecordNotFoundException e)
			{
				// throw new HisRecordNotFoundException();
				unknown = true;
			}

			_patientVO = patientDao.retrieveByCrNoFromMCTS(_patientVO, _addressVO, _userVO);
			String fname = "(Unknown)" + _patientVO.getPatFirstName();
			
			if (_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)) _patientVO.setPatFirstName(fname);

			// if(_patientVO.getPatAge()!=null &&
			// _patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE)){
			if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);
				//System.out.println("_patientVO.getPatAge()1:::" + _patientVO.getPatAge());

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Y");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("M");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("W");
			
			}
			
			 * System.out.println("Patient dob::"+_patientVO.getPatDOB()); System.out.println("Patient
			 * isurban::"+_patientVO.getPatIsUrban()); System.out.println("after populate"); System.out.println("Patient
			 * first name::"+_patientVO.getPatFirstName());
			 * System.out.println("_patientVO.getPatAddCity()"+_patientVO.getPatAddCity());
			 

			if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
			{
				patImageDtlVO=patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(),_userVO);
				 boolean flag =HisFileControlUtil.isWindowsOS();
				  if(!flag)
				  {
					  patImageDtlVO.setFilePath("/root"+patImageDtlVO.getFilePath()); 
				  }
				 
				_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
				_patientVO.setImageFileName(patImageDtlVO.getFilePath() + patImageDtlVO.getFileNo());
			}

			//System.out.println(_patientVO.getPatPrimaryCat()+"XXXXX>>>>>"+_patientVO.getPatPrimaryCatCode());
			if (_patientVO.getPatPrimaryCatCode() == null || _patientVO.getPatPrimaryCatCode().equals("")) throw new HisRecordNotFoundException(
					"Current Patient Category is invalid please change the Patient Category first");
			if (_patientVO.getPatPrimaryCat() == null || _patientVO.getPatPrimaryCat().equals("")) 
				throw new HisRecordNotFoundException("Current Patient Category is invalid please change the Patient Category first");
		}
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("inside  BO Record not found exception");
			System.out.println(e.getMessage());
			//e.printStackTrace();
			
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			//System.out.println("inside  BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _patientVO;
	}
	
	public EpisodeVO[] getAllLatestEpisodesVisited(String crNo, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeVO;

		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			_arrEpisodeVO = episodeDao.getAllLatestEpisodesVisited(crNo, _userVO);

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
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrEpisodeVO;
	}*/
	/*
	public List<PatientDetailVO> getPatientsForPoliceExaminationReqt(String _patCrNo, UserVO _userVO)
	{
		List<PatientDetailVO> dailyPatientVOs;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			dailyPatientVOs = dailyPatientDAO.getPatientsForPoliceExaminationReqt(_patCrNo, _userVO);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return dailyPatientVOs;
	}

	public void savePoliceExaminationReqtDtl( PoliceExaminationReqtDtlVO objPoliceExamReqtDtlVO_p, UserVO objUserVO_p) {
		
		//JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO=null;
		try
		{
//			tx.begin();
			objHisDAO = new HisDAO("Emergency","PoliceExaminationReqtDtlDAO");
			PoliceExaminationReqtDtlDAO objPoliceExamReqtDtlDAO = new PoliceExaminationReqtDtlDAO();
			boolean flagDuplicate = objPoliceExamReqtDtlDAO.isPoliceExamReqtDtlExist(objPoliceExamReqtDtlVO_p, objUserVO_p);
			if(!flagDuplicate)
				objPoliceExamReqtDtlDAO.savePoliceExaminationReqtDtl("1",objPoliceExamReqtDtlVO_p, objHisDAO, objUserVO_p);
			else
				throw new HisDuplicateRecordException("Police Examination Request already raised for this Department");
			
			synchronized (objHisDAO) {
				objHisDAO.fire();
			}
		}
		catch (HisDuplicateRecordException e)
		{
			System.out.println(e.getMessage());
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		
		
	}

	public List<PatientDetailVO> getPatientsForPoliceExaminationReportGeneration(String strMode_p,
			String strPatCrNo_p, String strEpisode_p,
			String strEpisodeVisitNo_p, UserVO objUserVO_p) {
		
		List<PatientDetailVO> dailyPatientVOs;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			dailyPatientVOs = objEssentialDAO.getPatientsForPoliceExaminationReqt(strMode_p,strPatCrNo_p,strEpisode_p,strEpisodeVisitNo_p, objUserVO_p);
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
			System.out.println("error.... Patient BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return dailyPatientVOs;
	}*/
	
	
/*	public void savePoliceExaminationReportGenerationDtl( PoliceExaminationReqtDtlVO objPoliceExamReqtDtlVO_p, UserVO objUserVO_p) {
		
		//JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO=null;
		try
		{
//			tx.begin();
			objHisDAO = new HisDAO("Emergency","PoliceExaminationReqtDtlDAO");
			PoliceExaminationReqtDtlDAO objPoliceExamReqtDtlDAO = new PoliceExaminationReqtDtlDAO();
			
			objPoliceExamReqtDtlDAO.savePoliceExaminationReqtDtl("2",objPoliceExamReqtDtlVO_p, objHisDAO, objUserVO_p);
						
		}
		catch (HisDuplicateRecordException e)
		{
			System.out.println(e.getMessage());
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		
		
	}*/
	public PatientVO[] searchPatientByName(String _patientName, UserVO _userVO)
	{
		// System.out.println("_crNo::"+ _crNo);
		PatientVO[] _patientVO = new PatientVO[]
		{};
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			AddressVO[] _addressVO = new AddressVO[]
			{};
			try
			{
				_addressVO = addDao.retrieveByName(_patientName, _userVO);
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println("Patient is unknown");
			}

			_patientVO = patientDao.retrieveByName(_patientName, _addressVO, _userVO);
			/*
			 * System.out.println("_patientVO.getPatAddTypeCode():::"+_patientVO.getPatAddTypeCode());
			 * System.out.println("_patientVO.getPatAddCityLocCode():::"+_patientVO.getPatAddCityLocCode());
			 * System.out.println("_patientVO.getIsActualDob():::"+_patientVO.getIsActualDob());
			 * System.out.println("_patientVO.getPatAge():::"+_patientVO.getPatAge());
			 * System.out.println("_patientVO.getEntryDate():::"+_patientVO.getEntryDate());
			 */

			for (int i = 0; i < _patientVO.length; i++)
			{
				if (_patientVO[i].getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
				{
					String fname = "(Unknown)" + _patientVO[i].getPatFirstName();
					_patientVO[i].setPatFirstName(fname);
				}
				if (_patientVO[i].getPatAge() != null && _patientVO[i].getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE))
				{
					String patAgeWithUnit = _patientVO[i].getPatAge();
					int startidx = patAgeWithUnit.lastIndexOf(" ");
					String ageunit = patAgeWithUnit.substring(startidx + 1);
					patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
					_patientVO[i].setPatAge(patAgeWithUnit);

					if (ageunit.equalsIgnoreCase("yr")) _patientVO[i].setPatAgeUnit("Y");
					if (ageunit.equalsIgnoreCase("mth")) _patientVO[i].setPatAgeUnit("M");
					if (ageunit.equalsIgnoreCase("d")) _patientVO[i].setPatAgeUnit("D");
				}
			}
			/*
			 * System.out.println("Patient dob::"+_patientVO.getPatDOB()); System.out.println("Patient
			 * isurban::"+_patientVO.getPatIsUrban()); System.out.println("after populate"); System.out.println("Patient
			 * first name::"+_patientVO.getPatFirstName());
			 * System.out.println("_patientVO.getPatAddCity()"+_patientVO.getPatAddCity());
			 */
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			throw new HisRecordNotFoundException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return (_patientVO);
	}
	
//Added by Vasu
	public void savePatientDocumentDetail(DocumentUploadDtlVO[] docUploadVos,DocumentUploadDtlVO[] documentUploadRevokeDtlVO,vo.registration.PatientVO patientVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count;
		String newFileName="";
		int i = 0;
		HisDAO objHisDAO = null;
		try
		{
			objHisDAO = new HisDAO("OPD","PatientBO");
			DocumentUploadDtlDAOi docUploadDaoi = new DocumentUploadDtlDAO(tx);
			tx.begin();
			if( (docUploadVos!=null) && (docUploadVos.length>0) )
				{
				count=docUploadDaoi.getContCrNoWise(docUploadVos[0].getPatCrNo(), userVO);
				//int countInt=Integer.parseInt(count)+10;
				int countInt=Integer.parseInt(count);
				for (; i < docUploadVos.length; i++)
				{
					String sysdate=docUploadVos[i].getEntryDate();
					char[] charArray; 
					String newSysdate="";
					charArray=sysdate.toCharArray();
					for(int charIndex=0;charIndex<charArray.length;charIndex++){
						if(Character.isSpaceChar(charArray[charIndex])){
							newSysdate=newSysdate+'-';
						}
						else{
							newSysdate=newSysdate+charArray[charIndex];
						}
					}
					newSysdate=newSysdate.replace('/','-');
					newSysdate=newSysdate.replace(':','-');
					countInt=countInt+1;
					String[] filePartName={docUploadVos[i].getPatCrNo(),newSysdate,String.valueOf(countInt)};
					String fileName=HelperMethods.getFileName('$',filePartName);
					//String fileExt=docUploadVos[i].getDocumentName().substring(docUploadVos[i].getDocumentName().lastIndexOf("."));
					//fileExt = fileExt.substring(1);
					String fileExt = docUploadVos[i].getFileName();
					fileExt = "."+fileExt;
					fileName=fileName+fileExt;
					newFileName= "16_Doc_"+docUploadVos[i].getPatCrNo()+"_"+countInt+fileExt;
					String docId="";
					docId=docUploadVos[i].getPatCrNo()+"_"+countInt;
					patientVO.setDocId(docId);
					docUploadVos[i].setDocumentCode(docId);
					docUploadVos[i].setDocumentName(newFileName);
					docUploadVos[i].setDocumentDirectoryPath(fileExt);
					docUploadVos[i].setDocSlNo(""+countInt);
					
					docUploadDaoi.create(docUploadVos[i], userVO);
					}
				}
			if(documentUploadRevokeDtlVO!=null)
			{
				for(int j=0;j<documentUploadRevokeDtlVO.length;j++)
				{
					docUploadDaoi.revokeDocument(documentUploadRevokeDtlVO[j], userVO);
				}
			}
			//vo.registration.PatientImageDtlVO patientImageDtlVO = new vo.registration.PatientImageDtlVO();
			hisglobal.vo.PatientImageDtlVO patientImageDtlVO =new hisglobal.vo.PatientImageDtlVO();
			
			//DocumentUploadDtlDAOi docUploadDaoi = new DocumentUploadDtlDAO(tx);
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			
			if (patientVO.getImageFile() != null)
			{
				//HelperMethods.populate(patientImageDtlVO, objArrEpisodeVO_p[i]);
				patientImageDtlVO.setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD+"_Image_"+ patientVO.getPatCrNo()+"_01");	
				//if(null!=objRegistrationConfigVO.getIsImageStoredFTP() && !objRegistrationConfigVO.getIsImageStoredFTP().equals(""))
				//{
				//if(objRegistrationConfigVO.getIsImageStoredFTP().equals("1"))
				//{
				patientImageDtlVO.setPatCrNo(patientVO.getPatCrNo());
				patientImageDtlVO.setEpisodeCode(patientVO.getEpisodeCode());
				patientImageDtlVO.setImageFile(patientVO.getImageFile());
				patientImageDtlVO.setDocId(patientVO.getDocId());
				//System.out.println("Inside FTP");
				File file=new File(patientVO.getImageFileName());
				FileUtils.writeByteArrayToFile(file, patientVO.getImageFile());
				//FileInputStream fileInFTPStream = new FileInputStream(file);
				//FTPFileTransfer.uploadFile(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, patientImageDtlVO.getFileNo(), fileInFTPStream, patientImageDtlVO.getPatCrNo());
				//System.out.println("FTP Saved");
				//}
				
				//else{
					
				//}
				//}
				boolean flag =true;
				if(flag)
				{
					patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
				}
				else
				{
					patientImageDtlVO.setFilePath(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
				}
				patientImageDtlVO.setIsValid(Config.IS_VALID_ACTIVE);
				patientImageDtlVO.setIsImageDefault(RegistrationConfig.IS_IMAGE_DEFAULT_TRUE);
				//patientImageDtlDAO.create(objHisDAO,patientImageDtlVO, userVO);
			}

			/*synchronized (objHisDAO) {
				objHisDAO.fire();
			}*/
			
			patientImageDtlDAO.saveImageToPostgres(objHisDAO,patientImageDtlVO,patientVO.getImageFile(),userVO);
			
			

		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			if(objHisDAO!=null){
				objHisDAO.free();
			}
			objHisDAO=null;
			tx.close();
		}
	}

	public byte[] fetchImageFromPostgres(PatientImageDtlVO patientImageDtlVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		byte[] getDoc =null;
		try
		{
			objHisDAO = new HisDAO("OPD","PatientBO");
			PatientImageDtlDAO patientImageDtlDAO = new PatientImageDtlDAO(tx);
			tx.begin();
			
			 getDoc= patientImageDtlDAO.latestFetchImagePostgres(patientImageDtlVO.getFileNo());
			

		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			
				if(objHisDAO!=null){
					objHisDAO.free();
				}
				objHisDAO=null;
			
			tx.close();
		}
	return getDoc;
	}

	
	public List searchPatientDetailByDemographicDetailForSearchTile(vo.registration.PatientVO _patientVO, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPatientJsonObjString=null;
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			
			_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);

			lstPatientJsonObjString = patientDao.searchPatientDetailByDemographicDetailForSearchTile(_patientVO, objUserVO_p);
			
			/*if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("Wk");
			}*/
		
		/*
		 * Comented for now till the time image uploading is not done
		 * 
		 * 
					if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
					{
						patImageDtlVO = patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(), objUserVO_p);
						boolean flag = HisFileControlUtil.isWindowsOS();
						if (!flag)
						{
							patImageDtlVO.setFilePath("/root" + patImageDtlVO.getFilePath());
						}
		
						_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
						_patientVO.setImageFileName(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo());
					}
		*/
			//if (_patientVO.getPatCatCode() == null || _patientVO.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					//"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return lstPatientJsonObjString;
	}
	
	public List searchPatientDetailByCRNoForSearchTile(vo.registration.PatientVO _patientVO, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPatientJsonObjString=null;
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			
			_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);

			lstPatientJsonObjString = patientDao.searchPatientDetailByCRNoForSearchTile(_patientVO, objUserVO_p);
			
			/*if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("Wk");
			}*/
		
		/*
		 * Comented for now till the time image uploading is not done
		 * 
		 * 
					if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
					{
						patImageDtlVO = patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(), objUserVO_p);
						boolean flag = HisFileControlUtil.isWindowsOS();
						if (!flag)
						{
							patImageDtlVO.setFilePath("/root" + patImageDtlVO.getFilePath());
						}
		
						_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
						_patientVO.setImageFileName(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo());
					}
		*/
			//if (_patientVO.getPatCatCode() == null || _patientVO.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					//"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return lstPatientJsonObjString;
	}
	public List searchPatientDetailByUniqueIdForSearchTile(PatientIdVO objPatientIdVO_p, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPatientJsonObjString=null;
		try
		{
			tx.begin();
			PatientIdDetailDAO patientIdDetailDao=new PatientIdDetailDAO(tx);
						
			lstPatientJsonObjString = patientIdDetailDao.searchPatientDetailByUniqueIdForSearchTile(objPatientIdVO_p, objUserVO_p);
			
			/*if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("Wk");
			}*/
		
		/*
		 * Comented for now till the time image uploading is not done
		 * 
		 * 
					if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
					{
						patImageDtlVO = patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(), objUserVO_p);
						boolean flag = HisFileControlUtil.isWindowsOS();
						if (!flag)
						{
							patImageDtlVO.setFilePath("/root" + patImageDtlVO.getFilePath());
						}
		
						_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
						_patientVO.setImageFileName(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo());
					}
		*/
			//if (_patientVO.getPatCatCode() == null || _patientVO.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					//"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return lstPatientJsonObjString;
	}
	public List searchPatientDetailByMobileNOForSearchTile(vo.registration.PatientVO _patientVO, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPatientJsonObjString=null;
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			
			_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);

			lstPatientJsonObjString = patientDao.searchPatientDetailByMobileNoForSearchTile(_patientVO, objUserVO_p);
			
			/*if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("Wk");
			}*/
		
		/*
		 * Comented for now till the time image uploading is not done
		 * 
		 * 
					if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
					{
						patImageDtlVO = patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(), objUserVO_p);
						boolean flag = HisFileControlUtil.isWindowsOS();
						if (!flag)
						{
							patImageDtlVO.setFilePath("/root" + patImageDtlVO.getFilePath());
						}
		
						_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
						_patientVO.setImageFileName(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo());
					}
		*/
			//if (_patientVO.getPatCatCode() == null || _patientVO.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					//"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return lstPatientJsonObjString;
	}
	public List searchPatientDetailByEmailForSearchTile(vo.registration.PatientVO _patientVO, UserVO objUserVO_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPatientJsonObjString=null;
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			AddressDAO addDao = new AddressDAO(tx);
			
			_patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);

			lstPatientJsonObjString = patientDao.searchPatientDetailByEmailForSearchTile(_patientVO, objUserVO_p);
			
			/*if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) _patientVO.setPatAgeUnit("Wk");
			}*/
		
		/*
		 * Comented for now till the time image uploading is not done
		 * 
		 * 
					if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
					{
						patImageDtlVO = patImageDtlDao.getPatientDefaultImageByCrNo(_patientVO.getPatCrNo(), objUserVO_p);
						boolean flag = HisFileControlUtil.isWindowsOS();
						if (!flag)
						{
							patImageDtlVO.setFilePath("/root" + patImageDtlVO.getFilePath());
						}
		
						_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo()));
						_patientVO.setImageFileName(patImageDtlVO.getFilePath() + "/" + patImageDtlVO.getFileNo());
					}
		*/
			//if (_patientVO.getPatCatCode() == null || _patientVO.getPatCatCode().equals("")) throw new HisRecordNotFoundException(
					//"Current Patient Category is invalid please change the Patient Category first");
		
		}
		catch (HisRecordNotFoundException e)
		{
			// System.out.println("inside BO Record not found exception");
			System.out.println(e.getMessage());
			// e.printStackTrace();

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			// System.out.println("inside BO HisApplicationExecutionException");
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		finally
		{
			tx.close();
		}
		return lstPatientJsonObjString;
	}
	
}// end of class
