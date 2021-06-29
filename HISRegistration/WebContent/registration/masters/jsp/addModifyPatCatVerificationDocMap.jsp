<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: s.singaravelan
 	 Date			: 10-Feb-2013 -->

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>

<link href="./../hisglobal/css/buttons.css" rel="stylesheet"	type="text/css"/>
<link href="./../hisglobal/css/layout.css" rel="stylesheet"	type="text/css"/>
<link href="./../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../hisglobal/css/jqueryExtValidationToolTip.css">
<!-- <link rel="stylesheet" href="/HISRegistration/struts/xhtml/styles.css" type="text/css"/> -->

<script language="JavaScript" type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript"	src="./../registration/masters/js/registration.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="./../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type="text/javascript" src="./../registration/masters/js/categoryDocMapMst.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>

<body>
<s:form action="CatDocMapMst" validate="true">
	
<div class="wrapper rounded">
<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="catDocMap.title"/>
<%-- 						<s:if test="flagAddMod=='ADD'">>><s:text name="global.add"/></s:if> --%>
<%-- 						<s:else >>><s:text name="global.modify"/></s:else > --%>
				</div>
			</div>
			<div class="div-table-row ">
				<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.primary"/>&nbsp;<s:text name="global.category"/></div>
				<div class="div-table-col control width45"><s:select list="#session.optionPrimaryCategory" listKey="value" listValue="label" headerKey="-1" headerValue="Select Value"
																key="strPatCategoryCode" name="patCatDocMapModel.strPatCategoryCode" onchange="getData();"></s:select>				
				</div>
			</div>
			<div class="div-table-col title width100">
				<s:text name="select"/>&nbsp;<s:text name="global.verificationDoc"/>
			</div>
			
			<div class="div-table-col label width40">
				<s:select list="#session.DocNotMappedInPrimaryCategory" listKey="value" listValue="label" 
					key="strUnMappedDocCodes" name="patCatDocMapModel.strUnMappedDocCodes" multiple="true" size="8"></s:select>
			</div>
			<div class="div-table-col width20" align="center">	
				<img src="./../hisglobal/images/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/>
				<img src="./../hisglobal/images/forwardward.gif" class="link" onClick='moveRightAll("1");'/> 	
				<br><br>
				<img src="./../hisglobal/images/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
				<img src="./../hisglobal/images/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
				 			
			</div>
			<div class="div-table-col control width40">
				<s:select list="#session.DocMappedInPrimaryCategory" listKey="value" listValue="label" 
					key="strMappedDocCodes" name="patCatDocMapModel.strMappedDocCodes" multiple="true" size="8"></s:select>				
			</div>
						
</div>
<div class="div-table-button">
<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
				</div>
				<div class="div-table-row">
					<div class="div-table-col emptyBar"></div>
				</div>
	<div class="div-table-row" align="center">
			<s:if test="flagAddMod=='ADD'">
    		<a href="#" class="button" onclick="savePatCatDocMapMstAction();"><span class="save"><s:text name="save"/></span></a>
    		<a href="#" class="button" onclick="clearPatCatDocMapMstAction();"><span class="clear"><s:text name="clear"/></span></a>
			<a href="#" class="button" onclick="window.parent.closeTab('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>
			</s:if>
			<s:else >
			<a href="#" class="button" onclick="updatePatCatDocMapMstAction();"><span class="save"><s:text name="save"/></span></a>
			<a href="#" class="button" onclick="cancelPatCatDocMapMstAction();"><span class="cancel"><s:text name="cancel"/></span></a>
			</s:else>
	</div>
</div>
		
</div>
<s:hidden name="flagAddMod" value="%{flagAddMod}"></s:hidden>

<div class="div-table">
	<div class="div-table-row ">
		<div class="div-table-col  width100  ">
				<s:property value="message" />
		</div>
	</div>
</div>
<div id="isMappedSelected" style="display:none">
<h3>
	<font color="red">Please Map The Values</font>
</h3>
</div>
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
<s:actionerror/>
<div class="div-table">
<div class="div-table-row   fontError">
<s:fielderror ></s:fielderror>
</div>
</div>
<h4><font color="#FF0000"><s:property value="%{patCatDocMapModel.StrWarning}"/></font></h4>
<h4><font color="#FF0000"><s:property value="%{patCatDocMapModel.strErrorMsg}"/></font></h4>
<script type="text/javascript" >
$('[name="patCatDocMapModel.strPatCategoryCode"]').validatebox({
	required: true,
	validType: ['selectCombo[-1]']
});
</script>
</body>
</html>