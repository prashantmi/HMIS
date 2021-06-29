<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />


<script>
window.history.forward()


function isEmptyWithoutFocus(obj,name)
{
  	if(obj!=null && obj!='undefined')
	{
	var value=obj.value;
		if(value=="" || value=="-1")
		{
			alert("Please Enter the "+ name);
			obj.value=value;
			return false;
		}
		else
		return true;
	}
	return false;
}
function compareDateCall(d1,d2,mode,l1,l2){
//alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmptyWithoutFocus(d1,l1) && isEmptyWithoutFocus(d2,l2)){
 if(compareDate(d1,d2, mode)){
		valid = true;
	}
 else {
	alert(l1+" can not be greater than "+l2);
	valid = false;
	}
}

else
valid=false;

return valid;
}

function validateDate()
{//alert("validateDate")
	var valid=true;
	var checkeffectiveDateFrom=document.getElementById("effectiveFrom1");
	
	if(checkeffectiveDateFrom !=null && checkeffectiveDateFrom != 'null')
	   {
	   
	        if(!compareDateCall(entryDate,effectiveFrom,2,"CurrentDate","Effective From Date"))
	        valid= false;
       }
    if(!document.getElementsByName('effectiveTo')[0].value=="")
	   {
	   if(!compareDateCall(effectiveFrom,effectiveTo,2,"Effective From Date","Effective To Date"))
	   valid= false;
	   }
	return valid;
}

function validateRemarks()
{	var valid=true;
//alert("validateRemarks")
	if(document.getElementsByName('remarks')[0].value.length>50)
	 {
	 	alert("Characters in Remarks greater than 50");
	 	valid= false;
	 }
	 return valid;
}



function validatePatCatMaster()
{

		var valid=true;
       effectiveFrom = document.getElementsByName("effectiveFrom")[0];
       effectiveTo = document.getElementsByName("effectiveTo")[0];
       entryDate = document.getElementsByName("entryDate")[0];
       

     
	 
    if(
	   isEmpty(document.forms[0].patCatType,"Patient Category Type")&&
       isEmpty(document.forms[0].patCatName,"Patient Category Name")&&
	   isEmpty(document.forms[0].patCatShortName,"Patient Category Short Name")&&
	   isSelected(document.forms[0].priority,"Is Priority") && 
	   isSelected(document.forms[0].isExpiry,"Is Expiry") && 
	   validateIsExpiry() &&
	  	 
	   validateRemarks() 
	    )
     
	    {
	  	valid=true;
	  //	alert("submit");
	  	 submitTile('SAVE');
	  	 
	  	}
	  	  
	else
	{
	//	alert("valid=false");
		valid=false;
		
	}
	
}
function submitTile(mode)
{
   document.getElementsByName("transactionMode")[0].value=mode;  

   document.forms[0].submit();
}

function showRow()
{
	var isExpiry=document.getElementsByName("isExpiry")[0].value;
			
			if(isExpiry==-1)
			{
				document.getElementById("monthRow").style.display="none";
				document.getElementById("dayRow").style.display="none";
				//document.getElementsByName("expiryMonth")[0].value="";
				//document.getElementsByName("expiryDay")[0].value="";
			}
			if(isExpiry==0)
			{
				document.getElementById("monthRow").style.display="none";
				document.getElementById("dayRow").style.display="none";
				document.getElementsByName("expiryMonth")[0].value="-1";
				document.getElementsByName("expiryDay")[0].value="";
			}
			if(isExpiry==1)
			{
				document.getElementById("monthRow").style.display="";
				document.getElementById("dayRow").style.display="none";
				document.getElementsByName("expiryDay")[0].value="";
			}
			if(isExpiry==2)
			{
				document.getElementById("monthRow").style.display="none";
				document.getElementById("dayRow").style.display="";
				document.getElementsByName("expiryMonth")[0].value="-1";
			}
}
function callThisOnload()
		{
			showRow()
		}
		
function validateIsExpiry()
		{
			var valid=true;
	
			if(document.getElementsByName("isExpiry")[0].value==1)
			{ 
				valid=isSelected(document.forms[0].expiryMonth,"Expiry Month")
			}
			if(document.getElementsByName("isExpiry")[0].value==2)
			{
				valid=isEmpty(document.forms[0].expiryDay,"Expiry Day")
			}
			
			return valid;
		}		

</script>

<body>
<html:form action="/master/modifyPatientCategoryMaster">
<his:TransactionContainer>
<%@ page import="java.util.*,registration.*,hisglobal.presentation.*"%>
		
<%boolean varReadOnly = false;
		  String strReadOnly="false";
%>


<%String sysDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(RegistrationConfig.SYSADATEOBJECT),"dd-MMM-yyyy");%>

		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
			<his:TitleTag name="Modify Category">
			</his:TitleTag>
		</logic:equal>
		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
			<his:TitleTag name="View Category">
			</his:TitleTag>
		</logic:equal>

<%Date sysDateObject=(Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT); %>
<bean:define name="modifyPatientCategoryMasterFB" property="effectiveTo" id="effTo" type="java.lang.String" />
<bean:define name="modifyPatientCategoryMasterFB" property="expiryDate" id="expiryDate" type="java.lang.String" />
<his:ContentTag>
			
			<div>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			
			<tr>
	  				 <td width="50%"  class="tdfonthead">
		           		<div align="right">	           
		              	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              	<b><font color="#FF0000">*</font><bean:message key="patCatType"/></b>
		              	</font>
		            	</div>
	   				</td>  
	   				<td width="50%"  class="tdfont">
	        		 <div align="left">
	          <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
	                <html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="patCatType"  disabled="false">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:option value="<%=RegistrationConfig.PATIENT_CATEGORY_TYPE_PATIENT_CATEGORY %>">Patient Category</html:option>
  			 		<html:option value="<%=RegistrationConfig.PATIENT_CATEGORY_TYPE_TREATMENT_CATEGORY %>">Treatment Category</html:option>
  			 		<html:option value="<%=RegistrationConfig.PATIENT_CATEGORY_TYPE_OTHER %>">Other</html:option>
	   		 		</html:select>
	   		 	</logic:equal>
	   		 	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view" >
	   		 		<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="patCatType"  disabled="true">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:option value="<%=RegistrationConfig.PATIENT_CATEGORY_TYPE_PATIENT_CATEGORY %>">Patient Category</html:option>
  			 		<html:option value="<%=RegistrationConfig.PATIENT_CATEGORY_TYPE_TREATMENT_CATEGORY %>">Treatment Category</html:option>
  			 		<html:option value="<%=RegistrationConfig.PATIENT_CATEGORY_TYPE_OTHER %>">Other</html:option>
	   		 		</html:select>
	   		 	</logic:equal>	
	   		 		</div>
	   		 		</td>
	 			 </tr> 
	 			 
	 			 
				<tr>
					<td width="50%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
						color="#FF0000">*</font> <bean:message key="patCatName" /> </b> </font>
					</div>
					</td>
					<td width="50%" class="tdfont">
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
							<div align="left">
								<html:text name="modifyPatientCategoryMasterFB" maxlength="30"  tabindex="1"  property="patCatName" onkeypress="return validateAlphabetsOnly(event,this)" readonly="<%=varReadOnly%>"/>
							</div>
						</logic:equal> 
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
							<html:text name="modifyPatientCategoryMasterFB" maxlength="30" tabindex="1" property="patCatName" readonly="true" />
						</div>
						</logic:equal>
					</td>
				</tr>
				
				
				<tr>
					<td width="50%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b> <font color="#FF0000">*</font> <bean:message key="patCatShortName" /> </b></font>
					</div>
					</td>
					<td width="50%" class="tdfont">
					<div align="left">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<html:text name="modifyPatientCategoryMasterFB" tabindex="1" maxlength="3" size="5" property="patCatShortName"  onkeypress="return validateAlphabetsOnly(event,this)" readonly="<%=varReadOnly%>"/>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<html:text name="modifyPatientCategoryMasterFB" maxlength="3" tabindex="1" size="5" property="patCatShortName" readonly="true" />
					</logic:equal>
					</div>
					</td>
				</tr>


	<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<font color="#FF0000">*
											</font>
											 <bean:message key="isPriority" />
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
								<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
									<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="priority" disabled="false">
								 		<html:option value="-1">Select Value</html:option>
						  		 		<html:option value="1">Yes</html:option>
						  		 		<html:option value="0">No</html:option>
						  		 	</html:select>
						  		 </logic:equal>
						  		 <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view"> 
						  		 	<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="priority" disabled="true">
								 		<html:option value="-1">Select Value</html:option>
						  		 		<html:option value="1">Yes</html:option>
						  		 		<html:option value="0">No</html:option>
						  		 	</html:select>
						  		 </logic:equal>	
								</div>	
							</td>
						</tr>
						
<tr>
      <td width="50%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>	 
	  <bean:message key="idRequired"/></b>
	  </font>
	  </div>
      </td>      
      <td width="50%" class="tdfont">
	         <div align="left">	 
	            <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	           		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="yes" /> 
					</font> 
					<html:radio name="modifyPatientCategoryMasterFB" property="idRequired" tabindex="1"	value="<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>" />
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="no" /> 
					</font> 
					<html:radio name="modifyPatientCategoryMasterFB" property="idRequired" tabindex="1"	value="<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_NO%>" />	         
	         		 
	         	</logic:equal>       
	         	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">         
	         		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="yes" /> 
					</font> 
					<html:radio name="modifyPatientCategoryMasterFB" property="idRequired" tabindex="1"	value="<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>" />
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="no" /> 
					</font> 
					<html:radio name="modifyPatientCategoryMasterFB" property="idRequired" tabindex="1"	value="<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_NO%>" />  	         
	         	</logic:equal>	         	
	         </div>
      </td>                                           
  </tr>	
  
						
<tr>
	  				 <td width="50%"  class="tdfonthead">
		           		<div align="right">	           
		              	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              	<font color="#FF0000">*</font>
		              	<b><bean:message key="isExpiry"/></b>
		              	</font>
		            	</div>
	   				</td>  
	   				<td width="50%" class="tdfont">
								<div align="left">
								<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
									<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="isExpiry" onchange="showRow()" disabled="false">
								 		<html:option value="-1">Select Value</html:option>
					  			 		<html:option value="0">Not Required</html:option>
					  			 		<%--<html:option value="1">Month Based</html:option>--%>
					  			 		<html:option value="2">Day Based</html:option>
						   		 	</html:select>
						   		 </logic:equal>	
						   		 <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						   		 	<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="isExpiry" disabled="true" >
								 		<html:option value="-1">Select Value</html:option>
					  			 		<html:option value="0">Not Required</html:option>
					  			 		<%--<html:option value="1">Month Based</html:option>--%>
					  			 		<html:option value="2">Day Based</html:option>
						   		 	</html:select>
						   		 </logic:equal>	
								</div>	
							</td>
	 			 </tr> 										
</table>


	<div id="monthRow">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
  						<tr id="rowMonth" >
  							<td width="50%" class="tdfonthead">
  								<div align="right"   id="divMonthLabel">
  									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
            							<b>
            								<font color="#FF0000">*
            								</font>
            								<bean:message key="month"/>
            							</b>
  								</div>
  							</td>
  							<td width="50%" class="tdfont">
  								<div align="left" id="divMonthControl">
						  			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						  			<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="expiryMonth" disabled="false" >
										<html:option value="-1">Select Value</html:option>
							  			<html:option value="31-Jan">January</html:option>
							  			<html:option value="28-Feb">February</html:option>
							  			<html:option value="31-Mar">March</html:option>
							  			<html:option value="30-Apr">April</html:option>
							  			<html:option value="31-May">May</html:option>
							  			<html:option value="30-Jun">June</html:option>
							  			<html:option value="31-Jul">July</html:option>
							  			<html:option value="31-Aug">August</html:option>
							  			<html:option value="30-Sep">September</html:option>
							  			<html:option value="31-Oct">October</html:option>
							  			<html:option value="30-Nov">November</html:option>
							  			<html:option value="31-Dec">December</html:option>
						  			</html:select>
						  			</logic:equal>
						  			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						  			<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="expiryMonth" disabled="true" >
										<html:option value="-1">Select Value</html:option>
							  			<html:option value="31-Jan">January</html:option>
							  			<html:option value="28-Feb">February</html:option>
							  			<html:option value="31-Mar">March</html:option>
							  			<html:option value="30-Apr">April</html:option>
							  			<html:option value="31-May">May</html:option>
							  			<html:option value="30-Jun">June</html:option>
							  			<html:option value="31-Jul">July</html:option>
							  			<html:option value="31-Aug">August</html:option>
							  			<html:option value="30-Sep">September</html:option>
							  			<html:option value="31-Oct">October</html:option>
							  			<html:option value="30-Nov">November</html:option>
							  			<html:option value="31-Dec">December</html:option>
						  			</html:select>
						  			</logic:equal>
						  		</div>
  						  	</td>
						</tr></table>
					</div>	
  				
   					<div id="dayRow">	
   					<table width="100%" border="0" cellspacing="1" cellpadding="0">		
	  					<tr id="rowDay" >
	  						<td width="50%"  class="tdfonthead">
	  							<div align="right" id="divDayLabel">
	  								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            						<b>
	            							<font color="#FF0000">*
	            							</font>
	            							<bean:message key="day"/>
	            						</b>
						  		</div>
						  	</td>
	  						<td width="50%" class="tdfont">	
	  							<div align="left" id="divDayControl">
	  								<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	  								<html:text name="modifyPatientCategoryMasterFB" maxlength="3" size="5" property="expiryDay" onkeypress="return validateNumeric(event)" readonly="false"/>
	  								</logic:equal>
	  								<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view"> 
	  								<html:text name="modifyPatientCategoryMasterFB" maxlength="3" size="5" property="expiryDay" onkeypress="return validateNumeric(event)" readonly="true"/>
	  								</logic:equal>
	  							</div>
	  						</td>
	  					</tr></table>
	  				</div>
	  				
	  				
<table width="100%" border="0" cellspacing="1" cellpadding="0">

 <tr>

					<td width="50%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b> <bean:message key="criteria" /> </b> 
					</font>
					</div>
					</td>
					<td width="50%" class="tdfont">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<div align="left">
						<html:textarea name="modifyPatientCategoryMasterFB" tabindex="1" property="criteriaRemarks" readonly="<%=varReadOnly%>" onkeypress="return CheckMaxLength(event,this,100,1)"/>
						</div>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
						<html:textarea name="modifyPatientCategoryMasterFB" tabindex="1" property="criteriaRemarks" readonly="true" /></div>
					</logic:equal>
					</td>
					
	</tr>
	
	
	 <tr>

					<td width="50%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b> <bean:message key="benefits" /> </b> 
					</font>
					</div>
					</td>
					<td width="50%" class="tdfont">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<div align="left">
						<html:textarea name="modifyPatientCategoryMasterFB" tabindex="1" property="categoryBenefits" readonly="<%=varReadOnly%>" onkeypress="return CheckMaxLength(event,this,200,1)"/>
						</div>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
						<html:textarea name="modifyPatientCategoryMasterFB" tabindex="1" property="categoryBenefits" readonly="true" /></div>
					</logic:equal>
					</td>
					
	</tr>
					
					

				
									
<tr>

					<td width="50%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b> <bean:message key="remarks" /> </b> 
					</font>
					</div>
					</td>
					<td width="50%" class="tdfont">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<div align="left">
						<html:textarea name="modifyPatientCategoryMasterFB" tabindex="1" property="remarks" readonly="<%=varReadOnly%>" onkeypress="return CheckMaxLength(event,this,50,1)"/>
						</div>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
						<html:textarea name="modifyPatientCategoryMasterFB" tabindex="1" property="remarks" readonly="true" onkeypress="return CheckMaxLength(event,this,50,1)"/></div>
					</logic:equal>
					</td>
					
</tr>					
	 			
				
				
				
			
				
				</table>
			
			
	  				
<%-- 	  				

<table width="100%" border="0" cellspacing="1" cellpadding="0">	
				
			
				

 
   <tr>
	  				 <td width="50%"  class="tdfonthead">
		           		<div align="right">	           
		              	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              	<b><font color="#FF0000">*</font><bean:message key="isPaid"/></b>
		              	</font>
		            	</div>
	   				</td>  
	   				<td width="50%"  class="tdfont">
	        		 <div align="left">
	           <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
	                <html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="isPaid"  disabled="false">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:option value="0">Yes </html:option>
  			 		<html:option value="1">No</html:option>
	   		 		</html:select>
	   		 	</logic:equal>
	   		 	 <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
	   		 	 	<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="isPaid" disabled="true" >
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:option value="0">Yes </html:option>
  			 		<html:option value="1">No</html:option>
	   		 		</html:select>
	   		 	 </logic:equal>	
	   		 		</div>
	   		 		</td>
	 			 </tr> 
   
 
 

 

   <tr>
      <td width="50%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>	 
	  <bean:message key="hl7Code"/></b>
	  </font>
	  </div>
      </td>      
      <td width="50%" class="tdfont">
	         <div align="left">	 
	            <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	           		              
	         	  		<html:text name="modifyPatientCategoryMasterFB" maxlength="10" size="12"  tabindex="1"  property="hl7Code" onkeypress="return validateNumeric(event)"/>	         
	         		 
	         	</logic:equal>       
	         	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">         
	         	  <html:text name="modifyPatientCategoryMasterFB" maxlength="10" size="12"  tabindex="1"  property="hl7Code" readonly="true"/>	         
	         	</logic:equal>	         	
	         </div>
      </td>                                           
  </tr>
  
  	   
     
  				 <tr>
					<td width="30%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b> <bean:message key="expiryDate" /> </b> 
					</font>
					</div>
					</td>
					
					<td width="30%" class="tdfont">
					<div align="left">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						
							<his:date name='expiryDate' dateFormate="%d-%b-%Y" value="<%=expiryDate%>" />
						
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<html:text name="modifyPatientCategoryMasterFB" property="expiryDate" readonly="true" />
					</logic:equal>
					</div>
					</td>
				</tr>
	
	
	
	
  
 
</table>  
--%>


</his:ContentTag>   
<html:hidden name="modifyPatientCategoryMasterFB" property="transactionMode" />
		<html:hidden name="modifyPatientCategoryMasterFB" property="<%=RegistrationConfig.VIEWORMODIFY%>" />
		<html:hidden name="modifyPatientCategoryMasterFB" property="isActive" value="<%=Config.IS_VALID_ACTIVE%>"/>
		<!--<html:hidden name="modifyPatientCategoryMasterFB" property="chk" />-->
		<input type="hidden" name="chk" />
		<html:hidden name="modifyPatientCategoryMasterFB" property="entryDate" value="<%=sysDate%>"/>
		<html:hidden name="modifyPatientCategoryMasterFB" property="patCatCode"/>
		<html:hidden name="modifyPatientCategoryMasterFB" property="patCatSlNo"/>
		<his:ButtonToolBarTag>
			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style='cursor:pointer' tabindex="1" onclick="submitTile('CANCEL')"
					onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
			</logic:equal>
			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>"
				value="modify">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
					style='cursor:pointer' tabindex="1" onclick="validatePatCatMaster()"
					onkeypress="if(event.keyCode==13) validatePatCatMaster()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
					style='cursor:pointer' tabindex="1" onclick="submitTile('CLEAR')"
					onkeypress="if(event.keyCode==13) submitTile('CLEAR');">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style='cursor:pointer' tabindex="1" onclick="submitTile('CANCEL')"
					onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
			</logic:equal>
		</his:ButtonToolBarTag>
		
</his:TransactionContainer>
</html:form>
<his:status/>
</body>
</html>
<%
}catch(Exception e){e.printStackTrace();}
%>