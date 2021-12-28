/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Thang
 */
public class thongke {
    private double doanhThu;
    private  String tenDoanhThu;
    private String thang;

    public thongke() {
    }

    public thongke(double doanhThu, String tenDoanhThu, String thang) {
        this.doanhThu = doanhThu;
        this.tenDoanhThu = tenDoanhThu;
        this.thang = thang;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public String getTenDoanhThu() {
        return tenDoanhThu;
    }

    public void setTenDoanhThu(String tenDoanhThu) {
        this.tenDoanhThu = tenDoanhThu;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }
    
    
}
