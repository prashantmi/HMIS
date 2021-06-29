<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,startup.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="hisglobal.hisconfig.Config"%>
<html>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:javascript src="/registration/js/registration.js"/>

<script type="text/javascript">
function cancelPage(e)
{
if(e.type=="click"||e.keyCode==13)
	{
document.forms[0].action="../startup/initPage.jsp";
document.forms[0].submit();
    }
}
function getAllMenus(these,roleIdConcate)
{

document.forms[0].allRoles.value=these.value;
document.forms[0].hmode.value="GET_ALL_MENUS";
document.forms[0].display.value="true";
document.forms[0].submit();
}

function getRoleMenus(these)
{

document.forms[0].roleId.value=these.value;
document.forms[0].hmode.value="GET_MENUS";
document.forms[0].display.value="true";
document.getElementById("menusId").style.display="block"
document.getElementById("imageRoleId").src="../hisglobal/images/arrdouble-up.png"
document.forms[0].submit();

}

function showComponentDiv(these)
{

if(these.id=="imageRoleId")
 {	

	if(document.getElementById("menusId").style.display=="none")
	{
	document.getElementById("menusId").style.display="block"
	document.getElementById("imageRoleId").src="../hisglobal/images/arrdouble-up.png"
	}
	else
	{
	document.getElementById("menusId").style.display="none"
	document.getElementById("imageRoleId").src="../hisglobal/images/arrdouble-down.png"
	}
  }	
else
if(these.id=="imagePrivelageId")
 {	

 
	if(document.getElementById("privelageId").style.display=="none")
	{
	document.getElementById("privelageId").style.display="block"
	document.getElementById("imagePrivelageId").src="../hisglobal/images/arrdouble-up.png"
	}
	else
	{
	document.getElementById("privelageId").style.display="none"
	document.getElementById("imagePrivelageId").src="../hisglobal/images/arrdouble-down.png"
	}
  }	



}

</script>
<his:css src="/css/calendar-blue2.css" />

 <body  >
<%
String UserFullName="Roles and Privilages of "+session.getAttribute("UserFullName");
%>

  <form action="UpdateUser" method="post">
  
 <his:TransactionContainer>
  <his:TitleTag name="<%=UserFullName %>">
  </his:TitleTag>
    
  

	 <his:ContentTag>
	 
	
			 
			 
			
<%
 String display="";
 String imgSource="";
 String displayPrivelage="";
 String imgSourcePrivelage="";
 
 String check=request.getParameter("display");
 
 //System.out.println("check-------"+check);
 
 displayPrivelage="display:none";
 imgSourcePrivelage="/hisglobal/images/arrdouble-down.png";
 
 if(check==null || check.equals(""))
 	{
 
 
 	 display="display:none";
	 imgSource="/hisglobal/images/arrdouble-down.png";
	 
 	}
 	else
 if(check.equals("true"))
 	 	{
 	 
 	 
 	 	 display="display:block";
 		 imgSource="/hisglobal/images/arrdouble-up.png";
 	 	}
 		
	 
 String[] roleIdArray=(String[])session.getAttribute(Config.LIST_OF_ROLES_ID);
 String[] roleNameArray=(String[])session.getAttribute(Config.LIST_OF_ROLES_NAME);	 
 
 
 %>

<%
if(roleIdArray.length==1 && roleNameArray.length==1)
{
%>		
			
<his:SubTitleTag name="Role">

 <table width="100%">
 	<tr>
 	<td>
 	<div align="left">
 	<font  size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
 	<%=roleNameArray[0]%>
 	</font>
 	</div>
 	</td>
 	<td> 	
 	<div align="right">
 	<img id="imageRoleId" class="button" src='<his:path src="<%=imgSource%>"/>' tabindex="1"
	style=cursor:pointer onkeypress="showComponentDiv(this)" onclick="showComponentDiv(this)"> 
 	</div>
 	</td>
 	</tr>
 </table>
</his:SubTitleTag>


<div id="menusId" style="<%=display %>">

			<logic:present name="<%=Config.LIST_OF_MENUS %>">			
			   <bean:write name="<%=Config.LIST_OF_MENUS %>" filter="false"/>
		 	</logic:present>
			
         
</div>			
		 	
<%
}
else
	if(roleIdArray.length > 1 && roleNameArray.length > 1)
	{	
%>		 		 	
<his:SubTitleTag name="Roles">

 <table width="100%">
 	<tr>
 	<td>
 	
 	<logic:present name="<%=Config.USER_ROLES_LIST %>">
 	 	<font color="#ffffff"
						size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:write name="<%=Config.USER_ROLES_LIST %>" filter="false"/>
						</font>
 	</logic:present>
 	
 	</td>
 	<td> 	
 	<div align="right">
 	<img id="imageRoleId" class="button" src='<his:path src="<%=imgSource%>"/>' tabindex="1"
	style=cursor:pointer onkeypress="showComponentDiv(this)" onclick="showComponentDiv(this)"> 
 	</div>
 	</td>
 	</tr>
 </table>
</his:SubTitleTag>


<div id="menusId" style="<%=display %>">

			<logic:present name="<%=Config.LIST_OF_MENUS %>">			
			   <bean:write name="<%=Config.LIST_OF_MENUS %>" filter="false"/>
		 	</logic:present>
			
         
</div>			 	
		
		
<%
	}
%>		



<his:SubTitleTag name="Privilages">

 <table width="100%">
 	<tr>
 	<td>
  	</td>
 	<td> 	
 	<div align="right">
 	<img id="imagePrivelageId" class="button" src='<his:path src="<%=imgSourcePrivelage%>"/>' tabindex="1"
	style=cursor:pointer onkeypress="showComponentDiv(this)" onclick="showComponentDiv(this)"> 
 	</div>
 	</td>
 	</tr>
 </table>
</his:SubTitleTag>

<div id="privelageId" style="<%=displayPrivelage %>">

			<logic:present name="<%=Config.LIST_OF_PRIVELAGES %>">			
			   <bean:write name="<%=Config.LIST_OF_PRIVELAGES %>" filter="false"/>
		 	</logic:present>
			
         
</div>	

	  </his:ContentTag>
			  
		 <his:ButtonToolBarTag>
			   <div align="center" >
			     
			   <img class="button" 	src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" 	style=cursor:pointer 	onkeypress=" cancelPage(event)" tabindex="1" onclick="cancelPage(event)"> 

			   </div>
			</his:ButtonToolBarTag>
</his:TransactionContainer>		
  	<font color="red"><strong><%=(String)request.getAttribute("message") %></strong></font>
  	
  	<input type="hidden" name="hmode">
  	<input type="hidden" name="allRoles">
  	<input type="hidden" name="roleId">
  	<input type="hidden" name="display">
 
  </form>
 </body>
</html>

