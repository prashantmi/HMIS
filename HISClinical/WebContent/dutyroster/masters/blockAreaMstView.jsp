<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="dutyroster.DutyRosterConfig"%>
<%@page import="hisglobal.vo.BlockAreaMstVO"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />
<script>
function submitPage(mode){
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
} 
</script>
 <body>
<%!String blockName; %>
<%this.blockName=""; %>
  <html:form action="/masters/blockAreaMstAddModACT" > 
    <html:hidden name="blockAreaMstFB" property="hmode"/>
	<html:hidden name="blockAreaMstFB" property="chk"/>
       	<his:TitleTag name="Block Area Master >> View">
		</his:TitleTag>
  		<his:ContentTag>
  		<logic:present name="<%=DutyRosterConfig.BLOCK_AREA_DETAIL %>">
  		<logic:iterate id="blockAreaMstVO" name="<%=DutyRosterConfig.BLOCK_AREA_DETAIL %>" type="hisglobal.vo.BlockAreaMstVO">
  			<%this.blockName=blockAreaMstVO.getBlockId(); %>
  		</logic:iterate>
  		</logic:present>
  		<table width="100%" border="0" cellspacing="1" cellpadding="0">
  			<tr>
		      <td width="20%" class="tdfonthead" >
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="dutyBlockName"/>&nbsp;</b>
					</font>
				  </div>
			  </td>
		      <td width="25%" class="tdfont" >
			     <div align="left">
					&nbsp;<b><%
					if(this.blockName!=null){
					out.print(this.blockName);} %></b>
				 </div>
			  </td>
		      <td width="25%" class="tdfont" ></td>
		      <td width="25%" class="tdfont" > </td>
			  </tr>
  		</table>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
		      <td width="25%" class="tdfonthead">
			    <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="areaType"/>&nbsp;</b>
					</font>
				  </div>
			  </td>
		      <td width="25%" class="tdfonthead">
			   <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="areaCode"/>&nbsp;</b>
					</font>
				  </div>
			  </td>
		      <td width="25%" class="tdfonthead">
			    <div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="workPreference"/>&nbsp;</b>
					</font>
				</div>
			  </td>
		   </tr>
		  <logic:present name="<%=DutyRosterConfig.BLOCK_AREA_DETAIL %>">
		   <logic:iterate id="blockAreaMstVO" name="<%=DutyRosterConfig.BLOCK_AREA_DETAIL %>" type="hisglobal.vo.BlockAreaMstVO">
		   <tr>
		      <td width="25%" class="tdfont">
			    <div align="center">
					<b><bean:write name="blockAreaMstVO" property="areaTypeCode"/></b>
				  </div>
			    </td>
		      <td width="25%" class="tdfont">
			    <div align="center">
					<b><bean:write name="blockAreaMstVO" property="areaCode"/></b>
				  </div>
			    </td>
		      <td width="25%" class="tdfont">
			    <div align="center">
					<b><bean:write name="blockAreaMstVO" property="workPrefrence"/></b>
				  </div>
			    </td>
			    
		      </tr>
		  </logic:iterate>
		  </logic:present>
		  </table>
      </his:ContentTag>
    
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			   <img class="button" tabindex="1" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">
			</span>
		</his:ButtonToolBarTag>
	 <his:status/>
    </html:form>
  </body>
</html>
		     
		   
		  