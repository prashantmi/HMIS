<%try{ %>
<%@page import="enquiry.enquiryConfig,java.util.*"%>
<%@page import="enquiry.vo.*,hisglobal.tools.tag.*"%>
<%@page import="enquiry.transaction.controller.fb.*"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/enquiry/js/opdScheduleEnquiry.js" />
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
<logic:equal name="opdScheduleEnquiryFB" property="isDirectCall" value="DIRECT">
<form action="<his:path src='/enquiry/opdScheduleEnquiry.cnt'/>" method="post">
</logic:equal>
<his:TitleTag name="Opd Schedule Enquiry">
	</his:TitleTag>
	
	<%	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPage",fbPage);
				fbPage.setCurrentPage(((OpdScheduleEnquiryFB)request.getAttribute("opdScheduleEnquiryFB")).getCurrentPage());
				fbPage.setObjArrName(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_VO_VIEW);
				fbPage.setTitleRequired(false);
				fbPage.setAppendInTitle("");
				fbPage.setMaxRecords(15);
			%>
	<%
	String activeUnitFontColor="background-Color:#E0EBEB;  text-align:left; text-transform: capitalize; height:15; font-size: 10px; font-weight: bold;";
	String inActiveUnitFontColor="background-Color:#F1F6F6;  text-align:left; text-transform: capitalize; height:15; font-size: 10px; font-weight: bold;";
	 String style=""; %>
	 
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td class="tdfonthead" width="70%">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="department"/>/<bean:message key="specialClinic"/>
				</font>
			</td>
			<td class="tdfont" width="15%">
				 <div>
				 <html:text name="opdScheduleEnquiryFB" property="searchText" onkeyup="if(event.keyCode!=13) 
				  searchInListBox(event,this)" onmousedown="gotFocus()" onkeypress="if(event.keyCode==13) return getDetail()" onblur="callOnBlur()" tabindex="1" ></html:text>
				 </div>
				 <div id="listDiv" style="display:none;position: absolute;" >
                  <html:select  size="15" multiple="multiple" name="opdScheduleEnquiryFB" property="list"
                   onclick="setValue(this)" onkeypress="if(event.keyCode==13) setValue(this)" onblur="callOnBlurForCombo()">
                  <logic:notEmpty name="<%=enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_LIST %>">
                  <html:options  collection ="<%=enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_LIST %>" property = "value" labelProperty = "label"/>
                  </logic:notEmpty>
                  </html:select>
	            </div>
			</td>
			<td width="15%" class="tdfont">	
					<img class="button" src='<his:path src="/hisglobal/images/Search.png"/>' style="cursor: pointer"
						onkeypress="if(event.keyCode==13) return getDetail();" tabindex="1" onclick="return getDetail();">
			</td>
		 </tr>
	 </table>
	<his:statusList>
	<his:PaginationTag name="fbPage"></his:PaginationTag>
	<his:ContentTag>
	
	<bean:define id="opdScheduleEnquiryDeptVO" name="<%=enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_VO_VIEW %>"></bean:define>
	
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
	<td width="50%">
		<his:SubTitleTag name="Department">
		</his:SubTitleTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<%List lst; %>
		<logic:notEmpty name="opdScheduleEnquiryDeptVO">
		<%	 lst=(List)session.getAttribute(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_VO_VIEW);
		int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
		int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
		for(int i=startIndex;i<=endIndex;i++)
		{
			OpdScheduleEnquiryVO opdScheduleEnquiryVO=(OpdScheduleEnquiryVO)lst.get(i);
		%>
		<bean:define id="deptUnitIsonduty" value="<%=opdScheduleEnquiryVO.getDeptUnitIsonduty()%>"></bean:define>
			
			<tr height="20px">
			<logic:equal name="deptUnitIsonduty" value="<%=enquiryConfig.IS_DEPT_UNIT_ON_DUTY_YES %>">
				<%style=activeUnitFontColor; %>
			</logic:equal>	
			<logic:equal name="deptUnitIsonduty" value="<%=enquiryConfig.IS_DEPT_UNIT_ON_DUTY_NO %>">
				<%style=inActiveUnitFontColor; %>
			</logic:equal>	
				<td style="<%=style %>">
					<div align="center">
					<b><a style="cursor: pointer;" onclick="getUnitDetail('GETUNITDTL','<%=opdScheduleEnquiryVO.getDepartmentCode()%>','<%=opdScheduleEnquiryVO.getDepartment()%>')" 
						tabindex="1" onkeypress="if(event.keyCode==13) getUnitDetail('GETUNITDTL','<%=opdScheduleEnquiryVO.getDepartmentCode()%>','<%=opdScheduleEnquiryVO.getDepartment()%>')"
						onfocus="setSelected(this)" onblur="setDeselected(this)">

						<%=opdScheduleEnquiryVO.getDepartment() %>
					</a></b>
					</div>
				</td>
			</tr>

	<%} %>
	</logic:notEmpty>
	</table>
	</td>	
	<td width="50%">
		<his:SubTitleTag name="Special Clinic">
			</his:SubTitleTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<logic:notEmpty name="<%=enquiryConfig.OPD_SCHEDULE_ENQUIRY_SPECIAL_CLINIC_VO %>">

		<% lst=(List)session.getAttribute(enquiryConfig.OPD_SCHEDULE_ENQUIRY_SPECIAL_CLINIC_VO_VIEW);
		if(lst.size()>0){
		 int startIndex1 = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
		 int endIndex1 = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
		for(int i=startIndex1;i<=endIndex1;i++)
		{
			OpdScheduleEnquiryVO opdScheduleEnquiryVO=(OpdScheduleEnquiryVO)lst.get(i);
		%>
		<bean:define id="deptUnitOnduty" value="<%=opdScheduleEnquiryVO.getDeptUnitIsonduty()%>"></bean:define>
			<tr height="20px">
				<logic:equal  name="deptUnitOnduty" value="<%=enquiryConfig.IS_DEPT_UNIT_ON_DUTY_YES %>">
				<%style=activeUnitFontColor; %>
			</logic:equal>	
			<logic:equal  name="deptUnitOnduty" value="<%=enquiryConfig.IS_DEPT_UNIT_ON_DUTY_NO %>">
				<%style=inActiveUnitFontColor; %>
			</logic:equal>	
			<td style="<%=style %>">
				<div align="center">
					
					<b><a style="cursor: pointer;" onclick="getSpecialClinicDetail('GETSPECIALCLINICDTL','<%=opdScheduleEnquiryVO.getDepartmentUnitCode()%>','<%=opdScheduleEnquiryVO.getDepartment()%>')" 
					 tabindex="1" onkeypress="if(event.keyCode==13) getSpecialClinicDetail('GETSPECIALCLINICDTL','<%=opdScheduleEnquiryVO.getDepartmentUnitCode()%>','<%=opdScheduleEnquiryVO.getDepartment()%>')"
					 onfocus="setSelected(this)" onblur="setDeselected(this)">

						<%=opdScheduleEnquiryVO.getDepartmentUnit()==null?"":opdScheduleEnquiryVO.getDepartmentUnit() %>
					</a></b>
				</div>
			 </td>
			</tr>

	<%}} %>
	</logic:notEmpty>
	</table>
	</td>	
	</tr>
	
	</table>
	
	</his:ContentTag>
	</his:statusList>
	<his:statusDone>
	<%
	  
	  activeUnitFontColor="background-Color:#96BAEA; color:#990000; text-align:left; text-transform: capitalize; height:15; font-size: 10px; font-weight: bold;";
	  inActiveUnitFontColor="background-Color:#E0EBEB; color:#990000; text-align:left; text-transform: capitalize; height:15; font-size: 10px; font-weight: bold;";
	  
	%>
		 <his:ContentTag>
	   <table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="25%" class="tdfonthead">
				<font color="#000000"><bean:message key="department"/></font>
			</td>
			<td width="25%" class="tdfont">
				&nbsp;<b><bean:write name="opdScheduleEnquiryFB" property="department"/></b>
			</td>
			<td width="25%" class="tdfonthead">
				<font color="#000000"><bean:message key="hoddept"/></font>
			</td>
			<td width="25%" class="tdfont">
				&nbsp;<b><bean:write name="opdScheduleEnquiryFB" property="hod"/></b>
			</td>
		</tr>
		</table>
		<his:SubTitleTag name="Unit Working Detail"> 
	    </his:SubTitleTag>
	  
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="20%" class="tdfonthead">
				<div align="center" style="border-top: outset black 2px;border-bottom: inset black 2px;">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="unit"/></font>
				</div>
			</td>
			<td width="62%">
			<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
			<td width="25%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="day"/>
				</font>
				</div>
			</td>
			<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="timing"/>
				</font>
				</div>
			</td>
			<td width="35%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="room"/>
				</font>
				</div>
			</tr>
			</table>
			</td>
			
			<td width="18%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="con"/>
				<bean:message key="detail"/>
				</font>
				</div>
			</td>
		</tr>
		<logic:present name="<%=enquiryConfig.OPD_SCHEDULE_ENQUIRY_DETAIL_VO %>"></logic:present>
		<logic:iterate id="opdscheduleEnquiryVO" name="<%=enquiryConfig.OPD_SCHEDULE_ENQUIRY_DETAIL_VO %>" type="enquiry.vo.OpdScheduleEnquiryVO">
			<logic:equal  name="opdscheduleEnquiryVO" property="deptUnitIsonduty" value="<%=enquiryConfig.IS_DEPT_UNIT_ON_DUTY_YES %>">
				<%style=activeUnitFontColor; %>
			</logic:equal>	
			<logic:equal  name="opdscheduleEnquiryVO" property="deptUnitIsonduty" value="<%=enquiryConfig.IS_DEPT_UNIT_ON_DUTY_NO %>">
				<%style=inActiveUnitFontColor; %>
			</logic:equal>
			<bean:define id="unitworkingMap" name="<%=enquiryConfig.OPD_SCHEDULE_ENQUIRY_UNIT_WORKING_DETAIL %>" type="java.util.LinkedHashMap"></bean:define>
			<%List unitWorkingDetailList=(List)unitworkingMap.get(opdscheduleEnquiryVO.getDepartmentUnit());%> 
			
			<tr height="20px" >
				<td style="<%=style %>">
					<div align="center">
						<bean:write name="opdscheduleEnquiryVO" property="departmentUnit"/>
					</div>
				</td>
				<%if(unitWorkingDetailList.size()==0){%>
					<td width="62%" class="tdfont">
				<%}else{ %>
					<td width="62%">
				<%} %>
				<div align="center">
				<table width="100%" cellspacing="1" cellpadding="0">
				<logic:iterate id="unitWorkingDetail" collection="<%=unitWorkingDetailList%>" type="java.lang.String" indexId="index">
				<%String unitDetail[]=unitWorkingDetail.split("#"); %>
				<tr>
					<td width="25%" class="tdfont">
						<div align="center">
							<%=unitDetail[0] %>
						</div>
					</td>
					<td width="40%" class="tdfont">
						<div align="center">
							<%=unitDetail[1] %>
						</div>
					</td>
					<td width="35%" class="tdfont">
						<div align="center">
							<%=unitDetail[2] %>
						</div>
					</td>
				</tr>
				</logic:iterate>
				</table>
				</div>
				</td>
				
				<td class="tdfont">
				<div align="center">
<!--				<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' tabindex="1" -->
				<a style="cursor: pointer" onkeypress="if(event.keyCode==13) getConsultant(event,'<%=opdscheduleEnquiryVO.getDepartmentUnitCode() %>',
				'<%=opdscheduleEnquiryVO.getDepartmentUnit() %>');" onfocus="setSelected(this)" onblur="setDeselected(this)"
				 tabindex="1" onclick="getConsultant(event,'<%=opdscheduleEnquiryVO.getDepartmentUnitCode() %>','<%=opdscheduleEnquiryVO.getDepartmentUnit() %>');">
				 View</a>
				</div>
				</td>
			</tr>
			
		</logic:iterate>
	</table>
	
	</his:ContentTag>
	
	</his:statusDone>
	<html:hidden name="opdScheduleEnquiryFB" property="departmentCode"/>
	<html:hidden name="opdScheduleEnquiryFB" property="departmentUnitCode"/>
	<html:hidden name="opdScheduleEnquiryFB" property="departmentUnit"/>
	<html:hidden name="opdScheduleEnquiryFB" property="department"/>
	<html:hidden name="opdScheduleEnquiryFB" property="hmode"/>
	<html:hidden name="opdScheduleEnquiryFB" property="hod"/>
	<html:hidden name="opdScheduleEnquiryFB" property="currentPage"/>
	<html:hidden name="opdScheduleEnquiryFB" property="isDirectCall"/>
	<html:hidden name="opdScheduleEnquiryFB" property="flag"/>
	

<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' onclick="showLegends();">		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' onclick="showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
	<div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" cellpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="5%" style="<%=activeUnitFontColor %>">
					
				</td>
				<td width="5%"></td>
				<td width="90%">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Department/Unit is working
					</font>
					</div>
				</td>				
			</tr>
			<tr>
				<td width="5%" style="<%=inActiveUnitFontColor%>"></td>
				<td width="5%"></td>
				<td width="90%">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Department/Unit is not working
					</font>		
					</div>
				</td>				
			</tr>
		</table>
	</his:ContentTag>
	</div>
	
<logic:equal name="opdScheduleEnquiryFB" property="isDirectCall" value="DIRECT">
</form>
</logic:equal>


<his:ButtonToolBarTag>
	<his:statusList>
	<logic:equal name="opdScheduleEnquiryFB" property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick="submitPage('CANCEL');">
	</logic:equal>
	<logic:notEqual name="opdScheduleEnquiryFB" property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitDesk('NEW');" tabindex="1" onclick="submitDesk('NEW');">
	</logic:notEqual>
	</his:statusList>
	<his:statusDone>
	<logic:equal name="opdScheduleEnquiryFB" property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('unspecified');" tabindex="1" onclick="submitPage('unspecified');">
	</logic:equal>
	<logic:notEqual name="opdScheduleEnquiryFB" property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1" onclick="submitPage('NEW');">
	</logic:notEqual>
	</his:statusDone>
	<his:statusUnsuccessfull>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('unspecified');" tabindex="1" onclick="submitPage('unspecified');">
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>
<his:status />
</his:TransactionContainer>
</body>
</html>
<%} catch(Exception e){e.printStackTrace();}%>