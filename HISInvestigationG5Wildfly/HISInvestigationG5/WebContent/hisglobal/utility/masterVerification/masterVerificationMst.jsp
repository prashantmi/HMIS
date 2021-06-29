<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.hisconfig.Config"%>
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
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script src="/HISInvestigationG5/hisglobal/utility/masterVerification/masterVerificationMst.js" language="javascript"/>
<script>

</script>

<%try{ %>	
<his:TransactionContainer>
<html:form action="/masterVerificationMst">	   
		   
   <his:TitleTag name="Master Verification Master"> 		
   </his:TitleTag>
   <%boolean readonly=false; %>
   <logic:equal name="masterVerificationMstFB" property="hmode" value="VIEW">
   	<%readonly=true; %>
   </logic:equal>
<his:ContentTag>
<his:statusNew>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="35%" class="tdfonthead">
			<div align="right">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="modulename"/>
			 </font>
			</div>
		  </td>
		 <td width="65%" class="tdfont">
			 <div align="left">
			    <logic:notEqual value="MODIFY" name="masterVerificationMstFB" property="hmode"> 
			    <logic:notEqual value="VIEW" name="masterVerificationMstFB" property="hmode"> 
				&nbsp;<html:select name="masterVerificationMstFB" property="moduleId" tabindex="1" styleClass="regcbo"  style="width:200px" disabled="<%=readonly %>">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=Config.MASTER_VERIFICATION_MODULE_LIST %>">
				<html:options collection="<%=Config.MASTER_VERIFICATION_MODULE_LIST %>" labelProperty="label" property="value"/>
				</logic:present>
				</html:select>
				</logic:notEqual>
				</logic:notEqual>
				<logic:notEqual value="ADD" name="masterVerificationMstFB" property="hmode"> 
			    <logic:notEqual value="SAVE" name="masterVerificationMstFB" property="hmode"> 
				&nbsp; <bean:write name="masterVerificationMstFB" property="moduleName"/>
				<html:hidden name="masterVerificationMstFB" property="moduleId"/>
				</logic:notEqual>
				</logic:notEqual>
			</div>
		  </td>
		</tr>
		<tr>  
		 <td width="35%" class="tdfonthead">
		 	<div align="right">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="mainHeader"/>
			 </font>
			 </div>
		  </td>
		 <td width="65%" class="tdfont">
		 	<div align="left">
			&nbsp;<html:text name="masterVerificationMstFB" property="mainHeader" tabindex="1" size="30" readonly="<%=readonly %>">
				</html:text>
			</div>
		  </td>
		 </tr>
		<%--  
		<tr>  
		 <td width="35%" class="tdfonthead">
			<div align="right">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="subHeader"/>
			 </font>
			</div>
		  </td>
		  <td width="65%" class="tdfont">
			<div align="left">
			&nbsp;<html:text name="masterVerificationMstFB" property="subHeader" tabindex="1"/>
			</div>
		 </td>
		 </tr>
		 --%>
		 <tr>
		 	 <td width="50%" colspan="2">
		 	 <table width="100%" id="columnTable">
		 	 	 <tr>
		 		 	<td width="35%" class="tdfonthead">
					 	<div align="right">
						<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  <bean:message key="columnHeader"/>
						 </font>
						</div>
					</td>
				 	 <td width="45%" class="tdfont">
						<div align="left">
						&nbsp;<html:text name="masterVerificationMstFB" property="columnHeader" size="30" tabindex="1" readonly="<%=readonly %>"/>
						</div>
					</td>	
					<td width="20%" class="tdfont">	
						<div align="left">
						<logic:notEqual value="VIEW" name="masterVerificationMstFB" property="hmode">
						<img class="button" src='<his:path src="/hisglobal/images/RoundPlus12x12.png"/>'
							style=cursor:pointer onclick="addColumn(this);"
							onkeypress="if(event.keyCode==13) addColumn(this);" size='7'
							tabindex="1">
						</logic:notEqual>	
						</div>
					 </td>
		 		</tr>
		 	</table>
		 	
		  </td>
		 </tr>
		
		<tr>
		 <td width="35%" class="tdfonthead">
		 	<div align="right">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  Main Query
			 </font>
			 </div>
		  </td>
		  <td width="65%" class="tdfont">
			&nbsp;<html:textarea name="masterVerificationMstFB" property="mainQuery" tabindex="1" cols="60" rows="6" readonly="<%=readonly %>"/>
		 </td>
		</tr> 
		<tr>
		
		 <td width="50%" colspan="2">
		 	 <table width="100%" id="orderByTable">
		 	 	 <tr>
		 		 	<td width="35%" class="tdfonthead">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  <bean:message key="orderBy"/>
						 </font>
						</div>
					  </td>
				 	 <td width="45%" class="tdfont">
						<div align="left">
						&nbsp;<html:text name="masterVerificationMstFB" property="orderBy" tabindex="1" size="30" readonly="<%=readonly %>"/>
						</div>
					</td>	
					<td width="20%" class="tdfont">	
						<div align="left">
						<logic:notEqual value="VIEW" name="masterVerificationMstFB" property="hmode">
						<img class="button" src='<his:path src="/hisglobal/images/RoundPlus12x12.png"/>'
							style=cursor:pointer onclick="addOrderBy(this);"
							onkeypress="if(event.keyCode==13) addOrderBy(this);" size='7'
							tabindex="1">
						</logic:notEqual>
						</div>
					 </td>
		 		</tr>
		 	</table>
		 	
		  </td>
		  </tr>
	</table>
	
	<his:SubTitleTag name="">
		<table><tr>
			<td width="20%" align="right"><div><b>Criteria Required</b> <input type="checkbox" name="isCriteriaRequired" onclick="showCriteria(this)">
			</div>	
			</td>
		</tr></table>
		
	</his:SubTitleTag>
	<div id="criteriaDiv" style="display: none;">
	<table width="100%" cellspacing="1">	 
		  
		   <tr>
		   <td width="35%" class="tdfonthead">
			<div align="right">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  Criteria Label
			 </font>
			</div>
		  </td>
		  <td width="65%" class="tdfont">
		  	<div align="left">
				&nbsp;<html:text name="masterVerificationMstFB" property="criteriaLabel" tabindex="1" size="30" readonly="<%=readonly %>"/>
			</div>	
		  </td>
		  </tr>
		  <tr>
		   <td width="35%" class="tdfonthead">
			<div align="right">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  Criteria Column
			 </font>
			</div>
		  </td>
		  <td width="65%" class="tdfont">
		  	<div align="left">
				&nbsp;<html:text name="masterVerificationMstFB" property="criteriaColumn" tabindex="1" size="30" readonly="<%=readonly %>"/>
			</div>	
		  </td>
		</tr>
		
		 <tr>
		 <td width="35%" class="tdfonthead">
		 	<div align="right">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  Column Sub Header
			 </font>
			 </div>
		  </td>
		  <td width="65%" class="tdfont">
			&nbsp;<html:text name="masterVerificationMstFB" property="columnSubHeader" size="30" tabindex="1" readonly="<%=readonly %>"/>
		 </td>
		</tr> 
		
		  <tr>
		   <td width="35%" class="tdfonthead">
			<div align="right">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  Criteria Query
			 </font>
			</div>
		  </td>
		  <td width="65%" class="tdfont">
		  	<div align="left">
				&nbsp;<html:textarea name="masterVerificationMstFB" property="criteriaQuery" tabindex="1" cols="60" rows="3" readonly="<%=readonly %>"/>
			</div>	
		  </td>
		</tr>
		  <tr>
		   <td width="35%" class="tdfonthead">
			<div align="right">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  Group Query
			 </font>
			</div>
		  </td>
		  <td width="65%" class="tdfont">
		  	<div align="left">
				&nbsp;<html:textarea name="masterVerificationMstFB" property="groupQuery" tabindex="1" cols="60" rows="6" readonly="<%=readonly %>"/>
			</div>	
		  </td>
		</tr>
		</table>
	</div>	
</his:statusNew>	
</his:ContentTag>

	<his:ButtonToolBarTag>
         <div align="center">	
			<his:statusNew>
				<logic:notEqual value="VIEW" name="masterVerificationMstFB" property="hmode">
				<logic:notEqual value="MODIFY" name="masterVerificationMstFB" property="hmode">
				<img class="button" src="/HISInvestigationG5/hisglobal/images/btn-sv.png" style="cursor: pointer;" onkeypress="if(event.keyCode==13) validateSave('SAVE');" tabindex="1" onclick="validateSave('SAVE')">
				</logic:notEqual>
				<logic:equal value="MODIFY" name="masterVerificationMstFB" property="hmode">
				<img class="button" src="/HISInvestigationG5/hisglobal/images/btn-mo.png" style="cursor: pointer;" onkeypress="if(event.keyCode==13) validateSave('MODIFYSAVE');" tabindex="1" onclick="validateSave('MODIFYSAVE')">
				</logic:equal>
	        	<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) clearForm();" tabindex="1" onclick ="clearForm();">
	        	</logic:notEqual>
	        	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
	        </his:statusNew>
         </div>
     </his:ButtonToolBarTag>
     
   <html:hidden name="masterVerificationMstFB" property="hmode"/>
   <html:hidden name="masterVerificationMstFB" property="columnHeaderString"/>
   <html:hidden name="masterVerificationMstFB" property="orderByString"/>
   <html:hidden name="masterVerificationMstFB" property="serialNo"/>
	<input type="hidden" name="noOfRows">      
   </html:form>
   
  <his:status/>      

</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
