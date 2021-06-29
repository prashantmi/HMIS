<%
try{
%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page autoFlush="true" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/DateValidation.js"/>
<his:javascript src="/registration/js/deptUnitConsultantInitialMaster.js"/>
<his:javascript src="/registration/js/calendar.js"/>


<%@ page import ="registration.*" %>



<his:TransactionContainer>	
<html:form action="/master/deptUnitConsultantInitialMaster">
	<his:TitleTag name="Department Unit Consultant Initial Master">
	</his:TitleTag>
<his:ContentTag>
<table width="100%" cellpadding="1" cellspacing="1">
	<tr>
		<td width="25%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="department"/>
		</font>
		</div>
		</td>
		
		<td width="25%" class="tdfont">
		<html:select property="departmentCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') submitForm('GET_DEPT_UNITS');"> 		  
		<html:option value="-1">Select Value</html:option>
		<html:options collection = "<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>" property = "value" labelProperty = "label"/>
		</html:select>
		</td>
		
		<td width="25%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="unit"/>
		</font>
		</div>
		</td>
		
		<td width="25%" class="tdfont">
		<html:select property="departmentUnitCode" tabindex="1" styleClass="regCbo" onchange="if(this.value!='-1') submitForm('GET_UNIT_CONSULTANT');"> 		  
		<html:option value="-1">Select Value</html:option>
		<html:options collection = "<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT %>" property = "value" labelProperty = "label"/>
		</html:select>
		</td>
		
		
</tr>
</table>
</his:ContentTag>
<his:statusDone>
<logic:notEmpty name="<%=RegistrationConfig.UNIT_CONSULTANT_LIST %>">
<table width="100%" cellpadding="1" cellspacing="1">
	<tr>
		<td width="50%" class="tdfonthead">
			<div align="left">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="consultant"/>
			</font>
			</div>
			</td>
	</tr>
<logic:iterate id="unitConsultants" name="<%=RegistrationConfig.UNIT_CONSULTANT_LIST %>">
		<tr>
			<td width="50%" class="tdfont">
			<div align="left">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="unitConsultants" property="label"/>
			</font>
			</div>
			</td>
		</tr>	
</logic:iterate>
</table>
</logic:notEmpty>
<his:ContentTag>
<logic:notEmpty name="<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPT_UNIT_DAYS %>">
<table width="100%" cellpadding="1" cellspacing="1">
		<tr>
			<td width="50%" class="tdfonthead">
			<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="days"/>
			</font>
			</div>
			</td>
			
			<td width="50%" class="tdfonthead">
				<div align="center">
				<b><font color="red">*</font></b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="consultantInitial"/>
				</font>
				</div>
			</td>
				
		</tr>
<logic:iterate id="unitDays"  name="<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPT_UNIT_DAYS %>" type="hisglobal.utility.Entry">


	<tr>
		<td  class="tdfont">
		<div align="center">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<%-- <bean:write name="unitDays" property="label"/> --%>
		<%=unitDays.getLabel().split("#")[0] %>
		<input type="hidden" name="day" value="<%=unitDays.getLabel().split("#")[0] %>">
		<html:hidden property="dayOfWeek" name="deptUnitConsultantInitialMstFB" value="<%=unitDays.getValue() %>"></html:hidden>
		</font>
		</div>
		</td>
		
		<td class="tdfont">
			<div align="center">
			
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<%String initials[]=(unitDays.getLabel().split("#")); %>
			<html:text property="daywiseConsultantInitials" name="deptUnitConsultantInitialMstFB"  tabindex="1"
				value='<%=(initials.length>1?initials[1]:"") %>' maxlength="100" size="60" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"></html:text>
			</font>
			</div>
		</td>
		
		
		
</tr>


</logic:iterate>
</table>
</logic:notEmpty>
</his:ContentTag>
</his:statusDone>

<input type="hidden" name="hmode"/>


<his:ButtonToolBarTag>
		
          <his:statusDone>
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer;"  tabindex='1' onclick =  "submitFormOnValidate('SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate('SAVE');")>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer;" tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
          </his:statusDone>
		  
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer;" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
     	   
</his:ButtonToolBarTag>

</html:form>

<his:status/>
</his:TransactionContainer>
<%
}catch(Exception e){e.printStackTrace();}

%>