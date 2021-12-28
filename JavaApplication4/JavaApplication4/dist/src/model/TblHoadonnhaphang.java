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
@Table(name = "tbl_hoadonnhaphang")
@NamedQueries({
    @NamedQuery(name = "TblHoadonnhaphang.findAll", query = "SELECT t FROM TblHoadonnhaphang t"),
    @NamedQuery(name = "TblHoadonnhaphang.findByMaHDNH", query = "SELECT t FROM TblHoadonnhaphang t WHERE t.maHDNH = :maHDNH"),
    @NamedQuery(name = "TblHoadonnhaphang.findByNgayNhap", query = "SELECT t FROM TblHoadonnhaphang t WHERE t.ngayNhap = :ngayNhap"),
    @NamedQuery(name = "TblHoadonnhaphang.findByTongTien", query = "SELECT t FROM TblHoadonnhaphang t WHERE t.tongTien = :tongTien")})
public class TblHoadonnhaphang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaHDNH")
    private Long maHDNH;
    @Basic(optional = false)
    @Column(name = "NgayNhap")
    @Temporal(TemporalType.DATE)
    private Date ngayNhap;
    @Basic(optional = false)
    @Column(name = "TongTien")
    private float tongTien;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblHoadonnhaphang")
    private Collection<Chitiethdnh> chitiethdnhCollection;
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV")
    @ManyToOne(optional = false)
    private Long maNV;
    private String TenNV;

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public TblHoadonnhaphang() {
    }

    public TblHoadonnhaphang(Long maHDNH) {
        this.maHDNH = maHDNH;
    }

    public TblHoadonnhaphang(Long maHDNH, Date ngayNhap, float tongTien) {
        this.maHDNH = maHDNH;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
    }

    public Long getMaHDNH() {
        return maHDNH;
    }

    public void setMaHDNH(Long maHDNH) {
        this.maHDNH = maHDNH;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Collection<Chitiethdnh> getChitiethdnhCollection() {
        return chitiethdnhCollection;
    }

    public void setChitiethdnhCollection(Collection<Chitiethdnh> chitiethdnhCollection) {
        this.chitiethdnhCollection = chitiethdnhCollection;
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
        hash += (maHDNH != null ? maHDNH.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblHoadonnhaphang)) {
            return false;
        }
        TblHoadonnhaphang other = (TblHoadonnhaphang) object;
        if ((this.maHDNH == null && other.maHDNH != null) || (this.maHDNH != null && !this.maHDNH.equals(other.maHDNH))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblHoadonnhaphang[ maHDNH=" + maHDNH + " ]";
    }
    
}
