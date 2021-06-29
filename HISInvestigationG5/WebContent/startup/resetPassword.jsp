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
function resetPassword(e)
{
var selectObj=document.getElementsByName("selectUserId");
var checkCounter=0;


if(e.type=="click"||e.keyCode==13)
	{
	
	for(i=0 ;i < selectObj.length  ; i++)
	  {
	  if(selectObj[i].checked==true)
	       {
 		    document.forms[0].userIdToReset.value=selectObj[i].value;
 		 	checkCounter++;
 		   }		      
	   
	   }
	
	
	
	if(checkCounter==1)	
		{
		
		hisConfirm('Do You Want To Reset The Password !!');
		
		
    	}
    	else
    if(checkCounter==0)	
    	alert("Select a Record") 
        
        
    
    }
}
function confirmYesNo(mode,e)
{

var keyCode=e.keyCode
//alert(keyCode);
if(keyCode=='13' || e.type=='click')
 {	
	
	
if(mode==true)
	{
	closehisConfirm();
	document.forms[0].hmode.value="PASSWORD_IS_RESET";
	document.forms[0].submit();
	}	
	else
if(mode==false)
	{
	closehisConfirm();
	}

 }

}

function cancelPage(e)
{
if(e.type=="click"||e.keyCode==13)
	{
document.forms[0].action="../startup/initPage.jsp";
document.forms[0].submit();
    }
}


</script>
<his:css src="/css/calendar-blue2.css" />

 <body  >
<%
String UserFullName="Reset Password for Users of "+session.getAttribute("UserFullName");
%>

  <form action="UpdateUser" method="post">
  
 <his:TransactionContainer>
  <his:TitleTag name="<%=UserFullName %>">
  </his:TitleTag>
    
<his:ContentTag>

<logic:present name="<%=Config.LIST_OF_ALL_LOW_LEVEL_USERS_FOR_THE_GROUP %>" >
 <table width="100%">
 <tr>
<td class="tdfonthead" width="10%"><div align="center">Select</div></td>
<td class="tdfonthead" width="18%"><div align="center">User Id</div></td>
<td class="tdfonthead" width="18%"><div align="center">Effective Date</div></td>
<td class="tdfonthead" width="18%"><div align="center">Expiry Date</div></td>
<td class="tdfonthead" width="18%"><div align="center">Status</div></td>
<td class="tdfonthead" width="18%"><div align="center">Lock Status</div></td>
</tr>
<logic:iterate  id="userLogin" name="<%=Config.LIST_OF_ALL_LOW_LEVEL_USERS_FOR_THE_GROUP %>"    type="startup.login" >

<tr>

<td class="tdfont">
  <div align="center">
 	<input type="radio" name="selectUserId" value="<%=userLogin.getUserId()%>" > 
  </div>
</td>
 	
<td class="tdfont">
 <div align="center">
<bean:write name="userLogin" property="userName"/>	
 </div>
</td>
 	
<td class="tdfont">
 <div align="center">
<bean:write name="userLogin" property="effectiveDate"/>	
 </div>	
</td>
  	
<td class="tdfont">
  <div align="center">
<bean:write name="userLogin" property="expiryDate"/>	
  </div>
</td>

<td class="tdfont">
 <div align="center">
  <bean:write name="userLogin" property="status"/>	
 </div>
</td>


<td class="tdfont">
 	<div align="center">
      <bean:write name="userLogin" property="isLocked"/>
	</div>
</td>
 	
</tr>
 
</logic:iterate>

</table>



</logic:present>	

	  </his:ContentTag>
			  
		 <his:ButtonToolBarTag>
			   <div align="center" >
			     
			   <img class="button" 	src='<his:path src="/hisglobal/images/Reset_23.png"/>' tabindex="1" 	style=cursor:pointer 	onkeypress=" resetPassword(event)" tabindex="1" onclick="resetPassword(event)"> 
			   <img class="button" 	src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" 	style=cursor:pointer 	 onkeypress="cancelPage(event)" onclick="cancelPage(event)">
			   </div>
			</his:ButtonToolBarTag>
</his:TransactionContainer>		
  	<font color="red"><strong><%=(String)request.getAttribute("message") %></strong></font>
  	
  	<input type="hidden" name="hmode">
  	<input type="hidden" name="userIdToReset">
  	
  	
  	<his:ConfirmYesNoTag>
  	</his:ConfirmYesNoTag>
 
  </form>
 </body>
</html>

