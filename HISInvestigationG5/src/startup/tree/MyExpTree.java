package startup.tree;

import hisglobal.persistence.JDBCTransactionContext;

import java.util.ArrayList;



public class MyExpTree 
{
	public TreeNode callIt(String userId,String seatId,String hospitalCode)
	{	
		TreeNode root = null;
		JDBCTransactionContext tx=new JDBCTransactionContext();
		treeData dd=new treeData(tx);
		try
		{
			tx.begin();
			ArrayList resultSetArrayList=dd.execute(userId,seatId,hospitalCode);
			ResultSet rs = new ResultSet(resultSetArrayList,3);
			RSTree tr = new RSTree(rs, 3);
			tr.buildTree(resultSetArrayList);
			
			root = tr.getTreeRoot();
		}
		catch(Exception e)
		{
			//System.out.println("MyExpTree: "+e); 
			e.printStackTrace();
		tx.rollback();
		}finally{
			tx.close();
		}
			
		return root;
	}
	
}
