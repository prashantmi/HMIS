
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/time.js"/>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function populate(selectedarray){
  //alert("length:::"+selectedarray.length);  
  var strHtml="";
  elem =	document.getElementById("divDiagnosisList");          
         
  for(i=0;i<selectedarray.length;i++){
    arr= selectedarray[i].split("|");                                    
   strHtml=strHtml+ "<input type='hidden' name='diagnosticCode' value='"+arr[0]+"'/><input type='hidden' name='diagnosticName' value='"+arr[1]+"'/>";           
   }  
      elem.innerHTML= strHtml;
      //alert(strHtml); 
      //alert(elem.innerHTML);
      submitTile("DGNDETAIL");      
}  
function deleteRow(idx){
 //alert("inside delete row");
	document.getElementsByName("removeRow")[0].value = idx;
	submitTile("REMOVEROW");
}

function submitTile(mode)
{
 document.getElementsByName("hmode")[0].value=mode
 //alert("hmode"+document.getElementsByName("hmode")[0].value)
 document.forms[0].submit();
  } 
 
 function validateTile()
  {
 var valid=true;


<%
Object[] diagnosis = (Object[])session.getAttribute(RegistrationConfig.ARR_SELECTED_DIAGNOSIS_CODE);
int isDiagnosis = -1;
String strIsDiagnosis="-1";
if(diagnosis != null)
	{
	isDiagnosis = diagnosis.length;
	strIsDiagnosis=String.valueOf(isDiagnosis);
	}
%>
	//<%=diagnosis%>
 	var diagnosis = <%=(strIsDiagnosis.equals("-1"))?null:strIsDiagnosis%>;
 if(validateNullObjects(diagnosis,'Diagnostic Details'))
	valid=true;
	else
	valid=false;
  	return valid;
  }
</script>
<his:SubTitleTag name="Attended Treated and Disposed Off">
</his:SubTitleTag>

  
 
 <div id = "att_tret_disposed" >
 <his:ContentTag>
    <table width="100%" border="0" cellspacing="1" cellpadding="1">
  <tr>
	      <td width="25%"  class="tdfonthead">      
			<div align="right">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000">*</font>
			<bean:message key="date"/>
			</font></div>
			</td>
	        <td width="25%" class="tdfont">        
	        <bean:define name ="EpisodeAttDisposedFB" property="episodeActionDate" id="date" type="java.lang.String"/>	        
	        <his:date name='episodeActionDate' dateFormate="%d-%b-%Y" value="<%=date%>"/>
	        </td>               
	        <td width="25%" class="tdfonthead"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><font color="#FF0000">*</font>
	        <bean:message key="time"/></font></div></td>
	        <td width="25%" class="tdfont">
	           <html:text name="EpisodeAttDisposedFB" property="episodeActionTime" tabindex="1" onkeypress="checkTime(event,this);" onblur="if(this.value.length!=0 && this.value.length!=5) {alert('Use Time Format HH:MM 24Hr');this.focus();}" maxlength="5"/><font color="#FF0000"> Time Format HH:MM 24Hr</font>
	        </td>
      </tr>           
    
            <tr> 
		     <td class="tdfonthead" width = "25%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       <font color="#FF0000">*</font> <bean:message key="remarks"/></font></div></td>
		        <td class="tdfont" width="25%">
		        <html:textarea styleClass="textarea2" tabindex="1" onkeypress="return CheckMaxLength(event,this,300)" name="EpisodeAttDisposedFB" property="remarks"  />
		        
             </td>            
				<td class="tdfonthead" width = "25%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			        <bean:message key="complaintdtl"/></font></div></td>
			        <td class="tdfont" width="25%">
			        <html:textarea styleClass="textarea2" tabindex="1" name="EpisodeAttDisposedFB" property="complainDtl"  />		        
                </td>   
            </tr>            
            
          </table>   
          </his:ContentTag>
          
        <his:SubTitleTag name="Provisional Diagnosis">
        <img class="button" src='<his:path src="/hisglobal/images/btn-fwd.png"/>'onkeypress="if(event.keyCode==13) openPopup('<his:path src="/registration/episodeActPopup.cnt"/>',event,300,600);" onclick="openPopup('<his:path src="/registration/episodeActPopup.cnt"/>',event,300,600);" tabindex="1">  
        </his:SubTitleTag>
        
  <bean:define name="EpisodeAttDisposedFB" property="diagnosticName" id="arr" type="java.lang.String[]"/> 
  <bean:define name="EpisodeAttDisposedFB" property="diagnosticCode" id="arrcode" type="java.lang.String[]"/> 
  
   <his:ContentTag>
    <table width="100%" border="0" cellspacing="1" cellpadding="1">
  
  <%
  for(int i=0;i<arr.length;i++){
	  System.out.println("value::::::::::"+arr[i]);	  
	  %>
	  
	  <tr> 
    <td width="21%" class="tdfonthead">
     <div align="center">
    	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">    	     	         
    	  <%=arrcode[i] %>
    	  </font>
	 </div>    
    </td>	  
   <td width="50%"  class="tdfont">
    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">    	     	         
    	  <%=arr[i] %>
    	  </font>
   </td>
   <td class="tdfonthead" width="10%">
<input type="image" border="0" style="cursor:pointer" img class='button' src='<his:path src="/hisglobal/images/minus.gif"/>' onClick="deleteRow(<%=i%>)"  tabindex='1'>
</td>
   </tr>
 
      <%
  }  
  %>     
 </table>      
 </his:ContentTag>   

  </div>
  <html:hidden  name='EpisodeAttDisposedFB' property='hmode' value="unspecified"/>
  <div id="divDiagnosisList" style=display:none>              
 	Empty div
 </div>
 
  <html:hidden  name='EpisodeAttDisposedFB' property='removeRow' value=""/>
 
       