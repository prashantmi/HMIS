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
<his:javascript src="/dutyroster/js/rosterTypeMstAddMod.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />

 <body onload="focusFirstElementOnLoad()">

  <html:form action="/masters/rosterTypeMstAddModACT" > 
     <%!boolean readOnly; %>
     <%!boolean RosterGenerationReadOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="rosterTypeMstFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
   
    <logic:notEqual name="rosterTypeMstFB" property="hmode" value="ADD">
   		<% this.RosterGenerationReadOnly=true; %>
   </logic:notEqual>
  
        <logic:equal name="rosterTypeMstFB" property="hmode" value="ADD">
	   		<his:TitleTag name="Roster Type Master >> Add">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="rosterTypeMstFB" property="hmode" value="MODIFY">
	   		<his:TitleTag name="Roster Type Master >> Modify">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="rosterTypeMstFB" property="hmode" value="VIEW">
	   		<his:TitleTag name="Roster Type Master >> View">
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
			            &nbsp;<html:text name="rosterTypeMstFB" property="rosterTypeName" tabindex="1"   styleClass="textbox" maxlength="50" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)" >
							   </html:text>
					</div>
			     </td>  
		      </tr>
		  
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="rosterCategory"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
		  			&nbsp;<html:select name="rosterTypeMstFB" property="rosterCategory" tabindex="1"  styleClass="regcbo" disabled="<%=this.readOnly %>" >
						<html:option value="-1">Select Value</html:option>
	  					<logic:present name="<%=DutyRosterConfig.ROSTER_CAT_LIST%>">
	  						<html:options  collection="<%=DutyRosterConfig.ROSTER_CAT_LIST%>" property="value" labelProperty="label" />
	  					</logic:present>
		  			</html:select>
	  				</div>
			     </td>  
		      </tr>
		 
		  
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="areaType"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			       <div align="left">
		  			&nbsp;<html:select name="rosterTypeMstFB" tabindex="1" property="areaType" styleClass="regcbo" disabled="<%=this.readOnly %>" >
						<html:option value="-1">Select Value</html:option>
	  					<logic:present name="<%=DutyRosterConfig.DUTY_AREA_TYPE_LIST%>">
	  						<html:options  collection="<%=DutyRosterConfig.DUTY_AREA_TYPE_LIST%>" property="value" labelProperty="label" />
		  				</logic:present>
		  			</html:select>
	  				</div>
			     </td>  
		      </tr>
		  
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="dutyType"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			       <div align="left">
		  			&nbsp;<html:select name="rosterTypeMstFB" tabindex="1" property="dutyType"  disabled="<%=this.readOnly %>" styleClass="regcbo" >
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=DutyRosterConfig.DUTY_TYPE_OFFICIAL_DAYS %>">Official Days</html:option>
						<html:option value="<%=DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN%>">24X7</html:option>
						<html:option value="<%=DutyRosterConfig.DUTY_TYPE_SUNDAY_HOLIDAY%>">Sunday/Holiday</html:option>
					</html:select>
	  				</div>
			     </td>  
		      </tr>
		  
		  
		      
		      
		    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="rosterGenerationMethod"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			       <div align="left">
		  			&nbsp;<html:select name="rosterTypeMstFB" tabindex="1" property="rosterGenerationMethod" disabled="<%=this.RosterGenerationReadOnly %>" styleClass="regcbo" >
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=DutyRosterConfig.ROSTER_GENERATION_METHOD_MONTHWISE %>">Month Wise</html:option>
						<html:option value="<%=DutyRosterConfig.ROSTER_GENERATION_METHOD_DATE_RANGE_WISE %>">Date Range Wise</html:option>
	  				</html:select>
	  				<% if(this.RosterGenerationReadOnly){ %>
	  					<html:hidden name="rosterTypeMstFB" property="rosterGenerationMethod" ></html:hidden>
	  				<%} %>
	  				</div>
			     </td>  
		      </tr>
		  
		  
		      <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="reliverMode"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			       <div align="left">
		  			&nbsp;<html:select name="rosterTypeMstFB" tabindex="1"  disabled="<%=this.readOnly %>" property="reliverMode" styleClass="regcbo" >
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=DutyRosterConfig.RELIVER_MODE_ROSTER_AREA_WISE %>">Roster Area Wise</html:option>
						<html:option value="<%=DutyRosterConfig.RELIVER_MODE_ROSTER_TYPE_WISE %>">Roster Type Wise</html:option>
	  				</html:select>
	  				</div>
			     </td>  
		      </tr>
		      
		      
		  	  <logic:notEqual name="rosterTypeMstFB" property="hmode" value="ADD">
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
				     &nbsp;<html:select name="rosterTypeMstFB" tabindex="1" property="isActive" disabled="<%=this.readOnly %>" styleClass="regcbo">
						    <html:option value="-1">Select Value</html:option>
							<html:option value="<%=Config.IS_VALID_ACTIVE%>">Active</html:option>
							<html:option value="<%=Config.IS_VALID_INACTIVE%>">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:notEqual>
	    </table>
      </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			   
			    <logic:equal name="rosterTypeMstFB" property="hmode" value="ADD">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				</logic:equal>
				
				<logic:notEqual name="rosterTypeMstFB" property="hmode" value="VIEW">
				<logic:notEqual name="rosterTypeMstFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
				
				<logic:notEqual name="rosterTypeMstFB" property="hmode" value="VIEW">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual> 
				
				
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			
			</span>
		</his:ButtonToolBarTag>
		 
		 <his:status/>
    
    <html:hidden name="rosterTypeMstFB" property="hmode"/>
	<html:hidden name="rosterTypeMstFB" property="rosterTypeId"/>
	<html:hidden name="rosterTypeMstFB" property="serialNo"/>
    <html:hidden name="rosterTypeMstFB" property="chk"/>
  
      
   </html:form>
  </body>
</html>
		     
		   
		  