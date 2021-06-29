package startup.tree;

import java.util.*;

public class RSTree extends Tree {
	ResultSet rs;
	int noOfKeys;
	int noOfCols;
	public RSTree()
	{
		
	}
	public RSTree(ResultSet _rs, int _noOfKeys) throws Exception{// by default the index of the keys in the resultset are in ascending order starting frm 1
		int noOfColsInRs = _rs.getMetaData().getColumnCount();
		//System.out.println("noOfColsInRs is "+noOfColsInRs);
		//System.out.println("_noOfKeys is "+_noOfKeys);
		this.noOfCols = noOfColsInRs;
		if(noOfColsInRs<_noOfKeys)
			throw new IllegalArgumentException("noOfColsInRs<_noOfKeys");
		this.rs = _rs;
		this.noOfKeys = _noOfKeys;
	}
	
	public void buildTree(ArrayList _resultSetArrayList) throws Exception{
		super.root = new TreeNode();
		ArrayList colTreeState = new ArrayList();
		colTreeState.add(root);
		//System.out.println("inside RStree java file is "+this.noOfKeys);
		//////////The loop iterates for the depth of tree///Current depth of menu tree is 6////08/12/08
		for(int i=0; i<6; i++)
			colTreeState.add(null);
		this.buildTree(colTreeState,_resultSetArrayList);
	}
	
	public TreeNode getTreeRoot(){
		return super.root;
	}
	
	protected void buildTree(ArrayList _state,ArrayList _resultSetArrayList) throws Exception
	{
		ArrayList newState = null;
		newState = new ArrayList();
		String str="";
		TreeNode tn = null;
		for(int m=0;m<_resultSetArrayList.size();m++)
		{
			ArrayList al=(ArrayList)_resultSetArrayList.get(m);
			
			for (int n=0;n<al.size()-1;n++)
				{
				try
				{
					str = (String)al.get(n);
					
				}
				catch(Exception e)
				{e.printStackTrace();}
				
				if(_state.get(n+1) == null || !((TreeNode)_state.get(n+1)).getValue().equals(str))
				{
					//System.out.println("tree node"+" "+n+" "+((TreeNode)_state.get(n)).getValue());
					//System.out.println("str is "+" "+n+" "+str);
					tn = new TreeNode();
					if(n+2 == al.size())
					{
						tn.setUrl((String)al.get(al.size()-1));
					}
					
					tn.setValue(str);
					((TreeNode) _state.get(n)).addChild(tn);
					//Now change the current State to this new state
					newState = new ArrayList();
					//add all the same nodes
					int j;
					for(j=0; j<=n; j++)
						newState.add(_state.get(j));
					newState.add(tn);
					
					//make others null so that will result in a new entry later on
					for(++j; j<_state.size(); j++)						
						newState.add(null);
				
					_state = newState;
				}
				//this.buildTree(newState);
				//System.out.println("\t_state in the loop: "+_state);
			}
		}
	}
}
