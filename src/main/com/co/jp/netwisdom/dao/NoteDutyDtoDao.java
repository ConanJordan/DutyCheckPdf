package main.com.co.jp.netwisdom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import main.com.co.jp.netwisdom.consistant.ConsistantInfo;
import main.com.co.jp.netwisdom.db.DataBaseFactory;
import main.com.co.jp.netwisdom.dto.NoteDutyDto;
import main.com.co.jp.netwisdom.interfaces.Query;
import main.com.co.jp.netwisdom.util.CalendarUtil;

public class NoteDutyDtoDao implements Query {

    @Override
    public List<NoteDutyDto> query(int year, int month) {
        
        // 初始化返回结果
        List<NoteDutyDto> result = new ArrayList<NoteDutyDto>();
        
        Connection connection = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        
        connection = DataBaseFactory.createConnection();
        
        try {
            prst = connection.prepareStatement(ConsistantInfo.SQL_QUERY);
            
            // 设置不检索的部门
            prst.setString(1, ConsistantInfo.ADMINISTRATOR);
            prst.setString(2, ConsistantInfo.BUSINESS);
            
            // 设置目标月第一天
            Calendar firstDay = CalendarUtil.firstDayOfMonth(year, month);
            String firstDayStr = CalendarUtil.calToStr(firstDay, CalendarUtil.YYYYMMDD_DASH);
            prst.setString(3, firstDayStr);
            
            // 设置目标月最后一天
            Calendar lastDay = CalendarUtil.firstDayOfMonth(year, month);
            String lastDayStr = CalendarUtil.calToStr(lastDay, CalendarUtil.YYYYMMDD_DASH);
            prst.setString(4, lastDayStr);
            
            rs = prst.executeQuery();  // 执行检索
            
            while (rs.next()) {
                NoteDutyDto noteDuty = new NoteDutyDto();  // 新建[NoteDutyDto]对象
                
                noteDuty.setCardNo(rs.getString("CardNo"));  // 卡号
                noteDuty.setName(rs.getString("Name"));  // 姓名
                noteDuty.setDept(rs.getString("Detp"));  // 部门
                noteDuty.setCdt(rs.getDate("Cdt"));  // 打卡日期
                noteDuty.setCdi(rs.getString("Cdi"));  // 打卡时刻
                
                result.add(noteDuty);  // 放入集合
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 释放数据库资源
            DataBaseFactory.close(connection, prst, rs);
        }

        return result;  // 返回结果
    }

}
