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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/js/imageEditorFunctions.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/utility/dynamicdesk/js/deskNew.js" />
<his:javascript src="/opd/js/opd_desk_new.js"/>
<his:javascript src="/registration/js/registration.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/jquery/jquery.js"></script>
<his:javascript src="/hisglobal/js/jquery/jquery-1.10.2.js" />
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/jquery/jquery-migrate.js"></script>
<script type="text/javascript">


function goGetEditor()
{
	//alert(document.forms[0].action);
	if(document.getElementsByName('imageCode')[0].value=="-1")
	{
		alert("Please Select Image");
		document.getElementsByName('imageCode')[0].focus();
		return false;
	}
	return true;
}

function submitFormOnValidate1(flag,mode)
{

 	if(flag)
	{
 		//alert(mode);
 		var ic=document.getElementsByName('imageCode')[0].value;
  	  		var crNo=document.getElementsByName('patCrNo')[0].value;
  	  		var imageFileName=document.getElementsByName('title')[0].value;
  	  		var remark=document.getElementsByName('remark')[0].value;

  		if(mode=="SAVEIMAGE"){
  	  		
  	  		//var id=document.getElementsByName('imageCode')[0];
  	  		//var imageName=id.options[id.selectedIndex].text;
  	  		//alert(imageName);
  			document.forms[0].action="/HISClinical/opd/opdImageExamTab.cnt?hmode="+mode+"&title="+imageFileName+"&patCrNo="+crNo+"&remark="+remark;
  			document.forms[0].submit();

 	
  		}
 		else if(mode=="GETEDITOR"){
 			document.forms[0].action="/HISClinical/opd/opdImageExamTab.cnt?hmode="+mode+"&title="+imageFileName+"&patCrNo="+crNo;
 			document.forms[0].submit();
 	 		}
	
	else{

		return false;
	}
  		
}
}

function isOnLoad(e)
{
	//alert("1");
	//alert("Mukund isOnLoad()\n"+document.getElementsByName('hmode')[0].value);
	e.keyCode=13;
	if(document.getElementsByName('hmode')[0].value=="GETEDITOR" || document.getElementsByName('hmode')[0].value=="GETOLDEDITOR")
	{
		//alert("2");
		//var url="/HISClinical/opd/imageEditor.cnt";
		/*   //var url="/AHIMS/opd/imageEditorNew.cnt";
		openPopup(createFHashAjaxQuery(url),e,500,800);//,'yes'); */
		//window.open("/AHIMS/opd/transaction/miniPaint-master/index.jsp");
			$('#imgCdFrIndxId').val(document.getElementsByName('imageCode')[0].value);
			$('#patCrNoForImgUploadId').val(document.getElementsByName('patCrNo')[0].value);
			$('#patEpisodeCodeForImgUploadId').val(document.getElementsByName('episodeCode')[0].value);
			$('#imgSrc').val(document.getElementsByName('imageOutFileName')[0].value);
			
			//document.getElementsByName("imgCdFrIndx")[0].value= document.getElementsByName('imageCode')[0].value;
		(document.getElementsByName("imgCdFrIndx")[0].value);
		//alert("from opdimageExamTAB; "+document.getElementsByName('imageCode')[0].value);
		   var url="/HISClinical/opd/transaction/miniPaint-master/index.jsp";
			 //openPopup(url,e,7000,10000);
			//window.open(url, "newWin", "width="+screen.availWidth+",height="+screen.availHeight)
			window.open(url,"width="+screen.availWidth+",height="+screen.availHeight)
			CallFromImageEditor("SAVED");
	}
}

function submitPage21(){
	//alert(mode); 	
	window.close();
	//document.forms[0].submit();
}


function CallFromImageEditor(status)
{
	if(document.getElementsByName('hmode')[0].value=="GETEDITOR")
	{
		//alert("welcome to image editor");
		if(status=="SAVED")
		{
			document.getElementById('saveButton1').style.display="inherit";
			document.getElementById('noSaveButton').style.display="none";
			document.getElementById("statusStr").innerHTML="Image Editing is done ... Please Save the Image ... ";
		}
		else if(status=="CANCELED")
		{
			document.getElementById('saveButton1').style.display="none";
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

function getImageView(event,sno)
{
	//openDependentPopup(path,event,500,800,'yes');
   // alert("Vasu GetIamgeView()");
   
   // $('#imgCdFrIndxId').val(document.getElementsByName('imageCode')[0].value);
	var path = "/HISClinical/opd/opdImageExamTab.cnt?hmode=VIEWIMAGELOG&selectedSNo="+sno; 
	//alert(path);
	openDependentPopup(path,event,500,800,'yes');
}

function clearForm()
{
	document.getElementsByName('imageCode')[0].value="-1";
	document.getElementsByName('remark')[0].value="";
	}


	
function oldImageEdit(sno)
{
	document.getElementsByName("selectedSNo")[0].value=sno;
   	submitFormOnValidate(true,'GETOLDEDITOR');
}


</script>

	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
<body onload="isOnLoad(event)" >
<form id="formImage" action="/opdImageExamTab" method="post">

	<his:TitleTag key="opdImageExam">
	</his:TitleTag>

	
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
									<%-- <a style="cursor:pointer" onclick="getUploadedeDoc(event,'<%=DocumentUploadVO[i].getDocumentCode()%>','<%=DocumentUploadVO[i].getFileType() %>')"><b><%=DocumentUploadVO[i].getDocumentTitle()!=null?DocumentUploadVO[i].getDocumentTitle():DocumentUploadVO[i].getFileType()%></b></a>
				 --%>
										<a style="cursor:pointer" onclick="getImageView(event,'<%=i%>')" >
											<bean:write name="lst" property="imageName"/>
											
				      					</a>
									</font>
								</div>
							</td>
							<td width="48%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="lst" property="remarks"/>
									</font>
								</div>
							</td>
							<!-- commented by VASU on 31-AUG-2017  -->
							
							<!-- <td width="8%" class="tdfont">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<a style="cursor:pointer" onclick=oldImageEdit('<bean:write name="lst" property="imageFileName"/>') >
				      						<b>EDIT</b>
				      					</a>
			      					</font>
			      				</div>
							</td> -->
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
								<html:select name="OpdImageExamTabFB" property="imageCode" tabindex="1" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_IMAGES_OF_DEPTUNIT%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_IMAGES_OF_DEPTUNIT%>" property="imageCode" labelProperty="imageName" />
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
									<jsp:useBean id="opdImageExamFB" class="opd.transaction.controller.fb.OpdImageExamTabFB" scope="session">
									<jsp:getProperty name="opdImageExamFB" property="imageOutFileName" />
									</jsp:useBean>
					</tr>
				</table>
			</his:ContentTag>
		</logic:equal>
	</his:statusTransactionInProcess>

	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
			<logic:equal name="OpdImageExamTabFB" property="hmode" value="GETEDITOR">
				<div id="saveButton1" style="display: none;">
					<img  class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1"  onclick ="submitFormOnValidate1(validatefinalSave(),'SAVEIMAGE');"  >
					<!-- <img  class="button " src="/../HIS/hisglobal/images/buttons/btn-sv.png"  style="cursor:pointer" id="saveId" tabindex="1" > -->
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')"  >
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="clearForm();"  >
				</div>
				<div id="noSaveButton" style="display: inherit;">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitFormOnValidate1('1','NEW')"  >
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="clearForm();" >
				</div>
			</logic:equal>
			<logic:notEqual name="OpdImageExamTabFB" property="hmode" value="GETEDITOR">
				<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>'  style="cursor:pointer"  tabindex="1" onclick ="submitFormOnValidate1(goGetEditor(),'GETEDITOR');" onkeypress="if(event.keyCode==13) submitFormOnValidate(goGetEditor(),'GETEDITOR');" >
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')"  >
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="clearForm();" >
			</logic:notEqual>
		</his:statusTransactionInProcess>
		
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');" >
		</his:statusUnsuccessfull>
		
	</his:ButtonToolBarTag>
	</form>
</body>		

<html:hidden name="OpdImageExamTabFB" property="hmode"/>
<html:hidden name="OpdImageExamTabFB" property="userSeatId"/>
<html:hidden name="OpdImageExamTabFB" property="departmentUnitCode"/>
<html:hidden name="OpdImageExamTabFB" property="patCrNo"/>
<html:hidden name="OpdImageExamTabFB" property="episodeCode"/>
<html:hidden name="OpdImageExamTabFB" property="episodeVisitNo"/>
<html:hidden name="OpdImageExamTabFB" property="admissionNo"/>
<html:hidden name="OpdImageExamTabFB" property="remark"/>

<html:hidden name="OpdImageExamTabFB" property="patSerialNo"/>
<html:hidden name="OpdImageExamTabFB" property="title"/>
<html:hidden name="OpdImageExamTabFB" property="imageFileName"/>
<html:hidden name="OpdImageExamTabFB" property="imageOutFileName"/>
<html:hidden name="OpdImageExamTabFB" property="colorDesc"/>
<html:hidden name="OpdImageExamTabFB" property="dirOutPath"/>
<html:hidden name="OpdImageExamTabFB" property="selectedSNo"/>
<html:hidden name="OpdImageExamTabFB" property="editingStatus"/>
<input type="hidden" name="imgCdFrIndx" id="imgCdFrIndxId"/>
<input type="hidden" name="patCrNoForImgUpload" id="patCrNoForImgUploadId">
<input type="hidden" name="patEpisodeCodeForImgUpload" id="patEpisodeCodeForImgUploadId">
<input type="hidden" name="imgSrc" id="imgSrc">

