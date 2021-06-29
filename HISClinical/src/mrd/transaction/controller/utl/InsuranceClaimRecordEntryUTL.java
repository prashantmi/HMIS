package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.InsuranceClaimRecordEntryDATA;
import mrd.transaction.controller.fb.InsuranceClaimRecordEntryFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.InsuranceDetailVO;

public class InsuranceClaimRecordEntryUTL extends ControllerUTIL
{
	public static boolean getEssenForInsuranceClaimRecordEntry(InsuranceClaimRecordEntryFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		Map mpEssential=null;
		try
		{ 
			setSysdate(request);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			fb.setSysDate(sysadteString);
			
			mpEssential=InsuranceClaimRecordEntryDATA.getEssenForInsuranceClaimRecordEntry(getUserVO(request));
			
			WebUTIL.setMapInSession(mpEssential, request);
			
			objStatus.add(Status.DONE);
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
	
	public static boolean getInsuranceDetail(InsuranceClaimRecordEntryFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		List insuranceDtlList=new ArrayList();
		try
		{
			
			insuranceDtlList=(List)session.getAttribute(MrdConfig.INSURANCE_RECEIVED_DTL_LIST);
			InsuranceDetailVO selectedInsuranceVO=null;
			
			for(int i=0;i<insuranceDtlList.size();i++)
			{
				InsuranceDetailVO insuranceVO=(InsuranceDetailVO)insuranceDtlList.get(i);
				if(insuranceVO.getInsuranceId().equals(fb.getSelectedInsuranceId()))
				{
					selectedInsuranceVO=insuranceVO;
				}
			}
			
			fb.setInsuranceId(fb.getSelectedInsuranceId());
						
			fb.setReceiveDateTime(selectedInsuranceVO.getReceiveDateTime());
			
			WebUTIL.setAttributeInSession(request, MrdConfig.SELECTED_INSURANCE_DETAIL_VO, selectedInsuranceVO);
			session.removeAttribute(MrdConfig.INSURANCE_RECEIVED_DTL_LIST);
			
			objStatus.add(Status.LIST);
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
	
	public static boolean saveInsuranceClaimRecordEntry(InsuranceClaimRecordEntryFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{ 
			InsuranceDetailVO insurenceVO=new InsuranceDetailVO();
			
			insurenceVO.setInsuranceId(fb.getInsuranceId());
			insurenceVO.setOpinionBy(fb.getOpinionBy());
			insurenceVO.setOpinionDate(fb.getOpinionDate());
			insurenceVO.setOpinionRemarks(fb.getOpinionRemarks());
			insurenceVO.setSendingMode(fb.getSendingMode());
			insurenceVO.setStatus(MrdConfig.INSURENCE_STATUS_OPINION_FILLED);
			
			InsuranceClaimRecordEntryDATA.saveInsuranceClaimRecordEntry(insurenceVO, getUserVO(request));
			
			//objStatus.add(Status.DONE,"","Opinion Detail Saved");
		}
		catch (HisRecordNotFoundException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		
		}
		catch (HisDataAccessException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			
		}
		catch (HisApplicationExecutionException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		catch (HisException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			
		}
		catch (Exception e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	
}
