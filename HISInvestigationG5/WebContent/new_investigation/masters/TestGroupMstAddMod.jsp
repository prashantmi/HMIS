<!-- /*************************************************Start of program***************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	NIMS
 ## Name of Developer		 			:	Yogender yadav
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:   Global Test Group Master
 ## Purpose								:	This master is used to capture the Global Test Group used for investigation Process
 ## Date of Creation					:   03-March-2015
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
<his:javascript src="/new_investigation/js/TestGroupMstAddMod.js" />
<body>

	<script type="text/javascript">


function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
     
	if(document.forms[0].groupName &&document.getElementsByName("groupName")[0].value=="")
	{
		alert("Enter Group Name... ");
	
		document.getElementsByName("groupName")[0].focus();
		return false;
	}
	if(document.forms[0].GroupType && document.getElementsByName("GroupType")[0].value=="1")
	{
		alert("Select Group Type ... ");
		document.forms[0].GroupType.focus();
		return false;                          
	} 
   return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

function clearaddForm()
 {
  
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
   
    if( isEmpty(document.forms[0].groupName,"groupName") )
     {
         valid=true ;
     }
  return valid;
} 
  

  
</script>

	<html:form action="/masters/GlobalTestGroupMstACTION">

		<html:hidden name="TestGroupMstFB" property="hmode" />
		<html:hidden name="TestGroupMstFB" property="groupCode" />
		<html:hidden name="TestGroupMstFB" property="grupName" />
		<html:hidden name="TestGroupMstFB" property="selectedChk" />
		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="TestGroupMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>


		<his:TransactionContainer>
			<his:TitleTag name="Global Test Group Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="GroupName" />&nbsp;
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="TestGroupMstFB" property="groupName"
									maxlength="60" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event,this)"
									tabindex="1">
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
								<html:textarea name="TestGroupMstFB" property="remarks" cols="28"
									tabindex="1" readonly="<%=this.readOnly%>"
									onkeypress="return CheckMaxLength(event,this,50,1)">
								</html:textarea>
							</div>
						</td>
					</tr>
					<logic:equal name="TestGroupMstFB" property="hmode" value="MODIFY">

					</logic:equal>
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