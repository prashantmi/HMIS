<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page  import ="registration.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>



<script>

function submitFormToSearch(mode){
 document.getElementsByName("hmode")[0].value=mode;
	alert(document.getElementsByName("hmode")[0].value);
	 document.forms[0].submit();
	
}
function validateEmployeeSearchByID(){
	var flag=false;
	if(document.getElementsByName("patIdNo")[0].value==""){
		alert("Please Enter Employee ID");
	}
	else{
		flag=true;
	}
	return flag;
}
function validateEmployeeSearchByName(){
	var flag=false;
	if(document.getElementsByName("patFirstName")[0].value==""){
		alert("Please Enter Either Employee Or Dependent Name");
		}
		else{
			flag=true;
		}
		return flag;
}

function reflectEmpDtl(){
var val;
var k=0;
var patId
  for(i=0;i<document.getElementsByName("empIdChk").length;i++){
       
       if(document.getElementsByName("empIdChk")[i].checked){            
       k++;
       
       }
           
         if(document.getElementsByName("empIdChk")[i].checked)
         {         
          val=document.getElementsByName("empIdChk")[i].value;
          patId=document.getElementsByName("selectedEmployeeId")[val].value
      //    alert("pat"+document.getElementsByName("selectedEmployeeId")[i].value)
       //   alert("patId"+val)
          }
    	}
    //	alert("val::::::::"+val);
     opener.document.getElementsByName("patIdNo")[0].value=patId
     //alert(opener.document.getElementsByName("patIdNo")[0].value)
   	   populateEmpDtl(val);	
	self.close();
}

function clearForm(){
	
	document.getElementsByName("patIdNo")[0].value="";
	document.getElementsByName("patFirstName")[0].value="";

}	
</script>

<html:form action="/employeeDtlWithDependents">



<%
	boolean varIsNewStatus=false;	
	String varStatus="";
%>

<his:statusNew>
<%varIsNewStatus=true;
varStatus="New";%>	
</his:statusNew>
<%  String msg=""; %>
 <his:TitleTag>
    <his:name>
         <bean:message key="emp/dependentDetails"/>
     </his:name>
       
 </his:TitleTag>




<his:statusNew>
<his:ContentTag>
<table width="100%" cellpadding="0" cellspacing="1">
<tr>	
		<td width="15%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
		<div align="center">
		<bean:message key="employeeid"/>
		</div></font>
		</td>
		
		<td width="5%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
		&nbsp; &nbsp;</font>
		</td>
		
		<td width="15%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
		<div align="center">
		<bean:message key="employee/dependentName"/>
		</div></font>
		</td>
		<td width="5%" class="addtoolbar"
								style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
		&nbsp; &nbsp;</font>
		</td>
		
		
		</tr>
<tr>
		<td width="15%" class="tdfonthead">
		<div align="center">
		<html:text name="EmpDependentFB" property="patIdNo" maxlength="14" tabindex="1" onkeypress="if(event.keyCode==13){ submitFormOnValidate(validateEmployeeSearchByID(),'SEARCHEMPLOYEEBYID');} else {return validateAlphaNumericOnly(event,this)}"/>
		</div>
		</td>

		<td width="5%" class="tdfonthead">
		<div align="center">
		<img src='<his:path src="/hisglobal/images/forward3.gif"/>' tabindex="1" onClick="submitFormOnValidate(validateEmployeeSearchByID(),'SEARCHEMPLOYEEBYID');" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateEmployeeSearchByID(),'SEARCHEMPLOYEEBYID');">
		</div>
		</td>
		
		<td width="15%" class="tdfonthead">
		<div align="center">
		<html:text name="EmpDependentFB" property="patFirstName" maxlength="60" tabindex="1" onkeypress="if(event.keyCode==13) {submitFormOnValidate(validateEmployeeSearchByName(),'SEARCHEMPLOYEEBYNAME'); } else {return validateAlphabetsOnly(event,this)}"/>
		</div>
		</td>
		<td width="5%" class="tdfonthead">
		<div align="center">
		<img src='<his:path src="/hisglobal/images/forward3.gif"/>' tabindex="1" onClick="submitFormOnValidate(validateEmployeeSearchByName(),'SEARCHEMPLOYEEBYNAME');" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateEmployeeSearchByName(),'SEARCHEMPLOYEEBYNAME');">
		</div>
		</td>
				
		</tr>
	</table>
	</his:ContentTag>
</his:statusNew>


<his:statusInProcessWithJsp>
<his:ContentTag>
 <table width="100%" cellspacing="1" cellpadding="0">

 <tr>
  <td width="5%" class="tdfonthead">
 		<div align="center">
	          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b>Select</b>
			  </font>
	    </div>
 </td> 
 <td width="16%" class="tdfonthead" nowrap valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="TTId"/></b>
		              </font>
		            </div>
 </td> 
 <td width="16%" class="tdfonthead" nowrap valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="name"/></b>
		              </font>
		            </div>
 </td> 
 <td width="16%" class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="gender/age"/></b>
		              </font>
		            </div>
 </td> 
 
 
 <td width="16%" class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="realtionship"/></b>
		              </font>
		            </div>
 </td> 
 <td width="16%" class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="crNo"/></b>
		              </font>
		            </div>
 </td>   
 </tr>   
 <logic:notEmpty name="<%=RegistrationConfig.EMPLOYEE_MASTER_VO_ARRAY%>">
 <logic:iterate name="<%=RegistrationConfig.EMPLOYEE_MASTER_VO_ARRAY%>" id="empVO" indexId="sNo" > 
 <tr>
	  <td width ="5%"  class = "tdfont">	   
	  <bean:define id="patIdNo" name="empVO" property="patIdNo" type="java.lang.String"></bean:define>
	   <bean:define id="patNo" name="empVO" property="patNo" type="java.lang.String"></bean:define>
	  
		   <div align="center">  
		   <logic:notPresent name="empVO" property="patCrNo">
	    	   <html:radio  name="EmpDependentFB" property="empIdChk" value="<%=String.valueOf(sNo.intValue())%>" onclick ="reflectEmpDtl();" onkeypress="if(event.keyCode==32) reflectEmpDtl();"  tabindex="1"/>
			</logic:notPresent>	    	   
	       </div>
	  </td>  
	  <html:hidden name="EmpDependentFB" property="selectedEmployeeId" value="<%=patNo %>"/>
	  
	  <td width="16%" class="tdfont" nowrap valign="top">
			<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		           
				<bean:write  name="empVO" property="patNo"/>
				</font>
			</div>
		</td>
	   <td width="16%" class="tdfont" valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			              <bean:write  name="empVO" property="patFirstName"/>
			              <bean:write  name="empVO" property="patMiddleName"/>
			              <bean:write  name="empVO" property="patLastName"/>
		              </font>
		            </div>
      </td>

	 <td width="16%" class="tdfont" nowrap valign="top">
			<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		           
				<bean:write  name="empVO" property="patGenderAge"/>
				</font>
			</div>
		</td>
 
	
       <td width="16%" class="tdfont" valign="top">
				           <div align="center">	           
				              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		     
				              <bean:write  name="empVO" property="relation"/>		              
				              </font>
				            </div>
		</td>    
		<td width="16%" class="tdfont" valign="top">
				           <div align="center">	           
				              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		     
				              <bean:write  name="empVO" property="patCrNo"/>		              
				              </font>
				            </div>
		</td>          
 </tr>		
 </logic:iterate> 
 </logic:notEmpty>

  </table>  
</his:ContentTag> 
</his:statusInProcessWithJsp>  


   <his:ButtonToolBarTag>         
         <div align="center">		 
       <his:statusInProcessWithJsp>
			 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	 </his:statusInProcessWithJsp>
      <logic:equal name="EmpDependentFB" property="hmode" value="">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
	 </logic:equal>
		
	         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) window.close();" tabindex="1" onclick ="window.close();">
         </div>
   </his:ButtonToolBarTag>
  
<html:hidden name="EmpDependentFB" property="hmode" value=""/>
<his:status/>
  </html:form>
</html>