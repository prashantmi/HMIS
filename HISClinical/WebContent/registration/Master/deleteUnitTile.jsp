<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@page import="hisglobal.hisconfig.Config"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>

<script>
window.history.forward()

function submitTile(mode){
   document.getElementsByName("hmode")[0].value=mode;   
    document.forms[0].submit();

}
function getUnit(obj){
	
	if(obj.value=="-1"){
		submitTile("NEW");
	}
	else{
		submitTile('GETUNIT')
	}	
}

function validateModify()
{
	var len;
	var isValid = true;
	count=0;

	len=document.getElementsByName("chk").length;
	
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("chk")[i].checked){
				count++;
					}
				}
	
    if(count==0){    
        alert("Please Select record");
        return;
    	}
    if(count>1){    
        alert("Editing multiple records not allowed");
        return;
    	}	    	
	else
	{
	// alert("00000");
	submitTile('MOD');
	} 

}



function validate(mode)
{
	var len;
	var isValid = true;
	//int count=0;
	count=0;
//	alert("before assignment");
	len=document.getElementsByName("chk").length;
	//alert("before for");
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("chk")[i].checked){
				count++;
					}
				}
	
    if(count==0){    
        alert("Please Select record");
    	}
	else
	{
	
	submitTile(mode)
	}

}

</script>

<body>
<html:form action="/master/unitMaster.cnt"> 
<his:TransactionContainer>
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
%>
<his:statusNew>
	<%varIsNewStatus=true;
	 varStatus="New";%>
</his:statusNew>
<his:TitleTag name="Unit Master">			
	
</his:TitleTag> 


<his:ContentTag>
			 <table width="100%" border="0"  cellspacing="1" cellpadding="0">    
						  <tr>
						      <td width="80%" class="tdfonthead" nowrap align="right">
							           <div align="right">	 
							           	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							              <b><bean:message key="department"/></b>
							              </font>
							            </div>
						      </td>                        
						      
						      <td width="20%" class="tdfont" nowrap align="left">
							         <div align="left">	           
							             <html:select name="DeletionofUnitFB" tabindex="1" property="departmentCode" styleClass="registrationCmb" onchange="getUnit(this)">
									 			<html:option value="-1">Select Value</html:option>
						  			 			<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property="value" labelProperty="label" />
							   		 	</html:select>
							          </div>
						      </td>	        
						                               
						 </tr>
			 </table>
</his:ContentTag>

<his:statusInProcessWithJsp>
 <logic:notEmpty name="<%=RegistrationConfig.UNIT_MASTER_VO %>">
<his:ContentTag>

 <table width="100%" cellspacing="1" cellpadding="1"> 


 <tr>
 <td width="10%" class="tdfonthead">
 		<div align="center">
	          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b>Select</b>
			  </font>
	    </div>
 </td>
 <td width="45%" class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="unit"/></b>
		              </font>
		            </div>
 </td>       	  
 <td width="45%" class="tdfonthead" nowrap valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="unitType"/></b>
		              </font>
		            </div>
 </td><!--     
  
 
 
 <td width="16%" class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="effectiveFrom"/></b>
		              </font>
		            </div>
 </td> 
 
 <td width="16%" class="tdfonthead" nowrap>
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="effectiveTo"/></b>
		              </font>
		            </div>
 </td>
 --></tr> 
 <logic:iterate name="<%=RegistrationConfig.UNIT_MASTER_VO %>" id="unitMaster" indexId="idx" scope="request">
 <tr> 

 <bean:define  name='unitMaster' property='chk' id ="chk" type="java.lang.String"/>
  <td width ="10%"  class = "tdfont">	   
	   <div align="center">  
    	   <html:checkbox name="DeletionofUnitFB" property="chk" tabindex="1" value="<%=chk%>"/>
       </div>
  </td>  
 <td width="45%" class="tdfont" valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <bean:write  name="unitMaster" property="unitName"/>
		              </font>
		            </div>
 </td>

<td width="45%" class="tdfont" nowrap valign="top">
	<div align="center">	           
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		           
		<bean:write  name="unitMaster" property="isGeneral"/>
		</font>
	</div>
</td><!--
  
  
 

  
 <td width="16%" class="tdfont" valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <bean:write  name="unitMaster" property="effectiveFrom"/>		              
		              </font>
		            </div>
 </td>
  
  <td width="16%" class="tdfont" valign="top">
		           <div align="center">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		             
		              <bean:write  name="unitMaster" property="effectiveTo"/>		              
		              </font>
		            </div>
 </td> 
 --></tr> 
 </logic:iterate>
 </table>
 </his:ContentTag>
 </logic:notEmpty>
  <%varStatus="InProcess";%>
 </his:statusInProcessWithJsp>
  
<html:hidden name="DeletionofUnitFB" property="hmode"/> 	

 
   
 		 <his:ButtonToolBarTag> 	

  <%if(varStatus.equals("InProcess")){%>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitTile('ADD');" tabindex="1" onclick="submitTile('ADD')" >    
          <logic:notEmpty name="<%=RegistrationConfig.UNIT_MASTER_VO %>">
             <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validateModify()&& submitTile('MOD');" tabindex="1" onclick="validateModify()&& submitTile('MOD');" >   
             <img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13)validate('SAVE')" tabindex="1" onclick="validate('SAVE')" >
          </logic:notEmpty>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">

       <%} else{ %>
           <!--             <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">-->
      <%--    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">--%>

         
	  <%} %>
</his:ButtonToolBarTag>
<his:status/>
</his:TransactionContainer>

</html:form>

</body>
</html>