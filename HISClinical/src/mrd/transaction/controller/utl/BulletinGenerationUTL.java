package mrd.transaction.controller.utl;

import ehr.EHRConfig;
import emr.dataentry.spp.business.bo.UniPagePrescriptionBO;
import emr.dataentry.spp.presentation.fb.UniPagePrescriptionFB;
import emr.dataentry.spp.presentation.util.UniPagePrescriptionUTL;
import emr.vo.EHR_PatientProfileDtlVO;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerDATA;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.vo.UserVO;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.BulletinGenerationDATA;
import mrd.transaction.controller.fb.BulletinGeneartionFB;
import mrd.vo.BulletinGenerationVO;
import mrd.vo.BulletinHeadDataVO;
import mrd.vo.BulletinHeadVO;

public class BulletinGenerationUTL extends ControllerUTIL
{

	public static boolean getPrintDataForBulletin(BulletinGeneartionFB fb, HttpServletRequest _request,HttpServletResponse _response)
	{
		boolean isTrue = true;
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		String strHtmlCode = "";
		try
		{

			UserVO userVO = getUserVO(_request);
			// MRDHeadVO headVO = new MRDHeadVO();

			BulletinGenerationVO bulletinVO = new BulletinGenerationVO();
			bulletinVO.setBulletinId(fb.getBulletinType());
			bulletinVO.setSelectedMonth(fb.getSelectedMonth());
			bulletinVO.setSelectedMonthNumber(fb.getSelectedMonthNumber());
			bulletinVO.setSelectedYear(fb.getSelectedYear());
			bulletinVO.setHospitalCode(userVO.getHospitalCode());
			String Selecteddate = bulletinVO.getSelectedMonth();
			if (Selecteddate.equalsIgnoreCase("January"))
			{
				bulletinVO.setBulletinDate("01-" + fb.getSelectedYear());
			}
			else if (Selecteddate.equalsIgnoreCase("February"))
			{
				bulletinVO.setBulletinDate("02-" + fb.getSelectedYear());
			}
			else if (Selecteddate.equalsIgnoreCase("March"))
			{
				bulletinVO.setBulletinDate("03-" + fb.getSelectedYear());
			}
			else if (Selecteddate.equalsIgnoreCase("April"))
			{
				bulletinVO.setBulletinDate("04-" + fb.getSelectedYear());
			}
			else if (Selecteddate.equalsIgnoreCase("May"))
			{
				bulletinVO.setBulletinDate("05-" + fb.getSelectedYear());
			}
			else if (Selecteddate.equalsIgnoreCase("June"))
			{
				bulletinVO.setBulletinDate("06-" + fb.getSelectedYear());
			}
			else if (Selecteddate.equalsIgnoreCase("July"))
			{
				bulletinVO.setBulletinDate("07-" + fb.getSelectedYear());
			}
			else if (Selecteddate.equalsIgnoreCase("August"))
			{
				bulletinVO.setBulletinDate("08-" + fb.getSelectedYear());
			}
			else if (Selecteddate.equalsIgnoreCase("September"))
			{
				bulletinVO.setBulletinDate("09-" + fb.getSelectedYear());
			}
			else if (Selecteddate.equalsIgnoreCase("October"))
			{
				bulletinVO.setBulletinDate("10-" + fb.getSelectedYear());
			}
			else if (Selecteddate.equalsIgnoreCase("November"))
			{
				bulletinVO.setBulletinDate("11-" + fb.getSelectedYear());
			}
			else if (Selecteddate.equalsIgnoreCase("December"))
			{
				bulletinVO.setBulletinDate("12-" + fb.getSelectedYear());
			}
			
			bulletinVO.setDuration(fb.getSelectedMonth().substring(0, 3) + "-" + fb.getSelectedYear());

			// FEtching Bulleting Headings
			List<BulletinHeadVO> lstBulletinHeads = null;
			List<BulletinHeadVO> dataVO = null;
			lstBulletinHeads = BulletinGenerationDATA.getMRDBulletinTemplatePrintList(bulletinVO, userVO);
			bulletinVO.setHeadings(lstBulletinHeads);
			WebUTIL.setAttributeInSession(_request, MrdConfig.BULLETIN_TEMP_LIST, lstBulletinHeads);

			// Iterate through Headings
			for (BulletinHeadVO headVO : lstBulletinHeads)
			{
				// Execute Query and fecth Data
				List<BulletinHeadDataVO> lstHeadData = null;
				String query = headVO.getDataquery();
				if (query != null && !query.equals(""))
				{
					String finalQuery = query.replaceAll("#HOS_CODE#", bulletinVO.getHospitalCode());
					finalQuery = finalQuery.replaceAll("#STAT_DATE#", bulletinVO.getDuration());
					finalQuery = finalQuery.replaceAll("#STAT_YEAR#", bulletinVO.getSelectedYear());
					finalQuery = finalQuery.replaceAll("#STAT_MON#", bulletinVO.getSelectedMonthNumber());

					System.out.println("final Query:" + finalQuery);
					headVO.setDataquery(finalQuery);

					lstHeadData = BulletinGenerationDATA.getDataFromQuery(finalQuery, userVO);
				} else
					lstHeadData = new ArrayList<BulletinHeadDataVO>();
				headVO.setHeadData(lstHeadData);
				String ftlvalue = headVO.getFtl();
				// Generation of Head HTML through FTL
				if(!headVO.getIsPageBreakAfter().equals("0"))
				{
					String ht = headVO.getIsPageBreakAfter();
					ftlvalue += "<table width='100%'><tr><td width='100%' height="+ht+"></td></tr></table>";
					System.out.println(ftlvalue);
				}	
					
				
				if (ftlvalue != null && !ftlvalue.trim().equals(""))
				{
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("voBulletin", bulletinVO);
					data.put("voHeading", headVO);
					data.put("voHeadData", lstHeadData);

					

					Template template = null;
					StringTemplateLoader stringLoader = new StringTemplateLoader();
					String firstTemplate = "";
					//System.out.println(ftlvalue);
					stringLoader.putTemplate(firstTemplate, ftlvalue);
					Configuration cfg1 = new Configuration();
					cfg1.setTemplateLoader(stringLoader);
					//System.out.println(stringLoader);
					template = cfg1.getTemplate(firstTemplate);

					StringWriter out = new StringWriter();
					template.process(data, out);
					out.flush();
					strHtmlCode += out.toString();
					//System.out.println(strHtmlCode);
					headVO.setDisplayHTML(strHtmlCode);
				
				} else
				{
					headVO.setDisplayHTML(null);
			
				}
					//WebUTIL.setAttributeInSession(_request, "mySession", headVO);
				
				
			}
			System.out.println(strHtmlCode);
			bulletinVO.setHtmlPreview(strHtmlCode);
			BulletinGenerationDATA.insertHtmlData(bulletinVO);
			
	//Insert in hbut_bulletin_dtl to save log //
			
			
			if(strHtmlCode != null && !strHtmlCode.equals(""))
			{
				ByteArrayOutputStream baosPDF = HTMLToPDFUTIL.convertHtmlToPDFDirect(_request, strHtmlCode);
				HisFileControlUtil hfcu = new HisFileControlUtil("abc.pdf", "F:\\", "/root/");
				hfcu.setFileContent(baosPDF.toByteArray());
				hfcu.saveFile();
				
				bulletinVO.setBulletinDataPdf(baosPDF.toByteArray());
				bulletinVO.setHtmlPreview(strHtmlCode);
				 session.setAttribute(MrdConfig.PDF_FOR_BULLETIN, bulletinVO.getBulletinDataPdf());
				/*getPdfDoc(_request,bulletinVO,_response);
						objStatus.add(Status.TRANSINPROCESS, "Data Saved Successfully", "");*/
						_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			}
	
		} 
		catch (HisRecordNotFoundException e)
		{
			isTrue = false;
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
		} catch (HisDataAccessException e)
		{
			isTrue = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e)
		{
			isTrue = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		} catch (HisException e)
		{
			isTrue = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		} catch (Exception e)
		{
			isTrue = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		} finally
		{
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return isTrue;
	}

	
	public static void getPdfDoc(HttpServletRequest request,HttpServletResponse response) {
		Status objStatus = new Status();
		InputStream inputStream = null;
		BufferedOutputStream bos = null;
		HttpSession session = request.getSession();
		
		
		try
		{
	   byte[] getDoc =(byte[]) session.getAttribute(MrdConfig.PDF_FOR_BULLETIN);
		
		//byte[] getDoc =  profileDtlVO.getProfileDataPdf();
		response.setContentType("application/pdf");
		OutputStream os = response.getOutputStream();
		bos = new BufferedOutputStream(os);
		inputStream = new ByteArrayInputStream(getDoc);
		int c;
		while ((c = inputStream.read()) != -1)
		{
			os.write(c);
		}    
		
		
}
catch (HisRecordNotFoundException e)
{
	e.printStackTrace();
	objStatus.add(Status.LIST, e.getMessage(), "");
}
catch (HisDataAccessException e)
{
	e.printStackTrace();
	objStatus.add(Status.ERROR_DA, e.getMessage(), "");
}
catch (HisApplicationExecutionException e)
{
	e.printStackTrace();
	objStatus.add(Status.ERROR_AE, e.getMessage(), "");
}
catch (HisException e)
{
	e.printStackTrace();
	objStatus.add(Status.ERROR, e.getMessage(), "");
}

catch (Exception e)
{
	e.printStackTrace();
	String msg="<B>This file does not exist:: Please Contact Administrator</B>";
	byte[] bytes=msg.getBytes();
	response.setHeader("Pragma", "no-cache");
	try
	{
		bos.write(bytes, 0, bytes.length);
	}catch(Exception ee)
	{
	}
	
}
finally
{
	try
	{
		if(inputStream!=null) inputStream.close();
		response.getOutputStream().flush();
		if(bos!=null)	bos.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

}

	
	
	
	public static void getBulletinelist(BulletinGeneartionFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map mp = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);

			List dateAndCrNoFormat = ControllerDATA.getSystemDateAndFormat(getUserVO(_request));
			Date dateObj = (Date) dateAndCrNoFormat.get(2);

			// SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
			WebUTIL.getSession(_request).setAttribute(Config.SYSDATEOBJECT, dateObj);

			List<Entry> lstBulletin = new ArrayList<Entry>();

			mp = BulletinGenerationDATA.getBulletinelist(userVO);
			lstBulletin = (List<Entry>) mp.get(MrdConfig.BULLETIN_TYPE);
			
			if (lstBulletin == null || lstBulletin.size() == 0)
			{
				throw new HisRecordNotFoundException("No Bulletins Found for SeatId");
			}

			WebUTIL.setAttributeInSession(_request, MrdConfig.BULLETIN_TYPE, lstBulletin);
			// objStatus.add(Status.NEW);
		} catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
		} catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		} catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		} catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		} finally
		{
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}

}