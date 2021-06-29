<!-- 
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST GLOBAL Master
 ## Purpose						        : 
 ## Date of Creation					:19-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
 -->


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/labRequisition.js" />
<his:javascript src="/new_investigation/js/labTestGlobalMst.js" />
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>


<script type="text/javascript">

function clearForm1()
{

//	 alert("hi");
  //document.getElementsByName('testCode')[0].value="-1"; 
  //document.getElementsByName('labCode')[0].value="-1";
  document.getElementsByName('testCode')[0].value="-1";
  document.getElementsByName('testMethod')[0].value="-1";
  document.getElementsByName("isTestAvailable")[0].checked="true";
  document.getElementsByName("isConfidential")[1].checked="true";
  document.getElementsByName("isAppointment")[1].checked="true";
  document.getElementsByName('noOfTest')[0].value="9999";
  document.getElementsByName('reportAvailableAfter')[0].value="2"; 
  document.getElementsByName('testPrintingType')[0].value="1"; 
  document.getElementsByName("isConsent")[1].checked="true"; 
  document.getElementsByName("isMultisession")[1].checked="true";
  document.getElementsByName("isRequisitionFormNeeded")[1].checked="true";
  document.getElementsByName("isMandatoryReq")[1].checked="true";
  document.getElementsByName("isSampleFormNeeded")[1].checked="true";
  document.getElementsByName("genderBound")[0].checked="true";
  document.getElementsByName("ageBound")[1].checked="true";
  document.getElementsByName("isSecurePrinting")[1].checked="true";
  document.getElementsByName("isGrossingReq")[1].checked="true";
  document.getElementsByName("isFilmReq")[1].checked="true";
  document.getElementsByName("instructionPat")[0].value="";
  document.getElementsByName("instructionColl")[0].value="";
  document.getElementsByName("instructionPost")[0].value="";
  document.getElementsByName("instructionTech")[0].value="";
  document.getElementsByName("isNablAccritedTest")[1].checked="true";
  document.getElementsByName("isPID")[1].checked="true";
}

function validateAge(obj)
{
	if(parseInt(obj.value)>125)
		{
		
		alert("Max Age to be entered is 125");
		obj.value="";
		obj.focus();
		
		
		}
		
}



function fun()
{



	 var element=document.getElementsByTagName("textarea")[0];
 	   //alert(element);
    element.setAttribute("maxlength","1000");

   var element=document.getElementsByTagName("textarea")[1];
	   //alert(element);
   element.setAttribute("maxlength","1000");

   var element=document.getElementsByTagName("textarea")[2];
  //alert(element);
   element.setAttribute("maxlength","1000");

   var element=document.getElementsByTagName("textarea")[3];
 //alert(element);
   element.setAttribute("maxlength","1000");


var ageBound = document.getElementsByName('ageBound');
var ageBound_value;
for(var i = 0; i < ageBound.length; i++){
    if(ageBound[i].checked){
    	ageBound_value = ageBound[i].value;
    }
}
if(ageBound_value=="1")
	{
	document.getElementById("div1").style.display="";	
	}
else
	{
	document.getElementById("div1").style.display="none";
	}
}



function populatePage()

{
      var code = document.getElementsByName("testCode")[0].value;
      if (code != -1) {
            submitPage('POPULATE');
      } else {
            submitPage('ADD');
      }    
}
function checkTestCode()
{
	if(document.getElementsByName("userTestCode")[0].value=="")
	{
		alert("Enter the Test Code");
		document.getElementsByName("userTestCode")[0].focus();
		return false;
	}

	if(document.getElementsByName("preferenceOrder")[0].value=="")
	{
		alert("Enter Preference Order.");
		document.getElementsByName("preferenceOrder")[0].focus();
		return false;
	}
	
	return true;
	
}
	
function showHideDiv()
{
	
var ageBound = document.getElementsByName('ageBound');
var ageBound_value;
for(var i = 0; i < ageBound.length; i++){
    if(ageBound[i].checked){
    	ageBound_value = ageBound[i].value;
    }
}
if(ageBound_value=="1")
	{
	document.getElementById("div1").style.display="";	
	}
else
	{
	document.getElementById("div1").style.display="none";
	}
}
function filterTestListByLab(obj)
{ 
	//alert("in filterTestListByLab()");
	var testList = getTest(obj);
	//alert(testList);
	setTest(testList);
}

function getTest(obj)
{
		var flg = false;
		var objTestList = null;
		var _mode = "AJX_G_Test";
		var unit = obj.value;
		document.getElementsByName("labCode").value=obj.value;
		var urlNew= "/HISInvestigationG5/new_investigation/masters/LabTestGlobalMstACT.cnt?hmode="+_mode+'&unit='+unit;	
		//alert("in getTest()"+urlNew);
		var objXHR = {url: urlNew, sync:true, postData: "", handleAs: "json",
			load: function(data) 
			{
				//alert("DATA= :"+data.length+data[0].TestCode);
				objTestList = data;
				flg = true;
			},
	        error: function(error)
	        {
	            //if(typeof objMenuMealTimeList == 'undefined' || objMenuMealTimeList==null || objMenuMealTimeList=="" || objMenuMealTimeList.length==0)
	            	//alert("No  Type");
	            alert(error+"Error while populating Information");
	            objTestList = null;
	            flg = false;
	        }};
	
		var objDojoAjax = dojo.xhrPost(objXHR);		
		return objTestList;
}

function setTest(objTestList)
{
	//alert("Test No :"+obj.TestId+"  Test Name :"+obj.TestName);
	//var objTestList = obj.value;
	if(objTestList !=null)
	{
		//alert(objTestList.length);
		var testList = document.getElementsByName("testCode")[0];
		//alert(testList);
		testList.innerHTML="";
		for(var i=0;i<objTestList.length;i++)
			{
				//alert("hhhhhhhhh");
				//alert(objTestList.length);
				opt = document.createElement("option");
				opt.value = objTestList[i].testCode;
				opt.innerHTML = objTestList[i].testName;
				testList.appendChild(opt);
			}
	}
}
 

function setDay(obj)
{

 // var workingDays="0000000";
	  var workingDays= document.getElementsByName('testDays')[0].value;
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
		//alert(workingDays);
		 
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
		//alert(workingDays);
		 
		}
		else if(obj.name=="chkFri")
		
		{
			//alert("check Fri");
		workingDays=workingDays.substring(0, 4)+'1'+workingDays.substring(5, 7);
		//alert(workingDays);
		 
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
		//alert(workingDays);
		 
		}
		
		 
		}
	else
	{
		if(obj.name=="chkMon")
		{
			//alert("uncheck Mon");
		workingDays='0'+workingDays.substring(1, 7);
		//alert(workingDays);
		 
		}
		  if(obj.name=="chkTue")
		
		{
			//alert("uncheck Tue");
		workingDays=workingDays.substring(0, 1)+'0'+workingDays.substring(2, 7);
		//alert(workingDays);
		 
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
		
	document.getElementsByName('testDays')[0].value=workingDays;
	 	//alert("final value is");
	//alert(document.getElementsByName('testDays')[0].value); 
		//alert(workingDays);
		}

</script>
<body onload="fun();">
<html:form action="/masters/LabTestLocalMstACT">

    <html:hidden name="LabTestGlobalMstFB" property="hmode" />
    <html:hidden name="LabTestGlobalMstFB" property="labName" />
     <html:hidden name="LabTestGlobalMstFB" property="testDays" />
	
    <%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	
	<logic:equal name="LabTestGlobalMstFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>


<his:TitleTag name="Lab Test Local Master">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="2" cellpadding="1">
			  <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LaboratoryName"/>&nbsp;
						 </font>
				     </div>
			      </td>
		         <td width="50%" class="tdfont">
			      <div align="left">
				 <html:text name="LabTestGlobalMstFB" property="labName" readonly="true" style="width:35%" tabindex="1" ></html:text>
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
								<bean:message key="TestName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <logic:equal name="LabTestGlobalMstFB" property="hmode" value="ADD">
			      <div align="left"> 
				 <html:select name="LabTestGlobalMstFB" property="testCode" onchange="populatePage();" style="width:35%" tabindex="1"  >
				       			<html:option value="-1">Select Value</html:option>
				       			<logic:present name="<%= InvestigationConfig.LIST_TEST_COMBO%>">
	 	   			<html:options collection="<%=InvestigationConfig.LIST_TEST_COMBO %>" property="value" labelProperty="label"/>	
	 	   			</logic:present>	 
				      				</html:select>
				  </div>
				  </logic:equal>
				   <logic:equal name="LabTestGlobalMstFB" property="hmode" value="POPULATE">
			      <div align="left"> 
				 <html:select name="LabTestGlobalMstFB" property="testCode" onchange="populatePage();" style="width:35%" tabindex="1"  >
				       			<html:option value="-1">Select Value</html:option>
				       			<logic:present name="<%= InvestigationConfig.LIST_TEST_COMBO%>">
	 	   			<html:options collection="<%=InvestigationConfig.LIST_TEST_COMBO %>" property="value" labelProperty="label"/>	
	 	   			</logic:present>	 
				      				</html:select>
				  </div>
				  </logic:equal>
				   <logic:equal name="LabTestGlobalMstFB" property="hmode" value="MODIFY">
			      <div align="left"> 
				       <html:text name="LabTestGlobalMstFB" property="testName" readonly="true" style="width:35%" tabindex="1" />
				  </div>
				  </logic:equal>
			     </td>
			</tr>
			
			<!-- user defined test code --> 
			
			<tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="userTestCode"/>&nbsp;
						 </font>
				     </div>
			      </td>
		         <td width="50%" class="tdfont">
			      <div align="left">
				 <html:text name="LabTestGlobalMstFB" property="userTestCode" maxlength="10" style="width:35%" tabindex="1" ></html:text>
				  </div>
			     </td>
			</tr>
			
			  <!-- Preference Order --> 
			
			<tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="preferenceOrder"/>&nbsp;
						 </font>
				     </div>
			      </td>
		         <td width="50%" class="tdfont">
			      <div align="left">
				 <html:text name="LabTestGlobalMstFB" property="preferenceOrder" maxlength="10" style="width:35%" tabindex="1" ></html:text>
				  </div>
			     </td>
			</tr>
			
			
			
			     <tr>
			<td width="50%" class="tdfonthead">
			        <div align="right">
			          
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="machine"/>&nbsp;
						 </font>
				     </div>
			      </td>
		         <td width="50%" class="tdfont">
			      <div align="left"> 
				 <html:select name="LabTestGlobalMstFB"  property="machineId" style="width:35%" tabindex="1" >
				       					<html:option value="-1">No Machine</html:option>
				       					<logic:present name="<%= InvestigationConfig.LIST_MACHINE_COMBO%>">
				       					<logic:notEmpty  	name="<%=InvestigationConfig.LIST_MACHINE_COMBO %>">
				 	   					<html:options collection="<%=InvestigationConfig.LIST_MACHINE_COMBO %>" property="value" labelProperty="label"/>
				      				    </logic:notEmpty>
				      				    </logic:present>
				      				</html:select>
				  </div>
			     </td>
			     </tr>
			     
			
			<tr>
			<td width="50%" class="tdfonthead">
			        <div align="right">
			            
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="testMethod"/>&nbsp;
						 </font>
				     </div>
			      </td>
		         <td width="50%" class="tdfont">
			      <div align="left"> 
				 <html:select name="LabTestGlobalMstFB" property="testMethod" style="width:35%" tabindex="1" >
				       					<html:option value="-1">Select Value</html:option>
				       					<logic:present name="<%= InvestigationConfig.LIST_TEST_METHOD_COMBO%>">
				 	   					<html:options collection="<%=InvestigationConfig.LIST_TEST_METHOD_COMBO %>" property="value" labelProperty="label"/>
				 	   					</logic:present>
				      				</html:select>
				  </div>
			     </td>
			     </tr>
			     <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="testAvailability"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isTestAvailable" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isTestAvailable" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			      <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isConfidential"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isConfidential" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isConfidential" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			      <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isAppointment"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isAppointment" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isAppointment" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			      
			          <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="OPDDoctorDesk"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isOPDDoctorDesk" value="1"  ></html:radio>
			      <bean:message key="raise"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isOPDDoctorDesk" value="0"  ></html:radio>
			      <bean:message key="advise"/>
			      </div>
			      </td>
			      </tr>
			      
			       <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="IPDDoctorDesk"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isIPDDoctorDesk" value="1"  ></html:radio>
			      <bean:message key="raise"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isIPDDoctorDesk" value="0"  ></html:radio>
			      <bean:message key="advise"/>
			      </div>
			      </td>
			      </tr>
			      
			       <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="OPDBayDoctorDesk"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isOPDBayDoctorDesk" value="1"  ></html:radio>
			      <bean:message key="raise"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isOPDBayDoctorDesk" value="0"  ></html:radio>
			      <bean:message key="advise"/>
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
								<bean:message key="testDays"/>&nbsp;
						 </font>
				     </div>
			      </td>
		         <td width="50%" class="tdfont">
			      <logic:equal name="LabTestGlobalMstFB" property="hmode" value="ADD">
					      <div align="left">
						         <input Type="checkbox" name="chkMon" onclick="setDay(this)" />
						       <bean:message key="Monday"/>
						         <input Type="checkbox" name="chkTue" onclick="setDay(this)"  />
						       <bean:message key="Tuesday"/>
						         <input Type="checkbox" name="chkWed" onclick="setDay(this)"  />
						       <bean:message key="Wednesday"/>
						         <input Type="checkbox" name="chkThu" onclick="setDay(this)"  />
						       <bean:message key="Thursday"/>
						         <input Type="checkbox" name="chkFri" onclick="setDay(this)"  />
						       <bean:message key="Friday"/>
						         <input Type="checkbox" name="chkSat" onclick="setDay(this)"  />
						       <bean:message key="Saturday"/>
								<input Type="checkbox" name="chkSun" onclick="setDay(this)"  />
						       <bean:message key="Sunday"/>
							    
						  </div>
					</logic:equal>
				  <logic:notEqual name="LabTestGlobalMstFB" property="hmode" value="ADD">
				    <bean:define name="LabTestGlobalMstFB" property="testDays" id="testDays" type="java.lang.String" />
				    <%
				    // Logic to get the working days in a week .. True->Checked;  False-> unchecked
				    	boolean bMondayWorking=false;
				        boolean bTuesdayWorking=false;
				        boolean bWednesdayWorking=false;
				        boolean bThursdayWorking=false;
				        boolean bFridayWorking=false;
				        boolean bSaturdayWorking=false;
				        boolean bSundayWorking=false;
				    if(testDays!=null && !testDays.equals("")){
				    	bMondayWorking=(testDays.charAt(0)=='1')?true:false;
				        bTuesdayWorking=(testDays.charAt(1)=='1')?true:false;
				        bWednesdayWorking=(testDays.charAt(2)=='1')?true:false;
				        bThursdayWorking=(testDays.charAt(3)=='1')?true:false;
				        bFridayWorking=(testDays.charAt(4)=='1')?true:false;
				        bSaturdayWorking=(testDays.charAt(5)=='1')?true:false;
				        bSundayWorking=(testDays.charAt(6)=='1')?true:false;
				    }
				    %>
				      <div align="left">
				      <%if(bMondayWorking){ %>
					         <input Type="checkbox" name="chkMon" onclick="setDay(this)" checked="checked"/>
					   <%}else{ %>
					   		<input Type="checkbox" name="chkMon" onclick="setDay(this)" />
					   	<%} %>
					       <bean:message key="Monday"/>
					       
					      <%if(bTuesdayWorking){ %>
						         <input Type="checkbox" name="chkTue" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkTue" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Tuesday"/>
					       
					       <%if(bWednesdayWorking){ %>
						         <input Type="checkbox" name="chkWed" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkWed" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Wednesday"/>
					       
					        <%if(bThursdayWorking){ %>
						         <input Type="checkbox" name="chkThu" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkThu" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Thursday"/>
					       
					        <%if(bFridayWorking){ %>
						         <input Type="checkbox" name="chkFri" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkFri" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Friday"/>
					       
					         <%if(bSaturdayWorking){ %>
						         <input Type="checkbox" name="chkSat" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkSat" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Saturday"/>
						   	
						   	 <%if(bSundayWorking){ %>
						         <input Type="checkbox" name="chkSun" onclick="setDay(this)" checked="checked"/>
						   <%}else{ %>
						   		<input Type="checkbox" name="chkSun" onclick="setDay(this)" />
						   	<%} %>
					       <bean:message key="Sunday"/>
						    
					  </div>
				  </logic:notEqual>
			     </td>
			     </tr>
			     <tr>
			     <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="numberofTest"/>&nbsp;
						 </font>
				     </div>
			      </td width="50%" class="tdfont">
			      <td>
			       <html:text name="LabTestGlobalMstFB" property="noOfTest"  maxlength="4" size="30" style="width:35%" onkeypress="return validateNumeric(event,this)" tabindex="1"/>
			      </td>
			      </tr>
			      <tr>
			      <td width="50%" class="tdfonthead">
			        <div align="right">
			        <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font>
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="reportavailableAfter"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left"> 
				 <html:select name="LabTestGlobalMstFB" property="reportAvailableAfter"  tabindex="1" style="width:35%" >
				       					<html:option value="-1">Select Value</html:option>
            	   		                 <html:option value="<%=InvestigationConfig.REPORTAVAILABLEAFTERRESULTPRINTING%>">	<bean:message key="resultentry"/></html:option>
            	   		                 <html:option value="<%=InvestigationConfig.REPORTAVAILABLEAFTERRESULTVALIDATION %>">	<bean:message key="resultvalidation"/></html:option>
            	   		                 <html:option value="<%=InvestigationConfig.REPORTAVAILABLEAFTERRESULTREVALIDATION %>">	<bean:message key="resultrevalidation"/></html:option>
				      				</html:select>
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
								<bean:message key="resultPrintingType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left"> 
				 <html:select name="LabTestGlobalMstFB" property="testPrintingType"  tabindex="1" style="width:35%" >
				       					<html:option value="-1">Select Value</html:option>
            	   		                 <html:option value="<%=InvestigationConfig.RESULTPRINTINGSTANDARDRANGE%>"><bean:message key="withstandardrange"/></html:option>
            	   		                 <html:option value="<%=InvestigationConfig.RESULTPRINTINGWITHOUTSTANDARDRANGE%>"><bean:message key="withoutstandardrange"/></html:option>
            	   		                 <!--<html:option value="2">Special Printing</html:option>-->
				      				</html:select>
				  </div>
			     </td>
			      </tr>
			      <logic:equal name="LabTestGlobalMstFB" property="testPrintingType" value="2">
			      <tr>
			      <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="splPrintTemp"/>&nbsp;
						 </font>
				     </div>
			      </td>
		         <td width="50%" class="tdfont">
			      <div align="left"> 
				 <html:select name="LabTestGlobalMstFB" property="labName"  tabindex="1" style="width:35%" >
				       					<html:option value="-1">Select Value</html:option>
<%-- 				 	   					<html:options collection="<%=InvestigationConfig.LIST_LAB_COMBO %>" property="value" labelProperty="label"/> --%>
				      				</html:select>
				  </div>
			     </td>
			      </tr>
			      </logic:equal>
			      <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isConsent"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isConsent" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isConsent" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			      <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isMultiSession"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isMultisession" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isMultisession" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			       <tr>
			      <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="requisitionFormNeeded"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isRequisitionFormNeeded" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isRequisitionFormNeeded" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			       <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="mandatory"/>&nbsp;
						</font>
						</div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isMandatoryReq" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isMandatoryReq" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			      <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="sampleFormNeed"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isSampleFormNeeded" value="1"  ></html:radio>
			       <bean:message key="yes"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isSampleFormNeeded" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			      
			      <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="gender"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			        <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="genderBound" value="0"  ></html:radio>
			      <bean:message key="noBound"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="genderBound" value="1"  ></html:radio>
			      <bean:message key="male"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="genderBound" value="2"  ></html:radio>
			      <bean:message key="female"/>
			      </div>
			      </td>
			      </tr>
			      <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="ageBound"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="ageBound" value="1" onclick="showHideDiv();" ></html:radio>
			      <bean:message key="yes"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="ageBound" value="0" onclick="showHideDiv();" ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			      
			      <tr id="div1"  style="display:none">
			      <td width="50%" class="tdfonthead">
			     
			        <div align="right" >
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="ageRange"/>&nbsp;
						 </font>
				     </div>
				     </td>
				     <td width="50%" class="tdfont" >
		         <div align="left">
		        <bean:message key="lowRange"/>&nbsp;
		        <html:text name="LabTestGlobalMstFB" property="lowAgeRange"  maxlength="3" size="5" style="width:35%" onkeypress="return validateNumeric(event,this)" tabindex="1" onblur="validateAge(this);"/>
		        </div>
		         <div align="left" class="tdfont">
		        <bean:message key="highRange"/>&nbsp;
				<html:text name="LabTestGlobalMstFB" property="highAgeRange" maxlength="3" size="5" style="width:35%" onkeypress="return validateNumeric(event,this)" tabindex="1" onblur="validateAge(this);"/>
		         </div>
		         
		         </td>
		         </tr>
		       
			      <tr>
			 <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="securePrint"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isSecurePrinting" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isSecurePrinting" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			      <tr>
			    	<td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="grossing"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isGrossingReq" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isGrossingReq" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			      <tr>
			    	<td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="filmReq"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isFilmReq" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isFilmReq" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			      <tr>
			    	<td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Is PID Form Required &nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isPID" value="1"  ></html:radio>
			       yes
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isPID" value="0"  ></html:radio>
			       no
			      </div>
			      </td>
			      </tr>
			       <tr>
			    	<td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									NABL Accredited Test &nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isNablAccritedTest" value="1"  ></html:radio>
			      <bean:message key="yes"/>
			      <html:radio name="LabTestGlobalMstFB"  tabindex="1" property="isNablAccritedTest" value="0"  ></html:radio>
			      <bean:message key="no"/>
			      </div>
			      </td>
			      </tr>
			      
			      
			     
			      <tr>
			      
			      <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Instruction For Patient
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:textarea name="LabTestGlobalMstFB"  tabindex="1" property="instructionPat" style="width:35%"  ></html:textarea>
			      </div>
			      </td>
			      </tr>
			      <tr>
			    	<td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Instruction For Collection
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:textarea name="LabTestGlobalMstFB"  tabindex="1" property="instructionColl" style="width:35%"></html:textarea>
			      </div>
			      </td>
			      </tr>
			      <tr>
			    	<td width="50%" class="tdfonthead">
			        <div align="right">
			     
						  <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Instruction For Post Sample Collection To Patient
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			     
			      <html:textarea name="LabTestGlobalMstFB"  tabindex="1" property="instructionPost" style="width:35%"></html:textarea>
			      </div>
			      </td>
			      </tr>
			      <tr>
			    	<td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					      		
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Instruction For Technician
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			       <div align="left">
			      <html:textarea name="LabTestGlobalMstFB"  tabindex="1" property="instructionTech" style="width:35%"></html:textarea>
			      </div>
			      </td>
			      
			      </tr>
			      
			</table>
		
		
		</his:ContentTag>
	
		<his:ButtonToolBarTag>
		
				<span id="saveDiv">
			    <logic:notEqual name="LabTestGlobalMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="LabTestGlobalMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) if(checkTestCode()) finalSubmit('SAVE')" onclick="if(checkTestCode()) finalSubmit('SAVE')" tabindex="1">
				 <img class="button"  src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer"  onkeypress="if(event.keyCode==13) clearForm1()" onclick="clearForm1()" tabindex="1">
				 
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="LabTestGlobalMstFB" property="hmode" value="MODIFY"> 
				   <img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) if(checkTestCode()) finalSubmit('MODIFYSAVE')" onclick="if(checkTestCode()) finalSubmit('MODIFYSAVE')" tabindex="1">
				   <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="modifyClear()" onkeypress="if(event.keyCode==13) modifyClear()" tabindex="1">
				</logic:equal> 
	               <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		</his:ButtonToolBarTag>
		<logic:present name="<%=InvestigationConfig.LIST_TEST_COMBO %>">
		<logic:empty name="<%=InvestigationConfig.LIST_TEST_COMBO  %>">
			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<b>No Global Test Found </b>
			</font>	
		</logic:empty>		
		</logic:present>
		<his:status/>
		<html:hidden name="LabTestGlobalMstFB" property="hmode" />
        <html:hidden name="LabTestGlobalMstFB" property="labCode" />
        <html:hidden name="LabTestGlobalMstFB" property="ageBound" />
        <html:hidden name="LabTestGlobalMstFB" property="testCode" />
        <cmbPers:cmbPers />
        
		</html:form>
	</body>
	
</html>