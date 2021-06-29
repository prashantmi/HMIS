
	function getBedStatus(){
		
		if(document.forms[0].bedType.selectedIndex != 0){
		
			if(document.forms[0].roomType.selectedIndex == 0){
					
					document.forms[0].bType.selectedIndex = 0;
					alert("Please Select A Room Type");
					document.forms[0].roomType.focus();
					return false;
			}else{
				
				myFunc('1',"","");
			}
		}else{
			var objEle = document.getElementById("bedStatusDiv");
			objEle.innerHTML = "";
		}
	}

var pWindow ="";

function myFunc(mode,obj,status){

	if(mode == '1'){
		var hmode = "BEDSTATUSDTL"; 
		var url = "AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&wardCode=${advanceAdviceTransBean.strWard}&bDate=${advanceAdviceTransBean.strPropAdmissionDate}&bedType="+document.forms[0].bedType.value+"&roomType="+document.forms[0].roomType.value;
		ajaxFunction(url,"1");
	
	}else if(mode == '2'){
	
			pWindow = obj;
			var hmode = "BEDSTATUSPATIENTDTL"; 
		var url = "AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&wardCode=${advanceAdviceTransBean.strWard}&bDate="+obj.name+"&bedType="+document.forms[0].bedType.value+"&roomType="+document.forms[0].roomType.value+"&bStatus="+status;
		ajaxFunction(url,"2");
	}
	
	}
	
	function getAjaxResponse(res,mode){
	
		if(mode == '1'){
			
			var objEle = document.getElementById("bedStatusDiv");
			objEle.innerHTML = res;
		}else if(mode == '2'){
			var objEle = document.getElementById("menu1");
			objEle.innerHTML = res;
			
			display_popup_menu(pWindow,"menu1","20","");
			
		}
	}


	function bedStatusCheck_test(){
		
			var checkObj = document.forms[0].bedStatusCheck;
			var chkCnt = parseInt(0); 
			var selDtchk = parseInt(0);
			for(i = 0 ; i<checkObj.length; i++){
					
				if(checkObj[i].checked){
					selDtchk = i;
					chkCnt++;
					if(chkCnt>1){
						alert("Please Check Any One Status");
						chkCnt = 0;
						return false;
					}
					
				}
				
			}
			if(chkCnt == 0){
					alert("Please Check a Status");
					chkCnt = 0;
					return false;
				}else{
				
					var rVal = document.forms[0].roomType[document.forms[0].roomType.selectedIndex].text+"<input type='hidden' name='strRoomType' value='"+document.forms[0].roomType.value+"'>";
					var bVal = document.forms[0].bedType[document.forms[0].bedType.selectedIndex].text+"<input type='hidden' name='strBedType' value='"+document.forms[0].bedType.value+"'>";
					
					var sDate = checkObj[selDtchk].value;
					
					window.opener.setBedStatus( rVal, bVal ,sDate);
					
					window.close();
				}		
	}

	function roomTypeChange(obj){

			document.forms[0].bedType.selectedIndex = 0;
			var objEle = document.getElementById("bedStatusDiv");
			objEle.innerHTML = "";
		}

	
