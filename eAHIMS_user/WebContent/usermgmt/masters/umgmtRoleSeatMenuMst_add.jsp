<jsp:useBean id="beanObj" class="usermgmt.masters.umgmtRoleSeatMenuMstBean" scope="request"/>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%

String hmode = "";
String title = "";
String userSeatId	=	"";

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
%>	


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Role Seat Menu Master</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript" ></script>
<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript" ></script>
<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
  <script language="JavaScript" src="../js/calendar.js"></script>


<script>
function persist_submit(hmode)
{
		var seatObj = document.forms[0].secondSeat;
		var seatLen = seatObj.length;
		
		var menuObj = document.forms[0].secondMenu;
		var len = menuObj.length;
		
		var str2 = "";		
		var str = "";		
		
		for(i=0;i<seatLen;i++)
			str2 += seatObj.options[i].value+"^"+seatObj.options[i].text+"^";
		
		for(i=0;i<len;i++)
				str += menuObj.options[i].value+"^"+menuObj.options[i].text+"^";
			

		document.form1.seatNameSelected.value = str2;	
		document.form1.menuNameSelected.value = str;			
		submitMode(hmode);

}

function submitPage(e,formObj,hmode)
{
		var menuObj = document.forms[0].secondMenu;
		var len = menuObj.length;
		
		var seatObj = document.forms[0].secondSeat;
		var seatLen = seatObj.length;
		
		
		var i = 0;
		var str = "";
		var str2 = "";
		
		if(document.forms[0].module.value=="" || document.forms[0].module.value=="0")
			alert("Select Module");
		else if(document.forms[0].role.value==""||document.forms[0].role.value=="0")
			alert("Select Role");
		else
		{
			for(i=0;i<len;i++)
			{
				str += menuObj.options[i].value+"^";
				
			}
			
			for(i=0;i<seatLen;i++)
			{
				str2 += seatObj.options[i].value+"^";
				
			}
			if(str=="")
				alert("No Menu selected");
			else if(str2=="")
				alert("No Seat selected");
			else
			{						
				document.form1.menuSelected.value = str;		
				document.form1.seatSelected.value = str2;		
				document.form1.hmode.value = hmode;
				document.form1.submit();
			}
		}
	
}
function resetForm(event,formObj,hmode)
{
	if(event.type=="click")
	{
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
	if(listNo=="1")
	{
		firstMenuObj  = document.forms[0].firstSeat;
		secondMenuObj = document.forms[0].secondSeat;	
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
	if(listNo=="1")
	{
		firstMenuObj  = document.forms[0].firstSeat;
		secondMenuObj = document.forms[0].secondSeat;	
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
	if(listNo=="1")
	{
		firstMenuObj  = document.forms[0].firstSeat;
		secondMenuObj = document.forms[0].secondSeat;	
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
	
	if(listNo=="1")
	{
		firstMenuObj  = document.forms[0].firstSeat;
		secondMenuObj = document.forms[0].secondSeat;	
	}
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




</script>

<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
</head>

<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" >
<form name="form1" method="post" action="umgmtRoleMenuMst_cnt.jsp">

			
	
  <table width="100%" align="center" cellspacing="0" cellpadding="0">
    <tr> 
	 <td colspan=2><font color='#000066' size='-1' face='Geneva, Arial, Helvetica, sans-serif'>
		<strong>Role  Menu Master  >> <%=title%> Page</strong></font></td>
	</tr>
	
	
	
	<tr>
	<td class="addheader" colspan=2 align="right">Status</td>
	<td class="addheader" width="5%"> 
	
	<select name = 'isvalid' tabindex="1">
	<option value='1' <%=beanObj.getIsvalid().equals("1")?"selected":""%>>Active</option>
	<option value='2' <%=beanObj.getIsvalid().equals("2")?"selected":""%>>Inactive</option>						
	</select>
	</td>

	</tr>
	</table>
	
	 <table width="100%" align="center"  border="0" cellspacing="1" cellpadding="0">
	
	
  
  
    <tr> 		
      <td class="adddatalabel" nowrap width='50%'> 
        <div align="right"><font color='red'>*</font>Module&nbsp;&nbsp;<select name="module" onChange="submitMode('TEMP');">
            <%=beanObj.getModuleCombo()%> 
          </select></div>
      </td>
      <td class="adddatavalue" nowrap width='50%'> 

        <div align="left"> &nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>*</font>Role&nbsp;&nbsp;<select name="role"  onChange="submitMode('TEMP');">
            <%=beanObj.getRoleCombo()%> 
          </select>
        </div>
      </td>
	  
    </tr>   
    </table>
    
						    <!--  Code for creating the list boxes for the seats -->
	
	<table width="100%" align="center">
	 <tr>		
      <td class="adddatalabel" width='25%'></td>
            <td class="adddatalabel" width='25%'></td>
                  <td class="adddatalabel" width='25%'>Seat</td>
                        <td class="adddatalabel" width='25%'></td>
      </tr>
	 <tr>		
      <td class="adddatalabel" width='20%'>         
      </td>
      <td class="adddatavalue" width='20%'> 
        <div align="left"> 
                   <%=beanObj.getFirstSeatNames()%> 
         
        </div>
      </td>
	   <td class="adddatavalue"  width='20%'> 
        <div align="center"> 
			       

              <img src="../../images/forward4.gif"   class="link"  onClick='moveRightSingle("1");'/> 	

              <img src="../../images/forwardAll4.gif"   class="link"  onClick='moveRightAll("1");'/> 	
              <br>
              <br>
              <img src="../../images/back4.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	

              <img src="../../images/backAll4.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
               
        </div>
      </td>
	   <td class="adddatavalue"  width='20%'> 
          <div align="left">
          <%=beanObj.getSecondSeatNames()%>          
        </div>
      </td>
	  
    </tr>
	</table>
		
		
		<!--  Code for creating the Root Menu Combo and Level Two Combo -->
		
		
		
 <table width="100%" align="center">  
 <tr> 		
      <td class="adddatalabel" nowrap width='50%'> 
        <div align="right"><font color='red'>*</font>Root Menu&nbsp;&nbsp;
          <select name="rootMenu"    onChange="persist_submit('TEMP_PERSIST');">
            <%=beanObj.getRootMenuCombo()%> 
          </select>
       	</div>
      </td>
      <td class="adddatavalue" nowrap width='50%'> 

        <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Level 2&nbsp;&nbsp;
        <select name='levelTwo' onChange='persist_submit("TEMP_PERSIST");'>
            <%=beanObj.getLevelTwoOptions()%> 
        </select>
        </div>
      </td>	  
  </tr>   
</table>

		
					    
					    
					    <!--  Code for creating the list boxes for the menus -->
	
	<table width="100%" align="center">
	 <tr>		
      <td class="adddatalabel" width='25%'></td>
            <td class="adddatalabel" width='25%'></td>
                  <td class="adddatalabel" width='25%'>Menu</td>
                        <td class="adddatalabel" width='25%'></td>
      </tr>
	 <tr>		
      <td class="adddatalabel" width='20%'>         
      </td>
      <td class="adddatavalue" width='20%'> 
        <div align="left"> 
                   <%=beanObj.getFirstMenuNamesAdd()%> 
         
        </div>
      </td>
	   <td class="adddatavalue"  width='20%'> 
        <div align="center"> 
			       

              <img src="../../images/forward4.gif"   class="link"  onClick='moveRightSingle("2");'/> 	
              <img src="../../images/forwardAll4.gif"   class="link"  onClick='moveRightAll("2");'/> 	
              <br>
              <br>
              <img src="../../images/back4.gif"   class="link"  onClick='moveLeftSingle("2");'/> 	
              <img src="../../images/backAll4.gif"   class="link"  onClick='moveLeftAll("2");'/> 	
               
        </div>
      </td>
	   <td class="adddatavalue"  width='20%'> 
          <div align="left">
          <%=beanObj.getSecondMenuNamesAdd()%>          
        </div>
      </td>
	  
    </tr>
	</table>
	<table width="100%" align="center">
	
	
	
	
	<tr>
	<td class="addheader" width="100%" colspan='2'></td>
	</tr> 
    
   <tr> 

      <td colspan=2> 
        <div align="center"> 
		<a 	style=cursor:hand><img src="../../images/Save.gif"   class="link" tabindex=0 onClick='submitPage(event,form1,"SAVE")' onkeyPress='assignMode1(event,form1,"<%=hmode%>")'></a> 
       <!-- <a 	style=cursor:hand><img src="../../images/Clear.gif"  class="link" tabindex=0 onClick='document.form1.reset();'></a> -->
        <a 	style=cursor:hand><img src="../../images/Cancel.gif" class="link" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")' onkeyPress='resetForm(event,form1,"DEFAULT")'></a> 
        </div>
      </td>

    </tr>
  </table>
<input type="hidden" name="hmode">
<input type="hidden" name="seatId" value="<%=beanObj.getSeatId()%>">
<input type="hidden" name="menuSelected" 		value="">
<input type="hidden" name="menuNameSelected" 	value="">
<input type="hidden" name="seatSelected" 		value="">
<input type="hidden" name="seatNameSelected" 	value="">


    </FORM>
	</BODY>
</HTML>
