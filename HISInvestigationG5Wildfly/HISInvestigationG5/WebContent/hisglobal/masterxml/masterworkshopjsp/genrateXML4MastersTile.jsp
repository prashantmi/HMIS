
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<html>

<body>

<html:form action="generateXML.cnt">
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td Class="header" colspan="4" width="68%">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Master Details
				</font>
			</td>
			<% System.out.println("printing header"); %>
		</tr>

		<tr>
			<td width="17%" nowrap Class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Enter Master Name <font color="#FF0000">*</font>
					</font>
				</div>
			</td>
			
			<td width="17%" Class="tdfont">
				<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
					<html:text name="XmlGeneratorFB" property="masterName" maxlength="3" size="3" tabindex="1" />
				</font>
			</td>
		</tr>

		<tr>
			<td Class="header" colspan="4" width="68%">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Common Values
				</font>
			</td>
		</tr>
		
		<tr>
			<td width="17%" nowrap class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Enter Master File Name <font color="#FF0000">*</font>
					</font>
				</div>
			</td>
			
			<td width="17%" Class="tdfont">
				<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
					<html:text name="XmlGeneratorFB" property="masterNameAsFileName" maxlength="3" size="3" tabindex="1" /> 
				</font>
			</td>
		</tr>

		<tr>
			<td Class="header" colspan="4" width="68%">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
					List Page Details
				</font>
			</td>
		</tr>
		
		<tr>
			<td width="17%" nowrap Class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Table Heading(Title) <font color="#FF0000">*</font>
					</font>
				</div>
			</td>
			
			<td width="17%" Class="tdfont">
				<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
					<html:text name="XmlGeneratorFB" property="tableHeading" maxlength="19" size="10" tabindex="1" />
				</font>
			</td>
		</tr>
		
		<tr>
			<td width="17%" nowrap Class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						List Table <font color="#FF0000">*</font>
					</font>
				</div>
			</td>
			
			<td width="17%" Class="tdfont">
				<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
					<html:text name="XmlGeneratorFB" property="listTable" maxlength="19" size="10" tabindex="1" />
				</font>
			</td>
		</tr>

		<tr>
			<td width="17%" nowrap Class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Control Details(Only combo control is allowed on list page) <font color="#FF0000">*</font>
					</font>
				</div>
			</td>
		<tr>
		
		<tr>
			<td width="17%" nowrap Class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Control Type <font color="#FF0000">*</font>
					</font>
				</div>
			</td>
			
			<td width="17%" Class="tdfont">
				<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
					<html:text name="XmlGeneratorFB" property="lstControlType" maxlength="10" size="10" tabindex="1" />
				</font>
			</td>
			
			<td width="17%" nowrap Class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Control Index <font color="#FF0000">*</font>
					</font>
				</div>
			</td>
			
			<td width="17%" Class="tdfont">
				<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
					<html:text name="XmlGeneratorFB" property="lstControlIndex" maxlength="19" size="10" tabindex="1" />
				</font>
			</td>
		</tr>

		<tr>
			<td width="17%" nowrap Class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Data <font color="#FF0000">*</font>
					</font>
				</div>
			</td>
		</tr>

		<tr>
			<td width="17%" Class="tdfont">
				<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
					Dynamic <html:radio name="XmlGeneratorFB" property="lstCtrlIsDynamic" value="true" tabindex="1" />
				</font>
			</td>
			<td width="17%" nowrap Class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Static <font color="#FF0000">*</font>
					</font>
				</div>
			</td>
		</tr>
		<tr>
			<td width="17%" nowrap Class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Option text <font color="#FF0000">*</font>
					</font>
				</div>
			</td>

			<td width="17%" Class="tdfont">
				<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">
					<html:text name="XmlGeneratorFB" property="lstControlIndex" maxlength="19" size="10" tabindex="1" />
				</font>
			</td>

			<td width="17%" nowrap="nowrap" class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Option Value <font color="#FF0000">*</font>
					</font>
				</div>
			</td>
		</tr>
	</table>
</html:form>

</body>

</html>
