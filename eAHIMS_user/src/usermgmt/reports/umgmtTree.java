package usermgmt.reports;
import java.util.*;
import usermgmt.FuncLib;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


class treeNode
{
	private String 	key				= "";
	private String 	displayText 	= "";
	private List 	subtree 		= null;
	
	
	treeNode(String t1,String t2,List t3)
	{
		key = t1;
		displayText = t2;
		subtree = t3;		
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List getSubtree() {
		return subtree;
	}

	public void setSubtree(List subtree) {
		this.subtree = subtree;
	}

	
	
	
}

//getters & setters ends here 
//functions starts here

public class umgmtTree  extends FuncLib
{
	private treeNode rootObj = null;
	private String	hospitalCode    = "";
	
	public treeNode getRootObj() {
		return rootObj;
	}
	public void setRootObj(treeNode rootObj) {
		this.rootObj = rootObj;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public void addNode()
	{
		
		
	}
	public void buildTree()
	{	
		buildLevelOneNodes();
		buildLevelTwoNodes();
		buildLevelThreeNodes();
		
		
	}
	public String displayTree()
	{
		String space5 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		String space10 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		String html = "";
		int level3Counter = 1;
		try
		{
		if(this.rootObj!=null)
		{
			
			/*		 Getting the reference of Root Node 	*/
			
			
			treeNode tempRootNode = this.rootObj;
			
			
			/*		 Getting the All Child  Nodes of Root Node that represent Group Names 	*/
			
			
			List groupNodeList = tempRootNode.getSubtree();
			
			if(groupNodeList!=null && groupNodeList.size()!=0)
			{			
				html += "<br><img src='../../images/plus.gif' onClick='levelOneAction(this);'>&nbsp;&nbsp;<font color='red'>"+tempRootNode.getDisplayText()+"</font>\n";				
			}
			else
			{
				html += "<br><font color='red'>&nbsp;&nbsp;"+tempRootNode.getDisplayText()+"</font>\n";
				
			}
			
			/*		 Going through each Group Node		*/
			
			
			if(groupNodeList!=null)
			{
				html += "<div id='levelOne'>\n";
				
				/*		 Getting the reference 	of each Group Node one by one		*/
				
				boolean firstGroupNode = true;
				
				for(int i=0;i<groupNodeList.size();i++)
				{					
					
					treeNode groupNode = (treeNode) groupNodeList.get(i);
					
					/*		 Getting the List of seat attached to that group	*/
					
					List seatNodeList = groupNode.getSubtree();					
					
					/*		 Checking if the group node has further child then display a plus button	*/
					
					if(seatNodeList!=null && seatNodeList.size()!=0)
					{
						String divName = "level_2_"+(i+1)+"";	
						if(firstGroupNode)
						{
							html += ""+space5+"<img src='../../images/plus.gif' onClick='levelTwoAction(\""+divName+"\",this);'>&nbsp;&nbsp;<font color='green'>"+groupNode.getDisplayText()+"</font>\n";
							firstGroupNode = false;
						}
						else
						{				
							html += "<br>"+space5+"<img src='../../images/plus.gif' onClick='levelTwoAction(\""+divName+"\",this);'>&nbsp;&nbsp;<font color='green'>"+groupNode.getDisplayText()+"</font>\n";
						}
						
						html += "<div id='"+divName+"' style='display:none'>\n";
						
						/*		 Getting the reference of each seat node one by one	*/
						boolean firstSeatNode = true;
						
						for(int j=0;j<seatNodeList.size();j++)
						{
							treeNode seatNode = (treeNode) seatNodeList.get(j);
						
							/*		 Getting the subtree List of each seat Node	*/
							
							List roleSeatList = seatNode.getSubtree();
						
							/*		 Checking if the seat Node has further children 	*/
						
							if(roleSeatList!=null && roleSeatList.size()!=0)
							{							
								String seatdivName = "level_3_"+level3Counter;
								level3Counter++;
								if(firstSeatNode)
								{
									html += ""+space10+"<img src='../../images/plus.gif'  onClick='levelThreeAction(\""+seatdivName+"\",this);'>&nbsp;&nbsp;&nbsp;<font color='brown'>"+seatNode.getDisplayText()+"</font>\n";									
									firstSeatNode = false;
								}
								else
								{
									html += "<br>"+space10+"<img src='../../images/plus.gif'  onClick='levelThreeAction(\""+seatdivName+"\",this);'>&nbsp;&nbsp;&nbsp;<font color='brown'>"+seatNode.getDisplayText()+"</font>\n";
								}
								html += "<div id='"+seatdivName+"' style='display:none'>";
								
								/*Here Role Names are displayed of a the seat*/
								boolean firstRoleNode = true;
								for(int k=0;k<roleSeatList.size();k++)
								{									
									treeNode roleNode = (treeNode) roleSeatList.get(k);	
									
									
									/* Group Id, Seat Id and Role Id are passed from here */
									if(firstRoleNode)
									{
										//String altText = "Group Code : "+groupNode.getKey()+" Seat Id :"+seatNode.getKey()+" Role Id :"+roleNode.getKey();
										html += ""+space10+space10+"<img src='../../images/tri.gif'>&nbsp;&nbsp;<a onClick='passValues(\""+groupNode.getKey()+"\",\""+seatNode.getKey()+"\",\""+roleNode.getKey()+"\");'>"+roleNode.getDisplayText()+"</a>\n";
										firstRoleNode = false;
									}
									else
									{
										html += "<br>"+space10+space10+"<img src='../../images/tri.gif'>&nbsp;&nbsp;<a onClick='passValues(\""+groupNode.getKey()+"\",\""+seatNode.getKey()+"\",\""+roleNode.getKey()+"\");'>"+roleNode.getDisplayText()+"</a>\n";
									}
								}
								html += "</div>\n";
								
							}
							else
							{
								if(firstSeatNode)
								{
									html += ""+space10+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='brown'>"+seatNode.getDisplayText()+"</font>\n";									
									firstSeatNode = false;
								}
								else
								{
									html += "<br>"+space10+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='brown'>"+seatNode.getDisplayText()+"</font>\n";
								}
								
							
							}
						}
						html += "</div>\n";
						
					}
					else
					{
						
						if(firstGroupNode)
						{
							System.out.println("groupNode.getDisplayText()="+groupNode.getDisplayText());
							html += ""+space5+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='green'>"+groupNode.getDisplayText()+"</font>\n";
							firstGroupNode = false;
						}
						else
						{								
							html += "<br>"+space5+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='green'>"+groupNode.getDisplayText()+"</font>\n";
						}
						
						//html += "<div id='xxx'>\n";
						//html += "</div>\n";
						
					}					
				}	
				html += "</div>\n";
			}		
		}
		}
		catch(Exception e)
		{
			System.out.println("Exception in display tree "+e);
		
		}
		return html;		
	}
	
	
	public void buildLevelOneNodes()
	{	
		
		
		String query = 	" SELECT GNUM_GROUP_CODE, INITCAP(GSTR_GROUP_NAME) "+
						" FROM GBLT_GROUP_MST WHERE GNUM_ISVALID = 1 " +
						" and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' " +
						"  order by GSTR_GROUP_NAME";
		System.out.println("LevelOneNodesQuery----->"+query);
		List temp = null;
		try
		{
			temp = super.getDetails(query,2);
		}
		catch(Exception e)
		{
			System.out.println("Exception in buildLevelOneNodes() "+e);
		}
		List nodeList = new ArrayList();
		if(temp!=null || temp.size()!=0)
		{
			for(int i=0;i<temp.size();i+=2)
			{
												
				treeNode tempNode = new treeNode(temp.get(i+0).toString(),temp.get(i+1).toString(),null);
				nodeList.add(tempNode);
			}
			
		}	
		if(this.getRootObj()==null)
		{
			treeNode tempNode = new treeNode("99999","User Management",nodeList);	
			this.setRootObj(tempNode);
		}
		
	}
	public void buildLevelTwoNodes()
	{	
		
		
		String query = 	" SELECT  GNUM_SEATID, INITCAP(GSTR_SEAT_NAME) "+
						" FROM GBLT_SEAT_MST WHERE GNUM_GROUP_CODE = ? and GNUM_ISVALID = 1"+
						" and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' " ;
		
		System.out.println("LevelTwoNodes----->"+query);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
				conn = super.getConnection();
				
				ps = conn.prepareStatement(query);	
				
				List rootList = null;
				
				rootList = this.getRootObj().getSubtree();
				
				
				
				if(rootList!=null || rootList.size()!=0)
				{
					for(int i=0;i<rootList.size();i++)
					{
						rs = null;
						
						treeNode currentRoot = (treeNode)rootList.get(i);
						
						String key = currentRoot.getKey();
						
						
						
						ps.setString(1,key);		
						
						rs = ps.executeQuery();
						
						List childList = null;
						
						if(rs!=null)
						{
							childList = new ArrayList();
							
							while(rs.next())
							{
								
								
								treeNode tempNode = new treeNode(rs.getString(1).toString(),rs.getString(2).toString(),null);
								
								childList.add(tempNode);
								
							}
						}
						currentRoot.setSubtree(childList);						
					}			
				}		
				
		}
		catch(Exception e)
		{
			System.out.println("Exception in buildLevelTwoNodes() "+e);
		}
		finally
		{
			try
			{
				if(rs != null)			rs.close();
				if(ps != null)			ps.close();
				if(conn != null)		conn.close();			
			}
			catch(Exception e)
			{
				System.out.println("Exception in closing connection "+e);
				
			}			
		}
		
		
	}
	public void buildLevelThreeNodes()
	{	
		
		String query = 	" SELECT GNUM_ROLE_ID, "+
						" ("+
						" 	SELECT initcap(GSTR_ROLE_NAME) FROM GBLT_ROLE_MST x"+
						" 	WHERE x.GNUM_ROLE_ID = a.GNUM_ROLE_ID"+
						" AND x.GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE "+
						" ) FROM GBLT_SEAT_ROLE_MST a"+
						" WHERE a.GNUM_SEATID = ?  and a.GBL_ISVALID = 1 "+
						" and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' " ;

		System.out.println("LevelThreeNodes---->"+query);
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
				conn = super.getConnection();
				ps = conn.prepareStatement(query);				
				List groupNodeList = null;
				groupNodeList = this.getRootObj().getSubtree();				
				if(groupNodeList!=null || groupNodeList.size()!=0)
				{
					for(int i=0;i<groupNodeList.size();i++)
					{						
						treeNode groupNode = (treeNode)groupNodeList.get(i);
						List seatNodeList = groupNode.getSubtree();
						if(seatNodeList!=null && seatNodeList.size()!=0)
						{
							for(int j=0;j<seatNodeList.size();j++)
							{							
								treeNode seatNode = (treeNode)seatNodeList.get(j);
								String key = seatNode.getKey();
								ps.setString(1,key);
								rs = null;
								rs = ps.executeQuery();
								List childList = new ArrayList();
								if(rs!=null)
								{
									while(rs.next())
									{
										treeNode tempNode = new treeNode(rs.getString(1).toString(),rs.getString(2).toString(),null);
										childList.add(tempNode);										
									}
								}
								seatNode.setSubtree(childList);									
							}
						}										
					}			
				}		
				
		}
		catch(Exception e)
		{
			System.out.println("Exception in buildLevelThreeNodes() "+e);
		}
		finally
		{
			try
			{
				if(rs != null)			rs.close();
				if(ps != null)			ps.close();
				if(conn != null)		conn.close();			
			}
			catch(Exception e)
			{
				System.out.println("Exception in closing connection "+e);
				
			}			
		}
		
	}

}
