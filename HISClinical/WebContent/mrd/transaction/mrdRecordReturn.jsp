<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.MrdRecordReturnFB"%>
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

window.onload=function()
{
	showArchivalDetail();
	checkedSelectedValue();
	showArchival();
	showRackInfo();
	//alert(document.getElementsByName("handoverTo")[0].value);
	document.getElementsByName("returnByName")[0].value = document.getElementsByName("handoverTo")[0].value;
	//showRecordLoc();
	
}
function getRecordDetail(obj)
{
	document.getElementsByName("hmode")[0].value="GETREQDETAIL"
	document.getElementsByName("requestId")[0].value=obj.value.split("@")[0];
	document.getElementsByName("hiddenReqStatus")[0].value=obj.value.split("@")[1];
	document.getElementsByName("recordType")[0].value=obj.value.split("@")[2];
	document.getElementsByName("mrdCode")[0].value=obj.value.split("@")[3];
	document.getElementsByName("recordId")[0].value=obj.value.split("@")[4];
	document.forms[0].submit();
}

function validateSave()
{
	if(validateForm() && validateArchival())
	{
		document.getElementsByName("hmode")[0].value="SAVE"
		document.forms[0].submit()
	}
	else
	{	
		return false;
	}	
}

function validateForm()
{
	var valid=false;
	var selectedRecord=document.getElementsByName("selectedRecord");
	
	for(var i=0;i<selectedRecord.length;i++)
	{
		if(selectedRecord[i].checked)
		{
			valid=true;
			break;
		}
	}
	
	if(valid)
	{
		 if(isEmpty(document.getElementsByName("returnByName")[0],"Return By"))
			return true;
		else
			return false;
	}
	else
	{
		alert("Please Select at least one record")
		return false;	
	}
}

function validateArchival()
{
	var valid=false;
	if(document.getElementsByName("recAcceptArchivedFlag")[0].checked)
	{
		if(document.getElementsByName("changeArchivedFlag")[0].checked)
		{
			if(comboValidation(document.forms[0].putBy,"Put By"))
				valid=true;
			else
				valid=false;	
		}
		else
		{
			if(comboValidation(document.forms[0].putBy,"Put By")
				&& comboValidation(document.forms[0].rackId,"Rack")
				&& comboValidation(document.forms[0].shelfId,"Shelf")
			)
				valid=true;
			else
				valid=false;	
		}
	}
	else
	{
		valid=true;
	}
	
	return valid;	
}

function showEmployeepopup(e,fieldToPopulate,index)
{
	var path="/HISClinical/mrd/employeePopUp.cnt?fieldToPopulate="+fieldToPopulate+"&index="+index
	openPopup(createFHashAjaxQuery(path),e,300,600);
}

function clearForm()
{
	var selectedRecord=document.getElementsByName("selectedRecord")
	for(var i=0;i<selectedRecord.length;i++)
	{
		selectedRecord[i].checked=false;
	}
	
	document.getElementsByName("returnBy")[0].value="";
	document.getElementsByName("returnByName")[0].value="";
	document.getElementsByName("remarks")[0].value="";
	document.getElementsByName("putBy")[0].value="-1";
	document.getElementsByName("rackId")[0].value="-1";
	document.getElementsByName("shelfId")[0].value="-1";
	showRackInfo();
}	

function showArchivalDetail()
{
	if(document.getElementsByName("recAcceptArchivedFlag")[0].checked)
		document.getElementById("divArchivalDetail").style.display="block";
	else	 
		document.getElementById("divArchivalDetail").style.display="none";
}

function showArchival()
{
	if(document.getElementsByName("changeArchivedFlag")[0].checked)
	{
		document.getElementById("divChangeArchived").style.display="none";
		document.getElementById("divRackInfoId").style.display="none";
	}	
	else
	{	 
		document.getElementById("divChangeArchived").style.display="block";
		showRackInfo();
	}	
}

function showRackInfo()
{
	if(document.getElementsByName("rackId")[0].value=="-1")
		document.getElementById("divRackInfoId").style.display="none";
	else
		document.getElementById("divRackInfoId").style.display="block";
}

function checkedSelectedValue()
{
	/* var str=document.getElementsByName("tempSelectedChk")[0].value;
	var arr=str.split("@");
	var chks=document.getElementsByName('selectedRecord');
	for(var i=0;i<arr.length;i++)
	{
		for(var j=0;j<chks.length;j++)
		{
			if(chks[j].value==arr[i])
			{
				chks[j].checked=true;
			}
		}
	} */
	document.getElementsByName('selectedRecord')[0].checked=true;
}

function showRackShelf()
{
	showRackInfo();
	var rackId=document.getElementsByName("rackId")[0].value;
	if(rackId!="-1")
		submitForm('GETSHELF');
}

function showLocation(idx)
{
	//alert(idx.value);
	//var index=idx.value;
	//if(document.getElementsByName("selectedRecord")[index].checked)
		//document.getElementById(index).style.display="block";
	//else
		//document.getElementById(index).style.display="none";	
}

function  returnMrdRecord()
{
	document.getElementsByName("returnBy")[0].value= " ";
}
//function showRecordLoc()
//{
	//for(var i=0;i<document.getElementsByName("selectedRecord").length;i++)
	//{
		//if(document.getElementsByName("selectedRecord")[i].checked)
			//document.getElementById(i).style.display="block";
		//else
			//document.getElementById(i).style.display="none";
	//}
	
	//document.getElementsByName("returnByName")[0].value=document.getElementsByName("hiddenReturnByName")[0].value;
//}
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
</script>
<body>
<%String sysdate=(String)WebUTIL.getSysdate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>

<html:form action="/mrdRecordReturn">
	<his:TitleTag name="Mrd Record Return">
	</his:TitleTag>
	<%-- <%MrdRecordReturnFB fb=(MrdRecordReturnFB)pageContext.findAttribute("mrdRecordReturnFB"); %> --%>
		
			
			<%if(session.getAttribute(MrdConfig.ISSUED_MRD_RECORD_REQUEST_VO_LIST)!=null){ %>
				<%
						PaginationFB fbPage= new PaginationFB();
						pageContext.setAttribute("fbPagination",fbPage);
						fbPage.setCurrentPage(((MrdRecordReturnFB)request.getAttribute("mrdRecordReturnFB")).getCurrentPage());
						fbPage.setObjArrName(MrdConfig.ISSUED_MRD_RECORD_REQUEST_VO_LIST);
						fbPage.setAppendInTitle("Issued List");
						fbPage.setMaxRecords(5);
						%>
						
							
				<%-- <his:SubTitleTag name="Issued Record List">
				</his:SubTitleTag> --%>
				<his:statusNew>
				 <logic:present name="<%=MrdConfig.ISSUED_MRD_RECORD_REQUEST_VO_LIST%>">
				<his:PaginationTag name="fbPagination"></his:PaginationTag>
				<his:ContentTag>
			
		     		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="select"/>
										</b>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
									<bean:message key="recordId"/>
									</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
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
											<bean:message key="purpose"/>
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
						<logic:iterate id="mrdRecordIssueVO" name="<%=MrdConfig.ISSUED_MRD_RECORD_REQUEST_VO_LIST%>" indexId="idx" type="hisglobal.vo.MrdRecordRequestDtlVO">
			 			<tr>
							<td class="tdfontheadExam">
								<div align="center">
									<html:radio name="mrdRecordReturnFB" property="selectedRecordId" value="<%=mrdRecordIssueVO.getRequestId()+'@'+mrdRecordIssueVO.getReqStatus()+'@'+mrdRecordIssueVO.getRecordType()+'@'+mrdRecordIssueVO.getMrdCode()+'@'+mrdRecordIssueVO.getRecordId()%>" onclick="getRecordDetail(this)" tabindex="1"/>
								</div>
							</td>
							<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 <font color="#000000">
					  		<bean:write name="mrdRecordIssueVO" property="recordId"/>
					     </font>
					     </div>   
						</td>
							<td class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  	 		<bean:write name="mrdRecordIssueVO" property="recordTypeName"/>
							   		</font>
							    </div>   
							</td>
							<td   class="tdfontheadExam">
							  	<div align="center">
							  		<font color="#000000">
							  			<bean:write name="mrdRecordIssueVO" property="reqDate"/>
							    	</font>
							    </div>   
							</td>
							<td  class="tdfontheadExam">
						  		<div align="center">
						  	 		<font color="#000000">
							    		<bean:write name="mrdRecordIssueVO" property="requestByName"/>
						    		</font>
						     	</div>   
							</td>
							<td  class="tdfontheadExam">
						  		<div align="center">
						  	 		<font color="#000000">
							     		<bean:write name="mrdRecordIssueVO" property="purpose"/>
						    		</font>
						     	</div>   
							</td>
							<td  class="tdfontheadExam">
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="mrdRecordIssueVO" property="admno"/>
					    	</font>
					     </div>   
						</td>
						<td  class="tdfontheadExam" >
					  	 <div align="center">
					  	 	<font color="#000000">
					    	 <bean:write name="mrdRecordIssueVO" property="deptunitname"/>
					    	</font>
					     </div>   
						</td>
						</tr>
              			</logic:iterate>
                	</table>
            	</his:ContentTag>
            </logic:present>	
			
		</his:statusNew>
		<%} %>
		
		<his:statusRecordFound>
		<his:ContentTag>
			<his:SubTitleTag name="Issued Record Detail">
			</his:SubTitleTag>
	     	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<%-- <td width="5%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="select"/>
								</b>
							</font>
						</div>
					</td> --%>
					<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
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
									<logic:equal name="mrdRecordReturnFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
										<bean:message key="fileNo"/>
									</logic:equal>
									<logic:notEqual name="mrdRecordReturnFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
										<bean:message key="admNo"/>
									</logic:notEqual>
								</b>	
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="recordType"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="handoverTo"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="issueDate"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="expectedReturnDate"/>
								</b>	
							</font>
						</div>
					</td>
				</tr>
			</table>	
			<logic:iterate id="recordIssueVO" name="<%=MrdConfig.ISSUED_MRD_RECORD_BY_REQUEST_ID_VO_LIST%>" indexId="idx" type="hisglobal.vo.MrdRecordIssueDtlVO">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0"> 		
			 		<tr>
			 		 	<td width="5%" class="tdfontheadExam">
							<div align="center" style="display:none">
								<html:checkbox name="mrdRecordReturnFB" property="selectedRecord" value="<%=String.valueOf(idx)%>" tabindex="1" onclick="showLocation(this)" />
							</div>
						</td>
						<td width="15%"  class="tdfontheadExam">
						  	<div align="center">
						  		<font color="#000000">
						  	 		<bean:write name="recordIssueVO" property="patCrNo"/>
						   		</font>
						    </div>   
						</td>
						<td  width="15%" class="tdfontheadExam">
						  	<div align="center">
						  		<font color="#000000">
						  	 		<bean:write name="recordIssueVO" property="patName"/>
						   		</font>
						    </div>   
						</td>
						<td width="10%"  class="tdfontheadExam">
						  	<div align="center">
						  		<font color="#000000">
						  	 		<logic:equal name="mrdRecordReturnFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
										<bean:write name="recordIssueVO" property="fileNo"/>
									</logic:equal>
									<logic:notEqual name="mrdRecordReturnFB" property="recordType" value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
										<bean:write name="recordIssueVO" property="patAdmNo"/>
									</logic:notEqual>
						  	 		
						   		</font>
						    </div>   
						</td>
						<td  width="15%" class="tdfontheadExam">
						  	<div align="center">
						  		<font color="#000000">
						  	 		<bean:write name="recordIssueVO" property="recordTypeName"/>
						   		</font>
						    </div>   
						</td>
						<td  width="20%" class="tdfontheadExam">
						  	<div align="center">
						  		<font color="#000000">
						  			<bean:write name="recordIssueVO" property="handoverTo"/>
						  			<html:hidden name="recordIssueVO" property="handoverTo"></html:hidden>
						    	</font>
						    </div>   
						</td>
						<td  width="10%" class="tdfontheadExam">
					  		<div align="center">
					  	 		<font color="#000000">
						    		<bean:write name="recordIssueVO" property="issueDate"/>
					    		</font>
					     	</div>   
						</td>
						<td  width="10%" class="tdfontheadExam">
					  		<div align="center">
					  	 		<font color="#000000">
						     		<bean:write name="recordIssueVO" property="expectedReturnDate"/>
					    		</font>
					     	</div>   
						</td>
					</tr>
				</table>	
				<div id="<%=String.valueOf(idx)%>" style="display: none;">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0"> 	
						<tr>
							<td  class="tdfonthead" colspan="2">
								<div align="right">
						  	 		<font color="#000000">
						  	 			<b>
						  	 			<%if((recordIssueVO.getRackLocation()!=null)||(recordIssueVO.getRackLabel()!=null)||(recordIssueVO.getShelfName()!=null)){ %>
						  	 				<bean:message key="record" />
						  	 				<bean:message key="location" />&nbsp;
						  	 			<%} %>
						  	 			</b>
						  	 		</font>
						  	 	</div>	
							</td>
							<td  class="tdfont" colspan="6">
								<div align="left">
								<logic:notEmpty name="recordIssueVO" property="rackLocation">&nbsp;<bean:write name="recordIssueVO" property="rackLocation"/>/</logic:notEmpty>
						  	 	<logic:notEmpty name="recordIssueVO" property="rackLabel"><bean:write name="recordIssueVO" property="rackLabel"/>/</logic:notEmpty>
						  	 	<logic:notEmpty name="recordIssueVO" property="shelfName"><bean:write name="recordIssueVO" property="shelfName"/>/</logic:notEmpty>	
						  	 	</div>
							</td>
						</tr>
					</table>	
					</div>
	            </logic:iterate>
            
        </his:ContentTag>
		<his:SubTitleTag name="Return Detail">
		</his:SubTitleTag>
		<his:ContentTag>
        	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
            	<tr>
					<td width="25%" class="tdfonthead">
						<font color="red">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="returnBy"/> 	
						</font>
					</td>
					<td width="25%" class="tdfont">
						<html:hidden property="returnBy"/>
						<input type="text" name="returnByName" size="20" onchange="returnMrdRecord()"/>
						<img class="button" src="/HISClinical/hisglobal/images/ico_myfriends.gif" tabindex="1" style="cursor: pointer;" 
						onkeypress="if(event.keyCode==13) showEmployeepopup(event,'returnBy@returnByName','0');" 
						onclick="showEmployeepopup(event,'returnBy@returnByName','0');" alt="Search Employee" title="Search Employee" border="0">
					</td>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="remarks"/>	
							</font>
						</div>
					</td>
					<td width="25%"  class="tdfont">
						<html:textarea property="remarks" rows="1" cols="30" tabindex="1" onkeypress="return CheckMaxLength(event,this,50,1)"/>
					</td>
				</tr>
			</table>
		</his:ContentTag>	
		
		<his:SubTitleTag name="">
			<div align="left">
				<html:checkbox name="mrdRecordReturnFB" property="recAcceptArchivedFlag" onclick="showArchivalDetail()"></html:checkbox>
				Record Archival Detail
			</div>
		</his:SubTitleTag>
		<div id="divArchivalDetail">
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%%" class="tdfont">
							<div align="left">
								<html:radio name="mrdRecordReturnFB" property="changeArchivedFlag" value="<%=MrdConfig.NO %>" onclick="showArchival()"></html:radio>
								<bean:message key="previousPlace"/>
								<html:radio name="mrdRecordReturnFB" property="changeArchivedFlag" value="<%=MrdConfig.YES %>" onclick="showArchival()"></html:radio>
								<bean:message key="change"/> <bean:message key="archived"/>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000" size="2">*</font>
									<bean:message key="putBy"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<html:select name="mrdRecordReturnFB" property="putBy" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" >
										<html:options collection="<%=MrdConfig.LIST_RECORD_PUT_BY_IN_MRD %>" property = "value" labelProperty = "label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>
				<div id="divChangeArchived">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
		            	<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000" size="2">*</font>
										<bean:message key="rack"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="mrdRecordReturnFB" property="rackId" onchange="showRackShelf()">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.LIST_OF_RACK_BASED_ON_MRD %>" >
											<html:options collection="<%=MrdConfig.LIST_OF_RACK_BASED_ON_MRD %>" property = "rackId" labelProperty = "rackName"/>
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000" size="2">*</font>
										<bean:message key="shelf"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="mrdRecordReturnFB" property="shelfId">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MrdConfig.LIST_OF_SHELF_BASED_ON_RACK %>" >
											<html:options collection="<%=MrdConfig.LIST_OF_SHELF_BASED_ON_RACK %>" property = "value" labelProperty = "label"/>
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
					</table>
				</div>	
				<div id="divRackInfoId" style="display: none;">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="rack"/>
										<bean:message key="information"/>
									</font>
								</div>
							</td>
							<td width="75%" class="tdfont">
								<div align="left">
									&nbsp;<bean:write name="mrdRecordReturnFB" property="rackInfo" />
								</div>
							</td>
						</tr>
					</table>		
				</div>		
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
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
			</his:statusNew>
			
		</his:ButtonToolBarTag>

	<html:hidden name="mrdRecordReturnFB" property="hmode"/>
	<html:hidden name="mrdRecordReturnFB"  property="requestId" />
	<html:hidden name="mrdRecordReturnFB"  property="recordId" />
	<html:hidden name="mrdRecordReturnFB"  property="recordType" />
	<html:hidden name="mrdRecordReturnFB"  property="mrdCode" />
	<html:hidden name="mrdRecordReturnFB"  property="issueUpto" />
	<html:hidden name="mrdRecordReturnFB"  property="hiddenReqStatus" />
	<html:hidden name="mrdRecordReturnFB" property="tempSelectedChk" />
	<html:hidden name="mrdRecordReturnFB" property="hiddenReturnByName" />
	<html:hidden name="mrdRecordReturnFB" property="currentPage"/>
	
	<input type="hidden" name="sysdate" value="<%=sysdate %>">	
</html:form>
<his:status/>

</body>
</html>

