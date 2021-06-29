<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="registration.RegistrationConfig"%>

<%@page import="mrd.transaction.controller.fb.EmrCommonDeskFB"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<!-- Code for browser detection.              -->
<script src="/HISClinical/mrd/js/ua.js"></script>

<!-- Infrastructure code for the TreeView.   -->
<script src="/HISClinical/mrd/js/ftiens4.js"></script>

<!-- Scripts that define the tree.            -->
<!--  <script src="/AHIMS/mrd/js/demoFuncsNodes.js"></script>-->
 
<script type="text/javascript"> 
USETEXTLINKS = 1  //replace 0 with 1 for hyperlinks

// Decide if the tree is to start all open or just showing the root folders
STARTALLOPEN = 0 //replace 0 with 1 to show the whole tree

HIGHLIGHT = 1

hosNode=''; // hospital Display logic ; for showing hospital Name
opdNode='';
ipdNode='';
unitNode='';
episodeNode='';
visitNode='';
 <%EmrCommonDeskFB fb=(EmrCommonDeskFB)pageContext.findAttribute("EmrCommonDeskFB");%>
  //create the root node
  foldersTree = gFld("All", "javascript:parent.setSelected(\"\",\"All\")")
  foldersTree.treeID = "Funcs"
  //foldersTree.xID="root"
 
   <%String[] hosName=fb.getHospitalName();
   String[] hosCode=fb.getHospitalCode();
   
   System.out.println("hosName.length ="+hosName.length);
   int len=hosCode.length;
   int i=0;
   for(i=0;i<len;i++)
   {
	 
   %>
 
 
 //create the opd node under the root node 
  hosNode = insFld(foldersTree, gFld("<%=hosName[i]%>", "javascript:parent.setSelected(\"<%=hosCode[i]%>\",\"H\")"))
 
  //create the opd node under the root node 
 // opdNode = insFld(foldersTree, gFld("Opd Wise", "javascript:parent.setSelected(\"\",\"\")"))
    
  //create child nodes of the opd root node      
  createNodeOpd(hosNode,<%=hosCode[i]%>);//opdNode); 
 
  //create the Ipd node and child nodes of the ipd node
  createIpdNode(hosNode,<%=hosCode[i]%>);
  
  <%}%>
	  
  // ********* create node encouunter wise under root node	
  //encounterNode = insFld(foldersTree, gFld("Encounter Wise", "javascript:parent.op()"))  ----currently not in use
  // ******** create child nodes of the episode wise node
 // createEpisodeNode(encounterNode)  ----currently not in use
 

/*
	create ipd node and its childs
*/
function createIpdNode(hosNode, hosCode){
	 //alert("Hi");
 	var treeNodeAdmission='<%=fb.getAddmissionTreeNode()%>';
 	 //alert("treeNodeAdmission::::"+treeNodeAdmission);
	var nodeArray=treeNodeAdmission.split('#');
   // alert(nodeArray);
	if(treeNodeAdmission!=""){
		  //alert("in createipdnode treeNodeAdmission");
 		ipdNode = insFld(hosNode, gFld("Ipd Wise", "javascript:parent.setSelected(\""+hosCode+"\",\"I\")"));
      	for(i=0;i<nodeArray.length;i++) 
		{
			var hospitalCode=nodeArray[i].split("$")[3];
			 //alert("hospitalCode:::"+hospitalCode);
			if(hosCode==hospitalCode)
			{ 
				obj1=insDoc(ipdNode, gLnk("S", "AdmissionNo::"+nodeArray[i].split("$")[0]+" ("+ 
				nodeArray[i].split("$")[1]+ ")" , "javascript:parent.setSelected(\\\'" +hosCode+"#"+nodeArray[i].split("$")[0]+"\\\',\\\'A\\\')"));
			}	
		}
	}
}


//bulid the tree on load 
function loadSynchPage(xID) 
{
  var folderObj;
  var rootObj = findObj("0")
  //select the root node on load	 
  highlightObjLink(rootObj)
 
} 

/*
	create the childeren of the opd node
*/
function createNodeOpd(hosNode,hosCode)
{
 
 
 	 //alert("in createNodeOpd");
	//var treeNode=document.getElementsByName("treeNode")[0].value;
	var treeNode='<%=fb.getTreeNode()%>';
	var episodeClosed=<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>;
	var episodeTypeCodeMLC=2//<%=RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC%>;
	var nodeArray=treeNode.split('^');
	var unit='';
	var episode='';
	var visit='';
	var treeStruct='';
	var treeStruct='';
	var unitNode='';
	var episodeNode='';
	
	
    // alert("fdfdfdf unit root "+unitRoot)
    //var unitCodeArray=(document.getElementsByName("unitCodeArray")[0].value).split('^');
    var unitCodeArray='<%=fb.getUnitCodeArray()%>'.split('^');
    
    //create the opd node under the root node 
  	opdNode = insFld(hosNode, gFld("Opd Wise", "javascript:parent.setSelected(\""+hosCode+"\",\"O\")"));
    for(i=0;i<nodeArray.length;i++)
	{
		var singleNodeArray=nodeArray[i].split('#');
		var unitTemp=singleNodeArray[0];
		var episodeTemp=singleNodeArray[1];
		var episodeIsOpen=singleNodeArray[4];
		var episodeTypeCode=singleNodeArray[5];
		var episodeColor="#B46767";
		var hospitalCode=singleNodeArray[6];
		var mlcData="";
		var episodeIsOpenStatus="";
		
	if(hosCode==hospitalCode)
		{
		if(episodeIsOpen==episodeClosed)
		{
			episodeIsOpenStatus=" <font style='color: #9900CC;' >(Closed)</font> ";
			//episodeIsOpenStatus=" (Closed)";
		}
		if(episodeTypeCode==episodeTypeCodeMLC)
		{
			mlcData=" <font style='color: #FF0000;' >(MLC)</font> ";
			//mlcData=" (MLC) ";
		}
		var index="0";
		//if the unit is not same as previous then add the unit as new node
		
			if(unit!=unitTemp)
			{
				//create the unit node
				//unitNode=insDoc(opdNode, gLnk("S", unitTemp, ""))
				//hospitalCode="#"+hospitalCode;
				var strHospitalUnitCode=hospitalCode+"#"+unitCodeArray[i];
				//var abc=strHospitalUnitCode.toString();
				//alert(abc);
				var strCode="javascript:parent.setSelected(\""+strHospitalUnitCode+"\",\"U\")";
				unitNode=insFld(opdNode, gFld(unitTemp,strCode ))
				var tempEpisodeSeq=singleNodeArray[1].substring(5,8);
	      		var tempEpisodeSeqInt=parseInt(tempEpisodeSeq);
	      		// alert("episeq "+tempEpisodeSeqInt);
	      		
	      		//create the episode node under the unit node
	      		episodeNode=insFld(unitNode, gFld("Episode::" + tempEpisodeSeqInt+mlcData+episodeIsOpenStatus , "javascript:parent.setSelected(\""+hospitalCode+"#"+singleNodeArray[1]+"#"+unitCodeArray[i]+"\",\"E\")"));
	      		
	      		var visitValue=hospitalCode+"#"+unitCodeArray[i]+'#'+singleNodeArray[1]+'#'+singleNodeArray[2]; 
	      		
	      		//create the visit node under the episode node
	      		visitNode=insDoc(episodeNode, gLnk("S", "Enc::"+ singleNodeArray[2]+" ("+singleNodeArray[3]+")", "javascript:parent.setSelected(\\\'" + visitValue +"\\\',\\\'V\\\')"));	      		
	      		unit=unitTemp;
	      		episode=singleNodeArray[1];
	      		visit=singleNodeArray[2];
			}
		
		//if the unit is same as the previous unit 
		//then add the episode node under the previous unit node 
		else
		{
			var episodeTemp=singleNodeArray[1];
			var tempEpisodeSeq=singleNodeArray[1].substring(5,8);
      		var tempEpisodeSeqInt=parseInt(tempEpisodeSeq);
			//if episode is not same as the previous episode then create a new episode node
			if(episode!=episodeTemp)
			{
				episodeNode=insFld(unitNode, gFld("Episode::" + tempEpisodeSeqInt+mlcData+episodeIsOpenStatus , "javascript:parent.setSelected(\""+hospitalCode+"#"+singleNodeArray[1]+"\",\"E\")"));
				
				episode=episodeTemp;
				var visitValue=hospitalCode+"#"+unitCodeArray[i]+'#'+singleNodeArray[1]+'#'+singleNodeArray[2];
				// alert("visitValur "+visitValue)				
	      		visitNode=insDoc(episodeNode, gLnk("S", "Enc::"+ singleNodeArray[2]+" ("+singleNodeArray[3]+")", "javascript:parent.setSelected(\\\'" + visitValue +"\\\',\\\'V\\\')"));	      		
				visit=singleNodeArray[2];
			}
			//if episode is same as the previous episode then
			// create the visit node and it under the previous episode node
			else
			{
				var visitValue=hospitalCode+"#"+unitCodeArray[i]+'#'+singleNodeArray[1]+'#'+singleNodeArray[2]; 	
				var visitTemp=singleNodeArray[2];
				if(visit!=visitTemp)
				{
					visitNode=insDoc(episodeNode, gLnk("S", "Enc::"+ visitTemp + " (" + singleNodeArray[3] + ")", "javascript:parent.setSelected(\\\'"+visitValue+"\\\',\\\'V\\\')"));
					visit=visitTemp;
				}
			}
	
		}
   }	
 }
	 
}

/*
	add the childeren node of the episode wise noe  
*/
function createEpisodeNode(encounterNode)
{
	//var treeNode=document.getElementsByName("treeNode")[0].value;
	var treeNode='<%=fb.getTreeNode()%>';
	var episodeClosed=<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>;
	var episodeTypeCodeMLC=<%=RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC%>;
	// alert("node "+document.getElementsByName("treeNode")[0].value)
	var episodeNode=''
	var nodeArray=treeNode.split('^');
	var unit='';
	var episode='';
	var visit='';
	var treeStruct='';
	var treeStruct='';
	var unitNode='';
	var episodeNode='';
	
    var unitCodeArray='<%=fb.getUnitCodeArray()%>'.split('^');
    for(i=0;i<nodeArray.length;i++)
	{
		var singleNodeArray=nodeArray[i].split('#');
		var unitTemp=singleNodeArray[0];
		var episodeTemp=singleNodeArray[1];
		var episodeIsOpen=singleNodeArray[4];
		var episodeTypeCode=singleNodeArray[5];
		var episodeColor="#000000";//"#B46767";
		var mlcData="";
		var episodeIsOpenStatus="";
		if(episodeIsOpen==episodeClosed)
		{
			episodeIsOpenStatus=" <font style='color: #9900CC;' >(Closed)</font> ";
			//episodeIsOpenStatus=" (Closed)";
		}
		if(episodeTypeCode==episodeTypeCodeMLC)
		{
			mlcData=" <font style='color: #FF0000;' >(MLC)</font> ";
			//mlcData=" (MLC) ";
		}
	
		if(episode!=episodeTemp)
		{
			var episodeTemp=singleNodeArray[1];
			var tempEpisodeSeq=singleNodeArray[1].substring(5,8);
      		var tempEpisodeSeqInt=parseInt(tempEpisodeSeq);
			if(episode!=episodeTemp)
			{
				episodeNode=insFld(encounterNode, gFld("Episode::" + tempEpisodeSeqInt+episodeIsOpenStatus , "javascript:parent.setSelected(" + singleNodeArray[1] + ",\"E\")"));
				
				episode=episodeTemp;
				var visitValue=unitCodeArray[i]+'$'+singleNodeArray[1]+'$'+singleNodeArray[2]; 	
				// alert("visitValur "+visitValue)				
				visitNode=insDoc(episodeNode, gLnk("S", "Enc::"+ singleNodeArray[2]+" ("+singleNodeArray[3]+")"+mlcData, "javascript:parent.setSelected(\\\'"+visitValue+"\\\',\\\'V\\\')"))
				
				visit=singleNodeArray[2];
			}
			else
			{
				var visitValue=unitCodeArray[i]+'$'+singleNodeArray[1]+'$'+singleNodeArray[2]; 	
				// alert("visitValur "+visitValue)
				var visitTemp=singleNodeArray[2];
				if(visit!=visitTemp)
				{
					visitNode=insDoc(episodeNode, gLnk("S", "Enc::"+ visitTemp + " (" + singleNodeArray[3] + ")"+mlcData, "javascript:parent.setSelected(\\\'"+visitValue+"\\\',\\\'V\\\')"))
					visit=visitTemp;
				}
			}
				
		}
			
	}
	 
}
  
/*
	change the frame size and hide the tree menu
*/  
function changeFrameSize(mode){
	var frameset=parent.document.getElementById("emrDetailFS")
	if(mode==1){
		document.getElementById("imgLeftId").style.display="none"
		document.getElementById("imgRightId").style.display="block"
		document.getElementById("treeDivId").style.display="none"
		document.getElementById("treeNodeId").width="0%"
		frameset.cols = "13,*";
	}
	else{
		document.getElementById("imgLeftId").style.display="block"
		document.getElementById("imgRightId").style.display="none"
		frameset.cols = "18%,*";
		document.getElementById("treeDivId").style.display="block"	
		document.getElementById("treeNodeId").width="100%"
	}

} 
</script> 

 <style>
   BODY {
     background-color: #E0EBEB;
	
	}
   TD {
     font-size: 11px; 
     font-family: verdana,helvetica;
     text-decoration: none;
     white-space: nowrap;
	color:#000000;
	}
   A {
     text-decoration: none;
     color:#000000;
	font-weight: bold;}
  </style>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


</head>
<body onload="">
<html:form action="/emrDesk">
<his:ContentTag>
<div id="imgLeftId" >
<table width="100%">
<tr height="100%"  >
<td width="100%" height="100%" style="background-color:#E0EBEB;" id="treeNodeId"> 
 <div style="position:absolute; top:0; left:0; " id="treeDivId"><table border="0">
<tr><td><font size="-2"><a style="font-size:7pt;text-decoration:none;color:silver;" 
href="http://www.treemenu.net/" target="_blank"></a>
</font></td></tr></table></div>

    <!--Build the browser's objects and display default view  
    of the tree.      -->                                    
   <SCRIPT>initializeDocument()
   	loadSynchPage("Funcs")
   	</SCRIPT>
  <NOSCRIPT>
    A tree for site navigation will open here if you enable JavaScript in your browser.
  </NOSCRIPT>
</td>
<!--<td valign="top" class="tdfonthead">
	<div align="left">
	<img class="button" src='<his:path src="/hisglobal/images/trileft.gif"/>' onclick="changeFrameSize(1)" title="Hide Menu"/>
	</div>
</td>

-->
</tr> 
</table>
</div>
<!--
<div id="imgRightId" style="display: none">
<table width="100%">
<tr style="height: 200px">
<td bgcolor="#FFB468" valign="top">
<td class="tdfonthead" valign="top">
	<div  align="left">
	<img class="button" src='<his:path src="/hisglobal/images/tri.gif"/>' onclick="changeFrameSize(2)" title="Show Menu"/>
	</div>
</td>
</tr> 
</table>
</div>
-->
</his:ContentTag>

<html:hidden name="EmrCommonDeskFB" property="treeNode"/>
<html:hidden name="EmrCommonDeskFB" property="episodeVisitNo"/>
<html:hidden name="EmrCommonDeskFB" property="unitCodeArray"/>
<html:hidden name="EmrCommonDeskFB" property="selectedVisit" />
<html:hidden name="EmrCommonDeskFB" property="selectionCriteria" />
<html:hidden name="EmrCommonDeskFB" property="selectedUnit" />
<html:hidden name="EmrCommonDeskFB" property="episodeCode" />
<html:hidden name="EmrCommonDeskFB" property="addmissionTreeNode" />
<html:hidden name="EmrCommonDeskFB" property="selectedPatAdmNo" />
<html:hidden name="EmrCommonDeskFB" property="hosCode" />

</html:form>
</body>