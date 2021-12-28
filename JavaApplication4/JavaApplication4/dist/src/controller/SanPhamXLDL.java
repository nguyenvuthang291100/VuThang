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
import model.TblSanpham;

/**
 *
 * @author Thang
 */
public class SanPhamXLDL {
     KNCSDL kn = null;

    public SanPhamXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<TblSanpham> getListSP() throws SQLException {
        ArrayList<TblSanpham> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_sanpham.*, tbl_danhmuc.TenDM FROM tbl_sanpham, tbl_danhmuc where tbl_sanpham.MaDM = tbl_danhmuc.MaDM ";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblSanpham sp = new TblSanpham();
            sp.setMaSP(result.getLong(1));
            sp.setTenSP(result.getString(2));
            sp.setMaDM(result.getLong(3));
            sp.setSize(result.getString(4));
            sp.setMau(result.getString(5));
            sp.setGia(result.getInt(6));
            sp.setHinhAnh(result.getString(7));
            sp.setSLTon(result.getInt(8));
            sp.setTenDM(result.getString(9));
            list.add(sp);
        }
        return list;
    }
     public ArrayList<TblSanpham> getListSLSP() throws SQLException {
        ArrayList<TblSanpham> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT SLTon FROM tbl_sanpham";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblSanpham sp = new TblSanpham();
            
            sp.setSLTon(result.getInt(1));
            
            list.add(sp);
        }
        return list;
    }
      
     public TblSanpham getmaSP(String ma) throws SQLException {
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_sanpham.*, tbl_danhmuc.TenDM FROM tbl_sanpham, tbl_danhmuc where tbl_sanpham.MaDM = tbl_danhmuc.MaDM AND tbl_sanpham.MaSP=" +ma;
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        TblSanpham sp = new TblSanpham();
        while (result.next()) {
            sp.setMaSP(result.getLong(1));
            sp.setTenSP(result.getString(2));
            sp.setMaDM(result.getLong(3));
            sp.setSize(result.getString(4));
            sp.setMau(result.getString(5));
            sp.setGia(result.getInt(6));
            sp.setHinhAnh(result.getString(7));
            sp.setSLTon(result.getInt(8));
            sp.setTenDM(result.getString(9));
        }
        return sp;
     }
     public ArrayList<TblSanpham> timkiemSP(String tk) throws SQLException {
        ArrayList<TblSanpham> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_sanpham.*, tbl_danhmuc.TenDM FROM tbl_sanpham, tbl_danhmuc where tbl_sanpham.MaDM = tbl_danhmuc.MaDM and (TenSP like N'%"+tk+"%' or MaSP like N'%"+tk+"%' or Size like N'%"+tk+"%' or Mau like N'%"+tk+"%' or TenDM like N'%"+tk+"%' or Gia like N'%"+tk+"%' )";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblSanpham sp = new TblSanpham();
            sp.setMaSP(result.getLong(1));
            sp.setTenSP(result.getString(2));
            sp.setMaDM(result.getLong(3));
            sp.setSize(result.getString(4));
            sp.setMau(result.getString(5));
            sp.setGia(result.getInt(6));
            sp.setHinhAnh(result.getString(7));
            sp.setSLTon(result.getInt(8));
            sp.setTenDM(result.getString(9));
            list.add(sp);
        }
        return list;
    }
}
