 ////Display The Values When Next  is Clicked///
 
 function showNext()
 {
  document.getElementById('block'+(document.forms[0].currentblock.value)).style.display='none';
  document.forms[0].currentblock.value++;
  document.getElementById('block'+(document.forms[0].currentblock.value)).style.display='block';
  var page=(parseInt(document.forms[0].currentblock.value)-1)*parseInt(document.forms[0].pagesPerBlock.value)+1;
 
  document.getElementById('b'+(page)).style.display='block';
  document.getElementsByName('a'+(page))[0].style.color='red';
  document.getElementById('b'+document.forms[0].currentPageNo.value).style.display='none';
  document.getElementsByName('a'+document.forms[0].currentPageNo.value)[0].style.color='blue';
  document.forms[0].currentPageNo.value=page;
 }

////Display The Values When Previous  is Clicked///

function showPrevious()
 {
  document.getElementById('block'+(document.forms[0].currentblock.value)).style.display='none';
  document.forms[0].currentblock.value--;
  document.getElementById('block'+(document.forms[0].currentblock.value)).style.display='block';
  var page=(parseInt(document.forms[0].currentblock.value)-1)*parseInt(document.forms[0].pagesPerBlock.value)+1;
  document.getElementById('b'+(page)).style.display='block';
  document.getElementsByName('a'+(page))[0].style.color='red';
  document.getElementById('b'+document.forms[0].currentPageNo.value).style.display='none';
  document.getElementsByName('a'+document.forms[0].currentPageNo.value)[0].style.color='blue';
  document.forms[0].currentPageNo.value=page;
 }

////Display The Values When Page number is Clicked///

function showPage(page,str)
{alert(document.forms[0].currentblock.value)
alert(document.forms[0].currentPageNo.value)

  if(document.forms[0].currentPageNo.value!=page)
   {
    document.getElementById('b'+(page)).style.display='block';
    document.getElementsByName('a'+(page))[0].style.color='red';
    document.getElementById('b'+document.forms[0].currentPageNo.value).style.display='none';
    document.getElementsByName('a'+document.forms[0].currentPageNo.value)[0].style.color='blue';
   }
  document.forms[0].currentPageNo.value=page;
}
	

