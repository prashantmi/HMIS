package medicalboard.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.MedicalBoardChecklistVO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.data.MbRegistrationSearchDATA;
import medicalboard.transactions.controller.fb.MbNewRegistrationFB;

public class MbRegistrationSearchUTL extends ControllerUTIL

{
	public static boolean getPatientCatAndCertificateType(MbNewRegistrationFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
//		HttpSession session=request.getSession();
		List orgTypeList=new ArrayList();
		try
		{
			
			List lstPatCat=MbRegistrationSearchDATA.getPatientCategory(getUserVO(request));
			WebUTIL.setAttributeInSession(request,MedicalBoardConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY ,lstPatCat);
			
			List lstCertificateType=MbRegistrationSearchDATA.getCertificateType(getUserVO(request));
			WebUTIL.setAttributeInSession(request,MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE ,lstCertificateType);
			
			List lstOrgName=MbRegistrationSearchDATA.getOrganisation(getUserVO(request));
			WebUTIL.setAttributeInSession(request,MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME ,lstOrgName);
			
			
			for(int i=1;i<MedicalBoardConfig.MEDICAL_BOARD_ORGANIZATION_TYPES.length;i++)
			{
				Entry entry=new Entry();
				entry.setLabel(MedicalBoardConfig.MEDICAL_BOARD_ORGANIZATION_TYPES[i]);
				entry.setValue(String.valueOf(i));
				orgTypeList.add(entry);
			}
			
			WebUTIL.setAttributeInSession(request,MedicalBoardConfig.MEDICALBOARD_ORGANIZATIONTYPES ,orgTypeList);
			
			objStatus.add(Status.INPROCESS);
		}
		/*catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		
		}*/
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
		
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	public static void getChecklistDetails(MbNewRegistrationFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
//		HttpSession session = WebUTIL.getSession(request);
		
		try
		{
			MedicalBoardChecklistVO[] checkListDtlVO=MbRegistrationSearchDATA.getAllChecklistDetails(getUserVO(request));
			
			
			WebUTIL.setAttributeInSession(request,MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_ARRAY , checkListDtlVO);
			//objStatus.add(Status.TRANSINPROCESS);
			//objStatus.add(Status.DONE);
			objStatus.add(Status.INPROCESS);
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
