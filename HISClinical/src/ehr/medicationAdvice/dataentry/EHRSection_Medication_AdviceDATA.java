/*
 * Nilesh Gupta
 * Date:- 30-10-2017
 * New Process Status At Discharge
*/

package ehr.medicationAdvice.dataentry;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;


import ehr.medicationAdvice.dataentry.EHRSection_Medication_AdviceBO;
import ehr.medicationAdvice.vo.EHRSection_Medication_AdviceVO;

public class EHRSection_Medication_AdviceDATA extends  ControllerDATA
{


	public static EHRSection_Medication_AdviceVO getEssentials(EHRSection_Medication_AdviceVO dischargeVO,UserVO userVO) {
			EHRSection_Medication_AdviceBO serviceBO = new EHRSection_Medication_AdviceBO();
			return serviceBO.getEssentials(dischargeVO, userVO);
		}

		public static void saveDetails(EHRSection_Medication_AdviceVO dischargeVO,
				UserVO userVO) {
			EHRSection_Medication_AdviceBO serviceBO = new EHRSection_Medication_AdviceBO();
			serviceBO.saveDetails(dischargeVO, userVO);
			
		}

		
}
