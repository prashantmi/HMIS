<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import ="opd.*" %>
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


<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="hisglobal.hisconfig.*" %>

<script type="text/javascript">

function validateAlphabetsWithNumberOnly(e)
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
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890./").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
}

function validatePositiveNumberOnly(e)
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
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space
	else if ((("1234567890.").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
}

function submitForm21(mode)
{	
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}

function AddRowToTable(mode)
{
document.getElementsByName('hmode')[0].value=mode
document.forms[0].submit();

}

function DelRowFromTable(mode,obj)
{
//alert(obj);
document.getElementsByName('hmode')[0].value=mode
document.getElementsByName('index')[0].value=obj
document.forms[0].submit();

}



function addNewRecord(mode)
{
   
	if(document.forms[0].doseName.value=="")
	 {
	 alert("Please Enter Dose Name");
	 document.forms[0].doseName.focus();
	 return false;                          
	 }
	 
	 else if(document.forms[0].doseInstruction.value=="")
	 {
	 alert("Please Enter Instruction");
	 document.forms[0].doseInstruction.focus();
	 return false;                          
	 }
	 
	
	 
	else
	{
	AddRowToTable(mode);
	} 


}

function validateForm(mode)
{
		if(document.getElementsByName('doseName')[0].value=="")
		{
			document.getElementsByName('doseName')[0].focus();
			alert("Please Enter Dose");
			return false;
		}
		
		if(document.getElementsByName("isFrequencyBound")[0].checked)
		{
			if(document.forms[0].doseQty.value=="" )
			{	
				alert("Please Enter Dose Quantity");
				document.forms[0].doseQty.focus();
				return false;                          
			}
		}
		
		
		
		
	
	submitForm21(mode);
}
function clearForm()
{
	document.getElementsByName('modDoseName')[0].value="";
	document.getElementsByName('modDoseInstruction')[0].value="";
	document.forms[0].doseQty.value="";
}

function showDoseQty(obj)
{
	//alert(obj);
	//alert(obj.value);
	if(obj.value=="0")
	{
		document.getElementById("doseQtyRowId").style.display="none";
	}
	else
	{
		document.getElementById("doseQtyRowId").style.display="";
	}
	
}

window.onload = function(){

	if(document.getElementsByName("isFrequencyBound")[0].checked)
	{
		document.getElementById("doseQtyRowId").style.display="";
	}
	else
	{
		document.getElementById("doseQtyRowId").style.display="none";
	}
}

</script>

<body >
		<html:form action="/master/DrugDoseMaster">
		<html:hidden property="hmode" name="drugDoseMasterFB"/>
		<html:hidden property="index" name="drugDoseMasterFB"/>
		<html:hidden property="itemTypeId" name="drugDoseMasterFB"/>
		<html:hidden property="doseId" name="drugDoseMasterFB" />
		<html:hidden property="sereialNo" name="drugDoseMasterFB"/>
		<his:TransactionContainer>	
		
		<%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="drugDoseMasterFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
		<his:ContentTag>
		
		
		<his:TitleTag name="Drug Dose Master>>Modify">
		</his:TitleTag>
		
		<logic:notEqual value="VIEW" name="drugDoseMasterFB" property="hmode">
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		<td width="50%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<font color="#FF0000">*</font>
				<b><bean:message key="itemType"/></b>
				<html:hidden name="drugDoseMasterFB" property="itemType"/>
	  			</font>
				</div>
	  		</td>
	  		
	  		
	  	<td width="50%" class="tdfont">
			      <div align="left">
			       		<html:hidden name="drugDoseMasterFB" property="itemTypeName" />
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:write name="drugDoseMasterFB" property="itemTypeName"/></b> 
						</font>
				   			   
			      </div>
			    </td>  
	    	<td width="50%" class="tdfont" >
	    	</td>
		</tr>	
		<tr>
		<td width="33%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<font color="#FF0000">*</font>
				<b><bean:message key="doseName"/></b>
	  			</font>
				</div>
	  		</td>
		<td width="33%" class="tdfont">
				<div align="left">
				<html:text name="drugDoseMasterFB" property="doseName" onkeypress="return validateAlphabetsWithNumberOnly(event)" tabindex="1" maxlength="50"  />
				</div>
			</td>
	
	  	</tr>
	  	
		
		<tr>	
	  	<td width="33%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<font color="#FF0000">*</font>
				<b><bean:message key="isFrequencyBound"/></b>
	  			</font>
				</div>
	  		</td>
	  		<td width="33%" class="tdfont">
				<div align="left">
				Yes<html:radio property="isFrequencyBound" value="<%=OpdConfig.IS_FREQUENCY_BOUND %>" name="drugDoseMasterFB" onclick="showDoseQty(this)"></html:radio>
				No<html:radio property="isFrequencyBound" value="<%=OpdConfig.IS_NOT_FREQUENCY_BOUND%>" name="drugDoseMasterFB" onclick="showDoseQty(this)"></html:radio>
				</div>
			</td>	
		</tr>
		<tr id="doseQtyRowId">	
	  
	  		<td width="33%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<font color="#FF0000">*</font>
				<b><bean:message key="doseQty"/></b>
	  			</font>
				</div>
	  		</td>
	  
	  		<td width="33%" class="tdfont">
				<div align="left">
				<html:text name="drugDoseMasterFB" property="doseQty" onkeypress="return validatePositiveNumberOnly(event)" tabindex="1" maxlength="2" size="3" />
				</div>
			</td>	
	  
	  		
		</tr>
		<tr>	
	  		<td width="33%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="instructionType"/></b>
	  			</font>
				</div>
	  		</td>
	  		<td width="33%" class="tdfont">
				<div align="left">
				<html:text name="drugDoseMasterFB" property="doseInstruction" onkeypress="return validateAlphabetsWithNumberOnly(event)" tabindex="1" maxlength="50"  />
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
							<b>	<bean:message key="isActive"/></b>
					  		 </font>
				    	 </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     <html:select name="drugDoseMasterFB" property="isActive"  styleClass="regcbo">
							<html:option value="<%=Config.IS_VALID_ACTIVE%>">Active</html:option>
							<html:option value="<%=Config.IS_VALID_INACTIVE%>">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
	  </table>
		</logic:notEqual>
		<logic:equal value="VIEW" name="drugDoseMasterFB" property="hmode">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		<td width="50%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<html:hidden name="drugDoseMasterFB" property="itemType"/>
				<b><bean:message key="itemType"/></b>
	  			</font>
				</div>
	  		</td>
	  		
	  	<td width="50%" class="tdfont" >
	  	 	  	<div align="left" >	  
	  	 	  	<html:hidden name="drugDoseMasterFB" property="itemType"/>
	  	    	<bean:write name="drugDoseMasterFB" property="itemType"/>
	        	</div>
	    </td>
	    	<td width="50%" class="tdfont" >
	    	</td>
		</tr>	
		<tr>
		<td width="33%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="doseName"/></b>
	  			</font>
				</div>
	  		</td>
			
			<td width="33%" class="tdfont">
				<div align="left">
				
				<bean:write name="drugDoseMasterFB" property="doseName"/>
				</div>
			</td>
	  	</tr>
		<tr>	
	  	<td width="33%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="isFrequencyBound"/></b>
	  			</font>
				</div>
	  		</td>
	  		<td width="33%" class="tdfont">
				<div align="left">
				Yes<html:radio property="isFrequencyBound" value="<%=OpdConfig.IS_FREQUENCY_BOUND %>" name="drugDoseMasterFB" disabled="true"></html:radio>
				No<html:radio property="isFrequencyBound" value="<%=OpdConfig.IS_NOT_FREQUENCY_BOUND%>" name="drugDoseMasterFB" disabled="true"></html:radio>
				</div>
			</td>	
		</tr>
	  	<tr id="doseQtyRowId">	
	  	<td width="33%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="doseQty"/></b>
	  			</font>
				</div>
	  		</td>
	  		<td width="33%" class="tdfont">
				<div align="left">
				<bean:write name="drugDoseMasterFB" property="doseQty"/>
				</div>
			</td>	
		</tr>
	  	<tr>
	  		
	  	<td width="33%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="instructionType"/></b>
	  			</font>
				</div>
	  		</td>
	  		<td width="33%" class="tdfont">
				<div align="left">
				<bean:write name="drugDoseMasterFB" property="doseInstruction"/>
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
							<b>	<bean:message key="isActive"/></b>
					  		 </font>
				    	 </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     <html:select name="drugDoseMasterFB" property="isActive" disabled="<%=this.readOnly %>" styleClass="regcbo">
							<html:option value="<%=Config.IS_VALID_ACTIVE%>">Active</html:option>
							<html:option value="<%=Config.IS_VALID_INACTIVE%>">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
		
	  </table>
		
	  	</logic:equal>
		
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
					
					<logic:notEqual value="VIEW" property="hmode" name="drugDoseMasterFB">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick =" validateForm('MODIFYSAVE')" onkeypress="if(event.keyCode==13)  validateForm('MODIFYSAVE')">
					</logic:notEqual>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
    				<logic:notEqual value="VIEW" property="hmode" name="drugDoseMasterFB">
    				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
    				</logic:notEqual>          	
		</his:ButtonToolBarTag>
		<center><b><his:status/></b></center>
		</his:TransactionContainer>
		
		
		
		</html:form>
		
		</body>
		</html>
		