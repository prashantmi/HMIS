<%@page import="enquiry.enquiryConfig"%>
<%@page import="enquiry.vo.*"%>
<%@page import="enquiry.transaction.controller.fb.HospitalDepartmentEnquiryFB"%>
<%@page import="registration.RegistrationConfig"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/enquiry/js/hospitalDeptEnquiry.js" />
<script>
function setSelected(obj){
	obj.style.color="#800080";
}
function setDeselected(obj){
	obj.style.color="";
}
</script>

<body>
<his:TransactionContainer>

<form action="<his:path src='/enquiry/hospitalDepartmentEnquiry.cnt' />" method="post">


<his:TitleTag name="Department Enquiry">
	</his:TitleTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td class="tdfonthead" width="70%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif" >			
					<bean:message key="department" />
				</font>	
			</td>
			<td class="tdfont" width="15%">
				 <div>
				 <html:text name="HospitalDepartmentEnquiryFB" property="searchText" onkeyup="if(event.keyCode!=13)
				  searchInListBox(event,this)" onmousedown="gotFocus()" onkeypress="if(event.keyCode==13) return getDetail()" onblur="callOnBlur()" tabindex="1"  ></html:text>
				 </div>
				 
				 <div id="listDiv" style="display:none;position: absolute;" >
                  <html:select  size="15" name="HospitalDepartmentEnquiryFB" property="list" 
                   onclick="setValue(this)" onkeypress="if(event.keyCode==13) setValue(this)" onblur="callOnBlurForCombo()">
                  <html:options collection ="<%=enquiryConfig.ALL_DEPT_LIST %>" property = "value" labelProperty = "label"/>
                  </html:select>
	            </div>
			</td>
			<td width="15%" class="tdfont">	
					<img class="button" src='<his:path src="/hisglobal/images/Search.png"/>' tabindex="1" style="cursor: pointer"
						onkeypress="if(event.keyCode==13) return getDetail();" tabindex="1" onclick="return getDetail();">
			</td>
		 </tr>
	 </table>
	
	<his:statusList>
	<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<bean:define id="allDepartmentTypeMap" name="<%=enquiryConfig.DEPARTMENT_ANDDEPARTMENT_TYPE_MAP_VIEW %>" type="java.util.LinkedHashMap"></bean:define>
		<% int tdWidth=allDepartmentTypeMap.size();
		if(tdWidth>0){
			tdWidth=100/tdWidth;
		}	
		String oddFontColor="background-Color:#F1ECE2; text-align:left; text-transform: capitalize; height:15; font-size: 10px; font-weight: bold;";
		String evenFontColor="background-Color:#FFEBD5; text-align:left; text-transform: capitalize; height:15; font-size: 10px; font-weight: bold;";
		%>
		<logic:iterate indexId="idx" id="departmentType" name="allDepartmentTypeMap" type="java.util.Map.Entry">
		<%int indexValue=idx.intValue(); 
		String fontColor=oddFontColor;
		if(indexValue%2==0)
			fontColor=evenFontColor;
			
		%>
		<td style=position: valign="top"  width="<%=tdWidth %>%" >
		<bean:define id="departmentTypeMapKey" name="departmentType" property="key" type="java.lang.String"></bean:define>
		<bean:define id="departmentList" name="departmentType" property="value" type="java.util.ArrayList"></bean:define>
		<%
			String[] arrayCodeDeptType=departmentTypeMapKey.split("#");
			%>
			<his:SubTitleTag name="<%=arrayCodeDeptType[1] %>">
			
			</his:SubTitleTag>
			
			<his:ContentTag>
			
				<table width="100%" cellspacing="1" cellpadding="0">
				<logic:iterate id="department" name="departmentList" type="java.lang.String">
				<tr style="height: 15px;">
					<td style="<%=fontColor %>">
					<%
					String[] temp=(department.split("#"));
					
					String link="/hospitalDepartmentEnquiry.cnt?hmode=DEPTDTL&departmentCode="+temp[0].trim()+"&departmentTypeCode="+arrayCodeDeptType[0];
					//System.out.println("temp[0]"+temp[0]);
					//System.out.println("temp[1]"+temp[1]);
					//System.out.println("temp size"+temp.length);
					if(temp.length>1)
					{
					%>
					
					<a style='cursor:pointer;' onclick="submitForm('DEPTDTL','<%=temp[0].trim() %>','<%=arrayCodeDeptType[0] %>')" 
						onkeypress="if(event.keyCode==13) submitForm('DEPTDTL','<%=temp[0].trim() %>','<%=arrayCodeDeptType[0] %>')" tabindex="1" onfocus="setSelected(this)" onblur="setDeselected(this)">
						<%=temp[1] %></a>
					<%} %>
					</td>
				</tr>
			
				</logic:iterate>
				</table>
			
			
			</his:ContentTag>
		</td>
		</logic:iterate>
		
	</tr>
	</table>
	
	</his:ContentTag>
	</his:statusList>
	
	<his:statusTransactionInProcess>
		<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	   <tr>
		<td width="50%" class="tdfonthead">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif" >			
			<bean:message key="department" />
		</font>			
		</td>
		<td width="50%" class="tdfont">
		<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
			&nbsp;<bean:write name="HospitalDepartmentEnquiryFB" property="department"/>
		</font>
		</td>
	</tr>
	   <tr>
		<td width="50%" class="tdfonthead">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">			
				<bean:message key="deptShortName" />
		</font>			
		</td>
		<td width="50%" class="tdfont">
		<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
			&nbsp;<bean:write name="HospitalDepartmentEnquiryFB" property="departmentShort"/>
		</font>
		</td>
	</tr>
	<tr>
		<td width="50%"  class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		
				<bean:message key="hoddept" />
			</font>		
		</td>
		<td width="50%" class="tdfont">
		<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
			&nbsp;<bean:write name="HospitalDepartmentEnquiryFB" property="hod"/>
		</font>
		</td>
	</tr>
	<tr>
		<td width="50%"  class="tdfonthead">
			<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="location" /></font>
			</div>		
		</td>
		<%-- <td width="25%" class="tdfonthead">
			<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		
			<bean:message key="block" /></font>
			</div>		
		</td>
		<td width="25%" class="tdfonthead">
			<div align="center"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		
			<bean:message key="floor" /></font>
			</div>	
		</td>
		<td width="25%" class="tdfonthead">
			<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">		
			<bean:message key="room" /></font>
			</div>		
		</td>
		--%>
		<td class="tdfont" width="50%">
		<div align="left">
			&nbsp;<bean:write name="HospitalDepartmentEnquiryFB" property="locationBuilding" />, 
			<bean:write name="HospitalDepartmentEnquiryFB" property="locationBlock" />, 
			<bean:write name="HospitalDepartmentEnquiryFB" property="locationFloor" />, 
			<bean:write name="HospitalDepartmentEnquiryFB" property="locationRoom" />
		</div>
		</td>
	</tr> 
	<%--
	<tr>
		<td class="tdfont">
		<div align="center">
			<bean:write name="HospitalDepartmentEnquiryFB" property="locationBuilding" />", "
			<bean:write name="HospitalDepartmentEnquiryFB" property="locationBlock" />", "
			<bean:write name="HospitalDepartmentEnquiryFB" property="locationFloor" />", "
			<bean:write name="HospitalDepartmentEnquiryFB" property="locationRoom" />
		</div>
		</td>
		
		<td class="tdfont">
		<div align="center">
		<bean:write name="HospitalDepartmentEnquiryFB" property="locationBlock" />
		</div>
		</td>
		<td class="tdfont">
		<div align="center">
		<bean:write name="HospitalDepartmentEnquiryFB" property="locationFloor" />
		</div>
		</td>
		<td class="tdfont">
		<div align="center">
		<bean:write name="HospitalDepartmentEnquiryFB" property="locationRoom" />
		</div>
		</td>
	</tr>--%>
	
	</table>
	</his:ContentTag>
	
	
	<logic:equal  name="HospitalDepartmentEnquiryFB"  property="departmentTypeCode" value="<%=RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE %>">
	<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<td class="tdfonthead" width="50%">
			<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b><bean:message key="general"/> <bean:message key="unit"/></b>
			</font>
			</div>
		</td>
		<td class="tdfonthead" width="50%">
			<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b><bean:message key="specialClinic"/></b>
			</font>
			</div>
		</td>
	</tr>
	</table>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<td class="tdfonthead" width="25%">
			<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="unit"/>
			</font>
			</div>
		</td>
		<td class="tdfonthead" width="25%">
			<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="hou"/>
			</font></div>
		</td>
		<td class="tdfonthead" width="25%">
			<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="unit"/>
			</font></div>
		</td>
		<td class="tdfonthead" width="25%">
			<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="hou"/>
			</font></div>
		</td>
	</tr>
	</table>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
	<td width="50%">
	<table width="100%" cellspacing="1" cellpadding="0">
	<logic:iterate id="departmentEnquiryVO" name="<%=enquiryConfig.GENERAL_UNIT_LIST %>" type="enquiry.vo.HospitalDepartmentEnquiryVO">
		<tr style="height: 15px;">
		  <td width="50%" class="tdfont">
				<div align="center">
				<a style="cursor: pointer;" title="Get Unit Detail" onclick="submitFormForUnit('UNITDTL','<%=departmentEnquiryVO.getDepartmentUnitCode() %>','<%=departmentEnquiryVO.getDepartmentUnit() %>')"
					onkeypress="if(event.keyCode==13) submitFormForUnit('UNITDTL','<%=departmentEnquiryVO.getDepartmentUnitCode() %>','<%=departmentEnquiryVO.getDepartmentUnit() %>')" tabindex="1" onfocus="setSelected(this)" onblur="setDeselected(this)">
					<bean:write name="departmentEnquiryVO" property="departmentUnit" /></a>
				
				</div>
			</td>
			<td width="50%" class="tdfont">
				<div align="center">
				<bean:write name="departmentEnquiryVO" property="hou"/>
				</div>
			</td>
			
		</tr>
		</logic:iterate>
		</table>
		</td>
		<td widht="50%">
		<table width="100%" cellspacing="1" cellpadding="0">
			<logic:iterate id="departmentEnquiryVO" name="<%=enquiryConfig.SPECIAL_UNIT_LIST %>" type="enquiry.vo.HospitalDepartmentEnquiryVO">
			<tr style="height: 15px;">
			<td width="50%" class="tdfont">
				<div align="center">
				<a style="cursor: pointer;" title="Get Unit Detail" onclick="submitFormForUnit('UNITDTL','<%=departmentEnquiryVO.getDepartmentUnitCode() %>','<%=departmentEnquiryVO.getDepartmentUnit() %>')"><bean:write name="departmentEnquiryVO" property="departmentUnit" /></a>
				
				</div>
			</td>
			<td width="50%" class="tdfont">
				<div align="center">
				<bean:write name="departmentEnquiryVO" property="hou"/>
				</div>
			</td>
		</tr>
	</logic:iterate>
	</table>
	</td>
	</tr>
	</table>
	</his:ContentTag>
	</logic:equal>
	
	<logic:equal  name="HospitalDepartmentEnquiryFB"  property="departmentTypeCode" value="<%=RegistrationConfig.DEPT_TYPE_PARA_CLINICAL_VALUE %>">
	<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<td class="tdfonthead" width="33%">
		<div align="center"><font color="#000000" size="2"
		face="Verdana, Arial, Helvetica, sans-serif">
		<b><bean:message key="labName" /></b> </font></div>
		</td>
		<td class="tdfonthead" width="33%">
		<div align="center"><font color="#000000" size="2"
		face="Verdana, Arial, Helvetica, sans-serif">
		<b><bean:message key="labType" /></b> </font></div>
		</td>
		<td class="tdfonthead" width="33%">
		<div align="center"><font color="#000000" size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
		key="labIncharge" /> </b></font></div>
		</td>
	
	</tr>
	
		
	<logic:iterate id="departmentEnquiryVO" name="<%=enquiryConfig.ARRAY_DEPARTMENT_ENQUIRY_VO %>" type="enquiry.vo.HospitalDepartmentEnquiryVO">
		<tr>
		<%
		
		String unitLink="/hospitalDepartmentEnquiry.cnt?hmode=UNITDTL&departmentUnitCode="+departmentEnquiryVO.getLabCode();
		%>
			<td  class="tdfont">
				<div align="center">

				<bean:write name="departmentEnquiryVO" property="labName" /> 

				
				</div>
			</td>
			<td  class="tdfont">
				<div align="center">
				<bean:write name="departmentEnquiryVO" property="labType"/>
				</div>
			</td>
			<td  class="tdfont">
				<div align="center">
				<bean:write name="departmentEnquiryVO" property="labIncharge"/>
				</div>
			</td>
		
		</tr>
	</logic:iterate>
	
	</table>
	</his:ContentTag>
	</logic:equal>
	</his:statusTransactionInProcess>
	<his:statusRecordFound>
	<his:SubTitleTag name="Unit Detail">
	</his:SubTitleTag>
	<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	<bean:define id="unitEnquiryDetailVO" name="<%=enquiryConfig.DEPARTMENT_UNIT_ENQUIRY_DETAIL_VO %>" type="enquiry.vo.HospitalDepartmentEnquiryVO"></bean:define>
	<tr>
		<td width="50%"  class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="unit" />
			</font>
		</td>
		<td class="tdfont">
		<font  size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:write name="HospitalDepartmentEnquiryFB" property="departmentUnit"/></font>
		</td>
		</tr>
	<%--
	<tr>
	
	
		<td width="50%"  class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="workingDays" />
			</font>
		</td>
		<td class="tdfont">
		<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:write name="HospitalDepartmentEnquiryFB" property="workingDays"/></font>
		</td>
		</tr>
		<tr>
		<td width="50%"  class="tdfonthead">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="timing" /></font>
		</td>
		<td class="tdfont">
		<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<bean:write name="HospitalDepartmentEnquiryFB" property="timing"/></font>
		</td>
	</tr>
	--%>
	<tr>
		<td width="50%" class="tdfonthead">
			<div align="right"> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="location" /></font>
			</div>
		</td>
		<%--<td width="25%" class="tdfonthead">
			<div align="center"> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="block" /></font>
			</div>
		</td>
		<td width="25%" class="tdfonthead">
		<div align="center"> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="floor" /></font>
		</div>	
		</td>	
		<td width="25%" class="tdfonthead">
		<div align="center"> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="room" /></font>
		</div>	
		</td>
		
	--%>
		<td width="50%" class="tdfont">
			<div align="left">
				<c:if test="${unitEnquiryDetailVO.locationBuilding!=null}">
					<c:out value="${unitEnquiryDetailVO.locationBuilding}"></c:out>
				</c:if>
				<c:if test="${unitEnquiryDetailVO.locationBlock!=null}">
					, <c:out value="${unitEnquiryDetailVO.locationBlock}"></c:out>
				</c:if>
				<c:if test="${unitEnquiryDetailVO.locationFloor!=null}">
					, <c:out value="${unitEnquiryDetailVO.locationFloor}"></c:out>
				</c:if>
			</div>
		</td>
	</tr>
	<%--
	
	<tr>
		<td width="25%" class="tdfont">
			<div align="center"> 
				<bean:write name="unitEnquiryDetailVO" property="locationBuilding"/>
			</div>
		</td>
		<td width="25%" class="tdfont">
			<div align="center"> 
				<bean:write name="unitEnquiryDetailVO" property="locationBlock"/>
			</div>	
		</td>
		<td width="25%" class="tdfont">
		<div align="center"> 
		<bean:write name="unitEnquiryDetailVO" property="locationFloor"/>
		</div>
		</td>
		<td width="25%" class="tdfont">
		<div align="center"> 
			<bean:write name="HospitalDepartmentEnquiryFB" property="room"/>
		</div>
		</td>
		
	</tr>
	--%>
	</table>
		
	<table width="100%" cellspacing="1" cellpadding="0">
		<!--<tr>
			<td width="33%" class="tdfonthead" colspan="3">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>Unit Working Detail</b>
				</font>
				</div>
			</td>	
		</tr>
		--><tr>
			<td width="30%" class="tdfonthead">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				Days</font>
				</div>
			</td>
			<td width="35%" class="tdfonthead">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				Timing</font>
				</div>
			</td>
			<td width="35%" class="tdfonthead">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				Room</font>
				</div>
			</td>
		</tr>	
		<logic:present name="<%=enquiryConfig.DEPARTMENT_UNIT_WORKING_DAYS %>" >
		<logic:iterate id="workingDetailList" name="<%=enquiryConfig.DEPARTMENT_UNIT_WORKING_DAYS %>" type="java.lang.String">
			<%String detail[]=workingDetailList.split("#");
			%>
			
			<tr>
			<td class="tdfont">
				<div align="center">
				<b><%=detail[0] %></b>
				</div>
			</td>
			<td class="tdfont">
				<div align="center">
				<b><%=detail[1] %></b>
				</div>
			</td>
			<td class="tdfont">
				<div align="center">
				<b><%=detail[2] %></b>
				</div>
			</td>
		</tr>	
		
		</logic:iterate>
		</logic:present>
	</table>
	<%--
	
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		<td width="33%" style="vertical-align: top; ">
			 <his:ContentTag>
			 <table width="100%" cellspacing="1" cellpadding="0">
			 <tr>
				<td width="50%" class="tdfonthead" colspan="2">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Consultant Detail</font>
					</div>
				</td>
				
			 </tr>
			 <logic:present name="<%= enquiryConfig.DEPARTMENT_UNIT_CONSULTANT_ENQUIRY_DETAIL_VO%>">
			  <% HospitalDepartmentEnquiryVO [] deptUnitConsultantEnquiryVO=(HospitalDepartmentEnquiryVO[])session.getAttribute(enquiryConfig.DEPARTMENT_UNIT_CONSULTANT_ENQUIRY_DETAIL_VO);
			  	if(deptUnitConsultantEnquiryVO.length>1){
			  	for(int i=0;i<deptUnitConsultantEnquiryVO.length;i++){
			  %>
			  	<tr>
				<td width="50%" class="tdfont">
					<div align="center">
					<%=deptUnitConsultantEnquiryVO[i].getUnitConsultant()%>
					</div>
				</td>
					<%if(deptUnitConsultantEnquiryVO.length>(i+1) ){ %>
				<td width="50%" class="tdfont">
				<div align="center">
					<%=deptUnitConsultantEnquiryVO[++i].getUnitConsultant()%>
				</div>
				</td>
				<%}else{ %>
				<td width="50%" class="tdfont">			
				</td>
				<%} %>
				</tr>
				<%}}else{ %>
				  <tr>
					<td width="100%" class="tdfont">
					<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<%=deptUnitConsultantEnquiryVO[0].getUnitConsultant()%>
					</font>	
					</td>
				   </tr>
				   <%} %>	
				</logic:present>
				<logic:notPresent name="<%= enquiryConfig.DEPARTMENT_UNIT_CONSULTANT_ENQUIRY_DETAIL_VO%>">
					<tr><td width="100%" class="tdfont">
					<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
					No Consultant found</font>
					</td></tr>
				</logic:notPresent>
			 </table>
			 </his:ContentTag>
					
		</td>
		</tr>
		</table>
	--%></his:ContentTag>	
	<his:SubTitleTag name="Ward Detail">
	</his:SubTitleTag>		
	 <his:ContentTag>
		 <table width="100%" cellspacing="1" cellpadding="0">
		
		 <logic:present name="<%=enquiryConfig.DEPARTMENT_UNIT_WARD_ENQUIRY_DETAIL_VO %>">
			 <tr>
				<td width="30%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="ward"/>
					</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="wardType"/>
					</font>
					</div>
				</td>
				<td width="45%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="location"/>
					</font>
					</div>
				</td>
			</tr>
			 <logic:iterate indexId="idx" id="unitWardEnquiryDetailVO" name="<%=enquiryConfig.DEPARTMENT_UNIT_WARD_ENQUIRY_DETAIL_VO %>" type="enquiry.vo.HospitalDepartmentEnquiryVO">
				<tr> 
				<%
				String unitCode=(String)((HospitalDepartmentEnquiryFB)pageContext.findAttribute("HospitalDepartmentEnquiryFB")).getDepartmentUnitCode();
				String modPopUp=unitWardEnquiryDetailVO.getWardCode()+"^ ^ ^"+unitCode+"^";
				String imagePath="/AHIMS";
				String wardLink="/AHIMS/enquiry/hospitalDepartmentEnquiry.cnt?hmode=BEDSTATUS&wardCode="+unitWardEnquiryDetailVO.getWardCode()+"&departmentUnitCode="+unitCode+"&modPopUp="+modPopUp+"&imagepath="+imagePath;
				%>
				<td class="tdfont">
				<div align="center">
				<a style="cursor: pointer;" onclick="openPopup('<%=wardLink %>',event,200,300)"
					tabindex="1" onkeypress="if(event.keyCode==13) openPopup('<%=wardLink %>',event,200,300)"
					onfocus="setSelected(this)" onblur="setDeselected(this)">
				<bean:write name="unitWardEnquiryDetailVO" property="ward"/></a>
				</div>
				</td>
				<td class="tdfont">
					<div align="center">
					<b><bean:write name="unitWardEnquiryDetailVO" property="wardType"/></b>
					</div>
				</td>
				<td class="tdfont">
					<div align="center">
					<c:if test="${unitWardEnquiryDetailVO.locationBuilding!=null}">
						<c:out value="${unitWardEnquiryDetailVO.locationBuilding}"></c:out>			
					</c:if>
					<c:if test="${unitWardEnquiryDetailVO.locationBlock!=null}">
						, <c:out value="${unitWardEnquiryDetailVO.locationBlock}"></c:out>			
					</c:if>
					<c:if test="${unitWardEnquiryDetailVO.locationFloor!=null}">
						, <c:out value="${unitWardEnquiryDetailVO.locationFloor}"></c:out>			
					</c:if>
					<b>
					</b>
					</div>
				</td>
			  </tr>
				</logic:iterate>
			</logic:present>
			 <logic:notPresent name="<%=enquiryConfig.DEPARTMENT_UNIT_WARD_ENQUIRY_DETAIL_VO %>">
			 	<tr><td class="tdfont">
			 	<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 	No ward found
			 	</font>
			 	</td></tr>
			 </logic:notPresent>
		</table>
	</his:ContentTag>
	</his:statusRecordFound>

	<html:hidden name="HospitalDepartmentEnquiryFB" property="departmentCode"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="departmentTypeCode"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="departmentUnitCode"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="departmentUnit"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="hmode"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="department"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="departmentShort"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="location"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="locationRoom"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="locationBlock"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="locationFloor"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="locationBuilding"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="hod"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="workingDays"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="timing"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="wardCode"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="room"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="flag"/>
	<html:hidden name="HospitalDepartmentEnquiryFB" property="isDirectCall"/>
	

</form>

<his:ButtonToolBarTag>
	<logic:equal name="HospitalDepartmentEnquiryFB" property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick="submitForm('CANCEL');">
	</logic:equal>
	<logic:notEqual name="HospitalDepartmentEnquiryFB" property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick="submitForm('NEW');">
	</logic:notEqual>
</his:ButtonToolBarTag>
<his:status />
</his:TransactionContainer>
</body>
</html>