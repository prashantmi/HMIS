<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.transaction.controller.fb.CaseSheetHandoverFB"%>
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
	//alert(index);
	if(document.getElementsByName("hmode")[0].value == "ADDENCLOSURES")
	{
		var index=document.getElementsByName("index")[0].value;
		//alert("isEnclosureSelected"+index);
		//opener.document.getElementsByName("isEnclosureSelected")[index].value="1";
		opener.document.getElementById("isEnclosureSelected"+index).value="1";
		//alert("selectEnclosure"+index);
		//opener.document.getElementsByName("selectEnclosure")[index].value="Modify Enclosure";
		opener.document.getElementById("selectEnclosure"+index).value="Modify Enclosure";
		self.close();
	}
	
	var checkedItem=document.getElementsByName("checkedItem")[0].value.split("#")
	
	var checkedChecklist=document.getElementsByName("checkedChecklist")[0].value.split("#")
	var selectedCheckListId=document.getElementsByName("selectedCheckListId")
	for(var i=0;i<selectedCheckListId.length;i++){
		for(var j=0;j<checkedChecklist.length;j++){
			if(checkedChecklist[j]==selectedCheckListId[i].value){
				selectedCheckListId[i].checked=true;
	
			}
		}	
	}
	showRemarksTextBox();
	
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
		
		
	    var compulCheckArr=document.getElementsByName('selectedEnclosureId');
	  
		for(var m=0;m<compulCheckArr.length;m++)
			{
		
			if( document.getElementsByName('pages')[m].value=="")
				document.getElementsByName('pages')[m].value=document.getElementsByName('prePages')[m].value;
		 	} 
			
	
      
} 

function validateEnclosure()
 {
     var valid=true;
     var verfiedByPeon=document.getElementsByName('pages');
     for(var j=0;j<verfiedByPeon.length;j++)
     {
        if(!isEmpty(verfiedByPeon[j],"Verify By peon for" + 
        	document.getElementsByName("enclosureNameArray")[j].value))
        {
			return false;
		}
		if(!validatePages(j)){
			return false;
		}	
 	}
 	return true;
}
 
function saveEnclosureDetail()
{
	if(validateEnclosure()&& validateChecklist())
	{
		document.getElementsByName("hmode")[0].value="ADDENCLOSURES";
		document.forms[0].submit();
	}
	else
	{
		return false;
	}	
}
 
function clearForm()
{
	var enclosure=document.getElementsByName('selectedEnclosureId');
    var checklist=document.getElementsByName('selectedCheckListId');
    
     for(var j=0;j<enclosure.length;j++)
     {
     	//enclosure[j].checked=false
     	document.getElementsByName('pages')[j].value="";
     }
     
     for(var i=0;i<checklist.length;i++)
     {
      	checklist[i].checked=false;
     	document.getElementsByName('remarks')[i].value="";
     	document.getElementsByName('remarks')[i].disabled=true;
     }
     
     document.getElementsByName("selectedAllCheckListId")[0].checked=false;
}

function validatePages(i){
	var pages=document.getElementsByName('pages');
	var prePages=document.getElementsByName('prePages');
	if(pages[i].value > prePages[i].value){
		alert("Verify By peon cannot be greater than Dispatch Pages")
		pages[i].focus()
		pages[i].select()
		return false;
	}
	return true;
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

function validateChecklist(){
	 var valid=true;
	  var length=document.getElementsByName('selectedCheckListId').length;
	 for(var i=0;i<length;i++)
     {
	 	 if(document.getElementsByName('isCompulsoryArray')[i].value==<%=MrdConfig.IS_COMPULSORY_YES%>)
	 	 {
		 	 if(!document.getElementsByName('selectedCheckListId')[i].checked)
		 	 {
		 	 	alert("Select Compulsory Checklists:: "+document.getElementsByName('checkListNameArray')[i].value);
		 	 	valid=false;
		 	 	return valid;
		 	 	break;
		 	 	
		 	 }  
	 	 }
 	 }
 	 return valid

}
 
 
</script>
<body>

	<html:form action="/caseSheetHandover">
		<his:TransactionContainer>
		<logic:notEqual name="caseSheetHandoverFB" property="hmode" value="ADDENCLOSURES">
			<his:statusDone>
	             <his:SubTitleTag name="Enclosure Summary">
			   </his:SubTitleTag>
				<logic:present name="<%=MrdConfig.RECORD_ENCLOSURE_SUMMARY_VO_ARRAY %>">			  
			   <his:ContentTag>
                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                
                <tr>
                	<td width="50%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="enclosure"/>
						
					</b>	
					</font>
					</div>
					</td>
					<td width="25%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="dispatchPages"/>
					</b>	
					</font>
					</div>
					</td>
					<td width="25%" class="tdfonthead">
					<div align="center">
					<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<%-- <bean:message key="verifyByPeon"/> --%>Verified by concern person
					</b>	
					</font>
					</div>
					</td>
					
                </tr>
                             
                 <logic:iterate id="enclosureDtlVOs" indexId="idx"  name="<%=MrdConfig.RECORD_ENCLOSURE_SUMMARY_VO_ARRAY %>" type="hisglobal.vo.RecordTypeWiseEnclosureMstVO">
             			   
                <tr>
                	<%String style="color:#000000" ;%>
                
                <td width="50%" class="tdfont" style="<%=style %>">
					 <div align="center">
				         <bean:write name="enclosureDtlVOs" property="enclosure"/>
				         <html:hidden name="caseSheetHandoverFB" property="selectedEnclosureId" value="<%=enclosureDtlVOs.getEnclosureId()%>"/>
				        <html:hidden name="caseSheetHandoverFB" property="enclosureNameArray" value="<%=enclosureDtlVOs.getEnclosure()%>"/>
				     </div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
					<bean:write name="enclosureDtlVOs" property="pages"/>
					<input type="hidden" name="prePages" value="<%=enclosureDtlVOs.getPages()%>"/>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
					<%CaseSheetHandoverFB fb=(CaseSheetHandoverFB)pageContext.findAttribute("caseSheetHandoverFB"); %>
			          &nbsp;<html:text name="caseSheetHandoverFB" property="pages" maxlength="3" size="3"
							value='<%=(fb.getPages()==null?"":fb.getPages()[idx])%>' onkeypress="return validateNumeric(event)" tabindex="1">
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
					<input type="checkbox" name="selectedAllCheckListId" value="0" onclick="checkAllChecklist(this)" />
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
						<html:checkbox name="caseSheetHandoverFB" property="selectedCheckListId" value="<%=checklistDtlVOs.getChecklistId()%>" onclick="showRemarksTextBox(this)" ></html:checkbox>
						
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
						     <html:hidden name="caseSheetHandoverFB" property="isCompulsoryArray" value="<%=checklistDtlVOs.getIsCompulsory() %>"/>
						     <html:hidden name="caseSheetHandoverFB" property="checkListNameArray" value="<%=checklistDtlVOs.getChecklistName() %>"/>
					     </div>
					</td>
					<td width="50%" class="tdfont">
						<div align="center" >
				         <%CaseSheetHandoverFB fb=(CaseSheetHandoverFB)pageContext.findAttribute("caseSheetHandoverFB"); %>
				          &nbsp;<html:text name="caseSheetHandoverFB" property="remarks" disabled="true" maxlength="50" size="45"
							value='<%=(fb.getRemarks()==null?"":fb.getRemarks()[idx]) %>' onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1">
						</html:text>
						</div>
					</td>
			
                </tr>
                </logic:iterate>
                </table>
               </his:ContentTag> 
         	</logic:present>
         </his:statusDone> 
        </logic:notEqual>
        
		<his:ButtonToolBarTag>
			<his:statusDone>
				<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"/>'  style="cursor:pointer" tabindex="1" onclick ="saveEnclosureDetail()" onkeypress="if(event.keyCode==13) saveEnclosureDetail()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" self.close()" onkeypress="if(event.keyCode==13)self.close()">
			</his:statusDone>	
		</his:ButtonToolBarTag>
</his:TransactionContainer>

	<html:hidden name="caseSheetHandoverFB" property="hmode"/>
	<html:hidden name="caseSheetHandoverFB" property="index"/>
	<html:hidden name="caseSheetHandoverFB" property="dispatchId"/>
	<html:hidden name="caseSheetHandoverFB" property="checkedChecklist"/>
	<html:hidden name="caseSheetHandoverFB" property="checkedItem"/>
</html:form>
<his:status/>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();}%>