package App.service;

import App.entity.*;

import javax.swing.*;
import java.sql.*;


public class SqlKit extends ConnectDB{

    /**
     * 统计普通用户数量
     * @param user
     * @return
     */
    public int countUUser(User user){
        int cot = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select COUNT(*) as num from cUser where uid like 'U%'";
        conn = this.getConnection();
        try{
            ps = conn.prepareStatement(sql);
            System.out.println(user.getUserName());
            rs = ps.executeQuery();

            if(rs.next()){
                cot = rs.getInt(1);
                System.out.println(rs.getInt("num"));
            }
        }catch (SQLException e){
            System.out.println("countUUser1" + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("countUUser2" + e.getMessage());
            }
            this.closeConnection();
        }
        return cot;
    }

    /**
     * 插入用户
     * @param user
     * @param num
     */
    public void insertUser(User user, int num){
        String number = "0123456789";
        String s = new String();
        for(int i = 0;i < 9;i++){
            s = number.charAt(num % 10) + s;
            num /= 10;
        }
        s = 'U' + s;
        user.setUid(s);
        if(user.getPhone().equals("")){
            user.setPhone(null);
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into cUser(uid, uSex, uName, uUserName, uPassword, uEmail,uTele) Values(?, ?, ?, ?, ?, ?, ?)";
        conn = this.getConnection();
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUid());
            ps.setString(2, user.getSex());
            ps.setString(3, user.getName());
            ps.setString(4, user.getUserName());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getPhone());
            rs = ps.executeQuery();


        }catch (SQLException e){
            System.out.println("insertUser1" + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("insertUser2" + e.getMessage());
            }
            this.closeConnection();
        }
    }

    /**
     * 用户名重复使用检查
     */
    public boolean UserNameCheck(String userName){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select uid from cUser where uUserName = ?";
        conn = this.getConnection();

        boolean flag = false;
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            rs = ps.executeQuery();
            if(!rs.next()){
                flag = true;
            }
        }catch (SQLException e){
            System.out.println("UserNameCheck1: " + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("UserNameCheck2: " + e.getMessage());
            }
            this.closeConnection();
        }
        if(!flag){
            JOptionPane.showMessageDialog(null,"用户名重复");
        }
        return flag;
    }


    /**
     * 用户信息更改
     */

    public void modifyUser(User user){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update cUser set uSex = ?, uName = ?, uUserName = ?, uPassword = ?, uEmail = ?, uTele = ? where uid = ?";
        conn = this.getConnection();


        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getSex());
            ps.setString(2, user.getName());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone());
            ps.setString(7,user.getUid());
            rs = ps.executeQuery();

        }catch (SQLException e){
            System.out.println("modifyUser1: " + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("modifyUser2: " + e.getMessage());
            }
            this.closeConnection();
        }
    }
}
