<!-- 
 /****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	LAB NUMBER CONFIGURATION MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	Lab Patient Mapping
 ## Date of Creation					: 	20-Mar-2015
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
<%@page import="new_investigation.vo.LabNoConfigMasterVO"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
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
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/LabNoConfigMstAddMod.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />

<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<body onload="checktypevalue()">

	<script type="text/javascript">


	function setacceptanceArea()
	{

        //  alert("hi");
		var initializationType="0";
		for(var i=0;i<document.getElementsByName("acceptanceAreawise").length;i++)
		{
			if(document.getElementsByName("acceptanceAreawise")[i].checked==true)
			{
				initializationType=document.getElementsByName("acceptanceAreawise")[i].value;
			} 
		}
     //   alert( initializationType);
		if(initializationType=='1')
		{
			document.getElementById('acceptanceareadiv').style.display="";
		}

		if(initializationType=='0')
		{
			document.getElementById('acceptanceareadiv').style.display="none";
		}
	
	}
	
function checktypevalue(){
	
	var displaydiv=document.getElementById('secondTable');
	var displaydivsecond=document.getElementById('thirdTable');
	if(document.getElementsByName("labNoFormat")[0].value=="-")
		document.getElementsByName("labNoFormat")[0].value="";
	
	if(document.getElementsByName("initializationType")[4].checked==true)	
	{
		displaydiv.style.display='none';
		displaydivsecond.style.display='none';

		                  
	}

	setacceptanceArea();
	
} 

	////////////////

	<%String sysdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>

		function getTest() {
		
			var patientType = document.getElementsByName("patientType")[0].value;

			if (patientType != -1) {
				submitPage('GETTEST');
			} else {
				submitPage('ADD');
			}
		}

		function clearaddForm() {

			document.getElementsByName('patientType')[0].value = "3";
			document.getElementsByName('initializationType')[0].checked = "true";
			document.getElementsByName('labCode')[0].value = "-1";
			document.getElementsByName('testCode')[0].value = "-1";
			document.getElementsByName('seqDigit')[0].value = "";
			document.getElementsByName('labNoFormat')[0].value = "";
			document.getElementsByName('fromSeries')[0].value = "";
			document.getElementsByName('toSeries')[0].value = "";
	<%-- 		document.getElementsByName('initDate')[0].value = "<%=sysdate%>";
			document.getElementsByName('reinitDate')[0].value = ""; --%>
			document.getElementsByName('seriesFormat')[0].value = "";
			document.getElementsByName('labNoFormat')[0].value = "";
			document.getElementsByName('partOne')[0].value = "";
			document.getElementsByName('partTwo')[0].value = "";

			document.getElementsByName("yearFormat")[0].checked=false;
			document.getElementsByName("yearFormat")[1].checked=false;
			document.getElementsByName("monthFormat")[0].checked=false;
			document.getElementsByName("monthFormat")[1].checked=false;
			document.getElementsByName("dateFormat")[0].checked=false;
			document.getElementsByName("dateOrder")[0].value="";
			document.getElementsByName("monthOrder")[0].value="";
			document.getElementsByName("yearOrder")[0].value="";
			document.getElementsByName("sampleAcceptanceAreaCode")[0].value="-1";
			checktypevalue();
			resetInitializationDates();

		}
		
		function cleardownvalues() {
			
			getTest();
			
			
			//document.getElementsByName('acceptanceAreawise')[0].value = "0";
			document.getElementsByName('patientType')[0].value = "3";
			document.getElementsByName('initializationType')[0].checked = "true";
			document.getElementsByName('acceptanceAreawise')[0].checked = "true";
			
			document.getElementsByName('testCode')[0].value = "-1";
			document.getElementsByName('seqDigit')[0].value = "";
			document.getElementsByName('labNoFormat')[0].value = "";
			document.getElementsByName('fromSeries')[0].value = "";
			document.getElementsByName('toSeries')[0].value = "";
		<%-- 	document.getElementsByName('initDate')[0].value ="<%=sysdate%>";
			document.getElementsByName('reinitDate')[0].value = ""; --%>
			document.getElementsByName('seriesFormat')[0].value = "";
			document.getElementsByName('partOne')[0].value = "";
			document.getElementsByName('partTwo')[0].value = "";

			document.getElementsByName("yearFormat")[0].checked=false;
			document.getElementsByName("yearFormat")[1].checked=false;
			document.getElementsByName("monthFormat")[0].checked=false;
			document.getElementsByName("monthFormat")[1].checked=false;
			document.getElementsByName("dateFormat")[0].checked=false;
			document.getElementsByName("dateOrder")[0].value="";
			document.getElementsByName("monthOrder")[0].value="";
			document.getElementsByName("yearOrder")[0].value="";
			alert(document.getElementsByName('acceptanceAreawise')[0].value);			

		}
function reset(){
	
	
			var displaydiv=document.getElementById('secondTable');
			if(document.getElementsByName("hmode")[0].value=="MODIFY")
			{		displaydiv.style.display='';
	}
	
			document.getElementsByName('labNoFormat')[0].value = "";
			document.getElementsByName('partOne')[0].value = "";
			document.getElementsByName('partTwo')[0].value = "";
			document.getElementsByName("yearFormat")[0].checked=false;
			document.getElementsByName("yearFormat")[1].checked=false;
			document.getElementsByName("monthFormat")[0].checked=false;
			document.getElementsByName("monthFormat")[1].checked=false;
			document.getElementsByName("dateFormat")[0].checked=false;
			document.getElementsByName("dateOrder")[0].value="";
			document.getElementsByName("monthOrder")[0].value="";
			document.getElementsByName("yearOrder")[0].value="";
			document.getElementsByName('seqDigit')[0].value = "";
			document.getElementsByName('labNoFormat')[0].value = "";
			document.getElementsByName('fromSeries')[0].value = "";
			document.getElementsByName('toSeries')[0].value = "";
			document.getElementsByName('seriesFormat')[0].value = "";


}

function resetDate()
{				document.getElementsByName('labNoFormat')[0].value = "";
	document.getElementsByName('partOne')[0].value = "";
	document.getElementsByName('partTwo')[0].value = "";
	document.getElementsByName("yearFormat")[0].checked=false;
	document.getElementsByName("yearFormat")[1].checked=false;
	document.getElementsByName("monthFormat")[0].checked=false;
	document.getElementsByName("monthFormat")[1].checked=false;
	document.getElementsByName("dateFormat")[0].checked=false;
	document.getElementsByName("dateOrder")[0].value="";
	document.getElementsByName("monthOrder")[0].value="";
	document.getElementsByName("yearOrder")[0].value="";
}









		function validateOnSave() {
			valid = false;

			if (isEmpty(document.forms[0].collectionareaName,"collectionareaName")) {
				valid = true;
			}
			return valid;
		}
	</script>

	<html:form action="/masters/LabNoConfigMstACTION">
		<html:hidden name="LabNoConfigMstFB" property="hmode" />
		<html:hidden name="LabNoConfigMstFB" property="selectedChk" />
		<html:hidden name="LabNoConfigMstFB" property="seqNo" />
		<html:hidden name="LabNoConfigMstFB" property="dateOrder" />
		<html:hidden name="LabNoConfigMstFB" property="monthOrder" />
		<html:hidden name="LabNoConfigMstFB" property="yearOrder" />
		<html:hidden name="LabNoConfigMstFB" property="initDate" />
		<html:hidden name="LabNoConfigMstFB" property="reinitDate" />
		<html:hidden name="LabNoConfigMstFB" property="labNoFormat" />



		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="LabNoConfigMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>


		<!-- Logic for ADD Page -->


		<his:TransactionContainer>
			<his:TitleTag name="Lab Number Configuration Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>



				<table width="100%" border="0" cellspacing="1" cellpadding="0">




					<!-- Lab Combo -->
					<logic:notEqual name="LabNoConfigMstFB" property="hmode"
						value="MODIFY">
						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="LaboratoryName" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
									<div align="left">

										<html:select name="LabNoConfigMstFB" property="labCode"
											tabindex="1" style="width:200px;"
											onchange="cleardownvalues();">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_LAB_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>




						<!-- Patient Type -->

						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="PatientType" />&nbsp;</b>
									</font>
								</div>
							</td>

							<td class='tdfont' colspan="2">
								<div align="left">
									<html:select name="LabNoConfigMstFB" property="patientType"
										style="width:200px" onchange="getTest();">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">IPD</html:option>
										<html:option value="2">OPD</html:option>
										<html:option value="3">Both IPD/OPD</html:option>

									</html:select>
								</div>
							</td>
						</tr>


						<!-- Test Combo -->



						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="testCombo" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_TEST_COMBO %>">
									<div align="left">

										<html:select name="LabNoConfigMstFB" property="testCode"
											tabindex="1" style="width:200px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_TEST_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>

								</logic:present></td>
						</tr>
						
						
							<!-- added by chandan -->
						
							<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b> Is Acceptance Area Wise&nbsp;</b>
								</font>
							</div>
						</td>


						<td width="50%" class="tdfont">
							<div align="left">
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="acceptanceAreawise" value="1"
									onchange="setacceptanceArea()"></html:radio>
								Yes
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="acceptanceAreawise" value="0"
									onchange="setacceptanceArea()"></html:radio>
								No
								

							</div>
						</td>
					</tr>
					
						<tr id="acceptanceareadiv" style="display: none;">


							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b>
										Acceptance Area &nbsp;</b>
									</font>
								</div>
							</td>


							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
									<div align="left">

										<html:select name="LabNoConfigMstFB" property="sampleAcceptanceAreaCode"
											tabindex="1" style="width:200px;"
											>
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>
						
						<!-- Initialization Type -->

					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="InitializationType" />&nbsp;</b>
								</font>
							</div>
						</td>


						<td width="50%" class="tdfont">
							<div align="left">
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="initializationType" value="d"
									onchange="resetInitializationDates()"></html:radio>
								<bean:message key="daily" />
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="initializationType" value="w"
									onchange="resetInitializationDates()"></html:radio>
								<bean:message key="weekly" />
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="initializationType" value="m"
									onchange="resetInitializationDates()"></html:radio>
								<bean:message key="monthly" />

							</div>
						</td>
					</tr>

					<tr>
						<td width="50%" class="tdfont"></td>
						<td width="50%" class="tdfont">
							<div align="left">

								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="initializationType" value="y"
									onchange="resetInitializationDates()"></html:radio>
								<bean:message key="yearly" />
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="initializationType" value="x"
									onchange="resetInitializationDates()"></html:radio>
								<bean:message key="SameasSampleNumber" />
							</div>
						</td>

					</tr>
						

					</logic:notEqual>

					<logic:equal name="LabNoConfigMstFB" property="hmode"
						value="MODIFY">
						
						
						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="LaboratoryName" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
									<div align="left">

										<html:select name="LabNoConfigMstFB" property="labCode"
											tabindex="1" disabled="true" style="width:200px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_LAB_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
										<html:hidden name="LabNoConfigMstFB" property="labCode" />

									</div>
								</logic:present></td>
						</tr>




						<!-- Patient Type -->

						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="PatientType" />&nbsp;</b>
									</font>
								</div>
							</td>

							<td class='tdfont' colspan="2">
								<div align="left">
									<html:select name="LabNoConfigMstFB" property="patientType"
										style="width:200px" onchange="getTest();" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">IPD</html:option>
										<html:option value="2">OPD</html:option>
										<html:option value="3">Both IPD/OPD</html:option>

									</html:select>
									<html:hidden name="LabNoConfigMstFB" property="patientType" />

								</div>
							</td>
						</tr>


						<!-- Test Combo -->



						<tr>

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="testCombo" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_TEST_COMBO %>">
									<div align="left">

										<html:select name="LabNoConfigMstFB" property="testCode"
											tabindex="1" disabled="true" style="width:200px;">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_TEST_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
										<html:hidden name="LabNoConfigMstFB" property="testCode" />

									</div>

								</logic:present></td>
						</tr>
						
						<!-- added by chandan -->
						
							<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b> Is Acceptance Area Wise&nbsp;</b>
								</font>
							</div>
						</td>


						<td width="50%" class="tdfont">
							<div align="left" >
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="acceptanceAreawise" value="1"
									onchange="setacceptanceArea()" disabled="true"></html:radio>
								Yes
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="acceptanceAreawise"  value="0"
									onchange="setacceptanceArea()" disabled="true"></html:radio>
								No
								

							</div>
						</td>
					</tr>
					
						<tr id="acceptanceareadiv" style="display: none;">

							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b>
										Acceptance Area &nbsp;</b>
									</font>
								</div>
							</td>


							<td width="50%" class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
									<div align="left">

										<html:select name="LabNoConfigMstFB" property="sampleAcceptanceAreaCode"
											tabindex="1"  style="width:200px;" disabled="true"
											>
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>
						
						<!-- Initialization Type -->

					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="InitializationType" />&nbsp;</b>
								</font>
							</div>
						</td>


						<td width="50%" class="tdfont">
							<div align="left">
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="initializationType" value="d"
									onchange="resetType()"></html:radio>
								<bean:message key="daily" />
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="initializationType" value="w"
									onchange="resetType()"></html:radio>
								<bean:message key="weekly" />
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="initializationType" value="m"
									onchange="resetType()"></html:radio>
								<bean:message key="monthly" />

							</div>
						</td>
					</tr>

					<tr>
						<td width="50%" class="tdfont"></td>
						<td width="50%" class="tdfont">
							<div align="left">

								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="initializationType" value="y"
									onchange="resetType()"></html:radio>
								<bean:message key="yearly" />
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="initializationType" value="x"
									onchange="resetType()"></html:radio>
								<bean:message key="SameasSampleNumber" />
							</div>
						</td>

					</tr>
						

					</logic:equal>
					

				</table>


				<table width="100%" style="display: ''" id="secondTable" border="0"
					cellspacing="1" cellpadding="0">


					<!-- Lab no format-->



					<tr>
						<td class="HEADER" width='100%' colspan="6"
							style="font-size: 14px; font-weight: bold;"><b>Date Format</b></td>
					</tr>

					<!-- YEAR FORMAT -->

					<tr>

						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="yearFormat" />&nbsp;</b>
								</font>
							</div>
						</td>




						<td width="50%" class="tdfont">
							<div align="left">
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="yearFormat" value="1" onclick="setYear()"></html:radio>
								<bean:message key="YY" />
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="yearFormat" value="2" onclick="setYear()"></html:radio>
								<bean:message key="YYYY" />

							</div>
						</td>

					</tr>





					<!-- MONTH FORMAT -->

					<tr>

						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="monthFormat" />&nbsp;</b>
								</font>
							</div>
						</td>




						<td width="50%" class="tdfont">
							<div align="left">
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="monthFormat" value="1" onclick="setMonth()"></html:radio>
								<bean:message key="MM" />
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="monthFormat" value="2" onclick="setMonth()"></html:radio>
								<bean:message key="Mon" />

							</div>
						</td>

					</tr>

					<!-- DATE FORMAT -->

					<tr>

						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="dateFormat" />&nbsp;</b>
								</font>
							</div>
						</td>




						<td width="50%" class="tdfont">
							<div align="left">
								<html:radio name="LabNoConfigMstFB" tabindex="1"
									property="dateFormat" value="1" onclick="setDate()"></html:radio>
								<bean:message key="DD" />


							</div>
						</td>

					</tr>

					<!-- part one -->
					
					<tr>

						<td width="50%" class="tdfonthead">
							<div align="right">
								 <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b>Date Format</b>
								</font>
							</div>
						</td>

						<td width="40%" class="tdfont">
							<div align="left">

								<html:text name="LabNoConfigMstFB" size="30"
									property="partOne" readonly="true"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event)" ></html:text>

								<img src="/HISInvestigationG5/hisglobal/images/redo.gif" class="link"
									onClick='resetDate()' /> <font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">
										<b><bean:message
											key="resetFormat" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
											 
								</font>
							</div>
						</td>




					</tr>



					<!--Series Format -->
					
					<tr>
						<td class="HEADER" width='100%' colspan="6"
							style="font-size: 14px; font-weight: bold;"><b>Series Format</b></td>
					</tr>
					
					
					

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="seriesFormat" />&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="LabNoConfigMstFB" property="seriesFormat"
									maxlength="3" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphaNumericOnly(event,this)"
									tabindex="1" onchange="maxlength()">
								</html:text>
							</div>
						</td>
					</tr>



					<!-- No. of Sequence Digits -->

					<tr>

						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="SeqDigit" />&nbsp;</b>
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="LabNoConfigMstFB" property="seqDigit"
									maxlength="1" size="30" readonly="<%=this.readOnly = false%>"
									onkeypress="return validateNumeric(event)" tabindex="1"
									onchange="maxlength()">
								</html:text>
								<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b>Max Value=6</b>
								</font>
							</div>
						</td>
					</tr>

				</table>
				<table width="100%" style="display: ''" id="thirdTable" border="0"
					cellspacing="1" cellpadding="0">


					<!-- Lab no format-->
					
							<tr>
						<td class="HEADER" width='100%' colspan="6"
							style="font-size: 14px; font-weight: bold;"><b>Lab Number Format</b></td>
					</tr>

					<tr>

						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="LabNoFormat" />&nbsp;</b>
								</font>
							</div>
						</td>

						<td width="40%" class="tdfont">
							<div align="left">

								<html:text name="LabNoConfigMstFB" size="30"
									property="partTwo" readonly="true"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event)"></html:text>

								<img src="/HISInvestigationG5/hisglobal/images/redo.gif" class="link"
									onClick='reset()' /> <font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">
										<b><bean:message
											key="resetFormat" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
											 <b><bean:message
											key="digitX" />&nbsp;</b>
								</font>
							</div>
						</td>




					</tr>

					<!-- from series -->
					<tr>

						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="fromSeries" />&nbsp;</b>
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="LabNoConfigMstFB" property="fromSeries"
									size="30" readonly="<%=this.readOnly = false%>"
									onkeypress="return validateNumeric(event)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>


					<!-- to series -->
					<tr>

						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="toSeries" />&nbsp;</b>
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="LabNoConfigMstFB" property="toSeries" size="30"
									readonly="<%=this.readOnly = false%>"
									onkeypress="return validateNumeric(event)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<%-- <!-- Initialization Date -->
						
					<tr>
					<td class="HEADER" width='100%' colspan="6" style="font-size: 14px; font-weight: bold;">
					 <b>Date Selection</b>
					</td>
					</tr>
			
					
						<tr>
					<td class='tdfonthead'  width="50%">
					<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="initDate" />&nbsp;</b>
								</font>
							</div>
							
							
						<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' align="left">	
	    		<logic:equal name="LabNoConfigMstFB" property="hmode" value="ADD">  
					<his:date name='initDate' dateFormate="%d-%b-%Y" value="<%=sysdate%>" onchange="resetReInitializationDate()"/>
					</logic:equal>
	    		<logic:equal name="LabNoConfigMstFB" property="hmode" value="MODIFY">  
	    		<bean:define id="iniDate" name="LabNoConfigMstFB" property="initDate" type="java.lang.String"></bean:define>
					<his:date name='initDate' dateFormate="%d-%b-%Y" value="<%=iniDate%>" onchange="resetReInitializationDate()"/>
					</logic:equal>
				</div>
		 		
			</td>
				</tr>
				
				<!--RE Initialization Date -->
					
						<tr>
					<td class='tdfonthead'  width="50%">
					<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="reinitDate" />&nbsp;</b>
								</font>
							</div>
							
							
							
					<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' align="left">	
	    		<logic:equal name="LabNoConfigMstFB" property="hmode" value="ADD">  
					<his:date name='reinitDate' dateFormate="%d-%b-%Y" />
					</logic:equal>
	    		<logic:equal name="LabNoConfigMstFB" property="hmode" value="MODIFY">  
	    		<bean:define id="reiniDate" name="LabNoConfigMstFB" property="reinitDate" type="java.lang.String"></bean:define>
					<his:date name='reinitDate' dateFormate="%d-%b-%Y" value="<%=reiniDate%>" />
					</logic:equal>
				</div>
		 		
			</td>
				</tr>
					 --%>
				</table>
			</his:ContentTag>



			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="LabNoConfigMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="LabNoConfigMstFB" property="hmode"
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
					</logic:notEqual> <logic:equal name="LabNoConfigMstFB" property="hmode"
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
