package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;

public class RecordRequestApprovalDATA extends ControllerDATA
{
	public static List getRequestListForApproval(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getRequestListForApproval(mrdRecordRequestDtlVO,userVO); 
	}

	public static List getRequestDetail(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getRequestDetail(requestRecordDtlVO,userVO); 
	}

	public static void saveApprovalDetail(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,List<RequestRecordDtlVO> requestRecordDtlVOList, UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		delegate.saveApprovalDetail(mrdRecordRequestDtlVO,requestRecordDtlVOList,userVO); 
	}

	public static Map getMrdRecordStatus(String mrdRecordId,String requestId,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getMrdRecordStatus(mrdRecordId,requestId,userVO);
	}

	public static MrdRecordRequestDtlVO checkUserIsEmp(
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO) {
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.checkUserIsEmp(mrdRecordRequestDtlVO,userVO); 
	}

}
