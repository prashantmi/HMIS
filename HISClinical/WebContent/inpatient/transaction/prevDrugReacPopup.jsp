<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="java.util.List"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<script type="text/javascript">

function closeForm()
{
	self.close();
}

function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
	elmt.value=mode;
	document.forms[0].submit();
	self.close();
}
function viewReactionSummary(obj)
{
	if(document.getElementsByName("hiddenReactionSummary")[obj].value!="")
	{
		document.getElementsByName("showReactionSummary")[0].value=document.getElementsByName("hiddenReactionSummary")[obj].value;
	}
	else
	{
		document.getElementsByName("showReactionSummary")[0].value="No Reaction Summary";
	}
	
	document.getElementById("divViewReactionSummary").style.display="block"; 
	document.getElementById("divViewControllSummary").style.display="none";
	
}

function viewControlSummary(obj)
{
	if(document.getElementsByName("hiddenControlSummary")[obj].value!="")
	{
		document.getElementsByName("showControlSummary")[0].value=document.getElementsByName("hiddenControlSummary")[obj].value;
	}
	else
	{
		document.getElementsByName("showControlSummary")[0].value="No Control Summary";
	}
	document.getElementById("divViewControllSummary").style.display="block"; 
	document.getElementById("divViewReactionSummary").style.display="none";
	
}


function showPrevDrugSchedule(index,hmode)
{	
	//alert("index "+index);
	//alert("hmode "+hmode);
	document.getElementsByName("scheduleIndex")[0].value=index;
	
	
	elmt=document.getElementsByName("hmode")[0];  
	elmt.value=hmode;
	document.forms[0].submit();
	
//	alert("afterrrrrrrrrrr");
	
	
	/*
	var path='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=PREVDRUGSHEDULE&scheduleIndex='+index;
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
	child.opener = self;
	return child
	*/
}

</script>
<html:form action="/nurDrugReaction">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body >
		<his:TitleTag name="Previous Drug Reaction">
		</his:TitleTag>
		
		<logic:present name="<%=InpatientConfig.DRUG_REACTION_DTL_LIST_FOR_PARTICULAR_DRUG%>">
			<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td width="16%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
		  			<div align="center">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<bean:message key="drugname"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		
	      		<td width="16%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
		  			<div align="center">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<bean:message key="drugBrandName"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		
	      		<td width="18%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="reactionStartTime" />
								</b>
							</font>
						</div>
					</td>
					<td width="18%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="reactionEndTime" />
								</b>
							</font>
						</div>
					</td>
	      		<td width="16%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="reactionSummary" />
							</b>
						</font>
					</div>
				</td>
				<td width="16%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="controlSummary" />
							</b>
						</font>
					</div>
				</td>
				
			</tr>
			<logic:iterate id="drugReactionDtlVO" indexId="idx" name="<%=InpatientConfig.DRUG_REACTION_DTL_LIST_FOR_PARTICULAR_DRUG%>" type="hisglobal.vo.DrugReactionVO">
				<%String index=idx.toString(); %>
				<tr>
					<td width="16%" class="tdfont">
						<div align="center">
							<b>
								<bean:write name="drugReactionDtlVO" property="itemName"/>
						 	</b>
						</div>
					</td>
					
					<td width="16%" class="tdfont">
						<div align="center">
							<b>
								<bean:write name="drugReactionDtlVO" property="drugBrandName"/>
						 	</b>
						</div>
					</td>
				<td width="18%" class="tdfont">
					<div align="center">
						<b>
							<bean:write name="drugReactionDtlVO" property="reactionStartTime"/>
						</b>
					</div>
				</td>
				<td width="18%" class="tdfont">
					<div align="center">
						<b>
							<bean:write name="drugReactionDtlVO" property="reactionEndTime"/>
						</b>
					</div>
				</td>	
				<td class="tdfont" width="16%" >
						<div id="divProgNotesId" style="display: none;">
							<html:textarea name="DrugReactionFB" property="hiddenReactionSummary" rows="5" cols="130"  value="<%=drugReactionDtlVO.getReactionSummary()%>"></html:textarea>
						</div>
						
						<div align="center">
								<a style="cursor:pointer" onclick="viewReactionSummary('<%=index%>')" >
								VIEW
								</a>
							</div>
						</td>
						<div id="divProgNotesId" style="display: none;">
							<html:textarea name="DrugReactionFB" property="hiddenControlSummary" rows="5" cols="130"  value="<%=drugReactionDtlVO.getControlSummary()%>"></html:textarea>
						</div>
					<td class="tdfont" width="16%" >
							<div align="center">
								<a style="cursor:pointer" onclick="viewControlSummary('<%=index %>')" >
								VIEW
								</a>
							</div>
						</td>
				
				
			</tr>		
			</logic:iterate>
		</table>
		</his:ContentTag>
		</logic:present>
		
		
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
        <html:hidden name="DrugReactionFB" property="hmode"/>
      
	</body>
	<div id="divViewControllSummary" style="display: none;">
       			<his:SubTitleTag name="How It Was Controlled">
       			</his:SubTitleTag>
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="DrugReactionFB" property="showControlSummary" rows="5" cols="130" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>
	<div id="divViewReactionSummary" style="display: none;">
       			<his:SubTitleTag name="Reaction Summary">
       			</his:SubTitleTag>
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="DrugReactionFB" property="showReactionSummary" rows="5" cols="130" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>		
</html:form>		