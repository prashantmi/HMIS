<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="startup.tree.treeData"%>
<%@page import="hisglobal.persistence.JDBCTransactionContext" %>

<%
String userId=(String)request.getParameter("userId");
String seatId=(String)request.getParameter("seatId");
String hospitalCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
System.out.println(" userid: "+ userId+ " seatId: "+ seatId+ " hospCode: "+ hospitalCode);
JDBCTransactionContext tx=new JDBCTransactionContext();
treeData treeDataObj= new treeData(tx);
	Map zeroLevelModules = treeDataObj.getMenusHierarchyMap(userId,seatId,hospitalCode);
	if(zeroLevelModules!=null){
	 Set<Map.Entry> moduleSet = zeroLevelModules.entrySet();
%>
<%! public String traverseMap(Map map,String list){
						Set<Map.Entry> columnMapSet=map.entrySet();
						for (Map.Entry column : columnMapSet) {
							//System.out.println("key: "+ column.getKey().toString());
							if (column.getValue() instanceof String) { // only one level in column
								String value= column.getValue().toString();
								/* String link="<li ><a class='leafNode' onclick=callMenu('"+value+"') href='"+ value+"'>"+column.getKey().toString() +"</a> </li>"; */
								String link="<li ><a  onclick=\"callMenu('"+value +"')\">"+column.getKey().toString() +"</a> </li>";
								list+=link;
							}
							else if (column.getValue() instanceof Map) {
								list+="<li><a  href='#'>"+column.getKey().toString() +"</a> ";
								list+="<ul style='display:none;'>";
								list+=traverseMap((Map)column.getValue(),"");
								list+="</ul></li>";
								
							}
						}
						return list;
					}
					
					%>	
 <div id="menuContainer">
			<div id="arrow_left">
				<img src="../hisglobal/images/scroll_left.png" alt="scroll Menu left"
					title="Scroll Left" width="100%" height="100%">
			</div>
			<div id="arrow_right">
				<img src="../hisglobal/images/scroll_right.png" alt="scroll Menu right"
					title="scroll Right" width="100%" height="100%">
			</div>
			<div id="menuStrip" style="background: #A3C2EB;">
			<div id="smoothmenu">
				<ul id="menuList">
				<!--<li> <a href="#"> Registration </a>
				<ul>
					<li> <a onclick=callMenu("/AHIMS/registration/RegistrationDeskDuplicate.cnt")> Registration1 </a></li>
					<li> <a href="#"> Registration2 </a></li>
					<li> <a href="#"> Registration3 </a></li>
					<li> <a href="#"> Registration4 </a></li>
					<li> <a href="#"> Registration5 </a></li>
					<li> <a href="#"> Registration6 </a></li>
					<li> <a href="#"> Setup </a>
						<ul> 
							<li> <a href="#">Setup1 </a></li>
							<li> <a href="#">Setup2 </a></li>
							<li> <a href="#">Setup3 </a></li>
							<li> <a href="#">Setup4 </a></li>
							<li> <a href="#">Setup5 </a></li>
						</ul>
					</li>
				</ul>
				</li>
				<li> <a href="#"> Emergency</a></li>
				<li> <a href="#"> ADT </a></li>
				<li> <a href="#"> Operation Theatre</a>
					<ul>
					<li> <a href="#"> Registration1 </a></li>
					<li> <a href="#"> Registration2 </a></li>
					<li> <a href="#"> Registration3 </a></li>
					<li> <a href="#"> Registration4 </a></li>
					<li> <a href="#"> Registration5 </a></li>
					<li> <a href="#"> Registration6 </a></li>
					<li> <a href="#"> Setup </a>
						<ul> 
							<li> <a href="#">Setup1 </a></li>
							<li> <a href="#">Setup2 </a></li>
							<li> <a href="#">Setup3 </a></li>
							<li> <a href="#">Setup4 </a></li>
							<li> <a href="#">Setup5 </a></li>
						</ul>
					</li>
				</ul>
				</li>
				<li> <a href="#"> Sanitation</a></li>
				-->
				<!-- Code to Generate Menu Dynamically -->
					<%
						for (Map.Entry set : moduleSet) {
							String key = set.getKey().toString();
							//System.out.println("key: " + key);
							if (set.getValue() instanceof String) { // no dropdown menu is there
								%><li><a href="<%=set.getValue().toString() %>"> <%=set.getKey().toString() %></a> </li>
								<%
							}else if (set.getValue() instanceof HashMap) {
								System.out.println("Value type HashMap: "
										+ set.getValue().getClass());
								%>
								<li><a href="#"> <%=set.getKey().toString() %></a> 
								<ul> <!-- list to display column of 1st level -->
								<% HashMap columnHeadingMap=(HashMap)set.getValue();
								boolean isSingleSetupReport=false;
								/*if(columnHeadingMap.size()==1)
								{
									Set<Map.Entry> st = columnHeadingMap.entrySet();
									for(Map.Entry colEntrySet : st)
									{
										if(colEntrySet.getValue() instanceof HashMap)
											isSingleSetupReport=true;
										break;
									}
								}*/
								
								Set<Map.Entry> columnHeadingSet = columnHeadingMap.entrySet();
								int columnCount=0;
								columnCount=columnHeadingSet.size();
								String columnCountClass=""; // to refer to 1, 2 or 3 columns
								if(columnCount!=0){
									if(columnCount==1)
										columnCountClass="dropdown_1column";
									else if(columnCount==2)
										columnCountClass="dropdown_2columns";
									else if(columnCount==3)
										columnCountClass="dropdown_3columns";
								} 
								%>
								<%-- <div class="<%= columnCountClass %>"> --%>
								<%
								for (Map.Entry columnHeadinigMapSet : columnHeadingSet) {
									if(columnHeadinigMapSet.getKey().toString().equalsIgnoreCase("services") || isSingleSetupReport){
										%>
										<%-- <div class="col_1">
										<h3><%= columnHeadinigMapSet.getKey().toString() %> </h3> --%>
										 
										<%
										HashMap columnMapFirstLevel=(HashMap) columnHeadinigMapSet.getValue();
										String list= traverseMap(columnMapFirstLevel,"");
										//System.out.println("list: "+list);
										%>
										<%= list %>
										
										<!-- </div> --> 
										
										<%
									}//end of if to check services map
									else{%>
									<li><a href="#"> <%=columnHeadinigMapSet.getKey().toString() %></a>
									<ul>  
									<%
										HashMap columnMapFirstLevel=(HashMap) columnHeadinigMapSet.getValue();
										String list= traverseMap(columnMapFirstLevel,"");
										//System.out.println("list: "+list);
										%>
										<%= list %>
									
									</ul>
									</li>
									
									<%
									}
									}
								%>
								</ul>
								</li>
								<%
							} 
						}
					%>

					<!-- Code to Generate Menu Dynamically  Ends -->
				
				
				
					<div id="slideEnd"></div>
				</ul>
				</div>

			</div>

		</div>
		
		
		<%} %>
		
		