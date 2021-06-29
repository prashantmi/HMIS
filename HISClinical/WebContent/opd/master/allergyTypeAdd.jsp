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
<%@ page import ="opd.*" %>	
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<bean:define id="srcflag" name="AddAllergyTypeFB" property="sourceFlag" type="java.lang.String"></bean:define>

<script>
	
function validateMaster()
{
		var valid=true;
	//alert("inside validate master");		 
	if(document.getElementsByName("allergiesType")[0].value=="")
		{
		alert("Please Enter Allergy type")
		document.forms[0].allergiesType.focus();
		valid=false;
		}
	else if(document.getElementsByName("allergiesDesc")[0].value=="")
	{
		alert("Please Enter Allergy description")
		document.forms[0].allergiesDesc.focus();
		valid=false;
	}
	else if(document.getElementsByName('sourceFlag')[1].checked)
	{
		//alert("dynamic>>>>"+validateDynamicMode());
		valid=validateDynamicMode();
	}
	//alert("valid>>>>>>"+valid);
		if(valid)
	    submitTile('SAVE');		
}
		
		
		
function validateDynamicMode()                                             
    {
    var valid=true;
     if(document.getElementsByName('sourceFlag')[1].checked)
       {
       
 	  if(document.getElementsByName("tableId")[0].value=="-1")
		{
			alert("Please Select the Table Name");
			document.getElementsByName("tableId")[0].focus();
			valid=false;
		}	
		
	       
	    else if(document.getElementsByName("columnId")[0].value=="-1")
		{
			alert("Please Select the Label Field");
			document.getElementsByName("columnId")[0].focus();
			valid=false;
		}	
	       
	       
	    else if(document.getElementsByName("valueId")[0].value=="-1")
		{
			alert("Please Select the Value Field");
			document.getElementsByName("valueId")[0].focus();
			valid=false;
		}	
		
	       
	    else if(document.getElementsByName("validationCondition")[0].value=="")
		{
		alert("Please Enter Validation Condition")
		document.forms[0].validationCondition.focus();
		valid=false;
		}
	       
	       
	    else if(document.getElementsByName("hospitalCodeId")[0].value=="-1")
		{
			alert("Please Select the Hospital Code Column");
			document.getElementsByName("hospitalCodeId")[0].focus();
			valid=false;
		}	
		
 	 }
 	 return valid;
}
			
			
			
function submitTile(mode)
{
   			//alert(mode);
   			document.getElementsByName("transactionMode")[0].value=mode;  
   			document.forms[0].submit();
}
		
		
		
function showTable()
{
			document.getElementById("staticDynamic").style.display="";
			document.getElementById("divTableName").style.display="";
			document.getElementById("divTableValue").style.display="";
			document.getElementById("divLabelName").style.display="";
			document.getElementById("divLabelValue").style.display="";
			document.getElementById("divValueName").style.display="";
			document.getElementById("divValue").style.display="";
			document.getElementById("divValidationCondition").style.display="";
			document.getElementById("divValidationConditionValue").style.display="";
			document.getElementById("hospitalCodeFieldName").style.display="";
			document.getElementById("hospitalCodeFieldValue").style.display="";
			document.getElementById("showActualQuery").style.display="";
			/*if(document.getElementById("tableId") !=null && document.getElementById("tableId").value=="-1"){
				document.getElementById('showActualQuery').innerHTML="";				
			}*/
			
			
}


function doNotShow()
{
			document.getElementById("staticDynamic").style.display="none";
			document.getElementById("divTableName").style.display="none";
			document.getElementById("divTableValue").style.display="none";
			document.getElementById("divLabelName").style.display="none";
			document.getElementById("divLabelValue").style.display="none";
			document.getElementById("divValueName").style.display="none";
			document.getElementById("divValue").style.display="none";
			document.getElementById("divValidationCondition").style.display="none";
			document.getElementById("divValidationConditionValue").style.display="none";
			document.getElementById("hospitalCodeFieldName").style.display="none";
			document.getElementById("hospitalCodeFieldValue").style.display="none";
			document.getElementById("showActualQuery").style.display="none";
			document.getElementsByName("tableId")[0].value="";
			document.getElementsByName("columnId")[0].value="";
			document.getElementsByName("valueId")[0].value="";
			document.getElementsByName("validationCondition")[0].value="";
			document.getElementsByName("hospitalCodeId")[0].value="";
			document.getElementsByName("longQuery")[0].value="";
			
			
}
/*function submitPage(mode)
{
	alert("mode===="+mode);
	document.forms[0].hmode.value=mode;
	alert(document.getElementsByName("hmode")[0].value)
	document.forms[0].submit();
	alert("after submit");
}*/
		
function showData()
{
	//alert("inside show data");
	if(document.getElementsByName("tableId")[0].value=="-1")
	{
		alert("Please Select The Table Name")
	}
	else
	{
		//alert("inside show data else condition");
		submitTile('GETCOLUMNS');
	}
}



function query()
{
   //alert("inside query function");
    var select="<font color='red'>SELECT</font>";
    var from="<font color='red'>FROM</font>";
    var where="<font color='red'>WHERE</font>";
    var query="";
    var selQuery="";
    var table=document.getElementsByName('tableId')[0].value;
    var labelField=document.getElementsByName('columnId')[0].value;
    var valueField=document.getElementsByName('valueId')[0].value;
    var whereClauseValid=document.getElementsByName('validationCondition')[0].value;
    var whereClauseHospital=document.getElementsByName('hospitalCodeId')[0].value;
    
   	if(valueField == "-1")
   	{
   	valueField="";
   	}
   	
   	
   	if(whereClauseHospital == "-1")
   	{
   	whereClauseHospital="";
   	}
   
   	if (document.getElementsByName('localOrGlobalFlag')[0].checked == true) {
 	   query=select+" "+valueField+" "+","+" "+labelField+" "+from+" "+table+" "+where+" "+whereClauseValid+" "+"and"+" "+whereClauseHospital+" "+"=";
 	    selQuery="SELECT"+" "+valueField+","+""+labelField+" " +"FROM"+" "+table+" "+"WHERE"+" "+whereClauseValid+" "+ "AND"+" "+whereClauseHospital+" "+" = ?"
    }else{
 	   query=select+" "+valueField+" "+","+" "+labelField+" "+from+" "+table+" "+where+" "+whereClauseValid+" "+"and"+" "+whereClauseHospital+" "+"=100";
 	    selQuery="SELECT"+" "+valueField+","+""+labelField+" " +"FROM"+" "+table+" "+"WHERE"+" "+whereClauseValid+" "+ "AND"+" "+whereClauseHospital+" "+" =100 and 1<> ?"
    }
    
  // alert("query" +selQuery);
   
  document.getElementsByName("longQuery")[0].value= selQuery;
  document.getElementById('showActualQuery').innerHTML="<b>"+query +"</b>";
 
  // alert("query" +document.getElementsByName('query')[0].value);
}



window.onload = function() 
{
 
 
 	//alert("<%=srcflag%>");
 	//if(document.getElementsByName("sourceFlag")[0].value=="1")
 	if("<%=srcflag%>"=="1")
 	{
 		doNotShow();
 	}
 	else
 	{
 		showTable();
 	}   
}
	
	
</script>

	<body>
		<html:form action="/master/allergyTypeAdd.cnt">
			<html:hidden name="AddAllergyTypeFB" property="hmode" value="LIST"/>
			<html:hidden name="AddAllergyTypeFB" property="longQuery" />
			<his:TransactionContainer>

				<his:TitleTag name=" Allergy Type Master >> Add">
				</his:TitleTag>
				
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">  
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="allergyType"/>
	  									</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									&nbsp;<html:text name="AddAllergyTypeFB" property="allergiesType" maxlength="50" size="35" onkeypress="return validateAlphabetsOnly(event,this)">
									</html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="allergyDesc"/>
	  									</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									&nbsp;<html:text name="AddAllergyTypeFB" property="allergiesDesc" maxlength="500" size="35" onkeypress="return validateAlphaNumericOnly(event)">
										</html:text>
								</div>
							</td>
						</tr>
						<tr>
			      <td width="50%" class="tdfonthead">
			          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 <b><bean:message key="sensitivity"/></b> 
			 		  </font>
		     	 
			     </td>
			     <td width="50%" class="tdfont">
			         &nbsp;<html:select name="AddAllergyTypeFB" property="allergySensitivity" >
						  <html:option value="-1">Select Value</html:option>
						  <logic:present name="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" >
						  <html:options collection="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" property="value" labelProperty="label" />
						  </logic:present>
					   </html:select>
				 
				 </td>
			  </tr>
						
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="staticOrDynamic"/>
	  									</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								
								<div align="left">
									&nbsp;
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      							<%=OpdConfig.ALLERY_TYPE_SOURCE_FLAG_ARR[Integer.parseInt(OpdConfig.ALLERY_TYPE_SOURCE_FLAG_STATIC)] %>
		      						</font>
		      						<html:radio name="AddAllergyTypeFB" property="sourceFlag" tabindex="1" value="<%=OpdConfig.ALLERY_TYPE_SOURCE_FLAG_STATIC%>" onclick="doNotShow()"/>
		       						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       							<%=OpdConfig.ALLERY_TYPE_SOURCE_FLAG_ARR[Integer.parseInt(OpdConfig.ALLERY_TYPE_SOURCE_FLAG_DYNAMIC )] %>
		       						</font> 
		        					<html:radio name="AddAllergyTypeFB" property="sourceFlag" tabindex="1" value="<%=OpdConfig.ALLERY_TYPE_SOURCE_FLAG_DYNAMIC%>" onclick="showTable()"/>

								</div>
							</td>
						</tr>
			</table>	
			
			<div id="staticDynamic" style="display: none;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1" >  	
				<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
  									<font color="#FF0000">*
  									</font>
  									<b>
  										<bean:message key="localOrGlobal"/>
  									</b>
  								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							
							<div align="left">
								&nbsp;
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Local Table</font>
								<html:radio name="AddAllergyTypeFB" property="localOrGlobalFlag" tabindex="1"  value="0" onclick="query()" />
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Global Table</font>
								<html:radio name="AddAllergyTypeFB" property="localOrGlobalFlag" tabindex="1" value="1" onclick="query()" />
							</div>
						</td>
				 </tr>
				 <tr>
			      <td width="50%" class="tdfonthead">
			        <div align="right" id='divTableName' style='display: none;'>
				      <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
					  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 <b><bean:message key="table"/></b> 
			 		  </font>
		     	   </div>
			     </td>
			     <td width="50%" class="tdfont">
			       <div align="left" id='divTableValue' style='display: none;'>
			         &nbsp;<html:select name="AddAllergyTypeFB" property="tableId" onchange="showData();query()">
						  <html:option value="-1">Select Value</html:option>
						  <logic:present name="<%=OpdConfig.ALLERGYTYPE_ALL_TABLE_NAME%>" >
						  <html:options collection="<%=OpdConfig.ALLERGYTYPE_ALL_TABLE_NAME%>" property="value" labelProperty="label" />
						  </logic:present>
					   </html:select>
				   </div>
				 </td>
			  </tr>
			  <tr>
			  
			  <tr>
			      <td width="50%" class="tdfonthead">
			        <div align="right" id='divLabelName' style='display: none;'>
				      <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
					  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="labelField" /></b> 
			 		  </font>
		     	   </div>
			     </td>
			     <td width="50%" class="tdfont">
			       <div align="left" id='divLabelValue' style='display: none;'>
			         &nbsp;<html:select name="AddAllergyTypeFB" property="columnId" onchange="query()">
						  <html:option value="-1">Select Value</html:option>
						  <logic:present name="<%=OpdConfig.ALLERGYTYPE_ALL_COLUMN_NAME%>" >
 						  <html:options collection="<%=OpdConfig.ALLERGYTYPE_ALL_COLUMN_NAME%>" property="value" labelProperty="label" />
 						  </logic:present>
					   </html:select>
				   </div>
				 </td>
			  </tr>
			  <tr>
			    
			     <tr>
			      <td width="50%" class="tdfonthead">
			        <div align="right" id='divValueName' style='display: none;'>
				      <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
					  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="valueField" /></b> 
			 		  </font>
		     	   </div>
			     </td>
			     <td width="50%" class="tdfont">
			       <div align="left" id='divValue' style='display: none;'>
			         &nbsp;<html:select name="AddAllergyTypeFB" property="valueId" onchange="query()">
						  <html:option value="-1">Select Value</html:option>
						  	<logic:present name="<%=OpdConfig.ALLERGYTYPE_PRIMARY_KEY_LIST%>" >
						    <html:options collection="<%=OpdConfig.ALLERGYTYPE_PRIMARY_KEY_LIST%>" property="value" labelProperty="label" />
						    </logic:present>
					   </html:select>
				   </div>
				 </td>
			  </tr>
			  <tr>
			  <tr>
				<td width="50%" class="tdfonthead">
					<div align="right" id='divValidationCondition' style='display: none;'>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  						<font color="#FF0000">*</font>
	  							<b><bean:message key="validationCondition"/></b>
	  						</font>	
					</div>
				  </td>
				  <td width="50%" class="tdfont">
					 <div align="left" style='display: none;' id='divValidationConditionValue'>
						&nbsp;<html:textarea name="AddAllergyTypeFB" property="validationCondition" cols="32" rows="1" onchange="query()" > <!--  onkeypress="return validateAlphaNumericOnly(event)" -->
							</html:textarea>
					  </div>
				  </td>
		     </tr>
			 <tr>
			      <td width="50%" class="tdfonthead">
			        <div align="right" style='display: none;' id='hospitalCodeFieldName'>
				      <font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
					  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="hospitalCodeField" /></b> 
			 		  </font>
		     	   </div>
			     </td>
			     <td width="50%" class="tdfont">
			       <div align="left" style='display: none;' id='hospitalCodeFieldValue' >
			         &nbsp;<html:select name="AddAllergyTypeFB" property="hospitalCodeId" onchange="query()">
						  <html:option value="-1">Select Value</html:option>
						  <logic:present name="<%=OpdConfig.ALLERGYTYPE_ALL_COLUMN_NAME%>" >
						  <html:options collection="<%=OpdConfig.ALLERGYTYPE_ALL_COLUMN_NAME%>" property="value" labelProperty="label" />
						  </logic:present>
					   </html:select>
				   </div>
				 </td>
			  </tr>
			  
			   <tr>
			     <td width="50%" class="tdfonthead">
				       <div align="right">
				          <font color="RED" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							     	*
						  </font> 
						  <font color="#000000" size="2" face="Verdana,Arial,Helvetica,sans-serif"> 
							   <b> 	<bean:message key="query"/>&nbsp;</b>
						  </font>
				       </div>
				     </td>
			     	<td width="50%" class="tdfont" >
			         <div  align="center" id="showActualQuery">
			           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			          </font>
				     </div>
				     
				   </td>
				 </tr> 
			</table>
		  </div>
		</his:ContentTag>
		<html:hidden name="AddAllergyTypeFB" property="transactionMode" />
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer  onclick ="validateMaster()" onkeypress="if(event.keyCode==13) validateMaster()">
    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer  onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">          	
		</his:ButtonToolBarTag>
		<center><b><div id="statusId" ><his:status/></div></b></center>
		
	  </his:TransactionContainer>
   </html:form>
  </body>
</html> 