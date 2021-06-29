package ipd;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import ipd.transactions.*;

import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisPrinter;
import hisglobal.utility.HisUtil;
import hisglobal.exceptions.HisException;

public class ADTPrintingTransHLP
{
	public static String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
	public static String DATE_FORMAT_NOW = "dd-MMM-yyyy";

	public static final char ESCAPECHAR = 27;
	public static final String BOLDON = ESCAPECHAR+""+'E';
	public static final String BOLDOFF = ESCAPECHAR+""+'F';
	
	public static final char FORMFEED = 12;
	
	public static String now(String frmt) {
		HisUtil util = null;
		String a = "";
		util = new HisUtil("transaction", " ADTPrintingTransHLP");
		try {
			a = util.getASDate(frmt);
		} catch (Exception e) {
			new HisException("ADTPrintingTransHLP",
					"ADTPrintingTransHLP.now()-->", e.getMessage());
		}
		/*
		 * Calendar cal = Calendar.getInstance(); SimpleDateFormat sdf = new
		 * SimpleDateFormat(DATE_FORMAT_NOW); return sdf.format(cal.getTime());
		 */
		return a;
	}

	public static String NewAdmission(PatientAdmissionTransVO vo, HttpServletRequest request)
	{
		String strResult = "",strHospitalCode;
		PrintData objPrintData = new PrintData();
		try
		{
			objPrintData.fileContents = new StringBuffer();
			objPrintData.iCountNoOfLines = 0;
			
			objPrintData = getBaseAdmissionSlip(vo, objPrintData);
			
			strHospitalCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
			strResult = printSettingAdmission(objPrintData.iCountNoOfLines, objPrintData.fileContents.toString(),strHospitalCode);
			System.out.println("DATA TO BE PRINTED\n\n" + strResult);
			// Printing Slip Twise
			//printData(strResult, "ADT", request);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return objPrintData.fileContents.toString();
	}

	public static String NewBorn(NewBornBabyTransVO vo, HttpServletRequest request)
	{
		String strResult = "",strHospitalCode="";
		PrintData objPrintData = new PrintData();
		try
		{
			objPrintData.fileContents = new StringBuffer();
			objPrintData.iCountNoOfLines = 0;
			
			objPrintData = getBaseAdmissionSlip(vo, objPrintData);

			strHospitalCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
			strResult = printSettingNewBorn(objPrintData.iCountNoOfLines, objPrintData.fileContents.toString(),strHospitalCode);
			System.out.println("DATA TO BE PRINTED\n\n" + strResult);
			printData(strResult, "ADT", request);
		}
		catch (Exception e)
		{

		}
		return objPrintData.fileContents.toString();
	}

	public static String AdmissionModification(PatientAdmissionModificationTransVO vo, HttpServletRequest request)
	{
		String strResult = "",strHospitalCode="";
		PrintData objPrintData = new PrintData();
		try
		{
			objPrintData.fileContents = new StringBuffer();
			objPrintData.iCountNoOfLines = 0;

			objPrintData = getBaseAdmissionSlip(vo, objPrintData);

			strHospitalCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
			strResult = printSettingAdmission(objPrintData.iCountNoOfLines, objPrintData.fileContents.toString(),strHospitalCode);
			System.out.println("DATA TO BE PRINTED\n\n" + strResult);
			printData(strResult, "ADT", request);
		}
		catch (Exception e)
		{

		}
		return objPrintData.fileContents.toString();
	}
	public static String AdmissionSlipReprint(SlipReprintTransVO vo, HttpServletRequest request)
	{
		String strResult = "",strHospitalCode="";
		PrintData objPrintData = new PrintData();
		try
		{
			objPrintData.fileContents = new StringBuffer();
			objPrintData.iCountNoOfLines = 0;

			objPrintData = getBaseAdmissionSlip(vo, objPrintData);

			strHospitalCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
			strResult = printSettingAdmission(objPrintData.iCountNoOfLines, objPrintData.fileContents.toString(),strHospitalCode);
			System.out.println("DATA TO BE PRINTED\n\n" + strResult);
			printData(strResult, "ADT", request);
		}
		catch (Exception e)
		{

		}
		return objPrintData.fileContents.toString();
	}

	public static String AdmissionReprint(AdmissionReprintTransVO vo, HttpServletRequest request)
	{
		String strResult = "",strHospitalCode="";
		PrintData objPrintData = new PrintData();
		try
		{
			objPrintData.fileContents = new StringBuffer();
			objPrintData.iCountNoOfLines = 0;

			objPrintData = getBaseAdmissionSlip(vo, objPrintData);

			strHospitalCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
			strResult = printSettingAdmission(objPrintData.iCountNoOfLines, objPrintData.fileContents.toString(),strHospitalCode);
			System.out.println("DATA TO BE PRINTED\n\n" + strResult);
			printData(strResult, "ADT", request);
		}
		catch (Exception e)
		{
			//e.printStackTrace();
		}

		return strResult.toString();
	}

	

	public static String VisitorPass(VisitorPassTransVO vo,HttpServletRequest request) {
		StringBuffer fileContents = null;
		fileContents = new StringBuffer();
		String puk[] = null;
		String passNo[] = null;
		String passType[] = null;
		String issueDt[] = null;
		String validfrm[] = null;
		String validto[] = null;
		String passAmt[] = null;
		String patName[] = null;
		String admNo[] = null;
		String momName[] = null;
		String momCrNo[] = null;
		String deptname[] = null;
		String wardname[] = null;
		String roomname[] = null;
		String unitname[] = null;
		String admdttime[] = null;
		String isnewborn[] = null;
		String ismlc[] = null;
		String fsname[] = null;
		String type = "";
		String strResult = "",strHospitalCode="";
		String strCharge="";
		IpdConfigUtil ipdConfig=null;
		int i = 0;
		int iCountNoOfLines = 0;
		try {
			ipdConfig=new IpdConfigUtil(vo.getStrHospitalCode());
			fileContents = new StringBuffer();
			strHospitalCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();

			puk = vo.getStrPuk();
			patName = vo.getStrPatName();
			passNo = vo.getStrPassNo();
			passType = vo.getStrPassType();
			issueDt = vo.getStrIssueDate();
			validfrm = vo.getStrValidFrom();
			validto = vo.getStrValidTo();
			passAmt = vo.getStrPassAmount();
			admNo = vo.getStrAdmissionNo();
			momName = vo.getStrMomName();
			momCrNo = vo.getStrMomCrNo();
			deptname = vo.getStrDeptName();
			wardname = vo.getStrWardName();
			roomname = vo.getStrRoomName();
			unitname = vo.getStrUnitName();
			admdttime = vo.getStrAdmDtTime();
			isnewborn = vo.getStrIsNewBorn();
			ismlc = vo.getStrIsMLC();
			fsname = vo.getStrFSName();
		
			
			for (i = 0; i < passNo.length; i++) {
				fileContents = new StringBuffer();
				iCountNoOfLines = 0;
				if (passType[i].equals("1")){
					type = "Free";
					strCharge="0.00";
				}
				else if (passType[i].equals("3")){
					type = "Attendant";
					strCharge="0.00";
				}
				else{
					type = "Paid";
					strCharge=ipdConfig.getStrPaidPassCharge();
				}
				fileContents.append("Pass No/Pass type     : " + passNo[i]
						+ "/" + type + "         Issue Date   : " + issueDt[i]
						+ "\n");
				iCountNoOfLines++;
				
				fileContents.append("Valid From            : " + validfrm[i] + "\t       Valid Up To  : " 
						+ validto[i] + "\nPass Amount    :"
						+ passAmt[i] + "   \n");
				iCountNoOfLines++;
				fileContents.append("CR No.   : " + puk[i] + "  Adm No : "
						+ admNo[i] + "\n");
				iCountNoOfLines++;
				if (isnewborn[i].equals("1")) {
					fileContents.append("Name     : " + patName[i]
							+ "            MLC No.   : " + admNo[i] + "\n");
					iCountNoOfLines++;
				} else {
					fileContents.append("Name     : " + patName[i] + "\n");
					iCountNoOfLines++;
				}
				fileContents.append("Father/Spouse Name     : " + fsname[i]
						+ " \n");
				iCountNoOfLines++;
				if (ismlc[i].equals("1")) {
					fileContents.append("Mother Name     : " + momName[i]
							+ "           Mother CR No.   : " + momCrNo[i]
							+ "\n");
					iCountNoOfLines++;
				}
				fileContents.append("Dept/Unit: " + deptname[i] + "/"
						+ unitname[i] + "           Ward/Room  : "
						+ wardname[i] + "/" + roomname[i] + "\n");
				iCountNoOfLines++;
				fileContents.append("Adm Date/Time : " + admdttime[i]
						+ "            Fee : Rs."+strCharge+"\n");// field[0] ,dataval[]
														// array set in save
														// time
				iCountNoOfLines++;
				
				strResult = printSettingVisitor(iCountNoOfLines, fileContents
						.toString(),strHospitalCode);
				System.out.println("DATA TO BE PRINTED\n\n"+strResult.toString());
			//	printData(strResult,"ADT",request);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgType("1");
			vo.setStrMsgString("ADTPrintingTransHLP.VisitorPass()---->"+e.getMessage());
		}
		return strResult.toString();
	}

	

	// New Born Baby Admission
	private static PrintData getBaseAdmissionSlip(NewBornBabyTransVO vo, PrintData _objPrintData)
	{
		PatientAdmissionTransVO patAdmVO = new PatientAdmissionTransVO();
		HelperMethods.populate(patAdmVO, vo);
		patAdmVO.setStrIsNewBorn("1");
		_objPrintData.slipMode="N";		
		return getBaseAdmissionSlip(patAdmVO, _objPrintData);
	}
	
	// Patient Admission Modification
	private static PrintData getBaseAdmissionSlip(PatientAdmissionModificationTransVO vo, PrintData _objPrintData)
	{
		PatientAdmissionTransVO patAdmVO = new PatientAdmissionTransVO();
		HelperMethods.populate(patAdmVO, vo);
		_objPrintData.slipMode="M";		
		return getBaseAdmissionSlip(patAdmVO, _objPrintData);
	}
	private static PrintData getBaseAdmissionSlip(SlipReprintTransVO vo, PrintData _objPrintData)
	{
		PatientAdmissionTransVO patAdmVO = new PatientAdmissionTransVO();
		HelperMethods.populate(patAdmVO, vo);
		_objPrintData.slipMode="M";		
		return getBaseAdmissionSlip(patAdmVO, _objPrintData);
	}

	// Patient Admission Reprint
	private static PrintData getBaseAdmissionSlip(AdmissionReprintTransVO vo, PrintData _objPrintData)
	{
		PatientAdmissionTransVO patAdmVO = new PatientAdmissionTransVO();
		HelperMethods.populate(patAdmVO, vo);
		_objPrintData.slipMode="R";
		patAdmVO.setStrAdmNo(vo.getStrAdmnNo());
		//System.out.println("getCurDtUtWrRmBd :"+vo.getCurDtUtWrRmBd());
		String[] tmp = vo.getCurDtUtWrRmBd().replace('^', '#').split("#");
		patAdmVO.setStrDeptUnitName(tmp[0]+"/"+tmp[1]);
		patAdmVO.setStrWardName(tmp[2]);
		patAdmVO.setStrRoom(tmp[3]);
		IpdConfigUtil ipdConfig=new IpdConfigUtil(vo.getStrHospitalCode());
		patAdmVO.setStrAdmissionChargeValue(ipdConfig.getStrAdmissionReprintCharge());
		patAdmVO.setStrCurrentDate(vo.getStrAdmDateAndTime());
		return getBaseAdmissionSlip(patAdmVO, _objPrintData);
	}

	// Patient Admission
	private static PrintData getBaseAdmissionSlip(PatientAdmissionTransVO vo, PrintData _objPrintData)
	{
		String arrPatDetail[] = null;
		String tmp2[] = null;
		HisUtil util = null;
		ResourceBundle utilRes = null;
		String strPrintAdmNo = null;
		//String strAdmCharges = null;
		try
		{
			utilRes = ResourceBundle.getBundle("ipd.adt_mandatory_info");
			strPrintAdmNo = utilRes.getString("ADMISSION_SLIP_PRINT_ADM_NO");
			util = new HisUtil("IPD", "PrintHLP");

		//	strAdmCharges = vo.getStrAdmissionChargeValue();
			
			
			//Target 60 Characters in each Row 
			
			// Patient Detail 
			// In Format :         Patient Name^Father Name/Spouse Name^Category^Age Sex^Address
			arrPatDetail = vo.getStrHiddenPatDtl().replace('^', '#').split("#");
			if(vo.getStrAddress()==null || vo.getStrAddress().trim().equals(""))
				vo.setStrAddress(arrPatDetail[4]);
			
			
			if(vo.getStrIsNewBorn().trim().equals("1") && (vo.getStrMotherName()==null || vo.getStrMotherName().trim().equals("") 
					|| vo.getStrMotherCrNo()==null || vo.getStrMotherCrNo().trim().equals("")))
			{
				tmp2 = vo.getStrMotherDtl().replace('^', '#').split("#");
				vo.setStrMotherName(tmp2[0]);
				vo.setStrMotherCrNo(tmp2[1]);
			}

			// Cr No & Admission No.
			_objPrintData.fileContents.append(util.appendSpace(BOLDON+"CR No.        : ",16,0));
			_objPrintData.fileContents.append(util.appendSpace(vo.getStrCrNo(),20,0));
			if (strPrintAdmNo.equals("1"))
			{
				_objPrintData.fileContents.append(util.appendSpace("Admission No. : "+vo.getStrAdmNo()+""+BOLDOFF,26,1));
			}
			_objPrintData.fileContents.append("\n");
			_objPrintData.iCountNoOfLines++;

			// Extra Line
			//_objPrintData.fileContents.append("\n");
			//_objPrintData.iCountNoOfLines++;
			
			// Name Age Sex
			_objPrintData.fileContents.append(util.appendSpace(BOLDON+"Name          : ",16,0));
			if (_objPrintData.slipMode.equals("N"))					
			{
				_objPrintData.fileContents.append(util.appendSpace(
						util.breakString(vo.getStrBabyName(), 18, "~").get(0), 20,0)+""+BOLDOFF);
				_objPrintData.fileContents.append(util.appendSpace("D.O.B./Sex    : "+vo.getStrDobTime() +"/" + vo.getStrGenderName(),26,1));
			}
			else
			{
				_objPrintData.fileContents.append(util.appendSpace(
						util.breakString(arrPatDetail[0], 18, "~").get(0), 20,0)+""+BOLDOFF);
				_objPrintData.fileContents.append(util.appendSpace("Age/Sex      : "+arrPatDetail[3],26,1));
			}
			_objPrintData.fileContents.append("\n");
			_objPrintData.iCountNoOfLines++;
			
			// MLC No.
			if (vo.getStrIsMLC().trim().equals("1"))
			{
				_objPrintData.fileContents.append(util.appendSpace("MLC NO       : ",16,0));
				_objPrintData.fileContents.append(util.appendSpace(vo.getStrMLCNo(),20,0));
				_objPrintData.fileContents.append("\n");
				_objPrintData.iCountNoOfLines++;
			}

			try
			{
				arrPatDetail[1] = arrPatDetail[1].replace("null", "");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			// Father or Spouse Name
			try
			{
				if (_objPrintData.slipMode.equals("N"))
				{
					_objPrintData.fileContents.append(util.appendSpace("Father       : ",16,0));
					_objPrintData.fileContents.append(util.appendSpace(util.breakString(vo.getStrFatherName(), 43, "~").get(0), 44,0));
				}
				else
				{
					_objPrintData.fileContents.append(util.appendSpace("Father/Spouse : ",16,0));
					_objPrintData.fileContents.append(util.appendSpace(util.breakString(arrPatDetail[1], 43, "~").get(0), 44,0));
				}
				_objPrintData.fileContents.append("\n");
				_objPrintData.iCountNoOfLines++;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			// Mother Name & CR No
			if (vo.getStrIsNewBorn().trim().equals("1"))
			{
				_objPrintData.fileContents.append(util.appendSpace("Mother Name   : ",16,0));
				_objPrintData.fileContents.append(util.appendSpace(
						util.breakString(vo.getStrMotherName(), 18, "~").get(0), 20,0));
				_objPrintData.fileContents.append(util.appendSpace("Mother CR No. : "+vo.getStrMotherCrNo(),26,1));
				_objPrintData.fileContents.append("\n");
				_objPrintData.iCountNoOfLines++;
			}
			
			//_objPrintData.fileContents.append("Category : " + tmp1[2] + "\n");
			//_objPrintData.iCountNoOfLines++;
			
			// Address
			_objPrintData.fileContents.append(util.appendSpace("Address       : ",16,0));
			_objPrintData.fileContents.append(util.appendSpace(vo.getStrAddress(),44,0));
			_objPrintData.fileContents.append("\n");
			_objPrintData.iCountNoOfLines++;
			if (!vo.getStrIsNewBorn().trim().equals("1"))
			{
				// Phone No
				_objPrintData.fileContents.append(util.appendSpace("Phone No.     : ",16,0));
				_objPrintData.fileContents.append(util.appendSpace(vo.getStrPhoneNo(),44,0));
				_objPrintData.fileContents.append("\n");
				_objPrintData.iCountNoOfLines++;
			}
			
			IpdConfigUtil ipdC=new IpdConfigUtil(vo.getStrHospCode());
			
			// Department/Unit
			if(ipdC.getStrUnitNameReq().equals("1"))
			_objPrintData.fileContents.append(util.appendSpace("Dept./Unit    : ",16,0));
			else
				_objPrintData.fileContents.append(util.appendSpace("Deptartment    : ",16,0));
			if (_objPrintData.slipMode.equals("N"))
				_objPrintData.fileContents.append(util.appendSpace(vo.getStrBabyDeptUntName(),44,0));
			else
				_objPrintData.fileContents.append(util.appendSpace(vo.getStrDeptUnitName(),44,0));
			_objPrintData.fileContents.append("\n");
			_objPrintData.iCountNoOfLines++;
				
			
			// Ward/Room/Bed
			if(vo.getStrIsNewBorn().trim().equals("1") || _objPrintData.slipMode.equals("R"))
			{
				if(ipdC.getStrRoomNoReq().equals("1"))
				{
					_objPrintData.fileContents.append(util.appendSpace("Ward/Room/Bed : ",16,0));
					_objPrintData.fileContents.append(util.appendSpace(vo.getStrWardName()+"/"+vo.getStrRoom()+ "/" + vo.getStrBed(),44,0));
				}
				else
				{
					_objPrintData.fileContents.append(util.appendSpace("Ward/Room/Bed : ",16,0));
					_objPrintData.fileContents.append(util.appendSpace(vo.getStrWardName()+"/"+vo.getStrRoom()+ "/" + vo.getStrBed(),44,0));
				}
			}
			else
			{
				if(ipdC.getStrRoomNoReq().equals("1"))
				{
					_objPrintData.fileContents.append(util.appendSpace("Ward/Room : ",16,0));
					_objPrintData.fileContents.append(util.appendSpace(vo.getStrWardName()+"/"+vo.getStrRoom(),44,0));
				}
				else
				{
					_objPrintData.fileContents.append(util.appendSpace("Ward : ",16,0));
					_objPrintData.fileContents.append(util.appendSpace(vo.getStrWardName(),44,0));
				}
			}
			_objPrintData.fileContents.append("\n");
			_objPrintData.iCountNoOfLines++;
			
			// Consultant Name
			_objPrintData.fileContents.append(util.appendSpace("Consultant    : ",16,0));
			_objPrintData.fileContents.append(util.appendSpace(vo.getStrConsultantName(),44,0));
			_objPrintData.fileContents.append("\n");
			_objPrintData.iCountNoOfLines++;
			
			// Admission Date & Time
			_objPrintData.fileContents.append(util.appendSpace("Adm Date/Time :",16,0));
			if(vo.getStrAdmDateTime()!=null && !vo.getStrAdmDateTime().equals(""))
			_objPrintData.fileContents.append(util.appendSpace(vo.getStrAdmDateTime(),44,0));
			else
			_objPrintData.fileContents.append(util.appendSpace(vo.getStrCurrentDate(),44,0));
			_objPrintData.fileContents.append("\n");
			_objPrintData.iCountNoOfLines++;
			
			
			// Fee
		/*	if(vo.getStrIsAdvanceAmountAtAdmission()!=null && vo.getStrIsAdvanceAmountAtAdmission().equals("1"))
			{*/
			if(vo.getStrAdvanceAmount()==null || vo.getStrAdvanceAmount().equals(""))
				vo.setStrAdvanceAmount("0");
				

			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			_objPrintData.fileContents = null;
		}
		return _objPrintData;
	}
	
	public static String VisitorPass(AdmissionReprintTransVO vo,HttpServletRequest request) {
		StringBuffer fileContents = null;
		String tmp[] = null;
		String tmp1[] = null;
		String tmp2[] = null;
		String passNo[] = null;
		String passTyp[] = null;
		String issueDt[] = null;
		String frm[] = null;
		String to[] = null;
		String amt[] = null;
		int i = 0;
		int iCountNoOfLines=0;
		String strResult="",strHospitalCode="";
		String type = "";
		String strChargeValue="";
		IpdConfigUtil ipdConfig=null;
		try {
			fileContents = new StringBuffer();
			ipdConfig=new IpdConfigUtil(vo.getStrHospitalCode());
			// System.out.println("vo.getCurDtUtWrRmBd()->"+vo.getCurDtUtWrRmBd());
			// System.out.println("vo.getStrHiddenPatDtl()->"+vo.getStrHiddenPatDtl());
			tmp = vo.getCurDtUtWrRmBd().replace('^', '#').split("#");
			tmp1 = vo.getStrHiddenPatDtl().replace('^', '#').split("#");
			tmp2 = vo.getStrMotherDtl().replace('^', '#').split("#");
			passNo = vo.getStrPassNo().replace('^', '#').split("#");
			passTyp = vo.getStrPassType().replace('^', '#').split("#");
			issueDt = vo.getStrIssueDate().replace('^', '#').split("#");
			frm = vo.getStrValidFrom().replace('^', '#').split("#");
			to = vo.getStrValidUpto().replace('^', '#').split("#");
			amt = vo.getStrPassAmount().replace('^', '#').split("#");
			strHospitalCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
			
			for (i = 0; i < passNo.length; i++) {
				fileContents = new StringBuffer();
				iCountNoOfLines = 0;
				if (passTyp[i].equals("1")){
					
					type = "Free";
					strChargeValue=ipdConfig.getStrPassReprintCharge();
				}
				else
				{
					type = "Paid";
					strChargeValue=ipdConfig.getStrPassReprintCharge();
				}
				fileContents.append("Pass No/Pass type     : " + passNo[i]
						+ "/" + type + "         Issue Date   : " + issueDt[i]
						+ "\n");
				iCountNoOfLines++;
				fileContents.append("Valid From/To     : " + frm[i] + "/"
						+ to[i] + "               Pass Amount    :" + amt[i]
						+ "   \n");
				iCountNoOfLines++;
				fileContents.append("CR No.   : " + vo.getStrCrNo()
						+ "  Adm No : " + vo.getStrAdmnNo() + "\n");
				iCountNoOfLines++;
				if (vo.getStrIsMLC().trim().equals("1")) {
					fileContents.append("Name     : " + tmp1[0]
							+ "            MLC No.   : " + vo.getStrMLCNo()
							+ "\n");
					iCountNoOfLines++;
				} else {
					fileContents.append("Name     : " + tmp1[0] + "\n");
					iCountNoOfLines++;
				}
				// fileContents.append("Father/Spouse Name : "+tmp1[1]+" \n");
				if (vo.getStrIsNewBorn().trim().equals("1")) {
					fileContents.append("Mother Name     : " + tmp2[0]
							+ "           Mother CR No.   : " + tmp2[1] + "\n");
					iCountNoOfLines++;
				}
				// fileContents.append("Address :"+tmp1[4]+" \n");
				// fileContents.append("Age/Sex :"+tmp1[3]+" Category :
				// "+tmp1[2]+"\n");
				fileContents.append("Dept/Unit: " + tmp[0] + "/" + tmp[1]
						+ "           Ward/Room  : " + tmp[2] + "/" + tmp[3]
						+ "\n");
				iCountNoOfLines++;
				fileContents.append("Adm Date/Time : "
						+ vo.getStrAdmDateAndTime()
						+ "            Fees : Rs."+strChargeValue+"\n");// field[0] ,dataval[]
														// array set in save
														// time
				iCountNoOfLines++;
				strResult = printSettingVisitor(iCountNoOfLines, fileContents
						.toString(),strHospitalCode);
				System.out.println("DATA TO BE PRINTED\n\n"+strResult);
				//printData(strResult,"ADT",request);
			}
		} catch (Exception e) {

		}

		return strResult.toString();
	}

	public static String printSettingVisitor(int noOLines, String contents,String strHospitalCode_p) {
		int x = 0;
		try {
			IpdConfigUtil ipdConfig = new IpdConfigUtil(strHospitalCode_p);
			int k = 1, y = 0, temp = 0;
			x = Integer.parseInt(ipdConfig.getStrNoOfLineInVisitorPassSlip());
			temp = x;
			while (true) {
				if (temp >= noOLines) {
					y = temp - noOLines;
					break;

				} else {
					++k;
					temp = x * k;
				}
			}

			for (int j = 0; j < y; j++) {
				contents += "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}
	
	public static String printSettingAdmission(int noOLines, String contents, String strHospitalCode_p) {
		int x = 0;
		try {
			IpdConfigUtil ipdConfig = new IpdConfigUtil(strHospitalCode_p);
			int k = 1, y = 0, temp = 0;
			x = Integer.parseInt(ipdConfig.getStrNoOfLineInAdmissionSlip());
			temp = x;
			while (true) {
				if (temp >= noOLines) {
					y = temp - noOLines;
					break;

				} else {
					++k;
					temp = x * k;
				}
			}

			for (int j = 0; j < y; j++) {
				contents += "\n";
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return contents;
	}
	public static String printSettingNewBorn(int noOLines, String contents, String strHospitalCode_p) {
		int x = 0;
		try {
			IpdConfigUtil ipdConfig = new IpdConfigUtil(strHospitalCode_p);
			int k = 1, y = 0, temp = 0;
			x = Integer.parseInt(ipdConfig.getStrNoOfLineInNewBornBabyAdmissionSlip());
			temp = x;
			while (true) {
				if (temp >= noOLines) {
					y = temp - noOLines;
					break;

				} else {
					++k;
					temp = x * k;
				}
			}

			for (int j = 0; j < y; j++) {
				contents += "\n";
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return contents;
	}
	/**
	 * This function is used to transfer control to Printer
	 * @param data
	 * @param moduleName
	 * @param request
	 */
	public static void printData(String data,String moduleName,HttpServletRequest request)
	{
		HisPrinter  _hisPrinter=null;
		try
		{
			_hisPrinter=new HisPrinter();
			//System.out.println("print the data here");
			_hisPrinter.printFile(data, moduleName, request);
			/*try{
			Runtime rt = Runtime.getRuntime();
			Process prs = rt.exec("chmod 777 /root/ADTPrintSlip.sh");
			prs.waitFor();
			}catch(Exception e){
				e.printStackTrace();
			}
			HisPrinterSupport.createTempFile(data, "ADTPrintSlip");
			HisPrinterSupport.printSlipADT(request.getRemoteAddr(), "ADTPrintSlip.dat");*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	static class PrintData
	{
		StringBuffer fileContents;
		int iCountNoOfLines;
		String slipMode = "A";
	}
}
