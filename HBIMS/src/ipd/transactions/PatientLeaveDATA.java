package ipd.transactions;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientLeaveDATA
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
      util=new HisUtil("transaction","PatientLeaveHLP");
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
	///////////////////////////AJAX RESPONSE//////////////////////////////////////
	public static String AjaxResponse(PatientLeaveFB formBean,String strValmode,HttpServletRequest request)
	{
	    PatientLeaveVO vo = new PatientLeaveVO();
		String movDetails = null;
		try
		{
			vo = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",formBean);
			String tmp=request.getParameter("currDtl");
			String currStat[]=tmp.replace('^','#').split("#");
			vo.setStrdeptproperty(formBean.getStrdeptproperty());
		    vo.setStrUnitName("<option>"+currStat[1]+"</option>");
		    vo.setStrWard("<option>"+currStat[2]+"</option>");
		    vo.setStrRoom("<option>"+currStat[3]+"</option>");
		    vo.setStrBed("<option>"+currStat[4]+"</option>");
		    vo.setStrwardType(formBean.getStrwardType());
		    vo.setStrRoomType(formBean.getStrRoomType());
		    vo.setStrBedType(formBean.getStrBedType());
		    vo.setStrServArea(formBean.getStrServArea());
		    vo.setStrIsBedVacant(formBean.getStrIsBedVacant());
			movDetails =  PatientLeaveHLP.jd(vo);
		    formBean = (PatientLeaveFB)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveFB",vo);
	        formBean.setStrErrMsgString(vo.getStrErrMsgString());
	        formBean.setStrMsgType(vo.getStrMsgType());
		   if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
		}
	    } catch (Exception e) {
	    	  String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveDATA->AjaxResponse()", strmsgText);
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		} finally {
			formBean.setStrCtDate(vo.getStrCtDate());
		}
     return movDetails;
	}

	public static void getRsnRmk(PatientLeaveFB formBean)
	{
		
		PatientLeaveVO vo = new PatientLeaveVO();
		PatientLeaveBO bo= new  PatientLeaveBO();
	//	System.out.println("rsnRmk DATA");
		try{
		vo = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",formBean);	
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
			//e.printStackTrace();
			 String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveDATA->getRsnRmk()", strmsgText);
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
		finally{
		bo=null;
		vo=null;}
	}
	///////////////////////////////////////////////////////////////////////////////

	public static void initClientDetail(String strChk,PatientLeaveFB formBean)
	{
		PatientLeaveVO vo = new PatientLeaveVO();
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
			   HisException eObj = new HisException("IPD", "PatientLeaveDATA->initClientDetail()", strmsgText);
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

//this function is called for populating ward value on the basis of unit combo
/*
public static void ward(PatientLeaveFB beanObj,HttpServletRequest request, HttpServletResponse response){
	PatientLeaveVO VO = null;
	String strmsgStr = "";
	String modWardTpe= request.getParameter("modWardTpe");
	String temp[]=modWardTpe.replace('^', '#').split("#");
	System.out.println("modUnit in ward=="+modWardTpe);
	//VO.setStrHospitalCode(beanObj.getStrHospitalCode());
	try {
		//we do not need to call populate() function.
		//that is why we create an instance and pass it to the BO
		VO = new PatientLeaveVO();
		VO = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",beanObj);
		PatientLeaveBO bo = new PatientLeaveBO();
		//System.out.println("ward codein cnt=="+VO.getStrprivatewardName());
		VO.setStrDeptCode(temp[0]);
	    VO.setStrDeptUnitCode(temp[1]);
	    VO.setStrWardTypeCode(temp[2]);
		bo.ward(VO);
		beanObj.setStrErrMsgString(VO.getStrErrMsgString());
		beanObj.setStrMsgType(VO.getStrMsgType());
			if(beanObj.getStrMsgType().equals("1")) {		//error
			throw new Exception(beanObj.getStrErrMsgString());
		}
		else {

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(VO.getStrWard());
			//beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
		}
		}
		catch(Exception e) {
			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveDATA->ward()", strmsgText);
			   beanObj.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
		finally {
			if(VO != null) VO = null;
			if(beanObj != null) beanObj = null;
		}
	}*/
//this function is called for populating room value on the basis of ward combo

public static void room(PatientLeaveFB beanObj,HttpServletRequest request, HttpServletResponse response){

	PatientLeaveVO VO = null;
	String modRoomType= request.getParameter("modRoomType");
	String temp[]=modRoomType.replace('^', '#').split("#");
	//System.out.println("modRoomType=="+modRoomType);
	try {
		VO = new PatientLeaveVO();
		VO = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",beanObj);
		PatientLeaveBO bo = new PatientLeaveBO();
	    VO.setStrWardCode(temp[0]);
		VO.setStrRoomTypeCode(temp[1]);
		VO.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		if(temp.length>2)
		{
			/* Changed By Amit Kumar Ateria on 19 Jan 2011 to check MS Approval for Private Ward*/
			IpdConfigUtil ipdC=new IpdConfigUtil(VO.getStrHospCode());
			//if (temp[3].equals("11")) {
			if (temp[3].equals(ipdC.getStrPrivateWardType())) 
		 {
		  VO.setStrMsApprovalFlag(temp[2]);
		  VO.setStrWardTypeCode(temp[3]);
		  VO.setStrCrNo(temp[4]);
		  VO.setStrAdviceAdmNo(temp[5]);
		 }
		}
		bo.room(VO);
		beanObj.setStrRoomCode(VO.getStrRoomCode());
		beanObj.setStrErrMsgString(VO.getStrErrMsgString());
		beanObj.setStrMsgType(VO.getStrMsgType());
		if(beanObj.getStrMsgType().equals("1")) {		//error
			throw new Exception(beanObj.getStrErrMsgString());
		}
		else {
			    response.setHeader("Cache-Control", "no-cache");
		      //  System.out.println("response str->"+"<option selected value="+VO.getStrRoomCode()+">"+VO.getStrRoom()+"</option>");
			    response.getWriter().print(VO.getStrRoom());//beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis())
		}
		}
		catch(Exception e) {
			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveDATA->room()", strmsgText);
			   beanObj.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
		finally {
			if(VO != null) VO = null;
			if(beanObj != null) beanObj = null;
		}
	}
public static void bed(PatientLeaveFB beanObj,HttpServletRequest request, HttpServletResponse response){	
	
	PatientLeaveVO VO = null;
	String modBedType= request.getParameter("modBedType");
	String temp[]=modBedType.replace('^', '#').split("#");
//	System.out.println("modBedType=="+modBedType);
	//VO.setStrHospitalCode(beanObj.getStrHospitalCode());
	try {		
		//we do not need to call populate() function.
		//that is why we create an instance and pass it to the BO
		VO = new PatientLeaveVO();
		VO = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",beanObj);
		PatientLeaveBO bo = new PatientLeaveBO();
		//System.out.println("ward codein cnt=="+VO.getStrprivatewardName());
	    VO.setStrWardCode(temp[0]);
	    VO.setStrRoomCode(temp[1]);
	    VO.setStrBedTypeCode(temp[2]);
	    VO.setStrDeptUnitCode(temp[3]);
		bo.bed(VO);
		beanObj.setStrMsgType(VO.getStrMsgType());
		if(beanObj.getStrMsgType().equals("1")) {		//error
			throw new Exception(beanObj.getStrErrMsgString());
		}
		else {
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(VO.getStrBed());
			
			//beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
	
		}
		}
		catch(Exception e) {
			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveDATA->bed()", strmsgText);
			   beanObj.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
		finally {
			if(VO != null) VO = null;
			if(beanObj != null) beanObj = null;
		}
	}

public static void beddetail(PatientLeaveFB formBean,HttpServletRequest request,HttpServletResponse response) {
	
	//System.out.println("inside DATA-->beddetail");
	
	PatientLeaveVO VO=new PatientLeaveVO();
	PatientLeaveBO BO=new PatientLeaveBO();
	VO = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",formBean);
	try {
	VO = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",formBean);	
	String tmp=request.getParameter("modPopUp");
	String temp[]=tmp.replace('^','#').split("#");
	VO.setStrWardCode(temp[0]);
	VO.setStrRoomCode(temp[1]);
	VO.setStrBedTypeCode(temp[2]);
	VO.setStrDeptUnitCode(temp[3]);
	VO.setStrPopUp("1");	
	BO.setBedDetails(VO);
	String strbed=PatientLeaveHLP.getBedDetails(VO);
	//System.out.println("strbed String in data=="+strbed);
	formBean.setStrBedProperty(strbed);		
		formBean.setStrMsgType(VO.getStrMsgType());
			if(formBean.getStrMsgType().equals("1")) {		//error
			throw new Exception(formBean.getStrErrMsgString());
		}
	}
	catch(Exception e) {
		String strmsgText = e.getMessage();
		   HisException eObj = new HisException("IPD", "PatientLeaveDATA->beddetail()", strmsgText);
		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		     eObj = null;
	}
	finally {
		if(VO != null) VO = null;
		if(formBean != null) formBean = null;
	}
}
/************INIT All Combos *******************/
public static void initBedStatus(PatientLeaveFB formBean,HttpServletRequest request) {
	PatientLeaveVO vo=new PatientLeaveVO();
	PatientLeaveBO bo=new PatientLeaveBO();
	try {
		vo = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",formBean);
		vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
		vo.setStrWardCode(request.getParameter("wardCode"));
		vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
		vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
		vo.setStrDeptCode(request.getParameter("deptCode"));
		vo.setStrCrNo(request.getParameter("crNo"));
		bo.setBedStatusDtl(vo);
		formBean.setStrwardType(vo.getStrwardType());
		formBean.setStrRoomType(vo.getStrRoomType());
		formBean.setStrBedType(vo.getStrBedType());
		formBean.setStrServArea(vo.getStrServArea());
		formBean.setStrErrMsgString(vo.getStrErrMsgString());
		  formBean.setStrMsgType(vo.getStrMsgType());
		   if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
		}
	} catch (Exception e) {
		String strmsgText = e.getMessage();
		   HisException eObj = new HisException("IPD", "PatientLeaveDATA->room()", strmsgText);
		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		     eObj = null;
	}
}

    public static boolean admstat(PatientLeaveFB formBean)
    {
    	PatientLeaveVO vo=new PatientLeaveVO();
    	String isDead="";
    	boolean retVal=false;
    	try
    	{
    	  vo = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",formBean);
    	  PatientLeaveBO.admstat(vo);
    	//  System.out.println("admitted->"+admitted);
    	//  System.out.println("onleave->"+onLeave);
    //	  System.out.println("admStstCode->"+vo.getStrAdmStatCode());
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
    	//	  System.out.println("isDead->"+isDead);
    		  if(isDead.equals("1")){
    			  formBean.setStrErrMsgString("Patient is Dead!!");
        		  formBean.setStrCrNo("");  
        		  retVal=false;
    		  }else if(vo.getStrBelongingClear().equals("1")){
    			  formBean.setStrErrMsgString("Patient Belonging has not been Cleared!!");
        		  formBean.setStrCrNo("");  
        		  retVal=false;
    		  }
    		  else
    			  retVal=true;
    	  }
    	  else if(vo.getStrAdmStatCode().equals(onLeave))
    	  {
    		  formBean.setStrjHLP("1");
    		  formBean.setStrAdmStatCode(onLeave);
    		  retVal=true;
    	  }
    	  else
    	  {
    		  formBean.setStrErrMsgString("Patient Not Admitted!!");
    		  formBean.setStrCrNo("");
    		  retVal=false;
    	  }
    	//  System.out.println("strJHLP_DATA_admStat->"+formBean.getStrjHLP());
    	//  System.out.println("StrAdmStatCode_DATA_admStat->"+formBean.getStrAdmStatCode());
	   } 
      }
      catch(Exception e)
      {
    	   String strmsgText = e.getMessage();
  		   HisException eObj = new HisException("IPD", "PatientLeaveDATA->admstat()", strmsgText);
  		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
  		   eObj = null;
      }
    	return retVal;
    }
    
    public static void upadateLeaveApprovalDtl(PatientLeaveFB formBean)
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
     //   System.out.println("isBedVacantDATA->"+formBean.getStrIsBedVacant());
        PatientLeaveVO vo = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",formBean);
        PatientLeaveBO.upadateLeaveApprovalDtl(vo);
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
   		   HisException eObj = new HisException("IPD", "PatientLeaveDATA->upadateLeaveApprovalDtl()", strmsgText);
   		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
   		     eObj = null;
    	}
    }
    
    public static void setLeaveDtl(PatientLeaveFB formBean)
	{
    	PatientLeaveVO vo = new PatientLeaveVO();
    	PatientLeaveBO bo= new  PatientLeaveBO();
    	boolean retVal=false;
		try{
		vo = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",formBean);
		vo.setStrCrNo(formBean.getStrCrNo());
		vo.setStrAdmStatCode(formBean.getStrAdmStatCode());
		retVal=bo.setLeaveDtl(vo);
	 if(retVal==true)
	 {	
		formBean.setStrCtDate(now(DATE_FORMAT_NOW));
		formBean.setStrProbJoinDateForExternalInv(getASNextDate(DATE_FORMAT_NOW));
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
		formBean.setStrValidFrom(vo.getStrValidFrom());
		formBean.setStrValidTo(vo.getStrValidTo());
		formBean.setStrLeaveBy(vo.getStrLeaveBy());
		formBean.setStrAdviceL(vo.getStrAdviceL());
		formBean.setStrErrMsgString(vo.getStrErrMsgString());
		formBean.setStrMsgType(vo.getStrMsgType());
	 }
	 else
	 {
		 formBean.setStrErrMsgString(vo.getStrErrMsgString());
		 formBean.setStrCrNo("");
	 }
		   if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
		}
		}
		catch(Exception e)
		{
			 String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveDATA->setLeaveDtl()", strmsgText);
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
		finally{
		bo=null;
		vo=null;}
	}
   
    public static void insertLJ(PatientLeaveFB formBean)
    {
    	String tmp1=formBean.getCurAdmNo();
    	String tmp2=formBean.getCurDept_Unt_RomCode();
    	String tmp3=formBean.getCurWrdBedCode();
    //	String tmp4=formBean.getCurDtUtWrRmBd();
    //	String tmp5=formBean.getCurAdmAdvNo();
    	String tmpArray1[]=tmp1.replace('^','#').split("#");
    	String tmpArray2[]=tmp2.replace('^','#').split("#");
    	String tmpArray3[]=tmp3.replace('^','#').split("#");
    	//String tmpArray4[]=tmp4.replace('^','#').split("#");
    	//String tmpArray5[]=tmp5.replace('^','#').split("#");
    	try{
    		formBean.setStrAdmNo(tmpArray1[0]);
            formBean.setStrDeptCode(tmpArray2[0]);
            formBean.setStrDeptUnitCode(tmpArray2[1]);
            formBean.setStrWardCode(tmpArray3[0]);
            formBean.setStrRoomCode(tmpArray2[2]);
            formBean.setStrBedCode(tmpArray3[1]);
    	//    System.out.println("strAdmStatCode during Insert->"+formBean.getStrAdmStatCode());
    	    PatientLeaveVO vo = (PatientLeaveVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveVO",formBean);
    	//   System.out.println("Bed Code"+vo.getStrBedCode());
    	    PatientLeaveBO.insertLJ(vo);
    	    formBean.setStrMsgType(vo.getStrMsgType());
		    if(formBean.getStrMsgType().equals("1")) 
		    {		//error
		    	formBean.setStrErrMsgString(vo.getStrErrMsgString());
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
   		   HisException eObj = new HisException("IPD", "PatientLeaveDATA->insertLJ()", strmsgText);
   		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
   		   formBean.setStrCrNo("");  
   		   eObj = null;
    	}
    }
}
