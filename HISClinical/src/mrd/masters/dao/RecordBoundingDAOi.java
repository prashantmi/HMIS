package mrd.masters.dao;

import java.util.List;

import hisglobal.vo.RecordBoundingVO;
import hisglobal.vo.UserVO;

public interface RecordBoundingDAOi 
{
	public List getBoundedRecordType(String boundingMode,String boundingId,UserVO userVO);
	
	public void create(RecordBoundingVO recordBoundingVO,UserVO userVO);
	
	public void deletePrevRecord(RecordBoundingVO recordBoundingVO,UserVO userVO);
	
	
}
