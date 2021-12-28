/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.TblKyluong;
import model.TblNhanvien;

/**
 *
 * @author Thang
 */
public class KyluongXLDL {
    
    KNCSDL kn = null;

    public KyluongXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<TblKyluong> getListKL() throws SQLException {
        ArrayList<TblKyluong> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT *FROM tbl_kyluong";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblKyluong kl = new TblKyluong();
            kl.setMaKL(result.getLong(1));
            kl.setTenKL(result.getString(2));
            kl.setLuong(result.getFloat(3));
            
            list.add(kl);
        }
        return list;
    }
    public TblKyluong getKL(long ma) throws SQLException { 
        Connection conn = kn.getConnect();
        String sql = "SELECT *FROM tbl_kyluong where MaKL="+ma;
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
         TblKyluong kl = new TblKyluong();
        while (result.next()) {
            kl.setMaKL(result.getLong(1));
            kl.setTenKL(result.getString(2));
            kl.setLuong(result.getFloat(3));
        }
        return kl;
    }
    public ArrayList<TblKyluong> timkiemKL(String tk) throws SQLException {
        ArrayList<TblKyluong> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT *FROM tbl_kyluong  where (TenKL like N'%"+tk+"%' or MaKL like N'%"+tk+"%')";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblKyluong kl = new TblKyluong();
            kl.setMaKL(result.getLong(1));
            kl.setTenKL(result.getString(2));
            kl.setLuong(result.getFloat(3));
            
            list.add(kl);
        }
        return list;
    }
}
