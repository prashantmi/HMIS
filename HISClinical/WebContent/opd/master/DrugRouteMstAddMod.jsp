<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.Arrays"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/registration.js"/>  
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/opd/opdJs/pagination.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/js/DrugRouteMstAddMod.js"/>
<body>
<html:form action="/master/drugRouteMstAdd" >
	
	<html:hidden name="drugRouteMstFB" property="hmode"/>
	<%-- <html:hidden name="drugRouteMstFB" property="itemTypeId"/> --%>
	<html:hidden name="drugRouteMstFB" property="serialNo"/>
 	<html:hidden name="drugRouteMstFB" property="drugRouteId"/>
     
    
    <his:TransactionContainer>

		<his:TitleTag name="Drug Route Master">
		</his:TitleTag>
         <his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			 
			  <tr> 
			    <td width="50%" class="tdfonthead">
			      <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="itemType"/>
								<html:hidden name="drugRouteMstFB" property="itemTypeName"/>
								
						 </font>
				  </div>
			    </td>
			   
			   <%-- //Commented By Chetan 
			          Date:05-12-2015

<td width="50%" class="tdfont">
			      <div align="left">
			       		<html:hidden name="drugRouteMstFB" property="itemTypeName" />
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:write name="drugRouteMstFB" property="itemTypeName"/></b> 
						</font>
				   			   
			      </div>
			    </td>   --%>
			  <logic:equal property="hmode" name="drugRouteMstFB" value="ADD">
			  <td width="50%" class="tdfont">
	  	 	  		<div align="left" >	  
	  	    		<html:select name="drugRouteMstFB" property="itemTypeId" tabindex="1" >
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE%>" >
						<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_ALL_ITEM_TYPE%>" property="value" labelProperty="label" />
						</logic:present>
	        		</html:select>
	        		</div>
	  	 	 	</td>
	  	 	 	</logic:equal>
	  	 	 	<logic:equal property="hmode" name="drugRouteMstFB" value="MODIFY">
	  	 	 	<td width="50%" class="tdfont">
	  	 	  		<div align="left" >	  
	  	    		 	<html:hidden name="drugRouteMstFB" property="itemTypeName" />
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:write name="drugRouteMstFB" property="itemTypeName"/></b> 
						</font>
				   			   
			      	    		
	  	    		
	        		</div>
	  	 	 	</td>
	  	 	 	</logic:equal>
			  </tr>
			 	
			 	
			 	
			 	<tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			            
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="drugRouteClassification"/>
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
			       		<logic:notEqual name="drugRouteMstFB" property="hmode" value="VIEW">
						<html:select name="drugRouteMstFB" property="routeClassification" style="width:160;" >
								<html:option value="-1">Select Value</html:option>
								   <html:options collection="<%=OpdConfig.DRUG_ROUTE_CLASSIFICATION_LIST%>" property="value" labelProperty="label" />
						   </html:select>

						</logic:notEqual>
						
						<logic:equal name="drugRouteMstFB" property="hmode" value="VIEW">
						 
							<html:select name="drugRouteMstFB" property="routeClassification" style="width:160;" disabled="true">
								<html:option value="-1">Select Value</html:option>
								<logic:iterate id="record" collection="<%=Arrays.asList(OpdConfig.DRUG_ROUTE_CLASSIFICATION_ARRAY) %>" indexId="i" offset="1">
								   <html:option value='<%=String.valueOf(i.intValue()-1)%>'><bean:write name="record"/> </html:option>
								</logic:iterate>
							   </html:select>
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
								<bean:message key="drugRouteName"/>
						 </font>
				  </div>
			    </td>
			   
			    <td width="50%" class="tdfont">
			      <div align="left">
			        <logic:notEqual name="drugRouteMstFB" property="hmode" value="VIEW">
			             <html:text name="drugRouteMstFB" property="drugRouteName"  maxlength="100" size="30"  onkeypress="return validateAlphaOnly(this,event)">
							   </html:text>
					</logic:notEqual>		   
				    <logic:equal name="drugRouteMstFB" property="hmode" value="VIEW">
								<html:hidden name="drugRouteMstFB" property="drugRouteName" />
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:write name="drugRouteMstFB" property="drugRouteName"/></b> 
								</font>
				   </logic:equal>			   
			      </div>
			    </td>  
			  </tr>
			  <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="routeDesc"/>
						 </font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			        <logic:notEqual name="drugRouteMstFB" property="hmode" value="VIEW">
							    <html:text name="drugRouteMstFB" property="drugRouteDesc"  maxlength="200" size="30"  onkeypress="return validateAlphaOnly(this,event)">
							   </html:text>
					</logic:notEqual>		   
				    <logic:equal name="drugRouteMstFB" property="hmode" value="VIEW">
								<html:hidden name="drugRouteMstFB" property="drugRouteDesc" />
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:write name="drugRouteMstFB" property="drugRouteDesc"/></b> 
								</font>
				    </logic:equal>				   
			      </div>
			    </td>
			  </tr>
			     
			     		 
			  <logic:notEqual name="drugRouteMstFB" property="hmode" value="ADD">
			 <logic:notEqual name="drugRouteMstFB" property="hmode" value="SAVE">
			 <logic:notEqual name="drugRouteMstFB" property="hmode" value="VIEW">
			      <tr>
			        <td width="50%" class="tdfonthead">
			         <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isActive"/>
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     <html:select name="drugRouteMstFB" property="isActive"  style="width:160;">
						    <html:option value="-1">Select Value</html:option>
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
			    <logic:notEqual name="drugRouteMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="drugRouteMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="drugRouteMstFB" property="hmode" value="MODIFY">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:equal>  
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
	</his:TransactionContainer>	
	<center><b><his:status/></b></center>
</html:form>
</body>
</html>
