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
import model.TblTinhluong;

/**
 *
 * @author Thang
 */
public class TinhLuongXLDL {
    KNCSDL kn = null;

    public TinhLuongXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<TblTinhluong> getListTL() throws SQLException {
        ArrayList<TblTinhluong> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_tinhluong.*,tbl_nhanvien.TenNV, tbl_kyluong.TenKL FROM tbl_tinhluong, tbl_nhanvien, tbl_kyluong where tbl_tinhluong.MaNV = tbl_nhanvien.MaNV and tbl_tinhluong.MaKL = tbl_kyluong.MaKL ";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblTinhluong tl = new TblTinhluong();
            tl.setMaTL(result.getLong(1));
            tl.setMaNV(result.getLong(2));
            tl.setMaKL(result.getLong(3));
            tl.setMaChamCong(result.getLong(4));
            tl.setSoNgayLam(result.getInt(5));
            tl.setThuong(result.getInt(6));
            tl.setTru(result.getInt(7));
            tl.setThue(result.getFloat(8));
            tl.setTongLuong(result.getFloat(9));
            tl.setNgayPhat(result.getDate(10));
            tl.setTenNV(result.getString(11));
               tl.setTenKL(result.getString(12));
            list.add(tl);
        }
        return list;
    }
    public ArrayList<TblTinhluong> timkiemTL(String tk) throws SQLException {
        ArrayList<TblTinhluong> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_tinhluong.*,tbl_nhanvien.TenNV, tbl_kyluong.TenKL FROM tbl_tinhluong, tbl_nhanvien, tbl_kyluong where tbl_tinhluong.MaNV = tbl_nhanvien.MaNV and tbl_tinhluong.MaKL = tbl_kyluong.MaKL and (TenNV like N'%"+tk+"%' or MaTL like N'%"+tk+"%' or TenKL like N'%"+tk+"%' or MaChamcong like N'%"+tk+"%') ";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblTinhluong tl = new TblTinhluong();
            tl.setMaTL(result.getLong(1));
            tl.setMaNV(result.getLong(2));
            tl.setMaKL(result.getLong(3));
            tl.setMaChamCong(result.getLong(4));
            tl.setSoNgayLam(result.getInt(5));
            tl.setThuong(result.getInt(6));
            tl.setTru(result.getInt(7));
            tl.setThue(result.getFloat(8));
            tl.setTongLuong(result.getFloat(9));
            tl.setNgayPhat(result.getDate(10));
            tl.setTenNV(result.getString(11));
               tl.setTenKL(result.getString(12));
            list.add(tl);
        }
        return list;
    }
}
