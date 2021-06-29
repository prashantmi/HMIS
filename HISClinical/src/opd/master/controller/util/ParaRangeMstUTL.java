package opd.master.controller.util;

import javax.servlet.http.HttpServletRequest;


import opd.OpdConfig;
import opd.master.controller.data.ParaRangeMstDATA;
import opd.master.controller.fb.ParaRangeMstFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import hisglobal.utility.HelperMethods;

import hisglobal.vo.ParameterRangeMasterVO;
import hisglobal.vo.UserVO;

public class ParaRangeMstUTL extends ControllerUTIL
{
	public static void getParaName(ParaRangeMstFB _fb, HttpServletRequest _request)
	 {
		Status objStatus = new Status();
		//Map mp = new HashMap();
		
		ParameterRangeMasterVO _ParameterRangeMasterVO = new ParameterRangeMasterVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			_fb.setHmode(_fb.getTempMode());
			if(_fb.getHmode().equals("ADD"))
			{
			 _fb.setParaId(_fb.getControls()[0]);
			 String paraID=_fb.getParaId();
			 
			 _ParameterRangeMasterVO= ParaRangeMstDATA.getParaName(paraID,userVO);
			 	 
			_fb.setParaName(_ParameterRangeMasterVO.getParaName());
			_fb.setParaId(paraID);
			}
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "e.printStackTrace()", "dfsdf");
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
	}
	
	

	// On Add Page saving Values into Database
	public static boolean saveParaRangeInfo(ParaRangeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			ParameterRangeMasterVO parameterRangeMasterVO = new ParameterRangeMasterVO();
			HelperMethods.populate(parameterRangeMasterVO, _fb);
			
		
			hasFlag=ParaRangeMstDATA.saveParaRangeInfo(parameterRangeMasterVO, userVO);
			
			if(hasFlag)
			 {
				
				objStatus.add(Status.DONE, "Record Added Successfully", "");
			 }else
			 {	
				 objStatus.add(Status.INPROCESS, "Age overlapping", "");
			 }

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.DONE, "", "Duplicate Reocrd exists");
			hasFlag = false;
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			hasFlag = false;

		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, "Age Overlapping:Check age range", "");
			hasFlag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}

	public static boolean fetchParaRangeInfo(ParaRangeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		ParameterRangeMasterVO parameterRangeMasterVO = new ParameterRangeMasterVO();
		//Map mp = new HashMap();
		
		//String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
		
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String sParaID = concatid[0];
			String sParaRangeID = concatid[1];
			String sHtcode = concatid[2];
			String paraName="";
			// putting the selected Record Primary Key into Vo

			_fb.setParaId(sParaID);
			_fb.setHospitalCode(sHtcode);
			_fb.setParaRangeId(sParaRangeID);
			
			parameterRangeMasterVO.setParaId(_fb.getParaId());
			
			parameterRangeMasterVO = ParaRangeMstDATA.getParaName(_fb.getParaId(), userVO);
			
			paraName=parameterRangeMasterVO.getParaName();
			
			parameterRangeMasterVO.setParaId(_fb.getParaId());
			parameterRangeMasterVO.setParaRangeId(sParaRangeID);
			parameterRangeMasterVO.setHospitalCode(_fb.getHospitalCode());
			parameterRangeMasterVO.setIsValid(_fb.getIsValid());
			
			parameterRangeMasterVO = ParaRangeMstDATA.fetchParaRangeInfo(parameterRangeMasterVO, userVO);
			
			HelperMethods.populate(_fb, parameterRangeMasterVO);
			
			_fb.setParaName(paraName);
			_fb.setParaId(sParaID);
			_fb.setParaRangeId(sParaRangeID);
			_fb.setHospitalCode(sHtcode);
			_fb.setParaRangeId(sParaRangeID);
			
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


	public static boolean saveModParaRangeInfo(ParaRangeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=false;
		try
		{
			UserVO userVO = getUserVO(_request);
			ParameterRangeMasterVO parameterRangeMasterVO = new ParameterRangeMasterVO();
			if(_fb.getGenderCode().equals(OpdConfig.GENDER_FLAG_FOR_NOT_REQUIRED)){
				_fb.setLowAge(null);
				_fb.setHighAge(null);
			}
			HelperMethods.populate(parameterRangeMasterVO , _fb);
		//	_fb.setHmode(_fb.getTempMode());
			hasFlag=ParaRangeMstDATA.saveModParaRangeInfo(parameterRangeMasterVO, userVO);
			if(hasFlag)
			 {
				
				objStatus.add(Status.DONE, "Record Modified Successfully", "");
			 }else
			 {	
				 objStatus.add(Status.INPROCESS, "Age overlapping", "");
			 }
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Duplicate Record");
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
			objStatus.add(Status.ERROR, "Age Overlapping:Check age range", "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	

}
