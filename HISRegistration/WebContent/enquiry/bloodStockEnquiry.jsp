<%@page import="hisglobal.vo.BloodStockEnquiryVO"%>

<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<%--
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
--%>
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


function showComponentDiv(componentId,imageId)
{
	if(document.getElementById(componentId).style.display=="none")
	{
	document.getElementById(componentId).style.display="block"
	document.getElementById(imageId).src="../hisglobal/images/arrdouble-up.png"
	}
	else
	{
	document.getElementById(componentId).style.display="none"
	document.getElementById(imageId).src="../hisglobal/images/arrdouble-down.png"
	}
}

</script>

<body>
<html:form action="/bloodStockEnquiry">
<%@page import="java.util.*,enquiry.*"%>
<his:TransactionContainer>


<his:TitleTag>
		<his:name>
			<bean:message key="bloodStockEnquiry" />
		</his:name>
</his:TitleTag>

<his:SubTitleTag name="Stock Type">
<table>
	<tr>
	<div align="right">
	<font  size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
	<bean:message key="available" /> </font> 
	<html:radio name="BloodStockEnquiryFB" property="choice" tabindex="1"
	value="<%=enquiryConfig.IN_STOCK_BLOOD_COMPONENT_STOCK_ENQUIRY%>" onclick="submitPage('AVAILABLE')" /> 
	<font size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
	<bean:message key="all" /> </font> 
	<html:radio name="BloodStockEnquiryFB" property="choice" tabindex="1"
	value="<%=enquiryConfig.ALL_BLOOD_COMPONENT_STOCK_ENQUIRY%>" 
	onclick="submitPage('ALL')" />
	</div>
	</tr>
	
</table>
</his:SubTitleTag>
 <his:statusTransactionInProcess>
 

 
 <logic:iterate indexId="idx" id="bloodStockMapEntry" name="<%=enquiryConfig.ALL_COMPONENT_BLOOD_STOCK_MAP %>" type="java.util.Map.Entry">
 
 <bean:define id="bloodStockMapKey" name="bloodStockMapEntry" type="java.lang.String" property="key"></bean:define>
 <%String divID="div"+bloodStockMapKey; 
 String imageId="image"+bloodStockMapKey;
 String display="";
 String imgSource="";
 if(idx==0)
 {
	 display="display:block";
	 imgSource="/hisglobal/images/arrdouble-up.png";
 }
 else
 {
	 display="display:none";
	 imgSource="/hisglobal/images/arrdouble-down.png";
 }
 %>
 
 <his:SubTitleTag name="<%=bloodStockMapKey %>">

 <table>
 	<tr>
 	<div align="right">
 	<img id="<%=imageId %>" class="button" src='<his:path src="<%=imgSource %>"/>' tabindex="1"
	style=cursor:pointer onkeypress="showComponentDiv('<%=divID%>','<%=imageId %>')" onclick="showComponentDiv('<%=divID%>','<%=imageId %>')"> 
 	</div>
 	</tr>
 </table>
</his:SubTitleTag>

<div id="<%=divID%>" style="<%=display %>">
<his:ContentTag>
	<table width="100%"  cellspacing="1" cellpadding="0">                    
		 <tr>
             <td  class="tdfonthead" width="20%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="40%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="bloodgroup"/>
	            </font>
	            </div>
             </td>
             <td  class="tdfonthead" width="40%">
	            <div align="center">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="bagCount"/>
	            </font>
	            </div>
             </td>
         </tr>
<bean:define id="bloodStockList" name="bloodStockMapEntry" type="java.util.ArrayList" property="value"></bean:define>
 <logic:iterate id="bloodEnquiryVo" name="bloodStockList" type="hisglobal.vo.BloodStockEnquiryVO">
 
    
		<tr>
 			<td class="tdfont">
	  			<div align="center">
	  				
	  			</div>
	  		</td>
	  		<td class="tdfont"> 
	  			<div align="center">
	  			<bean:write name="bloodEnquiryVo" property="bloodGroup"/>
	  			
	  			</div>
	  		</td>
            <td class="tdfont">
	  			<div align="center">
	  			<bean:write name="bloodEnquiryVo" property="bagCount"/>
	  			</div>
	  		</td>
         </tr>
             		
 </logic:iterate>
 
	 </table>
	 </his:ContentTag>  
    
</div>
 </logic:iterate>		

 			
 
 </his:statusTransactionInProcess>
</his:TransactionContainer>
<his:ButtonToolBarTag>
		 
         
</his:ButtonToolBarTag>	
<html:hidden name="BloodStockEnquiryFB" property="hmode"/>
<html:hidden name="BloodStockEnquiryFB" property="choice"/>
</html:form>
<his:status/>
</body>
</html>