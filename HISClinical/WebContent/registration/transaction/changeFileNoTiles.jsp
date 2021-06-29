<%try{ %>
<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,registration.*,hisglobal.vo.*"%>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.controller.fb.FileNoChangeFB"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">


function validateChangeFileNumber()
{
	var a = 0;
	var valid=false;
	for(i=0;i<document.getElementsByName('selectedEpisode').length;i++){
		if(document.getElementsByName('selectedEpisode')[i].checked==true){
			a++;
			if(<%=Config.FILE_NO_GENERATION_FLAG%>==<%=Config.FILE_NO_GENRATION_MANNUAL_TRUE%>
			   || document.getElementsByName('isGenerateAuto')[0].checked )
			{
				if(isEmpty(document.getElementsByName('fileNo')[i],'FileNo') && validateFileNo(i)
					&& isEmpty(document.getElementsByName('remarks')[i],'Reason'))
				{
					valid= true;
				}
				else
				{
					return false
				}
			}
			if(<%=Config.FILE_NO_GENERATION_FLAG%>==<%=Config.FILE_NO_GENRATION_AUTO_TRUE%>)
			{
				if(isEmpty(document.getElementsByName('remarks')[i],'Reason'))
					{
						valid= true
					}
				else
					{
						return false
					}
			}
		}			
	}
	if (a < 1){
		alert('Please Select Atleast One Record');
		return false;
	}
	else 
		valid= true;	
	
	return valid
	
}

function validatePrint(){
	var a = 0;
	for(i=0;i<document.getElementsByName('selectedEpisode').length;i++){
		if(document.getElementsByName('selectedEpisode')[i].checked==true){
			a++;
		}			
	}
	if (a < 1){
		alert('Please Select Atleast One Record');
		return false;
	}
	else 
		return true;	

}

function enableEpisode(i){
// alert(i);
j= parseInt(i);
var elementArraylength=document.getElementsByName('selectedEpisode').length;
for(k=0;k<elementArraylength;k++)
{
//document.getElementsByName('department')[j].disabled = false;
	if(j==k){
		if(document.getElementsByName("selectedEpisode")[i].checked)
			{
				if(<%=Config.FILE_NO_GENERATION_FLAG%>==<%=Config.FILE_NO_GENRATION_MANNUAL_TRUE%>
					||document.getElementsByName('isGenerateAuto')[0].checked){
					document.getElementsByName('fileNo')[k].disabled = false;
				}
				document.getElementsByName('remarks')[k].disabled = false;
			}else{
				document.getElementsByName('fileNo')[k].disabled =true;
				document.getElementsByName('remarks')[k].disabled = true;
			}
	}
}
	
}

window.onload=function(){
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
}

function changeGenerationMode(obj){
	var fileNo=document.getElementsByName("fileNo")
	if(obj.value=="<%=Config.FILENO_GENRATION_TYPE_AUTO%>"){
		for(var i=0;i<fileNo.length;i++){
			fileNo[i].disabled=true
		}
	}
	else
	{
		selectedEpisode=document.getElementsByName("selectedEpisode")
		for(var i=0;i<fileNo.length;i++){
			for(var j=0;j<selectedEpisode.length;j++){
				if(selectedEpisode[j].checked)
				fileNo[j].disabled=false
			}
		}
	}                                                   
}

function validateFileNo(index){
	var fileNoHidden=document.getElementsByName('fileNoHidden')
	for(var i=0;i<fileNoHidden.length;i++){
		if(document.getElementsByName('fileNo')[index].value==fileNoHidden[i].value){
			alert("Please Enter different FileNo");
			document.getElementsByName('fileNo')[i].focus();
			return false;	
		}	
	}
	return true;
}


</script>


<%@page import="java.util.*,registration.*,hisglobal.vo.*, registration.controller.fb.FileNoChangeFB.*, registration.controller.util.FileNoChangeUTIL"%>
<%
System.out.println("System date in jsp========="+(Date)session.getAttribute(Config.SYSDATEOBJECT));
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
<his:TitleTag name="File Number Monitoring">
</his:TitleTag>


			
<%boolean flagIsStatusNew = false;
			String varStatus = "";
			boolean varIsNewStatus = false;%>
<his:statusNew>
	<%flagIsStatusNew = true;
				varIsNewStatus = true;
				varStatus = "New";%>
</his:statusNew>

<his:InputCrNoTag name="FileNoChangeFB">
</his:InputCrNoTag>

<%if (!flagIsStatusNew) {

				%>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />



<his:statusTransactionInProcess>
<logic:present name="<%=RegistrationConfig.COLL_EPISODE%>">
<his:SubTitleTag name="Change File Number">
<bean:define id="isFileNoGenerationAuto" value="<%=Config.FILE_NO_GENERATION_FLAG%>"></bean:define>
<logic:equal name="isFileNoGenerationAuto" value="<%=Config.FILE_NO_GENRATION_AUTO_TRUE %>">
<table>
<tr>
	<td>
		<input type="radio" name="isGenerateAuto" value="<%=Config.FILENO_GENRATION_TYPE_MANNUAL %>" tabindex="1" onclick="changeGenerationMode(this)"><b><bean:message key="changeManually"/></b>
		<input type="radio" name="isGenerateAuto" value="<%=Config.FILENO_GENRATION_TYPE_AUTO %>" checked="checked" tabindex="1" onclick="changeGenerationMode(this)"><b><bean:message key="generateAutomatically"/></b>
	</td>
	</tr>
</table>
</logic:equal>
</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<%int i = 0;%>
					<tr>
						<td width="10%" class="tdfonthead">
							<div align="center">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="select" /> </font> </b></div></td>
					
						<td width="20%" class="tdfonthead" >
							<div align="center">
							<font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
							<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="department" /> </font> </b></div></td>
						

						<td width="20%" class="tdfonthead">
							<div align="center">
							<font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
							<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="unit" /> </font> </b></div></td>
						
						<td width="25%" class="tdfonthead">
							<div align="center">
							<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="fileNo" /></b></font>
							</div>
						</td>

						<td width="25%" class="tdfonthead">
							<div align="center">
							<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
							<font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message
							key="reason" /> </b></font>
							</div>
						</td>
					</tr>
				<logic:iterate id="CollecFile" name="<%=RegistrationConfig.COLL_EPISODE%>" indexId="j"  >
						     
				<tr>
					<td  class="tdfont">
						<div align="center">
						<input type="checkbox"	name="selectedEpisode" value='<bean:write name="CollecFile" property="episodeCode" /> ' onclick="enableEpisode('<%=i++%>');" tabindex="1" />
						</div>
						</td>
					<td  class="tdfont">
						<div align="center"><bean:write name="CollecFile"
							property="department" />
						</div>
						</td>
						
						<td  class="tdfont">
						<div align="center"><bean:write name="CollecFile"
							property="departmentUnit" />
						</div>
						</td>
					
						
					<td  class="tdfont">
					   <div align="center">
							<html:text name="CollecFile" styleClass="textboxBig" maxlength="30" property="fileNo"  disabled="true"/>
							<%EpisodeVO episodeVO=(EpisodeVO)CollecFile; %>
							<input type="hidden" name="fileNoHidden" value="<%=(episodeVO.getFileNo()==null?"":episodeVO.getFileNo())%>"/>
						</div>
					</td>
					<td class="tdfont">
						<div align="center">
						<html:text name="CollecFile" styleClass="textboxBig" maxlength="100" property="remarks"  disabled="true" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/></div>
					</td>
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
</logic:present>

			

		<%--  </his:statusNew>	--%>

		<!--- End ReferInternal ---- Details of all open episodes-->

</his:statusTransactionInProcess>
<%}%>	

<!-- ...............Code for Button Tool Bar.......... -->
<his:ButtonToolBarTag>
	
	<his:statusInProcessWithJsp>
		<%
		varStatus="InProcess";
	%>
	</his:statusInProcessWithJsp>
	<%if(varStatus.equals("InProcess")){%>
	<div align="center">
	<logic:present name="<%=RegistrationConfig.COLL_EPISODE%>">
	<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>' style="cursor:pointer"
		onkeypress="if(event.keyCode==13) submitFormOnValidate(validatePrint(),'PRINT');"
		tabindex="1" onclick="submitFormOnValidate(validatePrint(),'PRINT');">
	<% if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_MANNUAL_TRUE)){%>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitFormOnValidate(validateChangeFileNumber(),'SAVE');"
		tabindex="1" onclick="submitFormOnValidate(validateChangeFileNumber(),'SAVE');">
		<%} %>
	<% if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE)){%>
	 <img class="button" src='<his:path src="/hisglobal/images/Generate.png"/>'  style=cursor:pointer 
	 tabindex="1" onclick ="submitFormOnValidate(validateChangeFileNumber(),'SAVE');" 
	 onkeypress="if(event.keyCode==13) submitFormOnValidate(validateChangeFileNumber(),'SAVE');">
	<%} %>
	 </logic:present>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
		tabindex="1" style="cursor:pointer"
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');">
	<img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	<%} else{ %>
	<div align="center"><img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"
		style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer"
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	<%} %>
</his:ButtonToolBarTag>
<!-- .......End........Code for Button Tool Bar.......... -->

<his:status />
	
<html:hidden name="FileNoChangeFB" property="hmode" value="unspecified"/>
<%}catch(Exception e){e.printStackTrace();} %>	
 