
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />


<script>

function validateRegCatMaster()
{
var valid=true;
       effectiveFrom = document.getElementsByName("effectiveFrom")[0];
       effectiveTo = document.getElementsByName("effectiveTo")[0];
       entryDate = document.getElementsByName("entryDate")[0];
       
	  
       if(!document.getElementsByName('effectiveTo')[0].value=="")
	   {
	   if(!compareDateCall(effectiveFrom,effectiveTo,2,"Effective From Date","Effective To Date"))
	     		 return false;
	   }
	   
	  
	   var checkeffectiveDateFrom=document.getElementById("effectiveFrom1");
	
	if(checkeffectiveDateFrom !=null && checkeffectiveDateFrom != 'null')
	   {
	   
	        if(!compareDateCall(entryDate,effectiveFrom,2,"CurrentDate","Effective From Date"))
	        return false;
       }
	
	 if(document.getElementsByName('remarks')[0].value.length>50)
	 {
	 	alert("Characters in Remarks greater than 50");
	 	return false;
	 }
    if(document.forms[0].regCatName.value==""){
    	alert("Category Name ::: can not be left blank")
    	document.forms[0].regCatName.focus()
    	return false;
    	
    }
    if(document.forms[0].regCatShortName.value==""){
    	alert("Category Short Name ::: can not be left blank")
    	document.forms[0].regCatShortName.focus()
    	return false;
    	
    }
    if(document.forms[0].effectiveFrom.value==""){
    	alert("Effective From ::: can not be left blank")
    	document.forms[0].effectiveFrom.focus()
    	return false;
    }
	valid=true;
	//alert("submit");
	submitTile('SAVE');

	  	  
	
}
function submitTile(mode)
{
   document.getElementsByName("transactionMode")[0].value=mode;  
   document.forms[0].submit();
}


</script>

<body>
<html:form action="/master/modifyRegistrationCatMst">
<his:TransactionContainer>
<%@ page import="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*"%>
		
<%boolean varReadOnly = false;
		  String strReadOnly="false";
%>

<logic:equal name="modifyRegistrationCatMstFB" property="isValid" value="2">
	<%varReadOnly = true;
	strReadOnly="true";
	%>
</logic:equal>
<%String sysDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(RegistrationConfig.SYSADATEOBJECT),"dd-MMM-yyyy");%>

		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
			<his:TitleTag name="Modify Category">
			</his:TitleTag>
		</logic:equal>
		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
			<his:TitleTag name="View Category">
			</his:TitleTag>
		</logic:equal>

<%Date sysDateObject=(Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT); %>
<bean:define name="modifyRegistrationCatMstFB" property="effectiveTo" id="effTo" type="java.lang.String" />
					
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
			<%
			Date effToDate=new Date();
			if(effTo!="")
			{
				effToDate=new Date(effTo);
			}
				if(effToDate.compareTo(sysDateObject)>=0 || effTo.equals(""))
				{
			
				%>
				<tr>
					
					<td class="tdfonthead" align="left" width="25%">
						<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b>
						<bean:message key="updateMode" /> </b> </font>
					
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="correction" /> </font> 
						<html:radio name="modifyRegistrationCatMstFB" property="choice" tabindex="1"
						value="<%=RegistrationConfig.CHOICE_MISTAKE%>"
						onclick="submitTile('MISTAKE');" /> <font color="#000000"
						size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="modification" /> </font> <html:radio name="modifyRegistrationCatMstFB"
						property="choice" tabindex="1"
						value="<%=RegistrationConfig.CHOICE_ADDITION%>"
						onclick="submitTile('ADDITION');" /></td>
				</tr>
			 <%}else{%>
			 <html:hidden name="modifyRegistrationCatMstFB" property="choice" value="2"/>
			 
			 <%} %>
			</logic:equal>
			</table>



			<div>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="30%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
						color="#FF0000">*</font> <bean:message key="regCatName" /> </b> </font>
					</div>
					</td>
					<td width="30%" class="tdfont">
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
							<div align="left">
								<html:text name="modifyRegistrationCatMstFB" maxlength="50" property="regCatName" onkeypress="return validateAlphabetsOnly(event,this)" readonly="<%=varReadOnly%>"/>
							</div>
						</logic:equal> 
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
							<html:text name="modifyRegistrationCatMstFB" maxlength="50" property="regCatName" readonly="true" />
						</div>
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td width="30%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b> <font color="#FF0000">*</font> <bean:message key="regCatShortName" /> </b></font>
					</div>
					</td>
					<td width="30%" class="tdfont">
					<div align="left">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<html:text name="modifyRegistrationCatMstFB" maxlength="3" size="5" property="regCatShortName"  onkeypress="return validateAlphabetsOnly(event,this)" readonly="<%=varReadOnly%>"/>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<html:text name="modifyRegistrationCatMstFB" maxlength="3" size="5" property="regCatShortName" readonly="true" />
					</logic:equal>
					</div>
					</td>
				</tr>
				<!--<tr>
					<td width="30%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b> 
									<font color="#FF0000">*
									</font> 
									<bean:message key="regType" /> 
								</b>
							</font>
						</div>
					</td>
					<td width="30%" class="tdfont">
						<div align="left">
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
							<html:select name="modifyRegistrationCatMstFB" property="regType" disabled="<%=varReadOnly%>">
								<html:option value="-1">Select Value</html:option>
								<html:option value="1">Normal</html:option>
								<html:option value="2">Special</html:option>
								<html:option value="3">Casualty</html:option>
							</html:select>
						</logic:equal>	
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
							<html:select name="modifyRegistrationCatMstFB" property="regType" disabled="true">
								<html:option value="-1">Select Value</html:option>
								<html:option value="1">Normal</html:option>
								<html:option value="2">Special</html:option>
								<html:option value="3">Casualty</html:option>
							</html:select>
						</logic:equal>
						</div>
					</td>	
				</tr>
				--><tr>
					<td width="30%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<font color="#FF0000">*</font> 
					<b> <bean:message key="effectiveFrom" />
					</b> 
					</font>
					</div>
					</td>

					<bean:define name="modifyRegistrationCatMstFB" property="effectiveFrom" id="effFrom" type="java.lang.String" />
					<%if (effFrom.equalsIgnoreCase("") || effFrom == null)
							effFrom = sysDate;

						%>
					<td class="tdfont">
					<div align="left">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
					
						<logic:equal name="modifyRegistrationCatMstFB" property="transactionMode" value="MISTAKE">
							<html:text name="modifyRegistrationCatMstFB" property="effectiveFrom" readonly="true" />
						 </logic:equal>
						<logic:equal name="modifyRegistrationCatMstFB" property="transactionMode" value="ADDITION">
							<his:date name='effectiveFrom' dateFormate="%d-%b-%Y" value="<%=effFrom%>" />
						 </logic:equal>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<html:text name="modifyRegistrationCatMstFB" property="effectiveFrom" readonly="true" />
					</logic:equal>
					</div>
					</td>
				</tr>
				<tr>
					<td width="30%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b> <bean:message key="effectiveTo" /> </b> 
					</font>
					</div>
					</td>
					
					<td width="30%" class="tdfont">
					<div align="left">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						
							<his:date name='effectiveTo' dateFormate="%d-%b-%Y" value="<%=effTo%>" />
						
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<html:text name="modifyRegistrationCatMstFB" property="effectiveTo" readonly="true" />
					</logic:equal>
					</div>
					</td>
				</tr>
				
				

   <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>	 
	  <bean:message key="hl7Code"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	            <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	           		              
	         	  		<html:text name="modifyRegistrationCatMstFB" maxlength="10" size="12" property="hl7Code" onkeypress="return validateNumeric(event)"/>	         
	         		 
	         	</logic:equal>       
	         	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">         
	         	  <html:text name="modifyRegistrationCatMstFB" maxlength="10" size="12" property="hl7Code" readonly="true"/>	         
	         	</logic:equal>	         	
	         </div>
      </td>                                           
  </tr>	   
     
   <tr>

					<td width="30%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b> <bean:message key="remarks" /> </b> 
					</font>
					</div>
					</td>
					<td width="30%" class="tdfont">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<div align="left">
						<html:textarea name="modifyRegistrationCatMstFB" property="remarks" readonly="<%=varReadOnly%>" onkeypress="return validateTextArea(event,this,50)"/>
						</div>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
						<html:textarea name="modifyRegistrationCatMstFB" property="remarks" readonly="true" /></div>
					</logic:equal>
					</td>
					
	</tr>
	
	
  
 
</table>  

</his:ContentTag>   
<html:hidden name="modifyRegistrationCatMstFB" property="transactionMode" />
		<html:hidden name="modifyRegistrationCatMstFB" property="<%=RegistrationConfig.VIEWORMODIFY%>" />
		<html:hidden name="modifyRegistrationCatMstFB" property="isActive" value="<%=Config.IS_VALID_ACTIVE%>"/>
		<html:hidden name="modifyRegistrationCatMstFB" property="chk" />
		<html:hidden name="modifyRegistrationCatMstFB" property="entryDate" value="<%=sysDate%>"/>
		<html:hidden name="modifyRegistrationCatMstFB" property="regCatCode"/>
		<html:hidden name="modifyRegistrationCatMstFB" property="regCatSlNo"/>
		<his:ButtonToolBarTag>
			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')"
					onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
			</logic:equal>
			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>"
				value="modify">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
					style=cursor:pointer tabindex="1" onclick="validateRegCatMaster()"
					onkeypress="if(event.keyCode==13) validateRegCatMaster()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
					style=cursor:pointer tabindex="1" onclick="submitTile('CLEAR')"
					onkeypress="if(event.keyCode==13) submitTile('CLEAR');">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')"
					onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
			</logic:equal>
		</his:ButtonToolBarTag>
		
</his:TransactionContainer>
</html:form>
<his:status/>
</body>
</html>