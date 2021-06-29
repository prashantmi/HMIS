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
<%@page import="mrd.transaction.controller.fb.MRDDocumentUploadFB"%>
<%@page import="mrd.vo.MRDDocumentUploadVO"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="mrd.MrdConfig"%>
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
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<his:javascript src="/opd/opdJs/opd.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

/* function callThisOnload()
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
 */
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
	submitForm("REMOVE");
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
			openDependentPopup(createFHashAjaxQuery(path),event,300,600,'yes');	
		}
		else
		{
			if(fileType==fileTypeAudioVedio)
			{
			path="<%=OpdConfig.AUDIO_VIDEO_URL%>"+"?hmode=PLAY&fileType="+path;
			path="/"+path;
			//alert("path "+path);
			openDependentPopup(path,event,300,600,'yes');s
			}
		}
 

}

/* function getUploadedeDoc(event,docId,docType)
{
	//alert("Hello OPEN Doc")
	//openDependentPopup(path,event,300,600,'yes');	
	var left=(screen.width-600)/2;
	var top=(screen.height-400)/2;
	window.open("/HISClinical/mrd/mrdDocumentUpload.cnt?hmode=VIEWDOC&documentCode="+docId+"&documentDirectoryPath="+docType,"popup","height=500,width=600,left="+left+",top="+ top +",scrollbars=yes,location=no");
}
 */
function getUploadedeDoc(event,patCrNo)
{
	//alert(patCrNo)
	//openDependentPopup(path,event,300,600,'yes');	
	var left=(screen.width-600)/2;
	var top=(screen.height-400)/2;
	window.open(createFHashAjaxQuery("/HISClinical/mrd/mrdDocumentUpload.cnt?hmode=VIEWDOC&patCrNo="+patCrNo),"popup","height=500,width=600,left="+left+",top="+ top +",scrollbars=yes,location=no");
}


function submitToUpload(event)
{
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
// alert(parseInt(index))
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
	
		
			 return true;
	
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


//added by manisha gangwar date: 21.7.2017
function enableUploadButton(obj,index){
	
	var buttonDiv="uploadButtonDiv" + index;
	var uploadDiv="uploadStatusDiv" + index
	//alert("buttonDiv "+buttonDiv)
	if(obj.checked) 
		//&& document.getElementsByName("isAccept")[0].checked){
		{document.getElementById(buttonDiv+"").style.display="block";
		document.getElementById(uploadDiv+"").style.display="none";
		}
	
	else{
		document.getElementById(buttonDiv+"").style.display="none";
		document.getElementById(uploadDiv+"").style.display="block";
	}
}

function openPopFileUpload(event)
{
	var url= '<his:path src='registration/uploadFile.cnt'/>'
	//openPopup('<his:path src='registration/uploadFile.cnt'/>',event,300,600);
	openPopup(url,event,300,600);
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





function setDocumentDetails(idx)
{
var docdetailindex="docdetail_"+idx;
var arr= document.getElementsByName(docdetailindex)[0].value.split("#");
document.getElementsByName("selectedRecordId")[0].value=arr[0];
document.getElementsByName("selectedRecordType")[0].value=arr[1];
document.getElementsByName("selectedRecordTypeName")[0].value=arr[2];
document.getElementsByName("selectedAdmissionNo")[0].value=arr[3];
  
}


</script>
<body>

<his:TransactionContainer>
	<html:form action="/mrdDocumentUpload"><his:ContentTag>
	<his:TitleTag name="MRD Document Upload">
			</his:TitleTag>
		
		<his:InputCrNoTag name="MRDDocumentUploadFB"></his:InputCrNoTag>
		</his:ContentTag>
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
<%int indexVal=-1;
			%>
	
<his:ContentTag>
<his:statusList>	
 <his:SubTitleTag name="MRD Records Detail">
					</his:SubTitleTag>
<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<%-- <tr>
							<td colspan="2" width="25%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="crNo"/>
									</b>	
									</font>
								</div>
							</td>
							<td colspan="2"  width="25%" class="tdfontheadExam" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					  	 <div align="center">
					  	 	<font color="#000000" size="2">
						     <bean:write name="MRDDocumentUploadFB" property="patCrNo"/>
					    	</font>
					     </div>   
						</td>
							<td  colspan="2" width="25%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="patient"/> <bean:message key="name"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td  colspan="2" width="25%" class="tdfontheadExam" style="border-top: outset black 2px;border-bottom: inset black 2px;">
					  	 <div align="center">
					  	 	<font color="#000000" size="2">
						     <bean:write name="MRDDocumentUploadFB" property="patName"/>
					    	</font>
					     </div>   
						</td>
							
							
							
							</tr>
							 --%>
							
							 
							 <tr>
							<td width="5%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="select"/></b>
								</font>
								</div>
							</td>
							<td width="13%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="recordId"/>
									</b>	
									</font>
								</div>
							</td>
						
							<td width="13%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="recordType"/>
									</b>	
									</font>
								</div>
							</td>
						<td width="13%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="admNo"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="13%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="entryDate"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="recordStatus"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="13%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="uploadStatus"/>
									</b>	
									</font>
								</div>
							</td>
						
						</tr>
					<logic:notEmpty  name="<%= MrdConfig.MRD_RECORD_VO_LIST %>">
					<logic:iterate id="mrdRecordVO" name="<%=MrdConfig.MRD_RECORD_VO_LIST%>" indexId="idx" type="mrd.vo.MRDDocumentUploadVO">
			 		 <tr>
			 		 	<% boolean disabled=false;String color="#000000";boolean disabledbtn=false; %>
			 		 	<%if(mrdRecordVO.getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_ISSUE) ||
			 		 			mrdRecordVO.getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_LOST) ||
			 		 			mrdRecordVO.getRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_DESTROY) ){
			 		 		disabled=true;
			 		 		color="red";
			 		 	}%>
						<td class="tdfontheadExam">
						<div align="center">
						<%String uploadstatus=mrdRecordVO.getIsScanned();%>
							<%if(uploadstatus.equalsIgnoreCase("uploaded")) {
								 disabledbtn=true;
								}
							else
								{
								 disabledbtn=false; 
								} %>
							<%String enableUploadButton="enableUploadButton(this,"+idx+")";%>
							
							
							 <% String docDetail= mrdRecordVO.getRecordId()+"#"+mrdRecordVO.getRecordType()+"#"+mrdRecordVO.getRecordTypeName()+"#"+mrdRecordVO.getAdmissionNo()+"#"+mrdRecordVO.getRecordStatus();%>
							
						<html:checkbox name="MRDDocumentUploadFB" property="selectedRecord" value="<%=String.valueOf(idx)%>" onclick="<%=enableUploadButton%>" tabindex="1" disabled="<%=disabledbtn %>"/>
						
						</div>
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					  	 
						     <bean:write name="mrdRecordVO" property="recordId"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					  	 
						     <bean:write name="mrdRecordVO" property="recordTypeName"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						   	 <bean:write name="mrdRecordVO" property="admissionNo"/>
						   </font>
					     </div>   
						</td>
						
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="mrdRecordVO" property="entryDate"/>
					    	  	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<%-- <font color="<%=color %>"> --%>
					  	 		<font color="#000000">
					    	 <%String status=MrdConfig.MRD_RECORD_STATUS_ARRAY[Integer.parseInt(mrdRecordVO.getRecordStatus())]; %>
					    	 <%--  <a style="cursor: pointer"><%=status %></a> --%>
					    	<%=status %>
					    	<%--  <a style="cursor: pointer" onclick="getRecordStatus(<%= mrdRecordVO.getRecordId()%>,<%=mrdRecordVO.getRequestId() %>,'<bean:write name='MRDDocumentUploadFB' property='recordType'/>',event)"><%=status %></a>
					    	 --%></font>
					     </div>   
						</td>
						
						<td  class="tdfontheadExam">
							<%String tid="uploadStatusDiv" + idx; %>
						  	 <div id="<%=tid%>" style="display: block;" align="center">
						<%--  <bean:write name="mrdRecordVO" property="isScanned"/> --%>
						 <% if(mrdRecordVO.getIsScanned().equalsIgnoreCase("uploaded")) { %>
						 <a style="cursor:pointer" onclick="getUploadedeDoc(event,<%=mrdRecordVO.getPatCrNo()%>);"><b><%=mrdRecordVO.getIsScanned()%></b></a>
					 <%}
						 else {%>
						  <bean:write name="mrdRecordVO" property="isScanned"/> 
						 <%} %>
					  </div>   
					  	 	<%String id="uploadButtonDiv" + idx; %>
						  	 <div id="<%=id%>" style="display: none;" align="center">
						  	   	<input type="button" name="uploadDocument" value="Upload Document" onClick="setDocumentDetails(<%=idx%>); openPopFileUpload(event);" tabindex="1">
		                     <input type="hidden"   name="docdetail_<%=idx%>" value="<%=docDetail%>" /> 
		                     </div>   
                      	    
						</td>
						
						
					 </tr>
              	</logic:iterate></logic:notEmpty>
                </table>
                </his:statusList>
            	</his:ContentTag>
            	
            	




<his:ButtonToolBarTag>
		  <%-- <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick =  "submitFormOnValidate(validateIt('<%=indexVal%>'),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt('<%=indexVal%>'),'SAVE');")>
		  --%> 
		   <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick =  "submitForm21('SAVE');" onkeypress="if(event.keyCode==13)submitForm21('SAVE');">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
</his:ButtonToolBarTag>
<br><br><br><br>
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
	
	<his:status/>

<html:hidden name="MRDDocumentUploadFB" property="hmode"/>
<html:hidden name="MRDDocumentUploadFB" property="patCrNo"/>
<html:hidden name="MRDDocumentUploadFB" property="serialNo"/>
<html:hidden name="MRDDocumentUploadFB" property="selectedRevoke"/>
<html:hidden name="MRDDocumentUploadFB" property="documentDirectoryPath"/>
<html:hidden name="MRDDocumentUploadFB" property="documentCode"/>
<html:hidden name="MRDDocumentUploadFB" property="currentPage" />
<html:hidden name="MRDDocumentUploadFB" property="admissionNo" />

<html:hidden name="MRDDocumentUploadFB" property="selectedRecordId" />
<html:hidden name="MRDDocumentUploadFB" property="selectedRecordType" />
<html:hidden name="MRDDocumentUploadFB" property="selectedRecordTypeName" />
<html:hidden name="MRDDocumentUploadFB" property="selectedAdmissionNo" />


</html:form>

</his:TransactionContainer>
</body>

</html>