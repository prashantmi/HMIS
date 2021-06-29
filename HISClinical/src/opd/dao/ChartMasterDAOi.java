package opd.dao;

import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface ChartMasterDAOi
{
	// Getting Chart Parameters List By Chart ID 
	public List<ChartParameterMappingVO> getChartTempParas(String _chartID, UserVO _userVO);
	
	// Getting Chart Parameters List With Range By Chart ID
	public List<ChartParameterMappingVO> getChartTempParasWithRange(PatientDetailVO _patDtlVO, String _chartID, UserVO _userVO);

	public void create(ChartMasterVO _chartMasterVO,UserVO _userVO);
	
	public boolean checkDuplicate(ChartMasterVO _chartMasterVO,UserVO _userVO);
	
	public ChartMasterVO fetchRecord(ChartMasterVO _chartMasterVO,	UserVO _userVO) ;
	
	public void modify(ChartMasterVO _chartMasterVO,	UserVO _userVO);
	
	public void modifyInsert(ChartMasterVO _chartMasterVO,	UserVO _userVO);
	
	public boolean checkDuplicateBeforeModifySave(ChartMasterVO _chartMasterVO,	UserVO _userVO);
}
