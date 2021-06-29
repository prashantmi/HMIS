package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.AssignmentChangeDtlVO;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.SummonAssignChangeDATA;
import mrd.transaction.controller.fb.SummonAssigmentChangeFB;

public class SummonAssignChangeUTL extends ControllerUTIL
{
	public static boolean getEssenForSummonAssignChange(SummonAssigmentChangeFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		Map mpEssential=null;
		try
		{
			setSysdate(request);
			
			
			mpEssential=SummonAssignChangeDATA.getEssenForSummonAssignChange(getUserVO(request));
			List summonTypeList=(List)mpEssential.get(MrdConfig.ALL_SUMMON_TYPE_LIST);
			List summonAssignChangeList=(List)mpEssential.get(MrdConfig.SUMMON_ASSIGN_CHANGE_LIST);
			
			if(summonAssignChangeList!=null)
			{
				Iterator changeAssignItr=summonAssignChangeList.iterator();
				while(changeAssignItr.hasNext())
				{
					SummonDetailVO vo=(SummonDetailVO)changeAssignItr.next();
					Iterator summonTypeItr=summonTypeList.iterator();
					while(summonTypeItr.hasNext())
					{
						Entry obj=(Entry)summonTypeItr.next();
						if(vo.getSummonTypeId().equals(obj.getValue()))
						{
							vo.setSummonTypeDesc(obj.getLabel());
						}
						
					}
				}
			}
			
			
			WebUTIL.setMapInSession(mpEssential, request);
			if(summonAssignChangeList==null || summonAssignChangeList.size()==0)
			{
				objStatus.add(Status.DONE, "", "No summon assigned to employee");
			}
			//objStatus.add(Status.INPROCESS);
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
	
	public static boolean getSummonDetail(SummonAssigmentChangeFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		SummonDetailVO postSummonVO=new SummonDetailVO();
		try
		{
			setSysdate(request);
			List allChangeSummonAssignList=(List)session.getAttribute(MrdConfig.SUMMON_ASSIGN_CHANGE_LIST);
						
			Iterator changeAssignItr=allChangeSummonAssignList.iterator();
			while(changeAssignItr.hasNext())
			{
				SummonDetailVO vo=(SummonDetailVO)changeAssignItr.next();
				if(vo.getSummonId().equals(fb.getSelectedSummonId()))
				{
					postSummonVO=vo;
				}
			}
			List selPostSummonVoLst=new ArrayList();	
			selPostSummonVoLst.add(postSummonVO);
			//WebUTIL.setAttributeInSession(request, MrdConfig.POST_SUMMON_LIST, selPostSummonVoLst);
			WebUTIL.setAttributeInSession(request, MrdConfig.SELECTED_SUMMON_ASSIGN_CHANGE_VO, postSummonVO);
			
			
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
	
	public static boolean searchEmpDetail(SummonAssigmentChangeFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		List empList=null;
		try
		{
			setSysdate(request);
						
			empList=SummonAssignChangeDATA.searchEmpDetail(fb.getStr_first_name().toUpperCase(),fb.getStr_middle_name().toUpperCase(),fb.getStr_last_name().toUpperCase(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.SEARCH_EMPLOYEE_lIST, empList);
			
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
	
	public static boolean getEmployeeDetail(SummonAssigmentChangeFB fb,HttpServletRequest request)
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
	
	public static boolean saveSummonDetail(SummonAssigmentChangeFB _fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		SummonDetailVO summonDetailVO=new SummonDetailVO();
		AssignmentChangeDtlVO assignChangeVO=new AssignmentChangeDtlVO();
		try
		{
			UserVO userVO = getUserVO(request);
			SummonDetailVO selectedVO=(SummonDetailVO)session.getAttribute(MrdConfig.SELECTED_SUMMON_ASSIGN_CHANGE_VO);
			
			
			//assignChangeVO.setSummonId(_fb.getSelectedSummonId());
			assignChangeVO.setSummonId(selectedVO.getSummonId());
			assignChangeVO.setOldAssignee(selectedVO.getAssignTo());
			assignChangeVO.setOldOtherAssignReason(selectedVO.getOtherAssignReason());
			assignChangeVO.setOldSummonAck(selectedVO.getSummonAck());
			assignChangeVO.setOldAssignSame(selectedVO.getAssignSame());
			assignChangeVO.setNewAssignee(_fb.getSelectedEmpId());
			assignChangeVO.setReason(_fb.getChangeAssignReason());
			
			summonDetailVO.setAssignTo(_fb.getSelectedEmpId());
			summonDetailVO.setAssignSame(_fb.getAssignFlag());
			summonDetailVO.setOtherAssignReason(_fb.getOtherAssignReason());
			//summonDetailVO.setSummonId(_fb.getSelectedSummonId());
			summonDetailVO.setSummonId(selectedVO.getSummonId());
			summonDetailVO.setStatus(MrdConfig.SUMMON_ASSIGN);
			
			SummonAssignChangeDATA.saveChangeAssignDetail(assignChangeVO,summonDetailVO,userVO);											
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
