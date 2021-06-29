
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Arrays"%>
<%@page import="mrd.MrdConfig"%>
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
    
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/mrd/js/rackMstAddModjs.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script>
function validateRackName(e,obj)
{
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	//alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	else if((getCursorIdex(obj)>0) && (key==32))
		return true

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./(-)").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}


</script>
<body>
<html:form action="/master/rackMst" >

	<%!
		boolean readOnly;
	%>
	<% this.readOnly=false;%>
	
	<logic:equal name="rackMstFB" property="hmode" value="VIEW">
		<% this.readOnly=true; %>
	</logic:equal>
	
  
	<his:TransactionContainer>
		<his:TitleTag name="Rack Master">
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
								<bean:message key="rackName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
					 <html:text name="rackMstFB" property="rackName"  maxlength="50" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateRackName(event,this)" tabindex="1">
					 </html:text>
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
								<bean:message key="rackStatus"/>&nbsp;
						 </font>
				     </div>
			      	</td>
			     <td width="50%" class="tdfont">
			          <div align="left">
			            <html:select name="rackMstFB" property="rackStatus" disabled="<%=this.readOnly %>" style="width:160;"  styleClass="regcbo" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<%-- <html:options collection="<%=MrdConfig.RACK_TYPE_LIST %>" labelProperty="label" property="value"/>
								--%>
								<html:option value="<%=MrdConfig.MRD_RACK_STATUS_NOT_IN_USED%>">Not Working</html:option>
								<html:option value="<%=MrdConfig.MRD_RACK_STATUS_WORKING%>">Working</html:option>
							</html:select> 
						</div>				
			        </td>
			     </tr> 
			     <%-- 
			     <tr> 	
			    	<td width="50%" class="tdfonthead">
			        <div align="right">
			              
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="itemName"/>&nbsp;
						 </font>
				     </div>
			      	</td>
			     <td width="50%" class="tdfont">
			          <div align="left">
			            <html:select name="rackMstFB" property="itemId" disabled="<%=this.readOnly %>" style="width:160;" >
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=MrdConfig.ITEM_LIST %>" labelProperty="label" property="value"/>
							</html:select> 
						</div>				
			        </td>
			     </tr> 
			     --%>
			      
			     <tr id="mrdDiv"> 	
			    	<td width="50%" class="tdfonthead">
			       		<div align="right">
				              <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
							 </font> 
							 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="mrdName"/>&nbsp;
							 </font>
				     	</div>
			      	</td>
				     <td width="50%" class="tdfont">
				          <div align="left">
				            <html:select name="rackMstFB" property="mrdCode" disabled="<%=this.readOnly %>" style="width:160;"  styleClass="regcbo" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=MrdConfig.ESSENTIAL_MRD_LIST_OPTION %>" labelProperty="label" property="value"/>
							</html:select> 
						</div>				
			        </td>
			     </tr> 
			     
			    <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="building"/>&nbsp;
						 </font>
				     </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
					  <html:select name="rackMstFB" property="buildingCode" disabled="<%=this.readOnly %>" style="width:160;"  onchange="submitForm('BLOCK_LIST')" styleClass="regcbo" tabindex="1">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=MrdConfig.BUILDING_LIST %>" >
						 <html:options collection="<%=MrdConfig.BUILDING_LIST%>" labelProperty="label" property="value"/>
					    </logic:present>
					  </html:select>
				  </div>
			    </td>
			  </tr>
			  <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="block"/>&nbsp;
						 </font>
				     </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
					  <html:select name="rackMstFB" property="blockId" disabled="<%=this.readOnly %>" style="width:160;" onchange="submitForm('FLOOR_LIST')" styleClass="regcbo" tabindex="1">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=MrdConfig.BLOCK_LIST %>" >
						 <html:options collection="<%=MrdConfig.BLOCK_LIST %>" labelProperty="label" property="value"/>
					    </logic:present>
					  </html:select>
				  </div>
			    </td>
			  </tr>
			  <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="floor"/>&nbsp;
						 </font>
				     </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
					  <html:select name="rackMstFB" property="floorId" disabled="<%=this.readOnly %>" style="width:160;" onchange="submitForm('ROOM_LIST')" styleClass="regcbo" tabindex="1">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=MrdConfig.FLOOR_LIST %>">
						 <html:options collection="<%=MrdConfig.FLOOR_LIST %>" labelProperty="label" property="value"/>
					    </logic:present>
					  </html:select>
				  </div>
			    </td>
			  </tr>
			  <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="room"/>&nbsp;
						 </font>
				     </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
					  <html:select name="rackMstFB" property="roomId" disabled="<%=this.readOnly %>" style="width:160;" styleClass="regcbo" tabindex="1">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=MrdConfig.ROOM_ID_LIST %>">
						 <html:options collection="<%=MrdConfig.ROOM_ID_LIST %>" labelProperty="label" property="value"/>
					    </logic:present>
					  </html:select>
				  </div>
			    </td>
			  </tr>
			    
		      <tr> 	
		    	<td width="50%" class="tdfonthead">
			        <div align="right">
			           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="remarks"/>&nbsp;
						</font>
				     </div>
		      	</td>
		      	<td width="50%" class="tdfont">
			      	<div align="left">
					 <html:text name="rackMstFB" property="remarks"  maxlength="50" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1">
					 </html:text>
				  	</div>
		     	</td>
		     	
		      </tr> 
			    <logic:equal name="rackMstFB" property="mode" value="MODIFY">
			        
				  <tr>
					<td width="50%" class="tdfonthead">
					 <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					      <bean:message key="isActive" />&nbsp;
					   </font>
					 </div>
					</td>
					<logic:notEqual name="rackMstFB" property="hmode" value="ADD">
					<logic:notEqual name="rackMstFB" property="hmode" value="VIEW">
					<td width="50%" class="tdfont">
					  <div align="left">
					    	<html:select name="rackMstFB" property="isValid" style="width:160;"  styleClass="regcbo" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option>
								<html:option value="<%=Config.IS_VALID_INACTIVE %>">InActive</html:option>
							  </html:select>
					   </div>
					 </td>
					</logic:notEqual>
					</logic:notEqual>
					 <logic:equal name="rackMstFB" property="hmode" value="VIEW">
						<td width="50%" class="tdfont">
					  		<div align="left">
						    	<logic:equal name="rackMstFB" property="isValid" value="<%=Config.IS_VALID_ACTIVE %>" >
									<html:text name="rackMstFB" property="isValid"  maxlength="20" size="30" value="Active" readonly="true">
								   </html:text>
								</logic:equal>
								<logic:equal name="rackMstFB" property="isValid" value="<%=Config.IS_VALID_INACTIVE %>">
									<html:text name="rackMstFB" property="isValid"  maxlength="20" size="30" value="In Active" readonly="true">
								   </html:text>
								</logic:equal>
							</div>
					 	</td>
					 </logic:equal>
					</tr>
				</logic:equal>
		      </table>
		    </his:ContentTag>
			
			 <his:ButtonToolBarTag>
				<span id="saveDiv">
			    <logic:notEqual name="rackMstFB" property="mode" value="MODIFY">
			    <logic:notEqual name="rackMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')" tabindex="1">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
	               <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')" tabindex="1">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="rackMstFB" property="mode" value="MODIFY">
			    <logic:notEqual name="rackMstFB" property="hmode" value="VIEW">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
	               <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')" tabindex="1">
				</logic:notEqual>
				</logic:equal> 
				
				<logic:equal name="rackMstFB" property="hmode" value="VIEW">
	               <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')" tabindex="1">
				</logic:equal>
				</span>
			</his:ButtonToolBarTag>
	<his:status/>
	</his:TransactionContainer>
	
	<html:hidden name="rackMstFB" property="hmode" />
	<html:hidden name="rackMstFB" property="rackId" />
	<html:hidden name="rackMstFB" property="serialNo" />
	<html:hidden name="rackMstFB" property="mrdCode" />
	<html:hidden name="rackMstFB" property="mode" />
	<html:hidden name="rackMstFB" property="mrdSize" />
	</html:form>
	</body>
</html>