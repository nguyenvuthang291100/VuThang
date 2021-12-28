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
import model.TblChamcong;

/**
 *
 * @author Thang
 */
public class ChamcongXLDL {
        KNCSDL kn = null;

    public ChamcongXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<TblChamcong> getListCC(String ngay) throws SQLException {
        ArrayList<TblChamcong> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_chamcong.*, tbl_nhanvien.TenNV FROM tbl_chamcong, tbl_nhanvien where tbl_chamcong.MaNV = tbl_nhanvien.MaNV and Ngay='"+ngay+"'";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblChamcong nv = new TblChamcong();
            nv.setMaChamCong(result.getLong(1));
            nv.setMaNV(result.getLong(2));
            nv.setNgay(result.getDate(3));
            nv.setGioVao(result.getTime(4));
            nv.setGioRa(result.getTime(5));
            nv.setGhiChu(result.getString(6));
            nv.setTenNV(result.getString(7));
            list.add(nv);
        }
        return list;
    }
     public ArrayList<TblChamcong> timkiemCC(String tk) throws SQLException {
        ArrayList<TblChamcong> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_chamcong.*, tbl_nhanvien.TenNV FROM tbl_chamcong, tbl_nhanvien where tbl_chamcong.MaNV = tbl_nhanvien.MaNV and (TenNV like N'%"+tk+"%' or MaChamcong like N'%"+tk+"%')";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblChamcong nv = new TblChamcong();
            nv.setMaChamCong(result.getLong(1));
            nv.setMaNV(result.getLong(2));
            nv.setNgay(result.getDate(3));
            nv.setGioVao(result.getTime(4));
            nv.setGioRa(result.getTime(5));
            nv.setGhiChu(result.getString(6));
            nv.setTenNV(result.getString(7));
         
            list.add(nv);
        }
        return list;
    }
}
