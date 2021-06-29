<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@ page import="mrd.MrdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="mrd.transaction.controller.fb.IcdIndexingFB"%>
<%@page import="hisglobal.vo.EpisodeDiagnosisVO"%>
<%@page import="java.util.Map"%>
<%@page import="org.json.JSONArray"%> 
<%@page import="org.json.JSONObject"%> 
<%@page import="hisglobal.vo.MrdIcdDtlVO"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" /> 
<his:javascript src="/hisglobal/js/jquery.js" /> 
<his:javascript src="/mrd/js/chosen/commonFunctions.js" />


<script src="/HISClinical/mrd/js/flexdatalist/jquery.flexdatalist.min.js"></script>    
	<link rel="stylesheet" href="/HISClinical/mrd/js/flexdatalist/jquery.flexdatalist.min.css">



<script type="text/javascript">
function comboSearch(){

	$(".NormalEmpId").chosen();  

	}
	
function cancelFunction(){
	
	submitForm21('CANCEL');
}	

function toggleTable() {
    var lTable = document.getElementById("diagnosistable");
    lTable.style.display = (lTable.style.display == "table") ? "none" : "table";
}

function toggleTable2()
{
	var temp=0;
     var elem=document.getElementById("diagnosistable");
     var hide = elem.style.display =="none";
     if (hide) {
    	 
         elem.style.display="table";
    } 
    else {
       elem.style.display="none";
    }
}


	
	
function validateSave(){
	
	if(	document.getElementsByName("icdCodeList")[0].value=="")
	//if(document.getElementById('icdCodeListId').value=="")
	if(document.getElementsByName("arrIcdCodeList").length==0)
	{
	alert("Please enter ICD Code to Add!");
	document.getElementById('icdCodeListId-flexdatalist').focus();
	return false;
	}

	
	submitForm21("SAVE");
}
function clearForm()
{
	
	submitForm21("CLEAR");
	
}


function deleteDiagRow(Str)
{	
	//alert(Str.rowIndex);
	var tableObj=document.getElementById('icdtable');
	var temp=Str;
	tableObj.deleteRow(temp.rowIndex);
	
	return true;
}



function AddRowToTable(alertflag){
	
	//alert(document.getElementsByName("unitDiagnosisCodeType")[0].value)
	//var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	
   // 	var resp=document.getElementsByName('comboOptionString')[0].value;
    // 	var respDiagnosisSite=document.getElementsByName('comboDiagnosisSite')[0].value;
	//alert(resp); alert(respDiagnosisSite);
	
	//alert(document.getElementById('icdCodeListId').value);
	//alert(document.getElementById('icdCodeListId').value);
	
	if(!document.getElementById('icdCodeListId').value==""){
		var nRow=0;
		var tableObj=document.getElementById('icdtable');
		var numRows=tableObj.rows.length;
	
		if(numRows>2){
			nRow=tableObj.rows[numRows-1].id;
		}
	else{
		nRow=numRows;
	}
	
 	var icdcode =document.getElementById('icdCodeListId').value;
 	var diseaseName=document.getElementsByName('diagnosisName')[0].value;
 
 	
 	if(document.getElementsByName('arrIcdCodeList').length > 0){
 		
 		for(var i=0;i<document.getElementsByName('arrIcdCodeList').length;i++){
 	
			if(document.getElementsByName('arrIcdCodeList')[i].value==icdcode){
				if(alertflag!=false)
				{
					alert("Please Select Different ICD Code!");
				}
				document.getElementById('icdCodeListId-flexdatalist').value="";
				document.getElementsByName('diagnosisName')[0].value="";
				document.getElementById('pDiagnosisName').value="";
				$("p").text("");
				return false;
			}
		
		}
	}
 	

	var tabRow1=tableObj.insertRow(numRows);
	tabRow1.id=parseInt(nRow)+1;
	
		
	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
		
	var td3=document.createElement("TD");
	var td4=document.createElement("TD");
	

	 /* 	td1.innerHTML="<div align='center'>"
			+"<input   id='icdCodeListId' name='arrIcdCodeList' value='"+icdcode+"' type='hidden'  ></input>"
			+icdcode +"</div>";
		td1.className='tdfont';																													
		td1.colspan="1"; 
		 */
	td1.innerHTML="<div align='center'>"
				  +"<input   id='icdCodeListId' name='arrIcdCodeList' value='"+icdcode+"' type='hidden'  ></input>"
				  +icdcode +" <img src='/HISClinical/hisglobal/images/minus.gif' onClick='deleteDiagRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'/></div>";
			      td1.className='tdfont';																													
 			      td1.colspan="1"; 
 			      td1.width="40%";
			

// 			     	 td1.innerHTML="<div align='center'>"
// 			     	 td1.innerHTML="<div align='center'>"
// 			 				+"<input   id='icdCodeListId' name='arrIcdCodeList' value='"+icdcode+"' type='hidden'  ></input>"
// 			 				+icdcode +"</div>";
// 			 		 td1.innerHTML="<div align='center'>"
// 			 			+icdcode +" <img src='/HISClinical/hisglobal/images/minus.gif' onClick='deleteDiagRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'/></div></div>";
// 			 		td1.className='tdfont';																													
//     		 			td1.colspan="1"; 
			
		/* td1.innerHTML="<div align='center'>"
			+"<input   id='icdCodeListId' name='arrIcdCodeList' value='"+icdcode+"' type='hidden'  ></input>"
			+icdcode +"
			+"<img src='/HISClinical/hisglobal/images/minus.gif' onClick='deleteDiagRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'></div>";
			
		td1.className='tdfont';																													
		td1.colspan="1"; */
		
		

	td2.innerHTML="<div align='center'>"
					+"<input name='arrdiagnosisName' value='"+diseaseName+"' type='hidden'></input>"
					+diseaseName +"</div>";
	td2.className='tdfont';																													
	td2.colspan="1";

		
		
// 		td3.className='tdfont';
// 		td3.colspan="1";
// 		td3.innerHTML="<div align='center'><img src='/HISClinical/hisglobal/images/minus.gif' onClick='deleteDiagRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'></div>";
	   
	document.getElementById('icdCodeListId-flexdatalist').value="";
	document.getElementsByName('diagnosisName')[0].value="";
	document.getElementById('pDiagnosisName').value="";
	$("p").text("");
		
	
	
	tabRow1.appendChild(td1);
	tabRow1.appendChild(td2);
		
		//tabRow1.appendChild(td3);

	

	}
	else{
		alert("please select ICD Code!");
	}
	
}
	
	
//Added by Vasu on 07.March.2019
function deletePrevICDRecordRow(obj)
{
	//alert(obj);
	var icdCodeTobeRemoved = obj;
	document.getElementsByName("icdCodeToBeRemoved")[0].value = icdCodeTobeRemoved;

	submitForm21("DELETERECORD");
	
}	


function hideIcdRow(idx)
{	//alert(idx);
	var tableObj=document.getElementById('icdtable');
	var temp=document.getElementById("prevICDRecordsRow#"+(parseInt(idx)+2));
	tableObj.deleteRow(temp.rowIndex);
	//temp.style.display ="none";
	//document.getElementsByName('diagnosisRecordStatus')[parseInt(idx)+1].value="4";
	return true;
}
	
</script>

<%-- <%
	try{

	String errorMessage="hello";
%> --%>


<body>
<html:form action="/IcdIndexing">

	<his:TransactionContainer>
<his:TitleTag name="ICD Indexing">
				</his:TitleTag>
				<his:SubTitleTag name="Patient Details" >
				</his:SubTitleTag>
	<html:hidden name="IcdIndexingFB" property="patCrNo" />
	
	
	<his:ContentTag>
	
	
	<table width="100%">
	
	<tr>
				 <td width="16%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    <bean:message key="patientName"/>
				    </font>
					</div>
					
				</td>
				
		     	<td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:write name="IcdIndexingFB" property="patName"  /></b>
					</font>
				    </div>
				</td>
				
				
				<td width="16%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="crNo"/>
					</font>
				</div>
				</td>
				
				
              <td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write name="IcdIndexingFB" property="patCrNo"  />
					</font>
				</div>
				</td>
				
				
	            <td width="16%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="gender"/><font face="Verdana, Arial, Helvetica, sans-serif" size="1">/</font><bean:message key="age"/>
					</font>
					</div>
				 </td>
				
				
	           <td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="IcdIndexingFB" property="patGender"  /><font  face="Verdana, Arial, Helvetica, sans-serif" size="1">/</font><bean:write name="IcdIndexingFB" property="patAge"  />
				   </font>
				   </div>
				  </td>
			    </tr>
			
			
			
			<tr>
				 <td width="16%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    <bean:message key="primCat"/>
				    </font>
					</div>
					
				</td>
				
		     	<td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:write name="IcdIndexingFB" property="patPrimaryCat"  /></b>
					</font>
				    </div>
				</td>
				
				
				<td width="16%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="fathersName"/>
					</font>
				</div>
				</td>
				
				
              <td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write name="IcdIndexingFB" property="patGuardianName"  />
					</font>
				</div>
				</td>
				
				
	            <td width="16%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="husbandName"/>
					</font>
					</div>
				 </td>
				
				
	           <td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="IcdIndexingFB" property="patSpouceName"  />
						</font>
				   </div>
				  </td>
			    </tr>
		    	
		    	
				<tr>
				 <td width="16%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    <bean:message key="admissionNo"/>
				    </font>
					</div>
					
				</td>
				
		     	<td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:write name="IcdIndexingFB" property="addmissionNo"  /></b>
					</font>
				    </div>
				</td>
				
				
				<td width="16%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="dept/unit"/>
					</font>
				</div>
				</td>
				
				
              <td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write name="IcdIndexingFB" property="departmentName"  /><font  face="Verdana, Arial, Helvetica, sans-serif" size="1">/</font><bean:write name="IcdIndexingFB" property="departmentUnitName"  />
				</font>
				</div>
				</td>
				
				
	            <td width="16%" class="tdfonthead">
					<div align=right>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="admdatetime"/>
					</font>
					</div>
				 </td>
				
				
	           <td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:write name="IcdIndexingFB" property="admDateTime"  /></b>
					</font>
				   </div>
				  </td>
			    </tr>

			    
			    <tr>
				 <td width="16%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    <bean:message key="ward"/>
				    </font>
					</div>
					
				</td>
				
		     	<td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:write name="IcdIndexingFB" property="wardName"  /></b>
					</font>
				    </div>
				</td>
				
				
				<td width="16%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="room"/><font face="Verdana, Arial, Helvetica, sans-serif" size="1">/</font><bean:message key="bed"/>
					</font>
				</div>
				</td>
				
				
              <td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write name="IcdIndexingFB" property="ipdRoomName"  /><font  face="Verdana, Arial, Helvetica, sans-serif" size="1">/</font><bean:write name="IcdIndexingFB" property="bedName"  />
				</font>
				</div>
				</td>
				
				
	            <td width="16%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="disdatetime"/>
					</font>
					</div>
				 </td>
				
				
	           <td  width="16%" align="left" class="tdfont">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:write name="IcdIndexingFB" property="disDateTime"  /></b>
					</font>
				   </div>
				  </td>
			    </tr>
				
				
				
				
				</table>
				</his:ContentTag>						

</his:TransactionContainer>
				
								
				
			<logic:notEmpty name="<%=MrdConfig.PATIENT_DIAGNOSIS_DTLLIST%>">		
			<logic:present name="<%=MrdConfig.PATIENT_DIAGNOSIS_DTLLIST%>" >		
	        <his:TransactionContainer>
	       <%--  <logic:present name="<%=MrdConfig.PATIENT_DIAGNOSIS_DTLLIST%>" >  --%>
			<his:SubTitleTag name="Diagnosis Details">
			
<!-- 			<a id="loginLink" class="tdfonthead" onclick="toggleTable();" href="#">Show/Hide</a> -->
			<img src='/HISClinical/hisglobal/images/plus.gif' onClick='toggleTable2();'>
				</his:SubTitleTag>

	      <his:ContentTag>
	      
	    
			   
		    				 <%-- <logic:present name="<%=MrdConfig.PATIENT_DIAGNOSIS_DTLLIST%>" >  --%>

				<table width="100%" id="diagnosistable">
				
				<tr>
				<td width="16%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="diagnosisName"/></b>
					</font>
					</div>
				 </td>
				 
				 
				 <td width="16%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="dignosisTypeType"/></b>
					</font>
					</div>
				 </td>
				 
				 
				 
				 <td width="16%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="remarks"/></b>
					</font>
					</div>
				 </td>
				</tr>
				
				

				<logic:iterate id="voDiagnosisDtl"  name="<%=MrdConfig.PATIENT_DIAGNOSIS_DTLLIST%>" type="hisglobal.vo.EpisodeDiagnosisVO">
				<tr>
				<td width="16%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			        	<bean:write name="voDiagnosisDtl" property="dignosisName" /> </font></div>
								</td>
								
								
								<td width="16%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			        	<bean:write name="voDiagnosisDtl" property="diagnosticTypeName" /> </font></div>
								</td>
								
								<td width="16%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			        	<bean:write name="voDiagnosisDtl" property="remarks" /> </font></div>
								</td>
							</tr>	
								
				</logic:iterate>

				</table>
				
				
				</his:ContentTag>
					
				</his:TransactionContainer>
       </logic:present>
				
		</logic:notEmpty>		
				
				<his:TransactionContainer>
			<his:SubTitleTag name="ICD Index">
			 <table>
			    <tr> 
			      <td width="25%" > 
					<div align="right">
						<img class="button" style="cursor: pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="openPopup('/HISClinical/opd/opdDiagnosis.cnt?hmode=POPUPMRD',event,300,600);">
					</div>
				</td>	
			   </tr>
			 </table>
				</his:SubTitleTag>

	      <his:ContentTag>
		    
				
				<table width="100%" id="icdtable">
				
				<tr>
				<td width="30%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="icdcode"/></b>
					</font>
					</div>
				 </td>
				 
				 
				 <td width="30%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="disease"/></b>
					</font>
					</div>
				 </td>
				 
<!-- 				  <td width="30%" class="tdfonthead"> -->
<!-- 				 </td> -->
				 
				 
				 </tr>
				 
				
				 
				 <tr>
				<td width="40%" class="tdfont">
				<div>
					<div align="center">
					
					<input id="icdCodeListId"  type="text" name="icdCodeList">  <img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' tabindex='1' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13)  AddRowToTable()" onclick=" AddRowToTable(); ">
											 
					</div>
					</div>
				</td>
				
				<td width="40%" class="tdfont">
					<div align="center">
					
						<html:hidden 
							 name="IcdIndexingFB"  property="diagnosisName"
						   />
						<p id="pDiagnosisName"></p>
						<%-- 	<bean:write name="IcdIndexingFB"  property="diagnosisName"/> --%>
							
							
						
					</div>
				</td>
				
<!-- 				 <td  width="40%" class="tdfont"> -->
<!-- 					<div align="center"> -->
<%-- 						<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' tabindex='1' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13)  AddRowToTable()" onclick=" AddRowToTable(); "> --%>
<!-- 					</div> -->
<!-- 				</td>                                                                                                                                                                                      -->
				 
				 
		      	</tr>
		      	
		      	<!--Added by Vasu on 07.March.2019 -->
		      	<logic:notEmpty name="<%=MrdConfig.PREV_ICD_CODES%>">
		      	<logic:iterate id="prevICDIndexDtlVO" indexId="idx" name="<%=MrdConfig.PREV_ICD_CODES%>" type="hisglobal.vo.MrdIcdDtlVO">
				<%String ind=idx.toString(); %>
				<tr id="prevICDRecordsRow#<%=idx.intValue()+2%>">
				    <td width="40%" class="tdfont" >
						<div align="center">
						  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<html:hidden name="IcdIndexingFB" property="arrIcdCodeList" value="<%=prevICDIndexDtlVO.getIcdCodeList()%>" ></html:hidden>
							<%-- <html:hidden name="IcdIndexingFB" property="icdRecordStatus" value="<%=MrdConfig.ICD_RECORD_STATUS_SAVED%>" ></html:hidden> --%>
							 <bean:write name="prevICDIndexDtlVO" property="icdCodeList"/>
							 <img class="button" id="revokeButton" style="cursor:pointer" src="/HIS/hisglobal/images/avai/minus.gif" title="Cancel ICD Disease Code"  onclick="hideIcdRow('<%=idx.toString()%>')">
							</b></font>
						</div> 
					</td>
					<td width="40%" class="tdfont" >
						<div align="center">
						  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<html:hidden name="IcdIndexingFB" property="arrdiagnosisName" value="<%=prevICDIndexDtlVO.getDiagnosisName()%>" ></html:hidden>
							 <bean:write name="prevICDIndexDtlVO" property="diagnosisName"/>
							</b></font>
						</div> 
					</td>
				</tr>
				</logic:iterate>
		      	
		      	</logic:notEmpty>
		      	
		      	<!-- End Vasu -->
				
		
				</table>
				
	<script>
	   <% String tempObj = (String) request.getSession().getAttribute("icdDiagObj"); 
	   JSONArray mapIcdCodeLst = new JSONArray(tempObj);
	   %>
       var tempJSON = <%=mapIcdCodeLst.toString() %>;
	    var icdJsonObj = tempJSON; 

	
	
	
	$(document).ready(function(){
		$('#icdCodeListId').flexdatalist({
		     minLength: 1,
		     focusFirstResult: true,
		     maxShownResults: 50,
		     selectionRequired: true,
		     searchIn: 'icdCode', 
		     data: tempJSON
		 });  

		 //Added by Vasu on 07.March.2019 
		 $('#icdCodeListId-flexdatalist').on('keyup',function(event){
			if(event.which == 13)
			{
				//alert('hiii');
				 $('#addButton').click();
				}
			 });
		 
		$('input[name=icdCodeList]').on('select:flexdatalist',function(event, set, options){
			$('input[name=diagnosisName]').val(set.diagnosisName);
			 $("p").text(set.diagnosisName);
		});
		
	
	});
	</script>
				</his:ContentTag>
				
				
				
				<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSave()" onkeypress="if(event.keyCode==13) validateSave()">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
				
				</his:ButtonToolBarTag>
				</his:TransactionContainer>
				
				
				
				
		<html:hidden property="hmode"/>
		<html:hidden name="IcdIndexingFB" property="addmissionNo" />
		<html:hidden name="IcdIndexingFB" property="icdCodeToBeRemoved" />
		
		<%-- <html:hidden name="IcdIndexingFB" property="arrIcdCodeList" />
		<html:hidden name="IcdIndexingFB" property="arrdiagnosisName" /> --%>
	    <%-- <html:hidden name="IcdIndexingFB" property="icdRecordStatus" /> --%>
<%-- <%
	} catch(Exception e) 
	{e.printStackTrace();}
%> --%>

</html:form>
</body>

</html>

