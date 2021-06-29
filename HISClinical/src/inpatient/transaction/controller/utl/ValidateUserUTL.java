package inpatient.transaction.controller.utl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import startup.login;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import inpatient.transaction.controller.data.ValidateUserDATA;
import inpatient.transaction.controller.fb.ValidateUserFB;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.NursingDeskUserVO;
import hisglobal.vo.TransactionVO;
import hisglobal.vo.UserVO;
import hissso.client.filter.HISSSOClientRequestFilter;

public class ValidateUserUTL extends ControllerUTIL
{
	public static boolean getUserList(HttpServletRequest _request,ValidateUserFB _fb)
	{
		Status objStatus = new Status();		
		try
		{
			setSysdate(_request);
			String wardCode=(String)_request.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE);
			
			//getUserRoleIds
			
			List<UserVO> userList=ValidateUserDATA.getUserList(getUserVO(_request),wardCode);
			//WebUTIL.setAttributeInSession(_request, DynamicDeskConfig.ESSENTIAL_BO_LOGIN_USER_ROLEIDLIST, userList);
			_request.getSession().setAttribute(DynamicDeskConfig.ESSENTIAL_BO_LOGIN_USER_ROLEIDLIST, userList);	
			Iterator<UserVO> itr=userList.iterator();
			String response="";
			String userRoleIds="";
			while(itr.hasNext())
			{
				UserVO objEntry=(UserVO)itr.next();
				response=response+"<option value=\""+objEntry.getUserId() +"\">" +objEntry.getUserName()+"</option>";
				
			
			}
			//WebUTIL.setAttributeInSession(_request, InpatientConfig.ESSENTIAL_BO_LOGIN_USER_LIST,response);
			//_request.getSession().setAttribute(DynamicDeskConfig.ESSENTIAL_BO_LOGIN_USER_ROLEIDLIST, userRoleIds);
			_request.getSession().setAttribute(DynamicDeskConfig.ESSENTIAL_BO_LOGIN_USER_LIST, response);
		}
		catch (HisRecordNotFoundException e)
		{
			_request.setAttribute("MESSAGE", e.getMessage());
			_request.getSession().setAttribute(DynamicDeskConfig.ESSENTIAL_BO_LOGIN_USER_LIST, "");
			System.out.println("Inside HisRecordNotFoundException");
			e.printStackTrace();
			//objStatus.add(Status.UNSUCESSFULL, "","User Not found");
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
			e.printStackTrace();
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
	
	
	public static boolean valideteUser(HttpServletRequest _request,ValidateUserFB _fb)
	{
		Status objStatus = new Status();
		boolean inValidFlag=true;
		boolean inValidRoleFLag=true;
		String menuroleid="";
		String temp="";
		String[] role=null;
		try
		{
			
		//	setSysdate(_request);
		

			  
			  login objLogin=new login();
			  objLogin.setUid(_request.getParameter("uid"));
			  objLogin.setPwd(_request.getParameter("pwd"));
			  
			  objLogin=objLogin.validateUid(_request.getRemoteAddr());
			  String msg="";
			  
			  
//in case of invalid user 
			  
	if(objLogin.getSeatId().equals("NONE"))
		{
		msg="Invalid User Id or Password";
		inValidFlag=false;
		_fb.setHmode("NEW");
		_fb.setTargetMode("");
		}
	else //in case of valid user 
		{
		_request.setAttribute("VALID_USER", "YES");
		if(_fb.getMenuRoleId()!=null && _fb.getMenuRoleId()!="")
		{
			List<UserVO> userList = (List<UserVO>)_request.getSession().getAttribute(DynamicDeskConfig.ESSENTIAL_BO_LOGIN_USER_ROLEIDLIST);	
			Iterator<UserVO> itr=userList.iterator();
			while(itr.hasNext())
			{
				UserVO objEntry=(UserVO)itr.next();
				String loginuid=objLogin.getUserId();
				String loginUid2=objEntry.getUserId();
				 menuroleid=_fb.getMenuRoleId().toString();
				
				if(loginuid.equals(loginUid2))
				{
					 role=objEntry.getUserRoleIds().split("#");
					for(int i=0;i<role.length;i++)
					{
						 temp = role[i];
						
							if(menuroleid.equals(temp.trim()))
						{
							_fb.setTargetMode("VALIDROLE");
							inValidRoleFLag=false;
							break;
						}
					}
						
					
				}
				
			}
			if(inValidRoleFLag)
			{
				msg="User Role Not Mapped To Menu";
				_fb.setTargetMode("");
			}
		}
		else
		{
		_fb.setTargetMode("VALIDROLE");
		//ValidateUserUTL.setNursingDeskUserVO(_request, objLogin);
		}
	    if(_fb.getTargetMode()!=null && _fb.getTargetMode()!="")
		ValidateUserUTL.setNursingDeskUserVO(_request, objLogin);
     	}
	    _request.setAttribute("MESSAGE", msg);
		objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "");
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
		return inValidFlag;
	}	

	public static void setNursingDeskUserVO(HttpServletRequest _request,login _objLogin)
	{
		 UserVO _userVO = new UserVO();
		 HISSSOClientRequestFilter.populateData(_userVO, _objLogin);
		
		 /*HttpSession session = _request.getSession();
		session.removeAttribute(DynamicDeskConfig.DYNAMIC_DESK_USER_VO);
		*/
		 DynamicDeskUTIL.setAttributeInSession(_request,DynamicDeskConfig.DYNAMIC_DESK_USER_VO, _userVO);
		
		
		
	}
	
	
	/*public static void setNursingDeskUserVO(HttpServletRequest _request,login _objLogin)
	{
		UserVO _userVO = new UserVO();
		
	
		//_userVO.setUserId(_objLogin.getUserId());
		_userVO.setUserSeatId(_objLogin.getSeatId());
		_userVO.setUserName(_objLogin.getUserFullName());
		_userVO.setSeatId(_objLogin.getUserId());

		//_userVO.setTariffID(objLogin.gett)
		_userVO.setUserLevel(_objLogin.getUserLevel());
		_userVO.setUserEmpID(_objLogin.getEmpId());
		//_userVO.setTariffID(objLogin.gett)
		_userVO.setHospitalCode(_objLogin.getHospitalCode());
		_userVO.setIpAddress(_request.getRemoteAddr());
		
		
		TransactionVO transactionVO = _userVO.getTransactionInfo();
		if (transactionVO == null)
		{
			transactionVO = new TransactionVO();
			_userVO.setTransactionInfo(transactionVO);
		}
		String menuID = (String) _request.getSession().getAttribute(Config.MENU_ID);
		if (menuID == null) menuID = "10000";
		transactionVO.setMenuID(menuID);
		
		DynamicDeskUTIL.setAttributeInSession(_request,DynamicDeskConfig.DYNAMIC_DESK_USER_VO, _userVO);
		
		
		
	}*/
}
