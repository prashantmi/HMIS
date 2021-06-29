package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.PostmortemTeamDetailDATA;
import mortuary.transaction.controller.fb.PostmortemTeamDetailFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.PostmortemTeamDetailVO;

public class PostmortemTeamDetailUTL extends ControllerUTIL
{
	public static void getEssentialForTeamDetail(PostmortemTeamDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//PostmortemTeamDetailVO[] addedTeamVO=null;
		
		try
		{
			Map map=PostmortemTeamDetailDATA.getEssentialForTeamDetail(fb.getPostmortemId(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDuplicateRecordException e)
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
	}
	
	public static void addRow(PostmortemTeamDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemTeamDetailVO[] postmortemTeamVO1=null;
		PostmortemTeamDetailVO[] postmortemTeamVO2=null;
		PostmortemTeamDetailVO[] postmortemTeamVO3=null;
		List lstTeam=null;
		List lstRole=null;
		String teamMemberName="";
		String roleName="";
		
		try
		{
			lstTeam=(List)session.getAttribute(MortuaryConfig.POSTMORTEM_CONDUCTED_BY_LIST);
			for(int i=0;i<lstTeam.size();i++)
			{
				Entry ent=(Entry)lstTeam.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getTeamMember()))
			    {
			    	teamMemberName=ent.getLabel();
			    	break;
			    }
			}
			
			lstRole=(List)session.getAttribute(MortuaryConfig.POSTMORTEM_CONDUCTED_BY_ROLE);
			for(int i=0;i<lstRole.size();i++)
			{
				Entry ent=(Entry)lstRole.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getRole()))
			    {
			    	roleName=ent.getLabel();
			    	break;
			    }
			}
			
			List newList = new ArrayList(lstTeam);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getTeamMember()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.POSTMORTEM_CONDUCTED_BY_LIST,newList);
			
			postmortemTeamVO1=(PostmortemTeamDetailVO[])session.getAttribute(MortuaryConfig.ARR_POSTMORTEM_TEAM_DETAIL_VO);
			
			if(postmortemTeamVO1==null)
			{
				postmortemTeamVO2=new PostmortemTeamDetailVO[1];
				postmortemTeamVO2[0]=new PostmortemTeamDetailVO();
				postmortemTeamVO2[0].setEmpId(fb.getTeamMember());
				postmortemTeamVO2[0].setEmpName(teamMemberName);
				postmortemTeamVO2[0].setRoleId(fb.getRole());
				postmortemTeamVO2[0].setRoleName(roleName);
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_POSTMORTEM_TEAM_DETAIL_VO, postmortemTeamVO2);
			}
			else
			{
				postmortemTeamVO3=new PostmortemTeamDetailVO[postmortemTeamVO1.length+1];
				int i=0;
				for(;i<postmortemTeamVO1.length;i++)
				{
					postmortemTeamVO3[i]=postmortemTeamVO1[i];
				}
				postmortemTeamVO3[i]=new PostmortemTeamDetailVO();
				postmortemTeamVO3[i].setEmpId(fb.getTeamMember());
				postmortemTeamVO3[i].setEmpName(teamMemberName);
				postmortemTeamVO3[i].setRoleId(fb.getRole());
				postmortemTeamVO3[i].setRoleName(roleName);
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_POSTMORTEM_TEAM_DETAIL_VO, postmortemTeamVO3);
			}
			
			objStatus.add(Status.TRANSINPROCESS);
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
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void deleteRow(PostmortemTeamDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemTeamDetailVO[] postmortemTeamVO1=null;
		PostmortemTeamDetailVO[] postmortemTeamVO2=null;
		
		try
		{
			List lstTeam=(List)session.getAttribute(MortuaryConfig.POSTMORTEM_CONDUCTED_BY_LIST);
			List newList = new ArrayList(lstTeam);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenTeamMemberId(),fb.getHiddenTeamMemberName()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.POSTMORTEM_CONDUCTED_BY_LIST,newList);
			
			postmortemTeamVO1=(PostmortemTeamDetailVO[])session.getAttribute(MortuaryConfig.ARR_POSTMORTEM_TEAM_DETAIL_VO);
			postmortemTeamVO2=new PostmortemTeamDetailVO[postmortemTeamVO1.length-1];
			
			int j=0;
			for(int i=0;i<postmortemTeamVO1.length;i++)
			{
				if(!fb.getHiddenTeamMemberId().equals(postmortemTeamVO1[i].getEmpId()))
				{
					postmortemTeamVO2[j]=postmortemTeamVO1[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_POSTMORTEM_TEAM_DETAIL_VO, postmortemTeamVO2);
			
			objStatus.add(Status.TRANSINPROCESS);
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
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void saveTeamDetail(PostmortemTeamDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemTeamDetailVO[] teamDtlVO=null;
		PostmortemTeamDetailVO[] addedTeamDtlVO=null;
		List<PostmortemTeamDetailVO> lstTeamAdd=null;
		List<PostmortemTeamDetailVO> lstTeamModify=null;
		
		try
		{
			//////////Team Member Add Start////////// 
			lstTeamAdd=new ArrayList<PostmortemTeamDetailVO>();
			teamDtlVO=(PostmortemTeamDetailVO[])session.getAttribute(MortuaryConfig.ARR_POSTMORTEM_TEAM_DETAIL_VO);
			
			if(fb.getTeamMember()!=null)
			{
				PostmortemTeamDetailVO postmortemTeamVO=new PostmortemTeamDetailVO();
				postmortemTeamVO.setEmpId(fb.getTeamMember());
				postmortemTeamVO.setRoleId(fb.getRole());
				postmortemTeamVO.setPostmortemId(fb.getPostmortemId());
				postmortemTeamVO.setIsIncharge(MortuaryConfig.POSTMORTEM_INCHARGE_NO);
				postmortemTeamVO.setIsPerformed(MortuaryConfig.INCHARGE_IS_PERFORMED_YES);
				
				lstTeamAdd.add(postmortemTeamVO);
			}
			if(teamDtlVO!=null)
			{
				int len=teamDtlVO.length;
				for(int i=0;i<len;i++)
				{
					PostmortemTeamDetailVO postmortemTeamVO=new PostmortemTeamDetailVO();
					postmortemTeamVO.setEmpId(teamDtlVO[i].getEmpId());
					postmortemTeamVO.setRoleId(teamDtlVO[i].getRoleId());
					postmortemTeamVO.setPostmortemId(fb.getPostmortemId());
					postmortemTeamVO.setIsIncharge(MortuaryConfig.POSTMORTEM_INCHARGE_NO);
					postmortemTeamVO.setIsPerformed(MortuaryConfig.INCHARGE_IS_PERFORMED_YES);
					
					lstTeamAdd.add(postmortemTeamVO);
				}
			}
			
			//////////InCharge Detail Modification//////////
			lstTeamModify=new ArrayList<PostmortemTeamDetailVO>();
			addedTeamDtlVO=(PostmortemTeamDetailVO[])session.getAttribute(MortuaryConfig.ARR_ADDED_TEAM_MEMBER_VO);
			
			for(int i=0;i<addedTeamDtlVO.length;i++)
			{
				if(addedTeamDtlVO[i].getIsPerformed().equals(MortuaryConfig.INCHARGE_IS_PERFORMED_ASSIGN))
				{
					PostmortemTeamDetailVO postmortemTeamVO=new PostmortemTeamDetailVO();
					postmortemTeamVO.setPostmortemId(fb.getPostmortemId());
					postmortemTeamVO.setSrNO(addedTeamDtlVO[i].getSrNO());
					postmortemTeamVO.setIsPerformed(fb.getNotPerformedValue()[i]) ;
					postmortemTeamVO.setReason(fb.getReason()[i]);
					postmortemTeamVO.setEmpId(addedTeamDtlVO[i].getEmpId());
					
					lstTeamModify.add(postmortemTeamVO);
				}
			}
			
			PostmortemTeamDetailDATA.saveTeamDetail(lstTeamModify,lstTeamAdd,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Added Successfully");
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
	}
}
