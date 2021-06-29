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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.SummonInboxDATA;
import mrd.transaction.controller.fb.SummonInboxFB;

public class SummonInboxUTL extends ControllerUTIL
{
	public static boolean getEssentialForSummonDtl(SummonInboxFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		Map mpEssential=null;
		try
		{
			mpEssential=SummonInboxDATA.getEssentialForSummonInbox(getUserVO(request));
			
			List summonTypeList=(List)mpEssential.get(MrdConfig.ALL_SUMMON_TYPE_LIST);
			List summonAssignedList=(List)mpEssential.get(MrdConfig.ALL_ASSIGNED_SUMMON_DETAIL_LIST);
			
			if(summonAssignedList!=null)
			{
				Iterator changeAssignItr=summonAssignedList.iterator();
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
			
			if(summonAssignedList==null || summonAssignedList.size()==0)
			{
				objStatus.add(Status.DONE,"","No summon found");
			}
			else
			{
				objStatus.add(Status.DONE);
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
	
	public static boolean getSummonDetail(SummonInboxFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		SummonDetailVO summonDtlVO=new SummonDetailVO();
		UserVO userVo=getUserVO(request);
		try
		{
			setSysdate(request);
			List allChangeSummonAssignList=(List)session.getAttribute(MrdConfig.ALL_ASSIGNED_SUMMON_DETAIL_LIST);
						
			Iterator changeAssignItr=allChangeSummonAssignList.iterator();
			while(changeAssignItr.hasNext())
			{
				SummonDetailVO vo=(SummonDetailVO)changeAssignItr.next();
				if(vo.getSummonId().equals(fb.getSelectedSummonId()))
				{
					summonDtlVO=vo;
				}
			}
			
			SummonInboxDATA.saveSummonAcceptenceDtl(fb.getSelectedSummonId(), userVo);
			
			WebUTIL.setAttributeInSession(request, MrdConfig.SELECTED_SUMMON_ASSIGN_ACCEPTED_VO, summonDtlVO);
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
	
	
	
}
