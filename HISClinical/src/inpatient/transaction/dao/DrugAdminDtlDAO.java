package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Sequence;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugReactionVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

public class DrugAdminDtlDAO extends DataAccessObject implements DrugAdminDtlDAOi
{
	public  DrugAdminDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void save(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO){
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT_HRGT_EPISODE_DRUG_ADMIN_DTL";
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
        	populateMAP.put(sq.next(),drugAdminDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getEpisodeCode());
        	//adviceDate date
        	populateMAP.put(sq.next(),drugAdminDtlVO.getScheduleDate());
        	//serial no
        	populateMAP.put(sq.next(),drugAdminDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	
        	populateMAP.put(sq.next(),drugAdminDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getItemId());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDoseTime());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getAdministrationTime());
        	//populateMAP.put(sq.next(),drugAdminDtlVO.getAdministrationTime());	//added by adil
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDoseId());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getAdministrationEndTime());
        	//populateMAP.put(sq.next(),drugAdminDtlVO.getAdministrationEndTime());//added by adil
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDoseShift());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDrugSourceFlag());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	//entry date
        	populateMAP.put(sq.next(),drugAdminDtlVO.getBatchNo());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),drugAdminDtlVO.getExpiryDate());
        	//populateMAP.put(sq.next(),drugAdminDtlVO.getExpiryDate());//added by adil
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDoseName());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDrugRouteId());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getRemarks());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getIsEmptyStomach());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getItemName());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getUserEmpID());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getIsReaction());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getAdminFlag());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getActualDoseId());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getActualDoseName());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getActualDrugRouteId());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getItemTypeId());
        	//populateMAP.put(sq.next(),drugAdminDtlVO.getDrugBrandId());
        	
        	if(drugAdminDtlVO.getDrugBrandId().indexOf("#") < 0)	//By manisha on 31.05.18	 for seprating drug id and drug band id	
				populateMAP.put(sq.next(), drugAdminDtlVO.getDrugBrandId());
			else
				populateMAP.put(sq.next(), drugAdminDtlVO.getDrugBrandId().split("#")[1]);
		
        	
        	
        	populateMAP.put(sq.next(),drugAdminDtlVO.getBeforeTimeLimit());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getAfterTimeLimit());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getItemId());
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
	
	public void saveDrugAdminDetail(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO){
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT_DRUG_ADMIN_DTL_HRGT_EPISODE_DRUG_ADMIN_DTL";
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
        	populateMAP.put(sq.next(),drugAdminDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), drugAdminDtlVO.getAdviceDate());
        	
        	//schedule Date
        	//populateMAP.put(sq.next(),drugAdminDtlVO.getScheduleDate());
        	//serial no
        	populateMAP.put(sq.next(),drugAdminDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	
        	populateMAP.put(sq.next(),drugAdminDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getItemId());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDoseTime());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getAdministrationTime());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDoseId());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getAdministrationEndTime());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDoseShift());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDrugSourceFlag());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	//entry date
        	populateMAP.put(sq.next(),drugAdminDtlVO.getBatchNo());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),drugAdminDtlVO.getExpiryDate());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDoseName());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDrugRouteId());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getRemarks());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getIsEmptyStomach());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getItemName());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getUserEmpID());
        	//populateMAP.put(sq.next(),drugAdminDtlVO.getIsReaction());
        	populateMAP.put(sq.next(),InpatientConfig.IS_REACTION_NO);
        	populateMAP.put(sq.next(),drugAdminDtlVO.getAdminFlag());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getActualDoseId());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getActualDoseName());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getActualDrugRouteId());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getVolume().equals("")?"0":drugAdminDtlVO.getVolume());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getVolume().equals("")?"0":drugAdminDtlVO.getVolume());
           	populateMAP.put(sq.next(),drugAdminDtlVO.getDrugBrandId());
        	populateMAP.put(sq.next(),drugAdminDtlVO.getDrugBrandName());
     
        	
         	
        
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
	
	public void updateDrugAdminDetail(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="UPDATE.DRUG_ADMIN_DETAIL.HRGT_EPISODE_DRUG_ADMIN_DTL";
		 Sequence sq=new Sequence();
		 
		 try
		  {
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		  }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }
		  
		  try
		  {
			  populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			  //condition field
			  populateMAP.put(sq.next(), drugAdminDtlVO.getPatCrNo());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getAdviceDate());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getItemId());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getPatAdmNo());
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			  populateMAP.put(sq.next(), drugAdminDtlVO.getAdminFlag());
			  
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	//int i=
		   		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   //	int j=i;
		   	/*
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
			*/
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}
	
	public String getdrugStatus(DrugAdminDtlVO vo,UserVO userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_DRUG_STATUS.HRGT_EPISODE_DRUG_ADMIN_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), vo.getPatCrNo());
		populateMAP.put(sq.next(), vo.getItemId());
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
	
	public String getConsentStatus(DrugAdminDtlVO vo,UserVO userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "PKG_MRD_DTL.CONSENT_STATUS_FOR_DRUG";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), OpdConfig.SERVICE_TYPE_FOR_CONSENT);
		populateMAP.put(sq.next(), vo.getItemId());
		populateMAP.put(sq.next(), vo.getEpisodeVisitNo());
		populateMAP.put(sq.next(), vo.getPatCrNo());
		populateMAP.put(sq.next(), vo.getEpisodeCode());
		populateMAP.put(sq.next(), vo.getEpisodeVisitNo());
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No Consent Is Here");
			}
			return rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		
	}
	
	public void updateDrugExec(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="UPDATE_DRUG_EXECUTION.HRGT_EPISODE_DRUG_ADMIN_DTL";
		 Sequence sq=new Sequence();
		 
		 try
		  {
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		  }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }
		  
		  try
		  {
			  populateMAP.put(sq.next(), drugAdminDtlVO.getAdministrationTime());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getAdministrationEndTime().equalsIgnoreCase("")?"0": drugAdminDtlVO.getAdministrationEndTime());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getAdministrationEndTime().equalsIgnoreCase("")?"0": drugAdminDtlVO.getAdministrationEndTime());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getBatchNo());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getExpiryDate());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getRemarks());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getAdminFlag());
			 // populateMAP.put(sq.next(), OpdConfig.DRUG_GIVEN_IN_PER_SCHEDULE);
			  populateMAP.put(sq.next(), drugAdminDtlVO.getActualDoseId());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getActualDoseName());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getActualDrugRouteId());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getVolume().equalsIgnoreCase("")?"0": drugAdminDtlVO.getVolume());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getVolume().equalsIgnoreCase("")?"0": drugAdminDtlVO.getVolume());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getDrugBrandId());
	          populateMAP.put(sq.next(), drugAdminDtlVO.getDrugBrandName());

			  populateMAP.put(sq.next(), drugAdminDtlVO.getActualItemBrandID());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getActualItemName());
			  //condition field
			  populateMAP.put(sq.next(), drugAdminDtlVO.getPatCrNo());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getAdviceDate());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getSerialNo());
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	//int i=
		   		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	/*
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
			*/
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}
	
	public void updateIvFluidDrugExec(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="UPDATE_ENDTIME.HRGT_EPISODE_DRUG_ADMIN_DTL";
		 Sequence sq=new Sequence();
		 
		 try
		  {
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		  }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }
		  
		  try
		  {
			  
			  populateMAP.put(sq.next(), drugAdminDtlVO.getAdministrationEndTime());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getRemarks());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getAdminFlag());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getVolume());
			  //condition field
			  populateMAP.put(sq.next(), drugAdminDtlVO.getPatCrNo());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getAdviceDate());
			  populateMAP.put(sq.next(), drugAdminDtlVO.getSerialNo());
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	//int i=
		   		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	/*
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
			*/
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}
	
	public String getMaxSlNo(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_MAXSLNO.HRGT_EPISODE_DRUG_ADMIN_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), drugAdminDtlVO.getPatCrNo());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No record Is Here");
			}
			return rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		
	}
	public String getMaxAdminDate(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_MAXADMIN_DATE.HRGT_EPISODE_DRUG_ADMIN_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), drugAdminDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), drugAdminDtlVO.getEpisodeCode());
		populateMAP.put(sq.next(), OpdConfig.SCHEDULE);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No record Is Here");
			}
			return rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		
	}
	
	public void updateIsReactionStatus(DrugReactionVO drugReactionVO,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="UPDATE.IS_REACTION_STATUS.HRGT_EPISODE_DRUG_ADMIN_DTL";
		 Sequence sq=new Sequence();
		 
		 try
		  {
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		  }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }
		  
		  try
		  {
			  populateMAP.put(sq.next(), InpatientConfig.IS_REACTION_YES);
			  populateMAP.put(sq.next(), drugReactionVO.getPatCrNo());
			  //populateMAP.put(sq.next(), drugReactionVO.getAdministrationDate());
			  populateMAP.put(sq.next(), drugReactionVO.getAdviceDate());
			  populateMAP.put(sq.next(), drugReactionVO.getAdminSerialNo());
			  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}
	/*
	public List getDrugBatchNoLstFromStore(String storeId,String itemBrandId,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequenc.e();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.DRUG_FROM_STORE.HSTT_DRUG_CURRSTOCK_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), storeId);
		populateMAP.put(sq.next(), itemBrandId);
				

		List batchNoList = new ArrayList();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DrugAdminDtlVO.class, rs);			
				for (ValueObject v : vo)
					batchNoList.add((DrugAdminDtlVO)v);
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
		return batchNoList;
	}
	*/
	
	public List getDrugBatchNoLstFromStore(PatientDetailVO patientDetailVO,String itemIdList,UserVO _userVO)
	{
		HisDAO daoObj = null;
		int nProcIndex = 0;
		ResultSet rs = null;
		List alRecord = new ArrayList();
	
		String strErr = "";
		
		String strProcName = "{call pkg_opd_view.proc_hstt_drug_currstock_dtl (?,?,?,?,?,?,?,?,?,?,?)}";
		String pmode="2"; // pmode 1: batchno for drug brand,   2: batchno for generic drug
		
		try 
		{
		daoObj = new HisDAO("InPatient","InPatientEssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "pmode", pmode,1);
		daoObj.setProcInValue(nProcIndex, "req_type", InpatientConfig.SOURCE_OF_DRUG_STORE_STOCK_WARD,2);
		daoObj.setProcInValue(nProcIndex, "hosp_code", _userVO.getHospitalCode(),3);
		daoObj.setProcInValue(nProcIndex, "item_id", itemIdList,4);
		daoObj.setProcInValue(nProcIndex, "patAdmNo",patientDetailVO.getPatAdmNo(),5);
		daoObj.setProcInValue(nProcIndex, "deput_unit_code", patientDetailVO.getDepartmentUnitCode(),6);
		daoObj.setProcInValue(nProcIndex, "pat_cr_no ",patientDetailVO.getPatCrNo(),7);
		daoObj.setProcInValue(nProcIndex, "ward_code", patientDetailVO.getWardCode(),8);
		daoObj.setProcInValue(nProcIndex, "episode_code	", patientDetailVO.getEpisodeCode(),9);
				
		daoObj.setProcOutValue(nProcIndex, "err",1,10);
		daoObj.setProcOutValue(nProcIndex, "resultset",2,11);
		
		daoObj.executeProcedureByPosition(nProcIndex);
		
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
		if (daoObj != null) 
			daoObj.free();
		daoObj = null;
		}
		try
			{
			
			if (!rs.next())
			{				
				//throw new HisRecordNotFoundException("No Request Record Detail Found");
			}
			else
			{
			    rs.beforeFirst();
			    alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		    }
			}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getDrugBatchNoLstFromStore" + e);
		}
		return alRecord;
	}
	
	
	
	// Added by Manisha Gangwar Date: 03.May.2016 fetching drug brand list on basis of generic drug id
	
		public List getDrugBrandLstFromGenericType(PatientDetailVO patientDetailVO,String itemIdList,UserVO _userVO)
		{
			HisDAO daoObj = null;
			int nProcIndex = 0;
			ResultSet rs = null;
			List alRecord = new ArrayList();
		
			String strErr = "";
			
			String strProcName = "{call pkg_opd_view.proc_hstt_drugbrand_mst_getbrandfromgeneric (?,?,?,?,?,?,?)}";
			String pmode="1"; 
			
			try 
			{
			daoObj = new HisDAO("InPatient","InPatientEssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "pmode", pmode,1);
			daoObj.setProcInValue(nProcIndex, "item_id", itemIdList,2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", Config.SUPER_HOSPITAL_CODE,3);
			daoObj.setProcInValue(nProcIndex, "itembrand_id","",4);
			daoObj.setProcInValue(nProcIndex, "hstnum_itemtype_id", "",5);

					
			daoObj.setProcOutValue(nProcIndex, "err",1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset",2,7);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			
			System.out.println("strErr----------------------->"+strErr);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			finally{
				if (daoObj != null) 
					daoObj.free();
				daoObj = null;
				}
			try
				{
				
				if (!rs.next())
				{				
					//throw new HisRecordNotFoundException("No Request Record Detail Found");
				}
				else
				{
				    rs.beforeFirst();
				    alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			    }
				}
			catch (Exception e)
			{
				throw new HisDataAccessException("HisDataAccessException  :getDrugBrandLstFromGenericType" + e);
			}
			return alRecord;
		}
	
	
	// Commented by Manisha Gangwar on date: 03.dec.2015 for Pharmacy Linkage at Drug Administration
	/*public List getDrugBatchNoLstFromStore(String storeId,String itemBrandId,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.DRUG_FROM_STORE.HSTT_DRUG_CURRSTOCK_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), storeId);
		populateMAP.put(sq.next(), itemBrandId);
				

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		//	if (!rs.next()) throw new HisRecordNotFoundException("No Record for Anomoly Type Found");
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
			throw new HisDataAccessException("HisDataAccessException  :getAnomolyTypeList" + e);
		}
		return alRecord;
	}*/
	
}
