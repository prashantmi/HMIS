<!-- 
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :BOOKMARK MASTER
 ## Purpose						        : 
 ## Date of Creation					:30-Mar-2015
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
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/new_investigation/js/bookMark.js" />

<script type="text/javascript">
function modifyClear()
{
	submitPage("CLEAR");	
}
</script>

<body>
<html:form action="/masters/BookMarkMstACT">
 
<his:TitleTag name="Bookmark Master">
		<%-- 	<his:insertDateTag/> --%>
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
								<bean:message key="BookmarkName"/>&nbsp;
						 </font>
				     </div>
			      </td>
		         <td width="50%" class="tdfont">
			      <div align="left">
				 <html:text name="BookMarkMstFB" property="bookmarkName" maxlength="30" size="35"   tabindex="1" ></html:text>
				  </div>
			     </td>
			     </tr>
			     <tr>
			     <tr>
			      	    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*	
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="BookmarkType"/>&nbsp;
						 </font>
				     </div>
			      </td>
		         <td width="50%" class="tdfont">
			      <div align="left">
			      <html:radio name="BookMarkMstFB"  tabindex="1" property="bookmarkType" value="1"  ></html:radio>
			      <bean:message key="UserWise"/>
			    <%--   
			      <html:radio name="BookMarkMstFB"  tabindex="1" property="bookmarkType" value="2"  ></html:radio>
			      <bean:message key="DeptUnitWise"/> --%>
			      </div>
			      </td>
			</tr>
			
			</table>
		
		
		</his:ContentTag>
	
		<his:ButtonToolBarTag>
		
				<span id="saveDiv">
			    <logic:notEqual name="BookMarkMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="BookMarkMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')" tabindex="1">
				  <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="BookMarkMstFB" property="hmode" value="MODIFY"> 
				   <img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')" tabindex="1">
				   <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor: pointer" onclick="modifyClear()" onkeypress="if(event.keyCode==13) modifyClear()" tabindex="1">
				</logic:equal> 
	               <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		</his:ButtonToolBarTag>
		<his:status/>
		<html:hidden name="BookMarkMstFB" property="hmode" />
		<html:hidden name="BookMarkMstFB" property="bookmarkCode" />
        <cmbPers:cmbPers />
        
		</html:form>
		
	</body>
	
</html>