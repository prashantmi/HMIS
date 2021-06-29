<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script>

function validateEpisodeCode(){
// alert('inside validateEpisodeCode() of referDeptVisitStamp.jsp');

<logic:equal name="ReferDeptVisitStampFB" property="isRefferInOut" value="<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL%>">
// 	alert('one');
				
// 	alert('two');
		//alert("internal referral...validate ep code"+document.getElementsByName('episodeCode')[0].value);
		//alert(document.forms[0].episodeCode.value);
		<logic:notEmpty name="<%=RegistrationConfig.ARR_EPISODE_REFER_VO%>">
	// 		alert("not empty");
			return true;
		</logic:notEmpty>
		
		<logic:empty name="<%=RegistrationConfig.ARR_EPISODE_REFER_VO%>">
	// 		alert("empty");
				if(document.forms[0].episodeCode=="undefined" || document.forms[0].episodeCode.value=="" || document.forms[0].episodeCode.value=="undefined"){
					alert("Enter a valid Episode Number");
					return false;
				}
				else
					return true;
		</logic:empty>
	
</logic:equal>

<logic:equal name="ReferDeptVisitStampFB" property="isRefferInOut"
				value="<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL%>">
		// 		alert('external referral');
				if (validateExternalReferral())
				return true;
				else 
				return false;
</logic:equal>
}


function validateExternalReferral(){
<logic:equal name="ReferDeptVisitStampFB" property="referringInstType" value="O">
// alert('Referral...Other Hospital');
if (document.getElementsByName('patRefHospitalName')[0].value.length == 0)
        {
        alert("Enter Referring Hospital Name");
        document.getElementsByName('patRefHospitalName')[0].focus();
        return false;
        }
        else if (document.getElementsByName('patRefDoctor')[0].value.length == 0)
        {
        alert("Enter Referring Doctor Name");
        document.getElementsByName('patRefDoctor')[0].focus();
        return false;
        }
        else if (document.getElementsByName('refFromDepartment')[0].value.length == 0)
        {
        alert("Enter Referred From Department");
        document.getElementsByName('refFromDepartment')[0].focus();
        return false;
        }
        else 
        return true;
</logic:equal>


<logic:equal name="ReferDeptVisitStampFB" property="referringInstType" value="G">
//var value;
//alert('Referral...GNCTD'+document.getElementsByName('patRefGnctdHospitalCode').selectedIndex);
if (document.getElementsByName('patRefGnctdHospitalCode')[0].options[document.getElementsByName('patRefGnctdHospitalCode')[0].selectedIndex].value == "-1")
        {
        alert("Select Referring Hospital");
        document.getElementsByName('patRefGnctdHospitalCode')[0].focus();
        return false;
        }
        else if (document.getElementsByName('patRefGnctdHospitalCrno')[0].value.length == 0)
        {
        alert("Enter Referring Hospital CR No");
        document.getElementsByName('patRefGnctdHospitalCrno')[0].focus();
        return false;
        }
        else if (document.getElementsByName('patRefDoctor')[0].value.length == 0)
        {
        alert("Enter Referring Doctor Name");
        document.getElementsByName('patRefDoctor')[0].focus();
        return false;
        }
        else if (document.getElementsByName('patRefGnctdHospitalDept')[0].options[document.getElementsByName('patRefGnctdHospitalDept')[0].selectedIndex].value == "-1" ) //"" ||  document.getElementsByName('patRefGnctdHospitalDept').value=="-1")
        {
        alert("Enter Referring Department");
        document.getElementsByName('patRefGnctdHospitalDept')[0].focus();
        return false;
        }
        else 
        return true;
</logic:equal>

}


function checkvalue(){
//alert("check val......");
if(document.getElementsByName('refToDepartmentCode')[0].value=="-1")
alert("Select a department");
else
{
//document.getElementsByName('departmentdiv')[0].value="1";
//alert("true");

 submitForm('ADDDEPT'); 
 }
 }
 
 function ValidateDepartment(field) {
//  alert('.....inside ValidateDepartment() of refdeptvisitstamp.jsp  ');
 
 
  if (document.getElementsByName('refToDepartmentCode')[0].options[document.getElementsByName('refToDepartmentCode')[0].selectedIndex].value == "-1" ) //"" ||  document.getElementsByName('patRefGnctdHospitalDept').value=="-1")
        {
        alert("Select Referred to Department");
        document.getElementsByName('refToDepartmentCode')[0].focus();
        return false;
        }
        else 
        return true;
}
 
 
 function callThisOnload()
{
	focusCrNo();
	<his:statusTransactionInProcess>
	document.getElementsByName("patPrimaryCatCode")[0].focus()="true";
	</his:statusTransactionInProcess>
	
	if('<bean:write name="ReferDeptVisitStampFB" property="isRefferInOut"/>'=='2')
	{
	<logic:equal name="ReferDeptVisitStampFB" property="referringInstType" value=""> 
	document.getElementsByName('referringInstType')[0].checked=true;
	submitForm('CHANGEREFERINSTTYPE');
	</logic:equal>
	}
}

function enableDepartment(i){
// alert(i);
j= parseInt(i);
var elementArraylength=document.getElementsByName('refferringOPDEpisode').length;
for(k=0;k<elementArraylength;k++)
{
//document.getElementsByName('department')[j].disabled = false;
if(j==k)
	document.getElementsByName('refToDepartmentCode')[k].disabled = false;
	else
	document.getElementsByName('refToDepartmentCode')[k].disabled =true;
}

	
}

</script>
<%@ page
	import="java.util.*,registration.*,hisglobal.vo.*,registration.controller.util.ReferDeptVisitStampUTIL"%>
	<%
	String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
	%>
<his:TitleTag>
	<his:name>
		<bean:message key="referDeptVisitStamp" />
	</his:name>
	<b> <font  size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> 
		</font> </b>
</his:TitleTag>
<%System.out.println("Hi 119");

			%>
<%boolean flagIsStatusNew = false;
			String varStatus = "";
			boolean varIsNewStatus = false;%>
<his:statusNew>
	<%flagIsStatusNew = true;
				varIsNewStatus = true;
				varStatus = "New";%>
</his:statusNew>

<his:InputCrNoTag name="ReferDeptVisitStampFB">
</his:InputCrNoTag>

<%if (!flagIsStatusNew) {

				%>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />
<%System.out.println("Hi 146");

				%>


<%System.out.println("Hi 149");

				%>
<his:statusTransactionInProcess>
	<!-- ...............Code for selection of Primary and secondary category.......... -->
<%--
	<his:SubTitleTag name="Select Category">
		<his:name>
			<bean:message key="selcat" />
		</his:name>
	</his:SubTitleTag>

	<his:ContentTag>
		<%System.out.println("Hi 156");%>
		<table width="100%" border="0" cellspacing="1" cellpadding="0"
			bgcolor="#EBEBEB">

			<tr>

				<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="primary" /> <bean:message key="category" /> </font></div>
				</td>

				<%System.out.println("Hi 169");%>

				<td width="25%" class="tdfont"><font color="#000000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif"> <html:select
					name="ReferDeptVisitStampFB" property="patPrimaryCatCode"
					tabindex="1" styleClass="regcbo"
					onchange="if(this.value!='-1') submitForm('GETSECCAT')">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>"
						property="value" labelProperty="label" />
				</html:select> </font></td>

				<%System.out.println("Hi 180");%>

				<td width="25%" nowrap class="tdfonthead">
				<div align="right"><font color="#FF0000" size="1"
					face="Verdana, Arial, Helvetica, sans-serif">* </font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="secondary" /> <bean:message key="category" /> </font></div>
				</td>

				<%System.out.println("Hi 193");%>

				<td width="25%" height="21" class="tdfont"><html:select
					name="ReferDeptVisitStampFB" property="patSecondaryCatCode"
					tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY%>"
						property="value" labelProperty="label" />
				</html:select></td>
			</tr>
		</table>

		<%System.out.println("Hi 203");%>

	</his:ContentTag>
--%>

	<!-- .........End......Code for selection of Primary and secondary category.......... -->

	<!--................................... code for referred details........... -->
	<his:SubTitleTag>
		<his:name>
			<bean:message key="referDetail" />
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="referInternal" /> <html:radio name="ReferDeptVisitStampFB"
					property="isRefferInOut"
					value="<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL%>"
					onclick="submitForm('CHANGEREFERTYPE');" tabindex="1" />
				&nbsp;&nbsp; <bean:message key="referExternal" /> <html:radio
					name="ReferDeptVisitStampFB" property="isRefferInOut"
					value="<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL%>"
					onclick="submitForm('CHANGEREFERTYPE');" tabindex="1" /> </font></div>
				</td>


				<td><logic:equal name="ReferDeptVisitStampFB"
					property="isRefferInOut"
					value="<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL%>">

					<!-- if External Referral -->
					<bean:message key="gnctd" />
					<html:radio name="ReferDeptVisitStampFB"
						property="referringInstType" tabindex="1" value="G"
						onclick="submitForm('CHANGEREFERINSTTYPE');" tabindex="1" /> 
			         &nbsp; &nbsp;
			         <bean:message key="other" />
					<html:radio name="ReferDeptVisitStampFB"
						property="referringInstType" value="O" tabindex="1"
						onclick="submitForm('CHANGEREFERINSTTYPE');" tabindex="1" />
				</logic:equal></td>
				<td><logic:equal name="ReferDeptVisitStampFB"
					property="isRefferInOut"
					value="<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL%>">

					<!-- if External Referral -->
					<logic:equal name="ReferDeptVisitStampFB"
						property="referringInstType" value="O">
						<!-- ReferInstType is Other than GNCTD -->
						<font color="#FF0000" size="1"
							face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<bean:message key="referringHospital" />
						<html:text name="ReferDeptVisitStampFB" tabindex="1"
							property="patRefHospitalName" maxlength="100"
							styleClass="textboxBig" size="20" />

						<!-- Closing ReferInstType is Other than GNCTD -->
					</logic:equal>

					<logic:equal name="ReferDeptVisitStampFB"
						property="referringInstType" value="G">

						<!-- ReferInstType GNCTD -->
						<font color="#FF0000" size="1"
							face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<bean:message key="referringHospital" />
						<html:select name="ReferDeptVisitStampFB" tabindex="1"
							property="patRefGnctdHospitalCode" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<html:options
								collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>"
								property="value" labelProperty="label" />
						</html:select>
						<!-- Closing ReferInstType is GNCTD -->
					</logic:equal>
				</logic:equal></td>
			</tr>

		</table>
	</his:SubTitleTag>

	<logic:equal name="ReferDeptVisitStampFB" property="isRefferInOut"
		value="<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL%>">

		<!-- -----Refer internal....Reading records from Episode Refer Dtl Table -->
		<%System.out.println("Hi 192");

						%>

		<logic:notEmpty name="<%=RegistrationConfig.ARR_EPISODE_REFER_VO%>">
			<bean:define id="EPISODEREFERVO"
				name="<%=RegistrationConfig.ARR_EPISODE_REFER_VO%>"
				type="hisglobal.vo.EpisodeReferVO[]" />
			<his:ContentTag>
				<%if (EPISODEREFERVO.length != 0) {
									System.out
											.println("length in jsp......................"
													+ EPISODEREFERVO.length);

									%>
				<%varStatus = "InProcess";%>
				<table width="100%" colspacing="1" colpadding="0"
					style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
					<tr>
						<%System.out.println("Hi 198");

									%>
						<td width="100%" colspan="5"><his:SubTitleTag
							name="Internal Referral Detail">
						</his:SubTitleTag>
						<table width="100%" colspacing="0" colpadding="0">
							<%System.out.println("Hi 202");

									%>
							<tr>
								<td width="33%" class="addtoolbar"
									style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><b> <bean:message
									key="referFromDept" /></b></font></td>
								<td width="33%" class="addtoolbar"
									style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message
									key="referredBy" /></b></font></td>
								<td width="34%" class="addtoolbar"
									style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message
									key="referToDept" /></b></font></td>
							</tr>
							<logic:iterate id="internalReferral"
								name="<%=RegistrationConfig.ARR_EPISODE_REFER_VO%>">
								<tr>
									<%System.out.println("Hi 211");

										%>

									<%System.out.println("Hi 225");

										%>
									<td width="33%" class="tdfont">
									<div align="center"><font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
										name="internalReferral" property="refFromDepartment" /></font></div>
									</td>
									<td width="33%" class="tdfont">
									<div align="center"><font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
										name="internalReferral" property="patRefDoctor" /></font></div>
									</td>
									<td width="34%" class="tdfont"><%System.out.println("Hi 234");

									%>
									<div align="center"><font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:write
										name="internalReferral" property="refToDepartment" /></font></div>
									</td>
								</tr>
							</logic:iterate>

						</table>
						</td>
					</tr>

				</table>
				<%}

							%>
			</his:ContentTag>
		</logic:notEmpty>
		<!-- ---End.....Refer internal....Reading records from Episode Refer Dtl Table -->

		<!--- ReferInternal -------  Details of all open episodes-->

		<logic:empty name="<%=RegistrationConfig.ARR_EPISODE_REFER_VO%>">

			<%System.out.println("empty ARR_EPISODE_REFER_VO");

							%>

			<logic:notEmpty
				name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>">
				<bean:define id="OPD_OPEN_EPISODES"
					name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>"
					type="hisglobal.vo.EpisodeReferVO[]" />

				<%System.out
										.println("OPD_OPEN_EPISODES.length... "
												+ OPD_OPEN_EPISODES.length);%>

				<his:ContentTag>

					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<%int i = 0;

									%>
						<tr>
							<td width="5%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"></td>

							<td width="20%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="left"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="epicode" /> </font> </b></div></td>


							<td width="30%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="referFromDept" /> </font> </b></div></td>



							<td width="45%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<div align="center"><b> <font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
								key="referToDept" /> </font> </b></div></td>



						</tr>



						<logic:iterate id="ARR_OPD_OPEN_EPISODE_VO"
							name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>">

							<tr>
								<td width="5%" class="tdfonthead">
								<div align="center"><input type="radio"
									name="refferringOPDEpisode"
									onclick="enableDepartment('<%=i++%>');"
									value='<bean:write name="ARR_OPD_OPEN_EPISODE_VO" property="episodeCode" />' /></div>
								</td>
								<td width="20%" class="tdfonthead">
								<div align="left"><bean:write name="ARR_OPD_OPEN_EPISODE_VO"
									property="episodeCode" /><html:hidden
									name="ReferDeptVisitStampFB" property="episodeCode"
									value='<bean:write name="ARR_OPD_OPEN_EPISODE_VO" property="episodeCode" />' />
								</div>
								</td>
								<td width="30%" class="tdfont">
								<div align="center"><bean:write name="ARR_OPD_OPEN_EPISODE_VO"
									property="refFromDepartment" /></div>
								</td>


								<td width="45%" class="tdfont">
								<div align="center"><html:select name="ReferDeptVisitStampFB"
									property="refToDepartmentCode" tabindex="1"
									styleClass="registrationCmb" disabled="true">
									<html:option value="-1">Select Value</html:option>
									<html:options
										collection="<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>"
										property="value" labelProperty="label" />
								</html:select></div>

								</td>

							</tr>
						</logic:iterate>

					</table>
				</his:ContentTag>



			</logic:notEmpty>
		</logic:empty>
		<%--  </his:statusNew>	--%>

		<!--- End ReferInternal ---- Details of all open episodes-->
	</logic:equal>

	<logic:equal name="ReferDeptVisitStampFB" property="isRefferInOut"
		value="<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL%>">
		<logic:equal name="ReferDeptVisitStampFB" property="referringInstType"
			value="O">
			<!-- ReferInstType is Other than GNCTD -->
			<his:ContentTag>
				<table width="100%" cellpadding="1" cellspacing="1">
					<tr>
						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000" size="1"
							face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
							key="referredBy" /> </font></div>
						</td>

						<td width="25%" class="tdfont">
						<div align="left"><html:text name="ReferDeptVisitStampFB"
							styleClass="textboxBig" maxlength="100" property="patRefDoctor"
							onblur="isAlpha(this,'Referred By Doctor')" tabindex="1"
							onkeypress="return validateAlphabetsOnly(event)" tabindex="1" /></div>
						</td>

						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000" size="1"
							face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
							key="referFromDept" /> </font></div>
						</td>

						<td width="25%" class="tdfont">
						<div align="left"><html:text name="ReferDeptVisitStampFB"
							styleClass="textboxBig" maxlength="20"
							property="refFromDepartment"
							onblur="isAlpha(this,'Referred from Department')"
							onkeypress="return validateAlphabetsOnly(event)" tabindex="1" /></div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			<!-- Closing ReferInstType is Other than GNCTD -->
		</logic:equal>
		<logic:equal name="ReferDeptVisitStampFB" property="referringInstType"
			value="G">

			<!-- ReferInstType GNCTD -->
			<his:ContentTag>
				<table width="100%" cellpadding="1" cellspacing="1">
					<tr>
						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#FF0000" size="1"
							face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="referringHospitalCrNo" /> </font></div>
						</td>

						<td width="25" % nowrap class="tdfont"><html:text
							name="ReferDeptVisitStampFB" tabindex="1"
							property="patRefGnctdHospitalCrno" maxlength="13"
							onkeypress="return validateNumeric(event)" styleClass="textbox" />
						</td>
						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000" size="1"
							face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
							key="referredBy" /> </font></div>
						</td>

						<td width="25%" colspan="2" class="tdfont">
						<div align="left"><html:text name="ReferDeptVisitStampFB"
							styleClass="textboxBig" maxlength="100" property="patRefDoctor"
							onblur="isAlpha(this,'Referred By Doctor')" tabindex="1"
							onkeypress="return validateAlphabetsOnly(event)" /></div>
						</td>
					</tr>

					<tr>

						<td width="25%" class="tdfonthead" nowrap>
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
							key="referringHospitalDept" /> </font></div>
						</td>

						<td width="25%" class="tdfont"><html:select
							name="ReferDeptVisitStampFB" tabindex="1"
							property="patRefGnctdHospitalDept" styleClass="registrationCmb">
							<html:option value="-1">Select Value</html:option>
							<html:options
								collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT%>"
								property="value" labelProperty="label" />
						</html:select></td>

						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="referringHospitalDeptUnit" /> </font></div>
						</td>

						<td width="25%" class="tdfont">
						<div align="left"><html:text name="ReferDeptVisitStampFB"
							property="patRefGnctdHospitalDeptUnit" tabindex="1"
							maxlength="15" styleClass="textbox" /></div>
						</td>
					</tr>
				</table>





			</his:ContentTag>
			<!-- Closing ReferInstType is GNCTD -->
		</logic:equal>




	</logic:equal>


	<!-- Dept to which patient is referred -->
	<logic:equal name="ReferDeptVisitStampFB" property="isRefferInOut"
		value="<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL%>">
		<his:ContentTag>
			<table width="100%">
				<tr>
					<td width="25%" class="tdfonthead">
					<div align="right"><font color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referToDept" /> </font></div>
					</td>
					<td width="75%" class="tdfont">
					<div align="left"><html:select name="ReferDeptVisitStampFB"
						tabindex="1" property="refToDepartmentCode"
						styleClass="registrationCmb">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>"
							property="value" labelProperty="label" />
					</html:select></div>
					</td>

				</tr>
			</table>
		</his:ContentTag>
	</logic:equal>


	<logic:equal name="ReferDeptVisitStampFB" property="isRefferInOut"
		value="<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL%>">
		<logic:notEmpty name="<%=RegistrationConfig.ARR_EPISODE_REFER_VO%>">
			<his:ContentTag>
				<table width="100%">
					<tr>
						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#FF0000" size="1"
							face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="referToDept" /> </font></div>
						</td>
						<td width="75%" class="tdfont">
						<div align="left"><html:select name="ReferDeptVisitStampFB"
							tabindex="1" property="refToDepartmentCode"
							styleClass="registrationCmb">
							<html:option value="-1">Select Value</html:option>
							<html:options
								collection="<%=Config.ESSENTIALBO_OPTION_DEPARTMENT%>"
								property="value" labelProperty="label" />
						</html:select></div>
						</td>

					</tr>
				</table>
			</his:ContentTag>
		</logic:notEmpty>
	</logic:equal>



	<!--................end................... code for referred details........... -->


</his:statusTransactionInProcess>
<%}%>

<!-- Button toolbar Started -->


<his:ButtonToolBarTag>

	<his:statusInProcessWithJsp>
		<%varStatus = "InProcess";

				%>
	</his:statusInProcessWithJsp>
	<%if (varStatus.equals("InProcess")) {%>
	<div align="center"><img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"
		style=cursor:pointer
		onkeypress="if(event.keyCode==13)submitFormOnValidate(ValidateReferDeptVisitStamp()&& ValidateDepartment(document.getElementsByName('refToDepartment')[0]),'SAVE');"
		onclick="submitFormOnValidate(ValidateReferDeptVisitStamp()&& ValidateDepartment(document.getElementsByName('refToDepartment')[0]),'SAVE');" />
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
		tabindex="1" style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	<%} else {

				%>
	<div align="center"><img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"
		style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	<%}

			%>
</his:ButtonToolBarTag>

<his:status />

<input type="hidden" name="hmode" value="unspecified"/>
