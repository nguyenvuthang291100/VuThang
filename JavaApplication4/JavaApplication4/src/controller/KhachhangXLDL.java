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
import model.TblKhachhang;
import model.TblNhanvien;

/**
 *
 * @author Thang
 */
public class KhachhangXLDL {
    
    KNCSDL kn = null;

    public KhachhangXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<TblKhachhang> getListKH() throws SQLException {
        ArrayList<TblKhachhang> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT *FROM tbl_khachhang";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblKhachhang KH = new TblKhachhang();
            KH.setMaKH(result.getLong(1));
            KH.setTenKH(result.getString(2));
            KH.setLoai(result.getString(3));
            
            list.add(KH);
        }
        return list;
    }
    public ArrayList<TblKhachhang> timkiemKH(String tk) throws SQLException {
        ArrayList<TblKhachhang> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT *FROM tbl_khachhang where (MaKH like N'%"+tk+"%' or Loai like N'%"+tk+"%' or TenKH like N'%"+tk+"%')";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblKhachhang KH = new TblKhachhang();
            KH.setMaKH(result.getLong(1));
            KH.setTenKH(result.getString(2));
            KH.setLoai(result.getString(3));
            list.add(KH);
        }
        return list;
    }
}
