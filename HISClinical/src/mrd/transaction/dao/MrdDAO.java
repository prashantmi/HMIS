package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.AdmissionAdviceVO;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeRestAdviceVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.ServiceAreaPatientVO;
import hisglobal.vo.Service_Req_dtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.rowset.WebRowSet;

import hisglobal.vo.VisitReasonsVO;
import mrd.MrdConfig;
import mrd.vo.CommonCaseSheetEnquiryVO;
public class MrdDAO extends DataAccessObject implements MrdDAOi
{
	public  MrdDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	// Getting the List of Rest Advice Given to the Patient on a Particular Episode 
	public EpisodeRestAdviceVO[] getEpisodeRestAdvice(String crNo,String epiCode,UserVO userVO)
	{
		EpisodeRestAdviceVO[] arrEpiRestAdviceVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_EPISODE_REST_ADVICE.HRGT_EPISODE_RESTADVICE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), epiCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.REST_ADVICE_CERTIFICATE_STATUS_NOT_GENERATED);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeRestAdviceVO.class, rs);
			arrEpiRestAdviceVO = new EpisodeRestAdviceVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrEpiRestAdviceVO[i] = (EpisodeRestAdviceVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrEpiRestAdviceVO;
	}
	
	// Getting the List of All Visits of the patient on a Particular Episode
	public EpisodeVO[] getAllVisitOfEpisodePat(String crNo,String epiCode,UserVO userVO)
	{
		EpisodeVO[] arrVisitEpiVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_ALL_VISIT_NEW_ADVICE.HRGT_EPISODE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), epiCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			arrVisitEpiVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrVisitEpiVO[i] = (EpisodeVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrVisitEpiVO;
		
	}
	
	// Getting the List of Admission Advice Given to the Patient on a Particular Episode
	public AdmissionAdviceVO[] getAdmissionAdvice(String crNo,String epiCode,UserVO userVO)
	{
		AdmissionAdviceVO[] arrAdmAdviceVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_ADM_ADVICE.HIPT_ADMISSIONADVICE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), epiCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(AdmissionAdviceVO.class, rs);
			arrAdmAdviceVO = new AdmissionAdviceVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrAdmAdviceVO[i] = (AdmissionAdviceVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrAdmAdviceVO;
	}
	
	// Getting The List of Certificate For Movement By Unit 
	public CertificateIssueDtlVO[] getAllCertificateForMoveBydUnit(String unitCode,UserVO userVO)
	{
		CertificateIssueDtlVO[] arrCertificateMoveDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_ALL_CERTIFICATE_BY_UNIT_FOR_MOVE.HPMRT_CERTIFICATE_ISSUE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_READY_TO_DISPATCH);
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_NOT_RECEIVED_RETURN);
		populateMAP.put(sq.next(), unitCode);
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_READY_TO_DISPATCH);
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_NOT_RECEIVED_RETURN);
		populateMAP.put(sq.next(), unitCode);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(CertificateIssueDtlVO.class, rs);
			arrCertificateMoveDtlVO = new CertificateIssueDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrCertificateMoveDtlVO[i] = (CertificateIssueDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrCertificateMoveDtlVO;
	}
	
	// Getting The List of Certificate For Movement By Cr No 
	public CertificateIssueDtlVO[] getAllCertificateForMoveByCrNo(String crNo,UserVO userVO)
	{
		CertificateIssueDtlVO[] arrCertificateMoveDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_ALL_CERTIFICATE_BY_CRNO_FOR_MOVE.HPMRT_CERTIFICATE_ISSUE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
	//	populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_IS_DUPLICATE_NO);
	//	populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_READY_TO_DISPATCH);
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_NOT_RECEIVED_RETURN);
	//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	//	populateMAP.put(sq.next(), userVO.getHospitalCode());
	//	populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_IS_DUPLICATE_NO);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
	//	populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_IS_DUPLICATE_NO);
	//	populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_READY_TO_DISPATCH);
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_NOT_RECEIVED_RETURN);
	//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	//	populateMAP.put(sq.next(), userVO.getHospitalCode());
	//	populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_IS_DUPLICATE_NO);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(CertificateIssueDtlVO.class, rs);
			arrCertificateMoveDtlVO = new CertificateIssueDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrCertificateMoveDtlVO[i] = (CertificateIssueDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrCertificateMoveDtlVO;
	}
	
	public EpisodeRestAdviceVO[] getEpisodeRestAdviceTodayVisited(String crNo,String epiCode,UserVO userVO)
	{
		EpisodeRestAdviceVO[] arrEpiRestAdviceVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_EPISODE_REST_ADVICE_TODAY.HRGT_EPISODE_RESTADVICE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), epiCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.REST_ADVICE_CERTIFICATE_STATUS_NOT_GENERATED);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeRestAdviceVO.class, rs);
			arrEpiRestAdviceVO = new EpisodeRestAdviceVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrEpiRestAdviceVO[i] = (EpisodeRestAdviceVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrEpiRestAdviceVO;
	}
	
	/**
	 * get the list of discharged patient whose case sheet has not been dispatched
	 */
	public List <CaseSheetDtlVO> getCaseSheetListToReady(String unitCode,String wardCode,UserVO userVO)
	{
		List<CaseSheetDtlVO> caseSheetVOList = new ArrayList<CaseSheetDtlVO>();
		CaseSheetDtlVO caseDtlVO;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_ALL_PATIENTS_FOR_CASESHEET_DISPATCH";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);  //Added by prachi
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);  //Added by prachi
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), wardCode);
		populateMAP.put(sq.next(), MrdConfig.PATIENT_ADMISSION_STATUS_DISCHARGE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_READY_TO_DIPATCH);
		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_READY_TO_DISPATCH);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_IN_WARD);
		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_DISPATCHED);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_PARTIAL_LOST);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				caseDtlVO=new CaseSheetDtlVO();
				HelperMethods.populateVOfrmRS(caseDtlVO, rs);
				caseSheetVOList.add(caseDtlVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return caseSheetVOList;
	}

	
	
	
	//added by swati on date:01-may-2019
	public List <CaseSheetDtlVO> getCaseSheetListToReadyADMNOWISE(String admno,UserVO userVO)
	{
		List<CaseSheetDtlVO> caseSheetVOList = new ArrayList<CaseSheetDtlVO>();
		CaseSheetDtlVO caseDtlVO;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_ALL_PATIENTS_FOR_CASESHEET_DISPATCH_ADMNOWISE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
//		
//		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
//		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
//		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
//		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
//		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
//		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
//		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
//		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
//		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
//		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
//		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
//		populateMAP.put(sq.next(), admno);
//		
//		populateMAP.put(sq.next(), MrdConfig.PATIENT_ADMISSION_STATUS_DISCHARGE);
//		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
//		populateMAP.put(sq.next(), userVO.getHospitalCode());
//		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_READY_TO_DIPATCH);
//		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_READY_TO_DISPATCH);
//		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_IN_WARD);
//		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_DISPATCHED);
//		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_PARTIAL_LOST);
		
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);  //Added by prachi
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);  //Added by prachi
		populateMAP.put(sq.next(), admno);
		populateMAP.put(sq.next(), MrdConfig.PATIENT_ADMISSION_STATUS_DISCHARGE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_READY_TO_DIPATCH);
		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_READY_TO_DISPATCH);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_IN_WARD);
		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_DISPATCHED);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_PARTIAL_LOST);
		System.out.println("hello java");
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				caseDtlVO=new CaseSheetDtlVO();
				HelperMethods.populateVOfrmRS(caseDtlVO, rs);
				caseSheetVOList.add(caseDtlVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return caseSheetVOList;
	}

		//get list of record for case sheet handover
	//ADDED BY SWATI  ON DATE:06-MAY-2019
	//ADM NO WISE DTL
		
		public RecordDispatchDtlVO[] getCaseSheetListToReadyADMNOWISE2(String admno,UserVO userVO)
		{
			RecordDispatchDtlVO[] arrDispatchVO = null;
			ValueObject vo[] = null;
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
			String queryKey = "SELECT.FOR_CASESHEET_HANDOVER.HPMRT_RECORD_DISPATCH_DTL_ADMNOWISE";
			
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				//populateMAP.put(sq.next(), recordDispatchVO.getRecordStatus());
				populateMAP.put(sq.next(), userVO.getHospitalCode());
				//populateMAP.put(sq.next(), recordDispatchVO.getRecordType());
				populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
				populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_MLC_CASESHEET);
				//populateMAP.put(sq.next(), recordDispatchVO.getDeptUnitCode());
				populateMAP.put(sq.next(), admno);
		
			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("No Record Found");
					//arrDispatchVO = null;
				}
				else
				{
					rs.beforeFirst();
					
					vo = HelperMethods.populateVOfrmRS(RecordDispatchDtlVO.class, rs);
					arrDispatchVO = new RecordDispatchDtlVO[vo.length];
					for (int i = 0; i < vo.length; i++)
					{
						arrDispatchVO[i] = (RecordDispatchDtlVO) vo[i];
					}
				}	
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);
			}
			return arrDispatchVO;
		}
	
		
		
		//ADDED BY SWATI  ON DATE:07-MAY-2019
		//CR NO WISE DTL
			
			public RecordDispatchDtlVO[] getCaseSheetListToReadyCRNOWISE2(String crno,UserVO userVO)
			{
				RecordDispatchDtlVO[] arrDispatchVO = null;
				ValueObject vo[] = null;
				String query = "";
				Map populateMAP = new HashMap();
				Sequence sq = new Sequence();
				String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
				String queryKey = "SELECT.FOR_CASESHEET_HANDOVER.HPMRT_RECORD_DISPATCH_DTL_CRNOWISE";
				
				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
				
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				//	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					//populateMAP.put(sq.next(), recordDispatchVO.getRecordStatus());
					populateMAP.put(sq.next(), userVO.getHospitalCode());
					//populateMAP.put(sq.next(), recordDispatchVO.getRecordType());
					populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
					populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_MLC_CASESHEET);
					//populateMAP.put(sq.next(), recordDispatchVO.getDeptUnitCode());
					populateMAP.put(sq.next(), crno);
			
				try
				{
					ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
					if (!rs.next())
					{
						throw new HisRecordNotFoundException("No Record Found");
						//arrDispatchVO = null;
					}
					else
					{
						rs.beforeFirst();
						
						vo = HelperMethods.populateVOfrmRS(RecordDispatchDtlVO.class, rs);
						arrDispatchVO = new RecordDispatchDtlVO[vo.length];
						for (int i = 0; i < vo.length; i++)
						{
							arrDispatchVO[i] = (RecordDispatchDtlVO) vo[i];
						}
					}	
				}
				catch (Exception e)
				{
					if (e.getClass() == HisRecordNotFoundException.class)
					{
						throw new HisRecordNotFoundException(e.getMessage());
					}
					else throw new HisDataAccessException("Application Execution Exception" + e);
				}
				return arrDispatchVO;
			}
		
		
		
		
		
		
//--------------------------------------------------------------------------------------------------------------
	//get list of record for case sheet handover ADM WARDCOPY
	
/*	public List <CaseSheetDtlVO> getCaseSheetListToReadyADMNOWISE(String admno,UserVO userVO)
	{
		RecordDispatchDtlVO[] arrDispatchVO = null;
		List<CaseSheetDtlVO> caseSheetVOList = new ArrayList<CaseSheetDtlVO>();
		ValueObject vo[] = null;
		CaseSheetDtlVO caseDtlVO;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.FOR_CASESHEET_HANDOVER.HPMRT_RECORD_DISPATCH_DTL_ADMNOWISE";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	//	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), recordDispatchVO.getRecordStatus());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), recordDispatchVO.getRecordType());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_MLC_CASESHEET);
		//populateMAP.put(sq.next(), recordDispatchVO.getDeptUnitCode());
		populateMAP.put(sq.next(), admno);
	
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				caseDtlVO=new CaseSheetDtlVO();
				HelperMethods.populateVOfrmRS(caseDtlVO, rs);
				caseSheetVOList.add(caseDtlVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return caseSheetVOList;
	}
	
	*/
	
	
	//added by swati
	//date:02-may-2019
	

	public List <CaseSheetDtlVO> getCaseSheetListToReadyCRNOWISE(String crno,UserVO userVO)
	{
		List<CaseSheetDtlVO> caseSheetVOList = new ArrayList<CaseSheetDtlVO>();
		CaseSheetDtlVO caseDtlVO;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_ALL_PATIENTS_FOR_CASESHEET_DISPATCH_CRNOWISE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);  //Added by prachi
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);  //Added by prachi
		populateMAP.put(sq.next(), crno);
		populateMAP.put(sq.next(), MrdConfig.PATIENT_ADMISSION_STATUS_DISCHARGE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_READY_TO_DIPATCH);
		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_READY_TO_DISPATCH);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_IN_WARD);
		//populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_DISPATCHED);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_PARTIAL_LOST);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				caseDtlVO=new CaseSheetDtlVO();
				HelperMethods.populateVOfrmRS(caseDtlVO, rs);
				caseSheetVOList.add(caseDtlVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return caseSheetVOList;
	}
	
	
	
		public RecordTypeWiseEnclosureMstVO[] getAllEnclosureDetails(String recordType,UserVO userVO)
	{
		RecordTypeWiseEnclosureMstVO[] arrEnclosureDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "ENCLOSURE_LIST_FOR_CASESHEET_DISPATCH.HPMRT_RECORD_ENCLOSURE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), recordType);
	
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No record found");
				
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(RecordTypeWiseEnclosureMstVO.class, rs);
			arrEnclosureDtlVO = new RecordTypeWiseEnclosureMstVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrEnclosureDtlVO[i] = (RecordTypeWiseEnclosureMstVO) vo[i];
			}
		}
		catch (Exception e)
		{
			/*if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else*/ 
				throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrEnclosureDtlVO;
	}
	
	public RecordTypeCheckListMstVO[] getAllChecklistDetails(String checklistMode,String recordType,UserVO userVO)
	{
		RecordTypeCheckListMstVO[] arrChecklistDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "CHECKLIST_FOR_CASESHEET_DISPATCH.HPMRT_RECORD_TYPE_CHECKLIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), recordType);
		populateMAP.put(sq.next(), checklistMode);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No record found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(RecordTypeCheckListMstVO.class, rs);
			arrChecklistDtlVO = new RecordTypeCheckListMstVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrChecklistDtlVO[i] = (RecordTypeCheckListMstVO) vo[i];
			}
		}
		catch (Exception e)
		{
			/*if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else */
				throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrChecklistDtlVO;
	}

	
	public PatientDetailVO getDischargePatientDtlByCrNo(String patCrNo,UserVO userVO)
	{
		PatientDetailVO patientDetailVO=new PatientDetailVO();
		String query = "";
		Map populateMap= new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.DISCHARGE_PATIENT_DTL.BYCRNO";
		
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), patCrNo);
		//populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		//populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		//populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		//populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMap.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE );
		populateMap.put(sq.next(), patCrNo);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE );
		populateMap.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(patientDetailVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return patientDetailVO;
	}
	
	
	public List<CaseSheetDtlVO> getPatientCaseSheetDtlByCrNo(String patCrNo,UserVO userVO)
	{
		List <CaseSheetDtlVO> caseSheetDtlVOList=new ArrayList<CaseSheetDtlVO>();
		CaseSheetDtlVO caseSheetDtlVO=null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.CASE_SHEET_DTL_.CR_NO_WISE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
		//populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE); // commented by Manisha Gangwar (Bug Fixing 13842) Date:23/2/2016
		populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_READY_TO_DISPATCH);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_IN_WARD);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_LOST);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_PARTIAL_LOST);
		populateMAP.put(sq.next(), patCrNo);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Case Sheet Pending");
			}
			rs.beforeFirst();
			while(rs.next()){
				caseSheetDtlVO=new CaseSheetDtlVO();
				HelperMethods.populateVOfrmRS(caseSheetDtlVO, rs);
				caseSheetDtlVOList.add(caseSheetDtlVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else 
				throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return caseSheetDtlVOList;
	}

	
	public List<CaseSheetDtlVO> getPatientCaseSheetDtl(String deptUnitCode,String ward,UserVO userVO)
	{
		List <CaseSheetDtlVO> caseSheetDtlVOList=new ArrayList<CaseSheetDtlVO>();
		CaseSheetDtlVO caseSheetDtlVO=null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.CASE_SHEET_DTL_.UNIT_WARD_WISE";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), ward);
		populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED);
		populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_READY_TO_DISPATCH);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_IN_WARD);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_LOST);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_PARTIAL_LOST);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Case Sheet Pending");
			}
			rs.beforeFirst();
			while(rs.next()){
				caseSheetDtlVO=new CaseSheetDtlVO();
				HelperMethods.populateVOfrmRS(caseSheetDtlVO, rs);
				caseSheetDtlVOList.add(caseSheetDtlVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else 
				throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		return caseSheetDtlVOList;
	}
	
	
	//get list of record for case sheet handover
	
	public RecordDispatchDtlVO[] getRecordListByUnitWard(RecordDispatchDtlVO recordDispatchVO,UserVO userVO)
	{
		RecordDispatchDtlVO[] arrDispatchVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.FOR_CASESHEET_HANDOVER.HPMRT_RECORD_DISPATCH_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	//	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), recordDispatchVO.getRecordStatus());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), recordDispatchVO.getRecordType());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_MLC_CASESHEET);
		populateMAP.put(sq.next(), recordDispatchVO.getDeptUnitCode());
		populateMAP.put(sq.next(), recordDispatchVO.getWardCode());
	
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
				//arrDispatchVO = null;
			}
			else
			{
				rs.beforeFirst();
				
				vo = HelperMethods.populateVOfrmRS(RecordDispatchDtlVO.class, rs);
				arrDispatchVO = new RecordDispatchDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDispatchVO[i] = (RecordDispatchDtlVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return arrDispatchVO;
	}
	
	
	//get list of record for case sheet handover by cr no. and recordtype
	
	public RecordDispatchDtlVO[] getRecordListByPatCrNo(RecordDispatchDtlVO recordDispatchVO,UserVO userVO)
	{
		RecordDispatchDtlVO[] arrDispatchVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.FOR_CASESHEET_HANDOVER_BY_PATCRNO.HPMRT_RECORD_DISPATCH_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), recordDispatchVO.getRecordStatus());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), recordDispatchVO.getRecordType());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_MLC_CASESHEET);
		populateMAP.put(sq.next(), recordDispatchVO.getPatCrNo());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
				//arrDispatchVO = null;
			}
			else
			{
				rs.beforeFirst();
				
				vo = HelperMethods.populateVOfrmRS(RecordDispatchDtlVO.class, rs);
				arrDispatchVO = new RecordDispatchDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDispatchVO[i] = (RecordDispatchDtlVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return arrDispatchVO;
	}
	
	
	/* **************************************************************************************
	 * Get the Array of the RecordTypeCheckListMstVO on basis of checklistMode and recordType 
	 * ***************************************************************************************/
	public RecordTypeCheckListMstVO[] getChecklistByRecordType(String checklistMode,String recordType,UserVO userVO)
	{
		RecordTypeCheckListMstVO[] arrChecklistDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.BY_RECORD_TYPE.HPMRT_RECORD_TYPE_CHECKLIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), recordType);
		populateMAP.put(sq.next(), checklistMode);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No record found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(RecordTypeCheckListMstVO.class, rs);
			arrChecklistDtlVO = new RecordTypeCheckListMstVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrChecklistDtlVO[i] = (RecordTypeCheckListMstVO) vo[i];
			}
		}
		catch (Exception e)
		{
			/*if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else */
				throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrChecklistDtlVO;
	}

	 /* ***************************************************************
	 * get the list of the employee for handover/receiving of records
	 * on basis of processId, deptUnit and wardCode
	 * ****************************************************************/
	public List getEmployeeListForHandover(String processId,String departmentUnitCode,String wardCode,UserVO userVO)
	{
		List empList=null;
		ResultSet rs=null;
		String strErr = "";
		
		try
		{
			Procedure procedure=new Procedure(MrdConfig.GET_EMP_LIST_PROCEDURE);
			procedure.addInParameter(1,Types.NUMERIC, userVO.getHospitalCode());
			procedure.addInParameter(2,Types.NUMERIC, processId);
			procedure.addInParameter(3,Types.NUMERIC, (departmentUnitCode.equals("%"))?"0":departmentUnitCode);
			procedure.addInParameter(4,Types.NUMERIC, wardCode);
			procedure.addOutParameter(5, Types.VARCHAR);
			procedure.addOutParameter(6, Types.REF);//OracleTypes.CURSOR);
				
			procedure.execute(super.getTransactionContext().getConnection());
			
			
			rs=(ResultSet)procedure.getParameterAt(6);
			strErr = (String) procedure.getParameterAt(5);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdDAO.getEmployeeListForHandover" + e
					+ strErr);
		}
		
		try
		{
			if (!rs.next()){
    			System.out.println("No records Found");
    			throw new HisRecordNotFoundException();       			
    		}
    		else
    		{
    			
    			rs.beforeFirst();
    			empList=new ArrayList();
				Entry entry=null;
				while (rs.next()) {
					entry=new Entry();
					entry.setValue(rs.getString(1));
					entry.setLabel(rs.getString(2));
					empList.add(entry);
				}
    		}
		}
			
			
		catch (Exception e)
		{
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
				throw new HisDataAccessException("Data access exception "+e);
		}
		
		
		return empList;
	}
	
	////Search Case Sheet For Request
	public CommonCaseSheetEnquiryVO[] searchRecord(HashMap _finalQueryMap,UserVO userVO)
	{
		CommonCaseSheetEnquiryVO[] commonCaseSheetVO=null;
		ValueObject[] valueObjects=null;
		Map _populateMap=new HashMap();
		String query="";
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SEARCH_RECORD_FOR_RECORD_REQUEST";
		String whereCOndition="";
		String finalQuery="";
		ResultSet rs=null;
		String orderBy=" ORDER BY TRIM (b.hrgstr_fname), TRIM (b.hrgstr_mname), TRIM (b.hrgstr_lname)";
		Sequence sq = new Sequence();
		String recordType=(String)_finalQueryMap.get("recordtype");
		
	//	_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),MrdConfig.MRD_RECORD_STATUS_IN_MRD);
		_populateMap.put(sq.next(),MrdConfig.MRD_RECORD_STATUS_ARCHIVED);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),recordType);
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		Set keySet=(Set)_finalQueryMap.keySet();
		Iterator keySetItr=keySet.iterator();
		
		while(keySetItr.hasNext())
		{
			String mapKey=(String)keySetItr.next();
			String mapValue=(String)_finalQueryMap.get(mapKey);
			
			if(mapKey.equals("hrgstr_fname") || mapKey.equals("hrgstr_mname") || mapKey.equals("hrgstr_lname"))
			{
				whereCOndition=whereCOndition+" AND "+"( UPPER (b."+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
			}
			if(mapKey.equals("hrgnum_puk") || mapKey.equals("hipnum_admno") || mapKey.equals("hrgnum_mlc_no") || mapKey.equals("hipdt_disstatus_code"))
			{
				whereCOndition=whereCOndition+" AND "+" a."+mapKey+" = "+mapValue;
			}
			if(mapKey.equals("fromdate"))
			{
				whereCOndition=whereCOndition +" AND "+ "trunc(hipdt_disdatetime) >= trunc(to_date('" +mapValue +"','dd-Mon-yyyy'))";
			}
			if(mapKey.equals("todate"))
			{
				whereCOndition=whereCOndition +" AND "+ "trunc(hipdt_disdatetime) <= trunc(to_date('" +mapValue +"','dd-Mon-yyyy'))";
			}
		}	
		
		
		
		finalQuery=query + whereCOndition + orderBy;
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), finalQuery,_populateMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try
		{
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found Matching The Search Criteria");
			}
			else
			{
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(CommonCaseSheetEnquiryVO.class, rs);
				commonCaseSheetVO=new CommonCaseSheetEnquiryVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++)
				{
					commonCaseSheetVO[i]=(CommonCaseSheetEnquiryVO)valueObjects[i];
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
				throw new HisDataAccessException("Enquiry Data access exception "+e);
		}
		return commonCaseSheetVO;
	}
	
	////Search Case Sheet For Request
	public List<MrdRecordDtlVO> getMrdRecordStatusDetail(String mrdRecordId,String requestId,UserVO userVO)
	{
		List <MrdRecordDtlVO> mrdRecordDtlVOList=null;
		MrdRecordDtlVO mrdRecordDtlVO=null;
		Map _populateMap=new HashMap();
		String query="";
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.RECORD_STATUS_DETAIL.HPMRT_MRD_RECORD_DTL";
		ResultSet rs=null;
		Sequence sq = new Sequence();
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);

			_populateMap.put(sq.next(),MrdConfig.MRD_RACK_STATUS_WORKING);
			_populateMap.put(sq.next(),MrdConfig.MRD_RECORD_REQUEST_STATUS_RAISED);
			_populateMap.put(sq.next(),requestId);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),userVO.getHospitalCode());
			_populateMap.put(sq.next(),mrdRecordId);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,_populateMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try
		{
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No Status Found");
			}
			else
			{
				rs.beforeFirst();
				mrdRecordDtlVOList=new ArrayList<MrdRecordDtlVO>();
				while (rs.next()) {
					mrdRecordDtlVO=new MrdRecordDtlVO();
					HelperMethods.populateVOfrmRS(mrdRecordDtlVO, rs);
					mrdRecordDtlVOList.add(mrdRecordDtlVO);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
				throw new HisDataAccessException("Enquiry Data access exception "+e);
		}
		return mrdRecordDtlVOList;
	}
	
	public MrdRecordDtlVO[] searchLostRecord(HashMap _finalQueryMap,UserVO userVO)
	{
		MrdRecordDtlVO[] commonCaseSheetVO=null;
		ValueObject[] valueObjects=null;
		Map _populateMap=new HashMap();
		String query="";
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SEARCH_RECORD_FOR_RECORD_LOST";
		String whereCOndition="";
		String finalQuery="";
		ResultSet rs=null;
		String orderBy=" ORDER BY patName";
		Sequence sq = new Sequence();
		
		
		//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),MrdConfig.MRD_RECORD_STATUS_IN_MRD);
		_populateMap.put(sq.next(),MrdConfig.MRD_RECORD_STATUS_ARCHIVED);
		
		
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		Set keySet=(Set)_finalQueryMap.keySet();
		Iterator keySetItr=keySet.iterator();
		
		while(keySetItr.hasNext())
		{
			String mapKey=(String)keySetItr.next();
			String mapValue=(String)_finalQueryMap.get(mapKey);
			
			if(mapKey.equals("hrgstr_fname") || mapKey.equals("hrgstr_mname") || mapKey.equals("hrgstr_lname"))
			{
				whereCOndition=whereCOndition+" AND "+"( UPPER (b."+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
			}
			if(mapKey.equals("hrgnum_puk"))
			{
				whereCOndition=whereCOndition+" AND "+" a."+mapKey+" = "+mapValue;
			}
			if(mapKey.equals("recordtype"))
			{
				whereCOndition=whereCOndition +" AND "+ "a.hpmrnum_record_type = "+ mapValue;
			}
			
		}	
		
		
		
		finalQuery=query + whereCOndition + orderBy;
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), finalQuery,_populateMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try
		{
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found Matching The Search Criteria");
			}
			else
			{
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(MrdRecordDtlVO.class, rs);
				commonCaseSheetVO=new MrdRecordDtlVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++)
				{
					commonCaseSheetVO[i]=(MrdRecordDtlVO)valueObjects[i];
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
				throw new HisDataAccessException("Enquiry Data access exception "+e);
		}
		return commonCaseSheetVO;
	}

	////Search OPD File For Request
	public CommonCaseSheetEnquiryVO[] searchOpdFileRecord(HashMap _finalQueryMap,UserVO userVO)
	{
		CommonCaseSheetEnquiryVO[] commonCaseSheetVO=null;
		ValueObject[] valueObjects=null;
		Map _populateMap=new HashMap();
		String query="";
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SEARCH_OPD_FILE_FOR_RECORD_REQUEST";
		String whereCOndition="";
		String finalQuery="";
		ResultSet rs=null;
		String orderBy=" ORDER BY INITCAP(a.hrgstr_fname), INITCAP(a.hrgstr_mname), INITCAP(a.hrgstr_lname)";
		Sequence sq = new Sequence();
		String recordType=(String)_finalQueryMap.get("recordtype");
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		//_populateMap.put(sq.next(),"0");
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),MrdConfig.MRD_RECORD_STATUS_IN_MRD);
		_populateMap.put(sq.next(),MrdConfig.MRD_RECORD_STATUS_ARCHIVED);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),recordType);
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		Set keySet=(Set)_finalQueryMap.keySet();
		Iterator keySetItr=keySet.iterator();
		
		while(keySetItr.hasNext())
		{
			String mapKey=(String)keySetItr.next();
			String mapValue=(String)_finalQueryMap.get(mapKey);
			
			if(mapKey.equals("hrgstr_fname") || mapKey.equals("hrgstr_mname") || mapKey.equals("hrgstr_lname"))
			{
				whereCOndition=whereCOndition+" AND "+"( UPPER (a."+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
			}
			if(mapKey.equals("hrgnum_puk") || mapKey.equals("hipnum_admno") || mapKey.equals("hrgnum_mlc_no") || mapKey.equals("hipdt_disstatus_code"))
			{
				whereCOndition=whereCOndition+" AND "+" a."+mapKey+" = "+mapValue;
			}
			/*if(mapKey.equals("fromdate"))
			{
				whereCOndition=whereCOndition +" AND "+ "trunc(hipdt_disdatetime) >trunc(to_date('" +mapValue +"','dd-mm-yyyy'))";
			}
			if(mapKey.equals("todate"))
			{
				whereCOndition=whereCOndition +" AND "+ "trunc(hipdt_disdatetime) <= trunc(to_date('" +mapValue +"','dd-mm-yyyy'))";
			}*/
		}	
		
		
		
		finalQuery=query + whereCOndition + orderBy;
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), finalQuery,_populateMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try
		{
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found Matching The Search Criteria");
			}
			else
			{
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(CommonCaseSheetEnquiryVO.class, rs);
				commonCaseSheetVO=new CommonCaseSheetEnquiryVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++)
				{
					commonCaseSheetVO[i]=(CommonCaseSheetEnquiryVO)valueObjects[i];
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
				throw new HisDataAccessException("Data access exception "+e);
		}
		return commonCaseSheetVO;
	}
	

public VisitReasonsVO[] retrieveVisitReasonsForEMR(String _crNo,String departmentUnitArray[],String accessType,UserVO _userVO) {
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_emr_view.proc_pat_visit_reasons_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		VisitReasonsVO[] _visitReasonsvo = null;
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
		daoObj.setProcInValue(nProcIndex, "p_crno", _crNo,2);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),3);
		daoObj.setProcOutValue(nProcIndex, "err", 1,4);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
			/*queryDate = HelperMethodsDAO.getQuery(filename, queryKeyDate);
			System.out.println("queryDate " + queryDate);
*/
		strErr = daoObj.getString(nProcIndex, "err");
		//System.out.println("strErr----------------------->"+strErr);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		finally {
			if (daoObj != null) {
				daoObj.free();
				
			}
			daoObj = null;
		}

		try {
			ValueObject[] vo = {};
			if (!rs.next()) {
				//throw new HisRecordNotFoundException("Visit Reasons Not Found");
			} else {
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(VisitReasonsVO.class, rs);
				_visitReasonsvo = new VisitReasonsVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					_visitReasonsvo[i] = (VisitReasonsVO) vo[i];
				}
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("MRDDAO:retrieveVisitReasonsForEMR:HelperMethods :: " + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return _visitReasonsvo;
	}

public Service_Req_dtlVO[] retrieveServiceProceduresForEMR(String _crNo,String departmentUnitArray[],String accessType,UserVO _userVO) {
	
	WebRowSet rs = null;
	HisDAO daoObj = null;
	String strProcName = "{call pkg_emr_view.proc_pat_service_procedures_dtl(?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";
	Service_Req_dtlVO[] _serviceReqDtlvos = null;
	try 
	{
	daoObj = new HisDAO("Registration","EssentialDAO");
	nProcIndex = daoObj.setProcedure(strProcName);
	daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
	daoObj.setProcInValue(nProcIndex, "p_crno", _crNo,2);
	daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),3);
	daoObj.setProcOutValue(nProcIndex, "err", 1,4);
	daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
	daoObj.executeProcedureByPosition(nProcIndex);

	strErr = daoObj.getString(nProcIndex, "err");
	rs = daoObj.getWebRowSet(nProcIndex, "resultset");
	
		/*queryDate = HelperMethodsDAO.getQuery(filename, queryKeyDate);
		System.out.println("queryDate " + queryDate);
*/
	strErr = daoObj.getString(nProcIndex, "err");
	//System.out.println("strErr----------------------->"+strErr);
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}

	finally {
		if (daoObj != null) {
			daoObj.free();
			
		}
		daoObj = null;
	}
	try {
		ValueObject[] vo = {};
		if (!rs.next()) {
		//	throw new HisRecordNotFoundException("Service Procedures Not Found");
		} else {
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(Service_Req_dtlVO.class, rs);
			_serviceReqDtlvos = new Service_Req_dtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_serviceReqDtlvos[i] = (Service_Req_dtlVO) vo[i];
			}
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("MRDDAO:retrieveServiceProceduresForEMR:HelperMethods :: " + e);
	}
	finally 
	{
		if (daoObj != null) 
		{
			daoObj.free();
			daoObj = null;
		}
	}
	return _serviceReqDtlvos;
}


public PatientProfileDetailVO[] retrieveProfileDetailsForEMR(String _crNo,String departmentUnitArray[],String accessType,UserVO _userVO) {
	
	WebRowSet rs = null;
	HisDAO daoObj = null;
	String strProcName = "{call pkg_emr_view.proc_pat_profiles_dtl(?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";
	PatientProfileDetailVO[] _patientProfileDtlvos = null;
	try 
	{
	daoObj = new HisDAO("Registration","EssentialDAO");
	nProcIndex = daoObj.setProcedure(strProcName);
	daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
	daoObj.setProcInValue(nProcIndex, "p_crno", _crNo,2);
	daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),3);
	daoObj.setProcOutValue(nProcIndex, "err", 1,4);
	daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
	daoObj.executeProcedureByPosition(nProcIndex);

	strErr = daoObj.getString(nProcIndex, "err");
	rs = daoObj.getWebRowSet(nProcIndex, "resultset");
	
		/*queryDate = HelperMethodsDAO.getQuery(filename, queryKeyDate);
		System.out.println("queryDate " + queryDate);
*/
	strErr = daoObj.getString(nProcIndex, "err");
	//System.out.println("strErr----------------------->"+strErr);
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	finally {
		if (daoObj != null) {
			daoObj.free();
			
		}
		daoObj = null;
	}

	try {
		ValueObject[] vo = {};
		if (!rs.next()) {
		//	throw new HisRecordNotFoundException("Profiles Not Found");
		} else {
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(PatientProfileDetailVO.class, rs);
			_patientProfileDtlvos = new PatientProfileDetailVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_patientProfileDtlvos[i] = (PatientProfileDetailVO) vo[i];
			}
		}
	} catch (Exception e) {
		
			throw new HisDataAccessException("MRDDAO:retrieveProfileDetailsForEMR:HelperMethods :: " + e);
	}
	finally 
	{
		if (daoObj != null) 
		
			daoObj.free();
			daoObj = null;
		
	}
	return _patientProfileDtlvos;
}

public MrdRecordRequestDtlVO checkUserIsEmp(
		MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
{
	MrdRecordRequestDtlVO mrdRecordRequestDtlVOO = new MrdRecordRequestDtlVO();
	Map _populateMap=new HashMap();
	String query="";
	String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
	String queryKey = "CHECK_USER_IS_EMP_PIST_EMP_REGISTRATION_DTL";
	ResultSet rs=null;
	Sequence sq = new Sequence();
	
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),userVO.getUserEmpID());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	
	try
	{
		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,_populateMap);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	}
	
	try
	{
		if(!rs.next())
		{
			//throw new HisRecordNotFoundException("No Status Found");
		}
		else
		{
			rs.beforeFirst();
			rs.next();
			mrdRecordRequestDtlVOO.setIsUserEmp(rs.getString(1));
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());	
		}
		else			 		
			throw new HisDataAccessException("Enquiry Data access exception "+e);
	}
	return mrdRecordRequestDtlVOO;
}
public DocumentUploadDtlVO[] retrieveDocumentUploadForEMR(String _crNo,String departmentUnitArray[],String accessType,UserVO _userVO) {
	
	WebRowSet rs = null;
	HisDAO daoObj = null;
	String strProcName = "{call pkg_emr_view.proc_pat_document_upload_dtl(?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr = "";
	DocumentUploadDtlVO[] _documentUploadDtlVOs = null;
	try 
	{
	daoObj = new HisDAO("Registration","EssentialDAO");
	nProcIndex = daoObj.setProcedure(strProcName);
	daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
	daoObj.setProcInValue(nProcIndex, "p_crno", _crNo,2);
	daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),3);
	daoObj.setProcOutValue(nProcIndex, "err", 1,4);
	daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
	daoObj.executeProcedureByPosition(nProcIndex);

	strErr = daoObj.getString(nProcIndex, "err");
	rs = daoObj.getWebRowSet(nProcIndex, "resultset");
	
		/*queryDate = HelperMethodsDAO.getQuery(filename, queryKeyDate);
		System.out.println("queryDate " + queryDate);
*/
	strErr = daoObj.getString(nProcIndex, "err");
	//System.out.println("strErr----------------------->"+strErr);
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	finally {
		if (daoObj != null) {
			daoObj.free();
			
		}
		daoObj = null;
	}

	try {
		ValueObject[] vo = {};
		if (!rs.next()) {
		} else {
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(DocumentUploadDtlVO.class, rs);
			_documentUploadDtlVOs = new DocumentUploadDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_documentUploadDtlVOs[i] = (DocumentUploadDtlVO) vo[i];
			}
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("MRDDAO:retrieveDocumentUploadForEMR:HelperMethods :: " + e);
	}
	finally 
	{
		if (daoObj != null) 
		{
			daoObj.free();
			daoObj = null;
		}
	}
	return _documentUploadDtlVOs;
}

public RecordDispatchDtlVO[] getRecordListByPatAdmNo(RecordDispatchDtlVO recordDispatchVO,UserVO userVO)
{
	RecordDispatchDtlVO[] arrDispatchVO = null;
	ValueObject vo[] = null;
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
	String queryKey = "SELECT.FOR_CASESHEET_HANDOVER_BY_PATADMNO.HPMRT_RECORD_DISPATCH_DTL_APPROVAL";
	
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	
	/*populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	//populateMAP.put(sq.next(), recordDispatchVO.getRecordStatus());
	populateMAP.put(sq.next(), userVO.getHospitalCode());
	//populateMAP.put(sq.next(), recordDispatchVO.getRecordType());
	populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
	populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_MLC_CASESHEET);
	populateMAP.put(sq.next(), recordDispatchVO.getPatAdmNo());*/
	
	
	
	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	//	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), recordDispatchVO.getRecordStatus());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), recordDispatchVO.getRecordType());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_MLC_CASESHEET);
		populateMAP.put(sq.next(), recordDispatchVO.getPatAdmNo());
	
	
	try
	{
		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		if (!rs.next())
		{
			throw new HisRecordNotFoundException("No Record Found");
			//arrDispatchVO = null;
		}
		else
		{
			rs.beforeFirst();
			
			vo = HelperMethods.populateVOfrmRS(RecordDispatchDtlVO.class, rs);
			arrDispatchVO = new RecordDispatchDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrDispatchVO[i] = (RecordDispatchDtlVO) vo[i];
			}
		}	
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		else throw new HisDataAccessException("Application Execution Exception" + e);
	}
	return arrDispatchVO;
}

}
