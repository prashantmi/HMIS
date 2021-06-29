package opd.transaction.controller.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdPatientBelongingDATA;
import opd.transaction.controller.fb.OpdPatientBelongingFB;

import registration.RegistrationConfig;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.PatientBelongingVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;


public class OpdPatientBelongingUTIL extends ControllerUTIL {
	
	public static void setPatientDtlByCrno( OpdPatientBelongingFB _fb,HttpServletRequest _request){
		Status  objStatus=new Status();
						
		try{
				
			UserVO userVO =getUserVO(_request);
			PatientVO patientVO=new PatientVO();
			patientVO.setPatCrNo(_fb.getPatCrNo());
			patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
			patientVO=OpdPatientBelongingDATA.getPatientDtl(patientVO, userVO);
			HelperMethods.populate(_fb,patientVO);	
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO,patientVO);
			
			objStatus.add(Status.NEW);
		
		}catch(HisRecordNotFoundException e){
			e.printStackTrace();			
			objStatus.add(Status.NEW,e.getMessage(),"");	
		}		
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			WebUTIL.setStatus(_request,objStatus);
			
		}	

	}
	
	public static void getPatientBelongingEssentials(OpdPatientBelongingFB _fb, HttpServletRequest _rq)
	{
		//HttpSession session= _rq.getSession();
		
		Map essentialMap;
		Status objStatus=new Status();
		try{
			essentialMap=OpdPatientBelongingDATA.getPatientBelongingEssentials(_fb.getPatCrNo(),getUserVO(_rq));
			WebUTIL.setMapInSession(essentialMap,_rq);
			objStatus.add(Status.NEW);
			
		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			essentialMap=e.getEssentialMap();
			WebUTIL.setMapInSession(essentialMap,_rq);
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
		
	}
	
	public static void addDetailRow(OpdPatientBelongingFB _fb,HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		Map selectedItemMap=null;
		List selectedItemList=new ArrayList();
		List belongingList=null;
		String belongingItemName="";
		Status objStatus=new Status();
		try{
			String belongingItemCode=_fb.getBelongingItemCode();
					
			selectedItemMap=(Map)session.getAttribute(OpdConfig.PATIENT_BELONGING_MAP);
			belongingList=(List)session.getAttribute(OpdConfig.ESSENTIALBO_BELONGING_LIST);
			if(selectedItemMap==null)
			{
				selectedItemMap=new LinkedHashMap();
			}
						
			ListIterator listIterator=belongingList.listIterator();
			while(listIterator.hasNext()){
				Entry entry=(Entry)listIterator.next();
				if(entry.getValue().equals(belongingItemCode)){
					belongingItemName=entry.getLabel();
					break;
				}
			}
						
			belongingList=(List) WebUTIL.removeEntriesfromOptions(belongingList,belongingItemCode);	
					
			selectedItemList.add(0,belongingItemName);
			selectedItemList.add(1,belongingItemCode);
			selectedItemList.add(2,_fb.getQuantity());
			selectedItemList.add(3,_fb.getRemarks());
			
			selectedItemMap.put(belongingItemCode, selectedItemList);
			
			
			WebUTIL.setAttributeInSession(_rq,OpdConfig.PATIENT_BELONGING_MAP,selectedItemMap);
			WebUTIL.setAttributeInSession(_rq,OpdConfig.ESSENTIALBO_BELONGING_LIST, belongingList);
			
			_fb.setQuantity("");
			_fb.setRemarks("");
			
			objStatus.add(Status.TRANSINPROCESS);
			}catch(HisRecordNotFoundException e)		{
				e.printStackTrace();
				objStatus.add(Status.NEW, e.getMessage(), "");
			}catch(HisDataAccessException e){
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
			}
			catch(HisApplicationExecutionException e){		
				objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			}
			catch(HisException e){
				
			}
			catch(Exception e){
				objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			}
			finally{
				
				WebUTIL.setStatus(_rq,objStatus);
				
			}	
	}
	
	public static void removeDetailRow	(OpdPatientBelongingFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		HttpSession session= _rq.getSession();
		Map selectedBelongingMap=(Map)session.getAttribute(OpdConfig.PATIENT_BELONGING_MAP);
		List belongingList=(List)session.getAttribute(OpdConfig.ESSENTIALBO_BELONGING_LIST);
		String belongingItemCode=_fb.getRemoveBelongingCode();
		
		List selectedItemList=(List)selectedBelongingMap.get(belongingItemCode);
		String belongingItemName=(String)selectedItemList.get(0);
		
		belongingList=(List)WebUTIL.addEntryToOptions(belongingList, belongingItemCode, belongingItemName);
		
		selectedBelongingMap.remove(belongingItemCode);
		
		session.setAttribute(OpdConfig.PATIENT_BELONGING_MAP, selectedBelongingMap);
		session.setAttribute(OpdConfig.ESSENTIALBO_BELONGING_LIST, belongingList);
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(_rq,objStatus);
		
	}
	
	
	public static void modifyBelonging	(OpdPatientBelongingFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		HttpSession session= _rq.getSession();
		
		PatientBelongingVO[] arrayPatBelongingVOs=(PatientBelongingVO[]) session.getAttribute(OpdConfig.PATIENT_BELONGING_DETAILS_VOS);
		
		
			String selectedCheckBox=_fb.getChk()[0];
			String selectedItmCode=selectedCheckBox.substring(0,selectedCheckBox.indexOf("^"));
			String selectedDate=selectedCheckBox.substring(selectedCheckBox.indexOf("^")+1);
			for(int j=0;j<arrayPatBelongingVOs.length;j++)
			{
				if(arrayPatBelongingVOs[j].getBelongingItemCode().equals(selectedItmCode) && 
						arrayPatBelongingVOs[j].getCollectionDate().equals(selectedDate))
				{
					_fb.setBelongingItemCode(arrayPatBelongingVOs[j].getBelongingItemCode());
					_fb.setQuantity(arrayPatBelongingVOs[j].getQuantity());
					_fb.setRemarks(arrayPatBelongingVOs[j].getRemarks());
					WebUTIL.setAttributeInSession(_rq, OpdConfig.SELECTED_PATIENT_BELONGING_VO, arrayPatBelongingVOs[j]);
				}
			}
		
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(_rq,objStatus);
		
	}
	
	
	
	public static void saveBelongingDetails(OpdPatientBelongingFB _fb,HttpServletRequest _rq){
		
		Status objStatus=new Status();
		HttpSession session= _rq.getSession();
		Map selectedBelongingMap;
		
		List SelectedBelongingList=new ArrayList();
		PatientBelongingVO[] patBelongingVO=null;
		
		int lengthvo=0;
		int loopCounter=0;
		
		try{
			
			////////////Adding form properties to map////////
			selectedBelongingMap=(Map)session.getAttribute(OpdConfig.PATIENT_BELONGING_MAP);
			if(selectedBelongingMap==null)
			{
				selectedBelongingMap=new LinkedHashMap();
			}
			SelectedBelongingList.add(0, "");
			SelectedBelongingList.add(1, _fb.getBelongingItemCode());
			SelectedBelongingList.add(2,_fb.getQuantity());
			SelectedBelongingList.add(3,_fb.getRemarks());
			
			selectedBelongingMap.put(_fb.getBelongingItemCode(), SelectedBelongingList);
		
	
			lengthvo=selectedBelongingMap.size();
	
		
	
		patBelongingVO=new PatientBelongingVO[lengthvo];
		
		
			Iterator innerMapItr = ((Collection)selectedBelongingMap.values()).iterator();
			while(innerMapItr.hasNext())
			{
				SelectedBelongingList=(List)innerMapItr.next();
				
				patBelongingVO[loopCounter]=new PatientBelongingVO();
				patBelongingVO[loopCounter].setBelongingItemCode((String)SelectedBelongingList.get(1));
				patBelongingVO[loopCounter].setQuantity((String)SelectedBelongingList.get(2));
				patBelongingVO[loopCounter].setRemarks((String)SelectedBelongingList.get(3));
				patBelongingVO[loopCounter].setPatCrNo(_fb.getPatCrNo());
							
				loopCounter++;
			}
		
		OpdPatientBelongingDATA.saveBelongingDetails(patBelongingVO,getUserVO(_rq));
		objStatus.add(Status.DONE,"Belonging Details saved Successfully","");
		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			e.printStackTrace();
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	}
	
	public static void saveHandOverDetails(OpdPatientBelongingFB _fb, HttpServletRequest _rq){
		Status objStatus=new Status();
		HttpSession session= _rq.getSession();
		PatientBelongingVO[] savePatBelongingVO=new PatientBelongingVO[_fb.getChk().length];
		PatientBelongingVO[] arrayPatBelongingVOs=null;
		int loopCounter=0;
		
		try{
			UserVO userVO=getUserVO(_rq);
			
			arrayPatBelongingVOs=(PatientBelongingVO[]) session.getAttribute(OpdConfig.PATIENT_BELONGING_DETAILS_VOS);
			for(int i=0;i<_fb.getChk().length;i++)
			{
				String selectedCheckBox=_fb.getChk()[i];
				String selectedItmCode=selectedCheckBox.substring(0,selectedCheckBox.indexOf("^"));
				String selectedDate=selectedCheckBox.substring(selectedCheckBox.indexOf("^")+1);
				for(int j=0;j<arrayPatBelongingVOs.length;j++)
				{
					if(arrayPatBelongingVOs[j].getBelongingItemCode().equals(selectedItmCode) && 
							arrayPatBelongingVOs[j].getCollectionDate().equals(selectedDate))
					{
						savePatBelongingVO[loopCounter]=new PatientBelongingVO();
						HelperMethods.populate(savePatBelongingVO[loopCounter], arrayPatBelongingVOs[j]);
						savePatBelongingVO[loopCounter].setLastModifiedSeatID(userVO.getSeatId());
						savePatBelongingVO[loopCounter].setHandOverTo(_fb.getHandOverTo()+"^"+_fb.getRelation());
						savePatBelongingVO[loopCounter].setHandOverBy(userVO.getSeatId());
						loopCounter++;
					}
				}
				
			}
			
			
			
			OpdPatientBelongingDATA.saveBelongingHandoverDetails(savePatBelongingVO,getUserVO(_rq));
			objStatus.add(Status.DONE,"Belonging Hand Over saved Successfully","");
			}catch(HisRecordNotFoundException e)		{
				e.printStackTrace();
				objStatus.add(Status.NEW, e.getMessage(), "");
			}catch(HisDataAccessException e){
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
			}
			catch(HisApplicationExecutionException e){		
				objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			}
			catch(HisException e){
				
			}
			catch(Exception e){
				objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			}
			finally{
				
				WebUTIL.setStatus(_rq,objStatus);
				
			}
		
	}

	
	public static void saveModifyDetails(OpdPatientBelongingFB _fb, HttpServletRequest _rq){
		Status objStatus=new Status();
		HttpSession session= _rq.getSession();
		
		PatientBelongingVO patBelongingVOs=new PatientBelongingVO();
		PatientBelongingVO savePatBelongingVO=new PatientBelongingVO();
		
		PatientBelongingVO[] arrayPatBeloningVOs=null;
		
		
		try{
			UserVO userVO=getUserVO(_rq);
			
			patBelongingVOs=(PatientBelongingVO) session.getAttribute(OpdConfig.SELECTED_PATIENT_BELONGING_VO);
			String oldItemCode=patBelongingVOs.getBelongingItemCode();
			String CollectionDate=patBelongingVOs.getCollectionDate();
			
			savePatBelongingVO.setBelongingItemCode(_fb.getBelongingItemCode());
			savePatBelongingVO.setQuantity(_fb.getQuantity());
			savePatBelongingVO.setRemarks(_fb.getRemarks());
			savePatBelongingVO.setPatCrNo(_fb.getPatCrNo());
			savePatBelongingVO.setSeatID(userVO.getSeatId());
				
				
			
			arrayPatBeloningVOs=OpdPatientBelongingDATA.modifyBelongingDetails(savePatBelongingVO,oldItemCode,CollectionDate,getUserVO(_rq));
			
			WebUTIL.setAttributeInSession(_rq,OpdConfig.PATIENT_BELONGING_DETAILS_VOS, arrayPatBeloningVOs);
			
			objStatus.add(Status.NEW,"Record Modified Successfully","");
			}catch(HisRecordNotFoundException e)		{
				e.printStackTrace();
				objStatus.add(Status.NEW, e.getMessage(), "");
			}catch(HisDataAccessException e){
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
			}
			catch(HisApplicationExecutionException e){		
				objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			}
			catch(HisException e){
				
			}
			catch(Exception e){
				objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			}
			finally{
				
				WebUTIL.setStatus(_rq,objStatus);
				
			}
		
	}


}
