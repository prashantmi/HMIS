<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/utility/dynamicdesk/js/desk.js" />

<html>
	<body>
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="100%" class="tdfonthead">
						<div align="center">
							<font color="red" size="4" face="Verdana, Arial, Helvetica, sans-serif">	
								<label>
									This Page is Under Construction...
								</label>
								<html:button value="BACK"  property="mybutton" onclick="submitDesk('NEW')"/>
							</font>	 
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
	</body>
</html>