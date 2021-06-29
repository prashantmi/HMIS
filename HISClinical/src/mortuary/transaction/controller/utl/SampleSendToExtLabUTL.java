package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.SampleSendToExtLabDATA;
import mortuary.transaction.controller.fb.SampleSendToExtLabFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.MortuaryExtLabInvReqDtlVO;
import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.MortuaryExtReqSampleDtlVO;

public class SampleSendToExtLabUTL extends ControllerUTIL
{
	public static void getEssentialForSampleSend(SampleSendToExtLabFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String deceasedNo="";
		String postmortemType="";
		
		try
		{
			setSysdate(request);
			DeceasedDetailVO[] deceasedVO=(DeceasedDetailVO[])session.getAttribute(DynamicDeskConfig.POSTMORTEM_DESK_DECEASED_LIST);
			for(int i=0;i<deceasedVO.length;i++)
			{
				if(fb.getPostmortemId().equalsIgnoreCase(deceasedVO[i].getPostmortemId()))
				{
					deceasedNo=deceasedVO[i].getDeceasedNo();
					postmortemType=deceasedVO[i].getPostmortemType();
					break;
				}	
			}
			fb.setDeceasedNo(deceasedNo);
			fb.setPostmortemType(postmortemType);
			
			Map map=SampleSendToExtLabDATA.getEssentialForSampleSend(fb.getPostmortemId(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			
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
	
	public static void addRow(SampleSendToExtLabFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		MortuaryExtLabInvReqDtlVO[] extLabInvReqDtlVO1=null;
		MortuaryExtLabInvReqDtlVO[] extLabInvReqDtlVO2=null;
		MortuaryExtLabInvReqDtlVO[] extLabInvReqDtlVO3=null;
		List lstLabTest=null;
		String labTestName="";
		String str="";
		
		try
		{
			if(fb.getSelectedItem()!=null)
			{
				for(int i=0;i<fb.getSelectedItem().length;i++)
				{
					str=str+fb.getSelectedItem()[i]+"@";
				}
				str=str.substring(0,str.length()-1);
				fb.setCheckedItem(str);
			}
			
			lstLabTest=(List)session.getAttribute(MortuaryConfig.ESSENTIAL_ALL_EXTERNAL_LAB_TEST_LIST);
			for(int i=0;i<lstLabTest.size();i++)
			{
				Entry ent=(Entry)lstLabTest.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getLabTestId()))
			    {
			    	labTestName=ent.getLabel();
			    	break;
			    }
			}
			
			List newList = new ArrayList(lstLabTest);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getLabTestId()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ESSENTIAL_ALL_EXTERNAL_LAB_TEST_LIST,newList);
			
			extLabInvReqDtlVO1=(MortuaryExtLabInvReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_ADDED_INVESTTIGATION_VO);
			
			if(extLabInvReqDtlVO1 == null)
			{
				extLabInvReqDtlVO2=new MortuaryExtLabInvReqDtlVO[1];
				extLabInvReqDtlVO2[0]=new MortuaryExtLabInvReqDtlVO();
				extLabInvReqDtlVO2[0].setLabTestId(fb.getLabTestId());
				extLabInvReqDtlVO2[0].setLabTestName(labTestName);
				extLabInvReqDtlVO2[0].setLabTestRemrks(fb.getLabTestRemrks());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_ADDED_INVESTTIGATION_VO, extLabInvReqDtlVO2);
			}
			else
			{
				extLabInvReqDtlVO3=new MortuaryExtLabInvReqDtlVO[extLabInvReqDtlVO1.length+1];
				int i=0;
				for(;i<extLabInvReqDtlVO1.length;i++)
				{
					extLabInvReqDtlVO3[i]=extLabInvReqDtlVO1[i];
				}
				extLabInvReqDtlVO3[i]=new MortuaryExtLabInvReqDtlVO();
				extLabInvReqDtlVO3[i].setLabTestId(fb.getLabTestId());
				extLabInvReqDtlVO3[i].setLabTestName(labTestName);
				extLabInvReqDtlVO3[i].setLabTestRemrks(fb.getLabTestRemrks());
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_ADDED_INVESTTIGATION_VO, extLabInvReqDtlVO3);
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
	
	public static void deleteRow(SampleSendToExtLabFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		MortuaryExtLabInvReqDtlVO[] extLabInvReqDtlVO1=null;
		MortuaryExtLabInvReqDtlVO[] extLabInvReqDtlVO2=null;
		String str="";
		
		try
		{
			if(fb.getSelectedItem()!=null)
			{
				for(int i=0;i<fb.getSelectedItem().length;i++)
				{
					str=str+fb.getSelectedItem()[i]+"@";
				}
				str=str.substring(0,str.length()-1);
				
			}	
			
		
			
			List lstItem=(List)session.getAttribute(MortuaryConfig.ESSENTIAL_ALL_EXTERNAL_LAB_TEST_LIST);
			List newList = new ArrayList(lstItem);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenLabTestId(),fb.getHiddenLabTestName()); 
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ESSENTIAL_ALL_EXTERNAL_LAB_TEST_LIST,newList);
			
			
			extLabInvReqDtlVO1=(MortuaryExtLabInvReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_ADDED_INVESTTIGATION_VO);
			extLabInvReqDtlVO2=new MortuaryExtLabInvReqDtlVO[extLabInvReqDtlVO1.length-1];
			
			int j=0;
			for(int i=0;i<extLabInvReqDtlVO1.length;i++)
			{
				if(!fb.getHiddenLabTestId().equals(extLabInvReqDtlVO1[i].getLabTestId()))
				{
					extLabInvReqDtlVO2[j]=extLabInvReqDtlVO1[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_ADDED_INVESTTIGATION_VO, extLabInvReqDtlVO2);
			
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
	
	public static void saveSampleSendDetail(SampleSendToExtLabFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		MortuaryExtLabInvReqDtlVO[] addedExtLabInvReqDtlVO=null;
		MortuaryExtLabRequestDtlVO extLabReqDtlVO=new MortuaryExtLabRequestDtlVO();
		List<MortuaryExtLabInvReqDtlVO> lstInvestigation=new ArrayList<MortuaryExtLabInvReqDtlVO>();
		List<MortuaryExtReqSampleDtlVO> lstSample=new ArrayList<MortuaryExtReqSampleDtlVO>();
		
		
		try
		{
			/*DeceasedDetailVO[] deceasedVO=(DeceasedDetailVO[])session.getAttribute(DynamicDeskConfig.POSTMORTEM_DESK_DECEASED_LIST);
			for(int i=0;i<deceasedVO.length;i++)
			{
				if(fb.getPostmortemId().equalsIgnoreCase(deceasedVO[i].getPostmortemId()))
				{
					srNo=deceasedVO[i].getSrNo();
					break;
				}	
			}*/
			/*if(srNo.length()==1)
				srNo="0"+srNo;
			
			requestId=fb.getPostmortemId()+srNo;*/
			
			HelperMethods.populate(extLabReqDtlVO,fb);
			
			extLabReqDtlVO.setStatus(MortuaryConfig.EXTERNAL_LAB_REQUESTE_STATUS_REQUEST_SEND);
			if(!fb.getLabTestId().equals("-1"))
			{
				MortuaryExtLabInvReqDtlVO extLabInvReqDtlVO=new MortuaryExtLabInvReqDtlVO();
				extLabInvReqDtlVO.setLabTestId(fb.getLabTestId());
				extLabInvReqDtlVO.setLabTestRemrks(fb.getLabTestRemrks());
		//		extLabInvReqDtlVO.setRequestId(requestId);
				
				lstInvestigation.add(extLabInvReqDtlVO);
			}
			
			addedExtLabInvReqDtlVO=(MortuaryExtLabInvReqDtlVO[])session.getAttribute(MortuaryConfig.ARR_ADDED_INVESTTIGATION_VO);
			if(addedExtLabInvReqDtlVO!=null)
			{
				for(int i=0;i<addedExtLabInvReqDtlVO.length;i++)
				{
					MortuaryExtLabInvReqDtlVO extLabInvReqDtlVO=new MortuaryExtLabInvReqDtlVO();
					extLabInvReqDtlVO.setLabTestId(addedExtLabInvReqDtlVO[i].getLabTestId());
					extLabInvReqDtlVO.setLabTestRemrks(addedExtLabInvReqDtlVO[i].getLabTestRemrks());
			//		extLabInvReqDtlVO.setRequestId(requestId);
					
					lstInvestigation.add(extLabInvReqDtlVO);
				}
			}
			
			for(int i=0;i<fb.getSelectedItem().length;i++)
			{
				MortuaryExtReqSampleDtlVO extReqSampleDtlVO=new MortuaryExtReqSampleDtlVO();
				extReqSampleDtlVO.setItemCode(fb.getSelectedItem()[i]);
				extReqSampleDtlVO.setStatus(MortuaryConfig.EXTERNAL_REQUEST_SAMPLE_STATUS_SEND);
		//		extReqSampleDtlVO.setRequestId(requestId);
				lstSample.add(extReqSampleDtlVO);
				
			}
			
			SampleSendToExtLabDATA.saveSampleSendDetail(extLabReqDtlVO,lstSample,lstInvestigation,getUserVO(request));
			objStatus.add(Status.DONE,"","Sample Send Successfully");
			
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
