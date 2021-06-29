package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisEpisodeClosedException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;
import registration.RegistrationConfig;

public class EpisodeCloseDAO extends DataAccessObject implements EpisodeCloseDAOi
{
	public EpisodeCloseDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	public void create(EpisodeCloseVO _episodeCloseVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_EPISODE_CLOSE_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		//call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), _episodeCloseVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeCode());
			populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeTypeCode());
			populateMAP.put(sq.next(), _episodeCloseVO.getAdmissionNo());
			populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeDate());
			populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeCloseType());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), RegistrationConfig.EPISODE_AUTO_CLOSE_NO);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);

		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSERT FAILED ::EpisodeCloseDAO:populateMap(_dailyPatientVO,psDailyPatDtl)::"+ e);
		}
	}

	public void populateMap(EpisodeCloseVO _episodeCloseVO, Map _populateMAP) throws HisApplicationExecutionException
	{
		System.out.println("inside populateMap" + _episodeCloseVO);
		Sequence sq = new Sequence();
		_populateMAP.put(sq.next(), _episodeCloseVO.getPatCrNo());
		System.out.println("getPatCrNo" + _episodeCloseVO.getPatCrNo());
		_populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeCode());
		_populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeTypeCode());
		_populateMAP.put(sq.next(), _episodeCloseVO.getAdmissionNo());
		_populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeDate());
		_populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeCloseType());
		_populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		System.out.println("inside populateMap222");
	}

	public EpisodeCloseVO[] getPatientEpisodeDtl(String crNo, UserVO _userVO, PatientVO _patientVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		ValueObject[] vo =
		{};
		String fileName = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.CLOSE.EPISODE.HRGT_EPISODE_CLOSE_DTL";

		EpisodeCloseVO[] _episodeCloseVO;
		try
		{
			query = HelperMethodsDAO.getQuery(fileName, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), crNo);

			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisEpisodeClosedException(" No Close Episode Found For This C.R.Number ");
		}
		catch (HisEpisodeClosedException e)
		{
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		try
		{
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeCloseVO.class, rs);
			_episodeCloseVO = new EpisodeCloseVO[vo.length];

			for (int i = 0; i < vo.length; i++)
			{
				_episodeCloseVO[i] = (EpisodeCloseVO) vo[i];
			}
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("EpisodeCloseDAO:getPatientEpisodeDtl::Episode close dtls" + e);
		}
		return _episodeCloseVO;

	}
	
	
	public void update(EpisodeCloseVO _episodeCloseVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.HRGT_EPISODE_CLOSE_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		//call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), _episodeCloseVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);

		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("UPDATE FAILED ::EpisodeCloseDAO:populateMap(_dailyPatientVO,psDailyPatDtl)::"+ e);
		}
	}


}
