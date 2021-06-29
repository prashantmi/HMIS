<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="mrd.MrdConfig"%>
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
<his:javascript src="/mrd/js/processWiseDesigMapping.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />

 <body>
<his:TransactionContainer>
  <html:form action="/processWiseDesigMapping" > 
    <html:hidden name="processWiseDesigMappingFB" property="hmode"/>
<%-- 	<html:hidden name="processWiseDesigMappingFB" property="serialNo"/> --%>
	<html:hidden name="processWiseDesigMappingFB" property="fetchedList"/>
	
	<his:TitleTag name="Process Wise Designation Mapping">	</his:TitleTag>
  	   
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="processType"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			     
		  			&nbsp;<html:select name="processWiseDesigMappingFB" property="processType" tabindex="1" styleClass="regcbo" onchange="getDesignation(this)">
						<html:option value="-1">Select Value</html:option>
	  					<logic:present name="<%=MrdConfig.PROCESS_TYPE_LIST %>">
	  						<html:options  collection="<%=MrdConfig.PROCESS_TYPE_LIST%>" property="value" labelProperty="label" />
	  					</logic:present>
		  			</html:select>
		  		
		  			</div>
			     </td>  
		      </tr>
		  </table>
		  
		  
		  <table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">
							<div align="center"><b>Select Designation </b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfont">
							<div align="right">
								<html:select name="processWiseDesigMappingFB" property="designationId" tabindex="1" multiple="true" size="6">
									<logic:notEqual name="processWiseDesigMappingFB" property="hmode"  value="NEW">
										<logic:present name="<%=MrdConfig.DESIGNATION_LIST_NOT_MAPPED%>">
										<html:options  collection="<%=MrdConfig.DESIGNATION_LIST_NOT_MAPPED%>" property="value" labelProperty="label" />
										</logic:present>
									</logic:notEqual>
								</html:select>		
							</div>
						</td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");' /> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:select name="processWiseDesigMappingFB" property="selectedDesignationId" tabindex="1" multiple="true" size="6">
									<logic:notEqual name="processWiseDesigMappingFB" property="hmode"  value="NEW">
										<logic:present name="<%=MrdConfig.DESIGNATION_LIST_MAPPED%>">
										<html:options  collection="<%=MrdConfig.DESIGNATION_LIST_MAPPED%>" property="value" labelProperty="label" />
										</logic:present>
									</logic:notEqual>
								</html:select>
								
							</div>
						</td>
					</tr>
			</table>
				
      </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			   
			     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
			     <img class="button" src='<his:path src="/../../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
<!--				 <img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' tabindex="1" style="cursor: pointer" onclick="finalSubmit('VIEW')" onkeypress="if(event.keyCode==13) finalSubmit('VIEW')">-->
				 
			</span>
		</his:ButtonToolBarTag>
		<his:status/>
       </html:form>
       </his:TransactionContainer>
  </body>
</html>
		     
		   
		  