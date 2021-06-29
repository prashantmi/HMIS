<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="hisglobal.hisconfig.*" %>
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
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/dutyroster/js/rosterShiftMstAddMod.js" />
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />

 <body onload="focusFirstElementOnLoad()">

  <html:form action="/masters/rosterShiftMstAddModACT" > 
   
   
   <%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="rosterShiftMstFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
    
       <logic:equal name="rosterShiftMstFB" property="hmode" value="ADD">
	   		<his:TitleTag name="Roster Shift Master >> Add">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="rosterShiftMstFB" property="hmode" value="MODIFY">
	   		<his:TitleTag name="Roster Shift Master >> Modify">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="rosterShiftMstFB" property="hmode" value="VIEW">
	   		<his:TitleTag name="Roster Shift Master >> View">
			</his:TitleTag>
  	   </logic:equal>
       
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="rosterTypeName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			      
			      <logic:equal name="rosterShiftMstFB" property="hmode" value="ADD">
		  			&nbsp;<html:select name="rosterShiftMstFB" property="rosterTypeId" tabindex="1" styleClass="regcbo" disabled="<%=this.readOnly %>" onchange="getShiftsBasedOnRoster('GET_SHIFTS_BASED_ON_ROSTER',this)">
						<html:option value="-1">Select Value</html:option>
	  					<logic:present name="<%=DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_LIST%>">
	  						<html:options  collection="<%=DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_LIST%>" property="value" labelProperty="label" />
	  					</logic:present>
		  			</html:select>
		  			</logic:equal>
		  			
		  			<logic:notEqual name="rosterShiftMstFB" property="hmode" value="ADD">
		  				<html:text name="rosterShiftMstFB" property="rosterTypeName" readonly="true" />
		  				
		  			</logic:notEqual>
	  				</div>
			     </td>  
		      </tr>
		  </table>
		  
		  <logic:notEqual name="rosterShiftMstFB" property="hmode" value="VIEW">
		  <table width="100%" border="0"  cellspacing="1" cellpadding="0">
					
					<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">
							<div align="center"><b>Select Shift </b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfont">
							<div align="right">
								<html:select name="rosterShiftMstFB" tabindex="1" property="shiftId" multiple="true" size="6">
									<div id="unselectedShiftId">
									<logic:present name="<%=DutyRosterConfig.ESSENTIAL_SHIFT_LIST%>">
	  									<html:options  collection="<%=DutyRosterConfig.ESSENTIAL_SHIFT_LIST%>" property="value" labelProperty="label" />
									</logic:present>
									</div> 
								</html:select>		
							</div>
						</td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link" onClick='moveRightAll("1");' /> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<logic:notEqual name="rosterShiftMstFB" property="hmode" value="MODIFY">
									<html:select name="rosterShiftMstFB" tabindex="1" property="selectedShiftId" multiple="true" size="6">
									</html:select>
								</logic:notEqual>
								<logic:equal name="rosterShiftMstFB" property="hmode" value="MODIFY">
									<html:select name="rosterShiftMstFB" tabindex="1" property="selectedShiftId" multiple="true" size="6">
							<div id="selectedShiftId">		
									<logic:present name="<%=DutyRosterConfig.ROSTER_SHIFT_DETAIL%>">	
										<html:options  collection="<%=DutyRosterConfig.ROSTER_SHIFT_DETAIL%>" property="value" labelProperty="label" />
									</logic:present>
							</div>		
									</html:select>
								</logic:equal>
							</div>
						</td>
					</tr>
					</table>
				</logic:notEqual>	
				<logic:equal name="rosterShiftMstFB" property="hmode" value="VIEW">
					  <table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
						<td width="50%"  class="tdfont">
							<div align="center">
									<html:select name="rosterShiftMstFB" tabindex="1" property="selectedShiftId" multiple="true" size="6" disabled="true">
										
										<logic:present name="<%=DutyRosterConfig.ROSTER_SHIFT_DETAIL%>">	
											<html:options  collection="<%=DutyRosterConfig.ROSTER_SHIFT_DETAIL%>" property="value" labelProperty="label" />
										</logic:present>
										
									</html:select>
								</div>
							</td>
						</tr>	
					</table>	
				</logic:equal>
    			<logic:notEqual name="rosterShiftMstFB" property="hmode" value="ADD">
			          <table width="100%" border="0"  cellspacing="1" cellpadding="0">
			        <tr>
			         <td width="50%" class="tdfonthead">
			          <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b>	<bean:message key="isActive"/>&nbsp;</b>
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     &nbsp;<html:select name="rosterShiftMstFB" property="isActive" tabindex="1" disabled="<%=this.readOnly %>" >
						    <html:option value="-1">Select Value</html:option>
							<html:option value="<%=Config.IS_VALID_ACTIVE%>">Active</html:option>
							<html:option value="<%=Config.IS_VALID_INACTIVE%>">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			      </tr>
			     </table>
			 </logic:notEqual>
      </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			   
			    <logic:equal name="rosterShiftMstFB" property="hmode" value="ADD">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				</logic:equal>
				<logic:notEqual name="rosterShiftMstFB" property="hmode" value="VIEW">
				<logic:notEqual name="rosterShiftMstFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
				<logic:notEqual name="rosterShiftMstFB" property="hmode" value="VIEW">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual> 
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		 
		 <his:status/>
   
    <html:hidden name="rosterShiftMstFB" property="hmode"/>
	<html:hidden name="rosterShiftMstFB" property="serialNo"/>
	<html:hidden name="rosterShiftMstFB" property="fetchedList"/>
	<html:hidden name="rosterShiftMstFB" property="availableList"/>
	<html:hidden name="rosterShiftMstFB" property="rosterType"/>
    <html:hidden name="rosterShiftMstFB" property="chk"/>
         
   </html:form>
  </body>
</html>
		     
		   
		  