<%try {

				%>
				<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
				
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/tab.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

//Submit Tile by setting hmode
function submitTile(mode){
	elem = document.getElementsByName('hmode')[0];
	elem.value = mode; 
	//alert(elem.value);
	document.forms[0].submit();
}

function validateDepartments()
{
	var len;
	var isValid = true;
	//int count=0;
	count=0;
	//alert("before assignment");
	len=document.getElementsByName("departmentToChange").length;
	//alert("before for");
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("departmentToChange")[i].checked){
				count++;
					}
				}
	
	if(count!=1){
		isValid = false;
		alert("Select a single department");
				}
	else
	isValid = true;

//alert(isValid);
return isValid;
}
function callThisOnload(){
	focusCrNo();
}
</script>
<%@ page import="java.util.*,registration.*,hisglobal.vo.*"%>
<%System.out.println("inside unitTrransferTile.jsp");

				%>

<%String st = (String) session.getAttribute("SYSDATE");
				System.out.println("date in jsp" + st);

				%>

<%boolean varIsNewStatus = false;
				String varStatus = "";
%>
<his:statusNew>
	<%varIsNewStatus = true;%>

</his:statusNew>




	<his:TitleTag name="Unit Transfer">


		<b>
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		
		</font>
		</b>


	</his:TitleTag>

  <his:InputCrNoTag name="UnitTransferFB"> </his:InputCrNoTag>	
	
	

	<bean:define id="crNo" name="UnitTransferFB" property="patCrNo"
		type="java.lang.String" />
	<%if (!crNo.trim().equals("")) {%>

	<his:statusNew>
		<his:statusInProcessWithJsp>
			<his:patientStatus opd="true" emergency="false">
			</his:patientStatus>
		</his:statusInProcessWithJsp>
	</his:statusNew>

	<his:statusInProcessWithJsp>
		<jsp:include page="/registration/patientDetail.cnt" flush="true"/>

		<bean:define id="EPISODEVO"
			name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>"
			type="hisglobal.vo.EpisodeVO[]" />

		<his:SubTitleTag>
		<his:name>
        <bean:message key="transfer" />
        <bean:message key="unit" />
		</his:name>

						<logic:notEmpty name="<%=RegistrationConfig.ESSENTIALBO_OPTION_UNIT_CHANGE_TO_DEPTUNIT%>">
						<%System.out.println("inside logic not empty");%>
						<%varStatus = "InProcess";%>
						<bean:message key="transfer" />
						<bean:message	key="to" />
						<html:select name="UnitTransferFB" property="toDepartmentUnitCode"	tabindex="0" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<html:options	collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_UNIT_CHANGE_TO_DEPTUNIT%>"	property="value" labelProperty="label" />
						</html:select>
					</logic:notEmpty>
		</his:SubTitleTag>
		
		<his:ContentTag>
				<table width="100%" cellpadding="1">
				<tr>
				<td class="tdfonthead" width="5%">
				<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<div align="center">
				<bean:message key="select"/>
				</div>
				</font>
				</b>
				</td>
				<td class="tdfont" width="35%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<div align="center">
				<bean:message key="department"/>
				</div>
				</b>
				</font>
				</td>
				<td class="tdfonthead" width="30%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<div align="center">
				<bean:message key="unit"/>
				</div>
				</b>
				</font>
				</td>
				<td class="tdfont" width="30%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<div align="center">
				<bean:message key="room"/>
				</div>
				</b>
				</font>
				</td>
				</tr>
				
				<logic:iterate id="episode" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>" indexId="idx">
				<tr>
				<%System.out.println("sdfdfsdfsdf"+idx.intValue()); %>
				<td width="5%" nowrap class="tdfonthead">
				<div align="center">
				<html:radio name="UnitTransferFB" onclick="submitForm('GETWARD');" property="departmentToChange" value='<%=(String)((EpisodeVO)episode).getDepartmentUnitCode()%>' />
				</div>
				</td>
				
				<td width="35%" nowrap class="tdfont">
				<div align="center">
				<bean:write name="episode" property="department" />
				</div>
				</td>
				<td width="30%" nowrap class="tdfonthead">
				<div align="center">
				<bean:write name="episode" property="departmentUnit" />
				</div>
				</td>
				<td width="30%" nowrap class="tdfont">
				<div align="center">
				<bean:write name="episode" property="room" />
				</div>
				</td>
				</tr>
				</logic:iterate>
				</table>

		</his:ContentTag>
	</his:statusInProcessWithJsp>
	<%}

					%>

	<his:ButtonToolBarTag>
		
			
				<%if (varStatus.equals("InProcess")) {%>

					<img class="button"
					src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer
					tabindex="0"
					onkeypress="if(event.keyCode==13) submitFormOnValidate(validateUnitTransfer(),'SAVE');"
					onclick="submitFormOnValidate(validateUnitTransfer(),'SAVE');"> 
  	                <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
					<img  class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
				<%} else {

						%>

					         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
					         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');"> 
					 <%}

					%>
		
	</his:ButtonToolBarTag>



<his:status />

<input type="hidden" name="hmode" />


<%} catch (Exception e) {	
	e.printStackTrace();
				System.out.println("exception in tile... " + e);
			}

		%>

