package registration.bo;

import hisglobal.CrNo;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisAppointmentNotAvailableException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDeadPatientException;
import hisglobal.exceptions.HisDeptUnitAlreadyVisited;
import hisglobal.exceptions.HisDeptUnitIsClosed;
import hisglobal.exceptions.HisDeptUnitNotOnDuty;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInvalidTokenNumberException;
import hisglobal.exceptions.HisMlcAlreadyExistException;
import hisglobal.exceptions.HisNoOpenEpisodeFoundException;
import hisglobal.exceptions.HisNotAnEmergencyCaseException;
import hisglobal.exceptions.HisNotAnIPDcaseException;
import hisglobal.exceptions.HisNotAnOPDcaseException;
import hisglobal.exceptions.HisNotEligibleForMLCException;
import hisglobal.exceptions.HisOPDPatientEligibilityException;
import hisglobal.exceptions.HisPatientNotReferredException;
import hisglobal.exceptions.HisPatientStillUnknownException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisReferredRecordNotFoundException;
import hisglobal.exceptions.HisRenewalCardRequired;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.GlobalEssentialDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.TransactionContext;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.Printer;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.ChangeCategoryVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DeptUnitRosterModDtlVO;
import hisglobal.vo.DirectChageDetailVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.DuplicateRenewVO;
import hisglobal.vo.EmpMasterVO;
import hisglobal.vo.EpisodeActionDtlVO;
import hisglobal.vo.EpisodeCloseVO;
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
import hisglobal.vo.PatientBroughtByDtlVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientParichitVO;
import hisglobal.vo.PatientStatusChangeVO;
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
import hisglobal.vo.YellowSlipEntryDtlVO;
import hisglobal.vo.PatientAdhaarDtlVO;
 



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

 


import registration.RegistrationConfig;
import registration.RegistrationSlip;
import registration.bo.helper.PatientBOHelper;
 
import registration.dao.AddressDAO;
import registration.dao.PatientDAO;
import registration.vo.BPLDetailsVO;
 

/**
 * PatientBO is a class which specifies the business logic for all the transactions. PatientBO class provides a standard
 * interface between Controller and Data Access Objects.
 * 
 * @author AHIS*
 */

public class PatientBO implements PatientBOi
{
	
 
	
	
	
	
	
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
			/*
			 * System.out.println("Patient dob::"+_patientVO.getPatDOB()); System.out.println("Patient
			 * isurban::"+_patientVO.getPatIsUrban()); System.out.println("after populate"); System.out.println("Patient
			 * first name::"+_patientVO.getPatFirstName());
			 * System.out.println("_patientVO.getPatAddCity()"+_patientVO.getPatAddCity());
			 */
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
			/*
			 * System.out.println("Patient dob::"+_patientVO.getPatDOB()); System.out.println("Patient
			 * isurban::"+_patientVO.getPatIsUrban()); System.out.println("after populate"); System.out.println("Patient
			 * first name::"+_patientVO.getPatFirstName());
			 * System.out.println("_patientVO.getPatAddCity()"+_patientVO.getPatAddCity());
			 */
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

}// end of class
