
	
	
	
	function closeWindow()
	{
		var count=0;
		var len = document.form1.chk.length;
		if(isNaN(len))
		{
			if(document.form1.chk.checked==true)
				count++;	
		}
		else
		{
			for( var i=0; i<document.form1.chk.length; i++)
			{
				if(document.form1.chk[i].checked==true)
					count++;
			}
		}
		if(count==0)
		{
			alert("Select atleast one value");
		} 
		else if(count>1)
		{
			alert("select only one value");
		}
		else if(count==1)
		{
			if(isNaN(len))
			{
				var values=document.form1.chk.value;
				var idLength=values.length;
				//var index=values.indexOf("^");
				//1st line commented by amit
				//opener.document.form1.empNo.value = values.substring(0,idLength);
				//opener.document.form1.desigCode.value=value.substrinfg(index+1,values.lastIndexOf("^"));
				//opener.document.form1.ufullName.value =values.substring(values.lastIndexOf("^")+1,values.length);
				window.close();
			}
			else
			{
				
				for(var i=0;i<document.form1.chk.length;i++)
				{
					if(document.form1.chk[i].checked==true)
					{
						var values=document.form1.chk[i].value;
						var idLength=values.length;
						//var index=values.indexOf("^");
						//1st line commented by amit
						//opener.document.form1.empNo.value = values.substring(0,idLength);
						//opener.document.form1.ufullName.value=values.substring(index+1,values.lastIndexOf("^"));
						//opener.document.form1.desigCode.value =values.substring(values.lastIndexOf("^")+1,values.length);
						window.close();
					}
				}
			}
		}
	}
	
		
