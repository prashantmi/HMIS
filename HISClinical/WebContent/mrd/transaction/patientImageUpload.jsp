<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:css src="/hisglobal/css/layout.css"/>
<%-- 
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function submitToUpload(event){
	//console.log("Button clicked")
	document.getElementsByName("hmode")[0].value="UPLOADIMAGE"
	var url='<his:path src='/registration/uploadFile.cnt'/>'
	openPopup(url,event, 200, 300)
	
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
	//alert("Saving image")
	var valid=false
	var isDefault=document.getElementsByName("isDefault")
	//alert(isDefault);
	document.getElementsByName("isDefault")[0].value = isDefault;
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
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
	
	if(!valid){
		alert("Please select one Image as Default");
		return false;
	}

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
		var isDefault=document.getElementsByName("isDefault")[0].value;
		//alert(isDefault);
		document.getElementsByName("isDefault")[0].value = isDefault;
		document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
	}
}

 function openPopup(url,eventObj)
{
	//alert("url: "+url);
if(eventObj.type=="click" || eventObj.keyCode==13)
 {

//error in createFHashAjaxQuery method
//	child = window.open(createFHashAjaxQuery(url),'popupWindow','status=yes,scrollbars=yes,height=200,width=350,left=10,top=10');  
  	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=200,width=350,left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 

if(!child.opener)
   child.opener = self;
 }
} 

</script>

<body>
<his:TransactionContainer>
	<html:form action="/patientImageUpload">
			<his:TitleTag name="Patient Image Upload">
			</his:TitleTag>
		 <his:InputCrNoTag name="patImageUploadFB"></his:InputCrNoTag> 
		<his:statusList>
			
				<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
				<logic:present name="<%= MrdConfig.PATIENT_IMAGE_DTL_VO_LIST %>">
					<his:SubTitleTag name="Previously Uploaded Images">
					</his:SubTitleTag>
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="10%" class="tdfonthead">
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
								<td width="25%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="uploadedDate"/>
											</b>	
										</font>
									</div>
								</td>
								<td width="40%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												<bean:message key="default"/>
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate name="<%=MrdConfig.PATIENT_IMAGE_DTL_VO_LIST %>" id="patImageVO" type="hisglobal.vo.PatientImageDtlVO" indexId="index">
								<tr>
									<td width="10%" class="tdfont">
										<div align="center">
											<html:checkbox name="patImageUploadFB" property="chk" value='<%=patImageVO.getPatCrNo()+"#"+patImageVO.getSerialNo()+"#"+patImageVO.getIsImageDefault()%>' ></html:checkbox>
										</div>
									</td>
									<!-- Nilesh Gupta  -->
									<td  width="25%" style="height: 20px" class="tdfont">
										<%-- <div align="center">
											<%String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX; %>
											<%String imagepath="/image/showImage?"+ imageIndex+"="+index; %>
											<% System.out.println("Imagepath: "+imagepath); %>
											<img src='<his:path src="<%=imagepath%>"/>' height="50%" width="50%" title="Click To Enlarge" alt="No image Loaded" onkeypress="if(event.keyCode==13) 
                    						openPopup('<his:path src="/./registration/controller/action/enlargedImage.cnt"/>',event)"  style="cursor: pointer"
											onclick="openPopup('<his:path src="/./registration/controller/action/enlargedImage.cnt"/>',event)" />										
										</div> --%>
										<div align="center">
											<%String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX; %>
											<%String imagepath="/image/showImage?"+ imageIndex+"="+index; %>
											<% System.out.println("Imagepath: "+imagepath); %>
											<img src='<his:path src="<%=imagepath%>"/>' height="50%" width="50%" class="patTileImgEnlarge" title="Patient Image" alt="No image Loaded" style="cursor: pointer" />
										
										</div>
									</td>
									<td  width="25%" class="tdfont">
										<div align="center">
											<bean:write name="patImageVO" property="entryDate"/>
										</div>
									</td>
									<td  width="40%" class="tdfont">
										<div align="center">
										 	 
										<logic:equal name="patImageVO" property="isImageDefault" value="1">
										<input type="radio" name="isDefault" checked="checked"   value="<%=index%>">
										</logic:equal>
										
										<logic:equal name="patImageVO" property="isImageDefault" value="0">
											<input type="radio" name="isDefault"  value="<%=index%>">
										</logic:equal>
										
										</div>
									</td>
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
							<html:text name="patImageUploadFB" property="imageTitle" maxlength="50" size="50" tabindex="1" value="" onkeypress="return validateAlphaOnly(this,event)" />
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
				<img class="button" src='<his:path src="/hisglobal/images/plus.png"/>' style="cursor: pointer;" onkeypress="if(event.keyCode==13)submitToUpload(event);" onclick="submitToUpload(event);"/>
				</div>
				</td>	
	  		</tr>
 			</table>
			</his:ContentTag>
		</his:statusList>	
		<his:statusTransactionInProcess>
			<his:ContentTag>
				<table width="100%" cellspacing="1" cellpadding="0">
					<bean:define id="allImageMap" name="<%=MrdConfig.PATIENT_IMAGE_MAP%>" type="java.util.LinkedHashMap"></bean:define>
					<logic:iterate id="imageMap" name="allImageMap" indexId="index" type="java.util.Map.Entry">
					<bean:define id="imageKey" name="imageMap" property="key"></bean:define>	
					<tr>
						<td  width="50%" style="height: 20px" class="tdfont">
							<%String key=Config.IMAGE_BYTE_ARRAY_KEY; %>
							<%String imageIndex=Config.REQ_PARAMETER_IMAGE_INDEX;%>
								<%String imagepath="/image/showImage?"+imageIndex+"="+imageKey+"&"+key+"="+MrdConfig.PATIENT_IMAGE_UPLOADED_IN_SESSION;%>
							<div align="center">
								<img src='<his:path src="<%=imagepath%>"/>' height="50%" width="50%" />
							</div>	
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
							<input type="radio" name="isDefault" value="<%=imageIndex%>" checked="checked">
							</div>
					  	</td>
						<td width="40%" class="tdfont">
							<img class="button" src='<his:path src="/hisglobal/images/minus.gif"/>' style="cursor: pointer;" onkeypress="if(event.keyCode==13)removeImage('<%=imageKey %>');" onclick="removeImage('<%=imageKey %>');"/>
					  	</td>
					  </tr>
					</logic:iterate> 
				  </table>
			</his:ContentTag>
		</his:statusTransactionInProcess>	
		
		<html:hidden name="patImageUploadFB" property="hmode" />
		<html:hidden name="patImageUploadFB" property="patCrNo"/>
		<html:hidden name="patImageUploadFB" property="serialNo"/>
		<html:hidden name="patImageUploadFB" property="maxSerialNo"/>
		<html:hidden name="patImageUploadFB" property="noOfImages"/>
		<html:hidden name="patImageUploadFB" property="removeImageIndex"/>
		<html:hidden name="patImageUploadFB" property="isDefault"/>
	
	<his:ButtonToolBarTag>
		<logic:notEqual value="0" name="patImageUploadFB" property="noOfImages">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick ="return validateSave('SAVEPATIMAGE')" onkeypress="if(event.keyCode==13) return validateSave('SAVEPATIMAGE')">
		</logic:notEqual>
		<logic:notEqual name="patImageUploadFB" property="hmode" value="NEW">
		<logic:notEmpty  name="<%= MrdConfig.PATIENT_IMAGE_DTL_VO_LIST %>">
		  <img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>'  style="cursor:pointer" tabindex="1" onclick ="return  modifyImage('MODIFYPATIMAGE')" onkeypress="if(event.keyCode==13)return modifyImage('MODIFYPATIMAGE')"> 
	 	<img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>'  style="cursor:pointer" tabindex="1" onclick ="return  deleteImage('DELETEPATIMAGE')" onkeypress="if(event.keyCode==13)return deleteImage('DELETEPATIMAGE')">
		</logic:notEmpty>
		</logic:notEqual>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
	</his:ButtonToolBarTag>
	</html:form>
	
	<his:status/>
</his:TransactionContainer>
</body>

</html>