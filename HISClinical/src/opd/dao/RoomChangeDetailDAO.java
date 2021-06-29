package opd.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.RoomChangeVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RoomChangeDetailDAO extends DataAccessObject implements RoomChangeDetailDAOi{
	

	Logger log;

	/**
	 * Constructor for Setting Transaction Context
	 */
	public RoomChangeDetailDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}
	

	/**
	## 		Modification Log		: added hospital code check						
	##		Modify Date				: 04-03-2015	
	##		Reason	(CR/PRS)		: table HRGT_ROOM_CHANGE_DTL modified 
	##		Modify By				: Akash Singh
	*/
	public void changePatientRoomDetails(RoomChangeVO _roomChangeVO,UserVO _userVO)
	{
		//ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "INSERT.HRGT_ROOM_CHANGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), _roomChangeVO.getPatCrNo());
		populateMAP.put(sq.next(), _roomChangeVO.getEpisodeCode());
		populateMAP.put(sq.next(), _roomChangeVO.getEpisodeVisitNo());
		
		populateMAP.put(sq.next(), _roomChangeVO.getPatCrNo());
		populateMAP.put(sq.next(), _roomChangeVO.getEpisodeCode());
		populateMAP.put(sq.next(), _roomChangeVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
		populateMAP.put(sq.next(), _roomChangeVO.getFromRoomCode());
		populateMAP.put(sq.next(), _roomChangeVO.getToRoomCode());
		populateMAP.put(sq.next(), _roomChangeVO.getChangeReason());
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		
		
		

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :Change Room details" + e);
		}
		
	}
	
	
	public void saveRoomChangeDetail(RoomChangeVO _roomChangeVO,UserVO _userVO)
	{
		//ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "INSERT.ROOM_CHANGE.HRGT_ROOM_CHANGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), _roomChangeVO.getPatCrNo());
		populateMAP.put(sq.next(), _roomChangeVO.getEpisodeCode());
		populateMAP.put(sq.next(), _roomChangeVO.getEpisodeVisitNo());
		
		//serealNo
		populateMAP.put(sq.next(), _roomChangeVO.getPatCrNo());
		populateMAP.put(sq.next(), _roomChangeVO.getEpisodeCode());
		populateMAP.put(sq.next(), _roomChangeVO.getEpisodeVisitNo());
		
		
		populateMAP.put(sq.next(), _roomChangeVO.getFromRoomCode());
		populateMAP.put(sq.next(), _roomChangeVO.getToRoomCode());
		populateMAP.put(sq.next(), _roomChangeVO.getChangeReason());
		populateMAP.put(sq.next(), _userVO.getSeatId());
		//entryDate
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _roomChangeVO.getChangeType());
		populateMAP.put(sq.next(), _roomChangeVO.getFromDeprUnitCode());
		populateMAP.put(sq.next(), _roomChangeVO.getToDeptUnitCode());
		
		
		

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :Change Room details" + e);
		}
		
	}


}
