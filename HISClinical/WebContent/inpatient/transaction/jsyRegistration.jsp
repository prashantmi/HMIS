<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:javascript src="/inpatient/js/anc_history_detail.js"/>
<his:javascript src="/inpatient/js/anc_detail.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

 /*function submitPage(mode)
 {
    document.getElementsByName('patCrNo')[0].value=document.getElementsByName('patCrNoText')[0].value;
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
 }*/

function submitButton(mode)
 {  
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}

function submitButtonOnValidation(mode)
 {  
   if(validateBroughtByInfo() && validateOnSave()){
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit(); }
 }


function validateOnSave()
 {
   var valid=true;
    var minAgeJsyRule=parseInt(document.getElementsByName('patMinAgeJsyRule')[0].value);
    var maxAgeJsyRule=parseInt(document.getElementsByName('patMaxAgeJsyRule')[0].value);
    var patAge=parseInt(document.getElementsByName('patAge')[0].value);
     
    var liveBirth=parseInt(document.getElementsByName('liveBirthCount')[0].value);
    var liveBirthjsyRule=parseInt(document.getElementsByName('liveBirthJsyRule')[0].value);
     
    //alert(document.getElementsByName('patPrimaryCategory')[0].value);
    var primaryCat=parseInt(document.getElementsByName('patPrimaryCategory')[0].value);
    var primaryCatJsyRule=parseInt(document.getElementsByName('patPrimaryCatJsyRule')[0].value);
    var primaryCatName=document.getElementsByName('primaryCategoryName')[0].value;
    
    //alert("primaryCat"+primaryCat);
    //alert("primaryCatJsyRule"+primaryCatJsyRule);
    
     var regUpTo=parseInt(document.getElementsByName('registrationUpto')[0].value); 
     var gestationDays=parseInt(document.getElementsByName('gestationPeriodDays')[0].value); 
     
       var casteId=document.getElementsByName('patCaste')[0].value;
       var casteIdJsyRule=document.getElementsByName('casteNameJsyRule')[0].value;
  
       var str="<font color='red'>Following Rules of JSY Registration do not follow :</font>"+"<br>",seq=1;
  
    if(gestationDays>regUpTo)
    {
      str=str+"<br>"+"  "+"<font color='red'>"+(seq++)+". Gestation peried for registration is elapsed (greater than "+regUpTo/7+" Weeks)</font>";
    // alert("Gestation Data Elapsed")
     valid=false;
    } 
    if(patAge<minAgeJsyRule || patAge>maxAgeJsyRule)
    {
       str=str+"<br>"+"  "+"<font color='red'>"+(seq++)+". Patient age is not in range of"+" "+"("+minAgeJsyRule+","+maxAgeJsyRule+")"+ "</font>";
      // alert("Patient Age Should greater than"+""+minAgeJsyRule);
        valid=false;
    }
  //  if(patAge>maxAgeJsyRule)
  //  {
  //     str=str+"<br>"+"  "+"<font color='red'>"+(seq++)+". Patient Age Less</font>";
      //alert("Patient Age Should Less than"+""+maxAgeJsyRule);
  //    valid=false;
  //  }
    if(liveBirth>liveBirthjsyRule)
    {
       str=str+"<br>"+"  "+"<font color='red'>"+(seq++)+". No of Livebirth are greater than "+" "+liveBirthjsyRule+" .</font>";
     // alert("LiveBirth should less Than"+""+liveBirthjsyRule);
      valid=false;
    }
    if(primaryCat!=primaryCatJsyRule)
    {
      str=str+"<br>"+"  "+"<font color='red'>"+(seq++)+". Primary Category is not"+" "+primaryCatName+" .</font>";
      //alert("Primary Category Is Not BPL");
      valid=false;
    }
    if(!checkForCaste())
    {
      str=str+"<br>"+"  "+"<font color='red'>"+(seq++)+". Patient Caste does not match"+" "+casteIdJsyRule+" .</font>";
     valid=false;
    }
   
   if(valid==false)
   {
     document.getElementById('showMessage').innerHTML="<br><b>"+str+"<br><b>";
   // alert(str);
   }
   
   return valid;
 } 

function checkForCaste()
{
  var valid=false;
    var isCasteBound=document.getElementsByName('isCasteBound')[0].value;
   
    var casteId=document.getElementsByName('patCaste')[0].value;
    var casteIdCombo=document.getElementsByName('patCasteText')[0];
      
    var casteJsyRule=document.getElementsByName('castIdjsyRule')[0].value; 
    var casteIdjsyRule=casteJsyRule.split("#");
   
   if(casteId=="")
   {
     casteId=casteIdCombo.value;
   }
   // alert("casteId ckeck"+casteId);
   if(isCasteBound=="1")
    {
      for(var i=0;i<casteIdjsyRule.length;i++)
      {
         if(casteId==casteIdjsyRule[i])
           {  valid=true; break; }
      }
    }
  //  if(valid==false)
  //  {
   //  alert("Caste does not Meet JSY Rule" );    }
   return valid;
} 


function comboValidation(obj, str)
{	
  var valid= true

 if(typeof obj!='undefined'){
       if(obj.value==-1)
	     {
		  alert("Please Select : "+str)
		  valid=false
		  obj.focus()
	     }
	 }
	return valid
}


function validateBroughtByInfo()
{
 var valid=true;
  var broughtById=document.getElementsByName('broughtByASHA')[0];
  //alert("broughtById "+broughtById.checked)
  
  
  if(comboValidation(document.getElementsByName('patCasteText')[0],'Caste') &&
     comboValidation(document.getElementsByName('areaTypeId')[0],'Area Type') )
	   {  
      if(broughtById.checked)
        {
         if(comboValidation(document.getElementsByName('healthWorkerID')[0],'HealthWorker ID') &&
           isEmpty(document.getElementsByName('healthWorkerName')[0],'HealthWorker Name') )
	        { valid=true; }
	     else { valid=false; }
	    }   
      }else {
       valid=false; }
   
     return valid ;
     
}

function showHealthWorkerInfo(broughtByHW)
{
  if(broughtByHW.checked)
  {
    document.getElementById('healthWorkerId').style.display="block";
  }else{
    document.getElementById('healthWorkerId').style.display="none";
    
    document.getElementsByName('healthWorkerID')[0].value="-1";
    document.getElementsByName('healthWorkerCardNo')[0].value="";
    document.getElementsByName('healthWorkerName')[0].value="";
    document.getElementsByName('healthWorkerAddress')[0].value="";
  }
}

window.onload = function() 
 {
   	showHealthWorkerInfo(document.getElementsByName('broughtByASHA')[0]);
 }    


</script>

<html:form action="/jsyRegistration">

   <%!	boolean readOnly=false; %>
	<logic:notEqual name="JSYRegitrationFB" property="healthWorkerID" value="">
	<% readOnly=true; %>
	</logic:notEqual>
  
<his:TransactionContainer>

 <his:TitleTag key="jsyreg">
 </his:TitleTag>

  <his:statusNew>
	<his:ContentTag>
	   <his:InputCrNoTag  name="JSYRegitrationFB" ></his:InputCrNoTag>
    </his:ContentTag>
      <his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'style="cursor: pointer" tabindex="1" onclick="submitButton('NEW')" onkeypress="if(event.keyCode==13) submitButton('NEW')">
   </his:ButtonToolBarTag>
    
  </his:statusNew>
  <his:statusList>
        <jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
        <his:SubTitleTag  name="Obstetric Detail">
        </his:SubTitleTag>    
          <his:ContentTag>
	        <table width="100%" cellpadding="0" cellspacing="1">
		     <tr>
			  <td width="25%" class="tdfonthead">
				<div align="right">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="ancno" /> 
				  </font>	
				</div>
			  </td>
			  
			  <td width="25%" class="tdfont">
				<div align="left">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:write name="JSYRegitrationFB" property="ancNo"/>
					<html:hidden name="JSYRegitrationFB" property="ancNo"/>
				  </font>	
				</div>
			  </td>
			  
			  <td width="25%" class="tdfonthead">
				<div align="right">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="gravidano" /> 
				  </font>	
				</div>
			  </td>
			  
			  <td width="25%" class="tdfont">
				<div align="left">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:write name="JSYRegitrationFB" property="gravidaNo"/>
					<html:hidden name="JSYRegitrationFB" property="gravidaNo"/>
				  </font>	
				</div>
			  </td>
			 </tr> 
			 
			 
			 
			 <tr>
			  <td width="25%" class="tdfonthead">
				<div align="right">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="eddNo" /> 
				  </font>	
				</div>
			  </td>
			  
			  <td width="25%" class="tdfont">
				<div align="left">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:write name="JSYRegitrationFB" property="expectedDeliveryDate"/>
					<html:hidden name="JSYRegitrationFB" property="expectedDeliveryDate"/>
				  </font>	
				</div>
			  </td>
			  
			  <td width="25%" class="tdfonthead">
				<div align="right">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="gestationweek" /> 
				  </font>	
				</div>
			  </td>
			  
			  <td width="25%" class="tdfont">
				<div align="left">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:write name="JSYRegitrationFB" property="gestationPeriod"/>
				  </font>	
				</div>
			  </td>
			 </tr> 
			 
			 
	        <tr>
	          <td width="25%" class="tdfonthead">
				<div align="right">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="liveBirth" /> 
				  </font>	
				</div>
			  </td>
			  <td width="25%" class="tdfont">
				<div align="left">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:write name="JSYRegitrationFB" property="liveBirthCount"/>
				  </font>	
				</div>
			  </td>
			  <td width="25%" class="tdfonthead">
			  </td>
			  
			  <td width="25%" class="tdfont">
			  </td>
		  </tr> 
			 
			 
			 
			 
        </table>
        </his:ContentTag>
        
        <his:SubTitleTag  name="JSY Detail">
        </his:SubTitleTag>   
            <his:ContentTag>
	        <table width="100%" cellpadding="0" cellspacing="1">
		     <tr>
			  <td width="25%" class="tdfonthead">
				<div align="right">
			      <logic:equal name="JSYRegitrationFB" property="patCaste" value="">
			        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
			      </logic:equal>
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="caste" /> 
				  </font>	
				</div>
			  </td>
		
			  <td width="25%" class="tdfont">
				<div align="left">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				    <logic:equal name="JSYRegitrationFB" property="patCaste" value="">
					  <html:select name="JSYRegitrationFB" property="patCasteText" style="width:160;">
					 	<html:option value="-1">Select Value</html:option>
					 	<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_CASTE_LIST %>" property = "value" labelProperty = "label"/>
					 </html:select>
					</logic:equal>
					<logic:notEqual name="JSYRegitrationFB" property="patCaste" value="">
					   <bean:write name="JSYRegitrationFB" property="patCasteName"/>
					</logic:notEqual>
				  </font>	
				</div>
			  </td>
			
			  
			  
			  
			    <td width="25%" class="tdfonthead">
				<div align="right">
				  <logic:equal name="JSYRegitrationFB" property="areaType" value="">
				  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				  </logic:equal>
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="areaType" /> 
				  </font>	
				</div>
			  </td>
			  
			    <td width="25%" class="tdfont">
				<div align="left">
				  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				  	<logic:equal name="JSYRegitrationFB" property="areaType" value="">
					  <html:select name="JSYRegitrationFB" property="areaTypeId" style="width:160;">
					 	<html:option value="-1">Select Value</html:option>
					 	<html:options collection="<%=InpatientConfig.AREA_CATEGORY_LIST %>"  property="value" labelProperty = "label"/>
					 </html:select>
					</logic:equal>
					<logic:notEqual name="JSYRegitrationFB" property="areaType" value="">
					  <bean:write name="JSYRegitrationFB" property="patIsUrban"/>
					</logic:notEqual>
					
				  </font>	
				</div>
			  </td>
			  
			  </tr>
					
			  </table>
			    </his:ContentTag>
			  <his:SubTitleTag >
			  	<his:name>
			    <div align="left">
			    <html:checkbox name="JSYRegitrationFB" property="broughtByASHA" disabled="<%=readOnly %>" value="1" onclick="showHealthWorkerInfo(this)"></html:checkbox>
			    &nbsp;&nbsp;
				<font color="" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="broughtByHealthWorker"/>
				</font>
			    </div>
			    </his:name>
			   </his:SubTitleTag>   
			
			<div id="healthWorkerId" style="display:none">  
              <his:ContentTag>
			   <table width="100%" cellpadding="0" cellspacing="1">
		        <tr>
			     <td width="25%" class="tdfonthead">
			       <div align="right">
			        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					 <bean:message key="healthWorkerType" /> 
				    </font>	
				   </div>
			     </td>
			     <td width="25%" class="tdfont">
				  <div align="left">
				   &nbsp;<html:select name="JSYRegitrationFB" property="healthWorkerID" disabled="<%=readOnly %>" style="width:160;">
					  <html:option value="-1">Select Value</html:option>
					  <html:option value="1">ASHA</html:option>
					</html:select>
				  </div>
			     </td>
			       <td width="25%" class="tdfonthead">
			         <td width="25%" class="tdfont">
			     </tr>
			     <tr>
			     <td width="25%" class="tdfonthead">
			       <div align="right">
			        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					  <bean:message key="healthWorkerName" /> 
				    </font>	
				   </div>
			     </td>
			     <td width="25%" class="tdfont">
				  <div align="left">
				    &nbsp;<html:text name="JSYRegitrationFB" property="healthWorkerName"  maxlength="50" style="width:160;" readonly="<%=readOnly %>" onkeypress="return validateAlphabetsWithDotsOnly(event);">
				    </html:text>
				
				  </div>
			     </td>
			     <td width="25%" class="tdfonthead">
			       <div align="right">
			       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 <bean:message key="healthWorkerCardNo" /> 
				    </font>	
				   </div>
			      </td>
			     <td width="25%" class="tdfont">
				  <div align="left">
					&nbsp;<html:text name="JSYRegitrationFB" property="healthWorkerCardNo"  maxlength="50" readonly="<%=readOnly %>" style="width:160;" onkeypress="return validateAlphabetsWithDotsOnly(event);">
				    </html:text>
				  </div>
			     </td>
			     </tr>
			     <tr>
			     <td width="25%" class="tdfonthead">
			       <div align="right">
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					 <bean:message key="healthWorkerAdd" /> 
				    </font>	
				   </div>
			      </td>
			     <td width="25%" class="tdfont" colspan="2">
				   <div align="left">
					&nbsp;<b><html:textarea name="JSYRegitrationFB" property="healthWorkerAddress" style="width:400" readonly="<%=readOnly %>" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))">
				    </html:textarea></b>
				   </div>
			     </td>
			      <td width="25%" class="tdfont"></td>
			   </tr>
			   </table>
			   </his:ContentTag>
			 </div>
			<his:ContentTag>
			  <table width="100%" cellpadding="0" cellspacing="1">
		       <tr>
			    <td width="25%" class="tdfonthead">
	             <div id="showMessage" align="left">
			 
			     </div>
			   </td>
			 </tr>
			</table>
		  </his:ContentTag>
		
   <his:ButtonToolBarTag>
		<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex='2' onclick="submitButtonOnValidation('SAVE')" onkeypress="if(event.keyCode==13)submitButtonOnValidation('SAVE')")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'style="cursor: pointer" tabindex="1" onclick="submitButton('NEW')" onkeypress="if(event.keyCode==13) submitButton('NEW')">
   </his:ButtonToolBarTag>
		
  </his:statusList>
  
  
  
  <html:hidden name="JSYRegitrationFB" property="patCrNo"/>
  <html:hidden name="JSYRegitrationFB" property="hmode"/>
  <html:hidden name="JSYRegitrationFB" property="patCaste"/>
  <html:hidden name="JSYRegitrationFB" property="liveBirthCount"/>
  <html:hidden name="JSYRegitrationFB" property="patAge"/>
  <html:hidden name="JSYRegitrationFB" property="patPrimaryCategory"/>
  <html:hidden name="JSYRegitrationFB" property="isCasteBound"/> 
  <html:hidden name="JSYRegitrationFB" property="patMinAgeJsyRule"/>
  <html:hidden name="JSYRegitrationFB" property="patMaxAgeJsyRule"/>
  <html:hidden name="JSYRegitrationFB" property="patPrimaryCatJsyRule"/>
  <html:hidden name="JSYRegitrationFB" property="liveBirthJsyRule"/>
  <html:hidden name="JSYRegitrationFB" property="castIdjsyRule"/>
  
  <html:hidden name="JSYRegitrationFB" property="episodeCode"/>
  <html:hidden name="JSYRegitrationFB" property="visitNo"/>
  <html:hidden name="JSYRegitrationFB" property="areaType"/>
  
  <html:hidden name="JSYRegitrationFB" property="jsyCategoryCode"/>
  <html:hidden name="JSYRegitrationFB" property="gestationPeriod"/>
  <html:hidden name="JSYRegitrationFB" property="gestationPeriodDays"/>
  <html:hidden name="JSYRegitrationFB" property="registrationUpto"/>
  <html:hidden name="JSYRegitrationFB" property="primaryCategoryName"/>
  <html:hidden name="JSYRegitrationFB" property="casteNameJsyRule"/>
  <html:hidden name="JSYRegitrationFB" property="patSecondaryCatCode"/>
  <html:hidden name="JSYRegitrationFB" property="treatmentCatValidity"/>
  
  
</his:TransactionContainer>

<his:status/>
</html:form>
  