function toggle(div_id) {
	var el = document.getElementById(div_id);
	if ( el.style.display == 'none' ) {	el.style.display = 'block';}
	else {el.style.display = 'none';}
	
	 
}
function blanket_size(popUpDivVar) {
	if (typeof window.innerWidth != 'undefined') {
		viewportheight = window.innerHeight;
	} else {
		viewportheight = document.documentElement.clientHeight;
	}
	if ((viewportheight > document.body.parentNode.scrollHeight) && (viewportheight > document.body.parentNode.clientHeight)) {
		blanket_height = viewportheight;
	} else {
		if (document.body.parentNode.clientHeight > document.body.parentNode.scrollHeight) {
			blanket_height = document.body.parentNode.clientHeight;
		} else {
			blanket_height = document.body.parentNode.scrollHeight;
		}
	}
	var blanket = document.getElementById('blanket');
	blanket.style.height = blanket_height + 'px';
	var popUpDiv = document.getElementById(popUpDivVar);
	popUpDiv_height=blanket_height/2-200;//200 is half popup's height
	popUpDiv.style.top = popUpDiv_height + 'px';
}
function window_pos(popUpDivVar) {
	if (typeof window.innerWidth != 'undefined') {
		viewportwidth = window.innerHeight;
	} else {
		viewportwidth = document.documentElement.clientHeight;
	}
	if ((viewportwidth > document.body.parentNode.scrollWidth) && (viewportwidth > document.body.parentNode.clientWidth)) {
		window_width = viewportwidth;
	} else {
		if (document.body.parentNode.clientWidth > document.body.parentNode.scrollWidth) {
			window_width = document.body.parentNode.clientWidth;
		} else {
			window_width = document.body.parentNode.scrollWidth;
		}
	}
	var popUpDiv = document.getElementById(popUpDivVar);
	window_width=window_width/2-200;//200 is half popup's width
	popUpDiv.style.left = window_width + 'px';
}
function popup(windowname) {
	//alert(windowname);
	blanket_size(windowname);
	window_pos(windowname);
	toggle('blanket');
	toggle(windowname);	
	//demo1();
}

 

function popupClose(windowname) {
	//alert("close"+windowname);
	blanket_size(windowname);
	window_pos(windowname);
	toggle('blanket');
	toggle(windowname);		
	refresh();
}




function popupIn(windowname) {
	//alert(windowname);
	
	//alert(document.getElementsByName('mandComboTextBoxComboNamesOnPage')[0].value.length);
	
	 var spliTheValue=document.getElementsByName('mandComboTextBoxComboNamesOnPage')[0].value.split('&');
	 var concatValue="";
	 var length=spliTheValue.length;
	 //alert(length);
	  for(var i=0;i<length;i++)
		  {
		  
		  //alert(document.getElementsByName(spliTheValue[i])[0].value);
//alert(spliTheValue[i]);
		  
		  if(spliTheValue[i]!='null')
		  {
		  //alert("not null");
		
			  if(document.getElementsByName(spliTheValue[i])[0].value==""||document.getElementsByName(spliTheValue[i])[0].value=="-1")
		  {
			  
			  if(i==0)
				  { 
			  blanket_size(windowname);
				window_pos(windowname);
				toggle('blanket');
				toggle(windowname);	
			  }
		//  alert("Please Enter/Select Field Focused");
		  document.getElementsByName(spliTheValue[i])[0].focus()
	    	 
	    	 
	    	return false;
		 }
		  
		  
		  
		
			 // alert(spliTheValue[i]);
			   
		  concatValue=concatValue.concat(document.getElementsByName(spliTheValue[i])[0].value);
			  }
		  
		  concatValue+="@";
		  }
	 
	  //alert("final Values"+concatValue);
	 
	  document.getElementsByName('finalMandValues')[0].value=concatValue;
	
	blanket_size(windowname);
	window_pos(windowname);
	toggle('blanket');
	toggle(windowname);		
}