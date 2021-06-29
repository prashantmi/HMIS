package opd.master.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Sequence;

import hisglobal.vo.IcdHospitalMasterVO;
import hisglobal.vo.UserVO;

import opd.OpdConfig;

public class IcdHospitalMasterDAO extends DataAccessObject
{

	public IcdHospitalMasterDAO(TransactionContext _tx)
	{
		super(_tx);

	}

	public void deleteAll(String _hosDisCode, UserVO _userVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.DELETE_BY_HOPDISEASECODE.HGBT_ICD_HOSPITAL_MST";

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
			populateMAP.put(sq.next(), _hosDisCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdHospitalMasterDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Excetion While Updating");
		}

	}

	public boolean exist(IcdHospitalMasterVO _vo, UserVO _userVO)
	{

		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.EXIST.HGBT_ICD_HOSPITAL_MST";

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
			populateMAP.put(sq.next(), _vo.getHospitalDiseaseCode());
			populateMAP.put(sq.next(), _vo.getDiseaseCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdHospitalMasterDAO.populateMAP::" + e);
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

		boolean flag = false;
		try
		{
			if (!rs.next()) return flag = false;
			rs.first();
			if (rs.getInt(1) > 0) flag = true;
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return flag;
	}

	public void updateActive(IcdHospitalMasterVO _vo, UserVO _userVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.ACTIVE_RECORD.HGBT_ICD_HOSPITAL_MST";

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
			populateMAP.put(sq.next(), _vo.getHospitalDiseaseCode());
			populateMAP.put(sq.next(), _vo.getDiseaseCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdHospitalMasterDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Excetion While Updating");
		}

	}

	public void save(IcdHospitalMasterVO _icdHospitalMasterVO, UserVO _userVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_ICD_HOSPITAL_MST";

		_icdHospitalMasterVO.setIsValid(Config.IS_VALID_ACTIVE);
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
			populateMAP.put(sq.next(), _icdHospitalMasterVO.getDiseaseCode());
			populateMAP.put(sq.next(), _icdHospitalMasterVO.getHospitalDiseaseCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _icdHospitalMasterVO.getIsValid());
			populateMAP.put(sq.next(), _userVO.getSeatId());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdHospitalMasterDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Excetion While ADDING");
		}

	}

}
