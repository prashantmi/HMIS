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

import hisglobal.hisconfig.Config;
import opd.master.controller.data.AttendantReasonMasterDATA;
import opd.master.controller.fb.AttendantReasonFB;

public class AttendantReasonMasterUTL extends ControllerUTIL
{
	public static void addReason(AttendantReasonFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO = getUserVO(request);
		AttendantReasonMasterVO vo=new AttendantReasonMasterVO();
		HelperMethods.populate(vo, fb);
		vo.setIsValid(Config.IS_VALID_ACTIVE);
		
		try
		{
			AttendantReasonMasterDATA.addReason(vo,userVO);
			objStatus.add(Status.DONE,"Record added successfully","");
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	public static void getReason(AttendantReasonFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO = getUserVO(request);
		AttendantReasonMasterVO vo=new AttendantReasonMasterVO();
		//HelperMethods.populate(vo, fb);
		
		String chk=fb.getChk()[0].replace("^","@");
		String[] concatid=chk.split("@");
		fb.setReasonid(concatid[0]);
		fb.setHospitalcode(concatid[1]);
		fb.setSlno(concatid[2]);
		vo.setReasonid(concatid[0]);
		vo.setHospitalcode(concatid[1]);
		vo.setSlno(concatid[2]);
		//String reason=fb.getReason();
		try
		{
			AttendantReasonMasterVO addvo=AttendantReasonMasterDATA.getReason(vo, userVO);
			
			fb.setReason(addvo.getReason());
			fb.setIsvalid(addvo.getIsvalid());
			
			
			objStatus.add(Status.DONE);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}	
	}
	
	public static void updateReason(AttendantReasonFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO = getUserVO(request);
		//AddReasonMasterVO vo=new AddReasonMasterVO();
		AttendantReasonMasterVO vo=new AttendantReasonMasterVO();
		//HelperMethods.populate(vo, fb);
		
		
		
		vo.setReasonid(fb.getReasonid());
		vo.setHospitalcode(userVO.getHospitalCode());
		vo.setSlno(fb.getSlno());
		//HelperMethods.populate(vo, fb);
		//String reason=fb.getReason();
		try
		{
			AttendantReasonMasterDATA.updateReason(vo,userVO);
			objStatus.add(Status.DONE);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	public static void reasonModify(AttendantReasonFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO = getUserVO(request);
		AttendantReasonMasterVO vo=new AttendantReasonMasterVO();
		HelperMethods.populate(vo, fb);
		//String reason=fb.getReason();
		try
		{
			AttendantReasonMasterDATA.reasonModify(vo,userVO);
			objStatus.add(Status.DONE);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
}
