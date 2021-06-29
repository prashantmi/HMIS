package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.transaction.controller.data.RecordRequestApprovalDATA;
import mrd.transaction.controller.fb.RecordRequestApprovalFB;

public class RecordRequestApprovalUTL extends ControllerUTIL {
	
	public static boolean getRequestListForApproval(RecordRequestApprovalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		//UserVO userVO = getUserVO(request);
		try
		{
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO=new MrdRecordRequestDtlVO();
			mrdRecordRequestDtlVO.setReqStatus(MrdConfig.MRD_RECORD_REQUEST_STATUS_RAISED);
			List mrdRecordRequestVOList=RecordRequestApprovalDATA.getRequestListForApproval(mrdRecordRequestDtlVO,getUserVO(request));
			mrdRecordRequestDtlVO = RecordRequestApprovalDATA.checkUserIsEmp(mrdRecordRequestDtlVO,getUserVO(request));
			if(mrdRecordRequestDtlVO.getIsUserEmp() != null && (!mrdRecordRequestDtlVO.getIsUserEmp().equals("")))
			{
				fb.setIsUserEmp("1");
			}
			else
			{
				fb.setIsUserEmp("0");
			}
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
	
	
	public static void getRequestDetail(RecordRequestApprovalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		String arr[]=fb.getSelectedRecordId().split("#");
		fb.setRecordId(arr[1]);
		
		List<MrdRecordRequestDtlVO> mrdRecordRequestVOList=null;
		try
		{
			RequestRecordDtlVO requestRecordDtlVO=new RequestRecordDtlVO();
			requestRecordDtlVO.setReqStatus(MrdConfig.REQUESTED_RECORD_STATUS_RAISED);
			mrdRecordRequestVOList=(List)request.getSession().getAttribute(MrdConfig.MRD_RECORD_REQUEST_VO_LIST);
			
			if(mrdRecordRequestVOList!=null){
				for(int i=0;i<mrdRecordRequestVOList.size();i++){
					if((mrdRecordRequestVOList.get(i).getRequestId().equals(fb.getRequestId()))&&(mrdRecordRequestVOList.get(i).getRecordId().equals(fb.getRecordId()))){
						HelperMethods.populate(fb, mrdRecordRequestVOList.get(i));
						break;
					}
				}
			}
			requestRecordDtlVO.setRequestId(fb.getRequestId());
			requestRecordDtlVO.setRecordId(fb.getRecordId());//added by sandip naik on 20/7/17
			
			List requestRecordDtlVOList=RecordRequestApprovalDATA.getRequestDetail(requestRecordDtlVO,getUserVO(request));
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
	
	
	
	/** 
	 * save the approval and reject details
	 * update the mrdRecordRequestdtl set request_status as approved if any of the record 
	 * is accepted for the particular request Id and update the approval detail and issue_upto field, 
	 * the request_record_dtl is updated for each record Id which has been accepted with the record status as approved and the 
	 * record which has not been selected will be updated as rejected with reject reason.
	 * if the request Id is rejected then set the request_status as rejected and 
	 * update the cancel reason and no update in request_record_dtl table .
	 * 
	 */
	
	public static boolean saveApprovalDetail(RecordRequestApprovalFB fb,HttpServletRequest request)
	{
		boolean flag=true;
		Status objStatus = new Status();
		List <RequestRecordDtlVO> requestRecordDtlVOOldList=null;
		List <RequestRecordDtlVO> requestRecordDtlVOList=null;
		List<MrdRecordRequestDtlVO> mrdRecordRequestVOList=null;
		MrdRecordRequestDtlVO mrdRecordRequestDtlVO=new MrdRecordRequestDtlVO();
		RequestRecordDtlVO requestRecordDtlVO=null;
		try
		{
			mrdRecordRequestVOList=(List)request.getSession().getAttribute(MrdConfig.MRD_RECORD_REQUEST_VO_LIST);

			// if accept is selected on the list page
			if(mrdRecordRequestVOList!=null){
				for(int i=0;i<mrdRecordRequestVOList.size();i++){
					if(mrdRecordRequestVOList.get(i).getRequestId().equals(fb.getRequestId())){
						mrdRecordRequestDtlVO=mrdRecordRequestVOList.get(i);
						break;
					}
				}
			}
			
			
			requestRecordDtlVOOldList=(List)request.getSession().getAttribute(MrdConfig.REQUEST_RECORD_VO_LIST);
			//int rejectCount=0;
			requestRecordDtlVOList=new ArrayList<RequestRecordDtlVO>();
			if(fb.getIsAcceptAll().equals("1"))		//Accept
			{	
				if(fb.getSelectedRecord()!=null && requestRecordDtlVOOldList!=null){
					int k=0;
				//	
					for(int i=0;i<requestRecordDtlVOOldList.size();i++){
						requestRecordDtlVO=new RequestRecordDtlVO();
						if(k<fb.getSelectedRecord().length && i==Integer.parseInt(fb.getSelectedRecord()[k])){
							requestRecordDtlVO=requestRecordDtlVOOldList.get(i);
							requestRecordDtlVO.setReqStatus(MrdConfig.REQUESTED_RECORD_STATUS_REJECTED_AT_DEPT);//rejected @dept
							//Enumeration enumeration=request.getParameterNames();
							//String isAccepted=request.getParameter("isAccept_"+i);
							k++;
						}
						else{
							requestRecordDtlVO=requestRecordDtlVOOldList.get(i);
							requestRecordDtlVO.setReqStatus(MrdConfig.REQUESTED_RECORD_STATUS_ACCEPTED_AT_DEPT);//accepted @ dept
							requestRecordDtlVO.setRecordId(fb.getRecordId());
							requestRecordDtlVO.setCancelReason("");
							//rejectCount++;
						}
						requestRecordDtlVOList.add(requestRecordDtlVO);
					}
					mrdRecordRequestDtlVO.setReqStatus(MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS);// check from record list
					mrdRecordRequestDtlVO.setIssueUpto(fb.getAcceptForDays());
				}
			}
			
			
			//if reject is selected on the list page
			if(fb.getIsAcceptAll().equals("0"))		//Reject
			{	
				if(mrdRecordRequestVOList!=null){
					for(int i=0;i<mrdRecordRequestVOList.size();i++){
						if(mrdRecordRequestVOList.get(i).getRequestId().equals(fb.getSelectedRecordId())){
							mrdRecordRequestDtlVO=mrdRecordRequestVOList.get(i);
							break;
						}
					}
				}
				mrdRecordRequestDtlVO.setReqStatus(MrdConfig.MRD_RECORD_REQUEST_STATUS_CLOSED);//closed
				mrdRecordRequestDtlVO.setCancelReason(fb.getCancelReason());
				if(requestRecordDtlVOOldList!=null)
				for(int i=0;i<requestRecordDtlVOOldList.size();i++){
					requestRecordDtlVO=new RequestRecordDtlVO();
					requestRecordDtlVO=requestRecordDtlVOOldList.get(i);
					requestRecordDtlVO.setReqStatus(MrdConfig.REQUESTED_RECORD_STATUS_REJECTED_AT_DEPT);// ejecred @dept
					//requestRecordDtlVO.setCancelReason(fb.getCancelReason());
					requestRecordDtlVOList.add(requestRecordDtlVO);
				}
			//	requestRecordDtlVOList=null;
			}
			
			RecordRequestApprovalDATA.saveApprovalDetail(mrdRecordRequestDtlVO,requestRecordDtlVOList,getUserVO(request));
			
			//objStatus.add(Status.DONE,"","Record Saved Successfully");
		}
		
		catch (HisDataAccessException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			
		}
		catch (HisApplicationExecutionException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		catch (HisException e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			
		}
		catch (Exception e)
		{
			flag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	//get mrd record status and archived detail
	public static void getMrdRecordStatus(RecordRequestApprovalFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//List<MrdRecordDtlVO> mrdRecordRequestVOList=null;
		
		try
		{
			Map map=RecordRequestApprovalDATA.getMrdRecordStatus(fb.getMrdRecordId(),fb.getRequestId(),getUserVO(request));
			//WebUTIL.setAttributeInSession(request,MrdConfig.MRD_RECORD_DTL_VO_LIST ,mrdRecordRequestVOList);
			WebUTIL.setMapInSession(map, request);
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
	
	
	
		
}
