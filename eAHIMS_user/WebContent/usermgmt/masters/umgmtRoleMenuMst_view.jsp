<jsp:useBean id="beanObj" class="usermgmt.masters.umgmtRoleMenuBean_Mst" scope="request"/>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%

String hmode = "";
String userSeatId	=	"";

hmode = request.getParameter("hmode");

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


</script>

<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
</head>

<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" >
<form name="form1" method="post" action="umgmtRoleMenuMst_cnt.jsp">
	<!--<%@ include file ="/HisGlobal/header.jsp"%>-->
			
	
  <table width="100%" align="center">
    <tr> 
	  <td class="pagetitle" colspan=2>Role  Menu Master >> View Page</td>
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
		        <div align="right">Root Menu&nbsp;&nbsp;<select name="rootMenu"   onChange="getMenuID(this),submitMode('TEMP_VIEW');">
		            <%=beanObj.getRootMenuComboModify()%> 
		          </select>
		       	</div>
		      </td>
		      <td class="adddatavalue" nowrap width='50%'> 
		
		        <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Level 2&nbsp;&nbsp;
		        <select name='levelTwo2' onChange='getLevelTwo(this),submitMode("TEMP_VIEW");'>
		            <%=beanObj.getLevelTwoOptionsModify()%> 
		           </select>
		        </div>
		      </td>	  
		      <td class="adddatavalue" nowrap width='50%'> 
		
		        <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Level 3&nbsp;&nbsp;
		        <select name='levelThree3' onChange='getLevelThree(this),submitMode("TEMP_VIEW");'>
		            <%=beanObj.getLevelThreeOptionsModify()%> 
		           </select>
		        </div>
		      </td>	  
		  </tr>   
		</table>
	
	
	
	<table width="100%" align="center">
	<tr>		
      <td class="adddatalabel" width='20%'></td>
            <td class="adddatalabel" width='20%'></td>
                  <td class="adddatalabel" width='20%' ><div align="center">Menu</div></td>
                        <td class="adddatalabel" width='20%'></td>
                         <td class="adddatalabel" width='20%'></td>
      </tr>
	 <tr>	
	 
	 <td class="adddatavalue"  width='20%'> 
      </td>
	 	
      <td class="adddatalabel" width='20%'> 
      </td>
      
      <td class="adddatavalue" width='20%'> 
        <div align="center"> 
	    <%=beanObj.getSecondMenuNameView()%> 
       </div>
      </td>
	   <td class="adddatavalue"  width='20%'> 
        <div align="CENTER"> 
       
			 
        </div>
      </td>
	   <td class="adddatavalue"  width='20%'> 
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

<input type="hidden" name="menuSelected" value="">
<input type="hidden" name="menuId_selected" value="<%=beanObj.getMenuId_selected()%>">
<input type="hidden" name="levelTwo" value="<jsp:getProperty property="levelTwo" name="beanObj" />">
<input type="hidden" name="levelThree" value="<jsp:getProperty property="levelThree" name="beanObj" />">
<input type="hidden" name="menuSNo" value="<jsp:getProperty property="menuSNo" name="beanObj" />">
<input type="hidden" name="isvalid" value="<jsp:getProperty property="isvalid" name="beanObj" />">
    </FORM>
	</BODY>
</HTML>
