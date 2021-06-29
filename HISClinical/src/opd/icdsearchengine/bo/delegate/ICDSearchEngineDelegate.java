package opd.icdsearchengine.bo.delegate;

import hisglobal.business.Delegate;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import opd.icdsearchengine.bo.ICDSearchEngineBO;
import opd.icdsearchengine.bo.ICDSearchEngineBOi;
import opd.icdsearchengine.vo.ICDDiseaseDetailVO;
import opd.icdsearchengine.vo.ICDSearchEngineVO;
import opd.icdsearchengine.vo.ResultDiseaseListVO;
import opd.icdsearchengine.vo.ResultIndexListVO;

public class ICDSearchEngineDelegate extends Delegate
{
	// Constructor for Setting Service Provider
	public ICDSearchEngineDelegate()
	{
		super(new ICDSearchEngineBO()); // <<Setting the service provider
	}

	// Getting Volume 1 Searching Essentials
	public Map<String, Object> getICDSearchingVolume1Essentials(UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getICDSearchingVolume1Essentials(_userVO);
	}

	// Getting Reports Searching Essentials
	public Map<String, Object> getICDSearchingReportsEssentials(UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getICDSearchingReportsEssentials(_userVO);
	}

	// Getting Result for ICD Disease List
	public List<ResultDiseaseListVO> getResultICDDiseaseList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getResultICDDiseaseList(_engineVO, _userVO);
	}

	// Getting Result for ICD Sub Disease List
	public List<ResultDiseaseListVO> getResultICDSubDiseaseList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getResultICDSubDiseaseList(_engineVO, _userVO);
	}

	// Getting Detailed ICD Disease VO
	public ICDDiseaseDetailVO getDetailICDDiseaseVO(ICDDiseaseDetailVO _disVO, UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getDetailICDDiseaseVO(_disVO, _userVO);
	}

	// Getting Result for Index Term List
	public List<ResultIndexListVO> getResultIndexTermList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getResultIndexTermList(_engineVO, _userVO);
	}

	// Getting List of Index Term List
	public List<ResultIndexListVO> getIndexTermList(UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getIndexTermList(_userVO);
	}

	// Getting List of Modifier Term of Index Term
	public List<ResultIndexListVO> getIndexModiTermList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getIndexModiTermList(_engineVO, _userVO);
	}

	// Getting List of Modifier Term of Modifiers Terms
	public List<ResultIndexListVO> getModifierTermTreeList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getModifierTermTreeList(_engineVO, _userVO);
	}

	// Getting List of All Sub Groups By Group
	public List<Entry> getAllSubGroupByGroup(String _icdGroupCode, UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getAllSubGroupByGroup(_icdGroupCode, _userVO);
	}

	// Getting List of All Diseases By Sub Groups
	public List<Entry> getAllDiseasesBySubGroup(String _icdSubGroupCode, UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getAllDiseasesBySubGroup(_icdSubGroupCode, _userVO);
	}

	// Getting Reports
	public JasperPrint getReport(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ICDSearchEngineBOi serviceBO = (ICDSearchEngineBOi) super.getServiceProvider();
		return serviceBO.getReport(_engineVO, _userVO);
	}
}
