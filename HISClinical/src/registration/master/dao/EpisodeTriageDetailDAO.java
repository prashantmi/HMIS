package registration.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeTriageDetailVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;

public class EpisodeTriageDetailDAO extends DataAccessObject
{
	Logger log;

	public EpisodeTriageDetailDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	public void create(EpisodeTriageDetailVO _triVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "INSERT.HRGT_TRIAGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _triVO.getEpisodeCode());
			populateMAP.put(sq.next(), _triVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _triVO.getPatCrNo());
			
			populateMAP.put(sq.next(), _triVO.getEpisodeCode());
			populateMAP.put(sq.next(), _triVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _triVO.getPatCrNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

			populateMAP.put(sq.next(), _triVO.getEmergencyCode());			
			populateMAP.put(sq.next(), _triVO.getInDate());
			populateMAP.put(sq.next(), _triVO.getInTime());
			populateMAP.put(sq.next(), _triVO.getInPatStatus());
			populateMAP.put(sq.next(), _triVO.getInCondition());
			populateMAP.put(sq.next(), _triVO.getOutDate());
			populateMAP.put(sq.next(), _triVO.getOutTime());
			populateMAP.put(sq.next(), _triVO.getOutPatStatus());
			populateMAP.put(sq.next(), _triVO.getOutCondition());
			populateMAP.put(sq.next(), _triVO.getRemarks());

			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _triVO.getSystemIPAddress());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeTriageDetailDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public void createNew(EpisodeTriageDetailVO _triVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "INSERT.HRGT_TRIAGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _triVO.getEpisodeCode());
			populateMAP.put(sq.next(), _triVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _triVO.getPatCrNo());
			
			populateMAP.put(sq.next(), _triVO.getEpisodeCode());
			populateMAP.put(sq.next(), _triVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _triVO.getPatCrNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

			populateMAP.put(sq.next(), _triVO.getEmergencyCode());			
			populateMAP.put(sq.next(), _triVO.getInDate());
			populateMAP.put(sq.next(), _triVO.getInTime());
			populateMAP.put(sq.next(), _triVO.getInPatStatus());
			populateMAP.put(sq.next(), _triVO.getInCondition());
			populateMAP.put(sq.next(), _triVO.getOutDate());
			populateMAP.put(sq.next(), _triVO.getOutTime());
			populateMAP.put(sq.next(), _triVO.getOutPatStatus());
			populateMAP.put(sq.next(), _triVO.getOutCondition());
			populateMAP.put(sq.next(), _triVO.getRemarks());

			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _triVO.getSystemIPAddress());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeTriageDetailDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	public EpisodeTriageDetailVO getDataByCrnoVisit(String _crNo,String _epiCode,String _visitNo, UserVO _userVO)
	{
		String query = "";
		ResultSet rs=null;
		EpisodeTriageDetailVO vo=null;
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.DATA_BY_EPI_VISIT.HRGT_TRIAGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _epiCode);
			populateMAP.put(sq.next(), _visitNo);
			populateMAP.put(sq.next(), _crNo);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeTriageDetailDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				vo= new EpisodeTriageDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return vo;		
	}

	public void update(EpisodeTriageDetailVO _triVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "UPDATE.CURRENT.HRGT_TRIAGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _triVO.getEmergencyCode());
			populateMAP.put(sq.next(), _triVO.getOutDate());
			populateMAP.put(sq.next(), _triVO.getOutTime());
			populateMAP.put(sq.next(), _triVO.getOutPatStatus());
			populateMAP.put(sq.next(), _triVO.getOutCondition());
			populateMAP.put(sq.next(), _triVO.getRemarks());
			populateMAP.put(sq.next(), _userVO.getSeatId());

			populateMAP.put(sq.next(), _triVO.getEpisodeCode());
			populateMAP.put(sq.next(), _triVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _triVO.getPatCrNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeTriageDetailDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	public void updateOld(EpisodeTriageDetailVO _triVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "UPDATE.OLD.HRGT_TRIAGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _triVO.getEntryDate());
			populateMAP.put(sq.next(), _triVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);

			populateMAP.put(sq.next(), _triVO.getEpisodeCode());
			populateMAP.put(sq.next(), _triVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _triVO.getPatCrNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeTriageDetailDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
}