package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.SummonAssignmentDetailDATA;
import mrd.transaction.controller.fb.SummonAssigmentFB;

public class SummonAssignmentDetailUTL extends ControllerUTIL
{
	public static boolean getEssenForSummonAssignDtl(SummonAssigmentFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		Map mpEssential=null;
		try
		{
			setSysdate(request);
			
			
			mpEssential=SummonAssignmentDetailDATA.getEssenForSummonAssignDtl(getUserVO(request));
			
			List summonTypeList=(List)mpEssential.get(MrdConfig.ALL_SUMMON_TYPE_LIST);
			List summonReceivedVOList=(List)mpEssential.get(MrdConfig.ALL_SUMMON_RECEIVED_LIST);
			
			if(summonReceivedVOList!=null)
			{
				Iterator summonRecevitr=summonReceivedVOList.iterator();
				while(summonRecevitr.hasNext())
				{
					SummonDetailVO vo=(SummonDetailVO)summonRecevitr.next();
					Iterator summonTypeItr=summonTypeList.iterator();
					while(summonTypeItr.hasNext())
					{
						Entry obj=(Entry)summonTypeItr.next();
						if(vo.getSummonTypeId().equals(obj.getValue()))
						{
							vo.setSummonTypeDesc(obj.getLabel());
						}
						if(vo.getIsUpload().equals(MrdConfig.IS_SUMMON_UPLOAD_YES))
						{
							vo.setIsUpload("Yes");
						}
						else
						{
							vo.setIsUpload("No");
						}
					}
				}
			}
			
			WebUTIL.setMapInSession(mpEssential, request);
			if(summonReceivedVOList==null || summonReceivedVOList.size()==0)
			{
				objStatus.add(Status.DONE, "", "No summon received");
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
	
	public static boolean getSummonDetail(SummonAssigmentFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		SummonDetailVO summonReceveVO=new SummonDetailVO();
		try
		{
			setSysdate(request);
			List allSummonReceived=(List)session.getAttribute(MrdConfig.ALL_SUMMON_RECEIVED_LIST);
						
			Iterator summonRecevItr=allSummonReceived.iterator();
			while(summonRecevItr.hasNext())
			{
				SummonDetailVO vo=(SummonDetailVO)summonRecevItr.next();
				if(vo.getSummonId().equals(fb.getSelectedSummonId()))
				{
					summonReceveVO=vo;
				}
			}
			List selSummonReceiveVoLst=new ArrayList();	
			selSummonReceiveVoLst.add(summonReceveVO);
			//WebUTIL.setAttributeInSession(request, MrdConfig.ALL_SUMMON_RECEIVED_LIST, selSummonReceiveVoLst);
			WebUTIL.setAttributeInSession(request, MrdConfig.SELECTED_SUMMON_RECEIVED_VO, summonReceveVO);
			
			
			objStatus.add(Status.INPROCESS);
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
	
	public static boolean searchEmpDetail(SummonAssigmentFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		List empList=null;
		try
		{
			setSysdate(request);
						
			empList=SummonAssignmentDetailDATA.searchEmpDetail(fb.getStr_first_name().toUpperCase(),fb.getStr_middle_name().toUpperCase(),fb.getStr_last_name().toUpperCase(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.SEARCH_EMPLOYEE_lIST, empList);
			
			
			//StaffEnquiryVO staffEnquiryVO=new StaffEnquiryVO();
			
			/*
			staffEnquiryVO.setStr_first_name(fb.getStr_first_name());
			staffEnquiryVO.setStr_middle_name(fb.getStr_middle_name());
			staffEnquiryVO.setStr_last_name(fb.getStr_last_name());
			
			StaffEnquiryVO[] staffEnqVOArray=SummonAssignmentDetailDATA.searchStaffDetail(staffEnquiryVO, getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.SEARCH_EMPLOYEE_lIST, staffEnqVOArray);
			*/
			objStatus.add(Status.INPROCESS);
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
	
	public static boolean getEmployeeDetail(SummonAssigmentFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		SummonDetailVO summonDetailVO=new SummonDetailVO();
		try
		{
			setSysdate(request);
			List searchEmpList=(List)session.getAttribute(MrdConfig.SEARCH_EMPLOYEE_lIST);
						
			Iterator itr=searchEmpList.iterator();
			while(itr.hasNext())
			{
				SummonDetailVO vo=(SummonDetailVO)itr.next();
				if(vo.getEmpId().equals(fb.getSelectedEmpId()))
				{
					summonDetailVO=vo;
				}
			}
			
			fb.setEmpName(summonDetailVO.getEmpName());
			fb.setEmpAddress(summonDetailVO.getEmpAddress());
			fb.setEmpAge(summonDetailVO.getEmpAge());
			fb.setEmpDesignation(summonDetailVO.getEmpDesignation());
			fb.setEmpGenderCode(summonDetailVO.getEmpGenderCode());
			
			WebUTIL.setAttributeInSession(request, MrdConfig.SELECTED_EMPLOYEE_DETAIL, summonDetailVO);
			//session.setAttribute(MrdConfig.SELECTED_SUMMON_RECEIVED_VO, summonReceveVO);
			
			objStatus.add(Status.INPROCESS);
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
	
	public static boolean saveAssigmentDetail(SummonAssigmentFB _fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		SummonDetailVO summonDetailVO=new SummonDetailVO();
		try
		{
			UserVO userVO = getUserVO(request);
			
			HttpSession session=WebUTIL.getSession(request);
			SummonDetailVO vo=(SummonDetailVO)session.getAttribute(MrdConfig.SELECTED_SUMMON_RECEIVED_VO);
			
			summonDetailVO.setAssignTo(_fb.getSelectedEmpId());
			summonDetailVO.setAssignSame(_fb.getAssignFlag());
			summonDetailVO.setOtherAssignReason(_fb.getOtherAssignReason());
			//summonDetailVO.setSummonId(_fb.getSelectedSummonId());
			summonDetailVO.setSummonId(vo.getSummonId());
			summonDetailVO.setStatus(MrdConfig.SUMMON_ASSIGN);
			
			
			
			SummonAssignmentDetailDATA.saveSummonAssigmentDetail(summonDetailVO,userVO);											
			objStatus.add(Status.DONE,"Details Saved","");
		}
		
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}
}
