<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
    elmt.value=mode;
    document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function clearForm()
{	//alert("hi");
	document.getElementsByName("hrgstr_fname")[0].value="";
	document.getElementsByName("hrgstr_mname")[0].value="";
	document.getElementsByName("hrgstr_lname")[0].value="";
	document.getElementsByName("hrgnum_puk")[0].value="";
	if(document.getElementsByName("recordType")[0].value!=<%=MrdConfig.RECORD_TYPE_OPD_FILE%>)
	{
		document.getElementsByName("hipnum_admno")[0].value="";
		document.getElementsByName("hrgnum_mlc_no")[0].value="";
		document.getElementsByName("hipdt_disstatus_code")[0].value="-1";
	}	
	document.getElementsByName("fromDate")[0].value="";
	document.getElementsByName("toDate")[0].value="";
}

function validateSearch()
{
	if(checkSearchField()){
		submitPage('SEARCH');
	}	
}

function validateAddRecord()
{
	var len=document.getElementsByName("chkSelectRecord").length
	var count=0;
	var selectedIndex="";
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("chkSelectRecord")[i].checked)
		{
			count++;
			selectedIndex=selectedIndex+document.getElementsByName("chkSelectRecord")[i].value+"@";
		}
	}
	
	if(count==0)
	{
		alert("Please Select At Least a Record");
	}
	else
	{
		if(selectedIndex!="") selectedIndex=selectedIndex.substring(0,selectedIndex.length-1);
		
	opener.document.getElementsByName('hmode')[0].value='POPULATE'
	opener.document.getElementsByName('concatedIndex')[0].value=selectedIndex;
	opener.document.forms[0].submit();
	self.close();
	}
}

function checkSearchField()
{
	if(document.getElementsByName("recordType")[0].value!=<%=MrdConfig.RECORD_TYPE_OPD_FILE%>)
	{
		if(document.getElementsByName("hrgstr_fname")[0].value!="" ||
		document.getElementsByName("hrgstr_mname")[0].value!="" ||
		document.getElementsByName("hrgstr_lname")[0].value!="" ||
		document.getElementsByName("hrgnum_puk")[0].value!="" ||
		document.getElementsByName("hipnum_admno")[0].value!="" ||
		document.getElementsByName("hrgnum_mlc_no")[0].value!="" ||
		document.getElementsByName("hipdt_disstatus_code")[0].value!="-1" ||
		document.getElementsByName("fromDate")[0].value!="" ||
		document.getElementsByName("toDate")[0].value!=""){
			return true;
		}
		else{
			alert("Please Enter at least one field");
			return false;
		}
	}
	else
	{
		if(document.getElementsByName("hrgstr_fname")[0].value!="" ||
		document.getElementsByName("hrgstr_mname")[0].value!="" ||
		document.getElementsByName("hrgstr_lname")[0].value!="" ||
		document.getElementsByName("hrgnum_puk")[0].value!="" ||
		document.getElementsByName("fromDate")[0].value!="" ||
		document.getElementsByName("toDate")[0].value!=""){
			return true;
		}
		else{
			alert("Please Enter at least one field");
			return false;
		}
	}
}

function checkDuplicateRec(index)
{
	
	var idx=index.value;
	//alert(idx);
	
	var str=document.getElementsByName("ducRecordId")[0].value;
	var arr=str.split("@");
	//alert(str);
	//alert(arr);
	//alert(document.getElementsByName("chkSelectRecord")[idx].value);
	//alert(document.getElementsByName("chkSelectRecord")[idx].checked);
	
	if(document.getElementsByName("chkSelectRecord")[idx].checked)
	{
		var recId=document.getElementsByName("searchRecordId")[idx].value;
		for(var i=0;i<arr.length;i++)
		{
			if(recId==arr[i].split("$")[0])
			{
				if(arr[i].split("$")[1]==<%=MrdConfig.MRD_RECORD_REQUEST_STATUS_RAISED%>)
					{
					alert("You Have Already Requested For This Record!!!");
					document.getElementsByName("chkSelectRecord")[idx].checked=false;
					return false;
					}
				 if(arr[i].split("$")[1]==<%=MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS%>)
					{
						alert("The Request For This Record Has Been Approved!!!"); 
						document.getElementsByName("chkSelectRecord")[idx].checked=false;
						return false;
					}   
						
			}
			
		}	
	}
	
}
var countnew = -1;
function checkDept(index)
{
	//alert("count" + countnew)
	countnew++;
	
	var idx=index.value;
	//alert(idx)
	var hiddendept = document.getElementsByName("hiddenDeptName")[0].value
	var reqdeptname=document.getElementsByName("requestByEmpDept")[0].value;
	var sheetdeptname=document.getElementsByName("sheetDept")[0].value;
	var deptname=document.getElementsByName("deptname")[idx].value;
	var empname=document.getElementsByName("requestById")[idx].value;
	
	//alert("hiddendept"+hiddendept)
	//alert("deptname"+deptname)
	//alert("req"+reqdeptname)
	//alert("count++"+countnew)
//	alert(sheetdeptname)
if( sheetdeptname != null && sheetdeptname != "")
	{
		//alert("final")
		if( sheetdeptname == deptname)
		{
			return true;
		}
		else
		{
			//alert("Department of case sheets should be same!!!");
			document.getElementsByName("chkSelectRecord")[idx].checked=true;
			return true;
		}
	}
if(countnew>0)
{
	//alert("count enter 1")
	if(hiddendept != null && hiddendept != ""  )
		{
	//	alert("first if")
			if(hiddendept == deptname)
				{
					return true;
				}
			else
				{
					//alert("department of case sheets should be same");
					document.getElementsByName("chkSelectRecord")[idx].checked=true;
					return true;
				}
		}
	else
		{
		//alert("first else")
			
			if(reqdeptname == deptname )
			{
				return true;
			}
			else
			{
				document.getElementsByName("chkSelectRecord")[idx].checked=true;
				return true;
				//alert("department of sheet doesnot match with department of requested by");
				//document.getElementsByName("chkSelectRecord")[idx].checked=false;		
				//return false; 
			}
		}
}
else
	{
	//alert("last else")
		if(document.getElementsByName("chkSelectRecord")[idx].checked)
		{
			if(reqdeptname != null && reqdeptname != "")
				{
					
					//alert(deptname)
					if(reqdeptname == deptname)
					{
						return true;
					}
					else
					{
						//alert("department of sheet doesnot match with departmenmt of requested by"); commented by sandip naik
						//document.getElementsByName("chkSelectRecord")[idx].checked=false;		
						//return false;
						//added by sandip naik on 14/06/2017
						document.getElementsByName("chkSelectRecord")[idx].checked=true;		
						return true;
					}
				}
			else
				{
					document.getElementsByName("hiddenDeptName")[0].value=document.getElementsByName("deptname")[idx].value;
					return true;
				}
			
		}
	
	}
}
</script>

<html:form action="/offlineMrdRecordRequest">
<body>  
	<his:SubTitleTag name="Search">
	</his:SubTitleTag>
		
    <his:ContentTag>
    	<table width="100%" border="0"  cellspacing="1" cellpadding="0">
			<tr>
				<td width="34%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="firstName"/>
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="middleName"/>
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="lastName"/>
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="34%" class="tdfont">
					<div align="center">
						<html:text name="OfflineMrdRecordReqDtlFB" property="hrgstr_fname" maxlength="30" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
					</div>
				</td>
				<td width="33%" class="tdfont">
					<div align="center">
						<html:text name="OfflineMrdRecordReqDtlFB" property="hrgstr_mname" maxlength="20" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
					</div>
				</td>
				<td width="33%" class="tdfont">
					<div align="center">
						<html:text name="OfflineMrdRecordReqDtlFB" property="hrgstr_lname" maxlength="30" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1"></html:text>
					</div>
				</td>
			</tr>
			<logic:notEqual name="OfflineMrdRecordReqDtlFB" property="recordType"  value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">	
				<tr>
					<td width="34%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>
						</div>
					</td>
					<td width="33%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="admNo"/>
							</font>
						</div>
					</td>
					<td width="33%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mlcNo"/>
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="34%" class="tdfont">
						<div align="center">
							<html:text name="OfflineMrdRecordReqDtlFB" property="hrgnum_puk" maxlength="15" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
						</div>
					</td>
					<td width="33%" class="tdfont">
						<div align="center">
							<html:text name="OfflineMrdRecordReqDtlFB" property="hipnum_admno" maxlength="15" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
						</div>
					</td>
					<td width="33%" class="tdfont">
						<div align="center">
							<html:text name="OfflineMrdRecordReqDtlFB" property="hrgnum_mlc_no" maxlength="10" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
						</div>
					</td>
				</tr>
				<tr>
					<td width="34%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="discharge"/>
								<bean:message key="type"/>
							</font>
						</div>
					</td>
					<td width="33%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="fromDate"/>
							</font>
						</div>
					</td>
					<td width="33%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="toDate"/>
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="34%" class="tdfont">
						<div align="center">
							<html:select name="OfflineMrdRecordReqDtlFB" property="hipdt_disstatus_code">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MrdConfig.CASE_SHEET_ENQUIRY_DISCHARGE_TYPE_LIST %>">
									<html:options collection="<%=MrdConfig.CASE_SHEET_ENQUIRY_DISCHARGE_TYPE_LIST %>" property="value" labelProperty="label"/>
								</logic:present>
							</html:select>
						</div>
					</td>
					<bean:define name="OfflineMrdRecordReqDtlFB" property="fromDate" id="frDate" type="java.lang.String"/>
					<td width="33%" class="tdfont">
						<div align="center">
							<his:date name="fromDate" dateFormate="%d-%b-%Y" value="<%=frDate %>"></his:date>
						</div>
					</td>
					<bean:define name="OfflineMrdRecordReqDtlFB" property="toDate" id="toDate" type="java.lang.String"/>
					<td width="33%" class="tdfont">
						<div align="center">
							<his:date name="toDate" dateFormate="%d-%b-%Y" value="<%=toDate %>"></his:date>
						</div>
					</td>
				</tr>
			</logic:notEqual>	
		
			<logic:equal name="OfflineMrdRecordReqDtlFB" property="recordType"  value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
				<tr>
					<td width="34%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="crNo"/>
							</font>
						</div>
					</td>
					<td width="33%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="fromDate"/>
							</font>
						</div>
					</td>
					<td width="33%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="toDate"/>
							</font>
						</div>
					</td>
				</tr>	
				<tr>
					<td width="34%" class="tdfont">
						<div align="center">
							<html:text name="OfflineMrdRecordReqDtlFB" property="hrgnum_puk" maxlength="13" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
						</div>
					</td>
					<bean:define name="OfflineMrdRecordReqDtlFB" property="fromDate" id="frDate" type="java.lang.String"/>
					<td width="33%" class="tdfont">
						<div align="center">
							<his:date name="fromDate" dateFormate="%d-%b-%Y" value="<%=frDate %>"></his:date>
						</div>
					</td>
					<bean:define name="OfflineMrdRecordReqDtlFB" property="toDate" id="toDate" type="java.lang.String"/>
					<td width="33%" class="tdfont">
						<div align="center">
							<his:date name="toDate" dateFormate="%d-%b-%Y" value="<%=toDate %>"></his:date>
						</div>
					</td>
				</tr>
			</logic:equal>
		</table>	
    </his:ContentTag>
    
    <his:ButtonToolBarTag>
    	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-search.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateSearch()" onkeypress="if(event.keyCode==13) validateSearch()">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" closeForm()" onkeypress="if(event.keyCode==13)closeForm()">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
    </his:ButtonToolBarTag>
    
    <his:statusTransactionInProcess>
    	<his:SubTitleTag name="Search Result">
    	</his:SubTitleTag>
    	<%if(session.getAttribute(MrdConfig.ARR_CASESHEET_SEARCHED_RECORD_VO)!=null)
		{
			List lstSearchVO=(List)session.getAttribute(MrdConfig.ARR_CASESHEET_SEARCHED_RECORD_VO);
			if(lstSearchVO.size()>0){
		%>
		<logic:notEqual name="OfflineMrdRecordReqDtlFB" property="recordType"  value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
	    	<his:ContentTag>
	    		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="select"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="crNo"/>
									</b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="name"/>
									</b>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="gender/age"/>
									</b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="admNo"/>
									</b>
								</font>
							</div>
						</td>
						<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="mlcNo"/>
									</b>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="recordStatus"/>
									</b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deptname"/>(<bean:message key="deptunitname"/>)
									</b>
								</font>
							</div>
						</td>
<!-- 						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;"> -->
<!-- 							<div align="center"> -->
<!-- 								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> -->
<!-- 									<b> -->
<%-- 										<bean:message key="hod"/> --%>
<!-- 									</b> -->
<!-- 								</font> -->
<!-- 							</div> -->
<!-- 						</td> -->
					
					</tr>
					<logic:iterate id="searchedRecord" name="<%=MrdConfig.ARR_CASESHEET_SEARCHED_RECORD_VO %>" type="mrd.vo.CommonCaseSheetEnquiryVO" indexId="idx">
						<tr>
							<td width="5%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<html:checkbox name="OfflineMrdRecordReqDtlFB" property="chkSelectRecord" value="<%=idx.toString() %>" onclick="checkDuplicateRec(this)"> <%-- , checkDept(this)" --%></html:checkbox>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchedRecord.getHrgnum_puk() %>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchedRecord.getHrgstr_fname() %>
										<%=searchedRecord.getHrgstr_mname()==null?"":searchedRecord.getHrgstr_mname() %>
										<%=searchedRecord.getHrgstr_lname()==null?"":searchedRecord.getHrgstr_lname() %>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchedRecord.getGstr_gender_name() %>/
										<%=searchedRecord.getHrgnum_age()%>
										<%-- <%=searchedRecord.getHrgstr_ageunit()%> --%>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchedRecord.getHipnum_admno()%>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchedRecord.getHrgnum_mlc_no()==null?"-":searchedRecord.getHrgnum_mlc_no()%>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=MrdConfig.MRD_RECORD_STATUS_ARRAY[Integer.parseInt(searchedRecord.getRecordStatus())]%>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									   <html:hidden name="OfflineMrdRecordReqDtlFB" property="deptname" value="<%=searchedRecord.getDeptname()%>"/>
										<%=searchedRecord.getDeptname()==null?"-":searchedRecord.getDeptname()%>(<%=searchedRecord.getDeptunitname()==null?"-":searchedRecord.getDeptunitname()%>)
									</font>
								</div>
							</td>
<!-- 							<td width="15%" class="tdfontheadExam" > -->
<!-- 								<div align="center"> -->
<!-- 									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> -->
<%-- 										<%=searchedRecord.getHodName()==null?"-":searchedRecord.getHodName()%> --%>
<!-- 									</font> -->
<!-- 								</div> -->
<!-- 							</td> -->
						</tr>
						<html:hidden name="OfflineMrdRecordReqDtlFB" property="searchRecordId" value="<%=searchedRecord.getMrdRecordId() %>"/>
					</logic:iterate>
				</table>		
	    	</his:ContentTag>
    	</logic:notEqual>
    	
    	<logic:equal name="OfflineMrdRecordReqDtlFB" property="recordType"  value="<%=MrdConfig.RECORD_TYPE_OPD_FILE %>">
	    	<his:ContentTag>
	    		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="select"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="crNo"/>
									</b>
								</font>
							</div>
						</td>
						<td width="38%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="name"/>
									</b>
								</font>
							</div>
						</td>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="gender/age"/>
									</b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="fileNo"/>
									</b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="unit"/>
									</b>
								</font>
							</div>
						</td>
						<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="recordStatus"/>
									</b>
								</font>
							</div>
						</td>
					
					
					</tr>
					<logic:iterate id="searchedRecord" name="<%=MrdConfig.ARR_CASESHEET_SEARCHED_RECORD_VO %>" type="mrd.vo.CommonCaseSheetEnquiryVO" indexId="idx">
						<tr>
							<td width="5%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<html:checkbox name="OfflineMrdRecordReqDtlFB" property="chkSelectRecord" value="<%=idx.toString() %>" onclick="checkDuplicateRec(this)"></html:checkbox>
									</font>
								</div>
							</td>
							<td width="17%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchedRecord.getHrgnum_puk() %>
									</font>
								</div>
							</td>
							<td width="38%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchedRecord.getHrgstr_fname() %>
										<%=searchedRecord.getHrgstr_mname()==null?"":searchedRecord.getHrgstr_mname() %>
										<%=searchedRecord.getHrgstr_lname()==null?"":searchedRecord.getHrgstr_lname() %>
									</font>
								</div>
							</td>
							<td width="10%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchedRecord.getGender() %>/
										<%=searchedRecord.getAge()%>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchedRecord.getHrgstr_file_no()%>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=searchedRecord.getDeptUnit()%>
									</font>
								</div>
							</td>
							<td width="15%" class="tdfontheadExam" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=MrdConfig.MRD_RECORD_STATUS_ARRAY[Integer.parseInt(searchedRecord.getRecordStatus())]%>
									</font>
								</div>
							</td>
						</tr>
						<html:hidden name="OfflineMrdRecordReqDtlFB" property="searchRecordId" value="<%=searchedRecord.getMrdRecordId() %>"/>
					</logic:iterate>
				</table>		
	    	</his:ContentTag>
    	</logic:equal>
    	<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ok.png"/>'  style=cursor:pointer tabindex="1" onclick ="validateAddRecord()" onkeypress="if(event.keyCode==13)validateAddRecord() ">
		</his:ButtonToolBarTag>
		<%}} %>
    </his:statusTransactionInProcess>
    
    <html:hidden name="OfflineMrdRecordReqDtlFB" property="isReqOnlineOffline"/>
    <html:hidden name="OfflineMrdRecordReqDtlFB" property="ducRecordId"/>
    <html:hidden name="OfflineMrdRecordReqDtlFB" property="userEmpId"/>
    <html:hidden name="OfflineMrdRecordReqDtlFB" property="recordType"/>
    <html:hidden name="OfflineMrdRecordReqDtlFB" property="hmode"/>
        <html:hidden name="OfflineMrdRecordReqDtlFB" property="requestByEmpDept"/>
         <html:hidden name="OfflineMrdRecordReqDtlFB" property="hiddenDeptName"/>
           <html:hidden name="OfflineMrdRecordReqDtlFB" property="sheetDept"/>
           <html:hidden name="OfflineMrdRecordReqDtlFB" property="employeeId"/>
           
        
</body>
<his:status/>
</html:form>    