<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

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
	openPopup('<his:path src='/opd/master/imageUpload.cnt'/>',event,300,600);
}
function submit4image()
{
	
	elem = document.getElementsByName('hmode')[0];

	elem.value = "IMAGE";
	document.forms[0].submit();
}
function validateAddImage()
{
	if(document.getElementsByName("imageName")[0].value=="")
	{
		alert("Please Enter the Image Name");
		document.getElementsByName("imageName")[0].focus();
	}
	else if(document.getElementsByName("uploadImageName")[0].value=="")
	{
		alert("Click on the '+' Button to select a image");
	}
	else
	submitTile("SAVE");
}

function validateModifyImage()
{
	if(document.getElementsByName("imageName")[0].value=="")
	{
		alert("Please Enter the Image Name");
		document.getElementsByName("imageName")[0].focus();
	}
	else if(document.getElementsByName("uploadImageName")[0].value=="")
	{
		alert("Click on the '+' Button to select a image");
	}
	else
	submitTile("MODIFYSAVE");
}
function clearValue()
{
	document.getElementsByName("imageName")[0].value="";
	document.getElementsByName("uploadImageName")[0].value="";
	document.getElementById("label").style.display="none";
}

function getUploadedImage(event,path)
{
	var chk=document.getElementsByName("chk")[0].value
	var path = "/HISClinical/opd//master/addImageUploadMaster.cnt?hmode=VIEWIMAGE&chk="+chk; 
	openDependentPopup(path,event,600,700,'yes');
}

</script>

<body>
	<html:form action="/master/addImageUploadMaster">
	<%!boolean readOnly; %>
   <% this.readOnly=false; %>
   <logic:equal name="AddImageUploadMasterFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   		</logic:equal>
		<his:TransactionContainer>
		
		<logic:equal name="AddImageUploadMasterFB" property="hmode" value="ADD">
			<his:TitleTag name="Image Upload Master >> Add">			
			</his:TitleTag>
		</logic:equal>
		
		<logic:equal name="AddImageUploadMasterFB" property="hmode" value="MODIFY">
			<his:TitleTag name="Image Upload Master >> Modify">			
			</his:TitleTag>
		</logic:equal>
		
		<logic:equal name="AddImageUploadMasterFB" property="hmode" value="VIEW">
		
			<his:TitleTag name="Image Upload Master >> View">
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
	              					<bean:message key="image"/>
	              					<bean:message key="name"/>
	              				</b>
	              			</font>
	            		</div>
	            		
      				</td>
      					<logic:notEqual name="AddImageUploadMasterFB" property="hmode" value="VIEW">
      				<td width="50%" class="tdfont">
      					
      					<div align="left">
      						<html:text name="AddImageUploadMasterFB" property="imageName" maxlength="50" size="35" onkeypress="return validateAlphabetsOnly(event,this)" >
							</html:text>
      					</div>
      					
      				</td>  
      				</logic:notEqual>
      					<logic:equal name="AddImageUploadMasterFB" property="hmode" value="VIEW">
      				<td width="50%" class="tdfont">
      					
      					<div align="left">
      						<bean:write  name="AddImageUploadMasterFB" property="imageName" />
      					</div>
      					
      				</td>  
      				</logic:equal>
				</tr>
					<tr>
					<logic:notEqual name="AddImageUploadMasterFB" property="hmode" value="VIEW">
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
						    <html:radio name="AddImageUploadMasterFB" property="isDefault" tabindex="1"  value="<%=OpdConfig.NO%>" />No
							<html:radio name="AddImageUploadMasterFB" property="isDefault" tabindex="1"  value="<%=OpdConfig.YES%>" />Yes
						</div>
					</td>
					</logic:notEqual>
					<logic:equal name="AddImageUploadMasterFB" property="hmode" value="VIEW">
				
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
						    <html:radio name="AddImageUploadMasterFB" property="isDefault" disabled="true" tabindex="1"  value="<%=OpdConfig.NO%>" />No
							<html:radio name="AddImageUploadMasterFB" property="isDefault" disabled="true" tabindex="1"  value="<%=OpdConfig.YES%>" />Yes
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
	              					<bean:message key="uploadImage"/>
	              				</b>
	              			</font>
	            		</div>
      				</td>
      				<td width="50%" class="tdfont" nowrap="nowrap">
      					<div align="left">
      						<logic:notEqual name="AddImageUploadMasterFB" property="hmode" value="VIEW">
      						<img class="button" src='<his:path src="hisglobal/images/plus.gif"/>'  border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13)submitToUpload(event);" onClick="submitToUpload(event);" >
      						</logic:notEqual>
      						
      						<%-- <%
      							String viewImage=(String)request.getAttribute("ImageToBeViewed");
      						
      							//String path="/HISClinical/ShowFileFromDir?"+OpdConfig.DOCUMENT_STORAGE_PATH+"="+Config.OPD_EXAMINATION_IMAGE_PATH+"&"+OpdConfig.FILE_NAME+"="+viewImage+"&"+OpdConfig.IS_EXTENSION+"="+OpdConfig.IS_EXTENSION_FALSE;
      							//String path="/HISClinical/ShowFileFromDir?"+OpdConfig.DOCUMENT_STORAGE_PATH+"="+Config.IMAGE_EXAMINATION_IMAGE_PATH_WINDOWS+"&"+OpdConfig.FILE_NAME+"="+viewImage+"&"+OpdConfig.IS_EXTENSION+"="+OpdConfig.IS_EXTENSION_FALSE;
      						%>
      						
      						<%
							String path = ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" 
									+ ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.IMAGE_EXAMINATION_IMAGE_PATH_WINDOWS + "&" 
									+ ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.IMAGE_EXAMINATION_IMAGE_PATH_LINUX + "&" 
									+ ServletsUtilityConfig.FILE_NAME + "="; 
  						%> --%>
      						
      						<logic:equal name="AddImageUploadMasterFB" property="hmode" value="VIEW">
      							<label id="label" >
      								<a style="cursor:pointer" onclick="getUploadedImage(event,'<bean:write name="AddImageUploadMasterFB" property="uploadImageName"/>')" >
      									<B>
      										View Image
      										<!--<bean:write name="AddImageUploadMasterFB" property="uploadImageName"/>-->
      									</B>
      								</a>
      								<html:hidden name="AddImageUploadMasterFB" property="uploadImageName"/>
      							</label>
   	   						</logic:equal>
   	   						<logic:notEqual name="AddImageUploadMasterFB" property="hmode" value="VIEW">
   	   							<label id="label" >
   	   								<B>
   	   									<logic:notEmpty name="AddImageUploadMasterFB" property="uploadImageName">
   	   									Image Attached
   	   									</logic:notEmpty>
      									<!--<bean:write name="AddImageUploadMasterFB" property="uploadImageName"/>-->
      								</B>
      								
      								<html:hidden name="AddImageUploadMasterFB" property="uploadImageName"/>
      							</label>
   	   						</logic:notEqual>
   	   							
      					</div>
      				</td> 
				</tr>
			
			<logic:notEqual name="AddImageUploadMasterFB" property="hmode" value="ADD">
				
				<tr>
			        <td width="50%" class="tdfonthead">
			         	<div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b>	<bean:message key="isActive"/></b>
					  		 </font>
				    	 </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     <html:select name="AddImageUploadMasterFB" property="isValid" disabled="<%=this.readOnly %>" styleClass="regcbo">
							<html:option value="<%=Config.IS_VALID_ACTIVE%>">Active</html:option>
							<html:option value="<%=Config.IS_VALID_INACTIVE%>">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
				</logic:notEqual>
			</table>
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
			<logic:equal name="AddImageUploadMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="hisglobal/images/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="validateAddImage() && submitTile('SAVE')" onkeypress="if(event.keyCode==13) validateAddImage() && submitTile('SAVE')">
			</logic:equal>
		
			<logic:equal name="AddImageUploadMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="hisglobal/images/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="validateModifyImage() && submitTile('MODIFYSAVE')" onkeypress="if(event.keyCode==13) validateModifyImage() && submitTile('MODIFYSAVE')">
			</logic:equal>	
		
    			
    			<img class="button" src='<his:path src="hisglobal/images/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
    		
    		
    		<logic:equal name="AddImageUploadMasterFB" property="hmode" value="ADD">
    			<img class="button" src='<his:path src="hisglobal/images/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('ADD')" onkeypress="if(event.keyCode==13) submitTile('ADD');">
    		</logic:equal>	
    		
    		<logic:equal name="AddImageUploadMasterFB" property="hmode" value="MODIFY">
    			<img class="button" src='<his:path src="hisglobal/images/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="clearValue()" onkeypress="clearValue();">
    		</logic:equal>
		</his:ButtonToolBarTag>
	<html:hidden name="AddImageUploadMasterFB" property="transactionMode"/>
	<html:hidden name="AddImageUploadMasterFB" property="chk"/>
	<html:hidden name="AddImageUploadMasterFB" property="slNo"/>
	<html:hidden name="AddImageUploadMasterFB" property="hmode"/>
	
		
	<center><b><his:status/></b></center>
	</his:TransactionContainer>
	</html:form>
	
</body>


</html>