package appointment.masters.controller.data;

/**
 * Created By 	: Neha Sharma
 * Date			: 20-Jan-2014
 */
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.appointment.ApptConfigMstVO;
import appointment.bo.ApptConfigMstBO;
import appointment.config.AppointmentConfig;


public class ApptConfigMstDATA 
{

	public static void getApptTypeList(HttpServletRequest objRequest)
	{
		ApptConfigMstBO bo = new ApptConfigMstBO();
		@SuppressWarnings("rawtypes")
		List appTypeList=bo.getApptTypeList();
		
		objRequest.getSession().setAttribute("apptTypeList",appTypeList);
	}
	
	public static void getApptDurationList(HttpServletRequest objRequest)
	{
		ApptConfigMstBO bo = new ApptConfigMstBO();
		@SuppressWarnings("rawtypes")
		List appDurationList=bo.getApptDurationList();
		
		objRequest.getSession().setAttribute("apptDurationList",appDurationList);
	}
	
	public static void getApptShiftList(HttpServletRequest objRequest)
	{
		ApptConfigMstBO bo = new ApptConfigMstBO();
		@SuppressWarnings("rawtypes")
		List appShiftList=bo.getApptShiftList();
		
		objRequest.getSession().setAttribute("apptShiftList",appShiftList);
	}
	
	public static void getApptTypeModList(HttpServletRequest objRequest)
	{
		try{
		String strTemp[] = null;
		ApptConfigMstVO apptMstVo =new ApptConfigMstVO();
		String strChk = "";
		ApptConfigMstBO bo = new ApptConfigMstBO();
		
		strChk = objRequest.getParameter("chk");
		strTemp = strChk.replace('$', '#').split("#");
		
		//set id for which data is to be modified..cz we have pararefid@hospcode
		apptMstVo.setParaRefId((strTemp[0].split("@"))[0]);
		apptMstVo.setHospCode((strTemp[0].split("@"))[1]);
		
		@SuppressWarnings("rawtypes")
		List appTypeModList=bo.getApptTypeModList(apptMstVo);
		
		objRequest.getSession().setAttribute("apptTypeModList",appTypeModList);
		}
		catch (HisRecordNotFoundException e) 
		{
			objRequest.getSession().setAttribute("apptTypeModList",null);
		}
		catch(Exception e){
			
		}
	}
	
	public static void getApptTypeSelModList(HttpServletRequest objRequest)
	{
		String strTemp[] = null;
		ApptConfigMstVO apptMstVo =new ApptConfigMstVO();
		String strChk = "";
		ApptConfigMstBO bo = new ApptConfigMstBO();
		
		strChk = objRequest.getParameter("chk");
		strTemp = strChk.replace('$', '#').split("#");
		
		//set id for which data is to be modified..cz we have pararefid@hospcode
		apptMstVo.setParaRefId((strTemp[0].split("@"))[0]);
		apptMstVo.setHospCode((strTemp[0].split("@"))[1]);
		
		@SuppressWarnings("rawtypes")
		List appTypeSelModList=bo.getApptTypeSelModList(apptMstVo);
		
		objRequest.getSession().setAttribute("apptTypeSelModList",appTypeSelModList);
	}
	
	// default appt type list fetch..modify page..
	public static void getDefaultApptTypeModList(HttpServletRequest objRequest)
	{
		String strTemp[] = null;
		ApptConfigMstVO apptMstVo =new ApptConfigMstVO();
		String strChk = "";
		ApptConfigMstBO bo = new ApptConfigMstBO();
		
		strChk = objRequest.getParameter("chk");
		strTemp = strChk.replace('$', '#').split("#");
		
		//set id for which data is to be modified..cz we have pararefid@hospcode
		apptMstVo.setParaRefId((strTemp[0].split("@"))[0]);
		apptMstVo.setHospCode((strTemp[0].split("@"))[1]);
		
		@SuppressWarnings("rawtypes")
		List appTypeSelModList=bo.getDefaultApptTypeModList(apptMstVo);
		
		objRequest.getSession().setAttribute("apptTypeDefModList",appTypeSelModList);
	}
	
	public static Boolean saveConfigDtl(HttpServletRequest objHttpServletRequest,ApptConfigMstVO apptConfgMstVo) 
	{
		String strmsgText = "";
		ApptConfigMstVO apptConfigMstObj = new ApptConfigMstVO();
		ApptConfigMstBO bo = new ApptConfigMstBO();
		Boolean retValue=false;

		try 
		{
			apptConfigMstObj= new ApptConfigMstVO();
			apptConfigMstObj=apptConfgMstVo;
			
			UserVO userVo= ControllerUTIL.getUserVO(objHttpServletRequest);
			
			retValue=bo.saveConfigDtl(apptConfgMstVo,userVo);

			if (apptConfigMstObj.getStrMsgType()!=null && apptConfigMstObj.getStrMsgType().equals("1")) 
			{
				retValue=false;
			
				throw new Exception(apptConfigMstObj.getStrMsgString());
			}			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "AppConfMstAction.AppConfMstData.saveConfigDtl(vo) --> "+ e.getMessage();
			apptConfigMstObj.setStrErrorMsg("Application Error .Contact System Administrator."+strmsgText);
					
		} 
		finally 
		{
			apptConfigMstObj = null;
		}
		
		return retValue;
		
	}
	
	// to fetch details of the record selected for modification..
	public static void modifyRecord( HttpServletRequest request,ApptConfigMstVO apptMstVo) 
	{
		String strMsgText = "";
		String strTemp[] = null;
		String strTemp2[] = null;
		
		String strChk = "";
		ApptConfigMstBO bo = new ApptConfigMstBO();
		
		try 
		{
			strChk = request.getParameter("chk");
			strTemp = strChk.replace('$', '#').split("#");
			
			//set id for which data is to be modified..cz we have pararefid@hospcode
			apptMstVo.setParaRefId((strTemp[0].split("@"))[0]);
			apptMstVo.setHospCode((strTemp[0].split("@"))[1]);
			
			UserVO userVo= ControllerUTIL.getUserVO(request);

			apptMstVo=bo.modifyRecord(apptMstVo,userVo,request);
			
			if(apptMstVo.getStrMsgType() != null && apptMstVo.getStrMsgType().equals("1") )
			{
				apptMstVo.setStrMsgString("ApptConfMstData.modifyRecord(vo) --> " + apptMstVo.getStrMsgString());
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strMsgText = "ApptConfMstData.modifyRecord() --> "	+ e.getMessage();
			
			apptMstVo.setStrErrorMsg("Application Error .Contact System Administrator.");

		}
		finally 
		{
			strMsgText = null;
		}
		
		

	}
	
	// to update details..i.e modify existing data..
	public static boolean update(HttpServletRequest objRequest, ApptConfigMstVO apptConfMstVo)
	{
		String strmsgText = "";
		ApptConfigMstVO apptConfMstObj=new ApptConfigMstVO();
		boolean bExistStatus=false;
		ApptConfigMstBO bo = new ApptConfigMstBO();
		try {
			apptConfMstObj= new ApptConfigMstVO();

			apptConfMstObj=apptConfMstVo;
			
			UserVO uservo = ControllerUTIL.getUserVO(objRequest);
			
			bExistStatus=bo.update(apptConfMstVo,uservo);

			if (apptConfMstObj.getStrMsgType()!=null && apptConfMstObj.getStrMsgType().equals("1"))
			{
				throw new Exception(apptConfMstObj.getStrMsgString());
			}
			
			apptConfMstObj.setStrMsg("Data successfully updated");
	
			} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "AppConfMstUtil -->update() "+ e.getMessage();
			
			apptConfMstObj.setStrErrorMsg("Application Error [ERROR ID : ");
			
		} finally 
		{
			apptConfMstVo = null;
		}
		return bExistStatus;
	}

	public static void getOPDRosterSchedule(HttpServletRequest request,HttpServletResponse response, ApptConfigMstVO apptConfigMstVo) {
			ApptConfigMstBO bo = new ApptConfigMstBO();
			String schedule="";
			String scheduleData="";
			UserVO uservo = ControllerUTIL.getUserVO(request);
			if(apptConfigMstVo.getAppointmentForId().equals(AppointmentConfig.APPOINTMENT_ID_FOR_OPD)){
				scheduleData=bo.getOPDRosterSchedule(apptConfigMstVo.getFinalParaId(),uservo);
				if(scheduleData!=null && !scheduleData.equals("")){	
					schedule+="<div class='div-table-row listHeader' >";
					schedule+="<div class='div-table-col' style='width: 10%;'>";
					schedule+="<b>Shift</b>";
					schedule+="</div>";
					schedule+="<div class='div-table-col' style='width: 10%;'>";
					schedule+="<b>Time</b>"; 
					schedule+="</div>";
					schedule+="<div class='div-table-col' style='width: 10%;' >";
					schedule+="Week of Month";
					schedule+="</div>";
					schedule+="<div class='div-table-col' style='width: 15%;'>";
					schedule+="<b>Days Of Week</b>"; 
					schedule+="</div>";
					schedule+="<div class='div-table-col' style='width: 9%;'>";
					schedule+="<b>Current Date Appt.</b>"; 
					schedule+="</div>";
					schedule+="<div class='div-table-col' style='width: 8%;'>";
					schedule+="<b>Prior Appt.</b>"; 
					schedule+="</div>";
					/*schedule+="<div class='div-table-col' style='width: 8%;'>";
					schedule+="<b>Overbook</b>"; 
					schedule+="</div>";*/
					schedule+="<div class='div-table-col' style='width: 8%;'>";
					schedule+="<b>Portal Appt.</b>"; 
					schedule+="</div>";
					
					schedule+="<div class='div-table-col' style='width: 8%;'>";
					schedule+="<b>OPD</b>"; 
					schedule+="</div>";
					schedule+="<div class='div-table-col' style='width: 8%;'>";
					schedule+="<b>IPD</b>"; 
					schedule+="</div>";
					schedule+="<div class='div-table-col' style='width: 8%;'>";
					schedule+="<b>EMG</b>"; 
					schedule+="</div>";
					
					schedule+="<div class='div-table-col' style='width: 10%;display:none;' >";
					schedule+="<b>VIP Slot Allowed</b>"; 
					schedule+="</div>";			
					schedule+="</div>";
					schedule+=scheduleData;
					System.out.println("schedule---" + schedule);
					scheduleData=null;
				}
			}
			WebUTIL.writeResponse(response, schedule,null);
		}	
}
