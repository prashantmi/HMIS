package dutyroster.masters.controller.utl;

import hisglobal.backutil.HelperMethods;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.BlockAreaMstVO;
import hisglobal.vo.UserVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.controller.data.BlockAreaMstDATA;
import dutyroster.masters.controller.fb.BlockAreaMstFB;

public class BlockAreaMstUTL extends ControllerUTIL
{
	
	public static void getEssentials(BlockAreaMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map essentialMap=new HashMap();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			essentialMap=BlockAreaMstDATA.getBlockAreaEssentials(userVO);
			WebUTIL.setMapInSession(essentialMap, _request);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setMapInSession(e.getEssentialMap(), _request);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
						
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

	public static void getAreaCodeNotAssigned(BlockAreaMstFB _fb,HttpServletRequest _request) {
		
		Status objStatus = new Status();
		List areaCodeNotSelectedlist=new ArrayList();		
		List assignedAreaCodelist=new ArrayList();	
		String [] array;
		String fetchedList="";
		try
		{
			UserVO userVO = getUserVO(_request);
			String dutyAreaType=_fb.getAreaTypeCode();
			areaCodeNotSelectedlist=BlockAreaMstDATA.getAreaCodeNotSelected(dutyAreaType, userVO);
			assignedAreaCodelist=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.DUTY_AREA_CODE_SELECTED_IN_BLOCK_AREA);
			int len=0;
			for(int i=0;i<assignedAreaCodelist.size();i++){
				len++;
			}
			array=new String[len];
			for(int i=0;i<assignedAreaCodelist.size();i++){
				Entry entry=new Entry();
				entry=(Entry)assignedAreaCodelist.get(i);
				array[i]=entry.getValue().split("@")[0];
			}
			areaCodeNotSelectedlist=(ArrayList)WebUTIL.removeEntriesfromOptions(areaCodeNotSelectedlist, array);
			
			for(int i=0;i<assignedAreaCodelist.size();i++){
				Entry entry=new Entry();
				entry=(Entry)assignedAreaCodelist.get(i);
				array[i]=entry.getValue();
			}
			for(int i=0;i<array.length;i++){
				fetchedList=fetchedList + array[i]+"%";
			}
			_fb.setFetchedList(fetchedList);
			if(areaCodeNotSelectedlist.size()==0)
				throw new HisRecordNotFoundException("Either No Duty Area Code Found or All are mapped");
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_AREA_CODE_NOT_SELECTED_IN_BLOCK_AREA, areaCodeNotSelectedlist);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_AREA_CODE_NOT_SELECTED_IN_BLOCK_AREA, areaCodeNotSelectedlist);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
						
		}
		catch (HisDataAccessException e)
		{
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_AREA_CODE_NOT_SELECTED_IN_BLOCK_AREA, areaCodeNotSelectedlist);
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			
		}
		catch (HisApplicationExecutionException e)
		{
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_AREA_CODE_NOT_SELECTED_IN_BLOCK_AREA, areaCodeNotSelectedlist);
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
	
	public static void getAssignedAreaCode(BlockAreaMstFB _fb,HttpServletRequest _request) {
		
		Status objStatus = new Status();
		List areaCodeSelectedlist=new ArrayList();		
		try
		{
			UserVO userVO = getUserVO(_request);
			String dutyAreaType=_fb.getAreaTypeCode();
			BlockAreaMstVO blockAreaVO=new BlockAreaMstVO();
			HelperMethods.populate(blockAreaVO, _fb);
			areaCodeSelectedlist=BlockAreaMstDATA.getAssignedAreaCode(blockAreaVO, userVO);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_AREA_CODE_SELECTED_IN_BLOCK_AREA, areaCodeSelectedlist);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_AREA_CODE_SELECTED_IN_BLOCK_AREA, areaCodeSelectedlist);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL);
			
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
	
	
	public static void saveBlockArea(BlockAreaMstFB _fb,HttpServletRequest _request) 
	{
			Status objStatus = new Status();
			BlockAreaMstVO insertBlockAreaVO[]=null;
			BlockAreaMstVO updateBlockAreaVO[]=null;
			int len=0;
			String fetchedList[]=_fb.getFetchedList().split("%");
			String selectedList[]=_fb.getSelectedAreaCode();
			boolean flag=true;
			
			for(int i=0;i<selectedList.length;i++){
				for(int j=0;j<fetchedList.length;j++){
					if(selectedList[i].split("@")[0].equals(fetchedList[j].split("@")[0])){
						flag=true;
						break;
					}
					else{
						flag=false;
					}
				}if(!flag){
					len++;
				}
			}	
			insertBlockAreaVO=new BlockAreaMstVO[len];
			len=0;
			for(int i=0;i<selectedList.length;i++){
				for(int j=0;j<fetchedList.length;j++){
					if(selectedList[i].split("@")[0].equals(fetchedList[j].split("@")[0])){
						flag=true;
						break;
					}
					else{
						flag=false;
					}
				}if(!flag){
					insertBlockAreaVO[len]=new BlockAreaMstVO();
					insertBlockAreaVO[len].setBlockId(_fb.getBlockId());
					insertBlockAreaVO[len].setAreaTypeCode(_fb.getAreaTypeCode());
					insertBlockAreaVO[len].setAreaCode(selectedList[i].split("@")[0]);
					len++;
				}
			}	
			
			len=0;
			if(!fetchedList[0].equals("")){
				for(int i=0;i<fetchedList.length;i++){
					for(int j=0;j<selectedList.length;j++){
						if(fetchedList[i].split("@")[0].equals(selectedList[j].split("@")[0])){
							flag=true;
							break;
						}
						else{
							flag=false;
						}
					}if(!flag){
						len++;
					}
				}	
			}
			updateBlockAreaVO=new BlockAreaMstVO[len];
			len=0;
			if(!fetchedList[0].equals("")){	
				for(int i=0;i<fetchedList.length;i++){
					for(int j=0;j<selectedList.length;j++){
						if(fetchedList[i].split("@")[0].equals(selectedList[j].split("@")[0])){
							flag=true;
							break;
						}
						else{
							flag=false;
						}
					}if(!flag){
						updateBlockAreaVO[len]=new BlockAreaMstVO();
						updateBlockAreaVO[len].setBlockId(_fb.getBlockId());
						updateBlockAreaVO[len].setAreaCode(fetchedList[i].split("@")[0]);
						updateBlockAreaVO[len].setWorkPrefrence(fetchedList[i].split("@")[1]);
						len++;
					}
				}
			}
			try
			{
				UserVO userVO = getUserVO(_request);
				BlockAreaMstDATA.saveBlockArea(insertBlockAreaVO,updateBlockAreaVO,userVO);
				objStatus.add(Status.TRANSINPROCESS,"","Record Added Successfully");
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
	
	public static void getBlockArea(BlockAreaMstFB _fb,HttpServletRequest _request) {
		
		Status objStatus = new Status();
		BlockAreaMstVO [] blockAreaVOs=null;		
		try
		{
			UserVO userVO = getUserVO(_request);
			String dutyBlockId=_fb.getBlockId();
			blockAreaVOs=BlockAreaMstDATA.getBlockArea(dutyBlockId, userVO);
			if(blockAreaVOs!=null)
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.BLOCK_AREA_DETAIL, blockAreaVOs);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			blockAreaVOs=new BlockAreaMstVO[0];
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.BLOCK_AREA_DETAIL, blockAreaVOs);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found");
						
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

	public static void getAreaCode(BlockAreaMstFB _fb,HttpServletRequest _request) {
	
		Status objStatus = new Status();
		List areaCodelist=new ArrayList();		
		try
		{
			UserVO userVO = getUserVO(_request);
			areaCodelist=BlockAreaMstDATA.getAreaCode(_fb.getBlockId(), userVO);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_AREA_CODE, areaCodelist);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_AREA_CODE, areaCodelist);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
			
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
	
	public static void saveChangeWorkPreference(BlockAreaMstFB _fb,HttpServletRequest _request) {
		
		Status objStatus = new Status();
		BlockAreaMstVO updateBlockAreaVOs[]=null;
		
		try
		{
			UserVO userVO = getUserVO(_request);
			String dutyAreaType=_fb.getAreaTypeCode();
			String [] selectedAreaCode=_fb.getSelectedAreaCode();
			int len=0;
			for(int i=0;i<selectedAreaCode.length;i++){
				len++;
			}	
			updateBlockAreaVOs=new BlockAreaMstVO[len];
			len=0;
			for(int i=0;i<selectedAreaCode.length;i++){
				updateBlockAreaVOs[len]=new BlockAreaMstVO();
				updateBlockAreaVOs[len].setBlockId(_fb.getBlockId());
				updateBlockAreaVOs[len].setAreaCode(selectedAreaCode[i]);
				//updateBlockAreaVOs[len].setWorkPrefrence(selectedAreaCode[i].split("@")[1]);
				len++;
			}
			
			BlockAreaMstDATA.saveChangeWorkPreference(updateBlockAreaVOs, userVO);
			objStatus.add(Status.TRANSINPROCESS,"","Work Preference Modified Successfully");
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
		
}
		

