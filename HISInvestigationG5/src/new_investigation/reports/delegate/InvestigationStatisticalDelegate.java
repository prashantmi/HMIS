package new_investigation.reports.delegate;

import java.util.Map;

import new_investigation.reports.bo.InvestigationStatisticalReportBO;
import new_investigation.vo.InvestigationStatisticalReportVO;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

public class InvestigationStatisticalDelegate extends Delegate {

	public InvestigationStatisticalDelegate()
	{
		super(new InvestigationStatisticalReportBO());
	}  

	public Map fetchLab(InvestigationStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		InvestigationStatisticalReportBO serviceBO = (InvestigationStatisticalReportBO) super.getServiceProvider();
		return serviceBO.fetchLab(reqList_VO, _UserVO);
	}
	
	public Map fetchTest(InvestigationStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		InvestigationStatisticalReportBO serviceBO = (InvestigationStatisticalReportBO) super.getServiceProvider();
		return serviceBO.fetchTest(reqList_VO, _UserVO);
	}
	
	public Map fetchDept(InvestigationStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		InvestigationStatisticalReportBO serviceBO = (InvestigationStatisticalReportBO) super.getServiceProvider();
		return serviceBO.fetchDept(reqList_VO, _UserVO);
	}

	public Map fetchSample(InvestigationStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		InvestigationStatisticalReportBO serviceBO = (InvestigationStatisticalReportBO) super.getServiceProvider();
		return serviceBO.fetchSample(reqList_VO, _UserVO);
	}

}
