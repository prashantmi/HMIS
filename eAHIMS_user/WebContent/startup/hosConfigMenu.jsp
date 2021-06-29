
<html>
<head>

<title>C-DAC Admin Console</title>
<style type="text/css">
	.white{
		background-Color:#EAEAEA; font:  11px tahoma,verdana,sans-serif; color: #215dc6; width: 185px;
				  height: 15px;
	}
	.lightOrange{
		  font: bold 11px  Arial, Helvetica, sans-serif;  width: 185px;color:  #800080;
				  height: 15px;
	}
	.grey{
		font:  11px tahoma,verdana,sans-serif;
				  color: #FFEBD5;
	}
	.orange{
		  font: bold 11px tahoma,verdana,sans-serif; color:  #800080; width: 185px;
				  height: 15px;
	}


	.magic {
				  font: bold 11px tahoma,verdana,sans-serif;
				  letter-spacing: 0;

				  width: 185px;
				  height: 15px;
				  color: #215dc6;
				  -moz-border-radius: 0;
				  padding: 0px;


       }
.piyush
{
font:  11px tahoma,verdana,sans-serif; color: #215dc6;
}
</style>

<script>

window.history.forward() ;


HOSPITALCONFIGURATION = '1';  selectedObj ='0';  

HIDE='0';
var i=0
var k = 0 ;
//var c=0
var c=0
var intShow
var intFrame
var speed=3

function expandAll()
{
	if(HIDE=='0')
	{
	HOSPITALCONFIGURATION = '0' ;

	document.all.hosConfig.style.display ='block';
	
	document.getElementById("img_hosconfig").src="../images/mi_small.gif";  // up
	document.getElementById("expand").src="../images/minus.gif";
	parent.document.getElementById("content").src = "content.jsp";
	HIDE='1';
	}
	else
	{
	HOSPITALCONFIGURATION = '1' ;

	document.all.hosConfig.style.display ='none';
	
	document.getElementById("img_hosconfig").src="../images/pl_small.gif";  // down
	document.getElementById("expand").src="../images/plus.gif";
	parent.document.getElementById("content").src = "content.jsp";
	HIDE='0';
	}
	
}


function changeUser(bar)
{
   if ( bar == '1' )  // Hospital Configuration
   {
   		if  ( HOSPITALCONFIGURATION == '0' )
	    {
	
		   document.all.hosConfig.style.display='none';  HOSPITALCONFIGURATION = '1' ;
  		   document.getElementById("img_hosconfig").src="../images/pl_small.gif";  // down
		}
		else
	    {
	        document.all.hosConfig.style.display='block';  HOSPITALCONFIGURATION = '0' ;
	  		document.getElementById("img_hosconfig").src="../images/mi_small.gif";   // up
		}
		
   }
   		   parent.document.getElementById("content").src="content.jsp" ; 
}

function setNavigation()
{
	document.all.hosConfig.style.display ='none';
}

function callPage(url)
{
	parent.document.getElementById("content").src = url;

}
function checkOut(obj)
{
	if(selectedObj == obj)
		{	obj.className='grey';  }
	else
			obj.className='lightOrange';
}
function checkIn(obj)
{
	if(selectedObj == obj)
		 {	obj.className='grey';  }
	else
		obj.className='grey';
}
function setTdColor(obj)
{
		selectedObj.className='lightOrange';
		obj.className='grey';
		selectedObj = obj ;
}







function frame_setting()
{

			if(HIDE=='0')
			{
				
				document.getElementById("minus").src="../images/arrow-right.png";
				document.getElementById("minus").alt ="show menubar";
				parent.document.getElementById("tmp").cols ="1%,*" ;
				HIDE="1";
				document.bgColor="#FFFFFF";
				
			}
			else
			{

				document.getElementById("minus").src="../images/arrow-left.png";
				document.getElementById("minus").alt ="hide menubar";
				parent.document.getElementById("tmp").cols ="20%,*" ;
				HIDE="0";
				document.bgColor="#8A8AC6";
			}
}



</script>
<link href="../css/Color.css" rel="stylesheet" type="text/css">

</head>


<!--<body onLoad="setNavigation();" bgcolor="#EDE6D9" id='menu_body'  >-->
<body onLoad="setNavigation();" bgcolor="#efefef" id='menu_body'  >


<form name="form1" method="post" action="">

<div id="admin_head" style="position: relative">

<table width="100%" >


<tr >
 
  <td colspan="1"><div align='right'><img id="expand"  alt="expand all" onClick='expandAll();' style="cursor:pointer"
			src="../images/plus.gif" width="10" height="10"></div>
	</td>
  
</tr>
</table>






<div id="hospitalConfig_header" style="position: relative">
<table >
	<tr>
		<td valign="baseline" class='magic' onClick="changeUser(1);">&nbsp;&nbsp;<img
			id='img_hosconfig'  style="cursor:pointer"
			src="../images/pl_small.gif" > <font color="#004080" face="Verdana" size="1" style="cursor:pointer">Hospital Configuration</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
	</tr>

</table>
</div>

<div id="hosConfig" style="position: absolute">
<table  >
	<tr >
		<td valign="middle" class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/hospital_master_list.jsp');">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Hospital Master</td>
	</tr>
	
	<tr>
		<td valign="middle" class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtMenu_cntMst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Menu Master</font></td>
	</tr>
	
	<tr>
		<td class='magic' style="cursor:pointer" nowrap align='left'
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtMetatableCol_lstMst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Meta Table Column
		Mapping</font></td>
	</tr>
	
	<tr>
		<td valign="middle" class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/ChangeSuperPassword_cnt.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Change Super User Password</font></td>
	</tr>
	
	<tr>
		<td valign="middle" class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtMenuAllowedURL_cntMst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Allow Menu URL</font></td>
	</tr>
	
	<tr>
		<td valign="middle" class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/ConfigSetup.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Global Configuration Setup</font></td>
	</tr>
	
	<!--<tr style="display:none">
		<td class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/userMgtConfigController.cnt');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Configuartion Master</font>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	
	-->
	<!-- <tr>
		<td class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtJobList_cntMst.jsp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Schedule Listing</font>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	 -->
	<!--<tr >
		<td valign="middle" class='magic' style="cursor:pointer" nowrap
			onMouseOver="checkIn(this);" onMouseOut="checkOut(this);"
			onClick="setTdColor(this);callPage('../usermgmt/masters/umgmtModuleSubscriptionCnt_Mst.jsp');">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#004080" face="Verdana" size="1" style="cursor:pointer">Module Subscription Process</td>
	</tr>
	
	
--></table>
</div>





</div>

</form>

</body>
</html>