package opd.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.Apt_appointmentDtlVO;
import hisglobal.vo.Apt_slotDtlVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdNextVisitAppointmentDATA;
import opd.transaction.controller.fb.OpdNextVisitAppointmentFB;
import registration.RegistrationConfig;

public class OpdNextVisitAppointmentUTIL extends ControllerUTIL {
	
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	
	public static void getEssentials(HttpServletRequest _rq,OpdNextVisitAppointmentFB _fb )
	{
		Status objStatus=new Status();
		HttpSession session=_rq.getSession();
		PatientDetailVO[] dailyPatientVOs = null;
		try{
		setSysdate(_rq);
		//UserVO userVO=getUserVO(_rq);
		////////Retrieving the patient vo of the selected patient////////
		
		PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
		{
			dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			for (int i = 0; i < dailyPatientVOs.length; i++)
			{
				if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
				{
					patientDetailVO = dailyPatientVOs[i];
					break;
				}
			}
		}
		/*DailyPatientVO[] arrayDailyPatVO=(DailyPatientVO[]) WebUTIL.getSession(_rq).getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
		DailyPatientVO selectedPatientVO=null;
		for(int i=0;i<arrayDailyPatVO.length;i++)
		{
			if(_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
			{
				selectedPatientVO=arrayDailyPatVO[i];
			}
		}*/
		WebUTIL.getSession(_rq).setAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO, patientDetailVO);		
		_fb.setAptReqNo("");		
		_fb.setActCode(OpdConfig.ACTIVITY_CODE_OPD_CONSULTATION);
		_fb.setActName(OpdConfig.ACTIVITY_NAME_OPD_CONSULTATION);				
		String unitCode=(String)session.getAttribute(OpdConfig.OPD_DESK_UNIT_CODE);
		List lstunits=(ArrayList) session.getAttribute(OpdConfig.OPD_DESK_UNIT_LIST);		
		Iterator itr=lstunits.iterator();
		while(itr.hasNext())
		{
			Entry obj=(Entry)itr.next() ;
			String unitCodeInList=obj.getValue();
			String unitCode1=unitCodeInList.substring(0,(unitCodeInList.indexOf('^')));
			if(unitCode1.equals(unitCode))
			{
				_fb.setDeptUnitName(obj.getLabel());
			}
		}	
		WebUTIL.getSession(_rq).setAttribute(OpdConfig.DEPARTMENT_UNITNAME_APPOINTMENT,_fb.getDeptUnitName());
		_fb.setAptReqNo(null);
		_fb.setMsg(null);
		System.out.println(unitCode.substring(0,3));
		_fb.setPara1(unitCode.substring(0,3));
		_fb.setPara2(unitCode);
	
			
		}catch(HisRecordNotFoundException e)		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			objStatus.add(Status.NEW);
			WebUTIL.setStatus(_rq,objStatus);		
		}		
	}	
	
	public static void getSlotDtl(HttpServletRequest _req, OpdNextVisitAppointmentFB _fb,int levelFromDb)
	{
		
		HashMap  mp=new HashMap();		
		Apt_slotDtlVO _slotDtlVO= new Apt_slotDtlVO();				
		WebUTIL.populate(_slotDtlVO,_fb);		
		UserVO userVO= getUserVO(_req);
		_slotDtlVO.setSlotDate(_fb.getAptDate());		
		mp=(HashMap)OpdNextVisitAppointmentDATA.getSlotDtl(_slotDtlVO ,userVO);
		List lstslot= new ArrayList();
		lstslot=(ArrayList) mp.get(OpdConfig.APPOINTMENT_ALL_SLOTDTL);
		if(lstslot.size()==0)
			_fb.setMsg("Slots not available");
		
		//System.out.println("mp slot----->" + mp);
		_req.setAttribute(OpdConfig.APPOINTMENT_DATE,_fb.getAptDate());
		WebUTIL.setMapInSession(mp,_req);			
	}
	public static void 	SaveData(HttpServletRequest _req, OpdNextVisitAppointmentFB _fb)
	{	
		
		HttpSession session= WebUTIL.getSession(_req);
		String tempTime=_fb.getSelSlotTime();		
		//PatientVO patientVO=(PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);
		DailyPatientVO selectedPatientVO=(DailyPatientVO)session.getAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);
		
		UserVO userVO =getUserVO(_req);
		Apt_slotDtlVO _slotDtlVO= new Apt_slotDtlVO();
		Apt_appointmentDtlVO _appointmentDtlVO= new Apt_appointmentDtlVO();
		HelperMethods.populate(_slotDtlVO,_fb);
		HelperMethods.populate(_appointmentDtlVO,selectedPatientVO);
		
		_appointmentDtlVO.setSlotDate(_fb.getAptDate());
		_slotDtlVO.setSlotDate(_fb.getAptDate());
		//_appointmentDtlVO=OpdNextVisitAppointmentDATA.SaveData(_appointmentDtlVO, _slotDtlVO, patientVO, userVO);		

		HelperMethods.populate(_fb,_appointmentDtlVO);

		HelperMethods.populate(_fb,_appointmentDtlVO);	
			
		OpdNextVisitAppointmentDATA.saveOpdPatientEpisode(selectedPatientVO.getPatCrNo(),selectedPatientVO.getSerialNo(),selectedPatientVO.getEpisodeVisitNo(),selectedPatientVO.getEpisodeCode(),RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED,RegistrationConfig.EPISODE_ISOPEN_TRUE,_fb.getAptDate(),userVO);
					
		
		_fb.setSelSlotTime(tempTime);
		if (_fb.getAptReqNo() !=null)
		_fb.setMsg("Appointment successfully given!");
	}
	public static void getNextSlotDate(HttpServletRequest _req,OpdNextVisitAppointmentFB _fb)
	{			
		Apt_slotDtlVO _slotDtlVO= new Apt_slotDtlVO();
		WebUTIL.populate(_slotDtlVO,_fb);
		UserVO uservo=getUserVO(_req);
		_slotDtlVO.setSlotDate(_fb.getAptDate());
		_slotDtlVO=OpdNextVisitAppointmentDATA.getNextSlotDate(_slotDtlVO,uservo);		
		WebUTIL.populate(_fb,_slotDtlVO);
		_fb.setAptDate(_slotDtlVO.getSlotDate());
		_req.setAttribute(OpdConfig.APPOINTMENT_DATE,_fb.getAptDate());		
	}
public static void showSlotRows(OpdNextVisitAppointmentFB fb,HttpServletRequest _rq,HttpServletResponse _resp){
		
		String strResult="";		
		List lstSlot=new ArrayList();		
		int i;
		
		lstSlot=(List) WebUTIL.getSession(_rq).getAttribute(OpdConfig.APPOINTMENT_ALL_SLOTDTL);
		Apt_slotDtlVO _slotDtlVO=new Apt_slotDtlVO(); 
		if(lstSlot!=null && lstSlot.size()>0)
		{
			strResult +="<table valign=\"top\" width=\"100%\">" +
							"<tr>" +
							"<td class='tdfonthead'></td>" +
							"<td class='tdfonthead'>" +
							"<div align=\"center\"><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">Slot No.</font></div>" +
							"</td>" +
							"<td class='tdfonthead'>" +
							"<div align=\"center\"><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">Slot Time</font></div>" +
							"</td>" +
							"<td class='tdfonthead'>" +
							"<div align=\"center\"><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">No. of Appointment Left</font></div>" +
							"</td>" +
							"<td class='tdfonthead'>" +
							"<div align=\"center\"><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">No. of Appointment Given</font></div>" +
							"</td>" +
							"</tr>";		
			
			for(i=0;i<lstSlot.size();i++)
			{
				_slotDtlVO=(Apt_slotDtlVO)lstSlot.get(i);
				
				
				strResult +="<tr>" +
								"<td class='tdfont'>" +
									"<div align=\"center\">" +
									//
//										"<input type=\"radio\" style='cursor:pointer' name=\"rdoSlot\" onclick='getSlotInfo(this,\""+ _slotDtlVO.getSlotCode()+"\",\" "+ _slotDtlVO.getSlotStartTime()+"\",\"" +_slotDtlVO.getSlotEndTime() +"\",\""+_slotDtlVO.getAptActCode()+"\")'>" +
									"</div>" +
								"</td>" +
								"<td class='tdfont'>" +
									"<div align=\"center\"><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" +
									  _slotDtlVO.getSlotCode()+ "</font></div>" +
						  		"</td>" +
						  		"<td class='tdfont'>" +
						  			"<div align=\"center\"><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" +
						  				_slotDtlVO.getSlotStartTime() +	"-" +_slotDtlVO.getSlotEndTime() + "</font></div>" +
						  		"</td>" +
						  		"<td class='tdfont'>" +
							  		"<div align=\"center\"><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" +
							  			_slotDtlVO.getAptPersonLeft()+"</font></div>" +
							  	"</td>" +
							  	"<td class='tdfont'>" +
							  	"<div align=\"center\"><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" +
							  	_slotDtlVO.getTotAptPersons()+"</font></div>" +
							  	"</td>" +
							 "</tr>";				
			}			
			
			strResult +=	"<tr>" +						
							"<td colspan='5'>"+
								"<table valign=\"top\" width=\"100%\">" +
									"<tr>" +
									"<td class='tdfonthead' width='25%' height='10%' >" +
									"<div align=\"right\"><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">Appointment Slot No.</div>" +
									"</td>" +
									"<td class='tdfonthead' width='25%' height='10%'><div align=\"left\"><input type='text' name='slotCode'  style=\"TEXTBOX1\" readonly=\"true\"></div></td>" +
									"<td class='tdfonthead' width='25%' height='10%'><div align=\"right\"><font color=\"#000000\" size=\"2\"	 face=\"Verdana, Arial, Helvetica, sans-serif\">" +
									"Slot Time</div>" +
									" </td>" +
									"<td class='tdfonthead' width='25%' height='10%'><div align=\"left\"><input type='text' name='selSlotTime'  style=\"TEXTBOX1\" readonly=\"true\"></div></td>" +
									"</tr>"+
								"</table"+	
							"</tr>" +						
							"</td>"+
							"</table>" ;
		}
		else
		{
			strResult += "<table valign=\"top\" width=\"100%\">" +
							"<tr>" +					
								"<td class='tdfont'>" +
									"<div align=\"center\"><font color=\"red\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">Appointment Slots Not Available!</font></div>" +
								"</td>" +				
							"</tr>" +
							"</table>";
		}		
		System.out.println("strResult" + strResult);		
		try{
			PrintWriter writer=_resp.getWriter();
			writer.write(strResult);
			}catch(IOException e)
			{e.printStackTrace();}		
	}
	


}
