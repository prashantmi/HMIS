package mrd.transaction.controller.utl;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.PostSummonDtlDATA;
import mrd.transaction.controller.fb.PostSummonDetailFB;

public class PostSummonDtlUTL extends ControllerUTIL
{
	public static boolean getEssenForPostSummonDtl(PostSummonDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		Map mpEssential=null;
		try
		{
			setSysdate(request);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
			fb.setSysDate(sysadteString);
			
			mpEssential=PostSummonDtlDATA.getEssenForPostSummonDtl(getUserVO(request));
			List summonTypeList=(List)mpEssential.get(MrdConfig.ALL_SUMMON_TYPE_LIST);
			List postSummonList=(List)mpEssential.get(MrdConfig.POST_SUMMON_LIST);
			
			if(postSummonList!=null)
			{
				Iterator postSummonItr=postSummonList.iterator();
				while(postSummonItr.hasNext())
				{
					SummonDetailVO vo=(SummonDetailVO)postSummonItr.next();
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
			if(postSummonList==null || postSummonList.size()==0)
			{
				objStatus.add(Status.DONE, "", "No post summon detail is pending ");
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
	
	public static boolean getSummonDetail(PostSummonDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		SummonDetailVO postSummonVO=new SummonDetailVO();
		try
		{
			setSysdate(request);
			List allPostSummon=(List)session.getAttribute(MrdConfig.POST_SUMMON_LIST);
						
			Iterator summonPostItr=allPostSummon.iterator();
			while(summonPostItr.hasNext())
			{
				SummonDetailVO vo=(SummonDetailVO)summonPostItr.next();
				if(vo.getSummonId().equals(fb.getSelectedSummonId().split("#")[0]))
				{
					postSummonVO=vo;
				}
			}
			List selPostSummonVoLst=new ArrayList();	
			selPostSummonVoLst.add(postSummonVO);
			//WebUTIL.setAttributeInSession(request, MrdConfig.POST_SUMMON_LIST, selPostSummonVoLst);
			WebUTIL.setAttributeInSession(request, MrdConfig.SELECTED_POST_SUMMON_VO, postSummonVO);
			
			
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
	
	public static boolean savePostSummonDetail(PostSummonDetailFB _fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		SummonDetailVO summonDetailVO=new SummonDetailVO();
		HttpSession session=WebUTIL.getSession(request);
		try
		{
			UserVO userVO = getUserVO(request);
			
			SummonDetailVO vo=(SummonDetailVO)session.getAttribute(MrdConfig.SELECTED_POST_SUMMON_VO);
			summonDetailVO.setSummonId(vo.getSummonId());
			
			if(_fb.getHearingAttendedFlag().equals(MrdConfig.IS_HEARING_ATTENDED_YES))
			{
				summonDetailVO.setStatus(MrdConfig.PRESENT_IN_COURT);
				summonDetailVO.setVisitRemarks(_fb.getVisitRemarks());
				/*
				if(_fb.getNextVisitDate()!=null && !_fb.getNextVisitDate().equals("") )
				{
					if(_fb.getNextHearingTimeHr()!=null &&! _fb.getNextHearingTimeHr().equals(""))
					{
						_fb.setNextHearingTimeHr("00");
					}
					
					if(_fb.getNextHearingTimeMin()!=null && _fb.getNextHearingTimeMin().equals(""))
					{
						_fb.setNextHearingTimeMin("00");
					}
					summonDetailVO.setNextHearingDateTime(_fb.getNextVisitDate()+" "+_fb.getNextHearingTimeHr()+":"+_fb.getNextHearingTimeMin());
				}
				*/
				
				
			}
			else
			{
				summonDetailVO.setStatus(MrdConfig.NOT_PRESENT_IN_COURT);
				summonDetailVO.setVisitRemarks(_fb.getNotAttendedReason());
			}
						
			PostSummonDtlDATA.savePostSummonDetail(summonDetailVO,userVO);											
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
