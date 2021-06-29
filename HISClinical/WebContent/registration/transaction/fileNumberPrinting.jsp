<%
try{
%>
<%FileNumberPrintingFB fb=(FileNumberPrintingFB)pageContext.findAttribute("fileNumberPrintingFB");%>
<%String departmentCode=(String)session.getAttribute("departmentCode");
String departmentUnitCode=(String)session.getAttribute("departmentUnitCode");
if(fb.getHmode()!=null && fb.getHmode().equals("GET_DEPT_UNITS")){
if (departmentCode.equals("%")) departmentCode=departmentCode+"25";
if (departmentUnitCode.equals("%")) departmentUnitCode=departmentUnitCode+"25";
}
%>
<%if(departmentCode!=null && !departmentCode.equals("-1")) {%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<meta HTTP-EQUIV="Refresh" CONTENT="65;URL=/../HISClinical/registration/fileNumberPrinting.cnt?hmode=GETPATIENTLIST
&departmentCode=<%=departmentCode%>&mode=<%=fb.getMode() %>&departmentUnitCode=<%=departmentUnitCode %>" > 
<%} %>
<%@page autoFlush="true" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.controller.fb.FileNumberPrintingFB"%>
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
<his:javascript src="/registration/js/fileNumberPrinting.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<script language="javascript">
	function timedRefresh(timeoutPeriod) {
	//alert("refresh")
	setTimeout("location.reload(true);",timeoutPeriod);
}
</script>

<%@ page import ="registration.*" %>

<his:TransactionContainer>	
<body> <!-- onload="JavaScript:timedRefresh(5000);"> -->
<html:form action="/fileNumberPrinting">
	<his:TitleTag name="File Number Printing">
	</his:TitleTag>
<his:ContentTag>
<table width="100%" cellpadding="1" cellspacing="1">
	<tr>
		<td width="25%" class="tdfonthead" colspan="4">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 <html:radio  property="mode" value="<%=RegistrationConfig.MODE_FILE_NUMBER_PRINT %>" onclick="submitMode(this)"/> File Printing
		  <html:radio property="mode" value="<%=RegistrationConfig.MODE_SLIP_PRINT %>" onclick="submitMode(this)"/> Slip Printing
		</font>
		</div>
		</td>
	</tr>	
	<tr>
		<td width="25%" class="tdfonthead">
		<div align="right">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="department"/>
		</font>
		</div>
		</td>
		
		<td width="25%" class="tdfont">
		<html:select property="departmentCode" tabindex="1" styleClass="regCbo" onchange="submitForm('GET_DEPT_UNITS');"> 		  
		<html:option value="-1">Select Value</html:option>
		<html:option value="%">All</html:option>
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
		<html:select property="departmentUnitCode" tabindex="1" styleClass="regCbo" onchange="submitForm('GETPATIENTLIST');"> 		  
		<html:option value="%">All</html:option>
		<html:options collection = "<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT %>" property = "value" labelProperty = "label"/>
		</html:select>
		</td>
		
		
</tr>
</table>
</his:ContentTag>
<his:statusDone>
<his:ContentTag>
<logic:present	name="<%=RegistrationConfig.DAILY_PATIENT_VO_ARRAY %>">
		<his:SubTitleTag name="Patient List">
			<table width="100%">
			<tr>
				<td width="25%">
				</td>
			<tr>
				<td width="25%">
					<div align="right">
					<b><input type="radio" name="isPrinted" value="0" onclick="setDisplayMode(this)">Not Printed
					<input type="radio" name="isPrinted" value="1" onclick="setDisplayMode(this)">Printed
					</b>
					</div>
				</td>
			</tr></table>
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td width="4%" class="tdfonthead" height="20px"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"></font>
						<div align="center">
							<input type="checkbox" name="selectAll" onchange="selectAllRecord(this)"> 
						</div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="crNo" /> </font></b></div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="name" /> </font></b></div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="gender/age" /> </font></b></div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="fileNo" /> </font></b></div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="deptName" /> </font></b></div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="unit" /> </font></b></div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="room" /> </font></b></div>
					</td>
				</tr>
				<%int count=0; %>
				<logic:iterate id="dailyPatientVO" indexId="index"
					name="<%=RegistrationConfig.DAILY_PATIENT_VO_ARRAY %>" 
					type="hisglobal.vo.DailyPatientVO"  >
					<bean:define id="isFilePrinted"  name="fileNumberPrintingFB" property="isPrinted"/>
					
					<%System.out.println(isFilePrinted); %>
					<%if(dailyPatientVO.getFileNoPrint().equals(isFilePrinted)){ %>
					<tr>
					
						<td width="4%" class="tdfont">
						<div align="center"><font size="2" color="black"> 
						<html:checkbox	name="fileNumberPrintingFB" property="selectedCrNo"
								value='<%=index.toString()%>'
								onclick="showDetail(this)" tabindex="1">
						</html:checkbox>
						</div>
						</td> 
						<td width="16%" class="tdfont">
						<div align="center"><font size="2" color="black">  <bean:write
							name="dailyPatientVO" property="patCrNo" /> </font></div>
						</td>
						<td width="16%" class="tdfont">
						<div align="center"><font size="2" color="black"> 
							<bean:write	name="dailyPatientVO" property="patFirstName" /> 
							<bean:write	name="dailyPatientVO" property="patMiddleName" /> 
							<bean:write	name="dailyPatientVO" property="patLastName" /> 
						</font></div>
						</td>
						<td width="16%" class="tdfont">
						<div align="center"><font size="2" color="black">  <bean:write
							name="dailyPatientVO" property="patGender" />/<bean:write
							name="dailyPatientVO" property="patAge" />
							 </font>
						</div>
						</td>
						<td width="16%" class="tdfont">
						<div align="center"><font size="2" color="black">  <bean:write
							name="dailyPatientVO" property="fileNo" />
						</font></div>
						</td>
						<td width="16%" class="tdfont">
						<div align="center"><font size="2" color="black">  <bean:write
							name="dailyPatientVO" property="department" /> </font></div>
						</td>
						<td width="16%" class="tdfont">
						<div align="center"><font size="2" color="black">  <bean:write
							name="dailyPatientVO" property="departmentUnit" /> </font>
						</div>
						</td>
						<td width="16%" class="tdfont">
						<div align="center"><font size="2" color="black">  <bean:write
							name="dailyPatientVO" property="room" /> </font>
						</div>
						</td>
						<%count++;%>
					</tr>
					<%} %>
				</logic:iterate>
			</table>
			<%if(count==0){ %>
				<tr>
					<td class="tdfont">
						<div align="center">
						<b><font color="red">No File Found to Print</font></b>
						</div>
					</td>
				</tr>
			<%} %>
		</his:ContentTag>
	</logic:present>

</his:ContentTag>
</his:statusDone>

<html:hidden property="hmode"/>
<html:hidden property="isPrintedFlag"/>


<his:ButtonToolBarTag>
		
          <his:statusDone>
		  <img class='button' src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  style="cursor:pointer;"  tabindex='1' onclick =  "submitFormOnValidate('PRINT');" onkeypress="if(event.keyCode==13)submitFormOnValidate('PRINT');")>
          </his:statusDone>
		  
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer;" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
     	   
</his:ButtonToolBarTag>

</html:form>

<his:status/>
</body>
</his:TransactionContainer>
<%
}catch(Exception e){e.printStackTrace();}

%>