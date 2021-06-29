package registration.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import registration.controller.data.SearchByNameDATA;
import registration.controller.fb.SearchByNameFB;

public class SearchByNameUTIL extends ControllerUTIL {
	
	//get the list of gender, age range and location 
	public static void getEssentialForSearch(SearchByNameFB _fb,HttpServletRequest request){
		Status status = new Status();
		Map essentialMap=new HashMap();
		try{
		UserVO userVO =getUserVO(request);
		setSysdate(request);
		essentialMap=SearchByNameDATA.getEssentialForSearch(userVO);
		WebUTIL.setMapInSession(essentialMap, request);
		_fb.setGnum_hospital_code(userVO.getHospitalCode());
		status.add(Status.NEW);
		
		}
		catch(HisRecordNotFoundException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA, "", e.getMessage());
			
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA, "Application execution error", "");
			
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA, "Data Access error", "");
			
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.ERROR_DA, "Error", "");
			
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
	}
	
	/**
	 * sets Patient Detail by name
	 * @param _fb -SearchByNameFB form bean
	 * @param request -HttpServletRequest
	 */
	public static void setPatientDtlByName( SearchByNameFB _fb,HttpServletRequest request){
		Status status = new Status();
		try{
			UserVO userVO =getUserVO(request);
			PatientVO[] patVO=new PatientVO[]{};
			/*String searchName=_fb.getSearchName();
		String searchContactNo=_fb.getSearchContactNo();
		String searchNationalID=_fb.getSearchNationalID();
		String searchEmployeelID=_fb.getSearchEmployeeID();
		if(searchName==null || searchName.trim().equals(""))
			if( searchContactNo==null || searchContactNo.trim().equals(""))
				if(searchNationalID==null || searchNationalID.trim().equals(""))
					if(searchEmployeelID==null || searchEmployeelID.trim().equals(""))
					System.out.println("gfhfh");
					else
						patVO=SearchByNameDATA.getPatientByEmployeelID(searchEmployeelID,userVO);
				else
					patVO=SearchByNameDATA.getPatientBynationalID(searchNationalID,userVO);
			else
				patVO=SearchByNameDATA.getPatientByContactNo(searchContactNo, userVO);
		else
			patVO=SearchByNameDATA.getPatientDtlByName(searchName,userVO);*/
			HashMap searchMap=HelperMethods.createQueryMapFromVO(_fb);
			searchMap.remove("hmode");
			searchMap.remove("crNoToRetrieve");
			patVO=SearchByNameDATA.searchPatient(searchMap,userVO);
			HttpSession ses=request.getSession();
			ses.setAttribute(RegistrationConfig.arrPATIENT_VO,patVO);
			
			status.add(Status.LIST);
			
		}
		catch(HisRecordNotFoundException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA, "", e.getMessage());
			
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA, "Application execution error", "");
			
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			status.add(Status.ERROR_DA, "Data Access error", "");
			
		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.ERROR_DA, "Error", "");
			
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
	}
	
	
	
}
