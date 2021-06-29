package medicalboard.transactions.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.Conn;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.BoardConfigurationVO;
import hisglobal.vo.BoardDetailVO;
import hisglobal.vo.BoardTeamDetailVO;
import hisglobal.vo.DocReceivingFormDtlVO;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MBInvestigationRequisitionDetailVO;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MbLocationEligiblityMstVO;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.MedicalBoardBillingVO;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.MedicalBoardExternalReferVO;
import hisglobal.vo.MedicalBoardInvestigationMappingVO;
import hisglobal.vo.MedicalBoardMasterVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.MedicalBoardVerificationDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.SlideBasedTest;
import hisglobal.vo.Test;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import investigation.InvestigationStaticConfigurator;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import medicalboard.MedicalBoardConfig;
//import oracle.jdbc.driver.OracleTypes;
//import oracle.sql.ArrayDescriptor;
//import oracle.sql.StructDescriptor;
import registration.RegistrationConfig;


public class MbTransactionEssentialDAO extends DataAccessObject implements MbTransactionEssentialDAOi
{
	 
	/**
	 * Constructor for Setting Transaction Context
	 */
	public MbTransactionEssentialDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		
	}

	public List getPatientCategory(UserVO userVO)
	{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.CATEGORY.GBLT_PATIENT_CAT_MST.PRIMARY";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
					
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), RegistrationConfig.PATIENT_CAT_TYPE_PRIMARY);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next()) throw new HisRecordNotFoundException(
						"No Records for Patient Category found. Either there are No Records in Database or No Records against your Seat Id exist  ");
			}
			catch (Exception e)
			{

				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}

			List alRecord = new ArrayList();

			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{

				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}

	
	
/*	public List getCertificateType(UserVO userVO)
	{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.CERTIFCATE.HMBT_CERTIFICATE_TYPE_MST";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next()) throw new HisRecordNotFoundException(
						"No Records for Patient Category found. Either there are No Records in Database or No Records against your Seat Id exist  ");
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = new ArrayList();
			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}
		*/
	
	
	public MbCertificateTypeMstVO[] getCertificateType(UserVO userVO)
	{
		MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CERTIFCATE.HMBT_CERTIFICATE_TYPE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		// populateMAP.put(sq.next(), userVO.getHospitalCode());
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(MbCertificateTypeMstVO.class, rs);
			mCertificateTypeMstVOs = new MbCertificateTypeMstVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				mCertificateTypeMstVOs[i] = (MbCertificateTypeMstVO) vo[i];
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
		return mCertificateTypeMstVOs;
	}

	
	
	
	
	public List getOrganisationName(UserVO userVO)
	{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.ORGANISATION.HMBT_ORGANIZATION_MST";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next()) throw new HisRecordNotFoundException(
						"No Organisation Found");
			}
			catch (Exception e)
			{

				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}

			List alRecord = new ArrayList();

			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}
	
	
/*	public List getDepartmentDetail(UserVO userVO)
	{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT_DEPARTMENT.HMBT_BOARD_CONFIGURATION";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next()) throw new HisRecordNotFoundException(
						"No Records for Department found. Either there are No Records in Database or No Records against your Seat Id exist  ");
			}
			catch (Exception e)
			{

				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}

			List alRecord = new ArrayList();

			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}*/
	
	
		
	public MedicalBoardChecklistVO[] getAllChecklistDetails(String certificateType,UserVO userVO)
	{
		MedicalBoardChecklistVO[] arrChecklistDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey = "CHECKLIST_FOR_MEDICALBOARD.HMBT_CHECKLIST_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), certificateType);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Checklist found for this Certificate type");
			}
			rs.beforeFirst();*/

			vo = HelperMethods.populateVOfrmRS(MedicalBoardChecklistVO.class, rs);
			arrChecklistDtlVO = new MedicalBoardChecklistVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrChecklistDtlVO[i] = (MedicalBoardChecklistVO) vo[i];
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

		return arrChecklistDtlVO;
	}


	public BoardConfigurationVO getDepartmentDetail(UserVO userVO)
	{
		BoardConfigurationVO bConfigurationVO=new BoardConfigurationVO();
		
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey="SELECT_DEPARTMENT_DETAIL.HMBT_BOARD_CONFIGURATION"; 
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		Connection conn =super.getTransactionContext().getConnection();
		try
		{
	 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
	 	    if(!rs.next())
	 	    {
	 	    	throw new HisRecordNotFoundException();	 	    
	 	    }
	 	    else
	 	    {
	 	    	rs.beforeFirst();
	 	    	while(rs.next()) { HelperMethods.populateVOfrmRS(bConfigurationVO,rs); }
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
		return bConfigurationVO;
	}

	
	public MbOrganizationMstVO getOrganizationDetail(String orgId,UserVO userVO)
	{
		MbOrganizationMstVO mOrganizationMstVO=new MbOrganizationMstVO();
		
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey="SELECT_ORG_DTL.HMBT_ORGANIZATION_MST"; 
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),orgId);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		Connection conn =super.getTransactionContext().getConnection();
		try
		{
	 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
	 	    if(!rs.next())
	 	    { throw new HisRecordNotFoundException();}
	 	    else
	 	    {   rs.beforeFirst();
	 	    	while(rs.next()) { HelperMethods.populateVOfrmRS(mOrganizationMstVO,rs); }
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
		return mOrganizationMstVO;
	}

	
	/**
	 * get the list of the patient who are registered today in medical board
	 */
	public List getPatientListForRequisition(UserVO userVO)
	{
		List<PatientVO> list=null;
		PatientVO patientVo=new PatientVO();
			
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey="SELECT.PATIENT.HRGT_EPISODE_DTL"; 
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		Connection conn =super.getTransactionContext().getConnection();
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if(rs.next()){
				list=new ArrayList<PatientVO>();
			}
			rs.beforeFirst();
			while(rs.next()) {
				patientVo=new PatientVO();
				HelperMethods.populateVOfrmRS(patientVo,rs);
				list.add(patientVo);
			}
			
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return list;
	}
	
	
	/**
	 * get the detail of the patient who are registered in medical board
	 */
	public PatientVO getPatientForRequisitionByCrNo(String crNo,UserVO userVO)
	{
		PatientVO patientVO=new PatientVO();
		
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey="SELECT.PATIENT_BY_CRNO.HRGT_EPISODE_DTL"; 
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		populateMap.put(sq.next(),crNo);
		
		Connection conn =super.getTransactionContext().getConnection();
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if(!rs.next()){
				throw new HisRecordNotFoundException("Patient Not Registered in Medical Board");
			}
			rs.beforeFirst();
			HelperMethods.populateVOfrmRS(patientVO,rs);
			
		}
		catch(Exception e)
		{	
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else  throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return patientVO;
	}
	
	/**
	 * gets the list of the certificate type
	 */
	
	public MbCertificateTypeMstVO[] getCertificateTypeForRequisition(UserVO userVO)
	{
		MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CERTIFICATE_FOR_REQUISITION.HMBT_CERTIFICATE_TYPE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
			 populateMAP.put(sq.next(), RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL);
			 populateMAP.put(sq.next(), MedicalBoardConfig.CHARGE_TYPE_ID_OPD);//charge type id
			 populateMAP.put(sq.next(), MedicalBoardConfig.CHARGE_TYPE_ID_IPD);//ipd charge type id
			 populateMAP.put(sq.next(), MedicalBoardConfig.SERVICE_ID);//service id
			 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			 populateMAP.put(sq.next(), userVO.getHospitalCode());
		    //populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Certificate Found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(MbCertificateTypeMstVO.class, rs);
			mCertificateTypeMstVOs = new MbCertificateTypeMstVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				mCertificateTypeMstVOs[i] = (MbCertificateTypeMstVO) vo[i];
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
		return mCertificateTypeMstVOs;
	}
	
	
	/* **check for exisiting requition if exists for the crno and certificate type	 */
	
	public int checkDuplicateRequisitionForCertitificateType(String patCrNo,String certtificateTypeID,UserVO userVO)
	{
//		MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;
//		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.COUNT_BY_CRNO.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), patCrNo);
		populateMAP.put(sq.next(), certtificateTypeID);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_REQUEST_RAISED);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_REQUEST_ACCEPTED);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_ATTENDENCE_ON_VISIT_DATE);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_MEDICAL_PERFORMED);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_PARTIAL_PERFORMED);
		
		int count=0;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.next();
			count=rs.getInt(1);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return count;
	}

	
	/**
	 * get the details of the location which has been bound by the certificate type
	 */
	public List<MbLocationEligiblityMstVO> getLocationEligliblity(MbCertificateTypeMstVO certificateTypeMstVO,UserVO userVO)
	{
		MbLocationEligiblityMstVO mCertificateTypeMstVOs = null;
		List<MbLocationEligiblityMstVO> locationEligiblityMstVOList=new ArrayList<MbLocationEligiblityMstVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.HMBT_LOCATION_ELIGIBILITY_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
	
		populateMAP.put(sq.next(),certificateTypeMstVO.getCertificateTypeID());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while(rs.next()){
				mCertificateTypeMstVOs=new MbLocationEligiblityMstVO();
				HelperMethods.populateVOfrmRS(mCertificateTypeMstVOs, rs);
				locationEligiblityMstVOList.add(mCertificateTypeMstVOs);
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
		return locationEligiblityMstVOList;
	}
	
	
	/**
	 * get the list of the schedule of unit for medical board requisition
	 */
	public List getScheduleForRequisition(MbCertificateTypeMstVO certificateTypeMstVO,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.SCHEDULE.HOPT_DEPT_UNIT_ROSTER_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),certificateTypeMstVO.getCertificateTypeID());
		//populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(),certificateTypeMstVO.getCertificateTypeID());
		//populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(),certificateTypeMstVO.getCertificateTypeID());
		populateMAP.put(sq.next(),MedicalBoardConfig.DEPARTUNITCODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Schedule Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	/**
	 * get the list of the CID No's of unit for medical board requisition
	 */
	/*public List getCIDNoListForRequisition(MbCertificateTypeMstVO certificateTypeMstVO,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.SCHEDULE.HOPT_DEPT_UNIT_ROSTER_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),certificateTypeMstVO.getCertificateTypeID());
		populateMAP.put(sq.next(),certificateTypeMstVO.getCertificateTypeID());
		populateMAP.put(sq.next(),certificateTypeMstVO.getCertificateTypeID());
		populateMAP.put(sq.next(),certificateTypeMstVO.getDepartmentUnitCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Schedule Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}*/
	
	public List getCIDNoListForRequisition(MbCertificateTypeMstVO certificateTypeMstVO, UserVO userVO)
	{
		List DocReceivingFormDtlVOLst = new ArrayList();
		DocReceivingFormDtlVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey = "SELECT.CIDNO_FOR_MEDICAL_BOARD.FTS_DOC_RECEIVING_FORM_TRN";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), MedicalBoardConfig.FTS_NUM_CATEGORY_ID_DISABILITY_CERTIFICATE);
		populateMAP.put(sq.next(), MedicalBoardConfig.FTS_NUM_CATEGORY_ID_MEDICAL_CERTIFICATE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("Patient is either not addmited or not discharged.");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new DocReceivingFormDtlVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				DocReceivingFormDtlVOLst.add(vo);
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

		return DocReceivingFormDtlVOLst;
	}
	
	public List getBillingDetail(String patCrNo,UserVO userVO)
	{
		MedicalBoardBillingVO billingVO = null;
		List<MedicalBoardBillingVO> billingVOList=new ArrayList<MedicalBoardBillingVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.BILL_DETAIL.HBLT_BILL_SERVICE_DTL";
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
		populateMAP.put(sq.next(),MedicalBoardConfig.HBLNUM_STATUS);
		populateMAP.put(sq.next(),MedicalBoardConfig.CHARGE_TYPE_ID_OPD);
		populateMAP.put(sq.next(),MedicalBoardConfig.BSERVICE_ID);
		populateMAP.put(sq.next(),MedicalBoardConfig.SERVICE_ID);
		populateMAP.put(sq.next(),MedicalBoardConfig.TRANS_TYPE);
		populateMAP.put(sq.next(),patCrNo);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while(rs.next()){
				billingVO=new MedicalBoardBillingVO();
				HelperMethods.populateVOfrmRS(billingVO, rs);
				billingVOList.add(billingVO);
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
		return billingVOList;
	}
	
	public void updateBillingQuantity(MedicalBoardBillingVO billingVO,UserVO userVO)
	{
		//String query = "";
		//Map populateMAP = new HashMap();
		//Sequence sq = new Sequence();
		//String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		//String queryKey ="SELECT.BILL_DETAIL.HBLT_BILL_SERVICE_DTL";
					
		try
		{
			Procedure strProc = new Procedure(MedicalBoardConfig.PROCEDURE_UPDATE_QTY);
			strProc.addInParameter(1, Types.NUMERIC, userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, billingVO.getTarriff().trim());
			strProc.addInParameter(3, Types.NUMERIC, billingVO.getServiceId().trim());
			strProc.addInParameter(4, Types.NUMERIC, billingVO.getBillNo().trim());
			strProc.addInParameter(5, Types.NUMERIC, billingVO.getRemainedQuantity().trim());
			strProc.addOutParameter(6, Types.VARCHAR);
			strProc.addOutParameter(7, Types.VARCHAR);
			strProc.execute(super.getTransactionContext().getConnection());
			//strProc.getParameterAt(7);
			System.out.println("No Of record Updated :" +strProc.getParameterAt(7));	
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
	}
	
	
	
	
	
	/**
	 * get the list of the schedules of the requisition by crno
	 */
	public List getPatientMbRequisitionsByCrNo(String crNo,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.BY_CRNO.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),crNo);
		populateMAP.put(sq.next(),MedicalBoardConfig.REQUEST_STATUS_REQUEST_ACCEPTED);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Requisition Schedule Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	/**
	 *  get the list of the schedules of the requisition by organisation
	 */
	public List getMbRequisitionsByOrg(String orgId,String orgName,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.BY_ORG.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(orgId.equals(MedicalBoardConfig.ORGANISATION_ID_OTHER)){
				query+=" and HMBSTR_ORG_NAME like ? " ;
				orgName+="%";
			}
			else{
				query+=" and HMBNUM_ORGANIZATION_ID = ? " ;
				orgName=orgId;
			}
			
			query+=" order by hmbdt_exam_date " ;
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
			populateMAP.put(sq.next(),MedicalBoardConfig.REQUEST_STATUS_REQUEST_ACCEPTED);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), orgName);
			
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Requisition Schedule Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	/**
	 *  get the list of the certificate Type for which candidate has been scheduled today
	 */
	public List getCertificateTypeList(UserVO userVO,String date)
	{
		List certificateTypeList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CERTIFICATE_TYPE.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), date);

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_REQUEST_ACCEPTED);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_PARTIAL_PERFORMED);
				
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No Certificate Type Found");
				throw new HisRecordNotFoundException("No Candidate Scheduled for the Date");
			}
			rs.beforeFirst();
			certificateTypeList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return certificateTypeList;
	}
	
	
	/**
	 *  get the list of the candidate who are schedule today for a particular certificate Type
	 */
	public List getcandidateListByCerficateType(String certificateTypeId,UserVO userVO, String examDate)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.PATIENT_FOR_MB_ACCEPTANCE.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), examDate);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_REQUEST_ACCEPTED);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_PARTIAL_PERFORMED);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Candidate Scheduled for "+examDate);
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	
	/**
	 *  get the list of the candidate who are schedule today for a particular certificate Type
	 */
	public List getcandidateListByCerficateTypePrevdate(String certificateTypeId,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.PATIENT_FOR_MB_ACCEPTANCE_PREV_DATE.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), "4-Jun-2013");
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_REQUEST_ACCEPTED);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_PARTIAL_PERFORMED);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Candidate Scheduled Today");
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	
	/**
	 *  get the list of the board which are available today for a particular certificate Type
	 */
	public List getBoardListByCerficateType(String certificateTypeId,UserVO userVO,String date)
	{
		List boardList=null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.BOARD_NO.BOARD_NAME.HMBT_BOARD_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), date);
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), MedicalBoardConfig.BOARD_STATUS_INPROCESS);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Board Found");
			}*/
			if(rs.next()){
				boardList=new ArrayList();
			}
			rs.beforeFirst();
			boardList=HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return boardList;
	}
	
	
	/**
	 *  get the list of the checklist compulsory at acceptance
	 */
	public List getCheckListForAcceptance(String certificateTypeId,String reqID,UserVO userVO)
	{
		MedicalBoardChecklistVO checkListVO = null;
		List<MedicalBoardChecklistVO> checkListVOList=new ArrayList<MedicalBoardChecklistVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CHECKLIST_COMPULSORY_AT_ACCEPTANCE.HMBT_CERTIFICATE_CHECKLIST_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), MedicalBoardConfig.COMPULSORY_AT_TIME_OF_ACCEPTANCE);
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), reqID);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Certificate Type Found");
			}*/
			while(rs.next()){
				checkListVO=new MedicalBoardChecklistVO();
				HelperMethods.populateVOfrmRS(checkListVO, rs);
				checkListVOList.add(checkListVO);
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
		return checkListVOList;
	}
	
	

	
	//// medical Board Initialization // TO GET CERTIFICATE dETAIL
	
	public MbCertificateTypeMstVO[] getCertificateDetail(UserVO userVO)
	{
		MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT_CERTIFICATE_DETAIL.HMBT_CERTIFICATE_TYPE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		 populateMAP.put(sq.next(), MedicalBoardConfig.REQUISITION_STATUS_ACCEPTED);
		 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		// populateMAP.put(sq.next(), userVO.getHospitalCode());
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(MbCertificateTypeMstVO.class, rs);
			mCertificateTypeMstVOs = new MbCertificateTypeMstVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				mCertificateTypeMstVOs[i] = (MbCertificateTypeMstVO) vo[i];
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
		return mCertificateTypeMstVOs;
	}

	
	/// To get selected boards//
	
	
	public List getSelectedBoards(String certificateTypeId,UserVO userVO)
	{
		List boardList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT_BOARDS.HMBT_CERTIFICATE_BOARD_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Board Found For Certificate Type");
			}
			rs.beforeFirst();
			boardList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return boardList;
	}
	
	
	
	

	/**
	 *  get the Episode detail by crno
	 */
	public EpisodeVO getEpisodeDetailByCrNo(String crNo,UserVO userVO)
	{
		EpisodeVO episodeVO=new EpisodeVO();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.EPISODE_DTL.BY_CRNO.HRGT_EPISODE_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),crNo);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),userVO.getHospitalCode());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			HelperMethods.populateVOfrmRS(episodeVO, rs);
				
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return episodeVO;
	}
	
	/**
	 *  get the Episode Code by crno
	 */
	public EpisodeVO getEpisodeCodeByCrNo(String crNo,UserVO userVO)
	{
		EpisodeVO episodeVO=new EpisodeVO();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.MAX_EPISODE_CODE.HRGT_EPISODE_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),crNo);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),userVO.getHospitalCode());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			HelperMethods.populateVOfrmRS(episodeVO, rs);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return episodeVO;
	}
	
	
	/* **********************************Medical Exam ***************************************************/
	
	/**
	 * get the list of the certificate type for which patient has attended today
	 */
	public List getCertificateTypeListForMedicalExam(UserVO userVO)
	{
		List certificateTypeList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CERTIFICATE_TYPE_FOR_MEDICAL_EXAM.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_ATTENDENCE_ON_VISIT_DATE);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Candidate Attended Today for Medical Examination");
			}
			rs.beforeFirst();
			certificateTypeList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return certificateTypeList;
	}
	
	
	/**
	 *  get the list of the candidate who attended today for a particular certificate Type
	 *  and whose medical is not yet performed
	 */
	public List getCandidateListForMedicalByCerficateType(String certificateTypeId,String reqStaus,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.PATIENT_FOR_MB_EXAM.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), reqStaus);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Certificate Type Found");
			}*/
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	
	/**
	 *  get the department unit code of the medical board
	 */
	public String getMedicalBoardDepartment(UserVO userVO)
	{
		String departmentUnitCode="";
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.HMBT_BOARD_CONFIGURATION";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				departmentUnitCode=rs.getString(1);	
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
		return departmentUnitCode;
	}
	

	public List getAllCertificateTypeList(UserVO userVO)
	{
		List certificateTypeList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.ALL_CERTIFICATE_TYPE.HMBT_CERTIFICATE_TYPE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No Accepted Candidate Found");
			}
			rs.beforeFirst();
			certificateTypeList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return certificateTypeList;
	}
	
	public List getScheduleList(MbCertificateTypeMstVO certificateTypeMstVO,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.SCHEDULE_DATE_LIST.HOPT_DEPT_UNIT_ROSTER_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),MedicalBoardConfig.REQUEST_STATUS_REQUEST_ACCEPTED);
		populateMAP.put(sq.next(),certificateTypeMstVO.getCertificateTypeID());
		populateMAP.put(sq.next(),certificateTypeMstVO.getCertificateTypeID());
		populateMAP.put(sq.next(),MedicalBoardConfig.DEPARTUNITCODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Schedule Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	public List getBoardDetailByCerficateType(String certificateTypeId,UserVO userVO)
	{
		MedicalBoardMasterVO medicalBoardMasterVO = null;
		List boardDetailList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.BOARD_DETAIL_LIST.HMBT_CERTIFICATE_BOARD_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), MedicalBoardConfig.TABLE_ID_CERTIFICATE_TYPE_MST);
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Certificate Type Found");
			}*/
			while(rs.next()){
				medicalBoardMasterVO=new MedicalBoardMasterVO();
				HelperMethods.populateVOfrmRS(medicalBoardMasterVO, rs);
				boardDetailList.add(medicalBoardMasterVO);
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
		return boardDetailList;
	}
	
	
	public List getTeamDetailByBoardId(String boardId,UserVO _UserVO)
	{
		BoardTeamDetailVO boardTeamDetailVO = null;
		List teamDetailList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.TEAM_DETAIL_LIST.HMBT_BOARD_TEAM_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), boardId);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Certificate Type Found");
			}*/
			while(rs.next()){
				boardTeamDetailVO=new BoardTeamDetailVO();
				HelperMethods.populateVOfrmRS(boardTeamDetailVO, rs);
				teamDetailList.add(boardTeamDetailVO);
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
		return teamDetailList;
	}
	
	public List getAssignBoardDetailList(String certificateTypeId,String scheduleDate,UserVO userVO)
	{
		BoardDetailVO boardDetailVO = null;
		List boardDetailList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.ASSIGN_BOARD_LIST.HMBT_BOARD_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), scheduleDate);
		populateMAP.put(sq.next(), MedicalBoardConfig.BOARD_INPROCESS);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Certificate Type Found");
			}*/
			while(rs.next()){
				boardDetailVO=new BoardDetailVO();
				HelperMethods.populateVOfrmRS(boardDetailVO, rs);
				boardDetailList.add(boardDetailVO);
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
		return boardDetailList;
	}
	
	public List getAvailableBoardDetailList(String certificateTypeId,String scheduleDate,UserVO userVO)
	{
		MedicalBoardMasterVO medicalBoardMasterVO = null;
		List boardDetailList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.NEW_BOARD_DETAIL_LIST.HMBT_CERTIFICATE_BOARD_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), scheduleDate);
		populateMAP.put(sq.next(), MedicalBoardConfig.BOARD_INPROCESS);
		
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), MedicalBoardConfig.TABLE_ID_CERTIFICATE_TYPE_MST);
		populateMAP.put(sq.next(), certificateTypeId);
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Certificate Type Found");
			}*/
			while(rs.next()){
				medicalBoardMasterVO=new MedicalBoardMasterVO();
				HelperMethods.populateVOfrmRS(medicalBoardMasterVO, rs);
				boardDetailList.add(medicalBoardMasterVO);
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
		return boardDetailList;
	}
	
	public List getAssignTeamDetail(String boardNo,UserVO _UserVO)
	{
		BoardTeamDetailVO boardTeamDetailVO = null;
		List teamDetailList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.ASSIGN_TEAM_DETAIL.HMBT_BOARD_TEAM_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), boardNo);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Certificate Type Found");
			}*/
			while(rs.next()){
				boardTeamDetailVO=new BoardTeamDetailVO();
				HelperMethods.populateVOfrmRS(boardTeamDetailVO, rs);
				teamDetailList.add(boardTeamDetailVO);
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
		return teamDetailList;
	}
	

	/**
	 *  get the detail of the referral of the candidate from medical board
	 */
	public List<EpisodeRefDtlVO> getMBCandidateRefDetail(String crNo,UserVO userVO)
	{
		List<EpisodeRefDtlVO> episodeRefDtlVOList=null;
		EpisodeRefDtlVO episodeRefDtlVO;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.REF_DTL_FROM_MEDICAL_BOARD.HRGT_EPISODE_REF_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				episodeRefDtlVOList=new ArrayList<EpisodeRefDtlVO>();	
			}
			/*else{
				throw new HisRecordNotFoundException("No Refer Detail Found");
			}*/
			rs.beforeFirst();
			while(rs.next()){
				episodeRefDtlVO=new EpisodeRefDtlVO();
				HelperMethods.populateVOfrmRS(episodeRefDtlVO, rs);
				episodeRefDtlVOList.add(episodeRefDtlVO);
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
		return episodeRefDtlVOList;
	}
	
	/**
	 *  get the investigation based on the certificate type
	 */
	public List<MedicalBoardInvestigationMappingVO> getMBInvestigationMapping(String certificateTypeID,UserVO userVO)
	{
		List<MedicalBoardInvestigationMappingVO> investigationMappingVOList=null;
		MedicalBoardInvestigationMappingVO investigationMappingVO;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.LAB_TEST.HMBT_INVESTIGATION_MAPPING";
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
		populateMAP.put(sq.next(), certificateTypeID);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				investigationMappingVOList=new ArrayList<MedicalBoardInvestigationMappingVO>();	
			}
			else{
				throw new HisRecordNotFoundException("No Investigation Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				investigationMappingVO=new MedicalBoardInvestigationMappingVO();
				HelperMethods.populateVOfrmRS(investigationMappingVO, rs);
				investigationMappingVOList.add(investigationMappingVO);
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
		return investigationMappingVOList;
	}

	
	/**
	 *  get the list of samples 
	 */
	public List getMBInvestigationTestSamples(UserVO userVO)
	{
		List sampleList;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		
		//investigation module has stopped using this table 
		///chnage done on 13-feb-2012 by Prakhar Misra
		//String queryKey ="SELECT.SAMPLE.HIVT_TEST_SAMPLE_MST";
		
		String queryKey ="SELECT.SAMPLE.HIVT_LABTEST_SAMPLE_MST";
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
		populateMAP.put(sq.next(), MedicalBoardConfig.GROUP_CODE);
		populateMAP.put(sq.next(), MedicalBoardConfig.IS_GROUP_CODE);
				
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				sampleList=new ArrayList();	
			}
			//else{
				///throw new HisRecordNotFoundException("No Investigation Found");
			//}
			rs.beforeFirst();
			sampleList=HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return sampleList;
	}
	
	
	/**
	 *  get the Episode detail by crno, Episode Code and visit no
	 */
	public EpisodeVO getEpisodeDetailByEpisodeCode(EpisodeVO episodeVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.EPISODE_DTL.BY_EPISODE_CODE_VIST_NO.HRGT_EPISODE_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),episodeVO.getPatCrNo());
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),userVO.getHospitalCode());
		populateMAP.put(sq.next(),episodeVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(),episodeVO.getEpisodeCode());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			HelperMethods.populateVOfrmRS(episodeVO, rs);
				
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return episodeVO;
	}
	
	/**
	 *  get the Patient detail by crno, Episode Code and visit no
	 */
	public PatientDetailVO getPatientDetailByEpisodeCode(PatientDetailVO patientDtlVO,UserVO userVO)
	{
		//PatientDetailVO patientDtlVO=new PatientDetailVO();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.PATIENT_DTL.HRGT_PATIENT_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),patientDtlVO.getPatCrNo());
		populateMAP.put(sq.next(),patientDtlVO.getEpisodeCode());
		populateMAP.put(sq.next(),patientDtlVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(),userVO.getHospitalCode());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			HelperMethods.populateVOfrmRS(patientDtlVO, rs);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return patientDtlVO;
	}
	
	
	public List<Test> getTestDetail(String []labTestCode,UserVO userVO)
	{
		List<Test> testDtlList=null;
//		ResultSet rs;
//		ResultSet sampleRS;
		try
		{
			Procedure strProc = new Procedure(MedicalBoardConfig.INVESTIGATION_TEST_DTL_PROCEDURE);
			strProc.addInParameter(1, Types.NUMERIC, labTestCode[0]);
			strProc.addInParameter(2, Types.NUMERIC, userVO.getHospitalCode());
			strProc.addOutParameter(3,Types.REF);
			strProc.addOutParameter(5,Types.REF);
			
			strProc.execute(getTransactionContext().getConnection());
//			rs=(ResultSet)strProc.getParameterAt(3);
//			sampleRS=(ResultSet)strProc.getParameterAt(5);

			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return testDtlList;
	}
	
	
	//Raise Investigation
	public String[] raiseMBInvestigationRequest(PatientDetailVO _patVO, EpisodeVO _episodeVO, List<Test> _lstTestDtl,
	String _sysDate, String _reqArea, String _reqType, UserVO _userVO)
	{
		Connection conn = null;
		CallableStatement cstmt = null;
		String[] collectedRequisitionNosArray = null;
		try
		{
		//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.edb.Driver");
		conn = DriverManager.getConnection(Conn.getDatabaseConnection(),InvestigationStaticConfigurator.databaseusername,InvestigationStaticConfigurator.databasepassword);
		//conn = super.getTransactionContext().getConnection();
		
		conn.setAutoCommit(false);
		// PROCEDURE Inv_Requisition_Raising.requisitionRaisingInsidePat(requisitionArray IN INVREQUCOLLECTION,patientVo
		// IN INVPATIENTDETAIL,episodeVo IN INVEPISODEDETAIL,hospitalCode IN VARCHAR2,seatid IN VARCHAR2,errorMsgCode OUT
		// VARCHAR2,errorMsg OUT VARCHAR2,outputRequisitionArrayString OUT VARCHAR2)
		cstmt = conn.prepareCall("{call INV_REQUISITION_RAISING.requisitionRaisingInsidePat(?,?,?,?,?,?,?,?)}");
		 
		// Parameter 1 : requisitionArray IN INVREQUCOLLECTION
		ArrayDescriptor requisitionCollectionSD = ArrayDescriptor.createDescriptor("INVREQUCOLLECTION",conn);
		StructDescriptor requisitionSD = StructDescriptor.createDescriptor("REQUISITION", conn);
		StructDescriptor testSD = StructDescriptor.createDescriptor("INVTEST", conn);
		StructDescriptor multiSessionSD = StructDescriptor.createDescriptor("MULTISESSIONALDETAIL", conn); 
		//-N- StructDescriptor mandatorySD = StructDescriptor.createDescriptor("MANDATORY", conn);
		ArrayDescriptor multiSessionCollectionSD = ArrayDescriptor.createDescriptor("MULSESSIONCOL", conn); 
		ArrayDescriptor testCollectionSD = ArrayDescriptor.createDescriptor("INVTESTCOLLECTION", conn);
		//-N- ArrayDescriptor mandatoryCollectionAD = ArrayDescriptor.createDescriptor("MANDATORYCOL", conn);
		List<Object> lstRequisitionCollection = new ArrayList<Object>(); 
		Object requisitionAttributes[] = new Object[8];
		int requisitionIndex = 0;
		//for (Requisition requisition : requisitionCollection)
		Iterator<Test> itrTestsDtl = _lstTestDtl.iterator();
		Test test = null;
		while(itrTestsDtl.hasNext())
		{
		// Set Requisition Details
		//-- requisitionAttributes[0] //REQUISITIONNO VARCHAR2(255),
		requisitionAttributes[1] = _sysDate; //REQUISITIONDATE VARCHAR2(255),
		requisitionAttributes[2] = _reqArea; //RAISINGAREA VARCHAR2(255),
		//-- requisitionAttributes[3] //REMARKS VARCHAR2(255),
		requisitionAttributes[4] = _reqType; //REQUISITIONTYPE VARCHAR2(255),
		//requisitionAttributes[5] //TESTS INVTESTCOLLECTION,
		requisitionAttributes[6] = _userVO.getUserEmpID(); //requisitionOrderedBy VARCHAR2(255),
		requisitionAttributes[7] = ""; //requisitionOrderedThrough VARCHAR2(255)
		// Set Test Details at 5
		int testIndex = 0;
		String labCode = null;
		List<Object> lstTestCollection = new ArrayList<Object>();
		if(test==null) test = itrTestsDtl.next();
		labCode = test.getLaboratoryCode();
		while(test!=null && labCode.equalsIgnoreCase(test.getLaboratoryCode()))
		{
		Object testAttributes[] = new Object[45];
		testAttributes[0] = test.getTestType(); //TESTTYPE VARCHAR2(255),
		testAttributes[1] = test.getIsMultiSessionTest(); //ISMULTISESSIONTEST VARCHAR2(255),
		testAttributes[2] = test.getIsAppointmentBased(); //ISAPPOINTMENTBASED VARCHAR2(255),
		testAttributes[3] = test.getMandatoryInfoPresent(); //MANDATORYINFOPRESENT VARCHAR2(255),
		testAttributes[4] = ""; //WORKORDERNO VARCHAR2(255),
		testAttributes[5] = ""; //REQUISITIONTYPE VARCHAR2(255),
		testAttributes[6] = ""; //REQUISITIONDNO VARCHAR2(255),
		testAttributes[7] = ""; //TESTREMARKS VARCHAR2(255),
		testAttributes[8] = ""; //APPOINTMENTSLOTENDTIME VARCHAR2(255),
		testAttributes[9] = ""; //APPOINTMENTSLOTSTARTTIME VARCHAR2(255),
		testAttributes[10] = ""; //APPOINTMENTSLOTREFERENCEID VARCHAR2(255),
		testAttributes[11] = test.getProposedTestDate(); //PROPOSEDTESTDATE VARCHAR2(255),
		testAttributes[12] = ""; //SELECTEDLAB VARCHAR2(255),
		testAttributes[13] = test.getLaboratoryCode(); //LABORATORYCODE VARCHAR2(255),
		testAttributes[14] = test.getLaboratoryName(); //LABORATORYNAME VARCHAR2(255),
		testAttributes[15] = test.getPriority(); //PRIORITY VARCHAR2(255),
		testAttributes[16] = ""; //GROUPTYPENAME VARCHAR2(255),
		testAttributes[17] = ""; //GROUPTYPE VARCHAR2(255),
		testAttributes[18] = ""; //GROUPCODE VARCHAR2(255),
		testAttributes[19] = ""; //GROUPNAME VARCHAR2(255),
		testAttributes[20] = test.getTestCode(); //TESTCODE VARCHAR2(255),
		testAttributes[21] = test.getTestName(); //TESTNAME VARCHAR2(255),
		//testAttributes[22] //MULTISESSIONALDETAILLIST MULSESSIONCOL,
		//***testAttributes[41] = test.getAssociatedSampleNo(); //AssociatedSampleNo
		testAttributes[39] = test.getRaisedGroupNo(); //RaisedGroupNo
		// Collect the multisession array
		/*if (test.getIsMultiSessionTest().equals("1"))
		{
		// set test with multisession details
		int multiSessionIndex = 0;
		Object[] multiSessionCollectionAttributes = new Object[test.getMultisessionalDetailList().size()];
		// multi session object sessionNo VARCHAR2(2),
		// proposedTestDate VARCHAR2(15),
		// appointmentSlotReferenceId VARCHAR2(2),
		// appointmentSlotStartTime VARCHAR2(5),
		// appointmentSlotEndTime
		for (MultiSessionDetail msd : test.getMultisessionalDetailList())
		{
		Object multiSessionAttributes[] = new Object[5];
		if (test.getIsAppointmentBased().equals("1"))
		{
		// set MultiSession detail
		multiSessionAttributes[0] = msd.getSessionNo();
		multiSessionAttributes[1] = msd.getProposedTestDate();
		multiSessionAttributes[2] = msd.getAppointmentSlotReferenceId();
		multiSessionAttributes[3] = msd.getAppointmentSlotStartTime();
		multiSessionAttributes[4] = msd.getAppointmentSlotEndTime();
		}
		else
		{
		multiSessionAttributes[0] = msd.getSessionNo();
		multiSessionAttributes[1] = msd.getProposedTestDate();
		multiSessionAttributes[2] = "";
		multiSessionAttributes[3] = msd.getAppointmentSlotStartTime();
		multiSessionAttributes[4] = "";
		}
		multiSessionCollectionAttributes[multiSessionIndex++] = new oracle.sql.STRUCT(multiSessionSD, conn,
		multiSessionAttributes);
		}
		testAttributes[22] = new oracle.sql.ARRAY(multiSessionCollectionSD, conn, multiSessionCollectionAttributes);
		if (test.getTestType().equals("S"))
		{
		SlideBasedTest sbTest = (SlideBasedTest) test;
		testAttributes[27] = "";
		testAttributes[28] = sbTest.getSelectedSpecimenCode();
		testAttributes[29] = (sbTest.getSelectedSpecimenGroupCode() != null) ? sbTest.getSelectedSpecimenGroupCode() : "";
		testAttributes[30] = "";
		testAttributes[31] = (sbTest.getSpecimenId() != null) ? sbTest.getSpecimenId() : "";
		testAttributes[32] = "";
		}
		else if (test.getTestType().equals("I"))
		{
		SlideBasedTest sbTest = (SlideBasedTest) test;
		testAttributes[27] = sbTest.getSelectedSpecimenCode();
		testAttributes[28] = "";
		testAttributes[29] = "";
		testAttributes[30] = (sbTest.getSelectedSpecimenGroupCode() != null) ? sbTest.getSelectedSpecimenGroupCode() : "";
		testAttributes[31] = (sbTest.getSpecimenId() != null) ? sbTest.getSpecimenId() : "";
		testAttributes[32] = (sbTest.getTestComponentType() != null) ? sbTest.getTestComponentType() : "";
		}
		else
		{
		testAttributes[27] = "";
		testAttributes[28] = "";
		testAttributes[29] = "";
		testAttributes[30] = "";
		testAttributes[31] = "";
		testAttributes[32] = "";
		}
		}
		else
		{*/
		// Set test without multisession
		
		if (test.getTestType().equals("S"))
		{
		//System.out.println("test.getclass="+test.getClass());
		SlideBasedTest sbTest = (SlideBasedTest) test;
		testAttributes[27] = ""; //SelectedSpecimenCode-Slide
		//testAttributes[28] = sbTest.getSelectedSampleCode(); //SelectedSpecimenCode-Sample
		testAttributes[28] = sbTest.getSelectedSpecimenCode();
		testAttributes[29] = ""; // SelectedSpecimenGroupCode-Sample
		testAttributes[30] = ""; // SelectedSpecimenGroupCode-Slide
		testAttributes[31] = ""; // SpecimenId
		testAttributes[32] = ""; // TestComponentType-Slide
		}
		
		else if (test.getTestType().equals("I"))
		{
		SlideBasedTest sbTest = (SlideBasedTest) test;
		testAttributes[27] = sbTest.getSelectedSpecimenCode(); //SelectedSpecimenCode-Slide
		testAttributes[28] = ""; //SelectedSpecimenCode-Sample
		testAttributes[29] = ""; // SelectedSpecimenGroupCode-Sample
		testAttributes[30] = ""; // SelectedSpecimenGroupCode-Slide
		testAttributes[31] = ""; // SpecimenId
		testAttributes[32] = ""; // TestComponentType-Slide
		}
		else
		{
		testAttributes[27] = ""; //SelectedSpecimenCode-Slide
		testAttributes[28] = ""; //SelectedSpecimenCode-Sample
		testAttributes[29] = ""; // SelectedSpecimenGroupCode-Sample
		testAttributes[30] = ""; // SelectedSpecimenGroupCode-Slide
		testAttributes[31] = ""; // SpecimenId
		testAttributes[32] = ""; // TestComponentType-Slide
		}
		// Set Multisession Id at 22
		Object[] multiSessionCollectionAttributes = new Object[1];
		Object multiSessionAttributes[] = new Object[5];
		multiSessionAttributes[0] = ""; //SessionNo
		multiSessionAttributes[1] = ""; //ProposedTestDate
		multiSessionAttributes[2] = ""; //AppointmentSlotReferenceId
		multiSessionAttributes[3] = ""; //AppointmentSlotStartTime
		multiSessionAttributes[4] = ""; //AppointmentSlotEndTime
		multiSessionCollectionAttributes[0] = new oracle.sql.STRUCT(multiSessionSD, conn, multiSessionAttributes);
		testAttributes[22] = new oracle.sql.ARRAY(multiSessionCollectionSD, conn, multiSessionCollectionAttributes);
		/*}*/
		//-- testAttributes[23] //LaboratoryName
		// Mandatory details
		testAttributes[23] = ""; //IsMandatoryAssociated
		testAttributes[24] = ""; //DefaultDuration
		testAttributes[25] = ""; //DefaultNoOfSession
		testAttributes[26] = ""; //TestStatus
		// adding mandatory information detail
		/*if (test.getIsMandatoryAssociated().equals("1"))
		{
		Object[] mandatoryCollectionAttributes = new Object[test.getMandatoryInfoDtl().size()];
		int mandatoryIndex = 0;
		for (TemplateRepresentationClass templateRepresentationClass : test.getMandatoryInfoDtl())
		{
		Object[] mandatoryAttributes = new Object[3];
		mandatoryAttributes[0] = test.getRequisitionDNo();
		mandatoryAttributes[1] = templateRepresentationClass.getName();
		mandatoryAttributes[2] = templateRepresentationClass.getHtmlObjectValue();
		mandatoryCollectionAttributes[mandatoryIndex++] = new oracle.sql.STRUCT(mandatorySD, conn, mandatoryAttributes);
		}
		testAttributes[33] = new oracle.sql.ARRAY(mandatoryCollectionAD, conn, mandatoryCollectionAttributes);
		}*/
		/*if (test.getIsRequisitionFormrequired().equals("1"))
		{
		StringWriter sw = new StringWriter();
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		t.transform(new DOMSource(test.getTestDocument()), new StreamResult(sw));
		System.out.println("Duration call =" + oracle.sql.CLOB.DURATION_SESSION);
		oracle.sql.CLOB newClob = oracle.sql.CLOB.createTemporary(conn, false, 1);
		System.out.println("BO :" + sw.toString());
		newClob.setString(1, "<requisitionform>" + sw.toString() + "</requisitionform>");
		testAttributes[44] = newClob;
		}*/
		
		//testCollectionAttributes[testIndex++] = new oracle.sql.STRUCT(testSD, conn, testAttributes); 
		lstTestCollection.add(new oracle.sql.STRUCT(testSD, conn, testAttributes));
		testIndex++;
		if(itrTestsDtl.hasNext()) test = itrTestsDtl.next();
		else test = null;
		
		}
		// Adding test array to the requisition at 5
		Object[] testCollectionAttributes = lstTestCollection.toArray();
		requisitionAttributes[5] = new oracle.sql.ARRAY(testCollectionSD, conn, testCollectionAttributes);
		// adding requisition to requisitionCollection
		//requisitionCollectionAttributes[requisitionIndex++] = new oracle.sql.STRUCT(requisitionSD, conn, requisitionAttributes);
		lstRequisitionCollection.add(new oracle.sql.STRUCT(requisitionSD, conn, requisitionAttributes));
		requisitionIndex++;
		}
		Object[] requisitionCollectionAttributes = lstRequisitionCollection.toArray(); //new Object[requisitionMap.size()];
		cstmt.setArray(1, new oracle.sql.ARRAY(requisitionCollectionSD, conn, requisitionCollectionAttributes));
		 
		// Parameter 2 : patientVo IN INVPATIENTDETAIL Set patient oracle object
		StructDescriptor patientSD = StructDescriptor.createDescriptor("INVPATIENTDETAIL", conn);
		Object[] patientAttributes = new Object[27];
		patientAttributes[0] = _patVO.getPatCrNo(); //PATCRNO
		patientAttributes[1] = _patVO.getPatFirstName(); //PATFIRSTNAME
		patientAttributes[2] = _patVO.getPatDOB(); //PATDOB
		//****patientAttributes[3] = _patVO.getPatAgeUnit(); //PATAGEUNIT
		patientAttributes[4] = _patVO.getPatAge(); //PATAGE
		patientAttributes[5] = _patVO.getPatGenderCode(); //PATGENDERCODE
		patientAttributes[6] = _patVO.getPatGender(); //PATGENDER
		patientAttributes[7] = _patVO.getPatLastName(); //PATLASTNAME
		patientAttributes[8] = _patVO.getPatMiddleName(); //PATMIDDLENAME
		patientAttributes[9] = _patVO.getPatGuardianName(); //PATGUARDIANNAME
		patientAttributes[10] = _patVO.getPatHusbandName(); //PATHUSBANDNAME
		patientAttributes[11] = _patVO.getPatPrimaryCatCode(); //PATCATEGORYCODE
		patientAttributes[12] = _patVO.getPatPrimaryCat(); //PATCATEGORY
		patientAttributes[13] = _patVO.getIsActualDob(); //ISACTUALDOB
		patientAttributes[14] = _episodeVO.getWard(); //PATWARD
		patientAttributes[15] = _episodeVO.getDepartmentUnit(); //PATUNIT
		patientAttributes[16] = _patVO.getDepartment(); //PATDEPARTMENT
		patientAttributes[17] = _patVO.getPatStatus(); //PATSTATUS
		patientAttributes[18] = _patVO.getPatStatusCode(); 
		patientAttributes[21] = _episodeVO.getAdmissionNo();
		patientAttributes[22] = _episodeVO.getWardCode();
		/**patientAttributes[23] = _episodeVO.getRoomCode(); */
		patientAttributes[24] = _episodeVO.getRoom();
		//****patientAttributes[25] = _episodeVO.getBedCode();
		//****patientAttributes[26] = _episodeVO.getBedName();
		cstmt.setObject(2, new oracle.sql.STRUCT(patientSD, conn, patientAttributes));
		// Parameter 3 : episodeVo IN INVEPISODEDETAIL
		StructDescriptor episodeSD = StructDescriptor.createDescriptor("INVEPISODEDETAIL", conn);
		Object[] episodeAttributes = new Object[11];
		episodeAttributes[0] = _episodeVO.getPatCrNo();
		episodeAttributes[1] = _episodeVO.getMlcNo();
		episodeAttributes[2] = _episodeVO.getDepartmentUnit();
		episodeAttributes[3] = _episodeVO.getDepartmentUnitCode();
		episodeAttributes[4] = _episodeVO.getEpisodeVisitNo();
		episodeAttributes[5] = _episodeVO.getEpisodeCode();
		episodeAttributes[6] = _episodeVO.getEpisodeDate(); // Visit Date
		episodeAttributes[7] = _episodeVO.getMlcNo();
		//****episodeAttributes[8] = _episodeVO.getPatStatus();
		episodeAttributes[9] = _episodeVO.getEpisodeTypeCode();
		/** episodeAttributes[10] = _episodeVO.getEpisodeNextVisitType(); */
		episodeAttributes[10] = _episodeVO.getEpisodeVisitType();
		cstmt.setObject(3, new oracle.sql.STRUCT(episodeSD, conn, episodeAttributes));
		cstmt.setObject(4, _userVO.getHospitalCode());
		cstmt.setObject(5, _userVO.getSeatId());
		cstmt.registerOutParameter(6, Types.VARCHAR);
		cstmt.registerOutParameter(7, Types.VARCHAR);
		cstmt.registerOutParameter(8, Types.VARCHAR);
		cstmt.executeUpdate();
		String errorMsg = cstmt.getString(7);
		String errorCode = cstmt.getString(6);
		System.out.println(errorCode + " :" + errorMsg);
		if (errorCode.equals("0"))
		{
			System.out.println("Database Error Message :" + errorMsg);
			String collectedRequisitionNos = cstmt.getString(8);
			collectedRequisitionNosArray = collectedRequisitionNos.split("#");
		
		}
		else
		{
			throw new HisApplicationExecutionException(errorCode);
		}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
			throw new HisRecordNotFoundException(e.getMessage());
			}
			else {
				try{
					conn.rollback();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
				throw new HisDataAccessException("Requisition raise time is over" + "Requisition raise time is over");
			}
			/*e.printStackTrace();
			System.out.println("error.... Essential BO");
			/*try
			{
			conn.rollback();
			}
			catch (SQLException e1)
			{
			e1.printStackTrace();
			}
			throw new HisApplicationExecutionException();*/
		}
		finally
		{
			try
			{
				conn.commit();
				conn.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return collectedRequisitionNosArray;
	}
 
	
	/**
	 *  get the Investigation status detail by crno, Episode Code and visit no
	 */
	public List<MBInvestigationRequisitionDetailVO> getInvDetailByEpisodeCode(EpisodeVO episodeVO,UserVO userVO)
	{
		List<MBInvestigationRequisitionDetailVO> invRequisitionVOList=null;
		MBInvestigationRequisitionDetailVO invRequisitionVO;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.REQUISITION_STATUS.HIVT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),episodeVO.getPatCrNo());
		populateMAP.put(sq.next(),episodeVO.getEpisodeCode());
		//populateMAP.put(sq.next(),episodeVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),userVO.getHospitalCode());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				invRequisitionVOList=new ArrayList<MBInvestigationRequisitionDetailVO>();
			}
			rs.beforeFirst();
			while(rs.next()){
				invRequisitionVO=new MBInvestigationRequisitionDetailVO();
				HelperMethods.populateVOfrmRS(invRequisitionVO, rs);
				invRequisitionVOList.add(invRequisitionVO);
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
		return invRequisitionVOList;
	}
	
	
	/* ********************************** Post Medical Entry *****************************************/
	
	/**
	 * get the list of the certificate type for which patient has schedule today
	 */
	public List getCertificateTypeListForPostEntry(UserVO userVO)
	{
		List certificateTypeList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CERTIFICATE_TYPE_FOR_POST_ENTRY.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_ATTENDENCE_ON_VISIT_DATE);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Candidate Found");
			}
			rs.beforeFirst();
			certificateTypeList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return certificateTypeList;
	}
	
	
	/**
	 * get the list of the certificate type for which patient has schedule today
	 */
	public List getAssginedBoard(UserVO userVO)
	{
		List boardList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.BOARD_ASSIGNED.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_ATTENDENCE_ON_VISIT_DATE);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Board Found");
			}*/
			rs.beforeFirst();
			boardList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return boardList;
	}
	
	public List getConsultantList(UserVO userVO)
	{
		List consultantList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CONSULTANT.PIST_EMP_CUR_DETAIL_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MedicalBoardConfig.PROCESS_TYPE_DEPARTMENT);//process type
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No Board Found");
			}
			rs.beforeFirst();
			consultantList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return consultantList;
	}
	
	/**
	 *  get the list of the candidate who requisition is pending
	 */
	public List getCandidateListForPostEntry(String certificateTypeId,String reqStaus,String boardNo,String fromDate,String toDate,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.PATIENT_FOR_POST_ENTRY.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(!fromDate.equals("")){
				query+=" and trunc(b.hmbdt_visit_date) >= trunc(to_date('" + fromDate + "','dd-mon-yyyy'))";
			}
			if(!toDate.equals("")){
				query+=" and trunc(b.hmbdt_visit_date) <= trunc(to_date('" + toDate + "','dd-mon-yyyy'))";
			}
			
			query+=" ORDER BY visitdate, patname";
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), boardNo);
		populateMAP.put(sq.next(), reqStaus);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Candidate Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	
	/**
	 *  get the list of the checklist compulsory at Post Entry
	 */
	public List getCheckListForPostEntry(String certificateTypeId,UserVO userVO)
	{
		MedicalBoardChecklistVO checkListVO = null;
		List<MedicalBoardChecklistVO> checkListVOList=new ArrayList<MedicalBoardChecklistVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CHECKLIST_COMPULSORY_AT_POST_ENTRY.HMBT_CERTIFICATE_CHECKLIST_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MedicalBoardConfig.COMPULSORY_AT_TIME_OF_ACCEPTANCE);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Certificate Type Found");
			}*/
			while(rs.next()){
				checkListVO=new MedicalBoardChecklistVO();
				HelperMethods.populateVOfrmRS(checkListVO, rs);
				checkListVOList.add(checkListVO);
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
		return checkListVOList;
	}
	
	public List getVarifiedCertificateTypeList(UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.VARIFIED_CERTIFICATE_LIST.HMBT_REQUISITION_DTL";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			
			//populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_GENERATED);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			}
			catch (Exception e)
			{
			  throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord = new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}
	
	public List getReqDateByCertificateTypeId(String certificateTypeId,UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.REQ_DATE_BY_CRTIFICATETYPEID.HMBT_REQUISITION_DTL";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			
			populateMAP.put(sq.next(), certificateTypeId);
			populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_GENERATED);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			}
			catch (Exception e)
			{
			  throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord = new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}
	
	public List getCandidateListByReqDate(String certificateTypeId,String reqDate,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CADIDATE_LIST.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),reqDate);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),certificateTypeId);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_GENERATED);
		
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Schedule Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	public List getAllCandidateListByCertificateType(String certificateTypeId,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CADIDATE_LIST_BY_CERTIFICATE_TYPE.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),certificateTypeId);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_GENERATED);
		
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Schedule Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	public List getHandOverByEmpList(UserVO _userVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(MedicalBoardConfig.PROCEDURE_GET_EMPLIST_PROCESSWISE);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, MedicalBoardConfig.PROCESS_ID_DOCTOR_LIST);
			strProc.addInParameter(3, Types.VARCHAR, null);
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		// log.error(query + "\n");
		/*
		 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
		 */

		List alRecord = new ArrayList();

		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return alRecord;
	}
	
	public List getAllCandidateListByCrNo(String patCrNo,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CADIDATE_LIST_BY_REQNO.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),patCrNo);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_GENERATED);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_HANDOVER);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No requisition found from this patient");
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	public List getOpinionListByReqId(String requisitonId,UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.OPINION_LIST_BY_REQUISITIONID.HMBT_REQUISITION_DTL";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), requisitonId);
			
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			}
			catch (Exception e)
			{
			  throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord = new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}
	
	public List getBoardMemberListByReqId(String requisitonId,UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.BOARD_MEMBER_LIST_BY_REQUISITIONID.HMBT_REQUISITION_DTL";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			
			populateMAP.put(sq.next(), requisitonId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
			populateMAP.put(sq.next(), requisitonId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
			populateMAP.put(sq.next(), MedicalBoardConfig.ROLE_ID_FOR_ESCORTED_EMPLOYEE);
			
			
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			}
			catch (Exception e)
			{
			  throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord = new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}
	
	public List getCertTypeListForCertVerification(UserVO userVO)
	{
		List certificateTypeList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.CERT_TYPE_FOR_CERT_VERIFICATION.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_MEDICAL_PERFORMED);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Candidate Found");
			}
			rs.beforeFirst();
			certificateTypeList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return certificateTypeList;
	}
	
	public List getAssginedBoardForVerification(UserVO userVO)
	{
		List boardList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.BOARD_ASSIGNED_FOR_CERT_VERIF.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_MEDICAL_PERFORMED);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Board Found");
			}*/
			rs.beforeFirst();
			boardList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return boardList;
	}
	
	public List getCandidateListForCertVerification(String certificateTypeId,String reqStaus,String boardNo,String fromDate,String toDate,UserVO userVO)
	{
		MedicalBoardRequisitionVO requisitionVO = null;
		List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.PATIENT_FOR_CERT_VERIFICATION.HMBT_REQUISITION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(!fromDate.equals("")){
				query+=" and trunc(b.hmbdt_visit_date) >= trunc(to_date('" + fromDate + "','dd-mon-yyyy'))";
			}
			if(!toDate.equals("")){
				query+=" and trunc(b.hmbdt_visit_date) <= trunc(to_date('" + toDate + "','dd-mon-yyyy'))";
			}
			
			query+=" ORDER BY visitdate, patname";
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), certificateTypeId);
		populateMAP.put(sq.next(), boardNo);
		populateMAP.put(sq.next(), reqStaus);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Candidate Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				requisitionVO=new MedicalBoardRequisitionVO();
				HelperMethods.populateVOfrmRS(requisitionVO, rs);
				requisitionVOList.add(requisitionVO);
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
		return requisitionVOList;
	}
	
	public List getBoardMemberOpinionListByReqId(String reqId,UserVO userVO)
	{
		MedicalBoardVerificationDtlVO verificationVO = null;
		List verificationVOList=new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey ="SELECT.OPINION_LIST_BY_REQID.HMBT_VERIFICATION_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),reqId);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No requisition found from this patient");
			}
			rs.beforeFirst();
			while(rs.next()){
				verificationVO=new MedicalBoardVerificationDtlVO();
				HelperMethods.populateVOfrmRS(verificationVO, rs);
				verificationVOList.add(verificationVO);
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
		return verificationVOList;
	}

	
	
	
	

		public String getScanFlag(String crNo,UserVO userVO) 
		{

			HisDAO daoObj = null;
//			WebRowSet ws = null;
			
			int nFuncIndex = 0;
		
//			String strErr = "";
			String strFuncName = "";
			String strDocAvl="";

			try {

				daoObj = new HisDAO("MMS Transactions","IssueTransDAO");
				
				strFuncName = "{? = call pkg_scanning_view.is_patient_document_present(?,?,?,?,?,?,?,?,?)}";
				nFuncIndex = daoObj.setFunction(strFuncName);
				daoObj.setFuncInValue(nFuncIndex, 2, "1");
				daoObj.setFuncInValue(nFuncIndex, 3, userVO.getHospitalCode());
				daoObj.setFuncInValue(nFuncIndex, 4, crNo);
				daoObj.setFuncInValue(nFuncIndex, 5, "");
				daoObj.setFuncInValue(nFuncIndex, 6, "");
				daoObj.setFuncInValue(nFuncIndex, 7, "");
				daoObj.setFuncInValue(nFuncIndex, 8, "");
				daoObj.setFuncInValue(nFuncIndex, 9, "");
				daoObj.setFuncInValue(nFuncIndex,10, "");
				daoObj.setFuncOutValue(nFuncIndex, 1);
				daoObj.executeFunction(nFuncIndex);
				strDocAvl = daoObj.getFuncString(nFuncIndex);
							
			
						
				

			} catch (Exception e) {
				e.printStackTrace();
				

			} finally {
				if (daoObj != null) 
					daoObj.free();
					daoObj = null;
				
			}
			return strDocAvl;
		}

	public String getLastCertMeDcNo(String strCertTypeId, UserVO userVO)
	{

		HisDAO daoObj = null;

		int nFuncIndex = 0;

		String strFuncName = "";
		String strCertReqNo = "";

		try
		{

			daoObj = new HisDAO("Medical Board", "MbTransactionEssentialDAO");

			strFuncName = "{? = call pkg_medical_board.getLastCertReqNo(?,?)}";
			nFuncIndex = daoObj.setFunction(strFuncName);
			daoObj.setFuncInValue(nFuncIndex, 2, userVO.getHospitalCode());
			daoObj.setFuncInValue(nFuncIndex, 3, strCertTypeId);
			daoObj.setFuncOutValue(nFuncIndex, 1);
			daoObj.executeFunction(nFuncIndex);
			strCertReqNo = daoObj.getFuncString(nFuncIndex);

		}
		catch (Exception e)
		{
			e.printStackTrace();

		}
		finally
		{
			if (daoObj != null)
			
				daoObj.free();
				daoObj = null;
			
		}
		return strCertReqNo;
	}
		
		
		
		/**
		 * get the list of the certificate type for which patient has schedule today
		 */
		public List getAssginedBoardCerttificateBased(String certificateTypeID,UserVO userVO)
		{
			List boardList=new ArrayList();
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey ="SELECT.BOARD_ASSIGNED.HMBT_REQUISITION_DTL_CERTIFICATE_ID_BASED";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), MedicalBoardConfig.REQUEST_STATUS_ATTENDENCE_ON_VISIT_DATE);
			populateMAP.put(sq.next(), certificateTypeID );
			
			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				/*if(!rs.next()){
					throw new HisRecordNotFoundException("No Board Found");
				}*/
				rs.beforeFirst();
				boardList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);
			}
			return boardList;
		}

		public void saveExternalRefer(MedicalBoardExternalReferVO externalReferVO, UserVO userVO) {

//			List boardList=new ArrayList();
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
			String queryKey ="INSERT.HMBT_REQ_EXT_REFER_DTL";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			
			populateMAP.put(sq.next(), externalReferVO.getReqID());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), externalReferVO.getReqID());
			populateMAP.put(sq.next(), externalReferVO.getExtReferTo());
			populateMAP.put(sq.next(), externalReferVO.getReferReason());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
			try
			{
//				ResultSet rs = 
				HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
				
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException(e.getMessage());
			}
			
			
		}
		
		public List<MedicalBoardExternalReferVO> getMBCandidateExtRefDetail(String reqId,UserVO userVO)
		{
			List<MedicalBoardExternalReferVO> externalRefDtlVOList=null;
			MedicalBoardExternalReferVO externalRefDtlVO;
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey ="SELECT.REF_DTL.HMBT_REQ_EXT_REFER_DTL";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			
			
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), reqId);
			
			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if(rs.next()){
					externalRefDtlVOList=new ArrayList<MedicalBoardExternalReferVO>();	
				}
				/*else{
					throw new HisRecordNotFoundException("No Refer Detail Found");
				}*/
				rs.beforeFirst();
				while(rs.next()){
					externalRefDtlVO=new MedicalBoardExternalReferVO();
					HelperMethods.populateVOfrmRS(externalRefDtlVO, rs);
					externalRefDtlVOList.add(externalRefDtlVO);
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
			return externalRefDtlVOList;
		}
		
		
		/**
		 *  get the Episode detail by crno
		 */
		public EpisodeVO getEpisodeCodeByCrNoPrevDate(String crNo,UserVO userVO,String date)
		{
			EpisodeVO episodeVO=new EpisodeVO();
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey ="SELECT.EPISODE_DTL.BY_CRNO.PREVDATE.HRGT_EPISODE_DTL";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			
			populateMAP.put(sq.next(),crNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),date);

			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if(!rs.next()){
					throw new HisRecordNotFoundException(" Candidate with CR no. "+crNo+" did not visit on  "+date);
				}
				HelperMethods.populateVOfrmRS(episodeVO, rs);
					
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);
			}
			return episodeVO;
		}

}//end class