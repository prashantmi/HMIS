
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,registration.*,hisglobal.vo.*"%>

<%@page import="opd.transaction.controller.fb.EpisodeAllergyFB"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<his:javascript src="/opd/opdJs/opdAjax.js"/>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script>
function validateAddAlergyType()
{	
	if(document.getElementsByName("hiddenPatDeadStatus")[0].value==<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>)
	{
		alert("Patient is dead");
		return false;
	}
	
	
	var valid=false
	valid=isSelected(document.forms[0].allergyCode,'Allergy Type')
	if(valid)
	{
		document.forms[0].numberOfTables.value=parseInt(document.forms[0].numberOfTables.value)+1
	}
	
	return valid
	
}

function submitAddDetail(mode,tableId)
{
	var seqno=tableId.substr(tableId.indexOf("^")+1);
	document.getElementsByName('allergyTableId')[0].value=tableId;
	
	//alert(document.getElementsByName('allergyTableId')[0].value);
	submitForm(mode);
}
function submitFormRemoveAllergy(mode,tableId)
{// alert("tableid===="+tableId);
	document.getElementsByName('allergyTableId')[0].value=tableId;
	// alert(document.getElementsByName('allergyTableId')[0].value);
	document.forms[0].numberOfTables.value=parseInt(document.forms[0].numberOfTables.value)-1
	submitForm(mode);
}
function submitFormRemoveDetail(mode,rowId,tableId)
{
	var seqno=tableId.substr(tableId.indexOf("^")+1);
	document.getElementsByName('reasonDetalRowId')[0].value=rowId;
	
	// alert(document.getElementsByName('reasonDetalRowId')[0].value);
	document.getElementsByName('allergyTableId')[0].value=tableId;
	// alert(document.getElementsByName('allergyTableId')[0].value);
	submitForm(mode);
}

function validateAdd(tableId)
{
	
	var tableno=tableId.substring(0,tableId.indexOf('^'))
	var seqno=tableId.substr(tableId.indexOf("^")+1);
	
	// alert("tableno==="+tableno)
	// alert("seqno===="+seqno)
	var valid=true
	var indexNO
	allergyReason		= document.getElementsByName("reason")[seqno]
	allergyRemarks	= document.getElementsByName("remarks")[seqno]
	allergySensitivity		= document.getElementsByName("sensitivity")[seqno]
	duration = document.getElementsByName("duration")[seqno]
	
	if(comboValidation(allergyReason,"Allergy Name") &&
		/*isEmpty(allergyRemarks,"Remarks") &&*/
		comboValidation(allergySensitivity,"Sensitivity") &&
		isEmpty(duration,"Duration in Days")
		)
		{
		valid=true
		
		}
		else
		{
		valid=false
		}
		return valid
}

function validateIt()
{	
	if(document.getElementsByName("hiddenPatDeadStatus")[0].value==<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>)
	{
		alert("Patient is dead");
		return false;
	}
	
	
	
	var valid=false
	var numberOfTable=document.forms[0].numberOfTables.value;
	if(numberOfTable>0 )
	{
		for(i=0;i<numberOfTable;i++)
		{
			if(comboValidation(document.getElementsByName("reason")[i],"Allergy Name") &&
			/*isEmpty(document.getElementsByName("remarks")[i],"Remarks") &&*/
			comboValidation(document.getElementsByName("sensitivity")[i],"Sensitivity") &&
			isEmpty(document.getElementsByName("duration")[i],"Duration in Days")
			&& validateAdVice()
			)
			{
				valid=true
		//alert("inner if true")
			}
			else
			{
			//alert("inner if false")
				valid=false
				return valid
			}
		}
	}
	else
	{
	
		alert("Please Select an Allergy Type");
		document.getElementsByName("allergyCode")[0].focus();
	}
	//alert("final return ")
	return valid
}

function validateAdVice()
{
	var valid=true;
	if(document.getElementsByName('adviceText')[0].value.length>500)
	 {
	 	alert("Characters in Advice should be less than 500");
	 	document.getElementsByName("adviceText")[0].focus();
	 	valid= false;
	 }
	 return valid;
}

function getAllergyDetail(event,path)
{
	openPopup(createFHashAjaxQuery(path),event,300,600);
}

function getAllergySite(event,path)
{
	openPopup(createFHashAjaxQuery(path),event,300,600);
}

function getSymptoms(event,path)
{
	openPopup(createFHashAjaxQuery(path),event,300,600);
}

function submitAllergySite()
{
	elem = document.getElementsByName('hmode')[0];
	alert(elem);
	elem.value = "ADDDETAIL";
	document.forms[0].submit();
}

/*function showAdvice()
{
	var advice=document.getElementsByName("adviceHeader")[0].value;
	if(advice=="-1")
		document.getElementsByName("adviceText")[0].value="";
	else	
		document.getElementsByName("adviceText")[0].value=document.getElementsByName("adviceHeader")[0].value;
}*/

function addAdvice(event)
{
	var path='/HISClinical/opd/opdEpisodeAllergies.cnt?hmode=ADDADVICE';
	openPopup(createFHashAjaxQuery(path),event,300,600);
}

function showDayCalculator(event,obj)
{
	
	var path='/HISClinical/opd/opdEpisodeAllergies.cnt?hmode=DAYCAL&allergyTableId='+obj;
	openPopup(createFHashAjaxQuery(path),event,200,500);
}

function revokeAllergy(obj1,obj2)
{
	if(document.getElementsByName("hiddenPatDeadStatus")[0].value==<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>)
	{
		alert("Patient is dead");
		return false;
	}
	
	var alertId=obj1;
	var index=obj2;
	document.getElementsByName("revokeAllergyId")[0].value=alertId;
	document.getElementsByName("index")[0].value=index;
	if(document.getElementsByName("revokeRemarks")[index].value=="")
	{
		alert("Please Enter The Revoke Remarks");
		document.getElementsByName("revokeRemarks")[index].focus();
		return false;
	}
	else
	{
		submitForm('REVOKE');
	}	
}
</script>

<%@page
	import="hisglobal.presentation.*,hisglobal.persistence.*,opd.*"%>




<his:TransactionContainer>

	<his:TitleTag>
		<his:name>
			<bean:message key="episodeAllergies" />
		</his:name>
	</his:TitleTag>
	
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
	
<his:statusTransactionInProcess>
<%if(session.getAttribute(OpdConfig.ARRAY_EPISODE_ALLERGY_VO)!=null){ %>
		<his:SubTitleTag name="Previous Allergies Detail">
		</his:SubTitleTag>
				
		<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="allergyType"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="25%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="duration"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="allergyName"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<font color="#FF0000">*</font>
							<bean:message key="revoke"/>
							<bean:message key="remarks"/>
						</b>
					</font>
				</div>
	  		</td>
	  		
	  		<td width="5%"  class="tdfonthead">
				<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="revoke"/>
						</b>
					</font>
				</div>
	  		</td>
	  		
	  	</tr>
	
	<logic:iterate id="prevAllergyVO" indexId="idx" name="<%=OpdConfig.ARRAY_EPISODE_ALLERGY_VO%>" type="hisglobal.vo.PatAllergyDtlVO">
		<tr>
	  		<%String path="/HISClinical/opd/opdEpisodeAllergies.cnt?hmode=PREVIOUS&selectedAllergyCode="+(String) prevAllergyVO.getAllergyTypeCode()+"&selectedAllergyTypeName="+(String)prevAllergyVO.getAllergyTypeName()+"&selectedAllergyID="+(String)prevAllergyVO.getAllergyID()+"&selectedAllergyName="+(String) prevAllergyVO.getAllergyName(); %>
	  		<td class="tdfont">
	  			<div align="center">
	  				<%=prevAllergyVO.getAllergyTypeName() %>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  				<%=prevAllergyVO.getDurationDays() %>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  				<a style="cursor:pointer" onclick="getAllergyDetail(event,'<%= path%>')" >	
	  					<%= prevAllergyVO.getAllergyName()%>
	  				</a>	
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">
	  				<html:text name="OpdEpisodeAllergyFB" property="revokeRemarks" value="" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>	
	  			</div>
	  		</td>
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  				<img class="button" id="revokeButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus_new.png"/>'  title="Revoke Allergy" onclick="revokeAllergy('<%=prevAllergyVO.getAllergyID()%> ','<%=idx.toString() %>')">
	  			</div>
	  		</td>
	  	</tr>
	  
	 </logic:iterate>
	</table>
	</his:ContentTag>
	
	<%} %>
	
	
	<his:SubTitleTag name="Add Allergies Detail"> </his:SubTitleTag>
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
		
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="allergyType"/></b>
				</font>
				</div>
	  		</td>
	  		<td width="20%" class="tdfont">
	  			<div align="center">
	  			<html:select name="OpdEpisodeAllergyFB" tabindex="1" property="allergyCode" styleClass="regcbo" >
	  				<html:option value="-1">Select Value</html:option>
	  				<logic:present name="<%=OpdConfig.ESSENTIALBO_LIST_ALL_ALLERGY_TYPES%>" >
					<html:options  collection="<%=OpdConfig.ESSENTIALBO_LIST_ALL_ALLERGY_TYPES%>" property="value" labelProperty="label" />
					</logic:present>
	  			</html:select>
	  			</div>
	  		</td>
	  		<td  class="tdfont">
			<div align="center">   
			<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/sign-plus.png"/>' title="Add Allergy" onclick="submitFormOnValidate(validateAddAlergyType(),'ADDNEWALLERGY')">
			</div>
			</td> 
	  		</tr>
	  	
	  	</table>
	</his:ContentTag>
	
	
	<%int i=0; %>
	<logic:notEmpty name="<%=OpdConfig.MAP_SELECTED_ALLERGIES%>" >
		<logic:iterate id="mapSelectedAllergies" indexId="idX" name="<%=OpdConfig.MAP_SELECTED_ALLERGIES%>" type="java.util.Map.Entry"  >
			<bean:define  name="mapSelectedAllergies" property="value"  id="innerList" type="java.util.List"/> 
				
	<%String tableId=((String)mapSelectedAllergies.getKey())+"^"+idX;
	String allergyName=(String)innerList.get(0);
	System.out.println("allergyName========== "+allergyName);
	%>
	
	
	
	<b><strong><%=allergyName %></strong></b>
	  
	<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/sign-minus.png"/>' title="Remove Allergy" onclick="submitFormRemoveAllergy('REMOVEALLERGY','<%=tableId %>')">
			
	<his:ContentTag>
	<table id="<%=tableId %>" width="100%" border="0" cellspacing="1" cellpadding="0">
	
	<tr>
		
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><font color="#FF0000">*</font>
				<bean:message key="allergyName"/></b>
				</font>
				</div>
	  		</td>
	  			  	
		  	<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>
					<bean:message key="sensitivity"/></b>
					</font>
					</div>
		  	</td>
	  		
			<td  class="tdfonthead" width="13%">
				<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<font color="#FF0000">*</font>
							<bean:message key="duration"/>
						</b>
					</font>
				</div>
			</td>
			<td width="22%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="remarks"/></b>
				</font>
				</div>
	  		</td>
			<td  class="tdfonthead" width="10%"></td>
	  		<td  class="tdfonthead" width="10%"></td> 
	  		<td  class="tdfonthead" width="5%">
				<div align="center">   
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/sign-plus.png"/>' title="Add DETAIL" onclick="if (validateAdd('<%=tableId %>'))submitAddDetail('ADDDETAIL','<%=tableId %>')" >
				</div>
			</td> 
	  		
	  	</tr>
	  	
	  	
	  	<tr>
	  	
	  		<td class="tdfont">
	  		<div align="center">
	  			<html:select name="OpdEpisodeAllergyFB" tabindex="1" property="reason" styleClass="regcbo" >
					<html:option value="-1">Select Value</html:option>
					<%
					List allergyReason=(List)innerList.get(1);
					String nameOfList="allergyReason"+idX;
					request.setAttribute(nameOfList,allergyReason);%>
					<logic:present name="<%=nameOfList%>" >
  					<html:options  collection="<%=nameOfList%>" property="value" labelProperty="label" />
  					</logic:present>
	  			</html:select>
	  			</div>
	  		</td>
	  	
	  		
	  		<td class="tdfont">
	  		
	  			<div align="center">

	  			<html:select name="OpdEpisodeAllergyFB" tabindex="1" property="sensitivity"  value='<%=((EpisodeAllergyFB)pageContext.findAttribute("OpdEpisodeAllergyFB")).getSensitivity()[idX.intValue()] %>' styleClass="regcbo">
	  				<logic:present name="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" >
  					<html:options  collection="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" property="value" labelProperty="label" />
  					</logic:present>
	  			</html:select>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">  
	  				<html:text name="OpdEpisodeAllergyFB" property="duration" onkeypress="return validateNumeric(event)" tabindex="1" size="6" maxlength="5" value=""></html:text>
	  				<img class="button" src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Day Calculator " onclick ="showDayCalculator(event,'<%=tableId %>')" onkeypress="if(event.keyCode==13) showDayCalculator(event);">
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">

	  			<html:text name="OpdEpisodeAllergyFB"  maxlength="500" size="25" property="remarks" 
	  				value='<%=((EpisodeAllergyFB)pageContext.findAttribute("OpdEpisodeAllergyFB")).getRemarks()[i++] %>' 
	  				onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"  ></html:text>

	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">  
	  				<% String allergySitePath="/HISClinical/opd/opdEpisodeAllergies.cnt?hmode=ALLERGYSITE&allergyTableId="+tableId; %>
	  				<a style="cursor:pointer" onclick="getAllergySite(event,'<%= allergySitePath%>')" >
	  					<bean:message key="allergySite"/>
	  				</a>	
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  			<div align="center">  
	  				<% String symptomsPath="/HISClinical/opd/opdEpisodeAllergies.cnt?hmode=GETSYMPTOM&allergyTableId="+tableId; %>
	  				<a style="cursor:pointer" onclick="getSymptoms(event,'<%= symptomsPath%>')" >	
	  					<bean:message key="symptoms"/>
	  				</a>	
	  			</div>
	  		</td>
	  		<td class="tdfont"></td>
	  		
	  		<html:hidden name="OpdEpisodeAllergyFB" property="hiddenAllergyCode" value="<%=(String)mapSelectedAllergies.getKey() %>"/>
	  		
	  		
	  	</tr>
	  	<logic:notEmpty name="<%=OpdConfig.OUTER_ALLERGY_MAP %>">
	  	<logic:iterate id="outerMap" name="<%=OpdConfig.OUTER_ALLERGY_MAP %>" >
	  	<bean:define id="outerKey" name="outerMap" property="key" ></bean:define>
	  	<%
	  
	  	if(((String)mapSelectedAllergies.getKey()).equals(outerKey))
	  		{%>
	  	<bean:define id="innerMap" name="outerMap" property="value" ></bean:define>
	  	<logic:notEmpty name="innerMap" >
	  		<logic:iterate id="innerlist" name="innerMap" >
	  	
	  			<logic:notEmpty name="innerlist">
	  			<bean:define id="list" name="innerlist" property="value" type="java.util.List" ></bean:define>
	  				<bean:define id="innerKey" name="innerlist" property="key" ></bean:define>
	  	<tr id="<%=innerKey %>">
	  	
	  	<td class="tdfont">
	  			
	  		<div align="center">
	  			<%=list.get(0) %>
	  		</div></td>
	  		<td class="tdfont">
	  		<div align="center">
	  			<%=list.get(4) %>
	  		</div>
	  		</td>
	  		
	  		
	  		<td class="tdfont">
	  			<div align="center">
	  				<%=list.get(10) %>
	  			</div>
	  		</td>
	  		<td class="tdfont">
	  		<div align="center">
	  			<%=list.get(1) %>
	  		</div>
	  		</td>
			<td class="tdfont">
	  		<div align="center">
	  			<%=(list.get(6)==null)?"-":list.get(6) %>
	  		</div>
	  		</td>
	  		<td class="tdfont">
	  		<div align="center">
	  			<%=(list.get(7)==null)?"-":list.get(7) %>
	  		</div>
	  		</td>
	  		<td  class="tdfont">
				<div align="center">   
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/sign-minus.png"/>' title="Remove Detail" onclick="submitFormRemoveDetail('REMOVEDETAIL','<%=innerKey %>','<%=tableId %>')">
				</div>
			</td> 
	  	</tr>
	  			</logic:notEmpty>
	  		</logic:iterate>
	  		</logic:notEmpty>
	  		<%} %>
	  	</logic:iterate>
	  	</logic:notEmpty>
	</table>
	</his:ContentTag>
	</logic:iterate>
	</logic:notEmpty>


	<his:ContentTag>
	<his:SubTitleTag name="Advice">
	</his:SubTitleTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="75%" class="tdfont">
					<div align="center">
						<html:textarea name="OpdEpisodeAllergyFB" property="adviceText" rows="2" cols="90" 
							onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"  tabindex='1'>
						</html:textarea>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
						<html:button value="Add Advice"  property="mybutton" onclick="addAdvice(event)" style='cursor:pointer'  tabindex='1'/>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
</his:statusTransactionInProcess>
<his:ButtonToolBarTag>
<his:statusTransactionInProcess>
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick =  "submitFormOnValidate(validateIt(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'SAVE');")>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
</his:statusTransactionInProcess>
<his:statusUnsuccessfull>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
</his:statusUnsuccessfull>
</his:ButtonToolBarTag>	

<html:hidden name="OpdEpisodeAllergyFB" property="hmode"/>
<html:hidden name="OpdEpisodeAllergyFB" property="patCrNo"/>
<html:hidden name="OpdEpisodeAllergyFB" property="allergyTableId"/>
<html:hidden name="OpdEpisodeAllergyFB" property="reasonDetalRowId"/>
<html:hidden name="OpdEpisodeAllergyFB" property="numberOfTables"/>
<html:hidden name="OpdEpisodeAllergyFB" property="revokeAllergyId"/>
<html:hidden name="OpdEpisodeAllergyFB" property="index"/>
<html:hidden name="OpdEpisodeAllergyFB" property="hiddenPatDeadStatus"/>
</his:TransactionContainer>
