//////////////  Image Editor Applet Related JavaScript Functions

function setAplletsize(h,w)
{
	var app=document.getElementsByName("imageApplet")[0];
	app.height=h;
	app.width=w;
} 

function saveImageinJS()
{
	//alert(" Inside   saveImageinJS() .....");
	document.getElementsByName("imageApplet")[0].saveImage();
	document.getElementById("imageApplet").saveImage();
	document.applets[0].saveImage();
}
