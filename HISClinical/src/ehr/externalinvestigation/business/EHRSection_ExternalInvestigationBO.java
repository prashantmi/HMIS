/**
##		Date					: 05-Aug-2019
##		Reason	(CR/PRS)		: External Investigation Section at SPD 
##		Created By				: Vasu
*/



package ehr.externalinvestigation.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ehr.EHRConfig;
import ehr.examination.data.EHRSection_ExaminationDAO;
import ehr.examination.vo.EHRSection_ExaminationVO;
import ehr.externalinvestigation.data.EHRSection_ExternalInvestigationDAO;
import ehr.externalinvestigation.vo.EHRSection_ExternalInvestigationVO;
import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import opd.OpdConfig;
import opd.dao.OpdEssentialDAO;
import opd.dao.OpdEssentialDAOi;
import opd.dao.PatientClinicalDetailDAO;
import opd.dao.PatientClinicalDetailDAOi;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.dao.TemplateMasterDAO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;



public class EHRSection_ExternalInvestigationBO {


	public Map getParameterForExtInv(PatientDetailVO selectedPatientVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map extInvEssentials = new HashMap();
		List lstParameter = null;
		List lstTests = null;
		EHRSection_ExternalInvestigationVO[] addedExtInv = null;
		try
		{
			tx.begin();
			EHRSection_ExternalInvestigationDAO dao = new EHRSection_ExternalInvestigationDAO(tx);
			lstParameter=dao.getParameterForExtInv(userVO);
			
			extInvEssentials.put(EHRConfig.PARAMETERS_FOR_EXTERNAL_INVESTIGATION, lstParameter);
			
			lstTests = dao.getTestNamesForExtInv(userVO);
			
			extInvEssentials.put(EHRConfig.TESTS_FOR_EXTERNAL_INVESTIGATION, lstTests);
			
			addedExtInv = dao.getAddedExtInvestigationData(selectedPatientVO,userVO);
			
			extInvEssentials.put(EHRConfig.ADDED_EXTERNAL_INVESTIGATIONS, addedExtInv);
				
			
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
		return extInvEssentials;
	}

	public void saveExtInvestigationDetail(List<EHRSection_ExternalInvestigationVO> lstExtInvDtl,PatientDetailVO selectedPatientVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
		tx.begin();
		EHRSection_ExternalInvestigationDAO dao = new EHRSection_ExternalInvestigationDAO(tx);
		for (EHRSection_ExternalInvestigationVO vo : lstExtInvDtl)
		{	
			String pmode="1";

			dao.saveExtInvestigationDetail(pmode,vo,selectedPatientVO,userVO);
					
		}
		
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

	public void deletePreviousExtInvestigations(EHRSection_ExternalInvestigationVO eHRExtInvDetailsVO, UserVO userVO)
	{
		
       JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();

			EHRSection_ExternalInvestigationDAO ExtInvEssentialDAO = new EHRSection_ExternalInvestigationDAO(tx);
			
			ExtInvEssentialDAO.deletePreviousExtInvestigations(eHRExtInvDetailsVO,userVO);

		}
		
		catch (HisApplicationExecutionException e)
		{
			//System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			//System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			//System.out.println(e.getMessage());
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}


}//end class
