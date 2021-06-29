package hisglobal.transactionmgmnt;

/*
Developed By          : Partha P Chattaraj
Creation Dated        : 17-06-2006
Modification Dated    : 07-05-2008
Version               : HIMS 2.0

*/
import java.util.*;


public class Block extends Query
{
	private int blockNo;
	private Query[] query = null;
	private List blockValue = null;
	private int noOfQuery   = 0;

	public Block(){}
	public Block(int noOfQry,List blockQuery,List blockQuerySize,int _blockNo)
	{
		this.noOfQuery = noOfQry;
		this.blockNo = _blockNo;
		query = new Query[noOfQry];
		blockValue = new ArrayList();
		initializeQueryObject(blockQuery,blockQuerySize);
	}
	private void initializeQueryObject(List blockQuery,List blockQuerySize)
	{
		int i;
		int size;
		String qry;
		for(i=0;i<blockQuery.size();i++)
		{
			size = Integer.parseInt((String)blockQuerySize.get(i));
			qry  = (String)blockQuery.get(i);
			query[i] = new Query(size,qry);
		}
	}
	public int getBlockNo(){return this.blockNo;}
	public int getNoOfQuery(){return this.noOfQuery;}
	public Query getQuery(int i){return this.query[i];}
	public Query[] getQuery(){return this.query;}

	public void addQuery(Query q1)
	{
		System.out.println("inside add query");
		blockValue.add(q1);
	}

	public boolean checkBlockBeforeAdd()
	{
		int i;
		boolean retVal = true;
		System.out.println("quer length==="+this.query.length);
		for(i=0;i<this.query.length;i++)

		{
			System.out.println("query[i].getAL_ListVal().size()==="+query[i].getAL_ListVal().size());

			if(query[i].getAL_ListVal().size() <=0)
				retVal = false;
		}

		return retVal;
	}
	public List getBlockValue() {
		return blockValue;
	}
	public void setBlockValue(List blockValue) {
		this.blockValue = blockValue;
	}
}