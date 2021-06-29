package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ConsultationProfileDtlVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConsultationProfileDtlDAO extends DataAccessObject implements ConsultationProfileDtlDAOi{
	Logger log;
	
	public ConsultationProfileDtlDAO(TransactionContext _tx) {
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	public ConsultationProfileDtlVO create(ConsultationProfileDtlVO consultationDtlVO , UserVO _userVO) {
		
		String query =  "" ;
	   	Map populateMAP =new HashMap();    		 	  	
	 	String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
	 	String queryKey="INSERT.HOPT_CONSULTATION_PROFILE_DTL";	 	  
	 	
	 	//call the getQueryMethod with arguments filename,querykey from prop file
	 	try{
	 	    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	   }
	 	catch(Exception e){
	 		e.printStackTrace();
	 		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	 	}
	 	
	 	
	 	try{
			populateMap(consultationDtlVO,populateMAP,_userVO);
	 	}catch(Exception e){
			 throw new HisDataAccessException(":populateMap(,populateMAP)::"+e);
	 	}
	 	
	 	try{
	 		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	 	
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		 throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate"+e);
	 	}	 	
	 	return consultationDtlVO;
		
		
	}

	public void populateMap(ConsultationProfileDtlVO consultationDtlVO,Map _populateMap,UserVO _userVO)throws SQLException{
		Sequence sequence=new Sequence();
		_populateMap.put(sequence.next(),consultationDtlVO.getProfileId());
		_populateMap.put(sequence.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sequence.next(),consultationDtlVO.getMailRequestId());
		_populateMap.put(sequence.next(),_userVO.getSeatId());
		_populateMap.put(sequence.next(),_userVO.getHospitalCode());
		
	}

	
	public List<ConsultationProfileDtlVO> getPatientProfile(UserVO _userVO,String _mailRequestId)
	{
		ConsultationProfileDtlVO consultationVO=null;
		List <ConsultationProfileDtlVO> consultationVOList=new ArrayList<ConsultationProfileDtlVO>();
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="SELECT.BY_REQID.HOPT_CONSULTATION_PROFILE_DTL";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
         populateMAP.put(sq.next(),_mailRequestId);
         populateMAP.put(sq.next(),_mailRequestId);
         populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
         populateMAP.put(sq.next(),_userVO.getHospitalCode());
     	try{
 			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	   // if(!rs.next()){
 	 	    	//throw new HisRecordNotFoundException("Mail not exist");	 	    
 	 	    //}
 	 	   
 	 	   //rs.beforeFirst();
 			
 	 	   while(rs.next()){
 	 		   consultationVO=new ConsultationProfileDtlVO();
 	 	   	   HelperMethods.populateVOfrmRS(consultationVO, rs);
 	 	   	   consultationVOList.add(consultationVO);
 	 	   }   
 	    }
 		catch(Exception e){
 			if(e.getClass()==HisRecordNotFoundException.class){
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		 }			 
    
		return consultationVOList;
	}
	
}
