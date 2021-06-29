<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.masters.controller.fb.RackShelfMstFB"%>

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
<his:javascript src="/mrd/masters/rackShelfMstAdd.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />

 <body>
  <html:form action="/master/rackShelfMst" > 
    <his:TransactionContainer>
   
  <his:TitleTag name="Shelf Master">
  
  <%!boolean readOnly=false; %>

  <logic:equal name="rackShelfMstFB" property="hmode" value="VIEW">
  	<%readOnly=true; %>	
  </logic:equal>
  <logic:notEqual name="rackShelfMstFB" property="hmode" value="VIEW">
  	<%readOnly=false; %>	
  </logic:notEqual>
  
  </his:TitleTag>
   	 <his:ContentTag>
	 
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="rackName"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			      	<bean:write name="rackShelfMstFB" property="rackName"/>	
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
						<bean:message key="shelfName"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			      	<html:text name="rackShelfMstFB" property="shelfLabel"  maxlength="50" size="30" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1" readonly="<%=this.readOnly %>">
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
						<bean:message key="shelfStatus"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			      	<html:select name="rackShelfMstFB" property="shelfStatus" style="width:160;"  styleClass="regcbo" tabindex="1" disabled="<%=this.readOnly %>">
						<html:option value="-1">Select Value</html:option>
						<%-- <html:options collection="<%=MrdConfig.RACK_TYPE_LIST %>" labelProperty="label" property="value"/>
						--%>
						<html:option value="<%=MrdConfig.MRD_RACK_SHELF_STATUS_NOT_IN_USED%>">Not In Use</html:option>
						<html:option value="<%=MrdConfig.MRD_RACK_SHELF_STATUS_SPACE_AVAILABLE%>">Available</html:option>
						<html:option value="<%=MrdConfig.MRD_RACK_SHELF_STATUS_OCCUPIED%>">Occupied</html:option>
					</html:select> 
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
						<bean:message key="capacity"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			      	<html:text name="rackShelfMstFB" property="shelfCapacity"  maxlength="5" size="6" onkeypress="return validateNumeric(event)" tabindex="1" readonly="<%=this.readOnly %>">
					 </html:text>	
					 	<bean:message key="records"/>
			      </div>
			     </td>  
		      </tr>
		      <logic:equal name="rackShelfMstFB" property="mode" value="MODIFY">
			        
				  <tr>
					<td width="50%" class="tdfonthead">
					 <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font> 
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					      <bean:message key="isActive" />&nbsp;
					   </font>
					 </div>
					</td>
					<logic:notEqual name="rackShelfMstFB" property="hmode" value="ADD">
					<logic:notEqual name="rackShelfMstFB" property="hmode" value="VIEW">
					<td width="50%" class="tdfont">
					  <div align="left">
					    	<html:select name="rackShelfMstFB" property="isValid" style="width:160;"  styleClass="regcbo" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option>
								<html:option value="<%=Config.IS_VALID_INACTIVE %>">InActive</html:option>
							  </html:select>
					   </div>
					 </td>
					</logic:notEqual>
					</logic:notEqual>
					 <logic:equal name="rackShelfMstFB" property="hmode" value="VIEW">
						<td width="50%" class="tdfont">
					  		<div align="left">
						    	<logic:equal name="rackShelfMstFB" property="isValid" value="<%=Config.IS_VALID_ACTIVE %>" >
									<html:text name="rackShelfMstFB" property="isValid"  maxlength="20" size="30" value="Active" readonly="true">
								   </html:text>
								</logic:equal>
								<logic:equal name="rackShelfMstFB" property="isValid" value="<%=Config.IS_VALID_INACTIVE %>">
									<html:text name="rackShelfMstFB" property="isValid"  maxlength="20" size="30" value="In Active" readonly="true">
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
				<logic:notEqual name="rackShelfMstFB" property="mode"  value="MODIFY">
				<logic:notEqual name="rackShelfMstFB" property="hmode"  value="VIEW">
			  	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
			  	</logic:notEqual>
			  	</logic:notEqual>
			  	<logic:equal name="rackShelfMstFB" property="mode"  value="MODIFY">
			  	<logic:notEqual name="rackShelfMstFB" property="hmode"  value="VIEW">
			  	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
			  	</logic:notEqual>
			  	</logic:equal>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		 <his:status/>
		 
		 <html:hidden name="rackShelfMstFB" property="hmode"/>
		<html:hidden name="rackShelfMstFB" property="serialNo"/>
		<html:hidden name="rackShelfMstFB" property="chk"/>
		<html:hidden name="rackShelfMstFB" property="rackId"/>
		<html:hidden name="rackShelfMstFB" property="rackShelfId"/>
		<html:hidden name="rackShelfMstFB" property="mode"/>
		<html:hidden name="rackShelfMstFB" property="rackName"/>
	</his:TransactionContainer>	
 	 </html:form>
  </body>
</html>
		     
		   
		  