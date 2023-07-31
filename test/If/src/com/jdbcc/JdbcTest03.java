package com.jdbcc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JdbcTest03 {
    public static void main(String[] args) {
        //初始化一个界面
        Map<String,String> userLogInfo =  initUI();
        //验证用户名和密码
        boolean loginSuccess = login(userLogInfo);
        //最后输出
        System.out.println(loginSuccess? "登陆成功":"登录失败");
    }

    private static boolean login(Map<String, String> userLogInfo) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        String driver = resourceBundle.getString("Driver");
        String username = resourceBundle.getString("username");
        String password = resourceBundle.getString("password");
        String url = resourceBundle.getString("url");
        PreparedStatement pstm = null;
        Connection conn = null;
        ResultSet rs = null;
        boolean r = false;
        try {
            //1.注册驱动
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            //在字符串中加入变量
            String sql = "select * from t_user where loginName = ? and loginPwd = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,userLogInfo.get("loginMame"));
            pstm.setString(2,userLogInfo.get("loginPwd"));
            rs = pstm.executeQuery();
            if (rs.next()) {
                r = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
        return r;
    }
    private static Map<String, String> initUI() {
        /*
          初始化用户界面
          @return 用户输入的用户名和密码等信息
         */
        Scanner s = new Scanner(System.in);
        System.out.println("用户名：");
        String useName = s.nextLine();
        System.out.println("密码：");
        String password = s.nextLine();
        Map<String,String> useLogInfo = new HashMap<>();
        useLogInfo.put("loginMame",useName);
        useLogInfo.put("loginPwd",password);
        return  useLogInfo;
    }
}
