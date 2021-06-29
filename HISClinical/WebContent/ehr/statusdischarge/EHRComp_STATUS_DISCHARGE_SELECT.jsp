<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="ehr.EHRConfig"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="emr.dataentry.spp.presentation.fb.*"%>

<%@page import="ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
      
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Discharge Status</title> 
      <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<%-- <his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<his:javascript src="/investigation/js/investigation.js" />   
   <his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />   
    <his:javascript src="/ehr/js/EHR_spp_followup.js" />   
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
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet"> --%>
   
 </head>
    <style>


  </style>


<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
EHRSection_StatusAtDischargeVO dischargeVO= (EHRSection_StatusAtDischargeVO) session.getAttribute(EHRConfig.STATUS_AT_DISCHARGE);
%>
	<his:ContentTag>
	<html:hidden name="EHRSection_StatusAtDischargeFB" property="entryDate" value="<%=sysDate%>"/>
	
	<logic:notEmpty name="<%=EHRConfig.STATUS_AT_DISCHARGE%>" >
			<table width="98%" cellspacing="1" cellpadding="0" style="margin:1%">
				<tr>
				<td width="4%" class="" style="vertical-align: middle;">
						<div align="right">
					
				 <input type="checkbox" name="ehrcomp_DISCHARGESTATUS_chk_select" value="<%=dischargeVO.getSelectedSectionData()%>"  checked="checked"  onchange="setPatDocSelectJSON('DISCHARGESTATUS')" />
 				<input type="hidden" name="ehrcomp_DISCHARGESTATUS_dataelem_json_<%=dischargeVO.getSelectedSectionData()%>"   value='<%=dischargeVO.getJSONObj().toString() %>'  />         
 				</div></td>
					<td width="22%" class="" style="vertical-align: middle;">
						<div align="center">
							<font color="#126295" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="disNote"/></b>
							</font>
						</div>
					</td>
					<td width="74%" class="" style="vertical-align: middle;">
						<div align="left">
						   	           
							  <html:hidden name="EHRSection_StatusAtDischargeFB" property="snomdPTStatusAtDischarge" ></html:hidden>
					          <html:hidden name="EHRSection_StatusAtDischargeFB" property="snomdCIdStatusAtDischarge" ></html:hidden>
					         <%=(dischargeVO.getStatusAtDischarge()==null)?"":dischargeVO.getStatusAtDischarge() %>
					          <html:hidden name="EHRSection_StatusAtDischargeFB" property="statusAtDischarge" value="<%=dischargeVO.getStatusAtDischarge() %>"/>
							
						</div>
					</td>       	
						<!-- <textarea name="txt-snomed-ct-search_FU4" rows="3" cols="20" id="txt-snomed-ct-search_FU4" class="clearable ui-autocomplete-input x onX" value=""  onfocus="load_FU('FU4','','');" style="width:58%;" autocomplete="off" disabled="disabled"></textarea>
						 -->										
						
				</tr>
			</table>
		</logic:notEmpty>


<input type="hidden" name="targetId" />

<html:hidden name="EHRSection_StatusAtDischargeFB" property="hmode" />

<html:hidden name="EHRSection_StatusAtDischargeFB" property="episodeCode" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="admissionNo" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="departmentUnitCode" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="deskType" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="seatId" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="isSetSD" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="dataExist" />
 
	</his:ContentTag>
					         
<script></script>
