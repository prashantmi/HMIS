<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<%@page import="inpatient.InpatientConfig"%>

<%@page import="mrd.MrdConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js" />
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
var selectedIndex="";
function closeForm()
{
	self.close();
}
/* function showProgNotes(obj1,event)
{
	
	 var path="/HISClinical/mrd/emrDesk.cnt?hmode=PROGRESSNOTESPOPUP&progressNotesIndex="+obj1;
	 //alert("path "+path)
	 openPopup(createFHashAjaxQuery(path),event,250,800);
	 
} */

function viewProgNotes(obj1)
{
//alert("obj1="+obj1)
//alert("obj2="+obj2)
//alert("obj3="+obj3)
// alert("obj1 "+obj1)
	if(selectedIndex!="")
	{
	var hideId="progressNoteId"+selectedIndex;
	document.getElementById(hideId).style.display="none";
	}
	var showId="progressNoteId"+obj1;
	document.getElementById(showId).style.display="block";
	selectedIndex=obj1;
}

function createDiv(){
	var divElem=document.createElement('div')
	divElem.id="div1"
	document.body.appendChild(divElem);	
	<%--
	divElem.innerHTML="<div id='progressNoteDiv' style='display:none;position:absolute; " +
					"width:250px; height:22px; left:340px; top:180px;'>"+
					"<table width='100%'style='background-color:#E0EBEB;border-top: outset black " + 
					"2px;border-bottom: inset black 2px;border-left: outset black 2px;" +
					"border-right: inset black 2px;'><tr><td style='background-color:#E0EBEB'><div align='right'><a title='close' onclick=\"showPopupDiv('hide','','')\">X</a></div></td>"+
					"</tr><tr><td width='100%' id='progressNoteTdId' " +
					" style='background-color:#E0EBEB;'></td></tr>"+
					"<tr><td><input type='text' name='ssfsf'></td></tr>"+
					"<tr><td align='center'><input type='button' value='OK'></td></tr></table></div>"
	--%>
	divElem.innerHTML="<div id='progressNoteDiv' style='display:none;position:absolute; " +
					"width:250px; height:22px; z-index:2; left:340px; top:180px;'><table width='100%' ><tr>" +
					"<td id='progressNoteTdId' width='100%' style='background-color:#E0EBEB;border-top: outset black " + 
					"2px;border-bottom: inset black 2px;border-left: outset black 2px;border-right: inset black 2px;'>"+
					"</td></tr></table></div>";
				
	//document.body.appendChild(divElem);			
}

function showPopupDiv(mode,elem,divId,index){
	if(document.getElementById("progressNoteDiv")==null){
		createDiv()
	}
	
	if(mode=='show'){
		//value="dfjdskl flkgjflk lkghsjdfl hglfkdjshg lkdfjlkhg fdlkjhl fdklhjfd lhkjdflkjh lkfdj hlkfdlkjh lkdfjh lkfdjlkh fdlkj hlkdfjhlk dfjlkh jdflkjh ldfkjhlkfdjhlk dhlkjdflkhj"
		document.getElementById("progressNoteDiv").style.display=""
		value=document.getElementsByName(elem)[index].value
		value="<div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+ value + "</font></div>"
		document.getElementById("progressNoteTdId").innerHTML=value
		
		var left=0
		var top=0
		//alert(divId)
		var td=document.getElementById(divId)
		while(td!=null){
			left+=td.offsetLeft
			top+=td.offsetTop
			td=td.offsetParent
		}
		//alert("left "+left)
		//alert("top "+top)
		
		document.getElementById("progressNoteDiv").style.top= (top+15)
		document.getElementById("progressNoteDiv").style.left= left
	}
	else{
		document.getElementById("progressNoteDiv").style.display="none"
	}	
}
</script>
<body>
<html:form action="/emrDesk">
	<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>

	<logic:present name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" >
	<logic:notEmpty name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" >
		<his:TitleTag name="Patient Progress Notes">
		</his:TitleTag>
		<his:ContentTag>
			<table style="width:100%; cellpadding:0; cellspacing:1">
				<tr>
					<td width="5%" class="tdfonthead"  align="center" style="border-top: outset black 2px;border-bottom: inset black 2px;" >
						<!-- <div align="center" > -->
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="SNo"/>
							</font>
						<!-- </div> -->
					</td>
					<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;" >
						<div align="center" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="captureArea"/>
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;" >
						<div align="center" >
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="capturedBy"/>
							</font>
						</div>
					</td>
					<td width="19%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="roundTime"/>
							</font>
						</div>
					</td>	
					<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="progNote"/>
							</font>
						</div>
					</td>	
					<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visitNotes"/>
							</font>
						</div>
					</td>	
					<td width="15%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="instructions"/>
							</font>
						</div>
					</td>	
									
					<%-- <td width="19%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="entryDate"/><bean:message key="timeFormat"/>
							</font>
						</div>
					</td> --%>
					
				</tr>
			
				 <%String indexEpisodeOpen="-1"; %>
			<logic:iterate indexId="i" id="docRoundVO" name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" type="hisglobal.vo.DoctorRoundDtlVO"  >
			
				<tr>
					<td class="tdfont" width="5%" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=(i.intValue()+1)%>
							</font>
						</div>
					</td>
					<td class="tdfont" width="15%" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="docRoundVO" property="captureArea" /> 
							</font>
						</div>
					</td>
					<td class="tdfont" width="15%" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="docRoundVO" property="doctorName" /> 
							</font>
						</div>
					</td>
					<td class="tdfont" width="19%" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="docRoundVO" property="roundTime" /> 
							</font>
						</div>
					</td>
					<td class="tdfont" width="15%" id="progressNoteTdID<%=i%>">
						<div align="center">
						<%--<a onclick="showProgNotes('<%=i.intValue() %>',event,'<%=docRoundVO.getVisitNote()%>','progressNotTd<%=i%>')" tabindex="1" onkeypress="viewProgNotes('<%=(docRoundVO.getProgressNote()==null)?"No Progress Notes":docRoundVO.getProgressNote() %>')" style="cursor: pointer;" >
						 --%> 
						 <input type="hidden" name="progressNote" value="<%=docRoundVO.getProgressNote()%>">
						 <a onmouseover="showPopupDiv('show','progressNote','progressNoteTdID<%=i%>',<%=i%>)" tabindex="1" 
						 	 onmouseout="showPopupDiv('hide','','','')" style="cursor: default;" 
						 	 onfocus="showPopupDiv('show','progressNote','progressNoteTdID<%=i%>',<%=i%>)" 
						 	 onblur="showPopupDiv('hide','','','')">
						<font color="#0066FF" > <b><bean:message key="view" /></b></font></a>
						</div>
						
					</td>	
					<td class="tdfont" width="15%" id="visitNoteTdID<%=i%>">
						<div align="center">
						<%--<a onclick="showProgNotes('<%=i.intValue() %>',event,'<%=docRoundVO.getVisitNote()%>','progressNotTd<%=i%>')" tabindex="1" onkeypress="viewProgNotes('<%=(docRoundVO.getProgressNote()==null)?"No Progress Notes":docRoundVO.getProgressNote() %>')" style="cursor: pointer;" >
						 --%> 
						 <input type="hidden" name="visitNote" value="<%=docRoundVO.getVisitNote()%>">
						 <a onmouseover="showPopupDiv('show','visitNote','visitNoteTdID<%=i%>',<%=i%>)" tabindex="1" 
						 	 onmouseout="showPopupDiv('hide','','','')" style="cursor: default;" 
						 	 onfocus="showPopupDiv('show','visitNote','visitNoteTdID<%=i%>',<%=i%>)" 
						 	 onblur="showPopupDiv('hide','','','')">
						<font color="#0066FF" > <b><bean:message key="view" /></b></font></a>
						</div>
						
					</td>
					<td class="tdfont" width="15%" id="instructionTdID<%=i%>">
						<div align="center">
						<%--<a onclick="showProgNotes('<%=i.intValue() %>',event,'<%=docRoundVO.getVisitNote()%>','progressNotTd<%=i%>')" tabindex="1" onkeypress="viewProgNotes('<%=(docRoundVO.getProgressNote()==null)?"No Progress Notes":docRoundVO.getProgressNote() %>')" style="cursor: pointer;" >
						 --%> 
						 <input type="hidden" name="instruction" value="<%=docRoundVO.getInstruction()%>">
						 <a onmouseover="showPopupDiv('show','instruction','instructionTdID<%=i%>',<%=i%>)" tabindex="1" 
						 	 onmouseout="showPopupDiv('hide','','','')" style="cursor: default;" 
						 	 onfocus="showPopupDiv('show','instruction','instructionTdID<%=i%>',<%=i%>)" 
						 	 onblur="showPopupDiv('hide','','','')">
						<font color="#0066FF" > <b><bean:message key="view" /></b></font></a>
						</div>
						
					</td>	
					
					<%-- <td class="tdfont" width="19%" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="docRoundVO" property="entryDate" /> 
							</font>
						</div>
					</td> --%>
						
				</tr>
			<%indexEpisodeOpen=i.toString(); %>
			</logic:iterate>
				<%if(indexEpisodeOpen.equals("-1")){%>
			<his:ContentTag>
				<tr>
					
						<td class="tdfont" width="25%" colspan="5" nowrap>
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>	<bean:message key="noInvestigationDetailsFound" /></b></font></div></td>
					
				</tr>
			</his:ContentTag>	
			<%} %>
			</table>	
				</his:ContentTag>
			</logic:notEmpty>	
			</logic:present>
			
			<logic:present name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" >
			<logic:notEmpty name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" >
			<logic:iterate indexId="i" id="docRoundVO" name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY %>" type="hisglobal.vo.DoctorRoundDtlVO"> 
       			<div id='<%="progressNoteId"+i.intValue() %>' style="display: none;">
       			    <his:ContentTag>
						<table width="100%" cellpadding="0" cellspacing="1">
							<tr>
								<td width="100%">
									<div align="center">
										<html:textarea name="docRoundVO" property="progressNote" rows="6" cols="135" readonly="true"></html:textarea>
									</div>
								</td>
							</tr>
						</table>
					</his:ContentTag>		
				</div>
       		</logic:iterate>
       		
			</logic:notEmpty>
			</logic:present>
			
			<logic:notPresent name="<%=MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY%>" >
    	<his:ContentTag>
    	
				<tr>
					
									<td class="tdfont" width="100%"  nowrap>
									<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	<bean:message key="noProgressNotesFound" /></b></font></div></td>
					
				</tr>
		
			</his:ContentTag>
    </logic:notPresent>
		
		
<his:status/>
</html:form>
</body>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 