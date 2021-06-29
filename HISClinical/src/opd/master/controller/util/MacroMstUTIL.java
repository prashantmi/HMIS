package opd.master.controller.util;

import javax.servlet.http.HttpServletRequest;

import opd.master.controller.data.MacroMstDATA;
import opd.master.controller.fb.MacroMstFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.MacroMasterVO;
import hisglobal.vo.UserVO;

public class MacroMstUTIL extends ControllerUTIL
{
	public static void getProcessName(MacroMstFB _fb, HttpServletRequest _request)
	 {
		Status objStatus = new Status();
		//Map mp = new HashMap();
		MacroMasterVO _macroMstVO = new MacroMasterVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			 _fb.setProcessID(_fb.getControls()[0]);
			 String processID=_fb.getProcessID();
			 _macroMstVO= MacroMstDATA.getProcessName(processID,userVO);
			_fb.setProcessName(_macroMstVO.getProcessName());
			_fb.setProcessID(processID);
			_fb.setLength(_macroMstVO.getLength());
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
	

	// On Add Page saving Values into Database
	public static boolean saveMacroInfo(MacroMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			MacroMasterVO _macroMstVO = new MacroMasterVO();
			
			_macroMstVO.setMacroHeader(_fb.getMacroHeader());
			_macroMstVO.setMacroDesc(_fb.getMacroDesc());
			_macroMstVO.setProcessID(_fb.getProcessID());
			hasFlag=MacroMstDATA.saveMacroInfo(_macroMstVO, userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
			 }else
			 {		System.out.println("in false");
				 objStatus.add(Status.NEW, "The Macro Header Already Exists", "");
			 }

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
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
			objStatus.add(Status.ERROR, "", "Error");
			hasFlag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}

	public static boolean fetchMacroInfo(MacroMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MacroMasterVO _macroMstVO = new MacroMasterVO();
		//Map mp = new HashMap();
		//String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sMacroID = concatid[0];
			String sSlNo = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo

			_fb.setMacroID(sMacroID);
			_fb.setHospitalcode(sHtcode);
			_fb.setSlNo(sSlNo);

			_macroMstVO.setMacroID(_fb.getMacroID());
			_macroMstVO.setSlNo(sSlNo);
			_macroMstVO.setHospitalcode(_fb.getHospitalcode());
			_macroMstVO.setIsActive(_fb.getIsActive());

			_macroMstVO = MacroMstDATA.fetchMacroInfo(_macroMstVO, userVO);
			
			_fb.setMacroDesc(_macroMstVO.getMacroDesc());
			_fb.setMacroHeader(_macroMstVO.getMacroHeader());
			_fb.setProcessID(_macroMstVO.getProcessID());
			_fb.setMacroID(sMacroID);
			_fb.setSlNo(sSlNo);
			_macroMstVO = MacroMstDATA.getProcessName(_fb.getProcessID(), userVO);
			_fb.setProcessName(_macroMstVO.getProcessName());
			_fb.setLength(_macroMstVO.getLength());

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


	public static boolean saveModMacroInfo(MacroMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//List listDonationComponentVO = new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			MacroMasterVO _macroMstVO = new MacroMasterVO();
			_macroMstVO.setMacroHeader(_fb.getMacroHeader());
			_macroMstVO.setMacroDesc(_fb.getMacroDesc());
			_macroMstVO.setProcessID(_fb.getProcessID());
			_macroMstVO.setMacroID(_fb.getMacroID());
			_macroMstVO.setSlNo(_fb.getSlNo());
			_macroMstVO.setIsActive(_fb.getIsActive());			
			
				MacroMstDATA.saveModMacroInfo(_macroMstVO,userVO);
			
			objStatus.add(Status.TRANSINPROCESS);
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
}
