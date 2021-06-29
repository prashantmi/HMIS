package registration.config.slip;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisPrinterSupport;
import hisglobal.utility.HisUtil;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.views.jsp.TextTag;

import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml3;

public class NewRegistrationSlip extends RegistrationsSlip {
	private PatientVO patientVO;
	private EpisodeVO[] episodeVO;
	
	public NewRegistrationSlip(PatientVO patientVO,EpisodeVO[] episodeVO,String tmpFileName){
		this.patientVO=patientVO;
		this.episodeVO=episodeVO;
		this.tmpFileName=tmpFileName;
		
	}
	
	
	/*public static String print(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request,String _mode) throws ParseException{
		
  	  	StringBuilder str=new StringBuilder();
  		int counter = 0;  		
  		String align="";
  		
  		SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
  		String _printDatTime=_sdf2.format(_sdf1.parse((String)_request.getSession().getAttribute(Config.SYSDATE)));
  		
  		CharacterEncoding.setCharacterEncoding(_request);
  		for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
  		{
  			
  			//str.append("<div align='right'><table width='100%' class='donotprint'><tr><td width='100%' align='right'><div align='right'><img src='../../hisglobal/images/printer_tab.jpg' onclick='window.print();'/><img src='../../hisglobal/images/back_button.gif' onclick='window.close();'/></div></td></tr></table></div>");
  			
  			str.append("<div class='div-table-simple' align='center' style='width: 90%;'>");
  			
  			str.append("<div class='div-table-row' style='height: 100px;'>");
  			//str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/NIMSLOGO.jpg'/></div>");
  			str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/nims-tr-logo.gif'/></div>");
  			str.append("<div class='div-table-col' id='divOPDCardHeader' align='center' style='width: 60%'>");
					
  		  //str.append("<h3>"+_rSlip.getHospitalName()+"</h3>");
  		  str.append("<br><font style='font-size: 16px; font-weight:bold;'>"+_rSlip.getHospitalName().toUpperCase()+"</font><br>");
  		  //str.append("<h4>"+_rSlip.getHospitalAddress1());
  		if(_rSlip.getHospitalAddress1()!=null && !_rSlip.getHospitalAddress1().trim().equals(""))
    
  		{
  		  str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+"</font><br>");
  		
  		}
  		  if(_rSlip.getHospitalAddress2()!=null && !_rSlip.getHospitalAddress2().trim().equals(""))
  			 str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress2()+"</font><br>");
  		  if(_rSlip.getHospitalCity()!=null && !_rSlip.getHospitalCity().trim().equals(""))
  			  str.append( _rSlip.getHospitalCity());
  		 if(_rSlip.getHospitalDistrict()!=null && !_rSlip.getHospitalDistrict().trim().equals(""))
 			  str.append(  ", "+_rSlip.getHospitalDistrict());
  		  if(_rSlip.getHospitalpinCode()!=null && !_rSlip.getHospitalpinCode().trim().equals("") && !_rSlip.getHospitalpinCode().trim().equals("0"))
  			  str.append( " -"+_rSlip.getHospitalpinCode());  
  		 if(_rSlip.getHospitalState()!=null && !_rSlip.getHospitalState().trim().equals(""))
			  str.append(  ", "+_rSlip.getHospitalState());
  		 str.append(" (INDIA)");  
  		// str.append("</font><br>");
  		 str.append("<br>");
  		 //str.append("<h5>");
  		 str.append("<font style='font-size: 14px;'>");
  			if(_rSlip.getHospitalPhone()!=null && !_rSlip.getHospitalPhone().trim().equals(""))
  			{
  				str.append(RegistrationConfig.OPD_CARD_HEADER_LINE_4_PHONE_LABEL); 
  				str.append(_rSlip.getHospitalPhone());
  			}
  			if(_rSlip.getHospitalFax()!=null && !_rSlip.getHospitalFax().trim().equals("")){
  				str.append(", " + RegistrationConfig.OPD_CARD_HEADER_LINE_4_FAX_LABEL);
  				str.append(_rSlip.getHospitalFax());
  			}
  			if(_rSlip.getHospitalEmail()!=null && !_rSlip.getHospitalEmail().trim().equals("")){
  				str.append(", " +RegistrationConfig.OPD_CARD_HEADER_LINE_4_EMAIL_LABEL);
  	  			str.append(_rSlip.getHospitalEmail());
  			}
  			
  			str.append("</font><br></div>")
  			str.append("</div>");
  			str.append("<br>");
  			str.append("<div class='div-table-row' style='height: 10px;'>");
  			str.append("<div class='div-table-col fontBold alignCenter' style='width: 100%; height: 20px'>");
  				if(_mode.equalsIgnoreCase("NR"))
  					str.append("OPD Registration Card");
  				else if(_mode.equalsIgnoreCase("ER"))
  					str.append("EMERGENCY Registration Card");
  				else if(_mode.equalsIgnoreCase("DC"))
  					str.append("Duplicate Registration Card");
  				else if(_mode.equalsIgnoreCase("RP"))
  					str.append("RePrint Registration Card"); 
  			str.append("</div>");
  			str.append("</div>");
		
  			str.append("<div class='div-table-row' style='height: 10px;'>");
  			str.append("<div class='div-table-col' style='width: 50%; height: 20px'></div>");
  			str.append("<div class='div-table-col fontBold alignRight' style='width: 50%; height: 20px;'>");
  			
				if(_mode.equalsIgnoreCase("NR")||_mode.equalsIgnoreCase("ER"))
					str.append("Registration FEE (Rs) :"+_rSlip.getPatAmountCollected());
				else if(_mode.equalsIgnoreCase("DC"))
					str.append("Duplicate Card Printing FEE (Rs) :"+_rSlip.getPatAmountCollected());
				else if(_mode.equalsIgnoreCase("DV"))
					str.append("Department Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
				else if(_mode.equalsIgnoreCase("RP"))	
					str.append("Registration/Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
			str.append("</div></div>");
	 
			str.append("<div id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");
			str.append("<div style='border: 1px solid black;'>");
			str.append("<div class='div-table-simple border'>");
			
			if(!_mode.equalsIgnoreCase("DC")){
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col fontBold' id='divCRNoLabel' style='width: 20%; height: 37px;'>CR No<br/>");
				//str.append(RegistrationConfig.OPD_CARD_NO_MARATHI_LABEL);
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 35%; height: 37px;'>");
				str.append(_rSlip.getPatCrNo());
				str.append("</div>");
						
				str.append("<div class='div-table-col fontBold' id='divEpisodeDateLabel' style='width: 25%; height: 37px;'>Visit Date & Time <br/>Valid Upto<br/>");
				//str.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 20%; height: 37px;'>");
				str.append(_rSlip.getEpisodeDate()[index]+" <br/>"+_rSlip.getExpDate()[index]);
				str.append("</div></div>");
			}
			else
			{
				str.append("<div class='div-table-row' style='height: 70px;'>");
				str.append("<div class='div-table-col fontBold' id='divCRNoLabel' style='width: 20%; height: 70px;'>CR No<br/>");
				//str.append(RegistrationConfig.OPD_CARD_NAME_MARATHI_LABEL);
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 35%; height: 70px;'>");
				str.append(_rSlip.getPatCrNo());
				str.append("</div>");
				
				str.append("<div class='div-table-col noPadding' style='width: 25%; height: 70px;'>");
				str.append("<div class='div-table-simple'>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>Last Vist <br/> Date & Time");
				str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 100%; height: 35px; '>Last Visit <br/> Date & Time");
				//str.append(RegistrationConfig.OPD_CARD_AGE_MARATHI_LABEL);
				str.append("</div></div>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col fontBold' id='divGenderLabel' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>Duplicate Card printing <br/>Date & Time & Valid Up to <br/> ");
				str.append("<div class='div-table-col fontBold' id='divGenderLabel' style='width: 100%; height: 35px; '>Duplicate Card printing <br/>Date & Time & Valid Up to <br/> ");
				//str.append(RegistrationConfig.OPD_CARD_SEX_HINDI_LABEL);
				str.append("</div></div></div></div>");
				str.append("<div class='div-table-col noPadding' style='width: 20%; height: 70px;'>");
				str.append("<div class='div-table-simple'>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>");
				str.append("<div class='div-table-col' style='width: 100%; height: 35px; '>");
				str.append(_rSlip.getEpisodeDate()[index]);
				str.append("</div></div>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>");
				str.append("<div class='div-table-col' style='width: 100%; height: 35px; '>");
				str.append(_printDatTime+" <br/>"+_rSlip.getExpDate()[index]);
				str.append("</div></div></div></div></div>");
			}
			
			str.append("<div class='div-table-row' style='height: 50px;'>");
			str.append("<div class='div-table-col fontBold' id='divPatientNameLabel' style='width: 20%; height: 50px;'>Patient Name<br/></div>");
			str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
			str.append(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName().toUpperCase()+" "+_rSlip.getPatLastName().toUpperCase()+"<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 25%; height: 50px;'>Age<br/></div>");
			str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
			str.append(_rSlip.getPatAge());
			str.append("</div>");
			str.append("</div>");
			str.append("<div class='div-table-row' style='height: 50px;'>");
			str.append("<div class='div-table-col fontBold' id='divFatherNameLabel' style='width: 20%; height: 50px;'>Father Name /<br/> Spouse Name<br/></div>");
			str.append("<div class='div-table-col' style='width: 35%; height: 50px;word-wrap: break-word;'>");
			//str.append(!_rSlip.getPatGuardianName().equals("")?_rSlip.getPatGuardianName().toUpperCase():_rSlip.getPatHusbandName().toUpperCase()+"<br/>");
			str.append(!_rSlip.getPatGuardianName().equals("")?_rSlip.getPatGuardianName().toUpperCase():"--");
			str.append("/");
			str.append(_rSlip.getPatHusbandName().toUpperCase()+"<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 25%; height: 50px;'>Sex<br/></div>");
			str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
			str.append(_rSlip.getPatGender());
			str.append("</div>");
			str.append("</div>");
				
			str.append("<div class='div-table-row' style='height: 50px;'>");
			str.append("<div class='div-table-col fontBold' id='divPatientNameLabel' style='width: 20%; height: 50px;'>Patient Name<br/>");
			//str.append(RegistrationConfig.OPD_CARD_NAME_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
			str.append(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName().toUpperCase()+" "+_rSlip.getPatLastName().toUpperCase()+"<br/>");
			//str.append(_rSlip.getPatFirstNameInMultiLang()+" "+_rSlip.getPatMiddleNameInMultiLang()+" "+_rSlip.getPatLastNameInMultiLang());
			str.append("</div>");
			str.append("<div class='div-table-col noPadding' style='width: 25%; height: 50px;'>");
			str.append("<div class='div-table-simple'>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 100%; height: 25px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>Age ");
			//str.append(RegistrationConfig.OPD_CARD_AGE_MARATHI_LABEL);
			str.append("</div></div>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col fontBold' id='divGenderLabel' style='width: 100%; height: 25px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>Sex ");
			//str.append(RegistrationConfig.OPD_CARD_SEX_HINDI_LABEL);
			str.append("</div></div></div></div>");
			str.append("<div class='div-table-col noPadding' style='width: 20%; height: 50px;'>");
			str.append("<div class='div-table-simple'>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col' style='width: 100%; height: 25px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>");
			str.append(_rSlip.getPatAge());
			str.append("</div></div>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col' style='width: 100%; height: 25px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>");
			str.append(_rSlip.getPatGender());
			str.append("</div></div></div></div></div>");
				
			str.append("<div class='div-table-row' style='height: 37px;'>");
			str.append("<div class='div-table-col  fontBold' id='divCasteLabel' style='width: 20%; height: 37px;'>Category<br/>");
			//str.append(RegistrationConfig.OPD_CARD_CATEGORY_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 35%; height: 37px;'>");
			str.append((_rSlip.getPatPrimaryCat().equalsIgnoreCase("null")|| _rSlip.getPatPrimaryCat().equalsIgnoreCase("")?"NA":_rSlip.getPatPrimaryCat()));
			str.append("</div>");
					
			str.append("<div class='div-table-col fontBold' id='divEpisodeDateLabel' style='width: 25%; height: 37px;'>Aadhaar No.<br/>");
			//str.append(RegistrationConfig.OPD_CARD_AADHAAR_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 20%; height: 37px;'>");
			str.append((_rSlip.getPatNationalId()==null?"":_rSlip.getPatNationalId()));
			str.append("</div>");
			str.append("</div>");
				
			String _dRheight="";
			if(_rSlip.getWorkingDays()[index].length()>0)
				_dRheight="60px";
			else
				_dRheight="40px";
			
			str.append("<div class='div-table-row' style='height: "+_dRheight+";'>");
			str.append("<div class='div-table-col fontBold' id='divAreaLabel' style='width: 20%; height: "+_dRheight+";'>Address with <br/>Contact Details<br/>");
			//str.append(RegistrationConfig.OPD_CARD_RESIDENCE_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 35%; height: "+_dRheight+";'>");
			str.append(_rSlip.getAddress1()+ _rSlip.getAddress2());
			str.append("</div>");
					
			str.append("<div class='div-table-col fontBold' id='divTokenLabel' style='width: 25%; height: "+_dRheight+";'>Dept/Room No/<br/>Queue No/Days<br/>");
			//str.append(RegistrationConfig.OPD_CARD_ROOM_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 20%; height: "+_dRheight+"; word-wrap: break-word;'>");
			
				String[] tokenDetail=new String[3];
		  		tokenDetail[0]=_rSlip.getDepartmentToBeVisited()[index]+'-'+_rSlip.getDepartmentUnit()[index]+'/';
		  		tokenDetail[1]=_rSlip.getRoom()[index]+'/'+_rSlip.getQueNo()[index];// Commented By Adil Wasi
		  		tokenDetail[2]="/"+_rSlip.getWorkingDays()[index];
		  		int rows=1;
	  			String tokenSplit="";
	  			for(int i=0;i<tokenDetail.length;i++)
	  			{
	  				if(tokenDetail[i].length()>20)
		  			{
		  				counter++;
		  				rows= tokenDetail[i].length()/20+((tokenDetail[i].length()%20==0)?0:1);		
		  				for(int j=0;j<rows;j++){
		  				
			  				if(j==rows-1){
			  					tokenSplit=tokenDetail[i].substring(j*20);
			  				}else{
			  					tokenSplit=tokenDetail[i].substring(j*20,(j+1)*20);
			  				}if(i==1){
			  					//str.append("");
			  				}else
			  					str.append(tokenSplit);
			  			}
			  			
			  		}	
	  				else if(tokenDetail[i].length()<=20)
	  				{
	  					if(i==0){
	  						str.append(tokenDetail[0]); 
	  					}
	  					if(i==1)
	  					{
	  						str.append(tokenDetail[1]);
	  					}
	  					if(i==2)
	  					{
	  						str.append(tokenDetail[2]);
	  					}
	  				
	  				}
	  			}
	  			str.append("</div>");
	  			str.append("</div>");
	  			str.append("<div class='div-table-row' style='height: 37px;'>");
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank1' style='width: 20%; height: 37px;'>Date<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
	  			str.append("</div>");
	  			str.append("<div class='div-table-col fontBold alignCenter' style='width: 20%; height: 37px;'>Symptoms and Progress<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_SYMPTOMSPROGRESS_MARATHI_LABEL);
	  			str.append("</div>");
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 40%; height: 37px;'>Prescriptions<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_PRESCRIPTIONS_MARATHI_LABEL);
	  			str.append("</div>");
					
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 20%; height: 37px;'>For how many days<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_HOWMANYDAYSILL_MARATHI_LABEL);
	  			str.append("</div>");
	  			str.append("</div>");
				
	  			str.append("<div class='div-table-row' style='height: 700px;'>");
	  			str.append("<div class='div-table-col' id='divBlank1' style='width: 20%; height: 700px;'></div>");
	  			str.append("<div class='div-table-col' style='width: 20%; height: 700px;'></div>");
	  			str.append("<div class='div-table-col' id='divBlank2' style='width: 40%; height: 700px;'></div>");
	  			str.append("<div class='div-table-col' id='divBlank2' style='width: 20%; height: 700px;'></div>");
	  			str.append("</div>");
	  			str.append("</div></div></div>");
	
					if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_CENTER))
						align="Center";
					else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_LEFT))
						align="Left";
					else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_RIGHT))
						align="Right";
					else
						align="Center";
		
				if(_rSlip.getIsHeader()[index].equals(RegistrationConfig.DISCLAIMER_IS_HEADER_NO)){
					str.append("<div class='div-table'>");
					//str.append("<div class='div-table-row' style='height: 100px;'>");
					str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer1()[index]+"</div></div>");
					str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer2()[index]+"</div></div>");
					str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer3()[index]+"</div></div>");
					//str.append("</div>");
					str.append("</div>");
				}
  		} 
  	  return str.toString();
	}*/
	
	//Start:Sheeldarshi
public static String print_double(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request,String _mode) throws ParseException{
		
  	  	StringBuilder str=new StringBuilder();
  		int counter = 0;  		
  		String align="";
  		
  		SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
	    SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
	    SimpleDateFormat _sdf4 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");

	    String _printDatTime=_sdf2.format(_sdf1.parse((String)_request.getSession().getAttribute(Config.SYSDATE)));
  		_rSlip.setSheetNo("1");
  		//By Sandip on 23.06.17
   		String asterixId1, asterixId2=""; 
  		asterixId1 =_rSlip.getPatNationalId();
  		if(asterixId1!=null && !asterixId1.equalsIgnoreCase(""))
  		{
  			asterixId1 = asterixId1.substring(8,12);
  			asterixId2 = "XXXX-XXXX-"+asterixId1;
  		}
  		else
  		{
  			asterixId2 ="";
  		}
  		_rSlip.setPatNationalId(asterixId2);
  		//ENd 23.06.17
  		CharacterEncoding.setCharacterEncoding(_request);
  		for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
  		{
  			str.append("<div class='div-table-simple' align='center' style='width: 95%;'>");
  			str.append("<div class='div-table-row' style='height: 100px;'>");
  			
  		  /*****************************************************************************************************************************************************************/
            /****************************************************************On 07.04.2017 By Mukund for putting headers on the Registration cards***************************/
            if(RegistrationConfig.IS_HEADER_PRINTABLE.equals(RegistrationConfig.IS_HEADER_PRINTABLE_ON)){
                    //str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/NIMSLOGO.jpg'/></div>");
                    str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/nims-tr-logo.gif'/></div>");
                    str.append("<div class='div-table-col' id='divOPDCardHeader' align='center' style='width: 60%'>");
                            
                  //str.append("<h3>"+_rSlip.getHospitalName()+"</h3>");
                  str.append("<br><font style='font-size: 16px; font-weight:bold;'>"+_rSlip.getHospitalName().toUpperCase()+"</font><br>");
                  //str.append("<h4>"+_rSlip.getHospitalAddress1());
                  if(_rSlip.getHospitalAddress1()!=null && !_rSlip.getHospitalAddress1().trim().equals(""))
                    str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+"</font><br>");
                  if(_rSlip.getHospitalAddress2()!=null && !_rSlip.getHospitalAddress2().trim().equals(""))
                  {/*str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress2()+"</font><br>");*/}
                  if(_rSlip.getHospitalCity()!=null && !_rSlip.getHospitalCity().trim().equals(""))
                      str.append( _rSlip.getHospitalCity());
                  if(_rSlip.getHospitalDistrict()!=null && !_rSlip.getHospitalDistrict().trim().equals(""))
                      str.append(  ", "+_rSlip.getHospitalDistrict());
                  if(_rSlip.getHospitalpinCode()!=null && !_rSlip.getHospitalpinCode().trim().equals("") && !_rSlip.getHospitalpinCode().trim().equals("0"))
                      str.append( " -"+_rSlip.getHospitalpinCode());  
                  if(_rSlip.getHospitalState()!=null && !_rSlip.getHospitalState().trim().equals(""))
                      str.append(  ", "+_rSlip.getHospitalState());
                    str.append(" (INDIA)");  
                // str.append("</font><br>");
                 str.append("<br>");
                 //str.append("<h5>");
                 str.append("<font style='font-size: 14px;'>");
                    if(_rSlip.getHospitalPhone()!=null && !_rSlip.getHospitalPhone().trim().equals(""))
                    {
                        str.append(RegistrationConfig.OPD_CARD_HEADER_LINE_4_PHONE_LABEL); 
                        str.append(_rSlip.getHospitalPhone());
                    }
                    if(_rSlip.getHospitalFax()!=null && !_rSlip.getHospitalFax().trim().equals("")){
                        str.append(", " + RegistrationConfig.OPD_CARD_HEADER_LINE_4_FAX_LABEL);
                        str.append(_rSlip.getHospitalFax());
                    }
                    /*if(_rSlip.getHospitalEmail()!=null && !_rSlip.getHospitalEmail().trim().equals("")){
                        str.append(", " +RegistrationConfig.OPD_CARD_HEADER_LINE_4_EMAIL_LABEL);
                        str.append(_rSlip.getHospitalEmail());
                    }*/
                    
                    str.append("</font><br></div>");
                    //DIV QRCODE 
                    str.append("<div id='divQRCodeControl' class='div-table-col' style='width: 20%' align='right'>");
                    if(_rSlip.getIsQRCodePrintFlag().equals(RegistrationConfig.ISQRCodePrintFlag_True))
                    	{str.append("<img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no QR found in session\">");}
                    str.append("</div>");
                    
            }           
            /*****************************************************************************************************************************************************************/
            /****************************************************************End 07.04.2017 Mukund****************************************************************************/
            
  			
  			str.append("</div>");
  			str.append("<br>");
  			str.append("<div class='div-table-row' style='height: 10px;'>");
  			str.append("<div class='div-table-col fontBold alignCenter' style='width: 100%; height: 20px'>");
  				if(_mode.equalsIgnoreCase("NR"))
  					{//str.append("OPD Registration Card");
  					}
  					
  				/*else if(_mode.equalsIgnoreCase("ER"))
  					str.append("EMERGENCY Registration Card");*/
  				else if(_mode.equalsIgnoreCase("DC"))
  					str.append("Duplicate Registration Card");
  				else if(_mode.equalsIgnoreCase("RP"))
  					str.append("RePrint Registration Card"); 
  			str.append("</div>");
  			str.append("</div>");
		
  			/*str.append("<div class='div-table-row' style='height: 10px;'>");
  			str.append("<div class='div-table-col' style='width: 50%; height: 20px'></div>");
  			str.append("<div class='div-table-col fontBold alignRight' style='width: 50%; height: 20px;'>");
  			
				if(_mode.equalsIgnoreCase("NR")||_mode.equalsIgnoreCase("ER"))
					{if(_mode.equalsIgnoreCase("DC"))
					str.append("Registration FEE (Rs) :"+_rSlip.getPatAmountCollected());}
				else if(_mode.equalsIgnoreCase("DC"))
					str.append("Duplicate Card Printing FEE (Rs) :"+_rSlip.getPatAmountCollected());
				else if(_mode.equalsIgnoreCase("DV"))
					str.append("Department Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
				else if(_mode.equalsIgnoreCase("RP"))	
					str.append("Registration/Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
			str.append("</div></div>");*/
	 
			if(_mode.equalsIgnoreCase("DC")){
			str.append("<div id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");}
			
			
			if(!_mode.equalsIgnoreCase("DC")){
				
				/*str.append("<div class='div-table-row' style='width: 100%;height: 37px;text-align:center;'>");
				str.append("<b>");
				str.append("<font size='4'>");
				str.append("OUT PATIENT RECORD");
				str.append("</font>");
				str.append("</b>");	
				str.append("</div>");*/
				
				str.append("<div class='div-table-row' style='width: 100%;height: 37px;text-align:center;'>");
				str.append("<font size='4'>OUT PATIENT RECORD");
				if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("1"))
				str.append(" ( MORNING CLINIC )");
				else if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("4"))
				str.append(" ( EVENING CLINIC )");	
				str.append("</font>");
				str.append("</div>");
				
				str.append("<b>");
				str.append("<div>Card Valid Upto :&nbsp;");
				str.append("</b>");		
				if(_mode.equalsIgnoreCase("ER"))
				{
				str.append(_rSlip.getExpDate()[index]);
				}
				else
				{
					if(_rSlip.getExpDate()[index]!=null&&_rSlip.getExpDate()[index]!=""&&_rSlip.getExpDate()[index].indexOf("-")>0){
						if(_rSlip.getExpDate()[index].indexOf(" ")>0)
							str.append(_sdf3.format(_sdf2.parse((String)_rSlip.getExpDate()[index])));
						else		
							str.append(_rSlip.getExpDate()[index]);

					}
						
					if(_rSlip.getExpDate()[index]!=null&&_rSlip.getExpDate()[index]!=""&&_rSlip.getExpDate()[index].indexOf("/")>0)
						str.append(_sdf4.format(_sdf1.parse((String)_rSlip.getExpDate()[index])));
				}
				//str.append("</div>");
				/*str.append("<b>");
				str.append("<font size='4'>");
				str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				//str.append("<div style='text-align: center;'>OUT PATIENT RECORD");
				str.append("OUT PATIENT RECORD");
				str.append("</font>");
				str.append("</b>");	*/
				str.append("</div>");
				str.append("<br/>");
				str.append("</div>");
				str.append("<div style='border: 1px solid black;width:95%;'>");
				str.append("<div class='div-table-simple border'>");
				//str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 40px;'>CR No &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
				str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 55px;'>");
				//str.append(RegistrationConfig.OPD_CARD_NO_MARATHI_LABEL);
	  			str.append("<div class='div-table-simple' align='center' style='width: 100%;'>");
				str.append("<div class='div-table-row' style='width: 100%;height: 20px;'>");
				
				str.append("<div class='div-table-col' style='width: 21%;border: 0px solid #000'>CR No ");
				
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 75%;border: 0px solid #000'>");
				str.append(":&nbsp;");
				str.append("<b>");
				
				str.append(_rSlip.getPatCrNo());
				
				str.append("</b>");
				str.append("</div>");
				
				str.append("</div>");
				
				str.append("<div class='div-table-row' style='width: 100%;height: 30px;'>");
				
				str.append("<div class='div-table-col' style='width: 26%;border: 0px solid #000'>");
				
				str.append("</div>");
				
				//str.append("<div class='div-table-col' style='width: 68%;border: 0px solid #000;margin-left:-45%;'>");
				str.append("<div class='div-table-col' style='width: 68%;border: 0px solid #000;'>");
				//str.append("<div class='div-table-col' id='divBarCodeControl' style='width: 75%;border: 0px solid #000;;p-left:-45%;'>"+_rSlip.getPatCrNo()+"</div>");
				str.append("<div  id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");
				str.append("</div>");
				str.append("</div>");
				
				
				
				
				//str.append("&nbsp;&nbsp;");
				//str.append("<div id='divBarCodeControl' style='margin-right:47%;'>"+_rSlip.getPatCrNo()+"</div>");
				str.append("</div>");
				
				str.append("</div>");

				str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 33%; height: 55px;padding-top: 15px;'>Date & Time :&nbsp;");
				//str.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
				
				
			
				str.append(_rSlip.getEpisodeDate()[index]);
			
				str.append("</div>");
				str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 25%; height: 55px;padding-top: 15px;'>Sheet No. :&nbsp;");
				
				
				//To Do
				
				str.append(_rSlip.getSheetNo());
				str.append("</div>");
			}
			else
			{
				str.append("<div style='border: 1px solid black;'>");
				str.append("<div class='div-table-simple border'>");
				str.append("<div class='div-table-row' style='height: 70px;'>");
				str.append("<div class='div-table-col fontBold' id='divCRNoLabel' style='width: 20%; height: 70px;'>CR No<br/>");
				//str.append(RegistrationConfig.OPD_CARD_NAME_MARATHI_LABEL);
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 35%; height: 70px;'>");
				str.append(_rSlip.getPatCrNo());
				str.append("</div>");
				
				str.append("<div class='div-table-col noPadding' style='width: 25%; height: 70px;'>");
				str.append("<div class='div-table-simple'>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>Last Vist <br/> Date & Time");
				str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 100%; height: 35px; '>Last Visit <br/> Date & Time");
				//str.append(RegistrationConfig.OPD_CARD_AGE_MARATHI_LABEL);
				str.append("</div></div>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col fontBold' id='divGenderLabel' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>Duplicate Card printing <br/>Date & Time & Valid Up to <br/> ");
				str.append("<div class='div-table-col fontBold' id='divGenderLabel' style='width: 100%; height: 35px; '>Duplicate Card printing <br/>Date & Time & Valid Up to <br/> ");
				//str.append(RegistrationConfig.OPD_CARD_SEX_HINDI_LABEL);
				str.append("</div></div></div></div>");
				str.append("<div class='div-table-col noPadding' style='width: 20%; height: 70px;'>");
				str.append("<div class='div-table-simple'>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>");
				str.append("<div class='div-table-col' style='width: 100%; height: 35px; '>");
				str.append(_rSlip.getEpisodeDate()[index]);
				str.append("</div></div>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>");
				str.append("<div class='div-table-col' style='width: 100%; height: 35px; '>");
				if(_mode.equalsIgnoreCase("ER"))
				{
				str.append(_printDatTime+" <br/>"+_rSlip.getExpDate()[index]);
				}
				else
				{
					str.append(_printDatTime+" <br/>"+_sdf3.format(_sdf2.parse((String)_rSlip.getExpDate()[index])));
				}
				
				//str.append(_printDatTime+" <br/>"+_rSlip.getExpDate()[index]);
				
				str.append("</div></div></div></div></div>");
			}
			
			str.append("<div class='div-table-row' style='height: 40px;'>");
			str.append("<div class='div-table-col' id='divPatientNameLabel' style='width: 42%; height: 40px;'>Patient Name &nbsp;&nbsp;:&nbsp;");
			//str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
			
			str.append("<b>");
			str.append(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName().toUpperCase()+" "+_rSlip.getPatLastName().toUpperCase());
			str.append("</b>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divAgeLabel' style='width: 33%; height: 40px;'>Age/Sex&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
			//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
			
			
			str.append(_rSlip.getPatAge());
			str.append("/");
			str.append(_rSlip.getPatGender().substring(0,1));
			
			str.append("</div>");
			str.append("<div class='div-table-col' id='divCasteLabel' style='width: 25%; height: 40px;'>Category&nbsp;&nbsp;:&nbsp;");
			
			
			str.append((_rSlip.getPatPrimaryCat().equalsIgnoreCase("null")|| _rSlip.getPatPrimaryCat().equalsIgnoreCase("")?"NA":_rSlip.getPatPrimaryCat()));
		
			str.append("</div>");
			str.append("</div>");
			if(_rSlip.getPatPrimaryCat().equalsIgnoreCase("staff"))
			{
				str.append("<div class='div-table-row' style='height: 40px;'>");
				str.append("<div class='div-table-col' id='divStaffNameLabel' style='width: 42%; height: 40px;'>Staff Name/No. &nbsp;&nbsp;:&nbsp;");
				//str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
				
				str.append("<b>");
				str.append((_rSlip.getDependentName()!=null && !_rSlip.getDependentName().equals("")?_rSlip.getDependentName().toUpperCase():"NA"));
				str.append("/");
				str.append((_rSlip.getHiddenPatIdNo()!=null && !_rSlip.getHiddenPatIdNo().equals("")?_rSlip.getHiddenPatIdNo():"NA"));
				str.append("</b>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divRelationLabel' style='width: 33%; height: 40px;'>Staff Relation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
				//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
				
				
				str.append((_rSlip.getPatMemRelationName()!=null && ! _rSlip.getPatMemRelationName().equals("")?_rSlip.getPatMemRelationName().toUpperCase():"NA"));
				
				
				str.append("</div>");
				str.append("<div class='div-table-col' id='divDepartmentLabel' style='width: 25%; height: 40px;'>Staff Department&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
				//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
				
				
				str.append((_rSlip.getPatMemDeptName()!=null && !  _rSlip.getPatMemDeptName().equals("")?_rSlip.getPatMemDeptName().toUpperCase():"NA"));
				
				
				str.append("</div>");
				
				str.append("</div>");
			}
			str.append("<div class='div-table-row' style='width: 100%;height: 40px;'>");
			str.append("<div class='div-table-col' id='divAreaLabel' style='width: 75%; height: 40px;'>Address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
			//str.append(RegistrationConfig.OPD_CARD_RESIDENCE_MARATHI_LABEL);
			
			
			str.append(_rSlip.getAddress1()+" "+ _rSlip.getAddress2());
		
			str.append("</div>");
			
			str.append("<div class='div-table-col' style='width: 25%; height: 40px;'>Aadhaar&nbsp;&nbsp;&nbsp;:&nbsp;");
			str.append(_rSlip.getPatNationalId());
			str.append("</div>");
			
			str.append("</div>");
			str.append("<div class='div-table-row ' id='divTokenLabel' style='width: 100%; height: 50px;'>");
			str.append("<div class='div-table-col'  style='width: 42%;height: 60px;'>");
			str.append("<div>Department :&nbsp;");
			
			String[] tokenDetail=new String[4];
			if(_rSlip.getDepartmentUnit()[index]!=null && _rSlip.getDepartmentUnit()[index]!="")
			{
	  		tokenDetail[0]=_rSlip.getDepartmentToBeVisited()[index]+'-'+_rSlip.getDepartmentUnit()[index];
			}
			else
			{
				tokenDetail[0]=_rSlip.getDepartmentToBeVisited()[index];
			}
	  		int rows=1;
  			String tokenSplit="";
  			str.append("<b>");
  				if(tokenDetail[0].length()>20)
	  			{
	  				counter++;
	  				rows= tokenDetail[0].length()/20+((tokenDetail[0].length()%20==0)?0:1);		
	  				for(int j=0;j<rows;j++){
	  				
		  				if(j==rows-1){
		  					tokenSplit=tokenDetail[0].substring(j*20);
		  				}else{
		  					tokenSplit=tokenDetail[0].substring(j*20,(j+1)*20);
		  				}
		  				
		  					str.append(tokenSplit);
		  					
		  			}
		  			
		  		}	
  				else if(tokenDetail[0].length()<=20)
  				{
  					
  					str.append(tokenDetail[0]);
  					
  					 				
  				}
  				str.append("</b>");
  				str.append("</div>");
  				//str.append("</br>");
  				str.append("<div>OPD Days :&nbsp;");
  				tokenDetail[2]=_rSlip.getWorkingDays()[index];
  				if(_rSlip.getWorkingDays()[index].contains("#"))
  					tokenDetail[2]=	_rSlip.getWorkingDays()[index].split("#")[0];
  		  		tokenSplit="";
  		  	if(tokenDetail[2].length()>20)
  			{
  				counter++;
  				rows= tokenDetail[2].length()/20+((tokenDetail[2].length()%20==0)?0:1);		
  				for(int j=0;j<rows;j++){
  				
	  				if(j==rows-1){
	  					tokenSplit=tokenDetail[2].substring(j*20);
	  				}else{
	  					tokenSplit=tokenDetail[2].substring(j*20,(j+1)*20);
	  				}
	  				
	  					str.append(tokenSplit);
	  					
	  			}
	  			
	  		}	
				else if(tokenDetail[2].length()<=20)
				{
					str.append(tokenDetail[2]);		
					 				
				}
  		  	str.append("</div>");
  		  	   str.append("</div>");
  		  	 /*****************************************************************************************************************************************************************************/
               /*************************************************************************By Mukund on 21.04.2017*****************************************************************************/
           	
  				str.append("<div class='div-table-col'  style='width: 33%; height: 60px;'>");
  				 str.append("<div>Doctor&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
                 str.append(_rSlip.getUnitConsultant()[index]);
                 str.append("</div>");
                 
                 /*str.append("<div>Room No.	:");
  				str.append("<b>");
  				tokenDetail[1]=_rSlip.getRoom()[index];// Commented By Adil Wasi
  		  		tokenSplit="";
  		  		if(tokenDetail[1].length()>20)
  		  		{
  				counter++;
  				rows= tokenDetail[1].length()/20+((tokenDetail[1].length()%20==0)?0:1);		
  				for(int j=0;j<rows;j++){
  				
	  				if(j==rows-1){
	  					tokenSplit=tokenDetail[1].substring(j*20);
	  				}else{
	  					tokenSplit=tokenDetail[1].substring(j*20,(j+1)*20);
	  				}
	  				
	  					
	  					
	  			}
	  			
	  		}	
				else if(tokenDetail[1].length()<=20)
				{
					str.append(tokenDetail[1]);		
					 				
				}
  		  		
  		  str.append("</b>");
  	 	str.append("</div>");*/
        
        /***********************************************************************************End Mukund 0n 21.04.2017***************************************************************/
        /*****************************************************************************************************************************************************************************/
        
  	 	//start : Surabhi
  	 	//reason: hide the queue no. from the slip
  		  	/*str.append("<div><font color='white'>Queue No. :");
  		  	tokenDetail[3]=_rSlip.getQueNo()[index];
  			if(tokenDetail[3].length()>20)
  			{
  				counter++;
  				rows= tokenDetail[3].length()/20+((tokenDetail[3].length()%20==0)?0:1);		
  				for(int j=0;j<rows;j++){
  				
	  				if(j==rows-1){
	  					tokenSplit=tokenDetail[3].substring(j*20);
	  				}else{
	  					tokenSplit=tokenDetail[3].substring(j*20,(j+1)*20);
	  				}
	  				
	  					
	  					
	  			}
	  			
	  		}	
				else if(tokenDetail[3].length()<=20)
				{
					str.append(tokenDetail[3]);		
					 				
				}
  		  	
  		  	str.append("</font></div>");*/
  	 		//str.append("<div></div>");
  	 		//End on 10.04.2017 by Mukund to remove the queue no field from the OPD cards
		  	//end
  		  str.append("</div>");
  		 /*****************************************************************************************************************************************************************************/
          /***************************************************************By Mukund on 21.04.2017**************************************************************************************/
         /*str.append("<div class='div-table-col'  style='width: 25%; height: 60px;'>Doctor&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
  		  
			//str.append("<b>");
			//str.append(_rSlip.getDoctorName());
  		  str.append(_rSlip.getUnitConsultant()[index]);
		//	str.append("</b>");
  		str.append("</div>");*/
        str.append("<div class='div-table-col'  style='width: 25%; height: 60px;'>");
       str.append("<div>Room No.    :");
           str.append("<b>");
           tokenDetail[1]=_rSlip.getRoom()[index];// Commented By Adil Wasi
           tokenSplit="";
           if(tokenDetail[1].length()>20)
           {
           counter++;
           rows= tokenDetail[1].length()/20+((tokenDetail[1].length()%20==0)?0:1);     
           for(int j=0;j<rows;j++){
           
               if(j==rows-1){
                   tokenSplit=tokenDetail[1].substring(j*20);
               }else{
                   tokenSplit=tokenDetail[1].substring(j*20,(j+1)*20);
               }
               
                   
                   
           }
           
       }   
       else if(tokenDetail[1].length()<=20)
       {
           str.append(tokenDetail[1]);     
                           
       }
           
     str.append("</b>");
			str.append("</div>");
			str.append("</div>");
			 /**************************************************************************End : Mukund on 21.04.2017************************************************************************/
	        /*****************************************************************************************************************************************************************************/
	            str.append("</div>");
			
			str.append("<div class='div-table-simple border'>");
			//str.append("<div class='div-table-row' style='width:100%;height: 80px;'>ALERTS");
			str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>ALERTS");
			str.append("</div>");
			str.append("</br>");
			//str.append("</div>");
		//	str.append("<div class='div-table-row' style='width:100%;height: 80px;'>DIAGNOSIS");
			str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>DIAGNOSIS");
			str.append("</div>");
			str.append("</div>");
	  			str.append("<div class='div-table-row' style='height: 37px;'>");
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank1' style='width: 25%; height: 37px;'>Date<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
	  			str.append("</div>");
	  			/*str.append("<div class='div-table-col fontBold alignCenter' style='width: 20%; height: 37px;'>Symptoms and Progress<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_SYMPTOMSPROGRESS_MARATHI_LABEL);
	  			str.append("</div>");*/
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 50%; height: 37px;'>Clinical Notes/Prescriptions<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_PRESCRIPTIONS_MARATHI_LABEL);
	  			str.append("</div>");
					
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 25%; height: 37px;'>Investigations<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_HOWMANYDAYSILL_MARATHI_LABEL);
	  			str.append("</div>");
	  			str.append("</div>");
				
	  			str.append("<div class='div-table-row' style='height: 425px;'>");
	  			str.append("<div class='div-table-col' id='divBlank1' style='width: 25%; height: 425px;'></div>");
	  			//str.append("<div class='div-table-col' style='width: 20%; height: 700px;'></div>");
	  			str.append("<div class='div-table-col' id='divBlank2' style='width: 50%; height: 425px;'></div>");
	  			str.append("<div class='div-table-col' id='divBlank2' style='width: 25%; height: 425px;'></div>");
	  			str.append("</div>");
	  			str.append("</div></div></div>");
	
					if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_CENTER))
						align="Center";
					else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_LEFT))
						align="Left";
					else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_RIGHT))
						align="Right";
					else
						align="Center";
		
				if(_rSlip.getIsHeader()[index].equals(RegistrationConfig.DISCLAIMER_IS_HEADER_NO)){
					str.append("<div class='div-table'>");
					//str.append("<div class='div-table-row' style='height: 100px;'>");
					/*str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer1()[index]+"</div></div>");
					str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer2()[index]+"</div></div>");
					str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer3()[index]+"</div></div>");*/
					//str.append("</div>");
					str.append("</div>");
				}
  		} 
  	  return str.toString();
	}

public static String print_odisha(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request,String _mode) throws ParseException{
	
	  	StringBuilder str=new StringBuilder();
		int counter = 0;  		
		String align="";
		
		SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
    SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat _sdf4 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");

    String _printDatTime=_sdf2.format(_sdf1.parse((String)_request.getSession().getAttribute(Config.SYSDATE)));
		_rSlip.setSheetNo("1");
		//By Sandip on 23.06.17
		String asterixId1, asterixId2=""; 
		asterixId1 =_rSlip.getPatNationalId();
		if(asterixId1!=null && !asterixId1.equalsIgnoreCase(""))
		{
			asterixId1 = asterixId1.substring(8,12);
			asterixId2 = "XXXX-XXXX-"+asterixId1;
		}
		else
		{
			asterixId2 ="";
		}
		_rSlip.setPatNationalId(asterixId2);
		//ENd 23.06.17
		CharacterEncoding.setCharacterEncoding(_request);
		for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
		{
			str.append("<div class='div-table-simple' align='center' style='width: 95%;'>");
			str.append("<div class='div-table-row' style='height: 100px;'>");
			
		  /*****************************************************************************************************************************************************************/
        /****************************************************************On 07.04.2017 By Mukund for putting headers on the Registration cards***************************/
        if(RegistrationConfig.IS_HEADER_PRINTABLE.equals(RegistrationConfig.IS_HEADER_PRINTABLE_ON)){
                //str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/NIMSLOGO.jpg'/></div>");
                str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/nims-tr-logo.gif'/></div>");
                str.append("<div class='div-table-col' id='divOPDCardHeader' align='center' style='width: 60%'>");
                        
              //str.append("<h3>"+_rSlip.getHospitalName()+"</h3>");
              str.append("<br><font style='font-size: 16px; font-weight:bold;'>"+_rSlip.getHospitalName().toUpperCase()+"</font><br>");
              //str.append("<h4>"+_rSlip.getHospitalAddress1());
              if(_rSlip.getHospitalAddress1()!=null && !_rSlip.getHospitalAddress1().trim().equals(""))
                str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+"</font><br>");
              if(_rSlip.getHospitalAddress2()!=null && !_rSlip.getHospitalAddress2().trim().equals(""))
              {/*str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress2()+"</font><br>");*/}
              if(_rSlip.getHospitalCity()!=null && !_rSlip.getHospitalCity().trim().equals(""))
                  str.append( _rSlip.getHospitalCity());
              if(_rSlip.getHospitalDistrict()!=null && !_rSlip.getHospitalDistrict().trim().equals(""))
                  str.append(  ", "+_rSlip.getHospitalDistrict());
              if(_rSlip.getHospitalpinCode()!=null && !_rSlip.getHospitalpinCode().trim().equals("") && !_rSlip.getHospitalpinCode().trim().equals("0"))
                  str.append( " -"+_rSlip.getHospitalpinCode());  
              if(_rSlip.getHospitalState()!=null && !_rSlip.getHospitalState().trim().equals(""))
                  str.append(  ", "+_rSlip.getHospitalState());
                str.append(" (INDIA)");  
            // str.append("</font><br>");
             str.append("<br>");
             //str.append("<h5>");
             str.append("<font style='font-size: 14px;'>");
                if(_rSlip.getHospitalPhone()!=null && !_rSlip.getHospitalPhone().trim().equals(""))
                {
                    str.append(RegistrationConfig.OPD_CARD_HEADER_LINE_4_PHONE_LABEL); 
                    str.append(_rSlip.getHospitalPhone());
                }
                if(_rSlip.getHospitalFax()!=null && !_rSlip.getHospitalFax().trim().equals("")){
                    str.append(", " + RegistrationConfig.OPD_CARD_HEADER_LINE_4_FAX_LABEL);
                    str.append(_rSlip.getHospitalFax());
                }
                /*if(_rSlip.getHospitalEmail()!=null && !_rSlip.getHospitalEmail().trim().equals("")){
                    str.append(", " +RegistrationConfig.OPD_CARD_HEADER_LINE_4_EMAIL_LABEL);
                    str.append(_rSlip.getHospitalEmail());
                }*/
                
                str.append("</font><br></div>");
                //DIV QRCODE 
                str.append("<div id='divQRCodeControl' class='div-table-col' style='width: 20%' align='right'>");
                if(_rSlip.getIsQRCodePrintFlag()!=null && _rSlip.getIsQRCodePrintFlag().equals(RegistrationConfig.ISQRCodePrintFlag_True))
                {
                	str.append("<img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no QR found in session\">");
            	}
                str.append("</div>");
                
        }           
        /*****************************************************************************************************************************************************************/
        /****************************************************************End 07.04.2017 Mukund****************************************************************************/
        
			
			str.append("</div>");
			str.append("<br>");
			str.append("<div class='div-table-row' style='height: 10px;'>");
			str.append("<div class='div-table-col fontBold alignCenter' style='width: 100%; height: 20px'>");
				if(_mode.equalsIgnoreCase("NR"))
					{//str.append("OPD Registration Card");
					}
					
				/*else if(_mode.equalsIgnoreCase("ER"))
					str.append("EMERGENCY Registration Card");*/
				else if(_mode.equalsIgnoreCase("DC"))
					str.append("Duplicate Registration Card");
				else if(_mode.equalsIgnoreCase("RP"))
					str.append("RePrint Registration Card"); 
			str.append("</div>");
			str.append("</div>");
	
			/*str.append("<div class='div-table-row' style='height: 10px;'>");
			str.append("<div class='div-table-col' style='width: 50%; height: 20px'></div>");
			str.append("<div class='div-table-col fontBold alignRight' style='width: 50%; height: 20px;'>");
			
			if(_mode.equalsIgnoreCase("NR")||_mode.equalsIgnoreCase("ER"))
				{if(_mode.equalsIgnoreCase("DC"))
				str.append("Registration FEE (Rs) :"+_rSlip.getPatAmountCollected());}
			else if(_mode.equalsIgnoreCase("DC"))
				str.append("Duplicate Card Printing FEE (Rs) :"+_rSlip.getPatAmountCollected());
			else if(_mode.equalsIgnoreCase("DV"))
				str.append("Department Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
			else if(_mode.equalsIgnoreCase("RP"))	
				str.append("Registration/Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
		str.append("</div></div>");*/
 
		if(_mode.equalsIgnoreCase("DC")){
		str.append("<div id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");}
		
		
		if(!_mode.equalsIgnoreCase("DC")){
			
			/*str.append("<div class='div-table-row' style='width: 100%;height: 37px;text-align:center;'>");
			str.append("<b>");
			str.append("<font size='4'>");
			str.append("OUT PATIENT RECORD");
			str.append("</font>");
			str.append("</b>");	
			str.append("</div>");*/
			str.append("<div class='div-table-row' style='width: 100%;height: 37px;text-align:center;'>");
			
			/**Added by Vasu on 04.06.18 for Emergency Registration slip header*/
			if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("3"))
			{
				str.append("<font size='4'>CASUALTY OPD CARD");
			}
			/**End Vasu*/
			else{
				str.append("<font size='4'>OUT PATIENT RECORD");
			}
			if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("1"))
			str.append(" ( MORNING CLINIC )");
			else if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("4"))
			str.append(" ( EVENING CLINIC )");	
			str.append("</font>");
			str.append("</div>");
			
		
			str.append("<div class='div-table-row width100' >");
			str.append("<div class='div-table-col width50 control' id='divEpisodeDateLabel' ><p>");
			str.append("Date & Time :&nbsp;");
			str.append("<b>"+_rSlip.getEpisodeDate()[index]+"</b>");
			str.append("</p></div>");
			str.append("<div class='div-table-col width50 label' >");
			str.append("<div id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");
			str.append("</div>");
			
			str.append("</div>");
			
			str.append("</div>");
			str.append("</div>");
			str.append("<div style='border: 1px solid black;width:95%;'>");
			str.append("<div class='div-table-simple border'>");
			str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 40px;'>");
			str.append("<div class='div-table-simple' align='center' style='width: 100%;'>");
			str.append("<div class='div-table-row' style='width: 100%;height: 20px;'>");
			str.append("<div class='div-table-col' style='width: 21%;border: 0px solid #000'>CR No ");
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 75%;border: 0px solid #000'>");
			str.append(":&nbsp;<b>");
			str.append(_rSlip.getPatCrNo());
			str.append("</b></div>");
			
			str.append("</div>");
			
			str.append("</div>");
			
			str.append("</div>");

			str.append("<div class='div-table-col' style='width: 33%;height: 40px;'>Aadhaar &nbsp;&nbsp;&nbsp;:&nbsp;<b>");
			str.append(_rSlip.getPatNationalId());
			str.append("</b></div>");
		
			str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 25%; height: 40px;'>Fees: &nbsp;");
			str.append("<b>"+_rSlip.getPatAmountCollected()+"</b>");
			str.append("</div>");
		}
		else
		{
			str.append("<div style='border: 1px solid black;'>");
			str.append("<div class='div-table-simple border'>");
			str.append("<div class='div-table-row' style='height: 70px;'>");
			str.append("<div class='div-table-col fontBold' id='divCRNoLabel' style='width: 20%; height: 70px;'>CR No<br/>");
			//str.append(RegistrationConfig.OPD_CARD_NAME_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 35%; height: 70px;'>");
			str.append(_rSlip.getPatCrNo());
			str.append("</div>");
			
			str.append("<div class='div-table-col noPadding' style='width: 25%; height: 70px;'>");
			str.append("<div class='div-table-simple'>");
			str.append("<div class='div-table-row'>");
			//str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>Last Vist <br/> Date & Time");
			str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 100%; height: 35px; '>Last Visit <br/> Date & Time");
			//str.append(RegistrationConfig.OPD_CARD_AGE_MARATHI_LABEL);
			str.append("</div></div>");
			str.append("<div class='div-table-row'>");
			//str.append("<div class='div-table-col fontBold' id='divGenderLabel' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>Duplicate Card printing <br/>Date & Time & Valid Up to <br/> ");
			str.append("<div class='div-table-col fontBold' id='divGenderLabel' style='width: 100%; height: 35px; '>Duplicate Card printing <br/>Date & Time & Valid Up to <br/> ");
			//str.append(RegistrationConfig.OPD_CARD_SEX_HINDI_LABEL);
			str.append("</div></div></div></div>");
			str.append("<div class='div-table-col noPadding' style='width: 20%; height: 70px;'>");
			str.append("<div class='div-table-simple'>");
			str.append("<div class='div-table-row'>");
			//str.append("<div class='div-table-col' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>");
			str.append("<div class='div-table-col' style='width: 100%; height: 35px; '>");
			str.append(_rSlip.getEpisodeDate()[index]);
			str.append("</div></div>");
			str.append("<div class='div-table-row'>");
			//str.append("<div class='div-table-col' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>");
			str.append("<div class='div-table-col' style='width: 100%; height: 35px; '>");
			if(_mode.equalsIgnoreCase("ER"))
			{
			str.append(_printDatTime+" <br/>"+_rSlip.getExpDate()[index]);
			}
			else
			{
				str.append(_printDatTime+" <br/>"+_sdf3.format(_sdf2.parse((String)_rSlip.getExpDate()[index])));
			}
			
			//str.append(_printDatTime+" <br/>"+_rSlip.getExpDate()[index]);
			
			str.append("</div></div></div></div></div>");
		}
		
		str.append("<div class='div-table-row' style='height: 40px;'>");
		str.append("<div class='div-table-col' id='divPatientNameLabel' style='width: 42%; height: 40px;'>Patient Name &nbsp;&nbsp;:&nbsp;");
		//str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
		
		str.append("<b>");
		str.append(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName().toUpperCase()+" "+_rSlip.getPatLastName().toUpperCase());
		str.append("</b>");
		str.append("</div>");
		str.append("<div class='div-table-col' id='divAgeLabel' style='width: 33%; height: 40px;'>Age/Sex&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<b>");
		//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
		
		
		str.append(_rSlip.getPatAge());
		str.append("/");
		str.append(_rSlip.getPatGender().substring(0,1));
		
		str.append("</b></div>");
		str.append("<div class='div-table-col' id='divCasteLabel' style='width: 25%; height: 40px;'>Category&nbsp;&nbsp;:&nbsp;<b>");
		
		
		str.append((_rSlip.getPatPrimaryCat().equalsIgnoreCase("null")|| _rSlip.getPatPrimaryCat().equalsIgnoreCase("")?"NA":_rSlip.getPatPrimaryCat()));
	
		str.append("</b></div>");
		str.append("</div>");
		if(_rSlip.getPatPrimaryCat().equalsIgnoreCase("staff"))
		{
			str.append("<div class='div-table-row' style='height: 40px;'>");
			str.append("<div class='div-table-col' id='divStaffNameLabel' style='width: 42%; height: 40px;'>Staff Name/No. &nbsp;&nbsp;:&nbsp;");
			//str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
			
			str.append("<b>");
			str.append((_rSlip.getDependentName()!=null && !_rSlip.getDependentName().equals("")?_rSlip.getDependentName().toUpperCase():"NA"));
			str.append("/");
			str.append((_rSlip.getHiddenPatIdNo()!=null && !_rSlip.getHiddenPatIdNo().equals("")?_rSlip.getHiddenPatIdNo():"NA"));
			str.append("</b>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divRelationLabel' style='width: 33%; height: 40px;'>Staff Relation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
			//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
			
			
			str.append((_rSlip.getPatMemRelationName()!=null && ! _rSlip.getPatMemRelationName().equals("")?_rSlip.getPatMemRelationName().toUpperCase():"NA"));
			
			
			str.append("</div>");
			str.append("<div class='div-table-col' id='divDepartmentLabel' style='width: 25%; height: 40px;'>Staff Department&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
			//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
			
			
			str.append((_rSlip.getPatMemDeptName()!=null && !  _rSlip.getPatMemDeptName().equals("")?_rSlip.getPatMemDeptName().toUpperCase():"NA"));
			
			
			str.append("</div>");
			
			str.append("</div>");
		}
		str.append("<div class='div-table-row' style='width: 100%;height: 40px;'>");
		str.append("<div class='div-table-col' id='divAreaLabel' style='width: 75%; height: 40px;'>Address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
		//str.append(RegistrationConfig.OPD_CARD_RESIDENCE_MARATHI_LABEL);
		
		
		str.append(_rSlip.getAddress1()+" "+ _rSlip.getAddress2());
	
		str.append("</div>");
		
		str.append("<div class='div-table-col' style='width: 25%; height: 40px;'>MLC No&nbsp;&nbsp;&nbsp;:&nbsp;");
		str.append(_rSlip.getMlcDetail());
		str.append("</div>");
		str.append("</div>");
		str.append("<div class='div-table-row ' id='divTokenLabel' style='width: 100%; height: 50px;'>");
		str.append("<div class='div-table-col'  style='width: 42%;height: 60px;'>");
		str.append("<div>Department :&nbsp;");
		
		String[] tokenDetail=new String[4];
		if(_rSlip.getDepartmentUnit()[index]!=null && _rSlip.getDepartmentUnit()[index]!="")
		{
  		tokenDetail[0]=_rSlip.getDepartmentToBeVisited()[index]+'-'+_rSlip.getDepartmentUnit()[index];
		}
		else
		{
			tokenDetail[0]=_rSlip.getDepartmentToBeVisited()[index];
		}
  		int rows=1;
			String tokenSplit="";
			str.append("<b>");
				if(tokenDetail[0].length()>20)
  			{
  				counter++;
  				rows= tokenDetail[0].length()/20+((tokenDetail[0].length()%20==0)?0:1);		
  				for(int j=0;j<rows;j++){
  				
	  				if(j==rows-1){
	  					tokenSplit=tokenDetail[0].substring(j*20);
	  				}else{
	  					tokenSplit=tokenDetail[0].substring(j*20,(j+1)*20);
	  				}
	  				
	  					str.append(tokenSplit);
	  					
	  			}
	  			
	  		}	
				else if(tokenDetail[0].length()<=20)
				{
					
					str.append(tokenDetail[0]);
					
					 				
				}
				str.append("</b>");
				str.append("</div>");
				//str.append("</br>");
				str.append("<div>OPD Days :&nbsp;<b>");
				tokenDetail[2]=_rSlip.getWorkingDays()[index];
				if(_rSlip.getWorkingDays()[index].contains("#"))
					tokenDetail[2]=	_rSlip.getWorkingDays()[index].split("#")[0];
		  		tokenSplit="";
		  	if(tokenDetail[2].length()>20)
			{
				counter++;
				rows= tokenDetail[2].length()/20+((tokenDetail[2].length()%20==0)?0:1);		
				for(int j=0;j<rows;j++){
				
  				if(j==rows-1){
  					tokenSplit=tokenDetail[2].substring(j*20);
  				}else{
  					tokenSplit=tokenDetail[2].substring(j*20,(j+1)*20);
  				}
  				
  					str.append(tokenSplit);
  					
  			}
  			
  		}	
			else if(tokenDetail[2].length()<=20)
			{
				str.append(tokenDetail[2]);		
				 				
			}
		  	str.append("</b></div>");
		  	str.append("<div><font color=''>Queue No. :<b>");
		  	tokenDetail[3]=_rSlip.getQueNo()[index];
			if(tokenDetail[3].length()>20)
			{
				counter++;
				rows= tokenDetail[3].length()/20+((tokenDetail[3].length()%20==0)?0:1);		
				for(int j=0;j<rows;j++){
				
  				if(j==rows-1){
  					tokenSplit=tokenDetail[3].substring(j*20);
  				}else{
  					tokenSplit=tokenDetail[3].substring(j*20,(j+1)*20);
  				}
  				
  					
  					
  			}
  			
  		}	
			else if(tokenDetail[3].length()<=20)
			{
				str.append(tokenDetail[3]);		
				 				
			}
		  	
		  	str.append("</b></font></div>");
		  	   str.append("</div>");
		  	 /*****************************************************************************************************************************************************************************/
           /*************************************************************************By Mukund on 21.04.2017*****************************************************************************/
       	
				str.append("<div class='div-table-col'  style='width: 33%; height: 60px;'>");
				 str.append("<div>Doctor&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
             str.append(_rSlip.getUnitConsultant()[index]);
             str.append("</div>");
             
             /*str.append("<div>Room No.	:");
				str.append("<b>");
				tokenDetail[1]=_rSlip.getRoom()[index];// Commented By Adil Wasi
		  		tokenSplit="";
		  		if(tokenDetail[1].length()>20)
		  		{
				counter++;
				rows= tokenDetail[1].length()/20+((tokenDetail[1].length()%20==0)?0:1);		
				for(int j=0;j<rows;j++){
				
  				if(j==rows-1){
  					tokenSplit=tokenDetail[1].substring(j*20);
  				}else{
  					tokenSplit=tokenDetail[1].substring(j*20,(j+1)*20);
  				}
  				
  					
  					
  			}
  			
  		}	
			else if(tokenDetail[1].length()<=20)
			{
				str.append(tokenDetail[1]);		
				 				
			}
		  		
		  str.append("</b>");
	 	str.append("</div>");*/
    
    /***********************************************************************************End Mukund 0n 21.04.2017***************************************************************/
    /*****************************************************************************************************************************************************************************/
    
	 	//start : Surabhi
	 	//reason: hide the queue no. from the slip
		  	/*str.append("<div><font color=''>Queue No. :");
		  	tokenDetail[3]=_rSlip.getQueNo()[index];
			if(tokenDetail[3].length()>20)
			{
				counter++;
				rows= tokenDetail[3].length()/20+((tokenDetail[3].length()%20==0)?0:1);		
				for(int j=0;j<rows;j++){
				
  				if(j==rows-1){
  					tokenSplit=tokenDetail[3].substring(j*20);
  				}else{
  					tokenSplit=tokenDetail[3].substring(j*20,(j+1)*20);
  				}
  				
  					
  					
  			}
  			
  		}	
			else if(tokenDetail[3].length()<=20)
			{
				str.append(tokenDetail[3]);		
				 				
			}
		  	
		  	str.append("</font></div>");*/
	 		//str.append("<div></div>");
	 		//End on 10.04.2017 by Mukund to remove the queue no field from the OPD cards
	  	//end
		  str.append("</div>");
		 /*****************************************************************************************************************************************************************************/
      /***************************************************************By Mukund on 21.04.2017**************************************************************************************/
     /*str.append("<div class='div-table-col'  style='width: 25%; height: 60px;'>Doctor&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
		  
		//str.append("<b>");
		//str.append(_rSlip.getDoctorName());
		  str.append(_rSlip.getUnitConsultant()[index]);
	//	str.append("</b>");
		str.append("</div>");*/
    str.append("<div class='div-table-col'  style='width: 25%; height: 60px;'>");
   str.append("<div>Room No.    :");
       str.append("<b>");
       tokenDetail[1]=_rSlip.getRoom()[index];// Commented By Adil Wasi
       tokenSplit="";
       if(tokenDetail[1].length()>20)
       {
       counter++;
       rows= tokenDetail[1].length()/20+((tokenDetail[1].length()%20==0)?0:1);     
       for(int j=0;j<rows;j++){
       
           if(j==rows-1){
               tokenSplit=tokenDetail[1].substring(j*20);
           }else{
               tokenSplit=tokenDetail[1].substring(j*20,(j+1)*20);
           }
           
               
               
       }
       
   }   
   else if(tokenDetail[1].length()<=20)
   {
       str.append(tokenDetail[1]);     
                       
   }
       
 str.append("</b>");
		str.append("</div>");
		str.append("</div>");
		 /**************************************************************************End : Mukund on 21.04.2017************************************************************************/
        /*****************************************************************************************************************************************************************************/
            str.append("</div>");
		
		str.append("<div class='div-table-simple border'>");
		//str.append("<div class='div-table-row' style='width:100%;height: 80px;'>ALERTS");
		str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>ALERTS");
		str.append("</div>");
		str.append("</br>");
		//str.append("</div>");
	//	str.append("<div class='div-table-row' style='width:100%;height: 80px;'>DIAGNOSIS");
		str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>DIAGNOSIS");
		str.append("</div>");
		str.append("</div>");
  			str.append("<div class='div-table-row' style='height: 37px;'>");
  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank1' style='width: 25%; height: 37px;'>Date<br/>");
  			//str.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
  			str.append("</div>");
  			/*str.append("<div class='div-table-col fontBold alignCenter' style='width: 20%; height: 37px;'>Symptoms and Progress<br/>");
  			//str.append(RegistrationConfig.OPD_CARD_SYMPTOMSPROGRESS_MARATHI_LABEL);
  			str.append("</div>");*/
  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 50%; height: 37px;'>Clinical Notes/Prescriptions<br/>");
  			//str.append(RegistrationConfig.OPD_CARD_PRESCRIPTIONS_MARATHI_LABEL);
  			str.append("</div>");
				
  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 25%; height: 37px;'>Investigations<br/>");
  			//str.append(RegistrationConfig.OPD_CARD_HOWMANYDAYSILL_MARATHI_LABEL);
  			str.append("</div>");
  			str.append("</div>");
			
  			str.append("<div class='div-table-row' style='height: 425px;'>");
  			str.append("<div class='div-table-col' id='divBlank1' style='width: 25%; height: 425px;'></div>");
  			//str.append("<div class='div-table-col' style='width: 20%; height: 700px;'></div>");
  			str.append("<div class='div-table-col' id='divBlank2' style='width: 50%; height: 425px;'></div>");
  			str.append("<div class='div-table-col' id='divBlank2' style='width: 25%; height: 425px;'></div>");
  			str.append("</div>");
  			str.append("</div></div></div>");

				if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_CENTER))
					align="Center";
				else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_LEFT))
					align="Left";
				else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_RIGHT))
					align="Right";
				else
					align="Center";
	
			if(_rSlip.getIsHeader()[index].equals(RegistrationConfig.DISCLAIMER_IS_HEADER_NO)){
				str.append("<div class='div-table'>");
				//str.append("<div class='div-table-row' style='height: 100px;'>");
				/*str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer1()[index]+"</div></div>");
				str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer2()[index]+"</div></div>");
				str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer3()[index]+"</div></div>");*/
				//str.append("</div>");
				str.append("</div>");
			}
		} 
		
	  return str.toString();
}
	
	//End
/*public static String printDuplicateCard(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request,String _mode) throws ParseException{
		
  	  	StringBuilder str=new StringBuilder();
  		int counter = 0;  		
  		String align="";
  		
  		SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
  		String _printDatTime=_sdf2.format(_sdf1.parse((String)_request.getSession().getAttribute(Config.SYSDATE)));
  		
  		CharacterEncoding.setCharacterEncoding(_request);
  		for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
  		{
  			
  			//str.append("<div align='right'><table width='100%' class='donotprint'><tr><td width='100%' align='right'><div align='right'><img src='../../hisglobal/images/printer_tab.jpg' onclick='window.print();'/><img src='../../hisglobal/images/back_button.gif' onclick='window.close();'/></div></td></tr></table></div>");
  			
  			str.append("<div class='div-table-simple'  font-size='10px' align='center' style='width: 90%;'>");
  			
  			str.append("<div class='div-table-row' style='height: 100px;'>");
  			
  			str.append("</div>");
  			str.append("<br>");
  			str.append("<div class='div-table-row' style='height: 10px;'>");
  			str.append("<div class='div-table-col fontBold alignCenter' style='width: 100%; height: 20px'>");
  				if(_mode.equalsIgnoreCase("NR"))
  					str.append("OPD Registration Card");
  				else if(_mode.equalsIgnoreCase("ER"))
  					str.append("EMERGENCY Registration Card");
  				else if(_mode.equalsIgnoreCase("DC"))
  					str.append("Duplicate Registration Card");
  				else if(_mode.equalsIgnoreCase("RP"))
  					str.append("RePrint Registration Card"); 
  			str.append("</div>");
  			str.append("</div>");
		
  			str.append("<div class='div-table-row' style='height: 10px;'>");
  			str.append("<div class='div-table-col' style='width: 50%; height: 20px'></div>");
  			str.append("<div class='div-table-col fontBold alignRight' style='width: 50%; height: 20px;'>");
  			
				if(_mode.equalsIgnoreCase("NR")||_mode.equalsIgnoreCase("ER"))
					str.append("Registration FEE (Rs) :"+_rSlip.getPatAmountCollected());
				else if(_mode.equalsIgnoreCase("DC"))
					str.append("Duplicate Card Printing FEE (Rs) :"+_rSlip.getPatAmountCollected());
				else if(_mode.equalsIgnoreCase("DV"))
					str.append("Department Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
				else if(_mode.equalsIgnoreCase("RP"))	
					str.append("Registration/Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
			str.append("</div></div>");
	 
			str.append("<div id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");
			str.append("<div style='border: 1px solid black;'>");
			str.append("<div class='div-table-simple border'>");
			
			if(!_mode.equalsIgnoreCase("DC")){
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col fontBold' id='divCRNoLabel' style='width: 20%; height: 37px;'>CR No<br/>");
				//str.append(RegistrationConfig.OPD_CARD_NO_MARATHI_LABEL);
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 35%; height: 37px;'>");
				str.append(_rSlip.getPatCrNo());
				str.append("</div>");
						
				str.append("<div class='div-table-col fontBold' id='divEpisodeDateLabel' style='width: 25%; height: 37px;'>Visit Date & Time <br/>Valid Upto<br/>");
				//str.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 20%; height: 37px;'>");
				str.append(_rSlip.getEpisodeDate()[index]+" <br/>"+_rSlip.getExpDate()[index]);
				str.append("</div></div>");
			}
			else
			{
				str.append("<div class='div-table-row' style='height: 70px;'>");
				str.append("<div class='div-table-col fontBold' id='divCRNoLabel' style='width: 20%; height: 70px;'>CR No<br/>");
				//str.append(RegistrationConfig.OPD_CARD_NAME_MARATHI_LABEL);
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 35%; height: 70px;'>");
				str.append(_rSlip.getPatCrNo());
				str.append("</div>");
				
				str.append("<div class='div-table-col noPadding' style='width: 25%; height: 70px;'>");
				str.append("<div class='div-table-simple'>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>Last Vist <br/> Date & Time");
				str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 100%; height: 35px; '>Last Visit Date/Time");
				//str.append(RegistrationConfig.OPD_CARD_AGE_MARATHI_LABEL);
				str.append("</div></div>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col fontBold' id='divGenderLabel' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>Duplicate Card printing <br/>Date & Time & Valid Up to <br/> ");
				str.append("<div class='div-table-col fontBold' id='divGenderLabel' style='width: 100%; height: 35px; '>Card Print Date/Time <br/> Valid Upto  ");
				//str.append(RegistrationConfig.OPD_CARD_SEX_HINDI_LABEL);
				str.append("</div></div></div></div>");
				str.append("<div class='div-table-col noPadding' style='width: 20%; height: 70px;'>");
				str.append("<div class='div-table-simple'>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>");
				str.append("<div class='div-table-col' style='width: 100%; height: 35px; '>");
				str.append(_rSlip.getEpisodeDate()[index]);
				str.append("</div></div>");
				str.append("<div class='div-table-row'>");
				//str.append("<div class='div-table-col' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>");
				str.append("<div class='div-table-col' style='width: 100%; height: 35px; '>");
				str.append(_printDatTime+" <br/>"+_rSlip.getExpDate()[index]);
				str.append("</div></div></div></div></div>");
			}
			
			str.append("<div class='div-table-row' style='height: 50px;'>");
			str.append("<div class='div-table-col fontBold' id='divPatientNameLabel' style='width: 20%; height: 50px;'>Patient Name<br/></div>");
			str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
			str.append(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName().toUpperCase()+" "+_rSlip.getPatLastName().toUpperCase()+"<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 25%; height: 50px;'>Age<br/></div>");
			str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
			str.append(_rSlip.getPatAge());
			str.append("</div>");
			str.append("</div>");
			str.append("<div class='div-table-row' style='height: 50px;'>");
			str.append("<div class='div-table-col fontBold' id='divFatherNameLabel' style='width: 20%; height: 50px;'>Father Name /<br/> Spouse Name<br/></div>");
			str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
			str.append(!_rSlip.getPatGuardianName().equals("")?_rSlip.getPatGuardianName().toUpperCase():_rSlip.getPatHusbandName().toUpperCase()+"<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 25%; height: 50px;'>Sex<br/></div>");
			str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
			str.append(_rSlip.getPatGender());
			str.append("</div>");
			str.append("</div>");
				
			
				
			str.append("<div class='div-table-row' style='height: 37px;'>");
			str.append("<div class='div-table-col  fontBold' id='divCasteLabel' style='width: 20%; height: 37px;'>Category<br/>");
			//str.append(RegistrationConfig.OPD_CARD_CATEGORY_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 35%; height: 37px;'>");
			str.append((_rSlip.getPatPrimaryCat().equalsIgnoreCase("null")|| _rSlip.getPatPrimaryCat().equalsIgnoreCase("")?"NA":_rSlip.getPatPrimaryCat()));
			str.append("</div>");
					
			str.append("<div class='div-table-col fontBold' id='divEpisodeDateLabel' style='width: 25%; height: 37px;'>Aadhaar No.<br/>");
			//str.append(RegistrationConfig.OPD_CARD_AADHAAR_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 20%; height: 37px;'>");
			str.append((_rSlip.getPatNationalId()==null?"":_rSlip.getPatNationalId()));
			str.append("</div>");
			str.append("</div>");
				
			String _dRheight="";
			if(_rSlip.getWorkingDays()[index].length()>0)
				_dRheight="60px";
			else
				_dRheight="40px";
			
			str.append("<div class='div-table-row' style='height: "+_dRheight+";'>");
			str.append("<div class='div-table-col fontBold' id='divAreaLabel' style='width: 20%; height: "+_dRheight+";'>Address with <br/>Contact Details<br/>");
			//str.append(RegistrationConfig.OPD_CARD_RESIDENCE_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 35%; height: "+_dRheight+";'>");
			str.append(_rSlip.getAddress1()+ _rSlip.getAddress2());
			str.append("</div>");
					
			str.append("<div class='div-table-col fontBold' id='divTokenLabel' style='width: 25%; height: "+_dRheight+";'>Dept/Room No/<br/>Queue No/Days<br/>");
			//str.append(RegistrationConfig.OPD_CARD_ROOM_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 20%; height: "+_dRheight+"; word-wrap: break-word;'>");
			
				String[] tokenDetail=new String[3];
		  		tokenDetail[0]=_rSlip.getDepartmentToBeVisited()[index]+'-'+_rSlip.getDepartmentUnit()[index]+'/';
		  		tokenDetail[1]=_rSlip.getRoom()[index]+'/'+_rSlip.getQueNo()[index];// Commented By Adil Wasi
		  		tokenDetail[2]="/"+_rSlip.getWorkingDays()[index];
		  		int rows=1;
	  			String tokenSplit="";
	  			for(int i=0;i<tokenDetail.length;i++)
	  			{
	  				if(tokenDetail[i].length()>20)
		  			{
		  				counter++;
		  				rows= tokenDetail[i].length()/20+((tokenDetail[i].length()%20==0)?0:1);		
		  				for(int j=0;j<rows;j++){
		  				
			  				if(j==rows-1){
			  					tokenSplit=tokenDetail[i].substring(j*20);
			  				}else{
			  					tokenSplit=tokenDetail[i].substring(j*20,(j+1)*20);
			  				}if(i==1){
			  					//str.append("");
			  				}else
			  					str.append(tokenSplit);
			  			}
			  			
			  		}	
	  				else if(tokenDetail[i].length()<=20)
	  				{
	  					if(i==0){
	  						str.append(tokenDetail[0]); 
	  					}
	  					if(i==1)
	  					{
	  						str.append(tokenDetail[1]);
	  					}
	  					if(i==2)
	  					{
	  						str.append(tokenDetail[2]);
	  					}
	  				
	  				}
	  			}
	  			str.append("</div>");
	  			str.append("</div>");
	  			str.append("<div class='div-table-row' style='height: 37px;'>");
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank1' style='width: 20%; height: 37px;'>Date<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
	  			str.append("</div>");
	  			str.append("<div class='div-table-col fontBold alignCenter' style='width: 20%; height: 37px;'>Symptoms and Progress<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_SYMPTOMSPROGRESS_MARATHI_LABEL);
	  			str.append("</div>");
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 40%; height: 37px;'>Prescriptions<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_PRESCRIPTIONS_MARATHI_LABEL);
	  			str.append("</div>");
					
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 20%; height: 37px;'>For how many days<br/>");
	  			//str.append(RegistrationConfig.OPD_CARD_HOWMANYDAYSILL_MARATHI_LABEL);
	  			str.append("</div>");
	  			str.append("</div>");
				
	  			str.append("<div class='div-table-row' style='height: 500px;'>");
	  			str.append("<div class='div-table-col' id='divBlank1' style='width: 20%; height: 500px;'></div>");
	  			str.append("<div class='div-table-col' style='width: 20%; height: 500px;'></div>");
	  			str.append("<div class='div-table-col' id='divBlank2' style='width: 40%; height: 500px;'></div>");
	  			str.append("<div class='div-table-col' id='divBlank2' style='width: 20%; height: 500px;'></div>");
	  			str.append("</div>");
	  			str.append("</div></div></div>");
	
					if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_CENTER))
						align="Center";
					else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_LEFT))
						align="Left";
					else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_RIGHT))
						align="Right";
					else
						align="Center";
		
				if(_rSlip.getIsHeader()[index].equals(RegistrationConfig.DISCLAIMER_IS_HEADER_NO)){
					str.append("<div class='div-table'>");
					//str.append("<div class='div-table-row' style='height: 100px;'>");
					str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer1()[index]+"</div></div>");
					str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer2()[index]+"</div></div>");
					str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer3()[index]+"</div></div>");
					//str.append("</div>");
					str.append("</div>");
				}
  		} 
  	  return str.toString();
	}*/
public static String printDuplicateCard_odisha(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request,String _mode) throws ParseException{
	
	  	StringBuilder str=new StringBuilder();
		int counter = 0;  		
		String align="";
		
		SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
	    SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
		String _printDatTime=_sdf2.format(_sdf1.parse((String)_request.getSession().getAttribute(Config.SYSDATE)));
		_rSlip.setSheetNo("1");
		//By Sandip on 23.06.17
		String asterixId1, asterixId2=""; 
		asterixId1 =_rSlip.getPatNationalId();
		if(asterixId1!=null && !asterixId1.equalsIgnoreCase(""))
		{
			asterixId1 = asterixId1.substring(8,12);
			asterixId2 = "XXXX-XXXX-"+asterixId1;
		}
		else
		{
			asterixId2 ="";
		}
		_rSlip.setPatNationalId(asterixId2);
		//ENd 23.06.17
		CharacterEncoding.setCharacterEncoding(_request);
		for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
		{
			str.append("<div class='div-table-simple'  font-size='10px' align='center' style='width: 95%;'>");
			str.append("<div class='div-table-row' style='height: 50px;'>");
	
			/*****************************************************************************************************************************************************************/
  			/****************************************************************On 07.04.2017 By Mukund for putting headers on the Registration cards***************************/
  			if(RegistrationConfig.IS_HEADER_PRINTABLE.equals(RegistrationConfig.IS_HEADER_PRINTABLE_ON)){
              str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/nims-tr-logo.gif'/></div>");
              str.append("<div class='div-table-col' id='divOPDCardHeader' align='center' style='width: 60%'>");
                        
	          str.append("<br><font style='font-size: 16px; font-weight:bold;'>"+_rSlip.getHospitalName().toUpperCase()+"</font><br>");
	       
	          if(_rSlip.getHospitalAddress1()!=null && !_rSlip.getHospitalAddress1().trim().equals(""))
	            str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+"</font><br>");
	          if(_rSlip.getHospitalAddress2()!=null && !_rSlip.getHospitalAddress2().trim().equals(""))
	          {/*str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress2()+"</font><br>");*/}
	          if(_rSlip.getHospitalCity()!=null && !_rSlip.getHospitalCity().trim().equals(""))
	              str.append( _rSlip.getHospitalCity());
	          if(_rSlip.getHospitalDistrict()!=null && !_rSlip.getHospitalDistrict().trim().equals(""))
	              str.append(  ", "+_rSlip.getHospitalDistrict());
	          if(_rSlip.getHospitalpinCode()!=null && !_rSlip.getHospitalpinCode().trim().equals("") && !_rSlip.getHospitalpinCode().trim().equals("0"))
	              str.append( " -"+_rSlip.getHospitalpinCode());  
	          if(_rSlip.getHospitalState()!=null && !_rSlip.getHospitalState().trim().equals(""))
	              str.append(  ", "+_rSlip.getHospitalState());
              str.append(" (INDIA)");  
            
             str.append("<br>");
             
             str.append("<font style='font-size: 14px;'>");
                if(_rSlip.getHospitalPhone()!=null && !_rSlip.getHospitalPhone().trim().equals(""))
                {
                    str.append(RegistrationConfig.OPD_CARD_HEADER_LINE_4_PHONE_LABEL); 
                    str.append(_rSlip.getHospitalPhone());
                }
                if(_rSlip.getHospitalFax()!=null && !_rSlip.getHospitalFax().trim().equals("")){
                    str.append(", " + RegistrationConfig.OPD_CARD_HEADER_LINE_4_FAX_LABEL);
                    str.append(_rSlip.getHospitalFax());
                }
                
                str.append("</font><br></div>");
                //DIV QRCODE 
                str.append("<div id='divQRCodeControl' class='div-table-col' style='width: 20%' align='right'>");
                if(_rSlip.getIsQRCodePrintFlag()!=null && _rSlip.getIsQRCodePrintFlag().equals(RegistrationConfig.ISQRCodePrintFlag_True))
                {
                	str.append("<img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no QR found in session\">");
            	}
                str.append("</div>");
                
        }			
  			/*****************************************************************************************************************************************************************/
  			/****************************************************************End 07.04.2017 Mukund****************************************************************************/
  			
			
			str.append("</div>");
			str.append("<br>");
			str.append("<div class='div-table-row' style='height: 10px;'>");
			str.append("<div class='div-table-col fontBold alignCenter' style='width: 100%; height: 20px'>");
				if(_mode.equalsIgnoreCase("NR"))
					str.append("OPD Registration Card");
				else if(_mode.equalsIgnoreCase("ER"))
					str.append("EMERGENCY Registration Card");
				
			str.append("</div>");
			str.append("</div>");
	
			str.append("<div class='div-table-row' style='height: 10px;'>");
			str.append("<div class='div-table-col' style='width: 50%; height: 20px'></div>");
			str.append("<div class='div-table-col fontBold alignRight' style='width: 50%; height: 20px;'>");
			
			if(_mode.equalsIgnoreCase("NR")||_mode.equalsIgnoreCase("ER"))
				str.append("Registration FEE (Rs) :"+_rSlip.getPatAmountCollected());
			else if(_mode.equalsIgnoreCase("DV"))
				str.append("Department Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
				str.append("</div></div>");
 
				str.append("<b>");
				if(!_mode.equalsIgnoreCase("DC"))
					str.append("<div class='div-table-row' style='height: 10px;'><div  style='height: 37px;text-align: center;border: 0px solid #000;'>OUT PATIENT RECORD - REPRINTED CARD");
				else
					str.append("<div class='div-table-row' style='height: 10px;'><div  style='height: 37px;text-align: center;border: 0px solid #000;'>OUT PATIENT RECORD - DUPLICATE CARD");
				
				if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("1"))
					str.append("( MORNING CLINIC )");
				else if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("4"))
					str.append("( EVENING CLINIC )");	
				str.append("</b></div></div>");
				

				str.append("<div class='div-table-row width90' >");
				str.append("<div class='div-table-col control width50' id='divEpisodeDateLabel'><p>Last Visit Date & Time :&nbsp;");			
				str.append("<b>"+_rSlip.getEpisodeDate()[index]+"</b>");
				str.append("</p></div>");
		
				str.append("<div class='div-table-col label width50' >");
				str.append("<div id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");
				str.append("</div></div>");
				str.append("</div>");
				
				
				
				str.append("<div style='border: 1px solid black;width:95%;'>");
				str.append("<div class='div-table-simple border'>");
				str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 40px;'>");
	  			str.append("<div class='div-table-simple' align='center' style='width: 100%;'>");
				str.append("<div class='div-table-row' style='width: 100%;height: 20px;'>");
				str.append("<div class='div-table-col' style='width: 21%;border: 0px solid #000'>CR No ");
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 75%;border: 0px solid #000'>");
				str.append(":&nbsp;<b>");				
				str.append(_rSlip.getPatCrNo());
				str.append("</b></div>");
				str.append("</div>");
				
				str.append("</div></div>");
				
				str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 33%; height: 40px;padding-top: 15px;'>Aadhaar :&nbsp;<b>");
				str.append(_rSlip.getPatNationalId());
				str.append("</b></div>");
				str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 25%; height: 40px;padding-top: 15px;'>FEE (Rs) :&nbsp;<b>");
				str.append(_rSlip.getPatAmountCollected());
				str.append("</b></div>");
			/*else//Separating Reprint & duplicate card print headers
			{	
				str.append("<b>");
				str.append("<div  style='width: 100%;height: 37px;text-align: center;border: 0px solid #000;'>OUT PATIENT RECORD - DUPLICATE CARD");
				str.append("</div></b>");
				
				str.append("<div class='div-table-row width90' >");
				str.append("<div class='div-table-col control width50' id='divEpisodeDateLabel'><p>Last Visit Date & Time :&nbsp;");			
				str.append("<b>"+_rSlip.getEpisodeDate()[index]+"</b>");
				str.append("</p></div>");
		
				str.append("<div class='div-table-col label width50' >");
				str.append("<div id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");
				str.append("</div></div>");
				str.append("</div>");
				
				str.append("<div style='border: 1px solid black;'>");
				str.append("<div class='div-table-simple border'>");

				str.append("<div class='div-table-row' style='height: 40px;'>");
				
				str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 40px;'>CR No :&nbsp;<b>");
					str.append(_rSlip.getPatCrNo());
				str.append("</b></div>");
				
				str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 33%; height: 40px;'>Aadhaar :&nbsp;<b>");
					str.append(_rSlip.getPatNationalId());
				str.append("</b></div>");
				str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 25%; height: 40px;'>FEE (Rs) :&nbsp;<b>");				
					str.append(_rSlip.getPatAmountCollected());
				str.append("</b></div>");
				str.append("</div>");
			}*/
			
			str.append("<div class='div-table-row' style='height: 40px;'>");
			str.append("<div class='div-table-col' id='divPatientNameLabel' style='width: 42%; height: 40px;'>Patient Name &nbsp;&nbsp;:&nbsp;");
			
			str.append("<b>");
			str.append(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName().toUpperCase()+" "+_rSlip.getPatLastName().toUpperCase());
			str.append("</b>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divAgeLabel' style='width: 33%; height: 40px;'>Age/Sex&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<b>");
			
			
			str.append(_rSlip.getPatAge());
			str.append("/");
			str.append(_rSlip.getPatGender().substring(0,1));
			
			str.append("</b></div>");
			str.append("<div class='div-table-col' id='divCasteLabel' style='width: 25%; height: 40px;'>Category&nbsp;&nbsp;:&nbsp;<b>");
			
			
			str.append((_rSlip.getPatPrimaryCat().equalsIgnoreCase("null")|| _rSlip.getPatPrimaryCat().equalsIgnoreCase("")?"NA":_rSlip.getPatPrimaryCat()));
		
			str.append("</b></div>");
			str.append("</div>");
			if(_rSlip.getPatPrimaryCat().equalsIgnoreCase("staff"))
			{
				str.append("<div class='div-table-row' style='height: 40px;'>");
				str.append("<div class='div-table-col' id='divStaffNameLabel' style='width: 42%; height: 40px;'>Staff Name/No. &nbsp;&nbsp;:&nbsp;");
				
				str.append("<b>");
				str.append(_rSlip.getDependentName().toUpperCase());
				str.append("/");
				str.append((_rSlip.getHiddenPatIdNo()));
				str.append("</b>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divRelationLabel' style='width: 33%; height: 40px;'>Staff Relation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
				
				
				str.append(_rSlip.getPatMemRelationName().toUpperCase());
				
				
				str.append("</div>");
				str.append("<div class='div-table-col' id='divDepartmentLabel' style='width: 25%; height: 40px;'>Staff Department&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
				
				
				str.append(_rSlip.getPatMemDeptName().toUpperCase());
				
				
				str.append("</div>");
				
				str.append("</div>");
			}
			str.append("<div class='div-table-row' style='width: 100%;height: 40px;'>");
			str.append("<div class='div-table-col' id='divAreaLabel' style='width: 75%; height: 40px;'>Address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<b>");
			
			
			str.append(_rSlip.getAddress1()+ _rSlip.getAddress2());
		
			str.append("</b></div>");
			str.append("<div class='div-table-col' style='width: 25%; height: 40px;'>MLC NO. : ");
			str.append(_rSlip.getMlcDetail());
			str.append("</div>");
		
			str.append("</div>");
			str.append("<div class='div-table-row ' id='divTokenLabel' style='width: 100%; height: 62px;'>");
			str.append("<div class='div-table-col'  style='width: 42%;height: 62px;'>");
			str.append("<div>Department  :&nbsp;");
			
			String[] tokenDetail=new String[4];
	  		tokenDetail[0]=_rSlip.getDepartmentToBeVisited()[index]+'-'+_rSlip.getDepartmentUnit()[index];
	  		int rows=1;
  			String tokenSplit="";
  			str.append("<b>");
  				if(tokenDetail[0].length()>20)
	  			{
	  				counter++;
	  				rows= tokenDetail[0].length()/20+((tokenDetail[0].length()%20==0)?0:1);		
	  				for(int j=0;j<rows;j++){
	  				
		  				if(j==rows-1){
		  					tokenSplit=tokenDetail[0].substring(j*20);
		  				}else{
		  					tokenSplit=tokenDetail[0].substring(j*20,(j+1)*20);
		  				}
		  				
		  					str.append(tokenSplit);
		  					
		  			}
		  			
		  		}	
  				else if(tokenDetail[0].length()<=20)
  				{
  					
  					str.append(tokenDetail[0]);
  					
  					 				
  				}
  				str.append("</b>");
  				str.append("</div>");
  				str.append("<div>OPD Days :&nbsp;<b>");
  		  		tokenDetail[2]=_rSlip.getWorkingDays()[index];
  		  		tokenSplit="";
  		  	if(tokenDetail[2].length()>20)
  			{
  				counter++;
  				rows= tokenDetail[2].length()/20+((tokenDetail[2].length()%20==0)?0:1);		
  				for(int j=0;j<rows;j++){
  				
	  				if(j==rows-1){
	  					tokenSplit=tokenDetail[2].substring(j*20);
	  				}else{
	  					tokenSplit=tokenDetail[2].substring(j*20,(j+1)*20);
	  				}
	  				
	  					str.append(tokenSplit);
	  					
	  			}
	  			
	  		}	
				else if(tokenDetail[2].length()<=20)
				{
					str.append(tokenDetail[2]);		
					 				
				}
  		  	str.append("</b></div>");
  		  	/**Queue no Field*/
  		  str.append("<div><font color=''>Queue No. :<b>");
		  	tokenDetail[3]=_rSlip.getQueNo()[index];
			if(tokenDetail[3].length()>20)
			{
				counter++;
				rows= tokenDetail[3].length()/20+((tokenDetail[3].length()%20==0)?0:1);		
				for(int j=0;j<rows;j++){
				
				if(j==rows-1){
					tokenSplit=tokenDetail[3].substring(j*20);
				}else{
					tokenSplit=tokenDetail[3].substring(j*20,(j+1)*20);
				}
				
					
					
			}
			
		}	
			else if(tokenDetail[3].length()<=20)
			{
				str.append(tokenDetail[3]);		
				 				
			}
		  	
		  	str.append("</b></font></div>");
  		  	/***/
  		  	str.append("</div>");
  			str.append("<div class='div-table-col'  style='width: 33%; height: 62px;'>");
  			str.append("<div>Doctor	:	");
			str.append("<b>"+_rSlip.getUnitConsultant()[index]+"</b>");
			str.append("</div>");
			str.append("<div></div>");
			str.append("</div>");
  		  
  		str.append("<div class='div-table-col'  style='width: 25%; height: 62px;'>");
	  	str.append("<div>Room No.	:");
		str.append("<b>");
			tokenDetail[1]=_rSlip.getRoom()[index];// Commented By Adil Wasi
			tokenSplit="";
	  		if(tokenDetail[1].length()>20)
	  		{
				counter++;
				rows= tokenDetail[1].length()/20+((tokenDetail[1].length()%20==0)?0:1);		
				for(int j=0;j<rows;j++){
			
  				if(j==rows-1){
  					tokenSplit=tokenDetail[1].substring(j*20);
  				}else{
  					tokenSplit=tokenDetail[1].substring(j*20,(j+1)*20);
  				}
				}
			}	
			else if(tokenDetail[1].length()<=20)
			{
				str.append(tokenDetail[1]);		
			}
		str.append("</b>");
		str.append("</div>");
		str.append("</div>");
		/******************************************************************************************************************************************/
			str.append("</div>");
			str.append("<div class='div-table-simple border'>");
			str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>ALERTS");
			str.append("</div>");
			str.append("</br>");
			
			str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>DIAGNOSIS");
			str.append("</div>");
			str.append("</div>");
	  			str.append("<div class='div-table-row' style='height: 37px;'>");
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank1' style='width: 25%; height: 37px;'>Date<br/>");
	  			str.append("</div>");
	  			
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 50%; height: 37px;'>Clinical Notes/Prescriptions<br/>");
	  			str.append("</div>");
					
	  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 25%; height: 37px;'>Investigations<br/>");
	  			str.append("</div>");
	  			str.append("</div>");
				
	  			str.append("<div class='div-table-row' style='height: 425px;'>");
	  			str.append("<div class='div-table-col' id='divBlank1' style='width: 25%; height: 425px;'></div>");
	  			str.append("<div class='div-table-col' id='divBlank2' style='width: 50%; height: 425px;'></div>");
	  			str.append("<div class='div-table-col' id='divBlank2' style='width: 25%; height: 425px;'></div>");
	  			str.append("</div>");
	  			str.append("</div></div></div>");
	  			
				if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_CENTER))
					align="Center";
				else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_LEFT))
					align="Left";
				else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_RIGHT))
					align="Right";
				else
					align="Center";
	
			if(_rSlip.getIsHeader()[index].equals(RegistrationConfig.DISCLAIMER_IS_HEADER_NO)){
				str.append("<div class='div-table'>");
				//str.append("<div class='div-table-row' style='height: 100px;'>");
				/*str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer1()[index]+"</div></div>");
				str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer2()[index]+"</div></div>");
				str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer3()[index]+"</div></div>");
				*///str.append("</div>");
				str.append("</div>");
			}
		} 
	  return str.toString();
}
public static String printDuplicateCard2(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request,String _mode) throws ParseException{
	
  	StringBuilder str=new StringBuilder();
	int counter = 0;  		
	String align="";
	
	SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
	String _printDatTime=_sdf2.format(_sdf1.parse((String)_request.getSession().getAttribute(Config.SYSDATE)));
	_rSlip.setSheetNo("1");
	CharacterEncoding.setCharacterEncoding(_request);
	for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
	{
		
		//str.append("<div align='right'><table width='100%' class='donotprint'><tr><td width='100%' align='right'><div align='right'><img src='../../hisglobal/images/printer_tab.jpg' onclick='window.print();'/><img src='../../hisglobal/images/back_button.gif' onclick='window.close();'/></div></td></tr></table></div>");
		
		str.append("<div class='div-table-simple'  font-size='10px' align='center' style='width: 95%;'>");
		
		str.append("<div class='div-table-row' style='height: 50px;'>");
		
		/*****************************************************************************************************************************************************************/
			/****************************************************************On 07.04.2017 By Mukund for putting headers on the Registration cards***************************/
			if(RegistrationConfig.IS_HEADER_PRINTABLE.equals(RegistrationConfig.IS_HEADER_PRINTABLE_ON)){
	  			//str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/NIMSLOGO.jpg'/></div>");
	  			str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/nims-tr-logo.gif'/></div>");
	  			str.append("<div class='div-table-col' id='divOPDCardHeader' align='center' style='width: 60%'>");
						
	  		  //str.append("<h3>"+_rSlip.getHospitalName()+"</h3>");
	  		  str.append("<br><font style='font-size: 16px; font-weight:bold;'>"+_rSlip.getHospitalName().toUpperCase()+"</font><br>");
	  		  //str.append("<h4>"+_rSlip.getHospitalAddress1());
	  		  if(_rSlip.getHospitalAddress1()!=null && !_rSlip.getHospitalAddress1().trim().equals(""))
		      	str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+"</font><br>");
		  	  if(_rSlip.getHospitalAddress2()!=null && !_rSlip.getHospitalAddress2().trim().equals(""))
	  			 //str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress2()+"</font><br>");
	  		  if(_rSlip.getHospitalCity()!=null && !_rSlip.getHospitalCity().trim().equals(""))
	  			  str.append( _rSlip.getHospitalCity());
	  		  if(_rSlip.getHospitalDistrict()!=null && !_rSlip.getHospitalDistrict().trim().equals(""))
	 			  str.append(  ", "+_rSlip.getHospitalDistrict());
	  		  if(_rSlip.getHospitalpinCode()!=null && !_rSlip.getHospitalpinCode().trim().equals("") && !_rSlip.getHospitalpinCode().trim().equals("0"))
	  			  str.append( " -"+_rSlip.getHospitalpinCode());  
	  		  if(_rSlip.getHospitalState()!=null && !_rSlip.getHospitalState().trim().equals(""))
				  str.append(  ", "+_rSlip.getHospitalState());
	  		 	str.append(" (INDIA)");  
	  		// str.append("</font><br>");
	  		 str.append("<br>");
	  		 //str.append("<h5>");
	  		 str.append("<font style='font-size: 14px;'>");
	  			if(_rSlip.getHospitalPhone()!=null && !_rSlip.getHospitalPhone().trim().equals(""))
	  			{
	  				str.append(RegistrationConfig.OPD_CARD_HEADER_LINE_4_PHONE_LABEL); 
	  				str.append(_rSlip.getHospitalPhone());
	  			}
	  			if(_rSlip.getHospitalFax()!=null && !_rSlip.getHospitalFax().trim().equals("")){
	  				str.append(", " + RegistrationConfig.OPD_CARD_HEADER_LINE_4_FAX_LABEL);
	  				str.append(_rSlip.getHospitalFax());
	  			}
	  			/*if(_rSlip.getHospitalEmail()!=null && !_rSlip.getHospitalEmail().trim().equals("")){
	  				str.append(", " +RegistrationConfig.OPD_CARD_HEADER_LINE_4_EMAIL_LABEL);
	  	  			str.append(_rSlip.getHospitalEmail());
	  			}*/
	  			
	  			str.append("</font><br></div>");
	  			
	  			 //DIV QRCODE //By Mukund
                str.append("<div id='divQRCodeControl' class='div-table-col' style='width: 20%' align='right'>");
                //str.append("<img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"no QR found in session\">");
                str.append("</div>");
                
			}			
			/*****************************************************************************************************************************************************************/
			/****************************************************************End 07.04.2017 Mukund****************************************************************************/
			
		
		str.append("</div>");
		str.append("<br>");
		str.append("<div class='div-table-row' style='height: 10px;'>");
		str.append("<div class='div-table-col fontBold alignCenter' style='width: 100%; height: 20px'>");
			if(_mode.equalsIgnoreCase("NR"))
				str.append("OPD Registration Card");
			else if(_mode.equalsIgnoreCase("ER"))
				str.append("EMERGENCY Registration Card");
			
		str.append("</div>");
		str.append("</div>");

		str.append("<div class='div-table-row' style='height: 10px;'>");
		str.append("<div class='div-table-col' style='width: 50%; height: 20px'></div>");
		str.append("<div class='div-table-col fontBold alignRight' style='width: 50%; height: 20px;'>");
		
		if(_mode.equalsIgnoreCase("NR")||_mode.equalsIgnoreCase("ER"))
			str.append("Registration FEE (Rs) :"+_rSlip.getPatAmountCollected());
		else if(_mode.equalsIgnoreCase("DV"))
			str.append("Department Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
			str.append("</div></div>");

			if(!_mode.equalsIgnoreCase("DC")){
			str.append("<b>");
			str.append("<div  style='width: 90%;height: 37px;text-align: center;border: 0px solid #000;'>OUT PATIENT RECORD - REPRINTED CARD");
			/*str.append("</div>");
			
			str.append("</b>");
			str.append("<div  style='width: 90%;height: 37px;text-align: center;border: 0px solid #000;'>");*/
			if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("1"))
				str.append("( MORNING CLINIC )");
			else if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("4"))
				str.append("( EVENING CLINIC )");	
			str.append("</div>");str.append("</b>");
			str.append("<b>");

			str.append("<div  style='width: 90%;height: 20px;'>");
			str.append("<div class='div-table-col' style='text-align: left;width: 60%;height: 20px;border: 0px solid #000;'>Card Valid Upto :&nbsp;");
			str.append("</b>");				
			if(_mode.equalsIgnoreCase("ER"))
			{
			str.append(_rSlip.getExpDate()[index]);
			}
			else
			{
				if(!_rSlip.getExpDate()[index].equals(""))
				str.append(_sdf3.format(_sdf2.parse((String)_rSlip.getExpDate()[index])));
			}
			str.append("</div>");
	
			str.append("<br/>");
			str.append("</div>");
			
			
			str.append("<div style='border: 1px solid black;width:95%;'>");
			str.append("<div class='div-table-simple border'>");
			//str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 40px;'>CR No &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
			str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 55px;'>");
			//str.append(RegistrationConfig.OPD_CARD_NO_MARATHI_LABEL);
  			str.append("<div class='div-table-simple' align='center' style='width: 100%;'>");
			str.append("<div class='div-table-row' style='width: 100%;height: 20px;'>");
			
			str.append("<div class='div-table-col' style='width: 21%;border: 0px solid #000'>CR No ");
			
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 75%;border: 0px solid #000'>");
			str.append(":&nbsp;");
			str.append("<b>");
			
			str.append(_rSlip.getPatCrNo());
			
			str.append("</b>");
			str.append("</div>");
			
			str.append("</div>");
			
			str.append("<div class='div-table-row' style='width: 100%;height: 30px;'>");
			
			str.append("<div class='div-table-col' style='width: 25%;border: 0px solid #000'>");
			
			str.append("</div>");
			
			str.append("<div class='div-table-col' style='width: 75%;border: 0px solid #000;margin-left:-20%;'>");
			//str.append("<div class='div-table-col' id='divBarCodeControl' style='width: 75%;border: 0px solid #000;;p-left:-45%;'>"+_rSlip.getPatCrNo()+"</div>");
			str.append("<div  id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");
			str.append("</div>");
			str.append("</div>");
			
			
			
			
			//str.append("&nbsp;&nbsp;");
			//str.append("<div id='divBarCodeControl' style='margin-right:47%;'>"+_rSlip.getPatCrNo()+"</div>");
			str.append("</div>");
			
			str.append("</div>");

			str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 33%; height: 55px;padding-top: 15px;'>Date & Time :&nbsp;");
			//str.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
			
			
		
			str.append(_rSlip.getEpisodeDate()[index]);
		
			str.append("</div>");
			str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 25%; height: 55px;padding-top: 15px;'>Sheet No. :&nbsp;");
			
			
			//To Do
			
			str.append(_rSlip.getSheetNo());
			str.append("</div>");
		}
		else
		{	
			str.append("<b>");
			str.append("<div  style='width: 100%;height: 37px;text-align: center;border: 0px solid #000;'>OUT PATIENT RECORD - DUPLICATE CARD");
			str.append("</div>");
			
			str.append("<div  style='width: 100%;height: 20px;'>");
			str.append("<div class='div-table-col' style='text-align: left;width: 60%;height: 20px;border: 0px solid #000;'>Card Valid Upto :&nbsp;");
			str.append("</b>");				
			if(_mode.equalsIgnoreCase("ER"))
			{
			str.append(_rSlip.getExpDate()[index]);
			}
			else
			{
				if(!_rSlip.getExpDate()[index].equals(""))
				str.append(_sdf3.format(_sdf2.parse((String)_rSlip.getExpDate()[index])));
			}
			str.append("</div>");
		//	str.append("<b>");
			//str.append("<div class='div-table-col' style='text-align: center;width: 50%;height: 37px;border: 0px solid #000;'>OUT PATIENT RECORD - DUPLICATE CARD");
			
			//str.append("</b>");
			//str.append("</div>");
			str.append("<div class='div-table-col' style='text-align: right;width: 40%;height: 20px;border: 0px solid #000;'>Printed on :&nbsp;");
				
			str.append(_rSlip.getEpisodeDate()[index]);
			str.append("</div>");
			str.append("<br/>");
			str.append("</div>");
			//
			str.append("<div style='border: 1px solid black;'>");
			str.append("<div class='div-table-simple border'>");
			//str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 40px;'>CR No &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
			str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 55px;'>");
			//str.append(RegistrationConfig.OPD_CARD_NO_MARATHI_LABEL);
  			str.append("<div class='div-table-simple' align='center' style='width: 100%;'>");
			str.append("<div class='div-table-row' style='width: 100%;height: 20px;'>");
			
			str.append("<div class='div-table-col' style='width: 21%;border: 0px solid #000'>CR No ");
			
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 75%;border: 0px solid #000'>");
			str.append(":&nbsp;");
			str.append("<b>");
			
			str.append(_rSlip.getPatCrNo());
			
			str.append("</b>");
			str.append("</div>");
			
			str.append("</div>");
			
			str.append("<div class='div-table-row' style='width: 100%;height: 30px;'>");
			
			str.append("<div class='div-table-col' style='width: 25%;border: 0px solid #000'>");
			
			str.append("</div>");
			
			str.append("<div class='div-table-col' style='width: 75%;border: 0px solid #000;margin-left:-20%;'>");
			//str.append("<div class='div-table-col' id='divBarCodeControl' style='width: 75%;border: 0px solid #000;;p-left:-45%;'>"+_rSlip.getPatCrNo()+"</div>");
			str.append("<div  id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");
			str.append("</div>");
			str.append("</div>");
			
			
			
			
			//str.append("&nbsp;&nbsp;");
			//str.append("<div id='divBarCodeControl' style='margin-right:47%;'>"+_rSlip.getPatCrNo()+"</div>");
			str.append("</div>");
			
			str.append("</div>");

			str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 33%; height: 55px;padding-top: 15px;'>Last Visit </br> Date & Time :&nbsp;");
			//str.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
			
			
		
			str.append(_rSlip.getEpisodeDate()[index]);
		
			str.append("</div>");
			str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 25%; height: 55px;padding-top: 15px;'>Sheet No. :&nbsp;");
			
			
			str.append(_rSlip.getSheetNo());
			
			str.append("</div>");
			//
		}
		
		str.append("<div class='div-table-row' style='height: 40px;'>");
		str.append("<div class='div-table-col' id='divPatientNameLabel' style='width: 42%; height: 40px;'>Patient Name &nbsp;&nbsp;:&nbsp;");
		//str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
		
		str.append("<b>");
		str.append(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName().toUpperCase()+" "+_rSlip.getPatLastName().toUpperCase());
		str.append("</b>");
		str.append("</div>");
		str.append("<div class='div-table-col' id='divAgeLabel' style='width: 33%; height: 40px;'>Age/Sex&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
		//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
		
		
		str.append(_rSlip.getPatAge());
		str.append("/");
		str.append(_rSlip.getPatGender().substring(0,1));
		
		str.append("</div>");
		str.append("<div class='div-table-col' id='divCasteLabel' style='width: 25%; height: 40px;'>Category&nbsp;&nbsp;:&nbsp;");
		
		
		str.append((_rSlip.getPatPrimaryCat().equalsIgnoreCase("null")|| _rSlip.getPatPrimaryCat().equalsIgnoreCase("")?"NA":_rSlip.getPatPrimaryCat()));
	
		str.append("</div>");
		str.append("</div>");
		if(_rSlip.getPatPrimaryCat().equalsIgnoreCase("staff"))
		{
			str.append("<div class='div-table-row' style='height: 40px;'>");
			str.append("<div class='div-table-col' id='divStaffNameLabel' style='width: 42%; height: 40px;'>Staff Name/No. &nbsp;&nbsp;:&nbsp;");
			//str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
			
			str.append("<b>");
			str.append(_rSlip.getDependentName().toUpperCase());
			str.append("/");
			str.append((_rSlip.getHiddenPatIdNo()));
			str.append("</b>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divRelationLabel' style='width: 33%; height: 40px;'>Staff Relation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
			//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
			
			
			str.append(_rSlip.getPatMemRelationName().toUpperCase());
			
			
			str.append("</div>");
			str.append("<div class='div-table-col' id='divDepartmentLabel' style='width: 25%; height: 40px;'>Staff Department&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
			//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
			
			
			str.append(_rSlip.getPatMemDeptName().toUpperCase());
			
			
			str.append("</div>");
			
			str.append("</div>");
		}
		str.append("<div class='div-table-row' style='width: 100%;height: 40px;'>");
		str.append("<div class='div-table-col' id='divAreaLabel' style='width: 100%; height: 40px;'>Address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
		//str.append(RegistrationConfig.OPD_CARD_RESIDENCE_MARATHI_LABEL);
		
		
		str.append(_rSlip.getAddress1()+ _rSlip.getAddress2());
	
		str.append("</div>");
		str.append("</div>");
		str.append("<div class='div-table-row ' id='divTokenLabel' style='width: 100%; height: 62px;'>");
		str.append("<div class='div-table-col'  style='width: 42%;height: 62px;'>");
		str.append("<div>Department  :&nbsp;");
		
		String[] tokenDetail=new String[4];
  		tokenDetail[0]=_rSlip.getDepartmentToBeVisited()[index]+'-'+_rSlip.getDepartmentUnit()[index];
  		int rows=1;
			String tokenSplit="";
			str.append("<b>");
				if(tokenDetail[0].length()>20)
  			{
  				counter++;
  				rows= tokenDetail[0].length()/20+((tokenDetail[0].length()%20==0)?0:1);		
  				for(int j=0;j<rows;j++){
  				
	  				if(j==rows-1){
	  					tokenSplit=tokenDetail[0].substring(j*20);
	  				}else{
	  					tokenSplit=tokenDetail[0].substring(j*20,(j+1)*20);
	  				}
	  				
	  					str.append(tokenSplit);
	  					
	  			}
	  			
	  		}	
				else if(tokenDetail[0].length()<=20)
				{
					
					str.append(tokenDetail[0]);
					
					 				
				}
				str.append("</b>");
				str.append("</div>");
				//str.append("</br>");
				str.append("<div>OPD Days :&nbsp;");
		  		tokenDetail[2]=_rSlip.getWorkingDays()[index];
		  		tokenSplit="";
		  	if(tokenDetail[2].length()>20)
			{
				counter++;
				rows= tokenDetail[2].length()/20+((tokenDetail[2].length()%20==0)?0:1);		
				for(int j=0;j<rows;j++){
				
  				if(j==rows-1){
  					tokenSplit=tokenDetail[2].substring(j*20);
  				}else{
  					tokenSplit=tokenDetail[2].substring(j*20,(j+1)*20);
  				}
  				
  					str.append(tokenSplit);
  					
  			}
  			
  		}	
			else if(tokenDetail[2].length()<=20)
			{
				str.append(tokenDetail[2]);		
				 				
			}
		  	str.append("</div>");
		  	   str.append("</div>");
				
	
				str.append("<div class='div-table-col'  style='width: 33%; height: 62px;'>");
				/******************************************************************************************************************************/
				/*str.append("<div>Room No.	:");
				str.append("<b>");
				tokenDetail[1]=_rSlip.getRoom()[index];// Commented By Adil Wasi
		  		tokenSplit="";
		  		if(tokenDetail[1].length()>20)
		  		{
  				counter++;
  				rows= tokenDetail[1].length()/20+((tokenDetail[1].length()%20==0)?0:1);		
  				for(int j=0;j<rows;j++){
				
	  				if(j==rows-1){
	  					tokenSplit=tokenDetail[1].substring(j*20);
	  				}else{
	  					tokenSplit=tokenDetail[1].substring(j*20,(j+1)*20);
	  				}
	  			}
  			
		  		}	
			else if(tokenDetail[1].length()<=20)
			{
				str.append(tokenDetail[1]);		
				 				
			}
		  		
		  str.append("</b>");
		str.append("</div>");*/
		str.append("<div>Doctor	:	");
		str.append("<b>"+_rSlip.getUnitConsultant()[index]+"</b>");
		str.append("</div>");
	
/******************************************************************************************************************************/
	//By Mukund on 10.04.2017 for hiding queue no on opd cards
  	/*str.append("<div><font color='white'>Queue No. :");
		  	tokenDetail[3]=_rSlip.getQueNo()[index];
			if(tokenDetail[3].length()>20)
			{
				counter++;
				rows= tokenDetail[3].length()/20+((tokenDetail[3].length()%20==0)?0:1);		
				for(int j=0;j<rows;j++){
				
  				if(j==rows-1){
  					tokenSplit=tokenDetail[3].substring(j*20);
  				}else{
  					tokenSplit=tokenDetail[3].substring(j*20,(j+1)*20);
  				}
  				
  					
  					
  			}
  			
  		}	
			else if(tokenDetail[3].length()<=20)
			{
				str.append("");		
				 				
			}
		  	
		  	str.append("</font></div>");*/
	 		str.append("<div></div>");
	 		//End on 10.04.2017  By Mukund to hide the queue no field from opd cards
		  str.append("</div>");
		  /******************************************************************************************************************************************/
		  	/*str.append("<div class='div-table-col'  style='width: 25%; height: 62px;'>Doctor&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
		  	
		str.append("<b>");
		str.append(_rSlip.getUnitConsultant()[index]);
		str.append("</b>");
		str.append("</div>");*/
		str.append("<div class='div-table-col'  style='width: 25%; height: 62px;'>");
  	str.append("<div>Room No.	:");
	str.append("<b>");
		tokenDetail[1]=_rSlip.getRoom()[index];// Commented By Adil Wasi
		tokenSplit="";
  		if(tokenDetail[1].length()>20)
  		{
			counter++;
			rows= tokenDetail[1].length()/20+((tokenDetail[1].length()%20==0)?0:1);		
			for(int j=0;j<rows;j++){
		
				if(j==rows-1){
					tokenSplit=tokenDetail[1].substring(j*20);
				}else{
					tokenSplit=tokenDetail[1].substring(j*20,(j+1)*20);
				}
			}
		}	
		else if(tokenDetail[1].length()<=20)
		{
			str.append(tokenDetail[1]);		
		}
	str.append("</b>");
	str.append("</div>");
	str.append("</div>");
	/******************************************************************************************************************************************/
		str.append("</div>");
		str.append("<div class='div-table-simple border'>");
		//str.append("<div class='div-table-row' style='width:100%;height: 80px;'>ALERTS");
		str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>ALERTS");
		str.append("</div>");
		str.append("</br>");
		//str.append("</div>");
	//	str.append("<div class='div-table-row' style='width:100%;height: 80px;'>DIAGNOSIS");
		str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>DIAGNOSIS");
		str.append("</div>");
		str.append("</div>");
  			str.append("<div class='div-table-row' style='height: 37px;'>");
  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank1' style='width: 25%; height: 37px;'>Date<br/>");
  			//str.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
  			str.append("</div>");
  			/*str.append("<div class='div-table-col fontBold alignCenter' style='width: 20%; height: 37px;'>Symptoms and Progress<br/>");
  			//str.append(RegistrationConfig.OPD_CARD_SYMPTOMSPROGRESS_MARATHI_LABEL);
  			str.append("</div>");*/
  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 50%; height: 37px;'>Clinical Notes/Prescriptions<br/>");
  			//str.append(RegistrationConfig.OPD_CARD_PRESCRIPTIONS_MARATHI_LABEL);
  			str.append("</div>");
				
  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 25%; height: 37px;'>Investigations<br/>");
  			//str.append(RegistrationConfig.OPD_CARD_HOWMANYDAYSILL_MARATHI_LABEL);
  			str.append("</div>");
  			str.append("</div>");
			
  			str.append("<div class='div-table-row' style='height: 425px;'>");
  			str.append("<div class='div-table-col' id='divBlank1' style='width: 25%; height: 425px;'></div>");
  			//str.append("<div class='div-table-col' style='width: 20%; height: 700px;'></div>");
  			str.append("<div class='div-table-col' id='divBlank2' style='width: 50%; height: 425px;'></div>");
  			str.append("<div class='div-table-col' id='divBlank2' style='width: 25%; height: 425px;'></div>");
  			str.append("</div>");
  			//str.append("</div>");
  			str.append("</div></div></div>");
  			//str.append("</div></div></div>");

			if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_CENTER))
				align="Center";
			else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_LEFT))
				align="Left";
			else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_RIGHT))
				align="Right";
			else
				align="Center";

		if(_rSlip.getIsHeader()[index].equals(RegistrationConfig.DISCLAIMER_IS_HEADER_NO)){
			str.append("<div class='div-table'>");
			//str.append("<div class='div-table-row' style='height: 100px;'>");
			/*str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer1()[index]+"</div></div>");
			str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer2()[index]+"</div></div>");
			str.append("<div class='div-table-row' style='height: 20px;'><div class='div-table-col' align='"+align+"' style='width: 100%; height: 20px;'>"+ _rSlip.getDisclaimer3()[index]+"</div></div>");
			*///str.append("</div>");
			str.append("</div>");
		}
	} 
  return str.toString();
}
	public  RegistrationSlip preapareSlip()
	{		
		
		RegistrationSlip _rSlip=new RegistrationSlip();
		HelperMethods.populate(_rSlip,patientVO);
		_rSlip.setPatPrimaryCat(patientVO.getPatPrimaryCat());				
		
		String fileNo[]  = new String[episodeVO.length];
		String depName[] = new String[episodeVO.length];
		String depUnit[] = new String[episodeVO.length];
		String queueNo[] = new String[episodeVO.length];
		String roomName[]= new String[episodeVO.length];
		
		for(int index=0;index<episodeVO.length;index++)
		{			
			fileNo[index]=episodeVO[index].getFileNo();
			roomName[index]=episodeVO[index].getRoom();
			depName[index]=episodeVO[index].getDepartment();			
			depUnit[index]=episodeVO[index].getDepartmentUnit();
			queueNo[index]=episodeVO[index].getQueNo();
		}
		_rSlip.setHostName(patientVO.getSystemIPAddress());
		_rSlip.setPatCrNo(episodeVO[0].getPatCrNo());
		_rSlip.setRoom(roomName);
		_rSlip.setDepartmentToBeVisited(depName);		
		_rSlip.setFileNo(fileNo);
		_rSlip.setDepartmentUnit(depUnit);		
		//_rSlip.setQueNo(queueNo);
		return _rSlip;	
	}
	
	public void genrateSlip(){
		//printRegistrationlabel(preapareSlip());
	}

	public static void patientOPDCard(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request)
	{
	
		StringBuffer fileContents	=	new StringBuffer();
		try{
			String clientOS=getClientSystemOsType(_request);
			String center="\t\t\t";	
			String right="\t\t\t\t";	
			String left="";	
			String align="";
			HelperMethods.setNullToEmpty(_rSlip);
			
			//if card is duplicate print Duplicate in Header
			for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
			{
				fileContents=new StringBuffer();
				if(_rSlip.getIsDuplicate().equals(RegistrationConfig.DUPLICATE_CARD))
					fileContents.append("" + center + "Duplicate\n");

				String episodeDate=WebUTIL.getCustomisedSysDate(WebUTIL.getDateFromString(_rSlip.getEpisodeDate()[index], ""), "dd-MM-yyyy ");
				
				
				///setting max length for print allignment
				String crNo=setMaxLength(_rSlip.getPatCrNo());
				String patientName=setMaxLength(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName()+" "+_rSlip.getPatLastName().toUpperCase());
				String patGaurdianName=setMaxLength(WebUTIL.convertStringToInitcap(_rSlip.getPatGuardianName()));
				String monthltIncome=setMaxLength(_rSlip.getPatMonthlyIncome());
				String departmentname=setMaxLength(_rSlip.getDepartmentToBeVisited()[index]);
				String roomName=setMaxLength(_rSlip.getRoom()[index]);
				String fileNumber=setMaxLength(_rSlip.getFileNoView()[index]);
				String amount=setMaxLength(_rSlip.getPatAmountCollected()+"/-");
				
				String ageGender=setMaxLength(_rSlip.getPatAge()+"/"+_rSlip.getPatGender().substring(0,1));
				String unitName=_rSlip.getDepartmentUnit()[index];
				String unitConsultant=_rSlip.getUnitConsultant()[index];
								
				if(unitConsultant==null)
					unitConsultant="";
				
				String unitDetails=unitName+"/"+unitConsultant;
				
				String printingMode="D"; //D : Dot Matrix, else Barcode
				

				if (printingMode.equals("D")) 
				{
						
					fileContents.append("\t\t\t\t\t\tCR No.     : "+crNo+"Date      :"+episodeDate.trim());
					fileContents.append("\n");
					fileContents.append("\n");
					fileContents.append("\t\t\t\t\t\tName       : "+patientName);
					fileContents.append("\n");
					fileContents.append("\t\t\t\t\t\tAge/Sex    : "+ageGender+"Category  : "+_rSlip.getPatPrimaryCat());
					fileContents.append("\n");
					if(_rSlip.getPatGender().substring(0,1).equals("F") && !_rSlip.getPatHusbandName().equals(""))
						fileContents.append("\t\t\t\t\t\tHusband Name: "+ _rSlip.getPatHusbandName());
					else
						fileContents.append("\t\t\t\t\t\tFather Name: "+patGaurdianName);
					
					fileContents.append("\n");
					fileContents.append("\t\t\t\t\t\tAddress    : "+_rSlip.getAddress1().trim()+"    ");
					fileContents.append("\n");
					fileContents.append("\t\t\t\t\t\t             "+_rSlip.getAddress2()+"    ");
					fileContents.append("\n");
						
					fileContents.append("\t\t\t\t\t\tDepartment : "+departmentname+"\n");
					fileContents.append("\t\t\t\t\t\tUnit/Conslt: "+unitDetails);
					fileContents.append("\n");
					fileContents.append("\t\t\t\t\t\tRoom No    : "+ (roomName==null?"":roomName)+(_rSlip.getQueNo()[index]==null?"":_rSlip.getQueNo()[index]));
					fileContents.append("\n");
					fileContents.append("\t\t\t\t\t\tAmount(Rs)      : "+amount);
						
					String[] workingDays=_rSlip.getWorkingDays()[index].split("#");
					fileContents.append("\t\t\t\t\t\tUnit Days  : ");
					
					for(int j=0;j<workingDays.length;j++)
					{
						if(j==0)
							fileContents.append(workingDays[j].trim());
						fileContents.append("\n");
					}
					
					fileContents.append("\n");
					for(int x=0;x<47;x++)
						fileContents.append("\n");
				}//end Printing mode If
				else	
				{
					//String barCodeDataString = "";
					fileContents=new StringBuffer();
					fileContents.append("REFERENCE 0,0" +"\n");
					fileContents.append("DIRECTION 0,0"	+"\n");
					fileContents.append("SPEED 3.0"		+"\n");
					fileContents.append("GAP 3 mm,0 mm"	+"\n");
					
					fileContents.append("SIZE 101.6 mm,50.8 mm"	+"\n");
					fileContents.append("SET PEEL OFF"		+"\n");
					fileContents.append("DENSITY 8"		+"\n");
					fileContents.append("OFFSET 0 mm"		+"\n");
					fileContents.append("SHIFT 0"		+"\n");
					fileContents.append("SET CUTTER OFF"		+"\n");
					fileContents.append("SET STRIPER ON"		+"\n");
					fileContents.append("CLS" +"\n");
						
					fileContents.append("BARCODE 787,391,\"128M\",58,0,180,3,6,\""+ crNo.trim() +"\"\n");
					fileContents.append("CODEPAGE 850"+ "\n");				
					fileContents.append("TEXT 787,300,\"3\",180,1,1, \""+ "CR No.: "+"\"\n") ;
					fileContents.append("TEXT 597,300,\"3\",180,1,1, \""+ crNo+ "\"\n");
					fileContents.append("TEXT 787,273,\"3\",180,1,1, \""+ "Name       : "+ "\"\n");
					fileContents.append("TEXT 597,273,\"3\",180,1,1, \""+ patientName+ "\"\n");
					fileContents.append("TEXT 370,273,\"3\",180,1,1, \""+ "Age/Sex     : "+ "\"\n");
					fileContents.append("TEXT 204,273,\"3\",180,1,1, \""+ _rSlip.getPatAge()+"/"+_rSlip.getPatGender()+ "\"\n");
					fileContents.append("TEXT 787,241,\"3\",180,1,1, \""+ "Father Name: "+"\"\n");
					fileContents.append("TEXT 597,241,\"3\",180,1,1, \""+ patGaurdianName +"\"\n");
					fileContents.append("TEXT 370,241,\"3\",180,1,1, \""+ "Category    : " +"\"\n");
					fileContents.append("TEXT 204,241,\"3\",180,1,1, \""+ _rSlip.getPatPrimaryCat() +"\"\n");
					fileContents.append("TEXT 787,212,\"3\",180,1,1, \""+ "Address    : "+"\"\n");
					fileContents.append("TEXT 597,212,\"3\",180,1,1, \""+ _rSlip.getAddress1().trim()+"\"\n");
					fileContents.append("TEXT 597,191,\"3\",180,1,1, \""+ _rSlip.getAddress2()+"\"\n");
					
					fileContents.append("TEXT 787,149,\"3\",180,1,1, \""+"Department : "+"\"\n");
					fileContents.append("TEXT 597,149,\"3\",180,1,1, \""+departmentname+"\"\n");
					fileContents.append("TEXT 370,149,\"3\",180,1,1, \""+"Unit/Conslt : "+"\"\n");
					fileContents.append("TEXT 204,149,\"3\",180,1,1, \""+_rSlip.getDepartmentUnit()[index]+"\"\n");
					
					fileContents.append("TEXT 787,121,\"3\",180,1,1, \""+ "Room No    : "+"\"\n");
					fileContents.append("TEXT 597,121,\"3\",180,1,1, \""+ (roomName==null?"":roomName)+"\"\n");
					fileContents.append("TEXT 370,121,\"3\",180,1,1, \""+ "Serial No   : "+"\"\n");
					fileContents.append("TEXT 204,121,\"3\",180,1,1, \""+(_rSlip.getQueNo()[index]==null?"":_rSlip.getQueNo()[index])+"\"\n");
				
					fileContents.append("TEXT 787,87,\"3\",180,1,1, \""+ "Amount(Rs)      : "+"\"\n");
					fileContents.append("TEXT 597,87,\"3\",180,1,1, \""+ amount+"\"\n");
						
					String[] workingDays=_rSlip.getWorkingDays()[index].split("#");
					
					for(int j=0;j<workingDays.length;j++)
					{
						if(j==0){
							fileContents.append("TEXT 787,52,\"3\",180,1,1, \""+ "Working Day: "+"\"\n");
							fileContents.append("TEXT 597,52,\"3\",180,1,1, \""+ workingDays[j].trim()+"\"\n");
						}	
						else{
							fileContents.append("TEXT 787,52,\"3\",180,1,1, \""+ "      "+"\"\n");
							fileContents.append("TEXT 597,52,\"3\",180,1,1, \""+ workingDays[j].trim()+"\"\n");
						}	
					}
					
					fileContents.append("PRINT 1,1" + "\n\n");
				}
					
				/////always two slips for PGI
				int numberOfSlip=Integer.parseInt(Config.NUMBER_OF_FILE_SLIP_TO_BE_GENERATED);
				for(int k=0;k<numberOfSlip;k++)
				{
					HisPrinterSupport.createTempFile(fileContents.toString(), tmpFileName);				
					HisPrinterSupport.printSlip(_rSlip.getHostName(),tmpFileName+".dat",clientOS);
				}
				
				
			}
			//System.out.println("createSlip() Exited");				
		}
		catch(Exception e){
			System.out.println("Exception in createSlip "+e);	
			e.printStackTrace();
		}	

	}
	
	
	public static String setMaxLength(String variable)
	{
		if(variable==null)
			variable="";
		int maxLength=17;
		int varLength=variable.length();
		if(varLength<maxLength)
		{
			for(int index=0;index<maxLength-varLength;index++)
			{
				variable=variable+" ";
			}
		}
		return variable;
	}
	
	public static String getClientSystemOsType(HttpServletRequest request) 
	{

        String strOsType = "";
        String strAgentDtls = "";
        Enumeration<?> names = request.getHeaderNames();

        while (names.hasMoreElements()) 
        {
		  String elem = (String) names.nextElement();
		  if (elem.contains("agent")) 
		  {
		            strAgentDtls = request.getHeader(elem);
		            break;
		  }
        }
        
        if (!strAgentDtls.equals("")) 
        {
      	  if (strAgentDtls.contains("windows") || strAgentDtls.contains("Windows")
      			  || strAgentDtls.contains("Win") || strAgentDtls.contains("win"))
      	  {
                    strOsType = "Windows";
      	  } 
      	  else if (strAgentDtls.contains("linux") || strAgentDtls.contains("Linux")) 
      	  {
      		  strOsType = "Linux";
          }
      	  else
      	  {
          	  strOsType = "Other";
          }
        }
        
        return strOsType;
  }
	
	//Added to Print Bill Receipt by Singaravelan on 22-May-14
	/*public static String printBillReceipt(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request,String _mode)
	{
	
		StringBuffer str	=	new StringBuffer();
		
		HisUtil hisutil=new HisUtil("Registration","New Registration");
		
		try{
			Date billDate=new Date();
			String clientOS=getClientSystemOsType(_request);
			HelperMethods.setNullToEmpty(_rSlip);
			String job="";
			
			for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
			{
										
				//String episodeDate=WebUTIL.getCustomisedSysDate(WebUTIL.getDateFromString(_rSlip.getEpisodeDate()[index], ""), "dd-MM-yyyy ");
				String episodeDate=_rSlip.getEpisodeDate()[index];
				String billPrintedTime=WebUTIL.getCustomisedSysDate(billDate,"dd-MMM-yyyy HH:mm");
				
				///setting max length for print allignment
				String crNo=setMaxLength(_rSlip.getPatCrNo());
				String patientName=setMaxLength(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName()+" "+_rSlip.getPatLastName().toUpperCase());
				String patGaurdianName=setMaxLength(WebUTIL.convertStringToInitcap(_rSlip.getPatGuardianName()));
				String monthltIncome=setMaxLength(_rSlip.getPatMonthlyIncome());
				String departmentname=setMaxLength(_rSlip.getDepartmentToBeVisited()[index]);
				String roomName=setMaxLength(_rSlip.getRoom()[index]);
				String fileNumber=setMaxLength(_rSlip.getFileNoView()[index]);
				String amount=setMaxLength(_rSlip.getPatAmountCollected()+"/-");
				String actualAmount=setMaxLength(_rSlip.getPatActualAmount()+"/-");
				String ageGender=setMaxLength(_rSlip.getPatAge()+"/"+_rSlip.getPatGender().substring(0,1));
				String unitName=_rSlip.getDepartmentUnit()[index];
				String unitConsultant=_rSlip.getUnitConsultant()[index];
				String patCategory=_rSlip.getPatPrimaryCat();				
				String Remarks=_rSlip.getRemarks();
				
				String billNo="";
				if(_rSlip.getBillNo()!=null)
				{
					if(!_mode.equalsIgnoreCase("RC"))
					billNo=_rSlip.getBillNo()[index]+"/1";
					else
						billNo=_rSlip.getBillNo()[index]+"/"+(Integer.parseInt(_rSlip.getReceiptNo()[index])+1)+"";
				}
				else
					billNo=crNo+"/1";
				if(_rSlip.getBillAmount()!=null)
					_rSlip.setPatAmountCollected(_rSlip.getBillAmount()[index]);
				
				String amountPaidByClientInWords="";
				String amountInWords="";
				if(!_rSlip.getPatAmountCollected().equals("")){
				if(_rSlip.getPatAmountCollected().contains("."))
					amountInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatAmountCollected(),2));
				  				//amountInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatAmountCollected().substring(0, _rSlip.getPatAmountCollected().indexOf("."))));
				else
					amountInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatAmountCollected(),2));
				}
				//amountInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatAmountCollected()));
				//Start:Sheeldarshi
				if(!_rSlip.getPatActualAmount().equals(""))
				{
				if(_rSlip.getPatActualAmount().contains("."))
					amountPaidByClientInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatActualAmount(),2));
					//amountPaidByClientInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatActualAmount().substring(0, _rSlip.getPatActualAmount().indexOf("."))));
                else
                	amountPaidByClientInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatActualAmount(),2));
                	//amountPaidByClientInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatActualAmount()));
				}
				//End
				
				if(_mode.equalsIgnoreCase("NR"))
					job="OPD REGISTRATION";
				else if(_mode.equalsIgnoreCase("DC"))
					job="DUPLICATE CARD PRINTING";
				else if(_mode.equalsIgnoreCase("ODV"))
					job="RENEWAL CHARGE";//job="OLD DEPARTMENT VISIT";					
				else if(_mode.equalsIgnoreCase("NDV"))
					job="NEW DEPARTMENT VISIT";
				else if(_mode.equalsIgnoreCase("RP"))	
					job="REGISTRATION/VISIT";
				else if(_mode.equalsIgnoreCase("SR"))	
					job="SPECIAL OPD REGISTRATION";
				else if(_mode.equalsIgnoreCase("EMG"))	
					job="EMERGENCY REGISTRATION";
				else if(_mode.equalsIgnoreCase("RC"))	
					job="REGISTRATION CANCELLATION";
								
				if(unitConsultant==null)
					unitConsultant="";
				
				String unitDetails=unitName+"/"+unitConsultant;				
				String printingMode="D"; //D : Dot Matrix, else Barcode
				
				
			if(((_rSlip.getPatActualAmount()!=null)&& _mode.equalsIgnoreCase("RC"))||(_rSlip.getPatActualAmount()!=null)&&!(_rSlip.getPatActualAmount().equalsIgnoreCase("0.00"))&&!(_rSlip.getPatActualAmount().equalsIgnoreCase("0")))
			{	
			
					//Start:Sheeldarshi:28thOct'14:Registration Cancellation
				if(!_mode.equalsIgnoreCase("ODV") && ! _mode.equalsIgnoreCase("RC")){
					//End :Sheeldarshi:28thOct'14:Registration Cancellation
					str.append("<pre><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br></pre>");
					if(_mode.contains("R")){
						str.append("<br><br><br><br>");
					}
					else if(_mode.contains("D")){
						str.append("<br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
					}
				}
				
				str.append("<div class='div-table-simple' style='width:90%'>");
	  			
	  			str.append("<div class='div-table-row' align='center' style='height: 50px;'>");	  			
	  				
	  			 if(_mode.equalsIgnoreCase("RC"))	
	  				str.append("<h2>REFUND RECEIPT</h2>");
	  			 else
	  				str.append("<h2>CASH RECEIPT</h2>");
	  			str.append("</div>");
			
	  			str.append("<div class='div-table-row' style='height: 10px;'>");
	  			str.append("<div class='div-table-col' style='width: 60%; height: 20px'></div>");
	  			str.append("<div class='div-table-col alignRight' style='width: 40%; height: 20px;'>");
	  			str.append("DATE & TIME :"+billPrintedTime);					
				str.append("</div></div>");
				str.append("</div>");

				str.append("<div class='div-table-simple' style='border: 1px solid black; width:99%'>");
				
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divBillNoLabel' style='width: 18%; height: 37px;'>BILL No.<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divBillNoLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col fontBold' style='width: 30%; height: 37px;'>");
				//str.append(_rSlip.getPatCrNo()+"/1");
				str.append(billNo);
				str.append("</div>");
						
				str.append("<div class='div-table-col' id='divCategoryLabel' style='width: 18%; height: 37px;'>CATEGORY<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divCategoryLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 30%; height: 37px;'>");
				//if(_mode.equalsIgnoreCase("RC"))
				//{
				str.append(_rSlip.getPatPrimaryCat().toUpperCase());
				str.append("(");
				str.append(_rSlip.getPatPrimaryCatGrp().toUpperCase());
				str.append(")");
				//}
				//else
				//{
					//str.append(_rSlip.getPatPrimaryCat().toUpperCase());
				//}
				str.append("</div></div>");
			   
				Code commented by Garima for Billing Module Format Synchronization
				
			str.append("<div class='div-table-col' id='divBillDateLabel' style='width: 18%; height: 37px;'>BILL DATE<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divBillDateLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 30%; height: 37px;'>");
				str.append(billPrintedTime);
				str.append("</div>");
				
				//if(_mode.equalsIgnoreCase("RC"))
				if(_rSlip.getPatPrimaryCatGrpCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) || _rSlip.getPatPrimaryCatGrpCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
				{
				    str.append("<div class='div-table-row' style='height: 37px;'>");
					str.append("<div class='div-table-col' id='divClientNameLabel' style='width: 18%; height: 37px;'>CLIENT<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divClientNameLabelx' style='width: 2%; height: 37px;'>:<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' style='width: 25%; height: 37px;'>");
					str.append(_rSlip.getclientName().toUpperCase());
					str.append("</div></div>");
				}
				else
				{
					 str.append("<div class='div-table-row' style='height: 37px;'>");
					str.append("<div class='div-table-col' id='divEmptyLabel' style='width: 25%; height: 37px;'><br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 25%; height: 37px;'>");
				str.append("");
				str.append("</div></div>");
				}
				
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 18%; height: 37px;'>CR NO.<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divCRNoLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col fontBold' style='width: 30%; height: 37px;'>");
				str.append(_rSlip.getPatCrNo());
				str.append("</div>");
						
				str.append("<div class='div-table-col' id='divEmptyLabel' style='width: 25%; height: 37px;'><br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 25%; height: 37px;'>");
				str.append("");
				str.append("</div></div>");
				
				
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divNameLabel' style='width: 18%; height: 37px;'>NAME<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divNameLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col fontBold' style='width: 30%; height: 37px;'>");
				str.append(patientName);
				str.append("</div>");
						
				str.append("<div class='div-table-col' id='divAgeSexLabel' style='width: 18%; height: 37px;'>AGE/SEX<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divAgeSexLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 30%; height: 37px;'>");
				str.append(ageGender);
				str.append("</div></div>");				
				
				
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divDeptLabel' style='width: 18%; height: 37px;'>DEPARTMENT<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divDeptLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 30%; height: 37px;'>");
				str.append(departmentname.toUpperCase());
				str.append("</div>");
						
				str.append("<div class='div-table-col' id='divUnitLabel' style='width: 18%; height: 37px;'>UNIT<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divUnitLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 30%; height: 37px;'>");
				str.append(unitName.toUpperCase());
				str.append("</div></div>");				
				
				str.append("<div class='div-table-simple'>");
	  			str.append("<div class='div-table-row' style='width: 100%; height: 10px;' >");
	  			//Start :Sheeldarshi:28thOct'14:Registration Cancellation
	  			//if(_mode.equalsIgnoreCase("RC"))
	  			if(_rSlip.getPatPrimaryCatGrpCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) || _rSlip.getPatPrimaryCatGrpCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
	  				{
	  				str.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					str.append("</div>");
					
					str.append("<div class='div-table-row' style='height: 20px;'>");
					str.append("<div class='div-table-col' id='divDescLabel' style='width: 30%; height: 37px;'>DESCRIPTION<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divRateLabel' style='width: 15%; height: 37px;'>RATE(Rs.)<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divQtyLabel' style='width: 15%; height: 37px;'>QTY.<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divAmountLabel' style='width: 15%; height: 37px;'>AMOUNT(Rs.)<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divPatientShareLabel' style='width: 15%; height: 37px;'>PATIENT SHARE<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divClientShareLabel' style='width: 10%; height: 37px;'>CLIENT SHARE<br/>");
					str.append("</div></div>");
		  			str.append("<div class='div-table-row' style='width: 100%; height: 10px;' >");
		  			str.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					str.append("</div>");
					
					str.append("<div class='div-table-row' style='height: 37px;'>");
					str.append("<div class='div-table-col' id='divDesc' style='width: 30%; height: 37px;'>");
					str.append(job);
					str.append("</div>");
					str.append("<div class='div-table-col' id='divRate' style='width: 15%; height: 37px;'>");
					str.append(_rSlip.getPatActualAmount());
					str.append("</div>");
					str.append("<div class='div-table-col' id='divQty' style='width: 15%; height: 37px;'>");
					str.append("1");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divAmount' style='width: 15%; height: 37px;'>");
					str.append(_rSlip.getPatActualAmount());
					str.append("</div>");
					str.append("<div class='div-table-col' id='divPatAmount' style='width: 15%; height: 37px;'>");
					str.append(_rSlip.getPatAmountCollected());
					str.append("</div>");
					str.append("<div class='div-table-col' id='divClientAmount' style='width: 10%; height: 37px;'>");
					str.append(_rSlip.getPatActualAmount());
					str.append("</div>");			
					str.append("</div></div>");
						
					str.append("<div class='div-table-simple'>");
					str.append("<div class='div-table-row' style='height: 10px;'>");
					str.append("<div class='div-table-col' id='divDesc1' style='width: 30%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divRate1' style='width: 15%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divQty1' style='width: 15%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divAmount1' style='width: 15%; height: 10px;'>");
					str.append("----------------------------------------------------------------------------------------------");
					str.append("</div>");	
					str.append("<div class='div-table-col' id='divEmptyLabel' style='width: 15%; height: 10px;'><br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' style='width: 10%; height: 10px;'>");
					str.append("");
					str.append("</div></div>");	
					str.append("<div class='div-table-row' style='height: 20px;'>");
					str.append("<div class='div-table-col' id='divDesc2' style='width: 30%; height: 20px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divRate2' style='width: 15%; height: 20px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col alignLeft' id='divDescLabel' style='width: 15%; height: 20px;'>TOTAL<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col ' id='divRateLabel' style='width: 15%; height: 20px;'>");
					str.append(actualAmount);
					str.append("</div>");
					str.append("<div class='div-table-col ' id='divPatRateLabel' style='width: 15%; height: 20px;'>");
					str.append(amount);
					str.append("</div>");
					str.append("<div class='div-table-col ' id='divClientRateLabel' style='width: 10%; height: 20px;'>");
					str.append(actualAmount);
					str.append("</div></div>");
					
					str.append("<div class='div-table-row' style='height: 10px;'>");
					str.append("<div class='div-table-col' id='divDesc1' style='width: 30%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divRate1' style='width: 15%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divQty1' style='width: 15%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divAmount1' style='width: 15%; height: 10px;'>");
					str.append("----------------------------------------------------------------------------------------------");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divEmptyLabel' style='width: 15%; height: 10px;'><br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' style='width: 10%; height: 10px;'>");
					str.append("");
					str.append("</div></div>");
					str.append("</div>");				
					
					str.append("<div class='div-table-simple'>");
					str.append("<div class='div-table-row' style='height: 37px;'>");
					str.append("<div class='div-table-col' id='divRupeeDesc1' style='width: 28%; height: 37px;'>AMOUNT PAID BY PATIENT (IN WORD)<br/>");				
					str.append("</div>");
					str.append("<div class='div-table-col' id='divUnitLabelx' style='width: 2%; height: 37px;'>:<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col alignLeft' id='divRupeeDesc1' style='width: 70%; height: 37px;'>");
					str.append(amountInWords.toUpperCase());
					str.append("</div></div>");
					str.append("<div class='div-table-row' style='height: 37px;'>");
					str.append("<div class='div-table-col' id='divRupeeDesc1' style='width: 28%; height: 37px;'>AMOUNT PAID BY CLIENT (IN WORD)<br/>");				
					str.append("</div>");
					str.append("<div class='div-table-col' id='divUnitLabelx' style='width: 2%; height: 37px;'>:<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col alignLeft' id='divRupeeDesc1' style='width: 70%; height: 37px;'>");
					str.append(amountPaidByClientInWords.toUpperCase());
					str.append("</div></div>");
					str.append("<div class='div-table-row' style='height: 37px;'>");
					str.append("<div class='div-table-col' id='divNote' style='width: 70%; height: 37px;'>Note: Amount, Patient Share, and Client Share are in Rs.<br/>");
				}
				else
				{
					str.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					str.append("</div>");
					
					str.append("<div class='div-table-row' style='height: 20px;'>");
					str.append("<div class='div-table-col' id='divDescLabel' style='width: 30%; height: 37px;'>DESCRIPTION<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divRateLabel' style='width: 20%; height: 37px;'>RATE(Rs.)<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divQtyLabel' style='width: 25%; height: 37px;'>QTY.<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divAmountLabel' style='width: 25%; height: 37px;'>AMOUNT(Rs.)<br/>");
					str.append("</div></div>");
					
		  			str.append("<div class='div-table-row' style='width: 100%; height: 10px;' >");
		  			str.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					str.append("</div>");
					
					str.append("<div class='div-table-row' style='height: 37px;'>");
					str.append("<div class='div-table-col' id='divDesc' style='width: 30%; height: 37px;'>");
					str.append(job);
					str.append("</div>");
					str.append("<div class='div-table-col' id='divRate' style='width: 20%; height: 37px;'>");
					str.append(_rSlip.getPatAmountCollected());
					str.append("</div>");
					str.append("<div class='div-table-col' id='divQty' style='width: 25%; height: 37px;'>");
					if(_mode.equalsIgnoreCase("RC"))
						str.append("-1");
					else
						str.append("1");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divAmount' style='width: 25%; height: 37px;'>");
					
					if(_mode.equalsIgnoreCase("RC"))
						str.append("-"+_rSlip.getPatAmountCollected());
					else
						str.append(_rSlip.getPatAmountCollected());
					str.append("</div>");			
					str.append("</div></div>");
						
					str.append("<div class='div-table-simple'>");
					str.append("<div class='div-table-row' style='height: 10px;'>");
					str.append("<div class='div-table-col' id='divDesc1' style='width: 30%; height: 10px;'>");
					str.append("");
					str.append("</div>");
				
					str.append("<div class='div-table-col' id='divRate1' style='width: 20%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divQty1' style='width: 25%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divAmount1' style='width: 25%; height: 10px;'>");
					str.append("----------------------------");
					str.append("</div></div>");	
					str.append("<div class='div-table-row' style='height: 20px;'>");
					str.append("<div class='div-table-col' id='divDesc2' style='width: 30%; height: 20px;'>");
					str.append("");
					str.append("</div>");					
					str.append("<div class='div-table-col' id='divRate2' style='width: 20%; height: 20px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col alignLeft' id='divDescLabel' style='width: 25%; height: 20px;'>TOTAL<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col ' id='divRateLabel' style='width: 25%; height: 20px;'>");
					if(_mode.equalsIgnoreCase("RC"))
						str.append("-"+amount);
					else
						str.append(amount);
					str.append("</div></div>");
					
					str.append("<div class='div-table-row' style='height: 10px;'>");
					str.append("<div class='div-table-col' id='divDesc1' style='width: 30%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divRate1' style='width: 20%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divQty1' style='width: 25%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divAmount1' style='width: 25%; height: 10px;'>");
					str.append("----------------------------");
					str.append("</div></div>");
					str.append("</div>");				
					
					str.append("<div class='div-table-simple'>");
					str.append("<div class='div-table-row' style='height: 37px;'>");
					str.append("<div class='div-table-col' id='divRupeeDesc1' style='width: 18%; height: 37px;'>RUPEES (IN WORD)<br/>");				
					str.append("</div>");
					str.append("<div class='div-table-col' id='divUnitLabelx' style='width: 2%; height: 37px;'>:<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col alignLeft' id='divRupeeDesc1' style='width: 70%; height: 37px;'>");
					str.append(amountInWords.toUpperCase());
					str.append("</div>");
					str.append("</div>");
					
					if(_mode.equalsIgnoreCase("RC"))
						
					
					{
					
					str.append("<div class='div-table-row' style='height: 37px;'>");
					str.append("<div class='div-table-col' id='divRemarksLabel' style='width: 18%; height: 37px;'>REASON<br/>");				
					str.append("</div>");
					str.append("<div class='div-table-col' id='divRemarksLabelx' style='width: 2%; height: 37px;'>:<br/>");
					str.append("</div>");
					str.append("<div class='div-table-col alignLeft' id='divRemarksLabel' style='width: 70%; height: 37px;'>");
					str.append(_rSlip.getRemarks());
					str.append("</div>");
					}
					
					else
						
					str.append("");
					str.append("</div>");
					
					//End :Sheeldarshi:28thOct'14:Registration Cancellation
					
					
					//str.append("</div></div>");	
					str.append("<div class='div-table-row' style='height: 37px;'>");
					str.append("<div class='div-table-col' id='divCountDesc1' style='width: 30%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divCountDesc2' style='width: 20%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append("<div class='div-table-col' id='divCountDesc3' style='width: 25%; height: 10px;'>");
					str.append("");
					str.append("</div>");
					str.append((_rSlip.getCounterName()==""||_rSlip.getCounterName()==null)?"( DEFAULT )":"( "+_rSlip.getCounterName().toUpperCase()+" )");
					str.append("</div></div>");	
					str.append("</div>");
					
					str.append("</div>");

				}
			}
							
		}
		}
		catch(Exception e){
			System.out.println("Exception in createSlip "+e);	
			e.printStackTrace();
		}
		System.out.println("Bill Receipt Html String::::::::::::::::::::::::::::::::::::::::::\n"+str.toString());
		return str.toString();	

	}	*/
	public static String printBillReceipt(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request,String _mode)
	{
	
		StringBuffer str	=	new StringBuffer();
		
		HisUtil hisutil=new HisUtil("Registration","New Registration");
		
		try{
			Date billDate=new Date();
			String clientOS=getClientSystemOsType(_request);
			HelperMethods.setNullToEmpty(_rSlip);
			String job="";
			
			for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
			{
										
				//String episodeDate=WebUTIL.getCustomisedSysDate(WebUTIL.getDateFromString(_rSlip.getEpisodeDate()[index], ""), "dd-MM-yyyy ");
				String episodeDate = _rSlip.getEpisodeDate()[index];
				String billPrintedTime = _rSlip.getEpisodeDate()[index];//WebUTIL.getCustomisedSysDate(billDate,"dd-MMM-yyyy HH:mm");
				SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
				 SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			    SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
			    SimpleDateFormat _sdf4 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
				///setting max length for print allignment
				String crNo=setMaxLength(_rSlip.getPatCrNo());
				String patientName=setMaxLength(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName().toUpperCase()+" "+_rSlip.getPatLastName().toUpperCase());
				String patGaurdianName=setMaxLength(WebUTIL.convertStringToInitcap(_rSlip.getPatGuardianName()));
				String monthltIncome=setMaxLength(_rSlip.getPatMonthlyIncome());
				String departmentname=setMaxLength(_rSlip.getDepartmentToBeVisited()[index]+'-'+_rSlip.getDepartmentUnit()[index]);
				String roomName=setMaxLength(_rSlip.getRoom()[index]);
				String fileNumber=setMaxLength(_rSlip.getFileNoView()[index]);
				String amount=setMaxLength(_rSlip.getPatAmountCollected()+"/-");
				String actualAmount=setMaxLength(_rSlip.getPatActualAmount()+"/-");
				String ageGender=setMaxLength(_rSlip.getPatAge()+"/"+_rSlip.getPatGender().substring(0,1));
				String unitName=_rSlip.getDepartmentUnit()[index];
				String unitConsultant=_rSlip.getUnitConsultant()[index];
				String patCategory=_rSlip.getPatPrimaryCat();				
				String Remarks=_rSlip.getRemarks();
				
				String billNo="";
				if(_rSlip.getBillNo()!=null)
				{
					if(!_mode.equalsIgnoreCase("RC"))
					billNo=_rSlip.getBillNo()[index]+"/1";
					else
						billNo=_rSlip.getBillNo()[index]+"/"+(Integer.parseInt(_rSlip.getReceiptNo()[index])+1)+"";
				}
				else
					billNo=crNo+"/1";
				if(_rSlip.getBillAmount()!=null)
					_rSlip.setPatAmountCollected(_rSlip.getBillAmount()[index]);
				
				String amountPaidByClientInWords="";
				String amountInWords="";
				if(!_rSlip.getPatAmountCollected().equals("")){
				if(_rSlip.getPatAmountCollected().contains("."))
					amountInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatAmountCollected(),2));
				  				//amountInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatAmountCollected().substring(0, _rSlip.getPatAmountCollected().indexOf("."))));
				else
					amountInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatAmountCollected(),2));
				}
				//amountInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatAmountCollected()));
				//Start:Sheeldarshi
				if(!_rSlip.getPatActualAmount().equals(""))
				{
				if(_rSlip.getPatActualAmount().contains("."))
					amountPaidByClientInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatActualAmount(),2));
					//amountPaidByClientInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatActualAmount().substring(0, _rSlip.getPatActualAmount().indexOf("."))));
                else
                	amountPaidByClientInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatActualAmount(),2));
                	//amountPaidByClientInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatActualAmount()));
				}
				//End
				
				if(_mode.equalsIgnoreCase("NR"))
					job="OPD REGISTRATION";
				else if(_mode.equalsIgnoreCase("DC"))
					job="DUPLICATE CARD PRINTING";
				else if(_mode.equalsIgnoreCase("ODV"))
					job="RENEWAL CHARGE";//job="OLD DEPARTMENT VISIT";					
				else if(_mode.equalsIgnoreCase("NDV"))
					job="NEW DEPARTMENT VISIT";
				else if(_mode.equalsIgnoreCase("RP"))	
					job="REGISTRATION/VISIT";
				else if(_mode.equalsIgnoreCase("SR"))	
					job="SPECIAL OPD REGISTRATION";
				else if(_mode.equalsIgnoreCase("EMG"))	
					job="EMERGENCY REGISTRATION";
				else if(_mode.equalsIgnoreCase("RC"))	
					job="REGISTRATION CANCELLATION";
								
				if(unitConsultant==null)
					unitConsultant="";
				
				String unitDetails=unitName+"/"+unitConsultant;				
				String printingMode="D"; //D : Dot Matrix, else Barcode
				
				
			if(((_rSlip.getPatActualAmount()!=null)&& _mode.equalsIgnoreCase("RC"))||(_rSlip.getPatActualAmount()!=null)&&!(_rSlip.getPatActualAmount().equalsIgnoreCase("0.00"))&&!(_rSlip.getPatActualAmount().equalsIgnoreCase("0")))
			{	
			
					//Start:Sheeldarshi:28thOct'14:Registration Cancellation
				if(!_mode.equalsIgnoreCase("ODV") && ! _mode.equalsIgnoreCase("RC")){
					//End :Sheeldarshi:28thOct'14:Registration Cancellation
					str.append("<pre><br><br><br><br><br><br><br><br></pre>");
					if(_mode.contains("R")){
						str.append("<br><br><br><br>");
					}
					else if(_mode.contains("D")){
						str.append("<br><br><br><br><br><br><br>");
					}
				}
				
				str.append("<div class='div-table-simple' style='width:95%'>");
				
				
				/*****************************************************************************************************************************************************************/
	  			/****************************************************************On 07.04.2017 By Mukund****************************************************************************/
				if(RegistrationConfig.IS_HEADER_PRINTABLE.equals(RegistrationConfig.IS_HEADER_PRINTABLE_ON)){
						str.append("<div class='div-table-row' style='height: 100px;'>");
			  			//str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/NIMSLOGO.jpg'/></div>");
			  			str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/nims-tr-logo.gif'/></div>");
			  			str.append("<div class='div-table-col' id='divOPDCardHeader' align='center' style='width: 60%'>");
								
			  		  //str.append("<h3>"+_rSlip.getHospitalName()+"</h3>");
			  		  str.append("<br><font style='font-size: 16px; font-weight:bold;'>"+_rSlip.getHospitalName().toUpperCase()+"</font><br>");
			  		  //str.append("<h4>"+_rSlip.getHospitalAddress1());
			  		  if(_rSlip.getHospitalAddress1()!=null && !_rSlip.getHospitalAddress1().trim().equals(""))
				      	str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+"</font><br>");
				  	  if(_rSlip.getHospitalAddress2()!=null && !_rSlip.getHospitalAddress2().trim().equals(""))
			  			 //str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress2()+"</font><br>");
			  		  if(_rSlip.getHospitalCity()!=null && !_rSlip.getHospitalCity().trim().equals(""))
			  			  str.append( _rSlip.getHospitalCity());
			  		  if(_rSlip.getHospitalDistrict()!=null && !_rSlip.getHospitalDistrict().trim().equals(""))
			 			  str.append(  ", "+_rSlip.getHospitalDistrict());
			  		  if(_rSlip.getHospitalpinCode()!=null && !_rSlip.getHospitalpinCode().trim().equals("") && !_rSlip.getHospitalpinCode().trim().equals("0"))
			  			  str.append( " -"+_rSlip.getHospitalpinCode());  
			  		  if(_rSlip.getHospitalState()!=null && !_rSlip.getHospitalState().trim().equals(""))
						  str.append(  ", "+_rSlip.getHospitalState());
			  		 	str.append(" (INDIA)");  
			  		// str.append("</font><br>");
			  		 str.append("<br>");
			  		 //str.append("<h5>");
			  		 str.append("<font style='font-size: 14px;'>");
			  			if(_rSlip.getHospitalPhone()!=null && !_rSlip.getHospitalPhone().trim().equals(""))
			  			{
			  				str.append(RegistrationConfig.OPD_CARD_HEADER_LINE_4_PHONE_LABEL); 
			  				str.append(_rSlip.getHospitalPhone());
			  			}
			  			if(_rSlip.getHospitalFax()!=null && !_rSlip.getHospitalFax().trim().equals("")){
			  				str.append(", " + RegistrationConfig.OPD_CARD_HEADER_LINE_4_FAX_LABEL);
			  				str.append(_rSlip.getHospitalFax());
			  			}
			  			/*if(_rSlip.getHospitalEmail()!=null && !_rSlip.getHospitalEmail().trim().equals("")){
			  				str.append(", " +RegistrationConfig.OPD_CARD_HEADER_LINE_4_EMAIL_LABEL);
			  	  			str.append(_rSlip.getHospitalEmail());
			  			}*/
			  			
			  			str.append("</font><br></div>");
			  			str.append("</div>");
			  			str.append("<br>"); 
	  			}
	  			/*****************************************************************************************************************************************************************/
	  			/****************************************************************End 07.04.2017 Mukund****************************************************************************/
	  		
	  			
	  			//str.append("<div class='div-table-row' align='center' style='height: 50px;'>");
				str.append("<div style='height: 50px;text-align:center;'>");
	  				
	  			 if(_mode.equalsIgnoreCase("RC"))	
	  				str.append("<h2>REFUND RECEIPT</h2>");
	  			 else
	  				str.append("<h2>CONSULTATION RECEIPT</h2>");
	  			str.append("</div>");
			
	  			
	  			//str.append("<div style='border: 1px solid black;'>");
				str.append("<div class='div-table-simple' style='border: 1px solid black; width:99%'>");
				
				//Start row 1
				str.append("<div class='div-table-row' style='height: 37px;'>");
				//str.append("<div  style='border: 1px solid #000;'>");
				str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 60%; height: 50px;border: 1px solid black;'>CR No. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append(_rSlip.getPatCrNo());
				str.append("<div  id='divBarCodeControlForBill' style='padding-right: 30%;'>"+_rSlip.getPatCrNo()+"</div>");
				str.append("</div>");
		
				str.append("<div class='div-table-col' style='width: 40%; height: 50px;border: 1px solid black;padding-top:4px'>");
	  			//str.append("DATE & TIME :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+billPrintedTime);
				str.append("Date & Time :&nbsp;"+billPrintedTime);
				str.append("</div></div>");
				//str.append("</div>");
				
				//End row 1
				//Start row 2
				str.append("<div class='div-table-row' style='height: 37px;'>");
				//str.append("<div  style='border: 1px solid #000;'>");
				str.append("<div class='div-table-col' id='divNameLabel' style='width: 60%; height: 37px;border: 1px solid black;'>Patient Name &nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				//str.append("</div>");
				//str.append("<div class='div-table-col fontBold'  style='width: 30%; height: 37px;'>");
				str.append(patientName);
				//str.append("</div>");
						
				//str.append("<div  class='div-table-col' id='divAgeSexLabel' style='width: 25%; height: 37px;'>AGE/SEX :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				//str.append("</div>");
				//str.append("<div class='div-table-col' style='width: 10%; height: 37px;'>");
				//str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;AGE/SEX :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Age/Sex :&nbsp;");
				str.append(ageGender);
				
				//str.append("</div>");
				str.append("</div>");	
			//	str.append("<div class='div-table-col' id='divDeptLabel' style='width: 25%; height: 37px;border: 1px solid black;'>DEPARTMENT :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append("<div class='div-table-col' id='divDeptLabel' style='width: 40%; height: 37px;border: 1px solid black;'>Department :");
				//str.append("</div>");
				//str.append("<div class='div-table-col' style='width: 14%; height: 37px;'>");
				//str.append(departmentname.toUpperCase());
				str.append(departmentname);
				str.append("</div>");
				str.append("</div>");
				//End row 2
				//Start row 3
				str.append("<div class='div-table-row' style='height: 37px;'>");
				//str.append("<div  style='border: 1px solid #000;'>");
				str.append("<div class='div-table-col' id='divCategoryLabel' style='width: 60%; height: 37px;border: 1px solid black;'>Category &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				//str.append("<div class='div-table-col' id='divCategoryLabel' style='width: 60%; height: 37px;border: 1px solid black;'>CATEGORY :");
				//str.append("</div>");
				//str.append("<div class='div-table-col' style='width: 55%; height: 37px;'>");
				str.append(_rSlip.getPatPrimaryCat());
				/*str.append("(");
				str.append(_rSlip.getPatPrimaryCatGrp().toUpperCase());
				str.append(")");*/
				//str.append("</div>");
				str.append("</div>");
				//str.append("<div class='div-table-col' id='divMLCLabel' style='width: 25%; height: 37px;border: 1px solid black;'>MLC No. &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				str.append("<div class='div-table-col' id='divMLCLabel' style='width: 40%; height: 37px;border: 1px solid black;'>MLC No. :");
				//str.append("</div>");
				//str.append("<div class='div-table-col' style='width: 14%; height: 37px;'>");
				str.append("</div>");
				str.append("</div>");
				str.append("</div>");
				//End row 3
				
				//Start New block Row11
				str.append("</br>");
				str.append("</br>");
				str.append("<div class='div-table-simple' style='border: 2px solid black; width:99%'>");
				str.append("<u>");
				str.append("<div class='div-table-row fontBold' style='height: 37px;'>Payment Details");
				str.append("</u>");
				str.append("</div>");
				//row end 11
				
				//row start 12
				
				str.append("<div class='div-table-row' style='height: 50px;'>");
				/* Start : Surabhi
				 * Reason : resolved the bug related to the alignment
				 * */
				str.append("<div class='div-table-col' style='width: 27%; height: 30px;'>Amount Received Towards Consultation Charges :");
				str.append("</div>");
				str.append("<div class='div-table-col'style='width: 73%; height: 30px;'>");
				str.append(_rSlip.getPatAmountCollected());
				str.append("&nbsp;");
				str.append("(");
				//End
				str.append(amountInWords);
				str.append(")");
				str.append("</div>");
				str.append("</div>");
				//row end 12
				
				
				//row start 13				
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' style='width: 25%; height: 37px;'>Mode of Payment :");
				str.append("</div>");
				str.append("<div class='div-table-col'style='width: 75%; height: 37px;'>");
				str.append("Cash");
				str.append("</div>");
				str.append("</div>");
				//row end 13
				
				//row start 14				
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' style='width: 25%; height: 37px;'>Credit/ Debit card/ Cheque Details :");
				str.append("</div>");
				str.append("<div class='div-table-col'style='width: 75%; height: 37px;'>");
				
				str.append("</div>");
				str.append("</div>");
				//row end 14
				
				//row start 15			
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' style='width: 25%; height: 37px;'>Bill Number :");
				str.append("</div>");
				str.append("<div class='div-table-col'style='width: 75%; height: 37px;'>");
				//By Mukdun on 15.05.2017
				str.append(billNo);
				/*str.append(_rSlip.getPatCrNo()+"/1");*/
				//End 15.05.2017
				str.append("</div>");
				str.append("</div>");
				//row end 15
				
				//row start 16			
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' style='width: 25%; height: 37px;'>Payment Valid Upto :");
				str.append("</div>");
				str.append("<div class='div-table-col'style='width: 75%; height: 37px;'>");
				//str.append(_rSlip.getExpDate()[index]);
				 if(_mode.equalsIgnoreCase("EMG"))	
				{
				str.append(_rSlip.getExpDate()[index]);
				}
				/*else
				{
					str.append(_sdf3.format(_sdf2.parse((String)_rSlip.getExpDate()[index])));
				}*/
				 else
					{
						if(_rSlip.getExpDate()[index]!=null&&_rSlip.getExpDate()[index]!=""&&_rSlip.getExpDate()[index].indexOf("-")>0){
							if(_rSlip.getExpDate()[index].indexOf(" ")>0)
								str.append(_sdf3.format(_sdf2.parse((String)_rSlip.getExpDate()[index])));
							else		
								str.append(_rSlip.getExpDate()[index]);

						}
							
						if(_rSlip.getExpDate()[index]!=null&&_rSlip.getExpDate()[index]!=""&&_rSlip.getExpDate()[index].indexOf("/")>0)
							str.append(_sdf4.format(_sdf1.parse((String)_rSlip.getExpDate()[index])));
					}
				str.append("</div>");
				str.append("</div>");
				//row end 16
				
				str.append("</div>");
				str.append("</div>");
				str.append("</br>");
				str.append("</br>");
				str.append("</div>");
				//By Mukund on 15.05.2017
				str.append("<b>");
				str.append("<div  style='width:90%;height: 37px;text-align: right;'>"+_rSlip.getLoginUserName());
				str.append("</b>");
				str.append("</div>");
				//End 15.05.2017
				str.append("<b>");
				str.append("<div  style='width:90%;height: 37px;text-align: right;'>Authorised Signatory");
				str.append("</b>");
				str.append("</div>");
					

				}
			}
							
		
		}
		catch(Exception e){
			System.out.println("Exception in createSlip "+e);	
			e.printStackTrace();
		}
		System.out.println("Bill Receipt Html String::::::::::::::::::::::::::::::::::::::::::\n"+str.toString());
		return str.toString();	
		//return escapeHtml3(str.toString()).replaceAll("'", "&#039;");

	}	

public static String printBillReceiptForCancellation(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request,String _mode)
{

	StringBuffer str	=	new StringBuffer();
	
	HisUtil hisutil=new HisUtil("Registration","New Registration");
	
	try{
		Date billDate=new Date();
		String clientOS=getClientSystemOsType(_request);
		HelperMethods.setNullToEmpty(_rSlip);
		String job="";
		
		for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
		{
									
			//String episodeDate=WebUTIL.getCustomisedSysDate(WebUTIL.getDateFromString(_rSlip.getEpisodeDate()[index], ""), "dd-MM-yyyy ");
			String episodeDate=_rSlip.getEpisodeDate()[index];
			String billPrintedTime = _rSlip.getEpisodeDate()[index];//WebUTIL.getCustomisedSysDate(billDate,"dd-MMM-yyyy HH:mm");
            
			///setting max length for print allignment
			String crNo=setMaxLength(_rSlip.getPatCrNo());
			String patientName=setMaxLength(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName()+" "+_rSlip.getPatLastName().toUpperCase());
			String patGaurdianName=setMaxLength(WebUTIL.convertStringToInitcap(_rSlip.getPatGuardianName()));
			String monthltIncome=setMaxLength(_rSlip.getPatMonthlyIncome());
			String departmentname=setMaxLength(_rSlip.getDepartmentToBeVisited()[index]);
			String roomName=setMaxLength(_rSlip.getRoom()[index]);
			String fileNumber=setMaxLength(_rSlip.getFileNoView()[index]);
			String amount=setMaxLength(_rSlip.getPatAmountCollected()+"/-");
			String actualAmount=setMaxLength(_rSlip.getPatActualAmount()+"/-");
			String ageGender=setMaxLength(_rSlip.getPatAge()+"/"+_rSlip.getPatGender().substring(0,1));
			String unitName=_rSlip.getDepartmentUnit()[index];
			String unitConsultant=_rSlip.getUnitConsultant()[index];
			String patCategory=_rSlip.getPatPrimaryCat();				
			String Remarks=_rSlip.getRemarks();
			
			String billNo="";
			if(_rSlip.getBillNo()!=null)
			{
				if(!_mode.equalsIgnoreCase("RC"))
				billNo=_rSlip.getBillNo()[index]+"/1";
				else
					billNo=_rSlip.getBillNo()[index]+"/"+(Integer.parseInt(_rSlip.getReceiptNo()[index])+1)+"";
			}
			else
				billNo=crNo+"/1";
			if(_rSlip.getBillAmount()!=null)
				_rSlip.setPatAmountCollected(_rSlip.getBillAmount()[index]);
			
			String amountPaidByClientInWords="";
			String amountInWords="";
			if(!_rSlip.getPatAmountCollected().equals("")){
			if(_rSlip.getPatAmountCollected().contains("."))
				amountInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatAmountCollected(),2));
			  				//amountInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatAmountCollected().substring(0, _rSlip.getPatAmountCollected().indexOf("."))));
			else
				amountInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatAmountCollected(),2));
			}
			//amountInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatAmountCollected()));
			//Start:Sheeldarshi
			if(!_rSlip.getPatActualAmount().equals(""))
			{
			if(_rSlip.getPatActualAmount().contains("."))
				amountPaidByClientInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatActualAmount(),2));
				//amountPaidByClientInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatActualAmount().substring(0, _rSlip.getPatActualAmount().indexOf("."))));
            else
            	amountPaidByClientInWords=hisutil.getAmountStr(HisUtil.getAmountWithDecimal(_rSlip.getPatActualAmount(),2));
            	//amountPaidByClientInWords=HisUtil.ConvertNumberToText(Integer.parseInt(_rSlip.getPatActualAmount()));
			}
			//End
			
			if(_mode.equalsIgnoreCase("NR"))
				job="OPD REGISTRATION";
			else if(_mode.equalsIgnoreCase("DC"))
				job="DUPLICATE CARD PRINTING";
			else if(_mode.equalsIgnoreCase("ODV"))
				job="RENEWAL CHARGE";//job="OLD DEPARTMENT VISIT";					
			else if(_mode.equalsIgnoreCase("NDV"))
				job="NEW DEPARTMENT VISIT";
			else if(_mode.equalsIgnoreCase("RP"))	
				job="REGISTRATION/VISIT";
			else if(_mode.equalsIgnoreCase("SR"))	
				job="SPECIAL OPD REGISTRATION";
			else if(_mode.equalsIgnoreCase("EMG"))	
				job="EMERGENCY REGISTRATION";
			else if(_mode.equalsIgnoreCase("RC"))	
				job="REGISTRATION CANCELLATION";
							
			if(unitConsultant==null)
				unitConsultant="";
			
			String unitDetails=unitName+"/"+unitConsultant;				
			String printingMode="D"; //D : Dot Matrix, else Barcode
			
			
		if(((_rSlip.getPatActualAmount()!=null)&& _mode.equalsIgnoreCase("RC"))||(_rSlip.getPatActualAmount()!=null)&&!(_rSlip.getPatActualAmount().equalsIgnoreCase("0.00"))&&!(_rSlip.getPatActualAmount().equalsIgnoreCase("0")))
		{	
		
				//Start:Sheeldarshi:28thOct'14:Registration Cancellation
			if(!_mode.equalsIgnoreCase("ODV") && ! _mode.equalsIgnoreCase("RC")){
				//End :Sheeldarshi:28thOct'14:Registration Cancellation
				str.append("<pre><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br></pre>");
				if(_mode.contains("R")){
					str.append("<br><br><br><br>");
				}
				else if(_mode.contains("D")){
					str.append("<br><br><br><br><br><br><br><br><br><br><br><br><br><br>");
				}
			}
			
			str.append("<div class='div-table-simple' style='width:95%'>");
			str.append("<div class='div-table-row' style='height: 100px;'>");
			
			/*****************************************************************************************************************************************************************/
  			/****************************************************************On 07.04.2017 By Mukund****************************************************************************/
			if(RegistrationConfig.IS_HEADER_PRINTABLE.equals(RegistrationConfig.IS_HEADER_PRINTABLE_ON)){
					
		  			//str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/NIMSLOGO.jpg'/></div>");
		  			str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/nims-tr-logo.gif'/></div>");
		  			str.append("<div class='div-table-col' id='divOPDCardHeader' align='center' style='width: 60%'>");
							
		  		  //str.append("<h3>"+_rSlip.getHospitalName()+"</h3>");
		  		  str.append("<br><font style='font-size: 16px; font-weight:bold;'>"+_rSlip.getHospitalName().toUpperCase()+"</font><br>");
		  		  str.append("<h2>&#2309;&#2326;&#2367;&#2354; &#2349;&#2366;&#2352;&#2340;&#2368;&#2351; &#2310;&#2351;&#2369;&#2352;&#2381;&#2357;&#2367;&#2332;&#2381;&#2334;&#2366;&#2344; &#2360;&#2306;&#2360;&#2381;&#2341;&#2366;&#2344; &#2346;&#2335;&#2344;&#2366;</h2>");//&#2309;&#2326;&#2367;&#2354; &#2349;&#2366;&#2352;&#2340;&#2368;&#2351; &#2310;&#2351;&#2369;&#2352;&#2381;&#2357;&#2367;&#2332;&#2381;&#2334;&#2366;&#2344; &#2360;&#2306;&#2360;&#2381;&#2341;&#2366;&#2344; &#2346;&#2335;&#2344;&#2366;
		        
		  		  
		  		  //str.append("<h4>"+_rSlip.getHospitalAddress1());
		  		/* if(_rSlip.getHospitalAddress1()!=null && !_rSlip.getHospitalAddress1().trim().equals(""))
		            str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+"</font><br>");*/
		          if(_rSlip.getHospitalAddress2()!=null && !_rSlip.getHospitalAddress2().trim().equals(""))
		          {/*str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress2()+"</font><br>");*/}
		          if(_rSlip.getHospitalCity()!=null && !_rSlip.getHospitalCity().trim().equals(""))
		              str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+", "+ _rSlip.getHospitalCity());
		          /*if(_rSlip.getHospitalDistrict()!=null && !_rSlip.getHospitalDistrict().trim().equals(""))
		              str.append(  ", "+_rSlip.getHospitalDistrict());*/
		          if(_rSlip.getHospitalpinCode()!=null && !_rSlip.getHospitalpinCode().trim().equals("") && !_rSlip.getHospitalpinCode().trim().equals("0"))
		              str.append( "-"+_rSlip.getHospitalpinCode());  
		          if(_rSlip.getHospitalState()!=null && !_rSlip.getHospitalState().trim().equals(""))
		              str.append(  ", "+_rSlip.getHospitalState());
		          str.append(", India");  
		         str.append("</font><br>");
		         
		         str.append("<font style='font-size: 14px;'>");
		            if(_rSlip.getHospitalPhone()!=null && !_rSlip.getHospitalPhone().trim().equals(""))
		            {
		                str.append(RegistrationConfig.OPD_CARD_HEADER_LINE_4_PHONE_LABEL); 
		                str.append(_rSlip.getHospitalPhone());
		            }
		            if(_rSlip.getHospitalFax()!=null && !_rSlip.getHospitalFax().trim().equals("")){
		                str.append(", " + RegistrationConfig.OPD_CARD_HEADER_LINE_4_FAX_LABEL);
		                str.append(_rSlip.getHospitalFax());
		            }
		  			/*if(_rSlip.getHospitalEmail()!=null && !_rSlip.getHospitalEmail().trim().equals("")){
		  				str.append(", " +RegistrationConfig.OPD_CARD_HEADER_LINE_4_EMAIL_LABEL);
		  	  			str.append(_rSlip.getHospitalEmail());
		  			}*/
		  			
		  			str.append("</font><br></div>");
		  			 
  			}
  			/*****************************************************************************************************************************************************************/
  			/****************************************************************End 07.04.2017 Mukund****************************************************************************/
			str.append("</div><br>");
  			
  			str.append("<div class='div-table-row' align='center' style='height: 50px;'>");	  			
  				
  			 if(_mode.equalsIgnoreCase("RC"))	
  				str.append("<h2>REFUND RECEIPT</h2>");
  			 else
  				str.append("<h2>CASH RECEIPT</h2>");
  			str.append("</div>");
		
  			str.append("<div class='div-table-row' style='height: 10px;'>");
  			str.append("<div class='div-table-col' style='width: 60%; height: 20px'></div>");
  			str.append("<div class='div-table-col alignRight' style='width: 40%; height: 20px;'>");
  			str.append("DATE & TIME :"+billPrintedTime);					
			str.append("</div></div>");
			str.append("</div>");

			str.append("<div class='div-table-simple' style='border: 1px solid black; width:99%'>");
			
			str.append("<div class='div-table-row' style='height: 37px;'>");
			str.append("<div class='div-table-col' id='divBillNoLabel' style='width: 18%; height: 37px;'>BILL No.<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divBillNoLabelx' style='width: 2%; height: 37px;'>:<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col fontBold' style='width: 30%; height: 37px;'>");
			//str.append(_rSlip.getPatCrNo()+"/1");
			str.append(billNo);
			str.append("</div>");
					
			str.append("<div class='div-table-col' id='divCategoryLabel' style='width: 18%; height: 37px;'>CATEGORY<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divCategoryLabelx' style='width: 2%; height: 37px;'>:<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 30%; height: 37px;'>");
			//if(_mode.equalsIgnoreCase("RC"))
			//{
			str.append(_rSlip.getPatPrimaryCat().toUpperCase());
			str.append("(");
			str.append(_rSlip.getPatPrimaryCatGrp().toUpperCase());
			str.append(")");
			//}
			//else
			//{
				//str.append(_rSlip.getPatPrimaryCat().toUpperCase());
			//}
			str.append("</div></div>");
		   
			//Code commented by Garima for Billing Module Format Synchronization
			
		str.append("<div class='div-table-col' id='divBillDateLabel' style='width: 18%; height: 37px;'>BILL DATE<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divBillDateLabelx' style='width: 2%; height: 37px;'>:<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 30%; height: 37px;'>");
			str.append(billPrintedTime);
			str.append("</div>");
			
			//if(_mode.equalsIgnoreCase("RC"))
			if(_rSlip.getPatPrimaryCatGrpCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) || _rSlip.getPatPrimaryCatGrpCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
			{
			    str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divClientNameLabel' style='width: 18%; height: 37px;'>CLIENT<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divClientNameLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 25%; height: 37px;'>");
				str.append(_rSlip.getclientName().toUpperCase());
				str.append("</div></div>");
			}
			else
			{
				 str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divEmptyLabel' style='width: 25%; height: 37px;'><br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 25%; height: 37px;'>");
			str.append("");
			str.append("</div></div>");
			}
			
			str.append("<div class='div-table-row' style='height: 37px;'>");
			str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 18%; height: 37px;'>CR NO.<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divCRNoLabelx' style='width: 2%; height: 37px;'>:<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col fontBold' style='width: 30%; height: 37px;'>");
			str.append(_rSlip.getPatCrNo());
			str.append("</div>");
					
			str.append("<div class='div-table-col' id='divEmptyLabel' style='width: 25%; height: 37px;'><br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 25%; height: 37px;'>");
			str.append("");
			str.append("</div></div>");
			
			
			str.append("<div class='div-table-row' style='height: 37px;'>");
			str.append("<div class='div-table-col' id='divNameLabel' style='width: 18%; height: 37px;'>NAME<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divNameLabelx' style='width: 2%; height: 37px;'>:<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col fontBold' style='width: 30%; height: 37px;'>");
			str.append(patientName);
			str.append("</div>");
					
			str.append("<div class='div-table-col' id='divAgeSexLabel' style='width: 18%; height: 37px;'>AGE/SEX<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divAgeSexLabelx' style='width: 2%; height: 37px;'>:<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 30%; height: 37px;'>");
			str.append(ageGender);
			str.append("</div></div>");				
			
			
			str.append("<div class='div-table-row' style='height: 37px;'>");
			str.append("<div class='div-table-col' id='divDeptLabel' style='width: 18%; height: 37px;'>DEPARTMENT<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divDeptLabelx' style='width: 2%; height: 37px;'>:<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 30%; height: 37px;'>");
			str.append(departmentname.toUpperCase());
			str.append("</div>");
					
			str.append("<div class='div-table-col' id='divUnitLabel' style='width: 18%; height: 37px;'>UNIT<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divUnitLabelx' style='width: 2%; height: 37px;'>:<br/>");
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 30%; height: 37px;'>");
			str.append(unitName.toUpperCase());
			str.append("</div></div>");				
			
			str.append("<div class='div-table-simple'>");
  			str.append("<div class='div-table-row' style='width: 100%; height: 10px;' >");
  			//Start :Sheeldarshi:28thOct'14:Registration Cancellation
  			//if(_mode.equalsIgnoreCase("RC"))
  			if(_rSlip.getPatPrimaryCatGrpCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) || _rSlip.getPatPrimaryCatGrpCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE))
  				{
  				str.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				str.append("</div>");
				
				str.append("<div class='div-table-row' style='height: 20px;'>");
				str.append("<div class='div-table-col' id='divDescLabel' style='width: 30%; height: 37px;'>DESCRIPTION<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divRateLabel' style='width: 15%; height: 37px;'>RATE(Rs.)<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divQtyLabel' style='width: 15%; height: 37px;'>QTY.<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divAmountLabel' style='width: 15%; height: 37px;'>AMOUNT(Rs.)<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divPatientShareLabel' style='width: 15%; height: 37px;'>PATIENT SHARE<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divClientShareLabel' style='width: 10%; height: 37px;'>CLIENT SHARE<br/>");
				str.append("</div></div>");
	  			str.append("<div class='div-table-row' style='width: 100%; height: 10px;' >");
	  			str.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				str.append("</div>");
				
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divDesc' style='width: 30%; height: 37px;'>");
				str.append(job);
				str.append("</div>");
				str.append("<div class='div-table-col' id='divRate' style='width: 15%; height: 37px;'>");
				str.append(_rSlip.getPatActualAmount());
				str.append("</div>");
				str.append("<div class='div-table-col' id='divQty' style='width: 15%; height: 37px;'>");
				str.append("1");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divAmount' style='width: 15%; height: 37px;'>");
				str.append(_rSlip.getPatActualAmount());
				str.append("</div>");
				str.append("<div class='div-table-col' id='divPatAmount' style='width: 15%; height: 37px;'>");
				str.append(_rSlip.getPatAmountCollected());
				str.append("</div>");
				str.append("<div class='div-table-col' id='divClientAmount' style='width: 10%; height: 37px;'>");
				str.append(_rSlip.getPatActualAmount());
				str.append("</div>");			
				str.append("</div></div>");
					
				str.append("<div class='div-table-simple'>");
				str.append("<div class='div-table-row' style='height: 10px;'>");
				str.append("<div class='div-table-col' id='divDesc1' style='width: 30%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divRate1' style='width: 15%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divQty1' style='width: 15%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divAmount1' style='width: 15%; height: 10px;'>");
				str.append("----------------------------------------------------------------------------------------------");
				str.append("</div>");	
				str.append("<div class='div-table-col' id='divEmptyLabel' style='width: 15%; height: 10px;'><br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 10%; height: 10px;'>");
				str.append("");
				str.append("</div></div>");	
				str.append("<div class='div-table-row' style='height: 20px;'>");
				str.append("<div class='div-table-col' id='divDesc2' style='width: 30%; height: 20px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divRate2' style='width: 15%; height: 20px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col alignLeft' id='divDescLabel' style='width: 15%; height: 20px;'>TOTAL<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col ' id='divRateLabel' style='width: 15%; height: 20px;'>");
				str.append(actualAmount);
				str.append("</div>");
				str.append("<div class='div-table-col ' id='divPatRateLabel' style='width: 15%; height: 20px;'>");
				str.append(amount);
				str.append("</div>");
				str.append("<div class='div-table-col ' id='divClientRateLabel' style='width: 10%; height: 20px;'>");
				str.append(actualAmount);
				str.append("</div></div>");
				
				str.append("<div class='div-table-row' style='height: 10px;'>");
				str.append("<div class='div-table-col' id='divDesc1' style='width: 30%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divRate1' style='width: 15%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divQty1' style='width: 15%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divAmount1' style='width: 15%; height: 10px;'>");
				str.append("----------------------------------------------------------------------------------------------");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divEmptyLabel' style='width: 15%; height: 10px;'><br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' style='width: 10%; height: 10px;'>");
				str.append("");
				str.append("</div></div>");
				str.append("</div>");				
				
				str.append("<div class='div-table-simple'>");
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divRupeeDesc1' style='width: 28%; height: 37px;'>AMOUNT PAID BY PATIENT (IN WORD)<br/>");				
				str.append("</div>");
				str.append("<div class='div-table-col' id='divUnitLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col alignLeft' id='divRupeeDesc1' style='width: 70%; height: 37px;'>");
				str.append(amountInWords.toUpperCase());
				str.append("</div></div>");
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divRupeeDesc1' style='width: 28%; height: 37px;'>AMOUNT PAID BY CLIENT (IN WORD)<br/>");				
				str.append("</div>");
				str.append("<div class='div-table-col' id='divUnitLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col alignLeft' id='divRupeeDesc1' style='width: 70%; height: 37px;'>");
				str.append(amountPaidByClientInWords.toUpperCase());
				str.append("</div></div>");
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divNote' style='width: 70%; height: 37px;'>Note: Amount, Patient Share, and Client Share are in Rs.<br/>");
			}
			else
			{
				str.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				str.append("</div>");
				
				str.append("<div class='div-table-row' style='height: 20px;'>");
				str.append("<div class='div-table-col' id='divDescLabel' style='width: 30%; height: 37px;'>DESCRIPTION<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divRateLabel' style='width: 20%; height: 37px;'>RATE(Rs.)<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divQtyLabel' style='width: 25%; height: 37px;'>QTY.<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divAmountLabel' style='width: 25%; height: 37px;'>AMOUNT(Rs.)<br/>");
				str.append("</div></div>");
				
	  			str.append("<div class='div-table-row' style='width: 100%; height: 10px;' >");
	  			str.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				str.append("</div>");
				
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divDesc' style='width: 30%; height: 37px;'>");
				str.append(job);
				str.append("</div>");
				str.append("<div class='div-table-col' id='divRate' style='width: 20%; height: 37px;'>");
				str.append(_rSlip.getPatAmountCollected());
				str.append("</div>");
				str.append("<div class='div-table-col' id='divQty' style='width: 25%; height: 37px;'>");
				if(_mode.equalsIgnoreCase("RC"))
					str.append("-1");
				else
					str.append("1");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divAmount' style='width: 25%; height: 37px;'>");
				
				if(_mode.equalsIgnoreCase("RC"))
					str.append("-"+_rSlip.getPatAmountCollected());
				else
					str.append(_rSlip.getPatAmountCollected());
				str.append("</div>");			
				str.append("</div></div>");
					
				str.append("<div class='div-table-simple'>");
				str.append("<div class='div-table-row' style='height: 10px;'>");
				str.append("<div class='div-table-col' id='divDesc1' style='width: 30%; height: 10px;'>");
				str.append("");
				str.append("</div>");
			
				str.append("<div class='div-table-col' id='divRate1' style='width: 20%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divQty1' style='width: 25%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divAmount1' style='width: 25%; height: 10px;'>");
				str.append("----------------------------");
				str.append("</div></div>");	
				str.append("<div class='div-table-row' style='height: 20px;'>");
				str.append("<div class='div-table-col' id='divDesc2' style='width: 30%; height: 20px;'>");
				str.append("");
				str.append("</div>");					
				str.append("<div class='div-table-col' id='divRate2' style='width: 20%; height: 20px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col alignLeft' id='divDescLabel' style='width: 25%; height: 20px;'>TOTAL<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col ' id='divRateLabel' style='width: 25%; height: 20px;'>");
				if(_mode.equalsIgnoreCase("RC"))
					str.append("-"+amount);
				else
					str.append(amount);
				str.append("</div></div>");
				
				str.append("<div class='div-table-row' style='height: 10px;'>");
				str.append("<div class='div-table-col' id='divDesc1' style='width: 30%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divRate1' style='width: 20%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divQty1' style='width: 25%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divAmount1' style='width: 25%; height: 10px;'>");
				str.append("----------------------------");
				str.append("</div></div>");
				str.append("</div>");				
				
				str.append("<div class='div-table-simple'>");
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divRupeeDesc1' style='width: 18%; height: 37px;'>RUPEES (IN WORD)<br/>");				
				str.append("</div>");
				str.append("<div class='div-table-col' id='divUnitLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col alignLeft' id='divRupeeDesc1' style='width: 70%; height: 37px;'>");
				str.append(amountInWords.toUpperCase());
				str.append("</div>");
				str.append("</div>");
				
				if(_mode.equalsIgnoreCase("RC"))
					
				
				{
				
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divRemarksLabel' style='width: 18%; height: 37px;'>REASON<br/>");				
				str.append("</div>");
				str.append("<div class='div-table-col' id='divRemarksLabelx' style='width: 2%; height: 37px;'>:<br/>");
				str.append("</div>");
				str.append("<div class='div-table-col alignLeft' id='divRemarksLabel' style='width: 70%; height: 37px;'>");
				str.append(_rSlip.getRemarks());
				str.append("</div>");
				}
				
				else
					
				str.append("");
				str.append("</div>");
				
				//End :Sheeldarshi:28thOct'14:Registration Cancellation
				
				
				//str.append("</div></div>");	
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divCountDesc1' style='width: 30%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divCountDesc2' style='width: 20%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divCountDesc3' style='width: 25%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				/*str.append((_rSlip.getCounterName()==""||_rSlip.getCounterName()==null)?"( DEFAULT )":"( "+_rSlip.getCounterName().toUpperCase()+" )");*/
				str.append("<b>"+_rSlip.getLoginUserName().toUpperCase()+"</b>");
				/*str.append("</div></div>");*/	
				str.append("</div>");
				//By Mukund On 19.05.2017
				str.append("<div class='div-table-row' style='height: 37px;'>");
				str.append("<div class='div-table-col' id='divCountDesc1' style='width: 30%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divCountDesc2' style='width: 20%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<div class='div-table-col' id='divCountDesc3' style='width: 25%; height: 10px;'>");
				str.append("");
				str.append("</div>");
				str.append("<b>Authorised Signatory Label</b>");
				str.append("</div>");
				str.append("</div>");
				//End Mukund	
				str.append("</div>");
				
				str.append("</div>");

			}
		}
						
	}
	}
	catch(Exception e){
		System.out.println("Exception in createSlip "+e);	
		e.printStackTrace();
	}
	System.out.println("Bill Receipt Html String::::::::::::::::::::::::::::::::::::::::::\n"+str.toString());
	return str.toString();	

}
public static String print(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request,String _mode) throws ParseException{
	
  	StringBuilder str=new StringBuilder();
	int counter = 0;  		
	String align="";
	
	SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
SimpleDateFormat _sdf4 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");

String _printDatTime=_sdf2.format(_sdf1.parse((String)_request.getSession().getAttribute(Config.SYSDATE)));
	_rSlip.setSheetNo("1");
	//By Sandip on 23.06.17
	String asterixId1, asterixId2=""; 
	asterixId1 =_rSlip.getPatNationalId();
	if(asterixId1!=null && !asterixId1.equalsIgnoreCase(""))
	{
		asterixId1 = asterixId1.substring(8,12);
		asterixId2 = "XXXX-XXXX-"+asterixId1;
	}
	else
	{
		asterixId2 ="";
	}
	_rSlip.setPatNationalId(asterixId2);
	//ENd 23.06.17
	CharacterEncoding.setCharacterEncoding(_request);
	for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
	{
		str.append("<div class='div-table-simple' align='center' style='width: 95%;'>");
		str.append("<div class='div-table-row' style='height: 100px;'>");
		
	  /*****************************************************************************************************************************************************************/
    /****************************************************************On 07.04.2017 By Mukund for putting headers on the Registration cards***************************/
    if(RegistrationConfig.IS_HEADER_PRINTABLE.equals(RegistrationConfig.IS_HEADER_PRINTABLE_ON)){
            //str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 20%'><img src='/HISRegistration/hisglobal/images/NIMSLOGO.jpg'/></div>");
            str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 15%'><img src='/HISRegistration/hisglobal/images/nims-tr-logo.gif'/></div>");
            str.append("<div class='div-table-col' id='divOPDCardHeader' align='center' style='width: 70%'>");
                    
          //str.append("<h3>"+_rSlip.getHospitalName()+"</h3>");
          str.append("<br><font style='font-size: 16px; font-weight:bold;'>"+_rSlip.getHospitalName().toUpperCase()+"</font><br>");
          str.append("<h2>&#2309;&#2326;&#2367;&#2354; &#2349;&#2366;&#2352;&#2340;&#2368;&#2351; &#2310;&#2351;&#2369;&#2352;&#2381;&#2357;&#2367;&#2332;&#2381;&#2334;&#2366;&#2344; &#2360;&#2306;&#2360;&#2381;&#2341;&#2366;&#2344; &#2346;&#2335;&#2344;&#2366;</h2>");//&#2309;&#2326;&#2367;&#2354; &#2349;&#2366;&#2352;&#2340;&#2368;&#2351; &#2310;&#2351;&#2369;&#2352;&#2381;&#2357;&#2367;&#2332;&#2381;&#2334;&#2366;&#2344; &#2360;&#2306;&#2360;&#2381;&#2341;&#2366;&#2344; &#2346;&#2335;&#2344;&#2366;
          /*if(_rSlip.getHospitalAddress1()!=null && !_rSlip.getHospitalAddress1().trim().equals(""))
            str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+"</font><br>");*/
          if(_rSlip.getHospitalAddress2()!=null && !_rSlip.getHospitalAddress2().trim().equals(""))
          {/*str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress2()+"</font><br>");*/}
          if(_rSlip.getHospitalCity()!=null && !_rSlip.getHospitalCity().trim().equals(""))
              str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+", "+_rSlip.getHospitalCity());
          /*if(_rSlip.getHospitalDistrict()!=null && !_rSlip.getHospitalDistrict().trim().equals(""))
              str.append(  ", "+_rSlip.getHospitalDistrict());*/
          if(_rSlip.getHospitalpinCode()!=null && !_rSlip.getHospitalpinCode().trim().equals("") && !_rSlip.getHospitalpinCode().trim().equals("0"))
              str.append( "-"+_rSlip.getHospitalpinCode());  
          if(_rSlip.getHospitalState()!=null && !_rSlip.getHospitalState().trim().equals(""))
              str.append(  ", "+_rSlip.getHospitalState());
          
          str.append(", India"+"</font><br>");  
        // str.append("</font><br>");
         //str.append("<br>");
         //str.append("<h5>");
         str.append("<font style='font-size: 14px;'>");
            if(_rSlip.getHospitalPhone()!=null && !_rSlip.getHospitalPhone().trim().equals(""))
            {
                str.append(RegistrationConfig.OPD_CARD_HEADER_LINE_4_PHONE_LABEL); 
                str.append(_rSlip.getHospitalPhone());
            }
            if(_rSlip.getHospitalFax()!=null && !_rSlip.getHospitalFax().trim().equals("")){
                str.append(", " + RegistrationConfig.OPD_CARD_HEADER_LINE_4_FAX_LABEL);
                str.append(_rSlip.getHospitalFax());
            }
            /*if(_rSlip.getHospitalEmail()!=null && !_rSlip.getHospitalEmail().trim().equals("")){
                str.append(", " +RegistrationConfig.OPD_CARD_HEADER_LINE_4_EMAIL_LABEL);
                str.append(_rSlip.getHospitalEmail());
            }*/
            
            str.append("</font></div>");
            //DIV QRCODE 
            str.append("<div id='divQRCodeControl' class='div-table-col' style='width: 15%' align='right'>");
            if(_rSlip.getIsQRCodePrintFlag()!=null && _rSlip.getIsQRCodePrintFlag().equals(RegistrationConfig.ISQRCodePrintFlag_True))
            {
            	str.append("<img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=\"\">");
        	}
            str.append("</div>");
            
    }           
    /*****************************************************************************************************************************************************************/
    /****************************************************************End 07.04.2017 Mukund****************************************************************************/
    
		
		str.append("</div>");
		//str.append("<br>");
		/*str.append("<div class='div-table-row' style='height: 10px;'>");
		str.append("<div class='div-table-col fontBold alignCenter' style='width: 100%; height: 20px'>");
			if(_mode.equalsIgnoreCase("NR"))
				{//str.append("OPD Registration Card");
				}
				
			else if(_mode.equalsIgnoreCase("ER"))
				str.append("EMERGENCY Registration Card");
			else if(_mode.equalsIgnoreCase("DC"))
				str.append("Duplicate Registration Card");
			else if(_mode.equalsIgnoreCase("RP"))
				str.append("RePrint Registration Card"); 
		str.append("</div>");
		str.append("</div>");*/

		/*str.append("<div class='div-table-row' style='height: 10px;'>");
		str.append("<div class='div-table-col' style='width: 50%; height: 20px'></div>");
		str.append("<div class='div-table-col fontBold alignRight' style='width: 50%; height: 20px;'>");
		
		if(_mode.equalsIgnoreCase("NR")||_mode.equalsIgnoreCase("ER"))
			{if(_mode.equalsIgnoreCase("DC"))
			str.append("Registration FEE (Rs) :"+_rSlip.getPatAmountCollected());}
		else if(_mode.equalsIgnoreCase("DC"))
			str.append("Duplicate Card Printing FEE (Rs) :"+_rSlip.getPatAmountCollected());
		else if(_mode.equalsIgnoreCase("DV"))
			str.append("Department Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
		else if(_mode.equalsIgnoreCase("RP"))	
			str.append("Registration/Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
	str.append("</div></div>");*/

	if(_mode.equalsIgnoreCase("DC")){
	str.append("<div id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");}
	
	
	if(!_mode.equalsIgnoreCase("DC")){
		
		/*str.append("<div class='div-table-row' style='width: 100%;height: 37px;text-align:center;'>");
		str.append("<b>");
		str.append("<font size='4'>");
		str.append("OUT PATIENT RECORD");
		str.append("</font>");
		str.append("</b>");	
		str.append("</div>");*/
		str.append("<div class='div-table-row alignCenter' style='width: 100%;'>");
				
		if(_rSlip.getEpisodeVisitType()!=null&&(_rSlip.getEpisodeVisitType().equals("3")||_rSlip.getEpisodeVisitType().equals("2"))){
			str.append("<font style='font-size: 17px;'>CASUALTY OPD CARD");
		}else if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("1")){
			str.append("<font style='font-size: 17px;'>OUT PATIENT CARD");
		}else if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("4")){
			str.append("<font style='font-size: 17px;'>SPECIAL CLINIC CARD");
		}
		/*if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("1")){
		str.append(" ( MORNING CLINIC )");}
		else if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("4")){
		str.append(" ( EVENING CLINIC )");	}*/
		str.append("</font>");
		str.append("</div>");
			
		str.append("<div class='div-table-row width100' >");
		str.append("<div class='div-table-col width50 control' id='divEpisodeDateLabel' ><p>");
		str.append("Date & Time :&nbsp;");
		str.append("<b>"+_rSlip.getEpisodeDate()[index]+"</b>");
		str.append("</p></div>");
		str.append("<div class='div-table-col width50 label' >");
		str.append("<div id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");
		str.append("</div>");
		
		str.append("</div>");
		
		str.append("</div>");
		str.append("</div>");
		str.append("<div style='border: 1px solid black;width:95%;'>");
		str.append("<div class='div-table-simple border'>");
		str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 40px;'>");
		str.append("<div class='div-table-simple' align='center' style='width: 100%;'>");
		str.append("<div class='div-table-row' style='width: 100%;height: 20px;'>");
		str.append("<div class='div-table-col' style='width: 21%;border: 0px solid #000'>CR No ");
		str.append("</div>");
		str.append("<div class='div-table-col' style='width: 75%;border: 0px solid #000'>");
		str.append(":&nbsp;<b>");
		str.append(_rSlip.getPatCrNo());
		str.append("</b></div>");
		
		str.append("</div>");
		
		str.append("</div>");
		
		str.append("</div>");

		str.append("<div class='div-table-col' style='width: 33%;height: 40px;'>Aadhaar &nbsp;&nbsp;&nbsp;:&nbsp;<b>");
		str.append(_rSlip.getPatNationalId());
		str.append("</b></div>");
	
		str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 25%; height: 40px;'>Fees: &nbsp;");
		str.append("<b>"+_rSlip.getPatAmountCollected()+"</b>");
		str.append("</div>");
	}
	else
	{
		str.append("<div style='border: 1px solid black;'>");
		str.append("<div class='div-table-simple border'>");
		str.append("<div class='div-table-row' style='height: 70px;'>");
		str.append("<div class='div-table-col fontBold' id='divCRNoLabel' style='width: 20%; height: 70px;'>CR No<br/>");
		//str.append(RegistrationConfig.OPD_CARD_NAME_MARATHI_LABEL);
		str.append("</div>");
		str.append("<div class='div-table-col' style='width: 35%; height: 70px;'>");
		str.append(_rSlip.getPatCrNo());
		str.append("</div>");
		
		str.append("<div class='div-table-col noPadding' style='width: 25%; height: 70px;'>");
		str.append("<div class='div-table-simple'>");
		str.append("<div class='div-table-row'>");
		//str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>Last Vist <br/> Date & Time");
		str.append("<div class='div-table-col fontBold' id='divAgeLabel' style='width: 100%; height: 35px; '>Last Visit <br/> Date & Time");
		//str.append(RegistrationConfig.OPD_CARD_AGE_MARATHI_LABEL);
		str.append("</div></div>");
		str.append("<div class='div-table-row'>");
		//str.append("<div class='div-table-col fontBold' id='divGenderLabel' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>Duplicate Card printing <br/>Date & Time & Valid Up to <br/> ");
		str.append("<div class='div-table-col fontBold' id='divGenderLabel' style='width: 100%; height: 35px; '>Duplicate Card printing <br/>Date & Time & Valid Up to <br/> ");
		//str.append(RegistrationConfig.OPD_CARD_SEX_HINDI_LABEL);
		str.append("</div></div></div></div>");
		str.append("<div class='div-table-col noPadding' style='width: 20%; height: 70px;'>");
		str.append("<div class='div-table-simple'>");
		str.append("<div class='div-table-row'>");
		//str.append("<div class='div-table-col' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:0px #000000'>");
		str.append("<div class='div-table-col' style='width: 100%; height: 35px; '>");
		str.append(_rSlip.getEpisodeDate()[index]);
		str.append("</div></div>");
		str.append("<div class='div-table-row'>");
		//str.append("<div class='div-table-col' style='width: 100%; height: 35px; border-bottom:0px;border-left:0 ;border-right:0px;border-top:2px solid #000000'>");
		str.append("<div class='div-table-col' style='width: 100%; height: 35px; '>");
		if(_mode.equalsIgnoreCase("ER"))
		{
		str.append(_printDatTime+" <br/>"+_rSlip.getExpDate()[index]);
		}
		else
		{
			str.append(_printDatTime+" <br/>"+_sdf3.format(_sdf2.parse((String)_rSlip.getExpDate()[index])));
		}
		
		//str.append(_printDatTime+" <br/>"+_rSlip.getExpDate()[index]);
		
		str.append("</div></div></div></div></div>");
	}
	
	str.append("<div class='div-table-row' style='height: 40px;'>");
	str.append("<div class='div-table-col' id='divPatientNameLabel' style='width: 42%; height: 40px;'>Patient Name &nbsp;&nbsp;:&nbsp;");
	//str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
	
	str.append("<b>");
	str.append(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName().toUpperCase()+" "+_rSlip.getPatLastName().toUpperCase());
	str.append("</b>");
	str.append("</div>");
	str.append("<div class='div-table-col' id='divAgeLabel' style='width: 33%; height: 40px;'>Age/Sex&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<b>");
	//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
	
	
	str.append(_rSlip.getPatAge());
	str.append("/");
	str.append(_rSlip.getPatGender().substring(0,1));
	
	str.append("</b></div>");
	str.append("<div class='div-table-col' id='divCasteLabel' style='width: 25%; height: 40px;'>Category&nbsp;&nbsp;:&nbsp;<b>");
	
	
	str.append((_rSlip.getPatPrimaryCat().equalsIgnoreCase("null")|| _rSlip.getPatPrimaryCat().equalsIgnoreCase("")?"NA":_rSlip.getPatPrimaryCat()));

	str.append("</b></div>");
	str.append("</div>");
	if(_rSlip.getPatPrimaryCat().equalsIgnoreCase("staff"))
	{
		str.append("<div class='div-table-row' style='height: 40px;'>");
		str.append("<div class='div-table-col' id='divStaffNameLabel' style='width: 42%; height: 40px;'>Staff Name/No. &nbsp;&nbsp;:&nbsp;");
		//str.append("<div class='div-table-col' style='width: 35%; height: 50px;'>");
		
		str.append("<b>");
		str.append((_rSlip.getDependentName()!=null && !_rSlip.getDependentName().equals("")?_rSlip.getDependentName().toUpperCase():"NA"));
		str.append("/");
		str.append((_rSlip.getHiddenPatIdNo()!=null && !_rSlip.getHiddenPatIdNo().equals("")?_rSlip.getHiddenPatIdNo():"NA"));
		str.append("</b>");
		str.append("</div>");
		str.append("<div class='div-table-col' id='divRelationLabel' style='width: 33%; height: 40px;'>Staff Relation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
		//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
		
		
		str.append((_rSlip.getPatMemRelationName()!=null && ! _rSlip.getPatMemRelationName().equals("")?_rSlip.getPatMemRelationName().toUpperCase():"NA"));
		
		
		str.append("</div>");
		str.append("<div class='div-table-col' id='divDepartmentLabel' style='width: 25%; height: 40px;'>Staff Department&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
		//str.append("<div class='div-table-col' style='width: 20%; height: 50px;'>");
		
		
		str.append((_rSlip.getPatMemDeptName()!=null && !  _rSlip.getPatMemDeptName().equals("")?_rSlip.getPatMemDeptName().toUpperCase():"NA"));
		
		
		str.append("</div>");
		
		str.append("</div>");
	}
	str.append("<div class='div-table-row' style='width: 100%;height: 40px;'>");
	str.append("<div class='div-table-col' id='divAreaLabel' style='width: 75%; height: 40px;'>Address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
	//str.append(RegistrationConfig.OPD_CARD_RESIDENCE_MARATHI_LABEL);
	
	
	str.append(_rSlip.getAddress1()+" "+ _rSlip.getAddress2());

	str.append("</div>");
	
	str.append("<div class='div-table-col' style='width: 25%; height: 40px;'>MLC No&nbsp;&nbsp;&nbsp;:&nbsp;");      
	str.append(_rSlip.getMlcDetail());
	str.append("</div>");
	str.append("</div>");
	str.append("<div class='div-table-row ' id='divTokenLabel' style='width: 100%; height: 50px;'>");
	str.append("<div class='div-table-col'  style='width: 42%;height: 60px;'>");
	str.append("<div>Department :&nbsp;");
	
	String[] tokenDetail=new String[4];
	if(_rSlip.getDepartmentUnit()[index]!=null && _rSlip.getDepartmentUnit()[index]!="")
	{
		tokenDetail[0]=_rSlip.getDepartmentToBeVisited()[index]+'-'+_rSlip.getDepartmentUnit()[index];
	}
	else
	{
		tokenDetail[0]=_rSlip.getDepartmentToBeVisited()[index];
	}
		int rows=1;
		String tokenSplit="";
		str.append("<b>");
			if(tokenDetail[0].length()>20)
			{
				counter++;
				rows= tokenDetail[0].length()/20+((tokenDetail[0].length()%20==0)?0:1);		
				for(int j=0;j<rows;j++){
				
  				if(j==rows-1){
  					tokenSplit=tokenDetail[0].substring(j*20);
  				}else{
  					tokenSplit=tokenDetail[0].substring(j*20,(j+1)*20);
  				}
  				
  					str.append(tokenSplit);
  					
  			}
  			
  		}	
			else if(tokenDetail[0].length()<=20)
			{
				
				str.append(tokenDetail[0]);
				
				 				
			}
			str.append("</b>");
			str.append("</div>");
			//str.append("</br>");
			str.append("<div>OPD Days :&nbsp;<b>");
			tokenDetail[2]=_rSlip.getWorkingDays()[index];
			if(_rSlip.getWorkingDays()[index].contains("#"))
				tokenDetail[2]=	_rSlip.getWorkingDays()[index].split("#")[0];
	  		tokenSplit="";
	  	if(tokenDetail[2].length()>20)
		{
			counter++;
			rows= tokenDetail[2].length()/20+((tokenDetail[2].length()%20==0)?0:1);		
			for(int j=0;j<rows;j++){
			
				if(j==rows-1){
					tokenSplit=tokenDetail[2].substring(j*20);
				}else{
					tokenSplit=tokenDetail[2].substring(j*20,(j+1)*20);
				}
				
					str.append(tokenSplit);
					
			}
			
		}	
		else if(tokenDetail[2].length()<=20)
		{
			str.append(tokenDetail[2]);		
			 				
		}
	  	str.append("</b></div>");
	  	/*str.append("<div><font color=''>Queue No. :<b>");
	  	tokenDetail[3]=_rSlip.getQueNo()[index];
		if(tokenDetail[3].length()>20)
		{
			counter++;
			rows= tokenDetail[3].length()/20+((tokenDetail[3].length()%20==0)?0:1);		
			for(int j=0;j<rows;j++){
			
				if(j==rows-1){
					tokenSplit=tokenDetail[3].substring(j*20);
				}else{
					tokenSplit=tokenDetail[3].substring(j*20,(j+1)*20);
				}
				
					
					
			}
			
		}	
		else if(tokenDetail[3].length()<=20)
		{
			str.append(tokenDetail[3]);		
			 				
		}
	  	
	  	str.append("</b></font></div>");*/
	  	   str.append("</div>");
	  	 /*****************************************************************************************************************************************************************************/
       /*************************************************************************By Mukund on 21.04.2017*****************************************************************************/
   	
			str.append("<div class='div-table-col'  style='width: 33%; height: 60px;'>");
			 str.append("<div>Doctor&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
         str.append(_rSlip.getUnitConsultant()[index]);
         str.append("</div>");
         
         /*str.append("<div>Room No.	:");
			str.append("<b>");
			tokenDetail[1]=_rSlip.getRoom()[index];// Commented By Adil Wasi
	  		tokenSplit="";
	  		if(tokenDetail[1].length()>20)
	  		{
			counter++;
			rows= tokenDetail[1].length()/20+((tokenDetail[1].length()%20==0)?0:1);		
			for(int j=0;j<rows;j++){
			
				if(j==rows-1){
					tokenSplit=tokenDetail[1].substring(j*20);
				}else{
					tokenSplit=tokenDetail[1].substring(j*20,(j+1)*20);
				}
				
					
					
			}
			
		}	
		else if(tokenDetail[1].length()<=20)
		{
			str.append(tokenDetail[1]);		
			 				
		}
	  		
	  str.append("</b>");
 	str.append("</div>");*/

/***********************************************************************************End Mukund 0n 21.04.2017***************************************************************/
/*****************************************************************************************************************************************************************************/

 	//start : Surabhi
 	//reason: hide the queue no. from the slip
	  	/*str.append("<div><font color=''>Queue No. :");
	  	tokenDetail[3]=_rSlip.getQueNo()[index];
		if(tokenDetail[3].length()>20)
		{
			counter++;
			rows= tokenDetail[3].length()/20+((tokenDetail[3].length()%20==0)?0:1);		
			for(int j=0;j<rows;j++){
			
				if(j==rows-1){
					tokenSplit=tokenDetail[3].substring(j*20);
				}else{
					tokenSplit=tokenDetail[3].substring(j*20,(j+1)*20);
				}
				
					
					
			}
			
		}	
		else if(tokenDetail[3].length()<=20)
		{
			str.append(tokenDetail[3]);		
			 				
		}
	  	
	  	str.append("</font></div>");*/
 		//str.append("<div></div>");
 		//End on 10.04.2017 by Mukund to remove the queue no field from the OPD cards
  	//end
	  str.append("</div>");
	 /*****************************************************************************************************************************************************************************/
  /***************************************************************By Mukund on 21.04.2017**************************************************************************************/
 /*str.append("<div class='div-table-col'  style='width: 25%; height: 60px;'>Doctor&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
	  
	//str.append("<b>");
	//str.append(_rSlip.getDoctorName());
	  str.append(_rSlip.getUnitConsultant()[index]);
//	str.append("</b>");
	str.append("</div>");*/
str.append("<div class='div-table-col'  style='width: 25%; height: 60px;'>");
str.append("<div>Room No.    :");
   str.append("<b>");
   tokenDetail[1]=_rSlip.getRoom()[index];// Commented By Adil Wasi
   tokenSplit="";
   if(tokenDetail[1].length()>20)
   {
   counter++;
   rows= tokenDetail[1].length()/20+((tokenDetail[1].length()%20==0)?0:1);     
   for(int j=0;j<rows;j++){
   
       if(j==rows-1){
           tokenSplit=tokenDetail[1].substring(j*20);
       }else{
           tokenSplit=tokenDetail[1].substring(j*20,(j+1)*20);
       }
       
           
           
   }
   
}   
else if(tokenDetail[1].length()<=20)
{
   str.append(tokenDetail[1]);     
                   
}
   
str.append("</b>");
	str.append("</div>");
	str.append("</div>");
	 /**************************************************************************End : Mukund on 21.04.2017************************************************************************/
    /*****************************************************************************************************************************************************************************/
        str.append("</div>");
	
	//str.append("<div class='div-table-simple border'>");
	//str.append("<div class='div-table-row' style='width:100%;height: 80px;'>ALERTS");
	//str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>ALERTS");str.append("</div>");str.append("</br>");
	//str.append("</div>");
	//str.append("<div class='div-table-row' style='width:100%;height: 80px;'>DIAGNOSIS");
	//str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>DIAGNOSIS");str.append("</div>");
	//str.append("</div>");
	
	/************************************<font style="font-size:20px"><b>सहमति</b></font> ************/
	
	str.append("<div class='div-table-simple border'>");
	str.append("<div class='div-table-row fontBold alignCenter'>"+
"<div class='div-table-col fontBold alignCenter' style='width: 100%;font-size:10px;'>"+"<font style='font-size:15px'>सहमति |</font><br>"+
//"मैं ________ अपनी इच्छा से बिना किसी दबाव के AIIMS PATNA मै अपना/अपने रोगी का इलाज़/जांच कराने को इस जानकारी के साथ तैयार हूं | मुझे  इलाज़ और जांच के खतरे  अच्छी  तरह से समझा दिये गये हैं और उन खतरों के बावज़ूद मैं/मेरे रोगी का प्रस्तावित इलाज़  ऍव  जांच अपनी इच्छा से  बिना दबाव के कराने को तैयार हूं|"+
"&#2350;&#2376;&#2306; &#95;&#95;&#95;&#95;&#95;&#95;&#95;&#95; &#2309;&#2346;&#2344;&#2368; &#2311;&#2330;&#2381;&#2331;&#2366; &#2360;&#2375; &#2348;&#2367;&#2344;&#2366; &#2325;&#2367;&#2360;&#2368; &#2342;&#2348;&#2366;&#2357; &#2325;&#2375; AIIMS PATNA "+
"&#2350;&#2376; &#2309;&#2346;&#2344;&#2366;&#47;&#2309;&#2346;&#2344;&#2375; &#2352;&#2379;&#2327;&#2368; &#2325;&#2366; &#2311;&#2354;&#2366;&#2332;&#2364;&#47;&#2332;&#2366;&#2306;&#2330; &#2325;&#2352;&#2366;&#2344;&#2375; &#2325;&#2379; &#2311;&#2360; &#2332;&#2366;&#2344;&#2325;&#2366;&#2352;&#2368; &#2325;&#2375; &#2360;&#2366;&#2341; &#2340;&#2376;&#2351;&#2366;&#2352; &#2361;&#2370;&#2306; &#2404; &#2350;&#2369;&#2333;&#2375;  &#2311;&#2354;&#2366;&#2332;&#2364; &#2324;&#2352; &#2332;&#2366;&#2306;&#2330; &#2325;&#2375; &#2326;&#2340;&#2352;&#2375;  &#2309;&#2330;&#2381;&#2331;&#2368;  &#2340;&#2352;&#2361; &#2360;&#2375; &#2360;&#2350;&#2333;&#2366; &#2342;&#2367;&#2351;&#2375; &#2327;&#2351;&#2375; &#2361;&#2376;&#2306; &#2324;&#2352; &#2313;&#2344; &#2326;&#2340;&#2352;&#2379;&#2306; &#2325;&#2375; &#2348;&#2366;&#2357;&#2395;&#2370;&#2342; &#2350;&#2376;&#2306;&#47;&#2350;&#2375;&#2352;&#2375; &#2352;&#2379;&#2327;&#2368; &#2325;&#2366; &#2346;&#2381;&#2352;&#2360;&#2381;&#2340;&#2366;&#2357;&#2367;&#2340; &#2311;&#2354;&#2366;&#2332;&#2364;  &#2317;&#2357;  &#2332;&#2366;&#2306;&#2330; &#2309;&#2346;&#2344;&#2368; &#2311;&#2330;&#2381;&#2331;&#2366; &#2360;&#2375;  &#2348;&#2367;&#2344;&#2366; &#2342;&#2348;&#2366;&#2357; &#2325;&#2375; &#2325;&#2352;&#2366;&#2344;&#2375; &#2325;&#2379; &#2340;&#2376;&#2351;&#2366;&#2352; &#2361;&#2370;&#2306;&#2404;"+
"<br>"+"<div class='div-table-simple border'>"
+"<div class='div-table-row fontBold alignCenter'>"
+"<div class='div-table-col fontBold alignLeft' style='width: 50%; border:0px;'>Date: "+_rSlip.getEpisodeDate()[index]+"</div>"
+"<div class='div-table-col fontBold alignRight' style='width: 50%; border:0px;'>________________</div>"
+"</div>"
+"<div class='div-table-row fontBold alignCenter'>"
+"<div class='div-table-col fontBold alignLeft' style='width: 50%; border:0px;'>Place: Patna</div>"
+"<div class='div-table-col fontBold alignRight' style='width: 50%; border:0px;'>Signature</div>"
+"</div>"
+"</div></div>"
+"</div>");
	/***********************************************/
	
	
			str.append("<div class='div-table-row' style='height: 37px;'>");
			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank1' style='width: 25%; height: 37px; border:0px;'>");
			//str.append(RegistrationConfig.OPD_CARD_DATE_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col fontBold alignCenter' style='width: 20%; height: 37px; border:0px;'>");
			//str.append(RegistrationConfig.OPD_CARD_SYMPTOMSPROGRESS_MARATHI_LABEL);
			str.append("</div>");
			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 50%; height: 37px;border:0px;'>");
			//str.append(RegistrationConfig.OPD_CARD_PRESCRIPTIONS_MARATHI_LABEL);
			str.append("</div>");
			
			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 25%; height: 37px;border:0px;'>");
			//str.append(RegistrationConfig.OPD_CARD_HOWMANYDAYSILL_MARATHI_LABEL);
			str.append("</div>");
			str.append("</div>");
		
			str.append("<div class='div-table-row' style='height: 415px;'>");
			str.append("<div class='div-table-col' id='divBlank1' style='width: 25%; height: 415px;border:0px;'></div>");
			//str.append("<div class='div-table-col' style='width: 20%; height: 700px;'></div>");
			str.append("<div class='div-table-col' id='divBlank2' style='width: 50%; height: 415px;border:0px;'></div>");
			str.append("<div class='div-table-col' id='divBlank2' style='width: 25%; height: 415px;border:0px;'></div>");
			str.append("</div>");
			str.append("</div></div></div>");

			if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_CENTER))
				align="Center";
			else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_LEFT))
				align="Left";
			else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_RIGHT))
				align="Right";
			else
				align="Center";

			str.append("<div class='div-table-simple border'>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col alignCenter' style='width: 100%;border:0px;font-size:10px;'>");
			str.append(RegistrationConfig.medicinalSideEffects_Hindi+" (0612-2451207) (Toll Free No. 18001803024)</div></div>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col alignCenter' style='width: 100%;border:0px;font-size:10px;'>");  
			str.append("In Case of drug side effects. Please contact Phone no. (0612-2451207) (Toll Free No. 18001803024)</div></div>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col alignCenter' style='width: 100%;border:0px;font-size:10px;'>  PHULWARISHARIF, PATNA BIHAR-801507</div></div>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col  alignCenter' style='width: 100%;border:0px;font-size:10px;'> AIIMS OFFICE CONTACT NUMBER - 0612-2451044 | WEBSITE:-www.aiimspatna.org</div></div>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col  alignCenter' style='width: 100%;border:0px;font-size:10px;'> <b>"+RegistrationConfig.smokingIsInjuriousToHealth_Hindi+"</b> SMOKING IS PROHIBITED IN HOSPITAL PREMISES</div></div></div>");
		
	} 
	
	return escapeHtml3(str.toString()).replaceAll("'", "&#039;");
	
	//return HtmlEscape.htmlEncode(str.toString());
  
}

public static String printDuplicateCard(RegistrationSlip _rSlip, String tmpFileName,HttpServletRequest _request,String _mode) throws ParseException{
	
  	StringBuilder str=new StringBuilder();
	int counter = 0;  		
	String align="";
	
	SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
    SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
	String _printDatTime=_sdf2.format(_sdf1.parse((String)_request.getSession().getAttribute(Config.SYSDATE)));
	_rSlip.setSheetNo("1");
	//By Sandip on 23.06.17
	String asterixId1, asterixId2=""; 
	asterixId1 =_rSlip.getPatNationalId();
	if(asterixId1!=null && !asterixId1.equalsIgnoreCase(""))
	{
		asterixId1 = asterixId1.substring(8,12);
		asterixId2 = "XXXX-XXXX-"+asterixId1;
	}
	else
	{
		asterixId2 ="";
	}
	_rSlip.setPatNationalId(asterixId2);
	//ENd 23.06.17
	CharacterEncoding.setCharacterEncoding(_request);
	for(int index=0;index<_rSlip.getDepartmentToBeVisited().length;index++)
	{
		str.append("<div class='div-table-simple'  font-size='10px' align='center' style='width: 95%;'>");
		str.append("<div class='div-table-row' style='height: 50px;'>");

		/*****************************************************************************************************************************************************************/
			/****************************************************************On 07.04.2017 By Mukund for putting headers on the Registration cards***************************/
			if(RegistrationConfig.IS_HEADER_PRINTABLE.equals(RegistrationConfig.IS_HEADER_PRINTABLE_ON)){
          str.append("<div class='div-table-col' id='divOPDCardLogo' align='left' style='width: 15%'><img src='/HISRegistration/hisglobal/images/nims-tr-logo.gif'/></div>");
          str.append("<div class='div-table-col' id='divOPDCardHeader' align='center' style='width: 70%'>");
                    
          str.append("<br><font style='font-size: 16px; font-weight:bold;'>"+_rSlip.getHospitalName().toUpperCase()+"</font><br>");
          str.append("<h2>&#2309;&#2326;&#2367;&#2354; &#2349;&#2366;&#2352;&#2340;&#2368;&#2351; &#2310;&#2351;&#2369;&#2352;&#2381;&#2357;&#2367;&#2332;&#2381;&#2334;&#2366;&#2344; &#2360;&#2306;&#2360;&#2381;&#2341;&#2366;&#2344; &#2346;&#2335;&#2344;&#2366;</h2>");//&#2309;&#2326;&#2367;&#2354; &#2349;&#2366;&#2352;&#2340;&#2368;&#2351; &#2310;&#2351;&#2369;&#2352;&#2381;&#2357;&#2367;&#2332;&#2381;&#2334;&#2366;&#2344; &#2360;&#2306;&#2360;&#2381;&#2341;&#2366;&#2344; &#2346;&#2335;&#2344;&#2366;
          
         /* if(_rSlip.getHospitalAddress1()!=null && !_rSlip.getHospitalAddress1().trim().equals(""))
            str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+"</font><br>");*/
          if(_rSlip.getHospitalAddress2()!=null && !_rSlip.getHospitalAddress2().trim().equals(""))
          {/*str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress2()+"</font><br>");*/}
          if(_rSlip.getHospitalCity()!=null && !_rSlip.getHospitalCity().trim().equals(""))
              str.append("<font style='font-size: 14px;'>"+_rSlip.getHospitalAddress1()+", "+ _rSlip.getHospitalCity());
          /*if(_rSlip.getHospitalDistrict()!=null && !_rSlip.getHospitalDistrict().trim().equals(""))
              str.append(  ", "+_rSlip.getHospitalDistrict());*/
          if(_rSlip.getHospitalpinCode()!=null && !_rSlip.getHospitalpinCode().trim().equals("") && !_rSlip.getHospitalpinCode().trim().equals("0"))
              str.append( "-"+_rSlip.getHospitalpinCode());  
          if(_rSlip.getHospitalState()!=null && !_rSlip.getHospitalState().trim().equals(""))
              str.append(  ", "+_rSlip.getHospitalState());
          str.append(", India");  
         str.append("</font><br>");
         
         str.append("<font style='font-size: 14px;'>");
            if(_rSlip.getHospitalPhone()!=null && !_rSlip.getHospitalPhone().trim().equals(""))
            {
                str.append(RegistrationConfig.OPD_CARD_HEADER_LINE_4_PHONE_LABEL); 
                str.append(_rSlip.getHospitalPhone());
            }
            if(_rSlip.getHospitalFax()!=null && !_rSlip.getHospitalFax().trim().equals("")){
                str.append(", " + RegistrationConfig.OPD_CARD_HEADER_LINE_4_FAX_LABEL);
                str.append(_rSlip.getHospitalFax());
            }
            
            str.append("</font></div>");
            //DIV QRCODE 
            str.append("<div id='divQRCodeControl' class='div-table-col' style='width: 15%' align='right'>");
            if(_rSlip.getIsQRCodePrintFlag()!=null && _rSlip.getIsQRCodePrintFlag().equals(RegistrationConfig.ISQRCodePrintFlag_True))
            {
            	str.append("<img src=\"/HISRegistration/ShowImageOutofAnArray\" alt=''>");
        	}
            str.append("</div>");
            
    }			
			/*****************************************************************************************************************************************************************/
			/****************************************************************End 07.04.2017 Mukund****************************************************************************/
			
		
		str.append("</div>");
		//str.append("<br>");
		/*str.append("<div class='div-table-row' style='height: 10px;'>");
		str.append("<div class='div-table-col fontBold alignCenter' style='width: 100%; height: 20px'>");
			if(_mode.equalsIgnoreCase("NR"))
				str.append("OPD Registration Card");
			else if(_mode.equalsIgnoreCase("ER"))
				str.append("EMERGENCY Registration Card");
			
		str.append("</div>");
		str.append("</div>");*/

		/*str.append("<div class='div-table-row' style='height: 10px;'>");
		str.append("<div class='div-table-col' style='width: 50%; height: 20px'></div>");
		str.append("<div class='div-table-col fontBold alignRight' style='width: 50%; height: 20px;'>");
		
		if(_mode.equalsIgnoreCase("NR")||_mode.equalsIgnoreCase("ER"))
			str.append("Registration FEE (Rs) :"+_rSlip.getPatAmountCollected());
		else if(_mode.equalsIgnoreCase("DV"))
			str.append("Department Visit FEE (Rs) :"+_rSlip.getPatAmountCollected());
			str.append("</div></div>");*/

			//str.append("<b>");
		/*if(_rSlip.getEpisodeVisitType()!=null&&(_rSlip.getEpisodeVisitType().equals("3")||_rSlip.getEpisodeVisitType().equals("2"))){
		str.append("<font style='font-size: 17px;'>CASUALTY OPD CARD");
	}else{
		str.append("<font style='font-size: 17px;'>OUT PATIENT CARD");
	}*/
			if(!_mode.equalsIgnoreCase("DC")){
				if(_rSlip.getEpisodeVisitType()!=null&&(_rSlip.getEpisodeVisitType().equals("3")||_rSlip.getEpisodeVisitType().equals("2"))){
					str.append("<div class='div-table-row alignCenter' style='height: 10px;'><font style='font-size: 17px;'>CASUALTY PATIENT CARD - REPRINTED");
				}else if(_rSlip.getEpisodeVisitType()!=null&&(_rSlip.getEpisodeVisitType().equals("4"))){
					str.append("<div class='div-table-row alignCenter' style='height: 10px;'><font style='font-size: 17px;'>SPECIAL CLINIC CARD - REPRINTED");
				}else{
					str.append("<div class='div-table-row alignCenter' style='height: 10px;'><font style='font-size: 17px;'>OUT PATIENT CARD - REPRINTED");
				}
			}else{
				if(_rSlip.getEpisodeVisitType()!=null&&(_rSlip.getEpisodeVisitType().equals("3")||_rSlip.getEpisodeVisitType().equals("2"))){
					str.append("<div class='div-table-row alignCenter' style='height: 10px;'><font style='font-size: 17px;'>CASUALTY PATIENT CARD - DUPLICATE");
				}else if(_rSlip.getEpisodeVisitType()!=null&&(_rSlip.getEpisodeVisitType().equals("4"))){
					str.append("<div class='div-table-row alignCenter' style='height: 10px;'><font style='font-size: 17px;'>SPECIAL CLINIC CARD - DUPLICATE");
				}else{
					str.append("<div class='div-table-row alignCenter' style='height: 10px;'><font style='font-size: 17px;'>OUT PATIENT CARD - DUPLICATE");
				}
			}
			
			/*if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("1"))
				str.append("( MORNING CLINIC )");
			else if(_rSlip.getEpisodeVisitType()!=null&&_rSlip.getEpisodeVisitType().equals("4"))
				str.append("( EVENING CLINIC )");*/	
			str.append("</font></div>");
			

			str.append("<div class='div-table-row width90' >");
			str.append("<div class='div-table-col control width50' id='divEpisodeDateLabel'><p>Last Visit Date & Time :&nbsp;");			
			str.append("<b>"+_rSlip.getEpisodeDate()[index]+"</b>");
			str.append("</p></div>");
	
			str.append("<div class='div-table-col label width50' >");
			str.append("<div id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");
			str.append("</div></div>");
			str.append("</div>");
			
			
			
			str.append("<div style='border: 1px solid black;width:95%;'>");
			str.append("<div class='div-table-simple border'>");
			str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 40px;'>");
  			str.append("<div class='div-table-simple' align='center' style='width: 100%;'>");
			str.append("<div class='div-table-row' style='width: 100%;height: 20px;'>");
			str.append("<div class='div-table-col' style='width: 21%;border: 0px solid #000'>CR No ");
			str.append("</div>");
			str.append("<div class='div-table-col' style='width: 75%;border: 0px solid #000'>");
			str.append(":&nbsp;<b>");				
			str.append(_rSlip.getPatCrNo());
			str.append("</b></div>");
			str.append("</div>");
			
			str.append("</div></div>");
			
			str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 33%; height: 40px;padding-top: 15px;'>Aadhaar :&nbsp;<b>");
			str.append(_rSlip.getPatNationalId());
			str.append("</b></div>");
			/***/
			str.append("<div class='div-table-col' style='width: 25%; height: 40px;padding-top: 15px;'>");
			/*str.append("<div class='div-table-row'> Card Print Fee: <b>&#8377;"+_rSlip.getPatAmountCollected()+"&#47;&#45;</b></div>");*/
			str.append("<div class='div-table-row'> FEE : <b>&#8377;"+_rSlip.getEpisodeVisitAmount()+"&#47;&#45;</b></div>");
			str.append("</div>");
			/***/
		/*else//Separating Reprint & duplicate card print headers
		{	
			str.append("<b>");
			str.append("<div  style='width: 100%;height: 37px;text-align: center;border: 0px solid #000;'>OUT PATIENT RECORD - DUPLICATE CARD");
			str.append("</div></b>");
			
			str.append("<div class='div-table-row width90' >");
			str.append("<div class='div-table-col control width50' id='divEpisodeDateLabel'><p>Last Visit Date & Time :&nbsp;");			
			str.append("<b>"+_rSlip.getEpisodeDate()[index]+"</b>");
			str.append("</p></div>");
	
			str.append("<div class='div-table-col label width50' >");
			str.append("<div id='divBarCodeControl'>"+_rSlip.getPatCrNo()+"</div>");
			str.append("</div></div>");
			str.append("</div>");
			
			str.append("<div style='border: 1px solid black;'>");
			str.append("<div class='div-table-simple border'>");

			str.append("<div class='div-table-row' style='height: 40px;'>");
			
			str.append("<div class='div-table-col' id='divCRNoLabel' style='width: 42%; height: 40px;'>CR No :&nbsp;<b>");
				str.append(_rSlip.getPatCrNo());
			str.append("</b></div>");
			
			str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 33%; height: 40px;'>Aadhaar :&nbsp;<b>");
				str.append(_rSlip.getPatNationalId());
			str.append("</b></div>");
			str.append("<div class='div-table-col' id='divEpisodeDateLabel' style='width: 25%; height: 40px;'>FEE (Rs) :&nbsp;<b>");				
				str.append(_rSlip.getPatAmountCollected());
			str.append("</b></div>");
			str.append("</div>");
		}*/
		
		str.append("<div class='div-table-row' style='height: 40px;'>");
		str.append("<div class='div-table-col' id='divPatientNameLabel' style='width: 42%; height: 40px;'>Patient Name &nbsp;&nbsp;:&nbsp;");
		
		str.append("<b>");
		str.append(_rSlip.getPatFirstName().toUpperCase()+" "+_rSlip.getPatMiddleName().toUpperCase()+" "+_rSlip.getPatLastName().toUpperCase());
		str.append("</b>");
		str.append("</div>");
		str.append("<div class='div-table-col' id='divAgeLabel' style='width: 33%; height: 40px;'>Age/Sex&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<b>");
		
		
		str.append(_rSlip.getPatAge());
		str.append("/");
		str.append(_rSlip.getPatGender().substring(0,1));
		
		str.append("</b></div>");
		str.append("<div class='div-table-col' id='divCasteLabel' style='width: 25%; height: 40px;'>Category&nbsp;&nbsp;:&nbsp;<b>");
		
		
		str.append((_rSlip.getPatPrimaryCat().equalsIgnoreCase("null")|| _rSlip.getPatPrimaryCat().equalsIgnoreCase("")?"NA":_rSlip.getPatPrimaryCat()));
	
		str.append("</b></div>");
		str.append("</div>");
		if(_rSlip.getPatPrimaryCat().equalsIgnoreCase("staff"))
		{
			str.append("<div class='div-table-row' style='height: 40px;'>");
			str.append("<div class='div-table-col' id='divStaffNameLabel' style='width: 42%; height: 40px;'>Staff Name/No. &nbsp;&nbsp;:&nbsp;");
			
			str.append("<b>");
			str.append(_rSlip.getDependentName().toUpperCase());
			str.append("/");
			str.append((_rSlip.getHiddenPatIdNo()));
			str.append("</b>");
			str.append("</div>");
			str.append("<div class='div-table-col' id='divRelationLabel' style='width: 33%; height: 40px;'>Staff Relation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
			
			
			str.append(_rSlip.getPatMemRelationName().toUpperCase());
			
			
			str.append("</div>");
			str.append("<div class='div-table-col' id='divDepartmentLabel' style='width: 25%; height: 40px;'>Staff Department&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;");
			
			
			str.append(_rSlip.getPatMemDeptName().toUpperCase());
			
			
			str.append("</div>");
			
			str.append("</div>");
		}
		str.append("<div class='div-table-row' style='width: 100%;height: 40px;'>");
		str.append("<div class='div-table-col' id='divAreaLabel' style='width: 75%; height: 40px;'>Address&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<b>");
		
		
		str.append(_rSlip.getAddress1()+ _rSlip.getAddress2());
	
		str.append("</b></div>");
		str.append("<div class='div-table-col' style='width: 25%; height: 40px;'>MLC NO. : ");
		str.append(_rSlip.getMlcDetail());
		str.append("</div>");
	
		str.append("</div>");
		str.append("<div class='div-table-row ' id='divTokenLabel' style='width: 100%; height: 62px;'>");
		str.append("<div class='div-table-col'  style='width: 42%;height: 62px;'>");
		str.append("<div>Department  :&nbsp;");
		
		String[] tokenDetail=new String[4];
  		tokenDetail[0]=_rSlip.getDepartmentToBeVisited()[index]+'-'+_rSlip.getDepartmentUnit()[index];
  		int rows=1;
			String tokenSplit="";
			str.append("<b>");
				if(tokenDetail[0].length()>20)
  			{
  				counter++;
  				rows= tokenDetail[0].length()/20+((tokenDetail[0].length()%20==0)?0:1);		
  				for(int j=0;j<rows;j++){
  				
	  				if(j==rows-1){
	  					tokenSplit=tokenDetail[0].substring(j*20);
	  				}else{
	  					tokenSplit=tokenDetail[0].substring(j*20,(j+1)*20);
	  				}
	  				
	  					str.append(tokenSplit);
	  					
	  			}
	  			
	  		}	
				else if(tokenDetail[0].length()<=20)
				{
					
					str.append(tokenDetail[0]);
					
					 				
				}
				str.append("</b>");
				str.append("</div>");
				str.append("<div>OPD Days :&nbsp;<b>");
		  		tokenDetail[2]=_rSlip.getWorkingDays()[index];
		  		tokenSplit="";
		  	if(tokenDetail[2].length()>20)
			{
				counter++;
				rows= tokenDetail[2].length()/20+((tokenDetail[2].length()%20==0)?0:1);		
				for(int j=0;j<rows;j++){
				
  				if(j==rows-1){
  					tokenSplit=tokenDetail[2].substring(j*20);
  				}else{
  					tokenSplit=tokenDetail[2].substring(j*20,(j+1)*20);
  				}
  				
  					str.append(tokenSplit);
  					
  			}
  			
  		}	
			else if(tokenDetail[2].length()<=20)
			{
				str.append(tokenDetail[2]);		
				 				
			}
		  	str.append("</b></div>");
		  	/**Queue no Field*/
		 /* str.append("<div><font color=''>Queue No. :<b>");
	  	tokenDetail[3]=_rSlip.getQueNo()[index];
		if(tokenDetail[3].length()>20)
		{
			counter++;
			rows= tokenDetail[3].length()/20+((tokenDetail[3].length()%20==0)?0:1);		
			for(int j=0;j<rows;j++){
			
			if(j==rows-1){
				tokenSplit=tokenDetail[3].substring(j*20);
			}else{
				tokenSplit=tokenDetail[3].substring(j*20,(j+1)*20);
			}
			
				
				
		}
		
	}	
		else if(tokenDetail[3].length()<=20)
		{
			str.append(tokenDetail[3]);		
			 				
		}
	  	
	  	str.append("</b></font></div>");*/
		  	/***/
		  	str.append("</div>");
			str.append("<div class='div-table-col'  style='width: 33%; height: 62px;'>");
			str.append("<div>Doctor	:	");
		str.append("<b>"+_rSlip.getUnitConsultant()[index]+"</b>");
		str.append("</div>");
		str.append("<div></div>");
		str.append("</div>");
		  
		str.append("<div class='div-table-col'  style='width: 25%; height: 62px;'>");
  	str.append("<div>Room No.	:");
	str.append("<b>");
		tokenDetail[1]=_rSlip.getRoom()[index];// Commented By Adil Wasi
		tokenSplit="";
  		if(tokenDetail[1].length()>20)
  		{
			counter++;
			rows= tokenDetail[1].length()/20+((tokenDetail[1].length()%20==0)?0:1);		
			for(int j=0;j<rows;j++){
		
				if(j==rows-1){
					tokenSplit=tokenDetail[1].substring(j*20);
				}else{
					tokenSplit=tokenDetail[1].substring(j*20,(j+1)*20);
				}
			}
		}	
		else if(tokenDetail[1].length()<=20)
		{
			str.append(tokenDetail[1]);		
		}
	str.append("</b>");
	str.append("</div>");
	str.append("</div>");
	/******************************************************************************************************************************************/
		str.append("</div>");
		/*str.append("<div class='div-table-simple border'>");
		str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>ALERTS");
		str.append("</div>");
		str.append("</br>");
		
		str.append("<div class='div-table-col'  style='width:100%;height: 40px;'>DIAGNOSIS");
		str.append("</div>");
		str.append("</div>");*/
		/************************************<font style="font-size:20px"><b>सहमति</b></font> ************/
		
		str.append("<div class='div-table-simple border'>");
		str.append("<div class='div-table-row fontBold alignCenter'>"+
	"<div class='div-table-col fontBold alignCenter' style='width: 100%;font-size:10px;'>"+"<font style='font-size:15px'>&#2360;&#2361;&#2350;&#2340;&#2367; &#2404;</font><br>"+//for sahamatti |
	//"मैं ________ अपनी इच्छा से बिना किसी दबाव के AIIMS PATNA मै अपना/अपने रोगी का इलाज़/जांच कराने को इस जानकारी के साथ तैयार हूं | मुझे  इलाज़ और जांच के खतरे  अच्छी  तरह से समझा दिये गये हैं और उन खतरों के बावज़ूद मैं/मेरे रोगी का प्रस्तावित इलाज़  ऍव  जांच अपनी इच्छा से  बिना दबाव के कराने को तैयार हूं|"+
	"&#2350;&#2376;&#2306; &#95;&#95;&#95;&#95;&#95;&#95;&#95;&#95; &#2309;&#2346;&#2344;&#2368; &#2311;&#2330;&#2381;&#2331;&#2366; &#2360;&#2375; &#2348;&#2367;&#2344;&#2366; &#2325;&#2367;&#2360;&#2368; &#2342;&#2348;&#2366;&#2357; &#2325;&#2375; AIIMS PATNA "+
	"&#2350;&#2376; &#2309;&#2346;&#2344;&#2366;&#47;&#2309;&#2346;&#2344;&#2375; &#2352;&#2379;&#2327;&#2368; &#2325;&#2366; &#2311;&#2354;&#2366;&#2332;&#2364;&#47;&#2332;&#2366;&#2306;&#2330; &#2325;&#2352;&#2366;&#2344;&#2375; &#2325;&#2379; &#2311;&#2360; &#2332;&#2366;&#2344;&#2325;&#2366;&#2352;&#2368; &#2325;&#2375; &#2360;&#2366;&#2341; &#2340;&#2376;&#2351;&#2366;&#2352; &#2361;&#2370;&#2306; &#2404; &#2350;&#2369;&#2333;&#2375;  &#2311;&#2354;&#2366;&#2332;&#2364; &#2324;&#2352; &#2332;&#2366;&#2306;&#2330; &#2325;&#2375; &#2326;&#2340;&#2352;&#2375;  &#2309;&#2330;&#2381;&#2331;&#2368;  &#2340;&#2352;&#2361; &#2360;&#2375; &#2360;&#2350;&#2333;&#2366; &#2342;&#2367;&#2351;&#2375; &#2327;&#2351;&#2375; &#2361;&#2376;&#2306; &#2324;&#2352; &#2313;&#2344; &#2326;&#2340;&#2352;&#2379;&#2306; &#2325;&#2375; &#2348;&#2366;&#2357;&#2395;&#2370;&#2342; &#2350;&#2376;&#2306;&#47;&#2350;&#2375;&#2352;&#2375; &#2352;&#2379;&#2327;&#2368; &#2325;&#2366; &#2346;&#2381;&#2352;&#2360;&#2381;&#2340;&#2366;&#2357;&#2367;&#2340; &#2311;&#2354;&#2366;&#2332;&#2364;  &#2317;&#2357;  &#2332;&#2366;&#2306;&#2330; &#2309;&#2346;&#2344;&#2368; &#2311;&#2330;&#2381;&#2331;&#2366; &#2360;&#2375;  &#2348;&#2367;&#2344;&#2366; &#2342;&#2348;&#2366;&#2357; &#2325;&#2375; &#2325;&#2352;&#2366;&#2344;&#2375; &#2325;&#2379; &#2340;&#2376;&#2351;&#2366;&#2352; &#2361;&#2370;&#2306;&#2404;"+
	"<br>"+"<div class='div-table-simple border'>"
	+"<div class='div-table-row fontBold alignCenter'>"
	+"<div class='div-table-col fontBold alignLeft' style='width: 50%; border:0px;'>Date: "+_rSlip.getEpisodeDate()[index]+"</div>"
	+"<div class='div-table-col fontBold alignRight' style='width: 50%; border:0px;'>________________</div>"
	+"</div>"
	+"<div class='div-table-row fontBold alignCenter'>"
	+"<div class='div-table-col fontBold alignLeft' style='width: 50%; border:0px;'>Place: Patna</div>"
	+"<div class='div-table-col fontBold alignRight' style='width: 50%; border:0px;'>Signature</div>"
	+"</div>"
	+"</div></div>"
	+"</div>");
		/***********************************************/
		
  			str.append("<div class='div-table-row' style='height: 37px;'>");
  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank1' style='width: 25%; height: 37px;border:0px;'>");
  			str.append("</div>");
  			
  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 50%; height: 37px;border:0px;'>");
  			str.append("</div>");
				
  			str.append("<div class='div-table-col fontBold alignCenter' id='divBlank2' style='width: 25%; height: 37px;border:0px;'>");
  			str.append("</div>");
  			str.append("</div>");
			
  			str.append("<div class='div-table-row' style='height: 425px;'>");
  			str.append("<div class='div-table-col' id='divBlank1' style='width: 25%; height: 425px;border:0px;'></div>");
  			str.append("<div class='div-table-col' id='divBlank2' style='width: 50%; height: 425px;border:0px;'></div>");
  			str.append("<div class='div-table-col' id='divBlank2' style='width: 25%; height: 425px;border:0px;'></div>");
  			str.append("</div>");
  			str.append("</div></div></div>");
  			
			if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_CENTER))
				align="Center";
			else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_LEFT))
				align="Left";
			else if(_rSlip.getAlignment()[index].equals(RegistrationConfig.DISCLAIMER_ALIGN_RIGHT))
				align="Right";
			else
				align="Center";

			str.append("<div class='div-table-simple border'>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col alignCenter' style='width: 100%;border:0px;font-size:10px;'>");
			str.append(RegistrationConfig.medicinalSideEffects_Hindi+" (0612-2451207) (Toll Free No. 18001803024)</div></div>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col alignCenter' style='width: 100%;border:0px;font-size:10px;'>");  
			str.append("In Case of drug side effects. Please contact Phone no. (0612-2451207) (Toll Free No. 18001803024)</div></div>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col alignCenter' style='width: 100%;border:0px;font-size:10px;'>PHULWARISHARIF, PATNA BIHAR-801507</div></div>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col  alignCenter' style='width: 100%;border:0px;font-size:10px;'>AIIMS OFFICE CONTACT NUMBER - 0612-2451044 | WEBSITE:-www.aiimspatna.org</div></div>");
			str.append("<div class='div-table-row'>");
			str.append("<div class='div-table-col  alignCenter' style='width: 100%;border:0px;font-size:10px;'><b>"+RegistrationConfig.smokingIsInjuriousToHealth_Hindi+"</b> SMOKING IS PROHIBITED IN HOSPITAL PREMISES</div></div></div>");
	} 
  return str.toString();
 // return escapeHtml3(str.toString()).replaceAll("'", "&#039;");
}

}
//end class
