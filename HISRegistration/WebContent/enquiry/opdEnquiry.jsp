<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>



<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/opd/opdJs/opdAjax.js" />
<!--<his:javascript src="/opd/opdJs/opd.js" />-->

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />


<script>

function submitPage(hmode){
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}

function onChangeHospital(obj){
	document.getElementsByName("hospitalName")[0].value=obj.options[obj.selectedIndex].text;
	submitPage("ONCHANGEHOSPITAL");
}

function validateDepartmentSubmit(hmode)
{
	
		
		submitForm(hmode);
	
	/*else
	{
	
	showUnit()
	}*/
	return valid
}

/*function showUnit(){
 	var flag=document.getElementsByName("gnum_dept_code")[0].value
 	
 	if(flag=="-1"){
 	
 		document.getElementById("unitCombolable").style.display="none"; 
 		document.getElementById("divUnitControl").style.display="none"; 
 		 
 	}
 	else{
 	
 		document.getElementById("unitCombolable").style.display=""; 
 		document.getElementById("divUnitControl").style.display=""; 
 		 
 	}
 }
 
 function callThisOnload(){
 	showUnit()
 }*/
 
 function validatePatientEnquiry(hmode)
 {
 var valid=false
 	if(comboValidation(document.forms[0].gnum_dept_code,'Department') &&
 	comboValidation(document.forms[0].hgnum_deptunitcode,'Unit'))
 	{
 	valid=true
 	submitForm(hmode)
 	
 	}
 	return valid
 }
 
 
 
 
</script>

<body>
<logic:equal name="opdEnquiryFB" property="isDirectCall" value="DIRECT">
	<form action="<his:path src='/enquiry/opdEnquiry.cnt'/>" method="post">
</logic:equal>

	<%@page import="registration.*,enquiry.*"%>


	<his:TitleTag name="OPD Enquiry">
	</his:TitleTag>

	<his:SubTitleTag name="Opd Schedule Enquiry">
	</his:SubTitleTag>

	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" nowrap width="25%">
					<div align="right"><font color="#FF0000"
						size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
						key="hospname" /></b> </font></div>
				</td>
				<td class="tdfont" width="25%">
					<html:select name="opdEnquiryFB" property="hospitalCode" tabindex="1" styleClass="registrationCmb" onchange="onChangeHospital(this);"> 
						<logic:present
							name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
							<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</td>
				<td class="tdfonthead" width="25%" colspan="2"></td>
			</tr>
			<tr>
				<td width="20%" class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
					key="department" /></b></font></div>
				</td>

				<td class="tdfont">
				<div align="left">
				<html:select name="opdEnquiryFB"
					tabindex="1" property="gnum_dept_code"
					onchange="validateDepartmentSubmit('GETUNIT')" styleClass="regcbo">
					<html:option value="%">All Department</html:option>
					<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>">
						<html:options
							collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>"
							property="value" labelProperty="label" />
					</logic:present>
					</html:select>
					</div>
				</td>
					<td width="20%" id="unitCombolable" class="tdfonthead">
					<div id="divUnitLabel" align="right"><font color="#FF0000"
						size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
						key="unit" /></b></font></div>
					</td>
					<td class="tdfont">
					<div id="divUnitControl" align="left"><html:select
						name="opdEnquiryFB" tabindex="1" property="hgnum_deptunitcode"
						styleClass="regcbo">
						<html:option value="%">All Unit</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>">
							<html:options
								collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>"
								property="value" labelProperty="label" />
						</logic:present>
					</html:select></div>
					</td>


				
			</tr>
			<tr>
				<td width="20%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
					key="weekdays" /></b></font></div>
				</td>
				<td width="20%" class="tdfont">
				<div align="left"><html:select name="opdEnquiryFB"
					tabindex="1" property="hopnum_day_of_week" styleClass="regcbo">
					<html:option value="0">All</html:option>
					<html:option value="1">
						<bean:message key="dOw1" />
					</html:option>
					<html:option value="2">
						<bean:message key="dOw2" />
					</html:option>
					<html:option value="3">
						<bean:message key="dOw3" />
					</html:option>
					<html:option value="4">
						<bean:message key="dOw4" />
					</html:option>
					<html:option value="5">
						<bean:message key="dOw5" />
					</html:option>
					<html:option value="6">
						<bean:message key="dOw6" />
					</html:option>
					<html:option value="7">
						<bean:message key="dOw7" />
					</html:option>

				</html:select></div>
				</td>
				<td width="20%" class="tdfonthead">
				<!--<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
					key="weekOfMonth" /></b></font></div>-->
				</td>
				<td width="20%" class="tdfont">
				<!-- <div align="left"><html:select name="opdEnquiryFB"
					tabindex="1" property="hopnum_week_of_month" styleClass="regcbo">
					<html:option value="0">All</html:option>
					<html:option value="1">
						<bean:message key="wom1" />
					</html:option>
					<html:option value="2">
						<bean:message key="wom2" />
					</html:option>
					<html:option value="3">
						<bean:message key="wom3" />
					</html:option>
					<html:option value="4">
						<bean:message key="wom4" />
					</html:option>
					<html:option value="5">
						<bean:message key="wom5" />
					</html:option>

				</html:select></div>  -->
				</td>



			</tr>

		</table>
	</his:ContentTag>

	<his:ButtonToolBarTag>
		<img class="button" style="cursor: pointer"
			src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search"
			title="Search" onclick="validatePatientEnquiry('SEARCH');">
		<logic:notEqual name="opdEnquiryFB" property="isDirectCall" value="DIRECT">
		 <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
			tabindex="1" style="cursor: pointer"
			onkeypress="if(event.keyCode==13) submitDesk('NEW');" tabindex="1"
			onclick="submitDesk('NEW');">
		</logic:notEqual>
		<logic:equal name="opdEnquiryFB" property="isDirectCall" value="DIRECT">	
		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
			tabindex="1" style="cursor: pointer"
			onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
			onclick="submitPage('CANCEL');">
		</logic:equal>	
		<his:statusTransactionInProcess>
			<logic:equal name="opdEnquiryFB" property="isDirectCall" value="DIRECT">
			<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>'
				tabindex="1" style="cursor: pointer" onclick="submitForm('unspecified')"
				onkeypress="if(event.keyCode==13) submitForm('unspecified');">
			</logic:equal>
			<logic:equal name="opdEnquiryFB" property="isDirectCall" value="DESK">
			<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>'
				tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')"
				onkeypress="if(event.keyCode==13) submitForm('NEW');">
			</logic:equal>
				
		</his:statusTransactionInProcess>
	</his:ButtonToolBarTag>

	<his:statusTransactionInProcess>
		<b><strong>Search Result</strong></b>
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<!-- <td class="tdfonthead" width="12%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
						key="weekOfMonth" /> </b></font></div>
					</td> -->
					<td class="tdfonthead" width="12%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
						key="weekdays" /></b> </font></div>
					</td>
					<td class="tdfonthead" width="12%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
						key="hou" /></b></font></div>
					</td>
					<td class="tdfonthead" width="12%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
						key="department" /></b> </font></div>
					</td>
					<td class="tdfonthead" width="5%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
						key="unit" /></b> </font></div>
					</td>

					<td class="tdfonthead" width="12%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
						key="room" /></b> </font></div>
					</td>
					<td class="tdfonthead" width="12%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
						key="timing" /></b> </font></div>
					</td>
					<td class="tdfonthead" width="35%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
						key="opdConsultantDetail" /></b> </font></div>
					</td>
				</tr>
				<bean:define id="hospitalCodeId" name="opdEnquiryFB" property="hospitalCode" type="java.lang.String"></bean:define>
				<logic:iterate id="opdEnquiryVO"
					name="<%=enquiryConfig.OPD_ENQUIERY_VO_ARRAY %>"
					type="hisglobal.vo.OpdEnquiryVO">
					<tr>
						<%
								String dayOfweek = "dOw" + opdEnquiryVO.getHopnum_day_of_week();
								String weekOfMonth = "wom"
								+ opdEnquiryVO.getHopnum_week_of_month();
								String unitCode = opdEnquiryVO.getHgnum_deptunitcode();
								String deptName = opdEnquiryVO.getGstr_dept_name();
								String unitName = opdEnquiryVO.getHgstr_unitname();
								String houName = opdEnquiryVO.getEmployeeName();
								String start_timing=opdEnquiryVO.getHopstr_start_time()!=null? opdEnquiryVO.getHopstr_start_time():"-";
								String end_timing=opdEnquiryVO.getHopstr_end_time()!=null? opdEnquiryVO.getHopstr_end_time():"-";
								//String timing = opdEnquiryVO.getHopstr_start_time() + " to "
								//+ opdEnquiryVO.getHopstr_end_time();
								String timing =start_timing + " to "
								+ end_timing;
								String queryString = "/AHIMS/enquiry/opdEnquiry.cnt?hmode=POPUP&hospitalCode="+hospitalCodeId
								+ "&hgnum_deptunitcode="
								+ unitCode
								+ "&deptName="
								+ deptName
								+ "&headOfUnit="
								+ houName
								+ "&unitName="
								+ unitName + "&timing=" + timing;
						%>
					 <!-- 	<td class="tdfont">
						 <div align="center"><bean:message key="<%= weekOfMonth%>" />

						</div>
						</td> -->
						<td class="tdfont">
						<div align="center"><bean:message key="<%=dayOfweek %>" /></div>
						</td>
						<td class="tdfont">
						<div align="center">
						<%if(opdEnquiryVO.getEmployeeName()!=null){ %>
						<%=opdEnquiryVO.getEmployeeName()%>
						<%}else{ out.print("-");} %>
						</div>
						</td>
						<td class="tdfont">
						<div align="center">
						<%if(opdEnquiryVO.getGstr_dept_name()!=null){ %>
						<%=opdEnquiryVO.getGstr_dept_name()%>
						<%}else{ out.print("-");} %>
						</div>
						</td>

						<td class="tdfont">
						<div align="center">
						<%if(opdEnquiryVO.getHgstr_unitname()!=null){ %>
						<%=opdEnquiryVO.getHgstr_unitname()%>
						<%}else{ out.print("-");} %>
						</div>
						</td>
						<td class="tdfont">
						<div align="center">
						<%if(opdEnquiryVO.getHgstr_room_name()!=null){ %>
						<%=opdEnquiryVO.getHgstr_room_name()%>
						<%}else{ out.print("-");} %>
						
						</div>
						</td>
						<td class="tdfont">
						<div align="center">
						<%if(opdEnquiryVO.getHopstr_start_time()!=null){ %>
						<%=opdEnquiryVO.getHopstr_start_time()%>
						<%}else{ out.print("-");} %>  TO 
						<%if(opdEnquiryVO.getHopstr_end_time()!=null){ %>
						<%=opdEnquiryVO.getHopstr_end_time()%>
						<%}else{ out.print("-");} %>   
						
						</div>
						</td>
						<td class="tdfont">
						<div align="center"><img
							src='<his:path src="/hisglobal/images/btn-view.png"/>' border=0 tabindex='1'
							onclick="openPopup('<%= queryString%>',event,300,600);"></div>
						</td>
					</tr>

				</logic:iterate>
			</table>
		</his:ContentTag>

	</his:statusTransactionInProcess>


	<html:hidden name="opdEnquiryFB" property="hmode" />
	<html:hidden name="opdEnquiryFB" property="headOfUnit" />
	<html:hidden name="opdEnquiryFB" property="deptName" />
	<html:hidden name="opdEnquiryFB" property="unitName" />
	<html:hidden name="opdEnquiryFB" property="timing" />
	<html:hidden name="opdEnquiryFB" property="isDirectCall" />
	<html:hidden name="opdEnquiryFB" property="hospitalName"/>

<logic:equal name="opdEnquiryFB" property="isDirectCall" value="DIRECT">
	</form>
</logic:equal>

<his:status />
</body>
</html>