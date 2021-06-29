<jsp:useBean id="beanObj" class="usermgmt.masters.umgmtRoleSeatMenuMstBean" scope="request"/>
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
<form name="form1" method="post" action="umgmtRoleSeatMenuMst_cnt.jsp">
	<!--<%@ include file ="/HisGlobal/header.jsp"%>-->
			
	
  <table width="100%" align="center">
    <tr> 
	  <td class="pagetitle" colspan=2>Role Seat Menu Master >> View Page</td>
	</tr>
	</table>
	
	 <table width="100%" align="center">
	
	
  
  
    <tr> 		
      <td class="adddatalabel" > 
        <div align="right">Module</div>
      </td>
      <td class="adddatavalue" > 
        <div align="left"> 
        <input type='text' name='moduleName' value='<%=beanObj.getModuleName()%>' readonly>
         
        </div>
      </td>
	  
    </tr>
	 <tr> 
		
      <td class="adddatalabel" > 
        <div align="right">Role</div>
      </td>
      <td class="adddatavalue" > 
        <div align="left"> 
                <input type='text' name='roleName' value='<%=beanObj.getRoleName()%>' readonly>
        </div>
      </td>
	  
    </tr>
	 <tr> 
		
      <td class="adddatalabel" > 
        <div align="right">Seat</div>
      </td>
      <td class="adddatavalue" > 
        <div align="left"> 
                 <input type='text' name='seatName' value='<%=beanObj.getSeatName()%>' readonly>
        </div>
      </td>
	  
    </tr>
	</table>
	
	
			<!--  Code for creating the Root Menu Combo and Level Two Combo -->
		
		
		
		 <table width="70%" align="center">  
		 <tr> 		
		      <td class="adddatalabel" nowrap width='50%'> 
		        <div align="right">Root Menu&nbsp;&nbsp;<select name="rootMenu"   onChange="submitMode('TEMP_VIEW');">
		            <%=beanObj.getRootMenuComboModify()%> 
		          </select>
		       	</div>
		      </td>
		      <td class="adddatavalue" nowrap width='50%'> 
		
		        <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Level 2&nbsp;&nbsp;
		        <select name='levelTwo' onChange='submitMode("TEMP_VIEW");'>
		            <%=beanObj.getLevelTwoOptionsModify()%> 
		           </select>
		        </div>
		      </td>	  
		  </tr>   
		</table>
	
	
	
	<table width="70%" align="center">
	
	 <tr>		
      <td class="adddatalabel" width='20%'> 
             
              
      </td>
      <td class="adddatavalue" width='20%'> 
        <div align="left"> 
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
    </FORM>
	</BODY>
</HTML>
