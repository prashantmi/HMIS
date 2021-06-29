<!-- 
 /****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	TEST SAMPLE MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	This master is used to define the Samples for test
 ## Date of Creation					: 	24-Feb-2015
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

<his:javascript src="/HIS/WebContent/hisglobal/js/masterutil/master.js" />

<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/InvTestSampleMstAddMod.js" />
<body>
	

<script type="text/javascript">
	

		function clearaddForm() {

			document.getElementsByName('sampleCode')[0].value = "-1";
			document.getElementsByName('uomCode')[0].value = "-1";
			document.getElementsByName('containerCode')[0].value = "-1";
			document.getElementsByName('defaultSample')[0].checked = "true";
			document.getElementsByName('sampleQuantity')[0].value = "";

		}

		function validateOnSave() {
			valid = false;

			if (isEmpty(document.forms[0].sampleQuantity, "sampleQuantity")) {
				valid = true;
			}
			return valid;
		}

		

		
</script>

		<html:form action="/masters/InvTestSampleMstACTION">
		<html:hidden name="InvTestSampleMstFB" property="hmode" />
		<html:hidden name="InvTestSampleMstFB" property="selectedChk" />
		<html:hidden name="InvTestSampleMstFB" property="testCode" />
				<html:hidden name="InvTestSampleMstFB" property="defaultTrue" />
		


		<%!boolean readOnly;%>
		<%
			this.readOnly = false;
		%>

		<logic:equal name="InvTestSampleMstFB" property="hmode" value="VIEW">
			<%
				this.readOnly = true;
			%>
		</logic:equal>


			<!-- Logic for ADD Page -->

	<his:TransactionContainer>

<logic:equal name="InvTestSampleMstFB" property="hmode" value="ADD">


				<his:TitleTag name="Test Sample Master">
					<%-- <his:insertDateTag /> --%>
				</his:TitleTag>
				<his:ContentTag>

					<table width="100%" border="0" cellspacing="1" cellpadding="0">
					


						<!-- TEST Combo -->

						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="testCombo" />&nbsp;</b>
									</font>
								</div>
							</td>


							
							<td width="50%" class="tdfont">
								<div align="left">
								<html:text name="InvTestSampleMstFB" property="testName"
									size="30" readonly="<%=this.readOnly=true %>"
									onkeypress="return validateAlphaNumericOnly(event,this)"
									tabindex="1">
								</html:text>
								</div>
								</td>
						</tr>



						<!-- Sample Combo -->

						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="sampleCombo" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
									<div align="left">

										<html:select name="InvTestSampleMstFB" property="sampleCode"
											tabindex="1" style="width:199px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>


						<!-- Sample Quantity -->

						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="testsampleQuantity" />&nbsp;</b>
									</font>
								</div>
							</td>

							<td width="50%" class="tdfont">
								<div align="left">
									<html:text name="InvTestSampleMstFB" property="sampleQuantity"
										maxlength="3" size="30" readonly="<%=this.readOnly = false%>"
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

										<html:select name="InvTestSampleMstFB" property="uomCode"
											tabindex="1" style="width:199px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_UOM_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>



						<!-- Container Combo -->

						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="containerCombo" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_CONTAINER_COMBO%>">
									<div align="left">

										<html:select name="InvTestSampleMstFB"
											property="containerCode" tabindex="1" style="width:199px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_CONTAINER_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>



						<!-- Is Default Sample -->

						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="isDefaultSample" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width="50%" class="tdfont">
								<div align="left">
									<html:radio name="InvTestSampleMstFB" tabindex="1"
										property="defaultSample" value="0"></html:radio>
									<bean:message key="No" />
									<html:radio name="InvTestSampleMstFB" tabindex="1"
										property="defaultSample" value="1"></html:radio>
									<bean:message key="Yes" />
								</div>
							</td>
						</tr>


					</table>
				</his:ContentTag>

			
</logic:equal>			
			
			
			<!-- Logic for MODIFY Page -->




			<logic:equal name="InvTestSampleMstFB" property="hmode"	value="MODIFY">

			<his:TitleTag name="Test Sample Master">
					<his:insertDateTag />
				</his:TitleTag>
				
				<his:ContentTag>


					<table width="100%" border="0" cellspacing="1" cellpadding="0">

						<!-- TEST Combo -->

						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="testCombo" />&nbsp;</b>
									</font>
								</div>
							</td>
							
							<td width="50%" class="tdfont">
								<div align="left">
								<html:text name="InvTestSampleMstFB" property="testName"
									size="30" readonly="<%=this.readOnly=true %>"
									onkeypress="return validateAlphaNumericOnly(event,this)"
									tabindex="1">
								</html:text>
								</div>
								</td>
						</tr>



						<!-- Sample Combo -->

						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="sampleCombo" />&nbsp;</b>
									</font>
								</div>
							</td>


						<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>">
									<div align="left">

										<html:select name="InvTestSampleMstFB" property="sampleCode"
											tabindex="1" disabled="true" style="width:199px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
												<html:hidden name="InvTestSampleMstFB" property="sampleCode" />
										
									</div>
								</logic:present></td>
						</tr>


						<!-- Sample Quantity -->

						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="testsampleQuantity" />&nbsp;</b>
									</font>
								</div>
							</td>

							<td width="50%" class="tdfont">
								<div align="left">
									<html:text name="InvTestSampleMstFB" property="sampleQuantity"
										maxlength="3" size="30" readonly="<%=this.readOnly = false%>"
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

										<html:select name="InvTestSampleMstFB" property="uomCode"
											tabindex="1" style="width:199px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_UOM_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>



						<!-- Container Combo -->

						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="containerCombo" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_CONTAINER_COMBO%>">
									<div align="left">

										<html:select name="InvTestSampleMstFB"
											property="containerCode" tabindex="1" style="width:199px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_CONTAINER_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>



						<!-- Is Default Sample -->

						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="isDefaultSample" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width="50%" class="tdfont">
								<div align="left">
									<html:radio name="InvTestSampleMstFB" tabindex="1"
										property="defaultSample" value="0"></html:radio>
									<bean:message key="No" />
									<html:radio name="InvTestSampleMstFB" tabindex="1"
										property="defaultSample" value="1"></html:radio>
									<bean:message key="Yes" />
								</div>
							</td>
						</tr>




						
						
			</table>
		`
			</his:ContentTag>
			</logic:equal>





			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="InvTestSampleMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="InvTestSampleMstFB" property="hmode"
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
					</logic:notEqual> <logic:equal name="InvTestSampleMstFB" property="hmode"
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
