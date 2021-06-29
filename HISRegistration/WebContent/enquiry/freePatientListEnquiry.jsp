<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="registration.*,opd.*,enquiry.*,java.util.*"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<script>
function submitPage(hmode){
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}

</script>

<body>
<html:form action="/staffEnquiry">

<his:TitleTag name="Free Patient Category List">
</his:TitleTag>
<table>
</table>

 <his:statusTransactionInProcess>
 <b><strong>Search Result</strong></b>
 <his:ContentTag>
 	<table width="100%"  cellspacing="1" cellpadding="0">                    
		 <tr>
             <td  class="tdfonthead" width="30%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	           	<b><bean:message key="primCat"/></b>
	            </font>
	            </div>
             </td>
              <td  class="tdfonthead" width="70%">
	            
             </td>
     	</tr> 
     	
     	<logic:iterate id="patCatList" name="<%=enquiryConfig.ENQUIRY_FREE_PATIENT_CAT_LIST %>">
     	 <tr>
     	 <td class="tdfont">
	  			<div align="center">
	  				<bean:write name="patCatList"/>
	  				
	  			</div>
	  		</td>
	  		<td  class="tdfont" width="70%">
	           
            </td>
            </tr>
     	</logic:iterate>
 		
 	</table>
 </his:ContentTag>
 
 </his:statusTransactionInProcess>
 <his:ButtonToolBarTag>
 		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" onclick ="submitPage('CANCEL');">
</his:ButtonToolBarTag>
 
<html:hidden name="freePatientListEnquiryFB" property="hmode"/>
</html:form>
<his:status/>
</body>
</html>