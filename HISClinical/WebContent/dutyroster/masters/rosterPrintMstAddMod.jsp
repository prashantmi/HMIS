<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="hisglobal.vo.DutyRosterPrintPropertiesVO"%>
<%@page import="dutyroster.DutyRosterConfig"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/dutyroster/js/rosterPrintMstAddMod.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<his:css src="/css/calendar-blue2.css" />

<body onload="focusOnLoad()">


<%
int instCounter=0;
int copyToCounter=0;

%>
 
      
  <html:form action="/masters/RosterPrintMstAddModACT" > 
    
  
    
     <his:TransactionContainer>
     

       <his:TitleTag name="Roster Print Master">
        </his:TitleTag>

   
		
	  
	
		
	  <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		
		    
		      <tr>     
		      	     
		      <td width="25%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="rosterCategory"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont"> 
			      <div align="left">        
	    	   
		  <html:select property="rosterCategory" name="RosterPrintMstFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_ROSTER_TYPES')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
				
				   </div>
			     </td>  
			     
			     
			      <td width="25%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="rosterName"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	   
		  <html:select property="rosterType" name="RosterPrintMstFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_ROSTER_PRINT_DETAILS')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>	
		   		
				   </div>
			     </td>  
			    
			     
		 </tr>
		      
		 
			
	</table>
		
		
		
		
<logic:notEmpty name="<%=DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS %>">
	
	<logic:iterate id="rosterPrintMapId" name="<%=DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS %>" type="java.util.Map.Entry">
	
	<bean:define id="rosterPrintMapKey" name="rosterPrintMapId" property="key"></bean:define>
	<bean:define id="rosterPrintMapValue" name="rosterPrintMapId" property="value" type="java.util.Map"></bean:define>
	
		<logic:equal value="1" name="rosterPrintMapKey">
	
			
			<his:ContentTag>
	
	<his:SubTitleTag  name="Instructions">
	
	<div align="right">
		<bean:message key="alignment"/>
		<html:select property="alignInstruction" name="RosterPrintMstFB" tabindex="1">
				<html:option value="1">Left</html:option>
				<html:option value="2">Right</html:option>
				<html:option value="3">Center</html:option>
		</html:select>
		
		</div>
	
	</his:SubTitleTag>
	
			<table width="100%" id="InstructionTableId">
		
		<tr>
		
		<td class="tdfonthead" width="10%"><div align="center"><bean:message key="displayOrder"/></div></td>
		<td class="tdfonthead" width="80%"><div align="center"><bean:message key="displayValue"/></div></td>
		<td class="tdfonthead" width="10%"></td>
		
		</tr>
	
	<logic:iterate id="rosterPrintOrderId" name="rosterPrintMapValue" type="java.util.Map.Entry" indexId="indexOfInstruction">
	
	<bean:define id="rosterPrintOrderKey"  name="rosterPrintOrderId" property="key"></bean:define>	
	<bean:define id="rosterPrintOrderValue"  name="rosterPrintOrderId" property="value"  type="hisglobal.vo.DutyRosterPrintPropertiesVO"  ></bean:define>	
		
	<tr id='InstructionTableId<%=indexOfInstruction.intValue()+2%>'>
	
	
		
		<td class="tdfont" width="10%"><div align="center"><%=rosterPrintOrderKey%></div></td>
		
		<td class="tdfont" width="80%">
		<div align="center">
		<html:text property="displayInstruction" name="RosterPrintMstFB" size="50" value="<%=rosterPrintOrderValue.getDisplayValue()%>" tabindex="1"/>
		</div>
		
			
		</td>
		
		<td class="tdfont" width="10%">
		
		<div align="center">
		
		<%
		instCounter++;
		
	
		if(indexOfInstruction > 0) 
		{
		%>
		<img class="button" id="addButton" style="cursor:pointer" src='/HIS/hisglobal/images/avai/Minus.png' alt="Add Instructions" title="Add Instructions"  onclick='deleteDiagRow(document.getElementById("InstructionTableId<%=indexOfInstruction.intValue()+2%>"),document.getElementById("InstructionTableId"))'>
		<%  }
			else
			{
		%>
		<img class="button" id="addButton" style="cursor:pointer" src='/HIS/hisglobal/images/avai/plus.png' alt="Add Instructions" title="Add Instructions"  onclick='AddRowToTable("InstructionTableId","displayInstruction")'>
		
		
		<%
			}
		%>
		
		</div>
		
		
		
		</td>
		
	</tr>
	
	</logic:iterate>
	
	
			</table>
	
	
	
		
		
			</his:ContentTag>
	
	
	
		
			
	
	
		</logic:equal>
		
		
<c:set var="instCounterId"><%=instCounter%></c:set>


<c:if test="${instCounterId eq 0}">

	<his:ContentTag>
	
	<his:SubTitleTag  name="Instructions">
	
	<div align="right">
		<bean:message key="alignment"/>
		<html:select property="alignInstruction" name="RosterPrintMstFB" tabindex="1">
				<html:option value="1">Left</html:option>
				<html:option value="2">Right</html:option>
				<html:option value="3">Center</html:option>
		</html:select>
		
		</div>
	
	</his:SubTitleTag>
	
			<table width="100%" id="InstructionTableId">
		
		<tr>
		
		<td class="tdfonthead" width="10%"><div align="center"><bean:message key="displayOrder"/></div></td>
		<td class="tdfonthead" width="80%"><div align="center"><bean:message key="displayValue"/></div></td>
		<td class="tdfonthead" width="10%"></td>
		
		</tr>
	
	
		
	<tr>
		
		<td class="tdfont" width="10%"><div align="center">1</div></td>
		
		<td class="tdfont" width="80%"><div align="center"><html:text property="displayInstruction" name="RosterPrintMstFB" size="50"  tabindex="1"/></div>
		
		
		
		</td>
		
		<td class="tdfont" width="10%">
		
		<div align="center">
		
		
		<img class="button" id="addButton" style="cursor:pointer" src='/HIS/hisglobal/images/avai/plus.png' alt="Add Instructions" title="Add Instructions" onkeypress="if(event.keyCode==13)if(true) AddRowToTable('InstructionTableId','displayInstruction') ;" onclick="if(true)AddRowToTable('InstructionTableId','displayInstruction')">
		
		</div>
		
		
		
		</td>
		
	</tr>
	
	
	
	
			</table>
	
	
	
		
		
			</his:ContentTag>
	
	
	
		

  </c:if>
	
	
	
	
		<logic:equal value="2" name="rosterPrintMapKey">
	
			
			<his:ContentTag>
	
	<his:SubTitleTag  name="Roster By">
	
	<div align="right">
		<bean:message key="alignment"/>
		<html:select property="alignRosterBy" name="RosterPrintMstFB" tabindex="1">
				<html:option value="1">Left</html:option>
				<html:option value="2">Right</html:option>
				<html:option value="3">Center</html:option>
		</html:select>
		
		</div>
	
	</his:SubTitleTag>
	
			<table width="100%" id="RosterByTableId">
		
		
	
	<logic:iterate id="rosterPrintOrderId" name="rosterPrintMapValue" type="java.util.Map.Entry" >
	
	<bean:define id="rosterPrintOrderKey"  name="rosterPrintOrderId" property="key"></bean:define>	
	<bean:define id="rosterPrintOrderValue"  name="rosterPrintOrderId" property="value"  type="hisglobal.vo.DutyRosterPrintPropertiesVO"  ></bean:define>	
		
	<tr>
	
	
		
		<td class="tdfont" width="10%"><div align="center"></div></td>
		
		<td class="tdfont" width="80%"><div align="center"><html:textarea property="displayRosterBy" name="RosterPrintMstFB" cols="47"  value="<%=rosterPrintOrderValue.getDisplayValue()%>" tabindex="1"/></div>
	
		
		</td>
		
		<td class="tdfont" width="10%">
		
		<div align="center">
		</div>
		
		</td>
		
	</tr>
	
	</logic:iterate>
	
	
		</table>
	
	</his:ContentTag>
</logic:equal>
	


<logic:equal value="3" name="rosterPrintMapKey">
	
			
<his:ContentTag>
	
	<his:SubTitleTag  name="Copy To">
	
	<div align="right">
		<bean:message key="alignment"/>
		<html:select property="alignCopyTo" name="RosterPrintMstFB" tabindex="1">
				<html:option value="1">Left</html:option>
				<html:option value="2">Right</html:option>
				<html:option value="3">Center</html:option>
		</html:select>
		
		</div>
	
	</his:SubTitleTag>
	
			<table width="100%" id="copyToByTableId">
		
		<tr>
		
		<td class="tdfonthead" width="10%"><div align="center"><bean:message key="displayOrder"/></div></td>
		<td class="tdfonthead" width="80%"><div align="center"><bean:message key="displayValue"/></div></td>
		<td class="tdfonthead" width="10%"></td>
		
		</tr>
	
	<logic:iterate id="rosterPrintOrderId" name="rosterPrintMapValue" type="java.util.Map.Entry" indexId="indexOfCopyTo">
	
	<bean:define id="rosterPrintOrderKey"  name="rosterPrintOrderId" property="key"></bean:define>	
	<bean:define id="rosterPrintOrderValue"  name="rosterPrintOrderId" property="value"  type="hisglobal.vo.DutyRosterPrintPropertiesVO"  ></bean:define>	
		
	<tr id="copyToByTableId<%=indexOfCopyTo.intValue()+2 %>">
	
	
		
		<td class="tdfont" width="10%"><div align="center"><%=rosterPrintOrderKey%></div></td>
		
		<td class="tdfont" width="80%"><div align="center"><html:text property="displayCopyTo" name="RosterPrintMstFB" size="50" value="<%=rosterPrintOrderValue.getDisplayValue()%>" tabindex="1"/></div>
		
		
		
		</td>
		
		<td class="tdfont" width="10%">
		
		<div align="center">
		
		<%
		copyToCounter++;
	
		if(indexOfCopyTo > 0) 
		{
		%>
		<img class="button" id="addButton" style="cursor:pointer" src='/HIS/hisglobal/images/avai/Minus.png' alt="Add Copy To" title="Add Copy To"  onclick='deleteDiagRow(document.getElementById("copyToByTableId<%=indexOfCopyTo.intValue()+2 %>"),document.getElementById("copyToByTableId"))'>
		<%  }
			else																																																
			{
		%>
		<img class="button" id="addButton" style="cursor:pointer" src='/HIS/hisglobal/images/avai/plus.png' alt="Add Copy To" title="Add Copy To"  onclick='AddRowToTable("copyToByTableId","displayCopyTo")'>
		
		
		<%
			}
		%>
		
		</div>
		
		
		
		</td>
		
	</tr>
	
	</logic:iterate>
	
	
			</table>
	
	
	
		
		
			</his:ContentTag>
	
</logic:equal>

	
		
	
	</logic:iterate>
	
	<c:set var="copyToCounterId"><%=copyToCounter%></c:set>
 	

<c:if test="${copyToCounterId eq 0}">

	<his:ContentTag>
	
	<his:SubTitleTag  name="Copy To">
	
	<div align="right">
		<bean:message key="alignment"/>
		<html:select property="alignInstruction" name="RosterPrintMstFB" tabindex="1">
				<html:option value="1">Left</html:option>
				<html:option value="2">Right</html:option>
				<html:option value="3">Center</html:option>
		</html:select>
		
		</div>
		
	</his:SubTitleTag>
	
<table width="100%" id="copyToByTableId">
		
	<tr>
		
		<td class="tdfonthead" width="10%"><div align="center"><bean:message key="displayOrder"/></div></td>
		
		<td class="tdfonthead" width="80%"><div align="center"><bean:message key="displayValue"/></div></td>
		
		<td class="tdfonthead" width="10%"></td>
		
		
	</tr>
	
	
		
	<tr>
		
		<td class="tdfont" width="10%"><div align="center">1</div></td>
		<td class="tdfont" width="80%"><div align="center"><html:text property="displayCopyTo" name="RosterPrintMstFB" size="50" tabindex="1"/></div></td>
		<td class="tdfont" width="10%"><div align="center">
		
		
		<img class="button" id="addButton" style="cursor:pointer" src='/HIS/hisglobal/images/avai/plus.png' alt="Add Instructions" title="Add Instructions" onkeypress="if(event.keyCode==13)if(true) AddRowToTable('copyToByTableId','displayCopyTo') ;" onclick="if(true)AddRowToTable('copyToByTableId','displayCopyTo')">
		
		</div></td>
		
		
	</tr>
	
	
	
	
			</table>
	
	
	
		
		
			</his:ContentTag>

</c:if>	

</logic:notEmpty>
	

		
	<logic:empty name="<%=DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS %>">
	
			<his:ContentTag>
	
	<his:SubTitleTag  name="Instructions">
	
	<div align="right">
		<bean:message key="alignment"/>
		<html:select property="alignInstruction" name="RosterPrintMstFB" tabindex="1">
				<html:option value="1">Left</html:option>
				<html:option value="2">Right</html:option>
				<html:option value="3">Center</html:option>
		</html:select>
		
		</div>
	
	</his:SubTitleTag>
	
			<table width="100%" id="InstructionTableId">
		
		<tr>
		
		<td class="tdfonthead" width="10%"><div align="center"><bean:message key="displayOrder"/></div></td>
		<td class="tdfonthead" width="80%"><div align="center"><bean:message key="displayValue"/></div></td>
		<td class="tdfonthead" width="10%"></td>
		
		</tr>
	
	
		
	<tr>
		
		<td class="tdfont" width="10%"><div align="center">1</div></td>
		
		<td class="tdfont" width="80%"><div align="center"><html:text property="displayInstruction" name="RosterPrintMstFB" size="50" tabindex="1"/></div>
		
		
		
		</td>
		
		<td class="tdfont" width="10%">
		
		<div align="center">
		
		
		<img class="button" id="addButton" style="cursor:pointer" src='/HIS/hisglobal/images/avai/plus.png' alt="Add Instructions" title="Add Instructions" onkeypress="if(event.keyCode==13)if(true) AddRowToTable('InstructionTableId','displayInstruction') ;" onclick="if(true)AddRowToTable('InstructionTableId','displayInstruction')">
		
		</div>
		
		
		
		</td>
		
	</tr>
	
	
	
	
			</table>
	
	
	
		
		
			</his:ContentTag>
	
	
	
		<his:ContentTag>
	
	<his:SubTitleTag  name="Roster By">
	<div align="right">
		<bean:message key="alignment"/>
		<html:select property="alignRosterBy" name="RosterPrintMstFB" tabindex="1">
				<html:option value="1">Left</html:option>
				<html:option value="2">Right</html:option>
				<html:option value="3">Center</html:option>
		</html:select>
		
		</div>
	</his:SubTitleTag>
	
			<table width="100%" id="RosterByTableId">
		
		<tr>
		
		<td class="tdfont" width="10%"></td>
		
		<td class="tdfont" width="80%">
		<div align="center">
		<html:textarea property="displayRosterBy" name="RosterPrintMstFB" cols="47" tabindex="1"></html:textarea>
		</div>
		</td>
		
		<td class="tdfont" width="10%">
		
		</td>
		
		</tr>
	
	
		
			</table>
	
	
	
		
		
			</his:ContentTag>
			
			
			
			
				<his:ContentTag>
	
	<his:SubTitleTag  name="Copy To">
	
	<div align="right">
		<bean:message key="alignment"/>
		<html:select property="alignInstruction" name="RosterPrintMstFB" tabindex="1">
				<html:option value="1">Left</html:option>
				<html:option value="2">Right</html:option>
				<html:option value="3">Center</html:option>
		</html:select>
		
		</div>
		
	</his:SubTitleTag>
	
<table width="100%" id="copyToByTableId">
		
	<tr>
		
		<td class="tdfonthead" width="10%"><div align="center"><bean:message key="displayOrder"/></div></td>
		
		<td class="tdfonthead" width="80%"><div align="center"><bean:message key="displayValue"/></div></td>
		
		<td class="tdfonthead" width="10%"></td>
		
		
	</tr>
	
	
		
	<tr>
		
		<td class="tdfont" width="10%"><div align="center">1</div></td>
		<td class="tdfont" width="80%"><div align="center"><html:text property="displayCopyTo" name="RosterPrintMstFB" size="50" tabindex="1"/></div></td>
		<td class="tdfont" width="10%"><div align="center">
		
		
		<img class="button" id="addButton" style="cursor:pointer" src='/HIS/hisglobal/images/avai/plus.png' alt="Add Instructions" title="Add Instructions" onkeypress="if(event.keyCode==13)if(true) AddRowToTable('copyToByTableId','displayCopyTo') ;" onclick="if(true)AddRowToTable('copyToByTableId','displayCopyTo')">
		
		</div></td>
		
		
	</tr>
	
	
	
	
			</table>
	
	
	
		
		
			</his:ContentTag>
	
	
	</logic:empty>
		
		
      </his:ContentTag>

  
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
				   <img class="button" src='<his:path src="/hisglobal/images/ChangeSequence1.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('CHANGE_ORDER')" onkeypress="if(event.keyCode==13) submitPage('CHANGE_ORDER')">
				 
			</span>
		</his:ButtonToolBarTag>
		
      
  
    <his:status/>
     </his:TransactionContainer>
    
    <html:hidden name="RosterPrintMstFB" property="hmode"/>
	<html:hidden name="RosterPrintMstFB" property="noOfInstructionRow"/>	    
	<html:hidden name="RosterPrintMstFB" property="noOfCopyToRow"/>	 
	<html:hidden name="RosterPrintMstFB" property="concatedValueOfInstruction"/>
	<html:hidden name="RosterPrintMstFB" property="concatedValueOfCopyTo"/>
	<html:hidden name="RosterPrintMstFB" property="concatedValueOfRosterBy"/>
		
   </html:form>
  </body>
</html>
		     
		