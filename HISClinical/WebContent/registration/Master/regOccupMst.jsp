<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@page import="registration.RegistrationConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/regOccupMst.js" />

<head>
<script type="text/javascript">
window.history.forward()
</script>
</head>

<his:css src="/css/calendar-blue2.css" />

 <body>

  <html:form action="/masters/RegOccupMstACTION" >
    <html:hidden name="RegOccupMstFB" property="hmode"/>
	<html:hidden name="RegOccupMstFB" property="occupCode"/>
	<html:hidden name="RegOccupMstFB" property="occupSlNo"/>
    <html:hidden name="RegOccupMstFB" property="hospitalcode"/>
    
     <his:TransactionContainer>
       <his:TitleTag name="Registration Occupation Master">
			
		</his:TitleTag>
		
		
	  <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		   <tr>
			  <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="occupationname"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			        <logic:notEqual name="RegOccupMstFB"  property="hmode" value="VIEW">
			          &nbsp;<html:text name="RegOccupMstFB" property="occupName"  maxlength="100" size="30" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)">
						    </html:text>
					</logic:notEqual>		   
				      <logic:equal name="RegOccupMstFB"  property="hmode" value="VIEW">
								<html:hidden name="RegOccupMstFB" property="occupName" />
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								  &nbsp;<b><bean:write name="RegOccupMstFB" property="occupName"/></b> 
								</font>
				     </logic:equal>		   
				   </div>
			     </td>  
			    </tr>
			 <logic:notEqual name="RegOccupMstFB" property="hmode" value="ADD">
			 <logic:notEqual name="RegOccupMstFB" property="hmode" value="SAVE">
			 <logic:notEqual name="RegOccupMstFB" property="hmode" value="VIEW">
			      <tr>
			        <td width="50%" class="tdfonthead">
			         <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isActive"/>&nbsp;
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     &nbsp;<html:select name="RegOccupMstFB" property="isActive"  tabindex="1"  style="width:160;">
							<html:option value="1">Active</html:option>
							<html:option value="2">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:notEqual>
			   </logic:notEqual>
			   </logic:notEqual> 	
			   </table>
		 </his:ContentTag>
			  
		 <his:ButtonToolBarTag>
			<span id="saveDiv">
			  <logic:notEqual name="RegOccupMstFB"  property="hmode" value="MODIFY">
			  <logic:notEqual name="RegOccupMstFB"  property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual>
				</logic:notEqual>
			
			    <logic:equal name="RegOccupMstFB"  property="hmode" value="MODIFY">
				   <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>'   tabindex="1"   style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:equal>
	               <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1"   style="cursor: pointer" onclick="submitPage('LIST')" onkeypress="if(event.keyCode==13) submitPage('LIST')">
			</span>
		</his:ButtonToolBarTag>
		
		<his:status/>
		<html:hidden name="RegOccupMstFB" property="chk"/>
   </his:TransactionContainer>	
  </html:form>
 </body>
</html>

