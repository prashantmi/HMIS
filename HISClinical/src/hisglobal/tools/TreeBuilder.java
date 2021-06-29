package hisglobal.tools;

import hisglobal.Division;
import hisglobal.Label;
import hisglobal.utility.Sequence;

import java.util.*;

/**
 * 
 * @author Administrator This class is used for Rendering a tree(HTML) on providing a tree object
 * 
 */

public class TreeBuilder
{
	private Tree tree;

	public void setTree(Tree tree)
	{
		this.tree = tree;
	}

	public Tree getTree()
	{
		return tree;
	}

	public TreeBuilder(Tree _tree)
	{
		tree = _tree;
	}

	Division division = new Division();
	CheckBox chk = new CheckBox();
	Label lb = new Label();
	ArrayList al = new ArrayList();
	ArrayList div = new ArrayList();
	ArrayList preorderTraversalQue = new ArrayList();
	ArrayList postorderData = new ArrayList();
	ArrayList postorderTraversalQue = new ArrayList();
	ArrayList div1 = new ArrayList();

	// getter & setter methods for arraylist inorder
	public java.util.ArrayList getPreorderTraversalQue()
	{
		return preorderTraversalQue;
	}

	public void setPreorderTraversalQue(java.util.ArrayList preorderTraversalQue)
	{
		this.preorderTraversalQue = preorderTraversalQue;
	}

	/*
	 * public java.util.ArrayList getPreorderData( ) { return preorderData; }
	 * 
	 * public void setPreorderData( java.util.ArrayList preorderData ) { this.preorderData = preorderData; }
	 */

	public java.util.ArrayList getPostorderData()
	{
		return postorderData;
	}

	public void setPostorderData(java.util.ArrayList postorderData)
	{
		this.postorderData = postorderData;
	}

	public java.util.ArrayList getPostorderTraversalQue()
	{
		return postorderTraversalQue;
	}

	public void setPostorderTraversalQue(java.util.ArrayList postorderTraversalQue)
	{
		this.postorderTraversalQue = postorderTraversalQue;
	}

	public void renderSelectTree(javax.servlet.jsp.JspWriter out, String _propertyName, String _arrSelectedProp[])
	{
		System.out.println("_root: " + this.tree.getRoot());
		al.add("0");
		chk.setValue(al);
		chk.setId(al);
		division.getControls().add(lb);
		System.out.println("al:::::::" + al);
		System.out.println("inside render tree");
		try
		{
			// f.setControls(chk);

			out.write("\n" + "<script> var " + this.tree.getTreeName().trim() + "= new Tree();</script>"
					+ "<table border='0' cellpadding='0' cellspacing='1' width ='97%' >" +

					"<tr >\n" + "<td class='tdfont'>\n" + "<a href='javascript:" + this.tree.getTreeName().trim()
					+ ".expandAll();'>Expand All - </a>\n" + "<a href='javascript:" + this.tree.getTreeName().trim()
					+ ".collapseAll();'>Collapse All</a>\n" +
					// "<a href='javascript:"+this.tree.getTreeName().trim()+".expandAllByTree();'>Expnad All</a>\n"+
					"</td>\n" + "</tr>\n" + "<tr >\n" +
					/*
					 * "<td class='tdfont'>\n"+ "<img src='../hisglobal/images/Search.gif' >\n"+ "<input
					 * name='searchString' type='text' size='30' maxlength='100' >\n"+
					 *  "<input type='button' class='tdfont' name='firstSearch' value='FirstSearch'"+ "
					 * onKeyPress='if(window.event.keyCode==13)"+this.tree.getTreeName().trim()+".find(document.forms[0].searchString.value)'"+ "
					 * onclick="+this.tree.getTreeName().trim()+".find(document.forms[0].searchString.value)>\n"+ // "<input
					 * type='button' class='tdfont' name='markAll' value='MarkAll'
					 * onclick="+this.tree.getTreeName().trim()+".findAll(document.forms[0].searchString.value)>\n"+ "<input
					 * type='button' class='tdfont' name='next' value='findNext'
					 * onKeyPress='if(window.event.keyCode==13)"+this.tree.getTreeName().trim()+".findNext(document.forms[0].searchString.value)'
					 * onclick="+this.tree.getTreeName().trim()+".findNext(document.forms[0].searchString.value)>\n"+
					 *  "</td>\n"+ "</tr>\n"+
					 */
					"</table>\n");

			Collection colCheckedOnes = new ArrayList();

			for (int i = 0; i < _arrSelectedProp.length; i++)
			{
				TreeNode tn = new TreeNode();
				System.out.println("_arrSelectedProp[i]:  " + _arrSelectedProp[i]);
				tn.setValue(_arrSelectedProp[i]);
				colCheckedOnes.add(tn);
			}

			Sequence sq = new Sequence();

			Collection colAncestorsToBOpen = new HashSet();

			colAncestorsToBOpen.add(this.tree.getFindAnscestor());

			Iterator it = colCheckedOnes.iterator();

			// find ancestors of all the checked nodes too

			while (it.hasNext())
			{
				TreeNode tn = (TreeNode) it.next();
				System.out.println("treeNode value:  " + tn.getValue());
				colAncestorsToBOpen.addAll(this.tree.getAncestors(tn));
			}

			renderTree(out, this.tree.getRoot(), sq, _propertyName, colCheckedOnes, colAncestorsToBOpen);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("e");
		}
	}

	public void renderTree(javax.servlet.jsp.JspWriter out, TreeNode root, Sequence sq, String _propertyName, Collection _colCheckedOnes,
			Collection colAncestorsToBOpen)
	{
		{
			try
			{
				int _sq = sq.next();

				TreeNode treeNode = root;

				Collection colFindResult = this.tree.getFindresult();
				Collection colFindAncestor = this.tree.getFindAnscestor();

				System.out.println("colAncestorsToBOpen:  " + colAncestorsToBOpen);
				String checked = "";

				if (colFindResult.contains(treeNode))
				{
					out.write("\n<table border='0' cellpadding='1' cellspacing='1' width='100%'>\n" + "	\t<tr id='" + root.getValue().trim()
							+ "' bgColor='blue'>\n" + "\t\t<td  >\n");
				}
				else
				{
					out.write("\n<table border='0' cellpadding='1' cellspacing='1' width='100%'>\n" + "	\t<tr id='" + root.getValue().trim()
							+ "' bgColor='#EBEBEB'>\n" + "\t\t<td  >\n");
				}
				if (root.getSubtree().size() == 0)
				{
					lb.setLabel(root.getValue());
					out.write("\t\t\t<span id='xnode" + _sq + "'  style='cursor:pointer;' class='tdfont'>\n ");
					out.write("\t\t\t\t<img src='../hisglobal/images/leaf.gif'>\n");
					// out.write("\t\t<html:checkbox name='"+_beanName + " property='"+_propertyName+"'id
					// ='"+root.getValue().trim()+"' value='"+root.getValue().trim()+"' onClick=changeColor(this)>\n");
					// for(int i=0;i<_arrProp.length;i++){
					// System.out.println("root.getValue().trim()"+root.getValue().trim());
					// System.out.println("_arrProp"+_arrProp[i]);
					// if(root.getValue().trim().equalsIgnoreCase(_arrProp[i])){
					if (_colCheckedOnes.contains(treeNode))
					{
						System.out.println("indside checked" + treeNode.getValue());
						checked = "checked";
					}
					// }
					// }
					out.write("\t\t<input type='checkbox' name='" + _propertyName + "'" + checked + " id='" + root.getValue().trim() + "' value='"
							+ root.getValue().trim() + "' onClick=changeColor(this)>\n");
					out.write("<b>" + lb.getLabel() + "</b>");
					out.write("\t\t</span>");
				}
				else
				{
					out.write("\t\t\t<span id='xnode" + _sq + "'  style='cursor:pointer;' onClick=" + this.tree.getTreeName().trim()
							+ ".toggle('node" + _sq + "')>\n ");
					out.write("\t\t\t\t<img src='../hisglobal/images/plus.gif'>\n");
					out.write("\t\t\t</span>\n" +

					"\t\t\t<b>" + treeNode.getValue() + "</b>\n" +

					"\t\t</td>\n" + "\t</tr>\n" +

					"\t<tr>\n" + "\t<td colspan='2'>\n");
					System.out.println("node value" + root.getValue());
					// System.out.println("_tree.findAncestor"+_tree.findAncestor);
					if (colAncestorsToBOpen.contains(treeNode))
					{
						System.out.println("Open division");
						out.write("\t\t<div id='node" + _sq + "' style='display:block; margin-left: 2em;'>\n");
					}
					else out.write("\t\t<div id='node" + _sq + "' style='display:none; margin-left: 2em;'>\n");
				}

				div1.add("node" + _sq);
				// preorderData.add(root.getValue());

				if (root.getSubtree().size() == 0)
				{
					out.write("\t\t</div>\n" +

					"\t</td>\n" + "\t</tr>\n" + "</table>\n");
					div.add("node" + _sq);
					postorderData.add(root.getValue());
					return;
				}
				Iterator itr = root.getSubtree().iterator();

				while (itr.hasNext())
				{
					renderTree(out, (TreeNode) itr.next(), sq, _propertyName, _colCheckedOnes, colAncestorsToBOpen);
				}

				// Adding
				div.add("node" + _sq);
				postorderData.add(root.getValue());

				out.write("\t\t</div>\n" +

				"\t</td>\n" + "\t</tr>\n" + "</table>\n");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		/*
		 * this.setPreorderTraversalQue(div1); this.setPostorderTraversalQue(div); this.setPostorderData(postorderData);
		 */
	}

}//end of class
