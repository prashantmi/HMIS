package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.AttendantReasonMasterVO;
import javax.servlet.http.HttpServletRequest;

import opd.master.controller.data.AttendantReasonMasterDATA;
import opd.master.controller.fb.AttendantReasonFB;

public class AttendantReasonMasterUTIL extends ControllerUTIL
{
	// Saving Addendant Reason Record
	public static boolean saveRecord(AttendantReasonFB fb, HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		AttendantReasonMasterVO vo = new AttendantReasonMasterVO();

		try
		{
			UserVO userVO = getUserVO(request);
			HelperMethods.populate(vo, fb);

			AttendantReasonMasterDATA.saveRecord(vo, userVO);
			objStatus.add(Status.TRANSINPROCESS, "Record added successfully", "");
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}

	// Getting Addendant Reason Record
	public static void getRecord(AttendantReasonFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		AttendantReasonMasterVO vo = new AttendantReasonMasterVO();
		try
		{
			UserVO userVO = getUserVO(request);

			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			fb.setAttendantReasonID(concatid[0]);
			fb.setSlNo(concatid[2]);
			
			HelperMethods.populate(vo, fb);

			vo = AttendantReasonMasterDATA.getRecord(vo, userVO);

			HelperMethods.populate(fb, vo);

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
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
	
	// Modifying Addendant Reason Record
	public static boolean modifyReason(AttendantReasonFB fb, HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(request);
			AttendantReasonMasterVO vo = new AttendantReasonMasterVO();
			HelperMethods.populate(vo, fb);
			
			AttendantReasonMasterDATA.updateAttendantReason(vo, userVO);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
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
