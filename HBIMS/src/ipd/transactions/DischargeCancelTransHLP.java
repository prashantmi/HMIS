package ipd.transactions;

import ipd.IpdVO;

import java.util.ResourceBundle;

import javax.sql.rowset.WebRowSet;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

public class DischargeCancelTransHLP {
	
	public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy/HH:mm:ss";
	private static ResourceBundle hisProp = ResourceBundle.getBundle("ipd.hisIpdProperties");
	public static String bedChng = hisProp.getString("BED_CHANGE");
    public static  String now() 
    {
    	HisUtil util=null;
    	String a="";
    	util=new HisUtil("transaction","DischargeCancelTransHLP");
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
	
	
	public static String getChangeOfBed(DischargeCancelTransVO vo)
	 {
		
	      StringBuffer sBuffer = new StringBuffer("");
	      try{
	              sBuffer.append("<input type='hidden' name='transId' value='"+DischargeCancelTransHLP.bedChng+"'>");
	              sBuffer.append("<table width='95%' border='0' cellspacing='1px' align='center'>");   
	              sBuffer.append("<tr><td  width='25%' class='LABEL' ><div align='right'>Discharge Cancellation Date/Time</div></td>");
	              sBuffer.append("<td width='75%' colspan='4' class='CONTROL' ><font color='blue'>");
	              sBuffer.append(now());
	              sBuffer.append("</font></td></tr>");  
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'>Room Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;' name='strRoomType' class='comboNormal' dir='' title='' onChange='funroom_disCancel();'>"+vo.getStrRoomType()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td width='25%' class='LABEL' ><div align='right'>Room</div></td>");
	              sBuffer.append("<td colspan='2 'width='25%' class='CONTROL' ><div id ='roomId'>");
	              sBuffer.append("<select name='strRoom' class='comboNormal'  dir='' title='' onChange='sharableChkBox();'>"+vo.getStrRoom()+"</select>");
	              sBuffer.append("</div></td></tr>");  
	              sBuffer.append("<tr><td width='25%' class='LABEL'><div align='right'>Bed Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;' name='strBedType' class='comboNormal' dir='' title='' onChange='funbed();'>"+vo.getStrBedType()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td  width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Bed</div></td>");
	              sBuffer.append("<td  width='15%'  class='CONTROL' ><div id ='bedId'>");
	              sBuffer.append("<select name='strBed' class='comboNormal' dir='' title='Click to view Bed Status' onChange=''>"+vo.getStrBed()+"</select></div></td></tr>");
	             // sBuffer.append("</div></td><td align='right' width='10%' class='CONTROL' ><img style='cursor:pointer;cursor:hand' title='Click to view Bed Status' src='../../hisglobal/images/Bed_.gif'  data-toggle='modal' href='#myModal' id='modellink'   onClick ='openPopup("+"\"BEDSTATUS\""+");'></td></tr>");
	              sBuffer.append("<tr><td width='25%' class='LABEL'></td><td  width='15%'  class='CONTROL'></td>");
	              sBuffer.append("<td  width='25%' class='LABEL' ><div align='right'>Sharable Bed</div></td>");
	              sBuffer.append("<td  width='15%'  class='CONTROL' >&nbsp;&nbsp;&nbsp;<input type='checkbox' id='sharableChkid' name='sharableChk' value='0' onclick='sharableChkBox();'></tr>");
	              sBuffer.append("</table>");    	              
		     }
			 catch(Exception e)
			 {
				 new HisException("Discharge Cancellation Trans","DischargeCancelTransHLP.getChangeOfBed()-->",e.getMessage());
		     }
		     return sBuffer.toString();
		 }
	
	public static String getChangeOfBed_BS(DischargeCancelTransVO vo)
	 {
		
	      StringBuffer sBuffer = new StringBuffer("");
	      try{
	              sBuffer.append("<input type='hidden' name='transId' value='"+DischargeCancelTransHLP.bedChng+"'>");
	             /* sBuffer.append("<table width='95%' border='0' cellspacing='1px' align='center'>");   
	              sBuffer.append("<tr><td  width='25%' class='LABEL' ><div align='right'>Discharge Cancellation Date/Time</div></td>");
	              sBuffer.append("<td width='75%' colspan='4' class='CONTROL' ><font color='blue'>");
	              sBuffer.append(now());
	              sBuffer.append("</font></td></tr>");  
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'>Room Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;' name='strRoomType' class='comboNormal' dir='' title='' onChange='funroom_disCancel();'>"+vo.getStrRoomType()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td width='25%' class='LABEL' ><div align='right'>Room</div></td>");
	              sBuffer.append("<td colspan='2 'width='25%' class='CONTROL' ><div id ='roomId'>");
	              sBuffer.append("<select name='strRoom' class='comboNormal'  dir='' title='' onChange='sharableChkBox();'>"+vo.getStrRoom()+"</select>");
	              sBuffer.append("</div></td></tr>");  
	              sBuffer.append("<tr><td width='25%' class='LABEL'><div align='right'>Bed Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;' name='strBedType' class='comboNormal' dir='' title='' onChange='funbed();'>"+vo.getStrBedType()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td  width='25%' class='LABEL' ><div align='right'><font color='red'>*</font>Bed</div></td>");
	              sBuffer.append("<td  width='15%'  class='CONTROL' ><div id ='bedId'>");
	              sBuffer.append("<select name='strBed' class='comboNormal' dir='' title='Click to view Bed Status' onChange=''>"+vo.getStrBed()+"</select>");
	              sBuffer.append("</div></td><td align='right' width='10%' class='CONTROL' ><img style='cursor:pointer;cursor:hand' title='Click to view Bed Status' src='../../hisglobal/images/Bed_.gif'  data-toggle='modal' href='#myModal' id='modellink'   onClick ='openPopup("+"\"BEDSTATUS\""+");'></td></tr>");
	              sBuffer.append("<tr><td width='25%' class='LABEL'></td><td  width='15%'  class='CONTROL'></td>");
	              sBuffer.append("<td  width='25%' class='LABEL' ><div align='right'>Sharable Bed</div></td>");
	              sBuffer.append("<td  width='15%'  class='CONTROL' >&nbsp;&nbsp;&nbsp;<input type='checkbox' id='sharableChkid' name='sharableChk' value='0' onclick='sharableChkBox();'></tr>");
	              sBuffer.append("</table>");*/   
	              
	                sBuffer.append("<div class='row rowFlex reFlex'>");
					sBuffer.append("<div class='col-sm-3' align='right'>");
					sBuffer.append("<label>Discharge Cancellation Date/Time</label>");
					sBuffer.append("</div>");
					sBuffer.append("<div class='col-sm-3'><font color='blue'>");
		            sBuffer.append(now());
					sBuffer.append("</font></div>");
					sBuffer.append("<div class='col-sm-3' align='right'>");
					sBuffer.append("</div>");
					sBuffer.append("<div class='col-sm-3'>");
					sBuffer.append("</div>");
					sBuffer.append("</div>");
					
					   sBuffer.append("<div class='row rowFlex reFlex'>");
						sBuffer.append("<div class='col-sm-3' align='right'>");
						sBuffer.append("<label>Room Type</label>");
						sBuffer.append("</div>");
						sBuffer.append("<div class='col-sm-3'><font color='blue'>");
			            sBuffer.append("<select  name='strRoomType' class='form-control' dir='' title='' onChange='funroom_disCancel();'>"+vo.getStrRoomType()+"</select>");
						sBuffer.append("</font></div>");
						sBuffer.append("<div class='col-sm-3' align='right'>");
						sBuffer.append("<label>Room</label>");
						sBuffer.append("</div>");
						sBuffer.append("<div class='col-sm-3'><div id ='roomId'>");
			            sBuffer.append("<select name='strRoom' class='form-control'  dir='' title='' onChange='sharableChkBox();'>"+vo.getStrRoom()+"</select>");
						sBuffer.append("</div></div>");
						sBuffer.append("</div>");
						
						 sBuffer.append("<div class='row rowFlex reFlex'>");
							sBuffer.append("<div class='col-sm-3' align='right'>");
							sBuffer.append("<label>Bed Type</label>");
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3'><font color='blue'>");
				            sBuffer.append("<select style='cursor:pointer;' name='strBedType' class='form-control' dir='' title='' onChange='funbed();'>"+vo.getStrBedType()+"</select>");
							sBuffer.append("</font></div>");
							sBuffer.append("<div class='col-sm-3' align='right'>");
							sBuffer.append("<label><font color='red'>*</font>Bed</label>");
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3'><div id ='bedId'>");
				            sBuffer.append("<select name='strBed' class='form-control' dir='' title='Click to view Bed Status' onChange=''>"+vo.getStrBed()+"</select>");
							sBuffer.append("</div></div>");
							sBuffer.append("</div>");
							
							 sBuffer.append("<div class='row rowFlex reFlex'>");
								sBuffer.append("<div class='col-sm-3' align='right'>");
								sBuffer.append("</div>");
								sBuffer.append("<div class='col-sm-3'><font color='blue'>");
								sBuffer.append("</font></div>");
								sBuffer.append("<div class='col-sm-3' align='right'>");
								sBuffer.append("<label>Sharable Bed</label>");
								sBuffer.append("</div>");
								sBuffer.append("<div class='col-sm-3'>");
					            sBuffer.append("<input type='checkbox' id='sharableChkid' name='sharableChk' value='0' onclick='sharableChkBox();'>");
								sBuffer.append("</div>");
								sBuffer.append("</div>");
		     }
			 catch(Exception e)
			 {
				 new HisException("Discharge Cancellation Trans","DischargeCancelTransHLP.getChangeOfBed()-->",e.getMessage());
		     }
		     return sBuffer.toString();
		 }
	public static String getAdmDetails(IpdVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      WebRowSet ws = null;
	      String temp[]=null;
		  String strDis="";
	      
	      try 
	      {
				ws=vo.getGblWs1();
				if (ws!=null && ws.size()>0) 
				{
						if (ws.next()) 
						{
							String strAdmNo = ws.getString(1);
							String strAdmDateAndTime = ws.getString(2);
							String strWard = ws.getString(9);
							String strRoomOrBed =ws.getString(8);
							String tmp[]=strRoomOrBed.replace('/','#').split("#");
							String deptName=ws.getString(10);
							String unitName=ws.getString(11);
							String wardCode=ws.getString(3);
							String bedCode=ws.getString(4);
							String roomCode=ws.getString(5);
							String deptCode=ws.getString(6);
							String deptUnitCode=ws.getString(7);
							String admAdvNo=ws.getString(12);
							String consultantName=ws.getString(14);
							String strWardTypeCode=ws.getString(15); 
							String strBedTypeCode = ws.getString(16);
							String strRoomTypeCode = ws.getString(17);
							if(deptName.split("&").length>1)
								deptName=deptName.replace("&", "and");
							if(unitName.split("&").length>1)
								unitName=unitName.replace("&", "and");
							if(strWard.split("&").length>1)
								strWard=strWard.replace("&", "and");
							if(tmp[0].split("&").length>1)
								tmp[0]=tmp[0].replace("&", "and");
							String strDtUtWrRmBd=deptName+"^"+unitName+"^"+strWard+"^"+tmp[0]+"^"+tmp[1];
							String strWrdBedCode=wardCode+"^"+bedCode;
							String strDeptUntRomCode=deptCode+"^"+deptUnitCode+"^"+roomCode;
							vo.setStrDeptUnitCode(deptUnitCode);
							if(!(ws.getString(13).equals("0")))
							{
								strDis=ws.getString(13);
								temp=strDis.replace('^','#').split("#");
							}
							if(strAdmNo == null)  strAdmNo = "";
							if( strAdmDateAndTime == null) strAdmDateAndTime = "";
							if( strWard == null)  strWard = "";
							if( strRoomOrBed == null) strRoomOrBed = "";
							sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center' >");
							sBuffer.append("<input type='hidden' name='curDtUtWrRmBd' value='"+strDtUtWrRmBd+"'>");
							sBuffer.append("<input type='hidden' name='curWrdBedCode' value='"+strWrdBedCode+"'>");
							sBuffer.append("<input type='hidden' name='curDept_Unt_RomCode' value='"+strDeptUntRomCode+"'>");
							sBuffer.append("<input type='hidden' name='curAdmAdvNo' value='"+admAdvNo+"'>");
							sBuffer.append("<input type='hidden' name='curAdmNo' value='"+strAdmNo+"'>");
							sBuffer.append("<input type='hidden' name='strWardTypeCode' value='"+strWardTypeCode+"'>");
							sBuffer.append("<input type='hidden' name='strRoomTypeCode' value='"+strRoomTypeCode+"'>");
							sBuffer.append("<input type='hidden' name='strBedTypeCode' value='"+strBedTypeCode+"'>");
							sBuffer.append("<input type='hidden' name='strAdmDateAndTime' value='"+strAdmDateAndTime+"'>");
							sBuffer.append("<tr><td width='25%' class='LABEL'>Admission No.</td>");
							sBuffer.append("<td  width='25%' class='CONTROL'> ");
							sBuffer.append(strAdmNo);
							sBuffer.append("</td>");
							sBuffer.append("<td width='25%' class='LABEL'>Admission Date/Time</td>");
							sBuffer.append("<td width='25%' class='CONTROL'><font color=\"blue\">");
							sBuffer.append(strAdmDateAndTime);
							sBuffer.append("</font></td></tr>");
							sBuffer.append("<tr><td width='25%' class='LABEL'>Ward</td>");
							sBuffer.append("<td width='25%' class='CONTROL'>");
							sBuffer.append(strWard);
							sBuffer.append("</td>");
							sBuffer.append("<td width='25%' class='LABEL'>Room/Bed</td>");
							sBuffer.append("<td width='25%' class='CONTROL'>");
							sBuffer.append(strRoomOrBed);
							sBuffer.append("</td></tr>");
							sBuffer.append("<tr><td width='25%' class='LABEL'>Department Name</td>");
							sBuffer.append("<td width='25%' class='CONTROL'>");
							sBuffer.append(deptName);
							sBuffer.append("</td>");
							sBuffer.append("<td width='25%' class='LABEL'>Unit Name</td>");
							sBuffer.append("<td width='25%' class='CONTROL'>");
							sBuffer.append(unitName);
							sBuffer.append("</td></tr>");
							sBuffer.append("<tr><td width='25%' class='LABEL'>Consultant Name</td>");
							sBuffer.append("<td width='75%' colspan='3' class='CONTROL'>");
							sBuffer.append(consultantName);
							sBuffer.append("</td></tr>");
							if(temp!=null)
							{
								sBuffer.append("<tr><td width='25%' class='LABEL'>Discharge By</td>");
								sBuffer.append("<td width='25%' class='CONTROL'>");
								sBuffer.append(temp[0]);
								sBuffer.append("</td>");
								sBuffer.append("<td width='25%' class='LABEL'>Discharge Date</td>");
								sBuffer.append("<td width='25%' class='CONTROL'>");
								sBuffer.append(temp[1]);
								sBuffer.append("</td></tr>");
							}
							sBuffer.append("</table>");
						}
				}
			} 
	      catch (Exception e) 
	      {
				
				vo.setStrMsgString("Error while getting Admission detail");
				new HisException("Discharge Cancel"," getAdmDetails-->",e.getMessage());
		  }
		  return sBuffer.toString();
		 }
	
	public static String getAdmDetails_BS(IpdVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer("");
	      WebRowSet ws = null;
	      String temp[]=null;
		  String strDis="";
	      
	      try 
	      {
				ws=vo.getGblWs1();
				if (ws!=null && ws.size()>0) 
				{
						if (ws.next()) 
						{
							String strAdmNo = ws.getString(1);
							String strAdmDateAndTime = ws.getString(2);
							String strWard = ws.getString(9);
							String strRoomOrBed =ws.getString(8);
							String tmp[]=strRoomOrBed.replace('/','#').split("#");
							String deptName=ws.getString(10);
							String unitName=ws.getString(11);
							String wardCode=ws.getString(3);
							String bedCode=ws.getString(4);
							String roomCode=ws.getString(5);
							String deptCode=ws.getString(6);
							String deptUnitCode=ws.getString(7);
							String admAdvNo=ws.getString(12);
							String consultantName=ws.getString(14);
							String strWardTypeCode=ws.getString(15); 
							String strBedTypeCode = ws.getString(16);
							String strRoomTypeCode = ws.getString(17);
							if(deptName.split("&").length>1)
								deptName=deptName.replace("&", "and");
							if(unitName.split("&").length>1)
								unitName=unitName.replace("&", "and");
							if(strWard.split("&").length>1)
								strWard=strWard.replace("&", "and");
							if(tmp[0].split("&").length>1)
								tmp[0]=tmp[0].replace("&", "and");
							String strDtUtWrRmBd=deptName+"^"+unitName+"^"+strWard+"^"+tmp[0]+"^"+tmp[1];
							String strWrdBedCode=wardCode+"^"+bedCode;
							String strDeptUntRomCode=deptCode+"^"+deptUnitCode+"^"+roomCode;
							vo.setStrDeptUnitCode(deptUnitCode);
							if(!(ws.getString(13).equals("0")))
							{
								strDis=ws.getString(13);
								temp=strDis.replace('^','#').split("#");
							}
							if(strAdmNo == null)  strAdmNo = "";
							if( strAdmDateAndTime == null) strAdmDateAndTime = "";
							if( strWard == null)  strWard = "";
							if( strRoomOrBed == null) strRoomOrBed = "";
							
							sBuffer.append("<input type='hidden' name='curDtUtWrRmBd' value='"+strDtUtWrRmBd+"'>");
							sBuffer.append("<input type='hidden' name='curWrdBedCode' value='"+strWrdBedCode+"'>");
							sBuffer.append("<input type='hidden' name='curDept_Unt_RomCode' value='"+strDeptUntRomCode+"'>");
							sBuffer.append("<input type='hidden' name='curAdmAdvNo' value='"+admAdvNo+"'>");
							sBuffer.append("<input type='hidden' name='curAdmNo' value='"+strAdmNo+"'>");
							sBuffer.append("<input type='hidden' name='strWardTypeCode' value='"+strWardTypeCode+"'>");
							sBuffer.append("<input type='hidden' name='strRoomTypeCode' value='"+strRoomTypeCode+"'>");
							sBuffer.append("<input type='hidden' name='strBedTypeCode' value='"+strBedTypeCode+"'>");
							sBuffer.append("<input type='hidden' name='strAdmDateAndTime' value='"+strAdmDateAndTime+"'>");
							sBuffer.append("<p class='subHeaders'><i class='fas fa-university' style='font-size: 26px;'>&nbsp;</i>Admission Details</p>");
							sBuffer.append("<div class='row rowFlex reFlex'>");
							sBuffer.append("<div class='col-sm-3' align='right'>");
							sBuffer.append("<label>Admission No.</label>");
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3'>");
							sBuffer.append(strAdmNo);
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3' align='right'>");
							sBuffer.append("<label>Admission Date/Time</label>");
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3'><font color='blue'>");
							sBuffer.append(strAdmDateAndTime);
							sBuffer.append("</font></div>");
							sBuffer.append("</div>");
							
							
							sBuffer.append("<div class='row rowFlex reFlex'>");
							sBuffer.append("<div class='col-sm-3' align='right'>");
							sBuffer.append("<label>Ward</label>");
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3'>");
							sBuffer.append(strWard);
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3' align='right'>");
							sBuffer.append("<label>Room/Bed</label>");
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3'>");
							sBuffer.append(strRoomOrBed);
							sBuffer.append("</div>");
							sBuffer.append("</div>");
							
							sBuffer.append("<div class='row rowFlex reFlex'>");
							sBuffer.append("<div class='col-sm-3' align='right'>");
							sBuffer.append("<label>Department Name</label>");
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3'>");
							sBuffer.append(deptName);
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3' align='right'>");
							sBuffer.append("<label>Unit Name</label>");
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3'>");
							sBuffer.append(unitName);
							sBuffer.append("</div>");
							sBuffer.append("</div>");
							
							sBuffer.append("<div class='row rowFlex reFlex'>");
							sBuffer.append("<div class='col-sm-3' align='right'>");
							sBuffer.append("<label>Consultant Name</label>");
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3'>");
							sBuffer.append(consultantName);
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3' align='right'>");
							sBuffer.append("</div>");
							sBuffer.append("<div class='col-sm-3'>");
							sBuffer.append("</div>");
							sBuffer.append("</div>");
							
							
						
							if(temp!=null)
							{
								sBuffer.append("<div class='row rowFlex reFlex'>");
								sBuffer.append("<div class='col-sm-3' align='right'>");
								sBuffer.append("<label>Discharge By</label>");
								sBuffer.append("</div>");
								sBuffer.append("<div class='col-sm-3'>");
								sBuffer.append(temp[0]);
								sBuffer.append("</div>");
								sBuffer.append("<div class='col-sm-3' align='right'>");
								sBuffer.append("<label>Discharge Date</label>");
								sBuffer.append("</div>");
								sBuffer.append("<div class='col-sm-3'>");
								sBuffer.append(temp[1]);
								sBuffer.append("</div>");
								sBuffer.append("</div>");
								
							}
							
						}
				}
			} 
	      catch (Exception e) 
	      {
				
				vo.setStrMsgString("Error while getting Admission detail");
				new HisException("Discharge Cancel"," getAdmDetails-->",e.getMessage());
		  }
		  return sBuffer.toString();
		 }
	public static String getBedDetails(DischargeCancelTransVO vo)
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
		catch(Exception e)
		{
			vo.setStrMsgString("DischargeCancelTransHLP.getBedDetails() -->"+ e.getMessage());
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
	 */
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
		
	}

}
