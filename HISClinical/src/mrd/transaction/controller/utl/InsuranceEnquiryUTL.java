package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.InsuranceDetailVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.InsuranceEnquiryDATA;
import mrd.transaction.controller.fb.InsuranceEnquiryFB;

public class InsuranceEnquiryUTL extends ControllerUTIL
{
	public static boolean getEssenForInsuranceEnquiry(InsuranceEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		Map mpEssential=null;
		try
		{
			setSysdate(request);
						
			mpEssential=InsuranceEnquiryDATA.getEssenForInsuranceEnquiry(getUserVO(request));
			
			WebUTIL.setMapInSession(mpEssential, request);
			
			objStatus.add(Status.NEW);
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
	
	
	
	public static boolean searchInsuranceDtl(InsuranceEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		List insuranceDetailVOLst=null;
		try
		{
			setSysdate(request);
			
			insuranceDetailVOLst=InsuranceEnquiryDATA.searchInsuranceDtl(fb.getPatFirstName(),fb.getPatMiddleName(),fb.getPatLastName(),fb.getCompanyId(),fb.getPatCrNo(),fb.getPolicyNo(),getUserVO(request));
			
			WebUTIL.setAttributeInSession(request, MrdConfig.INSURANCE_DETAIL_VO_LIST_FOR_SEARCH, insuranceDetailVOLst);
						
			if(insuranceDetailVOLst==null ||insuranceDetailVOLst.size()==0)
			{
				objStatus.add(Status.NEW,"","");
				objStatus.add(Status.LIST,"No Record Found","");
			}
			else
			{
				objStatus.add(Status.NEW);
				objStatus.add(Status.LIST);
			}
			
			
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
	
		
	public static boolean getDetail(InsuranceEnquiryFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		List insuranceDetailVOLst=null;
		List mewInsuranceVoList=new ArrayList();
		try
		{
			setSysdate(request);
				
			insuranceDetailVOLst=(List)session.getAttribute(MrdConfig.INSURANCE_DETAIL_VO_LIST_FOR_SEARCH);
			InsuranceDetailVO vo=null;
			
			for(Integer i=0;i<insuranceDetailVOLst.size();i++)
			{
				if(i.toString().equals(fb.getSelectedIndex()))
				{
					vo=(InsuranceDetailVO)insuranceDetailVOLst.get(i);
					mewInsuranceVoList.add(vo);
					break;
				}
			}
			
			session.setAttribute(MrdConfig.INSURANCE_DETAIL_VO_FOR_DETAIL,mewInsuranceVoList);
			
			objStatus.add(Status.TRANSINPROCESS);
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
}
