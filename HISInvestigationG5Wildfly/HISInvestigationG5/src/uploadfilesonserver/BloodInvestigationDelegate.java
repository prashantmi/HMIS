package uploadfilesonserver;

/**
 * @author : C-DAC, Noida
 * Project : AHIMS
 * Module  : Blood Bank
 * Created On : 18 Aug, 2008
 * 
 * Developed By : For Common Use
 * 
 * Purpose : This is Delegate Class for Presentation Tier to Business Tier 
 * 			for the Process 'Blood Investigation'
 * 
 */
import hisglobal.business.Delegate;
import hisglobal.utility.Entry;

import hisglobal.vo.BloodBagComponentDtlVO;
import hisglobal.vo.BloodBagDtlVO;
import hisglobal.vo.BloodBankMasterVO;
import hisglobal.vo.BloodDonationVO;
import hisglobal.vo.BloodRequisitionComponentDtlVO;
import hisglobal.vo.BloodRequisitionDtlVO;
import hisglobal.vo.BloodRequisitionPatientDtlVO;
import hisglobal.vo.BloodTestResultVO;
import hisglobal.vo.CampDetailVO;
import hisglobal.vo.CellGroupingValidationVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.BloodRequisitionDtlVO;











import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperPrint;

public class BloodInvestigationDelegate extends Delegate
{
	public BloodInvestigationDelegate()
	{
		super(new BloodInvestigationBO());
	}
	
	
	//added bY hemant to fetch path for upload process
	public String getPathData(UserVO userVO)
	{
		BloodInvestigationBOi investigationBOi = (BloodInvestigationBOi) super.getServiceProvider();
		return (investigationBOi.getPathData(userVO));
	}
	//Added bY Hemant for saving file Uploaded data
	public void saveDetailsOfFile(FileUploadedVO vo , UserVO userVO)
	{
		BloodInvestigationBOi investigationBOi = (BloodInvestigationBOi) super.getServiceProvider();
		 investigationBOi.saveDetailsOfFile(vo,userVO);
	}
}
