
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ page import="opd.OpdConfig"%>
<%@ page import="hisglobal.hisconfig.*"%>
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
	<logic:equal value="MODIFY" name="templateMappingMstFB" property="hmode">
		<his:TitleTag name="Template Mapping Master >> Modify"> </his:TitleTag>
	</logic:equal>
	<logic:equal value="VIEW" name="templateMappingMstFB" property="hmode">
		<his:TitleTag name="Template Mapping Master >> View"> </his:TitleTag>
	</logic:equal>
  <html:form action="/master/templateMappingMstAdd" > 
    <html:hidden name="templateMappingMstFB" property="hmode"/>
	<html:hidden name="templateMappingMstFB" property="templateCategory"/>
	<html:hidden name="templateMappingMstFB" property="templateCategoryName"/>
	<html:hidden name="templateMappingMstFB" property="chk"/>
	<html:hidden name="templateMappingMstFB" property="deptCode"/>
	<html:hidden name="templateMappingMstFB" property="deptUnitCode"/>
	<html:hidden name="templateMappingMstFB" property="wardCode"/>
	<html:hidden name="templateMappingMstFB" property="fetchedList"/>
        
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="tmplcat"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
		            	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		            	&nbsp;<bean:write name="templateMappingMstFB" property="templateCategoryName" />
						</font>	   
					</div>
			 </td>  
			 </tr>
			<tr> 
		    <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="department"/></b>&nbsp;
					</font>
				  </div>
			    </td>
		    <td width="50%" class="tdfont">
			   <div align="left">
				 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			          &nbsp;<bean:write name="templateMappingMstFB" property="deptName"/>
				 </font>	  
			   </div>
		   </td>
		   </tr>
		   <logic:notEmpty name="templateMappingMstFB" property="deptUnitName">
		   <tr>
		   	<td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="unit"/></b>&nbsp;
					</font>
				  </div>
			    </td>
		      <td width="50%" class="tdfont">
			     <div align="left">
					 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			            &nbsp;<bean:write name="templateMappingMstFB" property="deptUnitName"/>
					 </font>	  
				  </div>
			    </td>
			</tr>    
		    </logic:notEmpty>
		     <logic:notEmpty name="templateMappingMstFB" property="wardName">
		     <tr>
			 <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="ward"/></b>&nbsp;
					</font>
				  </div>
			    </td>
		      <td width="50%" class="tdfont">
			     <div align="left">
					 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			            &nbsp;<bean:write name="templateMappingMstFB" property="wardName"/>
					 </font>	  
				  </div>
			    </td>
			 </tr>
		 	</logic:notEmpty>
		 	  <tr>
		 		<td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="isDefault"/></b>&nbsp;
					</font>
				  </div>
			    </td>
		      <td width="50%" class="tdfont">
			     <div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<logic:equal value="MODIFY" name="templateMappingMstFB" property="hmode">
							<html:hidden name="templateMappingMstFB" property="isDefault" />
						</logic:equal>
						&nbsp;<html:select name="templateMappingMstFB" property="isDefault" disabled="true" styleClass="regcbo">
							<html:option value="<%=OpdConfig.YES%>">Yes</html:option>
							<html:option value="<%=OpdConfig.NO%>">No</html:option>
						</html:select>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfonthead"></td>
			    <td width="25%" class="tdfont"></td>
		      </tr>
		</table>
		<logic:equal value="MODIFY" name="templateMappingMstFB" property="hmode">
			<his:SubTitleTag name="Select Template Name">  </his:SubTitleTag>
		</logic:equal>
		<logic:equal value="VIEW" name="templateMappingMstFB" property="hmode">
			<his:SubTitleTag name="Template Name">  </his:SubTitleTag>
		</logic:equal>
		
		<logic:equal value="MODIFY" name="templateMappingMstFB" property="hmode">
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
							<logic:present name="<%=OpdConfig.TEMPLATE_ASSIGNED%>">
	  							<html:options  collection="<%=OpdConfig.TEMPLATE_ASSIGNED%>" property="value" labelProperty="label" />
							</logic:present> 
							</html:select>
						</div>
					</td>
				</tr>
			</table>
		</logic:equal>
			
		<logic:equal value="VIEW" name="templateMappingMstFB" property="hmode">
		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
			<tr>
				<td width="100%"  class="tdfont">
						<div align="center">
							<html:select name="templateMappingMstFB" property="selectedTemplateId" multiple="true" size="6">
							<logic:present name="<%=OpdConfig.TEMPLATE_ASSIGNED%>">
	  							<html:options  collection="<%=OpdConfig.TEMPLATE_ASSIGNED%>" property="value" labelProperty="label" />
							</logic:present> 
							</html:select>
						</div>
					</td>
				</tr>
			</table>
		</logic:equal>
     </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			    <logic:equal value="MODIFY" name="templateMappingMstFB" property="hmode"> 
			    	<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				</logic:equal>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
	    <center><b><his:status/></b></center>
   </html:form>
  </body>
</html>