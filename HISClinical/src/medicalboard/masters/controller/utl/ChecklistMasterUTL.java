package medicalboard.masters.controller.utl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import medicalboard.MedicalBoardConfig;
import medicalboard.masters.controller.data.ChecklistMasterDATA;
import medicalboard.masters.controller.fb.ChecklistMasterFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.UserVO;

public class ChecklistMasterUTL extends ControllerUTIL
{
	public static void getIsCompulsoryOptions(ChecklistMasterFB _fb, HttpServletRequest _request)
	 {
		Status objStatus = new Status();
		List isCompulsoryOption = new ArrayList();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			isCompulsoryOption=ChecklistMasterDATA.getIsCompulsoryOptions(userVO);
			
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.IS_COMPULSORY_OPTION_LIST, isCompulsoryOption);
						
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "e.printStackTrace()", "");
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}
	
	
	public static boolean saveChecklistMst(ChecklistMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MedicalBoardChecklistVO checklistVO=new MedicalBoardChecklistVO();
		boolean flag=false;
		try
		{
			UserVO userVO = getUserVO(_request);
			HelperMethods.populate(checklistVO, _fb);
			
			flag=ChecklistMasterDATA.saveChecklistMst(checklistVO,userVO);
			if(!flag){
				throw new HisDuplicateRecordException("Checklist Already Exists");
			}
			objStatus.add(Status.DONE,"","Record Saved Successfully");
		}
		catch (HisDuplicateRecordException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.DONE, "",e.getMessage());
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
		return flag;
	}
	
	public static void getChecklistById(ChecklistMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MedicalBoardChecklistVO checklistVO=new MedicalBoardChecklistVO();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			String checkListId=_fb.getChk()[0].replace("^","@").split("@")[0];
			String serialNo=_fb.getChk()[0].replace("^","@").split("@")[2];
			checklistVO.setChecklistID(checkListId);
			checklistVO.setSerialNo(serialNo);
			
			checklistVO=ChecklistMasterDATA.getChecklistById(checklistVO,userVO);
			getIsCompulsoryOptions(_fb, _request);
			HelperMethods.populate(_fb,checklistVO);
			_fb.setSerialNo(serialNo);
			
			objStatus.add(Status.NEW);
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}


	public static boolean modifySaveChecklist(ChecklistMasterFB _fb,HttpServletRequest _request) {
		Status objStatus = new Status();
		MedicalBoardChecklistVO checklistVO=new MedicalBoardChecklistVO();
		boolean flag=false;
		try
		{
			UserVO userVO = getUserVO(_request);
			HelperMethods.populate(checklistVO, _fb);
			
			flag=ChecklistMasterDATA.modifySaveChecklist(checklistVO,userVO);
			if(!flag){
				throw new HisDuplicateRecordException("Checklist Already Exists");
				
			}
			objStatus.add(Status.DONE);
		}
		catch (HisDuplicateRecordException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.DONE, "",e.getMessage());
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
		return flag;
		
	}
	


}
