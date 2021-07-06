function disableButton(_buttonID){
	try{
		document.getElementById(_buttonID+"enable").style.display="none";
		document.getElementById(_buttonID+"disable").style.display="block";
	}catch(_err){
		_buttonID="";
	}
}

function enableButton(_buttonID){
	try{
		document.getElementById(_buttonID+"enable").style.display="block";
		document.getElementById(_buttonID+"disable").style.display="none";
	}catch(_err){
		_buttonID="";
	}
}

function chkUserFunc(these)
{
	try
	{
		chkUserDefinedFunc(these);
	}
	catch(_err)
	{
	}
}
