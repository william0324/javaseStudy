package com.jdbcc;

import java.sql.*;

public class JDBCTest01 {
    public static void main(String[] args) {
        PreparedStatement pstm = null;
        Connection conn = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取链接
            // 数据库用户名
            String username = "root";
            // 数据库密码
            String password = "123";
         /*
            url参数用来确定连接的数据库信息： 数据库机器ip 端口号port 数据库名db_name 连接的参数，比如编解码集、时区...
            url格式：jdbc:mysql://ip:port/db_name?k=v参数 ，只需要了解url的组成，不需要记忆
        */
            String url = "jdbc:mysql://127.0.0.1:3306/bb";
            conn = DriverManager.getConnection(url,username,password);
            // url错误创建链接时会出现异常
            // 参数错误不会导致运行时异常
            System.out.println( conn );
            //获取数据库操作对象，执行sql
            String sql = "insert into dept(deptno,dname,loc) values(50,'人事部','北京');";
            pstm = conn.prepareStatement(sql);
            //执行sql
            int count = pstm.executeUpdate(sql);
            System.out.println(count == 1? "保存成功":"保存失败");
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
