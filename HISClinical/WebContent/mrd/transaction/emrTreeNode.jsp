<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="registration.RegistrationConfig"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/dojotoolkit/dojo.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript" src="hisglobal/js/dojotoolkit/dojo.js"> 
    /* Load Dojo engine */ 
</script> 
 
<script type="text/javascript">
function show(obj,id)
{

	// alert("dsdddddddddddddddddddddddddd "+(obj.split('$'))[0]);
	if(id=='V')
	{
		document.getElementsByName("selectedVisit")[0].value=obj;
	}
	
	if(id=='U')
	{
		document.getElementsByName("selectedUnit")[0].value=obj;
	}
	
	if(id=='E')
	{
		document.getElementsByName("episodeCode")[0].value=obj;
	}
	
	if(id=='I')
	{
	
		document.getElementsByName("selectedPatAdmNo")[0].value=obj;
		// alert("selectedPatAdmNo "+document.getElementsByName("selectedPatAdmNo")[0].value);
	}
	
	
	
	
// 	alert("selectedUnit "+document.getElementsByName("episodeCode")[0].value)
	// alert("selectionCriteria "+id)
	document.getElementsByName("selectionCriteria")[0].value=id;
	
} 
</script>

<script type="text/javascript"> 
    
    dojo.require("dojo.widget.Tree"); 
</script> 



<script type="text/javascript"> 


dojo.addOnLoad(function(){ 

   buildTree();
   selectRootNode();
  }); 
 function selectRootNode ()
 	 {
 	//  alert("rootNode "+rootNode)
 	dojo.html.addClass(rootNode.titleNode, "dojoTreeNodeSelected");

//	var e=window.event; 
//	dojo.event.topic.publish(rootNode.tree.eventNames.treeClick, {source:rootNode, event:e});
	show('','A');
	}
	
	function unSelectRootNode ()
 	 {
 	//  alert("rootNode "+rootNode)
	// 	dojo.html.addClass(rootNode.titleNode, "dojoTreeNodeLabelSelected");
	dojo.html.removeClass(rootNode.titleNode, "dojoTreeNodeSelected");
	 show('','');
	}
	
 
</script> 

 
<script type="text/javascript"> 
var rootNode;
function buildTree()
{
	myTreeWidget = dojo.widget.createWidget("Tree",{ 
    widgetId:"myNewTreeWidget" });
      
     	var visitValue='45';
   var all = dojo.widget.createWidget("TreeNode",{ 
   					title:"<a herf='' onclick='show(\""+visitValue+"\",\"A\");'  ><b><font style='color: #B46767;' >  ALL </font></b> </a> "})
       	myTreeWidget.addChild(all); 
   		myTreeWidget.registerChild(all,2);  
      rootNode=all;
	 var unitRoot = dojo.widget.createWidget("TreeNode",{ 
        				title:"<a herf='' onclick='unSelectRootNode();'  ><b><font style='color: #B46767;' >  Opd Wise </font></b> </a> "})
        	myTreeWidget.addChild(unitRoot); 
      		myTreeWidget.registerChild(unitRoot,0); 
      		
      unitRoot=makeNode(unitRoot);
      		
     
   var ipdTreeNodeArray=document.getElementsByName("addmissionTreeNode")[0].value;
   if(ipdTreeNodeArray!="")
   {
   var ipdRoot = dojo.widget.createWidget("TreeNode",{ 
       				title:"<a herf='' onclick='unSelectRootNode();'  ><b><font style='color: #B46767;' >  Ipd Wise </font></b> </a> "})
       	myTreeWidget.addChild(ipdRoot); 
   		myTreeWidget.registerChild(ipdRoot,1); 
   		
   		ipdRoot=makeNodeIpdWise(ipdRoot);
   	}
   	
   	 var episodeRoot = dojo.widget.createWidget("TreeNode",{ 
       				title:"<a herf='' onclick='unSelectRootNode();'  ><b><font style='color: #B46767;' >  Encounter Wise </font></b> </a> "})
       	myTreeWidget.addChild(episodeRoot); 
   		myTreeWidget.registerChild(episodeRoot,1); 
   		
   		episodeRoot=makeNodeEpisodeWise(episodeRoot);
   	
	var treeContainer = document.getElementById("myWidgetContainer"); 
    var placeHolder = document.getElementById("treePlaceHolder"); 
    treeContainer.replaceChild(myTreeWidget.domNode,placeHolder); 
}


 
var visitNo='';
function makeNode(unitRoot)
{
	var treeNode=document.getElementsByName("treeNode")[0].value;
	var episodeClosed=<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>;
	var episodeTypeCodeMLC=<%=RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC%>;
	// alert("node "+document.getElementsByName("treeNode")[0].value)
	var nodeArray=treeNode.split('^');
	var unit='';
	var episode='';
	var visit='';
	var treeStruct='';
	var treeStruct='';
	var unitNode='';
	var episodeNode='';
	
    // alert("fdfdfdf unit root "+unitRoot)
      var unitCodeArray=(document.getElementsByName("unitCodeArray")[0].value).split('^');
	for(i=0;i<nodeArray.length;i++)
	{
		var singleNodeArray=nodeArray[i].split('#');
		var unitTemp=singleNodeArray[0];
		var episodeTemp=singleNodeArray[1];
		var episodeIsOpen=singleNodeArray[4];
		var episodeTypeCode=singleNodeArray[5];
		var episodeColor="#B46767";
		var mlcData="";
		var episodeIsOpenStatus="";
		if(episodeIsOpen==episodeClosed)
		{
			episodeIsOpenStatus=" <font style='color: #9900CC;' >(Closed)</font> ";
		}
		if(episodeTypeCode==episodeTypeCodeMLC)
		{
			mlcData=" <font style='color: #FF0000;' >(MLC)</font> ";
		}
		var index="0";
		if(unit!=unitTemp)
		{
		
			unitNode = dojo.widget.createWidget("TreeNode",{ 
        				title:"<a herf='' onclick='show(\""+unitCodeArray[i]+"\",\"U\");'  ><b><font style='color: #B46767;' >"+unitTemp+"</font></b> </a> "})
        	unitRoot.addChild(unitNode); 
      		unitRoot.registerChild(unitNode,i); 
      		
      		var tempEpisodeSeq=singleNodeArray[1].substring(5,8);
      		var tempEpisodeSeqInt=parseInt(tempEpisodeSeq);
      		// alert("episeq "+tempEpisodeSeqInt);
      		
    		episodeNode=dojo.widget.createWidget("TreeNode",{ 
        				title:"<a herf='' onclick='show(\""+singleNodeArray[1]+"\",\"E\");'  ><b><font style='color: "+episodeColor+";' >  Episode::"+tempEpisodeSeqInt+"</b></font>"+mlcData+episodeIsOpenStatus+" </a> "
        				})
        	unitNode.addChild(episodeNode); 
      		unitNode.registerChild(episodeNode,i);
      		var visitValue=unitCodeArray[i]+'$'+singleNodeArray[1]+'$'+singleNodeArray[2];
      		// alert("visitValur "+visitValue) 
			visitNode=dojo.widget.createWidget("TreeNode",{ 
			
        				title:"<a herf='' onclick='show(\""+visitValue+"\",\"V\");'  ><b><font style='color: #B46767;' >  Enc::"+singleNodeArray[2]+" ("+singleNodeArray[3]+")"+"</font></b> </a> ",
        				widgetUrl:"sdsdsd"
        				})
        	episodeNode.addChild(visitNode); 
      		episodeNode.registerChild(visitNode,i); 
      		
      		unit=unitTemp;
      		episode=singleNodeArray[1];
      		visit=singleNodeArray[2];
		}
		else
		{
			var episodeTemp=singleNodeArray[1];
			var tempEpisodeSeq=singleNodeArray[1].substring(5,8);
      		var tempEpisodeSeqInt=parseInt(tempEpisodeSeq);
			if(episode!=episodeTemp)
			{
			episodeNode=dojo.widget.createWidget("TreeNode",{ 
        				title:"<a herf='' onclick='show(\""+singleNodeArray[1]+"\",\"E\");'  ><b><font style='color: "+episodeColor+";' >  Episode::"+tempEpisodeSeqInt+"</b></font> "+mlcData+episodeIsOpenStatus+" </a> "})
        	unitNode.addChild(episodeNode); 
      		unitNode.registerChild(episodeNode,i); 
			episode=episodeTemp;
			var visitValue=unitCodeArray[i]+'$'+singleNodeArray[1]+'$'+singleNodeArray[2]; 	
			// alert("visitValur "+visitValue)				
			visitNode=dojo.widget.createWidget("TreeNode",{ 
        				title:"<a herf='' onclick='show(\""+visitValue+"\",\"V\");'  ><b><font style='color: #B46767;' >  Enc::"+singleNodeArray[2]+" ("+singleNodeArray[3]+")"+"</font></b> </a> ",
        				widgetUrl:"sdsdsd"
        				})
        	episodeNode.addChild(visitNode); 
      		episodeNode.registerChild(visitNode,i); 	
      		visit=singleNodeArray[2];
			}
			else
			{
				var visitValue=unitCodeArray[i]+'$'+singleNodeArray[1]+'$'+singleNodeArray[2]; 	
				// alert("visitValur "+visitValue)
				var visitTemp=singleNodeArray[2];
				if(visit!=visitTemp)
				{
				visitNode=dojo.widget.createWidget("TreeNode",{ 
        				title:"<a herf='' onclick='show(\""+visitValue+"\",\"V\");'  ><b><font style='color: #B46767;' >  Enc::"+visitTemp+" ("+singleNodeArray[3]+")"+"</font></b> </a> ",
        				widgetUrl:"sdsdsd"
        				})
        	episodeNode.addChild(visitNode); 
      		episodeNode.registerChild(visitNode,i); 
      		visit=visitTemp;
				}
			}
			
		}
		
		
		 
		
	}
 	
	 
}
 

function makeNodeEpisodeWise(episodeRoot)
{

	var treeNode=document.getElementsByName("treeNode")[0].value;
	// alert("node "+document.getElementsByName("treeNode")[0].value)
	var episodeClosed=<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>;
	var episodeTypeCodeMLC=<%=RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC%>;
	var nodeArray=treeNode.split('^');
	var unit='';
	var episode='';
	var visit='';
	var treeStruct='';
	var treeStruct='';
	var unitNode='';
	var episodeNode='';
	
      
    // alert("klk "+episodeRoot)
      var unitCodeArray=(document.getElementsByName("unitCodeArray")[0].value).split('^');
	for(i=0;i<nodeArray.length;i++)
	{
		var singleNodeArray=nodeArray[i].split('#');
		var episodeTemp=singleNodeArray[1];
		var episodeIsOpen=singleNodeArray[4];
		var episodeTypeCode=singleNodeArray[5];
		var episodeColor="#B46767";
		var mlcData="";
		var episodeIsOpenStatus="";
		if(episodeIsOpen==episodeClosed)
		{
			episodeIsOpenStatus=" <font style='color: #9900CC;' >(Closed)</font> ";
		}
		
		if(episodeTypeCode==episodeTypeCodeMLC)
		{
			mlcData=" <font style='color: #FF0000;' >(MLC)</font> ";
		}
		if(episode!=episodeTemp)
		{
			var episodeTemp=singleNodeArray[1];
			var tempEpisodeSeq=singleNodeArray[1].substring(5,8);
      		var tempEpisodeSeqInt=parseInt(tempEpisodeSeq);
			episodeNode = dojo.widget.createWidget("TreeNode",{ 
        				title:"<a herf='' onclick='show(\""+episodeTemp+"\",\"E\");'  ><b><font style='color: "+episodeColor+";' >  Episode::"+tempEpisodeSeqInt+"</font></b> "+mlcData+episodeIsOpenStatus+"</a> "})
        	episodeRoot.addChild(episodeNode); 
      		episodeRoot.registerChild(episodeNode,i); 
      		var visitValue=unitCodeArray[i]+'$'+singleNodeArray[1]+'$'+singleNodeArray[2];
    		visitNode=dojo.widget.createWidget("TreeNode",{ 
        				title:"<a herf='' onclick='show(\""+visitValue+"\",\"V\");'  ><b><font style='color: #B46767;' >  Enc::"+singleNodeArray[2]+" ("+singleNodeArray[3]+")"+"</font></b> </a> ",
        				widgetUrl:"sdsdsd"
        				})
        	episodeNode.addChild(visitNode); 
      		episodeNode.registerChild(visitNode,i); 
      		
      		// alert("visitValur "+visitValue) 
		
      		episode=singleNodeArray[1];
      		visit=singleNodeArray[2];
		}
		
			
			else
			{
				var visitValue=unitCodeArray[i]+'$'+singleNodeArray[1]+'$'+singleNodeArray[2]; 	
				// alert("visitValur "+visitValue)
				var visitTemp=singleNodeArray[2];
				if(visit!=visitTemp)
				{
				visitNode=dojo.widget.createWidget("TreeNode",{ 
        				title:"<a herf='' onclick='show(\""+visitValue+"\",\"V\");'  ><b><font style='color: #B46767;' >  Enc::"+visitTemp+" ("+singleNodeArray[3]+")"+"</font></b> </a> ",
        				widgetUrl:"sdsdsd"
        				})
        	episodeNode.addChild(visitNode); 
      		episodeNode.registerChild(visitNode,i); 
      		visit=visitTemp;
				}
			}
			
		
		
		
		 
		
	}
	
	//var treeContainer = document.getElementById("myWidgetContainerEpisode"); 
    //var placeHolder = document.getElementById("treePlaceHolderEpisode"); 
    //treeContainer.replaceChild(myTreeWidget.domNode,placeHolder); 
	 
}



function makeNodeIpdWise(treeNodeIpd)
{

	var treeNodeAdmission=document.getElementsByName("addmissionTreeNode")[0].value;
	// alert("node "+document.getElementsByName("treeNode")[0].value)
	var nodeArray=treeNodeAdmission.split('#');
    // alert("klk "+episodeRoot)
  for(i=0;i<nodeArray.length;i++)
	{
		//var nodeArrayAdmissionNo=nodeArray[i].split("@");
		
		var admissionNode = dojo.widget.createWidget("TreeNode",{ 
        				title:"<a herf='' onclick='show(\""+nodeArray[i].split("@")[0]+"\",\"I\");'  ><b><font style='color: #B46767;' >  AdmissionNo::"+nodeArray[i].split("@")[0]+" ("+ nodeArray[i].split("@")[1]+ ") </font> </b></a> "})
		treeNodeIpd.addChild(admissionNode); 
      	treeNodeIpd.registerChild(admissionNode,i); 
	}
	 
}
function changeFrameSize(mode){
	var frameset=parent.document.getElementById("emrDetailFS")
	if(mode==1){
		document.getElementById("imgLeftId").style.display="none"
		document.getElementById("imgRightId").style.display="block"
		document.getElementById("myWidgetContainer").style.display="none"
		document.getElementById("treeNodeId").width="0%"
		frameset.cols = "13,*";
	}
	else{
		document.getElementById("imgLeftId").style.display="block"
		document.getElementById("imgRightId").style.display="none"
		frameset.cols = "18%,*";
		document.getElementById("myWidgetContainer").style.display="block"	
		document.getElementById("treeNodeId").width="100%"
	}

}

</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html:form action="/emrDesk">
<his:ContentTag>
<div id="imgLeftId" >
<table width="100%">
<tr height="100%"  >
<td width="100%" height="100%" style="background-color:#E0EBEB;" id="treeNodeId"> 
<div id="myWidgetContainer" class="dojo-Tree" menu="treeContextMenu"
   style="width: 100%; border: solid #888 1px;display: block"> 
  <span id="treePlaceHolder"  
     style="background-color:#F00; height: 100%;width: 100%"> 
    Loading tree ... 
  </span> 
</div>
</td>
<td valign="top" class="tdfonthead">
	<div align="left">
	<img class="button" src='<his:path src="/hisglobal/images/trileft.gif"/>' onclick="changeFrameSize(1)" title="Hide Menu"/>
	</div>
</td>
</tr> 
</table>
</div>
<div id="imgRightId" style="display: none">
<table width="100%">
<tr style="height: 200px">
<!--<td bgcolor="#FFB468" valign="top">-->
<td class="tdfonthead" valign="top">
	<div  align="left">
	<img class="button" src='<his:path src="/hisglobal/images/tri.gif"/>' onclick="changeFrameSize(2)" title="Show Menu"/>
	</div>
</td>
</tr> 
</table>
</div>
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

</html:form>
