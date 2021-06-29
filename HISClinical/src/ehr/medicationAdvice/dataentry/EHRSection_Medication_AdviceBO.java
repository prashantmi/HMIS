/*
 * Nilesh Gupta
 * Date:- 30-10-2017
 * New Process Status At Discharge
*/

package ehr.medicationAdvice.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import ehr.casesummary.dataentry.EHRSection_CaseSummaryDAO;
import ehr.casesummary.vo.EHRSection_CaseSummaryVO;
import ehr.medicationAdvice.dataentry.EHRSection_Medication_AdviceDAO;
import ehr.medicationAdvice.vo.EHRSection_Medication_AdviceVO;





public class EHRSection_Medication_AdviceBO {
	
	public EHRSection_Medication_AdviceVO getEssentials(EHRSection_Medication_AdviceVO summaryVO, UserVO userVO) {
		Map essentialMap = new HashMap();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EHRSection_Medication_AdviceVO medicationDtlVO = new EHRSection_Medication_AdviceVO();
		try
		{
			tx.begin();
			
			EHRSection_Medication_AdviceDAO EHRSection_StatusAtDischargeDAO = new EHRSection_Medication_AdviceDAO(tx);
			medicationDtlVO = EHRSection_StatusAtDischargeDAO.getEssentials(summaryVO, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return medicationDtlVO;
	}

	
	public void saveDetails(EHRSection_Medication_AdviceVO dischargeVO,UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			
			EHRSection_Medication_AdviceDAO EHRSection_StatusAtDischargeDAO = new EHRSection_Medication_AdviceDAO(tx);
			EHRSection_StatusAtDischargeDAO.saveDetails(dischargeVO, userVO);
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		
	}

}
