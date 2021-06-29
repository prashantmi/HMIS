package mrd.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;


public class RecordTypeWiseEnclosureMstDAO extends DataAccessObject 
{
	
	public RecordTypeWiseEnclosureMstDAO(TransactionContext _tx)
	{
		super(_tx);
	}	

	
	public String checkDuplicateRecordEnclosureInfo(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey ="CHECKDUPLICATEBEFOREINSERT.HPMRT_RECORD_ENCLOSURE_MST";

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
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getEnclosure().trim());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}
	
	public String checkDuplicateRecordForModifyEnclosureInfo(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey ="CHECKDUPLICATEBEFOREMODIFY.HPMRT_RECORD_ENCLOSURE_MST";

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
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getEnclosure().trim());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(),_RecordTypeWiseEnclosureMstVO.getEnclosureId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}
	
	public void saveRecordEnclosure(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "INSERT.HPMRT_RECORD_ENCLOSURE_MST";

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
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getRecordTypeId());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getEnclosureId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getRecordTypeId());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getEnclosureId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getIsCompulsory());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getDisplayOrder());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			
				
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RecordTypeWiseEnclosureMstDAO.populateMAP::" + e);
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
	
	public RecordTypeWiseEnclosureMstVO fetchEnclosureRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename =MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey ="FETCH.HPMRT_RECORD_ENCLOSURE_MST";

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
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getEnclosureId());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getSerialNo());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getHospitalCode());
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDntionTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				_RecordTypeWiseEnclosureMstVO.setEnclosure(rs.getString(1));
				_RecordTypeWiseEnclosureMstVO.setRecordTypeId(rs.getString(2));
				_RecordTypeWiseEnclosureMstVO.setRemarks(rs.getString(3));
				_RecordTypeWiseEnclosureMstVO.setIsCompulsory(rs.getString(4));
				_RecordTypeWiseEnclosureMstVO.setIsValid(rs.getString(5));
				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return _RecordTypeWiseEnclosureMstVO;
	}
	
	public String getRecordTypeName(String recordTypeId , UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		String recordTypeName=null;
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.RECORDTYPEFORMODIFY.HPMRT_FLAG_MST";

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
			populateMAP.put(sq.next(), recordTypeId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 04-Jul-2012
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldCompMstDAO.populateMAP::" + e);
		}
		//String str = new String();
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				
				recordTypeName=rs.getString(1);
											
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return recordTypeName;
	}
	
	public void updateEnclosureRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "UPDATE.HPMRT_RECORD_ENCLOSURE_MST";

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
			
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getEnclosureId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getSerialNo());
		
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
	
	
	public void modifyEnclosureRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "MODIFYINSERT.HPMRT_RECORD_ENCLOSURE_MST";

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
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getEnclosureId());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getRecordTypeId());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getEnclosure());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getRemarks());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getIsCompulsory());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getIsValid());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
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
	
	public RecordTypeWiseEnclosureMstVO[] getAddedRecordTypeEnclosure(String recordTypeId , UserVO userVO)
	{
		RecordTypeWiseEnclosureMstVO[] addedEnclosureVO=null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "GET_ADDED_ENCLOSURE.HPMRT_RECORD_ENCLOSURE_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), recordTypeId);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 04-Jul-2012
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				addedEnclosureVO=null;
				//throw new HisRecordNotFoundException("");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(RecordTypeWiseEnclosureMstVO.class, rs);
				addedEnclosureVO = new RecordTypeWiseEnclosureMstVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					addedEnclosureVO[i] = (RecordTypeWiseEnclosureMstVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return addedEnclosureVO;
	}
	
	public List getEnclosureRecordListNotMapped(String recordTypeId,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "GET_ENCLOSURE_NOT_MAPPED.HPMRT_RECORD_ENCLOSURE_MST";

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
			
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 04-Jul-2012
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), recordTypeId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RecordTypeWiseEnclosureMstDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
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

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("RecordTypeWiseEnclosureMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}

	
	public void updatePreviousRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "UPDATE_PREVIOUS_RECORD.HPMRT_RECORD_ENCLOSURE_MST";

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
			
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _RecordTypeWiseEnclosureMstVO.getRecordTypeId());
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RecordTypeWiseEnclosureMstDAO.populateMAP::" + e);
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
