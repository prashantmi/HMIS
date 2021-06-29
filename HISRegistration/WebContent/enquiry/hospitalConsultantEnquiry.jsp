<%try{ %>
<%@page import="enquiry.enquiryConfig"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="enquiry.transaction.controller.fb.HospitalConsultantEnquiryFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<%@page import="registration.*,enquiry.*"%>
<%@page import="enquiry.vo.*,java.util.*" %>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<script>
window.onload=function(){
	document.getElementsByName("consultantName")[0].focus();
	document.getElementsByName("flag")[0].value="0";
}
function submitPage(mode){
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}
function onChangeHospital(obj){
	document.getElementsByName("hospitalName")[0].value=obj.options[obj.selectedIndex].text;
	submitPage("ONCHANGEHOSPITAL");
}
function validateConsultantEnquiry()
{
	submitForm('SEARCH');
}
function getConsultantUnitDetail(mode,departmentUnitCode){
	document.getElementsByName("departmentUnitCode")[0].value=departmentUnitCode;
	document.getElementsByName("flag")[0].value="1";
	submitPage(mode);
}

function getConsultantByEmpNo(hmode,empNo){
	document.getElementsByName("empNo")[0].value=empNo;
	submitPage(hmode);
}

function getConsultantByDept(hmode,departmentCode){
	document.getElementsByName("departmentCode")[0].value=departmentCode;
	submitPage(hmode);
}

function setSelected(obj){
	obj.style.color="#800080";
}
function setDeselected(obj){
	obj.style.color="";
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
</script>

<body>
<his:TransactionContainer>

<logic:equal name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">
<form action="<his:path src="/enquiry/hospitalConsultantEnquiry.cnt"/>" method="post">
</logic:equal>
	<his:TitleTag name="Consultant Enquiry">
	</his:TitleTag>
		<%
			PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPage",fbPage);
				fbPage.setCurrentPage(((HospitalConsultantEnquiryFB)request.getAttribute("hospitalConsultantEnquiryFB")).getCurrentPage());
				fbPage.setObjArrName(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY_VIEW);
				fbPage.setTitleRequired(false);
				fbPage.setAppendInTitle("All Consultant");
				fbPage.setMaxRecords(20);
			%>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" nowrap width="25%">
					<div align="right"><font color="#FF0000"
						size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="hospname" /> </font></div>
				</td>
				<td class="tdfont" width="25%">
					<html:select name="hospitalConsultantEnquiryFB" property="hospitalCode" tabindex="1" styleClass="registrationCmb" onchange="onChangeHospital(this);"> 
						<logic:present
							name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
							<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</td>
				<td width="20%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					 <bean:message key="con" /> <bean:message key="name" />
					</b>
					</font></div>
				</td>
				<td width="20%" class="tdfont">
				<div align="left"><html:text name="hospitalConsultantEnquiryFB"
					maxlength="30" size="30" styleClass="textbox"
					property="consultantName" tabindex="1"
					onkeypress="if(event.keyCode==13) submitPage('SEARCHBYNAME');" /></div>
				</td>
				<td width="10%" class="tdfont">
				<div align="left"><img class="button" src='<his:path src="/hisglobal/images/Search.png"/>' tabindex="1" style="cursor: pointer"
				onkeypress="if(event.keyCode==13) submitPage('SEARCHBYNAME');" onclick="submitPage('SEARCHBYNAME');"></div>
				</td>
			</tr>
		</table>
	</his:ContentTag>

	<his:statusList>
	
	<his:PaginationTag name="fbPage"></his:PaginationTag>	
		<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
					<td class="tdfonthead" width="25%">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="name" /></b> </font></div>
					</td>
					<td class="tdfonthead" width="25%">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="designation" /></b> </font></div>
					</td>
					<td class="tdfonthead" width="35%">
					<div align="left"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="department" /> </b></font></div>
					</td>

					<%--<td class="tdfonthead" width="20%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="unit" /></b> </font></div>
					</td>
					
					--%>
				</tr>
			<%	
			if(session.getAttribute(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY_VIEW)!=null){
			List lst=(List)session.getAttribute(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY_VIEW);
					int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
					int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
					HospitalConsultantEnquiryVO vo=null;
					for(int i=startIndex;i<=endIndex;i++){
						vo=(HospitalConsultantEnquiryVO)lst.get(i);
						pageContext.setAttribute("vo",vo);
			 %>
		
					<tr>
						<td class="tdfont">
							<div align="left">
								<a onclick="getConsultantByEmpNo('CONSULTANTDTL','<%=vo.getEmpNo()%>')" style="cursor:pointer" tabindex="1"
								onkeypress="if(event.keyCode==13) getConsultantByEmpNo('CONSULTANTDTL','<%=vo.getEmpNo()%>')">
								<bean:write name="vo" property="consultantName"/></a>
							</div>
						</td>
						<td class="tdfont">
							<div align="left">
								<bean:write name="vo" property="designation"/>
							</div>
						</td>
						<td class="tdfont">
							<div align="left">
								<a onclick="getConsultantByDept('SEARCHBYDEPT','<%=vo.getDepartmentCode()%>')" style="cursor:pointer" tabindex="1"
								onkeypress="if(event.keyCode==13) getConsultantByDept('SEARCHBYDEPT','<%=vo.getDepartmentCode()%>')">
								<bean:write name="vo" property="department"/></a>
							</div>
						</td>
						<%-- <td class="tdfont">
							<div align="center">
							  <bean:write name="consultantVo" property="departmentUnit"/>
							</div>
						</td>
						--%>
					</tr>
				
				<%}} %>
			</table>
		  </his:ContentTag>
		
	</his:statusList>
	<his:statusTransactionInProcess>
		<his:SubTitleTag name="Consultant Detail"> </his:SubTitleTag>
		<% HospitalConsultantEnquiryVO []consultantDetailVO=(HospitalConsultantEnquiryVO[])session.getAttribute(enquiryConfig.CONSULTANT_DETAIL_VO_ARRAY);%>
		<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
				<tr>	
					<td class="tdfonthead" width="12%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="name" /> </b></font></div>
					</td>
					<td class="tdfont" width="12%">
						<div align="left">
							&nbsp;<%= consultantDetailVO[0].getConsultantName()%></b> 
						</div>
					</td>				
					<td class="tdfonthead" width="12%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="designation" /> </b></font></div>
					</td>
					<td class="tdfont" width="12%">
						<div align="left">
							&nbsp;<%= consultantDetailVO[0].getDesignation()%></b> 
						</div>
					</td>				
					<td class="tdfonthead" width="12%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
<!--						 <bean:message key="onleave" /> </b></font></div>-->
					</td>
					<td class="tdfont" width="12%">
						<div align="left">
<!--							&nbsp;No</b> -->
						</div>
					</td>				
				</tr>
			</table>
		</his:ContentTag>
		
		<%Map consultantVOMap=(HashMap)session.getAttribute(enquiryConfig.CONSULTANT_ENQUIRY_MAP);
		List key=(ArrayList)session.getAttribute(enquiryConfig.CONSULTANT_ENQUIRY_MAP_KEY);
		
		%>
		<logic:iterate id="k" collection="<%=key %>" indexId="index">
		
		<%List consultantVOList= (ArrayList)consultantVOMap.get(key.get(index).toString());
		HospitalConsultantEnquiryVO consultantVO=(HospitalConsultantEnquiryVO)consultantVOList.get(0);
		String department=consultantVO.getDepartment().toString();
		department="Department : "+department; %>
		<his:SubTitleTag name="<%=department %>"> 
		</his:SubTitleTag>	
		<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
				<%String location[]=new String[]{"","","",""};
					if(consultantVO.getLocation()!=null){
					location=consultantVO.getLocation().split("@");
					}
					%>

				<tr>
					<td class="tdfonthead" width="50%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="department" /> <bean:message key="location" />
						 </font></div>
					</td>
					<td class="tdfont" width="50%">
					<div align="left">
						<%=location.length>3?location[3]:" " %>
							<%=location.length>2?","+location[2]:" " %>
							<%=location.length>1?","+location[1]:" " %>
							<%=location.length>0?","+location[0]:" " %>
						 </b>
						</div>
					</td>
				
					<%--
					<td class="tdfonthead" width="25%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="block" /></b> </font></div>
					</td>
					<td class="tdfonthead" width="25%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="floor" /></b> </font></div>
					</td>
					<td class="tdfonthead" width="25%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="room" /></b> </font></div>
					</td>
				--%></tr>
				<%--<tr>
					<td class="tdfont" width="25%">
					<div align="center">
						</b><%=location.length>3?location[3]:" " %> </div>
					</td>
					<td class="tdfont" width="25%">
					<div align="center">
						</b><%=location.length>2?location[2]:" " %> </div>
					</td>
					<td class="tdfont" width="25%">
					<div align="center">
						</b><%=location.length>1?location[1]:" " %> </div>
					</td>
					<td class="tdfont" width="25%">
					<div align="center">
						</b><%=location.length>0?location[0]:" "  %></div>
					</td>
				</tr>
			--%>
			</table>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td class="tdfonthead" width="50%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="unit" /></font></div>
					</td>
					<td class="tdfonthead" width="50%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">
						 <bean:message
						key="location" /> </font></div>
					</td>
				</tr>
				<logic:iterate id="consultantVoList" indexId="idx" collection="<%=consultantVOList %>">
				<%HospitalConsultantEnquiryVO consultantVo=(HospitalConsultantEnquiryVO)consultantVOList.get(idx);%>
					<tr>
						<%String link="/AHIMS/enquiry/hospitalConsultantEnquiry.cnt?hmode=SEARCHCONSULTANTUNITDTL&departmentUnitCode="+ consultantVo.getDepartmentUnitCode();%>
						<td class="tdfont" width="12%">
							<div align="center">
								<a onclick="getConsultantUnitDetail('SEARCHCONSULTANTUNITDTL','<%=consultantVo.getDepartmentUnitCode()%>')" style="cursor:pointer" tabindex="1" 
									onkeypress="if(event.keyCode==13) getConsultantUnitDetail('SEARCHCONSULTANTUNITDTL','<%=consultantVo.getDepartmentUnitCode()%>')" onfocus="setSelected(this)" onblur="setDeselected(this)">
								 <%=consultantVo.getDepartmentUnit() %></a></b> 
							</div>
						</td>
						<td class="tdfont" width="50%">
						<%String unitLocation[]=new String[]{"","","",""};boolean flag=false;
							if(consultantVO.getUnitLocation()!=null){
								unitLocation=consultantVO.getUnitLocation().split("@");
								flag=true;
							}%>
						<div align="center">
							<%if(flag){ %>
							<%=(location.length>0?location[0]:" ") +(location.length>1?", "+location[1]:" " )+ (unitLocation.length>2?", "+unitLocation[2]:" ")+ (unitLocation.length>3?", "+unitLocation[3]:" ") %></b>
							<%} %>
						</div>
						</td>
					</tr>		
			</logic:iterate>
			</table>
			<logic:equal name="hospitalConsultantEnquiryFB" property="flag" value="1">
			<logic:equal name="hospitalConsultantEnquiryFB" property="departmentCode" value="<%=consultantVO.getDepartmentCode() %>">
			<his:statusDone>
			
			<his:ContentTag>

			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<!--<td width="25%">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="department" /></b> </font></div>
					</td>
					<td width="25%">
					<div align="left">
						: <bean:write name="hospitalConsultantEnquiryFB" property="department"/></b> </div>
					</td>
					-->
					<td width="25%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="unit" /> </font></div>
					</td>
					<td  width="25%" class="tdfont">
					<div align="left">
						: <bean:write name="hospitalConsultantEnquiryFB" property="departmentUnit"/></b> </div>
					</td>
				</tr>
			</table>

			<table width="100%" cellspacing="1" cellpadding="0">
				<% //String detail=session.getAttribute(enquiryConfig.UNIT_WORKING_DAYS).toString();
				//String unitRoomDays[]=detail.split("#");
				//if(unitRoomDays.length<1){ unitRoomDays=new String[]{"","",""}; }%>
				<tr>
					<td class="tdfonthead" width="25%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="day" /> </font></div>
					</td>
					<td class="tdfonthead" width="25%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="timing" /> </font></div>
					</td>
					<td class="tdfonthead" width="25%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="room" /> </font></div>
					</td>
				</tr>
				<logic:present name="<%=enquiryConfig.UNIT_WORKING_DAYS %>">
				<logic:iterate id="unitWorkingDetail" name="<%=enquiryConfig.UNIT_WORKING_DAYS %>" type="java.lang.String">
				<%String unitDetail[]=unitWorkingDetail.split("#"); %>
				<tr>
					<td class="tdfont" width="25%">
					<div align="center">
						<%=unitDetail[0] %> </b></div>
					</td>
					<td class="tdfont" width="25%">
					<div align="center">
						<%=unitDetail[1] %></b></div>
					</td>
					<td class="tdfont" width="25%">
					<div align="center">
						<%=(unitDetail.length>2?unitDetail[2]:"") %></b></div>
					</td>
				</tr>
				</logic:iterate>
				</logic:present>
			</table>	
		</his:ContentTag>
		</his:statusDone>
		</logic:equal>
		</logic:equal>	
		</his:ContentTag>
		</logic:iterate>		
		
	</his:statusTransactionInProcess>
	
	<html:hidden name="hospitalConsultantEnquiryFB" property="hmode" />
		<html:hidden name="hospitalConsultantEnquiryFB" property="departmentUnitCode" />
		<html:hidden name="hospitalConsultantEnquiryFB" property="empNo" />
		<html:hidden name="hospitalConsultantEnquiryFB" property="departmentCode" />
		<html:hidden name="hospitalConsultantEnquiryFB" property="flag" />
		<html:hidden name="hospitalConsultantEnquiryFB" property="workingDays" />
		<html:hidden name="hospitalConsultantEnquiryFB" property="workingTimings" />
		<html:hidden name="hospitalConsultantEnquiryFB" property="unitWorkingRoom" />
		<html:hidden name="hospitalConsultantEnquiryFB" property="isDirectCall" />
		<html:hidden name="hospitalConsultantEnquiryFB" property="currentPage"/>
		<html:hidden name="hospitalConsultantEnquiryFB" property="hospitalName"/>
		
	
<logic:equal name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">		
	</form>
</logic:equal>
	<his:ButtonToolBarTag>
		<his:statusList>
		<logic:equal name="hospitalConsultantEnquiryFB" property="isFinalCancelReqd" value="1">
			<logic:equal name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick="submitPage('CANCEL');">
			</logic:equal>
			<logic:notEqual name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitDesk('NEW');" tabindex="1" onclick="submitDesk('NEW');">
			</logic:notEqual>
		</logic:equal>
		<logic:notEqual name="hospitalConsultantEnquiryFB" property="isFinalCancelReqd" value="1">
			<logic:equal name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer"
				onkeypress="if(event.keyCode==13) submitPage('unspecified');" tabindex="1"onclick="submitPage('unspecified');">
			</logic:equal>
			<logic:notEqual name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer"
				onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1"onclick="submitPage('NEW');">
			</logic:notEqual>
		</logic:notEqual>
		</his:statusList>
		<his:statusTransactionInProcess>
		<logic:equal name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer"
			onkeypress="if(event.keyCode==13) submitPage('unspecified');" tabindex="1"onclick="submitPage('unspecified');">
		</logic:equal>
		<logic:notEqual name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer"
			onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1"onclick="submitPage('NEW');">
		</logic:notEqual>
		</his:statusTransactionInProcess>
		<his:statusUnsuccessfull>
			<logic:equal name="hospitalConsultantEnquiryFB" property="isFinalCancelReqd" value="1">
			<logic:equal name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick="submitPage('CANCEL');">
			</logic:equal>
			<logic:notEqual name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitDesk('NEW');" tabindex="1" onclick="submitDesk('NEW');">
			</logic:notEqual>
		</logic:equal>
		<logic:notEqual name="hospitalConsultantEnquiryFB" property="isFinalCancelReqd" value="1">
			<logic:equal name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer"
				onkeypress="if(event.keyCode==13) submitPage('unspecified');" tabindex="1"onclick="submitPage('unspecified');">
			</logic:equal>
			<logic:notEqual name="hospitalConsultantEnquiryFB" property="isDirectCall" value="DIRECT">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer"
				onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1"onclick="submitPage('NEW');">
			</logic:notEqual>
		</logic:notEqual>
		</his:statusUnsuccessfull>
					
		</his:ButtonToolBarTag>
		
		

<his:status/>
</his:TransactionContainer>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();}%>