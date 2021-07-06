package hisglobal.tools;

import java.util.*;

public class Tree
{
	protected TreeNode root;
	protected String treeName;
	protected Collection findresult = new ArrayList();
	protected Collection findAncestor = new ArrayList();
	protected TreeQue preorderTraversalQue = new TreeQue();
	public List postData = new ArrayList();
	protected TreeQue postorderTraversalQue = new TreeQue();

	public Tree()
	{
	}

	public Tree(TreeNode root)
	{
		this.root = root;
	}

	public java.util.List getPostData()
	{
		return postData;
	}

	public void setPostData(java.util.List postData)
	{
		this.postData = postData;
	}

	public TreeQue getPostorderTraversalQue()
	{
		return postorderTraversalQue;
	}

	public void setPostorderTraversalQue(TreeQue postorderTraversalQue)
	{
		this.postorderTraversalQue = postorderTraversalQue;
	}

	public TreeQue getPreorderTraversalQue()
	{
		return preorderTraversalQue;
	}

	public void setPreorderTraversalQue(TreeQue preorderTraversalQue)
	{
		this.preorderTraversalQue = preorderTraversalQue;
	}

	public void setTreeName(java.lang.String treeName)
	{
		this.treeName = treeName;
	}

	public java.lang.String getTreeName()
	{
		return treeName;
	}

	public void setRoot(TreeNode root)
	{
		this.root = root;
	}

	public TreeNode getRoot()
	{
		return root;
	}

	public TreeNode findFirst(String valSearch) throws Exception
	{

		TreeNode tn = new TreeNode();
		postorderTraversalQue.refreshQueue();
		while (postorderTraversalQue.hasNextInQueue())
		{

			tn = postorderTraversalQue.nextInQueue();
			String tnValue = tn.getValue();
			if (tnValue.toUpperCase().indexOf(valSearch.toUpperCase()) != -1)
			{

				break;
			}
		}

		return tn;
	}

	public TreeNode findNext(String valSearch) throws Exception
	{
		TreeNode tn = null;

		while (postorderTraversalQue.hasNextInQueue())
		{
			tn = postorderTraversalQue.nextInQueue();
			String tnValue = tn.getValue();
			if (tnValue.toUpperCase().indexOf(valSearch.toUpperCase()) != -1)
			{
				break;
			}
		}

		return tn;
	}

	public Collection findAll(String valSearch) throws Exception
	{

		TreeNode tn = null;
		Collection tnSet = new ArrayList();
		postorderTraversalQue.refreshQueue();
		while (postorderTraversalQue.hasNextInQueue())
		{

			tn = postorderTraversalQue.nextInQueue();
			//System.out.println("postorderTraversalQue.nextInQueue():  "+"findAll(String valSearch)");
			String tnValue = tn.getValue();
			if (tnValue.toUpperCase().indexOf(valSearch.toUpperCase()) != -1)
			{
				tnSet.add(tn);

				continue;
			}
		}

		return tnSet;
	}

	public Collection getAncestors(TreeNode _tn) throws Exception
	{
		int idxInPreQ = this.preorderTraversalQue.searchInQueue(_tn);
		int idxInPostQ = this.postorderTraversalQue.searchInQueue(_tn);
		System.out.println("preorderTraversalQue:  " + preorderTraversalQue.getQueueSize());
		System.out.println("postorderTraversalQue: " + postorderTraversalQue.getQueueSize());
		if (_tn == this.root) return new ArrayList();
		System.out.println("getAncestors(TreeNode _tn): idxInPreQ - " + idxInPreQ);
		System.out.println("getAncestors(TreeNode _tn): idxInPostQ - " + idxInPostQ);
		if (idxInPreQ == -1 && idxInPostQ == -1) throw new IllegalArgumentException("NODE Not Found Exception- Node: " + _tn);

		if (idxInPreQ == -1 || idxInPostQ == -1) throw new IllegalArgumentException("Synchronization Exception: Que\'s arenot Sysnchronized");

		TreeQue qPostSubQue = new TreeQue();
		//get sublist of postOrderQueue from the cursor position + onward
		qPostSubQue = this.postorderTraversalQue.getSubQueue(idxInPostQ, this.postorderTraversalQue.getQueueSize());

		TreeStack stkNodeOrder = new TreeStack();

		while (qPostSubQue.hasNextInQueue())
		{
			TreeNode strNodeName = null;
			strNodeName = qPostSubQue.nextInQueue();

			int presIdxInPreQ = this.preorderTraversalQue.searchInQueue(strNodeName);
			if (presIdxInPreQ == -1) throw new IllegalArgumentException("Synchronization Exception: Que\'s arenot Sysnchronized");
			if (presIdxInPreQ <= idxInPreQ)
			{
				System.out.println("getAncestors(TreeNode _tn): presIdxInPreQ<=idxInPreQ");
				stkNodeOrder.pushIntoStack(strNodeName);
			}
		}

		return stkNodeOrder.array;
	}

	public Collection getAllAncestors(Collection _tnNode) throws Exception
	{
		Collection colOfTreeNode = new HashSet();
		Iterator it = _tnNode.iterator();
		System.out.println("getAllAncestors(Collection _tnNode)~ _tnNode.size():  " + _tnNode.size());
		while (it.hasNext())
		{
			System.out.println("getAllAncestors(Collection _tnNode) element:  inside the loop.. ");
			TreeNode tmpNode = (TreeNode) it.next();
			System.out.println("getAllAncestors(Collection _tnNode) element:  " + tmpNode);
			Collection tempCol = this.getAncestors(tmpNode);
			colOfTreeNode.addAll(tempCol);
		}
		return colOfTreeNode;
	}

	//inner Class
	public class TreeStack
	{
		protected List array = new ArrayList();
		protected int top = -1;

		//functions used in Stack class
		public void pushIntoStack(TreeNode value) throws Exception
		{
			try
			{
				if (value == null) throw new IllegalArgumentException("Illegal Argument Exception Value" + value);
				++top;
				this.array.add(value);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		}

		public TreeNode popOutOfStack() throws Exception
		{
			if (this.top < 0) return null;
			return (TreeNode) this.array.get(this.top--);
		}

		public boolean isEmptyStack()
		{
			if (this.top < 0) return true;
			return false;
		}

		public void refreshStack()
		{
			this.top = this.array.size() - 1;

		}

		public void mergeStack(TreeStack _stack) throws Exception
		{

			//code for merging the stack comes here
			TreeNode node = new TreeNode();
			if (_stack == null) return; ///<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			//alert("the size of stack is:"+_stack.size() + "stack.toString(): "+_stack.toString());
			//alert("the length of array in current stack is: "+this.array.length+ "mergestack.toString():  "+ this.toString());
			_stack.refreshStack();
			while ((node = _stack.popOutOfStack()) != null)
			{
				//alert("Node merge:  "+node);
				if (this.searchIntoStack(node) == -1)
				{
					this.pushIntoStack(node);
				}
			}
		}

		public int searchIntoStack(TreeNode _value)
		{
			for (int i = 0; i < this.array.size(); i++)
			{
				if (this.array.get(i).equals(_value))
				{
					return i;
				}
			}
			return -1;
		}

		public int sizeStack()
		{
			if (this.top == -1) System.out.println("the stack is empty");
			return this.array.size();
		}

	}

	public class TreeQue
	{
		protected int cursor = -1;
		protected List array = new ArrayList();
		protected Map mapQueue = new HashMap();

		//functions used in Queue Class
		public TreeNode nextInQueue() throws Exception
		{
			try
			{
				if (this.isQueueEmpty() || this.cursor >= this.array.size() - 1) throw new IndexOutOfBoundsException("Index Out Of Bound Exception");

				this.cursor++;
				return (TreeNode) this.array.get(this.cursor);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		}

		public boolean isQueueEmpty() throws Exception
		{
			if (this.array.size() == 0) return true;
			return false;
		}

		public String getAtIdxInQueue(int idx) throws Exception
		{
			try
			{
				if (idx < 0) throw new IllegalArgumentException("Illegal Argument Exception Value" + idx);
				if (this.isQueueEmpty() || idx > this.array.size() - 1) return null;
				return (String) this.array.get(idx);

			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		}

		public int getQueueSize() throws Exception
		{
			return this.array.size();
		}

		public TreeQue getSubQueue(int frmidx, int lastidx) throws Exception
		{
			try
			{
				if (frmidx < 0 || lastidx < 0 || frmidx >= this.getQueueSize()) throw new IllegalArgumentException(
						"Illegal Argument Exception - frmIdx:" + frmidx);

				if (lastidx < 0) lastidx = this.array.size();
				if (lastidx > this.array.size()) lastidx = this.array.size();

				TreeQue subQ = new TreeQue();

				for (int i = frmidx; i < lastidx; i++)
				{
					subQ.appendInQueue((TreeNode) this.array.get(i));
				}
				return subQ;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		}

		public void appendInQueue(TreeNode _root) throws Exception
		{
			try
			{
				if (_root == null) throw new IllegalArgumentException("Illegal Argument Exception value: " + _root);
				this.mapQueue.put(_root, Integer.valueOf(this.array.size()));
				this.array.add(_root);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		}

		public boolean hasNextInQueue() throws Exception
		{
			if (this.cursor + 1 == this.array.size()) return false;
			return true;
		}

		public int searchInQueue(TreeNode value) throws Exception
		{
			try
			{
				if (value == null) throw new IllegalArgumentException("Illegal Argument Exception value: " + value);

				/*for(int i=0; i<this.array.size(); i++){
					if(this.array.get(i).equals(value)){
						return (i);
					}
				}*/
				//System.out.println("map:  "+this.mapQueue);
				//System.out.println("treeNode search:  "+ value.getValue());
				if (this.mapQueue.containsKey(value)) return ((Integer) this.mapQueue.get(value)).intValue();
				return -1;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		}

		public void refreshQueue()
		{
			this.cursor = -1;
		}

		public void clearQueue()
		{
			this.cursor = -1;
			this.array = new ArrayList();
		}
	}

	public Collection getFindAnscestor()
	{
		return findAncestor;
	}

	public void setFindAnscestor(Collection findAnscestor)
	{
		this.findAncestor = findAnscestor;
	}

	public Collection getFindresult()
	{
		return findresult;
	}

	public void setFindresult(Collection findresult)
	{
		this.findresult = findresult;
	}

	public void setFindState(String valSearch) throws Exception
	{

		//TreeNode tn=new TreeNode();
		this.findresult.clear();
		this.findresult.add(this.findFirst(valSearch));

		System.out.println("findresult" + findresult);
		System.out.println("findAncestor:  " + findAncestor);
		this.findAncestor.clear();
		this.findAncestor.addAll(this.getAllAncestors(this.findresult));
		System.out.println("findAncestor:  " + findAncestor);

	}
}
