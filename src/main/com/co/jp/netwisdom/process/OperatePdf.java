package main.com.co.jp.netwisdom.process;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import main.com.co.jp.netwisdom.consistant.ConsistantInfo;
import main.com.co.jp.netwisdom.dto.OutputResultDto;
import main.com.co.jp.netwisdom.util.CalendarUtil;
import main.com.co.jp.netwisdom.util.FileUtil;

/**
 * 创建，编辑PDF的类
 */
public class OperatePdf {

    /** 年份 */
    private int year;
    /** 月份 */
    private int month;
    /** 目标月天数 */
    private int days;
    /** 要写入的数据集合 */
    private List<OutputResultDto> outputResult;
    /** PDF文件名 */
    private String pdfName;
    /** [PdfWriter]对象 */
    private PdfWriter writer;
    /** [PdfDocument]对象 */
    private PdfDocument document;
    /** [PdfPTable]对象 */
    private PdfPTable table;
    
    private static BaseFont BASE_FONT;
    private static Font FONT;
    
    static {
        try {
            BASE_FONT = BaseFont.createFont("C:/WINDOWS/Fonts/simhei.ttf", BaseFont.IDENTITY_H,
                    BaseFont.NOT_EMBEDDED);
            FONT = new Font(BASE_FONT);
        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public OperatePdf(int year, int month, List<OutputResultDto> outputResult) {
        super();
        this.year = year;
        this.month = month;
        this.outputResult = outputResult;
        this.days = CalendarUtil.daysOfMonth(this.year, this.month);
    }
    
    // 初始化PDF文件
    private void init () throws FileNotFoundException, DocumentException {
        
        if (this.create() == false) {  // 创建PDF文件失败的话
            return;  // 处理结束
        }
        
        this.document = new PdfDocument();  // 新建PdfDocument对象
        // 新建PdfWriter对象
        this.writer = PdfWriter.getInstance(this.document, new FileOutputStream(ConsistantInfo.PATH + pdfName));
        this.document.open();  // 打开document对象
        
    }
    
    // 新建PDF文件
    private boolean create () {
        
        this.pdfName = 
                this.year + "年" 
                + this.month + "月份考勤结果"
                + CalendarUtil.timestamp() + ".pdf";  // 取得PDF文件的名称
        try {
            return FileUtil.create(ConsistantInfo.PATH + pdfName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;
    }
    
    // 关闭PDF文件，释放资源
    private void finish () {
        if (this.document != null && this.document.isOpen()) {
            this.document.close();
        }
        if (this.writer != null) {
            this.writer.close();
        }
    }
    
    // 搭建PDF文件的框架
    private void createFrameWork () throws DocumentException {
        // 新建Table
        this.table = new PdfPTable(3 + this.days + 4);
        this.document.add(this.table);
        
        // 设置Table的第一行
        PdfPCell header = new PdfPCell();
        header.setRowspan(1);
        header.setColspan(3 + this.days + 4);
        Paragraph headerContent = new Paragraph(
                this.year + "年" + this.month + "月份考勤结果", 
                FONT);
        header.addElement(headerContent);
        this.table.addCell(header);
        
        // 设置Table的第二行和第三行
        // 姓名:占两行，一列
        PdfPCell nameCell = new PdfPCell();
        nameCell.setRowspan(2);
        nameCell.setColspan(1);
        nameCell.addElement(new Paragraph("姓名", FONT));
        this.table.addCell(nameCell);
        
        // 部门:占两行，一列
        PdfPCell deptCell = new PdfPCell();
        deptCell.setRowspan(2);
        deptCell.setColspan(1);
        deptCell.addElement(new Paragraph("部门", FONT));
        this.table.addCell(deptCell);
        
        // 日期:占一行，一列
        for (int day = 1; day <= this.days; day ++) {
            PdfPCell cell = new PdfPCell();
            cell.addElement(new Paragraph(String.valueOf(day), FONT));
            this.table.addCell(cell);
        }
        
        // 预计出勤:占两行，一列
        PdfPCell preDutyCell = new PdfPCell();
        preDutyCell.setRowspan(2);
        preDutyCell.setColspan(1);
        preDutyCell.addElement(new Paragraph("预计出勤", FONT));
        this.table.addCell(preDutyCell);
        
        // 实际出勤:占两行，一列
        PdfPCell realDutyCell = new PdfPCell();
        realDutyCell.setRowspan(2);
        realDutyCell.setColspan(1);
        realDutyCell.addElement(new Paragraph("实际出勤", FONT));
        this.table.addCell(realDutyCell);
        
        // 出勤时长:占两行，一列
        PdfPCell dutyTimesCell = new PdfPCell();
        dutyTimesCell.setRowspan(2);
        dutyTimesCell.setColspan(1);
        dutyTimesCell.addElement(new Paragraph("出勤时长", FONT));
        this.table.addCell(dutyTimesCell);
        
        // 迟到早退:占两行，一列
        PdfPCell lateEarlyDutyCell = new PdfPCell();
        lateEarlyDutyCell.setRowspan(2);
        lateEarlyDutyCell.setColspan(1);
        lateEarlyDutyCell.addElement(new Paragraph("迟到早退", FONT));
        this.table.addCell(lateEarlyDutyCell);
        
        // 曜日:占一行，一列
        for (int day = 1; day <= this.days; day ++) {
            PdfPCell cell = new PdfPCell();
            cell.addElement(new Paragraph(
                    CalendarUtil.getWeek(CalendarUtil.getWeek(this.year, this.month, day)), 
                    FONT));
            this.table.addCell(cell);
        }
    }
    
    // 编辑PDF文件
    public void edit () {
        try {
            this.init();  // 初始化PDF文件
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
    
}
