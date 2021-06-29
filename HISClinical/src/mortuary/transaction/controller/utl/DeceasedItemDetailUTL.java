package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.DeceasedItemDetailDATA;
import mortuary.transaction.controller.fb.DeceasedItemDetailFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.PostmortemItemDtlVO;
import hisglobal.vo.PostmortemItemReqDtlVO;

public class DeceasedItemDetailUTL extends ControllerUTIL
{
	public static void getItemToBePreserved(DeceasedItemDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PostmortemItemReqDtlVO[] itemReqVO=null;
		String deceasedNo="";
		String postmortemId="";
		
		try
		{
			DeceasedDetailVO[] deceasedVO=(DeceasedDetailVO[])session.getAttribute(DynamicDeskConfig.POSTMORTEM_DESK_DECEASED_LIST);
			for(int i=0;i<deceasedVO.length;i++)
			{
				if(fb.getPostmortemId().equalsIgnoreCase(deceasedVO[i].getPostmortemId()))
				{
					deceasedNo=deceasedVO[i].getDeceasedNo();
					break;
				}	
			}
			fb.setDeceasedNo(deceasedNo);
			postmortemId=fb.getPostmortemId();
			DeceasedTileUTL.getDeceasedDetailByDeceasedNo(fb, request);
			fb.setPostmortemId(postmortemId);
			
			Map map=DeceasedItemDetailDATA.getPreservativeList(fb.getPostmortemId(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			itemReqVO=DeceasedItemDetailDATA.getItemToBePreserved(fb.getPostmortemId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_DECEASED_ITEM_TOBE_PRESERVED, itemReqVO);
			List lstItem=(List)map.get(MortuaryConfig.ITEM_NOT_REQUESTED_N_PRESERVED_LIST);
			
			if(itemReqVO!=null)
			{
				objStatus.add(Status.TRANSINPROCESS);
				fb.setItemFoundFlag(MortuaryConfig.YES);
			}	
			else
			{
				fb.setItemFoundFlag(MortuaryConfig.NO);
				if(lstItem.size()>0)
					objStatus.add(Status.TRANSINPROCESS,"","No Requested Item Found To Be Preserved");
				else
					objStatus.add(Status.UNSUCESSFULL,"","No Requested Item Found To Be Preserved");
			}	
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
	
	public static void saveItemToBePreserved(DeceasedItemDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PostmortemItemDtlVO[] postmortemItemDtlVO=null;
		List<PostmortemItemDtlVO> lstItem=new ArrayList<PostmortemItemDtlVO>();
		int j=0;
		
		try
		{
			if(fb.getItemCode()!=null)
			{
				for(int i=0;i<fb.getItemCode().length;i++)
				{
					PostmortemItemDtlVO itemDtlVO=new PostmortemItemDtlVO();
					itemDtlVO.setPostmortemId(fb.getPostmortemId());
					itemDtlVO.setItemCode(fb.getItemCode()[i]);
					itemDtlVO.setRemarks(fb.getRemarks()[i]);
					itemDtlVO.setStatus(MortuaryConfig.DECEASED_ITEM_STATUS_MORTUARY);
					if(fb.getIsPreservedValue()[i].equals("1"))
					{
						itemDtlVO.setIsPreserved(fb.getIsPreservedValue()[i]);
						itemDtlVO.setPreservativeId(fb.getPreservativeId()[j]);
						j++;
					}
					else
					{
						itemDtlVO.setIsPreserved(fb.getIsPreservedValue()[i]);
					}
					
					lstItem.add(itemDtlVO);
				}
			}
			if(fb.getExtraItemCode()!=null && !fb.getExtraItemCode().equals("-1") && !fb.getExtraItemCode().equals(""))
			{
				PostmortemItemDtlVO itemDtlVO=new PostmortemItemDtlVO();
				itemDtlVO.setPostmortemId(fb.getPostmortemId());
				itemDtlVO.setItemCode(fb.getExtraItemCode());
				itemDtlVO.setRemarks(fb.getExtraRemarks());
				itemDtlVO.setStatus(MortuaryConfig.DECEASED_ITEM_STATUS_MORTUARY);
				if(fb.getExtraIsPreservedValue().equals("1"))
				{
					itemDtlVO.setIsPreserved(fb.getExtraIsPreservedValue());
					itemDtlVO.setPreservativeId(fb.getExtraPreservativeId());
				}
				else
				{
					itemDtlVO.setIsPreserved(fb.getExtraIsPreservedValue());
				}
				
				lstItem.add(itemDtlVO);
			}
			
			postmortemItemDtlVO=(PostmortemItemDtlVO[])session.getAttribute(MortuaryConfig.ARR_EXTRA_ITEM_ADDED_VO);
			if(postmortemItemDtlVO!=null)
			{
				for(int i=0;i<postmortemItemDtlVO.length;i++)
				{
					PostmortemItemDtlVO itemDtlVO=new PostmortemItemDtlVO();
					itemDtlVO.setPostmortemId(fb.getPostmortemId());
					itemDtlVO.setItemCode(postmortemItemDtlVO[i].getItemCode());
					itemDtlVO.setRemarks(postmortemItemDtlVO[i].getRemarks());
					itemDtlVO.setStatus(MortuaryConfig.DECEASED_ITEM_STATUS_MORTUARY);
					itemDtlVO.setIsPreserved(postmortemItemDtlVO[i].getIsPreserved());
					itemDtlVO.setPreservativeId(postmortemItemDtlVO[i].getPreservativeId());
					
					lstItem.add(itemDtlVO);
				}
			}
			
			DeceasedItemDetailDATA.saveItemToBePreserved(lstItem,getUserVO(request));
			objStatus.add(Status.UNSUCESSFULL,"","Record Added Successfully");
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
	
	public static void addRow(DeceasedItemDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PostmortemItemDtlVO[] postmortemItemDtlVO1=null;
		PostmortemItemDtlVO[] postmortemItemDtlVO2=null;
		PostmortemItemDtlVO[] postmortemItemDtlVO3=null;
		List lstItem=null;
		String itemName="";
		List lstPreservative=null;
		String preservativeName="-";
		String isPreservedLabel="";
		
		try
		{
			lstItem=(List)session.getAttribute(MortuaryConfig.ITEM_NOT_REQUESTED_N_PRESERVED_LIST);
			for(int i=0;i<lstItem.size();i++)
			{
				Entry ent=(Entry)lstItem.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getExtraItemCode()))
			    {
			    	itemName=ent.getLabel();
			    	break;
			    }
			}
			
			lstPreservative=(List)session.getAttribute(MortuaryConfig.ESSENTIAL_PRESERVATIVE_LIST);
			for(int i=0;i<lstPreservative.size();i++)
			{
				Entry ent=(Entry)lstPreservative.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getExtraPreservativeId()))
			    {
			    	preservativeName=ent.getLabel();
			    	break;
			    }
			}
			
			if(fb.getExtraIsPreservedValue().equals("1"))
				isPreservedLabel="Yes";
			else
				isPreservedLabel="No";
			
			List newList = new ArrayList(lstItem);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getExtraItemCode()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ITEM_NOT_REQUESTED_N_PRESERVED_LIST,newList);
			
			postmortemItemDtlVO1=(PostmortemItemDtlVO[])session.getAttribute(MortuaryConfig.ARR_EXTRA_ITEM_ADDED_VO);
			
			if(postmortemItemDtlVO1==null)
			{
				postmortemItemDtlVO2=new PostmortemItemDtlVO[1];
				postmortemItemDtlVO2[0]=new PostmortemItemDtlVO();
				postmortemItemDtlVO2[0].setItemCode(fb.getExtraItemCode());
				postmortemItemDtlVO2[0].setItemName(itemName);
				postmortemItemDtlVO2[0].setIsPreserved(fb.getExtraIsPreservedValue());
				postmortemItemDtlVO2[0].setIsPreservedLabel(isPreservedLabel);
				postmortemItemDtlVO2[0].setPreservativeId(fb.getExtraPreservativeId());
				postmortemItemDtlVO2[0].setPreservativeName(preservativeName);
				postmortemItemDtlVO2[0].setRemarks(fb.getExtraRemarks());
				
				WebUTIL.setAttributeInSession(request,MortuaryConfig.ARR_EXTRA_ITEM_ADDED_VO, postmortemItemDtlVO2);
			}
			else
			{
				postmortemItemDtlVO3=new PostmortemItemDtlVO[postmortemItemDtlVO1.length+1];
				int i=0;
				for(;i<postmortemItemDtlVO1.length;i++)
				{
					postmortemItemDtlVO3[i]=postmortemItemDtlVO1[i];
				}
				
				postmortemItemDtlVO3[i]=new PostmortemItemDtlVO();
				
				postmortemItemDtlVO3[i].setItemCode(fb.getExtraItemCode());
				postmortemItemDtlVO3[i].setItemName(itemName);
				postmortemItemDtlVO3[i].setIsPreserved(fb.getExtraIsPreservedValue());
				postmortemItemDtlVO3[i].setIsPreservedLabel(isPreservedLabel);
				postmortemItemDtlVO3[i].setPreservativeId(fb.getExtraPreservativeId());
				postmortemItemDtlVO3[i].setPreservativeName(preservativeName);
				postmortemItemDtlVO3[i].setRemarks(fb.getExtraRemarks());
				
				WebUTIL.setAttributeInSession(request,MortuaryConfig.ARR_EXTRA_ITEM_ADDED_VO, postmortemItemDtlVO3);
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
	
	public static void deleteRow(DeceasedItemDetailFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PostmortemItemDtlVO[] postmortemItemDtlVO1=null;
		PostmortemItemDtlVO[] postmortemItemDtlVO2=null;
		
		try
		{
			List lstItem=(List)session.getAttribute(MortuaryConfig.ITEM_NOT_REQUESTED_N_PRESERVED_LIST);
			List newList = new ArrayList(lstItem);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenExtraItemCode(),fb.getHiddenExtraItemName()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ITEM_NOT_REQUESTED_N_PRESERVED_LIST,newList);
			
			postmortemItemDtlVO1=(PostmortemItemDtlVO[])session.getAttribute(MortuaryConfig.ARR_EXTRA_ITEM_ADDED_VO);
			postmortemItemDtlVO2=new PostmortemItemDtlVO[postmortemItemDtlVO1.length-1];
			
			int j=0;
			for(int i=0;i<postmortemItemDtlVO1.length;i++)
			{
				if(!fb.getHiddenExtraItemCode().equals(postmortemItemDtlVO1[i].getItemCode()))
				{
					postmortemItemDtlVO2[j]=postmortemItemDtlVO1[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_EXTRA_ITEM_ADDED_VO, postmortemItemDtlVO2);
			
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
	
}
