package App.service;

import App.entity.*;
import java.sql.*;


public class SqlKit extends ConnectDB{

    public int countUUser(User user){
        int cot = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select COUNT(*) from cUser group by uid having uid like 'U%' ";
        conn = this.getConnection();
        try{
            ps = conn.prepareStatement(sql);
            System.out.println(user.getUserName());
            rs = ps.executeQuery();

            if(rs.next()){
                cot = rs.getInt(1);
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

    public void insertUser(User user, int num){
        String number = "0123456789";
        String s = new String();
        for(int i = 0;i < 9;i++){
            s = number.charAt(num % 10) + s;
            num /= 10;
        }
        s = 'U' + s;
        user.setUid(s);

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
            ps.executeQuery();


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
}
