package mrd.masters.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.DoctorDesigMappingVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.masters.controller.data.ProcessWiseDesigMappingDATA;
import mrd.masters.controller.fb.ProcessWiseDesigMappingFB;

public class ProcessWiseDesigMappingUTL extends ControllerUTIL
{
	/**
	 * To get the List of the process Type
	 * @param _fb
	 * @param _request
	 */
	public static void getProcessType(ProcessWiseDesigMappingFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//Map essentialMap=new HashMap();
		List processTypelist=new ArrayList();
		try
		{
			//UserVO userVO = getUserVO(_request);
			processTypelist=ProcessWiseDesigMappingDATA.getProcessType();
			WebUTIL.setAttributeInSession(_request, MrdConfig.PROCESS_TYPE_LIST, processTypelist);
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
	public static void getAssignedDesignationList(ProcessWiseDesigMappingFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		//Map essentialMap=new HashMap();
		List assignedDesig=null;
		String processType=_fb.getProcessType();
		String fetchedList=new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			assignedDesig=(ArrayList)ProcessWiseDesigMappingDATA.getAssignedDesignationList(processType,userVO);
			if(assignedDesig!=null){
				for(int i=0;i<assignedDesig.size();i++){
					Entry entry=(Entry)assignedDesig.get(i);
					fetchedList=fetchedList+"%"+entry.getValue();
				}
			}
			_fb.setFetchedList(fetchedList.replaceFirst("%", ""));
			WebUTIL.setAttributeInSession(_request, MrdConfig.DESIGNATION_LIST_MAPPED, assignedDesig);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			
			WebUTIL.setAttributeInSession(_request, MrdConfig.DESIGNATION_LIST_MAPPED, assignedDesig);
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
	
	public static void getNotAssignedDesignationList(ProcessWiseDesigMappingFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		//Map essentialMap=new HashMap();
		List notAssignedDesig=null;
		String processType=_fb.getProcessType();
		//String fetchedList=new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			notAssignedDesig=ProcessWiseDesigMappingDATA.getNotAssignedDesignationList(processType,userVO);
			WebUTIL.setAttributeInSession(_request, MrdConfig.DESIGNATION_LIST_NOT_MAPPED, notAssignedDesig);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			
			WebUTIL.setAttributeInSession(_request, MrdConfig.DESIGNATION_LIST_NOT_MAPPED, notAssignedDesig);
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
	
	public static void saveProcessWiseDesig(ProcessWiseDesigMappingFB _fb,HttpServletRequest _request) {

		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		DoctorDesigMappingVO []insertDoctorDesigMappingVO=null;
		DoctorDesigMappingVO []updateDoctorDesigMappingVO=null;
		try
		{
			
			String fetchedList[]=_fb.getFetchedList().split("%");
			String selectedList[]=_fb.getSelectedDesignationId();
			if(selectedList==null)	selectedList=new String[0];
			String processType=_fb.getProcessType();
			
			int len=0;
			boolean flag=false;
			if(fetchedList.length==0){
				insertDoctorDesigMappingVO=new DoctorDesigMappingVO[selectedList.length];
				for(int j=0;j<selectedList.length;j++){
					insertDoctorDesigMappingVO[j]=new DoctorDesigMappingVO();
					insertDoctorDesigMappingVO[j].setProcessType(processType);
					insertDoctorDesigMappingVO[j].setDesignationId(selectedList[j]);
					
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
			
			insertDoctorDesigMappingVO=new DoctorDesigMappingVO[len];
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
					insertDoctorDesigMappingVO[len]=new DoctorDesigMappingVO();
					insertDoctorDesigMappingVO[len].setProcessType(processType);
					insertDoctorDesigMappingVO[len].setDesignationId(selectedList[j]);
					len++;
				}	
			}
			}
			len=0;
			if(selectedList.length==0){
				updateDoctorDesigMappingVO=new DoctorDesigMappingVO[fetchedList.length];
				for(int i=0;i<fetchedList.length;i++){
					updateDoctorDesigMappingVO[i]=new DoctorDesigMappingVO();
					updateDoctorDesigMappingVO[i].setProcessType(processType);
					updateDoctorDesigMappingVO[i].setDesignationId(fetchedList[i]);
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
				
				updateDoctorDesigMappingVO=new DoctorDesigMappingVO[len];
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
						updateDoctorDesigMappingVO[len]=new DoctorDesigMappingVO();
						updateDoctorDesigMappingVO[len].setProcessType(processType);
						updateDoctorDesigMappingVO[len].setDesignationId(fetchedList[i]);
						len++;
					}	
				}
			}
			
			ProcessWiseDesigMappingDATA.saveProcessWiseDesig(insertDoctorDesigMappingVO,updateDoctorDesigMappingVO,userVO);
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

	/*public static void getProcessWiseDesigForView(ProcessWiseDesigMappingFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		Map essentialMap=new HashMap();
		String processType=_fb.getProcessType();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			essentialMap=ProcessWiseDesigMappingDATA.getProcessWiseDesigForView(processType,userVO);
			_fb.setProcessType(essentialMap.get(MrdConfig.PROCESS_TYPE_NAME));
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
		

