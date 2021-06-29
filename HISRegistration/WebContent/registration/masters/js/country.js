/**
 * 
 */
function validateSave(){
	alert("inside validateSave()");
	var objChk= document.getElementsByName("strChk");
	for(var i=0; i<objChk.length; i++){
		if(objChk[i].checked){
			alert("Primary Key :"+document.getElementsByName("strPrimaryKey").value);
		}
	}
	
}
