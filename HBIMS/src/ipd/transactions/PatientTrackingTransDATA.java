package ipd.transactions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;


public class PatientTrackingTransDATA {

	
	
	/**
	 * This function is used to set initial parameters required to display on
	 * main page
	 * 
	 * @param formBean
	 */
	public static void detail(PatientTrackingTransFB formBean) 
	{
		PatientTrackingTransVO vo = null;
		PatientTrackingTransBO bo= null;
		try 
		{
			vo = new PatientTrackingTransVO();
			bo = new PatientTrackingTransBO();
			
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			String strMovementDtl = "";
			
			try 
			{
				bo.setMovementDetail(vo);
				strMovementDtl = PatientTrackingTransHLP.getMovementDetail(vo);
			} 
			catch (Exception e) 
			{
				throw e;
			}
			//vo.setStrAdmnNo(formBean.getStrAdmnNo());
			//vo.setStrHospitalCode(formBean.getStrHospitalCode());
			

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrErrMsgString());
			}

			formBean.setStrMovementDtl(strMovementDtl);
			/*
			 * formBean.setStrAdmnNo(vo.getStrAdmnNo());
			 * formBean.setStrAdmnDate(vo.getStrAdmnDate());
			 * formBean.setStrWard(vo.getStrWard());
			 * formBean.setStrRoomBed(vo.getStrRoomBed());
			 */

		} 
		catch (Exception e) 
		{
			vo.setStrErrMsgString(e.getMessage());
			vo.setStrMsgType("1");
			HisException eObj = new HisException("IPD-->Patient Tracking","PatientTrackingTransDATA-->detail()", vo.getStrErrMsgString());

			if (e.getMessage().startsWith("Invalid")) 
			{
				formBean.setStrErrMsgString(e.getMessage());
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			} 
			else 
			{
				formBean.setStrErrMsgString("Application Error [ERROR ID :"+ eObj.getErrorID()+ "], Contact System Administrator! ");
			}
			eObj = null;
		} 
		finally 
		{
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
	}
	
	/**
	 * This function is used to set initial parameters required to display on
	 * main page
	 * 
	 * @param formBean
	 */
	public static void detail_BS(PatientTrackingTransFB formBean) 
	{
		PatientTrackingTransVO vo = null;
		PatientTrackingTransBO bo= null;
		try 
		{
			vo = new PatientTrackingTransVO();
			bo = new PatientTrackingTransBO();
			
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			String strMovementDtl = "";
			
			try 
			{
				bo.setMovementDetail(vo);
				strMovementDtl = PatientTrackingTransHLP.getMovementDetail_BS(vo);
			} 
			catch (Exception e) 
			{
				throw e;
			}
			//vo.setStrAdmnNo(formBean.getStrAdmnNo());
			//vo.setStrHospitalCode(formBean.getStrHospitalCode());
			

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrErrMsgString());
			}

			formBean.setStrMovementDtl(strMovementDtl);
			/*
			 * formBean.setStrAdmnNo(vo.getStrAdmnNo());
			 * formBean.setStrAdmnDate(vo.getStrAdmnDate());
			 * formBean.setStrWard(vo.getStrWard());
			 * formBean.setStrRoomBed(vo.getStrRoomBed());
			 */

		} 
		catch (Exception e) 
		{
			vo.setStrErrMsgString(e.getMessage());
			vo.setStrMsgType("1");
			HisException eObj = new HisException("IPD-->Patient Tracking","PatientTrackingTransDATA-->detail()", vo.getStrErrMsgString());

			if (e.getMessage().startsWith("Invalid")) 
			{
				formBean.setStrErrMsgString(e.getMessage());
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			} 
			else 
			{
				formBean.setStrErrMsgString("Application Error [ERROR ID :"+ eObj.getErrorID()+ "], Contact System Administrator! ");
			}
			eObj = null;
		} 
		finally 
		{
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
	}
	
	/**
	 * This function is used to set initial parameters required to display on
	 * main page
	 * 
	 * @param formBean
	 */
	public static void movDetails(PatientTrackingTransFB formBean,HttpServletRequest request,HttpServletResponse response) 
	{
		PatientTrackingTransVO vo = null;
		PatientTrackingTransBO bo= null;
		try 
		{
			vo = new PatientTrackingTransVO();
			bo = new PatientTrackingTransBO();
			
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			String strMovementDtl = "";
			
			try 
			{
				bo.setMovementDetail(vo);
				strMovementDtl = PatientTrackingTransHLP.getMovementDetail(vo);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strMovementDtl);	
			} 
			catch (Exception e) 
			{
				throw e;
			}
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrErrMsgString());
			}
		} 
		catch (Exception e) 
		{
			vo.setStrErrMsgString(e.getMessage());
			vo.setStrMsgType("1");
			HisException eObj = new HisException("IPD-->Patient Tracking","PatientTrackingTransDATA-->detail()", vo.getStrErrMsgString());

			if (e.getMessage().startsWith("Invalid")) 
			{
				formBean.setStrErrMsgString(e.getMessage());
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			} 
			else 
			{
				formBean.setStrErrMsgString("Application Error [ERROR ID :"+ eObj.getErrorID()+ "], Contact System Administrator! ");
			}
			eObj = null;
		} 
		finally 
		{
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
	}
	public static void movDetails_BS(PatientTrackingTransFB formBean,HttpServletRequest request,HttpServletResponse response) 
	{
		PatientTrackingTransVO vo = null;
		PatientTrackingTransBO bo= null;
		try 
		{
			vo = new PatientTrackingTransVO();
			bo = new PatientTrackingTransBO();
			
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			String strMovementDtl = "";
			
			try 
			{
				bo.setMovementDetail(vo);
				strMovementDtl = PatientTrackingTransHLP.getMovementDetail_BS(vo);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strMovementDtl);	
			} 
			catch (Exception e) 
			{
				throw e;
			}
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrErrMsgString());
			}
		} 
		catch (Exception e) 
		{
			vo.setStrErrMsgString(e.getMessage());
			vo.setStrMsgType("1");
			HisException eObj = new HisException("IPD-->Patient Tracking","PatientTrackingTransDATA-->detail()", vo.getStrErrMsgString());

			if (e.getMessage().startsWith("Invalid")) 
			{
				formBean.setStrErrMsgString(e.getMessage());
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			} 
			else 
			{
				formBean.setStrErrMsgString("Application Error [ERROR ID :"+ eObj.getErrorID()+ "], Contact System Administrator! ");
			}
			eObj = null;
		} 
		finally 
		{
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
	}
	public static boolean patStatusCode(PatientTrackingTransFB formBean) 
	{
		PatientTrackingTransVO vo = null;
		PatientTrackingTransBO bo = null;
		boolean retVal = false;

		try
		{
			vo = new PatientTrackingTransVO();
			bo = new PatientTrackingTransBO();
			vo = (PatientTrackingTransVO) TransferObjectFactory.populateData("ipd.transactions.PatientTrackingTransVO", formBean);
			
			//if(formBean.getStrCase().equals("1"))//Admission No Wise
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			//else
				//vo.setStrCrNo(formBean.getStrCrNo());
			
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.setpatStatusCode(vo);
			
			formBean.setStrAdmnStatusCode(vo.getStrAdmnStatusCode());
			formBean.setStrPatDeadCode(vo.getStrPatDeadCode());
			formBean.setStrCrNo(vo.getStrCrNo());
			formBean.setDeadStatus(vo.getDeadStatus());
			formBean.setStrAdmnDate(vo.getStrAdmnDate());
			formBean.setStrDischargeTime(vo.getStrDischargeTime());
			
			if(vo.getStrInvalidAdmNo().equals("1"))
			{
				formBean.setStrErrMsgString("Invalid Admission No./Data not found!!!");
				formBean.setStrCrNo("");
				retVal = false;
				return retVal;
			}
			else if(!formBean.getStrAdmnStatusCode().equals("12")) 
			{
				if (formBean.getStrPatDeadCode().equals("1") && formBean.getDeadStatus().equals("")) 
				{
					formBean.setStrErrMsgString("Patient is Dead!!");
					formBean.setStrCrNo("");
					retVal = false;
				} 
				else
					retVal = true;
			}
			else
				retVal = true;

			if (formBean.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(formBean.getStrErrMsgString());
			}
		} 
		catch (Exception e) 
		{
			formBean.setStrErrMsgString(e.getMessage());
			formBean.setStrMsgType("1");
			HisException eObj = new HisException("IPD-->Patient Tracking","PatientTrackingTransDATA-->patStatusCode()", formBean.getStrErrMsgString());
			formBean.setStrErrMsgString("Application Error[ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
			if (bo != null)
				bo = null;
		}
		return retVal;
	}
	/**
	 * This function is used to set initial parameters required to display on
	 * main page
	 * 
	 * @param formBean
	 */
	public static void admissionList(PatientTrackingTransFB formBean) 
	{
		PatientTrackingTransVO vo = null;
		PatientTrackingTransBO bo= null;
		try 
		{
			vo = new PatientTrackingTransVO();
			bo = new PatientTrackingTransBO();
			
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			String strAdmissionList = "";
			
			try 
			{
				bo.admissionList(vo);
				strAdmissionList = PatientTrackingTransHLP.admissionList(vo);
			} 
			catch (Exception e) 
			{
				throw e;
			}
			//vo.setStrAdmnNo(formBean.getStrAdmnNo());
			//vo.setStrHospitalCode(formBean.getStrHospitalCode());
			

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrErrMsgString());
			}

			formBean.setStrMovementDtl(strAdmissionList);
			/*
			 * formBean.setStrAdmnNo(vo.getStrAdmnNo());
			 * formBean.setStrAdmnDate(vo.getStrAdmnDate());
			 * formBean.setStrWard(vo.getStrWard());
			 * formBean.setStrRoomBed(vo.getStrRoomBed());
			 */

		} 
		catch (Exception e) 
		{
			vo.setStrErrMsgString(e.getMessage());
			vo.setStrMsgType("1");
			HisException eObj = new HisException("IPD-->Patient Tracking","PatientTrackingTransDATA-->detail()", vo.getStrErrMsgString());

			if (e.getMessage().startsWith("Invalid")) 
			{
				formBean.setStrErrMsgString(e.getMessage());
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			} 
			else 
			{
				formBean.setStrErrMsgString("Application Error [ERROR ID :"+ eObj.getErrorID()+ "], Contact System Administrator! ");
			}
			eObj = null;
		} 
		finally 
		{
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
	}
	
	/**
	 * This function is used to set initial parameters required to display on
	 * main page
	 * 
	 * @param formBean
	 */
	public static void admissionList_BS(PatientTrackingTransFB formBean) 
	{
		PatientTrackingTransVO vo = null;
		PatientTrackingTransBO bo= null;
		try 
		{
			vo = new PatientTrackingTransVO();
			bo = new PatientTrackingTransBO();
			
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			String strAdmissionList = "";
			
			try 
			{
				bo.admissionList(vo);
				strAdmissionList = PatientTrackingTransHLP.admissionList_BS(vo);
			} 
			catch (Exception e) 
			{
				throw e;
			}
			//vo.setStrAdmnNo(formBean.getStrAdmnNo());
			//vo.setStrHospitalCode(formBean.getStrHospitalCode());
			

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrErrMsgString());
			}

			formBean.setStrMovementDtl(strAdmissionList);
			/*
			 * formBean.setStrAdmnNo(vo.getStrAdmnNo());
			 * formBean.setStrAdmnDate(vo.getStrAdmnDate());
			 * formBean.setStrWard(vo.getStrWard());
			 * formBean.setStrRoomBed(vo.getStrRoomBed());
			 */

		} 
		catch (Exception e) 
		{
			vo.setStrErrMsgString(e.getMessage());
			vo.setStrMsgType("1");
			HisException eObj = new HisException("IPD-->Patient Tracking","PatientTrackingTransDATA-->detail()", vo.getStrErrMsgString());

			if (e.getMessage().startsWith("Invalid")) 
			{
				formBean.setStrErrMsgString(e.getMessage());
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			} 
			else 
			{
				formBean.setStrErrMsgString("Application Error [ERROR ID :"+ eObj.getErrorID()+ "], Contact System Administrator! ");
			}
			eObj = null;
		} 
		finally 
		{
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
		}
	}

}
