<%
try
{
%>
<frameset rows="100%,0%" border="0" FRAMESPACING="0" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0" MARGINWIDTH="0">

<frame id="frameMain" src="/HISClinical/opd/gotoIcdEntryFormDesk.cnt" scrolling="auto" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0">

<frame id="frameFooter" src="/HISClinical/opd/transaction/opd_ICDEntryDeskFooter.jsp"  scrolling="no" TOPMARGIN="0" LEFTMARGIN="0" MARGINHEIGHT="0">

</frameset>
<%
}
catch (Exception e)
{
	e.printStackTrace();
}
%>