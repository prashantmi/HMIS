//
// Copyright (c) 2006 by Conor O'Mahony.
// For enquiries, please email GubuSoft@GubuSoft.com.

// Decide if the names are links or just the icons
USETEXTLINKS = 1  //replace 0 with 1 for hyperlinks

// Decide if the tree is to start all open or just showing the root folders
STARTALLOPEN = 0 //replace 0 with 1 to show the whole tree

HIGHLIGHT = 1
opdNode='';
ipdNode='';

foldersTree = gFld("All", "javascript:setSelected()")
  foldersTree.treeID = "Funcs"
  //foldersTree.xID="root"
  opdNode = insFld(foldersTree, gFld("Opd Wise", "javascript:setSelected()"))
  //opdNode.xID="opdRoot"
      opdNode1=insDoc(opdNode, gLnk("S", "Ent", ""))
      opdNode2=insDoc(opdNode, gLnk("S", "Medicine", ""))
      opdNode3=insDoc(opdNode, gLnk("R", "Ent and Eye", ""))
      //opdNode1.xID="opdNode1"
      //opdNode2.xID="opdNode2"
      //opdNode3.xID="opdNode3"
      
  ipdNode = insFld(foldersTree, gFld("Ipd Wise", "javascript:parent.setSelected()"))
  //opdNode.xID="ipdRoot"
	  ipdNode1=insDoc(ipdNode, gLnk("R", "200923233", ""))
	  ipdNode2=insDoc(ipdNode, gLnk("R", "200923565", ""))
	  //ipdNode1.xID="ipdNode1"
      //ipdNode2.xID="ipdNode2"
      

  // If your tree instead of the regular http links has "javascript:function(arg)"
  // links, and the type of the argument is string, special care is needed regarding
  // the quotes and double quotes. Please use exactly the same kind of 
  // quotes or double quotes used in this example (they change from folder to document).
  // Use the exact same number of backslashes for escaping the (double)quote 
  // characters, and pay attention not only to the (double)quotes surrouding the 
  // strings, but also to any (double)quote characters inside of that string

  // If you are going to use a frameless layout, you will need to move the functions 
  // exampleFunction and windowWithoutToolbar to the main page and change
  // parent.functionname to window.functionname in this file

  //Note the "S" target in the first argument of gLnk
  encounterNode = insFld(foldersTree, gFld("Encounter Wise", "javascript:parent.op()"))
      //Use \" to delimit your string arguments and \\\" inside 
	 // insDoc(aux1, gLnk("S", "Window w/o bars", "javascript:parent.windowWithoutToolbar(\\\'http://www.treeview.net/treemenu/demopics/beenthere_tuscany.gif\\\', 410, 415)"))
      //Use \\\' to delimit your string arguments and \\\\\\' inside
	  //Double quote characters (") are not allowed in the string argument
	 // insDoc(aux1, gLnk("S", "A <i>js:</i> doc", "javascript:parent.exampleFunction(\\\'Special care with: &quot; and \\\\\\'\\\')"))
	  //Single quote characters (') are not allowed in the string argument but you can replace them with &#39;
	 // aux2 = insFld(aux1, gFld("A <i>js:</i> folder", "javascript:parent.exampleFunction(\"Take special care with: \\\" and &#39;\")"))
createIpdNode();

function createIpdNode(){
	//alert("tree "+document.getElementsByName("addmissionTreeNode")[0].value)
    //ipdNode = findObj("ipdRoot")
    alert(ipdNode)
 	var treeNodeAdmission=document.getElementsByName("addmissionTreeNode")[0].value;
	var nodeArray=treeNodeAdmission.split('#');
    // alert("klk "+episodeRoot)
    //array=new Array()
    
  	for(i=0;i<nodeArray.length;i++)
	{
		obj1=insDoc(ipdNode, gLnk("S", "AdmissionNo::"+nodeArray[i].split("@")[0]+" ("+ nodeArray[i].split("@")[1]+ ")", ""))
		//alert("node created")
		//array[i]="AdmissionNo::"+nodeArray[i].split("@")[0]+" ("+ nodeArray[i].split("@")[1]+ ")";
		//var nodeArrayAdmissionNo=nodeArray[i].split("@");
		
		//var admissionNode = dojo.widget.createWidget("TreeNode",{ 
        //				title:"<a herf='' onclick='show(\""+nodeArray[i].split("@")[0]+"\",\"I\");'  ><b><font style='color: #B46767;' >  AdmissionNo::"+nodeArray[i].split("@")[0]+" ("+ nodeArray[i].split("@")[1]+ ") </font> </b></a> "})
		//treeNodeIpd.addChild(admissionNode); 
      //	treeNodeIpd.registerChild(admissionNode,i);
      ipdNode.addChild(obj1) 
	}
	
	
}






/*
// Decide if the names are links or just the icons
USETEXTLINKS = 1  //replace 0 with 1 for hyperlinks

// Decide if the tree is to start all open or just showing the root folders
STARTALLOPEN = 0 //replace 0 with 1 to show the whole tree

HIGHLIGHT = 1

 foldersTree = gFld("All", "javascript:parent.op()")
 foldersTree.treeID = "Funcs"  
 opdRoot = insFld(foldersTree, gFld("Opd Wise", "javascript:parent.op()"))
 ipdRoot = insFld(foldersTree, gFld("Ipd Wise", "javascript:parent.op()"))
 //insDoc(ipdRoot, gLnk("R", "AdmissionNo", "javascript:alert('a')"))
emrDoc='';
deskFrameEMRDetails=parent.document.getElementById("treeNodeFrameId");
if(window.XMLHttpRequest)
	emrDoc=deskFrameEMRDetails.contentDocument;
else if (window.ActiveXObject)
	emrDoc=deskFrameEMRDetails.Document;
//alert("emrDoc "+emrDoc)	
//emrFrm=emrDoc.forms[0];
//alert("emrFrm "+emrFrm)

nodeArray=new Array("Ent","Medicine","Surgery","Ent and Eye"); 
for(i=0;i<nodeArray.length;i++)
{
	//var nodeArrayAdmissionNo=nodeArray[i].split("@");
	//alert("node creation")
	var nodeName="AdmissionNo::"+ +nodeArray[i].split("@")[0]+" ("+ nodeArray[i].split("@")[1];
	createIpdNode(nodeName,"")
	
}
		
 function createIpdNode(nodeName,value){
 	insDoc(ipdRoot, gLnk("R", nodeName, ""))
	
 } 
 
function createOpdNode(nodeName,value)
{
	insDoc(opdRoot, gLnk("R", nodeName, ""))
	 
}
*/