<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="mrd.MrdConfig"%>
<%@page import="medicalboard.MedicalBoardConfig"%>
<html

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>>
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/mrd/js/processWiseDesigMapping.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/medicalboard/js/certificateBoardMstAddMod.js" />
<his:css src="/css/calendar-blue2.css" />
 <body>
 
 
<his:TransactionContainer>
  <html:form action="/certificateBoardMapping" > 
   
    <html:hidden name="CertificateBoardMstFB" property="hmode"/>
    <html:hidden name="CertificateBoardMstFB" property="oldSelectedBoardId"/>
    
	
	<his:TitleTag name="Certificate Wise Board Mapping"> </his:TitleTag>
  	   
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="certificatetype"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			     
		  			&nbsp;<html:select name="CertificateBoardMstFB" property="certificateTypeID" tabindex="1" styleClass="regcbo" onchange="getBoard(this)">
						<html:option value="-1">Select Value</html:option>
	  					<logic:present name="<%=MedicalBoardConfig.CERTIFICATE_TYPE_LIST %>">
	  						<html:options  collection="<%=MedicalBoardConfig.CERTIFICATE_TYPE_LIST%>" property="value" labelProperty="label" />
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
							<div align="center"><b>Select Board</b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfont">
							<div align="right">
								<html:select name="CertificateBoardMstFB" property="boardID" tabindex="1" multiple="true" size="6">
									<logic:notEqual name="CertificateBoardMstFB" property="hmode"  value="NEW">
										<logic:present name="<%=MedicalBoardConfig.UNSELECTED_BOARD_LIST%>">
										<html:options  collection="<%=MedicalBoardConfig.UNSELECTED_BOARD_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</logic:notEqual>
								</html:select>		
							</div>
						</td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle();'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll();' /> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle();'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll();'/> 	
							</div>
						</td>
						<td width="40%"  class="tdfont">
							<div align="left">
								<html:select name="CertificateBoardMstFB" property="selectedBoardId" tabindex="1" multiple="true" size="6">
									<logic:notEqual name="CertificateBoardMstFB" property="hmode"  value="NEW">
										<logic:present name="<%=MedicalBoardConfig.SELECTED_BOARD_LIST%>">
										<html:options  collection="<%=MedicalBoardConfig.SELECTED_BOARD_LIST%>" property="value" labelProperty="label" />
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
			     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		<logic:present name="<%=MedicalBoardConfig.UNSELECTED_BOARD_LIST %>">
		<logic:empty name="<%=MedicalBoardConfig.UNSELECTED_BOARD_LIST  %>">
			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<b>No New Board Found </b>
			</font>	
		</logic:empty>		
		</logic:present>
		<his:status/>
       </html:form>
       </his:TransactionContainer>
  </body>
</html>
		     
		   
		  