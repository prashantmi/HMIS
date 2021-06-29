package mrd.transaction.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientFamilyDtlVO;
import hisglobal.vo.UserVO;

public class PatientFamilyDtlDAO extends DataAccessObject implements PatientFamilyDtlDAOi
{
	public PatientFamilyDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public List<PatientFamilyDtlVO> getPatAttendantDtlByCrNo(String patCrNo, UserVO userVO)
	{
		PatientFamilyDtlVO patFamilyAttendantVO=null;
		List<PatientFamilyDtlVO> patFamilyAttendantVOList=new ArrayList<PatientFamilyDtlVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET.PATIENT_ATTENDANT_BY_PUK.HPMRT_PAT_FAMILY_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), patCrNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					patFamilyAttendantVO=new PatientFamilyDtlVO();
					HelperMethods.populateVOfrmRS(patFamilyAttendantVO, rs);
					patFamilyAttendantVOList.add(patFamilyAttendantVO);
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
		return patFamilyAttendantVOList;
	}
	
	public void create(PatientFamilyDtlVO patFamilyVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "INSERT.HPMRT_PAT_FAMILY_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq=new Sequence();

		
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
			populateMAP.put(sq.next(), patFamilyVO.getPatRelativeId());
			populateMAP.put(sq.next(), patFamilyVO.getPatCrNo());
			populateMAP.put(sq.next(), patFamilyVO.getRelativeName());
			populateMAP.put(sq.next(), patFamilyVO.getPatRelativeId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), patFamilyVO.getRelationCode());
			populateMAP.put(sq.next(), patFamilyVO.getRelativeCrNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), patFamilyVO.getAddress());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), patFamilyVO.getEmail());
			populateMAP.put(sq.next(), patFamilyVO.getPhoneNo());
			populateMAP.put(sq.next(), patFamilyVO.getMobileNo());
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientFamilyDtlDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSERT FAILED::PatientFamilyDtlDAO:::" + e);
		}
	}
	
	public String generatePatientRelativeId(String patCrNo)
	{
		String query = "";
		ResultSet rs;

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey ="GENERATE_PAT_RELATIVE_ID.HPMRT_PAT_FAMILY_DTL";

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
			populateMAP.put(sq.next(), patCrNo);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientFamilyDtlDAO.populateMAP::" + e);
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

	// returns true if Relative already exists
	public boolean isExist(PatientFamilyDtlVO patFamilyVO,UserVO userVO)
	{
		String query = "";
		ResultSet rs;

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey ="SELECT.EXISTS.HPMRT_PAT_FAMILY_DTL";

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
			populateMAP.put(sq.next(), patFamilyVO.getRelativeName());
			populateMAP.put(sq.next(), patFamilyVO.getRelationCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), patFamilyVO.getPatCrNo());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientFamilyDtlDAO.populateMAP::" + e);
		}

		String record=null;
		boolean isExists = false;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
			}
			if(record!=null && Integer.parseInt(record)>0)
			{
				isExists = true;
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return isExists;
	}
}
