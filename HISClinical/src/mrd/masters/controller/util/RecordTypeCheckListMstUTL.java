package mrd.masters.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.masters.controller.data.RecordTypeCheckListMstDATA;
import mrd.masters.controller.fb.RecordTypeCheckListMstFB;

public class RecordTypeCheckListMstUTL extends ControllerUTIL
{
	public static void getEssentialForReqPurposeMst(RecordTypeCheckListMstFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		Map mp=new HashMap();
		List allRecordTypeList=null;
		try
		{
			if(fb.getControls()[0]!=null)
			{
				fb.setRecordTypeId(fb.getControls()[0]);
			}
				
			
			mp=RecordTypeCheckListMstDATA.getEssentialForRecordTypeCheckListMst((getUserVO(request)));
			allRecordTypeList=(List)mp.get(MrdConfig.ALL_RECORD_TYPE_LIST_FOR_MRD);
			
			if(allRecordTypeList!=null)
			{
				for(int i=0;i<allRecordTypeList.size();i++)
				{
					Entry obj=(Entry)allRecordTypeList.get(i);
					
					if(obj.getValue().equals(fb.getRecordTypeId()))
					{
						fb.setRecordTypeName(obj.getLabel());
						break;
					}
					
				}
			}
			
			fb.setCheckListMode("-1");
			
			
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
	
	public static void getAddedCheckListByRecordType(RecordTypeCheckListMstFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		Map mp=new HashMap();
		
		try
		{
			RecordTypeCheckListMstVO recordTypeCheckLstVO=new RecordTypeCheckListMstVO();
			
			recordTypeCheckLstVO.setRecordTypeId(fb.getRecordTypeId());
			recordTypeCheckLstVO.setCheckListMode(fb.getCheckListMode());
			
			mp=RecordTypeCheckListMstDATA.getAllAddedCheckListByRecordType(recordTypeCheckLstVO, getUserVO(request));
			
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
	
	public static void getCheckListListNotMapped(RecordTypeCheckListMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstCheckListNotMap=new ArrayList();
		List lstAddedCheckList=new ArrayList();
		List lstAddedCheckLstForPopUp=new ArrayList();
		HttpSession session=WebUTIL.getSession(_request);
		try
		{
			RecordTypeCheckListMstVO recordTypeCheckLstVO=new RecordTypeCheckListMstVO();
			
			recordTypeCheckLstVO.setRecordTypeId(fb.getRecordTypeId());
			recordTypeCheckLstVO.setCheckListMode(fb.getCheckListMode());
			
			lstCheckListNotMap=RecordTypeCheckListMstDATA.getCheckListNotMapped(recordTypeCheckLstVO,getUserVO(_request));
			WebUTIL.setAttributeInSession(_request, MrdConfig.LIST_NOT_MAPPED_CHECK_LIST, lstCheckListNotMap);
		
			lstAddedCheckList=(List)session.getAttribute(MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE);
			
			for(int i=0;i<lstAddedCheckList.size();i++)
			{
				RecordTypeCheckListMstVO vo=(RecordTypeCheckListMstVO)lstAddedCheckList.get(i);
				Entry obj=new Entry();
				obj.setLabel(vo.getChecklistName());
				obj.setValue(vo.getChecklistId());
				lstAddedCheckLstForPopUp.add(obj);
			}
			
			
			WebUTIL.setAttributeInSession(_request, MrdConfig.LIST_NEW_ADDEDD_CHECKLIST_VO, lstAddedCheckLstForPopUp);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "e.printStackTrace()", "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}
	
	
	public static void populateSelectedCheckList(RecordTypeCheckListMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List allCheckListList=new ArrayList();
		List newCheckListList=new ArrayList();
		List lstAddedCheckList=new ArrayList();
		List VoListWithIsCompulsory=new ArrayList();
		HttpSession session=WebUTIL.getSession(_request);
		try
		{
			
			lstAddedCheckList=(List)session.getAttribute(MrdConfig.ALL_ADDED_CHECKLIST_LIST_BY_RECORDTYPE);
			
			if(fb.getConcatedIndex()!=null || !fb.getConcatedIndex().equals(""))
			{
				allCheckListList=(List)session.getAttribute(MrdConfig.ALL_CHECKLIST_LIST);
				
				String[] checkListId=fb.getConcatedIndex().split("@");
				for(int i=0;i<checkListId.length;i++)
				{
					for(int j=0;j<allCheckListList.size();j++)
					{
						Entry ent=(Entry)allCheckListList.get(j);
						if(checkListId[i].equals(ent.getValue()))
						{
							RecordTypeCheckListMstVO recordTypeCheckLstVO=new RecordTypeCheckListMstVO();
							recordTypeCheckLstVO.setChecklistId(ent.getValue());
							recordTypeCheckLstVO.setChecklistName(ent.getLabel());
							
							newCheckListList.add(recordTypeCheckLstVO);
						}
					}
				}
				
				
				for(int i=0;i<newCheckListList.size();i++)
				{
					RecordTypeCheckListMstVO recordTypeCheckLstVO=(RecordTypeCheckListMstVO)newCheckListList.get(i);
					Boolean flag=false;
					
					for(int j=0;j<lstAddedCheckList.size();j++)
					{
						
						
						RecordTypeCheckListMstVO addedRecordTypeCheckLstVO=(RecordTypeCheckListMstVO)lstAddedCheckList.get(j);
						if(recordTypeCheckLstVO.getChecklistId()!=null && addedRecordTypeCheckLstVO.getChecklistId()!=null)
						{
							if(recordTypeCheckLstVO.getChecklistId().equals(addedRecordTypeCheckLstVO.getChecklistId()))
							{
								recordTypeCheckLstVO.setIsCompulsory(addedRecordTypeCheckLstVO.getIsCompulsory());
								flag=true;
								break;
							}
						}
					}
					
					if(!flag)
					{
						recordTypeCheckLstVO.setIsCompulsory("-1");
					}
					
					VoListWithIsCompulsory.add(recordTypeCheckLstVO);
				}
								
				WebUTIL.setAttributeInSession(_request, MrdConfig.LIST_NEW_ADDEDD_CHECKLIST_VO, VoListWithIsCompulsory);
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
	public static boolean saveRecordTypeCheckListMst(RecordTypeCheckListMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		HttpSession session=WebUTIL.getSession(_request);
		List recordTypeCheckListVOLst=new ArrayList();
		List addedCheckListVOLst=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			RecordTypeCheckListMstVO recordTypeCheckLstVO=null;
			
			addedCheckListVOLst=(List)session.getAttribute(MrdConfig.LIST_NEW_ADDEDD_CHECKLIST_VO);
			
			for(Integer i=0;i<addedCheckListVOLst.size();i++)
			{
				recordTypeCheckLstVO=(RecordTypeCheckListMstVO)addedCheckListVOLst.get(i);
				
				Integer displayOrder=i+1;
				
				recordTypeCheckLstVO.setRecordTypeId(_fb.getRecordTypeId());
				recordTypeCheckLstVO.setCheckListMode(_fb.getCheckListMode());
				recordTypeCheckLstVO.setIsCompulsory(_fb.getIsCompulsory()[i]);
				recordTypeCheckLstVO.setDisplayOrder(displayOrder.toString());
				recordTypeCheckListVOLst.add(recordTypeCheckLstVO);
			}
			
			hasFlag=RecordTypeCheckListMstDATA.saveRecordTypeCheckListMst(recordTypeCheckListVOLst, userVO);
			
			 objStatus.add(Status.INPROCESS, "Record Added Successfully", "");

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
			objStatus.add(Status.ERROR, "", "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	public static boolean getDataForModify(RecordTypeCheckListMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		
		//List allRecordTypeCheckListVOLst=null;	
		HttpSession session=WebUTIL.getSession(_request);
		Map mp=new HashMap();
		List allRecordTypeList=new ArrayList();
		try
		{
			//UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sRecordTypeId = concatid[0];
			//String sCheckListId = concatid[1];
			String sCheckListMode = concatid[2];
			//String shospitalCode = concatid[3];
			//String sSlNo = concatid[4];
			
			
			RecordTypeCheckListMstVO recordTypeCheckLstVO=new RecordTypeCheckListMstVO();
			
			fb.setRecordTypeId(sRecordTypeId);
			recordTypeCheckLstVO.setRecordTypeId(sRecordTypeId);
			recordTypeCheckLstVO.setCheckListMode(sCheckListMode);
			
			mp=RecordTypeCheckListMstDATA.getAllAddedCheckListByRecordType(recordTypeCheckLstVO, getUserVO(_request));
			
			
			WebUTIL.setMapInSession(mp, _request);
			
			fb.setCheckListMode(sCheckListMode);
			mp=RecordTypeCheckListMstDATA.getEssentialForRecordTypeCheckListMst((getUserVO(_request)));
			allRecordTypeList=(List)mp.get(MrdConfig.ALL_RECORD_TYPE_LIST_FOR_MRD);
			
			if(allRecordTypeList!=null)
			{
				for(int i=0;i<allRecordTypeList.size();i++)
				{
					Entry obj=(Entry)allRecordTypeList.get(i);
					
					if(obj.getValue().equals(sRecordTypeId))
					{
						fb.setRecordTypeName(obj.getLabel());
						break;
					}
					
				}
			}
			if(recordTypeCheckLstVO.getRecordTypeName()!=null)
			fb.setRecordTypeName(recordTypeCheckLstVO.getRecordTypeName());
			
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
	
}
