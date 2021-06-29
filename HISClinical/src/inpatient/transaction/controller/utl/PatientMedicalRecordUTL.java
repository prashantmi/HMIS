package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.data.PatientMedicalRecordDATA;
import inpatient.transaction.controller.fb.PatientMedicalRecordFB;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PatientMedicalRecordUTL extends ControllerUTIL
{
	public static void getPatientChronicNAllergy(PatientMedicalRecordFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		/*PatAllergyDtlVO[] arrPatAllergyDtlVO=null;
		PatientAlertsDetailVO[] arrPatAssignedAlertVO=null;*/

		try
		{
			essentialMap=PatientMedicalRecordDATA.getPatientChronicNAllergy(fb.getPatCrNo(),getUserVO(request));
			WebUTIL.setMapInSession(essentialMap, request);
			
		/*	arrPatAllergyDtlVO=(PatAllergyDtlVO[])essentialMap.get(InpatientConfig.PATIENT_CURRENT_ALLERGY_VO_ARR);
			arrPatAssignedAlertVO=(PatientAlertsDetailVO[])essentialMap.get(InpatientConfig.PATIENT_CURRENT_CHRONIC_DISEASE_VO_ARR);*/
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
}
