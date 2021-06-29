<!-- /****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
 ## Name of Developer		 			:	Yogender Yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	Global LAB Macro Mapping MASTER
 ## Purpose								:	This master is used to define the Global lab macro mapping master
 ## Date of Creation					: 	17-MAR-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/**************************************************************************************************************/  -->


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.LabMacroMapMasterVO"%>
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
<his:javascript src="/new_investigation/js/LabMacroGlobalMapMstAddMod.js" />
<body>

	<script type="text/javascript">
		function clearaddForm() {

			document.getElementsByName('labCode')[0].value = "-1";
			document.getElementsByName("mappedList")[0].length = 0;
			document.getElementsByName("unmappedList")[0].length = 0;

		}

		function validateOnSave() {
			valid = false;

			if (isEmpty(document.forms[0].laboratoryName, "laboratoryName")) {
				valid = true;
			}
			return valid;
		}

		function getarea() {
			if (document.forms[0].labCode.value == "-1") {
				document.forms[0].hmode.value = "ADD";
				document.forms[0].submit();
			} else {
				document.forms[0].hmode.value = "GETMACRO";
				document.forms[0].submit();
			}
		}
	</script>

	<html:form action="/masters/LabMacroGlobalMapMstACTION">
		<html:hidden name="LabMacroMapMstFB" property="hmode" />
		<html:hidden name="LabMacroMapMstFB" property="selectedChk" />
		



		<%!boolean readOnly;%>
		<%
			this.readOnly = false;
		%>

		<logic:equal name="LabMacroMapMstFB" property="hmode" value="VIEW">
			<%
				this.readOnly = true;
			%>
		</logic:equal>


		<!-- Logic for ADD Page -->


		<his:TransactionContainer>
			<his:TitleTag name="Global Lab Macro Mapping  Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>



				<table width='100%' border="0" cellspacing="1" cellpadding="0">
					<logic:notEqual name="LabMacroMapMstFB" property="hmode"
						value="MODIFY">

						<!-- Lab Combo -->

						<tr>

							<td width='50%' class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="LaboratoryName" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width='50%' class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
									<div align="left">

										<html:select name="LabMacroMapMstFB" property="labCode"
											tabindex="1" onchange="getarea();">

											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>
					</logic:notEqual>

					<logic:equal name="LabMacroMapMstFB" property="hmode"
						value="MODIFY">
						<tr>

							<td width='50%' class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="LaboratoryName" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width='50%' class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_LAB_COMBO%>">
									<div align="left">

										<html:select name="LabMacroMapMstFB" property="labCode"
											tabindex="1" disabled="true">

											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_LAB_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
										<html:hidden name="LabMacroMapMstFB" property="labCode" />
									</div>
								</logic:present></td>
						</tr>
					</logic:equal>
				</table>
				<!-- list name -->
				<%-- 	<logic:present name="not required">		 --%>

				<table width='100%' border="0" cellspacing="1" cellpadding="0">

					<tr>

						<td width='40%' class="tdfonthead">
							<div align="center">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="UnMappedMacroName" />&nbsp;</b>
								</font>
							</div>
						</td>

						<td width='20%' class="tdfonthead"></td>

						<td width='40%' class="tdfonthead">
							<div align="center">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="MappedMacroName" />&nbsp;</b>
								</font>
							</div>
						</td>
					</tr>

					<!--left list values -->



					<tr>
						<td width='40%' class="tdfont">

							<div align="center">
								<logic:notEqual name="LabMacroMapMstFB" property="hmode"
									value="GETMACRO">
									<logic:notEqual name="LabMacroMapMstFB" property="hmode"
										value="MODIFY">
										<html:select name="LabMacroMapMstFB"
											property="laboratorymacroCode" size="5" tabindex="1"
											style="width:200px">
										</html:select>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal name="LabMacroMapMstFB" property="hmode"
									value="GETMACRO">
									<logic:present name="<%=InvestigationConfig.LIST_MACRO_COMBO%>">
										<html:select name="LabMacroMapMstFB" property="unmappedList"
											size="5" tabindex="1" style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_MACRO_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
								<logic:equal name="LabMacroMapMstFB" property="hmode"
									value="MODIFY">
									<logic:present name="<%=InvestigationConfig.LIST_MACRO_COMBO%>">
										<html:select name="LabMacroMapMstFB" property="unmappedList"
											size="5" tabindex="1" style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_MACRO_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
							</div>
						</td>

						<!-- images -->

						<td width="20%" class="tdfont">
							<div align="center">
								<img src="/HISInvestigationG5/hisglobal/images/forward3.gif" class="link"
									onClick='moveRightSingle();' />	&nbsp;&nbsp;
								<img src="/HISInvestigationG5/hisglobal/images/forwardward.gif"   class="link"  onClick='moveRightAll();'/>
								<br><br>
								<img src="/HISInvestigationG5/hisglobal/images/back3.gif"   class="link"  onClick='moveLeftSingle();'/> 		&nbsp;&nbsp;
								<img src="/HISInvestigationG5/hisglobal/images/backward.gif"   class="link"  onClick='moveLeftAll();'/>
							</div>
						</td>

						<!--right list values -->


						<td width='40%' class="tdfont">

							<div align="center">
								<logic:notEqual name="LabMacroMapMstFB" property="hmode"
									value="GETMACRO">
									<logic:notEqual name="LabMacroMapMstFB" property="hmode"
										value="MODIFY">

										<html:select name="LabMacroMapMstFB"
											property="laboratorymacroCode" size="5" tabindex="1"
											style="width:200px">
										</html:select>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal name="LabMacroMapMstFB" property="hmode"
									value="GETMACRO">
									<logic:present
										name="<%=InvestigationConfig.LIST_SELECTED_MACRO_COMBO%>">
										<html:select name="LabMacroMapMstFB" property="mappedList"
											size="5" tabindex="1" multiple="true" style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_SELECTED_MACRO_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
								<logic:equal name="LabMacroMapMstFB" property="hmode"
									value="MODIFY">
									<logic:present
										name="<%=InvestigationConfig.LIST_SELECTED_MACRO_COMBO%>">
										<html:select name="LabMacroMapMstFB" property="mappedList"
											size="5" tabindex="1" multiple="true" style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_SELECTED_MACRO_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
							</div>
						</td>



					</tr>
				</table>

			</his:ContentTag>



			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="LabMacroMapMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="LabMacroMapMstFB" property="hmode"
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
					</logic:notEqual> <logic:equal name="LabMacroMapMstFB" property="hmode"
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
