package opd.master.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.persistence.DataAccessObject;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeKeywordsMasterVO;
import hisglobal.vo.UserVO;

public class EpisodeKeywordsMasterDAO extends DataAccessObject implements EpisodeKeywordsMasterDAOi
{
	Logger log;

	public EpisodeKeywordsMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	public void creat(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_EPISODE_KEYWORD_MST";

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
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), keywordMstVO.getSlNo());
			populateMAP.put(sq.next(), keywordMstVO.getEpisodeKeyword());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
			// entrydate
			populateMAP.put(sq.next(), keywordMstVO.getLastModifyDate());
			populateMAP.put(sq.next(), keywordMstVO.getLastModifiedSeatID());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ReqPurposeMstDAO.populateMAP::" + e);
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

	// Checking For Duplicate Name
	public String checkDuplicateName(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "CHECK_DUPLICATE_NAME.HGBT_EPISODE_KEYWORD_MST";

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
			populateMAP.put(sq.next(), keywordMstVO.getEpisodeKeyword());
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryMasterDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.first();
			return rs.getString(1);

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	public EpisodeKeywordsMasterVO getDataForModify(EpisodeKeywordsMasterVO keywordMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		EpisodeKeywordsMasterVO vo = new EpisodeKeywordsMasterVO();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HGBT_EPISODE_KEYWORD_MST";
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
			populateMAP.put(sq.next(), keywordMstVO.getEpisodeKeywordID());
			//populateMAP.put(sq.next(), keywordMstVO.getSlNo());
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodStorageMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				HelperMethods.populateVOfrmRS(vo, rs);
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return vo;
	}

	public String checkDuplicateNameForModify(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "CHECK_DUPLICATE_NAMEFORMODIFY.HGBT_EPISODE_KEYWORD_MST";

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
			populateMAP.put(sq.next(), keywordMstVO.getEpisodeKeyword());
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), keywordMstVO.getEpisodeKeywordID());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryMasterDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.first();
			return rs.getString(1);

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	public void updateKeywordMaster(EpisodeKeywordsMasterVO keywordMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_EPISODE_KEYWORD_MST";

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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), keywordMstVO.getEpisodeKeywordID());
			//populateMAP.put(sq.next(), keywordMstVO.getSlNo());
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
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

	public void saveModEnclosureMaster(EpisodeKeywordsMasterVO keywordMstVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT_MODIFY.HGBT_EPISODE_KEYWORD_MST";

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
			//populateMAP.put(sq.next(), keywordMstVO.getEpisodeKeywordID());
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), keywordMstVO.getEpisodeKeywordID());

			populateMAP.put(sq.next(), keywordMstVO.getEpisodeKeyword());
			// populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), keywordMstVO.getIsValid());
			populateMAP.put(sq.next(), userVO.getSeatId());
			// entrydate
			populateMAP.put(sq.next(), keywordMstVO.getLastModifyDate());
			populateMAP.put(sq.next(), keywordMstVO.getLastModifiedSeatID());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RackShelfMstDAO.populateMAP::" + e);
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
