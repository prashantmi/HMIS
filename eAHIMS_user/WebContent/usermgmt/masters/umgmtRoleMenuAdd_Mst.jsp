<jsp:useBean id="beanObj" class="usermgmt.masters.umgmtRoleMenuBean_Mst" scope="request"/>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>

<%

String hmode = "";
String title = "";
String userSeatId	=	"";
String hospital_code=(String)request.getSession().getAttribute("HOSPITALCODE");
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
<script>
	window.history.forward() ;
	
	</script>
<title>Role Seat Menu Master</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript" ></script>
<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript" ></script>
<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
  <script language="JavaScript" src="../js/calendar.js"></script>


<script>
 
function focusOnLoad()
	{

calculateLength();

	if(document.forms[0].module.value=="0")
	    	document.forms[0].module.focus();
		else 
	if(document.forms[0].role.value=="0")
	    	document.forms[0].role.focus();
		else	
	if(document.forms[0].rootMenu.value=="0")
	    	document.forms[0].rootMenu.focus();
		else
	if(document.forms[0].levelTwo2.value=="0")
	    	document.forms[0].levelTwo2.focus();
		else	
	if(document.forms[0].levelThree3.value=="0")
	    	document.forms[0].levelThree3.focus();
		else
	if(document.forms[0].levelFour4.value=="0")
	    	document.forms[0].levelFour4.focus();
	    else
	    	document.forms[0].firstMenu.focus();
	    	

}      	


      		
var rightLength="";

function getLevelFour(these)
{

document.forms[0].levelFour.value=these.value;
//alert(these.value);
}
function getLevelThree(these)
{

document.forms[0].levelThree.value=these.value;
document.forms[0].levelFour.value="";
//alert(these.value);
}
function getLevelTwo(these)
{

document.forms[0].levelTwo.value=these.value;
document.forms[0].levelThree.value="";
document.forms[0].levelFour.value="";
//alert(these.value);
}
function getRootMenu(these)
{

document.forms[0].menuId.value=these.value;
document.forms[0].levelTwo.value="";
document.forms[0].levelThree.value="";

//alert(these.value);
}
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
		
		
		var menuObj1 = document.forms[0].firstMenu;
		var len1 = menuObj1.length;	
		
		var menuObj2 = document.forms[0].secondMenu;
		var len2 = menuObj2.length;
		
		
		for(i=0;i<len1;i++)
			str1 += menuObj1.options[i].value+"^";		
				
		for(i=0;i<len2;i++)
			str2 += menuObj2.options[i].value+"^";
		
		
	if(document.forms[0].module.value=="" || document.forms[0].module.value=="0")
	     {
			alert("Select Module");
			document.forms[0].module.focus();
		 }	
		else 
	if(document.forms[0].role.value==""||document.forms[0].role.value=="0")
	     {
			alert("Select Role");
			document.forms[0].role.focus();
		 }	
		else	
	if(document.forms[0].rootMenu.value==""||document.forms[0].rootMenu.value=="0")
	     {
			alert("Select Root Menu");
			document.forms[0].rootMenu.focus();
		 }	
		else	
	if(rightLength==0 && str2=="")
	     {
	       alert("Select a Menu") 	;
	       document.forms[0].secondMenu.focus(); 
	     }   	
		else
			{						
				document.form1.newMenuNameSelected.value = str2;
				document.form1.newMenuNameUnSelected.value = str1;
				document.form1.menuSelected.value = str2;
						
				document.form1.hmode.value = hmode;
		    	document.form1.submit();
			}
	
	}	
	
}
function resetForm(event,formObj,hmode)
{
	if(event.type=="click" || event.keyCode==13)
	{   
	    document.form1.module.value=0;
	    document.form1.role.value=0;
	    document.form1.rootMenu.value=0;
	    
	    document.form1.levelTwo2.value=0;
	    document.form1.levelThree3.value=0;
	    document.form1.levelFour4.value=0; 
	    
	     document.form1.levelTwo.value="";
	    document.form1.levelThree.value="";
	    document.form1.levelFour.value="";   
	    	    
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
<form name="form1" method="post" action="umgmtRoleMenuCnt_Mst.jsp" >

			
	
  <table width="97%" align="center" cellspacing="0" cellpadding="0">
    <tr>
	<td class="ShadedSubTitleTagImage" colspan=2 align="left">Role Menu Master </td>
	

	</tr>
	</table>

<table class="formbg" width="97%" align="center" border="0" cellspacing="0"
	cellpadding="0" align="center">




	<tr>
		<td class="label" nowrap width='50%'>
		<div align="right"><font color='red'>*</font>Module&nbsp;&nbsp;</div></td>
		<td class="control" nowrap width='50%'><div align="left"><select
			name="module" onChange="submitMode('TEMP')" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
			<%=beanObj.getModuleCombo()%>
		</select></div>
		</td>

	</tr>
	<tr>
		<td class="label" nowrap width='50%'>

		<div align="right">&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>*</font>Role&nbsp;&nbsp;</div>
		</td>
<td class="control" nowrap width='50%'>
<div align="left">
<select	name="role" onChange='submitMode("TEMP");' 
style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
			<%=beanObj.getRoleCombo()%>
		</select></div></td>
	</tr>
	<tr>
		<td class="label" nowrap width='50%'>
		<div align="right"><font color='red'>*</font>Root
		Menu&nbsp;&nbsp; </div>
		</td>
		<td class="control" nowrap width='50%'><div align="left"><select name="rootMenu" onChange='getRootMenu(this),persist_submit("TEMP_PERSIST")' style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
			<%=beanObj.getRootMenuCombo()%>
		</select></div></td>

	</tr>
	<tr>
		<td class="label" nowrap width='50%'>

		<div align="right">&nbsp;&nbsp;&nbsp;&nbsp;Level 1 &nbsp; </div>
		</td>
<td class="control" nowrap width='50%'><div align="left"><select name="levelTwo2" onChange='getLevelTwo(this),persist_submit("TEMP_PERSIST");' style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
			<%= beanObj.getLevelTwoOptions()%>
		</select></div></td>
	</tr>
	<tr>
		<td class="label" nowrap width='50%'>

		<div align="right">&nbsp;&nbsp;&nbsp;&nbsp;Level 2 &nbsp; </div>
		</td>
		<td class="control" nowrap width='50%'><div align="left"><select name="levelThree3" onChange='getLevelThree(this),persist_submit("TEMP_PERSIST");' style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
			<%= beanObj.getLevelThreeOptions()%>
		</select></div></td>
	</tr>
	<tr>
		
		<td class="label" nowrap="" width="50%"> 

        <div align="right">    Level 3  
        
        </div>
      </td>	
	<td class="control" nowrap width='50%'><div align="left"><select name="levelFour4" onChange='getLevelFour(this),persist_submit("TEMP_PERSIST");' style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width:115px;">
           <%=  beanObj.getLevelFourOptions()%> 
        </select></div></td>
	</tr>
</table>
    
					    
					    <!--  Code for creating the list boxes for the menus -->
	
	<table class="formbg" width="97%" align="center" border="0" cellspacing="0"	cellpadding="0" align="center">
	 <tr class="ShadedSubtitletagImage">	
    	<td align="center" colspan="4" width='97%'><font color='red'>*</font>Menus</td>
      </tr>
	 <tr>		
      <td class="control" width='25%'>         
      </td>
      <td class="control" width='25%'> 
        <div align="left"> 
                   <%=beanObj.getFirstMenuNamesAdd()%> 
         
        </div>
      </td>
	   <td class="control"  width='25%'> 
        <div align="left"> 
			       

              <img src="../../images/forward4.gif"   class="link"  onClick='moveRightSingle("2");'/> 	
              <img src="../../images/forwardAll4.gif"   class="link"  onClick='moveRightAll("2");'/> 	
              <br>
              <br>
              <img src="../../images/back4.gif"   class="link"  onClick='moveLeftSingle("2");'/> 	
              <img src="../../images/backAll4.gif"   class="link"  onClick='moveLeftAll("2");'/> 	
               
        </div>
      </td>
	   <td class="control"  width='25%'> 
          <div align="left">
          <%=beanObj.getSecondMenuNamesAdd()%>          
        </div>
      </td>
	  
    </tr>
	
</table>
    
  

  <table width="97%" cellspacing="0" cellpadding="0" align="center">
	<tr class="FOOTER">

		<td colspan=2>
	</tr>
	
	<tr>
		<td colspan=2>
				<div align="center" class="control_button2">
	<a 	style=cursor:hand class="button" tabindex=0 onClick='submitPage(event,form1,"SAVE")' onkeyPress='submitPage(event,form1,"<%=hmode%>")'><span class="save">Save</span></a> 
        <a 	style=cursor:hand class="button" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")' onkeyPress='resetForm(event,form1,"DEFAULT")'><span class="clear">Clear</span></a>
        <a 	style=cursor:hand class="button" tabindex=0 onClick='cancelForm(event)' onkeyPress='cancelForm(event)'><span class="cancel">Cancel</span></a> 
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
<input type="hidden" name="seatSelected" 		value="">
<input type="hidden" name="seatNameSelected" 	value="">
<input type="hidden" name="menuId" value="<jsp:getProperty property="menuId" name="beanObj" />">
<input type="hidden" name="levelTwo" value="<jsp:getProperty property="levelTwo" name="beanObj" />"/>
<input type="hidden" name="levelThree" value="<jsp:getProperty property="levelThree" name="beanObj" />"/>
<input type="hidden" name="levelFour" value="<jsp:getProperty property="levelFour" name="beanObj" />"/>
 <anticsrf:csrftoken/>

    </FORM>
	</BODY>
</HTML>
