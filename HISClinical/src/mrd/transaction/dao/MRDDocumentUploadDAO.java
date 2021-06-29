package mrd.transaction.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import emr.vo.PatientClinicalDocDetailVO;
import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import mrd.MrdConfig;
import mrd.vo.MRDDocumentUploadVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class MRDDocumentUploadDAO extends DataAccessObject {
Logger log;

String isScanned = "0";


	public MRDDocumentUploadDAO(TransactionContext _tx) {
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	public List<MRDDocumentUploadVO> getDocumentDetailsByCrNo(String patCrNo,String episodeCode,UserVO _userVO)
	{
		//MRDDocumentUploadVO[] MRDDocumentUploadVOs=null;
		List mrdRecordDtlVOList=null; ResultSet rs=null; MRDDocumentUploadVO MRDDocumentUploadVO=null;
		ValueObject vo[]= null;
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
      //  String queryKey="SELECT.DOCMENT_DETAIL_BY_CRNO.HOPT_DOC_UPLOAD_DTL";
        String queryKey="SELECT.MRD_RECORDS_DETAIL_BY_CRNO.HPMRT_MRD_RECORD_DTL";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
         populateMAP.put(sq.next(),patCrNo);
         populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        // populateMAP.put(sq.next(),episodeCode);
           
     	
         try
 		{
 			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
 			if(!rs.next()){
 				throw new HisRecordNotFoundException("No Record Found");
 			}
 			else {
 				rs.beforeFirst();
 				mrdRecordDtlVOList=new ArrayList<MRDDocumentUploadVO>();
 				while (rs.next()) {
 					MRDDocumentUploadVO=new MRDDocumentUploadVO();
 					HelperMethods.populateVOfrmRS(MRDDocumentUploadVO, rs);
 					mrdRecordDtlVOList.add(MRDDocumentUploadVO);
 				}
 			}
 			
 		}
 		catch(Exception e)
 		{
 			if(e.getClass()==HisRecordNotFoundException.class){
 				throw new HisRecordNotFoundException(e.getMessage());
 			}
 			else{
 				e.printStackTrace();
 				throw new HisDataAccessException("HisDataAccessException");
 			}	
 		}
 		return mrdRecordDtlVOList;	 
    
		
	}
	
	
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
	
	
	public MRDDocumentUploadVO create(MRDDocumentUploadVO MRDDocumentUploadVO , UserVO _userVO) {
		System.out.println("inside create of EpisodeRefDtlVO");
		String query =  "" ;
	   	Map populateMAP =new HashMap();    		 	  	
	 	String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
	 	String queryKey="INSERT.HOPT_DOC_UPLOAD_DTL";	 	  
	 	
	 	//call the getQueryMethod with arguments filename,querykey from prop file
	 	try{
	 		MRDDocumentUploadVO.setSeatId(_userVO.getSeatId());
	 	    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	   }
	 	catch(Exception e){
	 		e.printStackTrace();
	 		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	 	}
	 	
	 	
		
	 	try{
			populateMap(MRDDocumentUploadVO,populateMAP,_userVO);
	 	}catch(Exception e){
			 throw new HisDataAccessException(":populateMap(,populateMAP)::"+e);
	 	}
	 	
	 	try{
	 		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	 	System.out.println("");
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		 throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate"+e);
	 	}	 	
	 	return MRDDocumentUploadVO;
		
	}
	
	public MRDDocumentUploadVO update(MRDDocumentUploadVO MRDDocumentUploadVO , UserVO _userVO) {
		System.out.println("inside create of EpisodeRefDtlVO");
		String query =  "" ;
	   	Map _populateMap =new HashMap();    		 	  	
	 	String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
	 	String queryKey="UPDATE.ISSCANNED_HPMRT_MRD_RECORD_DTL";	 	  
	 	
	 	//call the getQueryMethod with arguments filename,querykey from prop file
	 	try{
	 		MRDDocumentUploadVO.setSeatId(_userVO.getSeatId());
	 	    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	   }
	 	catch(Exception e){
	 		e.printStackTrace();
	 		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	 	}
	 	
	 	
		
	 	try{
	 		Sequence sq = new Sequence();
	 		_populateMap.put(sq.next(),MrdConfig.MRD_RECORD_DTL_IS_SCANNED_YES);
			_populateMap.put(sq.next(),MRDDocumentUploadVO.getRecordId()); //record Id
			_populateMap.put(sq.next(),_userVO.getHospitalCode());
			_populateMap.put(sq.next(),MRDDocumentUploadVO.getIsValid());
			
				
		
	 	}catch(Exception e){
			 throw new HisDataAccessException(":populateMap(,populateMAP)::"+e);
	 	}
	 	
	 	try{
	 		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,_populateMap);
	 	System.out.println("");
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		 throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate"+e);
	 	}	 	
	 	return MRDDocumentUploadVO;
		
	}
	
	
	
	public void revokeDocument(MRDDocumentUploadVO MRDDocumentUploadVO , UserVO _userVO) {
		String query =  "" ;
	   	Map populateMAP =new HashMap();    		 	  	
	 	String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
	 	String queryKey="UPDATE.HRGT_PATIENT_DOCUMENT_DTL.REVOKE_DOCUMENT";	 	  
	 	Sequence sq=new Sequence();
	 	//call the getQueryMethod with arguments filename,querykey from prop file
	 	try{
	 		MRDDocumentUploadVO.setSeatId(_userVO.getSeatId());
	 	    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	   }
	 	catch(Exception e){
	 		e.printStackTrace();
	 		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	 	}
	 	
	 	
		
	 	try{
	 		 populateMAP.put(sq.next(),Config.IS_VALID_INACTIVE);
	 		 populateMAP.put(sq.next(),MRDDocumentUploadVO.getRemoveReason());
	         populateMAP.put(sq.next(),_userVO.getSeatId());
	         populateMAP.put(sq.next(),MRDDocumentUploadVO.getDocumentName());
	         populateMAP.put(sq.next(),_userVO.getHospitalCode());
	         
	 	}catch(Exception e){
			 throw new HisDataAccessException(":populateMap(,populateMAP)::"+e);
	 	}
	 	
	 	try{
	 		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	 	System.out.println("");
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		 throw new HisDataAccessException("UPDATE FAILED::HelperMethodsDAO.excecuteUpdate"+e);
	 	}	 	
	 	
		
	}
	
	public void populateMap(MRDDocumentUploadVO MRDDocumentUploadVO,Map _populateMap,UserVO _userVO)throws SQLException{
		Sequence sequence=new Sequence();
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getPatCrNo());
		//Sl_No
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getDocSlNo());
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getEpisodeCode());
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getEpisodeVisitNo());
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getDocumentTitle());
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getDocumentName());
		//Doc Id
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getDocumentCode());
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getDocumentDirectoryPath()); //file type
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getIsValid());
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getSeatId());
		_populateMap.put(sequence.next(),_userVO.getHospitalCode());
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getFileType());
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getMlcNo());
		_populateMap.put(sequence.next(),MRDDocumentUploadVO.getAdmissionNo());
	}
	
	
	//Added by Dheeraj on 26-Sept-2018 /////////////////////////////////////////////////////////////

public void saveImageToPostgres(MRDDocumentUploadVO mrdDocumentUploadVO, byte[] decodedData, UserVO _userVO) throws Exception
{
	
	PreparedStatement ps = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	try
	{
		if(mrdDocumentUploadVO.getDocFile()!=null)
		{
		 String query = "update hrgt_patient_document_dtl SET gbyte_doc_data=? where HRGNUM_PUK=? and HRGNUM_S_NO = (SELECT NVL (MAX (hrgnum_s_no),1) FROM hrgt_patient_document_dtl where hrgnum_puk = ?)";
	   	 ps = conn.prepareStatement(query);
	   	 InputStream iss = new ByteArrayInputStream(mrdDocumentUploadVO.getDocFile());	
	   	 ps.setBinaryStream(1,iss,mrdDocumentUploadVO.getDocFile().length);
		// ps.setBytes(1,decodedData);
	   	 ps.setString(2,mrdDocumentUploadVO.getPatCrNo());
	   	 ps.setString(3,mrdDocumentUploadVO.getPatCrNo());
	   	 ps.executeUpdate();
	   	isScanned = "1";
		}
	}
	catch(Exception e)
	   	{
	   		System.out.println("exception in Image saving transaction..."+e); 
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) { System.out.println("error in try block..."+se); }
	   	}
		
}


public byte[] latestFetchImagePostgres(String patCrNo) throws Exception
{
	PreparedStatement ps = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	byte[] imgBytes = null;
	InputStream fis=null;
	try
	{
		
		
		String query = "select gbyte_doc_data from hrgt_patient_document_dtl where hrgnum_puk = ? and gnum_isvalid=1";
		 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	   	 ps.setString(1,patCrNo);
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
	   		System.out.println("exception in Image saving transaction..."+e); 
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) { System.out.println("error in try block..."+se); }
	   	}
	return imgBytes;
}




// to set isScanned Value
public void setUploadedStatus(MRDDocumentUploadVO mrdDocumentUploadVO, byte[] decodedData, UserVO _userVO) throws Exception
{
	
	PreparedStatement ps = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	try
	{
		if(isScanned!= "0")
		{
		 String query = "update HPMRT_MRD_RECORD_DTL SET hpmrnum_is_scanned=? where HRGNUM_PUK=?";
	   	 ps = conn.prepareStatement(query);
	   	 //String isScanned = mrdDocumentUploadVO.getIsValid();
	   	 //InputStream iss = new ByteArrayInputStream(mrdDocumentUploadVO.getDocFile());	
	   	// ps.setBinaryStream(1,iss,mrdDocumentUploadVO.getDocFile().length);
		// ps.setBytes(1,decodedData);
	   	 ps.setString(1, isScanned);
	   	 ps.setString(2,mrdDocumentUploadVO.getPatCrNo());
	   	// ps.setString(3,mrdDocumentUploadVO.getPatCrNo());
	   	 ps.executeUpdate();
		}
	}
	catch(Exception e)
	   	{
	   		System.out.println("exception in Setting isScanned value transaction..."+e); 
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving value to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) { System.out.println("error in try block..."+se); }
	   	}
		
}



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	


	

}
