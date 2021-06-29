/*
 * Nilesh Gupta
 * Date:- 30-10-2017
 * New Process Status At Discharge
*/

package ehr.statusdischarge.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import ehr.statusdischarge.dataentry.EHRSection_StatusAtDischargeDAO;
import ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO;




public class EHRSection_StatusAtDischargeBO {
	
	public EHRSection_StatusAtDischargeVO getEssentials(EHRSection_StatusAtDischargeVO dischargeVO, UserVO userVO) {
		Map essentialMap = new HashMap();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EHRSection_StatusAtDischargeVO statusdischargeVO = null;
		//List<EHRSection_VisitReasonVO> lstEpisodeSummaryDtl = new ArrayList<EHRSection_VisitReasonVO>();
		
		
		try
		{
			tx.begin();
			
			EHRSection_StatusAtDischargeDAO EHRSection_StatusAtDischargeDAO = new EHRSection_StatusAtDischargeDAO(tx);
			statusdischargeVO = EHRSection_StatusAtDischargeDAO.getEssentials(dischargeVO, userVO);
			//essentialMap.put(EHRConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST, lstEpisodeSummaryDtl);
			
			
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
		return statusdischargeVO;
	}

	

	public void saveDetails(EHRSection_StatusAtDischargeVO dischargeVO,
			UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			
			EHRSection_StatusAtDischargeDAO EHRSection_StatusAtDischargeDAO = new EHRSection_StatusAtDischargeDAO(tx);
			EHRSection_StatusAtDischargeDAO.saveDetails(dischargeVO, userVO);
		//essentialMap.put(EHRConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST, lstEpisodeSummaryDtl);
			
			
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
