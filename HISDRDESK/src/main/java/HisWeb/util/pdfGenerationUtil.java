package HisWeb.util;

import hisglobal.utility.HisUtil;

import java.io.BufferedOutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.sql.rowset.WebRowSet;

import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class pdfGenerationUtil {
	
	public static final String DEST = "results/tables/cell_method.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
    public static final int FONTSIZE=7;
    BufferedOutputStream bos = null ;
    URL urlftp=null;
	public String uploadPdfToFTP(WebRowSet ws1,WebRowSet ws2, String billNo) throws JSONException
	{
		JSONObject jsonObj = new JSONObject();
		 try {

             // OutputStream file = new FileOutputStream(new File("ftp://ehmsapftp:fTpASmHE@10.226.1.119/ftpserver"));
              urlftp =new URL("ftp://ehmsapftp:fTpASmHE@10.226.1.119/ftpserver/billing"+"/"+billNo+".pdf");
              System.out.println(urlftp);
  			 URLConnection urlc= urlftp.openConnection();
  			  int i=1;
  			  String pataccountNo=null;
  			  Double totalAmt=0.00 , disAmt=0.00;
  			HisUtil hisutil = null;
  			hisutil = new HisUtil("billing", "PrintHLP");
  			 
  			bos=new BufferedOutputStream(urlc.getOutputStream());
	          Document document = new Document();
	          PdfWriter.getInstance(document, bos);
	          HisUtil hisUtil=new HisUtil("Global","ReportUtil");
	          System.out.println("header name ======= "+hisUtil.getHospitalHeaderForApp("33101",1,"HTML"));
	          String hospitalDtls=hisUtil.getHospitalHeaderForApp("33101",1,"HTML");
	          		float[] columnWidths = {2, 4, 3, 4, 4, 4};
			
	                 Font f = new Font(FontFamily.HELVETICA, FONTSIZE, Font.NORMAL, GrayColor.BLACK);
	                 
	                 Font f1 = new Font(FontFamily.HELVETICA, 9, Font.BOLD, GrayColor.BLACK);
			     	 PdfPTable table=new PdfPTable(columnWidths);
			     	 table.setWidthPercentage(100);
			     	 
					 //table.getDefaultCell().setBorderColor(BaseColor.WHITE);
					 PdfPCell cell = new PdfPCell (new Phrase(hospitalDtls, f1));
					  //cell.setBorderColor(BaseColor.BLACK);
					  cell.setBorderColor(BaseColor.WHITE);
				      cell.setColspan (6);
				      cell.setHorizontalAlignment (Element.ALIGN_CENTER);
				      cell.setPadding (10.0f);
				      cell.setSpaceCharRatio(10);
				      //cell.setBackgroundColor (new BaseColor (140, 221, 8));					               
				      table.addCell(cell);	
				      
				      if(ws1.size() > 0)
				      {
				    	  ////Added by warish 03-01-18 add semicolon
				    	  ws1.first();
				    	  table.addCell(getNormalCell("Cr No. 	           :", null, FONTSIZE));
					      table.addCell(getNormalCell(ws1.getString(5), null, FONTSIZE));
					      table.addCell(getNormalCell4("DATE&TIME     :", null, FONTSIZE));
	                      table.addCell(getNormalCell(ws1.getString(2), null, FONTSIZE));
					      table.addCell(getNormalCell4("BILL No.                :", null, FONTSIZE));
					      table.addCell(getNormalCell(ws1.getString(1), null, FONTSIZE));
					      //table.setSpacingBefore(3.0f);       // Space Before table starts, like margin-top in CSS
					      //table.setSpacingAfter(3.0f);        // Space After table starts, like margin-Bottom in CSS		
					      
					      table.addCell(getNormalCell("NAME            :", null, FONTSIZE));
					      table.addCell(getNormalCell(ws1.getString(3).replace("^", "#").split("#")[0], null, FONTSIZE));
					      table.addCell(getNormalCell4("AGE/SEX          :", null, FONTSIZE));
	                      table.addCell(getNormalCell(ws1.getString(3).replace("^", "#").split("#")[1], null, FONTSIZE));
					      table.addCell(getNormalCell4("DEPARTMENT     :", null, FONTSIZE));
					      table.addCell(getNormalCell(ws1.getString(11), null, FONTSIZE));
					      
					      table.addCell(getNormalCell("CATEGORY  :", null, FONTSIZE));
					      table.addCell(getNormalCell(ws1.getString(4), null, FONTSIZE));
					      table.addCell(getNormalCell4("ORG.                 :", null, FONTSIZE));
	                      table.addCell(getNormalCell("-----", null, FONTSIZE));
					      table.addCell(getNormalCell4("SERVICE              :", null, FONTSIZE));
					      table.addCell(getNormalCell(ws1.getString(10), null, FONTSIZE));
					      
					      pataccountNo=ws1.getString(20);
				    	 
				      }
				      
				      Paragraph chunck3= new Paragraph ("____________________________________________________________________________________________________________________________________", f);
					     
				      float[] columnWidths1 = {(float) 0.5, 4, 1, 1, 1, 1 , 1};
				      PdfPTable table1=new PdfPTable(columnWidths1);
				      
				     /* table1.setWidthPercentage(100);
				      PdfPCell cell5 = new PdfPCell (new Phrase(pataccountNo, f1));
				      cell5.setBorderColor(BaseColor.WHITE);*/
				     	
				      
				      table1.setWidthPercentage(100);
				      table1.addCell(getNormalCell2("S.No.", null, FONTSIZE));
				      table1.addCell(getNormalCell2("PROCEDURE/INV./SERVICE NAME", null, FONTSIZE));
				      table1.addCell(getNormalCell2("LOCATION", null, FONTSIZE));
                      table1.addCell(getNormalCell3("RATE", null, FONTSIZE));
				      table1.addCell(getNormalCell3("QTY", null, FONTSIZE));
				      table1.addCell(getNormalCell3("DISC.", null, FONTSIZE));
				      table1.addCell(getNormalCell3("AMOUNT", null, FONTSIZE));
				      
				    
				      
				      while(ws2.next())
				      {
				    	  
				    	  table1.addCell(getNormalCell(String.valueOf(i), null, FONTSIZE));
					      table1.addCell(getNormalCell(ws2.getString(1).split("@")[0], null, FONTSIZE));
					      table1.addCell(getNormalCell("---", null, FONTSIZE));
	                      table1.addCell(getNormalCell1(ws2.getString(17), null, FONTSIZE));
					      table1.addCell(getNormalCell1(ws2.getString(2), null, FONTSIZE));
					      table1.addCell(getNormalCell1(ws2.getString(3), null, FONTSIZE));
					      table1.addCell(getNormalCell1(ws2.getString(5), null, FONTSIZE));
					      i++;
					      totalAmt=totalAmt+Double.parseDouble(ws2.getString(5));
					      disAmt=disAmt+Double.parseDouble(ws2.getString(3));
				      }
				      
				      
				      PdfPCell cell1 = new PdfPCell (new Phrase("TOTAL AMOUNT:"+totalAmt, f));
					  //cell1.setBorderColor(BaseColor.WHITE);
					  cell1.setColspan (7);
					  cell1.setHorizontalAlignment (Element.ALIGN_RIGHT);
					  cell1.setPadding (2.0f);
					  cell1.setPaddingRight(2.0f);
					  cell1.setSpaceCharRatio(10);
					  //cell1.setBackgroundColor (new BaseColor (140, 221, 8));	
					  cell1.setBorderColor(BaseColor.WHITE);
					  table1.addCell(cell1);
					      
					  //Paragraph p6= new Paragraph("________________________________________________________________________________________________________________\n\n", f);
					  Paragraph Chunk1  = new Paragraph("____________________________________________________________________________________________________________________________________", f);
					  float[] columnWidths2 = {(float) 5};
					  PdfPTable table2=new PdfPTable(columnWidths2);
					  
					  PdfPCell cell2 = new PdfPCell (new Phrase("BILLED AMT:"+totalAmt, f));
					  //cell2.setBorderColor(BaseColor.WHITE);
					  cell2.setColspan (7);
					  cell2.setHorizontalAlignment (Element.ALIGN_RIGHT);
					  cell2.setPadding (3.0f);
					  cell2.setSpaceCharRatio(10);
					  //cell2.setBackgroundColor (new BaseColor (140, 221, 8));	
					  cell2.setBorderColor(BaseColor.WHITE);
					  table2.addCell(cell2);
					  
					  PdfPCell cell3 = new PdfPCell (new Phrase("CONCESSION AMT:"+disAmt, f));
					  //cell3.setBorderColor(BaseColor.WHITE);
					  cell3.setColspan (7);
					  cell3.setHorizontalAlignment (Element.ALIGN_RIGHT);
					  cell3.setPadding (2.0f);
					  cell3.setSpaceCharRatio(10);
					  //cell3.setBackgroundColor (new BaseColor (140, 221, 8));
					  cell3.setBorderColor(BaseColor.WHITE);
					  table2.addCell(cell3);
					  
					  PdfPCell cell4 = new PdfPCell (new Phrase("COLLECTED:"+totalAmt, f));
					  //cell4.setBorderColor(BaseColor.WHITE);
					  cell4.setColspan (7);
					  cell4.setHorizontalAlignment (Element.ALIGN_RIGHT);
					  cell4.setPadding (2.0f);
					  cell4.setSpaceCharRatio(10);
					  //cell4.setBackgroundColor (new BaseColor (140, 221, 8));
					  cell4.setBorderColor(BaseColor.WHITE);
					  table2.addCell(cell4);
					  
					  Paragraph p2= new Paragraph("RUPEES (IN WORD) :"+hisutil.getAmountStr(String.valueOf(totalAmt))+"\n",f);  
					  Paragraph p3= new Paragraph("NOTE : AMOUNT,PATIENT SHARE AND CLIENT SHARE ARE IN RS.\n",f);
					  Paragraph p4= new Paragraph("MODE OF PAYMENT: WALLET \n",f);
					  String s=pataccountNo.substring(11,15);                               //Added by warish 03-01-18
					  String newCRNO="XXXXXXXXXXX"+s;
					  Paragraph p5= new Paragraph("WALLET DETAILS :"+newCRNO+"\n",f);
					 
				      
					  System.out.println("Document oeping start.......");
					  System.out.println(document.toString());
					  
					  //Now Insert Every Thing Into PDF Document
				         document.open();//PDF document opened........	
				         System.out.println("start  writnig ...........");
				         document.add(Chunk.NEWLINE);   //Something like in HTML :-)

		                    //document.add(new Paragraph("Dear Java4s.com"));
			                //document.add(new Paragraph("Document Generated On - "+new Date().toString()));	

							document.add(table);
							//document.add(Chunk.NEWLINE); 
							
							document.add(chunck3);
							
							document.add(table1);
							//document.add(Chunk.NEWLINE); 
							//document.add(chunk);
							//document.add(chunk1);
							
							document.add(Chunk1);
							
							document.add(table2);
							document.add(p2);
							
							//document.add(Chunk.NEWLINE);
							
							document.add(p3);
							document.add(p4);
							document.add(p5);
							document.add(Chunk.NEWLINE);   //Something like in HTML :-)							    

		       				document.newPage();            //Opened new page
							          //In the new page we are going to add list

				         document.close();
				         //file.close();
				         jsonObj.put("FTPURL", urlftp.toString());
		            System.out.println("Pdf created successfully..");
				      
		 } catch (Exception e) {
			 jsonObj.put("FTPURL", "Something Went Wrong");
	            e.printStackTrace();
	        }
		
		return jsonObj.toString();
	}
	   public static PdfPCell getNormalCell(String string, String language, float size)
	            throws Exception {
	        if(string != null && "".equals(string)){
	            return new PdfPCell();
	        }
	        Font f  = getFontForThisLanguage(language);
	        if(size < 0) {
	            f.setColor(BaseColor.RED);
	            //size = -size;
	        }
	        f.setSize(size);
	        PdfPCell cell = new PdfPCell(new Phrase(string, f));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorderColor(BaseColor.WHITE);
	        return cell;
	    }
	   
	   public static PdfPCell getNormalCell4(String string, String language, float size)  ////Added by warish 03-01-18to reduce spaces tablle1
	            throws Exception {
	        if(string != null && "".equals(string)){
	            return new PdfPCell();
	        }
	        Font f  = getFontForThisLanguage(language);
	        if(size < 0) {
	            f.setColor(BaseColor.RED);
	            //size = -size;
	        }
	        f.setSize(size);
	        PdfPCell cell = new PdfPCell(new Phrase(string, f));
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cell.setBorderColor(BaseColor.WHITE);
	        return cell;
	    }
	   public static PdfPCell getNormalCell1(String string, String language, float size)        //Added by warish 03-01-18fetch value in rightAlign
	            throws Exception {
	        if(string != null && "".equals(string)){
	            return new PdfPCell();
	        }
	        Font f  = getFontForThisLanguage(language);
	        if(size < 0) {
	            f.setColor(BaseColor.RED);
	            size = -size;
	        }
	        f.setSize(size);
	       
	        PdfPCell cell = new PdfPCell(new Phrase(string, f));
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cell.setBorderColor(BaseColor.WHITE);
	        return cell;
	    }
	   public static PdfPCell getNormalCell2(String string, String language, float size)
	            throws Exception {
	        if(string != null && "".equals(string)){
	            return new PdfPCell();
	        }
	        Font f1 = new Font(FontFamily.HELVETICA, 9, Font.BOLD, GrayColor.BLACK);
	        if(size < 0) {
	            f1.setColor(BaseColor.RED);
	            size = -size;
	        }
	        f1.setSize(size);
	       
	        PdfPCell cell = new PdfPCell(new Phrase(string, f1));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorderColor(BaseColor.WHITE);
	        return cell;
	    }
	   
	   public static PdfPCell getNormalCell3(String string, String language, float size)          //Added by warish 03-01-18 bold heading in table 2
	            throws Exception {
	        if(string != null && "".equals(string)){
	            return new PdfPCell();
	        }
	        Font f1 = new Font(FontFamily.HELVETICA, 9, Font.BOLD, GrayColor.BLACK);
	        if(size < 0) {
	            f1.setColor(BaseColor.RED);
	            size = -size;
	        }
	        f1.setSize(size);
	       
	        PdfPCell cell = new PdfPCell(new Phrase(string, f1));
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cell.setBorderColor(BaseColor.WHITE);
	        return cell;
	    }
	   public static Font getFontForThisLanguage(String language) {
	        if ("czech".equals(language)) {
	            return FontFactory.getFont(FONT, "Cp1250", true);
	        }
	        if ("greek".equals(language)) {
	            return FontFactory.getFont(FONT, "Cp1253", true);
	        }
	        return FontFactory.getFont(FONT, null, true);
	    }
	    
	    
	

}
