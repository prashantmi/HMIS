<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head><meta charset=utf-8>
<title>Client Master Modify Page</title>
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

		alert("Start Validation");

		hisValidator.addValidation("strClientName", "req","Client Name is a Mandatory Field");
		hisValidator.addValidation("strClientName", "maxlen=100", "Client Name should have less than or equal to 100 Characters" );

		hisValidator.addValidation("strClientCode", "req","Client Code is a Mandatory Field");
		hisValidator.addValidation("strClientCode", "maxlen=15", "Client Code should have less than or equal to 15 Characters" );
		
	// 	hisValidator.addValidation("strRegistrationNo", "req","RegistrationNo is a Mandatory Field");
		hisValidator.addValidation("strRegistrationNo", "maxlen=50", "RegistrationNo should have less than or equal to 50 Characters" );

// 		hisValidator.addValidation("strContactPerson", "req","Contact Person is a Mandatory Field");
		hisValidator.addValidation("strContactPerson", "maxlen=100", "Contact Person should have less than or equal to 100 Characters" );

	// 	hisValidator.addValidation("strContactNo", "req","Contact No is a Mandatory Field");
		hisValidator.addValidation("strContactNo", "maxlen=100", "Contact No should have less than or equal to 100 Characters" );

	// 	hisValidator.addValidation("strClientAddress", "req","Client Address is a Mandatory Field");
		hisValidator.addValidation("strClientAddress", "maxlen=250", "Client Address should have less than or equal to 250 Characters" );
		
	// 	hisValidator.addValidation("strNearestOff", "req","Nearest Off is a Mandatory Field");
		hisValidator.addValidation("strNearestOff", "maxlen=250", "Nearest Off should have less than or equal to 250 Characters" );

	// 	hisValidator.addValidation("strEmail", "req","Email is a Mandatory Field");
		hisValidator.addValidation("strEmail", "maxlen=100", "Email should have less than or equal to 100 Characters" );

	// 	hisValidator.addValidation("strClientName", "req","Client Name is a Mandatory Field");
	// 	hisValidator.addValidation("strClientName", "maxlen=100", "Client Name should have less than or equal to 100 Characters" );
		
		hisValidator.addValidation("strRemarks", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );

        hisValidator.addValidation("strEffectiveDate", "date","Effective From Date is a mandatory field");
	// 	hisValidator.addValidation("strEffectiveDate", "dtgtet="+"${clientBean.strEffectiveDate}", "Effective Date should be Greater than or Equal to Current Date");
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
		        document.forms[0].hmode.value = "UPDATE";
				document.forms[0].submit();
		 }
        else
        {
             return false;
        }
         
	}

</script>

</head>
<body onLoad="">
<html:form action="/masters/CNTClientMst.cnt"
	type="billing.masters.controller.fb.ClientMstFB">
	
	
	<div class="errMsg"><bean:write name="clientBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="clientBean" property="strWarning"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="clientBean" property="strMsg"/></div>
		<center>
	<tag:tab tabLabel="Modify Client" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	</center>
    <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">   
	<tr class="HEADER">
			<td colspan="2">Client Master&gt;&gt; Modify</td>
		</tr>
		
		
      <tr >
        <td class="LABEL"><font color="red">*</font>Client Name</td>
        <td width="50%" class ="CONTROL"><input type="text" class="txtFldMax" name="strClientName" value ="${clientBean.strClientName}" onkeypress="return validateData(event,4);"> </td>
      </tr>
      <tr>
        <td class="LABEL" width="50%"><font color="red">*</font>Client Short Name/Code</td>
			<td class="CONTROL" width="50%">
			<input type="text" class="txtFldMax" style="text-transform: uppercase" name="strClientCode" value ="${clientBean.strClientCode}" maxlength="15" onkeypress="return validateData(event,8);"></td>
       <tr>
      <tr> 
        <td width="50%" class="LABEL"><font color="red">*</font>Payment Type</td>
        <td class="CONTROL" ><html:select name="clientBean"  property="strPaymentType"  >
    	<html:option value="1"> PRE-PAID</html:option>
    	<html:option value="2">POST-PAID </html:option>
        </html:select></td>
      </tr>        
      
      
      <tr> 
        <td width="50%" class="LABEL"><font color="red">*</font>Client Type</td>
       <td class="CONTROL">
       <html:select name="clientBean" property="strClientType">
    	
    	
    	<html:option value="1"> Insurance</html:option>
    	<html:option value="2">Corporate </html:option>
    	<html:option value="3">Customer</</html:option>
    	          </html:select>
			
			</td>
      </tr>

      <tr >
        <td class="LABEL">Registration No</td>
        <td width="50%" class ="CONTROL"><input type="text" class='txtFldMax' name="strRegistrationNo" value ="${clientBean.strRegistrationNo}" maxlength="50"></td>
      </tr>

      <tr >
        <td class="LABEL">Contact Person</td>
        <td width="50%" class ="CONTROL"><input type="text" class='txtFldMax' name="strContactPerson" value ="${clientBean.strContactPerson}" onkeypress="return validateData(event,4);" maxlength="100"> </td>
      </tr>
      
      <tr >
        <td class="LABEL">Contact No</td>
        <td width="50%" class ="CONTROL"><input type="text" class='txtFldMax' maxlength="11" name="strContactNo" value ="${clientBean.strContactNo}" onkeypress="return validateData(event,5);"> </td>
      </tr>
        
      <tr>
			<td class="LABEL">Client Address</td>
			<td class="CONTROL">
			<textarea name="strClientAddress" cols="20" rows="2" id="strClientAddress"><bean:write name="clientBean" property="strClientAddress"/></textarea>
     		</td>
	  </tr>
    
      <tr>
        <td class="LABEL">Nearest Office</td>
        <td width="50%" class ="CONTROL"><input type="text" class='txtFldMax' name="strNearestOff" value ="${clientBean.strNearestOff}" onkeypress="return validateData(event,4);" maxlength="100"> </td>
      </tr>      
      
      <tr>
        <td class="LABEL">E-Mail</td>
        <td width="50%" class ="CONTROL"><input type="text" class='txtFldMax' name="strEmail" value ="${clientBean.strEmail}" onkeypress="return validateData(event,1);" maxlength="100"> </td>
      </tr>      
      <tr>
			<td class="LABEL">
			Effective From
			</td>
			<td class="CONTROL"><font color="black"><bean:write name="clientBean" property="strEffectiveDate" /></font>
				 
	    	</td>
		</tr>
	</table>
    <table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
	 <tr>
	 <td class="CONTROL">
	   <div id="coverageid"> 
	    <html:checkbox name="clientBean" property="isOPD" styleId="isOPD" value="1">OPD Covers</html:checkbox>
        <html:checkbox name="clientBean" property="isIPD" styleId="isIPD" value="1">IPD Covers</html:checkbox>
        <html:checkbox name="clientBean" property="isEME" styleID="isEME"  value="1">Emergency Covers</html:checkbox>
       </div>
       </td>
   </tr>
    </table>
    <table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
    <tr>
			<td class="LABEL">Ipd Payment Rule(Paid By Client)</td>
			<td class="CONTROL" >
			<input type="text" class='txtFldMax' name="strIpdPayRule" id="strIpdPayRule" maxlength="6,2" onkeypress="return validateData(event,7);" value="${clientBean.strIpdPayRule}">%
			</td>
	</tr>
	</table>
	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px" cellspacing="1px">
		 <tr>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL" width=50%><textarea name="strRemarks" cols="20" rows="2" id="strRemarks"><bean:write name="clientBean" property="strRemarks" filter="false"/></textarea></td>
		</tr>
		 <tr >
    <td width ="45%" class ="LABEL">Record Status </td>
    <td width ="45%" class ="CONTROL" >
   <html:radio name="clientBean" property="strIsValid" value="1">Active</html:radio>
    <html:radio name="clientBean" property="strIsValid" value="2">InActive</html:radio>
    
   </td>
    </tr>
		
		<tr class="FOOTER">
			 <td colspan="2"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>

    <div align="center">
    
<!--      <img name="save" src="../../hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand;" title="Save Record"  onClick="return validate1(); "/>

      <img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;cursor:hand;" title="Cancel the Process" onClick="cancel('LIST');" /> 
    -->
    	<br><a href="#" class="button" id="" onclick='return validate1();'><span class="save">Save</span></a>
				<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
     	</div>	
	
        <input type="hidden" name="strChk" value="${clientBean.strChk}">
	    <input type="hidden" name="hmode" value=""/>
	    <cmbPers:cmbPers/>
	    
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>