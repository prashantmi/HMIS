<!-- 
/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ICD Search Engine</title>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>

<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function viewMore()
{
	var div = document.getElementById("divMore");
	var img = document.getElementById("imgMore");
	if(div.style.display=="none")
	{
		div.style.display="block";
		img.src="/HISClinical/hisglobal/images/arrdouble-up.png";
	}
	else
	{
		div.style.display="none";
		img.src="/HISClinical/hisglobal/images/arrdouble-down.png";
	}
}
function viewList()
{
	var div = document.getElementById("divList");
	if(div.style.display=="none")
		div.style.display="block";
	else
		div.style.display="none";
}
function viewDetail()
{
	var div = document.getElementById("divDetail");
	var img = document.getElementById("imgDetail");
	if(div.style.display=="none")
	{
		div.style.display="block";
		img.src="/HIS/hisglobal/images/avai/arr-up.png";
	}
	else
	{
		div.style.display="none";
		img.src="/HIS/hisglobal/images/avai/arr-dwn.png";
	}
}
</script>
<STYLE TYPE="text/css">
	.highlight {
		line-height: 16px;
		font-weight: bold;
		font-family: arial;
		font-size: 11px;
		padding-top: 1px;
		vertical-align: middle;
		padding-left: 6px;
   		background:#E0EBEB;//FFEBD5;
   		border-style:solid;
		border-width:1px;
		
	
   		}
</STYLE>
<STYLE TYPE="text/css">
	.dehighlite {
	font-family: verdana, arial, sans-serif;
    font-size:12px;
    text-decoration: none;
    color: black;
    background-color: #E0EBEB;//FFEBD5;
    white-space: nowrap;
    background:#E0EBEB;
    border-style:solid;
	border-width:1px;
   		}
</STYLE>

</head>

<body>
<his:TitleTag name="ICD Search Engine">
</his:TitleTag>

<his:ContentTag>
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td id="tabDrugAdvice" onclick="activateTab(this)" width="25%" class="highlight">
				<div align="center" >ICD Search Tab</div>
			</td>
			<td id="tabLAAdvice" onclick="activateTab(this)" width="25%" class="dehighlite">
				<div align="center">Golden Rules</div>
			</td>
			<td id="tabDietAdvice" onclick="activateTab(this)" width="25%" class="dehighlite">
				<div align="center">Guidelines etc.</div>
			</td>
		</tr>
	</table>
</his:ContentTag>

<his:ContentTag>
	<table width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="100%" colspan="2" class="tdfonthead" style="vertical-align: middle;">
				<div align="center" style="vertical-align: middle;">
					<input size="30" style="vertical-align: middle;"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' title="Go" style="cursor:pointer;vertical-align: middle;" onclick="viewList();" onkeypress="if(event.keyCode==13) viewList();" >
				</div>
			</td>
		</tr>
		<tr>
			<td width="95%" class="tdfonthead" style="vertical-align: middle;">
				<div align="center" style="vertical-align: middle;">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<input type="radio" style="vertical-align: middle;" checked="checked"> &nbsp;
						ICD Code&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" style="vertical-align: middle;"> &nbsp;
						Disease Name&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" style="vertical-align: middle;"> &nbsp;
						Site&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" style="vertical-align: middle;"> &nbsp;
						Symptom&nbsp;&nbsp;&nbsp;&nbsp;
					</font>
				</div>
			</td>
			<td width="5%" class="tdfonthead" style="vertical-align: middle;">
				<div align="right" style="vertical-align: middle;">
					<img id="imgMore" title="More" class="button" src='<his:path src="/hisglobal/images/arrdouble-down.png"/>' style="cursor:pointer;vertical-align: middle;" onclick="viewMore();" onkeypress="if(event.keyCode==13) viewMore();" >
				</div>
			</td>
		</tr>
	</table>
</his:ContentTag>

<div id="divMore" style="display: none;">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							ICD Code
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<input>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Disease
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<input>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Include/Synonym
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<input>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Site
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<input>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Symptoms
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<input>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Excludes
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<input>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
</div>

<div id="divList" style="display: none;">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="10%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					&nbsp;
				</td>
				<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							ICD Code
						</font>
					</div>
				</td>
				<td width="60%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Disease Name
						</font>
					</div>
				</td>
				<td width="5%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td width="10%" class="tdfont">
					<div align="center">
						<input type="radio">
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<a onclick="viewDetail()">
								A00
							</a>
						</font>
					</div>
				</td>
				<td width="60%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<a onclick="viewDetail()">
								Cholera
							</a>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfont" style="vertical-align: middle;">
					<div align="center" style="vertical-align: middle;">
						<img id="imgDetail" title="Detail" class="button" src='<his:path src="/hisglobal/images/arr-dwn.png"/>' style="cursor:pointer;vertical-align: middle;" onclick="viewDetail();" onkeypress="if(event.keyCode==13) viewDetail();" >
					</div>				
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="divDetail" style="display: none;">
						<his:ContentTag>
							<table width="100%" cellpadding="0" cellspacing="1">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Group
											</font>
										</div>
									</td>
									<td width="75%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Certain infectious and parasitic diseases (A00-B99)
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Sub Group
											</font>
										</div>
									</td>
									<td width="75%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Intestinal infectious diseases (A00-A09)
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												Sub Diseases
											</font>
										</div>
									</td>
									<td width="75%" class="tdfont">
										<div align="left">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												A00.0, A00.1, A00.9
											</font>
										</div>
									</td>
								</tr>
							</table>
						</his:ContentTag>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
</div>
<div align="right">
<a>Back</a>
</div>
</body>
</html>