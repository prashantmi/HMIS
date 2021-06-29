package hisglobal.transactionmgmnt;

/*
Developed By          : Partha P Chattaraj
Creation Dated        : 17-06-2006
Modification Dated    : 07-05-2008
Version               : HIMS 2.0

*/


import java.util.*;

public class Query
{
	private String qry   = "";
	private String[] val = null;
	private List AL_ListVal = null;
	private int cnt;

	public String[] getVal(){return this.val;}
	public String getQry(){return this.qry;}
	public List getAL_ListVal(){return this.AL_ListVal;}

	public void setCnt(int i){this.cnt = i;}

	public Query(){}
	public Query(int size,String q1)
	{
		System.out.println("query object is created");
		System.out.println("size of query is "+size);
		this.qry = q1;
		val = new String[size];
		cnt = 0;
		this.AL_ListVal = new ArrayList();
	}

	public void setVal(String str)
	{
		System.out.println("inside setval function");
		val[cnt] = str;
		cnt++;
		if(cnt == this.val.length)
		{
			AL_ListVal.add(this.val);
			val = null;
			val = new String[cnt];
			cnt = 0;
		}
		System.out.println("end of it");
	}
}