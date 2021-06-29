<jsp:useBean id="beanObj" class="usermgmt.masters.UmgmtButtonLevelPrivilegesBean" scope="request"/>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%

String hmode = "";
String title = "";
String userSeatId	=	"";
String hospital_code=(String)request.getSession().getAttribute("HOSPITAL_CODE");
beanObj.setHospitalcode(hospital_code);
//String strSeatId=(String)session.getAttribute("SEAT_ID");
//beanObj.setSeatId(strSeatId);
System.out.println("hospital_code2-----"+hospital_code);

hmode = request.getParameter("hmode");
if(hmode==null)
	hmode="SAVE";

if(hmode.equals("SAVE"))
{
	title="Add";
	
}
if(hmode.equals("UPDATE"))
{
	title = "Modify";

}
title=request.getParameter("tile")==null?"Add":request.getParameter("tile");


System.out.println("title----."+title);
%>	


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Button Level Privilage</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript" ></script>
<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript" ></script>
<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
  <script language="JavaScript" src="../js/calendar.js"></script>


<script>
 
function focusOnLoad()
	{

calculateLength();

	if(document.forms[0].seat.value=="0")
	    	document.forms[0].seat.focus();
		else 
	if(document.forms[0].role.value=="0")
	    	document.forms[0].role.focus();
		
	    else
	    	document.forms[0].firstMenu.focus();
	    	

}      	

window.history.foward();
      		
var rightLength="";

function persist_submit(hmode)
{
		var str1 = "";		
		var str2 = "";		
		
		var menuObj1 = document.forms[0].firstMenu;
		var len1 = menuObj1.length;
		
		
		var menuObj2 = document.forms[0].secondMenu;
		var len2 = menuObj2.length;
		
		
		
		
		for(i=0;i<len1;i++)
			str1 += menuObj1.options[i].value+"^";
			
				
	   for(i=0;i<len2;i++)
				str2 += menuObj2.options[i].value+"^";
				
		if(str2=="")
		{
		
		alert("Data in Right List Box must exist");	
		document.forms[0].secondMenu.focus();
		return false;
		}
			
		//document.form1.menuNameSelected.value = str2;			
	//	document.form1.menuNameUnSelected.value = str1;			
		submitMode(hmode);

}
function calculateLength()
{
rightLength=document.forms[0].secondMenu.length;
//alert(rightLength);
}
function submitPage(e,formObj,hmode)
{
if(e.type=="click" || e.keyCode==13)
	{
		var i = 0;
		var str1 = "";
		var str2 = "";
		var create = "";
		var update = "";
		var del =  "";
		var view = "";
		var buttonType="";
		var menuObj1 = document.forms[0].firstMenu;
		var len1 = menuObj1.length;	
		
		var menuObj2 = document.forms[0].secondMenu;
		var len2 = menuObj2.length;
		
		for(i=0;i<len1;i++)
			str1 += menuObj1.options[i].value+"^";		
				
		for(i=0;i<len2;i++)
			str2 += menuObj2.options[i].value+"^";
		
		
		if(str2=="")
		{
		
		alert("Data in Right List Box must exist");	
		document.forms[0].secondMenu.focus();
		return false;
		}
	if(document.forms[0].seat.value=="" || document.forms[0].seat.value=="0")
	     {
			alert("Select Seat");
			document.forms[0].seat.focus();
		 }	
		else 
	if(document.forms[0].role.value==""||document.forms[0].role.value=="0")
	     {
			alert("Select Role");
			document.forms[0].role.focus();
		 }	
		else
			{						
				document.form1.newMenuNameSelected.value = str2;
				document.form1.newMenuNameUnSelected.value = str1;
				document.form1.menuSelected.value = str2;
				if(document.forms[0].create.checked) 
				{
					buttonType="A";
				}
				if(document.forms[0].update.checked) 
				{
					buttonType=buttonType+"M";
				}
				if(document.forms[0].del.checked) 
				{
					buttonType=buttonType+"D";
				}
				if(document.forms[0].view.checked) 
				{
					buttonType=buttonType+"V";
				}
				document.form1.buttonPerm.value=buttonType;
				
					
				document.form1.hmode.value = hmode;
		    	document.form1.submit();
			}
	
	}	
	
}
function resetForm(event,formObj,hmode)
{
	if(event.type=="click" || event.keyCode==13)
	{   
	    document.form1.seat.value=0;
	    document.form1.role.value=0;
	    	    
		document.form1.hmode.value = hmode;
		document.form1.submit();
		
	}
}

function submitMode(hmode)
{
	document.forms[0].hmode.value = hmode;
	document.forms[0].submit();
}

function moveRightSingle(listNo)
{
	var firstMenuObj;
	var secondMenuObj;
	
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstMenu;
		secondMenuObj = document.forms[0].secondMenu;	
	}
	
	
	
	var totalElement  = firstMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
	
						
	for(var i=0;i<totalElement;i++)
	{
		if(firstMenuObj.options[i].selected)
		{
			val1 = firstMenuObj.options[i].value;
			val2 = firstMenuObj.options[i].text;			
		
			t1 = secondMenuObj.length;							
			secondMenuObj.length = (t1+1);				
			
			secondMenuObj.options[t1].value = val1;
			secondMenuObj.options[t1].text  = val2;													
		}
	}
	
	deleteThis(secondMenuObj,firstMenuObj)
}
function moveRightAll(listNo)
{
	var firstMenuObj;
	var secondMenuObj;
	
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstMenu;
		secondMenuObj = document.forms[0].secondMenu;	
	}
	
	var totalElement  = firstMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
	
						
	for(var i=0;i<totalElement;i++)
	{
		
			val1 = firstMenuObj.options[i].value;
			val2 = firstMenuObj.options[i].text;			
		
			t1 = secondMenuObj.length;							
			secondMenuObj.length = (t1+1);				
			
			secondMenuObj.options[t1].value = val1;
			secondMenuObj.options[t1].text  = val2;													
		
	}
	
	deleteThis(secondMenuObj,firstMenuObj)
}



function deleteThis(srcMenuObj,targetMenuObj)
{	
	
	
	var t =0;
	var val1 = "";
	var val2 = "";	
	
	var len  = targetMenuObj.length;

	var len2 = srcMenuObj.length;
	
		
	var a1 = new Array(len);
	var a2 = new Array(len);
	
	var a3 = new Array(len2);
	
	
	
	for(var i=0;i<len;i++)
	{		
		a1[i]= targetMenuObj.options[i].value;
		a2[i]= targetMenuObj.options[i].text;	
		
	}
	
	
	for( i=0;i<len2;i++)
	{		
		a3[i]= srcMenuObj.options[i].value;
	}
	
	
	
	targetMenuObj.length = 0;
	
	var counter = 0;
	
	
	var k = 0;
	
	var flag = 0;
		
	for(i=0;i<len;i++)
	{		
		flag = 0;
		for(k=0;k<len2;k++)
		{		
			if(a1[i]==a3[k])
			{	
				flag = 1;					
			}					
		}		
		if(flag==0)
		{
			targetMenuObj.length = (counter+1);
			targetMenuObj.options[counter].value = a1[i];
			targetMenuObj.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
	
}

function moveLeftSingle(listNo)
{
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstMenu;
		secondMenuObj = document.forms[0].secondMenu;	
	}
	
	
	var totalElement  = secondMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
						
	for(var i=0;i<totalElement;i++)
	{
		if(secondMenuObj.options[i].selected)
		{
			val1 = secondMenuObj.options[i].value;
			val2 = secondMenuObj.options[i].text;
			
		
			t1 = firstMenuObj.length;							
			firstMenuObj.length = (t1+1);				
			
			firstMenuObj.options[t1].value = val1;
			firstMenuObj.options[t1].text  = val2;													
		}
	}
	deleteThis(firstMenuObj,secondMenuObj);
}

function moveLeftAll(listNo)
{
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].firstMenu;
		secondMenuObj = document.forms[0].secondMenu;	
	}
	var totalElement  = secondMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
						
	for(var i=0;i<totalElement;i++)
	{
		
			val1 = secondMenuObj.options[i].value;
			val2 = secondMenuObj.options[i].text;
			
		
			t1 = firstMenuObj.length;							
			firstMenuObj.length = (t1+1);				
			
			firstMenuObj.options[t1].value = val1;
			firstMenuObj.options[t1].text  = val2;													
		
	}
	deleteThis(firstMenuObj,secondMenuObj);
}

function cancelForm(e)
	{
		if(e.type=="click"||e.keyCode==13)
		{
			   document.forms[0].action="../../startup/content.jsp";
			   document.forms[0].submit();
			
		}		
	}


</script>

<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
<link href="../../css/ColorNew.css" rel="stylesheet" type="text/css">
</head>
<!-- 
<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()"> -->
<body  width="100%" topmargin="0" onload="focusOnLoad()">
<form name="form1" method="post" action="umgmtButtonLevel_cntMst.jsp" >
	<table width="100%" align="center" cellspacing="0" cellpadding="0">
  		<tr>
			<td class="addHeaderNew" colspan=2 align="left">Button Level Privilege </td>
		</tr>
	</table>
	<table width="100%" align="center" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td class="adddatalabelNewRight" nowrap width='50%'>
				<div align="right"><font color='red'>*</font>Seat&nbsp;&nbsp;</div></td>
			<td class="adddatavalueNew" nowrap width='50%'><div align="left">
				<select	name="seat" onChange="submitMode('TEMP')" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
						<%=beanObj.getSeatCombo()%>
				</select></div>
			</td>
		</tr>
		<tr>
			<td class="adddatalabelNewRight" nowrap width='50%'>
				<div align="right">&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>*</font>Role&nbsp;&nbsp;</div>
			</td>
			<td class="adddatavalueNew" nowrap width='50%'><div align="left">
				<select	name="role" onChange='submitMode("TEMP");' style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
						<%=beanObj.getRoleCombo()%>
				</select></div>
			</td>
		</tr>
	
	</table>
    <!-- for button previlages -->
    
    <%
    String buttonType=beanObj.getButtonType(); 
    //String buttonType=beanObj.getButtonPerm();
    String isAdd="";
    String isUpdate="";
    String isDel="";
    String isView="";
    if(buttonType!=""){
   	for(int i=0;i<buttonType.length();i++)
   	{
   	 if(buttonType.substring(i,i+1).equals("A"))
   	 {
   		isAdd="checked";
   	 }
   	if(buttonType.substring(i,i+1).equals("M"))
  	 {
   		isUpdate="checked";
  	 }
   	if(buttonType.substring(i,i+1).equals("D"))
  	 {
   		isDel="checked";
  	 }
   	if(buttonType.substring(i,i+1).equals("V"))
  	 {
   		isView="checked";
  	 }
   	}}
    else
    {
    	isView="";
    	isAdd="";
    	isUpdate="";
    	isView="";

    }
    
   
    %>
    <table width="100%" align="center" cellspacing="0" cellpadding="0">
    	<tr>
			<td class="addHeaderNew" colspan=4 align="left">Button Type </td>
		</tr>
	</table>
	<table width="100%" align="center" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td class="adddatavalueNew"  width="25%" align="center"><div align="center">CREATE</div>
			</td>
			<td class="adddatavalueNew"  width="25%" align="center"><div align="center">UPDATE</div>
			</td>
			<td class="adddatavalueNew"  width="25%" align="center"><div align="center">DELETE</div>
			</td>
			<td class="adddatavalueNew"  width="25%" align="center"><div align="center">VIEW</div>
			</td>
		</tr>
		
		<tr>
			<td class="adddatavalueNew"  width="25%" align="center">
				<div align="center"><input type='checkbox' name='create' <%=isAdd %>  value="create"></div>
			</td> 
			<td class="adddatavalueNew"  width="25%" align="center">
				<div align="center"><input type='checkbox' name="update" <%=isUpdate %>  value="update" ></div>
			</td>
			<td class="adddatavalueNew"  width="25%" align="center">
				<div align="center"><input type='checkbox' name="del" <%=isDel %>  value="delete" ></div>
			</td>
			<td class="adddatavalueNew"  width="25%" align="center">
				<div align="center"><input type='checkbox' name="view" <%=isView %>  value="view" ></div>
			</td>
		</tr>
	</table>
					    
					    <!--  Code for creating the list boxes for the menus -->
	
	<table width="100%" align="center">
		<tr>		
            <td class="adddatalabelNewRight" width='35%' align="center" ><div align="center"> All Menu</div></td>
            <td class="adddatalabelNewRight" width='25%'></td>
            <td class="adddatalabelNewRight" width='35%' align="center"><div align="center"> Selected Menu</div></td>
      	</tr>
	   	<tr>		
      		<td class="adddatavalueNew" width='35%'> 
        		<div align="center"> 
                   <%=beanObj.getFirstMenuNamesAdd()%> 
       			</div>
     		</td>
	   		<td class="adddatavalueNew"  width='25%'> 
        		<div align="center"> 
              		<img src="../../images/forward4.gif"   class="link"  onClick='moveRightSingle("2");'/> 	
              		<img src="../../images/forwardAll4.gif"   class="link"  onClick='moveRightAll("2");'/> 	
             		 <br><br>
              		<img src="../../images/back4.gif"   class="link"  onClick='moveLeftSingle("2");'/> 	
              		<img src="../../images/backAll4.gif"   class="link"  onClick='moveLeftAll("2");'/> 	
        		</div>
      		</td>
	   		<td class="adddatavalueNew"  width='35%'> 
          		<div align="center"><%=beanObj.getSecondMenuNamesAdd()%>  </div>
      		</td>
    	</tr>
		<tr>
			<td class="addHeaderNew"  colspan=3 ></td>
		</tr> 
		 <tr> 
     		 <td  colspan="4"> 
       			 <div align="center"> 
					<a 	style=cursor:hand><img src="../../images/btn-sv.png"   class="link" tabindex=0 onClick='submitPage(event,form1,"SAVE")' onkeyPress='submitPage(event,form1,"<%=hmode%>")'></a> 
        			<a 	style=cursor:hand><img src="../../images/btn-clr.png" class="link" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")' onkeyPress='resetForm(event,form1,"DEFAULT")'></a>
       				<a 	style=cursor:hand><img src="../../images/btn-ccl.png" class="link" tabindex=0 onClick='cancelForm(event)' onkeyPress='cancelForm(event)'></a> 
        		</div>
      		</td>
	    </tr>
  </table>
  <input type="hidden" name="title" value=<%=title%>>
<input type="hidden" name="hmode">
<input type="hidden" name="seatId" value="<%=beanObj.getSeatId()%>">
<input type="hidden" name="menuSelected" 		value="<jsp:getProperty property="menuSelected" name="beanObj" />">
<input type="hidden" name="menuNameSelected" value="<jsp:getProperty property="menuNameSelected" name="beanObj" />">
<input type="hidden" name="menuNameUnSelected" value="<jsp:getProperty property="menuNameUnSelected" name="beanObj" />">
<input type="hidden" name="newMenuNameSelected" value="<jsp:getProperty property="newMenuNameSelected" name="beanObj" />">
<input type="hidden" name="newMenuNameUnSelected" value="<jsp:getProperty property="newMenuNameUnSelected" name="beanObj" />">
<input type="hidden" name="buttonPerm" value="<jsp:getProperty property="buttonPerm" name="beanObj" />">
<input type="hidden" name="seatSelected" 		value="">
<input type="hidden" name="seatNameSelected" 	value="">
<input type="hidden" name="menuId" value="<jsp:getProperty property="menuId" name="beanObj" />">

    </FORM>
	</BODY>
</HTML>
