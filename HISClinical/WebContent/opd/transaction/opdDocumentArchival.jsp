<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.*"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="opd.transaction.controller.fb.OpdDocumentArchivalFB"%>
<%@page import="hisglobal.vo.DocumentUploadDtlVO"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.RegistrationConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js" />

<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/jquery.simplemodal.js" />

<his:javascript src="/opd/opdJs/opd.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function callThisOnload()
{
//alert("onl;oad");
	
	var selectedRevoke=document.getElementsByName("selectedRevoke")[0].value;
	// alert("selectedRevoke "+selectedRevoke  );
	var selectedRevokeArray=selectedRevoke.split('^');
	for(i=0;i<selectedRevokeArray.length;i++)
	{
	//alert("index " + )
		document.getElementsByName("revoke")[parseInt(selectedRevokeArray[i])].checked=true;
		document.getElementsByName("removeReason")[parseInt(selectedRevokeArray[i])].disabled=false;
	}
}

function enableReason(obj)
{
	index=parseInt(obj.value);
	if(obj.checked)
	{
		document.getElementsByName("removeReason")[index].disabled=false;
	}
	else
	{
		document.getElementsByName("removeReason")[index].disabled=true;
	}
}

function deleteRow(key)
{	
	//	alert("key==== "+key);
	document.getElementsByName("serialNo")[0].value=key;
	//	alert("uploadedFileName===  "+document.getElementsByName("uploadedFileName")[0].value);
	submitForm21("REMOVE");
}
function openPopFileUpload(event)
{
	//alert("1");
	//openPopup('<his:path src='registration/uploadFile.cnt'/>',event,300,600);
	var crNo = document.getElementsByName("patCrNo")[0].value;
	//alert(crNo);
	var url = "/HISClinical/hisglobal/filetransfer/UploadFile.action?strCRNoHospCode="+crNo;
	//alert(url);
	openPopup(url,event,300,600); 
}

function openPopFileUploadAudioVedio(event)
{
	
	openPopup('<his:path src='registration/uploadFile.cnt?hmode=AUDIOVEDIO'/>',event,300,600);
}

function getUploadedeFile(event,path,fileType)
{
var fileTypeAudioVedio=<%=RegistrationConfig.DOCUMENT_TYPE_AUDIO_VIDEO%>;
	var fileTypeDocumentFile=<%=RegistrationConfig.DOCUMENT_TYPE_DOC%>;
	var fileTypeMLCFile=<%=RegistrationConfig.DOCUMENT_TYPE_MLC%>;
	var fileTypePrescriptionNote=<%=RegistrationConfig.DOCUMENT_TYPE_PRESCRIPTION_NOTE%>;
	//alert("file "+fileType)
	//alert("path "+path);
// window.open(path,'popupWindow','status=yes,scrollbars=yes,300,600,left=10,top=10,dependent=yes,resizable=yes');
	if(fileType==fileTypeDocumentFile)
		{
			openDependentPopup(path,event,300,600,'yes');	
		}
		else
		{
			if(fileType==fileTypeAudioVedio)
			{
			path="<%=OpdConfig.AUDIO_VIDEO_URL%>"+"?hmode=PLAY&fileType="+path;
			path="/"+path;
			//alert("path "+path);
			openDependentPopup(createFHashAjaxQuery(path),event,300,600,'yes');
			}
		}
 

}

function getUploadedeDoc(event,docId,docType)
{
	//alert("Hello OPEN Doc")
	//openDependentPopup(path,event,300,600,'yes');	
	var left=(screen.width-600)/2;
	var top=(screen.height-400)/2;
	window.open(createFHashAjaxQuery("/HISClinical/opd/opdDocumentArchival.cnt?hmode=VIEWDOC&documentCode="+docId+"&documentDirectoryPath="+docType),"popup","height=500,width=600,left="+left+",top="+ top +",scrollbars=yes,location=no");
}

function submitToUpload(event)
{
	//alert("2");
	var flag=false;
	var fileTypeAudioVedio=<%=RegistrationConfig.DOCUMENT_TYPE_AUDIO_VIDEO%>;
	var fileTypeDocumentFile=<%=RegistrationConfig.DOCUMENT_TYPE_DOC%>;
	var fileTypeMLCFile=<%=RegistrationConfig.DOCUMENT_TYPE_MLC%>;
	var fileTypePrescriptionNote=<%=RegistrationConfig.DOCUMENT_TYPE_PRESCRIPTION_NOTE%>;
	var fileType=document.getElementsByName("fileType")[0].value;
	// alert("dsdsds")
	var prevDocLen=document.getElementsByName("prevAddDocTitle").length;
	var currentDocLen=document.getElementsByName("currentlyAddDocTitle").length;
	for(var i=0;i<prevDocLen;i++)
	{
		if(document.getElementsByName("documentTitle")[0].value==document.getElementsByName("prevAddDocTitle")[i].value)
		{
			alert("This Document Title Is Allready Added");
			document.getElementsByName("documentTitle")[0].focus();
			return false;
		}
	}
	
	for(var i=0;i<currentDocLen;i++)
	{
		if(document.getElementsByName("documentTitle")[0].value==document.getElementsByName("currentlyAddDocTitle")[i].value)
		{
			alert("This Document Title Is Allready Added");
			document.getElementsByName("documentTitle")[0].focus();
			return false;
		}
	}
	if(document.getElementsByName("fileType")[0].value==-1)
	{
		alert("Please Select Document Type");
	}
	else
	{
		if(document.getElementsByName("documentTitle")[0].value=="")
		{
			alert("Please Enter Docment Title");
		}
		else
		{
			flag=true;
		}
	}
	
	if(flag==true)
	{
		//if(fileType==fileTypeDocumentFile) -- Commented By Pragya to stop Audio Video Player
		//{	
		openPopFileUpload(event);
		//}
		//else
		//{
		//	if(fileType==fileTypeAudioVedio)
		//	openPopFileUploadAudioVedio(event);
		//}
	}
		
}

function submit4image(){
	//alert("dsdsdsdsdsdllllololo")
	elem = document.getElementsByName('hmode')[0];
	elem.value = "REFRESHFORIMAGE"; 
	document.forms[0].submit();
 } 
 
function validateIt(index)
{
  //alert(parseInt(index))
	var removeFlag=false;
	var addFlag=false;
	var removeReason=document.getElementsByName("revoke");
	if(removeReason.length>0)
	{
			for(i=0;i<removeReason.length;i++)
			{
				if(removeReason[i].checked)
				{
					if(document.getElementsByName("removeReason")[i].value=="")
					{
						alert("Please Enter Revoke Reason");
						document.getElementsByName("removeReason")[i].focus();
						return false;
					}
					else
					{
						removeFlag=true;
					}
					
					
				}
			}
		
			
			var ind=parseInt(index);
				if(ind>-1){
					addFlag=true;
				}
				
			if(removeFlag || addFlag)
			{
				return true;
			}
			else
			{
				document.getElementsByName("revoke")[0].focus();
				alert("Please Select Either Revoke Or Add Atleast One Document")
				
				return false;
			}
			
	}
	else
	{
		
			var ind=parseInt(index);
				if(ind>-1){
					return true;
				}
				else
				{
					document.getElementsByName("fileType")[0].focus();
					alert("Please Add Atleast One Document");
					return false;
				}
	}
	
		
			// return true;
	
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;	
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function setDocTitle()
{
	if(document.getElementsByName("fileType")[0].value == <%=RegistrationConfig.DOCUMENT_TYPE_DOC %>)
	{		
		document.getElementsByName("documentTitle")[0].value = "<%=RegistrationConfig.DOCUMENT_TYPE_DOC_LABEL %>" ;
	}
	if(document.getElementsByName("fileType")[0].value == <%=RegistrationConfig.DOCUMENT_TYPE_AUDIO_VIDEO %>)
	{		
		document.getElementsByName("documentTitle")[0].value = "<%=RegistrationConfig.DOCUMENT_TYPE_AUDIO_VIDEO_LABEL %>";
	}
	if(document.getElementsByName("fileType")[0].value == <%=RegistrationConfig.DOCUMENT_TYPE_MLC %>)
	{		
		document.getElementsByName("documentTitle")[0].value = "<%=RegistrationConfig.DOCUMENT_TYPE_MLC_LABEL %>";
	}
	if(document.getElementsByName("fileType")[0].value == <%=RegistrationConfig.DOCUMENT_TYPE_PRESCRIPTION_NOTE %>)
	{		
		document.getElementsByName("documentTitle")[0].value = "<%=RegistrationConfig.DOCUMENY_TYPE_PRESCRIPTION_NOTE_LABEL %>";
	}
}


</script>
<%int indexVal=-1; %>
<his:TitleTag>
		<his:name>
			<bean:message key="archivalOfDocument" />
		</his:name>
	</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
<his:statusTransactionInProcess>
 
  
 <% 					PaginationFB fbPage= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPage);
						fbPage.setCurrentPage(((OpdDocumentArchivalFB)request.getAttribute("OpdDocumentArchivalFB")).getCurrentPage());
						fbPage.setObjArrName(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
						fbPage.setAppendInTitle("Records");
						fbPage.setMaxRecords(5);
					%>
					
  	<logic:notEmpty name="<%=OpdConfig.DOCUMENT_DTL_VO_ARRAY%>" >
  	
  	<his:ContentTag> 
  	<his:PaginationTag name="fbPagination"></his:PaginationTag>	
 <table width="100%" cellspacing="1" cellpadding="0">
 
 	<tr>
 	<td width="5%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="revoke"/>
				</font>
				</div>
	  		</td>
 	<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="dtype"/>
				</font>
				</div>
	  		</td>
	  		
 		<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
					<bean:message key="documentTitle"/>
				</font>
				</div>
	  		</td>
 		
	  		<td width="25%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="uploadTime"/>
				</font>
				</div>
	  		</td>
	  		
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<font color="#FF0000">*</font> <bean:message key="rvrsn"/>
				</font>
				</div>
	  		</td>
	  	</tr>
			
			<%
						DocumentUploadDtlVO []DocumentUploadVO=(DocumentUploadDtlVO[])session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);;						
						int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
						int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
						int index=0;
						//int j=0;
						for(int i=startIndex;i<=endIndex;i++)
						{
							
						%>

  		
  			<tr>
  			<td width="5%"  class="tdfont">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<html:checkbox name="OpdDocumentArchivalFB" property="revoke" value="<%=String.valueOf(index++) %>" onclick="enableReason(this)" tabindex="1" ></html:checkbox>
				</font>
				</div>
	  		</td>
  			
  			<td width="20%"  class="tdfont">
					<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
					<%=DocumentUploadVO[i].getFileType() %>
				</b>
				</font>
				</div>
	  		</td>
  			<td width="20%"  class="tdfont">
					<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
<%-- //Commented By Akash Singh , dated on 01-10-2015 due to mongodb changes 
				<a style="cursor:pointer" onclick="getUploadedeFile(event,'<%=path%>',<%=RegistrationConfig.DOCUMENT_TYPE_DOC %>)"><b><bean:write name="docArchivalId" property="documentTitle"/></b></a> --%>
				<a style="cursor:pointer" onclick="getUploadedeDoc(event,'<%=DocumentUploadVO[i].getDocumentCode()%>','<%=DocumentUploadVO[i].getFileType() %>')"><b><%=DocumentUploadVO[i].getDocumentTitle()!=null?DocumentUploadVO[i].getDocumentTitle():DocumentUploadVO[i].getFileType()%></b></a>
				<html:hidden name="OpdDocumentArchivalFB"  property="prevAddDocTitle" value="<%=DocumentUploadVO[i].getDocumentTitle() %>"/>
				</font>
				</div>
	  		</td>
		 
		  	<td width="25%"  class="tdfont">
					<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<%=DocumentUploadVO[i].getEntryDate() %> </b>
				</font>
				</div>
	  		</td>
	  		
	  		<td width="20%"  class="tdfont">
					<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:define id="removeReasonId" name="OpdDocumentArchivalFB" property="removeReason"  type="java.lang.String[]" ></bean:define>
				
				 <input type="text"  name="removeReason"  tabindex="1" disabled=true />
				</font>
				</div>
	  		</td>
  			</tr>
  			
  			
  		<%} %>
  		
  			</table>
  		 </his:ContentTag>
  		 
  	</logic:notEmpty>
  	<his:SubTitleTag name="Upload Document">
  	</his:SubTitleTag>
  	
 	<his:ContentTag>
 		<table width="100%" cellspacing="1" cellpadding="0">
 			<tr >
 				<td width="20%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<font color="#FF0000">*</font>
				<bean:message key="type"/>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfont">
					<div align="left">	           
						<html:select name="OpdDocumentArchivalFB" property="fileType" tabindex="1" onchange="setDocTitle()" >
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=RegistrationConfig.DOCUMENT_TYPE_DOC%>"><%=RegistrationConfig.DOCUMENT_TYPE_DOC_LABEL%></html:option>
						<html:option value="<%=RegistrationConfig.DOCUMENT_TYPE_AUDIO_VIDEO%>"><%=RegistrationConfig.DOCUMENT_TYPE_AUDIO_VIDEO_LABEL%></html:option>
						<html:option value="<%=RegistrationConfig.DOCUMENT_TYPE_MLC%>"><%=RegistrationConfig.DOCUMENT_TYPE_MLC_LABEL%></html:option>
						<html:option value="<%=RegistrationConfig.DOCUMENT_TYPE_PRESCRIPTION_NOTE%>"><%=RegistrationConfig.DOCUMENY_TYPE_PRESCRIPTION_NOTE_LABEL%></html:option>

						</html:select>
				</div>
	  		</td>
	  		</tr>
	  		<tr>
	  		<td width="20%"  class="tdfonthead" nowrap="nowrap">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font></b>
				<bean:message key="documentTitle"/>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
				<div align="left">	           
					<html:text name="OpdDocumentArchivalFB" property="documentTitle" size="50" 
						maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
				</div>
	  		</td>
	  		</tr>
	  		</table>
	  		<table>
	  		<tr>
	  			<td width="10%"  class="tdfonthead" nowrap="nowrap" >
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="uploadFile"/>
				</font>
				</div>
				</td>
	  			
	  			
	  			<td width="10%"  class="tdfont" nowrap="nowrap" >
	  			<div align="left">
				<img class="button"  class="button" src="/HIS/hisglobal/images/plus.gif"  border="0" tabindex="1" style="cursor:pointer" onkeypress="if(event.keyCode==13)submitToUpload(event);" onClick="submitToUpload(event);" >
				</div>
			</td>	
	  		
	  		
	  		</tr>
 		</table>
 	</his:ContentTag>
 	<logic:notEmpty name="<%=OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP%>" >
 	<his:ContentTag>
 	<table width="100%" cellspacing="1" cellpadding="0">
 			<tr>
 			<td width="20%"  class="tdfonthead" >
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="type"/>
				</font>
				</div>
	  		</td>
	  		
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="documentTitle"/>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfonthead">
	  		</td>
	  		</tr>
	</table>
	<logic:iterate id="documentDeatail" indexId="index" name="<%=OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP%>" type="java.util.List">
	
 	<% indexVal=index.intValue(); %>
 	<table width="100%" cellspacing="1" cellpadding="0">
 		<tr>
 		<td width="20%"  class="tdfont" >
					<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				
				
				<%if( ((String)documentDeatail.get(2)).equals(RegistrationConfig.DOCUMENT_TYPE_DOC) ) {%>
					<%=RegistrationConfig.DOCUMENT_TYPE_DOC_LABEL %>
				<%} %>
					<%if( ((String)documentDeatail.get(2)).equals(RegistrationConfig.DOCUMENT_TYPE_AUDIO_VIDEO) ) {%>
					<%=RegistrationConfig.DOCUMENT_TYPE_AUDIO_VIDEO_LABEL %>
				<%} %>
				
				<%if( ((String)documentDeatail.get(2)).equals(RegistrationConfig.DOCUMENT_TYPE_MLC) ) {%>
					<%=RegistrationConfig.DOCUMENT_TYPE_MLC_LABEL %>
				<%} %>
				<%if( ((String)documentDeatail.get(2)).equals(RegistrationConfig.DOCUMENT_TYPE_PRESCRIPTION_NOTE) ) {%>
					<%=RegistrationConfig.DOCUMENY_TYPE_PRESCRIPTION_NOTE_LABEL %>
				<%} %>
				 </b>
				</font>
				</div>
			</td>
  			<td width="20%"  class="tdfont">
					<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<%=documentDeatail.get(0)%> 
				<html:hidden name="OpdDocumentArchivalFB" property="currentlyAddDocTitle" value="<%=(String)documentDeatail.get(0)%>"/>
				</b>
				</font>
				</div>
	  		</td>
	  		
	  		
			<td width="20%"  class="tdfont">
					<div align="center">	           
				<img class="button" border="0" src='<his:path src="/hisglobal/images/minus.gif"/>' tabindex="1" style="cursor:pointer" onkeypress="if(event.keyCode==13)deleteRow('<%=indexVal%>')" onClick="deleteRow('<%=indexVal%>')">
				</div>
			</td>
	  		</tr>
 	</table>
 	
 	</logic:iterate>


 	
 	</his:ContentTag>
 	</logic:notEmpty>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
		<%--   <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick =  "submitFormOnValidate(validateIt('<%=indexVal%>'),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt('<%=indexVal%>'),'SAVE');")> --%>
				  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick =  "submitFormOnValidate(validateIt('<%=indexVal%>'),'SAVEDOCUMENT');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt('<%=indexVal%>'),'SAVEDOCUMENT');")>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
</his:ButtonToolBarTag>

	<table>
	<tr>
		<td>
			<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b><bean:message key="note"/></b>
			</font>
		</td>
	</tr>
	<tr>
		<td>
			<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="documentNote"/>
			</font>
		</td>
	</tr>
	</table>

<html:hidden name="OpdDocumentArchivalFB" property="hmode"/>
<html:hidden name="OpdDocumentArchivalFB" property="patCrNo"/>
<html:hidden name="OpdDocumentArchivalFB" property="serialNo"/>
<html:hidden name="OpdDocumentArchivalFB" property="selectedRevoke"/>
<html:hidden name="OpdDocumentArchivalFB" property="documentDirectoryPath"/>
<html:hidden name="OpdDocumentArchivalFB" property="documentCode"/>
<html:hidden name="OpdDocumentArchivalFB" property="currentPage" />
<html:hidden name="OpdDocumentArchivalFB" property="admissionNo" />