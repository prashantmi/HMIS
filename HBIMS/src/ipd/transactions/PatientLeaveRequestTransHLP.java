package ipd.transactions;
import hisglobal.utility.HisUtil;
import hisglobal.exceptions.HisException;
public class PatientLeaveRequestTransHLP{

	public static  String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
	public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy";
    
	public static  String now(String frmt)
    {
    	HisUtil util=null;
    	String a="";
    	util=new HisUtil("transaction","PatientLeaveRequestTransHLP");
    	try{
    	 a= util.getASDate(frmt);
    	}
    	catch(Exception e){
    		new HisException("Patient Leave Trans","PatientLeaveRequestTransTransHLP.now()-->",e.getMessage());
    	}
    	/*Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());*/
    	return a;
    }
/*
    public static String jd(PatientLeaveRequestTransVO vo)
	 {
	    vo.setStrCtDate(now(DATE_FORMAT_NOW));
		StringBuffer sBuffer = new StringBuffer("");
	      try{
	              //sBuffer.append("<input type='hidden' name='strCtDate' value='"+vo.getStrCtDate()+"'>");
	              sBuffer.append("<table width='85%' border='0' cellspacing='0px' callpadding='0px' align='center'>");
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'>Joining Date/Time</div></td>");
	              sBuffer.append("<td width='25%' class='CONTROL' >");
	              sBuffer.append(now(DATE_FORMAT_NOWwt));
	              sBuffer.append("</td>");
	              sBuffer.append("<td width='25%' class='LABEL' ><div align='right'>Patient Condition at the time of Joining</div></td>");
	              sBuffer.append("<td width='25%' colspan='' class='CONTROL' >");
	              sBuffer.append("<textarea name='strPatCondJ' cols='20' rows=''></textarea>");
	              sBuffer.append("</td>");
	              sBuffer.append("</tr>");
	              sBuffer.append("<tr><td width='25%' class='LABEL' ><div align='right'>Ward Type</div></td>");
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
	              sBuffer.append("</div></td></tr>");
	              sBuffer.append("</table>");
		     }
			 catch(Exception e)
			 {
				 new HisException("Patient Leave","PatientLeaveRequestTransHLP.jd()-->",e.getMessage());
		     }
		     return sBuffer.toString();
	  }*/
}
