package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MrdRackShelfChangeDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordIssueDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.MrdRecordReturnDtlVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.RequestRecordDtlVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.MrdRecordReturnDATA;
import mrd.transaction.controller.fb.MrdRecordReturnFB;

public class MrdRecordReturnUTL extends ControllerUTIL {
	
	public static void getRequestListForReturn(MrdRecordReturnFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List<MrdRecordRequestDtlVO> mrdRecordIssuedReqList=new ArrayList<MrdRecordRequestDtlVO>();
		
		try
		{
			setSysdate(request);
			mrdRecordIssuedReqList=MrdRecordReturnDATA.getRequestListForReturn(getUserVO(request)); 
			WebUTIL.setAttributeInSession(request,MrdConfig.ISSUED_MRD_RECORD_REQUEST_VO_LIST ,mrdRecordIssuedReqList);
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
	}
	
	public static void getReturnedMrdRecordListByRequestId(MrdRecordReturnFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List<MrdRecordIssueDtlVO> mrdRecordIssuedList=new ArrayList<MrdRecordIssueDtlVO>();
		
		try
		{
			//mrdRecordIssuedList=MrdRecordReturnDATA.getReturnedMrdRecordListByRequestId(fb.getRequestId(),getUserVO(request));
			mrdRecordIssuedList=MrdRecordReturnDATA.getReturnedMrdRecordListByRequestId(fb.getRequestId(),fb.getRecordId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ISSUED_MRD_RECORD_BY_REQUEST_ID_VO_LIST, mrdRecordIssuedList);
			Map map=MrdRecordReturnDATA.getArchivalDetail(fb.getRecordType(),fb.getMrdCode(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			
			RackMstVO[] rackMstVO=(RackMstVO[])map.get(MrdConfig.LIST_OF_RACK_BASED_ON_MRD);
			if(rackMstVO!=null)
				objStatus.add(Status.RECORDFOUND);
			else
				objStatus.add(Status.RECORDFOUND,"","No Rack Found");
			
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
	}
	
	public static void getShelfBasedOnRack(MrdRecordReturnFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String rackId=fb.getRackId();
		String recordType="";
		String tempStr="";
		String rackInfo="";
		
		try
		{
			if(fb.getSelectedRecord()!=null)
			{
				for(int i=0;i<fb.getSelectedRecord().length;i++)
				{
					tempStr=tempStr+fb.getSelectedRecord()[i]+"@";
				}
			}
			if(tempStr.length()!=0)tempStr=tempStr.substring(0,tempStr.length()-1);
			fb.setTempSelectedChk(tempStr);
			fb.setHiddenReturnByName(fb.getReturnByName());
			
			RackMstVO[] rackMstVO=(RackMstVO[])session.getAttribute(MrdConfig.LIST_OF_RACK_BASED_ON_MRD); 
			for(int i=0;i<rackMstVO.length;i++)
			{
				if(fb.getRackId().equals(rackMstVO[i].getRackId()))
				{
					rackInfo=rackInfo+rackMstVO[i].getBuildingCode()+" , "+rackMstVO[i].getBlockId()+" , "+rackMstVO[i].getFloorId()+" , "+rackMstVO[i].getRoomId();
					fb.setRackInfo(rackInfo);
					break;
				}
			}
			
			recordType=fb.getRecordType();
			List lstShelf=MrdRecordReturnDATA.getShelfBasedOnRack(recordType,rackId,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.LIST_OF_SHELF_BASED_ON_RACK , lstShelf);
			
			objStatus.add(Status.RECORDFOUND);
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
	
	public static boolean saveMrdRecordReturnDetail(MrdRecordReturnFB fb,HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<MrdRecordIssueDtlVO> mrdRecordIssuedList=null;
		List<MrdRecordReturnDtlVO> mrdRecordReturnDtlList=new ArrayList<MrdRecordReturnDtlVO>();
		List<MrdRecordDtlVO> mrdRecordDtlVOList=new ArrayList<MrdRecordDtlVO>();
		List<MrdRackShelfChangeDtlVO> mrdRackShelfChangeList=new ArrayList<MrdRackShelfChangeDtlVO>();
		String isRequestReturn=MrdConfig.NO;

	
		try
		{
			mrdRecordIssuedList=(List<MrdRecordIssueDtlVO>)session.getAttribute(MrdConfig.ISSUED_MRD_RECORD_BY_REQUEST_ID_VO_LIST);
			for(int i=0;i<fb.getSelectedRecord().length;i++)
			{
				int x=Integer.parseInt(fb.getSelectedRecord()[i]);
				MrdRecordReturnDtlVO mrdRecordReturnVO=new MrdRecordReturnDtlVO();
				mrdRecordReturnVO.setRequestId(mrdRecordIssuedList.get(x).getRequestId());
				mrdRecordReturnVO.setMrdRecordId(mrdRecordIssuedList.get(x).getMrdRecordId());
				mrdRecordReturnVO.setMrdCode(mrdRecordIssuedList.get(x).getMrdCode());
				mrdRecordReturnVO.setRecordType(mrdRecordIssuedList.get(x).getRecordType());
				mrdRecordReturnVO.setReturnBy(fb.getReturnBy());
				mrdRecordReturnVO.setRemarks(fb.getRemarks());
				mrdRecordReturnVO.setReturnByName(fb.getReturnByName());
				mrdRecordReturnDtlList.add(mrdRecordReturnVO);
				
				MrdRackShelfChangeDtlVO mrdRackShelfChangeVO=new MrdRackShelfChangeDtlVO();
				MrdRecordDtlVO mrdRecordDtlVO=new MrdRecordDtlVO();
				mrdRecordDtlVO.setMrdRecordId(mrdRecordIssuedList.get(x).getMrdRecordId());
				if(fb.getRecAcceptArchivedFlag()!=null)		//For Record Archival
				{
					if(fb.getChangeArchivedFlag().equals(MrdConfig.YES))	//Changed Archived
					{
						mrdRecordDtlVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_ARCHIVED);
						mrdRecordDtlVO.setRackId(fb.getRackId());
						mrdRecordDtlVO.setShelfId(fb.getShelfId());
						mrdRecordDtlVO.setPutBy(fb.getPutBy());
						mrdRecordDtlVO.setChangeArchivedFlag(MrdConfig.YES);
						
						mrdRackShelfChangeVO.setFromMrdCode(mrdRecordIssuedList.get(x).getMrdCode());
						mrdRackShelfChangeVO.setFromRackId(mrdRecordIssuedList.get(x).getRackId());
						mrdRackShelfChangeVO.setFromShelfId(mrdRecordIssuedList.get(x).getShelfId());
						mrdRackShelfChangeVO.setMrdRecordId(mrdRecordIssuedList.get(x).getMrdRecordId());
						mrdRackShelfChangeVO.setToMrdCode(mrdRecordIssuedList.get(x).getMrdCode());
						mrdRackShelfChangeVO.setToRackId(fb.getRackId());
						mrdRackShelfChangeVO.setToShelfId(fb.getShelfId());
						mrdRackShelfChangeVO.setPutBy(fb.getPutBy());
						mrdRackShelfChangeVO.setEntryMode(MrdConfig.MRD_RACKSHELF_CHANGE_ENTRY_MODE_BY_RETURN);
						mrdRackShelfChangeVO.setRemarks(MrdConfig.RECORD_RETURN_DEFAULT_REMARKS);
						
						mrdRackShelfChangeList.add(mrdRackShelfChangeVO);
					}
					else		//Previous Place
					{
						mrdRecordDtlVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_ARCHIVED);
						mrdRecordDtlVO.setPutBy(fb.getPutBy());
						mrdRecordDtlVO.setChangeArchivedFlag(MrdConfig.NO);
					}
				}
				else		//Record in Mrd(Not Archived)
				{
					mrdRecordDtlVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_IN_MRD);
					mrdRecordDtlVO.setRackId("");
					mrdRecordDtlVO.setShelfId("");
					mrdRecordDtlVO.setPutBy("");
					mrdRecordDtlVO.setChangeArchivedFlag(MrdConfig.YES);
				}
				
				mrdRecordDtlVOList.add(mrdRecordDtlVO);
			}
			
			
			
			if(fb.getHiddenReqStatus().equals(MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS) && fb.getSelectedRecord().length==mrdRecordIssuedList.size())
			{
				isRequestReturn=MrdConfig.YES;
			}
			
			MrdRecordReturnDATA.saveMrdRecordReturnDetail(mrdRecordReturnDtlList,mrdRecordDtlVOList,isRequestReturn,mrdRackShelfChangeList,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Returned");
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
}
