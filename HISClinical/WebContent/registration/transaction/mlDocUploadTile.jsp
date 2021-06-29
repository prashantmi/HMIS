<%try{ %>
<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="opd.OpdConfig"%>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script language="javaScript"  src="./hisglobal/js/calendar.js"></script>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*" %>

<script>



function callThisOnload()
{

	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
	
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
		
	document.getElementsByName("serialNo")[0].value=key;
	//	alert("uploadedFileName===  "+document.getElementsByName("uploadedFileName")[0].value);
	submitForm("REMOVE");
}
  
 function submitTile(mode){
    
   elem = document.getElementsByName('hmode')[0];
	elem.value =mode; 
	document.forms[0].submit();
 } 
 

function openPopFileUpload(event)
{
	
	openPopup('<his:path src='registration/uploadFile.cnt'/>',event,300,600);
}
function getUploadedeFile(event,path)
{
// window.open(path,'popupWindow','status=yes,scrollbars=yes,300,600,left=10,top=10,dependent=yes,resizable=yes');
openDependentPopup(path,event,300,600,'yes');

}

function submitToUpload(event)
{
	var flag=false;
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
	
	if(document.getElementsByName("documentTitle")[0].value=="")
		{
			alert("Please Enter Docment Title");
		}
	else
		{
			flag=true;
		}
	if(flag==true)
	{
		openPopFileUpload(event);
	}
		
}

function submit4image(){
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



</script>
   
<%int indexVal=-1; %>
<his:TitleTag name="MLC Document Upload">
	<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	           
     </font></b>
</his:TitleTag>  

<his:InputCrNoTag name="MlcuploadFB">	
</his:InputCrNoTag>

<bean:define id="crNo" name="MlcuploadFB" property="patCrNo" type="java.lang.String"/>

<%if(!crNo.trim().equals("")){%>
  <jsp:include page="/registration/patientDetail.cnt" flush="true" />  
  <%} %>
<his:statusRecordFound>


 
  <his:ContentTag>
  <his:SubTitleTag name="Select MLC To Upload Document">
 		</his:SubTitleTag>
  <table width="100%">
  
  
  <tr>
  <td width="5%" class="tdfonthead">
					<div align="center">				
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
  					 <b><bean:message key="select" /></b>
					</font>
					</div>					
   </td>
   <td width="15%" class="tdfonthead">
					<div align="center">				
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
  					 <b><bean:message key="mlcNo" /></b>
					</font>
					</div>					
   </td>

  <td width="15%" class="tdfonthead">
					<div align="center">				
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b><bean:message key="mlcDate" /></b>
					</font>
					</div>					
  </td>
  
  </tr>
  <logic:iterate id="arrayMLCVo" name="<%=RegistrationConfig.PATIENT_MLC_VO_ARRAY %>" type="hisglobal.vo.MlcVO">
  <tr>
  
  		<td width="5%" class="tdfont">	  
		<div align="center">				
		<html:radio name="MlcuploadFB" property="selectedMlc" value="<%=arrayMLCVo.getMlcNo() %>" onclick="submitForm('GETUPLOADDETAIL')">
		</html:radio>
					</div>
		</td>	
        <td width="15%" class="tdfont">	  
		<div align="center">				
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write	name="arrayMLCVo" property="mlcNo" />
					</font>
					</div>
		</td>		
	
		<td width="15%" class="tdfont">
					<div align="center">				
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write	name="arrayMLCVo" property="mlcDate" />
					</font>
					</div>
		</td>			
		
  </tr>
  </logic:iterate>
 </table>
</his:ContentTag>
</his:statusRecordFound>
<his:statusList>
	<logic:notEmpty name="<%=RegistrationConfig.ARRAY_DOCUMENT_UPLOAD_VO_MLC %>">
	 <his:SubTitleTag name="Uploaded Document Detail">
 		</his:SubTitleTag>
 		
 		<html:hidden name="MlcuploadFB" property="selectedMlc"/>
  	
  	<his:ContentTag> 
 <table width="100%" cellspacing="1" cellpadding="0">
 	<tr>
 	<td width="10%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="revoke"/>
				</font>
				</div>
	  		</td>
 	
 		<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
					<bean:message key="imageDesc"/>
				</font>
				</div>
	  		</td>
 		
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="uploadTime"/>
				</font>
				</div>
	  		</td>
	  		
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="reason"/>
				</font>
				</div>
	  		</td>
	  	</tr>
	 </table>
  </his:ContentTag>
  		 <his:ContentTag>
  		 	<table width="100%" cellspacing="1" cellpadding="0">
  		<logic:iterate id="docArchivalId" name="<%=RegistrationConfig.ARRAY_DOCUMENT_UPLOAD_VO_MLC%>" indexId="index"  type="hisglobal.vo.DocumentUploadDtlVO">
  			<%//String path="/HISClinical/ShowFileFromDir?"+OpdConfig.DOCUMENT_STORAGE_PATH+"="+docArchivalId.getDocumentDirectoryPath()+"&"+OpdConfig.FILE_NAME+"="+docArchivalId.getDocumentName()
  			//+"&"+OpdConfig.IS_EXTENSION+"="+OpdConfig.IS_EXTENSION_FALSE;
  			%>
  			<%
							String path = ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" 
									+ ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.MLC_DOC_IMAGE_FILE_STORAGE_PATH + "&" 
									+ ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.MLC_DOC_IMAGE_FILE_STORAGE_PATH_LINUX + "&" 
									+ ServletsUtilityConfig.FILE_NAME + "=" + docArchivalId.getDocumentName(); 
  						%>
  			<tr>
  			<td width="10%"  class="tdfont">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<html:checkbox name="MlcuploadFB" property="revoke" value="<%=String.valueOf(index.intValue()) %>" onclick="enableReason(this)" tabindex="1" ></html:checkbox>
				</font>
				</div>
	  		</td>
  			
  			<td width="20%"  class="tdfont">
					<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<a  style="cursor:pointer" onclick="getUploadedeFile(event,'<%=path%>')" ><b><bean:write name="docArchivalId" property="documentTitle"/></b></a>
				<html:hidden name="MlcuploadFB"  property="prevAddDocTitle" value="<%=docArchivalId.getDocumentTitle() %>"/>
				</font>
				</div>
	  		</td>
		 
		  	<td width="20%"  class="tdfont">
					<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:write name="docArchivalId" property="entryDate"/> </b>
				</font>
				</div>
	  		</td>
	  		
	  		<td width="20%"  class="tdfont">
					<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:define id="removeReasonId" name="MlcuploadFB" property="removeReason"  type="java.lang.String[]" ></bean:define>
				<html:text name="MlcuploadFB" property="removeReason" disabled="true" tabindex="1" value="<%=(String)removeReasonId[index.intValue()]%>" ></html:text>  </b>
				</font>
				</div>
	  		</td>
  			</tr>
  		</logic:iterate>
  			</table>
  		 </his:ContentTag>
  		 
  	</logic:notEmpty>
  	
  	<his:SubTitleTag name="Upload Document">
  	</his:SubTitleTag>
  	
 	<his:ContentTag>
 		<table width="100%" cellspacing="1" cellpadding="0">
 			
				
	  		<tr>
	  		<td width="20%"  class="tdfonthead" nowrap="nowrap">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="imageDesc"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfont" nowrap="nowrap" >
				<div align="left">	           
					<html:text name="MlcuploadFB" property="documentTitle" size="50" 
						maxlength="50" onkeypress="return validateAlphaOnly(this,event)" tabindex="1"></html:text>
				</div>
	  		</td>
	  		</tr>
	  		</table>
	  		<table>
	  		<tr>
	  			<td width="10%"  class="tdfonthead" nowrap="nowrap" >
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="uploadFile"/></b>
				</font>
				</div>
				</td>
	  			
	  			
	  			<td width="10%"  class="tdfont" nowrap="nowrap" >
	  			<div align="left">
				<img class="button"  class="button" src="../hisglobal/images/plus.png"  border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13)submitToUpload(event);" onClick="submitToUpload(event);" >
				</div>
			</td>	
	  		
	  		
	  		</tr>
 		</table>
 	</his:ContentTag>
 	<logic:notEmpty name="<%=OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP%>" >
 	<his:ContentTag>
 	<table width="100%" cellspacing="1" cellpadding="0">
 			<tr>
 			
	  		
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				<bean:message key="imageDesc"/>
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
				<img class="button" border="0" src='<his:path src="/hisglobal/images/Minus.png"/>' tabindex="1" style="cursor:pointer" onkeypress="if(event.keyCode==13)deleteRow('<%=indexVal%>')" onClick="deleteRow('<%=indexVal%>')">
				</div>
			</td>
	  		</tr>
 	</table>
 	
 	</logic:iterate>
 	
 	</his:ContentTag>
 	</logic:notEmpty>
</his:statusList>	
	
	
	 <his:ButtonToolBarTag>
	 <his:statusNew>
	  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style='cursor:pointer' onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
	 </his:statusNew>
	 <his:statusRecordFound>
	  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style='cursor:pointer' onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick ="submitForm('NEW');">
	 </his:statusRecordFound>
	 <his:statusList>
	  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick = "if(validateIt('<%=indexVal%>')) submitForm('SAVE');" onkeypress="if(event.keyCode==13) if(validateIt('<%=indexVal%>')) submitForm('SAVE');">
	 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitForm('GETUPLOADDETAIL')" onkeypress="if(event.keyCode==13) submitForm('GETUPLOADDETAIL');">
	  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style='cursor:pointer' onkeypress="if(event.keyCode==13) submitForm('GETPATDTL');" tabindex="1" onclick ="submitForm('GETPATDTL');">
	 
	 </his:statusList>
	
	
</his:ButtonToolBarTag>
 <his:statusList>
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
</his:statusList>
   <his:status/>
   
<html:hidden name="MlcuploadFB" property="hmode"/>
<html:hidden name="MlcuploadFB" property="uploadedFileName"/>
<html:hidden name="MlcuploadFB" property="serialNo"/>
<html:hidden name="MlcuploadFB" property="selectedRevoke"/>

<%}catch(Exception e)
{
	e.printStackTrace();	
}%>