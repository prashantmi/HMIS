<!-- 
 /****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	CONTAINER MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	This master is used to define the Containers in Investigation Module
 ## Date of Creation					: 	18-Feb-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/**************************************************************************************************************/ 
-->



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.MandatoryComboMasterVO"%>
<%@page import="java.util.*"%>
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
<his:javascript src="/new_investigation/js/ContainerMstAddMod.js" />
<body>
	<script type="text/javascript">
		function validateOnSave() {
			valid = false;

			if (isEmpty(document.forms[0].containerName, "containerName")) {
				valid = true;
			}
			return valid;
		}

		function clearaddForm() {

			document.getElementsByName('containerName')[0].value = "";
			document.getElementsByName('containerVolume')[0].value = "";
			document.getElementsByName('uomCode')[0].value = "-1";
			document.getElementsByName('remarks')[0].value = "";

		}

		function clearForm() {

			submitPage('CLEAR');

		}
	</script>

	<html:form action="/masters/ContainerMstACTION">
		<html:hidden name="ContainerMstFB" property="hmode" />
		<html:hidden name="ContainerMstFB" property="containerCode" />
		<html:hidden name="ContainerMstFB" property="selectedChk" />



		<%!boolean readOnly;%>
		<%
			this.readOnly = false;
		%>

		<logic:equal name="ContainerMstFB" property="hmode" value="VIEW">
			<%
				this.readOnly = true;
			%>
		</logic:equal>


		<!-- Logic for ADD Page -->


		<his:TransactionContainer>
			<his:TitleTag name="Container Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>



				<table width="100%" border="0" cellspacing="1" cellpadding="0">

					<!-- Container Name -->

					<tr>

						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="containerName" />&nbsp;</b>
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="ContainerMstFB" property="containerName"
									maxlength="50" size="30" readonly="<%=this.readOnly = false%>"
									onkeypress="return validateAlphaNumericOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<!-- Container Volume -->

					<tr>

						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="ContainerVolume" />&nbsp;</b>
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="ContainerMstFB" property="containerVolume"
									maxlength="2" size="30" readonly="<%=this.readOnly = false%>"
									onkeypress="return validateNumeric(event)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<!-- Unit of Measurement Combo -->

					<tr>

						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="uomCombo" />&nbsp;</b>
								</font>
							</div>
						</td>


						<td width="50%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_UOM_COMBO%>">
								<div align="left">

									<html:select name="ContainerMstFB" property="uomCode" style="width : 65%"
										tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_UOM_COMBO%>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
					</tr>


					<!-- REMARKS -->

					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="remarks" />&nbsp;</b>
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:textarea name="ContainerMstFB" property="remarks" cols="28"
									tabindex="1" readonly="<%=this.readOnly%>"
									onkeypress="return CheckMaxLength(event,this,50,1)">
								</html:textarea>
							</div>
						</td>
					</tr>



				</table>
		`
</his:ContentTag>



			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="ContainerMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="ContainerMstFB" property="hmode"
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
					</logic:notEqual> <logic:equal name="ContainerMstFB" property="hmode" value="MODIFY">
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
