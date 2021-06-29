<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%@page import="inpatient.InpatientConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function closeForm()
{
	self.close();
}

</script>
<%@page import="hisglobal.vo.DrugAdminDtlVO"%>
<html>
<html:form action="/nurBloodTransfusion">
<body>
	<his:TitleTag name="Drug Given Detail">
	</his:TitleTag>
	<his:ContentTag>
	<%DrugAdminDtlVO vo=(DrugAdminDtlVO)session.getAttribute(InpatientConfig.SELECTED_DRUG_ADMIN_DETAIL_VO); %>
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="drugname" />
									</b>
								</font>
							</div>
						</td>
			<td width="50%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=vo.getItemName() %>
								</font>
							</div>
						</td>			
			
		</tr>
		<tr>
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Here we will show total information of particular drug.
				</font>
			</div>		
		</tr>
		
	</table>
	</his:ContentTag>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
</body>
</html:form>
</html>