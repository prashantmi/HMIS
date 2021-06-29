<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="hisglobal.utility.masterVerification.MasterVerificationFB"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date,java.util.Map,java.util.Set,java.util.Iterator"%>
<%@page import="hisglobal.utility.masterVerification.MasterVerificationVO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedHashMap"%>
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
<his:css src="/hisglobal/css/hisStyleExt.css" />

<script>
function convert(obj){
	if(obj.value==1){
		document.getElementsByName("hmode")[0].value="CONVERTTOPDF"
		document.getElementsByName("htmlContent")[0].value=document.getElementById("htmlContent").innerHTML;
		document.forms[0].submit();
	}
}

function printPage(){
	document.getElementById("buttonDiv").style.display="none"
	window.print();
	self.close();
}
</script>
<body>
<%try{ %>	
	<%//String errorMessage="";%>
	<c:set var="errorMessage" value=""></c:set> 
<html:form action="/masterVerification">	   
	<%String sysdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>
	<%MasterVerificationFB fb=(MasterVerificationFB)pageContext.findAttribute("masterVerificationFB");%>
	<% String columnName[]=fb.getColumnName().split("@");
	pageContext.setAttribute("columnName",columnName);%>
	<table  width="100%" cellspacing="1" cellpadding="0">
		<tr >
		 <td width="50%" colspan="2">
	 	 	<div align="left">
	 	 		<font color="#000000" size="2" face="Arial">
					<input type="radio" name="convertTo" value="0" onclick="convert(this)" checked="checked"/>HTML
					<input type="radio" name="convertTo" value="1" onclick="convert(this)"/>PDF
				</font>
			</div>			
		  </td>
		 </tr>
	</table>
	
<his:statusList>
	<!-- get the main list which contains the list of column data (ie row in form of list)-->
	<logic:present name="<%=Config.MASTER_DATA_LIST %>">
	<bean:define id="list" name="<%=Config.MASTER_DATA_LIST %>" type="java.util.List"></bean:define>	
	<div id="htmlContent">
	<table  width="100%" cellspacing="1" cellpadding="0">
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
	<%//HospitalMstVO hospitalVO =(HospitalMstVO)session.getAttribute(Config.HOSPITAL_VO);%>
	<bean:define id="hospitalVO" name="<%=Config.HOSPITAL_VO %>" type="hisglobal.vo.HospitalMstVO"></bean:define>
	<table  width="100%" cellspacing="1" cellpadding="0">
		<tr >
		 <td width="50%" colspan="2">
	 	 	<div align="center" style="font-size:large;font:Arial">
				<bean:write name="hospitalVO" property="hospitalName"/>
			</div>			
		  </td>
		 </tr>
	</table>
	
	<table width="100%">	 
		 <tr>
		 <td width="50%" colspan="2">
		 	<div align="center" style="font-size:17px; font:Arial; font:bold;">
				<bean:write name="hospitalVO" property="address1"/>	
			</div> 
		  </td>
		 </tr> 
	</table>
	<br>
	<BR>
	<table width="100%">	 
		 <tr>
		 <td width="50%" colspan="2">
		 	<div align="center" style="font-size:18px; font:Arial; font:bold;">
		 		<bean:define id="masterVerificationVO" name="<%=Config.MASTER_VERIFICATION_VO%>" type="hisglobal.utility.masterVerification.MasterVerificationVO"></bean:define>
				Report For  <bean:write name="masterVerificationVO" property="mainHeader"/>		
			</div> 
		  </td>
		 </tr> 
	</table>
	<table>
		<tr height="20px">
			<td>
			</td>
		</tr>
	</table>
	
	<logic:notEmpty name="masterVerificationFB" property="criteriaLabel"> 
	<table width="100%" cellspacing="1">
		<tr height="20px">
			<td width="20%" align="left">
				<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:write name="masterVerificationFB" property="criteriaLabel"/>
				</b>
				<b> : <bean:write name="masterVerificationFB" property="criteriaValue"/>
				</b>
				</font>
			</td>
			<td width="25%" colspan="2">
			</td>
		</tr>
	</table>
	<br>
	</logic:notEmpty>		 
	<bean:define id="width" value='<%=94/columnName.length+"%"%>'></bean:define>
	<table width="100%" cellspacing="1" cellpadding="0" border="1" bgcolor="#F1ECE2">
		<tr valign="middle">
		 <td width="6%" class="tdfonthead">
		 	<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>Sl No</b>		
			 </font>
			</div> 
		  </td>
		<c:forEach var="column" items="${columnName}">
		 <td width="<%=width %>" class="tdfonthead">
		 	<div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><c:out value="${column}"></c:out>	</b>
					
			 </font>
			</div> 
		  </td>
		  </c:forEach>
		 </tr>
		 <c:set var="i" value="${0}"/>
		 <c:forEach var="rowList" items="${list}">
		 <tr>
			<!-- column list contains the column data  -->		 
			<c:set var="columnList" value="${rowList}">
			</c:set>
			<c:set var="i" value="${i+1}"/>
			  <td width="6%">
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<c:out value="${i}"></c:out>		
				 </font>
				</div> 
			 </td>
			 <c:forEach var="columnList" items="${columnList}">
			 <td>
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<c:choose>
						<c:when test="${columnList==null}">
							<c:out value="-"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="${columnList}"></c:out>
						</c:otherwise>
					</c:choose>
				 </font>
				</div> 
			  </td>
			  </c:forEach>
		 </tr>
		</c:forEach>
	</table>	  
</div>
</logic:present>
<c:if test="${list==null}">
	<%//errorMessage="No data found"; %>
	<c:set var="errorMessage" value="No data found"></c:set>
</c:if>
</his:statusList>

<his:statusTransactionInProcess>
	<logic:present name="<%=Config.MASTER_DATA_MAP%>" >
	<bean:define id="map" name="<%=Config.MASTER_DATA_MAP%>" type="java.util.Map"></bean:define>
	<%//if(map!=null){ %>
	<div id="htmlContent">
	<table  width="100%" cellspacing="1" cellpadding="0">
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
	<%//HospitalMstVO hospitalVO =(HospitalMstVO)session.getAttribute(Config.HOSPITAL_VO);%>
	<bean:define id="hospitalVO" name="<%=Config.HOSPITAL_VO %>" type="hisglobal.vo.HospitalMstVO"></bean:define>
	<table  width="100%" cellspacing="1" cellpadding="0">
		<tr >
		 <td width="50%" colspan="2">
	 	 	<div align="center" style="font-size:large;font:Arial">
				<bean:write name="hospitalVO" property="hospitalName"/>
			</div>			
		  </td>
		 </tr>
	</table>
	
	<table width="100%">	 
		 <tr>
		 <td width="50%" colspan="2">
		 	<div align="center" style="font-size:17px; font:Arial; font:bold;">
				<bean:write name="hospitalVO" property="address1"/>	
			</div> 
		  </td>
		 </tr> 
	</table>
	<br>
	<BR>
	<table width="100%">	 
		 <tr>
		 <td width="50%" colspan="2">
		 	<div align="center" style="font-size:20px; font:arial; font:bold;">
				<bean:define id="masterVerificationVO" name="<%=Config.MASTER_VERIFICATION_VO%>" type="hisglobal.utility.masterVerification.MasterVerificationVO"></bean:define>
				Report For  <bean:write name="masterVerificationVO" property="mainHeader"/>				
			</div> 
		  </td>
		 </tr> 
	</table>
	<table>
		<tr height="20px">
			<td>
			</td>
		</tr>
	</table>
	
	<%
		//Set keySet=map.keySet();
		//Iterator itr=keySet.iterator();
		//while(itr.hasNext()){
		//String key=	itr.next().toString() ;
	%>
	<c:forEach var="mapValue" items="${map}">
	<table width="100%" cellspacing="1">
		<tr height="20px">
			<td width="100%" align="left">
				<font color="#000000" size="3" face="Arial">
					<b><bean:write name="masterVerificationVO" property="columnSubHeader"/></b>
				
				<b> : <c:out value="${mapValue.key}"></c:out><%//=key%></b>
				</font>
			</td>
		</tr>
	</table>
	<br>
	<bean:define id="width" value='<%=94/columnName.length+"%"%>'></bean:define>
	<%//String width=94/columnName.length+"%";%>
	<table width="100%" cellspacing="1" cellpadding="0" border="1" bgcolor="#F1ECE2">
		<tr valign="middle">
		 <td width="6%" class="tdfonthead">
		 	<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>Sl No</b>		
			 </font>
			</div> 
		  </td>
		<%//for(int i=0;i<columnName.length;i++){ %>
		<c:forEach var="column" items="${columnName}">
		 <td width="<%=width %>" class="tdfonthead">
		 	<div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><%//=columnName[i] %>
				<c:out value="${column}"></c:out></b>		
			 </font>
			</div> 
		  </td>
		  </c:forEach>
		 <%//} %>	 
		 </tr>
		 <!-- get the main list which contains the list of column data (ie row in form of list)-->
		 <%//List list=(List)map.get(key); %>
		 <%//if(list!=null){
		 //List columnList=null; %>
		 <c:if test="${mapValue.value!=null}">
		 <c:set var="list" value="${mapValue.value}"></c:set>
		 <%//or(int i=0;i<list.size();i++) {%>
		 <c:set var="i" value="${0}"></c:set>
		 <c:forEach var="rowList" items="${list}">
		 <tr>
			<!-- column list contains the column data  -->		 
			<%//columnList=new ArrayList();
			//columnList=(List)list.get(i); %>
			<c:set var="columnList" value="${rowList}"></c:set>
			<c:set var="i" value="${i+1}"></c:set>
			  <td width="6%">
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%//=(i+1) %>
					<c:out value="${i}"></c:out>		
				 </font>
				</div> 
			 </td>
			<%//for(int j=0;j<columnName.length;j++){ %>
			<c:set var="columnLength">
				<%=columnName.length-1 %>
			</c:set> 
			<c:forEach var="columnValue" items="${columnList}" end="${columnLength}">
			 <td>
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%//=(columnList.get(j)==null?"-":(String)columnList.get(j)) %>
					<c:choose>
						<c:when test="${columnValue==null}">
							<c:out value="-"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="${columnValue}"></c:out>
						</c:otherwise>
					</c:choose>
				 </font>
				</div> 
			  </td>
			  </c:forEach>
		 	 <%//} %>	 
		 </tr>
		 </c:forEach>
		 <%//}%>
		 <%//}%>
		 </c:if>
	</table>
	<br>	
	<br style="page-break-after: always;">
	</c:forEach>
	<%//}%>
	</div>
	</logic:present>
	<%//}else{%>
	<c:if test="${map==null}">
<!--		<b><font color="red">No data found</font></b>-->
	<c:set var="errorMessage" value="No data found"></c:set>
	</c:if>	
	<%//} %>	
</his:statusTransactionInProcess>

   <html:hidden name="masterVerificationFB" property="hmode"/>
   <html:hidden name="masterVerificationFB" property="columnName"/>
   <html:hidden name="masterVerificationFB" property="htmlContent"/>
   <html:hidden name="masterVerificationFB" property="criteriaValue"/>
   <html:hidden name="masterVerificationFB" property="criteriaLabel"/>
   <html:hidden name="masterVerificationFB" property="isGrouped"/>
   
   </html:form>
</body>
    <div id="buttonDiv" align="center">	
	<his:ButtonToolBarTag>
		<c:if test="${empty errorMessage}">
		<%//if(errorMessage.equals("")){ %>
			<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) printPage();" tabindex="1" onclick ="printPage()">
		<%//} %>
		</c:if>	
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) window.close();" tabindex="1" onclick ="window.close();">
     </his:ButtonToolBarTag>
    </div>
     
  <his:status/>      
<b><font color="red"><c:out value="${errorMessage}"/></font></b> 	

<%} catch(Exception e) {e.printStackTrace();}%>
