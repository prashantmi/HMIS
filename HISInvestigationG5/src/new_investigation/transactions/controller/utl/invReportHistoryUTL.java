package new_investigation.transactions.controller.utl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.invReportHistoryDATA;
import new_investigation.transactions.controller.fb.invReportHistoryFB;
import new_investigation.vo.invReportHistoryVO;
import hisglobal.Status;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

public class invReportHistoryUTL extends ControllerUTIL {

	
	public static void getEssentiall(invReportHistoryFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();

		UserVO userVO = ControllerUTIL.getUserVO(request);
        
		invReportHistoryVO vo=new invReportHistoryVO();
		
		try{	
			Map mp=new HashMap(); 
			 
          HelperMethods.populate(vo,fb);

			ControllerUTIL.setSysdate(request);
			Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);

			mp=invReportHistoryDATA.fetchActiveReportData1(fb, userVO);
			WebUTIL.setMapInSession(mp, request);
			
				
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
	
	public static void getEssential(invReportHistoryFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();

		UserVO userVO = ControllerUTIL.getUserVO(request);
        
		invReportHistoryVO vo=new invReportHistoryVO();
		
		try{	
			Map mp=new HashMap(); 
			 
          HelperMethods.populate(vo,fb);

			ControllerUTIL.setSysdate(request);
			Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);

			mp=invReportHistoryDATA.fetchActiveReportData(fb, userVO);
			WebUTIL.setMapInSession(mp, request);
			
				
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
	
	
	public static void getalldata(invReportHistoryFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();

		UserVO userVO = ControllerUTIL.getUserVO(request);
        
		invReportHistoryVO vo=new invReportHistoryVO();
		
		try{	
			Map mp=new HashMap(); 
			 
          HelperMethods.populate(vo,fb);

			ControllerUTIL.setSysdate(request);
			Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);

			mp=invReportHistoryDATA.fetchActiveReportDataALL(fb, userVO);
			WebUTIL.setMapInSession(mp, request);
			
				
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
	

	public static void getalldataInactive(invReportHistoryFB fb,HttpServletRequest request,String check)
	{
		Status objStatus= new Status();

		UserVO userVO = ControllerUTIL.getUserVO(request);
        
		invReportHistoryVO vo=new invReportHistoryVO();
		
		try{	
			Map mp=new HashMap(); 
			 
          HelperMethods.populate(vo,fb);

          fb.setIsresumeservice(check);
          
			ControllerUTIL.setSysdate(request);
			Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);

			mp=invReportHistoryDATA.fetchActiveReportDataALLInactive(fb, userVO);
			WebUTIL.setMapInSession(mp, request);
			
				
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
}
