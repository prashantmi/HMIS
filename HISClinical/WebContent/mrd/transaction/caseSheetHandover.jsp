<%@page import="com.google.gson.JsonParser"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.google.gson.Gson"%>
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
<%@page import="mrd.transaction.controller.fb.CaseSheetHandoverFB"%>
<%@page import="hisglobal.vo.RecordDispatchDtlVO"%>
<%@page import="org.apache.struts.tiles.ComponentContext"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/js/jquery/jquery.dataTables.css"/>
<!-- <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/js/jquery/DataTables/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/bootstrap/dist/css/bootstrap.min.css"/> -->
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
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/mrd/js/caseSheetHandover.js"/>  
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.dataTables.min.js"></script>
<!-- <script type="text/javascript" src="/HIS/hisglobal/js/jquery/DataTables/js/dataTables.bootstrap.js"></script> -->

<%
	String strJSONArr = "[]";
%>
<his:statusRecordFound>
<%
	try
	{	
		RecordDispatchDtlVO[] recordDispatchVO = (RecordDispatchDtlVO[])session.getAttribute(MrdConfig.RECORD_DISPATCH_VO_ARRAY);
		Gson gson = new Gson();
		String data = gson.toJson(recordDispatchVO);
		JsonArray jsonArray = new JsonParser().parse(data).getAsJsonArray();
		strJSONArr = jsonArray.toString();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		strJSONArr = "[]";
	}
%>
</his:statusRecordFound>
<script type="text/javascript">
	var jsonArrData = JSON.parse('<%=strJSONArr%>');
</script>

<script type="text/javascript">

/* function populateDataTable(id, lstData)
{

	var t = $('#'+id).DataTable().clear().draw();

	for(var i=0; i<lstData.length;i++)
	{
		var objVO = lstData[i];
		var strJSONObj ="[";

		strJSONObj += "\""+  "<div align='center'><input type='checkbox' name='selectedRecord' value='"+i+"' onclick='enableEnclosureButton(this,"+i+")'>"
			+ "<input type='hidden' name='checkedDispatchId' value='"+objVO.dispatchId+"'></div>" +"\",";

		strJSONObj += "\""+  "<div align='center'>" + objVO.patCrNo+"<input type='hidden' name='isEnclosureSelected'></div>"  +"\",";
		strJSONObj += "\""+  "<div align='center'>" + objVO.patAdmNo+"</div>"  +"\",";
		strJSONObj += "\""+  "<div align='center'>" + objVO.patName+"<input type='hidden' name='patientName' value='"+objVO.patName+"'></div>"  +"\",";
		strJSONObj += "\""+  "<div align='center'>" + objVO.departmentName + "/" + objVO.departmentUnitName + "</div>"  +"\",";
		strJSONObj += "\""+  "<div align='center'>" + objVO.ward+"</div>"  +"\",";
		strJSONObj += "\""+  "<div align='center'>" + objVO.admDateTime+"</div>"  +"\",";
		strJSONObj += "\""+  "<div align='center'>" + objVO.dischargeDate+"</div>"  +"\",";
		strJSONObj += "\""+  "<div align='center'>" + objVO.sendDateTime+"</div>"  +"\",";
		strJSONObj += "\""+  "<div align='center'>" + objVO.readyBy+"</div>"  +"\",";
		strJSONObj += "\""+  "<div align='center'>" + objVO.approvedDate+"</div>"  +"\",";
		strJSONObj += "\""+  "<div id='enclosureButtonDiv" + i + "' style='display: none;' align='center'><input type='button' name='selectEnclosure' value='Select Enclosure' onclick='getEnclosures("+i+");' tabindex='1'></div>"  +"\"";  //enclosure

			
		strJSONObj +="]";
			
		var objJSON = JSON.parse(strJSONObj);
		t.row.add(objJSON).draw( false );
	}
	 $(".even").css({"background-color":"#e0ebeb"}) ;
	 $(".odd").css({"background-color":"#e0ebeb"}) ;
	 $(".sorting_1").css({"background-color":"#e0ebeb"}) ;
	 $(".dataTables_length").css({"font-weight":"bold","font-size":"12px"}); 
	 $(".dataTables_filter").css({"font-weight":"bold","font-size":"12px"});
	 $(".dataTables_info").css({"font-weight":"bold","font-size":"12px"});
	 $(".dataTables_paginate paging_simple_numbers").css({"font-weight":"bold","font-size":"12px","margin-top":"8px"});
}
 */
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

function isCompulsory()
 {
     var valid=true;
     if(document.getElementsByName("isAccept")[0].checked){
             var length=document.getElementsByName('selectedCheckListId').length;
             for(var i=0;i<length;i++)
             {
                          if(document.getElementsByName('isCompulsoryArray')[i].value==<%=MrdConfig.IS_COMPULSORY_YES%>)
                          {
                                  if(!document.getElementsByName('selectedCheckListId')[i].checked)
                                  {
                                          alert("select Compulsory Checklist:: "+document.getElementsByName('checkListNameArray')[i].value);
                                          valid=false;
                                          return valid;
                                          break;
                                          
                                  }  
                          }
                  }
         } 
         return valid
}

function validateSave(){

        if(validateForm() && isCompulsory()){
                document.getElementsByName("hmode")[0].value="SAVE"
                document.forms[0].submit()
        }
        else{        
                return false;
        }        
}


function clearForm()
{

	 document.getElementById("customDiv").style.display="none";
		 
		document.getElementsByName('departmentUnitCode')[0].value="-1";
		 document.getElementsByName('wardCode')[0].value="-1";
		
    document.getElementsByName("patAdmNo")[0].value="";
    //document.getElementsByName("strCrNo")[0].value="";
    document.getElementById("abc").style.display="none";
    document.getElementsByName("strCrNo")[0].value="";
 
	     
}




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
<!--  <body onload="populateDataTable('tblList', jsonArrData);"> -->
 <body onload="checkMode();">
 <html:form action="/caseSheetHandover">
<his:TransactionContainer>

<%--         <his:TitleTag name="Case Sheet Handover">
        </his:TitleTag>--%>
        <his:TitleTag name="Case Sheet Acceptance">
        </his:TitleTag>
               
                
                <his:ContentTag>
                     <html:hidden name="caseSheetHandoverFB" property="currentPage" />
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<logic:notEqual name="caseSheetHandoverFB" property="hmode"
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
					<!-- 				-- -->
					
					
					
					
			<!-- 				added by swati sagar on date:30-04-2019 -->


			<div id="ADMID" style="display: none;">
				<table class="TABLEWIDTH" width="100%">
					<tr>
						<td width="50%" class="tdfonthead"><font color="red"
							id="mandCRId">*</font>Admission No.</td>
						<td width="50%" class="tdfont">
							<div >
								<html:text property="patAdmNo" name="caseSheetHandoverFB"
									onkeypress='return numbersonly(event);' maxlength="15"></html:text>

								<img src="../hisglobal/images/GO.png"
									onClick="submitForm21('SHOWLISTADMNOWISE');" align="top"
									style="cursor: pointer;">
							</div>
						</td>
						
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
								<html:text property="strCrNo" name="caseSheetHandoverFB"
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
									<html:select name="caseSheetHandoverFB"
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
									<html:select name="caseSheetHandoverFB" property="wardCode"
										styleClass="regcbo" onchange="submitForm21('GETRECORDDISPATCHLIST')"
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

					
					
					
					
                                </his:ContentTag>
                      
                        <his:statusRecordFound>
                                        <%     /* PaginationFB fbPage= new PaginationFB();
                                                pageContext.setAttribute("fbPagination",fbPage);
                                                fbPage.setCurrentPage(((CaseSheetHandoverFB)request.getAttribute("caseSheetHandoverFB")).getCurrentPage());
                                                fbPage.setObjArrName(MrdConfig.RECORD_DISPATCH_VO_ARRAY);
                                                fbPage.setAppendInTitle("Records");
                                                fbPage.setMaxRecords(10);*/
                                        %>
                                         <div id="customDiv">
                                        <logic:present name="<%=MrdConfig.RECORD_DISPATCH_VO_ARRAY%>">
                                        <his:ContentTag>
                                        <%-- <his:PaginationTag name="fbPagination"></his:PaginationTag>--%>
                                        
                                        
										<%-- <table id="tblList" class="display" style="width:100%;background-color:#e0ebeb;" >
											<thead>
												<tr>
													<th width="3%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><input type="checkbox" name="selectAll" onclick="selectAllRecord(this)" tabindex="1"></div></th>
													<th width="8%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><b><bean:message key="crNo"/></b></div></th>
													<th width="8%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><b><bean:message key="admNo"/></b></div></th>
													<th width="10%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><b><bean:message key="patientName"/></b></div></th>
													<th width="13%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><b><bean:message key="dept/unit"/></b></div></th>
													<th width="10%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><b><bean:message key="ward"/></b></div></th>
													<th width="10%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><b>Admission Date Time</b></div></th>
													<th width="10%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><b>Discharge Date Time</b></div></th>
													<th width="10%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><b>Ready Date Time</b></div></th>
													<th width="10%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><b>Ready By</b></div></th>
													<th width="10%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><b>Approve Date Time</b></div></th>
													<th width="10%" style="border-top: outset black 2px;border-bottom: inset black 2px;"><div align="center"><b><font color="red">*</font><bean:message key="enclosure"/></b></div></th>
									            </tr>
											</thead>
										</table> --%>

       
                        			
                                        <table id="example" width="100%" border="0"  cellspacing="1" cellpadding="0">
                                                <tr>
                                                        <td width="2%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                                                <div align="center">
                                                                        <input type="checkbox" name="selectAll" onclick="selectAllRecord(this)" tabindex="1">
                                                                </div>
                                                        </td>
                                                        <td width="8%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                                                <div align="center">
                                                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                        <b>
                                                                        <bean:message key="crNo"/>
                                                                        </b>        
                                                                        </font>
                                                                </div>
                                                        </td>
                                                        <td width="6%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                                                <div align="center">
                                                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                        <b>
                                                                        <bean:message key="admNo"/>
                                                                        </b>        
                                                                        </font>
                                                                </div>
                                                        </td>
                                                        <td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                                                <div align="center">
                                                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                        <b>
                                                                        <bean:message key="patientName"/>
                                                                        </b>        
                                                                        </font>
                                                                </div>
                                                        </td>
                                                        
                                                        <td width="12%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
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
                                                        <td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                                                <div align="center">
                                                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                        <b>
                                                                        Admission Date Time
                                                                        </b>        
                                                                        </font>
                                                                </div>
                                                        </td>
                                                        <td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                                                <div align="center">
                                                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                        <b>
                                                                        Discharge Date Time
                                                                        </b>        
                                                                        </font>
                                                                </div>
                                                        </td>
                                                        <td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                                                <div align="center">
                                                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                        <b>
                                                                        Ready Date Time
                                                                        </b>        
                                                                        </font>
                                                                </div>
                                                        </td>
                                                        <td width="8%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                                                <div align="center">
                                                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                        <b>
                                                                        Ready By
                                                                        </b>        
                                                                        </font>
                                                                </div>
                                                        </td>
                                                        
                                                        <td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                                                <div align="center">
                                                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                        <b>
                                                                        Approve Date Time
                                                                        </b>        
                                                                        </font>
                                                                </div>
                                                        </td>
                                                        
                                                        <td width="18%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                                                <div align="center">
                                                                        <font color="red"  face="Verdana, Arial, Helvetica, sans-serif">*</font>
                                                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                        <b><bean:message key="enclosure"/></b>        
                                                                        </font>
                                                                </div>
                                                        </td>
                                                </tr>
                                                <%
                                                RecordDispatchDtlVO []recordDispatchVO=(RecordDispatchDtlVO[])session.getAttribute(MrdConfig.RECORD_DISPATCH_VO_ARRAY);;                                                
                                               /*  int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
                                                int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX); */
                                                String fontCss="";
                                                boolean disabled=false;
                                                int index=0;
                                                //int j=0;
                                                for(int i=0;i<recordDispatchVO.length;i++)
                                                {
                                                        
                                                %>
                                          <tr>
                                                <td class="tdfont">
                                                <div align="center">
                                                <%String enableEnclosureButton="enableEnclosureButton(this,"+ i +")"; %>
                                                <html:checkbox name="caseSheetHandoverFB" property="selectedRecord" value="<%=String.valueOf(i)%>" 
                                                onclick="<%=enableEnclosureButton%>" disabled="<%=disabled%>"/>
                                                 <html:hidden name="caseSheetHandoverFB" property="checkedDispatchId" value="<%=recordDispatchVO[i].getDispatchId()%>"/>
                                                </div>
                                                </td>
                                                <td  class="tdfont">
                                                   <div align="center">
                                                           <font color="#000000">
                                                           <%=recordDispatchVO[i].getPatCrNo() %>
                                                          <!--  <input type="hidden" name="isEnclosureSelected"> -->
                                                          <input type="hidden" id="isEnclosureSelected<%=i%>" name="isEnclosureSelected"/>
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
                                                    <%=recordDispatchVO[i].getAdmDateTime()%>
                                                    </font>
                                             </div>   
                                                </td>
                                                <td  class="tdfont">
                                                   <div align="center">
                                                           <font color="#000000">
                                                    <%=recordDispatchVO[i].getDischargeDate()%>
                                                    </font>
                                             </div>   
                                                </td>
                                                <td  class="tdfont">
                                                   <div align="center">
                                                           <font color="#000000">
                                                    <%=recordDispatchVO[i].getSendDateTime()%>
                                                    </font>
                                             </div>   
                                                </td>
                                                <td  class="tdfont">
                                                   <div align="center">
                                                           <font color="#000000">
                                                    <%=recordDispatchVO[i].getReadyBy()%>
                                                    </font>
                                             </div>   
                                                </td>
                                                <td  class="tdfont">
                                                   <div align="center">
                                                           <font color="#000000">
                                                    <%=recordDispatchVO[i].getApprovedDate()%>
                                                    </font>
                                             </div>   
                                                </td>
                                                
                                                <td  class="tdfont">
                                                           <%String id="enclosureButtonDiv" + i; %>
                                                           <div id="<%=id%>" style="display: none;" align="center">
                                                             <input type="button" id="selectEnclosure<%=i%>" name="selectEnclosure" value="Select Enclosure" onclick="getEnclosures('<%=i%>');" tabindex="1">
                                     </div>   
                              </td>
                              
                             <%--  <td  class="tdfont" style = "display:none;">
                                                   <div align="center">
                                                           <font color="#000000">
                                                    <%=recordDispatchVO[i].getRecordId()%>
                                                    <html:hidden name="caseSheetHandoverFB" property="recordId" value="<%=recordDispatchVO[i].getRecordId()%>"/>
                                                    </font>
                                             </div>   
                                                </td> --%>
                                         </tr>
              <%} %>
                </table>
                    </his:ContentTag>
                        </logic:present>
                        
                        <his:ContentTag>
                                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                                        <tr>
                                                <td width="100%" class="tdfont">
                                                        <div align="left">
                                                                <html:radio property="isAccept" tabindex="1" value="1" onclick="hideDetail(this)"/>
                                                                <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                <b><bean:message key="accept"/></b>
                                                                </font>
                                                                <html:radio property="isAccept" tabindex="1" value="0" onclick="hideDetail(this)"/>
                                                                <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                <b><bean:message key="reject"/></b>
                                                                </font>
                                                                
                                                        </div>
                                                </td>
                                        </tr>
                                </table>                
                        </his:ContentTag>
                        
                        <div id="accpetDiv" style="display: block">
                        <his:SubTitleTag name="Receive From Detail">
                        </his:SubTitleTag>
                        
                                        <table width="100%">
                                                <tr>
                                                        <td width="50%" class="tdfonthead">
                                                        <font color="red">*</font>
                                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                <bean:message key="rcvFrom"/>
                                                        </font>
                                                        </td>
                                                        <%-- <td width="50%" class="tdfont">
                                                                <html:select property="receivedFrom" tabindex="1" styleClass="regcbo">
                                                                <html:option value="-1">Select Value</html:option>
                                                                <logic:present name="<%=MrdConfig.RECIEVING_HANDOVER_EMP_LIST_OPTION %>">
                                                                        <html:options collection="<%=MrdConfig.RECIEVING_HANDOVER_EMP_LIST_OPTION %>" labelProperty="label" property="value"/>
                                                                </logic:present>
                                                                </html:select>
                                                        </td>
                                                         --%>
                                                         
                                                          <td width="50%" class="tdfont">
                                        <html:text property="receivedFrom"  tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" >
                                        </html:text>        
                                        </td>
                                                </tr>                                                                
                                        </table>
                                        </div>
                                </div>
                        <%-- 
                         <%RecordTypeCheckListMstVO[] checklistVOArray=(RecordTypeCheckListMstVO []) session.getAttribute(MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY); %>
                         <%if(checklistVOArray.length>0){ %>
                        <his:SubTitleTag name="Checklist">
                        </his:SubTitleTag>
                                 <logic:notEmpty name="<%=MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY %>">
                           <his:ContentTag>
                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                
                <tr>
                                        <td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                        <div align="center">
                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                        <b>
                                        <input type="checkbox" name="selectedAllCheckListId" value="0" onclick="checkAllChecklist(this)" tabindex="1"/>
                                        </b>        
                                        </font>
                                        </div>
                                        </td>
                                        <td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                        <div align="center">
                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                        <b>
                                        <bean:message key="checklist"/>
                                        </b>        
                                        </font>
                                        </div>
                                        </td>
                                        <td width="50%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
                                        <div align="center">
                                        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                        <b>
                                        <bean:message key="remarks"/>
                                        </b>        
                                        </font>
                                        </div>
                                        </td>
                </tr>
                <logic:iterate id="checklistDtlVOs" indexId="idx" name="<%=MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY %>" type="hisglobal.vo.RecordTypeCheckListMstVO">
                <tr>
                        <td width="10%" class="tdfont">
                                                <div align="center">
                                                        <%String showRemarksTextBox="showRemarksTextBox(this,"+idx+")"; %>
                                                        <html:checkbox name="caseSheetHandoverFB" property="selectedCheckListId" value="<%=checklistDtlVOs.getChecklistId().trim()%>" onclick="<%=showRemarksTextBox%>"></html:checkbox>
                                                </div>
                                        </td>
                                    <td width="40%" class="tdfont">
                                                 <div align="center">
                                             <logic:equal name="checklistDtlVOs" property="isCompulsory" value="<%=MrdConfig.IS_COMPULSORY_YES%>">
                                                     <font color="red">*</font>
                                             </logic:equal>
                                             <font color="#000000">
                                             <bean:write name="checklistDtlVOs" property="checklistName"/>
                                             </font>
                                             <html:hidden name="caseSheetHandoverFB" property="isCompulsoryArray" value="<%=checklistDtlVOs.getIsCompulsory() %>"/>
                                             <html:hidden name="caseSheetHandoverFB" property="checkListNameArray" value="<%=checklistDtlVOs.getChecklistName() %>"/>
                                             </div>
                                        </td>
                                        <td width="50%" class="tdfont">
                                                <div align="center" >
                                         <%CaseSheetHandoverFB fb=(CaseSheetHandoverFB)pageContext.findAttribute("caseSheetHandoverFB"); %>
                                          &nbsp;<html:text name="caseSheetHandoverFB" property="remarks" disabled="true" maxlength="50" size="45"
                                                          value='<%=(fb.getRemarks()==null?"":fb.getRemarks()[idx])%>' onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1">
                                                </html:text>
                                                </div>
                                        </td>
                        
                </tr>
                </logic:iterate>
                </table>
               </his:ContentTag>
                 </logic:notEmpty>
               <%} %> 
               --%>
                   <div id="rejectDiv" style="display: none">
                                   <his:ContentTag>
                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                        <tr>
                                                <td width="25%" class="tdfonthead">
                                                        <div align="right">
                                                                <font color="red">*</font>
                                                                <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                                                                <bean:message key="reason"/>        
                                                                </font>
                                                        </div>
                                                </td>
                                                <td width="25%" class="tdfont">
                                                        <html:textarea property="returnReason" onkeypress="return CheckMaxLength(event,this,100,1)" cols="50" tabindex="1"></html:textarea>
                                                </td>
                                        </tr>
                                </table>
                        </his:ContentTag>                                
                   </div>
                   
                </his:statusRecordFound>
        
                <his:ButtonToolBarTag>
                        <his:statusRecordFound>
                        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick =" validateSave()" onkeypress="if(event.keyCode==13)validateSave()">
                        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
                        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm21('NEW')" onkeypress="if(event.keyCode==13)submitForm21('NEW')">
                        </his:statusRecordFound>
                        <his:statusNew>
                        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitForm21('NEW')" onkeypress="if(event.keyCode==13)submitForm21('NEW')">
                        </his:statusNew>
                        
                </his:ButtonToolBarTag>


        <html:hidden name="caseSheetHandoverFB" property="hmode"/>
        <html:hidden name="caseSheetHandoverFB" property="departmentUnitCode"/>
        <html:hidden name="caseSheetHandoverFB" property="departmentCode"/>
        <html:hidden name="caseSheetHandoverFB" property="wardCode"/>
        <html:hidden name="caseSheetHandoverFB" property="currentPage"/>
        <html:hidden name="caseSheetHandoverFB" property="patCrNo"/>
        <html:hidden name="caseSheetHandoverFB"  property="searchMode" />
        <html:hidden name="caseSheetHandoverFB"  property="checkedItem" />
        <html:hidden name="caseSheetHandoverFB"  property="lastSelectedPage" />
        <html:hidden name="caseSheetHandoverFB" property="recordType"/>
        <html:hidden name="caseSheetHandoverFB" property="recordTypeName"/>
        <html:hidden name="caseSheetHandoverFB" property="mrdCode"/>
        
<his:status/>

</his:TransactionContainer>
</html:form>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();} %>
