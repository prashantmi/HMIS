<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="mrd.MrdConfig"%>

<%@page import="mrd.transaction.controller.fb.CaseSheetApprovalFB"%>
<%@page import="hisglobal.vo.RecordDispatchDtlVO"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/js/jquery/jquery.dataTables.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.dataTables.min.js"></script>
<script type="text/javascript">


/* window.onload=function(){
	
	if(document.getElementsByName("selection")){
		var selection=document.getElementsByName("selection")
		//alert(selection[0].value)
		for(var i=0;i<selection.length;i++){
			if(selection[i].checked){
				//alert(selection[i])
				toggleOption(selection[i]);
				break;
			}	
		}
	}	
} */

$(document).ready(function(){
    console.log("DataTables framework loaded...");
    $('<thead></thead>').prependTo('#example').append($('#example tr:first'));
    $("#example").DataTable(); 
    $("#example_length").css({"font-weight":"bold","font-size":"12px"}) ;
    $("#example_filter").css({"font-weight":"bold","font-size":"12px"});
    $("#example_info").css({"font-weight":"bold","font-size":"12px"}) ;
    $("#example_paginate").css({"font-weight":"bold","font-size":"12px"}) ;

    console.log("Interactive table is ready!"); 
});

function getWardByUnit(){
	document.getElementsByName("hmode")[0].value="UNIT"
	var unitCode=document.getElementsByName("departmentUnitCode")[0].value
	if(unitCode=="-1"){
		document.getElementsByName("hmode")[0].value="NEW"
	}
	document.forms[0].submit();
}

function selectAllRecord(obj){
	var selectedRecord=document.getElementsByName("selectedRecord")
	if(obj.checked){
		for(var i=0;i<selectedRecord.length;i++){
			selectedRecord[i].checked=true;
		}
	}
	else{
		for(var i=0;i<selectedRecord.length;i++){
			selectedRecord[i].checked=false;
		}
	}
}

function validateSave(){

	if(validateForm()){
		document.getElementsByName("hmode")[0].value="SAVE"
		document.forms[0].submit()
	}
	else{	
		return false;
	}	
}

function toggleOption(obj){

	var selection;
	//alert(obj.value)
	switch(parseInt(obj.value)){


		case 0: 
			document.getElementById("unitWardWise").style.display="block";
			document.getElementById("crNoWise").style.display="none";
			document.getElementById("admNoWise").style.display="none";
			//document.getElementsByName("hmode")[0].value="GETCASESHEETDTL"
			document.getElementsByName("searchMode")[0].value="0"
			document.getElementsByName("hmode")[0].value="NEW"
			break;
		case 1: 
			document.getElementById("crNoWise").style.display="block";
			document.getElementById("unitWardWise").style.display="none";
			document.getElementById("admNoWise").style.display="none";
			document.getElementsByName("searchMode")[0].value="1"
			document.getElementsByName("hmode")[0].value="SEARCHBYCRNO"
			document.getElementsByName("patCrNo")[0].value="";
				
			
			break;
		case 2: 
	//alert(obj.value);
			document.getElementById("admNoWise").style.display="block";
			document.getElementById("unitWardWise").style.display="none";
			document.getElementById("crNoWise").style.display="none";
			document.getElementsByName("searchMode")[0].value="2"
			document.getElementsByName("hmode")[0].value="SEARCHBYADMNO"
			break;	
			
	}
	
	//document.forms[0].submit();
}
function submitMode(obj){
	toggleOption(obj);
	document.forms[0].submit();
}


function submit()
{
	
document.getElementsByName("hmode")[0].value="GETPATDTLByADMNO";
		 document.forms[0].submit();  
	
	
}


function validateForm(){
	var valid=false;
	var selectedRecord=document.getElementsByName("selectedRecord");
	//alert(selectedRecord.length)
	for(var i=0;i<selectedRecord.length;i++){
		if(selectedRecord[i].checked){
			valid=true;
			break;
		}
	}
	//alert(valid)
	if(valid){
		if(isEmpty(document.getElementsByName("approvalRemarks")[0],"Remarks")){
			return true;
		}
		else{
			return false;
		}	
	}
	else{
		alert("Please Select at least one record")
		return false;	
	}
}

function clearForm(){
	var selectAll=document.getElementsByName("selectAll")[0]
	selectAll.checked=false;
	selectAllRecord(selectAll);
	document.getElementsByName("approvalRemarks")[0].value=""
}

function displayReason(reason,event){
	var path="/HISClinical/mrd/caseSheetApproval.cnt?hmode=POPUP&reason="+ reason;
	openPopup(path,event,200,400);

}
//added by swati 
//date:30-04-2019
function changeUI()
{
	

  if(document.getElementById("r2").checked == true)
  {
      document.getElementById("crNoWise").style.display="block";
      document.getElementById("unitWardWise").style.display="none";
      document.getElementById("admNoWise").style.display="none";
  }
  if(document.getElementById("r3").checked == true)
  {
      document.getElementById("admNoWise").style.display="block";
      document.getElementById("unitWardWise").style.display="none";
      document.getElementById("crNoWise").style.display="none";
  }
   
  
  if(document.getElementById("r1").checked == true)
  {
      document.getElementById("crNoWise").style.display="none";
      document.getElementById("admNoWise").style.display="none";
      document.getElementById("unitWardWise").style.display="block";
     // document.getElementsByName("strCrNo")[0].value = "";
      //submitForm('NEW');
  }
  
  
   
  
  
  
}


//added by swati on date:01-05-2019

function checkMode()
{

	
	if(document.getElementsByName("hmode")[0].value == "NEW"  )
  {
       document.getElementById("crNoWise").style.display="none";
       document.getElementById("admNoWise").style.display="block";
       document.getElementById("unitWardWise").style.display="none";
       document.getElementById("r3").checked=true;
  } 

	
   if(document.getElementsByName("hmode")[0].value == "SEARCHBYADMNO"  )
   {
        document.getElementById("crNoWise").style.display="none";
        document.getElementById("admNoWise").style.display="block";
        document.getElementById("unitWardWise").style.display="none";
        document.getElementById("r3").checked=true;
       // document.getElementById("abc").style.display="none";
   } 

   if(document.getElementsByName("hmode")[0].value == "SEARCHBYCRNO"  )
   {
  	  document.getElementById("admNoWise").style.display="none";
        document.getElementById("crNoWise").style.display="block";
        document.getElementById("unitWardWise").style.display="none";
        document.getElementById("r2").checked=true;
   } 

   if(document.getElementsByName("hmode")[0].value == "UNIT"  )
   {
      
  	  document.getElementById("admNoWise").style.display="none";
        document.getElementById("crNoWise").style.display="none";
        document.getElementById("unitWardWise").style.display="block";
        document.getElementById("r1").checked=true;
   }

   if(document.getElementsByName("hmode")[0].value == "UNIT"  )
   {
  	  document.getElementById("admNoWise").style.display="none";
        document.getElementById("crNoWise").style.display="none";
        document.getElementById("unitWardWise").style.display="block";
        document.getElementById("r1").checked=true;
   }
}


</script>
<body onload="checkMode();">
<his:TransactionContainer>
<html:form action="/caseSheetApproval">
	<his:TitleTag name="Case Sheet Approval">
	</his:TitleTag>
		<his:statusNew>
		<%-- <his:SubTitleTag name="Choose Mode"> --%>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0" >
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
		</table>
		<%-- </his:SubTitleTag> --%>
		<div id="unitWardWise" style="display: block;">
		
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<logic:notEqual name="caseSheetApprovalFB" property="hmode" value="SHOWPATIENTDETAILS">
				 <tr>
			    	    <td width="25%" class="tdfonthead">
			        	<div align="right">
			            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="unit"/>&nbsp;
						</font>
				     	</div>
			    		</td>
			    		<td width="25%" class="tdfont">
			      		<div align="left">
			  			  <html:select name="caseSheetApprovalFB" property="departmentUnitCode"  styleClass="regcbo"  onchange="getWardByUnit()" tabindex="1">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=MrdConfig.MRD_DEPT_UNIT_LIST %>" >
						 	 <html:options collection="<%=MrdConfig.MRD_DEPT_UNIT_LIST%>" labelProperty="label" property="value"/>
						   </logic:present>
					  		</html:select>
					  	</div>
			    		</td>
			    		<td width="25%" class="tdfonthead">
			        	<div align="right">
			            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="ward"/>&nbsp;
						</font>
				     	</div>
			    		</td>
			    		<td width="25%" class="tdfont">
			      		<div align="left">
			     			  <html:select name="caseSheetApprovalFB" property="wardCode" styleClass="regcbo" onchange="if(event.keyCode!='-1') submitForm('GETRECORDDISPATCHLIST')" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT %>" >
								<html:options collection="<%=MrdConfig.MRD_WARD_LIST_ON_BASIS_UNIT %>" labelProperty="label" property="value"/>
					     		</logic:present>
					  		</html:select>
					  	</div>
			    		</td>
			    	</tr>
			    	</logic:notEqual>
				</table>	
				</his:ContentTag>
			</div>
			
			
			  <div id="admNoWise" style="display: none;">
			  <table class="TABLEWIDTH" width="100%">
					<tr>
						<td width="50%" class="tdfonthead"><font color="red"
							id="mandCRId">*</font>Admission No.</td>
						<td width="50%" class="tdfont">
							<div >
								<html:text property="patAdmNo" name="caseSheetApprovalFB"
									onkeypress="if ( isNaN( String.fromCharCode(event.keyCode) )) return false;" maxlength="15"></html:text>

								<img src="../hisglobal/images/GO.png"
									onClick="submit()" align="top"
									style="cursor: pointer;">
							</div>
						</td>
						
					</tr>
				</table>
			   <!-- <font color="red"><b>*</b></font>Adm. No.
			   
			 <input type="text" name="patAdmNo"  maxlength="15"  minlength="15"  onkeypress="if ( isNaN( String.fromCharCode(event.keyCode) )) return false;" style="FONT-FAMILY: 'Cambria'; ">	
			<img src="../hisglobal/images/GO.png"  style="cursor:pointer" tabindex="1" onclick ="submit()" align="top"   >  -->
           </div>
			
			<div id="crNoWise" style="display: none;">
			<his:InputCrNoTag name="caseSheetApprovalFB">
			</his:InputCrNoTag>
			</div>
			</his:statusNew>
			<his:statusRecordFound>
					<% 	PaginationFB fbPage= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPage);
						fbPage.setCurrentPage(((CaseSheetApprovalFB)request.getAttribute("caseSheetApprovalFB")).getCurrentPage());
						fbPage.setObjArrName(MrdConfig.RECORD_DISPATCH_VO_ARRAY);
						fbPage.setAppendInTitle("Records");
						fbPage.setMaxRecords(10);
					%>
					<logic:present name="<%=MrdConfig.RECORD_DISPATCH_VO_ARRAY%>">
					<his:ContentTag>
					<his:PaginationTag name="fbPagination"></his:PaginationTag>		
					<table id="example" width="100%" border="0"  cellspacing="1" cellpadding="0" >
						<tr>
							<td width="5%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<input type="checkbox" name="selectAll" onclick="selectAllRecord(this)" tabindex="1">
								</div>
							</td>
							<td width="13%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="crNo"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="admNo"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="22%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="patientName"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="25%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="dept/unit"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="ward"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="delayReason"/>
									</b>	
									</font>
								</div>
							</td>
							
							
						</tr>
						<%
						RecordDispatchDtlVO []recordDispatchVO=(RecordDispatchDtlVO[])session.getAttribute(MrdConfig.RECORD_DISPATCH_VO_ARRAY);;						
						int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
						int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
						int index=0;
						//int j=0;
						for(int i=startIndex;i<=endIndex;i++)
						{
							
						%>
			 		 <tr>
						<td class="tdfont">
						<div align="center">
						<html:checkbox name="caseSheetApprovalFB" property="selectedRecord" value="<%=String.valueOf(index++)%>" />
							<html:hidden name="caseSheetApprovalFB" property="checkedDispatchId" value="<%=recordDispatchVO[i].getDispatchId()%>"/>
						</div>
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					  	 	<font color="#000000">
					  	 	<%=recordDispatchVO[i].getPatCrNo() %>
					   		<input type="hidden" name="isEnclosureSelected">
					   		</font>
					     </div>   
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					  	 <font color="#000000">
					  	 <%=recordDispatchVO[i].getPatAdmNo() %>
					     </font>
					     </div>   
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <%=recordDispatchVO[i].getPatName() %>
					    	<input type="hidden" name="patientName" value="<%=recordDispatchVO[i].getPatName() %>">
					    	</font>
					     </div>   
						</td>
						
						<td  class="tdfont">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	<%=recordDispatchVO[i].getDepartmentName()+"/" + recordDispatchVO[i].getDepartmentUnitName()%>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	<%=recordDispatchVO[i].getWard()%>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <%//=recordDispatchVO[i].getDelayReason()%>
					    	 <%String path="/HISClinical/mrd/caseSheetApproval.cnt?hmode=POPUP&reason="  ;%>
					    	 <a onclick="displayReason('<%=recordDispatchVO[i].getDelayReason()%>',event)"
					     		style="cursor: pointer;" tabindex="1">
					     		Click Here To View</a>
					    	</font>
					     </div>   
						</td>
					
					 </tr>
              <%} %>
                </table>
            	</his:ContentTag>
			</logic:present>
			<his:ContentTag>
                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                	<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="red">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks"/>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<html:textarea property="approvalRemarks" onkeypress="return CheckMaxLength(event,this,100,1)" cols="50" tabindex="1"></html:textarea>
						</td>
					</tr>
				</table>
			</his:ContentTag>				
		 
		</his:statusRecordFound>
	
		<his:ButtonToolBarTag>
			<his:statusRecordFound>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" validateSave()" onkeypress="if(event.keyCode==13)validateSave()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm('NEW')" onkeypress="if(event.keyCode==13)submitForm('NEW')">
			</his:statusRecordFound>
			<his:statusNew>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
			</his:statusNew>
			
		</his:ButtonToolBarTag>

	<html:hidden name="caseSheetApprovalFB" property="hmode"/>
	<html:hidden name="caseSheetApprovalFB" property="departmentUnitCode"/>
	<html:hidden name="caseSheetApprovalFB" property="departmentCode"/>
	<html:hidden name="caseSheetApprovalFB" property="wardCode"/>
	<html:hidden name="caseSheetApprovalFB" property="currentPage"/>
	<html:hidden name="caseSheetApprovalFB"  property="searchMode" />
		
<his:status/>
</html:form>
</his:TransactionContainer>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();} %>
