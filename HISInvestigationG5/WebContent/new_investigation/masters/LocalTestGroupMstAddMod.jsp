<!-- /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
 ## Name of Developer		 			:	Yogender yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:   Local Test Group Master
 ## Purpose								:	This master is used to capture the Local Test Group used for investigation Process
 ## Date of Creation					:   18-March-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/*************************************************************************************************************************/
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
<his:javascript src="/new_investigation/js/LocalTestGroupMstAddMod.js" />
<body>

	<script type="text/javascript">


	
    
	function displayname()
	{
		
		var mandatoryCode=document.getElementsByName("groupCde")[0].value;
		
		if(mandatoryCode!=-1)
		{
			submitPage('DISPLAYNAME');
		}
		else
		{
			submitPage('ADD');
		} 
		
		 
	}
	
	
function submitPage(mode)
{

	
	document.forms[0].hmode.value=mode;
	
	//alert("prefOrder"+document.getElementsByName("prefOrder")[0].value);
	document.getElementsByName("preferenceOrder")[0].value=document.getElementsByName("prefOrder")[0].value;
	//alert("preferenceOrder"+document.getElementsByName("preferenceOrder")[0].value);
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
     //alert(document.getElementsByName("preferenceOrder")[0].value);
    if(document.forms[0].globalgroupName &&document.getElementsByName("preferenceOrder")[0].value=="")
	{
		alert("Enter Preference ");
	
		document.getElementsByName("globalgroupName")[0].focus();
		return false;
	}
     
	if(document.forms[0].globalgroupName &&document.getElementsByName("globalgroupName")[0].value=="")
	{
		alert("Enter Global Group Name ");
	
		document.getElementsByName("globalgroupName")[0].focus();
		return false;
	}
	if(document.forms[0].localgroupName &&document.getElementsByName("localgroupName")[0].value=="")
	{
		alert("Enter Local Group Name ");
	
		document.getElementsByName("localgroupName")[0].focus();
		return false;
	}
	if(document.forms[0].GroupType && document.getElementsByName("GroupType")[0].value=="1")
	{
		alert("Select Group Type  ");
		document.forms[0].GroupType.focus();
		return false;                          
	} 
	if(document.forms[0].isprint && document.getElementsByName("isprint")[0].value=="0")
	{
		
		
		                          
	} 
   return true;
 } 	
	

function clearaddForm()
 {
  
   document.getElementsByName('groupCde')[0].value="-1";
   document.getElementsByName('groupName')[0].value="";
   document.getElementsByName('groupType')[0].checked="true";
                  
   document.getElementsByName('remarks')[0].value="";
 
 }
 function clearForm()
    {
    
       submitPage('CLEAR');
    
    }
     
  function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].globalgroupName,"globalgroupName") )
     {
         valid=true ;
     }
  return valid;
 
  {
	   valid=false;
	   
	    if( isEmpty(document.forms[0].localgroupName,"localgroupName") )
	     {
	         valid=true ;
	     }
	  return valid;
	} 
  
}
  
</script>

	<html:form action="/masters/LocalTestGroupMstACTION">

		<html:hidden name="TestGroupMstFB" property="hmode" />
		<%-- <html:hidden name="TestGroupMstFB" property="prefOrder" />
		 --%>
		
		<html:hidden name="TestGroupMstFB" property="selectedChk" />
		<html:hidden name="TestGroupMstFB" property="preferenceOrder" />
		
		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="TestGroupMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>


		<his:TransactionContainer>
			<his:TitleTag name="Local Test Group Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
			
					<!-- Lab Combo -->


 <logic:notEqual name="TestGroupMstFB"
						property="hmode" value="MODIFY">

						<tr>
							<td width='50%' class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="GlobalGroupName" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width='50%' class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_TESTGROUP_COMBO %>">
									<div align="left">

										<html:select name="TestGroupMstFB" property="groupCde"
											tabindex="1" onchange="displayname();" style="width:200px;">

											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_TESTGROUP_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
									</div>
								</logic:present></td>
						</tr>
					</logic:notEqual>
							<logic:equal name="TestGroupMstFB" property="hmode" value="MODIFY">
					
						<tr>

							<td width='50%' class="tdfonthead">
								<div align="right">
									<font color="RED" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
										color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="GlobalGroupName" />&nbsp;</b>
									</font>
								</div>
							</td>


							<td width='50%' class="tdfont"><logic:present
									name="<%=InvestigationConfig.LIST_TESTGROUP_COMBO %>">
									<div align="left">

										<html:select name="TestGroupMstFB" property="groupCde"
											tabindex="1" disabled="true" style="width:200px;">

											<html:option value="-1">Select Value</html:option>
											<html:options
												collection="<%=InvestigationConfig.LIST_TESTGROUP_COMBO %>"
												property="value" labelProperty="label" />
										</html:select>
												<html:hidden name="TestGroupMstFB" property="groupCde" />
										
									</div>
								</logic:present></td>
						</tr>
					

					</logic:equal>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> 
									<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LocalGroupName" />&nbsp;
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="TestGroupMstFB" property="groupName"
									maxlength="60" size="60" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1" style="width:200px;">
								</html:text>
							</div>
						</td>
					</tr>
					
					
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="GroupType" />&nbsp;

								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:radio name="TestGroupMstFB" tabindex="1"
									property="groupType" value="1"></html:radio>
								<bean:message key="Package" />
								<html:radio name="TestGroupMstFB" tabindex="1"
									property="groupType" value="2"></html:radio>
								<bean:message key="Profile" />
								<html:radio name="TestGroupMstFB" tabindex="1"
									property="groupType" value="3"></html:radio>
								<bean:message key="UserDefined" />
							</div>
						</td>
					</tr>
					
 				<!-- 	<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								    <font size="2" face="Verdana, Arial, Helvetica, sans-serif" color="#000000">
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> 
									
									<bean:message 
 										key="preferenceOrder" /> 
									 	Group Order&nbsp;
                                       
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<input type="text" name="prefOrder" 
									maxlength="60" size="60"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1" style="width:200px;" value="" >
								</input>
							</div>
						</td>
					</tr>-->
				 <tr> 
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="preferenceOrder" />&nbsp;
								</font>
							</div>
						</td>
						
					
					 <td width="50%" class="tdfont">
							<div align="left">
								<!-- <input type="text" name="prefOrder" 
									maxlength="60" size="60"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1" style="width:200px;" value="" >
								</input> -->
								<html:text name="TestGroupMstFB" property="prefOrder" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"></html:text>
							</div>
						</td> 
					<!--
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="TestGroupMstFB" property="preferenceOrder"
									tabindex="1" readonly="<%=this.readOnly%>"
									onkeypress="return CheckMaxLength(event,this,50,1)">
								</html:text>
							</div>
						</td>
						-->
					</tr>
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">  </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Is Print Required&nbsp;
								</font>   
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="TestGroupMstFB"  property="isprint" tabindex="1"
									value="1"></html:radio>

								Yes
								
								<html:radio name="TestGroupMstFB" property="isprint" tabindex="1" 
									value="0"></html:radio>
									
								No
									
                            
							</div>
						</td>
					</tr>
					

					
			
 			 

					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="remarks" />&nbsp;
								</font>
							</div>
						</td>
						
					
						<td width="50%" class="tdfont">
							<div align="left">
								<html:textarea name="TestGroupMstFB" property="remarks"
									tabindex="1" readonly="<%=this.readOnly%>"
									onkeypress="return CheckMaxLength(event,this,50,1)">
								</html:textarea>
							</div>
						</td>
					</tr>
			
				</table>
			</his:ContentTag>

			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="TestGroupMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="TestGroupMstFB" property="hmode"
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
					</logic:notEqual> <logic:equal name="TestGroupMstFB" property="hmode" value="MODIFY">
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