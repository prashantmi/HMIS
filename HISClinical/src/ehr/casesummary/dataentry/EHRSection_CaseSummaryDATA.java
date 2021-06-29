/*
 * Nilesh Gupta
 * Date:- 30-10-2017
 * New Process Status At Discharge
*/

package ehr.casesummary.dataentry;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;
import hisglobal.vo.PatientDetailVO;


import ehr.casesummary.vo.EHRSection_CaseSummaryVO;
import ehr.statusdischarge.dataentry.EHRSection_StatusAtDischargeBO;
import ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO;


public class EHRSection_CaseSummaryDATA extends  ControllerDATA
{

		public static EHRSection_CaseSummaryVO getEssentials(EHRSection_CaseSummaryVO summaryVO,UserVO userVO) {
			EHRSection_CaseSummaryBO serviceBO = new EHRSection_CaseSummaryBO();
			return serviceBO.getEssentials(summaryVO, userVO);
		}

		public static void saveDetails(EHRSection_CaseSummaryVO summaryVO,UserVO userVO) {
			EHRSection_CaseSummaryBO serviceBO = new EHRSection_CaseSummaryBO();
			serviceBO.saveDetails(summaryVO, userVO);
			
		}

		
}
