package mortuary.transaction.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.data.SampleResultEntryDATA;
import mortuary.transaction.controller.fb.SampleResultEntryFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.MortuaryExtLabInvReqDtlVO;
import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.MortuaryExtReqSampleDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;

public class SampleResultEntryUTL extends ControllerUTIL
{
	public static void getAllSampleRequestDetail(SampleResultEntryFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		String deceasedNo="";
		
		try
		{
			Map map=SampleResultEntryDATA.getAllRequestedIdDetailNDeceasedNo(fb.getPostmortemId(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			deceasedNo=(String)map.get(MortuaryConfig.DECEASED_NO_BY_POSTMORTEM_ID);
			fb.setDeceasedNo(deceasedNo);
			
			MortuaryExtLabRequestDtlVO[] arrReqDtlVO=(MortuaryExtLabRequestDtlVO[])map.get(MortuaryConfig.ARR_RECEIVED_REPORT_BY_POSTMORTEM_NO_VO);
			
			if(arrReqDtlVO!=null)
			{
				Map<String, MortuaryExtLabRequestDtlVO> mapReqId =new HashMap<String, MortuaryExtLabRequestDtlVO>();
				Map<String, List<MortuaryExtLabRequestDtlVO>> mapLabTest =new HashMap<String, List<MortuaryExtLabRequestDtlVO>>();
				//List<MortuaryExtLabRequestDtlVO> lstExtLabTest=new ArrayList<MortuaryExtLabRequestDtlVO>();
				for(int i=0;i<arrReqDtlVO.length;i++)
				{
					mapReqId.put(arrReqDtlVO[i].getRequestId(), arrReqDtlVO[i]);
					
					List<MortuaryExtLabRequestDtlVO> lstExtLabTest = null;
					if(mapLabTest.get(arrReqDtlVO[i].getRequestId())==null)
						lstExtLabTest = new ArrayList<MortuaryExtLabRequestDtlVO>();
					else
						lstExtLabTest = mapLabTest.get(arrReqDtlVO[i].getRequestId());
					lstExtLabTest.add(arrReqDtlVO[i]);
					mapLabTest.put(arrReqDtlVO[i].getRequestId(), lstExtLabTest);
				}
				
				WebUTIL.setAttributeInSession(request, MortuaryConfig.MAP_REQUEST_ID_N_FINAL_RESULT_BY_POSTMORTEM_NO, mapReqId);
				WebUTIL.setAttributeInSession(request, MortuaryConfig.MAP_REQUEST_ID_N_LAB_TEST_BY_POSTMORTEM_NO, mapLabTest);
			}
			//DeceasedTileUTL.getDeceasedDetailByDeceasedNo(fb, request);
			MortuaryExtLabRequestDtlVO[] arrReqIdDtlVO=(MortuaryExtLabRequestDtlVO[])map.get(MortuaryConfig.ARR_REQUESTED_ID_BY_POSTMORTEM_NO_VO);
			if(arrReqIdDtlVO==null)
				objStatus.add(Status.LIST,"","No Sample Send To External Lab");
			else
				objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			fb.setPostmortemId("");
			objStatus.add(Status.DONE, e.getMessage(), "Invalid Postmortem No");
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
	
	public static void getAllSampleNInvestigationRequestDetail(SampleResultEntryFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			Map map=SampleResultEntryDATA.getAllSampleNInvestigationRequestDetail(fb.getSelectedRequestId(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			fb.setRequestId(fb.getSelectedRequestId());
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
	
	public static void saveSampleResultEntry(SampleResultEntryFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		MortuaryExtLabRequestDtlVO extLabReqDtlVO=new MortuaryExtLabRequestDtlVO();
		List<MortuaryExtLabInvReqDtlVO> lstInvestigation=new ArrayList<MortuaryExtLabInvReqDtlVO>();
		List<MortuaryExtReqSampleDtlVO> lstSample=new ArrayList<MortuaryExtReqSampleDtlVO>();
		
		try
		{
			extLabReqDtlVO.setResult(fb.getFinalResult());
			extLabReqDtlVO.setStatus(MortuaryConfig.EXTERNAL_LAB_REQUESTE_STATUS_REPORT_RECEIVED);
			extLabReqDtlVO.setRequestId(fb.getRequestId());
			extLabReqDtlVO.setPostmortemId(fb.getPostmortemId());
			
			for(int i=0;i<fb.getLabTestId().length;i++)
			{
				MortuaryExtLabInvReqDtlVO extLabInvReqVO=new MortuaryExtLabInvReqDtlVO();
				extLabInvReqVO.setLabTestResult(fb.getLabTestResults()[i]);
				extLabInvReqVO.setRequestId(fb.getRequestId());
				extLabInvReqVO.setLabTestId(fb.getLabTestId()[i]);
				
				lstInvestigation.add(extLabInvReqVO);
			}
			
			if(fb.getSelectedReturnedItem()!=null)
			{
				for(int i=0;i<fb.getSelectedReturnedItem().length;i++)
				{
					MortuaryExtReqSampleDtlVO sampleVO=new MortuaryExtReqSampleDtlVO();
					sampleVO.setItemCode(fb.getSelectedReturnedItem()[i]);
					sampleVO.setReceiveRemarks(fb.getReceiveRemarks()[i]);
					sampleVO.setRequestId(fb.getRequestId());
					sampleVO.setStatus(MortuaryConfig.EXTERNAL_REQUEST_SAMPLE_STATUS_RECEIVED_BACK);
					
					lstSample.add(sampleVO);
				}
			}
			
			SampleResultEntryDATA.saveSampleResultEntry(extLabReqDtlVO,lstInvestigation,lstSample,getUserVO(request));
			objStatus.add(Status.DONE,"","Sample Result Entered Successfully");
		
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
	
	public static void searchPostmortemNo(SampleResultEntryFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			deceasedDtlVO=SampleResultEntryDATA.searchPostmortemNo(fb.getDeceasedNo(),fb.getSearchFName(),fb.getSearchMName(),fb.getSearchLName(),fb.getDeathDate(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ARR_SEARCHED_POSTMORTEM_VO, deceasedDtlVO);
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
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
}
