package webServiceReport.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import new_investigation.InvestigationConfig;
import webServiceReport.data.FetchReportDATA;
import new_investigation.transactions.controller.data.InvValueAuditDATA;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.vo.InvValueAuditVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

public class FetchReportUTL extends ControllerUTIL {
	
	
	public static String getReportFileName(String crNo,String reqNo,String reqDno,String hosCode)
	{
		Status objStatus= new Status();

		String filename="";        
		
		try{	
			
			filename=FetchReportDATA.getReportFileName( crNo, reqNo, reqDno, hosCode);
			
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
		return filename;

	}  

	public static String getPortalVal(String hosCode)
	{
		Status objStatus= new Status();

		String isPortal="";        
		
		try{	
			
			isPortal=FetchReportDATA.getPortalVal(hosCode);
			
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
		return isPortal;

	}
}
