<!-- 
/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@ page import = "hisglobal.hisconfig.Config"%>
<%@ page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/commonUtility.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/parameterMasterAdd.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css"> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>


<!-- <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
 
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
 -->

<%-- <%@page import="hisglobal.config.HISConfig"%>
<link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css">

<script type="text/javascript">
var snomedServerURL = '<%=HISConfig.HIS_SNOMEDCT_SERVER_URL%>';
</script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<script type="text/javascript" src="/HIS/appointment/js/appointment.js"></script> --%>


<script>

function selectValue(value, callback) 
{
	
	var data = unescape(value);

	var term = data.split('###$$$');

	var returnterm_OUT = term[1] +","+ term[0].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#dialog-form").dialog("close");

}


</script>

</head>

<body>
	<html:form action="/master/ParameterMaster.cnt" >
		
		<html:hidden name="ParameterMasterFB" property="hmode"/>		
	<his:TransactionContainer>	
		<his:TitleTag name="Parameter Master>>Add">
		</his:TitleTag>
		<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
						<td width="25%"  class="tdfonthead" >
							<div align="right">
								<font color="#000000"  size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<!-- <font color="#ff0000">*</font> -->
									<b><bean:message key="snomedCtCompatible"/></b>
								</font>
							</div>
						
						</td>
						<td width="25%"  class="tdfont">
						
						<div align="left">
						<html:radio name="ParameterMasterFB" property="isSnomedConcept" value="0" onclick="showSnomed();" ></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="yes" />
								</b>
							</font>
							<html:radio name="ParameterMasterFB" property="isSnomedConcept" value="1" onclick="showSnomed();" ></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="no" />
								</b>
							</font></div>
							<html:hidden name="ParameterMasterFB" property="isSnomedFlagValue" />
							</td></tr>
							<tr>
							<td width="25%"  class="tdfonthead" ></td>
							<td  class="tdfont">
						<div id="divSnomed" style="display:block">
					<%-- <html:text readonly="" name="ParameterMasterFB" property="prefferedTerm" ></html:text>
				 --%>	
					<html:hidden 	name="ParameterMasterFB" property="prefferedTerm" ></html:hidden>
					
					<html:hidden name="ParameterMasterFB" property="conceptId" ></html:hidden>
					
					
				<!-- 	<img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) selectSNOMEDCT('ACTIVE','','SYNONYMS','10',callbck_index);" onclick="selectSNOMEDCT('ACTIVE','','SYNONYMS','10',callbck_index);" title="Click to add SNOMED-CT Term">							
				 -->
					
					<div align="left" >
				    <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_1"  style="width:60%;color:#000000;" type="text">
					</div>
					 <div id="norecorddiv_1">
					 <label style="display: inline;" id="reccnt">No. of records : </label>
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
					 </div>         
                     <div class="concept" id="conceptdiv_1">                 
                    </div>
                         </div>
					
					</div>
					</div>
							
						</td>
					</tr>
					
					
					<tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
						<td width="25%"  class="tdfonthead" >
							<div align="right">
								<font color="#000000"  size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#ff0000">*</font>
									<b><bean:message key="paraName"/></b>
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">
							<html:text name="ParameterMasterFB" property="paraName" maxlength="50" onkeypress="return validateAlphabetsOnly(event,this)" >
							</html:text>
						</td>
					</tr>
					
					<tr>	
						<td width="25%"  class="tdfonthead" align="right">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#ff0000">*</font>
									<b><bean:message key="paraBound"/></b>
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont" >
							<html:select name="ParameterMasterFB" property="paraBound">
								<html:option value="-1">Select Value</html:option>
								<html:option value="<%=GenericTemplateConfig.PARAMETER_BOUND_NON_PATIENT_CENTRIC %>">Non Patient Centric</html:option>
								<html:option value="<%=GenericTemplateConfig.PARAMETER_BOUND_PATIENT_CENTRIC %>">Patient Centric</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="25%"  class="tdfonthead" align="right">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#ff0000">*</font>
									<b><bean:message key="paraType"/></b> 
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont" align="right">
							<html:select name="ParameterMasterFB" property="paraType">
								<html:option value="-1">Select Value</html:option>
								 <html:options collection="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_TEMPLATE_GROUP%>" property="templateGroupID" labelProperty="templateGroupName"/>
							</html:select>
							<html:hidden name="ParameterMasterFB" property="isActive" value="<%=Config.IS_VALID_ACTIVE%>" />
						</td>
					</tr>
				</table>
				</his:ContentTag>
					<his:ButtonToolBarTag>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onclick="validate('SAVE')" onkeypress="if(event.keyCode==13) validate('SAVE')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onclick="clearText()" onkeypress="if(event.keyCode==13) clearText()">
					</his:ButtonToolBarTag>
			<center><b><his:status/></b></center>
			
		</his:TransactionContainer>		
	<div id="dialog-howto">
	</div>
	</html:form>	
</body>
</html>