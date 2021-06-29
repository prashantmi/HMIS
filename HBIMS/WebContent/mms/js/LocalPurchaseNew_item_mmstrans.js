function calMRP(obj)
{
	var i = obj.parentNode.parentNode.id;
	var rt = Math.round((parseFloat(document.getElementById(parseInt(i)).querySelectorAll("input")[5].value)/parseFloat(document.getElementById(parseInt(i)).querySelectorAll("input")[4].value))*10000.0)/10000.0;
	//alert(rt);
	//alert(document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML);
	document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML=Math.round((+(rt*obj.value)/100.0)*10000.0)/10000.0;
	document.getElementById(parseInt(i)+1).querySelectorAll("label")[3].innerHTML=Math.round((parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML)+(rt*obj.value)/100.0)*10000.0)/10000.0;
    var j = parseInt(i/2) + 1; 
    document.getElementsByName("strAdm")[0].value = "0";
    document.getElementsByName("strCosttoPat")[0].value = "0";
    document.getElementsByName("strAdm")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML);
    document.getElementsByName("strCosttoPat")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[3].innerHTML);
    var totadmchg = (parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML)); // * parseFloat(document.getElementsByName("strTotalQty")[j].value) ) ;
	console.log("parseInt(totadmchg)"+parseInt(totadmchg) );
	if( parseInt(totadmchg) > 1000){
		document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML="1000";
		document.getElementById(parseInt(i)+1).querySelectorAll("label")[3].innerHTML=(parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML)+parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML)).toFixed(2);
		document.getElementsByName("strAdm")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML);
		document.getElementsByName("strCosttoPat")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[3].innerHTML);
		
	}
} 

function calMRP1(k , val)
{
	var i = k ; //obj.parentNode.parentNode.id;
	var rt = Math.round((parseFloat(document.getElementById(parseInt(i)).querySelectorAll("input")[5].value)/parseFloat(document.getElementById(parseInt(i)).querySelectorAll("input")[4].value))*10000.0)/10000.0;
	//alert(rt);
	//alert(document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML);
	document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML=Math.round((+(rt*val)/100.0)*10000.0)/10000.0;
	document.getElementById(parseInt(i)+1).querySelectorAll("label")[3].innerHTML=Math.round((parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML)+(rt*val)/100.0)*10000.0)/10000.0;
    var j = parseInt(i/2) + 1; 
    document.getElementsByName("strAdm")[0].value = "0";
    document.getElementsByName("strCosttoPat")[0].value = "0";
    document.getElementsByName("strAdm")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML);
    document.getElementsByName("strCosttoPat")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[3].innerHTML);
    
    var totadmchg = (parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML));// * parseFloat(document.getElementsByName("strTotalQty")[j].value) ) ;
	console.log("parseInt(totadmchg)"+parseInt(totadmchg) );
	if( parseInt(totadmchg) > 1000){
		document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML="1000";
		document.getElementById(parseInt(i)+1).querySelectorAll("label")[3].innerHTML=(parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML)+parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML)).toFixed(2);
		document.getElementsByName("strAdm")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[2].innerHTML);
		document.getElementsByName("strCosttoPat")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[3].innerHTML);
	}
    
} 

function calRateWithTax(obj)
{
	console.log('1');
	var i = obj.parentNode.parentNode.id;

	//console.log(document.getElementById(parseInt(i)).querySelectorAll("input")[5].value);	
	var rt = Math.round((parseFloat(document.getElementById(parseInt(i)).querySelectorAll("input")[5].value)/parseFloat(document.getElementById(parseInt(i)).querySelectorAll("input")[4].value))*10000.0)/10000.0;
	console.log('2');
//	console.log(rt);
	//alert(document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML);
//	console.log(document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML);
	document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML=   Math.round((rt+(rt*obj.value)/100.0)*10000.0)/10000.0;
	var j = parseInt(i/2) + 1;
	 console.log("i::"+i+" j::"+j);
	  console.log('3');
    document.getElementsByName("strPurWidTax")[0].value = "0";
    console.log('4');
    document.getElementsByName("strPurWidTax")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[0].innerHTML);
    console.log('5');
     //document.getElementsByName("strBrandId")[0].value = "0";
	//document.getElementsByName("strBrandId")[j].value = document.forms[0].strMultiRowItemId.value;	
	console.log('6');
	//console.log("document.getElementsByName(strBrandId)[j].value"+document.getElementsByName("strBrandId")[j].value);
	//console.log("document.multirowitemid.value"+document.forms[0].strMultiRowItemId.value);
	if(document.getElementById(parseInt(i)).querySelectorAll("input")[7].value !='')
	calMRP1(i , parseFloat(document.getElementById(parseInt(i)).querySelectorAll("input")[7].value)) ;
}   


function calRateWithTax1(k , val)
{
	console.log('1');
	var i = k ; //obj.parentNode.parentNode.id;

	//console.log(document.getElementById(parseInt(i)).querySelectorAll("input")[5].value);	
	var rt = Math.round((parseFloat(document.getElementById(parseInt(i)).querySelectorAll("input")[5].value)/parseFloat(document.getElementById(parseInt(i)).querySelectorAll("input")[4].value))*10000.0)/10000.0;
	console.log('2');
//	console.log(rt);
	//alert(document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML);
//	console.log(document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML);
	document.getElementById(parseInt(i)+1).querySelectorAll("label")[1].innerHTML=   Math.round((rt+(rt*val)/100.0)*10000.0)/10000.0;
	var j = parseInt(i/2) + 1;
	 console.log("i::"+i+" j::"+j);
	  console.log('3');
    document.getElementsByName("strPurWidTax")[0].value = "0";
    console.log('4');
    document.getElementsByName("strPurWidTax")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[0].innerHTML);
    console.log('5');
    // document.getElementsByName("strBrandId")[0].value = "0";
	// document.getElementsByName("strBrandId")[j].value = document.forms[0].strMultiRowItemId.value;	
	console.log('6');
	console.log("document.getElementsByName(strBrandId)[j].value"+document.getElementsByName("strBrandId")[j].value);
	console.log("document.multirowitemid.value"+document.forms[0].strMultiRowItemId.value);
	if(document.getElementById(parseInt(i)).querySelectorAll("input")[7].value !='')
		calMRP1(i , parseFloat(document.getElementById(parseInt(i)).querySelectorAll("input")[7].value)) ;
}   



function calRateUnit(obj)
{
	//alert(obj.value);
	var i = obj.parentNode.parentNode.id;
	var qty=parseInt(document.getElementById(parseInt(i)).querySelectorAll("input")[4].value);
	//alert((parseFloat(parseFloat(obj.value)/qty)*10000.0)/10000.0);
	document.getElementById(parseInt(i)+1).querySelectorAll("label")[0].innerHTML=Math.round((parseFloat(obj.value)/qty)*10000.0)/10000.0;
	//onkeypress="return validateData(event,5);"
	var j = parseInt(i/2) + 1;
	document.getElementsByName("strPurRate")[0].value = "0";
	document.getElementsByName("strPurRate")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[0].innerHTML);
	if(document.getElementById(parseInt(i)).querySelectorAll("input")[6].value !='')
	calRateWithTax1(i , parseInt(document.getElementById(parseInt(i)).querySelectorAll("input")[6].value));
}



function calRateUnit1(k,val)
{
	//alert(obj.value);
	var i = k ; //obj.parentNode.parentNode.id;
	var qty=parseInt(document.getElementById(parseInt(i)).querySelectorAll("input")[4].value);
	//alert((parseFloat(parseFloat(obj.value)/qty)*10000.0)/10000.0);
	document.getElementById(parseInt(i)+1).querySelectorAll("label")[0].innerHTML=Math.round((parseFloat(val)/qty)*10000.0)/10000.0;
	//onkeypress="return validateData(event,5);"
	var j = parseInt(i/2) + 1;
	document.getElementsByName("strPurRate")[0].value = "0";
	document.getElementsByName("strPurRate")[j].value = parseFloat(document.getElementById(parseInt(i)+1).querySelectorAll("label")[0].innerHTML);
	if(document.getElementById(parseInt(i)).querySelectorAll("input")[6].value !='')
	calRateWithTax1(i , parseInt(document.getElementById(parseInt(i)).querySelectorAll("input")[6].value));
}

function calQty(obj){
	
	var i = obj.parentNode.parentNode.id;
	var qty=parseInt(document.getElementById(parseInt(i)).querySelectorAll("input")[4].value);
	
	if(document.getElementById(parseInt(i)).querySelectorAll("input")[5].value !='')
	calRateUnit1(i,qty);
	
	
}
