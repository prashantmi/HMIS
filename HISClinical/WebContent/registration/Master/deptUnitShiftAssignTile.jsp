<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/time.js"/>


<script>
window.history.forward()

	function addRowData(forDay){
		//alert("Inside AddRow");
		//alert("for day="+forDay)
		document.getElementsByName("addRow")[0].value = forDay;
		submitForm("ADD_ROSTER_ROW");
	}
	
	function deleteRowData(forDay_row){
		//alert("deleteRow");
		document.getElementsByName("removeRow")[0].value = forDay_row;
		submitForm("REMOVE_ROSTER_ROW");
	}
</script>

<script language="javascript">
function getTimehrMin(strTime,hrMin)
{
	var lenstr=strTime.length;
	var hours;
	var minutes;
	if(lenstr==5)
	{
		hours=strTime.substring(0,2);
		minutes =strTime.substring(5,3);
	}
	if(lenstr==4)
	{
		hours=strTime.substring(0,1);
		minutes=strTime.substring(2,4);
	}
	//alert("length" + lenstr);
	//alert("hours="+ hours + " \n minutes="+ minutes);

	if(hrMin=="hr")
	{
		//alert("hr" + hours)
		return hours;
	}	
		
	if(hrMin=="min")
	{
	    //alert("min" + minutes)
		return minutes;		
	}	
}

function checkTimeClash(startTime1,endTime1,startTime2,endTime2)
{	
	var StartTimeHr1=parseInt(getTimehrMin(startTime1,"hr"));
	var StartTimeMin1=parseInt(getTimehrMin(startTime1,"min"));
	var StartTimeHr2=parseInt(getTimehrMin(startTime2,"hr"));
	var StartTimeMin2=parseInt(getTimehrMin(startTime2,"min"));	
	var endTimeHr1=parseInt(getTimehrMin(endTime1,"hr"));
	var endTimeMin1=parseInt(getTimehrMin(endTime1,"min"));
	var endTimeHr2=parseInt(getTimehrMin(endTime2,"hr"));
	var endTimeMin2=parseInt(getTimehrMin(endTime2,"min"));	
/*alert("StartTimeHr1 "+ StartTimeHr1 +" StartTimeMin1 " + StartTimeMin1+
	"\n StartTimeHr2 "+ StartTimeHr2 +" StartTimeMin2 " + StartTimeMin2+
	"\nendTimeHr1 "+ endTimeHr1 +" endTimeMin1 " + endTimeMin1+
	"\nendTimeHr2 "+ endTimeHr2 +" endTimeMin2 " + endTimeMin2);
	*/
	var status =0;
	if(endTimeHr1<=StartTimeHr2)
	{
		//alert("check1");
		if(endTimeHr1==StartTimeHr2)
		{
			//alert("check2");
			if(endTimeMin1<=StartTimeMin2)
			{
				//alert("check3");
				status=1;
			}
			else
			{
				//alert("check4");
				status=0;
			}
		}
		else
		{
			//alert("check5");
			status=1;
		}
	}
	else
	{
		//alert("check6");
		status=0;
	}
	
	if(status==1)	
	{
		//alert("time is not clashing")
		return true;
	}
	else
	{
		alert("Shift Times are overlapping!")
		return false;
	}	   
}
function getDiRi(obj,strIdType)
{
	var strCheckedVal=obj.value;	
	var indx=strCheckedVal.indexOf("_") 	
 	var di=strCheckedVal.substr(0,indx)
 	var ri=strCheckedVal.substr(indx+1,strCheckedVal.length-indx)
 	//alert("di "+ di + " \n ri "+ri);
 	if(strIdType=="di")
 		return di;
 	if(strIdType=="ri")
 		return ri; 	
}

function checkShiftTimeClash(obj)
{
 if(obj.checked==false)
 	return false;
 var noOfShift=document.getElementsByName("startTime").length;
 //alert("noOfShift  "+ noOfShift);
 var i;
 var j;
 var k=0;  
 var di=getDiRi(obj,"di");
 var ri=getDiRi(obj,"ri");  
 var initVal=di-1;
 var tempri=0;
 var shiftName="shift("+(noOfShift-1)+")";
 var finalVal; 
 
 for(i=initVal;i<document.getElementsByName(shiftName).length;i++)
 {
 	  var tempDi=getDiRi(document.getElementsByName(shiftName)[i],"di");
 	  //alert("tempDi  "+ tempDi +"\n di =" + di);
   	 if(parseInt(di)==parseInt(tempDi))
   	 {     	 		 	
   	 	tempri=getDiRi(document.getElementsByName(shiftName)[i],"ri");   	     	 	
   	 }
   	 else
   	 	if(tempDi>di)
   	 		break;	 	
   	
 } 
 finalVal=parseInt(di)+parseInt(tempri);
 //alert("initVal=" + initVal + "\n finalVal=" + finalVal);
 //var arr=new Array(parseInt(noOfShift)*parseInt(finalVal));
  
 for(i=initVal;i<finalVal;i++)
 {
 	for(j=0;j<noOfShift;j++)
 	{
 		shiftName="shift("+(j)+")"; 
 		if(document.getElementsByName(shiftName)[i].checked==true)
 		{ 			 
 			if((j+1)!=noOfShift)
 			{
 				for(k=(j+1);k<noOfShift;k++)
 				{
 					var tempShiftName="shift("+(k)+")";
 					if(document.getElementsByName(tempShiftName)[i].checked==true)
 					{
 						if(checkTimeClash(document.getElementsByName("startTime")[j].value,document.getElementsByName("endTime")[j].value,document.getElementsByName("startTime")[k].value,document.getElementsByName("endTime")[k].value)==false)
 							obj.checked=false;
 					}
 				}	
 			}			
 					
 		}
 	}
 }
  
}


function shiftselect(this_shift)
{	
	s_name = this_shift.name ;
	var arr_s_value = this_shift.value.split("_");
	s_value = arr_s_value[0];
	s__shift_value = arr_s_value[1];
	
	var len;
	var isValid = true;
	count=0;
	len=document.getElementsByName(s_name).length;	
	var i=0;
	var j=0;
var isValid = true ;
if ( this_shift.checked == true ) 
{	
	this_shift.checked = false ;
	var abc_array = document.getElementsByName(s_name);

	for(i=0;i<len;i++)
    { 
		var abc  = abc_array[i].value.split("_");     	
		if(abc[0]==s_value && abc_array[i].checked == true )
       	{       
         	alert ( "Only one shift can be selected for the day"); 
			isValid=false;
			break;
       	}
		
	}

	if(isValid)
	{
	this_shift.checked = true ;
	}
	}
	return;
}
</script>

<script language="javascript">
	function checkShiftWeekOfMonthConsistency(){
		lenControlArray = document.getElementsByName("week1stOfMonth").length; // length of all the chkbox arrays are gonna be same

		for(elemIdx=0; elemIdx<lenControlArray; elemIdx++){
			flagWoMChked = false; // flag will be set to tru if a checkbox in any weekOfMonth array is checked for this element of the 
			//there r 5 weeks in mOnth
			for(weekId=0; weekId<5; weekId++){ // for each week of Month
				//arrWeekOfMonth= new Array();
				switch(weekId){
					case(0):
						arrWeekOfMonth = document.getElementsByName("week1stOfMonth");
						break;
					case(1):
						arrWeekOfMonth = document.getElementsByName("week2ndOfMonth");
						break;	
					case(2):
						arrWeekOfMonth = document.getElementsByName("week3rdOfMonth");
						break;
					case(3):
						arrWeekOfMonth = document.getElementsByName("week4thOfMonth");
						break;
					case(4):
						arrWeekOfMonth = document.getElementsByName("week5thOfMonth");
						break;
				}
				if(arrWeekOfMonth[elemIdx].checked){  // checked in this row?
					flagWoMChked = true;
					break;
				}
			}
			
			flagShiftChked = false; // flag will be set to tru if a checkbox in any shift array is checked for this idx
			
			for(shiftId=0; shiftId<<%=DeptUnitRosterUTIL.getNumberOfShifts(request)%>; shiftId++){
				shiftChkName = "shift("+shiftId+")";
				arrShiftChk = document.getElementsByName(shiftChkName);
				
				if(arrShiftChk[elemIdx].checked){  // checked in this row?
					flagShiftChked = true;
					break;
				}
			}
			
			if(flagShiftChked != flagWoMChked){ // Not True-True or False-False >> a wOm selected w/o selecting shift or shift is slected w/o specifying wOm
				if(flagShiftChked)
					alert("Specify Week Of month for the selected shift");
				else
					alert("Assign a shift for the selected Week Of mOnth");
				return false;
			}
		}
		return true;
		
	}
	
function selectAllWeek(obj){
	if(obj.checked){
		var di=obj.value.split("_")[0]
		var ri=obj.value.split("_")[1]
		//alert("ri "+ri)
		var weekIndex=""
		var week1stOfMonth=document.getElementsByName("week1stOfMonth")
		for(var i=0;i<week1stOfMonth.length;i++){
			var a=week1stOfMonth[i].value.split("_")[0]
			var b=week1stOfMonth[i].value.split("_")[1]
			//alert(a==di)
			//alert("b "+ b+"b")
			///alert(b == ri)
			b=b.substring(0,1)
			if((a==di) && (b==ri)){
				//alert("equal")
				weekIndex=i;
				break;
			}
		}
		//alert(i)
		document.getElementsByName("week1stOfMonth")[i].checked=true;
		document.getElementsByName("week2ndOfMonth")[i].checked=true;
		document.getElementsByName("week3rdOfMonth")[i].checked=true;
		document.getElementsByName("week4thOfMonth")[i].checked=true;
		document.getElementsByName("week5thOfMonth")[i].checked=true;
		
	}
	else{
		var di=obj.value.split("_")[0]
		var ri=obj.value.split("_")[1]
		//alert("ri "+ri)
		var weekIndex=""
		var week1stOfMonth=document.getElementsByName("week1stOfMonth")
		for(var i=0;i<week1stOfMonth.length;i++){
			var a=week1stOfMonth[i].value.split("_")[0]
			var b=week1stOfMonth[i].value.split("_")[1]
			//alert(a==di)
			//alert("b "+ b+"b")
			///alert(b == ri)
			b=b.substring(0,1)
			if((a==di) && (b==ri)){
				//alert("equal")
				weekIndex=i;
				break;
			}
		}
		//alert(i)
		document.getElementsByName("week1stOfMonth")[i].checked=false;
		document.getElementsByName("week2ndOfMonth")[i].checked=false;
		document.getElementsByName("week3rdOfMonth")[i].checked=false;
		document.getElementsByName("week4thOfMonth")[i].checked=false;
		document.getElementsByName("week5thOfMonth")[i].checked=false;
	}
}
</script>
	
<his:css src="/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/calendar.js"/>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*, registration.master.controller.util.*, hisglobal.utility.*, hisglobal.presentation.*, registration.master.controller.fb.*" %>



<his:TransactionContainer>
<his:SubTitleTag>
	<his:name>
	<bean:message key="titleRosterDetails"/>
	</his:name>	
	<table width="100%">
	  <tr>
	  	<td width="50%">
	  	</td>
		<td width="20%">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b>
			  <bean:message key="effectiveDate"/>
			</b>
			</font>
			</div>
		</td>
		<td width="30%">
			<div align="left">
			<logic:empty name="DeptUnitRosterFB" property="effectDate">
				<bean:define name="DeptUnitRosterFB" property="effectDate" id="effectiveDate" type="java.lang.String"/>
				<%
					effectiveDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				%>				
				
				<his:date name='<%="effectDate"%>' dateFormate="%d-%b-%Y" value='<%=effectiveDate%>'/>
			</logic:empty>			
			
			
			<logic:notEmpty name="DeptUnitRosterFB" property="effectDate">
				<bean:define name="DeptUnitRosterFB" property="effectDate" id="effectiveDate" type="java.lang.String"/>
 	      		<his:date name='<%="effectDate"%>' dateFormate="%d-%b-%Y" value='<%=effectiveDate%>'/>
       		</logic:notEmpty>
			</div>
		</td>
	  </tr>
	</table>
</his:SubTitleTag>

<his:ContentTag>
	<% int noOfShifts = DeptUnitRosterUTIL.getNumberOfShifts(request);%>
<table width="100%" cellspacing="1" cellpading="1">
		<tr>
		<td colspan="1" width="17%" Class="tdfonthead">
		<b>
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<div align="center">
		<bean:message key="daysOfWeek"/>
		</div>
		</font>
		</b>
		</td>
		
		<td colspan="5" Class="tdfonthead">
		<b>
		<div align="center">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="weekOfMonth"/>
		</font>
		</div>
		</b>
		</td>
		<td colspan="<%=noOfShifts%>" Class="tdfonthead">
		<b>
		<center>
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="shift"/>
		</font>
		</center>
		</b>
		</td>
		</tr>
	
	<tr>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">&nbsp;</td>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;"><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">1st <bean:message key="week"/></font></div></td>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;"><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">2nd <bean:message key="week"/></font></div></td>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;"><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">3rd <bean:message key="week"/></font></div></td>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;"><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">4th <bean:message key="week"/></font></div></td>
	  <td class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;"><div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">5th <bean:message key="week"/></font></div></td>
	  
	  
	   	<logic:iterate id="list" name="<%=RegistrationConfig.ESSENTIALBO_OPTION_SHIFT%>" type="hisglobal.vo.ShiftMasterVO">	
		  	<td Class="tdfont" style="border-top:outset black 2px; border-bottom:inset black 2px;">
		  		<div align="center">
		  		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name='list' property='shiftDescription'/></font></div>
		  		<input type="hidden" name="startTime" value="<bean:write name='list' property='shiftStartTime'/>">
		  		<input type="hidden" name="endTime" value="<bean:write name='list' property='shiftEndTime'/>">
		  	</td>
	  	</logic:iterate>
	  
	  
    </tr>
	<bean:define name="DeptUnitRosterFB" id="currForm" type="DeptUnitRosterFB"/>
	<%for(int di=1; di<= 7; di++){ 
		//for each Day of Week	
		int noOfRows = DeptUnitRosterUTIL.getNumOfRows4aDOfWinJSP(currForm, request, di);

		for(int ri=0; ri<noOfRows; ri++){
			//for each row in a Day
		
	%>
	<tr>
		<td class="tdfont">
			<table width="100%">
				<tr>
					<td>
					<div align="left">
						<%if(ri==0){  String tmpRowDay = "dOw"+di; %>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="<%=tmpRowDay%>"/>
							</font>
						<%}%>
					</div>
					</td>
					<td Class="tdfont" width="20%">
				    	<div align="left">
				    		<input type="checkbox" name="selectAll<%=di%>" value="<%=di+"_"+ri%>" onclick="selectAllWeek(this)">
				    	</div>
			   		 </td>	
				</tr>
			</table>
		</td>
		
	    <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		<his:checkbox onClick="shiftselect(this)" name="DeptUnitRosterFB" property="week1stOfMonth" value='<%=di+"_"+ri%>'/>
      		</font></div>
        </td>
        <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		<his:checkbox onClick="shiftselect(this)" name="DeptUnitRosterFB" property="week2ndOfMonth" value='<%=di+"_"+ri%>'/>
      		</font></div>
        </td>
        <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		<his:checkbox onClick="shiftselect(this)" name="DeptUnitRosterFB" property="week3rdOfMonth" value='<%=di+"_"+ri%>'/>
      		</font></div>
        </td>
	    <td Class="tdfont">
	    	<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		<his:checkbox onClick="shiftselect(this)" name="DeptUnitRosterFB" property="week4thOfMonth" value='<%=di+"_"+ri%>'/>
      		</font></div>
        </td>
	    <td Class="tdfont">
	    	<div align="center">
	    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	      		<his:checkbox onClick="shiftselect(this)" name="DeptUnitRosterFB" property="week5thOfMonth" value='<%=di+"_"+ri%>'/>
      		</font>
      		</div>
        </td>
		<%for(int si=0; si<noOfShifts; si++){ %>
	    <td Class="tdfont" >
	      <div align="center">
	      <his:checkbox name="DeptUnitRosterFB" onClick="checkShiftTimeClash(this);"  property='<%="shift("+si+")"%>' value='<%=di+"_"+ri%>'  />
          </div>
        </td>
        <%}%>
		
		<td border="0">
			<div align="center">
				<img class="button" src='<his:path src="/hisglobal/images/Minus.png"/>' tabindex=1 border="0" style="cursor:pointer" onClick='deleteRowData("<%=di+"_"+ri%>")' onkeypress='if(event.keyCode==13) deleteRowData("<%=di+"_"+ri%>")'>
			</div>
		</td>
        <td border="0">
			<div align="center">
				<%if(ri==0){%>
					<img class="button" src='<his:path src="/hisglobal/images/plus.png"/>' tabindex=1 border="0" style="cursor:pointer" onClick='addRowData("<%=di%>");' onkeypress="if(event.keyCode==13)addRowData('<%=di%>');">
				<%} %>
			</div>
		</td>
		
	</tr>
			
	<%} } %>    

</table>
</his:ContentTag>
</his:TransactionContainer>
<input type="hidden" name="addRow"/>
<input type="hidden" name="removeRow"/>
		


