/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.diagnosis.business;

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


public class EHRSection_DiagnosisBO {

	public Map getSnomdDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
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

	}
	
	
	
	public void saveOpdDiagnosisDetails(List<EHRSection_DiagnosisVO> addepisodeDiaVO, List<EHRSection_DiagnosisVO>  episodeRevokeDiaVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{

			EHRSection_DiagnosisDAO EHRSection_DiagnosisDAO = new EHRSection_DiagnosisDAO(tx);
			tx.begin();

			for(EHRSection_DiagnosisVO vo : addepisodeDiaVO)
			{
				EHRSection_DiagnosisDAO.create(vo, userVO);
			}
			
			for(EHRSection_DiagnosisVO rvo : episodeRevokeDiaVO)
			{
				EHRSection_DiagnosisDAO.revokeDiagnosis(rvo, userVO);
			}
				
			
			
			
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
   /*Added by VASU on 26.Sept.2017 to add diagnosis details in diagnosis composition*/
	public List<EHRSection_DiagnosisVO> getPatientEncDiagnosisDetail (EHR_AccessPermissionVO _voEHRAccess, EHR_PatientEncounterVO _voEHREncounter, EHRSection_DiagnosisVO _voEHRDiagnosis)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<EHRSection_DiagnosisVO> lstPatEncDiagosis= new ArrayList<EHRSection_DiagnosisVO>();
		try
		{
			EHRSection_DiagnosisDAO EHRSection_DiagnosisDAO = new EHRSection_DiagnosisDAO(tx);
			tx.begin();

			lstPatEncDiagosis = EHRSection_DiagnosisDAO.getPatientDiagnosisDetail("0",_voEHRAccess, _voEHREncounter, _voEHRDiagnosis);
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
		return lstPatEncDiagosis;
	}
}
