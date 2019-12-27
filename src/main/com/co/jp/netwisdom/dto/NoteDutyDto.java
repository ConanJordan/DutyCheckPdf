package main.com.co.jp.netwisdom.dto;

import java.sql.Date;

/**
 * 员工信息和考勤信息连接查询的DTO类
 */
public class NoteDutyDto {

	/** 卡号 */
	private String cardNo;
	/** 姓名 */
	private String name;
	/** 部门 */
	private String dept;
	/** 打卡日期 */
	private Date cdt;
	/** 打卡时刻 */
	private String cdi;
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Date getCdt() {
		return cdt;
	}
	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}
	public String getCdi() {
		return cdi;
	}
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	
	
}
