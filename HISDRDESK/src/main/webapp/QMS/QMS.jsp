<!DOCTYPE html>
<html lang="en">
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.collections.MultiMap"%>
<%@page import="org.apache.commons.collections.map.MultiValueMap"%>
<%@page import="org.json.*"%>
<head>
  <title>QMS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  
  <style>
.card {
    
    border: 3px solid rgba(108, 208, 76, 0.97);
   
}

.table td, .table th{

border-top:0;

} 
label{
margin-top:3rem;
} 
th{
  font-size: 1.8rem;
}
.navbar{

  border-bottom: 3px solid rgba(108, 208, 76, 0.97);
}
  </style>

  
  
  
<script type="text/javascript">
		var webSocket = 
			new WebSocket('ws://10.226.17.20:8082/HISDRDESK/QMSTest');

		webSocket.onerror = function(event) {
			onError(event)
		};

		webSocket.onopen = function(event) {
			onOpen(event)
		};

		webSocket.onmessage = function(event) {
			onMessage(event)
		};

		function onMessage(event) {
			
			document.getElementById('messages').innerHTML 
				+= '<br />Received message: ' + event.data;
			//alert(event.data);
			var JsonObj=JSON.parse(event.data);
			//alert(JsonObj.ROOM_CODE);
			document.getElementById('QnoDivId'+JsonObj.DEPTUNITROOMCODE).className = ""; 
			document.getElementById('RoomNoDivId'+JsonObj.DEPTUNITROOMCODE).innerHTML=JsonObj.ROOM;
			document.getElementById('QnoDivId'+JsonObj.DEPTUNITROOMCODE).innerHTML=JsonObj.QUENO;
			document.getElementById('DepName').innerHTML=JsonObj.DEPARTMENT+'(All Rooms)';
			document.getElementById('QnoDivId'+JsonObj.DEPTUNITROOMCODE).className = "w3-container w3-center w3-animate-zoom"; 
			
		}

		function onOpen(event) {
			document.getElementById('messages').innerHTML 
				= 'Connection established';
		}

		function onError(event) {
			alert(event.data);
		}

		function send() {
			//var txt = document.getElementById('inputmessage').value;
			webSocket.send();
			return false;
		}
	</script>
</head>



<body >
  <div class="container col-sm-10" style="background-color: #070606">
<nav class="navbar navbar-expand-sm  navbar-dark">

  <b> <a class="navbar-brand" href="#"><div id="DepName"></div></a></b>
  
</nav>
<%

System.out.println("Room Code"+request.getParameter("RoomCode"));
%>
<div class="row">
  <div class="col-sm-5" style="border-right: 3px solid rgba(108, 208, 76, 0.97)">
  <%
  		String RoomDtls=request.getParameter("RoomCode");
  	if(RoomDtls !=null){
  		for(int i=0 ;i<RoomDtls.split("Room").length ; i++)
  		{
  		
  %>
  
  
   <div class="card" style="background-color: #070606;color: #fbf7f7;">
    <div class="card-body">
      <div class="row">
        <div class="col-sm-2" align="right">
          <b style="font-size: 4rem;color: #f5d307"> <div id="QnoDivId<%=RoomDtls.split("Room")[i]%>"> 0 </div></b>
        </div>
        <div class="col-sm-4">
           <b style="font-size: 1.5rem;"><label>Now</label></b>
        </div>
        <!-- <div class="col-sm-2">
        </div> -->
        <div class="col-sm-6">
           <b style="font-size: 1.5rem;color: #077df5" ><label><div id="RoomNoDivId<%=RoomDtls.split("Room")[i]%>"> 0 </div></label ></b>
        </div>
      </div>
    </div>
  </div> 



<%
	}
  }
%>
</div>
     <div class="col-sm-7">
      <table class="table" style="color: #fbf7f7;border-top: none;">
    <thead style="color: #fde606">
      <tr>
        <th>Q NO.</th>
        <th>STATUS</th>
        <th>ROOM</th>
        <th>ETA</th>
      </tr>
    </thead>
    <tbody align="center">
      <tr >
        <td>7</td>
        <td>Next</td>
        <td>08</td>
        <td>13.20</td>
      </tr>
      <tr>
        <td>5</td>
        <td>Next</td>
        <td>09</td>
        <td>13.20</td>
      </tr>
      <tr>
       <td>6</td>
        <td>Next</td>
        <td>10</td>
        <td>13.22</td>
      </tr>
       <tr>
       <td>7</td>
        <td>Next</td>
        <td>11</td>
        <td>13.23</td>
      </tr>
       <tr>
       <td>8</td>
        <td>Wait</td>
        <td>08</td>
        <td>13.30</td>
      </tr>
       <tr>
       <td>6</td>
        <td>Wait</td>
        <td>09</td>
        <td>13.30</td>
      </tr>
       <tr>
       <td>7</td>
        <td>Wait</td>
        <td>10</td>
        <td>13.30</td>
      </tr>
       <tr>
       <td>9</td>
        <td>Wait</td>
        <td>11</td>
        <td>13.30</td>
      </tr>
    </tbody>
  </table>
<div class="card" style="background-color: #070606;color: #fbf7f7">
    
    <div class="card-body">
        
         <b style="font-size: 1.5rem;color: #fbf7f7;"><label style="margin-top: 0;">Information texts</label></b>
       
  </div>
  </div>
  </div>
</div>
  </div>
  </div>
  <br>
</div>
	<div style="display: none" id="messages"></div>
</body>
</html>


 