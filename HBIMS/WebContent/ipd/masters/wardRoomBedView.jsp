<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head><meta charset="utf-8" />
<title>Ward Room Bed Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>

<script type="text/javascript">
function validate1()
{
				document.forms[0].hmode.value = "VIEW";
				document.forms[0].submit();
}
</script>
</head>
<body onload="document.forms[0].strBedName2.focus();">
   <html:form name="wardroombedBean" action="/masters/CNTWardRoomBedMst" type="ipd.masters.VOWardRoomBedMst">
	
<div class="errMsg"><bean:write name="wardroombedBean" property="strErr"/></div>
	
	<tag:tab tabLabel="Modify Room Config" selectedTab="FIRST" align="center" width="75%">
	</tag:tab>
	
<table class="TABLEWIDTH" align="center">
  <tr class="HEADER"> 
    <td colspan="2">Ward Room Bed Config Master&gt;&gt; VIEW</td>
  </tr>

  <tr> 
    <td width="50%" class="LABEL">Ward Name</td>
    <td width="50%" class="CONTROL">
    	<bean:write name="wardroombedBean" property="strWardName" filter="false"/>
    </td>
  </tr>
  <tr> 
    <td class="LABEL">Room Name</td>
    <td class="CONTROL">
	<bean:write property="strRoomName" name="wardroombedBean" filter="false"/>
	</td>
  </tr>
  <tr>
	   	<td class="LABEL">Bed Name</td>
		<td class="CONTROL"><bean:write name="wardroombedBean" property="strBedName2"/></td>
		</tr>
    <tr>
	   	<td class="LABEL">Bed Type</td>
		<td class="CONTROL">
		<bean:write property="strBedTypeName" name="wardroombedBean"/>
		</td>
	</tr>
	<tr>
	   	<td class="LABEL">Bed Status</td>
		<td class="CONTROL">
			<bean:write property="strBedStatusName" name="wardroombedBean"/>
		</td>
		 
	</tr>
     
    <tr >
    <td class ="LABEL" width ="50%"><font color="red">*</font>Effective From Date</td>
    <td class ="CONTROL">
   		<bean:write name="wardroombedBean" property="strDate"/>  
    </td>
  </tr>
   	<tr>
 	    	<td class="LABEL">Remarks</td>
			<td class="CONTROL"><textarea  name="strRemarks" cols="25" rows="2">
			<bean:write name="wardroombedBean" property="strRemarks"/>
			</textarea></td>
	</tr>
  
  
    
    <tr >
    <td width ="45%" class ="LABEL"><font color="red">*</font> Record Status </td>
    <td width ="45%" class ="CONTROL" >
  		<html:radio name="wardroombedBean" property="strValid" value="1">Active</html:radio>
    	<html:radio name="wardroombedBean" property="strValid" value="2">InActive</html:radio>
    </td>
    </tr>
    
   <tr class ="FOOTER" >
  <td colspan ="2" >&nbsp;</td>
  </tr>
</table>																														
	
	    <table CLASS ="TABLEWIDTH" align="center">
	      <tr> 
		
		   <td align="center">
		
		    <img  src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" />  

	   </td>
	      </tr>
	    </table>
    
         <input type="hidden" name="chk" value="${wardroombedBean.strChk} }">

	   	<input type="hidden" name="hmode">
	 	
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>	
