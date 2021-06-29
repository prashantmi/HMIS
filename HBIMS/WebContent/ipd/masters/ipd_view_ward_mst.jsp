<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<html>
<head>
<meta charset=utf-8>
<title>Ward Master view Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function checkMsApproval()
	{
		if(document.forms[0].strmsapproveRequired.value==1)
		{
			var ob=document.getElementById("divWaiting");
			ob.style.display="block";
		}
		else
		{
			document.forms[0].strmsapproveRequired.checked=false;
		}
	}
</script>
</head>
<body onload="checkMsApproval()" >
<html:form action="/masters/CNTWardMst">
	<div class="normalMsg" id="normalMsg"></div>  
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td height="29" colspan="4">&nbsp;<b>Ward Master</b></td>
		</tr>		
		<tr>			
			<td width="18%" class="LABEL" nowrap>Ward Name</td>
			<td  colspan="3" class="CONTROL">
			<bean:write name="wardBean" property="strWardName"/></td>
		</tr>
		<tr>
			<td class="LABEL" width="18%">Ward Type</td>
			<td   class="CONTROL" width="18%"><bean:write name="wardBean" property="strWardType"/>
			</td>
			<td class="LABEL" nowrap width="18%">No Of Beds</td>
			<td class="CONTROL"  width="18%">
			<bean:write name="wardBean" property="strNoOfBed"/>
			</td>
		</tr>
		<tr>
			<td width="18%" class="LABEL">Building</td>
			<td class="CONTROL"><bean:write name="wardBean" property="strBuildingName"/>
			</td>
			<td class="LABEL" width="18%">Block</td>
			<td class="CONTROL"  width="18%"><bean:write name="wardBean" property="strBlockName"/>
			</td>
		</tr>
		<tr>
		<td width="20%" class="LABEL" nowrap colspan="1">Max No. of Patient Per Bed</td>
		<td colspan="5" class="CONTROL">
		<bean:write name="wardBean" property="strMaxPatPerBed"/>
		</td>
	   </tr>
        <tr>
			<td class ="LABEL" >Booking Allowed </td>
			<td class="CONTROL" colspan="3"><bean:write name="wardBean" property="strbookingAllowed"/>
			  <strong>%</strong>&nbsp;<bean:write name="wardBean" property="strmoreorless"/>
		 	of to be Vacant beds.
			</td>
		</tr>
		<tr>
		<td class ="LABEL" colspan="5">Ms Approval Required
		 <html:checkbox name ="wardBean" property ="strmsapproveRequired" disabled="true" value="${wardBean.strmsapproveRequired}" /></td>
		</tr>
</table>
<div id ="divWaiting" style="display:none">
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td  class ="CONTROL" colspan="5">
			Max No of Patient in waiting list&nbsp;<bean:write name="wardBean" property="strmaxNoWaitingList"/>
	 	</tr>
	</table>
</div>
 <table CLASS="TABLEWIDTH" align="center" cellspacing="1px">
	<tr class="HEADER">	
		<td class="colspan="2"><b>Room Name</b></td>
	</tr>
	<tr>
			<td colspan="1" class="CONTROL">
			<select name="strRroom" multiple="multiple" style="border:thin;width: 100%;background-color: #F1ECE2"> 
			<bean:write name="wardBean" property="strRRoomModi" filter="false"/></select>	
		</td>
	</tr>
	</table>		
	<table class= "TABLEWIDTH"align="center" cellspacing="1px">
		<tr>
			<td class="LABEL" nowrap width ="18%">Effective Date</td>
			<td class="CONTROL" width ="18%%">
			<bean:write property="strEffectiveFrom" name="wardBean"/>			
		  </td>
			<td class="LABEL" nowrap width ="18%">Effective To</td>
			<td class="CONTROL" width ="18%%">
			<bean:write property="strEffectiveTo" name="wardBean"/>
         </td>
		</tr>
		<tr>
			<td width="18%" class="LABEL">Remarks</td>
			<td colspan="3" class="CONTROL">
			<textarea  name="strRemark" cols="25" rows="2"  readonly style="background-color: #F1ECE2;border: thin"><bean:write property="strRemark" name="wardBean"/></textarea></td>
		</tr>
		<tr class="HEADER">
			<td colspan="6">&nbsp;</td>
		</tr>		
	</table>
	<center>
	<!-- <img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick="window.close();"/> -->
	<br><a href="#" class="button" onClick="window.close();"><span class="cancel">Cancel</span></a>
	</center>
	
	<input type="hidden" name="chk" value="${wardBean.strwardCode }">
    <input type="hidden" name="hmode">
	
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>