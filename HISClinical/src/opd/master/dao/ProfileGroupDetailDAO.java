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
import hisglobal.vo.UserVO;
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProfileGroupDetailDAO extends DataAccessObject implements ProfileGroupDetailDAOi
{
	
Logger log;
	
	public ProfileGroupDetailDAO(TransactionContext _tx) {
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

		
	
	public void save(ProfileGroupDtlVO profileGroupDetailVO,UserVO userVO) {
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HPMRT_PROFILE_GROUP_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			populateMAP.put(sq.next(), profileGroupDetailVO.getProfileGroupId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileGroupDetailVO.getRecordMode());
			populateMAP.put(sq.next(), profileGroupDetailVO.getAccessRecordId());
			populateMAP.put(sq.next(), profileGroupDetailVO.getSerialNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileRestrictedCatDAO.populateMAP::" + e);
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
	
	
	
	public List<ProfileGroupDtlVO> fetchProfileGroupDetailAccessModify(ProfileGroupDtlVO profileGroupDetailVO,UserVO userVO) 
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HPMRT_PROFILE_GROUP_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), profileGroupDetailVO.getProfileGroupId());
			populateMap.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO:populateMap::" + e);
		}

		List<ProfileGroupDtlVO> lstProfileAccesses = new ArrayList<ProfileGroupDtlVO>();
		ValueObject[] valueObjects = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(ProfileGroupDtlVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstProfileAccesses.add((ProfileGroupDtlVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("ProfileAccessDetailDAO:get::Profile Access Priviledges:: " + e);
		}
		return lstProfileAccesses;
	}
	
	
	
	public void modify(ProfileGroupDtlVO profileGroupDtlVO,UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HPMRT_PROFILE_GROUP_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), profileGroupDtlVO.getProfileGroupId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileGroupDtlVO.getSerialNo());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileGroupDetailDAO.populateMAP::" + e);
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

	public void modifyInsert(ProfileGroupDtlVO profileGroupDtlVO,UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "MODIFY_INSERT.HPMRT_PROFILE_GROUP_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			populateMAP.put(sq.next(), profileGroupDtlVO.getProfileGroupId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileGroupDtlVO.getRecordMode());
			populateMAP.put(sq.next(), profileGroupDtlVO.getAccessRecordId());
			populateMAP.put(sq.next(), profileGroupDtlVO.getSerialNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileGroupDetailDAO.populateMAP::" + e);
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

	///
	public String getMaxSerialNoforSave(String strHospitalCode_p) 
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		String strMaxSerialNoOfSave=null;
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT_MAX_SERIAL.HPMRT_PROFILE_GROUP_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			//populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMap.put(sq.next(), profileGroupDetailVO.getProfileGroupId());
			populateMap.put(sq.next(), strHospitalCode_p);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileGroupDetailDAO:populateMap::" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if(rs.next())
			{
				strMaxSerialNoOfSave=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("ProfileGroupDetailDAO:getMaxSerialNoforSave():: " + e);
		}
		return strMaxSerialNoOfSave;
	}

}
