package registration.masters.controller.data;

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
import registration.bo.ApptTypeBO;
import registration.config.RegistrationConfig;
import vo.registration.ApptTypeVO;


public class ApptTypeMstDATA {
	
	public static List getApptTypeEssentials()
	{
		List alList=null;
		try
		{
			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			ApptTypeBO bo = new ApptTypeBO();
			/*List locationList=bo.getLocation(uservo);			
			request.getSession().setAttribute("locationList",locationList);*/
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return alList;

	}
	public static ApptTypeVO modifyRecord(HttpServletRequest request) 
	{
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		ApptTypeVO locModel =new ApptTypeVO();
		String strChk = "";
		Map mp= new LinkedHashMap();


		try {


			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			
			locModel.setStrApptTypeId(strTemp[0]);
			ApptTypeBO bo = new ApptTypeBO();
			ApptTypeVO ApptTypeVO_p=bo.modifyRecordApptTypeMst(locModel);

			if (locModel.getStrMsgType().equals("1")) {
				throw new Exception(locModel.getStrMsgString());
			}


		} 
		catch (Exception e) 
		{
			strMsgText = "ApptTypeMstData.modifyRecord(fb,request) --> "+ e.getMessage();
			locModel.setStrErrorMsg("Application Error [ERROR ID : " +strMsgText);
		}
		finally 
		{
			strMsgText = null;
		}
		return locModel;

	}

	public static boolean saveApptTypeDtl(ApptTypeVO apptTypeModel,String strMode_p) 
	{

		String strmsgText = "";
		ApptTypeVO objApptTypeModel;
		boolean bExistStatus=false;

		try 
		{
			objApptTypeModel= new ApptTypeVO();		
			objApptTypeModel=apptTypeModel;

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	

			//objApptTypeModel.setStrHospitalCode(uservo.getHospitalCode());
			ApptTypeBO bo = new ApptTypeBO();
			bExistStatus=bo.saveApptTypeDtl(objApptTypeModel,strMode_p,uservo);

			if (objApptTypeModel.getStrMsgType()!=null && objApptTypeModel.getStrMsgType().equals("1")) 
			{
				throw new Exception(objApptTypeModel.getStrMsgString());
			}	
			if (bExistStatus == false) 
			{
				objApptTypeModel.setStrWarning("Duplicate Name Exist..!");
			} else 
			{
				objApptTypeModel.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "ApptTypeMstData.saveApptTypeDtl(vo) --> "+ e.getMessage();
			apptTypeModel.setStrErrorMsg("Application Error ,Contact System Administrator!  " +strmsgText);

		} 
		finally 
		{
			objApptTypeModel = null;
		}
		return bExistStatus;
	}

	public static boolean updateApptTypeDtl(ApptTypeVO apptTypeModel,String strMode_p)
	{

		String strmsgText = "";
		ApptTypeVO objLocModel;
		boolean bExistStatus=false;
		Map mp= new LinkedHashMap();

		try 
		{
			objLocModel= new ApptTypeVO();

			ActionContext acx=ActionContext.getContext();
			HttpServletRequest request=(HttpServletRequest)acx.get(ServletActionContext.HTTP_REQUEST);
			UserVO uservo = ControllerUTIL.getUserVO(request);	
			ApptTypeBO bo = new ApptTypeBO();
			objLocModel=apptTypeModel;
			bExistStatus=bo.updateApptTypeDtl(objLocModel,strMode_p,uservo);
			
			//Audit Log on update
			if(bExistStatus)
			{
				mp.put("save_1"  , objLocModel);
				String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=objLocModel.getStrApptTypeId();
				//AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(request),request,arrKeyVariables);
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
			strmsgText = "ApptTypeMstData.updateApptTypeDtl(vo) --> "	+ e.getMessage();
			apptTypeModel.setStrErrorMsg("Application Error [ERROR ID : " +strmsgText);
		}
		finally
		{
			objLocModel = null;
		}
		return bExistStatus;
	}

	



}
