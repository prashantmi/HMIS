function deleteData(_these){
	var strChk = document.getElementsByName("chk");
	for(nTmpI = 0; nTmpI < strChk.length; nTmpI++){
		if(strChk[nTmpI].checked){
			if(strChk[nTmpI].value.replace("|","@").split("@")[6] == 0){
				alert("Selected Data can not be Deleted.");
				return;
			}
		}
	}
	document.forms[0].hmode.value = "DELETEDATA";
	document.forms[0].submit();
	
}

function deleteChargeRecords()
{
	var combo1;
	var prevNext	=	null;
	var totalChk	=	0;
	var primarykey	=	"";
	var search		=	"";
	var str			=	"";
	var str			=	"";
			
	var mode = "DELETEDATA";
	var len			= 	document.forms[0].chk.length;
	var no_of_combo	=	document.forms[0].no_of_combo.value;
		
	var strChk = document.getElementsByName("chk");
	for(nTmpI = 0; nTmpI < strChk.length; nTmpI++){
		if(strChk[nTmpI].checked){
			if(strChk[nTmpI].value.replace("|","@").split("@")[6] == 0){
				alert("Selected Data can not be Deleted.");
				return;
			}
		}
	}
	if(document.forms[0].combo !=null)
		combo1 		=	document.forms[0].combo;	
	
	//call in this file	 
	if(checkForDelete()==false) return ;
		 
	var divisionId		= document.forms[0].divisionId.value;
	var rec_per_page	= document.forms[0].record_per_page.value;
	var prevDivIndex 	= divisionId.substring(1,divisionId.length);
	var min_rec_len 	= parseInt(rec_per_page)*(parseInt(prevDivIndex) -1); 
	var max_rec_len 	= parseInt(rec_per_page) * parseInt(prevDivIndex);
	
	if(!isNaN(document.forms[0].chk.length))
	{
		for(var i=min_rec_len;i< max_rec_len && i < document.forms[0].chk.length;i++)
		{
			if(document.forms[0].chk[i].checked==true)
			{
				totalChk+=1;
				primarykey +=document.forms[0].chk[i].value;
		  		primarykey +="~"; // concatenating all chk value with @ symbols to a single variable to for easy manipulations like deleting the records and update
		  	}
		  }
		  
	}
	else
		primarykey=document.forms[0].chk.value;
		
		
    var retValue=confirm("Selected Record (s) are being deleted\n\nAre You Sure");	 	
	if(retValue==false) return;
	

	if(prevNext	==	null) prevNext='1';
	
	if(no_of_combo >1)
		{
			for(i=0;i<combo1.length;i++)
				str += "&combo="+combo1[i].value;
		}
	else
		if(no_of_combo==1) 
			str += "&combo="+combo1.value;		
	
	
	var minrow		=	document.forms[0].minrow.value;	
	var max_rownum	=	document.forms[0].max_rownum.value;	
	var blockNo		=	document.forms[0].blockNo.value;  
	var divisionId	=	document.forms[0].divisionId.value;
	var search			=	"";
	var searchColumn	=	null;
	var rowNum			=	minrow;
	var params="CNTChargeMst.cnt?hmode="+mode+str+"&divisionId="+divisionId+"&chk="+primarykey+"&prevNext="+prevNext
					+"&rowNum=0&minrow="+minrow+"&blockNo="+blockNo+"&max_rownum="+max_rownum+"&search="+search+"&searchColumn="+searchColumn;
	params = createFHashAjaxQuery(params);
	xmlHttp.open("GET",params,true);
	document.getElementById("message").style.display='block';	
	document.getElementById('start').innerHTML ='<table align=center bgcolor="RED"><tr><td> <FONT size=3 color=\"white\">Wait Deleting Records...</FONT></td></tr></table>';
	
		
	xmlHttp.onreadystatechange = function()
	{ 
		if(xmlHttp.readyState ==4 )
		{
			if(xmlHttp.status == 200 || xmlHttp.status=='complete' )
			{
				var response 		= 	xmlHttp.responseText;
				
							
				var responseData	=	new Array();
				responseData	=	response.split("####");
				document.getElementById('message').innerHTML 	= 	responseData[6];
	        	document.forms[0].record_per_page.value			=	responseData[0];
				document.forms[0].no_of_combo.value				=	responseData[1];
				document.forms[0].actual_page_block.value		=	responseData[2];
				document.forms[0].totalpage.value				=	responseData[3];
				document.forms[0].divisionId.value				=	responseData[7];
				document.getElementById("start").innerHTML 		= 	responseData[4];
				document.getElementById("searchid").innerHTML	= 	responseData[5];
				document.getElementById("footer").style.display	=	'block';
				//document.getElementById("a1").style.display		=	'none';
					
				var	curr_block		=	responseData[7];
			  	
			   	 if(document.getElementById("bb"+curr_block) !=null)
			   	{
			   		document.getElementById(curr_block).style.display='block';
			   		document.forms[0].divisionId.value=curr_block;
			   		changeDiv(curr_block);
			   		document.forms[0].divisionId.value=curr_block;
					document.getElementById("bb"+curr_block).style.color='red';
				}
			else
			    if(document.getElementById("bba1") !=null)
			   	{
			   	document.getElementById("a1").style.display='block';
			   	document.forms[0].divisionId.value="a1";
			 	document.getElementById("bba1").style.color='red';
				}						
			  	
			 }			
		}
	}
	
	xmlHttp.send(params);
	
}	




	/**
	 * modifyPage
	 * @param {String} mode		  
	 */
	 function modifyPage(form1) {
	 	
	  
	  	var cmbVal = "";
	with(form1)
	{
	
		if(combo[0].value == "0")
		{
			alert("Please Select a Hospital Service");
			return;
			
		} else {
						
			 edit("MODIFY");
			 
			
		}	
	}		
	  
	 	
	 }
v