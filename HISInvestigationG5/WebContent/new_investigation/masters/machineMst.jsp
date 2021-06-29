<!--
 /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	MACHINE MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	
 ## Date of Creation					: 	16-Oct-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*********************************************************************************************************************/
 --->



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="new_investigation.vo.machineMstVO"%>
<%@page import="java.util.*"%>

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

			if (isEmpty(document.forms[0].machineName, "machineName")) {
				valid = true;
			}
			return valid;
		}

		function clearaddForm() {

			document.getElementsByName('machineName')[0].value = "";
			document.getElementsByName('remarks')[0].value = "";

		}

		function clearForm() {

			submitPage('CLEAR');

		}
		
		
		//default values
		function setDefaultValueForConfiguration(i)
{
	//alert(i);

	/* if(checkCommPortValidation(i)==false){
		//document.forms[0].strDefaultMode[i].checked=false;
		document.getElementsByName("defaultMode")[i].value="0";
		return;
	} */
	
	
		if(document.getElementsByName("defaultMode")[i].value=="1"){
			document.getElementsByName("defaultMode")[i].value="1";
			document.getElementsByName("baudRate")[i].value="7";
			document.getElementsByName("byteSize")[i].value="2";
			document.getElementsByName("parity")[i].value="0";
			document.getElementsByName("stopBit")[i].value="1";
			document.getElementsByName("readTimeOut")[i].value="1";
			document.getElementsByName("writeTimeOut")[i].value="1";
			document.getElementsByName("flowControl")[i].value="0";
			document.getElementsByName("flowParity")[i].value="0";
		}
		else
			{
			document.getElementsByName("defaultMode")[i].value="0";
				document.getElementsByName("baudRate")[i].value="-1";
			document.getElementsByName("byteSize")[i].value="-1";
			document.getElementsByName("parity")[i].value="-1";
			document.getElementsByName("stopBit")[i].value="-1";
			document.getElementsByName("readTimeOut")[i].value="-1";
			document.getElementsByName("writeTimeOut")[i].value="-1";
			document.getElementsByName("flowControl")[i].value="-1";
			document.getElementsByName("flowParity")[i].value="-1";
			}
	
	
}
		
		//setting default values if checked
		function setDefaultStrComportFlag(obj,i)
		{
						if(obj.checked==true)
							{
								obj.value=i+1;
								
								document.getElementsByName("defaultMode")[i].disabled=false;
								document.getElementsByName("baudRate")[i].disabled=false;
								document.getElementsByName("byteSize")[i].disabled=false;
								document.getElementsByName("parity")[i].disabled=false;
								document.getElementsByName("stopBit")[i].disabled=false;
								document.getElementsByName("readTimeOut")[i].disabled=false;
								document.getElementsByName("writeTimeOut")[i].disabled=false;
								document.getElementsByName("flowControl")[i].disabled=false;
								document.getElementsByName("flowParity")[i].disabled=false;
								
								
								
								
								
								
								
								
							}
					
						//else set all default to select value (-1)
						else
							{
								//document.forms[0].strDefaultMode[i].checked=false;	
								
								document.getElementsByName("defaultMode")[i].disabled=true;
								//setDefaultValueForConfiguration(i);
								obj.value="0";
								document.getElementsByName("baudRate")[i].disabled=true;
								document.getElementsByName("byteSize")[i].disabled=true;
								document.getElementsByName("parity")[i].disabled=true;
								document.getElementsByName("stopBit")[i].disabled=true;
								document.getElementsByName("readTimeOut")[i].disabled=true;
								document.getElementsByName("writeTimeOut")[i].disabled=true;
								document.getElementsByName("flowControl")[i].disabled=true;
								document.getElementsByName("flowParity")[i].disabled=true;
								}
	
			}
		
		
		
		
		
		
		//check if the comm port is selected for the selected value
		function checkCommPortValidation(row)
		{
			
			var retValue=true;
			
			if(document.getElementsByName("chkCommPort")[row].checked==false)
			{	alert("Select the Comm Port first");
				retValue=false;			
			
			}
			
			return retValue;
			
		}
		

		
		function validateSave()
		{
			
			if(document.getElementsByName("machineName")[0].value=="")
				{
				
				alert("Enter the Machine Name");
				document.getElementsByName("machineName")[0].focus();
				return false;
				}
			
			if(document.getElementsByName("locationCode")[0].value=="-1")
			{
			
			alert("Select the Location");
			document.getElementsByName("locationCode")[0].focus();
			return false;
			}
			
			if(document.getElementsByName("archivalDays")[0].value=="" || document.getElementsByName("archivalDays")[0].value<"3")
			{
			
			alert("Enter No. of Archival Days Value (Greater than 3)");
			document.getElementsByName("archivalDays")[0].focus();
			return false;
			}
			
			var i=0;
		for(i=0;i<document.getElementsByName("chkCommPort").length;i++)
			{
			
				if(document.getElementsByName("chkCommPort")[i].checked==false)
				;
				else
					break;
			}
		
		if(i==4)
			{
			//alert(i);
			alert("Please select at least one comm port flag.");
			return false;
			
			}
		
		
		
		
	
		
		
		for(i=0;i<document.getElementsByName("chkCommPort").length;i++)
		{
		
			if(document.getElementsByName("chkCommPort")[i].checked)
				{
				
				
				if(document.getElementsByName("baudRate")[i].value=="-1")
						{
							alert("Please select the Baud Rate.");
							document.getElementsByName("baudRate")[i].focus();
							return false;
					
						}
				
				
				if(document.getElementsByName("byteSize")[i].value=="-1")
				{
					alert("Please select the Byte Size.");
					document.getElementsByName("byteSize")[i].focus();
					return false;
			
				}
				
				
				if(document.getElementsByName("parity")[i].value=="-1")
				{
					alert("Please select the Parity.");
					document.getElementsByName("parity")[i].focus();
					return false;
			
				}
		
				
				
				
				if(document.getElementsByName("stopBit")[i].value=="-1")
				{
					alert("Please select the Stop Bit.");
					document.getElementsByName("stopBit")[i].focus();
					return false;
			
				}
				
				
				
				if(document.getElementsByName("readTimeOut")[i].value=="-1")
				{
					alert("Please select the read Time Out.");
					document.getElementsByName("readTimeOut")[i].focus();
					return false;
			
				}
				
				if(document.getElementsByName("writeTimeOut")[i].value=="-1")
				{
					alert("Please select the write Time Out.");
					document.getElementsByName("writeTimeOut")[i].focus();
					return false;
			
				}
				
				if(document.getElementsByName("flowControl")[i].value=="-1")
				{
					alert("Please select the flow Control.");
					document.getElementsByName("flowControl")[i].focus();
					return false;
			
				}
				
				if(document.getElementsByName("flowParity")[i].value=="-1")
				{
					alert("Please select the flow Parity.");
					document.getElementsByName("flowParity")[i].focus();
					return false;
			
				}
				
				
				
				}
			
		}
	
		
		
			
			document.getElementsByName("hmode")[0].value="SAVE";
			 document.forms[0].submit();
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
//added by krishnan nema on 08/10/2018

function populateMachine(locationCode){
	alert("populateMachine called...");
}

		
	</script>

	<html:form action="/masters/machineMstACTION">

		<html:hidden name="machineMstFB" property="hmode" />
		<html:hidden name="machineMstFB" property="machineCode" />
		<html:hidden name="machineMstFB" property="selectedChk" />


		<%!boolean readOnly;%>
		<%
			this.readOnly = false;
		%>

		<logic:equal name="machineMstFB" property="hmode" value="VIEW">
			<%
				this.readOnly = true;
			%>
		</logic:equal>


		
			<his:TitleTag name="Machine Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>

				<table width="100%" border="0" cellspacing="1" cellpadding="0">


				<!-- Machine Name -->
				
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="machineName" />&nbsp;
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="machineMstFB" property="machineName" style="width=62%"
									maxlength="60" size="30" readonly="<%=this.readOnly%>"
					onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					<!-- STATUS -->


					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="status" />&nbsp;
								</font>
							</div>
						</td>

						<td width="25%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="status" tabindex="1">
										<html:option value="1">Working Condition</html:option>
										<html:option value="0">Out of Order</html:option>
									</html:select>
								</span>
							</div>

						</td>

					</tr>

					<!-- 	FORMAT -->


					<tr>


						<td width="25%" class="tdfont">

							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="format" />&nbsp;
								</font>
							</div>


						</td>
						<td width="25%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_MACHINE_MST_FORMAT%>">
								<div align="left">
									&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
											name="machineMstFB" property="format" tabindex="1">
												<html:options
												collection="<%=InvestigationConfig.LIST_MACHINE_MST_FORMAT%>"
												property="value" labelProperty="label" />
										</html:select>
									</span>
								</div>


							</logic:present></td>
					</tr>




					<!-- COMM PORT -->



					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="commFlag" />&nbsp;
								</font>
							</div>
						</td>

						<td width="25%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_MACHINE_MST_COMMPORT%>">
								<div align="left">
									&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
											name="machineMstFB" property="commFlag" tabindex="1">
											
											<html:options
												collection="<%=InvestigationConfig.LIST_MACHINE_MST_COMMPORT%>"
												property="value" labelProperty="label" />
										</html:select>
									</span>
								</div>


							</logic:present></td>

					</tr>

					<!-- LOCATION -->

					<tr>


						<td width="25%" class="tdfont">

							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="location" />&nbsp;
								</font>
							</div>


						</td>
						<td width="25%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_MACHINE_MST_LOCATION_COMBO%>">
								<div align="left">
									&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
											name="machineMstFB" property="locationCode" tabindex="1">
											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_MACHINE_MST_LOCATION_COMBO%>"
												property="value" labelProperty="label" />
										</html:select>
									</span>
								</div>


							</logic:present></td>
					</tr>
					
					<!-- updated by krishnan nema on 08/10/2018 -->
					<tr>
					<td width="25%" class="tdfont">

							<div align="right">
								<!-- <font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> -->
									 <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="machineType" />&nbsp;
								</font>
							</div>


					 </td>
					
					 <td width="25%" class="tdfont">
						<div align="left">
									&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="machineType" tabindex="1">
										<html:option value="1">Patient Based</html:option>
										<html:option value="0">Sample Based</html:option>
									</html:select>
									</span>
						</div>
					</td>
					</tr>
					<!-- End: krishnan -->

					<!-- RESULT VALIDATION TYPE -->

					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="resultValType" />&nbsp;
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">


								<html:radio name="machineMstFB" tabindex="1" property="validationType"
									value="1"></html:radio>
								<bean:message key="Automatic" />
								<html:radio name="machineMstFB" tabindex="1" property="validationType"
									value="2"></html:radio>
								<bean:message key="Manual" />


							</div>
						</td>
					</tr>

					<!-- NO OF DAYS ARCHIVAL -->
					
					
					
			
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="archiveDays" />&nbsp;
								</font>
							</div>
						</td>

						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="machineMstFB" property="archivalDays" style="width=62%"
									maxlength="1" size="30" 
					onkeypress="return validateNumeric(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>		

				</table>
				
				
				
				
				<his:SubTitleTag> Communication Details </his:SubTitleTag>
				<!-- table for communication details -->
				
				<table>
				
				
				<tr>

						<td width="5%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="componentFlag" />&nbsp;
								</font>
							</div>
						</td>

						<td width="5%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="defaultMode" />&nbsp;
								</font>
							</div>
						</td>
						
						<td width="10%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="baudRate" />&nbsp;
								</font>
							</div>
						</td>
						
						<td width="10%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="byteSize" />&nbsp;
								</font>
							</div>
						</td>
						
						<td width="10%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="parity" />&nbsp;
								</font>
							</div>
						</td>
						
						<td width="10%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="stopBit" />&nbsp;
								</font>
							</div>
						</td>
						
						<td width="10%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="readTimeOut" />&nbsp;
								</font>
							</div>
						</td>
						
						<td width="10%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="writeTimeOut" />&nbsp;
								</font>
							</div>
						</td>
						
						<td width="10%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="flowControl" />&nbsp;
								</font>
							</div>
						</td>
						
						<td width="10%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="flowParity" />&nbsp;
								</font>
							</div>
						</td>




					</tr>
				
			 <logic:notEqual name="machineMstFB"
						property="hmode" value="MODIFY">	
				
				<tr>


						<!-- COMM FLAG -->
						
						
						<td width="5%" class="tdfonthead">
							<div align="right">
							 <input value="0" Type="checkbox" name="chkCommPort" onclick="setDefaultStrComportFlag(this,0)"/>
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Comm1 </font>
							</div>
						</td>
						
						
						<!-- DEFAULT MODE -->
						
						<td width="5%" class="tdfonthead">
							<div align="right">
							
							
							 <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="defaultMode" tabindex="1" disabled="true" onchange="setDefaultValueForConfiguration(0)">
										<html:option value="0">No</html:option>
										<html:option value="1">Yes</html:option>
										
									</html:select>
								</span>
								
								
							<%-- <html:radio name="machineMstFB" tabindex="1" property="defaultMode"
									value="0" onclick="setDefaultValueForConfiguration(0)"></html:radio> --%>
							<!--  <input Type="checkbox" name="defaultMode" onclick="setDefaultValueForConfiguration(0)"/> -->
								</div>
						</td>
						
						
						<!-- BAUD RATE -->
						
						
							<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="baudRate" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">110</html:option>
										<html:option value="2">300</html:option>
										<html:option value="3">600</html:option>
										<html:option value="4">1200</html:option>
										<html:option value="5">2400</html:option>
										<html:option value="6">4800</html:option>
										<html:option value="7">9600</html:option>
										<html:option value="8">14400</html:option>
										<html:option value="9">19200</html:option>
										<html:option value="10">38400</html:option>
										<html:option value="11">57600</html:option>
										<html:option value="12">115200</html:option>
										<html:option value="13">128000</html:option>
										<html:option value="14">256000</html:option>
									</html:select>
								</span>
							</div>

						</td>
						
						
						<!-- BYTE SIZE -->
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="byteSize" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">Seven</html:option>
										<html:option value="2">Eight</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						<!-- PARITY -->
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="parity" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						<!-- STOP BIT -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="stopBit" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										<html:option value="2">Two</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
					
					<!-- READ TIME OUT -->	
						
						

						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="readTimeOut" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">50</html:option>
										<html:option value="2">100</html:option>
										<html:option value="3">150</html:option>
										<html:option value="4">200</html:option>
										<html:option value="5">500</html:option>
										<html:option value="6">1000</html:option>
										<html:option value="7">2000</html:option>
										<html:option value="8">3000</html:option>
										<html:option value="9">4000</html:option>
										<html:option value="10">5000</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						
						<!-- WRITE TIME OUT -->	
						
						

						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="writeTimeOut" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">50</html:option>
										<html:option value="2">100</html:option>
										<html:option value="3">150</html:option>
										<html:option value="4">200</html:option>
										<html:option value="5">500</html:option>
										<html:option value="6">1000</html:option>
										<html:option value="7">2000</html:option>
										<html:option value="8">3000</html:option>
										<html:option value="9">4000</html:option>
										<html:option value="10">5000</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
							<!-- FLOW CONTROL -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="flowControl" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">Xon/Xoff</html:option>
										<html:option value="2">Hardware</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						
							<!-- FLOW PARITY -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="flowParity" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										
										
									</html:select>
								</span>
							</div>

						</td>



					</tr>
				
						<!-- COMM FLAG -->
						
						
						<td width="5%" class="tdfonthead">
							<div align="right">
							 <input value="0" Type="checkbox" name="chkCommPort" onclick="setDefaultStrComportFlag(this,1)"/>
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Comm2 </font>
							</div>
						</td>
						
						
						<!-- DEFAULT MODE -->
						
						<td width="5%" class="tdfonthead">
							<div align="right">
							 <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="defaultMode" tabindex="1" disabled="true" onchange="setDefaultValueForConfiguration(1)">
										<html:option value="0">No</html:option>
										<html:option value="1">Yes</html:option>
										
									</html:select>
								</span>
								</div>
						</td>
						
						
						<!-- BAUD RATE -->
						
						
							<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="baudRate" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">110</html:option>
										<html:option value="2">300</html:option>
										<html:option value="3">600</html:option>
										<html:option value="4">1200</html:option>
										<html:option value="5">2400</html:option>
										<html:option value="6">4800</html:option>
										<html:option value="7">9600</html:option>
										<html:option value="8">14400</html:option>
										<html:option value="9">19200</html:option>
										<html:option value="10">38400</html:option>
										<html:option value="11">57600</html:option>
										<html:option value="12">115200</html:option>
										<html:option value="13">128000</html:option>
										<html:option value="14">256000</html:option>
									</html:select>
								</span>
							</div>

						</td>
						
						
						<!-- BYTE SIZE -->
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="byteSize" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">Seven</html:option>
										<html:option value="2">Eight</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						<!-- PARITY -->
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="parity" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						<!-- STOP BIT -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="stopBit" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										<html:option value="2">Two</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
					
					<!-- READ TIME OUT -->	
						
						

						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="readTimeOut" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">50</html:option>
										<html:option value="2">100</html:option>
										<html:option value="3">150</html:option>
										<html:option value="4">200</html:option>
										<html:option value="5">500</html:option>
										<html:option value="6">1000</html:option>
										<html:option value="7">2000</html:option>
										<html:option value="8">3000</html:option>
										<html:option value="9">4000</html:option>
										<html:option value="10">5000</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						
						<!-- WRITE TIME OUT -->	
						
						

						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="writeTimeOut" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">50</html:option>
										<html:option value="2">100</html:option>
										<html:option value="3">150</html:option>
										<html:option value="4">200</html:option>
										<html:option value="5">500</html:option>
										<html:option value="6">1000</html:option>
										<html:option value="7">2000</html:option>
										<html:option value="8">3000</html:option>
										<html:option value="9">4000</html:option>
										<html:option value="10">5000</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
							<!-- FLOW CONTROL -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="flowControl" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">Xon/Xoff</html:option>
										<html:option value="2">Hardware</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						
							<!-- FLOW PARITY -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="flowParity" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										
										
									</html:select>
								</span>
							</div>

						</td>



					</tr>
						<!-- COMM FLAG -->
						
						<tr>
						<td width="5%" class="tdfonthead">
							<div align="right">
							 <input value="0" Type="checkbox" name="chkCommPort" onclick="setDefaultStrComportFlag(this,2)"/>
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Comm3 </font>
							</div>
						</td>
						
						
						<!-- DEFAULT MODE -->
						
						<td width="5%" class="tdfonthead">
							<div align="right">
							 <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="defaultMode" tabindex="1" disabled="true" onchange="setDefaultValueForConfiguration(2)">
										<html:option value="0">No</html:option>
										<html:option value="1">Yes</html:option>
										
									</html:select>
								</span>
								</div>
						</td>
						
						
						<!-- BAUD RATE -->
						
						
							<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="baudRate" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">110</html:option>
										<html:option value="2">300</html:option>
										<html:option value="3">600</html:option>
										<html:option value="4">1200</html:option>
										<html:option value="5">2400</html:option>
										<html:option value="6">4800</html:option>
										<html:option value="7">9600</html:option>
										<html:option value="8">14400</html:option>
										<html:option value="9">19200</html:option>
										<html:option value="10">38400</html:option>
										<html:option value="11">57600</html:option>
										<html:option value="12">115200</html:option>
										<html:option value="13">128000</html:option>
										<html:option value="14">256000</html:option>
									</html:select>
								</span>
							</div>

						</td>
						
						
						<!-- BYTE SIZE -->
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="byteSize" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">Seven</html:option>
										<html:option value="2">Eight</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						<!-- PARITY -->
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="parity" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						<!-- STOP BIT -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="stopBit" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										<html:option value="2">Two</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
					
					<!-- READ TIME OUT -->	
						
						

						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="readTimeOut" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">50</html:option>
										<html:option value="2">100</html:option>
										<html:option value="3">150</html:option>
										<html:option value="4">200</html:option>
										<html:option value="5">500</html:option>
										<html:option value="6">1000</html:option>
										<html:option value="7">2000</html:option>
										<html:option value="8">3000</html:option>
										<html:option value="9">4000</html:option>
										<html:option value="10">5000</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						
						<!-- WRITE TIME OUT -->	
						
						

						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="writeTimeOut" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">50</html:option>
										<html:option value="2">100</html:option>
										<html:option value="3">150</html:option>
										<html:option value="4">200</html:option>
										<html:option value="5">500</html:option>
										<html:option value="6">1000</html:option>
										<html:option value="7">2000</html:option>
										<html:option value="8">3000</html:option>
										<html:option value="9">4000</html:option>
										<html:option value="10">5000</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
							<!-- FLOW CONTROL -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="flowControl" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">Xon/Xoff</html:option>
										<html:option value="2">Hardware</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						
							<!-- FLOW PARITY -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="flowParity" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										
										
									</html:select>
								</span>
							</div>

						</td>



					</tr>
						<!-- COMM FLAG -->
						
						<tr>
						<td width="5%" class="tdfonthead">
							<div align="right">
							 <input value="0" Type="checkbox" name="chkCommPort" onclick="setDefaultStrComportFlag(this,3)"/>
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Comm4 </font>
							</div>
						</td>
						
						
						<!-- DEFAULT MODE -->
						
						<td width="5%" class="tdfonthead">
							<div align="right">
							  <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="defaultMode" tabindex="1" disabled="true" onchange="setDefaultValueForConfiguration(3)">
										<html:option value="0">No</html:option>
										<html:option value="1">Yes</html:option>
										
									</html:select>
								</span>
								</div>
						</td>
						
						
						<!-- BAUD RATE -->
						
						
							<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="baudRate" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">110</html:option>
										<html:option value="2">300</html:option>
										<html:option value="3">600</html:option>
										<html:option value="4">1200</html:option>
										<html:option value="5">2400</html:option>
										<html:option value="6">4800</html:option>
										<html:option value="7">9600</html:option>
										<html:option value="8">14400</html:option>
										<html:option value="9">19200</html:option>
										<html:option value="10">38400</html:option>
										<html:option value="11">57600</html:option>
										<html:option value="12">115200</html:option>
										<html:option value="13">128000</html:option>
										<html:option value="14">256000</html:option>
									</html:select>
								</span>
							</div>

						</td>
						
						
						<!-- BYTE SIZE -->
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="byteSize" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">Seven</html:option>
										<html:option value="2">Eight</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						<!-- PARITY -->
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="parity" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						<!-- STOP BIT -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="stopBit" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										<html:option value="2">Two</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
					
					<!-- READ TIME OUT -->	
						
						

						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="readTimeOut" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">50</html:option>
										<html:option value="2">100</html:option>
										<html:option value="3">150</html:option>
										<html:option value="4">200</html:option>
										<html:option value="5">500</html:option>
										<html:option value="6">1000</html:option>
										<html:option value="7">2000</html:option>
										<html:option value="8">3000</html:option>
										<html:option value="9">4000</html:option>
										<html:option value="10">5000</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						
						<!-- WRITE TIME OUT -->	
						
						

						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="writeTimeOut" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">50</html:option>
										<html:option value="2">100</html:option>
										<html:option value="3">150</html:option>
										<html:option value="4">200</html:option>
										<html:option value="5">500</html:option>
										<html:option value="6">1000</html:option>
										<html:option value="7">2000</html:option>
										<html:option value="8">3000</html:option>
										<html:option value="9">4000</html:option>
										<html:option value="10">5000</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
							<!-- FLOW CONTROL -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="flowControl" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">Xon/Xoff</html:option>
										<html:option value="2">Hardware</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						
							<!-- FLOW PARITY -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										name="machineMstFB" property="flowParity" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										
										
									</html:select>
								</span>
							</div>

						</td>



					</tr>
				</logic:notEqual>
				</table>
				
				
		<!-- 		display of comports on modification  -->
		
						<logic:present
					name="<%=InvestigationConfig.MACHINE_COMMPORTS_DETAILS %>">
				
						<%
     
  	 List<machineMstVO> machineMstVOLst=(List<machineMstVO>)session.getAttribute(InvestigationConfig.MACHINE_COMMPORTS_DETAILS);
  	 
 	if(machineMstVOLst!=null && machineMstVOLst.size()>0 )
 	{
 		int machineLstSize=machineMstVOLst.size();
 		boolean disable=false;
 		
					%>	
					<table width="100%">
					
				
					
					<%
						for(int k=0;k<machineLstSize;k++)
				 		{
				 			machineMstVO voMachineComm=machineMstVOLst.get(k);
				 	 	  
				 	 	    %>	<!-- COMM FLAG -->
						
						<tr>
						<td width="5%" class="tdfonthead">
							<div align="right">
							
							<%if(voMachineComm.getCommPortFlag().equals("0"))
									{ disable=true;%>
							 <input value="0" Type="checkbox" name="chkCommPort" onclick='<%="setDefaultStrComportFlag(this,"+(k)+")"%>'/>
								<%}else
									{	disable=false;%>
								
									<input value='<%=k+1 %>' Type="checkbox" checked name="chkCommPort" onclick='<%="setDefaultStrComportFlag(this,"+(k)+")"%>'/>
									<%} %>
									
									
									<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Comm<%=k+1 %> </font>
							</div>
						</td>
						
						
						<!-- DEFAULT MODE -->
						
						<td width="5%" class="tdfonthead">
							<div align="right">
							  <span class="custom-dropdown small"> <html:select
										value='<%=voMachineComm.getDefaultModep()%>' name="machineMstFB" property="defaultMode" tabindex="1" disabled='<%=disable %>' onchange='<%="setDefaultValueForConfiguration("+(k)+")"%>' >
										<html:option value="0">No</html:option>
										<html:option value="1">Yes</html:option>
										
									</html:select>
								</span>
								</div>
						</td>
						
						
						<!-- BAUD RATE -->
						
						
							<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										value='<%=voMachineComm.getBaudRate()%>' name="machineMstFB" property="baudRate" tabindex="1" disabled='<%=disable %>'>
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">110</html:option>
										<html:option value="2">300</html:option>
										<html:option value="3">600</html:option>
										<html:option value="4">1200</html:option>
										<html:option value="5">2400</html:option>
										<html:option value="6">4800</html:option>
										<html:option value="7">9600</html:option>
										<html:option value="8">14400</html:option>
										<html:option value="9">19200</html:option>
										<html:option value="10">38400</html:option>
										<html:option value="11">57600</html:option>
										<html:option value="12">115200</html:option>
										<html:option value="13">128000</html:option>
										<html:option value="14">256000</html:option>
									</html:select>
								</span>
							</div>

						</td>
						
						
						<!-- BYTE SIZE -->
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										value='<%=voMachineComm.getByteSize()%>' name="machineMstFB" property="byteSize" tabindex="1" disabled='<%=disable %>'>
										<html:option value="-1">Select Value</html:option>
										<html:option value="1">Seven</html:option>
										<html:option value="2">Eight</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						<!-- PARITY -->
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										value='<%=voMachineComm.getParity()%>' name="machineMstFB" property="parity" tabindex="1" disabled='<%=disable %>'>
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						<!-- STOP BIT -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										value='<%=voMachineComm.getStopBit().trim()%>'name="machineMstFB" property="stopBit" tabindex="1" disabled='<%=disable%>'>
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										<html:option value="2">Two</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
					
					<!-- READ TIME OUT -->	
						
						

						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										value='<%=voMachineComm.getReadTimeOut()%>' name="machineMstFB" property="readTimeOut" tabindex="1" disabled='<%=disable %>'>
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">50</html:option>
										<html:option value="2">100</html:option>
										<html:option value="3">150</html:option>
										<html:option value="4">200</html:option>
										<html:option value="5">500</html:option>
										<html:option value="6">1000</html:option>
										<html:option value="7">2000</html:option>
										<html:option value="8">3000</html:option>
										<html:option value="9">4000</html:option>
										<html:option value="10">5000</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						
						<!-- WRITE TIME OUT -->	
						
						

						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
									value='<%=voMachineComm.getWriteTimeOut()%>'	name="machineMstFB" property="writeTimeOut" tabindex="1" disabled='<%=disable %>'>
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">50</html:option>
										<html:option value="2">100</html:option>
										<html:option value="3">150</html:option>
										<html:option value="4">200</html:option>
										<html:option value="5">500</html:option>
										<html:option value="6">1000</html:option>
										<html:option value="7">2000</html:option>
										<html:option value="8">3000</html:option>
										<html:option value="9">4000</html:option>
										<html:option value="10">5000</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
							<!-- FLOW CONTROL -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
										value='<%=voMachineComm.getFlowControl()%>' name="machineMstFB" property="flowControl" tabindex="1" disabled='<%=disable %>'>
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">None</html:option>
										<html:option value="1">Xon/Xoff</html:option>
										<html:option value="2">Hardware</html:option>
										
									</html:select>
								</span>
							</div>

						</td>
						
						
							<!-- FLOW PARITY -->
						
						
						<td width="10%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select
									value='<%=voMachineComm.getFlowParity()%>'	name="machineMstFB" property="flowParity" tabindex="1" disabled='<%=disable %>'>
										<html:option value="-1">Select Value</html:option>
										<html:option value="0">Zero</html:option>
										<html:option value="1">One</html:option>
										
										
									</html:select>
								</span>
							</div>

						</td>



					</tr>
				
							<%
							}%>
						</table>
		<%				
 		}
 		else 
 		{;}
 		%>
 		
				</logic:present>
				
		
		
		
		
		
		
		<!-- end modification display -->
				
				
				
				
				
				
			</his:ContentTag>

			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="machineMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="machineMstFB" property="hmode"
							value="VIEW">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-sv.png"/>'
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) validateSave()"
									onclick="validateSave()" tabindex="1">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-clr.png"/>'
								style="cursor: pointer" onclick="clearaddForm()"
								onkeypress="if(event.keyCode==13) clearaddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> <logic:equal name="machineMstFB" property="hmode"
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
		



	</html:form>
</body>
</html>