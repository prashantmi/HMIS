package opd.master.dao;

/**
 * @author  CDAC
 */

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import hisglobal.persistence.TransactionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Sequence;
import hisglobal.vo.OpdClinicalDetailVO;
import hisglobal.vo.UserVO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import opd.OpdConfig;

public class OPDClinicalDetailDAO extends DataAccessObject
{
	Logger log;

	public OPDClinicalDetailDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	//* Inserting Clinical Data Record
	public void create(OpdClinicalDetailVO _CnclDtlVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.TEMPLATE_PARA_VALUES.HOPT_EPISODE_CLINICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), _CnclDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _CnclDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _CnclDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _CnclDtlVO.getDeskMenuId());
			populateMAP.put(sq.next(), _CnclDtlVO.getTemplateId());
			populateMAP.put(sq.next(), _CnclDtlVO.getParaId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _CnclDtlVO.getParaValue());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDClinicalDetailDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+ e);
		}
	}

	//* Checking Whether a Record Already exist for given Composite Primary Key
	public boolean hasRecord(OpdClinicalDetailVO _CnclDtlVO, UserVO _UserVO)
	{
		boolean flag = false;
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HAS_RECORD.HOPT_EPISODE_CLINICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), _CnclDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _CnclDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _CnclDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _CnclDtlVO.getDeskMenuId());
			populateMAP.put(sq.next(), _CnclDtlVO.getTemplateId());
			populateMAP.put(sq.next(), _CnclDtlVO.getParaId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDClinicalDetailDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				if (Integer.parseInt(rs.getString(1)) > 0)
					flag = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+ e);
		}
		return flag;
	}

	//* Updating Existing Record of Clinical Data
	public void update(OpdClinicalDetailVO _CnclDtlVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.TEMPLATE_PARA_VALUES.HOPT_EPISODE_CLINICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), _CnclDtlVO.getParaValue());
			populateMAP.put(sq.next(), _CnclDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _CnclDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _CnclDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _CnclDtlVO.getDeskMenuId());
			populateMAP.put(sq.next(), _CnclDtlVO.getTemplateId());
			populateMAP.put(sq.next(), _CnclDtlVO.getParaId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDClinicalDetailDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+ e);
		}
	}

	//* Deleting all Records of given Patient Visit
	public void deleteAll(OpdClinicalDetailVO _CnclDtlVO, String ids, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DELETE.REMOVE_ALL_VALUES.HOPT_EPISODE_CLINICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		log.info(query);

		try
		{
			query= query.replace("@",ids);
			populateMAP.put(sq.next(), _CnclDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _CnclDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _CnclDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _CnclDtlVO.getDeskMenuId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDClinicalDetailDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+ e);
		}
	}
}
