/**
 * Added by krishnan nema on 12/02/2019 for removing special characters from result entry, validation, revalidation
 * 
 */


function removeSpecialCharacter(result){

	var res  = result.replace("$", " ", true);
    var res2  = res.replace("#", " ", true);
    var res3 = res2.replace("~", " ", true);
    var res4 = res3.replace("`", " ", true);
    var res5 = res4.replace("&", " and ", true);
    var res6 = res5.replace("!", " ", true);
    var res7 = res6.replace("<", " less than ", true);
    var res8 = res7.replace(">", " greater than ", true);
    
    var finalResult = res8.replace("@", " ", true);
    
    return  finalResult;
}

function removeSpecialCharacterEditor(result){

	//var res  = result.replace("$", " ", true);
    var res2  = result.replace("#", " ", true);
    var res3 = res2.replace("~", " ", true);
    var res4 = res3.replace("`", " ", true);
    //var res5 = res4.replace("!", " ", true);
/*    var res6 = res5.replace("<", " less than ", true);
    var res7 = res6.replace(">", " greater than ", true);*/
    var res5 = res4.replace("&lt;", " less than ", true);
    var res6 = res5.replace("&gt;", " greater than ", true);
    var finalResult = res6.replace("@", " ", true);
    
    return  finalResult;
}

function checkReservedCahracters(value){
	//alert("fdejrh");
	//alert("Value "+value);
	var specialChars = "@!#$^|`";
	for(i = 0; i < specialChars.length;i++){
	//	alert("werwrwr");
        if(value.indexOf(specialChars[i]) > -1){
      ///  	alert("true");
            return true;
        }
    }
    return false;
}





