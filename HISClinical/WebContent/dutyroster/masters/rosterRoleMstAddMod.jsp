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
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/dutyroster/js/rosterRoleMstAddMod.js" />
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />
	
<body onload="focusFirstElementOnLoad()">

  <html:form action="/masters/rosterRoleMstAddModACT" > 
    
   <his:TitleTag name="Roster Role Master">  </his:TitleTag>
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
			      	&nbsp;<html:select name="rosterRoleMstFB" tabindex="1" property="rosterTypeId" styleClass="regcbo" onchange='getDutyRole();' >
						<html:option value="-1">Select Value</html:option>
	  					<logic:present name="<%=DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_LIST%>">
	  						<html:options  collection="<%=DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_LIST%>" property="value" labelProperty="label" />
	  					</logic:present>
		  			</html:select>
		  			</div>
			     </td>  
		      </tr>
		  </table>
		  
		   <table width="100%" border="0"  cellspacing="1" cellpadding="0">
					
					<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">
							<div align="center"><b>Select Duty Role </b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfont">
							<div align="right">
							
								<logic:notEqual name="rosterRoleMstFB" property="hmode" value="GETDUTYROLE">
									<html:select name="rosterRoleMstFB" tabindex="1" property="dutyRole" multiple="true" size="6">
									</html:select>
								</logic:notEqual>		
								<logic:equal name="rosterRoleMstFB" property="hmode" value="GETDUTYROLE">
									<html:select name="rosterRoleMstFB" property="dutyRole" tabindex="1" multiple="true" size="6">
									<logic:present name="<%=DutyRosterConfig.DUTY_ROLE_NOT_IN_ROSTER_ROLE%>">
	  									<html:options  collection="<%=DutyRosterConfig.DUTY_ROLE_NOT_IN_ROSTER_ROLE%>" property="value" labelProperty="label" />
									</logic:present> 
								</html:select>
								</logic:equal>			
							</div>
						</td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif" class="link" onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<logic:notEqual name="rosterRoleMstFB" property="hmode" value="GETDUTYROLE">
									<html:select name="rosterRoleMstFB" tabindex="1" property="selectedDutyRole" multiple="true" size="6">
									</html:select>
								</logic:notEqual>	
								<logic:equal name="rosterRoleMstFB" property="hmode" value="GETDUTYROLE">
								<html:select name="rosterRoleMstFB" tabindex="1" property="selectedDutyRole" multiple="true" size="6">
								<logic:present name="<%=DutyRosterConfig.DUTY_ROLE_IN_ROSTER_ROLE%>">
									<html:options  collection="<%=DutyRosterConfig.DUTY_ROLE_IN_ROSTER_ROLE%>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
								</logic:equal>
							</div>
						</td>
					</tr>
					</table>
			</his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			  	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			</span>
		</his:ButtonToolBarTag>
		 <his:status/>
 	
 	<html:hidden name="rosterRoleMstFB" property="hmode"/>
	<html:hidden name="rosterRoleMstFB" property="serialNo"/>
	<html:hidden name="rosterRoleMstFB" property="fetchedList"/>
	<html:hidden name="rosterRoleMstFB" property="chk"/>
	
	
 	 </html:form>
  </body>
</html>
		     
		   
		  