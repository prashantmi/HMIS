<!-- 
/**
 * @author CDAC
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.*"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>

<his:javascript src="/opd/js/imageEditorFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function goGetEditor()
{
	if(document.getElementsByName('imageCode')[0].value=="-1")
	{
		alert("Please Select Image");
		document.getElementsByName('imageCode')[0].focus();
		return false;
	}
	return true;
}

function isOnLoad(e)
{
	e.keyCode=13;
	if(document.getElementsByName('hmode')[0].value=="GETEDITOR" || document.getElementsByName('hmode')[0].value=="GETOLDEDITOR")
	{
		var url="/HISClinical/opd/imageEditor.cnt";
		openDependentPopup(createFHashAjaxQuery(url),e,500,800,'yes');
	}
}

function CallFromImageEditor(status)
{
	if(document.getElementsByName('hmode')[0].value=="GETEDITOR")
	{
		if(status=="SAVED")
		{
			document.getElementById('saveButton').style.display="inherit";
			document.getElementById('noSaveButton').style.display="none";
			document.getElementById("statusStr").innerHTML="Image Editing is done ... Please Save the Image ... ";
		}
		else if(status=="CANCELED")
		{
			document.getElementById('saveButton').style.display="none";
			document.getElementById('noSaveButton').style.display="inherit";
			document.getElementsByName("remark")[0].value="";
			document.getElementsByName("remark")[0].disabled=true;
			document.getElementById("statusStr").innerHTML="Image Editing is Canceled ... ";
		}
	}
	//----
	if(document.getElementsByName('hmode')[0].value=="GETOLDEDITOR")
	{
		if(status=="SAVED")
		{
			submitFormOnValidate(true,'MODIFYSAVE');
		}
		else if(status=="CANCELED")
		{
		}
	}
}

function validatefinalSave()
{
	/*if(document.getElementsByName('remark')[0].value=="")
	{
		alert("Please Enter the Remark ...");
		document.getElementsByName('remark')[0].focus();
		return false;
	}*/
	return true;
}

function getImageView(event,path,sno)
{
	//openDependentPopup(path,event,500,800,'yes');

	var path = "/HISClinical/opd/opdImageExamTab.cnt?hmode=VIEWIMAGELOG&selectedSNo="+sno; 
	openDependentPopup(path,event,500,800,'yes');
}

function oldImageEdit(sno)
{
	document.getElementsByName("selectedSNo")[0].value=sno;
   	submitFormOnValidate(true,'GETOLDEDITOR');
}

</script>

<body onload="isOnLoad(event)" >

<his:TransactionContainer>

	<his:TitleTag key="opdImageExam">
	</his:TitleTag>

	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
	<his:statusTransactionInProcess>
		<logic:notEmpty name="<%=OpdConfig.OPD_ESSENTIAL_OLD_ADDED_IMAGES_OF_DEPTUNIT%>" >
			<his:SubTitleTag key="examimagedtl"> 
			</his:SubTitleTag>
			
			<his:ContentTag>
				<table width="100%" cellspacing="1" cellpadding="0" >
					<tr>
						<td width="5%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="SNo" /> </b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="entryDate" /></b>
								</font>
							</div>
						</td>
						<td width="32%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="image" /> <bean:message key="name" /></b>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="remarks" /></b>
								</font>
							</div>
						</td>
						<td width="8%"  class="tdfonthead"></td>
					</tr>
					
					<logic:iterate name="<%=OpdConfig.OPD_ESSENTIAL_OLD_ADDED_IMAGES_OF_DEPTUNIT%>"  id="lst" type="hisglobal.vo.OpdPatientImageDtlVO" indexId="i">
						<%
							String path = ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" 
								+ ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_WINDOWS + "&" 
								+ ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_LINUX + "&" 
								+ ServletsUtilityConfig.FILE_NAME + "=" + lst.getImageFileName(); 
						%>
						<tr>
							<td width="5%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b><%=(i.intValue()+1)%>.</b>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="lst" property="entryDate"/>
									</font>
								</div>
							</td>
							<td width="32%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<a style="cursor:pointer" onclick="getImageView(event,'<%=path%>','<%=i%>')" >
											<bean:write name="lst" property="imageName"/>
				      					</a>
									</font>
								</div>
							</td>
							<td width="40%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="lst" property="remarks"/>
									</font>
								</div>
							</td>
							<td width="8%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<a style="cursor:pointer" onclick=oldImageEdit('<bean:write name="lst" property="imageFileName"/>') >
				      						<b>EDIT</b>
				      					</a>
			      					</font>
			      				</div>
							</td>
						</tr>
					</logic:iterate>
				</table>
			</his:ContentTag>
		</logic:notEmpty>
	
		<logic:notEqual name="OpdImageExamTabFB" property="hmode" value="GETEDITOR">
			<logic:notEmpty name="<%=OpdConfig.OPD_ESSENTIAL_IMAGES_OF_DEPTUNIT%>" >
				<his:SubTitleTag key="newImgExam">
				</his:SubTitleTag>
				
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0" >
						<tr>
							<td width="50%"  class="tdfonthead"></td>
							<td width="50%"  class="tdfont"></td>
						</tr>
						<tr>
							<td width="50%"  class="tdfonthead">
								<font color="#FF0000">*</font> <bean:message key="sel"/> <bean:message key="image"/>&nbsp;&nbsp;
							</td>
							<td width="50%"  class="tdfont">
								<html:select name="OpdImageExamTabFB" property="imageCode" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_IMAGES_OF_DEPTUNIT%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_IMAGES_OF_DEPTUNIT%>" property="imageCode" labelProperty="imageName"/>
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="50%"  class="tdfonthead"></td>
							<td width="50%"  class="tdfont"></td>
						</tr>
					</table>
				</his:ContentTag>
			</logic:notEmpty>
		</logic:notEqual>
	
		<logic:equal name="OpdImageExamTabFB" property="hmode" value="GETEDITOR">	
			<his:ContentTag>
				<table width="100%" cellspacing="1" cellpadding="0" >
					<tr>
						<td width="50%"  class="tdfonthead"></td>
						<td width="50%"  class="tdfont"></td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead">
							<bean:message key="image"/>&nbsp;&nbsp;
						</td>
						<td width="50%"  class="tdfont">
							<html:hidden name="OpdImageExamTabFB" property="imageCode"/>
							&nbsp;<b><bean:write name="OpdImageExamTabFB" property="title"/> </b>
						</td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead"></td>
						<td width="50%"  class="tdfont"></td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead">
							<bean:message key="remarks"/>&nbsp;&nbsp;
						</td>
						<td width="50%"  class="tdfont">
							<html:textarea name="OpdImageExamTabFB" property="remark" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event))" ></html:textarea>
						</td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead"></td>
						<td width="50%"  class="tdfont"></td>
					</tr>
					<tr>
						<td width="100%" colspan="2" class="tdfont">
							<font color="#0000FF">
								<b>
									<div id="statusStr">
										<bean:write name="OpdImageExamTabFB" property="editingStatus" />
									</div>
								</b>
							</font>
						</td>
					</tr>
				</table>
			</his:ContentTag>
		</logic:equal>
	</his:statusTransactionInProcess>

	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
			<logic:equal name="OpdImageExamTabFB" property="hmode" value="GETEDITOR">
				<div id="saveButton" style="display: none;">
					<img  class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1"  onclick ="submitFormOnValidate(validatefinalSave(),'SAVE');" onkeypress="if(event.keyCode==13) submitFormOnValidate(validatefinalSave(),'SAVE');" >
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW');" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW');" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
				</div>
				<div id="noSaveButton" style="display: inherit;">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW');" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW');" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
				</div>
			</logic:equal>
			<logic:notEqual name="OpdImageExamTabFB" property="hmode" value="GETEDITOR">
				<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>'  style="cursor:pointer"  tabindex="1" onclick ="submitFormOnValidate(goGetEditor(),'GETEDITOR');" onkeypress="if(event.keyCode==13) submitFormOnValidate(goGetEditor(),'GETEDITOR');" >
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW');" onkeypress="if(event.keyCode==13) submitForm('NEW');" >
			</logic:notEqual>
		</his:statusTransactionInProcess>
		
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
		</his:statusUnsuccessfull>
		
	</his:ButtonToolBarTag>
</his:TransactionContainer>
</body>		

<html:hidden name="OpdImageExamTabFB" property="hmode"/>
<html:hidden name="OpdImageExamTabFB" property="userSeatId"/>
<html:hidden name="OpdImageExamTabFB" property="departmentUnitCode"/>
<html:hidden name="OpdImageExamTabFB" property="patCrNo"/>
<html:hidden name="OpdImageExamTabFB" property="episodeCode"/>
<html:hidden name="OpdImageExamTabFB" property="episodeVisitNo"/>
<html:hidden name="OpdImageExamTabFB" property="admissionNo"/>

<html:hidden name="OpdImageExamTabFB" property="patSerialNo"/>
<html:hidden name="OpdImageExamTabFB" property="title"/>
<html:hidden name="OpdImageExamTabFB" property="imageFileName"/>
<html:hidden name="OpdImageExamTabFB" property="imageOutFileName"/>
<html:hidden name="OpdImageExamTabFB" property="colorDesc"/>
<html:hidden name="OpdImageExamTabFB" property="dirOutPath"/>
<html:hidden name="OpdImageExamTabFB" property="selectedSNo"/>
<html:hidden name="OpdImageExamTabFB" property="editingStatus"/>