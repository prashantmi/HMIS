package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordIssueDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.RequestRecordDtlVO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.transaction.controller.data.MrdRecordIssueDATA;
import mrd.transaction.controller.fb.MrdRecordIssueFB;

public class MrdRecordIssueUTL extends ControllerUTIL {
	
	public static boolean getRequestListForIssue(MrdRecordIssueFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		
		try
		{
			setSysdate(request);
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO=new MrdRecordRequestDtlVO();
			mrdRecordRequestDtlVO.setReqStatus(MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS);
			
			List mrdRecordRequestVOList=MrdRecordIssueDATA.getRequestListForIssue(mrdRecordRequestDtlVO,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.MRD_RECORD_REQUEST_VO_LIST ,mrdRecordRequestVOList);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
		
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
	
	
	public static void getRequestDetail(MrdRecordIssueFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List<MrdRecordRequestDtlVO> mrdRecordRequestVOList=null;
		try
		{
			RequestRecordDtlVO requestRecordDtlVO=new RequestRecordDtlVO();
			requestRecordDtlVO.setReqStatus(MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS);//recheck
			mrdRecordRequestVOList=(List)request.getSession().getAttribute(MrdConfig.MRD_RECORD_REQUEST_VO_LIST);
			int i=0;
			if(mrdRecordRequestVOList!=null){
				for(i=0;i<mrdRecordRequestVOList.size();i++){
					if((mrdRecordRequestVOList.get(i).getRequestId().equals(fb.getRequestId())) && (mrdRecordRequestVOList.get(i).getRecordId().equals(fb.getRecordId())))
					{
						HelperMethods.populate(fb, mrdRecordRequestVOList.get(i));
						break;
					}
				}
			}
			requestRecordDtlVO.setRequestId(fb.getRequestId());
			requestRecordDtlVO.setRecordId(fb.getRecordId());
			String sysdate=(String)WebUTIL.getSysdate((Date)request.getSession().getAttribute(Config.SYSDATEOBJECT), "dd-MM-yyyy");
			//System.out.println("WebUTIL.addDate(sysdate, Integer.parseInt(fb.getIssueUpto()))" +WebUTIL.addDate(sysdate, Integer.parseInt(fb.getIssueUpto())));
			String expectedReturnDate=WebUTIL.addDate(sysdate, Integer.parseInt(fb.getIssueUpto()));
			
			String maxReturnDate=WebUTIL.addDate(sysdate, Integer.parseInt(fb.getForDays()));
			
			fb.setExpectedReturnDate(getDateString(expectedReturnDate));
			fb.setMaxReturnDate(getDateString(maxReturnDate));
			List requestRecordDtlVOList=MrdRecordIssueDATA.getRequestDetail(requestRecordDtlVO,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.REQUEST_RECORD_VO_LIST ,requestRecordDtlVOList);
			objStatus.add(Status.RECORDFOUND);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			
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
	
	
	public static boolean saveIssueDetail(MrdRecordIssueFB fb,HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		List <RequestRecordDtlVO> requestRecordDtlVOOldList=null;
		List <RequestRecordDtlVO> requestRecordDtlVOList=null;
		List<MrdRecordRequestDtlVO> mrdRecordRequestVOList=null;
		MrdRecordRequestDtlVO mrdRecordRequestDtlVO=new MrdRecordRequestDtlVO();
		RequestRecordDtlVO requestRecordDtlVO=null;
		List <MrdRecordDtlVO> mrdRecordDtlVOList=new ArrayList<MrdRecordDtlVO>();
		MrdRecordDtlVO mrdRecordDtlVO=null;
		List <MrdRecordIssueDtlVO> mrdRecordIssueDtlVOList=new ArrayList<MrdRecordIssueDtlVO>(); 
		MrdRecordIssueDtlVO mrdRecordIssueDtlVO=null;
		try
		{
			mrdRecordRequestVOList=(List)request.getSession().getAttribute(MrdConfig.MRD_RECORD_REQUEST_VO_LIST);
			
			if(mrdRecordRequestVOList!=null){
				for(int i=0;i<mrdRecordRequestVOList.size();i++){
					if(mrdRecordRequestVOList.get(i).getRequestId().equals(fb.getRequestId())){
						mrdRecordRequestDtlVO=mrdRecordRequestVOList.get(i);
						break;
					}
				}
			}
			
			
			requestRecordDtlVOOldList=(List)request.getSession().getAttribute(MrdConfig.REQUEST_RECORD_VO_LIST);
			if(fb.getSelectedRecord()!=null && requestRecordDtlVOOldList!=null){
				int k=0;
				requestRecordDtlVOList=new ArrayList<RequestRecordDtlVO>();
				for(int i=0;i<requestRecordDtlVOOldList.size();i++){
					if(k<fb.getSelectedRecord().length && i==Integer.parseInt(fb.getSelectedRecord()[k])){
						requestRecordDtlVO=new RequestRecordDtlVO();
						mrdRecordDtlVO=new MrdRecordDtlVO();
						mrdRecordIssueDtlVO=new MrdRecordIssueDtlVO();
						
						requestRecordDtlVO=requestRecordDtlVOOldList.get(i);
						if(fb.getIsRejected().equals("1")) // rejected
						{
						requestRecordDtlVO.setReqStatus(MrdConfig.REQUESTED_RECORD_STATUS_REJECTED_AT_MRD);//rejected @mrd
						}
						else
						{
							requestRecordDtlVO.setReqStatus(MrdConfig.REQUESTED_RECORD_STATUS_ISSUED);//issued
						}
						requestRecordDtlVOList.add(requestRecordDtlVO);

						mrdRecordDtlVO.setMrdRecordId(requestRecordDtlVO.getMrdRecordId());
						mrdRecordDtlVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_ISSUE);//issued
						mrdRecordDtlVOList.add(mrdRecordDtlVO);
						
						HelperMethods.populate(mrdRecordIssueDtlVO,requestRecordDtlVO);
						if(fb.getIsRejected().equals("1")) //rejected
						{
							mrdRecordIssueDtlVO.setReqStatus(MrdConfig.MRD_RECORD_REQUEST_STATUS_CLOSED);//check status as per above request status
							mrdRecordIssueDtlVO.setRejectRemarks(fb.getRejectRemarks());
							mrdRecordIssueDtlVO.setExpectedReturnDate("");
							mrdRecordIssueDtlVO.setHandoverTo("");
							mrdRecordIssueDtlVO.setRemarks("");
							mrdRecordIssueDtlVO.setHandoverToName("");
							mrdRecordRequestDtlVO.setRejectRemarks(mrdRecordIssueDtlVO.getRejectRemarks());
							
						
						}
						else
						{
							mrdRecordIssueDtlVO.setReqStatus(MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS);//in process // issued
							mrdRecordIssueDtlVO.setExpectedReturnDate(fb.getExpectedReturnDate());
							mrdRecordIssueDtlVO.setHandoverTo(fb.getHandoverTo());
							mrdRecordIssueDtlVO.setRemarks(fb.getIssueRemarks());
							mrdRecordIssueDtlVO.setRecordType(fb.getRecordType());
							mrdRecordIssueDtlVO.setHandoverToName(fb.getHandoverToName());
							if(requestRecordDtlVO.getExtendDays() != null && requestRecordDtlVO.getExtendDays() != "")
							{
								mrdRecordRequestDtlVO.setForDays(mrdRecordRequestDtlVO.getForDays() + requestRecordDtlVO.getExtendDays());
								mrdRecordRequestDtlVO.setExtendDays(requestRecordDtlVO.getExtendDays());
							}
						}
						
						mrdRecordIssueDtlVOList.add(mrdRecordIssueDtlVO);
						
						k++;
					}
				}
			}
			MrdRecordIssueDATA.saveIssueDetail(mrdRecordRequestDtlVO,requestRecordDtlVOList,mrdRecordDtlVOList,mrdRecordIssueDtlVOList,getUserVO(request));
			objStatus.add(Status.NEW);
			
		}
		
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	
	public static String getDateString(String date){
		
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.DATE, Integer.parseInt(date.substring(0, 2).trim()));
		cl.set(Calendar.MONTH, (Integer.parseInt(date.substring(3, 5).trim())) - 1);
		cl.set(Calendar.YEAR, Integer.parseInt(date.substring(6, 10).trim()));
		Date dt = cl.getTime();
		SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
		sf.applyPattern("dd-MMM-yyyy");
		return sf.format(dt);
	}
	
	
	
		
}
