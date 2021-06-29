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
import hisglobal.vo.IcdSnomedMappingMasterVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

public class IcdSnomedMappingMasterDAO extends DataAccessObject implements IcdSnomedMappingMasterDAOi
{
	public IcdSnomedMappingMasterDAO(TransactionContext _tx) {
		super(_tx);
	}
	
	

	public boolean checkDuplicateBeforeSave(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, String _snomedID)
	{
		ResultSet rs;
		boolean flag=false;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_SAVE.HGBT_DISEASE_MAPPING_MST";
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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _icdSnomedMappingMasterVO.getIcdCode());
			populateMAP.put(sq.next(),_snomedID);
			
			
		
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
	
	public IcdSnomedMappingMasterVO fetchIcdSnomedModify(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO)
	{
		String query = "";

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HGBT_DISEASE_MAPPING_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _icdSnomedMappingMasterVO.getMappingSq());
			
			
			
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
				HelperMethods.populateVOfrmRS(_icdSnomedMappingMasterVO, rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}

		return _icdSnomedMappingMasterVO;
	}
	public boolean  checkDuplicateBeforeModifySave(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO)
	{
		ResultSet rs;
		boolean flag=false;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_MODIFY_SAVE.HGBT_HOSPITAL_DISEASE_MST";
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
			populateMAP.put(sq.next(), _icdSnomedMappingMasterVO.getIcdCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),_icdSnomedMappingMasterVO.getPatAlertId());
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
				"HospitalDiseaseMasterDAO.populateMAP::" + e);
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
	public void modify(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_HOSPITAL_DISEASE_MST";

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
			populateMAP.put(sq.next(), _icdSnomedMappingMasterVO.getIcdCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

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
	
	
	
	
	
	public void mapSnomed(IcdSnomedMappingMasterVO _icdSnomedMappingMasterVO, UserVO _userVO)  {

		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT_ICD_SNOMED.HGBT_DISEASE_MAPPING_MST";

		for(int i=0;i<_icdSnomedMappingMasterVO.getSelsnomed().length;i++)
		{
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			} catch (Exception e) {
				throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
				}

			Sequence sq = new Sequence();
			try {
				populateMAP.put(sq.next(),_icdSnomedMappingMasterVO.getIcdCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getSeatId());
				populateMAP.put(sq.next(),_icdSnomedMappingMasterVO.getSelsnomed()[i]);
				populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);				
				populateMAP.put(sq.next(),_userVO.getIpAddress());
				populateMAP.put(sq.next(),_icdSnomedMappingMasterVO.getIcdName());
				populateMAP.put(sq.next(),_icdSnomedMappingMasterVO.getSelSnomedName()[i]);
			
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
	
	
	}
	
	
	
	
}
