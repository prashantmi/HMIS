package opd.master.controller.util;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.DrugDoseMstVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.DrugDoseMasterDATA;
import opd.master.controller.fb.DrugDoseMasterFB;

public class DrugDoseMasterUTIL extends ControllerUTIL
{
	
	public static void getItemTypeName(DrugDoseMasterFB _fb, HttpServletRequest _request)
	 {
		Status objStatus = new Status();
		List itemTypeList=null;
		Map essentialMap=new HashMap();
		//Map mp = new HashMap();
		DrugDoseMstVO _drugDoseMstVO = new DrugDoseMstVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			/* _fb.setItemTypeId(_fb.getControls()[0]);
			 String itemTypeID=_fb.getItemTypeId();
			 _drugDoseMstVO=DrugDoseMasterDATA.getItemName(itemTypeID,userVO);
			_fb.setItemType(_drugDoseMstVO.getItemType());
			_fb.setItemTypeId(itemTypeID);
			objStatus.add(Status.NEW);*/
			

			
			essentialMap = DrugDoseMasterDATA.getDrugDoseMasterEssentails(userVO);
		//itemTypeList=(List)essentialMap.get(OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE);
			itemTypeList=(List)essentialMap.get(OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE);
			Iterator itr=itemTypeList.iterator();
			String itemType="";
			while(itr.hasNext())
			{
				Entry obj=(Entry)itr.next();
				if(obj.getValue().equals(_fb.getItemTypeId()))
				{
					itemType=obj.getLabel();
				}
			}
			_fb.setItemType(itemType);
			_fb.setIsFrequencyBound(OpdConfig.IS_FREQUENCY_BOUND);
			
	        //Commented By Chetan
			//Date : 2015_12_5
			//WebUTIL.setAttributeInSession(_request, OpdConfig.ESSENTIALBO_ITEM_TYPE_LIST, itemTypeList);
			WebUTIL.setAttributeInSession(_request, OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE, itemTypeList);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "e.printStackTrace()", "");
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}
/*	public static boolean getEssentails(DrugDoseMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List itemTypeList=null;
		Map essentialMap=new HashMap();
		
		try
		{	
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			if(_fb.getControls() !=null && _fb.getControls()[0]!=null)
				_fb.setItemTypeId(_fb.getControls()[0]);
			
			essentialMap = DrugDoseMasterDATA.getDrugDoseMasterEssentails(userVO);
		//itemTypeList=(List)essentialMap.get(OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE);
			itemTypeList=(List)essentialMap.get(OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE);
			Iterator itr=itemTypeList.iterator();
			String itemType="";
			while(itr.hasNext())
			{
				Entry obj=(Entry)itr.next();
				if(obj.getValue().equals(_fb.getItemTypeId()))
				{
					itemType=obj.getLabel();
				}
			}
			_fb.setItemType(itemType);
			_fb.setIsFrequencyBound(OpdConfig.IS_FREQUENCY_BOUND);
			
	        //Commented By Chetan
			//Date : 2015_12_5
			//WebUTIL.setAttributeInSession(_request, OpdConfig.ESSENTIALBO_ITEM_TYPE_LIST, itemTypeList);
			WebUTIL.setAttributeInSession(_request, OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE, itemTypeList);
		
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
		}
		return true;
	}*/
	/*
	public static void saveDetail(DrugDoseMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		DrugDoseMstVO[] drugDoseMstVO=null;
		DrugDoseMstVO[] drugDoseMstDetailVO=null;
		HttpSession session=request.getSession();
		try
		{
			drugDoseMstVO=(DrugDoseMstVO[])session.getAttribute(OpdConfig.NEW_DRUG_DOSE_MST_DTL_LIST);
			
			if(drugDoseMstVO==null)
			{
				drugDoseMstDetailVO=new DrugDoseMstVO[1];
				drugDoseMstDetailVO[0]=new DrugDoseMstVO();
			    drugDoseMstDetailVO[0].setDoseName(_fb.getDoseName());
			    drugDoseMstDetailVO[0].setDoseInstruction(_fb.getDoseInstruction());
			}
			else
			{
				drugDoseMstDetailVO=new DrugDoseMstVO[drugDoseMstVO.length+1];
				int j=0;
			    for(;j<drugDoseMstVO.length;j++)
			      {	
			    	drugDoseMstDetailVO[j]=drugDoseMstVO[j];
			      }
			    drugDoseMstDetailVO[j]=new DrugDoseMstVO();
			    drugDoseMstDetailVO[j].setDoseName(_fb.getDoseName());
			    drugDoseMstDetailVO[j].setDoseInstruction(_fb.getDoseInstruction());
			}
			
			
			
			for(int i=0;i<drugDoseMstDetailVO.length;i++)
			{
				drugDoseMstDetailVO[i].setItemTypeId(_fb.getItemTypeId());
				drugDoseMstDetailVO[i].setSereialNo("1");
			}
			DrugDoseMasterDATA.saveDetail(drugDoseMstDetailVO,userVO);
			objStatus.add(Status.DONE,"Record Saved Successfully","");		
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	*/
	
	public static boolean saveDetail(DrugDoseMasterFB _fb,HttpServletRequest request)
	{
		boolean saveFlag = true;
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		DrugDoseMstVO drugDoseMstVO=null;
		
		try
		{
			drugDoseMstVO=new DrugDoseMstVO();
			drugDoseMstVO.setDoseName(_fb.getDoseName());
			drugDoseMstVO.setDoseInstruction(_fb.getDoseInstruction());
			drugDoseMstVO.setItemTypeId(_fb.getItemTypeId());
			drugDoseMstVO.setSereialNo("1");
			drugDoseMstVO.setDoseName(_fb.getDoseName());
			drugDoseMstVO.setDoseInstruction(_fb.getDoseInstruction());
			drugDoseMstVO.setIsFrequencyBound(_fb.getIsFrequencyBound());
			drugDoseMstVO.setDoseQty(_fb.getDoseQty());
		    
			DrugDoseMasterDATA.saveDetail(drugDoseMstVO,userVO);
			objStatus.add(Status.DONE,"Record Saved Successfully","");		
		}
		catch(HisRecordNotFoundException e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
		return saveFlag;
	}
	
	public static void getModifyDetail(DrugDoseMasterFB _fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		UserVO userVO=getUserVO(request);
		DrugDoseMstVO drugDoseMstVO=new DrugDoseMstVO();
		try
		{
			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");
			String doseId=concatid[0];
			String sHtcode = concatid[1];
			String sereialNo=concatid[2];
			
			_fb.setDoseId(doseId);
			_fb.setHospitalCode(sHtcode);
			_fb.setSereialNo(sereialNo);
			
			drugDoseMstVO.setDoseId(doseId);
			drugDoseMstVO.setHospitalCode(sHtcode);
			drugDoseMstVO.setSereialNo(sereialNo);
			drugDoseMstVO=DrugDoseMasterDATA.getModifyDetail(drugDoseMstVO,userVO);
			
			_fb.setIsActive(drugDoseMstVO.getIsValid());
			_fb.setDoseName(drugDoseMstVO.getDoseName());
			_fb.setDoseInstruction(drugDoseMstVO.getDoseInstruction());
			
			_fb.setIsFrequencyBound(drugDoseMstVO.getIsFrequencyBound());
			_fb.setDoseQty(drugDoseMstVO.getDoseQty());
			_fb.setDoseId(doseId);
			_fb.setSereialNo(sereialNo);
			_fb.setItemTypeId(drugDoseMstVO.getItemTypeId());
			// Added By : Chhetan Sharma
			_fb.setItemTypeName(drugDoseMstVO.getItemTypeName());
	
			drugDoseMstVO = DrugDoseMasterDATA.getItemName(_fb.getItemTypeId(), userVO);
			_fb.setItemType(drugDoseMstVO.getItemType());
			
//			drugDoseMstVO=_drugDoseMstVO[0];
//			_fb.setIsFrequencyBound(drugDoseMstVO.getIsFrequencyBound());
//			HelperMethods.populate(_fb, drugDoseMstVO);
//			WebUTIL.setAttributeInSession(request, OpdConfig.MODIFY_DRUG_DOSE_MST_DTL_VO_LIST, _drugDoseMstVO);
//			
//			_fb.setItemType(_drugDoseMstVO[0].getItemType());
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
		
	}
	
	public static void addRowToTable(DrugDoseMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		//Map essentialMap=new HashMap();
		HttpSession session=WebUTIL.getSession(_request);
		DrugDoseMstVO[] drugDoseMstVO=null;
		DrugDoseMstVO[] drugDoseMstDtlVO=null;
		//DrugDoseMstVO[] drugDoseMstDetailVO=null;
		
		try{
			  //setSysdate(_request);
			 // UserVO userVO = getUserVO(_request);
			 
			 // WebUTIL.setAttributeInSession(_request, OpdConfig.NEW_DRUG_DOSE_MST_DTL_LIST, drugDoseMstDtlVO);
            			  
			  drugDoseMstDtlVO=(DrugDoseMstVO[])session.getAttribute(OpdConfig.NEW_DRUG_DOSE_MST_DTL_LIST);
			  if(drugDoseMstDtlVO==null)
			  {
				  
				  drugDoseMstDtlVO=new DrugDoseMstVO[1];
				  drugDoseMstDtlVO[0]=new DrugDoseMstVO();
				  drugDoseMstDtlVO[0].setDoseName(_fb.getDoseName());
				  drugDoseMstDtlVO[0].setDoseInstruction(_fb.getDoseInstruction());
				  
				  
				  WebUTIL.setAttributeInSession(_request,OpdConfig.NEW_DRUG_DOSE_MST_DTL_LIST , drugDoseMstDtlVO);
			  }
			  
			  else
			  {
				  drugDoseMstVO=new DrugDoseMstVO[drugDoseMstDtlVO.length+1];
					  	int j=0;
					    for(;j<drugDoseMstDtlVO.length;j++)
					      {	
					    	drugDoseMstVO[j]=drugDoseMstDtlVO[j];
					      }
					    drugDoseMstVO[j]=new DrugDoseMstVO();
					    drugDoseMstVO[j].setDoseName(_fb.getDoseName());
					    drugDoseMstVO[j].setDoseInstruction(_fb.getDoseInstruction());
					      
					      WebUTIL.setAttributeInSession(_request,OpdConfig.NEW_DRUG_DOSE_MST_DTL_LIST , drugDoseMstVO);
			  }
			  
			  
			  objStatus.add(Status.LIST);
			  objStatus.add(Status.TRANSINPROCESS);
			  }
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");		
		}
		catch(HisApplicationExecutionException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}	
		finally{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void delRowFromTable(DrugDoseMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		//Map essentialMap=new HashMap();
		HttpSession session=WebUTIL.getSession(_request);
		DrugDoseMstVO[] drugDoseDtlVO=null;
		DrugDoseMstVO[] drugDoseDetailtVO=null;
		try{
			  //setSysdate(_request);
			  //UserVO userVO = getUserVO(_request);
			
			  int selectedDelRow=Integer.parseInt(_fb.getIndex());
			  
			  
			  drugDoseDtlVO=(DrugDoseMstVO[])session.getAttribute(OpdConfig.NEW_DRUG_DOSE_MST_DTL_LIST );
			 
			  drugDoseDetailtVO=new DrugDoseMstVO[drugDoseDtlVO.length-1];
			  int j=0;
			  for(int i=0;i<drugDoseDtlVO.length;i++)
			  {
				 
				  if( !(selectedDelRow==i) )
				  {
					  drugDoseDetailtVO[j]=drugDoseDtlVO[i];
					  j++;
				  }
				  
			  }
			  //_fb.setEmpNo("");
			  WebUTIL.setAttributeInSession(_request,OpdConfig.NEW_DRUG_DOSE_MST_DTL_LIST , drugDoseDetailtVO);
			  objStatus.add(Status.LIST);
			  objStatus.add(Status.TRANSINPROCESS);
			}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");		
		}
		catch(HisApplicationExecutionException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}	
		finally{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	/*
	public static void addModRowToTable(DrugDoseMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		HttpSession session=WebUTIL.getSession(_request);
		DrugDoseMstVO[] drugDoseMstVO=null;
		DrugDoseMstVO[] drugDoseMstDtlVO=null;
		DrugDoseMstVO[] drugDoseMstDetailVO=null;
		
		try{
			  //setSysdate(_request);
			  UserVO userVO = getUserVO(_request);
			 
			 // WebUTIL.setAttributeInSession(_request, OpdConfig.NEW_DRUG_DOSE_MST_DTL_LIST, drugDoseMstDtlVO);
            			  
			  drugDoseMstDtlVO=(DrugDoseMstVO[])session.getAttribute(OpdConfig.MODIFY_DRUG_DOSE_MST_DTL_VO_LIST);
			  System.out.println("length--------"+drugDoseMstDtlVO.length);
			  
			  if(drugDoseMstDtlVO==null || drugDoseMstDtlVO.length==0)
			  {
				  
				  drugDoseMstDtlVO=new DrugDoseMstVO[1];
				  drugDoseMstDtlVO[0]=new DrugDoseMstVO();
				  drugDoseMstDtlVO[0].setDoseName(_fb.getDoseName());
				  drugDoseMstDtlVO[0].setDoseInstruction(_fb.getDoseInstruction());
				  
				  
				  WebUTIL.setAttributeInSession(_request,OpdConfig.MODIFY_DRUG_DOSE_MST_DTL_VO_LIST , drugDoseMstDtlVO);
			  }
			  
			  else
			  {	
			  */	
				  /*
				  drugDoseMstVO=new DrugDoseMstVO[drugDoseMstDtlVO.length+1];
					  	int j=0;
					    for(;j<drugDoseMstDtlVO.length;j++)
					      {	
					    	drugDoseMstVO[j]=drugDoseMstDtlVO[j];
					      }
					    drugDoseMstVO[j]=new DrugDoseMstVO();
					    drugDoseMstVO[j].setDoseName(_fb.getDoseName());
					    drugDoseMstVO[j].setDoseInstruction(_fb.getDoseInstruction());
					    drugDoseMstVO[j].setDoseId("");  
					      WebUTIL.setAttributeInSession(_request,OpdConfig.MODIFY_DRUG_DOSE_MST_DTL_VO_LIST , drugDoseMstVO);
			  		*/
	/*
				  	String[] modDoseName=_fb.getModDoseName();
					String[] modDoseInstruction=_fb.getModDoseInstruction();
					String[] modDoseId=_fb.getDoseId();
					
					DrugDoseMstVO[] _drugDoseMstVO=new DrugDoseMstVO[modDoseName.length];
					
					for(int i=0;i<modDoseName.length;i++)
					{
						_drugDoseMstVO[i]=new DrugDoseMstVO();
						_drugDoseMstVO[i].setDoseName(modDoseName[i]);
						_drugDoseMstVO[i].setDoseInstruction(modDoseInstruction[i]);
						_drugDoseMstVO[i].setItemTypeId(_fb.getItemTypeId());
						_drugDoseMstVO[i].setDoseId(modDoseId[i]);
					}
					
					 drugDoseMstVO=new DrugDoseMstVO[_drugDoseMstVO.length+1];
					  	int j=0;
					    for(;j<_drugDoseMstVO.length;j++)
					      {	
					    	drugDoseMstVO[j]=_drugDoseMstVO[j];
					      }
					    drugDoseMstVO[j]=new DrugDoseMstVO();
					    drugDoseMstVO[j].setDoseName(_fb.getDoseName());
					    drugDoseMstVO[j].setDoseInstruction(_fb.getDoseInstruction());
					    drugDoseMstVO[j].setDoseId("");  
					      WebUTIL.setAttributeInSession(_request,OpdConfig.MODIFY_DRUG_DOSE_MST_DTL_VO_LIST , drugDoseMstVO);
					
			  }
			  
			  
			  objStatus.add(Status.LIST);
			  objStatus.add(Status.TRANSINPROCESS);
			  }
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");		
		}
		catch(HisApplicationExecutionException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}	
		finally{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	*/
	/*
	public static void delModRowFromTable(DrugDoseMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		HttpSession session=WebUTIL.getSession(_request);
		DrugDoseMstVO[] drugDoseDtlVO=null;
		DrugDoseMstVO[] drugDoseDetailtVO=null;
		try{
		*/
			 /*
			  UserVO userVO = getUserVO(_request);
			
			  int selectedDelRow=Integer.parseInt(_fb.getIndex());
			  
			  
			  drugDoseDtlVO=(DrugDoseMstVO[])session.getAttribute(OpdConfig.MODIFY_DRUG_DOSE_MST_DTL_VO_LIST );
			 
			  drugDoseDetailtVO=new DrugDoseMstVO[drugDoseDtlVO.length-1];
			  int j=0;
			  for(int i=0;i<drugDoseDtlVO.length;i++)
			  {
				 
				  if( !(selectedDelRow==i) )
				  {
					  drugDoseDetailtVO[j]=drugDoseDtlVO[i];
					  j++;
				  }
				  
			  }
			  */
	/*
			String[] modDoseName=_fb.getModDoseName();
			String[] modDoseInstruction=_fb.getModDoseInstruction();
			String[] modDoseId=_fb.getDoseId();
			
			DrugDoseMstVO[] _drugDoseMstVO=new DrugDoseMstVO[modDoseName.length];
			
			for(int i=0;i<modDoseName.length;i++)
			{
				_drugDoseMstVO[i]=new DrugDoseMstVO();
				_drugDoseMstVO[i].setDoseName(modDoseName[i]);
				_drugDoseMstVO[i].setDoseInstruction(modDoseInstruction[i]);
				_drugDoseMstVO[i].setItemTypeId(_fb.getItemTypeId());
				_drugDoseMstVO[i].setDoseId(modDoseId[i]);
			}
			
				int selectedDelRow=Integer.parseInt(_fb.getIndex());
			  
			  
			 // drugDoseDtlVO=(DrugDoseMstVO[])session.getAttribute(OpdConfig.MODIFY_DRUG_DOSE_MST_DTL_VO_LIST );
			 
			  drugDoseDetailtVO=new DrugDoseMstVO[_drugDoseMstVO.length-1];
			  int j=0;
			  for(int i=0;i<_drugDoseMstVO.length;i++)
			  {
				 
				  if( !(selectedDelRow==i) )
				  {
					  drugDoseDetailtVO[j]=_drugDoseMstVO[i];
					  j++;
				  }
				  
			  }
			
			
			  WebUTIL.setAttributeInSession(_request,OpdConfig.MODIFY_DRUG_DOSE_MST_DTL_VO_LIST , drugDoseDetailtVO);
			  objStatus.add(Status.LIST);
			  objStatus.add(Status.TRANSINPROCESS);
			}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");		
		}
		catch(HisApplicationExecutionException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}	
		finally{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	*/
	public static boolean saveModifyDetail(DrugDoseMasterFB _fb,HttpServletRequest _request)
	{
		boolean saveFlag = true;
		Status objStatus=new Status();
		//HttpSession session=request.getSession();
		try
		{	
			UserVO _userVO = getUserVO(_request);
			 DrugDoseMstVO _drugDoseMstVO=new DrugDoseMstVO();
			 _drugDoseMstVO.setDoseId(_fb.getDoseId());
			 _drugDoseMstVO.setDoseName(_fb.getDoseName());
			 _drugDoseMstVO.setDoseInstruction(_fb.getDoseInstruction());
			 _drugDoseMstVO.setIsFrequencyBound(_fb.getIsFrequencyBound());
			 _drugDoseMstVO.setSereialNo(_fb.getSereialNo());
			 _drugDoseMstVO.setIsValid(_fb.getIsActive());	
			_drugDoseMstVO.setDoseQty(_fb.getDoseQty());
			_drugDoseMstVO.setItemTypeId(_fb.getItemTypeId());
			DrugDoseMasterDATA.saveModifyDetail(_drugDoseMstVO,_userVO);
			objStatus.add(Status.TRANSINPROCESS, "Record Modified Successfully","");		
		}
		catch (HisRecordNotFoundException e)
		{
			saveFlag = false;
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			saveFlag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
		return saveFlag;
	}
}
