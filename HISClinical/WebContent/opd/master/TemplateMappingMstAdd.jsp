<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ page import="opd.OpdConfig"%>
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
<his:javascript src="/opd/js/templateMappingMstAdd.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />

 <body>
	<his:TitleTag name="Template Mapping Master >> Add"> </his:TitleTag>
  <html:form action="/master/templateMappingMstAdd" > 
    <html:hidden name="templateMappingMstFB" property="hmode"/>
	<html:hidden name="templateMappingMstFB" property="templateCategory"/>
	<html:hidden name="templateMappingMstFB" property="templateCategoryName"/>
	<html:hidden name="templateMappingMstFB" property="chk"/>
    <%!boolean readOnly; %>
    <%this.readOnly=false; %>
    <logic:equal name="templateMappingMstFB" value="GO" property="hmode">
     <%this.readOnly=true;%>
    </logic:equal>    
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		  <tr>
			  <td width="15%" class="tdfonthead">
		   		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<B><bean:message key="aditionMode"/></B>&nbsp;
				</font>
			  </td>
		      <td width="35%" class="tdfont">
			     <div align="left">
			     	&nbsp;<b><bean:message key="department"/>
					<html:radio property="deptRadio" value="1" name="templateMappingMstFB" disabled="<%=this.readOnly %>" onclick="showDepartment()"></html:radio>
				  	<bean:message key="unit"/>
				  	<html:radio property="deptRadio" value="2" name="templateMappingMstFB" disabled="<%=this.readOnly %>" onclick="showUnit()"></html:radio>
				  	<bean:message key="ward"/>
				  	<html:radio property="deptRadio" value="3" name="templateMappingMstFB" disabled="<%=this.readOnly %>" onclick="showWard()"></html:radio>
				  	</b>
				  </div>
			  </td>
		      <td width="25%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="tmplcat"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="25%" class="tdfont">
			      <div align="left">
			            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			            	&nbsp;<bean:write name="templateMappingMstFB" property="templateCategoryName"/>
						</font>	   
					</div>
			     </td>  
		  	</tr>
		</table>
		
		<div id="departmentDiv">
		 <table width="100%" border="0" cellspacing="1" cellpadding="0">
		 	<tr>
		 	<td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="department"/></b>&nbsp;
					</font>
				  </div>
			    </td>
		      <td width="50%" class="tdfont">
			     <div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<logic:equal name="templateMappingMstFB" property="hmode" value="GO">
							<html:hidden name="templateMappingMstFB" property="deptCode"/>
						</logic:equal>
						<html:select name="templateMappingMstFB" property="deptCode" disabled="<%=this.readOnly %>" onchange="if(this!='-1') getDeptUnit();" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.TEMPLATE_MAPPING_DEPT_NOT_ASSIGNED %>">
								<html:options collection="<%=OpdConfig.TEMPLATE_MAPPING_DEPT_NOT_ASSIGNED %>" labelProperty="label" property="value"/>
							</logic:present>
						</html:select>
					</font>
				  </div>
			    </td>
			 </tr>
		 </table>
		</div>
		<div id="unitDiv">
		 <table width="100%" border="0" cellspacing="1" cellpadding="0">
		 	<tr>
		 	<td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="unit"/></b>&nbsp;
					</font>
				  </div>
			    </td>
		      <td width="50%" class="tdfont">
			     <div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<logic:equal name="templateMappingMstFB" property="hmode" value="GO">
						 <html:hidden name="templateMappingMstFB" property="deptUnitCode"/>
						</logic:equal>
						<html:select name="templateMappingMstFB" property="deptUnitCode"  disabled="<%=this.readOnly %>" onchange="if(this!='-1') getWard()" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<logic:notEqual name="templateMappingMstFB" value="ADD" property="hmode">
								<logic:present name="<%=OpdConfig.TEMPLATE_MAPPING_UNIT_NOT_ASSIGNED %>">
								<html:options collection="<%=OpdConfig.TEMPLATE_MAPPING_UNIT_NOT_ASSIGNED %>" labelProperty="label" property="value"/>
								</logic:present>
							</logic:notEqual>
						</html:select>
					</font>
				  </div>
			    </td>
		      </tr>
		 </table>
		</div>
		<div id="wardDiv">
		 <table width="100%" border="0" cellspacing="1" cellpadding="0">
		 	<tr>
		 	<td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="ward"/></b>&nbsp;
					</font>
				  </div>
			    </td>
		      <td width="50%" class="tdfont">
			     <div align="left">
					 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<logic:equal name="templateMappingMstFB" property="hmode" value="GO">
						 	<html:hidden name="templateMappingMstFB" property="wardCode"/>
						</logic:equal>
						<html:select name="templateMappingMstFB" property="wardCode" disabled="<%=this.readOnly %>" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<logic:notEqual name="templateMappingMstFB" value="ADD" property="hmode">
								<logic:present name="<%=OpdConfig.TEMPLATE_MAPPING_WARD_NOT_ASSIGNED %>">
									<html:options collection="<%=OpdConfig.TEMPLATE_MAPPING_WARD_NOT_ASSIGNED %>" labelProperty="label" property="value"/>
								</logic:present>
							</logic:notEqual>
						</html:select>
					</font>
				  </div>
			    </td>
		      </tr>
		 </table>
		</div>
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		 <tr>
		  <td width="50%" class="tdfonthead">
			     <div align="right">
			     	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						*
					</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="isDefault"/></b>&nbsp;
					</font>
				  </div>
			    </td>
		      <td width="50%" class="tdfont">
			     <div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<logic:equal name="templateMappingMstFB" property="hmode" value="GO">
						 <html:hidden name="templateMappingMstFB" property="isDefault"/>
						</logic:equal>
						<html:select name="templateMappingMstFB" property="isDefault" disabled="<%=this.readOnly %>" styleClass="regcbo">
							<html:option value="<%=OpdConfig.NO%>">No</html:option>
							<html:option value="<%=OpdConfig.YES%>">Yes</html:option>
						</html:select>
					</font>
				  </div>
			    </td>
			    </tr>
			   </table> 
			 
		</his:ContentTag>
		<logic:notEqual value="GO" name="templateMappingMstFB" property="hmode">
			 <his:ButtonToolBarTag>
				<span id="saveDiv">
				<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('GO')" onclick="finalSubmit('GO')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
				</span>
			</his:ButtonToolBarTag>	
		</logic:notEqual>
		<his:statusTransactionInProcess>
		<his:SubTitleTag name="Select Template Name">  </his:SubTitleTag>
		<his:ContentTag>
		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="40%"  class="tdfont">
						<div align="right">
							<html:select name="templateMappingMstFB" property="templateId" multiple="true" size="6">
								<logic:present name="<%=OpdConfig.TEMPLATE_NOT_ASSIGNED%>">
	  								<html:options  collection="<%=OpdConfig.TEMPLATE_NOT_ASSIGNED%>" property="value" labelProperty="label" />
								</logic:present> 
							</html:select>
										
						</div>
					</td>
					<td width="20%"  class="tdfont">
						<div align="center">
							<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
							<img src="/HIS/hisglobal/images/avai/forwardward.gif" class="link" onClick='moveRightAll("1");'/> 	
							<br><br>
							<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
							<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
						</div>
					</td>
					<td width="40%"  class="tdfont">
						<div align="left">
							<html:select name="templateMappingMstFB" property="selectedTemplateId" multiple="true" size="6">
							</html:select>
						</div>
					</td>
				</tr>
			</table>
     	</his:ContentTag>
     	 <his:ButtonToolBarTag>
			<span id="saveDiv">
			    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				<logic:equal name="templateMappingMstFB" value="GO" property="hmode">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="cancel()" onkeypress="if(event.keyCode==13) cancel()">
				</logic:equal>
				<logic:equal name="templateMappingMstFB" value="ADD" property="hmode">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
				</logic:equal>
			</span>
		</his:ButtonToolBarTag>
		</his:statusTransactionInProcess> 
		 <center><b><his:status/></b></center>
	 </html:form>
  </body>
</html>
		   