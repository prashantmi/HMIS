package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.PostmortemRequestAcceptanceDATA;
import mortuary.transaction.controller.fb.PostmortemRequestAcceptanceFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.PostmortemItemReqDtlVO;
import hisglobal.vo.PostmortemOpinionReqDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.PostmortemTeamDetailVO;

public class PostmortemRequestAcceptanceUTL extends ControllerUTIL
{
	public static void getPostmortemReqList(PostmortemRequestAcceptanceFB fb, HttpServletRequest request )
	{
		Status objStatus=new Status();
		PostmortemRequestDetailVO[] postmortemReqVO=null;
		
		try
		{
			setSysdate(request);
			postmortemReqVO=PostmortemRequestAcceptanceDATA.getPostmortemReqList(getUserVO(request));
			if(postmortemReqVO!=null)
			{
				for(int i=0;i<postmortemReqVO.length;i++)
				{
					if(postmortemReqVO[i].getPostmortemType().equals(MortuaryConfig.POSTMORTEM_TYPE_FORENSIC))
						postmortemReqVO[i].setColor(MortuaryConfig.DECEASED_COLOR_MLC);
					else
						postmortemReqVO[i].setColor(MortuaryConfig.DECEASED_COLOR_NORMAL);
				}
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_POSTMORTM_REQUEST_LIST_VO,postmortemReqVO);
				objStatus.add(Status.LIST);
			}
			else
				throw new HisRecordNotFoundException("No Record Found");
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
	}
	
	public static void getPostmortemRequestDetail(PostmortemRequestAcceptanceFB fb, HttpServletRequest request )
	{
		Status objStatus=new Status();
		PostmortemRequestDetailVO postmortemReqVO=new PostmortemRequestDetailVO();
		DeceasedRelativeDtlVO relativeVO=new DeceasedRelativeDtlVO(); 
		
		try
		{
			postmortemReqVO=PostmortemRequestAcceptanceDATA.getPostmortemRequestDetail(fb.getPostmortemId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.POSTMORTEM_REQUEST_DETAIL_VO, postmortemReqVO);
			fb.setAutopsyType(postmortemReqVO.getAutopsyType());
			fb.setConductedBy(postmortemReqVO.getConductBy());
			fb.setApprovedBy(postmortemReqVO.getApprovedBy());
			fb.setConsentStatus(postmortemReqVO.getConsentStatus());
			
			Map map=PostmortemRequestAcceptanceDATA.getEssentialForPostmortemReqApproved(fb.getDeceasedNo(),postmortemReqVO.getConsentStatus(),fb.getPostmortemId(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			if(postmortemReqVO.getConsentStatus().equals(MortuaryConfig.CONSENT_RAISED))
			{
				relativeVO=(DeceasedRelativeDtlVO)map.get(MortuaryConfig.PM_REQUESTED_RELATIVE_DETAIL_VO);
				HelperMethods.populatetToNullOrEmpty(fb, relativeVO);
			}
			
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
	}
	
	public static void addOpinionRow(PostmortemRequestAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemOpinionReqDtlVO[] opinionReqVO1=null;
		PostmortemOpinionReqDtlVO[] opinionReqVO2=null;
		PostmortemOpinionReqDtlVO[] opinionReqVO3=null;
		List lstOpinion=null;
		String opinionName="";
		
		try
		{
			lstOpinion=(List)session.getAttribute(MortuaryConfig.OPINION_LIST_NOT_REQUESTED);
			for(int i=0;i<lstOpinion.size();i++)
			{
				Entry ent=(Entry)lstOpinion.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getRequestedOpinion()))
			    {
			    	opinionName=ent.getLabel();
			    	break;
			    }
			}
			
			
			List newList = new ArrayList(lstOpinion);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getRequestedOpinion()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.OPINION_LIST_NOT_REQUESTED,newList);
			
			opinionReqVO1=(PostmortemOpinionReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_OPINION_REQUEST_VO);
			
			if(opinionReqVO1==null)
			{
				opinionReqVO2=new PostmortemOpinionReqDtlVO[1];
				opinionReqVO2[0]=new PostmortemOpinionReqDtlVO();
				opinionReqVO2[0].setOpinionCode(fb.getRequestedOpinion());
				opinionReqVO2[0].setOpinionName(opinionName);
				opinionReqVO2[0].setRemarks(fb.getOpinionRemark());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_OPINION_REQUEST_VO, opinionReqVO2);
			}
			else
			{
				opinionReqVO3=new PostmortemOpinionReqDtlVO[opinionReqVO1.length+1];
				int i=0;
				for(;i<opinionReqVO1.length;i++)
				{
					opinionReqVO3[i]=opinionReqVO1[i];
				}
				opinionReqVO3[i]=new PostmortemOpinionReqDtlVO();
				opinionReqVO3[i].setOpinionCode(fb.getRequestedOpinion());
				opinionReqVO3[i].setOpinionName(opinionName);
				opinionReqVO3[i].setRemarks(fb.getOpinionRemark());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_OPINION_REQUEST_VO, opinionReqVO3);
			}
			
			if(session.getAttribute(MortuaryConfig.ARR_OPINION_REQUEST_VO)!=null)
				fb.setAddOpinionFlag(MortuaryConfig.YES);
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
	
	public static void deleteOpinionRow(PostmortemRequestAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemOpinionReqDtlVO[] opinionReqVO1=null;
		PostmortemOpinionReqDtlVO[] opinionReqVO2=null;
		
		try
		{
			List lstOpinion=(List)session.getAttribute(MortuaryConfig.OPINION_LIST_NOT_REQUESTED);
			List newList = new ArrayList(lstOpinion);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenOpinionCode(),fb.getHiddenOpinionName()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.OPINION_LIST_NOT_REQUESTED,newList);
			
			opinionReqVO1=(PostmortemOpinionReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_OPINION_REQUEST_VO);
			opinionReqVO2=new PostmortemOpinionReqDtlVO[opinionReqVO1.length-1];
			
			int j=0;
			for(int i=0;i<opinionReqVO1.length;i++)
			{
				if(!fb.getHiddenOpinionCode().equals(opinionReqVO1[i].getOpinionCode()))
				{
					opinionReqVO2[j]=opinionReqVO1[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_OPINION_REQUEST_VO, opinionReqVO2);
			
			if(opinionReqVO2.length==0)
				fb.setAddOpinionFlag(MortuaryConfig.NO);
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
	
	public static void addItemRow(PostmortemRequestAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemItemReqDtlVO[] itemReqVO1=null;
		PostmortemItemReqDtlVO[] itemReqVO2=null;
		PostmortemItemReqDtlVO[] itemReqVO3=null;
		List lstItem=null;
		String itemName="";
		
		try
		{
			lstItem=(List)session.getAttribute(MortuaryConfig.ITEM_LIST_NOT_REQUESTED);
			for(int i=0;i<lstItem.size();i++)
			{
				Entry ent=(Entry)lstItem.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getRequestedItem()))
			    {
			    	itemName=ent.getLabel();
			    	break;
			    }
			}
			
			
			List newList = new ArrayList(lstItem);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getRequestedItem()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ITEM_LIST_NOT_REQUESTED,newList);
			
			itemReqVO1=(PostmortemItemReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_ITEM_REQUEST_VO);
			
			if(itemReqVO1==null)
			{
				itemReqVO2=new PostmortemItemReqDtlVO[1];
				itemReqVO2[0]=new PostmortemItemReqDtlVO();
				itemReqVO2[0].setItemCode(fb.getRequestedItem());
				itemReqVO2[0].setItemName(itemName);
				itemReqVO2[0].setRemarks(fb.getItemRemark());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_ITEM_REQUEST_VO, itemReqVO2);
			}
			else
			{
				itemReqVO3=new PostmortemItemReqDtlVO[itemReqVO1.length+1];
				int i=0;
				for(;i<itemReqVO1.length;i++)
				{
					itemReqVO3[i]=itemReqVO1[i];
				}
				itemReqVO3[i]=new PostmortemItemReqDtlVO();
				itemReqVO3[i].setItemCode(fb.getRequestedItem());
				itemReqVO3[i].setItemName(itemName);
				itemReqVO3[i].setRemarks(fb.getItemRemark());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_ITEM_REQUEST_VO, itemReqVO3);
			}
			
			if(session.getAttribute(MortuaryConfig.ARR_ITEM_REQUEST_VO)!=null)
				fb.setAddItemFlag(MortuaryConfig.YES);
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
	
	public static void deleteItemRow(PostmortemRequestAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemItemReqDtlVO[] itemReqVO1=null;
		PostmortemItemReqDtlVO[] itemReqVO2=null;
		
		try
		{
			List lstItem=(List)session.getAttribute(MortuaryConfig.ITEM_LIST_NOT_REQUESTED);
			List newList = new ArrayList(lstItem);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenItemCode(),fb.getHiddenItemName()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ITEM_LIST_NOT_REQUESTED,newList);
			
			itemReqVO1=(PostmortemItemReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_ITEM_REQUEST_VO);
			itemReqVO2=new PostmortemItemReqDtlVO[itemReqVO1.length-1];
			
			int j=0;
			for(int i=0;i<itemReqVO1.length;i++)
			{
				if(!fb.getHiddenItemCode().equals(itemReqVO1[i].getItemCode()))
				{
					itemReqVO2[j]=itemReqVO1[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_ITEM_REQUEST_VO, itemReqVO2);
			
			if(itemReqVO2.length==0)
				fb.setAddItemFlag(MortuaryConfig.NO);
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
	
	public static void addPanelInchargeRow(PostmortemRequestAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemTeamDetailVO[] teamVO1=null;
		PostmortemTeamDetailVO[] teamVO2=null;
		PostmortemTeamDetailVO[] teamVO3=null;
		List lstIncharge=null;
		String empName="";
		List lstRole=null;
		String roleName="";
		
		try
		{
			lstIncharge=(List)session.getAttribute(MortuaryConfig.EMPLOYEE_INCHARGE_LIST);
			for(int i=0;i<lstIncharge.size();i++)
			{
				Entry ent=(Entry)lstIncharge.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getEmployeeIncharge()))
			    {
			    	empName=ent.getLabel();
			    	break;
			    }
			}
			
			lstRole=(List)session.getAttribute(MortuaryConfig.POSTMORTEM_INCHARGE_ROLE_LIST);
			for(int i=0;i<lstRole.size();i++)
			{
				Entry ent=(Entry)lstRole.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getRoleId()))
			    {
			    	roleName=ent.getLabel();
			    	break;
			    }
			}
			
			List newList = new ArrayList(lstIncharge);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getEmployeeIncharge()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.EMPLOYEE_INCHARGE_LIST,newList);
			
			teamVO1=(PostmortemTeamDetailVO[])session.getAttribute(MortuaryConfig.ARR_TEAM_INCHARGE_VO);
			
			if(teamVO1==null)
			{
				teamVO2=new PostmortemTeamDetailVO[1];
				teamVO2[0]=new PostmortemTeamDetailVO();
				teamVO2[0].setEmpId(fb.getEmployeeIncharge());
				teamVO2[0].setEmpName(empName);
				teamVO2[0].setRoleId(fb.getRoleId());
				teamVO2[0].setRoleName(roleName);
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_TEAM_INCHARGE_VO, teamVO2);
			}
			else
			{
				teamVO3=new PostmortemTeamDetailVO[teamVO1.length+1];
				int i=0;
				for(;i<teamVO1.length;i++)
				{
					teamVO3[i]=teamVO1[i];
				}
				teamVO3[i]=new PostmortemTeamDetailVO();
				teamVO3[i].setEmpId(fb.getEmployeeIncharge());
				teamVO3[i].setEmpName(empName);
				teamVO3[i].setRoleId(fb.getRoleId());
				teamVO3[i].setRoleName(roleName);
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_TEAM_INCHARGE_VO, teamVO3);
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
	
	public static void deletePanelInchargeRow(PostmortemRequestAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemTeamDetailVO[] teamVO1=null;
		PostmortemTeamDetailVO[] teamVO2=null;
		
		try
		{
			List lstItem=(List)session.getAttribute(MortuaryConfig.EMPLOYEE_INCHARGE_LIST);
			List newList = new ArrayList(lstItem);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenInchargeId(),fb.getHiddenInchargeName()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.EMPLOYEE_INCHARGE_LIST,newList);
			
			teamVO1=(PostmortemTeamDetailVO[])session.getAttribute(MortuaryConfig.ARR_TEAM_INCHARGE_VO);
			teamVO2=new PostmortemTeamDetailVO[teamVO1.length-1];
			
			int j=0;
			for(int i=0;i<teamVO1.length;i++)
			{
				if(!fb.getHiddenInchargeId().equals(teamVO1[i].getEmpId()))
				{
					teamVO2[j]=teamVO1[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_TEAM_INCHARGE_VO, teamVO2);
			
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
	
	public static void approvedPostmortemRequest(PostmortemRequestAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemItemReqDtlVO[] arrItemReqVO=null;
		PostmortemOpinionReqDtlVO[] arrOpinionReqVO=null;
		PostmortemTeamDetailVO[] arrTeamDtlVO=null;
		PostmortemRequestDetailVO postmortemReqVO=new PostmortemRequestDetailVO();
		List<PostmortemOpinionReqDtlVO> lstOpinionAdd=new ArrayList<PostmortemOpinionReqDtlVO>();
		List<PostmortemOpinionReqDtlVO> lstOpinionRevoke=new ArrayList<PostmortemOpinionReqDtlVO>();
		List<PostmortemItemReqDtlVO> lstItemAdd=new ArrayList<PostmortemItemReqDtlVO>();
		List<PostmortemItemReqDtlVO> lstItemRevoke=new ArrayList<PostmortemItemReqDtlVO>();
		List<PostmortemTeamDetailVO> lstTeam=new ArrayList<PostmortemTeamDetailVO>();
		

		try
		{
			PostmortemRequestDetailVO postmortemReqDtlVO=(PostmortemRequestDetailVO)session.getAttribute(MortuaryConfig.POSTMORTEM_REQUEST_DETAIL_VO);
			HelperMethods.populate(postmortemReqVO, postmortemReqDtlVO);
			postmortemReqVO.setConductBy(fb.getConductedBy());
			postmortemReqVO.setApprovedBy(fb.getApprovedBy());
			postmortemReqVO.setAutopsyType(fb.getAutopsyType());
			postmortemReqVO.setPostmortemStatus(MortuaryConfig.POSTMORTEM_STATUS_REQUEST_APPROVED);
			
			postmortemReqVO.setConsentStatus(MortuaryConfig.CONSENT_RECEIVED);
			postmortemReqVO.setRelativeName(fb.getRelativeName());
			postmortemReqVO.setRelativeAddress(fb.getRelativeAddress());
			postmortemReqVO.setRelativeCode(fb.getRelativeCode());
			postmortemReqVO.setRelativeContactNo(fb.getRelativeContactNo());
			
	/////////For Opinion
			arrOpinionReqVO=(PostmortemOpinionReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_OPINION_REQUEST_VO);
			
			if(fb.getRevokeOpinion()!=null)
			{
				for(int i=0;i<fb.getRevokeOpinion().length;i++)
				{
					PostmortemOpinionReqDtlVO opinionReqVO=new PostmortemOpinionReqDtlVO();
					opinionReqVO.setOpinionCode(fb.getRevokeOpinion()[i]);
					opinionReqVO.setPostmortemId(fb.getPostmortemId());
					lstOpinionRevoke.add(opinionReqVO);
				}
			}
			
			if(fb.getRequestedOpinion()!=null && !fb.getRequestedOpinion().equals("-1"))
			{
				PostmortemOpinionReqDtlVO opinionReqVO=new PostmortemOpinionReqDtlVO();
				opinionReqVO.setOpinionCode(fb.getRequestedOpinion());
				opinionReqVO.setRemarks(fb.getOpinionRemark());
				opinionReqVO.setPostmortemId(fb.getPostmortemId());
				lstOpinionAdd.add(opinionReqVO);
			}
			
			if(arrOpinionReqVO!=null)
			{
				for(int i=0;i<arrOpinionReqVO.length;i++)
				{
					PostmortemOpinionReqDtlVO opinionReqVO=new PostmortemOpinionReqDtlVO();
					opinionReqVO.setOpinionCode(arrOpinionReqVO[i].getOpinionCode());
					opinionReqVO.setRemarks(arrOpinionReqVO[i].getRemarks());
					opinionReqVO.setPostmortemId(fb.getPostmortemId());
					lstOpinionAdd.add(opinionReqVO);
				}
			}
	/////////For Item
			arrItemReqVO=(PostmortemItemReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_ITEM_REQUEST_VO);
			
			if(fb.getRevokeItem()!=null)
			{
				for(int i=0;i<fb.getRevokeItem().length;i++)
				{
					PostmortemItemReqDtlVO itemReqVO=new PostmortemItemReqDtlVO();
					itemReqVO.setItemCode(fb.getRevokeItem()[i]);
					itemReqVO.setPostmortemId(fb.getPostmortemId());
					lstItemRevoke.add(itemReqVO);
				}
			}
			
			if(!fb.getRequestedItem().equals("-1"))
			{
				PostmortemItemReqDtlVO itemReqVO=new PostmortemItemReqDtlVO();
				itemReqVO.setItemCode(fb.getRequestedItem());
				itemReqVO.setRemarks(fb.getItemRemark());
				itemReqVO.setPostmortemId(fb.getPostmortemId());
				lstItemAdd.add(itemReqVO);
			}
			
			if(arrItemReqVO!=null)
			{
				for(int i=0;i<arrItemReqVO.length;i++)
				{
					PostmortemItemReqDtlVO itemReqVO=new PostmortemItemReqDtlVO();
					itemReqVO.setItemCode(arrItemReqVO[i].getItemCode());
					itemReqVO.setRemarks(arrItemReqVO[i].getRemarks());
					itemReqVO.setPostmortemId(fb.getPostmortemId());
					lstItemAdd.add(itemReqVO);
				}
			}
			
	///////For Team Detail
			if(fb.getConductedBy().equals(MortuaryConfig.POSTMORTEM_CONDUCTED_BY_TEAM))
			{
				PostmortemTeamDetailVO teamDetailVO=new PostmortemTeamDetailVO();
				teamDetailVO.setEmpId(fb.getTeamEmployeeIncharge());
				teamDetailVO.setRoleId(MortuaryConfig.DEFAULT_ROLE_ID);
				teamDetailVO.setIsIncharge(MortuaryConfig.POSTMORTEM_INCHARGE_YES);
				teamDetailVO.setPostmortemId(fb.getPostmortemId());
				teamDetailVO.setIsPerformed(MortuaryConfig.INCHARGE_IS_PERFORMED_ASSIGN);
				lstTeam.add(teamDetailVO);
			}
			else
			{
				arrTeamDtlVO=(PostmortemTeamDetailVO[])session.getAttribute(MortuaryConfig.ARR_TEAM_INCHARGE_VO);
				
				if(fb.getEmployeeIncharge()!="-1")
				{
					PostmortemTeamDetailVO teamDetailVO=new PostmortemTeamDetailVO();
					teamDetailVO.setEmpId(fb.getEmployeeIncharge());
					teamDetailVO.setRoleId(fb.getRoleId());
					teamDetailVO.setIsIncharge(MortuaryConfig.POSTMORTEM_INCHARGE_YES);
					teamDetailVO.setPostmortemId(fb.getPostmortemId());
					teamDetailVO.setIsPerformed(MortuaryConfig.INCHARGE_IS_PERFORMED_ASSIGN);
					lstTeam.add(teamDetailVO);
				}
				
				if(arrTeamDtlVO!=null)
				{
					for(int i=0;i<arrTeamDtlVO.length;i++)
					{
						PostmortemTeamDetailVO teamDetailVO=new PostmortemTeamDetailVO();
						teamDetailVO.setEmpId(arrTeamDtlVO[i].getEmpId());
						teamDetailVO.setRoleId(arrTeamDtlVO[i].getRoleId());
						teamDetailVO.setIsIncharge(MortuaryConfig.POSTMORTEM_INCHARGE_YES);
						teamDetailVO.setPostmortemId(fb.getPostmortemId());
						teamDetailVO.setIsPerformed(MortuaryConfig.INCHARGE_IS_PERFORMED_ASSIGN);
						lstTeam.add(teamDetailVO);
					}
				}
			}
			
			PostmortemRequestAcceptanceDATA.approvedPostmortemRequest(postmortemReqVO,lstItemAdd,lstItemRevoke,lstOpinionAdd,lstOpinionRevoke,lstTeam,getUserVO(request));
			objStatus.add(Status.DONE,"","Postmortem Request Accepted");
			
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
	
	public static void cancelledPostmortemRequest(PostmortemRequestAcceptanceFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PostmortemRequestDetailVO postmortemReqVO=new PostmortemRequestDetailVO();
		
		try
		{
			PostmortemRequestDetailVO postmortemReqDtlVO=(PostmortemRequestDetailVO)session.getAttribute(MortuaryConfig.POSTMORTEM_REQUEST_DETAIL_VO);
			HelperMethods.populate(postmortemReqVO, postmortemReqDtlVO);
			postmortemReqVO.setPostmortemStatus(MortuaryConfig.POSTMORTEM_STATUS_REQUEST_CANCELED);
			postmortemReqVO.setCancelReason(fb.getCancelReason());
			
			PostmortemRequestAcceptanceDATA.cancelledPostmortemRequest(postmortemReqVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Postmortem Request Rejected");
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
}
