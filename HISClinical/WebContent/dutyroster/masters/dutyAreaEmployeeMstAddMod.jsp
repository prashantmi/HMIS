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

<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/dutyroster/js/dutyAreaEmployeeMstAddMod.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<his:css src="/css/calendar-blue2.css" />

 <body onload="concatinateEmployeesOnLoad()">

  <html:form action="/masters/DutyAreaEmpMstAddModACT" > 
    
   
    
     <his:TransactionContainer>
     

       <his:TitleTag name="Duty Area Employee Master">
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
								<bean:message key="dutyAreaType"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			      
			  
			       		
		   <html:select property="areaTypeCode" name="DutyAreaEmpMstFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_DUTY_AREA')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA_TYPE%>" >
			          <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA_TYPE%>" property="value"  labelProperty="label"/>
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
								<bean:message key="dutyArea"/>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
	     
	     	   
			          
	    	   
		  <html:select property="areaCode" name="DutyAreaEmpMstFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_ROLE_AND_DESIGNATION')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" >
			          <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA%>" property="value"  labelProperty="label"/>
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
								<bean:message key="designation"/>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
	     
	     	   
			          
	    	   
		  <html:select property="empDesg" name="DutyAreaEmpMstFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_EMPLOYEES')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_DESIGNATION%>" >
			          <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_DESIGNATION%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
				
				   </div>
			     </td>  
		      </tr>
	</table>
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
	<tr>
	
	
		    
		    <td width="40%" class="tdfonthead" align="center">
		    </td>
		    <td width="20%" class="tdfonthead" align="left"><bean:message key="employee"/> </td>
		   
		     <td width="40%" class="tdfonthead" align="center">
		    </td>
		    
	</tr> 
	<tr>
		   <td width="40%" class="tdfont">
		  <div align="center"> 
		   <html:select property="empCollectionLeft" tabindex="1" name="DutyAreaEmpMstFB" multiple="true" size="6">
			           <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_UNSELECTED%>" >
			          <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_UNSELECTED%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>	
		   </div>
		   </td>
		   <td width="20%" class="tdfonthead">
		 
 <img class="button" src='<his:path src="/../HIS/hisglobal/images/avai/forward3.gif"/>' style="cursor: pointer"   onclick="moveRightSingle()">
 <img class="button" src='<his:path src="/../HIS/hisglobal/images/avai/forwardward.gif"/>' style="cursor: pointer"  onclick="moveRightAll()">
 <br>
 <br>
 <img class="button" src='<his:path src="/hisglobal/images/back3.gif"/>' style="cursor: pointer"  onclick="moveLeftSingle()">
 <img class="button" src='<his:path src="/hisglobal/images/backward.gif"/>' style="cursor: pointer"  onclick="moveLeftAll()">
				 		   
		 
		   </td>
		   
		   <td width="40%" class="tdfont">
		  <div align="center"> 
		     <html:select property="empCollectionRight" tabindex="1" name="DutyAreaEmpMstFB" multiple="true" size="6" >
			    
			       <logic:present  name="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_SELECTED%>" >
			          <html:options collection="<%=DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_SELECTED%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>	
		  </div> 
		   </td>
		   
	</tr>    
		  </table>
		  
		
      </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			    <logic:notEqual name="DutyAreaEmpMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="DutyAreaEmpMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="DutyAreaEmpMstFB" property="hmode" value="MODIFY">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:equal> 
	               <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
	               	</span>
		</his:ButtonToolBarTag>
		
      
    </his:TransactionContainer>
    <his:status/>
    
    <html:hidden name="DutyAreaEmpMstFB" property="hmode"/>
	<html:hidden name="DutyAreaEmpMstFB" property="serialNo"/>
    <html:hidden name="DutyAreaEmpMstFB" property="chk"/>
    <html:hidden name="DutyAreaEmpMstFB" property="oldEmpSelectedLeft"/>
    <html:hidden name="DutyAreaEmpMstFB" property="oldEmpSelectedRight"/>
    <html:hidden name="DutyAreaEmpMstFB" property="newEmpSelectedLeft"/>
    <html:hidden name="DutyAreaEmpMstFB" property="newEmpSelectedRight"/>
    

   </html:form>
  </body>
</html>
		     
		   
		  