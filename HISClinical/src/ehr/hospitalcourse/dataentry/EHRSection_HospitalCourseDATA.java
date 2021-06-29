
package ehr.hospitalcourse.dataentry;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;
import hisglobal.vo.PatientDetailVO;


import ehr.hospitalcourse.dataentry.EHRSection_HospitalCourseBO;
import ehr.hospitalcourse.vo.EHRSection_HospitalCourseVO;


public class EHRSection_HospitalCourseDATA extends  ControllerDATA
{

		public static EHRSection_HospitalCourseVO getEssentials(EHRSection_HospitalCourseVO dischargeVO,UserVO userVO) {
			EHRSection_HospitalCourseBO serviceBO = new EHRSection_HospitalCourseBO();
			return serviceBO.getEssentials(dischargeVO, userVO);
		}

		public static void saveDetails(EHRSection_HospitalCourseVO dischargeVO,
				UserVO userVO) {
			EHRSection_HospitalCourseBO serviceBO = new EHRSection_HospitalCourseBO();
			serviceBO.saveDetails(dischargeVO, userVO);
			
		}

		
}
