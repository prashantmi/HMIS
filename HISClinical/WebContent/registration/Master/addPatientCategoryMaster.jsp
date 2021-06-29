<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

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

window.onload=function(){ 
document.getElementsByName("patCatType")[0].focus();
}	
	
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
		function callThisOnload()
		{
				document.getElementById("monthRow").style.display="none";
				document.getElementById("dayRow").style.display="none";
		}
		
		function showRow()
		{
			var isExpiry=document.getElementsByName("isExpiry")[0].value;
		//	alert(">>>>>"+isExpiry);
			if(isExpiry==0)
			{
				document.getElementById("monthRow").style.display="none";
				document.getElementById("dayRow").style.display="none";
				document.getElementsByName("expiryMonth")[0].value="";
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
		//		alert(">>2>>"+document.getElementsByName("expiryMonth")[0].value);
			}
		}
		
		function submitTile(mode)
		{
		   document.getElementsByName("transactionMode")[0].value=mode;  
		//   alert(document.getElementsByName('transactionMode')[0].value);
		   document.forms[0].submit();
		}
		
		function validatePatCatMaster()
		{
			var valid=true;
	       
	       entryDate = document.getElementsByName("entryDate")[0];
	       
	   		
		if(
			isEmpty(document.forms[0].patCatType,"Patient Category Type")&&
			isEmpty(document.forms[0].patCatName,"Patient Category Name")&&
	   		isEmpty(document.forms[0].patCatShortName,"Patient Category Short Name")&&
		   	isSelected(document.forms[0].priority,"Is Priority") && 
		   	isSelected(document.forms[0].isExpiry,"Is Expiry") &&
		   	validateIsExpiry() 
		   
		  )
		  	{
		  		valid=true;
		  		//alert("submit");
	  	 		submitTile('SAVE');
	  	  	}
	  	  	else
	  	  	{
	  	  	//	alert("valid=false");
				valid=false;
	  	  	}
		}
		function validateDate()
		{
		//	alert("validateDate")
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
	</script>
	
	<body>
		<html:form action="/master/addPatientCategoryMaster">
			<his:TransactionContainer>
				<%@ page import="java.util.*,registration.*,hisglobal.presentation.*"%>
				<%  
				String sysDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(RegistrationConfig.SYSADATEOBJECT),"dd-MMM-yyyy");%>
				
		
				<his:TitleTag name="Add Category">
				</his:TitleTag>
				
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
					
					<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<font color="#FF0000">*
											</font>
											 <bean:message key="patCatType" />
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="patCatType"  >
								 		<html:option value="-1">Select Value</html:option>
					  			 		<html:option value="<%=RegistrationConfig.PATIENT_CATEGORY_TYPE_PATIENT_CATEGORY %>">Patient Category</html:option>
					  			 		<html:option value="<%=RegistrationConfig.PATIENT_CATEGORY_TYPE_TREATMENT_CATEGORY %>">Treatment Category</html:option>
					  			 		<html:option value="<%=RegistrationConfig.PATIENT_CATEGORY_TYPE_OTHER %>">Other</html:option>
						   	 		</html:select>
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
											 <bean:message key="patCatName" />
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:text name="modifyPatientCategoryMasterFB" maxlength="30" tabindex="1" property="patCatName" onkeypress="return validateAlphabetsOnly(event,this)" />
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
											 <bean:message key="patCatShortName" />
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:text name="modifyPatientCategoryMasterFB" tabindex="1" maxlength="3" size="5" property="patCatShortName" onkeypress="return validateAlphabetsOnly(event,this)" />
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
									<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="priority" >
								 		<html:option value="-1">Select Value</html:option>
						  		 		<html:option value="1">Yes</html:option>
						  		 		<html:option value="0">No</html:option>
						  		 	</html:select>
								</div>	
							</td>
						</tr>
						
						
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											 <bean:message key="idRequired" />
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
										<bean:message key="yes" /> 
									</font> 
									<html:radio name="modifyPatientCategoryMasterFB" property="idRequired" tabindex="1"	value="<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
										<bean:message key="no" /> 
									</font> 
									<html:radio name="modifyPatientCategoryMasterFB" property="idRequired" tabindex="1"	value="<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_NO%>" />
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
											 <bean:message key="isExpiry" />
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="isExpiry" onchange="showRow()" >
								 		<html:option value="-1">Select Value</html:option>
					  			 		<html:option value="0">Not Required</html:option>
					  			 		<%--<html:option value="1">Month Based</html:option>--%>
					  			 		<html:option value="2">Day Based</html:option>
						   		 	</html:select>
								</div>	
							</td>
						</tr>
					</table>
					
					<div id="monthRow" style="display: none;">
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
						  			<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="expiryMonth"  >
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
						  		</div>
  						  	</td>
						</tr></table>
					</div>	
  				
   					<div id="dayRow" style="display: none;">	
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
	  								<html:text name="modifyPatientCategoryMasterFB" maxlength="3" size="5" property="expiryDay" onkeypress="return validateNumeric(event)"/>
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
						<div align="left">
						<html:textarea name="modifyPatientCategoryMasterFB" tabindex="1" property="criteriaRemarks" onkeypress="return CheckMaxLength(event,this,100,1)"/>
						</div>
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
						<div align="left">
						<html:textarea name="modifyPatientCategoryMasterFB" tabindex="1" property="categoryBenefits" onkeypress="return CheckMaxLength(event,this,200,1)"/>
						</div>
					</td>
					
				</tr>
				
				
						
						</table>
</his:ContentTag>						
						
<%-- 				
						<table width="100%" border="0" cellspacing="1" cellpadding="0">	
						
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<font color="#FF0000">*
											</font>
											 <bean:message key="isPaid" />
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:select name="modifyPatientCategoryMasterFB" tabindex="1" property="isPaid"  >
								 		<html:option value="-1">Select Value</html:option>
					  			 		<html:option value="0">Yes </html:option>
					  			 		<html:option value="1">No</html:option>
						   		 	</html:select>
								</div>
							</td>				
						</tr>
								
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											 <bean:message key="hl7Code" />
										</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:text name="modifyPatientCategoryMasterFB" tabindex="1" maxlength="10" size="12" property="hl7Code" onkeypress="return validateNumeric(event)"/>
								</div>	
							</td>
						</tr>			
		
					</table>
--%>					
					
					
				
				<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validatePatCatMaster()"	onkeypress="if(event.keyCode==13) validatePatCatMaster()">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
				</his:ButtonToolBarTag>
				
				<html:hidden name="modifyPatientCategoryMasterFB" property="transactionMode" />
				<html:hidden name="modifyPatientCategoryMasterFB" property="entryDate" value="<%=sysDate%>"/>		
				<his:status/>
			</his:TransactionContainer>
		</html:form>
	</body>
</html>