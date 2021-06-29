<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,registration.*,hisglobal.vo.*"%>

<%@page import="opd.transaction.controller.fb.EpisodeAllergyFB"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<%-- <his:javascript src="/registration/js/registration.js" />
 --%><his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script>

function clearForm()
{
	//document.forms[0].submit('NEW');
	document.forms[0].reset();
	}


function submitFormOnValidate(flag,mode)
{
  //alert("flag 123456 "+flag+" mode 123456 "+mode);
 // alert("cr no  "+document.getElementsByName("patCrNo")[0]);
  //alert("submitFormOnValidate")
 	if(flag)
	{
	 //alert("inside if");
		submitForm21(mode);
	}
	else{
// 	alert("elesee")
		return false;
	} 
	
}


function checkDuration()
{	
	if(document.getElementsByName("duration")[0])
	{
		var valid= true;
		var dob = document.getElementsByName("patDOB")[0].value;
		var duration = document.getElementsByName("duration")[0].value; 
		var sysDate= document.getElementsByName("sysDate")[0].value; 
		var age= document.getElementsByName("patAge")[0].value;
		
		var sysDateArray=sysDate.split(" ");
		sysDate=sysDateArray[0];
		//alert("D.O.B ="+dob);
		//alert("SySDate ="+sysDate);
		//alert("age= "+age);
		
		//for(i=0;i<duration.length;i++)
		//{
			//alert("Duration= "+ duration);
			//if(duration>age)
			//{
				//alert("Duration cannnot be greater than Actual Age");
				//valid= false;
			//}
		//}
		var days= noOfDays(dob,sysDate);
		var calYears=days/366;
		calYears=Math.round(calYears*100)/100;
		//alert("calYears ="+calYears);
		if(duration>calYears)
			{
				alert("Duration cannnot be greater than Actual Age");
				document.getElementsByName("duration")[0].focus();
				return false;
			}
	}	
	return true;

}

function noOfDays(a,b)
{
	var valid=true;
	var days=0;
	var a_temp=a.toString();
    var aArray=a_temp.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
    var b_temp=b.toString();
    var bArray=b_temp.split("/");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
    days=((bdate-adate)/86400000);

    return days;
}

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
		/*isEmpty(allergyRemarks,"Remark") &&*/
		comboValidation(allergySensitivity,"Sensitivity") &&
		isEmpty(duration,"Duration in Years")
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

function validateIt(tableId)
{	
	if(document.getElementsByName("hiddenPatDeadStatus")[0].value==<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>)
	{
		alert("Patient is dead");
		return false;
	}
	
	if(!validateAdd(tableId))
		{
		return false;
		}
	if(!validateAdVice())
		{
		return false;
		}
	//alert("final return ");
	return true
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
		alert("Please Enter The Revoke Remark");
		document.getElementsByName("revokeRemarks")[index].focus();
		return false;
	}
	else
	{
		submitForm21('REVOKE');
	}	
}
</script>

<%@page
	import="hisglobal.presentation.*,hisglobal.persistence.*,opd.*"%>





	<his:TitleTag>
		<his:name>
			<bean:message key="episodeAllergies" />
		</his:name>
	</his:TitleTag>
<%String tableId=""; %>	
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
	
<his:statusTransactionInProcess>
<%

if(session.getAttribute(OpdConfig.ARRAY_EPISODE_ALLERGY_VO)!=null){ %>
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
							<bean:message key="remark"/>
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
	  				<%-- <%=prevAllergyVO.getAllergyTypeName() %>  --%>  Drug Allergy
	  				<%-- <%=prevAllergyVO.getAllergyName() %> --%>
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
	  				<img class="button" id="revokeButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/Minus.png"/>'  title="Revoke Allergy" onclick="revokeAllergy('<%=prevAllergyVO.getAllergyID()%> ','<%=idx.toString() %>')">
	  			</div>
	  		</td>
	  	</tr>
	  
	 </logic:iterate>
	</table>
	</his:ContentTag>
	
	<%} %>
	
	
	<his:SubTitleTag name="Add Allergies Detail"> </his:SubTitleTag>
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" style="display:none">
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
			<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' title="Add Allergy" onclick="submitFormOnValidate(validateAddAlergyType(),'ADDNEWALLERGY')">
			</div>
			</td> 
	  		</tr>
	  	
	  	</table>
	</his:ContentTag>
	
	
	<%int i=0; %>
	<logic:notEmpty name="<%=OpdConfig.MAP_SELECTED_ALLERGIES%>" >
		<logic:iterate id="mapSelectedAllergies" indexId="idX" name="<%=OpdConfig.MAP_SELECTED_ALLERGIES%>" type="java.util.Map.Entry"  >
			<bean:define  name="mapSelectedAllergies" property="value"  id="innerList" type="java.util.List"/> 
				
	<%tableId=((String)mapSelectedAllergies.getKey())+"^"+idX;
	String allergyName=(String)innerList.get(0);
	System.out.println("allergyName========== "+allergyName);
	%>
	
	
	
	<b><strong><%=allergyName %></strong></b>
	  
	
			
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
							<bean:message key="dDays"/>
						</b>
					</font>
				</div>
			</td>
			<td width="22%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="remark"/></b>
				</font>
				</div>
	  		</td>
			<td  class="tdfonthead" width="10%"></td>
	  		<td  class="tdfonthead" width="10%"></td> 
	  		<td  class="tdfonthead" width="5%">
				<div align="center">   
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.gif"/>' title="Add DETAIL" onclick="if (validateAdd('<%=tableId %>'))submitAddDetail('ADDDETAIL','<%=tableId %>')" >
				</div>
			</td> 
	  	</tr>
	  	
	  	<tr>	  	
	  		<td class="tdfont" style="vertical-align: middle">
	  			<div align="center">
	  			<html:select name="OpdEpisodeAllergyFB" tabindex="1" property="reason" styleClass="regcbo" style="vertical-align: middle" >
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
	  		<td class="tdfont" style="vertical-align: middle">
	  			<div align="center">
		  			<html:select name="OpdEpisodeAllergyFB" tabindex="1" property="sensitivity"  value='<%=((EpisodeAllergyFB)pageContext.findAttribute("OpdEpisodeAllergyFB")).getSensitivity()[idX.intValue()] %>' styleClass="regcbo" style="vertical-align: middle">
		  				<logic:present name="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" >
	  					<html:options  collection="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" property="value" labelProperty="label" />
	  					</logic:present>
		  			</html:select>
	  			</div>
	  		</td>
	  		<td class="tdfont" style="vertical-align: middle">
	  			<div align="center">
	  				<html:text name="OpdEpisodeAllergyFB" property="duration" onkeypress="return validateNumeric(event)" tabindex="1" size="6" maxlength="5" value='<%=((EpisodeAllergyFB)pageContext.findAttribute("OpdEpisodeAllergyFB")).getDuration()[idX.intValue()] %>' style="vertical-align: middle"></html:text>
	  				<img class="button" src='<his:path src="/hisglobal/images/cal_duration.png"/>' style="cursor:pointer;vertical-align: middle;" title="Duration Calculator" onclick ="showDayCalculator(event,'<%=tableId %>')" onkeypress="if(event.keyCode==13) showDayCalculator(event,'<%=tableId %>');">
	  			</div>
	  		</td>
	  		<td class="tdfont" style="vertical-align: middle">
	  			<div align="center">
		  			<html:text name="OpdEpisodeAllergyFB"  maxlength="500" size="25" property="remarks" style="vertical-align: middle" 
		  				value='<%=((EpisodeAllergyFB)pageContext.findAttribute("OpdEpisodeAllergyFB")).getRemarks()[i++] %>' 
		  				onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
	  			</div>
	  		</td>
	  		<td class="tdfont" style="vertical-align: middle">
	  			<div align="center">  
	  				<% String allergySitePath="/HISClinical/opd/opdEpisodeAllergies.cnt?hmode=ALLERGYSITE&allergyTableId="+tableId; %>
	  				<a style="cursor:pointer" onclick="getAllergySite(event,'<%= allergySitePath%>')" >
	  					<bean:message key="allergySite"/>
	  				</a>	
	  			</div>
	  		</td>
	  		<td class="tdfont" style="vertical-align: middle">
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
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' title="Remove Detail" onclick="submitFormRemoveDetail('REMOVEDETAIL','<%=innerKey %>','<%=tableId %>')">
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
				<td width="100%" class="tdfont" style="vertical-align: middle;">
					<div align="center">
						<html:textarea name="OpdEpisodeAllergyFB" property="adviceText" rows="2" cols="90" style="vertical-align: middle;" 
							onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"  tabindex='1'>
						</html:textarea>
						&nbsp;
						<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Select Advice" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="addAdvice(event)" onkeypress="if(event.keyCode==13) addAdvice(event)">
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
</his:statusTransactionInProcess>
<his:ButtonToolBarTag>
<his:statusTransactionInProcess>
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick =  "if(checkDuration())submitFormOnValidate(validateIt('<%=tableId%>'),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateIt(),'SAVE');")>
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
          <%-- <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');"> --%>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
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
<html:hidden name="OpdEpisodeAllergyFB" property="patDOB"/>
<html:hidden name="OpdEpisodeAllergyFB" property="patAge"/>
<html:hidden name="OpdEpisodeAllergyFB" property="sysDate"/>


