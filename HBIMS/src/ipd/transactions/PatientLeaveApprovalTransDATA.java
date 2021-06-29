package ipd.transactions;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import java.util.ResourceBundle;

public class PatientLeaveApprovalTransDATA
{
	private static ResourceBundle hisProp = ResourceBundle.getBundle("ipd.hisIpdProperties");
	public static String admitted = hisProp.getString("Admitted");
	public static String onLeave = hisProp.getString("On_Leave");
	public static  String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
  public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy";
    
  public static  String now(String frmt)
    {
      HisUtil util=null;
      String a="";
      util=new HisUtil("transaction","PatientLeaveApprovalTransHLP");
      try{
       a= util.getASDate(frmt);
      }
      catch(Exception e){
    	
      }
      /*Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
      return sdf.format(cal.getTime());*/
      return a;
    }

	
	public static void getRsnRmk(PatientLeaveApprovalTransFB formBean)
	{
		PatientLeaveApprovalTransVO vo = new PatientLeaveApprovalTransVO();
		PatientLeaveApprovalTransBO bo= new  PatientLeaveApprovalTransBO();
		try{
		vo = (PatientLeaveApprovalTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveApprovalTransVO",formBean);
		bo.getRsnRmk(vo);
		formBean.setStrCtDate(now(DATE_FORMAT_NOW));
  	    formBean.setStrAdmStatCode(vo.getStrAdmStatCode());
		formBean.setStrDisBy(vo.getStrDisBy());
		formBean.setStrDisRsn(vo.getStrDisRsn());
		formBean.setStrRmk(vo.getStrRmk());
		formBean.setStrRsn(vo.getStrRsn());
		formBean.setStrErrMsgString(vo.getStrErrMsgString());
		  formBean.setStrMsgType(vo.getStrMsgType());
		   if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
		}
		}
		catch(Exception e)
		{
			 String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveApprovalTransDATA->getRsnRmk()", strmsgText);
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
		finally{
		bo=null;
		vo=null;}
	}
	///////////////////////////////////////////////////////////////////////////////

	public static void initClientDetail(String strChk,PatientLeaveApprovalTransFB formBean)
	{
		PatientLeaveApprovalTransVO vo = new PatientLeaveApprovalTransVO();
	    vo.setStrChk(strChk);
		HisUtil util = null;
		try
		{
		   formBean.setStrCrNo(strChk);
		   formBean.setStrMsgType(vo.getStrMsgType());
		   formBean.setStrErrMsgString(vo.getStrErrMsgString());
		   if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
 		}
		}
		catch (Exception e) {
			 String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveApprovalTransDATA->initClientDetail()", strmsgText);
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		} finally {

			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
			if (util != null)
				util = null;
		}
	}


    public static boolean admstat(PatientLeaveApprovalTransFB formBean)
    {
    	PatientLeaveApprovalTransVO vo=new PatientLeaveApprovalTransVO();
    	String isDead="";
    	boolean retVal=false;
    	
    	try
    	{
    	  vo = (PatientLeaveApprovalTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveApprovalTransVO",formBean);
    	  PatientLeaveApprovalTransBO.admstat(vo);
    	//  System.out.println("admStstCode->"+vo.getStrAdmStatCode());
    	  formBean.setStrAdmStatCode(vo.getStrAdmStatCode());
    	  isDead=vo.getStrIsDead();
    	  formBean.setStrIsDead(vo.getStrIsDead());
    	//  System.out.println("isDead->"+isDead);
    	  //vo.setStrAdmStatCode(admitted);//hard code...delete after
    	  //isDead="1";//hard code...delete after
    	  formBean.setStrErrMsgString(vo.getStrErrMsgString());
    	  formBean.setStrMsgType(vo.getStrMsgType());
	   if(formBean.getStrMsgType().equals("1")) 
	   {		//error
			throw new Exception(formBean.getStrErrMsgString());
	   }
	   else
	   {	   
    	  if(vo.getStrAdmStatCode().equals(admitted))
    	  {
    		  formBean.setStrjHLP("0");
    		  formBean.setStrAdmStatCode(admitted);
    		//  System.out.println("isDead->"+isDead);
    		  if(isDead.equals("1"))
    		  {
    			  formBean.setStrErrMsgString("Patient is Dead!!");
        		  formBean.setStrCrNo("");  
        		  retVal=false;
    		  }
    		  else
    			  retVal=true;
    	  }
    	  else if(vo.getStrAdmStatCode().equals(onLeave))
    	  {
    		  formBean.setStrErrMsgString("Patient is on Leave!!");
    		  formBean.setStrCrNo("");  
    		  retVal=false;
    	  }
    	  else
    	  {
    		  formBean.setStrErrMsgString("Patient Not Admitted!!");
    		  formBean.setStrCrNo("");
    		  retVal=false;
    	  }
    	}
    	}
    	catch(Exception e)
    	{
    		String strmsgText = e.getMessage();
  		   HisException eObj = new HisException("IPD", "PatientLeaveApprovalTransDATA->admstat()", strmsgText);
  		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
  		     eObj = null;
    	}
    	return retVal;
    }
   
    public static void upadateLeaveApprovalDtl(PatientLeaveApprovalTransFB formBean)
    {
    	try{
    	PatientLeaveApprovalTransVO vo = (PatientLeaveApprovalTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveApprovalTransVO",formBean);
    	vo = (PatientLeaveApprovalTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveApprovalTransVO",formBean);
    	PatientLeaveApprovalTransBO.upadateLeaveApprovalDtl(vo);
    	formBean.setStrErrMsgString(vo.getStrErrMsgString());
    	  formBean.setStrMsgType(vo.getStrMsgType());
		   if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
		}
		   else
		   {
			  formBean.setStrNormalMsgString(vo.getStrNormalMsgString()); 
			  formBean.setStrCrNo("");  
		   }
    	}
    	catch(Exception e)
    	{
    		String strmsgText = e.getMessage();
   		   HisException eObj = new HisException("IPD", "PatientLeaveApprovalTransDATA->upadateLeaveApprovalDtl()", strmsgText);
   		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
   		   formBean.setStrCrNo("");  
   		   eObj = null;
    	}
    }
    
    /***select****/
    public static boolean setLeaveDtl(PatientLeaveApprovalTransFB formBean)
	{
		PatientLeaveApprovalTransVO vo = new PatientLeaveApprovalTransVO();
		PatientLeaveApprovalTransBO bo= new  PatientLeaveApprovalTransBO();
		boolean retVal=false;
		try{
		vo = (PatientLeaveApprovalTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveApprovalTransVO",formBean);	
		vo.setStrCrNo(formBean.getStrCrNo());	
		retVal=bo.setLeaveDtl(vo);
	if(retVal==true)
	{	
		formBean.setStrCtDate(now(DATE_FORMAT_NOW));
  	    formBean.setStrAdmStatCode(vo.getStrAdmStatCode());
		formBean.setStrDisBy(vo.getStrDisBy());
		formBean.setStrDisRsn(vo.getStrDisRsn());
		formBean.setStrRmk(vo.getStrRmk());
		formBean.setStrLeaveSlNo(vo.getStrLeaveSlNo());
		formBean.setStrRsn(vo.getStrRsn());
	//	System.out.println("leaveReason->"+formBean.getStrRsn());
		formBean.setStrPatCondL(vo.getStrPatCondL());
		formBean.setStrPhoneNo(vo.getStrPhoneNo());
		formBean.setStrAddrLeave(vo.getStrAddrLeave());
		formBean.setStrIsBedVacant(vo.getStrIsBedVacant());
		formBean.setStrEntryDate(vo.getStrEntryDate());
		formBean.setStrLeavReqFrmDate(vo.getStrLeavReqFrmDate());
		formBean.setStrLeavReqToDate(vo.getStrLeavReqToDate());
		formBean.setStrMsgType(vo.getStrMsgType());
		formBean.setStrLeaveTypeText(vo.getStrLeaveTypeText());
		//formBean.setStrAdmNo(vo.getStrAdmNo());
	}
	else
	  {
		if(formBean.getStrMsgType().equals("1")) 		//error
			throw new Exception(formBean.getStrErrMsgString()); 
		else
		{
		formBean.setStrErrMsgString(vo.getStrErrMsgString());
		formBean.setStrCrNo("");
		}
	  }
	}
	catch(Exception e)
	{
			 String strmsgText = e.getMessage();
			 HisException eObj = new HisException("IPD", "PatientLeaveApprovalTransDATA->setLeaveDtl()", strmsgText);
			 formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			 eObj = null;
			 formBean.setStrCrNo("");
	}
		finally{
		bo=null;
		vo=null;}
	 return retVal;	
	}
}
