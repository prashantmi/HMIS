
package dutyroster.masters.controller.utl;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisEpisodeOpenInEmergencyException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.RosterAreaCapacityMstVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.controller.data.RosterAreaCapacityMstDATA; 
import dutyroster.masters.controller.fb.RosterAreaCapacityMstFB; 
import dutyroster.transaction.controller.fb.EmployeeDutyRosterFB;


public class RosterAreaCapacityMstUTL extends ControllerUTIL
{

	
// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_REGISTRATION_DTL and  GBLT_DESIGNATION_MST

	public static boolean getRosterIdList(HttpServletRequest _request,RosterAreaCapacityMstFB _fb)
	{
		Status objStatus = new Status();
		
		List rosterList=new ArrayList();
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
			rosterList=RosterAreaCapacityMstDATA.getRosterIdList(_userVO);
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_ROSTERS, rosterList);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
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
		return true;
	}	
	
	


	// Getting the Duty Area on the Basis of Duty Area Types
//		 from the Package PKG_DUTY_ROSTER using the Procedure Proc_get_duty_area
		
	
	public static boolean getDutyAreaAndShiftsBasedOnRosterType(HttpServletRequest _request,RosterAreaCapacityMstFB _fb)
		{
			Status objStatus = new Status();
			
			List dutyAreaList=new ArrayList();
			Map EssentialMap=new HashMap();
			
			try
			{
				UserVO _userVO = getUserVO(_request);
				setSysdate(_request);
				
				String rosterArray[]=_fb.getRosterId().replace("^", "@").split("@");
				
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, new ArrayList());
			
				
				
				EssentialMap=RosterAreaCapacityMstDATA.getDutyAreaAndShiftsBasedOnRosterType(rosterArray[0],_fb.getAreaTypeCode(),_userVO);
				
				WebUTIL.setMapInSession(EssentialMap,_request);
				
				RosterAreaCapacityMstUTL.getNewDynamicShifts(_request, _fb);
				
				_fb.setHmode("ADD");
				
				
				
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
				_fb.setHmode("ADD");
				WebUTIL.setMapInSession(EssentialMap,_request);
				
		//	WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, new ArrayList());
				
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
				
			}
			catch (HisDataAccessException e)
			{
				
				System.out.println("Inside HisDataAccessException");
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, "", e.getMessage());
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
			return true;
		}	
	
		
		// On Add Page saving Values into Database
		public static boolean saveRosterAreaCapacityInfo(RosterAreaCapacityMstFB _fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			boolean hasFlag = true;
			try
			{
				UserVO userVO = getUserVO(_request);
				RosterAreaCapacityMstVO _rosterAreaCapaMstVO=new RosterAreaCapacityMstVO();

				HelperMethods.populate(_rosterAreaCapaMstVO, _fb); // for dropping all values of formBean into vo//

				
				
				//fetching the Roster ID Information from the Concatinated Values 
				String concateValues=_fb.getRosterId().replace("^","@");
				String splitValues[]=concateValues.split("@");
				
				_rosterAreaCapaMstVO.setRosterId(splitValues[0]);
				
				
				
			RosterAreaCapacityMstDATA.saveRosterAreaCapacityInfo(_rosterAreaCapaMstVO, userVO);
				
				objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
				_fb.setHmode("ADD");
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
				hasFlag = false;
			}
			catch (HisDataAccessException e)
			{
				System.out.println("Inside HisDataAccessException");
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, "", "Data Access Error");
				hasFlag = false;
			}
			catch (HisApplicationExecutionException e)
			{
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
				hasFlag = false;
			}
			catch (HisException e)
			{
				System.out.println("Inside HisException");
				objStatus.add(Status.ERROR, "", "Error");
				hasFlag = false;
			}
			finally
			{
				WebUTIL.setStatus(_request, objStatus);
				_request.setAttribute(Config.STATUS_OBJECT, objStatus);
				System.out.println("   -----> objStatus in finally  : " + objStatus);
				System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
			}
			return hasFlag;
		}

			
		// on modify Page for Showing Data of Selected Record
		public static boolean fetchRosterAreaCapacityInfo(RosterAreaCapacityMstFB _fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			RosterAreaCapacityMstVO _rosterAreaCapaMstVO=new RosterAreaCapacityMstVO();
			RosterAreaCapacityMstVO  rostAreaCapaMstVO=new RosterAreaCapacityMstVO();
			String shiftTypeCode="";
			String _hCode="";
			String _sTypeCode="";
			Map essentialMap=new HashMap(); 		
			try
			{
				UserVO userVO = getUserVO(_request);
				setSysdate(_request);

		//	HelperMethods.populate(_rosterAreaCapaMstVO, _fb);	
				
				// Fetching Selected Record Primary Key
				String chk = _fb.getChk().replace("^", "@");
				String[] concatid = chk.split("@");

				String rosterCode = concatid[0];
				String areaCode = concatid[1];
				String slNo = concatid[2];
				String Htcode = concatid[3];
				
				// putting the selected Record Primary Key into Vo
				
				_rosterAreaCapaMstVO.setRosterId(rosterCode);
				_rosterAreaCapaMstVO.setAreaCode(areaCode);
				_rosterAreaCapaMstVO.setSerialNo(slNo);
				
				
				
				essentialMap=RosterAreaCapacityMstDATA.fetchRosterAreaCapacityInfo(_rosterAreaCapaMstVO, userVO);
				WebUTIL.setMapInSession(essentialMap,_request);
				
				rostAreaCapaMstVO=(RosterAreaCapacityMstVO)essentialMap.get(DutyRosterConfig.DUTY_ROSTER_MASTER_VO_OF_ROSTER_AREA_CAPACITY_MST);
				
			
							
				
			
				RosterAreaCapacityMstUTL.getModifyDynamicShifts(rostAreaCapaMstVO,_request, _fb);
				
				HelperMethods.populate(_fb, rostAreaCapaMstVO);
				
				_fb.setChk(chk);
				 
				
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
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
			return true;
		}

		// for Updating The old Record
		public static boolean updateRosterAreaCapacityInfo(RosterAreaCapacityMstFB _fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			boolean flag=true;
			try
			{
				UserVO userVO = getUserVO(_request);
				setSysdate(_request);
				RosterAreaCapacityMstVO _rosterAreaCapaMstVO=new RosterAreaCapacityMstVO();
				

				// Fetching Selected Record Primary Key
				String chk = _fb.getChk().replace("^", "@");
				String[] concatid = chk.split("@");

				String rosterCode = concatid[0];
				String areaCode = concatid[1];
				String slNo = concatid[2];
				String Htcode = concatid[3];
				
			
				HelperMethods.populate(_rosterAreaCapaMstVO, _fb);
				_rosterAreaCapaMstVO.setSerialNo(slNo);
			
				
				
				RosterAreaCapacityMstDATA.updateRosterAreaCapacityInfo(areaCode,_rosterAreaCapaMstVO, userVO);
				
				objStatus.add(Status.TRANSINPROCESS);
			}
			catch(HisDuplicateRecordException e){	   		   	
				objStatus.add(Status.TRANSINPROCESS,"Roster Category Already Exists","");	
		  		 e.printStackTrace(); 
		  		 flag=false;
		  		 _fb.setHmode("MODIFY");
		  		
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
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
				_request.setAttribute(Config.STATUS_OBJECT, objStatus);
				System.out.println("   -----> objStatus in finally  : " + objStatus);
				System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
			}
			return flag;
		}

		public static void  getNewDynamicShifts(HttpServletRequest _request,RosterAreaCapacityMstFB _fb)
{
			
			List shiftList=new ArrayList();
			
			shiftList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
			
			String textBoxNameConcate="";
			String shiftNameAlertConcate="";
			
			
				
		//Creating a string Buffer for Creating a New Calendar on the basis of employees mapped
		///for the corresponding area type,areaCode and rosterType	
			StringBuffer strShift=new StringBuffer(); 
			
					//Creating the Dynamic Shifts based on the the Shifts mapped 
		//for the corresponding  rosterType


		//iterating through the total No of Shifts mapped and getting the Shifts ID and Shifts name
		for(int i=0 ; i <shiftList.size();i++)
	    	{
			
			Entry objEntryEmp = new Entry();
			
			String shiftId="";
			String displayShiftName="";
			
			
			objEntryEmp=(Entry)shiftList.get(i);
			shiftId=objEntryEmp.getValue();
			displayShiftName	=objEntryEmp.getLabel()+" Capacity ";
			
			
			String textBoxName=RosterAreaCapacityMstUTL.getTextBoxName(Integer.parseInt(shiftId));
			
			strShift.append("<tr>");
			
			textBoxNameConcate+=textBoxName+"@";
			shiftNameAlertConcate+=displayShiftName+"@";
				
			
			
			strShift.append("<td align=\"right\" width=\"50%\"  class=\"tdfonthead\"><font color=\"RED\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">*</font><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"+displayShiftName+"</font></td>");
			
			
			strShift.append("<td align=\"left\" width=\"50%\"  class=\"tdfont\"><input type=\"text\" name=\""+textBoxName+"\" maxlength=\"2\"  size=\"3\"  tabindex=\"1\"  value=\"\"  onkeypress=\"return validatePositiveIntegerOnly(this,event)\"></td>");
			
			strShift.append("</tr>");
			
		   }	

		strShift.append("<tr>");	
		strShift.append("<input type=\"hidden\" id=\"textBoxNameConcate\"  value=\""+textBoxNameConcate+"\" >");
		strShift.append("<input type=\"hidden\" id=\"shiftNameAlertConcate\"   value=\""+shiftNameAlertConcate+"\" >");
		strShift.append("</tr>");	
			
			
			_fb.setDynamicShifts(strShift.toString());
				
			
}
		
		public static void  getModifyDynamicShifts(RosterAreaCapacityMstVO _rosterAreaCapaMstVO,HttpServletRequest _request,RosterAreaCapacityMstFB _fb)
		{
					
					List shiftList=new ArrayList();
					
					shiftList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
					
					String textBoxNameConcate="";
					String shiftNameAlertConcate="";
					
					
						
				//Creating a string Buffer for Creating a New Calendar on the basis of employees mapped
				///for the corresponding area type,areaCode and rosterType	
					StringBuffer strShift=new StringBuffer(); 
					
							//Creating the Dynamic Shifts based on the the Shifts mapped 
				//for the corresponding  rosterType


				//iterating through the total No of Shifts mapped and getting the Shifts ID and Shifts name
				for(int i=0 ; i <shiftList.size();i++)
			    	{
					
					Entry objEntryEmp = new Entry();
					
					String shiftId="";
					String displayShiftName="";
					
					
					objEntryEmp=(Entry)shiftList.get(i);
					shiftId=objEntryEmp.getValue();
					displayShiftName	=objEntryEmp.getLabel()+" Capacity ";
					
					
					
					String textBoxName=RosterAreaCapacityMstUTL.getTextBoxName(Integer.parseInt(shiftId));
					
					String textBoxValue=RosterAreaCapacityMstUTL.getTextBoxValue(_rosterAreaCapaMstVO,Integer.parseInt(shiftId));
					
					strShift.append("<tr>");
					
					textBoxNameConcate+=textBoxName+"@";
					shiftNameAlertConcate+=displayShiftName+"@";
						
					strShift.append("<td align=\"right\" width=\"50%\"  class=\"tdfonthead\"><font color=\"RED\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">*</font><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"+displayShiftName+"</font></td>");
					
			if(_fb.getHmode().equals("MODIFY"))
					strShift.append("<td align=\"left\" width=\"50%\"  class=\"tdfont\"><input type=\"text\" name=\""+textBoxName+"\" maxlength=\"2\"  size=\"3\"  tabindex=\"1\"  value=\""+textBoxValue+"\"  onkeypress=\"return validatePositiveIntegerOnly(this,event)\"></td>");
				else
			if(_fb.getHmode().equals("VIEW"))
				strShift.append("<td align=\"left\" width=\"50%\"  class=\"tdfont\"><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"+textBoxValue+"</font></td>");
		
			
					strShift.append("</tr>");
					
				   }	

				strShift.append("<tr>");	
				strShift.append("<input type=\"hidden\" id=\"textBoxNameConcate\"  value=\""+textBoxNameConcate+"\" >");
				strShift.append("<input type=\"hidden\" id=\"shiftNameAlertConcate\"   value=\""+shiftNameAlertConcate+"\" >");
				strShift.append("</tr>");	
					
					
					_fb.setDynamicShifts(strShift.toString());
						
					
		}
		
		
		public static String  getTextBoxName(int shiftId)
	{
			String textBoxName="";
			
	switch(shiftId)
			{
	case DutyRosterConfig.MORNING_SHIFT_TYPE_ID : 	
						{	
							textBoxName=DutyRosterConfig.MORNING_SHIFT_TYPE_TEXT_BOX_NAME;
							break;
						}
	
	case DutyRosterConfig.EVENING_SHIFT_TYPE_ID : 	
						{
							textBoxName=DutyRosterConfig.EVENING_SHIFT_TYPE_TEXT_BOX_NAME;
							break;
						}
						
	case DutyRosterConfig.NIGHT_SHIFT_TYPE_ID :		
						{
							textBoxName=DutyRosterConfig.NIGHT_SHIFT_TYPE_TEXT_BOX_NAME;
							break;
						}
	
	case DutyRosterConfig.DAY_SHIFT_TYPE_ID : 	
						{
							textBoxName=DutyRosterConfig.DAY_SHIFT_TYPE_TEXT_BOX_NAME;
							break;
						}
	
	case DutyRosterConfig.EARLY_MORNING_SHIFT_TYPE_ID : 
						{
							textBoxName=DutyRosterConfig.EARLY_MORNING_SHIFT_TYPE_TEXT_BOX_NAME;
							break;
						}
			}	
	
		return textBoxName;
	
	}
	
		public static String  getTextBoxValue(RosterAreaCapacityMstVO _rosterAreaCapaMstVO,int shiftId)
		{
				String textBoxvalue="";
				
		switch(shiftId)
				{
		case DutyRosterConfig.MORNING_SHIFT_TYPE_ID : 	
							{	
								textBoxvalue=_rosterAreaCapaMstVO.getMorningCapacity();
								break;
							}
		
		case DutyRosterConfig.EVENING_SHIFT_TYPE_ID : 	
							{
								textBoxvalue=_rosterAreaCapaMstVO.getEveningCapacity();
								break;
							}
							
		case DutyRosterConfig.NIGHT_SHIFT_TYPE_ID :		
							{
								textBoxvalue=_rosterAreaCapaMstVO.getNightCapacity();
								break;
							}
		
		case DutyRosterConfig.DAY_SHIFT_TYPE_ID : 	
							{
								textBoxvalue=_rosterAreaCapaMstVO.getDayCapacity();
								break;
							}
		
		case DutyRosterConfig.EARLY_MORNING_SHIFT_TYPE_ID : 
							{
								textBoxvalue=_rosterAreaCapaMstVO.getEarlyMorningCapacity();
								break;
							}
				}	
		
			return textBoxvalue;
		
		}
}
