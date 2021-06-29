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
<his:javascript src="/dutyroster/js/rosterAreaCapacityMstAddMod.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<his:css src="/css/calendar-blue2.css" />

 <body onload="focusFirstElementOnLoad()">

  <html:form action="/masters/RosterAreaCapacityMstAddModACT" > 
    
   
    
     <his:TransactionContainer>
     
<logic:equal value="ADD" property="hmode" name="RosterAreaCapMstFB">
       <his:TitleTag name="Roster Area Mapping Master >> Add">
       </his:TitleTag>
</logic:equal>
   
 <logic:equal value="MODIFY" property="hmode" name="RosterAreaCapMstFB">  
		<his:TitleTag name="Roster Area Mapping Master >> Modify">
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
								<bean:message key="rosterName"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			      
	<logic:equal name="RosterAreaCapMstFB" property="hmode" value="ADD">	 		
		   <html:select property="rosterId" name="RosterAreaCapMstFB" tabindex="1" styleClass="textbox" onchange="submitDutyAreaType(this)">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_ROSTERS%>" >
			          <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_ROSTERS%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>
	</logic:equal>    		
		   		<logic:equal name="RosterAreaCapMstFB" property="hmode" value="MODIFY">
		   				<html:hidden  name="RosterAreaCapMstFB"  property="rosterId" />
			    		<html:text  name="RosterAreaCapMstFB"  property="rosterName" readonly="true"/>
		   		</logic:equal>
			    		
			    		
			    <logic:equal name="RosterAreaCapMstFB" property="hmode" value="VIEW">
			    <html:hidden  name="RosterAreaCapMstFB"  property="rosterId" />
			    		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 &nbsp;<b><bean:write name="RosterAreaCapMstFB" property="rosterName"/></b> 
								</font>
			    </logic:equal>
			    		   
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
								<bean:message key="dutyAreaType"/>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
<logic:notEqual name="RosterAreaCapMstFB" property="hmode" value="VIEW">				      
	        <html:text property="areaTypeName" name="RosterAreaCapMstFB" tabindex="1" styleClass="textbox" readonly="true"/>
</logic:notEqual>

<logic:equal name="RosterAreaCapMstFB" property="hmode" value="VIEW">
		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
		 &nbsp;<b><bean:write name="RosterAreaCapMstFB" property="areaTypeName"/></b> 
		</font>
 </logic:equal>	
 	        
		 	   </div>
			     </td>  
		      </tr>
		      
		      <tr>     
		      
		     
		      <td width="50%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="dutyArea"/>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
	     
	     	   
 <logic:notEqual name="RosterAreaCapMstFB" property="hmode" value="VIEW">			          
	    	   
		  <html:select property="areaCode" tabindex="1" name="RosterAreaCapMstFB" styleClass="textbox" >
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" >
			          <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
</logic:notEqual>

 <logic:equal name="RosterAreaCapMstFB" property="hmode" value="VIEW">
		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
		 &nbsp;<b><bean:write name="RosterAreaCapMstFB" property="areaTypeName"/></b> 
		</font>
 </logic:equal>		
				   </div>
			     </td>  
		      </tr>
		       <tr>     
		      
	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
		 <bean:write name="RosterAreaCapMstFB" property="dynamicShifts" filter="false"/>   
	 </font>
		            
	</table>
  </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			
	<logic:notEqual name="RosterAreaCapMstFB" property="hmode" value="MODIFY">
	    <logic:notEqual name="RosterAreaCapMstFB" property="hmode" value="VIEW">
			 <logic:notEmpty name="<%=DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE%>">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
			</logic:notEmpty>
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
		</logic:notEqual>
			</logic:notEqual>
			
	    <logic:equal name="RosterAreaCapMstFB" property="hmode" value="MODIFY">
			 <logic:notEmpty name="<%=DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE%>">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
			</logic:notEmpty>
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
		</logic:equal> 
	               <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			
			</span>
		</his:ButtonToolBarTag>
		
      
    </his:TransactionContainer>
    <his:status/>
    
    <html:hidden name="RosterAreaCapMstFB" property="hmode"/>
	<html:hidden name="RosterAreaCapMstFB" property="serialNo"/>
    <html:hidden name="RosterAreaCapMstFB" property="chk"/>
    <html:hidden name="RosterAreaCapMstFB" property="areaTypeCode" />
    

   </html:form>
  </body>
</html>
		     
		   
		  