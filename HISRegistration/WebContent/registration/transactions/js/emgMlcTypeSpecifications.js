var mlcEpisodeType_imgDivHTML;
$(document).ready(function(){
	emgMlcTypeSpecJsObj.createFirstRow_for_MlcEpisodeType();
	
	/*for(var i=0; i<10; i++)
		emgMlcTypeSpecJsObj.createMultiDIv();*/
	
	$("#imgPlusId").click(function(){
		//var actionName=$("#actionNameId").val();
		if($('#TBL_MLC_TYPE_ID').form('validate') == false){
			alert("Kindly Fill Previous MLC Details First");
			return false;
		}
		emgMlcTypeSpecJsObj.createMultiDIv();
	});
	/*$("#mlcTypeCodeId").validatebox({validType: 'selectCombo[-1]'});
	$("#injurySideId").validatebox({validType: 'selectCombo[-1]'});
	$("#injuryNatureCodeId").validatebox({validType: 'selectCombo[-1]'});
	$("#burnPercentageId").validatebox({validType: 'numeric'});*/
	
	emgMlcTypeSpecJsObj.addValidation();
	
});


var emgMlcTypeSpecJsObj={
	createFirstRow_for_MlcEpisodeType : function(){
		mlcEpisodeType_imgDivHTML=$("#TR_MLC_TYPE_ID_").html();
		var objTmp=$('.tmpClass');
		var uniqueId= getUniqueId();
		var rowId="TR_MLC_TYPE_ID_"+ uniqueId;
		objTmp[0].id=rowId;
		var childRowId="CHILD_TR_MLC_TYPE_ID_"+ uniqueId;
		objTmp[1].id=childRowId;
		
		//emgMlcTypeSpecJsObj.createImgHeaderDiv(rowId,childRowId);
	},
	createImgHeaderDiv : function(rowId,childRowId){
		var strHtml="<img src='/HIS/hisglobal/images/avai/minus.gif' style='cursor: pointer;' onclick=DeleteRows('"+ rowId+"')>";
	 	createTableData(childRowId, 'div-table-col width5', strHtml, '5');
	},
	createMultiDIv : function(callBackFn){
		//alert("emgMlcTypeSpecJsObj :: createMultiDIv()");
		console.log("emgMlcTypeSpecJsObj :: createMultiDIv() called");
		var rId="TR_MLC_TYPE_ID_";
	 	var tableId="TBL_MLC_TYPE_ID";
	 	var rowclass="div-table-row";
	 	var newRowId= createTableRow(tableId,rId,rowclass);  
	 	var childNewRowId = "CHILD_"+ newRowId;
	 	console.log("emgMlcTypeSpecJsObj :: createMultiDIv() -->> childNewRowId :"+childNewRowId);
	 	var imgDivHTML = mlcEpisodeType_imgDivHTML.replace("CHILD_TR_MLC_TYPE_ID_", childNewRowId);
	 	//imgDivHTML="<span> Hello </span>";
	 	$("#"+ newRowId).html(imgDivHTML);
	 	emgMlcTypeSpecJsObj.createImgHeaderDiv(newRowId,childNewRowId);
	 	
	 	emgMlcTypeSpecJsObj.addValidation();
	 	var objMlcTypeCode = $('[name="mlcTypeCode"]');
	 	objMlcTypeCode[objMlcTypeCode.length-1].focus();
	 	/*alert("callBackFn :"+callBackFn);
	 	if(callBackFn!=undefined && callBackFn!="")
	 		eval(callBackFn);*/
	},
	addValidation : function(){
		$('[name="mlcTypeCode"]').validatebox({validType: 'selectCombo[-1]'});
		$('[name="injurySide"]').validatebox({validType: 'selectCombo[-1]'});
		$('[name="injuryNatureCode"]').validatebox({validType: 'selectCombo[-1]'});
		$('[name="burnPercentage"]').validatebox({validType: 'numeric'});
	}
};

/*function myMlcParamComboValidation(){
	var objMlcTypeCode= $('[name="mlcTypeCode"]');
	for(var i=0; i<objMlcTypeCode.length; i++){
		if(objmlcTypeCode[i])
	}
}*/

/*$("#imgPlusId").click(function(){
	emgMlcTypeSpecJsObj.createMultiDIv();
});*/