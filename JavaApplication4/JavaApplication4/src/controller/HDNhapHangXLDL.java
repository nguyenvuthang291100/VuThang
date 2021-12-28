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
import model.TblHoadonnhaphang;

/**
 *
 * @author Thang
 */
public class HDNhapHangXLDL {
    KNCSDL kn = null;

    public HDNhapHangXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<TblHoadonnhaphang> getListHDNH(String NgayNhap) throws SQLException {
        ArrayList<TblHoadonnhaphang> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_hoadonnhaphang.*, tbl_nhanvien.TenNV FROM tbl_hoadonnhaphang, tbl_nhanvien where tbl_hoadonnhaphang.MaNV = tbl_nhanvien.MaNV and NgayNhap='"+NgayNhap+"'";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblHoadonnhaphang HDNH = new TblHoadonnhaphang();
            HDNH.setMaHDNH(result.getLong(1));
            HDNH.setMaNV(result.getLong(2));
            HDNH.setNgayNhap(result.getDate(3));
            HDNH.setTongTien(result.getFloat(4));
            HDNH.setTenNV(result.getString(5));
            list.add(HDNH);
        }
        return list;
    }
    public ArrayList<TblHoadonnhaphang> timkiemHDNH(String tk) throws SQLException {
        ArrayList<TblHoadonnhaphang> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_hoadonnhaphang.*, tbl_nhanvien.TenNV FROM tbl_hoadonnhaphang, tbl_nhanvien where tbl_hoadonnhaphang.MaNV = tbl_nhanvien.MaNV and (MaHDNH like N'%"+tk+"%' or TenNV like N'%"+tk+"%') ";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblHoadonnhaphang HDNH = new TblHoadonnhaphang();
            HDNH.setMaHDNH(result.getLong(1));
            HDNH.setMaNV(result.getLong(2));
            HDNH.setNgayNhap(result.getDate(3));
            HDNH.setTongTien(result.getFloat(4));
            HDNH.setTenNV(result.getString(5));
            list.add(HDNH);
        }
        return list;
    }
}
