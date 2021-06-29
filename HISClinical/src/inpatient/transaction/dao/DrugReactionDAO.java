package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.vo.DrugReactionVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

public class DrugReactionDAO extends DataAccessObject implements DrugReactionDtlDAOi
{
	public  DrugReactionDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void save(DrugReactionVO drugReactionVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT_HRGT_EPISODE_DRUG_REACTION_DTL";
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
        	populateMAP.put(sq.next(),drugReactionVO.getPatCrNo());
        	//populateMAP.put(sq.next(),drugReactionVO.getRecordDate());
        	//serial no
        	//populateMAP.put(sq.next(),drugReactionVO.getPatCrNo());
        	//populateMAP.put(sq.next(),drugReactionVO.getRecordDate());
        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),drugReactionVO.getSerialNo());
        	
        	populateMAP.put(sq.next(),drugReactionVO.getEpisodeCode());
        	populateMAP.put(sq.next(),drugReactionVO.getPatAdmNo());
        	populateMAP.put(sq.next(),drugReactionVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),drugReactionVO.getItemId());
        	populateMAP.put(sq.next(),drugReactionVO.getReactionStartTime());
        	populateMAP.put(sq.next(),drugReactionVO.getBatchNo());
        	populateMAP.put(sq.next(),drugReactionVO.getDrugGivenTime());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),drugReactionVO.getReactionEndTime());
        	populateMAP.put(sq.next(),drugReactionVO.getControlSummary());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),drugReactionVO.getReactionSummary());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getUserEmpID());
         	populateMAP.put(sq.next(),drugReactionVO.getDrugBrandId());
        	populateMAP.put(sq.next(),drugReactionVO.getDrugBrandName());
        	
                	
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
	
	public List getTemplateIdList(UserVO userVO)
	{
		List tempalteIdList=new ArrayList();
		ResultSet rs=null;
		
		//PatientDetailVO patientDetailVO=new PatientDetailVO();
		String query = "";
		Map populateMap= new HashMap();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.TEMPLAEiD_LIST.HGBT_TEMPLATE_MAPPING_MST";
		
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), GenericTemplateConfig.TEMPLATE_CATEGORY_DRUG_REACTION);
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE );
	
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					tempalteIdList.add(rs.getString(1));
				}
			}
			
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		

		
		return tempalteIdList;
	}
	
	public String getMaxSlNo(String patCrNo,UserVO userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_MAXSLNO.HRGT_EPISODE_DRUG_REACTION_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), patCrNo);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		
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
	
	public String getdrugReactionStatus(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_DRUG_REACTION_STATUS.HRGT_EPISODE_DRUG_REACTION_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), patDrugDtlVO.getPatCrNo());
		//populateMAP.put(sq.next(), patDrugDtlVO.getEpisodeCode());
		populateMAP.put(sq.next(), patDrugDtlVO.getDrugId());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());
							
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No Drug Reaction");
			}
			return rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
	 }
	
	// Added by Manisha Gangwar Date: 13.04.2016 for Drug Alerts (Allergies, Reaction, Contradiction, Pregnancy ALert for Reactive Drug)
	
	public String getdrugAdviceAlerts(String pmode, HisDAO hisDAO_p, PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO)
	{
				
		int nFuntionIndex;
		//String strDBErr;
		//ResultSet objResSet;
		String strAlerts=null;
		try
		{
			nFuntionIndex = hisDAO_p.setFunction(OpdConfig.FUNC_EMR_VIEW_PAT_DRUG_ADVICE_ALERTS);
			// Setting and Registering In and Out Parameters 
			hisDAO_p.setFuncOutValue(nFuntionIndex, 1);
			hisDAO_p.setFuncInValue(nFuntionIndex, 2, pmode);
			hisDAO_p.setFuncInValue(nFuntionIndex, 3, _userVO.getHospitalCode());
			hisDAO_p.setFuncInValue(nFuntionIndex, 4, patDrugDtlVO.getPatCrNo());
			hisDAO_p.setFuncInValue(nFuntionIndex, 5, patDrugDtlVO.getDrugId());
			hisDAO_p.setFuncInValue(nFuntionIndex, 6, patDrugDtlVO.getDrugName());
			hisDAO_p.setFuncInValue(nFuntionIndex, 7, patDrugDtlVO.getEpisodeCode());
			hisDAO_p.setFuncInValue(nFuntionIndex, 8, OpdConfig.IS_EPISODE_OPEN);
			hisDAO_p.setFuncInValue(nFuntionIndex, 9, patDrugDtlVO.getDepartmentUnitCode());
			hisDAO_p.setFuncInValue(nFuntionIndex, 10, patDrugDtlVO.getAdmissionNo());
			hisDAO_p.setFuncInValue(nFuntionIndex, 11, OpdConfig.REVOKE);
			hisDAO_p.setFuncInValue(nFuntionIndex, 12, Config.IS_VALID_ACTIVE);
			hisDAO_p.setFuncInValue(nFuntionIndex, 13, Config.ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC);
					
				
			// Executing function
			hisDAO_p.executeFunction(nFuntionIndex);
			strAlerts = hisDAO_p.getFuncString(nFuntionIndex);
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO.getdrugAdviceAlerts::hisDAO_p.execute" + OpdConfig.FUNC_EMR_VIEW_PAT_DRUG_ADVICE_ALERTS
					+ ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) 
				hisDAO_p.free();
			hisDAO_p = null;
			}
		
		return strAlerts;
		
		
	}
		
		
	
	
	public List getPreviousDrugReactionListByCrNo(String CrNo,String episodeCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "SELECT_PREV_LIST.HRGT_EPISODE_DRUG_REACTION_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), CrNo);
		//populateMAP.put(sq.next(), episodeCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Room Alloted For This Unit");
			}*/

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}
	
	public List getDrugReactionDetail(DrugReactionVO drugReacVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.DRUG_REACTION_DETAIL.HRGT_EPISODE_DRUG_REACTION_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), drugReacVO.getPatCrNo());
		//populateMAP.put(sq.next(), drugReacVO.getEpisodeCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), drugReacVO.getItemId());

		List drugReactionVoList = new ArrayList();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DrugReactionVO.class, rs);			
				for (ValueObject v : vo)
					drugReactionVoList.add((DrugReactionVO)v);
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
		return drugReactionVoList;
	}
	
}
