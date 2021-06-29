/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package emr.dataentry.spp.persistence.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import mrd.MrdConfig;
import mrd.vo.MRDDocumentUploadVO;
import opd.OpdConfig;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;
import emr.dataentry.spp.presentation.fb.UniPagePrescriptionFB;
import emr.vo.EHR_PatientProfileDtlVO;
import emr.vo.PatientClinicalDocDetailVO;
import hisglobal.vo.EpisodeVO;

public class UniPagePrescriptionDAO extends DataAccessObject implements UniPagePrescriptionDAOi{
	Logger log;

	/**
	 * Constructor for Setting Transaction Context
	 */
	public UniPagePrescriptionDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}
	public void  getPatDetailOpp(PatientDetailVO ptaientDetailVO, UserVO userVO)
	{
		
		 ResultSet rs=null; 
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		 String queryKey="SELECT_PAT_DETAIL_ONE_PAGE_PRESCRIPTION";
		 Sequence sq=new Sequence();
		 Connection conn=super.getTransactionContext().getConnection();
		 try
		  {
		      query =HelperMethodsDAO.getQuery(filename,queryKey);  
		      populateMAP.put(sq.next(), ptaientDetailVO.getPatCrNo());
		  	  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  
			 
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
					throw new HisRecordNotFoundException();
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
			

			try
			{
				ptaientDetailVO.setDepartmentUnitCode(rs.getString(1));
				ptaientDetailVO.setDepartmentUnitName(rs.getString(3));
				ptaientDetailVO.setVisitDate(rs.getString(4));
				ptaientDetailVO.setVisitReason(rs.getString(5));
				if(ptaientDetailVO.getVisitReason()==null)
				{
					ptaientDetailVO.setVisitReason("NA");
				}
				
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			
	}
	public List getAdvisedByList(String string, UserVO userVO)
	{
			//String deptCode="";
			String errorMsg = "";
			ResultSet rs = null;
			Connection conn = super.getTransactionContext().getConnection();
			try
			{
				Procedure strProc = new Procedure(InpatientConfig.PROCEDURE_GET_PEON_LIST);
				strProc.addInParameter(1, Types.VARCHAR, userVO.getHospitalCode());
				strProc.addInParameter(2, Types.VARCHAR, "2");
				strProc.addInParameter(3, Types.VARCHAR, string);
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.REF);//OracleTypes.CURSOR);
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
	public List getDiagnosisTypeListByDiseaseCode(UserVO _userVO,
			String choiceHospitalCode, String choiceIcdDefaultAndHospitalCode) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DIAGNOSIS.TYPE.HGBT_DIAGNOSIS_TYPE_MST";

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
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), choiceHospitalCode);
		populateMAP.put(sq.next(), choiceIcdDefaultAndHospitalCode);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Diagnosis Type Found");
			}

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
			throw new HisDataAccessException();
		}
		return alRecord;
	}
	
	
	public EpisodeDiagnosisVO[] getPrevDiagnosisDetail(String patCrNo,
			String episodeCode, UserVO _userVO) {
		// TODO Auto-generated method stub
		EpisodeDiagnosisVO[] _previousEpisodeDiagVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS.DIAGNOSIS.HRGT_EPISODE_DIAGNOSIS_DTL";

		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), patCrNo);
		populateMAP.put(sq.next(), episodeCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record for previous diagnosis found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeDiagnosisVO.class, rs);
			_previousEpisodeDiagVO = new EpisodeDiagnosisVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_previousEpisodeDiagVO[i] = (EpisodeDiagnosisVO) vo[i];
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

		return _previousEpisodeDiagVO;
	}
	
	
	public EpisodeVO[] getAllOpenEpisodesVisitedToday(String crNo,UserVO objUserVO_p,String strMode)
	{

		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_ehr_View.proc_hrgt_episode_dtl(?,?,?,?,?, ?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeVO[] _episodeVO;
		ValueObject[] vo ={};
		try
		{
			
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode,1);

			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", crNo,3);
			daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", objUserVO_p.getUserSeatId(),4);
			daoObj.setProcInValue(nProcIndex, "p_dept", "",5);
			daoObj.setProcInValue(nProcIndex, "p_deptunitcode", "",6);
			daoObj.setProcInValue(nProcIndex, "p_visittype", "",7);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.executeProcedureByPosition()::" + e);
		}
		finally {
			if (daoObj != null) {
				daoObj.free();
				
			}
			daoObj = null;
		}	
		try
		{			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Patient did not visit today");

			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			_episodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_episodeVO[i] = (EpisodeVO) vo[i];
	
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}
	
	public EpisodeVO getPatientAdmittedEpisodes(String crNo,UserVO objUserVO_p,String strMode)
	{

		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_ehr_View.proc_hrgt_episode_dtl(?,?,?,?,?, ?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		EpisodeVO _episodeVO;
		ValueObject[] vo ={};
		try
		{
					
			daoObj = new HisDAO("unipage","dao");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeval", strMode,1);

			daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", crNo,3);
			daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", objUserVO_p.getUserSeatId(),4);
			daoObj.setProcInValue(nProcIndex, "p_dept", "",5);
			daoObj.setProcInValue(nProcIndex, "p_deptunitcode", "",6);
			daoObj.setProcInValue(nProcIndex, "p_visittype", "",7);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.executeProcedureByPosition()::" + e);
		}
		finally {
			if (daoObj != null) {
				daoObj.free();
				
			}
			daoObj = null;
		}	
		try
		{			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Patient not admitted");

			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			//_episodeVO = new EpisodeVO[vo.length];
			//for (int i = 0; i < vo.length; i++)
			//{
				_episodeVO = (EpisodeVO) vo[0];

//			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return _episodeVO;
	}
	
	
	
	//Added by Dheeraj on 15-Oct-2018
	
	
	public String getContCrNoWise(String _patCrNo,UserVO _userVO)
	{
		String count="0";
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="SELECT.HRGT_PATIENT_DOCUMENT_DTL.COUNT_CR_NO_WISE";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
       
        populateMAP.put(sq.next(),_patCrNo);
        populateMAP.put(sq.next(),_userVO.getHospitalCode());
        
     	try{
 			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(rs.next()){
 	 	       count=rs.getString(1);
 	 	    }
 	 	 
 	 	   
 	 	 
 	 	  			
 	 	  }
 		catch(Exception e){
 			if(e.getClass()==HisRecordNotFoundException.class){
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		 }			 
    
		return count;
	}
	
	public PatientClinicalDocDetailVO createPDF(PatientClinicalDocDetailVO patientDocDetailVO , UserVO _userVO) {
		//System.out.println("inside create of Signed PDF");
		String query =  "" ;
	   	Map populateMAP =new HashMap();    		 	  	
	 	String filename=OpdConfig.QUERY_FILE_FOR_UNIPAGE_DAO;
	 	String queryKey="INSERT.SPP_PRESC_UPLOAD_FILE";
	 	
	 	
	 	//call the getQueryMethod with arguments filename,querykey from prop file
	 	try{
	 		patientDocDetailVO.setSeatId(_userVO.getSeatId());
	 	    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	   }
	 	catch(Exception e){
	 		e.printStackTrace();
	 		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	 	}
	 	
	 	
		
	 	try{
			populateMap(patientDocDetailVO,populateMAP,_userVO);
	 	}catch(Exception e){
			 throw new HisDataAccessException(":populateMap(,populateMAP)::"+e);
	 	}
	 	
	 	try{
	 		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	 	
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		 throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate"+e);
	 	}	 	
	 	return patientDocDetailVO;
		
	}
	
	
	public void populateMap(PatientClinicalDocDetailVO patientDocDetailVO,Map _populateMap,UserVO _userVO)throws SQLException{
		Sequence sequence=new Sequence();
		_populateMap.put(sequence.next(),patientDocDetailVO.getPatCrNo());
		//Sl_No
		_populateMap.put(sequence.next(),patientDocDetailVO.getSlNo());
		_populateMap.put(sequence.next(),patientDocDetailVO.getEpisodeCode());
		//_populateMap.put(sequence.next(),patientDocDetailVO.getEpisodeVisitNo());
		_populateMap.put(sequence.next(),patientDocDetailVO.getDocumentTitle());
		_populateMap.put(sequence.next(),patientDocDetailVO.getFileName());
		//Doc Id
		_populateMap.put(sequence.next(),patientDocDetailVO.getDocumentType());
		_populateMap.put(sequence.next(),patientDocDetailVO.getDocumentTypeDesc()); //file type
		_populateMap.put(sequence.next(),patientDocDetailVO.getIsValid());
		_populateMap.put(sequence.next(),patientDocDetailVO.getSeatId());
		_populateMap.put(sequence.next(),_userVO.getHospitalCode());
		_populateMap.put(sequence.next(),patientDocDetailVO.getFileType());
		//_populateMap.put(sequence.next(),patientDocDetailVO.getMlcNo());
		//_populateMap.put(sequence.next(),patientDocDetailVO.getAdmissionNo());
	}
	
	
	
	//Added by Dheeraj on 26-Sept-2018 /////////////////////////////////////////////////////////////

public void saveImageToPostgres(PatientClinicalDocDetailVO patientDocDetailVO, byte[] decodedData, UserVO _userVO) throws Exception
{
	
	PreparedStatement ps = null;
	PreparedStatement ps1 = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	try
	{
		/*if(patientDocDetailVO.getSlNo() !=null)
		{
			String query1 = "update hrgt_patient_document_dtl SET gnum_isvalid = 0 where HRGNUM_PUK=? "
					+ "and HRGNUM_S_NO = (SELECT NVL (MAX (hrgnum_s_no)-1,1) FROM hrgt_patient_document_dtl where hrgnum_puk = ?)";
			ps1 = conn.prepareStatement(query1);	
			ps1.setString(1,patientDocDetailVO.getPatCrNo());
		   	ps1.setString(2,patientDocDetailVO.getPatCrNo());
		   	ps1.executeUpdate();
			
		}
		*/
		if(patientDocDetailVO.getDocData()!=null)
		{   
			String query = "update hrgt_patient_document_dtl SET gbyte_doc_data=? where HRGNUM_PUK=? and HRGNUM_S_NO = (SELECT NVL (MAX (hrgnum_s_no),1) FROM hrgt_patient_document_dtl where hrgnum_puk = ?)";
			ps = conn.prepareStatement(query);
			InputStream iss = new ByteArrayInputStream(patientDocDetailVO.getDocData());	
			ps.setBinaryStream(1,iss,patientDocDetailVO.getDocData().length);
			// ps.setBytes(1,decodedData);
			ps.setString(2,patientDocDetailVO.getPatCrNo());
			ps.setString(3,patientDocDetailVO.getPatCrNo());
			ps.executeUpdate();
		   
		}
	}
	catch(Exception e)
	   	{
	   		
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) {  se.printStackTrace();}
	   	}
		
}


public byte[] latestFetchImagePostgres(PatientClinicalDocDetailVO patDocDetailVO) throws Exception
{
	PreparedStatement ps = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	byte[] imgBytes = null;
	InputStream fis=null;
	try
	{
		
		
		String query = "select gbyte_doc_data from hrgt_patient_document_dtl where hrgnum_puk = ? and hrgnum_s_no = ?";
		 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	   	 ps.setString(1,patDocDetailVO.getPatCrNo());
	   	 ps.setString(2, patDocDetailVO.getSerialNo());
		 ResultSet resultSet = ps.executeQuery();
		 
		 if(resultSet!=null && resultSet.next())
			{
			 Base64 codec = new Base64();
			 imgBytes = Base64.decodeBase64(resultSet.getBytes(1));
			  imgBytes = resultSet.getBytes(1);
			  if(imgBytes!=null)
			  fis = new ByteArrayInputStream(imgBytes); 
				
			  
			}
	}
	catch(Exception e)
	   	{
	   		
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) {  }
	   	}
	return imgBytes;
}


public byte[] latestPrescriptionImagePostgres(UniPagePrescriptionFB fb) throws Exception
{
	PreparedStatement ps = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	byte[] imgBytes = null;
	InputStream fis=null;
	try
	{
		
		
		String query = "select gbyte_doc_data from hrgt_patient_document_dtl where hrgnum_puk = ? and hrgnum_s_no = ?";
		 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	   	 ps.setString(1,fb.getPatCrNo());
	   	 ps.setString(2, fb.getVisitNo());
		 ResultSet resultSet = ps.executeQuery();
		 
		 if(resultSet!=null && resultSet.next())
			{
			 Base64 codec = new Base64();
			 imgBytes = Base64.decodeBase64(resultSet.getBytes(1));
			  imgBytes = resultSet.getBytes(1);
			  if(imgBytes!=null)
			  fis = new ByteArrayInputStream(imgBytes); 
				
			  
			}
	}
	catch(Exception e)
	   	{
	   		
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) {  }
	   	}
	return imgBytes;
}

public List<PatientClinicalDocDetailVO> getPrescriptionDtl(PatientClinicalDocDetailVO clinicalDocVO, UserVO _userVO)

{
	
	//String query = "select (hrgnum_puk :: string) as patCrNo, (hrgnum_s_no :: string) as serialNo, (hrgnum_episode_code:: string) as episodeCode, (hrgstr_doc_title :: string) as documentTitle, (hrgstr_document_id :: string) as documentId, (gnum_seat_id :: string) as seatId, hrgstr_file_name as fileName, (gnum_file_type :: string) as fileType, gbyte_doc_data as docData from hrgt_patient_document_dtl where hrgnum_puk = ? and gnum_file_type = ?";
	String query = "select   hrgnum_s_no   as serialNo,  hrgstr_doc_title   as documentTitle, gdt_entry_date as visitDate,  hrgstr_document_id   as documentId,(select gstr_usr_name  from gblt_user_mst where gnum_userid = ?)as prescribedBy, hrgstr_file_name as fileName,  gnum_file_type   as fileType from hrgt_patient_document_dtl where hrgnum_puk = ? and gnum_file_type=?";
	PreparedStatement ps = null;
	Connection conn = super.getTransactionContext().getConnection();    
	String errorMsg = "";
	ResultSet rs = null;
	try
	{
		 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 ps.setString(1, _userVO.getSeatId());
	   	 ps.setString(2,clinicalDocVO.getPatCrNo());
	   	 ps.setString(3,clinicalDocVO.getFileType());
	   	 rs = ps.executeQuery();
		
	}
	catch (Exception e)
	{
		throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
	}
	// log.error(query + "\n");
	/*
	 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
	 */

	List<PatientClinicalDocDetailVO> prescriptionDtl = new ArrayList<PatientClinicalDocDetailVO>();
	ValueObject[] valueObjects = null;
	try
	{
		if(rs.next())
		{
			rs.beforeFirst();
			valueObjects = HelperMethods.populateVOfrmRS(PatientClinicalDocDetailVO.class, rs);
			for (int i = 0; i < valueObjects.length; i++)
				prescriptionDtl.add((PatientClinicalDocDetailVO) valueObjects[i]);
		}
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		else throw new HisDataAccessException("PatientDocumentDetailDAO:getPrescriptionDtl::Patient Episode Documents:: " + e);
	}
	return prescriptionDtl;
}


public List getPatientPrescriptions(String patCrNo, UserVO _userVO){
	List patientImageDtlVOList=new ArrayList();
	PatientClinicalDocDetailVO imageDtlVO=null;
	ResultSet rs=null;
	String query  = "";
	//String query  = "SELECT hrgnum_puk patCrNo, hrgnum_s_no serialNo, hrgnum_episode_code episodeCode, hrgstr_file_name fileName FROM hrgt_patient_document_dtl where hrgnum_puk =?  and gnum_file_type = 21 order by hrgnum_s_no";
	Sequence sq=new Sequence();
	Map populateMAP =new HashMap();
	//String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
	//String queryKey="SELECT.ALL_IMAGES_BY_CRNO.HRGT_PATIENT_IMAGE_DTL";                       
	//Properties properties = new Properties();        
	//call the getQueryMethod with arguments filename,querykey from prop file
	String SerialNo="";
	try{
		query  = "SELECT hrgnum_puk patCrNo, hrgnum_s_no serialNo, hrgnum_episode_code episodeCode, hrgstr_file_name fileName FROM hrgt_patient_document_dtl where hrgnum_puk =?  and gnum_file_type = 21 order by hrgnum_s_no";
		//query =HelperMethodsDAO.getQuery(filename,queryKey);
	}
	catch(Exception e){
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	}
	
	
	try{
		populateMAP.put(sq.next(),patCrNo);
		
	}
	catch(Exception e){
		throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
	}
	try{
		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		if(!rs.next()){
			throw new HisRecordNotFoundException("No Image Found");
		}
		else{
			rs.beforeFirst();
			while(rs.next()){
				imageDtlVO=new PatientClinicalDocDetailVO();
				HelperMethods.populateVOfrmRS(imageDtlVO, rs);
				patientImageDtlVOList.add(imageDtlVO);
				
			}
		}
	}
	
	catch(Exception e){
		if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException(e.getMessage());
		}
		else{	
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}	
	}
	return patientImageDtlVOList;
}

//Added by Vasu on 13.Nov.2018
public byte[] getLatestPDFFromPostgres(EHR_PatientProfileDtlVO profileDtlVO) throws Exception
{
	PreparedStatement ps = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	byte[] imgBytes = null;
	InputStream fis=null;
	try
	{
		
		
		//String query = "select gbyte_doc_data from hrgt_patient_document_dtl where hrgnum_puk = ? and hrgnum_s_no = ?";
		 String query = "select gbyte_profile_pdf_data from hpmrt_pat_profile_dtl where hpmrnum_profile_id = ? and hrgnum_puk = ? and hrgnum_episode_code = ? and hrgnum_visit_no = ? and hpmrnum_slno = (select max(hpmrnum_slno) from hpmrt_pat_profile_dtl where hpmrnum_profile_id = ? and hrgnum_puk = ? and hrgnum_episode_code = ? and hrgnum_visit_no = ?)";
		 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	   	 
		 ps.setString(1,profileDtlVO.getProfileId());
		 ps.setString(2,profileDtlVO.getPatCrNo());
		 ps.setString(3, profileDtlVO.getEpisodeCode());
	   	 ps.setString(4, profileDtlVO.getEpisodeVisitNo());
	   	 ps.setString(5,profileDtlVO.getProfileId());
		 ps.setString(6,profileDtlVO.getPatCrNo());
		 ps.setString(7, profileDtlVO.getEpisodeCode());
	   	 ps.setString(8, profileDtlVO.getEpisodeVisitNo());
	   	 
		 ResultSet resultSet = ps.executeQuery();
		 
		 if(resultSet!=null && resultSet.next())
			{
			 //Base64 codec = new Base64();
			 //imgBytes = Base64.decodeBase64(resultSet.getBytes(1));
			  imgBytes = resultSet.getBytes(1);
			  if(imgBytes!=null)
			  fis = new ByteArrayInputStream(imgBytes); 
				
			  
			}
	}
	catch(Exception e)
	   	{
	   		
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) {  }
	   	}
	return imgBytes;
}


	
}