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
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.UserVO;

public class IcdDiseaseMasterDAO extends DataAccessObject implements IcdDiseaseMasterDAOi
{
	Logger log;

	public IcdDiseaseMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// Saving ICD Include Exclude Record 
	public void createIncludeExclude(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.INCLUDE_EXCLUDE.HGBT_ICD_DISEASE_MST";

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
			populateMAP.put(sq.next(), _icdDiseaseVO.getDiseaseCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _icdDiseaseVO.getDiseaseCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _icdDiseaseVO.getIcdSubgroupCode());
			populateMAP.put(sq.next(), _icdDiseaseVO.getDisease().trim());
			populateMAP.put(sq.next(), _icdDiseaseVO.getParentCode());
			populateMAP.put(sq.next(), _icdDiseaseVO.getRecordType());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _icdDiseaseVO.getIsCommon());
			populateMAP.put(sq.next(), _icdDiseaseVO.getDiseaseRemark());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _icdDiseaseVO.getMainDisease());
			populateMAP.put(sq.next(), _icdDiseaseVO.getDiseaseTypeCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdDiseaseMasterDAO.populateMAP::" + e);
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

	// Checking Duplicate ICD Include Exclude
	public boolean checkIncludeExcludeDuplicate(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		boolean flag = true;
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "CHECK_DUPLICATE.INCLUDE_EXCLUDE.HGBT_ICD_DISEASE_MST";

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
			populateMAP.put(sq.next(), _icdDiseaseVO.getDiseaseCode());
			populateMAP.put(sq.next(), _icdDiseaseVO.getDisease().trim());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdDiseaseMasterDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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

	// Getting Detail of ICD Include Exclude Record
	public IcdDiseaseMasterVO getIncludeExclude(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		IcdDiseaseMasterVO vo = new IcdDiseaseMasterVO();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.INCLUDE_EXCLUDE.HGBT_ICD_DISEASE_MST";
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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _icdDiseaseVO.getDiseaseCode());
			populateMAP.put(sq.next(), _icdDiseaseVO.getSlNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdDiseaseMasterDAO.populateMAP::" + e);
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
	
	// Updating the ICD Include Exclude
	public void update(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.DELETE.INCLUDE_EXCLUDE.HGBT_ICD_DISEASE_MST";

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
			populateMAP.put(sq.next(), _icdDiseaseVO.getDiseaseCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _icdDiseaseVO.getSlNo());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdDiseaseMasterDAO.populateMAP::" + e);
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

	// Checking Duplicate ICD Include Exclude for Modification
	public boolean checkDuplicateforIncludeExcludeModify(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		boolean flag = true;
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "CHECK_DUPLICATE.INCLUDE_EXCLUDE.MODIFY.HGBT_ICD_DISEASE_MST";

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
			populateMAP.put(sq.next(), _icdDiseaseVO.getDisease().trim());
			populateMAP.put(sq.next(), _icdDiseaseVO.getDiseaseCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _icdDiseaseVO.getSlNo());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _icdDiseaseVO.getRecordType());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdDiseaseMasterDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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

	// Get Disease Name In Special Short Format
	public String getName(String _diseaseCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.DISEASE_NAME_IN_FORMAT.HGBT_ICD_DISEASE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _diseaseCode);
			populateMAP.put(sq.next(), OpdConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdDiseaseMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		String diseaseName = "";
		try
		{
			if(rs.next())
			{
				rs.first();
				diseaseName = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException : getName" + e);
		}
		return diseaseName;
	}
}
