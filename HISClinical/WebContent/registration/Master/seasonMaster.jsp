<html>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>    

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/css/calendar-blue2.css"/>

<script>
window.history.forward()

<!--
function submitTile(mode)
{
	document.getElementsByName("hmode")[0].value=mode;  
//	alert(">>>>>>"+mode);
   	document.forms[0].submit();
}

function validationAdd()
{
	if(isEmpty(document.forms[0].seasonDesc,"Season Description")&&
		isEmpty(document.forms[0].seasonFromDay,"Season From Day")&&
		isEmpty(document.forms[0].seasonFromMonth,"Season From Month")&&
		isEmpty(document.forms[0].seasonToDay,"Season To Day")&&
		isEmpty(document.forms[0].seasonToMonth,"Season To Month"))
		{
			submitTile("SAVE");
		}
}

function validateModify()
{
	if(isEmpty(document.forms[0].seasonDesc,"Season Description")&&
		isEmpty(document.forms[0].seasonFromDay,"Season From Day")&&
		isEmpty(document.forms[0].seasonFromMonth,"Season From Month")&&
		isEmpty(document.forms[0].seasonToDay,"Season To Day")&&
		isEmpty(document.forms[0].seasonToMonth,"Season To Month"))
		{
			submitTile("MODIFYSAVE");
		}
}

function disactivateCombo()
{
//	alert("inside disactivateCombo");
	document.forms[0].seasonFromDay.disabled=true;
	document.forms[0].seasonFromMonth.disabled=true;
	document.forms[0].seasonToDay.disabled=true;
	document.forms[0].seasonToMonth.disabled=true;
	
//	submitTile("CHOICE");
}

function activateCombo()
{
//	alert("inside activateCombo");
	document.forms[0].seasonFromDay.disabled=false;
	document.forms[0].seasonFromMonth.disabled=false;
	document.forms[0].seasonToDay.disabled=false;
	document.forms[0].seasonToMonth.disabled=false;
	
//	submitTile("CHOICE");
}

function callThisOnload()
{
	var hmode=document.getElementsByName("hmode")[0].value;

	if(hmode=="MODIFY")
	{
	//	document.forms[0].seasonFromDay.disabled=true;
	//	document.forms[0].seasonFromMonth.disabled=true;
	//	document.forms[0].seasonToDay.disabled=true;
	//	document.forms[0].seasonToMonth.disabled=true;
	//	var choice=document.getElementsByName("choice")[0].value;
		if(document.getElementsByName("choice")[0].checked)
		{
			disactivateCombo();
		}
		else
		{
			activateCombo();
		}
		
	}
}
--></script>

<body>
	<html:form action="/master/seasonMaster">
		<his:TransactionContainer>
		<%@ page import="registration.*"%>
		<%boolean varReadOnly=false; %>
		
		<logic:equal name="seasonMasterFB" property="hmode" value="ADD">
			<his:TitleTag name="Season Master >>Add">
			</his:TitleTag>
		</logic:equal>	
		
		<logic:equal name="seasonMasterFB" property="hmode" value="MODIFY">
			<his:TitleTag name="Season Master >>Modify">
			</his:TitleTag>
		</logic:equal>
		
		<logic:equal name="seasonMasterFB" property="hmode" value="VIEW">
			<his:TitleTag name="Season Master >>View">
			</his:TitleTag>
			<%varReadOnly=true; %>
		</logic:equal>
		
			<his:ContentTag>
			
			<logic:equal name="seasonMasterFB" property="hmode" value="MODIFY">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td class="tdfonthead" align="left" width="25%">
							<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif"> 
								<b>
									<bean:message key="updateMode" /> 
								</b> 
							</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="correction" /> 
							</font> 
							<html:radio name="seasonMasterFB" property="choice" tabindex="1" value="<%=RegistrationConfig.CHOICE_MISTAKE%>"  onclick="submitTile('CHOICE')"/>
					
					 		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					 			<bean:message key="modification" /> 
					 		</font> 
					 		<html:radio name="seasonMasterFB" property="choice" tabindex="1" value="<%=RegistrationConfig.CHOICE_ADDITION%>" onclick="submitTile('CHOICE')" />
					 	</td>
				
					</tr>
				</table>
			</logic:equal>
			
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="seasonDesc"/>
	  								</b>
	  							</font>	
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="seasonMasterFB" tabindex="1" property="seasonDesc" maxlength="50" size="35" readonly="<%=varReadOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)">
								</html:text>
							</div>
						</td>	
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="seasonFrom"/>
	  								</b>
	  							</font>	
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="seasonMasterFB" property="seasonFromDay" disabled="<%=varReadOnly %>" tabindex="1">
									<html:option value="-1">Day</html:option>
									<%for(int i=1;i<=9;i++){ 
									String k=Integer.toString(i);
									%>
									<html:option value='<%="0"+k %>'><%="0"+i %></html:option>
									<%} %>
									<%for(int i=10;i<=31;i++){ 
									String k=Integer.toString(i);
									%>
									<html:option value="<%=k %>"><%=i %></html:option>
									<%} %>
								</html:select>
								<html:select name="seasonMasterFB" property="seasonFromMonth" disabled="<%=varReadOnly %>" tabindex="1">
									<html:option value="-1">Month</html:option>
									<html:option value="Jan">January</html:option>
									<html:option value="Feb">February</html:option>
									<html:option value="Mar">March</html:option>
									<html:option value="Apr">April</html:option>
									<html:option value="May">May</html:option>
									<html:option value="Jun">June</html:option>
									<html:option value="Jul">July</html:option>
									<html:option value="Aug">August</html:option>
									<html:option value="Sep">September</html:option>
									<html:option value="Oct">October</html:option>
									<html:option value="Nov">November</html:option>
									<html:option value="Dec">December</html:option>
								</html:select>
							</div>
						</td>	
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="seasonTo"/>
	  								</b>
	  							</font>	
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="seasonMasterFB" property="seasonToDay" disabled="<%=varReadOnly %>" tabindex="1">
									<html:option value="-1">Day</html:option>
									<%for(int i=1;i<=9;i++){ 
									String k=Integer.toString(i);
									%>
									<html:option value='<%="0"+k %>'><%="0"+i %></html:option>
									<%} %>
									<%for(int i=10;i<=31;i++){ 
									String k=Integer.toString(i);
									%>
									<html:option value="<%=k %>"><%=i %></html:option>
									<%} %>
								</html:select>
								
								<html:select name="seasonMasterFB" property="seasonToMonth" disabled="<%=varReadOnly %>" tabindex="1">
									<html:option value="-1">Month</html:option>
									<html:option value="Jan">January</html:option>
									<html:option value="Feb">February</html:option>
									<html:option value="Mar">March</html:option>
									<html:option value="Apr">April</html:option>
									<html:option value="May">May</html:option>
									<html:option value="Jun">June</html:option>
									<html:option value="Jul">July</html:option>
									<html:option value="Aug">August</html:option>
									<html:option value="Sep">September</html:option>
									<html:option value="Oct">October</html:option>
									<html:option value="Nov">November</html:option>
									<html:option value="Dec">December</html:option>
								</html:select>
							</div>
						</td>	
					</tr>
				
				</table>
			</his:ContentTag>
			
			<his:ButtonToolBarTag>
			<logic:equal name="seasonMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validationAdd() && submitTile('SAVE')" onkeypress="if(event.keyCode==13)validationAdd() && submitTile('SAVE')">
			</logic:equal>
			<logic:equal name="seasonMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateModify() && submitTile('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateModify() && submitTile('MODIFYSAVE')">
			</logic:equal>	
			<logic:notEqual name="seasonMasterFB" property="hmode" value="VIEW">	
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">
			</logic:notEqual>	
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
			</his:ButtonToolBarTag>
			
			<html:hidden name="seasonMasterFB" property="hmode"/>
			<html:hidden name="seasonMasterFB" property="seasonCode"/>
			<html:hidden name="seasonMasterFB" property="seasonSlNo"/>
			<html:hidden name="seasonMasterFB" property="chk"/>
			<html:hidden name="seasonMasterFB" property="tempMode"/>
			<html:hidden name="seasonMasterFB" property="choice"/>
		</his:TransactionContainer>
	
	</html:form>
	<his:status/>
</body>
</html>