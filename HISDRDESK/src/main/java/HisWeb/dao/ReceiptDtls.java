package HisWeb.dao;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import HisWeb.util.pdfGenerationUtil;
/*import billing.PrintBO;
import billing.PrintHLP;
import billing.PrintVO;*/

import java.awt.Color;
import java.io.*;
import java.net.MalformedURLException;

import com.lowagie.*;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class ReceiptDtls {
	
	public static String getReceiptDtls(String billNo, String hospcode,String crNo, String receiptNo) throws Exception
	{
		WebRowSet ws1 = null, ws2 = null;
		PrintVO voObj = null;
		voObj = new PrintVO();
		PrintBO boObj = null;
		boObj = new PrintBO();
		voObj.setStrReceiptNo(receiptNo);
		voObj.setStrBillNo(billNo);
		voObj.setStrHospCode(hospcode);
		
		boObj.OPD_SERVICES(voObj);
		
		
		ws1 = voObj.getGblWs1();
		ws2 = voObj.getGblWs2();
		
		pdfGenerationUtil util=new pdfGenerationUtil();
		
		String urlftp=util.uploadPdfToFTP(ws1, ws2 ,billNo);
		System.out.println("urlftp======"+urlftp);
		return urlftp;
	} 

}