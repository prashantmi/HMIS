<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function popup(obj,eventObj)
{
	//var url=obj.value;
	url="/HISClinical/opd/opdDocumentArchival.cnt?hmode=PLAY&fileType=" + obj.value;
	//alert(url)
	openDependentPopup(url,eventObj,400,600,true);
	// window.open('ftp://administrator:hisregistration@10.103.0.20/dir/MOV00254.MPG','popupWindow','status=yes,scrollbars=yes,height=200,width=350,left=10,top=10');
}

</script>

<%@ page import ="opd.*" %>

<his:TransactionContainer>
<his:TitleTag name="Audio Video Player">
</his:TitleTag>
	<his:statusTransactionInProcess>
	<his:ContentTag>
		<table>
		
			<logic:iterate id="filePaths" indexId="idx" name="<%=OpdConfig.ESSENTIAL_BO_UNIT_AUDIO_VIDEO_LIST %>"  type="hisglobal.vo.AudioVideoMasterVO">
			
				<tr>
				
				<td>
				
					 
					<%String fileName=(String)filePaths.getFileName(); 
					String filePath=Config.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+fileName;
					System.out.println("path of file on server"+filePath);%>
					<html:radio name="AudioVideoUploadFB" property="selectedFile" value="<%=filePath%>" onclick="popup(this,event);" tabindex="1"></html:radio>
					<%--<html:radio name="AudioVideoUploadFB" property="selectedFile" value="<%=filePath%>" onclick="submitForm('PLAY');"></html:radio>
				--%>
				<!--<a href="ftp://administrator:hisregistration@10.103.0.20/dir/Croc.wmv">gghghghghg</a>
				
				--></td>
				<td>
					<%=filePaths.getFileHeader() %>
				</td>
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
	</his:statusTransactionInProcess>
	
	
	<his:ButtonToolBarTag>		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" tabindex="1">
	</his:ButtonToolBarTag>


		  			

</his:TransactionContainer>
<html:hidden name="AudioVideoUploadFB" property="hmode"/>
<html:hidden name="AudioVideoUploadFB" property="patCrNo"/>

