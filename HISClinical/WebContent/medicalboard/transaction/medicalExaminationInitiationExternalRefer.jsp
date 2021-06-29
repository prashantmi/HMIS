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

function saveExtReferDetail(){
	
	if(isEmpty(document.getElementsByName('extReferTo')[0],"Refer To") && 
	isEmpty(document.getElementsByName('referReason')[0],"Refer Reason"))
	{
	opener.document.getElementsByName('extReferTo')[0].value=document.getElementsByName('extReferTo')[0].value;
	opener.document.getElementsByName('referReason')[0].value=document.getElementsByName('referReason')[0].value;
	opener.document.getElementsByName('hmode')[0].value='SAVEEXTREFER'
	self.close();
	opener.document.forms[0].submit();
	}
}


</script>

<%try{ %>	
<his:TransactionContainer>
<html:form action="/medicalExamInitiation">	   
<his:TitleTag name="External Refer">
</his:TitleTag>	   

   	<his:ContentTag>
		
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="25%" class="tdfonthead">
		  <div align="right" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b>Refer To</b>
			 </font>
			</div>
		  </td>
		 <td width="25%" class="tdfont">
		  <div align="left" >
			<html:text name="medicalExamInitiationFB" property="extReferTo" maxlength="100"  tabindex="1" styleClass="textbox"  onkeypress="return validateAlphabetsOnly(event,this)"></html:text>
		  </td>
		 <td width="25%" class="tdfonthead">
		  <div align="right" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b>Refer Reason</b>
			 </font>
			</div>
		  </td>
		 <td width="25%" class="tdfont">
		  <div align="left" >
			<html:text name="medicalExamInitiationFB" property="referReason" maxlength="100"  tabindex="1" styleClass="textbox"  onkeypress="return validateAlphabetsOnly(event,this)"></html:text>
			</div>
		  </td>
		 
		</tr>
	</table>
		
</his:ContentTag>

	
 	<his:ButtonToolBarTag>
         <div align="center">
         
         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) return saveExtReferDetail()" onclick ="return saveExtReferDetail();" tabindex="1"/>
         
            <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) window.close();" tabindex="1" onclick ="window.close();">
	     </div>
     </his:ButtonToolBarTag>

   
   <html:hidden name="medicalExamInitiationFB" property="extReferTo"/>
   <html:hidden name="medicalExamInitiationFB" property="referReason"/>
</html:form>


</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
