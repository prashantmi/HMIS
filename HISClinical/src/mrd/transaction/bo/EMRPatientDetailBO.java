/*Copyright Information			: C-DAC, Noida  
	 ## Project Name				: NIMS
	 ## Name of Developer		 	: Pragya Sharma 
	 ## Module Name					: MRD
	 ## Purpose						:EMR Patient detail BO
	 ## Date of Creation			:2015.02.02 
	 ## Modification Log			:				
 */
package mrd.transaction.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import mrd.transaction.dao.EMRPatientDetailDAO;
import mrd.vo.EMRPateintDataPolicyVO;
import opd.OpdConfig;

public class EMRPatientDetailBO
{
	// Getting Consultation Inbox Detail
	public List<ConsultationDtlVO> getConsultationInboxDetails(EMRPateintDataPolicyVO voEMRPolicy, DeskDetailVO voDeskMenuDtl, PatientDetailVO patientDetailVO, UserVO userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<ConsultationDtlVO> lstMenuDtl = null;
		HisDAO hisDAO = new HisDAO("Opd", "getDeskMenusdetails");	
		try
		{
			tx.begin();
			
			EMRPatientDetailDAO emrPatientDetailDAO = new EMRPatientDetailDAO();
			lstMenuDtl = emrPatientDetailDAO.getConsultation(hisDAO, "1", voEMRPolicy, voDeskMenuDtl, patientDetailVO, userVO);
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
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			if (hisDAO != null) 
				hisDAO.free();
			hisDAO = null;
			tx.close();
		}
		return lstMenuDtl;
	}

	public List<ConsentRequestVO> getConsentInboxDetails(EMRPateintDataPolicyVO voEMRPolicy, DeskDetailVO voDeskMenuDtl,PatientDetailVO patientDetailVO, UserVO userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<ConsentRequestVO> lstMenuDtl = null;
		HisDAO hisDAO = new HisDAO("Opd", "getDeskMenusdetails");	
		try
		{
			tx.begin();
			
			EMRPatientDetailDAO emrPatientDetailDAO = new EMRPatientDetailDAO();
			lstMenuDtl = emrPatientDetailDAO.getConsent(hisDAO, "1", voEMRPolicy, voDeskMenuDtl, patientDetailVO, userVO);
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
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			
			
				if (hisDAO != null) 
					hisDAO.free();
				hisDAO = null;
				
			tx.close();
		}
		return lstMenuDtl;
	}
}

