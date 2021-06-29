package registration.masters.controller.data;

/**
 * @author s.singaravelan 	
 * Creation Date:- 07-Feb-2013
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;
import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import vo.registration.DepartmentVO;
import vo.registration.PatCatDocVO;
import vo.registration.PatCategoryVO;

public class PatCatDocMapMstDATA 
{

	public static List getPatientPrimaryCategory(UserVO uservo) {
		RegMasterBO bo = new RegMasterBO();
		return bo.getPatientPrimaryCategory(uservo);
	}
	
	public static Map getCategoryWiseMappedUnMappedDocument(String _categoryCode,UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getCategoryWiseMappedUnMappedDocument(_categoryCode,uservo);
	}

	public static boolean saveCategoryVerificationDocument(PatCatDocVO[] insertpatCatVerDocVO,PatCatDocVO[] updatepatCatVerDocVO,String strMode,UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.saveCategoryVerificationDocument(insertpatCatVerDocVO,updatepatCatVerDocVO,strMode,uservo);
	}
	

}
