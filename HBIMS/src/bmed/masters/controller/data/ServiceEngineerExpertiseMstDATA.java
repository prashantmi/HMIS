package bmed.masters.controller.data;



import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;

import bmed.masters.bo.ServiceEngineerExpertiseMstBO;
import bmed.masters.controller.fb.ServiceEngineerExpertiseMstFB;
import bmed.masters.vo.ServiceEngineerExpertiseMstVO;

public class ServiceEngineerExpertiseMstDATA {

	public static void initializeAdd(HttpServletRequest request,
			ServiceEngineerExpertiseMstFB formBean) {

		HisUtil util = null;
		ServiceEngineerExpertiseMstBO bo = null;
		ServiceEngineerExpertiseMstVO vo = null;

		
		
		String strAvailableExpertiseOptions = null;

		try {

			util = new HisUtil("BMED", "ServiceEngineerExpertiseMstDATA");
			vo = new ServiceEngineerExpertiseMstVO();
			bo = new ServiceEngineerExpertiseMstBO();

			formBean.setStrHospitalCode(request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());

			
			if (formBean.getCombo() != null) {
				String strEmployeeIdAndName = formBean.getCombo()[0];
				String[] arrStrEmployeeIdAndName = strEmployeeIdAndName.split("\\^");
				
				if(formBean.getStrServiceEngineerEmployeeId()==null) {
					formBean.setStrServiceEngineerEmployeeId(arrStrEmployeeIdAndName[0]) ;
				}
				if(formBean.getStrServiceEngineerName()==null) {
					formBean.setStrServiceEngineerName(arrStrEmployeeIdAndName[1]);
				}	
			}
			
			
			TransferObjectFactory.populateData(vo, formBean);
			bo.initializeAdd(vo);
			TransferObjectFactory.populateData(formBean, vo);

			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

			
			/* Getting different combo options */
			strAvailableExpertiseOptions = util.getOptionValue(
					vo.getWrsAvailableExpertiseOptions(), "0", "", false);
			

			/* Getting different combo options in form bean */
			formBean.setStrAvailableExpertiseOptions(strAvailableExpertiseOptions);
			

		} catch (Exception _e) {

			HisException eObj = new HisException("BMED",
					"ServiceEngineerExpertiseMstDATA->initializeAdd()",
					_e.getMessage());

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			
			eObj = null;
		}

	}

	public static void insert(HttpServletRequest request,
			ServiceEngineerExpertiseMstFB formBean) {
		
		ServiceEngineerExpertiseMstBO bo = null;
		ServiceEngineerExpertiseMstVO vo = null;

		try {

			
			vo = new ServiceEngineerExpertiseMstVO();
			bo = new ServiceEngineerExpertiseMstBO();

			formBean.setStrHospitalCode(request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			

			TransferObjectFactory.populateData(vo, formBean);
			vo.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			bo.insert(vo);
			
			TransferObjectFactory.populateData(formBean, vo);

		} catch (Exception _e) {
			//_e.printStackTrace();

			HisException eObj = new HisException("BMED",
					"ServiceEngineerExpertiseMstDATA->insert()", _e.getMessage());

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			
			eObj = null;
		}

	}

	
	public static void initializeModify(HttpServletRequest request,
			ServiceEngineerExpertiseMstFB formBean) {
		HisUtil util = null;
		ServiceEngineerExpertiseMstBO bo = null;
		ServiceEngineerExpertiseMstVO vo = null;

		String strPrimaryKeySet = null;
		String[] arrStrPrimaryKeys = null;
		String[] strCheckValue = null;
		

		try {

			util = new HisUtil("BMED", "ServiceEngineerExpertiseMstDATA");
			vo = new ServiceEngineerExpertiseMstVO();
			bo = new ServiceEngineerExpertiseMstBO();

			formBean.setStrHospitalCode(request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());

			strCheckValue = formBean.getChk();
			if (strCheckValue != null) {
				strPrimaryKeySet = strCheckValue[0].split("\\$")[0];
				arrStrPrimaryKeys = strPrimaryKeySet.split("@");
			}
			
			if(formBean.getStrServiceEngineerEmployeeId()==null) {
				formBean.setStrServiceEngineerEmployeeId(arrStrPrimaryKeys[1]);
			}
			if(formBean.getArrStrSelectedExpertiseId()==null) {
				formBean.setStrSelectedExpertiseId(arrStrPrimaryKeys[2]);
			}
			
			

			TransferObjectFactory.populateData(vo, formBean);

			bo.initializeModify(vo);

			TransferObjectFactory.populateData(formBean, vo);

			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

			

		} catch (Exception _e) {

			HisException eObj = new HisException("BMED",
					"ServiceEngineerExpertiseMstDATA->initializeModify()",
					_e.getMessage());

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			
			eObj = null;
		}

	}

	public static boolean update(HttpServletRequest request,
			ServiceEngineerExpertiseMstFB formBean) {

		ServiceEngineerExpertiseMstBO bo = null;
		ServiceEngineerExpertiseMstVO vo = null;
		boolean fModificationStatus = false; // success: true, fail: false.

		try {

			vo = new ServiceEngineerExpertiseMstVO();
			bo = new ServiceEngineerExpertiseMstBO();

			formBean.setStrHospitalCode(request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			

			TransferObjectFactory.populateData(vo, formBean);
			vo.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			bo.update(vo);
			TransferObjectFactory.populateData(formBean, vo);

			fModificationStatus = vo.isfSaveSuccessfull();

		} catch (Exception _e) {

			HisException eObj = new HisException("BMED",
					"ServiceEngineerExpertiseMstDATA->update()", _e.getMessage());

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");

			eObj = null;
		}

		return fModificationStatus;
	}

	

}
