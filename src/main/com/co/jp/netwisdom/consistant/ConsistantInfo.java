package main.com.co.jp.netwisdom.consistant;

import java.io.Serializable;

import main.com.co.jp.netwisdom.util.ReadProperty;

public final class ConsistantInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5419246571361847214L;

    public static final String DRIVER = ReadProperty.getProperty("JDBC_DRIVER", "org.sqlite.JDBC");

    public static final String URL = ReadProperty.getProperty("URL", "C:/DataBase/NetWisdom/netwisdom.db");

    public static final String PATH = ReadProperty.getProperty("PATH", "C:/PDF/DutyCheck");

    /**
     * 检索员工和考勤信息的SQL语句
     */
    public static final String SQL_QUERY = "select " 
            + "e.cardNo as CardNo, " // 卡号
            + "e.name as Name, " // 姓名
            + "e.dept as Dept, " // 部门
            + "n.cdt as Cdt, " // 打卡日期
            + "n.cti as Cti " // 打卡时刻
            + "from Employee e, " // [Employee]表
            + "left join " // 左连接
            + "NoteTable n, " // [NoteTable]表
            + "(on e.cardNo = n.cardNo " // 卡号一致
            + "and e.dept not in (?, ?) " // 管理部和营业部不检索
            + "and (n.cdt <= date(?) and n.cdt >= date(?)))"; // 打卡日期在目标月内

}
