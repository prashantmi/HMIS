<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,inpatient.*,hisglobal.presentation.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="mrd.MrdConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript  src="/registration/js/reportsValidation.js"/>
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/masterXml.css" />

<script>
 
function submitPage(mode)
{
	elmt=document.getElementsByName("reportMode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function showDate(){ 
if (document.forms[0].status.value=="2" || document.forms[0].status.value=="1"){
 document.getElementById("showDate").style.display="block";
	}
	else{
	    document.getElementById("showDate").style.display="none";
	
}
}
function ListOfCaseSheetReportHandler()
{
	if(document.getElementsByName('status')[0].value=="%")
		{
			document.getElementsByName('jrxmlName')[0].value="<%=InpatientConfig.LIST_OF_CASESHEET_ALLSTATUS_REPORT %>";
		}
	if(document.getElementsByName('status')[0].value=="2" ){
	
		document.getElementsByName('jrxmlName')[0].value="<%=InpatientConfig.LIST_OF_CASESHEET_LOSTORFOUND_REPORT %>";
		}
		if(document.getElementsByName('status')[0].value=="0" ){
		
			document.getElementsByName('jrxmlName')[0].value="<%=InpatientConfig.LIST_OF_CASESHEET_REPORT %>";
			}
			if( document.getElementsByName('status')[0].value=="1"){
		
			document.getElementsByName('jrxmlName')[0].value="<%=InpatientConfig.LIST_OF_LOSTCASESHEET_REPORT %>";
			}
	if(validateGraphOrText())
    { 
		if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
		{
	    	document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
	        if(validateTextual())
	        {      
	        	
	           		return true;           	   
	           	          	  
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
}

</script>

<his:TitleTag name="List Of Case Sheet Report">
</his:TitleTag>

          <%
			  String fromDateLabel="" ;
              String toDateLabel="" ;
              String fromDateControl="" ;
              String toDateControl="" ;
               
         %>
        
      <bean:define name="ListOfCaseSheetFB" property="fromDate" id="frDate" type="java.lang.String"/>
	  <bean:define name="ListOfCaseSheetFB" property="toDate" id="tDate" type="java.lang.String"/>          
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

  	<table width="100%" cellspacing="1" cellpadding="0">
		    	<tr>
		    	
				<td class="tdfonthead" width="25%">
	 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="unit"/></font>
				</td>
				<td class="tdfont" width="25%" >
	                <html:select name="ListOfCaseSheetFB" property="unitCode"  style="width:160;"  onchange="submitPage('UNIT')">
			      	<html:option value="%">All</html:option>
			      	<logic:present name="<%=MrdConfig.MRD_DEPT_UNIT_LIST%>" >
			       	<html:options collection="<%=MrdConfig.MRD_DEPT_UNIT_LIST%>" property="value"  labelProperty="label"></html:options>
			       	</logic:present>
				</html:select>
		</td>	 
				<td class="tdfonthead" width="25%">	
					
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="ward"/></font>
					</td>
					<td class="tdfont" width="25%">
					
				<html:select name="ListOfCaseSheetFB"  property="wardCode"  style="" tabindex="1" styleClass="regcbo" >
		        	<html:option value="%">All</html:option>
		        	<logic:present name="<%=MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT%>" >
		        	<html:options collection="<%=MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT%>" property="value" labelProperty="label"></html:options>
		        	</logic:present>
				</html:select>  
			</td>
					</tr>
						<tr>
		    	
				<td class="tdfonthead" width="25%">
	 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="caseSheetStatus"/></font>
				</td>
				<td class="tdfont" width="25%" >
	                <html:select name="ListOfCaseSheetFB" property="status"  style="width:160;"  onchange="showDate()">
			      	<html:option value="%">All</html:option>
			      	<logic:present name="<%=InpatientConfig.CASESHEET_STATUS_FOR_REPORT_LIST%>" >
			       	<html:options collection="<%=InpatientConfig.CASESHEET_STATUS_FOR_REPORT_LIST%>" property="value"  labelProperty="label"></html:options>
			       	</logic:present>
				</html:select>
		</td>	
		<td class="tdfonthead" width="25%">
		</td>
		<td class="tdfont" width="25%">
		</td>
		</tr>
			</table>
			
			 <div id="showDate" style="display:none;">	
		<his:ContentTag>	
	      <table width="100%" cellspacing="1" cellpadding="0">
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
        		<div id='divfromDate' style='<%=toDateLabel %>' align="right">
	        		<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate"/>
					</font>
        		</div>
			</td>
			<td class="tdfont" width="25%">
	    		<div id='divfromDateControl' style='<%=toDateControl %>'align="left">	               		 
					<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>"/>
				</div>
		 		
			</td>
			
 		</tr>
 	</table> 	
	</his:ContentTag>
	</div> 	
				
        	<table width="100%" cellspacing="1" cellpadding="0">
    <tr>   
		<td width="25%" class="tdfonthead">
        	<font color="#FF0000">*</font>
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          	<bean:message key="reportType"/>      
	        </font>
        </td>                
        <td width="25%" class="tdfont" > 
	        <html:select name="ListOfCaseSheetFB"  property="graphOrText" tabindex="1" styleClass="regcbo" >
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
        		<html:select name="ListOfCaseSheetFB" property="reportType" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>        	
					<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
					<html:option value="<%=Config.RTF %>">HTML</html:option>
        		</html:select>
        	</td>
    	</tr>    	
	</table>        
</his:ContentTag>
<his:status/>



<input type="hidden"  name="reportMode">
<input type="hidden"  name="mode" value="LISTOFCASESHEETREPORT">
<html:hidden name="ListOfCaseSheetFB" property="jrxmlPath" value="<%=InpatientConfig.INPATIENT_JRXML_PATH%>"/>
<html:hidden name="ListOfCaseSheetFB" property="jrxmlName"/>
<html:hidden name="ListOfCaseSheetFB" property="sysDate" value="<%=tDate%>"/>

