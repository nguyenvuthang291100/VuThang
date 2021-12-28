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
import model.inHoaDonBH;

/**
 *
 * @author Thang
 */
public class InHoaDonBHXLDL {
    KNCSDL kn=null;
    public InHoaDonBHXLDL(){
        kn=new KNCSDL();
    }
    public ArrayList<inHoaDonBH> getListHoaDon(String ma) throws SQLException{
        ArrayList<inHoaDonBH> list=new ArrayList<>();
        Connection conn=kn.getConnect();
        String sql="select hdbh.MaHDBH, hdbh.NgayLap, kh.MaKH, nv.MaNV, sp.TenSP, cthdbh.SoLuong, sp.DonGia, cthdbh.TongTien, hdbh.TongTien FROM tbl_hoadonbanhang hdbh, tbl_khachhang kh, tbl_nhanvien nv, tbl_sanpham sp, chitiethdbh cthdbh WHERE (hdbh.MaHDBH = cthdbh.MaHDBH and kh.MaKH = hdbh.MaKH and nv.MaNV = hdbh.MaNV and sp.MaSP = cthdbh.MaSP and cthdbh.MaHDBH="+ma+")";
        Statement statement=(Statement) conn.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            inHoaDonBH  nv=new inHoaDonBH();
            nv.setMaHD(result.getString(1));
            nv.setNgayLap(result.getString(2));
            nv.setMaKH(result.getString(3));
            nv.setMaNV(result.getString(4));
            nv.setTenSP(result.getString(5));
            nv.setSoLuong(result.getInt(6));
            nv.setDonGia(result.getFloat(7));
            nv.setTongtienSP(result.getFloat(8));
            nv.setTongTienHD(result.getFloat(9));
            list.add(nv);
        }
        return list;
    }
}
