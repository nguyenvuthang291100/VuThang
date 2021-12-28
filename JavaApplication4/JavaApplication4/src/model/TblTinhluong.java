/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Thang
 */
@Entity
@Table(name = "tbl_tinhluong")
@NamedQueries({
    @NamedQuery(name = "TblTinhluong.findAll", query = "SELECT t FROM TblTinhluong t"),
    @NamedQuery(name = "TblTinhluong.findByMaTL", query = "SELECT t FROM TblTinhluong t WHERE t.maTL = :maTL"),
    @NamedQuery(name = "TblTinhluong.findBySoNgayLam", query = "SELECT t FROM TblTinhluong t WHERE t.soNgayLam = :soNgayLam"),
    @NamedQuery(name = "TblTinhluong.findByThuong", query = "SELECT t FROM TblTinhluong t WHERE t.thuong = :thuong"),
    @NamedQuery(name = "TblTinhluong.findByTru", query = "SELECT t FROM TblTinhluong t WHERE t.tru = :tru"),
    @NamedQuery(name = "TblTinhluong.findByThue", query = "SELECT t FROM TblTinhluong t WHERE t.thue = :thue"),
    @NamedQuery(name = "TblTinhluong.findByTongLuong", query = "SELECT t FROM TblTinhluong t WHERE t.tongLuong = :tongLuong"),
    @NamedQuery(name = "TblTinhluong.findByNgayPhat", query = "SELECT t FROM TblTinhluong t WHERE t.ngayPhat = :ngayPhat")})
public class TblTinhluong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaTL")
    private Long maTL;
    @Basic(optional = false)
    @Column(name = "SoNgayLam")
    private int soNgayLam;
    @Basic(optional = false)
    @Column(name = "Thuong")
    private float thuong;
    @Basic(optional = false)
    @Column(name = "Tru")
    private float tru;
    @Basic(optional = false)
    @Column(name = "Thue")
    private float thue;
    @Basic(optional = false)
    @Column(name = "TongLuong")
    private float tongLuong;
    @Basic(optional = false)
    @Column(name = "NgayPhat")
    @Temporal(TemporalType.DATE)
    private Date ngayPhat;
    @JoinColumn(name = "MaChamCong", referencedColumnName = "MaChamCong")
    @ManyToOne(optional = false)
    private Long maChamCong;
    @JoinColumn(name = "MaKL", referencedColumnName = "MaKL")
    @ManyToOne(optional = false)
    private Long maKL;
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV")
    @ManyToOne(optional = false)
    private Long maNV;
    private String TenNV;
    private String TenKL;

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getTenKL() {
        return TenKL;
    }

    public void setTenKL(String TenKL) {
        this.TenKL = TenKL;
    }
   

    public TblTinhluong() {
    }

    public TblTinhluong(Long maTL) {
        this.maTL = maTL;
    }

    public TblTinhluong(Long maTL, int soNgayLam, float thuong, float tru, float thue, float tongLuong, Date ngayPhat) {
        this.maTL = maTL;
        this.soNgayLam = soNgayLam;
        this.thuong = thuong;
        this.tru = tru;
        this.thue = thue;
        this.tongLuong = tongLuong;
        this.ngayPhat = ngayPhat;
    }

    public Long getMaTL() {
        return maTL;
    }

    public void setMaTL(Long maTL) {
        this.maTL = maTL;
    }

    public int getSoNgayLam() {
        return soNgayLam;
    }

    public void setSoNgayLam(int soNgayLam) {
        this.soNgayLam = soNgayLam;
    }

    public float getThuong() {
        return thuong;
    }

    public void setThuong(float thuong) {
        this.thuong = thuong;
    }

    public float getTru() {
        return tru;
    }

    public void setTru(float tru) {
        this.tru = tru;
    }

    public float getThue() {
        return thue;
    }

    public void setThue(float thue) {
        this.thue = thue;
    }

    public float getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(float tongLuong) {
        this.tongLuong = tongLuong;
    }

    public Date getNgayPhat() {
        return ngayPhat;
    }

    public void setNgayPhat(Date ngayPhat) {
        this.ngayPhat = ngayPhat;
    }

    public long getMaChamCong() {
        return maChamCong;
    }

    public void setMaChamCong(long maChamCong) {
        this.maChamCong = maChamCong;
    }

    public long getMaKL() {
        return maKL;
    }

    public void setMaKL(long maKL) {
        this.maKL = maKL;
    }

    public long getMaNV() {
        return maNV;
    }

    public void setMaNV(long maNV) {
        this.maNV = maNV;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maTL != null ? maTL.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTinhluong)) {
            return false;
        }
        TblTinhluong other = (TblTinhluong) object;
        if ((this.maTL == null && other.maTL != null) || (this.maTL != null && !this.maTL.equals(other.maTL))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblTinhluong[ maTL=" + maTL + " ]";
    }
    
}
