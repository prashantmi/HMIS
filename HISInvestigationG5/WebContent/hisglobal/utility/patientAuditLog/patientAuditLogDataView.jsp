<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.utility.patientAuditLog.PatientAuditLogMstFB"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date" %>
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

<%try{ %>	
	<%String errorMessage="";%> 
	<%String sysdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy"); %>
<html:form action="/patientAuditLogMst">
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
	
<his:statusDone>
	<!-- get the main list which contains the list of column data (ie row in form of list)-->
	<%List list=(List)session.getAttribute(Config.AUDIT_LOG_CURRENT_RECORD_ROW_LIST); %>
	<%PatientAuditLogMstFB fb=(PatientAuditLogMstFB)pageContext.findAttribute("patientAuditLogMstFB");%>	
	<%if(list!=null){%>
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
	<table width="100%">	 
		 <tr>
		 <td width="50%" colspan="2">
		 	<div align="center" style="font-size:18px; font:Arial; font:bold;">
				Report For  <%=fb.getAuditHeader() %>		
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
	<table>
		<tr>
			<td>
				<b>Current Record</b>
			</td>
		</tr>
	</table>
	<%List columnNameList=(List)session.getAttribute(Config.AUDIT_LOG_COLUMN_NAME_LIST); %>
	<%if(columnNameList!=null){ %>	 
	<%String width=94/columnNameList.size()+"%";%>
	<table width="100%" cellspacing="1" cellpadding="0" border="1">
		<tr valign="middle">
		 <td width="6%">
		 	<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>Sl No</b>		
			 </font>
			</div> 
		  </td>
		<%for(int i=0;i<columnNameList.size();i++){ %>
		 <td width="<%=width %>" valign="top">
		 	<div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><%=columnNameList.get(i) %></b>		
			 </font>
			</div> 
		  </td>
		 <%}} %>	 
		 </tr>
		 <% List columnList=null; %>
		 <%for(int i=0;i<list.size();i++) {%>
		 <tr>
			<!-- column list contains the column data  -->		 
			<%columnList=new ArrayList();
			columnList=(List)list.get(i); %>
			  <td width="6%">
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%=(i+1) %>		
				 </font>
				</div> 
			 </td>
			<%for(int j=0;j<columnList.size();j++){ %>
			 <td>
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%=(columnList.get(j)==null?"-":(String)columnList.get(j)) %>
				 </font>
				</div> 
			  </td>
		 	 <%} %>	 
		 </tr>
		
		 <%}%>
	
		<tr style="height: 40px">
			<td colspan="<%=columnNameList.size()+1 %>" valign="bottom">
				<b>Audit Record</b>
			</td>
		</tr>
		<tr><td></td></tr>
		<%columnNameList=(List)session.getAttribute(Config.AUDIT_LOG_COLUMN_NAME_LIST); %>
	<%if(columnNameList!=null){ %>	 
	<%String width=94/columnNameList.size()+"%";%>
		<tr valign="middle">
		 <td width="6%">
		 	<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>Sl No</b>		
			 </font>
			</div> 
		  </td>
		<%for(int i=0;i<columnNameList.size();i++){ %>
		 <td width="<%=width %>" valign="top">
		 	<div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><%=columnNameList.get(i) %></b>		
			 </font>
			</div> 
		  </td>
		 <%}} %>	 
		 </tr>
		 <% columnList=null;
		 list=new ArrayList();
		 list=(List)session.getAttribute(Config.AUDIT_LOG_ROW_LIST);
		 %>
		 <%for(int i=0;i<list.size();i++) {%>
		 <tr>
			<!-- column list contains the column data  -->		 
			<%columnList=new ArrayList();
			columnList=(List)list.get(i); %>
			  <td width="6%">
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%=(i+1) %>		
				 </font>
				</div> 
			 </td>
			<%for(int j=0;j<columnNameList.size();j++){ %>
			 <td>
			 	<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%=(columnList.get(j)==null?"-":(String)columnList.get(j)) %>
				 </font>
				</div> 
			  </td>
		 	 <%} %>	 
		 </tr>
		
		 <%}%>
	</table>
</div>
<%}else{ %>
	<%errorMessage="No data found"; %>
<%} %>
</his:statusDone>

   <html:hidden name="patientAuditLogMstFB" property="hmode"/>
   <html:hidden name="patientAuditLogMstFB" property="htmlContent"/>
   <html:hidden name="patientAuditLogMstFB" property="auditHeader"/>
   </html:form>

    <div id="buttonDiv" align="center">	
	<his:ButtonToolBarTag>
		<%if(errorMessage.equals("")){ %>
			<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) printPage();" tabindex="1" onclick ="printPage()">
		<%} %>	
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) window.close();" tabindex="1" onclick ="window.close();">
     </his:ButtonToolBarTag>
    </div>
     
  <his:status/>      
<b><font color="red"><%=errorMessage%></font></b> 	

<%} catch(Exception e) {e.printStackTrace();}%>
