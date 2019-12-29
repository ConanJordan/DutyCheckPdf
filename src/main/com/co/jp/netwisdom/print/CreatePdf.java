package main.com.co.jp.netwisdom.print;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePdf {

    public static void main(String[] args) {

        // 创建Document对象
        Document document = new Document(PageSize.A4.rotate());

        // 创建PdfWriter对象
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream("d:/sample.pdf"));

            // 打开pdf文档
            document.open();

            // 作者
            document.addAuthor("NetWisdom");
            // 创建日期
            document.addCreationDate();
            // 创建关键字
            document.addKeywords("考勤");
            // 创建生产商，自动使用iText
            document.addProducer();
            // 创建程序
            document.addCreator("www.netwisdom.com");
            // 标题
            document.addTitle("测试标题");
            // 主题
            document.addSubject("测试PDF创建的主题");

            // 设置字体
            BaseFont baseFont = BaseFont.createFont("C:/WINDOWS/Fonts/simhei.ttf", BaseFont.IDENTITY_H,
                    BaseFont.NOT_EMBEDDED);
            Font font = new Font(baseFont);

            // 添加内容
            document.add(new Paragraph("PDF文件创建成功。", font));

            // 关闭文档
            document.close();

            System.out.println("PDF文件创建成功。");

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

}
