<!-- 
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LOINC MASTER
 ## Purpose						        : 
 ## Date of Creation					:21-APR-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
 -->


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page import="new_investigation.vo.LoincMstVO"%>
<%@page import="new_investigation.masters.controller.fb.LoincMstFB;"%>
<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/loincMst.js" />

<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<script type="text/javascript">
	
function populatePage()

{
      var code = document.getElementsByName("testCode")[0].value;
      if (code != -1) {
            submitPage('POPULATE');
      } else {
            submitPage('ADD');
      } 
         
}
function populatePage1()

{
      var code = document.getElementsByName("sampleCode")[0].value;
      if (code != -1) {
            submitPage('GETUOM');
      } else {
            submitPage('ADD');
      }    
}
function getLoinc()

{
      var code = document.getElementsByName("sampleCode")[0].value;
      if (code != -1) {
            submitPage('GETLOINC');
      } else {
            submitPage('ADD');
      }    
}
function getSearch()

{
      var code = document.getElementsByName("loincSearch")[0].value.length;
      if (code>=3) {
            submitPage('GETSEARCH');
      } else {
      alert("Please enter atleast theee Character");
      
       submitPage('GETLOINC');
          
      }    
}
function getLoincTime()
{
var testCode=document.getElementsByName("testCode")[0].value;
document.getElementsByName("loincTime")[0].value=testCode.split("#")[2];
//alert(testCode.split("#")[2]);
}
function getLoincScale()
{
var paraCode=document.getElementsByName("paraCode")[0].value;
document.getElementsByName("loincScale")[0].value=paraCode.split("#")[1];

}
function getLoincSystem()
{
var sampleCode=document.getElementsByName("sampleCode")[0].value;
document.getElementsByName("loincSystem")[0].value=sampleCode.split("#")[1];

}
function getLoincProperty()
{
var uomCode=document.getElementsByName("uomCode")[0].value;
document.getElementsByName("loincProperty")[0].value=uomCode.split("#")[1];

}
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
</script>
<body >
<html:form action="/masters/LoincMstACT">
 
<his:TitleTag name="Loinc Master">
			<%-- <his:insertDateTag/> --%>
		</his:TitleTag>
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="2" cellpadding="1">
		<tr>
		<td width="50%" class="tdfonthead">
			        <div align="right">
			        <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="testName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <logic:equal name="LoincMstFB" property="hmode" value="ADD">
		  <td width="50%" class="tdfont">
			  <div align="left"> 
		 <html:select name="LoincMstFB" property="testCode" style="width:35%" onchange="populatePage();"  tabindex="1" >
				       		<html:option value="-1">Select Value</html:option>
				       		<logic:present name="<%= InvestigationConfig.LIST_TEST_COMBO%>">
				 	   		<html:options collection="<%=InvestigationConfig.LIST_TEST_COMBO %>" property="value" labelProperty="label"/>
				 	 		</logic:present>
				      		</html:select>
			  </div>
		 </td>
		 </logic:equal>
		 <logic:notEqual name="LoincMstFB" property="hmode" value="ADD">
		 	<td width="50%" class="tdfont">
			  <div align="left">
			  	 <html:text name="LoincMstFB" property="testName" style="width:35%"  readonly="true" tabindex="1" ></html:text>
			  </div>
			</td>
		 </logic:notEqual>
     </tr>
     <tr>
     <td width="50%" class="tdfonthead">
			        <div align="right">
			        	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LOINCTiming"/>&nbsp;
						 </font>
				    </div>
	 </td>
     <td width="50%" class="tdfont">
			      <div align="left">
				 	 <html:text name="LoincMstFB" property="loincTime"  style="width:35%" readonly="true" tabindex="1" ></html:text>
				  </div>
	</td>
     </tr>
     <tr>
		<td width="50%" class="tdfonthead">
			        <div align="right">
			        <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="TestParaCombo"/>&nbsp;
						 </font>
				     </div>
			      </td>
			       <logic:equal name="LoincMstFB" property="hmode" value="ADD">
		  <td width="50%" class="tdfont">
			  <div align="left"> 
		 <html:select name="LoincMstFB" property="paraCode" style="width:35%"  onchange="getLoincScale();" tabindex="1" >
				       		<html:option value="-1">Select Value</html:option>
				       		<logic:present name="<%= InvestigationConfig.LIST_PARA_COMBO%>">
				 	   		<html:options collection="<%=InvestigationConfig.LIST_PARA_COMBO %>" property="value" labelProperty="label"/>
				 	 		</logic:present>
				      		</html:select>
			  </div>
		 </td>
		 </logic:equal>
		  <logic:notEqual name="LoincMstFB" property="hmode" value="ADD">
		 	<td width="50%" class="tdfont">
			  <div align="left">
			  	 <html:text name="LoincMstFB" property="paraName"  style="width:35%" readonly="true" tabindex="1" ></html:text>
			  </div>
			</td>
		 </logic:notEqual>
     </tr>
        <tr>
     <td width="50%" class="tdfonthead">
			        <div align="right">
			        	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LOINCScale"/>&nbsp;
						 </font>
				    </div>
	 </td>
	 
     <td width="50%" class="tdfont">
			      <div align="left">
				 	 <html:text name="LoincMstFB" property="loincScale" style="width:35%"  readonly="true" tabindex="1" ></html:text>
				  </div>
	</td>
     </tr>
     <tr>
		<td width="50%" class="tdfonthead">
			        <div align="right">
			        <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="SampleName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			       <logic:equal name="LoincMstFB" property="hmode" value="ADD">
		  <td width="50%" class="tdfont">
			  <div align="left"> 
		 <html:select name="LoincMstFB" property="sampleCode" style="width:35%"  onchange="getLoincSystem();" tabindex="1" >
				       		<html:option value="-1">Select Value</html:option>
				       		<logic:present name="<%= InvestigationConfig.LIST_SAMPLE_COMBO%>">
				 	   		<html:options collection="<%=InvestigationConfig.LIST_SAMPLE_COMBO %>" property="value" labelProperty="label"/>
				 	 		</logic:present>
				      		</html:select>
			  </div>
		 </td>
		 </logic:equal>
		  <logic:notEqual name="LoincMstFB" property="hmode" value="ADD">
		 	<td width="50%" class="tdfont">
			  <div align="left">
			  	 <html:text name="LoincMstFB" property="sampleName" style="width:35%" readonly="true" tabindex="1" ></html:text>
			  </div>
			</td>
		 </logic:notEqual>
     </tr>
     <tr>
     <td width="50%" class="tdfonthead">
			        <div align="right">
			        	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LOINCSystem"/>&nbsp;
						 </font>
				    </div>
	 </td>
     <td width="50%" class="tdfont">
			      <div align="left">
				 	 <html:text name="LoincMstFB" property="loincSystem" style="width:35%" readonly="true" tabindex="1" ></html:text>
				  </div>
	</td>
     </tr>
  
          <tr>
		<td width="50%" class="tdfonthead">
			        <div align="right">
			        <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="uomCombo"/>&nbsp;
						 </font>
				     </div>
			      </td>
			       <logic:equal name="LoincMstFB" property="hmode" value="ADD">
		  <td width="50%" class="tdfont">
			  <div align="left"> 
		 <html:select name="LoincMstFB" property="uomCode" style="width:35%" onchange="getLoincProperty();"  tabindex="1" >
				       		<html:option value="-1">Select Value</html:option>
				       		<logic:present name="<%= InvestigationConfig.LIST_UOM_COMBO%>">
				 	   		<html:options collection="<%=InvestigationConfig.LIST_UOM_COMBO %>" property="value" labelProperty="label"/>
				 	 		</logic:present>
				      		</html:select>
			  </div>
		 </td>
		 </logic:equal>
		  <logic:notEqual name="LoincMstFB" property="hmode" value="ADD">
		 	<td width="50%" class="tdfont">
			  <div align="left">
			  	 <html:text name="LoincMstFB" property="uomName" style="width:35%" readonly="true" tabindex="1" ></html:text>
			  </div>
			</td>
		 </logic:notEqual>
     </tr>
     <tr>
     <td width="50%" class="tdfonthead">
			        <div align="right">
			        	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LOINCProperty"/>&nbsp;
						 </font>
				    </div>
	 </td>
     <td width="50%" class="tdfont">
			      <div align="left">
				 	 <html:text name="LoincMstFB" property="loincProperty" style="width:35%" readonly="true" tabindex="1" ></html:text>
				  </div>
	</td>
    
     </tr>
      <tr>
		<td width="50%" class="tdfonthead">
			        <div align="right">
			        <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="testMethod"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <logic:equal name="LoincMstFB" property="hmode" value="ADD">
		  <td width="50%" class="tdfont">
			  <div align="left"> 
		 <html:select name="LoincMstFB" property="methodCode" style="width:35%" onchange="getLoincProperty();"  tabindex="1" >
				       		<html:option value="-1">Select Value</html:option>
				       		<logic:present name="<%= InvestigationConfig.LIST_TEST_METHOD_COMBO%>">
				 	   		<html:options collection="<%=InvestigationConfig.LIST_TEST_METHOD_COMBO %>" property="value" labelProperty="label"/>
				 	 		</logic:present>
				      		</html:select>
			  </div>
		 </td>
		 </logic:equal>
		  <logic:notEqual name="LoincMstFB" property="hmode" value="ADD">
		 	<td width="50%" class="tdfont">
			  <div align="left">
			  	 <html:text name="LoincMstFB" property="methodName" style="width:35%" readonly="true" tabindex="1" ></html:text>
			  </div>
			</td>
		 </logic:notEqual>
	</tr>
	<logic:notEqual name="LoincMstFB" property="hmode" value="ADD">
     <tr>
     <td width="50%" class="tdfonthead">
			        <div align="right">
			        	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="LOINCCode"/>&nbsp;
						 </font>
				    </div>
	 </td>
     <td width="50%" class="tdfont">
			      <div align="left">
				 	 <html:text name="LoincMstFB" property="loincCode" style="width:35%" readonly="true" tabindex="1" ></html:text>
				  </div>
     </tr>
     </logic:notEqual>
     <logic:equal name="LoincMstFB" property="mode" value="GETLOINC">
     <tr>
     <td width="50%" class="tdfonthead">
     
     </td>
     <td width="50%" class="tdfont">
     <img class="button" src='<his:path src="/hisglobal/images/btn-search.png"/>' style="cursor: pointer" onclick="validateFinalSubmit(); getLoinc();" tabindex="1">
     </td>
     </tr>
     </logic:equal>
</table>
<logic:notEqual name="LoincMstFB" property="mode"  value="ADD">
<table  id="tab2" width="100%">
<tr>
<td>
  <his:SubTitleTag name="Loinc Data">
  <table id="tab1" width="100%">
  	<tr>
  		<td width="50%">
  			<div align="right">
  			
  			</div>
  		</td>
  		<td width="45%">
  		<div align="right">
  			<html:text name="LoincMstFB" property="loincSearch"  maxlength="15" tabindex="1" ></html:text>
  			</div>
  		</td>
  		<td width="5%">
  		<div align="left">
  			<img class="button" src='<his:path src="/hisglobal/images/search_icon1.gif"/>' style="cursor: pointer" onclick="getSearch();" tabindex="1">
  			</div>
  		</td>
  	</tr>
  </table>
  </his:SubTitleTag>
  </td>
  </tr>
  </table>
  </logic:notEqual>
  <%boolean flag=false; %>
  <% 
		PaginationFB fbPage= new PaginationFB();
		pageContext.setAttribute("fbPagination",fbPage);
		fbPage.setCurrentPage(((LoincMstFB)request.getAttribute("LoincMstFB")).getCurrentPage());
		fbPage.setObjArrName(InvestigationConfig.LIST_LOINC_DTLS);
		//fbPage.setAppendInTitle("List ");
		int maxRecord=10;
		fbPage.setMaxRecords(maxRecord);
				 
	%>
	<his:PaginationTag name="fbPagination"></his:PaginationTag>
			<% flag=true; %>
			<logic:present name="<%=InvestigationConfig.LIST_LOINC_DTLS %>">
			<table   width="100%">
				<tr>
					<td width="3%" align="left"  >	
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 <bean:message key="select"/> </font></b>
					</td>
					<td width="15%" align="center"   >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 <bean:message key="LOINCCode"/> </font></b>
					</td>
					<td width="25%" align="center"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="componentName"/></font></b>
					</td>
					<td width="10%" align="center"  >
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="LOINCProperty"/></font></b>
					</td>
					<td width="15%" align="center"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="tom"/></font></b>
					</td>
					<td width="15%" align="center">
						<b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="LOINCScale"/></font></b>
					</td>
					<td width="17%" align="center"  >
						<b><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="methodType"/></font></b>
					</td>
				</tr>
			</table>
			</logic:present>
			<table id="tab3" width="100%">
			<tr>
			<td>
			<logic:notEqual name="LoincMstFB" property="mode"  value="ADD">
			<logic:empty name="<%=InvestigationConfig.LIST_LOINC_DTLS %>">
				<center>
				<font color="red" size="4">
				No Record Found</font></center>
			</logic:empty>
			</logic:notEqual>
			</td>
			</tr>
			</table>
			<logic:notEmpty name="<%=InvestigationConfig.LIST_LOINC_DTLS%>">
			<table   width="100%">
					<%
					 List<LoincMstVO> lstPatVO=(List<LoincMstVO>)session.getAttribute(InvestigationConfig.LIST_LOINC_DTLS);
					 int i,size=0;
			 		if(lstPatVO!=null && lstPatVO.size()>0 )
			 			size=lstPatVO.size();
					int startIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex=((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
					for(int j=startIndex;j<=endIndex;j++)
					{
						//int i=j-startIndex;
					if(j<size)
									{
						LoincMstVO voLoinc=lstPatVO.get(j);
					String chkVal=voLoinc.getLoincCode();%>
					<tr>
						<td width="3%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<html:radio name="LoincMstFB" property="loincValues" value="<%=chkVal%>" />
							</font>
						</td>
						<td width="15%" align="center" >
						 <div  >
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getLoincCode() %></font> 
						 </div>
				  		</td>
				  		
				  		<td width="25%" align="center"  >
				  		 
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getComponentName() %></font>
						 </div>
				  		
				  		</td>
				  		<td width="10%" align="center">
				  		 
				  		
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getProperty() %></font>
						 </div>
				  		</td>
				  		 <td  width="15%" align="center">
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getTom() %></font>
						 </div>
				  		</td>
				  		<td width="15%" align="center">
				  		 
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getScale() %></font>
						 </div>
				  		</td>
				  		<td width="17%" align="center">
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getMethodType()  %></font>
						 </div>
				  		</td>
					</tr>
					<%}  }
					if(startIndex>endIndex)
					{
						for(int j=0;j<size;j++)
						{
						
						LoincMstVO voLoinc=lstPatVO.get(j);
						String chkVal=voLoinc.getLoincCode();
					%>
						<tr>
						<td width="3%" align="left"  >
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">
							<html:radio name="LoincMstFB" property="loincValues" value="<%=chkVal%>" />
							</font>
						</td>
						<td width="15%" align="center" >
						 <div  >
						  <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getLoincCode() %></font> 
						 </div>
				  		</td>
				  		
				  		<td width="25%" align="center"  >
				  		 
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getComponentName() %></font>
						 </div>
				  		
				  		</td>
				  		<td width="10%" align="center">
				  		 
				  		
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getProperty() %></font>
						 </div>
				  		</td>
				  		 <td  width="15%" align="center">
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getTom() %></font>
						 </div>
				  		</td>
				  		<td width="15%" align="center">
				  		 
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getScale() %></font>
						 </div>
				  		</td>
				  		<td width="17%" align="center">
				  		<div  >
				  		<font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						 	<%=voLoinc.getMethodType()  %></font>
						 </div>
				  		</td>
					</tr>
					<%
						
						}
					}
					%>
			</table>
			</logic:notEmpty>
						
</his:ContentTag>
	<his:ButtonToolBarTag>
		
				<span id="saveDiv">
			    <logic:notEqual name="LoincMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="LoincMstFB" property="hmode" value="VIEW">
			    <logic:equal name="LoincMstFB" property="mode" value="GETLOINC">
			      <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')" tabindex="1">
			      </logic:equal>			      
			      <logic:equal name="LoincMstFB" property="mode" value="ADD">
			      <img class="button" src='<his:path src="/hisglobal/images/btn-search.png"/>' style="cursor: pointer" onclick="validateFinalSubmit(); getLoinc();" tabindex="1">
			      </logic:equal>
				  <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="LoincMstFB" property="hmode" value="MODIFY"> 
				   <img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
				   <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="modifyClear()" onkeypress="if(event.keyCode==13) modifyClear()" tabindex="1">
				</logic:equal> 
	               <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		</his:ButtonToolBarTag>
		<logic:present name="<%=InvestigationConfig.LIST_TEST_COMBO %>">
		<logic:empty name="<%=InvestigationConfig.LIST_TEST_COMBO  %>">
			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<b>No Test Found </b>
			</font>	
		</logic:empty>		
		</logic:present>
		<logic:present name="<%=InvestigationConfig.LIST_PARA_COMBO %>">
		<logic:empty name="<%=InvestigationConfig.LIST_PARA_COMBO  %>">
			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<b>No Parameter Found </b>
			</font>	
		</logic:empty>		
		</logic:present>
		<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COMBO %>">
		<logic:empty name="<%=InvestigationConfig.LIST_SAMPLE_COMBO  %>">
			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<b>No Sample Found </b>
			</font>	
		</logic:empty>		
		</logic:present>
		<logic:present name="<%=InvestigationConfig.LIST_UOM_COMBO %>">
		<logic:empty name="<%=InvestigationConfig.LIST_UOM_COMBO  %>">
			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<b>No Uom Found </b>
			</font>	
		</logic:empty>		
		</logic:present>
		<logic:present name="<%=InvestigationConfig.MAP_LOINC_DTLS %>">
		<logic:empty name="<%=InvestigationConfig.MAP_LOINC_DTLS  %>">
			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<b>No Loinc Found </b>
			</font>	
		</logic:empty>		
		</logic:present>
			<his:status/>
		<html:hidden name="LoincMstFB" property="hmode" />
		<html:hidden name="LoincMstFB" property="testCode"/>
		<html:hidden name="LoincMstFB" property="paraCode"/>
		<html:hidden name="LoincMstFB" property="sampleCode"/>
		<html:hidden name="LoincMstFB" property="uomCode"/>
		<html:hidden name="LoincMstFB" property="testName"/>
		<html:hidden name="LoincMstFB" property="paraName"/>
		<html:hidden name="LoincMstFB" property="sampleName"/>
		<html:hidden name="LoincMstFB" property="uomName"/>
		<html:hidden name="LoincMstFB" property="loincSearch"/>
		<html:hidden name="LoincMstFB" property="loincValues"/>
		<html:hidden name="LoincMstFB" property="loincCode"/>
		<html:hidden name="LoincMstFB" property="mode"/>
		<html:hidden name="LoincMstFB" property="currentPage"/>
		<html:hidden name="LoincMstFB" property="loincTime"/>
		<html:hidden name="LoincMstFB" property="methodCode"/>
        <cmbPers:cmbPers />
        
		</html:form>
		
	</body>
	
</html>