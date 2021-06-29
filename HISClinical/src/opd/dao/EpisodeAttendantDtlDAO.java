package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeAttendantDetailVO;
import hisglobal.vo.UserVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

public class EpisodeAttendantDtlDAO extends DataAccessObject implements EpisodeAttendantDtlDAOi
{
	public EpisodeAttendantDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	public void create(EpisodeAttendantDetailVO epiAttendantVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "INSERT.HRGT_EPISODE_ATTENDANT_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			populateMAP.put(sq.next(), epiAttendantVO.getPatCrNo());
			populateMAP.put(sq.next(), epiAttendantVO.getEpisodeCode());
			populateMAP.put(sq.next(), epiAttendantVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), epiAttendantVO.getPatCrNo());
			populateMAP.put(sq.next(), epiAttendantVO.getEpisodeCode());
			populateMAP.put(sq.next(), epiAttendantVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), epiAttendantVO.getPatRelativeId());
			populateMAP.put(sq.next(), epiAttendantVO.getAttendantReasonId());
			populateMAP.put(sq.next(), userVO.getUserEmpID());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getIpAddress());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeAttendantDtlDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSERT FAILED::EpisodeAttendantDtlDAO:::" + e);
		}
	}

	// Getting List of Attendants of Given Episode
	public List<EpisodeAttendantDetailVO> getEpisodeAttendantsList(String strPatCrNo_p, String strEpisodeCode_p, UserVO strUserVO_p)
	{
		EpisodeAttendantDetailVO voEpiAttendant = null;

		List<EpisodeAttendantDetailVO> lstAttendants = new ArrayList<EpisodeAttendantDetailVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.LIST_BY_PAT_EPI.HRGT_EPISODE_ATTENDANT_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), strPatCrNo_p);
			populateMAP.put(sq.next(), strEpisodeCode_p);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), strUserVO_p.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeAttendantDtlDAO.getEpisodeAttendantsList::populateMap" + e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
				while (rs.next())
				{
					voEpiAttendant = new EpisodeAttendantDetailVO();
					HelperMethods.populateVOfrmRS(voEpiAttendant, rs);
					lstAttendants.add(voEpiAttendant);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return lstAttendants;
	}
}
