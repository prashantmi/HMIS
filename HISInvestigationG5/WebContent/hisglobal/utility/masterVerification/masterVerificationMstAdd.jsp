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
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script>
var query="SELECT ";
//var column="";
var whereCondition=" WHERE " 
function getMasterList(obj){
	document.getElementsByName("hmode")[0].value='GETMASTERLIST'
	//alert(obj.value)
	document.getElementsByName("moduleId")[0].value=obj.value
	document.forms[0].submit()
}
function getColumnList(obj){
	if(obj.value!="-1"){
		document.getElementsByName("hmode")[0].value='GETCOLUMN'
		document.getElementsByName("masterName")[0].value=obj.value
		document.forms[0].submit();
	}
	else{
		document.getElementById("orderByDiv").innerHTML=null;
		
	}	
}
function enableCriteria(obj){
	if(obj.checked){
		document.getElementById("criteriaDiv").style.display="block"
	}
	else{
		document.getElementById("criteriaDiv").style.display="none"
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
   
   
   //popup=window.open("/HISInvestigationG5/utility/masterVerification.cnt?hmode=GETDATA&orderByString=" + orderByString +"&criteria=" + criteria +"&criteriaColumn=" + criteriaColumn, 
	//	"Master Data","scrollbars=yes, height=500,width=800 ,top=" + "150" + ",left=" + "150" );
	document.getElementsByName("hmode")[0].value='GETDATA'
	document.forms[0].submit()
}

window.onload=function(){
	showMainQuery(null)
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
		title ,"scrollbars=yes, height=500,width=800 ,top=" + "150" + ",left=" + "150" );
	}
	
}

function getCriteriaData(obj){
	if(obj.value!="-1"){
	 	submitForm('GETCRITERIADATA')
		document.getElementsByName("criteriaCode")[0].disabled=false
		document.getElementsByName("orderBy")[0].disabled=false
	}
	else{
		document.getElementsByName("criteriaCode")[0].value="-1"
		document.getElementsByName("criteriaCode")[0].disabled=true
		document.getElementsByName("orderBy")[0].disabled=true
	}	 
}

function showMainQuery(obj){
	query="";
	//if(column==""){
	//	column+=document.getElementsByName("columnValue")[0].value
	//}else{	 
	//	column+=", "+ document.getElementsByName("columnValue")[0].value
	//}	
	//column=column.substring(1);
	if(document.getElementsByName("whereConditionQuery")[0].value==""){
		whereCondition="";
	}
	else{
		whereCondition+=document.getElementsByName("whereConditionQuery")[0].value;
	}
	var column=document.getElementsByName("columnQuery")[0].value
	query="SELECT "+ column +" FROM "+ document.getElementsByName("masterName")[0].value + whereCondition;
	document.getElementById("mainQuerytd").innerHTML=query;
	document.getElementsByName("mainQuery")[0].value=query;
	
}
function addColumn(){
	submitForm('ADDCOLUMN')
}
function addWhereCondition(){
	submitForm('ADDWHERECONDITION')
}
</script>

<%try{ %>	
<his:TransactionContainer>
<html:form action="/masterVerificationMst">	   
		   
   <his:TitleTag name="Master Verification Master"> 		
   </his:TitleTag>
   
<his:ContentTag>
<his:statusNew>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="25%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="modulename"/>
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont">
			&nbsp;<html:select name="masterVerificationMstFB" property="moduleId" tabindex="1" styleClass="regcbo" onchange="getMasterList(this);" style="width:200px">
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
			  <bean:message key="master"/> <bean:message key="name"/>
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont">
			&nbsp;<html:select name="masterVerificationMstFB" property="masterName" tabindex="1" styleClass="regcbo" onchange="getColumnList(this);" style="width:200px">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=Config.MASTER_LIST %>">
				<html:options collection="<%=Config.MASTER_LIST %>" labelProperty="label" property="value"/>
				</logic:present>
			</html:select>
		  </td>
		 </tr>
		</table> 
	<logic:present name="<%=Config.MASTER_COLUMN_LIST %>">
	<his:SubTitleTag name="Column Detail">
	</his:SubTitleTag>	
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>  
		 <td width="15%" class="tdfonthead">
			<div align="center">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="columnName"/>
			 </font>
			</div>
		  </td>
		
		 <td width="15%" class="tdfonthead">
			<div align="center">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="columnLabel"/>
			 </font>
			</div>
		  </td>
		
		 <td width="20%" class="tdfonthead">
		  <div align="center">
		  	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				Query Required
			</font>
			</div>
		 </td>
		 <td width="5%" class="tdfonthead">
		  	
		  </td>
		 </tr>
		 <tr>
		 	 <td width="15%" class="tdfont">
			<div align="center">
			<html:select name="masterVerificationMstFB" property="columnValue" tabindex="1" styleClass="regcbo" style="width:150px" onchange="showMainQuery(this)">
				<html:option value="-1">Select Value</html:option>
				<html:options collection="<%=Config.MASTER_COLUMN_LIST %>" labelProperty="label" property="value"/>
			</html:select>
			</div>
		  </td>
		   <td width="15%" class="tdfont">
			<div align="center">
			<html:text name="masterVerificationMstFB" property="columnName" tabindex="1"/>
			</div>
		 </td>
		  <td width="20%" class="tdfont">
			<div align="center">
			<input type="checkbox" name="isQueryRequired" onclick=""/>
			</div>
		 </td>
		 <td width="5%" class="tdfont">
		 <div align="center">
				<img class="button" id="addButton" style="cursor: pointer;" 
				src="/HISInvestigationG5/hisglobal/images/RoundPlus12x12.png" tabindex="1" 
				title="Add to List" onclick="addColumn()" onkeypress="if(event.keyCode==13) addColumn()">
			</div>
		 </td>
		 </tr>
		<logic:present name="AddedColumnList">
			<logic:iterate id="columnEntry" name="AddedColumnList" type="hisglobal.utility.Entry">
			<tr>
		 	 <td width="15%" class="tdfont">
			<div align="center">
			<bean:write name="columnEntry" property="value"/>
			</div>
		  </td>
		   <td width="15%" class="tdfont">
			<div align="center">
			<bean:write name="columnEntry" property="label"/>
			</div>
		 </td>
		  <td width="20%" class="tdfont">
			<div align="center">
			
			</div>
		 </td>
		 <td width="5%" class="tdfont">
		 	
		 </td>
		 </tr>
		 </logic:iterate>
		</logic:present>
		</table>
	<his:SubTitleTag name="Criteria">
		<td align="right">
		<b>Is Required</b> 
		<input type="checkbox" name="isCriteriaRequired" onclick="enableCriteria(this)"/>
		</td>
	</his:SubTitleTag>	
		<div id="criteriaDiv" style="display: none;"> 
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="25%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  Criteria
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont">
			&nbsp;<html:select name="masterVerificationMstFB" property="columnValue" tabindex="1" styleClass="regcbo" style="width:150px" onchange="showMainQuery(this)">
				<html:option value="-1">Select Value</html:option>
				<html:options collection="<%=Config.MASTER_COLUMN_LIST %>" labelProperty="label" property="value"/>
			</html:select>
		  </td>
		 <td width="25%" class="tdfonthead">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  Criteria Query
			 </font>
		  </td>
		  <td width="20%" class="tdfont">
			&nbsp;<html:textarea name="masterVerificationMstFB" property="criteriaQuery" tabindex="1"/>
		 </td>
		 <td width="5%" class="tdfont">
		  	<div align="center">
			<img class="button" id="addButton" style="cursor: pointer;" 
			src="/HISInvestigationG5/hisglobal/images/RoundPlus12x12.png" tabindex="1" 
			title="Add to List" onclick="addAuditHeader()" onkeypress="if(event.keyCode==13) addAuditHeader()">
			</div>
		  </td>
		 </tr>
		</table> 
		</div>
	<his:SubTitleTag name="Where Condition">
	</his:SubTitleTag>	
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="50%" class="tdfonthead">
			<div align="center">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="columnName"/>
			 </font>
			</div>
		  </td>
		   <td width="45%" class="tdfonthead">
			<div align="center">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  Value
			 </font>
			</div>
		  </td>
		  <td width="5%" class="tdfonthead">
		  	<div align="center">
			</div>
		  </td>
		</tr>
		<tr>  
		 <td width="50%" class="tdfont">
			<div align="center">
			<html:select name="masterVerificationMstFB" property="whereConditionColumn" tabindex="1" styleClass="regcbo" style="width:150px">
				<html:option value="-1">Select Value</html:option>
				<html:options collection="<%=Config.MASTER_COLUMN_LIST %>" labelProperty="label" property="value"/>
			</html:select>
			</div>
		  </td>
		
		 <td width="45%" class="tdfont">
			<div align="center">
				<html:text name="masterVerificationMstFB" property="conditionValue" tabindex="1"/>
			</div>	
		 </td>
		 <td width="5%" class="tdfont">
		  	<div align="center">
			<img class="button" id="addButton" style="cursor: pointer;" 
			src="/HISInvestigationG5/hisglobal/images/RoundPlus12x12.png" tabindex="1" 
			title="Add to List" onclick="addWhereCondition()" onkeypress="if(event.keyCode==13) addWhereCondition()">
			</div>
		  </td>
		 </tr>
		 <logic:present name="AddedWhereConditionList">
			<logic:iterate id="whereConditionEntry" name="AddedWhereConditionList" type="hisglobal.utility.Entry">
			<tr>
			   <td width="50%" class="tdfont">
				<div align="center">
				<bean:write name="whereConditionEntry" property="label"/>
				</div>
			  </td>
			
			 <td width="45%" class="tdfont">
				<div align="center">
					<bean:write name="whereConditionEntry" property="value"/>
				</div>	
			 </td>
			 <td width="5%" class="tdfont">
			  	<div align="center">
				
				</div>
			  </td>
			</tr>
			</logic:iterate>
		</logic:present>		
		</table>
			
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr id="orderByDiv">
		 <td width="25%" class="tdfonthead">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <bean:message key="orderBy"/>
			 </font>
	
		  </td>
		 <td width="25%" class="tdfont" colspan="4">
			<table width="100%">
			<tr>
			<td width="40%">
			<html:select name="masterVerificationMstFB" property="orderBy" tabindex="1" size="5" multiple="true" style="width:200px">
				<html:options collection="<%=Config.MASTER_COLUMN_LIST %>" labelProperty="label" property="value"/>
			</html:select>
			 </td>
			 <td width="20%" class="tdfont">
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
		
		
		 <tr>  
		 <td width="25%" class="tdfonthead" rowspan="2">
			<font color="#ff0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>		
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  Main Query
			 </font>
	
		  </td>
		  <td id="mainQuerytd" width="25%" class="tdfont" colspan="4" rowspan="2">
			 
		  </td>
		 </tr>
		
	</table>
	</logic:present>
</his:statusNew>	
</his:ContentTag>


	<his:ButtonToolBarTag>
         <div align="center">	
			<his:statusNew>
				<img class="button" src="/HISInvestigationG5/hisglobal/images/btn-sv.png" style="cursor: pointer;" onkeypress="if(event.keyCode==13) submitForm('SAVE');" tabindex="1" onclick="submitForm('SAVE')">
	        	<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick ="submitForm('NEW');">
	        	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
	        </his:statusNew>
         </div>
     </his:ButtonToolBarTag>
     
   <html:hidden name="masterVerificationMstFB" property="hmode"/>
   <html:hidden name="masterVerificationMstFB" property="moduleId"/>
   <html:hidden name="masterVerificationMstFB" property="columnName"/>
   <html:hidden name="masterVerificationMstFB" property="orderByString"/>
   <html:hidden name="masterVerificationMstFB" property="criteriaValue"/>
   <html:hidden name="masterVerificationMstFB" property="criteriaLabel"/>
   <html:hidden name="masterVerificationMstFB" property="isGrouped"/>
   <html:hidden name="masterVerificationMstFB" property="columnQuery"/>
   <html:hidden name="masterVerificationMstFB" property="whereConditionQuery"/>
   <html:hidden name="masterVerificationMstFB" property="mainQuery"/>
   
   </html:form>
   
  <his:status/>      

</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
