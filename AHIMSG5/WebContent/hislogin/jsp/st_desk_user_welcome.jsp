<%@ taglib uri="/struts-tags" prefix="s" %>

<script>	
	var st1;
	var secs;
	var timerID = null;
	var timerRunning = false;
	var delay = 1000;
	var currentDate=null;
	var dateAsString=null;
	var request;
	
	function setTime()
	{
		return;
	
	
		
	var form=document.forms[0];	
	currentDate=convertStrToDate(form.dateAsString2.value,form.dateAsString2.value);
	dateAsString=form.dateAsString2.value;
	InitializeTimer();
	
	}
	
	function convertStrToDate(_date,_dateAsString)
	{
		var fT=["dd","MM","yyyy","hh","mm","ss"];	// formatTokens
		var value= new Array(6);
		// Date  As String is in Date Format dd/mm/yyyy hh:mm
		var _fDAS="dd/MM/yyyy hh:mm";
		for(var i=0;i<fT.length;i++)
		{
			var beg=_fDAS.indexOf(fT[i]);
			if(beg!=-1)
			{
				var len=fT[i].length;
				value[i]=parseInt(trimNum(_dateAsString.substr(beg,len)));
				
			}
		}
		var beg=_date.indexOf(":",_date.indexOf(":")+1)+1;
		value[5]=parseInt(trimNum(_date.substr(beg,fT[5].length)));
		
		
		
		return new Date(value[2],value[1]-1,value[0],value[3],value[4],value[5]);	
	}
	
	function trimNum(n)
	{
		while(n.substr(0,1)=='0')	n=n.substr(1);
		return n;
	}
	
	
	function InitializeTimer()
	{
	
	    // Set the length of the timer, in seconds
	    secs = 60;
	    StopTheClock();
	    StartTheTimer();
	}
	
	function StopTheClock()
	{	
	
	    if(timerRunning)
	        clearTimeout(timerID);
	    timerRunning = false
	}
	
	function StartTheTimer()
	{
	
	//alert("StartTheTimer---"+dateAsString+"--sec---"+secs+"--currentDate---"+currentDate);
	
		if (secs==0)
		{
			StopTheClock();
			var newYear=dateAsString.substring(dateAsString.lastIndexOf('-')+1,dateAsString.lastIndexOf(' '));
			currentDate.setSeconds(currentDate.getSeconds()+60);
			var newMin=currentDate.getMinutes();
			if(currentDate.getMinutes()>=0 && currentDate.getMinutes()<=9)
				newMin="0"+newMin;
			var newMonth=currentDate.getMonth()+1;
			var newDay=currentDate.getDate();
			var newHour=currentDate.getHours(); 
			var newWeekDayCode=currentDate.getDay();
			var newWeekDay=getNextDay(newWeekDayCode);
					
			var newMonthFormat=getNextMonth(newMonth-1)
		
		if(newDay<=9)
				newDay="0"+newDay;	
		
		if(newMonth<=9)
				newMonth="0"+newMonth;
		
			var newDate=newDay+"-"+newMonth+"-"+newYear+" "+newHour+":"+newMin;
			
			var newDateFormat=newWeekDay+","+newDay+"-"+newMonthFormat+"-"+newYear+" "+newHour+":"+newMin;
			
			var tdDate=document.getElementById("dateTdId");
	     
	     
	     
	   // alert("newDate---------"+newDate);
	   // alert("newDateFormat------"+newDateFormat);
			
		tdDate.innerHTML="<font style=\"left: 60%;top:0;\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"><b>"+newDateFormat+"</b></font>"
	      	document.forms[0].dateAsString2.value=newDate;
	      	
	      	InitializeTimer();
	     //	sendData();
	    }
	    else
	    {
	        self.status = secs;
	        secs = secs - 1;
	      	timerRunning = true;
	        timerID = self.setTimeout("StartTheTimer()", delay);
	    
	    }
	}
		
		var lastKeyDown;
		function changeFrameSize()
		{	
		//alert("in menu hide");
				if(parent.document.getElementById("fs2").cols == "230,*")
				{
					parent.document.getElementById("fs2").cols = "0,*";
					document.getElementById("Image").src="../hisglobal/images/arrsingle-right.png"
				}
				else
				{
					parent.document.getElementById("fs2").cols = "230,*";
					document.getElementById("Image").src="../hisglobal/images/arrsingle-left.png"
				}
				  
		}
		
		function getNextMonth(monthId)
		{
		var monthArray=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];	
		return monthArray[monthId];
		}
		
		function getNextDay(dayId)
		{
		var daysArray=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];	
		return daysArray[dayId];
		}
		
	}
	function showTitle(these)
	{
	these.title="Log Out"
	}
	function showMenuTitle(these)
	{
	//alert("hi......."+parent.document.getElementById("fs2").cols);
	if(parent.document.getElementById("fs2").cols == "230,*")
		these.title="Hide Menu"
		else
	if(parent.document.getElementById("fs2").cols == "0,*")
		these.title="Show Menu"
	
	}

	 //onload="setTime();"
</script>

<%
// 	String UserFullName="";
// 	String systemDate1="";
// 	String systemDate2="";
// 	String currentDay=""; 
// 	Date currentDate=new Date();
// 	String []WeekDays={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	
// try{
		
// 	if(session!=null)	
//     	  {	
// 	 UserFullName="Welcome, "+session.getAttribute("UserFullName");
	
// 	 //This calling of function is closed inorder to increase the performance 
// 	 //which is called from the login query 
	 
// 	// ControllerUTIL.setSysdate(request);
// 	 //systemDate1=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT_LOGIN), "dd-MMM-yyyy HH:mm");
// 	 //systemDate2=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT_LOGIN), "dd-MM-yyyy HH:mm");
// 	 //currentDate=(Date)WebUTIL.getSession(request).getAttribute(Config.SYSDATEOBJECT_LOGIN);
//      //currentDay=WeekDays[currentDate.getDay()];
	
// 	  //System.out.println("dayCode-----"+currentDate.getDay()+"-----Day----"+currentDay);
// 	      }
// 	else
// 		UserFullName="";
	  
// 	}
// catch(Exception e)
// 	{
// 	UserFullName="";
// 	e.printStackTrace();
// 	}
	
	
	%>

<div>
	<table cellpadding="0" cellspacing="0" border="0" width="100%" style="background-image: '/HIS/hisglobal/images/strip20.png'; background-repeat: repeat-x;"> 
		<tr>
			<td width="30%">
			<div align="left" ><font color="#000000" style="left:3%;top:0%;" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>&nbsp;Welcome, <s:property value="varUserName"/></b></font></div>
			</td>	
				<td width="65%">
				<div align="left" id="dateTdId"><font color="#000000" style="left:60%;top:0%;" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b></b></font></div>
			</td>
			<td width="4%" >		
			<img src="/HIS/hisglobal/images/Logout.png"  onclick="callLogOut()" onmouseover="showTitle(this)" style="right:0%;top:  5%;cursor: pointer;">
			</td>	
		</tr>	
	</table>
</div>
