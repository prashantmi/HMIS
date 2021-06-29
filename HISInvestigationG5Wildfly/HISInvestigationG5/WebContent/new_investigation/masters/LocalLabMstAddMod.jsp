<!-- 
 /****************************************Start of program*****************************************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	LOCAL LAB MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:	
 ## Purpose								:	This master is used to capture Local  Labs used for investigation Process.  
 ## Date of Creation					: 	27-Mar-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/**************************************************************************************************************/ 

-->


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/LocalLabMstAddMod.js" />
<his:javascript src="/new_investigation/js/labRequisition.js" />
<his:javascript src="/new_investigation/js/jquery-3.3.1.min.js" />



<body onload="showTime(document.getElementsByName('isTimeBound')[0]);checktypevalue();">


	<script type="text/javascript">
	
 

    window.onload = function()
    {
      //alert("hello");
      {
    	   var d = document.getElementsByName("combovalue")[0].value; 
    	  //alert(d); 
    	  var mode = document.getElementsByName("hmode")[0].value;
    	  //alert(mode);
    	  if(d==1 && mode=="MODIFY")
        	{
          	  //alert("userWise");
    		  document.getElementsByName("combovalue")[0].value=d;
          }
    	  else{}
      }
      {
      var a = document.getElementsByName("entryuser")[0].value;
      //alert(a);
      if(a==1)
          {
             //alert("true");
             document.getElementsByName("entryuser1")[0].checked = true;

          }
      else{
    	 /*  alert("false");
    	  document.getElementsByName("entryuser1")[0].checked = false;  */
          }
      }
      {
   var b = document.getElementsByName("validation")[0].value;
      //alert(b);
      if(b==1)
          {
            // alert("true");
             document.getElementsByName("validation1")[0].checked = true;

          }
      else{
    	  /* alert("false");
    	  document.getElementsByName("validation1")[0].checked = false; */
          }
      }
      {
      var c = document.getElementsByName("revalidation")[0].value;
      //alert(c);
      if(c==1)
          {
             //alert("true");
             document.getElementsByName("revalidation1")[0].checked = true;

          }
      else{
    	   /* alert("false");
    	  document.getElementsByName("revalidation1")[0].checked = false; */
          }
      }

        }
	/* function printChecked(){
		var items=document.getElementsByName('acs');
		var selectedItems="";
		for(var i=0; i<items.length; i++){
			if(items[i].type=='checkbox' && items[i].checked==true)
				selectedItems+=items[i].value+"\n";
		}
		alert(selectedItems);
	}	 */

	
	function onchangeChk(){
		//alert("onchange called");
		//alert(document.getElementsByName("entryuser1")[0].checked);
		//var chk = document.getElementsByName("entryuser1")[0];
		//if(chk.checked == true)
		//{	
		if(document.getElementsByName("entryuser1")[0].checked == true){
			//alert("true1");
	    	document.getElementsByName("entryuser")[0].value="1";
	        //alert(document.getElementsByName("entryuser")[0].value);
	    }
		else{
			//alert("false");
			document.getElementsByName("entryuser")[0].value="0";
		}
	 }

	  
	function onchangeChk1(){
		if(document.getElementsByName("validation1")[0].checked == true){
	    	document.getElementsByName("validation")[0].value="1";
	        //alert(document.getElementsByName("validation")[0].value);
	    }
		else{
			document.getElementsByName("validation")[0].value="0";
		}
	 }
	 function onchangeChk2(){
			if(document.getElementsByName("revalidation1")[0].checked == true){
		    	document.getElementsByName("revalidation")[0].value="1";
		        //alert(document.getElementsByName("revalidation")[0].value);
		    }
			else{
				document.getElementsByName("revalidation")[0].value="0";
			}
		 }	
	 function onchangeChk3(){
			if(document.getElementsByName("combovalue")[0].selectedIndex == true){
		    	document.getElementsByName("accesstoaddendum")[0].value="1";
		        //alert(document.getElementsByName("accesstoaddendum")[0].value);
		    }
			else{
				document.getElementsByName("accesstoaddendum")[0].value="0";
			}
		 }	
	
				
		

        
	/* function printChecked(){
		var items=document.getElementsByName('acs');
		var selectedItems="";
		for(var i=0; i<items.length; i++){
			if(items[i].type=='checkbox' && items[i].checked==true)
				selectedItems+=items[i].value+"\n";
		}
		alert(selectedItems);
	}	 */

	
	function onchangeChk(){
		//alert("onchange called");
		//alert(document.getElementsByName("entryuser1")[0].checked);
		//var chk = document.getElementsByName("entryuser1")[0];
		//if(chk.checked == true)
		//{	
		if(document.getElementsByName("entryuser1")[0].checked == true){
			//alert("true1");
	    	document.getElementsByName("entryuser")[0].value="1";
	        //alert(document.getElementsByName("entryuser")[0].value);
	    }
		else{
			//alert("false");
			document.getElementsByName("entryuser")[0].value="0";
		}
	 } 
	function onchangeChk1(){
		if(document.getElementsByName("validation1")[0].checked == true){
	    	document.getElementsByName("validation")[0].value="1";
	        //alert(document.getElementsByName("validation")[0].value);
	    }
		else{
			document.getElementsByName("validation")[0].value="0";
		}
	 }
	 function onchangeChk2(){
			if(document.getElementsByName("revalidation1")[0].checked == true){
		    	document.getElementsByName("revalidation")[0].value="1";
		        //alert(document.getElementsByName("revalidation")[0].value);
		    }
			else{
				document.getElementsByName("revalidation")[0].value="0";
			}
		 }	
	 function onchangeChk3(){
			if(document.getElementsByName("combovalue")[0].selectedIndex == true){
		    	document.getElementsByName("accesstoaddendum")[0].value="1";
		        //alert(document.getElementsByName("accesstoaddendum")[0].value);
		    }
			else{
				document.getElementsByName("accesstoaddendum")[0].value="0";
			}
		 }	
	
				
		


	/* function printChecked(){
		var items=document.getElementsByName('acs');
		var selectedItems="";
		for(var i=0; i<items.length; i++){
			if(items[i].type=='checkbox' && items[i].checked==true)
				selectedItems+=items[i].value+"\n";
		}
		alert(selectedItems);
	}	 */

	
	function onchangeChk(){
		//alert("onchange called");
		//alert(document.getElementsByName("entryuser1")[0].checked);
		//var chk = document.getElementsByName("entryuser1")[0];
		//if(chk.checked == true)
		//{	
		if(document.getElementsByName("entryuser1")[0].checked == true){
			//alert("true1");
	    	document.getElementsByName("entryuser")[0].value="1";
	        //alert(document.getElementsByName("entryuser")[0].value);
	    }
		else{
			//alert("false");
			document.getElementsByName("entryuser")[0].value="0";
		}
	 } 
	function onchangeChk1(){
		if(document.getElementsByName("validation1")[0].checked == true){
	    	document.getElementsByName("validation")[0].value="1";
	        //alert(document.getElementsByName("validation")[0].value);
	    }
		else{
			document.getElementsByName("validation")[0].value="0";
		}
	 }
	 function onchangeChk2(){
			if(document.getElementsByName("revalidation1")[0].checked == true){
		    	document.getElementsByName("revalidation")[0].value="1";
		        //alert(document.getElementsByName("revalidation")[0].value);
		    }
			else{
				document.getElementsByName("revalidation")[0].value="0";
			}
		 }	
	 function onchangeChk3(){
			if(document.getElementsByName("combovalue")[0].selectedIndex == true){
		    	document.getElementsByName("accesstoaddendum")[0].value="1";
		        //alert(document.getElementsByName("accesstoaddendum")[0].value);
		    }
			else{
				document.getElementsByName("accesstoaddendum")[0].value="0";
			}
		 }	
	
				
		


function populatePage()
{
	
	var code = document.getElementsByName("labCode")[0].value;

	if (code != -1) {
		 
		submitPage('POPULATE');
	} else {
		submitPage('ADD');
	}	
	
	
	
}
		
  function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].LaboratoryName,"laboratoryName") )
     {
         valid=true ;
     }
  return valid;
}
  
  
  
  
  function setDay(obj)
  {
  
   // var workingDays="0000000";
	  var workingDays= document.getElementsByName('labWorkingDays')[0].value;
 	if(obj.checked)
 		{
 		
 		if(obj.name=="chkMon")
		{
 			//alert("check Mon");	
 			
		workingDays='1'+workingDays.substring(1, 7); //setting first value in the sting as 1
		//alert(workingDays);
		}
 		else  if(obj.name=="chkTue")
		
 		{
 			//alert("check Tue");
		workingDays=workingDays.substring(0, 1)+'1'+workingDays.substring(2, 7);
		//	alert(workingDays);
		 
 		}
 		else if(obj.name=="chkWed")
		
 		{
 			//alert("check Wed");
 			
		workingDays=workingDays.substring(0, 2)+'1'+workingDays.substring(3, 7);
		//alert(workingDays);
		 
		}
		
		else if(obj.name=="chkThu")
		
 		{
			//alert("check Thu");
		workingDays=workingDays.substring(0, 3)+'1'+workingDays.substring(4, 7);
		//	alert(workingDays);
		 
		}
		else if(obj.name=="chkFri")
		
 		{
			//alert("check Fri");
		workingDays=workingDays.substring(0, 4)+'1'+workingDays.substring(5, 7);
		//	alert(workingDays);
		 
 		}
		else if(obj.name=="chkSat")
		
 		{
			//alert("check Sat");
		workingDays=workingDays.substring(0, 5)+'1'+workingDays.substring(6, 7);
		//alert(workingDays);
		 
 		}
		else if(obj.name=="chkSun")
		
 		{
			//alert("check Sun");
		workingDays=workingDays.substring(0, 6)+'1';
		//	alert(workingDays);
		 
 		}
		
 		 
 		}
	else
	{
 		if(obj.name=="chkMon")
		{
 			//alert("uncheck Mon");
		workingDays='0'+workingDays.substring(1, 7);
		//	alert(workingDays);
		 
		}
		  if(obj.name=="chkTue")
		
 		{
			  //alert("uncheck Tue");
		workingDays=workingDays.substring(0, 1)+'0'+workingDays.substring(2, 7);
		//	alert(workingDays);
		 
		}
 		else if(obj.name=="chkWed")
		
 		{
 			//alert("uncheck Wed");
		workingDays=workingDays.substring(0, 2)+'0'+workingDays.substring(3, 7);
		//alert(workingDays);
 		}
		
		else if(obj.name=="chkThu")
		
 		{
			//alert("uncheck Thu");
		workingDays=workingDays.substring(0, 3)+'0'+workingDays.substring(4, 7);
		//alert(workingDays);
 		}
		else if(obj.name=="chkFri")
		
 		{
			//alert("uncheck Fri");
		workingDays=workingDays.substring(0, 4)+'0'+workingDays.substring(5, 7);
		//alert(workingDays);
 		}
		else if(obj.name=="chkSat")
		
 		{
			//alert("uncheck Sat");
		workingDays=workingDays.substring(0, 5)+'0'+workingDays.substring(6, 7);
		//alert(workingDays);
 		}
		else if(obj.name=="chkSun")
		
 		{
			//alert("uncheck Sun");
		workingDays=workingDays.substring(0, 6)+'0';
		//alert(workingDays);
 		}
		
 		}
		
 	document.getElementsByName('labWorkingDays')[0].value=workingDays;
 	//alert("final value is");
 	//alert(document.getElementsByName('labWorkingDays')[0].value); 
 	//alert(workingDays);
 		}
  
  
  function clearForm()
  {
   
     submitPage('CLEAR');
  
  }


  /* Added by harshita  */
  function checktypevalue(){
	
	var displaydiv=document.getElementById('ulist');
	var displaydivcol=document.getElementById('ucol');
	
	if(document.getElementsByName("accesstoaddendum")[1].checked==true)	
	{
		displaydiv.style.display="";
		displaydivcol.style.display="";                     
	}
	
	
}
   var display=function(){
	var displaydiv=document.getElementById('ulist');
	
	if(displaydiv.style.display=='none'||displaydiv.style.display=='')
		displaydiv.style.display='block';
	
	var displaydivcol=document.getElementById('ucol');
	
	if(displaydivcol.style.display=='none'||displaydivcol.style.display=='')
		displaydivcol.style.display='block';
}
 
 
 
var hide=function(){
	var displaydiv=document.getElementById('ulist');
	
	if(displaydiv.style.display=='block'||displaydiv.style.display=='')
		displaydiv.style.display='none';
	
	
	var displaydivcol=document.getElementById('ucol');
	
	if(displaydivcol.style.display=='block'||displaydivcol.style.display=='')
		displaydivcol.style.display='none';
	
} 
</script>

	<html:form action="/masters/LocalLabMstACT">



		<html:hidden name="LabMstFB" property="hmode" />
		<html:hidden name="LabMstFB" property="hospitalCode" />
		<html:hidden name="LabMstFB" property="labWorkingDays" />
		<html:hidden name="LabMstFB" property="selectedChk" />
		<html:hidden name="LabMstFB" property="entryuser" />
		<html:hidden name="LabMstFB" property="validation" />
		<html:hidden name="LabMstFB" property="revalidation" />
		<html:hidden name="LabMstFB" property="accesstoaddendum" />
		<%!
		boolean readOnly;
	%>
		<% this.readOnly=false;%>

		<logic:equal name="LabMstFB" property="hmode" value="VIEW">
			<% this.readOnly=true; %>
		</logic:equal>


		<his:TransactionContainer>
			<his:TitleTag name="Local Laboratory Master">
				<%-- <his:insertDateTag /> --%>
			</his:TitleTag>
			<his:ContentTag>

				<table width="100%" border="0" cellspacing="1" cellpadding="0">

					<!-- GLOBAL LAB NAME COMBO -->
<logic:notEqual name="LabMstFB"
						property="hmode" value="MODIFY">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="GlobalLaboratoryName" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
								<div align="left">

									<html:select name="LabMstFB" property="labCode" tabindex="1"
										style="width:41%" onchange="populatePage()">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_LAB_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
						</tr></logic:notEqual>

	<%-- <!-- GLOBAL LAB NAME COMBO -->
							<logic:equal name="LabMstFB"
						property="hmode" value="MODIFY">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LaboratoryName" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
								<div align="left">

									<html:select name="LabMstFB" property="labCode" tabindex="1"
										style="width:41%" onchange="populatePage()" disabled="true">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_LAB_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
											<html:hidden name="LabMstFB" property="labCode" />
									</div>
							</logic:present></td>
</tr></logic:equal> --%>

<logic:equal name="LabMstFB"
						property="hmode" value="MODIFY"><html:hidden name="LabMstFB" property="labCode" /> </logic:equal>

						<!-- LOCAL LAB NAME  -->
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LaboratoryName" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="LabMstFB" property="laboratoryName"
									maxlength="60" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphaNumericOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					<!-- LAB SHORT NAME -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabShortShortName" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="LabMstFB" property="labShortSName"
									maxlength="25" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					<!-- DEPARTMENT COMBO -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="DepartmentCombo" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.DEPART_COMBO %>">
								<div align="left">

									<html:select name="LabMstFB" property="department" tabindex="1"
										style="width:41%">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.DEPART_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
					</tr>
		<!-- LOCATION COMBO -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="locationCombo" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_LOCATION_COMBO %>">
								<div align="left">

									<html:select name="LabMstFB" property="location" tabindex="1"
										style="width:41%">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_LOCATION_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
					</tr>
					
					<!-- LAB INCHARGE COMBO -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="labInchargeCombo" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont"><logic:present
								name="<%=InvestigationConfig.LIST_LABINCHARGE_COMBO %>">
								<div align="left">

									<html:select name="LabMstFB" property="labIncharge" tabindex="1"
										style="width:41%">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=InvestigationConfig.LIST_LABINCHARGE_COMBO %>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
							</logic:present></td>
					</tr>

					<!--SAMPLE NUMBER CONFIGURATION  -->


					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="SampleNumberConfiguration" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1"
									property="sampleNumberConfig" value="1"></html:radio>

								<bean:message key="LabSampleNumberCofig" />
								<html:radio name="LabMstFB" tabindex="1"
									property="sampleNumberConfig" value="2"></html:radio>

								<bean:message key="SamplecollectionAreaConfig" />

								<html:radio name="LabMstFB" tabindex="1"
									property="sampleNumberConfig" value="3"></html:radio>

								<bean:message key="Manual" />


							</div>
						</td>
					</tr>


					<!-- lab no configuration -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabNumberConfiguration" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1"
									property="labNumberConfig" value="1"></html:radio>

								<bean:message key="Manual" />
								<html:radio name="LabMstFB" tabindex="1"
									property="labNumberConfig" value="2"></html:radio>

								<bean:message key="Automatic" />




							</div>
						</td>
					</tr>

					<!--lab type  -->

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabType" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1" property="labType"
									value="1"></html:radio>

								<bean:message key="Routine" />
								<html:radio name="LabMstFB" tabindex="1" property="labType"
									value="2"></html:radio>

								<bean:message key="Emergency" />


							</div>
						</td>
					</tr>
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Is Test Modify&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1" property="istestmodify"
									value="1"></html:radio>

								Yes
								<html:radio name="LabMstFB" tabindex="1" property="istestmodify"
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
									face="Verdana, Arial, Helvetica, sans-serif">Report Lab Label&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1" property="isreportlablabelchanged"
									value="1"></html:radio>

								Study No.
								<html:radio name="LabMstFB" tabindex="1" property="isreportlablabelchanged"
									value="0"></html:radio>

								Coll./Study No.


							</div>
						</td>
					</tr>
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Report Collection Date Label&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1" property="isreportcollectionlabelchanged"
									value="1"></html:radio>

								Access No
								<html:radio name="LabMstFB" tabindex="1" property="isreportcollectionlabelchanged"
									value="0"></html:radio>

								Lab/Study No


							</div>
						</td>
					</tr>
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Is Report Sample Label Required&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1" property="isreportsamplelabelrequired"
									value="0"></html:radio>

								Yes
								<html:radio name="LabMstFB" tabindex="1" property="isreportsamplelabelrequired"
									value="1"></html:radio>

								No


							</div>
						</td>
					</tr>
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">  </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Show Result Entered In Report&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1" property="resultentered"
									value="1"></html:radio>

								Yes
								<html:radio name="LabMstFB" tabindex="1" property="resultentered"
									value="0"></html:radio>

								No


							</div>
						</td>
					</tr>
					  <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="displayHeader"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="LabMstFB"  tabindex="1" property="displayHeader" value="1"  ></html:radio>
						
						<bean:message key="yes"/>
						<html:radio name="LabMstFB" tabindex="1" property="displayHeader" value="0" ></html:radio>
						
						<bean:message key="no"/>
					    
					    
				  </div>
			     </td>
			     </tr>
			     
			     
			     
			       <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="finalRemarkReqd"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="70%" class="tdfont">
			      <div align="left">
					  
				       
						<html:radio name="LabMstFB"  tabindex="1" property="finalRemark" value="1"  ></html:radio>
						
						<bean:message key="yes"/>
						<html:radio name="LabMstFB" tabindex="1" property="finalRemark" value="0" ></html:radio>
						
						<bean:message key="no"/>
					    
					    
				  </div>
			     </td>
			     </tr>
			     <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Is Appointment Mandatory&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1" property="isaptmand"
									value="1"></html:radio>

								Yes
								<html:radio name="LabMstFB" tabindex="1" property="isaptmand"
									value="0"></html:radio>

								No


							</div>
						</td>
					</tr>
			     
			     
			     <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">  </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Is Appointment&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1" property="isAppointment"
									value="1"></html:radio>

								Yes
								<html:radio name="LabMstFB" tabindex="1" property="isAppointment"
									value="0"></html:radio>

								No


							</div>
						</td>
					</tr>
			     
			      <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">  </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Is Lab Film Based&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1" property="isfilmused"
									value="1"></html:radio>

								Yes
								<html:radio name="LabMstFB" tabindex="1" property="isfilmused"
									value="0"></html:radio>

								No


							</div>
						</td>
					</tr>
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">  </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Is lab Hide From Desk &nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1" property="hidefromdesk"
									value="1"></html:radio>

								Yes
								<html:radio name="LabMstFB" tabindex="1" property="hidefromdesk"
									value="0"></html:radio>

								No


							</div>
						</td>
					</tr>
					 <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">  </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Access to Addendum and Ammendent&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left" >
								 <%-- <html:radio name="LabMstFB" tabindex="1"  property="accesstoaddendum" onclick="hide();" 
									value="0"></html:radio>

								LabWise
								<html:radio name="LabMstFB" tabindex="1"  property="accesstoaddendum" onclick="display();"
									value="1"></html:radio>

								Userwise  --%>  
								  <select id="combo_new" name=combovalue onchange="onchangeChk3();">
  									<option value="1"  onclick="hide();">LabWise</option>
  									<option value="0" onclick="display();">UserWise</option>
  								</select>     
  								<!-- <input type="checkbox" id="combo_new" name="combovalue" value="0" onclick="hide();" onchange="onchangeChk3();" >Labwise
							      <input type="checkbox" id="combo_new" name="combovalue" value="0" onclick="display();" onchange="onchangeChk3();">userwise -->
                                    

							</div>
						</td>
					</tr>
					<!--userwise check  -->
					<tr>
						
						<td width="50%" class="tdfonthead">
							<div align="right" id="ucol" ><!-- style="display:none;" --> 
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"></font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="UserCombo" />&nbsp;</b>
								</font>
							</div>
						</td>

						
						<td width="50%" class="tdfont">
				
						<div align="left" id="ulist" ><!-- style="display:none;" --> 
							    <!-- <input Type="checkbox" name="mail" onchange="getValue(this.value)" value="1"/>Entry User -->
							 <%--    <%=request.getAttribute("entry") %> --%>
							    <input type="checkbox" name="entryuser1" value="0" onchange="onchangeChk();" >Entry User
							 <%--    <%=request.getAttribute("val") %> --%>
							      <input type="checkbox" name="validation1" value="0" onchange="onchangeChk1();">Validation User
							    <%--   <%=request.getAttribute("reval") %> --%>
							        <input type="checkbox" name="revalidation1" value="0" onchange="onchangeChk2();">Revalidation User 
							  
						       
						         <!-- <input Type="checkbox" name="LabMstFB" property="validation" value="1"/>Validation User
						   
						         <input Type="checkbox" name="LabMstFB" property="revalidation" value="1"/>Revalidation User -->
						
						    </div>
						
						</td>
					</tr>	
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">  </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">Collection Area &nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">


								<html:radio name="LabMstFB" tabindex="1" property="sopbased"
									value="1"></html:radio>

								Sample Based
								<html:radio name="LabMstFB" tabindex="1" property="sopbased"
									value="2"></html:radio>

								Patient Based


							</div>
						</td>
					</tr>
					
					 <tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif">  </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> Access to Addendum and Ammendent&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left" >
								 <%-- <html:radio name="LabMstFB" tabindex="1"  property="accesstoaddendum" onclick="hide();" 
									value="0"></html:radio>

								LabWise
								<html:radio name="LabMstFB" tabindex="1"  property="accesstoaddendum" onclick="display();"
									value="1"></html:radio>

								Userwise  --%>  
								  <select id="combo_new" name=combovalue onchange="onchangeChk3();">
  									<option value="1"  onclick="hide();">LabWise</option>
  									<option value="0" onclick="display();">UserWise</option>
  								</select>     
  								<!-- <input type="checkbox" id="combo_new" name="combovalue" value="0" onclick="hide();" onchange="onchangeChk3();" >Labwise
							      <input type="checkbox" id="combo_new" name="combovalue" value="0" onclick="display();" onchange="onchangeChk3();">userwise -->
                                    

							</div>
						</td>
					</tr>
					<!--userwise check  -->
					<tr>
						
						<td width="50%" class="tdfonthead">
							<div align="right" id="ucol" ><!-- style="display:none;" --> 
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"></font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
											key="UserCombo" />&nbsp;</b>
								</font>
							</div>
						</td>

						
						<td width="50%" class="tdfont">
				
						<div align="left" id="ulist" ><!-- style="display:none;" --> 
							    <!-- <input Type="checkbox" name="mail" onchange="getValue(this.value)" value="1"/>Entry User -->
							 <%--    <%=request.getAttribute("entry") %> --%>
							    <input type="checkbox" name="entryuser1" value="0" onchange="onchangeChk();" >Entry User
							 <%--    <%=request.getAttribute("val") %> --%>
							      <input type="checkbox" name="validation1" value="0" onchange="onchangeChk1();">Validation User
							    <%--   <%=request.getAttribute("reval") %> --%>
							        <input type="checkbox" name="revalidation1" value="0" onchange="onchangeChk2();">Revalidation User 
							  
						       
						         <!-- <input Type="checkbox" name="LabMstFB" property="validation" value="1"/>Validation User
						   
						         <input Type="checkbox" name="LabMstFB" property="revalidation" value="1"/>Revalidation User -->
						
						    </div>
						
						</td>
					</tr>	
					
					
					<!--LAB WORKING DAYS  -->

					<tr>
						<td width="20%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="LabWorkingDays" />&nbsp;
								</font>
							</div>
						</td>
						<td width="80%" class="tdfont"><bean:define name="LabMstFB"
								property="labWorkingDays" id="labWorkingDays"
								type="java.lang.String" /> <%
				    // Logic to get the working days in a week .. True->Checked;  False-> unchecked
				    
				    	boolean bMondayWorking=false;
				        boolean bTuesdayWorking=false;
				        boolean bWednesdayWorking=false;
				        boolean bThursdayWorking=false;
				        boolean bFridayWorking=false;
				        boolean bSaturdayWorking=false;
				        boolean bSundayWorking=false;
				    if(labWorkingDays!=null && !labWorkingDays.equals("")){
				    	bMondayWorking=(labWorkingDays.charAt(0)=='1')?true:false;
				        bTuesdayWorking=(labWorkingDays.charAt(1)=='1')?true:false;
				        bWednesdayWorking=(labWorkingDays.charAt(2)=='1')?true:false;
				        bThursdayWorking=(labWorkingDays.charAt(3)=='1')?true:false;
				        bFridayWorking=(labWorkingDays.charAt(4)=='1')?true:false;
				        bSaturdayWorking=(labWorkingDays.charAt(5)=='1')?true:false;
				        bSundayWorking=(labWorkingDays.charAt(6)=='1')?true:false;
				    }
				    %>
							<div align="left">
								<%if(bMondayWorking){ %>
								<input Type="checkbox" name="chkMon" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkMon" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Monday" />

								<%if(bTuesdayWorking){ %>
								<input Type="checkbox" name="chkTue" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkTue" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Tuesday" />

								<%if(bWednesdayWorking){ %>
								<input Type="checkbox" name="chkWed" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkWed" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Wednesday" />

								<%if(bThursdayWorking){ %>
								<input Type="checkbox" name="chkThu" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkThu" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Thursday" />

								<%if(bFridayWorking){ %>
								<input Type="checkbox" name="chkFri" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkFri" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Friday" />

								<%if(bSaturdayWorking){ %>
								<input Type="checkbox" name="chkSat" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkSat" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Saturday" />

								<%if(bSundayWorking){ %>
								<input Type="checkbox" name="chkSun" onclick="setDay(this)"
									checked="checked" />
								<%}else{ %>
								<input Type="checkbox" name="chkSun" onclick="setDay(this)" />
								<%} %>
								<bean:message key="Sunday" />

							</div></td>
					</tr>

					<!--NUMBER OF TESTS  -->

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="NumberofTests" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="LabMstFB" property="numberofTests"
									maxlength="4" size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)" tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
					
					<!-- HEADER TEXT -->
					
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="Headertext" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="LabMstFB" property="headertext" maxlength="50"
									size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>

					<!-- Footer text -->


					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="FooterText" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:text name="LabMstFB" property="footerText" maxlength="50"
									size="30" readonly="<%=this.readOnly %>"
									onkeypress="return validateAlphabetsOnly(event,this)"
									tabindex="1">
								</html:text>
							</div>
						</td>
					</tr>
							
							
							<!--remarks  -->

					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="remarks" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								<html:textarea name="LabMstFB" property="remarks" cols="28"
									tabindex="1" readonly="<%=this.readOnly %>"
									onkeypress="return CheckMaxLength(event,this,50,3)">
								</html:textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="isTimeBound" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div align="left">
								
								<html:checkbox property="isTimeBound" onclick="showTime(this)" name="LabMstFB"></html:checkbox>
							</div>
						</td>
					</tr>
					<tr name="time">
						<td width="30%" class="tdfonthead">
							<div name="time" align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="startTime" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
							<div name="time" align="left">
								<html:text name="LabMstFB" property="startTimeHH" maxlength="2"
									size="5" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)"
									tabindex="1"></html:text><b>:</b>
									<html:text name="LabMstFB" property="startTimeMM" maxlength="2"
									size="5" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)"
									tabindex="1">
								</html:text>
								<b>(HH:MM 24 Hrs )</b>
							</div>
						</td>
						</tr>
						<tr name="time">
						<td width="30%" class="tdfonthead">
							<div name="time" align="right">
								<font color="RED" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> </font> <font
									color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="endTime" />&nbsp;
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont">
						<div  name="time" align="left">
							<html:text name="LabMstFB" property="endTimeHH" maxlength="2"
									size="5" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)"
									tabindex="1">
								</html:text><b>:</b>
								<html:text name="LabMstFB" property="endTimeMM" maxlength="2"
									size="5" readonly="<%=this.readOnly %>"
									onkeypress="return validateNumeric(event,this)"
									tabindex="1">
								</html:text>
								<b>(HH:MM 24 Hrs )</b>
							</div>
						</td>
					</tr>




				</table>
			</his:ContentTag>

			<his:ButtonToolBarTag>
				<span id="saveDiv"> <logic:notEqual name="LabMstFB"
						property="hmode" value="MODIFY">
						<logic:notEqual name="LabMstFB" property="hmode" value="VIEW">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-sv.png"/>'
								style="cursor: pointer"
								onkeypress="if(event.keyCode==13) finalSubmit('SAVE')"
								onclick="finalSubmit('SAVE')" tabindex="1">
							<img class="button"
								src='<his:path src="/hisglobal/images/btn-clr.png"/>'
								style="cursor: pointer" onclick="clearAddForm()"
								onkeypress="if(event.keyCode==13) clearAddForm()" tabindex="1">
						</logic:notEqual>
					</logic:notEqual> <logic:equal name="LabMstFB" property="hmode" value="MODIFY">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-mo.png"/>'
							style="cursor: pointer"
							onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')"
							onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
						<img class="button"
							src='<his:path src="/hisglobal/images/btn-clr.png"/>'
							style="cursor: pointer" onclick="clearForm()"
							onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
					</logic:equal> <img class="button"
					src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
					style="cursor: pointer" onclick="submitForm('CANCEL')"
					onkeypress="if(event.keyCode==13) submitForm('CANCEL')"
					tabindex="1">
				</span>
			</his:ButtonToolBarTag>
			 <logic:equal name="LabMstFB" property="hmode" value="ADD">
			<logic:present name="<%=InvestigationConfig.LIST_LAB_COMBO %>">
		<logic:empty name="<%=InvestigationConfig.LIST_LAB_COMBO  %>">
			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<b>No New Global Lab Found</b>
			</font>	
		</logic:empty>		
		</logic:present>		
		</logic:equal>
		
			<his:status />
		</his:TransactionContainer>



	</html:form>
</body>
</html>