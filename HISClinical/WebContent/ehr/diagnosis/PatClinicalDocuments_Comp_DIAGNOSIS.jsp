<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="ehr.EHRConfig"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="registration.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="ehr.diagnosis.vo.EHRSection_DiagnosisVO"%>
<%@page import="ehr.diagnosis.presentation.EHRSection_DiagnosisFB"%>
<%@page import="opd.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Diagnosis</title>      
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<his:javascript src="/investigation/js/investigation.js" />
<his:javascript src="/ehr/js/EHR_spp_diagnosis.js" />
<his:javascript src="/emr/datafetch/patientClinicalDocuments/PatientClinicalDocuments.js" />   

<!--<his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />    -->

<his:javascript src="/opd/opdJs/opdAjax.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js" />


<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css"> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script> -->
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
	<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script> -->
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">
 
 </head>
 
<script type="text/javascript">
//window.onload=function()
//{
	
//diagnosisOnload();
//} 

</script>
    <style>
input, select, textarea{
border: 1px solid #bbb;
}
#vdate{
font-weight:100;
font-size:13px;

}
#depunit{
font-weight:100;
font-size:13px;

}
label{
font-size:13px;
}

  </style>

	
<body>
<his:ContentTag>
	<html:hidden name="EHRSection_DiagnosisFB" property="icdNHospitalFlagValue"/>
	<html:hidden name="EHRSection_DiagnosisFB" property="icdNHospitalFlagValue" value="<%=OpdConfig.CHOICE_SNOMEDCT_CODE %>"/>

	<div align="center" id="somectIddata" style="display:none">
	</div>				

	<div class="" style="width:98%;">
		<table id="diagnosisTable" width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="22%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="disease" />
							</b>
						</font>
					</div>
				</td>
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="diagonosisType" />
							</b>
						</font>
					</div>
				</td>
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="diagnosisSite" />
							</b>
						</font>
					</div>
				</td>
				<td width="30%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b> <bean:message key="remark" /></b>
						</font>
					</div>
				</td>
				<td class="tdfonthead"></td>
			</tr>
			
			<tr >
				 <td width="27%" class="tdfont" style="vertical-align: middle;">
				 	<html:hidden name="EHRSection_DiagnosisFB" property="diagnosisRecordStatus" value="<%=EHRConfig.EHR_SECTION_RECORD_STATUS_NOT_SAVED %>" ></html:hidden>
				 	<div align="center" id="divsnomedCTDiagnosisName">
						<html:hidden name="EHRSection_DiagnosisFB" property="snomedCTDiagnosisName" value="" ></html:hidden>
					</div>
					<div align="center" id="divsnomedCTDiagnosisCode">
						<html:hidden name="EHRSection_DiagnosisFB" property="snomedCTDiagnosisCode" value="" ></html:hidden>
					</div>
					<div align="center" >
					    <div id="dialog-form_1" >
							<div id="snomed-ct-search">
								<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
								<input id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_1" autocomplete="off" placeholder="Enter 3 characters to search..." onfocus="load1('1','DISORDER');"  style="width:95%;color:#000000;" type="text">
							</div>
                         </div>
					</div>
				</td>
				<td class="tdfont">
					<%
						EHRSection_DiagnosisFB dd = (EHRSection_DiagnosisFB) pageContext.findAttribute("EHRSection_DiagnosisFB");
						System.out.println("fb=================" + dd);
						System.out.println("getDiagonisticTypeCode=================" + dd.getDiagonisticTypeCode());
					%>
					<div align="center" id="divdiagnostictype">
						<html:hidden name="EHRSection_DiagnosisFB" property="diagnosticTypeName" value="Provisional"></html:hidden>
						<html:select name="EHRSection_DiagnosisFB" tabindex="1" styleId="diagnostictypeId"  property="diagonisticTypeCode" onchange="setDiagnosticTypeName()" >
							<logic:present name="<%=RegistrationConfig.DIAGNOSIS_LIST%>" >
							<html:options collection="<%=RegistrationConfig.DIAGNOSIS_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
						
						
					</div>
				</td>
				<td width="24%" class="tdfont" style="vertical-align: middle;">	
				 	<div align="center" id="divsnomedCTDiagnosisSiteName">
						<html:hidden name="EHRSection_DiagnosisFB" property="snomedCTDiagnosisSiteName" value="" ></html:hidden>
					</div>
					<div align="center" id="divsnomedCTDiagnosisSiteCode">
						<html:hidden name="EHRSection_DiagnosisFB" property="snomedCTDiagnosisSiteCode" value="" ></html:hidden>
					</div>
					<div align="center" id="divfindingsitesnomed" style="display:none" >
					    <div id="dialog-form_2" >
							<div id="snomed-ct-search">
								<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
								<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_2" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_2" onfocus="load1('2','BODY_STRUCTURE');"  style="width:95%;color:#000000;" type="text">
							</div>
					 		<div id="norecorddiv_2">
					 			<label style="display: inline;" id="reccnt">No. of records : </label>
								<span style="display: inline;" id="reccount" ></span>
								<label style="display: none;" id="nosuggestion">No suggestions found</label>
								<label style="display: none;" id="norec">No results found</label>
                   				<label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
							</div>         
							<div class="concept" id="conceptdiv_2">                 
							</div>
						</div>
					</div> 
					<div align="center" id="divfindingsite" style="display:none">
						<html:select name="EHRSection_DiagnosisFB" tabindex="1" styleId="seldiagnosticSiteId"  property="selSnomedCTDiagnosisSiteCode" onchange="setSnomedSiteName()" >
						<html:option value="-1">Select Value</html:option>
						</html:select>
					</div>
				</td>
				<td width="26%" class="tdfont" style="vertical-align: middle;">	
					<html:hidden name="EHRSection_DiagnosisFB" property="snomedPTRemarks" value="" ></html:hidden>
					<html:hidden name="EHRSection_DiagnosisFB" property="snomedCIdRemarks" value="" ></html:hidden>
					<html:hidden name="EHRSection_DiagnosisFB" property="remarks" value="" ></html:hidden>
					<textarea name="txt-snomed-ct-search_4" rows="1"  id="txt-snomed-ct-search_4" class="clearable ui-autocomplete-input x onX pull-right" value="" onfocus="selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','4',callbck_index4);" style="width:80%;" autocomplete="off"></textarea>
				</td>
				<td class="tdfont">
					<div align="center">
						<img class="button"  id="addButton" style="cursor: pointer;" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' tabindex='1' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) submitDiagnosisOnValidate();" onclick="submitDiagnosisOnValidate(); ">
					</div>
				</td>
			</tr>
			
			
			
		
			<%
			EHRSection_DiagnosisVO[] latestDiag = (EHRSection_DiagnosisVO[]) session.getAttribute(OpdConfig.Latest_DIAGNOSIS_VO);
			System.out.print(latestDiag.length);
				if(latestDiag!=null && latestDiag.length !=0)
				{
			%>
			<logic:iterate id="prevDiagVO" indexId="idx" name="<%=OpdConfig.Latest_DIAGNOSIS_VO%>" type="ehr.diagnosis.vo.EHRSection_DiagnosisVO">
					
				<tr id="diagnosisRow#<%=idx.intValue()+2%>" >
				<td class="tdfont">
						<div align="center">
							<input name='snomedCTDiagnosisCode' value='<%=(prevDiagVO.getDiagnosticCode()==null)?"":prevDiagVO.getDiagnosticCode()%>' type='hidden'></input>
							<input name='snomedCTDiagnosisName' value='<%=(prevDiagVO.getDignosisName()==null)?"":prevDiagVO.getDignosisName()%>' type='hidden'></input>
							<html:hidden name="EHRSection_DiagnosisFB" property="diagnosisRecordStatus" value="<%=EHRConfig.EHR_SECTION_RECORD_STATUS_SAVED%>" ></html:hidden>
							<%=(prevDiagVO.getDignosisName()==null)?"":prevDiagVO.getDignosisName()%>
						</div>

					<td class="tdfont">
						<div align="center">
							<input name='diagonisticTypeCode' value='<%=(prevDiagVO.getDiagnosticTypeCode()==null)?"":prevDiagVO.getDiagnosticTypeCode()%>' type='hidden'></input>
							<html:hidden name="EHRSection_DiagnosisFB" property="diagnosticTypeName" value="<%=prevDiagVO.getDiagnosticTypeName()%>" ></html:hidden>
							
							<%=(prevDiagVO.getDiagnosticTypeName()==null)?"":prevDiagVO.getDiagnosticTypeName()%>
						</div>
					</td>

					<td class="tdfont">
						<div align="center">
							<input name='snomedCTDiagnosisSiteCode' value='<%=(prevDiagVO.getDiagnosisSite()==null)?"":prevDiagVO.getDiagnosisSite()%>' type='hidden'></input>
							<input name='snomedCTDiagnosisSiteName' value='<%=(prevDiagVO.getDiagnosisSiteLabel()==null)?"":prevDiagVO.getDiagnosisSiteLabel()%>' type='hidden'></input>
							<%=(prevDiagVO.getDiagnosisSiteLabel()==null)?"":prevDiagVO.getDiagnosisSiteLabel()%>
						</div>
					</td>

					<td class="tdfont">
						<div align="center">
							<input name='remarks' value='<%=(prevDiagVO.getRemarks()==null)?"":prevDiagVO.getRemarks() %>' type='hidden'></input>
							<%=(prevDiagVO.getRemarks()==null)?"":prevDiagVO.getRemarks() %>
						</div>
					</td>
					<td class="tdfont">
						<div align="center">
							<img class="button" id="revokeButton" style="cursor:pointer" src="/HIS/hisglobal/images/avai/minus.gif" title="Cancel Diagnosis"  onclick="hideDiagRow('<%=idx.toString()%>')">
						</div>
					</td>	
				</tr>
			</logic:iterate>
			<%
				}
			%>
			
		</table>
	</div>
	</his:ContentTag>
</body>

<html:hidden name="EHRSection_DiagnosisFB" property="hmode" />
<html:hidden name="EHRSection_DiagnosisFB" property="patCrNo" />
<html:hidden name="EHRSection_DiagnosisFB" property="departmentUnitCode" />
<html:hidden name="EHRSection_DiagnosisFB" property="episodeCode" />
<html:hidden name="EHRSection_DiagnosisFB" property="numberOfRow"/> <%-- <%=Integer.toString(count)%>" />  --%>
<html:hidden name="EHRSection_DiagnosisFB" property="unitDiagnosisCodeType" />
<html:hidden name="EHRSection_DiagnosisFB" property="diagnosisCodeType" />
<html:hidden name="EHRSection_DiagnosisFB" property="comboOptionString" />
<html:hidden name="EHRSection_DiagnosisFB" property="episodeVisitNo" />
<html:hidden name="EHRSection_DiagnosisFB" property="comboDiagnosisSite" />
<html:hidden name="EHRSection_DiagnosisFB" property="isSetDIGANOSIS"/>

<input type="hidden" name="targetId" />



<script type="text/javascript">
DIAGSetDataExist();
</script>

</html>