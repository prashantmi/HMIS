package mrd.transaction.controller.utl;

import inpatient.InpatientConfig;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.transaction.controller.data.DupRecPrintAndHandoverDATA;
import mrd.transaction.controller.data.DuplicateRecordPrintReqDATA;
import mrd.transaction.controller.fb.DuplicateRecordPrintReqFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.PatientDetailVO;

public class DuplicateRecordPrintReqUTL extends ControllerUTIL {
	
	public static boolean getEssentialData(DuplicateRecordPrintReqFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		
		try
		{
			setSysdate(request);
			Map map=DuplicateRecordPrintReqDATA.getEssentialData(getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
		
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
		return flag;
	}
	
	public static boolean saveDuplicateRecordPrintReqDtl(DuplicateRecordPrintReqFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		HttpSession session = request.getSession();
		
		try
		{
			mrd.vo.DupRecPrintReqVO dupRecPrintReqVO=new mrd.vo.DupRecPrintReqVO();
			HelperMethods.populate(dupRecPrintReqVO, fb);
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			if(ptaientDetailVO!=null)
			{
				HelperMethods.populatetToNullOrEmpty(dupRecPrintReqVO, ptaientDetailVO);
				dupRecPrintReqVO.setPatAge(ptaientDetailVO.getPatAge().replaceAll("\\D", ""));
			}
			setSysdate(request);
			DuplicateRecordPrintReqDATA.saveDuplicateRecordPrintReqDtl(dupRecPrintReqVO,getUserVO(request));
			//objStatus.add(Status.NEW);\
			objStatus.add(Status.DONE,"","Record Added Successfully");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
		
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
		return flag;
	}

	
}
