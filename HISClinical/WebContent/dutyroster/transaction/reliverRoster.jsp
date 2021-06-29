<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig"%>
<%@page import="hisglobal.vo.RosterDtlVO"%>
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/dutyroster/js/reliverRoster.js" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />


<his:css src="/css/calendar-blue2.css" />

<body onload="focusOnLoad()">

  <%
  String sysDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");

  %>
    
  <html:form action="/transaction/ReliverRoster" > 
    
   
    
     <his:TransactionContainer>
     

       <his:TitleTag name="Reliver Duty Assignment">
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
								<bean:message key="year"/>&nbsp;
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">			       		
		   <html:select property="year" name="ReliverRosterFB" tabindex="1" styleClass="textbox" onchange="submitFormData()">
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_YEARS_EMPLOYEE_DUTY_ROSTER_MASTER%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_YEARS_EMPLOYEE_DUTY_ROSTER_MASTER%>" property="value"  labelProperty="label"/>
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
								<bean:message key="month"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">	    	   
		  <html:select property="month" name="ReliverRosterFB" tabindex="1" styleClass="textbox" onchange="submitFormData()">
			      <logic:present  name="<%=DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
				
				   </div>
			     </td>  
			     
		</tr>
				      
		      <tr>    
		      
						      
		      <tr>     
		      	     
		      <td width="25%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="rosterMainCategory"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		  <html:select property="rosterMainCatg" name="ReliverRosterFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_ROSTER_CATEGORY')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY%>" property="value"  labelProperty="label"/>
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
								<bean:message key="rosterCategory"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	   
		  <html:select property="rosterCatg" name="ReliverRosterFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_AREA')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>	
		   		
				   </div>
			     </td>  
			    
			     
		 </tr>
		     
		 
		  <tr>     
		      	     
		      <td width="25%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="dutyArea"/>(<bean:message key="areaType"/>)
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		  <html:select property="areaCode" name="ReliverRosterFB" tabindex="1" styleClass="textbox" onchange="submitPage('GET_EMP_LIST_AND_SHIFTS')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
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
								<bean:message key="reason"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	   
		<html:radio property="reason" name="ReliverRosterFB" value="<%=DutyRosterConfig.RELIVER_REASON_EMPLOYEE %>" onclick="selectReason()" tabindex="1"></html:radio>
		   		<bean:message key="absenceOfEmp"/>
		   		
		<html:radio property="reason" name="ReliverRosterFB" value="<%=DutyRosterConfig.RELIVER_REASON_OVERLOAD %>" onclick="selectReason()" tabindex="1"></html:radio>
		   		<bean:message key="overload"/>
		
				   </div>
			     </td>  
			    
			     
		 </tr>
		
		 </table>
		</his:ContentTag>
		 
		 	 

<his:SubTitleTag name="Reliver Details">
</his:SubTitleTag>   
		
	  
	
		
	  <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
				 	 
		 	 
		  <tr>     
		      	     
		      <td width="25%" class="tdfonthead">
			     <div align="right">
			     
			
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					   			<bean:message key="requestBy"/>
								<bean:message key="employee"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
	    	   
		  <html:select property="requestedEmpId" name="ReliverRosterFB" tabindex="1" styleClass="textbox" onchange="validateReliverEmp('1')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.EMP_LIST_BASED_ON_ROSTER_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.EMP_LIST_BASED_ON_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
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
								<bean:message key="reliverEmp"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	    	   
		  <html:select property="reliverEmpId" name="ReliverRosterFB" tabindex="1" styleClass="textbox" onchange="validateReliverEmp('2')">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.RELIVER_EMP_LIST_BASED_ON_ROSTER_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.RELIVER_EMP_LIST_BASED_ON_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
		
			
				   </div>
			     </td>  
			    
			     
		 </tr>
		
		
		 	 
		  <tr>     
		      	     
		      <td width="25%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="reliver"/> <bean:message key="fromDate"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">        
			      
	    <his:date name="fromDate" dateFormate="%d-%b-%Y" value="<%=sysDate%>" />	   
				
				   </div>
			     </td>  
			     
			     
			       <td width="25%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="reliver"/> <bean:message key="toDate"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	    	   
		<his:date name="toDate" dateFormate="%d-%b-%Y" value="<%=sysDate%>" />			   
		
			
				   </div>
			     </td>  
			    
			     
		 </tr>
		 
		  	 
		  <tr>     
		      	     
		     
			     
			     
			       <td width="25%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="shiftName"/>(<bean:message key="rosterName"/>)
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	    	    	   
		  <html:select property="shiftId" name="ReliverRosterFB" tabindex="1" styleClass="textbox" onchange="">
			    <html:option value="-1">Select Value</html:option>
			       <logic:present  name="<%=DutyRosterConfig.SHIFT_LIST_BASED_ON_ROSTER_CATEGORY%>" >
			          <html:options collection="<%=DutyRosterConfig.SHIFT_LIST_BASED_ON_ROSTER_CATEGORY%>" property="value"  labelProperty="label"/>
			       </logic:present>
		   </html:select>		   
		
			
				   </div>
			     </td>  
			    
			    
			    
			     <td width="5%" class="tdfonthead">
			     <div align="right">
			        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="anyDutyOff"/>
					</font>
				  </div>
			    </td>
			    
			    
			    <td width="5%" class="tdfont">
			      <div align="left">        
	    	   
		<html:checkbox property="isDutyOff" name="ReliverRosterFB" value="Y" onclick="getTheNextDate(document.forms[0].toDate.value,document.forms[0].toDate.value,'nextToReliverToDate',1)"></html:checkbox>		
		<html:text property="nextToReliverToDate" name="ReliverRosterFB"  readonly="true"></html:text>
				   </div>
			     </td>  
			     
			     
			   
			     
			     
		 </tr>
		 
		 
		   <tr>     
		      	     
		     
			     
			     
			   <td width="25%" class="tdfonthead">
			     <div align="right">
			     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="reason"/>	
				</font>	
				  </div>
			   </td>
			    
			    
			    <td width="25%" class="tdfont">
			      <div align="left">		          
	   					<html:textarea property="reasonForReliver" name="ReliverRosterFB"></html:textarea>				
				   </div>
			     </td>  
			    
			    
			    
			     <td width="5%" class="tdfonthead">
			     <div align="left">
			     
				  </div>
			    </td>
			    
			    
			    <td width="5%" class="tdfont">
			      <div align="left">        
	  			   </div>
			     </td>  
			     
			     
			   
			     
			     
		 </tr>
		 
		 
	</table>

	
	
	
      </his:ContentTag>
        </his:TransactionContainer>
      
    


 
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' id="saveButton"   tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			</span>
		</his:ButtonToolBarTag>


    <his:status/>
    
    <html:hidden name="ReliverRosterFB"   property="hmode" />
    <html:hidden name="ReliverRosterFB"   property="concatedData" />
	<html:hidden name="ReliverRosterFB"   property="sysDate" value="<%=sysDate %>" />
	<html:hidden name="ReliverRosterFB"   property="dayOffReliverDate" />

    		
   </html:form>
  </body>
</html>
		     
		   
		  