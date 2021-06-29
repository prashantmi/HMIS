package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.ExtAdminDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

public class ExtAdminDtlDAO extends DataAccessObject implements ExtAdminDtlDAOi
{
	public  ExtAdminDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void save(ExtAdminDtlVO extAdminDtlVO,UserVO userVO){
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HRGT_EPISODE_EXTADMIN_DTL";
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        try
        {
        	populateMAP.put(sq.next(),extAdminDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),extAdminDtlVO.getEpisodeCode());
        	//adviceDate date(sysdate)
        	//serial no
        	populateMAP.put(sq.next(),extAdminDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	
        	populateMAP.put(sq.next(),extAdminDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(),extAdminDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),extAdminDtlVO.getExtTreatmentId());
        	populateMAP.put(sq.next(),extAdminDtlVO.getDoseId());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),extAdminDtlVO.getScheduleDate());
        	populateMAP.put(sq.next(),extAdminDtlVO.getDoseName());
        	populateMAP.put(sq.next(),extAdminDtlVO.getAdministrationTime());
        	populateMAP.put(sq.next(),extAdminDtlVO.getFrequencyId());
        	populateMAP.put(sq.next(),extAdminDtlVO.getAdministrationEndTime());
        	//entryDate(sysdate)
        	populateMAP.put(sq.next(),extAdminDtlVO.getDoseTime());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),extAdminDtlVO.getRemarks());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),extAdminDtlVO.getAdminFlag());
        	populateMAP.put(sq.next(),userVO.getUserEmpID());
        	populateMAP.put(sq.next(),extAdminDtlVO.getIsMasterBound());
        	populateMAP.put(sq.next(),extAdminDtlVO.getExtTreatmentName());
        	populateMAP.put(sq.next(),extAdminDtlVO.getTreatmentType());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("DoctorRoundDtlDAO.populateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
	}
	
	public void updateTodayExtDetail(ExtAdminDtlVO _extAdminDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.TODAY_RECORD.HRGT_EPISODE_EXTADMIN_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		try
		{
			populateMAP.put(sq.next(), _extAdminDtlVO.getAdministrationTime());
			populateMAP.put(sq.next(), _extAdminDtlVO.getAdministrationEndTime());
			populateMAP.put(sq.next(), _extAdminDtlVO.getRemarks());	
			populateMAP.put(sq.next(), _extAdminDtlVO.getAdminFlag());  
			  //condition field
			  populateMAP.put(sq.next(), _extAdminDtlVO.getPatCrNo());
			  populateMAP.put(sq.next(), _extAdminDtlVO.getAdviceDate());
			  populateMAP.put(sq.next(), _extAdminDtlVO.getSerialNo());
			  populateMAP.put(sq.next(), _userVO.getHospitalCode());
			  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
						
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public void updateAllNotExtDetail(ExtAdminDtlVO _extAdminDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.NOT_EXC_RECORD.HRGT_EPISODE_EXTADMIN_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		try
		{
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			  //condition field
			  populateMAP.put(sq.next(), _extAdminDtlVO.getPatCrNo());
			  populateMAP.put(sq.next(), _extAdminDtlVO.getAdviceDate());
			  //populateMAP.put(sq.next(), _extAdminDtlVO.getSerialNo());
			  populateMAP.put(sq.next(), _userVO.getHospitalCode());
			  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			  populateMAP.put(sq.next(),OpdConfig.SCHEDULE);
						
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public String getExtTreatmentStatus(ExtAdminDtlVO vo,UserVO userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_EXT_TREATMENT_STATUS.HRGT_EPISODE_DRUG_ADMIN_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), vo.getPatCrNo());
		populateMAP.put(sq.next(), vo.getExtTreatmentName());
		populateMAP.put(sq.next(), vo.getScheduleDate());
		populateMAP.put(sq.next(), vo.getDoseTime());
		populateMAP.put(sq.next(), OpdConfig.DRUG_GIVEN_IN_PER_SCHEDULE);
		populateMAP.put(sq.next(), OpdConfig.EXTRA_DOSE_GIVEN);
		populateMAP.put(sq.next(), OpdConfig.DRUG_GIVEN_WITHOUT_SCHEDULE);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
				
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.first();
			/*
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No Consent Is Here");
			}
			*/
			return rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		
	}
}
