package opd.master.controller.data;

import hisglobal.vo.ParameterRangeMasterVO;
import hisglobal.vo.UserVO;

import opd.bo.delegate.OpdMasterDelegate;

public class ParaRangeMstDATA
{
	public static ParameterRangeMasterVO getParaName(String paraID,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		ParameterRangeMasterVO parameterRangeMasterVO = masterDelegate.getParaName(paraID,_UserVO);
		return parameterRangeMasterVO;
	}
	
	
	
	public static boolean saveParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		boolean hasFlag=masterDelegate.saveParaRangeInfo(parameterRangeMasterVO, _UserVO);
		return hasFlag;
	}

	public static ParameterRangeMasterVO fetchParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return (masterDelegate.fetchParaRangeInfo(parameterRangeMasterVO, _UserVO));
	}


	public static boolean saveModParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		boolean flag=masterDelegate.saveModParaRangeInfo(parameterRangeMasterVO, _UserVO);
		return flag;
	}

}
