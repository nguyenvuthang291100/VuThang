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
import model.inHoaDonBH;
import model.tblPhieuLuong;

/**
 *
 * @author Thang
 */
public class InPhieuLuong {

    KNCSDL kn = null;

    public InPhieuLuong() {
        kn = new KNCSDL();
    }

    public ArrayList<tblPhieuLuong> getListPL(String ma) throws SQLException {
        ArrayList<tblPhieuLuong> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "select tl.MaTL, nv.TenNV, kl.TenKL, tl.SoNgayLam,  tl.Thuong, tl.Tru, tl.Thue, tl.TongLuong, tl.NgayPhat FROM tbl_tinhluong tl, tbl_nhanvien nv, tbl_kyluong kl WHERE (nv.MaNV = tl.MaNV and kl.MaKL = tl.MaKL and tl.MaTL=" + ma + ")";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            tblPhieuLuong tl = new tblPhieuLuong();
            tl.setMaTL(result.getString(1));
            tl.setTenKL(result.getString(2));
            tl.setTenNV(result.getString(3));
            tl.setSoNgayLV(result.getString(4));
            tl.setThuong(result.getString(5));
            tl.setTru(result.getString(6));
            tl.setThue(result.getString(7));
            tl.setTongLuong(result.getString(8));
            tl.setNgayPhat(result.getString(9));
        }
        return list;
    }
}
