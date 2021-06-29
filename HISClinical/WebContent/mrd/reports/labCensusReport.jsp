<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,mrd.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
<his:css src="/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript  src="/registration/js/reportsValidation.js"/>
<%-- <his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js" /> --%>
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

	function showdivtoday()
	{	
		document.getElementById("divfrom").style.display="";
		document.getElementById("divfromhrcontrol").style.display="";
		document.getElementById("divfromMinControl").style.display="";
		document.getElementById("divto").style.display="";
		document.getElementById("divtohrcontrol").style.display="";
		document.getElementById("divtoMinControl").style.display="";
		document.getElementById("divfromDate").style.display="none";
		document.getElementById("divfromDateControl").style.display="none";
		document.getElementById("divtoDate").style.display="none";
		document.getElementById("divtoDateControl").style.display="none";
	}
	       
	function showdivdatewise()
	{
		document.getElementById("divfrom").style.display="none";
		document.getElementById("divfromhrcontrol").style.display="none";
		document.getElementById("divfromMinControl").style.display="none";
		document.getElementById("divto").style.display="none";
		document.getElementById("divtohrcontrol").style.display="none";
		document.getElementById("divtoMinControl").style.display="none";
		document.getElementById("divfromDate").style.display="";
		document.getElementById("divfromDateControl").style.display="";
		document.getElementById("divtoDate").style.display="";
		document.getElementById("divtoDateControl").style.display="";
	}

	function submitPage(mode)
	{
		//alert("inside submitPage");
		elmt=document.getElementsByName("reportMode")[0];  
	    elmt.value=mode;
	    document.forms[0].submit();
	}
	
	
	function labCensusReportHandler()
	{
		
		
		success=false;
		document.getElementsByName('strDeptName')[0].value=document.getElementsByName('departmentCode')[0].options[document.getElementsByName('departmentCode')[0].selectedIndex].text;
		
		if(document.getElementsByName('hospitalCode')[0].value==0)
		{
			alert("Please Select the Hospital");
			document.getElementsByName('hospitalCode')[0].focus();
			return false;
		}
		
		if(document.getElementsByName('choice')[0].checked){
	    	document.getElementsByName('jrxmlName')[0].value="<%=MrdConfig.MRD_LAB_CENSUS_REPORT_TODAY%>";	    	
		}else if(document.getElementsByName('choice')[1].checked){
			document.getElementsByName('jrxmlName')[0].value="<%=MrdConfig.MRD_LAB_CENSUS_REPORT_DATEWISE%>";
		}
		if(validateGraphOrText())
	    { 
			if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
			{
		    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
		        if(validateTextual())
		        {      
		        	if(validateTodayOrDate())
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
			    	if(validateTodayOrDate())        
			        success=true;	                
		        }
	        }//end of selection is for graph based report	 
	    }
		
		
		
		return success;
	}//end of function labCensusReportHandler()

	function showLab()
	{
		var labCode = document.getElementsByName("labCode")[0].value;
		//PragyaDesigner.clearCombo(unit);
		var hospitalCode=document.getElementsByName("hospitalCode")[0].value;
		if(hospitalCode=="0"){
			alert("Please select Hospital");
			labCode="%";
			return false;
		}
		var departmentCode = document.getElementsByName("departmentCode")[0].value;
		//alert("departmentCode :"+departmentCode);
		if(departmentCode!="-1")
		{ 
			var objLabList = showLabAjaxResponse(hospitalCode,departmentCode);
			var str='<html:select name="labCensusReportFB"  property="labCode"  tabindex="1"  styleClass ="regcbo"><html:option value="%">All</html:option>';   
			  	str=str+objLabList+"</html:select>";
			document.getElementById("divLabControl").innerHTML=str;
			
		}
	}

	/*************************************** AJAX Functions ***************************/
	//Gettting List of Unit
	function showLabAjaxResponse(hospitalCode,departmentCode)
	{
		var flg = false;
		var objLabList = null;
		var reportMode = "GETLABORATORYLIST_AJAX";
		//alert( "/HISClinical/mrd/report/wardCensusReport.cnt?reportMode="+reportMode+"&hospitalCode="+hospitalCode+"&departmentCode="+departmentCode);
		var objXHR = {url: "/HISClinical/mrd/report/labCensusReport.cnt?reportMode="+reportMode+"&hospitalCode="+hospitalCode+"&departmentCode="+departmentCode, sync:true, postData: "", handleAs: "text",
			load: function(data) 
			{
				objLabList = data;
				flg = true;
			},
	   error: function(error)
	   {
		   objLabList = null;
	       flg = false;
	   }};

		var objDojoAjax = dojo.xhrPost(objXHR);
		return objLabList;
	}
</script>

<his:TitleTag name="Lab Census Report">
</his:TitleTag>

         <%
         	  String deptlabel="" ;
   	  		  String deptcontrol="" ;
              String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
              String fromLabel="" ;
              String toLabel="" ;
              String fromControl="" ;
              String toControl="" ; 
         %>
		<logic:equal name="labCensusReportFB" property="choice" value="<%=Config.CHOICE_TODAY%>">
        <%
               fromDateLabel="display:none" ;
               toDateLabel="display:none" ;
               fromDateControl="display:none" ;
               toDateControl="display:none" ;
               fromLabel="" ;
               toLabel="" ;
               fromControl="" ;
               toControl="" ;  
        %>
		</logic:equal>  

		<logic:equal name="labCensusReportFB" property="choice" value="<%=Config.CHOICE_DATE_WISE%>">
        <%     
        	   fromDateLabel="" ;
               toDateLabel="" ;
               fromDateControl="" ;
               toDateControl="" ;
               fromLabel="display:none" ;
               toLabel="display:none" ;
               fromControl="display:none" ;
               toControl="display:none" ;  
        %>
		</logic:equal> 
      
      <bean:define name="labCensusReportFB" property="fromDate" id="frDate" type="java.lang.String"/>
	   <bean:define name="labCensusReportFB" property="toDate" id="tDate" type="java.lang.String"/>          
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
 			<td class="tdfonthead" nowrap width="25%">       	      
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      		<bean:message key="today"/>
		      	</font>
		      		<html:radio name="labCensusReportFB" property="choice" tabindex="1" value="<%=Config.CHOICE_TODAY%>" onclick="showdivtoday()"/>
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       		<bean:message key="dateWise"/>
		       	</font> 
		        	<html:radio name="labCensusReportFB" property="choice" tabindex="1" value="<%=Config.CHOICE_DATE_WISE%>" onclick="showdivdatewise()"/>
			</td> 
			<td width="25%" class="tdfont"></td>
			<td width="25%" class="tdfonthead" colspan="2"></td>
		</tr>
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
					<html:select name="labCensusReportFB" property="hospitalCode" tabindex="1" onchange="showLab();">
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
        		<div id='divfrom' style='<%=fromLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="from"/>
					</font>
         		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=fromDateControl %>'align="left">	               		 
					<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>"/>
				</div>
		 		<span id='divfromhrcontrol' style='<%=fromControl %>'align="left">	            
		   			<html:text name="labCensusReportFB" tabindex="1" property="fromHour"  maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)" />	
		  			 <b>	<bean:message key="colon"/></b>
				</span>
		 		<span id='divfromMinControl' style='<%=fromControl %>' align="left">         
					<html:text name="labCensusReportFB" tabindex="1" property="fromMin"   maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
						<bean:message key="timeFormat"/>
    			</span>
		
			</td>
				
			<td class="tdfonthead" width="25%">
    			<div id='divtoDate' style='<%=toDateLabel %>' align="right">
         			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 				<bean:message key="toDate"/>
		 			</font>
    			</div>
    			<div id='divto' style='<%=toLabel %>' align="right">
        			<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
        			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="to"/>
					</font>
         		</div>
			</td>
		
			<td class="tdfont" width="25%">
		 		<div id='divtoDateControl' style='<%=toDateControl %>' align="left">    
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>" />
    	 		</div>
    	  		<span id='divtohrcontrol' style='<%=toControl%>' align="left">	            
		   			<html:text name="labCensusReportFB" tabindex="1" property="toHour"  maxlength="2" size="3" onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>	
	   				<b><bean:message key="colon"/>	  </b>
				</span>
		 		<span id='divtoMinControl' style='<%=toControl%>' align="left">         
					<html:text name="labCensusReportFB" tabindex="1" property="toMin" maxlength="2" size="3"  onkeydown="setPreviosValue(this, event)" onkeyup="moveToRightBox(this, event)" onkeypress="return validateNumeric(event)"/>				
						<bean:message key="timeFormat"/>	  
    	 		</span>
			</td>                 			
 		</tr>
		<tr>
 			<td class="tdfonthead">
      			<div id ="deptLabel"  align="right"  style='<%=deptlabel%>'>
		        	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		<bean:message key="department"/>
				  </font>
      			</div>
    		</td>
    		<td class="tdfont">
       			<div id="deptControl" align="left"  style='<%=deptcontrol%>'>
     		 		<html:select name="labCensusReportFB"  property="departmentCode"  tabindex="1"  styleClass ="regcbo" onchange="showLab();">   
					  <html:option value="%">All</html:option>
					  <logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" >
					  <html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property = "value" labelProperty = "label"/>
					  </logic:present>
			  		</html:select> 
	   			</div>       
    		</td>
    		<td class="tdfonthead">
      			<div id ="deptLabel"  align="right"  style='<%=deptlabel%>'>
		        	<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  		Laboratory
				  </font>
      			</div>
    		</td>
    		<td class="tdfont">
       			<div id="divLabControl" align="left"  style='<%=deptcontrol%>'>
     		 		<html:select name="labCensusReportFB"  property="labCode"  tabindex="1"  styleClass ="regcbo">   
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
	        	<html:select name="labCensusReportFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
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
        		<html:select name="labCensusReportFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	</td>
    	</tr>    	
	</table>        
</his:ContentTag>
<his:status/>

<html:hidden name="labCensusReportFB" property="reportMode" />
<html:hidden name="labCensusReportFB" property="mode" value="LABCENSUSREPORT" />
<html:hidden name="labCensusReportFB" property="jrxmlName" />
<html:hidden name="labCensusReportFB" property="sysDate" value="<%=tDate%>"/>
<html:hidden name="labCensusReportFB" property="jrxmlPath" value="<%=MrdConfig.MRD_JRXML_PATH%>"/>
<html:hidden name="labCensusReportFB" property="strDeptName" />
