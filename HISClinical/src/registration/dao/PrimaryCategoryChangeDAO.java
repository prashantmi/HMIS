package registration.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import registration.RegistrationConfig;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PrimaryCategoryChangeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

/**
 * CategoryChangeDAO is a class which describes all the methods required for database interaction
 * for HRGT_PRICAT_CHANGE_DTL table, for example, insert, update, select and delete.
 * CategoryChangeDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 *
 */
public class PrimaryCategoryChangeDAO extends RegistrationDAO implements PrimaryCategoryChangeDAOi{
	

	/**
	 * Creates a new CategoryChangeDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */	
	public PrimaryCategoryChangeDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}

	/**
	 * Creates category change entry in DB for a patient whose Secondary Category has changed.
	 * @param	_changeCategoryVO	Provides category change details.
	 * @param	_userVO		Provides User details.
	 * @return	ChangeCategoryVO with values stored in DB.
	 */
	public PrimaryCategoryChangeVO create(PrimaryCategoryChangeVO _primCatChangeVO, UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="INSERT.HRGT_PRICAT_CHANGE_DTL";
        //Properties properties = new Properties();
        //call the getQueryMethod with arguments filename,querykey from prop file
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	_primCatChangeVO.setSeatId(_userVO.getSeatId());
        	_primCatChangeVO.setIsValid(Config.IS_VALID_ACTIVE);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        System.out.println("query"+query);
        
        try{
        	populateMap(_primCatChangeVO,_userVO,populateMAP);
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("CategoryChangeDAO:populateMap(_changeCategoryVO,populateMAP)::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        return _primCatChangeVO;
	}
	
	/**
	 * Populates the map with the category change details to be entered in the DB.
	 * @param	_changeCategoryVO	Provides category change details to be entered.
	 * @param	_populateMAP	Map containig values which will be used for insert query.
	 */
    public void populateMap(PrimaryCategoryChangeVO _primCatChangeVO,UserVO _userVO, Map _populateMAP)throws SQLException{
    	Sequence sq=new Sequence();
    	System.out.println("_changeCategoryVO.getPatCrNo()"+_primCatChangeVO.getPatCrNo());
    	_populateMAP.put(sq.next(), _primCatChangeVO.getPatCrNo());//-----HRGNUM_PUK
    	_populateMAP.put(sq.next(), _primCatChangeVO.getPatCrNo());		//-----for Serial Number.
    	_populateMAP.put(sq.next(), _primCatChangeVO.getPatPrevPrimaryCatCode());
    	_populateMAP.put(sq.next(), _primCatChangeVO.getPatNewPrimaryCatCode());
    	_populateMAP.put(sq.next(),"'"+ _primCatChangeVO.getRemarks()+"'");
    	_populateMAP.put(sq.next(),"'"+ _userVO.getIpAddress()+"'"); 
    	_populateMAP.put(sq.next(), _primCatChangeVO.getIsValid());
    	_populateMAP.put(sq.next(), _primCatChangeVO.getSeatId());
    	_populateMAP.put(sq.next(),_userVO.getHospitalCode());
    	_populateMAP.put(sq.next(), _primCatChangeVO.getCardNo());
    	_populateMAP.put(sq.next(), _primCatChangeVO.getVerificationDocumentId());
    	_populateMAP.put(sq.next(), _primCatChangeVO.getIssuingAuthority());
    	_populateMAP.put(sq.next(), _primCatChangeVO.getValidUpto());
    	_populateMAP.put(sq.next(), _primCatChangeVO.getValidUpto());
    	   	
    }
    
   
    public PrimaryCategoryChangeVO[] getChangePrimaryCategoryDetail(String crNo,UserVO _userVO){
    	
    	PrimaryCategoryChangeVO[] _primaryCategoryChangeVO=null;
    	ValueObject[] vo ={};
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.PREVIOUS.PRIMARY.CATEGORY.HRGT_PRICAT_CHANGE_DTL";
		 

		Sequence sq = new Sequence();
		
		_populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		_populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),crNo);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
			Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);
			if (!rs.next())
			{

				//throw new HisRecordNotFoundException("No Record Found Against This CrNo.");	 	    

				throw new HisRecordNotFoundException("No previous change detail found");

			}
			rs.beforeFirst();
			//_episodeVO = (EpisodeVO[]) HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			vo = HelperMethods.populateVOfrmRS(PrimaryCategoryChangeVO.class, rs);
			System.out.println("length" + vo.length);
			_primaryCategoryChangeVO = new PrimaryCategoryChangeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				_primaryCategoryChangeVO[i] = (PrimaryCategoryChangeVO) vo[i];
				}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PrimaryCategoryChangeDAO:getChangePrimaryCategoryDetail():: " + e);
		}
	

    	
    	return _primaryCategoryChangeVO;
    }
    
   
    
}




