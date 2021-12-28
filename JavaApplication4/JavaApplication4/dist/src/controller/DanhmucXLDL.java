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
import model.TblDanhmuc;
import model.TblNhanvien;

/**
 *
 * @author Thang
 */
public class DanhmucXLDL {
    
    KNCSDL kn = null;

    public DanhmucXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<TblDanhmuc> getListDM() throws SQLException {
        ArrayList<TblDanhmuc> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT *FROM tbl_danhmuc";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblDanhmuc dm = new TblDanhmuc();
            dm.setMaDM(result.getLong(1));
            dm.setTenDM(result.getString(2));
            list.add(dm);
        }
        return list;
    }
     public ArrayList<TblDanhmuc> timkiemDM(String tk) throws SQLException {
        ArrayList<TblDanhmuc> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT *FROM tbl_danhmuc where (MaDM like N'%"+tk+"%' or TenDM like N'%"+tk+"%')";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblDanhmuc dm = new TblDanhmuc();
            dm.setMaDM(result.getLong(1));
            dm.setTenDM(result.getString(2));
            list.add(dm);
        }
        return list;
    }
}
