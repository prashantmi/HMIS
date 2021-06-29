function printIt(){
	
	document.getElementsByName('htmlCode')[0].value=document.getElementById('htmlCode').innerHTML
	var htmlCode=document.getElementById('htmlCode').innerHTML
	document.getElementById('print').innerHTML=null
	document.getElementsByName("hmode")[0].value="POPUP"
	document.forms[0].submit()

}
function callDialogOnLoad(){

	if(document.forms[0].hmode){
		
		var copies=document.getElementsByName("copies")[0].value;
		var isEditable=document.getElementsByName("isCopiesEditable")[0].value;
		//alert("document.forms[0].hmode.value :" +document.forms[0].hmode.value)
		if(document.forms[0].hmode.value=="POPUP"){
			document.getElementById('print').innerHTML=null
			var context=document.getElementsByName("context")[0].value;
			var url=context + "/hisglobal/utility/print/printDialog.jsp?copies=" + copies + "&isCopiesEditable=" + isEditable
			var myPopup=window.open(url , "Print","width=400,height=220" , "scrollbars=no,resizable=false")
			if (!myPopup.opener)
		  	 	myPopup.opener = self;	
			}	
		}
}
