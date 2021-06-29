package hr.pis.empReg.transactions.controller.data;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;
import hr.pis.empReg.bo.transactions.EmpRegistrationBO;

import javax.servlet.http.HttpServletRequest;

import vo.hr.pis.empReg.transactions.EmpRegistrationVO;


public class EmpRegistrationValidationDATA
{

	private EmpRegistrationVO modifyRecord(EmpRegistrationVO vo, UserVO userVO_p)
	{
		System.out.println("EmployeeRegistrationValidationDATA>>>  modifyRecord");
		EmpRegistrationVO EmpRegVO_p = null;
		EmpRegistrationBO objBO = new EmpRegistrationBO();
		try{
			EmpRegVO_p=objBO.modifyRecord(vo, userVO_p);

			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("PisMasterBO.modifyRecord(vo) --> " + vo.getStrMsgString());
			}
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			throw new HisApplicationExecutionException();
		}

		catch(HisDataAccessException e){		
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			throw new HisApplicationExecutionException();
		}
		finally
		{
		}
		return EmpRegVO_p;
	}

	public static boolean validateEmpRegDtl(HttpServletRequest objRequest, EmpRegistrationVO VOEmpReg, String strMode_p)
	{
		EmpRegistrationVO objEmpRegVO;
		boolean bExistStatus=false;
		try 
		{
			objEmpRegVO= new EmpRegistrationVO();
			objEmpRegVO=VOEmpReg;
			UserVO uservo = ControllerUTIL.getUserVO(objRequest);
			EmpRegistrationBO bo = new EmpRegistrationBO();
			bExistStatus= bo.validateEmpRegDtl(VOEmpReg,strMode_p,uservo);


			if (objEmpRegVO.getStrMsgType()!=null && objEmpRegVO.getStrMsgType().equals("1")) {
				throw new Exception(objEmpRegVO.getStrMsgString());
			}

			if (bExistStatus == false) 
			{
				VOEmpReg.setStrWarning("Data already exist");
			} 
			else 
			{
				//VOCadre.setStrOldCadreName("");
				//Map mp= new HashMap();
				//mp.put("save_1"  , VOCadre);
				//String[] arrKeyVariables= new String[2];
				//arrKeyVariables[0]=VOCadre.getStrCadreName();
				//arrKeyVariables[1]=VOCadre.getStrCadreId();
				//AuditlogDATA.saveUpdateAuditLog(mp,  uservo, objRequest, arrKeyVariables);
				//VOCadre.setStrMsg("Data Saved Successfully");
				
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "EmployeeRegistrationValidationAction.EmployeeRegistrationValidationDATA.validateEmpRegDtl(vo) --> "+ e.getMessage();
			VOEmpReg.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objEmpRegVO = null;
		}
		return bExistStatus;
	}

	/**
	 * to get the data for modify page.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static EmpRegistrationVO modifyRecord(HttpServletRequest objRequest, EmpRegistrationVO objEmpRegValVO) 
	{
		EmpRegistrationValidationDATA objEmpRegValData = new EmpRegistrationValidationDATA();
		String strMsgText = "";

		String strTemp[] = null;
		EmpRegistrationVO VOEmpReg =new EmpRegistrationVO();
		String strChk = "";

		try 
		{
		
			UserVO uservo = ControllerUTIL.getUserVO(objRequest);
			
			String strCombos[] = objRequest.getParameterValues("combo");

			if(objRequest.getParameter("chk") !=null && !objRequest.getParameter("chk").equals(""))
			{
				strChk = objRequest.getParameter("chk");
				strTemp = strChk.replace('$', '#').split("#");
				VOEmpReg.setStrEmployeeNumber(strTemp[0]);
				VOEmpReg.setStrEmployeeValidationStatusCategory(strCombos[0]);
			}
			else
				VOEmpReg=objEmpRegValVO;

			EmpRegistrationVO EmpRegVO_p=objEmpRegValData.modifyRecord(VOEmpReg, uservo);
			if (VOEmpReg.getStrMsgType().equals("1")) 
			{
				throw new Exception(VOEmpReg.getStrMsgString());
			}
			VOEmpReg.setStrEmployeeNumber(EmpRegVO_p.getStrEmployeeNumber());
			VOEmpReg.setStrEmployeeFullName(EmpRegVO_p.getStrEmployeeFullName());
			
			
			/*
 * 			
			if(request.getParameter("chk") !=null && !request.getParameter("chk").equals(""))
			{
				VOCadre.setStrIsValid("");
				AuditLogVO objAuditLogVO= new AuditLogVO(AuditLogVO.REGISTRATION,"1");
				Map mp= new HashMap();
				mp.put("save_1", VOCadre);
				objAuditLogVO.setMpobjectsOnInit(mp);
				AuditlogDATA.initAuditLog(objAuditLogVO, ControllerUTIL.getUserVO(request),request);
			}
*/
		} 
		catch (Exception e) 
		{
			strMsgText = "EmployeeRegistrationValidationDATA.modifyRecord(request) --> "+ e.getMessage();
			VOEmpReg.setStrErrorMsg("Application Error [ERROR ID : " + strMsgText);
			HisException eObj = new HisException("pis","EmployeeRegistrationValidationDATA->modifyRecord()", strMsgText);
			eObj = null;
		}
		finally 
		{
			objEmpRegValVO = null;
			strMsgText = null;
		}
		return VOEmpReg;
	}

/*
	public static boolean saveCadreDtl(HttpServletRequest objHttpServletRequest ,CadreVO VOCadre,String strMode_p) 
	{

		CadreVO objCadreVO;
		boolean bExistStatus=false;

		try
		{
			objCadreVO= new CadreVO();

			objCadreVO=VOCadre;
			//String abc = this
			UserVO userVo= ControllerUTIL.getUserVO(objHttpServletRequest);
			PisMasterBO bo = new PisMasterBO();
			bExistStatus=bo.saveCadreDtl(VOCadre,strMode_p,userVo);

			if (objCadreVO.getStrMsgType()!=null && objCadreVO.getStrMsgType().equals("1")) 
			{
				throw new Exception(objCadreVO.getStrMsgString());
			}

			if (bExistStatus == false) 
			{
				VOCadre.setStrWarning("Duplicate Name Exist..!");
			} else
			{
				VOCadre.setStrMsg("Data Saved Successfully");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = "CadreAction.CadreMstDATA.saveCadreDtl(vo) --> "
					+ e.getMessage();
			VOCadre.setStrErrorMsg("Application Error [ERROR ID : " + strmsgText);
		} 
		finally 
		{
			objCadreVO = null;
		}
		return bExistStatus;
	}
	*/
}
