<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.utility.patientAuditLog.PatientAuditLogMstFB"%>
<%@page import="java.util.Map"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/patientAuditLog/patientAuditLogMst.js" />
<script><!--

--></script>

<%try{ %>	
<%String errorMessage="";%> 
	<%String sysdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>
<html:form action="/patientAuditLogMst">	   
		   
   <his:TitleTag name="Patient Audit Log"> 		
   </his:TitleTag>
   
<his:statusNew>
<his:ContentTag>
	<his:InputCrNoTag name="patientAuditLogMstFB">
	</his:InputCrNoTag>
</his:ContentTag>
</his:statusNew>

<his:statusTransactionInProcess>
<jsp:include page="/registration/patientDetail.cnt" flush="true"></jsp:include>
<his:SubTitleTag name="Audit Log Option">
		 <td>
			<div align="right">
<!--			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		-->
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="dateWise"/></b>
			 </font>
			 <html:radio property="isDateWise" value="1" tabindex="1" onclick="showDateDiv(this)"/>
					<b><bean:message key="yes"/></b>
				<html:radio property="isDateWise" value="0" tabindex="1" onclick="showDateDiv(this)"/>
					<b><bean:message key="no"/></b>
			</div>		
		  </td>
	
</his:SubTitleTag>
<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		
		<tr>
			<td width="50%" class="tdfonthead">
			<div align="center">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="modulename"/></b>
			 </font>
			</div>
		  </td>
		   <td width="45%" class="tdfonthead">
			<div align="center">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="auditHeader"/></b>
			 </font>
			 </div>
		  </td>
		   <td width="5%" class="tdfonthead">
			
		  </td>
		 </tr> 
		  <tr>
		  <td width="50%" class="tdfont">
			<div align="center">
			&nbsp;<html:select name="patientAuditLogMstFB" property="moduleId" tabindex="1" onchange="getAuditLogHeader(this)" styleClass="regcbo" style="width:170px">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=Config.MASTER_VERIFICATION_MODULE_LIST %>">
				<html:options collection="<%=Config.MASTER_VERIFICATION_MODULE_LIST %>" labelProperty="label" property="value"/>
				</logic:present>
			</html:select>
			</div>
		  </td>
		
		 <td width="45%" class="tdfont">
		 	<div align="center">
			&nbsp;<html:select name="patientAuditLogMstFB" property="auditLogId" tabindex="1" styleClass="regcbo" style="width:170px">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=Config.PATIENT_AUDIT_LOG_OPTIONS %>">
				<html:options collection="<%=Config.PATIENT_AUDIT_LOG_OPTIONS %>" labelProperty="label" property="value"/>
				</logic:present>
			</html:select>
			</div>
		  </td>
		  <td width="5%" class="tdfont">
		  	<div align="center">
			<img class="button" id="addButton" style="cursor: pointer;" 
			src="/HISInvestigationG5/hisglobal/images/RoundPlus12x12.png" tabindex="1" 
			title="Add to List" onclick="addAuditHeader()" onkeypress="if(event.keyCode==13) addAuditHeader()">
			</div>
		  </td>
		</tr>  
	<logic:present name="<%=Config.AUDIT_HEADER_LIST %>">
	 <logic:iterate id="auditLogMstVO" name="<%=Config.AUDIT_HEADER_LIST %>" indexId="idx" type="hisglobal.vo.PatientAuditLogMstVO">
	  <tr>
		  <td width="50%" class="tdfont">
			<div align="center">
				<bean:write name="auditLogMstVO" property="moduleName"/>
			</div>	
		  </td>
		
		 <td width="45%" class="tdfont">
			<div align="center">
				<bean:write name="auditLogMstVO" property="auditHeader"/>
			</div>	
		  </td>
		  <td width="5%" class="tdfont">
			<div align="center">
			<img class="button" id="addButton" style="cursor: pointer;" 
			src="/HISInvestigationG5/hisglobal/images/RoundMinus12x12.png" tabindex="1" 
			title="Add to List" onclick="removeAuditHeader('<%=auditLogMstVO.getModuleId()+'#'+auditLogMstVO.getAuditLogId()%>');"
			onkeypress="if(event.keyCode==13) removeAuditHeader('<%=auditLogMstVO.getModuleId()+'#'+auditLogMstVO.getAuditLogId()%>');">
			</div>
		  </td>
		</tr>  
		</logic:iterate>
	</logic:present>
	</table>

	<div id="dateWiseDiv" style="display: none">
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="25%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="fromDate"/>
			 </font>
		  </td>
		 <td width="25%" class="tdfont">
			&nbsp;<his:date name="fromDate" value="<%=sysdate %>"></his:date>
		  </td>
		 <td width="25%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 	<bean:message key="toDate"/>
			 </font>
		  </td>
		 <td width="25%" class="tdfont">
			&nbsp;<his:date name="toDate" value="<%=sysdate %>"></his:date>
		  </td>
		</tr>
		</table>
	</div>
	
	</his:ContentTag>

</his:statusTransactionInProcess>

<his:statusList>
<!-- Get the map which contains the audit log header as key and the detail of the audit header which is in form of map -->
	
	<%//List auditHeaderList=(List)session.getAttribute(Config.AUDIT_HEADER_LIST); %>
	<logic:iterate id="auditHeader" name="<%=Config.AUDIT_HEADER_LIST %>" type="hisglobal.vo.PatientAuditLogMstVO" >
	<!-- get the main list which contains the list of column data (ie row in form of list)-->
	<%//List list=(List)session.getAttribute(Config.AUDIT_LOG_CURRENT_RECORD_ROW_LIST); %>
	<%-- <bean:define id="list" name="<%=Config.AUDIT_LOG_CURRENT_RECORD_ROW_LIST %>" type="java.util.List"></bean:define>
	--%>
	<%errorMessage="";
	Map auditDataMap=null;
	auditDataMap=(Map)session.getAttribute(auditHeader.getModuleId()+"#"+auditHeader.getAuditLogId()); %>
	<%List list=null;
		list=(List)auditDataMap.get(Config.AUDIT_LOG_CURRENT_RECORD_ROW_LIST); %>
	<%session.setAttribute(Config.AUDIT_LOG_COLUMN_NAME_LIST,auditDataMap.get(Config.AUDIT_LOG_COLUMN_NAME_LIST));
	//session.setAttribute(Config.AUDIT_LOG_ROW_LIST,auditDataMap.get(Config.AUDIT_LOG_ROW_LIST));
	pageContext.setAttribute("list",list);
	List auditLogRecordList=(List)auditDataMap.get(Config.AUDIT_LOG_ROW_LIST);
	%>
	<%PatientAuditLogMstFB fb=(PatientAuditLogMstFB)pageContext.findAttribute("patientAuditLogMstFB");%>	
	<%//if(list!=null){%>
	<logic:present name="list">
	<div id="htmlContent">
	<%-- <table  width="100%" cellspacing="1" cellpadding="0">
		<tr >
		 <td width="50%" colspan="2">
	 	 	<div align="right">
	 	 		<font color="#000000" size="2" face="Arial">
					Report Date : <%=sysdate%>
				</font>
			</div>			
		  </td>
		 </tr>
	</table>			   
   <br>
	<%HospitalMstVO hospitalVO =(HospitalMstVO)session.getAttribute(Config.HOSPITAL_VO);%>
	<table  width="100%" cellspacing="1" cellpadding="0">
		<tr >
		 <td width="50%" colspan="2">
	 	 	<div align="center" style="font-size:large;font:Arial">
				<%=hospitalVO.getHospitalName()%>
			</div>			
		  </td>
		 </tr>
	</table>
	
	<table width="100%">	 
		 <tr>
		 <td width="50%" colspan="2">
		 	<div align="center" style="font-size:17px; font:Arial; font:bold;">
				<%=hospitalVO.getAddress1()%>		
			</div> 
		  </td>
		 </tr> 
	</table>
	<br> 
	
	<BR>
	--%>
	<table width="100%">	 
		 <tr>
		 <td width="50%" colspan="2">
		 	<div align="center" style="font-size:18px; font:Arial; font:bold;">
				<%//=fb.getAuditHeader() %>
				<bean:write name="auditHeader" property="auditHeader"/>		
			</div> 
		  </td>
		 </tr> 
	</table>
	<table width="100%" >
		<tr>
			<td>
				<div align="left">
					<b><bean:message key="patient"/> <bean:message key="crNo"/> :
					<bean:write name="patientAuditLogMstFB" property="patCrNo"/></b>
				</div>
			</td>
			
		</tr>
		<tr>
			<td></td>
		</tr>
	</table>
	
	<!------------------- Code to display Current records starts here ------------------->
	<table width="100%">
		<tr>
			<td>
				<b><bean:message key="currentRecord"/> </b>
			</td>
		</tr>
	</table>
	<%//List columnNameList=(List)session.getAttribute(Config.AUDIT_LOG_COLUMN_NAME_LIST); %>
	<%//pageContext.setAttribute("columnNameList",columnNameList) ;%>
	<bean:define id="columnNameList" name="<%=Config.AUDIT_LOG_COLUMN_NAME_LIST %>" type="java.util.List"/>
	<%if(session.getAttribute(Config.AUDIT_LOG_COLUMN_NAME_LIST)!=null){ %>
	<%-- <bean:define id="width" value="<%=100/columnNameList.size()+'%'%>"></bean:define> --%>
	<%String width=100/columnNameList.size()+"%";%>
	
	<table width="100%" cellspacing="1" cellpadding="0" border="1" bgcolor="#F1ECE2">
		<tr valign="middle">
		 <%--<td width="6%">
		 	<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>Sl No</b>		
			 </font>
			</div> 
		  </td>
		 --%> 
		<%//for(int i=0;i<columnNameList.size();i++){ %>
		<c:forEach var="columnName" items="${columnNameList}">
		 <td width="<%=width %>" valign="top" class="tdfonthead">
		 	<div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<%//=columnNameList.get(i) %>
				<c:out value="${columnName}"></c:out>
				</b>		
			 </font>
			</div> 
		  </td>
		 </c:forEach>
		 <%//}%>
		 <%} %>
		 <%if(list.size()==0) {%>
			<font color="red"><b><c:out value="No Current Record Found"></c:out></b></font>
		<%} %> 		 
		 </tr>
		 <% //List columnList=null; %>
		 <%//for(int i=0;i<list.size();i++) {%>
		 <c:forEach var="rowList" items="${list}">
		 <tr>
			<!-- column list contains the column data  -->		 
			<%//columnList=new ArrayList();
			//columnList=(List)list.get(i); %>
			<c:set var="columValueList" value="${rowList}" >
			</c:set>
			 <%--  <td width="6%">
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%=(i+1) %>		
				 </font>
				</div> 
			 </td>
			 --%>
			<%//pageContext.setAttribute("columValueList",columnList); %> 
			<%//for(int j=0;j<columnList.size();j++){ %>
					<%//=(columnList.get(j)==null?"-":(String)columnList.get(j)) %>
			<c:set var="index" value="0">
			</c:set>
			<logic:equal name="auditHeader" property="displayLogic" value="<%=Config.AUDIT_LOG_DISPLAY_LOGIC_NEEDED %>">
				<c:set var="index" value="2"></c:set>
			</logic:equal>
					
			<c:forEach var="columnValue" items="${columValueList}" begin="${index}">
			 <td> 
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<c:if test="${columnValue==null}">
						<c:out value="-"></c:out>
					</c:if>
					<c:if test="${columnValue!=null}">
						<c:out value="${columnValue}"></c:out>
					</c:if>
				 </font>
				</div> 
			  </td>
			</c:forEach>
		 	 <%//} %>	 
		 </tr>
		</c:forEach>
		 <%//}%>
		
		<!------------------- Code to display Current records ends here -------------------> 
		 
		<!------------------- Code to display Audit records starts here ------------------->
		<tr style="height: 40px">
			<td colspan="<%=columnNameList.size()+1 %>" valign="bottom">
				<b><bean:message key="auditRecord"/></b>
			</td>
		</tr>
		<tr><td></td></tr>
		<%//columnNameList=(List)session.getAttribute(Config.AUDIT_LOG_COLUMN_NAME_LIST); %>
	<%//if(columnNameList!=null){ %>
	 <c:if test="${columnNameList!=null}">	 
	<%String width=100/columnNameList.size()+"%";%>
		<tr valign="middle">
		<%--  <td width="6%">
		 	<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>Sl No</b>		
			 </font>
			</div> 
		  </td>
		 --%> 
		<%//for(int i=0;i<columnNameList.size();i++){ %>
		<c:forEach var="columnName" items="${columnNameList}">
		 <td width="<%=width %>" valign="top" class="tdfonthead">
		 	<div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><c:out value="${columnName}"></c:out>
				<%//=columnNameList.get(i) %></b>		
			 </font>
			</div> 
		  </td>
		 </c:forEach>
		 <%//}%>
		 <%//} %>
		 </tr>
		 </c:if>	 
		 <% List columnList=null;
		 list=new ArrayList();
		 //list=(List)session.getAttribute(Config.AUDIT_LOG_ROW_LIST);
		 list=auditLogRecordList;
	//	 List currentRowlist=(List)session.getAttribute(Config.AUDIT_LOG_CURRENT_RECORD_ROW_LIST);
		 List currentRowlist=(List)pageContext.getAttribute("list");
		 list.addAll(currentRowlist);
		 pageContext.setAttribute("auditLogRecordList",list);
		 %>
		 <%if(list.size()>0) {%>
		 <%for(int i=0;i<list.size();i++) {%>
		 <tr>
			<!-- column list contains the column data  -->		 
			<%columnList=new ArrayList();
			columnList=(List)list.get(i);
			List columnListPrevious=null;;
			if(i>0 && i!=(list.size()))
				columnListPrevious=(List)list.get(i-1);%>
			
			 <%--  <td width="6%">
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%=(i+1) %>		
				 </font>
				</div> 
			 </td>--%>
			 
			<%for(int j=0;j<columnList.size();j++){ %>
				<%if(auditHeader.getDisplayLogic().equals(Config.AUDIT_LOG_DISPLAY_LOGIC_NEEDED)){ %>
				<%if(i==0 && (j==2 || j==3)){ 
						continue;
					} else if(i>0 && (j==0 || j==1)){
						continue;
					}else{%>
			    <td>
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%if(i>0){ %>
					<%if(columnListPrevious!=null){
					  String preValue="" ;
					   if(columnListPrevious.get(j)!=null) preValue=(String)columnListPrevious.get(j);
					  String newValue="";
					 if(columnList.get(j)!=null) newValue=(String)columnList.get(j);
					 if(j==2 || j==3){
						 newValue=preValue;
					 }
					 if(preValue.equals(newValue)){ %>
					 <%=(newValue.equals("")?"-":newValue) %>
					 <%}else{ %>
						<font color="red"><%=(newValue.equals("")?"-":newValue) %></font>
					 <%}
					 }else{%>
						<%=(columnList.get(j)==null?"-":(String)columnList.get(j)) %>
					<%}}else if(i==0){ %>
						<font color="blue">
							<%=(columnList.get(j)==null?"-":(String)columnList.get(j)) %>
						</font>
					<%}%>
				 </font>
				</div> 
			  </td>
			  <%} %>
			 <%}else{ %>
			  <td > 
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%if(i==0){%>
						<font color="blue"><%=(columnList.get(j)==null?"-":(String)columnList.get(j)) %></font>
					<%}else{ %>
					<%if(columnListPrevious!=null){
					  String preValue="" ;
					   if(columnListPrevious.get(j)!=null) preValue=(String)columnListPrevious.get(j);
					  String newValue="";
					 if(columnList.get(j)!=null) newValue=(String)columnList.get(j);
					 if(preValue.equals(newValue)){ %>
					 <%=(newValue.equals("")?"-":newValue) %>
					 <%}else if(j!=0 && j!=1){ %>
						<font color="red"><%=(newValue.equals("")?"-":newValue) %></font>
					 <%}else{  out.println((newValue.equals("")?"-":newValue));}
					 }else{%>
						<%=(columnList.get(j)==null?"-":(String)columnList.get(j)) %>
					<%}} %>
				 </font>
				</div> 
			  </td>
			  <%} %>
		 	 <%} %>	 
		 </tr>
		
		 <%}%>
		 <%}else{
		 errorMessage="No Audit Record Found";}
		 %>
		 <!------------------- Code to display Audit records ends here ------------------->
	</table>
</div>
<%//}%>
</logic:present>
<table>
	<tr>
		<td>
			<font color="red">
				<b><%=errorMessage %></b>
			</font>
		</td>
	</tr>
	<tr><td></td></tr>
</table>
</logic:iterate>

			<!------------------------- Code to Display the Legends starts here---------------------->
	<his:SubTitleTag name="Legends">
		<div align="right">
			<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
			<td width="70%"> </td>
				<td width="30%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font>
					<img src='/HISInvestigationG5/hisglobal/images/arrow_down.gif' onclick="showLegends();">	
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font>
				<img src='/HISInvestigationG5/hisglobal/images/arrow_up.gif' onclick="hideLegends();">
	
				</div>
				</td>			
			</tr>
			</table>
		</div>
	</his:SubTitleTag>	
	
	<div id="legendId" style="display: none;">
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td style="background-color:#F1ECE2;" width="10%">
					<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Blue Color
					</font>
				</td>
				<td class="tdfonthead">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Base/First Record
						</font>
					</div>
				</td> 
			</tr>
			<tr>
				<td style="background-color:#F1ECE2;" width="10%">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Red Color
					</font>
				</td>
				<td class="tdfonthead">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Modified Records
						</font>
					</div>
				</td> 
			</tr>
		</table>
	 </div> 
		<!------------------------- Code to Display the Legends ends here---------------------->
</his:statusList>

	<his:ButtonToolBarTag>
         <div align="center">	
			
<!--				<img class="button" src="/HISInvestigationG5/hisglobal/images/btn-view.png" style="cursor: pointer;" onkeypress="if(event.keyCode==13) getData();" tabindex="1" onclick="getData();">-->
		<his:statusNew>
	        	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
	    </his:statusNew>
		<his:statusTransactionInProcess>
	        	<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) getData();" tabindex="1" onclick ="getData();">
	        	<img class="button" src='<his:path src="/hisglobal/images/Cancel.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick ="submitForm('NEW');">
	    </his:statusTransactionInProcess>
	    <his:statusList>
	        	<img class="button" src='<his:path src="/hisglobal/images/Cancel.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('GETPATDTL');" tabindex="1" onclick =" submitForm('GETPATDTL');">
	     </his:statusList>  
	     </div>
     </his:ButtonToolBarTag>
     
   <html:hidden name="patientAuditLogMstFB" property="hmode"/>
   <html:hidden name="patientAuditLogMstFB" property="auditHeader"/>
   <html:hidden name="patientAuditLogMstFB" property="patCrNo"/>
   <html:hidden name="patientAuditLogMstFB" property="displayLogic"/>
   <html:hidden name="patientAuditLogMstFB" property="registerDate"/>
   <html:hidden name="patientAuditLogMstFB" property="auditLogIdToRemove"/>
   <html:hidden name="patientAuditLogMstFB" property="auditHeaderSize"/>
   <input type="hidden" name="sysdate" value="<%=sysdate %>" />
</html:form>
   
<his:status/>      

<% } catch(Exception e) {e.printStackTrace();}%>
