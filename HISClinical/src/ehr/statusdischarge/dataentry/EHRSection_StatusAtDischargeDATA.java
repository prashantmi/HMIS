/*
 * Nilesh Gupta
 * Date:- 30-10-2017
 * New Process Status At Discharge
*/

package ehr.statusdischarge.dataentry;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;
import hisglobal.vo.PatientDetailVO;


import ehr.statusdischarge.dataentry.EHRSection_StatusAtDischargeBO;
import ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO;


public class EHRSection_StatusAtDischargeDATA extends  ControllerDATA
{

		public static EHRSection_StatusAtDischargeVO getEssentials(EHRSection_StatusAtDischargeVO dischargeVO,UserVO userVO) {
			EHRSection_StatusAtDischargeBO serviceBO = new EHRSection_StatusAtDischargeBO();
			return serviceBO.getEssentials(dischargeVO, userVO);
		}

		public static void saveDetails(EHRSection_StatusAtDischargeVO dischargeVO,
				UserVO userVO) {
			EHRSection_StatusAtDischargeBO serviceBO = new EHRSection_StatusAtDischargeBO();
			serviceBO.saveDetails(dischargeVO, userVO);
			
		}

		
}
