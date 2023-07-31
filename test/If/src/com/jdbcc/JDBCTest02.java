package com.jdbcc;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest02 {
    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        String driver = resourceBundle.getString("Driver");
        String username = resourceBundle.getString("username");
        String password = resourceBundle.getString("password");
        String url = resourceBundle.getString("url");
        PreparedStatement pstm = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            // url错误创建链接时会出现异常
            // 参数错误不会导致运行时异常
            System.out.println( conn );
            //获取数据库操作对象，执行sql
            //String sql = "insert into dept(deptno,dname,loc) values(60,'人事部','北京');";
            //pstm = conn.prepareStatement(sql);
            //执行sql
            //int count = pstm.executeUpdate(sql);
            //System.out.println(count == 1? "保存成功":"保存失败");
            String sql = "select empno,ename,sal from emp";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery(sql);
            while (rs.next()) {
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                double sal = rs.getDouble("sal");
                System.out.println(empno + "," + ename + "," + sal);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
