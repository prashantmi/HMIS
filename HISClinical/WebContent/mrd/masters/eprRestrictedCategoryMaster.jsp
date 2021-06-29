
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page info="Used for addition , modification of epr restricted category master" %>
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
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/mrd/js/eprRestrictedCatMaster.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script>
function submitPage(mode){
	document.getElementsByName("hmode")[0].value=mode
	document.forms[0].submit();
}

</script>

<his:TransactionContainer>
 <body>

  <html:form action="/master/eprRestrictedCategoryMaster" > 
    <his:TitleTag name="Epr Restricted Patient Category">
	</his:TitleTag>
  	   
	 <his:ContentTag>
		  <table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
						<td width="40%"  class="tdfonthead">
						</td>
						<td width="20%"  class="tdfonthead">
							<div align="center"><b>Select Patient Category </b></div>
						</td>
						<td width="40%"  class="tdfonthead">
						</td>
					</tr>
					<tr>
						<td width="40%"  class="tdfont">
							<div align="right">
								<html:select name="eprRestrictedCategoryMasterFB" property="patCatCode" tabindex="1" multiple="true" size="6">
									<logic:present name="<%=MrdConfig.EPR_NOT_ADDED_PAT_CAT_LIST%>">
										<html:options  collection="<%=MrdConfig.EPR_NOT_ADDED_PAT_CAT_LIST%>" property="value" labelProperty="label" />
									</logic:present>
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
								<html:select name="eprRestrictedCategoryMasterFB" property="selectedPatCatCode" tabindex="1" multiple="true" size="6">
									<logic:present name="<%=MrdConfig.EPR_ADDED_PAT_CAT_LIST%>">
										<html:options  collection="<%=MrdConfig.EPR_ADDED_PAT_CAT_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
								
							</div>
						</td>
					</tr>
			</table>
      </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) return submitToSave('SAVE')" onclick="return submitToSave('SAVE')">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		 
		 <his:status/>
        
        <html:hidden name="eprRestrictedCategoryMasterFB" property="hmode"/>
   </html:form>
  </body>
  </his:TransactionContainer>
</html>
		     
		   
		  