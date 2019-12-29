package main.com.co.jp.netwisdom.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出打印结果的DTO类
 */
public class OutputResultDto {

    /** 姓名 */
    private String name;
    /** 考勤集合 */
    private List<DutyDto> duties = new ArrayList<DutyDto>();
    /** 考勤结果统计 */
    private DutyStatisDto dutyStatis = new DutyStatisDto();
    /** 年份 */
    private int year;
    /** 月份 */
    private int month;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DutyDto> getDuties() {
        return duties;
    }

    public void setDuties(List<DutyDto> duties) {
        this.duties = duties;
    }

    public DutyStatisDto getDutyStatis() {
        return dutyStatis;
    }

    public void setDutyStatis(DutyStatisDto dutyStatis) {
        this.dutyStatis = dutyStatis;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

}
