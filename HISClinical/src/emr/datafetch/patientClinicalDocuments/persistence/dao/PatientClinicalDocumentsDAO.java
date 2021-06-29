/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Manisha Gangwar
 ## Module Name					        : OPD IPD
 ## Process/Database Object Name	    : PATIENT CLINICAL DOCUMENTS
 ## Purpose						        : 
 ## Date of Creation					: 04-NOV-2017 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package emr.datafetch.patientClinicalDocuments.persistence.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileDiagnosisDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;
import opd.dao.OpdDaoConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ehr.EHRConfig;
import emr.vo.PatientClinicalDocDetailVO;
import emr.vo.PatientClinicalDocTypeVO;
import emr.vo.PatientClinicalDocumentsVO;

public class PatientClinicalDocumentsDAO extends DataAccessObject implements PatientClinicalDocumentsDAOi{
	Logger log;

	/**
	 * Constructor for Setting Transaction Context
	 */
	public PatientClinicalDocumentsDAO(TransactionContext _transactionContext)
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


	public List<PatientClinicalDocTypeVO> getClinicalDocumentTypes(String usablity,	String generationMode, UserVO _userVO) {
		
		ResultSet rs = null;
		String query = "";
		//Map populateMAP = new HashMap();
		Connection conn = super.getTransactionContext().getConnection();    
		String errorMsg = "";
		//String filename = EHRConfig.GET_CLINICAL_DOCUMENTS_TYPE;
		//String queryKey = "SELECT.EHRTS_CLINICAL_DOC_TYPE_MST";        
		
		
		
		List<PatientClinicalDocTypeVO> documentTypeVOList = new ArrayList<PatientClinicalDocTypeVO>();
		PatientClinicalDocTypeVO documentTypeVO = null;
		
		try {
			
			//Procedure strProc = new Procedure(EHRConfig.GET_CLINICAL_DOCUMENTS_TYPE);
			Procedure strProc = new Procedure("pkg_ehr_view.proc_pat_clinical_doc_type_details");
			
			strProc.addInParameter(1, Types.VARCHAR, "1");
			strProc.addInParameter(2, Types.VARCHAR, Config.SUPER_USER_HOSPITAL_CODE);
			strProc.addInParameter(3, Types.VARCHAR, Config.IS_VALID_ACTIVE);
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);//OracleTypes.CURSOR);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);
				
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); 
			} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"OpdEssentialDAO.populateMAP::" + e);
		}

		try {
			//rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					documentTypeVO = new PatientClinicalDocTypeVO();
					HelperMethods.populateVOfrmRS(documentTypeVO, rs);
					documentTypeVOList.add(documentTypeVO);
				}
			}
		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}
		return documentTypeVOList;
	}
	
	
	public List<PatientClinicalDocDetailVO> getEpisodePatientDocuments(PatientClinicalDocDetailVO _patDocumentVO, UserVO _userVO)
	{
		String query = "";
		Sequence sq = new Sequence();
		Connection conn = super.getTransactionContext().getConnection();    
		String errorMsg = "";
		ResultSet rs = null;
		try
		{
			Procedure strProc = new Procedure("pkg_ehr_view.proc_patient_clinical_doc_details");		
			strProc.addInParameter(1, Types.VARCHAR, "1");
			strProc.addInParameter(2, Types.VARCHAR, _patDocumentVO.getPatCrNo());
			strProc.addInParameter(3, Types.VARCHAR, _patDocumentVO.getEpisodeCode());
			strProc.addInParameter(4, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(5, Types.VARCHAR,  _patDocumentVO.getAdmNo());
			strProc.addInParameter(6, Types.VARCHAR, _userVO.getSeatId());
			strProc.addInParameter(7, Types.VARCHAR, Config.IS_VALID_ACTIVE);
			strProc.addOutParameter(8, Types.VARCHAR);
			strProc.addOutParameter(9, Types.REF);//OracleTypes.CURSOR);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(8);
			rs = (ResultSet) strProc.getParameterAt(9);
			
					
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientDocumentDetailDAO:)::" + e);
		}

		List<PatientClinicalDocDetailVO> lstPatDocuments = new ArrayList<PatientClinicalDocDetailVO>();
		ValueObject[] valueObjects = null;
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientClinicalDocDetailVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstPatDocuments.add((PatientClinicalDocDetailVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDocumentDetailDAO:getEpisodePatientDocuments::Patient Episode Documents:: " + e);
		}
		return lstPatDocuments;
	}

	/**
	 * Getting Patient Episode Documents List
	 * @param _patDocumentVO Patient Document Detail VO
	 * @param _userVO User VO
	 * @return List of Patient Document VO of Previous Documents
	 */
	public List<PatientClinicalDocumentsVO> getEpisodePatientDocuments1(PatientClinicalDocumentsVO _patDocumentVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		
		//String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		//String queryKey = "SELECT.PAT_EPISODE_DOCUMENTS.HPMRT_PAT_DOCUMENT_DTL";
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;  
		String queryKey = "SELECT.PAT_EPISODE_DOCUMENTS.HPMRT_PAT_DOCUMENT_DTL";
				
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			populateMap.put(sq.next(), _patDocumentVO.getPatCrNo());
			populateMap.put(sq.next(), _patDocumentVO.getEpisodeCode());
			populateMap.put(sq.next(), _userVO.getSeatId());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			//Commented by Akash Singh [as per Sir discussed] on date 02-03-2015
			//populateMap.put(sq.next(), OpdConfig.DOCUMENT_TYPE_DISCHARGE);
			populateMap.put(sq.next(), _patDocumentVO.getAdmissionNo());
			populateMap.put(sq.next(), _userVO.getSeatId());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			populateMap.put(sq.next(), EHRConfig.DOCUMENT_TYPE_DISCHARGE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientDocumentDetailDAO:populateMap(_patientVO,populateMAP)::" + e);
		}

		List<PatientClinicalDocumentsVO> lstPatDocuments = new ArrayList<PatientClinicalDocumentsVO>();
		ValueObject[] valueObjects = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientClinicalDocumentsVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstPatDocuments.add((PatientClinicalDocumentsVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDocumentDetailDAO:getEpisodePatientDocuments::Patient Episode Documents:: " + e);
		}
		return lstPatDocuments;
	}


	
	public List<PatientClinicalDocDetailVO> getClinicalSectionCompostionsList(PatientClinicalDocDetailVO clinicalDocVO, UserVO _userVO)
	
	{
		
		String query = "";
		Sequence sq = new Sequence();
		Connection conn = super.getTransactionContext().getConnection();    
		String errorMsg = "";
		ResultSet rs = null;
		//String mode = null;
		String docType = clinicalDocVO.getDocumentType().split("#")[0];
		
	/*	if(docType.equals("17"))
		{
			mode = "2";
		}
		else
		{
			mode = "1";
		}*/
		try
		{
			Procedure strProc = new Procedure("pkg_ehr_view.proc_patient_doc_clinical_section_comp_details");
			//strProc.addInParameter(1, Types.VARCHAR, "1");
			strProc.addInParameter(1, Types.VARCHAR, "2");
			strProc.addInParameter(2, Types.VARCHAR, clinicalDocVO.getDocumentType().split("#")[0]);
			strProc.addInParameter(3, Types.VARCHAR,clinicalDocVO.getDeptUnitcode());
			//strProc.addInParameter(4, Types.VARCHAR,Config.SUPER_HOSPITAL_CODE );
			strProc.addInParameter(4, Types.VARCHAR,_userVO.getHospitalCode());
			strProc.addInParameter(5, Types.VARCHAR,"");
			strProc.addInParameter(6, Types.VARCHAR,_userVO.getSeatId());
			strProc.addInParameter(7, Types.VARCHAR,_userVO.getSeatId());
			strProc.addInParameter(8, Types.VARCHAR,(clinicalDocVO.getWardCode()==null ||clinicalDocVO.getWardCode().trim().equals(""))?"0":clinicalDocVO.getWardCode());
			strProc.addInParameter(9, Types.VARCHAR, Config.IS_VALID_ACTIVE);
			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addOutParameter(11, Types.REF);//OracleTypes.CURSOR);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(10);
			rs = (ResultSet) strProc.getParameterAt(11);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		// log.error(query + "\n");
		/*
		 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
		 */

		List<PatientClinicalDocDetailVO> lstClinicalDocDetailVO = new ArrayList<PatientClinicalDocDetailVO>();
		ValueObject[] valueObjects = null;
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientClinicalDocDetailVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstClinicalDocDetailVO.add((PatientClinicalDocDetailVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDocumentDetailDAO:getEpisodePatientDocuments::Patient Episode Documents:: " + e);
		}
		return lstClinicalDocDetailVO;
	}
	
	public String createDocumentId(PatientClinicalDocDetailVO clinicalDocVO, UserVO _userVO)
	{
		Sequence sq = new Sequence();
		String documentId;
		

		try
		{
			if(clinicalDocVO.getDocumentId()==null || clinicalDocVO.getDocumentId().equals(""))
			{
				Procedure proc = new Procedure(EHRConfig.PROCEDURE_GET_DOCUMENT_ID);
				proc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
				proc.setReturnType(Types.VARCHAR);
				documentId = (String) proc.execute(super.getTransactionContext().getConnection());
				clinicalDocVO.setDocumentId(documentId);
			}
			else
				documentId = clinicalDocVO.getDocumentId();
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatProfileDtlD::=call getDocumentId" + e);
		}

		
		return documentId;
	}
	
public boolean saveClinicalSectionCompositionsDetail(PatientClinicalDocDetailVO clinicalDocVO, String pmode, UserVO _userVO)
	
	{
		
		String query = "";
		Sequence sq = new Sequence();
		Connection conn = super.getTransactionContext().getConnection();    
		String errorMsg = "";
		ResultSet rs = null;
		boolean flag=true;
		try
		{
				
			Procedure strProc = new Procedure("pkg_ehr_dml.proc_save_pat_clinical_document_dtl");
			strProc.addInParameter(1, Types.VARCHAR, pmode); //p_mode
			strProc.addInParameter(2, Types.VARCHAR, clinicalDocVO.getDocumentId()); //p_docid 
			strProc.addInParameter(3, Types.VARCHAR,"");   //p_slno
			strProc.addInParameter(4, Types.VARCHAR,clinicalDocVO.getPatCrNo());  //p_crno
			strProc.addInParameter(5, Types.VARCHAR,clinicalDocVO.getEpisodeCode());   //p_episodecode
			strProc.addInParameter(6, Types.VARCHAR,clinicalDocVO.getVisitNo());   //p_visitno
			strProc.addInParameter(7, Types.VARCHAR,clinicalDocVO.getDocumentTitle());   //p_doctitle
			strProc.addInParameter(8, Types.VARCHAR,clinicalDocVO.getAdmNo());   //p_admno
			strProc.addInParameter(9, Types.VARCHAR,clinicalDocVO.getRemark() );  //p_remarkc
			strProc.addInParameter(10, Types.VARCHAR,Config.IS_VALID_ACTIVE);  //p_isvalid
			strProc.addInParameter(11, Types.VARCHAR,clinicalDocVO.getDocumentType().split("#")[0]);  //p_doctypecode
			strProc.addInParameter(12, Types.VARCHAR,clinicalDocVO.getSeatId());  //p_seatid
			strProc.addInParameter(13, Types.VARCHAR,clinicalDocVO.getCreatedBy());  //p_created_by_empid
			strProc.addInParameter(14, Types.VARCHAR,"");  //p_entry_date
			strProc.addInParameter(15, Types.VARCHAR,clinicalDocVO.getModifiedBy());  //p_modified_by_empid
			strProc.addInParameter(16, Types.VARCHAR,clinicalDocVO.getGeneratedBy());  //p_generated_by_empid
			strProc.addInParameter(17, Types.VARCHAR,"");  //p_lstmod_date
			strProc.addInParameter(18, Types.VARCHAR,clinicalDocVO.getDataOwner());  //p_data_owner
			strProc.addInParameter(19, Types.VARCHAR,_userVO.getHospitalCode());  //p_hospital_code
			strProc.addInParameter(20, Types.VARCHAR,"");  //p_lstmod_seatid
			strProc.addInParameter(21, Types.VARCHAR,"");  //p_data_status
			strProc.addInParameter(22, Types.VARCHAR,clinicalDocVO.getDeptUnitcode());  //p_deptunitcode
			strProc.addInParameter(23, Types.VARCHAR,EHRConfig.CLINICAL_DOCUMENT_STATUS_INPROCESS);  //p_doc_status
			strProc.addInParameter(24, Types.VARCHAR,"");  //p_emp_no
			strProc.addInParameter(25, Types.VARCHAR,"");  //p_lstmod_action
			strProc.addInParameter(26, Types.VARCHAR,"");  //p_lstmod_reason
			strProc.addInParameter(27, Types.VARCHAR,clinicalDocVO.getClinicalSectionCode());  //p_clinical_section_code
			strProc.addInParameter(28, Types.VARCHAR,clinicalDocVO.getClinicalSecCompCode());  //p_clinical_sec_comp_code
			strProc.addInParameter(29, Types.VARCHAR,clinicalDocVO.getClinicalCompSelectJSON());  //p_section_selected_json
			strProc.addInParameter(30, Types.VARCHAR,clinicalDocVO.getClinicalDocCompHTMLJSON());  //p_section_data_html
			strProc.addInParameter(31, Types.VARCHAR,clinicalDocVO.getClinicalSectionOrder());  // p_section_order
			strProc.addInParameter(32, Types.VARCHAR,clinicalDocVO.getClinicalSecCompOrder());  // p_section_comp_order
			strProc.addInParameter(33, Types.VARCHAR,"");  //p_section_status
			strProc.addInParameter(34, Types.VARCHAR,"");  //p_section_data_json 
			strProc.addInParameter(35, Types.VARCHAR,clinicalDocVO.getClinicalDocCompSelectJSON());    // doc json
			strProc.addOutParameter(36, Types.VARCHAR);
			//strProc.addOutParameter(11, Types.REF);//OracleTypes.CURSOR);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(36);
			//rs = (ResultSet) strProc.getParameterAt(11);
			
			if(errorMsg==null || errorMsg.isEmpty())
				flag=true;
			else flag=false;
			

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		// log.error(query + "\n");
		/*
		 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
		 */

		
		return flag;
	}


public void updateDocumentStatus(PatientClinicalDocDetailVO clinicalDocVO, UserVO _userVO)
{
	
	String query = "";
	Sequence sq = new Sequence();
	Connection conn = super.getTransactionContext().getConnection();    
	String errorMsg = "";
	ResultSet rs = null;
	boolean flag=true;
	try
	{
		String pmode="1";	
		Procedure strProc = new Procedure("pkg_ehr_dml.proc_update_pat_clinical_document_status");
		strProc.addInParameter(1, Types.VARCHAR, pmode); //p_mode
		strProc.addInParameter(2, Types.VARCHAR, clinicalDocVO.getDocumentId()); //p_docid 
		strProc.addInParameter(3, Types.VARCHAR,EHRConfig.CLINICAL_DOCUMENT_STATUS_GENERATED);   //doc status
		strProc.addInParameter(4, Types.VARCHAR,Config.IS_VALID_ACTIVE);  //isvalid
		strProc.addInParameter(5, Types.VARCHAR,_userVO.getHospitalCode());  // hosp code
		strProc.addInParameter(6, Types.VARCHAR,clinicalDocVO.getSerialNo());  // slno
		strProc.addOutParameter(7, Types.VARCHAR);
		//strProc.addOutParameter(11, Types.REF);//OracleTypes.CURSOR);
		strProc.execute(conn);
		errorMsg = (String) strProc.getParameterAt(7);
		//rs = (ResultSet) strProc.getParameterAt(11);
		
		if(errorMsg==null || errorMsg.isEmpty())
			flag=true;
		else flag=false;
		
		
	}
	catch (Exception e)
	{
		throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
	}
	/*String query = "";
	Map populateMap = new HashMap();
	Sequence sq = new Sequence();
	String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
	String queryKey = "UPDATE_DOCUMENT_STATUS.HPMRT_PAT_DOCUMENT_DTL";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
	}
	try
	{
		
		populateMap.put(sq.next(), EHRConfig.CLINICAL_DOCUMENT_STATUS_GENERATED);
		if(_patDocumentDtlVO.getAccessType()==null) _patDocumentDtlVO.setAccessType("");
		populateMap.put(sq.next(), _patDocumentDtlVO.getAccessType());
		if(_patDocumentDtlVO.getUserLevel()==null) _patDocumentDtlVO.setUserLevel("");
		populateMap.put(sq.next(), _patDocumentDtlVO.getUserLevel());
		populateMap.put(sq.next(), _patDocumentDtlVO.getDocumentId());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), _userVO.getHospitalCode());
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
	}
	catch (Exception e)
	{

		throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
	}*/
}


public List<PatientClinicalDocDetailVO> fetchDocumentDetails(String pmode,PatientClinicalDocDetailVO clinicalDocVO, UserVO _userVO)
{

	String query = "";
	Sequence sq = new Sequence();
	Connection conn = super.getTransactionContext().getConnection();    
	String errorMsg = "";
	ResultSet rs = null;
	boolean flag=true;
	
	try
	{
		//pmode- 1. generate , 2. modify
		
		
		Procedure strProc = new Procedure("pkg_ehr_view.proc_patient_clinical_doc_details_HTML_json");
		strProc.addInParameter(1, Types.VARCHAR, pmode); //p_mode
		strProc.addInParameter(2, Types.VARCHAR, clinicalDocVO.getDocumentId()); //p_docid 
		if(pmode.equals("1"))  // generate
		strProc.addInParameter(3, Types.VARCHAR,EHRConfig.CLINICAL_DOCUMENT_STATUS_GENERATED);   //doc status
		if(pmode.equals("2"))  // modify
		strProc.addInParameter(3, Types.VARCHAR,EHRConfig.CLINICAL_DOCUMENT_STATUS_INPROCESS);   //doc status
		strProc.addInParameter(4, Types.VARCHAR, pmode); //p_patcrno
		strProc.addInParameter(5, Types.VARCHAR, pmode); //p_episodecode
		strProc.addInParameter(6, Types.VARCHAR,_userVO.getHospitalCode());  // hosp code
		strProc.addInParameter(7, Types.VARCHAR, pmode); //p_admno
		strProc.addInParameter(8, Types.VARCHAR,Config.IS_VALID_ACTIVE);  //isvalid
		strProc.addOutParameter(9, Types.VARCHAR);
		strProc.addOutParameter(10, Types.REF);//OracleTypes.CURSOR);
		strProc.execute(conn);
		errorMsg = (String) strProc.getParameterAt(9);
		rs = (ResultSet) strProc.getParameterAt(10);
		
		if(errorMsg==null || errorMsg.isEmpty())
			flag=true;
		else flag=false;
		
	}
	
	
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("ProfileDiagnosisDtlDAO.populateMAP::" + e);
	}

	
	List<PatientClinicalDocDetailVO> docDtlVO = new ArrayList<PatientClinicalDocDetailVO>();
	ValueObject[] valueObjects = null;
	try
	{
		if(rs.next())
		{
			rs.beforeFirst();
			valueObjects = HelperMethods.populateVOfrmRS(PatientClinicalDocDetailVO.class, rs);
			for (int i = 0; i < valueObjects.length; i++)
				docDtlVO.add((PatientClinicalDocDetailVO) valueObjects[i]);
	
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
	return docDtlVO;
}





public boolean updateOld(PatientClinicalDocDetailVO clinicalDocVO, String pmode, UserVO _userVO)

{
	
	String query = "";
	Sequence sq = new Sequence();
	Connection conn = super.getTransactionContext().getConnection();    
	String errorMsg = "";
	ResultSet rs = null;
	boolean flag=true;
	try
	{
		
		
		Procedure strProc = new Procedure("pkg_ehr_dml.proc_update_pat_clinical_document_dtl");
		strProc.addInParameter(1, Types.VARCHAR, pmode); //p_mode
		strProc.addInParameter(2, Types.VARCHAR, clinicalDocVO.getDocumentId()); //p_docid 
		strProc.addInParameter(3, Types.VARCHAR,clinicalDocVO.getSerialNo());   //p_slno
		strProc.addInParameter(4, Types.VARCHAR,Config.IS_VALID_DELETED);  //p_isvalid
		strProc.addInParameter(5, Types.VARCHAR,clinicalDocVO.getClinicalDocCompSelectJSON());    // doc json
		strProc.addInParameter(6, Types.VARCHAR, _userVO.getHospitalCode());
		strProc.addInParameter(7, Types.VARCHAR,_userVO.getSeatId());  //p_isvalid
		strProc.addOutParameter(8, Types.VARCHAR);
		//strProc.addOutParameter(11, Types.REF);//OracleTypes.CURSOR);
		strProc.execute(conn);
		errorMsg = (String) strProc.getParameterAt(8);
		//rs = (ResultSet) strProc.getParameterAt(11);
		
		if(errorMsg==null || errorMsg.isEmpty())
			flag=true;
		else flag=false;
		

	}
	catch (Exception e)
	{
		throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
	}
	// log.error(query + "\n");
	/*
	 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
	 */

	
	return flag;
}







/**
 * Removing Patient Profile
 * @param _patProfileDtlVO Patient Profile Detail 
 * @param _userVO User Detail
 */
public void delete(PatientClinicalDocDetailVO _patProfileDtlVO, UserVO _userVO)
{
	String query = "";
	Map populateMap = new HashMap();
	Sequence sq = new Sequence();
	String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
	String queryKey = "DELETE.HPMRT_PAT_PROFILE_DTL";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
	}
	try
	{
		populateMap.put(sq.next(), Config.IS_VALID_DELETED);
		populateMap.put(sq.next(), _patProfileDtlVO.getDocumentId());
		populateMap.put(sq.next(), _patProfileDtlVO.getSerialNo());
		populateMap.put(sq.next(), _userVO.getHospitalCode());
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
	}
	catch (Exception e)
	{

		throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
	}
}

//Added by Vasu on 07.Dec.2018

public List<PatientClinicalDocDetailVO> getClinicalSectionTemplatePrintList(PatientClinicalDocDetailVO clinicalDocVO, UserVO _userVO)

{
	
	String query = "";
	Sequence sq = new Sequence();
	Connection conn = super.getTransactionContext().getConnection();    
	String errorMsg = "";
	ResultSet rs = null;
	String mode = "3";
	String docType = clinicalDocVO.getDocumentType().split("#")[0];
	
	/*if(docType.equals("17"))
	{
		mode = "3";
	}
	else
	{
		mode = "1";
	}*/
	try
	{
		Procedure strProc = new Procedure("pkg_ehr_view.proc_patient_doc_clinical_section_comp_details");
		//strProc.addInParameter(1, Types.VARCHAR, "1");
		strProc.addInParameter(1, Types.VARCHAR, mode);
		strProc.addInParameter(2, Types.VARCHAR, clinicalDocVO.getDocumentType().split("#")[0]);
		strProc.addInParameter(3, Types.VARCHAR,clinicalDocVO.getDeptUnitcode());
		//strProc.addInParameter(4, Types.VARCHAR,Config.SUPER_HOSPITAL_CODE );
		strProc.addInParameter(4, Types.VARCHAR,_userVO.getHospitalCode());
		strProc.addInParameter(5, Types.VARCHAR,"");
		strProc.addInParameter(6, Types.VARCHAR,_userVO.getSeatId());
		strProc.addInParameter(7, Types.VARCHAR,_userVO.getSeatId());
		strProc.addInParameter(8, Types.VARCHAR,clinicalDocVO.getWardCode());
		strProc.addInParameter(9, Types.VARCHAR, Config.IS_VALID_ACTIVE);
		strProc.addOutParameter(10, Types.VARCHAR);
		strProc.addOutParameter(11, Types.REF);//OracleTypes.CURSOR);
		strProc.execute(conn);
		errorMsg = (String) strProc.getParameterAt(10);
		rs = (ResultSet) strProc.getParameterAt(11);

	}
	catch (Exception e)
	{
		throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
	}
	// log.error(query + "\n");
	/*
	 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
	 */

	List<PatientClinicalDocDetailVO> lstClinicalDocDetailVO = new ArrayList<PatientClinicalDocDetailVO>();
	ValueObject[] valueObjects = null;
	try
	{
		if(rs.next())
		{
			rs.beforeFirst();
			valueObjects = HelperMethods.populateVOfrmRS(PatientClinicalDocDetailVO.class, rs);
			for (int i = 0; i < valueObjects.length; i++)
				lstClinicalDocDetailVO.add((PatientClinicalDocDetailVO) valueObjects[i]);
		}
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		else throw new HisDataAccessException("PatientDocumentDetailDAO:getEpisodePatientDocuments::Patient Episode Documents:: " + e);
	}
	return lstClinicalDocDetailVO;
}


}