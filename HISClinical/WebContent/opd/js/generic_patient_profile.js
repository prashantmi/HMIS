// Generic Patient Profile Javascript Function

function displayProfileFile(path)
{
	window.open(createFHashAjaxQuery(path),'DisplayFile','status=yes,scrollbars=yes,height='+screen.availHeight+',width='+screen.availWidth+ ',left=10,top=10,,dependent=yes,resizable=yes');
}

function submitForm(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function findIndex(obj)
{
	var ctrlArr = document.getElementsByName(obj.name);
	var index=-1;
	for(var i=0;i<ctrlArr.length;i++)
	{
		if(ctrlArr[i]==obj)
		{
			index=i;
			break;
		}
	}
	return index;
}

function onChangeSelectedIndex(chk)
{
	var index = findIndex(chk);
	var accType = document.getElementsByName("selectedAccessType")[index];
	var usrLevel = document.getElementsByName("selectedUserLevel")[index];
	if(chk.checked)
	{
		accType.disabled = false;
		usrLevel.disabled = false;
	}
	else
	{
		accType.disabled = true;
		usrLevel.disabled = true;
	}
}

function validateModifyProfile()
{
	var chks = document.getElementsByName("selectedIndex");
	var sels = 0;
	for(var i=0; i<chks.length; i++)
	{
		if(chks[i].checked)
		{
			sels++;
			var accType = document.getElementsByName("selectedAccessType")[i];
			if(accType.value == "-1")
			{
				alert("Select Access Type");
				accType.focus();
				return false;
			}
			var usrLevel = document.getElementsByName("selectedUserLevel")[i];
			if(usrLevel.value == "-1")
			{
				alert("Select User Level");
				usrLevel.focus();
				return false;
			}
		}
	}
	if(sels==0)
	{
		alert("Select at Least one Profile to Modify");
		chks[0].focus();
		return false; 
	}
	var accessTypes = document.getElementsByName("selectedAccessType");
	var userLevels = document.getElementsByName("selectedUserLevel");
	for(var i=0; i<userLevels.length; i++)
	{
		accessTypes[i].disabled=false;
		userLevels[i].disabled=false;
	}
	return true;
}

function validateRemoveProfile()
{
	var chks = document.getElementsByName("selectedIndex");
	var sels = 0;
	for(var i=0; i<chks.length; i++)
	{
		if(chks[i].checked)
			sels++;
	}
	if(sels==0)
	{
		alert("Select at Least one Profile to Delete");
		chks[0].focus();
		return false; 
	}
	if(confirm("Are You sure to delete ("+sels+") Profiles ?"))
		return true;
	else
		return false;
}

function removeProfile(profileId, serialNo, choice)
{
	document.getElementsByName("selectedProfileId")[0].value=profileId;
	document.getElementsByName("selectedSerialNo")[0].value=serialNo;
	document.getElementsByName("selectedIndex")[0].value=choice;
	submitFormOnValidate(true,'REMOVEPROFILE');
}

function validateForm()
{
	//alert("ddkk"+document.getElementsByName("profileGenerationType")[0].checked)
	//alert("ddkknnnn"+document.getElementsByName("profileGenerationType")[1].value)
	if(isSelected(document.getElementsByName("profileType")[1],"Profile Type") &&
		isEmpty(document.getElementsByName("profileHeader")[0],"Profile Header"))
	{
		document.getElementsByName("profileType")[0].value=document.getElementsByName("profileType")[1].value;
	}
	else
		return false;
	
	
	var dup = false;
	var proHead = document.getElementsByName("profileHeader")[0].value;
	for(var i=0; i<document.getElementsByName("proName").length; i++)
		if(document.getElementsByName("proName")[i].value == proHead)
		{
			dup = true;
			break;
		}
	
	if(dup)
	{
		alert("Profile Header already Used!");
		document.getElementsByName("profileHeader")[0].focus();
		return false;
	}
	return true;
}

function validateForm1()
{
	
	if(document.getElementsByName("profileHeader")[0].value=="")
	{
		alert("Please Enter Profile Header");
		document.getElementsByName("profileHeader")[0].focus();
		return false;		
	}

	return true;
}


function selectProfileMenu(obj,mode)
{
	if(obj.checked)
	{
		document.getElementsByName("selectedMenuId")[0].value=obj.value;
		document.getElementsByName("deskMenuName")[0].value=obj.title;
		
		document.getElementsByName("hmode")[0].value=mode;
	}
}

function submitProfileDetails(obj,obj1,obj2,obj3,mode)
{
		//alert("inside submit"+obj)
		document.getElementsByName("profileId")[0].value=obj;
		document.getElementsByName("profileHeader")[0].value=obj1;
		document.getElementsByName("serialNo")[0].value=obj2;
		document.getElementsByName("profileType")[0].value=obj3;
		document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
}

function goForProfileMenu()
{
	var radios = document.getElementsByName('selectedMenu');
	var flag=false;
	for(var i=0; i<radios.length; i++)	
		if(radios[i].checked)
		{
			flag=true;
			break;
		}
	if(!flag)
	{
		alert("Please select an Option ");
		return;
	}
	//alert("Hmode="+document.getElementsByName("hmode")[0].value);
	document.forms[0].submit();
}

function selectAllCheckboxes(obj,name)
{
	var chks = document.getElementsByName(name);
	for(var i=0;i<chks.length;i++)
		if(obj.checked)
			chks[i].checked=true;
		else
			chks[i].checked=false;
}

function viewProfile(e,url)
{
   var profileId=document.getElementsByName("profileId")[0].value;
   var serialNo=document.getElementsByName("serialNo")[0].value;
   var deskType=document.getElementsByName("deskType")[0].value;
   var patCrNo=document.getElementsByName("patCrNo")[0].value;
   var reportMode=document.getElementsByName("reportMode")[0].value;
   var episodeCode=document.getElementsByName("episodeCode")[0].value;
   var admissionNo=document.getElementsByName("admissionNo")[0].value;
   var strHiddenPatDtl=document.getElementsByName("strHiddenPatDtl")[0].value;
   //alert(strHiddenPatDtl);
   var reqDnoList=document.getElementsByName("reqDnoList")[0].value;
    //alert("reqDNo="+reqDnoList);
   //alert(""+profileId)
   //alert(""+serialNo)
   /*var url='/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWPROFILE&profileId='+profileId+'&serialNo='+serialNo+'&deskType='+deskType+'&patCrNo='+patCrNo+'&reportMode='+reportMode;*/
   var url='/HISClinical/opd/opdPatientProfile.cnt?hmode=VIEWPROFILE&profileId='+profileId+'&serialNo='+serialNo+'&deskType='+deskType+'&patCrNo='+patCrNo+'&reportMode='+reportMode+'&episodeCode='+episodeCode+'&admissionNo='+admissionNo+'&strHiddenPatDtl='+strHiddenPatDtl+'&reqDnoList='+reqDnoList;
	//alert("path"+path)
	openDependentPopup(createFHashAjaxQuery(url),e,700,700,true);
}

function generateProfile(e,url)
{
	var valid=false;
	var count =0;
	var selectedIndex=document.getElementsByName("selectedIndex");
	var i=0;
	var j=0;
	for(;i<selectedIndex.length;i++)
	{
		if(selectedIndex[i].checked)
		{
			valid=true;
			count=count+1;
			j=i;
		}
	}
	//alert("j"+j)
	if(valid==true && count==1)
	{
		if(document.getElementsByName("proStatus")[j].value == 2) // Generated
		{
			alert("Profile already Generated!");
			return;
		}
		
		var selectedIndex =document.getElementsByName("selectedIndex")[j].value;
		var deskId=document.getElementsByName("deskId")[0].value;
		var deskType=document.getElementsByName("deskType")[0].value;
		var departmentUnitCode=document.getElementsByName("departmentUnitCode")[0].value;
		var wardCode=document.getElementsByName("wardCode")[0].value;
		var patCategoryCode=document.getElementsByName("patCategoryCode")[0].value;
		var patCrNo=document.getElementsByName("patCrNo")[0].value;
		var episodeCode=document.getElementsByName("episodeCode")[0].value;
		var admissionNo=document.getElementsByName("admissionNo")[0].value;
		url=url+'&selectedIndex='+selectedIndex+'&deskId='+deskId+'&departmentUnitCode='+departmentUnitCode+'&patCategoryCode='+patCategoryCode+'&deskType='+deskType+'&patCrNo='+patCrNo+'&episodeCode='+episodeCode+'&admissionNo='+admissionNo+'&wardCode='+wardCode;
		//   alert("url "+url)
		openDependentPopup(createFHashAjaxQuery(url),e,700,700,true);
	}
	else if(valid==true && count>1)
	{
		alert("Please Select Single Profile")
	}
	else
	{
		alert("Please Select Profile");
	} 
}


function setUserLevel(index)
{
	if(index==-1)
	{
		var elemAccessType = document.getElementsByName('accessType')[0]; 
		var elemUserLevel = document.getElementsByName('userLevel')[0];
	}
	else
	{
		var elemAccessType = document.getElementsByName('selectedAccessType')[index]; 
		var elemUserLevel = document.getElementsByName('selectedUserLevel')[index];
	}
	
	if(elemAccessType.value=="3")
	{
		elemUserLevel.value=0;
		elemUserLevel.disabled = true;
	}
	else
	{
		if(elemUserLevel.disabled)
		{
			elemUserLevel.value="-1";
		}
		elemUserLevel.disabled = false;
	}
}

function generateAutomaticProfile(e,url)
{
	openDependentPopup(createFHashAjaxQuery(url),e,700,700,true);
}