/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Thang
 */
@Entity
@Table(name = "tbl_hoadonbanhang")
@NamedQueries({
    @NamedQuery(name = "TblHoadonbanhang.findAll", query = "SELECT t FROM TblHoadonbanhang t"),
    @NamedQuery(name = "TblHoadonbanhang.findByMaHDBH", query = "SELECT t FROM TblHoadonbanhang t WHERE t.maHDBH = :maHDBH"),
    @NamedQuery(name = "TblHoadonbanhang.findByNgayLap", query = "SELECT t FROM TblHoadonbanhang t WHERE t.ngayLap = :ngayLap"),
    @NamedQuery(name = "TblHoadonbanhang.findByTongTien", query = "SELECT t FROM TblHoadonbanhang t WHERE t.tongTien = :tongTien")})
public class TblHoadonbanhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaHDBH")
    private Long maHDBH;
    @Basic(optional = false)
    @Column(name = "NgayLap")
    @Temporal(TemporalType.DATE)
    private Date ngayLap;
    @Basic(optional = false)
    @Column(name = "TongTien")
    private float tongTien;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblHoadonbanhang")
    private Collection<Chitiethdbh> chitiethdbhCollection;
    @JoinColumn(name = "MaKH", referencedColumnName = "MaKH")
    @ManyToOne(optional = false)
    private Long maKH;
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV")
    @ManyToOne(optional = false)
    private Long maNV;
    private  String TenKH;
    private  String TenNV;

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenDM) {
        this.TenNV = TenDM;
    }

    public TblHoadonbanhang() {
    }

    public TblHoadonbanhang(Long maHDBH) {
        this.maHDBH = maHDBH;
    }

    public TblHoadonbanhang(Long maHDBH, Date ngayLap, float tongTien) {
        this.maHDBH = maHDBH;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    public Long getMaHDBH() {
        return maHDBH;
    }

    public void setMaHDBH(Long maHDBH) {
        this.maHDBH = maHDBH;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Collection<Chitiethdbh> getChitiethdbhCollection() {
        return chitiethdbhCollection;
    }

    public void setChitiethdbhCollection(Collection<Chitiethdbh> chitiethdbhCollection) {
        this.chitiethdbhCollection = chitiethdbhCollection;
    }

    public long getMaKH() {
        return maKH;
    }

    public void setMaKH(long maKH) {
        this.maKH = maKH;
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
        hash += (maHDBH != null ? maHDBH.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblHoadonbanhang)) {
            return false;
        }
        TblHoadonbanhang other = (TblHoadonbanhang) object;
        if ((this.maHDBH == null && other.maHDBH != null) || (this.maHDBH != null && !this.maHDBH.equals(other.maHDBH))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblHoadonbanhang[ maHDBH=" + maHDBH + " ]";
    }
    
}
