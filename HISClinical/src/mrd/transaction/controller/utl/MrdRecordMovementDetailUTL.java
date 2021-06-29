package mrd.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MrdRackShelfChangeDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.RackMstVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.MrdRecordMovementDetailDATA;
import mrd.transaction.controller.fb.MrdRecordMovementDetailFB;

public class MrdRecordMovementDetailUTL extends ControllerUTIL
{
	public static void getAllRecordTypeList(MrdRecordMovementDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			List lstRecordType=MrdRecordMovementDetailDATA.getAllRecordTypeList(getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.RECORD_TYPE, lstRecordType);
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
	
	public static boolean getMrdList(MrdRecordMovementDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		boolean flag = true;
		
		try
		{
			Map mp=MrdRecordMovementDetailDATA.getEssentialForRecordMovement(fb.getRecordType(),getUserVO(request));
			WebUTIL.setMapInSession(mp, request);
		//	List lstMrd=MrdRecordMovementDetailDATA.getMrdBasedOnRecordTypeList(fb.getRecordType(),getUserVO(request));
		//	WebUTIL.setAttributeInSession(request,MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE, lstMrd);
			List lstMrd=(List)mp.get(MrdConfig.LIST_MRD_BASED_ON_RECORD_TYPE);
			if(lstMrd.size()==1)
			{
				Entry entry = (Entry) lstMrd.get(0);
				String mrdCode = entry.getValue();
				fb.setFromMrdCode(mrdCode);
				fb.setToMrdCode(mrdCode);
				fb.setIsMrdListOne(MrdConfig.YES);
				flag=false;
				
			}	
			else
			{
				fb.setIsMrdListOne(MrdConfig.NO);
				flag=true;
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
		return flag;
	}
	
	public static void getFromNToRackBasedOnMrd(MrdRecordMovementDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		RackMstVO[] fromRackMstVO=null;
		
		
		try
		{
			fromRackMstVO=MrdRecordMovementDetailDATA.getRackBasedOnMrdList(fb.getRecordType(),fb.getFromMrdCode(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.FROM_RACK_LIST_BASED_ON_MRD, fromRackMstVO);
			WebUTIL.setAttributeInSession(request, MrdConfig.TO_RACK_LIST_BASED_ON_MRD, fromRackMstVO);
			WebUTIL.setAttributeInSession(request, MrdConfig.FROM_SHELF_LIST_BASED_ON_RACK, new ArrayList());
			WebUTIL.setAttributeInSession(request, MrdConfig.TO_SHELF_LIST_BASED_ON_RACK, new ArrayList());
			if(fromRackMstVO==null)
			{
				objStatus.add(Status.TRANSINPROCESS,"","No Rack Found");
				objStatus.add(Status.DONE,"","No Rack Found");
			}
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
	
	public static void getFromRackBasedOnMrdList(MrdRecordMovementDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		RackMstVO[] fromRackMstVO=null;
		
		try
		{
			fromRackMstVO=MrdRecordMovementDetailDATA.getRackBasedOnMrdList(fb.getRecordType(),fb.getFromMrdCode(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.FROM_RACK_LIST_BASED_ON_MRD, fromRackMstVO);
			if(fromRackMstVO==null)
			{
				WebUTIL.setAttributeInSession(request, MrdConfig.FROM_SHELF_LIST_BASED_ON_RACK, new ArrayList());
				fb.setFromShelfId("-1");
				objStatus.add(Status.TRANSINPROCESS,"","No From Rack Found");
			}	
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
	
	public static void getFromShelfBasedOnRackList(MrdRecordMovementDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			List lstFromShelf=MrdRecordMovementDetailDATA.getShelfBasedOnRack(fb.getRecordType(),fb.getFromRackId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.FROM_SHELF_LIST_BASED_ON_RACK, lstFromShelf);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(request, MrdConfig.FROM_SHELF_LIST_BASED_ON_RACK, new ArrayList());
			fb.setFromShelfId("-1");
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
	
	public static void getToRackBasedOnMrdList(MrdRecordMovementDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		RackMstVO[] toRackMstVO=null;
		String str="";
		
		try
		{
			if(fb.getSelectedMrdRecord()!=null)
			{
				for(int i=0;i<fb.getSelectedMrdRecord().length;i++)
				{
					str=str+fb.getSelectedMrdRecord()[i]+"@";
				}
				if(str.length()>0)str=str.substring(0,str.length()-1);
				fb.setTempChkValue(str);
			}
			
			toRackMstVO=MrdRecordMovementDetailDATA.getRackBasedOnMrdList(fb.getRecordType(),fb.getToMrdCode(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.TO_RACK_LIST_BASED_ON_MRD, toRackMstVO);
			if(toRackMstVO==null)
			{
				WebUTIL.setAttributeInSession(request, MrdConfig.TO_RACK_LIST_BASED_ON_MRD, new ArrayList());
				WebUTIL.setAttributeInSession(request, MrdConfig.TO_SHELF_LIST_BASED_ON_RACK, new ArrayList());
				objStatus.add(Status.TRANSINPROCESS,"","No To Rack Found");
			}	
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
	
	public static void getToShelfBasedOnRackList(MrdRecordMovementDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		String str="";
		
		try
		{
			if(fb.getSelectedMrdRecord()!=null)
			{
				for(int i=0;i<fb.getSelectedMrdRecord().length;i++)
				{
					str=str+fb.getSelectedMrdRecord()[i]+"@";
				}
				if(str.length()>0)str=str.substring(0,str.length()-1);
				fb.setTempChkValue(str);
			}
			
			List lstFromShelf=MrdRecordMovementDetailDATA.getShelfBasedOnRack(fb.getRecordType(),fb.getToRackId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.TO_SHELF_LIST_BASED_ON_RACK, lstFromShelf);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(request, MrdConfig.TO_SHELF_LIST_BASED_ON_RACK, new ArrayList());
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
	
	public static void getMrdRecordBasedOnShelfList(MrdRecordMovementDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		MrdRecordDtlVO[] mrdRecordDtlVO=null;
		
		try
		{
			mrdRecordDtlVO=MrdRecordMovementDetailDATA.getMrdRecordBasedOnShelfList(fb.getRecordType(),fb.getFromShelfId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request,MrdConfig.ARR_MRD_RECORD_TO_BE_MOVED , mrdRecordDtlVO);
			if(mrdRecordDtlVO==null)
				fb.setRecordExistFlag(MrdConfig.NO);
			else
				fb.setRecordExistFlag(MrdConfig.YES);
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
	
	public static void saveRecordMovementDetail(MrdRecordMovementDetailFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		List<MrdRackShelfChangeDtlVO> lstMrdRackShelfChangeVO=new ArrayList<MrdRackShelfChangeDtlVO>();
		
		try
		{
			if(fb.getRecordSelectionFlag().equals(MrdConfig.RECORD_SELECTION_ALL))
			{
				MrdRecordDtlVO[] mrdRecordDtlVO=(MrdRecordDtlVO[])session.getAttribute(MrdConfig.ARR_MRD_RECORD_TO_BE_MOVED);																			
				for(int i=0;i<mrdRecordDtlVO.length;i++)
				{
					if(fb.getMovementOptionFlag().equals(MrdConfig.DESTROY_SELECTED))
					{
						String entryDate= mrdRecordDtlVO[i].getRecordEntryDate();
						String [] date= entryDate.split(" ");
						if((date[1].contains("year")) && (Integer.parseInt(date[0])>=4))
						{	
							System.out.println("DATE in UTIL :"+date[0]);
							MrdRackShelfChangeDtlVO mrdRackShelfChangeVO=new MrdRackShelfChangeDtlVO();
							HelperMethods.populate(mrdRackShelfChangeVO, fb);
							mrdRackShelfChangeVO.setMrdRecordId(mrdRecordDtlVO[i].getMrdRecordId());
							mrdRackShelfChangeVO.setEntryMode(MrdConfig.MRD_RACKSHELF_CHANGE_ENTRY_MODE_BY_MRD);
							mrdRackShelfChangeVO.setMovementOptionFlag(fb.getMovementOptionFlag());
							
							lstMrdRackShelfChangeVO.add(mrdRackShelfChangeVO);							
						}
					}
					else if(fb.getMovementOptionFlag().equals(MrdConfig.MOVEMENT_SELECTED))
					{					
						MrdRackShelfChangeDtlVO mrdRackShelfChangeVO=new MrdRackShelfChangeDtlVO();
						HelperMethods.populate(mrdRackShelfChangeVO, fb);
						mrdRackShelfChangeVO.setMrdRecordId(mrdRecordDtlVO[i].getMrdRecordId());
						mrdRackShelfChangeVO.setEntryMode(MrdConfig.MRD_RACKSHELF_CHANGE_ENTRY_MODE_BY_MRD);
						mrdRackShelfChangeVO.setMovementOptionFlag(fb.getMovementOptionFlag());
						
						lstMrdRackShelfChangeVO.add(mrdRackShelfChangeVO);
					}
				}
			}
			else
			{
				for(int i=0;i<fb.getSelectedMrdRecord().length;i++)
				{
					MrdRackShelfChangeDtlVO mrdRackShelfChangeVO=new MrdRackShelfChangeDtlVO();
					HelperMethods.populate(mrdRackShelfChangeVO, fb);
					mrdRackShelfChangeVO.setMrdRecordId(fb.getSelectedMrdRecord()[i]);
					mrdRackShelfChangeVO.setEntryMode(MrdConfig.MRD_RACKSHELF_CHANGE_ENTRY_MODE_BY_MRD);
					mrdRackShelfChangeVO.setMovementOptionFlag(fb.getMovementOptionFlag());
					
					lstMrdRackShelfChangeVO.add(mrdRackShelfChangeVO);
				}
			}
			
			MrdRecordMovementDetailDATA.saveRecordMovementDetail(lstMrdRackShelfChangeVO,getUserVO(request));
			if(fb.getMovementOptionFlag().equals("1")){objStatus.add(Status.DONE,"","Record Moved Successfully");}
			else{objStatus.add(Status.DONE,"","Record Destroyed Successfully");}			
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
