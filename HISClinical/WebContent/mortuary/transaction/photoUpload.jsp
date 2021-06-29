<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

/*window.onload=function()
{
	var index=document.getElementsByName("index")[0].value;
	document.getElementsByName("isDefaultImage")[index].checked=true;
}*/
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function getUploadedeFile(event,path)
{
	var url = "/HISClinical/mortuary/photoUpload.cnt?hmode=VIEW&imageHeader="+path;
	openDependentPopup(url,event,300,600,'yes');
	
}

function submitToUpload(event)
{
	if(document.getElementsByName("imageHeader")[0].value=="")
	{
		alert("Please Enter The Image Name");
		document.getElementsByName("imageHeader")[0].focus();
	}
	else
		openPopup('<his:path src='registration/uploadFile.cnt'/>',event,300,600);
}

function submit4image()
{
	elem = document.getElementsByName('hmode')[0];
	elem.value = "REFRESHFORIMAGE"; 
	document.forms[0].submit();
}

function deleteRow(key)
{	
	document.getElementsByName("removeImageIndex")[0].value=key;
	submitForm("REMOVE");
}

function validateSave()
{
	var valid=false;
	if(document.getElementsByName("imageAddedFlag")[0].value=="0")
	{
		alert("Please Add a Photo To Upload");
		valid=false;
	}
	else
	{
		valid=true;
	}
	return valid;
}
 
/*function validateIsDefault()
{
	var valid = false;
	var count=0;
	for(var i=0;i<document.getElementsByName("isDefaultImage").length;i++)
	{
		if(document.getElementsByName("isDefaultImage")[i].checked)
			count++;
	}
	
	if(count==0)
	{
		alert("Please Select a Default Image");
		valid = false;
	}
	else
		valid = true;
	
	return valid;
}*/

function clearForm()
{
	document.getElementsByName("imageHeader")[0].value="";
	document.getElementsByName("uploadRemarks")[0].value="";
}

function viewImageRemarks(obj)
{
	document.getElementsByName("viewRemarks")[0].value=document.getElementsByName("hiddenRemarks")[obj].value;
	document.getElementById("divViewRemarks").style.display="block"; 
}

function hideRemarksDiv()
{
	document.getElementsByName("viewRemarks")[0].value="";
	document.getElementById("divViewRemarks").style.display="none"; 
}

</script>

<his:TransactionContainer>
	<his:TitleTag name="Photo Upload">
	</his:TitleTag>
	
	<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
	
	<his:statusTransactionInProcess>
		<his:SubTitleTag name="Uploaded Photo">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
			<%if(session.getAttribute(MortuaryConfig.ARR_DECEASED_ADDED_PHOTO_VO)!=null) {%>
				<tr>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="date"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="image"/>
									<bean:message key="name"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="imageUploadForm"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="image"/>
									<bean:message key="remarks"/>
								</b>	
							</font>
						</div>
					</td>
				</tr>
				
					<logic:iterate id="arrDeceasedImageVO" name="<%=MortuaryConfig.ARR_DECEASED_ADDED_PHOTO_VO %>" type="hisglobal.vo.MortuaryDeceasedImageDtlVO" indexId="idx">
						<tr>
							<td width="20%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=arrDeceasedImageVO.getEntryDate() %>
									</font>
								</div>
							</td>
							<%
								String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX; 
								//String imagepath="/HISClinical/image/showImage?"+ imageIndex+"="+idx;
								String imagepath="/image/showImage?"+ imageIndex+"="+idx;
		  					%>
							<td width="30%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												
										<a style="cursor:pointer" onclick="getUploadedeFile(event,'<%=imagepath%>')" > 
											 <%=arrDeceasedImageVO.getImageHeader() %>
										</a>
												
									</font>	
								</div>
							</td>
							<td width="20%" class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrDeceasedImageVO.getUploadMode() %>
									</font>
								</div>
							</td>
							<td width="30%" class="tdfonthead">
								
								<div id="divImageRemarksId" style="display: none;">
								<%String remarks="No Remarks"; %>
									<html:hidden name="PhotoUploadFB" property="hiddenRemarks" value="<%=arrDeceasedImageVO.getUploadRemarks()==null?remarks:arrDeceasedImageVO.getUploadRemarks() %>"/>
								</div>	
								<div align="center">
									<%if(arrDeceasedImageVO.getUploadRemarks()!=null){ %>
									<a style="cursor:pointer" onclick="viewImageRemarks('<%=idx%>')" >
										VIEW REMARKS	
									</a>
									<%}else{ %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											-
										</font>
									<%} %>
								</div>
							</td>
						</tr>
					</logic:iterate>	
				<%} else{%>
					<tr>
						<td class="tdfont" colspan="3">
							<div align="center">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										No Uploaded Photo Found
									</b>
								</font>	
							</div>
						</td>
					</tr>
				<%} %>
			</table>
		</his:ContentTag>
				
		
		<his:SubTitleTag name="Upload New Photo">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<b>
									<bean:message key="image"/>
									<bean:message key="name"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="60%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="image"/>
									<bean:message key="remarks"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
						<div align="center">
							&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td width="30%" class="tdfont">
						<div align="center">
							<html:text name="PhotoUploadFB" property="imageHeader" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
						</div>
					</td>
					<td width="60%" class="tdfont">
						<div align="center">
							<html:textarea name="PhotoUploadFB" property="uploadRemarks" rows="1" cols="80" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" tabindex="1"></html:textarea>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<img class="button"  class="button" src="../hisglobal/images/plus.gif" tabindex="1" border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13)submitToUpload(event);" onClick="submitToUpload(event);" >
						</div>
					</td>
				</tr>
			</table>	
			<%if(session.getAttribute(MortuaryConfig.ARR_NEW_ADDED_IMAGE)!=null){	%>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<logic:iterate id="addedImage" name="<%=MortuaryConfig.ARR_NEW_ADDED_IMAGE %>" type="hisglobal.vo.MortuaryDeceasedImageDtlVO" indexId="index">
						<tr>
							<td width="30%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%String key=Config.IMAGE_BYTE_ARRAY_KEY; %>
										<%String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX;%>
										<%
										//String imagepath="/HISClinical/image/showImage?"+imageIndex+"="+index+"&"+key+"="+MortuaryConfig.UPLOADED_IMAGE_IN_SESSION;
										String imagepath="/image/showImage?"+imageIndex+"="+index+"&"+key+"="+MortuaryConfig.UPLOADED_IMAGE_IN_SESSION;
										%>											
										<a style="cursor:pointer" onclick="getUploadedeFile(event,'<%=imagepath%>')" > 
											<b>
												<%=addedImage.getImageHeader() %>
											</b>
										</a>		
									</font>
								</div>
							</td>
							<td width="60%" class="tdfont">
								<div align="center">
									<%=addedImage.getUploadRemarks().equals("")?"-":addedImage.getUploadRemarks() %>
								</div>
							</td>
							<td width="10%" class="tdfont">
								<div align="center">
									<img class="button"  class="button" src="../hisglobal/images/minus.gif"  border="0" style="cursor:pointer" tabindex="1" onkeypress="if(event.keyCode==13)deleteRow('<%=index.toString() %>');" onClick="deleteRow('<%=index.toString() %>');" tabindex="1"  >
								</div>
							</td>
						</tr>
					</logic:iterate>
				</table>	
				<%} %>
		</his:ContentTag>				
		
	</his:statusTransactionInProcess>		


	<his:ButtonToolBarTag>
		
		<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
		</his:statusTransactionInProcess>
		
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusUnsuccessfull>
	
	</his:ButtonToolBarTag>
	
	<div id="divViewRemarks" style="display: none;">
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="98%" >
						<div align="center">
							<html:textarea name="PhotoUploadFB" property="viewRemarks" rows="5" cols="145" readonly="true"></html:textarea>
							
						</div>
						
					</td>
					<td width="2%" valign="top">
						<div align="right" >
							<img class="button"  class="button" src="../hisglobal/images/stop.png"  border="0" style="cursor:pointer" tabindex="1" onclick="hideRemarksDiv()" onkeypress="if(event.keyCode==13) hideRemarksDiv()" >
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>		
	</div>
</his:TransactionContainer>	

		<html:hidden name="PhotoUploadFB" property="hmode"/>
		<html:hidden name="PhotoUploadFB" property="deceasedNo"/>
		<html:hidden name="PhotoUploadFB" property="patCrNo"/>
		<html:hidden name="PhotoUploadFB" property="index"/>
		<html:hidden name="PhotoUploadFB" property="removeImageIndex"/>
		<html:hidden name="PhotoUploadFB" property="imageAddedFlag"/>
		