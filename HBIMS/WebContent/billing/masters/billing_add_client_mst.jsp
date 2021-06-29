<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head><meta charset=utf-8>
<title>Client Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language ="javaScript">

function validate1()
{   
        var hisValidator = new HISValidator("clientBean");
        hisValidator.addValidation("strClientName", "req","Client Name is a Mandatory Field");
		hisValidator.addValidation("strClientName", "maxlen=100", "Client Name should have less than or equal to 100 Characters" );
		hisValidator.addValidation("strClientCode", "req","Client Code is a Mandatory Field");
		hisValidator.addValidation("strClientCode", "maxlen=15", "Client Code should have less than or equal to 15 Characters" );
		hisValidator.addValidation("strRegistrationNo", "req","RegistrationNo is a Mandatory Field");
		hisValidator.addValidation("strRegistrationNo", "maxlen=50", "RegistrationNo should have less than or equal to 50 Characters" );
		
		hisValidator.addValidation("strContactPerson", "req","Contact Person is a Mandatory Field");
		hisValidator.addValidation("strContactPerson", "maxlen=100", "Contact Person should have less than or equal to 100 Characters" );
		
		hisValidator.addValidation("strClientAddress", "maxlen=240", "Client Address should have less than or equal to 240 Characters" );
		
		hisValidator.addValidation("strContactNo", "maxlen=11", "Contact No should have less than or equal to 100 Characters" );
		
		hisValidator.addValidation("strEmail", "maxlen=100", "Email should have less than or equal to 100 Characters" );
		
		hisValidator.addValidation("strNearestOff", "maxlen=250", "Nearest Off should have less than or equal to 250 Characters" );
		
		hisValidator.addValidation("strPaymentType", "dontselect=0","Please select a value from Payment Type Combo");
		
		hisValidator.addValidation("strRemarks", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
		
		hisValidator.addValidation("strFaxNo", "maxlen=16", "Fax should have less than or equal to 50 numerals" );
		
		hisValidator.addValidation("strPaymentType", "dontselect=0","Please select a value from Payment Type Combo");
		
	    if(parseFloat(document.getElementById("strIpdPayRule").value)=="0")
	    {
		    alert("0% is not allowed");
		    document.getElementById("strIpdPayRule").focus();
		    return false;
		}
		if(parseFloat(document.getElementById("strIpdPayRule").value)>"100") 
	    {
			alert("Greater than 100% is not allowed");
			document.getElementById("strIpdPayRule").focus();
		    return false;
	    }
        var retVal = hisValidator.validate(); 
        if(retVal)
		 {
			    var Checkbox = document.getElementById("isOPD").checked ;
		        if(!Checkbox)
		        {
		        	  Checkbox = document.getElementById("isIPD").checked ;
			          if(!Checkbox)
				      {
			        	  Checkbox = document.getElementById("isEME").checked ;
			        	  if(!Checkbox)
					      {
			                   alert('Please Select a value from Coverage Type CheckBox');
			                   retVal=false; 
					      }
				      }
		        } 
		 }  
         if(retVal)
		 {
    		 
             if(document.getElementsByName('hmode')[0].value == 'MODIFY')
             {
             	 document.forms[0].hmode.value = "UPDATE";
             	 document.forms[0].submit();
             }
             else
             {
             	 document.forms[0].hmode.value = "SAVE";
                 document.forms[0].submit();
             }         
          }
          else
          {
             return false;
          }
	}
	
	function onLoadOfPage()
	{
		var hiddenPayType=document.getElementsByName('hiddenPayType')[0].value;
		var payTypeObj=document.getElementsByName('strPaymentType')[0];
		
		for(var i=0;i<payTypeObj.options.length;i++)
		{
			if(payTypeObj.options[i].value.trim()==hiddenPayType.trim())
			{
				payTypeObj.selectedIndex=i;
				break;
			}
		}
		
		var hiddenCatType=document.getElementsByName('hiddenSelCat')[0].value;
		var catTypeObj=document.getElementsByName('strSelCat')[0];
		
		for(var i=0;i<catTypeObj.options.length;i++)
		{
			if(catTypeObj.options[i].value.trim()==hiddenCatType.trim())
			{
				catTypeObj.selectedIndex=i;
				break;
			}
		}
		
		if(document.getElementsByName('hmode')[0].value.trim() != 'MODIFY')
		document.getElementsByName('strIsValid')[0].checked="true";
	}


</script>

</head>
<body onload="onLoadOfPage();">
<html:form action="/masters/CNTClientMst.cnt"
	type="billing.masters.controller.fb.ClientMstFB" name="clientBean">
	
	
	<div class="errMsg"><bean:write name="clientBean" property="strErr"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="clientBean" property="strMsg"/></div>
	<div class="warningMsg" id='warningMsg'><bean:write name="clientBean" property="strWarning"/></div>
		<center>
	<tag:tab tabLabel="Client Details" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	</center>
    <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">   
	
	<tr class="HEADER">
			<td colspan="2">Client Master</td>
	</tr>
			
	<tr>
			<td class="LABEL">
			<logic:equal name="clientBean" property="hmode" value="MODIFY">
				Client Type
			</logic:equal>
			<logic:notEqual name="clientBean" property="hmode" value="MODIFY">
				Client Type
			</logic:notEqual>
			</td>
			<td class="CONTROL">
			<bean:write name="clientBean" property="strClientType" filter="false"/>
			</td>
		</tr>
		
      <tr >
        <td class="LABEL"><font color="red">*</font>Client Name</td>
        <td width="50%" class ="CONTROL">
        	<input type="text" class='txtFldMax' name="strClientName" maxlength="100" onkeypress="return validateData(event,4);" value="${clientBean.strClientName}"> 
        </td>
      </tr>
      <tr>
        <td class="LABEL" width="50%"><font color="red">*</font>Client Short Name/Code</td>
			<td class="CONTROL" width="50%">
			<input type="text" class="txtFldMax" style="text-transform: uppercase" name="strClientCode" maxlength="15" onkeypress="return validateData(event,8);" value ="${clientBean.strClientCode}"></td>
       <tr>
        <td class="LABEL"><font color="red">*</font>Registration No</td>
        <td width="50%" class ="CONTROL"><input type="text" class='txtFldMax' name="strRegistrationNo" maxlength="50" onkeypress="return validateData(event,8);" value="${clientBean.strRegistrationNo}"></td>
      </tr>
      
      <tr >
        <td class="LABEL"><font color="red">*</font>Contact Person</td>
        <td width="50%" class ="CONTROL"><input type="text" class='txtFldMax' name="strContactPerson" maxlength="100" onkeypress="return validateData(event,11);" value="${clientBean.strContactPerson}"> </td>
      </tr>
      
      <tr>
			<td class="LABEL">Contact Address</td>
			<td class="CONTROL">
			<textarea name="strClientAddress" cols="15" rows="2" id="strClientAddress" onkeypress="return validateData(event,9);" >${clientBean.strClientAddress}</textarea>
			</td>
	  </tr>
	  
	  <tr >
        <td class="LABEL">Contact No</td>
        <td width="50%" class ="CONTROL"><input type="text" class='txtFldMax' maxlength="11" name="strContactNo" onkeypress="return validateData(event,5);" value="${clientBean.strContactNo}"> </td>
      </tr>
      
      <tr>
        <td class="LABEL">E-Mail</td>
        <td width="50%" class ="CONTROL"><input type="text" class='txtFldMax' name="strEmail" maxlength="100" onkeypress="return validateData(event,1);" value="${clientBean.strEmail}"> </td>
      </tr>  
      
      <tr>
        <td class="LABEL">Nearest Office</td>
        <td width="50%" class ="CONTROL"><input type="text" class='txtFldMax' name="strNearestOff" maxlength="100" onkeypress="return validateData(event,9);" value="${clientBean.strNearestOff}"> </td>
      </tr>  
      
      <tr>
			<td class="LABEL">
			<font color="red">*</font>Payment Type
			</td>
			<td class="CONTROL">
			<select name="strPaymentType" class="comboNormal">
				<option value="0" selected>Select Value</option>
				<option value="1">PRE-PAID</option>
				<option value="2">POST-PAID</option>
			</select>
			</td>
		</tr>
		
		<tr>
			<td class="LABEL"><font color="red">*</font>Coverage</td>
			<td class="CONTROL">
				<html:checkbox name="clientBean" property="isOPD" styleId="isOPD" value="1" />OPD
				<html:checkbox name="clientBean" property="isIPD" styleId="isIPD" value="1" />IPD
				<html:checkbox name="clientBean" property="isEME" styleId="isEME" value="1" />Emergency
			</td>
		</tr>
		
		<tr>
			<td class="LABEL">Ipd Payment Rule(Paid By Client)</td>
			<td class="CONTROL" >
			<input type="text" class='txtFldMax' name="strIpdPayRule" id="strIpdPayRule" maxlength="6,2" onkeypress="return validateData(event,7);" value="${clientBean.strIpdPayRule}">%
			</td>
		</tr>
		
		 <tr>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL" >
			<textarea name="strRemarks" cols="15" rows="2" id="strRemarks" onkeypress="return validateData(event,9);">${clientBean.strRemarks}</textarea>
			</td>
		</tr>
		
		<tr>
			<td class="LABEL">FAX</td>
			<td class="CONTROL" >
			<input type="text" class='txtFldMax' name="strFaxNo" maxlength="16" onkeypress="return validateData(event,6);" value="${clientBean.strFaxNo}">
			</td>
		</tr>
		
		<tr style='display: none;'>
			<td class="LABEL"><font color="red">*</font>Category</td>
			<td class="CONTROL">
			<select name="strSelCat" class="comboNormal">
				<bean:write name="clientBean" property="strCatValues" filter="false"/>				
			</select>
			</td>
		</tr>
		
		 <tr >
	    <td width ="45%" class ="LABEL">Record Status </td>
	    <td width ="45%" class ="CONTROL" >
	   		<html:radio name="clientBean" property="strIsValid" value="1" >Active</html:radio>
	    	<html:radio name="clientBean" property="strIsValid" value="2">InActive</html:radio>
        </td>
    </tr>
		
	</table>
    
			
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="FOOTER">
			 <td colspan="2"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>

    <div align="center">
    <!-- 
      <img name="save" src="../../hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand;" title="Save Record"  onClick="return validate1(); "/>
<img src="../../hisglobal/images/btn-clr.png" style="cursor:pointer;cursor:hand;" title="Reset Content"
				onClick="document.forms[0].reset();" />
      <img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;cursor:hand;" title="Cancel Process" onClick="cancel('LIST');" /> 
      -->
      <br><a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
      
    
    
    
	</div>	
	    <input type="hidden" name="hmode" value="${clientBean.hmode}"/>
	    <input type="hidden" name="strClientTypeId" value="${clientBean.strClientTypeId}"/>
	    <input type="hidden" name="hiddenPayType" value="${clientBean.strPaymentType}"/>
	    <input type="hidden" name="hiddenSelCat" value="${clientBean.strSelCat}"/>
	    <input type="hidden" name="strClientId" value="${clientBean.strClientId}"/>
	    <input type="hidden" name="strClientType" value="${clientBean.strClientType}"/>
	    
<cmbPers:cmbPers/>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>