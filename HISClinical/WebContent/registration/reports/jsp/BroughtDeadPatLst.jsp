<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function showpdfrtfdiv(){

  val=document.getElementsByName("reportMode")[0].selectedIndex;
  alert("val::::::::"+val);
  if(val==1){
  	document.getElementById("divrtfpdf").style.display="";
 	document.getElementById("mydiv").style.display="";  	
  }
  if(val==2){
  }
}
</script>


<his:TitleTag name="Brought Dead Patient Report">

</his:TitleTag>
<his:SubTitleTag name="Report Details"> </his:SubTitleTag>	

<his:ContentTag>	
   <table width="100% cellspacing="1" cellpadding="0">
    <tr>
     <td class="tdfont" width="18%">
        <div id='divfromDate' style='' align="left">
        <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:message key="fromDate"/></font>
         </div>
    </td>
        
    <td class="tdfonthead" width="18%">
	    <div id='divfromDateControl' style=''align="left">	            
			<his:date name='fromDate' dateFormate="%d-%b-%Y"/>
		</div>
    </td>
				
    <td class="tdfont" width="18%">
    <div id='divtoDate' style='' align="left">
         <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
         <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 <bean:message key="toDate"/>
		 </font>
         </div>
    </td>
		
    <td class="tdfonthead" width="18%">
		 <div id='divtoDateControl' style='' align="left">         
			<his:date name='toDate' dateFormate="%d-%b-%Y"/>
    	 </div>
    </td>                 
   </tr>
   </table>
</his:ContentTag>
<his:SubTitleTag name="Report generation options"> </his:SubTitleTag>
  <his:ContentTag>
   <table width="100%" cellspacing="1" cellpadding="0">
       <tr>       
        <td width="10%" class="tdfonthead">
	        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          <bean:message key="reportType"/>      
	        </font>
        </td>                
        <td width="10%" class="tdfont" > 
	        <html:select name="broughtDeadPatLstFB"  property="reportMode" tabindex="1" styleClass="regcbo" onchange='showpdfrtfdiv()'>
		        <html:option value="-1">Select Value</html:option>
		        <html:option value="<%=Config.TEXT%>">Textual</html:option>
		        <html:option value="<%=Config.GRAPH%>">Graphical</html:option>	    
			</html:select> 		
        </td>
        
        <td width="10%" class="tdfonthead">
		    <div id='mydiv' style='display:none'  align="right">
		    <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
		    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       <bean:message key="pdfOrWord"/>
	        </font>
	        </div>
       </td>               

	   <td class="tdfonthead" nowrap width="13%">       
	       	  <div id='divrtfpdf' style='display:none'  align="right">
		      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			     <html:select name="broughtDeadPatLstFB"  property="reportType" tabindex="1" styleClass="regcbo">
		        <html:option value="-1">Select Value</html:option>
		        <html:option value="<%=Config.PDF%>">Acrobat Reader</html:option>
		        <html:option value="<%=Config.RTF%>">HTML</html:option>	    
			</html:select> 		
		      </font>		      
     		</div>
	   </td> 
</table>
</his:ContentTag> 

<input type="hidden" name="mode" value="BROUGHTDEADPATIENTREPORT">
<html:hidden name="broughtDeadPatLstFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH %>"/>
<html:hidden name="broughtDeadPatLstFB" property="jrxmlName"/>
