/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Thang
 */
@Entity
@Table(name = "tbl_khachhang")
@NamedQueries({
    @NamedQuery(name = "TblKhachhang.findAll", query = "SELECT t FROM TblKhachhang t"),
    @NamedQuery(name = "TblKhachhang.findByMaKH", query = "SELECT t FROM TblKhachhang t WHERE t.maKH = :maKH"),
    @NamedQuery(name = "TblKhachhang.findByTenKH", query = "SELECT t FROM TblKhachhang t WHERE t.tenKH = :tenKH"),
    @NamedQuery(name = "TblKhachhang.findByLoai", query = "SELECT t FROM TblKhachhang t WHERE t.loai = :loai")})
public class TblKhachhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaKH")
    private Long maKH;
    @Basic(optional = false)
    @Column(name = "TenKH")
    private String tenKH;
    @Basic(optional = false)
    @Column(name = "Loai")
    private String loai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maKH")
    private Collection<TblHoadonbanhang> tblHoadonbanhangCollection;

    public TblKhachhang() {
    }

    public TblKhachhang(Long maKH) {
        this.maKH = maKH;
    }

    public TblKhachhang(Long maKH, String tenKH, String loai) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.loai = loai;
    }

    public Long getMaKH() {
        return maKH;
    }

    public void setMaKH(Long maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Collection<TblHoadonbanhang> getTblHoadonbanhangCollection() {
        return tblHoadonbanhangCollection;
    }

    public void setTblHoadonbanhangCollection(Collection<TblHoadonbanhang> tblHoadonbanhangCollection) {
        this.tblHoadonbanhangCollection = tblHoadonbanhangCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maKH != null ? maKH.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblKhachhang)) {
            return false;
        }
        TblKhachhang other = (TblKhachhang) object;
        if ((this.maKH == null && other.maKH != null) || (this.maKH != null && !this.maKH.equals(other.maKH))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tenKH;
    }
    
}
