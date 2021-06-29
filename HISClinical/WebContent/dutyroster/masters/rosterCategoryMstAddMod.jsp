<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>


<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig;"%>
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
<his:javascript src="/dutyroster/js/rosterCategoryMstAddMod.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>


<his:css src="/css/calendar-blue2.css" />

 <body onload="showDutyOffAccount()">

  <html:form action="/masters/RosterCategoryMstAddModACT" > 
  
     <%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="RosterCatgMstFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
   
    
     <his:TransactionContainer>
       <his:TitleTag name="Roster Category Master">
			</his:TitleTag>
		
		
	  <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		
		 <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="rosterCategory"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">			      
			          &nbsp;<html:text name="RosterCatgMstFB" property="rosterCategoryName" tabindex="1"  maxlength="50"  onkeypress="return validateAlphaNumericOnly(event,this)" size="21"  disabled="<%=this.readOnly %>">
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
								<bean:message key="rosterMainCategory"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			       			     
				 
				  &nbsp;<html:select name="RosterCatgMstFB" property="rosterMainCategoryCode" tabindex="1" styleClass="regcbo"  disabled="<%=this.readOnly %>">
						<html:option value="-1">Select Value</html:option>
	  					<logic:present name="<%=DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY%>">
	  						<html:options  collection="<%=DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY%>" property="value" labelProperty="label" />
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
								<bean:message key="rosterMode"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			       <div align="left">
		  			&nbsp;<html:select name="RosterCatgMstFB" tabindex="1"   property="rosterMode" styleClass="regcbo"   disabled="<%=this.readOnly %>">
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=DutyRosterConfig.ROSTER_MODE_EMPLOYEE_BASED %>">Employee Based</html:option>
						<html:option value="<%=DutyRosterConfig.ROSTER_MODE_LOCATION_BASED %>">Location Based</html:option>
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
								<bean:message key="dutyOffFlag"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
		  			&nbsp;<html:select name="RosterCatgMstFB" tabindex="1" property="dutyOffFlag" styleClass="regcbo"   onchange="showDutyOffAccount();"  disabled="<%=this.readOnly %>">
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=DutyRosterConfig.DUTY_OFF_FLAG_NOT_REQUIRED %>">Not Required</html:option>
						<html:option value="<%=DutyRosterConfig.DUTY_OFF_FLAG_DUTY_OFF_ACCOUNT %>">Duty Off Account</html:option>
						<html:option value="<%=DutyRosterConfig.DUTY_OFF_FLAG_SHIFT_BASED %>">Shift Based</html:option>
	  					
		  			</html:select>
	  				</div>
			     </td>  
		      </tr>
		    </table>  
		      
<div id="acc" style="display: none;">

<table width="100%"  border="0" cellspacing="1" cellpadding="0">
		      

		  	      
		  <tr>
		   	
		      <td width="50%" class="tdfonthead">
			     <div align="right" id="dutyOfAccLabel">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="dutyOffAcc"/>&nbsp;
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="50%" class="tdfont">
			      <div align="left" id="dutyOfAccText">
			            &nbsp;<html:text name="RosterCatgMstFB" property="dutyOffAccount" tabindex="1"   maxlength="1" size="5"  onkeypress="return validateNumeric(event)"  disabled="<%=this.readOnly %>">
							   </html:text>
					</div>
			     </td>  
			    
		      </tr>
</table>
		      
		      
</div>		      
		    
					
<table width="100%"  border="0" cellspacing="1" cellpadding="0">
			  
	 <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="maxOff"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			            &nbsp;<html:text name="RosterCatgMstFB" property="maxOff"  tabindex="1"  maxlength="2" size="5" onkeypress="return validateNumeric(event)"  disabled="<%=this.readOnly %>">
							   </html:text>
					</div>
			     </td>  
	 </tr>
		      
			  <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="maxContinuousOff"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			            &nbsp;<html:text name="RosterCatgMstFB" property="maxContinuousOff" tabindex="1"   maxlength="1" size="5" onkeypress="return validateNumeric(event)"  disabled="<%=this.readOnly %>">
							   </html:text>
					</div>
			     </td>  
		      </tr>
		  
		
		      

<tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="noOfHours"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			            &nbsp;<html:text name="RosterCatgMstFB" property="noOfHours" tabindex="1"   maxlength="3" size="5"  onkeypress="return validateNumeric(event)"  disabled="<%=this.readOnly %>">
							   </html:text>
					</div>
			     </td>  
</tr>

		      		      
			 
			 <logic:equal name="RosterCatgMstFB" property="hmode" value="MODIFY">
			        <tr>
			         <td width="50%" class="tdfonthead">
			          <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isActive"/>&nbsp;
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     &nbsp;<html:select name="RosterCatgMstFB" property="isValid" tabindex="1"  styleClass="regcbo">
						    <html:option value="-1">Select Value</html:option>
							<html:option value="1">Active</html:option>
							<html:option value="2">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:equal>
			  
		      
	    </table>
      </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			    <logic:notEqual name="RosterCatgMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="RosterCatgMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="RosterCatgMstFB" property="hmode" value="MODIFY">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"   style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:equal> 
	               <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		
      
    </his:TransactionContainer>
    <his:status/>
   
    <html:hidden name="RosterCatgMstFB" property="hmode"/>
	<html:hidden name="RosterCatgMstFB" property="rosterCategoryCode"/>
	<html:hidden name="RosterCatgMstFB" property="serialNo"/>
    <html:hidden name="RosterCatgMstFB" property="chk"/>
   
   
   
   </html:form>
  </body>
</html>
		     
		   
		  