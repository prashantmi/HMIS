<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet"
	type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet"
	type="text/css">
<script>
var doc_Affidavit	= <%=RegistrationConfig.VERIFICATION_DOCUMENT_AFFIDAVIT%>;

function verificationDocumentsSelected(){ 

	arrSelection=new Array();
	elem = document.getElementsByName("verificationDocumentList");
	
	
	for(i=0;i<elem.length;i++){
		if(elem[i].checked){
		  	arrSelection[arrSelection.length]=elem[i].value;
		}
	}
	
	if(arrSelection.length!=0){
		 if(checkAffidavit(arrSelection)){
			callToPopulate(arrSelection);
			opener.document.getElementById("divAfterGoId").style.display='block';
			opener.document.getElementsByName("verificationDocumentAdded")[0].value='1';
			self.close();
		 }
	}
	else
	alert("Select Verification Document(s)");
}

function checkAffidavit(arrSelection){
	
	var affChecked=true;
	if(document.getElementsByName("isAffidavit")[0].value=='1')
	{
		affChecked=false;
		for(i=0;i<arrSelection.length;i++){
			var selVal=arrSelection[i];
			//alert("doc_Affidavit"+doc_Affidavit+"arrSelection[i]"+selVal.substring(0, selVal.indexOf('#')));
			if(doc_Affidavit==selVal.substring(0, selVal.indexOf('#'))){
				affChecked=true;			
			}
		}
	}
	if(!affChecked){
		alert("Affidavit Must be Selected for Dead/MLC Patient(s)");
		return false;
	}
	else
		return true;
}

function onload(){
	
	var docId=document.getElementsByName("selectedVerDocs")[0].value.toString().split('@');
	for(i=0;i<docId.length;i++){
		for(j=0;j<document.getElementsByName("verificationDocumentList").length;j++){
			var idx=document.getElementsByName("verificationDocumentList")[j].value;
			//alert("idx--"+idx+"--idx.substring(0, idx.indexOf('#')---"+idx.substring(0, idx.indexOf('#'))+"--docId[i]--"+docId[i]);
			if(docId[i]==idx.substring(0, idx.indexOf('#'))){
				document.getElementsByName("verificationDocumentList")[j].checked=true;
			}
		}
	
	}
}

</script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>


<body onload="onload();">
<input type="hidden" name="hmode"/>
<input type="hidden" name="selectedVerDocs" value="<s:property value="%{#parameters.fbArrSelectedVerifyDocs}" />"/>
<input type="hidden" name="isAffidavit" value="<s:property value="%{#parameters.fbIsAffdivate}" />"/>

<div class="wrapper rounded ">

<div class="div-table-listing rounded">
					<div class="div-table-row title">
						<div class="div-table-col">
							<s:text name="verificationDoc.verificationDoc"/>
			 			</div>
					</div>
					
					
					<s:iterator value="#session.optionVerificationDoc" status = "key" id="entry">
					<div class="div-table-row control">
						<div class="div-table-col">
							<div align="left">
								<input type = "radio" name="verificationDocumentList" value='<s:property value="#entry.value"/>|<s:property value="#entry.label"/>' />
								<s:property value="#entry.label"/>
							</div>
						</div>
					</div>
					</s:iterator>
</div>
	
<div class="div-table-button">
				<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row" align="center">
				<!-- 
				Modify Date				: 24th Nov'14
				Reason	(CR/PRS)		: Bug Id 7600
				Modify By               : Sheeldarshi
				-->
				<a href="#" class="button" onclick="return verificationDocumentsSelected()" style="text-align:center;">
				<!-- End :Sheeldarshi -->
				<span class="ok"><s:text name="ok"/></span></a>
				</div>
				</div>
					

</div>
</body>
