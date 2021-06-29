package hisglobal.tools;

import hisglobal.persistence.ResultSet;

import java.util.*;

public class RSTree extends Tree
{
	List postorderData = new ArrayList();

	java.sql.ResultSet rs;




	int noOfKeys;
	int noOfCols;

	public RSTree()
	{

	}


	
	///Constructor for his resultset
	public RSTree(java.sql.ResultSet _rs, int _noOfKeys) throws Exception
	{// by default the index of the keys in the resultset are in ascending order starting frm 1
		int noOfColsInRs = _rs.getMetaData().getColumnCount();
		this.noOfCols = noOfColsInRs;
		if (noOfColsInRs < _noOfKeys) throw new IllegalArgumentException("noOfColsInRs<_noOfKeys");
		this.rs = _rs;
		this.noOfKeys = _noOfKeys;
	}
	
	
	///Constructor for java util resultset
	/*public RSTree(java.sql.ResultSet _rs, int _noOfKeys) throws Exception
	{// by default the index of the keys in the resultset are in ascending order starting frm 1
		int noOfColsInRs = _rs.getMetaData().getColumnCount();
		this.noOfCols = noOfColsInRs;
		if (noOfColsInRs < _noOfKeys) throw new IllegalArgumentException("noOfColsInRs<_noOfKeys");
		this.rs1 = _rs;
		this.noOfKeys = _noOfKeys;
	}
*/
	public Tree buildTree() throws Exception
	{
		super.root = new TreeNode();
		ArrayList colTreeState = new ArrayList();
		colTreeState.add(root);
		for (int i = 0; i < this.noOfKeys; i++)
			colTreeState.add(null);
		this.buildTree(colTreeState);
		this.setTreeData(this.root);
		System.out.println("super.postorderTraversalQue.getQueueSize()" + super.postorderTraversalQue.getQueueSize());
		System.out.println("super.postorderTraversalQue.getQueueSize()" + super.preorderTraversalQue.getQueueSize());
		System.out.println("super.postData.size()" + super.postData.size());
		return this;
	}

	public TreeNode getTreeRoot()
	{
		return super.root;
	}

	protected void buildTree(ArrayList _state) throws Exception
	{
		ArrayList newState = new ArrayList();
		String str = "";
		while (rs.next())
		{
			// System.out.println("_state: "+_state.toString());
			// System.out.println("the size of rs in while loop is:"+rs.rs.size());
			for (int i = 0; i < this.noOfCols; i++)
			{
				try
				{
					// System.out.println("rs.getString(i+1): "+rs.getString(i+1).getClass());
					str = rs.getString(i + 1);

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				// System.out.println("current element: "+str+".....");
				if (_state.get(i + 1) == null || !((TreeNode) _state.get(i + 1)).getValue().equals(str))
				{
					// System.out.println("State is not equal or null..."+_state.get(i+1));
					TreeNode tn = new TreeNode();
					tn.setValue(str);
					((TreeNode) _state.get(i)).addChild(tn);
					// Now change the current State to this new state
					newState = new ArrayList();
					// add all the same nodes
					int j;
					for (j = 0; j <= i; j++)
						newState.add(_state.get(j));
					newState.add(tn);
					// System.out.println("tn: "+tn);
					// make others null so that will result in a new entry later on
					for (++j; j < _state.size(); j++)
						newState.add(null);

					_state = newState;
				}
				// this.buildTree(newState);
				// System.out.println("\t_state in the loop: "+_state);
			}
		}
	}

	public void setTreeData(TreeNode _root) throws Exception
	{
		try
		{
			// Sequence sq = new Sequence();
			setTreeQueues(_root);
		}
		catch (Exception e)
		{
			System.out.println("e");
		}
	}

	public void setTreeQueues(TreeNode root) throws Exception
	{
		// int _sq = sq.next();
		System.out.println("the size of que:" + super.preorderTraversalQue.getQueueSize());
		try
		{
			preorderTraversalQue.appendInQueue(root);

			if (root.getSubtree().size() == 0)
			{
				super.postorderTraversalQue.appendInQueue(root);
				this.postorderData.add(root.getValue());
				return;
			}

			// preorderData.add(root.getValue());

			System.out.println("inside set data");
			Iterator itr = root.getSubtree().iterator();

			while (itr.hasNext())
			{

				setTreeQueues((TreeNode) itr.next());

			}

			// Adding
			postorderTraversalQue.appendInQueue(root);
			this.postorderData.add(root.getValue());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		super.setPostData(this.postorderData);
	}

}
