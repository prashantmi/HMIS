package mrd.masters.controller.util;

/**
 * @author Pawan Kumar B N
 * Creation Date 03-Jul-2012
 */
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.MrdCheckListVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.masters.controller.data.MrdCheckListMasterDATA;
import mrd.masters.controller.fb.MrdCheckListMasterFB;

public class MrdCheckListMasterUTL extends ControllerUTIL
{
		
	public static boolean saveMrdCheckListMstDetail(MrdCheckListMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			MrdCheckListVO mrdCheckListMstVO = new MrdCheckListVO();
			
			mrdCheckListMstVO.setCheckList(_fb.getCheckList().trim());
			mrdCheckListMstVO.setSlNo("1");
			
			
			hasFlag=MrdCheckListMasterDATA.saveMrdCheckListMstDetail(mrdCheckListMstVO, userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
			 }else
			 {	hasFlag=false;
				 objStatus.add(Status.NEW, "Check List Already Exists", "");
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
			objStatus.add(Status.ERROR, "", "Check List already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	public static boolean getDataForModify(MrdCheckListMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MrdCheckListVO mrdCheckListMstVO = new MrdCheckListVO();
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sCheckListId = concatid[0];
			String sCheckListSlno = concatid[1];
			String shospitalCode = concatid[2];
			
			// putting the selected Record Primary Key into Vo
			mrdCheckListMstVO.setSlNo(sCheckListSlno);
			mrdCheckListMstVO.setCheckListId(sCheckListId);
			mrdCheckListMstVO.setHospitalCode(shospitalCode);
			
			mp = MrdCheckListMasterDATA.getDataForModifyMrdCheckListMst(mrdCheckListMstVO,userVO);
			
			mrdCheckListMstVO=(MrdCheckListVO)mp.get(MrdConfig.MRD_CHECK_LIST_MST_VO_FOR_MODIFY);
		    
			fb.setCheckList(mrdCheckListMstVO.getCheckList());
			fb.setCheckListId(mrdCheckListMstVO.getCheckListId());
			fb.setSlNo(mrdCheckListMstVO.getSlNo());
			fb.setIsValid(mrdCheckListMstVO.getIsValid());
			
					
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
	
	public static boolean saveModMrdCheckListMaster(MrdCheckListMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			MrdCheckListVO mrdCheckListMstVO = new MrdCheckListVO();

			mrdCheckListMstVO.setCheckList(_fb.getCheckList().trim());
			mrdCheckListMstVO.setCheckListId(_fb.getCheckListId());
			mrdCheckListMstVO.setSlNo(_fb.getSlNo());
			mrdCheckListMstVO.setIsValid(_fb.getIsValid());
						
			
			flag=MrdCheckListMasterDATA.saveModMrdCheckListMaster(mrdCheckListMstVO, userVO);
			if(flag){
				objStatus.add(Status.DONE,"","Record Modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Check List already exists");
				
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
			objStatus.add(Status.ERROR, "", "Check List already exists");
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
