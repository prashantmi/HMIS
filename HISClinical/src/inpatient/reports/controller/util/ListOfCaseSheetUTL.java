package inpatient.reports.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.reports.controller.data.ListOfCaseSheetDATA;
import inpatient.reports.controller.fb.ListOfCaseSheetFB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;

public class ListOfCaseSheetUTL extends ReportUTIL
{

	public static void getEssential(ListOfCaseSheetFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		//HttpSession session=request.getSession();
		//List listWard = new ArrayList();
	try{	
		setSysdate(request);
		Date dt=ListOfCaseSheetDATA.getSysDateAsDate();
		
		WebUTIL.getSession(request).setAttribute(InpatientConfig.SYSADATEOBJECT,dt);
		fb.setGraphOrText(Config.TEXT);	
		fb.setReportType(Config.RTF);
	}catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
	}
	catch(HisDataAccessException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR_DA,"","Data Access Error");
	}
	catch(HisApplicationExecutionException e)
	{		
		e.printStackTrace();
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR,"","Error");
	}		
	finally
	{
		WebUTIL.setStatus(request,objStatus);
		System.out.println("   -----> objStatus in finally  : "+objStatus);
		System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
	}	
}
	
	public static boolean getDeptUnitList(ListOfCaseSheetFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List statusLst=new ArrayList();
		boolean flag = true;
		
		try
		{
		
			List lstUnit=ListOfCaseSheetDATA.getDeptUnitList(getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_DEPT_UNIT_LIST ,lstUnit);
			
			for(int i=0;i<InpatientConfig.CASESHEET_STATUS_FOR_REPORT.length-1;i++)
			{
				Entry ent=new Entry();
				ent.setValue(String.valueOf(i));
				ent.setLabel(InpatientConfig.CASESHEET_STATUS_FOR_REPORT[i+1]);
				statusLst.add(ent);
			}
		WebUTIL.setAttributeInSession(request, InpatientConfig.CASESHEET_STATUS_FOR_REPORT_LIST, statusLst);		
		
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	
	public static boolean getWardOnBasisOfUnitCode(ListOfCaseSheetFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		List listWard = new ArrayList();
		//HttpSession session = request.getSession();
		
		try
		{
			String unitCodeWithDiagCodeType = fb.getUnitCode();
			String unitCode = unitCodeWithDiagCodeType.substring(0, unitCodeWithDiagCodeType.indexOf("^"));
			
			listWard=ListOfCaseSheetDATA.getWardOnBasisOfUnitCode(unitCode,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
			
		
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT, listWard);
			
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	public static void getConsultantDetails(ListOfCaseSheetFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//HttpSession session=_request.getSession();
		List consultantDetails = new ArrayList();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			consultantDetails=ListOfCaseSheetDATA.getAllConsultantDetails(_fb.getUnitCode(), userVO);

			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_LIST,consultantDetails);
		   	
		
			//objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_LIST,consultantDetails);
			objStatus.add(Status.UNSUCESSFULL, "", "No Consultant Found");
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
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	
	
		}

	
}
