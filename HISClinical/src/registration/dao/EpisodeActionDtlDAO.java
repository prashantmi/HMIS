package registration.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

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
import hisglobal.vo.EpisodeActionDtlVO;

import hisglobal.vo.EpisodeVO;

import hisglobal.vo.UserVO;

public class EpisodeActionDtlDAO extends DataAccessObject implements EpisodeActionDtlDAOi{
	public EpisodeActionDtlDAO(TransactionContext _tx) {
		super(_tx);		
	}

	public void create(EpisodeActionDtlVO _episodeActionDtlVO, UserVO _userVO){
		  String query = "" ;
	   	  Map populateMAP =new HashMap();    		 	  	
	 	  String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
	 	  String queryKey="INSERT.HRGT_EPISODE_ACTION_DTL";
	 	  Connection conn =super.getTransactionContext().getConnection();	
	 	  
	 	 _episodeActionDtlVO.setSeatId(_userVO.getSeatId());
	 	 _episodeActionDtlVO.setHospitalCode(_userVO.getHospitalCode());
	 	  //call the getQueryMethod with arguments filename,querykey from prop file
	 	  try{
	 	       query =HelperMethodsDAO.getQuery(filename,queryKey);		 	       
	 	       populateMap(_episodeActionDtlVO,populateMAP);
	 	     }
	 	  catch(Exception e)
	 	  {	
	 		 throw new HisDataAccessException("INSERT FAILED::EpisodeActionDtlDAO::createHelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
	 	  }    		 	  
	 	   System.out.println("query"+query);	 		 
	 	 
	 	 try{		 		 
	 		 
	 	      HelperMethodsDAO.executeQuery(conn,query,populateMAP);	 					 	 		
	 		 
	 	 }catch(Exception e){
	 		throw new HisDataAccessException("DailyPatientDAO:populateMap(_dailyPatientVO,psDailyPatDtl)::"+e);
	 	 }
	}//END OF CREATE
	
	
	
	public void populateMap(EpisodeActionDtlVO _episodeActionDtlVO,Map _populateMAP )throws HisApplicationExecutionException 
	{
		  System.out.println("inside populateMap"+_episodeActionDtlVO);		
		  Sequence sq=new Sequence();		
		  _populateMAP.put(sq.next(),_episodeActionDtlVO.getEpisodeCode());
		  _populateMAP.put(sq.next(),_episodeActionDtlVO.getEpisodeVisitNo());
		  _populateMAP.put(sq.next(),_episodeActionDtlVO.getPatCrNo());
		 	
		 _populateMAP.put(sq.next(),_episodeActionDtlVO.getEpisodeActionCode());		 
		 _populateMAP.put(sq.next(),_episodeActionDtlVO.getDiagonisticTypeCode());		
		 _populateMAP.put(sq.next(),_episodeActionDtlVO.getDiagnosticDesc());
		 _populateMAP.put(sq.next(),_episodeActionDtlVO.getComplainDtl());
		 _populateMAP.put(sq.next(),_episodeActionDtlVO.getRemarks());
		 _populateMAP.put(sq.next(),_episodeActionDtlVO.getEpisodeActionDate());
		 _populateMAP.put(sq.next(),_episodeActionDtlVO.getEpisodeActionTime());
		 _populateMAP.put(sq.next(),_episodeActionDtlVO.getDepartmentCode());
		 _populateMAP.put(sq.next(),_episodeActionDtlVO.getWardCode());
		
		 _populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		 _populateMAP.put(sq.next(),_episodeActionDtlVO.getSeatId());
		 _populateMAP.put(sq.next(),_episodeActionDtlVO.getHospitalCode());
		  System.out.println("map populated >>>end");	 
	}	
	
	
	public  EpisodeActionDtlVO getActionDtl(EpisodeVO _episodeVO,UserVO _userVO){
		EpisodeActionDtlVO episodeActionVO=new EpisodeActionDtlVO();		
		
		System.out.println("inside getActionDtl  ");
    	Map _populateMapDeathDtl =new HashMap();
    	
    	String query="";
    	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.HRGT_EPISODE_ACTION_DTL";
		
		Sequence sq=new Sequence();
		_populateMapDeathDtl.put(sq.next(),_episodeVO.getPatCrNo());
		_populateMapDeathDtl.put(sq.next(),_episodeVO.getEpisodeCode());
		_populateMapDeathDtl.put(sq.next(),_episodeVO.getEpisodeVisitNo());				
		_populateMapDeathDtl.put(sq.next(),Config.IS_VALID_ACTIVE);
		Connection conn =super.getTransactionContext().getConnection(); 
    	try{
    		query =HelperMethodsDAO.getQuery(filename,queryKey);
    		System.out.println("Query "+query);	 	      
	 	}catch(Exception e){
	 		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	 	}	 	
	 	System.out.println("query"+query);
	 	try{
	 		ResultSet rs=HelperMethodsDAO.executeQuery(conn, query,_populateMapDeathDtl);
	 		System.out.println("hello........"+rs);
	 		if(!rs.next()){
	 			throw new HisRecordNotFoundException();
	 		}	 			
	 			rs.beforeFirst();
	 			HelperMethods.populateVOfrmRS(episodeActionVO, rs);	 		
	 			
	 	  }catch(Exception e){
	 		 if(e.getClass()==HisRecordNotFoundException.class){
					throw new HisRecordNotFoundException();	
				}
				else
	 		  throw new HisDataAccessException("MlcDAO:retrieveByCrNo:HelperMethods :: "+e);
	 	  }
	 	  return episodeActionVO;		
	}
		
}//end of class

