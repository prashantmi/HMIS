<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import="registration.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:javascript src="/registration/js/calendar-setup.js"/> 

<his:javascript src="/hisglobal/transactionutil/js/master.js"/>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function showEpisodeOpenDiv()
{
	document.getElementById("nextVisitDateLable").style.display="";
	document.getElementById("nextVisitDateControl").style.display="";
}

function closeEpisode()
{
	
	if(document.getElementsByName("episodeIsOpen")[1].checked){ 
		var result=confirm("Do You Want To Close The Episode ?");
		if(result==true){
			submitToDesk('NEW','SAVEEPISODE');
		}
		else{
			document.getElementsByName("episodeIsOpen")[0].checked=true;
		}
	}
}
function saveVisit(){
	
	submitToDesk('NEW','SAVEEPISODE');
}

</script>
<his:statusInProcess>
<his:ContentTag>
<table width="100%" cellspacing="0" cellpadding="0" 
	style="background:#BBBBBB;position: relative">
	<tr>
		<td width="10%" class="tdfonthead" align="left" nowrap="nowrap">
						<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b>
						<bean:message key="closeEpisode" /> </b> </font>
		</td>
		<td width="20%" class="tdfont" nowrap="nowrap" >
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="no" /> </font> 
						<html:radio name="OpdDeskFooterFB" property="episodeIsOpen" tabindex="1" 
						value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onclick="showEpisodeOpenDiv()" /> <font color="#000000"
						size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="yes" /> </font> 
						<html:radio name="OpdDeskFooterFB" property="episodeIsOpen" tabindex="1"
						value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>"  onclick="closeEpisode()" />
		</td>
		<td width="20%" class="tdfont"nowrap="nowrap"  >   
		</td>
		<%--	
		<td id="nextVisitDateLable" width="10%" class="tdfonthead" nowrap="nowrap" >
						<div   align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><font color="#FF0000">*</font>
						<bean:message key="nextVisitDate" /></b>
						</font>
						</div>
		</td>
		<td id="nextVisitDateControl" width="40%" class="tdfont"nowrap="nowrap"  >   
	    				<div  align="left"><his:date name="episodeNextVisitDate" dateFormate="%d-%b-%Y"/>
	    				<img class="button"  class="button" src="../hisglobal/images/GO.png" tabindex="1" border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13)saveVisit();" onClick="saveVisit();" >
	    					</div>
	  	 </td>
	  	--%>
</tr>
</table>

</his:ContentTag>
</his:statusInProcess>

