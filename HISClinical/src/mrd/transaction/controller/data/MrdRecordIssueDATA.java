package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordIssueDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;
import mrd.transaction.delegate.MrdEssentialDelegate;

public class MrdRecordIssueDATA extends ControllerDATA
{
	public static List getRequestListForIssue(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getRequestListForIssue(mrdRecordRequestDtlVO,userVO); 
	}

	public static List getRequestDetail(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getRequestDetail(requestRecordDtlVO,userVO); 
	}

	public static void saveIssueDetail(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,List<RequestRecordDtlVO> requestRecordDtlVOList, List<MrdRecordDtlVO> mrdRecordDtlVOList, List<MrdRecordIssueDtlVO> mrdRecordIssueDtlVOList, UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		delegate.saveIssueDetail(mrdRecordRequestDtlVO,requestRecordDtlVOList,mrdRecordDtlVOList,mrdRecordIssueDtlVOList,userVO); 
	}
	
	

}
