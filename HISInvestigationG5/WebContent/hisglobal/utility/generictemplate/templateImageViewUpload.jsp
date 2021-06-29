<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import ="hisglobal.presentation.*" %>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/popup.js"/>

<script>
function setImage()
{
	var imageName = document.getElementsByName("uploadImageName")[0].value;	
	if(imageName=="")
	{
		alert('Please Select A File');
		return;
	}
	var ext = imageName.substring(imageName.lastIndexOf("."),imageName.length).toUpperCase();
	if(ext==".JPG" || ext==".JPEG" || ext==".GIF" || ext==".PNG" || ext==".BMP")
	{
		document.getElementsByName('extension')[0].value = ext;
		document.getElementsByName('transactionMode')[0].value = "SAVE" ;
		document.forms[0].submit();	
	}
	else
	    alert('Please Select Files With Formats : GIF/JPG/JPEG/PNG/BMP');
}

function onFormLoad()
{
	var formclose=true;
  	<%     
		Status objStatus=(Status)request.getAttribute(Config.STATUS_OBJECT);
    	if(objStatus.contains(Status.NEW))
    	{
   	%>    	
    		formclose=false;    	   	
   	<%
	    }    
    %>
    if(formclose)
    {
    	if(!window.opener.closed)
    	{    		
    		opener.ImageUploadControl.setUploadedImage(document.getElementsByName('row')[0].value,document.getElementsByName('col')[0].value,document.getElementsByName('extension')[0].value);
    		    	
	    	self.close();
    	}
    }
}
</script>

<body onload="onFormLoad()">
	<html:form enctype="multipart/form-data" action="/templateImageViewUpload.cnt" method="post">
		<his:TransactionContainer>
			<his:ContentTag>
				<table>
					<tr>
						<td class="tdfonthead">
							<b>
								<bean:message key="selectImage" />
							</b>
							<html:file name="TemplateImageViewUploadFB" property="uploadImageName" ></html:file>
						</td>
					</tr>
				</table>
			</his:ContentTag>
			
			<his:ButtonToolBarTag>
				<html:button value="Attach" property="mybutton" onclick="setImage()"/>
			</his:ButtonToolBarTag>
		</his:TransactionContainer>
		<html:hidden name="TemplateImageViewUploadFB" property="transactionMode"/>
		<html:hidden name="TemplateImageViewUploadFB" property="row"/>
		<html:hidden name="TemplateImageViewUploadFB" property="col"/>
		<html:hidden name="TemplateImageViewUploadFB" property="extension"/>
	</html:form>
</body>
