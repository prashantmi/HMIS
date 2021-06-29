package mrd.masters.controller.data;

import java.util.List;
import java.util.Map;

import mrd.masters.delegate.MrdEssentialDelegate;
import mrd.masters.delegate.MrdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.RecordBoundingVO;
import hisglobal.vo.UserVO;

public class RecordBoundingDATA extends ControllerDATA
{
	public static Map getMrdBoundingEssential(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getMrdBoundingEssential(userVO);
	}
	
	public static List getBoundedRecordType(String boundingMode,String boundingId,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getBoundedRecordType(boundingMode,boundingId,userVO);
	}
	
	public static List getRackBasedOnMrd(String mrdCode,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getRackBasedOnMrd(mrdCode,userVO);
	}
	public static List getShelfBasedOnRack(String rackId,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getShelfBasedOnRack(rackId,userVO);
	}
	
	public static void saveRecordBoundingDetail(List<RecordBoundingVO> lstRecBound,UserVO userVO)
	{
		MrdMasterDelegate delegate=new MrdMasterDelegate();
		delegate.saveRecordBoundingDetail(lstRecBound,userVO);
	}
	
	
}
