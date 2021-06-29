package ipd.transactions;
import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
public class PatientTransferTransHLP{

	public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy/HH:mm:ss";
	private static ResourceBundle hisProp = ResourceBundle.getBundle("ipd.hisIpdProperties");
	public static String wrdChng = hisProp.getString("WARD_CHANGE");
	public static String bedChng = hisProp.getString("BED_CHANGE");
	public static String depUntChng = hisProp.getString("DEPT/UNIT_CHANGE");
	public static String serArChng = hisProp.getString("SERVICE_AREA_CHANGE");
	public static String hosChng = hisProp.getString("HOSPITAL_CHANGE");
	public static  String now() 
    {
    	HisUtil util=null;
    	String a="";
    	util=new HisUtil("transaction","PatientTransferTransHLP");
    	try{
    	 a= util.getASDate(DATE_FORMAT_NOW);
    	}
    	catch(Exception e){
    	}
    	/*Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());*/
    	return a;
    }
		
	public static String getChangeOfWard(PatientTransferTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		IpdConfigUtil ipdConfig = null;
		try
		{
			ipdConfig = new IpdConfigUtil(vo.getStrHospCode());
			
			/*setStrPvtWardCode(ipdConfig.getStrPrivateWardType());
			formBean.setStrIpdConfIcuWard(ipdConfig.getStrIcuWardType());*/

				// Row 1 Transfer Date/Time & Bed Dbl Occup
			
			
			sBuffer.append("<input type='hidden' name='transId' value='" + PatientTransferTransHLP.wrdChng + "'>");
			sBuffer.append("<input type='hidden' name='strEntryDate' value='" + now() + "'>");
			sBuffer.append("<input type='hidden' name='strMSBed' value=''>");
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-3' align='right'>");
			sBuffer.append("<label>Transfer Date/Time</label>");	
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-9' style='color:blue;'>");
			sBuffer.append(now());	
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-3' align='right'>");
			sBuffer.append("<label ><font color='red'>*</font>Department</label>");	
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<select style='cursor:pointer;cursor:hand' name = 'strDepartment' tabindex='1' class='browser-default custom-select' onChange ='fununit();' >"
					+ vo.getStrdeptproperty() + "</select>");
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-3' align='right'>");
			sBuffer.append("<label >Ward</label>");	
			sBuffer.append("</div>");
			if(!ipdConfig.getStrUnitNameReq().equals("1"))
				sBuffer.append("<input name='strUnit' type='hidden' value='0'>");
			if(!ipdConfig.getStrRoomNoReq().equals("1"))
				sBuffer.append("<input name='strRoom' type='hidden' value='0'>");
			sBuffer.append("<div class='col-sm-2' id='wardId'>");
			sBuffer.append("<select name='strWard' class='browser-default custom-select' tabindex='1' dir='' title='' onChange=''><option value='0'>Select Value</option></select>");
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			
			sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");
			sBuffer.append("<tr>");	
			/*sBuffer.append("<td width='25%' class='LABEL'>");	
			sBuffer.append("<div align='right'>");
			sBuffer.append("Transfer Date/Time");	
			sBuffer.append("</div>");	
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>");	
			sBuffer.append("<font color='blue'>");
			sBuffer.append(now());	
			sBuffer.append("</font>");	
			sBuffer.append("</td>");*/
			sBuffer.append("<td width='25%' class='LABEL' style='display:none;'>");	
			sBuffer.append("<div align='right'>");
			sBuffer.append("Bed Double Occupancy");	
			sBuffer.append("</div>");	
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='CONTROL' style='display:none;'>");
			sBuffer.append("<input type='checkbox' name='strIsBedVacant' value='1'");
				if (vo.getStrPrevDblOcc().equals("1")) 
			sBuffer.append(" checked");
			sBuffer.append(" onClick='isDoubleOccupancy(this);'>");
			sBuffer.append("</td>");	
			sBuffer.append("</tr>");
			
				// Row 2  
			sBuffer.append("<tr>");
				// Column 1 Department
	/*		sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
			sBuffer.append("<font color='red'>*</font>");	sBuffer.append("Department");
			sBuffer.append("</div>");	sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>");
			//---------------------------------
			sBuffer.append("<select style='cursor:pointer;cursor:hand' name = 'strDepartment' tabindex='1' class='comboNormal' onChange ='fununit();' >"
					+ vo.getStrdeptproperty() + "</select>");
			sBuffer.append("</td>");*/
			
				// Column 2 Unit
			if(ipdConfig.getStrUnitNameReq().equals("1"))
			{
				sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
				sBuffer.append("<font color='red'>*</font>");	sBuffer.append("Unit");
				sBuffer.append("</div>");	sBuffer.append("</td>");			
				sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("<div id='unitId'>");
				//----------------------
				sBuffer.append("<select name='strUnit' tabindex='1' class='comboNormal' dir='' title='' onChange='funward();' ><option value='0'>Select Value</option></select>");
				sBuffer.append("</div>");	sBuffer.append("</td>");
				sBuffer.append("</tr>");
				sBuffer.append("<tr>");
			}
			
			
			// sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Ward
			// Type</div></td>");
			// sBuffer.append("<td width='25%' class='CONTROL' ><div id ='wardTypeId'>");
			// sBuffer.append("<select style='cursor:pointer;cursor:hand' name='strWardType' class='comboNormal' dir=''
			// title='' onChange='funward();'>"+vo.getStrwardType()+"</select>");
			// sBuffer.append("</div></td>");
			
				// Column 3 Ward
			/*sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
			
			if(!ipdConfig.getStrUnitNameReq().equals("1"))
				sBuffer.append("<input name='strUnit' type='hidden' value='0'>");
			if(!ipdConfig.getStrRoomNoReq().equals("1"))
				sBuffer.append("<input name='strRoom' type='hidden' value='0'>");
			
			
			sBuffer.append("<font color='red'>*</font>");	sBuffer.append("Ward");
			sBuffer.append("</div>");	sBuffer.append("</td>");		
			sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("<div id='wardId'>");
			//----------------------
			sBuffer.append("<select name='strWard' class='comboNormal' tabindex='1' dir='' title='' onChange=''><option value='0'>Select Value</option></select>");
			sBuffer.append("</div>");	sBuffer.append("</td>");*/

			
			// sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Room
			// Type</div></td>");
			// sBuffer.append("<td width='25%' class='CONTROL' >");
			// sBuffer.append("<select style='cursor:pointer;cursor:hand' name='strRoomType' class='comboNormal' dir=''
			// title='' onChange='funroom();'>"+vo.getStrRoomType()+"</select>");
			// sBuffer.append("</td>");

				// Column 4 Room
			if(!ipdConfig.getStrUnitNameReq().equals("1"))
			{
				sBuffer.append("</tr>");
				if(ipdConfig.getStrRoomNoReq().equals("1")) sBuffer.append("<tr>");
			}
			if(ipdConfig.getStrRoomNoReq().equals("1"))
			{
				sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
				sBuffer.append("<font color='red'>*</font>");	sBuffer.append("Room");
				sBuffer.append("</div>");	sBuffer.append("</td>");		
				sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("<div id='roomId'>");
				//----------------------
				sBuffer.append("<select name='strRoom' class='comboNormal' dir='' title=''  onChange=''><option value='0'>Select Value</option></select>");
				sBuffer.append("</div>");	sBuffer.append("</td>");
			}	
			
			if(ipdConfig.getStrUnitNameReq().equals("1") && ipdConfig.getStrRoomNoReq().equals("1"))
				sBuffer.append("</tr>");
			else if(ipdConfig.getStrUnitNameReq().equals("1") || ipdConfig.getStrRoomNoReq().equals("1"))
			{
				sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("</td>");		
				sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("</td>");
				sBuffer.append("</tr>");
			}

			sBuffer.append("</table>");
		}
		catch (Exception e)
		{
			vo.setStrErrMsgString("PatientTransferTransHLP.getChangeOfWard---->" + e.getMessage());
			vo.setStrMsgType("1");
		}
		return sBuffer.toString();
	}
	
	
	public static String getChangeOfWardIPD(PatientTransferTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		IpdConfigUtil ipdConfig = null;
		try
		{
			ipdConfig = new IpdConfigUtil(vo.getStrHospCode());
			
			/*setStrPvtWardCode(ipdConfig.getStrPrivateWardType());
			formBean.setStrIpdConfIcuWard(ipdConfig.getStrIcuWardType());*/

				// Row 1 Transfer Date/Time & Bed Dbl Occup
			sBuffer.append("<input type='hidden' name='transId' value='" + PatientTransferTransHLP.wrdChng + "'>");
			sBuffer.append("<input type='hidden' name='strEntryDate' value='" + now() + "'>");
			sBuffer.append("<input type='hidden' name='strMSBed' value=''>");
			sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");
			sBuffer.append("<tr>");	
			sBuffer.append("<td width='25%' class='LABEL'>");	
			/*sBuffer.append("<div align='right'>");
			sBuffer.append("Transfer Date/Time");	
			sBuffer.append("</div>");*/	
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>");	
			sBuffer.append("<font color='blue'>");
			sBuffer.append(now());	
			sBuffer.append("</font>");	
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='LABEL' style='display:none;'>");	
			sBuffer.append("<div align='right'>");
			sBuffer.append("Bed Double Occupancy");	
			sBuffer.append("</div>");	
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='CONTROL' style='display:none;'>");
			sBuffer.append("<input type='checkbox' name='strIsBedVacant' value='1'");
				if (vo.getStrPrevDblOcc().equals("1")) 
			sBuffer.append(" checked");
			sBuffer.append(" onClick='isDoubleOccupancy(this);'>");
			sBuffer.append("</td>");	
			sBuffer.append("</tr>");
			
				// Row 2  
			sBuffer.append("<tr>");
				// Column 1 Department
			sBuffer.append("<td width='25%' class='LABEL'>");	
			/*sBuffer.append("<div align='right'>");
			sBuffer.append("<font color='red'>*</font>");	
			sBuffer.append("Department");
			sBuffer.append("</div>");*/	
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>");
			//---------------------------------
			sBuffer.append("<select style='cursor:pointer;cursor:hand' tabindex='1'name = 'strDepartment' class='comboNormal' onChange ='fununitIPD();' >"
					+ vo.getStrdeptproperty() + "</select>");
			sBuffer.append("</td>");
			
				// Column 2 Unit
			if(ipdConfig.getStrUnitNameReq().equals("1"))
			{
				sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
				sBuffer.append("<font color='red'>*</font>");	sBuffer.append("Unit");
				sBuffer.append("</div>");	sBuffer.append("</td>");			
				sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("<div id='unitId'>");
				//----------------------
				sBuffer.append("<select name='strUnit' tabindex='1' class='comboNormal' dir='' title='' onChange='funwardIPD();' ><option value='0'>Select Value</option></select>");
				sBuffer.append("</div>");	sBuffer.append("</td>");
				sBuffer.append("</tr>");
				sBuffer.append("<tr>");
			}
			
			
			// sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Ward
			// Type</div></td>");
			// sBuffer.append("<td width='25%' class='CONTROL' ><div id ='wardTypeId'>");
			// sBuffer.append("<select style='cursor:pointer;cursor:hand' name='strWardType' class='comboNormal' dir=''
			// title='' onChange='funward();'>"+vo.getStrwardType()+"</select>");
			// sBuffer.append("</div></td>");
			
				// Column 3 Ward
			sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
			
			if(!ipdConfig.getStrUnitNameReq().equals("1"))
				sBuffer.append("<input name='strUnit' type='hidden' value='0'>");
			if(!ipdConfig.getStrRoomNoReq().equals("1"))
				sBuffer.append("<input name='strRoom' type='hidden' value='0'>");
			
			
			sBuffer.append("<font color='red'>*</font>");	sBuffer.append("Ward");
			sBuffer.append("</div>");	sBuffer.append("</td>");		
			sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("<div id='wardId'>");
			//----------------------
			sBuffer.append("<select name='strWard' class='comboNormal' tabindex='1' dir='' title='' onChange=''><option value='0'>Select Value</option></select>");
			sBuffer.append("</div>");	sBuffer.append("</td>");

			
			// sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Room
			// Type</div></td>");
			// sBuffer.append("<td width='25%' class='CONTROL' >");
			// sBuffer.append("<select style='cursor:pointer;cursor:hand' name='strRoomType' class='comboNormal' dir=''
			// title='' onChange='funroom();'>"+vo.getStrRoomType()+"</select>");
			// sBuffer.append("</td>");

				// Column 4 Room
			if(!ipdConfig.getStrUnitNameReq().equals("1"))
			{
				sBuffer.append("</tr>");
				if(ipdConfig.getStrRoomNoReq().equals("1")) sBuffer.append("<tr>");
			}
			if(ipdConfig.getStrRoomNoReq().equals("1"))
			{
				sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
				sBuffer.append("<font color='red'>*</font>");	sBuffer.append("Room");
				sBuffer.append("</div>");	sBuffer.append("</td>");		
				sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("<div id='roomId'>");
				//----------------------
				sBuffer.append("<select name='strRoom'  class='comboNormal' dir='' title='' onChange=''><option value='0'>Select Value</option></select>");
				sBuffer.append("</div>");	sBuffer.append("</td>");
			}	
			
			if(ipdConfig.getStrUnitNameReq().equals("1") && ipdConfig.getStrRoomNoReq().equals("1"))
				sBuffer.append("</tr>");
			else if(ipdConfig.getStrUnitNameReq().equals("1") || ipdConfig.getStrRoomNoReq().equals("1"))
			{
				sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("</td>");		
				sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("</td>");
				sBuffer.append("</tr>");
			}

			sBuffer.append("</table>");
		}
		catch (Exception e)
		{
			vo.setStrErrMsgString("PatientTransferTransHLP.getChangeOfWard---->" + e.getMessage());
			vo.setStrMsgType("1");
		}
		return sBuffer.toString();
	}
	
	public static String getChangeOfUnit(PatientTransferTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		IpdConfigUtil ipdConfig = null;
		try
		{
			ipdConfig = new IpdConfigUtil(vo.getStrHospCode());
			
				// Row 1 Transfer Date/Time & Bed Dbl Occup
			sBuffer.append("<input type='hidden' name='transId' value='" + PatientTransferTransHLP.wrdChng + "'>");
			sBuffer.append("<input type='hidden' name='strEntryDate' value='" + now() + "'>");
			sBuffer.append("<input type='hidden' name='strMSBed' value=''>");
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-3' align='right'>");
			sBuffer.append("<label>Transfer Date/Time</label>");	
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-9' style='color:blue;'>");
			sBuffer.append(now());	
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-3' align='right'>");
			sBuffer.append("<label ><font color='red'>*</font>Department</label>");	
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<select style='cursor:pointer;cursor:hand' name = 'strDepartment' tabindex='1' class='browser-default custom-select' onChange ='fununit();' >"
					+ vo.getStrdeptproperty()  + "</select>");
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-3' align='right'>");
			sBuffer.append("<label >Ward</label>");	
			sBuffer.append("</div>");
			if(!ipdConfig.getStrUnitNameReq().equals("1"))
				sBuffer.append("<input name='strUnit' type='hidden' value='0'>");
			if(!ipdConfig.getStrRoomNoReq().equals("1"))
				sBuffer.append("<input name='strRoom' type='hidden' value='0'>");
			sBuffer.append("<div class='col-sm-2' id='wardId'>");
			sBuffer.append("<select name='strWard' class='browser-default custom-select' dir='' title='' onChange='' disabled>"+vo.getStrWard()+"</select>");
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-3' align='right'>");
			sBuffer.append("<label ><font color='red'>*</font>Unit</label>");	
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2' id='unitId'>");
			sBuffer.append("<select name='strUnit' class='browser-default custom-select'  tabindex='1' dir='' title='' onChange='getRoomCombo(this);'>" +
					 vo.getStrunitproperty() + "</select>");
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-3' align='right'>");
			sBuffer.append("<label >Room</label>");	
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2' id='roomId'>");
			sBuffer.append("<select name='strRoom' class='browser-default custom-select' dir='' title='' onChange=''><option value='0'>Select Value</option></select>");
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			
			/*sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");
			sBuffer.append("<tr>");	
			sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
			sBuffer.append("Transfer Date/Time");	sBuffer.append("</div>");	sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("<font color='blue'>");
			sBuffer.append(now());	sBuffer.append("</font>");	sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='LABEL'>");	
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>");
			sBuffer.append("</td>");	sBuffer.append("</tr>");
			
				// Row 2  
			sBuffer.append("<tr>");
				// Column 1 Department
			sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
			sBuffer.append("Department");
			sBuffer.append("</div>");	sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>");
			//---------------------------------
			sBuffer.append("<select style='cursor:pointer;cursor:hand'  tabindex='1' name = 'strDepartment' class='comboNormal' disabled>"
					+ vo.getStrdeptproperty() + "</select>");
			sBuffer.append("</td>");
			
			
			// Column 2 Ward
			sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
			sBuffer.append("Ward");
			sBuffer.append("</div>");	sBuffer.append("</td>");		
			sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("<div id='wardId'>");
			//----------------------
			sBuffer.append("<select name='strWard' class='comboNormal' dir='' title='' onChange='' disabled>"+vo.getStrWard()+"</select>");
			sBuffer.append("</div>");	sBuffer.append("</td>");
			sBuffer.append("</tr>");
			
			// row 3
				// Column 1 Unit
			
			sBuffer.append("<tr>");
				sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
				sBuffer.append("<font color='red'>*</font>");	sBuffer.append("Unit");
				sBuffer.append("</div>");	sBuffer.append("</td>");			
				sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("<div id='unitId'>");
				//----------------------
				sBuffer.append("<select name='strUnit' class='comboNormal'  tabindex='1' dir='' title='' onChange='getRoomCombo(this);'>" +
						 vo.getStrunitproperty() + "</select>");
				sBuffer.append("</div>");	sBuffer.append("</td>");
				
			

				// Column 2 Room
			
				
				sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
				sBuffer.append("Room");
				sBuffer.append("</div>");	sBuffer.append("</td>");		
				sBuffer.append("<td width='25%' class='CONTROL'>");	sBuffer.append("<div id='roomId'>");
				//----------------------
				sBuffer.append("<select name='strRoom' class='comboNormal' dir='' title='' onChange=''><option value='0'>Select Value</option></select>");
				sBuffer.append("</div>");	sBuffer.append("</td>");
				sBuffer.append("</tr>");
			

			sBuffer.append("</table>");*/
		}
		catch (Exception e)
		{
			vo.setStrErrMsgString("PatientTransferTransHLP.getChangeOfUnit---->" + e.getMessage());
			vo.setStrMsgType("1");
		}
		return sBuffer.toString();
	}
	public static String getChangeOfBed(PatientTransferTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      try{
	              sBuffer.append("<input type='hidden' name='transId' value='"+PatientTransferTransHLP.bedChng+"'>");
	              sBuffer.append("<input type='hidden' name='strEntryDate' value='"+now()+"'>");
	              
	              sBuffer.append("<div class='row rowFlex reFlex'>");
	  			sBuffer.append("<div class='col-sm-3' align='right'>");
	  			sBuffer.append("<label>Transfer Date/Time</label>");	
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-9' style='color:blue;'>");
	  			sBuffer.append(now());	
	  			sBuffer.append("</div>");
	  			sBuffer.append("</div>");
	  			
	  			sBuffer.append("<div class='row rowFlex reFlex'>");
	  			sBuffer.append("<div class='col-sm-3' align='right'>");
	  			sBuffer.append("<label ><font color='red'>*</font>Room Type</label>");	
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-2'>");
	              sBuffer.append("<select style='cursor:pointer;cursor:hand' tabindex='0' name='strRoomType' class='browser-default custom-select' dir='' title='' onChange='funroom();'>"+vo.getStrRoomType()+"</select>");
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-2' align='right'>");
	  			sBuffer.append("<label >Room</label>");	
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-2' id ='roomId'>");
	            sBuffer.append("<select style='cursor:pointer;cursor:hand' tabindex='0' name='strRoomType' class='browser-default custom-select' dir='' title='' onChange='funroom();'>"+vo.getStrRoomType()+"</select>");
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-3'>");
	  			sBuffer.append("</div>");
	  			sBuffer.append("</div>");
	  			
	  			
	  			sBuffer.append("<div class='row rowFlex reFlex'>");
	  			sBuffer.append("<div class='col-sm-3' align='right'>");
	  			sBuffer.append("<label ><font color='red'>*</font>Bed Type</label>");	
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-2'>");
	            sBuffer.append("<select style='cursor:pointer;cursor:hand' tabindex='0' name='strBedType' class='browser-default custom-select' dir='' title='Click to view Bed Satus Chart' onChange='sharableChkBox();'>"+vo.getStrBedType()+"</select>");
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-2' align='right'>");
	  			sBuffer.append("<label >Bed</label>");	
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-2' id ='bedId'>");
	              sBuffer.append("<select name='strBed' id='bedSelectId' class='browser-default custom-select' tabindex='1' dir='' title='Select from List of Vacant Beds' onChange=''><option value='0'>Select Value</option></select>");
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-1'>");
	  			sBuffer.append("<button type='button' class='btn btn-info btn-sm'   " +
	      				"  title ='Click to view Bed Status'  data-toggle='modal' href='#myModal' id='modellink' onClick ='openPopup("
	      				+ "\"BEDSTATUS\"" + ");'><i class='fas fa-bed'></i></button> ");
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-2'>");	  			
	            sBuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;Sharable Bed&nbsp;&nbsp;<input id='sharableChkid' name='sharableChk' value='0' onclick='sharableChkBox();' type='checkbox'>");
	  			sBuffer.append("</div>");
	  			sBuffer.append("</div>");
	  			
	  			
	            /*  sBuffer.append("<table  class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");   
	              sBuffer.append("<tr><td  width='25%' class='LABEL' ><div align='right'>Transfer Date/Time</div></td>");
	              sBuffer.append("<td width='75%' colspan='4' class='CONTROL'><font color='blue'>");
	              sBuffer.append(now());
	              sBuffer.append("</font></td></tr>");  
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'>Room Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;cursor:hand' tabindex='0' name='strRoomType' class='comboNormal' dir='' title='' onChange='funroom();'>"+vo.getStrRoomType()+"</select>");
	              sBuffer.append("</td>");
	              //sBuffer.append("<td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Room</div></td>");
	              //Commented for non mandatory of room as per change request dated 13Jun2011
	              sBuffer.append("<td width='25%' class='LABEL'><div align='right'>Room</div></td>");
	              sBuffer.append("<td colspan='2 'width='25%' class='CONTROL' ><div id ='roomId'>");
	              sBuffer.append("<select name='strRoom' tabindex='1' class='comboNormal'  dir='' title='' onChange=''><option value='0'>Select Value</option>");
	              sBuffer.append("</select>");
	              sBuffer.append("</div></td></tr>");  
	              sBuffer.append("<tr><td width='25%' class='LABEL'><div align='right'>Bed Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;cursor:hand' tabindex='0' name='strBedType' class='comboNormal' dir='' title='Click to view Bed Satus Chart' onChange='sharableChkBox();'>"+vo.getStrBedType()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td  width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Bed</div></td>");
	              sBuffer.append("<td  width='15%'  class='CONTROL' ><div id ='bedId'>");
	              sBuffer.append("<select name='strBed' id='bedSelectId' class='comboNormal' tabindex='1' dir='' title='Select from List of Vacant Beds' onChange=''><option value='0'>Select Value</option></select>");
	              //sBuffer.append("</div></td><td align='right' width='10%' class='CONTROL' ><img style='cursor:pointer;cursor:hand' title='Click to view Bed Status' src='../../hisglobal/images/Bed_.gif'  onClick ='openPopup("+"\"BEDSTATUS\""+");'></td></tr>");
	              sBuffer.append("</div></td><td align='left' width='10%' class='CONTROL' ><img src='../../hisglobal/images/Bed_.gif'  " +
	      				"style='cursor:hand;cursor:pointer;'  title ='Click to view Bed Status'  data-toggle='modal' href='#myModal' id='modellink' onClick ='openPopup("
	      				+ "\"BEDSTATUS\"" + ");'></td></tr> ");
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'></div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("</td>");
	              //sBuffer.append("<td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Room</div></td>");
	              //Commented for non mandatory of room as per change request dated 13Jun2011
	              sBuffer.append("<td width='25%' class='LABEL'><div align='right'>Sharable Bed</div></td>");
	              sBuffer.append("<td colspan='2 'width='25%' class='CONTROL' ><div id =''>");
	              sBuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;<input id='sharableChkid' name='sharableChk' value='0' onclick='sharableChkBox();' type='checkbox'>");
	              sBuffer.append("</div></td></tr>");   
	              sBuffer.append("</table>"); */
		     }
			 catch(Exception e)
			 {
				 vo.setStrErrMsgString("PatientTransferTransHLP.getChangeOfBed---->"+e.getMessage());
				 vo.setStrMsgType("1");
		     }
		     return sBuffer.toString();
		 }
	
	
	/*
	 *  IN CASE OF IPD DESK ONLY
	 */
	public static String getChangeOfBedIPD(PatientTransferTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      try{
	              sBuffer.append("<input type='hidden' name='transId' value='"+PatientTransferTransHLP.bedChng+"'>");
	              sBuffer.append("<input type='hidden' name='strEntryDate' value='"+now()+"'>");
	              sBuffer.append("<table  class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");   
	              sBuffer.append("<tr><td  width='25%' class='LABEL' ><div align='right'>Transfer Date/Time</div></td>");
	              sBuffer.append("<td width='75%' colspan='4' class='CONTROL'><font color='blue'>");
	              sBuffer.append(now());
	              sBuffer.append("</font></td></tr>");  
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'>Room Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;cursor:hand' tabindex='0' name='strRoomType' class='comboNormal' dir='' title='' onChange='funroomIPD();'>"+vo.getStrRoomType()+"</select>");
	              sBuffer.append("</td>");
	              //sBuffer.append("<td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Room</div></td>");
	              //Commented for non mandatory of room as per change request dated 13Jun2011
	              sBuffer.append("<td width='25%' class='LABEL'><div align='right'>Room</div></td>");
	              sBuffer.append("<td colspan='2 'width='25%' class='CONTROL' ><div id ='roomId'>");
	              sBuffer.append("<select name='strRoom' tabindex='0' class='comboNormal'  dir='' title='' onChange=''><option value='0'>Select Value</option>");
	              sBuffer.append("</select>");
	              sBuffer.append("</div></td></tr>");  
	              sBuffer.append("<tr><td width='25%' class='LABEL'><div align='right'>Bed Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;cursor:hand' name='strBedType' class='comboNormal' dir='' title='Click to view Bed Satus Chart' onChange='sharableChkBox();'>"+vo.getStrBedType()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td  width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Bed</div></td>");
	              sBuffer.append("<td  width='15%'  class='CONTROL' ><div id ='bedId'>");
	              sBuffer.append("<select name='strBed' id='bedSelectId' tabindex='1' class='comboNormal' dir='' title='Select from List of Vacant Beds' onChange='funbed();'><option>"+vo.getStrBed()+"</option></select>");
	              //sBuffer.append("</div></td><td align='right' width='10%' class='CONTROL' ><img style='cursor:pointer;cursor:hand' title='Click to view Bed Status' src='/HBIMS/hisglobal/images/Bed_.gif'  onClick ='openPopupIPD("+"\"BEDSTATUS\""+");'></td></tr>");
	              sBuffer.append("</div></td><td align='right' width='10%' class='CONTROL' ><img src='../../hisglobal/images/Bed_.gif'  " +
		      				"style='cursor:hand;cursor:pointer;'  title ='Click to view Bed Status'  data-toggle='modal' href='#myModal' id='modellink' onClick ='openPopupIPD("
		      				+ "\"BEDSTATUS\"" + ");'></td></tr> ");
	              sBuffer.append("<tr><td width='25%' class='LABEL'></td><td width='25%' class='CONTROL'></td>");
	              sBuffer.append("<td width='25%' class='LABEL'><div align='right'>Sharable Bed</div></td>");
	              sBuffer.append("<td width='15%' class='CONTROL'>&nbsp;&nbsp;&nbsp;<input type='checkbox' value='0' name='sharableChk' id='sharableChkid' onclick='sharableChkBox();'></td></tr>");  
	              sBuffer.append("</table>");    	              
		     }
			 catch(Exception e)
			 {
				 vo.setStrErrMsgString("PatientTransferTransHLP.getChangeOfBed---->"+e.getMessage());
				 vo.setStrMsgType("1");
		     }
		     return sBuffer.toString();
		 }
		


	public static String getChangeOfDeptUnit(PatientTransferTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      try{
	              sBuffer.append("<input type='hidden' name='transId' value='"+PatientTransferTransHLP.depUntChng+"'>");
	              sBuffer.append("<input type='hidden' name='strEntryDate' value='"+now()+"'>");
	              sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>"); 
	              sBuffer.append("<tr><td   class='LABEL' ><div align='right'>Transfer Date/Time</div></td>");
	              sBuffer.append("<td  colspan='3' class='CONTROL'><font color='blue'>");
	              sBuffer.append(now());
	              sBuffer.append("</font></td></tr>");  
	              sBuffer.append("<tr><td  class='LABEL' ><div align='right'><font color='red'>*</font>Department</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select   tabindex='1'style='cursor:pointer;cursor:hand' name='strDepartment' dir='' title='' class='comboNormal' onChange ='funUnit();'>"+vo.getStrdeptproperty()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Unit</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' ><div id ='unitId'>");
	              sBuffer.append("<select name='strUnit' tabindex='1' class='comboNormal' dir='' title='' onChange='funconsultant();'><option value='0'>Select Value</option></select>");
	              sBuffer.append("</div></td></tr>");  
	              sBuffer.append("<tr><td  width='25%' class='LABEL' ><div align='right'>Consultant Id</div></td>");
	              sBuffer.append("<td colspan='3' class='CONTROL' ><div id='consultantId'>");
	              sBuffer.append("<select name='strConsultantCode' class='comboNormal' dir='' title='' onChange=''><option value='0'>Select Value</option></select>");
	              sBuffer.append("</div></td></tr>");  
	              sBuffer.append("</table>");    	              
		     }
			 catch(Exception e)
			 {
				 vo.setStrErrMsgString("PatientTransferTransHLP.getChangeOfDeptUnit---->"+e.getMessage());
				 vo.setStrMsgType("1");
		     }
		     return sBuffer.toString();
	  }


	
	public static String getChangeOfServArea(PatientTransferTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      try{
	              sBuffer.append("<input type='hidden' name='transId' value='"+PatientTransferTransHLP.serArChng+"'>");
	              sBuffer.append("<input type='hidden' name='strEntryDate' value='"+now()+"'>");
	              sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>"); 
	              sBuffer.append("<tr><td  width='25%' class='LABEL' ><div align='right'>Transfer Date/Time</div></td>");
	              sBuffer.append("<td width='75%' colspan='3' class='CONTROL'><font color='blue'>");
	              sBuffer.append(now());
	              sBuffer.append("</font></td></tr>");  
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Service Area Code</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;cursor:hand' tabindex='1' name='strServArea' class='comboNormal' dir='' title='' onChange='funServRoom();'>"+vo.getStrServArea()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Room No.</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' ><div id='roomId'>");
	              sBuffer.append("<select name='strRoom' class='comboNormal' dir='' title='' onChange=''><option value='0'>Select Value</option></select>");
	              sBuffer.append("</div></td></tr>");  
	              sBuffer.append("</table>");    	              
		     }
			 catch(Exception e)
			 {
				 vo.setStrErrMsgString("PatientTransferTransHLP.getChangeOfServArea---->"+e.getMessage());
				 vo.setStrMsgType("1");
		     }
		     return sBuffer.toString();
	  }
	
	public static String getChangeOfServiceAreaOT(PatientTransferTransFB  formBean)
	{
		StringBuffer sBuffer = new StringBuffer("");
	      try{
	              sBuffer.append("<input type='hidden' name='transId' value='"+PatientTransferTransHLP.wrdChng+"'>");
	              sBuffer.append("<input type='hidden' name='strEntryDate' value='"+now()+"'>");
	              sBuffer.append("<input type='hidden' name='strMSBed' value=''>");
	              
	              sBuffer.append("<div class='row rowFlex reFlex'>");
	  			sBuffer.append("<div class='col-sm-3' align='right'>");
	  			sBuffer.append("<label>Transfer Date/Time</label>");	
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-9' style='color:blue;'>");
	  			sBuffer.append(now());	
	  			sBuffer.append("</div>");
	  			sBuffer.append("</div>");
	  			
	  			sBuffer.append("<div class='row rowFlex reFlex'>");
	  			sBuffer.append("<div class='col-sm-3' align='right'>");
	  			sBuffer.append("<label ><font color='red'>*</font>Service Type</label>");	
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-2'>");
	            sBuffer.append("<select style='cursor:pointer;' tabindex='1' name = 'strServiceType' class='browser-default custom-select' onChange ='getServiceName(this);'>"+formBean.getStrServiceType()+"</select>");
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-3' align='right'>");
	  			sBuffer.append("<label >Service</label>");	
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-2' id='serviceId'>");
	              sBuffer.append("<select name='strServiceName' tabindex='1' class='browser-default custom-select' dir='' title='' onChange='serviceValidate(this)'><option value='0'>Select Value</option></select>");
	  			sBuffer.append("</div>");
	  			sBuffer.append("<div class='col-sm-2'>");
	  			 sBuffer.append("<input type='checkbox' name='strIsBedVacant' value='1'");
	              if (formBean.getStrPrevDblOcc().equals("1")) sBuffer.append(" checked");
	              sBuffer.append(" onClick='isDoubleOccupancy(this);'>");
	  			sBuffer.append("</div>");
	  			sBuffer.append("</div>");
	  			
	              
	           /*   sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>"); 
	              sBuffer.append("<tr><td  width='25%' class='LABEL' ><div align='right'>Transfer Date/Time</div></td>");
	              sBuffer.append("<td width='25%' colspan='1' class='CONTROL'><font color='blue'>");
	              sBuffer.append(now());
	              sBuffer.append("</font></td>");  
	              sBuffer.append("<td  width='25%' class='LABEL' ><div align='right'></div></td>");
	              sBuffer.append("<td width='25%' colspan='1' class='CONTROL'>");
                  sBuffer.append("</td></tr>"); 
	              sBuffer.append("<tr style='display:none;'>");
	              sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
	              sBuffer.append("Bed Double Occupancy");	sBuffer.append("</div>");	sBuffer.append("</td>");
	              sBuffer.append("<td width='25%' class='CONTROL'>");
	              sBuffer.append("<input type='checkbox' name='strIsBedVacant' value='1'");
	              if (formBean.getStrPrevDblOcc().equals("1")) sBuffer.append(" checked");
	              sBuffer.append(" onClick='isDoubleOccupancy(this);'>");
	              sBuffer.append("</td>");	
	              sBuffer.append("<td  width='25%' class='LABEL' ><div align='right'></div></td>");
	              sBuffer.append("<td width='25%'  class='CONTROL'>");
	              //sBuffer.append("<input type='checkbox' name='strWhetherVacantBed' value='0'  disabled='true'");
	              //sBuffer.append(" onClick=''>");
	              sBuffer.append("</td>");
	              sBuffer.append("</tr>");
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Service Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;' tabindex='1' name = 'strServiceType' class='comboNormal' onChange ='getServiceName(this);'>"+formBean.getStrServiceType()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Service</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' ><div id ='serviceId'>");
	              sBuffer.append("<select name='strServiceName' tabindex='1' class='comboNormal' dir='' title='' onChange='serviceValidate(this)'><option value='0'>Select Value</option></select>");
	              sBuffer.append("</div></td></tr>");   
	              sBuffer.append("</table>");  */  	              
		     }
			 catch(Exception e)
			 {
				 formBean.setStrErrMsgString("PatientTransferTransHLP.getChangeOfWard---->"+e.getMessage());
				 formBean.setStrMsgType("1");
		     }
		     return sBuffer.toString(); 	 
	}
	
	
	
	/*
	 *  IN CASE OF IPD DESK ONLY 
	 */
	public static String getChangeOfServiceAreaOTIPD(PatientTransferTransFB  formBean)
	{
		StringBuffer sBuffer = new StringBuffer("");
	      try{
	              sBuffer.append("<input type='hidden' name='transId' value='"+PatientTransferTransHLP.wrdChng+"'>");
	              sBuffer.append("<input type='hidden' name='strEntryDate' value='"+now()+"'>");
	              sBuffer.append("<input type='hidden' name='strMSBed' value=''>");
	              sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>"); 
	              sBuffer.append("<tr><td  width='25%' class='LABEL' ><div align='right'>Transfer Date/Time</div></td>");
	              sBuffer.append("<td width='25%' colspan='1' class='CONTROL'><font color='blue'>");
	              sBuffer.append(now());
	              sBuffer.append("</font></td>");  
	              sBuffer.append("<td  width='25%' class='LABEL' ><div align='right'></div></td>");
	              sBuffer.append("<td width='25%' colspan='1' class='CONTROL'>");
                  sBuffer.append("</td></tr>"); 
	              sBuffer.append("<tr style='display:none;'>");
	              sBuffer.append("<td width='25%' class='LABEL'>");	sBuffer.append("<div align='right'>");
	              sBuffer.append("Bed Double Occupancy");	sBuffer.append("</div>");	sBuffer.append("</td>");
	              sBuffer.append("<td width='25%' class='CONTROL'>");
	              sBuffer.append("<input type='checkbox' name='strIsBedVacant' value='1'");
	              if (formBean.getStrPrevDblOcc().equals("1")) sBuffer.append(" checked");
	              sBuffer.append(" onClick='isDoubleOccupancy(this);'>");
	              sBuffer.append("</td>");	
	              sBuffer.append("<td  width='25%' class='LABEL' ><div align='right'></div></td>");
	              sBuffer.append("<td width='25%'  class='CONTROL'>");
	              //sBuffer.append("<input type='checkbox' name='strWhetherVacantBed' value='0'  disabled='true'");
	              //sBuffer.append(" onClick=''>");
	              sBuffer.append("</td>");
	              sBuffer.append("</tr>");
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Service Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;' tabindex='1' name = 'strServiceType' class='comboNormal' onChange ='getServiceName(this);'>"+formBean.getStrServiceType()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Service</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' ><div id ='serviceId'>");
	              sBuffer.append("<select name='strServiceName' tabindex='1' class='comboNormal' dir='' title='' onChange='serviceValidate(this)'><option value='0'>Select Value</option></select>");
	              sBuffer.append("</div></td></tr>");   
	              sBuffer.append("</table>");    	              
		     }
			 catch(Exception e)
			 {
				 formBean.setStrErrMsgString("PatientTransferTransHLP.getChangeOfWard---->"+e.getMessage());
				 formBean.setStrMsgType("1");
		     }
		     return sBuffer.toString(); 	 
	}
	
	
	public static String getChangeOfHospital(PatientTransferTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      LinkedHashMap<String , String> mapProcedureParam =null;
	      try{
	              sBuffer.append("<input type='hidden' name='transId' value='"+PatientTransferTransHLP.hosChng+"'>");
	              sBuffer.append("<input type='hidden' name='strEntryDate' value='"+now()+"'>");
	              sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>"); 
	              sBuffer.append("<tr><td  width='25%'  class='LABEL' ><div align='right'>Transfer Date/Time</div></td>");
	              sBuffer.append("<td width='75%' colspan='3' class='CONTROL' ><font color='blue'>");
	              sBuffer.append(now());
	              sBuffer.append("</font></td></tr>");  
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Location</div></td>");
	              sBuffer.append("<td width='75%' class='CONTROL' colspan='3' >");
	              sBuffer.append("<select tabindex='1' name='strLocation'>");
	              mapProcedureParam = new LinkedHashMap<String, String>();
	              mapProcedureParam.put("err", "#1");
	              mapProcedureParam.put("resultset", "#2");
	              sBuffer.append(HisComboOptions.getOptionsFromProc("pkg_ipd_mst.Proc_hospital_mst", mapProcedureParam, "", "0^Select Value", false));
	              sBuffer.append("</select>");
	              sBuffer.append("</td></tr>");  
	              sBuffer.append("</table>");    	              
		     }
			 catch(Exception e)
			 {
				 vo.setStrErrMsgString("PatientTransferTransHLP.getChangeOfHospital---->"+e.getMessage());
				 vo.setStrMsgType("1");
		     }
		     return sBuffer.toString();
	  }
	public static String getError(PatientTransferTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      try{
	    	      sBuffer.append("<input type='hidden' name='transId' value='10'>");
	    	      sBuffer.append("<input type='hidden' name='strEntryDate' value='"+now()+"'>");
	              sBuffer.append("<table class='TABLEWIDTH' border='0' bgcolor='#000000' cellspacing='1px' align='center'>"); 
	              if(vo.getStrErrorCode().equals("1"))
	                sBuffer.append("<tr><td width='100%' class='multiLabel' colspan='4' ><font size='2' color='blue' weight='bold'>"+now()+"::</font><font size='2' color='red' weight='bold'>Error while getting Department List or Room/Bed Type Initialization</font></td>");
	              else if(vo.getStrErrorCode().equals("0"))
	            	sBuffer.append("<tr><td width='100%' class='multiLabel' colspan='4' ><font size='2' color='blue' weight='bold'>"+now()+"::</font><font size='2' color='red' weight='bold'>Error while getting Required Information</font></td>"); 
	              else
	            	  sBuffer.append("<tr><td width='100%' class='multiLabel' colspan='4' ><font size='2' color='blue' weight='bold'>"+now()+"::</font><font size='2' color='red' weight='bold'>Error while setting HTML Response</font></td>");   
	              sBuffer.append("</tr>"); 
	              sBuffer.append("<tr><td width='100%' class='multiLabel' colspan='4' ><font size='1' color='red'>"+vo.getStrErrMsgString()+"</font></td></tr>"); 
	              sBuffer.append("</table>");    	              
		     }
			 catch(Exception e)
			 {
				 vo.setStrErrMsgString("PatientTransferTransHLP.getChangeOfHospital---->"+e.getMessage());
				 vo.setStrMsgType("1");
		     }
		     return sBuffer.toString();
	  }
/*
	public static String getBedDetails(PatientTransferTransVO vo)
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws=vo.getBedDetailWS();
		int wsSize=ws.size();
		int size=4;
		int count=1;
		try
		{
			sb.append("<br><table width='75%' align='center' border='5' bordercolor='#9DAFC6' cellspacing='1px'>");
			sb.append("<tr>");
			if(wsSize!=0)
			{	
				int i=0;
				ws.next();
				while(i<wsSize)
				{
					while(count<=size)
					{
						String temp[]=ws.getString(1).replace("^", "#").split("#");
						sb.append("<input type='hidden' id='"+temp[0]+"'name='bedCode' value='"+ws.getString(2)+"'>");
						sb.append(getCSS(ws.getString(2),temp[0]));
						boolean chk=ws.next();
						if(chk==false)
						{
							sb.append("</tr>");
							break;
						}
						i++;
						count++;
					}
					if(count>4)
					{
						sb.append("</tr>");
						count=1;
						sb.append("<tr>");
					}
				}
			}
			else
			{
			  sb.append("<td class='multiLabel'><font color='red' size='3' weight='bold'>No Beds Currently in Room</font></td></tr>");
			}
		}
		catch(Exception e)
		{
			vo.setStrErrMsgString("PatientAdmissionTransHLP.getBedDetails() -->"+ e.getMessage());
			vo.setStrMsgType("1");
		}
		sb.append("</table>");
		sb.append("<br><table align='center'>");
		sb.append("<tr><td bgcolor='#FFA980' width='10'></td><td class='multiLabel' width='60'>Blocked</td>");
  		sb.append("<td width='10'></td>");
  		sb.append("<td bgcolor='#CB5C5C' width='10'></td>");
  		sb.append("<td class='multiLabel' width='60'>Occupied</td>");
  		sb.append("<td width='10'></td>");
		sb.append("<td bgcolor='#8CE3E3' width='10'></td>");
  		sb.append("<td class='multiLabel' width='60'>Booked</td><td width='10'></td>");
  		sb.append("<td bgcolor='#88F78D' width='10'></td>\n");
  		sb.append("<td class='multiLabel' width='60'>Vacant</td>");
  		sb.append("<td width='10'></td><td bgcolor='#FEEB9E' width='10'></td>");
  		sb.append("	<td class='multiLabel' width='60'>Vacant But Dirty</td>	</tr>");
  		sb.append("</table>");
		return sb.toString();
	}
	
	public static String getCSS(String bedName,String status)
	{
		int strBedStatusCode=0;
		String temp[]=status.replace("^", "#").split("#");
		strBedStatusCode=Integer.parseInt(temp[0]);
		return  makeCss(bedName,strBedStatusCode);
	}
	/**
	 * This function decide css of bed at run time on the basis of bed status code
	 * @param bedName
	 * @param bedStatusCode
	 * @return String
	 
	public static String makeCss(String bedName,int bedStatusCode)
	{
		String css="";
		switch(bedStatusCode)
		{
			case 10:
				css="<td bgcolor='#9EFEA6' width='5%'><input style='background-color:#9EFEA6;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"' onClick='getBedname(this)'></td>" ;
				break;
					
			case 11:
				css="<td bgcolor='#FEEB9E' width='5%'><input style='background-color:#FEEB9E;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
						
			case 12:
				css="<td bgcolor='#CB5C5C' width='5%'><input style='background-color:#CB5C5C;;width: 70px' " +
						"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
			case 13:
				css="<td bgcolor='#FFA980' width='5%'><input style='background-color:#FFA980;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
			case 14:
				css="<td bgcolor='#8CE3E3' width='5%'><input style='background-color:#8CE3E3;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
		}
		return css;
		
	}*/

}
	 