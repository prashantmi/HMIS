package new_investigation.transactions.controller.utl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;








import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.machineEnquiryDATA;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.controller.fb.machineEnquiryFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;
import new_investigation.vo.machineEnquiryVO;

public class machineEnquiryUTIL extends ControllerUTIL
{
	public static void getEssential(machineEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		 
		UserVO userVO = ControllerUTIL.getUserVO(request);
		machineEnquiryVO invmachineEnquiryVO=new machineEnquiryVO();
		 	 
	try{	
		Map mp=new HashMap(); 
		
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		
		mp=machineEnquiryDATA.getMachineComboForEnquiry(invmachineEnquiryVO, userVO);
	
		
		WebUTIL.setMapInSession(mp, request);
		//HelperMethods.populate(fb, invmachineEnquiryVO);
	}catch(HisRecordNotFoundException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
	}
	catch(HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA,"","Data Access Error");
	}
	catch(HisApplicationExecutionException e)
	{		
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR,"","Error");
	}			
	
	
	
	}
	
	
	
	public static void getMachineBasedSampleNo(machineEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
	

		UserVO userVO = ControllerUTIL.getUserVO(request);
		machineEnquiryVO invmachineEnquiryVO=new machineEnquiryVO();
		 	 
	try{	
		Map mp=new HashMap(); 
		
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
	
		
		invmachineEnquiryVO.setFromDate(fb.getFromDate()); 
		invmachineEnquiryVO.setToDate(fb.getToDate());
		invmachineEnquiryVO.setMachineCode(fb.getMachineCode());
		invmachineEnquiryVO.setTestStatus(fb.getTestStatus());
		
		mp=machineEnquiryDATA.getMachineBasedSampleNo(invmachineEnquiryVO, userVO);
		WebUTIL.setMapInSession(mp, request);
		
		//HelperMethods.populate(fb, invmachineEnquiryVO);
	}catch(HisRecordNotFoundException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
	}
	catch(HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA,"","Data Access Error");
	}
	catch(HisApplicationExecutionException e)
	{		
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR,"","Error");
	}			
	
	
	
	}
	
	
	public static boolean setPatientEssentials(machineEnquiryFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		machineEnquiryVO invmachineEnquiryVO = new machineEnquiryVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
					
			invmachineEnquiryVO.setFromDate(fb.getFromDate()); 
			invmachineEnquiryVO.setToDate(fb.getToDate());
			invmachineEnquiryVO.setMachineCode(fb.getMachineCode());
			invmachineEnquiryVO.setTestStatus(fb.getTestStatus());
			invmachineEnquiryVO.setMachineLabSampleNo(fb.getMachineLabSampleNo());
			/*invmachineEnquiryVO.setLabCode(fb.getLabCode());
			invmachineEnquiryVO.setRecord(fb.getRecord());*/
					
			mp=machineEnquiryDATA.getPatientmachineEnquiry(invmachineEnquiryVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			
			
			//HelperMethods.populate(fb, invmachineEnquiryVO);
			  
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	
	


		
		
		
	
		
		
		
}