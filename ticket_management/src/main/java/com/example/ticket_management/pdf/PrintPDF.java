package com.example.ticket_management.pdf;

import com.example.ticket_management.dto.ITicketDTO1;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class PrintPDF {
    public static void printPDF(ITicketDTO1 iTicketDTO1) {
        float customWidth = 200;
        float customHeight = 220;
        Rectangle pageSize = new Rectangle(customWidth, customHeight);
        Document document = new Document(pageSize, 10, 10, 10, 10);

        try {

            // Tạo đối tượng PdfWriter
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("D:\\C0823G1_Nguyen_Dinh_Thai_Bao_Module1\\module_4\\case-chinh\\C0823G1-Repo-BE\\ticket_management\\src\\main\\java\\com\\example\\ticket_management\\pdf\\vexe" + iTicketDTO1.getId() + ".pdf"));
            document.open();
            PdfContentByte contentByte = pdfWriter.getDirectContentUnder();
            Image backgroundImage = Image.getInstance("D:\\C0823G1_Nguyen_Dinh_Thai_Bao_Module1\\module_4\\case-chinh\\C0823G1-Repo-BE\\ticket_management\\src\\main\\java\\com\\example\\ticket_management\\pdf\\pngtree-black-minimalist-atmospheric-wooden-background-backgroundwooden-backgroundtree-texture-image_77053.jpg");
            backgroundImage.setAbsolutePosition(0, 0);
            contentByte.addImage(backgroundImage);

            // Mở file để thực hiện ghi


            // Thêm nội dung sử dụng add function
            BaseFont baseFont = BaseFont.createFont("D:\\C0823G1_Nguyen_Dinh_Thai_Bao_Module1\\module_4\\case-chinh\\C0823G1-Repo-BE\\ticket_management\\src\\main\\java\\com\\example\\ticket_management\\pdf\\arial-unicode-ms.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 15, Font.BOLD, BaseColor.YELLOW);

            Chunk chunk = new Chunk("Vé xe nhà xe Hiếu Hoa", font);
            Paragraph paragraph = new Paragraph(chunk);

            document.add(paragraph);
            Font font1 = new Font(baseFont, 12, Font.BOLD, BaseColor.WHITE);
            String text1 = "Điểm đi: " + iTicketDTO1.getStartingPoint();
            Chunk chunk1 = new Chunk(text1, font1);
            Paragraph paragraph1 = new Paragraph(chunk1);
            String text2 = "Điểm đến: " + iTicketDTO1.getEndingPoint();
            Chunk chunk6 = new Chunk(text2, font1);
            Paragraph paragraph6 = new Paragraph(chunk6);
            document.add(paragraph1);
            document.add(paragraph6);
            Font font2 = new Font(baseFont, 10, Font.NORMAL, BaseColor.WHITE);
            String text3 = "Thời gian đi:" + iTicketDTO1.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
            Chunk chunk2 = new Chunk(text3, font2);
            Paragraph paragraph2 = new Paragraph(chunk2);

            document.add(paragraph2);
            String text4 = "Thời gian đến:" + iTicketDTO1.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
            Chunk chunk3 = new Chunk(text4, font2);
            Paragraph paragraph3 = new Paragraph(chunk3);

            document.add(paragraph3);
            String text5 = "Số ghế: " + iTicketDTO1.getNumberSeat();
            Chunk chunk4 = new Chunk(text5, font2);
            Paragraph paragraph4 = new Paragraph(chunk4);

            document.add(paragraph4);
            Chunk chunk5 = new Chunk("Trạng thái: Đã thanh toán", font2);
            Paragraph paragraph5 = new Paragraph(chunk5);

            document.add(paragraph5);
            document.add(new Paragraph(""));
            Image image1 = Image.getInstance("D:\\C0823G1_Nguyen_Dinh_Thai_Bao_Module1\\module_4\\case-chinh\\C0823G1-Repo-BE\\ticket_management\\src\\main\\java\\com\\example\\ticket_management\\pdf\\hieuhoa_logo_trang.png");
            image1.scaleAbsoluteWidth(180);
            document.add(image1);


            // Đóng File
            document.close();
            System.out.println("Write file succes!");
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
