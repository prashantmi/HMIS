/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.chronicdisease.business;

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
import ehr.chronicdisease.data.EHRSection_ChronicDiseaseDAO;
import ehr.chronicdisease.vo.EHRSection_ChronicDiseaseVO;
import ehr.investigation.data.EHRSection_InvestigationAdviceDAO;
import ehr.investigation.vo.EHRSection_InvestigationAdviceVO;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import ehr.investigation.vo.*;


public class EHRSection_ChronicDiseaseBO {

	

	//Added by Vasu on 03.May.2019
			public List<ProfileInvestigationVO> getPatientInvestigationDetails(UserVO userVO, PatientDetailVO selectedPatientVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<ProfileInvestigationVO> lstInvestigations=new ArrayList<ProfileInvestigationVO>();
				
				try
				{
					tx.begin();
					EHRSection_InvestigationAdviceDAO invEssentialDAO=new EHRSection_InvestigationAdviceDAO(tx);
					lstInvestigations = invEssentialDAO.getInvestigationsList(userVO, selectedPatientVO);
					
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
				return lstInvestigations;
				
			}

			public List<EHRSection_AllergiesVO> getPatientAllergyDetails(UserVO userVO, PatientDetailVO selectedPatientVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<EHRSection_AllergiesVO> lstAllergies=new ArrayList<EHRSection_AllergiesVO>();
				
				try
				{
					tx.begin();
					EHRSection_AllergiesDAO EssentialDAO=new EHRSection_AllergiesDAO(tx);
					lstAllergies = EssentialDAO.getAllergyList(userVO, selectedPatientVO);
					
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
				return lstAllergies;
			}

			//Added by Vasu on 08.May.2019
			public List<EHRSection_ChronicDiseaseVO> getPatientChronicDiseaseDetails(UserVO userVO, PatientDetailVO selectedPatientVO)
			{
				
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<EHRSection_ChronicDiseaseVO> lstChronicDiseases=new ArrayList<EHRSection_ChronicDiseaseVO>();
				
				try
				{
					tx.begin();
					EHRSection_ChronicDiseaseDAO EssentialDAO=new EHRSection_ChronicDiseaseDAO(tx);
					lstChronicDiseases = EssentialDAO.getChronicDiseaseList(userVO, selectedPatientVO);
					
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
				return lstChronicDiseases;
			}

	
	}//end class

	

	

