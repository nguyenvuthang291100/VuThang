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

/**
 *
 * @author Thang
 */
public class DangnhapXLDL {

    KNCSDL kn = null;

    public DangnhapXLDL() {
        kn = new KNCSDL();
    }

    public String dangnhap(String taikhoan, String matKhau) throws SQLException {

        Connection conn = kn.getConnect();
        String sql = "SELECT * FROM tbl_nhanvien where TaiKhoan like'" + taikhoan + "' and MatKhau like '" + matKhau + "'";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        int i = result.getRow();
        while (result.next()) {   
            return result.getString(11);
        }
        return "0";

    }
}
