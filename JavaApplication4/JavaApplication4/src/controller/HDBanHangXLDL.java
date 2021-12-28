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
import model.TblHoadonbanhang;
import model.thongke;

/**
 *
 * @author Thang
 */
public class HDBanHangXLDL {

    KNCSDL kn = null;

    public HDBanHangXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<TblHoadonbanhang> getThongKe(String tungay, String denngay) throws SQLException {
        ArrayList<TblHoadonbanhang> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_hoadonbanhang.*, tbl_khachhang.TenKH, tbl_nhanvien.TenNV FROM tbl_hoadonbanhang, tbl_khachhang, tbl_nhanvien where tbl_hoadonbanhang.MaKH = tbl_khachhang.MaKH and tbl_hoadonbanhang.MaNV = tbl_nhanvien.MaNV and tbl_hoadonbanhang.NgayLap Between + '" + tungay + "' and '" + denngay + "'";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblHoadonbanhang HDBH = new TblHoadonbanhang();
            HDBH.setMaHDBH(result.getLong(1));
            HDBH.setMaKH(result.getLong(2));
            HDBH.setNgayLap(result.getDate(3));
            HDBH.setMaNV(result.getLong(4));
            HDBH.setTongTien(result.getFloat(5));
            HDBH.setTenKH(result.getString(6));
            HDBH.setTenNV(result.getString(7));
            list.add(HDBH);
        }
        return list;
    }

    public ArrayList<thongke> thongKe() throws SQLException {
        ArrayList<thongke> listHD = new ArrayList<>();
        listHD.clear();
        Connection conn = kn.getConnect();
        String sql = "SELECT  month(tbl_hoadonbanhang.NgayLap), sum(tbl_hoadonbanhang.TongTien) FROM tbl_hoadonbanhang WHERE YEAR(tbl_hoadonbanhang.NgayLap) = year(now()) GROUP BY month(tbl_hoadonbanhang.NgayLap)";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            thongke hd = new thongke();
            hd.setThang("Tháng" + result.getString(1));
            hd.setDoanhThu(result.getDouble(2));
            hd.setTenDoanhThu("Doanh Thu");
            listHD.add(hd);
        }
        return listHD;
    }

    public ArrayList<TblHoadonbanhang> getListHDBH(String Ngay) throws SQLException {
        ArrayList<TblHoadonbanhang> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_hoadonbanhang.*, tbl_khachhang.TenKH, tbl_nhanvien.TenNV FROM tbl_hoadonbanhang, tbl_khachhang, tbl_nhanvien where tbl_hoadonbanhang.MaKH = tbl_khachhang.MaKH and tbl_hoadonbanhang.MaNV = tbl_nhanvien.MaNV and NgayLap='" + Ngay + "'";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblHoadonbanhang HDBH = new TblHoadonbanhang();
            HDBH.setMaHDBH(result.getLong(1));
            HDBH.setMaKH(result.getLong(2));
            HDBH.setNgayLap(result.getDate(3));
            HDBH.setMaNV(result.getLong(4));
            HDBH.setTongTien(result.getFloat(5));
            HDBH.setTenKH(result.getString(6));
            HDBH.setTenNV(result.getString(7));
            list.add(HDBH);
        }
        return list;
    }

    public ArrayList<TblHoadonbanhang> timkiemHDBH(String tk) throws SQLException {
        ArrayList<TblHoadonbanhang> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_hoadonbanhang.*, tbl_khachhang.TenKH, tbl_nhanvien.TenNV FROM tbl_hoadonbanhang, tbl_khachhang, tbl_nhanvien where tbl_hoadonbanhang.MaKH = tbl_khachhang.MaKH and tbl_hoadonbanhang.MaNV = tbl_nhanvien.MaNV and (MaHDBH like N'%" + tk + "%' or TenKH like N'%" + tk + "%' or TenNV like N'%" + tk + "%')";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblHoadonbanhang HDBH = new TblHoadonbanhang();
            HDBH.setMaHDBH(result.getLong(1));
            HDBH.setMaKH(result.getLong(2));
            HDBH.setNgayLap(result.getDate(3));
            HDBH.setMaNV(result.getLong(4));
            HDBH.setTongTien(result.getFloat(5));
            HDBH.setTenKH(result.getString(6));
            HDBH.setTenNV(result.getString(7));
            list.add(HDBH);
        }
        return list;
    }
}
