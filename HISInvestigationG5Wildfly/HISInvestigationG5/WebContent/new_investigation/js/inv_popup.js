
function CSSPopupPanelModalArea(){
	 var objIframe=document.getElementById('PopupPanelModalArea');
	 objIframe.style.left=0;
	 objIframe.style.top=0;
	 objIframe.style.height="150%";
	 objIframe.style.width="150%";
	 objIframe.style.position = "absolute";
	 objIframe.style.background="silver";
	 var value=6;
	 objIframe.style.opacity = value/10;
	 objIframe.style.filter = 'alpha(opacity=' + value*10 + ')';	     
}
function CSSPPopupPanel(left,top,border,divid){
	
	  if(left=="")left="50%";
	  if(top=="")top="50%";
	 // if(height=="")height="200px";
	  //if(width=="")width="400px";
	  if(border=="")border="solid 1px black";
	 	
	 var objDiv=document.getElementById(divid);
	 objDiv.style.left=left;
	 objDiv.style.top=top;
	 objDiv.style.height="auto";
	 objDiv.style.width="auto"; 
	 objDiv.style.border=border;
	 objDiv.style.marginTop="-100px";
	 objDiv.style.marginLeft="-200px";	 
	 objDiv.style.position = "absolute";	 
	 objDiv.style.background="#FFF6ED";	 
	 objDiv.style.zIndex="100";   
	 objDiv.style.display="";
}

function Popup(){
}
var popup=new Popup();

/**********************************
Simply displays or hides the panel
**********************************/
Popup.showModal = function(divId) 
{
	
	var objdiv=document.getElementById(divId);
	//alert('objdiv  '+objdiv);
	//alert(document.getElementById("PopupPanel"));
	if(!document.getElementById("PopupPanel")){
		var divTag = document.createElement("div");
 		divTag.id="PopupPanel";
 		divTag.style.display="none";
		var htmldata="<iframe  frameborder='0' scrolling='0' id='PopupPanelModalArea'></iframe>";
    	divTag.innerHTML=htmldata;
  		document.body.appendChild(divTag);    	    	    
    }
    CSSPopupPanelModalArea();
    CSSPPopupPanel(objdiv.style.left,objdiv.style.top,objdiv.style.border,divId);
	var panelContainer = document.getElementById("PopupPanel");
	panelContainer.style.display = "";	
	//alert("divId---" + divId);
	//alert(document.getElementById(divId).innerHTML);
	//document.getElementById('IDDIVCONTENT').innerHTML=document.getElementById(divId).innerHTML;
	document.getElementById('PopupPanelModalArea').focus();
	document.body.onfocus = function() { document.getElementById('PopupPanelModalArea').focus(); };	
}
Popup.hide = function(divId)
{
	var panelContainer = document.getElementById("PopupPanel");
	panelContainer.style.display = "none";
	document.getElementById(divId).style.display=  "none";
	document.body.onfocus = function() { return true; };
	document.body.removeChild(document.getElementById('PopupPanel'));	
}
