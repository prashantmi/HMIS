
<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,registration.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/tab.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
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
<%@page import="hisglobal.vo.PatientDetailVO"  %>
<%@page import="hisglobal.vo.AddressVO"  %>
<%@page import="hisglobal.vo.PrimaryCategoryChangeVO"  %>
<%@page import="hisglobal.vo.SecondaryCategoryChangeVO"  %>
<%@page import="hisglobal.vo.UnknownChangeVO"  %>
<%@page import="hisglobal.vo.MlcVO"  %>


<script type="text/javascript">

function showLegends()
{
document.getElementById("legendId").style.display="block";
}


function hideLegends()
{
document.getElementById("legendId").style.display="none";
}

function submitTile(hmode)
{
if(hmode=="CLEAR")
document.forms[0].patCrNo.value="";
document.forms[0].hmode.value=hmode;
document.forms[0].submit();

}
function showComponentDiv(these)
{
var buttonId=these.id;
var idArray=buttonId.split("_");
var divId="div_"+idArray[1];


	if(document.getElementById(divId).style.display=="none")
	{
	document.getElementById(divId).style.display="block"
	document.getElementById(buttonId).src="../hisglobal/images/arrdouble-up.png"
	}
	else
	{
	document.getElementById(divId).style.display="none"
	document.getElementById(buttonId).src="../hisglobal/images/arrdouble-down.png"
	}

}

window.onload=function(){
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
}


</script>



<his:css src="/css/calendar-blue2.css" />

<%@ page import="java.util.*,registration.*,hisglobal.presentation.Status"%>

<html:form  action="/patientAuditTrail">

<%
try{
%>


<%
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
<his:TitleTag name="Patient Audit Trail">



	<b><font size="2"
		face="Verdana, Arial, Helvetica, sans-serif">  </font></b>

</his:TitleTag>
<his:statusNew>
<his:InputCrNoTag name="PatientAuditFB"></his:InputCrNoTag>
 </his:statusNew>
 

<his:statusTransactionInProcess>


<jsp:include page="/registration/patientDetail.cnt" flush="true" />

<his:SubTitleTag name="Patient Demographic Detail">
	<div align="right">
 	<img id="showHide_1" class="button" src='/HISClinical/hisglobal/images/arrdouble-up.png' tabindex="1"
	style=cursor:pointer onkeypress="if(event.keyCode==13) showComponentDiv(this)" onclick="showComponentDiv(this)"> 
 	</div>

</his:SubTitleTag>

<div id="div_1">
<table width="100%">
<tr>

				<td width="10%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="user"/> <bean:message key="name"/>
					</font>
				  </div>
			    </td>
			     <td width="10%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="systemIPAddress"/>
					</font>
				  </div>
			    </td>
			      <td width="25%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="entryDate"/>
								<bean:message key="and"/>
								<bean:message key="time"/>
					</font>
				  </div>
			    </td>	
		      
		         <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="fname"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="mname"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="lname"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="gender"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="dob"/>
					</font>
				  </div>
			    </td>
			  
			     
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="maritalStatus"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="idMark1"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="idMark2"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="fathersName"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="motherName"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="husbandName"/>
					</font>
				  </div>
			    </td>
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="monthlyIncome"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="areaCategory"/>
					</font>
				  </div>
			    </td>
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="contactNo"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="idNo"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="religion"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="nationality"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="nationalId"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="newBorn"/>
					</font>
				  </div>
			    </td>
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="patCardNo"/>
					</font>
				  </div>
			    </td>
			      
			   <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="prevCRNo"/>
					</font>
				  </div>
			    </td>
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="changeRequestBy"/>
					</font>
				  </div>
			    </td>
			    
</tr>
<%String colorCodeForPatDtlVO=""; %>

<logic:empty name="<%=RegistrationConfig.VO_S_OF_HRGT_PATIENT_AUDIT_DTL %>">
		<%colorCodeForPatDtlVO="Blue"; %>
</logic:empty>

<logic:present name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL %>">
<tr>
			
			 <td width="10%" class="tdfont">
			      <div align="center">			  
			            <font color=" <%=colorCodeForPatDtlVO%>">	     		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="seatId"/>
		   			    </font>		   
				   </div>
			     </td>  
			     
			       <td width="10%" class="tdfont">
			      <div align="center">			       		
			      		<font color=" <%=colorCodeForPatDtlVO%>">
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="systemIPAddress"/>
		   				</font>    		   
				   </div>
			     </td>  
			     
			      <td width="10%" class="tdfont">
			      <div align="center">
			      		  <font color=" <%=colorCodeForPatDtlVO%>">			       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="registerDate"/>
		   				  </font>	    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center">		
			      		<font color=" <%=colorCodeForPatDtlVO%>">	       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patFirstName"/>
		   				</font>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center">			       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patMiddleName"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center">			 
			      			<font color=" <%=colorCodeForPatDtlVO%>">      		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patLastName"/>
		   				    </font>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center">			
			      			  <font color=" <%=colorCodeForPatDtlVO%>">       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patGender"/>
		   					  </font>		    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center">
			      			  <font color=" <%=colorCodeForPatDtlVO%>">			       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patDOB"/>
		   					  </font>	    		   
				   </div>
			     </td>  
			     
			    		    		     
			     <td width="5%" class="tdfont">
			      <div align="center">		
			      				<font color=" <%=colorCodeForPatDtlVO%>">	       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patMaritalStatus"/>
		   						</font>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center">			
			      				<font color=" <%=colorCodeForPatDtlVO%>">       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patIdMark1"/>
		   						</font>	    		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center">		
			      				<font color=" <%=colorCodeForPatDtlVO%>">	       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patIdMark2"/>
		   						</font>    		   
				   </div>
			     </td>  
			     			     
			   
			     <td width="5%" class="tdfont">
			      <div align="center">
			      				<font color=" <%=colorCodeForPatDtlVO%>">			       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patGuardianName"/>
		   						</font>    		   
				   </div>
			     </td>  
			     <td width="5%" class="tdfont">
			      <div align="center">		
			      				<font color=" <%=colorCodeForPatDtlVO%>">	       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patMotherName"/>
		   						</font>    		   
				   </div>
			     </td>  
			     <td width="5%" class="tdfont">
			      <div align="center">			 
			      				<font color=" <%=colorCodeForPatDtlVO%>">      		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patHusbandName"/>
		   						</font>    		   
				   </div>
			     </td>  
			    
			     <td width="5%" class="tdfont">
			      <div align="center">	
			      				<font color=" <%=colorCodeForPatDtlVO%>">		       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patMonthlyIncome"/>
		   						</font>
		   								    		   
				   </div>
			     </td>  
			     <td width="5%" class="tdfont">
			      <div align="center">	
			      				<font color=" <%=colorCodeForPatDtlVO%>">		       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patIsUrban"/>
		   						</font>    		   
				   </div>
			     </td>  
			   
			     <td width="5%" class="tdfont">
			      <div align="center">		
			      				<font color=" <%=colorCodeForPatDtlVO%>">	       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patAddContactNo"/>
		   						</font>    		   
				   </div>
			     </td>  
			     
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center">	
			      				<font color=" <%=colorCodeForPatDtlVO%>">		       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patIdNo"/>
		   						</font>    		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center">			   
			      				<font color=" <%=colorCodeForPatDtlVO%>">    		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patReligion"/>
		   						</font>			    		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center">			    
			      				<font color=" <%=colorCodeForPatDtlVO%>">   		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patNationality"/>
		   						</font>    		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center">
			      				<font color=" <%=colorCodeForPatDtlVO%>">				       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patNationalId"/>
		   						</font>    		   
				   </div>
			     </td>  
			     
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center">
			      				<font color=" <%=colorCodeForPatDtlVO%>">			       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="isNewBorn"/>
		   						</font>
		   							    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center">	
			      				<font color=" <%=colorCodeForPatDtlVO%>">		       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="patCardNo"/>
		   						</font>    		   
				   </div>
			     </td>  
			     
			     
			       <td width="5%" class="tdfont">
			      <div align="center">
			      				<font color=" <%=colorCodeForPatDtlVO%>">			       		
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="prevCrNo"/>
		   						</font>    		   
				   </div>
			     </td>  
			     
			       <td width="5%" class="tdfont">
			      <div align="center">			       		
			      				<font color=" <%=colorCodeForPatDtlVO%>">
		   <bean:write name="<%=RegistrationConfig.VO_OF_HRGT_PATIENT_DTL%>" property="requestByName"/>
		   						</font>    		   
				   </div>
			     </td>  
			     
</tr>
</logic:present>
<logic:present name="<%=RegistrationConfig.VO_S_OF_HRGT_PATIENT_AUDIT_DTL %>">
<logic:iterate id="PATIENT_AUDIT_TRAIL_VO_S" name="<%=RegistrationConfig.VO_S_OF_HRGT_PATIENT_AUDIT_DTL %>" type="hisglobal.vo.PatientDetailVO"  indexId="idx1">
<%
String colorCode="";
PatientDetailVO[] _patAuditDetailVO=(PatientDetailVO[])session.getAttribute(RegistrationConfig.VO_S_OF_HRGT_PATIENT_AUDIT_DTL);
if(idx1.intValue()+1==_patAuditDetailVO.length)
	colorCode="Blue";
%>
<tr>
				 <td width="10%" class="tdfont">
			      <div align="center" >	
			      <font color=" <%=colorCode %>">		       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="lastModifySeatId"/>    		   
				   </font>
				   </div>
			     </td>  
			     
			       <td width="10%" class="tdfont">
			      <div align="center" >			       		
			       <font color=" <%=colorCode %>">
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="systemIPAddress"/>    		   
				   </font>
				   
				   </div>
			     </td>  
			     
			      <td width="10%" class="tdfont">
			      <div align="center" >	
			      <font color=" <%=colorCode %>">		       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="lastModifyDate"/>    		   
				   </font>
				   </div>
			     </td>  	
			
			     <td width="5%" class="tdfont">
			      <div align="center" >
			      <font color=" <%=colorCode %>">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patFirstName"/>
		   			</font>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" >		
			      <font color=" <%=colorCode %>">	       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patMiddleName"/>    		   
				   </font>
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" >
			      <font color=" <%=colorCode %>">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patLastName"/>
		   			</font>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" >
			      <font color=" <%=colorCode %>">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patGender"/>
		   			</font>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" >			    
			      <font color=" <%=colorCode %>">   		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patDOB"/>
		   			</font>    		   
				   </div>
			     </td>  
			     
			    
			     
			     <td width="5%" class="tdfont">
			      <div align="center" >			   
			      <font color=" <%=colorCode %>">    		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patMaritalStatus"/>
		   			</font>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" >
			      <font color=" <%=colorCode %>">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patIdMark1"/>
		   			</font>   		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" >
			      <font color=" <%=colorCode %>">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patIdMark2"/>
		   			</font>    		   
				   </div>
			     </td>  
			     
			     
			   
			     <td width="5%" class="tdfont">
			      <div align="center" >			    
			      <font color=" <%=colorCode %>">   		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patGuardianName"/>
		   			</font>    		   
				   </div>
			     </td>  
			     
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" >
			      <font color=" <%=colorCode %>">			      			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patMotherName"/>
		   			</font>    		   
				   </div>
			     </td>  
			     
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" >			
			      
			      <font color=" <%=colorCode %>">       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patHusbandName"/>
		   			</font>
		   			    		   
				   </div>
			     </td>  
			    
			     <td width="5%" class="tdfont">
			      <div align="center">			  
			      <font color=" <%=colorCode %>">     		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patMonthlyIncome"/>
		   			</font>
		       		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center">		
			      <font color=" <%=colorCode %>">	       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patIsUrban"/>
		   			</font>    		   
				   </div>
			     </td>  
			   
			   
			     <td width="5%" class="tdfont">
			      <div align="center">
			      
			      <font color=" <%=colorCode %>">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patAddContactNo"/>
		   			</font>    		   
				   </div>
			     </td>  
			     
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center">			
			      <font color=" <%=colorCode %>">       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patIdNo"/>
		   			</font>    		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %> ">
			      			      		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patReligion"/>    		   
				   </div>
			     </td>  
			     <td width="5%" class="tdfont" >
			      <div align="center" style="color: <%=colorCode %> ">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patNationality"/>    		   
				   </div>
			     </td>  
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %> ">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patNationalId"/>    		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %> ">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="isNewBorn"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %> ">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="patCardNo"/>    		   
				   </div>
			     </td>  
			       <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %> ">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="prevCrNo"/>    		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %> ">			       		
		   <bean:write name="PATIENT_AUDIT_TRAIL_VO_S" property="requestByName"/>    		   
				   </div>
			     </td>  
			     
</tr>

</logic:iterate>
</logic:present>
</table>
</div>

<his:SubTitleTag name="Patient Address Details">
<div align="right">
 	<img id="showHide_2" class="button" src='/HISClinical/hisglobal/images/arrdouble-down.png' tabindex="1"
	style="cursor:pointer;" onkeypress="if(event.keyCode==13) showComponentDiv(this)" onclick="showComponentDiv(this)"> 
 	</div>

</his:SubTitleTag>

<div id="div_2" style="display: none;">
<table width="100%">
<tr>
				  <td width="14%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="user"/> <bean:message key="name"/>
					</font>
				  </div>
			    </td>
			    
			   			    
			     <td width="6%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="systemIPAddress"/>
					</font>
				  </div>
			    </td>
			     
			     <td width="25%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="entryDate"/><bean:message key="and"/><bean:message key="time"/>
					</font>
				  </div>
			    </td>
		      
		         <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="address"/> <bean:message key="type"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="city"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="state"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="pin"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="country"/>
					</font>
				  </div>
			    </td>
			 
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="hno"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="street"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="contactNo"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="location"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="mobileNo"/> 
					</font>
				  </div>
			    </td>
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="faxNo"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="email"/> 
					</font>
				  </div>
			    </td>
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="district"/> <bean:message key="name"/>
					</font>
				  </div>
			    </td>
			      
			    <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="changeRequestBy"/>
					</font>
				  </div>
			    </td>
			    
			    
</tr>
<logic:present name="<%=RegistrationConfig.VO_S_OF_HRGT_PATIENT_ADD_DTL %>">
<logic:iterate id="PATIENT_ADD_DTL_VO_S" name="<%=RegistrationConfig.VO_S_OF_HRGT_PATIENT_ADD_DTL %>" type="hisglobal.vo.AddressVO" indexId="idx2">

<%
String colorCode="";
AddressVO[] _addressVO=(AddressVO[])session.getAttribute(RegistrationConfig.VO_S_OF_HRGT_PATIENT_ADD_DTL);
if(idx2.intValue()+1==_addressVO.length)
	colorCode="Blue";
%>

<tr>

				 <td width="14%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="seatId"/>    		   
				   </div>
			     </td>  
			     
			     
			       <td width="6%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="systemIPAddress"/>    		   
				   </div>
			     </td>  
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="entryDate"/>    		   
				   </div>
			     </td>  
				
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddType"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			    
			      
			      <logic:notEmpty name="PATIENT_ADD_DTL_VO_S" property="patAddCityLocMstValue">   		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddCityLocMstValue"/>    		   
				   </logic:notEmpty>
				  
							
								    
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">
			      			       		
		    <logic:notEmpty name="PATIENT_ADD_DTL_VO_S" property="patAddState">   		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddState"/>    		   
				   </logic:notEmpty>
				   
				    <logic:notEmpty name="PATIENT_ADD_DTL_VO_S" property="patAddStateName">   		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddStateName"/>    		   
				   </logic:notEmpty>
				      		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddPIN"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddCountry"/>    		   
				   </div>
			     </td>  
			     		     	     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddHNo"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddStreet"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddContactNo"/>    		   
				   </div>
			     </td>  
			     
			     
			   
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">	
			      
			      <logic:notEmpty name="PATIENT_ADD_DTL_VO_S" property="patAddCityLoc">   		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddCityLoc"/>    		   
				   </logic:notEmpty>
				   
				     <logic:notEmpty name="PATIENT_ADD_DTL_VO_S" property="patAddCity">   		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddCity"/>    		   
				   </logic:notEmpty>	
		     		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddMobileNo"/>    		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddFaxNo"/>    		   
				   </div>
			     </td>  
			    
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddEmailId"/>    		   
				   </div>
			     </td>  
			     
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">		
			      
			       <logic:notEmpty name="PATIENT_ADD_DTL_VO_S" property="patAddDistrict">   		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddDistrict"/>    		   
				   </logic:notEmpty>
				   
				    <logic:notEmpty name="PATIENT_ADD_DTL_VO_S" property="patAddDistrictMstValue">   		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="patAddDistrictMstValue"/>    		   
				   </logic:notEmpty>	
				   	       		
		     		   
				   </div>
			     </td>  
			   
			   
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_ADD_DTL_VO_S" property="requestByName"/>    		   
				   </div>
			     </td>  
			     
			  
</tr>

</logic:iterate>
</logic:present>
</table>
</div>

<his:SubTitleTag name="Patient Category Change Details">
	<div align="right">
 	<img id="showHide_3" class="button" src='/HISClinical/hisglobal/images/arrdouble-down.png' tabindex="1"
	style="cursor: pointer;" onkeypress="if(event.keyCode==13) showComponentDiv(this)" onclick="showComponentDiv(this)"> 
 	</div>

</his:SubTitleTag>

 <div id="div_3" style="display: none;">

<table width="100%">
<tr>
				  <td width="3%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="user"/> <bean:message key="name"/>
					</font>
				  </div>
			    </td>
			    
			    			    
			     <td width="1%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="systemIPAddress"/>
					</font>
				  </div>
			    </td>
			     
			     <td width="1%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="entryDate"/>
								<bean:message key="and"/>
								<bean:message key="time"/>
					</font>
				  </div>
			    </td>
		      
		         <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="newCategory"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="previousCategory"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="verificationDocument"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="patCardNo"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="issuingAuthority"/>
					</font>
				  </div>
			    </td>
			 
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="remarks"/>
					</font>
				  </div>
			    </td>
			     
			      
			    
			    
			    
</tr>
<logic:present name="<%=RegistrationConfig.VO_S_OF_HRGT_PRICAT_CHANGE_DTL %>">
<logic:iterate id="PATIENT_PRIMARY_CAT_CHANGE_DTL_VO_S" name="<%=RegistrationConfig.VO_S_OF_HRGT_PRICAT_CHANGE_DTL %>" type="hisglobal.vo.PrimaryCategoryChangeVO"  indexId="idx3">
<%
String colorCode="";
PrimaryCategoryChangeVO[] _primaryCatgVO=(PrimaryCategoryChangeVO[])session.getAttribute(RegistrationConfig.VO_S_OF_HRGT_PRICAT_CHANGE_DTL);
if(idx3.intValue()+1==_primaryCatgVO.length)
	colorCode="Blue";
%>


<tr>
			 <td width="3%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_PRIMARY_CAT_CHANGE_DTL_VO_S" property="seatId"/>    		   
				   </div>
			     </td>  
			      
			     
			       <td width="1%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_PRIMARY_CAT_CHANGE_DTL_VO_S" property="systemIPAddress"/>    		   
				   </div>
			     </td>  
			    
			     <td width="1%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_PRIMARY_CAT_CHANGE_DTL_VO_S" property="entryDate"/>    		   
				   </div>
			     </td> 
			
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_PRIMARY_CAT_CHANGE_DTL_VO_S" property="patNewPrimaryCat"/>    		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">    		        		
		   <bean:write name="PATIENT_PRIMARY_CAT_CHANGE_DTL_VO_S" property="patPrevPrimaryCat"/>   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">
			   <bean:write name="PATIENT_PRIMARY_CAT_CHANGE_DTL_VO_S" property="verificationDocumentId"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_PRIMARY_CAT_CHANGE_DTL_VO_S" property="cardNo"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_PRIMARY_CAT_CHANGE_DTL_VO_S" property="issuingAuthority"/>    		   
				   </div>
			     </td>  
			     
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_PRIMARY_CAT_CHANGE_DTL_VO_S" property="remarks"/>    		   
				   </div>
			     </td>  
			     
			   
			   
			  
</tr>

</logic:iterate>
</logic:present>
</table>
</div>

<his:SubTitleTag name="Treatment Category Change Details">
	<div align="right">
 	<img id="showHide_4" class="button" src='/HISClinical/hisglobal/images/arrdouble-down.png' tabindex="1"
	style="cursor:pointer;" onkeypress="if(event.keyCode==13) showComponentDiv(this)" onclick="showComponentDiv(this)"> 
 	</div>

</his:SubTitleTag>

<div id="div_4" style="display: none;">

<table width="100%">
<tr>
				  <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="user"/> <bean:message key="name"/>
					</font>
				  </div>
			    </td>      
			    
			    
			     <td width="1%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="systemIPAddress"/>
					</font>
				  </div>
			    </td>
			    
			     <td width="1%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="entryDate"/>
								<bean:message key="and"/>
								<bean:message key="time"/>
					</font>
				  </div>
			    </td>	
		      
		         <td width="7%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="new"/>	<bean:message key="secondary"/> <bean:message key="category"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="previous"/> <bean:message key="secondary"/> <bean:message key="category"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="remarks"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="validUpto"/>
					</font>
				  </div>
			    </td>
			    
			 	        
			    
</tr>

<logic:present name="<%=RegistrationConfig.VO_S_OF_HRGT_SECCAT_CHANGE_DTL %>">
<logic:iterate id="PATIENT_SECONDARY_CAT_CHANGE_DTL_VO_S" name="<%=RegistrationConfig.VO_S_OF_HRGT_SECCAT_CHANGE_DTL %>" type="hisglobal.vo.SecondaryCategoryChangeVO"  indexId="idx4">

<%
String colorCode="";
SecondaryCategoryChangeVO[] _secondaryCatgVO=(SecondaryCategoryChangeVO[])session.getAttribute(RegistrationConfig.VO_S_OF_HRGT_SECCAT_CHANGE_DTL);
if(idx4.intValue()+1==_secondaryCatgVO.length)
	colorCode="Blue";
%>

<tr>
			  <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_SECONDARY_CAT_CHANGE_DTL_VO_S" property="seatId"/>    		   
				   </div>
			     </td>  
			     
			    
			       <td width="1%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_SECONDARY_CAT_CHANGE_DTL_VO_S" property="systemIPAddress"/>    		   
				   </div>
			     </td>  
			     
			   	 <td width="1%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_SECONDARY_CAT_CHANGE_DTL_VO_S" property="entryDate"/>    		   
				   </div>
			     </td>  
			
			     <td width="7%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_SECONDARY_CAT_CHANGE_DTL_VO_S" property="patNewSecondaryCat"/>    		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">    		        		
		   <bean:write name="PATIENT_SECONDARY_CAT_CHANGE_DTL_VO_S" property="patPrevSecondaryCat"/>   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">
			   <bean:write name="PATIENT_SECONDARY_CAT_CHANGE_DTL_VO_S" property="remarks"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="PATIENT_SECONDARY_CAT_CHANGE_DTL_VO_S" property="validUpto"/>    		   
				   </div>
			     </td>  
			    		    
			     
</tr>

</logic:iterate>
</logic:present>

</table>
</div>


<his:SubTitleTag name="Unknown To Known Conversion Change Details">
<div align="right">
 	<img id="showHide_5" class="button" src='/HISClinical/hisglobal/images/arrdouble-down.png' tabindex="1"
	style="cursor:pointer;" onkeypress="if(event.keyCode==13) showComponentDiv(this)" onclick="showComponentDiv(this)"> 
 	</div>

</his:SubTitleTag>

<div id="div_5" style="display: none;"> 
<table width="100%">
<tr>
			  <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="user"/> <bean:message key="name"/>
					</font>
				  </div>
			    </td>
			     <td width="1%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="systemIPAddress"/>
					</font>
				  </div>
			    </td> 
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="emgUnknownTOKnownConversion"/> <bean:message key="date"/> 
					</font>
				  </div>
			    </td>
		      
		      <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="fname"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="mname"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="lname"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="gender"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="dob"/>
					</font>
				  </div>
			    </td>
			    
			 
			   	    
		         <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="primary"/> <bean:message key="category"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="secondary"/> <bean:message key="category"/>
					</font>
				  </div>
			    </td>
			     
			  
			    <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="changeRequestBy"/>
					</font>
				  </div>
			    </td>	        
			    
</tr>
<logic:present name="<%=RegistrationConfig.VO_S_OF_HRGT_UNKNOWN_CHANGE_DTL %>">
<logic:iterate id="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" name="<%=RegistrationConfig.VO_S_OF_HRGT_UNKNOWN_CHANGE_DTL %>" type="hisglobal.vo.UnknownChangeVO" indexId="idx5">

<%
String colorCode="";
UnknownChangeVO[] _unknownChangeVO=(UnknownChangeVO[])session.getAttribute(RegistrationConfig.VO_S_OF_HRGT_UNKNOWN_CHANGE_DTL);
if(idx5.intValue()+1==_unknownChangeVO.length)
	colorCode="Blue";
%>

<tr>
			
			 <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" property="seatId"/>    		   
				   </div>
			     </td>  
			     
			       <td width="1%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" property="systemIPAddress"/>    		   
				   </div>
			     </td>  
			     
			      <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" property="unknownConversionDate"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" property="patFirstName"/>    		   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">    		        		
		   <bean:write name="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" property="patMiddleName"/>   
				   </div>
			     </td>  
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">
			   <bean:write name="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" property="patLastName"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" property="patGender"/>    		   
				   </div>
			     </td>  
			     
			      <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" property="patDOB"/>    		   
				   </div>
			     </td>  		   
			     
			     		   		     
			    <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" property="patPrimaryCat"/>    		   
				   </div>
			     </td>  
			     
			       <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" property="patSecondaryCat"/>    		   
				   </div>
			     </td>  
			   
			   <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="UNKNOWN_TO_KNOWN_PATIENT_CAT_CHANGE_DTL_VO_S" property="requestByName"/>    		   
				   </div>
			     </td>  
			     
</tr>

</logic:iterate>
</logic:present>
</table>
</div>

<his:SubTitleTag name="Patient Mlc Details">

<div align="right">
 	<img id="showHide_6" class="button" src='/HISClinical/hisglobal/images/arrdouble-down.png' tabindex="1"
	style="cursor:pointer;" onkeypress="if(event.keyCode==13) showComponentDiv(this)" onclick="showComponentDiv(this)"> 
 	</div>

</his:SubTitleTag>

<div id="div_6" style="display: none;">
<table width="100%">
<tr>
		      <td width="14%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="user"/> <bean:message key="name"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="systemIPAddress"/>
					</font>
				  </div>
			    </td> 
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="mlcDate"/> 
					</font>
				  </div>
			    </td>
		      
		      <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="mlcnumber"/>
					</font>
				  </div>
			    </td>
			    
			   
			    
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="policecaseno"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="policeofficername"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="policeDesignation"/>
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="policestation"/>
					</font>
				  </div>
			    </td>
			    
			  <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="officerincharge"/> 
					</font>
				  </div>
			    </td>
			    
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="batchno"/> 
					</font>
				  </div>
			    </td>
			     
			     
			    
		         <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="remarks"/> 
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="patientcondition"/> 
					</font>
				  </div>
			    </td>
			     
			  
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="broughtbyname"/> 
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="broughtbyaddress"/> 
					</font>
				  </div>
			    </td>
			     <td width="5%" class="tdfonthead">
			     <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="broughtByph"/> 
					</font>
				  </div>
			    </td>
			    	        
			    
</tr>
<logic:present name="<%=RegistrationConfig.VO_S_OF_HRGT_PATIENT_MLC_DTL %>">
<logic:iterate id="HRGT_PATIENT_MLC_DTL_VO_S" name="<%=RegistrationConfig.VO_S_OF_HRGT_PATIENT_MLC_DTL %>" type="hisglobal.vo.MlcVO" indexId="idx6">
<%
String colorCode="";
MlcVO[] _mlcVO=(MlcVO[])session.getAttribute(RegistrationConfig.VO_S_OF_HRGT_PATIENT_MLC_DTL);
if(idx6.intValue()+1==_mlcVO.length)
	colorCode="Blue";
%>


<tr>
			
			  <td width="14%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="seatId"/>    		   
				   </div>
			     </td>  
			     
			     
			       <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="systemIPAddress"/>    		   
				   </div>
			     </td>  
			     
			   		 <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">    		        		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="mlcDate"/>   
				   </div>
			     </td> 
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="mlcNo"/>    		   
				   </div>
			     </td>  
			     
			     
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">
			   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="caseNo"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="policeName"/>    		   
				   </div>
			     </td>  
			     
			      <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="policeDesignation"/>    		   
				   </div>
			     </td>  		   
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="policeStation"/>    		   
				   </div>
			     </td>  
			     		     
			      <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="officerIncharge"/>    		   
				   </div>
			     </td>  
			     
			     <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="batchNo"/>    		   
				   </div>
			     </td> 
			     
			      
			          
			    <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="mlcRemark"/>    		   
				   </div>
			     </td>  
			     
			       <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="patCondition"/>    		   
				   </div>
			     </td>  
			   
			   <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="broughtByName"/>    		   
				   </div>
			     </td>  
			      <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="broughtByAddress"/>    		   
				   </div>
			     </td>  
			      <td width="5%" class="tdfont">
			      <div align="center" style="color: <%=colorCode %>">			       		
		   <bean:write name="HRGT_PATIENT_MLC_DTL_VO_S" property="broughtByPhone"/>    		   
				   </div>
			     </td>  
			     
</tr>

</logic:iterate>
</logic:present>
</table>
</div>


 <his:SubTitleTag name="Legends">
<div align="right"><b>


		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font>
				<img src='/HIS/hisglobal/images/avai/arrow_down.gif' onclick="showLegends();">	
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font>
			<img src='/HIS/hisglobal/images/avai/arrow_up.gif' onclick="hideLegends();">

			</div>
			</td>			
		</tr>
		</table>
	
</b></div>

</his:SubTitleTag>	

<div id="legendId" style="display: none;">
 



<table width="100%" cellpadding="0" cellspacing="1">

<tr>

<td class="tdfont" width="10%"><font color="blue">Blue Color</font></td>
<td class="tdfonthead"><div align="left">
<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
Base/First Record
</font>
</div></td> 
</tr>

<tr>
<td class="tdfont" width="10%">Red Color</td>
<td class="tdfonthead"><div align="left">
<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
Modified Records Starting from the Latest
</font>
</div></td> 


</tr>
	
</table>

 </div> 
 
 
 
</his:statusTransactionInProcess>


<his:ButtonToolBarTag>   
	 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer;" tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
          	
</his:ButtonToolBarTag>

<html:hidden name="PatientAuditFB" property="patCrNo" />
<html:hidden name="PatientAuditFB" property="hmode" value="unspecified" />
<%
}
catch(Exception e){e.printStackTrace();}
%>

</html:form>