package ehr.followup.dataentry;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.dao.InPatientEssentialDAO;
import inpatient.transaction.dao.InPatientEssentialDAOi;
import inpatient.transaction.dao.InpatientDAO;
import inpatient.transaction.dao.InpatientDAOi;
import inpatient.transaction.dao.PatDischargeRequestDAO;
import inpatient.transaction.dao.PatDischargeRequestDAOi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import registration.RegistrationConfig;
import ehr.EHRConfig;
import ehr.followup.vo.EHRSection_FollowupVO;

public class EHRSection_FollowupBO {

	public Map getEssentials(EHRSection_FollowupVO followVO, UserVO userVO) {
		Map essentialMap = new HashMap();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<EHRSection_FollowupVO> lstEpisodeDtl = new ArrayList<EHRSection_FollowupVO>();
		List<EHRSection_FollowupVO> lstEpisodeSummaryDtl = new ArrayList<EHRSection_FollowupVO>();
		List<EHRSection_FollowupVO> lstEpisodeKeywords = new ArrayList<EHRSection_FollowupVO>();
		
		try
		{
			tx.begin();
			
			EHRSection_FollowupDAO EHRSection_FollowupDAO = new EHRSection_FollowupDAO(tx);
		
			
			// Episode All Visit Detail
			lstEpisodeDtl = EHRSection_FollowupDAO.retrieveAllVisitsByEpisodeNo(followVO, userVO);
			essentialMap.put(EHRConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST, lstEpisodeDtl);
			
			// Episode Current Visit Summary Detail
			lstEpisodeSummaryDtl = EHRSection_FollowupDAO.getAllVisitSummaryByEpisodeVisit(followVO, userVO);
			essentialMap.put(EHRConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST, lstEpisodeSummaryDtl);
			
			// Episode Keywords List Unit Wise
		
			lstEpisodeKeywords = EHRSection_FollowupDAO.getAllEpisodeKeywordList(userVO);
			//System.out.println("lstEpisodeKeywords::"+lstEpisodeKeywords);
			essentialMap.put(EHRConfig.OPD_ESSENTIAL_ALL_KEYWORDS_LIST, lstEpisodeKeywords);
			
		
			
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
		return essentialMap;
	}

	

	public void saveEhrFollowupdetails(EHRSection_FollowupVO followVO,
			UserVO userVO, String deskType) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			
			followVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
			
			EHRSection_FollowupDAO EHRSection_FollowupDAO = new EHRSection_FollowupDAO(tx);
		
			
			EHRSection_FollowupDAO.updateIsconfirmed(followVO, userVO);
			//update in episode table
			EHRSection_FollowupDAO.updateDailyPatientDetail(followVO, userVO);
			EHRSection_FollowupDAO.updateNextVisitDetail(followVO, userVO);
			
			// When Episode is closed
			if (followVO.getIsCloseVo() != null && followVO.getIsCloseVo().equals("1"))
			{
				
				EHRSection_FollowupDAO.create(followVO, userVO);
			}
			EHRSection_FollowupDAO.updateNextVisitAuditLog(followVO, userVO);
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
	
	//Added by Vasu on 18.Dec.2018 for DIscharge FollowUP
	public Map getDischargeStatusListNProfileStatus(PatientDetailVO patientVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstDischargeStatus=new ArrayList();
		String profileStatus="";
		
		try
		{
			tx.begin();
			EHRSection_FollowupDAO dao = new EHRSection_FollowupDAO(tx);
			
			lstDischargeStatus=dao.getDischargeStatusList(userVO);
			essentialMap.put(InpatientConfig.PATIENT_DISCHARGE_REQUEST_LIST, lstDischargeStatus);
			
			profileStatus=dao.getPatientProfileStatus(patientVO,userVO);
			essentialMap.put(InpatientConfig.ESSENTIAL_PROFILE_STATUS, profileStatus);
			
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
		return essentialMap;
	}
	
	
	public boolean getPatientStatus(String admNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false;
		
		try
		{
			tx.begin();
			EHRSection_FollowupDAO followUPDAO=new EHRSection_FollowupDAO(tx);
			flag=followUPDAO.checkPatientStatus(admNo,userVO);
			
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
		return flag;
	}
	
	public EHRSection_FollowupVO getDischargeRemarks(String admNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EHRSection_FollowupVO patDischargeReqVO;
		PatDischargeRequestDAOi dao=new PatDischargeRequestDAO(tx);
		
		try
		{
			tx.begin();
			EHRSection_FollowupDAO patDischargeReqDAO=new EHRSection_FollowupDAO(tx);
			patDischargeReqVO=patDischargeReqDAO.getDischargeRemarks(admNo,userVO);
			
			String dischargeStatus=dao.getDischargeStatus(admNo,userVO);
			//System.out.println("dischargeStatus....."+dischargeStatus);
			patDischargeReqVO.setDischargeStatus(dischargeStatus);
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
		return patDischargeReqVO;
	}
	//Added by Vasu on 03.Jan.2019 to save Discharge FollowUp Details
	public void saveDischargeFollowUpDetails(EHRSection_FollowupVO followVO,UserVO userVO, String deskType) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String status = "";
		try
		{
			tx.begin();

			
			followVO.setIsConfirmed(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
			
			EHRSection_FollowupDAO EHRSection_FollowupDAO = new EHRSection_FollowupDAO(tx);
		
			
			//EHRSection_FollowupDAO.updateIsconfirmed(followVO, userVO);
			//update in episode table
			//EHRSection_FollowupDAO.updateDailyPatientDetail(followVO, userVO);
			//EHRSection_FollowupDAO.updateNextVisitDetail(followVO, userVO);
			
			// When Episode is closed
			//if (followVO.getIsCloseVo() != null && followVO.getIsCloseVo().equals("1"))
			//{
				
				//EHRSection_FollowupDAO.saveDischargeFollowUpDetails(followVO, userVO);
			
			      EHRSection_FollowupDAO.saveSinglePageDischargeFollowUpDetails(followVO, userVO);
			//}
			//EHRSection_FollowupDAO.updateNextVisitAuditLog(followVO, userVO);
				
				//if(followVO.getPatStatus().equals(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED))
				//{
					status=InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE_APPROVAL;
					EHRSection_FollowupDAO.updatePatStatusForDischargeReq(status,followVO,userVO);
					EHRSection_FollowupDAO.updatePatStatusForDischargeReqInPatADMDiscTable(status,followVO,userVO);
					
					//ADDED ON Date: 08.06.2017 pkg_bloodbank new function called 
					//patDischargeReqDAO.crossmatched_bag_revert(patDischargeReqVO,userVO); //Commented by Vasu on 29.Jan.2018
				//}
				/*else
				{
					status=InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED;
					followVO.setIsDead("0");
					followVO.setNextVisitDate("");
					followVO.setDischargeStatus("");
					//patDischargeReqVO.setRemarks("");
					//patDischargeReqVO.setAdviceBy("");
					
					EHRSection_FollowupDAO.updatePatStatusForDischargeRevoke(status,followVO,userVO);				//patient Status =18				
					//BillFlag = inpatientDAO.GetBillAccStatus(patDischargeReqVO,userVO);
					//System.out.println(BillFlag);
					//if(BillFlag==true)
						//inpatientDAO.reopenPatBillingForDischargeRevoke(patDischargeReqVO,userVO);
					
				}*/
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
	
	public EHRSection_FollowupVO getDischargeFollowUpDetails(EHRSection_FollowupVO followVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatDischargeRequestDAOi patDischargeReqDAO=new PatDischargeRequestDAO(tx);
		EHRSection_FollowupDAO EHRSection_FollowupDAO = new EHRSection_FollowupDAO(tx);
		List<Entry> lstDischargeStatus=new ArrayList();
		try
		{
			
			tx.begin();

			
			//System.out.println("finall4");
			EHRSection_FollowupDAO.getDischargeFollowUpDetails(followVO, userVO);
			
			//Commented as Data is alrday coming from previous query
			
			/*//added by swati sagar on date:14-06-2019
			String dischargeStatus=patDischargeReqDAO.getDischargeStatus(followVO.getAdmissionNo(),userVO);//added by swati sagar on date:14-06-2019
			if(dischargeStatus==null){
				
				dischargeStatus="-1";
			}
			//System.out.println("dischargeStatus....."+dischargeStatus);
			followVO.setDischargeStatus(dischargeStatus);*/
			
			
			
			//tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
			
			 lstDischargeStatus=dao.getDischargeStatusList(userVO);
		
				//List<Entry> lstDischargeStatus= new ArrayList<Entry>();
				//lstDischargeStatus=(List<Entry>)map.get(InpatientConfig.PATIENT_DISCHARGE_REQUEST_LIST);
				//System.out.println("fb.getDischargeStatus()"+fb.getDischargeStatus());
				for(int i=0;i<lstDischargeStatus.size();i++){
					//System.out.println("gettttt");
					if( followVO.getDischargeStatus() !=null &&  followVO.getDischargeStatus().equals(lstDischargeStatus.get(i).getValue())){
						//System.out.println("gettttftyhdt"+lstDischargeStatus.get(i).getValue());
						followVO.setDischargeStatusName(lstDischargeStatus.get(i).getLabel());
					}
				}
				//System.out.println("disname::"+followVO.getDischargeStatusName());
		
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
		return followVO;
	}

}//End of Class
