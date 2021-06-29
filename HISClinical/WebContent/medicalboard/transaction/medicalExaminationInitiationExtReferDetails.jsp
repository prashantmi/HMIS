<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
</script>

<%try{ %>	

<html:form action="/medicalExamInitiation">	   
<his:TitleTag name="Candidate External Refer Detail">
</his:TitleTag>	   
<his:statusList>
   	<his:ContentTag>
		<logic:present name="<%=MedicalBoardConfig.EPISODE_EXT_REF_VO_LIST %>">
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="35%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b>Refer To</b>
			 </font>
			</div>
		  </td>
		 <td width="35%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b>Refer Reason</b>
			 </font>
			</div>
		  </td>
		 <td width="30%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b>Date</b>
			 </font>
			</div>
		  </td>
		 
		</tr>
		
		<logic:iterate id="externalRefVO" name="<%=MedicalBoardConfig.EPISODE_EXT_REF_VO_LIST %>" type="hisglobal.vo.MedicalBoardExternalReferVO" indexId="index">
		<tr>
		 <td class="tdfont">
		 	<div align="center">
		  		<bean:write name="externalRefVO" property="extReferTo"/>
		 		
			</div>
		  </td>
		 <td class="tdfont">
		  <div align="center">
				<bean:write name="externalRefVO" property="referReason"/> 
			</div>
		  </td>
		 <td class="tdfont">
		  <div align="center">
				<bean:write name="externalRefVO" property="entryDate"/> 
			</div>
		  </td>
		  </tr>
		</logic:iterate>
		
	 </table>
	</logic:present>		
</his:ContentTag>
</his:statusList>	
	
 	<his:ButtonToolBarTag>
         <div align="center">
         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) window.close();" tabindex="1" onclick ="window.close();">
	     </div>
     </his:ButtonToolBarTag>

 
   
<his:status/>
</html:form>

<% } catch(Exception e) {e.printStackTrace();}%>
