package mrd.transaction.controller.utl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.RecordFoundInMrdDATA;
import mrd.transaction.controller.fb.RecordFoundInMrdFB;

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
import hisglobal.vo.MrdRecordLostFoundDtlVO;
import hisglobal.vo.RackMstVO;

public class RecordFoundInMrdUTL extends ControllerUTIL
{
	public static void getLostRecordInMrdList(RecordFoundInMrdFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			setSysdate(request); // Required for Date Checking 
			MrdRecordLostFoundDtlVO[] mrdRecordLostListVO=RecordFoundInMrdDATA.getLostRecordInMrdList(getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_MRD_LOST_RECORD_LIST_VO, mrdRecordLostListVO);
			objStatus.add(Status.LIST);
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
	
	public static void getFoundEssentialDtl(RecordFoundInMrdFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		String str="";
		
		try
		{
			setSysdate(request);
			String sys=(String)session.getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			fb.setHiddenTimeHr(time.split(":")[0]);
			fb.setHiddenTimeMin(time.split(":")[1]);
			
			Map map=RecordFoundInMrdDATA.getFoundEssentialDtl(fb.getSelectedMrdRecordId(),fb.getRecordType(),fb.getMrdCode(),getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			
			str=(String)map.get(MrdConfig.STRING_RECORD_PREVIOUS_LOCATION);
			fb.setPrevLocation(str.split("@")[0]);
			fb.setPrevLocationCode(str.split("@")[1]);
			fb.setIsPrevLocationExist(str.split("@")[2]);
			RackMstVO[] rackMstVO=(RackMstVO[])map.get(MrdConfig.LIST_OF_RACK_BASED_ON_MRD);
			if(rackMstVO!=null)
				objStatus.add(Status.TRANSINPROCESS);
			else
				objStatus.add(Status.TRANSINPROCESS,"","No Rack Found");
			
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
	
	public static void getShelfBasedOnRack(RecordFoundInMrdFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String rackId=fb.getRackId();
		String rackInfo="";
		String recordType="";
		
		try
		{
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
			List lstShelf=RecordFoundInMrdDATA.getShelfBasedOnRack(recordType,rackId,getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.LIST_OF_SHELF_BASED_ON_RACK , lstShelf);
			
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
	
	@SuppressWarnings("finally")
	public static boolean saveRecordFoundNArchivalDetail(RecordFoundInMrdFB fb,HttpServletRequest request)
	{
		boolean flag=true;
		Status objStatus = new Status();
		//HttpSession session=request.getSession();
		MrdRecordLostFoundDtlVO mrdFoundVO=new MrdRecordLostFoundDtlVO();
		MrdRecordDtlVO mrdRecordDtlVO=new MrdRecordDtlVO();
		
		try
		{
			HelperMethods.populate(mrdFoundVO, fb);
			mrdFoundVO.setMrdRecordId(fb.getSelectedMrdRecordId());
			mrdFoundVO.setFoundDateTime(fb.getFoundDate()+" "+fb.getFoundTimeHr()+":"+fb.getFoundTimeMin());
			
			mrdRecordDtlVO.setMrdRecordId(fb.getSelectedMrdRecordId());
			if(fb.getRecAcceptArchivedFlag()!=null)		//For Record Archival
			{
				if(fb.getChangeArchivedFlag().equals(MrdConfig.YES))	//Changed Archived
				{
					mrdRecordDtlVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_ARCHIVED);
					mrdRecordDtlVO.setRackId(fb.getRackId());
					mrdRecordDtlVO.setShelfId(fb.getShelfId());
					mrdRecordDtlVO.setPutBy(fb.getPutBy());
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
			
			RecordFoundInMrdDATA.saveRecordFoundNArchivalDetail(mrdFoundVO,mrdRecordDtlVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Found Detail Saved Successfully");
			
			
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
			return flag;
		}
	}
	
}
