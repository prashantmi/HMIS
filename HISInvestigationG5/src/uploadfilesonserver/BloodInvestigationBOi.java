package uploadfilesonserver;


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
import hisglobal.vo.CellTestGroupingDetailVO;
import hisglobal.vo.UserVO;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;


import net.sf.jasperreports.engine.JasperPrint;


/**
 * @author : C-DAC, Noida
 * Project : AHIMS
 * Module  : Blood Bank
 * Created On : 18 Aug, 2008
 * 
 * Developed By : For Common Use
 * 
 * Purpose : This Interface should be used for the only Business Layer Methods 
 * 			regarding Process 'Blood Investigation'
 * 
 */

public interface BloodInvestigationBOi
{
  	//added by Hemant for fetching path data on 14-11-2014
	public String getPathData (UserVO userVO);
	//Added bY Hemant for saving file uploaded data
	public void saveDetailsOfFile (FileUploadedVO vo, UserVO userVO);
}

  //** functions for form CellGrouping and Table HBBT_CELL_TEST_DTL **// 	
