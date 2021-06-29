package mrd.masters.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.EnclosureMasterVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.masters.controller.data.EnclosureMasterDATA;
import mrd.masters.controller.fb.EnclosureMasterFB;

public class EnclosureMasterUTL extends ControllerUTIL
{
	public static boolean saveEnclosureMstDetail(EnclosureMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			EnclosureMasterVO enclosureMstVO = new EnclosureMasterVO();
			
			enclosureMstVO.setEnclosure(_fb.getEnclosure().trim());
			//enclosureMstVO.setMaxPages(_fb.getMaxPages());
			enclosureMstVO.setSlNo("1");
						
			hasFlag=EnclosureMasterDATA.saveEnclosureMstDetail(enclosureMstVO, userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
			 }else
			 {	hasFlag=false;
				 objStatus.add(Status.NEW, "Enclosure Already Exists", "");
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
			hasFlag=false;
			objStatus.add(Status.ERROR, "", "Enclosure already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	public static boolean getDataForModify(EnclosureMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		EnclosureMasterVO enclosureMstVO = new EnclosureMasterVO();
		//List allRecordTypeList=null;	
		//HttpSession session=WebUTIL.getSession(_request);
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sEnclosureId = concatid[0];
			String sEnclosureSlno = concatid[1];
			String shospitalCode = concatid[2];
			
			// putting the selected Record Primary Key into Vo
			
			enclosureMstVO.setSlNo(sEnclosureSlno);
			enclosureMstVO.setEnclosureId(sEnclosureId);
			enclosureMstVO.setHospitalCode(shospitalCode);
			
					
			mp = EnclosureMasterDATA.getDataForModifyEnclosureMst(enclosureMstVO,userVO);
			
			enclosureMstVO=(EnclosureMasterVO)mp.get(MrdConfig.ENCLOSURE_MST_VO_FOR_MODIFY);
		    
			
			fb.setEnclosure(enclosureMstVO.getEnclosure());
			//fb.setMaxPages(enclosureMstVO.getMaxPages());
			fb.setEnclosureId(enclosureMstVO.getEnclosureId());
			fb.setSlNo(enclosureMstVO.getSlNo());
			fb.setIsValid(enclosureMstVO.getIsValid());
			
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
	
	public static boolean saveModEnclosureMaster(EnclosureMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			EnclosureMasterVO enclosureMstVO = new EnclosureMasterVO();

			
			enclosureMstVO.setEnclosure(_fb.getEnclosure().trim());
			//enclosureMstVO.setMaxPages(_fb.getMaxPages());
			enclosureMstVO.setEnclosureId(_fb.getEnclosureId());
			enclosureMstVO.setSlNo(_fb.getSlNo());
			enclosureMstVO.setIsValid(_fb.getIsValid());
						
			flag=EnclosureMasterDATA.saveModEnclosureMaster(enclosureMstVO,userVO);
			if(flag){
				objStatus.add(Status.DONE,"","Record Modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Enclosure already exists");
				
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
			objStatus.add(Status.ERROR, "", "Enclosure already exists");
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
