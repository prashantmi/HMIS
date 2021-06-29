package opd.master.dao;

/**
 * @author  CDAC
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import hisglobal.persistence.TransactionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Sequence;
import hisglobal.vo.OpdTemplateVO;
import hisglobal.vo.UserVO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import opd.OpdConfig;

public class OPDTemplateMasterDAO extends DataAccessObject
{
	Logger log;

	public OPDTemplateMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// * Inserting Template Record
	public void create(OpdTemplateVO _TmplVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.FIRST.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _TmplVO.getTemplateName());
			populateMAP.put(sq.next(), _TmplVO.getTemplateType());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), _TmplVO.getEffectiveFrom());
			populateMAP.put(sq.next(), _TmplVO.getEffectiveTo());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDTemplateMasterDAO.populateMAP::" + e);
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

	// * Fetching Maximum Template Id
	public String getMaxTemplateId(UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String tempId = "";

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.MAX_TEMPLATE_ID.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDTemplateMasterDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				tempId = rs.getString(1).trim();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return tempId;
	}

	// * Fetching Template Record By Id
	public void getTemplateDataById(OpdTemplateVO _voTemp, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.BY_ID.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), _voTemp.getTemplateId());
			populateMAP.put(sq.next(), _voTemp.getTempSerialNo());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDTemplateMasterDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				_voTemp.setTemplateName(rs.getString(1).trim());
				_voTemp.setTemplateType(rs.getString(2).trim());
				_voTemp.setEffectiveFrom(rs.getString(3).trim());
				if (rs.getString(4) != null) _voTemp.setEffectiveTo(rs.getString(4).trim());
				else _voTemp.setEffectiveTo("");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	// * Getting Next Date
	public String getNextDate(OpdTemplateVO tempMstVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.NEXT_DATE.HGBT_TEMPLATE_MST";

		String isValid = Config.IS_VALID_ACTIVE;

		Sequence sq = new Sequence();

		try
		{
			populateMap.put(sq.next(), tempMstVO.getTemplateId());
			populateMap.put(sq.next(), tempMstVO.getEffectiveFrom());
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			populateMap.put(sq.next(), isValid);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDTemplateMasterDAO.populateMAP::" + e);
		}

		String nextDate = "";
		int rowCount;
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
		log.info(query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			rs.last();
			rowCount = rs.getRow();
			if (rowCount > 0)
			{
				nextDate = rs.getString(1);
				if (nextDate == null)
				{
					nextDate = "";
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HisDataAccessException:: " + e);
		}
		return nextDate;
	}

	// * Updating Template Record
	public void update(OpdTemplateVO _tempVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			_tempVO.setIsValid(Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), _tempVO.getTemplateName());
			populateMAP.put(sq.next(), _tempVO.getEffectiveFrom());
			populateMAP.put(sq.next(), _tempVO.getEffectiveTo());
			populateMAP.put(sq.next(), _tempVO.getIsValid());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _tempVO.getTemplateId());
			populateMAP.put(sq.next(), _tempVO.getTempSerialNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDTemplateMasterDAO.populateMAP::" + e);
		}
		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			if (i == 0)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset" + e);
		}
	}

	// * Updating Template Name
	public void updateName(OpdTemplateVO _tempVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.TABLE_NAME.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			_tempVO.setIsValid(Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), _tempVO.getTemplateName());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _tempVO.getTemplateId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDTemplateMasterDAO:populateMap::" + e);
		}
		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			if (i == 0)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset" + e);
		}
	}

	// * Getting Previous Date
	public int getPreviousDate(OpdTemplateVO _tempVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.PREV_DATE.HGBT_TEMPLATE_MST";

		Sequence sq = new Sequence();

		try
		{
			populateMap.put(sq.next(), _tempVO.getEffectiveFrom());
			populateMap.put(sq.next(), _tempVO.getEffectiveFrom());
			populateMap.put(sq.next(), _tempVO.getTemplateId());
			populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDTemplateMasterDAO.populateMAP::" + e);
		}

		int count;
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
		log.info(query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			rs.next();
			count = rs.getInt(1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HisDataAccessException:: " + e);
		}
		return count;
	}

	// * Getting Previous Serial No
	public String[] getPreviousSerialNo(OpdTemplateVO _tempVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.PREVIOUS_SERIAL_NO.HGBT_TEMPLATE_MST";

		String serialNo = "";
		String[] arrayRS = new String[2];
		String frmDate = ""; // of Null Record for update

		Sequence sq = new Sequence();

		try
		{
			populateMap.put(sq.next(), _tempVO.getTemplateId());
			populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDTemplateMasterDAO.populateMAP::" + e);
		}

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
		log.info(query);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			rs.last();
			rs.beforeFirst();
			if (rs.next())
			{
				serialNo = rs.getString(1);
				frmDate = rs.getString(2);
			}
			if (serialNo == null) serialNo = "";
			if (frmDate == null) frmDate = "";
			arrayRS[0] = serialNo;
			arrayRS[1] = frmDate;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException();
			else throw new HisDataAccessException("HisDataAccessException:: " + e);
		}
		return arrayRS;
	}

	// * Inserting a Row
	public void insertRow(OpdTemplateVO _tempVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "CREATE.NEW.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			_tempVO.setIsValid(Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), _tempVO.getTemplateId());
			populateMAP.put(sq.next(), _tempVO.getTemplateId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _tempVO.getTemplateName());
			populateMAP.put(sq.next(), _tempVO.getTemplateType());
			populateMAP.put(sq.next(), _tempVO.getEffectiveFrom());
			populateMAP.put(sq.next(), _tempVO.getEffectiveTo());
			populateMAP.put(sq.next(), _tempVO.getIsValid());
			populateMAP.put(sq.next(), _userVO.getSeatId());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDTemplateMasterDAO:populateMap::" + e);
		}
		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			if (i == 0)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset" + e);
		}
	}

	// * Updating Previous Row
	public void updatePrevRow(OpdTemplateVO _tempVO, String _serialNo, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.PREVIOUS_ROW.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), _tempVO.getEffectiveFrom());
			populateMAP.put(sq.next(), _tempVO.getTemplateId());
			populateMAP.put(sq.next(), _serialNo);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDTemplateMasterDAO:populateMap::" + e);
		}
		try
		{
			int i = HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			if (i == 0)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset" + e);
		}
	}

}
