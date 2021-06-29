package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ehr.EHRConfig;
import ehr.ot.vo.EHRSection_OTDetailsVO;
import mrd.MrdConfig;
import mrd.transaction.controller.data.OfflineMrdRecordReqDtlDATA;
import mrd.transaction.controller.fb.OfflineMrdRecordReqDtlFB;
import mrd.transaction.controller.fb.IcdIndexingFB;
import hisglobal.vo.PatientDetailVO;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.RequestPurposeMstVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

public class OfflineMrdRecordReqDtlUTL extends ControllerUTIL
{
	public static void getEssentialForOfflineReq(OfflineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			UserVO userVO = new UserVO();
			userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			Map map=OfflineMrdRecordReqDtlDATA.getEssentialForOfflineReq(getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			fb.setIsMrdLengthOne(MrdConfig.YES);
			fb.setRequestByName(userVO.getUsrName());
			/*List lstMrd=(List)map.get(MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE);
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
	
	
	
	
	
	
	
	
	public static void getMrdBasedOnRecordType(OfflineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			Map map=OfflineMrdRecordReqDtlDATA.getMrdNPurposeBasedOnRecordType(fb.getRecordType(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			
			List lstMrd=(List)map.get(MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE);
			RequestPurposeMstVO[] arrReqPurposeVO=(RequestPurposeMstVO[])map.get(MrdConfig.ARR_REQUEST_PURPOSE_MST_VO);

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
	
	
	public static void getIcdDtls(IcdIndexingFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		String reqdeptname = request.getParameter("deptname");
		String sheetdeptname = request.getParameter("sheetdept");
		//System.out.println("nneha"+sheetdeptname);
		if(reqdeptname != null)
		{
			fb.setRequestByEmpDept(reqdeptname);
			
		}
		if(sheetdeptname != null)
		{
			fb.setSheetDept(sheetdeptname);
		}
		
		try
		{
			String diagnosisCodeList=OfflineMrdRecordReqDtlDATA.getIcdDtls(getUserVO(request));
			System.out.println("diagnosisCodeList:::>>> "+diagnosisCodeList);
			request.getSession().setAttribute("icdDiagObj", diagnosisCodeList);
			//WebUTIL.setAttributeInSession(request, MrdConfig.DYNAMIC_DESK_ICD_DISEASE_LIST, diagnosisCodeList);
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
	
	public static void openPopupForSearchRecord(OfflineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		String reqdeptname = request.getParameter("deptname");
		String sheetdeptname = request.getParameter("sheetdept");
		//System.out.println("nneha"+sheetdeptname);
		if(reqdeptname != null)
		{
			fb.setRequestByEmpDept(reqdeptname);
			
		}
		if(sheetdeptname != null)
		{
			fb.setSheetDept(sheetdeptname);
		}
		
		try
		{
			List lstDischargeType=OfflineMrdRecordReqDtlDATA.getListDischargeType(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.CASE_SHEET_ENQUIRY_DISCHARGE_TYPE_LIST, lstDischargeType);
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
	
	public static void searchRecord(OfflineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		CommonCaseSheetEnquiryVO[] commoncaseSheetVO=null;
		CaseSheetEnquiryVO caseSheetEnqVO=new CaseSheetEnquiryVO();
		List<CommonCaseSheetEnquiryVO> lstSearchVO=new ArrayList<CommonCaseSheetEnquiryVO>();
		List<CommonCaseSheetEnquiryVO> lstRequestedVO= new ArrayList<CommonCaseSheetEnquiryVO>();
		String strReqRecIdNStatus="";
		
		
		try
		{
			if(fb.getIsReqOnlineOffline().equals(MrdConfig.RECORD_REQUEST_ONLINE))
			{
				List lstRequestedRecId=OfflineMrdRecordReqDtlDATA.getRequestedRecordId(fb.getUserEmpId(),getUserVO(request));
				for(int i=0;i<lstRequestedRecId.size();i++)
				{
					Entry ent=(Entry)lstRequestedRecId.get(i);
					strReqRecIdNStatus=strReqRecIdNStatus+ent.getValue()+"@";
				}
				if(strReqRecIdNStatus.length()!=0)strReqRecIdNStatus=strReqRecIdNStatus.substring(0,strReqRecIdNStatus.length()-1);
			}
			else
			{
				List lstRequestedRecId=OfflineMrdRecordReqDtlDATA.getRequestedRecordId(fb.getEmployeeId(),getUserVO(request));
				for(int i=0;i<lstRequestedRecId.size();i++)
				{
					Entry ent=(Entry)lstRequestedRecId.get(i);
					strReqRecIdNStatus=strReqRecIdNStatus+ent.getValue()+"@";
				}
				if(strReqRecIdNStatus.length()!=0)strReqRecIdNStatus=strReqRecIdNStatus.substring(0,strReqRecIdNStatus.length()-1);
			}
			
			fb.setDucRecordId(strReqRecIdNStatus);
			
			HelperMethods.populate(caseSheetEnqVO,fb);
			caseSheetEnqVO.setRecordType(fb.getRecordType());
			lstRequestedVO=(List<CommonCaseSheetEnquiryVO>)session.getAttribute(MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO);
			commoncaseSheetVO=OfflineMrdRecordReqDtlDATA.searchRecord(caseSheetEnqVO,getUserVO(request));
			for(int i=0;i<commoncaseSheetVO.length;i++)
			{
				lstSearchVO.add(commoncaseSheetVO[i]);
			}
			
			if(lstRequestedVO!=null)
			{
				for(int i=0;i<lstRequestedVO.size();i++)
				{
					for(int j=0;j<lstSearchVO.size();j++)
					{
						if(lstRequestedVO.get(i).getMrdRecordId().equals(lstSearchVO.get(j).getMrdRecordId()))
							lstSearchVO.remove(j);
					}
				}
			}
			
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_CASESHEET_SEARCHED_RECORD_VO, lstSearchVO);
			if(lstSearchVO.size()==0)
				objStatus.add(Status.TRANSINPROCESS,"","No Record Found Matching The Search Criteria");
			else
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
	
	
	public static void populateSelectedRecord(OfflineMrdRecordReqDtlFB fb,HttpServletRequest request)
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
	
	
	public static void removeSelectedRecord(OfflineMrdRecordReqDtlFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		List<CommonCaseSheetEnquiryVO> lstToBeRequestedVO=new ArrayList<CommonCaseSheetEnquiryVO>();
		
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
	
	public static void saveForOfflineRequestDetail(OfflineMrdRecordReqDtlFB fb,HttpServletRequest request)
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
			mrdRecordRequestVO.setReqStatus(MrdConfig.MRD_RECORD_REQUEST_STATUS_RAISED);
			mrdRecordRequestVO.setReqMode(MrdConfig.MRD_RECORD_REQUEST_MODE_EXTERNAL);
			mrdRecordRequestVO.setRemarks(fb.getRemarks());
			mrdRecordRequestVO.setForDays(fb.getForDays());
			mrdRecordRequestVO.setRequestBy(fb.getRequestById());
			mrdRecordRequestVO.setRequestByFlag(fb.getRequestByFlag());
			if(fb.getRequestByFlag().equals("0")){
				mrdRecordRequestVO.setRequestByName(fb.getRequestByName());	
			}
			else{
				mrdRecordRequestVO.setRequestByName(fb.getRequestByExternalName());
			}
			
			for(int i=0;i<lstToBeRequestedVO.size();i++)
			{
				RequestRecordDtlVO reqRecordDtlVO=new RequestRecordDtlVO();
				reqRecordDtlVO.setMrdRecordId(lstToBeRequestedVO.get(i).getMrdRecordId());
				reqRecordDtlVO.setDeptUnit(lstToBeRequestedVO.get(i).getDeptUnit());//added by sandip naik on 25/7/17
				reqRecordDtlVO.setPatCrNo(lstToBeRequestedVO.get(i).getHrgnum_puk());
				reqRecordDtlVO.setPatAdmNo(lstToBeRequestedVO.get(i).getHipnum_admno());
				reqRecordDtlVO.setMrdCode(fb.getMrdCode());
				reqRecordDtlVO.setRecordType(fb.getRecordType());
				reqRecordDtlVO.setReqPurposeId(fb.getReqPurposeId());
				//reqRecordDtlVO.setReqStatus(MrdConfig.REQUESTED_RECORD_STATUS_ACCEPTED_AT_DEPT);
				reqRecordDtlVO.setRemarks(fb.getReqRemarks()[i]);
				
				lstRequestRecordVO.add(reqRecordDtlVO);
			}
			
			OfflineMrdRecordReqDtlDATA.saveForOfflineRequestDetail(mrdRecordRequestVO,lstRequestRecordVO,getUserVO(request));
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
	
	public static void retainFormValue(OfflineMrdRecordReqDtlFB fb,HttpServletRequest request){
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
}
