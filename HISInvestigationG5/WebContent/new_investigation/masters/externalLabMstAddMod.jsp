<!--
 /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	EXTERNAL LAB MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	This master is used to capture the external laboratory
 ## Date of Creation					: 	07-Dec-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/
 -->





<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/invParMstAddMod.js" />
<body>


	<script type="text/javascript">
		function validateOnSave() {
			valid = false;

			if (isEmpty(document.forms[0].labName, "labName")) {
				valid = true;
			}
			return valid;
		}

		function clearaddForm() {

			document.getElementsByName('labName')[0].value = "";
			document.getElementsByName('remarks')[0].value = "";

		}

		function clearForm() {

			submitPage('CLEAR');

		}
	</script>

	<html:form action="/masters/externalLabMstACTION">

		<html:hidden name="externalLabMstFB" property="hmode" />
		<html:hidden name="externalLabMstFB" property="labCode" />
		<html:hidden name="externalLabMstFB" property="selectedChk" />


		<%!boolean readOnly;%>
		<%
			this.readOnly = false;
		%>

		<his:TransactionContainer>
			<his:TitleTag name="External Lab Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>

				<table width="100%" border="0" cellspacing="1" cellpadding="0">


					<!-- laboratory Name -->
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LaboratoryName" />&nbsp;
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="externalLabMstFB" property="labName"
									style="width=62%" maxlength="50" size="30"
									readonly="<%=this.readOnly%>"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>


					<!-- laboratory short Name -->
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabShortShortName" />&nbsp;
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="externalLabMstFB" property="labShortName"
									style="width=62%" maxlength="20" size="30"
									readonly="<%=this.readOnly%>"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>


					<!-- Address 1 -->


					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="add1" />&nbsp;
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:textarea name="externalLabMstFB" property="address1"
									cols="28" tabindex="1" readonly="<%=this.readOnly%>"
									onkeypress="return CheckMaxLength(event,this,500,1)">
								</html:textarea>
							</div>
						</td>
					</tr>

					<!-- Address 2 -->


					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="add2" />&nbsp;
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:textarea name="externalLabMstFB" property="address2"
									cols="28" tabindex="1" readonly="<%=this.readOnly%>"
									onkeypress="return CheckMaxLength(event,this,500,1)">
								</html:textarea>
							</div>
						</td>
					</tr>

					<!-- city Name -->
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="city" />&nbsp;
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="externalLabMstFB" property="cityName"
									style="width=62%" maxlength="35" size="30"
									readonly="<%=this.readOnly%>"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>


					<!-- state  -->

					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="state" />&nbsp;</b>
								</font>
							</div>
						</td>




						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_STATE_COMBO%>">
								<div align="left">

									<html:select name="externalLabMstFB" property="stateCode"
										tabindex="1" style="width:58%;">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_STATE_COMBO%>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>



					</tr>



					<!-- contact person Name -->
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="contactPerson" />&nbsp;
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="externalLabMstFB" property="contactPerson"
									style="width=62%" maxlength="50" size="30"
									readonly="<%=this.readOnly%>"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>



					<!-- phone -->
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="phone" />&nbsp;
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="externalLabMstFB" property="phone"
									style="width=62%" maxlength="15" size="30"
									readonly="<%=this.readOnly%>"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<!-- fax -->
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="fax" />&nbsp;
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="externalLabMstFB" property="fax"
									style="width=62%" maxlength="15" size="30"
									readonly="<%=this.readOnly%>"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<!-- email -->
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="email" />&nbsp;
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="externalLabMstFB" property="email"
									style="width=62%" maxlength="35" size="30"
									readonly="<%=this.readOnly%>"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>








				</table>
			</his:ContentTag>

			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="externalLabMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="externalLabMstFB" property="hmode"
							value="VIEW">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-sv.png"/>'
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
								onclick="finalSubmit('SAVE')" tabindex="1">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-clr.png"/>'
								style="cursor: pointer" onclick="clearaddForm()"
								onkeypress="if(event.keyCode==13) clearaddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> <logic:equal name="externalLabMstFB" property="hmode"
						value="MODIFY">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-mo.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')"
							onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onclick="clearForm()"
							onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
					</logic:equal> <img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
					style="cursor: pointer" onclick="submitForm('CANCEL')"
					onkeypress="if(event.keyCode==13) submitForm('CANCEL')"
					tabindex="1">
				</span>
			</his:ButtonToolBarTag>
			<his:status />
		</his:TransactionContainer>



	</html:form>
</body>
</html>