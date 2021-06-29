package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.HealthWorkerDetailVO;
import hisglobal.vo.JSYRegitrationVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JSYRegitrationDAO extends DataAccessObject implements JSYRegitrationDAOi{

	public JSYRegitrationDAO(TransactionContext _tx) {
		super(_tx);
	}

	
	/**
	 * insert Data into Jsy Registration.
	 * @param	_addressVO	Provides address details to be entered.
	 * @param	_userVO		Provides User details.
	 * @return	AddressVO with values stored in DB.
	 */
    public void create(JSYRegitrationVO jRegitrationVO, UserVO _userVO)
    {
        String query  = "";
        Map populateMAP =new HashMap();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERTINTO.HANCT_JSY_REGISTRATION_DTL";                       
        //Properties properties = new Properties();        
        //call the getQueryMethod with arguments filename,querykey from prop file
     
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        try{
        	populateMap(jRegitrationVO,_userVO,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("JSYRegitrationDAO:populateMap(jRegitrationVO,populateMAP)::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("INSERT FAILED:::HelperMethodsDAO.getResultset"+e);
        }
    }
    
    /**
	 * Populates the map with the Jsy Registration details to be entered in the DB.
	 * @param	jRegitrationVO	Provides address details to be entered.
	 * @param	_populateMAP	Map containig values which will be used for insert query.
	 */
    	Sequence sq=new Sequence();
    	public void populateMap(JSYRegitrationVO jRegitrationVO,UserVO _userVO ,Map _populateMAP)throws SQLException{
    	System.out.println("jRegitrationVO.getPatCrNo()"+jRegitrationVO.getPatCrNo());
    	
    	_populateMAP.put(sq.next(), jRegitrationVO.getPatCrNo());//-----HRGNUM_PUK
    	_populateMAP.put(sq.next(), jRegitrationVO.getAncNo());
    	_populateMAP.put(sq.next(), jRegitrationVO.getGravidaNo());
    	_populateMAP.put(sq.next(), jRegitrationVO.getEpisodeCode());
    	_populateMAP.put(sq.next(), jRegitrationVO.getRegistrationAge());
    	_populateMAP.put(sq.next(), jRegitrationVO.getVisitNo());
    	_populateMAP.put(sq.next(), jRegitrationVO.getLiveBirthCount());
    	_populateMAP.put(sq.next(), jRegitrationVO.getAreaType());
    	_populateMAP.put(sq.next(), jRegitrationVO.getBroughtByASHA());
    	_populateMAP.put(sq.next(), jRegitrationVO.getHealthWorkerID());
    	_populateMAP.put(sq.next(), jRegitrationVO.getHealthWorkerName());
    	_populateMAP.put(sq.next(), jRegitrationVO.getHealthWorkerCardNo());
    	_populateMAP.put(sq.next(), jRegitrationVO.getHealthWorkerAddress());
    	_populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
    	_populateMAP.put(sq.next(), _userVO.getSeatId());
    	_populateMAP.put(sq.next(),_userVO.getHospitalCode());
    	_populateMAP.put(sq.next(),_userVO.getIpAddress());
    	
    }
    	
    	
   
    public HealthWorkerDetailVO getHealthworkerDetailForJSYRegistration(String patCrNo, UserVO _userVO)
    	{
    		String query = "";
    		Map populateMAP = new HashMap();
    		Sequence sq = new Sequence();
    		HealthWorkerDetailVO hDetailVO=new HealthWorkerDetailVO();
    		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
    		String queryKey = "SELECT_HEALTHWORKER_DETAIL.HANCT_HEALTH_WORKER_DTL";
    		try
    		{
    			query = HelperMethodsDAO.getQuery(filename, queryKey);
    		}
    		catch (Exception e)
    		{
    			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    		}
    		 populateMAP.put(sq.next(), patCrNo);
    		 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
    		try
    		{
    			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
    			if (rs.next())
    				HelperMethods.populateVOfrmRS(hDetailVO, rs);
    			else{
    				hDetailVO=null;
    			}
    		}
    		catch (Exception e)
    		{
    			//if (e.getClass() == HisRecordNotFoundException.class)
    			//{
    			//	throw new HisRecordNotFoundException(e.getMessage());
    			//}
    			//else 
    				throw new HisDataAccessException("Application Execution Exception" + e);
    		}
    		return hDetailVO;
    	}
 	
    	
    	
    	
    	
    	
	
}
