<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mortuary.MortuaryConfig"%>
<%@page import="mortuary.transaction.controller.fb.MortuaryImageUploadFB"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function submitToUpload(event){
	
	document.getElementsByName("hmode")[0].value="UPLOADIMAGE"
	var url='<his:path src='/registration/uploadFile.cnt'/>'
	openPopup(url,event, 250, 400)
	
}
function submit4image(){
	document.getElementsByName("hmode")[0].value="UPLOADIMAGE"
	document.forms[0].submit();
}


function removeImage(index)
{
	document.getElementsByName("hmode")[0].value="REMOVEIMAGE"
	document.getElementsByName("removeImageIndex")[0].value=index
	document.forms[0].submit();
}

function validateSave(mode)
{
	var valid=false
	var isDefault=document.getElementsByName("isDefault")
	for(var i=0;i<isDefault.length;i++){
		//alert(isDefault[i].value)
		if(isDefault[i].checked){
			valid=true;
			break;
			//document.getElementsByName("isDefault")[i].value="1"
		}
		//else{	
			//valid=false;
			//document.getElementsByName("isDefault")[i].value="0"
		//}
	}
	if(!valid){
		alert("Please select one Image as Default");
		return false;
	}
	if(document.getElementsByName("imageHeader")){
		for(var i=0;i<document.getElementsByName("imageHeader").length;i++){
			if(!isEmpty(document.getElementsByName("imageHeader")[i],"Image Header")){
				return false;
			}
		}
	}
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}
function deleteImage(mode)
{
	var valid=false
	var selectedImage=document.getElementsByName("chk")
	for(var i=0;i<selectedImage.length;i++){
		if(selectedImage[i].checked){
			if(selectedImage[i].value.split("#")[2]=="1")
			{
				alert("You cannot delete a default Image.Please make another image default in order to delete this Image.")
				return false;
			}
			valid=true;
		}
	}
	if(!valid){
		alert("Please select an image to delete");
		return false;
	}
	
	if(window.confirm("Are you sure want to delete selected images?")){
		document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
	}
}

function modifyImage(mode)
{
	if(window.confirm("Are you sure want to modify ?")){
		document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
	}
}

function validateDeceasedNo()
{
	var valid=false;
	
	if(document.getElementsByName("deceasedNo")[0].value=="")
	{
		alert("Please Enter The Deceased No");
		document.getElementsByName("deceasedNo")[0].focus();
		valid=false;
	}
	else
	{
        if(document.getElementsByName("deceasedNo")[0].value.length==14)
			valid=true;
		else
		{
		    alert("Invalid Deceased No");
			document.getElementsByName("deceasedNo")[0].focus();
			valid=false;
			
		}
	}
	return valid;
}

function openSearchPopup(event)
{
	var path='/HISClinical/mortuary/deceasedGeneralAppearance.cnt?hmode=SEARCHPOPUP';
	openPrintPopup(path,300,700);
}

function openPrintPopup(url, height, width)
{
	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
}

</script>
<%try{ %>
<body>
<his:TransactionContainer>
	<html:form action="/mortuaryImageUpload">
			<his:TitleTag name="Deceased Image Upload">
			</his:TitleTag>
		<his:statusNew>
		<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="20%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<bean:message key="deceasedNo"/>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="left">
								<html:text name="mortuaryImageUploadFB" property="deceasedNo" maxlength="14" size="17" tabindex="1"  onkeypress= "return validateNumeric(event,this) " />
							</div>
						</td>
						<td width="40%" class="tdfont">
							<div align="left">
								<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style=cursor:pointer tabindex="1" onclick="if(validateDeceasedNo()) submitPage('DECEASEDDTL')" onkeypress="if(event.keyCode==13){if(validateDeceasedNo()) submitPage('DECEASEDDTL')}">
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
								<img class="button" src='<his:path src="/hisglobal/images/btn-search.png"/>' style=cursor:pointer tabindex="1" onclick="openSearchPopup(event)" onkeypress="if(event.keyCode==13){openSearchPopup(event)}">
							</div>
						</td>
					</tr>	
				</table>
		</his:ContentTag>
		</his:statusNew>
				<his:statusTransactionInProcess>	
				<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
				
				<logic:present name="<%= MortuaryConfig.ARR_DECEASED_EXISTING_IMAGE_VO %>">
					<his:SubTitleTag name="Previously Uploaded Images">
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="5%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="select"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="image"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="header"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="uploadedDate"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="remarks"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="5%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="default"/>
											</b>	
										</font>
									</div>
								</td>
								<td  width="5%" class="tdfonthead"></td>
							</tr>
							<logic:iterate name="<%= MortuaryConfig.ARR_DECEASED_EXISTING_IMAGE_VO %>" id="deceasedImageVO" type="hisglobal.vo.MortuaryDeceasedImageDtlVO" indexId="index">
								<tr>
									<td width="5%" class="tdfont">
										<div align="center">
											<html:checkbox name="mortuaryImageUploadFB" property="chk" value="<%=deceasedImageVO.getDeceasedNo()+'#'+deceasedImageVO.getSlNo()+'#'+deceasedImageVO.getIsDefaultImage()%>" tabindex="1"></html:checkbox>
										</div>
									</td>
									
									<td  width="25%" style="height: 20px" class="tdfont">
										<div align="center">
											<%String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX; %>
											<%String imagepath="/image/showImage?"+ imageIndex+"="+index; %>
											<%--<img src='<his:path src="<%=imagepath%>"/>' height="50%" width="50%" title="Click To Enlarge" alt="No image Loaded" onkeypress="if(event.keyCode==13) 
											openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event)"  style="cursor: pointer"
											onclick="openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event)" />
											--%>
											<img src='<his:path src="<%=imagepath%>"/>' height="50%" width="50%" alt="No image Loaded" />
										
										</div>
									</td>
									<td  width="20%" class="tdfont">
										<div align="center">
											<bean:write name="deceasedImageVO" property="imageHeader"/>
										</div>
									</td>
									<td  width="20%" class="tdfont">
										<div align="center">
											<bean:write name="deceasedImageVO" property="entryDate"/>
										</div>
									</td>
									<td  width="20%" class="tdfont">
										<div align="center">
											<bean:write name="deceasedImageVO" property="uploadRemarks"/>
										</div>
									</td>
									<td  width="5%" class="tdfont">
										<div align="center">
										<logic:equal name="deceasedImageVO" property="isDefaultImage" value="<%=MortuaryConfig.IS_DEFAULT_IMAGE_YES %>" >
										<input type="radio" name="isDefault" checked="checked" value="<%=MortuaryConfig.IMAGE_EXIST_YES +"#"+ index%>" tabindex="1">
										</logic:equal>
										<logic:equal name="deceasedImageVO" property="isDefaultImage" value="<%=MortuaryConfig.IS_DEFAULT_IMAGE_NO%>">
											<input type="radio" name="isDefault" value="<%=MortuaryConfig.IMAGE_EXIST_YES +"#"+ index%>" tabindex="1">
										</logic:equal>
										</div>
									</td>
									<td  width="5%" class="tdfont"></td>
								</tr>
							</logic:iterate>
						</table>		
					</his:ContentTag>
				</logic:present>
			<his:SubTitleTag name="Upload Image">
			</his:SubTitleTag>	
				<his:ContentTag>
					 <!-- <table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
						<td class="tdfonthead" width="20%" nowrap="nowrap">
								<b>Image Title</b> 
						</td>
						<td class="tdfont" width="20%" nowrap="nowrap">
							<div align="left">	           
							<html:text name="mortuaryImageUploadFB" property="imageTitle" maxlength="50" size="50" tabindex="1" value="" onkeypress="return validateAlphaOnly(this,event)" />
							</div>
					  	</td>
					  </tr>
					  </table>
					  -->
					  <table>
					  <tr>
		  			<td class="tdfonthead" width="10%" nowrap="nowrap">
					<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	
					<b>
					Upload Image</b>
					</font>
					</div>
					</td>
	  			<td class="tdfont" width="10%" nowrap="nowrap">
	  			<div align="left">
				<img class="button" src='<his:path src="/hisglobal/images/plus.png"/>' style="cursor: pointer;" onkeypress="if(event.keyCode==13)submitToUpload(event);" onclick="submitToUpload(event);" tabindex="1"/>
				</div>
				</td>	
	  		</tr>
 			</table>
			</his:ContentTag>
		
		
			<logic:present name="<%=MortuaryConfig.PATIENT_IMAGE_MAP%>">
			<his:ContentTag>
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="image"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead">
									<div align="center">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b><bean:message key="header"/></b>	
										</font>
									</div>
								</td>
								<td width="20%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b><bean:message key="remarks"/></b>	
										</font>
									</div>
								</td>
								<td width="5%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b><bean:message key="default"/></b>	
										</font>
									</div>
								</td>
								<td width="5%" class="tdfonthead"></td>
					</tr>
					<bean:define id="allImageMap" name="<%=MortuaryConfig.PATIENT_IMAGE_MAP%>" type="java.util.LinkedHashMap"></bean:define>
					<logic:iterate id="imageMap" name="allImageMap" indexId="index" type="java.util.Map.Entry">
					<bean:define id="imageKey" name="imageMap" property="key"></bean:define>	
					<tr>
						<td  width="50%" style="height: 20px" class="tdfont">
							<%String key=Config.IMAGE_BYTE_ARRAY_KEY; %>
							<%String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX;%>
								<%String imagepath="/image/showImage?"+imageIndex+"="+imageKey+"&"+key+"="+MortuaryConfig.DECEASED_IMAGE_UPLOADED_IN_SESSION;%>
							<div align="center">
								<img src='<his:path src="<%=imagepath%>"/>' height="50%" width="50%" />
							</div>	
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
							<html:text name="mortuaryImageUploadFB" property="imageHeader" maxlength="30" size="25" value="" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1"/>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
							<html:textarea name="mortuaryImageUploadFB" property="uploadRemarks" cols="20"  value="" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" tabindex="1"/>
							</div>
						</td>
						<td width="5%" class="tdfont">
							<div align="center">
							<%String maxSlNo=((MortuaryImageUploadFB)pageContext.findAttribute("mortuaryImageUploadFB")).getMaxSerialNo(); %>
							<input type="radio" name="isDefault" value="<%=MortuaryConfig.IMAGE_EXIST_NO +"#"+ index%>" tabindex="1">
							</div>
					  	</td>
						<td width="5%" class="tdfont">
							<div align="center">
							<img class="button" src='<his:path src="/hisglobal/images/Minus.png"/>' style="cursor: pointer;" onkeypress="if(event.keyCode==13) removeImage('<%=imageKey %>');" onclick="removeImage('<%=imageKey %>');" tabindex="1"/>
							</div>
					  	</td>
					  </tr>
					</logic:iterate> 
				  </table>
			</his:ContentTag>
			</logic:present>
		</his:statusTransactionInProcess>	
		
		<html:hidden name="mortuaryImageUploadFB" property="hmode" />
		<html:hidden name="mortuaryImageUploadFB" property="patCrNo"/>
		<html:hidden name="mortuaryImageUploadFB" property="deceasedNo"/>
		<html:hidden name="mortuaryImageUploadFB" property="slNo"/>
		<html:hidden name="mortuaryImageUploadFB" property="maxSerialNo"/>
		<html:hidden name="mortuaryImageUploadFB" property="noOfImages"/>
		<html:hidden name="mortuaryImageUploadFB" property="removeImageIndex"/>
		
	
	<his:ButtonToolBarTag>
	
		<%-- <his:statusList>
		 --%><%--<logic:notEqual value="0" name="mortuaryImageUploadFB" property="noOfImages">--%>
		<%-- <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick ="return validateSave('SAVEPATIMAGE')" onkeypress="if(event.keyCode==13) return validateSave('SAVEPATIMAGE')">
		 --%><%--</logic:notEqual>--%>
		<%-- </his:statusList> --%>
		<logic:notEmpty  name="<%= MortuaryConfig.ARR_DECEASED_EXISTING_IMAGE_VO  %>">
<!--		<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>'  style="cursor:pointer" tabindex="1" onclick ="return  modifyImage('MODIFYPATIMAGE')" onkeypress="if(event.keyCode==13)return modifyImage('MODIFYPATIMAGE')">-->
		<img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>'  style="cursor:pointer" tabindex="1" onclick ="return  deleteImage('DELETEPATIMAGE')" onkeypress="if(event.keyCode==13)return deleteImage('DELETEPATIMAGE')">
			</logic:notEmpty>
		<logic:notEqual name="mortuaryImageUploadFB" property="hmode" value="NEW">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick ="return validateSave('SAVEPATIMAGE')" onkeypress="if(event.keyCode==13) return validateSave('SAVEPATIMAGE')">
	
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitPage('NEW')" onkeypress="if(event.keyCode==13)submitPage('NEW')">
		</logic:notEqual>
		<his:statusNew>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
		</his:statusNew>
	<%-- 	<his:statusList>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitPage('NEW')" onkeypress="if(event.keyCode==13)submitPage('NEW')">
		</his:statusList>
	 --%>	
	</his:ButtonToolBarTag>
	</html:form>
	
	<his:status/>
</his:TransactionContainer>
</body>
<%}catch(Exception e){e.printStackTrace();} %>
</html>