package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.ProfileTypeTabMappingVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.ProfileTypeTabMappingDATA;
import opd.master.controller.fb.ProfileTypeTabMappingFB;

public class ProfileTypeTabMappingUTL extends ControllerUTIL
{
	/**
	 * To get the List of the profile Type
	 * @param _fb
	 * @param _request
	 */
	public static void getProfileType(ProfileTypeTabMappingFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//Map essentialMap=new HashMap();
		List profileTypelist=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			profileTypelist=ProfileTypeTabMappingDATA.getProfileType(userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.PROFILE_TYPE_LIST, profileTypelist);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			//WebUTIL.setMapInSession(e.getEssentialMap(), _request);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
						
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

	/**
	 * getting the list of the designation already assigned 
	 * and the list of the designation not assigned
	 * @param _fb
	 * @param _request
	 */
	public static void getDeskMenu(ProfileTypeTabMappingFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		Map essentialMap=new HashMap();
		List addedDeskMenuList=null;
		//List notAddedDeskMenuList=null;
		String profileType=_fb.getProfileType();
		String fetchedList=new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			essentialMap=ProfileTypeTabMappingDATA.getDeskMenuForProfileMapping(profileType,userVO);
			addedDeskMenuList=(List)essentialMap.get(OpdConfig.DESK_MENU_ADDED_TO_PROFILE_TYPE_LIST);
			//notAddedDeskMenuList=(List)essentialMap.get(OpdConfig.DESK_MENU_NOT_ADDED_TO_PROFILE_TYPE_LIST);
			if(addedDeskMenuList!=null){
				for(int i=0;i<addedDeskMenuList.size();i++){
					Entry entry=(Entry)addedDeskMenuList.get(i);
					fetchedList=fetchedList+"%"+entry.getValue();
				}
			}
			_fb.setFetchedList(fetchedList.replaceFirst("%", ""));
			WebUTIL.setMapInSession(essentialMap,_request);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.NEW, "", e.getMessage());
						
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
	
	
	/**
	 * saves the mapping of the designation with the process type
	 * the record will be updated which has been shifted to left list box
	 * and the record will be inserted which has been shifted to right list box 
	 * @param _fb
	 * @param _request
	 */
	
	public static void saveProfileTabMapping(ProfileTypeTabMappingFB _fb,HttpServletRequest _request) {

		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		ProfileTypeTabMappingVO []insertProfileTypeTabMappingVO=null;
		ProfileTypeTabMappingVO []updateProfileTypeTabMappingVO=null;
		try
		{
			
			String fetchedList[]=_fb.getFetchedList().split("%");
			String selectedList[]=_fb.getSelectedDeskMenuId();
			if(selectedList==null)	selectedList=new String[0];
			String profileType=_fb.getProfileType();
			
			int len=0;
			boolean flag=false;
			//if(fetchedList.length==0){
				insertProfileTypeTabMappingVO=new ProfileTypeTabMappingVO[selectedList.length];
				for(int j=0;j<selectedList.length;j++){
					insertProfileTypeTabMappingVO[j]=new ProfileTypeTabMappingVO();
					insertProfileTypeTabMappingVO[j].setProfileType(profileType);
					insertProfileTypeTabMappingVO[j].setDeskMenuId(selectedList[j]);
					insertProfileTypeTabMappingVO[j].setProfileTabOrder(Integer.toString(j));
				}	
			/*}
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
			
			insertProfileTypeTabMappingVO=new ProfileTypeTabMappingVO[len];
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
					insertProfileTypeTabMappingVO[len]=new ProfileTypeTabMappingVO();
					insertProfileTypeTabMappingVO[len].setProfileType(profileType);
					insertProfileTypeTabMappingVO[len].setDeskMenuId(selectedList[j]);
					insertProfileTypeTabMappingVO[len].setProfileTabOrder(Integer.toString(j));
					len++;
				}	
			}
			}
			len=0;
			if(selectedList.length==0){
				updateProfileTypeTabMappingVO=new ProfileTypeTabMappingVO[fetchedList.length];
				for(int i=0;i<fetchedList.length;i++){
					updateProfileTypeTabMappingVO[i]=new ProfileTypeTabMappingVO();
					updateProfileTypeTabMappingVO[i].setProfileType(profileType);
					updateProfileTypeTabMappingVO[i].setDeskMenuId(fetchedList[i]);
					updateProfileTypeTabMappingVO[i].setProfileTabOrder(Integer.toString(i));
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
				
				updateProfileTypeTabMappingVO=new ProfileTypeTabMappingVO[len];
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
						updateProfileTypeTabMappingVO[len]=new ProfileTypeTabMappingVO();
						updateProfileTypeTabMappingVO[len].setProfileType(profileType);
						updateProfileTypeTabMappingVO[len].setDeskMenuId(fetchedList[i]);
						updateProfileTypeTabMappingVO[len].setProfileTabOrder(Integer.toString(i));
						len++;
					}	
				}
			}*/
				updateProfileTypeTabMappingVO=new ProfileTypeTabMappingVO[1];
				updateProfileTypeTabMappingVO[0]=new ProfileTypeTabMappingVO();
				updateProfileTypeTabMappingVO[0].setProfileType(profileType);
				
			ProfileTypeTabMappingDATA.saveProfileTypeTabMapping(insertProfileTypeTabMappingVO,updateProfileTypeTabMappingVO,userVO);
			objStatus.add(Status.TRANSINPROCESS,"","Record Saved Successfully");
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

	/*public static void getProcessWiseDesigForView(ProfileTypeTabMappingFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		Map essentialMap=new HashMap();
		String profileType=_fb.getProcessType();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			essentialMap=ProfileTypeTabMappingDATA.getProcessWiseDesigForView(profileType,userVO);
			_fb.setProfileType(essentialMap.get(MrdConfig.PROCESS_TYPE_NAME));
			WebUTIL.setMapInSession(essentialMap, _request);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			
			WebUTIL.setMapInSession(e.getEssentialMap(), _request);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.NEW, "", e.getMessage());
						
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
	}*/
	
}
		

