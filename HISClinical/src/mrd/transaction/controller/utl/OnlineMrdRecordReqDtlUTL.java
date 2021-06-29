package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.OnlineMrdRecordReqDtlDATA;
import mrd.transaction.controller.data.RecordRequestApprovalDATA;
import mrd.transaction.controller.fb.OnlineMrdRecordReqDtlFB;
import mrd.vo.CommonCaseSheetEnquiryVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.RequestPurposeMstVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

public class OnlineMrdRecordReqDtlUTL extends ControllerUTIL
{
	public static void getEssentialForOnlineReq(OnlineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			UserVO user=getUserVO(request);
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO=new MrdRecordRequestDtlVO();
			Map map=OnlineMrdRecordReqDtlDATA.getEssentialForOnlineReq(user);
			
			if(user.getUserEmpID() != null)
			{
				mrdRecordRequestDtlVO = RecordRequestApprovalDATA.checkUserIsEmp(mrdRecordRequestDtlVO,getUserVO(request));
				if(mrdRecordRequestDtlVO.getIsUserEmp() != null && (!mrdRecordRequestDtlVO.getIsUserEmp().equals("")))
				{
					fb.setIsUserEmp("1");
				}
				else
				{
					fb.setIsUserEmp("0");
				}
			}
			else
			{
				fb.setIsUserEmp("0");
			}
			WebUTIL.setMapInSession(map, request);
			fb.setIsMrdLengthOne(MrdConfig.YES);
			fb.setUserEmpId(user.getUserEmpID());
		/*	List lstMrd=(List)map.get(MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE);
			if(lstMrd.size()>1)
				fb.setIsMrdLengthOne(MrdConfig.NO);
			else
			{
				fb.setIsMrdLengthOne(MrdConfig.YES);
				Entry ent=(Entry)lstMrd.get(0);
				String mrdCode=ent.getValue();
				fb.setMrdCode(mrdCode);
			}*/
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
	
	public static void getMrdBasedOnRecordType(OnlineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		List lstMrd=null;
		
		try
		{
			essentialMap=OnlineMrdRecordReqDtlDATA.getMrdNPurposeBasedOnRecordType(fb.getRecordType(),getUserVO(request));
			WebUTIL.setMapInSession(essentialMap, request);
			
			lstMrd=(List)essentialMap.get(MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE);
			RequestPurposeMstVO[] arrReqPurposeVO=(RequestPurposeMstVO[])essentialMap.get(MrdConfig.ARR_REQUEST_PURPOSE_MST_VO);

			WebUTIL.setAttributeInSession(request, MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE, lstMrd);
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO, null);
			fb.setIsRecordRequested(MrdConfig.NO);
			if(lstMrd.size()>1)
				fb.setIsMrdLengthOne(MrdConfig.NO);
			else
			{
				fb.setIsMrdLengthOne(MrdConfig.YES);
				Entry ent=(Entry)lstMrd.get(0);
				String mrdCode=ent.getValue();
				fb.setMrdCode(mrdCode);
			}
			if(arrReqPurposeVO==null)
				objStatus.add(Status.UNSUCESSFULL, "", "No Request Purpose Found For The Record Type");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(request, MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE, null);
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO, null);
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_REQUEST_PURPOSE_MST_VO, null);
			fb.setIsRecordRequested(MrdConfig.NO);
			fb.setIsMrdLengthOne(MrdConfig.YES);
			
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
	
	public static void populateSelectedRecord(OnlineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		List<CommonCaseSheetEnquiryVO> lstToBeRequestedVO=new ArrayList<CommonCaseSheetEnquiryVO>();
		List<CommonCaseSheetEnquiryVO> lstSearchVO=new ArrayList<CommonCaseSheetEnquiryVO>();
		
		try
		{
			if(fb.getConcatedIndex()!=null || !fb.getConcatedIndex().equals(""))
			{
				lstToBeRequestedVO=(List<CommonCaseSheetEnquiryVO>)session.getAttribute(MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO);
				lstSearchVO=(List<CommonCaseSheetEnquiryVO>)session.getAttribute(MrdConfig.ARR_CASESHEET_SEARCHED_RECORD_VO);
				
				String[] index=fb.getConcatedIndex().split("@");
				if(lstToBeRequestedVO==null)
				{
					lstToBeRequestedVO=new ArrayList<CommonCaseSheetEnquiryVO>();
					for(int i=0;i<index.length;i++)
					{
						lstToBeRequestedVO.add(lstSearchVO.get(Integer.parseInt(index[i])));
					}
					WebUTIL.setAttributeInSession(request, MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO, lstToBeRequestedVO);
				}
				else
				{
					for(int i=0;i<index.length;i++)
					{
						lstToBeRequestedVO.add(lstSearchVO.get(Integer.parseInt(index[i])));
					}
					WebUTIL.setAttributeInSession(request, MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO, lstToBeRequestedVO);
				}
			}
			retainFormValue(fb, request);
			fb.setIsRecordRequested(MrdConfig.YES);
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
	
	public static void removeSelectedRecord(OnlineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		List<CommonCaseSheetEnquiryVO> lstToBeRequestedVO=new ArrayList<CommonCaseSheetEnquiryVO>();
		//List<CommonCaseSheetEnquiryVO> lstSearchVO=new ArrayList<CommonCaseSheetEnquiryVO>();
		
		try
		{
			int index=Integer.parseInt(fb.getRecordIdToRemove());
			lstToBeRequestedVO=(List<CommonCaseSheetEnquiryVO>)session.getAttribute(MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO);
			if(lstToBeRequestedVO!=null)
			{
					lstToBeRequestedVO.remove(index);
			}
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO, lstToBeRequestedVO);
			retainFormValue(fb, request);
			if(lstToBeRequestedVO.size()==0)
				fb.setIsRecordRequested(MrdConfig.NO);
			else
				objStatus.add(Status.TRANSINPROCESS);
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
	
	public static void saveOnlineRequestDetail(OnlineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		MrdRecordRequestDtlVO mrdRecordRequestVO=new MrdRecordRequestDtlVO();
		List<RequestRecordDtlVO> lstRequestRecordVO=new ArrayList<RequestRecordDtlVO>();
		List<CommonCaseSheetEnquiryVO> lstToBeRequestedVO=new ArrayList<CommonCaseSheetEnquiryVO>();
		
		try
		{
			lstToBeRequestedVO=(List<CommonCaseSheetEnquiryVO>)session.getAttribute(MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO);
			
			mrdRecordRequestVO.setMrdCode(fb.getMrdCode());
			mrdRecordRequestVO.setRecordType(fb.getRecordType());
			mrdRecordRequestVO.setReqPurposeId(fb.getReqPurposeId());
			if(fb.getReqByIsHod().equals("1"))
			{
				mrdRecordRequestVO.setReqStatus(MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS);// inprocess // approved
			}
			else
			{
				mrdRecordRequestVO.setReqStatus(MrdConfig.MRD_RECORD_REQUEST_STATUS_RAISED);// raised // send for approval
			}
			
			mrdRecordRequestVO.setReqMode(MrdConfig.MRD_RECORD_REQUEST_MODE_BY_USER);
			mrdRecordRequestVO.setRemarks(fb.getRemarks());
			mrdRecordRequestVO.setForDays(fb.getForDays());
			//System.out.println("aprrobed by enha "+fb.getDESIGNATED_APPROVED_BY());
			if(fb.getDESIGNATED_APPROVED_BY() != null && fb.getDESIGNATED_APPROVED_BY() != "")
			{
				mrdRecordRequestVO.setDESIGNATED_APPROVED_BY(fb.getDESIGNATED_APPROVED_BY().toUpperCase());
			}
			else
			{
				mrdRecordRequestVO.setDESIGNATED_APPROVED_BY("Self Approved");
			}
		
			for(int i=0;i<lstToBeRequestedVO.size();i++)
			{
				RequestRecordDtlVO reqRecordDtlVO=new RequestRecordDtlVO();
				reqRecordDtlVO.setMrdRecordId(lstToBeRequestedVO.get(i).getMrdRecordId());
				reqRecordDtlVO.setPatCrNo(lstToBeRequestedVO.get(i).getHrgnum_puk());
				reqRecordDtlVO.setPatAdmNo(lstToBeRequestedVO.get(i).getHipnum_admno());
				reqRecordDtlVO.setDeptUnit(lstToBeRequestedVO.get(i).getDeptUnit());//added by sandip naik on 20/7/17
				reqRecordDtlVO.setMrdCode(fb.getMrdCode());
				reqRecordDtlVO.setRecordType(fb.getRecordType());
				reqRecordDtlVO.setReqPurposeId(fb.getReqPurposeId());
				if(fb.getReqByIsHod().equals("1"))
				{
					reqRecordDtlVO.setReqStatus(MrdConfig.REQUESTED_RECORD_STATUS_ACCEPTED_AT_DEPT); // accepted @ dept level // aprroved
				}
				else
				{
					reqRecordDtlVO.setReqStatus(MrdConfig.REQUESTED_RECORD_STATUS_RAISED);// raised // sent for approval
				}
				
				reqRecordDtlVO.setRemarks(fb.getReqRemarks()[i]);
				//reqRecordDtlVO.setd
				lstRequestRecordVO.add(reqRecordDtlVO);
				if(fb.getDESIGNATED_APPROVED_BY() != null && fb.getDESIGNATED_APPROVED_BY() != "")
				{
					reqRecordDtlVO.setDESIGNATED_APPROVED_BY(fb.getDESIGNATED_APPROVED_BY().toUpperCase());
				}
				else
				{
					reqRecordDtlVO.setDESIGNATED_APPROVED_BY("Self Approved");
				}
			}
			OnlineMrdRecordReqDtlDATA.saveOnlineRequestDetail(mrdRecordRequestVO,lstRequestRecordVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Request Raised");
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
	
	public static void retainFormValue(OnlineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		List<CommonCaseSheetEnquiryVO> lstToBeRequestedVO=null;
		String []remarks=null;
		try
		{
			
			lstToBeRequestedVO=(List<CommonCaseSheetEnquiryVO>)request.getSession().getAttribute(MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO);
			if(lstToBeRequestedVO!=null){
				remarks=new String[lstToBeRequestedVO.size()];
				//int j=fb.getReqRemarks().length;
				for(int i=0;i<lstToBeRequestedVO.size();i++){
					//if(fb.getReqRemarks()[i]==null)
						remarks[i]="";
					//else
						//remarks[i]=fb.getReqRemarks()[i];
				}
			}
			fb.setReqRemarks(remarks);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void getPendingRecordRequestStatus(OnlineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		List<RequestRecordDtlVO> listRecRequestDtlVO=new ArrayList<RequestRecordDtlVO>();
		
		try
		{
			listRecRequestDtlVO=OnlineMrdRecordReqDtlDATA.getPendingRecordRequestStatus(fb.getRequestId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ONLINE_PENDING_REQUEST_STATUS_DETAIL, listRecRequestDtlVO);
			if(fb.getReqStatus().equals(MrdConfig.REQUESTED_RECORD_STATUS_REJECTED_AT_DEPT))
			{
				fb.setRejectReason(listRecRequestDtlVO.get(0).getRejectReason());
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
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void deleteRejectedRecordDetail(OnlineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			OnlineMrdRecordReqDtlDATA.deleteRejectedRecordDetail(fb.getRequestId(),getUserVO(request));
			objStatus.add(Status.DONE,"","");
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
	
	public static void getExtendedDays(OnlineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		RequestRecordDtlVO RequestRecordDtlVO=new RequestRecordDtlVO();
		
		try
		{
			RequestRecordDtlVO.setRequestId(fb.getRequestId());
			RequestRecordDtlVO.setRecordTypeName(fb.getRecordType());
			//RequestRecordDtlVO.set
			
			WebUTIL.setAttributeInSession(request, MrdConfig.EXTENDED_REQUEST_DAYS, RequestRecordDtlVO);
			
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

	public static void saveExtendDays(OnlineMrdRecordReqDtlFB fb,
			HttpServletRequest request) {
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		MrdRecordRequestDtlVO mrdRecordRequestVO=new MrdRecordRequestDtlVO();
		MrdRecordRequestDtlVO mrdRecordRequestVOO=new MrdRecordRequestDtlVO();
		
		try
		{
			mrdRecordRequestVO.setRequestId(fb.getRequestId());
			mrdRecordRequestVO.setRecordType(fb.getRecordType());
			mrdRecordRequestVO.setExtendDays(fb.getExtendDays());
			mrdRecordRequestVO.setExtendReason(fb.getExtendReason());
			
			
			OnlineMrdRecordReqDtlDATA.saveExtendDays(mrdRecordRequestVO,getUserVO(request));
			mrdRecordRequestVOO = OnlineMrdRecordReqDtlDATA.getEssentials(mrdRecordRequestVO,getUserVO(request));
			mrdRecordRequestVOO.setRequestId(mrdRecordRequestVO.getRequestId());
			OnlineMrdRecordReqDtlDATA.updateSL_NO(mrdRecordRequestVOO,getUserVO(request));
			objStatus.add(Status.DONE,"","Request Date Extended");
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
