package App.service;

import App.entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


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


    /**
     * 查询显示全部社团
     */
    public DefaultTableModel searchClub(String clubName){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select cid, cName, uid, (select uName from cUser where cUser.uid = cClub.uid) as 'cLeaderName', cTime from cClub";
        if(!clubName.equals("")){
            sql += " where cName like '%" + clubName + "%'";
        }
        conn = this.getConnection();
        DefaultTableModel res = null;

        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector<Object> columns = new Vector<Object>();
            columns.add("社团编号");
            columns.add("社团名称");
            columns.add("社团团长ID");
            columns.add("团长姓名");
            columns.add("社团建立时间");
            res = new DefaultTableModel(columns, 0);
            while(rs.next()){
                Object cid = rs.getString("cid");
                Object cName = rs.getString("cName");
                Object uid = rs.getString("uid");
                Object cLeaderName = rs.getString("cLeaderName");
                Object cTime = rs.getString("cTime");

                Vector<Object> row = new Vector<Object>();
                row.add(cid);
                row.add(cName);
                row.add(uid);
                row.add(cLeaderName);
                row.add(cTime);
                res.addRow(row);
            }

        }catch (SQLException e){
            System.out.println("searchClub1: " + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("searchClub2: " + e.getMessage());
            }
            this.closeConnection();
        }
        return res;
    }


    public DefaultTableModel searchActivity(String aName){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select aid, aPlace, aContent, aPNum, aRigEndTime, aStartTime, aStatus, (select cName from cClub where cClub.cid = cActivity.cid) as 'cName', aReason from cActivity";
        if(!aName.equals("")){
            sql += " where aContent like '%" + aName + "%'";
        }
        conn = this.getConnection();
        DefaultTableModel res = null;

        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector<Object> columns = new Vector<Object>();
            columns.add("活动编号");
            columns.add("活动地点名称");
            columns.add("活动名称");
            columns.add("活动人数");
            columns.add("活动报名截止日期");
            columns.add("活动开始时间");
            columns.add("活动审核状态");
            columns.add("活动社团名");
            columns.add("申请理由");
            res = new DefaultTableModel(columns, 0);
            while(rs.next()){
                Object aid = rs.getString("aid");
                Object aPlace = rs.getString("aPlace");
                Object aContent = rs.getString("aContent");
                Object aPNum = rs.getString("aPNum");
                Object aRigEndTime = rs.getString("aRigEndTime");
                Object aStartTime = rs.getString("aStartTime");
                Object aStatus = rs.getString("aStatus");
                Object cName = rs.getString("cName");
                Object aReason = rs.getString("aReason");

                Vector<Object> row = new Vector<Object>();
                row.add(aid);
                row.add(aPlace);
                row.add(aContent);
                row.add(aPNum);
                row.add(aRigEndTime);
                row.add(aStartTime);
                row.add(aStatus);
                row.add(cName);
                row.add(aReason);
                res.addRow(row);
            }

        }catch (SQLException e){
            System.out.println("searchClub1: " + e.getMessage());
        }finally {
            try{
                rs.close();
                ps.close();
            }catch (SQLException e){
                System.out.println("searchClub2: " + e.getMessage());
            }
            this.closeConnection();
        }
        return res;
    }

    public static void main(String[] args){
        SqlKit tmp = new SqlKit();
        DefaultTableModel tmpmodel = tmp.searchActivity("");
        System.out.println(tmpmodel);
    }
}
