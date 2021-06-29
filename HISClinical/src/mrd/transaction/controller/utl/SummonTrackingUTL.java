package mrd.transaction.controller.utl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.SummonTrackingDATA;
import mrd.transaction.controller.fb.SummonTrackingFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;

public class SummonTrackingUTL extends ControllerUTIL
{
	public static boolean setEssential(SummonTrackingFB fb,HttpServletRequest request)
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
			UserVO userVO = getUserVO(request);
			fb.setSysdate(sysadteString);
			
			mpEssential=SummonTrackingDATA.getEssentialForSummonTracking(userVO);
			
			fb.setDisplayMode(MrdConfig.DISPLAY_MODE_IS_LISTING);
			
			WebUTIL.setMapInSession(mpEssential, request);
			
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
	
	
	public static boolean searchSummonDetail(SummonTrackingFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		Map mpEssential=null;
		Integer noOfReceivedSummon=0;
		Integer noOfAssignedSummon=0;
		Integer noOfAttendedSummon=0;
		Integer noOfNotAttendedSummon=0;
		Integer noOfAcceptedSummon=0;
		try
		{
			setSysdate(request);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			UserVO userVO = getUserVO(request);
			fb.setSysdate(sysadteString);
						
			mpEssential=SummonTrackingDATA.searchSummonDetail(fb.getSelectCriteria(),fb.getFromDate(),fb.getToDate(),fb.getEmployeeId(),fb.getSummonTypeId(),userVO);
			
			
			List summonTypeList=(List)session.getAttribute(MrdConfig.ALL_SUMMON_TYPE_LIST);
			List searchSummonVOList=(List)mpEssential.get(MrdConfig.ALL_SEARCH_SUMMON_DTL_VO_LIST);
			
			
			
			if(searchSummonVOList!=null)
			{
				Iterator itr=searchSummonVOList.iterator();
				while(itr.hasNext())
				{
					SummonDetailVO vo=(SummonDetailVO)itr.next();
					Iterator summonTypeItr=summonTypeList.iterator();
					while(summonTypeItr.hasNext())
					{
						Entry obj=(Entry)summonTypeItr.next();
						if(vo.getSummonTypeId().equals(obj.getValue()))
						{
							vo.setSummonTypeDesc(obj.getLabel());
						}
						
					}
					if(vo.getStatus().equals(MrdConfig.SUMMON_RECEIVED))
					{
						vo.setStatusDesc("Received");
						noOfReceivedSummon++;
					}
					if(vo.getStatus().equals(MrdConfig.SUMMON_ASSIGN))
					{
						vo.setStatusDesc("Assign");
						noOfAssignedSummon++;
					}
					if(vo.getStatus().equals(MrdConfig.PRESENT_IN_COURT))
					{
						vo.setStatusDesc("Attented");
						noOfAttendedSummon++;
					}
					if(vo.getStatus().equals(MrdConfig.NOT_PRESENT_IN_COURT))
					{
						vo.setStatusDesc("NotAttented");
						noOfNotAttendedSummon++;
					}
					if(vo.getStatus().equals(MrdConfig.SUMMON_ACCEPTED))
					{
						vo.setStatusDesc("Accepted");
						noOfAcceptedSummon++;
					}
					
				}
				
				
			}
			
			fb.setNoOfAcceptedSummon(noOfAcceptedSummon.toString());
			fb.setNoOfAssignedSummon(noOfAssignedSummon.toString());
			fb.setNoOfAttendedSummon(noOfAttendedSummon.toString());
			fb.setNoOfNotAttendedSummon(noOfNotAttendedSummon.toString());
			fb.setNoOfReceivedSummon(noOfReceivedSummon.toString());
			
			if(searchSummonVOList!=null)
			{
				Integer noOfRecord=searchSummonVOList.size();
				fb.setNoOfRecord(noOfRecord.toString());
			}
			
						
			WebUTIL.setMapInSession(mpEssential, request);
			
			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			fb.setNoOfAcceptedSummon(noOfAcceptedSummon.toString());
			fb.setNoOfAssignedSummon(noOfAssignedSummon.toString());
			fb.setNoOfAttendedSummon(noOfAttendedSummon.toString());
			fb.setNoOfNotAttendedSummon(noOfNotAttendedSummon.toString());
			fb.setNoOfReceivedSummon(noOfReceivedSummon.toString());
			
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
