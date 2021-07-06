package hisglobal.tools;

import hisglobal.persistence.ResultSet;
import hisglobal.tools.RSTree;

public class MyExpTree
{
	public TreeNode callIt()
	{

		TreeNode root = null;

		TreeData dd = new TreeData();

		try
		{

			System.out.println("the resultset is:" + dd.execute());
			ResultSet rs = new ResultSet(dd.execute(), 3);
			System.out.println("the size of rs is:" + rs.rs.size());
			RSTree tr = new RSTree(rs, 3);
			tr.buildTree();
			root = tr.getTreeRoot();

		}
		catch (Exception e)
		{
			System.out.println("MyExpTree: " + e);
			e.printStackTrace();
		}

		return root;
	}

}
