package opd.icdsearchengine.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import opd.icdsearchengine.bo.delegate.ICDSearchEngineDelegate;
import opd.icdsearchengine.vo.ICDDiseaseDetailVO;
import opd.icdsearchengine.vo.ICDSearchEngineVO;
import opd.icdsearchengine.vo.ResultDiseaseListVO;
import opd.icdsearchengine.vo.ResultIndexListVO;

public class ICDSearchEngineDATA extends ControllerDATA
{

	/**
	 * Getting Volume 1 Serching Essentials
	 * 
	 * @param _userVO
	 * @return Map of Essentials
	 */
	public static Map<String, Object> getICDSearchingVolume1Essentials(UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getICDSearchingVolume1Essentials(_userVO);
	}

	/**
	 * Getting Reports Serching Essentials
	 * 
	 * @param _userVO
	 * @return Map of Essentials
	 */
	public static Map<String, Object> getICDSearchingReportsEssentials(UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getICDSearchingReportsEssentials(_userVO);
	}

	// Getting Result for ICD Disease List
	public static List<ResultDiseaseListVO> getResultICDDiseaseList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getResultICDDiseaseList(_engineVO, _userVO);
	}
	
	// Getting Result for ICD Sub Disease List
	public static List<ResultDiseaseListVO> getResultICDSubDiseaseList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getResultICDSubDiseaseList(_engineVO, _userVO);
	}

	// Getting Detailed ICD Disease VO
	public static ICDDiseaseDetailVO getDetailICDDiseaseVO(ICDDiseaseDetailVO _disVO, UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getDetailICDDiseaseVO(_disVO, _userVO);
	}

	// Getting Result for Index Term List
	public static List<ResultIndexListVO> getResultIndexTermList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getResultIndexTermList(_engineVO, _userVO);
	}

	// Getting List of Index Term List
	public static List<ResultIndexListVO> getIndexTermList(UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getIndexTermList(_userVO);
	}

	// Getting List of Modifier Term of Index Term
	public static List<ResultIndexListVO> getIndexModiTermList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getIndexModiTermList(_engineVO, _userVO);
	}

	// Getting List of Modifier Term of Modifiers Terms
	public static List<ResultIndexListVO> getModifierTermTreeList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getModifierTermTreeList(_engineVO, _userVO);
	}

	// Getting List of All Sub Groups By Group
	public static List<Entry> getAllSubGroupByGroup(String _icdGroupCode, UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getAllSubGroupByGroup(_icdGroupCode, _userVO);
	}

	// Getting List of All Diseases By Sub Groups
	public static List<Entry> getAllDiseasesBySubGroup(String _icdSubGroupCode, UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getAllDiseasesBySubGroup(_icdSubGroupCode, _userVO);
	}

	// Getting Reports
	public static JasperPrint getReport(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineDelegate delegate = new ICDSearchEngineDelegate();
		return delegate.getReport(_engineVO, _userVO);
	}
}
