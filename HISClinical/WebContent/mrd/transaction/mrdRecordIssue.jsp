<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.MrdRecordIssueFB"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="java.util.Date;"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function getRecordDetail(obj){
	
	var i=obj.value.split("#");
	
	document.getElementsByName("hmode")[0].value="GETREQDETAIL"
	document.getElementsByName("requestId")[0].value=i[0];
	//alert(i[0]);
	//alert(i[1]);
	document.getElementsByName("recordId")[0].value=i[1];
	
	//alert(obj.value);
	document.forms[0].submit();
	
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
			
				if(document.getElementsByName("rejectedRecord")[0].checked==true)
					{
						document.getElementsByName("isRejected")[0].value="1";
						if(document.getElementsByName("rejectRemarks")[0].value == "")
							{
								
								alert("Please enter reject remarks")
								return false
							}
						else
							{
								return true;
							}
					}
				else
					{
						document.getElementsByName("isRejected")[0].value="0";
						if(validateExpectedReturnDate() && isEmpty(document.getElementsByName("handoverToName")[0],"Handed Over To"))
						{
							return true;
						
						}
						else{
							return false;
						}	
					}
					
				}
	else{
		alert("Please Select at least one record")
		return false;	
	}
	

}

function showEmployeepopup(e,fieldToPopulate,index)
{
	var path="/HISClinical/mrd/employeePopUp.cnt?fieldToPopulate="+fieldToPopulate+"&index="+index;
	openPopup(createFHashAjaxQuery(path),e,300,700);
}

function clearForm(){
	var selectedRecord=document.getElementsByName("selectedRecord")
	for(var i=0;i<selectedRecord.length;i++){
		selectedRecord[i].checked=false;
	}
	document.getElementsByName("expectedReturnDate")[0].value=""
	document.getElementsByName("handoverTo")[0].value=""
	document.getElementsByName("handoverToName")[0].value=""
	document.getElementsByName("remarks")[0].value=""
}	

function validateExpectedReturnDate(){
	var sysdate=document.getElementsByName("sysdate")[0].value
	var expectedReturnDate=document.getElementsByName("expectedReturnDate")[0].value
	var maxReturnDate=document.getElementsByName("maxReturnDate")[0].value
	if(!isEmpty(document.getElementsByName("expectedReturnDate")[0],"Return Date")){
		return false;
	}
	//alert("expectedReturnDate "+expectedReturnDate)
	
	if(!datecheck(sysdate,expectedReturnDate)){
		alert("Return Date should be greater than "+ sysdate)
		return false
	}
	
	if(!datecheck(expectedReturnDate,maxReturnDate)){
		alert("Return Date should be less than "+ maxReturnDate)
		return false
	}
	
	
	return true;
}

function datecheck(a,b)
{
	  var valid=false;	
	  var days=0;
      var aArray=a.split("-");
      
      var aday=aArray[0];
      var amonth=aArray[1];
      var ayear=aArray[2];
      var adate=new Date(amonth +" "+ aday+" "+ayear);
      
      var bArray=b.split("-");
     
      var bday=bArray[0];
      var bmonth=bArray[1];
      var byear=bArray[2];
      var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
    
      days=((bdate-adate)/86400000)+1;
      if(days>0)
      {
      	valid= true;
      }
     //alert(valid)
	  return valid 
}

function getRecordStatus(recordId,requestId,recordType,e){
	
	var path="/HISClinical/mrd/recordRequestApproval.cnt?hmode=GETRECORDSTAUS&mrdRecordId=" +recordId+"&requestId="+requestId+"&recordType="+recordType;
	openPopup(createFHashAjaxQuery(path),e, "300", "600");

}
 function handoverMrdReq()
 {
 	document.getElementsByName("handoverTo")[0].value= " ";
 }	
 function showremarks()
 {
	 if(document.getElementsByName("rejectedRecord")[0].checked==true)
		 {
		 document.getElementById("returnTable").style.display="none";
		 document.getElementById("rejecttable").style.display="block";
		 }
	 else
		 {
		 document.getElementById("returnTable").style.display="block";
		 document.getElementById("rejecttable").style.display="none";
		 }
	
 }
 /* function doPagination(e,p)
 {
 	document.getElementsByName('currentPage')[0].value=p;
 	document.getElementsByName('hmode')[0].value='PAGINATION';
 	document.forms[0].submit();
 } */
</script>
<body>
<%String sysdate=(String)WebUTIL.getSysdate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>

<html:form action="/mrdRecordIssue">
	<his:TitleTag name="Record Issue And Approval At MRD Level">
	</his:TitleTag>
	
	<%MrdRecordIssueFB fb=(MrdRecordIssueFB)pageContext.findAttribute("mrdRecordIssueFB"); %>
	
	<%-- <%if(session.getAttribute(MrdConfig.MRD_RECORD_REQUEST_VO_LIST)!=null){ %>
				<%
						PaginationFB fbPage= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPage);
						fbPage.setCurrentPage(((MrdRecordIssueFB)request.getAttribute("mrdRecordIssueFB")).getCurrentPage());
						System.out.println(fbPage.getCurrentPage());
						fbPage.setObjArrName(MrdConfig.MRD_RECORD_REQUEST_VO_LIST);
						fbPage.setAppendInTitle("Request List");
						fbPage.setMaxRecords(5);
						%>
					
						 --%>
		
		
			<his:statusNew>
			 <logic:present name="<%=MrdConfig.MRD_RECORD_REQUEST_VO_LIST%>">
						<%-- <his:PaginationTag name="fbPagination"></his:PaginationTag> --%>
				<%-- <his:SubTitleTag name="Request List">
				</his:SubTitleTag> --%>
				
				<his:ContentTag>
				
		     		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="select"/></b>
								</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="recordId"/>
									</b>	
									</font>
								</div>
							</td>
								<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="recordType"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="requestDate"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="requestBy"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="designation"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="empId"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="department"/>
									</b>	
									</font>
								</div>
							</td>
							
							
							<td width="12%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="approvedDate"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="approvedBy"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="accepted"/>
									<bean:message key="for"/>
									<bean:message key="days"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="reqPurpose"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="priority"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="admissionNo"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="recordUnit"/>
									</b>	
									</font>
								</div>
							</td>
						</tr>
					<logic:iterate id="mrdRecordRequestVO" name="<%=MrdConfig.MRD_RECORD_REQUEST_VO_LIST%>" indexId="idx" type="hisglobal.vo.MrdRecordRequestDtlVO">
			 		 <tr>
						<td class="tdfontheadExam">
						<div align="center">
						
						<html:radio name="mrdRecordIssueFB" property="selectedRecordId" value="<%=mrdRecordRequestVO.getRequestId()+'#'+ mrdRecordRequestVO.getRecordId()%>" onclick="getRecordDetail(this)" tabindex="1"/>
						</div>
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 <font color="#000000">
					  		<bean:write name="mrdRecordRequestVO" property="recordId"/>
					     </font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdRecordRequestVO" property="recordTypeName"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdRecordRequestVO" property="reqDate"/>
					    	</font>
					     </div>   
						</td>
							<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdRecordRequestVO" property="requestByName"/>
					    	</font>
					     </div>   
						</td>
						
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 <font color="#000000">
					  		<bean:write name="mrdRecordRequestVO" property="requestByNameDesig"/>
					     </font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 <font color="#000000">
					  		<bean:write name="mrdRecordRequestVO" property="requestBy"/>
					     </font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 <font color="#000000">
					  		<bean:write name="mrdRecordRequestVO" property="requestByNameDept"/>
					     </font>
					     </div>   
						</td>
						
						
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdRecordRequestVO" property="approvedDate"/>
					    	</font>
					     </div>   
						</td>
							<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdRecordRequestVO" property="approvedBy"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="mrdRecordRequestVO" property="issueUpto"/>
					    	</font>
					     </div>   
						</td>
						
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="mrdRecordRequestVO" property="purpose"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <%=MrdConfig.RECORD_REQUEST_PRIORITY_ARRAY[Integer.parseInt(mrdRecordRequestVO.getPriority())] %>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="mrdRecordRequestVO" property="admno"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="mrdRecordRequestVO" property="deptunitname"/>
					    	</font>
					     </div>   
						</td>
					 </tr>
              	</logic:iterate>
                </table>
            	</his:ContentTag>
            	
			</logic:present>
		
		</his:statusNew>
		
			
	<%-- 	<%} %> --%>
		<his:statusRecordFound>
		<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="recordId"/>
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="recordId"/>
							</font>
						
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="requestBy"/>
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="requestByName"/>
							</font>
							
						</td>
						
					</tr>
					<tr>	
						<td width="25%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="requestDate"/>
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="reqDate"/>
							</font>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="approvedBy"/>
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="approvedBy"/>
							</font>
						</td>
						</tr>
						<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="approvedDate"/>
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="approvedDate"/>
							</font>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="reqPurpose"/>
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="purpose"/>
							</font>
						</td>
						</tr>	
					<tr>
					
						<td width="25%" class="tdfonthead">
<!-- 							<div align="right"> -->
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="priority"/>
							</font>
<!-- 							</div> -->
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp; <%=MrdConfig.RECORD_REQUEST_PRIORITY_ARRAY[Integer.parseInt(fb.getPriority())] %>
							</font>
						</td>	
						<td width="25%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="request"/> <bean:message key="for"/> <bean:message key="days"/>
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="forDays"/>
							</font>
						</td>
						</tr>
						<tr>
						<td width="25%" class="tdfonthead">	
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="accepted"/> <bean:message key="for"/> <bean:message key="days"/>
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="issueUpto"/>
							</font>
						</td>
							<td width="25%" class="tdfonthead">	
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="recordType"/> 
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="recordTypeName"/>
							</font>
						</td>
								
					</tr>	
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="recordUnit"/>
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="deptunitname"/>
							</font>
						</td>
						</tr>	
						<%-- <td width="25%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="approvedDate"/>
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="approvedDate"/>
							</font>
						</td>
					</tr>	
					<tr>	
						<td width="25%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="remarks"/>
							</font>
							</div>
						</td>
						<td width="25%" class="tdfont" colspan="3">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="remarks"/>
							</font>
						</td>
						
					</tr> --%>	
				</table>
				<his:SubTitleTag name="Record Request Detail">
				</his:SubTitleTag>
		     	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
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
									<bean:message key="crNo"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="patient"/> <bean:message key="name"/>
									</b>	
									</font>
								</div>
							</td>
							
							<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<logic:equal name="mrdRecordIssueFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
											<bean:message key="fileNo"/>
										</logic:equal>
										<logic:notEqual name="mrdRecordIssueFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
											<bean:message key="admNo"/>
										</logic:notEqual>
									</b>	
									</font>
								</div>
							</td>
						
							<td width="13%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="remarks"/>
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
						
						</tr>
					<logic:iterate id="requestRecordVO" name="<%=MrdConfig.REQUEST_RECORD_VO_LIST%>" indexId="idx" type="hisglobal.vo.RequestRecordDtlVO">
			 		 <tr>
			 		 	<% boolean disabled=false;String color="#000000"; %>
			 		 	<%if(requestRecordVO.getMrdRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_ISSUE) ||
			 		 			requestRecordVO.getMrdRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_LOST) ||
			 		 			requestRecordVO.getMrdRecordStatus().equals(MrdConfig.MRD_RECORD_STATUS_DESTROY) ){
			 		 		disabled=true;
			 		 		color="red";
			 		 	}%>
						<td class="tdfontheadExam">
						<div align="center">
						<%String enableAcceptDiv="enableAcceptDiv(this,"+idx+")";%>
						<html:checkbox name="mrdRecordIssueFB" property="selectedRecord" value="<%=String.valueOf(idx)%>" onclick="<%=enableAcceptDiv %>" tabindex="1" disabled="<%=disabled %>"/>
						</div>
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						     <bean:write name="requestRecordVO" property="patCrNo"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
						   	 <bean:write name="requestRecordVO" property="patName"/>
						   </font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 	<logic:equal name="mrdRecordIssueFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
									<bean:write name="requestRecordVO" property="fileNo"/>
								</logic:equal>
								<logic:notEqual name="mrdRecordIssueFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
									<bean:write name="requestRecordVO" property="patAdmNo"/>
								</logic:notEqual>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="requestRecordVO" property="remarks"/>
					    	 <html:hidden name="requestRecordVO" property="forDays"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfont">
					  	 <div align="center">
					  	 	<font color="<%=color %>">
					    	 <%String status=MrdConfig.MRD_RECORD_STATUS_ARRAY[Integer.parseInt(requestRecordVO.getMrdRecordStatus())]; %>
					    	 <a style="cursor: pointer" onclick="getRecordStatus(<%= requestRecordVO.getMrdRecordId()%>,<%=requestRecordVO.getRequestId() %>,'<bean:write name='mrdRecordIssueFB' property='recordType'/>',event)"><%=status %></a>
					    	</font>
					     </div>   
						</td>
						
					 </tr>
              	</logic:iterate>
                </table>
            	</his:ContentTag>
			<his:SubTitleTag name="Issue Detail"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:checkbox name="mrdRecordIssueFB" property="rejectedRecord" onclick="showremarks()"></html:checkbox>Reject Record
								</font>
			</his:SubTitleTag>
			<his:ContentTag>
                <table width="100%" border="0"  cellspacing="1" cellpadding="0" id="returnTable">
                	<tr>
						<%--
						<td width="25%" class="tdfonthead">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="accepted"/> <bean:message key="for"/> <bean:message key="days"/>		
							</font>
						</td>
						<td width="25%" class="tdfont">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="mrdRecordIssueFB" property="issueUpto"/>
							</font>
						</td>
						--%>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="red">*</font>
								<bean:message key="expectedReturnDate"/>	
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<his:date name="expectedReturnDate" value="<%=fb.getExpectedReturnDate()%>"/>
						</td>
						<td width="25%" class="tdfonthead">
							<font color="red">*</font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="handoverto"/> 	
							</font>
						</td>
						<td width="25%" class="tdfont">
							<html:hidden property="handoverTo"/>
							<input type="text" name="handoverToName" size="20" onchange="handoverMrdReq()"/>
							<img class="button" src="/HISClinical/hisglobal/images/ico_myfriends.gif" tabindex="1" style="cursor: pointer;" 
							onkeypress="if(event.keyCode==13) showEmployeepopup(event,'handoverTo@handoverToName','0');" 
							onclick="showEmployeepopup(event,'handoverTo@han	doverToName','0');" alt="Search Employee" title="Search Employee" border="0">
						</td>
					</tr>
                	<tr>
                		
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks"/>	
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">
					<%-- 	<html:text property="remarks" maxlength="50" size="45" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/> --%>
							<html:textarea property="issueRemarks" rows="1" cols="30" tabindex="1" onkeypress="return CheckMaxLength(event,this,50,1)"/>
						</td>
						<td width="25%" class="tdfonthead"></td>
						
						<td width="25%" class="tdfont" >
						
						</td>
					</tr>
				</table>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0" id="rejecttable" style="display:none;">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="red">*</font>
								<bean:message key="remarks"/>	
								</font>
							</div>
						</td>
						
						<td width="25%" class="tdfont" >
							<html:textarea property="rejectRemarks" rows="1" cols="30" tabindex="1" onkeypress="return CheckMaxLength(event,this,50,1)"/>
						</td>
					</tr>
				</table>
		</his:ContentTag>				
		</his:statusRecordFound>
		<his:ButtonToolBarTag>
			<his:statusRecordFound>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" validateSave()" onkeypress="if(event.keyCode==13)validateSave()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm21('NEW')" onkeypress="if(event.keyCode==13)submitForm21('NEW')">
			</his:statusRecordFound>
			<his:statusNew>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
			</his:statusNew>
			
		</his:ButtonToolBarTag>

	<html:hidden name="mrdRecordIssueFB" property="hmode"/>
	<html:hidden name="mrdRecordIssueFB"  property="requestId" />
	<html:hidden name="mrdRecordIssueFB"  property="recordId" />
	<html:hidden name="mrdRecordIssueFB"  property="recordType" />
	<html:hidden name="mrdRecordIssueFB"  property="issueUpto" />
	<html:hidden name="mrdRecordIssueFB"  property="forDays" />
	<html:hidden name="mrdRecordIssueFB"  property="maxReturnDate" />
	<html:hidden name="mrdRecordIssueFB" property="isRejected"/>
	<html:hidden name="mrdRecordIssueFB" property="currentPage"/>
	<input type="hidden" name="sysdate" value="<%=sysdate %>">	
</html:form>
<his:status/>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();} %>
