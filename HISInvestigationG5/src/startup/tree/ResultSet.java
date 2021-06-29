package startup.tree;

import java.util.ArrayList;
import javax.sql.rowset.WebRowSet;

public class ResultSet {
	int noOfCols;
	int cursor = -1;
	ArrayList rs = new ArrayList();
	
	public ResultSet(ArrayList _rs, int _noOfCols)
	{		
		this.noOfCols = _noOfCols;
		this.rs  = _rs;
		//for(int k=0;k<rs.size();k++)
			//System.out.println("rs("+k+") is "+rs.get(k));
	}
	
	public ResultSet(WebRowSet _rs,int _noOfCols)
	{
		try
		{
			while(_rs.next())
			{
				ArrayList al = new ArrayList();
				for(int i=0;i<_noOfCols;i++)
				{
					//System.out.println("_rs.getString(i+1):  "+_rs.getString(i+1));
					al.add(_rs.getString(i+1));
				}	
				this.rs.add(al);
			}
			this.noOfCols = _noOfCols;
		}
		catch(Exception e)
		{
			System.out.println("error is : "+e);
		}
		
	}
	public ResultSet getMetaData(){
		return this;
	}
	public int getColumnCount(){
		return noOfCols;
	}
	
	public boolean next(){
		this.cursor++;
		if(this.cursor<rs.size()){
			//this.cursor = this.cursor +1;
			//System.out.println("cursor incremented: "+cursor);
			return true;
		}
		return false;
	}
	
	public String getString(int idx){
		String temp = "";
		int tmp = idx-1;
		if(!(this.cursor<rs.size()))
			throw new IndexOutOfBoundsException("");
		//if(tmp >=((ArrayList)rs.get(this.cursor)).size())
			//return "";
		if( idx<=noOfCols+1){
			if(((ArrayList)rs.get(this.cursor)).get(tmp) == null)
			{
				return "";
			}
			else
			{
			//System.out.println("((ArrayList)rs.get(this.cursor)).get(idx-1): "+((ArrayList)rs.get(this.cursor)).get(idx-1).getClass());
			temp = (String)((ArrayList)rs.get(this.cursor)).get(tmp); 
		//	System.out.println("temp is "+temp);
			if( temp == null || temp.equals("") || temp.equals("null"))
				return "";
			else
			{
				return (String)((ArrayList)rs.get(this.cursor)).get(tmp);
			}
			}
		}else
			throw new IndexOutOfBoundsException("");
	}
	
	public void add(ArrayList _al){
		rs.add(_al);
	}
	
	
}
