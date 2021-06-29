<jsp:useBean id="beanObj" class="usermgmt.masters.umgmtRoleMenuBean_Mst" scope="request"/>
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
<title>Seat Master</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript" ></script>
<script language="JavaScript" src="../js/functionality.js" type="text/JavaScript" ></script>
<style type="text/css">@import url(../../css/calendar-blue2.css);</style>
  <script language="JavaScript" src="../js/calendar.js"></script>


<script>
function getLevelThree(these)
{

document.forms[0].levelThree.value=these.value;
//alert(these.value);
}
function getLevelTwo(these)
{

document.forms[0].levelTwo.value=these.value;
document.forms[0].levelThree.value="";
//alert(these.value);
}
function getMenuID(these)
{

document.forms[0].menuId.value=these.value;
document.forms[0].levelTwo.value="";
document.forms[0].levelThree.value="";
//alert(these.value);
}
function submitPage(e,formObj,hmode)
{
		var menuObj = document.forms[0].secondMenu;
		var len = menuObj.length;
		var i = 0;
		var str = "";
				
		for(i=0;i<len;i++)
		{
			str += menuObj.options[i].value+"^";
			
		}
		if(str=="")
		{
			alert("No Menu Selected to Save");
		}
		else
		{
							
			document.form1.menuSelected.value = str;		
			document.form1.hmode.value = hmode;
		    document.form1.submit();
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

function moveRightSingle()
{
	var firstMenuObj  = document.forms[0].firstMenu;
	var secondMenuObj = document.forms[0].secondMenu;	
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
function moveRightAll()
{
	var firstMenuObj  = document.forms[0].firstMenu;
	var secondMenuObj = document.forms[0].secondMenu;	
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

function moveLeftSingle()
{
	var firstMenuObj  = document.forms[0].firstMenu;
	var secondMenuObj = document.forms[0].secondMenu;	
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

function moveLeftAll()
{
	var firstMenuObj  = document.forms[0].firstMenu;
	var secondMenuObj = document.forms[0].secondMenu;	
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
	<!--<%@ include file ="/HisGlobal/header.jsp"%>-->
			
	
  <table width="100%" align="center">
    <tr> 
	  <td colspan=2><font color='#000066' size='-1' face='Geneva, Arial, Helvetica, sans-serif'>
		<strong>Role Menu Master >> <%=title%> Page</strong></font></td>
	</tr>
	
	
	
	<tr>
	<td class="addheader" align="right" width="95%">Status</td>
	<td class="addheader" width="5%"> 
	
	<select name = 'isvalid' tabindex="1">
	<option value='1' <%=beanObj.getIsvalid().equals("1")?"selected":""%>>Active</option>
	<option value='2' <%=beanObj.getIsvalid().equals("2")?"selected":""%>>Inactive</option>						
	</select>
	</td>

	</tr>
	</table>
	
	 <table width="70%" align="center">
	
	
  
  
    <tr> 		
      
      <td class="adddatalabel" nowrap width='50%'> 
        <div align="c"><font color='red'>*</font>Module&nbsp;&nbsp;&nbsp;&nbsp;<select name="module" onChange="submitMode('TEMP');" disabled="true" >
            <%=beanObj.getModuleCombo()%> 
          </select></div>
      </td>
	  
    </tr>
	 <tr> 
		
      
      <td class="adddatavalue" nowrap width='50%'> 

        <div align="left"> &nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>*</font>Role&nbsp;&nbsp;&nbsp;&nbsp;<select name="role"  onChange="submitMode('TEMP');" disabled="true">
            <%=beanObj.getRoleCombo()%> 
          </select>
        </div>
      </td>
	  
    </tr>
	 
	</table>
	
	
			<!--  Code for creating the Root Menu Combo and Level Two Combo -->
		
		
		
		 <table width="70%" align="center">  
		 <tr> 		
		      <td class="adddatalabel" nowrap width='50%'> 
		        <div align="right"><font color='red'>*</font>Root Menu&nbsp;&nbsp;<select name="rootMenu"   onChange="getMenuID(this),submitMode('TEMP_MODIFY');">
		            <%=beanObj.getRootMenuComboModify()%> 
		          </select>
		       	</div>
		      </td>
		      <td class="adddatavalue" nowrap width='50%'> 
		
		        <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Level 2&nbsp;&nbsp;
		        <select name='levelTwo2' onChange='getLevelTwo(this),submitMode("TEMP_MODIFY");'>
		            <%=beanObj.getLevelTwoOptionsModify()%> 
		           </select>

		        </div>
		      </td>	  
		      <td class="adddatavalue" nowrap width='50%'> 
		
		        <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Level 3&nbsp;&nbsp;
		        <select name='levelThree3' onChange='getLevelThree(this),submitMode("TEMP_MODIFY");'>
		            <%=beanObj.getLevelThreeOptionsModify()%> 
		           </select>

		        </div>
		      </td>	  
		  </tr>   
		</table>
	
	
	
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
        <%=beanObj.getFirstMenuNameModify()%> 
        </div>
      </td>
	   <td class="adddatavalue"  width='20%'> 
        <div align="Left"> 
       
			  <br>
              <img src="../../images/forward4.gif"   class="link"  onClick='moveRightSingle();'/> 	
              <br>
              <img src="../../images/forwardAll4.gif"   class="link"  onClick='moveRightAll();'/> 	
              <br>
              <img src="../../images/back4.gif"   class="link"  onClick='moveLeftSingle();'/> 	
               <br>
              <img src="../../images/backAll4.gif"   class="link"  onClick='moveLeftAll();'/> 	
         
        </div>
      </td>
	   <td class="adddatavalue"  width='20%'> 
        <div align="left"> 
	    <%=beanObj.getSecondMenuNameModify()%> 
       </div>
      </td>
	  
    </tr>
	</table>
	
	
	<table width="70%" align="center">
	
	
	
	
	<tr>
	<td class="addheader" width="100%" colspan='2'></td>
	</tr> 
    
   <tr> 

      <td colspan=2> 
        <div align="center"> 
		<a style=cursor:hand><img src="../../images/Save.gif"   class="link" tabindex=0 onClick='submitPage(event,form1,"UPDATE")' onkeyPress='assignMode1(event,form1,"UPDATE")'></a> 
        <a style=cursor:hand><img src="../../images/Cancel.gif" class="link" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")' onkeyPress='resetForm(event,form1,"DEFAULT")'></a> 
        </div>
      </td>

    </tr>
  </table>
<input type="hidden" name="hmode">
<input type="hidden" name="seatId" value="<%=beanObj.getSeatId()%>">

<input type="hidden" name="module" value="<%=beanObj.getModule()%>">
<input type="hidden" name="role" value="<%=beanObj.getRole()%>">
<input type="hidden" name="seat" value="<%=beanObj.getSeat()%>">
<input type="hidden" name="menuId" value="<%=beanObj.getMenuId()%>">
<input type="hidden" name="previousIsValid" value="<%=beanObj.getPreviousIsValid()%>">
<input type="hidden" name="menuId_selected" value="<%=beanObj.getMenuId_selected()%>">

<input type="hidden" name="menuSelected" value="<jsp:getProperty property="menuSelected" name="beanObj"/>">
<input type="hidden" name="levelTwo" value="<jsp:getProperty property="levelTwo" name="beanObj" />">
<input type="hidden" name="levelThree" value="<jsp:getProperty property="levelThree" name="beanObj" />">
<input type="hidden" name="previousMenuList" value="<jsp:getProperty property="previousMenuList" name="beanObj"/>">
<input type="hidden" name="menuSNo" value="<jsp:getProperty property="menuSNo" name="beanObj"/>">
    </FORM>
	</BODY>
</HTML>
