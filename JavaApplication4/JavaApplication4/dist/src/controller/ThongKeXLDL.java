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
import java.util.List;
import model.TblHoadonbanhang;
import model.slMatHang;

/**
 *
 * @author Thang
 */
public class ThongKeXLDL {

    KNCSDL kn = null;

    public ThongKeXLDL() {
        kn = new KNCSDL();
    }

    public ArrayList<TblHoadonbanhang> getThongKeTC() throws SQLException {
        ArrayList<TblHoadonbanhang> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT TongTien FROM tbl_hoadonbanhang";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            TblHoadonbanhang HDBH = new TblHoadonbanhang();
            HDBH.setTongTien(result.getFloat(1));
            list.add(HDBH);
        }
        return list;
    }
}
