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
import hisglobal.vo.AttendantReasonMasterVO;
import hisglobal.vo.UserVO;

public class AttendantReasonMasterDAO extends DataAccessObject implements AttendantReasonMasterDAOi
{
	Logger log;

	public AttendantReasonMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// Saving Attendant Reason Record 
	public void create(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_ATTENDANT_REASON_MST";

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _attReasonVO.getAttendantReason().trim());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AttendantReasonMasterDAO.populateMAP::" + e);
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

	// Checking Duplicate Reason
	public boolean checkDuplicate(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		boolean flag = true;
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "CHECK_DUPLICATE.HGBT_ATTENDANT_REASON_MST";

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
			populateMAP.put(sq.next(), _attReasonVO.getAttendantReason().trim());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AttendantReasonMasterDAO.populateMAP::" + e);
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

	// Getting Detail of Attendant Reason Record
	public AttendantReasonMasterVO get(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		AttendantReasonMasterVO vo = new AttendantReasonMasterVO();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HGBT_ATTENDANT_REASON_MST";
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
			populateMAP.put(sq.next(), _attReasonVO.getAttendantReasonID());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _attReasonVO.getSlNo());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AttendantReasonMasterDAO.populateMAP::" + e);
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
	
	// Updating the Attendant Reason
	public void update(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.DELETE.HGBT_ATTENDANT_REASON_MST";

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
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _attReasonVO.getAttendantReasonID());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _attReasonVO.getSlNo());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AttendantReasonMasterDAO.populateMAP::" + e);
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

	// Saving Updated Attendant Reason
	public void createUpdate(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.MODIFY.HGBT_ATTENDANT_REASON_MST";

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
			populateMAP.put(sq.next(), _attReasonVO.getAttendantReasonID());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _attReasonVO.getAttendantReasonID());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _attReasonVO.getAttendantReason().trim());
			populateMAP.put(sq.next(), _attReasonVO.getIsValid());
			populateMAP.put(sq.next(), _userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AttendantReasonMasterDAO.populateMAP::" + e);
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

	// Checking Duplicate Reason for Modification
	public boolean checkDuplicateforModify(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		boolean flag = true;
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "CHECK_DUPLICATE.MODIFY.HGBT_ATTENDANT_REASON_MST";

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
			populateMAP.put(sq.next(), _attReasonVO.getAttendantReason().trim());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _attReasonVO.getAttendantReasonID());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AttendantReasonMasterDAO.populateMAP::" + e);
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
}
