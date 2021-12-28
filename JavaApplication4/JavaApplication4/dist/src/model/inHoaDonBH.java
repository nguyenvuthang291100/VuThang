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
public class inHoaDonBH {

    private String maHD, ngayLap, maKH, maNV, tenSP;
    private int soLuong;
    private float tongtienSP, tongTienHD, donGia;

    public inHoaDonBH() {
    }

    public inHoaDonBH(String maHD, String ngayLap, String maKH, String maNV, String tenSP, int soLuong, float tongTienSP, float tongTienHD, float donGia) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.maKH = maKH;
        this.maNV = maNV;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.tongtienSP = tongTienSP;
        this.tongTienHD = tongTienHD;
        this.donGia = donGia;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTongtienSP() {
        return tongtienSP;
    }

    public void setTongtienSP(float tongtienSP) {
        this.tongtienSP = tongtienSP;
    }

    public float getTongTienHD() {
        return tongTienHD;
    }

    public void setTongTienHD(float tongTienHD) {
        this.tongTienHD = tongTienHD;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    

}
