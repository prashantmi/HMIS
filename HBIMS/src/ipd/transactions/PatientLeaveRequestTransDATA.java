package ipd.transactions;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class PatientLeaveRequestTransDATA
{
	private static ResourceBundle hisProp = ResourceBundle.getBundle("ipd.hisIpdProperties");
	public static String admitted = hisProp.getString("Admitted");
	public static String onLeave = hisProp.getString("On_Leave");
	public static  String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
  public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy";
  private boolean retValue = false;
  private String errMsg = "";
  public static  String now(String frmt)
    {
      HisUtil util=null;
      String a="";
      util=new HisUtil("transaction","PatientLeaveRequestTransHLP");
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
  
  /* To get next date of current date
   added by Debashis on 26th Nov 2012 */
  public static String getASNextDate(String dtFormat) throws Exception {

		String dtStr = "";
		String defFormat = "";
		Calendar c = null;
		SimpleDateFormat dateFormat = null;

		defFormat = dtFormat;
		if (defFormat.equals(""))
			defFormat = "dd/MM/yyyy";

		try {
			c = Calendar.getInstance();
			c.add (Calendar.DAY_OF_YEAR, 1);
			dateFormat = new SimpleDateFormat(defFormat);
			dtStr = dateFormat.format(c.getTime());
		} catch (Exception e) {
			throw new Exception();
		} finally {
			if (c != null) {
				c.clear();
				c = null;
			}
			if (dateFormat != null)
				dateFormat = null;
		}

		return dtStr;
	}
	public static void getRsnRmk(PatientLeaveRequestTransFB formBean)
	{
		PatientLeaveRequestTransVO vo = new PatientLeaveRequestTransVO();
		PatientLeaveRequestTransBO bo= new  PatientLeaveRequestTransBO();
		try{
		bo.getRsnRmk(vo);
		formBean.setStrCtDate(now(DATE_FORMAT_NOW));
		formBean.setStrProbJoinDateForExternalInv(getASNextDate(DATE_FORMAT_NOW));
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
			   HisException eObj = new HisException("IPD", "PatientLeaveRequestTransDATA->getRsnRmk()", strmsgText);
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
		finally{
		bo=null;
		vo=null;}
	}
	///////////////////////////////////////////////////////////////////////////////

	public static void initClientDetail(String strChk,PatientLeaveRequestTransFB formBean)
	{
		PatientLeaveRequestTransVO vo = new PatientLeaveRequestTransVO();
	    
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
			   HisException eObj = new HisException("IPD", "PatientLeaveRequestTransDATA->initClientDetail()", strmsgText);
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

	public static void getLeaveRequestValidate(PatientLeaveRequestTransFB formBean)
	{
	  PatientLeaveRequestTransVO vo=new PatientLeaveRequestTransVO();
	  PatientLeaveRequestTransBO bo=new PatientLeaveRequestTransBO();
	  boolean retVal=false;
	  try
	  {
		formBean.setStrCtDate(now(DATE_FORMAT_NOW));
		formBean.setStrProbJoinDateForExternalInv(getASNextDate(DATE_FORMAT_NOW));
		formBean.setStrEntryDate(now(DATE_FORMAT_NOWwt));
		vo = (PatientLeaveRequestTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveRequestTransVO",formBean);
		retVal=bo.getLeaveRequestValidate(vo); 
		formBean.setStrErrMsgString(vo.getStrErrMsgString());
		formBean.setStrMsgType(vo.getStrMsgType());
		if(formBean.getStrMsgType().equals("1")) 		//error
			throw new Exception(formBean.getStrErrMsgString());
	    else
	    {	
		  if(retVal==false)
		  {
		    formBean.setStrErrMsgString(vo.getStrErrMsgString());
		    formBean.setStrCrNo("");
		  }
	    }  
	  }
	  catch(Exception e)
	  {
		  String strmsgText = e.getMessage();
 		  HisException eObj = new HisException("IPD", "PatientLeaveRequestTransDATA->admstat()", strmsgText);
 		  formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
 		  eObj = null;  
	  }
	}

    public static boolean admstat(PatientLeaveRequestTransFB formBean)
    {
    	PatientLeaveRequestTransVO vo=new PatientLeaveRequestTransVO();
    	String isDead="";
    	boolean retVal=false;
    	try
    	{
    	  vo = (PatientLeaveRequestTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveRequestTransVO",formBean);
    	  PatientLeaveRequestTransBO.admstat(vo);
    	  formBean.setStrAdmStatCode(vo.getStrAdmStatCode());
    	  isDead=vo.getStrIsDead();
    	  formBean.setStrIsDead(vo.getStrIsDead());
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
  		   HisException eObj = new HisException("IPD", "PatientLeaveRequestTransDATA->admstat()", strmsgText);
  		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
  		     eObj = null;
    	}
    	return retVal;
    }
   
    public static void insertLJ(PatientLeaveRequestTransFB formBean)
    {
    	String tmp1=formBean.getCurAdmNo();
    	String tmp2=formBean.getCurDept_Unt_RomCode();
    	String tmp3=formBean.getCurWrdBedCode();
    //	String tmp4=formBean.getCurDtUtWrRmBd();
    //	String tmp5=formBean.getCurAdmAdvNo();
    	String tmpArray1[]=tmp1.replace('^','#').split("#");
    	String tmpArray2[]=tmp2.replace('^','#').split("#");
    	String tmpArray3[]=tmp3.replace('^','#').split("#");
    //	String tmpArray4[]=tmp4.replace('^','#').split("#");
    //	String tmpArray5[]=tmp5.replace('^','#').split("#");
    	try{
    	formBean.setStrAdmNo(tmpArray1[0]);
    	formBean.setStrDeptCode(tmpArray2[0]);
    	formBean.setStrDeptUnitCode(tmpArray2[1]);
    	formBean.setStrWardCode(tmpArray3[0]);
    	formBean.setStrRoomCode(tmpArray2[2]);
    	formBean.setStrBedCode(tmpArray3[1]);
    	formBean.setStrLeaveStatusCode("1");
    /*	if(formBean.getStrjHLP().equals("0"))
    	  formBean.setStrAdmStatCode(onLeave);
    	else
    	  formBean.setStrAdmStatCode(admitted);	*/
    //	System.out.println("strjHLP during Insert->"+formBean.getStrjHLP());
    //	System.out.println("strAdmStatCode during Insert->"+formBean.getStrAdmStatCode());
    	PatientLeaveRequestTransVO vo = (PatientLeaveRequestTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveRequestTransVO",formBean);
    	PatientLeaveRequestTransBO.insertLJ(vo);
    	formBean.setStrMsgType(vo.getStrMsgType());
		if(formBean.getStrMsgType().equals("1")) {	
			formBean.setStrErrMsgString(vo.getStrErrMsgString());//error
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
   		   HisException eObj = new HisException("IPD", "PatientLeaveRequestTransDATA->insertLJ()", strmsgText);
   		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
   		     eObj = null;
    	}
    }
}
