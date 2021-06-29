<%@taglib uri="/WEB-INF/tree.tld" prefix="treeBuilder"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="java.util.*"%>
<%
	ArrayList temp=new ArrayList();
	ArrayList temp1=new ArrayList();
	ArrayList postdata=new ArrayList();
	String treename="";
	//ArrayList predata=new ArrayList();
	int i=0;
	
%>
<html>
<head>
<title>Tree Structure</title>
<his:javascript src="/hisglobal/js/stack.js"/>
<his:javascript src="/hisglobal/js/queue.js"/>
<his:javascript src="/hisglobal/js/tree.js"/>
<script>
function changeMode()
{
	document.form1.submit();
}

</script>

<style type="text/css">
	td, body {color:#000000; font-family:sans-serif; font-size:11px;}
	a {color:#000000; text-decoration:none; font-weight:bold;}
	a:hover {color:#000000; text-decoration:none; font-weight:bold;}
	a:active {color:#000000; text-decoration:none; font-weight:bold ;}
</style>
<style>
.greenColor{
	background-Color:#00FF00;
	height:7;
	font-size:12;
	font-family: Verdana;
	color:#000099;
	text-align:left;
	}
</style>

<link href="../css/Color.css" rel="stylesheet" type="text/css">
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../css/hisStyle.css" rel="stylesheet" type="text/css">
</head>


<body onload="initializer();">
<form name="form1" action="result.jsp">
<%@ include file="/hisglobal/header.jsp"%>
 
<%try{	%>	
<his:tab tabTitle="IPD Patient Disease List" width="100%" />

<table border='0' cellpadding='0' cellspacing='1' width ='97%' >
	
	
	<tr>
		<td class="tdfont">
			<treeBuilder:tree seatId=""/>
		</td>
	</tr>
	<tr>
		<td class='tdfont'>
		<div align="center">
		<img src="../hisglobal/images/Save.gif" onclick="changeMode()">
		</div>
		</td>
	</tr>
	<tr>
		<td class='tdfonthead'></td>
	</tr>
</table>
			
	<%
				
				try{
						temp=(ArrayList)request.getAttribute("postorderTraversalQue");
						temp1=(ArrayList)request.getAttribute("preorderTraversalQue");
						postdata=(ArrayList)request.getAttribute("postorderData");
						treename=(String)request.getAttribute("treename");
						//predata=(ArrayList)request.getAttribute("preorderData");
						
				}catch(Exception e){e.printStackTrace();}
				
				System.out.println("the size of arraylist is:"+temp1.size());
				System.out.println("the size of arraylist is:"+temp.size());
				//System.out.println("the size of arraylist of data is:"+predata.size());
				System.out.println("the size of arraylist of data is:"+postdata.size());
					
			
	%>
		
				
		<script>
		 var trICD=new Tree();

		function initializer(){
				var postorderque=new Queue();
				var nodeValueArr=new Array();
				var preorderque=new Queue();

				//<<<<<<<<<<<<<<<<<<<<<<<<Setting the values from JSP>>>>>>>>>>>>>>>>>>>>>>>>
				<%
				System.out.println("temp.size(): "+temp.size());
				for(i=0;i<temp.size();i++) //<<<<<<<
				{
				%>
						postorderque.add('<%=temp.get(i)%>');
						nodeValueArr['<%=temp.get(i)%>']='<%=postdata.get(i)%>';
						preorderque.add('<%=temp1.get(i)%>');
				<%
				}
				%>

			trICD = new Tree();
			//alert("the innerHtml is:"+document.getElementById("node1").innerHTML);
			trICD.treeScript = document.getElementById("node1").innerHTML; //<<<<<<<
			trICD.preOrderQueue = preorderque;
			trICD.postOrderQueue = postorderque;
			trICD.nodeValueArr = nodeValueArr;
			trICD.rootNodeName = "node1"; //<<<<<
		}		
		</script>
		
		</FORM>
	</BODY>
</HTML>

<%
}catch(Exception e){
	System.out.println("exception in test.jsp: "+e);	
}
%>