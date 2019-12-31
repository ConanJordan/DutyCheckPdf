package main.com.co.jp.netwisdom.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.com.co.jp.netwisdom.consistant.ConsistantInfo;

public final class DataBaseFactory {

    public static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(ConsistantInfo.DRIVER); // 加载数据库的驱动类
            connection = DriverManager.getConnection(ConsistantInfo.URL); // 获取数据库的连接
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 释放数据库资源
     * 
     * @param connection
     * @param prst
     * @param rs
     */
    public static void close(Connection connection, PreparedStatement prst, ResultSet rs) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (prst != null) {
                prst.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
