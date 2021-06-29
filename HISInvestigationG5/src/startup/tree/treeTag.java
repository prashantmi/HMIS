/*Developed By Sanjeev Kumar
Date=9-06-2006
*/

package startup.tree;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;

public class treeTag extends TagSupport
{
	private String userId = "";	
	private String seatId = "";
	private String hospitalCode = "";
	public String getUserId() {	return userId; }
	public void setUserId(String userId) { this.userId = userId; }
	public void setSeatId(String str){this.seatId = str;}
	public String getSeatId(){ return this.seatId;}
	public void setHospitalCode(String str){this.hospitalCode = str;}
	public String getHospitalCode(){ return this.hospitalCode;}
	
	TreeBuilder tb;
	Tree tr = new Tree();
	MyExpTree t=new MyExpTree();
		
	public int doStartTag() throws JspTagException
	{	
		tr.setTreeName(Config.TREE_NAME);
		this.tb=new TreeBuilder(tr);
		return EVAL_BODY_INCLUDE;
	}
		
	public int doEndTag() throws JspTagException
	{			
		try
		{
			javax.servlet.jsp.JspWriter out=pageContext.getOut();
			MyExpTree t=new MyExpTree();
								
			TreeNode root = t.callIt(this.userId,this.getSeatId(),this.getHospitalCode());
			tb.renderTree(out ,root);									
		}
		catch(Exception ex)
		{
			throw new JspTagException("Fatal error:helloTag could not write to JspOut");
		}
		return EVAL_PAGE;	
	}
	
}
