package opd.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import opd.OpdConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import registration.dao.RegistrationDaoConfig;
import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatProfileDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class PatProfileDtlDAO extends DataAccessObject implements PatProfileDtlDAOi {
Logger log;
	
	public PatProfileDtlDAO(TransactionContext _tx) {
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	public PatProfileDtlVO create(PatProfileDtlVO _patProfileDtlVO,UserVO _userVO){
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="INSERT.HOPT_PAT_PROFILE_DTL.RETRIVE_BY_PROFILE_ID";
        Connection conn = super.getTransactionContext().getConnection();
        String profileId;
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        	}
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        	}
        
        try {
        	if(_patProfileDtlVO.getPatProfileId()==null || _patProfileDtlVO.getPatProfileId().equals("")){
			Procedure proc = new Procedure(OpdDaoConfig.PROCEDURE_GET_PROFILE_ID);
			proc.addInParameter(1, Types.VARCHAR,RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE);
			proc.setReturnType(Types.VARCHAR);
			profileId = (String) proc.execute(conn);
			_patProfileDtlVO.setPatProfileId(profileId);
			_patProfileDtlVO.setSerialNo("1");
        	}
        	else{
        		profileId=_patProfileDtlVO.getPatProfileId();
        	}
        	}
        catch(Exception e){
        	throw new HisDataAccessException("PatProfileDtlD::=call getProfileId" + e);
        	}
        try{
        	populateMAP.put(sq.next(),profileId);
        	populateMAP.put(sq.next(),profileId);
        	populateMAP.put(sq.next(),_patProfileDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),_patProfileDtlVO.getDepartmentCode());
        	populateMAP.put(sq.next(),_patProfileDtlVO.getDepartmentUnitCode());
        	populateMAP.put(sq.next(),_patProfileDtlVO.getPatProfileHeader());
        	populateMAP.put(sq.next(),_patProfileDtlVO.getPatProfileRemark());
        	populateMAP.put(sq.next(),_patProfileDtlVO.getIsValid());
        	populateMAP.put(sq.next(),_userVO.getSeatId());
        	populateMAP.put(sq.next(),_patProfileDtlVO.getLastModifyDate());
        	populateMAP.put(sq.next(),_patProfileDtlVO.getLastModifiedSeatID());
        	populateMAP.put(sq.next(),_patProfileDtlVO.getPrevEffectiveFrom());
        	populateMAP.put(sq.next(),_patProfileDtlVO.getPrevEffectiveTo());
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        }
        catch(Exception e){
        	throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+ e);
        }
        	return _patProfileDtlVO;
	}
	
	public PatProfileDtlVO[] getProfileDtlByCrNo(String _crNo,String _departmentUnitCode,UserVO _userVO){
		String query="";
		Map populateMap =new HashMap();
        Sequence sq=new Sequence();
        PatProfileDtlVO[] patProfileDtlVOs=null;
        ValueObject[] valueObjects=null;
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="SELECT.HOPT_PAT_PROFILE_DTL.PROFILE_DTL_BY_CRNO";
        try{
        	query=HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
        	
        }
        try{
        	populateMap.put(sq.next(),_crNo);
        	populateMap.put(sq.next(),_userVO.getSeatId());
        	populateMap.put(sq.next(),_departmentUnitCode);
        }
        catch(Exception e){
        	throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
        }
        try{
        	ResultSet rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
        	if(!rs.next()){
        		throw new HisRecordNotFoundException("No Previous Profile Exist");
        	}
        	rs.beforeFirst();
        	valueObjects=HelperMethods.populateVOfrmRS(PatProfileDtlVO.class,rs);
        	patProfileDtlVOs=new PatProfileDtlVO[valueObjects.length];
        	for(int i=0;i<valueObjects.length;i++){
        		patProfileDtlVOs[i]=(PatProfileDtlVO)valueObjects[i];
        	}
        }
        catch(Exception e){
        	if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: "+e);		
        }
        return patProfileDtlVOs;
	}
	
public PatProfileDtlVO updateEffectiveFromToDate(PatProfileDtlVO _patProfileDtlVO,UserVO _userVO){
		String query="";
		Map populateMap =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="UPDATE.HOPT_PAT_PROFILE_DTL.EFFECTIVE_FROM_TO";
        try{
        	query=HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
        	
        } 
        try{
       // 	populateMap.put(sq.next(),_patProfileDtlVO.getEffectiveFrom());
        	populateMap.put(sq.next(),_patProfileDtlVO.getPrevEffectiveTo());
        	populateMap.put(sq.next(),_userVO.getSeatId());
        	populateMap.put(sq.next(),_patProfileDtlVO.getIsValid());
        	populateMap.put(sq.next(),_patProfileDtlVO.getPatProfileId());
        	populateMap.put(sq.next(),_patProfileDtlVO.getSerialNo());
        	
        }
        catch(Exception e){
        	throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMap);
        	
        }
        catch(Exception e){
        		 		
			 throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: "+e);		
        }
        return _patProfileDtlVO;
	}
	
}
