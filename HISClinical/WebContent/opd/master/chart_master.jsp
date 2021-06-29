<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.hisconfig.*" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script>
function enableQuery(obj)
{
	if(obj){
			 if(obj.value==1){
			document.getElementById("divQuery").style.display="block";}
			else { document.getElementById("divQuery").style.display="none"; }
	    }
}
function enableGraph(obj)
	{
	if(obj){
	if(obj.value==2){
	document.getElementById("divGraph").style.display="block";
	}
	else 
	{
		document.getElementById("divGraph").style.display="none"; 
	}
	}
}
window.onload=function()
	{
		if(document.getElementsByName("generationType")[0].value!="-1")
		{
		enableQuery(document.getElementsByName("generationType")[1]);
		enableGraph(document.getElementsByName("generationType")[2]);
		}
	}

function finalSubmit(mode) {
	if(validate())
		{
			document.getElementsByName("hmode")[0].value=mode
			document.forms[0].submit();
		}
		else
		{
			return false;
		}	
}

function submitPage(mode) {
	document.getElementsByName("hmode")[0].value=mode
	document.forms[0].submit();
	
}

function finalSubmitModify(mode){
		if(validate1())
		{
		document.getElementsByName("hmode")[0].value=mode
		document.forms[0].submit();
		}
		else
		{
			return false;
		}	
}

function clearForm(){
	document.getElementsByName("chartName")[0].value="";
	document.getElementsByName("chartCategory")[0].value="-1";
	document.getElementsByName("chartDescription")[0].value="";
	if(document.getElementsByName("hmode")[0].value=="ADD")
	{
	document.getElementsByName("generationType")[0].value = "-1";
	}
	if(document.getElementsByName("generationType")[0].value == "-1")
	{
		enableQuery(document.getElementsByName("generationType")[0]);
		enableGraph(document.getElementsByName("generationType")[0]);
		document.getElementsByName("bodyQuery")[0].value="";
		document.getElementsByName("footerQuery")[0].value="";
		document.getElementsByName("isGraph")[1].checked=true;
	}
	if(document.getElementsByName("generationType")[0].value == "1")
	{
		document.getElementsByName("bodyQuery")[0].value="";
		document.getElementsByName("footerQuery")[0].value="";
	}
	if(document.getElementsByName("generationType")[0].value == "2")
	{
		document.getElementsByName("isGraph")[1].checked=false;
	}
	
}	

function validate()
		{
			if(document.getElementsByName("chartName")[0].value=="")
				{
			 	alert("Enter the Chart Name");
			 	document.forms[0].chartName.focus();
			 	return false;
			 	
				}
			else if(document.getElementsByName("chartCategory")[0].value=="-1")
			{
				alert("Select the Chart Category");
				document.forms[0].chartCategory.focus();
					return false;
			} 
			else if(document.getElementsByName("generationType")[0].value=="-1")
			{
				alert("Select the Generation Type");
				document.forms[0].generationType.focus();
					return false;
			}
			else if(document.getElementsByName("generationType")[0].value=="1")
				{
					if(document.getElementsByName("bodyQuery")[0].value=="")
						{
							alert("Enter the Body Query");
							document.forms[0].bodyQuery.focus();
								return false;
						}
				}
				return true;
		}
		
function validate1()
		{
			if(document.getElementsByName("chartName")[0].value=="")
				{
			 	alert("Enter the Chart Name");
			 	document.forms[0].chartName.focus();
			 	return false;
			 	
				}
			else if(document.getElementsByName("chartCategory")[0].value=="-1")
			{
				alert("Select the Chart Category");
				document.forms[0].chartCategory.focus();
					return false;
			} 
			
			else if(document.getElementsByName("generationTypeLabel")[0].value=="Row Wise")
				{
					if(document.getElementsByName("bodyQuery")[0].value=="")
						{
							alert("Enter the Body Query");
							document.forms[0].bodyQuery.focus();
								return false;
						}
				}
			
				return true;
		}

function validateAlphaNumericWithSpaceOnly(e,param)
{
	var key;
	var keychar;
	var length=param.value.length;
	if(length<=200){
	
	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	// control keys
	if ((key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==95))
		{
	   return true;
		}

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ _/+-").indexOf(keychar) > -1))
	   			return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   			return true;
	   
	else
	   return false;
	   }
	   else
	   { 
	     return false;
	   	alert("Description should be maximum of 200 chanracters");
	   }
}

function validateAlphaNumericWithSpecialCharacterOnly(e)
{
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	//alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./-").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

</script>


<his:TransactionContainer>
<body>
  <html:form action="/master/chartMasterACTION">
   <html:hidden name="chartMasterFB" property="hmode"/>
     <html:hidden name="chartMasterFB" property="chk"/>
      <html:hidden name="chartMasterFB" property="generationTypeLabel"/>
      
     <!-- Add Page -->
   <%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="chartMasterFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
   
    <his:ContentTag>
        <logic:notEqual name="chartMasterFB" property="hmode" value="VIEW">
	   		
  	   <his:TitleTag name="Chart Master>> Add">
			</his:TitleTag>
			
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="chart"/>&nbsp;</b>
					</font>
				 </div>
			  </td>
			  <td width="50%" class="tdfont" >
				 <div align="left">
					&nbsp;<html:text  name="chartMasterFB" property="chartName" maxlength="50" size="25" tabindex="1" onkeypress="return validateAlphaNumericWithSpaceOnly(event,this);"></html:text>	
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
							<b><bean:message key="chartCategory"/>&nbsp;</b>
					</font>
				  </div>
			  </td>
			  <td width="50%" class="tdfont" >
			     <div align="left">
					&nbsp;<html:select  name="chartMasterFB" property="chartCategory" tabindex="1"  style="width:136px;" >
						 <html:option value="-1">Select Value</html:option>
						 <html:option value="<%=OpdConfig.CHART_CATEGORY_OPD%>"><%=OpdConfig.CHART_CATEGORY_ARR[Integer.parseInt(OpdConfig.CHART_CATEGORY_OPD)]%></html:option>
						 <html:option value="<%=OpdConfig.CHART_CATEGORY_IPD%>"><%=OpdConfig.CHART_CATEGORY_ARR[Integer.parseInt(OpdConfig.CHART_CATEGORY_IPD)]%></html:option>
						 <html:option value="<%=OpdConfig.CHART_CATEGORY_OPD_IPD%>"><%=OpdConfig. CHART_CATEGORY_ARR[Integer.parseInt(OpdConfig.CHART_CATEGORY_OPD_IPD)]%></html:option>
						 </html:select>
				 </div>
			  </td>
		   </tr>
		   <tr>
			  <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="chartDesc"/>&nbsp;</b>
					</font>
				  </div>
			  </td>
			  <td width="50%" class="tdfont" colspan="3">
			   	 &nbsp;<html:text  name="chartMasterFB" property="chartDescription" maxlength="200" size="25" tabindex="1" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event);" />	
			  </td>
		</tr>
		<tr>
			  <td width="50%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="generationType"/>&nbsp;</b>
					</font>
				  </div>
			  </td>
			  <td width="50%" class="tdfont" colspan="3">
				 <div align="left">
				<logic:equal name="chartMasterFB" property="hmode" value="ADD">
				    &nbsp;<html:select  name="chartMasterFB" property="generationType" tabindex="1" value="" style="width:136px" onchange = "enableQuery(this); enableGraph(this)">
						  <html:option value="-1">Select Value</html:option>
						 <%-- <html:option value="<%=OpdConfig.CHART_GENERATION_TYPE_ROW_WISE%>" ><%=OpdConfig.CHART_GENERATION_TYPE_ARR[Integer.parseInt(OpdConfig.CHART_GENERATION_TYPE_ROW_WISE)]%></html:option> --%>
						  <html:option value="<%=OpdConfig.CHART_GENERATION_TYPE_COLUMN_WISE%>" ><%=OpdConfig.CHART_GENERATION_TYPE_ARR[Integer.parseInt(OpdConfig.CHART_GENERATION_TYPE_COLUMN_WISE)]%></html:option>
						  </html:select>
				</logic:equal>
				<logic:equal name="chartMasterFB" property="hmode" value="MODIFY">
					<bean:define name="chartMasterFB" property="generationType" id="generationType" type="java.lang.String"/>
						<b>&nbsp;<%=OpdConfig.CHART_GENERATION_TYPE_ARR[Integer.parseInt(generationType)]%></b>
						<html:hidden name="chartMasterFB" property="generationType" />
				</logic:equal>
				 </div>
			  </td>
		   </tr>
		   </table>
		   <logic:equal name="chartMasterFB" property="hmode" value="ADD">
		     <div id="divGraph" style="display: none"  >
		    	 <table width="100%" border="0" cellspacing="1" cellpadding="0" >
		  		 	<tr>
			 			 <td width="50%" class="tdfonthead">
			     			<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="isGraph"/>&nbsp;</b>
							</font>
				 			 </div>
			  			</td>
			 	 		<td width="50%" class="tdfont" colspan="3" tabindex="1">
						&nbsp;<html:radio name="chartMasterFB" property="isGraph" value="<%=OpdConfig.YES%>"  /> Yes	
						&nbsp;<html:radio name="chartMasterFB" property="isGraph" value="<%=OpdConfig.NO %>"  /> No	
			  			</td>
		  	 		</tr>
				</table>
			</div>
			  <div id="divQuery" style="display: none"  >
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		   <tr>
			  <td width="50%" class="tdfonthead">
			     <div align="right">
			       <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="bodyQuery"/>&nbsp;</b>
					</font>
				 </div>
			  </td>
			  <td width="50%" class="tdfont" colspan="3">
			    &nbsp;<html:textarea property="bodyQuery" rows="3" cols="60" tabindex="1" />	
			  </td>
			</tr>
			<tr>
			  <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="footerQuery"/>&nbsp;</b>
					</font>
				  </div>
			  </td>
			  <td width="50%" class="tdfont" colspan="3">
			    &nbsp;<html:textarea property="footerQuery" rows="3" cols="60" tabindex="1" />	
			  </td>
		   </tr>
		</table>
		</div>
	</logic:equal>
			<logic:equal name="chartMasterFB" property="hmode" value="MODIFY">
			<logic:equal name="chartMasterFB" property="generationTypeLabel"  value="<%=OpdConfig.CHART_GENERATION_TYPE_ROW_WISE_LABEL%>" >
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
		  			 <tr>
						  <td width="50%" class="tdfonthead">
			 			    <div align="right">
			     			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
							</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="bodyQuery"/>&nbsp;</b>
							</font>
							 </div>
						  </td>
						  <td width="50%" class="tdfont" colspan="3">
			    			&nbsp;<html:textarea property="bodyQuery" rows="3" cols="60"  tabindex="1"/>	
			 			 </td>
		  			 </tr>
		  			 <tr>
		   				  <td width="50%" class="tdfonthead">
			    			 <div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="footerQuery"/>&nbsp;</b>
							</font>
				 			</div>
						  </td>
			 			 <td width="50%" class="tdfont" colspan="3">
			    			&nbsp;<html:textarea property="footerQuery" rows="3" cols="60" tabindex="1" />	
						  </td>
		   			</tr>
				</table>
			</logic:equal>
			
			<logic:equal name="chartMasterFB" property="generationTypeLabel" value="<%=OpdConfig.CHART_GENERATION_TYPE_COLUMN_WISE_LABEL%>">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="50%" class="tdfonthead">
			   				  <div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="isGraph"/>&nbsp;</b>
								</font>
				 			  </div>
			  				</td>
			  				 <td width="50%" class="tdfont" colspan="3" tabindex="1">
								&nbsp;<html:radio name="chartMasterFB" property="isGraph" value="<%=OpdConfig.YES%>"  /> Yes	
								&nbsp;<html:radio name="chartMasterFB" property="isGraph" value="<%=OpdConfig.NO %>" /> No	
							</td>
							</tr>
						</table>
				</logic:equal>
			</logic:equal>
			
	</logic:notEqual>
	
	
 
 
 
	 	<!--           VIEW PAGE                    -->
	 	<logic:equal name="chartMasterFB" property="hmode" value="VIEW">
			   <his:TitleTag name="Chart Master>>">
				</his:TitleTag>
	 	<table width="100%" border="0" cellspacing="2" cellpadding="0">
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="chart"/>&nbsp;</b>
					</font>
				 </div>
			  </td>
			  <td width="50%" class="tdfont" colspan="3">
			     <div align="left">
			     	&nbsp;<bean:write property="chartName" name="chartMasterFB"  />
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
							<b><bean:message key="chartCategory"/>&nbsp;</b>
					 </font>
				 </div>
			  </td>
			  <td width="50%" class="tdfont" colspan="3">
				 <div align="left">
				 	<bean:define name="chartMasterFB" property="chartCategory" id="category" type="java.lang.String"/>
						<b>&nbsp;<%=OpdConfig.CHART_CATEGORY_ARR[Integer.parseInt(category)]%></b>
						<html:hidden name="chartMasterFB" property="chartCategory" />
				 </div>
		      </td>
		   </tr>
		   <tr>
			  <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="chartDesc"/>&nbsp;</b>
					</font>
				  </div>
			  </td>
			  <td width="50%" class="tdfont" colspan="3">
			  	&nbsp;<bean:write property="chartDescription" name="chartMasterFB"  />
			  </td>
		   </tr>
		   <tr>
			  <td width="50%" class="tdfonthead">
			     <div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="generationType"/>&nbsp;</b>
							</font>
				 </div>
			  </td>
			  <td width="50%" class="tdfont" colspan="3">
			     <div align="left">
			    	  <bean:define name="chartMasterFB" property="generationType" id="generationType" type="java.lang.String"/>
						<b>&nbsp;<%=OpdConfig.CHART_GENERATION_TYPE_ARR[Integer.parseInt(generationType)]%></b>
						<html:hidden name="chartMasterFB" property="generationType" />
			     </div>
			  </td>
		   </tr>
		   </table>
		   <table width="100%" border="0" cellspacing="1" cellpadding="0">
		   <logic:equal name="chartMasterFB" property="generationType"  value="<%=OpdConfig.CHART_GENERATION_TYPE_COLUMN_WISE%>" >
				<tr>
			  <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="isGraph"/>&nbsp;</b>
					</font>
				  </div>
			  </td>
			  <td width="50%" class="tdfont" colspan="3">
						&nbsp;<html:radio name="chartMasterFB" property="isGraph" value="<%=OpdConfig.YES%>" disabled= "<%=this.readOnly %>" /> Yes	
						&nbsp;<html:radio name="chartMasterFB" property="isGraph" value="<%=OpdConfig.NO %>" disabled= "<%=this.readOnly %>" /> No	
			  </td>
		    </tr>
		    </logic:equal>
		</table>
		<logic:equal name="chartMasterFB" property="generationType"  value="<%=OpdConfig.CHART_GENERATION_TYPE_ROW_WISE%>" >
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		   <tr>
			  <td width="50%" class="tdfonthead">
			     <div align="right">
			     		<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="bodyQuery"/>&nbsp;</b>
						</font>
				 </div>
			  </td>
			  <td width="50%" class="tdfont" colspan="3">
			 	&nbsp;<bean:write property="bodyQuery" name="chartMasterFB"  />
			  </td>
		   </tr>
		   <tr>
		   <td width="50%" class="tdfonthead">
			     <div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="footerQuery"/>&nbsp;</b>
						</font>
				 </div>
			  </td>
			  <td width="50%" class="tdfont" colspan="3">
			  	&nbsp;<bean:write property="footerQuery" name="chartMasterFB"  />
			  </td>
		   </tr>
			</table>
		</logic:equal>
 </logic:equal>
	  	<logic:notEqual name="chartMasterFB" property="hmode" value="ADD">
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
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
			     <logic:equal value="MODIFY" name="chartMasterFB" property="hmode">
				    &nbsp;<html:select name="chartMasterFB" property="isValid" disabled="<%=this.readOnly %>" style="width:136px;">
						   <html:option value="<%=Config.IS_VALID_ACTIVE%>">Active</html:option>
						   <html:option value="<%=Config.IS_VALID_INACTIVE%>">InActive</html:option>
						   </html:select>
				</logic:equal>
				<logic:equal value="VIEW" name="chartMasterFB" property="hmode">
					<bean:define name="chartMasterFB" property="isValid" id="valid" type="java.lang.String"/>
								<b>&nbsp;<%=Config.IS_VALID_ARR[Integer.parseInt(valid)]%></b>
								<html:hidden name="chartMasterFB" property="isValid" />
				</logic:equal>
				 </div>
			  </td>  
		   </tr>
		</table>
	 	
	 	
	 	</logic:notEqual>
	</his:ContentTag>
			<his:ButtonToolBarTag>
			    <logic:equal name="chartMasterFB" property="hmode" value="ADD">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick=" finalSubmit('SAVE')">
				</logic:equal>
				
				<logic:notEqual name="chartMasterFB" property="hmode" value="VIEW">
				<logic:notEqual name="chartMasterFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmitModify('MODIFYSAVE')" onclick="finalSubmitModify('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
				
				<logic:notEqual name="chartMasterFB" property="hmode" value="VIEW">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual> 
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
		</his:ButtonToolBarTag>
  
  
 <center><b><his:status/></b></center>
  </html:form>
  <html:hidden name="chartMasterFB" property="hmode"/>
 
  
  </body>
 
  
  </his:TransactionContainer>
</html>