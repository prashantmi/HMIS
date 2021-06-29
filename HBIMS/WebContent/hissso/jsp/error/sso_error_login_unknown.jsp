<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">



function refreshPage()
{
	window.parent.tabRefresh();
	/*if(typeof(Storage) !== "undefined") 
	{
		//alert("hh"+sessionStorage.getItem("ui_flag"));
		if (sessionStorage.getItem("ui_flag") === null) // Check if the key is set. It's a self defined key.
		{
			//alert("Hi2");
		    sessionStorage.setItem("ui_flag", 1); // If not set, set it
		    window.parent.tabRefresh();
		}
		else
		{
			//alert("Hi3");
			 //No Action. It's not the first time.
		}
	}
    else 
    {
		alert("Sorry, your browser does not support web storage...");
    }*/

    
}
</script>
</head>
<body onload='refreshPage();'>
<center><div style='display: none;'>Unknown Error Occured.Kindly refresh your page.</div></center>
</body>
</html>