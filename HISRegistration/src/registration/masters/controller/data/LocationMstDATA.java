package registration.masters.controller.data;
/**
 * @author s.singaravelan 	
 * Creation Date:- 02-Jan-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 */

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import vo.registration.LocationVO;


public class LocationMstDATA 
{

	private LocationVO modifyRecordLocationMst(LocationVO vo)
	{
		LocationVO LocationVO_p = null;
		RegMasterBO bo = new RegMasterBO();
		try
		{
			LocationVO_p=bo.modifyRecordLocationMst(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("regMasterBO.modifyRecordLocationMst(vo) --> " + vo.getStrMsgString());
			}
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	  
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch(HisDataAccessException e)
		{	
			e.printStackTrace();
			throw new HisDataAccessException();  	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
		}
		return LocationVO_p;
	}

	//To populate the values in the Department Master Screen Combos
	public static List getLocationEssentials(HttpServletRequest request )
	{
		List alList=null;
		try
		{
			ActionContext acx=ActionContext.getContext();
			request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO bo = new RegMasterBO();
			alList=bo.getLocationType(uservo);			
			request.getSession().setAttribute("locationType",alList);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return alList;

	}

	//To Save the Location Details 
	public static boolean saveLocationDtl(LocationVO locModel,String strMode_p) 
	{

		String strmsgText = "";
		LocationVO objLocModel;
		boolean bExistStatus=false;
		objLocModel= new LocationVO();	
		try {
					
			objLocModel=locModel;
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			//objLocModel.setStrHospitalCode(uservo.getHospitalCode());
			RegMasterBO bo = new RegMasterBO();
			bExistStatus=bo.saveLocationDtl(objLocModel,strMode_p,uservo);

			if (objLocModel.getStrMsgType()!=null && objLocModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objLocModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objLocModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objLocModel.setStrMsg("Data Saved Successfully");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText= "LocationMstDATA.saveLocationDtl(vo) --> "+ e.getMessage();
			objLocModel.setStrErrMsg(strmsgText);
			locModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " + strmsgText);
		} 
		finally 
		{
			objLocModel = null;
		}
		return bExistStatus;
	}

	//To update the Location Details
	public static boolean updateLocationDtl(LocationVO locModel,String strMode_p)
	{
		LocationVO objLocModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try 
		{
			objLocModel= new LocationVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			objLocModel=locModel;
			//objLocModel.setStrHospitalCode(uservo.getHospitalCode());
			RegMasterBO bo = new RegMasterBO();
			bExistStatus =  bo.updateLocationDtl(objLocModel, strMode_p, uservo);
			
			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objLocModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objLocModel.getStrLocCode();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
			}

			if (objLocModel.getStrMsgType()!=null && objLocModel.getStrMsgType().equals("1"))
			{
				throw new Exception(objLocModel.getStrMsgString());
			}

			if (bExistStatus == false) 
			{
				objLocModel.setStrWarning("Duplicate Name Exist..!");
			} 
			else 
			{
				objLocModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "LocationMstDATA.updateLocationDtl(vo) --> "+ e.getMessage();
			locModel.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objLocModel = null;
		}
		return bExistStatus;
	}

	//To get the Data for Modify Page
	public static LocationVO modifyRecord(HttpServletRequest request) 
	{
		LocationMstDATA LocationMstDATA_obj = null;
		String strMsgText = "";
		String strTemp[] = null;
		String strTemp2[] = null;
		LocationVO locModel =new LocationVO();
		String strChk = "";
		Map mp= new LinkedHashMap();

		try
		{
			LocationMstDATA_obj = new LocationMstDATA();
			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			locModel.setStrLocCode(strTemp2[0]);
			locModel.setStrHospitalCode(strTemp2[1]);
			LocationVO LocationVO_p=LocationMstDATA_obj.modifyRecordLocationMst(locModel);

			if (locModel.getStrMsgType().equals("1"))
			{
				throw new Exception(locModel.getStrMsgString());
			}
			locModel.setStrLocCode(LocationVO_p.getStrLocCode());
			locModel.setStrLocDescription(LocationVO_p.getStrLocDescription());			
			locModel.setStrLocTypeCode(LocationVO_p.getStrLocTypeCode());
			locModel.setStrLandmark(LocationVO_p.getStrLandmark());
			locModel.setStrFloor(LocationVO_p.getStrFloor());
			locModel.setStrBuilding(LocationVO_p.getStrBuilding());
			locModel.setStrBlock(LocationVO_p.getStrBlock());
			//locModel.setStrIsValid(strCombos[0]);
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_LOCATION_MASTER;
			mp.put("save_1", locModel);
			//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);

		} 
		catch (Exception e) 
		{
			strMsgText = "LocationMstDATA.modifyRecord(fb,request) --> "	+ e.getMessage();
			locModel.setStrErrorMsg("Application Error [ERROR ID : " + strMsgText);
		}
		finally 
		{
			LocationMstDATA_obj = null;
			strMsgText = null;
		}
		return locModel;

	}





}
