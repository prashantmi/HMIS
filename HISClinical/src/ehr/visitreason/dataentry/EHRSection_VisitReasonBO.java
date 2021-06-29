package ehr.visitreason.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import ehr.visitreason.vo.EHRSection_VisitReasonVO;

public class EHRSection_VisitReasonBO {

	public EHRSection_VisitReasonVO[] getEssentials(PatientDetailVO visitVO, UserVO userVO) {
		Map essentialMap = new HashMap();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		EHRSection_VisitReasonVO[] VisitreasonVO = null;
		EHRSection_VisitReasonVO[] VisitreasonVO1 = null;
		//List<EHRSection_VisitReasonVO> lstEpisodeSummaryDtl = new ArrayList<EHRSection_VisitReasonVO>();
		
		
		try
		{
			tx.begin();
			
			EHRSection_VisitReasonDAO EHRSection_VisitReasonDAO = new EHRSection_VisitReasonDAO(tx);
			VisitreasonVO = EHRSection_VisitReasonDAO.getEssentials(visitVO, userVO,"1");
			//essentialMap.put(EHRConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST, lstEpisodeSummaryDtl);
			
			VisitreasonVO1 = EHRSection_VisitReasonDAO.getEssentials(visitVO, userVO,"2");
			
			if(VisitreasonVO1.length>0)
			{
			for(int i=0;i<=VisitreasonVO.length-1;i++)
			{
				
				VisitreasonVO[i].setPresentIllnessHistory(VisitreasonVO1[i].getPresentIllnessHistory());
				
			}
			}
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
		
		return VisitreasonVO;
	}

	public void saveDetails(EHRSection_VisitReasonVO visitVO, UserVO userVO) {
			JDBCTransactionContext tx = new JDBCTransactionContext();
		
		//List<EHRSection_VisitReasonVO> lstEpisodeSummaryDtl = new ArrayList<EHRSection_VisitReasonVO>();
		
		
		try
		{
			tx.begin();
			
			EHRSection_VisitReasonDAO EHRSection_VisitReasonDAO = new EHRSection_VisitReasonDAO(tx);
				EHRSection_VisitReasonDAO.saveDetails(visitVO, userVO,"1");
				EHRSection_VisitReasonDAO.saveDetails(visitVO, userVO,"2");
				
				if(visitVO.getPresentIllnessHistory()!=null && !visitVO.getPresentIllnessHistory().equalsIgnoreCase(""))
				{
					EHRSection_VisitReasonDAO.saveDetails(visitVO, userVO,"3");
				}
			//essentialMap.put(EHRConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST, lstEpisodeSummaryDtl);
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
		
		
	}

}
