<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<LINK REL="SHORTCUT ICON" HREF="../hisglobal/images/hisico.ico">
<title>HIS</title>
<link href="../hisglobal/css/ddsmoothmenu.css" rel="stylesheet" type="text/css">
<link href="../hisglobal/css/loginLayout.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../hisglobal/css/image-slideshow.css" type="text/css">
<link href="../hisglobal/css/hisStyle.css" rel="stylesheet" type="text/css">
	<link href="../hisglobal/css/Color.css" rel="stylesheet" type="text/css">
	<link href="../hisglobal/css/master.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../hisglobal/js/jquery.js"></script>
<script type="text/javascript" src="../hisglobal/js/image-slideshow.js"></script>
<script type="text/javascript" src="../hisglobal/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="../hisglobal/js/utilityFunctions.js"></script>
<style type="text/css">
.header{
	/*height: 55px;*/
	background: #1277b5; /* Old browsers */
	background: -moz-linear-gradient(top,  #135d8c 0%, #1277b5 0%, #1277b5 32%, #135d8c 100%); /* FF3.6+ */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#135d8c), color-stop(0%,#1277b5), color-stop(32%,#1277b5), color-stop(100%,#135d8c)); /* Chrome,Safari4+ */
	background: -webkit-linear-gradient(top,  #135d8c 0%,#1277b5 0%,#1277b5 32%,#135d8c 100%); /* Chrome10+,Safari5.1+ */
	background: -o-linear-gradient(top,  #135d8c 0%,#1277b5 0%,#1277b5 32%,#135d8c 100%); /* Opera 11.10+ */
	background: -ms-linear-gradient(top,  #135d8c 0%,#1277b5 0%,#1277b5 32%,#135d8c 100%); /* IE10+ */
	background: linear-gradient(to bottom,  #135d8c 0%,#1277b5 0%,#1277b5 32%,#135d8c 100%); /* W3C */
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1277b5', endColorstr='#135d8c',GradientType=0 ); /* IE6-9 */
	border: 0;
	width: 100%;
	color: white;
}
body{
	margin: 0;
	width :100%;
	/*overflow: hidden;*/
}
table{
	border: 0;
	}
	form{
	padding: 0px;
	}
.marquee{
	height: 20px;
}	
</style>
<script>
history.forward();




var lastKeyDown;
	function changeFrameSize(e)
	{
	var key = e.keyCode;
	//alert("main page"+key);
	if(window.event && window.event.keyCode == 17)  
	 lastKeyDown = 'Ctrl';
	  if(window.event && window.event.keyCode == 49)
	  {
		if(lastKeyDown == 'Ctrl')
		{
			if(document.getElementById("fs2").cols == "150,*")
			{
				parent.document.getElementById("fs2").cols = "0,*";
			}
			else
			{
				parent.document.getElementById("fs2").cols = "150,*";
			}
		}
	  }
	  else{
	  	lastKeyDown = 'not';
	  	}
	}


function callLogOut(e)
{
	document.form1.hmode.value="LOG_OUT";
	document.form1.action="../startup/loginAction";
	alert('logging out');
	document.form1.submit();

} 


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
/*var form=document.forms[0];	
currentDate=convertStrToDate(form.dateAsString2.value,form.dateAsString2.value);
dateAsString=form.dateAsString2.value;*/

	var dateAsString = document.getElementsByName("dateAsString2")[0].value;
	currentDate = convertStrToDate(dateAsString,'dd-MM-yyyy hh:mm'); // for format dd-MM-yyyy HH:mm  of 'dateAsString2'
	var tdDate=document.getElementById("dateTdId");
 	var newDateFormat = convertDateToStr(currentDate, "Week, dd-Mon-yyyy hh:mm");
	tdDate.innerHTML=newDateFormat;

InitializeTimer();

}

/*function convertStrToDate(_date,_dateAsString)
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
}*/

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
		var tdDate=document.getElementById("dateTdId");
		var objnewDate = addToDate(currentDate,1,"MI");
     	var newDateFormat = convertDateToStr(objnewDate, "Week, dd-Mon-yyyy hh:mm");
     	var newDate = convertDateToStr(objnewDate, "dd-MM-yyyy hh:mm");
     	currentDate = objnewDate;
     	
	   // alert("newDate---------"+newDate);
		tdDate.innerHTML=newDateFormat;
		document.getElementsByName("dateAsString2")[0].value=newDate;

		/*var newYear=dateAsString.substring(dateAsString.lastIndexOf('-')+1,dateAsString.lastIndexOf(' '));
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
		
	tdDate.innerHTML="<b>"+newDateFormat+"</b>";
      	document.forms[0].dateAsString2.value=newDate;*/
      	
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
	
	function callLogOut(){
	document.form1.hmode.value="LOG_OUT";
	//document.form1.action="../startup/loginAction";
	document.form1.action="loginAction";
	document.form1.submit();
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
function callMenu(url)
{
	//alert('menu called with url: '+ url);
	var targetURL = url;
	
	
	var elemFrame = document.getElementById("frmMain");
	elemFrame.src=targetURL;
	//elemFrame.refresh();
}



</script>
<script>
ddsmoothmenu.init({
		mainmenuid: "smoothmenu", //menu DIV id
		orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
		classname: 'ddsmoothmenu', //class added to menu's outer DIV
		//customtheme: ["#1c5a80", "#18374a"],
		contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
	}) 

</script>
<script type="text/javascript">
$(function() {
		$(document).ready(function() {
			var ht = Math.round($(window).height() - $(".header").outerHeight()) -$(".marquee").outerHeight()+ "px";
			var wt = Math.round($(window).width()) + "px";
			$('.body').height(ht);
			var ht2 = Math.round($(window).height() - $(".header").outerHeight()-$(".marquee").outerHeight()-$("#menuStrip").outerHeight()) + "px";
			$('.bodyContent').height(ht2);
			$('.bodyContent').css({'margin-top':$("#menuStrip").outerHeight()});
			$('#arrow_left').mouseout(adjustMenuAlignment);
			$('#arrow_right').mouseout(adjustMenuAlignment);
			$('#menu a').click(function() {
				if($(this).attr('class')=='plusParentNode'){
					$(this).removeClass('plusParentNode');
					$(this).addClass('minusParentNode');
				}else if($(this).attr('class')=='minusParentNode'){
					$(this).removeClass('minusParentNode');
					$(this).addClass('plusParentNode');
				}
			    $(this).next('ul').slideToggle();
			});
			checkIfSlideBarRequired();
			initSlideShow();
			$("#smoothmenu a").click(function(){
				$(this).trigger("mouseout");
			});
		});
		
	});

	function checkIfSlideBarRequired(){
		var menuContainerWidth = $("#menuContainer").width();
		var menuWidth=0;
		$("#menuStrip #smoothmenu ul#menuList")
		.children("li")
		.each(
				function(index) {
					currentMenuWidth = $(this).outerWidth(true);
					menuWidth += currentMenuWidth;

				});
		if(menuContainerWidth > menuWidth){
			$('#arrow_left').css({'display':'none'});
			$('#arrow_right').css({'display':'none'});
			$('#menuStrip').css({'left':'0'});
		}
		
	}
	function adjustMenuAlignment() {
		var listColumnWidth = 0;
		var menuContainerWidth = $("#menuContainer").width();
		var leftOffset = Math.abs($("#menuStrip").offset().left);
		/*Find leftmost menu item */
		var cummulativeLeftMenuWidth = 0;
		var currentMenuWidth = 0;
		var currentWindowMenuWidth = 0;
		$("#menuStrip ul#menu")
				.children("li")
				.each(
						function(index) {
							currentMenuWidth = $(this).outerWidth(true);
							cummulativeLeftMenuWidth += currentMenuWidth;
							if (cummulativeLeftMenuWidth > leftOffset
									&& currentWindowMenuWidth < menuContainerWidth) {
								currentWindowMenuWidth += currentMenuWidth;
								listColumnWidth = $($(this).children('div'))
										.outerWidth(true);
								if (currentWindowMenuWidth + listColumnWidth > menuContainerWidth) {
									//alert('class added to '+ $(this).children("a").text());
									$(this).children('div').addClass(
											"align_right");
								} else {
									//alert('class removed from '+ $(this).children("a").text());
									$(this).children('div').removeClass(
											"align_right");
								}

							}

						});

	}
</script>
	
</head>
<body  onbeforeunload="callLogOut(event)" onload="setTime()";>
<form name="form1" method="post" action="startup/loginAction">
<jsp:include page="/startup/header.jsp"></jsp:include>
<div class="body">
<jsp:include page="/startup/horizontalMenu.jsp"></jsp:include>
<div class="bodyContent">
<iframe id="frmMain" src="/AHIMS/startup/initPage.jsp" frameborder="0" height="100%" width="100%"></iframe>
</div>
</div>
<div class="marquee">
<jsp:include page="/startup/bottomBuleteinMarque.jsp"></jsp:include>
</div>








<noframes>
<body  onbeforeunload="callLogOut(event)" onload="setTime()";>
<form name="form1" method="post" action="startup/loginAction">

<input type="hidden" name="hmode" value=""/>
<input type=hidden name="seatId" value='<%=request.getParameter("seatId")%>' />
<input type=hidden name="empId" value='<%=request.getParameter("empId")%>' />
<input type=hidden name="roleId" value='<%=request.getParameter("roleId")%>' />
<input type='hidden' name="userId" value='<%=request.getParameter("userId")%>'>
<input type='hidden' name="userName" value='<%=request.getParameter("userName")%>'>
<input type='hidden' name="loginMode" value='<%=request.getParameter("loginMode")%>'>
</form>
</noframes>

</form>
</body>
</html>