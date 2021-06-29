package opd.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

import opd.OpdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class PatAlertsDetailDAO extends DataAccessObject implements PatAlertsDetailDAOi
{
	public PatAlertsDetailDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//  Getting All The Assigned Alerts of the Patient
	public List<PatientAlertsDetailVO> getPatientAssignedAlert(String crNo,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_PAT_ALERT.HPMRT_PAT_ALERTS_DTL";

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
			Sequence sq = new Sequence();

			populateMAP.put(sq.next(), crNo);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.populateMap::getPatientAssignedAlert::" + e);
		}

		List<PatientAlertsDetailVO> lstAssignedAlertVO = new ArrayList<PatientAlertsDetailVO>();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatientAlertsDetailVO.class, rs);
			for(ValueObject objVO : vo)
				lstAssignedAlertVO.add((PatientAlertsDetailVO)objVO);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return lstAssignedAlertVO;
	}
	
	// Saving the Patient Alerts
	public void create(PatientAlertsDetailVO patAlertDetailVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="INSERT.HPMRT_PAT_ALERTS_DTL";
       // String alertId=patAlertDetailVO.getPatAlertId();
		//String [] id;
		//id=alertId.split("#");
		//String idVal=id[0];
		//System.out.println("patAlertId:::::"+idVal);
       
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
        	
        	populateMAP.put(sq.next(), patAlertDetailVO.getPatCrNo());
        	populateMAP.put(sq.next(), patAlertDetailVO.getPatCrNo());
        	populateMAP.put(sq.next(), patAlertDetailVO.getAlertName());
        	populateMAP.put(sq.next(), patAlertDetailVO.getPatAlertId());
        	populateMAP.put(sq.next(), patAlertDetailVO.getRemarks());
        	populateMAP.put(sq.next(), patAlertDetailVO.getDurationDate());
        	populateMAP.put(sq.next(), patAlertDetailVO.getDurationDate());
        	populateMAP.put(sq.next(), patAlertDetailVO.getEffectiveTo());
        	populateMAP.put(sq.next(), patAlertDetailVO.getRevokeRemarks());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getIpAddress() );
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("PatAlertsDetailDAO.populateMAP::"+e);
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
	
	//  Revoking the Patient Alert
	public void updateAlert(PatientAlertsDetailVO patAlertDetailVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_REVOKE_ALERT.HPMRT_PAT_ALERTS_DTL";
		Sequence sq = new Sequence();
		
		
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
	    	 populateMAP.put(sq.next(), patAlertDetailVO.getRevokeRemarks());
	    	 populateMAP.put(sq.next(), patAlertDetailVO.getPatCrNo());
	    	 populateMAP.put(sq.next(), patAlertDetailVO.getPatAlertId());
	    	 
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("PatAlertsDetailDAO.populateMAP::"+e);
	    }
	    try
	    {
	        	
	      	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	}
	
	// Getting All The Alert of The patient
	public PatientAlertsDetailVO[] getAllPatientAlert(String crNo,UserVO userVO)
	{
		PatientAlertsDetailVO[] arrAllAlertVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_PATIENT_ALL_ALERT.HPMRT_PAT_ALERTS_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatientAlertsDetailVO.class, rs);
			arrAllAlertVO = new PatientAlertsDetailVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrAllAlertVO[i] = (PatientAlertsDetailVO) vo[i];
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

		return arrAllAlertVO;
	}

	
	public PatientAlertsDetailVO[] getPatientAlertEMR(String crNo,String[] departmentUnitArray, String accessType,UserVO userVO)
	{
		PatientAlertsDetailVO[] arrAllAlertVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HPMRT_PAT_ALERTS_DTL.CHRONIC_EMR";
		String orderBy=" ORDER BY a.gdt_entry_date, a.hgstr_alert_name";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(accessType.equals(MrdConfig.EPR_DISPLAY_ALL_RECORD_NO)){
				String inArg="";
				for(int i=0;i<departmentUnitArray.length;i++){
					inArg+=","+departmentUnitArray[i];
				}
				inArg=inArg.replaceFirst(",","");
				query+="  and (SELECT hgnum_deptunitcode FROM hrgt_episode_dtl WHERE hrgnum_episode_code = b.hrgnum_episode_code "
						+ "and HRGNUM_PUK=b.HRGNUM_PUK and HRGNUM_VISIT_NO=b.HRGNUM_VISIT_NO and gnum_isvalid=b.gnum_isvalid AND "
						+ "gnum_hospital_code = b.gnum_hospital_code) in ("+inArg+") ";
			}
			query+=orderBy;
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), OpdConfig.PATIENT_ALERT_REVOKED_NO);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}*/
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PatientAlertsDetailVO.class, rs);
				arrAllAlertVO = new PatientAlertsDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrAllAlertVO[i] = (PatientAlertsDetailVO) vo[i];
				}
			}
		}
		catch (Exception e)
		{
			new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrAllAlertVO;
	}
	
/**
## 		Modification Log		: Create 3 condition for diff-2 profile type						
##		Modify Date				: 12-01-2015	
##		Reason	(CR/PRS)		: To show episode wise data for Dynamic Profile & All Data for for Custom Mode
##		Modify By				: Akash Singh
*/
	public PatientAlertsDetailVO[] getPatientAlertsDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HPMRT_PAT_ALERTS_DTL.PAT_PROFILE_BY_CRNO";
		String queryKey_Auto = "SELECT.HPMRT_PAT_ALERTS_DTL.PAT_AUTO_PROFILE_BY_CRNO";		
		
		try
		{			
			if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				System.out.println("All VISIT"+"  QUERY= "+query);
			}
			if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey_Auto);
				System.out.println("All VISIT"+"  QUERY= "+query);
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey_Auto);
				query= query+"AND HRGNUM_VISIT_NO=?";
				System.out.println("Current VISIT"+"  QUERY= "+query);
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{		
			if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED))
			{
				populateMAP.put(sq.next(), _crNo);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE))
			{
				populateMAP.put(sq.next(), _crNo);
				//populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), "0");	//FOR checking hrgnum_isrevoked
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				populateMAP.put(sq.next(), _crNo);
				//populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), "0");	//FOR checking hrgnum_isrevoked
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), voDP.getEpisodeVisitNo());
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatAlertsDetailDAO.populateMAP::" + e);
		}

		PatientAlertsDetailVO[] patientAlertsDetailVO=null;
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Alerts Found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(PatientAlertsDetailVO.class, rs);
			patientAlertsDetailVO = new PatientAlertsDetailVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				patientAlertsDetailVO[i] = (PatientAlertsDetailVO) vo[i];
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
		return patientAlertsDetailVO;
	}

}
