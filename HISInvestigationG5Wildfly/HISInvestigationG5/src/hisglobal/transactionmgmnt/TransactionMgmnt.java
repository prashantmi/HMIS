package hisglobal.transactionmgmnt;

/*
Developed By          : Partha P Chattaraj
Creation Dated        : 17-06-2006
Modification Dated    : 07-05-2008
Version               : HIMS 2.0

*/

import java.sql.*;
import java.util.*;

public class TransactionMgmnt extends hisglobal.backutil.HisMethods
{
	private int totBlock;
	private int totQuery;
	private String transMgt = "";
	private int[] blocktotQuery;
	private String[] blockRowsIns;
	private String[] blockMandatory;
	private List[] blockQuery = null;
	private List[] blockQueryColSize = null;
	private PreparedStatement ps = null;
	private Connection conn = null;

	private List blockList = null;

	public TransactionMgmnt(){}
	public TransactionMgmnt(String xmlFilePath,String mode)
	{
		readXmlFile(xmlFilePath,mode);
		blockList = new ArrayList();
	}

	public void readXmlFile(String xmlFilePath,String mode)
	{
		try
		{
			hisglobal.transactionmgmnt.xmlTest.readXML xmlObj = new hisglobal.transactionmgmnt.xmlTest.readXML();
			xmlObj.getValueOfXml(xmlFilePath,mode);
			this.totBlock = xmlObj.getTotBlock();
			this.totQuery = xmlObj.getTotQuery();
			this.transMgt = xmlObj.getTransMgt();
			this.blocktotQuery = xmlObj.getBlocktotQuery();
			this.blockRowsIns = xmlObj.getBlockRowsIns();
			this.blockMandatory = xmlObj.getBlockMandatory();
			this.blockQuery = xmlObj.getBlockQuery();
			this.blockQueryColSize = xmlObj.getBlockQueryColSize();
		}
		catch(Exception e)
		{
			System.out.println("ERROR IS "+e);
			e.printStackTrace();
		}
	}

	public Block start(int blockNo)
	{
		int noOfQry = (int)blocktotQuery[blockNo];
		Block objBlock = new Block(noOfQry,blockQuery[blockNo],blockQueryColSize[blockNo],blockNo);
		return objBlock;
	}

	public void addBlock(Block b1)throws Exception
	{
		StringTokenizer st = null;
		String temp = "";
		if(b1.checkBlockBeforeAdd())
		{
			st = new StringTokenizer(blockMandatory[b1.getBlockNo()],"^");
			temp = st.nextToken() +"^"+"1";
			blockMandatory[b1.getBlockNo()] = temp;
			blockList.add(b1);
		}
		else
		{
			System.out.println("Add all Queries of Block");
			throw new Exception("Add All Queries");
		}
	}

	public boolean checkMandatoryBlock()
	{
		boolean ret = true;
		int i;
		StringTokenizer st = null;
		for(i=0;i<this.blockMandatory.length;i++)
		{
			st = new StringTokenizer(blockMandatory[i],"^");
			if(st.nextToken().equals("Y") && st.nextToken().equals("0"))
			{
				ret = false;
				break;
			}
		}
		return ret;
	}

	public boolean fire()throws Exception
	{
		System.out.println("insdie fire block");
		int i,j,k,m;
		List valList = null;
		String val[];
		String str = "";
		boolean retVal = true;
		if(checkMandatoryBlock())
		{
			try
			{
				if(transMgt.equals("Y"))///////in case transaction magement is for all block
				{
					try
					{
						conn = super.getConnection();
						conn.setAutoCommit(false);
						System.out.println("conn is open");
						if(blockList != null)
						{
							for(i=0;i<this.blockList.size();i++)
							{
								Block blckObj = (Block)blockList.get(i);
								Query[] qryObj = blckObj.getQuery();
								for(j=0;j<qryObj.length;j++)
								{
									str = qryObj[j].getQry();
									System.out.println("str si "+str);
									valList = qryObj[j].getAL_ListVal();
									System.out.println("size of vallist is "+valList.size());
									ps = conn.prepareStatement(str);
									for(k=0;k<valList.size();k++)
									{
										System.out.println("inside for k is "+k);
										val = (String[])valList.get(k);
										System.out.println("val is "+val.length);
										for(m=0;m<val.length;m++)
											ps.setString(m+1,val[m]);
										System.out.println("before exec");
										ps.execute();
										System.out.println("after exec");
									}
									ps.close();
								}
							}
						}
					}
					catch(Exception e)
					{
						retVal = false;
						System.out.println("error "+e);
						throw e;
					}
					finally
					{
						if(retVal)
						{
							conn.commit();
							conn.close();
							System.out.println("conn is commited and closed");
						}
						else
						{
							conn.rollback();
							conn.close();
							System.out.println("conn is rollbacked and closed");
						}
					}
				}
				else // in case transaction mgt is per block
				{
					if(blockList != null)
					{
						for(i=0;i<this.blockList.size();i++)
						{
							try
							{
								conn = super.getConnection();
								conn.setAutoCommit(false);
								System.out.println("conn is open");
								Block blckObj = (Block)blockList.get(i);
								Query[] qryObj = blckObj.getQuery();
								for(j=0;j<qryObj.length;j++)
								{

									str = qryObj[j].getQry();
									valList = qryObj[j].getAL_ListVal();
									ps = conn.prepareStatement(str);
									for(k=0;k<valList.size();k++)
									{
										System.out.println("INSIDE FOR BLOCK On The Page Of TransactionMgmnt New LINE:-188 Written By:-Partha P Chattaraj");
										val = (String[])valList.get(k);
										for(m=0;m<val.length;m++)
											ps.setString(m+1,val[m]);
										ps.execute();
									}
									ps.close();
								}
							}
							catch(Exception e)
							{
								retVal = false;
								System.out.println("error is "+e);
								throw e;
							}
							finally
							{
								if(retVal)
								{
									conn.commit();
									conn.close();
									System.out.println("conn is commited and closed");
								}
								else
								{
									conn.rollback();
									conn.close();
									System.out.println("conn is rollbacked and closed");
								}
							}
						}
					}
				}
			}
			catch(Exception e)
			{
				retVal = false;
				System.out.println("error in Transaction Management file is  "+e);
				throw e;
			}
			finally
			{
				ps = null;
				conn = null;
			}
		}
		else
		{
			System.out.println("Add all Mandatory Blocks");
			throw new Exception("All Mandatory Block Not Added");
		}
		return retVal;
	}


}
