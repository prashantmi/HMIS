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

function validateAddBelongingDetail()
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

function vaidateRemoveDetail(belongingItemCode)
{

	document.forms[0].removeBelongingCode.value=belongingItemCode;
	alert("belongingItemCode"+document.forms[0].removeBelongingCode.value);
	return true;
}

</script>

<%@ page import ="opd.*" %>

<html:form action="/opdPatientBelonging">
<his:TransactionContainer>
<%String transactionStatus=""; %>
<his:TitleTag name="Patient Belonging">
</his:TitleTag>

<his:InputCrNoTag name="patientBelongingFB">
	
	</his:InputCrNoTag>
	

<bean:define id="crNo" name="patientBelongingFB" property="patCrNo" type="java.lang.String"/>
<%if(!crNo.trim().equals("")){
	transactionStatus="INPROCESS";
%>	
	
<jsp:include page="/registration/patientDetail.cnt" flush="true"/>

<%if(session.getAttribute(OpdConfig.PATIENT_BELONGING_DETAILS_VOS)!=null){ 
%>

<his:SubTitleTag name="Patient Belonging Detail">
<input type="button" name="modify" value="Modify" onclick="submitForm('MODIFY')">
<input type="button" name="handOver" value="HandOver" onclick="submitForm('HANDOVER')">
<input type="button" name="handOveritem" value="Handed Over Item" onclick="submitForm('HANDOVERLIST')">
 </his:SubTitleTag>
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		
			<td width="25%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="itemName"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="25%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="description"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="25%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="remarks"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="25%"  class="tdfonthead">
	  		<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="collectionDate"/></b>
				</font>
				</div>
	  		</td>
	  		</tr>
	  		
	  	<logic:iterate id="patBelongingVOs" indexId="idx" name="<%=OpdConfig.PATIENT_BELONGING_DETAILS_VOS%>" type="hisglobal.vo.PatientBelongingVO">
	  	<logic:empty name="patBelongingVOs" property="handOverTo">
	  	
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

<%} %>

<his:SubTitleTag name="Add Patient Belonging Detail"> </his:SubTitleTag>
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
	  			<html:select name="patientBelongingFB" tabindex="1" property="belongingItemCode" styleClass="regcbo">
	  				<html:option value="-1">Select Value</html:option>
	  				<logic:present name="<%=OpdConfig.ESSENTIALBO_BELONGING_LIST%>" >
					<html:options  collection="<%=OpdConfig.ESSENTIALBO_BELONGING_LIST%>" property="value" labelProperty="label" />
					</logic:present>
	  			</html:select>
	  			</div>
	  		</td>
	  		<td  class="tdfont">
	  			<div align="center">
	  			<html:text name="patientBelongingFB" property="quantity" styleClass="textbox" maxlength="20"></html:text>
	  			
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  			<html:text name="patientBelongingFB" property="remarks" styleClass="textbox" maxlength="50"></html:text>
	  			</div>
	  		</td>
	  		<td  class="tdfont">
			<div align="center">   
			<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' title="Add Belonging Detail" onclick="submitFormOnValidate(validateAddBelongingDetail(),'ADDDETAIL')">
			</div>
			</td> 
	  		</tr>
	  		
	  		<logic:notEmpty name="<%=OpdConfig.PATIENT_BELONGING_MAP %>">
	  		<logic:iterate id="belongingMap" name="<%=OpdConfig.PATIENT_BELONGING_MAP %>" type="java.util.Map.Entry">
	  		<bean:define id="key" name="belongingMap" property="key"></bean:define>
	  		<bean:define id="list" name="belongingMap" property="value" type="java.util.ArrayList"></bean:define>
	  	  	
	  		
	  			
	  		<tr >
	  	
	  		<td class="tdfont">
	  			
	  		<div align="center">
	  			<%=list.get(0) %>
	  		</div>
	  		</td>
	  		<td class="tdfont">
	  		<div align="center">
	  			<%=list.get(2) %>
	  		</div>
	  		</td>
	  		<td class="tdfont">
	  		<div align="center">
	  			<%=list.get(3) %>
	  		</div>
	  		</td>
	  		<td  class="tdfont">
			<div align="center">   
			<img class="button" id="deleteButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/Mi_Green_Sqr.png"/>' title="Remove Detail" onclick="submitFormOnValidate(vaidateRemoveDetail('<%=key %>'),'REMOVEDETAIL')">
			</div>
			</td> 
	  	</tr>
	  			
	  		</logic:iterate>
	  		</logic:notEmpty>
	  	
	  	
	  	</table>
	</his:ContentTag>

<%} %>

<his:ButtonToolBarTag>

<%if(transactionStatus.equals("INPROCESS")){%>

	<div align="center">
	   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick = "submitFormOnValidate(validateAddBelongingDetail(),'SAVE');" onkeypress="if (event.keyCode==13) submitFormOnValidate(validateAddBelongingDetail(),'SAVE');" >
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
       	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
    </div>
                  			          
<%}else{ %>
         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
      			          
     			        		
<%} %>
			
</his:ButtonToolBarTag>
 

		  <his:status/>
		  			

</his:TransactionContainer>
<html:hidden name="patientBelongingFB" property="hmode"/>
<html:hidden name="patientBelongingFB" property="removeBelongingCode"/>
</html:form>