package mrd.masters.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.RequestPurposeMstVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.masters.controller.data.RequetPurposeMasterDATA;
import mrd.masters.controller.fb.ReqPurposeMasterFB;

public class RequestPurposeMasterUTL extends ControllerUTIL
{
	
	public static void getEssentialForReqPurposeMst(ReqPurposeMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		Map mp=new HashMap();
		List allRecordTypeList=null;
		try
		{
			if(fb.getControls()[0]!=null)
			{
				fb.setRecordType(fb.getControls()[0]);
			}
				
			
			mp=RequetPurposeMasterDATA.getEssentialForReqPurposeMst(getUserVO(request));
			allRecordTypeList=(List)mp.get(MrdConfig.ALL_RECORD_TYPE_LIST_FOR_MRD);
			
			if(allRecordTypeList!=null)
			{
				for(int i=0;i<allRecordTypeList.size();i++)
				{
					Entry obj=(Entry)allRecordTypeList.get(i);
					
					if(obj.getValue().equals(fb.getRecordType()))
					{
						fb.setRecordTypeDesc(obj.getLabel());
						break;
					}
					
				}
			}
			
			
			
			WebUTIL.setMapInSession(mp, request);
		
		}
		catch(HisRecordNotFoundException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR," ","");			
		}
		catch(HisDataAccessException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
		}		
		catch(Exception e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in UTIL","");
		}		
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	
	public static boolean saveReqPurposeDetail(ReqPurposeMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			RequestPurposeMstVO reqPurposeMstVO = new RequestPurposeMstVO();
			
			reqPurposeMstVO.setRecordType(_fb.getRecordType());
			reqPurposeMstVO.setPurpose(_fb.getReqPurpose().trim());
			reqPurposeMstVO.setPriority(_fb.getPriority());
			reqPurposeMstVO.setSlNo("1");
			
			hasFlag=RequetPurposeMasterDATA.saveReqPurposeMstDetail(reqPurposeMstVO, userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
			 }else
			 {	hasFlag=false;
				 objStatus.add(Status.NEW, "The Request Purpose Already Exists", "");
			 }

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			hasFlag=false;
			objStatus.add(Status.ERROR, "", "Request Purpose already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	public static boolean getDataForModify(ReqPurposeMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RequestPurposeMstVO reqPurposeMstVO = new RequestPurposeMstVO();
		List allRecordTypeList=null;	
		//HttpSession session=WebUTIL.getSession(_request);
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sReqPurposeId = concatid[0];
			String sReqPurposeSlno = concatid[1];
			String shospitalCode = concatid[2];
			
			// putting the selected Record Primary Key into Vo
			reqPurposeMstVO.setSlNo(sReqPurposeSlno);
			reqPurposeMstVO.setReqPurposeId(sReqPurposeId);
			reqPurposeMstVO.setHospitalCode(shospitalCode);
			
						
			mp = RequetPurposeMasterDATA.getDataForModifyReqPurposeMst(reqPurposeMstVO, userVO);
			
			reqPurposeMstVO=(RequestPurposeMstVO)mp.get(MrdConfig.REQUEST_PURPOSE_MST_VO_FOR_MODIFY);
		    
			fb.setPriority(reqPurposeMstVO.getPriority());
			fb.setRecordType(reqPurposeMstVO.getRecordType());
			fb.setReqPurpose(reqPurposeMstVO.getPurpose());
			fb.setReqPurposeId(reqPurposeMstVO.getReqPurposeId());
			fb.setSlNo(reqPurposeMstVO.getSlNo());
			fb.setIsActive(reqPurposeMstVO.getIsActive());
			
			
			
			allRecordTypeList=(List)mp.get(MrdConfig.ALL_RECORD_TYPE_LIST_FOR_MRD);
			
			if(allRecordTypeList!=null)
			{
				for(int i=0;i<allRecordTypeList.size();i++)
				{
					Entry obj=(Entry)allRecordTypeList.get(i);
					
					if(obj.getValue().equals(reqPurposeMstVO.getRecordType()))
					{
						fb.setRecordTypeDesc(obj.getLabel());
						break;
					}
					
				}
			}
			
			
			
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}
	
	public static boolean saveModReqPurposeMaster(ReqPurposeMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			RequestPurposeMstVO reqPurposeMstVO = new RequestPurposeMstVO();

			reqPurposeMstVO.setRecordType(_fb.getRecordType());
			reqPurposeMstVO.setPurpose(_fb.getReqPurpose().trim());
			reqPurposeMstVO.setPriority(_fb.getPriority());
			reqPurposeMstVO.setReqPurposeId(_fb.getReqPurposeId());
			reqPurposeMstVO.setSlNo(_fb.getSlNo());
			reqPurposeMstVO.setIsActive(_fb.getIsActive());
			
			flag=RequetPurposeMasterDATA.saveModReqPurposeMaster(reqPurposeMstVO, userVO);
			if(flag){
				objStatus.add(Status.DONE,"","Record Modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Request Purpose already exists");
				
			}
			
		}
		
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Request Purpose already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}
	
}
