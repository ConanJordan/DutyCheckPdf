package main.com.co.jp.netwisdom.view;

import java.util.List;
import java.util.Scanner;

import main.com.co.jp.netwisdom.consistant.ConsistantInfo;
import main.com.co.jp.netwisdom.dto.NoteDutyDto;
import main.com.co.jp.netwisdom.dto.OutputResultDto;
import main.com.co.jp.netwisdom.process.AdaptOutputResult;
import main.com.co.jp.netwisdom.service.NoteDutyDtoService;

/**
 * 控制台运行的类。
 * 该类向用户展示控制台界面，并请求用户输入查询年月然后处理。
 */
public class ConsoleView {
    
    /** 年份 */
    private static int YEAR;
    /** 月份 */
    private static int MONTH;
    /** 获取用户输入信息的对象实体 */
    private static Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println(ConsistantInfo.INFO_INPUT_MESSAGE);
        
        String inputStr = INPUT.next();  // 获取用户输入结果
        
        YEAR = Integer.parseInt(inputStr.substring(0, 4));  // 取得年份
        MONTH = Integer.parseInt(inputStr.substring(4, 6));  // 取得月份
        
        NoteDutyDtoService noteDutyDtoService = new NoteDutyDtoService();
        List<NoteDutyDto> noteDuties = noteDutyDtoService.query(YEAR, MONTH);  // 取得查询结果
        
        AdaptOutputResult adaptOutputResult = new AdaptOutputResult(YEAR, MONTH, noteDuties);
        adaptOutputResult.adapt();
        List<OutputResultDto> outputResults = adaptOutputResult.getOutputResults();  // 取得调整后的数据集合
        
        // 写入PDF文件 TODO
        
    }

}
