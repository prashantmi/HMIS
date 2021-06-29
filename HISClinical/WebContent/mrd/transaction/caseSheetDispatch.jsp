
<%
	try {
%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="mrd.MrdConfig"%>

<%@page import="mrd.transaction.controller.fb.CaseSheetDispatchFB"%>
<%@page import="hisglobal.vo.CaseSheetDtlVO"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<link rel="stylesheet" type="text/css"
	href="/HIS/hisglobal/js/jquery/jquery.dataTables.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/hisglobal/transactionutil/js/master.js" />
<his:javascript src="/hisglobal/js/util.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery.dataTables.js"></script>
<script type="text/javascript">
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function enableEnclosureButton(obj,index){
	
	var buttonDiv="enclosureButtonDiv" + index;
	//alert("buttonDiv "+buttonDiv);
	if(obj.checked){
		if(checkForIsDelaySelected(index)){
			if(document.getElementById("isDelay"+index).value==1)//document.getElementsByName("isDelay")[index].value==1)
			{
				document.getElementById("delayReasonDiv").style.display="block";
			}
			document.getElementById(buttonDiv+"").style.display="block";
		}
		else{
			alert("Cannot select the Delay Record with other records");
		}
	}
	else{
		document.getElementById(buttonDiv+"").style.display="none";
		if(!validateIsDelayRecord()){
			document.getElementById("delayReasonDiv").style.display="none";
		}	
	}
}
 
function clearForm()
{

	 document.getElementById("customDiv").style.display="none";
	 document.getElementById("customDiv1").style.display="none";
	 //document.getElementById("example_wrapper").style.display="none"
		 
		 document.getElementsByName('departmentUnitCode')[0].value="-1";
		 document.getElementsByName('wardCode')[0].value="-1";
		
    document.getElementsByName("patAdmNo")[0].value="";
    //document.getElementsByName("strCrNo")[0].value="";
    document.getElementById("abc").style.display="none";
    document.getElementsByName("strCrNo")[0].value="";
 
	     
}

//return true if either all record selected is not delay or all the record selected is delayed 
function checkForIsDelaySelected(index){

	var selectedPat=document.getElementsByName("selectCRNo")
	//alert(selectedPat);
	var valid=true;
	for(var j=0;j<selectedPat.length;j++){
		if(selectedPat[j].checked)
		{
			if(document.getElementById("isDelay"+index).value==1                     //document.getElementsByName("isDelay")[j].value==1  //document.getElementsByName("isDelay")[index].value==1
					&& validateIsDelay()){
				//alert("Cannot select the Delay Record with other records");
				//selectedPat[j].checked=false;//selectedPat[index].checked=false;
				/* selectedPat[j].checked=false; */
				selectedPat[index].checked=false;
				return false;
				}
				else
				{
				if(document.getElementById("isDelay"+index).value!="1" && validateIsDelayRecord())                   // document.getElementsByName("isDelay")[j].value!="1" && validateIsDelayRecord())//document.getElementsByName("isDelay")[index].value!="1"
					{
					//alert("not is delay")
					//alert("Cannot select the Delay Record with other records");
					selectedPat[j].checked=false;//selectedPat[index].checked=false;
					return false;
				}
			}
		}
		
	}
	return valid;
}
function getDetail(obj)
{
	document.getElementsByName("patCrNo")[0].value=obj.value.split("#")[0];
	submitForm('SHOWPATIENTDETAILS')
}

function getWardByUnit(){
	document.getElementsByName("hmode")[0].value="UNIT"
	var unitCode=document.getElementsByName("departmentUnitCode")[0].value
	if(unitCode=="-1"){
		document.getElementsByName("hmode")[0].value="NEW"
	}
	document.forms[0].submit();
}

function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}


function getEnclosures(index){
	
	//alert(index)
	var selectedPatient=document.getElementById("selectCRNo"+index).value;//document.getElementsByName("selectCRNo")[index].value
	//selectedPatient=selectedPatient.replace("#","@");
	document.getElementsByName("selectedPatient")[0].value=selectedPatient;//document.getElementsByName("selectCRNo")[index].value

	
	var path="/HISClinical/mrd/caseSheetDispatch.cnt?hmode=GETESSENTIALS&selectedPatient=" + selectedPatient +"&index=" + index ;
	var popup=window.open(createFHashAjaxQuery(path),"Enclosures",'height=300,width=600,left=200,top=150,scrollbars=yes');

}
 
function submitSave(){
	
	if(validateSave()){
		document.getElementsByName("hmode")[0].value="SAVE"
		document.forms[0].submit()
	}
	else{
		return false;
	}	
} 

function validateSave(){
	//alert("A");
	var isEnclosureSelected=document.getElementsByName("isEnclosureSelected");
	var selectedPat=document.getElementsByName("selectCRNo");
	var valid=false;
	for(var j=0;j<selectedPat.length;j++){
		if(selectedPat[j].checked){
			valid=true;	
		}
	}
	if(!valid){
		alert("Please Select at least One Record")
		return false;
	}
	for(var i=0;i<isEnclosureSelected.length;i++)
	{
		//for(var j=0;j<selectedPat.length;j++){
			if(selectedPat[i].checked)// selectedPat[j].checked && selectedPat[j].value.split("@")[2]==i)
			{
				if(isEnclosureSelected[i].value!="1"){
					alert("Please Select Enclosure For " + document.getElementsByName("patientName")[i].value);
					return false;
				}
			}
	
		//}
	}
	
	/*if(!document.getElementsByName("isChecklistSelected")[0].value=="1"){
		alert("Please Select Checklist");
		return false;
	}
	*/
	if(validateIsDelayRecord()){
		if(!isEmpty(document.getElementsByName("delayReason")[0],"Delay Reason")){
			return false;
		}
	}
	return true;
}

function getChecklist(){
	var path="/HISClinical/mrd/caseSheetDispatch.cnt?hmode=GETCHECKLIST" ;
	var popup=window.open(createFHashAjaxQuery(path),"Checklist",'height=300,width=600,left=200,top=150,scrollbars=yes');
	
}

function selectAllRecord(obj){
	var selectCRNo=document.getElementsByName("selectCRNo");
	//alert(selectCRNo.length);
	if(obj.checked){
		for(var i=0;i<selectCRNo.length;i++)
		{
			//alert(i);
			var chk = document.getElementById("selectCRNo"+i);//selectCRNo[i]
			chk.checked=true;
			//alert(chk.checked);
			enableEnclosureButton(chk,i);
		}
	}
	else
	{
		for(var i=0;i<selectCRNo.length;i++)
		{
			var chk = document.getElementById("selectCRNo"+i);//selectCRNo[i]
			chk.checked=false;
			//alert(chk.checked);
			enableEnclosureButton(chk,i);
			//selectCRNo[i].checked=false
			//enableEnclosureButton(selectCRNo[i],i);
		}
	}
}

//return true if any of the record is selected which is not delayed
function validateIsDelay(){
	var selectedPat=document.getElementsByName("selectCRNo")
	var valid=false;
	for(var j=0;j<selectedPat.length;j++){
		if(selectedPat[j].checked && document.getElementsByName("isDelay")[j].value!="1"){ 
			valid=true;
			break;
		}
	}
	//alert("valid ="+valid)
	return valid;
}

// return true if any of the delay record has been selected
function validateIsDelayRecord(){
	var selectedPat=document.getElementsByName("selectCRNo")
	var valid=false;
	for(var j=0;j<selectedPat.length;j++){
		if(selectedPat[j].checked && document.getElementsByName("isDelay")[j].value=="1"){ 
			valid=true;
			break;
		}
	}
	//alert("valid ="+valid)
	return valid;
}

function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

$(document).ready(function(){
	
   
    console.log("DataTables framework loaded...");
    $('<thead></thead>').prependTo('#example').append($('#example tr:first'));
    $("#example").DataTable();  
    console.log("Interactive table is ready!"); 
    $("#example").DataTable(); 
    $("#example_length").css({"font-weight":"bold","font-size":"12px"}) ;
    $("#example_filter").css({"font-weight":"bold","font-size":"12px"});
    $("#example_info").css({"font-weight":"bold","font-size":"12px"}) ;
    $("#example_paginate").css({"font-weight":"bold","font-size":"12px"}) ;
    
    /* if(isdelay==1)
        {
    	 $(".even").css({"color":"#ffccf2"}) ;
    	 $(".odd").css({"color":"#ffccf2"}) ;
        }  */
});




//added by swati 
//date:30-04-2019
function changeUI()
{
	
 
    if(document.getElementById("r2").checked == true)
    {
        document.getElementById("CRNOID").style.display="block";
        document.getElementById("wardwisediv").style.display="none";
        document.getElementById("ADMID").style.display="none";
    }
    if(document.getElementById("r3").checked == true)
    {
        document.getElementById("ADMID").style.display="block";
        document.getElementById("wardwisediv").style.display="none";
        document.getElementById("CRNOID").style.display="none";
    }
     
    
    if(document.getElementById("r1").checked == true)
    {
        document.getElementById("CRNOID").style.display="none";
        document.getElementById("ADMID").style.display="none";
        document.getElementById("wardwisediv").style.display="block";
        document.getElementsByName("strCrNo")[0].value = "";
        //submitForm('NEW');
    }
    
    
     
    
    
    
}


//added by swati on date:01-05-2019

function checkMode()
{

	
	if(document.getElementsByName("hmode")[0].value == "NEW"  )
    {
         document.getElementById("CRNOID").style.display="none";
         document.getElementById("ADMID").style.display="block";
         document.getElementById("wardwisediv").style.display="none";
         document.getElementById("r3").checked=true;
    } 

	
     if(document.getElementsByName("hmode")[0].value == "SHOWLISTADMNOWISE"  )
     {
          document.getElementById("CRNOID").style.display="none";
          document.getElementById("ADMID").style.display="block";
          document.getElementById("wardwisediv").style.display="none";
          document.getElementById("r3").checked=true;
         // document.getElementById("abc").style.display="none";
     } 

     if(document.getElementsByName("hmode")[0].value == "SHOWLISTCRNOWISE"  )
     {
    	  document.getElementById("ADMID").style.display="none";
          document.getElementById("CRNOID").style.display="block";
          document.getElementById("wardwisediv").style.display="none";
          document.getElementById("r2").checked=true;
     } 

     if(document.getElementsByName("hmode")[0].value == "SHOWLIST"  )
     {
        
    	  document.getElementById("ADMID").style.display="none";
          document.getElementById("CRNOID").style.display="none";
          document.getElementById("wardwisediv").style.display="block";
          document.getElementById("r1").checked=true;
     }

     if(document.getElementsByName("hmode")[0].value == "UNIT"  )
     {
    	  document.getElementById("ADMID").style.display="none";
          document.getElementById("CRNOID").style.display="none";
          document.getElementById("wardwisediv").style.display="block";
          document.getElementById("r1").checked=true;
     }
}



</script>
<body onload="checkMode();">
	<html:form action="/caseSheetDispatch">
		<%
			//int startIndex=-1, endIndex=-1;
		%>
		<html:hidden name="CaseSheetDispatchFB" property="patCrNo" />
		<his:TransactionContainer>

			<his:TitleTag name="Case Sheet Ready">
			</his:TitleTag>

			<%
				/*PaginationFB fbPage= new PaginationFB();
										pageContext.setAttribute("fbPagination",fbPage);
										fbPage.setCurrentPage(((CaseSheetDispatchFB)request.getAttribute("CaseSheetDispatchFB")).getCurrentPage());
										fbPage.setObjArrName(MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY);
										fbPage.setAppendInTitle("Records");
										fbPage.setMaxRecords(10);*/
			%>
			<html:hidden name="CaseSheetDispatchFB" property="currentPage" />
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<logic:notEqual name="CaseSheetDispatchFB" property="hmode"
					value="SHOWPATIENTDETAILS">

					<!-- 				added by swati sagar on date:30-04-2019 -->
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfont">
							<div align="right">
								<input type="radio" value="1" id="r1" name="strCheckMode"
									onclick="changeUI();">Ward/Unit Wise &nbsp; <input
									type="radio" value="3" id="r3" name="strCheckMode"
									onclick="changeUI();">Admission No. Wise <input
									type="radio" value="2" id="r2" name="strCheckMode"
									onclick="changeUI();">CR No. Wise

							</div>
						</td>
					</tr>
					<!-- 				-- -->
				</logic:notEqual>
			</table>



			<!-- 				added by swati sagar on date:30-04-2019 -->


			<div id="ADMID" style="display: none;">
				<table class="TABLEWIDTH" width="100%">
					<tr>
						<td width="35%" class="tdfonthead"><font color="red"
							id="mandCRId">*</font>Admission No.</td>
						<td width="35%" class="tdfont">
							<div >
								<html:text property="patAdmNo" name="CaseSheetDispatchFB"
									onkeypress='return numbersonly(event);' maxlength="15"></html:text>

								<img src="../hisglobal/images/GO.png"
									onClick="submitForm21('SHOWLISTADMNOWISE');" align="top"
									style="cursor: pointer;">
							</div>
						</td>
						<!-- <td width="15%" class="tdfonthead"></td>
						<td width="25%" class="tdfont"></td> -->
					</tr>
				</table>
			</div>


			<div id="CRNOID" style="display: none;">
				<table class="TABLEWIDTH" width="100%">
					<tr>
						<td width="25%" class="tdfonthead"><font color="red"
							id="mandCRId">*</font>CR No.</td>
						<td width="35%" class="tdfont">
							<div align="center">
								<html:text property="strCrNo" name="CaseSheetDispatchFB"
									onkeypress='return numbersonly(event);' maxlength="15"></html:text>

								<img src="../hisglobal/images/GO.png"
									onClick="submitForm21('SHOWLISTCRNOWISE');" align="top"
									style="cursor: pointer;">
							</div>
						</td>
						<td width="15%" class="tdfonthead"></td>
						<td width="25%" class="tdfont"></td>
					</tr>
				</table>
			</div>


			<div id='wardwisediv'>
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="unit" />&nbsp;
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="CaseSheetDispatchFB"
										property="departmentUnitCode" styleClass="regcbo"
										onchange="getWardByUnit()" tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.MRD_DEPT_UNIT_LIST%>">
											<html:options collection="<%=MrdConfig.MRD_DEPT_UNIT_LIST%>"
												labelProperty="label" property="value" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="ward" />&nbsp;
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="CaseSheetDispatchFB" property="wardCode"
										styleClass="regcbo" onchange="submitForm21('SHOWLIST')"
										tabindex="1">
										<html:option value="-1">Select Value</html:option>
										<logic:present
											name="<%=MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT%>">
											<html:options
												collection="<%=MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT%>"
												labelProperty="label" property="value" />
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</div>

			<!-- 					-- -->


			<his:statusTransactionInProcess>

				<%
					if (session
											.getAttribute(MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY) != null) {
				%>
				<%-- <his:PaginationTag name="fbPagination"></his:PaginationTag>	 --%>
				<his:ContentTag>
				<div id="customDiv">
					<table id="example" width="100%" border="0" cellspacing="1"
						cellpadding="0">
						<tr>
							<td width="4%" class="tdfonthead"
								style="border-top: outset black 2px; border-bottom: inset black 2px;">
								<!-- <div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<input type="checkbox" name="selectAll" onclick="selectAllRecord(this)" tabindex="1"> 
								</b>	
								</font>
							</div> -->
							</td>
							<td width="10%" class="tdfonthead"
								style="border-top: outset black 2px; border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
												key="crNo" />
									</b>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead"
								style="border-top: outset black 2px; border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
												key="admNo" />
									</b>
									</font>
								</div>
							</td>
							<td width="16%" class="tdfonthead"
								style="border-top: outset black 2px; border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
												key="patientName" />
									</b>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead"
								style="border-top: outset black 2px; border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b>
											Admission Date Time </b>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead"
								style="border-top: outset black 2px; border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b>
											Discharge Date Time </b>
									</font>
								</div>
							</td>
							<td width="8%" class="tdfonthead"
								style="border-top: outset black 2px; border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
												key="gender/age" />
									</b>
									</font>
								</div>
							</td>
							<td width="7%" class="tdfonthead"
								style="border-top: outset black 2px; border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
												key="isOriginal" />
									</b>
									</font>
								</div>
							</td>
							<td width="7%" class="tdfonthead"
								style="border-top: outset black 2px; border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b>
											is Delay </b>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead"
								style="border-top: outset black 2px; border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
												key="returnReason" />
									</b>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead"
								style="border-top: outset black 2px; border-bottom: inset black 2px;">
								<div align="center">
									<font color="red"><b>*</b></font> <font color="#000000"
										size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b><bean:message key="enclosure" /></b>
									</font>
								</div>
							</td>


							<%-- <td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
							<div align="center">
								<font color="red"><b>*</b></font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="clinicalentryform"/></b>	
								</font>
							</div>
						</td> --%>
						</tr>

						<%
							//PatientDetailVO patientDtlVOs=new PatientDetailVO();
						%>
						<logic:present
							name="<%=MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY%>">
							<%
								List list = (List) session
																	.getAttribute(MrdConfig.PATIENT_DETAILS_FOR_CASESHEET_DISPATCH_VO_ARRAY);

															/* int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
															int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX); */
															String fontCss = "";
															String isDisabled = "";//false;"disabled"
															int index = 0;
															for (int i = 0; i < list.size(); i++)//for(int i=startIndex;i<endIndex;i++)
															{
																CaseSheetDtlVO caseSheetDtlVO = (CaseSheetDtlVO) list
																		.get(i);
																pageContext.setAttribute(
																		"caseSheetDtlVO",
																		caseSheetDtlVO);
																String backgroundColor = "";
																String tdfont = "";//class='tdfont'";
																String isDelayed = "";
																if (caseSheetDtlVO.getIsDelay()
																		.equals("0")) {
																	isDelayed = "Normal";
																} else if (caseSheetDtlVO
																		.getIsDelay().equals("1")) {
																	//color="color='#990000'"; 
																	backgroundColor = "bgcolor='#FFC0CB'";
																	tdfont = "";
																	isDelayed = "Delayed";
																} else {
																	isDelayed = "Returned";
																}
							%>
							<%
								tdfont = backgroundColor;
																System.out.println(tdfont + " "
																		+ backgroundColor);
							%>
							<tr<%-- <%=backgroundColor%> --%>>
								<td<%--  <%=backgroundColor%> --%> >
									<div align="center">
										<%-- 
					<%String enableEnclosureButton="enableEnclosureButton(this,"+ index+ "); enableClinicalButton(this,"+ index+ ");"; %> --%>
										<%
											String enableEnclosureButton = "enableEnclosureButton(this,"
																					+ index + ");";
										%>
										<input type="checkbox" id="selectCRNo<%=index%>"
											name="selectCRNo"
											value='<%=caseSheetDtlVO
													.getPatCrNo()
													+ "@"
													+ caseSheetDtlVO
															.getCaseSheetId()
													+ "@"
													+ index
													+ "@"
													+ caseSheetDtlVO
															.getEpisodeCode()
													+ "@"
													+ caseSheetDtlVO
															.getEpisodeVisitNo()%>'
											onclick="<%=enableEnclosureButton%>" tabindex="1" />
									</div>
								</td>
								<%
									String color = "color='#000000'";
								%>
								<logic:equal name="caseSheetDtlVO" property="isMLC" value="1">
									<%
										color = "color='#990000'";
									%>
								</logic:equal>
								<td <%=tdfont%>>
									<div align="center">
										<font <%=color%>> <b><bean:write
													name="caseSheetDtlVO" property="patCrNo" /></b> <input
											type="hidden" id="isEnclosureSelected<%=index%>"
											name="isEnclosureSelected" /> <input id="isDelay<%=index%>"
											type="hidden" name="isDelay"
											value="<%=caseSheetDtlVO
													.getIsDelay()%>" />
										</font>
									</div>
								</td>
								<td <%=tdfont%>>
									<div align="center">
										<font <%=color%>> <b><%=caseSheetDtlVO
													.getPatAdmNo()%></b>
										</font>
									</div>
								</td>
								<td <%=tdfont%>>
									<div align="center">
										<font <%=color%>> <b><%=caseSheetDtlVO
													.getPatName()%></b>
											<input type="hidden" id="patientName<%=index%>"
											name="patientName" value='<%=caseSheetDtlVO
													.getPatName()%>' />
										</font>
									</div>
								</td>
								<td <%=tdfont%>>
									<div align="center">
										<font <%=color%>> <b><%=caseSheetDtlVO
													.getAdmDateTime()%></b>
										</font>
									</div>
								</td>
								<td <%=tdfont%>>
									<div align="center">
										<font <%=color%>> <b><%=caseSheetDtlVO
													.getDisDate()%></b>
										</font>
									</div>
								</td>
								<td <%=tdfont%>>
									<div align="center">
										<font <%=color%>> <b><%=caseSheetDtlVO
													.getPatGenderAge()%></b>
										</font>
									</div>
								</td>
								<td <%=tdfont%>>
									<div align="center">
										<font <%=color%>> <%
 	String isDuplicate = caseSheetDtlVO
 											.getIsDuplicate();
 									if (caseSheetDtlVO
 											.getCaseSheetStatusName() != null)
 										isDuplicate = isDuplicate
 												+ "("
 												+ caseSheetDtlVO
 														.getCaseSheetStatusName()
 												+ ")";
 %>
											<b><%=isDuplicate%></b>
										</font>
									</div>
								</td>


								<td <%=tdfont%>>
									<div align="center">
										<font <%=color%>> <b><%=isDelayed%></b>
										</font>
									</div>
								</td>

								<td <%=tdfont%>>
									<div align="center">
										<b> <%
 	String recordStatus = "";
 									if (caseSheetDtlVO
 											.getRecordStatus() == null)
 										recordStatus = "-";
 									else
 										recordStatus = caseSheetDtlVO
 												.getRecordStatus();
 									if (caseSheetDtlVO
 											.getRecordStatus() != null)//recordStatus.split("#")[0].equals(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN))
 									{
 %> <font <%=color%>> <%
 	String fun = "openPopup('/HISClinical/mrd/caseSheetApproval.cnt?hmode=POPUP&reason="
 												+ recordStatus
 														.split("#")[1]
 												+ "',event,200,400)";
 %>
												<a onclick="<%=fun%>"
												onkeypress="if(event.keyCode=='13') <%=fun%>"
												style="cursor: pointer;" tabindex="1"> View</a>
										</font> <%
 	} else {
 										out.print(recordStatus);
 									}
 %></b>
									</div>
								</td>
								<td <%=tdfont%>>
									<%
										String id = "enclosureButtonDiv"
																				+ index;
									%> <%
 	int ind = index;
 %>
									<div id="<%=id%>" style="display: none;" align="center">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> <input
											type="button" id="selectEnclosure<%=index%>"
											name="selectEnclosure" value="Select Enclosure"
											onclick="getEnclosures('<%=index++%>');" tabindex="1">
										</font>
									</div>
								</td>

								<!-- commented for Release sec vulnerablity -->
								<%--<td <%=tdfont %>>  
						 <%String idd="clinicalButtonDiv" + ind; %>
				  	 <div id="<%=idd%>" style="display: none;" align="center">
				     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				    	<input type="button" name="clinicalEntryForm" value="Clinical Entry" onclick="getClinicalForm('<%=ind++%>');" tabindex="1">
                     	 </font>
				     </div>      
					</td>--%>
								<!-- end commented for Release sec vulnerablity -->
							</tr>
							<%
								}
							%>
						</logic:present>
					</table>
					</div>
				</his:ContentTag>
				<%
					}
				%>

				<%-- 
			<his:SubTitleTag name="Checklist">
			</his:SubTitleTag>
			<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				 <tr>
		    	    <td id="checklistButtonDiv" width="50%" class="tdfonthead">
			        	<div align="right">
<!--						<font color="red"><b>*</b></font>-->
			            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
<!--							<a onclick="getChecklist()" style="cursor: pointer;"><b> Select Checklist</b></a>-->
							<bean:message key="select"/> <bean:message key="checklist"/>
						</font>
				     	</div>
				     	<html:hidden name="CaseSheetDispatchFB" property="isChecklistSelected"/>
		    		</td>
		    		<td width="50%" class="tdfont">
			        	<table><tr><td align="left">
			        	<div align="left">
			            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
			          &nbsp;
<!--							<input type="button" value="..." onclick="getChecklist()">-->
							<img class="button" src='<his:path src="/hisglobal/images/icon-vrf.png"/>'  style="cursor:pointer" tabindex="1" onclick =" getChecklist()" onkeypress="if(event.keyCode==13)getChecklist()">
						</font> 
						</div></td>
						<td align="right">
							<div id="displaySelectedDiv" align="left" style="display: none">
								<font color="#ff0000"><b>Selected</b></font>
							</div>
						</td>
						</tr>
						</table>
				    </td>
		    	</tr>
		    </table>
		    </his:ContentTag>	
		    --%>
				<div id="delayReasonDiv" style="display: none">
					<his:ContentTag>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td width="50%" class="tdfonthead">
									<div align="right">
										<font color="red"><b>*</b></font> <font color="#000000"
											size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="delayReason" />
										</font>
									</div>
								</td>
								<td width="50%" class="tdfont"><html:textarea
										property="delayReason"
										onkeypress="return CheckMaxLength(event,this,100,1)" cols="50"
										tabindex="1"></html:textarea></td>
							</tr>
						</table>
					</his:ContentTag>
				</div>
				
               <div id="customDiv1">
				<his:SubTitleTag>
				
					<his:name>
						<bean:message key="legends" />
					</his:name>
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td width="70%"></td>
							<td width="30%">
								<div align="right">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif">Show </font><img
										src='<his:path src="/hisglobal/images/arrow_down.gif"/>'
										onclick="showLegends();"> <font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif">Hide </font><img
										src='<his:path src="/hisglobal/images/arrow_up.gif"/>'
										onclick="showLegendsNone();">
								</div>
							</td>
						</tr>
					</table>
					
				</his:SubTitleTag>
				</div>
				
				<div id="divLegends" style="display: none">
					<his:ContentTag>
						<table width="100%" colspacing="1" colpadding="0"
							style="clear: both; border-left: 1px solid #003366; border-top: 1px solid #003366">
							<tr>
								<td width="5%"><font color="#990000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b>red</b>
								</font></td>
								<td width="5%"></td>
								<td width="90%">
									<div align="left">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> MLC
											CaseSheet </font>
									</div>
								</td>
							</tr>
							<tr>
								<td width="5%" bgcolor="#FFC0CB"><font color="#990000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b></b>
								</font></td>
								<td width="5%"></td>
								<td width="90%">
									<div align="left">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> Delayed
											CaseSheet </font>
									</div>
								</td>
							</tr>
							<tr>
								<td width="5%"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b>black</b>
								</font></td>
								<td width="5%"></td>
								<td width="90%">
									<div align="left">
										<font color="#000000" size="2"
											face="Verdana, Arial, Helvetica, sans-serif"> General
											CaseSheet </font>
									</div>
								</td>
							</tr>
						</table>
					</his:ContentTag>
				</div>
			</his:statusTransactionInProcess>

			<his:ButtonToolBarTag>
				<his:statusTransactionInProcess>
					<img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
						style="cursor: pointer" tabindex="1" onclick="return submitSave()"
						onkeypress="if(event.keyCode==13) return submitSave()">
					<img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
						style="cursor: pointer" tabindex="1" onclick=" clearForm()"
						onkeypress="if(event.keyCode==13)clearForm()">
					<img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
						style="cursor: pointer" tabindex="1"
						onclick=" submitForm21('NEW')"
						onkeypress="if(event.keyCode==13)submitForm21('NEW')">
				</his:statusTransactionInProcess>
				<his:statusNew>
					<img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
						style="cursor: pointer" tabindex="1" onclick=" submitForm21('NEW')"
						onkeypress="if(event.keyCode==13)submitForm21('NEW')">
				</his:statusNew>
			</his:ButtonToolBarTag>
		</his:TransactionContainer>

		<html:hidden name="CaseSheetDispatchFB" property="hmode" />
		<html:hidden name="CaseSheetDispatchFB" property="departmentUnitCode" />
		<html:hidden name="CaseSheetDispatchFB" property="departmentCode" />
		<html:hidden name="CaseSheetDispatchFB" property="wardCode" />
		<html:hidden name="CaseSheetDispatchFB" property="patAdmNo" />
		<html:hidden name="CaseSheetDispatchFB" property="selectedPatient" />
		<html:hidden name="CaseSheetDispatchFB" property="isdelay" />
		<html:hidden name="CaseSheetDispatchFB" property="ismlc" />
		<html:hidden name="CaseSheetDispatchFB" property="clearmsg" />
		<input type="hidden" name="isDelayChecked" />
	</html:form>
	<div  id="abc">
	<his:status />
	</div>
</body>

<script>



function enableClinicalButton(obj,index){
	//alert("helllo"+index);
	
	var clinicalbuttonDiv="clinicalButtonDiv" + index;
	alert("clinicalbuttonDiv "+buttonDiv);;
	if(obj.checked){
		
			document.getElementById(clinicalbuttonDiv+"").style.display="block";
			
	}
	else{
		
			document.getElementById(clinicalbuttonDiv+"").style.display="none";
	}
}

function getClinicalForm (index){
	
	//alert(index)
	var selectedPatient=document.getElementsByName("selectCRNo")[index].value
	//selectedPatient=selectedPatient.replace("#","@");
	document.getElementsByName("selectedPatient")[0].value=document.getElementsByName("selectCRNo")[index].value
	var arr=selectedPatient.split("@");
	var cr=arr[0];
	var path="/HISClinical/emr/uniPagePrescription.cnt?hmode=CLINICALMRD&patCrNo="+cr+"&selectedPatient=" + selectedPatient +"&index=" + index ;
	var popup=window.open(createFHashAjaxQuery(path),"Enclosures",'height=300,width=600,left=200,top=150,scrollbars=yes');

}




</script>

</html>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>



