package ipd.transactions;
import javax.servlet.http.HttpServletRequest;
import ipd.IpdConfigUtil;
import ipd.ADTPrintingTransHLP;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;

public class VisitorPassTransDATA {
	
	/**
	 * This function is used to set initial parameters required to display on main page 
	 * @param formBean
	 */
	public static void detail(VisitorPassTransFB formBean) {
		VisitorPassTransVO voObj=null;
		VisitorPassTransBO bo= null;
		int err=0;
		try {
			voObj = new VisitorPassTransVO();
			bo = new VisitorPassTransBO();
			
			String strPatientAdmndtl = "";
			
			try{
			 strPatientAdmndtl = hisglobal.tools.hlp.PatientDtlHLP.patientDtl(formBean.getStrCrNo(), false);
			}catch(Exception e){
				throw e;
			}
			
			
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrNewRenew(formBean.getStrNewRenew());
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			IpdConfigUtil icu = new IpdConfigUtil(formBean.getStrHospitalCode());
			voObj.setStrRFPassValidity(icu.getStrRenewFreePassValidity());
			voObj.setStrRPPassValidity(icu.getStrRenewPaidPassValidity());
			voObj.setStrNoFreePass(icu.getStrNoOfFreePass());
			voObj.setStrNoPaidPass(icu.getStrNoOfPaidPass());
			bo.setvisitPatientDtl(voObj);
			
			if(voObj.getStrMsgType().equals("1"))
			{
				throw new Exception(voObj.getStrMsgString());
			}
					
			if(voObj.getStrNewRenew().equals("0"))
			{
				formBean.setStrPatientAdmndtl(strPatientAdmndtl);
				formBean.setVisitorNewPassDetail(VisitorPassTransHLP.getVisitorNewPassDetail(voObj));
				formBean.setStrAdmnNo(voObj.getStrAdmnNo());
				formBean.setStrAdmnDate(voObj.getStrAdmnDate());
				formBean.setStrWard(voObj.getStrWard());
				formBean.setStrRoomBed(voObj.getStrRoomBed());
				formBean.setStrNoFreePass(voObj.getStrNoFreePass());
				formBean.setStrNoPaidPass(voObj.getStrNoPaidPass());
				formBean.setStrcountFree(voObj.getStrcountFree());
				formBean.setStrcountPass(voObj.getStrcountPass());
				formBean.setStrRFPassValidity(voObj.getStrRFPassValidity());
				formBean.setStrRPPassValidity(voObj.getStrRPPassValidity());
			}
			else
			{
				/**********HLP ERROR TRAP LOGIC*********/
				String temp = VisitorPassTransHLP.getVisitorPassDetail(voObj);
				String[] TestData = temp.split("\\####");
				if(TestData[0].equals("ERROR"))
				{
					err=1;
			       	throw new Exception(TestData[1]);
				} 
				else
				{ 
					formBean.setVisitorPassDetail(temp);
					formBean.setStrPatientAdmndtl(strPatientAdmndtl);
					formBean.setStrAdmnNo(voObj.getStrAdmnNo());
					formBean.setStrAdmnDate(voObj.getStrAdmnDate());
					formBean.setStrWard(voObj.getStrWard());
					formBean.setStrRoomBed(voObj.getStrRoomBed());
					formBean.setStrRFPassValidity(voObj.getStrRFPassValidity());
					formBean.setStrRPPassValidity(voObj.getStrRPPassValidity());
				}
			}
		} catch (Exception e) {
		  if(err==1)
			formBean.setStrMsgString("No Pass Found to be Renewed !!");
		  else
		  {
			   voObj.setStrMsgString(e.getMessage());
			   voObj.setStrMsgType("1");
			   HisException eObj = new HisException("IPD-->Visitor Pass", "VisitorPassTransDATA-->detail()", voObj.getStrMsgString());
			   
			   
			   if(e.getMessage().startsWith("Invalid")){
				  
				   formBean.setStrMsgString(e.getMessage());
				   formBean.setStrMsgType("1");
				   formBean.setStrCrNo("");
			   }else{
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   }	   
			   eObj = null;
		  }	   
		}
		finally {
			if(voObj != null) voObj = null;
			if(formBean != null) formBean = null;
		}
	}
	
	
	/**
	 * This function invoke TransferObjectFactory.populateData() to transfer the values of all attributes of form bean into vo and the invoke bo insert method
	 * @param formBean
	 */
	public static void insert(VisitorPassTransFB formBean,HttpServletRequest request)
	{
		VisitorPassTransVO vo=null;
		VisitorPassTransBO bo=null;
		try	{
				vo = (VisitorPassTransVO) TransferObjectFactory.populateData("ipd.transactions.VisitorPassTransVO", formBean);
				bo = new VisitorPassTransBO();
								
				bo.insert(vo);
				
				/**
				 * 
				 */
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				bo.getVisitorPassDtl(vo);
				
				
				if(vo.getStrMsgType().equals("0")){
					formBean.setStrNormalMsg("Record saved successfully");
				}
				//System.out.println("DATA");
				if(vo.getStrMsgType().equals("1")){
					throw new Exception(vo.getStrMsgString());
				}
				//System.out.println("DATA1");
				ADTPrintingTransHLP.VisitorPass(vo,request);
				formBean.setStrCrNo("");
					
			}
			catch(Exception e)
			{
				//e.printStackTrace();
				formBean.setStrMsgString(e.getMessage());
				formBean.setStrMsgType("1");
				HisException eObj = new HisException("IPD-->Visitor Pass", "VisitorPassTransDATA-->insert()", formBean.getStrMsgString());
				formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				eObj = null;
				
			}
			finally {
				if (vo != null) vo = null;
				if(formBean != null) formBean = null;
			}
		}
	
	public static boolean patStatusCode(VisitorPassTransFB formBean){
		VisitorPassTransVO vo = null; 
		VisitorPassTransBO bo = null;
		boolean retVal=false;
		try{
			
			vo = new VisitorPassTransVO();
			bo = new VisitorPassTransBO();
			
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrNewRenew(formBean.getStrNewRenew());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.setpatStatusCode(vo);
			formBean.setStrAdmnStatusCode(vo.getStrAdmnStatusCode());
			formBean.setStrPatDeadCode(vo.getStrPatDeadCode()); 
			if(formBean.getStrAdmnStatusCode().equals("12"))
			{
				if(formBean.getStrPatDeadCode().equals("1"))
				{
					formBean.setStrMsgString("Patient is Dead!!");
					retVal= false;
				}
				else
				{
					retVal=true;
				}	
			}
			else
			{
				formBean.setStrMsgString("Patient not Admitted!!");
				retVal= false;
			}
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		   }catch(Exception e){
			   formBean.setStrMsgString(e.getMessage());
			   formBean.setStrMsgType("1");
			   HisException eObj = new HisException("IPD-->Visitor Pass", "VisitorPassTransDATA-->insert()", formBean.getStrMsgString());
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}finally {
			if (vo != null) vo = null;
			if(formBean != null) formBean = null;
		}
		return retVal;
	}

}
