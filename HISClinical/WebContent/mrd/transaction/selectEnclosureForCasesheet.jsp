<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.CaseSheetDispatchFB"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> 
<!--<his:javascript src="/mrd/js/caseSheetDispatch.js"/>-->

<script type="text/javascript">
window.onload = function()
{
	if(document.getElementsByName("hmode")[0].value == "ADDENCLOSURES")
	{
		var index=document.getElementsByName("index")[0].value;
		opener.document.getElementById("isEnclosureSelected"+index).value="1";
		//opener.document.getElementsByName("isEnclosureSelected")[index].value="1";
		//alert("selectEnclosure"+index);
		opener.document.getElementById("selectEnclosure"+index).value="Modify Enclosure";
		//opener.document.getElementsByName("selectEnclosure")[index].value="Modify Enclosure";

		self.close();
		
		
	}
	
	
	var checkedItem=document.getElementsByName("checkedItem")[0].value.split("#");
	var selectedEnclosureId=document.getElementsByName("selectedEnclosureId");
	for(var i=0;i<selectedEnclosureId.length;i++)
	{
		for(var j=0;j<checkedItem.length;j++)
		{
			if(checkedItem[j]==selectedEnclosureId[i].value)
			{
				selectedEnclosureId[i].checked=true;
			}
		}	
	}
	
	
	
	
	var checkedChecklist=document.getElementsByName("checkedChecklist")[0].value.split("#");
	var selectedCheckListId=document.getElementsByName("selectedCheckListId");
	for(var i=0;i<selectedCheckListId.length;i++){
		for(var j=0;j<checkedChecklist.length;j++){
			if(checkedChecklist[j]==selectedCheckListId[i].value)
			{
				selectedCheckListId[i].checked=true;
				showRemarksTextBox(selectedCheckListId[i],i);
			}
		}	
	}
	
	
	
	
	
	var checkedItem=document.getElementsByName("checkedItem")[0].value.split("#");
	var selectedEnclosureId=document.getElementsByName("selectedEnclosureId");
	for(var i=0;i<selectedEnclosureId.length;i++)
	{
		for(var j=0;j<checkedItem.length;j++)
		{
			if(checkedItem[j]==selectedEnclosureId[i].value)
			{
				selectedEnclosureId[i].checked=true;
			}
		}	
	}
	
	//Added By manisha gangwar date: 15.7.2016
    var compulArr=document.getElementsByName('isCompulsoryArray');
	for(var k=0;k<compulArr.length;k++)
	{
	if(document.getElementsByName('isCompulsoryArray')[k].value==<%=MrdConfig.IS_COMPULSORY_YES%>)
	 	{
	 		document.getElementsByName('selectedCheckListId')[k].checked=true;
	 		 document.getElementsByName('remarks')[k].disabled=false;
	 		//showPageTextBox(document.getElementsByName('selectedCheckListId')[i]);
		}
	}
	
	
    var compulCheckArr=document.getElementsByName('compulsoryEnclosureArray');
	for(var m=0;m<compulCheckArr.length;m++)
		{
	if(document.getElementsByName('compulsoryEnclosureArray')[m].value==<%=MrdConfig.IS_COMPULSORY_YES%> )
 	{
	 		document.getElementsByName('selectedEnclosureId')[m].checked=true;
	 		 document.getElementsByName('pages')[m].disabled=false;
	 		 if(document.getElementsByName('pages')[m].value=="")
	 		 document.getElementsByName('pages')[m].value="1";
	 		//showRemarksTextBox(document.getElementsByName('selectedEnclosureId')[j],j);
 	} 
		}
	
	

	
	//showRemarksTextBox()
}

// end onload


 function checkAllEnclosure(obj){
	var enclosure=document.getElementsByName("selectedEnclosureId");
	if(obj.checked){
		for(var i=0;i<enclosure.length;i++){
			document.getElementsByName("selectedEnclosureId")[i].checked=true;
			showPageTextBox(obj);
		}	
	}
	else{
		for(var i=0;i<enclosure.length;i++){
			document.getElementsByName("selectedEnclosureId")[i].checked=false;
			showPageTextBox(obj);
		}	
	
	}
}

function showPageTextBox(enclosureId)
 {
	//alert("hi");
    var length=document.getElementsByName('selectedEnclosureId').length;
    for(var i=0;i<length;i++)
    {
    if(document.getElementsByName('selectedEnclosureId')[i].checked)
     {
       document.getElementsByName('pages')[i].disabled=false;
       if( document.getElementsByName('pages')[i].value!="1" &&  document.getElementsByName('pages')[i].value=="")
       document.getElementsByName('pages')[i].value=1;
     
     }else{
       document.getElementsByName('pages')[i].disabled=true;
      document.getElementsByName('pages')[i].value="";
       
     }
     }
 }
  
 function checkAllChecklist(obj){
	var checkList=document.getElementsByName("selectedCheckListId");
	if(obj.checked){
		for(var i=0;i<checkList.length;i++){
			document.getElementsByName("selectedCheckListId")[i].checked=true;
			showRemarksTextBox(obj,i);
		}	
		document.getElementsByName('remarks')[0].focus();
	}
	else{
		for(var i=0;i<checkList.length;i++){
			document.getElementsByName("selectedCheckListId")[i].checked=false;
			showRemarksTextBox(obj,i);
		}	
	
	}
}

function showRemarksTextBox(obj,index)
 {
     var length=document.getElementsByName('selectedCheckListId').length;
   /* for(var i=0;i<length;i++)
    {
    if(document.getElementsByName('selectedCheckListId')[i].checked)
     {
       document.getElementsByName('remarks')[i].disabled=false;
       document.getElementsByName('remarks')[i].focus();
     
     }else{
       document.getElementsByName('remarks')[i].disabled=true;
       document.getElementsByName('remarks')[i].value="";
     }
     }*/
     
     if(obj.checked)
     {
       document.getElementsByName('remarks')[index].disabled=false;
       document.getElementsByName('remarks')[index].focus();
     
     }else{
       document.getElementsByName('remarks')[index].disabled=true;
       document.getElementsByName('remarks')[index].value="";
     }
 }
  
function isCompulsory()
{
	var enclosureLength = document.getElementsByName('selectedEnclosureId').length;
	var checklistLength = document.getElementsByName('selectedCheckListId').length;
     
	for(var j=0;j<enclosureLength;j++)
    {
		if(document.getElementsByName('compulsoryEnclosureArray')[j].value==<%=MrdConfig.IS_COMPULSORY_YES%> )
     	{
 	 		if(!document.getElementsByName('selectedEnclosureId')[j].checked)
 	 		{
	 	 		alert('Select Compulsory Enclosure ::  '+document.getElementsByName('enclosureNameArray')[j].value);
	 	 		document.getElementsByName('selectedEnclosureId')[j].focus();
	 	 	 	return false;
	 	 	}
	 	}
 	   	if(document.getElementsByName('selectedEnclosureId')[j].checked)
 	 	{
 	 		if(document.getElementsByName('pages')[j].value==""||parseInt(document.getElementsByName('pages')[j].value)==0)
 			{
 				if(document.getElementsByName('pages')[j].value=="")
 	 			alert("Enter No of pages for "+ document.getElementsByName('enclosureNameArray')[j].value);
 	 			else if(parseInt(document.getElementsByName('pages')[j].value)==0)
 	 			alert("Enter Valid No of pages for "+ document.getElementsByName('enclosureNameArray')[j].value);
 	 			document.getElementsByName('pages')[j].focus();
 	 			return false;
 	  	 	}
 	 	}
	}
 	     
	for(var i=0;i<checklistLength;i++)
    {
 		if(document.getElementsByName('isCompulsoryArray')[i].value==<%=MrdConfig.IS_COMPULSORY_YES%>)
 	 	{
 	 		if(!document.getElementsByName('selectedCheckListId')[i].checked)
 	 		{
 	 			alert("Select Compulsory Checklist:: "+document.getElementsByName('checkListNameArray')[i].value);
 	 			document.getElementsByName('selectedCheckListId')[i].focus();
		 	 	return false;
			}  
 	 	}
	}
	return true;
}
 
function saveEnclosureDetail()
{
	if(isCompulsory())
	{
			document.getElementsByName("hmode")[0].value="ADDENCLOSURES";
			document.forms[0].submit();
	}
	else
	{
		return false;
	}	
}



function clearForm(){
	var enclosure=document.getElementsByName('selectedEnclosureId');
    var checklist=document.getElementsByName('selectedCheckListId');
  
     for(var j=0;j<enclosure.length;j++)
     {
     	enclosure[j].checked=false
     	document.getElementsByName('pages')[j].value="";
     	document.getElementsByName('pages')[j].disabled=true;
     }
     for(var i=0;i<checklist.length;i++)
     {
      	checklist[i].checked=false;
     	document.getElementsByName('remarks')[i].value="";
     	document.getElementsByName('remarks')[i].disabled=true;
     }
     
     document.getElementsByName("selectedAllEnclosureId")[0].checked=false;
     document.getElementsByName("selectedAllCheckListId")[0].checked=false;
     //checkAllEnclosure(document.getElementsByName("selectedAllEnclosureId")[0])
    // checkAllChecklist(document.getElementsByName("selectedAllCheckListId")[0])

}
</script>
<body>
	<html:form action="/caseSheetDispatch">
		<his:TransactionContainer>
			<his:statusDone> 
	<%--	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include> --%>
					
                 <his:SubTitleTag name="Enclosure Summary">
			   </his:SubTitleTag>
				<logic:present name="<%=MrdConfig.ENCLOSURE_NAMES_CASESHEET_DISPATCH_VO_ARRAY %>">			  
			   <his:ContentTag>
                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                
                <tr>
                <td width="10%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						<input type="checkbox" name="selectedAllEnclosureId" value="0" onclick="checkAllEnclosure(this)" tabindex="1"/>
					</b>	
					</font>
					</div>
					</td>
					<td width="40%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="enclosure"/>
					</b>	
					</font>
					</div>
					</td>
					<td width="50%" class="tdfonthead">
					<div align="center">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="pages"/> (consider only printed pages)
					</b>	
					</font>
					</div>
					</td>
					
                </tr>
                <logic:present  name="<%=MrdConfig.CASE_SHEET_ENCLOSURE_PRE_ADDED_VO_LIST %>">
                <logic:iterate id="enclosureDtlVOs" indexId="idxx" name="<%=MrdConfig.CASE_SHEET_ENCLOSURE_PRE_ADDED_VO_LIST %>" type="hisglobal.vo.RecordTypeWiseEnclosureMstVO" >
             	<tr>
                <td width="10%" class="tdfont">
						<div align="center">
						</div>
					</td>
                <td width="40%" class="tdfont">
					 <div align="center">
				        <font color="#000000">
				        <bean:write name="enclosureDtlVOs" property="enclosure"/>
				        </font>
				        <html:hidden name="CaseSheetDispatchFB" property="enclosureNameArray" value="<%=enclosureDtlVOs.getEnclosure() %>"/>
				         <html:hidden name="CaseSheetDispatchFB" property="compulsoryEnclosureArray" value="<%=enclosureDtlVOs.getIsCompulsory() %>"/>
				     </div>
				</td>
				<td width="50%" class="tdfont">
					<div align="center">
						<font color="#000000">
			          	<bean:write name="enclosureDtlVOs" property="pages"/>
			          	</font>
					</div>
					</td>
                </tr>
                </logic:iterate>
                </logic:present>
                
                 <logic:iterate id="enclosureDtlVOs" indexId="idx"  name="<%=MrdConfig.ENCLOSURE_NAMES_CASESHEET_DISPATCH_VO_ARRAY %>" type="hisglobal.vo.RecordTypeWiseEnclosureMstVO">
             			   
                <tr>
                <td width="10%" class="tdfont">
						<div align="center">
						<html:checkbox name="CaseSheetDispatchFB" property="selectedEnclosureId" value="<%=enclosureDtlVOs.getEnclosureId()%>" onclick="showPageTextBox(this)" tabindex="1"></html:checkbox>
									
						</div>
					</td>
                <%String style=""; %>
	               		<%style="color:#000000" ;%>
                <td width="40%" class="tdfont" style="<%=style %>">
	                <%-- 
	                <logic:equal name="enclosureDtlVOs" property="isCompulsory" value="0">
	               		<%style="color:#000000" ;%>
	                </logic:equal>
	                --%>
						 <div align="center">
			                <logic:equal name="enclosureDtlVOs" property="isCompulsory" value="<%=MrdConfig.IS_COMPULSORY_YES %>">
			                	<%-- <%style="color:#990000;" ;%> --%>
			                	<font color="red">*</font>
			                </logic:equal>
					         <bean:write name="enclosureDtlVOs" property="enclosure"/>
					        <html:hidden name="CaseSheetDispatchFB" property="enclosureNameArray" value="<%=enclosureDtlVOs.getEnclosure() %>"/>
					         <html:hidden name="CaseSheetDispatchFB" property="compulsoryEnclosureArray" value="<%=enclosureDtlVOs.getIsCompulsory() %>"/>
					     </div>
				</td>
				<td width="50%" class="tdfont">
					<div align="center">
					<%CaseSheetDispatchFB fb=(CaseSheetDispatchFB)pageContext.findAttribute("CaseSheetDispatchFB"); %>
			          &nbsp;<html:text name="CaseSheetDispatchFB" property="pages" disabled="true" maxlength="3" size="3"
							value='<%=(fb.getPages()==null?"":fb.getPages()[idx.intValue()])%>' onkeypress="return validateNumeric(event)" tabindex="1">
						</html:text>
						</div>
						</td>
                </tr>
                </logic:iterate>
                </table>
                </his:ContentTag>  
                </logic:present>  
                  
			   <logic:present name="<%=MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY %>">
                <his:SubTitleTag name="CheckList Details">
			   </his:SubTitleTag>
			   <his:ContentTag>
                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                
                <tr>
					<td width="10%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<input type="checkbox" name="selectedAllCheckListId" value="0" onclick="checkAllChecklist(this)" tabindex="1" />
					</b>	
					</font>
					</div>
					</td>
					<td width="40%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="checklist"/>
					</b>	
					</font>
					</div>
					</td>
					<td width="50%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="remarks"/>
					</b>	
					</font>
					</div>
					</td>
                </tr>
                <logic:iterate id="checklistDtlVOs" indexId="idx" name="<%=MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY %>" type="hisglobal.vo.RecordTypeCheckListMstVO" >
                <tr>
                	<td width="10%" class="tdfont">
						<div align="center">
						<%String showRemarksTextBox="showRemarksTextBox(this,"+ idx.toString()+")"; %>
						<html:checkbox name="CaseSheetDispatchFB" property="selectedCheckListId" value="<%=checklistDtlVOs.getChecklistId()%>" onclick="<%=showRemarksTextBox %>" tabindex="1" ></html:checkbox>
						
						</div>
					</td>
						<%String style=""; %>
               			<%style="color:#000000" ;%>
	                <td width="40%" class="tdfont" style="<%=style%>" >
		                <%-- 
		                <logic:equal name="checklistDtlVOs" property="isCompulsory" value="0">
		               		<%style="color:#000000" ;%>
		                </logic:equal>
		                --%>	
						 <div align="center">
			                <logic:equal name="checklistDtlVOs" property="isCompulsory" value="<%=MrdConfig.IS_COMPULSORY_YES %>">
			                	<%--style="color:#990000;" ; --%>
			                	<font color="red">*</font>
			                </logic:equal>
						     <bean:write name="checklistDtlVOs" property="checklistName"/>
						     <html:hidden name="CaseSheetDispatchFB" property="isCompulsoryArray" value="<%=checklistDtlVOs.getIsCompulsory() %>"/>
						     <html:hidden name="CaseSheetDispatchFB" property="checkListNameArray" value="<%=checklistDtlVOs.getChecklistName() %>"/>
					     </div>
					</td>
					<td width="50%" class="tdfont">
						<div align="center" >
				         <%CaseSheetDispatchFB fb=(CaseSheetDispatchFB)pageContext.findAttribute("CaseSheetDispatchFB"); %>
				          &nbsp;<html:text name="CaseSheetDispatchFB" property="remarks" disabled="true" maxlength="50" size="45"
							value='<%=(fb.getRemarks()==null?"":fb.getRemarks()[idx.intValue()]) %>' onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1">
						</html:text>
						</div>
					</td>
			
                </tr>
                </logic:iterate>
                </table>
               </his:ContentTag> 
         	</logic:present>
         
         </his:statusDone> 
         
		<his:ButtonToolBarTag>
			<his:statusDone>
				<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style="cursor:pointer" tabindex="1" onclick ="saveEnclosureDetail()" onkeypress="if(event.keyCode==13) saveEnclosureDetail()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" self.close()" onkeypress="if(event.keyCode==13)self.close()">
			</his:statusDone>	
		</his:ButtonToolBarTag>
</his:TransactionContainer>

	<html:hidden name="CaseSheetDispatchFB" property="hmode"/>
	<html:hidden name="CaseSheetDispatchFB" property="caseSheetId"/>
	<html:hidden name="CaseSheetDispatchFB" property="caseSheetType"/>
	<html:hidden name="CaseSheetDispatchFB" property="patAdmNo"/>
	<html:hidden name="CaseSheetDispatchFB" property="index"/>
	<html:hidden name="CaseSheetDispatchFB" property="selectedPatient"/>
	<html:hidden name="CaseSheetDispatchFB" property="checkedItem"/>
	<html:hidden name="CaseSheetDispatchFB" property="checkedChecklist"/>
</html:form>
<his:status/>
</body>
</html>