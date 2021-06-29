
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>

<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script type="text/javascript">

function validatemodifyBelongingDetail()
{
	var valid=false
	var itemName=document.getElementsByName("belongingItemCode")[0];
	var itemDescription=document.getElementsByName("quantity")[0];
	var remarks=document.getElementsByName("remarks")[0];
	
	if(comboValidation(itemName,"Item Name") &&
		isEmpty(itemDescription,"Item Description") &&
		isEmpty(remarks,"Remarks"))
	{
		valid=true
	}
		return valid
}

function modifyDetails()
{
	var counter=0
	var valid=true
	var checkBoxObj=document.getElementsByName("chk")
	
	var chechBoxLength=checkBoxObj.length
	for(j=0;j<chechBoxLength;j++)
	{
		if(checkBoxObj[j].checked==true)
		{
			counter=counter+1
		}
	}
	if(counter==0)
	{
		alert("Please Select one check Box")
		valid= false
	}
	if(counter>1)
	{
		alert("Please Select only one check Box")
		valid= false
	}
	return valid
}

function showNameTextBox(obj)
{
	var objValue=obj.value
	
	if(objValue==0)
	{
		document.forms[0].handOverTo.disabled=true
	}
	else
	{
		document.forms[0].handOverTo.disabled=false
	}
}


function saveHandOverDetails()
{
	var checkBoxObj=document.getElementsByName("chk")
	
	var chechBoxLength=checkBoxObj.length
	var counter=0
	var relationCombo=document.forms[0].relation
	var relationValue=relationCombo.value
	var valid=false
	for(i=0;i<chechBoxLength;i++)
	{
		if(checkBoxObj[i].checked==true)	
		{
		counter=1
		}
		
	}
	if(counter==0)
	{
		alert("Please Select Atleast One Checkbox")
		return valid
	}
	else
	{
		if(comboValidation(document.forms[0].relation,'Combo'))
		{
			if(relationValue!=0 )
			{
				valid=isEmpty(document.forms[0].handOverTo,"Hand Over Name")
			}
			else
			{
				valid=true
			}
		}
		return valid
	}
}

function comboValidation(obj, str)
	{	var valid= true
		if(obj.value==-1)
		{
		alert("Please Select : "+str)
		valid=false
		}
	return valid
	}
	
</script>

<%@ page import ="opd.*" %>

<html:form action="/opd/opdPatientBelonging">
<his:TransactionContainer>


<logic:equal name="patientBelongingFB" property="selectedMode" value="MODIFY">
		<his:TitleTag name="Modify Patient Belongings">
		</his:TitleTag>
		<his:statusNew>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
					<td width="4%"  class="tdfonthead">
						
			  		</td>
				
					<td width="24%"  class="tdfonthead">
						<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="itemName"/></b>
						</font>
						</div>
			  		</td>
			  		<td width="24%"  class="tdfonthead">
						<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="description"/></b>
						</font>
						</div>
			  		</td>
			  		<td width="24%"  class="tdfonthead">
						<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="remarks"/></b>
						</font>
						</div>
			  		</td>
			  		<td width="24%"  class="tdfonthead">
			  		<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="collectionDate"/></b>
						</font>
						</div>
			  		</td>
			  		</tr>
			<logic:iterate id="patBelongingVOs" name="<%=OpdConfig.PATIENT_BELONGING_DETAILS_VOS %>" type="hisglobal.vo.PatientBelongingVO">
				<logic:empty name="patBelongingVOs" property="handOverTo">
				<tr>
			  	<td class="tdfont">
			  	<html:checkbox name="patientBelongingFB" property="chk" value="<%=patBelongingVOs.getBelongingItemCode() +"^"+ patBelongingVOs.getCollectionDate()%>"></html:checkbox>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getBelongingItemName()%>
			  		</div>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getQuantity()%>
			  		</div>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getRemarks() %>
			  		</div>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getCollectionDate() %>
			  		</div>
			  	</td>
			  	
			  
			  	</tr>
			  
			</logic:empty>
			</logic:iterate>
			
			</table>
		</his:ContentTag>
		</his:statusNew>
		<his:statusTransactionInProcess>
		
		<his:SubTitleTag name="Modify Patient Belonging Detail"> </his:SubTitleTag>
		<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		
			<td width="32%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="itemName"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="32%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="description"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="32%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="remarks"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="4%"  class="tdfonthead">
	  		</td>
	  		</tr>
	  		<tr>
	  		<td  class="tdfont">
	  			<div align="center">
	  			<html:select name="patientBelongingFB" tabindex="1" property="belongingItemCode" styleClass="regcbo" tabindex="1">
	  				<html:option value="-1">Select Value</html:option>
	  				<logic:present name="<%=OpdConfig.ESSENTIALBO_BELONGING_LIST%>" >
					<html:options  collection="<%=OpdConfig.ESSENTIALBO_BELONGING_LIST%>" property="value" labelProperty="label" />
					</logic:present>
	  			</html:select>
	  			</div>
	  		</td>
	  		<td  class="tdfont">
	  			<div align="center">
	  			<html:textarea name="patientBelongingFB" property="quantity" styleClass="textarea" tabindex="1"></html:textarea>
	  			
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="patientBelongingFB" property="remarks" styleClass="textbox" tabindex="1"></html:text>
	  			</div>
	  		</td>
	  		
	  		</tr>
	  		
	  	
	  			
	  		
	  		
	  	
	  	
	  	</table>
	</his:ContentTag>
		
		
		</his:statusTransactionInProcess>
		
</logic:equal>


<logic:equal name="patientBelongingFB" property="selectedMode" value="HANDOVER">
		<his:TitleTag name="Handover Patient Belongings">
		</his:TitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
					<td width="4%"  class="tdfonthead">
						
			  		</td>
				
					<td width="24%"  class="tdfonthead">
						<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="itemName"/></b>
						</font>
						</div>
			  		</td>
			  		<td width="24%"  class="tdfonthead">
						<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="description"/></b>
						</font>
						</div>
			  		</td>
			  		<td width="24%"  class="tdfonthead">
						<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="remarks"/></b>
						</font>
						</div>
			  		</td>
			  		<td width="24%"  class="tdfonthead">
			  		<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="collectionDate"/></b>
						</font>
						</div>
			  		</td>
			  		</tr>
			<logic:iterate id="patBelongingVOs" name="<%=OpdConfig.PATIENT_BELONGING_DETAILS_VOS %>" type="hisglobal.vo.PatientBelongingVO">
				<logic:empty name="patBelongingVOs" property="handOverTo">
				<tr>
			  	<td class="tdfont">
			  	<html:checkbox name="patientBelongingFB" property="chk" value='<%=patBelongingVOs.getBelongingItemCode() +"^"+ patBelongingVOs.getCollectionDate()%>'></html:checkbox>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getBelongingItemName()%>
			  		</div>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getQuantity()%>
			  		</div>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getRemarks() %>
			  		</div>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getCollectionDate() %>
			  		</div>
			  	</td>
			  	
			  
			  	</tr>
			  
			</logic:empty>
			</logic:iterate>
			
			</table>
		</his:ContentTag>
		<his:SubTitleTag name="Handover Details">
		</his:SubTitleTag>
		<his:ContentTag>
		<table width="100%"  border="0" cellspacing="1" cellpadding="0">
			<tr>
			<td class="tdfonthead">
				<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>
					<bean:message key="handoverto"/></b>
					</font>
				</div>
			</td>
			<td  class="tdfont">
	  			<div align="left">
	  			<html:select name="patientBelongingFB" tabindex="1" property="relation" styleClass="regcbo" onchange="showNameTextBox(this)">
	  				<html:option value="-1">Select Value</html:option>
					<html:option value="<%=OpdConfig.OPTION_VALUE_SELF %>"><bean:message key="self" /></html:option>
					<html:option value="<%=OpdConfig.OPTION_VALUE_RELATIVE %>"><bean:message key="relative" /></html:option>
					<html:option value="<%=OpdConfig.OPTION_VALUE_OTHER %>"><bean:message key="other" /></html:option>
	  			</html:select>
	  			</div>
	  		</td>
			<td  class="tdfont">
	  			<div align="left">
	  			<html:text name="patientBelongingFB" property="handOverTo" styleClass="textbox"></html:text>
	  			
	  			</div>
	  		</td>
	  		
			</tr>
		</table>
		</his:ContentTag>
</logic:equal>

<logic:equal name="patientBelongingFB" property="selectedMode" value="HANDOVERLIST">
		<his:TitleTag name="Handed over Patient Belongings List">
		</his:TitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
					
				
					<td width="20%"  class="tdfonthead">
						<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="itemName"/></b>
						</font>
						</div>
			  		</td>
			  		<td width="20%"  class="tdfonthead">
						<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="description"/></b>
						</font>
						</div>
			  		</td>
			  		
			  		
			  		<td width="20%"  class="tdfonthead">
			  		<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="handoverto"/></b>
						</font>
						</div>
			  		</td>
			  		<td width="20%"  class="tdfonthead">
			  		<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="handOverBy"/></b>
						</font>
						</div>
			  		</td>
			  		<td width="20%"  class="tdfonthead">
			  		<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="handoverDate"/></b>
						</font>
						</div>
			  		</td>
			  		</tr>
			<logic:iterate id="patBelongingVOs" name="<%=OpdConfig.PATIENT_BELONGING_DETAILS_VOS %>" type="hisglobal.vo.PatientBelongingVO">
			<logic:notEmpty name="patBelongingVOs" property="handOverTo">
				<tr>
			  
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getBelongingItemName()%>
			  		</div>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getQuantity()%>
			  		</div>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  		<%String handoverto=patBelongingVOs.getHandOverTo();
			  		String relation=handoverto.substring(handoverto.indexOf("^")+1);
			  		String relationName=handoverto.substring(0,handoverto.indexOf("^"));%>
			  		<%if(relation.equals("0"))
			  		{ %>
			  			<bean:message key="self"/>
			  		<% } 
			  			else
			  				{%>
			  			<%=relationName %>
			  		<%} %>
			  		</div>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getHandOverBy() %>
			  		</div>
			  	</td>
			  	<td class="tdfont">
			  		<div align="center">
			  			<%= patBelongingVOs.getHandOverDate() %>
			  		</div>
			  	</td>
			  	
			  
			  	</tr>
			  
			</logic:notEmpty>
			</logic:iterate>
			
			</table>
		</his:ContentTag>
		
</logic:equal>


<his:ButtonToolBarTag>
		<div align="center">
		<logic:equal name="patientBelongingFB" property="selectedMode" value="HANDOVER">
	   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick = "submitFormOnValidate(saveHandOverDetails(),'HANDOVERSAVE');" onkeypress="if (event.keyCode==13) submitFormOnValidate(saveHandOverDetails(),'HANDOVERSAVE');" >
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('GETPATDTL');" tabindex="1" onclick ="submitForm('GETPATDTL');">
       	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
       	</logic:equal>
       	<logic:equal name="patientBelongingFB" property="selectedMode" value="HANDOVERLIST">
       	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('GETPATDTL');" tabindex="1" onclick ="submitForm('GETPATDTL');">
       	</logic:equal>
       	<logic:equal name="patientBelongingFB" property="selectedMode" value="MODIFY">
       	<his:statusNew>
       		<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style=cursor:pointer tabindex="1" onclick = "submitFormOnValidate(modifyDetails(),'MODIFYBELONGING');" onkeypress="if (event.keyCode==13) submitFormOnValidate(modifyDetails(),'MODIFYBELONGING');" >
	       		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('GETPATDTL');" tabindex="1" onclick ="submitForm('GETPATDTL');">
       	</his:statusNew>
       	<his:statusTransactionInProcess>
		   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick = "submitFormOnValidate(validatemodifyBelongingDetail(),'MODIFYSAVE');" onkeypress="if (event.keyCode==13) submitFormOnValidate(validatemodifyBelongingDetail(),'MODIFYSAVE');" >
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('GETPATDTL');" tabindex="1" onclick ="submitForm('GETPATDTL');">
	       	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
       	</his:statusTransactionInProcess>
       	</logic:equal>
    </div>

</his:ButtonToolBarTag>

</his:TransactionContainer>
<html:hidden name="patientBelongingFB" property="hmode"/>
<html:hidden name="patientBelongingFB" property="patCrNo"/>
<his:status/>
</html:form>

