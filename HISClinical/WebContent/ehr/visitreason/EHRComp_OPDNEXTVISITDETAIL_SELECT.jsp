<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="mrd.EMRConfig"%>
<%@page import="emr.dataentry.spp.presentation.fb.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Visit details</title> 
	<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
	<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>
	<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
	<his:javascript src="/investigation/js/investigation.js" />   
	<his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />   
      
     <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
     <link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
	<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
	<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
	<link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/jquery-ui.css">
	<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
	<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
	<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script> -->
	<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
	<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">
 </head>
<style>
</style>

<logic:equal name="EHRSection_VisitReasonFB" property="isSet_OPDNEXTVISITDETAIL" value="1">
<bean:define id="visitreason" name="EHRSection_VisitReasonFB" property="ehrVisitReason"></bean:define> 

	<table style="width:98%; margin:1%">
		<tr>
			<td width="1%" valign="top" class="deskTile">
					<div align="left">
					<input type="checkbox" name="ehrcomp_OPDNEXTVISITDETAIL_chk_select" checked="checked"   value="<%=visitreason%>" onchange="setPatDocSelectJSON('OPDNEXTVISITDETAIL')"  />
					</div>
			</td>
			<td style=""  width="25%">
				<div align="right">
				<font size="2" color="#126295" face="Verdana, Arial, Helvetica, sans-serif">
						<b>		Reason of visit</b>
							</font>
				</div>
			</td>
			<td style="width:1%;"> </td>
				<td width="70%" class="" style="vertical-align: middle;">
							<div align="left">
							 <bean:write name="EHRSection_VisitReasonFB" property="ehrVisitReason" />
							</div>
						</td>
		</tr>
		
	</table>
 
</logic:equal>

<input type="hidden" name="targetId" />
 <html:hidden name="EHRSection_VisitReasonFB" property="isSet_OPDNEXTVISITDETAIL" ></html:hidden>
<script>
</script>

