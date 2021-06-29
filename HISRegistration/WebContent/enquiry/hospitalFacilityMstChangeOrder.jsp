<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="enquiry.enquiryConfig"%>

<html>
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />
<script>
function submitPage(hmode){
	var selectedId=document.getElementsByName('selectedFaclityId')[0];
	//alert(selectedId)
	var facilityId="";
	for(var i=0;i<selectedId.length;i++){
		//alert(selectedId.options[i].value)
		facilityId=facilityId + selectedId.options[i].value + "%";
		//alert(facilityId)
	}
	document.getElementsByName('facilityId')[0].value=facilityId
	document.getElementsByName('hmode')[0].value=hmode
	MoveToSelected();
	document.forms[0].submit();	
} 
function MoveToSelected()
{
	if(document.forms[0].selectedFaclityId){
		for(var i=0;i<document.forms[0].selectedFaclityId.length;i++){
			document.forms[0].selectedFaclityId.options[i].selected=true;
		}
	}		
}			
</script>
<body>

  <html:form action="/hospitalFacilityMaster" > 
    <html:hidden name="hospitalFacilityMasterFB" property="hmode"/>
	<html:hidden name="hospitalFacilityMasterFB" property="slNo"/>
	<html:hidden name="hospitalFacilityMasterFB" property="facilityId"/>
	<html:hidden name="hospitalFacilityMasterFB" property="desc"/>
	   
      <his:TitleTag name="Hospital Facility Master >>Change Display Order">	</his:TitleTag>
  	   
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		    <tr>
	      		<td  class="tdfonthead">
					<div align="center">
						<b><bean:message key="facilityName"/></b>
					</div>
				</td>
			</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		    <tr>
		  		<td width="50%" class="tdfont">
	      			<div align="right">
						<html:select name="hospitalFacilityMasterFB" property="selectedFaclityId" multiple="true" size="10">
							<html:options  collection="<%=enquiryConfig.HOSPITAL_FACILITY_MST_VO_LIST%>" property="value" labelProperty="label" />
						</html:select>
					</div>
				</td>
	      		<td width="1%" class="tdfont">
	      			<div align="center">
					
					</div>
				</td>
	      		<td width="43%" class="tdfont">
	      			<div align="left">
						<img src="/AHIMS/hisglobal/images/arr-up.png" class="link" onClick='moveUP(document.forms[0].selectedFaclityId);'/>
						<br>
						<img src="/AHIMS/hisglobal/images/arr-dwn.png" class="link" onClick='moveDOWN(document.forms[0].selectedFaclityId);'/>
					</div>
				</td>
		      	
			</tr>
		</table>
		</his:ContentTag>
          <his:ButtonToolBarTag>
			<span id="saveDiv">
			     <img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('SAVECHANGEDORDER')" onclick="submitPage('SAVECHANGEDORDER')">
			     <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">
			</span>
		</his:ButtonToolBarTag>
		 <his:status/>
      
   </html:form>
  </body>
</html>
		     
		   
		  