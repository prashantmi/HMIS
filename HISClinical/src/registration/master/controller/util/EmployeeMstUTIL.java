package registration.master.controller.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import registration.RegistrationConfig;
import registration.master.controller.data.EmployeeMstDATA;
import registration.master.controller.fb.EmployeeMasterFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.EmployeeMasterVO;
import hisglobal.vo.RoomMasterVO;
import hisglobal.vo.UserVO;

/**
 * Project   : MHIMS
 * Module 	 : Registration
 * Developer : Singaravelan
 * Creation Date : 25-Jul-2014
 * Process Name : Employee Master
 * 
 */

public class EmployeeMstUTIL extends ControllerUTIL{
	
	public static void getEmployeeEssentials(HttpServletRequest _rq)
	{
		Status objStatus=new Status();
		try{
		setSysdate(_rq);
		UserVO userVO= getUserVO( _rq);
		Map essentialMap=EmployeeMstDATA.getEssentialForEmployee(userVO);
		WebUTIL.setMapInSession(essentialMap, _rq);
		objStatus.add(Status.NEW,"","");
		}
		catch(HisRecordNotFoundException e){			
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());			
		}
		catch(HisDataAccessException e){			
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e){			
			objStatus.add(Status.ERROR_AE,"Exception","");
			}		
		catch(Exception e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in AddDeptMasterUTIL","");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}
	}
	
	public static void saveEmployeeDetail(EmployeeMasterFB _fb,HttpServletRequest _request){
		Status objStatus=new Status();		
		EmployeeMasterVO employeeMasterVO=new EmployeeMasterVO();
		try{
			HelperMethods.populate(employeeMasterVO,_fb);
			employeeMasterVO.setCountryCode("1");
			EmployeeMstDATA.saveEmployeeDetail(employeeMasterVO,getUserVO(_request));		
			objStatus.add(Status.NEW,"Employee Added Successfully","");	
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(_request,objStatus);
		}
		
	}
	
	public static void modifyEmployeeMasterEssentials(HttpServletRequest _rq,EmployeeMasterFB _fb){
		try{
	
			setSysdate(_rq);
			UserVO _userVO= getUserVO( _rq);
			//_fb.setEmpNo(_fb.getChk().substring(0, 4));
			String[] empNo=_fb.getChk().split("\\^");
			_fb.setEmpNo(empNo[0]);
			String _empCode=_fb.getEmpNo();
			getEmployeeEssentials(_rq); 

			Map essentialMap=EmployeeMstDATA.getEmployeeMasterDetails(_empCode, _userVO);
			WebUTIL.setMapInSession(essentialMap, _rq);		
			HelperMethods.populate(_fb, essentialMap.get(RegistrationConfig.EMPLOYEE_MST_EMPLOYEEVO));
			
			String mode="";
			if(!(_rq.getParameter("hmode")==null))			
				 mode=_rq.getParameter("hmode");
			else
				mode=(String)(WebUTIL.getSession(_rq).getAttribute("RegistrationConfig.VIEWORMODIFY"));
			
			if(mode.equalsIgnoreCase("view"))
			{
				WebUTIL.setAttributeInSession(_rq,RegistrationConfig.VIEWORMODIFY,"view");			
			}
			else{
				WebUTIL.setAttributeInSession(_rq,RegistrationConfig.VIEWORMODIFY,"modify");
			}		
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	
	public static boolean updateEmployeeMaster(EmployeeMasterFB _fb,HttpServletRequest _request){
		
			boolean flag=false;
			Status objStatus=new Status();			
			EmployeeMasterVO _empMasterVO=(EmployeeMasterVO) WebUTIL.getSession(_request).getAttribute(RegistrationConfig.EMPLOYEE_MST_EMPLOYEEVO);
			try{
				String[] empNo=_fb.getChk().split("\\^");
				_fb.setEmpNo(empNo[0]);
				//System.out.println(_fb.getChk().substring(0, 4));
				//_fb.setEmpNo(_fb.getChk().substring(0, 4));
				HelperMethods.populate(_empMasterVO,_fb);
				_empMasterVO.setCountryCode("1");
				EmployeeMstDATA.updateEmployeeMaster(_empMasterVO,getUserVO(_request));	
				objStatus.add(Status.DONE,"Employee Details Modified Successfully","");
				flag=true;
			} 
			catch(HisRecordNotFoundException e){			
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				_fb.setTransactionMode("MISTAKE");
			}
			catch(HisDataAccessException e){			
				objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
			}
			catch(HisApplicationExecutionException e){			
				objStatus.add(Status.ERROR_AE,"Exception","");
				}		
			catch(Exception e){	
				e.printStackTrace();
				objStatus.add(Status.ERROR,"Exception in UTIL","");
			}
			finally{
				WebUTIL.setStatus(_request,objStatus);
			}
		return flag;
	 }

}
