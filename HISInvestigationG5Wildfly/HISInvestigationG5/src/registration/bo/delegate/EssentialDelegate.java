package registration.bo.delegate;

import hisglobal.business.Delegate;
import hisglobal.tools.Tree;
import hisglobal.vo.CityLocationMasterVO;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeTriageDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PoliceExaminationReqtDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.YellowSlipEntryDtlVO;
import hisglobal.vo.YellowSlipMonitoringVO;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import net.sf.jasperreports.engine.JasperPrint;
import registration.bo.EssentialBO;
import registration.bo.EssentialBOi;
import registration.vo.BPLDetailsVO;
import registration.vo.DSSEpisodeVO;
import registration.vo.DSSRegistrationVO;
import registration.vo.DSSReportVO;

public class EssentialDelegate extends Delegate {

	public EssentialDelegate() {
		super(new EssentialBO()); // /<<Setting the service provider
	}

	 
	public Map getEssentialForSearch(UserVO userVO) {
		EssentialBOi serviceBO = (EssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForSearch(userVO);
	}
}// end of class
