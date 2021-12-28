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
import model.Chitiethdbh;

/**
 *
 * @author Thang
 */
public class CTHDBanHangXLDL {

    KNCSDL kn = null;

    public CTHDBanHangXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<Chitiethdbh> getListCTBH(String id) throws SQLException {
        ArrayList<Chitiethdbh> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT chitiethdbh.*, tbl_sanpham.TenSP FROM chitiethdbh, tbl_sanpham where chitiethdbh.MaSP = tbl_sanpham.MaSP and MaHDBH=" + id;
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Chitiethdbh CTHDBH = new Chitiethdbh();
            CTHDBH.setMaHDBH(result.getLong(1));
            CTHDBH.setMaSP(result.getLong(2));
            CTHDBH.setSoLuong(result.getInt(3));
            CTHDBH.setTongTien(result.getFloat(4));
            CTHDBH.setTenSP(result.getString(5));
            list.add(CTHDBH);
        }
        return list;
    }

   

    public ArrayList<Chitiethdbh> timkiemCTBH(String tk) throws SQLException {
        ArrayList<Chitiethdbh> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT chitiethdbh.*, tbl_sanpham.TenSP FROM chitiethdbh, tbl_sanpham where chitiethdbh.MaSP = tbl_sanpham.MaSP and ( MaHDBH like N'%" + tk + "%' or TenSP like N'%" + tk + "%' or TongTien like N'%" + tk + "%')";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            Chitiethdbh CTHDBH = new Chitiethdbh();
            CTHDBH.setMaHDBH(result.getLong(1));
            CTHDBH.setMaSP(result.getLong(2));
            CTHDBH.setSoLuong(result.getInt(3));
            CTHDBH.setTongTien(result.getInt(4));
            CTHDBH.setTenSP(result.getString(5));
            list.add(CTHDBH);
        }
        return list;
    }
}
