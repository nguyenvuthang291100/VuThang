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
@Table(name = "tbl_chamcong")
@NamedQueries({
    @NamedQuery(name = "TblChamcong.findAll", query = "SELECT t FROM TblChamcong t"),
    @NamedQuery(name = "TblChamcong.findByMaChamCong", query = "SELECT t FROM TblChamcong t WHERE t.maChamCong = :maChamCong"),
    @NamedQuery(name = "TblChamcong.findByNgay", query = "SELECT t FROM TblChamcong t WHERE t.ngay = :ngay"),
    @NamedQuery(name = "TblChamcong.findByGioVao", query = "SELECT t FROM TblChamcong t WHERE t.gioVao = :gioVao"),
    @NamedQuery(name = "TblChamcong.findByGioRa", query = "SELECT t FROM TblChamcong t WHERE t.gioRa = :gioRa"),
    @NamedQuery(name = "TblChamcong.findByGhiChu", query = "SELECT t FROM TblChamcong t WHERE t.ghiChu = :ghiChu")})
public class TblChamcong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaChamCong")
    private Long maChamCong;
    @Basic(optional = false)
    @Column(name = "Ngay")
    @Temporal(TemporalType.DATE)
    private Date ngay;
    @Basic(optional = false)
    @Column(name = "GioVao")
    @Temporal(TemporalType.TIME)
    private Date gioVao;
    @Basic(optional = false)
    @Column(name = "GioRa")
    @Temporal(TemporalType.TIME)
    private Date gioRa;
    @Basic(optional = false)
    @Column(name = "GhiChu")
    private String ghiChu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maChamCong")
    private Collection<TblTinhluong> tblTinhluongCollection;
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

    public TblChamcong() {
    }

    public TblChamcong(Long maChamCong) {
        this.maChamCong = maChamCong;
    }

    public TblChamcong(Long maChamCong, Date ngay, Date gioVao, Date gioRa, String ghiChu) {
        this.maChamCong = maChamCong;
        this.ngay = ngay;
        this.gioVao = gioVao;
        this.gioRa = gioRa;
        this.ghiChu = ghiChu;
    }

    public Long getMaChamCong() {
        return maChamCong;
    }

    public void setMaChamCong(Long maChamCong) {
        this.maChamCong = maChamCong;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Date getGioVao() {
        return gioVao;
    }

    public void setGioVao(Date gioVao) {
        this.gioVao = gioVao;
    }

    public Date getGioRa() {
        return gioRa;
    }

    public void setGioRa(Date gioRa) {
        this.gioRa = gioRa;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Collection<TblTinhluong> getTblTinhluongCollection() {
        return tblTinhluongCollection;
    }

    public void setTblTinhluongCollection(Collection<TblTinhluong> tblTinhluongCollection) {
        this.tblTinhluongCollection = tblTinhluongCollection;
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
        hash += (maChamCong != null ? maChamCong.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblChamcong)) {
            return false;
        }
        TblChamcong other = (TblChamcong) object;
        if ((this.maChamCong == null && other.maChamCong != null) || (this.maChamCong != null && !this.maChamCong.equals(other.maChamCong))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TblChamcong[ maChamCong=" + maChamCong + " ]";
    }
    
}
