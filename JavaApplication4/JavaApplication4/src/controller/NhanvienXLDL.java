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
import model.TblNhanvien;

/**
 *
 * @author Thang
 */
public class NhanvienXLDL {

    KNCSDL kn = null;

    public NhanvienXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<TblNhanvien> getListNV() throws SQLException {
        ArrayList<TblNhanvien> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT *FROM tbl_nhanvien";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblNhanvien nv = new TblNhanvien();
            nv.setMaNV(result.getLong(1));
            nv.setTenNV(result.getString(2));
            nv.setGioiTinh(result.getString(3));
            nv.setNamSinh(result.getInt(4));
            nv.setDiaChi(result.getString(5));
            nv.setSdt(result.getInt(6));
            nv.setHinhAnh(result.getString(7));
            nv.setNgayVaoLam(result.getDate(8));
            nv.setTaiKhoan(result.getString(9));
            nv.setMatKhau(result.getString(10));
            nv.setQuyen(result.getString(11));
            list.add(nv);
        }
        return list;
    }
     public TblNhanvien getmaNV(String ma) throws SQLException {
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_nhanvien.* from tbl_nhanvien where tbl_nhanvien.MaNV=" +ma;
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        TblNhanvien nv = new TblNhanvien();
        while (result.next()) {
            nv.setMaNV(result.getLong(1));
            nv.setTenNV(result.getString(2));
            nv.setGioiTinh(result.getString(3));
            nv.setNamSinh(result.getInt(4));
            nv.setDiaChi(result.getString(5));
            nv.setSdt(result.getInt(6));
            nv.setHinhAnh(result.getString(7));
            nv.setNgayVaoLam(result.getDate(8));
            nv.setTaiKhoan(result.getString(9));
            nv.setMatKhau(result.getString(10));
            nv.setQuyen(result.getString(11));
        }
        return nv;
     }
      public ArrayList<TblNhanvien> timkiemNV(String tk) throws SQLException {
        ArrayList<TblNhanvien> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT *FROM tbl_nhanvien where (MaNV like N'%"+tk+"%' or TenNV like N'%"+tk+"%' or GioiTinh like N'%"+tk+"%' or NamSinh like N'%"+tk+"%' or Quyen like N'%"+tk+"%' or Diachi like N'%"+tk+"%' or SDT like N'%"+tk+"%')";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblNhanvien nv = new TblNhanvien();
            nv.setMaNV(result.getLong(1));
            nv.setTenNV(result.getString(2));
            nv.setGioiTinh(result.getString(3));
            nv.setNamSinh(result.getInt(4));
            nv.setDiaChi(result.getString(5));
            nv.setSdt(result.getInt(6));
            nv.setHinhAnh(result.getString(7));
            nv.setNgayVaoLam(result.getDate(8));
            nv.setTaiKhoan(result.getString(9));
            nv.setMatKhau(result.getString(10));
            nv.setQuyen(result.getString(11));
            list.add(nv);
        }
        return list;
    }
}
