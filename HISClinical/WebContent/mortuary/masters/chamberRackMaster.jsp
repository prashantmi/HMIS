<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="hisglobal.hisconfig.*" %>
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
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />
<script>

function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateRackNameAndCapacity()
{


var rackName=document.getElementsByName('rackName');
var rackCapacity=document.getElementsByName('rackCapacity');



for(var i=0;i < rackName.length;i++){
   

   
   if(rackName[i].value==""){
   		alert("Please Enter the Rack Name");
   		rackName[i].focus();	 
   		return false;
		  }		//1st if closed
		  
	
	
		  
	if(rackCapacity[i].value==""){
   		alert("Please Enter the Rack Capacity");
   		rackCapacity[i].focus();	 
   		return false;
		  }	   //2nd if closed
	}//for closed 


return true;
}//fn closed


function validateFinalSubmit(){
  //   alert("hii---"+validateRackNameAndCapacity())
     
     // These All Fields are Mandatory
  if(validateRackNameAndCapacity() &&  validateRackName() &&  validateIsActive())
		return true;
	else	
		return false;
		
 } 	
 
 
 
function validateIsActive(){
	if(document.getElementsByName('hmode')[0].value=="MODIFY"){	
		if(comboValidation(document.forms[0].isActive,"Is Active Status"))
			return true;
		else
			return false;
	}//outer if closed 
	return true;
    
} 
	
function finalSubmit(mode)
{
//alert(!validateFinalSubmit())

	if (validateFinalSubmit()) 
		submitPage(mode);
	else
		return;
	
	
}

function clearForm()
 {
   
if( document.getElementsByName('hmode')[0].value=="ADD"){
    	
 

var rackName=document.getElementsByName('rackName');
var rackCapacity=document.getElementsByName('rackCapacity');

    	
    	for(var i=0;i < rackName.length;i++){

 var rackStatusName='rackStatus_'+parseInt(i+1) ;
 
 //alert(rackStatusName); 
   
var rackStatus=document.getElementsByName(rackStatusName); 
   
//alert(rackStatus)   	
		rackName[i].value="" 
	
		
		rackCapacity[i].value=""
		
		rackStatus[1].checked=true;	
	
	
	
	}//for closed 
	
}//if closed of ADD Page
    
    
    if( document.getElementsByName('hmode')[0].value=="MODIFY" ||  document.getElementsByName('hmode')[0].value=="NEW"){
    
      	document.getElementsByName('isActive')[0].value="-1";
    	document.getElementsByName('rackName')[0].value="";
    	document.getElementsByName('rackCapacity')[0].value="";
    	document.getElementsByName('rackStatus_1')[1].checked=true;
  	}
 }
 
 function selectChamberType()
 {
 
 if(document.getElementsByName("chamberType")[1].checked==true)
 		{
 		document.getElementsByName("rackNumbers")[0].value="1";
 		document.getElementsByName("rackNumbers")[0].disabled=true;
 		}
 		else
 		{
 		document.getElementsByName("rackNumbers")[0].value="";
 		document.getElementsByName("rackNumbers")[0].disabled=false;
 		}
 
 				
 } 
 
function validateRackName(){


var rackName=document.getElementsByName('rackName') 

var flag=true;

if(document.getElementsByName('hmode')[0].value=="ADD"){

for(var i=0; i < rackName.length; i++){

for(var j=i+1 ; j < rackName.length ; j++){


		if(rackName[i].value==rackName[j].value){				
						alert('Rack Names Cannot be Same');
						rackName[j].focus();			
						return false;	
									}//inner if closed 		
 
					} //inner for closed 
	       } //outer for closed  

	 return true;

	 }//outer if closed 
	 else
	 return true;
}
</script>

 <body onload="focusFirstElementOnLoad()">


  <html:form action="/master/ChamberRackMaster" > 
    
   <%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
    
 
   <logic:equal name="ChamberRackMstFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
  
     <his:TransactionContainer>

       <logic:equal name="ChamberRackMstFB" property="hmode" value="ADD">
	   		<his:TitleTag name="Chamber Rack Master >> Add">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="ChamberRackMstFB" property="hmode" value="MODIFY">
	   		<his:TitleTag name="Chamber Rack Master >> Modify">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="ChamberRackMstFB" property="hmode" value="VIEW">
	   		<his:TitleTag name="Chamber Rack Master >> View">
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
								<b><bean:message key="chamberName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			            <bean:write property="chamberName" name="ChamberRackMstFB"/>
				   </div>
			     </td>  
		      </tr>
</table>		      
		      
		      
	
	
	
<table width="100%" border="0" cellspacing="1" cellpadding="0">	
	
<logic:notEqual value="VIEW" property="hmode" name="ChamberRackMstFB">	
  <logic:notEqual value="MODIFY" property="hmode" name="ChamberRackMstFB">
	<logic:notEqual value="0" property="rackNumbers" name="ChamberRackMstFB">	  
	<logic:notEqual value="-1" property="rackNumbers" name="ChamberRackMstFB">
	
	<bean:define id="rackNum" property="rackNumbers" name="ChamberRackMstFB" type="java.lang.String"/>
		  
   <tr>
		      <td width="33%" class="tdfonthead">
			     <div align="center">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="rackName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>		  
		  
		  
		 <td width="33%" class="tdfonthead">
			     <div align="center">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="rackStatus"/>&nbsp;</b>
					</font>
				  </div>
			    </td>  
		  
		  
		  <td width="33%" class="tdfonthead">
			     <div align="center">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="rackCapacity"/>&nbsp;</b>
					</font>
				  </div>
			    </td>	  
</tr>


		  
 <%
 
 for(int i=0; i < Integer.parseInt(rackNum) ; i++)
 {
 %>
		  
		  <tr>

			    <td width="33%" class="tdfont">
			      <div align="center">
			            <html:text name="ChamberRackMstFB" property="rackName" tabindex="1"  maxlength="50" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)" />
				   </div>
			     </td>  
		      
			    <td width="33%" class="tdfont">
			      <div align="center">
			      
			      <input type="radio" name="rackStatus_<%=i+1%>" value="0" tabindex="1" >Not Used
			      		&nbsp;
			       <input type="radio" name="rackStatus_<%=i+1%>" value="1" checked="checked" tabindex="1" >Working
			      
			      	   </div>
			     </td>  
		      
			    <td width="33%" class="tdfont">
			      <div align="center">
			     	    <html:text property="rackCapacity" name="ChamberRackMstFB" maxlength="3" size="4" tabindex="1" onkeypress="return validatePositiveIntegerOnly(this,event)"></html:text>
							   
					</div>
			     </td>  
			     
			     
		      </tr>
		  
<%
 }
%>		
			</logic:notEqual>  
 		</logic:notEqual>
	</logic:notEqual>		  
</logic:notEqual>			  
</table>
		  


<logic:notEqual value="ADD" property="hmode" name="ChamberRackMstFB">


<bean:define id="rackStatusId" property="rackStatus" name="ChamberRackMstFB" type="java.lang.String[]"/>
<bean:define id="rackNameId" property="rackName" name="ChamberRackMstFB" type="java.lang.String[]"/>
<bean:define id="rackCapacityId" property="rackCapacity" name="ChamberRackMstFB" type="java.lang.String[]"/>

<table width="100%" border="0" cellspacing="1" cellpadding="0">		
		  
   <tr>
		      <td width="33%" class="tdfonthead">
			     <div align="center">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="rackName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>		  
		  
		  
		 <td width="33%" class="tdfonthead">
			     <div align="center">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="rackStatus"/>&nbsp;</b>
					</font>
				  </div>
			    </td>  
		  
		  
		  <td width="33%" class="tdfonthead">
			     <div align="center">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="rackCapacity"/>&nbsp;</b>
					</font>
				  </div>
			    </td>	  
</tr>


		  
	  
		  <tr>

			    <td width="33%" class="tdfont">
			      <div align="center">
			            <html:text name="ChamberRackMstFB" property="rackName" tabindex="1"  maxlength="50" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)" value="<%=rackNameId[0] %>"/>
				   </div>
			     </td>  
		      
			    <td width="33%" class="tdfont">
			      <div align="center">
			   
			       <input type="radio" name="rackStatus_1" value="0" tabindex="1" <%if(rackStatusId[0].equals("0")){%>checked<%}%> <%if(this.readOnly){%>disabled<%}%> >Not Used
			      		&nbsp;
			       <input type="radio" name="rackStatus_1" value="1" tabindex="1" <%if(rackStatusId[0].equals("1")){%>checked<%}%> <%if(this.readOnly){%>disabled<%}%> >Working
			      
			      	   </div>
			     </td>  
		      
			    <td width="33%" class="tdfont">
			      <div align="center">
			     	    <html:text property="rackCapacity" name="ChamberRackMstFB" maxlength="3" size="4" tabindex="1" onkeypress="return validatePositiveIntegerOnly(this,event)" value="<%=rackCapacityId[0] %>" readonly="<%=this.readOnly %>"/>
							   
					</div>
			     </td>  
			     
			     
		      </tr>
	</table>
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0">	
	
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
				    <html:select name="ChamberRackMstFB" property="isActive" tabindex="1" disabled="<%=this.readOnly %>" >
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
			   
		   <logic:notEqual value="-1" property="rackNumbers" name="ChamberRackMstFB">  
			    <logic:equal name="ChamberRackMstFB" property="hmode" value="ADD">
			    	      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				</logic:equal>
			</logic:notEqual>
			
				
				<logic:notEqual name="ChamberRackMstFB" property="hmode" value="VIEW">
				<logic:notEqual name="ChamberRackMstFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
			
			<logic:notEqual value="-1" property="rackNumbers" name="ChamberRackMstFB">  
				<logic:notEqual name="ChamberRackMstFB" property="hmode" value="VIEW">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual> 
			</logic:notEqual>
			
			
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')" >
			
			
			</span>
		</his:ButtonToolBarTag>
		 
		 <his:status/>

	 
      </his:TransactionContainer>
   
   <html:hidden name="ChamberRackMstFB" property="hmode"/>
	<html:hidden name="ChamberRackMstFB" property="chamberId"/>
	<html:hidden name="ChamberRackMstFB" property="serialNo"/>
    <html:hidden name="ChamberRackMstFB" property="chk"/>
    <html:hidden name="ChamberRackMstFB" property="chamberName"/>
    <html:hidden name="ChamberRackMstFB" property="rackNumbers"/>
    

    
   </html:form>
  
   
  </body>
</html>
		     
		   
		  