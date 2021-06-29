
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

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
<his:javascript src="/registration/js/regEmergencyCaseMstAddMod.js" />

<head>
<script type="text/javascript">
window.history.forward()
</script>
</head>

 <body>

  <html:form action="/masters/RegEmergencyCaseMstACTION" >
    <html:hidden name="RegEmergencyCaseMstFB" property="hmode"/>
	<html:hidden name="RegEmergencyCaseMstFB" property="emergencyCode"/>
	<html:hidden name="RegEmergencyCaseMstFB" property="emergencySlNo"/>
    <html:hidden name="RegEmergencyCaseMstFB" property="hospitalcode"/>
    
    
       <his:TitleTag name="Emergency Case Master">
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
								<bean:message key="emergencycase"/>&nbsp;
				</font>
			  </div>
			</td>
			<td width="50%" class="tdfont">
			  <div align="left">
			     <logic:notEqual name="RegEmergencyCaseMstFB"  property="hmode" value="VIEW">
			        &nbsp;<html:text name="RegEmergencyCaseMstFB" property="emergencyCase"  maxlength="50" size="30" onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
						  </html:text>
				 </logic:notEqual>		   
				 <logic:equal name="RegEmergencyCaseMstFB"  property="hmode" value="VIEW">
						  <html:hidden name="RegEmergencyCaseMstFB" property="emergencyCase" />
						  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							 &nbsp;<b><bean:write name="RegEmergencyCaseMstFB" property="emergencyCase"/></b> 
						  </font>
				 </logic:equal>		   
			  </div>
			 </td>  
		    </tr>
		   
		    <tr>
			 <td width="50%" class="tdfonthead">
			  <div align="right">
				<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
				</font> 
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="ismlcrequired"/>&nbsp;
				</font>
			  </div>
			 </td>
			 <td width="50%" class="tdfont">
			  <div align="left">
				<logic:equal name="RegEmergencyCaseMstFB" property="hmode" value="VIEW">
				  <html:hidden name="RegEmergencyCaseMstFB" property="isMlcReq" />
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<logic:equal name="RegEmergencyCaseMstFB" property="isMlcReq" value="0">
							&nbsp;<b>No</b>
						</logic:equal>
						<logic:equal name="RegEmergencyCaseMstFB" property="isMlcReq" value="1">
							&nbsp;<b>Yes</b>
						</logic:equal>
					</font>
			   </logic:equal>
			   <logic:notEqual name="RegEmergencyCaseMstFB" property="hmode" value="VIEW"> 
				  &nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="l Arial, Helvetica, sans-serif"> 
								        Yes
							        </font>
						            <html:radio name="RegEmergencyCaseMstFB" property="isMlcReq" value="1" tabindex="1"/>
				  &nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							         	No
							        </font>
							        <html:radio name="RegEmergencyCaseMstFB" property="isMlcReq" value="0"  tabindex="1"/>
				</logic:notEqual> 
			   </div>
			 </td>
			</tr>
		    <tr>
			 <td width="50%" class="tdfonthead">
			   <div align="right">
			       <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
				    </font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="caseType"/>&nbsp;
					 </font>
			   </div>
			  </td>
			  <td width="50%" class="tdfont">
			    <div align="left">
			       <logic:notEqual name="RegEmergencyCaseMstFB" property="hmode" value="VIEW">
			           &nbsp;<html:select name="RegEmergencyCaseMstFB" property="caseType"  style="width:160;" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:option value="0">Normal</html:option>
								<html:option value="1">Trauma</html:option>
								<html:option value="2">Non Trouma</html:option>
								<html:option value="3">Ambulatory</html:option>
							 </html:select>
				   </logic:notEqual>	 
				   <logic:equal name="RegEmergencyCaseMstFB" property="hmode" value="VIEW">
							 <html:hidden name="RegEmergencyCaseMstFB" property="caseType" />
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<logic:equal name="RegEmergencyCaseMstFB" property="caseType" value="0">
									&nbsp;<b>Normal</b>
									</logic:equal>
									<logic:equal name="RegEmergencyCaseMstFB" property="caseType" value="1">
									&nbsp;<b>Trouma</b>
									</logic:equal>
									<logic:equal name="RegEmergencyCaseMstFB" property="caseType" value="2">
									&nbsp; <b>Non Trouma</b>
							        </logic:equal>
							        <logic:equal name="RegEmergencyCaseMstFB" property="caseType" value="3">
									&nbsp; <b>Ambulatory</b>
							        </logic:equal>
							     </font>
				    </logic:equal> 
				  </div>				
			    </td>
			  </tr>
			   
		   <logic:notEqual name="RegEmergencyCaseMstFB" property="hmode" value="ADD">
		   <logic:notEqual name="RegEmergencyCaseMstFB" property="hmode" value="SAVE">
		   <logic:notEqual name="RegEmergencyCaseMstFB" property="hmode" value="VIEW">
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
				   &nbsp;<html:select name="RegEmergencyCaseMstFB" property="isActive"  style="width:160;" tabindex="1">
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
			  <logic:notEqual name="RegEmergencyCaseMstFB"  property="hmode" value="MODIFY">
			  <logic:notEqual name="RegEmergencyCaseMstFB"  property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')" tabindex="1">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				</logic:notEqual>
				</logic:notEqual>
			
			    <logic:equal name="RegEmergencyCaseMstFB"  property="hmode" value="MODIFY">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				</logic:equal>
	               <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')" tabindex="1">
			</span>
		</his:ButtonToolBarTag>
		
   	
   <his:status/>
  </html:form>
 </body>
</html>

