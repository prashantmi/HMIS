<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="new_investigation.masters.controller.fb.TestMstFB" %>
<%@page import="new_investigation.vo.TestNewMasterVO"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/testMstAddMod.js" />
<body onload="displayPrintingCombo();INFOCombo();machineCombo();">


<script type="text/javascript">
function isNumberKey(evt){ 
//var e = evt || window.event;
var charCode = (evt.which) ? evt.which : evt.keyCode
if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
    return false;
    return true;
}
	   
function ValidateAlpha(evt)
{
    var keyCode = (evt.which) ? evt.which : evt.keyCode
    if ((keyCode < 65 || keyCode > 90) && (keyCode < 97 || keyCode > 123) && keyCode != 32)
     
    return false;
        return true;
}
function notSpecCharr(obj,e) 
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==126 || charCode==33 || charCode==45 ||
		charCode==64 || charCode==35 || 
		charCode==36 || charCode==37 || 
		charCode==94 || charCode== 38 )
		return false;
	else
		return true;
}





  function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].sampleName,"testName") )
     {
         valid=true ;
     }
  return valid;
}
  function clearForm()
  {
   
     submitPage('CLEAR');
  
  }
  
  function displayPrintingCombo()
  {


	 var testname= document.getElementsByName("testName")[0].value;

              if(testname!="")
            	  document.getElementById("testName").value=testname;
	  
	  if(document.getElementsByName("resultEntryForm")[1].checked==true)
		  {
		  
		  document.getElementById("printingTemplate").style.display="";
		  
		  }
	  
	  if(document.getElementsByName("departmentResultEntryForm")[1].checked==true)
	  {
	  
	  document.getElementById("departmentPrintingTemplate").style.display="";
	  
	  }
  
	    
  }
  function machineCombo()
  {
	


	 var testname= document.getElementsByName("testName")[0].value;

              if(testname!="")
            	  document.getElementById("testName").value=testname;
	  
	  if(document.getElementsByName("issugartest")[1].checked==true)
		  {
		  //var tat =document.getElementsByName("turnaround_time")[0].value; 
		  //alert(tat)
		// document.getElementById("turnaround_time").value=  tat;  
		  document.getElementById("sugarTestCombo").style.display="";
		//  document.getElementById("sugartestcode").value= sugar1;
		  }
	  
	  if(document.getElementsByName("machinetest")[1].checked==true)
	  {
		 // var tat =document.getElementsByName("turnaround_time")[0].value; 
		  //alert(tat)
			 // document.getElementById("turnaround_time").value=  tat;  
	//  document.getElementById("machinetestcode").style.display="";
	  document.getElementById("machineTestCombo").style.display=""; 
	//  document.getElementById("machinetestcode").value= machine ;
	  }
 
	//  alert(document.getElementsByName("printingTemplateCode")[0].value);
	  if(document.getElementsByName("printingTemplateCode")!=null && document.getElementsByName("printingTemplateCode")[0]!=null && document.getElementsByName("printingTemplateCode")[0].value!="-1")
		  {
		//  document.getElementsByName("printingTemplateCode")[0].style.disabled="true";
		 document.getElementsByName("printingTemplateCode")[0].disabled = true;

		  }
	  
  }
  function INFOCombo()
  {

 
//alert(document.getElementsByName("fastingtype")[0].value)
	 var testname= document.getElementsByName("testName")[0].value;
	// alert(testname)
              if(testname!="")
            	  document.getElementById("testName").value=testname;
	  
            if(document.getElementsByName("anyinstruction")[1].checked==true)
          	{
            	if(document.getElementsByName("fastingtype")[0].value =="0" && document.getElementsByName("blddrTxt")[0].value =="")
            		{
            		document.getElementById("FType").style.display="none";
        			  document.getElementById("FTimeId").style.display="none";
        			  document.getElementById("bladderId").style.display="none";
        			  document.getElementsByName("instType")[0].value="0"
            		}
            	else{
          		if(document.getElementsByName("fastingtype")[0].value !=="0")
          		{
          			document.getElementById("FType").style.display="";
      			  document.getElementById("FTimeId").style.display="";
      			  document.getElementById("bladderId").style.display="none";
      			  document.getElementById("instDiv").style.display="";
      			 document.getElementsByName("instType")[0].value="1"
          		}
          		else if(document.getElementsByName("blddrTxt")[0].value !=="")
          		{
          			document.getElementById("FType").style.display="none";
      			  document.getElementById("FTimeId").style.display="none";
      			  document.getElementById("bladderId").style.display="";
      			  document.getElementById("instDiv").style.display="";
      			 document.getElementsByName("instType")[0].value="2"
    
          		
          		}
          		else{
          			document.getElementById("FType").style.display="none";
      			  document.getElementById("FTimeId").style.display="none";
      			  document.getElementById("bladderId").style.display="none";
      		       
          		}
          	}
          	} 
  }
  
  
  function hideCombo()
  {
	  
	  document.getElementById("printingTemplate").style.display="none";
	  
  }
  
  
  function getCombo()
  {
	  document.getElementsByName("printedWith")[1].checked=true;
	  document.getElementById("printingTemplate").style.display="";
	  
  }
  
  
  function normalForm()
  {
	  document.getElementsByName("resultEntryForm")[0].checked=true;
	  document.getElementById("printingTemplate").style.display="none";
	  
  }
  
  function departmentNormalForm()
  {
	  document.getElementsByName("departmentResultEntryForm")[0].checked=true;
	  document.getElementById("departmentPrintingTemplate").style.display="none";
	  
  }
  
  function departmentHideCombo()
  {
	  
	  document.getElementById("departmentPrintingTemplate").style.display="none";
	  
  }
  
  function sugarTestBoxFun(){
	  if(document.getElementsByName("machinetest")[0].checked)
	  {
	
	  document.getElementById("sugarTestCombo").style.display="";
	  }
	  else{
		  alert("Please select either Sugar Test or Machine Test..");
		  document.getElementsByName("issugartest")[0].checked=true;
	  }
	  }
  
  function machineTestBoxFun(){
	
	//  alert(  document.getElementsByName("issugartest")[0].checked);
	  if(document.getElementsByName("issugartest")[0].checked)
	  {
	  document.getElementById("machineTestCombo").style.display="";  
	  }
	  else{
		  alert("Please select either Sugar Test or Machine Test..");
		  document.getElementsByName("machinetest")[0].checked=true;
	  }
  }
  function sugarTestBoxFun2(){
	  document.getElementById("sugarTestCombo").style.display="none";
	  document.getElementsByName("sugartestcode")[0].value="";
	//  document.getElementById("sugartestcode").value="";
  }
  
  function machineTestBoxFun2(){
	  document.getElementById("machineTestCombo").style.display="none"; 
	 // document.getElementById("machinetestcode").value="";
	   document.getElementsByName("machinetestcode")[0].value="";
  }
  function departmentGetCombo()
  {
	  document.getElementsByName("departmentPrintedWith")[1].checked=true;
	  document.getElementById("departmentPrintingTemplate").style.display="";
	  
  }
  
  function showDiv()
  {

	  document.getElementById("instDiv").style.display="";
	  
  }
  function hideDiv()
  {
	  
	  document.getElementById("instDiv").style.display="none";

			  document.getElementById("FType").style.display="none";
			  document.getElementById("FTimeId").style.display="none";
			  document.getElementById("bladderId").style.display="none";
			  document.getElementsByName("fastingtype")[0].value ="0";
			  document.getElementsByName("fastingtime")[0].value ="0";   
			  document.getElementsByName("blddrTxt")[0].value ="";
			  document.getElementsByName("instType")[0].value="0";
  }
  function showCombo()
  {
	  var comboval = document.getElementsByName("instType")[0].value;
	  //alert(comboval);
	  if(comboval=='1')
	        {
		  
		//  alert("aa"+comboval);
		  document.getElementById("FType").style.display="";
		  document.getElementById("FTimeId").style.display="";
		  document.getElementById("bladderId").style.display="none";
		  document.getElementsByName("blddrTxt")[0].value ="";
	        }
	  else if(comboval=='2')
		  {
		  
		//  alert("cc"+comboval);
		  document.getElementById("bladderId").style.display="";
		  document.getElementById("FType").style.display="none";
		  document.getElementById("FTimeId").style.display="none";
		  document.getElementsByName("fastingtype")[0].value ="0";
		  document.getElementsByName("fastingtime")[0].value ="0";
		  }
	  else{
		  document.getElementById("bladderId").style.display="none";
		  document.getElementById("FType").style.display="none";
		  document.getElementById("FTimeId").style.display="none";
		  document.getElementsByName("fastingtype")[0].value ="0";
		  document.getElementsByName("fastingtime")[0].value ="0";   
		  document.getElementsByName("blddrTxt")[0].value ="";
	  }
	  
  }
  
  
</script>




<html:form action="/masters/TestMstACTION">

	
	<%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	
	<logic:equal name="TestMstFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
	
  
	<his:TransactionContainer>
		<his:TitleTag name="Test Master">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			  <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="TestName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
					 <%-- <html:text name="TestMstFB" property="testName"  maxlength="200" size="30" readonly="<%=this.readOnly %>" onkeypress="return notSpecCharr(this,event)">
					 </html:text> --%>
					               <input  type="text"  id="testName"  style="width:62%" maxlength="60" size="30" 
					                onkeypress="return notSpecCharr(this,event)" onpaste="return false;" onCopy="return false" 
					               tabindex="1" />
					              
							</div>
			
			     </tr>
			     <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="TestShortname"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
					 <html:text name="TestMstFB" property="testSName"  maxlength="20" size="30" readonly="<%=this.readOnly %>" onkeypress="" tabindex="1">
					 </html:text>
				  </div>
			     </td>
			     </tr>
			     
			     <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="TestDescription"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
					 <html:text name="TestMstFB" property="testDescription"  maxlength="100" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
					 </html:text>
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
								<bean:message key="TestType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="TestMstFB"  tabindex="1" property="testType" value="S"  ></html:radio>
						<bean:message key="SampleBases"/>
						<html:radio name="TestMstFB" tabindex="1" property="testType" value="P" ></html:radio>
						<bean:message key="PatientBased"/>
					    <html:radio name="TestMstFB" tabindex="1" property="testType" value="I" ></html:radio>
						<bean:message key="Slidebased"/> 
					    
				  </div>
			     </td>
			     </tr>
			     <tr>
			    
			      <td width="50%" class="tdfonthead">
			        <div align="right">
			             
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LOINCTiming"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <logic:present name="<%=InvestigationConfig.LIST_TEST_COMBO %>">
			      <div align="left">
					 
				 <html:select name="TestMstFB" style="width:58%" property="loincTiming"  tabindex="1"   >
				       					<html:option value="-1">Select Value</html:option>
				 	   					<html:options collection="<%=InvestigationConfig.LIST_TEST_COMBO %>" property="value" labelProperty="label"/>
				      				</html:select>
				  </div>
				  </logic:present>
			     </td>
			     </tr>
			     
			     
			      <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Requistion Form Type&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      
			      <div align="left">
					 
				<html:select name="TestMstFB" property="reqMasterFormType"
											>
											<html:option value="0" >Normal Form</html:option>
											<html:option value="1">Master Form</html:option>
											<html:option value="2" >Derived Form</html:option>
										</html:select>
										
									
  
		
				  </div>
				  
			     </td>
			     </tr>
			     <tr>
						<td width="30%" class="tdfonthead">
						<div align="right">
						<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Is Hide From Requisition Raising&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="TestMstFB" tabindex="1" property="ishidefromrequisitionraising"
									value="1" ></html:radio>No

							<html:radio name="TestMstFB" tabindex="1" property="ishidefromrequisitionraising"
									value="0" ></html:radio>Yes

							</div>
						</td>
					</tr>
					<tr>
						<td width="30%" class="tdfonthead">
						<div align="right">
						<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Is Sugar test&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">

							<html:radio name="TestMstFB" tabindex="1" property="issugartest" onclick="sugarTestBoxFun2();"
									value="0" ></html:radio>No
								<html:radio name="TestMstFB" tabindex="1" property="issugartest"  onclick="sugarTestBoxFun();" value="1">
			</html:radio>yes
								

							</div>
						</td>
					</tr>
					
		
					<tr id="sugarTestCombo" style="display:none;">
						<td width='50%' class="tdfonthead">
						<div align="right">
						<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> 
						<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Sugar Test Code&nbsp;
								</font>
							</div>
						</td>
						<td width='50%' class="tdfont">
							<div align="left">
							 <html:text name="TestMstFB" property="sugartestcode"  style="width:62%" maxlength="5" size="30"  tabindex="1">
					 </html:text>
					 	<html:hidden name="TestMstFB" property="sugartestcode" />
								<!-- <input  type="text"  id="sugartestcode"  style="width:62%" maxlength="5" size="30" onkeypress="return ValidateAlpha(event);"
					                onpaste="return false;" onCopy="return false" 
					               tabindex="1" />
					           <input type="hidden" name="sugartestcode"  >  -->
							</div>
						</td>
					</tr>
				
					<tr>
						<td width='30%' class="tdfonthead">
						<div align="right">
						<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Is Machine test&nbsp;
								</font>
							</div>
						</td>
						<td width='70%' class="tdfont">
							<div align="left">


							
								<html:radio name="TestMstFB" tabindex="1" property="machinetest" onclick="machineTestBoxFun2();"
									value="0"> </html:radio>No
								<html:radio name="TestMstFB" tabindex="1" property="machinetest"
									value="1" onclick="machineTestBoxFun();">
								</html:radio>
								Yes
							
							</div>
						</td>
					</tr>
					
					
					
						<tr id="machineTestCombo" style="display:none;" >
						<td width='50%' class="tdfonthead">
						<div align="right">
						<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> 
						<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Machine Test Code&nbsp;
								</font>
							</div>
						</td>
						<td width='50%' class="tdfont">
							<div align="left">
							<html:text name="TestMstFB" property="machinetestcode"  style="width:62%" maxlength="5" size="30"  tabindex="1">
					 </html:text>
					 	<html:hidden name="TestMstFB" property="machinetestcode" />
								<!-- <input  type="text"  id="machinetestcode"  style="width:62%" maxlength="5" size="30" 
					                onpaste="return false;" onCopy="return false" onkeypress="return ValidateAlpha(event);"
					               tabindex="1" />
					           <input type="hidden" name="machinetestcode"  >    --> 
							</div>
						</td>
					</tr>
			
					<tr>
						<td width="30%" class="tdfonthead">
						<div align="right">
					<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> 
						<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">TAT(From Sample Acceptance to Report printing)&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
							<html:text name="TestMstFB" property="turnaround_time"  style="width:62%" maxlength="10" size="30" onkeypress="return isNumberKey(event);" tabindex="1">
					 </html:text>
					 	<html:hidden name="TestMstFB" property="turnaround_time" />
							 <!--<input  type="text"  id="turnaround_time"  style="width:62%" maxlength="10" size="30" 
					                 onpaste="return false;" onCopy="return false"  onkeypress="return isNumberKey(event)"
					               tabindex="1" />
					            <input type="hidden" name="turnaround_time"  >    -->
							</div>
						</td>
					</tr>
					 <tr>
						<td width="30%" class="tdfonthead">
						<div align="right">
						<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Is Confidential Test&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio  name="TestMstFB" tabindex="1" property="isconfidential"
									value="0" ></html:radio>

								No
							
								<html:radio  name="TestMstFB" tabindex="1" property="isconfidential"
									value="1"></html:radio>
								Yes
							

							</div>
						</td>
					</tr>
					 <tr>
						<td width="30%" class="tdfonthead">
						<div align="right">
						<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Is Any Extra Test Charges Required&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="TestMstFB"  tabindex="1" property="isextracharge"
									value="0"></html:radio>

							No
								<html:radio name="TestMstFB" tabindex="1" property="isextracharge" 
									value="1"></html:radio>

								yes
							
							</div>
						</td>
					</tr>
					
		
					
			      <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Is Report Print On Seperate Page Required&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="TestMstFB" tabindex="1" property="isreportrequiredonseperatepage"
									value="1"></html:radio>

								Yes
								<html:radio name="TestMstFB" tabindex="1" property="isreportrequiredonseperatepage"
									value="0"></html:radio>

								No


							</div>
						</td>
					</tr>
					 <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Is Uploaded File Print Required&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="TestMstFB" tabindex="1" property="isreportupload"
									value="1"></html:radio>

								Yes
								<html:radio name="TestMstFB" tabindex="1" property="isreportupload"
									value="0"></html:radio>

								No


							</div>
						</td>
					</tr>
					 <tr>
						<td width='50%' class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Is Report First Page Print Required&nbsp;
								</font>
							</div>
						</td>
						<td width='50%' class="tdfont">
							<div align="left">


								<html:radio name="TestMstFB" tabindex="1" property="isfrontpage"
									value="1"></html:radio>

								NO
								<html:radio name="TestMstFB" tabindex="1" property="isfrontpage"
									value="0"></html:radio>

								Yes


							</div>
						</td>
					</tr>
			     
			     
			     
			     
			     
			     
			     <tr>
			     <td width="50%" class="tdfonthead">
			     	<div align="right">
			     	
			     		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			     				<bean:message key="PrintedWith"/>&nbsp;
			     		</font>
			     	</div>
			     </td>
			     <td width="50%" class="tdfont">
			  		<div align="left">
			  		
			  		<html:radio name="TestMstFB" tabindex="1" property="printedWith" value="0" onclick="normalForm();"></html:radio>
			  		<bean:message key="NormalTemplate"/>&nbsp;
			  		<html:radio name="TestMstFB" tabindex="1" property="printedWith" value="1"></html:radio>
			  		<bean:message key="DynamicTemplate"/>&nbsp;
			  		
			  		</div>
			  	</td>
			  	</tr>
			  	
			  	<tr>
			  	<td width="50%" class="tdfonthead">
			  		<div align="right">
			  			
			  			<font color="#00000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  					<bean:message key="ResultEntryForm"/>&nbsp;
			  			</font>
			  		</div>
			  	</td>
			  	<td width="50%" class="tdfont">
			  		<div align="left">
			  		
			  		<html:radio name="TestMstFB" tabindex="1" property="resultEntryForm" value="0" onclick="hideCombo();"></html:radio>
			  		<bean:message key="NormalTemplate"/>&nbsp;
			  		<html:radio name="TestMstFB" tabindex="1" property="resultEntryForm" value="1" onclick="getCombo();"></html:radio>
			  		<bean:message key="DynamicTemplate"/>&nbsp;
			  		</div>
			  	</td>
			  	</tr>
			     
			    
			    
			    <!-- printing template combo -->   
			    
			    	<tr id="printingTemplate"  style="display:none">
						
						<td width='50%' class="tdfonthead">
							<div align="right">
								  <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="PrintingTemplateName" />&nbsp;
								</font>
							</div>
						</td>

						
						<td width='50%' class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_TEST_PRINTING_TEMPLATE %>">
								<div align="left">
									
									<html:select name="TestMstFB"
										property="printingTemplateCode" tabindex="1" style="width:58%">

										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_TEST_PRINTING_TEMPLATE %>"
											property="value" labelProperty="label" />
									</html:select>
				
								</div>
							</logic:present>
							<logic:notPresent name="<%=InvestigationConfig.LIST_TEST_PRINTING_TEMPLATE %>">
							<html:select name="TestMstFB"
										property="printingTemplateCode" tabindex="1" style="width:58%">

										<html:option value="-1">Select Value</html:option>
										
										</html:select>
									</logic:notPresent>
						</td>
					</tr>
					
					
					<!-- For Department Specific Result Entry Specifications
					Puneet -->
					
					
						     <tr>
			     <td width="50%" class="tdfonthead">
			     	<div align="right">
			     	
			     		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			     				<bean:message key="deptPrintedWith"/>&nbsp;
			     		</font>
			     	</div>
			     </td>
			     <td width="50%" class="tdfont">
			  		<div align="left">
			  		
			  		<html:radio name="TestMstFB" tabindex="1" property="departmentPrintedWith" value="0" onclick="departmentNormalForm();"></html:radio>
			  		<bean:message key="deptNormalTemplate"/>&nbsp;
			  		<html:radio name="TestMstFB" tabindex="1" property="departmentPrintedWith" value="1"></html:radio>
			  		<bean:message key="deptTemplate"/>&nbsp;
			  		
			  		</div>
			  	</td>
			  	</tr>
			  	
			  	<tr>
			  	<td width="50%" class="tdfonthead">
			  		<div align="right">
			  			
			  			<font color="#00000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  					<bean:message key="deptResultEntryForm"/>&nbsp;
			  			</font>
			  		</div>
			  	</td>
			  	<td width="50%" class="tdfont">
			  		<div align="left">
			  		
			  		<html:radio name="TestMstFB" tabindex="1" property="departmentResultEntryForm" value="0" onclick="departmentHideCombo();"></html:radio>
			  		<bean:message key="deptNormalTemplate"/>&nbsp;
			  		<html:radio name="TestMstFB" tabindex="1" property="departmentResultEntryForm" value="1" onclick="departmentGetCombo();"></html:radio>
			  		<bean:message key="deptTemplate"/>&nbsp;
			  		</div>
			  	</td>
			  	</tr>
			     
			    
			    
			    <!-- printing template combo -->   
			    
			    	<tr id="departmentPrintingTemplate"  style="display:none">
						
						<td width='50%' class="tdfonthead">
							<div align="right">
								  <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="PrintingTemplateName" />&nbsp;
								</font>
							</div>
						</td>

						
						<td width='50%' class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_TEST_PRINTING_TEMPLATE %>">
								<div align="left">
									
									<html:select name="TestMstFB"
										property="departmentPrintingTemplateCode" tabindex="1" style="width:58%">

										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_TEST_PRINTING_TEMPLATE %>"
											property="value" labelProperty="label" />
									</html:select>
				
								</div>
							</logic:present>
							<logic:notPresent name="<%=InvestigationConfig.LIST_TEST_PRINTING_TEMPLATE %>">
							<html:select name="TestMstFB"
										property="departmentPrintingTemplateCode" tabindex="1" style="width:58%">

										<html:option value="-1">Select Value</html:option>
										
										</html:select>
									</logic:notPresent>
						</td>
					</tr>
					
				 <tr>
						<td width="50%" class="tdfonthead">
						<div align="right">
						<font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Any Instruction Required&nbsp;
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">


								<html:radio name="TestMstFB" tabindex="1" property="anyinstruction" onclick="hideDiv();"
									value="0"></html:radio>
								No
								<html:radio name="TestMstFB" tabindex="1" property="anyinstruction" onclick="showDiv();"
									value="1"></html:radio>

								Yes


							</div>
						</td>
					</tr>
			
				 <tr id="instDiv" style="display:none;">
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Select Instruction&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      
			      <div align="left">
					 
				<html:select name="TestMstFB" property="instType" onclick="showCombo();"
											>
											<html:option value="0" >Select Value</html:option>
											<html:option value="1" >Fasting</html:option>
											<html:option value="2">Full Bladder</html:option>
											
										</html:select>
										
									
  
		
				  </div>
				  
			     </td>
			     </tr>
			   
			     <html:hidden name="TestMstFB" property="instType" />
			     	
			     	 <tr id="FTimeId" style="display:none;">
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							Fasting Time&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      
			   
				<logic:present name="<%=InvestigationConfig.LIST_FASTING_TIME%>">
								<div align="left">
									
									<html:select name="TestMstFB"
										property="fastingtime" tabindex="1" style="width:100%">

										<html:option value="0">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_FASTING_TIME%>"
											property="value" labelProperty="label" />
									</html:select>
				
								</div>
					</logic:present>
							<logic:notPresent name="<%=InvestigationConfig.LIST_FASTING_TIME%>">
							<html:select name="TestMstFB"
										property="fastingtime" tabindex="1" style="width:100%">

										<html:option value="0">Select Value</html:option>
										
										</html:select>
	
				  </logic:notPresent>
			     </td>
			     </tr>
			       <html:hidden name="TestMstFB" property="fastingtime" />
			     <tr id="FType" style="display:none;">
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							Fasting Type&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      
			    
			<logic:present name="<%=InvestigationConfig.LIST_FASTING_TYPE%>">
								<div align="left">
									
									<html:select name="TestMstFB"
										property="fastingtype" tabindex="1" style="width:100%">

										<html:option value="0">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_FASTING_TYPE%>"
											property="value" labelProperty="label" />
									</html:select>
				
								</div>
							</logic:present>
							<logic:notPresent name="<%=InvestigationConfig.LIST_FASTING_TYPE%>">
							<html:select name="TestMstFB"
										property="fastingtype" tabindex="1" style="width:100%">

										<html:option value="0">Select Value</html:option>
										
										</html:select>
	
				  </logic:notPresent>
			     </td>
			     </tr>
			     <html:hidden name="TestMstFB" property="fastingtype" />
			     <tr id="bladderId" style="display:none;">
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							Full Bladder Instruction&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      
			      <div align="left">
			      <html:text name="TestMstFB" property="blddrTxt"  style="width:62%" maxlength="1000" size="30" onkeypress="return ValidateAlpha(event);" tabindex="1">
					 </html:text>
					 	<html:hidden name="TestMstFB" property="blddrTxt" />
							<!-- <input  type="text"  id="blddrTxt"  style="width:62%" maxlength="1000" size="30" 
					                onkeypress="return ValidateAlpha(event);" onpaste="return false;" onCopy="return false" 
					               tabindex="1" />
					    <input type="hidden" name ="blddrTxt">     -->
							</div>
						</td>
		
				 
			     </tr>
			     	
						
					
					
		     <tr id="" >
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      
			      <div align="left">
							</div>
						</td>
		
				 
			     </tr>
			     	
						
					
					
		
			      	 
		 
			     </table>
			      </his:ContentTag>
			      
			      <his:ButtonToolBarTag>
				<span id="saveDiv">
			    <logic:notEqual name="TestMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="TestMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE');hideCombo();departmentHideCombo();" onclick="finalSubmit('SAVE');hideCombo();departmentHideCombo();" tabindex="1">
				  <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearAddForm()" onkeypress="if(event.keyCode==13) clearAddForm()" tabindex="1">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="TestMstFB" property="hmode" value="MODIFY"> 
				   <img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
				   <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				</logic:equal> 
	               <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		</his:ButtonToolBarTag>
		<his:status/>
	</his:TransactionContainer>
	
	
	
	<html:hidden name="TestMstFB" property="hmode" />
	<html:hidden name="TestMstFB" property="testCode" />

	<html:hidden name="TestMstFB" property="machinetest" />

	<html:hidden name="TestMstFB" property="isconfidential" />
	<html:hidden name="TestMstFB" property="selectedChk" />
	<html:hidden name="TestMstFB" property="testName" />
	<html:hidden name="TestMstFB" property="anyinstruction" />
	<html:hidden name="TestMstFB" property="isextracharge" />
	<html:hidden name="TestMstFB" property="ishidefromrequisitionraising" />
	<html:hidden name="TestMstFB" property="issugartest" />
	
		</html:form>
	</body>
</html>