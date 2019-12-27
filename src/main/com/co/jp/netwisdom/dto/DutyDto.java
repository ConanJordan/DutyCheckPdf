package main.com.co.jp.netwisdom.dto;

/**
 * 每天考勤结果的DTO类
 */
public class DutyDto {
	
	/** 姓名 */
	private String name;
	/** 考勤时间 */
	private double times;
	/** 是否迟到或早退 */
	private boolean isLateOrEarly;
	/** 是否异常 */
	private boolean isException;
	/** 是否缺席 */
	private boolean isAbsent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTimes() {
		return times;
	}
	public void setTimes(double times) {
		this.times = times;
	}
	public boolean isLateOrEarly() {
		return isLateOrEarly;
	}
	public void setLateOrEarly(boolean isLateOrEarly) {
		this.isLateOrEarly = isLateOrEarly;
	}
	public boolean isException() {
		return isException;
	}
	public void setException(boolean isException) {
		this.isException = isException;
	}
	public boolean isAbsent() {
		return isAbsent;
	}
	public void setAbsent(boolean isAbsent) {
		this.isAbsent = isAbsent;
	}

}
