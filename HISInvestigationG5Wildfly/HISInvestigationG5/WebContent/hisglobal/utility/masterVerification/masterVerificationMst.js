window.onload=function(){
	var hmode=document.getElementsByName("hmode")[0].value
	if(hmode=='MODIFY' || hmode=='VIEW'){
		if(document.getElementsByName("criteriaLabel")[0].value!=""){
			document.getElementsByName("isCriteriaRequired")[0].checked=true;
		}
		showCriteria(document.getElementsByName("isCriteriaRequired")[0])
		var columnHeaderString=document.getElementsByName("columnHeaderString")[0].value
		var columnHeader=columnHeaderString.split("#");
		var newColumnHeaderString="";
		var mode=0;
		if(hmode=="VIEW")
			mode=1;
		if(columnHeaderString!=""){	
			for(var i=0;i<columnHeader.length;i++){
				addrows('columnTable',columnHeader[i],"columnName",1,mode);
			}
		}
		
		var orderByString=document.getElementsByName("orderByString")[0].value
		if(orderByString!=""){
			var orderBy=orderByString.split("#");
			var newOrderByString="";
			for(var i=0;i<orderBy.length;i++){
				addrows('orderByTable',orderBy[i],"orderByValue",2,mode);
			}
		}	
	}

}

function addColumn(){

	if(document.getElementsByName("columnHeader")[0].value==""){
		alert("Please Enter Column Header");
		document.getElementsByName("columnHeader")[0].focus();
		return false;
	}
	else{
		var columnHeaderString=document.getElementsByName("columnHeaderString")[0].value
		if(columnHeaderString==""){
			columnHeaderString=document.getElementsByName("columnHeader")[0].value
		}
		else{
			columnHeaderString=columnHeaderString+"#"+ document.getElementsByName("columnHeader")[0].value
		}
		
		document.getElementsByName("columnHeaderString")[0].value=columnHeaderString
		addrows('columnTable',document.getElementsByName("columnHeader")[0].value,"columnName",1,0);
		document.getElementsByName("columnHeader")[0].value=""
	}	
}

function addOrderBy(obj){
	if(document.getElementsByName("orderBy")[0].value==""){
		alert("Please Enter Order By");
		document.getElementsByName("orderBy")[0].focus();
		return false;
	}
	else{
		var orderByString=document.getElementsByName("orderByString")[0].value
		if(orderByString==""){
			orderByString=document.getElementsByName("orderBy")[0].value
		}
		else{
			orderByString=orderByString+"#"+ document.getElementsByName("orderBy")[0].value
		}	
		document.getElementsByName("orderByString")[0].value=orderByString
		addrows('orderByTable',document.getElementsByName("orderBy")[0].value,"orderByValue",2,0);
		document.getElementsByName("orderBy")[0].value=""
	}	
}

function addrows(tablename,colValue,columnName,mode,isReadonly)
{
  	var index=0;
	var nRow=0;
	var tableObj=document.getElementById(tablename);
	var numRows=tableObj.rows.length;
	var icdCode;
	var disease;
	var maxlength;
	
	nRow=numRows
		var tabRow=tableObj.insertRow(numRows);
		tabRow.id="row"+(parseInt(nRow)+1);
	
		var td1=document.createElement("TD");
		var td2=document.createElement("TD");
		var td3=document.createElement("TD");
		
		
		td1.innerHTML="<div align=\"right\">"
	  				+ "</div>";
		td1.className='tdfonthead';																													
		td1.colspan="1";
		td1.width="35%";

		td2.innerHTML="<div align=\"left\">"
					+ colValue
	  				+ "<input type=hidden name=\""+ columnName + "\" value=\""+ colValue + "\">"
	  				+ "</div>";
		td2.className='tdfont';																													
		td2.colspan="1";
		td2.width="45%";
		
		if(isReadonly==0){
			td3.innerHTML="<div align=\"left\">"
					+ "<img class=\"button\" src=\"/HISInvestigationG5/hisglobal/images/RoundMinus12x12.png\" " 
					+ "style=cursor:pointer onclick=\"removeColumn("+tabRow.id+",'"+colValue+"','"+mode+"');\" onkeypress=\"if(event.keyCode==13) addColumn("+tabRow.id+");\" size='7'"
					+ "tabindex='1'></div>";
		}
		else{
			td3.innerHTML="<div align=\"left\">"
					+ "</div>";
		}
		
	
		td3.className='tdfont';																													
		td3.colspan="1";
		td3.width="20%";

		tabRow.appendChild(td1);
		tabRow.appendChild(td2);
		tabRow.appendChild(td3);
		
		document.getElementsByName('noOfRows')[0].value=(parseInt(nRow)+1);
		
}

function removeColumn(rowid,columnValue,mode){
	
	if(mode==1){
		removeRow(rowid,'columnTable')
		var columnHeaderString=document.getElementsByName("columnHeaderString")[0].value
		var columnHeader=columnHeaderString.split("#");
		var newColumnHeaderString="";
		for(var i=0;i<columnHeader.length;i++){
		if(columnHeader[i]!=columnValue){
			newColumnHeaderString=newColumnHeaderString+"#"+columnHeader[i]
		}
	}
		document.getElementsByName("columnHeaderString")[0].value=newColumnHeaderString.substring(1)
	}	
	else{	
		removeRow(rowid,'orderByTable')
		var orderByString=document.getElementsByName("orderByString")[0].value
		var orderBy=orderByString.split("#");
		var newOrderByString="";
		for(var i=0;i<orderBy.length;i++){
			if(orderBy[i]!=columnValue){
				newOrderByString=newOrderByString+"#"+orderBy[i]
			}
		}
		document.getElementsByName("orderByString")[0].value=newOrderByString.substring(1)
	}
}

function removeRow(rowid,tableName){
	var index=document.getElementsByName('noOfRows')[0].value
	var tableObj=document.getElementById(tableName);
	var len=tableObj.rows.length
	var rows=tableObj.rows
	//alert("rowid ;"+ rowid.rowIndex)
	//alert(rows.namedItem(rowid).id)
	tableObj.deleteRow(rowid.rowIndex);
	document.getElementsByName('noOfRows')[0].value=index-1;
}

function validateSave(mode){
	
	if(mode=="SAVE"){
		if(!isSelected(document.getElementsByName('moduleId')[0],"Module Name"))
			return false;
	}
	
	if(isEmpty(document.getElementsByName('mainHeader')[0],"Main Header")
		&& validateColumnHeader()
		&& isEmpty(document.getElementsByName('mainQuery')[0],"Main Query")
		&& validateCriteria()){
		submitForm(mode);	
	}
	else{
		return false;
	}	
}

function validateCriteria(){
	if(document.getElementsByName('isCriteriaRequired')[0].checked){
		
		if(isEmpty(document.getElementsByName('criteriaLabel')[0],"Criteria Label")
			&& isEmpty(document.getElementsByName('criteriaColumn')[0],"Criteria Column")
			&& isEmpty(document.getElementsByName('columnSubHeader')[0],"Column Sub Header")
			&& isEmpty(document.getElementsByName('criteriaQuery')[0],"Criteria Query")
			&& isEmpty(document.getElementsByName('groupQuery')[0],"Group Query")
			){
			return true;
		}
		else{
			return false;
		}
	}
	
	return true;
}

function showCriteria(obj){
	if(obj.checked){
		document.getElementById("criteriaDiv").style.display="block"
	}
	else
		document.getElementById("criteriaDiv").style.display="none"
}

function validateColumnHeader(){
	if(document.getElementsByName('columnHeader')[0].value=="" 
		&& document.getElementsByName('columnHeaderString')[0].value==""){
		alert("Please Enter Column Header");
		document.getElementsByName('columnHeader')[0].focus();
		return false;	
	}
	return true	
}

function clearForm(){
	document.getElementsByName("mainHeader")[0].value=""	
	document.getElementsByName("columnHeader")[0].value=""	
	
	document.getElementsByName("mainQuery")[0].value=""	
	document.getElementsByName("orderBy")[0].value=""	
	document.getElementsByName("criteriaLabel")[0].value=""	
	document.getElementsByName("criteriaColumn")[0].value=""	
	document.getElementsByName("columnSubHeader")[0].value=""	
	document.getElementsByName("criteriaQuery")[0].value=""	
	document.getElementsByName("groupQuery")[0].value=""	
	document.getElementsByName("criteriaQuery")[0].value=""
	
	/*
	var columnHeaderString=document.getElementsByName("columnHeaderString")[0].value
	var columnHeader=columnHeaderString.split("#");
	var newColumnHeaderString="";
	
	if(columnHeaderString!=""){	
		var tableObj=document.getElementById("columnTable");
		var len=tableObj.rows.length
		var rows=tableObj.rows
		
		
		for(var i=2;i<=len;i++){
			tableObj.deleteRow(i);
			len--;
			//i--;
		}
	}
	document.getElementsByName("columnHeaderString")[0].value=""
	
	var orderByString=document.getElementsByName("orderByString")[0].value
	if(orderByString!=""){
		var orderBy=orderByString.split("#");
		var newOrderByString="";
		for(var i=1;i<=orderBy.length;i++){
			var rowid="row"+(i+1);
			if(document.getElementById(rowid)){
				document.getElementById(rowid).innerHTML="";
			}
		}
	}	
	document.getElementsByName("orderByString")[0].value=""
	*/
}