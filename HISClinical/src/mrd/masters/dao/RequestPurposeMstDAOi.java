package mrd.masters.dao;

import hisglobal.vo.RequestPurposeMstVO;
import hisglobal.vo.UserVO;

public interface RequestPurposeMstDAOi 
{
	public RequestPurposeMstVO[] getRequestPurpose(String recordType,UserVO userVO);
	public void creat(RequestPurposeMstVO reqPurposeMstVO ,UserVO userVO);
	public String checkDuplicateName(RequestPurposeMstVO reqPurposeMstVO ,UserVO userVO);
	public RequestPurposeMstVO getDataForModify(RequestPurposeMstVO reqPurposeMstVO, UserVO _UserVO);
	public void saveModReqPurposeMaster(RequestPurposeMstVO reqPurposeMstVO ,UserVO userVO);
	public void updateReqPurposeMaster(RequestPurposeMstVO reqPurposeMstVO,UserVO _UserVO);
	public String checkDuplicateNameForModify(RequestPurposeMstVO reqPurposeMstVO,UserVO userVO);
}
