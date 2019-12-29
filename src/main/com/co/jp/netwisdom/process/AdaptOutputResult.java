package main.com.co.jp.netwisdom.process;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.com.co.jp.netwisdom.dto.DutyDto;
import main.com.co.jp.netwisdom.dto.DutyStatisDto;
import main.com.co.jp.netwisdom.dto.NoteDutyDto;
import main.com.co.jp.netwisdom.dto.OutputResultDto;
import main.com.co.jp.netwisdom.util.CalendarUtil;

/**
 * 把查询结果调整成方便于打印到PDF文件里的格式
 */
public class AdaptOutputResult {

    /** 目标年 */
    private int year;
    /** 目标月 */
    private int month;
    /** 目标月的天数 */
    private int days;
    /** 查询出来的DTO集合 */
    private List<NoteDutyDto> noteDuties;
    /** 调整后的DTO集合(要返回给外面的) */
    private List<OutputResultDto> outputResults;
    
    public AdaptOutputResult(int year, int month, List<NoteDutyDto> noteDuties) {
        super();
        this.year = year;
        this.month = month;
        this.noteDuties = noteDuties;
        this.outputResults = new ArrayList<OutputResultDto>();
        this.days = CalendarUtil.daysOfMonth(this.year, this.month);
    }
    
    public void adapt () {  // TODO
        
        // 以CardNo作为key,遍历noteDuties集合，把CardNo相同的[NoteDutyDto]元素放到一个集合里。
        Map<String, List<NoteDutyDto>> map = new HashMap<String, List<NoteDutyDto>>();
        
        for (NoteDutyDto noteDuty : noteDuties) {
            if (map.containsKey(noteDuty.getCardNo())) {  // key已经存在的情况
                map.get(noteDuty.getCardNo()).add(noteDuty);
            } else {  // key不存在的情况
                List<NoteDutyDto> list = new ArrayList<NoteDutyDto>();
                list.add(noteDuty);
                map.put(noteDuty.getCardNo(), list);
            }
        }
        
        // 遍历map，把每个员工的当月每天的考勤情况和考勤统计放入outputResults里。
        Iterator<Entry<String, List<NoteDutyDto>>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Entry<String, List<NoteDutyDto>> entry = entries.next();
            //String cardNo = entry.getKey();
            List<NoteDutyDto> noteDutyList = entry.getValue();
            
            // TODO 往要返回的结果集合里放入元素
            
        }
    }
    
    // 往要返回的结果集合里放入元素
    private void addOutputResult (List<NoteDutyDto> list) {
        
        OutputResultDto outputResult = new OutputResultDto();
;
        outputResult.setName(list.get(0).getName());  // 姓名
        
        int day = 1;  // 天数
        
        boolean loopable = true;  // 循环标志符
        
        // 临时集合，用来存放打卡日期一致的[NoteDutyDto]元素
        List<NoteDutyDto> tempList = new ArrayList<NoteDutyDto>();
        
        // 临时[Calendar]对象
        Calendar tempCal = Calendar.getInstance();
        
        // 从目标月的第一天循环到最后一天，对打卡日期一致的[NoteDutyDto]对象做进一步操作。
        while (loopable) {
            DutyDto duty = new DutyDto();
            for (NoteDutyDto noteDuty : list) {
                tempCal.setTime(noteDuty.getCdt());
                if (tempCal.get(Calendar.DAY_OF_MONTH) == day) {
                    tempList.add(noteDuty);
                }
            }
            if (tempList.size() >= 2) {  // 当天打卡两次获两次以上
                String start = tempList.get(0).getCdi();  // 第一次打卡日期
                String end = tempList.get(tempList.size() - 1).getCdi();  // 最后一次打卡日期
                // 迟到判断
                if (CalendarUtil.isLate(start)) {
                    duty.setLateOrEarly(true);
                }
                // 早退判断
                if (CalendarUtil.isEarly(end)) {
                    duty.setLateOrEarly(true);
                }
                // 出勤时间
                duty.setTimes(CalendarUtil.getDutyTime(start, end));
            }
            if (tempList.size() == 1) {  // 当天打卡一次
                duty.setException(true);  // 算作异常
            }
            if (tempList.size() == 0) {  // 当天没有打卡
                duty.setAbsent(true);  // 算作缺勤
            }
            
            outputResult.getDuties().add(duty);
            
            tempList.clear();  // 清空临时集合

            day ++;  // 天数自增
            if (day > days) {  // 超过目标月最大天数的时候
                loopable = false;  // 循环结束
            }
        }
        
        // 设置目标月考勤统计
        this.setDutyStatis(outputResult);
        
        this.outputResults.add(outputResult);  // 放入结果集合里
    }
    
    // 设置目标月考勤统计
    private void setDutyStatis (OutputResultDto outputResult) {
        
        DutyStatisDto dutyStatis = outputResult.getDutyStatis();

        for (DutyDto duty : outputResult.getDuties()) {
            
            // 实际出勤天数统计
            if (duty.isAbsent() == false) {
                dutyStatis.setDutyCounts(dutyStatis.getDutyCounts() + 1);
            }
            
            // 迟到早退统计（包括异常）
            if (duty.isException() || duty.isLateOrEarly()) {
                dutyStatis.setLateEarlyCounts(
                        dutyStatis.getLateEarlyCounts() + 1
                        );
            }
            
            // 实际出勤时间统计
            dutyStatis.setRealTimes(dutyStatis.getRealTimes() + duty.getTimes());
        }
    }
    
    public List<OutputResultDto> getOutputResults () {
        return this.outputResults;
    }
    
}
