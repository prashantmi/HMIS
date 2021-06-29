<jsp:useBean id="beanObj" class="usermgmt.masters.UmgmtSeatRoleMstBean" scope="request"/>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%

String hmode = "";
String title = "";
String userSeatId	=	"";

hmode = request.getParameter("hmode");
if(hmode==null)
	hmode="SAVE";
	
	if(hmode.equals("SAVE") || hmode.equals("TEMP"))
{
	title="Add Page";
	
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
function getLevelFour(these)
{
document.forms[0].levelFour.value=these.value;
document.forms[0].levelFive.value="";

//alert(these.value);
}
function getLevelThree(these)
{
document.forms[0].levelThree.value=these.value;
document.forms[0].levelFour.value="";
document.forms[0].levelFive.value="";

//alert(these.value);
}
function getLevelTwo(these)
{

document.forms[0].levelTwo.value=these.value;
document.forms[0].levelThree.value="";
document.forms[0].levelFour.value="";
document.forms[0].levelFive.value="";
//alert(these.value);
}
function getRootMenu(these)
{

document.forms[0].rootMenu.value=these.value;
document.forms[0].levelTwo.value="";
document.forms[0].levelThree.value="";
document.forms[0].levelFour.value="";
document.forms[0].levelFive.value="";

//alert(these.value);
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

function submitPage(e,formObj,hmode)
{
var menuObj = document.forms[0].list2;
		var len = menuObj.length;
		
		
		var i = 0;
		var str = "";
		var str2 = "";
		
		for(i=0;i<len;i++)
			str += menuObj.options[i].value+"^";
		
	if(document.forms[0].group.value=="" || document.forms[0].group.value=="0")
			alert("Please select Group Name ");
		else
	if(document.forms[0].role.value=="" ||document.forms[0].role.value=="0")
			alert("Please select Role Name ");
		else
    if(document.forms[0].seat.value=="" ||document.forms[0].seat.value=="0")
			alert("Please select Seat Name ");
		else
    if(document.forms[0].rootMenu.value=="" ||document.forms[0].rootMenu.value=="0")
			alert("Please select Root Menu  ");	
		else
    if(document.forms[0].levelTwo.value==""||document.forms[0].levelTwo.value=="0")
			alert("Please select Level One ");	
		else
	 if(len==0)
			alert("Right Menu List Box is mandatory ");	
		else {
		        document.form1.menuSelected.value = str;
				document.form1.hmode.value = hmode;
			    document.form1.submit();
			 }
		}

function moveRightSingle(listNo)
{
	var firstMenuObj;
	var secondMenuObj;
	
	if(listNo=="2")
	{
		firstMenuObj  = document.forms[0].list1;
		secondMenuObj = document.forms[0].list2;	
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
		firstMenuObj  = document.forms[0].list1;
		secondMenuObj = document.forms[0].list2;
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
		firstMenuObj  = document.forms[0].list1;
		secondMenuObj = document.forms[0].list2;
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
		firstMenuObj  = document.forms[0].list1;
		secondMenuObj = document.forms[0].list2;	
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
<!-- 
<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" > -->
<body  width="100%" topmargin="0" >
<form name="form1" method="post" action="umgmtSeatRoleMst_cnt.jsp">

 <table width="100%" align="center"  border="0" cellspacing="1" cellpadding="0">
    <tr> 
	 <td colspan=2><font color='#653232' size='-1' face='Geneva, Arial, Helvetica, sans-serif'>
		<strong> Seat Role Master  >> <%=title%></strong></font></td>
	</tr>
	<tr>
	<td class="addHeaderNew" colspan=2 align="right"></td>
	</tr>
	<tr> 		
      <td class="adddatalabelNewRight" nowrap width='50%'> 
        <div align="right"><font color='red'>*</font>Group&nbsp;&nbsp;<select name="group" onChange="submitMode('TEMP');">
            <%=beanObj.getGroupCombo(request)%> 
          </select></div>
      </td>
      <td class="adddatalabelNewRight" nowrap width='50%'> 
        <div align="left">&nbsp;&nbsp;<font color='red'>*</font>Role &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="role" onChange="submitMode('TEMP');">
            <%=beanObj.getRoleCombo(request)%> 
          </select></div>
      </td>
        
    </tr>
   
    <tr> 	
    <td class="adddatalabelNewRight" nowrap width='50%'> 
        <div align="right"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>*</font>Seat&nbsp;&nbsp;<select name="seat"  onChange="submitMode('TEMP');">
            <%=beanObj.getSeatCombo(request)%> 
          </select>
        </div>
      </td>
      <td class="adddatalabelNewRight" nowrap width='50%'> 
        <div align="left">&nbsp;&nbsp;<font color='red'>*</font>Root Menu &nbsp;&nbsp;
          <select name="rootMenu1"    onChange="getRootMenu(this),submitMode('TEMP');">
            <%=beanObj.getRootMenuCombo(request)%> 
          </select>
       	</div>
      </td>	
      
      
   </tr>  
  <!--  </table> 
   <table width="100%" align="center"> -->  
 <tr> 		
      
      <td class="adddatalabelNewRight" nowrap width='50%'> 

        <div align="right"><font color='red'>*</font>&nbsp;&nbsp;Level 1&nbsp;&nbsp;
        <select name='levelTwo2' onChange="getLevelTwo(this),submitMode('TEMP');">
            <%=beanObj.getLevelTwoOptions(request)%> 
        </select>
        </div>
      </td>	 
      <td class="adddatalabelNewRight" nowrap width='50%'> 
        <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Level 2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <select name="levelThree3"    onChange="getLevelThree(this),submitMode('TEMP');" >
         <%=beanObj.getLevelThreeOptions(request)%>      
          </select>
       	</div>
      </td> 
  </tr>   
  
   <tr> 		
      
      <td class="adddatalabelNewRight" nowrap width='50%'> 

        <div align="right">&nbsp;&nbsp;&nbsp;&nbsp;Level 3&nbsp;&nbsp;
        <select name='levelFour4' onChange="getLevelFour(this),submitMode('TEMP')">
           <%=beanObj.getLevelFourOptions(request)%> 
          
        </select>
        </div>
      </td>	 
      <td class="adddatalabelNewRight" nowrap="" width="50%"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Level 4&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <select name="levelFive5" onChange="submitMode('TEMP')">
           <jsp:expression>beanObj.getLevelFiveOptions(request)</jsp:expression> 
          
        </select>
        </div>
      </td>	  
   
      <tr>
      <td class="adddatalabelNewRight" nowrap width='50%'> 
        <div align="right"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Module&nbsp;&nbsp;<input name="moduleName" type="text" readonly value="<%=beanObj.getModule(request)%>" >
        </div>
      </td>
      <td class="adddatavalue" nowrap="" width="50%"></td>
      </tr>  
</table>

<table width="100%" align="center"  border="0" cellspacing="1" cellpadding="0">
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
                  
         <%=beanObj.getFirstMenuNamesAdd(request)%>      
           
         
        </div>
      </td>
	   <td class="adddatavalue"  width='20%'> 
        <div align="left"> 
			       

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
          <%=beanObj. getSecondMenuNamesAdd(request)%>
           
                         
        </div>
      </td>
	  
    </tr>
	</table>
	
	<table width="100%" align="center"  border="0" cellspacing="1" cellpadding="0">
	
	
	
	
	<tr>
	<td class="addHeaderNew" width="100%" colspan='2'></td>
	</tr> 
    
   <tr> 

      <td colspan=2> 
        <div align="center"> 
		<a 	style=cursor:hand><img style="cursor: pointer; cursor: hand" src="../../images/Save.gif"   class="link"  onclick='submitPage(event,form1,"SAVE")' onkeyPress='assignMode1(event,form1,"<%=hmode%>")'></a> 
        <a 	style=cursor:hand><img style="cursor: pointer; cursor: hand" src="../../images/Clear.gif"  class="link"  onclick='submitMode("CLEAR")'></a>
        <a 	style=cursor:hand><img style="cursor: pointer; cursor: hand" src="../../images/Cancel.gif" class="link"  onclick='submitMode("CANCEL")' onkeyPress='resetForm(event,form1,"DEFAULT")'></a> 
        </div>
      </td>

    </tr>
  </table>
  
  <input type="hidden" name="hmode">
  <input type="hidden" name="rootMenu" value="<jsp:getProperty property="rootMenu" name="beanObj" />"/>
 <input type="hidden" name="levelTwo" value="<jsp:getProperty property="levelTwo" name="beanObj" />"/>
<input type="hidden" name="levelThree" value="<jsp:getProperty property="levelThree" name="beanObj" />"/>
<input type="hidden" name="levelFour" value="<jsp:getProperty property="levelTwo" name="beanObj" />"/>
<input type="hidden" name="levelFive" value="<jsp:getProperty property="levelThree" name="beanObj" />"/>
<input type="hidden" name="menuSelected" 		value="<jsp:getProperty property="menuSelected" name="beanObj" />">
<input name="moduleId" type="hidden" readonly="" value="<%=beanObj.getModuleId()%>"/>
  
  </form></body></html>

  
	
	
