package new_investigation.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.InvResultEntryDATA;
import new_investigation.transactions.controller.data.InvestigationRaisingDtlDATA;
import new_investigation.transactions.controller.data.invStatusDashboardDATA;
import new_investigation.transactions.controller.fb.invStatusDashboardFB;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.invStatusDashboardVO;


public class invStatusDashboardUTIL {
	
	public static void getStatusDashoardRecord(invStatusDashboardFB fb, HttpServletRequest request) {
		Status objStatus= new Status();
		UserVO userVO = ControllerUTIL.getUserVO(request);
		invStatusDashboardVO invresultentryvo = new invStatusDashboardVO();
		
		try{	
			Map mp=new HashMap(); 
			ControllerUTIL.setSysdate(request);
			mp=invStatusDashboardDATA.getStatusDashboardRecord(invresultentryvo, userVO);
			WebUTIL.setMapInSession(mp, request);
			objStatus.add(Status.TRANSINPROCESS);
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

	public static void getSampleList(invStatusDashboardFB fb, HttpServletRequest objRequest_p) {
		HttpSession session=objRequest_p.getSession();
		Status objStatus = new Status();
		Map mp=new HashMap();
		String recordRequested = fb.getRequestedType();
		try
		{
			UserVO userVO=ControllerUTIL.getUserVO(objRequest_p);
			mp=invStatusDashboardDATA.getRequestedSampleList(recordRequested,userVO);
			WebUTIL.setMapInSession(mp, objRequest_p);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
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
