<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
<%@page import="scanning.vo.DocumentMasterVO"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<script language="JavaScript" src="/HISClinical/hisglobal/utility/generictemplate/js/commonDesigner.js"></script>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/registration/js/popup.js" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function onClickDocNum(_docId, _docFileExt)
{
	var divShow = document.getElementById("divDocumentShow");
	setAJAXLoading(divShow);
	if(_docFileExt.toUpperCase() != "PDF")
	{
		var docData = getScannedDocsWithFTP(_docId, _docFileExt);
		if(docData!=null)
		{
			divShow.innerHTML = "";
			var img = PragyaDesigner.createElement("img",divShow);
			img.id = "imgDoc";
			img.src = "/HISClinical/scanning/viewDocWithFTP?strFileName="+_docId+"&strFileExt="+_docFileExt;
			img.alt = 'No Image Found';
			img.title = _docId;
			img.width = 700;
		}
		else
		{
			divShow.innerHTML = "No Document Found";
		}
	}
	else
	{
		divShow.innerHTML = "";
		try
		{
			var frame = PragyaDesigner.createElement("iframe",divShow);
			frame.id = "ifrmDoc";
			frame.width = 700;
			frame.height = 800;
			frame.src = "/HISClinical/scanning/viewDocWithFTP?strFileName="+_docId+"&strFileExt="+_docFileExt;
		}
		catch(e)
		{
			divShow.innerHTML = "No Document Found"
		}
	}
}


// Gettting Document through FTP
function getScannedDocsWithFTP(_docName, _docExt)
{
	var flg = false;
	var objDocData = null;
	var objXHR = {url: "/HISClinical/scanning/viewDocWithFTP?strFileName="+_docName+"&strFileExt="+_docExt,
		sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			objDocData = data;
			if(trimData(objDocData)=="" || objDocData==null)	flg = false;
			else	flg = true;
		},
        error: function(error)
        {
            alert(error);
            if(typeof objDocData == 'undefined' || objDocData==null || objDocData=="" || objDocData.length==0)
            	alert("No Document Found");
            objDocData = null;
            flg = false;
        }};
	
	var objDojoAjax = dojo.xhrPost(objXHR);
	if(!flg)	objDocData = null;
	return objDocData;
}


	
</script>
	


<html:form action="/emrDesk">

	<body>
		<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>

		<logic:present name="<%=MrdConfig.PATIENT_SCANNED_DOCS%>" >
		<his:TitleTag name="Scanned Documents">
		</his:TitleTag>
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="30%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Document Type
							</font>
						</div>
					</td>
					<td width="30%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Visit Date
							</font>
						</div>
					</td>
					<td width="40%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Document Numbers
							</font>
						</div>
					</td>
				</tr>
				<%
					List lstDocs = (List) session.getAttribute(MrdConfig.PATIENT_SCANNED_DOCS);
					for(int i=0; i<lstDocs.size(); i++)
					{
						DocumentMasterVO scanDoc = (DocumentMasterVO) lstDocs.get(i);
						String match= scanDoc.getEpisodeCode()+scanDoc.getEpisodeVisitNo()+scanDoc.getStrDocumentDate();
				%>
					<tr>
						<td class="tdfont" width="30%" >
							<div align="center">
								<b>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=scanDoc.getStrDocTypeName()%>
									</font>
								</b>
							</div>
						</td>
						<td class="tdfont" width="30%" >
							<div align="center">
								<b>
									<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
										<%=scanDoc.getStrDocumentDate()%>
									</font>
								</b>
							</div>
						</td>
						<td class="tdfont" width="60%" >
							<div align="center">
								<b>
									<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
									<%
										int num=1;
										boolean flg = true;
										do
										{
									%>
										<a style="cursor:pointer" tabindex="1" onclick="onClickDocNum('<%=scanDoc.getStrDocumentId()%>','<%=scanDoc.getStrFileExt()%>')">
											<%=num%>
				      					</a>&nbsp;&nbsp;
			      					<%
				      						String matchWith = "";
				      						if((i+1)<=(lstDocs.size()-1))
				      						{
				      							DocumentMasterVO scanDocNext = (DocumentMasterVO) lstDocs.get(i+1);
				      							matchWith = scanDocNext.getEpisodeCode()+scanDocNext.getEpisodeVisitNo()+scanDocNext.getStrDocumentDate();
				      							
				      							if(match.equalsIgnoreCase(matchWith))
				      							{
				      								i++;
				      								num++;
				      								scanDoc = scanDocNext;
				      							}
				      							else
				      								flg = false;
				      						}
				      						else
				      							flg = false;
										} while (flg);
			      					%>
									</font>
								</b>
							</div>
						</td>
					</tr>
				<%
					}
				%>
			</table>
			<div align="center" id="divDocumentShow"></div>
			</his:ContentTag>		
		</logic:present>
	
		<logic:notPresent name="<%=MrdConfig.PATIENT_SCANNED_DOCS%>" >
		<his:ContentTag>
		<table width="100%" >
			<tr>
			<td class="tdfont" width="100%" nowrap>
			 	<div align="center">
		 		<b>
				 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
					<b>	<bean:message key="noScanDocFound"/> </b>
					</font>
				</b>
				</div>
			</td>
			</tr>
		</table>
		</his:ContentTag>
		</logic:notPresent>
		
		
		
	</body>	
	<his:status/>

</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 