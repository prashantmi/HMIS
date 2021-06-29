<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.hisconfig.Config"%>
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
<script>
function getMasterList(obj){
	document.getElementsByName("hmode")[0].value='GETMASTERLIST'
	//alert(obj.value)
	document.getElementsByName("moduleId")[0].value=obj.value
	document.forms[0].submit()
}
function setOrderByOption(obj){
	if(obj.value!="-1"){
		document.getElementsByName("hmode")[0].value='SETOPTION'
		document.getElementsByName("mainHeader")[0].value=obj.value
		document.forms[0].submit();
	}
	else{
		document.getElementById("orderByDiv").innerHTML=null;
		
	}	
}
function getData(){
   var popup=null;
   var orderBy=document.forms[0].orderBy;
   var orderByString="";
   if(!isSelected(document.getElementsByName("moduleId")[0],"Module Name")){
   		return false;
   }
   if(!isSelected(document.getElementsByName("mainHeader")[0],"Verification Of")){
   		return false;
   }
   if(document.getElementsByName("criteriaColumn")[0] && document.getElementsByName("criteriaColumn")[0].value!="-1"){
   	 if(!isSelected(document.getElementsByName("criteriaCode")[0],"Criteria Data")){
   	 	return false;
   	 }	
   }
   	
  // alert("orderBy.length "+orderBy.length)
   
   for(var i=0;i<orderBy.length;i++){
		//alert("orderBy[i].value "+orderBy[i].value)
		if(orderBy.options[i].selected){
			orderByString=orderByString + "@" + orderBy.options[i].value
		}
   }
   orderByString=orderByString.substring(1);
   
   //var criteria=document.getElementsByName("criteriaCode")[0].value
   //var criteriaColumn=document.getElementsByName("criteriaColumn")[0].value
   
   document.getElementsByName("orderByString")[0].value=orderByString
   
   
	//	"Master Data","scrollbars=yes, height=500,width=800 ,top=" + "150" + ",left=" + "150" );
	document.getElementsByName("hmode")[0].value='GETDATA'
	document.forms[0].submit()
}

window.onload=function(){
	if(document.getElementsByName("hmode")[0].value=='GETDATA'){
	
		var columnName=document.getElementsByName("columnName")[0].value
   		var criteriaLabel=document.getElementsByName("criteriaLabel")[0].value
   		var criteriaValue=document.getElementsByName("criteriaValue")[0].value
   		var isGrouped=document.getElementsByName("isGrouped")[0].value
   		
   		var orderBy=document.getElementsByName("orderBy")[0].value
   		var title=document.getElementsByName("mainHeader")[0].value + orderBy
   		if(document.getElementsByName("criteriaCode")[0]){
   			title+=document.getElementsByName("criteriaCode")[0].value
   		}
		popup=window.open("/HISInvestigationG5/utility/masterVerification.cnt?hmode=POPUP&columnName="+ columnName +"&criteriaLabel=" + criteriaLabel
		+ "&criteriaValue=" + criteriaValue +"&isGrouped=" + isGrouped , 
		title ,"scrollbars=yes, height=500,width=800 ,top=" + "75" + ",left=" + "150" );
		
	}
	
}

function getCriteriaData(obj){
	//if(obj.value!="-1"){
	 	submitForm('GETCRITERIADATA')
		document.getElementsByName("criteriaCode")[0].disabled=false
		document.getElementsByName("orderBy")[0].disabled=false
	//}
	//else{
	//	document.getElementsByName("criteriaCode")[0].value="-1"
	//	document.getElementsByName("criteriaCode")[0].disabled=true
	//	document.getElementsByName("orderBy")[0].disabled=true
	//}	 
}
</script>

<%try{ %>	
<his:TransactionContainer>
<html:form action="/masterVerification">	   
		   
   <his:TitleTag name="Master Verification"> 		
   </his:TitleTag>
<%-- 	  
   <his:SubTitleTag name="">
   	<td width="90%">
   		<div align="right">
   			<b>Is Active</b>
   		</div>
   	</td>
   	<td width="20%">
   		<div align="left">
   			<html:select property="isActive" styleClass="regcbo" style="width:75px">
   				<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option>
   				<html:option value="<%=Config.IS_VALID_INACTIVE %>">In Active</html:option>
   				<html:option value="<%=Config.IS_VALID_DELETED %>">Deleted</html:option>
   			</html:select>
   		</div>
   	</td>
   </his:SubTitleTag>
   --%>
<his:ContentTag>
<his:statusNew>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="25%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="modulename"/></b>
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont">
			&nbsp;<html:select name="masterVerificationFB" property="moduleId" tabindex="1" styleClass="regcbo" onchange="getMasterList(this);" style="width:200px">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=Config.MASTER_VERIFICATION_MODULE_LIST %>">
				<html:options collection="<%=Config.MASTER_VERIFICATION_MODULE_LIST %>" labelProperty="label" property="value"/>
				</logic:present>
			</html:select>
		  </td>
		</tr>
		<tr>  
		 <td width="25%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="verificationOf"/></b>
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont">
			&nbsp;<html:select name="masterVerificationFB" property="mainHeader" tabindex="1" styleClass="regcbo" onchange="setOrderByOption(this);" style="width:200px">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=Config.MASTER_VERIFICATION_MASTER_OPTION_LIST %>">
				<html:options collection="<%=Config.MASTER_VERIFICATION_MASTER_OPTION_LIST %>" labelProperty="label" property="value"/>
				</logic:present>
			</html:select>
		  </td>
		 </tr>
		
		<logic:present name="<%=Config.MASTER_CRITERIA_OPTION_LIST %>">
		<tr>  
		 <td width="25%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="selectCriteria"/></b>
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont">
			&nbsp;<html:select name="masterVerificationFB" property="criteriaColumn" tabindex="1" styleClass="regcbo" onchange="getCriteriaData(this)" style="width:200px">
				<html:option value="-1">All</html:option>
				<html:options collection="<%=Config.MASTER_CRITERIA_OPTION_LIST %>" labelProperty="label" property="value"/>
			</html:select>
		  </td>
		 </tr>
		</logic:present>
		<logic:present name="<%=Config.MASTER_CRITERIA_DATA %>">
		<tr>  
		 <td width="25%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="criteriaData"/></b>
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont">
			&nbsp;<html:select name="masterVerificationFB" property="criteriaCode" tabindex="1" styleClass="regcbo" style="width:200px">
				<html:option value="-1">Select Value</html:option>
				<html:options collection="<%=Config.MASTER_CRITERIA_DATA %>" labelProperty="label" property="value"/>
			</html:select>
		  </td>
		 </tr>
		</logic:present>
		 
		<logic:present name="<%=Config.MASTER_VERIFICATION_ORDERBY_OPTION_LIST %>">
		
		<tr id="orderByDiv">
		 <td width="50%" class="tdfonthead">
<!--			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		-->
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="orderBy"/></b>
			 </font>
	
		  </td>
		 <td width="50%" class="tdfont">
			<table width="100%">
			<tr>
			<td width="60%">
			<html:select name="masterVerificationFB" property="orderBy" tabindex="1" size="5" multiple="true" style="width:200px">
				<html:options collection="<%=Config.MASTER_VERIFICATION_ORDERBY_OPTION_LIST %>" labelProperty="label" property="value"/>
			</html:select>
			 </td>
			 <td width="40%" class="tdfont">
			  	<div align="left">
			  	<img src="/HISInvestigationG5/hisglobal/images/arr-up.png" class="link" onClick='moveUP(document.forms[0].orderBy);'/>
						<br>
				<img src="/HISInvestigationG5/hisglobal/images/arr-dwn.png" class="link" onClick='moveDOWN(document.forms[0].orderBy);'/>
		 		</div>
		 	</td>
		 	</tr>
		 	</table>	
	 	</td>
		</tr> 
		
		</logic:present>
	</table>
</his:statusNew>	
</his:ContentTag>


	<his:ButtonToolBarTag>
         <div align="center">	
			<his:statusNew>
				<img class="button" src="/HISInvestigationG5/hisglobal/images/btn-view.png" style="cursor: pointer;" onkeypress="if(event.keyCode==13) getData();" tabindex="1" onclick="getData();">
	        	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
	        </his:statusNew>
         </div>
     </his:ButtonToolBarTag>
     
   <html:hidden name="masterVerificationFB" property="hmode"/>
   <html:hidden name="masterVerificationFB" property="columnName"/>
   <html:hidden name="masterVerificationFB" property="orderByString"/>
   <html:hidden name="masterVerificationFB" property="criteriaValue"/>
   <html:hidden name="masterVerificationFB" property="criteriaLabel"/>
   <html:hidden name="masterVerificationFB" property="isGrouped"/>
   
   </html:form>
   
  <his:status/>      

</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
