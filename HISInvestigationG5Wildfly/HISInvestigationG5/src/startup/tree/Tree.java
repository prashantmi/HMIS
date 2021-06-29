package startup.tree;

public class Tree {
	protected TreeNode root;
	protected String treeName;
	


	public void setTreeName( java.lang.String treeName )
	{
		this.treeName = treeName;
	}

	public java.lang.String getTreeName( )
	{
		return treeName;
	}

	public void setRoot(TreeNode root )
	{
		this.root = root;
	}
	public TreeNode getRoot( )
	{
		return root;
	}
}
