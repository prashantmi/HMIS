package appointment.transactions.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisDeadPatientException;
import registration.config.Exceptions.HisNotAnIPDcaseException;
import registration.config.Exceptions.HisNotAnOPDcaseException;
import registration.config.Exceptions.HisRenewalRequiredException;
import registration.transactions.controller.data.PatientVisitDATA;
import vo.appointment.AppointmentParameterVO;
import vo.appointment.NewAppointmentVO;
import vo.appointment.RescheduleCancelAppointmentVO;
import vo.registration.PatientVO;
import appointment.config.AppointmentConfig;
import appointment.transactions.controller.actionsupport.NewAppointmentSUP;
import appointment.transactions.controller.actionsupport.RescheduleCancelAppointmentSUP;
import appointment.transactions.controller.data.AppointmentTagDATA;
import appointment.transactions.controller.data.NewAppointmentDATA;
import appointment.transactions.controller.data.RescheduleCancelAppointmentDATA;
import appointment.transactions.controller.data.ShiftAndSlotCancellationDATA;

public class ShiftAndSlotCancellationUTIL extends ControllerUTIL{

	
	public static void getActualParaIdWiseDetail(RescheduleCancelAppointmentSUP objRescheduleCancelAppointmentSUP_p,HttpServletRequest objRequest, HttpServletResponse objResponse,Map mapSesion) {
		
		String jsonString="";
		int maxdisplayOrder=0;
		try
		{
			UserVO userVO=getUserVO(objRequest);
			jsonString =RescheduleCancelAppointmentDATA.getAppointmentTypeList(objRescheduleCancelAppointmentSUP_p.getActualParameterReferenceId(),userVO);
			jsonString=  "{\"optionHTML\":\"" + jsonString + "\"}";
			System.out.println("json----" + jsonString);
			WebUTIL.writeResponse(objResponse, jsonString,null);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public static String getAvailableShift(HttpServletRequest request,HttpServletResponse response,String aptId, String paraId,String aptDate) 
	{
		
		String strResultTag="";
		String allactualParaId="";
		WebRowSet ws=null;
			
		try
		{	
			UserVO userVO= ControllerUTIL.getUserVO(request);
			
			String[] actualParaId= new String[7];
			String actualParaRefIdSlotDuration=null;
			String actualParaRefId=null;
			String slotDuration=null;		
			
			if(aptId!=null)
			{							
				actualParaId=paraId.replace("^", "#").split("#");
				allactualParaId=paraId;
			    
				AppointmentParameterVO appParaObj= new AppointmentParameterVO();
				appParaObj.setAppointmentForId(aptId);				
				appParaObj.setArrActualParaId(actualParaId);

				actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptId, allactualParaId);//actualParaRefId^slotDuartion
				actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
				slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
				appParaObj.setSlotDuration(slotDuration);
				appParaObj.setAppointmentForDate(aptDate);
				if(actualParaRefId!=null)
				{
					appParaObj.setActualParameterReferenceId(actualParaRefId);					
					ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);			
					WebUTIL.setAttributeInSession(request, AppointmentConfig.SHIFT_SLOT_WEBROWSET, ws);
				}					
				else
				{
					return "Appointment Not Configured";										
				}										
			}						
			
			strResultTag=makeShiftRowsHTML(ws, allactualParaId, aptDate, aptId,actualParaRefId);
			WebUTIL.writeResponse(response, strResultTag,null);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return strResultTag;
	}
	
	
	
	public static String getSlots(HttpServletRequest request,HttpServletResponse response,String aptId, String paraId,String aptDate,String shiftId) 
	{
		
		String strResultTag="";
		String allactualParaId="";
		WebRowSet ws=null;
			
		try
		{	
			UserVO userVO= ControllerUTIL.getUserVO(request);
			
			String[] actualParaId= new String[7];
			String actualParaRefIdSlotDuration=null;
			String actualParaRefId=null;
			String slotDuration=null;		
			
			if(aptId!=null && null==request.getAttribute(AppointmentConfig.SHIFT_SLOT_WEBROWSET))
			{							
				actualParaId=paraId.replace("^", "#").split("#");
				allactualParaId=paraId;
			    
				AppointmentParameterVO appParaObj= new AppointmentParameterVO();
				appParaObj.setAppointmentForId(aptId);				
				appParaObj.setArrActualParaId(actualParaId);

				actualParaRefIdSlotDuration=AppointmentTagDATA.getActualParaRefernceId(userVO,aptId, allactualParaId);//actualParaRefId^slotDuartion
				actualParaRefId=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[0]:"";
				slotDuration=actualParaRefIdSlotDuration.replace("^", "#").split("#").length>0?actualParaRefIdSlotDuration.replace("^", "#").split("#")[1]:"";
				appParaObj.setSlotDuration(slotDuration);
				appParaObj.setAppointmentForDate(aptDate);
				if(actualParaRefId!=null)
				{
					appParaObj.setActualParameterReferenceId(actualParaRefId);					
					ws=AppointmentTagDATA.makeSlotsData(userVO,appParaObj);			
					//WebUTIL.setAttributeInSession(request, AppointmentConfig.SHIFT_SLOT_WEBROWSET, ws);
				}					
				else
				{
					return "Appointment Not Configured";										
				}										
			}						
			
			strResultTag=makeSlotsRowsHTML(ws, shiftId,aptDate,actualParaRefId,paraId,aptId);
			WebUTIL.writeResponse(response, strResultTag,null);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return strResultTag;
	}
	
	public static String makeShiftRowsHTML(WebRowSet ws,String paraId,String aptDate,String aptId,String actualParaRefId)
	{
		String strResultTag="";
		boolean breakLoop=false;
		Set<String> shiftSet=new HashSet<>();

		try{
			String oldShift="";
			strResultTag+="<div class='div-table-listing'>";
			strResultTag+="<div class='div-table-row listHeader'>";
			strResultTag+="<div class='div-table-col title width100 '>Shift Details</div>";
			/*strResultTag+="<div class='div-table-col' style='width: 10%' align='center'></div>";
			strResultTag+="<div class='div-table-col' style='width: 70%' align='center'>Shift</div>";
			strResultTag+="<div class='div-table-col' style='width: 10%' align='center'>Cancel</div>";
			strResultTag+="<div class='div-table-col' style='width: 10%' align='center'>Reschedule</div>";*/
			strResultTag+="</div>";
		
			if(ws!=null && ws.size()>0)
			{
				while(ws.next() && !breakLoop)
				{
					if(!ws.getString(2).equals(oldShift)&&!shiftSet.contains(ws.getString(2))){
					String click="getShiftRelatedSlotDetails('"+paraId+"','"+aptDate+"','"+aptId+"','"+ws.getString(2)+"')";	
					String shiftCancel="cancelShift('"+paraId+"','"+aptDate+"','"+aptId+"','"+ws.getString(2)+"','"+actualParaRefId+"')";	
					String shiftReschedule="rescheduleShift('"+paraId+"','"+aptDate+"','"+aptId+"','"+ws.getString(2)+"','"+actualParaRefId+"')";	

					strResultTag+="<div class='div-table-row listData'>";	
					strResultTag+="<div class='div-table-col' style='width: 10%' align='center'>";	
					strResultTag+="<div id='shiftLabel' align='left'><img id='imgPlus_"+ws.getString(1)+"_"+ws.getString(2)+"' src='/HIS/hisglobal/images/plus.gif' onclick="+click+"></div>";
					strResultTag+="</div>";
					strResultTag+="<div class='div-table-col' style='width: 70%' align='center'>";	
					strResultTag+="<div id='shiftLabel' align='left'><font size='2px'><a onclick="+click+"> "+ws.getString(3)+"&nbsp;("+ws.getString(4)+"-"+ws.getString(5)+")"+"</a></font></div>";
					strResultTag+="</div>";
					strResultTag+="<div class='div-table-col' style='width: 10%' align='center'>";	
					strResultTag+="<div id='shiftCancelLabel' align='left'><img src='/HIS/hisglobal/images/avai/stop.png' title='Cancel' id='imgCancel_"+ws.getString(1)+"_"+ws.getString(2)+"' onclick="+shiftCancel+"></div>";
					strResultTag+="</div>";
					strResultTag+="<div class='div-table-col' style='width: 10%' align='center'>";	
					//strResultTag+="<div id='shiftCancelLabel' align='left'><img src='/HIS/hisglobal/images/avai/Refresh.png' id='imgRefresh_"+ws.getString(1)+"_"+ws.getString(2)+"' onclick="+shiftReschedule+"></div>";
					strResultTag+="</div>";
					strResultTag+="</div>";
					
					strResultTag+="<div id='slotData_"+ws.getString(1)+"_"+ws.getString(2)+"' class='div-table-row listData' style='display:none'>";	
					strResultTag+="</div>";
					
					/*strResultTag+="<div id='slotRowsButton_"+aptDate+"_"+ws.getString(2)+"' class='div-table-row listData' align='center' style='display:none'>";
					strResultTag+="<div class='div-table-button'><div class='div-table-row'>";
					strResultTag+="<a href='#' class='button' id='slotCancelId' onclick='slotCancel();'><span class='cancel'><s:text name='cancel'/></span></a>";
					strResultTag+="<a href='#' class='button' id='slotRenewalId' onclick='slotRenewal();'><span class='Save'><s:text name='cancel'/></span></a>";
					strResultTag+="</div></div>";
					strResultTag+="</div>";*/

					shiftSet.add(ws.getString(2));
					}
					oldShift=ws.getString(2);
	
				}
			}
			else
			{
				strResultTag+="<div class='div-table-row listData'>";	
				strResultTag+="<div class='div-table-col' style='width: 100%' align='center'>";	
				strResultTag+="<div id='noShiftLabel' align='center'><font size='2px'><a> No Shift Available </a></font></div>";
				strResultTag+="</div>";
				strResultTag+="</div>";
			}
			strResultTag+="</div>";		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return strResultTag;
	}
	
	
	public static String makeSlotsRowsHTML(WebRowSet ws,String shiftId,String aptDate,String actualParaRefId,String paraId,String aptId)
	{
		String strResultTag="";
		boolean breakLoop=false;
		Set<String> shiftSet=new HashSet<>();
		int i=1,j=1;
		boolean hListData=false;


		try{
			strResultTag+="<div id='slotRows_"+aptDate+"_"+shiftId+"' class='div-table-listing'>";
			strResultTag+="<div id='slotRowsHeader_"+aptDate+"_"+shiftId+"' class='div-table-row listHead'>";
			//strResultTag+="<div class='div-table-col title width100 '>Shift Details</div>";
			/*strResultTag+="<div class='div-table-col' style='width: 10%' align='center'></div>";
			strResultTag+="<div class='div-table-col' style='width: 70%' align='center'>Shift</div>";
			strResultTag+="<div class='div-table-col' style='width: 10%' align='center'>Cancel</div>";
			strResultTag+="<div class='div-table-col' style='width: 10%' align='center'>Reschedule</div>";*/
			//strResultTag+="<div class='div-table-col' style='width: 50%' align='center'></div>";
			String checkBoxClick="clickOnCheckbox(this)";
			//String checkBoxId=""
			strResultTag+="<div class='div-table-col' style='width: 80%' align='center'><font size='2px'><b>Slot Details</b></font></div>";
			strResultTag+="<div class='div-table-col' style='width: 10%' align='right'><input type='checkbox' onChange="+checkBoxClick+" id="+aptDate+"_"+shiftId+"></div>";
			strResultTag+="<div class='div-table-col' style='width: 10%' align='left'><font size='2px'>Select All</font></div>";
			strResultTag+="</div>";
		
			if(ws!=null && ws.size()>0)
			{
				while(ws.next() && !breakLoop)
				{
					if(ws.getString(2).equals(shiftId)){
						if(i==1 || hListData){
							strResultTag+="<div id='slotRowsDetail_"+aptDate+"_"+shiftId+"_"+j+"' class='div-table-row listData'>";
							hListData=false;j++;
						}
						
						String title=ws.getString(8)+"-"+ws.getString(9)+" "+ws.getString(11);
						String slotId="";
						String slotUniqueId=ws.getString(2)+""+i;
						String slotType="1";						//Slot Type:1 Normal,2 Urgent,3 VIP,4On Arrival,5 Overbooked
						String cssClass=ws.getString(11);
						if(ws.getString(10).equals("5"))
							slotType="5";
						String onclick="selectSlot('"+ws.getString(1)+"','"+ws.getString(8)+"','"+ws.getString(9)+"','"+ws.getString(2)+"','"+ws.getString(4)+"','"+ws.getString(5)+"','"+slotType+"','"+slotUniqueId+"','"+cssClass+"')";
						slotId=ws.getString(1)+"X"+ws.getString(8)+"X"+ws.getString(9)+"X"+ws.getString(2)+"X"+ws.getString(4)+"X"+ws.getString(5)+"X"+slotType+"X"+cssClass;
						strResultTag+="<div class='div-table-col "+cssClass+"' id='"+slotId+"' name="+title+" onclick="+onclick+" style='width: 10%' align='center'>";	
						strResultTag+="<a>"+ws.getString(8)+"</a>";
						strResultTag+="</div>";
						
						if(i!=1 && i%7==0){
							strResultTag+="</div>";
							hListData=true;
						}		
						i++;
					}	
				}				
			}
			
			else
			{
				strResultTag+="<div class='div-table-row listData'>";	
				strResultTag+="<div class='div-table-col' style='width: 100%' align='center'>";	
				strResultTag+="<div id='noShiftLabel' align='center'><font size='2px'><a> No Slots Available </a></font></div>";
				strResultTag+="</div>";
				strResultTag+="</div>";
			}			
			strResultTag+="<input type='hidden' value='"+paraId+"' id=paraId_"+aptDate+"_"+shiftId+">";
			strResultTag+="<input type='hidden' value='"+actualParaRefId+"' id=actualParaRefId_"+aptDate+"_"+shiftId+">";
			strResultTag+="<input type='hidden' value='"+aptId+"' id=aptId_"+aptDate+"_"+shiftId+">";
			strResultTag+="<input type='hidden' value='"+shiftId+"' id=shiftId_"+aptDate+"_"+shiftId+">";
			strResultTag+="<input type='hidden' value='"+aptDate+"' id=aptDate_"+aptDate+"_"+shiftId+">";

			strResultTag+="</div>";		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return strResultTag;
	}
	
	public static void cancelShift(RescheduleCancelAppointmentSUP objRescheduleCancelAppointmentSUP_p,HttpServletRequest objRequest,HttpServletResponse objResponse,String aptId, String paraId,String aptDate,String shiftId,String actualParaRefId){
		Status status=new Status();
		String strMsg="";
		try						
		{
			System.out.println("CancelShift()::UTIL");
			UserVO userVO=getUserVO(objRequest);
			RescheduleCancelAppointmentVO objCancelAppointmentVO= new RescheduleCancelAppointmentVO(); 
			objCancelAppointmentVO.setAllActualParameterId(paraId);
			objCancelAppointmentVO.setShiftId(shiftId);
			objCancelAppointmentVO.setAppointmentForId(aptId);
			objCancelAppointmentVO.setAppointmentDate(aptDate);
			objCancelAppointmentVO.setParaRefId(actualParaRefId);
			objCancelAppointmentVO.setAppointmentStatus(AppointmentConfig.APPOINTMENT_STATUS_CANCELLED);
			objCancelAppointmentVO.setRemarks(objRescheduleCancelAppointmentSUP_p.getRemarks());

			objCancelAppointmentVO= ShiftAndSlotCancellationDATA.cancelShift(objCancelAppointmentVO,userVO);
			status.add(Status.DONE,"Shift Cancelled Successfully" ,"");
			WebUTIL.writeResponse(objResponse, "Shift Cancelled Successfully",null);

		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.ERROR,"Unable to Cancel the Shift" ,"");
			WebUTIL.writeResponse(objResponse, "Shift Cancelled Successfully",null);

		}	
		finally{
			WebUTIL.setStatus(objRequest, status);
		}
	}
	
	
	public static void cancelSlots(RescheduleCancelAppointmentSUP objRescheduleCancelAppointmentSUP_p,HttpServletRequest objRequest,HttpServletResponse objResponse,String slotsData,String actualParaRefId,String paraId,String aptType){
		Status status=new Status();
		try						
		{
			System.out.println("CancelShift()::UTIL");
			UserVO userVO=getUserVO(objRequest);
			List<RescheduleCancelAppointmentVO> _lstCancelSlotVO=new ArrayList();
			
			if(slotsData!=null){
				String _tmpslotsData[]=slotsData.replace(",", "#").split("#");
				
				for (String slotEach : _tmpslotsData) {
					RescheduleCancelAppointmentVO objCancelAppointmentVO= new RescheduleCancelAppointmentVO();
					//aptdate+"_"+startTime+"_"+endTime+"_"+shiftId+"_"+shiftSt+"_"+shiftEt+"_"+slotType+"_"+cssClass;
					String[] _tmpData=slotEach.replace("_", "#").split("#");
					objCancelAppointmentVO.setAppointmentDate(_tmpData[0]);
					objCancelAppointmentVO.setSlotST(_tmpData[1]);
					objCancelAppointmentVO.setSlotET(_tmpData[2]);
					objCancelAppointmentVO.setShiftId(_tmpData[3]);	
	
					objCancelAppointmentVO.setAppointmentForId(aptType);
					objCancelAppointmentVO.setParaRefId(actualParaRefId);
					objCancelAppointmentVO.setAllActualParameterId(paraId);
					objCancelAppointmentVO.setAppointmentStatus(AppointmentConfig.APPOINTMENT_STATUS_CANCELLED);
					objCancelAppointmentVO.setRemarks(objRescheduleCancelAppointmentSUP_p.getRemarks());
					_lstCancelSlotVO.add(objCancelAppointmentVO);
				}			
				
				ShiftAndSlotCancellationDATA.cancelSlots(_lstCancelSlotVO, userVO);
				status.add(Status.DONE,"Slots Cancelled Successfully" ,"");
				WebUTIL.writeResponse(objResponse, "Slots Cancelled Successfully",null);
			}
			else{
				status.add(Status.ERROR,"Unable to Cancel the Slots" ,"");
				WebUTIL.writeResponse(objResponse, "Unable to Cancel the Slots",null);
			}

		}
		catch(Exception e){
			e.printStackTrace();
			status.add(Status.ERROR,"Unable to Cancel the Slots" ,"");
			WebUTIL.writeResponse(objResponse, "Unable to Cancel the Slots",null);
		}	
		finally{
			WebUTIL.setStatus(objRequest, status);
			
		}
	}




}
