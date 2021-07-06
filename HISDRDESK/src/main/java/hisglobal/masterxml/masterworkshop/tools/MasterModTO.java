package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;

public class MasterModTO extends MasterTO
{
	MasterModDtl objMstModDtl;
	String chk[];//will keep the primary key values selected on list page
	//taken as array beacause of possibility of a composite primary key
	ArrayList alInitialQryResult = new ArrayList();//will keep the initialise query result

	public java.util.ArrayList getAlInitialQryResult()
	{
		return alInitialQryResult;
	}

	public void setAlInitialQryResult(java.util.ArrayList alInitialQryResult)
	{
		this.alInitialQryResult = alInitialQryResult;
	}

	public java.lang.String[] getChk()
	{
		return chk;
	}

	public void setChk(java.lang.String[] chk)
	{
		this.chk = chk;
	}

	//ArrayList alPkValue=new ArrayList();  

	public hisglobal.masterxml.masterworkshop.tools.MasterModDtl getObjMstModDtl()
	{
		return objMstModDtl;
	}//for which the modification is carried out    

	public void setObjMstModDtl(MasterModDtl objMstModDtl)
	{
		this.objMstModDtl = objMstModDtl;
	}

}
