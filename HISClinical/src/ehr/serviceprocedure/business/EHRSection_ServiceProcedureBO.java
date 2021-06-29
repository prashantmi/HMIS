/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.serviceprocedure.business;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import investigationDesk.InvestigationConfig;
import ehr.allergies.data.EHRSection_AllergiesDAO;
import ehr.allergies.vo.EHRSection_AllergiesVO;
import ehr.investigation.data.EHRSection_InvestigationAdviceDAO;
import ehr.investigation.vo.EHRSection_InvestigationAdviceVO;
import ehr.patientreferral.data.EHRSection_PatientReferralDAO;
import ehr.patientreferral.vo.EHRSection_PatientReferralVO;
import ehr.serviceprocedure.data.EHRSection_ServiceProcedureDAO;
import ehr.serviceprocedure.vo.EHRSection_ServiceProcedureVO;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import ehr.investigation.vo.*;
import emr.vo.PatientClinicalDocDetailVO;


public class EHRSection_ServiceProcedureBO {

			public List<EHRSection_PatientReferralVO> getPatientRefferalDetails(UserVO userVO, PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<EHRSection_PatientReferralVO> lstRefferalDetails =new ArrayList<EHRSection_PatientReferralVO>();
				
				try
				{
					tx.begin();
					EHRSection_PatientReferralDAO EssentialDAO=new EHRSection_PatientReferralDAO(tx);
					lstRefferalDetails = EssentialDAO.getPatientReferralDetails(userVO, selectedPatientVO,clinicalDocVO);
					
				}
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					e.printStackTrace();
					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (HisApplicationExecutionException e)
				{
					tx.rollback();
					e.printStackTrace();
					throw new HisApplicationExecutionException();
				}

				catch (HisDataAccessException e)
				{
					tx.rollback();
					e.printStackTrace();
					throw new HisDataAccessException();
				}
				catch (Exception e)
				{
					e.printStackTrace();

					tx.rollback();
					throw new HisApplicationExecutionException();
				}
				finally
				{
					tx.close();
				}
			
				return lstRefferalDetails;
			}

			//Added by Vasu on 21.May.2019
			public List<EHRSection_ServiceProcedureVO> getServiceProcedures(UserVO userVO, PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
			     List<EHRSection_ServiceProcedureVO> lstServiceProcedures =new ArrayList<EHRSection_ServiceProcedureVO>();
			
			try
			{
				tx.begin();
				EHRSection_ServiceProcedureDAO EssentialDAO=new EHRSection_ServiceProcedureDAO(tx);
				lstServiceProcedures = EssentialDAO.getServiceProcedures(userVO, selectedPatientVO,clinicalDocVO);
				
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException();
			}

			catch (HisDataAccessException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisDataAccessException();
			}
			catch (Exception e)
			{
				e.printStackTrace();

				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
		
			return lstServiceProcedures;
				
				
			}

	
	}//end class

	

	

