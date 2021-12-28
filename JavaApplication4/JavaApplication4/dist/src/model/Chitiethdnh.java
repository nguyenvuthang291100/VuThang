/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Thang
 */
@Entity
@Table(name = "chitiethdnh")
@NamedQueries({
    @NamedQuery(name = "Chitiethdnh.findAll", query = "SELECT c FROM Chitiethdnh c"),
    @NamedQuery(name = "Chitiethdnh.findByMaHDNH", query = "SELECT c FROM Chitiethdnh c WHERE c.chitiethdnhPK.maHDNH = :maHDNH"),
    @NamedQuery(name = "Chitiethdnh.findByMaSP", query = "SELECT c FROM Chitiethdnh c WHERE c.chitiethdnhPK.maSP = :maSP"),
    @NamedQuery(name = "Chitiethdnh.findByDonGiaNhap", query = "SELECT c FROM Chitiethdnh c WHERE c.donGiaNhap = :donGiaNhap"),
    @NamedQuery(name = "Chitiethdnh.findBySoLuong", query = "SELECT c FROM Chitiethdnh c WHERE c.soLuong = :soLuong"),
    @NamedQuery(name = "Chitiethdnh.findByTongTien", query = "SELECT c FROM Chitiethdnh c WHERE c.tongTien = :tongTien")})
public class Chitiethdnh implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChitiethdnhPK chitiethdnhPK;
    @Basic(optional = false)
    @Column(name = "DonGiaNhap")
    private float donGiaNhap;
    @Basic(optional = false)
    @Column(name = "SoLuong")
    private int soLuong;
    @Basic(optional = false)
    @Column(name = "TongTien")
    private float tongTien;
    @JoinColumn(name = "MaHDNH", referencedColumnName = "MaHDNH", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Long MaHDNH;
    @JoinColumn(name = "MaSP", referencedColumnName = "MaSP", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Long MaSP;
    private String TenSP;

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }
    
    public Chitiethdnh() {
    }

    public Chitiethdnh(ChitiethdnhPK chitiethdnhPK) {
        this.chitiethdnhPK = chitiethdnhPK;
    }

    public Chitiethdnh(ChitiethdnhPK chitiethdnhPK, float donGiaNhap, int soLuong, float tongTien) {
        this.chitiethdnhPK = chitiethdnhPK;
        this.donGiaNhap = donGiaNhap;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public Chitiethdnh(long maHDNH, long maSP) {
        this.chitiethdnhPK = new ChitiethdnhPK(maHDNH, maSP);
    }

    public ChitiethdnhPK getChitiethdnhPK() {
        return chitiethdnhPK;
    }

    public void setChitiethdnhPK(ChitiethdnhPK chitiethdnhPK) {
        this.chitiethdnhPK = chitiethdnhPK;
    }

    public float getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(float donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public long getMaHDNH() {
        return MaHDNH;
    }

    public void setMaHDNH(long MaHDNH) {
        this.MaHDNH = MaHDNH;
    }

    public long getMaSP() {
        return MaSP;
    }

    public void setMaSP(long MaSP) {
        this.MaSP = MaSP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chitiethdnhPK != null ? chitiethdnhPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chitiethdnh)) {
            return false;
        }
        Chitiethdnh other = (Chitiethdnh) object;
        if ((this.chitiethdnhPK == null && other.chitiethdnhPK != null) || (this.chitiethdnhPK != null && !this.chitiethdnhPK.equals(other.chitiethdnhPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Chitiethdnh[ chitiethdnhPK=" + chitiethdnhPK + " ]";
    }
    
}
