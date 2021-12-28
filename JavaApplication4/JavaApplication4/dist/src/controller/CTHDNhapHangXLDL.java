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
import model.Chitiethdnh;

/**
 *
 * @author Thang
 */
public class CTHDNhapHangXLDL {
    KNCSDL kn = null;

    public CTHDNhapHangXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<Chitiethdnh> getListCTNH(String id) throws SQLException {
        ArrayList<Chitiethdnh> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT chitiethdnh.*,tbl_sanpham.TenSP FROM chitiethdnh, tbl_sanpham where chitiethdnh.MaSP = tbl_sanpham.MaSP and MaHDNH= "+id;
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Chitiethdnh nv = new Chitiethdnh();
            nv.setMaHDNH(result.getLong(1));
            nv.setMaSP(result.getLong(2));
            nv.setDonGiaNhap(result.getFloat(3));
            nv.setSoLuong(result.getInt(4));
            nv.setTongTien(result.getFloat(5));
            nv.setTenSP(result.getString(6));
            list.add(nv);
        }
        return list;
    }
     public ArrayList<Chitiethdnh> timkiemCTNH(String tk) throws SQLException {
        ArrayList<Chitiethdnh> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT chitiethdnh.*,tbl_sanpham.TenSP FROM chitiethdnh, tbl_sanpham where chitiethdnh.MaSP = tbl_sanpham.MaSP and (MaHDNH like '%"+tk+"%' or TenSP like N'%"+tk+"%' or TongTien like '%"+tk+"%') ";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Chitiethdnh nv = new Chitiethdnh();
            nv.setMaHDNH(result.getLong(1));
            nv.setMaSP(result.getLong(2));
            nv.setDonGiaNhap(result.getFloat(3));
            nv.setSoLuong(result.getInt(4));
            nv.setTongTien(result.getFloat(5));
            nv.setTenSP(result.getString(6));
            list.add(nv);
        }
        return list;
    }
}
