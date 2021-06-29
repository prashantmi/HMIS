<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@page import="mrd.MrdConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/mrd/js/recordTypeWiseEnclosureMstAddMod.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script>

</script>

<body>
	<html:form action="/master/recordTypeWiseEnclosureMst">
		<his:TransactionContainer>
			<his:SubTitleTag name="Add Enclosure">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="center">
								<b>
									<bean:message key="user"/>
								</b>	
							</div>
						</td>
						<td width="20%" class="tdfont"></td>
						<td width="40%" class="tdfonthead">
							<div align="center">
								<b>
									<bean:message key="selectedUser"/>
								</b>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="center"> 
								<html:select name="recordTypeWiseEnclosureMstFB" property="enclosureID" multiple="true" size="6">
									<html:options collection ="<%=MrdConfig.LIST_NOT_MAPPED_ENCLOSURE %>" property = "value" labelProperty = "label"/>
								</html:select>
							</div>	
						</td>
						<td width="20%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle();'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll();'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle();'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll();'/>
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="60%" class="tdfonthead">
										<div align="right">
											<html:select name="recordTypeWiseEnclosureMstFB" property="selectedEnclosureID" multiple="true" size="6">
												<logic:present name="<%=MrdConfig.ARR_ADDED_RECORD_TYPE_ENCLOSURE_VO %>">
													<html:options collection="<%=MrdConfig.ARR_ADDED_RECORD_TYPE_ENCLOSURE_VO %>"  property = "enclosureId" labelProperty = "enclosure" />
												</logic:present>	
											</html:select>
										</div>
									</td>
									<td width="40%" class="tdfonthead">
										<div align="left">
											&nbsp;&nbsp;<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP(document.forms[0].selectedEnclosureID);'/>
											<br>
											&nbsp;&nbsp;<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN(document.forms[0].selectedEnclosureID);'/>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</his:ContentTag>	
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>' style="cursor: pointer" onclick="validateOk() && submitTile('POPULATE')" onkeypress="if(event.keyCode==13)validateOk() && submitTile('POPULATE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
			</his:ButtonToolBarTag>
			<html:hidden name="recordTypeWiseEnclosureMstFB" property="hmode"/>
			
		</his:TransactionContainer>		
	</html:form>	
</body>