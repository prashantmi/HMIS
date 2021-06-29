package mortuary.masters.controller.hlp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mrd.MrdConfig;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.OpinionMasterVO;
import hisglobal.vo.UserVO;

public class OpinionMasterUTL extends ControllerUTIL
{
	
	
	//Saving the Data
	public static boolean saveOpinionMaster(OpinionMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean exist=true;
		OpinionMasterVO opinionMasterVO=new OpinionMasterVO();
		try
		{
			HelperMethods.populate(opinionMasterVO, fb);
			OpinionMasterDATA.saveOpinionMaster(opinionMasterVO,getUserVO(request));
			exist=false;
			fb.setHmode(fb.getHmode());
			objStatus.add(Status.DONE,"","Record Added Successfully");
			
		}
		catch (HisDuplicateRecordException e)
		{
			e.printStackTrace();
			exist=true;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			exist=true;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return exist;
	}
	
	public static boolean getDataForModify(OpinionMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		OpinionMasterVO _OpinionMasterVO = new OpinionMasterVO();
		Map mp = new HashMap();
		String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sOpinionCode = concatid[0];
			String shospitalCode = concatid[1];
			String sOpinionSlno = concatid[2];
			// putting the selected Record Primary Key into Vo

			fb.setOpinionCode(sOpinionCode);
			fb.setSlNo(sOpinionSlno);

			_OpinionMasterVO.setSlNo(sOpinionSlno);
			_OpinionMasterVO.setOpinionCode(sOpinionCode);
			
			_OpinionMasterVO = OpinionMasterDATA.getDataForModify(_OpinionMasterVO, userVO);
		    HelperMethods.populate(fb, _OpinionMasterVO);
		    fb.setOpinionCode(sOpinionCode);
			fb.setSlNo(sOpinionSlno);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}
	
	public static boolean saveModOpinionMaster(OpinionMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			OpinionMasterVO _OpinionMasterVO = new OpinionMasterVO();

			HelperMethods.populate( _OpinionMasterVO , fb);
			flag=OpinionMasterDATA.saveModOpinionMaster(_OpinionMasterVO, userVO);
			
			if(flag){
				objStatus.add(Status.DONE,"","record modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Opinion Name already exists");
				
			}
			
		}
		
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Opinion Name already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}
	
}
