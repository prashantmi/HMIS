package ehr.patientTile.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.PatientBulletinDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ehr.EHRConfig;
import registration.RegistrationConfig;

public class EHRSection_PatientTileDAO extends DataAccessObject implements EHRSection_PatientTileDAOi
{
	Logger log;

	public EHRSection_PatientTileDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
	public PatientDetailVO getIpdPatientDtlByCrNo(PatientDetailVO patDtlVO,UserVO userVO)
	{
		PatientDetailVO patientDetailVO=new PatientDetailVO();
		
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_PatientTileDAO");
		String strDBErr;
		ResultSet rs;
		String _pmode="1";
		
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_EHR_SECTION_PATIENT_DETAIL);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", _pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_crno", patDtlVO.getPatCrNo(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_gestweek", Config.ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC,3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admstat_notreported", InpatientConfig.PATIENT_ADMISSION_STATUS_NOT_REPORTED,4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admstat_cancel", InpatientConfig.PATIENT_ADMISSION_STATUS_ADMISSION_CANCELLED,5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admstat_discharge", InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE,6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", userVO.getHospitalCode(),7);
		
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,8); // varchar
		hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,9); // Cursor

		// Executing Procedure 
					hisDAO_p.executeProcedureByPosition(nProcedureIndex);
					// Getting out parameters 
					strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
					rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");
			
					// If Database Error Occurs, No further processing is required. 
					if (strDBErr != null && !strDBErr.equals(""))
					{
						throw new Exception("Data Base Error:" + strDBErr);
					}
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_PatientTileDAO.getIpdPatientDtlByCrNo::hisDAO_p.execute" + EHRConfig.GET_EHR_SECTION_PATIENT_DETAIL
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
		}
		try
		{
				
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
	
	
	
	public PatientDetailVO getIpdPatientDtlByCrNo1(PatientDetailVO patDtlVO,UserVO userVO)
	{
		PatientDetailVO patientDetailVO=new PatientDetailVO();
		String query = "";
		Map populateMap= new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.PATIENTSUMMARY.IPD.RETRIEVE_BY_CRNO.HIPT_PATADMISSION_DTL.HRGT_PATIENT_DTL";
		
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), patDtlVO.getPatCrNo());
		/*populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
*/		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		/*populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
*/		populateMap.put(sq.next(), Config.ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC);
		populateMap.put(sq.next(), Config.ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC);
		
		populateMap.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_NOT_REPORTED);
		populateMap.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMISSION_CANCELLED);
		populateMap.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE );
		populateMap.put(sq.next(), patDtlVO.getPatCrNo());
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
	
	
		
	public String checkPatientAdmStatus(PatientDetailVO patDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMap= new HashMap();
		ResultSet rs;
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.PAT_STATUS.HRGT_PATIENT_DTL";
		
		Sequence sq = new Sequence();
		//Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), patDtlVO.getPatCrNo());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMap.put(sq.next(), userVO.getHospitalCode());
		
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
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("No Status Found");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public PatientDetailVO getOpdPatientDtlByCrNo(PatientDetailVO patDtlVO,UserVO userVO)
	{
		PatientDetailVO patientDetailVO=new PatientDetailVO();
		String query = "";
		Map populateMap= new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.OPD.RETRIEVE_BY_CRNO.HRGT_DAILY_PATIENT_DTL.FROM.PAT_DTL";
		
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		
	
		populateMap.put(sq.next(), patDtlVO.getPatCrNo());
		/*populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);*/
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		/*populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);*/
		populateMap.put(sq.next(), Config.ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC);
		populateMap.put(sq.next(), Config.ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC);
		
		populateMap.put(sq.next(), patDtlVO.getPatCrNo());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE );
		populateMap.put(sq.next(), userVO.getHospitalCode());
		
		/*populateMap.put(sq.next(), patDtlVO.getPatCrNo());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE );
		populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), patDtlVO.getPatCrNo());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE );
		populateMap.put(sq.next(), userVO.getHospitalCode());*/
		
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
				throw new HisRecordNotFoundException("Patient Detail Not Found");
			}
			else
			{
				rs.beforeFirst();
			//	rs.last();
			//	int size=rs.getRow();
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
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return patientDetailVO;
	}

	
	// Checking whether the Patient is Admitted or Not
	public boolean checkPatAdmitted(PatientDetailVO patDtlVO,UserVO userVO)
	{
		boolean check=false;
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
	    String queryKey="CHECK_PATIENT_ADM.HIPT_PATADMISSION_DTL";
	    Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMAP.put(sq.next(), patDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_NOT_REPORTED);
		populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMISSION_CANCELLED);
		populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE);
		populateMAP.put(sq.next(), OpdConfig.YES);
		
	    try
	    {
	        query =HelperMethodsDAO.getQuery(filename,queryKey);
	        
	    }     
	    catch(Exception e)
	    {
	    	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			
			if (!rs.next())
			{
				check=false;
			}
			else
			{
				check=true;
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
		return check;
	}

	
	/**
	## 		Modification Log		:Hospital Code Removed				
	##		Modify Date				: 19-01-2015
	##		Reason	(CR/PRS)		: CR [Hospital code removed from the Table HRGT_PAT_STATUS_MST]
	##		Modify By				: Akash Singh
	*/
public List getPatientStatus(UserVO userVO)
	{
		List patientStatusList=new ArrayList();
		ResultSet rs=null;
		
		//PatientDetailVO patientDetailVO=new PatientDetailVO();
		String query = "";
		Map populateMap= new HashMap();
		String filename =RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;  
						
		String queryKey = "SELECT.PAT_STATUS_LIST.HRGT_PAT_STATUS_MST";
		
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
	
		//populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE );
	
		
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
			patientStatusList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return patientStatusList;
	}
	
	public PatientBulletinDetailVO[] getEssentialBulletinDetails(PatientDetailVO patDtlVO,UserVO userVO)
	{
		PatientBulletinDetailVO[] patientBulletinVO=null;
		ValueObject[] valueObjects=null;
		String query = "";
		Map populateMap= new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.HIPD_PAT_BULLETIN_DTL";
		
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
	
		populateMap.put(sq.next(), patDtlVO.getPatCrNo());
		populateMap.put(sq.next(), patDtlVO.getEpisodeCode());
		//populateMap.put(sq.next(), "10808001");
	    populateMap.put(sq.next(), patDtlVO.getEpisodeVisitNo());
		//populateMap.put(sq.next(), "1");
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
				throw new HisRecordNotFoundException("No Patient Bulletin Detail Found ");
			}
			else {
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(PatientBulletinDetailVO.class, rs);
				patientBulletinVO=new PatientBulletinDetailVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					patientBulletinVO[i]=(PatientBulletinDetailVO)valueObjects[i];
													 }
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
		return patientBulletinVO;
	}
	
	public void savePatientBulletinDetails(PatientBulletinDetailVO _newPatientBulletinVO,UserVO _userVO)
	{
		//List patientStatusList=new ArrayList();
		//ResultSet rs=null;
		
		//PatientDetailVO patientDetailVO=new PatientDetailVO();
		String query = "";
		Map populateMap= new HashMap();
		String filename =InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
						
		String queryKey = "INSERT.HIPD_PAT_BULLETIN_DTL";
		
		
		Sequence sq = new Sequence();
		//Connection conn=super.getTransactionContext().getConnection();
		if(_newPatientBulletinVO.getBulletinSerialNo()==null || _newPatientBulletinVO.getBulletinSerialNo().equals(""))
			_newPatientBulletinVO.setBulletinSerialNo("0");
		
		int bulletinSerialNo=Integer.parseInt(_newPatientBulletinVO.getBulletinSerialNo())+1;
		
		_newPatientBulletinVO.setBulletinSerialNo(bulletinSerialNo+"");
		
		
	
		populateMap.put(sq.next(), _newPatientBulletinVO.getPatCrNo());
		populateMap.put(sq.next(), _newPatientBulletinVO.getEpisodeCode());
		populateMap.put(sq.next(), _newPatientBulletinVO.getEpisodeVisitNo());
		populateMap.put(sq.next(), _newPatientBulletinVO.getBulletinSerialNo());
		populateMap.put(sq.next(), _newPatientBulletinVO.getPatStatusCode());
		populateMap.put(sq.next(), _newPatientBulletinVO.getPatAdmNo());
		populateMap.put(sq.next(), _newPatientBulletinVO.getPatRemarks());
		populateMap.put(sq.next(), _userVO.getUserEmpID());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE );
		populateMap.put(sq.next(), _userVO.getUserSeatId());		
		populateMap.put(sq.next(), _userVO.getHospitalCode());
		populateMap.put(sq.next(), _userVO.getIpAddress() );
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMap);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
		

		
		
	}
	
	public void updatePatientBulletinDetails(PatientBulletinDetailVO _oldPatientBulletinVO,UserVO userVO)
	{
		//List patientStatusList=new ArrayList();
		//ResultSet rs=null;
		
		//PatientDetailVO patientDetailVO=new PatientDetailVO();
		String query = "";
		Map populateMap= new HashMap();
		String filename =InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
						
		String queryKey = "UDPATE.HIPD_PAT_BULLETIN_DTL";
		
		Sequence sq = new Sequence();
		//Connection conn=super.getTransactionContext().getConnection();
		
	
		populateMap.put(sq.next(), _oldPatientBulletinVO.getPatStatusCode());
		populateMap.put(sq.next(), _oldPatientBulletinVO.getPatRemarks());
		populateMap.put(sq.next(), _oldPatientBulletinVO.getPatCrNo());
		populateMap.put(sq.next(), _oldPatientBulletinVO.getEpisodeCode());
		populateMap.put(sq.next(), _oldPatientBulletinVO.getEpisodeVisitNo());
		populateMap.put(sq.next(), _oldPatientBulletinVO.getBulletinSerialNo());
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		
		try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMap);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public boolean checkPatientStatus(String admNo,UserVO userVO)
	{
		boolean flag=false;
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
	    String queryKey="SELECT_PAT_STATUS.HIPT_PATADMISSION_DTL";
	    Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMAP.put(sq.next(), admNo);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		
	    try
	    {
	        query =HelperMethodsDAO.getQuery(filename,queryKey);
	        
	    }     
	    catch(Exception e)
	    {
	    	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			
			if (rs.next())
			{
				 rs.first();
				 if(rs.getString(1).equals(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED))
					 flag=true;
				 else
					 flag=false;
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
		return flag;
	}
	
	public void updatePatStatusForDischargeReq(String patStatus,PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="UPDATE_PAT_STATUS.HIPT_PATADMISSION_DTL";
		 Sequence sq=new Sequence();
		 
		 try
		  {
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		  }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }
		  
		  try
		  {
			  populateMAP.put(sq.next(), patStatus);
			  populateMAP.put(sq.next(), patDischargeReqVO.getIsDead());
			  populateMAP.put(sq.next(), patDischargeReqVO.getNextVisitDate());
			  populateMAP.put(sq.next(), patDischargeReqVO.getDischargeStatus());
			  populateMAP.put(sq.next(), patDischargeReqVO.getRemarks());
			  populateMAP.put(sq.next(), patDischargeReqVO.getAdviceBy());
			  populateMAP.put(sq.next(), patDischargeReqVO.getPatAdmNo());
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}
	
	
	public void updatePatStatusForDischargeRevoke(String patStatus,PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="UPDATE_PAT_STATUS_REVOKE.HIPT_PATADMISSION_DTL";
		 Sequence sq=new Sequence();
		 
		 try
		  {
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		  }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }
		  
		  try
		  {
			  populateMAP.put(sq.next(), patStatus);
			  populateMAP.put(sq.next(), patDischargeReqVO.getIsDead());
			  populateMAP.put(sq.next(), patDischargeReqVO.getNextVisitDate());
			  populateMAP.put(sq.next(), patDischargeReqVO.getDischargeStatus());
			  populateMAP.put(sq.next(), patDischargeReqVO.getRemarks());
			  populateMAP.put(sq.next(), patDischargeReqVO.getAdviceBy());
			  populateMAP.put(sq.next(), "");
			  populateMAP.put(sq.next(), patDischargeReqVO.getPatAdmNo());
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}
	

	public boolean GetBillAccStatus(PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO)
	{
		int AccStatus ;
		 ResultSet rs=null; 
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="SELECT_BILL_ACCOUNT_STATUS.HBLT_PATACCOUNT_DTL";
		 Sequence sq=new Sequence();
		 Connection conn=super.getTransactionContext().getConnection();
		 try
		  {
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		  	  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  populateMAP.put(sq.next(), patDischargeReqVO.getPatAdmNo());	  
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
				AccStatus = rs.getInt(1);
				//System.out.println("Account Status of Patient:"+AccStatus );
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			if(AccStatus == 0)
			return true;
			else
				return false;
		}
	
	public void reopenPatBillingForDischargeRevoke(PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO)
	{
		HisDAO daoObj = null;
		String rflag = null;

		String strProcName = "{call BILL_INTERFACE.dml_online_account_reopen(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Desk","InpatientDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex,"puk", patDischargeReqVO.getPatCrNo() ,2);
			daoObj.setProcInValue(nProcIndex, "seatid", userVO.getSeatId() ,3);
			daoObj.setProcInValue(nProcIndex, "hosp_code", userVO.getHospitalCode(),4);
			daoObj.setProcInValue(nProcIndex, "admno", patDischargeReqVO.getPatAdmNo() ,5);
			daoObj.setProcInValue(nProcIndex, "cancelby", patDischargeReqVO.getAdviceBy() ,6);
			daoObj.setProcInValue(nProcIndex, "cancelreason", patDischargeReqVO.getRemarks() ,7);
			daoObj.setProcOutValue(nProcIndex, "resultflag", 1,8);
			daoObj.setProcOutValue(nProcIndex, "err", 1,9);			
			daoObj.executeProcedureByPosition(nProcIndex);	
			strErr = daoObj.getString(nProcIndex, "err");
			
		//	System.out.println("In reopenPatBillingForDischargeRevoke");
			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				rflag = daoObj.getString(nProcIndex, "resultflag");
				
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			throw new HisApplicationExecutionException("InpatientDAO::"+e);
			

		} finally {
			if (daoObj != null) {
				daoObj.free();
				
			}
			daoObj = null;
		}
	}
	
	
	
	
	
	public PatientDetailVO getIpdDiscPatientDtlByCrNo(PatientDetailVO patDtlVO,UserVO userVO)
	{
		PatientDetailVO patientDetailVO=new PatientDetailVO();
		
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_PatientTileDAO");
		String strDBErr;
		ResultSet rs;
		String _pmode="2";  //pmode = 2 DISCHARGE SUMMARY, pmode =1 patient SUMMARY
		
		try
		{
		nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_EHR_SECTION_PATIENT_DETAIL);
		// Setting and Registering In and Out Parameters 
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", _pmode,1);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_crno", patDtlVO.getPatCrNo(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_gestweek", Config.ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC,3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admstat_notreported", InpatientConfig.PATIENT_ADMISSION_STATUS_NOT_REPORTED,4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admstat_cancel", InpatientConfig.PATIENT_ADMISSION_STATUS_ADMISSION_CANCELLED,5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_admstat_discharge", InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE,6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", userVO.getHospitalCode(),7);
		
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,8); // varchar
		hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,9); // Cursor

		// Executing Procedure 
					hisDAO_p.executeProcedureByPosition(nProcedureIndex);
					// Getting out parameters 
					strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
					rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");
			
					// If Database Error Occurs, No further processing is required. 
					if (strDBErr != null && !strDBErr.equals(""))
					{
						throw new Exception("Data Base Error:" + strDBErr);
					}
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EHRSection_PatientTileDAO.getIpdPatientDtlByCrNo::hisDAO_p.execute" + EHRConfig.GET_EHR_SECTION_PATIENT_DETAIL
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) {
				hisDAO_p.free();
			}
			hisDAO_p = null;
		}
		try
		{
				
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

}
