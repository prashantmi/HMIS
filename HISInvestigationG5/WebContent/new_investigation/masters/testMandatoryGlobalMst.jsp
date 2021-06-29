<!--  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :TEST MANDATORY GLOBAL MASTER
 ## Purpose						        : This master is used for mapping test with mandatory globally i.e. on hospital code 100
 ## Date of Creation					:16-Mar-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
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
<his:javascript src="/new_investigation/js/testMandatoryMst.js" />
<body >

	<script type="text/javascript">

function clearaddForm()
{
 
  document.getElementsByName('testCode')[0].value="-1";
  document.getElementsByName("mappedList")[0].length=0;
  document.getElementsByName("unmappedList")[0].length=0;
    
}
	
function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].mandatoryName,"mandatoryName"))
     {
         valid=true ;
     }
  return valid;
}

function getarea()
{
	if(document.forms[0].testCode.value=="-1" )
	{
	document.forms[0].hmode.value = "ADD";
	document.forms[0].submit();
	}
	else
	{
	document.forms[0].hmode.value = "GETMAND";
	document.forms[0].submit();
	}
}





</script>

		<html:form action="/masters/TestMandatoryGlobalMstACT">
		<html:hidden name="TestMandatoryMstFB" property="hmode" />
		<html:hidden name="TestMandatoryMstFB" property="selectedChk" />
		<html:hidden name="TestMandatoryMstFB" property="templab" />
		
		

		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="TestMandatoryMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>
		

											<!-- Logic for ADD Page -->

	
	<his:TransactionContainer>
			<his:TitleTag name="Test mandatory Global Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>



				<table width='100%' border="0" cellspacing="1" cellpadding="0">


										<!-- Test Combo -->
										
					<tr>
						
						<td width='50%' class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="TestName" />&nbsp;</b>
								</font>
							</div>
						</td>
<logic:notEqual name="TestMandatoryMstFB" property="hmode"
									value="MODIFY">						<td width='50%' class="tdfont">
						<logic:present name="<%=InvestigationConfig.LIST_TEST_COMBO %>">
						<div align="left">

									<html:select name="TestMandatoryMstFB"
property="testCode" tabindex="1" onchange="getarea();">
										<html:option value="-1">Select Value</html:option>
										<html:options collection="<%=InvestigationConfig.LIST_TEST_COMBO %>" property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present>
						</td>
						</logic:notEqual>
						<td width='50%' class="tdfont">
						<logic:equal name="TestMandatoryMstFB" property="hmode" value="MODIFY">
						<logic:present name="<%=InvestigationConfig.LIST_TEST_COMBO %>">
						<div align="left">

									<html:select name="TestMandatoryMstFB" property="testCode" tabindex="1" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:options collection="<%=InvestigationConfig.LIST_TEST_COMBO %>" property="value" labelProperty="label" />
									</html:select>
									<html:hidden name="TestMandatoryMstFB" property="testCode" />
								</div>
							</logic:present>
							</logic:equal>
						</td>
					</tr>
					</table>
										<!-- list name -->
				<%-- 	<logic:present name="not required">		 --%>		
						
			<table width='100%' border="0" cellspacing="1" cellpadding="0">
					
					<tr>
						
						<td width='45%' class="tdfonthead">
							<div align="center">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="mandatoryName" />&nbsp;</b>
								</font>
							</div>
						</td>	
						
						<td width='10%' class="tdfonthead"></td>
										
						<td width='45%' class="tdfonthead">
							<div align="center">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="selectedMandatoryName" />&nbsp;</b>
								</font>
							</div>
						</td>					
						</tr>
										
								<!--left list values -->
								
					
					
					<tr>
						<td width='45%' class="tdfont">

							<div align="center">
								<logic:notEqual name="TestMandatoryMstFB" property="hmode" value="GETMAND">
                                <logic:notEqual name="TestMandatoryMstFB" property="hmode" value="MODIFY">
									<html:select name="TestMandatoryMstFB" property="mandCode" size="5" tabindex="1" style="width:200px">
									</html:select>
								</logic:notEqual>
								</logic:notEqual>
								<logic:equal name="TestMandatoryMstFB" property="hmode" value="GETMAND">
									<logic:present name="<%=InvestigationConfig.LIST_MANDATORY_COMBO %>">
										<html:select name="TestMandatoryMstFB" property="unmappedList" size="5" tabindex="1" style="width:200px">
											<html:options collection="<%=InvestigationConfig.LIST_MANDATORY_COMBO %>" property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
									<logic:equal name="TestMandatoryMstFB" property="hmode"
									value="MODIFY">
									<logic:present name="<%=InvestigationConfig.LIST_MANDATORY_COMBO %>">
										<html:select name="TestMandatoryMstFB"
											property="unmappedList" size="5" tabindex="1"
											style="width:200px">
											<html:options
												collection="<%=InvestigationConfig.LIST_MANDATORY_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
							</div>
						</td>

						<!-- images -->
						
						<td width="10%"  class="tdfont">
							<div align="center">
								<img src="/HISInvestigationG5/hisglobal/images/forward3.gif"   class="link"  onClick='moveRightSingle();'/>  	&nbsp;&nbsp;
								<img src="/HISInvestigationG5/hisglobal/images/forwardward.gif"   class="link"  onClick='moveRightAll();'/>
								<br><br>
								<img src="/HISInvestigationG5/hisglobal/images/back3.gif"   class="link"  onClick='moveLeftSingle();'/> 		&nbsp;&nbsp;
								<img src="/HISInvestigationG5/hisglobal/images/backward.gif"   class="link"  onClick='moveLeftAll();'/>
							</div>
						</td>
						
						<!--right list values -->
						
						
								<td width='45%' class="tdfont">

							<div align="center">
								<logic:notEqual name="TestMandatoryMstFB" property="hmode"
									value="GETMAND">
<logic:notEqual name="TestMandatoryMstFB" property="hmode"
									value="MODIFY">
									<html:select name="TestMandatoryMstFB"
										property="mandCode" size="5" tabindex="1"
										style="width:200px">
									</html:select>
								</logic:notEqual></logic:notEqual>
								<logic:equal name="TestMandatoryMstFB" property="hmode" value="GETMAND">
									<logic:present name="<%=InvestigationConfig.LIST_SELECTED_COMBO%>">
										<html:select name="TestMandatoryMstFB" property="mappedList" size="5" tabindex="1" multiple="true" style="width:200px">
											<html:options collection="<%=InvestigationConfig.LIST_SELECTED_COMBO%>" property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
								<logic:equal name="TestMandatoryMstFB" property="hmode" value="MODIFY">
									<logic:present name="<%=InvestigationConfig.LIST_SELECTED_COMBO%>">
										<html:select name="TestMandatoryMstFB" property="mappedList" size="5" tabindex="1" multiple="true" style="width:200px">
											<html:options collection="<%=InvestigationConfig.LIST_SELECTED_COMBO%>" property="value" labelProperty="label" />
										</html:select>
									</logic:present>
								</logic:equal>
							</div>
						</td>
						
					
					
					</tr>
				</table>
				</his:ContentTag>
					
			

			<his:ButtonToolBarTag>
				<span id="saveDiv"> 
				
				<logic:notEqual
					name="TestMandatoryMstFB" property="hmode" value="MODIFY">
						<logic:notEqual name="TestMandatoryMstFB" property="hmode"
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
					</logic:notEqual> 
					
					<logic:equal name="TestMandatoryMstFB" property="hmode"
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
					</logic:equal> 
					
					<img class="button"
				src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
				style="cursor: pointer" onclick="submitForm('CANCEL')"
				onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
				
				</span>
			</his:ButtonToolBarTag>
			<logic:present name="<%=InvestigationConfig.LIST_MANDATORY_COMBO %>">
		<logic:empty name="<%=InvestigationConfig.LIST_MANDATORY_COMBO  %>">
			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<b>No New Mandatory Found </b>
			</font>	
		</logic:empty>		
		</logic:present>
			<his:status />
			
	</his:TransactionContainer>




	</html:form>
</body>
</html>
