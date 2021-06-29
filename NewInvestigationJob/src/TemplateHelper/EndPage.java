/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemplateHelper;

import DataProcessing.SavePDF;
import Logging.ServiceLogger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.List;
import java.util.logging.Level;

/**
 * *************************Start of program***************************** ##
 * Copyright Information	: C-DAC, Noida ## Project Name	: CCD SDK ## Name of
 * Developer	: Siddharth Srivastava ## Module Name	: Health Standards ##
 * Process/Database Object Name	: ## Purpose : ## Date of Creation	: ##
 * Modification Log	: ##	Modify Date	: ##	Reason	(CR/PRS)	: ##	Modify By	:
 */
/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class EndPage extends PdfPageEventHelper {

    public List<Element> footerElementList;
    public List<Element> headerElementList;
    Float headerHeight;
    Float footerHeight;
    String footerContentType = "HTML";
    private PdfTemplate t;
    private Image total;
    
    public void onEndPage(PdfWriter writer, Document document) {

        try {

            Rectangle page = document.getPageSize();
            float currentHeight = page.getHeight() - 36;
            List<Element> elementlist = headerElementList;
            if (elementlist != null) {
                //  Log(Level.INFO, "headerElementList  " + elementlist.size());
                for (int i = 0; i < elementlist.size(); i++) {
                    Element element = (Element) elementlist.get(i);
                    Log(Level.INFO, "" + element.type());

                    if (element.type() == 23) {
                        //     Log(Level.INFO, "Table Found ");
                        PdfPTable header = (PdfPTable) element;
                        //     Log(Level.INFO, "header Table Found " + header.size());
                        //    Log(Level.INFO, "header Table Found " + header.getRows().size());
                       // header.setWidthPercentage(100);
                     //   header.setWidths(new int[]{1, 2});
                        header.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
                        //    Log(Level.INFO, "left " + document.leftMargin() + " right " + document.rightMargin() + " Top " + document.topMargin() + " bottom " + document.bottomMargin());
                        document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin(), document.bottomMargin());
                        header.writeSelectedRows(0, header.size(), document.leftMargin(), currentHeight, writer.getDirectContent());

                 //       Log(Level.INFO, "left " + document.leftMargin() + " right " + document.rightMargin() + " Top " + document.topMargin() + " bottom " + document.bottomMargin());
                    }

                }

            }

            elementlist = footerElementList;

            if (elementlist != null) {
            	
            	PdfPTable footernew = new PdfPTable(3);
             	PdfPTable cellnew=null;

            	footernew.setWidths(new int[]{24, 2, 1});
            	footernew.setTotalWidth(600);
            	footernew.setLockedWidth(true);
            	footernew.getDefaultCell().setFixedHeight(60);
                
            	
              //  Log(Level.INFO, "elementlist  " + elementlist.size());
                for (int i = 0; i < elementlist.size(); i++) {
                    Element element = (Element) elementlist.get(i);
               	
                 //   Log(Level.INFO, "" + element.type());

                    if (element.type() == 23) {
                    	
                    	  cellnew = (PdfPTable) element;

                         	PdfPCell containerCell = new PdfPCell();
                       	containerCell.setColspan(3);
                       	containerCell.setBorder(Rectangle.NO_BORDER);
                       	containerCell.addElement(element);
                          footernew.getDefaultCell().setBorder(Rectangle.BOTTOM);

                          footernew.addCell(containerCell);
                          footernew.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

                          footernew.addCell(new Phrase("", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
                          footernew.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                          footernew.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

                          // add placeholder for total page count
                          PdfPCell totalPageCount = new PdfPCell(total);
                          totalPageCount.setBorder(Rectangle.NO_BORDER);

                         // totalPageCount.setBorder(Rectangle.TOP);
                          //totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
                          footernew.addCell(totalPageCount);
                          
                          footernew.addCell("<div ><table width='100%' height='5%' cellspacing='0' cellpadding='0' border='0' '><tr><td align='left' colspan='13'></td></tr></table></div>");
                          footernew.addCell("");
                          footernew.addCell("");
                          
                       // write page
                          PdfContentByte canvas = writer.getDirectContent();
                          canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
                      //    footer.writeSelectedRows(0, -1, 34, 50, canvas);
                          canvas.endMarkedContentSequence();
                          
                        // Log(Level.INFO, "Table Found ");
                        PdfPTable footer = (PdfPTable) element;
                        //  Log(Level.INFO, "Table Found " + footer.size());
                        // Log(Level.INFO, "Table Found " + footer.getRows().size());
                        footernew.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());

                        footer.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
             //           Log(Level.INFO, "left " + document.leftMargin() + " right " + document.rightMargin() + " Top " + document.topMargin() + " bottom " + document.bottomMargin());
                        document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin(), document.bottomMargin());
                        footernew.writeSelectedRows(0, footer.size(), document.leftMargin(), document.bottomMargin(), writer.getDirectContent());

            //            Log(Level.INFO, "left " + document.leftMargin() + " right " + document.rightMargin() + " Top " + document.topMargin() + " bottom " + document.bottomMargin());
                    }

                }
            }

            /*
             PdfPTable foot = new PdfPTable(1);

             PdfPCell cell = new PdfPCell(new Phrase(footerPageString));
             PdfPCell cell1 = new PdfPCell(new Phrase(""));
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             cell.setBorder(1);
             cell1.setBorder(1);
             cell.setBorderWidthTop(1);
             cell1.setBorderWidthBottom(1);

		
             foot.addCell(cell);
             foot.addCell(cell1);
             //foot.addCell("This place is to write your footer text ");
             foot.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
             foot.writeSelectedRows(0, -2, document.leftMargin(), document.bottomMargin(),
             writer.getDirectContent());
		
             */
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    private void Log(Level level, String msg) {
        ServiceLogger.Log(EndPage.class.getName(), level, msg);
    }

    private void Log(Level level, Exception e) {
        ServiceLogger.Log(EndPage.class.getName(), level, e);
    }

  
    
    public void onCloseDocument(PdfWriter writer, Document document) {
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 5;
        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
                new Phrase(String.valueOf(writer.getPageNumber()-1), new Font(Font.FontFamily.HELVETICA, 8)),
                totalWidth, 6, 0);
    }

    
    public void onOpenDocument(PdfWriter writer, Document document) {
        t = writer.getDirectContent().createTemplate(30, 16);
        try {
            total = Image.getInstance(t);
            total.setRole(PdfName.ARTIFACT);
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    
}
