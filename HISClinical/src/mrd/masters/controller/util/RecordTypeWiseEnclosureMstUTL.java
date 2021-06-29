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

import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;

import mrd.masters.controller.data.RecordTypeWiseEnclosureDATA;
import mrd.masters.controller.fb.RecordTypeWiseEnclosureMstFB;

public class RecordTypeWiseEnclosureMstUTL extends ControllerUTIL
{
	
	public static void getRecordTypeName(RecordTypeWiseEnclosureMstFB _fb, HttpServletRequest _request)
	 {
		Status objStatus = new Status();
		
		try
		{
			setSysdate(_request);
			if(_fb.getControls()[0]!=null && !_fb.getControls()[0].equals("-1"))
			{
				_fb.setHiddenControl(_fb.getControls()[0]);
			}
			
			String recordTypeID=_fb.getHiddenControl();
			Map map=RecordTypeWiseEnclosureDATA.getEssentialForEnclosureMapping(recordTypeID,getUserVO(_request));
			WebUTIL.setMapInSession(map, _request);
			
			
			 String recordTypeName=(String)map.get(MrdConfig.RECORD_TYPE_NAME);
			_fb.setRecordTypeName(recordTypeName);
			_fb.setRecordTypeId(recordTypeID);
			
			objStatus.add(Status.LIST);
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
	
	
	public static void getEnclosureRecordListNotMapped(RecordTypeWiseEnclosureMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//HttpSession session =_request.getSession();
		List lstEncNotMap=new ArrayList();
		//RecordTypeWiseEnclosureMstVO[] addedEnclosureVO=null;
		
		try
		{
			lstEncNotMap=RecordTypeWiseEnclosureDATA.getEnclosureRecordListNotMapped(fb.getRecordTypeId(),getUserVO(_request));
			WebUTIL.setAttributeInSession(_request, MrdConfig.LIST_NOT_MAPPED_ENCLOSURE, lstEncNotMap);
			
			//addedEnclosureVO=(RecordTypeWiseEnclosureMstVO[])session.getAttribute(MrdConfig.ARR_ADDED_RECORD_TYPE_ENCLOSURE_VO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(_request, MrdConfig.LIST_NOT_MAPPED_ENCLOSURE, new ArrayList());
			objStatus.add(Status.UNSUCESSFULL, "", "");
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
	
	public static void populateSelectedEnclosure(RecordTypeWiseEnclosureMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		List lstEncNotMap=new ArrayList();
		List<RecordTypeWiseEnclosureMstVO> lstNewAddEnc=new ArrayList<RecordTypeWiseEnclosureMstVO>();
		RecordTypeWiseEnclosureMstVO[] addedEnclosureVO=null;
		List allEnclosureList=new ArrayList();
		
		
		try
		{
			addedEnclosureVO=(RecordTypeWiseEnclosureMstVO[])session.getAttribute(MrdConfig.ARR_ADDED_RECORD_TYPE_ENCLOSURE_VO);
			if(addedEnclosureVO!=null)
			{
				for(int i=0;i<addedEnclosureVO.length;i++)
				{
					Entry ent=new Entry();
					ent.setLabel(addedEnclosureVO[i].getEnclosure());
					ent.setValue(addedEnclosureVO[i].getEnclosureId()+"@"+addedEnclosureVO[i].getIsCompulsory());
					allEnclosureList.add(ent);
				}
			}	
			
			lstEncNotMap=(List)session.getAttribute(MrdConfig.LIST_NOT_MAPPED_ENCLOSURE);
			if(lstEncNotMap.size()>0)
			{
				for(int i=0;i<lstEncNotMap.size();i++)
				{
					Entry ent=(Entry)lstEncNotMap.get(i);
					ent.setValue(ent.getValue()+"@"+"-1");
					allEnclosureList.add(ent);
				}
			}
			if(fb.getConcatedIndex()!=null || !fb.getConcatedIndex().equals(""))
			{
			
				
				String[] enclosureId=fb.getConcatedIndex().split("@");
				for(int i=0;i<enclosureId.length;i++)
				{
					for(int j=0;j<allEnclosureList.size();j++)
					{
						Entry ent=(Entry)allEnclosureList.get(j);
						if(enclosureId[i].equals(ent.getValue().split("@")[0]))
						{
							RecordTypeWiseEnclosureMstVO newEnclosureVO=new RecordTypeWiseEnclosureMstVO();
							newEnclosureVO.setEnclosureId(ent.getValue());
							newEnclosureVO.setEnclosure(ent.getLabel());
							newEnclosureVO.setIsCompulsory(ent.getValue().split("@")[1]);
							lstNewAddEnc.add(newEnclosureVO);
						}
					}
				}
				
				WebUTIL.setAttributeInSession(_request, MrdConfig.LIST_NEW_ADDEDD_ENCLOSURE_VO, lstNewAddEnc);
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

	/*public static boolean getEnclosureRecord(RecordTypeWiseEnclosureMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO = new RecordTypeWiseEnclosureMstVO();
		Map mp = new HashMap();
		List recordTypeLst = new ArrayList();
		List compulsoryLst=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sEnclosureID = concatid[0];
			String sSlNo = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo

			_fb.setEnclosureId(sEnclosureID);
			_fb.setHospitalCode(sHtcode);
			_fb.setSerialNo(sSlNo);

			_RecordTypeWiseEnclosureMstVO.setEnclosureId(_fb.getEnclosureId());
			_RecordTypeWiseEnclosureMstVO.setSerialNo(sSlNo);
			_RecordTypeWiseEnclosureMstVO.setHospitalCode(_fb.getHospitalCode());
			_RecordTypeWiseEnclosureMstVO.setIsValid(_fb.getIsValid());
			

			_RecordTypeWiseEnclosureMstVO = RecordTypeWiseEnclosureDATA.fetchEnclosureRecord(_RecordTypeWiseEnclosureMstVO, userVO);
			 
			_fb.setIsValid(_RecordTypeWiseEnclosureMstVO.getIsValid());
			_fb.setEnclosure(_RecordTypeWiseEnclosureMstVO.getEnclosure());
			_fb.setRecordTypeId(_RecordTypeWiseEnclosureMstVO.getRecordTypeId());
			_fb.setEnclosureId(sEnclosureID);
			_fb.setSerialNo(sSlNo);
			_fb.setRemarks(_RecordTypeWiseEnclosureMstVO.getRemarks());
			_fb.setIsCompulsory(_RecordTypeWiseEnclosureMstVO.getIsCompulsory());
			
			String recordTypeName;
			recordTypeName = RecordTypeWiseEnclosureDATA.getRecordTypeName(_fb.getRecordTypeId(), userVO);
			_fb.setRecordTypeName(recordTypeName);
						
			mp=RecordTypeWiseEnclosureDATA.getEssentials(userVO);
			WebUTIL.setMapInSession(mp, _request);
			
			for(int i=0;i<MrdConfig.IS_COMPULSORY_FOR_ENCLOSURE_AND_CHECKLIST_ARRAY.length-1;i++)
			{
				Entry ent=new Entry();
				ent.setValue(String.valueOf(i));
				ent.setLabel(MrdConfig.IS_COMPULSORY_FOR_ENCLOSURE_AND_CHECKLIST_ARRAY[i+1]);
				compulsoryLst.add(ent);
			}
		WebUTIL.setAttributeInSession(_request, MrdConfig.IS_COMPULSORY_FOR_ENCLOSURE_AND_CHECKLIST_LIST, compulsoryLst);

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
	}*/
	
	
	public static void saveEnclosure(RecordTypeWiseEnclosureMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List<RecordTypeWiseEnclosureMstVO> lstEnclosure=new ArrayList<RecordTypeWiseEnclosureMstVO>();
		
		try
		{
			for(int i=0;i<_fb.getHiddenEncId().length;i++)
			{
				RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO = new RecordTypeWiseEnclosureMstVO();
				_RecordTypeWiseEnclosureMstVO.setRecordTypeId(_fb.getRecordTypeId());
				_RecordTypeWiseEnclosureMstVO.setEnclosureId(_fb.getHiddenEncId()[i].split("@")[0]);
				_RecordTypeWiseEnclosureMstVO.setIsCompulsory(_fb.getIsCompulsory()[i]);
				_RecordTypeWiseEnclosureMstVO.setDisplayOrder(String.valueOf(i+1));
				
				lstEnclosure.add(_RecordTypeWiseEnclosureMstVO);
			}
			
			
			RecordTypeWiseEnclosureDATA.saveEnclosureRecord(lstEnclosure, getUserVO(_request));
		//	 String recordTypeName= RecordTypeWiseEnclosureDATA.getRecordTypeName(_fb.getHiddenControl(),userVO);
		//	 _fb.setRecordTypeName(recordTypeName);
			
			Map map=RecordTypeWiseEnclosureDATA.getEssentialForEnclosureMapping(_fb.getRecordTypeId(),getUserVO(_request));
			WebUTIL.setMapInSession(map, _request);
			objStatus.add(Status.LIST,"","Enclosure Mapped Successfully");
		
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
	
	public static void viewEnclosure(RecordTypeWiseEnclosureMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		
		try
		{
			String recordTypeID=_fb.getChk()[0].replace("^","@").split("@")[1];
			_fb.setHiddenControl(recordTypeID);
			
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
		catch(Exception e)
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
	/*public static boolean modifyEnclosureRecord(RecordTypeWiseEnclosureMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO = new RecordTypeWiseEnclosureMstVO();
			_RecordTypeWiseEnclosureMstVO.setEnclosure(_fb.getEnclosure());
			_RecordTypeWiseEnclosureMstVO.setRecordTypeId(_fb.getRecordTypeId());
			_RecordTypeWiseEnclosureMstVO.setRemarks(_fb.getRemarks());
			_RecordTypeWiseEnclosureMstVO.setEnclosureId(_fb.getEnclosureId());
			_RecordTypeWiseEnclosureMstVO.setSerialNo(_fb.getSerialNo());
			_RecordTypeWiseEnclosureMstVO.setIsValid(_fb.getIsValid());	
			_RecordTypeWiseEnclosureMstVO.setIsCompulsory(_fb.getIsCompulsory());
			
			hasFlag=RecordTypeWiseEnclosureDATA.modifyEnclosureRecord(_RecordTypeWiseEnclosureMstVO,userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Record Modified Successfully", "");
			 }else
			 {		System.out.println("in false");
				 objStatus.add(Status.NEW, "The Enclosure Name Already Exists", "");
			 }			
			
			
			objStatus.add(Status.TRANSINPROCESS);
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
		return hasFlag;
	}*/
	
}
		

