package startup.tree;

import java.util.ArrayList;
import java.util.Iterator;

import startup.menu.menuConfigure;

public class TreeBuilder
{
	private Tree tree;
	menuConfigure menuConfig = new menuConfigure();
	String menuPageBackColor = menuConfig.getMenuPageBackColor();
	String leafMenuFont = menuConfig.getLeafMenuFont();
	String leafMenuColor = menuConfig.getLeafMenuColor();
	String leafMenuFontSize = menuConfig.getLeafMenuFontSize();
	String plusImage = menuConfig.getPlusImage();
	String topMenuFont = menuConfig.getTopMenuFont();
	String topMenuColor = menuConfig.getTopMenuColor();
	String topMenuFontSize = menuConfig.getTopMenuFontSize();

	public void setTree(Tree tree)
	{
		this.tree = tree;
	}

	public Tree getTree()
	{
		return tree;
	}
	  
	public TreeBuilder(Tree _tree){
		tree = _tree;
	}
	
	Label lb=new Label();	
	ArrayList al=new ArrayList();
	
	public void renderTree(javax.servlet.jsp.JspWriter out, TreeNode _root)
	{
		al.add("0");
		try
		{
			Sequence sq = new Sequence();
			Sequence Lnodesq = new Sequence();
			renderTree(out, _root, sq, Lnodesq);
		}
		catch(Exception e)
		{System.out.println("e");}
	}
	
	public void renderTree( javax.servlet.jsp.JspWriter out,TreeNode root, Sequence sq, Sequence Lnodesq)
	{
		int _sq = sq.next();
				
		try
		{
			int _lnodesq = Lnodesq.next();
			if(root.getSubtree().size()==0)	
			{
					
				out.write("\n<table border='0' cellpadding='1' cellspacing='0' width='100%'>\n"+
							"<tr>\n"+
							"<td nowrap>\n");
				//System.out.println("inside if.................................. ");
				lb.setLabel(root.getValue());
				out.write("<span id='xnode"+_sq+"'  style='cursor:pointer;'>\n ");	
				//out.write("\t\t\t\t<img src='../images/leaf.gif'>\n");
				//out.write("\t\t<input type='checkbox' name='chk' id='"+root.getValue().trim()+"' value='"+root.getValue().trim()+"' onClick=changeColor(this)>\n");
				//out.write("<a tabindex='1' style='cursor:hand' target='iframe2'  href='"+root.getUrl()+"' ");
				out.write("<a tabindex='1' style='cursor:hand' "); 
				out.write("onMouseOver='callMouseOver(\"leafNode"+_lnodesq+"\");' ");
				out.write("onMouseOut='callMouseOut(\"leafNode"+_lnodesq+"\");' ");
				out.write("onfocus='callMouseOver(\"leafNode"+_lnodesq+"\");' ");
				out.write("onblur='callMouseOut(\"leafNode"+_lnodesq+"\");' ");
				//out.write("onClick='callMe(this.href,\"leafNode"+_lnodesq+"\");' title='"+lb.getLabel()+"'> ");
				out.write("onClick='callMe(\""+root.getUrl()+"\",\"leafNode"+_lnodesq+"\",\"title"+lb.getLabel()+"\");' ");
				out.write("onkeypress=\"if (event.keyCode==13) callMe('"+root.getUrl()+"','leafNode"+_lnodesq+"','title"+lb.getLabel()+"');\" title='"+lb.getLabel()+"'> ");
				out.write("<font id='leafNode"+_lnodesq+"' face='"+this.leafMenuFont+"' color='"+this.leafMenuColor+"' size= '"+this.leafMenuFontSize+"'> ");
				out.write(lb.getLabel());
				out.write(" </font> ");
				out.write("</a>");
				out.write("</span> ");
				//System.out.println("root.getUrl()--"+root.getUrl());
				//System.out.println("node sequence--"+_lnodesq);
				//System.out.println("Title--"+lb.getLabel());
				
			}
			else
			{
				out.write("\n<table border='0' cellpadding='1' cellspacing='0' width='100%'>\n"+
							"<tr>\n"+
							"<td nowrap>\n");
				if(root.getValue().equals("")||root.getValue()==null)
				{
					//out.write("<tr><td class='tdfont'>Hi This is Root</td></tr>");
				}
				else{          
				out.write("<span id='xnode"+_sq+"'  style='cursor:pointer;' onClick=toggleNodeOfTree('node"+_sq+"','initPage.jsp')>\n ");				
				out.write("<img src='"+this.plusImage+"' >\n ");
				
				out.write("</span>\n"+
						  //"<a onClick=toggleMenuName('node"+_sq+"',this.href) target='iframe2' href='initPage.jsp' style='cursor:pointer;'>"+
						  //"<a onClick=toggleMenuName('node"+_sq+"',this.href) style='cursor:pointer;'>"+
						  "<a onClick=toggleNodeOfTree('node"+_sq+"','initPage.jsp') onkeypress=\"if (event.keyCode==13) toggleNodeOfTree('node"+_sq+"','initPage.jsp')\" style='cursor:pointer;' tabindex='1'"+
						  "onMouseOver='callMouseOver(\"leafNode"+_lnodesq+"\");' "+
							"onMouseOut='callMouseOut(\"leafNode"+_lnodesq+"\");' "+
							"onfocus='callMouseOver(\"leafNode"+_lnodesq+"\");' "+
							"onblur='callMouseOut(\"leafNode"+_lnodesq+"\");' >" +
						  "<font id='leafNode"+_lnodesq+"' face='"+this.topMenuFont+"' color='"+this.topMenuColor+"' size='"+this.topMenuFontSize+"' title='"+root.getValue()+"'>"+
						  root.getValue()+
						  "</font>\n"+
						  "</a>"+					
						  "</td>\n"+
						  "</tr>\n"+			
						  "<tr>\n"+
						  "<td colspan='1' nowrap>\n"+
						  "<div id='node"+_sq+"' style='display:none; margin-left: 0.5em; '>\n");
				//System.out.println("root.getValue()---"+root.getValue());
				}
			}
			Iterator itr = root.getSubtree().iterator();
			
			while(itr.hasNext())
			{
				renderTree(out, (TreeNode)itr.next(), sq, Lnodesq);
				
			}
			out.write("</div>\n"+
					   "</td>\n"+
					   "</tr>\n"+
					   "</table>\n");
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
}
