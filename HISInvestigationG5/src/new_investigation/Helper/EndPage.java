package new_investigation.Helper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.List;



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

    public void onEndPage(PdfWriter writer, Document document) {

        try {

            Rectangle page = document.getPageSize();
            float currentHeight = page.getHeight() - 36;
            List<Element> elementlist = headerElementList;
            if (elementlist != null) {
                //  //Log(Level.INFO, "headerElementList  " + elementlist.size());
                for (int i = 0; i < elementlist.size(); i++) {
                    Element element = (Element) elementlist.get(i);
                    //Log(Level.INFO, "" + element.type());

                    if (element.type() == 23) {
                        //     //Log(Level.INFO, "Table Found ");
                        PdfPTable header = (PdfPTable) element;
                        //     //Log(Level.INFO, "header Table Found " + header.size());
                        //    //Log(Level.INFO, "header Table Found " + header.getRows().size());
                       // header.setWidthPercentage(100);
                     //   header.setWidths(new int[]{1, 2});
                        header.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
                        //    //Log(Level.INFO, "left " + document.leftMargin() + " right " + document.rightMargin() + " Top " + document.topMargin() + " bottom " + document.bottomMargin());
                        document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin(), document.bottomMargin());
                        header.writeSelectedRows(0, header.size(), document.leftMargin(), currentHeight, writer.getDirectContent());

                        //Log(Level.INFO, "left " + document.leftMargin() + " right " + document.rightMargin() + " Top " + document.topMargin() + " bottom " + document.bottomMargin());
                    }

                }

            }

            elementlist = footerElementList;

            if (elementlist != null) {
                //Log(Level.INFO, "elementlist  " + elementlist.size());
                for (int i = 0; i < elementlist.size(); i++) {
                    Element element = (Element) elementlist.get(i);
                    //Log(Level.INFO, "" + element.type());

                    if (element.type() == 23) {
                        // //Log(Level.INFO, "Table Found ");
                        PdfPTable footer = (PdfPTable) element;
                        //  //Log(Level.INFO, "Table Found " + footer.size());
                        // //Log(Level.INFO, "Table Found " + footer.getRows().size());

                        footer.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
                        //Log(Level.INFO, "left " + document.leftMargin() + " right " + document.rightMargin() + " Top " + document.topMargin() + " bottom " + document.bottomMargin());
                        document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin(), document.bottomMargin());
                        footer.writeSelectedRows(0, footer.size(), document.leftMargin(), document.bottomMargin(), writer.getDirectContent());

                        //Log(Level.INFO, "left " + document.leftMargin() + " right " + document.rightMargin() + " Top " + document.topMargin() + " bottom " + document.bottomMargin());
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
   

}
