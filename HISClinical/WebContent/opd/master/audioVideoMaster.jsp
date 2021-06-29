<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.master.controller.fb.*"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/opd/opdJs/opd.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

function submitTile(mode)
{
	document.getElementsByName("hmode")[0].value=mode;  
	//alert("MODE="+document.getElementsByName("hmode")[0].value);
   	document.forms[0].submit();
}

function submitToUpload(event)
{
	openPopFileUpload(event);
}

function openPopFileUpload(event)
{
	openPopup('<his:path src='/opd/master/addAudioVideoPopup.cnt'/>',event,300,600);
}

function submit4image()
{
	
	elem = document.getElementsByName('hmode')[0];

	elem.value = "FILE";
	document.forms[0].submit();
}

function validateAdd()
{ var valid=true;
	if(document.getElementsByName("fileHeader")[0].value=="")
	{
		alert("Please Enter the File Header");
		document.getElementsByName("fileHeader")[0].focus();
		valid=false;
		
		return valid;
		
	}
	if(document.getElementsByName("uploadFileName")[0].value=="")
	{
		alert("Click on the '+' Button to select a file");
		valid=false;
		return valid;
	}
	else
	{
	submitTile("SAVE");
	valid=true;
	return valid;
	}
	
}
function clearValue()
{
	document.getElementsByName("uploadFileName")[0].value="";
	document.getElementById("label").style.display="none";
}

function validateModify()
{var valid=true;
	if(document.getElementsByName("fileHeader")[0].value=="")
	{
		alert("Please Enter the File Header");
		document.getElementsByName("fileHeader")[0].focus();
		valid=false;
		
		return valid;
	}
	
	if(document.getElementsByName("uploadFileName")[0].value=="")
	{
		alert("Click on the '+' Button to select a file");
		valid=false;
		return valid;
	}
	else
	{
	submitTile("MODIFYSAVE");
	valid=true;
	return valid;
	}
	
}

function displayFile(path,eventObj)
{
	//window.open(path,'popupWindow1','status=yes,scrollbars=yes,height='+screen.availHeight+',width='+screen.availWidth+ ',left=10,top=10,,dependent=yes,resizable=yes');
	openDependentPopup(path,eventObj,400,600,true);
}

</script>

	<body>
		<html:form action="/master/audioVideoMaster">
		
			<his:TransactionContainer>
				
				<logic:equal name="AudioVideoMasterFB" property="hmode" value="ADD">
					<his:TitleTag name="Audio Video Master >> Add">
					</his:TitleTag>
				</logic:equal>
				
				<logic:equal name="AudioVideoMasterFB" property="hmode" value="MODIFY">
					<his:TitleTag name="Audio Video Master >> Modify">
					</his:TitleTag>
				</logic:equal>
			
				<logic:equal name="AudioVideoMasterFB" property="hmode" value="VIEW">
					<his:TitleTag name="Audio Video Master >> View">
					</his:TitleTag>
				</logic:equal>
				
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0"> 
					
					<tr>
							<td width="50%" class="tdfonthead">
							<div align="right">	           
	              					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              					<font color="#FF0000">*
	  								</font>
	              						<b>
	              							<bean:message key="fileHeader"/>
	              						</b>
	              					</font>
	            				</div>
							</td>
							<logic:notEqual name="AudioVideoMasterFB" property="hmode" value="VIEW">
							<td width="50%" class="tdfont">
							<div align="left">	
							<html:text property="fileHeader" name="AudioVideoMasterFB" maxlength="100" onkeypress="return validateAlphabetsOnly(event,this)"></html:text>  
							</div>
							</td>
							</logic:notEqual>
							
							<logic:equal name="AudioVideoMasterFB" property="hmode" value="VIEW">
						<td width="50%" class="tdfont">
							<div align="left">
						<%
							String path = ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" 
									+ ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.OPD_AUDIO_VIDEO_STORAGE_PATH_WINDOWS + "&" 
									+ ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.OPD_AUDIO_VIDEO_STORAGE_PATH_LINUX + "&" 
									+ ServletsUtilityConfig.FILE_NAME + "=" ; 
  						%>
							<a style="cursor: pointer;" onclick="displayFile('<%=Config.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER%><bean:write name="AudioVideoMasterFB" property="uploadFileName"/>',event);" >							
								<bean:write name="AudioVideoMasterFB" property="fileHeader"/>
							</a>
							</div>
							</td>
						</logic:equal>
						
						</tr>
						<tr>
					<logic:notEqual name="AudioVideoMasterFB" property="hmode" value="VIEW">
				    <td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*
	  								</font>
								<b><bean:message key="isDefault"/></b>
							</font>
						</div>
					</td>
					<td width="80%" class="tdfont">
						<div align="left">
						    <html:radio name="AudioVideoMasterFB" property="isDefault" tabindex="1"  value="<%=OpdConfig.NO%>" />No
							<html:radio name="AudioVideoMasterFB" property="isDefault" tabindex="1"  value="<%=OpdConfig.YES%>" />Yes
						</div>
					</td>
					</logic:notEqual>
					<logic:equal name="AudioVideoMasterFB" property="hmode" value="VIEW">
				
				    <td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*
	  								</font>
								<b><bean:message key="isDefault"/></b>
							</font>
						</div>
					</td>
					<td width="80%" class="tdfont">
						<div align="left">
						    <html:radio name="AudioVideoMasterFB" property="isDefault" disabled="true" tabindex="1"  value="<%=OpdConfig.NO%>" />No
							<html:radio name="AudioVideoMasterFB" property="isDefault" disabled="true" tabindex="1"  value="<%=OpdConfig.YES%>" />Yes
						</div>
					</td>
					</logic:equal>
				</tr>
						<tr>
							<td width="50%" class="tdfonthead">
	           					<div align="right">	           
	              					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              					<font color="#FF0000">*
	  								</font>
	              						<b>
	              							<bean:message key="uploadAVFile"/>
	              						</b>
	              					</font>
	            				</div>
      						</td>
      						<td width="50%" class="tdfont" nowrap="nowrap">
      							<div align="left">
      								<logic:notEqual name="AudioVideoMasterFB" property="hmode" value="VIEW">
      									<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>'  border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13)submitToUpload(event);" onClick="submitToUpload(event);" >
      								</logic:notEqual>
      								<label id="label">
      									<b>&nbsp;
      									<logic:notEmpty name="AudioVideoMasterFB" property="uploadFileName">
   	   									File Attached
   	   									</logic:notEmpty>
     	 								</b>
      									<html:hidden name="AudioVideoMasterFB" property="uploadFileName"/>
      								</label>
      							</div>
      						</td>		
						</tr>
						
						
						
						
					</table>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0"> 	
						
						<tr>
							<td width="100%" class="tdfont">
	           					<div align="center">	           
	              					<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              						<b>
	              							<bean:message key="uploadingAVFilemsg"/>
	              						</b>
	              					</font>
	            				</div>
      						</td>
      						
						</tr>
					</table>
				</his:ContentTag>
				
				<his:ButtonToolBarTag>
					<logic:equal name="AudioVideoMasterFB" property="hmode" value="ADD">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick ="validateAdd() && submitTile('SAVE')" onkeypress="if(event.keyCode==13) validateAdd() && submitTile('SAVE')">
					</logic:equal>
		
					<logic:equal name="AudioVideoMasterFB" property="hmode" value="MODIFY">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick ="validateModify() && submitTile('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateModify() && submitTile('MODIFYSAVE')">
					</logic:equal>	
		
    			
    				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
    		
    		
 			   		<logic:equal name="AudioVideoMasterFB" property="hmode" value="ADD">
    					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitTile('ADD')" onkeypress="if(event.keyCode==13) submitTile('ADD');">
    				</logic:equal>	
    		
   			 		<logic:equal name="AudioVideoMasterFB" property="hmode" value="MODIFY">
    					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="clearValue()" onkeypress="clearValue();">
    				</logic:equal>
				</his:ButtonToolBarTag>
				<center><b><his:status/></b></center>
			</his:TransactionContainer>
			
			
			<html:hidden name="AudioVideoMasterFB" property="hmode"/>
			<html:hidden name="AudioVideoMasterFB" property="tempMode"/>
			<html:hidden name="AudioVideoMasterFB" property="chk"/>
		
		</html:form>
		
	</body>

</html>
