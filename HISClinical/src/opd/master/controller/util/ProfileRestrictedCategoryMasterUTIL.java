package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInsertNotAllowedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ProfileRestrictedCategoryMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.ProfileRestrictedCategoryMasterDATA;
import opd.master.controller.fb.ProfileRestrictedCategoryMasterFB;

public class ProfileRestrictedCategoryMasterUTIL extends ControllerUTIL{

	public static void fetchRestrictedCategoryEssentials(ProfileRestrictedCategoryMasterFB _fb,HttpServletRequest _rq)
	{
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		//List<Entry> listProfileType= new ArrayList<Entry>();
		try{
			UserVO userVO= getUserVO( _rq);
			essentialMap=ProfileRestrictedCategoryMasterDATA.fetchRestrictedCategoryEssentials(userVO);
		
			/*for(int i=1;i<OpdConfig.PROFILE_TYPE_IPD.length;i++)
			{
				
				Entry entry=new Entry();
				entry.setLabel(OpdConfig.PROFILE_TYPE_IPD[i]);
				entry.setValue(String.valueOf(i));
				listProfileType.add(entry);
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PROFILE_TYPE_LIST, listProfileType);*/
			WebUTIL.setMapInSession(essentialMap, _rq);
				objStatus.add(Status.NEW,"","");
		}
		catch(HisRecordNotFoundException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisInsertNotAllowedException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisDataAccessException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
			}		
		catch(Exception e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in DisasterAreaMstUTIL","");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}
	}
	
	public static boolean saveProfileRestrictedCategory(ProfileRestrictedCategoryMasterFB _fb, HttpServletRequest _request)
	{
		boolean flag=false;
		Status objStatus = new Status();
		UserVO userVO = getUserVO(_request);
		ProfileRestrictedCategoryMasterVO []insertProfileRestrictedCatMstVO=null;
		ProfileRestrictedCategoryMasterVO []updateProfileRestrictedCatMstVO=null;
		
		try
			{
				
				String fetchedList[]=_fb.getFetchedList().split("%");
				String selectedList[]=_fb.getSelectedPatientCategoryCode();
				if(selectedList==null)	selectedList=new String[0];
				String profileType=_fb.getProfileType();
				
				int len=0;
				if(fetchedList.length==0){
					insertProfileRestrictedCatMstVO=new ProfileRestrictedCategoryMasterVO[selectedList.length];
					for(int j=0;j<selectedList.length;j++){
						insertProfileRestrictedCatMstVO[j]=new ProfileRestrictedCategoryMasterVO();
						insertProfileRestrictedCatMstVO[j].setProfileType(profileType);
						insertProfileRestrictedCatMstVO[j].setPatientCategoryCode(selectedList[j]);
						
					}	
				}
				else{
				for(int j=0;j<selectedList.length;j++){
					for(int i=0;i<fetchedList.length;i++){
						if(fetchedList[i].equals(selectedList[j])){
							flag=false;
							break;
						}
						else{
							flag=true;
						}
					}
					if(flag) len++;
				}
				
				insertProfileRestrictedCatMstVO=new ProfileRestrictedCategoryMasterVO[len];
				len=0;
				for(int j=0;j<selectedList.length;j++){
					for(int i=0;i<fetchedList.length;i++){
						if(fetchedList[i].equals(selectedList[j])){
							flag=false;
							break;
						}
						else{
							flag=true;
						}
					}
					if(flag){
						insertProfileRestrictedCatMstVO[len]=new ProfileRestrictedCategoryMasterVO();
						insertProfileRestrictedCatMstVO[len].setProfileType(profileType);
						insertProfileRestrictedCatMstVO[len].setPatientCategoryCode(selectedList[j]);
						len++;
					}	
				}
				}
				len=0;
				if(selectedList.length==0){
					updateProfileRestrictedCatMstVO=new ProfileRestrictedCategoryMasterVO[fetchedList.length];
					for(int i=0;i<fetchedList.length;i++){
						updateProfileRestrictedCatMstVO[i]=new ProfileRestrictedCategoryMasterVO();
						updateProfileRestrictedCatMstVO[i].setProfileType(profileType);
						updateProfileRestrictedCatMstVO[i].setPatientCategoryCode(fetchedList[i]);
					}	
				}
				else{
					for(int i=0;i<fetchedList.length;i++){
						for(int j=0;j<selectedList.length;j++){
							if(fetchedList[i].equals(selectedList[j])){
								flag=false;
								break;
							}
							else{
								flag=true;
							}
						}
						if(flag) len++;
					}
					
					updateProfileRestrictedCatMstVO=new ProfileRestrictedCategoryMasterVO[len];
					len=0;
					for(int i=0;i<fetchedList.length;i++){
						for(int j=0;j<selectedList.length;j++){
							if(fetchedList[i].equals(selectedList[j])){
								flag=false;
								break;
							}
							else{
								flag=true;
							}
						}
						if(flag){
							updateProfileRestrictedCatMstVO[len]=new ProfileRestrictedCategoryMasterVO();
							updateProfileRestrictedCatMstVO[len].setProfileType(profileType);
							updateProfileRestrictedCatMstVO[len].setPatientCategoryCode(fetchedList[i]);
							len++;
						}	
					}
				}
				
				ProfileRestrictedCategoryMasterDATA.saveProfileRestrictedCategory(insertProfileRestrictedCatMstVO,updateProfileRestrictedCatMstVO,userVO);
				objStatus.add(Status.NEW,"","Record Saved Successfully");
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
		return flag;
	}

	
	public static void fetchPatientCatModify(ProfileRestrictedCategoryMasterFB _fb,HttpServletRequest _request) {

		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		Map essentialMap=new HashMap();
		List<Entry> listProfileType= new ArrayList<Entry>();
		ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO=new ProfileRestrictedCategoryMasterVO();
		try
		{
			
			essentialMap=ProfileRestrictedCategoryMasterDATA.fetchRestrictedCategoryEssentials(userVO);
			
			for(int i=1;i<OpdConfig.PROFILE_TYPE_IPD.length;i++)
			{
				
				Entry entry=new Entry();
				entry.setLabel(OpdConfig.PROFILE_TYPE_IPD[i]);
				entry.setValue(String.valueOf(i));
				listProfileType.add(entry);
			}
			WebUTIL.setAttributeInSession(_request, OpdConfig.PROFILE_TYPE_LIST, listProfileType);
			WebUTIL.setMapInSession(essentialMap, _request);
			
			List patientCat=(List)session.getAttribute(OpdConfig.PROFILE_RESTRICTED_CATEGORY_PATIENT_CATEGORY_LIST);
			
			String chk=_fb.getChk().replace("^", "#");
			String primaryKey[]=chk.split("#");
			String patientCategoryCode=primaryKey[0];
			String profileType=primaryKey[1];
			profileRestrictedCategoryMasterVO.setPatientCategoryCode(patientCategoryCode);
			profileRestrictedCategoryMasterVO.setProfileType(profileType);
			//_fb.setPatientCategoryCode(patientCategoryCode);
			_fb.setProfileType(profileType);
			
			/*profileRestrictedCategoryMasterVO=ProfileRestrictedCategoryMasterDATA.fetchPatientCatModify(profileRestrictedCategoryMasterVO,userVO);
			//HelperMethods.populate(_fb, profileRestrictedCategoryMasterVO);			
			_fb.setIsActive(profileRestrictedCategoryMasterVO.getIsActive());
			_fb.setSerialNo(profileRestrictedCategoryMasterVO.getSerialNo());
			_fb.setEntryDate(profileRestrictedCategoryMasterVO.getEntryDate());
			
			Iterator patientCatItr=patientCat.iterator();
			while(patientCatItr.hasNext())
			{
				Entry entry=(Entry)patientCatItr.next();
				
				if(entry.getValue().equals(profileRestrictedCategoryMasterVO.getPatientCategoryCode()))
				{
					_fb.setPatientCategoryName(entry.getLabel());
				}
			}
			objStatus.add(Status.DONE);*/
			getPatientCategoryForProfileType(_fb, _request);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
			
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
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}
	
	
	/**
	 * modify the old record and insert new record in the database
	 * @param _fb
	 * @param _request
	 */
	
	public static boolean modifySave(ProfileRestrictedCategoryMasterFB _fb,HttpServletRequest _request) {
		
		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		boolean flag=false;
		ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO=new ProfileRestrictedCategoryMasterVO();
		try
		{
			//String disasterTypeCode=_fb.getDisasterTypeCode();
			HelperMethods.populate(profileRestrictedCategoryMasterVO, _fb);
			String chk=_fb.getChk().replace("^", "#");
			String primaryKey[]=chk.split("#");
			String patientCategoryCode=primaryKey[0];
			String profileTypeModify=primaryKey[1];
			profileRestrictedCategoryMasterVO.setPatientCategoryCode(patientCategoryCode);
			profileRestrictedCategoryMasterVO.setProfileTypeModify(profileTypeModify);
			
			ProfileRestrictedCategoryMasterDATA.modifySave(profileRestrictedCategoryMasterVO,userVO);
			//HelperMethods.populate(_fb, diasasterVO);
			flag=true;
			objStatus.add(Status.DONE);
		}
		catch(HisDuplicateRecordException e){	   		   	
			 System.out.println("Inside HisDuplicateRecordException");
	  		 e.printStackTrace(); 
	  		 flag=false;
	  		 objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			
		}
		catch (HisApplicationExecutionException e)
		{
			flag=false;
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			
		}
		catch (HisException e)
		{
			flag=false;
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}
	
	
	public static void getPatientCategoryForProfileType(ProfileRestrictedCategoryMasterFB _fb,HttpServletRequest _request) {

		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		//HttpSession session=_request.getSession();
		Map essentialMap=new HashMap();
		
		List <Entry> addedCategoryList=null;
		List <Entry> notAddedCategoryList=null;
		List <Entry> allCategoryList=null;
		try
		{
			
			essentialMap=ProfileRestrictedCategoryMasterDATA.getPatientCategoryForProfileType(_fb.getProfileType(), userVO);
			addedCategoryList=(List<Entry>)essentialMap.get(OpdConfig.PATIENT_PRIMARY_CATEGORY_MAPPED_WITH_PROFILE_TYPE_LIST);
			allCategoryList=(List<Entry>)essentialMap.get(OpdConfig.ALL_PATIENT_PRIMARY_CATEGORY_LIST);
			removeElementsFromList(allCategoryList, addedCategoryList);
			notAddedCategoryList=allCategoryList;
			String fetchedList="";
			if(addedCategoryList!=null){
				for(int i=0;i<addedCategoryList.size();i++){
					Entry entry=(Entry)addedCategoryList.get(i);
					fetchedList=fetchedList+"%"+entry.getValue();
				}
			}
			_fb.setFetchedList(fetchedList.replaceFirst("%", ""));
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.PATIENT_PRIMARY_CATEGORY_MAPPED_WITH_PROFILE_TYPE_LIST, addedCategoryList);
			WebUTIL.setAttributeInSession(_request, OpdConfig.PATIENT_PRIMARY_CATEGORY_NOT_MAPPED_WITH_PROFILE_TYPE_LIST, notAddedCategoryList);
			objStatus.add(Status.DONE);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
			
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
	}
	
	public static void removeElementsFromList(List<Entry> removeFromList,List<Entry> removeList){
		removeFromList.removeAll(removeList);
	}
}
