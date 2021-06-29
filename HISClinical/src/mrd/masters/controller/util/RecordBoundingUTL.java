package mrd.masters.controller.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.masters.controller.data.RecordBoundingDATA;
import mrd.masters.controller.fb.RecordBoundingFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.RecordBoundingVO;

public class RecordBoundingUTL extends ControllerUTIL
{
	public static void getMrdBoundingEssential(RecordBoundingFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			Map map= RecordBoundingDATA.getMrdBoundingEssential(getUserVO(request));
			WebUTIL.setMapInSession(map, request);
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
	
	public static void getMrdBoundedRecordType(RecordBoundingFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		String strBoundedRecordType="";
		List lst=new ArrayList();
		List lstRecordType=new ArrayList();
		
		try
		{
			if(!fb.getBoundingMode().equals(MrdConfig.RECORD_BOUNDING_MODE_MRD_WISE))
			{
				String boundingMode="";
				String boundingId="";
				
				if(fb.getBoundingMode().equals(MrdConfig.RECORD_BOUNDING_MODE_RACK_WISE))
				{
					boundingId=fb.getMrdCodeRack();
					boundingMode=MrdConfig.RECORD_BOUNDING_MODE_MRD_WISE;
				}
				if(fb.getBoundingMode().equals(MrdConfig.RECORD_BOUNDING_MODE_SHELF_WISE))
				{
					boundingId=fb.getRackIdShelf();
					boundingMode=MrdConfig.RECORD_BOUNDING_MODE_RACK_WISE;
				}
				lstRecordType=RecordBoundingDATA.getBoundedRecordType(boundingMode,boundingId,getUserVO(request));
				WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_TYPE_FOR_BOUNDING_LIST, lstRecordType);
			}
			else
			{
				lstRecordType=(List)session.getAttribute(MrdConfig.RECORD_TYPE);
				WebUTIL.setAttributeInSession(request, MrdConfig.RECORD_TYPE_FOR_BOUNDING_LIST, lstRecordType);
			}
			if(fb.getBoundingMode().equals(MrdConfig.RECORD_BOUNDING_MODE_MRD_WISE))
				lst=RecordBoundingDATA.getBoundedRecordType(fb.getBoundingMode(),fb.getMrdCode(),getUserVO(request));
			
			if(fb.getBoundingMode().equals(MrdConfig.RECORD_BOUNDING_MODE_RACK_WISE))
				lst=RecordBoundingDATA.getBoundedRecordType(fb.getBoundingMode(),fb.getRackId(),getUserVO(request));
			
			if(fb.getBoundingMode().equals(MrdConfig.RECORD_BOUNDING_MODE_SHELF_WISE))
				lst=RecordBoundingDATA.getBoundedRecordType(fb.getBoundingMode(),fb.getShelfId(),getUserVO(request));
			
			if(lst.size()>0)
			{
				for(int i=0;i<lst.size();i++)
				{
					Entry ent=(Entry)lst.get(i);
					strBoundedRecordType=strBoundedRecordType+ent.getValue()+"@";
				}
				if(strBoundedRecordType.length()>0)strBoundedRecordType=strBoundedRecordType.substring(0,strBoundedRecordType.length()-1);
				
			}
			
			fb.setMrdBoundedRecType(strBoundedRecordType);
			
			if(lstRecordType.size()==0)
			{
				fb.setIsRecordTypeExist(MrdConfig.NO);
				objStatus.add(Status.LIST,"","No Record Type Found To Bound");
			}	
			else
			{
				fb.setIsRecordTypeExist(MrdConfig.YES);
				objStatus.add(Status.LIST);
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
	
	public static void getRackBasedOnMrd(RecordBoundingFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		List rackList=new ArrayList();
		String mrdCode="";
		
		try
		{
			if(fb.getBoundingMode().equals(MrdConfig.RECORD_BOUNDING_MODE_RACK_WISE))
				mrdCode=fb.getMrdCodeRack();
			if(fb.getBoundingMode().equals(MrdConfig.RECORD_BOUNDING_MODE_SHELF_WISE))
				mrdCode=fb.getMrdCodeShelf();
				
			rackList=RecordBoundingDATA.getRackBasedOnMrd(mrdCode,getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ALL_RACK_LIST_BASED_ON_MRD_CODE, rackList);
			
			if(rackList.size()==0)
			{
				WebUTIL.setAttributeInSession(request, MrdConfig.ALL_SHELF_LIST_BASED_ON_RACK, new ArrayList());
				objStatus.add(Status.DONE,"","No Rack Found");
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
	
	public static void getShelfBasedOnRack(RecordBoundingFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		List shelfList=new ArrayList();
		
		
		try
		{
			shelfList=RecordBoundingDATA.getShelfBasedOnRack(fb.getRackIdShelf(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, MrdConfig.ALL_SHELF_LIST_BASED_ON_RACK, shelfList);
			
			if(shelfList.size()==0)
				objStatus.add(Status.DONE,"","No Shelf Found");
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
	
	public static void saveRecordBoundingDetail(RecordBoundingFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List<RecordBoundingVO> lstRecBound=new ArrayList<RecordBoundingVO>();
		
		try
		{
			for(int i=0;i<fb.getRecordType().length;i++)
			{	
				RecordBoundingVO recordBoundingVO=new RecordBoundingVO();
				recordBoundingVO.setRecordType(fb.getRecordType()[i]);
				if(fb.getBoundingMode().equals(MrdConfig.RECORD_BOUNDING_MODE_MRD_WISE))
				{
					recordBoundingVO.setBoundingMode(fb.getBoundingMode());
					recordBoundingVO.setBoundingId(fb.getMrdCode());
				}
				if(fb.getBoundingMode().equals(MrdConfig.RECORD_BOUNDING_MODE_RACK_WISE))
				{
					recordBoundingVO.setBoundingMode(fb.getBoundingMode());
					recordBoundingVO.setBoundingId(fb.getRackId());
				}
				if(fb.getBoundingMode().equals(MrdConfig.RECORD_BOUNDING_MODE_SHELF_WISE))
				{
					recordBoundingVO.setBoundingMode(fb.getBoundingMode());
					recordBoundingVO.setBoundingId(fb.getShelfId());
				}
				lstRecBound.add(recordBoundingVO);
			}	
			
			RecordBoundingDATA.saveRecordBoundingDetail(lstRecBound,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Saved Successfully");
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
