
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,mrd.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/utility/generictemplate/js/commonUtility.js" />
<his:javascript  src="/registration/js/reportsValidation.js"/>
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/masterXml.css" />
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js" />
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script> 
<!--<his:javascript src="/opd/opdJs/opd.js"/>-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

	function submitPage(mode)
	{
		//alert("inside submitPage");
		elmt=document.getElementsByName("reportMode")[0];  
	    elmt.value=mode;
	    document.forms[0].submit();
	}
	function validateFromOrToDate(){
		  //   alert("inside validateFromOrToDate");
		     success=false;        	   
		           
		               valFromDate=document.getElementsByName('fromDate')[0];
			           valToDate=document.getElementsByName('toDate')[0];
			           valSysDate=document.getElementsByName('sysDate')[0];
		      
		               if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
		               {
		                success=true;
		               }             
		           return success;        
		    }
	
	
	function clearCombo(){
		
		if(document.getElementsByName("hospitalCode")[0].value=="0"){
			//alert("Please select Hospital");
			document.getElementsByName("departmentCode")[0].value="%";
			var str1='<html:select name="wardCensusReportFB"  property="unit"  tabindex="1"  styleClass ="regcbo" onchange="showWard();"><html:option value="%">All</html:option></html:select>';
			document.getElementById("divUnitControl").innerHTML=str1;
			str2='<html:select name="wardCensusReportFB"  property="wardCode"  tabindex="1"  styleClass ="regcbo" onchange="showRoom();"><html:option value="%">All</html:option></html:select>';
		  	document.getElementById("divWardControl").innerHTML=str2;
		  	var str3='<html:select name="wardCensusReportFB"  property="roomCode"  tabindex="1"  styleClass ="regcbo"><html:option value="%">All</html:option></html:select>';
			document.getElementById("divRoomControl").innerHTML=str3;
			return false;
		}
		var str3='<html:select name="wardCensusReportFB"  property="roomCode"  tabindex="1"  styleClass ="regcbo"><html:option value="%">All</html:option></html:select>';
		document.getElementById("divRoomControl").innerHTML=str3;
	}
	function wardCensusReportHandler()
	{
		//alert("inside admissionAndDischargeStaticReportHandler");
		document.getElementsByName('strDeptName')[0].value=document.getElementsByName('departmentCode')[0].options[document.getElementsByName('departmentCode')[0].selectedIndex].text;
		document.getElementsByName('strUnitName')[0].value=document.getElementsByName('unit')[0].options[document.getElementsByName('unit')[0].selectedIndex].text;
		
		
		success=false;
		/* if(document.getElementsByName('hospitalCode')[0].value==0 )
		{
			alert("Please Select the Hospital");
			document.getElementsByName('hospitalCode')[0].focus();
			return false;
		} */
		if(validateGraphOrText())
	    { 
			if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
			{
		    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
		        if(validateTextual())
		        {      
		        	if(validateFromOrToDate())
		        	{
		           	return true;           	   
		          	}     	  
	            }
	        }//end of selection is for text based report	
	        if(document.getElementsByName('graphOrText')[0].selectedIndex==2)
			{
		    	document.getElementsByName('reportMode')[0].value="<%=Config.GRAPH%>";
		        if(validateGraphForAgeWiseReport())
		        {
			    	if(validateFromOrToDate())        
			        	success=true;	                
		        }
	        }//end of selection is for graph based report	 
	    }
		
		
		
		return success;
	}//end of function wardCensusReportHandler()

	function showRoom()
	{
		var roomCode = document.getElementsByName("roomCode")[0].value;
		//PragyaDesigner.clearCombo(roomCode);
		
		var hospitalCode=document.getElementsByName("hospitalCode")[0].value;
		if(hospitalCode=="0"){
			alert("Please select Hospital");
			return false;
		}
		var unit = document.getElementsByName("unit")[0].value;
		var wardCode = document.getElementsByName("wardCode")[0].value;

		if(wardCode!="-1")
		{ 
			var objRoomList = showRoomAjaxResponse(hospitalCode,unit,wardCode);
			var str='<html:select name="wardCensusReportFB"  property="roomCode"  tabindex="1"  styleClass ="regcbo"><html:option value="%">All</html:option>';   
			  	str=str+objRoomList+"</html:select>";
			document.getElementById("divRoomControl").innerHTML=str;
			
		}
	}
	
	function showWard()
	{
		var wardCode = document.getElementsByName("wardCode")[0].value;
		//document.getElementById("divWardControl")[0].innerHTML="";
		//PragyaDesigner.clearCombo(wardCode);
		//alert("Inside showWard()");
		var hospitalCode=document.getElementsByName("hospitalCode")[0].value;
		if(hospitalCode=="0"){
			alert("Please select Hospital");
			//var str1='<html:select name="wardCensusReportFB"  property="unit"  tabindex="1"  styleClass ="regcbo" onchange="showWard();"><html:option value="%">All</html:option></html:select>';
			document.getElementById("divUnitControl").innerHTML=str1;
			str2='<html:select name="wardCensusReportFB"  property="wardCode"  tabindex="1"  styleClass ="regcbo" onchange="showRoom();"><html:option value="%">All</html:option></html:select>';
		  	document.getElementById("divWardControl").innerHTML=str2;
		  	
			return false;
		}
		var unit = document.getElementsByName("unit")[0].value;
		var departmentCode = document.getElementsByName("departmentCode")[0].value;
		var str;
		if(departmentCode!="-1" && unit!="-1")
		{ 
			var objWardList = showWardAjaxResponse(hospitalCode,departmentCode,unit);
			str='<html:select name="wardCensusReportFB"  property="wardCode"  tabindex="1"  styleClass ="regcbo" onchange="showRoom();"><html:option value="%">All</html:option>';   
			  	str=str+objWardList+"</html:select>";
			
			
		}
		
		else{
			str='<html:select name="wardCensusReportFB"  property="wardCode"  tabindex="1"  styleClass ="regcbo" onchange="showRoom();"><html:option value="%">All</html:option></html:select>';
			
		}
		
		document.getElementById("divWardControl").innerHTML=str;
		var str3='<html:select name="wardCensusReportFB"  property="roomCode"  tabindex="1"  styleClass ="regcbo"><html:option value="%">All</html:option></html:select>';
		document.getElementById("divRoomControl").innerHTML=str3;
	}
	
	function showUnitWard()
	{
		var unit = document.getElementsByName("unit")[0];
		//PragyaDesigner.clearCombo(unit);
		var hospitalCode=document.getElementsByName("hospitalCode")[0].value;
		if(hospitalCode=="0"){
			//alert("Please select Hospital");
			var str1='<html:select name="wardCensusReportFB"  property="unit"  tabindex="1"  styleClass ="regcbo" onchange="showWard();"><html:option value="%">All</html:option></html:select>';
			document.getElementById("divUnitControl").innerHTML=str1;
			return false;
		}
		var departmentCode = document.getElementsByName("departmentCode")[0].value;

		if(departmentCode!="-1")
		{ 
			var objUnitList = showUnitAjaxResponse(hospitalCode,departmentCode);
			var str='<html:select name="wardCensusReportFB"  property="unit"  tabindex="1"  styleClass ="regcbo" onchange="showWard();"><html:option value="%">All</html:option>';   
			  	str=str+objUnitList+"</html:select>";
			document.getElementById("divUnitControl").innerHTML=str;
			//alert("Before calling showWard()");
			showWard();
		}
	}

	/*************************************** AJAX Functions ***************************/
	//Gettting List of Unit
	function showUnitAjaxResponse(hospitalCode,departmentCode)
	{
		var flg = false;
		var objUnitList = null;
		var reportMode = "GETALLUNIT_AJAX";
		//alert( "/HISClinical/mrd/report/wardCensusReport.cnt?reportMode="+reportMode+"&hospitalCode="+hospitalCode+"&departmentCode="+departmentCode);
		var objXHR = {url:"/HISClinical/mrd/report/wardCensusReport.cnt?reportMode="+reportMode+"&hospitalCode="+hospitalCode+"&departmentCode="+departmentCode, sync:true, postData: "", handleAs: "text",
			load: function(data) 
			{
				objUnitList = data;
				flg = true;
			},
	   error: function(error)
	   {
		   objUnitList = null;
	       flg = false;
	   }};

		var objDojoAjax = dojo.xhrPost(objXHR);
		//alert("objConsultantList :"+objConsultantList);
		return objUnitList;
	}	
	
	//Gettting List of Ward based on Dept Unit
	function showWardAjaxResponse(hospitalCode,departmentCode,unit)
	{
		var flg = false;
		var objWardList = null;
		var reportMode = "GETALLWARD_AJAX";
		//alert( "/HISClinical/mrd/report/wardCensusReport.cnt?reportMode="+reportMode+"&hospitalCode="+hospitalCode+"&departmentCode="+departmentCode+"&unit="+unit);
		var objXHR = {url:"/HISClinical/mrd/report/wardCensusReport.cnt?reportMode="+reportMode+"&hospitalCode="+hospitalCode+"&departmentCode="+departmentCode+"&unit="+unit, sync:true, postData: "", handleAs: "text",
			load: function(data) 
			{
				objWardList = data;
				flg = true;
			},
	   error: function(error)
	   {
		   objWardList = null;
	       flg = false;
	   }};

		var objDojoAjax = dojo.xhrPost(objXHR);
		//alert("objConsultantList :"+objConsultantList);
		return objWardList;
	}
	
	//Gettting List of Ward based on Dept Unit
	function showRoomAjaxResponse(hospitalCode,unit,wardCode)
	{
		var flg = false;
		var objRoomList = null;
		var reportMode = "GETALLROOM_AJAX";
		//alert( "/HISClinical/mrd/report/wardCensusReport.cnt?reportMode="+reportMode+"&hospitalCode="+hospitalCode+"&unit="+unit+"&wardCode="+wardCode);
		var objXHR = {url: "/HISClinical/mrd/report/wardCensusReport.cnt?reportMode="+reportMode+"&hospitalCode="+hospitalCode+"&unit="+unit+"&wardCode="+wardCode, sync:true, postData: "", handleAs: "text",
			load: function(data) 
			{
				objRoomList = data;
				flg = true;
			},
	   error: function(error)
	   {
		   objRoomList = null;
	       flg = false;
	   }};

		var objDojoAjax = dojo.xhrPost(objXHR);
		//alert("objConsultantList :"+objConsultantList);
		return objRoomList;
	}
</script>

<his:TitleTag name="Ward Census Report">
</his:TitleTag>

         <%
			  String deptlabel="" ;
        	  String deptcontrol="" ;
              String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              
         %>


      
      <bean:define name="wardCensusReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="wardCensusReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
	   <%
         if(frDate==null||frDate.equalsIgnoreCase(""))
         {  
         	System.out.println("dt::::::::::::::::");        	
        	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
        	System.out.println("dt:::::::::"+dt);        	
        	frDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
  
		  if(tDate==null||tDate.equalsIgnoreCase(""))
		  {  
		   	System.out.println("dt::::::::::::::::");        	
		  	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
		  	System.out.println("dt:::::::::"+dt);        	
		  	tDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		   }
    	%>  
    	

<his:SubTitleTag name="Report Details">
</his:SubTitleTag>   

<his:ContentTag>
	<table width="100% cellspacing="1" cellpadding="0">
		<tr>
			<!-- New Change add Hospital Combo -->
		      <td width="25%" class="tdfonthead">
					<div align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="hospital" /> </font>
					</div>	
					</td>
					
				<td class="tdfont" width="25%">
					<div >
					<html:select name="wardCensusReportFB" property="hospitalCode" tabindex="1" onchange="clearCombo();showUnitWard();">
						<html:option value="0">Select Value</html:option>
						<logic:present name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
							<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>"
								property="value" labelProperty="label" />
						</logic:present>
					</html:select></div>
					
				</td>
				<td class="tdfonthead" width="25%" colspan="2"></td>
		</tr>
		<tr>
 			<td class="tdfonthead" width="25%">
        		<div id='divfromDate' style='<%=fromDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate"/>
					</font>
        		</div>
        		
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
					<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
		 	</td>
				
			<td class="tdfonthead" width="25%">
    			<div id='divtoDate' style='<%=toDateLabel %>' align="right">
         			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="toDate"/>
		 			</font>
    			</div>
    		</td>
		
			<td class="tdfont" width="25%">
		 		<div id='divtoDateControl' style='<%=toDateControl %>' align="left">    
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>" />
    	 		</div>
    	  	</td>                 			
 		</tr>
 		<tr>
 			<td class="tdfonthead">
      			<div id ="deptLabel"  align="right"  style='<%=deptlabel%>'>
		        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="department"/>
				  </font>
      			</div>
    		</td>
    		<td class="tdfont">
       			<div id="deptControl" align="left"  style='<%=deptcontrol%>'>
     		 		<html:select name="wardCensusReportFB"  property="departmentCode"  tabindex="1"  styleClass ="regcbo" onchange="showUnitWard()">   
					  <html:option value="%">All</html:option>
					  <logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" >
					  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property = "value" labelProperty = "label"/>
					  </logic:present>
			  		</html:select> 
	   			</div>       
    		</td>
 	
    		<td class="tdfonthead">
    			<div id="unitLabel" align="right" >
    				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="unit"/>
				  </font>
    			</div>
    		</td>
    		<td class="tdfont">
    			<div id="divUnitControl" align="left" >
    				<html:select name="wardCensusReportFB"  property="unit"  tabindex="1"  styleClass ="regcbo" >
						<html:option value="%">All</html:option>
						<%-- <logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" >
						  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT%>" property = "value" labelProperty = "label"/>
						</logic:present> --%>
					</html:select>
				</div>			  
    		</td>
 		</tr>
 		
 		<tr>
 			<td class="tdfonthead" width="25%">
 				<div id="divPat" style="display: block;" align="right">
 					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="ward"/>
		 			</font>
 				</div>
 			</td>
 			<td class="tdfont" width="25%">
 				<div id="divWardControl" style="display: block;" align="left">
 					<html:select name="wardCensusReportFB" property="wardCode" tabindex="1"  styleClass ="regcbo">
 						<html:option value="%">All</html:option> 						
 					</html:select>
 				</div>
 			</td>
 			
 			<td class="tdfonthead" width="25%">
 				<div id="divPat" style="display: block;" align="right">
 					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="room"/>
		 			</font>
 				</div>
 			</td>
 			<td class="tdfont" width="25%">
 				<div id="divRoomControl" style="display: block;" align="left">
 					<html:select name="wardCensusReportFB" property="roomCode" tabindex="1"  styleClass ="regcbo">
 						<html:option value="%">All</html:option> 						
 					</html:select>
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
        	<td width="25%" class="tdfonthead">
        		<font color="#FF0000">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          		<bean:message key="reportType"/>      
	        	</font>
        	</td>                
        	<td width="25%" class="tdfont" > 
	        	<html:select name="wardCensusReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
		        	<html:option value="-1">Select Value</html:option>
		        	<html:option value="<%=Config.TEXT%>">Textual</html:option>
				</html:select> 		
        	</td>
        	<td width="25%" class="tdfonthead">
        		<font color="#FF0000">*</font>
	        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          		<bean:message key="pdfOrWord"/>      
	        	</font>
        	</td>
        	<td width="25%" class="tdfont">
        		<html:select name="wardCensusReportFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	</td>
    	</tr>    	
	</table>        
</his:ContentTag>
<his:status/>

<html:hidden name="wardCensusReportFB" property="reportMode" />
<html:hidden name="wardCensusReportFB" property="mode" value="WARDCENSUSREPORT" />
<html:hidden name="wardCensusReportFB" property="jrxmlPath" value="<%=MrdConfig.MRD_JRXML_PATH%>"/>
<html:hidden name="wardCensusReportFB" property="jrxmlName" value="<%=MrdConfig.MRD_WARD_CENSUS_REPORT_DATEWISE %>"/>
<html:hidden name="wardCensusReportFB" property="sysDate" value="<%=tDate%>"/>
<html:hidden name="wardCensusReportFB" property="strDeptName" />
<html:hidden name="wardCensusReportFB" property="strUnitName" />


