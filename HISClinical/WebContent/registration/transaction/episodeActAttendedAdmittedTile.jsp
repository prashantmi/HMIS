<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import ="registration.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:SubTitleTag name="Attended and Admitted">
</his:SubTitleTag>
<his:ContentTag>
    <table width="100%" cellpadding="1" cellspacing="1"  style="clear:both; border-left:1px solid #003366;">
     
      <tr> 
        <td class ="tdfonthead" width="17%" nowrap><div align="right">
        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
              <bean:message key="emergencyward"/>
        </font></div></td>
        <td class="tdfont" width="17%">        
       <html:select name="EpisodeAttendedAdmittedFB" property="episodeActionCode" tabindex="1" onchange="submitRegister('DTL4STATUS');">
           <html:option value="-1">Select Value</html:option><%--
        <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT_WITH_CASUALITY %>" property = "value" labelProperty = "label" />
        --%>
        </html:select>
        <td class="tdfonthead" width="17%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Bed</font></div></td>
        <td class = "tdfont" width="17%"><select name="cmoPatAttendedAdmittedBed" class="combosize">
          </select></td>
        <td class = "tdfonthead" width="16%">&nbsp;</td>
        <td class =  "tdfont" width="16%">&nbsp;</td>
      </tr>
      <tr> 
        <td class ="tdfonthead" width="17%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Remark</font></div></td>
        <td colspan="3" class="tdfont"> 
            <textarea name="cmoPatAttendedAdmittedRemark" class = "remarkText"></textarea>
          </td>
        <td class = "tdfonthead" width="16%">&nbsp;</td>
        <td class =  "tdfont" width="16%">&nbsp;</td>
      </tr> 
          
    </table>
</his:ContentTag>

 <html:hidden  name='EpisodeAttendedAdmittedFB' property='hmode' value="unspecified"/>
  <div id="divDiagnosisList" style=display:none>              
 	Empty div
 </div>

  <html:hidden  name='EpisodeAttendedAdmittedFB' property='removeRow' value=""/>