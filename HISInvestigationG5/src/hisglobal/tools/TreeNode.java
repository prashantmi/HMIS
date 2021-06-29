package hisglobal.tools;

import java.util.*;

public class TreeNode
{
	private String value = "";
	private Collection subtree = new ArrayList();
	private String url = "";

	public Collection getSubtree()
	{
		return subtree;
	}

	public void setSubtree(Collection subtree)
	{
		// System.out.println("Subtree is set for : "+this.value);
		this.subtree = subtree;
	}

	public java.lang.String getValue()
	{
		return value;
	}

	public void setValue(java.lang.String value)
	{
		this.value = value;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void addChild(TreeNode _child)
	{
		subtree.add(_child);
	}

	public String toString()
	{
		return value;
	}

	public boolean equals(Object o)
	{
		// System.out.println("treeNOde.equals: "+o);
		TreeNode tn = (TreeNode) o;
		if (o == null)
		{
			// System.out.println("treeNOde.equals: is Null");
			return false;
		}
		if (this == o)
		{
			System.out.println("treeNOde.equals:  same object");
			return true;
		}
		// System.out.println("treeNOde.equals: '"+tn.getValue()+"' & this.value: '"+this.getValue()+"'");
		return (tn.getValue().equals(this.getValue()));
	}

	public int hashCode()
	{
		System.out.println("treeNOde.hashCode:  ");
		return this.getValue().hashCode();
	}
}
