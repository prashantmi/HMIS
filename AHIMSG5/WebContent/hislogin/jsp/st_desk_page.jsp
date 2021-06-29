<script>
function callMenu(url)
{
	var targetURL = url + "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
	
	var elemFrame = document.getElementById("frmMain");
	elemFrame.src=targetURL;
	elemFrame.refresh();
}

function setFrameSize()
{
 	var elemFrame = document.getElementById("frmMain");
// 	elemFrame.height = window.height-100;
// 	elemFrame.width = window.width-5;
}
</script>

<jsp:include page="/hislogin/transactions/jsp/st_horizontal_menu.jsp"></jsp:include>

<div align="center" style="width:95%;height: 100%"> 
<iframe id="frmMain" width="100%" height="700px" frameborder="0"></iframe>
<script type="text/javascript"> setFrameSize();</script>
</div>