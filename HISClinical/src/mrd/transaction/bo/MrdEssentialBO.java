/*Copyright Information			: C-DAC, Noida  
	 ## Project Name				: NIMS
	 ## Name of Developer		 	: 
	 ## Module Name					: MRD
	 ## Process/Database Object Name:Estimate Certificate issue after Request
	 ## Purpose						:Certificate Issue Process
	 ## Date of Creation			: 
	 ## Modification Log			:				
     ## Modify Date				    :22-Nov-2014
     ##	Reason	(CR/PRS)			: Certificate Issue Process functionality implementation
 	 
 	 ## Modify By				    :Amit Garg 	
 	 ## Modify Date				    :04-DEC-2014
     ##	Reason	(CR/PRS)			: Medical and fitness certificate issue functionality implementation
 	
 */
package mrd.transaction.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.GlobalEssentialDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.CaseSheetDispatchVO;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.DischargePrintingVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeRestAdviceVO;
import hisglobal.vo.MrdIcdDtlVO;
import hisglobal.vo.MrdMedicalCampDtlVO;
import hisglobal.vo.MrdMedicalCampTeamDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordIssueDtlVO;
import hisglobal.vo.MrdRecordLostFoundDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileCDBurnDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.RackShelfMstVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordLostFoundDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.ReportVO;
import hisglobal.vo.RequestPurposeMstVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.StaffDetailVO;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.dao.InPatientEssentialDAO;
import inpatient.transaction.dao.InPatientEssentialDAOi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;
import mrd.masters.dao.RecordTypeCheckListDAO;
import mrd.masters.dao.RecordTypeCheckListDAOi;
import mrd.masters.dao.RequestPurposeMstDAO;
import mrd.masters.dao.RequestPurposeMstDAOi;
import mrd.transaction.dao.BirthDeathUploadDAO;
import mrd.transaction.dao.BirthDeathUploadDAOi;
import mrd.transaction.dao.CaseSheetDispatchDAO;
import mrd.transaction.dao.CaseSheetDispatchDAOi;
import mrd.transaction.dao.CaseSheetDtlDAO;
import mrd.transaction.dao.CaseSheetDtlDAOi;
import mrd.transaction.dao.CertificateBEntryFormDAO;
import mrd.transaction.dao.CrNoMergeDAO;
import mrd.transaction.dao.CrNoMergeDAOi;
import mrd.transaction.dao.DischargePrintDAO;
import mrd.transaction.dao.DischargePrintDAOi;
import mrd.transaction.dao.DupRecPrintAndHandoverDAO;
import mrd.transaction.dao.DuplicateRecordPrintReqDAO;
import mrd.transaction.dao.EstimateCertificateIssueDAO;
import mrd.transaction.dao.EstimateRequestDAO;
import mrd.transaction.dao.MRDDocumentUploadDAO;
import mrd.transaction.dao.MRDRecordDtlDAO;
import mrd.transaction.dao.MRDRecordDtlDAOi;
import mrd.transaction.dao.MedicalCertificateDAO;
import mrd.transaction.dao.MedicalCertificateRequestDAO;
import mrd.transaction.dao.MrdDAO;
import mrd.transaction.dao.MrdDAOi;
import mrd.transaction.dao.MrdEssentialDAO;
import mrd.transaction.dao.MrdEssentialDAOi;
import mrd.transaction.dao.MrdMedicalCampDtlDAO;
import mrd.transaction.dao.MrdMedicalCampDtlDAOi;
import mrd.transaction.dao.MrdRecordIssueDtlDAO;
import mrd.transaction.dao.MrdRecordIssueDtlDAOi;
import mrd.transaction.dao.MrdRecordLostFoundDAO;
import mrd.transaction.dao.MrdRecordLostFoundDAOi;
import mrd.transaction.dao.MrdRecordRequestDtlDAO;
import mrd.transaction.dao.MrdRecordRequestDtlDAOi;
import hisglobal.persistence.GlobalClinicalDAO;
import hisglobal.presentation.WebUTIL;
import mrd.transaction.dao.PatientEmrAuditDAO;
import mrd.transaction.dao.PatientProfileCDBurnDAO;
import mrd.transaction.dao.RecordDispatchDtlDAO;
import mrd.transaction.dao.RecordDispatchDtlDAOi;
import mrd.transaction.dao.RecordLostFoundDtlDAO;
import mrd.transaction.dao.RecordLostFoundDtlDAOi;
import mrd.transaction.dao.RecordTypeWiseChecklistDAO;
import mrd.transaction.dao.RecordTypeWiseChecklistDAOi;
import mrd.transaction.dao.RecordTypeWiseEnclosureDAO;
import mrd.transaction.dao.RecordTypeWiseEnclosureDAOi;
import mrd.transaction.dao.RequestRecordDtlDAO;
import mrd.transaction.dao.RequestRecordDtlDAOi;
import mrd.vo.BulletinGenerationVO;
import mrd.vo.BulletinHeadDataVO;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CertificateBEntryFormReqVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import mrd.vo.DupRecPrintReqVO;
import mrd.vo.EstimateCertificateIssueVO;
import mrd.vo.EstimateCertificateReqVO;
import mrd.vo.MRDDocumentUploadVO;
import mrd.vo.BulletinHeadVO;
import mrd.vo.PackageServiceMstVO;
import mrd.vo.PatientEmrAuditVO;
import opd.OpdConfig;
import opd.dao.DocumentUploadDtlDAO;
import opd.dao.DocumentUploadDtlDAOi;
import opd.dao.OpdEssentialDAO;
import registration.dao.EpisodeDAO;
import registration.dao.EssentialDAO;
import registration.dao.EssentialDAOi;

public class MrdEssentialBO implements MrdEssentialBOi
{

	// Getting the List of Diagnosis Given to the Patient on a Particular
	// Episode
	public List getPatDiagnosisList(String diagCodeType, EpisodeRestAdviceVO epiRestAdviceVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lst = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			if (diagCodeType.equals(MrdConfig.DIAGNOSIS_CHOICE_ICD_CODE))
				lst = mrdEssDAO.getPatDiagnosisListICD(diagCodeType, epiRestAdviceVO, userVO);
			else
				lst = mrdEssDAO.getPatDiagnosisListHospital(diagCodeType, epiRestAdviceVO, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lst;
	}

	// Getting the List of All the Consultant Name
	public List getAllConsultantForMC(String unitCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lst = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			lst = mrdEssDAO.getAllConsultantForMC(unitCode, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lst;
	}

	// Getting the List of Generated Medical Certificate
	public PatMedicalDtlVO[] getAllGeneratedMCList(String crNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatMedicalDtlVO[] arrPatMedDtlVO = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			arrPatMedDtlVO = mrdEssDAO.getAllGeneratedMCList(crNo, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return arrPatMedDtlVO;
	}

	// Getting the List of Generated Fitness Certificate
	public PatFitnessDtlVO[] getAllGeneratedFCList(String crNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatFitnessDtlVO[] arrPatFitDtlVO = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			arrPatFitDtlVO = mrdEssDAO.getAllGeneratedFCList(crNo, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return arrPatFitDtlVO;
	}

	// Getting the List of Duplicate Medical Certificate
	public PatMedicalDtlVO[] getAllDuplicateMCList(String crNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatMedicalDtlVO[] arrPatMedDupDtlVO = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			arrPatMedDupDtlVO = mrdEssDAO.getAllDuplicateMCList(crNo, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return arrPatMedDupDtlVO;
	}

	// Getting the List of Duplicate Fitness Certificate
	public PatFitnessDtlVO[] getAllDuplicateFCList(String crNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatFitnessDtlVO[] arrPatFitDupDtlVO = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			arrPatFitDupDtlVO = mrdEssDAO.getAllDuplicateFCList(crNo, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return arrPatFitDupDtlVO;
	}

	// Getting the List Of Certificate, Rack & Shelf List
	public Map getEssentialForCertificateReceived(String certificateRcvMode, CertificateIssueDtlVO _certificateIssueDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		CertificateIssueDtlVO[] certificateIssueDtlVO = null;
		// RecordMovementDtlVO[] recordMovDtlVO = null;
		RackShelfMstVO[] rackShelfVO = null;
		RackMstVO[] rackMstVO = null;
		// List rackNameLst=new ArrayList();
		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);

			if (certificateRcvMode.equals(Config.CERTIFICATE_RECEIVED_OFFLINE)) // Getting
																				// Data
																				// For
																				// OFFLINE
																				// Certificate
																				// Received
			{
				certificateIssueDtlVO = mrdEssDAO.getAllCertificateIssueDtlVOList(_certificateIssueDtlVO, userVO);
			}
			if (certificateRcvMode.equals(Config.CERTIFICATE_RECEIVED_ONLINE)) // Getting
																				// Data
																				// For
																				// ONLINE
																				// Certificate
																				// Received
			{
				certificateIssueDtlVO = mrdEssDAO.getAllMovementCertificateVO(_certificateIssueDtlVO, userVO);
			}
			essentialMap.put(MrdConfig.ALL_CERTIFICATE_ISSUE_DTL_VO_LIST, certificateIssueDtlVO);

			// rackNameLst=mrdMstEssDAO.getRackNameList(userVO);
			// Getting The List of All Rack
			rackMstVO = mrdEssDAO.getAllRack(userVO);
			essentialMap.put(MrdConfig.ALL_RACK_LIST_VO, rackMstVO);

			// Getting The List Of All Shelf
			rackShelfVO = mrdEssDAO.getAllRackshelf(userVO);
			essentialMap.put(MrdConfig.ALL_RACK_SHELF_LIST_VO, rackShelfVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public List getSelf(CertificateIssueDtlVO _certificateIssueDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// CertificateIssueDtlVO[] certificateIssueDtlVO = null;
		List selfNameLst = new ArrayList();
		try
		{
			tx.begin();
			// RackShelfMstDAO dao=new RackShelfMstDAO(tx);
			// selfNameLst=dao.getAssignedShelf(_certificateIssueDtlVO.getRackId(),
			// userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return selfNameLst;
	}

	public List getAllUnit(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lst = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			lst = mrdEssDAO.getAllUnit(userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lst;
	}

	public String getEmpMaxDaysOnline(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String maxDays = "";

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			maxDays = mrdEssDAO.getEmpMaxDaysOnline(userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return maxDays;
	}

	public List getDeptUnitList(UserVO userVO)
	{
		List lstUnit = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MrdEssentialDAOi essentialDAO = new MrdEssentialDAO(tx);
		try
		{
			tx.begin();
			lstUnit = essentialDAO.getDeptUnitList(userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstUnit;
	}

	public List getWardOnBasisOfUnitCode(String unitCode, UserVO userVO)
	{
		List lstWard = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MrdEssentialDAOi essentialDAO = new MrdEssentialDAO(tx);
		try
		{
			tx.begin();
			lstWard = essentialDAO.getWardOnBasisOfUnitCode(unitCode, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstWard;
	}

	public List getPhysicianType(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lst = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			lst = mrdEssDAO.getPhysicianType(userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lst;
	}

	public PatientVO getNotUsedCrNo(String crNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO patVO = null;
		String countMain = "";
		String count = "";

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			CrNoMergeDAOi crNoMergeDAO = new CrNoMergeDAO(tx);
			count = crNoMergeDAO.countMergedCrNo(crNo, userVO);
			countMain = crNoMergeDAO.countMainCrNo(crNo, userVO);
			if (countMain.equals("0"))
			{
				if (count.equals("0"))
					patVO = mrdEssDAO.getPatDtlByCrNo(crNo, userVO);
				else
					throw new HisApplicationExecutionException("CR Number is Already Merged");
			} else
			{
				throw new HisApplicationExecutionException("CR Number is Main CR Number. You Cannot Merged This CR Number.");
			}
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return patVO;
	}

	public PatientVO[] searchPatient(HashMap searchMap, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO[] patVO = null;
		try
		{
			tx.begin();
			// PatientDAO patientDao = new PatientDAO(tx);
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			if (MrdConfig.MRD_ONLINE_MERGE_CHECK_MAX_SEARCH_LIMIT.equals(MrdConfig.MRD_ONLINE_MERGE_CHECK_MAX_SEARCH_LIMIT_YES))
			{
				int patSerachCount = mrdEssDAO.countSearchPatient(searchMap, userVO);
				if (patSerachCount == 0)
					throw new HisRecordNotFoundException("No Patient Found");
				else if (patSerachCount > MrdConfig.MRD_ONLINE_MERGE_MAX_SEARCH_COUNT)
					throw new HisRecordNotFoundException("Too Many Patient Found. Please Give More Search Criteria");
			}
			patVO = mrdEssDAO.searchPatient(searchMap, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally
		{
			tx.close();
		}
		return patVO;
	}

	public Map getEssentialForOnlineCRNoMerge(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map map = new HashMap();
		List lstGender = null;
		List lstCityLoc = null;

		try
		{
			tx.begin();
			registration.dao.EssentialDAOi regEssDAO = new registration.dao.EssentialDAO(tx);
			// EssentialDAOi regEssDAO=new EssentialDAO(tx);
			lstGender = regEssDAO.getGender(userVO);
			map.put(MrdConfig.ESSENTIAL_GENDER_LIST, lstGender);

			lstCityLoc = regEssDAO.getLocation(userVO);
			map.put(MrdConfig.ESSENTIAL_CITY_LOCATION_LIST, lstCityLoc);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally
		{
			tx.close();
		}
		return map;
	}

	/*
	 * *****************************Case sheet Acceptance
	 * *****************************************
	 */

	public Map getEssentialForCaseSheetAcceptence(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		List<CaseSheetDispatchVO> caseSheetDispatchVOList = null;
		// RackShelfMstVO[] rackShelfVO=null;
		// RackMstVO[] rackMstVO=null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			caseSheetDispatchVOList = mrdEssDAO.getAllDispatchedCaseSheet(userVO);
			essentialMap.put(MrdConfig.CASE_SHEET_DISPATCH_VO_LIST, caseSheetDispatchVOList);

			// Getting The List of All Rack
			// rackMstVO=mrdEssDAO.getAllRack(userVO);
			// essentialMap.put(MrdConfig.ALL_RACK_LIST_VO, rackMstVO);

			// Getting The List Of All Shelf
			// rackShelfVO=mrdEssDAO.getAllRackshelf(userVO);
			// essentialMap.put(MrdConfig.ALL_RACK_SHELF_LIST_VO, rackShelfVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public Map getAllEnclosureChecklistsByRecordId(String recordId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<RecordTypeWiseEnclosureMstVO> enclosureDtlVOList = null;
		List<RecordTypeCheckListMstVO> checklistDtlVOList = null;
		Map essentialMap = new HashMap();
		RackShelfMstVO[] rackShelfVO = null;
		RackMstVO[] rackMstVO = null;
		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			// Getting The List of All Rack
			rackMstVO = mrdEssDAO.getAllRack(userVO);
			essentialMap.put(MrdConfig.ALL_RACK_LIST_VO, rackMstVO);

			// Getting The List Of All Shelf
			rackShelfVO = mrdEssDAO.getAllRackshelf(userVO);
			essentialMap.put(MrdConfig.ALL_RACK_SHELF_LIST_VO, rackShelfVO);

			enclosureDtlVOList = mrdEssDAO.getEnclosureByRecordId(recordId, userVO);
			essentialMap.put(MrdConfig.CASE_SHEET_ENCLOSURE_LIST, enclosureDtlVOList);
			checklistDtlVOList = mrdEssDAO.getChecklistByRecordId(recordId, userVO);
			essentialMap.put(MrdConfig.CASE_SHEET_CHECKLIST, checklistDtlVOList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();

			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public void saveCaseSheetAcceptence(CaseSheetDispatchVO caseSheetDispatchVO, String isAccept, RecordTypeWiseEnclosureMstVO[] recordTypeEnclosureVOArray, MrdRecordDtlVO mrdRecordDtlVO,
			RecordTypeCheckListMstVO[] recChecklistDetailVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		CaseSheetDtlVO caseSheetDtlVO = new CaseSheetDtlVO();
		try
		{
			tx.begin();
			RecordTypeWiseEnclosureDAOi enclosureDao = new RecordTypeWiseEnclosureDAO(tx);
			MRDRecordDtlDAOi mrdRecordDtlDAO = new MRDRecordDtlDAO(tx);
			CaseSheetDispatchDAOi caseSheetDispatchDao = new CaseSheetDispatchDAO(tx);
			CaseSheetDtlDAOi caseSheetDtlDao = new CaseSheetDtlDAO(tx);
			RecordTypeWiseChecklistDAOi checkListDao = new RecordTypeWiseChecklistDAO(tx);

			HelperMethods.populate(caseSheetDtlVO, caseSheetDispatchVO);
			caseSheetDtlVO.setPatAdmNo(caseSheetDispatchVO.getRecordId());
			caseSheetDtlVO.setCaseSheetId(caseSheetDispatchVO.getCaseSheetId());
			if (isAccept.equals(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN))
			{
				caseSheetDispatchDao.update(caseSheetDispatchVO, userVO);
				caseSheetDtlVO.setCaseSheetStatus(MrdConfig.CASE_SHEET_IN_WARD);
				caseSheetDtlDao.update(caseSheetDtlVO, userVO);
			} else
			{
				caseSheetDispatchDao.update(caseSheetDispatchVO, userVO);
				caseSheetDtlVO.setCaseSheetStatus(MrdConfig.CASE_SHEET_INMRD);
				caseSheetDtlDao.update(caseSheetDtlVO, userVO);
				mrdRecordDtlDAO.create(mrdRecordDtlVO, userVO);
				for (int i = 0; i < recordTypeEnclosureVOArray.length; i++)
				{
					enclosureDao.update(recordTypeEnclosureVOArray[i], userVO);
				}
				for (int i = 0; i < recChecklistDetailVO.length; i++)
				{
					checkListDao.saveCaseSheetDispatchCheckListDetails(recChecklistDetailVO[i], userVO);
				}
			}

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();

			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
	}

	/*
	 * **************************************Patient Discharge Printing
	 * ************************************
	 */

	public List<DischargePrintingVO> getDisPatientListForPrinting(String unitCode, String wardCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<DischargePrintingVO> dischargePatList = null;
		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			dischargePatList = mrdEssDAO.getDisPatientListForPrinting(unitCode, wardCode, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();

			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return dischargePatList;
	}

	public List<DischargePrintingVO> getDisPatientListForPrintingByCrNo(String patCrnNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<DischargePrintingVO> dischargePatList = null;
		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			dischargePatList = mrdEssDAO.getDisPatientListForPrintingByCrNo(patCrnNo, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();

			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return dischargePatList;
	}

	public void saveDischargePrinting(DischargePrintingVO dischargePrintingVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			DischargePrintDAOi dischargePrintDao = new DischargePrintDAO(tx);
			dischargePrintDao.save(dischargePrintingVO, userVO);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
	}

	public ANCNeonatalDetailVO[] getListOfBirth(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ANCNeonatalDetailVO[] birthListVO = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			birthListVO = mrdEssDAO.getListOfBirth(userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return birthListVO;
	}

	public List getRelationList(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstRelation = new ArrayList();

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			lstRelation = mrdEssDAO.getRelationList(userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();

			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstRelation;
	}

	public BirthDeathUploadDtlVO getBirthDeathUploadDtl(String recordType, String crNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		BirthDeathUploadDtlVO birthUploadVO = new BirthDeathUploadDtlVO();

		try
		{
			tx.begin();
			BirthDeathUploadDAOi birthDAO = new BirthDeathUploadDAO(tx);
			birthUploadVO = birthDAO.getBirthDeathUploadDtl(recordType, crNo, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return birthUploadVO;
	}

	public Map getMotherNChildDetail(String motherCrNo, String childCrNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		PatientVO motherPatVO = new PatientVO();
		PatientVO childPatVO = new PatientVO();

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);

			motherPatVO = mrdEssDAO.getMotherDetail(motherCrNo, userVO);
			essentialMap.put(MrdConfig.MOTHER_DETAIL_VO, motherPatVO);

			childPatVO = mrdEssDAO.getChildDetail(childCrNo, userVO);
			essentialMap.put(MrdConfig.CHILD_DETAIL_VO, childPatVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public PatientVO getBirthSlipDetail(String crNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO birthVO = new PatientVO();

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			birthVO = mrdEssDAO.getBirthSlipDetail(crNo, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return birthVO;
	}

	public PatientVO[] getListOfDeath(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO[] deathListVO = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			deathListVO = mrdEssDAO.getListOfDeath(userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return deathListVO;
	}

	public ANCNeonatalDetailVO[] searchForBirthRegUpload(PatientVO searchFindVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ANCNeonatalDetailVO[] searchListVO = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			searchListVO = mrdEssDAO.searchForBirthRegUpload(searchFindVO, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return searchListVO;
	}

	public Map getEssentialForSummonDtl(String cidNoFlag, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssential = new HashMap();
		List genderList = null;
		List summonTypeList = null;
		List ageTypeList = null;
		List CIDNOInfoList = null;
		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);
			EssentialDAOi regDaoObj = new EssentialDAO(tx);

			ageTypeList = regDaoObj.getAgeType();
			mpEssential.put(MrdConfig.ALL_AGE_TYPE_LIST, ageTypeList);

			genderList = daoobj.getGender(userVO);
			mpEssential.put(MrdConfig.ALL_GENDER_LIST, genderList);

			summonTypeList = daoobj.getSummonTypeList(userVO);
			mpEssential.put(MrdConfig.ALL_SUMMON_TYPE_LIST, summonTypeList);

			if (cidNoFlag.equals(MrdConfig.ENABLE))
			{
				CIDNOInfoList = daoobj.getCIDNoInfoList(userVO);
				mpEssential.put(MrdConfig.ALL_CID_NO_INFO_LIST, CIDNOInfoList);
			}

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mpEssential;
	}

	public Map getEssenForSummonAssignDtl(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssential = new HashMap();
		List summonTypeList = null;
		List summonReceivedList = null;
		List genderList = null;
		List ageTypeList = null;
		List summonAssignedList = null;
		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);
			EssentialDAOi regDaoObj = new EssentialDAO(tx);

			ageTypeList = regDaoObj.getAgeType();
			mpEssential.put(MrdConfig.ALL_AGE_TYPE_LIST, ageTypeList);

			summonTypeList = daoobj.getSummonTypeList(userVO);
			mpEssential.put(MrdConfig.ALL_SUMMON_TYPE_LIST, summonTypeList);

			genderList = daoobj.getGender(userVO);
			mpEssential.put(MrdConfig.ALL_GENDER_LIST, genderList);

			summonReceivedList = daoobj.getSummonReceivedList(userVO);
			mpEssential.put(MrdConfig.ALL_SUMMON_RECEIVED_LIST, summonReceivedList);

			summonAssignedList = daoobj.getSummonAssignChangeList(userVO);
			mpEssential.put(MrdConfig.ALL_SUMMON_ASSIGNED_LIST, summonAssignedList);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mpEssential;
	}

	public List searchEmpDetail(String _fName, String _mName, String _lName, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List employeeList = null;

		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);

			employeeList = daoobj.getSearchUsersByEmpName(_fName, _mName, _lName, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return employeeList;
	}

	public Map getEssenForPostSummonDtl(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssential = new HashMap();
		List postSummonDtlList = null;
		List summonTypeList = null;
		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);

			summonTypeList = daoobj.getSummonTypeList(userVO);
			mpEssential.put(MrdConfig.ALL_SUMMON_TYPE_LIST, summonTypeList);

			postSummonDtlList = daoobj.getPostSummonList(userVO);
			mpEssential.put(MrdConfig.POST_SUMMON_LIST, postSummonDtlList);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mpEssential;
	}

	public Map getEssenForSummonAssignChange(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssential = new HashMap();
		List summonAssignChangeList = null;
		List summonTypeList = null;
		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);

			summonTypeList = daoobj.getSummonTypeList(userVO);
			mpEssential.put(MrdConfig.ALL_SUMMON_TYPE_LIST, summonTypeList);

			summonAssignChangeList = daoobj.getSummonAssignChangeList(userVO);
			mpEssential.put(MrdConfig.SUMMON_ASSIGN_CHANGE_LIST, summonAssignChangeList);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mpEssential;
	}

	public Map getEssentialForSummonTracking(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssential = new HashMap();
		List summonTypeList = null;
		List allEmployeeList = null;

		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);

			summonTypeList = daoobj.getSummonTypeList(userVO);
			mpEssential.put(MrdConfig.ALL_SUMMON_TYPE_LIST, summonTypeList);

			allEmployeeList = daoobj.getAllEmployeeList(userVO);
			mpEssential.put(MrdConfig.ALL_EMPLOYEE_LIST, allEmployeeList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mpEssential;
	}

	public Map searchSummonDetail(String SearchCriteria, String fromDate, String toDate, String empId, String summonTypeId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssential = new HashMap();
		List allSummonDtlVOList = null;
		List allAttendedSummonList = null;
		List allNotAttendedSummonList = null;
		List allPostDtlList = null;
		List allNextHearingList = null;
		List partcularEmployeeDtlList = null;
		List partcularSummonTypeDtlList = null;

		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);

			if (SearchCriteria.equals(MrdConfig.ALL_RECORD))
			{
				allSummonDtlVOList = daoobj.getAllSummonDtlVOList(fromDate, toDate, userVO);
				mpEssential.put(MrdConfig.ALL_SEARCH_SUMMON_DTL_VO_LIST, allSummonDtlVOList);
			}

			if (SearchCriteria.equals(MrdConfig.ATTENDED_SUMMON))
			{
				allAttendedSummonList = daoobj.getAllAttendedSummonDtlVOList(fromDate, toDate, userVO);
				mpEssential.put(MrdConfig.ALL_SEARCH_SUMMON_DTL_VO_LIST, allAttendedSummonList);
			}

			if (SearchCriteria.equals(MrdConfig.NOT_ATTENDED))
			{
				allNotAttendedSummonList = daoobj.getAllNotAttendedSummonDtlVOList(fromDate, toDate, userVO);
				mpEssential.put(MrdConfig.ALL_SEARCH_SUMMON_DTL_VO_LIST, allNotAttendedSummonList);
			}
			if (SearchCriteria.equals(MrdConfig.POST_DETAIL))
			{
				allPostDtlList = daoobj.getAllPostDtlSummonDtlVOList(fromDate, toDate, userVO);
				mpEssential.put(MrdConfig.ALL_SEARCH_SUMMON_DTL_VO_LIST, allPostDtlList);
			}
			if (SearchCriteria.equals(MrdConfig.NEXT_HEARING))
			{
				allNextHearingList = daoobj.getNextHearingSummonDtlVOList(fromDate, toDate, userVO);
				mpEssential.put(MrdConfig.ALL_SEARCH_SUMMON_DTL_VO_LIST, allNextHearingList);
			}
			if (SearchCriteria.equals(MrdConfig.PARTICULAR_EMPLOYEE))
			{
				partcularEmployeeDtlList = daoobj.getParticularEmpSummonDtlVOList(fromDate, toDate, empId, userVO);
				mpEssential.put(MrdConfig.ALL_SEARCH_SUMMON_DTL_VO_LIST, partcularEmployeeDtlList);
			}

			if (SearchCriteria.equals(MrdConfig.SUMMON_TYPE))
			{
				partcularSummonTypeDtlList = daoobj.getParticularSummonTypeSummonDtlVOList(fromDate, toDate, summonTypeId, userVO);
				mpEssential.put(MrdConfig.ALL_SEARCH_SUMMON_DTL_VO_LIST, partcularSummonTypeDtlList);
			}

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mpEssential;
	}

	public Map searchPatientDtl(String searchType, String _str, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssential = new HashMap();
		List patListByMLCNo = null;
		List patListByPostMortemId = null;
		List patMLCDtlListByName = null;
		List patPostMortemDtlByName = null;
		List patAdmissionDtlByName = null;
		List patAdmissionDtlByCrNo = null;
		List patAdmissionDtlByAdmNo = null;
		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);

			if (searchType.equals(MrdConfig.PAT_SEARCH_BY_NAME))
			{
				String _firstName = _str.split("#")[0];
				String _middleName = _str.split("#")[1];
				String _lastName = _str.split("#")[2];

				if (_firstName.equals("@"))
				{
					_firstName = "";
				}
				if (_middleName.equals("@"))
				{
					_middleName = "";
				}
				if (_lastName.equals("@"))
				{
					_lastName = "";
				}

				patMLCDtlListByName = daoobj.getPatMLCDtlByName(_firstName.toUpperCase(), _middleName.toUpperCase(), _lastName.toUpperCase(), userVO);

				if (patMLCDtlListByName != null)
				{
					for (int i = 0; i < patMLCDtlListByName.size(); i++)
					{
						SummonDetailVO summonDtlVO = (SummonDetailVO) patMLCDtlListByName.get(i);
						String add1 = "";
						AddressVO patAddressVO = daoobj.getPatAddress(summonDtlVO.getPatCrNo(), userVO);
						if (patAddressVO != null)
						{
							String hNo = patAddressVO.getPatAddHNo();
							String cityLoc = patAddressVO.getPatAddCityLoc();
							String city = patAddressVO.getPatAddCity();
							String district = patAddressVO.getPatAddDistrict();
							String state = patAddressVO.getPatAddState();
							String country = patAddressVO.getPatAddCountry();

							if (hNo != null)
								add1 = hNo + ",";
							if (cityLoc != null)
								add1 += cityLoc + ",";
							if (city != null)
								add1 += city + ",";
							if (district != null)
								add1 += district;
							if (state != null)
								add1 += state + ",";
							if (country != null)
								add1 += country;
						}
						summonDtlVO.setPatAddress(add1);
					}
				}

				mpEssential.put(MrdConfig.PAT_DETAIL_lIST_BY_MLCNO, patMLCDtlListByName);

				patAdmissionDtlByName = daoobj.getPatAdmissionDtlByName(_firstName.toUpperCase(), _middleName.toUpperCase(), _lastName.toUpperCase(), userVO);

				if (patAdmissionDtlByName != null)
				{
					for (int i = 0; i < patAdmissionDtlByName.size(); i++)
					{
						SummonDetailVO summonDtlVO = (SummonDetailVO) patAdmissionDtlByName.get(i);
						String add1 = "";
						AddressVO patAddressVO = daoobj.getPatAddress(summonDtlVO.getPatCrNo(), userVO);
						if (patAddressVO != null)
						{
							String hNo = patAddressVO.getPatAddHNo();
							String cityLoc = patAddressVO.getPatAddCityLoc();
							String city = patAddressVO.getPatAddCity();
							String district = patAddressVO.getPatAddDistrict();
							String state = patAddressVO.getPatAddState();
							String country = patAddressVO.getPatAddCountry();

							if (hNo != null)
								add1 = hNo + ",";
							if (cityLoc != null)
								add1 += cityLoc + ",";
							if (city != null)
								add1 += city + ",";
							if (district != null)
								add1 += district;
							if (state != null)
								add1 += state + ",";
							if (country != null)
								add1 += country;
						}
						summonDtlVO.setPatAddress(add1);
					}
				}

				mpEssential.put(MrdConfig.PAT_DETAIL_LIST_BY_ADMISSION_NO, patAdmissionDtlByName);

				patPostMortemDtlByName = daoobj.getPatPostMortemDtlByName(_firstName.toUpperCase(), _middleName.toUpperCase(), _lastName.toUpperCase(), userVO);
				mpEssential.put(MrdConfig.PAT_DETAIL_LIST_BY_POSTMORTEMID, patPostMortemDtlByName);

			}

			if (searchType.equals(MrdConfig.PAT_SEARCH_BY_MLCNO))
			{
				patListByMLCNo = daoobj.getPatDetailByMLCNo(_str, userVO);
				if (patListByMLCNo != null)
				{
					for (int i = 0; i < patListByMLCNo.size(); i++)
					{
						SummonDetailVO summonDtlVO = (SummonDetailVO) patListByMLCNo.get(i);
						String add1 = "";
						AddressVO patAddressVO = daoobj.getPatAddress(summonDtlVO.getPatCrNo(), userVO);
						if (patAddressVO != null)
						{
							String hNo = patAddressVO.getPatAddHNo();
							String cityLoc = patAddressVO.getPatAddCityLoc();
							String city = patAddressVO.getPatAddCity();
							String district = patAddressVO.getPatAddDistrict();
							String state = patAddressVO.getPatAddState();
							String country = patAddressVO.getPatAddCountry();

							if (hNo != null)
								add1 = hNo + ",";
							if (cityLoc != null)
								add1 += cityLoc + ",";
							if (city != null)
								add1 += city + ",";
							if (district != null)
								add1 += district;
							if (state != null)
								add1 += state + ",";
							if (country != null)
								add1 += country;
						}
						summonDtlVO.setPatAddress(add1);
					}
				}

				mpEssential.put(MrdConfig.PAT_DETAIL_lIST_BY_MLCNO, patListByMLCNo);
			}

			if (searchType.equals(MrdConfig.PAT_SEARCH_BY_POSTMARTEM_NO))
			{
				patListByPostMortemId = daoobj.getPatDetailByPostMortemId(_str, userVO);
				mpEssential.put(MrdConfig.PAT_DETAIL_LIST_BY_POSTMORTEMID, patListByPostMortemId);
			}

			if (searchType.equals(MrdConfig.PAT_SERACH_BY_CRNO))
			{
				patListByPostMortemId = daoobj.getPatPostMortemDtlByCRNo(_str, userVO);
				mpEssential.put(MrdConfig.PAT_DETAIL_LIST_BY_POSTMORTEMID, patListByPostMortemId);

				patListByMLCNo = daoobj.getPatMLCDtlByCRNo(_str, userVO);

				if (patListByMLCNo != null)
				{
					for (int i = 0; i < patListByMLCNo.size(); i++)
					{
						SummonDetailVO summonDtlVO = (SummonDetailVO) patListByMLCNo.get(i);
						String add1 = "";
						AddressVO patAddressVO = daoobj.getPatAddress(summonDtlVO.getPatCrNo(), userVO);
						if (patAddressVO != null)
						{
							String hNo = patAddressVO.getPatAddHNo();
							String cityLoc = patAddressVO.getPatAddCityLoc();
							String city = patAddressVO.getPatAddCity();
							String district = patAddressVO.getPatAddDistrict();
							String state = patAddressVO.getPatAddState();
							String country = patAddressVO.getPatAddCountry();

							if (hNo != null)
								add1 = hNo + ",";
							if (cityLoc != null)
								add1 += cityLoc + ",";
							if (city != null)
								add1 += city + ",";
							if (district != null)
								add1 += district;
							if (state != null)
								add1 += state + ",";
							if (country != null)
								add1 += country;
						}
						summonDtlVO.setPatAddress(add1);
					}
				}

				mpEssential.put(MrdConfig.PAT_DETAIL_lIST_BY_MLCNO, patListByMLCNo);

				patAdmissionDtlByCrNo = daoobj.getPatAdmissionDtlByCRNo(_str, userVO);

				if (patAdmissionDtlByCrNo != null)
				{
					for (int i = 0; i < patAdmissionDtlByCrNo.size(); i++)
					{
						SummonDetailVO summonDtlVO = (SummonDetailVO) patAdmissionDtlByCrNo.get(i);
						String add1 = "";
						AddressVO patAddressVO = daoobj.getPatAddress(summonDtlVO.getPatCrNo(), userVO);
						if (patAddressVO != null)
						{
							String hNo = patAddressVO.getPatAddHNo();
							String cityLoc = patAddressVO.getPatAddCityLoc();
							String city = patAddressVO.getPatAddCity();
							String district = patAddressVO.getPatAddDistrict();
							String state = patAddressVO.getPatAddState();
							String country = patAddressVO.getPatAddCountry();

							if (hNo != null)
								add1 = hNo + ",";
							if (cityLoc != null)
								add1 += cityLoc + ",";
							if (city != null)
								add1 += city + ",";
							if (district != null)
								add1 += district;
							if (state != null)
								add1 += state + ",";
							if (country != null)
								add1 += country;
						}
						summonDtlVO.setPatAddress(add1);
					}
				}

				mpEssential.put(MrdConfig.PAT_DETAIL_LIST_BY_ADMISSION_NO, patAdmissionDtlByCrNo);
			}

			if (searchType.equals(MrdConfig.PAT_SERACH_BY_ADMISSION_NO))
			{
				patAdmissionDtlByAdmNo = daoobj.getPatAdmissionDtlByAdmNo(_str, userVO);

				if (patAdmissionDtlByAdmNo != null)
				{
					for (int i = 0; i < patAdmissionDtlByAdmNo.size(); i++)
					{
						SummonDetailVO summonDtlVO = (SummonDetailVO) patAdmissionDtlByAdmNo.get(i);
						String add1 = "";
						AddressVO patAddressVO = daoobj.getPatAddress(summonDtlVO.getPatCrNo(), userVO);
						if (patAddressVO != null)
						{
							String hNo = patAddressVO.getPatAddHNo();
							String cityLoc = patAddressVO.getPatAddCityLoc();
							String city = patAddressVO.getPatAddCity();
							String district = patAddressVO.getPatAddDistrict();
							String state = patAddressVO.getPatAddState();
							String country = patAddressVO.getPatAddCountry();

							if (hNo != null)
								add1 = hNo + ",";
							if (cityLoc != null)
								add1 += cityLoc + ",";
							if (city != null)
								add1 += city + ",";
							if (district != null)
								add1 += district;
							if (state != null)
								add1 += state + ",";
							if (country != null)
								add1 += country;
						}
						summonDtlVO.setPatAddress(add1);
					}
				}

				mpEssential.put(MrdConfig.PAT_DETAIL_lIST_BY_ADMISSION_NO, patAdmissionDtlByAdmNo);

				/*
				 * patListByMLCNo=daoobj.getPatMLCDtlByAdmNo(_str, userVO);
				 * 
				 * if(patListByMLCNo!=null) { for(int
				 * i=0;i<patListByMLCNo.size();i++) { SummonDetailVO
				 * summonDtlVO=(SummonDetailVO)patListByMLCNo.get(i); String
				 * add1=""; AddressVO
				 * patAddressVO=daoobj.getPatAddress(summonDtlVO
				 * .getPatCrNo(),userVO); if(patAddressVO!=null) { String
				 * hNo=patAddressVO.getPatAddHNo(); String
				 * cityLoc=patAddressVO.getPatAddCityLoc(); String
				 * city=patAddressVO.getPatAddCity(); String
				 * district=patAddressVO.getPatAddDistrict(); String
				 * state=patAddressVO.getPatAddState(); String
				 * country=patAddressVO.getPatAddCountry();
				 * 
				 * 
				 * if(hNo!=null) add1=hNo+","; if(cityLoc!=null)
				 * add1+=cityLoc+","; if(city!=null) add1+=city+",";
				 * if(district!=null) add1+=district; if(state!=null)
				 * add1+=state+","; if(country!=null) add1+=country; }
				 * summonDtlVO.setPatAddress(add1); } }
				 * 
				 * mpEssential.put(MrdConfig.PAT_DETAIL_lIST_BY_MLCNO,
				 * patListByMLCNo);
				 * 
				 * 
				 * 
				 * patListByPostMortemId=daoobj.getPatPostMortemDtlByAdmNo(_str,
				 * userVO);
				 * mpEssential.put(MrdConfig.PAT_DETAIL_LIST_BY_POSTMORTEMID,
				 * patListByPostMortemId);
				 */
			}

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mpEssential;
	}

	public ANCNeonatalDetailVO[] searchForBirthRegUploadByMother(PatientVO searchFindVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ANCNeonatalDetailVO[] searchListVO = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			searchListVO = mrdEssDAO.searchForBirthRegUploadByMother(searchFindVO, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return searchListVO;
	}

	public Map getEssenForInsuranceClaimReceive(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssential = new HashMap();
		List CIDNOInfoList = null;
		List allInsuranceCompanyList = null;
		try
		{
			tx.begin();
			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);

			CIDNOInfoList = daoobj.getCIDNoInfoListForInsurance(userVO);
			mpEssential.put(MrdConfig.ALL_CID_NO_INFO_LIST_FOR_INSURANCE, CIDNOInfoList);

			allInsuranceCompanyList = daoobj.getAllInsuranceCompanyList(userVO);
			mpEssential.put(MrdConfig.ALL_INSURANCE_COMPANY_LIST, allInsuranceCompanyList);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mpEssential;
	}

	public List getPatInfoList(String patCrNo, String patAdmNo, String _firstName, String _middleName, String _lastName, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List patInfoList = null;

		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);
			patInfoList = daoobj.getPatInfoList(patCrNo, patAdmNo, _firstName, _middleName, _lastName, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return patInfoList;
	}

	public List getPatDeathUploadList(String patCrNo, String deathDate, String _firstName, String _middleName, String _lastName, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List patInfoList = null;

		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);
			patInfoList = daoobj.getPatDeathUploadList(patCrNo, deathDate, _firstName, _middleName, _lastName, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return patInfoList;
	}

	public RecordTypeCheckListMstVO[] getCheckListForMedNFitCertificate(String recordType, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordTypeCheckListMstVO[] recordTypeCheckListVO = null;

		try
		{
			tx.begin();
			String checkListMode = MrdConfig.CHECKLIST_MODE_HANDOVER_LEVEL;
			RecordTypeCheckListDAOi mrdEssDAO = new RecordTypeCheckListDAO(tx);
			recordTypeCheckListVO = mrdEssDAO.getCheckListForMedNFitCertificate(checkListMode, recordType, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return recordTypeCheckListVO;
	}

	public List getRecordType(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List recordTypeList = null;

		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);
			recordTypeList = daoobj.getRecordType(userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return recordTypeList;
	}

	public List getRecordTypeBasedOnMrd(String mrdCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List recordTypeList = null;

		try
		{
			tx.begin();

			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);
			recordTypeList = daoobj.getRecordTypeBasedOnMrd(mrdCode, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return recordTypeList;
	}

	public RecordDispatchDtlVO[] getRecordListToAcceptByRecordType(String recordType, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordDispatchDtlVO[] arrRecordDispatchVO = null;

		try
		{
			tx.begin();
			RecordDispatchDtlDAOi dispatchDAO = new RecordDispatchDtlDAO(tx);
			arrRecordDispatchVO = dispatchDAO.getRecordListToAcceptByRecordType(recordType, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return arrRecordDispatchVO;
	}

	public Map getEssentialForSummonInbox(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssential = new HashMap();
		List summonAssignList = null;
		List summonTypeList = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);

			summonAssignList = daoobj.getSummonAssignDtlList(userVO);
			mpEssential.put(MrdConfig.ALL_ASSIGNED_SUMMON_DETAIL_LIST, summonAssignList);

			summonTypeList = daoobj.getSummonTypeList(userVO);
			mpEssential.put(MrdConfig.ALL_SUMMON_TYPE_LIST, summonTypeList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mpEssential;
	}

	public Map getEssentialForAcceptRecordInMrd(String recordType, String mrdCode, String searchDate, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		// List lstRack=new ArrayList();
		List lstPutBy = new ArrayList();
		RecordDispatchDtlVO[] arrRecordDispatchVO = null;
		RackMstVO[] rackMstVO = null;
		String deptCode = "";

		try
		{
			tx.begin();
			RecordDispatchDtlDAOi dispatchDAO = new RecordDispatchDtlDAO(tx);
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);

			if (recordType.equals(MrdConfig.RECORD_TYPE_OPD_FILE))
			{
				System.out.println("if");
				arrRecordDispatchVO = dispatchDAO.getRecordListToAcceptForOPDFile(recordType, searchDate, userVO);
			} else
			{
				System.out.println("else");
				arrRecordDispatchVO = dispatchDAO.getRecordListToAcceptByRecordType(recordType, userVO);
			}
			essMap.put(MrdConfig.ARR_RECORD_LIST_TO_ACCEPT_IN_MRD_BY_RECORD_TYPE_VO, arrRecordDispatchVO);

			rackMstVO = mrdEssDAO.getRackBasedOnMrd(recordType, mrdCode, userVO);
			essMap.put(MrdConfig.LIST_OF_RACK_BASED_ON_MRD, rackMstVO);

			lstPutBy = mrdEssDAO.getRecordPutByList(deptCode, userVO);
			essMap.put(MrdConfig.LIST_RECORD_PUT_BY_IN_MRD, lstPutBy);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	public RackMstVO[] getRackBasedOnMrd(String recordType, String mrdCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RackMstVO[] rackMstVO = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			rackMstVO = mrdEssDAO.getRackBasedOnMrd(recordType, mrdCode, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return rackMstVO;
	}

	public List getShelfBasedOnRack(String recordType, String rackId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstShelf = new ArrayList();

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			if (!(recordType.equals("-1")))
			{
				lstShelf = mrdEssDAO.getShelfBasedOnRack(recordType, rackId, userVO);
			} else
			{
				lstShelf = mrdEssDAO.getShelfBasedOnRackForCrAdmNo(recordType, rackId, userVO);// added
																								// by
																								// Swati
																								// sagar
																								// on
																								// date:14-may-2019
			}
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstShelf;
	}

	// ADDED BY SWATI SAGAR
	// DATE:14-MAY-2019
	public List getShelfBasedOnRackForCrAdmNo(String recordType, String rackId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstShelf = new ArrayList();

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			lstShelf = mrdEssDAO.getShelfBasedOnRackForCrAdmNo(recordType, rackId, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstShelf;
	}

	public List getMrdList(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstMrd = new ArrayList();

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			lstMrd = mrdEssDAO.getMrdListUserBased(userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstMrd;
	}

	public Map getEssentialForRecordArchived(String recordType, String mrdCode, UserVO userVO, String admNo)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		RackMstVO[] rackMstVO = null;
		MrdRecordDtlVO[] mrdRecordDtlVO = null;
		MrdRecordDtlVO[] admNoDtlVO = null;
		List lstPutBy = new ArrayList();
		String deptCode = "";

		try
		{
			tx.begin();
			MRDRecordDtlDAOi mrdRecordDAO = new MRDRecordDtlDAO(tx);
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);

			mrdRecordDtlVO = mrdRecordDAO.getRecordListToArchivedByRecordType(recordType, mrdCode, userVO);
			essMap.put(MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_RECORD_TYPE_VO, mrdRecordDtlVO);

			rackMstVO = mrdEssDAO.getRackBasedOnMrd(recordType, mrdCode, userVO);
			essMap.put(MrdConfig.LIST_OF_RACK_BASED_ON_MRD, rackMstVO);

			lstPutBy = mrdEssDAO.getRecordPutByList(deptCode, userVO);
			essMap.put(MrdConfig.LIST_RECORD_PUT_BY_IN_MRD, lstPutBy);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	// added by swati sagar
	// date:14-may-2019

	public Map getRackDtlForCrAdmNo(String recordType, String mrdCode, UserVO userVO, String admNo)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		RackMstVO[] rackMstVO = null;
		MrdRecordDtlVO[] mrdRecordDtlVO = null;
		MrdRecordDtlVO[] admNoDtlVO = null;
		List lstPutBy = new ArrayList();
		String deptCode = "";

		try
		{
			tx.begin();
			MRDRecordDtlDAOi mrdRecordDAO = new MRDRecordDtlDAO(tx);
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);

			rackMstVO = mrdEssDAO.getRackDtlForCrAdmNo(recordType, mrdCode, userVO);
			essMap.put(MrdConfig.LIST_OF_RACK_BASED_ON_MRD, rackMstVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	// added by swati sagar on date:13-may-2019

	public Map getEssentialForRecordArchived_AdmNo(String recordType, String mrdCode, UserVO userVO, String admNo)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		RackMstVO[] rackMstVO = null;
		MrdRecordDtlVO[] mrdRecordDtlVO = null;
		MrdRecordDtlVO[] admNoDtlVO = null;
		List lstPutBy = new ArrayList();
		String deptCode = "";

		try
		{
			tx.begin();
			MRDRecordDtlDAOi mrdRecordDAO = new MRDRecordDtlDAO(tx);
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);

			admNoDtlVO = mrdRecordDAO.getRecordListToArchivedByAdmNo(userVO, admNo);
			essMap.put(MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_ADM_NO_VO, admNoDtlVO);
			// WebUTIL.setAttributeInSession(request,MrdConfig.MRD_RECORD_DTL_VO
			// , MrdRecordDtlVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	// added by swati sagar on date:13-may-2019

	public Map getEssentialForRecordArchived_CrNo(String recordType, String mrdCode, UserVO userVO, String admNo, String crno)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		RackMstVO[] rackMstVO = null;
		MrdRecordDtlVO[] mrdRecordDtlVO = null;
		MrdRecordDtlVO[] CRNoDtlVO = null;
		List lstPutBy = new ArrayList();
		String deptCode = "";

		try
		{
			tx.begin();
			MRDRecordDtlDAOi mrdRecordDAO = new MRDRecordDtlDAO(tx);
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);

			CRNoDtlVO = mrdRecordDAO.getRecordListToArchivedByCrNo(userVO, admNo, crno);
			essMap.put(MrdConfig.ARR_RECORD_LIST_TO_ARCHIVED_IN_MRD_BY_CR_NO_VO, CRNoDtlVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	public Map getEssentialForLostFoundDetail(String recordType, String mrdCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		RackMstVO[] rackMstVO = null;
		RecordLostFoundDtlVO[] lostRecordDtlVO = null;
		List lstPutBy = new ArrayList();
		String deptCode = "";

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			RecordLostFoundDtlDAOi lostFoundDAO = new RecordLostFoundDtlDAO(tx);

			lostRecordDtlVO = lostFoundDAO.getLostRecordList(recordType, mrdCode, userVO);
			essMap.put(MrdConfig.ARR_LOST_RECORD_LIST_TO_BE_FOUND_BY_RECORD_TYPE_VO, lostRecordDtlVO);

			rackMstVO = mrdEssDAO.getRackBasedOnMrd(recordType, mrdCode, userVO);
			essMap.put(MrdConfig.LIST_OF_RACK_BASED_ON_MRD, rackMstVO);

			lstPutBy = mrdEssDAO.getRecordPutByList(deptCode, userVO);
			essMap.put(MrdConfig.LIST_RECORD_PUT_BY_IN_MRD, lstPutBy);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	public List<RecordTypeWiseEnclosureMstVO> getEnclosureDetail(String dispatchId, String recordType, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<RecordTypeWiseEnclosureMstVO> lstEnclosureVO = new ArrayList<RecordTypeWiseEnclosureMstVO>();

		try
		{
			tx.begin();
			RecordTypeWiseEnclosureDAOi enclosureSummaryDAO = new RecordTypeWiseEnclosureDAO(tx);
			lstEnclosureVO = enclosureSummaryDAO.getEnclosureDetailByDispatchId(dispatchId, recordType, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstEnclosureVO;
	}

	public Map getCheckListDetail(String dispatchId, String recordType, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		List lstCheckList = new ArrayList();
		RecordCheckListDtlVO[] checkListVO = null;
		RecordTypeCheckListMstVO[] recordTypeCheckListMstVO = null;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			RecordTypeWiseChecklistDAOi checkListDAO = new RecordTypeWiseChecklistDAO(tx);
			RecordTypeCheckListDAOi recordTypecheckListDAO = new RecordTypeCheckListDAO(tx);

			lstCheckList = mrdEssDAO.getCheckListByRecordType(recordType, userVO);
			essMap.put(MrdConfig.LIST_CHECKLIST_BY_RECORD_TYPE, lstCheckList);

			checkListVO = checkListDAO.getCheckListedRecord(dispatchId, recordType, userVO);
			essMap.put(MrdConfig.ARR_CHECKED_CHECKLIST_BY_DISPATCH_ID, checkListVO);

			String checkListMode = MrdConfig.CHECKLIST_MODE_ARCHIVAL_POINT;
			recordTypeCheckListMstVO = recordTypecheckListDAO.getCheckListForMedNFitCertificate(checkListMode, recordType, userVO);
			essMap.put(MrdConfig.ARR_CHECKLIST_BY_RECORD_TYPE_N_MODE_VO, recordTypeCheckListMstVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	public Map getEssentialForCertificateReceived(String recordType, String unitCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		RecordTypeCheckListMstVO[] recordTypeCheckListVO = null;
		List lstEmp = new ArrayList();
		String wardCode = "";
		String processId = MrdConfig.PROCESS_ID_MED_N_FIT_CERTIFICATE;

		try
		{
			tx.begin();
			String checkListMode = MrdConfig.CHECKLIST_MODE_HANDOVER_LEVEL;
			RecordTypeCheckListDAOi mrdEssDAO = new RecordTypeCheckListDAO(tx);
			MrdDAOi mrdDAO = new MrdDAO(tx);

			recordTypeCheckListVO = mrdEssDAO.getCheckListForMedNFitCertificate(checkListMode, recordType, userVO);
			essMap.put(MrdConfig.ARR_CHECKLIST_FOR_MEDICAL_CERTIFICATE_VO, recordTypeCheckListVO);

			lstEmp = mrdDAO.getEmployeeListForHandover(processId, unitCode, wardCode, userVO);
			essMap.put(MrdConfig.RECIEVING_HANDOVER_EMP_LIST_OPTION, lstEmp);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	public Map getEssentialForOnlineReq(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		// RequestPurposeMstVO[] reqPurposeMstVO=null;
		// List lstMrd=new ArrayList();
		List lstRecordType = new ArrayList();
		List<MrdRecordRequestDtlVO> pendingRequestVOList = new ArrayList<MrdRecordRequestDtlVO>();
		MrdRecordRequestDtlVO mrdRecordVO = new MrdRecordRequestDtlVO();
		// String recordType=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET;

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			// RequestPurposeMstDAOi reqPurposeDAO=new RequestPurposeMstDAO(tx);
			MrdRecordRequestDtlDAOi mrdRecordReqDAO = new MrdRecordRequestDtlDAO(tx);

			// lstMrd=mrdEssDAO.getMrdListBasedOnRecordType(recordType,userVO);
			// essMap.put(MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE, lstMrd);

			/*
			 * reqPurposeMstVO=reqPurposeDAO.getRequestPurpose(recordType,
			 * userVO); essMap.put(MrdConfig.ARR_REQUEST_PURPOSE_MST_VO,
			 * reqPurposeMstVO);
			 */

			lstRecordType = mrdEssDAO.getRecordTypeBasedOnMovableType(userVO);
			essMap.put(MrdConfig.RECORD_TYPE, lstRecordType);

			pendingRequestVOList = mrdRecordReqDAO.getPendingRequestListing(userVO);
			essMap.put(MrdConfig.ARR_ONLINE_PENDING_REQUEST_LIST, pendingRequestVOList);

			mrdRecordVO = mrdRecordReqDAO.getLoginUserRequestByDetails(userVO);
			mrdRecordVO.setLoginRequestByName(userVO.getUsrName().toUpperCase());
			mrdRecordVO.setLoginRequestByDesig(userVO.getDesignation());
			essMap.put(MrdConfig.LOGIN_USER_REQUEST_BY_DETAILS, mrdRecordVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	// added by swati sagar on date:20-02-2019

	public Map getEssentialForIcdIndexing(UserVO userVO, PatientDetailVO patdtlVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		// RequestPurposeMstVO[] reqPurposeMstVO=null;
		// List lstMrd=new ArrayList();
		List lstRecordType = new ArrayList();
		List patDiagDtl = new ArrayList();
		List patAddDtl = new ArrayList();
		List<MrdRecordRequestDtlVO> pendingRequestVOList = new ArrayList<MrdRecordRequestDtlVO>();

		// Added by Vasu on 06.March.2019
		List<MrdIcdDtlVO> lstPrevIcdRecords = null;

		MrdRecordRequestDtlVO mrdRecordVO = new MrdRecordRequestDtlVO();
		// String recordType=MrdConfig.RECORD_TYPE_GENERAL_CASESHEET;
		HisDAO hisDAO = new HisDAO("ICDIndexing", "MrdEssentialBOi");
		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);

			MrdEssentialDAO mrdEssentialDAO = new MrdEssentialDAO(tx);
			// RequestPurposeMstDAOi reqPurposeDAO=new RequestPurposeMstDAO(tx);
			MrdRecordRequestDtlDAOi mrdRecordReqDAO = new MrdRecordRequestDtlDAO(tx);
			GlobalClinicalDAO GlbClDao = new GlobalClinicalDAO();

			// lstRecordType=mrdEssDAO.getPatientRecord(userVO);
			// essMap.put(MrdConfig.RECORD_TYPE, lstRecordType);

			patAddDtl = GlbClDao.getPatientAddmisionNoWiseDtl(hisDAO, userVO, "1", patdtlVO);
			essMap.put(MrdConfig.PATIENT_ADDMISSION_NO_WISE_DTLLIST, patAddDtl);

			PatientDetailVO patdtlVONew = new PatientDetailVO();

			if (patAddDtl != null && patAddDtl.size() > 0)
			{
				patdtlVONew = (PatientDetailVO) patAddDtl.get(0);
				EpisodeDiagnosisVO voEpiDiagnosis_p = new EpisodeDiagnosisVO();
				voEpiDiagnosis_p.setEpisodeCode(patdtlVONew.getEpisodeCode());
				voEpiDiagnosis_p.setPatCrNo(patdtlVONew.getPatCrNo());
				System.out.println("value" + patdtlVONew.getAdmStatusCode());
				System.out.println("valuesmall" + patdtlVONew.getAdmstatuscode());
				System.out.println("cr" + voEpiDiagnosis_p.getPatCrNo());
				System.out.println("episode" + voEpiDiagnosis_p.getEpisodeCode());
				patDiagDtl = GlbClDao.getPatEpiDiagnosisDetail(hisDAO, "0", voEpiDiagnosis_p, userVO);
				essMap.put(MrdConfig.PATIENT_DIAGNOSIS_DTLLIST, patDiagDtl);

				// Added by Vasu on 06.March.2019
				lstPrevIcdRecords = mrdEssentialDAO.getPreviousIcdCodeDetails(patdtlVO, userVO);
				essMap.put(MrdConfig.PREV_ICD_CODES, lstPrevIcdRecords);

			}

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			if (hisDAO != null) {
				hisDAO.free();
				
			}
			hisDAO = null;
			tx.close();
		}
		return essMap;
	}

	public String getIcdDtls(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String diagnosisCodeList;

		try
		{
			tx.begin();
			InPatientEssentialDAOi inpatientEssDAO = new InPatientEssentialDAO(tx);
			diagnosisCodeList = inpatientEssDAO.getIcdDtls(userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return diagnosisCodeList;
	}

	public List getListDischargeType(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstDischargeType = new ArrayList();

		try
		{
			tx.begin();
			InPatientEssentialDAOi inpatientEssDAO = new InPatientEssentialDAO(tx);
			lstDischargeType = inpatientEssDAO.getDischargeStatusList(userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstDischargeType;
	}

	public CommonCaseSheetEnquiryVO[] searchRecord(CaseSheetEnquiryVO caseSheetEnqVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		CommonCaseSheetEnquiryVO[] commonCaseSheetVO = null;
		HashMap caseSheetMap = new HashMap();
		// HashMap finalQueryMap=new HashMap();

		try
		{
			tx.begin();
			MrdDAOi mrdDAO = new MrdDAO(tx);

			caseSheetMap = HelperMethods.createQueryMapFromVO(caseSheetEnqVO);
			if (caseSheetEnqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_OPD_FILE))
				commonCaseSheetVO = mrdDAO.searchOpdFileRecord(caseSheetMap, userVO);
			else
				commonCaseSheetVO = mrdDAO.searchRecord(caseSheetMap, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return commonCaseSheetVO;
	}

	/*
	 * ********************************MRD_RECORD_REQUEST_APPROVAL***************
	 * *******************
	 */

	public List getRequestListForApproval(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List mrdRecordReqDtlVOList = null;
		try
		{
			tx.begin();
			MrdRecordRequestDtlDAOi mrdRecordRequestDAO = new MrdRecordRequestDtlDAO(tx);
			mrdRecordReqDtlVOList = mrdRecordRequestDAO.selectAllRecordByRequestStatus(mrdRecordRequestDtlVO, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mrdRecordReqDtlVOList;
	}

	// get the list of the RequestRecordDtlVO by recordId and requestStatus
	public List getRequestDetail(RequestRecordDtlVO requestRecordDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List requestRecordDtlVOList = null;
		try
		{
			tx.begin();
			RequestRecordDtlDAOi requestRecordDAO = new RequestRecordDtlDAO(tx);
			requestRecordDtlVOList = requestRecordDAO.selectAllRecordByRequestID(requestRecordDtlVO, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return requestRecordDtlVOList;
	}

	// get the list of the RequestRecordDtlVO by recordId and requestStatus
	public Map getMrdRecordStatus(String mrdRecordId, String requestId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List mrdRecordDtlVOList = null;
		List mrdRecordIssueVOList = null;
		Map map = new HashMap();
		try
		{
			tx.begin();
			MrdDAOi mrdDAO = new MrdDAO(tx);
			MrdRecordIssueDtlDAOi issueDtlDAO = new MrdRecordIssueDtlDAO(tx);
			mrdRecordDtlVOList = mrdDAO.getMrdRecordStatusDetail(mrdRecordId, requestId, userVO);
			map.put(MrdConfig.MRD_RECORD_DTL_VO_LIST, mrdRecordDtlVOList);
			MrdRecordIssueDtlVO mrdRecordIssueDtlVO = new MrdRecordIssueDtlVO();
			mrdRecordIssueDtlVO.setMrdRecordId(mrdRecordId);
			mrdRecordIssueVOList = issueDtlDAO.selectByMrdRecordId(mrdRecordIssueDtlVO, userVO);
			map.put(MrdConfig.MRD_RECORD_ISSUE_DTL_VO_LIST, mrdRecordIssueVOList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return map;
	}

	public void saveApprovalDetail(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, List<RequestRecordDtlVO> requestRecordDtlVOList, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			RequestRecordDtlDAOi requestRecordDAO = new RequestRecordDtlDAO(tx);
			MrdRecordRequestDtlDAOi mrdRecordRequestDAO = new MrdRecordRequestDtlDAO(tx);

			if (requestRecordDtlVOList != null)
			{
				for (int i = 0; i < requestRecordDtlVOList.size(); i++)
				{
					if (requestRecordDtlVOList.get(i).getReqStatus().equals(MrdConfig.REQUESTED_RECORD_STATUS_ACCEPTED_AT_DEPT))
						requestRecordDAO.update(requestRecordDtlVOList.get(i), userVO);
					else
						requestRecordDAO.updateRejectDetail(requestRecordDtlVOList.get(i), userVO);
				}
			}

			if (mrdRecordRequestDtlVO.getReqStatus().equals(MrdConfig.MRD_RECORD_REQUEST_STATUS_CLOSED))
				mrdRecordRequestDAO.updateRejectDetail(mrdRecordRequestDtlVO, userVO);
			else
				mrdRecordRequestDAO.update(mrdRecordRequestDtlVO, userVO);

		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}

	}

	// public Map getEssenForInsuranceClaimRecordEntry(UserVO userVO)
	// {
	// JDBCTransactionContext tx = new JDBCTransactionContext();
	// Map essMap=new HashMap();
	// List insuranceReceivedList=null;
	// List empDoctorList=null;
	// try
	// {
	// tx.begin();
	// MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);
	// MbMasterEssentialDAOi mEssentialDAOi=new MbMasterEssentialDAO(tx);
	//
	// insuranceReceivedList=daoobj.getInsuranceReceivedDtlList(userVO);
	// essMap.put(MrdConfig.INSURANCE_RECEIVED_DTL_LIST, insuranceReceivedList);
	//
	// empDoctorList=mEssentialDAOi.getEmpDoctorList(userVO);
	// essMap.put(MrdConfig.LIST_OF_EMP_DOCTOR,empDoctorList);
	//
	//
	// }
	// catch (HisRecordNotFoundException e)
	// {
	// tx.rollback();
	// throw new HisRecordNotFoundException(e.getMessage());
	// }
	// catch (HisApplicationExecutionException e)
	// {
	// tx.rollback();
	// e.printStackTrace();
	// throw new HisApplicationExecutionException();
	// }
	// catch (HisDataAccessException e)
	// {
	// tx.rollback();
	// e.printStackTrace();
	// throw new HisDataAccessException();
	// }
	// catch (HisException e)
	// {
	// tx.rollback();
	// e.printStackTrace();
	// throw new HisException();
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// tx.rollback();
	// throw new HisApplicationExecutionException();
	// }
	// finally
	// {
	// tx.close();
	// }
	// return essMap;
	// }

	/*
	 * **********************************Mrd Record Issue
	 * ****************************************
	 */

	public List getRequestListForIssue(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List mrdRecordReqDtlVOList = null;
		try
		{
			tx.begin();
			MrdRecordRequestDtlDAOi mrdRecordRequestDAO = new MrdRecordRequestDtlDAO(tx);
			mrdRecordReqDtlVOList = mrdRecordRequestDAO.selectRecordRequestForIssue(mrdRecordRequestDtlVO, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mrdRecordReqDtlVOList;
	}

	public void saveIssueDetail(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, List<RequestRecordDtlVO> requestRecordDtlVOList, List<MrdRecordDtlVO> mrdRecordDtlVOList,
			List<MrdRecordIssueDtlVO> mrdRecordIssueDtlVOList, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			RequestRecordDtlDAOi requestRecordDAO = new RequestRecordDtlDAO(tx);
			MrdRecordRequestDtlDAOi mrdRecordRequestDAO = new MrdRecordRequestDtlDAO(tx);
			MRDRecordDtlDAOi mrdRecordDtlDAO = new MRDRecordDtlDAO(tx);
			MrdRecordIssueDtlDAOi mrdRecordIssueDtlDAO = new MrdRecordIssueDtlDAO(tx);

			if (requestRecordDtlVOList != null)
			{
				for (int i = 0; i < requestRecordDtlVOList.size(); i++)
				{
					if (requestRecordDtlVOList.get(i).getReqStatus().equals(MrdConfig.REQUESTED_RECORD_STATUS_REJECTED_AT_MRD))
					{
						mrdRecordRequestDAO.updateRequestStatus(mrdRecordIssueDtlVOList.get(i), userVO);
						if (mrdRecordRequestDtlVO.getExtendDays() != null && mrdRecordRequestDtlVO.getExtendDays() != "")
						{
							mrdRecordRequestDAO.updateForDays(mrdRecordRequestDtlVO, userVO);
							mrdRecordRequestDAO.updateExtensionDetailsRejected(mrdRecordRequestDtlVO, userVO);

						}
						// mrdRecordRequestDAO.setStatusIssueAgain(mrdRecordIssueDtlVOList.get(i),
						// userVO);
					}

					else
					{
						mrdRecordRequestDAO.updateRequestStatus(mrdRecordRequestDtlVO, userVO);
						if (mrdRecordRequestDtlVO.getExtendDays() != null && mrdRecordRequestDtlVO.getExtendDays() != "")
						{
							mrdRecordRequestDAO.updateExtension_SLNo(mrdRecordRequestDtlVO, userVO);
							mrdRecordRequestDAO.updateExtensionDetails(mrdRecordRequestDtlVO, userVO);
						}
					}
				}
			}
			// mrdRecordRequestDAO.updateRequestStatus(mrdRecordRequestDtlVO,
			// userVO);

			// update the selected record as issued
			if (requestRecordDtlVOList != null)
			{
				for (int i = 0; i < requestRecordDtlVOList.size(); i++)
				{

					requestRecordDAO.updateRequestStatus(requestRecordDtlVOList.get(i), userVO);

				}
			}

			// update the mrd record status as issued

			// insert issue detail
			if (mrdRecordIssueDtlVOList != null)
			{
				for (int i = 0; i < mrdRecordIssueDtlVOList.size(); i++)
				{
					if (mrdRecordIssueDtlVOList.get(i).getReqStatus().equals(MrdConfig.MRD_RECORD_REQUEST_STATUS_CLOSED))
					{
					}// do not update
					else
					{
						if (mrdRecordDtlVOList != null)
						{
							for (int j = 0; j < mrdRecordDtlVOList.size(); j++)
							{
								// if(mrdRecordDtlVOList.get(i).getreq)
								mrdRecordDtlDAO.updateRecordStatus(mrdRecordDtlVOList.get(j), userVO);
							}
						}
						if (mrdRecordRequestDtlVO.getExtendDays() != null && mrdRecordRequestDtlVO.getExtendDays() != "")
							mrdRecordIssueDtlDAO.createExtended(mrdRecordIssueDtlVOList.get(i), userVO);

						else
							mrdRecordIssueDtlDAO.create(mrdRecordIssueDtlVOList.get(i), userVO);
					}

				}
			}

		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}

	}

	public List getMrdBasedOnRecordType(String recordType, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List mrdList = null;
		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssentialDAO = new MrdEssentialDAO(tx);
			mrdList = mrdEssentialDAO.getMrdListBasedOnRecordType(recordType, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mrdList;
	}

	public List<MrdRecordRequestDtlVO> getRequestListForReturn(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<MrdRecordRequestDtlVO> mrdRecordIssueReqVOList = null;
		try
		{
			tx.begin();
			MrdRecordRequestDtlDAOi mrdRecordReqDAO = new MrdRecordRequestDtlDAO(tx);
			mrdRecordIssueReqVOList = mrdRecordReqDAO.getRequestListForReturn(userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mrdRecordIssueReqVOList;
	}

	public List<MrdRecordIssueDtlVO> getReturnedMrdRecordListByRequestId(String requestId, String recordId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<MrdRecordIssueDtlVO> mrdRecordIssueVOList = null;
		try
		{
			tx.begin();
			MrdRecordIssueDtlDAOi mrdRecordIssueDAO = new MrdRecordIssueDtlDAO(tx);
			mrdRecordIssueVOList = mrdRecordIssueDAO.getReturnedMrdRecordListByRequestId(requestId, recordId, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mrdRecordIssueVOList;
	}

	public List<RequestRecordDtlVO> getPendingRecordRequestStatus(String requestId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<RequestRecordDtlVO> pendingRequestStatus = new ArrayList<RequestRecordDtlVO>();

		try
		{
			tx.begin();
			RequestRecordDtlDAOi requestRecordDAO = new RequestRecordDtlDAO(tx);
			pendingRequestStatus = requestRecordDAO.getPendingRecordRequestStatus(requestId, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return pendingRequestStatus;
	}

	public Map getMrdNPurposeBasedOnRecordType(String recordType, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		RequestPurposeMstVO[] reqPurposeMstVO = null;
		List lstMrd = new ArrayList();

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			RequestPurposeMstDAOi reqPurposeDAO = new RequestPurposeMstDAO(tx);

			lstMrd = mrdEssDAO.getMrdListBasedOnRecordType(recordType, userVO);
			essentialMap.put(MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE, lstMrd);

			reqPurposeMstVO = reqPurposeDAO.getRequestPurpose(recordType, userVO);
			essentialMap.put(MrdConfig.ARR_REQUEST_PURPOSE_MST_VO, reqPurposeMstVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			essentialMap.put(MrdConfig.ARR_REQUEST_PURPOSE_MST_VO, reqPurposeMstVO);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public Map getReturnEssentialForRecordArchived(String recordType, String mrdCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		RackMstVO[] rackMstVO = null;
		List lstPutBy = new ArrayList();
		String deptCode = "";

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);

			rackMstVO = mrdEssDAO.getRackBasedOnMrd(recordType, mrdCode, userVO);
			essMap.put(MrdConfig.LIST_OF_RACK_BASED_ON_MRD, rackMstVO);

			lstPutBy = mrdEssDAO.getRecordPutByList(deptCode, userVO);
			essMap.put(MrdConfig.LIST_RECORD_PUT_BY_IN_MRD, lstPutBy);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	public MrdRecordDtlVO[] searchLostRecord(CaseSheetEnquiryVO caseSheetEnqVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MrdRecordDtlVO[] commonCaseSheetVO = null;
		HashMap caseSheetMap = new HashMap();

		try
		{
			tx.begin();
			MrdDAOi mrdDAO = new MrdDAO(tx);

			caseSheetMap = HelperMethods.createQueryMapFromVO(caseSheetEnqVO);
			commonCaseSheetVO = mrdDAO.searchLostRecord(caseSheetMap, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return commonCaseSheetVO;
	}

	public List getLostRecordReportedByList(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstReportedBy = new ArrayList();
		String deptCode = "";

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			lstReportedBy = mrdEssDAO.getRecordPutByList(deptCode, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstReportedBy;
	}

	public Map getEssentialForRecordMovement(String recordType, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		List lstPutBy = new ArrayList();
		List lstMrd = new ArrayList();
		String deptCode = "";

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);

			lstPutBy = mrdEssDAO.getRecordPutByList(deptCode, userVO);
			essMap.put(MrdConfig.LIST_RECORD_PUT_BY_IN_MRD, lstPutBy);

			lstMrd = mrdEssDAO.getMrdListBasedOnRecordType(recordType, userVO);
			essMap.put(MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE, lstMrd);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	public MrdRecordLostFoundDtlVO[] getLostRecordInMrdList(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MrdRecordLostFoundDtlVO[] mrdRecordLostVO = null;

		try
		{
			tx.begin();
			MrdRecordLostFoundDAOi mrdRecordLostDAO = new MrdRecordLostFoundDAO(tx);

			mrdRecordLostVO = mrdRecordLostDAO.getLostRecordInMrdList(userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mrdRecordLostVO;
	}

	public Map getFoundEssentialDtl(String selRecordId, String recordType, String mrdCode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		RackMstVO[] rackMstVO = null;
		List lstPutBy = new ArrayList();
		String deptCode = "";
		String prevPlace = "";

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);

			rackMstVO = mrdEssDAO.getRackBasedOnMrd(recordType, mrdCode, userVO);
			essMap.put(MrdConfig.LIST_OF_RACK_BASED_ON_MRD, rackMstVO);

			lstPutBy = mrdEssDAO.getRecordPutByList(deptCode, userVO);
			essMap.put(MrdConfig.LIST_RECORD_PUT_BY_IN_MRD, lstPutBy);

			prevPlace = mrdEssDAO.getRecordPreviousLocation(selRecordId, userVO);
			essMap.put(MrdConfig.STRING_RECORD_PREVIOUS_LOCATION, prevPlace);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essMap;
	}

	/*
	 * ************************************Duplicate Discharge Printing
	 * ***************************
	 */

	public List<DischargePrintingVO> getRecordForDupDischargePrinting(String patCrnNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<DischargePrintingVO> dischargePatList = null;
		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			dischargePatList = mrdEssDAO.getRecordForDuplicateDischargePrinting(patCrnNo, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();

			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return dischargePatList;
	}

	public List getPatientProfileByCrNo(String patCrnNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List patProfileVOList = null;
		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			patProfileVOList = mrdEssDAO.getPatientProfileByCrNo(patCrnNo, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();

			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return patProfileVOList;
	}

	public Map getEssenForInsuranceEnquiry(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssential = new HashMap();
		List allInsuranceCompanyList = null;
		try
		{
			tx.begin();
			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);

			allInsuranceCompanyList = daoobj.getAllInsuranceCompanyList(userVO);
			mpEssential.put(MrdConfig.ALL_INSURANCE_COMPANY_LIST, allInsuranceCompanyList);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mpEssential;
	}

	public List searchInsuranceDtl(String patFirstName, String patMiddleName, String patLastName, String companyId, String patCrNo, String policyNo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List insuranceVOList = null;
		try
		{
			tx.begin();
			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);

			insuranceVOList = daoobj.searchInsuranceDetail(patFirstName.toUpperCase().trim(), patMiddleName.toUpperCase().trim(), patLastName.toUpperCase().trim(), companyId.trim(), patCrNo.trim(),
					policyNo.trim(), userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return insuranceVOList;
	}

	public List getRequestedRecordId(String empId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstReqRecId = new ArrayList();

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			lstReqRecId = mrdEssDAO.getRequestedRecordId(empId, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstReqRecId;
	}

	public void saveCDBurnDetail(PatientProfileCDBurnDtlVO[] patientProfileCDBurnDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientProfileCDBurnDAO daoObj = new PatientProfileCDBurnDAO(tx);
			if (patientProfileCDBurnDtlVO != null)
			{
				for (PatientProfileCDBurnDtlVO vo : patientProfileCDBurnDtlVO)
				{
					daoObj.create(vo, userVO);
				}
			}

		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}

	}

	public Map getEmployeePopUpEssentials(UserVO _uservo)
	{

		Map essentialMap = new HashMap();
		List designationList = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MrdEssentialDAOi essentialDAO = new MrdEssentialDAO(tx);

		try
		{
			tx.begin();

			designationList = essentialDAO.getAllDesignations(_uservo);
			essentialMap.put(MrdConfig.ESSENTIAL_OPTION_DESIGNATION, designationList);

		} catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public StaffDetailVO[] searchStaffDetail(StaffDetailVO staffEnquiryVO, UserVO _userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		StaffDetailVO[] staffEnquiryVOs = null;
		HashMap staffDetailMap = new HashMap();
		MrdEssentialDAOi essentialDAO = new MrdEssentialDAO(tx);

		try
		{
			tx.begin();

			staffDetailMap = HelperMethods.createQueryMapFromVO(staffEnquiryVO);

			staffEnquiryVOs = essentialDAO.searchStaffDetail(staffDetailMap, _userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally
		{
			tx.close();
		}
		return staffEnquiryVOs;

	}

	public List getAuditTypeWiseList(UserVO userVO_p)
	{
		List lstAuditTypeWise = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientEmrAuditDAO patientEmrAuditDAO = new PatientEmrAuditDAO(tx);
		HisDAO hisDAO = new HisDAO("PatientEmrAudit", "PatientEmrAudit");
		try
		{
			tx.begin();
			// lstUnit=essentialDAO.getAuditTypeWiseList(userVO_p);
			lstAuditTypeWise = patientEmrAuditDAO.getAuditTypeWiseList(hisDAO, "1", userVO_p);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			if (hisDAO != null) {
				hisDAO.free();
				
			}
			hisDAO = null;
			tx.close();
		}
		return lstAuditTypeWise;
	}

	public List<PatientEmrAuditVO> getAuditUserList(String strMode_p, PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p)
	{
		List<PatientEmrAuditVO> lstAuditUser = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientEmrAuditDAO patientEmrAuditDAO = new PatientEmrAuditDAO(tx);
		HisDAO hisDAO = new HisDAO("PatientEmrAudit", "PatientEmrAudit");
		try
		{
			tx.begin();
			lstAuditUser = (List<PatientEmrAuditVO>) patientEmrAuditDAO.getAuditUserList(hisDAO, strMode_p, patientEmrAuditVO_p, userVO_p);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			if (hisDAO != null) {
				hisDAO.free();
				
			}
			hisDAO = null;
			tx.close();
		}
		return lstAuditUser;
	}

	public List<PatientEmrAuditVO> getPatientEmrAuditDtlByCrNo(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p)
	{
		List<PatientEmrAuditVO> lstPatientEmrAuditVO = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientEmrAuditDAO patientEmrAuditDAO = new PatientEmrAuditDAO(tx);
		HisDAO hisDAO = new HisDAO("PatientEmrAudit", "PatientEmrAudit");
		try
		{
			tx.begin();
			lstPatientEmrAuditVO = (List<PatientEmrAuditVO>) patientEmrAuditDAO.getPatientEmrAuditDtlByCrNo(hisDAO, patientEmrAuditVO_p, userVO_p);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			if (hisDAO != null) {
				hisDAO.free();
				
			}
			hisDAO = null;
			tx.close();
		}
		return lstPatientEmrAuditVO;
	}

	public List<List<String>> showPatientEmrAuditDiagnosisDialTileByPrimaryKey(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p)
	{
		List<List<String>> lstOfLstPatientEmrAudit = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientEmrAuditDAO patientEmrAuditDAO = new PatientEmrAuditDAO(tx);
		HisDAO hisDAO = new HisDAO("PatientEmrAudit", "PatientEmrAudit");
		try
		{
			tx.begin();
			lstOfLstPatientEmrAudit = (List<List<String>>) patientEmrAuditDAO.showPatientEmrAuditDiagnosisDialTileByPrimaryKey(hisDAO, patientEmrAuditVO_p, userVO_p);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			if (hisDAO != null) {
				hisDAO.free();
				
			}
			hisDAO = null;
			tx.close();
		}
		return lstOfLstPatientEmrAudit;
	}

	public boolean savePatientEmrAuditDtl(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p)
	{
		boolean boolPatientEmrAudit;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientEmrAuditDAO patientEmrAuditDAO = new PatientEmrAuditDAO(tx);
		HisDAO hisDAO = new HisDAO("PatientEmrAudit", "PatientEmrAudit");
		try
		{
			tx.begin();
			boolPatientEmrAudit = patientEmrAuditDAO.savePatientEmrAuditDtl(hisDAO, patientEmrAuditVO_p, userVO_p);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			if (hisDAO != null) {
				hisDAO.free();
				
			}
			hisDAO = null;
			tx.close();
		}
		return boolPatientEmrAudit;
	}

	public List<PatientEmrAuditVO> getPreviousPatientEmrAuditDtlByPrimaryKey(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p)
	{

		List<PatientEmrAuditVO> lstPatientEmrAuditVO = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientEmrAuditDAO patientEmrAuditDAO = new PatientEmrAuditDAO(tx);
		HisDAO hisDAO = new HisDAO("PatientEmrAudit", "PatientEmrAudit");
		try
		{
			tx.begin();
			lstPatientEmrAuditVO = (List<PatientEmrAuditVO>) patientEmrAuditDAO.getPreviousPatientEmrAuditDtlByPrimaryKey(hisDAO, patientEmrAuditVO_p, userVO_p);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			if (hisDAO != null) {
				hisDAO.free();
				
			}
			hisDAO = null;
			tx.close();
		}
		return lstPatientEmrAuditVO;
	}

	public List getWardBasedOnHospitalDepartmentUnit(String _hosCode, String _deptCode, String _deptUnitCode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List WardList = null;
		try
		{
			tx.begin();
			MrdEssentialDAOi objEssentialDAO = new MrdEssentialDAO(tx);
			WardList = objEssentialDAO.getWardBasedOnHospitalDepartmentUnit(_hosCode, _deptCode, _deptUnitCode, _userVO);
		} catch (HisRecordNotFoundException e)
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
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return WardList;
	}

	public List getRoomBasedOnHospitalDeptUnitWard(String _hosCode, String _deptUnitCode, String _wardCode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List roomList = null;
		try
		{
			tx.begin();
			MrdEssentialDAOi objEssentialDAO = new MrdEssentialDAO(tx);
			roomList = objEssentialDAO.getRoomBasedOnHospitalDeptUnitWard(_hosCode, _deptUnitCode, _wardCode);
		} catch (HisRecordNotFoundException e)
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
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return roomList;
	}

	public List getLaboratoryandTimeMap(UserVO _userVO, String departmentCode)
	{
		Map essentialMap = new HashMap();
		List lstLaboratory = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MrdEssentialDAOi objEssentialDAO = new MrdEssentialDAO(tx);
			lstLaboratory = objEssentialDAO.getLaboratoryAsPerDepartment(_userVO, departmentCode);

			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);
			java.util.Date dt = objGlobalEssentialDAO.getSystemDate(new java.util.Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstLaboratory;
	}

	public Map getRegistrationCensusReportEssentials(UserVO _userVO)
	{

		Map essentialMap = new HashMap();
		List lstPatCat = null;
		List hospitalList = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			// MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);

			hospitalList = objGlobalEssentialDAO.getHospitalCombo();
			essentialMap.put(MrdConfig.HOSPITAL_COMBO_LIST, hospitalList);

			lstPatCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(MrdConfig.MRD_PATIENT_CATEGORY_LIST, lstPatCat);

			java.util.Date dt = objGlobalEssentialDAO.getSystemDate(new java.util.Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;

	}

	public Map getRegistrationPatientListingReportEssentials(UserVO _userVO)
	{

		Map essentialMap = new HashMap();
		List hospitalList = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);

			hospitalList = objGlobalEssentialDAO.getHospitalCombo();
			essentialMap.put(MrdConfig.HOSPITAL_COMBO_LIST, hospitalList);

			java.util.Date dt = objGlobalEssentialDAO.getSystemDate(new java.util.Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;

	}

	public Map getOpdStaticReportEssentials(UserVO _userVO)
	{

		Map essentialMap = new HashMap();
		List hospitalList = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);

			hospitalList = objGlobalEssentialDAO.getHospitalCombo();
			essentialMap.put(MrdConfig.HOSPITAL_COMBO_LIST, hospitalList);

			java.util.Date dt = objGlobalEssentialDAO.getSystemDate(new java.util.Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;

	}

	public void getdeptComboDetails(ReportVO _rptVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MrdEssentialDAOi objEssentialDAO = new MrdEssentialDAO(tx);
			objEssentialDAO.getdeptComboDetails(_rptVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}

	}

	public Map getAllConsultant(UserVO _userVO)
	{

		Map essentialMap = new LinkedHashMap();
		List lstConsultant = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MrdEssentialDAOi objEssentialDAO = new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);

			lstConsultant = objEssentialDAO.getAllConsultant(_userVO);
			// essentialMap.put(OpdConfig.ESSENTIAL_BO_OPTION_ALLCONSULTANT,lstConsultant);
			java.util.Date dt = objGlobalEssentialDAO.getSystemDate(new java.util.Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;

	}

	/*
	 * **********************************Mrd Medical Camp
	 * ****************************************
	 */

	public List getCampListForMedicalCamp(MrdMedicalCampDtlVO mrdMedicalCampDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List mrdCampDtlVOList = null;
		try
		{
			tx.begin();
			MrdMedicalCampDtlDAOi mrdMedicalCampDAO = new MrdMedicalCampDtlDAO(tx);
			mrdCampDtlVOList = mrdMedicalCampDAO.getCampListForMedicalCamp(mrdMedicalCampDtlVO, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mrdCampDtlVOList;
	}

	public List getCampEmpNameForMedicalCamp(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List mrdCampEmpList = null;
		try
		{
			tx.begin();
			MrdMedicalCampDtlDAOi mrdMedicalCampDAO = new MrdMedicalCampDtlDAO(tx);
			mrdCampEmpList = mrdMedicalCampDAO.getCampEmpNameForMedicalCamp(userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mrdCampEmpList;
	}

	public void saveCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, MrdMedicalCampTeamDtlVO[] medicalCampTeamDtlVO, UserVO userVO)
	{
		HisDAO dao = null;
		String medicalCampNo = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			dao = new HisDAO("medicalCamp", "campEmpDetails");
			tx.begin();
			MrdMedicalCampDtlDAOi mrdMedicalCampDAO = new MrdMedicalCampDtlDAO(tx);

			// String bagNo=mrdMedicalCampDAO.generateCampNo(userVO);

			if (medicalCampDtlVO.getMode().equals("1"))
			{
				// to save medical camp dtls..
				medicalCampNo = mrdMedicalCampDAO.saveCampDetail(medicalCampDtlVO, userVO);
				System.out.println("Genrated medicalCampNo :" + medicalCampNo);
			}
			if (medicalCampDtlVO.getMode().equals("2"))
			{
				// to update medical camp dtls..
				medicalCampNo = medicalCampDtlVO.getStrCampReqNo();
				System.out.println("campReqNo in  BO :" + medicalCampNo);
				mrdMedicalCampDAO.updateCampDetail(medicalCampDtlVO, userVO);
			}

			// to save medical camp emp dtls..
			// deleteing existing records
			if (medicalCampDtlVO.getMode().equals("2"))
			{
				mrdMedicalCampDAO.saveCampEmpDetail(medicalCampTeamDtlVO[0], userVO, dao, medicalCampNo, "2");
			}
			if (medicalCampTeamDtlVO != null && medicalCampTeamDtlVO.length > 0)
			{
				for (int i = 0; i < medicalCampTeamDtlVO.length; i++)
				{
					mrdMedicalCampDAO.saveCampEmpDetail(medicalCampTeamDtlVO[i], userVO, dao, medicalCampNo, "1");
				}
			}

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			if (dao != null) {
				dao.free();
				
			}
			dao = null;
			tx.close();
		}
	}

	public void getCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MrdMedicalCampDtlDAOi mrdMedicalCampDAO = new MrdMedicalCampDtlDAO(tx);
			mrdMedicalCampDAO.getCampDetail(medicalCampDtlVO, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
	}

	public List<MrdMedicalCampTeamDtlVO> getCampEmpDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<MrdMedicalCampTeamDtlVO> mrdCampEmpDtlList = null;
		try
		{
			tx.begin();
			MrdMedicalCampDtlDAOi mrdMedicalCampDAO = new MrdMedicalCampDtlDAO(tx);
			mrdCampEmpDtlList = mrdMedicalCampDAO.getCampEmpDetail(medicalCampDtlVO, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mrdCampEmpDtlList;
	}

	public String getPatDiagnosisList(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAO = new HisDAO("Opd", "OpdEssentialBO");
		String diagnosis = null;

		try
		{
			tx.begin();
			MedicalCertificateRequestDAO medicalCertificateRequestDAO = new MedicalCertificateRequestDAO(tx);
			diagnosis = medicalCertificateRequestDAO.getDiagnosisDtl(hisDAO, "0", patMedicalDtlVO, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			if (hisDAO != null) {
				hisDAO.free();
				
			}
			hisDAO = null;
			tx.close();
		}
		return diagnosis;
	}

	public Map<String, Object> getEssentials(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List<PatMedicalDtlVO> medicalCertificateReqVOList = null;
		List lstMedicalCerificateReqAdvBy = new ArrayList();
		List<PatMedicalDtlVO> lstPrevMedicalReqDtl = new ArrayList();
		String str = "";
		String pmode = "1";
		String consultentType = MrdConfig.MEDICAL_CERTIFICATE_REQUEST;

		HisDAO hisDAO = new HisDAO("Mrd", "MrdEssentialBO");
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			MedicalCertificateRequestDAO medicalCertificateReqDAO = new MedicalCertificateRequestDAO(tx);

			// medicalCertificateReqVOList=medicalCertificateReqDAO.getMedicalCertificateReqTariffDtl(patMedicalDtlVO.getDepartmentUnitCode(),
			// userVO);
			// if(medicalCertificateReqVOList.size()<1)
			// {
			// medicalCertificateReqVOList=medicalCertificateReqDAO.getMedicalCertificateReqTariffDtlMst(userVO);
			// }
			// essentialMap.put(MrdConfig.ESTIMATE_PROCEDURE_TARIFF_DETAIL_LIST,
			// medicalCertificateReqVOList);

			lstMedicalCerificateReqAdvBy = medicalCertificateReqDAO.getMedicalCertificateReqAdvisedBy(patMedicalDtlVO.getDepartmentUnitCode(), userVO, consultentType);
			essentialMap.put(MrdConfig.MEDICAL_CERTIFICATE_REQUEST_ADVISEDBY_DETAIL, lstMedicalCerificateReqAdvBy);
			lstPrevMedicalReqDtl = medicalCertificateReqDAO.getPrevMedicalReqDtl(hisDAO, pmode, patMedicalDtlVO, userVO);
			essentialMap.put(MrdConfig.PREVIOUS_MEDICAL_CERTIFIATE_DTL, lstPrevMedicalReqDtl);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			if (hisDAO != null) {
				hisDAO.free();
				
			}
			hisDAO = null;
			tx.close();
		}
		return essentialMap;
	}

	public Map getEstimateCertificateReqDtl(UserVO userVO)
	{
		Map essentialMap = new HashMap();
		// List<EstimateCertificateReqVO> estimateCertificateReqVOList=null;
		List lstEstimateReqAdvBy = new ArrayList();
		List lstEstimateReqDtl = new ArrayList();
		List relationList = new ArrayList();
		String str = "";
		String pmode = "1";

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EstimateCertificateIssueDAO estimateCertificateIssueDAO = new EstimateCertificateIssueDAO(tx);
			lstEstimateReqDtl = estimateCertificateIssueDAO.getEstimateCertificateReqDtl(pmode, userVO);
			essentialMap.put(MrdConfig.ESTIMATE_CERTIFIATE_REQUEST_STATUS_DTL, lstEstimateReqDtl);
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			relationList = objDAO.getAllRelationList(userVO);
			essentialMap.put(OpdConfig.ALL_RELATIONSHIP_LIST, relationList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public Map getEstimateCertificateIssueDtl(EstimateCertificateIssueVO estimateCertificateIssueVO, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List lstEstimateCerIssue = new ArrayList();
		EstimateCertificateIssueVO[] estimateCertificateIssueVOList = null;
		EstimateCertificateIssueVO[] tariffListVOList = null;
		List relationList = new ArrayList();
		String str = "";
		String pmode = "1";

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EstimateCertificateIssueDAO estimateCertificateIssueDAO = new EstimateCertificateIssueDAO(tx);
			// lstEstimateCerIssue =
			// estimateCertificateIssueDAO.getEstimateCertificateIssueDtl(pmode,estimateCertificateIssueVO,userVO);
			// essentialMap.put(MrdConfig.ESTIMATE_CERTIFIATE_ISSUE_PAT_DTL,
			// lstEstimateCerIssue);
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			relationList = objDAO.getAllRelationList(userVO);
			essentialMap.put(OpdConfig.ALL_RELATIONSHIP_LIST, relationList);

			// for lstEstimateCerIssue vo
			estimateCertificateIssueVOList = estimateCertificateIssueDAO.getEstimateCertificateIssuePatDtl(pmode, estimateCertificateIssueVO, userVO);
			essentialMap.put(MrdConfig.ESTIMATE_CERTIFIATE_ISSUE_PAT_DTL, estimateCertificateIssueVOList);

			pmode = "2";
			tariffListVOList = estimateCertificateIssueDAO.getTariffsListForEstimateCert(pmode, estimateCertificateIssueVO, userVO);
			essentialMap.put(MrdConfig.ALL_REQUESTED_TARIFFS_LIST, tariffListVOList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public void saveEstimateCertificateIssueDtl(EstimateCertificateIssueVO estimateCertificateIssueVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String pmode = "1";
		try
		{
			tx.begin();
			EstimateCertificateIssueDAO estimateCertificateIssueDAO = new EstimateCertificateIssueDAO(tx);

			String recordType = MrdConfig.RECORD_TYPE_ESTIMATE_CERTIFICATE;
			String recordStatus = MrdConfig.RECORD_STATUS_IN_DEPARTMENTUNIT_WARD;
			estimateCertificateIssueDAO.saveEstimateCertificateIssueDtl(pmode, recordType, recordStatus, estimateCertificateIssueVO, userVO);
		} catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		} catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally
		{
			tx.close();
		}
	}

	public Map getEstimateRequestEssentials(EstimateCertificateReqVO estReqDtlVO, UserVO strUserVO)
	{
		Map essentialMap = new HashMap();
		List<EstimateCertificateReqVO> estimateCertificateReqVOList = null;
		List lstEstimateReqAdvBy = new ArrayList();
		List<EstimateCertificateReqVO> lstPrevEstimateReqDtl = new ArrayList();
		String str = "";
		String pmode = "1";

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EstimateRequestDAO estimateReqDAO = new EstimateRequestDAO(tx);

			estimateCertificateReqVOList = estimateReqDAO.getEstimateReqTariffDtl(estReqDtlVO.getDepartmentUnitCode(), strUserVO);
			if (estimateCertificateReqVOList.size() < 1)
			{
				estimateCertificateReqVOList = estimateReqDAO.getEstimateReqTariffDtlMst(strUserVO);
			}
			essentialMap.put(MrdConfig.ESTIMATE_PROCEDURE_TARIFF_DETAIL_LIST, estimateCertificateReqVOList);

			lstEstimateReqAdvBy = estimateReqDAO.getEstimateReqAdvisedBy(estReqDtlVO.getDepartmentUnitCode(), strUserVO);
			essentialMap.put(MrdConfig.ESTIMATE_REQUEST_ADVISEDBY_DETAIL, lstEstimateReqAdvBy);
			lstPrevEstimateReqDtl = estimateReqDAO.getPrevEstimateReqDtl(pmode, estReqDtlVO, strUserVO);
			essentialMap.put(MrdConfig.PREVIOUS_ESTIMATE_CERTIFIATE_DTL, lstPrevEstimateReqDtl);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public void saveEstimateCertificateReqDtl(EstimateCertificateReqVO estReqVo, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String pmode = "1";
		try
		{
			tx.begin();
			MrdEssentialDAO mrdEssDAO = new MrdEssentialDAO(tx);
			EstimateRequestDAO estimateReqDAO = new EstimateRequestDAO(tx);

			if (estReqVo != null)
			{
				String tariffId[] = estReqVo.getTariffId().split(",");
				System.out.println(tariffId.length);
				for (int i = 0; i < tariffId.length; i++)
				{
					String certificateId = mrdEssDAO.generateCertificateId(MrdConfig.RECORD_TYPE_ESTIMATE_CERTIFICATE, estReqVo.getDepartmentUnitCode(), MrdConfig.ESTIMATE_REQUEST_GENMODE_AUTOMATIC,
							userVO);
					String id = tariffId[i];
					estimateReqDAO.create(pmode, certificateId, id, estReqVo, userVO);
					// estimateReqDAO.billingRequest(certificateId,id,estReqVo,
					// userVO);
				}
			}

			// estimateReqDAO.create(estReqVo, userVO);

		} catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		} catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally
		{
			tx.close();
		}
	}

	public Map generateEstimateCertificate(EstimateCertificateIssueVO estimateCertificateIssueVO, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		PackageServiceMstVO[] packageServiceMstVOList = null;
		PackageServiceMstVO[] tariffListVOList = null;
		EstimateCertificateIssueVO[] diagnosisList = null;
		List relationList = new ArrayList();
		String str = "";
		String pmode = "";

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EstimateCertificateIssueDAO estimateCertificateIssueDAO = new EstimateCertificateIssueDAO(tx);

			pmode = "1";
			packageServiceMstVOList = estimateCertificateIssueDAO.generateEstimateCertificate(pmode, estimateCertificateIssueVO, userVO);
			diagnosisList = estimateCertificateIssueDAO.getDiagnosisList(pmode, estimateCertificateIssueVO, userVO);
			essentialMap.put(MrdConfig.ESTIMATE_CERTIFIATE_GENERATION_DTL, packageServiceMstVOList);
			essentialMap.put(MrdConfig.ESTIMATE_CERTIFIATE_DIAGNOSIS_DTL, diagnosisList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public Map getMedicalCertificatePatReqList(UserVO userVO)
	{
		Map essentialMap = new HashMap();
		PatMedicalDtlVO[] patMedicalDtlVOList = null;
		String str = "";
		String pmode = "2";

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			MedicalCertificateDAO medicalCertificateDAO = new MedicalCertificateDAO(tx);
			patMedicalDtlVOList = medicalCertificateDAO.getMedicalCertificatePatReqList(pmode, userVO);
			essentialMap.put(MrdConfig.MEDICAL_CERTIFICATE_PAT_REQ_DTL, patMedicalDtlVOList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public Map getMedicalCertificateIssuePatDtl(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List relationList = new ArrayList();
		String str = "";
		String pmode = "1";

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{

			tx.begin();
			EstimateCertificateIssueDAO estimateCertificateIssueDAO = new EstimateCertificateIssueDAO(tx);
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			relationList = objDAO.getAllRelationList(userVO);
			essentialMap.put(OpdConfig.ALL_RELATIONSHIP_LIST, relationList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public String getBillNoQtyDtl(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String billNoQty = "";

		try
		{
			tx.begin();
			MedicalCertificateDAO medicalCertificateDAO = new MedicalCertificateDAO(tx);
			billNoQty = medicalCertificateDAO.getBillNoQtyDtl(patMedicalDtlVO, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return billNoQty;
	}

	public void saveMedicalCertificateIssueDtl(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String pmode = "2";
		try
		{
			tx.begin();
			MedicalCertificateDAO medicalCertificateDAO = new MedicalCertificateDAO(tx);
			if (patMedicalDtlVO.getRecordType().equals("11"))
			{
				medicalCertificateDAO.saveMedicalCertificateIssueDtl(pmode, patMedicalDtlVO, userVO);
			} else
			{
				medicalCertificateDAO.saveFitnessCertificateIssueDtl(pmode, patMedicalDtlVO, userVO);
			}
		} catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		} catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally
		{
			tx.close();
		}
	}

	public Map<String, Object> getEssential(mrd.vo.DupRecPrintReqVO dupRecPrintReqVO, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List<mrd.vo.DupRecPrintReqVO> dupRecPrintReqVOList = null;
		List dupRectType = new ArrayList();
		List dupGender = new ArrayList();
		List ageTypeList = new ArrayList();
		List mrdEmpList = new ArrayList();
		List mrdRelList = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			registration.dao.EssentialDAOi regEssDAO = new registration.dao.EssentialDAO(tx);
			DuplicateRecordPrintReqDAO dupRecPrintReqDAO = new DuplicateRecordPrintReqDAO(tx);
			dupRectType = dupRecPrintReqDAO.getRecordType(userVO);
			essentialMap.put(MrdConfig.RECORD_TYPE, dupRectType);
			dupGender = regEssDAO.getGender(userVO);
			essentialMap.put(MrdConfig.ESSENTIAL_GENDER_LIST, dupGender);
			ageTypeList = regEssDAO.getAgeType();
			essentialMap.put(MrdConfig.ALL_AGE_TYPE_LIST, ageTypeList);
			mrdEmpList = dupRecPrintReqDAO.getEmpList(userVO);
			essentialMap.put(MrdConfig.ESSENTIAL_ALL_MRD_EMP_LIST, mrdEmpList);
			mrdEmpList = objDAO.getAllRelationList(userVO);
			essentialMap.put(MrdConfig.ESSENTIAL_ALL_RELATION_LIST, mrdEmpList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;

	}

	public Map getDupRecPrintAndHandoverDtl(UserVO userVO)
	{
		Map essentialMap = new HashMap();
		DupRecPrintReqVO[] dupRecPrintReqVOList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			DupRecPrintAndHandoverDAO dupRecPrintAndHandoverDAO = new DupRecPrintAndHandoverDAO(tx);
			dupRecPrintReqVOList = dupRecPrintAndHandoverDAO.getDupRecPrintAndHandoverDtl(userVO);
			essentialMap.put(MrdConfig.DUPLICATE_RECORD_PRINTING_AND_HANDOVER, dupRecPrintReqVOList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public Map getDuplicateRecordHandoverDtl(DupRecPrintReqVO dupRecPrintReqVO, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List relationList = new ArrayList();
		String str = "";
		String pmode = "1";

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{

			tx.begin();
			EstimateCertificateIssueDAO estimateCertificateIssueDAO = new EstimateCertificateIssueDAO(tx);
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			relationList = objDAO.getAllRelationList(userVO);
			essentialMap.put(OpdConfig.ALL_RELATIONSHIP_LIST, relationList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public String getBillNoDtl(DupRecPrintReqVO dupRecPrintReqVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String billNoQty = "";

		try
		{
			tx.begin();
			DupRecPrintAndHandoverDAO dupRecPrintAndHandoverDAO = new DupRecPrintAndHandoverDAO(tx);
			billNoQty = dupRecPrintAndHandoverDAO.getBillNoQtyDtl(dupRecPrintReqVO, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return billNoQty;
	}

	public void saveDuplicateRecordPrintReqDtl(DupRecPrintReqVO dupRecPrintReqVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String pmode = "2";
		try
		{
			tx.begin();
			MrdEssentialDAO mrdEssDAO = new MrdEssentialDAO(tx);
			DuplicateRecordPrintReqDAO dupRecPrintReqDAO = new DuplicateRecordPrintReqDAO(tx);
			String certificateId = mrdEssDAO.generateCertificateId(MrdConfig.RECORD_TYPE_MEDICAL, "", MrdConfig.MEDICAL_CERIFICATE_REQUEST_GENMODE_AUTOMATIC, userVO);
			dupRecPrintReqDAO.saveDuplicateRecordPrintReqDtl(dupRecPrintReqVO, certificateId, userVO);

		} catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		} catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally
		{
			tx.close();
		}
	}

	public void saveDuplicateRecordHandoverDtl(DupRecPrintReqVO dupRecPrintReqVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String pmode = "2";
		try
		{
			tx.begin();
			MrdEssentialDAO mrdEssDAO = new MrdEssentialDAO(tx);
			DuplicateRecordPrintReqDAO dupRecPrintReqDAO = new DuplicateRecordPrintReqDAO(tx);
			String certificateId = mrdEssDAO.generateCertificateId(MrdConfig.RECORD_TYPE_MEDICAL, "", MrdConfig.MEDICAL_CERIFICATE_REQUEST_GENMODE_AUTOMATIC, userVO);
			dupRecPrintReqDAO.updateDuplicateRecordPrintReqDtl(dupRecPrintReqVO, certificateId, userVO);

		} catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDuplicateRecordException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		} catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally
		{
			tx.close();
		}
	}

	// added by Manisha Gangwar on 14.oct.2015 for getting details for adding
	// certificate B entry form

	public Map<String, Object> getEssentialcertificate(mrd.vo.CertificateBEntryFormReqVO certBEntryReqVO, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List<mrd.vo.CertificateBEntryFormReqVO> certBEntryReqVOList = null;

		List mrdEmpList = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			registration.dao.EssentialDAOi regEssDAO = new registration.dao.EssentialDAO(tx);
			CertificateBEntryFormDAO certBEntryDAO = new CertificateBEntryFormDAO(tx);
			mrdEmpList = certBEntryDAO.getEmpList(userVO);
			essentialMap.put(MrdConfig.ESSENTIAL_ALL_MRD_EMP_LIST, mrdEmpList);
			mrdEmpList = objDAO.getAllRelationList(userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;

	}

	// added by Manisha Gangwar on 19.oct.2015 obtaining list of certificate b
	// request

	public Map getCertificatBEntryDtl(UserVO userVO)
	{
		Map essentialMap = new HashMap();
		CertificateBEntryFormReqVO[] CertificateBEntryFormReqVOList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			CertificateBEntryFormDAO certBEntryDAO = new CertificateBEntryFormDAO(tx);
			CertificateBEntryFormReqVOList = certBEntryDAO.getCertificateBEntryFormDtl(userVO);
			essentialMap.put(MrdConfig.CERTIFICATE_B_ENTRY_FORM_REQUEST, CertificateBEntryFormReqVOList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	// added by Manisha Gangwar on 20.10.2015 for getting Certificate B entry
	// handover details & getting Bill status

	public Map getcertifificateHandoverDtl(CertificateBEntryFormReqVO certBEntryReqVO, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List relationList = new ArrayList();
		/* String str=""; */
		/* String pmode="1"; */

		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{

			tx.begin();
			EstimateCertificateIssueDAO estimateCertificateIssueDAO = new EstimateCertificateIssueDAO(tx);
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);
			relationList = objDAO.getAllRelationList(userVO);

			essentialMap.put(OpdConfig.ALL_RELATIONSHIP_LIST, relationList);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public MrdRecordRequestDtlVO checkUserIsEmp(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MrdRecordRequestDtlVO mrdRecordRequestDtlVOO = new MrdRecordRequestDtlVO();
		Map map = new HashMap();
		try
		{
			tx.begin();
			MrdDAOi mrdDAO = new MrdDAO(tx);

			mrdRecordRequestDtlVOO = mrdDAO.checkUserIsEmp(mrdRecordRequestDtlVO, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mrdRecordRequestDtlVOO;
	}

	public List<MRDDocumentUploadVO> getMRDDocumentEssentials(String patCrNo, String episodeCode, UserVO _userVO)
	{
		List mrdRecordDtlVOList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap = new HashMap();
		try
		{
			tx.begin();
			MRDDocumentUploadDAO documentUploadDtlDAOi = new MRDDocumentUploadDAO(tx);
			mrdRecordDtlVOList = documentUploadDtlDAOi.getDocumentDetailsByCrNo(patCrNo, episodeCode, _userVO);
			// essMap.put(MrdConfig.MRD_RECORD_VO_LIST, mrdRecordDtlVOList);

		} catch (HisRecordNotFoundException e)
		{
			// tx.rollback();
			// throw new HisRecordNotFoundException(e.getMessage(),essMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return mrdRecordDtlVOList;
	}

	public List getStaffMembers(String recordType, String rackId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List staff = new ArrayList();

		try
		{
			tx.begin();
			MrdEssentialDAOi mrdEssDAO = new MrdEssentialDAO(tx);
			staff = mrdEssDAO.getShelfBasedOnRack(recordType, rackId, userVO);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return staff;
	}

	public Map getBulletinelist(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List bulletin = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			MrdEssentialDAO objDAO = new MrdEssentialDAO(tx);
			bulletin = objDAO.getBulletinelist(_userVO);
			essentialMap.put(MrdConfig.BULLETIN_TYPE, bulletin);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public List<BulletinHeadVO> getClinicalSectionTemplatePrintList(BulletinGenerationVO bulletinVO, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<BulletinHeadVO> lstBulletinTemplatePrint = new ArrayList<BulletinHeadVO>();

		try
		{
			tx.begin();
			MrdEssentialDAO objDAO = new MrdEssentialDAO(tx);

			lstBulletinTemplatePrint = objDAO.getBulletinTemplatePrintList(bulletinVO, userVO);

		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstBulletinTemplatePrint;
	}

	public List<BulletinHeadDataVO> getDataFromQuery(String query, UserVO voUser)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<BulletinHeadDataVO> lstDatafromquery = new ArrayList<BulletinHeadDataVO>();
		try
		{
			tx.begin();
			MrdEssentialDAO objDAO = new MrdEssentialDAO(tx);

			lstDatafromquery = objDAO.getDatafromQuery(query, voUser);
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		return lstDatafromquery;
	}
	
	public void insertHtmlData(BulletinGenerationVO bulletinVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<BulletinHeadDataVO> lstDatafromquery = new ArrayList<BulletinHeadDataVO>();
		boolean isDataExists;
		try
		{
			tx.begin();
			
			MrdEssentialDAO objDAO = new MrdEssentialDAO(tx);
			//isDataExists = objDAO.getEssentialsForBulletin(headVO, bulletinVO);
			
			//if(isDataExists)
			//{
				String hmode="1";
				 objDAO.insertHtmlData(hmode, bulletinVO);
			//}
				//String hmode="2";
				//objDAO.insertHtmlData(hmode,headVO, bulletinVO);
			
		} catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally
		{
			tx.close();
		}
		
	}


}// end class

