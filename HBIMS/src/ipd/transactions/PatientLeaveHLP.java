package ipd.transactions;
import javax.sql.rowset.WebRowSet;

import hisglobal.utility.HisUtil;
import hisglobal.exceptions.HisException;
public class PatientLeaveHLP{

	public static  String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
	public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy";
    
	public static  String now(String frmt)
    {
    	HisUtil util=null;
    	String a="";
    	util=new HisUtil("transaction","PatientLeaveHLP");
    	try{
    	 a= util.getASDate(frmt);
    	}
    	catch(Exception e){
    		new HisException("Patient Leave Trans","PatientLeaveTransHLP.now()-->",e.getMessage());
    	}
    	/*Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());*/
    	return a;
    }

	 public static String jd(PatientLeaveVO vo)
	 {
	    vo.setStrCtDate(now(DATE_FORMAT_NOW));
		StringBuffer sBuffer = new StringBuffer("");
	      try{
	    	  
	              sBuffer.append("<table width='85%' border='0' cellspacing='1px' cellpadding='0px' align='center'>");
	              sBuffer.append("<tr><td width='25%' class='LABEL' colspan='1'><div align='right'>Joining Date</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' colspan='1' ><div id='joinDatePic'>");
	              sBuffer.append("<font color='blue'>"+now(DATE_FORMAT_NOW)+"</font>");
	              sBuffer.append("</div></td>");
	              sBuffer.append("<td width='25%' class='LABEL' colspan='1'><font color='red'>*</font>Joining Time</td>");
	              sBuffer.append("<td class='CONTROL' width='25%' colspan='2'>");
	              sBuffer.append("<input type='text' class='txtFldSmall' name='strShiftHour_J' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);'><b>:</b>");
	              sBuffer.append("<input type='text' class='txtFldSmall' name='strShiftMin_J' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);'><b>:</b>");
	              sBuffer.append("<input type='text' class='txtFldSmall' name='strShiftSec_J' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event);'>");
	              sBuffer.append("<select class='comboSmall' name='strShiftAmPm_J'><option value='1'>AM</option><option value='2'>PM</option></select></td></tr>");
	              
	              sBuffer.append("<tr><td width='25%' class='LABEL' colspan='1' ><div align='right'><font color='red'>*</font>Patient Condition at the time of Joining</div></td>");
	              sBuffer.append("<td width='75%' colspan='4' class='CONTROL' >");
	              sBuffer.append("<textarea name='strPatCondJ' cols='20' rows=''></textarea>");
	              sBuffer.append("</td>");
	              sBuffer.append("</tr>");
	          // vo.setStrIsBedVacant("0");// just for checking..delete
	           if(vo.getStrIsBedVacant().equals("1"))
	           {   
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'><font size='2' color='red'>*</font>Room Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;cursor:hand' name='strRoomType' class='comboNormal' dir='' title='' onChange='funroom_JoinEntry();'>"+vo.getStrRoomType()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td width='25%' class='LABEL' ><div align='right'><font size='2' color='red'>*</font>Room</div></td>");
	              sBuffer.append("<td colspan='2 'width='25%' class='CONTROL' ><div id ='roomId'>");
	              sBuffer.append("<select name='strRoom' class='comboNormal'  dir='' title='' onChange=''>"+vo.getStrRoom()+"</select>");
	              sBuffer.append("</div></td></tr>");  
	              sBuffer.append("<tr><td width='25%' class='LABEL'><div align='right'><font size='2' color='red'>*</font>Bed Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;cursor:hand' name='strBedType' class='comboNormal' dir='' title='' onChange='funbed();'>"+vo.getStrBedType()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td  width='25%' class='LABEL' ><div align='right'><font size='2' color='red'>*</font>Bed</div></td>");
	              sBuffer.append("<td  width='15%'  class='CONTROL' ><div id ='bedId'>");
	              sBuffer.append("<select name='strBed' class='comboNormal' dir='' title='Click to view Bed Status' onChange=''>"+vo.getStrBed()+"</select>");
	              sBuffer.append("</div></td><td align='right' width='10%' class='CONTROL' ><img style='cursor:pointer;cursor:hand' title='Click to view Bed Status' src='../../hisglobal/images/Bed_.gif'  onClick ='openPopup("+"\"BEDSTATUS\""+");'></td></tr>");
	           }
	             /* sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'>Ward Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' ><div id ='wardTypeId'>");
	              sBuffer.append("<select style='cursor:pointer;cursor:hand' name='strWardType' class='comboNormal' dir='' title='' onChange='funward();'>"+vo.getStrwardType()+"</select>");
	              sBuffer.append("</div></td>");
	              sBuffer.append("<td width='25%' class='LABEL' ><div align='right'>Ward</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' ><div id ='wardId'>");
	              sBuffer.append("<select name='strWard' class='comboNormal' dir='' title='' onChange=''>"+vo.getStrWard()+"</select>");
	              sBuffer.append("</div></td></tr>");
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'>Room Type</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append("<select style='cursor:pointer;cursor:hand' name='strRoomType' class='comboNormal' dir='' title='' onChange='funroomLJ();'>"+vo.getStrRoomType()+"</select>");
	              sBuffer.append("</td>");
	              sBuffer.append("<td width='25%' class='LABEL' ><div align='right'>Room/Bed</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' ><div id ='roomId'>");
	              sBuffer.append("<select name='strRoom' class='comboNormal' dir='' title='' onChange=''>"+vo.getStrRoom()+"</select>");
	              sBuffer.append("</div></td></tr>");*/
	              sBuffer.append("</table>");
		     }
			 catch(Exception e)
			 {
				 new HisException("Patient Leave","PatientLeaveJoinRecordTransHLP.jd()-->",e.getMessage());
		     }
		     return sBuffer.toString();
	  }
	 public static String getBedDetails(PatientLeaveVO vo)
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
				vo.setStrErrMsgString("PatientLeaveHLP.getBedDetails() -->"+ e.getMessage());
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
