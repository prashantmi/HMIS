<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="medicalboard.transactions.controller.fb.PostMedicalEntryFB"%>
<%@page import="hisglobal.vo.MedicalBoardRequisitionVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>
<%@page import="medicalboard.transactions.controller.fb.CertificateVerificationFB"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function noOfDays(a,b)
{
	valid=true;
	var day=0;
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
	day=(adate-bdate)/86400000;
	return day;
}

function clearForm()
{
	document.getElementsByName("approvedDate")[0].value="";
	document.getElementsByName("opinionCode")[0].value="-1";
	document.getElementsByName("finalRemark")[0].value="";
}
function getCandidateList(){
	if(isSelected(document.getElementsByName("certificateTypeID")[0],"Certificate Type")
		&& validateFromDate()){
		submitForm('GETCANDIDATELIST');	
	}
	else{
		return false;
	}	
}

function validateFromDate(){
	var sysdate=document.getElementsByName("sysdate")[0]
	var fromDate=document.getElementsByName("fromDate")[0]
	var toDate=document.getElementsByName("toDate")[0]
	if(!compareDate(toDate,sysdate,2)){
		alert("To Date cannot be greater than Current Date")
		return false;
	}
	if(!compareDate(fromDate,sysdate,2)){
		alert("From Date cannot be greater than Current Date")
		return false;
	}
	if(toDate.value!="" && fromDate.value!=""){
		if(!compareDate(fromDate,toDate,2)){
			alert("From Date cannot be greater than To Date")
			return false;
		}
	}	
	return true;
}

function getCanidateMedicalDetail(obj){
	document.getElementsByName("hmode")[0].value='GETCANDIDATEMEDICALDTL'
	document.getElementsByName("patCrNo")[0].value=obj.value.split("#")[1];
	document.getElementsByName("selReqId")[0].value=obj.value.split("#")[2];
	document.forms[0].submit();
}

function validateSaveForm(mode)
{
	
	if(document.getElementsByName("approvedDate")[0].value=="")
	{
		alert("Please select approved date");
		document.getElementsByName("approvedDate")[0].focus();
		return false;
	}
	
	var days=noOfDays(document.getElementsByName("sysdate")[0].value,document.getElementsByName("approvedDate")[0].value);
			//alert("dayssssss "+days);
	if(days<0)
	{
		alert("Approved date can not be greater than today date");
		document.getElementsByName("approvedDate")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("opinionCode")[0].value=="-1")
	{
		alert("Please select final opinion");
		document.getElementsByName("opinionCode")[0].focus();
		return false;
	}
	
	
	var opinionLen=document.getElementsByName("opinionCodeArray").length;
	
	var opinion_Code_Array= new Array(opinionLen);
	var opinion_Count_Array=new Array(opinionLen);
		
	for(var i=0;i<opinionLen;i++)
	{
		opinion_Code_Array[i]=null;
		opinion_Count_Array[i]=0;
	}
	
	for(var i=0;i<opinionLen;i++)
	{
		var index="";
		
		//alert("opinion_Code_Array befor "+opinion_Code_Array);
		//alert("opinion_Count_Array "+opinion_Count_Array);
		for(var j=0;j<opinionLen;j++)
		{
			var flag=false;
			if(document.getElementsByName("opinionCodeArray")[i].value==opinion_Code_Array[j])
			{
				flag=true;
				index=j;
				break;
			}
		}
		
		if(flag)
		{
			//alert("inside if");
			opinion_Count_Array[index]=opinion_Count_Array[index]+1;
		}
		else
		{
			//alert("inside else");
			for(var k=0;k<opinionLen;k++)
			{
				if(opinion_Code_Array[k]==null)
				{
					opinion_Code_Array[k]=document.getElementsByName("opinionCodeArray")[i].value;
					opinion_Count_Array[k]=opinion_Count_Array[k]+1;
					break;
				}
			}
		}
		
		
	}
	
	//alert("opinion_Code_Array final "+opinion_Code_Array);
	//alert("opinion_Count_Array final "+opinion_Count_Array);
	
	var max=opinion_Count_Array[0];
	
	
	if(opinion_Count_Array.length>1)
	{
		for(var i=1;i<opinion_Count_Array.length;i++)
		{
			if(max>opinion_Count_Array[i])
			{
				max=max;
			}
			else
			{
				max=opinion_Count_Array[i];
			}
		}
	}
	
	//alert("max count "+max);
	
	var max_Count_Code_Array=new Array(opinion_Count_Array.length);
	
	var j=0;
	for(var i=0;i<opinion_Count_Array.length;i++)
	{
		if(max==opinion_Count_Array[i])
		{
			max_Count_Code_Array[j]=opinion_Code_Array[i];
			j=j+1;
		}
	}
	
	var codeFlag=false;
			
	for(var i=0;i<max_Count_Code_Array.length;i++)
	{
		var opinionCode=document.getElementsByName("opinionCode")[0].value.split("#")[0];
		if(opinionCode==max_Count_Code_Array[i])
		{
			codeFlag=true;					
		}
	}
			
	/*if(!codeFlag) // Commented as per CR 407
	{
		alert("Please select majority opinion");
		document.getElementsByName("opinionCode")[0].focus();
		return false;
	}*/
	
	if(document.getElementsByName("finalRemark")[0].value=="")
	{
		alert("Please select final remark");
		document.getElementsByName("finalRemark")[0].focus();
		return false;
	}
	
	submitPage(mode);
	
}

</script>


<%String sysdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>		
<his:TransactionContainer>
<html:form action="/certificateVerification">	   
<body>		   
   <his:TitleTag name="Certificate Verification"> 		
   </his:TitleTag>
   
<his:statusNew>
	<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="25%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="certificateType"/>
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont">
			<html:select name="CertificateVerificationFB" property="certificateTypeID" tabindex="1" styleClass="regcbo">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=MedicalBoardConfig.CERTIFICATE_TYPE_LIST_FOR_CERT_VERIFICATION %>">
				<html:options collection="<%=MedicalBoardConfig.CERTIFICATE_TYPE_LIST_FOR_CERT_VERIFICATION %>" labelProperty="label" property="value"/>
				</logic:present>
			</html:select>
		  </td>
		 <td width="25%" class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="board"/>
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont">
			<html:select name="CertificateVerificationFB" property="boardNo" tabindex="1" styleClass="regcbo">
				<html:option value="%">All</html:option>
				<logic:present name="<%=MedicalBoardConfig.MEDICAL_BOARD_LIST_FOR_CERT_VERIF %>">
				<html:options collection="<%=MedicalBoardConfig.MEDICAL_BOARD_LIST_FOR_CERT_VERIF %>" labelProperty="label" property="value"/>
				</logic:present>
			</html:select>
		  </td>
		 </tr>
		 <tr>
		 	<td width="25%" class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="fromDate"/>
			 </font>
		  	</td>
		  	 <td width="25%" class="tdfont">
		  	 	<his:date name="fromDate" value="${CertificateVerificationFB.sysDate}" ></his:date>
		  	 </td>
		 	<td width="25%" class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="toDate"/>
			 </font>
		  	</td>
		  	 <td width="25%" class="tdfont">
		  	 	<his:date name="toDate" value="${CertificateVerificationFB.sysDate}"></his:date>
		  	 </td>
		 </tr>
	</table>	  
	</his:ContentTag>
</his:statusNew>   

<his:statusList>
 	<% 	PaginationFB fbPage= new PaginationFB();
			pageContext.setAttribute("fbPagination",fbPage);
			fbPage.setCurrentPage(((CertificateVerificationFB)request.getAttribute("CertificateVerificationFB")).getCurrentPage());
			fbPage.setObjArrName(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST_FOR_CERT_VERIFICATION);
			fbPage.setAppendInTitle("Candidate");
			fbPage.setMaxRecords(15);
		%>
	<logic:present name="<%=MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST_FOR_CERT_VERIFICATION %>">
   <table width="100%">
   		<tr>
   			<td width="50%" class="tdfonthead">
   				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
   				<b><bean:message key="certificateType"/></b>
   				</font>
   			</td>
   			<td width="50%" class="tdfont">
   				<b><bean:write name="CertificateVerificationFB" property="certificateTypeName"/></b>
   			</td>
   			<%-- <td width="25%" class="tdfonthead">
   				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
   				<b><bean:message key="board"/></b>
   				</font>
   			</td>
   			<td width="25%" class="tdfont">
   				<b><bean:write name="postMedicalEntryFB" property="boardName"/></b>
   			</td>--%>
   		</tr>
   	</table>
   <his:SubTitleTag name="Candidate List">
   </his:SubTitleTag>
    
   <his:PaginationTag name="fbPagination"></his:PaginationTag>
   	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="5%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="select"/></b>
			 </font>
			</div>
		  </td>
		 <td width="15%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="crNo"/></b>
			 </font>
			</div>
		  </td>
		 <td width="15%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="candidate"/> <bean:message key="name"/></b>
			 </font>
			</div>
		  </td>
		 <td width="10%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="gender/age"/></b>
			 </font>
			</div>
		  </td>
		  
		 <td width="15%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="requisitionDate"/></b>
			 </font>
			</div>
		  </td>
		 <td width="15%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="lastMedicalVisit"/></b>
			 </font>
			</div>
		  </td>
		 <td width="10%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="board"/></b>
			 </font>
			</div>
		  </td> 
		</tr>
		<%	int startIndex= fbPage.getStartIndex();
			int endIndex=fbPage.getEndIndex();
			List requisitionVOList=(List)session.getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST_FOR_CERT_VERIFICATION);
			for(int index=startIndex;index<=endIndex;index++){
				MedicalBoardRequisitionVO requisitionVO=(MedicalBoardRequisitionVO)requisitionVOList.get(index);
				pageContext.setAttribute("requisitionVO",requisitionVO);
		%>
		<%--<logic:iterate id="requisitionVO" name="<%=MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST %>" type="hisglobal.vo.MedicalBoardRequisitionVO" indexId="index" offset="<%=startIndex+""%>" length="<%=endIndex+""%>"> --%>
		<tr>
		 <td class="tdfont">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <html:radio name="CertificateVerificationFB" property="selectedCandidate" value='<%=index+"#"+requisitionVO.getPatCrNo()+"#"+requisitionVO.getReqID()%>' onclick="getCanidateMedicalDetail(this)" onkeypress="if(event.keyCode==13 getCanidateMedicalDetail(this))" tabindex="1"/>
			 </font>
			</div>
		  </td>
		 <td class="tdfont">
		  <div align="center" >
			 <bean:write name="requisitionVO" property="patCrNo"/>
			</div>
		  </td>
		 <td class="tdfont">
		  <div align="center" >
			 <bean:write name="requisitionVO" property="patName"/>
			 <html:hidden name="CertificateVerificationFB" property="candidateName" value="<%=requisitionVO.getPatName() %>"/>
			</div>
		  </td>
		 <td  class="tdfont">
		  <div align="center">
			 <bean:write name="requisitionVO" property="patAgeGender"/>
			</div>
		  </td>
		 <td  class="tdfont">
		  <div align="center">
			 <bean:write name="requisitionVO" property="entryDate"/>
			</div>
		  </td>
		 <td class="tdfont">
		   <div align="center">
				<bean:write name="requisitionVO" property="visitDate"/>
			</div>
		  </td>
		 <td class="tdfont">
		   <div align="center">
				<bean:write name="requisitionVO" property="boardName"/>
			</div>
		  </td>

		  </tr>
		  <%} %>
		<%--</logic:iterate>--%>
	 </table>
	</his:ContentTag>
</logic:present>		
</his:statusList>
<his:statusTransactionInProcess>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />
<his:ContentTag>
		<logic:present name="<%=MedicalBoardConfig.BOARD_MEMBER_OPINION_LIST_BY_REQID %>">
		<logic:notEmpty name="<%=MedicalBoardConfig.BOARD_MEMBER_OPINION_LIST_BY_REQID %>">
		<his:SubTitleTag name="Board Opinion Detail">
		</his:SubTitleTag>	
		<table width="100%" cellspacing="1">
			<tr>
				<td width="33%" class="tdfonthead" align="center">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="boardMember"/>
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead" align="center">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="opinion"/>
						</font>
					</div>
				</td>
				<td width="33%" class="tdfonthead" align="center">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="opinionRemark"/>
						</font>
					</div>	 
				</td>
			</tr>
		<logic:iterate id="verificationVO" name="<%=MedicalBoardConfig.BOARD_MEMBER_OPINION_LIST_BY_REQID%>" type="hisglobal.vo.MedicalBoardVerificationDtlVO" indexId="index">
			<tr>
				<td width="33%" class="tdfont">
					<div align="center">
						<bean:write name="verificationVO" property="empName"/>
					</div>
				</td>
				<td width="33%" class="tdfont">
					<div align="center">
						<bean:write name="verificationVO" property="opinionDesc"/>
						<html:hidden name="CertificateVerificationFB" property="opinionCodeArray" value="<%=verificationVO.getOpinionCode() %>"/>
					</div>
				</td>
				<td width="33%" class="tdfont">
					<div align="center">
						<bean:write name="verificationVO" property="opinion"/>
					</div>
				</td>
			</tr>
		</logic:iterate>	
		</table>	
		</logic:notEmpty>
		</logic:present>	
		
	<his:SubTitleTag name="Verification Detail">
	</his:SubTitleTag>
		<table width="100%" cellspacing="1">	
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="medicalCertNo"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
					<bean:write name="CertificateVerificationFB" property="certificateNo"/>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="approvedDate"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
					<his:date name="approvedDate" value="<%=sysdate%>"></his:date>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="final"/> <bean:message key="opinion"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
				<html:select name="CertificateVerificationFB" property="opinionCode" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=MedicalBoardConfig.ALL_OPINION_LIST_BY_REQUISITION_ID %>">
					<html:options collection="<%=MedicalBoardConfig.ALL_OPINION_LIST_BY_REQUISITION_ID %>" labelProperty="label" property="value"/>
					</logic:present>
				</html:select>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right">
					<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="final"/> <bean:message key="remark"/>
					</font>
				</div>	
				</td>
				<td width="50%" class="tdfont">
					<html:textarea name="CertificateVerificationFB" property="finalRemark" cols="50" tabindex="1" onkeypress="return CheckMaxLength(event,this,100,1)"/>
				</td>
			</tr>
		</table>
</his:ContentTag>

</his:statusTransactionInProcess>	

<his:ButtonToolBarTag>
         <div align="center">	
       		
	         <his:statusTransactionInProcess>
	         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) validateSaveForm('SAVE');" tabindex="1" onclick ="validateSaveForm('SAVE');">
	         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) clearForm();" tabindex="1" onclick ="clearForm();">
	         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('GETCANDIDATELIST');" tabindex="1" onclick ="submitForm('GETCANDIDATELIST');">
	        </his:statusTransactionInProcess>
	        <his:statusList>
	         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick ="submitForm('NEW');">
	        </his:statusList>
	        <his:statusNew>
	        	<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13)getCandidateList();" tabindex="1" onclick ="getCandidateList();">
	        	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
	        </his:statusNew>
	
         </div>
     </his:ButtonToolBarTag>
    
   <html:hidden name="CertificateVerificationFB" property="hmode"/>
   <html:hidden name="CertificateVerificationFB" property="patCrNo"/>
   <html:hidden name="CertificateVerificationFB" property="certificateTypeID"/>
   <html:hidden name="CertificateVerificationFB" property="certificateTypeName"/>
   <html:hidden name="CertificateVerificationFB" property="selReqId"/>
   <input type="hidden" name="sysdate" value="<%=sysdate%>">
  
   <his:status/>
   </body>     
   </html:form>

</his:TransactionContainer>