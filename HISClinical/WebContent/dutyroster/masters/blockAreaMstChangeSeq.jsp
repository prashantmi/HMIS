<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="dutyroster.DutyRosterConfig"%>
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
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/dutyroster/js/blockAreaMstAddMod.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />
<script>
</script>
<body>

  <html:form action="/masters/blockAreaMstAddModACT" > 
    <html:hidden name="blockAreaMstFB" property="hmode"/>
	<html:hidden name="blockAreaMstFB" property="serialNo"/>
	<html:hidden name="blockAreaMstFB" property="blockId"/>
	<html:hidden name="blockAreaMstFB" property="chk"/>
       
      <his:TitleTag name="Block Area Master >>Change Work Preference ">	</his:TitleTag>
  	   
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		    <tr>
	      		<td  class="tdfonthead">
					<div align="center">
						<b><bean:message key="workPreference"/></b>
					</div>
				</td>
			</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		    <tr>
		  		<td width="50%" class="tdfont">
	      			<div align="right">
						<html:select name="blockAreaMstFB" property="selectedAreaCode" multiple="true" size="6" tabindex="1">
							<html:options  collection="<%=DutyRosterConfig.DUTY_AREA_CODE%>" property="value" labelProperty="label" />
						</html:select>
						
					</div>
				</td>
	      		<td width="1%" class="tdfont">
	      			<div align="center">
					
					</div>
				</td>
	      		<td width="43%" class="tdfont">
	      			<div align="left">
						<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP(document.forms[0].selectedAreaCode);' tabindex="1"/>
						<br>
						<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN(document.forms[0].selectedAreaCode);' tabindex="1"/>
					</div>
				</td>
		      	
			</tr>
		</table>
		</his:ContentTag>
          <his:ButtonToolBarTag>
			<span id="saveDiv">
			     <img class="button" tabindex="1" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('SAVECHANGESEQUENCE')" onclick="submitPage('SAVECHANGESEQUENCE')">
			     <img class="button" tabindex="1" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">
			</span>
		</his:ButtonToolBarTag>
		 <his:status/>
      
   </html:form>
  </body>
</html>
		     
		   
		  