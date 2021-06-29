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
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/mrd/masters/rackShelfMstMod.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />

 <body>
  <html:form action="/master/rackShelfMst" > 
    <html:hidden name="rackShelfMstFB" property="hmode"/>
	<html:hidden name="rackShelfMstFB" property="serialNo"/>
	<html:hidden name="rackShelfMstFB" property="fetchedList"/>
	<html:hidden name="rackShelfMstFB" property="rackId"/>
	<html:hidden name="rackShelfMstFB" property="chk"/>
   <his:TitleTag name="Rack Shelf Master">  </his:TitleTag>
   	 <his:ContentTag>
	 
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="rackName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			      	&nbsp;<b><bean:write name="rackShelfMstFB" property="rackName"/></b> 
			      	
		  			</div>
			     </td>  
		      </tr>
		  </table>
		  <logic:notEqual name="rackShelfMstFB" property="hmode" value="VIEW">
		   <table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="40%"  class="tdfonthead"></td>
					<td width="20%"  class="tdfonthead">
						<div align="center"><b>Select Shelf </b></div>
					</td>
					<td width="40%"  class="tdfonthead"></td>
				</tr>
				
				<tr>
					<td width="40%"  class="tdfont">
						<div align="right">
							<html:select name="rackShelfMstFB" property="shelfId" multiple="true" size="6">
								<logic:present name="<%=MrdConfig.NOT_ASSIGNED_SHELF_TO_RACK%>">
	  								<html:options  collection="<%=MrdConfig.NOT_ASSIGNED_SHELF_TO_RACK%>" property="value" labelProperty="label" />
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
							<html:select name="rackShelfMstFB" property="selectedShelfId" multiple="true" size="6">
							<logic:present name="<%=MrdConfig.ASSIGNED_SHELF_TO_RACK%>">
								<html:options  collection="<%=MrdConfig.ASSIGNED_SHELF_TO_RACK%>" property="value" labelProperty="label" />
							</logic:present>
							</html:select>
						</div>
					</td>
				</tr>
				</table>
				</logic:notEqual>
				<logic:equal name="rackShelfMstFB" property="hmode" value="VIEW">
				   <table width="100%" border="0"  cellspacing="1" cellpadding="0">
				   <tr>
						<td width="100%"  class="tdfonthead">
						<div align="center"><b> Shelf </b></div>
						</td>
					
					</tr>
					<tr>
						<td width="100%"  class="tdfont">
							<div align="center">
							<html:select name="rackShelfMstFB" property="selectedShelfId" multiple="true" size="6">
							<logic:present name="<%=MrdConfig.ASSIGNED_SHELF_TO_RACK%>">
								<html:options  collection="<%=MrdConfig.ASSIGNED_SHELF_TO_RACK%>" property="value" labelProperty="label" />
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
			  	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		 <his:status/>
 	 </html:form>
  </body>
</html>
		     
		   
		  