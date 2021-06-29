package emr.dataview.spp.utl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ICC_Profile;
import com.itextpdf.text.pdf.PdfAConformanceLevel;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class SamplePrescriptionToPDFA2 {

	public static final String DEST = "D:\\EHR_Standards\\PDFA2\\sample_prescription.pdf";
	public static final String LOGO = "D:\\EHR_Standards\\PDFA2\\AIIMS_Patna_Logo.jpg";
    public static final String FONT = "D:\\EHR_Standards\\PDFA2\\OpenSans-Regular.ttf";
    public static final String ICM = "D:\\EHR_Standards\\PDFA2\\sRGB_CS_profile.icm";
    
    public void convertPrescriptionToPDFA2(String dest)throws IOException, DocumentException{
    	Document document = new Document(PageSize.A4, 15, 15, 30, 20);
    	
        PdfAWriter writer = PdfAWriter.getInstance(document, new FileOutputStream(dest), PdfAConformanceLevel.PDF_A_2A);
        writer.createXmpMetadata();
        writer.setTagged();
        
     // add header and footer
        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
        writer.setPageEvent(event);
        
        document.open();
        document.addLanguage("en-us");
        File file = new File(ICM);
        ICC_Profile icc = ICC_Profile
                .getInstance(new FileInputStream(file));
        writer.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", icc);
        
        
        

        
        Font fontHEAD = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.BOLD);
        Font font12BOLD = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12, Font.BOLD);
        Font font10 = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
        Font font8 = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 8);
        Font font8BOLD = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 8, Font.BOLD); 
        
        float[] columnWidths = { 20f, 80f };
        PdfPTable table = new PdfPTable(columnWidths);

        PdfPCell defaultCell = table.getDefaultCell();
        defaultCell.setPhrase(new Phrase("",font10)); 

        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        

        Image logoImage = Image.getInstance(LOGO);
        logoImage.setAccessibleAttribute(PdfName.ALT, new PdfString("Logo"));
        PdfPCell cell1 = new PdfPCell(logoImage);
        PdfPCell cell2 = new PdfPCell(new Phrase("ALL INDIA INSTITUTE OF MEDICAL SCIENCES PATNA",fontHEAD));

        cell1.setBorder(0);
        cell2.setBorder(0);

        cell1.setIndent(20);
        cell2.setIndent(20);

        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell(cell1);
        table.addCell(cell2);

        document.add(table);

        
        Paragraph p = new Paragraph("Phulwarisharif", font10);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);

        
        p = new Paragraph("OPD Prescription", font12BOLD);
        p.setAlignment(Element.ALIGN_CENTER);
        
    
        LineSeparator line = new LineSeparator();
        line.setOffset(-15);
        p.add(line);
        document.add(p);
        
       
        
        
        //////////////////////////////////////////////PATIENT DETAILS//////////////////////////////////////////////
        
        float[] columnWidths2 = { 50f, 50f };
        table = new PdfPTable(columnWidths2);
        defaultCell.setPhrase(new Phrase("",font10)); 

        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        Phrase phraseLabel=new Phrase("Patient Name : ",font8);
        phraseLabel.add(new Chunk("TEST",font8BOLD));
        cell1 = new PdfPCell(phraseLabel);
        Phrase phraseContent=new Phrase("CR No. : ",font8);
        phraseContent.add(new Chunk("109111800014332",font8BOLD));
        cell2 = new PdfPCell(phraseContent);

        cell1.setBorder(0);
        cell2.setBorder(0);

        cell1.setIndent(10);
        cell2.setIndent(10);

        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell1);
        table.addCell(cell2);

        
        phraseLabel=new Phrase("Age / Gender :",font8);
        phraseLabel.add(new Chunk("25 Yr/Male",font8BOLD));
        cell1 = new PdfPCell(phraseLabel);
        phraseContent=new Phrase("Primary Category : ",font8);
        phraseContent.add(new Chunk("General",font8BOLD));
        cell2 = new PdfPCell(phraseContent);
        
        cell1.setBorder(0);
        cell2.setBorder(0);

        cell1.setIndent(10);
        cell2.setIndent(10);

        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell(cell1);
        table.addCell(cell2);
        
        phraseLabel=new Phrase("Father/Spouce Name : ",font8);
        phraseLabel.add(new Chunk("Asdasdad",font8BOLD));
        cell1 = new PdfPCell(phraseLabel);
        phraseContent=new Phrase("Department(Unit) : ",font8);
        phraseContent.add(new Chunk("General Medicine(Dr Anjani Kumar)",font8BOLD));
        cell2 = new PdfPCell(phraseContent);

        
        cell1.setBorder(0);
        cell2.setBorder(0);

        cell1.setIndent(10);
        cell2.setIndent(10);

        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell(cell1);
        table.addCell(cell2);
        
        phraseLabel=new Phrase("Visit Date : ",font8);
        phraseLabel.add(new Chunk("11-Oct-2018",font8BOLD));
        cell1 = new PdfPCell(phraseLabel);
        phraseContent=new Phrase("Consultant Name : ",font8);
        phraseContent.add(new Chunk("Dr. Divendu Bhushan",font8BOLD));
        cell2 = new PdfPCell(phraseContent);       
        
        cell1.setBorder(0);
        cell2.setBorder(0);

        cell1.setIndent(10);
        cell2.setIndent(10);

        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        table.addCell(cell1);
        table.addCell(cell2);
        
        
        
        table.setSpacingBefore(30f);         
        document.add(table);
        
        p=new Paragraph();
        line = new LineSeparator();
        line.setOffset(0);
        p.add(line);
        document.add(p);

        //////////////////////////////////////////////VISIT REASON//////////////////////////////////////////////        
        p=new Paragraph("VISIT REASON",font12BOLD);
        p.setSpacingBefore(30f);
        document.add(p);
        
        //////////////////////////////////////////////DIAGNOSIS//////////////////////////////////////////////       
        p=new Paragraph("DIAGNOSIS",font12BOLD);
        p.setSpacingBefore(30f);
        document.add(p);
        
        
        
        
        document.close();
    }
    
    
    public static void main (String arr[]){
    	 File file = new File(DEST);
         file.getParentFile().mkdirs();
    	try {
			new SamplePrescriptionToPDFA2().convertPrescriptionToPDFA2(DEST);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
    }
 
}
