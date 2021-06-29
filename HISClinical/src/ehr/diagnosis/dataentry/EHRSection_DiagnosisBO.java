/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.diagnosis.dataentry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ehr.EHRConfig;
import ehr.diagnosis.dao.EHRSection_DiagnosisDAO;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import opd.OpdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import registration.RegistrationConfig;


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

	
}
