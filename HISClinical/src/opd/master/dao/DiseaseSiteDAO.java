package opd.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DiseaseSiteMasterVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DiseaseSiteDAO extends DataAccessObject implements DiseaseSiteDAOi
{
	Logger log;

	public DiseaseSiteDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// Saving Disease Site Record 
	public void create(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_DISEASE_SITE_MST";

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSite().trim());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _diseaseSiteVO.getIcdSiteCode());
			populateMAP.put(sq.next(), _diseaseSiteVO.getIcdCodeType());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _diseaseSiteVO.getIcdSubgroupDiseaseCode());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("DiseaseSiteDAO.populateMAP::" + e);
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

	// Checking Duplicate Disease Site
	// returns true if found a Duplicate Record otherwise false
	public boolean checkDuplicate(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		boolean flag = true;
		String query = "", queryForDefined = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "CHECK_DUPLICATE.HGBT_DISEASE_SITE_MST";
		String queryForDefinedKey = "CHECK_DUPLICATE.HGBT_DISEASE_SITE_MST.FOR_DEFINED";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			queryForDefined = HelperMethodsDAO.getQuery(filename, queryForDefinedKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			if(_diseaseSiteVO.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED))
			{
				populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSite().trim());
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), _diseaseSiteVO.getIcdCodeType());
				populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			}
			else
			{
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), _diseaseSiteVO.getIcdCodeType());
				populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
				populateMAP.put(sq.next(), _diseaseSiteVO.getIcdSubgroupDiseaseCode());
				populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSite().trim());
				populateMAP.put(sq.next(), _diseaseSiteVO.getIcdSiteCode());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("DiseaseSiteDAO.populateMAP::" + e);
		}
		try
		{
			if(_diseaseSiteVO.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED))
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			else
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), queryForDefined, populateMAP);
			rs.first();
			int count = Integer.parseInt(rs.getString(1).trim());
			if(count==0)	flag = false;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return flag;
	}

	// Getting Detail of Disease Site Record
	public DiseaseSiteMasterVO get(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		DiseaseSiteMasterVO vo = new DiseaseSiteMasterVO();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HGBT_DISEASE_SITE_MST";
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
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_SUBGROUP);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_SUBGROUP);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_SUBGROUP);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_SUBGROUP);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSiteId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("DiseaseSiteDAO.populateMAP::" + e);
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
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return vo;
	}
	
	// Updating the Disease Site
	public void update(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.RECORD.HGBT_DISEASE_SITE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSite());
			populateMAP.put(sq.next(), _diseaseSiteVO.getIsValid());
			populateMAP.put(sq.next(), _diseaseSiteVO.getIcdSiteCode());
			populateMAP.put(sq.next(), _diseaseSiteVO.getIcdCodeType());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _diseaseSiteVO.getIcdSubgroupDiseaseCode());
			populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSiteId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("DiseaseSiteDAO.populateMAP::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException :update" + e);
		}
	}

	// Deleting the Disease Site
	public void delete(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.DELETE.HGBT_DISEASE_SITE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSiteId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DiseaseSiteDAO.populateMAP::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :update" + e);
		}
	}

	// Saving Updated Disease Site Record
	public void createUpdate(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.MODIFY.HGBT_DISEASE_SITE_MST";

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
			populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSiteId());
			populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSite().trim());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _diseaseSiteVO.getIcdSiteCode());
			populateMAP.put(sq.next(), _diseaseSiteVO.getIcdCodeType());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _diseaseSiteVO.getIcdSubgroupDiseaseCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DiseaseSiteDAO.populateMAP::" + e);
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

	// Checking Duplicate Disease Site for Modification
	public boolean checkDuplicateforModify(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		boolean flag = true;
		String query = "", queryForDefined = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "CHECK_DUPLICATE.MODIFY.HGBT_DISEASE_SITE_MST";
		String queryForDefinedKey = "CHECK_DUPLICATE.MODIFY.HGBT_DISEASE_SITE_MST.FOR_DEFINED";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			queryForDefined = HelperMethodsDAO.getQuery(filename, queryForDefinedKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			if(_diseaseSiteVO.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED))
			{
				populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSite().trim());
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), _diseaseSiteVO.getIcdCodeType());
				populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
				populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSiteId());
			}
			else
			{
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), _diseaseSiteVO.getIcdCodeType());
				populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
				populateMAP.put(sq.next(), _diseaseSiteVO.getIcdSubgroupDiseaseCode());
				populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSite().trim());
				populateMAP.put(sq.next(), _diseaseSiteVO.getIcdSiteCode());
				populateMAP.put(sq.next(), _diseaseSiteVO.getDiseaseSiteId());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("DiseaseSiteDAO.populateMAP::" + e);
		}
		try
		{
			if(_diseaseSiteVO.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED))
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			else
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), queryForDefined, populateMAP);
			rs.first();
			int count = Integer.parseInt(rs.getString(1).trim());
			if(count==0)	flag = false;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return flag;
	}
}
