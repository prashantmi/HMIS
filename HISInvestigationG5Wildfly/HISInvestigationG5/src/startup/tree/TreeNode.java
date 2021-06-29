package startup.tree;

import java.util.*;

public class TreeNode {
	private String value ="";
	private Collection subtree = new ArrayList();
	private String url = "";
	
	public Collection getSubtree() {
		return subtree;
	}
	public void setSubtree(Collection subtree) {
		System.out.println("Subtree is set for : "+this.value);
		this.subtree = subtree;
	}

	public java.lang.String getValue( ) {
		return value;
	}

	public void setValue( java.lang.String value ) {
		this.value = value;
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void addChild(TreeNode _child)
	{
		subtree.add(_child);	
	}
	public String toString(){
		return value;
	}
}
