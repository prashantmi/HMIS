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

import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import vo.registration.RoomVO;


public class RoomMstDATA 
{
	public static List getRoomEssentials()
	{
		List alList=null;
		try
		{
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO bo = new RegMasterBO();
			List locationList=bo.getLocation(uservo);			
			request.getSession().setAttribute("locationList",locationList);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return alList;

	}

	public static boolean saveRoomDtl(RoomVO roomModel,String strMode_p) 
	{

		String strmsgText = "";
		RoomVO objRoomModel;
		boolean bExistStatus=false;

		try 
		{
			objRoomModel= new RoomVO();			
			objRoomModel=roomModel;

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			objRoomModel.setStrHospitalCode(uservo.getHospitalCode());
			RegMasterBO bo = new RegMasterBO();
			bExistStatus=bo.saveRoomDtl(objRoomModel,strMode_p,uservo);

			if (objRoomModel.getStrMsgType()!=null && objRoomModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objRoomModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objRoomModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objRoomModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "RoomMstData.saveRoomDtl(vo) --> "+ e.getMessage();
			roomModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);

		} 
		finally 
		{
			objRoomModel = null;
		}
		return bExistStatus;
	}

	public static boolean updateRoomDtl(RoomVO roomModel,String strMode_p)
	{

		String strmsgText = "";
		RoomVO objLocModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try 
		{
			objLocModel= new RoomVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			RegMasterBO bo = new RegMasterBO();
			objLocModel=roomModel;
			objLocModel.setStrHospitalCode(uservo.getHospitalCode());
			bExistStatus=bo.updateRoomDtl(objLocModel,strMode_p,uservo);
			
			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objLocModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objLocModel.getStrRoomCode();
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
			strmsgText = "RoomMstData.updateRoomDtl(vo) --> "	+ e.getMessage();
			roomModel.setStrErrorMsg("Application Error [ERROR ID : " +strmsgText);
		}
		finally
		{
			objLocModel = null;
		}
		return bExistStatus;
	}

	//To get the Data for Modify Page
	public static RoomVO modifyRecord(HttpServletRequest request) 
	{
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		RoomVO locModel =new RoomVO();
		String strChk = "";
		Map mp= new LinkedHashMap();


		try {


			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			strTemp2 = strTemp[0].replace('@','#').split("#");
			locModel.setStrRoomCode(strTemp2[0]);
			locModel.setStrHospitalCode(strTemp2[1]);
			RegMasterBO bo = new RegMasterBO();
			RoomVO RoomVO_p=bo.modifyRecordRoomMst(locModel);

			if (locModel.getStrMsgType().equals("1")) {
				throw new Exception(locModel.getStrMsgString());
			}


			locModel.setStrRoomCode(RoomVO_p.getStrRoomCode());
			locModel.setStrRoomName(RoomVO_p.getStrRoomName());			
			locModel.setStrRoomDescription(RoomVO_p.getStrRoomDescription());
			locModel.setStrLocCode(RoomVO_p.getStrLocCode());
			locModel.setStrRoomId(RoomVO_p.getStrRoomId());
			
			//Audit Log Initiation
			String auditlogProcessId=RegistrationConfig.AUDIT_LOG_ROOM_MASTER;
			mp.put("save_1", locModel);
			//AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(request),request);


		} 
		catch (Exception e) 
		{
			strMsgText = "RoomMstData.modifyRecord(fb,request) --> "+ e.getMessage();
			locModel.setStrErrorMsg("Application Error [ERROR ID : " +strMsgText);
		}
		finally 
		{
			strMsgText = null;
		}
		return locModel;

	}



}
