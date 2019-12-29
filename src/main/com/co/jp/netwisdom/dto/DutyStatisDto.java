package main.com.co.jp.netwisdom.dto;

/**
 * 当月考勤结果统计DTO类
 */
public class DutyStatisDto {

    /** 姓名 */
    private String name;
    /** 预计出勤时间 */
    private double predictedTimes;
    /** 实际出勤时间 */
    private double realTimes;
    /** 迟到早退次数 */
    private int lateEarlyCounts;
    /** 异常次数 */
    private int exCounts;
    /** 实际出勤次数 */
    private int dutyCounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPredictedTimes() {
        return predictedTimes;
    }

    public void setPredictedTimes(double predictedTimes) {
        this.predictedTimes = predictedTimes;
    }

    public double getRealTimes() {
        return realTimes;
    }

    public void setRealTimes(double realTimes) {
        this.realTimes = realTimes;
    }

    public int getLateEarlyCounts() {
        return lateEarlyCounts;
    }

    public void setLateEarlyCounts(int lateEarlyCounts) {
        this.lateEarlyCounts = lateEarlyCounts;
    }

    public int getExCounts() {
        return exCounts;
    }

    public void setExCounts(int exCounts) {
        this.exCounts = exCounts;
    }

    public int getDutyCounts() {
        return dutyCounts;
    }

    public void setDutyCounts(int dutyCounts) {
        this.dutyCounts = dutyCounts;
    }

}
