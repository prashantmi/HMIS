
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<%@page import="mrd.MrdConfig"%>

<script type="text/javascript">

function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

function getUploadedeDoc(event,docId,docType)
{
	//alert("Hello OPEN Doc")
	//openDependentPopup(path,event,300,600,'yes');	
	var left=(screen.width-600)/2;
	var top=(screen.height-400)/2;
	window.open(createFHashAjaxQuery("/HISClinical/opd/opdDocumentArchival.cnt?hmode=VIEWDOC&documentCode="+docId+"&documentDirectoryPath="+docType),"popup","height=500,width=600,left="+left+",top="+ top +",scrollbars=yes,location=no");
}


</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html:form action="/emrDesk">


<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
 <his:TitleTag name="Uploaded Document" >
</his:TitleTag>

	<his:ContentTag>
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
					<td width="30%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="uploadTime" />
							</font>
						</div>
					</td>
					
					
					<td width="30%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="documentType" />
							</font>
						</div>
					</td>
					
					<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="documentTitle" />
							</font>
						</div>
					</td>
				
	   </tr>
	   	<logic:present name="<%=MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY%>" >
		<logic:notEmpty name="<%=MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY%>" >
	   <%String indexEpisodeOpen="-1"; %>
	   	<logic:iterate id="docUploadVOid" indexId="idx" name="<%=MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY%>" type="hisglobal.vo.DocumentUploadDtlVO">
			<bean:define id="documentCodeValue" name="docUploadVOid" property="documentCode" />
			<bean:define id="fileTypeValue" name="docUploadVOid" property="fileType" />
					<%//String color="#000000"; %>
					<c:set var="color" value="#000000"></c:set>				
					<tr>
						<td class="tdfontheadExam" width="30%"> 
							<div align="center">
							<b>
							<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="docUploadVOid"  property="entryDate" />
							</font>
							</b>
							</div>
						</td>
			
					<td class="tdfontheadExam" width="30%">
							<div align="center">
							<b>
							<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write  name="docUploadVOid"  property="fileType" />
							</font>
							</b>
							</div>
						</td>
						<td class="tdfontheadExam" width="40%"> 
							<div align="center">
							<b>
							<font color="${color}" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								
								<a style="cursor:pointer" onclick="getUploadedeDoc(event,'<%=documentCodeValue%>','<%=fileTypeValue%>')"><b><bean:write  name="docUploadVOid"  property="documentTitle" /></b></a>
							</font>
							</b>
							</div>
						</td>
						
				</tr>
				<%indexEpisodeOpen=idx.toString(); %>
				
			</logic:iterate>
		
			<%if(indexEpisodeOpen.equals("-1")){%>
			<his:ContentTag>
				<tr>
					<td class="tdfont" width="25%" colspan="5" nowrap>
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>	<bean:message key="noDocFound" /></b></font>
						</div>
					</td>
				</tr>
			</his:ContentTag>
			<%} %>
				</logic:notEmpty>
    		</logic:present>
    </table>
    
    <logic:notPresent name="<%=MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY%>" >
    	<his:ContentTag>
   			<tr>
				<td class="tdfont" width="100%"  nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	<bean:message key="noDocFound" /></b></font>
					</div>
				</td>
			</tr>
		</his:ContentTag>
    </logic:notPresent>
   
</his:ContentTag>


<his:SubTitleTag>
		
	</his:SubTitleTag>
	
</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 