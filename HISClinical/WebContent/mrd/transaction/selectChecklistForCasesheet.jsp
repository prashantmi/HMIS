
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
<his:javascript src="/mrd/js/caseSheetDispatch.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
window.onload=function()
{
	if(document.getElementsByName("hmode")[0].value == "ADDCHECKLIST")
	{
		var index=document.getElementsByName("index")[0].value;
		opener.document.getElementsByName("isChecklistSelected")[index].value="1"
		//opener.document.getElementById("checklistButtonDiv").style.borderStyle="inset";
		opener.document.getElementById("displaySelectedDiv").style.display="block";
		self.close();
	}

	var checkedItem=document.getElementsByName("checkedChecklist")[0].value.split("#")
	var selectedCheckListId=document.getElementsByName("selectedCheckListId")
	for(var i=0;i<selectedCheckListId.length;i++){
		for(var j=0;j<checkedItem.length;j++){
			if(checkedItem[j]==selectedCheckListId[i].value){
				selectedCheckListId[i].checked=true;
			}
		}	
	}
	showRemarksTextBox();
}

function checkAllChecklist(obj){
	var checkList=document.getElementsByName("selectedCheckListId");
	if(obj.checked){
		for(var i=0;i<checkList.length;i++){
			document.getElementsByName("selectedCheckListId")[i].checked=true;
			showRemarksTextBox(obj);
		}	
	}
	else{
		for(var i=0;i<checkList.length;i++){
			document.getElementsByName("selectedCheckListId")[i].checked=false;
			showRemarksTextBox(obj);
		}	
	
	}
}

function showRemarksTextBox(enclosureId)
 {
     var length=document.getElementsByName('selectedCheckListId').length;
    for(var i=0;i<length;i++)
    {
    if(document.getElementsByName('selectedCheckListId')[i].checked)
     {
       document.getElementsByName('remarks')[i].disabled=false;
     
     }else{
       document.getElementsByName('remarks')[i].disabled=true;
       document.getElementsByName('remarks')[i].value="";
     }
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
 	 		if(document.getElementsByName('pages')[j].value=="")
 			{
 	 			alert("Enter No of pages for "+ document.getElementsByName('enclosureNameArray')[j].value);
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
		document.getElementsByName("hmode")[0].value="ADDCHECKLIST";
		document.forms[0].submit();
	}
	else
	{
		return false;
	}	
} 

function clearForm(){
	
    var checklist=document.getElementsByName('selectedCheckListId');
  
     for(var i=0;i<checklist.length;i++)
     {
      	checklist[i].checked=false;
     	document.getElementsByName('remarks')[i].value="";
     	document.getElementsByName('remarks')[i].disabled=true;
     }
     
     document.getElementsByName("selectedAllCheckListId")[0].checked=false;
 
}

window.onload = function ()
{
}
 
</script>
<body>
	<html:form action="/caseSheetDispatch">
		<his:TransactionContainer>
			  <his:SubTitleTag name="CheckList Details">
			   </his:SubTitleTag>
			   <logic:present name="<%=MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY %>">
			   <his:ContentTag>
                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                
                <tr>
					<td width="10%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<input type="checkbox" name="selectedAllCheckListId" value="0" onclick="checkAllChecklist(this)" tabindex="1"/>
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
						<html:checkbox name="CaseSheetDispatchFB" property="selectedCheckListId" value="<%=checklistDtlVOs.getChecklistId()%>" onclick="showRemarksTextBox(this)" tabindex="1"></html:checkbox>
						
						</div>
					</td>
					<%String style=""; %>
	               		<%style="color:#000000" ;%>
	                <%--
	                <logic:equal name="checklistDtlVOs" property="isCompulsory" value="0">
	               		<%style="color:#000000" ;%>
	                </logic:equal>
	                --%>	
	                <td width="40%" class="tdfont" style="<%=style%>" >
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
        
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style="cursor:pointer" tabindex="1" onclick ="saveEnclosureDetail()" onkeypress="if(event.keyCode==13) saveEnclosureDetail()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" self.close()" onkeypress="if(event.keyCode==13)self.close()">
		</his:ButtonToolBarTag>
</his:TransactionContainer>

	<html:hidden name="CaseSheetDispatchFB" property="hmode"/>
	<html:hidden name="CaseSheetDispatchFB" property="caseSheetId"/>
	<html:hidden name="CaseSheetDispatchFB" property="caseSheetType"/>
	<html:hidden name="CaseSheetDispatchFB" property="patAdmNo"/>
	<html:hidden name="CaseSheetDispatchFB" property="index"/>
	<html:hidden name="CaseSheetDispatchFB" property="checkedChecklist"/>
</html:form>
<his:status/>
</body>
</html>