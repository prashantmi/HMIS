package registration.dao;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeDeathVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.RoomMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import registration.RegistrationConfig;

public class RoomMasterDAO extends DataAccessObject{
	
	public RoomMasterDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	public RoomMasterVO[] getRoomSequence(String _deptCode,String _unitCode,UserVO _userVO){
		
		ValueObject[] vo= {};
		RoomMasterVO roomMasterVO[];		
		String query = "" ;
	   	  Map populateMAP =new HashMap();    		 	  	
	 	  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	 	  String queryKey="ESSENTIAL.GET.HGBT_ROOM_MST.SEQUENCE";
	 	  Connection conn =super.getTransactionContext().getConnection();	 	  
	 	  //call the getQueryMethod with arguments filename,querykey from prop file
	 	  try{
	 	       query =HelperMethodsDAO.getQuery(filename,queryKey);
	 	       Sequence sq=new Sequence();	 	      
	 	       populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);	 	       
	 	       populateMAP.put(sq.next(),_unitCode.trim());
	 	       populateMAP.put(sq.next(),_deptCode.trim());
	 	     }
	 	  catch(Exception e)
	 	  {	
	 		 throw new HisDataAccessException("getRomSequence DAO::HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
	 	  }    		 	  
	 	   System.out.println("query"+query);	 		 
	 	 
	 	  try{
				ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);	 
		 	    if(!rs.next()){
		 	    	throw new HisRecordNotFoundException();	 	    
		 	    }
		 	    else{
		 	    	System.out.println("inside asas");
		 	    	rs.beforeFirst();
		 	    	vo = HelperMethods.populateVOfrmRS(RoomMasterVO.class, rs);
					 System.out.println("length"+vo.length);
					 roomMasterVO =new RoomMasterVO[vo.length];
					 for(int i=0;i<vo.length;i++){
						 System.out.println("before casting");
						 roomMasterVO[i]=(RoomMasterVO)vo[i];					 
					 }	 	    	
		 	    }
		 	    System.out.println("after populating _episodeVO");
			}
			catch(Exception e){
				if(e.getClass()==HisRecordNotFoundException.class){
					throw new HisRecordNotFoundException();	
				}
				else			 		
				 throw new HisDataAccessException("ROOMDAO:retrieveByCrNo::Episode Details:: "+e);			 
			 }			 	 	
	    	return roomMasterVO;
	}
	
	
public void updateRoomSequence(RoomMasterVO rmMasterVO,UserVO _userVO){	
				
		  String query = "" ;
	   	  Map populateMAP =new HashMap();    		 	  	
	 	  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	 	  String queryKey="ESSENTIAL.UPDATE.HRGT_DEPT_UNIT_ROOM_MST";
	 	  Connection conn =super.getTransactionContext().getConnection();	 	  
	 	  //call the getQueryMethod with arguments filename,querykey from prop file
	 	  try{
	 	       query =HelperMethodsDAO.getQuery(filename,queryKey);
	 	       Sequence sq=new Sequence();	 	       	 	       
	 	       populateMAP.put(sq.next(),rmMasterVO.getSequenceNo());
	 	       populateMAP.put(sq.next(),rmMasterVO.getDepartmentUnitCode());
	 	       populateMAP.put(sq.next(),rmMasterVO.getDepartmentCode());
	 	       populateMAP.put(sq.next(),rmMasterVO.getRoomCode());
	 	     }
	 	  catch(Exception e)
	 	  {	
	 		 throw new HisDataAccessException("getRomSequence DAO::HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
	 	  }    		 	  
	 	   System.out.println("query"+query);	 		 
	 	 
	 	  try{
				int i = HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);			
				
				 if(i<1){			//-----changes made on 11/oct/2006
					 throw new HisUpdateUnsuccesfullException();
				 }		  
		 	  }
			catch(Exception e){
				if(e.getClass()==HisUpdateUnsuccesfullException.class){
					throw new HisUpdateUnsuccesfullException();				
				}
				else
					throw new HisDataAccessException("EpisodeDAO::while updating data "+e);
			}
			
}
}
		 	    	
					
					
						 	    	
		 	 
	
