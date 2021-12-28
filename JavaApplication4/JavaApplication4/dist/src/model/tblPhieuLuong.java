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
public class tblPhieuLuong {

    private String maTL, tenKL, tenNV, soNgayLV, thuong, tru, thue, tongLuong, ngayPhat;

    public tblPhieuLuong(String maTL, String tenKL, String tenNV, String soNgayLV, String thuong, String tru, String thue, String tongLuong, String ngayPhat) {
        this.maTL = maTL;
        this.tenKL = tenKL;
        this.tenNV = tenNV;
        this.soNgayLV = soNgayLV;
        this.thuong = thuong;
        this.tru = tru;
        this.thue = thue;
        this.tongLuong = tongLuong;
        this.ngayPhat = ngayPhat;
    }

    public tblPhieuLuong() {
    }

    public String getMaTL() {
        return maTL;
    }

    public void setMaTL(String maTL) {
        this.maTL = maTL;
    }

    public String getTenKL() {
        return tenKL;
    }

    public void setTenKL(String tenKL) {
        this.tenKL = tenKL;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSoNgayLV() {
        return soNgayLV;
    }

    public void setSoNgayLV(String soNgayLV) {
        this.soNgayLV = soNgayLV;
    }

    public String getThuong() {
        return thuong;
    }

    public void setThuong(String thuong) {
        this.thuong = thuong;
    }

    public String getTru() {
        return tru;
    }

    public void setTru(String tru) {
        this.tru = tru;
    }

    public String getThue() {
        return thue;
    }

    public void setThue(String thue) {
        this.thue = thue;
    }

    public String getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(String tongLuong) {
        this.tongLuong = tongLuong;
    }

    public String getNgayPhat() {
        return ngayPhat;
    }

    public void setNgayPhat(String ngayPhat) {
        this.ngayPhat = ngayPhat;
    }
    
}
