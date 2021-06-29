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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "hisglobal.hisconfig.Config"%>
<%@ page import = "java.util.*,hisglobal.presentation.*" %>
<%@ page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/color_picker.css"/>
<his:css src="/hisglobal/utility/generictemplate/css/template_designer.css"/>
<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>

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

<%
	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");		
	HospitalMstVO voHospital = ControllerUTIL.getHospitalVO(request);
%>

<script type="text/javascript">
function onLoadFun()
{
	initMultilingual('English');
	
		document.getElementsByName("chkIsDefault")[0].disabled =true;	
	if(document.getElementsByName("defChk")[0].value == 1)
	{
		document.getElementsByName("chkIsDefault")[0].checked=true;
	}
	else if(document.getElementsByName("defChk")[0].value==0)
		document.getElementsByName("chkIsDefault")[0].checked=false;
	
	load1('1','');
}
var localLangName= '<%=voHospital.getLocalLangName()%>';
//alert(localLangName);
</script>

<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/colorPicker.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/commonUtility.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/templateDesigner.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/templateValidations.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/time_validator.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> 
<his:javascript src="/hisglobal/utility/generictemplate/js/templateParameterMasterAddMod.js"/>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>

</head>

<body onload="onLoadFun()">
	<html:form action="/master/TemplateParameterMaster.cnt">
		<html:hidden name="TemplateParameterMasterFB" property="hmode"/>
		<html:hidden name="TemplateParameterMasterFB" property="templateId"/>
		<html:hidden name="TemplateParameterMasterFB" property="tempSerialNo"/>
		<html:hidden name="TemplateParameterMasterFB" property="rowCount"/>
		<html:hidden name="TemplateParameterMasterFB" property="colCount"/>
		<html:hidden name="TemplateParameterMasterFB" property="parameterValuesList"/>
		<html:hidden name="TemplateParameterMasterFB" property="entryDate" value="<%=sysDate%>" />
		<html:hidden name="TemplateParameterMasterFB" property="templateGroupID"/>
		<html:hidden name="TemplateParameterMasterFB" property="isDefault"  />
		<html:hidden name="TemplateParameterMasterFB" property="defChk"  />
		
		<his:TransactionContainer>
			<his:TitleTag name="Template Parameter Master">
			</his:TitleTag>

			<his:statusNew>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%"  class="tdfonthead"></td>
							<td width="25%"  class="tdfont"></td>
							<td width="25%"  class="tdfonthead"></td>
							<td width="25%"  class="tdfont"></td>
						</tr>
						<tr>
							<td width="25%"  class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="tmplname"/>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
								<div align="left">
									<html:hidden name="TemplateParameterMasterFB" property="templateName"/>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="templateName"/></b>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="tmplcat"/>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
								<div align="left">
									<html:hidden name="TemplateParameterMasterFB" property="templateCategory"/>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:define id="tempType" name="TemplateParameterMasterFB" property="templateType"></bean:define>
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="templateCategoryType"/></b>
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%"  class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="tmpl"/> <bean:message key="type"/>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
								<div align="left">
									<html:hidden name="TemplateParameterMasterFB" property="templateType"/>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:define id="templateType" name="TemplateParameterMasterFB" property="templateType"></bean:define>
											<b>&nbsp;<%=GenericTemplateConfig.TEMPLATE_TYPES[Integer.parseInt((String)templateType)]%></b>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfonthead">
							<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="isDefault"/>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
							<input type="checkbox" name="chkIsDefault"  checked="checked" disabled="disabled"  />
										
							</td>
						</tr>
						<tr style="display:none">
					   		<td width="25%" class="tdfonthead">
			           			<div align="right">	           
			              			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			              				<bean:message key="effectiveFrom"/>
			              			</font>
			            		</div>
							</td>
							
		   					<td width="25%" class="tdfont">
		   						<div align="left">
									<html:hidden name="TemplateParameterMasterFB" property="effectiveFrom" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="effectiveFrom"/></b>
									</font>
		   						</div>
		   					</td>
		   					<td width="25%" class="tdfonthead">
		   						<div align="right">
		   							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		   								<bean:message key="effectiveTo"/>
		   							</font>
		   						</div>
		   					</td>
		   					
		   					<td width="25%" class="tdfont">
		   						<div align="left">
									<html:hidden name="TemplateParameterMasterFB" property="effectiveTo" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="effectiveTo"/></b>
									</font>
		   						</div>
		   					</td>
		   				</tr>
		   				
		   				
		   				
		   				
		   				
		   				
		   				
		   				
		   				<tr >
					   		<td width="25%" class="tdfonthead">
			           			<div align="right">	           
			              			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			              				<bean:message key="age"/>
			              			</font>
			            		</div>
							</td>
							
		   					<td width="25%" class="tdfont">
		   						<div align="left">
									<html:hidden name="TemplateParameterMasterFB" property="effectiveFrom" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="ageRangeName"/></b>
									</font>
		   						</div>
		   					</td>
		   					<td width="25%" class="tdfonthead">
		   						<div align="right">
		   							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		   								<bean:message key="gender"/>
		   							</font>
		   						</div>
		   					</td>
		   					
		   					<td width="25%" class="tdfont">
		   						<div align="left">
									<html:hidden name="TemplateParameterMasterFB" property="effectiveTo" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="strGenderName"/></b>
									</font>
		   						</div>
		   					</td>
		   				</tr>
		   				
		   				
		   				
		   				
		   				
						<tr>
							<td width="25%" class="tdfonthead">
		   						<div align="right">
		   							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		   								<bean:message key="displayname"/>
		   							</font>
		   						</div>
		   					</td>
		   					
		   					<td width="25%" class="tdfont">
		   						<div align="left">
									<html:hidden name="TemplateParameterMasterFB" property="effectiveTo" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="strTemplateDispName"/></b>
									</font>
		   						</div>
		   					</td>
		   					<td width="25%"  class="tdfonthead"></td>
							<td width="25%"  class="tdfont"></td>
		   				</tr>
							
					</table>
				</his:ContentTag>
				
				<his:SubTitleTag name="Template Designer">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" align="center" cellpadding="0" cellspacing="0" border="1">
						<tr>
							<td width="100%" align="center">
								<div id="TemplateDesigner">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div id="divParameterList" style="display: none; position: absolute;">
									<select name="parameterList" id="parameterList" multiple="multiple" size="4">
										<logic:iterate name="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_CLINICAL_PARAMETERS%>" id="list">
											<option value="<bean:write name='list' property='value'/>" ><bean:write name='list' property='label'/></option>
										</logic:iterate>
									</select>
								</div>
								<!--By Mukund on 10.08.2017  -->
								<div id="divTempCategoryList" style="display: none; position: absolute;">
									<select name="tempCategoryList" id="tempCategoryList" multiple="multiple" size="4">
										<logic:iterate name="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_TEMPLATE_CATEGORY%>" id="list">
											<option value="<bean:write name='list' property='templateCategory'/>" ><bean:write name='list' property='templateCategoryType'/></option>
										</logic:iterate>
									</select>
								</div> 
								<div id="divTemplateList" style="display: none; position: absolute;">
									<select name="templateList" id="templateList" multiple="multiple" size="4">
										<logic:iterate name="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_TEMPLATE_WITH_CAT%>" id="list">
											<option value="<bean:write name='list' property='value'/>" ><bean:write name='list' property='label'/></option>
										</logic:iterate>
									</select>
								</div>  
								<%--End on 10.08.2017--%>
								<% String infolist=""; %>
									<logic:iterate name="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_INFORMATION_CONTROL_VALUES%>" id="list" type="hisglobal.utility.Entry">
										<% infolist+=list.getValue()+"#"+list.getLabel()+"#"; %>
									</logic:iterate>
								<% if(infolist.equals("")) infolist = infolist.substring(0,infolist.length()-1); %>
								<div id="informationList" style="display: none;"><%=infolist%></div>
							</td>
						</tr>
					</table>
					<logic:equal name="TemplateParameterMasterFB" property="hmode" value="ADD">
						<script type="text/javascript"> TemplateDesigner.setup('TemplateDesigner','parameterValuesList',<bean:write name="TemplateParameterMasterFB" property="templateType"/>);</script>
					</logic:equal>
					<logic:notEqual name="TemplateParameterMasterFB" property="hmode" value="ADD">
						<script type="text/javascript"> TemplateDesigner.setup('TemplateDesigner','parameterValuesList',<bean:write name="TemplateParameterMasterFB" property="templateType"/>,'<bean:write name="TemplateParameterMasterFB" property="parameterValuesList"/>',<bean:write name="TemplateParameterMasterFB" property="rowCount"/>,<bean:write name="TemplateParameterMasterFB" property="colCount"/> );</script>
					</logic:notEqual>
				</his:ContentTag>
			</his:statusNew>

			
			<his:ButtonToolBarTag>
				<logic:equal name="TemplateParameterMasterFB" property="hmode" value="ADD">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateFinalSave(),'SAVE')" onclick="submitFormOnValidate(validateFinalSave(),'SAVE')">
				</logic:equal>
				<logic:equal name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateFinalSave(),'MODIFYSAVE')" onclick="submitFormOnValidate(validateFinalSave(),'MODIFYSAVE')">
				</logic:equal>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onclick ="submitForm('ADD')" onkeypress="if(event.keyCode==13) submitForm('ADD')">
			</his:ButtonToolBarTag>	
			<his:status/>
		</his:TransactionContainer>
		<input type="hidden" name="targetId" />
		<html:hidden name="TemplateParameterMasterFB" property="genderCode"  />
		<html:hidden name="TemplateParameterMasterFB" property="ageRangeName"  />
		<html:hidden name="TemplateParameterMasterFB" property="ageRangeId"  />
		<html:hidden name="TemplateParameterMasterFB" property="strTemplateDispName"  />
		
	</html:form>		
</body>

</html>