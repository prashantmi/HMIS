package new_investigation.transactions.dao;



import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientDetailVO;

import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.BookMarkVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.InvestigationRequistionVO;
import new_investigation.vo.filmUsedVO;
import new_investigation.vo.RequistionHeaderVO;

import new_investigation.vo.SampleCollectionCumAcceptanceVO;


public class filmUsedDAO extends DataAccessObject implements filmUsedDAOi
{
	public filmUsedDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	
	public  Inv_RequisitionRaisingPatientVO getInvRaisingPatDetails(Inv_RequisitionRaisingPatientVO patVO,UserVO _UserVO)
	{
		String query = "";		
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.PATIENT_DTLS.HRGT_PATIENT_DTL";
		Sequence sq = new Sequence();

		Inv_RequisitionRaisingPatientVO invReqRaisingVO;
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), patVO.getPatCRNo());
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
					populateMAP);
			rs.beforeFirst();
			invReqRaisingVO=new Inv_RequisitionRaisingPatientVO();
			if(!rs.next())
			{
			}
			else HelperMethods.populateVOfrmRS(invReqRaisingVO,rs);
			
		}
		catch (HisRecordNotFoundException e)
		{

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("" + e);
		}
		return invReqRaisingVO;
	}
	 
	public List getSampleCombo(String labCode,String testCode,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstSample=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.LABTESTSAMPLECOMBO.HIVT_LABTEST_SAMPLE_MST";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), labCode);
		populateMap.put(sq.next(), testCode);
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
			//	throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstSample=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstSample;
	}
	
	
	public String generateRequisitionNoSequence(String labCode,UserVO userVO)
	{
		String sequence_Hash_yyymmdd=""; 
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.REQUISTIONNO.SYS_REQUISITION_MAINTAINER";
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
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
			populateMap.put(sq.next(), labCode);
			populateMap.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}
			else
			{
				sequence_Hash_yyymmdd=rs.getString(1)+"#"+rs.getString(2);
				
			}
			
			
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }
		return sequence_Hash_yyymmdd;
	}
	
	
	public void insertRequisitionSequenceInMaintainer(String labCode,String sequence,String yymmdd, UserVO _userVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="INSERT.REQUISTIONNO.SYS_REQUISITION_MAINTAINER";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			
			populateMAP.put(sq.next(),labCode);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),sequence);
			populateMAP.put(sq.next(),yymmdd);
			
        	
            	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in SYS_REQUISITION_MAINTAINER Table");
		}

	}
	
	public void updateRequisitionSequenceInMaintainer(String sequence,String labcCode,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="UPDATE.REQUISTIONNO.SYS_REQUISITION_MAINTAINER";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			populateMAP.put(sq.next(),sequence);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labcCode);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	// insert in requisition header table
	
	public void insertRequisitionHeaderDtl(RequistionHeaderVO voReqHeader, UserVO _userVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="INSERT.HIVT_REQUISITION_HEADER";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			
			populateMAP.put(sq.next(),voReqHeader.getRequisitionNumber()); //1
			populateMAP.put(sq.next(),voReqHeader.getPatCrNo()); //2
			populateMAP.put(sq.next(),_userVO.getHospitalCode()); //3
			populateMAP.put(sq.next(),voReqHeader.getVisitNo());  //4
			populateMAP.put(sq.next(),voReqHeader.getLabCode());//5
			populateMAP.put(sq.next(),voReqHeader.getIsConfidential());//6
			populateMAP.put(sq.next(),voReqHeader.getReqHeaderStatus());//7
			populateMAP.put(sq.next(),_userVO.getSeatId());//8
			populateMAP.put(sq.next(),voReqHeader.getEpisodeCode());//9
			populateMAP.put(sq.next(),voReqHeader.getPatName());//10
			populateMAP.put(sq.next(),voReqHeader.getReqType());//11
			populateMAP.put(sq.next(),voReqHeader.getPatDob());//12
			populateMAP.put(sq.next(),voReqHeader.getVisitDate());//13
			populateMAP.put(sq.next(),voReqHeader.getIsActualDob());//14
			populateMAP.put(sq.next(),voReqHeader.getOrderedByDoctor());//15
			populateMAP.put(sq.next(),voReqHeader.getGenderCode());//16
			populateMAP.put(sq.next(),voReqHeader.getRequsitionRaisedThrough());//17
			populateMAP.put(sq.next(),voReqHeader.getPatAge());//18
			populateMAP.put(sq.next(),voReqHeader.getPatAdmissionNo());//19
			populateMAP.put(sq.next(),voReqHeader.getPatAddress());//20
			populateMAP.put(sq.next(),voReqHeader.getWardCode());//21
			populateMAP.put(sq.next(),voReqHeader.getMobileNo());//22
			populateMAP.put(sq.next(),voReqHeader.getEmailId());//23
			populateMAP.put(sq.next(),voReqHeader.getRoomCode());//24
			populateMAP.put(sq.next(),voReqHeader.getBedCode());//25
			populateMAP.put(sq.next(),voReqHeader.getMlcNo()); //26
			populateMAP.put(sq.next(),voReqHeader.getBedName());//27
			populateMAP.put(sq.next(),voReqHeader.getRoomName());//28
			populateMAP.put(sq.next(),voReqHeader.getWardName());//29
			populateMAP.put(sq.next(),voReqHeader.getDeptName());//30
			//populateMAP.put(sq.next(),voReqHeader.getReqDeleteSeatId());//31
			populateMAP.put(sq.next(),voReqHeader.getUnitName());//32
			//populateMAP.put(sq.next(),voReqHeader.getReqDeleteDate());//33
			populateMAP.put(sq.next(),voReqHeader.getDeptCode());//34
			populateMAP.put(sq.next(),voReqHeader.getDeptUnitCode());//35
			populateMAP.put(sq.next(),voReqHeader.getIsAutomatedRequest());//36
			populateMAP.put(sq.next(),voReqHeader.getOrderedByDoctorName());//37
			populateMAP.put(sq.next(),voReqHeader.getRefHospitalCode());//38
			populateMAP.put(sq.next(),""); //voReqHeader.getDeptUnitName());//39
			populateMAP.put(sq.next(),voReqHeader.getRefLabCode());//40
			populateMAP.put(sq.next(),voReqHeader.getHospOrLabName());//41
			populateMAP.put(sq.next(),voReqHeader.getExtCrNo());//42
			populateMAP.put(sq.next(),voReqHeader.getBillNo());//43
			populateMAP.put(sq.next(),voReqHeader.getDeleteReason()); //44
            	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in HIVT_REQUISITION_HEADER Table");
		}

	}
	
	
	
	// insert in requisition header table
	
		public void insertRequisitionDtl(InvestigationRequistionVO voLabTest, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.HIVT_REQUISITION_DTL";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				
				populateMAP.put(sq.next(),_userVO.getHospitalCode()); //1
				
				populateMAP.put(sq.next(),voLabTest.getStrRequsitionDNo()); //2
				
				populateMAP.put(sq.next(),(voLabTest.getStrIsAppointment()==null?"0":voLabTest.getStrIsAppointment())); //3
				
				populateMAP.put(sq.next(),voLabTest.getStrLabCode());  //4
				
				populateMAP.put(sq.next(),voLabTest.getStrTestCode());  //5
				
				populateMAP.put(sq.next(),(voLabTest.getStrIsConfidential()==null?"0":voLabTest.getStrIsConfidential()));//6
				
				//populateMAP.put(sq.next(),voLabTest.getStrResultDate());//7
				
				populateMAP.put(sq.next(),voLabTest.getStrSampleCode());//7-- should be 8
				
			//	populateMAP.put(sq.next(),voLabTest.getStrResultDate());//9
				
				//populateMAP.put(sq.next(),voLabTest.getStrSampleNo());//9
				
				populateMAP.put(sq.next(),_userVO.getSeatId());//8
				
				populateMAP.put(sq.next(),voLabTest.getStrReqNo());//9
				
				populateMAP.put(sq.next(),voLabTest.getStrWorkOrderSequence());//10
				
				populateMAP.put(sq.next(),voLabTest.getStrTypeOfComponent());//11
				
				populateMAP.put(sq.next(),voLabTest.getStrRequisitionDtlStatus());//11
				
				
				
				
				
	            	
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in HIVT_REQUISITION_DTL Table");
			}

		}

		public List<Inv_EpisodeVO> getPatientEpisode(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PATIENT_EPISODE_DTLS.HRGT_EPISODE_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				 
				populateMAP.put(sq.next(), patVO.getPatCRNo());
				 
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			
			PatientDetailVO[] dailyPatientVOs = null;
			List<Inv_EpisodeVO> lstPatientDetailVO = new ArrayList<Inv_EpisodeVO>();
			ValueObject[] valueObjects = null;
			
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("No Patient Record");
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(Inv_EpisodeVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
					for (int i = 0; i < valueObjects.length; i++)
					{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						lstPatientDetailVO.add((Inv_EpisodeVO)valueObjects[i]);
					}
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
			}
			return lstPatientDetailVO;
		}

		
		public List<Inv_PatientAdmissionDtlVO> getPatientAdmission(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PATIENT_ADMISSION_DTLS.HIPT_PATADMISSION_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
		

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
				
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				
				populateMAP.put(sq.next(), patVO.getPatCRNo());
				 
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			
			Inv_PatientAdmissionDtlVO[] dailyPatientVOs = null;
			List<Inv_PatientAdmissionDtlVO> lstPatientAdmissionDetailVO = new ArrayList<Inv_PatientAdmissionDtlVO>();
			ValueObject[] valueObjects = null;
			
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("No Patient Record");
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(Inv_PatientAdmissionDtlVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
					for (int i = 0; i < valueObjects.length; i++)
					{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						lstPatientAdmissionDetailVO.add((Inv_PatientAdmissionDtlVO)valueObjects[i]);
					}
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				//else throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
			}
			return lstPatientAdmissionDetailVO;
		}
		
		
		public List<BookMarkVO> getBookMarkList( UserVO _userVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.BOOK_MARK_DTLS.HIVT_BOOKMARK_MST";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				
				populateMAP.put(sq.next(), _userVO.getSeatId());
				
				populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION); //99
				
				populateMAP.put(sq.next(), _userVO.getUserSeatId());
				
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				 
				 
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			
			BookMarkVO[] bookMarkVOs = null;
			List<BookMarkVO> lstBookMarkVO = new ArrayList<BookMarkVO>();
			ValueObject[] valueObjects = null;
			
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("No Patient Record");
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(BookMarkVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
					for (int i = 0; i < valueObjects.length; i++)
					{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						lstBookMarkVO.add((BookMarkVO)valueObjects[i]);
					}
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
			}
			return lstBookMarkVO;
		}

		//getFilmSizeCombo
		public List getLabCombos(filmUsedVO onlinePatientvo,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List parameterCombo=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			//String queryKey="SELECT_LAB_COMBO_HIVT_LABORATORY_MST";
			String queryKey="SELECT_LAB_COMBO_HIVT_LABORATORY_MST_FILMUSED";
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			populateMap.put(sq.next(),_UserVO.getHospitalCode());
			populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMap.put(sq.next(),_UserVO.getUserSeatId());
			populateMap.put(sq.next(),_UserVO.getHospitalCode());


			
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
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				if (!rs.next())
				{
					throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					parameterCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return parameterCombo;
		}
			
		
		
		public List<filmUsedVO> setPatientEssentials(filmUsedVO onlinePatientvo, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.ONLOAD_HIVT_REQUISITION_HEADER.FILMDETAILSPATIENTS";
			 
	 		String condition5="And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)="+onlinePatientvo.getPatCrNo();
	 		String conditionLab="";
	 		if(onlinePatientvo.getLabCode().equals("%"))
				conditionLab=" and gnum_lab_code like '%' ";
			else
				conditionLab=" and gnum_lab_code="+onlinePatientvo.getLabCode();
			
			 String orderby="  order by patvisitdat desc";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
				
				
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(),onlinePatientvo.getFromDate());
				populateMAP.put(sq.next(),onlinePatientvo.getToDate());
				
				populateMAP.put(sq.next(),_UserVO.getUserSeatId());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());

	
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			
			PatientDetailVO[] dailyPatientVOs = null;
			List<filmUsedVO> lstfilmUsedVO = new ArrayList<filmUsedVO>();
			ValueObject[] valueObjects = null;
			query+=conditionLab;
			try
			{
				if(onlinePatientvo.getPatCrNo()!=null&&!onlinePatientvo.getPatCrNo().equals(""))
				{
					query+=condition5;
				}
		
				query+=orderby;

				
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("No Patient Record");
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(filmUsedVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
					for (int i = 0; i < valueObjects.length; i++)
					{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						lstfilmUsedVO.add((filmUsedVO)valueObjects[i]);
					}
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("filmUsedDAO:setPatientEssentials:HelperMethods :: " + e);
			}
			return lstfilmUsedVO;
		}	
		
		
		
		
		
		public List<filmUsedVO> setPatientEssentialsOnLoad(filmUsedVO onlinePatientvo, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.ONLOAD_HIVT_REQUISITION_HEADER.FILMDETAILSPATIENTS";
			 
	 		String condition5="And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)="+onlinePatientvo.getPatCrNo();
	 		String conditionLab="";
	 		if(onlinePatientvo.getLabCode().equals("%"))
				conditionLab=" and gnum_lab_code like '%' ";
			else
				conditionLab=" and gnum_lab_code="+onlinePatientvo.getLabCode();
			
			 String orderby="  order by patvisitdat desc";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
				
				
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(),onlinePatientvo.getFromDate());
				populateMAP.put(sq.next(),onlinePatientvo.getToDate());
			
				populateMAP.put(sq.next(),_UserVO.getUserSeatId());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());

				
			 
				 
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			
			PatientDetailVO[] dailyPatientVOs = null;
			List<filmUsedVO> lstfilmUsedVO = new ArrayList<filmUsedVO>();
			ValueObject[] valueObjects = null;
			query+=conditionLab;
			try
			{
				
				if(onlinePatientvo.getPatCrNo()!=null&&!onlinePatientvo.getPatCrNo().equals(""))
				{
					query+=condition5;
				}
				
				query+=orderby;

				 
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("No Patient Record");
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(filmUsedVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
					for (int i = 0; i < valueObjects.length; i++)
					{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
						lstfilmUsedVO.add((filmUsedVO)valueObjects[i]);
					}
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("filmUsedDAO:setPatientEssentials:HelperMethods :: " + e);
			}
			return lstfilmUsedVO;
		}	
		
		
		
		
		
		
		
		
		
		
		
		public  List<filmUsedVO>  patientDetails(String reqNo,String reqType,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PATIENT_HIVT_REQUISITION_HEADER";
			Sequence sq = new Sequence();
			
			filmUsedVO voPatientCollection=null;

			List<filmUsedVO> lstInvPatientCollectionVO =null;
			populateMAP.put(sq.next(), reqType);
			populateMAP.put(sq.next(), reqNo);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
						populateMAP);
				rs.beforeFirst();
				
				 if (!rs.next())
		            {
		                //throw new HisRecordNotFoundException("No Patient List  Found");
		            }
		            else
		            {
		                rs.beforeFirst();
		                lstInvPatientCollectionVO= new ArrayList<filmUsedVO>();
		                while(rs.next()) {
		                	voPatientCollection=new filmUsedVO();
		                	HelperMethods.populateVOfrmRS(voPatientCollection, rs);
		                	lstInvPatientCollectionVO.add(voPatientCollection);
		                }
		            }
			}
			catch (HisRecordNotFoundException e)
			{

				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return lstInvPatientCollectionVO;

		}
		
		
		public List getTestCombo(UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List departcombo=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.COMBO_hivt_rejectionreason_mst";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			//populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				if (!rs.next())
				{
					throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return departcombo;
		}
		
		public String generatePatientNoSequence(String labCode,UserVO userVO)
		{
			String sequence_Hash_yyymmdd=""; 
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.PATIENT.SYS_REQUISITION_MAINTAINER";
			Sequence sq = new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
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
				 
				populateMap.put(sq.next(), labCode);
				populateMap.put(sq.next(), userVO.getHospitalCode());
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("");
				}
				else
				{
					sequence_Hash_yyymmdd=rs.getString(1)+"#"+rs.getString(2);
					
				}
				
				
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }
			return sequence_Hash_yyymmdd;
		}
			 
		public void insertPatientNoSequenceInMaintainer(String labCode,String sequence,String yymmdd, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.PATIENTNO.SYS_PATIENTACC_MAINTAINER";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				
				populateMAP.put(sq.next(),labCode);
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				populateMAP.put(sq.next(),sequence);
				populateMAP.put(sq.next(),yymmdd);
				
	        	
	            	
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in SYS_PATIENTACC_MAINTAI Table");
			}

		}
		
		public void updatePatientNoSequenceInMaintainer(String sequence,String labCode,UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.PATIENT.REQUISTIONNO.SYS_SAMPLE_MAINTAINER";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				populateMAP.put(sq.next(),sequence);
				
				populateMAP.put(sq.next(), labCode);
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}	


		public void updateRequisitionHeaderForPatient(filmUsedVO voPatient,String patAddress,String reqNo,UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.PATIENTDTLS.HIVT_REQUISITION_HEADER";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				populateMAP.put(sq.next(),voPatient.getMobileNo());
				populateMAP.put(sq.next(),voPatient.getEmailId());
				populateMAP.put(sq.next(),voPatient.getAddress());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), reqNo);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}
		
		
		public void updateRequisitionDtlForPatient(filmUsedVO voPatient, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.REQDTLS.PATIENT.HIVT_REQUISITION_DTLS";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				 
				
				populateMAP.put(sq.next(),voPatient.getReqDtlStatus());
				 
				populateMAP.put(sq.next(),voPatient.getBillNo());
			//	populateMAP.put(sq.next(),voPatient.getLabNoConfiguration());
				populateMAP.put(sq.next(),voPatient.getRejectionReason());
			//	populateMAP.put(sq.next(),voPatient.getRejectionAction());
				
				 
				 
				populateMAP.put(sq.next(), _UserVO.getSeatId());
			//	populateMAP.put(sq.next(),voPatient.getAccepted());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), voPatient.getRequisitionDNo());
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}
		
		
		public void updateRequisitionDtlForPatientRescheduled(filmUsedVO voPatient, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.REQDTLS.PATIENT.RESCHEDULED.HIVT_REQUISITION_DTLS";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				 
				
				populateMAP.put(sq.next(),voPatient.getReqDtlStatus());
				 
				populateMAP.put(sq.next(),voPatient.getBillNo());
		//		populateMAP.put(sq.next(),voPatient.getLabNoConfiguration());
				populateMAP.put(sq.next(),voPatient.getRejectionReason());
		//		populateMAP.put(sq.next(),voPatient.getRejectionAction());
				populateMAP.put(sq.next(), _UserVO.getSeatId());
		//		populateMAP.put(sq.next(),voPatient.getAccepted());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), voPatient.getRequisitionDNo());
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}
		
		
		
		public void updateRequisitionDtlForPatientAccepted(filmUsedVO voPatient, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.REQDTLS.PATIENT.ACCEPTED.HIVT_REQUISITION_DTLS";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				 
				
				populateMAP.put(sq.next(),voPatient.getReqDtlStatus());
				 
				populateMAP.put(sq.next(),voPatient.getBillNo());
		//		populateMAP.put(sq.next(),voPatient.getLabNoConfiguration());
				 
				
				 
				
			//	populateMAP.put(sq.next(),voPatient.getAccepted());
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), voPatient.getRequisitionDNo());
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}
		
		
		
		
		
		
		
		public void insertFilmDetail(filmUsedVO voPatient,  UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.FILMDETAIL.HIVT_WORKORDER_FILM_DTL";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				
                populateMAP.put(sq.next(),voPatient.getBatchNo());
                 populateMAP.put(sq.next(),voPatient.getRequisitionDNo());
                 populateMAP.put(sq.next(),"1");
                 populateMAP.put(sq.next(),voPatient.getFilmNo());
                 populateMAP.put(sq.next(),voPatient.getFilmSize());
                 populateMAP.put(sq.next(),voPatient.getFilmUsedType());
                 populateMAP.put(sq.next(),voPatient.getTestStatus());
                 populateMAP.put(sq.next(),_userVO.getSeatId());
                 populateMAP.put(sq.next(),voPatient.getFilmReason());
                 populateMAP.put(sq.next(),voPatient.getRemarks());
                 populateMAP.put(sq.next(),_userVO.getHospitalCode());
						
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"film used dao.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in  film work order Table");
			}

		}
		
		 
		public void insertPatientNotAcceptedDtlForPatient(String pataccNo,filmUsedVO voPatient,  UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.PATIENTDTLSNOTACCEPTED.HIVT_PATIENT_ACCEPTANCE_DTL";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
			    
                 populateMAP.put(sq.next(),voPatient.getRequisitionDNo());
				populateMAP.put(sq.next(),_userVO.getHospitalCode());
				
				 populateMAP.put(sq.next(),pataccNo); // hivnum_pat_acc_no
		//		populateMAP.put(sq.next(),voPatient.getAccepted());
				populateMAP.put(sq.next(),voPatient.getRejectionReason()); 
				populateMAP.put(sq.next(),_userVO.getSeatId());
		//		populateMAP.put(sq.next(),voPatient.getRejectionAction());
		//		populateMAP.put(sq.next(),voPatient.getLabNoConfiguration());
		//		populateMAP.put(sq.next(),voPatient.getRejectionAction()); // hivnum_pat_acc_status_code
				populateMAP.put(sq.next(),_userVO.getSeatId()); 
		 
				
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in HIVT_PATIENT_ACCEPTANCE_DTL Table");
			}

		}

		
		
		
		public boolean checkDailyLabNoDuplicacy(filmUsedVO voPatient,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			boolean isLabNoPresent = false;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.CHECKDAILYLABNODUPLICACY";
			int count=0;
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			
		//	populateMap.put(sq.next(), voPatient.getLabNoConfiguration());
			//populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				if (!rs.next())
				{
					throw new HisRecordNotFoundException();
				}
				else
				{
					//rs.beforeFirst();
					count=rs.getInt(1);
					if(count>0)
						isLabNoPresent=true;
					
				//	departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return isLabNoPresent;
		}		
		
		public List   getPatientAcceptanceAutoLabNOConfig(filmUsedVO onlineAcceptanceVO, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PATIENT_ACCC_DETAIL_AUTOGEN_LABNO.HIVT_LABNO_CONF_MST";
			 
			 
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
			 
				populateMAP.put(sq.next(),onlineAcceptanceVO.getLabCode());
		//		populateMAP.put(sq.next(),onlineAcceptanceVO.getTestCode());
				 
			 	 
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
			}
			 
			List<filmUsedVO> listSampleAcceptanceVO = new ArrayList<filmUsedVO>();
			ValueObject[] valueObjects = null;
			
			try
			{
				 
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("No Patient Record");
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(filmUsedVO.class, rs);
					 
					//for (int i = 0; i < valueObjects.length; i++)
					//{
					 
						listSampleAcceptanceVO.add((filmUsedVO)valueObjects[0]);
					  // break;
					//}
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					return listSampleAcceptanceVO;
				}
				else return listSampleAcceptanceVO;
			}
			return listSampleAcceptanceVO;
		}	 
		 
		public void updatePatientAccInhivtlabnoconfmst(filmUsedVO voSamAcc,String finalEntryDate, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.PATIENTACCDETAIL.HIVT_LABNO_CONF_MST";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
			 
				populateMAP.put(sq.next(),finalEntryDate);
	//			populateMAP.put(sq.next(),voSamAcc.getLabNoConfiguration());
	//			populateMAP.put(sq.next(),voSamAcc.getConfigLab());
	//			populateMAP.put(sq.next(),voSamAcc.getConfigTest());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	//			populateMAP.put(sq.next(),voSamAcc.getConfigType());
	//			populateMAP.put(sq.next(),voSamAcc.getConfigSeq());
				
				 
				 
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}//updatePatientAccInhivtlabnoconfmstResetLabNO
		public String generateLabNoDateSequence(String subLength,UserVO userVO)
		{
			String sequence_Hash_yyymmdd=""; 
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.SAMPLEACC.LABNODATEGEN.FROM.DUAL";
			Sequence sq = new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
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
				 
				populateMap.put(sq.next(), subLength);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("");
				}
				else
				{
					sequence_Hash_yyymmdd=rs.getString(1);
					
				}
				
				
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }
			return sequence_Hash_yyymmdd;
		}
		
		public void updatePatientAccInhivtlabnoconfmstResetLabNO(filmUsedVO voSamAcc,String finalEntryDate, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.PATIENTACCDETAIL.RESET_RUNNING_LABNO.HIVT_LABNO_CONF_MST";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
			 
				 
	//			populateMAP.put(sq.next(),voSamAcc.getConfigLab());
	//			populateMAP.put(sq.next(),voSamAcc.getConfigTest());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	//			populateMAP.put(sq.next(),voSamAcc.getConfigType());
	//.put(sq.next(),voSamAcc.getConfigSeq());
				 
				 
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}//updatePatientAccInhivtlabnoconfmstResetLabNO
		
		
		 
		
		
		
		
		
		public List<filmUsedVO> checkAutoGenFormate(filmUsedVO online,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List<String> Formate=new ArrayList<String>();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PATIENT_ACCC_DETAIL_AUTOGEN_LABNO.HIVT_LABNO_CONF_MST";
			 
			   
			Connection conn=super.getTransactionContext().getConnection();
			try
			{
				 
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				 
				 
				
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
				 
				 
					populateMap.put(sq.next(),online.getLabCode());
	//				populateMap.put(sq.next(),online.getTestCode());
				 
				
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			List<filmUsedVO> filmUsedVO = new ArrayList<filmUsedVO>();
			ValueObject[] valueObjects = null;
			
			try
			{
				 
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException("No Patient Record");
				}
				else
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(filmUsedVO.class, rs);
					//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				//	for (int i = 0; i < valueObjects.length; i++)
				//	{
						//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					filmUsedVO.add((filmUsedVO)valueObjects[0]);
					//}
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
			return filmUsedVO;
		}
		
		
		
		
		public String checkAutoGenFormateRunningLabNo(filmUsedVO online, UserVO _UserVO)
		{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String Formate="";
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PATIENT_ACCC_DETAIL_AUTOGEN_LABNO.AJAX.CHECK.HIVT_LABNO_CONF_MST";
			Connection conn=super.getTransactionContext().getConnection();
			 
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();
			try
			{
			 
	//			populateMAP.put(sq.next(),online.getConfigLab());
	////			populateMAP.put(sq.next(),online.getConfigTest());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	//			populateMAP.put(sq.next(),online.getConfigType());
	//			populateMAP.put(sq.next(),online.getConfigSeq());

				

				 
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
				if (!rs.next())
				{
					//throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					if(rs.next())
					{
						Formate=rs.getString(1);
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
			return Formate;
		}
		
		
		
		public void insertUsedDetails(filmUsedVO filmvo,  UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.FILMDETAIL.HIVT_FILMUSED_DTL";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				
				/*  hivtnum_req_dno hivtnum_totalfilms_used, \
				      hivtnum_films_wasted, hivtnum_additional_films_used, gnum_seat_id, \
			          hgstr_remarks, gdt_entry_date)\
							  VALUES (?, ?, \*/
									  
				 			   
                 populateMAP.put(sq.next(),filmvo.getRequisitionDNo());
                 populateMAP.put(sq.next(),filmvo.getNoOfFilms());
                 populateMAP.put(sq.next(),filmvo.getNoOfFilmsWated());
                 populateMAP.put(sq.next(),filmvo.getNoOfFilmsAdditional());
                 populateMAP.put(sq.next(),_userVO.getSeatId());
                 populateMAP.put(sq.next(),filmvo.getRemarks());

						
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"film used dao.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in  film work order Table");
			}

		}
		
		
		
		//getFilmSizeCombo
				public List getFilmSizeCombo(filmUsedVO filmvo,UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List filmSizeCombo=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
					String queryKey="SELECT_FILM_SIZE.HIVT_FILM_DTL_MST";
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					populateMap.put(sq.next(),_UserVO.getHospitalCode());
					populateMap.put(sq.next(),filmvo.getTestCode());
				
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
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							filmSizeCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							//throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return filmSizeCombo;
				}
					
		
				
				
				//SELECT.FILM_NO.HIVT_LABORATORY_TEST_MST
				public String getFilmNo(filmUsedVO filmvo,UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					String filmNo="";
					List filmSizeCombo=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
					String queryKey="SELECT.FILM_NO.HIVT_LABORATORY_TEST_MST";
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					populateMap.put(sq.next(),_UserVO.getHospitalCode());
					populateMap.put(sq.next(),filmvo.getLabCode());
				
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
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							while(rs.next())
							filmNo=rs.getString(1);
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
					return filmNo;
				}
					
		//update film no
				public void updateFilmNo(filmUsedVO filmvo,  UserVO _userVO) {

					String query = "";
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
					String queryKey ="UPDATE.FILM_NO.HIVT_LABORATORY_MST";

					
					try {
						query = HelperMethodsDAO.getQuery(filename, queryKey);

					} catch (Exception e) {
						throw new HisApplicationExecutionException(
								"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
										+ e);
					}

					try {
						
						 populateMAP.put(sq.next(),filmvo.getTempFilmNo());
		                 populateMAP.put(sq.next(),_userVO.getSeatId());
		                 
		                 populateMAP.put(sq.next(),_userVO.getHospitalCode());
		                 populateMAP.put(sq.next(),filmvo.getLabCode());

								
					} catch (Exception e) {
						throw new HisApplicationExecutionException(
								"film used dao.populateMAP::" + e);
					}
					try {

						HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
								.getConnection(), query, populateMAP);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						throw new HisDataAccessException("Exception While insertion in  film work order Table");
					}

				}
				
				
				
				//update film flag
				public void updateFilmFlag(filmUsedVO filmvo,  UserVO _userVO) {

					String query = "";
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
					String queryKey ="UPDATE.FILM_FLAG.HIVT_REQUISITION_DTL";

					
					try {
						query = HelperMethodsDAO.getQuery(filename, queryKey);

					} catch (Exception e) {
						throw new HisApplicationExecutionException(
								"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
										+ e);
					}

					try {
						
						 populateMAP.put(sq.next(),filmvo.getTestStatus());
						 populateMAP.put(sq.next(),filmvo.getRequisitionDNo());
		                 populateMAP.put(sq.next(),_userVO.getHospitalCode());
		                

								
					} catch (Exception e) {
						throw new HisApplicationExecutionException(
								"film used dao.populateMAP::" + e);
					}
					try {

						HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
								.getConnection(), query, populateMAP);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						throw new HisDataAccessException("Exception While insertion in  film work order Table");
					}

				}
				
				
				//get batch details
				public List<String> getBatchDetails(filmUsedVO filmvo,UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List<String> batchDetails= new ArrayList<String>();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
					String queryKey="SELECT.BATCH_DETAILS.HSTT_ITEM_CURRSTOCK_DTL";
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					populateMap.put(sq.next(),_UserVO.getHospitalCode());
					populateMap.put(sq.next(),filmvo.getTestCode());
				
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
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
						//	throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							while(rs.next())
								batchDetails.add(rs.getString(1));
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							//throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return batchDetails;
				}
					
				
				//update inventory
				
				//update film flag
				public void updateInventory(String batchDetails,  UserVO _userVO) {

					String query = "";
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
					String queryKey ="UPDATE.BATCH_DETAILS.HSTT_ITEM_CURRSTOCK_DTL";

					
					try {
						query = HelperMethodsDAO.getQuery(filename, queryKey);

					} catch (Exception e) {
						throw new HisApplicationExecutionException(
								"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
										+ e);
					}

					try {
						
						
						 populateMAP.put(sq.next(),batchDetails.split("#")[4]);
						 
						 
						 populateMAP.put(sq.next(),batchDetails.split("#")[3]);
						 populateMAP.put(sq.next(),batchDetails.split("#")[2]);
						 populateMAP.put(sq.next(),batchDetails.split("#")[0]);
						 populateMAP.put(sq.next(),_userVO.getHospitalCode());
		                

								
					} catch (Exception e) {
						throw new HisApplicationExecutionException(
								"film used dao.populateMAP::" + e);
					}
					try {

						HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
								.getConnection(), query, populateMAP);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						throw new HisDataAccessException("Exception While insertion in  film work order Table");
					}

				}
				
				
				public String isrequisitionexist(String reqdno,UserVO userVO)
				{
					String sequence_Hash_yyymmdd=""; 
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
					String queryKey="SELECT_IS_FILM_REQUISITION_EXIST";
					Sequence sq = new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
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
						populateMap.put(sq.next(), reqdno);
					//	populateMap.put(sq.next(), userVO.getHospitalCode());
						
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						
						if (!rs.next())
						{
							//throw new HisRecordNotFoundException("");
						}
						else
						{
							sequence_Hash_yyymmdd=rs.getString(1);
							
						}
						
						
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException(e.getMessage());	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }
					return sequence_Hash_yyymmdd;
				}
				

				
				public void updateFilmNodata(String count,filmUsedVO filmvo,  UserVO _userVO) {

					String query = "";
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
					String queryKey ="UPDATE_FILM_DATA";
                    
					int totalaused=0;
					int addtionalused=0;
					int wasteused=0;
                    String[] data;
					if(count!=null || !count.equals(""))
					{
						data=count.split("#");
						
						
						     if(data.length>=1)
							totalaused=Integer.parseInt(data[0]);
						     if(data.length>=2)
						    	 addtionalused=Integer.parseInt(data[1]);
						     if(data.length==3)
						    	 wasteused=Integer.parseInt(data[2]);
						     
						     
						     if(filmvo.getNoOfFilms()!=null && !filmvo.getNoOfFilms().equals(""))
						     totalaused+=Integer.parseInt(filmvo.getNoOfFilms());
						     
						     if(filmvo.getNoOfFilmsAdditional()!=null && !filmvo.getNoOfFilmsAdditional().equals(""))
						     addtionalused+=Integer.parseInt(filmvo.getNoOfFilmsAdditional());
						   
						     if(filmvo.getNoOfFilmsWated()!=null && !filmvo.getNoOfFilmsWated().equals(""))
						     wasteused+=Integer.parseInt(filmvo.getNoOfFilmsWated());

						     
					}
					try {
						query = HelperMethodsDAO.getQuery(filename, queryKey);

					} catch (Exception e) {
						throw new HisApplicationExecutionException(
								"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
										+ e);
					}

					try {
						
						
						 populateMAP.put(sq.next(),Integer.toString(totalaused));
		                 populateMAP.put(sq.next(),Integer.toString(wasteused));
		                 
		                 populateMAP.put(sq.next(),Integer.toString(addtionalused));
		                 populateMAP.put(sq.next(),filmvo.getRemarks());
		                 populateMAP.put(sq.next(),filmvo.getRequisitionDNo());

								
					} catch (Exception e) {
						throw new HisApplicationExecutionException(
								"film used dao.populateMAP::" + e);
					}
					try {

						HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
								.getConnection(), query, populateMAP);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						throw new HisDataAccessException("Exception While insertion in  film work order Table");
					}

				}

				
				public List<filmUsedVO> getprevrequisition(String crno, UserVO _userVO)
				{
					ResultSet rs = null;
					String query = "";
					Map populateMAP = new HashMap();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
					String queryKey = "SELECT.PATIENT_EPISODE_DTLS.HRGT_EPISODE_DTL_FILM_DATA";

					try
					{
						query = HelperMethodsDAO.getQuery(filename, queryKey);
					}
					catch (Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
					}

					System.out.println("Query ---------> " + query);
					Sequence sq = new Sequence();
					try
					{
						//populateMAP.put(sq.next(), _userVO.getHospitalCode());
						 
						populateMAP.put(sq.next(), crno);
						 
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
					}
					
					filmUsedVO[] dailyPatientVOs = null;
					List<filmUsedVO> lstPatientDetailVO = new ArrayList<filmUsedVO>();
					ValueObject[] valueObjects = null;
					
					try
					{
						rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
						if (!rs.next())
						{
							//throw new HisRecordNotFoundException("No Patient Record");
						}
						else
						{
							rs.beforeFirst();
							valueObjects = HelperMethods.populateVOfrmRS(filmUsedVO.class, rs);
							//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
							for (int i = 0; i < valueObjects.length; i++)
							{
								//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
								lstPatientDetailVO.add((filmUsedVO)valueObjects[i]);
							}
						}
					}
					catch (Exception e)
					{
						if (e.getClass() == HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException(e.getMessage());
						}
						else throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
					}
					return lstPatientDetailVO;
				}
				
}





