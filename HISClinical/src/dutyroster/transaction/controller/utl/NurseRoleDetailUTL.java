package dutyroster.transaction.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dutyroster.DutyRosterConfig;
import dutyroster.transaction.controller.data.NurseRoleDetailDATA;
import dutyroster.transaction.controller.fb.NurseRoleDetailFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;

public class NurseRoleDetailUTL extends ControllerUTIL
{
	public static void setEssentials(NurseRoleDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		Map mpEssentials = new HashMap();
			
		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			mpEssentials=NurseRoleDetailDATA.getNurseRoleDetail(userVO);
			
			WebUTIL.setMapInSession(mpEssentials, _rq);
			
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
	
	public static void getRosterDetail(NurseRoleDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List rosterDtlVoList=null;
		
		LinkedHashMap shiftMap=new LinkedHashMap();	
		LinkedHashMap rosterDetailMap=new LinkedHashMap();
		
		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			
			String rosterCatId=_fb.getRosterCatId();
			String dutyAreaCode=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE);
				
			rosterDtlVoList=NurseRoleDetailDATA.getRosterDetail(rosterCatId,dutyAreaCode,userVO);
			
			for(int i=0;i<rosterDtlVoList.size();i++)
			{
				RosterDtlVO rosterDtlVo=(RosterDtlVO)rosterDtlVoList.get(i);
				shiftMap.put(rosterDtlVo.getShiftId()+"#"+rosterDtlVo.getShiftDesc(), rosterDtlVo.getShiftName());
			}
			
			Set keys = shiftMap.keySet();
			Iterator itr=keys.iterator();
			while(itr.hasNext())
			{
				String key=(String)itr.next();
				List shiftWiseRosterVoLst=new ArrayList();
				for(int i=0;i<rosterDtlVoList.size();i++)
				{
					RosterDtlVO rosterDtlVo=(RosterDtlVO)rosterDtlVoList.get(i);
					
					if(rosterDtlVo.getShiftId().equals(key.split("#")[0]))
					{
						shiftWiseRosterVoLst.add(rosterDtlVo);
					}
				}
				
				rosterDetailMap.put(key, shiftWiseRosterVoLst);
			}
			
			WebUTIL.setAttributeInSession(_rq, DutyRosterConfig.ROSTER_DETAIL_MAP, rosterDetailMap);
			
			if(rosterDetailMap.size()==0)
			{
				objStatus.add(Status.DONE, "", "No nurse role detail found");
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
}
