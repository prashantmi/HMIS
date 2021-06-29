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

<his:javascript src="/dutyroster/js/rosterPrintMstAddMod.js" />

<his:css src="/css/calendar-blue2.css" />
<script>
</script>
<body>

  <html:form action="/masters/RosterPrintMstAddModACT" > 
       
      <his:TitleTag name="Roster Print Master >>Change Printing Order ">	</his:TitleTag>
  	   
	 <his:ContentTag>
					
<logic:notEmpty name="<%=DutyRosterConfig.MAP_FOR_ORDER_ROSTER_PRINT_DETAILS %>">
			
			
<logic:iterate id="rosterPrintMapId" name="<%=DutyRosterConfig.MAP_FOR_ORDER_ROSTER_PRINT_DETAILS %>" type="java.util.Map.Entry">
	
	<bean:define id="rosterPrintMapKey" name="rosterPrintMapId" property="key"></bean:define>
	<bean:define id="rosterPrintMapValue" name="rosterPrintMapId" property="value" type="java.util.Map"></bean:define>
	
<logic:equal value="1" name="rosterPrintMapKey">
	
			
			<his:ContentTag>
	
	<his:SubTitleTag  name="Instructions">
	</his:SubTitleTag>
	
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
<td  width="60%" class="tdfont">
	
	<div align="right">
		
<html:select name="RosterPrintMstFB" property="displayInstruction" multiple="true" size="6" tabindex="1"> 
	<html:options  collection="rosterPrintMapValue" property="key" labelProperty="value" />
</html:select>

	</div>

</td>


<td width="40%" class="tdfont">
	      			<div align="left">
						<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP(document.forms[0].displayInstruction);'/>
						<br>
						<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN(document.forms[0].displayInstruction);'/>
					</div>
</td>

</tr>
	
			</table>
	</his:ContentTag>
</logic:equal>
		
		
	
	

<logic:equal value="3" name="rosterPrintMapKey">
	
			
			<his:ContentTag>
	
	<his:SubTitleTag  name="Copy To">
	</his:SubTitleTag>
	
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>

<td class="tdfont" width="60%">
	
<div align="right">
		
	<html:select name="RosterPrintMstFB" property="displayCopyTo" multiple="true" size="6" tabindex="1"> 
		<html:options  collection="rosterPrintMapValue" property="key" labelProperty="value"  />
	</html:select>

</div>

</td>


<td width="40%" class="tdfont">
	<div align="left">
		<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP(document.forms[0].displayCopyTo);'/>
			<br>
		<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN(document.forms[0].displayCopyTo);'/>
	</div>
</td>

</tr>
	
			</table>
	</his:ContentTag>
</logic:equal>
	
		
	
	</logic:iterate>
	
	
 	



</logic:notEmpty>

			
			
</his:ContentTag>

<html:hidden name="RosterPrintMstFB" property="hmode"/>
<html:hidden name="RosterPrintMstFB" property="concatedValueOfInstruction"/>
<html:hidden name="RosterPrintMstFB" property="concatedValueOfCopyTo"/>   
<html:hidden name="RosterPrintMstFB" property="rosterType"/>     

          <his:ButtonToolBarTag>
			<span id="saveDiv">
			     <img class="button" tabindex='1'  src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('SAVE_CHANGE_ORDER')" onclick="submitPage('SAVE_CHANGE_ORDER')">
			     <img class="button" tabindex='1'  src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">
			</span>
		</his:ButtonToolBarTag>
		 <his:status/>
      
      
      
   </html:form>
  </body>
</html>
		     
		   
		  