<%try {%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,registration.*,hisglobal.presentation.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
	
function submitPage(mode){
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function getUnitForDepartment(obj){
	document.getElementsByName("reportMode")[0].value='GETUNIT'
	document.forms[0].submit();
}

function religionCategoryWisePatientListingReportHandler()
{
	if(!isSelected(document.getElementsByName("departmentCode")[0],"Department")){
		return false;
	}
	var jrxmlName="";
   	var orderBy=0;
   	for(var i=0;i<document.getElementsByName('orderBy').length;i++){
   		if(document.getElementsByName('orderBy')[i].checked){
   			orderBy=i;
   			break;
   		}
   	}
   	
   	document.getElementsByName('orderBy')
   	
	if(document.getElementsByName('patientType')[0].value=="%"){
    	document.getElementsByName('patRegCatCode')[0].value="%"
    	
    	switch(parseInt(orderBy))
    	{
    		case 0: 
    				//alert(orderBy +" order by Date");
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_DATE%>";
			    	break;
    		case 1: 
			    	//alert(orderBy + " order by Name");
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_NAME%>";
			    	break;
    		case 2: 
    				//alert(orderBy+" order by crno");
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_CRNO%>";
			    	break;
    		case 3: 
    				//alert(orderBy+" order by crno");
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_FILENO%>";
			    	break;
    	}
	}else if(document.getElementsByName('patientType')[0].value=="1")
	{
    	document.getElementsByName('patRegCatCode')[0].value="15"
    	switch(parseInt(orderBy)){
    		case 0: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_REVISIT_PATIENT_REPORT_ORDER_BY_DATE%>";
			    	break;
    		case 1: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_REVISIT_PATIENT_REPORT_ORDER_BY_NAME%>";
			    	break;
    		case 2: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_REVISIT_PATIENT_REPORT_ORDER_BY_CRNO%>";
			    	break;
    		case 3: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_REVISIT_PATIENT_REPORT_ORDER_BY_FILENO%>";
			    	break;
		}	    	
	}
	else if(document.getElementsByName('patientType')[0].value=="2")
	{
		switch(parseInt(orderBy)){
    		case 0: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_OLD_PATIENT_REPORT_ORDER_BY_DATE%>";
			    	break;
    		case 1: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_OLD_PATIENT_REPORT_ORDER_BY_NAME%>";
			    	break;
    		case 2: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_OLD_PATIENT_REPORT_ORDER_BY_CRNO%>";
			    	break;
    		case 3: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_OLD_PATIENT_REPORT_ORDER_BY_FILENO%>";
			    	break;
		}	             
	}
	else{
		//document.getElementsByName('patRegCatCode')[0].value="15"
		switch(parseInt(orderBy)){
    		case 0: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_NEW_PATIENT_REPORT_ORDER_BY_DATE%>";
			    	break;
    		case 1: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_NEW_PATIENT_REPORT_ORDER_BY_NAME%>";
			    	break;
    		case 2: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_NEW_PATIENT_REPORT_ORDER_BY_CRNO%>";
			    	break;
    		case 3: 
			    	jrxmlName="<%=RegistrationConfig.DEPT_USER_WISE_LIST_OF_NEW_PATIENT_REPORT_ORDER_BY_FILENO%>";
			    	break;
		}	                     
	
	}
	if(document.getElementsByName('isSupervisor')[0].value==1)
	{
		if(document.getElementsByName('isConsolidate')[0].checked==true)
		{
			switch(parseInt(orderBy))
	    	{
	    		case 0: 
	    				//alert(orderBy +" order by Date");
				    	jrxmlName="<%=RegistrationConfig.DEPT_WISE_CONSOLIDATE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_DATE%>";
				    	break;
	    		
	    		case 3: 
	    				//alert(orderBy+" order by crno");
				    	jrxmlName="<%=RegistrationConfig.DEPT_WISE_CONSOLIDATE_LIST_OF_ALL_PATIENT_REPORT_ORDER_BY_FILENO%>";
				    	break;
	    	}
		}
	}
	document.getElementsByName('jrxmlName')[0].value=jrxmlName;
	
	
	var success=false;
	
    if(validateGraphOrText())
    { 
		if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
	        if(validateTextual())
	        {      
	        	if(validateTodayOrDate())
	        	{
	           	  success=true;	         	   
	           	}          	  
            }
        }//end of selection is for text based report	   		
		if(document.getElementsByName('graphOrText')[0].selectedIndex==2)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
	        if(validateGraphForAgeWiseReport())
	        {
		    	//if(validateTodayOrDate())        
		        success=true;	                
	        }
        }//end of selection is for graph based report	  
    }     
      
       
        
if(success==true )
 {
	 if(document.getElementsByName('departmentCode')[0].value==-1)
	  		{
			alert("Please select the Department");
			document.getElementsByName('departmentCode')[0].focus();
			   success=false;
			}
	
}
	/*
	else
if(success==true &&  document.getElementsByName('option')[1].checked)
{
	
 if(document.getElementsByName('unit')[0].value==-1)
	{
		alert("Please select the Unit");
		document.getElementsByName('unit')[0].focus();
		   success=false;
	}
	if(document.getElementsByName('speciality')[0].value==-1)
	{
		alert("Please select the Speciality Type");
		document.getElementsByName('speciality')[0].focus();
		   success=false;
	}
	
}*/
	 
	  return success;
}//end of function religionCategoryWisePatientReportFB() 
		
function checkIsConsolidate()
{
//alert(document.getElementsByName('isConsolidate')[0].checked)
 if(document.getElementsByName('isSupervisor')[0].value==1)
 {
	if(document.getElementsByName('isConsolidate')[0].checked==true)
	{
		document.getElementsByName('patientType')[0].disabled=true
		document.getElementsByName('userCode')[0].disabled=true
		document.getElementsByName('patientType')[0].value='%'
		document.getElementsByName('userCode')[0].value='%'
		document.getElementsByName('orderBy')[1].disabled=true
		document.getElementsByName('orderBy')[2].disabled=true
		
	}
	if(document.getElementsByName('isConsolidate')[0].checked==false)
	{
		document.getElementsByName('patientType')[0].disabled=false
		document.getElementsByName('userCode')[0].disabled=false
		document.getElementsByName('orderBy')[1].disabled=false
		document.getElementsByName('orderBy')[2].disabled=false
		
	}
	}
}
</script>

<body onload="checkIsConsolidate()" >


<%
		String title = "";
		if (Config.CLIENT.equals(Config.CLIENT_GNCTD)) {
			title = "Period Wise Use Of Services By The Patient";
		} else {
			title = "Department Wise Outpatients";
		}
%>

<his:TitleTag name="List of OPD Patients">
</his:TitleTag>

<%
		String fromDateLabel = "";
		String toDateLabel = "";
		String fromDateControl = "";
		String toDateControl = "";
		String fromLabel = "";
		String toLabel = "";
		String fromControl = "";
		String toControl = "";
%>
<logic:equal name="religionCategoryWisePatientReportFB" property="choice"
	value="<%=Config.CHOICE_TODAY%>">
	<%
			fromDateLabel = "display:none";
			toDateLabel = "display:none";
			fromDateControl = "display:none";
			toDateControl = "display:none";
			fromLabel = "";
			toLabel = "";
			fromControl = "";
			toControl = "";
	%>
</logic:equal>

<logic:equal name="religionCategoryWisePatientReportFB" property="choice"
	value="<%=Config.CHOICE_DATE_WISE%>">
	<%
			fromDateLabel = "";
			toDateLabel = "";
			fromDateControl = "";
			toDateControl = "";
			fromLabel = "display:none";
			toLabel = "display:none";
			fromControl = "display:none";
			toControl = "display:none";
	%>
</logic:equal>

<bean:define name="religionCategoryWisePatientReportFB" property="fromDate"
	id="frDate" type="java.lang.String" />
<bean:define name="religionCategoryWisePatientReportFB" property="toDate"
	id="tDate" type="java.lang.String" />
<%
			if (frDate == null || frDate.equalsIgnoreCase("")) {
			System.out.println("dt::::::::::::::::");
			Date dt = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			System.out.println("dt:::::::::" + dt);
			frDate = WebUTIL.getCustomisedSysDate((Date) session
			.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		}

		if (tDate == null || tDate.equalsIgnoreCase("")) {
			System.out.println("dt::::::::::::::::");
			Date dt = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			System.out.println("dt:::::::::" + dt);
			tDate = WebUTIL.getCustomisedSysDate((Date) session
			.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		}
%>

<his:SubTitleTag name="Report Details">
<logic:equal name="religionCategoryWisePatientReportFB" property="isSupervisor" value="1">
		<table width="100%" cellspacing="1" cellpadding="0">
	 	 <tr>
	 	 <td width="90%">
	 	 <div align="right">
	 	 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="isConsolidate"/>      
	        </font>
	        </div>
	 	 </td>
		<td   >
		<div align="left" >
	
        	 <html:checkbox name="religionCategoryWisePatientReportFB" property="isConsolidate" value="1" onchange="checkIsConsolidate()"></html:checkbox>
      
 		 </div>
  	</td>
 	 </tr>
 	 </table>
		
		
	</logic:equal>
</his:SubTitleTag>

<his:ContentTag>
	<table width="100% cellspacing=" 1" cellpadding="0">
		<tr>
			<td class="tdfonthead" width="25%">
			<div id='divfromDate' style='' align="right"><font
				color="#FF0000" size="1"
				face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
				color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="fromDate" /> </font></div>
			</td>

			<td class="tdfont" width="25%">
			<div id='divfromDateControl' style='' align="left"><his:date
				name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>" /></div>
			</td>

			<td class="tdfonthead" width="25%">
			<div id='divtoDate' style='' align="right"><font
				color="#FF0000" size="1"
				face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
				color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="toDate" /> </font></div>
			</td>

			<td class="tdfont" width="25%">
			<div id='divtoDateControl' style='' align="left"><his:date
				name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>" /></div>
			</td>
		</tr>
		
	</table>

	<table width="100%" cellspacing="1" cellpadding="0">

		<%-- 	
<tr>            
 			<td class="tdfonthead"  width="25%">       	      
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      		<bean:message key="deptWise"/>
		      	</font></td>
		      	<td class="tdfont" nowrap width="25%">
		      		<html:radio  name="religionCategoryWisePatientReportFB" property="option" tabindex="1"  value="<%=RegistrationConfig.DEPT_WISE%>"  onclick="showDeptWise()" />
		      		</td>
		      		<td class="tdfonthead"  width="25%">
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="unitWise"/>
		       	</font> </td>
		       	<td class="tdfont" nowrap width="25%">
		        	<html:radio name="religionCategoryWisePatientReportFB" property="option" tabindex="1" value="<%=RegistrationConfig.UNIT_WISE%>" onclick="showUnit()"/>
			</td> 
			 
 		</tr>
--%>
		<tr>

			<td class="tdfonthead" nowrap width="25%">
			<div align="right"><font color="#FF0000"
				size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
				color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="department" /> </font></div>
			</td>
			<td class="tdfont" width="25%">
			
			<html:select
				name="religionCategoryWisePatientReportFB" property="departmentCode"
				tabindex="1" styleClass="regcbo" onchange="getUnitForDepartment(this)">
				<logic:present
					name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>">
					<html:option value="-1">Select Value</html:option>
					<html:options
						collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>"
						property="value" labelProperty="label" />
				</logic:present>
			</html:select>
			
			</td>
			<td class="tdfonthead" nowrap width="25%" >  
				<div align="right">
      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="unit"/>
				  </font>
				</div>
			</td>
      	<td class="tdfont"  width="25%" >
			<html:select name="religionCategoryWisePatientReportFB"  property="unitCode"  tabindex="1"  styleClass="regcbo" onchange="changePatientType(this)" >   
			  		<html:option value="%">All</html:option>
			  		<logic:present
					name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>">
					<html:options
						collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>"
						property="value" labelProperty="label" />
				</logic:present>
			  </html:select> 
		</td>
			

		</tr>
		 <tr>	
			 <td class="tdfonthead" nowrap width="25%" >  
					<div align="right">
	      			      	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
			            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  		<bean:message key="patientType"/>
					  </font>
					</div>
				</td>
	      		<td class="tdfont"  width="25%" >
					<html:select name="religionCategoryWisePatientReportFB"  property="patientType"  tabindex="1"  styleClass="regcbo" onchange="changePatientType(this)" >   
				  		<html:option value="%">All</html:option>
					  		<html:option value="0">New</html:option>
					  		<html:option value="1">Revisit</html:option>
					  		<html:option value="2">Old Registered</html:option>
					  </html:select> 
			</td>
			
		 	<logic:equal name="religionCategoryWisePatientReportFB" property="isSupervisor" value="1">
			<td class="tdfonthead" nowrap width="25%">

			<div align="right"><font
				color="#FF0000" size="1"
				face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
				color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="user" /> </font></div>
			</td>
			<td class="tdfont" width="25%">
			<div >
			<html:select
				name="religionCategoryWisePatientReportFB" property="userCode" tabindex="1"
				styleClass="regcbo">
				<html:option value="%">All Users</html:option>
				<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_USER%>">
					<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_USER%>"
						property="value" labelProperty="label" />
				</logic:present>
			</html:select></div>
			
			</td>
	      </logic:equal>
 		<logic:notEqual name="religionCategoryWisePatientReportFB" property="isSupervisor" value="1">
 		<td class="tdfonthead" nowrap width="25%">
 		</td>
 		<td class="tdfont" nowrap width="25%"></td>
 		</logic:notEqual>
			
      </tr>
      <tr>
			<td width="25%" class="tdfonthead">
			<div align="right">
			<font color="#FF0000">*</font>
			<font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="orderBy" /> </font>
			</div>	
			</td>
			<td width="25%" class="tdfont" colspan="3">
				<div align="left">
					<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> 
					<html:radio name="religionCategoryWisePatientReportFB" property="orderBy" value="0"></html:radio>
					Entry Date 
					<html:radio name="religionCategoryWisePatientReportFB" property="orderBy" value="1"></html:radio>
					Name  
					<html:radio name="religionCategoryWisePatientReportFB" property="orderBy" value="2"></html:radio>
					Cr No.  
					<html:radio name="religionCategoryWisePatientReportFB" property="orderBy" value="3"></html:radio>
					File No.  
					</font>
				</div>
			</td>
			
		</tr>		
	</table>
</his:ContentTag>
<his:SubTitleTag name="Report Generation Option">
</his:SubTitleTag>

<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="25%" class="tdfonthead"><font color="#FF0000">*</font>
			<font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="reportType" /> </font></td>
			<td width="25%" class="tdfont"><html:select
				name="religionCategoryWisePatientReportFB" property="graphOrText"
				tabindex="1" styleClass="regcbo">
				<html:option value="-1">Select Value</html:option>
				<html:option value="<%=Config.TEXT%>">Textual</html:option>

			</html:select></td>
			<td width="25%" class="tdfonthead"><font color="#FF0000">*</font>
			<font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="pdfOrWord" /> </font></td>
			<td width="25%" class="tdfont"><html:select
				name="religionCategoryWisePatientReportFB" property="reportType"
				tabindex="1" styleClass="regcbo">
				<html:option value="-1">Select Value</html:option>
				<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
				<html:option value="<%=Config.RTF %>">HTML</html:option>
			</html:select></td>
		</tr>
	</table>
</his:ContentTag>

<input type="hidden" name="reportMode">
<input type="hidden" name="choice" >
<input type="hidden" name="choice" checked="checked">
<input type="hidden" name="mode" value="RELIGIONCATEGORYWISEPATLIST">
<html:hidden name="religionCategoryWisePatientReportFB" property="isSupervisor"/>
<html:hidden name="religionCategoryWisePatientReportFB" property="patRegCatCode"/>

<html:hidden name="religionCategoryWisePatientReportFB"
	property="jrxmlPath"
	value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH_REPORT1 %>" />
<html:hidden name="religionCategoryWisePatientReportFB"
	property="jrxmlName" />
<html:hidden name="religionCategoryWisePatientReportFB" property="sysDate"
	value="<%=tDate%>" />
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>




