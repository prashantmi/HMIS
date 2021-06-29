package opd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;

import hisglobal.persistence.TransactionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class DocumentUploadDtlDAO extends DataAccessObject implements DocumentUploadDtlDAOi{
Logger log;
	
	public DocumentUploadDtlDAO(TransactionContext _tx) {
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	public DocumentUploadDtlVO create(DocumentUploadDtlVO documentUploadDtlVO , UserVO _userVO) {
		
		String query =  "" ;
	   	Map populateMAP =new HashMap();    		 	  	
	 	String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
	 	String queryKey="INSERT.HOPT_DOC_UPLOAD_DTL";	 	  
	 	
	 	//call the getQueryMethod with arguments filename,querykey from prop file
	 	try{
	 		documentUploadDtlVO.setSeatId(_userVO.getSeatId());
	 	    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	   }
	 	catch(Exception e){
	 		e.printStackTrace();
	 		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	 	}
	 	
	 	
		
	 	try{
			populateMap(documentUploadDtlVO,populateMAP,_userVO);
	 	}catch(Exception e){
			 throw new HisDataAccessException(":populateMap(,populateMAP)::"+e);
	 	}
	 	
	 	try{
	 		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	 	
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		 throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate"+e);
	 	}	 	
	 	return documentUploadDtlVO;
		
	}
	
	public void populateMap(DocumentUploadDtlVO documentUploadDtlVO,Map _populateMap,UserVO _userVO)throws SQLException{
		Sequence sequence=new Sequence();
		_populateMap.put(sequence.next(),documentUploadDtlVO.getPatCrNo());
		//Sl_No
		_populateMap.put(sequence.next(),documentUploadDtlVO.getDocSlNo());
		_populateMap.put(sequence.next(),documentUploadDtlVO.getEpisodeCode());
		_populateMap.put(sequence.next(),documentUploadDtlVO.getEpisodeVisitNo());
		_populateMap.put(sequence.next(),documentUploadDtlVO.getDocumentTitle());
		_populateMap.put(sequence.next(),documentUploadDtlVO.getDocumentName());
		//Doc Id
		_populateMap.put(sequence.next(),documentUploadDtlVO.getDocumentCode());
		_populateMap.put(sequence.next(),documentUploadDtlVO.getDocumentDirectoryPath()); //file type
		_populateMap.put(sequence.next(),documentUploadDtlVO.getIsValid());
		_populateMap.put(sequence.next(),documentUploadDtlVO.getSeatId());
		_populateMap.put(sequence.next(),_userVO.getHospitalCode());
		_populateMap.put(sequence.next(),documentUploadDtlVO.getFileType());
		_populateMap.put(sequence.next(),documentUploadDtlVO.getMlcNo());
		_populateMap.put(sequence.next(),documentUploadDtlVO.getAdmissionNo());
	}
	 
	public DocumentUploadDtlVO[] getDocumentDetailsByCrNo(String patCrNo,String episodeCode,UserVO _userVO)
	{
		DocumentUploadDtlVO[] documentUploadDtlVOs=null;
		
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
        String queryKey="SELECT.DOCMENT_DETAIL_BY_CRNO.HOPT_DOC_UPLOAD_DTL";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
         populateMAP.put(sq.next(),patCrNo);
        /* populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
         populateMAP.put(sq.next(),episodeCode);
         //added for no episode--shruti shail
         populateMAP.put(sq.next(),patCrNo);
         populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);*/
         
     	
     	try{
 			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(!rs.next()){
 	 	    	throw new HisRecordNotFoundException("No Previous Document");	 	    
 	 	    }
 	 	    rs.beforeFirst();
 	 	   
 	 	  vo=HelperMethods.populateVOfrmRS(DocumentUploadDtlVO.class, rs);
 	 	documentUploadDtlVOs= new DocumentUploadDtlVO[vo.length];
 	 	for(int i=0;i<vo.length;i++)
 	 	{
 	 		documentUploadDtlVOs[i]= (DocumentUploadDtlVO) vo[i];
 	 	}
 	 	   
 	 	  			
 	 	  }
 		catch(Exception e){
 			e.printStackTrace();
 			if(e.getClass()==HisRecordNotFoundException.class){
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		 }			 
    
		return documentUploadDtlVOs;
	}
	
	public DocumentUploadDtlVO[] getDocumentDetailsByEpisode(EpisodeVO _episodeVO,UserVO _userVO)
	{
		DocumentUploadDtlVO[] documentUploadDtlVOs=null;
		
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
        String queryKey="SELECT.DOCMENT_DETAIL_BY_CRNO.HOPT_DOC_UPLOAD_DTL";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
       
         populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
     	
     	try{
 			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(!rs.next()){
 	 	    	throw new HisRecordNotFoundException("No Previous Document Found");	 	    
 	 	    }
 	 	    rs.beforeFirst();
 	 	   
 	 	  vo=HelperMethods.populateVOfrmRS(DocumentUploadDtlVO.class, rs);
 	 	documentUploadDtlVOs= new DocumentUploadDtlVO[vo.length];
 	 	for(int i=0;i<vo.length;i++)
 	 	{
 	 		documentUploadDtlVOs[i]= (DocumentUploadDtlVO) vo[i];
 	 	}
 	 	   
 	 	  			
 	 	  }
 		catch(Exception e){
 			if(e.getClass()==HisRecordNotFoundException.class){
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		 }			 
    
		return documentUploadDtlVOs;
	}
	
	public DocumentUploadDtlVO[] getDocumentDetailsByMlcEpisode(MlcVO _mlcVO,UserVO _userVO)
	{
		DocumentUploadDtlVO[] documentUploadDtlVOs=null;
		
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="SELECT.MLC_DOCUMENT_BY_MLC_EPISODE.HRGT_PATIENT_DOCUMENT_DTL";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
       
        populateMAP.put(sq.next(),_mlcVO.getPatCrNo());
        populateMAP.put(sq.next(),_mlcVO.getEpisodeCode());
         populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
         populateMAP.put(sq.next(),RegistrationConfig.DOCUMENT_TYPE_MLC);
     	
     	try{
 			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(!rs.next()){
 	 	    	throw new HisRecordNotFoundException("No MLC Document Found");	 	    
 	 	    }
 	 	    rs.beforeFirst();
 	 	   
 	 	  vo=HelperMethods.populateVOfrmRS(DocumentUploadDtlVO.class, rs);
 	 	documentUploadDtlVOs= new DocumentUploadDtlVO[vo.length];
 	 	for(int i=0;i<vo.length;i++)
 	 	{
 	 		documentUploadDtlVOs[i]= (DocumentUploadDtlVO) vo[i];
 	 	}
 	 	   
 	 	  			
 	 	  }
 		catch(Exception e){
 			if(e.getClass()==HisRecordNotFoundException.class){
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		 }			 
    
		return documentUploadDtlVOs;
	}
	
	public String getContCrNoWise(String _patCrNo,UserVO _userVO)
	{
		String count="0";
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
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
	
	public void revokeDocument(DocumentUploadDtlVO documentUploadDtlVO , UserVO _userVO) {
		String query =  "" ;
	   	Map populateMAP =new HashMap();    		 	  	
	 	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
	 	String queryKey="UPDATE.HRGT_PATIENT_DOCUMENT_DTL.REVOKE_DOCUMENT";	 	  
	 	Sequence sq=new Sequence();
	 	//call the getQueryMethod with arguments filename,querykey from prop file
	 	try{
	 		documentUploadDtlVO.setSeatId(_userVO.getSeatId());
	 	    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	   }
	 	catch(Exception e){
	 		e.printStackTrace();
	 		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	 	}
	 	
	 	
		
	 	try{
	 		 populateMAP.put(sq.next(),Config.IS_VALID_INACTIVE);
	 		 populateMAP.put(sq.next(),documentUploadDtlVO.getRemoveReason());
	         populateMAP.put(sq.next(),_userVO.getSeatId());
	         populateMAP.put(sq.next(),documentUploadDtlVO.getDocumentName());
	         populateMAP.put(sq.next(),_userVO.getHospitalCode());
	         
	 	}catch(Exception e){
			 throw new HisDataAccessException(":populateMap(,populateMAP)::"+e);
	 	}
	 	
	 	try{
	 		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	 	
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		 throw new HisDataAccessException("UPDATE FAILED::HelperMethodsDAO.excecuteUpdate"+e);
	 	}	 	
	 	
		
	}
	
}
