/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.patientreferral.business;

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
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import ehr.investigation.vo.*;
import emr.vo.PatientClinicalDocDetailVO;


public class EHRSection_PatientReferralBO {

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
					//throw new HisRecordNotFoundException(e.getMessage());
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
				//	throw new HisDataAccessException();
				}
				catch (Exception e)
				{
					e.printStackTrace();

					tx.rollback();
				//	throw new HisApplicationExecutionException();
				}
				finally
				{
					tx.close();
				}
			
				return lstRefferalDetails;
			}

			//Added by Vasu on 12.Dec.2019
			public Map getPatientReferralEssentials(String patCrNo, UserVO userVO)
			{
				Map referralEssentialMap = new HashMap();
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List<Entry> referralDepartment = null;
				try
				{
					tx.begin();
					EHRSection_PatientReferralDAO EssentialDAO=new EHRSection_PatientReferralDAO(tx);
					referralDepartment = EssentialDAO.getPatientReferralDepartments(patCrNo,userVO,"1");
					
					referralEssentialMap.put("referral_department_list", referralDepartment);
				}
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					e.printStackTrace();
					//throw new HisRecordNotFoundException(e.getMessage());
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
				//	throw new HisDataAccessException();
				}
				catch (Exception e)
				{
					e.printStackTrace();

					tx.rollback();
				//	throw new HisApplicationExecutionException();
				}
				finally
				{
					tx.close();
				}
			
				return referralEssentialMap;
			}

			//Added by Vasu on 16.Dec.2019
			public static void savePatientReferralDetails(List<EHRSection_PatientReferralVO> lstReferVo,PatientDetailVO selectedPatientVO, UserVO userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
			
				try
				{
					tx.begin();
					EHRSection_PatientReferralDAO EssentialDAO=new EHRSection_PatientReferralDAO(tx);
					
                    for(int i=0;i<lstReferVo.size();i++)
                    {			
                    	EssentialDAO.savePatientReferralDetails(lstReferVo.get(i), selectedPatientVO,userVO);

	                }
				}
				catch (HisRecordNotFoundException e)
				{
					tx.rollback();
					e.printStackTrace();
					//throw new HisRecordNotFoundException(e.getMessage());
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
				//	throw new HisDataAccessException();
				}
				catch (Exception e)
				{
					e.printStackTrace();

					tx.rollback();
				//	throw new HisApplicationExecutionException();
				}
				finally
				{
					tx.close();
				}
			
				
				
			}

		//Added by Vasu on 18.Dec.2019
			public static void deletePreviousOperationDetails(EHRSection_PatientReferralVO referVO, UserVO userVO,PatientDetailVO ptaientDetailVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				
				try
				{
					tx.begin();

					EHRSection_PatientReferralDAO EssentialDAO=new EHRSection_PatientReferralDAO(tx);
					
					EssentialDAO.deleteReferralDetails(referVO,userVO,ptaientDetailVO);

				}
				
				catch (HisApplicationExecutionException e)
				{
					
					tx.rollback();
					throw new HisApplicationExecutionException(e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					
					tx.rollback();
					throw new HisDataAccessException(e.getMessage());
				}
				catch (Exception e)
				{
					
					tx.rollback();
					throw new HisException(e.getMessage());
				}
				finally
				{
					tx.close();
				}
				
			}

	
	}//end class

	

	

