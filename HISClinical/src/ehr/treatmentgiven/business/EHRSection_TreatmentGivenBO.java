/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.treatmentgiven.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ehr.EHRConfig;
import ehr.diagnosis.data.EHRSection_DiagnosisDAO;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import opd.OpdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import registration.RegistrationConfig;
import registration.dao.EpisodeDiagnosisDAO;
import registration.dao.EpisodeDiagnosisDAOi;
import ehr.treatmentgiven.data.EHRSection_TreatmentGivenDAO;
import ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO;
public class EHRSection_TreatmentGivenBO {

	/*public Map getSnomdDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List diagnosisTypeList = null;
		List diagnosisCodeList = null;
		List diagnosisSiteList = null;
		EHRSection_DiagnosisVO[] episodeDiagVO = null;
	
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			EHRSection_DiagnosisDAO ehrEssDAO = new EHRSection_DiagnosisDAO(tx);

			diagnosisTypeList = ehrEssDAO.getDiagnosisTypeListByDiseaseCode(_userVO,OpdConfig.CHOICE_HOSPITAL_CODE,OpdConfig.CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE);
			essentialMap.put(RegistrationConfig.DIAGNOSIS_LIST, diagnosisTypeList);

			// //fetching the diagnosis record for previous visit///
			episodeDiagVO = ehrEssDAO.getPrevDiagnosisDetail(_patDtlVO.getPatCrNo(),_patDtlVO.getEpisodeCode(),_userVO);
			essentialMap.put(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO, episodeDiagVO);
        }
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
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
		return essentialMap;

	}*/
	
	
	
	public void saveTreatmentGivenDetails(List<EHRSection_TreatmentGivenVO> addTreatmentVO,List<EHRSection_TreatmentGivenVO> revokedTreatment, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{

			EHRSection_TreatmentGivenDAO EHRSection_DiagnosisDAO = new EHRSection_TreatmentGivenDAO(tx);
			tx.begin();

			for(EHRSection_TreatmentGivenVO vo : addTreatmentVO)
			{
				EHRSection_TreatmentGivenDAO.create(vo, userVO);
			}
			
			/*for(EHRSection_TreatmentGivenVO rvo : revokedTreatment)
			{
				EHRSection_TreatmentGivenDAO.revokeTreatment(rvo, userVO);
			}*/
				
			
			
			
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}
   /*Added by VASU on 01.Feb.2018 to add diagnosis details in diagnosis composition */
	public List<EHRSection_TreatmentGivenVO> getPatientEncTreatmentGivenDetail (EHR_AccessPermissionVO _voEHRAccess, EHR_PatientEncounterVO _voEHREncounter, EHRSection_TreatmentGivenVO _voEHRTreatmentGiven)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<EHRSection_TreatmentGivenVO> lstPatEncTreatmentGiven= new ArrayList<EHRSection_TreatmentGivenVO>();
		try
		{
			EHRSection_TreatmentGivenDAO EHRSection_TreatmentGivenDAO = new EHRSection_TreatmentGivenDAO(tx);
			tx.begin();

			lstPatEncTreatmentGiven = EHRSection_TreatmentGivenDAO.getPatientTreatmentGivenDetail("0",_voEHRAccess, _voEHREncounter, _voEHRTreatmentGiven);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return lstPatEncTreatmentGiven;
	}
}
