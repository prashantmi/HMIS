package enquiry.transaction.controller.util;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.ExtAdministrationDATA;
import inpatient.transaction.controller.fb.ExtAdministrationFB;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.GuidlineEnquiryDATA;
import enquiry.transaction.controller.fb.GuidlinesEnquiryFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class GuidlinesEnquiryUTIL extends ControllerUTIL
{
	public static void setEssentials(GuidlinesEnquiryFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		Map mpEssentials = new HashMap();
		
		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			List templateList=GuidlineEnquiryDATA.getTemplateListForGuidlines(userVO);
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.TEMPLATE_LIST_FOR_GUIDLINE, templateList);
			
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	public static void getTemplateId(GuidlinesEnquiryFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		Map mpEssentials = new HashMap();
		
		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			List templateIdList=new ArrayList();
			templateIdList.add(_fb.getTemplateId());
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.SELECTED_TEMPLATEID_LIST, templateIdList);
			
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
}
