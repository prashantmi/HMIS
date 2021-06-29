/**
 * 
 */
package opd.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.vo.PatientAlertMasterVO;
import hisglobal.vo.UserVO;
import java.sql.ResultSet;

import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;

import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;


/**
 * @author ashas
 *
 */
public class PatientAlertMasterDAO extends DataAccessObject implements PatientAlertMasterDAOi
{
	public PatientAlertMasterDAO(TransactionContext _tx) {
		super(_tx);
	}
	
	public void create(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO)  {

		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_PAT_ALERT_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		Sequence sq = new Sequence();
		try {
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
			populateMAP.put(sq.next(), _patientAlertMasterVO.getAlertName());
			populateMAP.put(sq.next(), _patientAlertMasterVO.getDiseaseCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.SL_NO);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"OpdEssentialDAO.populateMAP::" + e);
		}

		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.executeUpdate"
					+ e);
		}

	}

	public boolean checkDuplicateBeforeSave(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO)
	{
		ResultSet rs;
		boolean flag=false;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_SAVE.HGBT_PAT_ALERT_MST";
		try 
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
		}

		try
		{
			populateMAP.put(sq.next(),  _patientAlertMasterVO.getAlertName());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		
		} 
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
				"ProfileTypeMasterDAO.populateMAP::" + e);
		}
		try 
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			rs.next();
			if(rs.getInt(1)==0)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
        
			return flag;
		} 
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		}
			else {
					e.printStackTrace();
					throw new HisDataAccessException(
					"HelperMethodsDAO.getResultset" + e);
				}
		}
	}
	
	public PatientAlertMasterVO fetchPatientAlertModify(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO)
	{
		String query = "";

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HGBT_PAT_ALERT_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
//			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _patientAlertMasterVO.getPatAlertId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _patientAlertMasterVO.getSerialNo());
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"UserDeskMenuMasterDAO.populateMAP::" + e);
		}

		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(super
					.getTransactionContext().getConnection(), query,
					populateMAP);
			if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				HelperMethods.populateVOfrmRS(_patientAlertMasterVO, rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}

		return _patientAlertMasterVO;
	}
	public boolean  checkDuplicateBeforeModifySave(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO)
	{
		ResultSet rs;
		boolean flag=false;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_MODIFY_SAVE.HGBT_PAT_ALERT_MST";
		try 
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
		}
		try
		{
			populateMAP.put(sq.next(), _patientAlertMasterVO.getAlertName());
			//populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),_patientAlertMasterVO.getPatAlertId());
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
				"PatientAlertMasterDAO.populateMAP::" + e);
		}
		try 
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			rs.next();
    	if(rs.getInt(1)==0)
    	{
    		flag=true;
    	}
    	else
    	{
    		flag=false;
    	}
        
        return flag;
		} 
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else {
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.getResultset" + e);
		}
	}
}
	public void modify(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_PAT_ALERT_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		try {
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _patientAlertMasterVO.getPatAlertId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _patientAlertMasterVO.getSerialNo());

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileTypeMstDAO.populateMAP::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"
					+ e);
		}
	}
	
	public void modifyInsert(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "MODIFY_INSERT.HGBT_PAT_ALERT_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			populateMAP.put(sq.next(), _patientAlertMasterVO.getPatAlertId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _patientAlertMasterVO.getPatAlertId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _patientAlertMasterVO.getAlertName());
			populateMAP.put(sq.next(), _patientAlertMasterVO.getDiseaseCode());
			populateMAP.put(sq.next(), _patientAlertMasterVO.getIsActive());
			populateMAP.put(sq.next(), _userVO.getSeatId());
					
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ProfileTypeDAO.populateMAP::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"
					+ e);
		}
	}
	
}
