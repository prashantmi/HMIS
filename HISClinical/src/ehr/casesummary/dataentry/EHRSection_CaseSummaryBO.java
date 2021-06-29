
package ehr.casesummary.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import ehr.casesummary.vo.EHRSection_CaseSummaryVO;
import ehr.statusdischarge.dataentry.EHRSection_StatusAtDischargeDAO;
import ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO;




public class EHRSection_CaseSummaryBO {
	
	public EHRSection_CaseSummaryVO getEssentials(EHRSection_CaseSummaryVO summaryVO, UserVO userVO) {
		Map essentialMap = new HashMap();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EHRSection_CaseSummaryVO caseSummaryDtlVO = new EHRSection_CaseSummaryVO();
		try
		{
			tx.begin();
			
			EHRSection_CaseSummaryDAO EHRSection_CaseSummaryDAO = new EHRSection_CaseSummaryDAO(tx);
			caseSummaryDtlVO = EHRSection_CaseSummaryDAO.getEssentials(summaryVO, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			//throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			//throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			//throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			//throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			//throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return caseSummaryDtlVO;
	}

	

	public void saveDetails(EHRSection_CaseSummaryVO summaryVO,UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			
			EHRSection_CaseSummaryDAO EHRSection_CaseSummaryDAO = new EHRSection_CaseSummaryDAO(tx);
			EHRSection_CaseSummaryDAO.saveDetails(summaryVO, userVO);
		
			
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
