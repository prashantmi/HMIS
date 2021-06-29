function add(mode)
{
      if(checkforAdd())
      {
      document.forms[0].hmode.value=mode;
      document.forms[0].submit();
      }
      
}

function checkforAdd()
{
	
	var cmb1				=	document.getElementsByName("cmb");
	var combo				=	document.forms[0].combo;	
	var no_of_combo			=	0;
	var retval				=  true;

	if(typeof(document.forms[0].no_of_combo) !="undefined") no_of_combo=document.forms[0].no_of_combo.value;

	
	if(cmb1.length > 0 && typeof(combo) == "undefined")
		{
			//when the list page is loaded then no_of_combo will be null
			if(no_of_combo == null || no_of_combo == 0 || no_of_combo == 'null') 
			{
				no_of_combo = cmb1.length;
			}
			
			if(no_of_combo > 0) 
			{
				for(var i=0;i<cmb1.length;i++)
					{
						if(combo[i].value=="0")
						{
							alert("Please Select "+combo[i].title+" ...!");
							combo[i].focus();
							retval=false;
						}
						if(!retval)
							break;
					}
			}
			
		}
		else
		{
			if(typeof(combo) != "undefined" && no_of_combo > 0)
			{
				if(no_of_combo > 1)
				{
					for(var i=0;i<combo.length;i++)
					{
						if(combo[i].value=="0")
						{
							alert("Please Select "+combo[i].title+" ...!");
							combo[i].focus();
							retval=false;
						}
						
						if(!retval)
							break;
					}
					
				}
			}
		}
	
	return retval;
	
}