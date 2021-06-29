<%@page import="enquiry.enquiryConfig"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<script>
function submitPage(hmode){

	document.getElementsByName('hmode')[0].value=hmode
	document.forms[0].submit();
}
function getFacilityDetail(index,len){

	//document.getElementsByName('facilityId')[0].value=facilityId
	//document.getElementsByName('selectedFacilityId')[0].value=selectedFacilityId
	//submitPage('GETFACILITYDETAIL');
	//alert(index)
	//alert(len)
	for(var i=0;i<len;i++){
		if(index!=i){
			document.getElementById(i).style.display="none";
		}
	}
	document.getElementById(index).style.display="block";
	
}
function createDiv(){
	var divElem=document.createElement('div')
	divElem.id="div1"
	document.body.appendChild(divElem);	
	divElem.innerHTML="<div id='progressNoteDiv' style='display:none;position:absolute; " +
					"width:250px; height:22px; z-index:2; left:340px; top:180px;' ><table width='100%' ><tr>" +
					"<td id='progressNoteTdId' width='100%' style='background-color:#FFEBD5;border-top: outset black " + 
					"2px;border-bottom: inset black 2px;border-left: outset black 2px;border-right: inset black 2px;'>"+
					"</td></tr></table></div>";
				
}

function showPopupDiv(mode,elem,divId,index){
	if(document.getElementById("progressNoteDiv")==null){
		createDiv()
	}
	
	if(mode=='show'){
		document.getElementById("progressNoteDiv").style.display=""
		value=document.getElementsByName(elem)[index].value
		value="<div align='left'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+ value + "</b></font></div>"
		document.getElementById("progressNoteTdId").innerHTML=value
		
		var left=0
		var top=0
		var td=document.getElementById(divId)
		while(td!=null){
			left+=td.offsetCenter
			top+=td.offsetTop
			td=td.offsetParent
		}
		
		document.getElementById("progressNoteDiv").style.top= (top+15)
		document.getElementById("progressNoteDiv").style.left= left
	}
	else{
		document.getElementById("progressNoteDiv").style.display="none"
	}	
}

</script>

<body>
<his:TransactionContainer>
<logic:equal name="hospitalFacilityEnquiryFB" property="isDirectCall" value="DIRECT">
<form action="<his:path src='/enquiry/hospitalFacilityEnquiry.cnt' />" method="post">

<his:TitleTag name="Hospital Facility Enquiry">
</his:TitleTag>
</logic:equal>
		<his:SubTitleTag name="">
		<div align="center">Emergency Telephone Numbers</div>
		</his:SubTitleTag>	
	<his:statusList>
	<his:ContentTag>
	<bean:define id="facilityVoId" name="<%=enquiryConfig.HOSPITAL_FACILITY_MST_VO_LIST %>" type="java.util.List"> </bean:define>
	<%int len=facilityVoId.size(); %>	
	<table width="100%" cellspacing="1" cellpadding="0">
	<logic:iterate id="hospitalFacilityEnquiryrVOList" name="<%=enquiryConfig.HOSPITAL_FACILITY_MST_VO_LIST %>" type="enquiry.vo.HospitalFacilityMasterVO"  indexId="index">
		<tr >
			<%String desc=hospitalFacilityEnquiryrVOList.getFacilityDesc(); 
				String descArray[]=desc.split("#");
				String facilityDesc="<table>";%>
				<%int descLength=descArray.length;%>
				<%for(int i=0;i<descLength;i++){%>	
					 <%if(i == 0){ %>
					 	<%facilityDesc=facilityDesc+"<tr><td><b>"+descArray[i]+"</b></td></tr>"; %>
					 <%} else{%>
					 	 <%facilityDesc=facilityDesc+"<tr><td><b>"+descArray[i]+"</b></td></tr>"; %>
					 <%} %>
				<%} %>
				<%facilityDesc=facilityDesc+"</table>";%>
			 
			 <td class="tdfont" width="45%" id="tableDataId<%=index %>" align="center">
				<div align="center">
				<input type="hidden" name="hiddenData" value='<%=facilityDesc %>'>
				<a onmouseover="showPopupDiv('show', 'hiddenData', 'tableDataId<%=index %>','<%=index %>')" tabindex="1" 
						 onmouseout="showPopupDiv('hide','','','')" style="cursor: default;" 
				 onfocus="showPopupDiv('show',' hiddenData', 'tableDataId<%=index %>','<%=index %>')" 
					 	 onblur="showPopupDiv('hide','','','')">
					<b><bean:write name="hospitalFacilityEnquiryrVOList" property="facilityName"/></b>
				 </a>
				</div> 
			</td>
		 </tr>
		</logic:iterate>
	</table>
	</his:ContentTag>
	</his:statusList>
	
	<html:hidden name="hospitalFacilityEnquiryFB" property="hmode"/>
	<html:hidden name="hospitalFacilityEnquiryFB" property="facilityId"/>
	<html:hidden name="hospitalFacilityEnquiryFB" property="selectedFacilityId"/>
	<html:hidden name="hospitalFacilityEnquiryFB" property="desc"/>
	<html:hidden name="hospitalFacilityEnquiryFB" property="isDirectCall"/>
	
<logic:equal name="hospitalFacilityEnquiryFB" property="isDirectCall" value="DIRECT">
</form>
</logic:equal>
<logic:notEqual name="hospitalFacilityEnquiryFB" property="isDirectCall" value="DESK">
<his:ButtonToolBarTag>
	
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick="submitPage('CANCEL');"/>
	
<!--	<logic:notEqual name="hospitalFacilityEnquiryFB" property="isDirectCall" value="DIRECT">-->
<!--	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitDesk('CANCEL');" tabindex="1" onclick="submitDesk('CANCEL');"/>-->
<!--	</logic:notEqual>-->
</his:ButtonToolBarTag>
</logic:notEqual>
<his:status/>
</his:TransactionContainer>
</body>
</html>