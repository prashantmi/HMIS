package ipd.transactions;


import hisglobal.ReportUtil;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import ipd.ADTPrintingTransHLP;
import ipd.IpdConfigUtil;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;
//import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;


public class AdmissionReprintTransDATA extends Frame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;

	public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy/HH:mm:ss";
	
	//private static final String pathFileName = "hisglobal.hisconfig.hisReport";
	
    public static  String now() 
    {
    	HisUtil util=null;
    	String a="";
    	util=new HisUtil("transaction","AdmissionReprintTransDATA");
    	try{
    	 a= util.getASDate(DATE_FORMAT_NOW);
    	}
    	catch(Exception e){
    	}
    	/*Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());*/
    	return a;
    }
	/**
	 * This function is used to set initial parameters required to display on main page
	 * @param formBean
	 */
	public static void detail(AdmissionReprintTransFB formBean) {
		AdmissionReprintTransVO vo=null;
		//AdmissionReprintTransBO bo= null;
		try {
			vo = new AdmissionReprintTransVO();
			//bo = new AdmissionReprintTransBO();

			String strPatientAdmndtl = "";

			try{
			 strPatientAdmndtl = hisglobal.tools.hlp.PatientDtlHLP.patientDtl(formBean.getStrCrNo(), false);
			}catch(Exception e){

				throw e;

			}
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			//bo.setPatientDtl(vo);
			if(vo.getStrMsgType().equals("1")){
				throw new Exception(vo.getStrErrMsgString());
			}
				formBean.setStrPatientAdmndtl(strPatientAdmndtl);
				/*formBean.setStrAdmnNo(vo.getStrAdmnNo());
				formBean.setStrAdmnDate(vo.getStrAdmnDate());
				formBean.setStrWard(vo.getStrWard());
				formBean.setStrRoomBed(vo.getStrRoomBed());*/

		} catch (Exception e) {

			vo.setStrErrMsgString(e.getMessage());
			vo.setStrMsgType("1");
			   HisException eObj = new HisException("IPD-->Admission Reprint", "AdmissionReprintTransDATA-->detail()", vo.getStrErrMsgString());

			   if(e.getMessage().startsWith("Invalid")){

				   formBean.setStrErrMsgString(e.getMessage());
				   formBean.setStrMsgType("1");
				   formBean.setStrCrNo("");
			   }else{
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   }
			   eObj = null;
		}
		finally {
			if(vo != null) vo = null;
			if(formBean != null) formBean = null;
		}
	}



	public static boolean patStatusCode(AdmissionReprintTransFB formBean){

		AdmissionReprintTransVO vo = null;
		AdmissionReprintTransBO bo = null;
		boolean retVal=false;
		WebRowSet ws = null;
		try{
		    IpdConfigUtil ipc=new IpdConfigUtil(formBean.getStrHospitalCode());
			vo = new AdmissionReprintTransVO();
			bo = new AdmissionReprintTransBO();
			formBean.setStrRePrintCharge(ipc.getStrAdmissionReprintCharge());
			formBean.setRePrintCharges(ipc.getStrAdmissionReprintCharge()+"^"+ipc.getStrPassReprintCharge()+"^"+ipc.getStrDischargeReprintCharge());
			vo = (AdmissionReprintTransVO)TransferObjectFactory.populateData("ipd.transactions.AdmissionReprintTransVO",formBean);
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrIsIntegratedWithBilling(ipc.getStrIntegrationBilling());
			vo.setStrIsAdvanceAmountAtAdmission(ipc.getStrAdvanceAmountAdmission());
			formBean.setStrAdmnNo(vo.getStrAdmnNo());
			bo.setpatStatusCode(vo);
			formBean.setStrAdmnNo(vo.getStrAdmnNo());
			formBean.setStrIsIntegratedWithBilling(vo.getStrIsIntegratedWithBilling());
			formBean.setStrIsAdvanceAmountAtAdmission(vo.getStrIsAdvanceAmountAtAdmission());
			formBean.setStrIsAdvanceAmountAtAdmissionTaken(vo.getStrIsAdvanceAmountAtAdmissionTaken());
			formBean.setStrAdvanceAmount(vo.getStrAdvanceAmount());
			formBean.setStrAdvanceAmountDate(vo.getStrAdvanceAmountDate());
			formBean.setStrAdvanceAmountReceiptNo(vo.getStrAdvanceAmountReceiptNo());
			formBean.setStrAdmnStatusCode(vo.getStrAdmnStatusCode());
			formBean.setStrPatDeadCode(vo.getStrPatDeadCode());
			formBean.setStrCrNo(vo.getStrCrNo());
			formBean.setStrCtDt(now());
			ws = vo.getStrDisAdm();
			
			if(vo.getStrInvalidAdmno().equals("1"))
			{
				formBean.setStrErrMsgString("Invalid Admission No./Data Not Found!!!");
			    formBean.setStrCrNo("");
			    formBean.setStrAdmnNo("");
			    return false;
			}
			else                
		    {          
		      retVal = true;
		      formBean.setStrErrMsgString("");
		    }
			
			/*if(ws.size()==0)
			{
				if(vo.getStrInvalidAdmno().equals("1"))
				{
					formBean.setStrErrMsgString("Invalid Admission No./Data not found!!!");
				    formBean.setStrCrNo("");
				    formBean.setStrAdmnNo("");
				    return false;
				}
				if(!formBean.getStrSelCmbVal().trim().equals("2"))//2-Discharge Summary,1-Admission Slip,3-Visitor Pass
			    {
				  formBean.setStrErrMsgString("Patient Discharged!!!");
			      formBean.setStrCrNo("");
			      formBean.setStrAdmnNo("");
			      formBean.setStrRePrintCharge("0");
			      retVal = false;
			    }
			    else                
			    {          //commented for UAT point
			      retVal = true;
			      formBean.setStrErrMsgString("");
			    }
			}
			else
			{
				if(!formBean.getStrSelCmbVal().trim().equals("2")) 
			    {
				    if(formBean.getStrAdmnStatusCode().equals("12"))
					{
						if(formBean.getStrPatDeadCode().equals("1"))
						{
							formBean.setStrErrMsgString("Patient is Dead!!");
							formBean.setStrCrNo("");
							formBean.setStrAdmnNo("");
							formBean.setStrRePrintCharge("0");
							retVal= false;
						}
						else
						{
							retVal=true;
							formBean.setStrErrMsgString("");
						}
					}
					else
					{
						formBean.setStrErrMsgString("Patient not Admitted!!");
						formBean.setStrCrNo("");
						formBean.setStrAdmnNo("");
						formBean.setStrRePrintCharge("0");
						retVal= false;
					}
			    }
			    else
			    {
			       formBean.setStrErrMsgString("Patient not Discharged!!!");
				   formBean.setStrCrNo("");
				   formBean.setStrAdmnNo("");
				   formBean.setStrRePrintCharge("0");
				   retVal = false;
			    }
			}*/

			if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
			}


		   }catch(Exception e){
			   formBean.setStrErrMsgString(e.getMessage());
			   formBean.setStrMsgType("1");
			   HisException eObj = new HisException("IPD-->Admission Reprint", "AdmissionReprintTransDATA-->patStatusCode()", formBean.getStrErrMsgString());
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}finally {
			if (vo != null) vo = null;
			if(formBean != null) formBean = null;
		}
		return retVal;
	}
	public static void visitPass(AdmissionReprintTransFB formBean,HttpServletRequest request,HttpServletResponse response)
	{
		AdmissionReprintTransVO vo=null;
		AdmissionReprintTransBO bo=null;
		String printPassHLP="";
		try
			{
			    vo = new AdmissionReprintTransVO();
				bo = new AdmissionReprintTransBO();
				vo = (AdmissionReprintTransVO)TransferObjectFactory.populateData("ipd.transactions.AdmissionReprintTransVO",formBean);
				bo.visitPass(vo);
				printPassHLP=AdmissionReprintTransHLP.visitPass(vo);   
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(printPassHLP);
			}
		catch(Exception e)
		{
			   formBean.setStrErrMsgString(e.getMessage());
			   formBean.setStrMsgType("1");
			   HisException eObj = new HisException("IPD-->Admission Reprint", "AdmissionReprintTransDATA-->visitPass()", formBean.getStrErrMsgString());
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
	}
	/**
	 * This function invoke TransferObjectFactory.populateData() to transfer the values of all attributes of form bean into vo and the invoke bo insert method
	 * @param formBean
	 */
	public static void insert(AdmissionReprintTransFB formBean,HttpServletRequest request, HttpServletResponse response)
	{
		AdmissionReprintTransVO vo=null;
		AdmissionReprintTransBO bo=null;
		
		try
		{
				vo = new AdmissionReprintTransVO();
				bo = new AdmissionReprintTransBO();
				vo = (AdmissionReprintTransVO)TransferObjectFactory.populateData("ipd.transactions.AdmissionReprintTransVO",formBean);			
				bo.insert(vo);
				formBean.setStrSaveFlag("1");
				formBean.setStrPatientCrNo(formBean.getStrCrNo());
				formBean.setStrCrNo("");
				vo.setStrMsgType("0");
		System.out.println("formBean.getRePrintType().trim():::"+formBean.getRePrintType().trim());
				if(vo.getStrMsgType().equals("0"))
				{
					/*if(formBean.getRePrintType().trim().equals("1"))	
						   ADTPrintingTransHLP.AdmissionReprint(vo,request);
						
					else */if(formBean.getRePrintType().trim().equals("3"))
						   ADTPrintingTransHLP.VisitorPass(vo,request);
					else if(formBean.getRePrintType().trim().equals("2"))
					{
						showReport(formBean, request, response);
						formBean.setStrSaveFlag("2");
					}
					if(formBean.getRePrintType().trim().equals("2"))
					{
						formBean.setStrSaveFlag("2");
						formBean.setStrSaveStatus("1");
					}
					formBean.setStrNormalMsgString("Record Saved Successfully/Data Printed Successfully");
				}
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}				
			}
			catch(Exception e)
			{
				formBean.setStrErrMsgString(e.getMessage());
				formBean.setStrMsgType("1");
				HisException eObj = new HisException("ADT-->Admission Reprint", "AdmissionReprintTransDATA-->insert()", formBean.getStrErrMsgString());
				formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				eObj = null;
			}
			finally 
			{
				if (vo != null) vo = null;				
			}
		}
	
	
	public static void showReport(AdmissionReprintTransFB formBean,
			HttpServletRequest request, HttpServletResponse response){

		//ResourceBundle rsBundle = ResourceBundle.getBundle(pathFileName);
		String path = HisUtil.getParameterFromHisPathXML("IPD_PATH");

		ReportUtil report = null;

		try {

			report = new ReportUtil();
			
			String strAdmNo = formBean.getStrAdmnNo();
									
		//	String strHospCode = formBean.getStrHospitalCode();

			String strFileName = path + File.separator + strAdmNo + ".pdf";

			File f = new File(strFileName);

			if (f.exists()) {

				report.displayReportFromFile(response, "IPD", strAdmNo, "pdf");

			} else {
				
				throw new Exception(strFileName +" File Not Found");
			}
		} catch (Exception e) {
			HisException eObj = new HisException("IPD-->Admission Reprint", "AdmissionReprintTransDATA-->showReport()", e.getMessage());
			try {
				response.getWriter().write("ERROR###Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				new HisException("IPD-->Admission Reprint", "AdmissionReprintTransDATA-->showReport()", e1.getMessage());
			}
			eObj = null;
			
		}

	}


	public static void admissionList(AdmissionReprintTransFB formBean) 
	{
		AdmissionReprintTransVO vo = null;
		AdmissionReprintTransBO bo= null;
		try 
		{
			vo = new AdmissionReprintTransVO();
			bo = new AdmissionReprintTransBO();
			
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			String strAdmissionList = "";
			
			try 
			{
				bo.admissionList(vo);
				strAdmissionList = AdmissionReprintTransHLP.admissionList(vo);
			} 
			catch (Exception e) 
			{
				throw e;
			}
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrErrMsgString());
			}

			formBean.setStrPatientAdmndtl(strAdmissionList);
		} 
		catch (Exception e) 
		{
			vo.setStrErrMsgString(e.getMessage());
			vo.setStrMsgType("1");
			HisException eObj = new HisException("IPD-->Admission Reprint", "AdmissionReprintTransDATA-->detail()", vo.getStrErrMsgString());

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