package registration.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import registration.RegistrationConfig;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Sequence;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.UserVO;

/**
 * SecondaryCategoryChangeDAO is a class which describes all the methods required for database interaction
 * for HRGT_SECCAT_CHANGE_DTL table, for example, insert, update, select and delete.
 * CategoryChangeDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 *
 */
public class SecondaryCategoryChangeDAO extends RegistrationDAO implements SecondaryCategoryChangeDAOi{
	

	/**
	 * Creates a new CategoryChangeDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */	
	public SecondaryCategoryChangeDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}

	/**
	 * Creates category change entry in DB for a patient whose Secondary Category has changed.
	 * @param	_secCatChangeVO	Provides category change details.
	 * @param	_userVO		Provides User details.
	 * @return	ChangeCategoryVO with values stored in DB.
	 */
	public SecondaryCategoryChangeVO create(SecondaryCategoryChangeVO _secCatChangeVO, UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="INSERT.HRGT_SECCAT_CHANGE_DTL";                       
        //Properties properties = new Properties();        
        //call the getQueryMethod with arguments filename,querykey from prop file
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        
        try{
        	populateMap(_secCatChangeVO,populateMAP,_userVO);
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("SecondaryCategoryChangeDAO:populateMap(_secCatChangeVO,populateMAP)::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        return _secCatChangeVO;
	}
	
	/**
	 * Populates the map with the category change details to be entered in the DB.
	 * @param	_secCatChangeVO	Provides category change details to be entered.
	 * @param	_populateMAP	Map containig values which will be used for insert query.
	 */
    public void populateMap(SecondaryCategoryChangeVO _secCatChangeVO, Map _populateMAP,UserVO _userVO)throws SQLException{
    	Sequence sq=new Sequence();
    	System.out.println("_secCatChangeVO.getPatCrNo()"+_secCatChangeVO.getPatCrNo());
    	
    	if( _secCatChangeVO.getValidUpto().equalsIgnoreCase("")|| _secCatChangeVO.getValidUpto().equalsIgnoreCase(" ")|| _secCatChangeVO.getValidUpto().equalsIgnoreCase(null))
    	{
    		_secCatChangeVO.setValidUpto("0");
    	}
    	_populateMAP.put(sq.next(), _secCatChangeVO.getPatCrNo().trim());//-----HRGNUM_PUK
    	_populateMAP.put(sq.next(), _secCatChangeVO.getEpisodeCode().trim());
    	_populateMAP.put(sq.next(), _secCatChangeVO.getPatCrNo().trim());	//-----for Serial Number.
    	_populateMAP.put(sq.next(), _secCatChangeVO.getEpisodeCode().trim());	//-----for Serial Number.
    	_populateMAP.put(sq.next(), _secCatChangeVO.getPatPrimaryCatCode().trim());
    	_populateMAP.put(sq.next(), _secCatChangeVO.getPatPrevSecondaryCatCode());
    	_populateMAP.put(sq.next(), _secCatChangeVO.getPatNewSecondaryCatCode());
    	_populateMAP.put(sq.next(), _secCatChangeVO.getRemarks());
    	_populateMAP.put(sq.next(), _userVO.getIpAddress());
    	_populateMAP.put(sq.next(), _secCatChangeVO.getIsValid().trim());
    	//_populateMAP.put(sq.next(), _secCatChangeVO.getSeatId().trim());
    	_populateMAP.put(sq.next(), _userVO.getSeatId());
    	_populateMAP.put(sq.next(), _userVO.getHospitalCode()); 
    	_populateMAP.put(sq.next(), _secCatChangeVO.getValidUpto());
    	_populateMAP.put(sq.next(), _secCatChangeVO.getValidUpto());
    	 
    }
    
    
    public SecondaryCategoryChangeVO createExtendValidUpto(SecondaryCategoryChangeVO _secCatChangeVO, UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="INSERT.WITH.EXPIRY.DATE.HRGT_SECCAT_CHANGE_DTL";                       
        //Properties properties = new Properties();        
        //call the getQueryMethod with arguments filename,querykey from prop file
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        try{
        	Sequence sq=new Sequence();
        	populateMAP.put(sq.next(), _secCatChangeVO.getPatCrNo().trim());//-----HRGNUM_PUK
        	populateMAP.put(sq.next(), _secCatChangeVO.getEpisodeCode().trim());
        	populateMAP.put(sq.next(), _secCatChangeVO.getPatCrNo().trim());	//-----for Serial Number.
        	populateMAP.put(sq.next(), _secCatChangeVO.getEpisodeCode().trim());	//-----for Serial Number.
        	populateMAP.put(sq.next(), _secCatChangeVO.getPatPrimaryCatCode().trim());
        	populateMAP.put(sq.next(), _secCatChangeVO.getPatPrevSecondaryCatCode());
        	populateMAP.put(sq.next(), _secCatChangeVO.getPatNewSecondaryCatCode());
        	populateMAP.put(sq.next(), _secCatChangeVO.getRemarks());
        	populateMAP.put(sq.next(), _userVO.getIpAddress());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), _userVO.getSeatId()); 
        	populateMAP.put(sq.next(), _userVO.getHospitalCode()); 
        	populateMAP.put(sq.next(), _secCatChangeVO.getExpiryDate());
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("SecondaryCategoryChangeDAO:populateMap(_secCatChangeVO,populateMAP)::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        return _secCatChangeVO;
	}
	
	    
}



