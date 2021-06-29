<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="mrd.MrdConfig,java.util.List;"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>

<his:javascript src="/registration/js/popup.js" />


<script type="text/javascript">

function showNextImage()
{
	var strIndex=document.getElementsByName("imageIndex")[0].value;
	var size=<%=((List)session.getAttribute(Config.IMAGE_BYTE_ARRAY)).size()%>
	var sizeInt=parseInt(size);
	sizeInt=sizeInt-1;
	var index=parseInt(strIndex);
	index=index+1;
	//alert("sizeint "+sizeInt)
	//alert("index "+index)
	/* edit if(index<=sizeInt) on 11 OCT 2017 */
		if(index<sizeInt)
		{
			document.getElementsByName("imageIndex")[0].value=index;
			document.getElementById("count").innerHTML="Showing " +(index+1)+ " of  <%=((List)session.getAttribute(Config.IMAGE_BYTE_ARRAY)).size()%>";
			//submitForm('ALLIMAGES');
			
			var url="/HISClinical/image/showImage?"+'<%=Config.REQ_PARAMETER_IMAGE_INDEX%>'+"="+index;
			document.getElementById("image").src=url;
			document.getElementById("previousButton").style.display="block"
		}
		else
		{
			
			//alert("No More Images Available")
		}
		
		if(index==sizeInt){
			document.getElementById("nextButton").style.display="none"
		}
		else{
			document.getElementById("nextButton").style.display="block"
		}
}

function showPreviousImage()
{
	var strIndex=document.getElementsByName("imageIndex")[0].value;
	var index=parseInt(strIndex);
	index=index-1;
	if(index>=0)
	{
		document.getElementsByName("imageIndex")[0].value=index;
		document.getElementById("count").innerHTML="Showing " +(index+1)+ " of  <%=((List)session.getAttribute(Config.IMAGE_BYTE_ARRAY)).size()%>";
		//submitForm('ALLIMAGES');
		var url="/HISClinical/image/showImage?"+'<%=Config.REQ_PARAMETER_IMAGE_INDEX%>'+"="+index;
		document.getElementById("image").src=url;
		document.getElementById("nextButton").style.display="block"
	}
	else
	{
		//alert("No More Images Available")
	}
	
	if(index==0){
			document.getElementById("previousButton").style.display="none"
		}
		else{
			document.getElementById("previousButton").style.display="block"
		}
}

function hideDivision(id)
{
	//alert("hide "+document.getElementById(id).style.display)
	document.getElementById(id).style.display="none";
}
function closeImage(){
	disableBlanket();
	 document.getElementById("imageDetail").style.display="none"
	 document.getElementById("imageDiv").style.display="none"

}

function enableBlanket()
{
	document.getElementById("blanket").style.display="block";
}
function disableBlanket()
{
	document.getElementById("blanket").style.display="none";
}

function showAllImagesPopUp(e)
{
	 //openPopup('<his:path src="/mrd/emrDesk.cnt?hmode=ALLIMAGES&imageIndex=0"/>',e,500,900);
	 enableBlanket();
	 var index=document.getElementsByName("imageIndex")[0].value	
	 var index=parseInt(index)
	  document.getElementById("imageDetail").style.display="block"
	  document.getElementById("count").innerHTML="Showing " +(index+1)+ " of  <%=((List)session.getAttribute(Config.IMAGE_BYTE_ARRAY)).size()%>";
	  document.getElementById("imageDiv").style.display="block"
	 	//document.getElementById("imageDiv").innerHTML=responseString;
}


</script>

<html:form action="/emrDesk">

<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>

 <%--Patient Demographic Details --%>
<bean:define id="patientVoId" name="<%=MrdConfig.PATIENT_DTL_VO %>"  ></bean:define>
<%-- <bean:define id="clientFlag" name="<%=Config.CLIENT_NAME%>"></bean:define> --%>
	<his:TitleTag name="Patient Detail">
	</his:TitleTag>

<his:ContentTag>
		<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
				<tr>
				<td class="tdfonthead" width="17%">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="name" /></font></div>
				</td>
				<td width="17%" class="tdfont">
				<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write	name="patientVoId" property="patFirstName" />
				<bean:write name="patientVoId" property="patMiddleName" />
				<bean:write name="patientVoId" property="patLastName" />
				</font>
				</b>
				</td>
				
				<td width="17%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="crNo" />
				</font>
				</div>
				</td>
				
				
			<td class="tdfont">
				<div align="left">
				<b><bean:write name="patientVoId" property="patCrNo" />
						
				</b>
				</div>
				</td>
			
				<td width="17%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="age" />
				<bean:message key="slash" />
				<bean:message key="gender" />
				</font></div>
				</td>
				
				<td width="17%" class="tdfont"><b><font color="#000000"
					size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:write
					name="patientVoId" property="patAge" /> <bean:write
					name="patientVoId" property="patAgeUnit" /><logic:notEqual
					name="patientVoId" property="patGender" value="">/</logic:notEqual>
				<bean:write name="patientVoId" property="patGender" /></font></b></td>
				
			</tr>
			<tr>
			<td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="primCat" /> </font></div>
				</td>
				
				<td width="17%" class="tdfont"  ><b><font color="#000000" size="2" 
					face="Verdana, Arial, Helvetica, sans-serif"><bean:write
					name="patientVoId" property="patPrimaryCat" /> </font></b></td>
				
				<td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="maritalStatus" /> </font></div>
				</td> 
				
				<td width="17%" class="tdfont" nowrap ><b><font color="#000000" size="2" 
					face="Verdana, Arial, Helvetica, sans-serif"><bean:write
					name="patientVoId" property="patMaritalStatus" /> </font></b></td>
				
		<td class="tdfonthead" colspan="1" rowspan="3" width="17%" height="5%"  >
        	<div align="center">
       			 <%-- <table border="1" width="50%" height="50%">
	   				 <tr>
	    				<td>
	    					<img style=cursor:pointer src="<his:path src='/image/showImage'/>" height="100%" width="100%" title="Click To Enlarge" alt="No image Loaded" onkeypress="if(event.keyCode==13) openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,600,900)" onclick="openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,600,900)">
	    					<!-- eddited by Nilesh Gupta show Patient Image on 11 oct 2017 -->	
	    					<img style=cursor:pointer src="<his:path src='/image/showImage?reqParameterImageIndex=0'/>" height="100%" width="100%" title="Click To Enlarge" alt="No image Loaded" onkeypress="if(event.keyCode==13) showAllImagesPopUp(event)" onclick="showAllImagesPopUp(event)">
	    				</td>
	    			</tr>
	    		</table> --%>
	    	</div>
	    </td>
				
				<td width="17%" class="tdfont" colspan="1" rowspan="3">
				<div  align="center">
<!--		Click Below Link-->
				<table border="1" width="50%" height="50%">
	   				 <tr>
	    				<td>
	    					<%-- <img style=cursor:pointer src="<his:path src='/image/showImage'/>" height="100%" width="100%" title="Click To Enlarge" alt="No image Loaded" onkeypress="if(event.keyCode==13) openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,600,900)" onclick="openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,600,900)"> --%>
	    					<!-- eddited by Nilesh Gupta show Patient Image on 11 oct 2017 -->	
	    					<img style=cursor:pointer src="<his:path src='/image/showImage?reqParameterImageIndex=0'/>" height="100%" width="100%" title="Click To Enlarge" alt="No image Loaded" onkeypress="if(event.keyCode==13) showAllImagesPopUp(event)" onclick="showAllImagesPopUp(event)">
	    				</td>
	    			</tr>
	    		</table>
				</div>
				</td>
				
		</tr>
      	
			<tr>
			
				<td class="tdfonthead" width="17%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="fatherName" /></font></div></td>
				
				<td width="17%" class="tdfont"><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientVoId" property="patGuardianName" />
				</font>
				</b>
				</td>
				
				<td class="tdfonthead" width="17%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="motherName" /></font></div></td>
				
				<td width="17%" class="tdfont"><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientVoId" property="patMotherName" />
				</font>
				</b>
				</td>
				<td width="17%" class="tdfont">
				<div  align="center">
<!--			<a style=cursor:pointer onclick="showAllImagesPopUp(event);" ><font style="font-weight: bold;" ><u>More Images</u></font> </a>-->
				</div>
				</td>
				
			</tr>
			
			
				<tr>
				<td class="tdfonthead" width="17%" nowrap>
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="husbandName" /></font></div></td>
				
				
				
				<td width="17%" class="tdfont"  ><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientVoId" property="patHusbandName" />
				</font>
				</b>
				</td>
			
				
				<%-- <td class="tdfonthead" width="17%" nowrap>
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="nationality" /></font></div></td>
				
				<td width="17%" class="tdfont"><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientVoId" property="patNationality" />
				</font>
				</b>
				</td> 
				<td width="17%" class="tdfont">
				<div  >
				</div>
				</td>
				
			</tr>
			
			
			<tr>--%>
			<td class="tdfonthead" width="17%" nowrap>
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="religion" /></font></div></td>
				
				<td width="17%" class="tdfont"><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientVoId" property="patReligion" />
				</font>
				</b>
				</td>
				</tr><tr>
				<td class="tdfonthead" width="17%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="monthlyIncome" />  </font></div></td>
				
				
				
				<td width="17%" class="tdfont"  ><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">Rs.  
				<bean:write name="patientVoId" property="patMonthlyIncome" />
				</font>
				</b>
				</td>
				
				<logic:equal name="clientFlag"  value="<%=Config.CLIENT_GNCTD%>">	
				<td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="micnrNo" /> </font></div>
				</td>
				</logic:equal>
		
				<logic:notEqual name="clientFlag"  value="<%=Config.CLIENT_GNCTD%>">	
				<td width="17%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="nationalId" /> </font></div>
				</td>
				</logic:notEqual>
				<td width="17%" class="tdfont"><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientVoId" property="patNationalId" />
				</font>
				</b>
				</td>
			</tr>
			<logic:equal name="clientFlag"  value="<%=Config.CLIENT_PGIMER%>">
			<tr>
			
				<td class="tdfonthead" width="17%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="patOccupation" /></font></div></td>
				
				<td width="17%" class="tdfont"><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientVoId" property="patOccupationDesc" />
				</font>
				</b>
				</td>
				
				<td class="tdfonthead" width="17%" >
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="patFatherOccupation" /></font></div></td>
				
				<td width="17%" class="tdfont"><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientVoId" property="patFatherOccupationDesc" />
				</font>
				</b>
				</td>
				
				<td class="tdfonthead" width="17%" >
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="patHusbandOccupation" /></font></div></td>
				
				
				
				<td width="17%" class="tdfont"  ><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientVoId" property="patHusbandOccupationDesc" />
				</font>
				</b>
				</td>
				
			</tr>
			  
			<tr>
				<td class="tdfonthead" width="17%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="patCardNo" /></font></div></td>
				
				<td width="17%" class="tdfont"><b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="patientVoId" property="patCardNo" />
				</font>
				</b>
				</td>
				
				<td width="14%" class="tdfonthead">
				<div id="nicklable" align="right"><font size="2" color="#000000"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="nickName" /> </font></div>
				</td>
				
				<td width="17%" class="tdfont">
				<div id="nickNameControl">
				<bean:write name="patientVoId" property="patNickName" />
				</div>
				</td>
			
				<td class="tdfonthead" width="17%" >
				<div align="right">
				</div></td>
				<td width="17%" class="tdfont"  >
				<div>
				</div>
				</td>
			</tr>
			</logic:equal>
			
			<c:set var="patDeadStatusCode">
				<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD %>
			</c:set>
			<c:if test="${(patientVoId.mlcNo!=null && patientVoId.mlcNo!='') || (patientVoId.isDead==patDeadStatusCode) 
						|| (patientVoId.isMainCrNo!=null && patientVoId.isMainCrNo=='1') }">
				<tr>
					<c:if test="${patientVoId.mlcNo!=null && patientVoId.mlcNo!=''}">
						<td class="tdfonthead" width="17%" >
							<div align="right">
								<font size="2" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="mlcnumber"/>
								</font>
							</div>
						</td>		
						<td width="17%" class="tdfont"  >
							<div>
								<font size="2" color="red" face="Verdana, Arial, Helvetica, sans-serif">
								<c:out value="${patientVoId.mlcNo}"/>
								</font>
							</div>
						</td>
					</c:if>
				
					<c:if test="${patientVoId.isDead==patDeadStatusCode}">
						<td class="tdfonthead" width="17%" >
							<div align="right">
								<c:if test="${patientVoId.isDead==patDeadStatusCode}">
									<font size="2" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="patStatus"/>
									</font>
								</c:if>
							</div>
						</td>
						<td width="17%" class="tdfont"  >
							<div>
								<font size="2" color="red" face="Verdana, Arial, Helvetica, sans-serif">
								<c:if test="${patientVoId.isDead==patDeadStatusCode}">
									Dead
								</c:if>
								</font>
							</div>
						</td>
					</c:if>
					
					<c:if test="${patientVoId.isMainCrNo!=null && patientVoId.isMainCrNo=='1'}">
						<td class="tdfonthead" width="17%" >
							<div align="right">
								<font size="2" color="#000000" face="Verdana, Arial, Helvetica, sans-serif">
									CR Merge Status
								</font>
							</div>
						</td>
						<td width="17%" class="tdfont"  >
							<div>
								<font size="2" color="red" face="Verdana, Arial, Helvetica, sans-serif">
									Yes
								</font>
							</div>
						</td>
					</c:if>

					<c:if test="${!(patientVoId.mlcNo!=null && patientVoId.mlcNo!='')}">
						<td class="tdfonthead" width="17%" >
							<div align="right">
							</div>
						</td>
							
						<td width="17%" class="tdfont"  >
							<div>
							</div>
						</td>
					</c:if>
				
					<c:if test="${!(patientVoId.patStatusCode==patDeadStatusCode)}">
						<td class="tdfonthead" width="17%" >
							<div align="right">
							</div>
						</td>
							
						<td width="17%" class="tdfont"  >
							<div>
							</div>
						</td>
					</c:if>
					
					<c:if test="${!(patientVoId.isMainCrNo!=null && patientVoId.isMainCrNo=='1')}">
						<td class="tdfonthead" width="17%" >
							<div align="right">
							</div>
						</td>
							
						<td width="17%" class="tdfont"  >
							<div>
							</div>
						</td>
					</c:if>
				</tr>
			</c:if>
			<%-- IPD admission Detail --%>
	<%PatientDetailVO []patDtlVOs=(PatientDetailVO[])session.getAttribute(MrdConfig.ADMISSION_TREE_ARRAY);
	PatientDetailVO patDtlVO=null;
	if(patDtlVOs!=null){
		for(int i=0;i<patDtlVOs.length;i++){
			if(patDtlVOs[i].getDisDateTime()==null){
				patDtlVO=patDtlVOs[i];
				break;
			}	
		}	
	}
	if(patDtlVO!=null && patDtlVO.getDisDateTime()==null){%>
	<%pageContext.setAttribute("patDtlVO",patDtlVO); %>
			<tr>
				<td class="tdfonthead" width="17%"nowrap>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="admNo" />
						</font>
					</div>
				</td>			
				<td width="17%" class="tdfont">
					<b>
						<bean:write name="patDtlVO" property="patAdmNo" />
					</b>
				</td>
				
				<td class="tdfonthead" width="17%">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="admDate" />
						</font>
					</div>
				</td>			
				<td width="17%" class="tdfont">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="patDtlVO" property="admDateTime" />
					</font>
				</td>
				
				<td class="tdfonthead" width="17%" >
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dept/unit" />
						</font>
					</div>
				</td>			
				<td width="17%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="patDtlVO" property="departmentName" />/
							<bean:write name="patDtlVO" property="departmentUnitName" />
						</font>
					</b>
				</td>
				
			</tr>
			<tr>
				<td class="tdfonthead" width="17%" >
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="ward" />/
							<bean:message key="roomNo" />
						</font>
					</div>
				</td>			
				<td width="17%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="patDtlVO" property="wardName" />/
							<bean:write name="patDtlVO" property="ipdRoomName" />
						</font>
					</b>
				</td>
				<td class="tdfonthead" width="17%" >
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="bedNo" />
						</font>
					</div>
				</td>			
				<td width="17%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="patDtlVO" property="bedName" />
						</font>
					</b>
				</td>
				<td class="tdfonthead" width="17%" >
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							
						</font>
					</div>
				</td>			
				<td width="17%" class="tdfont">
					<b>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
							
						</font>
					</b>
				</td>
			</tr>
	<%} %>
<%-- /////////////////////////////////////////////////////////////// --%>
		</table>
		

<%--////////////////////////////////////////////// --%>

<%--Address Details --%>
<his:TitleTag name="Address Detail">
</his:TitleTag>
<table width="100%" cellspacing="0" cellpadding="0" bgcolor="#EBEBEB"
		style="clear:both; border-left:1px solid #003366;">

		<tr>
			<td colspan="4" width="50%" valign="top">
			<logic:notEmpty name="<%=MrdConfig.PAT_ADDRESS_DETAILS%>">
				<table width="100%" cellspacing="0" cellpadding="0">
					<tr>
						<td align="left">

						<table width="100%" cellspacing="1" cellpadding="0">

					
							<logic:iterate id="address" name="<%=MrdConfig.PAT_ADDRESS_DETAILS%>">
								<tr>
									<td width="65%" Class="tdfonthead" colspan="4"
										style="border-top:outset black 2px; border-bottom:inset black 2px;">
									<div align="left"><b><font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"><img
										src='<his:path src="/hisglobal/images/icn-home.png"/>'> <bean:write
										name="address" property="patAddType" /></font></b></div>
									</td>


								</tr>
								<tr>
									<td width="65%" Class="tdfont" colspan="4">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
											<bean:write	name="address" property="patAddHNo" />
												<logic:notEqual	name="address" property="patAddHNo" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddStreet" />
												<logic:notEqual	name="address" property="patAddStreet" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddCityLoc" />
												<logic:notEqual	name="address" property="patAddCityLoc" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddCityLocMstValue" />
												<logic:notEqual	name="address" property="patAddCityLocMstValue" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddDistrict" />
												<logic:notEqual	name="address" property="patAddDistrict" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddDistrictMstValue" />
												<logic:notEqual	name="address" property="patAddDistrictMstValue" value="">,
												</logic:notEqual>	
											<bean:write name="address" property="patAddCity" />
												<logic:notEqual	name="address" property="patAddCity" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddState" /> 
												<logic:notEqual	name="address" property="patAddState" value="">,
												</logic:notEqual>
											<bean:write name="address" property="patAddCountry" /> 
												<logic:notEqual	name="address" property="patAddPIN" value="">
													<bean:message key="line" />
												</logic:notEqual> 
											<bean:write name="address" property="patAddPIN" /> 
												<logic:notEqual name="address" property="patAddContactNo" value="">
													<bean:message key="addressContactNo" />
												</logic:notEqual> 
											<bean:write name="address" property="patAddContactNo" /> 
										</font>
									</td>
								</tr>
							</logic:iterate>
						</table>

						</td>
					</tr>
				</table>
				</logic:notEmpty>
				</td>
				</tr>
				</table>
</his:ContentTag>
<%-- /////////////////////////////////////////////// --%>


<%-- //////////////////////////////Patient Disease Details//////////////////////////////////////////// --%>
<%-- 
<his:ContentTag>
	<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
		<tr>
			<td class="tdfonthead" width="100%" nowrap>
				<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="patientCurrentDiseaseDetails" /></b></font></div>
			</td>
			<td>
			<div id="patientCurrentDiseaseShowImageId" style="display: block;" >
			<img class="button"  src='<his:path src="/hisglobal/images/arrow-down.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) showHideDivision('patientCurrentDiseaseDetailsId','patientCurrentDiseaseShowImageId','patientCurrentDiseaseHideImageId')" onclick="showHideDivision('patientCurrentDiseaseDetailsId','patientCurrentDiseaseShowImageId','patientCurrentDiseaseHideImageId')" tabindex="1"/>
			</div>
			<div id="patientCurrentDiseaseHideImageId" style="display: none;">
			<img class="button" src='<his:path src="/hisglobal/images/arrow-up.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) showHideDivision('patientCurrentDiseaseDetailsId','patientCurrentDiseaseShowImageId','patientCurrentDiseaseHideImageId')" onclick="showHideDivision('patientCurrentDiseaseDetailsId','patientCurrentDiseaseShowImageId','patientCurrentDiseaseHideImageId')" tabindex="1"/>
			</div>
			</td>
		</tr>   
	</table>
</his:ContentTag>
<div id="patientCurrentDiseaseDetailsId" style="display: none;" >
<his:ContentTag>
			<logic:present name="<%=MrdConfig.PAT_EPISODE_DIAGNOSIS_VO_ARRAY %>" >
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								
								<td width="33%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="diagnosisDate"/>
										</font>
									</div>
								</td>
								<td width="33%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="diseaseName"/>
										</font>
									</div>
								</td>
								<td width="33%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
												<bean:message key="dignosisTypeType"/>
										
										</font>
									</div>
								</td>
								
							</tr>
							<logic:iterate name="<%=MrdConfig.PAT_EPISODE_DIAGNOSIS_VO_ARRAY%>" id="episodeDiagnosisVOId" type="hisglobal.vo.EpisodeDiagnosisVO">
								<tr>
									
									<td class="tdfont">
										<div align="center"> 
										<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="episodeDiagnosisVOId" property="entryDate" />
										</font>
										</b>
										</div>
									</td>
									<td class="tdfont">
										<div align="center">
										<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="episodeDiagnosisVOId" property="dignosisName" />
										</font>
										</b>
						 				</div>
									</td>
									
									<td class="tdfont">
										<div align="center">
										<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="episodeDiagnosisVOId" property="diagnosticTypeName" />
										</font>
										</b>
										</div>
									</td>
									
								</tr>
							</logic:iterate>
						</table>
						</logic:present>	
						<logic:notPresent name="<%=MrdConfig.PAT_EPISODE_DIAGNOSIS_VO_ARRAY %>" >
							<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
								<tr>
									<td class="tdfonthead" width="25%" nowrap>
									<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="noDiseaseFound" /></font></div></td>
								</tr>
						</table>
						</logic:notPresent>	
					</his:ContentTag>
</div>
--%>
<%-- /////////////////////////////////////////////////////////////////////////////////////////// --%>

<%-- //////////////////////////////Patient Chronic Disease Details//////////////////////////////////////////// --%>
<%--
<his:ContentTag>
	<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
		<tr>
			<td class="tdfonthead" width="100%" nowrap>
				<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>Chronic Disease</b></font></div>
			</td>
			<td>
			<div id="chronicDiseaseShowImageId" style="display: block;" >
			<img class="button"  src='<his:path src="/hisglobal/images/arrow-down.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) showHideDivision('chronicDiseaseId','chronicDiseaseShowImageId','chronicDiseaseHideImageId')" onclick="showHideDivision('chronicDiseaseId','chronicDiseaseShowImageId','chronicDiseaseHideImageId')" tabindex="1"/>
			</div>
			<div id="chronicDiseaseHideImageId" style="display: none;">
			<img class="button" src='<his:path src="/hisglobal/images/arrow-up.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) showHideDivision('chronicDiseaseId','chronicDiseaseHideImageId','chronicDiseaseShowImageId')" onclick="showHideDivision('chronicDiseaseId','chronicDiseaseHideImageId','chronicDiseaseShowImageId')" tabindex="1"/>
			</div>
			</td>
		</tr>
	</table>
</his:ContentTag>
<div id="chronicDiseaseId" style="display: none;">
<table cellspacing="1" width="100%">
			   <logic:present name="<%=MrdConfig.PAT_CHRONIC_DISEASE_ARRAY %>" >
			   <tr>
					
					<td width="40%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="chronicDisease"/>
							</font>
						</div>
					</td>
					<td width="60%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="duration"/>
								
							</font>
						</div>
					</td>
				</tr>	
				

				    <bean:define id="arrAllAlert" name="<%= MrdConfig.PAT_CHRONIC_DISEASE_ARRAY%>"></bean:define>
					<c:forEach var="alertVO" items="${arrAllAlert}">
					
					<c:if test="${alertVO.effectiveTo==null}">
					<tr>
						<td class="tdfont" >
							<div align="center">
							  <b>
							  	<font color="#000000" size="2"  face="Verdana, Arial, Helvetica, sans-serif">

								<c:out value="${alertVO.alertName}"></c:out>
								</font>
								</b>
							</div>
						</td> 
						<td class="tdfont" >
							<div align="center">
							  <b>
							 <font color="#000000" size="2"  face="Verdana, Arial, Helvetica, sans-serif">

									<c:out value="${alertVO.durationDays}"></c:out>
								</font>
								</b>
							</div>
						</td> 
					</tr>
					<%//} %>
					</c:if>
					</c:forEach>

				</logic:present>
				 <logic:notPresent name="<%=MrdConfig.PAT_CHRONIC_DISEASE_ARRAY %>" >
				 	<tr>
						<td class="tdfonthead" width="100%">
						 	<div align="center">
					 		 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="noChronicDiseaseFound"/>
								</font>
							</div>
						</td>
						</tr>
				 </logic:notPresent>
		   </table>
	</div> --%> 
<%-- ./////////////////////////////////////////////////////////////////////////////////////////--%>

<%-- //////////////////////////////Patient Allergies Details//////////////////////////////////////////// --%>
<%-- 
<his:ContentTag>
	<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
		<tr>
			<td class="tdfonthead" width="100%" nowrap>
				<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>Patient Allergies</b></font></div>
			</td>
			<td>
			<div id="allergiesShowImageId" style="display: block;" >
			<img class="button"  src='<his:path src="/hisglobal/images/arrow-down.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) showHideDivision('patAllergyId','allergiesShowImageId','allergiesHideImageId')" onclick="showHideDivision('patAllergyId','allergiesShowImageId','allergiesHideImageId')" tabindex="1"/>
			</div>
			<div id="allergiesHideImageId" style="display: none;">
			<img class="button" src='<his:path src="/hisglobal/images/arrow-up.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) showHideDivision('patAllergyId','allergiesHideImageId','allergiesShowImageId')" onclick="showHideDivision('patAllergyId','allergiesHideImageId','allergiesShowImageId')" tabindex="1"/>
			</div>
			</td>
		</tr>
	</table>
</his:ContentTag>
<div id="patAllergyId" style="display: none;">
<table cellspacing="1" width="100%">
			 <logic:present name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY%>" >
					<tr>		
						<td width="13%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="diagnosisDate"/>
								</font>
							</div>
						</td>
						<td width="16%"  class="tdfonthead">
							<div align="center">	           
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="allergyName"/>
								</font>
							</div>
						</td>
								
						<td width="13%"  class="tdfonthead">
							<div align="center">	   
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="allergyType"/>
								</font>
							</div>
						</td>
						
						<td width="11%"  class="tdfonthead">
							<div align="center">	           
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="duration"/>
								</font>
							</div>
						</td>
							<td width="22%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="symptoms"/>
								</font>
							</div>
						</td>
						<td width="21%"  class="tdfonthead">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="allergySite"/>
									</font>
								</div>
						</td>
					</tr>
							
					<logic:iterate name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY%>" id="patAllergyDetailVO" indexId="index" type="hisglobal.vo.EpisodeAllergiesVO">
						
					<tr>
						<%String color="#000000"; %>
							<logic:equal name="patAllergyDetailVO"  property="isRevoked" value="0">
							<% color="#000000"; %> 
							</logic:equal>
							<logic:equal name="patAllergyDetailVO" property="isRevoked" value="1">
							<% color="#0000FF"; %>
							</logic:equal>
							
						<td class="tdfont" width="13%" >
							<div align="center">
							
							<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=patAllergyDetailVO.getEntryDate()  %>
							</font>
							</div>
						</td>
						
						<td width="16%"  class="tdfont">
							<div align="center">	           
								<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="patAllergyDetailVO" property="allergyName" />
										</font>
							</div>
						</td>
						<td width="13%"  class="tdfont">
							<div align="center">	   
								<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="patAllergyDetailVO" property="allergyTypeName"  />
								</font>
							</div>
						</td>
						<td width="11%"  class="tdfont">
							<div align="center">	           
								<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="patAllergyDetailVO" property="duration" />
								</font>
							</div>
						</td>			
						<td class="tdfont"  width="22%">
							<div align="center">
							<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=(patAllergyDetailVO.getAllergySymtoms()==null)?"-":patAllergyDetailVO.getAllergySymtoms()  %>
							</font>
							</div>
						</td>
						<td class="tdfont"  width="21%">
							<div align="center">
							<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<%=(patAllergyDetailVO.getAllergySite()==null)?"-":patAllergyDetailVO.getAllergySite()  %>
							</font>
							</div>
						</td>		  
					</tr> 
				</logic:iterate>	
				</logic:present>	
				
				<logic:notPresent name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY%>" >
					<his:ContentTag>
						<tr>
							<td class="tdfonthead" width="100%">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="noAllergiesFound" />
									</font>
								</div>
							</td>
						</tr>
			
					</his:ContentTag>
				</logic:notPresent>
		   </table>
	</div>  --%>
<%-- ./////////////////////////////////////////////////////////////////////////////////////////--%>

<%-- //////////////////////////////Patient Family Doctor Details//////////////////////////////////////////// --%>
<%-- 
<his:ContentTag>
	<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
		<tr>
			<td class="tdfonthead" width="100%">
				<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="patientFamilyDoctorDetails" /></b></font></div>
			</td>
			<td>
			<div id="patientFamilyDoctorShowImageId" style="display: block;" >
			<img class="button"  src='<his:path src="/hisglobal/images/arrow-down.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) showHideDivision('patientFamilyDoctorDetailsId','patientFamilyDoctorShowImageId','patientFamilyDoctorHideImageId')" onclick="showHideDivision('patientFamilyDoctorDetailsId','patientFamilyDoctorShowImageId','patientFamilyDoctorHideImageId')" tabindex="1"/>
			</div>
			<div id="patientFamilyDoctorHideImageId" style="display: none;">
			<img class="button" src='<his:path src="/hisglobal/images/arrow-up.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) showHideDivision('patientFamilyDoctorDetailsId','patientFamilyDoctorShowImageId','patientFamilyDoctorHideImageId')" onclick="showHideDivision('patientFamilyDoctorDetailsId','patientFamilyDoctorShowImageId','patientFamilyDoctorHideImageId')" tabindex="1"/>
			</div>
			</td>
		</tr>
	</table>
</his:ContentTag>
<div id="patientFamilyDoctorDetailsId" style="display: none;" >
<his:ContentTag>
			<logic:present name="<%=MrdConfig.PAT_FAMILY_DOC_DETAILS %>" >
			
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								
								<td width="20%" class="tdfonthead">
								<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										
												<bean:message key="doctor"/>
												<bean:message key="name"/>
											
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
												<bean:message key="physician"/>
												<bean:message key="type"/>
											
										</font>
									</div>
								</td>
								<td width="40%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
												<bean:message key="speciality"/>
										
										</font>
									</div>
								</td>
								<td width="15%" class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										
												<bean:message key="mobileNo"/>
												
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate name="<%=MrdConfig.PAT_FAMILY_DOC_DETAILS%>" id="familDoctorVOId" type="hisglobal.vo.PatFamilyDocDtlVO">
								<tr>
									
									<td class="tdfont">
										<div align="center">
										<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="familDoctorVOId" property="doctorName" />
										</font>
										</b>
										</div>
									</td>
									<td class="tdfont">
										<div align="center">
										<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="familDoctorVOId" property="physicianTypeDesc" />
										</font>
										</b>
										</div>
									</td>
									<td class="tdfont">
										<div align="center">
										<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="familDoctorVOId" property="consultationFor"/>
										</font>
										</b>
										</div>
									</td>
									<td class="tdfont">
										<div align="center">
										<b>
										<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
											<%=(familDoctorVOId.getMobileNo()==null)?"-":familDoctorVOId.getMobileNo() %>
										</font>
										</b>
										</div>
									</td>
								</tr>
							</logic:iterate>
						</table>
						</logic:present>	
						<logic:notPresent name="<%=MrdConfig.PAT_FAMILY_DOC_DETAILS %>" >
							<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
								<tr>
									<td class="tdfonthead" width="25%">
									<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="noFamilyDoctorDetailsFound" /></font></div></td>
								</tr>
						</table>
						</logic:notPresent>	
					</his:ContentTag>
</div>--%>
<%-- /////////////////////////////////////////////////////////////////////////////////////////// --%>


<%--//////////////////////////Next Visit Details//////////////////////////////////// --%>
<%-- 
<his:ContentTag>
	<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
		<tr>
			<td class="tdfonthead" width="100%" nowrap>
				<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="nextvisitdetail" /></b></font></div>
			</td>
			<td>
			<div id="patientNextVisitShowImageId" style="display: block;" >
			<img class="button"  src='<his:path src="/hisglobal/images/arrow-down.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) showHideDivision('patientNextVisitDetailsId','patientNextVisitShowImageId','patientNextVisitHideImageId')" onclick="showHideDivision('patientNextVisitDetailsId','patientNextVisitShowImageId','patientNextVisitHideImageId')" tabindex="1"/>
			</div>
			<div id="patientNextVisitHideImageId" style="display: none;">
			<img class="button" src='<his:path src="/hisglobal/images/arrow-up.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) showHideDivision('patientNextVisitDetailsId','patientNextVisitShowImageId','patientNextVisitHideImageId')" onclick="showHideDivision('patientNextVisitDetailsId','patientNextVisitShowImageId','patientNextVisitHideImageId')" tabindex="1"/>
			</div>
			</td>
		</tr>
	</table>
</his:ContentTag>
  
<div id="patientNextVisitDetailsId" style="display: none;">
<his:ContentTag>
	
	<logic:present name="<%=MrdConfig.PAT_EPISODE_NEXT_VISIT_ARRAY%>" >
	
	<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
			<tr>
				<td class="tdfonthead" width="50%">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="nextVisitDate" /></font></div></td>
				
				<td class="tdfonthead" width="50%">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="dept(unit)" /></font></div></td>
			</tr>

		<logic:iterate id="nextVisitId" name="<%=MrdConfig.PAT_EPISODE_NEXT_VISIT_ARRAY %>" >
			
			<tr>
			
				<td width="50%" class="tdfont">
				<div align="center">
				<b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="nextVisitId" property="episodeNextVisitDate" />
				</font>
				</b>
				</div>
				</td>
				
				<td width="50%" class="tdfont"  >
				<div align="center" >
				<b>
				<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="nextVisitId" property="episodeNextVisitDeptUnit" />
				</font>
				</b>
				</div>
				</td>
				</tr>

		</logic:iterate>
				</table>
	</logic:present>
	<logic:notPresent name="<%=MrdConfig.PAT_EPISODE_NEXT_VISIT_ARRAY %>" >
		<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
			<tr>
				<td class="tdfonthead" width="25%">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="noVisitDetailsFound" /></font></div></td>
			</tr>
		</table>
	</logic:notPresent>


</his:ContentTag>
</div>
 --%>
<%-- /////////////////////////////////////////////////////////////////////////////////////////// --%>
	<html:hidden name="EmrCommonDeskFB" property="imageIndex" value="0"/>
</html:form>

</html>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 
<%}catch(Exception e) {e.printStackTrace();}%>

<div id="blanket" style="height: 580;width: 1024;display: none;"></div>

<div id="imageDetail" style="display: none; height: 250;width: 350;position:absolute; top: 15%;left: 30%;z-index: 9100;">
	<div>
		<table width = "100%"  border="0" cellpadding="0" style="background-color:#F1ECE2;">  
		   <tr>
		   		<td id="count" class="tdfont" width="33%" align="center" colspan="1">
		   			
		   		</td>
		   		<td class="tdfont" width="33%" align="center" colspan="2">
		   			
		   			<div align="right" >
		   				<img class="button"	src="<his:path src='/hisglobal/images/close1.png'/>" tabindex="1"
						border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) closeImage();" onclick="closeImage();"  title="Close">
		   			</div>
		   		</td>
		   </tr>
		   <tr>
		   		<td class="tdfont" align="center" colspan="3" valign="center">
					<div id='imageDiv'>
						<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
						<%String url="/image/showImage?"+Config.REQ_PARAMETER_IMAGE_INDEX+"=0";%>
					    <img id="image" style=cursor:pointer src="<his:path src='<%=url%>'/>" height="100%" width="100%" alt="No Image Found">
					    </font>
					</div>
				</td>
				<tr>
		   		<td class="tdfont" width="45%" align="center" >
		   			<div align="right" id="previousButton" style="display: none">
		   				<img class="button"
						src="<his:path src='/hisglobal/images/arrsingle-left.png'/>" tabindex="1"
						border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showPreviousImage();" onclick="showPreviousImage();" title="Show Previous">
		   			</div>
		   		</td>
		   		<td class="tdfont" width="10%" align="center" >
		   		</td>
		   		<td class="tdfont" width="45%" align="center">
		   			<div align="left" id="nextButton">
		   				<img class="button"
						src="<his:path src='/hisglobal/images/arrsingle-right.png'/>" tabindex="1"
						border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showNextImage();" onclick="showNextImage();" title="Show Next">
					</div>		
		   		</td>
		   </tr>
		</table>
	</div>
</div>
