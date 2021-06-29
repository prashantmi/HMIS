<!-- 
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	LAB CONFIGRATOR MASTER
 ## Name of Developer		 			:	Anant Patel
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	This master is used to map all the global test,sample,mandatory to local lab used for investigation Process.  
 ## Date of Creation					: 	13-Apr-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						:  
-->


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="new_investigation.vo.LabConfigratorMstVO"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.*"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/labConfigratorMst.js" />
<his:javascript src="/new_investigation/js/labRequisition.js" />

<script type="text/javascript">

function fun()
{
document.getElementById("tab").style.display="";
}

function populatePage()
{
	
	var code = document.getElementsByName("labCode")[0].value;

	if (code != -1) {
		submitPage('POPULATE');
	} else {
		submitPage('ADD');
	}	
	
	
	
}
		
  function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].LaboratoryName,"laboratoryName") )
     {
         valid=true ;
     }
  return valid;
}
  
  
  
  
  function setDay(obj)
  {
  
   // var workingDays="0000000";
	  var workingDays= document.getElementsByName('labWorkingDays')[0].value;
 	if(obj.checked)
 		{
 		
 		if(obj.name=="chkMon")
		{
 			//alert("check Mon");	
 			
		workingDays='1'+workingDays.substring(1, 7); //setting first value in the sting as 1
		//alert(workingDays);
		}
 		else  if(obj.name=="chkTue")
		
 		{
 			//alert("check Tue");
		workingDays=workingDays.substring(0, 1)+'1'+workingDays.substring(2, 7);
		//	alert(workingDays);
		 
 		}
 		else if(obj.name=="chkWed")
		
 		{
 			//alert("check Wed");
 			
		workingDays=workingDays.substring(0, 2)+'1'+workingDays.substring(3, 7);
		//alert(workingDays);
		 
		}
		
		else if(obj.name=="chkThu")
		
 		{
			//alert("check Thu");
		workingDays=workingDays.substring(0, 3)+'1'+workingDays.substring(4, 7);
		//	alert(workingDays);
		 
		}
		else if(obj.name=="chkFri")
		
 		{
			//alert("check Fri");
		workingDays=workingDays.substring(0, 4)+'1'+workingDays.substring(5, 7);
		//	alert(workingDays);
		 
 		}
		else if(obj.name=="chkSat")
		
 		{
			//alert("check Sat");
		workingDays=workingDays.substring(0, 5)+'1'+workingDays.substring(6, 7);
		//alert(workingDays);
		 
 		}
		else if(obj.name=="chkSun")
		
 		{
			//alert("check Sun");
		workingDays=workingDays.substring(0, 6)+'1';
		//	alert(workingDays);
		 
 		}
		
 		 
 		}
	else
	{
 		if(obj.name=="chkMon")
		{
 			//alert("uncheck Mon");
		workingDays='0'+workingDays.substring(1, 7);
		//	alert(workingDays);
		 
		}
		  if(obj.name=="chkTue")
		
 		{
			  //alert("uncheck Tue");
		workingDays=workingDays.substring(0, 1)+'0'+workingDays.substring(2, 7);
		//	alert(workingDays);
		 
		}
 		else if(obj.name=="chkWed")
		
 		{
 			//alert("uncheck Wed");
		workingDays=workingDays.substring(0, 2)+'0'+workingDays.substring(3, 7);
		//alert(workingDays);
 		}
		
		else if(obj.name=="chkThu")
		
 		{
			//alert("uncheck Thu");
		workingDays=workingDays.substring(0, 3)+'0'+workingDays.substring(4, 7);
		//alert(workingDays);
 		}
		else if(obj.name=="chkFri")
		
 		{
			//alert("uncheck Fri");
		workingDays=workingDays.substring(0, 4)+'0'+workingDays.substring(5, 7);
		//alert(workingDays);
 		}
		else if(obj.name=="chkSat")
		
 		{
			//alert("uncheck Sat");
		workingDays=workingDays.substring(0, 5)+'0'+workingDays.substring(6, 7);
		//alert(workingDays);
 		}
		else if(obj.name=="chkSun")
		
 		{
			//alert("uncheck Sun");
		workingDays=workingDays.substring(0, 6)+'0';
		//alert(workingDays);
 		}
		
 		}
		
 	document.getElementsByName('labWorkingDays')[0].value=workingDays;
 	//alert("final value is");
 	//alert(document.getElementsByName('labWorkingDays')[0].value); 
 	//alert(workingDays);
 		}
  
  
  function clearForm()
  {
   
     submitPage('CLEAR');
  
  }
</script>
<body onload="fun()" >
	<html:form action="/masters/LabConfigratorMstACT">
	
		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="LabConfigratorMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>


		<his:TransactionContainer>
			<his:TitleTag name="Lab Configrator Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>

				<table width="100%" border="0" cellspacing="1" cellpadding="0">

					<!-- GLOBAL LAB NAME COMBO -->
<logic:notEqual name="LabConfigratorMstFB"
						property="hmode" value="MODIFY">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="GlobalLaboratoryName" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
								<div align="left">

									<html:select name="LabConfigratorMstFB" property="labCode" tabindex="1"
										style="width:41%" onchange="populatePage()">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_LAB_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
						</tr></logic:notEqual>

<logic:equal name="LabConfigratorMstFB"
						property="hmode" value="MODIFY"><html:hidden name="LabConfigratorMstFB" property="labCode" /> </logic:equal>

						<!-- LOCAL LAB NAME  -->
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LaboratoryName" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="LabConfigratorMstFB" property="laboratoryName"
									maxlength="60" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphaNumericOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					<!-- LAB SHORT NAME -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabShortShortName" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="LabConfigratorMstFB" property="labShortSName"
									maxlength="25" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					<!-- DEPARTMENT COMBO -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="DepartmentCombo" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.DEPART_COMBO %>">
								<div align="left">

									<html:select name="LabConfigratorMstFB" property="department" tabindex="1"
										style="width:41%">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.DEPART_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
					</tr>
		<!-- LOCATION COMBO -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="locationCombo" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_LOCATION_COMBO %>">
								<div align="left">

									<html:select name="LabConfigratorMstFB" property="location" tabindex="1"
										style="width:41%">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_LOCATION_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
					</tr>
					
					<!-- LAB INCHARGE COMBO -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="labInchargeCombo" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_LABINCHARGE_COMBO %>">
								<div align="left">

									<html:select name="LabConfigratorMstFB" property="labIncharge" tabindex="1"
										style="width:41%">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_LABINCHARGE_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
					</tr>

					<!--SAMPLE NUMBER CONFIGURATION  -->


					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="SampleNumberConfiguration" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabConfigratorMstFB" tabindex="1"
									property="sampleNumberConfig" value="1"></html:radio>

								<bean:message key="LabSampleNumberCofig" />
								<html:radio name="LabConfigratorMstFB" tabindex="1"
									property="sampleNumberConfig" value="2"></html:radio>

								<bean:message key="SamplecollectionAreaConfig" />

								<html:radio name="LabConfigratorMstFB" tabindex="1"
									property="sampleNumberConfig" value="3"></html:radio>

								<bean:message key="Manual" />


							</div>
						</td>
					</tr>


					<!-- lab no configuration -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabNumberConfiguration" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabConfigratorMstFB" tabindex="1"
									property="labNumberConfig" value="1"></html:radio>

								<bean:message key="Manual" />
								<html:radio name="LabConfigratorMstFB" tabindex="1"
									property="labNumberConfig" value="2"></html:radio>

								<bean:message key="Automatic" />




							</div>
						</td>
					</tr>

					<!--lab type  -->

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabType" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabConfigratorMstFB" tabindex="1" property="labType"
									value="1"></html:radio>

								<bean:message key="Routine" />
								<html:radio name="LabConfigratorMstFB" tabindex="1" property="labType"
									value="2"></html:radio>

								<bean:message key="Emergency" />


							</div>
						</td>
					</tr>
					
					<!--LAB WORKING DAYS  -->

					<tr>
						<td width="20%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabWorkingDays" />&nbsp;
								</font>
							</div>
						</td>
						<td width="80%" class="tdfont"><bean:define name="LabConfigratorMstFB"
								property="labWorkingDays" id="labWorkingDays"
								type="java.lang.String" /> <%
				    // Logic to get the working days in a week .. True->Checked;  False-> unchecked
				    
				    	boolean bMondayWorking=false;
				        boolean bTuesdayWorking=false;
				        boolean bWednesdayWorking=false;
				        boolean bThursdayWorking=false;
				        boolean bFridayWorking=false;
				        boolean bSaturdayWorking=false;
				        boolean bSundayWorking=false;
				    if(labWorkingDays!=null && !labWorkingDays.equals("")){
				    	bMondayWorking=(labWorkingDays.charAt(0)=='1')?true:false;
				        bTuesdayWorking=(labWorkingDays.charAt(1)=='1')?true:false;
				        bWednesdayWorking=(labWorkingDays.charAt(2)=='1')?true:false;
				        bThursdayWorking=(labWorkingDays.charAt(3)=='1')?true:false;
				        bFridayWorking=(labWorkingDays.charAt(4)=='1')?true:false;
				        bSaturdayWorking=(labWorkingDays.charAt(5)=='1')?true:false;
				        bSundayWorking=(labWorkingDays.charAt(6)=='1')?true:false;
				    }
				    %>
							<div align="left">
								<%if(bMondayWorking){ %>
								<input Type="checkbox" name="chkMon" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkMon" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Monday" />

								<%if(bTuesdayWorking){ %>
								<input Type="checkbox" name="chkTue" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkTue" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Tuesday" />

								<%if(bWednesdayWorking){ %>
								<input Type="checkbox" name="chkWed" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkWed" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Wednesday" />

								<%if(bThursdayWorking){ %>
								<input Type="checkbox" name="chkThu" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkThu" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Thursday" />

								<%if(bFridayWorking){ %>
								<input Type="checkbox" name="chkFri" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkFri" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Friday" />

								<%if(bSaturdayWorking){ %>
								<input Type="checkbox" name="chkSat" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkSat" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Saturday" />

								<%if(bSundayWorking){ %>
								<input Type="checkbox" name="chkSun" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkSun" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Sunday" />

							</div></td>
					</tr>

					<!--NUMBER OF TESTS  -->

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="NumberofTests" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="LabConfigratorMstFB" property="numberofTests"
									maxlength="4" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					<!-- HEADER TEXT -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Headertext" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="LabConfigratorMstFB" property="headertext" maxlength="50"
									size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<!-- Footer text -->


					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="FooterText" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="LabConfigratorMstFB" property="footerText" maxlength="50"
									size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
							
							
							<!--remarks  -->

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="remarks" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:textarea name="LabConfigratorMstFB" property="remarks" cols="28"
									tabindex="1" readonly="<%=this.readOnly %>"
									onkeypress="return CheckMaxLength(event,this,50,3)">
								</html:textarea>
							</div>
						</td>
					</tr>
				</table>
  <logic:equal name="LabConfigratorMstFB" property="hmode" value="ADD">
  <logic:present name="<%=InvestigationConfig.MAP_TEST_SAMPLE_DTLS %>">
  <his:SubTitleTag name="Globally Mapped Data">
  </his:SubTitleTag>
  <table id="tab" style="display:none" width="100%" border="1">
  <%
  Map<String,Map<String,List<String>>> mpTestSample=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_TEST_SAMPLE_DTLS);
  List mpTest=(List)session.getAttribute(InvestigationConfig.LIST_TEST_COMBO);
  if(mpTest!=null)
 	 { 
	  Iterator itrTestSsample=mpTest.iterator();
	  String tsCodeHashName="";
	  %>
	  <tr>
	  <%
	  while(itrTestSsample.hasNext())
	  {
		  tsCodeHashName=(String)itrTestSsample.next();
		  String testCode=tsCodeHashName.split("#")[0];
		  String testName=tsCodeHashName.split("#")[1];
		  %>
		   <td class="tdfonthead" width="=30%">
		   <div align="left">
		   <b>Test Name</b>
		   </div>
		   </td>
			   <td class="tdfont" width="70%">
					  <div align="left">
					  <input Type="checkbox" name="testChkBox" value="<%=testCode%>" checked="checked" />
					  <%=testName%>
					  </div>
				</td>
				</tr>	
				<%
				List<String> lsttestSample=(List<String>)mpTestSample.get(tsCodeHashName);
				if(lsttestSample!=null)
				{
					if(lsttestSample.isEmpty()==false)
					{
				%>
				<tr>
				<td width="30%">
				<div align="right">
				<b>Sample/System Name</b>
				</div>
				</td>	
	 			<td class="tdfont" width="70%">
	 				<div align="left">
		 				<table>
		 				 <tr>
		 					<% 
		 					if(lsttestSample!=null)
		 					{
							int size=lsttestSample.size(); 
							for(int k=0;k<size;k++)
							{
								String vo=lsttestSample.get(k);
								String test1 =vo.split("#")[0];
								String sampleCode=vo.split("#")[1];
			 					String sampleName=vo.split("#")[2];
							%>
			 				
				 				<td class="tdfonthead" >
				 				<div align="left">
				 				<input Type="checkbox" name="sampleChkBox" value="<%=test1+"#"+sampleCode%>" checked="checked" />
				 				<%=sampleName%>
				 				</div>
				 				</td>			
			 				
			 				<%} //end for loop
	  						}
							%>
							</tr>
		 				</table>
	 				</div>
			 	</td>
			 	</tr>
			 	<%
				}
	  }
				 Map<String,Map<String,List<String>>> mpTestMand=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_TEST_MAND_DTLS);
				 if(mpTestMand.isEmpty()==false)
				 {
			 	%>
			 	<tr>
			 	<td width="30%">
			 	<div align="right">
			 	<b> Mandatory Name</b>
			 	</div>
			 	</td>
			 	<td class="tdfont" width="70%">
			 		<div>
			 			<table>
			 				<tr>
			 				<%		
	  							Iterator itrTestMand=mpTestMand.keySet().iterator();
	  							String tmCodeHashName="";
	  							while(itrTestMand.hasNext())
	  						  {
	  								tmCodeHashName=(String)itrTestMand.next();
	  							 if(tmCodeHashName.equals(tsCodeHashName))
	  							 {
	  							  List<String> lsttestMand=(List<String>)mpTestMand.get(tsCodeHashName);
								  int size1=lsttestMand.size(); 
								  for(int k=0;k<size1;k++)
								  {
									String vo=lsttestMand.get(k);
									String test2 =vo.split("#")[0];
									String 	mandCode=vo.split("#")[1];
				 					String  mandName=vo.split("#")[2];
	  						%>	
	  							<td class="tdfonthead" >
	  							<div align="left">
				 				<input Type="checkbox" name="mandChkBox" value="<%=test2+"#"+mandCode%>" checked="checked" />
				 				<%=mandName%>
				 				</div>
				 				</td>			
			 				
			 				<%} //end for loop
							%>					
			 				</tr>
			 				<%
	  							 } //end of Inner If 
  								} // end while loop
  							%>
			 			</table>
			 		</div>
			 	</td>
				 			
		</tr>
		<%}
				 Map<String,Map<String,List<String>>> mpTestGroup=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_TEST_GROUP_DTLS);
				 if(mpTestGroup.isEmpty()==false)
				 {
		%>
		<tr style="display:none">
			 	<td width="30%">
			 	<div align="right">
			 	<b>Group Name</b>
			 	</div>
			 	</td>
			 	<td class="tdfont" width="70%">
			 		<div>
			 			<table>
			 				<tr>
			 				<%
	  							Iterator itrTestGroup=mpTestGroup.keySet().iterator();
	  							String tgCodeHashName="";
	  							while(itrTestGroup.hasNext())
	  						  {
	  								tgCodeHashName=(String)itrTestGroup.next();
	  							 if(tgCodeHashName.equals(tsCodeHashName))
	  							 {
	  							  List<String> lsttestGroup=(List<String>)mpTestGroup.get(tsCodeHashName);
								  int size1=lsttestGroup.size(); 
								  for(int k=0;k<size1;k++)
								  {
									String vo=lsttestGroup.get(k);
									String test3 =vo.split("#")[0];
									String 	groupCode=vo.split("#")[1];
				 					String  groupName=vo.split("#")[2];
	  						%>	
	  							<td class="tdfonthead" >
	  							<div align="left">
				 				<input Type="checkbox" name="groupChkBox" value="<%=test3+"#"+groupCode%>" />
				 				<%=groupName%>
				 				</div>
				 				</td>			
			 				
			 				<%} //end for loop
							%>					
			 				</tr>
			 				<%
	  							 } //end of Inner If 
  								} // end while loop
  							%>
			 			</table>
			 		</div>
			 	</td> 			
		</tr>		 
<%
		}
  	} // end while loop
  } // end if loop
  List mpCanned=(List)session.getAttribute(InvestigationConfig.LIST_CANNED_COMBO);
  if(mpCanned!=null)
 	 {
	  if(mpCanned.isEmpty()==false)
	  {
	  Iterator itrCanned=mpCanned.iterator();
	  String cannedHashName="";
	  %>
	  <tr>
	   <td class="tdfonthead" width="=30%">
		 <div align="left">
		       <b>Canned Name</b>
	     </div>
	 </td>
	 <td class="tdfont" width="70%">
					  <div align="left">
	  <%
	  while(itrCanned.hasNext())
	  {
	  int size11=mpCanned.size(); 
	  for(int k=0;k<size11;k++)
	  {
		  cannedHashName=(String)itrCanned.next();
		  String cannedCode=cannedHashName.split("#")[0];
		  String cannedName=cannedHashName.split("#")[1];
		  %>
					  <input Type="checkbox" name="cannedChkBox" value="<%=cannedCode%>" checked="checked" />
					  <%=cannedName%>
					  
		<%} %>
				
	<% } %>
	</div>
	</td>
	</tr>
	<%
 	 }
 	 }
	List mpMacro=(List)session.getAttribute(InvestigationConfig.LIST_MACRO_COMBO);
  if(mpMacro!=null)
 	 { 
	  if(mpMacro.isEmpty()==false)
	  {  
	  Iterator itrMacro=mpMacro.iterator();
	  String cannedHashName="";
	  %>
	  <tr>
	 		 <td  class="tdfonthead" width="=30%">
		   		<div align="left">
		   			<b>Macro Name</b>
		 	    </div>
		 	 </td>
		 	 <td class="tdfont" width="70%">
					  <div align="left">
	  <%
	  while(itrMacro.hasNext())
	  { 	
		  int size12=mpMacro.size(); 
		  for(int k=0;k<size12;k++)
		  {
		  cannedHashName=(String)itrMacro.next();
		  String macroCode=cannedHashName.split("#")[0];
		  String macroName=cannedHashName.split("#")[1];
		  %>
					  		<input Type="checkbox" name="macroChkBox" value="<%=macroCode%>" checked="checked" />
					  		<%=macroName%>
		
		<%} 
				
	} %>
	  			  </div>
		</td>
	</tr>
	  <%
 	 }
  }
		%>	
  </table>
  </logic:present>
  </logic:equal>
  <logic:equal name="LabConfigratorMstFB" property="hmode" value="MODIFY">
 <logic:present name="<%=InvestigationConfig.MAP_TEST_SAMPLE_DTLS %>">
  <his:SubTitleTag name="Locally Mapped/Unmapped Data">
  </his:SubTitleTag>
  <table  width="100%" border="1">
  <%
  String hosCode=session.getAttribute("HOSPITAL_CODE").toString();
  Map<String,Map<String,List<String>>> mpTestSample2=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_TEST_SAMPLE_DTLS);
  List mpTest2=(List)session.getAttribute(InvestigationConfig.LIST_TEST_COMBO);
  List mpTestMapped=(List)session.getAttribute(InvestigationConfig.LIST_TEST_COMBO_MAPPED);
  if(mpTest2!=null)
 	 { 
	  Iterator itrTestSsample=mpTest2.iterator();
	  
	  String tsCodeHashName="";
	  %>
	  
	  		<%
	 		 while(itrTestSsample.hasNext())
	 		 {
		 		 tsCodeHashName=(String)itrTestSsample.next();
		 		 String isLocal="";
		 		 
		 		Iterator itrTestSsampleMapped=mpTestMapped.iterator();
		 		 while(itrTestSsampleMapped.hasNext())
		 		 {
		 			 if(tsCodeHashName.equals((String)itrTestSsampleMapped.next()))
		 				 {isLocal="1"; break;}
		 			 
		 			 
		 		 }
		 		 
		 		 
		 		 
		 		 String testCode=tsCodeHashName.split("#")[0];
		  		String testName=tsCodeHashName.split("#")[1];
		  		//String testHosCode=tsCodeHashName.split("#")[2];
		  		//String splitter=testCode+"#"+testName;
		  		List<String> lsttestMand=null;
		  		List<String> lsttestSample=(List<String>)mpTestSample2.get(tsCodeHashName);
		  		Map<String,Map<String,List<String>>> mpTestMand=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_TEST_MAND_DTLS);
		  		if(mpTestMand.isEmpty()==false)
		  		{
		  			lsttestMand=(List<String>)mpTestMand.get(tsCodeHashName);
		  		}
		  		if(lsttestSample!=null||lsttestMand!=null)
		  		{	
		  	%>
		  
		  <tr >
		  <td class="tdfonthead" width="=30%">
		  	<div align="left">
		  		<b>Test Name</b>
		  	</div>
		  </td>
		  
		  <%
		  
		  
		  if(isLocal.equals("1"))
		  {
		  
		  %>
			    <td class="tdfont" width="70%">
					  <div align="left">
					  		<input Type="checkbox" name="testChkBox" value="<%=testCode%>"  checked="checked"/>
					  		<%=testName%>
					  </div>
			  </td>
	
	<%}
		  
		  else
		  {
			  %>
			   <td class="tdfont" width="70%">
					  <div align="left">
					  		<input Type="checkbox" name="testChkBox" value="<%=testCode%>"/>
					  		<%=testName%>
					  </div>
			  </td>
			  <%}
		  
		  
		  %>
		  </tr>
		  <%
		  
		  
		  
		  
	}
	if(lsttestSample!=null)
	{
		if(lsttestSample.isEmpty()==false)
		{
	%>
			  <tr>
			  	<td width="30%">
			  		<div align="right">
			 		 	<b>Sample/system Name</b>
			 		</div>
			   </td>	
	 			<td class="tdfonthead" width="70%">
	 				<div >
		 				<table>
		 					<tr>
		 						<% 
		 						if(lsttestSample!=null)
		 						{
								int size=lsttestSample.size();
								String temp="";
								for(int k=0;k<size;k++)
								{ 
									String vo=lsttestSample.get(k);
									String test1 =vo.split("#")[0];
									String sampleCode=vo.split("#")[1];
			 						String sampleName=vo.split("#")[2];
			 						String sampleHosCode=vo.split("#")[3];
			 						 		 						 
			 							if(!temp.equals(test1+sampleCode))
			 							{
			 								
			 								%>
			 								<td class="tdfonthead" >
				 						<div align="left">
			 								<%
			 							if(sampleHosCode.equals(Config.SUPER_USER_HOSPITAL_CODE))
			 						{
								%>
				 					
				 				 			<input Type="checkbox" name="sampleChkBox" value="<%=test1+"#"+sampleCode%>" />
				 							<%=sampleName%>
				 				    	
				 			   					
			 					<%}
			 					if(sampleHosCode.equals(hosCode))
			 					
			 					{%>
			 					
				 				 			<input Type="checkbox" name="sampleChkBox" value="<%=test1+"#"+sampleCode%>" checked="checked"/>
				 							<%=sampleName%>
				 				    	
			 					<%
		 						}
			 					%>
			 					</div>
				 			   		</td>
			 					<%
			 					 
			 							}
			 						temp=test1+sampleCode;
			 					} //end for loop
		 						}
								%>
							</tr>
					   </table>
					</div>
				</td>
			</tr>
		<%}
	 		 }
	
			if(lsttestMand!=null)
			 {
		%>
				<tr>
					<td  width="30%">
					<div align="right">
						<b>Mandatory Name</b>
					</div>
					</td>
						<td class="tdfonthead" width="70%">
							<div>
								<table>
									<tr>
							
										<%
	  											Iterator itrTestMand=mpTestMand.keySet().iterator();
	  											String tmCodeHashName="";
	  											tmCodeHashName=(String)itrTestMand.next();
								  					int size1=lsttestMand.size(); 
								  					String mandTemp="";
								  					for(int k=0;k<size1;k++)
								  						{
															String vo=lsttestMand.get(k);
															String test2 =vo.split("#")[0];
															String 	mandCode=vo.split("#")[1];
				 											String  mandName=vo.split("#")[2];
				 											String  mandHosCode=vo.split("#")[3];
				 											if(!mandTemp.equals(test2+mandCode))
				 											{
				 											if(mandHosCode.equals(Config.SUPER_USER_HOSPITAL_CODE))
				 											{
	  									%>
	  							
	  									<td class="tdfonthead" >
	  										<div align="left">
	  											<input Type="checkbox" name="mandChkBox" value="<%=test2+"#"+mandCode%>" />
	  											<%=mandName%>
				 							</div>
				 					   </td>			
			 				<%}
				 					if(mandHosCode.equals(hosCode))
				 					{
						      %>
						      <td class="tdfonthead" >
	  										<div align="left">
	  											<input Type="checkbox" name="mandChkBox" value="<%=test2+"#"+mandCode%>" checked="checked"/>
	  											<%=mandName%>
				 							</div>
				 					   </td>
				 					   <%
				 					}
				 					}
				 						mandTemp=test2+mandCode;					
								  	}
				 					   %>
  							</tr>
  							
							
		 				</table>
	 				</div>
			 	</td>	 			
		</tr>
	<%}
	Map<String,Map<String,List<String>>> mpTestGroup=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_TEST_GROUP_DTLS);
	if(mpTestGroup.isEmpty()==false)
	{
	%>
		<tr style="display:none">
				<td width="30%">
				<div align="right">
					<b>Group Name</b>
				</div>
				</td>
					<td class="tdfonthead" width="70%" >
						<div>
							<table>
								<tr>
								<%
 								 
								boolean chkTestGroup=mpTestGroup.isEmpty();
  								 if(chkTestGroup==false)
 								 	{
	  									Iterator itrTestgroupmod=mpTestGroup.keySet().iterator();
	  									String tgCodeHashName="";
	  									tgCodeHashName=(String)itrTestgroupmod.next();
	  							 	 	List<String> lsttestgroup=(List<String>)mpTestMand.get(tsCodeHashName);
	  							  		if(lsttestgroup!=null)
	  							  		{
								  			int size1=lsttestgroup.size(); 
								  			String groupTemp="";
								 	 		for(int k=0;k<size1;k++)
								  			{
												String vo=lsttestgroup.get(k);
												String test2 =vo.split("#")[0];
												String 	groupCode=vo.split("#")[1];
				 								String  groupName=vo.split("#")[2];
				 								String groupHosCode=vo.split("#")[3];
				 								if(!groupTemp.equals(test2+groupCode))
	 											{
				 								if(groupHosCode.equals(Config.SUPER_USER_HOSPITAL_CODE))
				 								{
				 								
	  							%>
	  								<td class="tdfonthead" >
	  									<div align="left" >
	  										<input Type="checkbox" name="groupChkBox" value="<%=test2+"#"+groupCode%>" />
	  										<%=groupName%>
	  										<% 										 	 		
								 	 		%>
				 						</div>
				 				    </td>			
			 					<%}
				 								if(groupHosCode.equals(hosCode))
				 								{
				 								
	  							%>
	  								<td class="tdfonthead" >
	  									<div align="left" >
	  										<input Type="checkbox" name="groupChkBox" value="<%=test2+"#"+groupCode%>" checked="checked"/>
	  										<%=groupName%>
	  										<% 										 	 		
								 	 		%>
				 						</div>
				 				    </td>			
			 					<%}	
				 								
	 											}		
									  			
								  			}
								 	 		
	  							  		
	  							  		} //end for loop
	  							  }
  								%>
  							</tr>	
		 				</table>
	 				</div>
			 	</td>	 			
			</tr>			 
<%
		}
  	   } // end while loop
 	 }
  List mpCanned1=(List)session.getAttribute(InvestigationConfig.LIST_CANNED_COMBO);
  List mpCannedMapped=(List)session.getAttribute(InvestigationConfig.LIST_CANNED_COMBO_MAPPED);
  if(mpCanned1!=null)
 	 { 
	  if(mpCanned1.isEmpty()==false)
	  {
	  Iterator itrCanned=mpCanned1.iterator();
	  String cannedHashName="";
	  %>
	  <tr>
	   <td class="tdfonthead" width="=30%">
		 <div align="left">
		       <b>Canned Name</b>
	     </div>
	 </td>
	 <td class="tdfont" width="70%">
					  <div align="left">
	  <%
	  while(itrCanned.hasNext())
	  {
	  int size11=mpCanned1.size(); 
	 
	  for(int k=0;k<size11;k++)
	  {
		  cannedHashName=(String)itrCanned.next();
		  String cannedCode=cannedHashName.split("#")[0];
		  String cannedName=cannedHashName.split("#")[1];
		  String cannedHosCode=cannedHashName.split("#")[2];
		  String displayCanned="";
		  
		  Iterator itrCannedMapped=mpCannedMapped.iterator();
		 
		  
		  while(itrCannedMapped.hasNext())
		  { int size22=mpCannedMapped.size(); 
		  
		 /*  for(int ki=0;ki<size22;ki++)
			  
		  { */
			  String cannedHashNameMapped=(String)itrCannedMapped.next();
			  String cannedCodeMapped=cannedHashNameMapped.split("#")[0];
			  String cannedNameMapped=cannedHashNameMapped.split("#")[1];
			  String cannedHosCodeMapped=cannedHashNameMapped.split("#")[2];
			  
			  if((cannedCode+"#"+cannedName).equals(cannedCodeMapped+"#"+cannedNameMapped))
			  {displayCanned="1";break;}
			  
			 
			  
	/* 	  } */
			  
			  
		  }
		  
		  
		  
		  
		  
		  if(displayCanned.equals("1"))
		  {
		  %>
					  <input Type="checkbox" name="cannedChkBox" value="<%=cannedCode%>" checked="checked" />
					  <%=cannedName%>
					 
		<%}
		  else
		  {
		 %>
		 <input Type="checkbox" name="cannedChkBox" value="<%=cannedCode%>" />
					  <%=cannedName%>
		 <%
		  }
	  	  }
				
	 } %>
			 </div>
		</td>
	 </tr>
	 <% }
  }
	List mpMacro1=(List)session.getAttribute(InvestigationConfig.LIST_MACRO_COMBO);
	List mpMacroMapped=(List)session.getAttribute(InvestigationConfig.LIST_MACRO_COMBO_MAPPED);
  if(mpMacro1!=null)
 	 { 
	  if(mpMacro1.isEmpty()==false)
	  {
	  Iterator itrMacro=mpMacro1.iterator();
	  String cannedHashName="";
	  %>
	  <tr>
	 		 <td class="tdfonthead" width="=30%">
		   		<div align="left">
		   			<b>Macro Name</b>
		 	    </div>
		 	 </td>
		 	 <td class="tdfont" width="70%">
					  <div align="left">
	  <%
	  while(itrMacro.hasNext())
	  {
		  int size12=mpMacro1.size(); 
		  
		  for(int k=0;k<size12;k++)
		  {
		  cannedHashName=(String)itrMacro.next();
		  String macroCode=cannedHashName.split("#")[0];
		  String macroName=cannedHashName.split("#")[1];
		  String macrohosCode=cannedHashName.split("#")[2];
		  String displayMacro="";
		  Iterator itrMacroMapped=mpMacroMapped.iterator();
		  while(itrMacroMapped.hasNext())
		  { int size22=mpMacroMapped.size(); 
		  
		/*   for(int ki=0;ki<size22;ki++)
			  
		  { */
			  String cannedHashNameMapped=(String)itrMacroMapped.next();
			  String macroCodeMapped=cannedHashNameMapped.split("#")[0];
			  String macroNameMapped=cannedHashNameMapped.split("#")[1];
			  String macroHosCodeMapped=cannedHashNameMapped.split("#")[2];
			  
			  if((macroCode+"#"+macroName).equals(macroCodeMapped+"#"+macroNameMapped))
			  {displayMacro="1";break;}
			  else
				  displayMacro="";
			  
				  
			  
		 /*  } */
			  
			  
		  }
		  
		  
		  if(displayMacro.equals("1"))
		  {
		  %>
					  		<input Type="checkbox" name="macroChkBox" value="<%=macroCode%>"  checked="checked"/>
					  		<%=macroName%>
	
		<%}
		  else
		  {
		%>
		<input Type="checkbox" name="macroChkBox" value="<%=macroCode%>" />
					  		<%=macroName%>
		<%
		  }
		  }
				
	} %>
		  	</div>
		</td>
	</tr>
	  <%
 	 }
  }
		%>	
  </table>
  </logic:present>
  </logic:equal>
			</his:ContentTag>

			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="LabConfigratorMstFB" property="hmode" value="MODIFY">
				<logic:notEqual name="LabConfigratorMstFB" property="hmode" value="VIEW">
				<img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>'style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')" tabindex="1">
				<img class="button"src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearAddForm()"onkeypress="if(event.keyCode==13) clearAddForm()" tabindex="1">
				</logic:notEqual>
				</logic:notEqual> <logic:equal name="LabConfigratorMstFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
				<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				</logic:equal> 
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
				</span>
			</his:ButtonToolBarTag>
			<logic:equal name="LabConfigratorMstFB" property="hmode" value="ADD">
			<logic:present name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
		<logic:empty name="<%=InvestigationConfig.LIST_LAB_COMBO  %>">
			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<b>No New Global Lab Found </b>
			</font>	
		</logic:empty>		
		</logic:present>
		</logic:equal>
			<his:status />
			<html:hidden name="LabConfigratorMstFB" property="hmode" />
			<html:hidden name="LabConfigratorMstFB" property="hospitalCode" />
			<html:hidden name="LabConfigratorMstFB" property="labWorkingDays" />
			<html:hidden name="LabConfigratorMstFB" property="selectedChk" />
			<html:hidden name="LabConfigratorMstFB" property="labCode" />
			</his:TransactionContainer>
		</html:form>
</body>
</html>